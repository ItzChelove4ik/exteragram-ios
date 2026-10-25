package androidx.mediarouter.media;

import androidx.mediarouter.media.MediaRouter;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaRouter.PrepareTransferNotifier f$0;

    public /* synthetic */ MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda0(MediaRouter.PrepareTransferNotifier r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.finishTransfer();
    }
}
