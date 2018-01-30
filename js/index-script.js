function checkCookie() {
    var token = getCookie("token");
    
    if (token != "") {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
              responseHandler(this.responseText);
            }
        };
        xhr.open("GET", "/Travelust/webresources/service/verifyToken", true);
        xhr.send();
    }else{
        responseHandler("");
    }
}

function responseHandler(res){
    if (res == "http://10.114.32.23:8080/home.html")
        window.location.href = res;   
    else{
        document.getElementById("body-content").style.display = "block";
        document.getElementById("body").style.backgroundImage = "url(../img/home_background5.jpg)";
    }
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
