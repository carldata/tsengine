package carldata.sf.compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.Vector;

/**
 * This is the only way to get error messages from ANTLR parser.
 */
class SyntaxErrorListener extends BaseErrorListener{

    private Vector<String> errors = new Vector<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        errors.add(msg);
    }

    public Vector<String> getErrors() { return errors; }
}
