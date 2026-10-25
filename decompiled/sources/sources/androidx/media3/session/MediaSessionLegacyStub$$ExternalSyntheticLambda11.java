package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda11 implements MediaSessionLegacyStub.SessionTask {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda11(MediaSessionLegacyStub r1, SessionCommand r2, Bundle r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public final void run(MediaSession.ControllerInfo r3) {
        MediaSessionLegacyStub.$r8$lambda$R860oOR7Ur9nrRVIHyJ9YFQgwfc(this.f$0, this.f$1, this.f$2, r3);
    }
}
