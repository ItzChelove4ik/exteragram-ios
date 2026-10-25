package androidx.media3.session;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda57 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaLibraryService$LibraryParams f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda57(MediaLibraryService$LibraryParams r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl r1, MediaSession.ControllerInfo r2, int r3) {
        MediaLibraryService$LibraryParams r0 = this.f$0;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        return MediaSessionStub.m909$r8$lambda$4u38cwJjRvGrJnjKvlnyWzyPfU(r0, null, r2, r3);
    }
}
