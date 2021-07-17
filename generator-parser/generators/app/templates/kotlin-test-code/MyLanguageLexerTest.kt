package <%= packageName %>

import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.ast.HelloStmt
import org.antlr.v4.runtime.CommonToken
import org.junit.Test
import kotlin.test.assertEquals

class <%= languageName %>LexerTest {

    @Test
    fun lexExample1FromString() {
        val code = "hello John"
        val kolasuParser = <%= languageName %>KolasuParser()
        val result = kolasuParser.lex(code)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertEquals(3, result.tokens.size)

        var i=0
        assertEquals(<%= languageName %>Lexer.HELLO, result.tokens[i].type)
        assertEquals("hello", result.tokens[i].text)

        i++
        assertEquals(<%= languageName %>Lexer.ID, result.tokens[i].type)
        assertEquals("John", result.tokens[i].text)

        i++
        assertEquals(<%= languageName %>Lexer.EOF, result.tokens[i].type)
    }

    @Test
    fun lexExample1FromFile() {
        val kolasuParser = <%= languageName %>KolasuParser()
        val result = kolasuParser.lex(this.javaClass.getResourceAsStream("/example1.hello"))
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertEquals(3, result.tokens.size)

        var i=0
        assertEquals(<%= languageName %>Lexer.HELLO, result.tokens[i].type)
        assertEquals("hello", result.tokens[i].text)

        i++
        assertEquals(<%= languageName %>Lexer.ID, result.tokens[i].type)
        assertEquals("John", result.tokens[i].text)

        i++
        assertEquals(<%= languageName %>Lexer.EOF, result.tokens[i].type)
    }
}