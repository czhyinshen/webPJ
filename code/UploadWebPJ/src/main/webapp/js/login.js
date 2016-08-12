var COOKIE_NAME = 'username'
var cookie = $.cookie(COOKIE_NAME)
$(document).ready(function() {
  
  $(".input-box input").on('focusout', function() {    
    if($.trim($(this).val()).length == 0) {
      $(this).css({'border':'1px #ff0000 solid', 'box-shadow':'#999 0px 2px 8px'})
    } else {
      $(this).css({'border':'', 'box-shadow':''})
    }
  })
  
  $("#login_form").submit(function(){
    var isSubmit = true
    var i_index = 0
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
    if($("#i_remember").attr('checked')) {
      console.log("save cookie")
      $.cookie(COOKIE_NAME, $("#i_username").val(), { path : '/', expires : 15})
    } else {
      $.cookie(COOKIE_NAME, null, { path : '/' })
    }
  })
  
  if (cookie != null) {
    $("#i_username").val($.cookie(COOKIE_NAME))
  }
  
})
