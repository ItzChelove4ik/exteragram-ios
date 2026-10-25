package androidx.media3.decoder.ffmpeg;

import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DecoderAudioRenderer;
import com.google.common.base.Preconditions;

/* loaded from: classes3.dex */
public final class FfmpegAudioRenderer extends DecoderAudioRenderer<FfmpegAudioDecoder> {
    public FfmpegAudioRenderer(Handler r1, AudioRendererEventListener r2, AudioSink r3) {
        super(r1, r2, r3);
    }

    private boolean shouldOutputFloat(Format r6) {
        if (sinkSupportsFormat(r6, 2) == true) goto L6;
        return true;
    L6:
        if (getSinkFormatSupport(Util.getPcmFormat(4, r6.channelCount, r6.sampleRate)) == 2) goto L10;
        return false;
    L10:
        return !"audio/ac3".equals(r6.sampleMimeType);
    }

    private boolean sinkSupportsFormat(Format r2, int r3) {
        return sinkSupportsFormat(Util.getPcmFormat(r3, r2.channelCount, r2.sampleRate));
    }

    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    public /* bridge */ /* synthetic */ Decoder createDecoder(Format r1, CryptoConfig r2) {
        return createDecoder(r1, r2);
    }

    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    public FfmpegAudioDecoder createDecoder(Format r7, CryptoConfig r8) {
        TraceUtil.beginSection("createFfmpegAudioDecoder");
        int r82 = r7.maxInputSize;
        if (r82 == (-1)) goto L5;
    L4:
        int r4 = r82;
        FfmpegAudioDecoder r0 = new FfmpegAudioDecoder(r7, 16, 16, r4, shouldOutputFloat(r7));
        TraceUtil.endSection();
        return r0;
    L5:
        r82 = 5760;
        goto L4
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "FfmpegAudioRenderer";
    }

    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    public /* bridge */ /* synthetic */ Format getOutputFormat(Decoder r1) {
        return getOutputFormat((FfmpegAudioDecoder) r1);
    }

    public Format getOutputFormat(FfmpegAudioDecoder r2) {
        Preconditions.checkNotNull(r2);
        return new Format.Builder().setSampleMimeType("audio/raw").setChannelCount(r2.getChannelCount()).setSampleRate(r2.getSampleRate()).setPcmEncoding(r2.getEncoding()).build();
    }

    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    public int supportsFormatInternal(Format r4) {
        String r0 = (String) Preconditions.checkNotNull(r4.sampleMimeType);
        if (FfmpegLibrary.isAvailable() == true) goto L5;
        return 0;
    L5:
        if (MimeTypes.isAudio(r0) == true) goto L8;
        return 0;
    L8:
        if (FfmpegLibrary.supportsFormat(r0) == true) goto L10;
        return 1;
    L10:
        if (sinkSupportsFormat(r4, 2) == true) goto L15;
        if (sinkSupportsFormat(r4, 4) == true) goto L15;
        return 1;
    L15:
        if (r4.cryptoType == 0) goto L17;
        return 2;
    L17:
        return 4;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.RendererCapabilities
    public int supportsMixedMimeTypeAdaptation() {
        return 8;
    }
}
