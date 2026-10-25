package androidx.media3.exoplayer.drm;

import android.media.MediaDrm;
import androidx.media3.exoplayer.drm.ExoMediaDrm;

/* loaded from: classes4.dex */
public final /* synthetic */ class FrameworkMediaDrm$$ExternalSyntheticLambda1 implements MediaDrm.OnEventListener {
    public final /* synthetic */ FrameworkMediaDrm f$0;
    public final /* synthetic */ ExoMediaDrm.OnEventListener f$1;

    public /* synthetic */ FrameworkMediaDrm$$ExternalSyntheticLambda1(FrameworkMediaDrm r1, ExoMediaDrm.OnEventListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.media.MediaDrm.OnEventListener
    public final void onEvent(MediaDrm r8, byte[] r9, int r10, int r11, byte[] r12) {
        FrameworkMediaDrm.$r8$lambda$FTMrYBm0_nl4l9MpEYMiZMFYRQE(this.f$0, this.f$1, r8, r9, r10, r11, r12);
    }
}
