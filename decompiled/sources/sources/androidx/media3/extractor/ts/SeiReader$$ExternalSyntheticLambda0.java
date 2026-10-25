package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;

/* loaded from: classes4.dex */
public final /* synthetic */ class SeiReader$$ExternalSyntheticLambda0 implements ReorderingBufferQueue.OutputConsumer {
    public final /* synthetic */ SeiReader f$0;

    public /* synthetic */ SeiReader$$ExternalSyntheticLambda0(SeiReader r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.container.ReorderingBufferQueue.OutputConsumer
    public final void consume(long r1, ParsableByteArray r3) {
        SeiReader.$r8$lambda$NXFkAmBgfgkngJQGcXxdP1VlNes(this.f$0, r1, r3);
    }
}
