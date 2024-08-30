// Generated from C:/Users/Yves/Work/Code/qmino/JavaFieldSelector/src/main/java/com/qmino/objectpredicates/PredicateGrammar.g4 by ANTLR 4.13.1

package com.qmino.objectpredicates.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PredicateGrammarParser}.
 */
public interface PredicateGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(PredicateGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(PredicateGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PredicateGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PredicateGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSingleExpression(PredicateGrammarParser.SingleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSingleExpression(PredicateGrammarParser.SingleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#numberInExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumberInExpression(PredicateGrammarParser.NumberInExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#numberInExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumberInExpression(PredicateGrammarParser.NumberInExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#stringInExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringInExpression(PredicateGrammarParser.StringInExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#stringInExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringInExpression(PredicateGrammarParser.StringInExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#dateOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterDateOperatorExpression(PredicateGrammarParser.DateOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#dateOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitDateOperatorExpression(PredicateGrammarParser.DateOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#numberOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumberOperatorExpression(PredicateGrammarParser.NumberOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#numberOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumberOperatorExpression(PredicateGrammarParser.NumberOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#stringOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringOperatorExpression(PredicateGrammarParser.StringOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#stringOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringOperatorExpression(PredicateGrammarParser.StringOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#doubleMethodOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterDoubleMethodOperatorExpression(PredicateGrammarParser.DoubleMethodOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#doubleMethodOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitDoubleMethodOperatorExpression(PredicateGrammarParser.DoubleMethodOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#methodBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterMethodBooleanExpression(PredicateGrammarParser.MethodBooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#methodBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitMethodBooleanExpression(PredicateGrammarParser.MethodBooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#multipleExpressions}.
	 * @param ctx the parse tree
	 */
	void enterMultipleExpressions(PredicateGrammarParser.MultipleExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#multipleExpressions}.
	 * @param ctx the parse tree
	 */
	void exitMultipleExpressions(PredicateGrammarParser.MultipleExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#bracketExpression}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpression(PredicateGrammarParser.BracketExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#bracketExpression}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpression(PredicateGrammarParser.BracketExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(PredicateGrammarParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(PredicateGrammarParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(PredicateGrammarParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(PredicateGrammarParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PredicateGrammarParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(PredicateGrammarParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PredicateGrammarParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(PredicateGrammarParser.NotExpressionContext ctx);
}