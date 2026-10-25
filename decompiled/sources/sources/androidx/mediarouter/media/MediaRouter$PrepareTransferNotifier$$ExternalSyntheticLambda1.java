package androidx.mediarouter.media;

import androidx.mediarouter.media.GlobalMediaRouter;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda1 implements Executor {
    public final /* synthetic */ GlobalMediaRouter.CallbackHandler f$0;

    public /* synthetic */ MediaRouter$PrepareTransferNotifier$$ExternalSyntheticLambda1(GlobalMediaRouter.CallbackHandler r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.f$0.post(r1);
    }
}
