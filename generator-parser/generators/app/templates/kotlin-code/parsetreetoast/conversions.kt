package <%= packageName %>.parsetreetoast

import <%= packageName %>.<%= languageName %>Parser
import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.ast.HelloStmt
import com.strumenta.kolasu.mapping.toPosition

fun <%= languageName %>Parser.CompilationUnitContext.toAst(considerPosition: Boolean = true) : CompilationUnit {
    return CompilationUnit(this.helloStmt().map { it.toAst(considerPosition) }, toPosition(considerPosition))
}

fun <%= languageName %>Parser.HelloStmtContext.toAst(considerPosition: Boolean = true) : HelloStmt {
    return HelloStmt(this.name.text, toPosition(considerPosition))
}
