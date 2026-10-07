<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.Cookie" %>

<%
Cookie[] cookies = request.getCookies();

int visitCount = 1;

if (cookies != null) {

    for (Cookie c : cookies) {

        if ("visitCount".equals(c.getName())) {

            visitCount = Integer.parseInt(c.getValue()) + 1;

            break;
        }
    }
}

Cookie visitCookie = new Cookie(
    "visitCount",
    String.valueOf(visitCount)
);

visitCookie.setMaxAge(60 * 60 * 24);

visitCookie.setPath(request.getContextPath());

response.addCookie(visitCookie);

String username = (String) session.getAttribute("username");

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Spotify Home</title>

<link rel="stylesheet" href="home.css">
```

</head>

<body>

<div class="container">

```
<!-- Sidebar -->

<div class="sidebar">

    <h2>Spotify</h2>

    <ul>

        <li>Home</li>

        <li>Search</li>

        <li>Library</li>

        <li>Playlist</li>

        <li>
            <a href="store.jsp"
               class="menu-link"
               style="color:white;text-decoration:none;">
                Premium Store
            </a>
        </li>

        <li>
            <a href="PurchaseHistoryServlet"
               class="menu-link"
               style="color:white;text-decoration:none;">
                Purchase History
            </a>
        </li>

        <!-- Feedback Option -->

        <li>
            <a href="feedback.jsp"
               class="menu-link"
               style="color:white;text-decoration:none;">
                Feedback
            </a>
        </li>
        <li>
			<a href="feedback5.jsp"
               class="menu-link"
               style="color:white;text-decoration:none;">
                Feedback5
            </a>	
        </li>

    </ul>

</div>


<!-- Main Content -->

<div class="main-content">

    <div class="top-header">

        <div class="search-box">

            <input type="text"
                   id="searchInput"
                   placeholder="Search Songs">

        </div>


        <div class="header-right">

            <button id="themeBtn">☀</button>


            <div class="profile">

                <p>
                    Welcome
                    <b><%=username%></b>
                </p>


                <p>
                    <strong>Number of Visits :</strong>
                    <%=visitCount%>
                </p>


                <div class="button-group">

                    <button id="logoutBtn"
                            class="logout-btn">
                        Logout
                    </button>


                    <button id="deleteBtn"
                            class="delete-btn">
                        Delete Cookie
                    </button>

                </div>

            </div>

        </div>

    </div>


    <!-- Banner -->

    <div class="banner">

        <img id="slider"
             src="https://picsum.photos/id/1015/1200/350">

    </div>


    <!-- Welcome Section -->

    <div class="welcome">

        <h1>
            Welcome <%=username%> 🎵
        </h1>

        <p>
            Enjoy your favourite music.
        </p>

        <p>
            Currently Logged In Users :
            <%=com.spotify.controller.ActiveUsers.getCount()%>
        </p>

    </div>


    <!-- Popular Songs -->

    <h2>Popular Songs</h2>

    <div id="songs"
         class="song-grid">
    </div>


    <h2 id="playing"></h2>

</div>
```

</div>

<script src="home.js"></script>

</body>

</html>
