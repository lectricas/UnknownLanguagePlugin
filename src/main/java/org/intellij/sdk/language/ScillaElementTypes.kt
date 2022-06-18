package org.intellij.sdk.language

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls


class ScillaElementType(@NonNls debugName: String) : IElementType(debugName, SimpleLanguage) {
    companion object {
        fun createElement(node: ASTNode): PsiElement {
            return SimpleElement(node)
//            return when (node.elementType) {
//                else -> throw IllegalArgumentException("Unknown elementType: " + node.elementType)
//            }
        }

        val VAR_DECLARATION = ScillaElementType("VAR_DECLARATION")
        val BLOCK = ScillaElementType("BLOCK")
        val IF = ScillaElementType("IF")
        val RETURN = ScillaElementType("RETURN")
        val UNARY = ScillaElementType("UNARY")
        val CALL = ScillaElementType("CALL")
        val PRIMARY = ScillaElementType("CALL")
        val OR = ScillaElementType("OR")
        val AND = ScillaElementType("AND")
        val EQ = ScillaElementType("EQ")
        val COMP = ScillaElementType("EQ")
        val TERM = ScillaElementType("TERM")
        val FACTOR = ScillaElementType("TERM")
        val WHILE = ScillaElementType("WHILE")
        val STATEMENT = ScillaElementType("STATEMENT")
        val FUNCTION_PARAMETERS = ScillaElementType("FUNCTION_PARAMETER_LIST")
        val FUN_EXPRESSION = ScillaElementType("FUN_EXPRESSION")
        val IDENTIFIER = ScillaElementType("ID_WITH_TYPE")
    }
}


