package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda9 implements MediaSessionStub.MediaItemsWithStartPositionPlayerTask {
    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda9() {
    }

    @Override // androidx.media3.session.MediaSessionStub.MediaItemsWithStartPositionPlayerTask
    public final void run(PlayerWrapper r1, MediaSession.MediaItemsWithStartPosition r2) {
        MediaUtils.setMediaItemsWithStartIndexAndPosition(r1, r2);
    }
}
