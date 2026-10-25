package com.exteragram.messenger.math;

import com.google.android.gms.cast.MediaTrack;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\f¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d8\u0006¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!R\u001d\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160\u001d8\u0006¢\u0006\f\n\u0004\b$\u0010\u001f\u001a\u0004\b%\u0010!R\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001d8\u0006¢\u0006\f\n\u0004\b&\u0010\u001f\u001a\u0004\b'\u0010!R#\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\f0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010\u001fR\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020/0.8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103¨\u00064"}, d2 = {"Lcom/exteragram/messenger/math/MathOperators;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "factorial", "(D)D", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "at", _UrlKt.FRAGMENT_ENCODE_SET, "matchSymbol", "(Ljava/lang/CharSequence;I)Ljava/lang/String;", "symbol", "Lcom/exteragram/messenger/math/InfixOperator;", "infixFor", "(Ljava/lang/String;)Lcom/exteragram/messenger/math/InfixOperator;", "Lcom/exteragram/messenger/math/PrefixOperator;", "prefixFor", "(Ljava/lang/String;)Lcom/exteragram/messenger/math/PrefixOperator;", "Lcom/exteragram/messenger/math/PostfixOperator;", "postfixFor", "(Ljava/lang/String;)Lcom/exteragram/messenger/math/PostfixOperator;", "name", "Lcom/exteragram/messenger/math/MathFunction;", "functionFor", "(Ljava/lang/String;)Lcom/exteragram/messenger/math/MathFunction;", _UrlKt.FRAGMENT_ENCODE_SET, "infix", "Ljava/util/List;", "getInfix", "()Ljava/util/List;", "prefix", "getPrefix", "postfix", "getPostfix", "functions", "getFunctions", _UrlKt.FRAGMENT_ENCODE_SET, "constants", "Ljava/util/Map;", "getConstants", "()Ljava/util/Map;", "symbols", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "symbolChars", "Ljava/util/Set;", "getSymbolChars", "()Ljava/util/Set;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMathOperators.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MathOperators.kt\ncom/exteragram/messenger/math/MathOperators\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,169:1\n296#2,2:170\n296#2,2:172\n296#2,2:174\n296#2,2:176\n296#2,2:178\n1915#2,2:180\n1915#2,2:182\n1915#2,2:184\n1080#2:186\n1915#2:187\n1916#2:190\n1915#2:191\n1916#2:194\n1198#3,2:188\n1198#3,2:192\n*S KotlinDebug\n*F\n+ 1 MathOperators.kt\ncom/exteragram/messenger/math/MathOperators\n*L\n146#1:170,2\n150#1:172,2\n152#1:174,2\n154#1:176,2\n156#1:178,2\n132#1:180,2\n133#1:182,2\n134#1:184,2\n135#1:186\n138#1:187\n138#1:190\n139#1:191\n139#1:194\n138#1:188,2\n139#1:192,2\n*E\n"})
/* loaded from: classes4.dex */
public final class MathOperators {
    public static final MathOperators INSTANCE = null;
    private static final Map<String, Double> constants = null;
    private static final List<MathFunction> functions = null;
    private static final List<InfixOperator> infix = null;
    private static final List<PostfixOperator> postfix = null;
    private static final List<PrefixOperator> prefix = null;
    private static final Set<Character> symbolChars = null;
    private static final List<String> symbols = null;

    public static double $r8$lambda$2Rk8z6aDGS4KecTxiuvdwviQ16M(double[] r2) {
        return ArraysKt.minOrThrow(r2);
    }

    public static double $r8$lambda$4o4A8oyy9h1n8BiLFzNrZD2utZE(double r0, double r2, boolean r4) {
        return Math.pow(r0, r2);
    }

    public static double $r8$lambda$75mGUABpXrRJYWymhp3hrgsn1JE(double[] r2) {
        return Math.cos(r2[0]);
    }

    public static double $r8$lambda$7bQwdoHcZmFo_nG3gAxc4r1rqWc(double[] r2) {
        return Math.signum(r2[0]);
    }

    /* renamed from: $r8$lambda$AMmkZnJFRcYws9V-liBP1uWpeC4, reason: not valid java name */
    public static double m1490$r8$lambda$AMmkZnJFRcYws9VliBP1uWpeC4(double[] r2) {
        return Math.log(r2[0]);
    }

    public static double $r8$lambda$BFooNEdZ4ppOvt8t3v5ZStoXkw8(double[] r2) {
        return Math.abs(r2[0]);
    }

    public static double $r8$lambda$HNwbT9TFboSx1Qv2fFLtoND2t2U(double[] r2) {
        return Math.sin(r2[0]);
    }

    /* renamed from: $r8$lambda$MmIyxf-UIsOxH0sIKT4YPjOKfRA, reason: not valid java name */
    public static double m1491$r8$lambda$MmIyxfUIsOxH0sIKT4YPjOKfRA(double[] r2) {
        return Math.asin(r2[0]);
    }

    /* renamed from: $r8$lambda$NX-VI9Lp4mK_LTV06it5t7lb3Fk, reason: not valid java name */
    public static double m1492$r8$lambda$NXVI9Lp4mK_LTV06it5t7lb3Fk(double r0, double r2, boolean r4) {
        return r0 * r2;
    }

    /* renamed from: $r8$lambda$Ogx3N3ve-Nsr9v5DrWCfTEBnuwM, reason: not valid java name */
    public static double m1493$r8$lambda$Ogx3N3veNsr9v5DrWCfTEBnuwM(double[] r2) {
        return Math.sqrt(r2[0]);
    }

    public static double $r8$lambda$PO40PjyQwlWFy0oXzDdm0Cqpwqg(double r0) {
        return Math.sqrt(r0);
    }

    public static double $r8$lambda$PdmqHd67MmY6SKLIBqdx6ABWaHA(double r0, double r2, boolean r4) {
        if (r4 == false) goto L6;
        return r0 + (r2 * r0);
    L6:
        return r0 + r2;
    }

    public static double $r8$lambda$RACIGCWG1N3j9nuPWknbNZIztF0(double[] r2) {
        return Math.sinh(r2[0]);
    }

    public static double $r8$lambda$S_4WW7PoHbBxDlspQ5N_JRX0z3E(double r0) {
        return r0;
    }

    /* renamed from: $r8$lambda$U0VnCyXeHU4f-aq0CS1bbkcX9mE, reason: not valid java name */
    public static double m1494$r8$lambda$U0VnCyXeHU4faq0CS1bbkcX9mE(double r0) {
        return -r0;
    }

    /* renamed from: $r8$lambda$Xje3POf6ubohXGprq7doAV-cvPM, reason: not valid java name */
    public static double m1495$r8$lambda$Xje3POf6ubohXGprq7doAVcvPM(double[] r2) {
        return Math.tanh(r2[0]);
    }

    public static double $r8$lambda$ZJV0fY8Ytcs8KoC8H2PlPlceQL8(double[] r2) {
        return Math.floor(r2[0]);
    }

    public static double $r8$lambda$_1ulvUA5i0TMrKEIFncewapu3aY(double r2) {
        return r2 / 100.0d;
    }

    /* renamed from: $r8$lambda$_QbFipDllU_sA_-l2MOdybWb9xo, reason: not valid java name */
    public static double m1496$r8$lambda$_QbFipDllU_sA_l2MOdybWb9xo(double[] r4) {
        return Math.atan2(r4[0], r4[1]);
    }

    public static double $r8$lambda$dv2MgMHqYqJ2UfHP1_z_UaEmlQQ(double[] r2) {
        return Math.cbrt(r2[0]);
    }

    public static double $r8$lambda$eFkwgZzavkSxoh7D3Ha8fOUnjdQ(double[] r2) {
        return Math.tan(r2[0]);
    }

    /* renamed from: $r8$lambda$frnPOuiqOprX6cd-U-KqrWu0UzI, reason: not valid java name */
    public static double m1497$r8$lambda$frnPOuiqOprX6cdUKqrWu0UzI(double r1) {
        return INSTANCE.factorial(r1);
    }

    public static double $r8$lambda$gRKKzFyY3BULvqH362lb1lWpdyM(double[] r2) {
        return Math.cosh(r2[0]);
    }

    public static double $r8$lambda$gba_bBoYmuPhNBN1noeZ3r0hSzk(double[] r2) {
        return Math.acos(r2[0]);
    }

    public static double $r8$lambda$iL8tOv0WahoAZ8TcBVaQKXXQYKE(double[] r2) {
        return ArraysKt.maxOrThrow(r2);
    }

    /* renamed from: $r8$lambda$olrIWt3q9nI7wgWYG_Zi-Yb638o, reason: not valid java name */
    public static double m1498$r8$lambda$olrIWt3q9nI7wgWYG_ZiYb638o(double[] r2) {
        return Math.atan(r2[0]);
    }

    public static double $r8$lambda$qDx7KSHZSEy2I8Nv_PQ5qYjADq4(double r0, double r2, boolean r4) {
        return r0 / r2;
    }

    /* renamed from: $r8$lambda$qlBG-24IUBkHUTEadOT3sQTe3Fo, reason: not valid java name */
    public static double m1499$r8$lambda$qlBG24IUBkHUTEadOT3sQTe3Fo(double[] r2) {
        return MathKt.roundToLong(r2[0]);
    }

    /* renamed from: $r8$lambda$sdagQo9XABXCk-W290RSZx00_1Q, reason: not valid java name */
    public static double m1500$r8$lambda$sdagQo9XABXCkW290RSZx00_1Q(double r0, double r2, boolean r4) {
        if (r4 == false) goto L6;
        return r0 - (r2 * r0);
    L6:
        return r0 - r2;
    }

    public static double $r8$lambda$tl4DjQxRPwDtDgcLf0fYWGZNevg(double[] r2) {
        return Math.exp(r2[0]);
    }

    public static double $r8$lambda$v2zGK8XNIbQzOYhdxkH3hjvk8mI(double[] r2) {
        return Math.log10(r2[0]);
    }

    public static double $r8$lambda$yxbgL8Ya1uvJ_YY0KlDPg8OJMM8(double[] r2) {
        return MathKt.log2(r2[0]);
    }

    public static double $r8$lambda$yyQ5mLbxNq2LYWahhjgfmM_rIek(double[] r3) {
        return INSTANCE.factorial(r3[0]);
    }

    /* renamed from: $r8$lambda$z-xkn3Jihi-SFkaoi8s2F00KsIg, reason: not valid java name */
    public static double m1501$r8$lambda$zxkn3JihiSFkaoi8s2F00KsIg(double[] r2) {
        return Math.ceil(r2[0]);
    }

    static {
        INSTANCE = new MathOperators();
        List<InfixOperator> r1 = CollectionsKt.listOf(new InfixOperator[]{new InfixOperator(CollectionsKt.listOf("+"), 10, false, new MathOperators$$ExternalSyntheticLambda0(), 4, null), new InfixOperator(CollectionsKt.listOf(new String[]{"-", "−"}), 10, false, new MathOperators$$ExternalSyntheticLambda11(), 4, null), new InfixOperator(CollectionsKt.listOf(new String[]{"*", "×"}), 20, false, new MathOperators$$ExternalSyntheticLambda22(), 4, null), new InfixOperator(CollectionsKt.listOf(new String[]{"/", "÷"}), 20, false, new MathOperators$$ExternalSyntheticLambda27(), 4, null), new InfixOperator(CollectionsKt.listOf("^"), 40, true, new MathOperators$$ExternalSyntheticLambda28())});
        infix = r1;
        prefix = CollectionsKt.listOf(new PrefixOperator[]{new PrefixOperator(CollectionsKt.listOf(new String[]{"-", "−"}), 30, false, new MathOperators$$ExternalSyntheticLambda29(), 4, null), new PrefixOperator(CollectionsKt.listOf("+"), 30, false, new MathOperators$$ExternalSyntheticLambda30(), 4, null), new PrefixOperator(CollectionsKt.listOf("√"), 40, true, new MathOperators$$ExternalSyntheticLambda31())});
        postfix = CollectionsKt.listOf(new PostfixOperator[]{new PostfixOperator(CollectionsKt.listOf("%"), true, new MathOperators$$ExternalSyntheticLambda32()), new PostfixOperator(CollectionsKt.listOf("!"), false, new MathOperators$$ExternalSyntheticLambda33(), 2, null)});
        functions = CollectionsKt.listOf(new MathFunction[]{new MathFunction("sqrt", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda1()), new MathFunction("cbrt", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda2()), new MathFunction("abs", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda3()), new MathFunction(MediaTrack.ROLE_SIGN, new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda4()), new MathFunction("round", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda5()), new MathFunction("floor", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda6()), new MathFunction("ceil", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda7()), new MathFunction("fact", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda8()), new MathFunction("min", new IntRange(1, Integer.MAX_VALUE), new MathOperators$$ExternalSyntheticLambda9()), new MathFunction("max", new IntRange(1, Integer.MAX_VALUE), new MathOperators$$ExternalSyntheticLambda10()), new MathFunction("log", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda12()), new MathFunction("log2", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda13()), new MathFunction("ln", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda14()), new MathFunction("exp", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda15()), new MathFunction("sin", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda16()), new MathFunction("cos", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda17()), new MathFunction("tan", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda18()), new MathFunction("asin", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda19()), new MathFunction("acos", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda20()), new MathFunction("atan", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda21()), new MathFunction("atan2", new IntRange(2, 2), new MathOperators$$ExternalSyntheticLambda23()), new MathFunction("sinh", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda24()), new MathFunction("cosh", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda25()), new MathFunction("tanh", new IntRange(1, 1), new MathOperators$$ExternalSyntheticLambda26())});
        Double r0 = Double.valueOf(3.141592653589793d);
        constants = MapsKt.mapOf(new Pair[]{TuplesKt.to("pi", r0), TuplesKt.to("π", r0), TuplesKt.to("e", Double.valueOf(2.718281828459045d))});
        List r02 = CollectionsKt.createListBuilder();
        Iterator<T> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r02.addAll(((InfixOperator) r12.next()).getSymbols());
        goto L4
    L6:
        Iterator<T> r13 = prefix.iterator();
    L8:
        if (r13.hasNext() == false) goto L10;
        r02.addAll(((PrefixOperator) r13.next()).getSymbols());
        goto L8
    L10:
        Iterator<T> r14 = postfix.iterator();
    L12:
        if (r14.hasNext() == false) goto L14;
        r02.addAll(((PostfixOperator) r14.next()).getSymbols());
        goto L12
    L14:
        List<String> r03 = CollectionsKt.sortedWith(CollectionsKt.distinct(CollectionsKt.build(r02)), new MathOperators$special$$inlined$sortedByDescending$1());
        symbols = r03;
        Set r15 = SetsKt.createSetBuilder();
        Iterator<T> r04 = r03.iterator();
    L15:
        int r3 = 0;
        if (r04.hasNext() == false) goto L21;
        String r2 = (String) r04.next();
    L19:
        if (r3 >= r2.length()) goto L15;
        r15.add(Character.valueOf(r2.charAt(r3)));
        r3 = r3 + 1;
        goto L19
    L21:
        Iterator<T> r05 = constants.keySet().iterator();
    L23:
        if (r05.hasNext() == false) goto L31;
        String r22 = (String) r05.next();
        int r4 = 0;
    L26:
        if (r4 >= r22.length()) goto L23;
        char r5 = r22.charAt(r4);
        if (Character.isLetter(r5) == true) goto L30;
        r15.add(Character.valueOf(r5));
    L30:
        r4 = r4 + 1;
        goto L26
    L31:
        r15.add('(');
        r15.add(')');
        r15.add(';');
        symbolChars = SetsKt.build(r15);
    }

    private MathOperators() {
    }

    private final double factorial(double r4) {
        if (r4 >= 0.0d) goto L5;
        return Double.NaN;
    L5:
        if (r4 == Math.floor(r4)) goto L7;
        return Double.NaN;
    L7:
        if (r4 > 170.0d) goto L19;
        int r3 = (int) r4;
        double r42 = 1.0d;
        int r0 = 2;
        if (2 > r3) goto L14;
    L11:
        r42 = r42 * r0;
        if (r0 == r3) goto L14;
        r0 = r0 + 1;
    L14:
        return r42;
    L19:
        return Double.NaN;
    }

    public final MathFunction functionFor(String r3) {
        Iterator<T> r2 = functions.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (Intrinsics.areEqual(((MathFunction) r0).getName(), r3) == false) goto L4;
    L10:
        return (MathFunction) r0;
    L8:
        r0 = null;
        goto L10
    }

    public final Map<String, Double> getConstants() {
        return constants;
    }

    public final Set<Character> getSymbolChars() {
        return symbolChars;
    }

    public final InfixOperator infixFor(String r3) {
        Iterator<T> r2 = infix.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (((InfixOperator) r0).getSymbols().contains(r3) == false) goto L4;
    L10:
        return (InfixOperator) r0;
    L8:
        r0 = null;
        goto L10
    }

    public final String matchSymbol(CharSequence r10, int r11) {
        Iterator<T> r9 = symbols.iterator();
    L4:
        if (r9.hasNext() == false) goto L12;
        Object r0 = r9.next();
        String r3 = (String) r0;
        if ((r3.length() + r11) > r10.length()) goto L10;
        CharSequence r1 = r10;
        int r2 = r11;
        if (StringsKt.regionMatches$default(r1, r2, r3, 0, r3.length(), false, 16, null) == false) goto L11;
    L14:
        return (String) r0;
    L11:
        r10 = r1;
        r11 = r2;
        goto L4
    L10:
        r1 = r10;
        r2 = r11;
        goto L11
    L12:
        r0 = null;
        goto L14
    }

    public final PostfixOperator postfixFor(String r3) {
        Iterator<T> r2 = postfix.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (((PostfixOperator) r0).getSymbols().contains(r3) == false) goto L4;
    L10:
        return (PostfixOperator) r0;
    L8:
        r0 = null;
        goto L10
    }

    public final PrefixOperator prefixFor(String r3) {
        Iterator<T> r2 = prefix.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (((PrefixOperator) r0).getSymbols().contains(r3) == false) goto L4;
    L10:
        return (PrefixOperator) r0;
    L8:
        r0 = null;
        goto L10
    }
}
