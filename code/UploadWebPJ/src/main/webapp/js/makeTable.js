var jsonString
var tds = ""

function makeTable(dataUrl) {
  getJson(dataUrl)
  var jsonData = JSON.parse(jsonString)
  
  if (jsonData.statusCode == "200") {
    console.log(jsonData.data)
    users = jsonData.data[0]
    jsonData.data.forEach(makeRow)
    /*$(".table-body")*/
  } else {
    $(this).bjuiajax("ajaxDone", jsonData)
    return
  }
  
}

function getJson(url) {
  xhr = new XMLHttpRequest()
  url += ((/\?/).test(url) ? "&" : "?") + (new Date()).getTime()
  xhr.open("GET", url, false)
  xhr.onload = function() {
    jsonString = xhr.responseText
  }
  xhr.send()
}

function makeRow(row) {
  $("<tr>", {"data-id" : row["id"]}).appendTo(".table-body")
  for (var prop in row) {
    $("<td>").html(row[prop]).appendTo(".table-body tr:last")
  }
}

