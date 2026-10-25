package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionImpl.AnonymousClass1 f$0;
    public final /* synthetic */ MediaSession.MediaItemsWithStartPosition f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MediaSession.ControllerInfo f$3;
    public final /* synthetic */ Player.Commands f$4;

    public /* synthetic */ MediaSessionImpl$1$$ExternalSyntheticLambda0(MediaSessionImpl.AnonymousClass1 r1, MediaSession.MediaItemsWithStartPosition r2, boolean r3, MediaSession.ControllerInfo r4, Player.Commands r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionImpl.AnonymousClass1.m880$r8$lambda$SAGIpfgpIPzdfCKENbGxwvJew8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
