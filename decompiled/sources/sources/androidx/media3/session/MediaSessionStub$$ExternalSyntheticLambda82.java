package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda82 implements Consumer {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda82(MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r3) {
        MediaSessionStub.$r8$lambda$SjZC1HY1SjS6ktIzYoyCorTzbWs(this.f$0, this.f$1, this.f$2, (ListenableFuture) r3);
    }
}
