package com.spotify.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@WebServlet("/feedback5")
public class Feedback5Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        // -------------------------------------------------
        // GET SESSION
        // -------------------------------------------------

        HttpSession session =
                request.getSession(false);


        if (session == null) {

            response.sendRedirect("login.jsp");

            return;
        }


        // -------------------------------------------------
        // GET LOGGED-IN USER
        // -------------------------------------------------

        String username =
                (String) session.getAttribute("username");


        if (username == null ||
            username.trim().isEmpty()) {

            response.sendRedirect("login.jsp");

            return;
        }


        // -------------------------------------------------
        // GET 5 USER INPUTS
        // -------------------------------------------------

        String song =
                request.getParameter("song");

        String rating =
                request.getParameter("rating");

        String comment =
                request.getParameter("comment");

        String recommendation =
                request.getParameter("recommendation");

        String category =
                request.getParameter("category");


        try {


            // =================================================
            // FILE PATHS
            // =================================================

        	String xmlPath =
        	        "C:\\Users\\CSE\\Documents\\spotify\\src\\main\\webapp\\xml\\feedback5.xml";

            String xsdPath =
                    getServletContext()
                    .getRealPath(
                            "/xml/feedback5.xsd"
                    );


            // =================================================
            // READ XML DATABASE
            // =================================================

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();


            DocumentBuilder builder =
                    factory.newDocumentBuilder();


            Document document =
                    builder.parse(
                            new File(xmlPath)
                    );


            Element root =
                    document.getDocumentElement();


            // =================================================
            // CREATE NEW FEEDBACK
            // =================================================

            Element feedback =
                    document.createElement(
                            "feedback"
                    );


            // Username from login session

            Element usernameElement =
                    document.createElement(
                            "username"
                    );

            usernameElement.setTextContent(
                    username
            );


            // Song

            Element songElement =
                    document.createElement(
                            "song"
                    );

            songElement.setTextContent(
                    song
            );


            // Rating

            Element ratingElement =
                    document.createElement(
                            "rating"
                    );

            ratingElement.setTextContent(
                    rating
            );


            // Comment

            Element commentElement =
                    document.createElement(
                            "comment"
                    );

            commentElement.setTextContent(
                    comment
            );


            // Recommendation

            Element recommendationElement =
                    document.createElement(
                            "recommendation"
                    );

            recommendationElement.setTextContent(
                    recommendation
            );


            // Category

            Element categoryElement =
                    document.createElement(
                            "category"
                    );

            categoryElement.setTextContent(
                    category
            );


            // =================================================
            // ADD DATA TO FEEDBACK
            // =================================================

            feedback.appendChild(
                    usernameElement
            );

            feedback.appendChild(
                    songElement
            );

            feedback.appendChild(
                    ratingElement
            );

            feedback.appendChild(
                    commentElement
            );

            feedback.appendChild(
                    recommendationElement
            );

            feedback.appendChild(
                    categoryElement
            );


            root.appendChild(
                    feedback
            );


            // =================================================
            // XSD VALIDATION
            // =================================================

            SchemaFactory schemaFactory =
                    SchemaFactory.newInstance(
                            XMLConstants
                            .W3C_XML_SCHEMA_NS_URI
                    );


            Schema schema =
                    schemaFactory.newSchema(
                            new File(xsdPath)
                    );


            schema.newValidator().validate(
                    new DOMSource(document)
            );


            // =================================================
            // SAVE XML DATABASE
            // =================================================

            TransformerFactory
                    transformerFactory =
                    TransformerFactory
                    .newInstance();


            Transformer saveTransformer =
                    transformerFactory
                    .newTransformer();


            saveTransformer.setOutputProperty(
                    OutputKeys.INDENT,
                    "yes"
            );


            saveTransformer.transform(
                    new DOMSource(document),
                    new StreamResult(
                            new File(xmlPath)
                    )
            );


            XPathFactory xpathFactory =
                    XPathFactory.newInstance();


            XPath xpath =
                    xpathFactory.newXPath();


            String expression =
                    "/feedbacks/feedback[rating >= 3]/rating";


            NodeList result =
                    (NodeList) xpath.evaluate(
                            expression,
                            document,
                            XPathConstants.NODESET
                    );



            System.out.println(
                    "Number of ratings >= 3: "
                    + result.getLength()
            );




            response.sendRedirect(
                    "feedback5"
            );

        }

        catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Feedback Error</h2>"
            );

            response.getWriter().println(
                    "<p>" +
                    e.getMessage() +
                    "</p>"
            );

        }

    }


    // =====================================================
    // GET
    // DISPLAY ONLY SUMMARY
    // =====================================================

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {


            // =================================================
            // FILE PATHS
            // =================================================

            String xmlPath =
                    getServletContext()
                    .getRealPath(
                            "/xml/feedback5.xml"
                    );


            String xslPath =
                    getServletContext()
                    .getRealPath(
                            "/xml/feedback5.xsl"
                    );


            // =================================================
            // RESPONSE
            // =================================================

            response.setContentType(
                    "text/html;charset=UTF-8"
            );


            // =================================================
            // XSLT TRANSFORMATION
            // =================================================

            TransformerFactory
                    transformerFactory =
                    TransformerFactory
                    .newInstance();


            Transformer transformer =
                    transformerFactory
                    .newTransformer(
                            new StreamSource(
                                    new File(xslPath)
                            )
                    );


            // =================================================
            // HTML HEADER
            // =================================================

            response.getWriter().println(
                    "<!DOCTYPE html>"
            );


            response.getWriter().println(
                    "<html>"
            );


            response.getWriter().println(
                    "<head>"
            );


            response.getWriter().println(
                    "<meta charset='UTF-8'>"
            );


            response.getWriter().println(
                    "<title>" +
                    "Spotify Feedback Summary" +
                    "</title>"
            );


            // =================================================
            // CSS
            // =================================================

            response.getWriter().println(
                    "<style>"
            );


            response.getWriter().println(

                "body{" +
                "margin:0;" +
                "background:#121212;" +
                "color:white;" +
                "font-family:Arial,sans-serif;" +
                "padding:40px;" +
                "}"
            );


            response.getWriter().println(

                "h1{" +
                "color:#1DB954;" +
                "text-align:center;" +
                "margin-bottom:35px;" +
                "}"
            );


            response.getWriter().println(

                ".summary-container{" +
                "background:#181818;" +
                "padding:25px;" +
                "border-radius:15px;" +
                "}"
            );


            response.getWriter().println(

                "h2{" +
                "color:#1DB954;" +
                "margin-top:0;" +
                "}"
            );


            response.getWriter().println(

                "table{" +
                "width:100%;" +
                "border-collapse:collapse;" +
                "margin-top:20px;" +
                "background:#181818;" +
                "}"
            );


            response.getWriter().println(

                "th{" +
                "background:#1DB954;" +
                "color:black;" +
                "padding:14px;" +
                "}"
            );


            response.getWriter().println(

                "td{" +
                "padding:14px;" +
                "text-align:center;" +
                "border-bottom:1px solid #333;" +
                "}"
            );


            response.getWriter().println(

                "tr:hover{" +
                "background:#282828;" +
                "}"
            );


            response.getWriter().println(

                ".rating{" +
                "color:#1DB954;" +
                "font-weight:bold;" +
                "}"
            );


            response.getWriter().println(

                ".back{" +
                "display:block;" +
                "width:220px;" +
                "margin:30px auto 0;" +
                "padding:12px;" +
                "background:#1DB954;" +
                "color:black;" +
                "text-align:center;" +
                "text-decoration:none;" +
                "border-radius:25px;" +
                "font-weight:bold;" +
                "}"
            );


            response.getWriter().println(
                    "</style>"
            );


            response.getWriter().println(
                    "</head>"
            );


            response.getWriter().println(
                    "<body>"
            );


            // =================================================
            // PAGE TITLE
            // =================================================

            response.getWriter().println(

                "<h1>" +
                "Spotify Feedback Summary" +
                "</h1>"
            );


            // =================================================
            // XML → XSLT → HTML
            // =================================================

            transformer.transform(

                    new StreamSource(
                            new File(xmlPath)
                    ),

                    new StreamResult(
                            response.getWriter()
                    )
            );


            // =================================================
            // BACK TO FEEDBACK FORM
            // =================================================

            response.getWriter().println(

                "<a class='back' " +
                "href='feedback5.jsp'>" +
                "Write Another Feedback" +
                "</a>"
            );


            response.getWriter().println(
                    "</body>"
            );


            response.getWriter().println(
                    "</html>"
            );


        }

        catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );


            response.getWriter().println(
                    "<h2>Error</h2>"
            );


            response.getWriter().println(
                    "<p>" +
                    e.getMessage() +
                    "</p>"
            );

        }

    }

}