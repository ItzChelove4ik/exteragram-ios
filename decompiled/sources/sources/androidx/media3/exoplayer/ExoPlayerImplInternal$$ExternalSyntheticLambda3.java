package androidx.media3.exoplayer;

import android.media.MediaFormat;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImplInternal$$ExternalSyntheticLambda3 implements VideoFrameMetadataListener {
    public final /* synthetic */ ExoPlayerImplInternal f$0;
    public final /* synthetic */ VideoFrameMetadataListener f$1;

    public /* synthetic */ ExoPlayerImplInternal$$ExternalSyntheticLambda3(ExoPlayerImplInternal r1, VideoFrameMetadataListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameMetadataListener
    public final void onVideoFrameAboutToBeRendered(long r9, long r11, Format r13, MediaFormat r14) {
        ExoPlayerImplInternal.$r8$lambda$PHPl0lerVHRNmb61QccGegOGlG0(this.f$0, this.f$1, r9, r11, r13, r14);
    }
}
