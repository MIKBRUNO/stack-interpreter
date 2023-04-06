package interpreter.tests;

import interpreter.*;

import interpreter.exceptions.InterpreterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A command parsing tests")
class InterpreterMainTests {

    private final RuntimeContext Context = new RuntimeContext(new PrintStream(OutputStream.nullOutputStream()));

    @BeforeAll
    @DisplayName("Checking if CommandFactory loads correctly")
    static void testFactoryInit() {
        Assertions.assertDoesNotThrow(CommandFactory::initFactory);
    }

    @Test
    @DisplayName("EXIT command check")
    void testExitCommand() {
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("EXIT").doAction(Context)),
                () -> assertFalse(Context.isRunning)
        );
    }

    @Test
    @DisplayName("DEFINE command check")
    void testDefineCommand() {
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("DEFINE df 13.31").doAction(Context)),
                () -> assertEquals(Context.getDefinitions().get("df"), 13.31)
        );
    }

    @Test
    @DisplayName("PUSH command check")
    void testPushCommand() {
        Context.getDefinitions().put("df", 100500.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("PUSH df").doAction(Context)),
                () -> assertEquals(Context.getDataStack().peek(), 100500.)
        );
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("PUSH 12.4").doAction(Context)),
                () -> assertEquals(Context.getDataStack().peek(), 12.4)
        );
    }

    @Test
    @DisplayName("POP command check")
    void testPopCommand() {
        Context.getDataStack().push(12.3);
        Context.getDataStack().push(32.1);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("POP").doAction(Context)),
                () -> assertEquals(12.3, Context.getDataStack().peek())
        );
    }

    @Test
    @DisplayName("DIV command check")
    void testDivCommand() {
        Context.getDataStack().push(2.);
        Context.getDataStack().push(4.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("/").doAction(Context)),
                () -> assertEquals(2., Context.getDataStack().peek())
        );
    }

    @Test
    @DisplayName("PLUS command check")
    void testPlusCommand() {
        Context.getDataStack().push(2.);
        Context.getDataStack().push(4.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("+").doAction(Context)),
                () -> assertEquals(6., Context.getDataStack().peek())
        );
    }

    @Test
    @DisplayName("MINUS command check")
    void testMinusCommand() {
        Context.getDataStack().push(2.);
        Context.getDataStack().push(4.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("-").doAction(Context)),
                () -> assertEquals(2., Context.getDataStack().peek())
        );
    }

    @Test
    @DisplayName("MUL command check")
    void testMulCommand() {
        Context.getDataStack().push(2.);
        Context.getDataStack().push(4.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("*").doAction(Context)),
                () -> assertEquals(Context.getDataStack().peek(), 8.)
        );
    }

    @Test
    @DisplayName("SQRT command check")
    void testSqrtCommand() {
        Context.getDataStack().push(4.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("SQRT").doAction(Context)),
                () -> assertEquals(Context.getDataStack().peek(), 2.)
        );
    }

    @Test
    @DisplayName("PRINT command check")
    void testPrintCommand() {
        Context.getDataStack().push(4.);
        assertAll(
                () -> assertDoesNotThrow(() -> CommandParser.parse("PRINT").doAction(Context))
        );
    }

    @Test
    @DisplayName("Empty line check")
    void testEmptyLine() {
        assertAll(
                () -> assertNull(CommandParser.parse(""))
        );
    }

    @Test
    @DisplayName("A comment line check")
    void testComment() {
        assertAll(
                () -> assertNull(CommandParser.parse("# hello world"))
        );
    }

    @Test
    @DisplayName("Empty stack exception check")
    void testEmptyStack() {
        Context.getDataStack().empty();
        assertAll(
                () -> assertThrows(InterpreterException.class, () -> CommandParser.parse("SQRT").doAction(Context)),
                () -> assertThrows(InterpreterException.class, () -> CommandParser.parse("PLUS").doAction(Context))
        );
    }

    @Test
    @DisplayName("Binary command empty stack exception")
    void testEmptyStackBinaryCommand() {
        Context.getDataStack().empty();
        Context.getDataStack().push(12.);
        assertAll(
                () -> assertThrows(InterpreterException.class, () -> CommandParser.parse("PLUS").doAction(Context))
        );
    }

    @Test
    @DisplayName("File program test")
    void testFileInterpreter() {
        PipedInputStream pipeInput = new PipedInputStream();
        BufferedReader reader = new BufferedReader( new InputStreamReader(pipeInput) );
        try (InputStream in = InterpreterMainTests.class.getResourceAsStream("testfile.txt");
             PrintStream out = new PrintStream( new PipedOutputStream(pipeInput) )) {
            if (in == null)
                fail("Cannot open testfile.txt");
            else {
                Interpreter interpreter = new Interpreter(in, out, out);
                interpreter.run();
                assertEquals("-1200.0", reader.readLine());
            }
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

}

