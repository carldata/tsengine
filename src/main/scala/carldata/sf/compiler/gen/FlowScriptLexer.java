// Generated from /home/klangner/workspaces/scala/flow-script/src/main/scala/com/carl/sf/compiler/FlowScript.g4 by ANTLR 4.7
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
		TRUE=10, FALSE=11, PLUS=12, MINUS=13, WS=14, LINE_COMMENT=15, Identifier=16, 
		QuotedString=17, Integer=18, AddOp=19, MultiplyOp=20, RelationOp=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "PLUS", "MINUS", "WS", "LINE_COMMENT", "Identifier", 
		"QuotedString", "Integer", "AddOp", "MultiplyOp", "RelationOp", "Letter", 
		"LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "PLUS", "MINUS", "WS", "LINE_COMMENT", "Identifier", 
		"QuotedString", "Integer", "AddOp", "MultiplyOp", "RelationOp"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00a9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\6"+
		"\17d\n\17\r\17\16\17e\3\17\3\17\3\20\3\20\3\20\3\20\7\20n\n\20\f\20\16"+
		"\20q\13\20\3\20\3\20\3\21\3\21\7\21w\n\21\f\21\16\21z\13\21\3\22\3\22"+
		"\7\22~\n\22\f\22\16\22\u0081\13\22\3\22\3\22\3\22\3\22\3\22\7\22\u0088"+
		"\n\22\f\22\16\22\u008b\13\22\3\22\5\22\u008e\n\22\3\23\6\23\u0091\n\23"+
		"\r\23\16\23\u0092\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\5\26\u00a2\n\26\3\27\3\27\3\30\3\30\3\31\3\31\2\2\32\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\2/\2\61\2\3\2\t\5\2\13\f\16\17\"\"\4\2\f\f"+
		"\17\17\4\2--//\4\2,,\61\61\4\2>>@@\6\2&&C\\aac|\7\2&&\62;C\\aac|\2\u00b1"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3\63\3\2\2\2\5\65\3"+
		"\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2\2\17?\3\2\2\2\21C"+
		"\3\2\2\2\23L\3\2\2\2\25S\3\2\2\2\27X\3\2\2\2\31^\3\2\2\2\33`\3\2\2\2\35"+
		"c\3\2\2\2\37i\3\2\2\2!t\3\2\2\2#\u008d\3\2\2\2%\u0090\3\2\2\2\'\u0094"+
		"\3\2\2\2)\u0096\3\2\2\2+\u00a1\3\2\2\2-\u00a3\3\2\2\2/\u00a5\3\2\2\2\61"+
		"\u00a7\3\2\2\2\63\64\7*\2\2\64\4\3\2\2\2\65\66\7+\2\2\66\6\3\2\2\2\67"+
		"8\7<\2\28\b\3\2\2\29:\7?\2\2:\n\3\2\2\2;<\7.\2\2<\f\3\2\2\2=>\7\60\2\2"+
		">\16\3\2\2\2?@\7f\2\2@A\7g\2\2AB\7h\2\2B\20\3\2\2\2CD\7g\2\2DE\7z\2\2"+
		"EF\7v\2\2FG\7g\2\2GH\7t\2\2HI\7p\2\2IJ\7c\2\2JK\7n\2\2K\22\3\2\2\2LM\7"+
		"o\2\2MN\7q\2\2NO\7f\2\2OP\7w\2\2PQ\7n\2\2QR\7g\2\2R\24\3\2\2\2ST\7V\2"+
		"\2TU\7t\2\2UV\7w\2\2VW\7g\2\2W\26\3\2\2\2XY\7H\2\2YZ\7c\2\2Z[\7n\2\2["+
		"\\\7u\2\2\\]\7g\2\2]\30\3\2\2\2^_\7-\2\2_\32\3\2\2\2`a\7/\2\2a\34\3\2"+
		"\2\2bd\t\2\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\b\17"+
		"\2\2h\36\3\2\2\2ij\7\61\2\2jk\7\61\2\2ko\3\2\2\2ln\n\3\2\2ml\3\2\2\2n"+
		"q\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\b\20\2\2s \3\2\2\2"+
		"tx\5-\27\2uw\5/\30\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\"\3\2\2"+
		"\2zx\3\2\2\2{\177\7)\2\2|~\n\3\2\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u008e"+
		"\7)\2\2\u0083\u0089\7$\2\2\u0084\u0085\7^\2\2\u0085\u0088\7$\2\2\u0086"+
		"\u0088\n\3\2\2\u0087\u0084\3\2\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008e\7$\2\2\u008d{\3\2\2\2\u008d\u0083\3\2\2\2\u008e"+
		"$\3\2\2\2\u008f\u0091\5\61\31\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2"+
		"\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093&\3\2\2\2\u0094\u0095"+
		"\t\4\2\2\u0095(\3\2\2\2\u0096\u0097\t\5\2\2\u0097*\3\2\2\2\u0098\u0099"+
		"\7?\2\2\u0099\u00a2\7?\2\2\u009a\u00a2\t\6\2\2\u009b\u009c\7@\2\2\u009c"+
		"\u00a2\7?\2\2\u009d\u009e\7>\2\2\u009e\u00a2\7?\2\2\u009f\u00a0\7#\2\2"+
		"\u00a0\u00a2\7?\2\2\u00a1\u0098\3\2\2\2\u00a1\u009a\3\2\2\2\u00a1\u009b"+
		"\3\2\2\2\u00a1\u009d\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2,\3\2\2\2\u00a3"+
		"\u00a4\t\7\2\2\u00a4.\3\2\2\2\u00a5\u00a6\t\b\2\2\u00a6\60\3\2\2\2\u00a7"+
		"\u00a8\4\62;\2\u00a8\62\3\2\2\2\f\2eox\177\u0087\u0089\u008d\u0092\u00a1"+
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