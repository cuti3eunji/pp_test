<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
	$(function(){
		
		//location.reload();
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<div id="box">
		<img alt="이미지" src="file:///D:/images/barcode.gif">
		<p class="dotted">
			<i style="font-size: 24px" class="fa">&#xf0de;</i><a href="#"
				onclick="printArea()" id="gogoprint">생성된 바코드 인쇄하려면 여기를 누르세요</a>
		</p>
	</div>	
	</div>
	
	<script type="text/javascript">
		var inBody;
		function beforePrint() {
			boxes = document.body.innerHTML;
			document.body.innerHTML = box.innerHTML;
		}

		function afterPrint() {
			document.body.innerHTML = boxes;
		}
		function printArea() {
			window.print();
		}

		window.onbeforeprint = beforePrint;
		window.onafterprint = afterPrint;
	</script>
</body>
</html>