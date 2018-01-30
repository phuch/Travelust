//Search box responsive
var search_icon = document.getElementById("search-icon");
var search_box_res = document.getElementById("search-box-responsive");
var search_box = document.getElementById("search-box-test");
search_icon.onclick = function(){
    if (search_box_res.style.display == "none")
        search_box_res.style.display = "block";
    else
        search_box_res.style.display = "none";
}

window.onresize = function(){
    var windowWidth = window.outerWidth;
    if (windowWidth >= 680){
        search_box_res.style.display = "none";
    }
}