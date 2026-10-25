package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda76 implements Consumer {
    public final /* synthetic */ MediaSession.ControllerInfo f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda76(MediaSession.ControllerInfo r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r2) {
        MediaSessionStub.m907$r8$lambda$2isH75fLe4J0Dtj3oqE3ggOXeg(this.f$0, this.f$1, (ListenableFuture) r2);
    }
}
