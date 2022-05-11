package com.fourm.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fourm.common.Utils;

public class ConfigFrame extends JFrame implements ActionListener{
	private Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -1499602504621571335L;
	private final String sourceLabelTxt = "文件目录";
	private final String histLabelTxt = "备份目录";
	private final String zipLabelTxt = "压缩目录";
	private final String successLabelTxt = "成功目录";
	private final String failLabelTxt = "失败目录";
	private final String clientidLabelTxt = "客户端ID";
	private final String serverLabelTxt = "上传路径";
	//private final String startLabelTxt = "开始日期";
	private final String spanLabelTxt = "任务执行间隔(秒)";
	private final String ftpHostLabelTxt = "FTP地址";
	private final String ftpPortLabelTxt = "FTP端口";
	private final String ftpUserLabelTxt = "FTP用户名";
	private final String ftpPasslabelTxt = "FTP密码";
	private final String tcpHostLabelTxt = "TCP地址";
	private final String tcpPortLabelTxt = "TCP端口";
	private Properties properties;
	String configFilePath;
	JFileChooser chooser;
	JPanel jContentPane;
	JPanel errorPanel;
	JPanel formPanel;
	JPanel buttonPanel;
	JLabel errorLabel ;
	
	JButton okButton;
	JButton cancelButton;
	JLabel sourceLabel;
	JTextField sourceTxt;
	JButton sourceBtn;
	
	JLabel histLabel;
	JTextField histTxt;
	JButton histBtn;
	
	JLabel zipLabel;
	JTextField zipTxt;
	JButton zipBtn;
	
	JLabel successLabel;
	JTextField successTxt;
	JButton successBtn;
	
	JLabel failLabel;
	JTextField failTxt;
	JButton failBtn;
	
	JLabel idLabel;
	JTextField idTxt;
	
	JLabel serverLabel;
	JTextField serverTxt;
	
	//JLabel startLabel;
	//JTextField startTxt;
	
	JLabel spanLabel;
	JTextField spanTxt;
	
	//JComboBox spanUnit;
	
	JLabel ftpHostLabel;
	JTextField ftpHostTxt;
	JLabel ftpPortLabel;
	JTextField ftpPortTxt;
	JLabel ftpUserLabel;
	JTextField ftpUserTxt;
	JLabel ftpPassLabel;
	JTextField ftpPassTxt;
	
	JLabel tcpHostLabel;
	JTextField tcpHostTxt;
	JLabel tcpPortLabel;
	JTextField tcpPortTxt;
	public ConfigFrame() throws IOException {
		super();
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("conf/sysconfig.properties"));
			URL url = getClass().getClassLoader().getResource("conf/sysconfig.properties");
			configFilePath = url.getPath();
		}
		catch(Exception e){
			//e.printStackTrace();
			logger.error("找不到配置文件");
		}
		
		setTitle("fourm client config");
		setBounds(500,150,400,500);
		setContentPane(getContentPane());
		setResizable(false);
	}
	
	public JPanel getContentPane() {
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(getErrorPanel());
		jContentPane.add(getFormPanel());
		return jContentPane;
	}
	private JPanel getErrorPanel() {
		errorPanel = new JPanel();
		errorPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		errorPanel.setBounds(10, 10, 370, 45);
		errorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		errorLabel = new JLabel();
		errorLabel.setText("用于配置客户端系统各个目录信息");
		Font f = new Font("font",Font.CENTER_BASELINE,15);
		errorLabel.setFont(f);
		errorLabel.setBackground(Color.white);
		errorPanel.add(errorLabel);
//		errorPanel.setVisible(false);
		return errorPanel;
	}
	private JPanel getFormPanel() {
		formPanel = new JPanel();
		formPanel.setBounds(10, 60, 370, 400);
		formPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formPanel.setLayout(new FlowLayout());
		createId(formPanel, 10, 10);
		createServer(formPanel, 10, 10);
		createSource(formPanel, 10, 10);
		createHist(formPanel, 10, 10);
		createZip(formPanel, 10, 10);
		createSuccess(formPanel, 10, 10);
		createFail(formPanel, 10, 10);
		createSpan(formPanel, 10, 10);
		//createStart(formPanel, 10, 10);
		createFtp(formPanel, 10, 10);
		createTcp(formPanel, 10, 10);
		createButton(formPanel, 10, 10);
		
		
		return formPanel;
	}
	private void createButton(JPanel panel,int x,int y) {
		okButton = new JButton();
		okButton.setText("确定");
		okButton.setActionCommand("ok");
		okButton.addActionListener(this);
		panel.add(okButton);
		
		cancelButton = new JButton();
		cancelButton.setText("取消");
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);
		panel.add(cancelButton);
	}
	private void createSource(JPanel panel,int x,int y) {
		sourceLabel = new JLabel();
		sourceLabel.setBounds(x, y, 20, 20);
		sourceLabel.setText(sourceLabelTxt);
		panel.add(sourceLabel);
		
		sourceTxt = new JTextField();
		sourceTxt.setBounds(x,y+20,100,10);
		sourceTxt.setPreferredSize(new Dimension(200,28));
		sourceTxt.setText(properties.getProperty(Utils.CLIENT_SOURCE));
		sourceTxt.setEditable(false);
		panel.add(sourceTxt);
		
		sourceBtn = new JButton();
		sourceBtn.setBounds(x, y+230, 40,28);
		sourceBtn.setText("选择");
		sourceBtn.setActionCommand("source");
		sourceBtn.addActionListener(this);
		panel.add(sourceBtn);
		
		
	}
	
	private void createHist(JPanel panel,int x,int y) {
		histLabel = new JLabel();
		histLabel.setBounds(x, y, 20, 20);
		histLabel.setText(histLabelTxt);
		panel.add(histLabel);
		
		histTxt = new JTextField();
		histTxt.setBounds(x,y+20,100,10);
		histTxt.setPreferredSize(new Dimension(200,28));
		histTxt.setText(properties.getProperty(Utils.CLIENT_HIST));
		histTxt.setEditable(false);
		panel.add(histTxt);
		
		histBtn = new JButton();
		histBtn.setBounds(x, y+230, 40,28);
		histBtn.setText("选择");
		histBtn.setActionCommand("hist");
		histBtn.addActionListener(this);
		panel.add(histBtn);
		
		
	}
	private void createZip(JPanel panel,int x,int y) {
		zipLabel = new JLabel();
		zipLabel.setBounds(x, y, 20, 20);
		zipLabel.setText(zipLabelTxt);
		panel.add(zipLabel);
		
		zipTxt = new JTextField();
		zipTxt.setBounds(x,y+20,100,10);
		zipTxt.setPreferredSize(new Dimension(200,28));
		zipTxt.setText(properties.getProperty(Utils.CLIENT_ZIP));
		zipTxt.setEditable(false);
		panel.add(zipTxt);
		
		zipBtn = new JButton();
		zipBtn.setBounds(x, y+230, 40,28);
		zipBtn.setText("选择");
		zipBtn.setActionCommand("zip");
		zipBtn.addActionListener(this);
		panel.add(zipBtn);
		
		
	}
	private void createSuccess(JPanel panel,int x,int y) {
		successLabel = new JLabel();
		successLabel.setBounds(x, y, 20, 20);
		successLabel.setText(successLabelTxt);
		panel.add(successLabel);
		
		successTxt = new JTextField();
		successTxt.setBounds(x,y+20,100,10);
		successTxt.setPreferredSize(new Dimension(200,28));
		successTxt.setText(properties.getProperty(Utils.CLIENT_SUCCESS));
		successTxt.setEditable(false);
		panel.add(successTxt);
		
		successBtn = new JButton();
		successBtn.setBounds(x, y+230, 40,28);
		successBtn.setText("选择");
		successBtn.setActionCommand("success");
		successBtn.addActionListener(this);
		panel.add(successBtn);
		
		
	}
	private void createFail(JPanel panel,int x,int y) {
		failLabel = new JLabel();
		failLabel.setBounds(x, y, 20, 20);
		failLabel.setText(failLabelTxt);
		panel.add(failLabel);
		
		failTxt = new JTextField();
		failTxt.setBounds(x,y+20,100,10);
		failTxt.setPreferredSize(new Dimension(200,28));
		failTxt.setText(properties.getProperty(Utils.CLIENT_FAIL));
		failTxt.setEditable(false);
		panel.add(failTxt);
		
		failBtn = new JButton();
		failBtn.setBounds(x, y+230, 40,28);
		failBtn.setText("选择");
		failBtn.setActionCommand("fail");
		failBtn.addActionListener(this);
		panel.add(failBtn);
	}
	private void createId(JPanel panel,int x,int y) {
		idLabel = new JLabel();
		idLabel.setBounds(x, y, 20, 20);
		idLabel.setText(clientidLabelTxt);
		panel.add(idLabel);
		
		idTxt = new JTextField();
		idTxt.setBounds(x,y+20,100,10);
		idTxt.setPreferredSize(new Dimension(260,28));
		idTxt.setText(properties.getProperty(Utils.CLIENT_ID));
		panel.add(idTxt);
	}
	private void createServer(JPanel panel,int x,int y) {
		serverLabel = new JLabel();
		serverLabel.setBounds(x, y, 20, 20);
		serverLabel.setText(serverLabelTxt);
		panel.add(serverLabel);
		
		serverTxt = new JTextField();
		serverTxt.setBounds(x,y+20,100,10);
		serverTxt.setPreferredSize(new Dimension(260,28));
		serverTxt.setText(properties.getProperty(Utils.CLIENT_SERVERPATH));
		panel.add(serverTxt);
	}
	/*private void createStart(JPanel panel,int x,int y) {
		startLabel = new JLabel();
		startLabel.setBounds(x, y, 20, 20);
		startLabel.setText("");
		startLabel.setPreferredSize(new Dimension(140,28));
		panel.add(startLabel);
		
		startTxt = new JTextField();
		startTxt.setBounds(x,y+20,100,10);
		startTxt.setPreferredSize(new Dimension(130,28));
		startTxt.setText(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		panel.add(startTxt);
	}*/
	private void createSpan(JPanel panel,int x,int y) {
		spanLabel = new JLabel();
		spanLabel.setBounds(x, y, 20, 20);
		spanLabel.setText(spanLabelTxt);
		panel.add(spanLabel);
		
		spanTxt = new JTextField();
		spanTxt.setBounds(x,y+20,100,10);
		spanTxt.setPreferredSize(new Dimension(220,28));
		spanTxt.setText(properties.getProperty(Utils.CLIENT_CRON).split(" ")[0].split("/")[1]); 
		panel.add(spanTxt);
	}
	private void createFtp(JPanel panel,int x,int y) {
		//
		ftpHostLabel = new JLabel();
		ftpHostLabel.setBounds(x, y, 20, 20);
		ftpHostLabel.setText(ftpHostLabelTxt);
		panel.add(ftpHostLabel);
		
		ftpHostTxt = new JTextField();
		ftpHostTxt.setBounds(x,y+20,100,10);
		ftpHostTxt.setPreferredSize(new Dimension(160,28));
		ftpHostTxt.setText(properties.getProperty(Utils.CLIENT_FTPHOST));
		panel.add(ftpHostTxt);
		//
		ftpPortLabel = new JLabel();
		ftpPortLabel.setBounds(x, y, 20, 20);
		ftpPortLabel.setText(ftpPortLabelTxt);
		panel.add(ftpPortLabel);
		
		ftpPortTxt = new JTextField();
		ftpPortTxt.setBounds(x,y+20,100,10);
		ftpPortTxt.setPreferredSize(new Dimension(50,28));
		ftpPortTxt.setText(properties.getProperty(Utils.CLIENT_FTPPORT));
		panel.add(ftpPortTxt);
		//
		ftpUserLabel = new JLabel();
		ftpUserLabel.setBounds(x, y, 20, 20);
		ftpUserLabel.setText(ftpUserLabelTxt);
		panel.add(ftpUserLabel);
		
		ftpUserTxt = new JTextField();
		ftpUserTxt.setBounds(x,y+20,100,10);
		ftpUserTxt.setPreferredSize(new Dimension(100,28));
		ftpUserTxt.setText(properties.getProperty(Utils.CLIENT_FTPUSERNAME));
		panel.add(ftpUserTxt);
		
		ftpPassLabel = new JLabel();
		ftpPassLabel.setBounds(x, y, 20, 20);
		ftpPassLabel.setText(ftpPasslabelTxt);
		panel.add(ftpPassLabel);
		
		ftpPassTxt = new JTextField();
		ftpPassTxt.setBounds(x,y+20,100,10);
		ftpPassTxt.setPreferredSize(new Dimension(100,28));
		ftpPassTxt.setText(properties.getProperty(Utils.CLIENT_FTPPASSWORD));
		panel.add(ftpPassTxt);
	}
	private void createTcp(JPanel panel,int x,int y) {
		//
		tcpHostLabel = new JLabel();
		tcpHostLabel.setBounds(x, y, 20, 20);
		tcpHostLabel.setText(tcpHostLabelTxt);
		panel.add(tcpHostLabel);
		
		tcpHostTxt = new JTextField();
		tcpHostTxt.setBounds(x,y+20,100,10);
		tcpHostTxt.setPreferredSize(new Dimension(160,28));
		tcpHostTxt.setText(properties.getProperty(Utils.CLIENT_TCPHOST));
		panel.add(tcpHostTxt);
		//
		tcpPortLabel = new JLabel();
		tcpPortLabel.setBounds(x, y, 20, 20);
		tcpPortLabel.setText(tcpPortLabelTxt);
		panel.add(tcpPortLabel);
		
		tcpPortTxt = new JTextField();
		tcpPortTxt.setBounds(x,y+20,100,10);
		tcpPortTxt.setPreferredSize(new Dimension(50,28));
		tcpPortTxt.setText(properties.getProperty(Utils.CLIENT_TCPPORT));
		panel.add(tcpPortTxt);
	}
	
	private void validatePrams() {
		String source = sourceTxt.getText().trim();
		if("".equals(source)) {
			throw new IllegalArgumentException(sourceLabelTxt + "不能为空");
		}
		String hist = histTxt.getText().trim();
		if("".equals(hist)) {
			throw new IllegalArgumentException(histLabelTxt + "不能为空");
		}
		String zip = zipTxt.getText().trim();
		if("".equals(zip)) {
			throw new IllegalArgumentException(zipLabelTxt + "不能为空");
		}
		String success = successTxt.getText().trim();
		if("".equals(success)) {
			throw new IllegalArgumentException(successLabelTxt + "不能为空");
		}
		String fail = failTxt.getText().trim();
		if("".equals(fail)) {
			throw new IllegalArgumentException(failLabelTxt + "不能为空");
		}
		String server = serverTxt.getText().trim();
		if("".equals(server)) {
			throw new IllegalArgumentException(serverLabelTxt + "不能为空");
		} else {
			server = server.replace("\\", "/");
		}
		/*String start = startTxt.getText().trim();
		if("".equals(start)) {
			throw new IllegalArgumentException(startLabelTxt + "不能为空");
		}*/
		String span = spanTxt.getText().trim();
		if("".equals(span)) {
			throw new IllegalArgumentException(spanLabelTxt + "不能为空");
		}else {
			try {
				int ispan = Integer.parseInt(span);
				if(ispan < 1 || ispan > 60) {
					throw new IllegalArgumentException(spanLabelTxt + "须在1-60之间");
				}
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException(spanLabelTxt + "必须为数字");
			}
		}
		String clientid = idTxt.getText().trim();
		if("".equals(clientid)) {
			throw new IllegalArgumentException(clientidLabelTxt + "不能为空");
		}
		String ftpHost = ftpHostTxt.getText().trim();
		if("".equals(ftpHost)) {
			throw new IllegalArgumentException(ftpHostLabelTxt + "不能为空");
		}
		String ftpPort = ftpPortTxt.getText().trim();
		if("".equals(ftpPort)) {
			throw new IllegalArgumentException(ftpPortLabelTxt + "不能为空");
		}
		String ftpUserName = ftpUserTxt.getText().trim();
		if("".equals(ftpUserName)) {
			throw new IllegalArgumentException(ftpUserLabelTxt + "不能为空");
		}
		String ftpPassword = ftpPassTxt.getText().trim();
		if("".equals(ftpPassword)) {
			throw new IllegalArgumentException(ftpPasslabelTxt + "不能为空");
		}
		String tcpHost = tcpHostTxt.getText().trim();
		if("".equals(tcpHost)) {
			throw new IllegalArgumentException(tcpHostLabelTxt + "不能为空");
		}
		String tcpPort = tcpPortTxt.getText().trim();
		if("".equals(tcpPort)) {
			throw new IllegalArgumentException(tcpPortLabelTxt + "不能为空");
		}
	}
//	private JTextField getTextField() {
//		
//	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			
			public void run() {
				ConfigFrame c;
				try {
					c = new ConfigFrame();
					c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					c.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		});
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(chooser == null) {
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		
		if("ok".equals(e.getActionCommand())) {
			try {
				validatePrams();
			} catch(IllegalArgumentException ie) {
				JOptionPane.showMessageDialog(this, ie.getMessage());
				return;
			}
			int r = JOptionPane.showConfirmDialog(this, "请确认配置信息，点击确认，程序退出并保存配置信息");
			if(r == JOptionPane.OK_OPTION) {
				properties.setProperty(Utils.CLIENT_ID, idTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_SERVERPATH, serverTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_SOURCE, sourceTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_HIST, histTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_ZIP, zipTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_SUCCESS, successTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_FAIL, failTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_FTPHOST, ftpHostTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_FTPPORT, ftpPortTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_FTPUSERNAME, ftpUserTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_FTPPASSWORD, ftpPassTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_TCPHOST, tcpHostTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_TCPPORT, tcpPortTxt.getText().trim());
				String span = spanTxt.getText().trim();
				String cron = "0/" + span + " * * * * ? *";
				
				properties.setProperty(Utils.CLIENT_CRON, cron);
				try {
					File f = new File("conf/sysconfig.properties");
					FileOutputStream out = new FileOutputStream(f);
					properties.store(out , "");
					out.flush();
					out.close();
				} catch (IOException e1) {
					logger.error(Utils.printStackTrace(e1));
					
				}
				JOptionPane.showMessageDialog(this, "参数保存成功，请重新启动服务生效");
				this.dispose();
			}
		} else if("cancel".equals(e.getActionCommand())) {
			this.dispose();
//			System.exit(0);
		} else {
			int ret = chooser.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				chooser.setCurrentDirectory(chooser.getSelectedFile());
				String path = chooser.getSelectedFile().getAbsolutePath();
				path = path.replace("\\", "/");
				String command = e.getActionCommand();
				if("source".equals(command)) {
					sourceTxt.setText(path);
				} else if("hist".equals(command)) {
					histTxt.setText(path);
				} else if("zip".equals(command)) {
					zipTxt.setText(path);
				} else if("success".equals(command)) {
					successTxt.setText(path);
				} else if("fail".equals(command)) {
					failTxt.setText(path);
				} 
			}
		}
	}
}
