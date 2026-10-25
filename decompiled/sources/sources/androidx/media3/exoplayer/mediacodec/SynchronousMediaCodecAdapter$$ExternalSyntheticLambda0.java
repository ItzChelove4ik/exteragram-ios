package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;

/* loaded from: classes4.dex */
public final /* synthetic */ class SynchronousMediaCodecAdapter$$ExternalSyntheticLambda0 implements MediaCodec.OnFrameRenderedListener {
    public final /* synthetic */ SynchronousMediaCodecAdapter f$0;
    public final /* synthetic */ MediaCodecAdapter.OnFrameRenderedListener f$1;

    public /* synthetic */ SynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(SynchronousMediaCodecAdapter r1, MediaCodecAdapter.OnFrameRenderedListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.media.MediaCodec.OnFrameRenderedListener
    public final void onFrameRendered(MediaCodec r8, long r9, long r11) {
        SynchronousMediaCodecAdapter.$r8$lambda$YcXm01yWwhzQB5uuYAW5ZUYz48Q(this.f$0, this.f$1, r8, r9, r11);
    }
}
