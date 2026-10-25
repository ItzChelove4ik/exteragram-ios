package androidx.media3.exoplayer;

import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.exoplayer.StreamVolumeManager;

/* loaded from: classes4.dex */
public final /* synthetic */ class StreamVolumeManager$$ExternalSyntheticLambda0 implements BackgroundThreadStateHandler.StateChangeListener {
    public final /* synthetic */ StreamVolumeManager f$0;

    public /* synthetic */ StreamVolumeManager$$ExternalSyntheticLambda0(StreamVolumeManager r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.BackgroundThreadStateHandler.StateChangeListener
    public final void onStateChanged(Object r1, Object r2) {
        StreamVolumeManager.m791$r8$lambda$j82104AOu2fyNbKADWnRq1sW38(this.f$0, (StreamVolumeManager.StreamVolumeState) r1, (StreamVolumeManager.StreamVolumeState) r2);
    }
}
