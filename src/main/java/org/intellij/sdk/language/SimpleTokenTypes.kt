package org.intellij.sdk.language

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls


class SimpleTokenType(@NonNls debugName: String) : IElementType(debugName, SimpleLanguage1) {
    override fun toString(): String {
        return "SimpleTokenType." + super.toString()
    }

    companion object {
        val NEW_LINE = SimpleTokenType("NEW_LINE")
        val STRING = SimpleTokenType("STRING")
        val IDEN = SimpleTokenType("IDEN")
        val INT = SimpleTokenType("INT")
        val FUNK = SimpleTokenType("FUNK")
        val VAR = SimpleTokenType("VAR")
        val IF = SimpleTokenType("IF")
        val THEN = SimpleTokenType("THEN")
        val ELSSE = SimpleTokenType("ELSSE")
        val RETURN = SimpleTokenType("RUTURN")
        val WHILE = SimpleTokenType("WHILE")
        val LPAREN = SimpleTokenType("LPAREN")
        val RPAREN = SimpleTokenType("RPAREN")
        val SEMICOLON = SimpleTokenType("SEMICOLON")
        val LBRACE = SimpleTokenType("LBRACE")
        val RBRACE = SimpleTokenType("RBRACE")
        val COMMA = SimpleTokenType("COMMA")
        val EQ = SimpleTokenType("EQ")

//        val COMMENTS = TokenSet.create(COMMENT)
//        val STRINGS = TokenSet.create(STRING)
//        val LITERALS = TokenSet.create(STRING, INT, HEX)
//        val IDENTS = TokenSet.create(TID, SPID, CID, ID)
//        val ARROWS = TokenSet.create(ARROW, TARROW)
//		val ASSIGNMENTS = TokenSet.create(EQ, ASSIGN, FETCH)
//        val KEYWORDS = TokenSet.create(FORALL, BUILTIN, LIBRARY, IMPORT, LET, IN, MATCH, WITH, END, FUN,
//                TFUN, CONTRACT, TRANSITION, SEND, EVENT, FIELD, ACCEPT, EXISTS, DELETE, EMP, MAP,
//                SCILLA_VERSION, TYPE, OF, TRY, CATCH, AS, PROCEDURE, THROW)
    }

}

