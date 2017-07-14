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
		Integer=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
		"Letter", "LetterOrDigit", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'.'", "'def'", "'external'", 
		"'module'", "'True'", "'False'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "DEF", "EXTERNAL", "MODULE", 
		"TRUE", "FALSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u008c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\6"+
		"\rV\n\r\r\r\16\rW\3\r\3\r\3\16\3\16\3\16\3\16\7\16`\n\16\f\16\16\16c\13"+
		"\16\3\16\3\16\3\17\3\17\7\17i\n\17\f\17\16\17l\13\17\3\20\3\20\7\20p\n"+
		"\20\f\20\16\20s\13\20\3\20\3\20\3\20\3\20\3\20\7\20z\n\20\f\20\16\20}"+
		"\13\20\3\20\5\20\u0080\n\20\3\21\6\21\u0083\n\21\r\21\16\21\u0084\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\2%\2\'\2\3\2\6\5\2\13\f\16"+
		"\17\"\"\4\2\f\f\17\17\6\2&&C\\aac|\7\2&&\62;C\\aac|\2\u0090\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3)\3\2\2\2\5+\3\2"+
		"\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\63\3\2\2\2\17\65\3\2\2\2\21"+
		"9\3\2\2\2\23B\3\2\2\2\25I\3\2\2\2\27N\3\2\2\2\31U\3\2\2\2\33[\3\2\2\2"+
		"\35f\3\2\2\2\37\177\3\2\2\2!\u0082\3\2\2\2#\u0086\3\2\2\2%\u0088\3\2\2"+
		"\2\'\u008a\3\2\2\2)*\7*\2\2*\4\3\2\2\2+,\7+\2\2,\6\3\2\2\2-.\7<\2\2.\b"+
		"\3\2\2\2/\60\7?\2\2\60\n\3\2\2\2\61\62\7.\2\2\62\f\3\2\2\2\63\64\7\60"+
		"\2\2\64\16\3\2\2\2\65\66\7f\2\2\66\67\7g\2\2\678\7h\2\28\20\3\2\2\29:"+
		"\7g\2\2:;\7z\2\2;<\7v\2\2<=\7g\2\2=>\7t\2\2>?\7p\2\2?@\7c\2\2@A\7n\2\2"+
		"A\22\3\2\2\2BC\7o\2\2CD\7q\2\2DE\7f\2\2EF\7w\2\2FG\7n\2\2GH\7g\2\2H\24"+
		"\3\2\2\2IJ\7V\2\2JK\7t\2\2KL\7w\2\2LM\7g\2\2M\26\3\2\2\2NO\7H\2\2OP\7"+
		"c\2\2PQ\7n\2\2QR\7u\2\2RS\7g\2\2S\30\3\2\2\2TV\t\2\2\2UT\3\2\2\2VW\3\2"+
		"\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\b\r\2\2Z\32\3\2\2\2[\\\7\61\2\2\\"+
		"]\7\61\2\2]a\3\2\2\2^`\n\3\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2"+
		"bd\3\2\2\2ca\3\2\2\2de\b\16\2\2e\34\3\2\2\2fj\5#\22\2gi\5%\23\2hg\3\2"+
		"\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\36\3\2\2\2lj\3\2\2\2mq\7)\2\2np\n"+
		"\3\2\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2t\u0080"+
		"\7)\2\2u{\7$\2\2vw\7^\2\2wz\7$\2\2xz\n\3\2\2yv\3\2\2\2yx\3\2\2\2z}\3\2"+
		"\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\u0080\7$\2\2\177m\3\2\2"+
		"\2\177u\3\2\2\2\u0080 \3\2\2\2\u0081\u0083\5\'\24\2\u0082\u0081\3\2\2"+
		"\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\""+
		"\3\2\2\2\u0086\u0087\t\4\2\2\u0087$\3\2\2\2\u0088\u0089\t\5\2\2\u0089"+
		"&\3\2\2\2\u008a\u008b\4\62;\2\u008b(\3\2\2\2\13\2Wajqy{\177\u0084\3\b"+
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