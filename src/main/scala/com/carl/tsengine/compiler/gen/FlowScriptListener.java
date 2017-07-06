// Generated from /home/klangner/workspaces/scala/tsengine/src/main/scala/com/carl/tsengine/compiler/FlowScript.g4 by ANTLR 4.7
package com.carl.tsengine.compiler.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FlowScriptParser}.
 */
public interface FlowScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FlowScriptParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(FlowScriptParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link FlowScriptParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(FlowScriptParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link FlowScriptParser#moduleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterModuleDeclaration(FlowScriptParser.ModuleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FlowScriptParser#moduleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitModuleDeclaration(FlowScriptParser.ModuleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FlowScriptParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(FlowScriptParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FlowScriptParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(FlowScriptParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FlowScriptParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(FlowScriptParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FlowScriptParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(FlowScriptParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link FlowScriptParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(FlowScriptParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link FlowScriptParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(FlowScriptParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link FlowScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FlowScriptParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FlowScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FlowScriptParser.ExpressionContext ctx);
}