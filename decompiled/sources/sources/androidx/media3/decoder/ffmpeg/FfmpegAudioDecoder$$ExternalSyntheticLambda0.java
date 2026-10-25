package androidx.media3.decoder.ffmpeg;

import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoderOutputBuffer;

/* loaded from: classes3.dex */
public final /* synthetic */ class FfmpegAudioDecoder$$ExternalSyntheticLambda0 implements DecoderOutputBuffer.Owner {
    public final /* synthetic */ FfmpegAudioDecoder f$0;

    public /* synthetic */ FfmpegAudioDecoder$$ExternalSyntheticLambda0(FfmpegAudioDecoder r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer.Owner
    public final void releaseOutputBuffer(DecoderOutputBuffer r1) {
        FfmpegAudioDecoder.$r8$lambda$gv1CCAcyN_eE2cDrXqQpFXTVmX8(this.f$0, (SimpleDecoderOutputBuffer) r1);
    }
}
