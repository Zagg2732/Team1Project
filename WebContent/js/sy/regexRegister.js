$(function(){
	//아이디
	var idCheck = $("#userId").on("input",function(){
		var regex = /^[a-z\d]{5,11}$/; 
		var result = regex.exec($("#userId").val());
		if(result != null){
			$("#idregex").html("");
			$("#idregex").css("width","0");
			return true;
		}else{
		    $("#idregex").html("5~10자리의 영 소문자+숫자 조합으로 입력해주세요.");
			$("#idregex").css("width","75%");
			return false;
		}
	});
	
	//이름
	var nameCheck = $("#userName").on("input",function(){
		var regex = /[가-힣]{2,}/;
		var result = regex.exec($("#userName").val());
		if(result != null){
			$("#nameregex").html("");
			$("#nameregex").css("width","0");
			return true;
		}else{
			$("#nameregex").html("한글만 입력 가능합니다.");
			$("#nameregex").css("width","75%");
			return false;
		}
	});
	
	//비밀번호
	var pwCheck1 = $("#userPw").on("input",function(){
		var regex = /^[A-Za-z\d]{4,12}$/;
		var result = regex.exec($("#userPw").val())
		if(result != null){
			$("#pwregex").html("");
			$("#pwregex").css("width","0");
			return true;
		}else{
			$("#pwregex").html("4~11자리의 영문 대소문자+숫자 조합으로 입력해주세요.");
			$("#pwregex").css("width","75%");
			return false;
		}
	});

	//비밀번호 확인    
	var pwCheck2 = $("#pwConfirm").on("keyup",function(){
		if($("#userPw").val()==$("#pwConfirm").val()){
			$("#repwregex").html("비밀번호가 일치합니다");
			$("#repwregex").css("color","darkgreen");
			return true;
		}else{
			$("#repwregex").html("비밀번호가 일치하지않습니다");
			$("#repwregex").css("width","75%");
			return false;
		}
	});
	
	if(!(idCheck&&nameCheck&&pwCheck1&&pwCheck2)){
		
	}

});