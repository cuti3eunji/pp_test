<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		
		$('#goBTN').click(function(){
			
			
			
			var mem_name = $('input[name=mem_name]').val();
			
			if(mem_name.length<=0){
				alert("입력필요");
				return false;
			}
			
			
			 $.ajax({
					
					type : 'get'
					,url : '${pageContext.request.contextPath}/ajax'
					,data :{mem_name :mem_name }
					,error : function(result){
							alert(result.status);
					}
					,success : function(result){
						
							var code ="";
							code += "<p>"+result.mem_name+"</p>"
							code += "<p>"+result.mem_age+"</p>"
							code += "<p>"+result.mem_add+"</p>"
							
							$('#result').append().html(code);
					}
				}) 
			
		})
		
		
	})
</script>
<style>
div{
	border: 1px solid black;
	width: 100%;
	height: 100px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>주소</th>
			
		</tr>
		<c:forEach items="${memberList }" var="mem">
		<tr>
			<td>${mem.mem_name}</td>
			<td>${mem.mem_age}</td>
			<td>${mem.mem_add}</td>
		</tr>
		</c:forEach>	
	</table>
	
			${member.mem_name }
			${member.mem_age }
			${member.mem_add }
			
			<br><br>
			
 	<h2>사람찾기</h2>			
	<form action="" id="goForm" method="get">
	<input name="mem_name" type="text">		
	<button id="goBTN"  type="button">찾기</button>
	</form>		
	<br>
	
	<h2>사람추가</h2>			
	<form action="${pageContext.request.contextPath }/insertMember" id="insertForm" method="get">
	<input name="mem_name" type="text" value="">
	<input name="mem_age" type="text" value="">		
	<input name="mem_add" type="text" value="">				
	<button id="insertBTN"  type="submit">추가</button>
	</form>	
	<div id="result"></div>
</body>
</html>