package androidx.media3.exoplayer.drm;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultDrmSessionManager.PreacquiredSessionReference f$0;
    public final /* synthetic */ Format f$1;

    public /* synthetic */ DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda0(DefaultDrmSessionManager.PreacquiredSessionReference r1, Format r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DefaultDrmSessionManager.PreacquiredSessionReference.$r8$lambda$3uBQpbDSBR_JYCKTHo5FzQm93bs(this.f$0, this.f$1);
    }
}
