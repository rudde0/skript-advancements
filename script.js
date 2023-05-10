var getJSON = function(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
      var status = xhr.status;
      if (status === 200) {
        callback(null, xhr.response);
      } else {
        callback(status, xhr.response);
      }
    };
    xhr.send();
};

var jsonData;

function loadSyntax() {
    getJSON('https://skripthub.net/api/v1/addonsyntaxlist/',
    function(err, data) {
        var count = 0;
        jsonData = data;
        for (var i = 0; i < data.length; i++) {
            if (data[i].addon.name == "skript-advancements") {
                let typeTitle = data[i].syntax_type.charAt(0).toUpperCase() + data[i].syntax_type.slice(1);
                let colorForBackground = "rgb(221, 221, 221)"
                let colorForSyntax = "rgb(199, 199, 199)"
                if (typeTitle == "Type") {
                  colorForBackground = "orange"
                } else if (typeTitle == "Expression") {
                  colorForBackground = "mediumseagreen"
                } else if (typeTitle == "Effect") {
                  colorForBackground = "dodgerblue"
                } else if (typeTitle == "Condition") {
                  colorForBackground = "rgb(223, 75, 75)"
                } else if (typeTitle == "Section") {
                  colorForBackground = "lightseagreen"
                } else if (typeTitle == "Event") {
                  colorForBackground = "mediumorchid"
                }
                document.getElementById("elements").insertAdjacentHTML("beforeend", "<div id='content' style='border-left: 7px solid " + colorForBackground + ";'><h3 id='title'>" + typeTitle + " | " + data[i].title + "</h3><p id='syntax' style='background-color: rgb(235, 235, 235);'>" + data[i].syntax_pattern.replace("\n", "<br>") + "</p><p id='description'>" + data[i].description + "</p></div>")
            }
        }
      });
}

function filterSyntax(str) {
  document.getElementById("elements").innerHTML = ""
    for (var i = 0; i < jsonData.length; i++) {
      if (jsonData[i].creator == "2270") {
        if (jsonData[i].title.toLowerCase().includes(str.toLowerCase())) {
          let typeTitle = jsonData[i].syntax_type.charAt(0).toUpperCase() + jsonData[i].syntax_type.slice(1);
          let colorForBackground = "rgb(221, 221, 221)"
          let colorForSyntax = "rgb(199, 199, 199)"
          if (typeTitle == "Type") {
             colorForBackground = "orange"
             colorForSyntax = "rgb(228, 148, 0)"
          } else if (typeTitle == "Expression") {
            colorForBackground = "rgb(55, 204, 122)"
            colorForSyntax = "rgb(51, 184, 111)"
          } else if (typeTitle == "Effect") {
            colorForBackground = "rgb(0, 174, 255)"
            colorForSyntax = "rgb(0, 156, 228)"
          } else if (typeTitle == "Condition") {
            colorForBackground = "rgb(223, 75, 75)"
            colorForSyntax = "rgb(201, 67, 67)"
          } else if (typeTitle == "Section") {
            colorForBackground = "rgb(55, 209, 183)"
            colorForSyntax = "rgb(51, 192, 169)"
          } else if (typeTitle == "Event") {
            colorForBackground = "rgb(224, 95, 214)"
            colorForSyntax = "rgb(201, 87, 191)"
          }
          document.getElementById("elements").insertAdjacentHTML("beforeend", "<div id='content' style='background-color:" + colorForBackground + ";'><h2 id='title'>" + typeTitle + " | " + jsonData[i].title + "</h2><p id='description'>" + jsonData[i].description + "</p><p id='syntax' style='background-color:" + colorForSyntax + ";'>" + jsonData[i].syntax_pattern.replace("\n", "<br>") + "</p></div>")        }
      }
    }
    if (document.getElementById("elements").innerHTML == "") {
      document.getElementById("loading").textContent = "No Results Found!"
    }
}

document.onkeyup = function(evt) {
  if (document.activeElement.id == "search") {
    filterSyntax(document.activeElement.value)
  }
}
