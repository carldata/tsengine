// Generated from /home/klangner/workspaces/scala/flow-script/src/main/scala/carldata/sf/compiler/FlowScript.g4 by ANTLR 4.7
package carldata.sf.compiler.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FlowScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		DEF=10, EXTERNAL=11, MODULE=12, TRUE=13, FALSE=14, PLUS=15, MINUS=16, 
		AND=17, OR=18, NEG=19, IF=20, THEN=21, ELSE=22, NULL=23, WS=24, LINE_COMMENT=25, 
		Identifier=26, QuotedString=27, Integer=28, AddOp=29, MultiplyOp=30, RelationOp=31;
	public static final int
		RULE_compilationUnit = 0, RULE_externalFunDef = 1, RULE_functionDefinition = 2, 
		RULE_paramList = 3, RULE_param = 4, RULE_typeDefinition = 5, RULE_typeList = 6, 
		RULE_functionBody = 7, RULE_assignment = 8, RULE_expression = 9, RULE_nullLiteral = 10, 
		RULE_stringLiteral = 11, RULE_numberLiteral = 12, RULE_variableExpr = 13, 
		RULE_funApp = 14, RULE_expressionList = 15;
	public static final String[] ruleNames = {
		"compilationUnit", "externalFunDef", "functionDefinition", "paramList", 
		"param", "typeDefinition", "typeList", "functionBody", "assignment", "expression", 
		"nullLiteral", "stringLiteral", "numberLiteral", "variableExpr", "funApp", 
		"expressionList"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'=>'", "'let'", "'in'", "'.'", 
		"'def'", "'external'", "'module'", "'True'", "'False'", "'+'", "'-'", 
		"'&&'", "'||'", "'!'", "'if'", "'then'", "'else'", "'NULL'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "DEF", "EXTERNAL", 
		"MODULE", "TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "NEG", "IF", 
		"THEN", "ELSE", "NULL", "WS", "LINE_COMMENT", "Identifier", "QuotedString", 
		"Integer", "AddOp", "MultiplyOp", "RelationOp"
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

	@Override
	public String getGrammarFileName() { return "FlowScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FlowScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(FlowScriptParser.EOF, 0); }
		public List<ExternalFunDefContext> externalFunDef() {
			return getRuleContexts(ExternalFunDefContext.class);
		}
		public ExternalFunDefContext externalFunDef(int i) {
			return getRuleContext(ExternalFunDefContext.class,i);
		}
		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}
		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXTERNAL) {
				{
				{
				setState(32);
				externalFunDef();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEF) {
				{
				{
				setState(38);
				functionDefinition();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExternalFunDefContext extends ParserRuleContext {
		public TerminalNode EXTERNAL() { return getToken(FlowScriptParser.EXTERNAL, 0); }
		public TerminalNode DEF() { return getToken(FlowScriptParser.DEF, 0); }
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public TypeDefinitionContext typeDefinition() {
			return getRuleContext(TypeDefinitionContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public ExternalFunDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalFunDef; }
	}

	public final ExternalFunDefContext externalFunDef() throws RecognitionException {
		ExternalFunDefContext _localctx = new ExternalFunDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_externalFunDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(EXTERNAL);
			setState(47);
			match(DEF);
			setState(48);
			match(Identifier);
			setState(49);
			match(T__0);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(50);
				paramList();
				}
			}

			setState(53);
			match(T__1);
			setState(54);
			match(T__2);
			setState(55);
			typeDefinition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(FlowScriptParser.DEF, 0); }
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public TypeDefinitionContext typeDefinition() {
			return getRuleContext(TypeDefinitionContext.class,0);
		}
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(DEF);
			setState(58);
			match(Identifier);
			setState(59);
			match(T__0);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(60);
				paramList();
				}
			}

			setState(63);
			match(T__1);
			setState(64);
			match(T__2);
			setState(65);
			typeDefinition();
			setState(66);
			match(T__3);
			setState(67);
			functionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			param();
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(70);
				match(T__4);
				setState(71);
				param();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public TypeDefinitionContext typeDefinition() {
			return getRuleContext(TypeDefinitionContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Identifier);
			setState(78);
			match(T__2);
			setState(79);
			typeDefinition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TypeDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefinition; }
	}

	public final TypeDefinitionContext typeDefinition() throws RecognitionException {
		TypeDefinitionContext _localctx = new TypeDefinitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_typeDefinition);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(82);
				typeList();
				setState(83);
				match(T__5);
				setState(84);
				match(Identifier);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeListContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(FlowScriptParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(FlowScriptParser.Identifier, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(Identifier);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(89);
				match(T__4);
				setState(90);
				match(Identifier);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionBody);
		int _la;
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				match(T__6);
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Identifier) {
					{
					{
					setState(97);
					assignment();
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(103);
				match(T__7);
				setState(104);
				expression(0);
				}
				break;
			case T__0:
			case MINUS:
			case NEG:
			case IF:
			case NULL:
			case Identifier:
			case QuotedString:
			case Integer:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(Identifier);
			setState(109);
			match(T__3);
			setState(110);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext ifExpr;
		public Token minusOp;
		public Token negOp;
		public Token addOp;
		public Token boolOp;
		public TerminalNode IF() { return getToken(FlowScriptParser.IF, 0); }
		public TerminalNode THEN() { return getToken(FlowScriptParser.THEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(FlowScriptParser.ELSE, 0); }
		public TerminalNode MINUS() { return getToken(FlowScriptParser.MINUS, 0); }
		public TerminalNode NEG() { return getToken(FlowScriptParser.NEG, 0); }
		public NullLiteralContext nullLiteral() {
			return getRuleContext(NullLiteralContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public VariableExprContext variableExpr() {
			return getRuleContext(VariableExprContext.class,0);
		}
		public FunAppContext funApp() {
			return getRuleContext(FunAppContext.class,0);
		}
		public TerminalNode MultiplyOp() { return getToken(FlowScriptParser.MultiplyOp, 0); }
		public TerminalNode PLUS() { return getToken(FlowScriptParser.PLUS, 0); }
		public TerminalNode RelationOp() { return getToken(FlowScriptParser.RelationOp, 0); }
		public TerminalNode AND() { return getToken(FlowScriptParser.AND, 0); }
		public TerminalNode OR() { return getToken(FlowScriptParser.OR, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(113);
				match(IF);
				setState(114);
				((ExpressionContext)_localctx).ifExpr = expression(0);
				setState(115);
				match(THEN);
				setState(116);
				expression(0);
				setState(117);
				match(ELSE);
				setState(118);
				expression(13);
				}
				break;
			case 2:
				{
				setState(120);
				match(T__0);
				setState(121);
				expression(0);
				setState(122);
				match(T__1);
				}
				break;
			case 3:
				{
				setState(124);
				((ExpressionContext)_localctx).minusOp = match(MINUS);
				setState(125);
				expression(11);
				}
				break;
			case 4:
				{
				setState(126);
				((ExpressionContext)_localctx).negOp = match(NEG);
				setState(127);
				expression(7);
				}
				break;
			case 5:
				{
				setState(128);
				nullLiteral();
				}
				break;
			case 6:
				{
				setState(129);
				stringLiteral();
				}
				break;
			case 7:
				{
				setState(130);
				numberLiteral();
				}
				break;
			case 8:
				{
				setState(131);
				variableExpr();
				}
				break;
			case 9:
				{
				setState(132);
				funApp();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(147);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(136);
						match(MultiplyOp);
						setState(137);
						expression(11);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(138);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(139);
						((ExpressionContext)_localctx).addOp = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((ExpressionContext)_localctx).addOp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(140);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(141);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(142);
						match(RelationOp);
						setState(143);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(144);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(145);
						((ExpressionContext)_localctx).boolOp = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((ExpressionContext)_localctx).boolOp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(146);
						expression(7);
						}
						break;
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NullLiteralContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(FlowScriptParser.NULL, 0); }
		public NullLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullLiteral; }
	}

	public final NullLiteralContext nullLiteral() throws RecognitionException {
		NullLiteralContext _localctx = new NullLiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_nullLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(NULL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode QuotedString() { return getToken(FlowScriptParser.QuotedString, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(QuotedString);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberLiteralContext extends ParserRuleContext {
		public List<TerminalNode> Integer() { return getTokens(FlowScriptParser.Integer); }
		public TerminalNode Integer(int i) {
			return getToken(FlowScriptParser.Integer, i);
		}
		public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLiteral; }
	}

	public final NumberLiteralContext numberLiteral() throws RecognitionException {
		NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_numberLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(Integer);
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(157);
				match(T__8);
				setState(158);
				match(Integer);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableExprContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public VariableExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableExpr; }
	}

	public final VariableExprContext variableExpr() throws RecognitionException {
		VariableExprContext _localctx = new VariableExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variableExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunAppContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(FlowScriptParser.Identifier, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunAppContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funApp; }
	}

	public final FunAppContext funApp() throws RecognitionException {
		FunAppContext _localctx = new FunAppContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_funApp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(Identifier);
			setState(164);
			match(T__0);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << MINUS) | (1L << NEG) | (1L << IF) | (1L << NULL) | (1L << Identifier) | (1L << QuotedString) | (1L << Integer))) != 0)) {
				{
				setState(165);
				expressionList();
				}
			}

			setState(168);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			expression(0);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(171);
				match(T__4);
				setState(172);
				expression(0);
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00b5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2$\n"+
		"\2\f\2\16\2\'\13\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\5\3\66\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4@\n\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\7\5K\n\5\f\5\16\5N\13\5\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\5\7Y\n\7\3\b\3\b\3\b\7\b^\n\b\f\b\16\ba\13\b\3\t\3\t\7\t"+
		"e\n\t\f\t\16\th\13\t\3\t\3\t\3\t\5\tm\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\5\13\u0088\n\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0096\n\13\f\13\16\13\u0099\13\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16\u00a2\n\16\3\17\3\17\3\20\3\20\3"+
		"\20\5\20\u00a9\n\20\3\20\3\20\3\21\3\21\3\21\7\21\u00b0\n\21\f\21\16\21"+
		"\u00b3\13\21\3\21\2\3\24\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2"+
		"\4\3\2\21\22\3\2\23\24\2\u00bc\2%\3\2\2\2\4\60\3\2\2\2\6;\3\2\2\2\bG\3"+
		"\2\2\2\nO\3\2\2\2\fX\3\2\2\2\16Z\3\2\2\2\20l\3\2\2\2\22n\3\2\2\2\24\u0087"+
		"\3\2\2\2\26\u009a\3\2\2\2\30\u009c\3\2\2\2\32\u009e\3\2\2\2\34\u00a3\3"+
		"\2\2\2\36\u00a5\3\2\2\2 \u00ac\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$\'\3\2\2"+
		"\2%#\3\2\2\2%&\3\2\2\2&+\3\2\2\2\'%\3\2\2\2(*\5\6\4\2)(\3\2\2\2*-\3\2"+
		"\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2-+\3\2\2\2./\7\2\2\3/\3\3\2\2\2\60\61"+
		"\7\r\2\2\61\62\7\f\2\2\62\63\7\34\2\2\63\65\7\3\2\2\64\66\5\b\5\2\65\64"+
		"\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\7\4\2\289\7\5\2\29:\5\f\7\2:"+
		"\5\3\2\2\2;<\7\f\2\2<=\7\34\2\2=?\7\3\2\2>@\5\b\5\2?>\3\2\2\2?@\3\2\2"+
		"\2@A\3\2\2\2AB\7\4\2\2BC\7\5\2\2CD\5\f\7\2DE\7\6\2\2EF\5\20\t\2F\7\3\2"+
		"\2\2GL\5\n\6\2HI\7\7\2\2IK\5\n\6\2JH\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2"+
		"\2\2M\t\3\2\2\2NL\3\2\2\2OP\7\34\2\2PQ\7\5\2\2QR\5\f\7\2R\13\3\2\2\2S"+
		"Y\7\34\2\2TU\5\16\b\2UV\7\b\2\2VW\7\34\2\2WY\3\2\2\2XS\3\2\2\2XT\3\2\2"+
		"\2Y\r\3\2\2\2Z_\7\34\2\2[\\\7\7\2\2\\^\7\34\2\2][\3\2\2\2^a\3\2\2\2_]"+
		"\3\2\2\2_`\3\2\2\2`\17\3\2\2\2a_\3\2\2\2bf\7\t\2\2ce\5\22\n\2dc\3\2\2"+
		"\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\n\2\2jm\5\24"+
		"\13\2km\5\24\13\2lb\3\2\2\2lk\3\2\2\2m\21\3\2\2\2no\7\34\2\2op\7\6\2\2"+
		"pq\5\24\13\2q\23\3\2\2\2rs\b\13\1\2st\7\26\2\2tu\5\24\13\2uv\7\27\2\2"+
		"vw\5\24\13\2wx\7\30\2\2xy\5\24\13\17y\u0088\3\2\2\2z{\7\3\2\2{|\5\24\13"+
		"\2|}\7\4\2\2}\u0088\3\2\2\2~\177\7\22\2\2\177\u0088\5\24\13\r\u0080\u0081"+
		"\7\25\2\2\u0081\u0088\5\24\13\t\u0082\u0088\5\26\f\2\u0083\u0088\5\30"+
		"\r\2\u0084\u0088\5\32\16\2\u0085\u0088\5\34\17\2\u0086\u0088\5\36\20\2"+
		"\u0087r\3\2\2\2\u0087z\3\2\2\2\u0087~\3\2\2\2\u0087\u0080\3\2\2\2\u0087"+
		"\u0082\3\2\2\2\u0087\u0083\3\2\2\2\u0087\u0084\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0087\u0086\3\2\2\2\u0088\u0097\3\2\2\2\u0089\u008a\f\f\2\2\u008a"+
		"\u008b\7 \2\2\u008b\u0096\5\24\13\r\u008c\u008d\f\13\2\2\u008d\u008e\t"+
		"\2\2\2\u008e\u0096\5\24\13\f\u008f\u0090\f\n\2\2\u0090\u0091\7!\2\2\u0091"+
		"\u0096\5\24\13\13\u0092\u0093\f\b\2\2\u0093\u0094\t\3\2\2\u0094\u0096"+
		"\5\24\13\t\u0095\u0089\3\2\2\2\u0095\u008c\3\2\2\2\u0095\u008f\3\2\2\2"+
		"\u0095\u0092\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\25\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7\31\2\2\u009b"+
		"\27\3\2\2\2\u009c\u009d\7\35\2\2\u009d\31\3\2\2\2\u009e\u00a1\7\36\2\2"+
		"\u009f\u00a0\7\13\2\2\u00a0\u00a2\7\36\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\33\3\2\2\2\u00a3\u00a4\7\34\2\2\u00a4\35\3\2\2\2\u00a5"+
		"\u00a6\7\34\2\2\u00a6\u00a8\7\3\2\2\u00a7\u00a9\5 \21\2\u00a8\u00a7\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7\4\2\2\u00ab"+
		"\37\3\2\2\2\u00ac\u00b1\5\24\13\2\u00ad\u00ae\7\7\2\2\u00ae\u00b0\5\24"+
		"\13\2\u00af\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2!\3\2\2\2\u00b3\u00b1\3\2\2\2\21%+\65?LX_fl\u0087"+
		"\u0095\u0097\u00a1\u00a8\u00b1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}