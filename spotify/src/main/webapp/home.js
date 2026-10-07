/**
 * 
 */
 function getCookie(name){

    let cookies=document.cookie.split(";");

    for(let i=0;i<cookies.length;i++){

        let cookie=cookies[i].trim();

        if(cookie.startsWith(name+"=")){

            return cookie.substring(name.length+1);

        }

    }

    return "";

}

function deleteCookie(name){

    document.cookie=name+"=;max-age=0;path=/";

}

const banners = [
    "https://picsum.photos/id/1015/1200/350",
    "https://picsum.photos/id/1018/1200/350",
    "https://picsum.photos/id/1021/1200/350"
];

let currentBanner = 0;

setInterval(function () {

    currentBanner++;

    if (currentBanner >= banners.length) {
        currentBanner = 0;
    }

    let slider = document.getElementById("slider");

    if (slider) {
        slider.src = banners[currentBanner];
    }

}, 3000);


// ===================================
// Dynamic Songs
// ===================================

const songs = [

    {
        name: "Shape Of You",
        image: "https://picsum.photos/200?1"
    },

    {
        name: "Believer",
        image: "https://picsum.photos/200?2"
    },

    {
        name: "Perfect",
        image: "https://picsum.photos/200?3"
    },

    {
        name: "Levitating",
        image: "https://picsum.photos/200?4"
    },

    {
        name: "Blinding Lights",
        image: "https://picsum.photos/200?5"
    },

    {
        name: "Faded",
        image: "https://picsum.photos/200?6"
    }

];

displaySongs(songs);


// ===================================
// Dynamic Content Display
// ===================================

function displaySongs(songArray) {

    let container = document.getElementById("songs");

    if (!container) return;

    let output = "";

    songArray.forEach(function (song) {

        output += `

        <div class="card">

            <img src="${song.image}" alt="${song.name}">

            <h3>${song.name}</h3>

            <button onclick="playSong('${song.name}')">

                ▶ Play

            </button>

        </div>

        `;

    });

    container.innerHTML = output;

}


// ===================================
// Play Song
// ===================================

function playSong(song) {

    let playing = document.getElementById("playing");

    if (playing) {

        playing.innerHTML = "🎶 Now Playing : " + song;

    }

    alert(song + " is now playing!");

}


// ===================================
// Search Songs
// ===================================

let search = document.getElementById("searchInput");

if (search) {

    search.addEventListener("keyup", function () {

        let keyword = this.value.toLowerCase();

        let filtered = songs.filter(function (song) {

            return song.name.toLowerCase().includes(keyword);

        });

        displaySongs(filtered);

    });

}


// ===================================
// Theme Toggle
// ===================================

let themeBtn = document.getElementById("themeBtn");

if (themeBtn) {

    themeBtn.addEventListener("click", function () {

        document.body.classList.toggle("light-theme");

    });

}


// ===================================
// Prompt
// ===================================

window.onload = function () {

    let username = prompt("Welcome!\nEnter your name:");

    if (username != null && username.trim() !== "") {

        let heading = document.querySelector(".welcome h1");

        if (heading) {

            heading.innerHTML = "Welcome " + username + " 🎵";

        }

    }

    // Display Cookies in Console
    console.log("Cookies:");
    console.log(document.cookie);

};

// ===================================
// DOM Manipulation Example
// ===================================

let title = document.querySelector(".sidebar h2");

if (title) {

    title.style.color = "#1DB954";

    title.style.fontWeight = "bold";

}


// ===================================
// Display Cookies
// ===================================

function showCookies() {

    console.log("Stored Cookies:");

    console.log(document.cookie);

}

showCookies();
// ===================================
// Logout Button
// ===================================

let logoutBtn = document.getElementById("logoutBtn");

if (logoutBtn) {

    logoutBtn.addEventListener("click", function () {

        let answer = confirm("Do you want to logout?");

        if (answer) {

            alert("Logged Out Successfully");

            window.location.href = "LogoutServlet";

        }

    });

}

// ===================================
// Delete Cookie Button
// ===================================

let deleteBtn = document.getElementById("deleteBtn");

if (deleteBtn) {

    deleteBtn.addEventListener("click", function () {

        let answer = confirm("Do you want to delete the visit cookie?");

        if (answer) {

            alert("Cookie Deleted Successfully");

            window.location.href = "DeleteCookieServlet";

        }

    });

}
// ================================
// Session Timeout
// ================================

let timeout;

function resetTimer() {

    clearTimeout(timeout);

    timeout = setTimeout(function () {

        window.location.href = "sessionExpired.jsp";

    }, 120000); // 10 seconds (change to 120000 for 2 minutes)

}

document.addEventListener("mousemove", resetTimer);
document.addEventListener("keypress", resetTimer);
document.addEventListener("click", resetTimer);
document.addEventListener("scroll", resetTimer);

resetTimer();