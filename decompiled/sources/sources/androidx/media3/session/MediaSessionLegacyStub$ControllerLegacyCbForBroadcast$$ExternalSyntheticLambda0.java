package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub.ControllerLegacyCbForBroadcast f$0;
    public final /* synthetic */ AtomicInteger f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ List f$3;

    public /* synthetic */ MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0(MediaSessionLegacyStub.ControllerLegacyCbForBroadcast r1, AtomicInteger r2, List r3, List r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionLegacyStub.ControllerLegacyCbForBroadcast.m901$r8$lambda$FOEjVJh95pMxDmpHvg3o9UcGz4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
