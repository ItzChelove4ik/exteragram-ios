package androidx.media3.exoplayer;

import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaPeriodQueue$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaPeriodQueue f$0;
    public final /* synthetic */ ImmutableList.Builder f$1;
    public final /* synthetic */ MediaSource.MediaPeriodId f$2;

    public /* synthetic */ MediaPeriodQueue$$ExternalSyntheticLambda0(MediaPeriodQueue r1, ImmutableList.Builder r2, MediaSource.MediaPeriodId r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaPeriodQueue.$r8$lambda$fC2Yvo68fonTcmK4OlepW6eTvFM(this.f$0, this.f$1, this.f$2);
    }
}
