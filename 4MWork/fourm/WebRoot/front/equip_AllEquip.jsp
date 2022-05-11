<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
 <div class="cksysb" id="cksysb">
			<div class="cksysb-top">
				<p>
		          <s:property value="equip.mineName"/>&nbsp;&nbsp;&nbsp; <s:property value="equip.roomName"/>
				</p>
				<span onclick="floatClose();"> 关闭</span>
			</div>
			<div class="ckzsb-cx">

				<ul>
					<li>
						<span>设备编号：</span>
						<input type="text" name="sbmc" id="equipNum">
					</li>
					<li>
						<span>设备名称：</span>
						<input type="text" name="sbmc" id="equipName">
					</li>
					<li>
						<a href="javascript:selQueryEquip()"><img src="${ctx}/images/sbtz-cx.jpg" /> </a>
					</li>

				</ul>

			</div>
			<div class="cksysb-gundong">
			<div class="cksysb-bt">
				<ul>

					<li style="width: 100px;">
						序号
					</li>
					<li>
						设备编号
					</li>
					<li>
						设备名称
					</li>
					<li>
						设备型号
					</li>
					<li style="width: 200px;">
						使用单位
					</li>
					<li style="width: 200px;">
						使用地点
					</li>
					<li>
						负责人
					</li>
					<li style="width: 200px;">
						厂家信息
					</li>
					<li>
						启用时间
					</li>
					<li>
						设备状态
					</li>
				</ul>
			</div>

			<div id="content"></div>
            </div>
		</div>
