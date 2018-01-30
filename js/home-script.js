/*<article class="grid-item content-box">
                
                <div class="content">
    
                    <img class="thumb" src="1.jpg">                    
                    <a class="header" href="#"><h1>Picture 1</h1></a>
                    <p class="description">'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil voluptatibus aliquam asperiores, cum distinctio aliquid recusandae velit beatae. Reprehenderit in eum, expedita, alias explicabo iure amet. Assumenda consequuntur vitae fugit.'</p>
                    <div class="location">
                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                        <span>Location</span>
                    </div>

                </div>
                
</article>
*/

//window.onload = function (e){
//    var token = getCookie('token');
//    if (token != ""){
//        document.getElementById('user-icon').style.display = "inline-block";
//        document.getElementById('user-icon').addEventListener('click', logOut, false);
//    }else{
//        document.getElementById('popup').onclick = "login.html";
//    }
//}
//
function logOut(){
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
    window.location.href = "index.html";
}
//
//function getCookie(cname) {
//    var name = cname + "=";
//    var ca = document.cookie.split(';');
//    for(var i = 0; i < ca.length; i++) {
//        var c = ca[i];
//        while (c.charAt(0) == ' ') {
//            c = c.substring(1);
//        }
//        if (c.indexOf(name) == 0) {
//            return c.substring(name.length, c.length);
//        }
//    }
//    return "";
//}

//Fetch img from images.json

var wrapper = document.getElementById('wrapper');
var showImages = function(arr) {
    
    
    for(var i = 0; i < arr.length; i++){
        
        
        
        var img = document.createElement('img');
        img.setAttribute('src', 'img/img-stream/' + arr[i].mediaUrl);
        img.className = 'thumb';
        
        
        var header = document.createTextNode(arr[i].mediaTitle);
        var h1 = document.createElement('h1');
        h1.appendChild(header);
        var a = document.createElement('a');
        a.className = 'header';
        a.setAttribute('href', '#');
        a.appendChild(h1)
        
        
        var description = document.createTextNode(arr[i].mediaDescription);
        var p = document.createElement('p');
        p.className = 'description';
        p.appendChild(description);
        
        var locationTag = document.createElement('div');
        locationTag.className = 'location';
        
        var icon = document.createElement('i');
        icon.className = 'fa';
        icon.className += ' fa-map-marker';
        icon.className += ' location';
        icon.setAttribute('aria-hidden', 'true');
        
        var span = document.createElement('span');
        var location = document.createTextNode(' ' + arr[i].mediaLocation);
        span.appendChild(location);
        
        locationTag.appendChild(icon);
        locationTag.appendChild(span);
        
        var div = document.createElement('div');
        div.className = 'content';
        div.appendChild(img);
        div.appendChild(a);
        div.appendChild(p);
        div.appendChild(locationTag);
        
        
        var article = document.createElement('article');
        article.className = 'grid-item';
        article.className += ' content-box';
        article.appendChild(div);
        
        wrapper.appendChild(article);
        
                
    }
}


fetch('images.json').then(function(response) {
    
    return response.json();
}).then(function(j) {
    
    showImages(j);
    
});


//Function To Display Popup
function div_show() {
    document.getElementById('popup-container').style.display = "block";
}

//Function to Hide Popup
function div_hide(){
    document.getElementById('popup-container').style.display = "none";
}

//Function for image upload

function imageUpload(){
    
    function init(){
        var fileSelect    = document.getElementById('file-upload'),
            fileDrag      = document.getElementById('file-drag'),
            next_button = document.getElementById("next-button");
        
        next_button.addEventListener('click', formSubmit, false);
        fileSelect.addEventListener('change', fileSelectHandler, false);

        var xhr = new XMLHttpRequest();
        if (xhr.upload) {
          // File Drop
          fileDrag.addEventListener('dragover', fileDragHover, false);
          fileDrag.addEventListener('dragleave', fileDragHover, false);
          fileDrag.addEventListener('drop', fileSelectHandler, false);
        }
    }
    
    function formSubmit(){
        document.getElementById('flex-box').submit();
    }
    
    function fileDragHover(e) {
        var fileDrag = document.getElementById('file-drag');

        e.stopPropagation();
        e.preventDefault();

        fileDrag.className = (e.type === 'dragover' ? 'hover' : 'modal-body file-upload');
    }

    function fileSelectHandler(e) {
        // Fetch FileList object
        var files = e.target.files || e.dataTransfer.files;
        
        // Cancel event and hover styling
        fileDragHover(e);

        // Process all File objects
        for (var i = 0, f; f = files[i]; i++) {
          parseFile(f);
          //uploadFile(f);
        }
    }
    
    //Set title for img
    function output(msg) {
        // Tilte
        var m = document.getElementById('messages');
        m.innerHTML = msg;
    }

    function parseFile(file) {
        console.log(file.name);
        output(
          '<strong>' + encodeURI(file.name) + '</strong>'
        );

        // var fileType = file.type;
        // console.log(fileType);
        var imageName = file.name;

        var isGood = (/\.(?=gif|jpg|png|jpeg)/gi).test(imageName);
        if (isGood) {
          document.getElementById('start').classList.add("hidden");
          document.getElementById('response').classList.remove("hidden");
          document.getElementById('notimage').classList.add("hidden");
          // Thumbnail Preview
          document.getElementById('file-image').classList.remove("hidden");
          document.getElementById('file-image').src = URL.createObjectURL(file);
        }
        else {
          document.getElementById('file-image').classList.add("hidden");
          document.getElementById('notimage').classList.remove("hidden");
          document.getElementById('start').classList.remove("hidden");
          document.getElementById('response').classList.add("hidden");
          document.getElementById("file-upload-form").reset();
        }
    }
    
    function uploadFile(file) {
        var xhr = new XMLHttpRequest(),
//        fileInput = document.getElementById('class-roster-file'),
//        pBar = document.getElementById('file-progress'),
        fileSizeLimit = 1024; // In MB
        if (xhr.upload) {
//        // Check if file is less than x MB
            if (file.size <= fileSizeLimit * 1024 * 1024) {
    //        // Progress bar
    //        pBar.style.display = 'inline';
    //        xhr.upload.addEventListener('loadstart', setProgressMaxValue, false);
    //        xhr.upload.addEventListener('progress', updateFileProgress, false);
    //
            // File received / failed
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                // Everything is good!

                // progress.className = (xhr.status == 200 ? "success" : "failure");
                // document.location.reload(true);
                }
            };

            // Start upload
            xhr.open('POST', 'Travelust/webresources/service/upload', true);
            xhr.setRequestHeader('X-File-Name', file.name);
            xhr.setRequestHeader('X-File-Size', file.size);
            xhr.setRequestHeader('Content-Type', 'multipart/form-data');
            xhr.send(file);
            } else {
                output('Please upload a smaller file (< ' + fileSizeLimit + ' MB).');
            }
        }
    }
    
    // Check for the various File API support.
    if (window.File && window.FileList && window.FileReader) {
        init();
    } else {
        document.getElementById('file-drag').style.display = 'none';
    }
}
imageUpload();