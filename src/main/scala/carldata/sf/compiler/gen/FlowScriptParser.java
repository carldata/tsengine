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
		AND=17, OR=18, NEG=19, IF=20, THEN=21, ELSE=22, WS=23, LINE_COMMENT=24, 
		Identifier=25, QuotedString=26, Integer=27, AddOp=28, MultiplyOp=29, RelationOp=30;
	public static final int
		RULE_compilationUnit = 0, RULE_externalFunDef = 1, RULE_functionDefinition = 2, 
		RULE_paramList = 3, RULE_param = 4, RULE_typeDefinition = 5, RULE_functionBody = 6, 
		RULE_assignment = 7, RULE_expression = 8, RULE_boolLiteral = 9, RULE_stringLiteral = 10, 
		RULE_numberLiteral = 11, RULE_variableExpr = 12, RULE_funApp = 13, RULE_expressionList = 14;
	public static final String[] ruleNames = {
		"compilationUnit", "externalFunDef", "functionDefinition", "paramList", 
		"param", "typeDefinition", "functionBody", "assignment", "expression", 
		"boolLiteral", "stringLiteral", "numberLiteral", "variableExpr", "funApp", 
		"expressionList"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "','", "'=>'", "'let'", "'in'", "'.'", 
		"'def'", "'external'", "'module'", "'True'", "'False'", "'+'", "'-'", 
		"'&&'", "'||'", "'!'", "'if'", "'then'", "'else'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "DEF", "EXTERNAL", 
		"MODULE", "TRUE", "FALSE", "PLUS", "MINUS", "AND", "OR", "NEG", "IF", 
		"THEN", "ELSE", "WS", "LINE_COMMENT", "Identifier", "QuotedString", "Integer", 
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
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXTERNAL) {
				{
				{
				setState(30);
				externalFunDef();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEF) {
				{
				{
				setState(36);
				functionDefinition();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
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
			setState(44);
			match(EXTERNAL);
			setState(45);
			match(DEF);
			setState(46);
			match(Identifier);
			setState(47);
			match(T__0);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(48);
				paramList();
				}
			}

			setState(51);
			match(T__1);
			setState(52);
			match(T__2);
			setState(53);
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
			setState(55);
			match(DEF);
			setState(56);
			match(Identifier);
			setState(57);
			match(T__0);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(58);
				paramList();
				}
			}

			setState(61);
			match(T__1);
			setState(62);
			match(T__2);
			setState(63);
			typeDefinition();
			setState(64);
			match(T__3);
			setState(65);
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
			setState(67);
			param();
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(68);
				match(T__4);
				setState(69);
				param();
				}
				}
				setState(74);
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
			setState(75);
			match(Identifier);
			setState(76);
			match(T__2);
			setState(77);
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
		public List<TerminalNode> Identifier() { return getTokens(FlowScriptParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(FlowScriptParser.Identifier, i);
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
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(80);
				match(Identifier);
				setState(81);
				match(T__5);
				setState(82);
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
		enterRule(_localctx, 12, RULE_functionBody);
		int _la;
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(T__6);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Identifier) {
					{
					{
					setState(86);
					assignment();
					}
					}
					setState(91);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(92);
				match(T__7);
				setState(93);
				expression(0);
				}
				break;
			case T__0:
			case TRUE:
			case FALSE:
			case MINUS:
			case NEG:
			case IF:
			case Identifier:
			case QuotedString:
			case Integer:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
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
		enterRule(_localctx, 14, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(Identifier);
			setState(98);
			match(T__3);
			setState(99);
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
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(102);
				match(IF);
				setState(103);
				((ExpressionContext)_localctx).ifExpr = expression(0);
				setState(104);
				match(THEN);
				setState(105);
				expression(0);
				setState(106);
				match(ELSE);
				setState(107);
				expression(13);
				}
				break;
			case 2:
				{
				setState(109);
				match(T__0);
				setState(110);
				expression(0);
				setState(111);
				match(T__1);
				}
				break;
			case 3:
				{
				setState(113);
				((ExpressionContext)_localctx).minusOp = match(MINUS);
				setState(114);
				expression(11);
				}
				break;
			case 4:
				{
				setState(115);
				((ExpressionContext)_localctx).negOp = match(NEG);
				setState(116);
				expression(7);
				}
				break;
			case 5:
				{
				setState(117);
				boolLiteral();
				}
				break;
			case 6:
				{
				setState(118);
				stringLiteral();
				}
				break;
			case 7:
				{
				setState(119);
				numberLiteral();
				}
				break;
			case 8:
				{
				setState(120);
				variableExpr();
				}
				break;
			case 9:
				{
				setState(121);
				funApp();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(136);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(125);
						match(MultiplyOp);
						setState(126);
						expression(11);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(127);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(128);
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
						setState(129);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(130);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(131);
						match(RelationOp);
						setState(132);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(133);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(134);
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
						setState(135);
						expression(7);
						}
						break;
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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

	public static class BoolLiteralContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(FlowScriptParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(FlowScriptParser.FALSE, 0); }
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_boolLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode QuotedString() { return getToken(FlowScriptParser.QuotedString, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
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
		enterRule(_localctx, 22, RULE_numberLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(Integer);
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(146);
				match(T__8);
				setState(147);
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
		enterRule(_localctx, 24, RULE_variableExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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
		enterRule(_localctx, 26, RULE_funApp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(Identifier);
			setState(153);
			match(T__0);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << TRUE) | (1L << FALSE) | (1L << MINUS) | (1L << NEG) | (1L << IF) | (1L << Identifier) | (1L << QuotedString) | (1L << Integer))) != 0)) {
				{
				setState(154);
				expressionList();
				}
			}

			setState(157);
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
		enterRule(_localctx, 28, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			expression(0);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(160);
				match(T__4);
				setState(161);
				expression(0);
				}
				}
				setState(166);
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
		case 8:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00aa\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\7\2\"\n\2\f\2\16"+
		"\2%\13\2\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3\64"+
		"\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4>\n\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5"+
		"\7V\n\7\3\b\3\b\7\bZ\n\b\f\b\16\b]\13\b\3\b\3\b\3\b\5\bb\n\b\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\5\n}\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\n\u008b\n\n\f\n\16\n\u008e\13\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\r\5\r\u0097\n\r\3\16\3\16\3\17\3\17\3\17\5\17\u009e\n\17\3\17\3\17\3"+
		"\20\3\20\3\20\7\20\u00a5\n\20\f\20\16\20\u00a8\13\20\3\20\2\3\22\21\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\5\3\2\21\22\3\2\23\24\3\2\17\20"+
		"\2\u00b1\2#\3\2\2\2\4.\3\2\2\2\69\3\2\2\2\bE\3\2\2\2\nM\3\2\2\2\fU\3\2"+
		"\2\2\16a\3\2\2\2\20c\3\2\2\2\22|\3\2\2\2\24\u008f\3\2\2\2\26\u0091\3\2"+
		"\2\2\30\u0093\3\2\2\2\32\u0098\3\2\2\2\34\u009a\3\2\2\2\36\u00a1\3\2\2"+
		"\2 \"\5\4\3\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$)\3\2\2\2%#\3\2"+
		"\2\2&(\5\6\4\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3"+
		"\2\2\2,-\7\2\2\3-\3\3\2\2\2./\7\r\2\2/\60\7\f\2\2\60\61\7\33\2\2\61\63"+
		"\7\3\2\2\62\64\5\b\5\2\63\62\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\66"+
		"\7\4\2\2\66\67\7\5\2\2\678\5\f\7\28\5\3\2\2\29:\7\f\2\2:;\7\33\2\2;=\7"+
		"\3\2\2<>\5\b\5\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\7\4\2\2@A\7\5\2\2AB\5"+
		"\f\7\2BC\7\6\2\2CD\5\16\b\2D\7\3\2\2\2EJ\5\n\6\2FG\7\7\2\2GI\5\n\6\2H"+
		"F\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\t\3\2\2\2LJ\3\2\2\2MN\7\33\2"+
		"\2NO\7\5\2\2OP\5\f\7\2P\13\3\2\2\2QV\7\33\2\2RS\7\33\2\2ST\7\b\2\2TV\7"+
		"\33\2\2UQ\3\2\2\2UR\3\2\2\2V\r\3\2\2\2W[\7\t\2\2XZ\5\20\t\2YX\3\2\2\2"+
		"Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7\n\2\2_b\5\22"+
		"\n\2`b\5\22\n\2aW\3\2\2\2a`\3\2\2\2b\17\3\2\2\2cd\7\33\2\2de\7\6\2\2e"+
		"f\5\22\n\2f\21\3\2\2\2gh\b\n\1\2hi\7\26\2\2ij\5\22\n\2jk\7\27\2\2kl\5"+
		"\22\n\2lm\7\30\2\2mn\5\22\n\17n}\3\2\2\2op\7\3\2\2pq\5\22\n\2qr\7\4\2"+
		"\2r}\3\2\2\2st\7\22\2\2t}\5\22\n\ruv\7\25\2\2v}\5\22\n\tw}\5\24\13\2x"+
		"}\5\26\f\2y}\5\30\r\2z}\5\32\16\2{}\5\34\17\2|g\3\2\2\2|o\3\2\2\2|s\3"+
		"\2\2\2|u\3\2\2\2|w\3\2\2\2|x\3\2\2\2|y\3\2\2\2|z\3\2\2\2|{\3\2\2\2}\u008c"+
		"\3\2\2\2~\177\f\f\2\2\177\u0080\7\37\2\2\u0080\u008b\5\22\n\r\u0081\u0082"+
		"\f\13\2\2\u0082\u0083\t\2\2\2\u0083\u008b\5\22\n\f\u0084\u0085\f\n\2\2"+
		"\u0085\u0086\7 \2\2\u0086\u008b\5\22\n\13\u0087\u0088\f\b\2\2\u0088\u0089"+
		"\t\3\2\2\u0089\u008b\5\22\n\t\u008a~\3\2\2\2\u008a\u0081\3\2\2\2\u008a"+
		"\u0084\3\2\2\2\u008a\u0087\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\23\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090"+
		"\t\4\2\2\u0090\25\3\2\2\2\u0091\u0092\7\34\2\2\u0092\27\3\2\2\2\u0093"+
		"\u0096\7\35\2\2\u0094\u0095\7\13\2\2\u0095\u0097\7\35\2\2\u0096\u0094"+
		"\3\2\2\2\u0096\u0097\3\2\2\2\u0097\31\3\2\2\2\u0098\u0099\7\33\2\2\u0099"+
		"\33\3\2\2\2\u009a\u009b\7\33\2\2\u009b\u009d\7\3\2\2\u009c\u009e\5\36"+
		"\20\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\7\4\2\2\u00a0\35\3\2\2\2\u00a1\u00a6\5\22\n\2\u00a2\u00a3\7\7\2"+
		"\2\u00a3\u00a5\5\22\n\2\u00a4\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\37\3\2\2\2\u00a8\u00a6\3\2\2"+
		"\2\20#)\63=JU[a|\u008a\u008c\u0096\u009d\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}