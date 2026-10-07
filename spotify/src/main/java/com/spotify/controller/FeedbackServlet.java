package com.spotify.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Eclipse project XML file
        String path =
            "C:\\Users\\CSE\\Documents\\spotify\\src\\main\\webapp\\xml\\feedback.xml";

        System.out.println("XML FILE PATH = " + path);

        File file = new File(path);

        String xml = new String(
                Files.readAllBytes(file.toPath()),
                StandardCharsets.UTF_8
        );

        String newUser =
                "    <user>\n" +
                "        <name>" + escapeXML(name) + "</name>\n" +
                "        <email>" + escapeXML(email) + "</email>\n" +
                "        <message>" + escapeXML(message) + "</message>\n" +
                "    </user>\n";

        xml = xml.replace(
                "</feedback>",
                newUser + "</feedback>"
        );

        Files.write(
                file.toPath(),
                xml.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.TRUNCATE_EXISTING
        );

        request.setAttribute(
                "successMessage",
                "Feedback submitted successfully!"
        );

        request.getRequestDispatcher("feedback.jsp")
               .forward(request, response);
    }

    private String escapeXML(String text) {

        if (text == null) {
            return "";
        }

        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&apos;");
    }
}