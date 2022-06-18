package org.intellij.sdk.language

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.lang.ASTNode
import com.intellij.psi.StubBasedPsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement

abstract class ScillaPsiElement(node: ASTNode) : ASTWrapperPsiElement(node) {
	override fun getContainingFile(): SimpleFile? = super.getContainingFile() as? SimpleFile
}

abstract class ScillaStubElement<S : StubElement<*>> : StubBasedPsiElementBase<S>, StubBasedPsiElement<S> {
	constructor(node: ASTNode) : super(node)
	constructor(stub: S, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

	override fun toString(): String {
		return javaClass.simpleName + "(" + elementType.toString() + ")"
	}
}

class SimpleElement(node: ASTNode) : ScillaPsiElement(node)