<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<h2>操作时发生了异常</h2>
  <hr/>
  <h3>错误原因</h3>
    <s:actionerror/>
    <p>
      <s:property value="%{exception.message}"/>
    </p>
    <hr/>
    <h3>错误细节</h3>
    <p>
      <s:property value="%{exceptionStack}"/>
    
</body>
</html>