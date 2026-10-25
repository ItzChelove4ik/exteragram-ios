package androidx.media3.session;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda52 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MediaLibraryService$LibraryParams f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda52(String r1, int r2, int r3, MediaLibraryService$LibraryParams r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl r8, MediaSession.ControllerInfo r9, int r10) {
        String r0 = this.f$0;
        int r1 = this.f$1;
        int r2 = this.f$2;
        MediaLibraryService$LibraryParams r3 = this.f$3;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r8);
        return MediaSessionStub.m921$r8$lambda$lnOMCm1EtshGmAsX3bb8oWGPU4(r0, r1, r2, r3, null, r9, r10);
    }
}
