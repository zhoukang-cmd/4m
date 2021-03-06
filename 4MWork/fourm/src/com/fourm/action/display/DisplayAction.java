package com.fourm.action.display;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Fault;
import com.fourm.entity.Field;
import com.fourm.entity.ShowDataView;
import com.fourm.service.display.DisplayService;
import com.fourm.service.fault.FaultService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;

/**
 * 实时数据
 */

@SuppressWarnings("serial")
public class DisplayAction extends BaseAction implements Runnable {

	/**
	 * 前台 实时数据
	 */
	private Fault sfault;
	private final String nav = "display";
	static FaultService faultService;
	static Equip currentSession;

	/**
	 * 
	 * @return
	 */
	public String goDisplay() {
		return "goDisplay";
	}

	/**
	 * //图片和参数
	 * 在实时数据展示页面，既有图片，又有实时显示的数据，这个方法就是获取实时数据和图片的，
	 * 实时数据以图片为背景，显示在图片上
	 * @return
	 */
	public String getExtList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip) getValueByKey("currentSession");
		DisplayService displayService = (DisplayService) getBeanById("displayService");

		List<Field> list = displayService.getDisplayField(currentSession.getEquipId());
		List<Field> extList = new ArrayList<Field>();

		if (list != null && list.size() > 0) {
			for (Field ext : list) {
				if (ext.getFieldDisplayX() != null && ext.getFieldDisplayY() != null
						&& ext.getFieldDisplayX().length() != 0 && ext.getFieldDisplayY().length() != 0) {
					extList.add(ext);
				} else {
					continue;
				}
			}
		}
		String imagePath = currentSession.getDisplayPic();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", "1");

		/*
		 * var htmlstr = ""; $.each(json.extList,function(i, ext){ htmlstr +=
		 * "<div id=\"ext"+ext.fieldNo+ext.fieldType+"\"><label>"+ext.
		 * fieldName+"：</label><input id=\"textext"+ext.fieldNo+ext.
		 * fieldType+"\" type=\"text\" size=\"4\" value=\"\"/></div>"; });
		 * if(json.extList.length > 0){
		 */
		jsonMap.put("extList", extList);
		jsonMap.put("imagePath", imagePath);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getExtList()", "实时数据得到设备图片和参数");
		return "json";
	}

	
	/**
	 * // 参数值，间隔5秒刷新一次
	 * $('body').everyTime('5s','A',showData,0,true);
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public String showData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		currentSession = (Equip) request.getSession().getAttribute("currentSession");
		DisplayService displayService = (DisplayService) getBeanById("displayService");

		List<Field> extList = displayService.getDisplayField(currentSession.getEquipId());// 图片上的参数
		for (Field ext : extList) {
			System.out.println(ext.getFieldId() + ext.getFieldType() + ext.getFieldNo() + ext.getFieldName()
					+ ext.getEquipId() + ext.getFieldLimitHigh() + ext.getFieldLimitHigh());
		}
		List<ShowDataView> dataList = new ArrayList<ShowDataView>();
		String limit;
		String table;
		String value;
		String dataInfo = null;

		HashMap tmp = new HashMap();
		String tableName = "T_L_" + currentSession.getProvCode() + "_" + currentSession.getCompCode() + "_"
				+ currentSession.getMineCode() + "_" + currentSession.getRommCode() + "_"
				+ currentSession.getEquipCode() + "_" + currentSession.getEquipNum();
		tmp.put("TABLE", tableName);
		System.out.println(tableName);
		if (extList != null && extList.size() > 0) {
			for (Field ext : extList) {
				ShowDataView sdv = new ShowDataView();

				sdv.setFieldType(ext.getFieldType());
				sdv.setOrderNo(ext.getFieldNo());
				tmp.put("COLUMN", "VALUE" + ext.getFieldNo());
				value = displayService.getDisplayValue(tmp);
				String displayTime = displayService.getDisplayTime(tmp);
				// 四舍五入，保留两位小数

				DecimalFormat decimalFormat = new DecimalFormat("##0.00 ");
				String dfValue = decimalFormat.format(Float.parseFloat(value));
				System.out.println("VALUE" + ext.getFieldNo() + "=====" + value + "===" + dfValue);
				sdv.setExtString(dfValue);
				if ("风门状态".equals(ext.getFieldName())) {
					if (Float.parseFloat(value) <= 0) {
						sdv.setExtString("关闭");
					} else if (Float.parseFloat(value) >= 0) {
						sdv.setExtString("开启");
					}
				}
				if ("风量".equals(ext.getFieldName())) {
					if (Float.parseFloat(value) <= 0) {
						sdv.setExtString("0.00");
					}
				}
				limit = ext.getFieldLimitHigh();
				sdv.setAlarmFlag(false);// 默认数据在范围之内
				boolean highorlow = false;
				String level = null;// 程度
				if (StringUtils.isNotBlank(ext.getFieldLimitHigh()) && StringUtils.isNotBlank(ext.getFieldLimitLow())) {

					if (StringUtils.isNotBlank(ext.getFieldLimitLow())) {
						if (Float.parseFloat(ext.getFieldLimitLow()) > Float.parseFloat(value)) {
							sdv.setAlarmFlag(true);
							highorlow = true;
							float v = Float.parseFloat(value);
							float low = Float.parseFloat(ext.getFieldLimitLow());
							float flag = (low - v) / low;
							if (flag < 0.3) {
								level = "轻度";
							} else if (flag < 0.5) {
								level = "中度";
							} else {
								level = "重度";
							}

						}
					}
					if (StringUtils.isNotBlank(ext.getFieldLimitHigh())) {
						if (Float.parseFloat(ext.getFieldLimitHigh()) < Float.parseFloat(value)) {
							sdv.setAlarmFlag(true);
							highorlow = false;

							float v = Float.parseFloat(value);
							float high = Float.parseFloat(ext.getFieldLimitHigh());
							float flag = (v - high) / high;
							if (flag < 0.3) {
								level = "轻度";
							} else if (flag < 0.5) {
								level = "中度";
							} else {
								level = "重度";
							}
						}
					}
				}

				if (sdv.isAlarmFlag()) {

					sfault = new Fault();
					sfault.setFaultCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					sfault.setFaultField(ext.getFieldDesc());
					sfault.setFaultLevel(level);
					sfault.setFaultPosition(ext.getTypeName());
					sfault.setFaultTime(displayTime);
					sfault.setFaultTrend("faultTrend");

					if (highorlow) {
						sfault.setFaultTrend(value + "低于最小值" + ext.getFieldLimitLow());
					} else {
						sfault.setFaultTrend(value + "高于最大值" + ext.getFieldLimitHigh());
					}

					faultService = (FaultService) getBeanById("faultService");
					sfault.setEquipId(currentSession.getEquipId());
					new Thread(this).start();

				}

				dataList.add(sdv);
			}
			String dataTimeStr = displayService.getDisplayTime(tmp);
			Date dataTime = null;
			try {
				dataTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (dataTime != null) {
				long delta = new Date().getTime() - dataTime.getTime();
				if (delta / 1000 / 60 > 20) { // 考虑不同机器的时间差异，设置20分钟以前的数据为旧数据
					dataInfo = "数据时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dataTime);
				} else {
					dataInfo = "数据是最新的";
				}
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", "1");
		jsonMap.put("extList", dataList);
		if (dataInfo != null) {
			jsonMap.put("info", dataInfo);
		} else {
			jsonMap.put("info", "");
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getExtList()", "实时数据参数值");
		return "json";
	}

	// 自动添加故障

	public void addFault(Fault fault) {

		Map map = new HashMap<String, Object>();
		map.put("equipId", currentSession.getEquipId());
		map.put("faultField", fault.getFaultField());
		map.put("faultPosition", fault.getFaultPosition());

		Fault selFault = faultService.getFaultByTypeTime(map);

		if (selFault != null) {
			String ftime = selFault.getFaultTime();
			String htime = fault.getFaultTime();
			Date fdate = DateUtil.formatToDate(ftime, "yyyy-MM-dd HH:mm:ss");
			Date hdate = DateUtil.formatToDate(htime, "yyyy-MM-dd HH:mm:ss");

			// 相同类型 最新故障的时间的下一小时
			Date flagdate = DateUtil.getNextDate(fdate, 3600);

			if (hdate.getTime() < flagdate.getTime()) {
				return;
			}
		}

		faultService.addFault(fault);

	}

	// 全屏页面
	public String goFullScreen() {
		return "goFullScreen";
	}

	public String getNav() {
		return nav;
	}

	public void run() {

		addFault(sfault);
	}

}
