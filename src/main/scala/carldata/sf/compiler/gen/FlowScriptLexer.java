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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		DEF=10, EXTERNAL=11, MODULE=12, TRUE=13, FALSE=14, PLUS=15, MINUS=16, 
		AND=17, OR=18, NEG=19, IF=20, THEN=21, ELSE=22, NULL=23, WS=24, LINE_COMMENT=25, 
		Identifier=26, Integer=27, AddOp=28, MultiplyOp=29, RelationOp=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"DEF", "EXTERNAL", "MODULE", "TRUE", "FALSE", "PLUS", "MINUS", "AND", 
		"OR", "NEG", "IF", "THEN", "ELSE", "NULL", "WS", "LINE_COMMENT", "Identifier", 
		"Integer", "AddOp", "MultiplyOp", "RelationOp", "Letter", "LetterOrDigit", 
		"Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'=>'", "'let'", "'in'", "'.'", 
		"'def'", "'external'", "'module'", "'True'", "'False'", "'+'", "'-'", 
		"'&&'", "'||'", "'!'", "'if'", "'then'", "'else'", "'NULL'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "DEF", "EXTERNAL", 
		"MODULE", "TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "NEG", "IF", 
		"THEN", "ELSE", "NULL", "WS", "LINE_COMMENT", "Identifier", "Integer", 
		"AddOp", "MultiplyOp", "RelationOp"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00cb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\6\31\u009a\n\31\r\31\16"+
		"\31\u009b\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00a4\n\32\f\32\16\32\u00a7"+
		"\13\32\3\32\3\32\3\33\3\33\7\33\u00ad\n\33\f\33\16\33\u00b0\13\33\3\34"+
		"\6\34\u00b3\n\34\r\34\16\34\u00b4\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\5\37\u00c4\n\37\3 \3 \3!\3!\3\"\3\"\2\2#"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?\2A\2C\2\3\2\t\5\2\13\f\16\17\"\"\4\2\f\f\17\17\4\2--//\4\2,,\61\61"+
		"\4\2>>@@\6\2&&C\\aac|\7\2&&\62;C\\aac|\2\u00cf\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3E\3\2"+
		"\2\2\5G\3\2\2\2\7I\3\2\2\2\tK\3\2\2\2\13M\3\2\2\2\rO\3\2\2\2\17R\3\2\2"+
		"\2\21V\3\2\2\2\23Y\3\2\2\2\25[\3\2\2\2\27_\3\2\2\2\31h\3\2\2\2\33o\3\2"+
		"\2\2\35t\3\2\2\2\37z\3\2\2\2!|\3\2\2\2#~\3\2\2\2%\u0081\3\2\2\2\'\u0084"+
		"\3\2\2\2)\u0086\3\2\2\2+\u0089\3\2\2\2-\u008e\3\2\2\2/\u0093\3\2\2\2\61"+
		"\u0099\3\2\2\2\63\u009f\3\2\2\2\65\u00aa\3\2\2\2\67\u00b2\3\2\2\29\u00b6"+
		"\3\2\2\2;\u00b8\3\2\2\2=\u00c3\3\2\2\2?\u00c5\3\2\2\2A\u00c7\3\2\2\2C"+
		"\u00c9\3\2\2\2EF\7*\2\2F\4\3\2\2\2GH\7+\2\2H\6\3\2\2\2IJ\7<\2\2J\b\3\2"+
		"\2\2KL\7?\2\2L\n\3\2\2\2MN\7.\2\2N\f\3\2\2\2OP\7?\2\2PQ\7@\2\2Q\16\3\2"+
		"\2\2RS\7n\2\2ST\7g\2\2TU\7v\2\2U\20\3\2\2\2VW\7k\2\2WX\7p\2\2X\22\3\2"+
		"\2\2YZ\7\60\2\2Z\24\3\2\2\2[\\\7f\2\2\\]\7g\2\2]^\7h\2\2^\26\3\2\2\2_"+
		"`\7g\2\2`a\7z\2\2ab\7v\2\2bc\7g\2\2cd\7t\2\2de\7p\2\2ef\7c\2\2fg\7n\2"+
		"\2g\30\3\2\2\2hi\7o\2\2ij\7q\2\2jk\7f\2\2kl\7w\2\2lm\7n\2\2mn\7g\2\2n"+
		"\32\3\2\2\2op\7V\2\2pq\7t\2\2qr\7w\2\2rs\7g\2\2s\34\3\2\2\2tu\7H\2\2u"+
		"v\7c\2\2vw\7n\2\2wx\7u\2\2xy\7g\2\2y\36\3\2\2\2z{\7-\2\2{ \3\2\2\2|}\7"+
		"/\2\2}\"\3\2\2\2~\177\7(\2\2\177\u0080\7(\2\2\u0080$\3\2\2\2\u0081\u0082"+
		"\7~\2\2\u0082\u0083\7~\2\2\u0083&\3\2\2\2\u0084\u0085\7#\2\2\u0085(\3"+
		"\2\2\2\u0086\u0087\7k\2\2\u0087\u0088\7h\2\2\u0088*\3\2\2\2\u0089\u008a"+
		"\7v\2\2\u008a\u008b\7j\2\2\u008b\u008c\7g\2\2\u008c\u008d\7p\2\2\u008d"+
		",\3\2\2\2\u008e\u008f\7g\2\2\u008f\u0090\7n\2\2\u0090\u0091\7u\2\2\u0091"+
		"\u0092\7g\2\2\u0092.\3\2\2\2\u0093\u0094\7P\2\2\u0094\u0095\7W\2\2\u0095"+
		"\u0096\7N\2\2\u0096\u0097\7N\2\2\u0097\60\3\2\2\2\u0098\u009a\t\2\2\2"+
		"\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\b\31\2\2\u009e\62\3\2\2\2\u009f"+
		"\u00a0\7\61\2\2\u00a0\u00a1\7\61\2\2\u00a1\u00a5\3\2\2\2\u00a2\u00a4\n"+
		"\3\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\b\32"+
		"\2\2\u00a9\64\3\2\2\2\u00aa\u00ae\5? \2\u00ab\u00ad\5A!\2\u00ac\u00ab"+
		"\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\66\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b3\5C\"\2\u00b2\u00b1\3\2\2\2"+
		"\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b58\3"+
		"\2\2\2\u00b6\u00b7\t\4\2\2\u00b7:\3\2\2\2\u00b8\u00b9\t\5\2\2\u00b9<\3"+
		"\2\2\2\u00ba\u00bb\7?\2\2\u00bb\u00c4\7?\2\2\u00bc\u00c4\t\6\2\2\u00bd"+
		"\u00be\7@\2\2\u00be\u00c4\7?\2\2\u00bf\u00c0\7>\2\2\u00c0\u00c4\7?\2\2"+
		"\u00c1\u00c2\7#\2\2\u00c2\u00c4\7?\2\2\u00c3\u00ba\3\2\2\2\u00c3\u00bc"+
		"\3\2\2\2\u00c3\u00bd\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		">\3\2\2\2\u00c5\u00c6\t\7\2\2\u00c6@\3\2\2\2\u00c7\u00c8\t\b\2\2\u00c8"+
		"B\3\2\2\2\u00c9\u00ca\4\62;\2\u00caD\3\2\2\2\b\2\u009b\u00a5\u00ae\u00b4"+
		"\u00c3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}