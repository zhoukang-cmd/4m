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
import javax.swing.JComboBox;
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
	private final String clientidLabelTxt = "客户端ID";
	private final String spanLabelTxt = "任务执行间隔(秒)";
	private final String serverLabelTxt = "服务器列表";
	private final String ftpHostLabelTxt = "FTP地址";
	private final String ftpPortLabelTxt = "FTP端口";
	private final String ftpUserLabelTxt = "FTP用户名";
	private final String ftpPasslabelTxt = "FTP密码";
	private final String ftpPathLabelTxt = "FTP路径";
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
	
	JButton addButton;
	JButton delButton;
	JButton okButton;
	JButton cancelButton;
	
	JLabel sourceLabel;
	JTextField sourceTxt;
	JButton sourceBtn;
	
	JLabel idLabel;
	JTextField idTxt;
	
	JLabel spanLabel;
	JTextField spanTxt;	
	
	JLabel serverLabel;
	JComboBox serverList;
	String lastSelecedServer;
	
	JLabel ftpHostLabel;
	JTextField ftpHostTxt;
	JLabel ftpPortLabel;
	JTextField ftpPortTxt;
	JLabel ftpUserLabel;
	JTextField ftpUserTxt;
	JLabel ftpPassLabel;
	JTextField ftpPassTxt;
	JLabel ftpPathLabel;
	JTextField ftpPathTxt;
	
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
		
		setTitle("fourm forward config");
		setBounds(500,150,400,430);
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
		errorLabel.setText("用于配置转发数据目录和每个目标服务器信息");
		Font f = new Font("font",Font.CENTER_BASELINE,15);
		errorLabel.setFont(f);
		errorLabel.setBackground(Color.white);
		errorPanel.add(errorLabel);
		return errorPanel;
	}
	
	private JPanel getFormPanel() {
		formPanel = new JPanel();
		formPanel.setBounds(10, 60, 370, 330);
		formPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formPanel.setLayout(new FlowLayout());
		
		createId(formPanel, 10, 10);
		createSource(formPanel, 10, 10);
		createSpan(formPanel, 10, 10);
		createServer(formPanel, 10, 10);
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
	
	private void createServer(JPanel panel,int x,int y) {
		serverLabel = new JLabel();
		serverLabel.setBounds(x, y, 20, 20);
		serverLabel.setText(serverLabelTxt);
		panel.add(serverLabel);
		
		serverList = new JComboBox();
		serverList.setBounds(x, y+20, 100, 10);
		serverList.setPreferredSize(new Dimension(250,28));
		String[] servers = properties.getProperty(Utils.CLIENT_SERVERS).trim().split(",");
		for (String server: servers) {
			serverList.addItem(server);
		}
		lastSelecedServer = servers[0];
		serverList.setActionCommand("change");
		serverList.addActionListener(this);
		panel.add(serverList);
		
		addButton = new JButton();
		addButton.setText("添加");
		addButton.setActionCommand("add");
		addButton.addActionListener(this);
		//panel.add(addButton);
		
		delButton = new JButton();
		delButton.setText("删除");
		delButton.setActionCommand("del");
		delButton.addActionListener(this);
		//panel.add(delButton);
	}
	
	private void serverChange() {
		//保存上一个服务器的设置到内存
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_FTPHOST, ftpHostTxt.getText());
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_FTPPORT, ftpPortTxt.getText());
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_FTPUSERNAME, ftpUserTxt.getText());
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_FTPPASSWORD, ftpPassTxt.getText());
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_FTPPATH, ftpPathTxt.getText());
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_TCPHOST, tcpHostTxt.getText());
		properties.setProperty(lastSelecedServer + "." + Utils.CLIENT_TCPPORT, tcpPortTxt.getText());
		//读取当前服务器的设置
		ftpHostTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPHOST));
		ftpPortTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPPORT));
		ftpUserTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPUSERNAME));
		ftpPassTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPPASSWORD));
		ftpPathTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPPATH));
		tcpHostTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_TCPHOST));
		tcpPortTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_TCPPORT));
		lastSelecedServer = serverList.getSelectedItem().toString();
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
		ftpHostTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPHOST));
		panel.add(ftpHostTxt);
		//
		ftpPortLabel = new JLabel();
		ftpPortLabel.setBounds(x, y, 20, 20);
		ftpPortLabel.setText(ftpPortLabelTxt);
		panel.add(ftpPortLabel);
		
		ftpPortTxt = new JTextField();
		ftpPortTxt.setBounds(x,y+20,100,10);
		ftpPortTxt.setPreferredSize(new Dimension(50,28));
		ftpPortTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPPORT));
		panel.add(ftpPortTxt);
		//
		ftpUserLabel = new JLabel();
		ftpUserLabel.setBounds(x, y, 20, 20);
		ftpUserLabel.setText(ftpUserLabelTxt);
		panel.add(ftpUserLabel);
		
		ftpUserTxt = new JTextField();
		ftpUserTxt.setBounds(x,y+20,100,10);
		ftpUserTxt.setPreferredSize(new Dimension(100,28));
		ftpUserTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPUSERNAME));
		panel.add(ftpUserTxt);
		
		ftpPassLabel = new JLabel();
		ftpPassLabel.setBounds(x, y, 20, 20);
		ftpPassLabel.setText(ftpPasslabelTxt);
		panel.add(ftpPassLabel);
		
		ftpPassTxt = new JTextField();
		ftpPassTxt.setBounds(x,y+20,100,10);
		ftpPassTxt.setPreferredSize(new Dimension(100,28));
		ftpPassTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPPASSWORD));
		panel.add(ftpPassTxt);

		ftpPathLabel = new JLabel();
		ftpPathLabel.setBounds(x, y, 20, 20);
		ftpPathLabel.setText(ftpPathLabelTxt);
		panel.add(ftpPathLabel);
		
		ftpPathTxt = new JTextField();
		ftpPathTxt.setBounds(x,y+20,100,10);
		ftpPathTxt.setPreferredSize(new Dimension(260,28));
		ftpPathTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_FTPPATH));
		panel.add(ftpPathTxt);
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
		tcpHostTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_TCPHOST));
		panel.add(tcpHostTxt);
		//
		tcpPortLabel = new JLabel();
		tcpPortLabel.setBounds(x, y, 20, 20);
		tcpPortLabel.setText(tcpPortLabelTxt);
		panel.add(tcpPortLabel);
		
		tcpPortTxt = new JTextField();
		tcpPortTxt.setBounds(x,y+20,100,10);
		tcpPortTxt.setPreferredSize(new Dimension(50,28));
		tcpPortTxt.setText(properties.getProperty(serverList.getSelectedItem() + "." + Utils.CLIENT_TCPPORT));
		panel.add(tcpPortTxt);
	}
	
	private void validatePrams() {
		String source = sourceTxt.getText().trim();
		if("".equals(source)) {
			throw new IllegalArgumentException(sourceLabelTxt + "不能为空");
		}
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
		String ftpPath = ftpPathTxt.getText().trim();
		if("".equals(ftpPath)) {
			throw new IllegalArgumentException(ftpPathLabelTxt + "不能为空");
		} else {
			ftpPath = ftpPath.replace("\\", "/");
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
			serverChange();
			
			int r = JOptionPane.showConfirmDialog(this, "请确认配置信息，点击确认，程序退出并保存配置信息");
			if(r == JOptionPane.OK_OPTION) {
				properties.setProperty(Utils.CLIENT_ID, idTxt.getText().trim());
				properties.setProperty(Utils.CLIENT_SOURCE, sourceTxt.getText().trim());
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
		} else if("change".equals(e.getActionCommand())) {
			try {
				validatePrams();
			} catch(IllegalArgumentException ie) {
				JOptionPane.showMessageDialog(this, ie.getMessage());
				serverList.setSelectedItem(lastSelecedServer);
				return;
			}
			serverChange();
		} else if("add".equals(e.getActionCommand())) {
			//TODO Complete this function if necessary.
		} else if("del".equals(e.getActionCommand())) {
			//TODO Complete this function if necessary.
		} else {
			int ret = chooser.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				chooser.setCurrentDirectory(chooser.getSelectedFile());
				String path = chooser.getSelectedFile().getAbsolutePath();
				path = path.replace("\\", "/");
				String command = e.getActionCommand();
				if("source".equals(command)) {
					sourceTxt.setText(path);
				} 
			}
		}
	}
}
