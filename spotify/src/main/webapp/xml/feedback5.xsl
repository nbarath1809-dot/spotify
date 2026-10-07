<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet
    version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output
        method="html"
        encoding="UTF-8"
        indent="yes"/>

    <xsl:template match="/">

        <div class="summary-container">

            <h2>Complete Feedback Summary</h2>

            <table>

                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Song</th>
                        <th>Rating</th>
                        <th>Comment</th>
                        <th>Recommendation</th>
                        <th>Category</th>
                    </tr>
                </thead>

                <tbody>

                    <!--
                        XPath condition:
                        Show only feedback where rating >= 3
                    -->

                    <xsl:for-each
                        select="feedbacks/feedback[rating &gt;= 3]">

                        <tr>

                            <td>
                                <xsl:value-of select="username"/>
                            </td>

                            <td>
                                <xsl:value-of select="song"/>
                            </td>

                            <td class="rating">
                                ⭐
                                <xsl:value-of select="rating"/>
                            </td>

                            <td>
                                <xsl:value-of select="comment"/>
                            </td>

                            <td>
                                <xsl:value-of select="recommendation"/>
                            </td>

                            <td>
                                <xsl:value-of select="category"/>
                            </td>

                        </tr>

                    </xsl:for-each>

                </tbody>

            </table>

        </div>

    </xsl:template>

</xsl:stylesheet>