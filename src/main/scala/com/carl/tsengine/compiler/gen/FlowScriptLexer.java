// Generated from /home/klangner/workspaces/scala/tsengine/src/main/scala/com/carl/tsengine/compiler/FlowScript.g4 by ANTLR 4.7
package com.carl.tsengine.compiler.gen;
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
		T__0=1, T__1=2, T__2=3, T__3=4, DEF=5, MODULE=6, WS=7, LINE_COMMENT=8, 
		Identifier=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "Letter", "LetterOrDigit", "DEF", "MODULE", 
		"WS", "LINE_COMMENT", "Identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'='", "','", "'def'", "'module'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "DEF", "MODULE", "WS", "LINE_COMMENT", "Identifier"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13I\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\6\n\62\n\n\r\n\16\n\63\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\7\13<\n\13\f\13\16\13?\13\13\3\13\3\13\3\f\3\f\7"+
		"\fE\n\f\f\f\16\fH\13\f\2\2\r\3\3\5\4\7\5\t\6\13\2\r\2\17\7\21\b\23\t\25"+
		"\n\27\13\3\2\6\6\2&&C\\aac|\7\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f"+
		"\f\17\17\2I\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\5"+
		"\33\3\2\2\2\7\35\3\2\2\2\t\37\3\2\2\2\13!\3\2\2\2\r#\3\2\2\2\17%\3\2\2"+
		"\2\21)\3\2\2\2\23\61\3\2\2\2\25\67\3\2\2\2\27B\3\2\2\2\31\32\7*\2\2\32"+
		"\4\3\2\2\2\33\34\7+\2\2\34\6\3\2\2\2\35\36\7?\2\2\36\b\3\2\2\2\37 \7."+
		"\2\2 \n\3\2\2\2!\"\t\2\2\2\"\f\3\2\2\2#$\t\3\2\2$\16\3\2\2\2%&\7f\2\2"+
		"&\'\7g\2\2\'(\7h\2\2(\20\3\2\2\2)*\7o\2\2*+\7q\2\2+,\7f\2\2,-\7w\2\2-"+
		".\7n\2\2./\7g\2\2/\22\3\2\2\2\60\62\t\4\2\2\61\60\3\2\2\2\62\63\3\2\2"+
		"\2\63\61\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\66\b\n\2\2\66\24\3\2\2"+
		"\2\678\7\61\2\289\7\61\2\29=\3\2\2\2:<\n\5\2\2;:\3\2\2\2<?\3\2\2\2=;\3"+
		"\2\2\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\b\13\2\2A\26\3\2\2\2BF\5\13\6\2"+
		"CE\5\r\7\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\30\3\2\2\2HF\3\2\2"+
		"\2\6\2\63=F\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}