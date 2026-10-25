package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.9")
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0006\u001a\u00020\u0005H\u0094\u0080\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086\u0082\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0082\u0080\u0004¢\u0006\u0004\b\u000e\u0010\fR\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "<init>", "()V", "reading", _UrlKt.FRAGMENT_ENCODE_SET, "read", "plusAssign", _UrlKt.FRAGMENT_ENCODE_SET, "duration", "Lkotlin/time/Duration;", "plusAssign-LRDsOJo", "(J)V", "overflow", "overflow-LRDsOJo", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalTime.class})
@SourceDebugExtension({"SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,210:1\n80#2:211\n80#2:212\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n*L\n184#1:211\n191#1:212\n*E\n"})
/* loaded from: classes5.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
        markNow();
    }

    /* renamed from: overflow-LRDsOJo, reason: not valid java name */
    private final void m3489overflowLRDsOJo(long r5) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt__DurationUnitKt.shortName(getUnit()) + " is advanced by " + Duration.m3415toStringimpl(r5) + '.');
    }

    /* renamed from: plusAssign-LRDsOJo, reason: not valid java name */
    public final void m3490plusAssignLRDsOJo(long r9) {
        long r0 = Duration.m3414toLongimpl(r9, getUnit());
        if (((r0 - 1) | 1) != LongCompanionObject.MAX_VALUE) goto L14;
        long r02 = Duration.m3378divUwyO8pc(r9, 2);
        if ((1 | (Duration.m3414toLongimpl(r02, getUnit()) - 1)) != LongCompanionObject.MAX_VALUE) goto L8;
        m3489overflowLRDsOJo(r9);
        return;
    L8:
        long r2 = this.reading;
        m3490plusAssignLRDsOJo(r02);     // Catch: IllegalStateException -> L11
        m3490plusAssignLRDsOJo(Duration.m3403minusLRDsOJo(r9, r02));     // Catch: IllegalStateException -> L11
        return;
    L11:
        e = move-exception;
        this.reading = r2;
        throw e;
    L14:
        long r22 = this.reading;
        long r4 = r22 + r0;
        if ((r0 ^ r22) >= 0) goto L17;
    L19:
        this.reading = r4;
        return;
    L17:
        if ((r22 ^ r4) >= 0) goto L19;
        m3489overflowLRDsOJo(r9);
        goto L19
    }

    @Override // kotlin.time.AbstractLongTimeSource
    public long read() {
        return this.reading;
    }
}
