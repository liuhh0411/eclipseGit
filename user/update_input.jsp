<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>修改用户帐号信息</title>
</head>
<body>
<center>
<form action="modify_user.action" method="post">
<input type="hidden" name="id" value="<%=Integer.parseInt(request.getParameter("userId"))%>">
<input type="hidden" name="personId" value="<%=Integer.parseInt(request.getParameter("personId"))%>">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">修改用户帐号信息</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->
<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td class="tdEditLabel" >用户帐号</td>			
		<td class="tdEditContent"><input type="text" name="username" value="${user.username }">
		</td>
		<td class="tdEditLabel" >登录密码</td>			
		<td class="tdEditContent"><input type="text" name="password" value="${user.password }"></td>
	</tr>
	<tr>
		<td class="tdEditLabel" >失效时间</td>			
		<td class="tdEditContent"><input type="text" name="expireTime" value="<fmt:formatDate value="${user.expireTime }" pattern="yyyy-MM-dd"/>">
		</td>
		<td class="tdEditLabel" ></td>			
		<td class="tdEditContent"></td>
	</tr>
</table>

			<!-- 主输入域结束 -->
			</td>
		</TR>
	</TBODY>
</TABLE>

<TABLE>
		<TR align="center">
			<TD colspan="3" bgcolor="#EFF3F7">
			<input type="submit" name="saveButton"
				class="MyButton" value="更新用户帐号"> 
			<input type="button" class="MyButton"
				value="关闭窗口" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</form>
</center>
</body>
</html>