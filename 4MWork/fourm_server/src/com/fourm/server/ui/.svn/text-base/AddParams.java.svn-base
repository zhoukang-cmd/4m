package com.fourm.server.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.StringUtils;

import com.fourm.common.Utils;

/**
 * 为某一机器新增参数
 * @author zhangtaichao , Mobile Bank System, CSII
 * <p>created on 2012-4-9 </p>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AddParams extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1576056214118578621L;
	private Logger logger = LoggerFactory.getLogger(AddParams.class);
	private final String pleaseselect= "***请选择***";
	private final String hint = "温馨提示：这里填入各个参数对应的名称，以英文件输入状态下分号\";\"做分隔符\n假如参数个数为5，则这里填入:" +
			"\n参数1;参数2;参数3;参数4;参数5;";
	List<Map> oreList = null;
	List<Map> roomList = null;
	List<Map> equipList = null;
	private JPanel jContentPane;
	private JComboBox ores;
	private JLabel oreLabel;
	private JComboBox rooms;
	private JLabel roomLabel;
	private JComboBox equips;
	private JLabel equipLabel;
	
	private JLabel typeLabel;
	private JRadioButton HRadio;
	private JRadioButton LRadio;
	private ButtonGroup btnGroup;
	
	private JLabel countLabel;
	private JTextField countTxt;
	
	private JLabel contentLabel;
	private JTextArea contentTxt;
	
	private JButton okButton;
	private JButton cancelButton;
	private SqlMapClientTemplate sqlMapClient;
	public AddParams() {
		setTitle("add params");
		setBounds(500,150,400,450);
		setResizable(false);
	}
	
	public Container getContentPane() {
		if(oreList == null) {
			try {
				oreList = getSqlMapClient().queryForList("server.selectMine");
			} catch(Exception e) {
				logger.error(Utils.printStackTrace(e));
				JOptionPane.showMessageDialog(this, "数据库异常，详情请查询日志!");
			}
		}
		if(roomList == null) {
			try {
				roomList = getSqlMapClient().queryForList("server.selectRoom");
			} catch(Exception e) {
				logger.error(Utils.printStackTrace(e));
				JOptionPane.showMessageDialog(this, "数据库异常，详情请查询日志!");
			}
		}
		if(equipList == null) {
			try {
				equipList = getSqlMapClient().queryForList("server.selectEquip");
			} catch(Exception e) {
				logger.error(Utils.printStackTrace(e));
				JOptionPane.showMessageDialog(this, "数据库异常，详情请查询日志!");
			}
		}
		
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		Box vb = Box.createVerticalBox();
		oreLabel = new JLabel();
		oreLabel.setText("选择矿区:");	
		ores = new JComboBox();
		ores.addItem(pleaseselect);
		ores.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED == e.getStateChange() ) {
					String mine = getSelectedMine();
					fillRooms(rooms, roomList, mine);
				}
				
			}
		});
		
		fillComboBox(ores, oreList);
		
		Box orebox = Box.createHorizontalBox();
		orebox.setBounds(10, 10, 380, 20);
		orebox.add(oreLabel);
		orebox.add(ores);
		orebox.add(Box.createGlue());
		vb.add(orebox);
		vb.add(Box.createVerticalGlue());
		
		roomLabel = new JLabel();
		roomLabel.setText("选择机房:");
		rooms = new JComboBox();
		rooms.addItem(pleaseselect);
		rooms.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED == e.getStateChange() ) {
					String room = getSelectedRoom();
					fillEquips(equips, equipList, room);
				}
			}
		});
		rooms.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				String ore = getSelectedMine();
				if(ore == null) {
					JOptionPane.showMessageDialog(null, "请先选择矿区!");
					ores.grabFocus();
				}	
			}
		});
		
		Box roombox = Box.createHorizontalBox();
		roombox.setBounds(10, 30, 380, 20);
		roombox.add(roomLabel);
		roombox.add(rooms);
		roombox.add(Box.createGlue());
		vb.add(roombox);
		vb.add(Box.createVerticalGlue());
		
		equipLabel = new JLabel();
		equipLabel.setText("选择风机:");
		equips = new JComboBox();
		equips.addItem(pleaseselect);
		
		Box equipbox = Box.createHorizontalBox();
		equipbox.setBounds(10, 50, 380, 20);
		equipbox.add(equipLabel);
		equipbox.add(equips);
		equipbox.add(Box.createGlue());
		vb.add(equipbox);
		vb.add(Box.createVerticalGlue());
		
		Box radioBox = Box.createHorizontalBox();
		typeLabel = new JLabel("参数类型:");
		btnGroup = new ButtonGroup();
		HRadio = new JRadioButton("振动",true);
		HRadio.setActionCommand("H");
		LRadio = new JRadioButton("非振动");
		LRadio.setActionCommand("L");
		btnGroup.add(HRadio);
		btnGroup.add(LRadio);
		radioBox.add(typeLabel);
		radioBox.add(HRadio);
		radioBox.add(LRadio);
		radioBox.add(Box.createGlue());
		vb.add(radioBox);
		vb.add(Box.createVerticalGlue());
		vb.setBounds(10, 10, 380, 150);
		
		JPanel formPanel = new JPanel();
		formPanel.setBorder(new LineBorder(Color.black));
		formPanel.setLayout(null);
		formPanel.setBounds(10,160,380,250);
		countLabel = new JLabel("参数个数:");
		countLabel.setBounds(5, 10, 70, 20);
		countTxt = new JTextField();
		countTxt.setBounds(70, 10, 50, 25);
		formPanel.add(countLabel);
		formPanel.add(countTxt);
		
		contentLabel = new JLabel("参数名称:");
		contentLabel.setBounds(5, 40, 70, 20);
		
		contentTxt = new JTextArea();
		contentTxt.setText(hint);
		contentTxt.setAutoscrolls(true);
		contentTxt.setLineWrap(true);
		contentTxt.setBorder(new LineBorder(Color.black));
		contentTxt.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				if("".equals(contentTxt.getText().trim())) {
					contentTxt.setText(hint);
				}
			}
			
			public void focusGained(FocusEvent e) {
				if(hint.equals(contentTxt.getText())) {
					contentTxt.setText("");
				}
			}
		});
		JScrollPane pane = new JScrollPane(contentTxt);
		pane.setBounds(70, 40, 300, 100);
		
		formPanel.add(contentLabel);
		formPanel.add(pane);
		
		okButton = new JButton("保存");
		okButton.setBounds(50, 150, 60, 30);
		okButton.setActionCommand("ok");
		okButton.addActionListener(this);
		cancelButton = new JButton("取消");
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(130, 150, 60, 30);
		formPanel.add(okButton);
		formPanel.add(cancelButton);
		jContentPane.add(vb);
		jContentPane.add(formPanel);
		
		return jContentPane;
	}

	public SqlMapClientTemplate getSqlMapClient() {
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public static void main(String[] args) {
		
	}
	private void fillComboBox(JComboBox box,List list) {
		if(list != null) {
			for(Iterator<Map> i = list.iterator();i.hasNext();) {
				Map tmp = i.next();
				box.addItem(tmp.get("SHOW"));
			}
		}
	}
	private void fillRooms(JComboBox rooms,List list,String mineId) {
		rooms.removeAllItems();
		if(mineId == null || list == null) {
			rooms.addItem(pleaseselect);
		} else {
			for(Iterator<Map> it = list.iterator();it.hasNext();) {
				Map tmp = it.next();
				if(mineId.equals(tmp.get("MINE_ID").toString())) {
					rooms.addItem(tmp.get("SHOW"));
				}
			}
		}
		if(rooms.getItemCount() == 0) {
			rooms.addItem(pleaseselect);
		}
	}
	private void fillEquips(JComboBox equips,List list,String roomId) {
		equips.removeAllItems();
		if(roomId == null || list == null) {
			equips.addItem(pleaseselect);
		} else {
			for(Iterator<Map> it = list.iterator();it.hasNext();) {
				Map tmp = it.next();
				if(roomId.equals(tmp.get("ROOM_ID").toString())) {
					equips.addItem(tmp.get("SHOW"));
				}
			}
		}
		if(equips.getItemCount() == 0) {
			equips.addItem(pleaseselect);
		}
	}
	private String getSelectedMine() {
		String show = (String)ores.getSelectedItem();
		if(oreList == null || pleaseselect.equals(show)) {
			return null;
		}
		for(Iterator<Map> it = oreList.iterator(); it.hasNext();) {
			Map tmp = it.next();
			if(show.equals(tmp.get("SHOW"))) {
				return tmp.get("MINE_ID").toString();
			}
		}
		return null;
	} 
	private String getSelectedRoom() {
		String show = (String)rooms.getSelectedItem();
		String mineId = getSelectedMine();
		if(CollectionUtils.isEmpty(roomList) || pleaseselect.equals(show) || mineId == null) {
			return null;
		}
		for(Iterator<Map> it = roomList.iterator(); it.hasNext();) {
			Map tmp = it.next();
			if(show.equals(tmp.get("SHOW")) && mineId.equals(tmp.get("MINE_ID").toString())) {
				return tmp.get("ROOM_ID").toString();
			}
		}
		return null;
	}
	private Map getSelectedEquip() {
		String show = (String)equips.getSelectedItem();
		String roomId = getSelectedRoom();
		if(CollectionUtils.isEmpty(equipList) || pleaseselect.equals(show) || roomId == null) {
			return null;
		}
		for(Iterator<Map> it = equipList.iterator(); it.hasNext();) {
			Map tmp = it.next();
			if(show.equals(tmp.get("SHOW")) && roomId.equals(tmp.get("ROOM_ID").toString())) {
				return tmp;
			}
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if("ok".equals(command)) {
			Map equip = getSelectedEquip();
			if(equip == null) {
				JOptionPane.showMessageDialog(this, "请选择风机!");
				return;
			}
			ButtonModel b = btnGroup.getSelection();
			String type = b.getActionCommand();
			equip.put("FIELD_TYPE", type);
			String countStr = countTxt.getText().trim();
			int count = 0;
			try {
				count = Integer.parseInt(countStr);
				if(count <= 0) throw new Exception();
			} catch(Exception e2) {
				JOptionPane.showMessageDialog(this, "参数个数应为正数");
				return;
			}
			String content = StringUtils.trimAllWhitespace(contentTxt.getText());
			String[] ss = content.split(";");
			if(ss.length != count) {
				JOptionPane.showMessageDialog(this, "参数名称个数与参数个数不一致!");
				return ;
			}
			for(int i=0; i<count; i++) {
				equip.put("FIELD_NAME", ss[i]);
				equip.put("FIELD_DESC", ss[i]);
				equip.put("FIELD_NO", (i+1));
				try {
					sqlMapClient.insert("server.insertField", equip);
				} catch(DataIntegrityViolationException e2) {
					try {
						sqlMapClient.update("server.updateField", equip);
					} catch(Exception ee) {
						logger.error(Utils.printStackTrace(ee));
						JOptionPane.showMessageDialog(this, "数据库更新异常，详情请查询日志");
					}
				}
			}
			int ret = JOptionPane.showConfirmDialog(this, "添加成功，确认关闭窗口?");
			if(ret == JOptionPane.OK_OPTION) {
				dispose();
			} 
		} else  if("cancel".equals(command)) {
			dispose();
		}
		
	}
	
}
