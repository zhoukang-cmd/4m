package com.fourm.server.task;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.fourm.common.FileData;
import com.fourm.common.Utils;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 服务端定时运行任务 读取各户端上传的zip文件，处理数据入库，转移zip文件至hist目录
 * 
 * @author zhangtaichao , Mobile Bank System, CSII
 *         <p>
 *         created on 2012-3-1
 *         </p>
 *         modified by wangzhe 2012-03-24 精确描述字段错误个数 
 *         modified by wangzhe 2012-03-25 低频表增加L,扩展名.dat,异常信息修正 
 *         modified by wangzhe 2012-05-15增加事物支持，提高速度 
 *         modified by yangaozhen 2012-12-29 重构代码
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcessFileTask {

	public static final String BAT_STATUS_NO = "0";
	public static final String BAT_STATUS_ING = "1";
	public static final String BAT_STATUS_SUCCESS = "2";
	public static final String BAT_STATUS_FAIL = "3";

	protected Logger logger = LoggerFactory.getLogger(ProcessFileTask.class);
	private String dataPath;
	private String histPath;
	private String lvmPath;
	private String encoding = "gbk";
	private int hBatchNum = 1000;
	private int lBatchNum = 100;
	private SqlMapClientTemplate sqlMapClient;
	private TransactionTemplate transactionTemplate;

	/**
	 * 查询并处理批次表中的文件
	 */
	public void process() {
		pathCheck();
		try {
			List list = getSqlMapClient().queryForList("server.selectBAT");
			if (CollectionUtils.isEmpty(list)) {
				return;
			}
			for (Iterator<Map> i = list.iterator(); i.hasNext();) {
				Map tmp = i.next();
				logger.info("start to process bat_id:" + tmp.get("PAKNAME"));
				int batid = ((Integer) tmp.get("BAT_ID")).intValue();
				// int batid = ((BigDecimal)tmp.get("BAT_ID")).intValue();
				String pakname = (String) tmp.get("PAKNAME");
				String zippath = (String) tmp.get("ZIP_PATH");
				processSingleZip(batid, pakname, zippath);
				String histPath = getHistPath() + "/" + tmp.get("PAKNAME");
				move(tmp.get("ZIP_PATH").toString(), histPath);
				move(tmp.get("ZIP_PATH").toString() + ".ok", histPath + ".ok");
				logger.info("end process bat_id:" + tmp.get("PAKNAME"));
			}
		} catch (Exception e) {
			logger.error(Utils.printStackTrace(e));
		}
	}

	/**
	 * 处理单个压缩数据文件
	 */
	private boolean processSingleZip(int batid, String filename, String src) {
		boolean result = false;
		Date start = new Date();
		try {
			File file = new File(src);
			if (!file.exists()) {
				updateBAT(batid, BAT_STATUS_FAIL, Utils.E001, "文件不存在，已被删除", start, new Date());
				return false;
			}
			BufferedReader br = null;
			FileData filedata = new FileData();
			ZipFile zip = new ZipFile(src);

			Enumeration entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String name = entry.getName();

				try {
					dealFileName(name, filedata);
				} catch (Exception e) {
					result = false;
					updateBAT(batid, BAT_STATUS_FAIL, Utils.E002, "文件名不符合规则:" + e.getMessage(), start, new Date());
					break;
				}

				InputStream is = zip.getInputStream(entry);
				br = new BufferedReader(new InputStreamReader(is, getEncoding()));
				if ("H".equals(filedata.fileType)) {// 振动信号数据文件
					result = processHFile(batid, is, entry, start);
				} else if ("L".equals(filedata.fileType)) {// 非振动信号数据文件
					result = processLFile(batid, br, filedata, start);
				}
			}
			if (br != null) {
				br.close();
			}
			zip.close();
		} catch (Exception e) {
			updateBAT(batid, BAT_STATUS_FAIL, Utils.E999, "未知异常", start, new Date());
			logger.error("process zipfile error:" + src + "\n" + Utils.printStackTrace(e));
		}
		return result;
	}

	/**
	 * 处理振动数据文件
	 */

	private boolean processHFile(int batid, InputStream is, ZipEntry entry, Date start) throws Exception {
		String[] arr = entry.getName().split("\\+");
		String objPath = lvmPath + "/" + arr[1] + "_" + arr[2] + "_" + arr[3] + "_" + arr[4] + "/"
				+ arr[7].substring(0, 6);
		try {
			File objDir = new File(objPath);
			if (!objDir.exists()) {
				objDir.mkdirs();
			}
		} catch (Exception e) {
			updateBAT(batid, BAT_STATUS_FAIL, Utils.E999, "建立lvm目录失败", new Date(), new Date());
			logger.error("process H zipfile error:" + entry.getName() + "\n" + Utils.printStackTrace(e));
			return false;
		}
		BufferedInputStream bin = new BufferedInputStream(is);
		File fout = new File(objPath + "/" + entry.getName());
		fout.createNewFile();
		FileOutputStream out = new FileOutputStream(fout);
		int len;
		try {
			while ((len = bin.read()) > -1) {
				out.write(len);
			}
		} catch (Exception e) {
			updateBAT(batid, BAT_STATUS_FAIL, Utils.E999, "解压失败", new Date(), new Date());
			logger.error("process H zipfile error:" + entry.getName() + "\n" + Utils.printStackTrace(e));
			out.close();
			return false;
		}
		bin.close();
		out.close();
		updateBAT(batid, BAT_STATUS_SUCCESS, "", "处理成功", new Date(), new Date());
		return true;
	}

	/**
	 * 处理非振动数据文件
	 */
	private boolean processLFile(int batid, BufferedReader br, FileData filedata, Date start) throws Exception {
		// 从数据库查询采集点个数
		int fieldCount = getFieldCount(filedata.equipName, filedata.equipCode, filedata.mine, filedata.room, "L");
		if (fieldCount == -1) {
			updateBAT(batid, BAT_STATUS_FAIL, Utils.E005, "获取设备振动信号采集点失败", start, new Date());
			return false;
		}
		// 建立数据库表
		String tableName = "T_L_" + filedata.province + "_" + filedata.company + "_" + filedata.mine + "_"
				+ filedata.room + "_" + filedata.equipName + "_" + filedata.equipCode;
		String columns[] = new String[fieldCount];
		for (int i = 0; i < fieldCount; i++) {
			columns[i] = "VALUE" + (i + 1);
		}
		Map create = new HashMap();
		create.put("TABLENAME", tableName);
		create.put("colList", columns);
		try {
			getSqlMapClient().update("server.createLTable", create);
		} catch (Exception e) {
			String errMsg = Utils.printStackTrace(e);
			if ((errMsg.indexOf("数据库中已存在") == -1) && // oracle
					(errMsg.indexOf("name is already used") == -1)) { // mssql
				logger.error(errMsg);
			}
		}
		// 准备读文件
		Calendar c = Calendar.getInstance();
		c.setTime(filedata.collectDate);
		List<Map> lineList = new ArrayList<Map>();
		double[] oldValues = new double[fieldCount + 1];
		for (int j = 0; j < oldValues.length; j++) {
			oldValues[j] = -1;
		}
		String qflag = "1";
		while (br.ready()) {
			String line = br.readLine();
			if (!br.ready()) {
				qflag = "0";
			}
			if (StringUtils.isNotEmpty(line)) {
				String[] ss = line.split(FileData.LSPLIT);
				if ((ss.length - 1) > fieldCount) {// 第一列为时间列，故数据列少1
					updateBAT(batid, BAT_STATUS_FAIL, Utils.E009,
							"文件内容实际列数大于已配置振动采集点个数[" + (ss.length - 1) + "/" + fieldCount + "]", start, new Date());
					return false;
				}
				// 获取当前行的时间
				Calendar dateTime = Calendar.getInstance();
				try {
					dateTime.setTime(FileData.TIME_FMT.parse(ss[0]));
					dateTime.set(Calendar.YEAR, c.get(Calendar.YEAR));
					dateTime.set(Calendar.MONTH, c.get(Calendar.MONTH));
					dateTime.set(Calendar.DATE, c.get(Calendar.DATE));
				} catch (Exception e) {
					logger.error("error time format:" + line);
					continue;
				}
				// 将当前行的数据加入列表
				// TODO 如果需要去重功能，请修改这部分代码
				Map tmpMap = new HashMap();
				tmpMap.put("line", line.substring(ss[0].length()).trim());
				tmpMap.put("time", dateTime.getTime());
				tmpMap.put("qflag", qflag);
				lineList.add(tmpMap);
			}
			if (lineList.size() >= getlBatchNum()) {
				insertLValues(lineList, batid, tableName);
				lineList.clear();
			}
		}
		if (lineList.size() > 0) {
			insertLValues(lineList, batid, tableName);
			lineList.clear();
		}
		updateBAT(batid, BAT_STATUS_SUCCESS, "", "处理成功", start, new Date());
		return true;
	}

	/**
	 * 写入非振动数据至数据库
	 */
	private void insertLValues(List<Map> list, int bat_id, String table) {
		final List<Map> dataList = new ArrayList<Map>();
		for (Iterator<Map> it = list.iterator(); it.hasNext();) {
			Map tmpMap = it.next();
			Date time = (Date) tmpMap.get("time");
			String[] tmpss = ((String) tmpMap.get("line")).split(FileData.LSPLIT);
			String qflag = (String) tmpMap.get("qflag");

			Map lineData = new HashMap();
			lineData.put("REAL_TIME", time);
			lineData.put("BAT_ID", bat_id);
			lineData.put("TABLENAME", table);
			lineData.put("QFLAG", qflag);

			String[] colList = new String[tmpss.length];
			String[] valList = new String[tmpss.length];
			for (int i = 0; i < tmpss.length; i++) {
				colList[i] = "VALUE" + (i + 1);
				valList[i] = tmpss[i];
			}

			lineData.put("colList", colList);
			lineData.put("valList", valList);
			dataList.add(lineData);
		}
		try {
			transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus transactionstatus) {
					((SqlMapClientTemplate) getSqlMapClient()).execute(new SqlMapClientCallback() {
						public Object doInSqlMapClient(SqlMapExecutor exec) throws SQLException {
							exec.startBatch();
							for (Map ins : dataList) {
								exec.insert("server.insertLTables", ins);
							}
							exec.executeBatch();
							return null;
						}
					});
					return null;
				}
			});
		} catch (DataIntegrityViolationException de) {
			// 主键约束异常
		} catch (Exception e) {
			logger.error("database error\n" + Utils.printStackTrace(e));
		}
	}

	/**
	 * 将src文件移至dest
	 */
	public void move(String src, String dest) {
		File sf = new File(src);
		File df = new File(dest);
		try {
			FileUtils.copyFile(sf, df);
			FileUtils.forceDelete(sf);
			logger.info("file move success:" + src + "->" + dest);
		} catch (IOException e) {
			logger.warn("file move fail:" + src + "->" + dest + "\n" + Utils.printStackTrace(e));
		}
	}

	/**
	 * 更新批次表
	 */
	private void updateBAT(int batid, String status, String errcode, String batinfo, Date start, Date end) {
		try {
			Map map = new HashMap();
			map.put("BAT_ID", batid);
			map.put("STATUS", status);
			map.put("ERRCODE", errcode);
			map.put("BAT_INFO", batinfo);
			map.put("STARTTIME_RESOVE", start);
			map.put("ENDTIME_RESOLVE", end);
			this.getSqlMapClient().update("server.updateBAT", map);
		} catch (Exception e) {
			logger.error("update bat error:\n" + Utils.printStackTrace(e));
		}
	}

	/**
	 * 获取某设备对应的字段数
	 */
	private int getFieldCount(String equipCode, int equipNum, String mineCode, String roomCode, String fieldType) {
		Map<String, Object> map = new HashMap();
		map.put("ORECODE", mineCode);
		map.put("ROOMCODE", roomCode);
		map.put("EQUIPCODE", equipCode);
		map.put("EQUIPNUM", equipNum);
		map.put("FIELDTYPE", fieldType);
		int i = -1;
		try {
			i = (Integer) getSqlMapClient().queryForObject("server.counttables", map);
		} catch (Exception e) {
			logger.error("database error:" + Utils.printStackTrace(e));
		}

		return i;
	}

	/**
	 * 处理文件名
	 */
	private void dealFileName(String name, FileData filedata) throws Exception {
		int ind = name.indexOf("_part_");
		if (ind != -1) {
			name = name.substring(ind + 6);
		}
		if ((name.lastIndexOf(FileData.SUFFIX) != -1)) {
			name = name.substring(0, name.lastIndexOf(FileData.SUFFIX));
		} else if (name.lastIndexOf(FileData.SUFFIX_DAT) != -1) {
			name = name.substring(0, name.lastIndexOf(FileData.SUFFIX_DAT));
		}
		String[] ss = name.split(FileData.FILENAME_SPLITSTR);
		if (ss.length != 8) {
			logger.error("file name error:" + name);
			throw new Exception("file name error,filename.split.length != 8");
		}
		if (("H".equals(ss[0])) || ("L".equals(ss[0]))) {
			filedata.fileType = ss[0];
		} else {
			throw new Exception("error filetype in file name:" + ss[0]);
		}
		filedata.province = ss[1];
		if (StringUtils.isEmpty(filedata.province)) {
			throw new Exception("文件名省份字段为空");
		}
		filedata.company = ss[2];
		if (StringUtils.isEmpty(filedata.company)) {
			throw new Exception("文件名集团字段为空");
		}
		filedata.mine = ss[3];
		if (StringUtils.isEmpty(filedata.mine)) {
			throw new Exception("文件名矿区字段为空");
		}
		filedata.room = ss[4];
		if (StringUtils.isEmpty(filedata.room)) {
			throw new Exception("文件名机房字段为空");
		}
		filedata.equipName = ss[5];
		if (StringUtils.isEmpty(filedata.equipName)) {
			throw new Exception("文件名设备名称字段为空");
		}
		String code = ss[6];
		if (code.indexOf("#") != -1) {
			code = code.substring(0, code.indexOf("#"));
			try {
				filedata.equipCode = Integer.parseInt(code);
			} catch (Exception e) {
				throw new Exception("error equipcode in file name:" + code);
			}
		} else {
			throw new Exception("error equipcode in file name:" + code);
		}

		try {
			if (ss[7].length() == 14) {
				filedata.collectDate = new SimpleDateFormat(FileData.FILENAME_DATE_FMT_SEC).parse(ss[7]);
			} else if (ss[7].length() == 12) {
				filedata.collectDate = new SimpleDateFormat(FileData.FILENAME_DATE_FMT_MIN).parse(ss[7]);
			} else if (ss[7].length() == 10) {
				filedata.collectDate = new SimpleDateFormat(FileData.FILENAME_DATE_FMT_HOUR).parse(ss[7]);
			} else {
				throw new Exception("error date in file name [" + ss[7] + "]");
			}
		} catch (ParseException e) {
			throw new Exception("parse error date in file name [" + ss[7] + "]");
		}
	}

	/**
	 * 验证已配置各目录是否符合规则
	 */
	private void pathCheck() {
		if (!pathExistsAndDir(getDataPath())) {
			throw new IllegalArgumentException("the param 'dataPath' not exists or not a directory");
		}
		if (!pathExistsAndDir(getHistPath())) {
			throw new IllegalArgumentException("the param 'histPath' not exists or not a directory");
		}
	}

	/**
	 * 验证单个路径是否存在
	 */
	public boolean pathExistsAndDir(String path) {
		File f = new File(path);
		if (!f.exists() || !f.isDirectory()) {
			return false;
		} else {
			return true;
		}
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getHistPath() {
		return histPath;
	}

	public void setHistPath(String histPath) {
		this.histPath = histPath;
	}

	public void setLvmPath(String lvmPath) {
		this.lvmPath = lvmPath;
	}

	public String getLvmPath() {
		return lvmPath;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public SqlMapClientTemplate getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int gethBatchNum() {
		return hBatchNum;
	}

	public void sethBatchNum(int hBatchNum) {
		this.hBatchNum = hBatchNum;
	}

	public int getlBatchNum() {
		return lBatchNum;
	}

	public void setlBatchNum(int lBatchNum) {
		this.lBatchNum = lBatchNum;
	}
}