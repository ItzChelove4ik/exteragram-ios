package androidx.media3.exoplayer;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda6 implements ListenerSet.Event {
    public final /* synthetic */ MediaItem f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda6(MediaItem r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r2) {
        ExoPlayerImpl.$r8$lambda$OWJGeulauWTiP5TmgRFLAI8hY4w(this.f$0, this.f$1, (Player.Listener) r2);
    }
}
