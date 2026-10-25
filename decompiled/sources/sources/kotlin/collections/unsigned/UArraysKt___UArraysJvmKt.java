package kotlin.collections.unsigned;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u001f\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001d\u0010\u0000\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\t\u0010\n\u001a\u001d\u0010\u0000\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\r\u0010\u000e\u001a\u001d\u0010\u0000\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014*\u00020\bH\u0087\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014*\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014*\u00020\u0010H\u0087\u0080\u0004¢\u0006\u0004\b\u001b\u0010\u001c\u001a1\u0010\u001d\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b!\u0010\"\u001a1\u0010\u001d\u001a\u00020\u0004*\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b#\u0010$\u001a1\u0010\u001d\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b%\u0010&\u001a1\u0010\u001d\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u000f2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b'\u0010(\u001a\u0017\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0004\b*\u0010+\u001a\u0017\u0010)\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0087\u0080\u0004¢\u0006\u0004\b,\u0010-\u001a\u0017\u0010)\u001a\u0004\u0018\u00010\u000b*\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\b.\u0010/\u001a\u0017\u0010)\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0087\u0080\u0004¢\u0006\u0004\b0\u00101\u001a>\u00102\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a>\u00102\u001a\u0004\u0018\u00010\u0007\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a>\u00102\u001a\u0004\u0018\u00010\u000b\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a>\u00102\u001a\u0004\u0018\u00010\u000f\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a3\u0010?\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`BH\u0087\u0080\u0004¢\u0006\u0004\bC\u0010D\u001a3\u0010?\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00070Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0007`BH\u0087\u0080\u0004¢\u0006\u0004\bE\u0010F\u001a3\u0010?\u001a\u0004\u0018\u00010\u000b*\u00020\f2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000b0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000b`BH\u0087\u0080\u0004¢\u0006\u0004\bG\u0010H\u001a3\u0010?\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000f0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000f`BH\u0087\u0080\u0004¢\u0006\u0004\bI\u0010J\u001a\u0017\u0010K\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0004\bL\u0010+\u001a\u0017\u0010K\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0087\u0080\u0004¢\u0006\u0004\bM\u0010-\u001a\u0017\u0010K\u001a\u0004\u0018\u00010\u000b*\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\bN\u0010/\u001a\u0017\u0010K\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0087\u0080\u0004¢\u0006\u0004\bO\u00101\u001a>\u0010P\u001a\u0004\u0018\u00010\u0001\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bQ\u00108\u001a>\u0010P\u001a\u0004\u0018\u00010\u0007\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bR\u0010:\u001a>\u0010P\u001a\u0004\u0018\u00010\u000b\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bS\u0010<\u001a>\u0010P\u001a\u0004\u0018\u00010\u000f\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H306H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bT\u0010>\u001a3\u0010U\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`BH\u0087\u0080\u0004¢\u0006\u0004\bV\u0010D\u001a3\u0010U\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00070Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0007`BH\u0087\u0080\u0004¢\u0006\u0004\bW\u0010F\u001a3\u0010U\u001a\u0004\u0018\u00010\u000b*\u00020\f2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000b0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000b`BH\u0087\u0080\u0004¢\u0006\u0004\bX\u0010H\u001a3\u0010U\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000f0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000f`BH\u0087\u0080\u0004¢\u0006\u0004\bY\u0010J\u001a,\u0010Z\u001a\u00020[*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020[06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b\\\u0010]\u001a,\u0010Z\u001a\u00020[*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020[06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b\\\u0010^\u001a,\u0010Z\u001a\u00020[*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020[06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b\\\u0010_\u001a,\u0010Z\u001a\u00020[*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020[06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\b\\\u0010`\u001a,\u0010Z\u001a\u00020a*\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020a06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a,\u0010Z\u001a\u00020a*\u00020\b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020a06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bb\u0010d\u001a,\u0010Z\u001a\u00020a*\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020a06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bb\u0010e\u001a,\u0010Z\u001a\u00020a*\u00020\u00102\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020a06H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0004\bb\u0010f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006g"}, d2 = {"elementAt", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "elementAt-qFRl0hI", "([II)I", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "elementAt-r7IrZao", "([JI)J", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "elementAt-PpDY95g", "([BI)B", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "elementAt-nggk6HY", "([SI)S", "asList", _UrlKt.FRAGMENT_ENCODE_SET, "asList--ajY-9A", "([I)Ljava/util/List;", "asList-QwZRm1k", "([J)Ljava/util/List;", "asList-GBYM_sE", "([B)Ljava/util/List;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "element", "fromIndex", "toIndex", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-EtDCXyQ", "([SSII)I", "max", "max--ajY-9A", "([I)Lkotlin/UInt;", "max-QwZRm1k", "([J)Lkotlin/ULong;", "max-GBYM_sE", "([B)Lkotlin/UByte;", "max-rL5Bavg", "([S)Lkotlin/UShort;", "maxBy", "R", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_SELECTOR, "Lkotlin/Function1;", "maxBy-jgv0xPQ", "([ILkotlin/jvm/functions/Function1;)Lkotlin/UInt;", "maxBy-MShoTSo", "([JLkotlin/jvm/functions/Function1;)Lkotlin/ULong;", "maxBy-JOV_ifY", "([BLkotlin/jvm/functions/Function1;)Lkotlin/UByte;", "maxBy-xTcfx_M", "([SLkotlin/jvm/functions/Function1;)Lkotlin/UShort;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "maxWith-YmdZ_VM", "([ILjava/util/Comparator;)Lkotlin/UInt;", "maxWith-zrEWJaI", "([JLjava/util/Comparator;)Lkotlin/ULong;", "maxWith-XMRcp5o", "([BLjava/util/Comparator;)Lkotlin/UByte;", "maxWith-eOHTfZs", "([SLjava/util/Comparator;)Lkotlin/UShort;", "min", "min--ajY-9A", "min-QwZRm1k", "min-GBYM_sE", "min-rL5Bavg", "minBy", "minBy-jgv0xPQ", "minBy-MShoTSo", "minBy-JOV_ifY", "minBy-xTcfx_M", "minWith", "minWith-YmdZ_VM", "minWith-zrEWJaI", "minWith-XMRcp5o", "minWith-eOHTfZs", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, pn = "kotlin.collections", xi = 49, xs = "kotlin/collections/unsigned/UArraysKt")
/* loaded from: classes5.dex */
class UArraysKt___UArraysJvmKt {
    public UArraysKt___UArraysJvmKt() {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: asList--ajY-9A, reason: not valid java name */
    public static final List<UInt> m2490asListajY9A(int[] r1) {
        return new UArraysKt___UArraysJvmKt$asList$1(r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: asList-GBYM_sE, reason: not valid java name */
    public static final List<UByte> m2491asListGBYM_sE(byte[] r1) {
        return new UArraysKt___UArraysJvmKt$asList$3(r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: asList-QwZRm1k, reason: not valid java name */
    public static final List<ULong> m2492asListQwZRm1k(long[] r1) {
        return new UArraysKt___UArraysJvmKt$asList$2(r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: asList-rL5Bavg, reason: not valid java name */
    public static final List<UShort> m2493asListrL5Bavg(short[] r1) {
        return new UArraysKt___UArraysJvmKt$asList$4(r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-2fe2U9s, reason: not valid java name */
    public static final int m2494binarySearch2fe2U9s(int[] r2, int r3, int r4, int r5) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r4, r5, UIntArray.m2174getSizeimpl(r2));
        int r52 = r5 - 1;
    L3:
        if (r4 > r52) goto L11;
        int r0 = (r4 + r52) >>> 1;
        int r1 = UnsignedKt.uintCompare(r2[r0], r3);
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r52 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r4 = r0 + 1;
        goto L3
    L11:
        return -(r4 + 1);
    }

    /* renamed from: binarySearch-2fe2U9s$default, reason: not valid java name */
    public static /* synthetic */ int m2495binarySearch2fe2U9s$default(int[] r0, int r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = UIntArray.m2174getSizeimpl(r0);
    L9:
        return m2494binarySearch2fe2U9s(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-EtDCXyQ, reason: not valid java name */
    public static final int m2496binarySearchEtDCXyQ(short[] r2, short r3, int r4, int r5) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r4, r5, UShortArray.m2358getSizeimpl(r2));
        int r32 = r3 & UShort.MAX_VALUE;
        int r52 = r5 - 1;
    L3:
        if (r4 > r52) goto L11;
        int r0 = (r4 + r52) >>> 1;
        int r1 = UnsignedKt.uintCompare(r2[r0], r32);
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r52 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r4 = r0 + 1;
        goto L3
    L11:
        return -(r4 + 1);
    }

    /* renamed from: binarySearch-EtDCXyQ$default, reason: not valid java name */
    public static /* synthetic */ int m2497binarySearchEtDCXyQ$default(short[] r0, short r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = UShortArray.m2358getSizeimpl(r0);
    L9:
        return m2496binarySearchEtDCXyQ(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-K6DWlUc, reason: not valid java name */
    public static final int m2498binarySearchK6DWlUc(long[] r3, long r4, int r6, int r7) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r6, r7, ULongArray.m2253getSizeimpl(r3));
        int r72 = r7 - 1;
    L3:
        if (r6 > r72) goto L11;
        int r0 = (r6 + r72) >>> 1;
        int r1 = UnsignedKt.ulongCompare(r3[r0], r4);
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r72 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r6 = r0 + 1;
        goto L3
    L11:
        return -(r6 + 1);
    }

    /* renamed from: binarySearch-K6DWlUc$default, reason: not valid java name */
    public static /* synthetic */ int m2499binarySearchK6DWlUc$default(long[] r0, long r1, int r3, int r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = ULongArray.m2253getSizeimpl(r0);
    L9:
        return m2498binarySearchK6DWlUc(r0, r1, r3, r4);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-WpHrYlw, reason: not valid java name */
    public static final int m2500binarySearchWpHrYlw(byte[] r2, byte r3, int r4, int r5) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r4, r5, UByteArray.m2095getSizeimpl(r2));
        int r32 = r3 & UByte.MAX_VALUE;
        int r52 = r5 - 1;
    L3:
        if (r4 > r52) goto L11;
        int r0 = (r4 + r52) >>> 1;
        int r1 = UnsignedKt.uintCompare(r2[r0], r32);
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r52 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r4 = r0 + 1;
        goto L3
    L11:
        return -(r4 + 1);
    }

    /* renamed from: binarySearch-WpHrYlw$default, reason: not valid java name */
    public static /* synthetic */ int m2501binarySearchWpHrYlw$default(byte[] r0, byte r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = UByteArray.m2095getSizeimpl(r0);
    L9:
        return m2500binarySearchWpHrYlw(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-PpDY95g, reason: not valid java name */
    private static final byte m2502elementAtPpDY95g(byte[] r0, int r1) {
        return UByteArray.m2094getw2LRezQ(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-nggk6HY, reason: not valid java name */
    private static final short m2503elementAtnggk6HY(short[] r0, int r1) {
        return UShortArray.m2357getMh2AYeg(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-qFRl0hI, reason: not valid java name */
    private static final int m2504elementAtqFRl0hI(int[] r0, int r1) {
        return UIntArray.m2173getpVg5ArA(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-r7IrZao, reason: not valid java name */
    private static final long m2505elementAtr7IrZao(long[] r0, int r1) {
        return ULongArray.m2252getsVKNKU(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max--ajY-9A, reason: not valid java name */
    public static final /* synthetic */ UInt m2506maxajY9A(int[] r0) {
        return UArraysKt___UArraysKt.m2881maxOrNullajY9A(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max-GBYM_sE, reason: not valid java name */
    public static final /* synthetic */ UByte m2507maxGBYM_sE(byte[] r0) {
        return UArraysKt___UArraysKt.m2882maxOrNullGBYM_sE(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max-QwZRm1k, reason: not valid java name */
    public static final /* synthetic */ ULong m2508maxQwZRm1k(long[] r0) {
        return UArraysKt___UArraysKt.m2883maxOrNullQwZRm1k(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max-rL5Bavg, reason: not valid java name */
    public static final /* synthetic */ UShort m2509maxrL5Bavg(short[] r0) {
        return UArraysKt___UArraysKt.m2884maxOrNullrL5Bavg(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-JOV_ifY, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UByte m2510maxByJOV_ifY(byte[] r7, Function1<? super UByte, ? extends R> r8) {
        if (UByteArray.m2097isEmptyimpl(r7) == false) goto L6;
        return null;
    L6:
        byte r0 = UByteArray.m2094getw2LRezQ(r7, 0);
        int r1 = ArraysKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(UByte.m2030boximpl(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        byte r4 = UByteArray.m2094getw2LRezQ(r7, r3);
        R r5 = r8.invoke(UByte.m2030boximpl(r4));
        if (r2.compareTo(r5) >= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return UByte.m2030boximpl(r0);
    L9:
        return UByte.m2030boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-MShoTSo, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> ULong m2511maxByMShoTSo(long[] r9, Function1<? super ULong, ? extends R> r10) {
        if (ULongArray.m2255isEmptyimpl(r9) == false) goto L6;
        return null;
    L6:
        long r0 = ULongArray.m2252getsVKNKU(r9, 0);
        int r2 = ArraysKt.getLastIndex(r9);
        if (r2 == 0) goto L9;
        R r3 = r10.invoke(ULong.m2186boximpl(r0));
        int r4 = 1;
        if (1 > r2) goto L18;
    L12:
        long r5 = ULongArray.m2252getsVKNKU(r9, r4);
        R r7 = r10.invoke(ULong.m2186boximpl(r5));
        if (r3.compareTo(r7) >= 0) goto L15;
        r0 = r5;
        r3 = r7;
    L15:
        if (r4 == r2) goto L18;
        r4 = r4 + 1;
    L18:
        return ULong.m2186boximpl(r0);
    L9:
        return ULong.m2186boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-jgv0xPQ, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UInt m2512maxByjgv0xPQ(int[] r7, Function1<? super UInt, ? extends R> r8) {
        if (UIntArray.m2176isEmptyimpl(r7) == false) goto L6;
        return null;
    L6:
        int r0 = UIntArray.m2173getpVg5ArA(r7, 0);
        int r1 = ArraysKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(UInt.m2107boximpl(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        int r4 = UIntArray.m2173getpVg5ArA(r7, r3);
        R r5 = r8.invoke(UInt.m2107boximpl(r4));
        if (r2.compareTo(r5) >= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return UInt.m2107boximpl(r0);
    L9:
        return UInt.m2107boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-xTcfx_M, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UShort m2513maxByxTcfx_M(short[] r7, Function1<? super UShort, ? extends R> r8) {
        if (UShortArray.m2360isEmptyimpl(r7) == false) goto L6;
        return null;
    L6:
        short r0 = UShortArray.m2357getMh2AYeg(r7, 0);
        int r1 = ArraysKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(UShort.m2293boximpl(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        short r4 = UShortArray.m2357getMh2AYeg(r7, r3);
        R r5 = r8.invoke(UShort.m2293boximpl(r4));
        if (r2.compareTo(r5) >= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return UShort.m2293boximpl(r0);
    L9:
        return UShort.m2293boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-XMRcp5o, reason: not valid java name */
    public static final /* synthetic */ UByte m2514maxWithXMRcp5o(byte[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2889maxWithOrNullXMRcp5o(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-YmdZ_VM, reason: not valid java name */
    public static final /* synthetic */ UInt m2515maxWithYmdZ_VM(int[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2890maxWithOrNullYmdZ_VM(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-eOHTfZs, reason: not valid java name */
    public static final /* synthetic */ UShort m2516maxWitheOHTfZs(short[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2891maxWithOrNulleOHTfZs(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-zrEWJaI, reason: not valid java name */
    public static final /* synthetic */ ULong m2517maxWithzrEWJaI(long[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2892maxWithOrNullzrEWJaI(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min--ajY-9A, reason: not valid java name */
    public static final /* synthetic */ UInt m2518minajY9A(int[] r0) {
        return UArraysKt___UArraysKt.m2937minOrNullajY9A(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min-GBYM_sE, reason: not valid java name */
    public static final /* synthetic */ UByte m2519minGBYM_sE(byte[] r0) {
        return UArraysKt___UArraysKt.m2938minOrNullGBYM_sE(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min-QwZRm1k, reason: not valid java name */
    public static final /* synthetic */ ULong m2520minQwZRm1k(long[] r0) {
        return UArraysKt___UArraysKt.m2939minOrNullQwZRm1k(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min-rL5Bavg, reason: not valid java name */
    public static final /* synthetic */ UShort m2521minrL5Bavg(short[] r0) {
        return UArraysKt___UArraysKt.m2940minOrNullrL5Bavg(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-JOV_ifY, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UByte m2522minByJOV_ifY(byte[] r7, Function1<? super UByte, ? extends R> r8) {
        if (UByteArray.m2097isEmptyimpl(r7) == false) goto L6;
        return null;
    L6:
        byte r0 = UByteArray.m2094getw2LRezQ(r7, 0);
        int r1 = ArraysKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(UByte.m2030boximpl(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        byte r4 = UByteArray.m2094getw2LRezQ(r7, r3);
        R r5 = r8.invoke(UByte.m2030boximpl(r4));
        if (r2.compareTo(r5) <= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return UByte.m2030boximpl(r0);
    L9:
        return UByte.m2030boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-MShoTSo, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> ULong m2523minByMShoTSo(long[] r9, Function1<? super ULong, ? extends R> r10) {
        if (ULongArray.m2255isEmptyimpl(r9) == false) goto L6;
        return null;
    L6:
        long r0 = ULongArray.m2252getsVKNKU(r9, 0);
        int r2 = ArraysKt.getLastIndex(r9);
        if (r2 == 0) goto L9;
        R r3 = r10.invoke(ULong.m2186boximpl(r0));
        int r4 = 1;
        if (1 > r2) goto L18;
    L12:
        long r5 = ULongArray.m2252getsVKNKU(r9, r4);
        R r7 = r10.invoke(ULong.m2186boximpl(r5));
        if (r3.compareTo(r7) <= 0) goto L15;
        r0 = r5;
        r3 = r7;
    L15:
        if (r4 == r2) goto L18;
        r4 = r4 + 1;
    L18:
        return ULong.m2186boximpl(r0);
    L9:
        return ULong.m2186boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-jgv0xPQ, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UInt m2524minByjgv0xPQ(int[] r7, Function1<? super UInt, ? extends R> r8) {
        if (UIntArray.m2176isEmptyimpl(r7) == false) goto L6;
        return null;
    L6:
        int r0 = UIntArray.m2173getpVg5ArA(r7, 0);
        int r1 = ArraysKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(UInt.m2107boximpl(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        int r4 = UIntArray.m2173getpVg5ArA(r7, r3);
        R r5 = r8.invoke(UInt.m2107boximpl(r4));
        if (r2.compareTo(r5) <= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return UInt.m2107boximpl(r0);
    L9:
        return UInt.m2107boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-xTcfx_M, reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UShort m2525minByxTcfx_M(short[] r7, Function1<? super UShort, ? extends R> r8) {
        if (UShortArray.m2360isEmptyimpl(r7) == false) goto L6;
        return null;
    L6:
        short r0 = UShortArray.m2357getMh2AYeg(r7, 0);
        int r1 = ArraysKt.getLastIndex(r7);
        if (r1 == 0) goto L9;
        R r2 = r8.invoke(UShort.m2293boximpl(r0));
        int r3 = 1;
        if (1 > r1) goto L18;
    L12:
        short r4 = UShortArray.m2357getMh2AYeg(r7, r3);
        R r5 = r8.invoke(UShort.m2293boximpl(r4));
        if (r2.compareTo(r5) <= 0) goto L15;
        r0 = r4;
        r2 = r5;
    L15:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
    L18:
        return UShort.m2293boximpl(r0);
    L9:
        return UShort.m2293boximpl(r0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-XMRcp5o, reason: not valid java name */
    public static final /* synthetic */ UByte m2526minWithXMRcp5o(byte[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2945minWithOrNullXMRcp5o(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-YmdZ_VM, reason: not valid java name */
    public static final /* synthetic */ UInt m2527minWithYmdZ_VM(int[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2946minWithOrNullYmdZ_VM(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-eOHTfZs, reason: not valid java name */
    public static final /* synthetic */ UShort m2528minWitheOHTfZs(short[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2947minWithOrNulleOHTfZs(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-zrEWJaI, reason: not valid java name */
    public static final /* synthetic */ ULong m2529minWithzrEWJaI(long[] r0, Comparator r1) {
        return UArraysKt___UArraysKt.m2948minWithOrNullzrEWJaI(r0, r1);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(byte[] r4, Function1<? super UByte, ? extends BigDecimal> r5) {
        BigDecimal r0 = BigDecimal.valueOf(0);
        int r1 = UByteArray.m2095getSizeimpl(r4);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r5.invoke(UByte.m2030boximpl(UByteArray.m2094getw2LRezQ(r4, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(int[] r4, Function1<? super UInt, ? extends BigDecimal> r5) {
        BigDecimal r0 = BigDecimal.valueOf(0);
        int r1 = UIntArray.m2174getSizeimpl(r4);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r5.invoke(UInt.m2107boximpl(UIntArray.m2173getpVg5ArA(r4, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(long[] r5, Function1<? super ULong, ? extends BigDecimal> r6) {
        BigDecimal r0 = BigDecimal.valueOf(0);
        int r1 = ULongArray.m2253getSizeimpl(r5);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r6.invoke(ULong.m2186boximpl(ULongArray.m2252getsVKNKU(r5, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(short[] r4, Function1<? super UShort, ? extends BigDecimal> r5) {
        BigDecimal r0 = BigDecimal.valueOf(0);
        int r1 = UShortArray.m2358getSizeimpl(r4);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r5.invoke(UShort.m2293boximpl(UShortArray.m2357getMh2AYeg(r4, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(byte[] r4, Function1<? super UByte, ? extends BigInteger> r5) {
        BigInteger r0 = BigInteger.valueOf(0);
        int r1 = UByteArray.m2095getSizeimpl(r4);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r5.invoke(UByte.m2030boximpl(UByteArray.m2094getw2LRezQ(r4, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(int[] r4, Function1<? super UInt, ? extends BigInteger> r5) {
        BigInteger r0 = BigInteger.valueOf(0);
        int r1 = UIntArray.m2174getSizeimpl(r4);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r5.invoke(UInt.m2107boximpl(UIntArray.m2173getpVg5ArA(r4, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(long[] r5, Function1<? super ULong, ? extends BigInteger> r6) {
        BigInteger r0 = BigInteger.valueOf(0);
        int r1 = ULongArray.m2253getSizeimpl(r5);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r6.invoke(ULong.m2186boximpl(ULongArray.m2252getsVKNKU(r5, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(short[] r4, Function1<? super UShort, ? extends BigInteger> r5) {
        BigInteger r0 = BigInteger.valueOf(0);
        int r1 = UShortArray.m2358getSizeimpl(r4);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0 = r0.add(r5.invoke(UShort.m2293boximpl(UShortArray.m2357getMh2AYeg(r4, r2))));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }
}
