package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0004\u001aK\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0007*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00070\u00060\u0003H\u0086\u0080\u0004¢\u0006\u0002\u0010\b\u001a*\u0010\t\u001a\u00020\n*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\u0088\u0004\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u000b\u001aI\u0010\f\u001a\u0002H\u0007\"\u0010\b\u0000\u0010\r*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0007\"\u0004\b\u0001\u0010\u0007*\u0002H\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000fH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0010\u001a7\u0010\u0011\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u0012\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0081\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014\u001a%\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0081\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0018\u001aA\u0010\u0019\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001d2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u001fH\u0082\u0080\u0004¢\u0006\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"flatten", _UrlKt.FRAGMENT_ENCODE_SET, "T", _UrlKt.FRAGMENT_ENCODE_SET, "([[Ljava/lang/Object;)Ljava/util/List;", "unzip", "Lkotlin/Pair;", "R", "([Lkotlin/Pair;)Lkotlin/Pair;", "isNullOrEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Z", "ifEmpty", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "contentDeepEqualsImpl", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", _UrlKt.FRAGMENT_ENCODE_SET, "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", _UrlKt.FRAGMENT_ENCODE_SET, "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", _UrlKt.FRAGMENT_ENCODE_SET, "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/ArraysKt")
@SourceDebugExtension({"SMAP\nArrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Arrays.kt\nkotlin/collections/ArraysKt__ArraysKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,175:1\n1#2:176\n*E\n"})
/* loaded from: classes.dex */
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    public ArraysKt__ArraysKt() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "contentDeepEquals")
    public static <T> boolean contentDeepEquals(T[] r7, T[] r8) {
        if (r7 != r8) goto L6;
        return true;
    L6:
        if (r7 == 0) goto L116;
        if (r8 == 0) goto L116;
        if (r7.length != r8.length) goto L116;
        int r2 = r7.length;
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L115;
        Object[] r4 = r7[r3];
        Object[] r5 = r8[r3];
        if (r4 == r5) goto L113;
        if (r4 == 0) goto L114;
        if (r5 == 0) goto L114;
        if ((r4 instanceof Object[]) == false) goto L27;
        if ((r5 instanceof Object[]) == false) goto L27;
        if (contentDeepEquals(r4, r5) == true) goto L113;
        return false;
    L27:
        if ((r4 instanceof byte[]) == false) goto L34;
        if ((r5 instanceof byte[]) == false) goto L34;
        if (Arrays.equals((byte[]) r4, (byte[]) r5) == true) goto L113;
        return false;
    L34:
        if ((r4 instanceof short[]) == false) goto L41;
        if ((r5 instanceof short[]) == false) goto L41;
        if (Arrays.equals((short[]) r4, (short[]) r5) == true) goto L113;
        return false;
    L41:
        if ((r4 instanceof int[]) == false) goto L48;
        if ((r5 instanceof int[]) == false) goto L48;
        if (Arrays.equals((int[]) r4, (int[]) r5) == true) goto L113;
        return false;
    L48:
        if ((r4 instanceof long[]) == false) goto L55;
        if ((r5 instanceof long[]) == false) goto L55;
        if (Arrays.equals((long[]) r4, (long[]) r5) == true) goto L113;
        return false;
    L55:
        if ((r4 instanceof float[]) == false) goto L62;
        if ((r5 instanceof float[]) == false) goto L62;
        if (Arrays.equals((float[]) r4, (float[]) r5) == true) goto L113;
        return false;
    L62:
        if ((r4 instanceof double[]) == false) goto L69;
        if ((r5 instanceof double[]) == false) goto L69;
        if (Arrays.equals((double[]) r4, (double[]) r5) == true) goto L113;
        return false;
    L69:
        if ((r4 instanceof char[]) == false) goto L76;
        if ((r5 instanceof char[]) == false) goto L76;
        if (Arrays.equals((char[]) r4, (char[]) r5) == true) goto L113;
        return false;
    L76:
        if ((r4 instanceof boolean[]) == false) goto L83;
        if ((r5 instanceof boolean[]) == false) goto L83;
        if (Arrays.equals((boolean[]) r4, (boolean[]) r5) == true) goto L113;
        return false;
    L83:
        if ((r4 instanceof UByteArray) == false) goto L90;
        if ((r5 instanceof UByteArray) == false) goto L90;
        if (UArraysKt.m2595contentEqualskV0jMPg(((UByteArray) r4).m2103unboximpl(), ((UByteArray) r5).m2103unboximpl()) == true) goto L113;
        return false;
    L90:
        if ((r4 instanceof UShortArray) == false) goto L97;
        if ((r5 instanceof UShortArray) == false) goto L97;
        if (UArraysKt.m2593contentEqualsFGO6Aew(((UShortArray) r4).m2366unboximpl(), ((UShortArray) r5).m2366unboximpl()) == true) goto L113;
        return false;
    L97:
        if ((r4 instanceof UIntArray) == false) goto L104;
        if ((r5 instanceof UIntArray) == false) goto L104;
        if (UArraysKt.m2594contentEqualsKJPZfPQ(((UIntArray) r4).m2182unboximpl(), ((UIntArray) r5).m2182unboximpl()) == true) goto L113;
        return false;
    L104:
        if ((r4 instanceof ULongArray) == false) goto L111;
        if ((r5 instanceof ULongArray) == false) goto L111;
        if (UArraysKt.m2596contentEqualslec5QzE(((ULongArray) r4).m2261unboximpl(), ((ULongArray) r5).m2261unboximpl()) == true) goto L113;
        return false;
    L111:
        if (Intrinsics.areEqual(r4, r5) == true) goto L113;
        return false;
    L114:
        return false;
    L113:
        r3 = r3 + 1;
        goto L12
    L115:
        return true;
    L116:
        return false;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "contentDeepToString")
    public static final <T> String contentDeepToString(T[] r2) {
        if (r2 != null) goto L5;
        return "null";
    L5:
        StringBuilder r1 = new StringBuilder((RangesKt.coerceAtMost(r2.length, 429496729) * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(r2, r1, new ArrayList());
        return r1.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(T[] r4, StringBuilder r5, List<Object[]> r6) {
        if (r6.contains(r4) == false) goto L6;
        r5.append("[...]");
        return;
    L6:
        r6.add(r4);
        r5.append('[');
        int r0 = r4.length;
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L54;
        if (r1 == 0) goto L10;
        r5.append(", ");
    L10:
        Object[] r2 = r4[r1];
        if (r2 != 0) goto L14;
        r5.append("null");
    L53:
        r1 = r1 + 1;
        goto L7
    L14:
        if ((r2 instanceof Object[]) == false) goto L17;
        contentDeepToStringInternal$ArraysKt__ArraysKt(r2, r5, r6);
        Unit r22 = Unit.INSTANCE;
        goto L53
    L17:
        if ((r2 instanceof byte[]) == false) goto L20;
        r5.append(Arrays.toString((byte[]) r2));
        goto L53
    L20:
        if ((r2 instanceof short[]) == false) goto L23;
        r5.append(Arrays.toString((short[]) r2));
        goto L53
    L23:
        if ((r2 instanceof int[]) == false) goto L26;
        r5.append(Arrays.toString((int[]) r2));
        goto L53
    L26:
        if ((r2 instanceof long[]) == false) goto L29;
        r5.append(Arrays.toString((long[]) r2));
        goto L53
    L29:
        if ((r2 instanceof float[]) == false) goto L32;
        r5.append(Arrays.toString((float[]) r2));
        goto L53
    L32:
        if ((r2 instanceof double[]) == false) goto L35;
        r5.append(Arrays.toString((double[]) r2));
        goto L53
    L35:
        if ((r2 instanceof char[]) == false) goto L38;
        r5.append(Arrays.toString((char[]) r2));
        goto L53
    L38:
        if ((r2 instanceof boolean[]) == false) goto L41;
        r5.append(Arrays.toString((boolean[]) r2));
        goto L53
    L41:
        if ((r2 instanceof UByteArray) == false) goto L44;
        r5.append(UArraysKt.m2601contentToString2csIQuQ(((UByteArray) r2).m2103unboximpl()));
        goto L53
    L44:
        if ((r2 instanceof UShortArray) == false) goto L47;
        r5.append(UArraysKt.m2603contentToStringd6D3K8(((UShortArray) r2).m2366unboximpl()));
        goto L53
    L47:
        if ((r2 instanceof UIntArray) == false) goto L50;
        r5.append(UArraysKt.m2602contentToStringXUkPCBk(((UIntArray) r2).m2182unboximpl()));
        goto L53
    L50:
        if ((r2 instanceof ULongArray) == false) goto L52;
        r5.append(UArraysKt.m2604contentToStringuLth9ew(((ULongArray) r2).m2261unboximpl()));
        goto L53
    L52:
        r5.append(r2.toString());
        goto L53
    L54:
        r5.append(']');
        r6.remove(CollectionsKt__CollectionsKt.getLastIndex(r6));
    }

    public static final <T> List<T> flatten(T[][] r11) {
        int r3 = 0;
        long r5 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r11.length) goto L6;
        r5 = r5 + r11[r4].length;
        r4 = r4 + 1;
        goto L3
    L6:
        if (r5 != 0) goto L10;
        return CollectionsKt__CollectionsKt.emptyList();
    L10:
        if (r5 > 2147483647L) goto L16;
        Object[] r52 = new Object[(int) r5];
        int r0 = r11.length;
        int r6 = 0;
    L12:
        if (r3 >= r0) goto L15;
        T[] r42 = r11[r3];
        ArraysKt___ArraysJvmKt.copyInto$default(r42, r52, r6, 0, 0, 12, null);
        r6 = r6 + r42.length;
        r3 = r3 + 1;
        goto L12
    L15:
        return ArraysKt___ArraysJvmKt.asList(r52);
    L16:
        ArraysKt__ArraysKt$$ExternalSyntheticBUOutline0.m("Sum of all arrays lengths (", r5, ") exceeds maximum list size (2147483647)");
        return null;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Object[] r1, Function0 r2) {
        if (r1.length == 0) goto L5;
        return r1;
    L5:
        return r2.invoke();
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean isNullOrEmpty(Object[] r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.length == 0) goto L10;
        return false;
    L10:
        return true;
    }

    public static final <T, R> Pair<List<T>, List<R>> unzip(Pair<? extends T, ? extends R>[] r6) {
        ArrayList r0 = new ArrayList(r6.length);
        ArrayList r1 = new ArrayList(r6.length);
        int r2 = r6.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L6;
        Pair<? extends T, ? extends R> r4 = r6[r3];
        r0.add(r4.getFirst());
        r1.add(r4.getSecond());
        r3 = r3 + 1;
        goto L3
    L6:
        return TuplesKt.to(r0, r1);
    }
}
