<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

        <html>

            <head>

                <title>Spotify Feedback</title>

                <style>

                    body {
                        font-family: Arial, sans-serif;
                        background: #121212;
                        color: white;
                        padding: 40px;
                    }

                    h1 {
                        text-align: center;
                        color: #1db954;
                        margin-bottom: 30px;
                    }

                    table {
                        width: 90%;
                        margin: auto;
                        border-collapse: collapse;
                        background: #181818;
                    }

                    th {
                        background: #1db954;
                        color: black;
                        padding: 15px;
                        text-align: left;
                    }

                    td {
                        padding: 15px;
                        border-bottom: 1px solid #333;
                    }

                    tr:hover {
                        background: #282828;
                    }

                    .no-feedback {
                        text-align: center;
                        color: #b3b3b3;
                        padding: 30px;
                    }

                </style>

            </head>

            <body>

                <h1>Spotify Feedback</h1>

                <table>

                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Feedback</th>
                    </tr>

                    <xsl:choose>

                        <xsl:when test="feedback/user">

                            <xsl:for-each select="feedback/user">

                                <tr>

                                    <td>
                                        <xsl:value-of select="name"/>
                                    </td>

                                    <td>
                                        <xsl:value-of select="email"/>
                                    </td>

                                    <td>
                                        <xsl:value-of select="message"/>
                                    </td>

                                </tr>

                            </xsl:for-each>

                        </xsl:when>

                        <xsl:otherwise>

                            <tr>
                                <td colspan="3"
                                    class="no-feedback">
                                    No feedback available.
                                </td>
                            </tr>

                        </xsl:otherwise>

                    </xsl:choose>

                </table>

            </body>

        </html>

    </xsl:template>

</xsl:stylesheet>