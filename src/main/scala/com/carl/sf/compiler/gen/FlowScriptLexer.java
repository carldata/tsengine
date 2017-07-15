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
		Integer=16, RelationOp=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
		"RelationOp", "Letter", "LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
		"RelationOp"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0099\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\6\rX\n\r\r\r\16\rY\3\r\3\r\3\16\3\16\3\16\3\16\7\16b\n\16\f\16"+
		"\16\16e\13\16\3\16\3\16\3\17\3\17\7\17k\n\17\f\17\16\17n\13\17\3\20\3"+
		"\20\7\20r\n\20\f\20\16\20u\13\20\3\20\3\20\3\20\3\20\3\20\7\20|\n\20\f"+
		"\20\16\20\177\13\20\3\20\5\20\u0082\n\20\3\21\6\21\u0085\n\21\r\21\16"+
		"\21\u0086\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0092\n\22"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\2\'\2)\2\3\2\7\5\2"+
		"\13\f\16\17\"\"\4\2\f\f\17\17\4\2>>@@\6\2&&C\\aac|\7\2&&\62;C\\aac|\2"+
		"\u00a1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13\63\3\2\2"+
		"\2\r\65\3\2\2\2\17\67\3\2\2\2\21;\3\2\2\2\23D\3\2\2\2\25K\3\2\2\2\27P"+
		"\3\2\2\2\31W\3\2\2\2\33]\3\2\2\2\35h\3\2\2\2\37\u0081\3\2\2\2!\u0084\3"+
		"\2\2\2#\u0091\3\2\2\2%\u0093\3\2\2\2\'\u0095\3\2\2\2)\u0097\3\2\2\2+,"+
		"\7*\2\2,\4\3\2\2\2-.\7+\2\2.\6\3\2\2\2/\60\7<\2\2\60\b\3\2\2\2\61\62\7"+
		"?\2\2\62\n\3\2\2\2\63\64\7.\2\2\64\f\3\2\2\2\65\66\7\60\2\2\66\16\3\2"+
		"\2\2\678\7f\2\289\7g\2\29:\7h\2\2:\20\3\2\2\2;<\7g\2\2<=\7z\2\2=>\7v\2"+
		"\2>?\7g\2\2?@\7t\2\2@A\7p\2\2AB\7c\2\2BC\7n\2\2C\22\3\2\2\2DE\7o\2\2E"+
		"F\7q\2\2FG\7f\2\2GH\7w\2\2HI\7n\2\2IJ\7g\2\2J\24\3\2\2\2KL\7V\2\2LM\7"+
		"t\2\2MN\7w\2\2NO\7g\2\2O\26\3\2\2\2PQ\7H\2\2QR\7c\2\2RS\7n\2\2ST\7u\2"+
		"\2TU\7g\2\2U\30\3\2\2\2VX\t\2\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2"+
		"\2\2Z[\3\2\2\2[\\\b\r\2\2\\\32\3\2\2\2]^\7\61\2\2^_\7\61\2\2_c\3\2\2\2"+
		"`b\n\3\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2"+
		"fg\b\16\2\2g\34\3\2\2\2hl\5%\23\2ik\5\'\24\2ji\3\2\2\2kn\3\2\2\2lj\3\2"+
		"\2\2lm\3\2\2\2m\36\3\2\2\2nl\3\2\2\2os\7)\2\2pr\n\3\2\2qp\3\2\2\2ru\3"+
		"\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2\2\2v\u0082\7)\2\2w}\7$\2\2"+
		"xy\7^\2\2y|\7$\2\2z|\n\3\2\2{x\3\2\2\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2"+
		"\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0082\7$\2\2\u0081o\3\2"+
		"\2\2\u0081w\3\2\2\2\u0082 \3\2\2\2\u0083\u0085\5)\25\2\u0084\u0083\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\"\3\2\2\2\u0088\u0089\7?\2\2\u0089\u0092\7?\2\2\u008a\u0092\t\4\2\2\u008b"+
		"\u008c\7@\2\2\u008c\u0092\7?\2\2\u008d\u008e\7>\2\2\u008e\u0092\7?\2\2"+
		"\u008f\u0090\7#\2\2\u0090\u0092\7?\2\2\u0091\u0088\3\2\2\2\u0091\u008a"+
		"\3\2\2\2\u0091\u008b\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"$\3\2\2\2\u0093\u0094\t\5\2\2\u0094&\3\2\2\2\u0095\u0096\t\6\2\2\u0096"+
		"(\3\2\2\2\u0097\u0098\4\62;\2\u0098*\3\2\2\2\f\2Ycls{}\u0081\u0086\u0091"+
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