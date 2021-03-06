var COOKIE_NAME = 'optID'
var cookie = $.cookie(COOKIE_NAME)
$(document).ready(function() {

  $(".form-control:input").on('focusout', function() {
    if($.trim($(this).val()).length == 0) {
      $(this).css({'border':'1px #ff0000 solid'})
    } else {
      $(this).css({'border':''})
    }
  })

  $("#login_ok").click(function(){
    var isSubmit = true, i_index = 0, remember

    $("#i_remember").is(":checked") ? (remember = true) : (remember = false)

    $(this).find('.input').each(function(i){
      if($.trim($(this).val()).length == 0) {
        $(this).css('border', '1px #ff0000 solid')
        isSubmit = false
        if(i_index == 0) {
          i_index = i
        }
      }
    })
    if(!isSubmit) {
      $(this).find('.input').eq(i_index).focus()
      return false
    }
    /*if($("#i_remember").attr('checked')) {
     remember = true
     //$.cookie(COOKIE_NAME, $("#i_username").val(), { path : '/', expires : 15})
     } else {
     remember =
     //$.cookie(COOKIE_NAME, null, { path : '/' })
     }*/
    $.ajax({
      type:"post",
      data:{ username:$("#i_username").val(), password:$("#i_password").val(), remember:remember },
      url:"controller/login",
      async:true
    }).success(function(data, status, xhr){
      if (status == "success") {
        if (data.statusCode === "200") {
          //jump
          window.location.href = data.data.url
        } else if (data.statusCode === "300") {
          console.log("error")
          $("#login_info").html(data.message).css('color','red')
        }
      }
    });
  })

  if (cookie != null) {
    $("#i_username").val($.cookie(COOKIE_NAME))
  }

})
