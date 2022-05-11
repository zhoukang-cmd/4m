package com.fourm.client.ui;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fourm.common.Utils;

public class ForwardStart {
	ClassPathXmlApplicationContext context;
	private Logger logger = LoggerFactory.getLogger(ForwardStart.class);
	public ForwardStart() {
		
	}
	public void start() {
		try {
			if(SystemTray.isSupported()) {
				String [] configLocations = new String[]{"config/base.xml"};
				context = new ClassPathXmlApplicationContext(configLocations, true);
				URL url = getClass().getClassLoader().getResource("icon/forward_small.png");
				Image image = Toolkit.getDefaultToolkit().getImage(url);
				SystemTray tray = SystemTray.getSystemTray();
				PopupMenu pop = new PopupMenu();
				MenuItem exit = new MenuItem("退出");
				MenuItem config = new MenuItem("系统配置");
				MenuItem restart = new MenuItem("重新启动");
				restart.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						context.refresh();
					}
				});
				config.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								ConfigFrame c;
								try {
									c = new ConfigFrame();
									c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									c.setVisible(true);
								} catch (IOException e) {
									logger.error(Utils.printStackTrace(e));
								}	
							}
						});
					}
				});
				exit.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				
				
				pop.add(config);
				pop.add(restart);
				pop.addSeparator();
				pop.add(exit);
				TrayIcon icon = new TrayIcon(image,"fourm_forward",pop);
				icon.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if( e.getClickCount() == 2) {
							SwingUtilities.invokeLater(new Runnable() {
								
								public void run() {
									ConfigFrame c;
									try {
										c = new ConfigFrame();
										c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										c.setVisible(true);
									} catch (IOException e) {
										logger.error(Utils.printStackTrace(e));
									}	
								}
							});
						}
					}
				});
				tray.add(icon);
			}
		}catch(Exception e) {
			logger.equals(Utils.printStackTrace(e));
		}
	}
	public static void main(String[] args) {
		new ForwardStart().start();
	}
}
	
