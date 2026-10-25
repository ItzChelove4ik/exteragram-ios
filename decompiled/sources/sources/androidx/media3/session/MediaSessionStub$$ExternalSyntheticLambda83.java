package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda83 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ Consumer f$2;
    public final /* synthetic */ ListenableFuture f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda83(MediaSessionImpl r1, SettableFuture r2, Consumer r3, ListenableFuture r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionStub.$r8$lambda$zNvv3J7cSmshUkEZlCtyHBdu4zw(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
