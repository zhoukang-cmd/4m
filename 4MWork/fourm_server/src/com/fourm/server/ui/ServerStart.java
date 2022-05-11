package com.fourm.server.ui;

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
import com.fourm.server.ui.AddParams;
import com.fourm.server.ui.ConfigFrame;

public class ServerStart {
	ClassPathXmlApplicationContext context;
	private Logger logger = LoggerFactory.getLogger(ServerStart.class);

	public ServerStart() {

	}

	public void start() {
		try {
			if (SystemTray.isSupported()) {
				String[] configLocations = new String[] { "config/base.xml" };
				context = new ClassPathXmlApplicationContext(configLocations, true);
				URL url = getClass().getClassLoader().getResource("icon/server_small.png");
				Image image = Toolkit.getDefaultToolkit().getImage(url);
				SystemTray tray = SystemTray.getSystemTray();
				PopupMenu pop = new PopupMenu();
				MenuItem exit = new MenuItem("退出");
				exit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				MenuItem config = new MenuItem("系统配置");
				config.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
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
				MenuItem addparam = new MenuItem("添加监测参数");
				addparam.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								try {
									AddParams ap = (AddParams) context.getBean("addParams");
									ap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									ap.setContentPane(ap.getContentPane());
									ap.setVisible(true);
								} catch (Exception e) {
									logger.error(Utils.printStackTrace(e));
								}

							}
						});
					}
				});
				pop.add(config);
				pop.add(addparam);
				pop.add(exit);

				TrayIcon icon = new TrayIcon(image, "fourm_server", pop);
				icon.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
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

			} else {// 非图形界面
				String[] configLocations = new String[] { "config/base.xml" };
				context = new ClassPathXmlApplicationContext(configLocations, true);
			}
		} catch (Exception e) {
			logger.error(Utils.printStackTrace(e));
		}
	}

	public static void main(String[] args) {
		new ServerStart().start();
	}
}
