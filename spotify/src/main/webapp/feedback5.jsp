<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String username = (String) session.getAttribute("username");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>

    <title>Spotify Feedback</title>

    <style>

        body {
            margin: 0;
            background: #121212;
            color: white;
            font-family: Arial, sans-serif;
        }

        .container {
            width: 520px;
            margin: 45px auto;
            background: #181818;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0 5px 25px black;
        }

        h1 {
            color: #1DB954;
            text-align: center;
            margin-bottom: 10px;
        }

        .welcome {
            text-align: center;
            color: #b3b3b3;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 18px;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input,
        textarea,
        select {
            width: 100%;
            box-sizing: border-box;
            padding: 12px;
            border: none;
            border-radius: 7px;
            background: #282828;
            color: white;
            font-size: 14px;
        }

        textarea {
            height: 100px;
            resize: vertical;
        }

        .rating {
            display: flex;
            gap: 8px;
        }

        .rating input {
            display: none;
        }

        .rating label {
            margin: 0;
            width: 50px;
            padding: 10px;
            text-align: center;
            background: #282828;
            border-radius: 7px;
            cursor: pointer;
            color: white;
        }

        .rating input:checked + label {
            background: #1DB954;
            color: black;
        }

        button {
            width: 100%;
            margin-top: 28px;
            padding: 14px;
            border: none;
            border-radius: 25px;
            background: #1DB954;
            color: black;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #1ed760;
        }

        .summary {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #1DB954;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Spotify Feedback</h1>

    <div class="welcome">
        Feedback by <b><%= username %></b>
    </div>

    <form action="feedback5" method="post">

        <!-- INPUT 1 -->
        <label>Song Name</label>

        <input type="text"
               name="song"
               placeholder="Enter song name"
               required>


        <!-- INPUT 2 -->
        <label>Rating</label>

        <div class="rating">

            <input type="radio"
                   id="r1"
                   name="rating"
                   value="1"
                   required>
            <label for="r1">⭐ 1</label>

            <input type="radio"
                   id="r2"
                   name="rating"
                   value="2">
            <label for="r2">⭐ 2</label>

            <input type="radio"
                   id="r3"
                   name="rating"
                   value="3">
            <label for="r3">⭐ 3</label>

            <input type="radio"
                   id="r4"
                   name="rating"
                   value="4">
            <label for="r4">⭐ 4</label>

            <input type="radio"
                   id="r5"
                   name="rating"
                   value="5">
            <label for="r5">⭐ 5</label>

        </div>


        <!-- INPUT 3 -->
        <label>Feedback / Comment</label>

        <textarea name="comment"
                  placeholder="Write your feedback..."
                  required></textarea>


        <!-- INPUT 4 -->
        <label>Would you recommend this song?</label>

        <select name="recommendation" required>

            <option value="">Select</option>
            <option value="Yes">Yes</option>
            <option value="No">No</option>

        </select>


        <!-- INPUT 5 -->
        <label>Feedback Category</label>

        <select name="category" required>

            <option value="">Select Category</option>
            <option value="Music Quality">Music Quality</option>
            <option value="Audio Quality">Audio Quality</option>
            <option value="Application Experience">Application Experience</option>
            <option value="Search">Search</option>
            <option value="Playlist">Playlist</option>
            <option value="Other">Other</option>

        </select>


        <button type="submit">
            Submit Feedback
        </button>

    </form>


    <a class="summary" href="feedback5">
        View Feedback Summary
    </a>

</div>

</body>
</html>