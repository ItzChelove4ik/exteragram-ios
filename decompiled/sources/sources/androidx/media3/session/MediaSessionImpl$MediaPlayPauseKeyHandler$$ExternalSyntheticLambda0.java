package androidx.media3.session;

import android.view.KeyEvent;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionImpl.MediaPlayPauseKeyHandler f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ KeyEvent f$2;

    public /* synthetic */ MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0(MediaSessionImpl.MediaPlayPauseKeyHandler r1, MediaSession.ControllerInfo r2, KeyEvent r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionImpl.MediaPlayPauseKeyHandler.$r8$lambda$uyPWhim0S19Cqz8c58uvz4Iclzg(this.f$0, this.f$1, this.f$2);
    }
}
