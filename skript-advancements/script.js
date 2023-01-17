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
            if (data[i].creator == "2270") {
                let typeTitle = data[i].syntax_type.charAt(0).toUpperCase() + data[i].syntax_type.slice(1);
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
                document.getElementById("elements").insertAdjacentHTML("beforeend", "<div id='content' style='background-color:" + colorForBackground + ";'><h2 id='title'>" + data[i].title + "</h2><h2 id='type'>" + typeTitle + "</h2><p id='description'>" + data[i].description + "</p><p id='syntax' style='background-color:" + colorForSyntax + ";'>" + data[i].syntax_pattern.replace("\n", "<br>") + "</p></div>")
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
          document.getElementById("elements").insertAdjacentHTML("beforeend", "<div id='content' style='background-color:" + colorForBackground + ";'><h2 id='title'>" + jsonData[i].title + "</h2><h2 id='type'>" + typeTitle + "</h2><p id='description'>" + jsonData[i].description + "</p><p id='syntax' style='background-color:" + colorForSyntax + ";'>" + jsonData[i].syntax_pattern.replace("\n", "<br>") + "</p></div>")
        }
      }
    }
    for (var i = 0; i < jsonData.length; i++) {
      if (jsonData[i].creator == "2270") {
        if (jsonData[i].description.toLowerCase().includes(str.toLowerCase())) {
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
          document.getElementById("elements").insertAdjacentHTML("beforeend", "<div id='content' style='background-color:" + colorForBackground + ";'><h2 id='title'>" + jsonData[i].title + "</h2><h2 id='type'>" + typeTitle + "</h2><p id='description'>" + jsonData[i].description + "</p><p id='syntax' style='background-color:" + colorForSyntax + ";'>" + jsonData[i].syntax_pattern.replace("\n", "<br>") + "</p></div>")
        }
      }
    }
    if (document.getElementById("elements").innerHTML == "") {
      document.getElementById("loading").textContent = "No Results Found!"
    }
}

/*var darkToggled = false;

function setMode(darkT) {
  if (!darkT) {
    document.body.style.backgroundColor = "white"
    document.getElementById("header").style.backgroundColor = "white"
    for (var i = 0; i < document.getElementsByTagName("p").length; i++) {
      document.getElementsByTagName("p")[i].style.color = "black"
      document.getElementsByTagName("p")[i].style.transition = "color 0.1s ease, text-decoration-color 0.1s ease"
    }
    for (var i = 0; i < document.getElementsByTagName("span").length; i++) {
      document.getElementsByTagName("span")[i].style.color = "black"
      document.getElementsByTagName("span")[i].style.textDecorationColor = "black"
      document.getElementsByTagName("span")[i].style.transition = "color 0.1s ease, text-decoration-color 0.1s ease"
    }
    document.getElementById("logo").style.color = "black"
    document.getElementById("header").style.boxShadow = "0 0 50px lightgray"
    if (document.getElementById("caption") != null) {
      document.getElementById("caption").style.color = "white"
    }
  } else {
    document.body.style.backgroundColor = "rgb(26, 39, 46)"
    document.body.style.transition = "background-color 0.3s ease"
    document.getElementById("header").style.backgroundColor = "rgb(26, 39, 46)"
    for (var i = 0; i < document.getElementsByTagName("p").length; i++) {
      if (document.getElementsByTagName("p")[i].id != "description" && document.getElementsByTagName("p")[i].id != "syntax") {
        document.getElementsByTagName("p")[i].style.color = "white"
        document.getElementsByTagName("p")[i].style.transition = "color 0.1s ease"
      }
    }
    for (var i = 0; i < document.getElementsByTagName("span").length; i++) {
      document.getElementsByTagName("span")[i].style.color = "white"
      document.getElementsByTagName("span")[i].style.textDecorationColor = "white"
      document.getElementsByTagName("span")[i].style.transition = "color 0.1s ease, text-decoration-color 0.1s ease"
    }
    document.getElementById("logo").style.color = "white"
    document.getElementById("header").style.boxShadow = "0 0 50px black"
    if (document.getElementById("caption") != null) {
      document.getElementById("caption").style.color = "white"
    }
  }
  darkToggled = darkT
}

function getToggled() {
  return darkToggled;
}*/

document.onkeyup = function(evt) {
  if (document.activeElement.id == "search") {
    filterSyntax(document.activeElement.value)
  }
}