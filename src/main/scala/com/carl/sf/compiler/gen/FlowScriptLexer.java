// Generated from /home/klangner/workspaces/scala/flow-script/src/main/scala/com/carl/sf/compiler/FlowScript.g4 by ANTLR 4.7
package com.carl.sf.compiler.gen;
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
		TRUE=10, FALSE=11, WS=12, LINE_COMMENT=13, Identifier=14, QuotedString=15, 
		Integer=16, AddOp=17, MultiplyOp=18, RelationOp=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
		"AddOp", "MultiplyOp", "RelationOp", "Letter", "LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u00a1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\6\r\\\n\r\r\r\16\r]\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\7\16f\n\16\f\16\16\16i\13\16\3\16\3\16\3\17\3\17\7\17o\n\17\f\17"+
		"\16\17r\13\17\3\20\3\20\7\20v\n\20\f\20\16\20y\13\20\3\20\3\20\3\20\3"+
		"\20\3\20\7\20\u0080\n\20\f\20\16\20\u0083\13\20\3\20\5\20\u0086\n\20\3"+
		"\21\6\21\u0089\n\21\r\21\16\21\u008a\3\22\3\22\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u009a\n\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\2+\2-\2\3\2\t\5\2\13\f\16\17\"\""+
		"\4\2\f\f\17\17\4\2--//\4\2,,\61\61\4\2>>@@\6\2&&C\\aac|\7\2&&\62;C\\a"+
		"ac|\2\u00a9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3/\3\2\2\2\5\61\3\2\2\2\7\63\3\2"+
		"\2\2\t\65\3\2\2\2\13\67\3\2\2\2\r9\3\2\2\2\17;\3\2\2\2\21?\3\2\2\2\23"+
		"H\3\2\2\2\25O\3\2\2\2\27T\3\2\2\2\31[\3\2\2\2\33a\3\2\2\2\35l\3\2\2\2"+
		"\37\u0085\3\2\2\2!\u0088\3\2\2\2#\u008c\3\2\2\2%\u008e\3\2\2\2\'\u0099"+
		"\3\2\2\2)\u009b\3\2\2\2+\u009d\3\2\2\2-\u009f\3\2\2\2/\60\7*\2\2\60\4"+
		"\3\2\2\2\61\62\7+\2\2\62\6\3\2\2\2\63\64\7<\2\2\64\b\3\2\2\2\65\66\7?"+
		"\2\2\66\n\3\2\2\2\678\7.\2\28\f\3\2\2\29:\7\60\2\2:\16\3\2\2\2;<\7f\2"+
		"\2<=\7g\2\2=>\7h\2\2>\20\3\2\2\2?@\7g\2\2@A\7z\2\2AB\7v\2\2BC\7g\2\2C"+
		"D\7t\2\2DE\7p\2\2EF\7c\2\2FG\7n\2\2G\22\3\2\2\2HI\7o\2\2IJ\7q\2\2JK\7"+
		"f\2\2KL\7w\2\2LM\7n\2\2MN\7g\2\2N\24\3\2\2\2OP\7V\2\2PQ\7t\2\2QR\7w\2"+
		"\2RS\7g\2\2S\26\3\2\2\2TU\7H\2\2UV\7c\2\2VW\7n\2\2WX\7u\2\2XY\7g\2\2Y"+
		"\30\3\2\2\2Z\\\t\2\2\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2"+
		"\2\2_`\b\r\2\2`\32\3\2\2\2ab\7\61\2\2bc\7\61\2\2cg\3\2\2\2df\n\3\2\2e"+
		"d\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\b\16\2\2"+
		"k\34\3\2\2\2lp\5)\25\2mo\5+\26\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2"+
		"\2q\36\3\2\2\2rp\3\2\2\2sw\7)\2\2tv\n\3\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2"+
		"\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z\u0086\7)\2\2{\u0081\7$\2\2|}\7^\2"+
		"\2}\u0080\7$\2\2~\u0080\n\3\2\2\177|\3\2\2\2\177~\3\2\2\2\u0080\u0083"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0084\u0086\7$\2\2\u0085s\3\2\2\2\u0085{\3\2\2\2\u0086"+
		" \3\2\2\2\u0087\u0089\5-\27\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2\2\2"+
		"\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\"\3\2\2\2\u008c\u008d\t"+
		"\4\2\2\u008d$\3\2\2\2\u008e\u008f\t\5\2\2\u008f&\3\2\2\2\u0090\u0091\7"+
		"?\2\2\u0091\u009a\7?\2\2\u0092\u009a\t\6\2\2\u0093\u0094\7@\2\2\u0094"+
		"\u009a\7?\2\2\u0095\u0096\7>\2\2\u0096\u009a\7?\2\2\u0097\u0098\7#\2\2"+
		"\u0098\u009a\7?\2\2\u0099\u0090\3\2\2\2\u0099\u0092\3\2\2\2\u0099\u0093"+
		"\3\2\2\2\u0099\u0095\3\2\2\2\u0099\u0097\3\2\2\2\u009a(\3\2\2\2\u009b"+
		"\u009c\t\7\2\2\u009c*\3\2\2\2\u009d\u009e\t\b\2\2\u009e,\3\2\2\2\u009f"+
		"\u00a0\4\62;\2\u00a0.\3\2\2\2\f\2]gpw\177\u0081\u0085\u008a\u0099\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}