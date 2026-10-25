package androidx.media3.session;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda62 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ MediaLibraryService$LibraryParams f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda62(String r1, MediaLibraryService$LibraryParams r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        String r0 = this.f$0;
        MediaLibraryService$LibraryParams r1 = this.f$1;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        return MediaSessionStub.m916$r8$lambda$Xc4eNQfbEJS3aLWoIm18RfatR8(r0, r1, null, r3, r4);
    }
}
