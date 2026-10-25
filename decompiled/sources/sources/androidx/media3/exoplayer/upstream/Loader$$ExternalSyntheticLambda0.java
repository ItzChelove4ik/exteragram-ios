package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.Consumer;
import java.util.concurrent.ExecutorService;

/* loaded from: classes4.dex */
public final /* synthetic */ class Loader$$ExternalSyntheticLambda0 implements Consumer {
    public /* synthetic */ Loader$$ExternalSyntheticLambda0() {
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r1) {
        ((ExecutorService) r1).shutdown();
    }
}
