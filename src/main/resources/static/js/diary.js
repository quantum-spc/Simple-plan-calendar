// 어플리케이션 시작
function startApplycation() {
	
}

// 테스트 계정 사용
function loginTestUser() {
	$("input[name=memberid]").val("test");
	$("input[name=memberpw]").val("test");
	submitLogin();
}

// 캘린더 갱신
function fullCalendarRefresh() {

	if (!checkLogin()) return;

	$('#calendar').fullCalendar('removeEvents');
	$('#calendar').fullCalendar('refetchEvents');
}

// 로그인 체크
function checkLogin() {
	return $("input[name=checkLogin]").val() ? true : false;
}

// endDate 널 체크
function checkNullEndDate(event) {
	return endDate = (event["end"]) ? event["end"]["_d"].toJSON() : null;
}

// 클릭 이벤트
function clickEvent(event, calEvent) {
	//console.log(event);
	//console.log(calEvent);

	$('#titleValue').val(event.title);
	$("input[name=eventId]").val(event.id);
	$("input[name=eventStart]").val((event["start"] != null ? event["start"]["_d"].toJSON() : null));
	$("input[name=eventEnd]").val((event["end"] != null ? event["end"]["_d"].toJSON() : null));
	$('#fullCalModal').modal();

	// 타이틀 색상 SELECT
	var eventTitleColor = event.color == "#3a87ad" ? "default" : event.color;
	$(".colorSelect option").each( function(index, Element) {
		if (this.value == eventTitleColor) {
			$(this).prop("selected", "selected");
		} else {
			$(this).removeAttr("selected");
		}
	});
}

// 일정 추가 이벤트
function putDiaryInsert(dateValue, bg_color, text_color) {
	if (!checkLogin()) {
		alert("로그인 하지 않은 상태에서는 반영되지 않습니다.");
	} else {
		$.ajax({
			url:"/calendar/plan/",
			method : 'POST',
			async: false,
			contentType: 'application/json',
			data:JSON.stringify({"start":dateValue, "title":"My Event", "color":bg_color}),
			success:function(data) {}
		});
	}

	fullCalendarRefresh();
} // End of function

// 일정 이동
function moveEvent(event) {
	var seq = event["id"];
	var startDate = event["start"]["_d"].toJSON();
	var EndDate = checkNullEndDate(event);
	var title = event["title"];
	var color = event["color"];

	putDiaryUpdate(seq, startDate, EndDate, title, color, "N");
}

// 일정 리사이즈
function resizeEvent(event) {
	//console.log(event);

	var seq = event["id"];
	var startDate = event["start"]["_d"].toJSON();
	var EndDate = checkNullEndDate(event);
	var title = event["title"];
	var color = event["color"];

	putDiaryUpdate(seq, startDate, EndDate, title, color, "N");
}

// 일정 수정
function putTitleUpdate() {
	var eventId = $("input[name=eventId]").val();
	var title = $("input[name=titleValue]").val();
	var colorSelect = $("select[name=colorSelect]").val();
	var start = $("input[name=eventStart]").val();
	var end = $("input[name=eventEnd]").val();

	putDiaryUpdate(eventId, start, end, title, colorSelect, "N");
}

// 일정 삭제
function putEventDelete(event) {
	var seq = event["id"];
	var startDate = event["start"]["_d"].toJSON();
	var EndDate = checkNullEndDate(event);
	var title = event["title"];
	var color = event["color"];

	putDiaryUpdate(seq, startDate, EndDate, title, color, "Y");
}

// 일정 업데이트 이벤트
function putDiaryUpdate(seq, startDate, EndDate, title, color, delflag) {
	$.ajax({
		url:"/calendar/plan/"+seq,
		method : 'PUT',
		contentType: 'application/json',
		data:JSON.stringify({"start":startDate, "end":EndDate, "title":title, "color":color, "delflag":delflag}),
		success:function(data) {
			fullCalendarRefresh();
			$('#fullCalModal').modal('hide');
		}, error:function(e) {
			if (e.status == 405) {
				alert("로그인 하지 않은 상태에서는 반영되지 않습니다.");
			} else {
				alert("에러가 발생하였습니다. 관리자에게 문의 바랍니다.");
				fullCalendarRefresh();
			}
		}
	});
} // End of function

// 로그인
function submitLogin() {
	if (!checkLoginInput()) return;

	var formObj = document.loginForm;
	formObj.action = "/calendar/user/login";
	formObj.submit();
}

// 로그아웃
function userLogout() {
	if (!confirm('로그아웃 하시겠습니까?')) return;

	var formObj = document.loginForm;
	formObj.action = "/calendar/user/logout";
	formObj.submit();
}

// 등록
function submitRegister() {
	if (!checkLoginInput()) return;
	if (!confirm('등록 하시겠습니까?')) return;

	var formObj = document.loginForm;
	formObj.action = "/calendar/user/register";
	formObj.submit();
}

// 로그인 및 등록 input 체크
function checkLoginInput() {
	if (!$("input[name=memberid]").val()) {
		alert("아이디를 입력해주세요.");
		$("input[name=memberid]").focus();
		return;
	}

	if (!$("input[name=memberpw]").val()) {
		alert("비밀번호를 입력해주세요.");
		$("input[name=memberpw]").focus();
		return;
	}

	return true;
}

// 이벤트 박스 새로고침
function refreshEventBox() {
	location.reload();
}

/** 텍스트 컬러 수정 */
const colorSelectMeter = (that) => {
	$("#colorSelectMeter").attr("value", $("option:selected", that).attr("meter"));
}