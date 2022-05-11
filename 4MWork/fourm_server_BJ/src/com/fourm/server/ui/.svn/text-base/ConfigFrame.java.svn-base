package com.fourm.server.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class ConfigFrame extends JFrame implements ActionListener {
	private Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -1466207123758887704L;
	private Properties properties;
	private JPanel jContentPane = null;
	private JPanel formPanel = null;
	JFileChooser chooser;
	//数据库
	private JLabel dbNameLabel;
	private JTextField dbNameTxt;
	private JLabel dbHostLabel;
	private JTextField dbHostTxt;
	private JLabel dbPortLabel;
	private JTextField dbPortTxt;
	private JLabel dbUserLabel;
	private JTextField dbUserTxt;
	private JLabel dbPassLabel;
	private JTextField dbPassTxt;
	private JLabel dataLabel;
	private JTextField dataTxt;
	private JButton dataBtn;
	private JLabel histLabel;
	private JTextField histTxt;
	private JButton histBtn;
	private JLabel lvmLabel;
	private JTextField lvmTxt;
	private JButton lvmBtn;
	
	JLabel spanLabel;
	JTextField spanTxt;

	JButton okButton;
	JButton cancelButton;
	
	public ConfigFrame() throws IOException {
		super();
		properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("conf/sysconfig.properties"));
		setTitle("fourm server config");
		setBounds(500,150,400,320);
		setContentPane(getContentPane());
		setResizable(false);
	}
	
	public JPanel getContentPane() {
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(getFormPanel());
		return jContentPane;
	}
	
	public JPanel getFormPanel() {
		formPanel = new JPanel();
		formPanel.setBounds(10, 10, 370, 270);
		formPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		formPanel.setLayout(new FlowLayout());
		
		createData(formPanel, 10, 10);
		createHist(formPanel, 10, 10);
		createLvm(formPanel, 10, 10);
		createSpan(formPanel, 10, 10);
		createDb(formPanel, 10, 10);
		createButton(formPanel, 10, 10);
		return formPanel;
	}
	
	private void createDb(JPanel panel,int x,int y) {
		
		//初始化数据库URL和部分连接参数
		String dbUrl = properties.getProperty(Utils.DBURL);
		String dbHost = new String();
		String dbPort = new String();
		String dbName = new String();
		
		//从数据库URL中解析出参数
		Matcher addrMatcher = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+").matcher(dbUrl);
		if (addrMatcher.find()){
			dbHost = addrMatcher.group().split(":")[0];
			dbPort = addrMatcher.group().split(":")[1];
		}
		Matcher nameMatcher = Pattern.compile("database=\\w+").matcher(dbUrl);
		if (nameMatcher.find()){
			dbName = nameMatcher.group().split("=")[1];
		}
		
		dbHostLabel = new JLabel();
		dbHostLabel.setBounds(x, y, 20, 20);
		dbHostLabel.setText("数据库地址");
		panel.add(dbHostLabel);
		
		dbHostTxt = new JTextField();
		dbHostTxt.setBounds(x,y+20,100,10);
		dbHostTxt.setPreferredSize(new Dimension(120,28));
		dbHostTxt.setText(dbHost);
		panel.add(dbHostTxt);
		//
		dbPortLabel = new JLabel();
		dbPortLabel.setBounds(x, y, 20, 20);
		dbPortLabel.setText("数据库端口");
		panel.add(dbPortLabel);
		
		dbPortTxt = new JTextField();
		dbPortTxt.setBounds(x,y+20,100,10);
		dbPortTxt.setPreferredSize(new Dimension(50,28));
		dbPortTxt.setText(dbPort);
		panel.add(dbPortTxt);
		//
		dbUserLabel = new JLabel();
		dbUserLabel.setBounds(x, y, 20, 20);
		dbUserLabel.setText("数据库用户名");
		panel.add(dbUserLabel);
		
		dbUserTxt = new JTextField();
		dbUserTxt.setBounds(x,y+20,100,10);
		dbUserTxt.setPreferredSize(new Dimension(80,28));
		dbUserTxt.setText(properties.getProperty(Utils.DBUSER));
		panel.add(dbUserTxt);
		
		dbPassLabel = new JLabel();
		dbPassLabel.setBounds(x, y, 20, 20);
		dbPassLabel.setText("数据库密码");
		panel.add(dbPassLabel);
		
		dbPassTxt = new JTextField();
		dbPassTxt.setBounds(x,y+20,100,10);
		dbPassTxt.setPreferredSize(new Dimension(80,28));
		dbPassTxt.setText(properties.getProperty(Utils.DBPASS));
		panel.add(dbPassTxt);
		
		dbNameLabel = new JLabel();
		dbNameLabel.setBounds(x, y, 20, 20);
		dbNameLabel.setText("数据库名称");
		panel.add(dbNameLabel);
		
		dbNameTxt = new JTextField();
		dbNameTxt.setBounds(x,y+20,100,10);
		dbNameTxt.setPreferredSize(new Dimension(240,28));
		dbNameTxt.setText(dbName);
		panel.add(dbNameTxt);
	}
	
	private void createData(JPanel panel,int x,int y) {
		dataLabel = new JLabel();
		dataLabel.setBounds(x, y, 20, 20);
		dataLabel.setText("数据目录");
		panel.add(dataLabel);
		
		dataTxt = new JTextField();
		dataTxt.setBounds(x,y+20,100,10);
		dataTxt.setPreferredSize(new Dimension(200,28));
		dataTxt.setText(properties.getProperty(Utils.DATA));
		dataTxt.setEditable(false);
		panel.add(dataTxt);
		
		dataBtn = new JButton();
		dataBtn.setBounds(x, y+230, 40,28);
		dataBtn.setText("选择");
		dataBtn.setActionCommand("data");
		dataBtn.addActionListener(this);
		panel.add(dataBtn);
	}
	
	private void createHist(JPanel panel,int x,int y) {
		histLabel = new JLabel();
		histLabel.setBounds(x, y, 20, 20);
		histLabel.setText("备份目录");
		panel.add(histLabel);
		
		histTxt = new JTextField();
		histTxt.setBounds(x,y+20,100,10);
		histTxt.setPreferredSize(new Dimension(200,28));
		histTxt.setText(properties.getProperty(Utils.HIST));
		histTxt.setEditable(false);
		panel.add(histTxt);
		
		histBtn = new JButton();
		histBtn.setBounds(x, y+230, 40,28);
		histBtn.setText("选择");
		histBtn.setActionCommand("hist");
		histBtn.addActionListener(this);
		panel.add(histBtn);
	}
	
	//TODO
	private void createLvm(JPanel panel,int x,int y) {
		lvmLabel = new JLabel();
		lvmLabel.setBounds(x, y, 20, 20);
		lvmLabel.setText("振动数据目录");
		panel.add(lvmLabel);
		
		lvmTxt = new JTextField();
		lvmTxt.setBounds(x,y+20,100,10);
		lvmTxt.setPreferredSize(new Dimension(174,28));
		lvmTxt.setText(properties.getProperty(Utils.LVM));
		lvmTxt.setEditable(false);
		panel.add(lvmTxt);
		
		lvmBtn = new JButton();
		lvmBtn.setBounds(x, y+230, 40,28);
		lvmBtn.setText("选择");
		lvmBtn.setActionCommand("lvm");
		lvmBtn.addActionListener(this);
		panel.add(lvmBtn);
	}
	
	private void createSpan(JPanel panel,int x,int y) {
		spanLabel = new JLabel();
		spanLabel.setBounds(x, y, 20, 20);
		spanLabel.setText("任务执行间隔(秒)");
		panel.add(spanLabel);
		
		spanTxt = new JTextField();
		spanTxt.setBounds(x,y+20,100,10);
		spanTxt.setPreferredSize(new Dimension(220,28));
		spanTxt.setText(properties.getProperty(Utils.CRONEXP).split(" ")[0].split("/")[1]); 
		panel.add(spanTxt);
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
	
	private void validatePrams() {
		String data = dataTxt.getText().trim();
		if("".equals(data)) {
			throw new IllegalArgumentException("\"数据目录\"不能为空");
		}
		String hist = histTxt.getText().trim();
		if("".equals(hist)) {
			throw new IllegalArgumentException("\"备份目录\"不能为空");
		}
		String lvm = lvmTxt.getText().trim();
		if("".equals(lvm)) {
			throw new IllegalArgumentException("\"振动数据目录\"不能为空");
		}
		String span = spanTxt.getText().trim();
		if("".equals(span)) {
			throw new IllegalArgumentException("\"任务执行间隔\"不能为空");
		}
		String dbhost = dbHostTxt.getText().trim();
		if("".equals(dbhost)) {
			throw new IllegalArgumentException("\"数据库地址\"不能为空");
		}
		String dbport = dbPortTxt.getText().trim();
		if("".equals(dbport)) {
			throw new IllegalArgumentException("\"数据库端口\"不能为空");
		}
		String dbuser = dbUserTxt.getText().trim();
		if("".equals(dbuser)) {
			throw new IllegalArgumentException("\"数据库用户\"不能为空");
		}
		String dbpass = dbPassTxt.getText().trim();
		if("".equals(dbpass)) {
			throw new IllegalArgumentException("\"数据库密码\"不能为空");
		}
		String dbname = dbNameTxt.getText().trim();
		if("".equals(dbname)) {
			throw new IllegalArgumentException("\"数据库名称\"不能为空");
		}
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ConfigFrame c;
				try {
					c = new ConfigFrame();
					c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					c.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		});
	}
	
	@Override
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
				properties.setProperty(Utils.DATA, dataTxt.getText().trim());
				properties.setProperty(Utils.HIST, histTxt.getText().trim());
				properties.setProperty(Utils.LVM, lvmTxt.getText().trim());
				properties.setProperty(Utils.DBUSER, dbUserTxt.getText().trim());
				properties.setProperty(Utils.DBPASS, dbPassTxt.getText().trim());
				
				String span = spanTxt.getText().trim();
				String cron = "0/" + span + " * * * * ? *";
				properties.setProperty(Utils.CRONEXP, cron);
				
				String dbhost = dbHostTxt.getText().trim();
				String dbPort = dbPortTxt.getText().trim();
				String dbName = dbNameTxt.getText().trim();
				String url = "jdbc:sqlserver://" + dbhost + ":" + dbPort + ";database=" + dbName + ";";
				//String url = "jdbc:oracle:thin:@" + dbhost + ":" + dbPort + ":"  + dbName;
				properties.setProperty(Utils.DBURL, url);
				
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
		} else {
			int ret = chooser.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				chooser.setCurrentDirectory(chooser.getSelectedFile());
				String path = chooser.getSelectedFile().getAbsolutePath();
				path = path.replace("\\", "/");
				String command = e.getActionCommand();
				if("data".equals(command)) {
					dataTxt.setText(path);
				} else if("hist".equals(command)) {
					histTxt.setText(path);
				} else if("lvm".equals(command)) {
					lvmTxt.setText(path);
				}
			}
		}
		
	}

}