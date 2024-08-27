// Generated from C:/Users/Yves/Work/Code/qmino/JavaFieldSelector/src/main/java/com/qmino/objectpredicates/PredicateGrammar.g4 by ANTLR 4.13.1

package com.qmino.objectpredicates.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PredicateGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PredicateGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(PredicateGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PredicateGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#numberInExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberInExpression(PredicateGrammarParser.NumberInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#stringInExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringInExpression(PredicateGrammarParser.StringInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#dateOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateOperatorExpression(PredicateGrammarParser.DateOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#numberOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberOperatorExpression(PredicateGrammarParser.NumberOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#stringOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringOperatorExpression(PredicateGrammarParser.StringOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#doubleMethodOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleMethodOperatorExpression(PredicateGrammarParser.DoubleMethodOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#methodBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBooleanExpression(PredicateGrammarParser.MethodBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#bracketExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketExpression(PredicateGrammarParser.BracketExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(PredicateGrammarParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#orExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(PredicateGrammarParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PredicateGrammarParser#notExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(PredicateGrammarParser.NotExpressionContext ctx);
}