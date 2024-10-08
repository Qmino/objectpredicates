// Generated from C:/Users/Yves/Work/Code/qmino/JavaFieldSelector/src/main/java/com/qmino/objectpredicates/PredicateGrammar.g4 by ANTLR 4.13.1

package com.qmino.objectpredicates.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PredicateGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, WS=6, AND=7, OR=8, NOT=9, IN=10, 
		OPERATOR=11, NULLVALUE=12, BOOLEAN=13, METHOD=14, NUMBER=15, STRING=16, 
		DATE=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "WS", "AND", "OR", "NOT", "IN", 
			"OPERATOR", "NULLVALUE", "BOOLEAN", "METHOD", "NUMBER", "STRING", "DATE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "WS", "AND", "OR", "NOT", "IN", "OPERATOR", 
			"NULLVALUE", "BOOLEAN", "METHOD", "NUMBER", "STRING", "DATE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public PredicateGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PredicateGrammar.g4"; }

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
		"\u0004\u0000\u0011\u00b5\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0004\u0005/\b\u0005\u000b\u0005"+
		"\f\u00050\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006;\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007A\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bJ\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\tP\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\nm\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000bw\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u008b\b\f\u0001\r\u0001\r\u0005\r\u008f"+
		"\b\r\n\r\f\r\u0092\t\r\u0001\u000e\u0004\u000e\u0095\b\u000e\u000b\u000e"+
		"\f\u000e\u0096\u0001\u000f\u0001\u000f\u0004\u000f\u009b\b\u000f\u000b"+
		"\u000f\f\u000f\u009c\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00b4"+
		"\b\u0010\u0000\u0000\u0011\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011\u0001\u0000\b\u0003"+
		"\u0000\t\n\r\r  \u0002\u0000<<>>\u0003\u0000AZ__az\u0005\u0000..09AZ_"+
		"_az\u0002\u0000..09\u0004\u0000%%09AZaz\u0001\u000009\u0001\u0000--\u00ca"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000\u0003%\u0001\u0000\u0000"+
		"\u0000\u0005\'\u0001\u0000\u0000\u0000\u0007)\u0001\u0000\u0000\u0000"+
		"\t+\u0001\u0000\u0000\u0000\u000b.\u0001\u0000\u0000\u0000\r:\u0001\u0000"+
		"\u0000\u0000\u000f@\u0001\u0000\u0000\u0000\u0011I\u0001\u0000\u0000\u0000"+
		"\u0013O\u0001\u0000\u0000\u0000\u0015l\u0001\u0000\u0000\u0000\u0017v"+
		"\u0001\u0000\u0000\u0000\u0019\u008a\u0001\u0000\u0000\u0000\u001b\u008c"+
		"\u0001\u0000\u0000\u0000\u001d\u0094\u0001\u0000\u0000\u0000\u001f\u0098"+
		"\u0001\u0000\u0000\u0000!\u00a0\u0001\u0000\u0000\u0000#$\u0005{\u0000"+
		"\u0000$\u0002\u0001\u0000\u0000\u0000%&\u0005,\u0000\u0000&\u0004\u0001"+
		"\u0000\u0000\u0000\'(\u0005}\u0000\u0000(\u0006\u0001\u0000\u0000\u0000"+
		")*\u0005(\u0000\u0000*\b\u0001\u0000\u0000\u0000+,\u0005)\u0000\u0000"+
		",\n\u0001\u0000\u0000\u0000-/\u0007\u0000\u0000\u0000.-\u0001\u0000\u0000"+
		"\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001\u0000"+
		"\u0000\u000012\u0001\u0000\u0000\u000023\u0006\u0005\u0000\u00003\f\u0001"+
		"\u0000\u0000\u000045\u0005A\u0000\u000056\u0005N\u0000\u00006;\u0005D"+
		"\u0000\u000078\u0005a\u0000\u000089\u0005n\u0000\u00009;\u0005d\u0000"+
		"\u0000:4\u0001\u0000\u0000\u0000:7\u0001\u0000\u0000\u0000;\u000e\u0001"+
		"\u0000\u0000\u0000<=\u0005O\u0000\u0000=A\u0005R\u0000\u0000>?\u0005o"+
		"\u0000\u0000?A\u0005r\u0000\u0000@<\u0001\u0000\u0000\u0000@>\u0001\u0000"+
		"\u0000\u0000A\u0010\u0001\u0000\u0000\u0000BC\u0005N\u0000\u0000CD\u0005"+
		"O\u0000\u0000DJ\u0005T\u0000\u0000EF\u0005n\u0000\u0000FG\u0005o\u0000"+
		"\u0000GJ\u0005t\u0000\u0000HJ\u0005!\u0000\u0000IB\u0001\u0000\u0000\u0000"+
		"IE\u0001\u0000\u0000\u0000IH\u0001\u0000\u0000\u0000J\u0012\u0001\u0000"+
		"\u0000\u0000KL\u0005I\u0000\u0000LP\u0005N\u0000\u0000MN\u0005i\u0000"+
		"\u0000NP\u0005n\u0000\u0000OK\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000"+
		"\u0000P\u0014\u0001\u0000\u0000\u0000QR\u0005=\u0000\u0000Rm\u0005=\u0000"+
		"\u0000ST\u0005!\u0000\u0000Tm\u0005=\u0000\u0000Um\u0007\u0001\u0000\u0000"+
		"VW\u0005>\u0000\u0000Wm\u0005=\u0000\u0000XY\u0005<\u0000\u0000Ym\u0005"+
		"=\u0000\u0000Z[\u0005I\u0000\u0000[\\\u0005L\u0000\u0000\\]\u0005I\u0000"+
		"\u0000]^\u0005K\u0000\u0000^m\u0005E\u0000\u0000_`\u0005i\u0000\u0000"+
		"`a\u0005l\u0000\u0000ab\u0005i\u0000\u0000bc\u0005k\u0000\u0000cm\u0005"+
		"e\u0000\u0000de\u0005L\u0000\u0000ef\u0005I\u0000\u0000fg\u0005K\u0000"+
		"\u0000gm\u0005E\u0000\u0000hi\u0005l\u0000\u0000ij\u0005i\u0000\u0000"+
		"jk\u0005k\u0000\u0000km\u0005e\u0000\u0000lQ\u0001\u0000\u0000\u0000l"+
		"S\u0001\u0000\u0000\u0000lU\u0001\u0000\u0000\u0000lV\u0001\u0000\u0000"+
		"\u0000lX\u0001\u0000\u0000\u0000lZ\u0001\u0000\u0000\u0000l_\u0001\u0000"+
		"\u0000\u0000ld\u0001\u0000\u0000\u0000lh\u0001\u0000\u0000\u0000m\u0016"+
		"\u0001\u0000\u0000\u0000no\u0005n\u0000\u0000op\u0005u\u0000\u0000pq\u0005"+
		"l\u0000\u0000qw\u0005l\u0000\u0000rs\u0005N\u0000\u0000st\u0005U\u0000"+
		"\u0000tu\u0005L\u0000\u0000uw\u0005L\u0000\u0000vn\u0001\u0000\u0000\u0000"+
		"vr\u0001\u0000\u0000\u0000w\u0018\u0001\u0000\u0000\u0000xy\u0005t\u0000"+
		"\u0000yz\u0005r\u0000\u0000z{\u0005u\u0000\u0000{\u008b\u0005e\u0000\u0000"+
		"|}\u0005f\u0000\u0000}~\u0005a\u0000\u0000~\u007f\u0005l\u0000\u0000\u007f"+
		"\u0080\u0005s\u0000\u0000\u0080\u008b\u0005e\u0000\u0000\u0081\u0082\u0005"+
		"T\u0000\u0000\u0082\u0083\u0005R\u0000\u0000\u0083\u0084\u0005U\u0000"+
		"\u0000\u0084\u008b\u0005E\u0000\u0000\u0085\u0086\u0005F\u0000\u0000\u0086"+
		"\u0087\u0005A\u0000\u0000\u0087\u0088\u0005L\u0000\u0000\u0088\u0089\u0005"+
		"S\u0000\u0000\u0089\u008b\u0005E\u0000\u0000\u008ax\u0001\u0000\u0000"+
		"\u0000\u008a|\u0001\u0000\u0000\u0000\u008a\u0081\u0001\u0000\u0000\u0000"+
		"\u008a\u0085\u0001\u0000\u0000\u0000\u008b\u001a\u0001\u0000\u0000\u0000"+
		"\u008c\u0090\u0007\u0002\u0000\u0000\u008d\u008f\u0007\u0003\u0000\u0000"+
		"\u008e\u008d\u0001\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000"+
		"\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091\u001c\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000"+
		"\u0093\u0095\u0007\u0004\u0000\u0000\u0094\u0093\u0001\u0000\u0000\u0000"+
		"\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000"+
		"\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u001e\u0001\u0000\u0000\u0000"+
		"\u0098\u009a\u0005\"\u0000\u0000\u0099\u009b\u0007\u0005\u0000\u0000\u009a"+
		"\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d"+
		"\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005\"\u0000\u0000\u009f "+
		"\u0001\u0000\u0000\u0000\u00a0\u00a1\u0007\u0006\u0000\u0000\u00a1\u00a2"+
		"\u0007\u0006\u0000\u0000\u00a2\u00a3\u0007\u0006\u0000\u0000\u00a3\u00a4"+
		"\u0007\u0006\u0000\u0000\u00a4\u00a5\u0007\u0007\u0000\u0000\u00a5\u00a6"+
		"\u0007\u0006\u0000\u0000\u00a6\u00a7\u0007\u0006\u0000\u0000\u00a7\u00a8"+
		"\u0007\u0007\u0000\u0000\u00a8\u00a9\u0007\u0006\u0000\u0000\u00a9\u00b3"+
		"\u0007\u0006\u0000\u0000\u00aa\u00ab\u0005T\u0000\u0000\u00ab\u00ac\u0007"+
		"\u0006\u0000\u0000\u00ac\u00ad\u0007\u0006\u0000\u0000\u00ad\u00ae\u0005"+
		":\u0000\u0000\u00ae\u00af\u0007\u0006\u0000\u0000\u00af\u00b0\u0007\u0006"+
		"\u0000\u0000\u00b0\u00b1\u0005:\u0000\u0000\u00b1\u00b2\u0007\u0006\u0000"+
		"\u0000\u00b2\u00b4\u0007\u0006\u0000\u0000\u00b3\u00aa\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\"\u0001\u0000\u0000\u0000"+
		"\r\u00000:@IOlv\u008a\u0090\u0096\u009c\u00b3\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}