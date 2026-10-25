package com.exteragram.messenger.math;

import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004$%&'B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ)\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0016\u0010\u0015J!\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006("}, d2 = {"Lcom/exteragram/messenger/math/MathExpression;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "from", "to", _UrlKt.FRAGMENT_ENCODE_SET, "looksLikeDate", "(Ljava/lang/CharSequence;II)Z", "end", "nextCandidate", "(Ljava/lang/CharSequence;II)Ljava/lang/Integer;", "equalsIndex", "expressionStart", "(Ljava/lang/CharSequence;I)Ljava/lang/Integer;", _UrlKt.FRAGMENT_ENCODE_SET, "c", "isBlank", "(C)Z", "isExpressionChar", "expression", "Lcom/exteragram/messenger/math/MathOptions;", "options", "Lcom/exteragram/messenger/math/MathResult;", "evaluate", "(Ljava/lang/CharSequence;Lcom/exteragram/messenger/math/MathOptions;)Lcom/exteragram/messenger/math/MathResult;", "caret", "Lcom/exteragram/messenger/math/MathSuggestion;", "suggestionAt", "(Ljava/lang/CharSequence;ILcom/exteragram/messenger/math/MathOptions;)Lcom/exteragram/messenger/math/MathSuggestion;", "Lcom/exteragram/messenger/math/MathExpression$MathParseError;", "error", "Lcom/exteragram/messenger/math/MathExpression$MathParseError;", "MathParseError", "Token", "Operand", "Parser", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MathExpression {
    public static final MathExpression INSTANCE = null;
    private static final MathParseError error = null;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/exteragram/messenger/math/MathExpression$MathParseError;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "<init>", "()V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MathParseError extends RuntimeException {
        public MathParseError() {
            super(null, null, false, false);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/math/MathExpression$Operand;", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "isPercent", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(DZ)V", "getValue", "()D", "()Z", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Operand {
        private final boolean isPercent;
        private final double value;

        public Operand(double r1, boolean r3) {
            this.value = r1;
            this.isPercent = r3;
        }

        public final double getValue() {
            return this.value;
        }

        public final boolean isPercent() {
            return this.isPercent;
        }
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002J\b\u0010\u001a\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\nH\u0002J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\rH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\rH\u0002J\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006("}, d2 = {"Lcom/exteragram/messenger/math/MathExpression$Parser;", _UrlKt.FRAGMENT_ENCODE_SET, "source", _UrlKt.FRAGMENT_ENCODE_SET, "options", "Lcom/exteragram/messenger/math/MathOptions;", "<init>", "(Ljava/lang/CharSequence;Lcom/exteragram/messenger/math/MathOptions;)V", "tokens", "Ljava/util/ArrayList;", "Lcom/exteragram/messenger/math/MathExpression$Token;", "Lkotlin/collections/ArrayList;", "position", _UrlKt.FRAGMENT_ENCODE_SET, "depth", "hasOperation", _UrlKt.FRAGMENT_ENCODE_SET, "separator", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Character;", "parse", "Lcom/exteragram/messenger/math/MathResult;", "tokenize", _UrlKt.FRAGMENT_ENCODE_SET, "readNumber", "from", "peek", "next", "expect", "type", "parseExpression", "Lcom/exteragram/messenger/math/MathExpression$Operand;", "minPrecedence", "parseUnary", "parsePostfix", "parsePrimary", _UrlKt.FRAGMENT_ENCODE_SET, "parseIdentifier", "name", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMathExpression.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MathExpression.kt\ncom/exteragram/messenger/math/MathExpression$Parser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,370:1\n1#2:371\n*E\n"})
    public static final class Parser {
        private int depth;
        private boolean hasOperation;
        private final MathOptions options;
        private int position;
        private Character separator;
        private final CharSequence source;
        private final ArrayList<Token> tokens;

        public Parser(CharSequence r1, MathOptions r2) {
            this.source = r1;
            this.options = r2;
            this.tokens = new ArrayList();
        }

        private final Token expect(int r2) {
            Token r1 = next();
            if (r1.getType() != r2) goto L6;
            return r1;
        L6:
            throw MathExpression.access$getError$p();
        }

        private final Token next() {
            ArrayList<Token> r0 = this.tokens;
            int r1 = this.position;
            this.position = r1 + 1;
            return r0.get(r1);
        }

        private final Operand parseExpression(int r8) {
            int r0 = this.depth + 1;
            this.depth = r0;
            if (r0 > 32) goto L26;
            Operand r02 = parseUnary();     // Catch: Throwable -> L18
        L5:
            Token r2 = peek();     // Catch: Throwable -> L18
            if (r2.getType() != 1) goto L13;
            InfixOperator r22 = MathOperators.INSTANCE.infixFor(r2.getText());     // Catch: Throwable -> L18
            if (r22 == null) goto L13;
            if (r22.getPrecedence() < r8) goto L13;
            next();     // Catch: Throwable -> L18
            if (r22.getRightAssociative() == false) goto L20;
            int r3 = r22.getPrecedence();     // Catch: Throwable -> L18
        L21:
            Operand r32 = parseExpression(r3);     // Catch: Throwable -> L18
            this.hasOperation = true;     // Catch: Throwable -> L18
            r02 = new Operand(r22.getApply().invoke(Double.valueOf(r02.getValue()), Double.valueOf(r32.getValue()), Boolean.valueOf(r32.isPercent())).doubleValue(), false);     // Catch: Throwable -> L18
            goto L5
        L20:
            r3 = r22.getPrecedence() + 1;     // Catch: Throwable -> L18
        L13:
            this.depth--;
            return r02;
        L18:
            th = move-exception;
            this.depth--;
            throw th;
        L26:
            throw MathExpression.access$getError$p();
        }

        private final double parseIdentifier(String r6) {
            MathOperators r0 = MathOperators.INSTANCE;
            Double r1 = r0.getConstants().get(r6);
            if (r1 != null) goto L5;
            MathFunction r62 = r0.functionFor(r6);
            if (r62 == null) goto L22;
            expect(2);
            ArrayList r12 = new ArrayList(2);
            if (peek().getType() == 3) goto L14;
            r12.add(Double.valueOf(parseExpression(0).getValue()));
        L12:
            if (peek().getType() != 5) goto L14;
            next();
            r12.add(Double.valueOf(parseExpression(0).getValue()));
        L14:
            expect(3);
            IntRange r02 = r62.getArity();
            int r2 = r02.getFirst();
            int r03 = r02.getLast();
            int r3 = r12.size();
            if (r2 > r3) goto L20;
            if (r3 > r03) goto L20;
            this.hasOperation = true;
            return r62.getApply().invoke(CollectionsKt.toDoubleArray(r12)).doubleValue();
        L20:
            throw MathExpression.access$getError$p();
        L22:
            throw MathExpression.access$getError$p();
        L5:
            return r1.doubleValue();
        }

        private final Operand parsePostfix() {
            double r0 = parsePrimary();
            boolean r2 = false;
        L3:
            Token r3 = peek();
            if (r3.getType() != 1) goto L9;
            PostfixOperator r32 = MathOperators.INSTANCE.postfixFor(r3.getText());
            if (r32 == null) goto L9;
            next();
            r0 = r32.getApply().invoke(Double.valueOf(r0)).doubleValue();
            this.hasOperation = true;
            r2 = r32.getPercent();
        L9:
            return new Operand(r0, r2);
        }

        private final double parsePrimary() {
            Token r0 = next();
            int r1 = r0.getType();
            if (r1 == 0) goto L15;
            if (r1 != 2) goto L7;
            Operand r02 = parseExpression(0);
            expect(3);
            return r02.getValue();
        L7:
            if (r1 != 4) goto L11;
            return parseIdentifier(r0.getText());
        L11:
            throw MathExpression.access$getError$p();
        L15:
            return r0.getNumber();
        }

        private final Operand parseUnary() {
            Token r0 = peek();
            if (r0.getType() != 1) goto L12;
            PrefixOperator r02 = MathOperators.INSTANCE.prefixFor(r0.getText());
            if (r02 == null) goto L12;
            next();
            Operand r1 = parseExpression(r02.getPrecedence());
            if (r02.getOperation() == false) goto L10;
            this.hasOperation = true;
        L10:
            return new Operand(r02.getApply().invoke(Double.valueOf(r1.getValue())).doubleValue(), r1.isPercent());
        L12:
            return parsePostfix();
        }

        private final Token peek() {
            return this.tokens.get(this.position);
        }

        private final int readNumber(int r9) {
            int r0 = r9;
        L4:
            if (r0 >= this.source.length()) goto L8;
            if (Character.isDigit(this.source.charAt(r0)) == false) goto L8;
            r0 = r0 + 1;
        L8:
            int r1 = r0 - r9;
            if (r0 >= this.source.length()) goto L11;
            char r2 = this.source.charAt(r0);
        L13:
            if (r2 == '.') goto L15;
            if (r2 == ',') goto L15;
        L41:
            int r3 = 0;
        L42:
            if (r1 != 0) goto L47;
            if (r3 != 0) goto L47;
            throw MathExpression.access$getError$p();
        L47:
            String r92 = StringsKt.replace$default(this.source.subSequence(r9, r0).toString(), ',', '.', false, 4, null);
            ArrayList<Token> r8 = this.tokens;
            Double r93 = StringsKt.toDoubleOrNull(r92);
            if (r93 == null) goto L52;
            r8.add(new Token(0, r93.doubleValue(), null, 4, null));
            return r0;
        L52:
            throw MathExpression.access$getError$p();
        L15:
            int r32 = r0 + 1;
            if (r32 >= this.source.length()) goto L41;
            if (Character.isDigit(this.source.charAt(r32)) == false) goto L41;
            r0 = r32;
        L21:
            if (r0 >= this.source.length()) goto L25;
            if (Character.isDigit(this.source.charAt(r0)) == false) goto L25;
            r0 = r0 + 1;
        L25:
            r3 = r0 - r32;
            if (r2 != ',') goto L39;
            if (r3 != 3) goto L39;
            if (1 > r1) goto L39;
            if (r1 >= 4) goto L39;
            if (this.source.charAt(r9) == '0') goto L39;
            throw MathExpression.access$getError$p();
        L39:
            if (this.separator != null) goto L42;
            this.separator = Character.valueOf(r2);
            goto L42
        L11:
            r2 = ' ';
            goto L13
        }

        private final void tokenize() {
            int r0 = 0;
        L4:
            if (r0 >= this.source.length()) goto L45;
            char r1 = this.source.charAt(r0);
            if (MathExpression.access$isBlank(MathExpression.INSTANCE, r1) == true) goto L7;
            if (Character.isDigit(r1) == true) goto L44;
            if (r1 != '.') goto L13;
        L14:
            int r2 = r0 + 1;
            if (r2 >= this.source.length()) goto L20;
            if (Character.isDigit(this.source.charAt(r2)) == true) goto L44;
        L20:
            if (r1 == '(') goto L21;
            if (r1 == ')') goto L24;
            if (r1 == ';') goto L27;
            if (Character.isLetter(r1) == true) goto L30;
            String r7 = MathOperators.INSTANCE.matchSymbol(this.source, r0);
            if (r7 == null) goto L43;
            this.tokens.add(new Token(1, 0.0d, r7, 2, null));
            r0 = r0 + r7.length();
            goto L4
        L43:
            throw MathExpression.access$getError$p();
        L30:
            int r12 = r0;
        L32:
            if (r12 >= this.source.length()) goto L38;
            if (Character.isLetter(this.source.charAt(r12)) == true) goto L37;
            if (Character.isDigit(this.source.charAt(r12)) == false) goto L38;
        L37:
            r12 = r12 + 1;
        L38:
            this.tokens.add(new Token(4, 0.0d, this.source.subSequence(r0, r12).toString().toLowerCase(Locale.ROOT), 2, null));
            r0 = r12;
            goto L4
        L27:
            this.tokens.add(new Token(5, 0.0d, null, 6, null));
            goto L7
        L24:
            this.tokens.add(new Token(3, 0.0d, null, 6, null));
            goto L7
        L21:
            this.tokens.add(new Token(2, 0.0d, null, 6, null));
            goto L7
        L13:
            if (r1 != ',') goto L20;
        L44:
            r0 = readNumber(r0);
        L7:
            r0 = r0 + 1;
            goto L4
        L45:
            this.tokens.add(new Token(6, 0.0d, null, 6, null));
        }

        public final MathResult parse() {
            tokenize();
            Operand r0 = parseExpression(0);
            expect(6);
            double r2 = r0.getValue();
            boolean r02 = this.hasOperation;
            Character r4 = this.separator;
            if (r4 == null) goto L5;
            char r5 = r4.charValue();
        L7:
            return new MathResult(r2, r02, r5);
        L5:
            r5 = this.options.getDecimalSeparator();
            goto L7
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/math/MathExpression$Token;", _UrlKt.FRAGMENT_ENCODE_SET, "type", _UrlKt.FRAGMENT_ENCODE_SET, "number", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(IDLjava/lang/String;)V", "getType", "()I", "getNumber", "()D", "getText", "()Ljava/lang/String;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Token {
        private final double number;
        private final String text;
        private final int type;

        public Token(int r1, double r2, String r4) {
            this.type = r1;
            this.number = r2;
            this.text = r4;
        }

        public /* synthetic */ Token(int r1, double r2, String r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 2) == 0) goto L6;
            r2 = 0.0d;
        L6:
            if ((r5 & 4) == 0) goto L8;
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            this(r1, r2, r4);
        }

        public final double getNumber() {
            return this.number;
        }

        public final String getText() {
            return this.text;
        }

        public final int getType() {
            return this.type;
        }
    }

    static {
        INSTANCE = new MathExpression();
        error = new MathParseError();
    }

    private MathExpression() {
    }

    public static final /* synthetic */ MathParseError access$getError$p() {
        return error;
    }

    public static final /* synthetic */ boolean access$isBlank(MathExpression r0, char r1) {
        return r0.isBlank(r1);
    }

    private final Integer expressionStart(CharSequence r4, int r5) {
        int r0 = Math.max(0, r5 - 64);
        int r1 = r5 - 1;
    L3:
        if (r1 < r0) goto L7;
        if (isExpressionChar(r4.charAt(r1)) == false) goto L7;
        r1 = r1 - 1;
    L7:
        r1 = r1 + 1;
        if (r1 >= r5) goto L13;
        if (isBlank(r4.charAt(r1)) == true) goto L7;
    L13:
        if (r1 < r5) goto L15;
        return null;
    L15:
        if (r1 <= 0) goto L22;
        char r42 = r4.charAt(r1 - 1);
        if (Character.isLetterOrDigit(r42) == false) goto L19;
    L20:
        return null;
    L19:
        if (r42 == '_') goto L20;
    L22:
        return Integer.valueOf(r1);
    }

    private final boolean isBlank(char r1) {
        if (r1 != ' ') goto L5;
        return true;
    L5:
        if (r1 != '\t') goto L7;
        return true;
    L7:
        if (r1 == 160) goto L14;
        return false;
    L14:
        return true;
    }

    private final boolean isExpressionChar(char r2) {
        if (Character.isDigit(r2) == false) goto L5;
        return true;
    L5:
        if (Character.isLetter(r2) == false) goto L7;
        return true;
    L7:
        if (r2 != '.') goto L9;
        return true;
    L9:
        if (r2 != ',') goto L11;
        return true;
    L11:
        if (isBlank(r2) == false) goto L13;
        return true;
    L13:
        if (MathOperators.INSTANCE.getSymbolChars().contains(Character.valueOf(r2)) == true) goto L23;
        return false;
    L23:
        return true;
    }

    private final boolean looksLikeDate(CharSequence r9, int r10, int r11) {
    L2:
        if (r10 >= r11) goto L6;
        if (isBlank(r9.charAt(r10)) == false) goto L6;
        r10 = r10 + 1;
    L6:
        if (r11 <= r10) goto L11;
        if (isBlank(r9.charAt(r11 - 1)) == false) goto L11;
        r11 = r11 - 1;
    L11:
        if ((r11 - r10) >= 6) goto L13;
        return false;
    L13:
        ArrayList r8 = new ArrayList(4);
        int r3 = 0;
        char r4 = ' ';
    L15:
        if (r10 >= r11) goto L37;
        char r6 = r9.charAt(r10);
        if (Character.isDigit(r6) == false) goto L20;
        r3 = r3 + 1;
    L35:
        r10 = r10 + 1;
        goto L15
    L20:
        if (r6 != '/') goto L22;
    L26:
        if (r4 != ' ') goto L28;
        r4 = r6;
    L30:
        if (r3 == 0) goto L36;
        if (r8.size() == 2) goto L36;
        r8.add(Integer.valueOf(r3));
        r3 = 0;
    L36:
        return false;
    L28:
        if (r4 == r6) goto L30;
        return false;
    L22:
        if (r6 == '.') goto L26;
        if (r6 == '-') goto L26;
        return false;
    L37:
        if (r3 != 0) goto L39;
    L70:
        return false;
    L39:
        if (r8.size() != 2) goto L70;
        r8.add(Integer.valueOf(r3));
        switch(r4) {
            case 45: goto L60;
            case 46: goto L45;
            case 47: goto L45;
            default: goto L43;
        };
    L43:
        return false;
    L60:
        Integer r102 = (Integer) r8.get(0);
        if (r102 == null) goto L70;
        if (r102.intValue() != 4) goto L70;
        if (((Number) r8.get(1)).intValue() > 2) goto L70;
        if (((Number) r8.get(2)).intValue() > 2) goto L70;
        return true;
    L45:
        if (((Number) r8.get(0)).intValue() <= 2) goto L47;
    L59:
        return false;
    L47:
        if (((Number) r8.get(1)).intValue() > 2) goto L59;
        Integer r103 = (Integer) r8.get(2);
        if (r103 != null) goto L52;
    L53:
        Integer r82 = (Integer) r8.get(2);
        if (r82 == null) goto L59;
        if (r82.intValue() != 4) goto L59;
    L58:
        return true;
    L52:
        if (r103.intValue() == 2) goto L58;
        goto L53
    }

    private final Integer nextCandidate(CharSequence r4, int r5, int r6) {
    L3:
        if (r5 >= r6) goto L17;
        char r1 = r4.charAt(r5);
        r5 = r5 + 1;
        if (r1 == '(') goto L7;
        if (isBlank(r1) == false) goto L3;
    L10:
        if (r5 >= r6) goto L14;
        if (isBlank(r4.charAt(r5)) == false) goto L14;
        r5 = r5 + 1;
    L14:
        if (r5 >= r6) goto L17;
        return Integer.valueOf(r5);
    L7:
        return Integer.valueOf(r5);
    L17:
        return null;
    }

    public final MathResult evaluate(CharSequence r1, MathOptions r2) {
        return new Parser(r1, r2).parse();
    L4:
        return null;
    }

    public final MathSuggestion suggestionAt(CharSequence r6, int r7, MathOptions r8) {
        if (r7 > 0) goto L5;
    L46:
        return null;
    L5:
        if (r7 > r6.length()) goto L46;
        int r1 = r7 - 1;
        if (r6.charAt(r1) != '=') goto L46;
        int r2 = r7;
    L11:
        if (r2 >= r6.length()) goto L16;
        if (isBlank(r6.charAt(r2)) == false) goto L16;
        r2 = r2 + 1;
    L16:
        if (r2 >= r6.length()) goto L24;
        char r22 = r6.charAt(r2);
        if (Character.isDigit(r22) == false) goto L20;
    L23:
        return null;
    L20:
        if (r22 == '.') goto L23;
        if (r22 == ',') goto L23;
    L24:
        Integer r23 = expressionStart(r6, r1);
        if (r23 == null) goto L46;
        int r24 = r23.intValue();
    L28:
        if (looksLikeDate(r6, r24, r1) == true) goto L43;
        StringBuilder r3 = new StringBuilder(r1 - r24);
        r3.append(r6, r24, r1);
        MathResult r32 = evaluate(r3, r8);
        if (r32 == null) goto L43;
        if (r32.getHasOperation() == true) goto L34;
        return null;
    L34:
        String r82 = MathFormat.INSTANCE.format(r32.getValue(), r32.getDecimalSeparator());
        if (r82 != null) goto L38;
        return null;
    L38:
        if (isBlank(r6.charAt(r7 - 2)) == false) goto L40;
        String r5 = " ".concat(r82);
    L42:
        return new MathSuggestion(r7, r5, r82);
    L40:
        r5 = r82;
    L43:
        Integer r25 = nextCandidate(r6, r24, r1);
        if (r25 == null) goto L46;
        r24 = r25.intValue();
        goto L28
    }
}
