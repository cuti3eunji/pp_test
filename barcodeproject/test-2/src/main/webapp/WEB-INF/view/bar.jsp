<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
body {	background-color: #25274d;}
.contact {	padding: 4%;	height: 400px;}
.col-md-3 {
	background: #ff9b00;
	padding: 4%;
	border-top-left-radius: 0.5rem;
	border-bottom-left-radius: 0.5rem;
}

.contact-info {
	margin-top: 10%;
}

.contact-info img {
	margin-bottom: 15%;
}

.contact-info h2 {
	margin-bottom: 10%;
}

.col-md-9 {
	background: #fff;
	padding: 3%;
	border-top-right-radius: 0.5rem;
	border-bottom-right-radius: 0.5rem;
}

.contact-form label {
	font-weight: 600;
}

.contact-form button {
	background: #25274d;
	color: #fff;
	font-weight: 600;
	width: 25%;
}

.contact-form button:focus {
	box-shadow: none;
}
</style>
<script type="text/javascript">
	$(function() {

		//input을 datepicker로 선언
		$("#expire")
				.datepicker(
						{
							dateFormat : 'ymmdd' //Input Display Format 변경
							,
							showOtherMonths : true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
							,
							showMonthAfterYear : true //년도 먼저 나오고, 뒤에 월 표시
							,
							changeYear : true //콤보박스에서 년 선택 가능
							,
							changeMonth : true //콤보박스에서 월 선택 가능                
							,
							showOn : "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
							,
							buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
							,
							buttonImageOnly : true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
							,
							buttonText : "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
							,
							yearSuffix : "년" //달력의 년도 부분 뒤에 붙는 텍스트
							,
							monthNamesShort : [ '1', '2', '3', '4', '5', '6',
									'7', '8', '9', '10', '11', '12' ] //달력의 월 부분 텍스트
							,
							monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
									'7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip 텍스트
							,
							dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 부분 텍스트
							,
							dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',
									'금요일', '토요일' ] //달력의 요일 부분 Tooltip 텍스트
							,
							minDate : "0" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
							,
							maxDate : "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                

						});

		//초기값을 오늘 날짜로 설정
		$('#expire').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)     

		/*모달창 켰을때 포커스처리  */
		$("#myModal").on('shown.bs.modal', function() {
			$(this).find('#searchBarcode').focus();
		});

		//바코드 조회버튼
		$('#goBTN').click(
				function() {

					var inputBarcode = $('#searchBarcode').val();
					if (inputBarcode.length <= 0) {
						alert("스캔을 해주세요!");
						return false;
					}
					$(location).attr(
							'href',
							'${pageContext.request.contextPath}/search?inputBarcode='
									+ inputBarcode);
					//alert(inputBarcode);

				})

		var gtincode = $('#gtinSpan').val();

		if (gtincode.length > 0) {
			$('select[name=gs1] option[value=' + gtincode + ']').attr(
					'selected', true);
			//alert(gtincode);

		}

		//GTIN코드 보여주기..		
		$('#gs1').change(function() {
			var data = $(this).val();
			$('#gtinSpan').append().html(data);
		})

		/* 정규식과 바코드 생성하는 버튼 클릭 */
		$("#gen")
				.click(
						function() {

							//상품식별코드 발리데이션(01)
							var gs1 = /^[0-9]{13,14}$/;
							var gs1code = $('select[name=gs1]').val();
							if (!gs1.test(gs1code) || gs1code.length <= 0) {
								alert("상품식별코드가 선택되지 않았습니다.")
								return false;
							}

							//유효기간입력 발리데이션(17)
							var exprieNum = /^\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
							var expireIn = $("#expire").val().trim();
							if (!exprieNum.test(expireIn)
									|| expireIn.length <= 0) {
								alert("유효기간 형식이 잘못되었습니다.")
								return false;
							}

							//제품제조번호(10)
							var lineNum = /^[!%&)(*+,-./_:;/>/</=/?a-zA-Z0-9]{5}$/;
							var lineNumIn = $('#lNum').val().trim();
							if (!lineNum.test(lineNumIn)
									|| lineNumIn.length <= 0) {
								alert("제품제조번호 코드가 잘못되었습니다.")
								return false;
							}

							//제품일련번호(21)
							var serialNum = /^[!%&)(*+,-./_:;/>/</=/?a-zA-Z0-9]{1,20}$/;
							var serialNumIn = $('#sNum').val().trim();
							if (!serialNum.test(serialNumIn)
									|| serialNumIn.length <= 0) {
								alert("제품일련번호가 잘못되었습니다.")
								return false;
							}

							//바코드생성하는 메서드로 ㄱㄱ

							var fullBarcode = "01" + gs1code + "17" + expireIn
									+ "10" + lineNumIn + "21" + serialNumIn;
							//	   alert("유효기간입력 : "+expireIn+"제품제조번호  : "+lineNumIn + "제품일련번호 : " + serialNumIn);
							var bacodeImg = "";
							$.ajax({

										type : 'post',
										url : '${pageContext.request.contextPath}/genBarcode',
										data : {
											fullBarcode : fullBarcode
										},
										error : function(result) {
											alert(result.status);
										},
										success : function(result) {
	
											var code = "";
											code += "<img alt='바코드' src="+result+" width='100' height='100'>";
											//	code += "<p>전체 바코드 : (01)"+result.substring(2,16)+"(17)"+result.substring(18,24)+"(10)"+result.substring(26,31)+"(21)"+result.substring(33)+" </p>"

											$('#box').append().html(code);
											bacodeImg = result;
								//		$('#box').load(location.href+'#box');
										}
									})
									

// 							$.ajax({
// 										url : '${pageContext.request.contextPath}/barcodeimg',
// 										error : function(result) {
// 											alert(result.status);
// 										},
// 										success : function(result) {

// 											var code = "";
// 											code += "<img alt='바코드' src="+bacodeImg+" width='100' height='100'>";
// 											//	code += "<p>전체 바코드 : (01)"+result.substring(2,16)+"(17)"+result.substring(18,24)+"(10)"+result.substring(26,31)+"(21)"+result.substring(33)+" </p>"

// 											$('#box').append().html(code);
// 										}
// 									})

							alert("바코드가 생성되었습니다.");
							 popupOpen();
						})
						
						
						

		//라벨로 인쇄하기 위한 버튼 클릭	
		$('#goLabel').click(function() {

			//상품식별코드 발리데이션(01)
			var gs1 = /^[0-9]{13,14}$/;
			var gs1code = $('select[name=gs1]').val();
			if (!gs1.test(gs1code) || gs1code.length <= 0) {
				alert("상품식별코드가 선택되지 않았습니다.")
				return false;
			}

			//유효기간입력 발리데이션(17)
			var exprieNum = /^\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
			var expireIn = $("#expire").val().trim();
			if (!exprieNum.test(expireIn) || expireIn.length <= 0) {
				alert("유효기간 형식이 잘못되었습니다.")
				return false;
			}

			//제품제조번호(10)
			var lineNum = /^[!%&)(*+,-./_:;/>/</=/?a-zA-Z0-9]{5}$/;
			var lineNumIn = $('#lNum').val().trim();
			if (!lineNum.test(lineNumIn) || lineNumIn.length <= 0) {
				alert("제품제조번호 코드가 잘못되었습니다.")
				return false;
			}

			//제품일련번호(21)
			var serialNum = /^[!%&)(*+,-./_:;/>/</=/?a-zA-Z0-9]{1,20}$/;
			var serialNumIn = $('#sNum').val().trim();
			if (!serialNum.test(serialNumIn) || serialNumIn.length <= 0) {
				alert("제품일련번호가 잘못되었습니다.")
				return false;
			}

			//바코드라벨프린터
			$.ajax({

				type : 'post',
				url : '${pageContext.request.contextPath}/print',
				data : {
					"gs1code" : gs1code,
					"expireIn" : expireIn,
					"lineNumIn" : lineNumIn,
					"serialNumIn" : serialNumIn
				},
				error : function(result) {
					alert(result.status);
				},
				success : function(result) {
					alert("인쇄성공");
				}
			})
			alert("바코드가 생성되었습니다.");
		});

	});
//팝업창
function popupOpen(){
		var url= "/barcodeImg";    //팝업창에 출력될 페이지 URL
		var winWidth = 700;
	    var winHeight = 600;
	    var popupOption= "width="+winWidth+", height="+winHeight;    //팝업창 옵션(optoin)
	    var myWindow = window.open(url,"TestName",popupOption);
	 //   myWindow.document.write("<img alt='바코드' src="+bacodeImg+" width='100' height='100'>");
	 
	 //file:///D:/images/barcode.gif
	}	
</script>
</head>
<body>
	<div class="container contact">
	<div class="row">
		<div class="col-md-3">
			<div class="contact-info">
				<img src="https://image.ibb.co/kUASdV/contact-image.png" alt="image"/>
				<h2>바코드를 생성해보세요!</h2>
				<h4>GS1 표준 바코드가 생성 가능합니다! </h4>
			</div>
		</div>
		<div class="col-md-9">
			<div class="contact-form">
	<form class="form-inline" id="generateForm" method="post">	
				<div class="form-group">
				  <label class="control-label col-sm-3" for="fname">상품식별코드(01):</label>
				  <div class="col-sm-10">          
					<select class="form-control"id="gs1" name="gs1">
							<option>GS1상품코드</option>
						<c:forEach items="${masterList }" var="master">
							<option value="${master.ma_gtin }">${master.ma_name }</option>
						</c:forEach>							
						</select>
					<c:if test="${!empty zz }">
							<input id="gtinSpan" class="form-control" type="text"	readonly="readonly" value="${fn:substring(zz.gs1code,2,18) }">
					</c:if>
					<c:if test="${empty zz }">
							<span id="gtinSpan" class="form-control"></span>
					</c:if>
						
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-sm-2" for="lname">유효기간(17):</label>
				  <div class="col-sm-10">          
					<c:if test="${!empty zz }">
						<input class="form-control" id="" name="expire"		placeholder="YYMMDD" readonly="readonly"value="${fn:substring(zz.expire,2,8) }">
					</c:if>
					<c:if test="${empty zz }">
						<input class="form-control" id="expire" name="expire"	placeholder="YYMMDD" readonly="readonly">
					</c:if>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-sm-2" for="email">제조번호(10):</label>
				  <div class="col-sm-10">
					<c:if test="${!empty zz }">
							<input class="form-control" id="lNum" name="lNum"
								placeholder="5자리입력 " value="${zz.lNum }">
						</c:if>
						<c:if test="${empty zz }">
							<input class="form-control" id="lNum" name="lNum"
								placeholder="5자리입력">
						</c:if>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-sm-2" for="email">일련번호(21):</label>
				  <div class="col-sm-10">
					<c:if test="${!empty zz }">
							<input class="form-control" id="sNum" name="sNum"
								placeholder="20자리이하입력" value="${zz.sNum }">
						</c:if>
						<c:if test="${empty zz }">
							<input class="form-control" id="sNum" name="sNum"
								placeholder="20자리이하입력">
						</c:if>
				  </div>
				</div>
				
				<div class="form-group">        
				  <div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
				  </div>
				</div>
				
		</form>
			</div>
		</div>
	</div>
</div>s
</body>
</html>