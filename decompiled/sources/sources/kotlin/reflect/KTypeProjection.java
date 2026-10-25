package kotlin.reflect;

import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.RandomKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\bF\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0083\u0004J\f\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0083\u0004J\"\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0081\u0004J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lkotlin/reflect/KTypeProjection;", _UrlKt.FRAGMENT_ENCODE_SET, "variance", "Lkotlin/reflect/KVariance;", "type", "Lkotlin/reflect/KType;", "<init>", "(Lkotlin/reflect/KVariance;Lkotlin/reflect/KType;)V", "getVariance", "()Lkotlin/reflect/KVariance;", "getType", "()Lkotlin/reflect/KType;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "component1", "component2", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class KTypeProjection {
    public static final Companion Companion = null;

    @JvmField
    public static final KTypeProjection star = null;
    private final KType type;
    private final KVariance variance;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0087\u0080\u0004J\u0012\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0087\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0087\u0080\u0004R\u0017\u0010\u0004\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0015\u0010\u0007\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/KTypeProjection$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "star", "Lkotlin/reflect/KTypeProjection;", "getStar$annotations", "STAR", "getSTAR", "()Lkotlin/reflect/KTypeProjection;", "invariant", "type", "Lkotlin/reflect/KType;", "contravariant", "covariant", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @PublishedApi
        public static /* synthetic */ void getStar$annotations() {
        }

        @JvmStatic
        public final KTypeProjection contravariant(KType r2) {
            return new KTypeProjection(KVariance.IN, r2);
        }

        @JvmStatic
        public final KTypeProjection covariant(KType r2) {
            return new KTypeProjection(KVariance.OUT, r2);
        }

        public final KTypeProjection getSTAR() {
            return KTypeProjection.star;
        }

        @JvmStatic
        public final KTypeProjection invariant(KType r2) {
            return new KTypeProjection(KVariance.INVARIANT, r2);
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[KVariance.values().length];
            r0[KVariance.INVARIANT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[KVariance.IN.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[KVariance.OUT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
        star = new KTypeProjection(null, null);
    }

    public KTypeProjection(KVariance r3, KType r4) {
        this.variance = r3;
        this.type = r4;
        boolean r2 = false;
        if (r3 != null) goto L5;
        boolean r1 = true;
    L6:
        if (r4 != null) goto L8;
        r2 = true;
    L8:
        if (r1 != r2) goto L10;
        return;
    L10:
        if (r3 != null) goto L12;
        String r22 = "Star projection must have no type specified.";
    L13:
        RandomKt$$ExternalSyntheticBUOutline0.m(r22);
        throw null;
    L12:
        r22 = "The projection variance " + r3 + " requires type to be specified.";
        goto L13
    L5:
        r1 = false;
        goto L6
    }

    @JvmStatic
    public static final KTypeProjection contravariant(KType r1) {
        return Companion.contravariant(r1);
    }

    public static /* synthetic */ KTypeProjection copy$default(KTypeProjection r0, KVariance r1, KType r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = r0.variance;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.type;
    L9:
        return r0.copy(r1, r2);
    }

    @JvmStatic
    public static final KTypeProjection covariant(KType r1) {
        return Companion.covariant(r1);
    }

    @JvmStatic
    public static final KTypeProjection invariant(KType r1) {
        return Companion.invariant(r1);
    }

    public final KVariance component1() {
        return this.variance;
    }

    public final KType component2() {
        return this.type;
    }

    public final KTypeProjection copy(KVariance r1, KType r2) {
        return new KTypeProjection(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof KTypeProjection) == true) goto L8;
        return false;
    L8:
        KTypeProjection r52 = (KTypeProjection) r5;
        if (this.variance == r52.variance) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.type, r52.type) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final KType getType() {
        return this.type;
    }

    public final KVariance getVariance() {
        return this.variance;
    }

    public int hashCode() {
        KVariance r0 = this.variance;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        KType r2 = this.type;
        if (r2 == null) goto L11;
        r1 = r2.hashCode();
    L11:
        return r03 + r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        KVariance r0 = this.variance;
        if (r0 != null) goto L5;
        int r02 = -1;
    L6:
        if (r02 != (-1)) goto L8;
        return "*";
    L8:
        if (r02 == 1) goto L20;
        if (r02 == 2) goto L18;
        if (r02 == 3) goto L14;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        return "out " + this.type;
    L18:
        return "in " + this.type;
    L20:
        return String.valueOf(this.type);
    L5:
        r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        goto L6
    }
}
