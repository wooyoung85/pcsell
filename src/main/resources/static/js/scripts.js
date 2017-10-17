String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

//에디터 
$('.edit').trumbowyg({
	lang : 'kr'
});

$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	var queryString = $(".answer-write").serialize();
	var url = $(".answer-write").attr("action");

	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : "JSON",
		cache : false,
		success : function(data, status){
			var answerTemplate = $("#answerTemplate").html();
			var template = answerTemplate.format(data.writer.id, data.writer.userId, '방금', data.contents, data.question.id, data.id);
			$('.qna-comment-slipp-articles').prepend($(template).hide().fadeIn(1000));
			$('#txtAnswerContents').val('').attr('placeholder', '답변 작성');
		},
		error : function(){console.log('error');}
	});
}

$('.link-modify-article').click(function(){
	updateAnswer($(this).parent().find('.hdQuestionId').val(), $(this).parent().find('.hdId').val(), $(this).parent().find('.hdContents').val());
	return false;
});
function updateAnswer(questionId, id, contents){
	var updateAnswerTemplate = $("#updateAnswerTemplate").html();
	var template = updateAnswerTemplate.format(questionId, id, contents);
	$('.qna-comment-slipp').html($(template).hide().fadeIn(1000));
}

$('.link-delete-article').click(function() {
	var delBtn = $(this);
	var url = delBtn.attr('href');
	delAnswer(delBtn,url);
	return false;
});

function delAnswer(btn, url){
	var delBtn = $(btn);
	var url = url;
	$.ajax({
		type : 'delete',
		url : url,
		dataType : "JSON",
		cache : false,
		success : function(data, status) {
			if (data.valid) {
				delBtn.closest("article").fadeOut(1500);
			} else {
				alert(data.errorMessage);
			}
		},
		error : function(){console.log('error');}
	});
}