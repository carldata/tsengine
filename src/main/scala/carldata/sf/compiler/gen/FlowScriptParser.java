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
		RULE_functionBody = 7, RULE_assignment = 8, RULE_expression = 9, RULE_boolLiteral = 10, 
		RULE_nullLiteral = 11, RULE_stringLiteral = 12, RULE_numberLiteral = 13, 
		RULE_variableExpr = 14, RULE_funApp = 15, RULE_expressionList = 16;
	public static final String[] ruleNames = {
		"compilationUnit", "externalFunDef", "functionDefinition", "paramList", 
		"param", "typeDefinition", "typeList", "functionBody", "assignment", "expression", 
		"boolLiteral", "nullLiteral", "stringLiteral", "numberLiteral", "variableExpr", 
		"funApp", "expressionList"
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
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXTERNAL) {
				{
				{
				setState(34);
				externalFunDef();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEF) {
				{
				{
				setState(40);
				functionDefinition();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
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
			setState(48);
			match(EXTERNAL);
			setState(49);
			match(DEF);
			setState(50);
			match(Identifier);
			setState(51);
			match(T__0);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(52);
				paramList();
				}
			}

			setState(55);
			match(T__1);
			setState(56);
			match(T__2);
			setState(57);
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
			setState(59);
			match(DEF);
			setState(60);
			match(Identifier);
			setState(61);
			match(T__0);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(62);
				paramList();
				}
			}

			setState(65);
			match(T__1);
			setState(66);
			match(T__2);
			setState(67);
			typeDefinition();
			setState(68);
			match(T__3);
			setState(69);
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
			setState(71);
			param();
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(72);
				match(T__4);
				setState(73);
				param();
				}
				}
				setState(78);
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
			setState(79);
			match(Identifier);
			setState(80);
			match(T__2);
			setState(81);
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
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(84);
				typeList();
				setState(85);
				match(T__5);
				setState(86);
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
			setState(90);
			match(Identifier);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(91);
				match(T__4);
				setState(92);
				match(Identifier);
				}
				}
				setState(97);
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
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(T__6);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Identifier) {
					{
					{
					setState(99);
					assignment();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(105);
				match(T__7);
				setState(106);
				expression(0);
				}
				break;
			case T__0:
			case TRUE:
			case FALSE:
			case MINUS:
			case NEG:
			case IF:
			case NULL:
			case Identifier:
			case QuotedString:
			case Integer:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
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
			setState(110);
			match(Identifier);
			setState(111);
			match(T__3);
			setState(112);
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
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(115);
				match(IF);
				setState(116);
				((ExpressionContext)_localctx).ifExpr = expression(0);
				setState(117);
				match(THEN);
				setState(118);
				expression(0);
				setState(119);
				match(ELSE);
				setState(120);
				expression(14);
				}
				break;
			case 2:
				{
				setState(122);
				match(T__0);
				setState(123);
				expression(0);
				setState(124);
				match(T__1);
				}
				break;
			case 3:
				{
				setState(126);
				((ExpressionContext)_localctx).minusOp = match(MINUS);
				setState(127);
				expression(12);
				}
				break;
			case 4:
				{
				setState(128);
				((ExpressionContext)_localctx).negOp = match(NEG);
				setState(129);
				expression(8);
				}
				break;
			case 5:
				{
				setState(130);
				boolLiteral();
				}
				break;
			case 6:
				{
				setState(131);
				nullLiteral();
				}
				break;
			case 7:
				{
				setState(132);
				stringLiteral();
				}
				break;
			case 8:
				{
				setState(133);
				numberLiteral();
				}
				break;
			case 9:
				{
				setState(134);
				variableExpr();
				}
				break;
			case 10:
				{
				setState(135);
				funApp();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(152);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(150);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(138);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(139);
						match(MultiplyOp);
						setState(140);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(141);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(142);
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
						setState(143);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(144);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(145);
						match(RelationOp);
						setState(146);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(147);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(148);
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
						setState(149);
						expression(8);
						}
						break;
					}
					} 
				}
				setState(154);
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
		enterRule(_localctx, 20, RULE_boolLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
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

	public static class NullLiteralContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(FlowScriptParser.NULL, 0); }
		public NullLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullLiteral; }
	}

	public final NullLiteralContext nullLiteral() throws RecognitionException {
		NullLiteralContext _localctx = new NullLiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_nullLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
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
		enterRule(_localctx, 24, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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
		enterRule(_localctx, 26, RULE_numberLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(Integer);
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(162);
				match(T__8);
				setState(163);
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
		enterRule(_localctx, 28, RULE_variableExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
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
		enterRule(_localctx, 30, RULE_funApp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(Identifier);
			setState(169);
			match(T__0);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << TRUE) | (1L << FALSE) | (1L << MINUS) | (1L << NEG) | (1L << IF) | (1L << NULL) | (1L << Identifier) | (1L << QuotedString) | (1L << Integer))) != 0)) {
				{
				setState(170);
				expressionList();
				}
			}

			setState(173);
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
		enterRule(_localctx, 32, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			expression(0);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(176);
				match(T__4);
				setState(177);
				expression(0);
				}
				}
				setState(182);
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
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00ba\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\7\2&\n\2\f\2\16\2)\13\2\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\5\38\n\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4B\n\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5M\n\5\f\5\16\5P\13\5\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\5\7[\n\7\3\b\3\b\3\b\7\b`\n\b\f\b\16\bc\13\b\3\t"+
		"\3\t\7\tg\n\t\f\t\16\tj\13\t\3\t\3\t\3\t\5\to\n\t\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008b\n\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0099\n\13\f\13\16\13\u009c"+
		"\13\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\5\17\u00a7\n\17\3\20\3"+
		"\20\3\21\3\21\3\21\5\21\u00ae\n\21\3\21\3\21\3\22\3\22\3\22\7\22\u00b5"+
		"\n\22\f\22\16\22\u00b8\13\22\3\22\2\3\24\23\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"\2\5\3\2\21\22\3\2\23\24\3\2\17\20\2\u00c1\2\'\3\2\2\2"+
		"\4\62\3\2\2\2\6=\3\2\2\2\bI\3\2\2\2\nQ\3\2\2\2\fZ\3\2\2\2\16\\\3\2\2\2"+
		"\20n\3\2\2\2\22p\3\2\2\2\24\u008a\3\2\2\2\26\u009d\3\2\2\2\30\u009f\3"+
		"\2\2\2\32\u00a1\3\2\2\2\34\u00a3\3\2\2\2\36\u00a8\3\2\2\2 \u00aa\3\2\2"+
		"\2\"\u00b1\3\2\2\2$&\5\4\3\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2"+
		"(-\3\2\2\2)\'\3\2\2\2*,\5\6\4\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2"+
		"\2.\60\3\2\2\2/-\3\2\2\2\60\61\7\2\2\3\61\3\3\2\2\2\62\63\7\r\2\2\63\64"+
		"\7\f\2\2\64\65\7\34\2\2\65\67\7\3\2\2\668\5\b\5\2\67\66\3\2\2\2\678\3"+
		"\2\2\289\3\2\2\29:\7\4\2\2:;\7\5\2\2;<\5\f\7\2<\5\3\2\2\2=>\7\f\2\2>?"+
		"\7\34\2\2?A\7\3\2\2@B\5\b\5\2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\7\4\2\2"+
		"DE\7\5\2\2EF\5\f\7\2FG\7\6\2\2GH\5\20\t\2H\7\3\2\2\2IN\5\n\6\2JK\7\7\2"+
		"\2KM\5\n\6\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\t\3\2\2\2PN\3\2"+
		"\2\2QR\7\34\2\2RS\7\5\2\2ST\5\f\7\2T\13\3\2\2\2U[\7\34\2\2VW\5\16\b\2"+
		"WX\7\b\2\2XY\7\34\2\2Y[\3\2\2\2ZU\3\2\2\2ZV\3\2\2\2[\r\3\2\2\2\\a\7\34"+
		"\2\2]^\7\7\2\2^`\7\34\2\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\17"+
		"\3\2\2\2ca\3\2\2\2dh\7\t\2\2eg\5\22\n\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2"+
		"hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7\n\2\2lo\5\24\13\2mo\5\24\13\2nd\3\2"+
		"\2\2nm\3\2\2\2o\21\3\2\2\2pq\7\34\2\2qr\7\6\2\2rs\5\24\13\2s\23\3\2\2"+
		"\2tu\b\13\1\2uv\7\26\2\2vw\5\24\13\2wx\7\27\2\2xy\5\24\13\2yz\7\30\2\2"+
		"z{\5\24\13\20{\u008b\3\2\2\2|}\7\3\2\2}~\5\24\13\2~\177\7\4\2\2\177\u008b"+
		"\3\2\2\2\u0080\u0081\7\22\2\2\u0081\u008b\5\24\13\16\u0082\u0083\7\25"+
		"\2\2\u0083\u008b\5\24\13\n\u0084\u008b\5\26\f\2\u0085\u008b\5\30\r\2\u0086"+
		"\u008b\5\32\16\2\u0087\u008b\5\34\17\2\u0088\u008b\5\36\20\2\u0089\u008b"+
		"\5 \21\2\u008at\3\2\2\2\u008a|\3\2\2\2\u008a\u0080\3\2\2\2\u008a\u0082"+
		"\3\2\2\2\u008a\u0084\3\2\2\2\u008a\u0085\3\2\2\2\u008a\u0086\3\2\2\2\u008a"+
		"\u0087\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b\u009a\3\2"+
		"\2\2\u008c\u008d\f\r\2\2\u008d\u008e\7 \2\2\u008e\u0099\5\24\13\16\u008f"+
		"\u0090\f\f\2\2\u0090\u0091\t\2\2\2\u0091\u0099\5\24\13\r\u0092\u0093\f"+
		"\13\2\2\u0093\u0094\7!\2\2\u0094\u0099\5\24\13\f\u0095\u0096\f\t\2\2\u0096"+
		"\u0097\t\3\2\2\u0097\u0099\5\24\13\n\u0098\u008c\3\2\2\2\u0098\u008f\3"+
		"\2\2\2\u0098\u0092\3\2\2\2\u0098\u0095\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\25\3\2\2\2\u009c\u009a\3\2\2"+
		"\2\u009d\u009e\t\4\2\2\u009e\27\3\2\2\2\u009f\u00a0\7\31\2\2\u00a0\31"+
		"\3\2\2\2\u00a1\u00a2\7\35\2\2\u00a2\33\3\2\2\2\u00a3\u00a6\7\36\2\2\u00a4"+
		"\u00a5\7\13\2\2\u00a5\u00a7\7\36\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3"+
		"\2\2\2\u00a7\35\3\2\2\2\u00a8\u00a9\7\34\2\2\u00a9\37\3\2\2\2\u00aa\u00ab"+
		"\7\34\2\2\u00ab\u00ad\7\3\2\2\u00ac\u00ae\5\"\22\2\u00ad\u00ac\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\7\4\2\2\u00b0!\3"+
		"\2\2\2\u00b1\u00b6\5\24\13\2\u00b2\u00b3\7\7\2\2\u00b3\u00b5\5\24\13\2"+
		"\u00b4\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7#\3\2\2\2\u00b8\u00b6\3\2\2\2\21\'-\67ANZahn\u008a\u0098"+
		"\u009a\u00a6\u00ad\u00b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}