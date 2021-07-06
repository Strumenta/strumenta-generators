package <%= packageName %>.ast

import com.strumenta.kolasu.model.*

data class CompilationUnit(
    val helloStmts: List<HelloStmt>,
    override val specifiedPosition: Position?
) : Node(specifiedPosition)

data class HelloStmt(
    name: String,
    override val specifiedPosition: Position?
) : Node(specifiedPosition)
