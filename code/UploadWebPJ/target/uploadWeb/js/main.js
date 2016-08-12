var COOKIE_NAME = 'username';
$(document).ready(function() {
  checkCookie();
});
function checkCookie() {
  console.log("check cookie");
  if ($.cookie(COOKIE_NAME) != null) {
    console.log("get username");
    $("#username").html($.cookie(COOKIE_NAME) + " - " + $("#username").html());
  }
}