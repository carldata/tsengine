// Generated from /home/klangner/workspaces/scala/flow-script/src/main/scala/carldata/sf/compiler/FlowScript.g4 by ANTLR 4.7
package carldata.sf.compiler.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FlowScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, DEF=7, EXTERNAL=8, MODULE=9, 
		TRUE=10, FALSE=11, PLUS=12, MINUS=13, AND=14, OR=15, NEG=16, IF=17, THEN=18, 
		ELSE=19, WS=20, LINE_COMMENT=21, Identifier=22, QuotedString=23, Integer=24, 
		AddOp=25, MultiplyOp=26, RelationOp=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "NEG", "IF", "THEN", "ELSE", 
		"WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", "AddOp", 
		"MultiplyOp", "RelationOp", "Letter", "LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'", "'+'", "'-'", "'&&'", "'||'", "'!'", 
		"'if'", "'then'", "'else'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "NEG", "IF", "THEN", "ELSE", 
		"WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", "AddOp", 
		"MultiplyOp", "RelationOp"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FlowScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FlowScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00ca\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\6\25\u0085\n\25\r\25\16\25\u0086\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\7\26\u008f\n\26\f\26\16\26\u0092\13\26\3\26\3\26\3\27"+
		"\3\27\7\27\u0098\n\27\f\27\16\27\u009b\13\27\3\30\3\30\7\30\u009f\n\30"+
		"\f\30\16\30\u00a2\13\30\3\30\3\30\3\30\3\30\3\30\7\30\u00a9\n\30\f\30"+
		"\16\30\u00ac\13\30\3\30\5\30\u00af\n\30\3\31\6\31\u00b2\n\31\r\31\16\31"+
		"\u00b3\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\5\34\u00c3\n\34\3\35\3\35\3\36\3\36\3\37\3\37\2\2 \3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\2;\2=\2\3\2\t\5\2\13\f\16"+
		"\17\"\"\4\2\f\f\17\17\4\2--//\4\2,,\61\61\4\2>>@@\6\2&&C\\aac|\7\2&&\62"+
		";C\\aac|\2\u00d2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\3?\3\2\2\2\5A\3\2\2\2\7C\3\2\2\2\tE\3\2\2\2\13G\3\2\2\2\rI\3\2\2\2\17"+
		"K\3\2\2\2\21O\3\2\2\2\23X\3\2\2\2\25_\3\2\2\2\27d\3\2\2\2\31j\3\2\2\2"+
		"\33l\3\2\2\2\35n\3\2\2\2\37q\3\2\2\2!t\3\2\2\2#v\3\2\2\2%y\3\2\2\2\'~"+
		"\3\2\2\2)\u0084\3\2\2\2+\u008a\3\2\2\2-\u0095\3\2\2\2/\u00ae\3\2\2\2\61"+
		"\u00b1\3\2\2\2\63\u00b5\3\2\2\2\65\u00b7\3\2\2\2\67\u00c2\3\2\2\29\u00c4"+
		"\3\2\2\2;\u00c6\3\2\2\2=\u00c8\3\2\2\2?@\7*\2\2@\4\3\2\2\2AB\7+\2\2B\6"+
		"\3\2\2\2CD\7<\2\2D\b\3\2\2\2EF\7?\2\2F\n\3\2\2\2GH\7.\2\2H\f\3\2\2\2I"+
		"J\7\60\2\2J\16\3\2\2\2KL\7f\2\2LM\7g\2\2MN\7h\2\2N\20\3\2\2\2OP\7g\2\2"+
		"PQ\7z\2\2QR\7v\2\2RS\7g\2\2ST\7t\2\2TU\7p\2\2UV\7c\2\2VW\7n\2\2W\22\3"+
		"\2\2\2XY\7o\2\2YZ\7q\2\2Z[\7f\2\2[\\\7w\2\2\\]\7n\2\2]^\7g\2\2^\24\3\2"+
		"\2\2_`\7V\2\2`a\7t\2\2ab\7w\2\2bc\7g\2\2c\26\3\2\2\2de\7H\2\2ef\7c\2\2"+
		"fg\7n\2\2gh\7u\2\2hi\7g\2\2i\30\3\2\2\2jk\7-\2\2k\32\3\2\2\2lm\7/\2\2"+
		"m\34\3\2\2\2no\7(\2\2op\7(\2\2p\36\3\2\2\2qr\7~\2\2rs\7~\2\2s \3\2\2\2"+
		"tu\7#\2\2u\"\3\2\2\2vw\7k\2\2wx\7h\2\2x$\3\2\2\2yz\7v\2\2z{\7j\2\2{|\7"+
		"g\2\2|}\7p\2\2}&\3\2\2\2~\177\7g\2\2\177\u0080\7n\2\2\u0080\u0081\7u\2"+
		"\2\u0081\u0082\7g\2\2\u0082(\3\2\2\2\u0083\u0085\t\2\2\2\u0084\u0083\3"+
		"\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u0089\b\25\2\2\u0089*\3\2\2\2\u008a\u008b\7\61\2"+
		"\2\u008b\u008c\7\61\2\2\u008c\u0090\3\2\2\2\u008d\u008f\n\3\2\2\u008e"+
		"\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\b\26\2\2\u0094"+
		",\3\2\2\2\u0095\u0099\59\35\2\u0096\u0098\5;\36\2\u0097\u0096\3\2\2\2"+
		"\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a.\3"+
		"\2\2\2\u009b\u0099\3\2\2\2\u009c\u00a0\7)\2\2\u009d\u009f\n\3\2\2\u009e"+
		"\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00af\7)\2\2\u00a4"+
		"\u00aa\7$\2\2\u00a5\u00a6\7^\2\2\u00a6\u00a9\7$\2\2\u00a7\u00a9\n\3\2"+
		"\2\u00a8\u00a5\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8"+
		"\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad"+
		"\u00af\7$\2\2\u00ae\u009c\3\2\2\2\u00ae\u00a4\3\2\2\2\u00af\60\3\2\2\2"+
		"\u00b0\u00b2\5=\37\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\62\3\2\2\2\u00b5\u00b6\t\4\2\2\u00b6"+
		"\64\3\2\2\2\u00b7\u00b8\t\5\2\2\u00b8\66\3\2\2\2\u00b9\u00ba\7?\2\2\u00ba"+
		"\u00c3\7?\2\2\u00bb\u00c3\t\6\2\2\u00bc\u00bd\7@\2\2\u00bd\u00c3\7?\2"+
		"\2\u00be\u00bf\7>\2\2\u00bf\u00c3\7?\2\2\u00c0\u00c1\7#\2\2\u00c1\u00c3"+
		"\7?\2\2\u00c2\u00b9\3\2\2\2\u00c2\u00bb\3\2\2\2\u00c2\u00bc\3\2\2\2\u00c2"+
		"\u00be\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c38\3\2\2\2\u00c4\u00c5\t\7\2\2"+
		"\u00c5:\3\2\2\2\u00c6\u00c7\t\b\2\2\u00c7<\3\2\2\2\u00c8\u00c9\4\62;\2"+
		"\u00c9>\3\2\2\2\f\2\u0086\u0090\u0099\u00a0\u00a8\u00aa\u00ae\u00b3\u00c2"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}