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
		Integer=16, BinaryOp=17, RelationOp=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
		"BinaryOp", "RelationOp", "Letter", "LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
		"BinaryOp", "RelationOp"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u009d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\6\rZ\n\r\r\r\16\r[\3\r\3\r\3\16\3\16\3\16\3\16\7\16"+
		"d\n\16\f\16\16\16g\13\16\3\16\3\16\3\17\3\17\7\17m\n\17\f\17\16\17p\13"+
		"\17\3\20\3\20\7\20t\n\20\f\20\16\20w\13\20\3\20\3\20\3\20\3\20\3\20\7"+
		"\20~\n\20\f\20\16\20\u0081\13\20\3\20\5\20\u0084\n\20\3\21\6\21\u0087"+
		"\n\21\r\21\16\21\u0088\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\5\23\u0096\n\23\3\24\3\24\3\25\3\25\3\26\3\26\2\2\27\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\2)\2+\2\3\2\b\5\2\13\f\16\17\"\"\4\2\f\f\17\17\5\2,-//\61\61"+
		"\4\2>>@@\6\2&&C\\aac|\7\2&&\62;C\\aac|\2\u00a5\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3-\3\2\2"+
		"\2\5/\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\65\3\2\2\2\r\67\3\2\2\2\17"+
		"9\3\2\2\2\21=\3\2\2\2\23F\3\2\2\2\25M\3\2\2\2\27R\3\2\2\2\31Y\3\2\2\2"+
		"\33_\3\2\2\2\35j\3\2\2\2\37\u0083\3\2\2\2!\u0086\3\2\2\2#\u008a\3\2\2"+
		"\2%\u0095\3\2\2\2\'\u0097\3\2\2\2)\u0099\3\2\2\2+\u009b\3\2\2\2-.\7*\2"+
		"\2.\4\3\2\2\2/\60\7+\2\2\60\6\3\2\2\2\61\62\7<\2\2\62\b\3\2\2\2\63\64"+
		"\7?\2\2\64\n\3\2\2\2\65\66\7.\2\2\66\f\3\2\2\2\678\7\60\2\28\16\3\2\2"+
		"\29:\7f\2\2:;\7g\2\2;<\7h\2\2<\20\3\2\2\2=>\7g\2\2>?\7z\2\2?@\7v\2\2@"+
		"A\7g\2\2AB\7t\2\2BC\7p\2\2CD\7c\2\2DE\7n\2\2E\22\3\2\2\2FG\7o\2\2GH\7"+
		"q\2\2HI\7f\2\2IJ\7w\2\2JK\7n\2\2KL\7g\2\2L\24\3\2\2\2MN\7V\2\2NO\7t\2"+
		"\2OP\7w\2\2PQ\7g\2\2Q\26\3\2\2\2RS\7H\2\2ST\7c\2\2TU\7n\2\2UV\7u\2\2V"+
		"W\7g\2\2W\30\3\2\2\2XZ\t\2\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2"+
		"\2\\]\3\2\2\2]^\b\r\2\2^\32\3\2\2\2_`\7\61\2\2`a\7\61\2\2ae\3\2\2\2bd"+
		"\n\3\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2h"+
		"i\b\16\2\2i\34\3\2\2\2jn\5\'\24\2km\5)\25\2lk\3\2\2\2mp\3\2\2\2nl\3\2"+
		"\2\2no\3\2\2\2o\36\3\2\2\2pn\3\2\2\2qu\7)\2\2rt\n\3\2\2sr\3\2\2\2tw\3"+
		"\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2x\u0084\7)\2\2y\177\7$\2"+
		"\2z{\7^\2\2{~\7$\2\2|~\n\3\2\2}z\3\2\2\2}|\3\2\2\2~\u0081\3\2\2\2\177"+
		"}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082"+
		"\u0084\7$\2\2\u0083q\3\2\2\2\u0083y\3\2\2\2\u0084 \3\2\2\2\u0085\u0087"+
		"\5+\26\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\"\3\2\2\2\u008a\u008b\t\4\2\2\u008b$\3\2\2\2\u008c"+
		"\u008d\7?\2\2\u008d\u0096\7?\2\2\u008e\u0096\t\5\2\2\u008f\u0090\7@\2"+
		"\2\u0090\u0096\7?\2\2\u0091\u0092\7>\2\2\u0092\u0096\7?\2\2\u0093\u0094"+
		"\7#\2\2\u0094\u0096\7?\2\2\u0095\u008c\3\2\2\2\u0095\u008e\3\2\2\2\u0095"+
		"\u008f\3\2\2\2\u0095\u0091\3\2\2\2\u0095\u0093\3\2\2\2\u0096&\3\2\2\2"+
		"\u0097\u0098\t\6\2\2\u0098(\3\2\2\2\u0099\u009a\t\7\2\2\u009a*\3\2\2\2"+
		"\u009b\u009c\4\62;\2\u009c,\3\2\2\2\f\2[enu}\177\u0083\u0088\u0095\3\b"+
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