package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda35 implements Consumer {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda35(MediaSessionStub r1, MediaSession.ControllerInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r2) {
        MediaSessionStub.m927$r8$lambda$wMocS4AH4xsCwAU3JbOpgcmZug(this.f$0, this.f$1, (PlayerWrapper) r2);
    }
}
