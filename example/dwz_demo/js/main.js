$(document).ready(function(){
	console.log("ready");
	
	$("#fileUpload").fileupload({
		dataType: 'json',
		done: function(e, data) {
			console.log("upload done");
			console.log(data);
		},
		progressall: function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$("#progress .bar").css(
				'width',
				progress + '%'
			);
		}
	});
	
});
