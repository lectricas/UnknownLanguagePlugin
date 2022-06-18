package org.intellij.sdk.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.intellij.sdk.language.SimpleTokenType.Companion.BANG
import org.intellij.sdk.language.SimpleTokenType.Companion.BANG_EQUAL
import org.intellij.sdk.language.SimpleTokenType.Companion.COMMA
import org.intellij.sdk.language.SimpleTokenType.Companion.ELSE
import org.intellij.sdk.language.SimpleTokenType.Companion.EQ
import org.intellij.sdk.language.SimpleTokenType.Companion.EQUAL_EQUAL
import org.intellij.sdk.language.SimpleTokenType.Companion.FALSE
import org.intellij.sdk.language.SimpleTokenType.Companion.FUNK
import org.intellij.sdk.language.SimpleTokenType.Companion.GREATER
import org.intellij.sdk.language.SimpleTokenType.Companion.GREATER_EQUAL
import org.intellij.sdk.language.SimpleTokenType.Companion.IDEN
import org.intellij.sdk.language.SimpleTokenType.Companion.IF
import org.intellij.sdk.language.SimpleTokenType.Companion.INT
import org.intellij.sdk.language.SimpleTokenType.Companion.LBRACE
import org.intellij.sdk.language.SimpleTokenType.Companion.LESS
import org.intellij.sdk.language.SimpleTokenType.Companion.LESS_EQUAL
import org.intellij.sdk.language.SimpleTokenType.Companion.LPAREN
import org.intellij.sdk.language.SimpleTokenType.Companion.MINUS
import org.intellij.sdk.language.SimpleTokenType.Companion.PLUS
import org.intellij.sdk.language.SimpleTokenType.Companion.RBRACE
import org.intellij.sdk.language.SimpleTokenType.Companion.RETURN
import org.intellij.sdk.language.SimpleTokenType.Companion.RPAREN
import org.intellij.sdk.language.SimpleTokenType.Companion.SEMICOLON
import org.intellij.sdk.language.SimpleTokenType.Companion.SLASH
import org.intellij.sdk.language.SimpleTokenType.Companion.STAR
import org.intellij.sdk.language.SimpleTokenType.Companion.STRING
import org.intellij.sdk.language.SimpleTokenType.Companion.THEN
import org.intellij.sdk.language.SimpleTokenType.Companion.TRUE
import org.intellij.sdk.language.SimpleTokenType.Companion.VAR
import org.intellij.sdk.language.SimpleTokenType.Companion.WHILE


class SimpleParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        InnerParser1(builder).declaration()
        return builder.treeBuilt
    }

    internal inner class InnerParser1(private val builder: PsiBuilder) {
        fun declaration() {
            try {
                if (builder.tokenType == FUNK) return function()
                if (builder.tokenType == VAR) return varDeclaration()
                return statement()
            } catch (error: Exception) {
                error.printStackTrace()
            }
        }

        private fun statement() {
            if (builder.tokenType == IF) return ifStatement()
            if (builder.tokenType == RETURN) return returnStatement()
            if (builder.tokenType == WHILE) return whileStatement()
            if (builder.tokenType == LBRACE) return block()
            return expressionStatement()
        }

        fun whileStatement() {
            val whilee = builder.mark()
            assertAdvance(WHILE)
            assertAdvance(LPAREN)
            expression()
            assertAdvance(RPAREN)
            statement()
            whilee.done(ScillaElementType.WHILE)
        }

        fun ifStatement() {
            val iff = builder.mark()
            assertAdvance(IF)
            assertAdvance(LPAREN)
            expression()
            assertAdvance(RPAREN)
            assertAdvance(THEN)
            statement()
            if (match(ELSE)) {
                statement()
            }
            iff.done(ScillaElementType.IF)
        }

        fun returnStatement() {
            val returnn = builder.mark()
            if (!check(SEMICOLON)) {
                expression()
            }
            returnn.done(ScillaElementType.RETURN)
        }

        private fun expressionStatement() {
            expression()
            assertAdvance(SEMICOLON)
        }

        private fun advance(): IElementType? {
            val result = builder.tokenType
            builder.advanceLexer()
            while (builder.tokenType == TokenType.BAD_CHARACTER) {
                val badMark = builder.mark()
                builder.advanceLexer()
                badMark.error("Unexpected character")
            }
            return result
        }

        private fun function() {
            val function = builder.mark()
            assertAdvance(FUNK)
            assertAdvance(IDEN)
            assertAdvance(LPAREN)
            if (!check(LPAREN)) {
                val paramListMarker = builder.mark()
                do {
                    assertAdvance(IDEN)
                    //parsing parameter list
                } while (match(COMMA))
                paramListMarker.done(ScillaElementType.FUNCTION_PARAMETERS)
            }
            assertAdvance(RPAREN)
            block()
            function.done(ScillaElementType.FUN_EXPRESSION)
        }

        private fun block() {
            val block = builder.mark()
            assertAdvance(LBRACE)
            while (!check(RBRACE)) {
                declaration()
            }
            assertAdvance(RBRACE)
            block.done(ScillaElementType.BLOCK)
        }

        private fun varDeclaration() {
            val variable = builder.mark()
            assertAdvance(VAR)
            assertAdvance(IDEN)
            if (match(EQ)) {
                expression()
            }
            assertAdvance(SEMICOLON)
            variable.done(ScillaElementType.VAR_DECLARATION)
        }

        private fun expression() {
            assignment()
        }

        private fun assignment() {
            or()
        }

        private fun or() {
            val or = builder.mark()
            and()
            while (match(SimpleTokenType.OR)) {
                and()
            }
            or.done(ScillaElementType.OR)
        }

        private fun and() {
            val and = builder.mark()
            equality()
            while (match(SimpleTokenType.AND)) {
                equality()
            }
            and.done(ScillaElementType.AND)
        }

        private fun equality() {
            val eq = builder.mark()
            comparison()
            while (match(BANG_EQUAL, EQUAL_EQUAL)) {
                comparison()
            }
            eq.done(ScillaElementType.EQ)
        }

        private fun comparison() {
            val comparation = builder.mark()
            term()
            while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
                term()
            }
            comparation.done(ScillaElementType.COMP)
        }

        private fun term() {
            val term = builder.mark()
            factor()
            while (match(MINUS, PLUS)) {
                factor()
            }
            term.done(ScillaElementType.TERM)
        }

        private fun factor() {
            val factor = builder.mark()
            unary()
            while (match(SLASH, STAR)) {
                unary()
            }
            factor.done(ScillaElementType.FACTOR)
        }

        private fun unary() {
            val unary = builder.mark()
            if (match(BANG, MINUS)) {
                unary()
            }
            unary.done(ScillaElementType.UNARY)
            return call()
        }

        private fun call() {
            primary()
            val call = builder.mark()
            while (true) {
                if (match(LPAREN)) {
                    finishCall()
                    //> Classes parse-property
                } else {
                    break
                }
            }
            call.done(ScillaElementType.CALL)
        }

        private fun finishCall() {
            if (!check(LPAREN)) {
                do {
                    //arguments
                } while (match(COMMA))
            }
            assertAdvance(RPAREN)
        }

        private fun primary() {
            val primary = builder.mark()
            println("text = ${builder.tokenText}")
            match(FALSE)
            match(TRUE)
            match(INT)
            match(STRING)
            match(IDEN)
            primary.done(ScillaElementType.PRIMARY)
            if (match(LPAREN)) {
                expression()
                assertAdvance(RPAREN)
            }
        }


        private fun assertAdvance(tt: IElementType) {
            assert(builder.tokenType == tt) {
                println(builder.tokenType.toString())
            }
            advance()
        }

        private fun match(vararg types: SimpleTokenType): Boolean {
            for (type in types) {
                if (check(type)) {
                    advance()
                    return true
                }
            }
            return false
        }

        private fun check(type: SimpleTokenType): Boolean {
            return builder.tokenType == type
        }
    }
}

open class SimpleParserDefinition : ParserDefinition {

    val FILE = IFileElementType(SimpleLanguage)

    override fun createLexer(project: Project?): Lexer {
        return SimpleLexerAdapter()
    }

    override fun createParser(project: Project?): PsiParser {
        return SimpleParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getCommentTokens(): TokenSet {
        return SimpleTokenType.COMMENTS // TODO: 17.06.2022 unsupported
    }

    override fun getStringLiteralElements(): TokenSet {
        return SimpleTokenType.STRINGS
    }

    override fun createElement(node: ASTNode): PsiElement {
        return ScillaElementType.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return SimpleFile(viewProvider)
    }
}