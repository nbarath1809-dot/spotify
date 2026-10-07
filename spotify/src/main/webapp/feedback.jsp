<!DOCTYPE html>
<html>
<head>
    <title>Spotify Feedback</title>

    <style>
        body {
            font-family: Arial;
            background: #f2f2f2;
            padding: 40px;
        }

        .container {
            width: 400px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 8px 0 15px;
            box-sizing: border-box;
        }

        textarea {
            height: 120px;
        }

        button {
            width: 100%;
            padding: 10px;
            cursor: pointer;
        }
        .success-message {
		    background: #1db954;
		    color: black;
		    padding: 12px;
		    border-radius: 6px;
		    text-align: center;
		    margin-bottom: 20px;
		    font-weight: bold;
		}
    </style>
</head>

<body>

<div class="container">

    <h2>Spotify Feedback</h2>
    
    <%
    String successMessage =
        (String) request.getAttribute("successMessage");

    if (successMessage != null) {
%>

    <div class="success-message">
        <%= successMessage %>
    </div>

<%
    }
%>

    <form action="FeedbackServlet" method="post">

        <label>Name</label>
        <input type="text" name="name" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Feedback</label>
        <textarea name="message"
                  placeholder="Write your feedback..."
                  required></textarea>

        <button type="submit">Submit Feedback</button>

    </form>

</div>

</body>
</html>