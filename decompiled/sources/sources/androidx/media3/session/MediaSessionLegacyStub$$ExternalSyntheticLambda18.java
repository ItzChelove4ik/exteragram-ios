package androidx.media3.session;

import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda18 implements MediaSessionLegacyStub.SessionTask {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ ResultReceiver f$3;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda18(MediaSessionLegacyStub r1, SessionCommand r2, Bundle r3, ResultReceiver r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public final void run(MediaSession.ControllerInfo r4) {
        MediaSessionLegacyStub.$r8$lambda$Y6DY0gQr0UFcIDfia_a7LYaKTqo(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
