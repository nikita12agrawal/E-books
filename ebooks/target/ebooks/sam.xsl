<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>BookName:<xsl:value-of select="Book/@title"/></h2>
                <table>
                    <xsl:for-each select="Book/Chunk">
                        <tr><xsl:value-of select="@title"/></tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>