/*
 * MIT License
 *
 * Copyright (c) 2024 Yves Vandewoude / Qmino BV
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.qmino.objectpredicates;

import com.qmino.objectpredicates.antlr.PredicateGrammarLexer;
import com.qmino.objectpredicates.antlr.PredicateGrammarParser;
import com.qmino.objectpredicates.evaluator.ObjectPredicate;
import com.qmino.objectpredicates.evaluator.Visitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

/**
 * Main factory class for object predicates.
 * @author Yves Vandewoude
 */
public class PredicateFactory {

    public static <T> Predicate<T> of(String condition, Class<T> target) {
        return of(condition, target, true);
    }

    public static <T> Predicate<T> of(String condition, Class<T> target, boolean ignoreIntermediateNulls) {
        try {
            InputStream input = new ByteArrayInputStream(condition.getBytes());
            PredicateGrammarLexer lexer = new PredicateGrammarLexer(CharStreams.fromStream(input));
            PredicateGrammarParser parser = new PredicateGrammarParser(new CommonTokenStream(lexer));
            Visitor<T> visitor = new Visitor<>(target);
            ObjectPredicate<T> result = visitor.visit(parser.start());
            if (result == null) {
                throw new PredicateConstructionException("Invalid query");
            } else {
                result.setIgnoreIntermediateNulls(ignoreIntermediateNulls);
                return result;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
