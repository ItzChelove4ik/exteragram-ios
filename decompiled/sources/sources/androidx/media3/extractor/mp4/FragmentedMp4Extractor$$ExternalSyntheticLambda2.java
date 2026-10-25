package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;

/* loaded from: classes4.dex */
public final /* synthetic */ class FragmentedMp4Extractor$$ExternalSyntheticLambda2 implements ReorderingBufferQueue.OutputConsumer {
    public final /* synthetic */ FragmentedMp4Extractor f$0;

    public /* synthetic */ FragmentedMp4Extractor$$ExternalSyntheticLambda2(FragmentedMp4Extractor r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.container.ReorderingBufferQueue.OutputConsumer
    public final void consume(long r1, ParsableByteArray r3) {
        FragmentedMp4Extractor.$r8$lambda$tBYVtDHCMH8xrQ1aRWBKD8kAvDc(this.f$0, r1, r3);
    }
}
