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
		TRUE=10, FALSE=11, PLUS=12, MINUS=13, AND=14, OR=15, WS=16, LINE_COMMENT=17, 
		Identifier=18, QuotedString=19, Integer=20, AddOp=21, MultiplyOp=22, RelationOp=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "WS", "LINE_COMMENT", "Identifier", 
		"QuotedString", "Integer", "AddOp", "MultiplyOp", "RelationOp", "Letter", 
		"LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'", "'+'", "'-'", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "WS", "LINE_COMMENT", "Identifier", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00b3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\6\21n\n\21\r\21\16\21"+
		"o\3\21\3\21\3\22\3\22\3\22\3\22\7\22x\n\22\f\22\16\22{\13\22\3\22\3\22"+
		"\3\23\3\23\7\23\u0081\n\23\f\23\16\23\u0084\13\23\3\24\3\24\7\24\u0088"+
		"\n\24\f\24\16\24\u008b\13\24\3\24\3\24\3\24\3\24\3\24\7\24\u0092\n\24"+
		"\f\24\16\24\u0095\13\24\3\24\5\24\u0098\n\24\3\25\6\25\u009b\n\25\r\25"+
		"\16\25\u009c\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\5\30\u00ac\n\30\3\31\3\31\3\32\3\32\3\33\3\33\2\2\34\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\2\63\2\65\2\3\2\t\5\2\13\f\16\17\"\""+
		"\4\2\f\f\17\17\4\2--//\4\2,,\61\61\4\2>>@@\6\2&&C\\aac|\7\2&&\62;C\\a"+
		"ac|\2\u00bb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\3\67\3\2\2\2\59\3\2\2\2\7;\3\2\2\2\t=\3\2\2\2\13?\3\2\2\2"+
		"\rA\3\2\2\2\17C\3\2\2\2\21G\3\2\2\2\23P\3\2\2\2\25W\3\2\2\2\27\\\3\2\2"+
		"\2\31b\3\2\2\2\33d\3\2\2\2\35f\3\2\2\2\37i\3\2\2\2!m\3\2\2\2#s\3\2\2\2"+
		"%~\3\2\2\2\'\u0097\3\2\2\2)\u009a\3\2\2\2+\u009e\3\2\2\2-\u00a0\3\2\2"+
		"\2/\u00ab\3\2\2\2\61\u00ad\3\2\2\2\63\u00af\3\2\2\2\65\u00b1\3\2\2\2\67"+
		"8\7*\2\28\4\3\2\2\29:\7+\2\2:\6\3\2\2\2;<\7<\2\2<\b\3\2\2\2=>\7?\2\2>"+
		"\n\3\2\2\2?@\7.\2\2@\f\3\2\2\2AB\7\60\2\2B\16\3\2\2\2CD\7f\2\2DE\7g\2"+
		"\2EF\7h\2\2F\20\3\2\2\2GH\7g\2\2HI\7z\2\2IJ\7v\2\2JK\7g\2\2KL\7t\2\2L"+
		"M\7p\2\2MN\7c\2\2NO\7n\2\2O\22\3\2\2\2PQ\7o\2\2QR\7q\2\2RS\7f\2\2ST\7"+
		"w\2\2TU\7n\2\2UV\7g\2\2V\24\3\2\2\2WX\7V\2\2XY\7t\2\2YZ\7w\2\2Z[\7g\2"+
		"\2[\26\3\2\2\2\\]\7H\2\2]^\7c\2\2^_\7n\2\2_`\7u\2\2`a\7g\2\2a\30\3\2\2"+
		"\2bc\7-\2\2c\32\3\2\2\2de\7/\2\2e\34\3\2\2\2fg\7(\2\2gh\7(\2\2h\36\3\2"+
		"\2\2ij\7~\2\2jk\7~\2\2k \3\2\2\2ln\t\2\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2"+
		"\2op\3\2\2\2pq\3\2\2\2qr\b\21\2\2r\"\3\2\2\2st\7\61\2\2tu\7\61\2\2uy\3"+
		"\2\2\2vx\n\3\2\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3"+
		"\2\2\2|}\b\22\2\2}$\3\2\2\2~\u0082\5\61\31\2\177\u0081\5\63\32\2\u0080"+
		"\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083&\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0089\7)\2\2\u0086\u0088\n"+
		"\3\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u0098\7)"+
		"\2\2\u008d\u0093\7$\2\2\u008e\u008f\7^\2\2\u008f\u0092\7$\2\2\u0090\u0092"+
		"\n\3\2\2\u0091\u008e\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0096\u0098\7$\2\2\u0097\u0085\3\2\2\2\u0097\u008d\3\2\2\2\u0098"+
		"(\3\2\2\2\u0099\u009b\5\65\33\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2"+
		"\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d*\3\2\2\2\u009e\u009f"+
		"\t\4\2\2\u009f,\3\2\2\2\u00a0\u00a1\t\5\2\2\u00a1.\3\2\2\2\u00a2\u00a3"+
		"\7?\2\2\u00a3\u00ac\7?\2\2\u00a4\u00ac\t\6\2\2\u00a5\u00a6\7@\2\2\u00a6"+
		"\u00ac\7?\2\2\u00a7\u00a8\7>\2\2\u00a8\u00ac\7?\2\2\u00a9\u00aa\7#\2\2"+
		"\u00aa\u00ac\7?\2\2\u00ab\u00a2\3\2\2\2\u00ab\u00a4\3\2\2\2\u00ab\u00a5"+
		"\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\60\3\2\2\2\u00ad"+
		"\u00ae\t\7\2\2\u00ae\62\3\2\2\2\u00af\u00b0\t\b\2\2\u00b0\64\3\2\2\2\u00b1"+
		"\u00b2\4\62;\2\u00b2\66\3\2\2\2\f\2oy\u0082\u0089\u0091\u0093\u0097\u009c"+
		"\u00ab\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}