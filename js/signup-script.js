var inputFName = document.getElementById("fname");
inputFName.addEventListener('invalid', function(e) {
    if(inputFName.validity.valueMissing){
        e.target.setCustomValidity("Please fill in your first name");
    }
    // to avoid the 'sticky' invalid problem when resuming typing after getting a custom invalid message
    inputFName.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

var inputLName = document.getElementById("lname");
inputLName.addEventListener('invalid', function(e) {
    if(inputLName.validity.valueMissing){
        e.target.setCustomValidity("Please fill in your last name");
    }
    // to avoid the 'sticky' invalid problem when resuming typing after getting a custom invalid message
    inputLName.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

var inputDOB = document.getElementById("bday");
inputDOB.addEventListener('invalid', function(e) {
    if(inputDOB.validity.valueMissing){
        e.target.setCustomValidity("Please choose your date of birth"); 
    }
    // to avoid the 'sticky' invlaid problem when resuming typing after getting a custom invalid message
    inputDOB.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

var inputEmail = document.getElementById("email");
inputEmail.addEventListener('invalid', function(e) {
    if(inputEmail.validity.valueMissing){
        e.target.setCustomValidity("Please choose your email"); 
    }
    // to avoid the 'sticky' invlaid problem when resuming typing after getting a custom invalid message
    inputEmail.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

var inputPW = document.getElementById("pword");
inputPW.addEventListener('invalid', function(e) {
    if(inputPW.validity.valueMissing){
        e.target.setCustomValidity("Please fill in your password"); 
    }
    // to avoid the 'sticky' invlaid problem when resuming typing after getting a custom invalid message
    inputPW.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

var inputConfPW = document.getElementById("conf-pword");
inputConfPW.addEventListener('invalid', function(e) {
    if(inputConfPW.validity.valueMissing){
        e.target.setCustomValidity("Please fill in your password again"); 
    }
    // to avoid the 'sticky' invlaid problem when resuming typing after getting a custom invalid message
    inputConfPW.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

inputConfPW.addEventListener('keyup', function(e) {
    if(inputConfPW.value != inputPW.value){
        e.target.setCustomValidity("Password does not match"); 
    }
    // to avoid the 'sticky' invlaid problem when resuming typing after getting a custom invalid message
    inputConfPW.addEventListener('input', function(e){
        e.target.setCustomValidity('');
    });
}, false);

var inputTerm = document.getElementById("term-cond");
inputTerm.addEventListener('invalid', function(e) {
    if(inputTerm.validity.valueMissing){
        e.target.setCustomValidity("You haven't agree to the Terms & Conditions of the service");
    }
    // to avoid the 'sticky' invlaid problem when resuming typing after getting a custom invalid message
    inputTerm.addEventListener('click', function(e){
        e.target.setCustomValidity('');
    });
}, false);
