package <%= packageName %>

import <%= packageName %>.ast.CompilationUnit
import <%= packageName %>.ast.HelloStmt
import org.junit.Test
import kotlin.test.assertEquals
import com.strumenta.kolasu.testing.assertASTsAreEqual

class <%= languageName %>KolasuParserTest {

    @Test
    fun parseExample1FromString() {
        val code = "hello John"
        val kolasuParser = <%= languageName %>KolasuParser()
        val result = kolasuParser.parse(code, considerPosition = false)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertASTsAreEqual(CompilationUnit(listOf(HelloStmt("John"))), result.root!!)
    }

    @Test
    fun parseExample1FromFile() {
        val kolasuParser = <%= languageName %>KolasuParser()
        val result = kolasuParser.parse(this.javaClass.getResourceAsStream("/example1.hello"),
                considerPosition = false)
        assertEquals(true, result.correct)
        assertEquals(true, result.issues.isEmpty())
        assertASTsAreEqual(CompilationUnit(listOf(HelloStmt("John"))), result.root!!)
    }
}