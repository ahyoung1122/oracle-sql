<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	ArrayList<HashMap<String,String>>jobColor 
		= EmpDAO.selectJobCaseList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<select name="empJob">
		<option value=""> ::: 선택 :::</option>
		<%
			for( HashMap<String,String> c : jobColor ) {
		%>
				<option value= '<%=c.get("job")%>'>
					<%=c.get("job")%>
					(<%=c.get("color")%>)
				</option>
		<%
			}
		%>
		
	</select>
</body>
</html>