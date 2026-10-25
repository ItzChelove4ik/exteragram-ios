package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;

/* loaded from: classes4.dex */
public final /* synthetic */ class UserDataReader$$ExternalSyntheticLambda0 implements ReorderingBufferQueue.OutputConsumer {
    public final /* synthetic */ UserDataReader f$0;

    public /* synthetic */ UserDataReader$$ExternalSyntheticLambda0(UserDataReader r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.container.ReorderingBufferQueue.OutputConsumer
    public final void consume(long r1, ParsableByteArray r3) {
        UserDataReader.$r8$lambda$zi7aTMO_v4QtNIFv_6gnkb6UCzY(this.f$0, r1, r3);
    }
}
