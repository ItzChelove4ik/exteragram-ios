package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.9")
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\bF\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000e\u001a\u00028\u0000HÆ\u0083\u0004¢\u0006\u0002\u0010\tJ\u0011\u0010\u000f\u001a\u00020\u0005HÆ\u0083\u0004¢\u0006\u0004\b\u0010\u0010\fJ+\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0081\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0017\u0010\u0003\u001a\u00028\u0000X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lkotlin/time/TimedValue;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "value", "duration", "Lkotlin/time/Duration;", "<init>", "(Ljava/lang/Object;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getDuration-UwyO8pc", "()J", "J", "component1", "component2", "component2-UwyO8pc", "copy", "copy-RFiDyg4", "(Ljava/lang/Object;J)Lkotlin/time/TimedValue;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: classes5.dex */
public final class TimedValue<T> {
    private final long duration;
    private final T value;

    private TimedValue(T r1, long r2) {
        this.value = r1;
        this.duration = r2;
    }

    public /* synthetic */ TimedValue(Object r1, long r2, DefaultConstructorMarker r4) {
        this(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: copy-RFiDyg4$default, reason: not valid java name */
    public static /* synthetic */ TimedValue m3512copyRFiDyg4$default(TimedValue r0, Object r1, long r2, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = r0.value;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.duration;
    L9:
        return r0.m3514copyRFiDyg4(r1, r2);
    }

    public final T component1() {
        return this.value;
    }

    /* renamed from: component2-UwyO8pc, reason: not valid java name */
    public final long m3513component2UwyO8pc() {
        return this.duration;
    }

    /* renamed from: copy-RFiDyg4, reason: not valid java name */
    public final TimedValue<T> m3514copyRFiDyg4(T r2, long r3) {
        return new TimedValue(r2, r3, null);
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof TimedValue) == true) goto L8;
        return false;
    L8:
        TimedValue r62 = (TimedValue) r6;
        if (Intrinsics.areEqual(this.value, r62.value) == true) goto L12;
        return false;
    L12:
        if (Duration.m3380equalsimpl0(this.duration, r62.duration) == true) goto L14;
        return false;
    L14:
        return true;
    }

    /* renamed from: getDuration-UwyO8pc, reason: not valid java name */
    public final long m3515getDurationUwyO8pc() {
        return this.duration;
    }

    public final T getValue() {
        return this.value;
    }

    public int hashCode() {
        T r0 = this.value;
        if (r0 != null) goto L5;
        int r02 = 0;
    L7:
        return (r02 * 31) + Duration.m3396hashCodeimpl(this.duration);
    L5:
        r02 = r0.hashCode();
        goto L7
    }

    public String toString() {
        return "TimedValue(value=" + this.value + ", duration=" + Duration.m3415toStringimpl(this.duration) + ')';
    }
}
