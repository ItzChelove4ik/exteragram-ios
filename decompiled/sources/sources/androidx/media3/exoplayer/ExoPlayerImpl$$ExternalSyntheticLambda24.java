package androidx.media3.exoplayer;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda24 implements ListenerSet.Event {
    public final /* synthetic */ AudioAttributes f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda24(AudioAttributes r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.$r8$lambda$dcv3_plMRw3eMKSaAkcF4LfoFPo(this.f$0, (Player.Listener) r1);
    }
}
