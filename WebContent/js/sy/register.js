$(function() {

	//jQuery time
	var current_fs, next_fs, previous_fs; //fieldsets
	var left, opacity, scale; //fieldset properties which we will animate
	var animating; //flag to prevent quick multi-click glitches

	$(".next").click(function() {
		if ($('input:checkbox[name=termsCheck]:checked').val() != "agree"){
			alert("이용약관 동의에 체크해주세요");
			return;
		}
		
		if (animating) return false;
		animating = true;
		
		current_fs = $(this).parent();
		next_fs = $(this).parent().next();

		//activate next step on progressbar using the index of next_fs
		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

		//show the next fieldset
		next_fs.show();
		//hide the current fieldset with style
		current_fs.animate({ opacity: 0 }, {
			step: function(now, mx) {
				//as the opacity of current_fs reduces to 0 - stored in "now"
				//1. scale current_fs down to 80%
				scale = 1 - (1 - now) * 0.2;
				//2. bring next_fs from the right(50%)
				left = (now * 50) + "%";
				//3. increase opacity of next_fs to 1 as it moves in
				opacity = 1 - now;
				current_fs.css({ 'transform': 'scale(' + scale + ')' });
				next_fs.css({ 'left': left, 'opacity': opacity });
			},
			duration: 800,
			complete: function() {
				current_fs.hide();
				animating = false;
			},
			//this comes from the custom easing plugin
			easing: 'easeInOutBack'
		});
	});

	$(".previous").click(function() {
		if (animating) return false;
		animating = true;

		current_fs = $(this).parent();
		previous_fs = $(this).parent().prev();

		//de-activate current step on progressbar
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

		//show the previous fieldset
		previous_fs.show();
		//hide the current fieldset with style
		current_fs.animate({ opacity: 0 }, {
			step: function(now, mx) {
				//as the opacity of current_fs reduces to 0 - stored in "now"
				//1. scale previous_fs from 80% to 100%
				scale = 0.8 + (1 - now) * 0.2;
				//2. take current_fs to the right(50%) - from 0%
				left = ((1 - now) * 50) + "%";
				//3. increase opacity of previous_fs to 1 as it moves in
				opacity = 1 - now;
				current_fs.css({ 'left': left });
				previous_fs.css({ 'transform': 'scale(' + scale + ')', 'opacity': opacity });
			},
			duration: 800,
			complete: function() {
				current_fs.hide();
				animating = false;
			},
			//this comes from the custom easing plugin
			easing: 'easeInOutBack'
		});
	});

	$(".submit").click((e) => {
		const idCheck = $('#userId').val()!==''?$('#userId').val():null;
		const nickCheck = $('#userNick').val()!==''?$('#userNick').val():null;
		const nameCheck = $('#userName').val()!==''?$('#userName').val():null;
		const pwCheck1 = $('#userPw').val()!==''?$('#userPw').val():null;
		const pwCheck2 = $('#pwConfirm').val()!==''?$('#pwConfirm').val():null;
		console.log(idCheck);
		if(idCheck&&nickCheck&&nameCheck&&pwCheck1&&pwCheck2){
			alert('pass');
		} else{
			e.preventDefault();
			alert('입력하지 않은 항목이 있습니다. 확인해주세요.');
		}
	});
	
	//아이디
	$("#userId").on("input",function(){
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
	
	//닉네임
	$("#userNick").on("input",function(){
		var regex = /[가-힣]{2,}/;
		var result = regex.exec($("#userNick").val());
		if(result != null){
			$("#nickregex").html("");
			$("#nickregex").css("width","0");
			return true;
		}else{
		    $("#nickregex").html("한글만 입력 가능합니다.");
			$("#nickregex").css("width","75%");
			return false;
		}
	});
	
	//이름
	$("#userName").on("input",function(){
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
	$("#userPw").on("input",function(){
		var regex = /^[A-Za-z\d]{4,12}$/;
		var result = regex.exec($("#userPw").val())
		if(result != null){
			$("#pwregex").html("");
			$("#pwregex").css("width","0");
			return true;
		}else{
			$("#pwregex").html("4~11자리의 영문 대소문자+숫자 조합으로 입력해주세요.");
			$("#pwregex").css("width","75%");
		}
	});

	//비밀번호 확인    
	$("#pwConfirm").on("keyup",function(){
		if($("#userPw").val()==$("#pwConfirm").val()){
			$("#repwregex").html("비밀번호가 일치합니다");
			$("#repwregex").css("color","darkgreen");
			return true;
		}else{
			$("#repwregex").html("비밀번호가 일치하지않습니다");
			$("#repwregex").css("width","75%");
		}
	}); 

});

function overlapCheck(type, value){
	$.ajax({
		url : "OverlapCheck.sooyeon",
		type : "POST",
		dataType : "json",
		data : {
			"type" : type,
			"value" : value
		},
		success : function(data){
						
			if(data.result == false){
				alert('이미 사용 중입니다. 다시 입력 후 중복체크를 진행해주세요 :)');
			}else {
				alert('사용가능합니다.');
			}
		},
		error : function(xhr){
			alert('error' + xhr);
		}
		
	});
}

function overlapCheckID(){
		$.ajax({
		url : "OverlapCheck.sooyeon",
		type : "POST",
		dataType : "json",
		data : {
			"type" : "id",
			"value" : $('#userId').val()
		},
		success : function(data){
						
			if(data.result == false){
				alert('이미 사용 중입니다. 다시 입력 후 중복체크를 진행해주세요 :)');
			}else {
				alert('사용가능합니다.');
			}
		},
		error : function(xhr){
			alert('error' + xhr);
		}
		
	});
}
function overlapCheckNick(){
		$.ajax({
		url : "OverlapCheck.sooyeon",
		type : "POST",
		dataType : "json",
		data : {
			"type" : "nick",
			"value" : $('#userNick').val()
		},
		success : function(data){
						
			if(data.result == false){
				alert('이미 사용 중입니다. 다시 입력 후 중복체크를 진행해주세요 :)');
			}else {
				alert('사용가능합니다.');
			}
		},
		error : function(xhr){
			alert('error' + xhr);
		}
		
	});
}
