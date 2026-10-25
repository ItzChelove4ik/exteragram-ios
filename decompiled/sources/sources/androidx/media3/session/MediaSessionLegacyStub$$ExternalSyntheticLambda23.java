package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaSessionManager;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MediaSessionManager.RemoteUserInfo f$3;
    public final /* synthetic */ MediaSessionLegacyStub.SessionTask f$4;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda23(MediaSessionLegacyStub r1, SessionCommand r2, int r3, MediaSessionManager.RemoteUserInfo r4, MediaSessionLegacyStub.SessionTask r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionLegacyStub.$r8$lambda$mk9a_qh3f89yHUBeGey9WUPhflA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
