document.getElementById("loginForm").addEventListener("submit", function(e){

    let email = document.getElementById("loginEmail").value.trim();
    let password = document.getElementById("loginPassword").value;

    if(email==""){
        alert("Enter Email");
        e.preventDefault();
        return;
    }

    if(password==""){
        alert("Enter Password");
        e.preventDefault();
        return;
    }

});