package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda31 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaItem f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda31(MediaItem r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return MediaSessionStub.$r8$lambda$KS3xK4qkadqzcBe0qXhTzN2XY4U(this.f$0, this.f$1, r2, r3, r4);
    }
}
