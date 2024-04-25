<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<%
	//empDAO랑 연결시키기
	ArrayList<HashMap<String,Object>>selfJoin
		=	EmpDAO.selfJoinEmp();
	
	//값 들어왔는지 확인하기 -> 확인완료~! 
	System.out.println("selfJoin임"+selfJoin);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>SELF JOIN TABLE</h1>
		<table border = "1">
			<tr>
				<th>empno</th>
				<th>ename</th>
				<th>grade</th>
				<th>mgrName</th>
				<th>mgrGrade</th>
			</tr>
				<%
					for(HashMap<String,Object> m : selfJoin){
				%>
					
					<tr><!-- 1.우선 m.get으로 데이터 먼저 출력해보기 ->완료
							 2. mgrGrade,grade숫자만큼 for문으로 반복시키기 -->
						<td><%=m.get("empno")%></td>
						<td><%=m.get("ename")%></td>
						<td>
							<%	//앞에 (integer)붙여서 int로 변경
								for(int i=0; i<(Integer)m.get("grade"); i=i+1)
								{
							%>
									🍒
							<%
								}
							%>
						</td>
						<td><%=m.get("mgrName")%></td>
						<td>
							<%	
								for(int i=0; i<(Integer)m.get("mgrGrade"); i=i+1)
								{
							%>
									🍊
							<%
								}
							%>
						</td>
					</tr>
					
				<%
					}
				%>
		

	
	</table>
</body>
</html>