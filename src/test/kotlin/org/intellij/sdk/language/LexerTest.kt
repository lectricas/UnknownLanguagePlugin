package org.intellij.sdk.language

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class LexerTest : LexerTestCase() {
    override fun createLexer(): Lexer {
        return SimpleLexerAdapter()
    }

    fun testAllTokens() {
        val text = "if then else while return funk var ; , = ( ) { } \"str\" 12"
        doTest(
            text, """
            SimpleTokenType.IF ('if')
            WHITE_SPACE (' ')
            SimpleTokenType.THEN ('then')
            WHITE_SPACE (' ')
            SimpleTokenType.ELSSE ('else')
            WHITE_SPACE (' ')
            SimpleTokenType.WHILE ('while')
            WHITE_SPACE (' ')
            SimpleTokenType.RETURN ('return')
            WHITE_SPACE (' ')
            SimpleTokenType.FUNK ('funk')
            WHITE_SPACE (' ')
            SimpleTokenType.VAR ('var')
            WHITE_SPACE (' ')
            SimpleTokenType.SEMICOLON (';')
            WHITE_SPACE (' ')
            SimpleTokenType.COMMA (',')
            WHITE_SPACE (' ')
            SimpleTokenType.EQ ('=')
            WHITE_SPACE (' ')
            SimpleTokenType.LPAREN ('(')
            WHITE_SPACE (' ')
            SimpleTokenType.RPAREN (')')
            WHITE_SPACE (' ')
            SimpleTokenType.LBRACE ('{')
            WHITE_SPACE (' ')
            SimpleTokenType.RBRACE ('}')
            WHITE_SPACE (' ')
            SimpleTokenType.STRING ('"str"')
            WHITE_SPACE (' ')
            SimpleTokenType.INT ('12')
        """.trimIndent()
        )
    }

    fun testAssignment() {
        val text = "var a = 12;"
        doTest(
            text, """
            SimpleTokenType.VAR ('var')
            WHITE_SPACE (' ')
            SimpleTokenType.IDEN ('a')
            WHITE_SPACE (' ')
            SimpleTokenType.EQ ('=')
            WHITE_SPACE (' ')
            SimpleTokenType.INT ('12')
            SimpleTokenType.SEMICOLON (';')
        """.trimIndent()
        )
    }

    fun testIf() {
        val text = "if (a == 15) then {\n" +
                "    var m = 1 + 2 < 3;\n" +
                "} else {\n" +
                "    var n = 1 * 2 + 4 > 3;\n" +
                "}"
        doTest(
            text, """
                SimpleTokenType.IF ('if')
                WHITE_SPACE (' ')
                SimpleTokenType.LPAREN ('(')
                SimpleTokenType.IDEN ('a')
                WHITE_SPACE (' ')
                SimpleTokenType.EQUAL_EQUAL ('==')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('15')
                SimpleTokenType.RPAREN (')')
                WHITE_SPACE (' ')
                SimpleTokenType.THEN ('then')
                WHITE_SPACE (' ')
                SimpleTokenType.LBRACE ('{')
                WHITE_SPACE ('\n    ')
                SimpleTokenType.VAR ('var')
                WHITE_SPACE (' ')
                SimpleTokenType.IDEN ('m')
                WHITE_SPACE (' ')
                SimpleTokenType.EQ ('=')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('1')
                WHITE_SPACE (' ')
                SimpleTokenType.PLUS ('+')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('2')
                WHITE_SPACE (' ')
                SimpleTokenType.LESS ('<')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('3')
                SimpleTokenType.SEMICOLON (';')
                WHITE_SPACE ('\n')
                SimpleTokenType.RBRACE ('}')
                WHITE_SPACE (' ')
                SimpleTokenType.ELSE ('else')
                WHITE_SPACE (' ')
                SimpleTokenType.LBRACE ('{')
                WHITE_SPACE ('\n    ')
                SimpleTokenType.VAR ('var')
                WHITE_SPACE (' ')
                SimpleTokenType.IDEN ('n')
                WHITE_SPACE (' ')
                SimpleTokenType.EQ ('=')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('1')
                WHITE_SPACE (' ')
                SimpleTokenType.STAR ('*')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('2')
                WHITE_SPACE (' ')
                SimpleTokenType.PLUS ('+')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('4')
                WHITE_SPACE (' ')
                SimpleTokenType.GREATER ('>')
                WHITE_SPACE (' ')
                SimpleTokenType.INT ('3')
                SimpleTokenType.SEMICOLON (';')
                WHITE_SPACE ('\n')
                SimpleTokenType.RBRACE ('}')
""".trimIndent()
        )
    }

    override fun getDirPath(): String {
        return ""
    }
}