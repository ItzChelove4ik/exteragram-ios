package androidx.media3.decoder.ffmpeg;

import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;

/* loaded from: classes3.dex */
public final /* synthetic */ class ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0 implements DecoderOutputBuffer.Owner {
    public final /* synthetic */ ExperimentalFfmpegVideoDecoder f$0;

    public /* synthetic */ ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0(ExperimentalFfmpegVideoDecoder r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.decoder.DecoderOutputBuffer.Owner
    public final void releaseOutputBuffer(DecoderOutputBuffer r1) {
        ExperimentalFfmpegVideoDecoder.$r8$lambda$KsPZ8n3VnwUduE9ssA7RhBITdHg(this.f$0, (VideoDecoderOutputBuffer) r1);
    }
}
