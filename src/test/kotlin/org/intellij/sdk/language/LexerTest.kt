package org.intellij.sdk.language

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase
import org.intellij.sdk.language.SimpleLexerAdapter

class LexerTest : LexerTestCase() {
    override fun createLexer(): Lexer {
        return SimpleLexerAdapter()
    }

    fun testErrorToken() {
        val text = "if then else while return funk var ; , = ( ) { } \"str\" 12"
        doTest(text, """
            SimpleTokenType.IF ('if')
            WHITE_SPACE (' ')
            SimpleTokenType.THEN ('then')
            WHITE_SPACE (' ')
            SimpleTokenType.ELSSE ('else')
            WHITE_SPACE (' ')
            SimpleTokenType.WHILE ('while')
            WHITE_SPACE (' ')
            SimpleTokenType.RUTURN ('return')
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
        """.trimIndent())
    }

    override fun getDirPath(): String {
        return ""
    }
}