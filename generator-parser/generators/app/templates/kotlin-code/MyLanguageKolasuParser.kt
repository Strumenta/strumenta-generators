package <%= packageName %>

import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.parsetreetoast.toAst
import com.strumenta.kolasu.parsing.KolasuParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.TokenStream
import java.io.InputStream

class <%= languageName %>KolasuParser : KolasuParser<CompilationUnit, <%= languageName %>Parser, <%= languageName %>Parser.CompilationUnitContext>() {

    override fun createANTLRLexer(inputStream: InputStream): Lexer {
        return <%= languageName %>Lexer(CharStreams.fromStream(inputStream))
    }

    override fun createANTLRParser(tokenStream: TokenStream): <%= languageName %>Parser {
        return <%= languageName %>Parser(tokenStream)
    }

    override fun invokeRootRule(parser: <%= languageName %>Parser): <%= languageName %>Parser.CompilationUnitContext? {
        return parser.compilationUnit()
    }

    override fun parseTreeToAst(parseTreeRoot: <%= languageName %>Parser.CompilationUnitContext, considerPosition: Boolean): CompilationUnit? {
        return parseTreeRoot.toAst(considerPosition)
    }
}

