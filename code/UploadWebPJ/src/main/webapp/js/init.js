$(function() {
  BJUI.init({
    JSPATH      : 'BJUI/',
    PLUGINPATH  : 'BJUI/plugins/',
    statusCode  : {ok:200, error:300, timeout:301},
    ajaxTimeout : 50000,
    ui          : {
      windowWidth      : 0,
      showSlidebar     : true,
      clientPaging     : true,
      overwriteHomeTab : false,
    },
    debug       : true
  })
  
  $(document).on('click', 'ul.menu-items > li > a', function(e) {
    console.log(e)
  })
  
  //时间刷新
  var today = new Date()
  $("#bjui-date").html(today.formatDate('yyyy/MM/dd'))
  setInterval(function() {
    today.setSeconds(today.getSeconds() + 1)
    $("#bjui-clock").html(today.formatDate('HH:mm:ss'))
    }, 1000)
})

//菜单-单击事件
function MainMenuClick(event, treeId, treeNode) {
  event.preventDefault()
  
  if (treeNode.isParent) {
    var zTree = $.fn.zTree.getZTreeObj(treeId)
    zTree.expandNode(treeNode, !treeNode.open, false, true, true)
    return
  }
  
  if (treeNode.target && treeNode.target == 'dialog')
    $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name})
  else
    $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external})
}