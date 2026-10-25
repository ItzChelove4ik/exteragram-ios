package kotlin;

import java.io.Serializable;
import kotlin.js.JsImplicitExport;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@JsImplicitExport(couldBeConvertedToExplicitExport = true)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0087\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0006\b\u0002\u0010\u0003 \u00012\u00060\u0004j\u0002`\u0005B!\bF\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00028\u0001\u0012\u0006\u0010\b\u001a\u00028\u0002¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u000f\u0010\u0012\u001a\u00028\u0000HÆ\u0083\u0004¢\u0006\u0002\u0010\fJ\u000f\u0010\u0013\u001a\u00028\u0001HÆ\u0083\u0004¢\u0006\u0002\u0010\fJ\u000f\u0010\u0014\u001a\u00028\u0002HÆ\u0083\u0004¢\u0006\u0002\u0010\fJ?\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u00002\b\b\u0002\u0010\u0007\u001a\u00028\u00012\b\b\u0002\u0010\b\u001a\u00028\u0002HÆ\u0081\u0004¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0017\u0010\u0006\u001a\u00028\u0000X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0007\u001a\u00028\u0001X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\b\u001a\u00028\u0002X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\f¨\u0006\u001d"}, d2 = {"Lkotlin/Triple;", "A", "B", "C", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "first", "second", "third", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getFirst", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSecond", "getThird", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Triple;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Triple<A, B, C> implements Serializable {
    private final A first;
    private final B second;
    private final C third;

    public Triple(A r1, B r2, C r3) {
        this.first = r1;
        this.second = r2;
        this.third = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Triple copy$default(Triple r0, Object r1, Object r2, Object r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = r0.first;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.second;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = r0.third;
    L12:
        return r0.copy(r1, r2, r3);
    }

    public final A component1() {
        return this.first;
    }

    public final B component2() {
        return this.second;
    }

    public final C component3() {
        return this.third;
    }

    public final Triple<A, B, C> copy(A r1, B r2, C r3) {
        return new Triple(r1, r2, r3);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Triple) == true) goto L8;
        return false;
    L8:
        Triple r52 = (Triple) r5;
        if (Intrinsics.areEqual(this.first, r52.first) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.second, r52.second) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.third, r52.third) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public final A getFirst() {
        return this.first;
    }

    public final B getSecond() {
        return this.second;
    }

    public final C getThird() {
        return this.third;
    }

    public int hashCode() {
        A r0 = this.first;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        B r2 = this.second;
        if (r2 != null) goto L9;
        int r22 = 0;
    L10:
        int r04 = (r03 + r22) * 31;
        C r3 = this.third;
        if (r3 == null) goto L15;
        r1 = r3.hashCode();
    L15:
        return r04 + r1;
    L9:
        r22 = r2.hashCode();
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ')';
    }
}
