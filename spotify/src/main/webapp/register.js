document.getElementById("registerForm").addEventListener("submit", function (e) {

    e.preventDefault();

    let name = document.getElementById("name").value.trim();

    let email = document.getElementById("email").value.trim();

    let password = document.getElementById("password").value;

    let confirmPassword = document.getElementById("confirmPassword").value;

    let age = document.getElementById("age").value;


    // Name validation
    if (name.length < 3) {

        alert("Name must contain at least 3 characters.");

        return;
    }


    // Email validation
    let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailPattern.test(email)) {

        alert("Invalid Email");

        return;
    }


    // Password validation
    if (password.length < 8) {

        alert("Password should contain at least 8 characters.");

        return;
    }


    // Confirm password validation
    if (password != confirmPassword) {

        alert("Passwords do not match.");

        return;
    }


    // Age validation
    if (age < 18) {

        alert("Age should be 18 or above.");

        return;
    }


    // =========================
    // AJAX REQUEST
    // =========================

    let xhr = new XMLHttpRequest();

    xhr.open("POST", "RegisterServlet", true);

    xhr.setRequestHeader(
        "Content-Type",
        "application/x-www-form-urlencoded"
    );


    xhr.onreadystatechange = function () {

        if (xhr.readyState === 4) {

			if (xhr.status === 200) {

			    if (xhr.responseText === "Registration Successful") {

			        alert("Registration Successful");

			        window.location.href = "confirmation.jsp";

			    } else {

			        alert(xhr.responseText);

			    }

			} else {

                alert("Error connecting to server.");

            }
        }
    };


    let data =
        "name=" + encodeURIComponent(name) +
        "&email=" + encodeURIComponent(email) +
        "&password=" + encodeURIComponent(password) +
        "&confirmPassword=" + encodeURIComponent(confirmPassword) +
        "&age=" + encodeURIComponent(age);


    xhr.send(data);

});