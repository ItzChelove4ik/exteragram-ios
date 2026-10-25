package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda71 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaSessionStub.SessionTask f$0;
    public final /* synthetic */ MediaSessionStub.MediaItemsWithStartPositionPlayerTask f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda71(MediaSessionStub.SessionTask r1, MediaSessionStub.MediaItemsWithStartPositionPlayerTask r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return MediaSessionStub.$r8$lambda$G2yFvKUfRMY9Pnv8FG4VEvznbt0(this.f$0, this.f$1, r2, r3, r4);
    }
}
