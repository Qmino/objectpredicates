package com.qmino.objectpredicates.evaluator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qmino.objectpredicates.PredicateConstructionException;
import com.qmino.objectpredicates.antlr.PredicateGrammarBaseVisitor;
import com.qmino.objectpredicates.antlr.PredicateGrammarParser;

public class Visitor<T> extends PredicateGrammarBaseVisitor<ObjectPredicate<T>> {

    private final Class<T> target;

    public Visitor(Class<T> target) {
        this.target = target;
    }

    @Override
    public ObjectPredicate<T> visitStart(PredicateGrammarParser.StartContext ctx) {
        if (ctx.getChildCount() == 2 && ctx.getChild(1).getText().equals("<EOF>")) {
            ctx.removeLastChild();
        } else if (ctx.getChildCount() > 2) {
            throw new PredicateConstructionException("More than one expression found. Please check your brackets.");
        }
        return super.visitStart(ctx);
    }

    @Override
    public ObjectPredicate<T> visitNumberInExpression(PredicateGrammarParser.NumberInExpressionContext ctx) {
        List<BigDecimal> children = new ArrayList<>();
        for (int i = 3; i < ctx.getChildCount(); i = i + 2) {
            children.add(new BigDecimal(ctx.getChild(i).getText()));
        }
        return new NumberInExpression<>(
                ctx.getChild(0).getText(),
                children,
                target);
    }

    @Override
    public ObjectPredicate<T> visitStringInExpression(PredicateGrammarParser.StringInExpressionContext ctx) {
        List<String> children = new ArrayList<>();
        for (int i = 3; i < ctx.getChildCount(); i = i + 2) {
            String currentChild = ctx.getChild(i).getText();
            if (currentChild.startsWith("\"") && currentChild.endsWith("\"")) {
                children.add(currentChild.substring(1, currentChild.length() - 1));
            } else {
                // This should not happen, as the parser should have caught this
                throw new IllegalArgumentException("In operator requires literals");
            }
        }
        return new StringInExpression<>(
                ctx.getChild(0).getText(),
                children,
                target);
    }

    @Override
    public ObjectPredicate<T> visitDateOperatorExpression(PredicateGrammarParser.DateOperatorExpressionContext ctx) {
        return new LocalDateTimeExpression<>(ctx.getChild(0).getText(),
                Operator.fromText(ctx.OPERATOR().getText()),
                ctx.getChild(2).getText(),
                target);
    }

    @Override
    public ObjectPredicate<T> visitNumberOperatorExpression(PredicateGrammarParser.NumberOperatorExpressionContext ctx) {
        return new NumberExpression<>(ctx.getChild(0).getText(),
                Operator.fromText(ctx.OPERATOR().getText()),
                ctx.getChild(2).getText(),
                target);
    }

    @Override
    public ObjectPredicate<T> visitStringOperatorExpression(PredicateGrammarParser.StringOperatorExpressionContext ctx) {
        return new StringExpression<>(ctx.getChild(0).getText(),
                Operator.fromText(ctx.OPERATOR().getText()),
                ctx.getChild(2).getText(),
                target);
    }

    @Override
    public ObjectPredicate<T> visitMethodBooleanExpression(PredicateGrammarParser.MethodBooleanExpressionContext ctx) {
        return new BooleanExpression<>(ctx.getChild(0).getText(),
                Operator.fromText(ctx.OPERATOR().getText()),
                ctx.getChild(2).getText(),
                target);
    }

    @Override
    public ObjectPredicate<T> visitDoubleMethodOperatorExpression(PredicateGrammarParser.DoubleMethodOperatorExpressionContext ctx) {
        return new DoubleMethodExpression<>(ctx.getChild(0).getText(),
                Operator.fromText(ctx.OPERATOR().getText()),
                ctx.getChild(2).getText(),
                target);
    }

    @Override
    public ObjectPredicate<T> visitBracketExpression(PredicateGrammarParser.BracketExpressionContext ctx) {
        return this.visit(ctx.expression());
    }

    @Override
    public ObjectPredicate<T> visitAndExpression(PredicateGrammarParser.AndExpressionContext ctx) {
        List<ObjectPredicate<T>> selectors = new ArrayList<>();
        for (PredicateGrammarParser.ExpressionContext child : ctx.expression()) {
            selectors.add(this.visit(child));
        }
        return new AndExpression<>(selectors);
    }

    @Override
    public ObjectPredicate<T> visitOrExpression(PredicateGrammarParser.OrExpressionContext ctx) {
        List<ObjectPredicate<T>> selectors = new ArrayList<>();
        for (PredicateGrammarParser.ExpressionContext child : ctx.expression()) {
            selectors.add(this.visit(child));
        }
        return new OrExpression<>(selectors);
    }

    @Override
    public ObjectPredicate<T> visitNotExpression(PredicateGrammarParser.NotExpressionContext ctx) {
        List<ObjectPredicate<T>> selectors = new ArrayList<>();
        selectors.add(this.visit(ctx.expression()));
        return new NotExpression<>(selectors);
    }
}
