package org.intellij.sdk.language

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NonNls


class SimpleTokenType(@NonNls debugName: String) : IElementType(debugName, SimpleLanguage) {
    override fun toString(): String {
        return "SimpleTokenType." + super.toString()
    }

    companion object {
        val NEW_LINE = SimpleTokenType("NEW_LINE")
        val COMMENT = SimpleTokenType("STRING")
        val STRING = SimpleTokenType("STRING")
        val IDEN = SimpleTokenType("IDEN")
        val INT = SimpleTokenType("INT")
        val FUNK = SimpleTokenType("FUNK")
        val VAR = SimpleTokenType("VAR")
        val IF = SimpleTokenType("IF")
        val THEN = SimpleTokenType("THEN")
        val ELSE = SimpleTokenType("ELSE")
        val RETURN = SimpleTokenType("RETURN")
        val WHILE = SimpleTokenType("WHILE")
        val LPAREN = SimpleTokenType("LPAREN")
        val RPAREN = SimpleTokenType("RPAREN")
        val SEMICOLON = SimpleTokenType("SEMICOLON")
        val LBRACE = SimpleTokenType("LBRACE")
        val RBRACE = SimpleTokenType("RBRACE")
        val COMMA = SimpleTokenType("COMMA")
        val EQ = SimpleTokenType("EQ")
        val OR = SimpleTokenType("OR")
        val AND = SimpleTokenType("AND")
        val BANG_EQUAL = SimpleTokenType("BANG_EQUAL")
        val EQUAL_EQUAL = SimpleTokenType("EQUAL_EQUAL")
        val GREATER = SimpleTokenType("GREATER")
        val GREATER_EQUAL = SimpleTokenType("GREATER_EQUAL")
        val LESS = SimpleTokenType("LESS")
        val LESS_EQUAL = SimpleTokenType("LESS_EQUAL")
        val PLUS = SimpleTokenType("PLUS")
        val MINUS = SimpleTokenType("MINUS")
        val SLASH = SimpleTokenType("SLASH")
        val STAR = SimpleTokenType("STAR")
        val BANG = SimpleTokenType("BANG")
        val TRUE = SimpleTokenType("TRUE")
        val FALSE = SimpleTokenType("FALSE")

        val STRINGS = TokenSet.create(STRING)
        val COMMENTS = TokenSet.create(COMMENT)
    }

}

