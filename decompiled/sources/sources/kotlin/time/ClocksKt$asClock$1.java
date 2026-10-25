package kotlin.time;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"kotlin/time/ClocksKt$asClock$1", "Lkotlin/time/Clock;", "startMark", "Lkotlin/time/TimeMark;", "now", "Lkotlin/time/Instant;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ClocksKt$asClock$1 implements Clock {
    final /* synthetic */ Instant $origin;
    private final TimeMark startMark;

    public ClocksKt$asClock$1(TimeSource r1, Instant r2) {
        this.$origin = r2;
        this.startMark = r1.markNow();
    }

    @Override // kotlin.time.Clock
    public Instant now() {
        return this.$origin.m3480plusLRDsOJo(this.startMark.mo3363elapsedNowUwyO8pc());
    }
}
