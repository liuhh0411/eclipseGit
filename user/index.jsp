<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>�û�����</title>
</head>
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
      <TABLE width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR height=35>
            <TD align="center" width=20 background=images/title_left.gif 
          bgColor=#dee7ff></TD>
            <TD align="center" width=120 background=images/title_left.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7> �û�����<font color="#FFFFFF">&nbsp;</font></FONT> </TD>
            <TD align="center" width=11 background=images/title_middle.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
            <TD align="center" background=images/title_right.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD width="82%" height=14 align=right vAlign="middle" noWrap>
            </TD>
            <TD width="18%" align=right vAlign="middle" noWrap>��</TD>
          </TR>
          <TR>
            <TD height=14 align=right vAlign="middle" noWrap>
            	<!-- ����������ѯ�� -->
            </TD>
            <TD height=14 align="left" vAlign="middle" noWrap>
            </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=right vAlign="middle" noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- ��������������ҳ������ -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
          <!-- �б������ -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="5%" height="37" align="center"><b>���</b></td>
		      <td width="10%" height="37" align="center"><B>����</B></td>
		      <td width="10%" height="37" align="center"><b>�Ա�</b></td>
              <td width="10%" height="37" align="center"><b>��������</b></td>
              <td width="10%" height="37" align="center"><b>��¼�ʺ�</b></td>
              <td width="10%" height="37" align="center"><b>ʧЧʱ��</b></td>
              <td width="45%" height="37" align="center"><b>����</b></td>
          </tr>
          <!-- �б������� -->
          <c:if test="${!empty pm.list}">
          <c:forEach items="${pm.list }" var="person">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		      <td align="center" vAlign="middle">${person.id }</td>
	          <td align="center" vAlign="middle">${person.name }</td>
	          <td align="center" vAlign="middle">${person.sex }</td>
	          <td align="center" vAlign="middle">${person.org.name }</td>
	          <td align="center" vAlign="middle">${person.user.username }</td>
	          <td align="center" vAlign="middle"><fmt:formatDate value="${person.user.expireTime }" pattern="yyyy-MM-dd"/></td>
	          <td align="center" vAlign="middle">
	          <a href="#" onclick="openWin('user/add_input.jsp?personId=${person.id }','assignUser',600,100);">�����ʺ�:${person.id }</a>
	          <a href="#" onclick="del('del_user.action?id=${person.user.id }');">ɾ���ʺ�</a>
	          <a href="#" onclick="openWin('user/update_input.jsp?userId=${person.user.id }&personId=${person.id }','chgpsw',600,100);">�޸��ʺ�</a>
	          <a href="#" onclick="openWin('userRole.action?id=${person.user.id }','assignRole',600,400);">�����ɫ</a>
	          <a href="#" onclick="openWin('acl.action?principalType=User&principalSn=${person.user.id }','RoleAuth',600,500,1);">���û���Ȩ</a>
	          </td>
        </tr>
        </c:forEach>
		</c:if>
        <!-- ���б�����Ϊ�յ�ʱ��Ҫ��ʾ����ʾ��Ϣ -->
	    <c:if test="${empty pm.list}">
	    <tr>
	    	<td colspan="7" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	û���ҵ���Ӧ�ļ�¼
	    	</td>
	    </tr>
	    </c:if>
      </table>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign="middle" noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- ��������������ҳ������ -->
<pg:pager items="${pm.total }" url="user.action" export="currentPageNumber=pageNumber" maxPageItems="5">
	<pg:first><a href="${pageUrl}">��ҳ</a></pg:first>
	<pg:prev><a href="${pageUrl }">ǰҳ</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		<font color="red">${pageNumber}</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }">��ҳ</a></pg:next>
	<pg:last><a href="${pageUrl }">βҳ</a></pg:last>
</pg:pager>            
    		</TD>
          </TR>
        </TBODY>
      </TABLE>
</center>

</body>

</html>
