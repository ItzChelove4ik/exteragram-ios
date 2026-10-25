package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\u00020\u0002H\u0086\u0080\u0004\u001a\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001a<\u0010\t\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a1\u0010\u000f\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010\u0010\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010\u0011j\n\u0012\u0006\b\u0000\u0012\u00020\u0001`\u0012H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0013\u001a\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001a<\u0010\u0015\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a1\u0010\u0016\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010\u0010\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010\u0011j\n\u0012\u0006\b\u0000\u0012\u00020\u0001`\u0012H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0013\u001a*\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00180\rH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b\u0019\u001a*\u0010\u0017\u001a\u00020\u001a*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001a0\rH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"}, d2 = {"elementAt", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "toSortedSet", "Ljava/util/SortedSet;", "max", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "maxBy", "R", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_SELECTOR, "Lkotlin/Function1;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
@SourceDebugExtension({"SMAP\n_StringsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _StringsJvm.kt\nkotlin/text/StringsKt___StringsJvmKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,108:1\n1279#2,14:109\n1603#2,14:123\n*S KotlinDebug\n*F\n+ 1 _StringsJvm.kt\nkotlin/text/StringsKt___StringsJvmKt\n*L\n45#1:109,14\n66#1:123,14\n*E\n"})
/* loaded from: classes.dex */
class StringsKt___StringsJvmKt extends StringsKt__StringsKt {
    public StringsKt___StringsJvmKt() {
    }

    @InlineOnly
    private static final char elementAt(CharSequence r0, int r1) {
        return r0.charAt(r1);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character max(CharSequence r0) {
        return StringsKt___StringsKt.maxOrNull(r0);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Character maxBy(CharSequence r7, Function1<? super Character, ? extends R> r8) {
        if (r7.length() != 0) goto L6;
        return null;
    L6:
        char r0 = r7.charAt(0);
        int r1 = StringsKt__StringsKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(Character.valueOf(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        char r4 = r7.charAt(r3);
        R r5 = r8.invoke(Character.valueOf(r4));
        if (r2.compareTo(r5) >= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return Character.valueOf(r0);
    L9:
        return Character.valueOf(r0);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character maxWith(CharSequence r0, Comparator r1) {
        return StringsKt___StringsKt.maxWithOrNull(r0, r1);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character min(CharSequence r0) {
        return StringsKt___StringsKt.minOrNull(r0);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Character minBy(CharSequence r7, Function1<? super Character, ? extends R> r8) {
        if (r7.length() != 0) goto L6;
        return null;
    L6:
        char r0 = r7.charAt(0);
        int r1 = StringsKt__StringsKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(Character.valueOf(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        char r4 = r7.charAt(r3);
        R r5 = r8.invoke(Character.valueOf(r4));
        if (r2.compareTo(r5) <= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return Character.valueOf(r0);
    L9:
        return Character.valueOf(r0);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character minWith(CharSequence r0, Comparator r1) {
        return StringsKt___StringsKt.minWithOrNull(r0, r1);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(CharSequence r3, Function1<? super Character, ? extends BigDecimal> r4) {
        BigDecimal r0 = BigDecimal.valueOf(0);
        int r1 = 0;
    L4:
        if (r1 >= r3.length()) goto L6;
        r0 = r0.add(r4.invoke(Character.valueOf(r3.charAt(r1))));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(CharSequence r3, Function1<? super Character, ? extends BigInteger> r4) {
        BigInteger r0 = BigInteger.valueOf(0);
        int r1 = 0;
    L4:
        if (r1 >= r3.length()) goto L6;
        r0 = r0.add(r4.invoke(Character.valueOf(r3.charAt(r1))));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public static final SortedSet<Character> toSortedSet(CharSequence r1) {
        return (SortedSet) StringsKt___StringsKt.toCollection(r1, new TreeSet());
    }
}
