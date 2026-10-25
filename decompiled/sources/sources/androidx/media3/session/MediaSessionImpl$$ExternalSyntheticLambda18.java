package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda18 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ SessionCommand f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda18(SessionCommand r1, Bundle r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r2, int r3) {
        MediaSessionImpl.$r8$lambda$0JJAc8dqDCSwOHZTi4HsGWjO3d8(this.f$0, this.f$1, r2, r3);
    }
}
