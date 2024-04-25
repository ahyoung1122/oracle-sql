<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>

<!-- 컨트롤러 : 무엇으로 정렬할것인지 받아온다.  -->
<%
	//어떤컬럼으로 정렬할것인지 
	//colname에서 col로 변경
	String col = request.getParameter("col");
	//asc, desc
	String sort = request.getParameter("sort");
	
	//디버깅 해서 돌아가는거 확인 완료
	System.out.println(col + "<== q005OrderBy.jsp param col");
	System.out.println(sort + "<== q005OrderBy.jsp param sort");
	
	
	//DAO 호출 -> 모델 반환
	//여기서 누가 모델인가?->DAO가 모델임
	ArrayList<Emp>list = EmpDAO.selectEmpListSort(col, sort);
	System.out.println(list.size() + "<== ");
	//0이 출력되는거 확인->sql작성한 후 확인해 보니 15들어오는거 확인!
%>
<!-- VIEW : 출력하는곳 --> 
<!-- view와 controller은 같은곳에 잇는데 model은 따로있음
나중에는 view랑 controller도 분리해야하기때문에 따로 작성하는것. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>
				empno
				<a href="./q005OrderBy.jsp?col=empno&sort=asc">오름</a>
				<a href="./q005OrderBy.jsp?col=empno&sort=desc">내림</a>
				<!-- 중요한건 이 값들이 넘어오는지 디버깅을 해야한다 -->
			</th>
			<th>
				ename
				<a href="./q005OrderBy.jsp?col=ename&sort=asc">오름</a>
				<a href="./q005OrderBy.jsp?col=ename&sort=desc">내림</a>
			</th>
			<!-- 오름,내림을 클릭했을때 오름차순, 내림차순 보이게끔
				메소드는 딱 하나뿐이다!  -->
		</tr>	
		<%
			for(Emp e : list){
		%>
			<tr>
				<td><%=e.getEmpNo()%></td>
				<td><%=e.getEname()%></td>
			</tr>
		
		<%
			}
		%>
	</table>

</body>
</html>