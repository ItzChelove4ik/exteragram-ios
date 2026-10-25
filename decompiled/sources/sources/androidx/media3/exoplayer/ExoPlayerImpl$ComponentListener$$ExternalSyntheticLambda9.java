package androidx.media3.exoplayer;

import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda9 implements ListenerSet.Event {
    public final /* synthetic */ DeviceInfo f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda9(DeviceInfo r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.ComponentListener.$r8$lambda$rrFwmDhvbvDQKroQT61AjQpXY_8(this.f$0, (Player.Listener) r1);
    }
}
