package androidx.media3.decoder.ffmpeg;

import android.os.Handler;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.video.DecoderVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* loaded from: classes3.dex */
public final class ExperimentalFfmpegVideoRenderer extends DecoderVideoRenderer {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final int DEFAULT_INPUT_BUFFER_SIZE = 0;
    private ExperimentalFfmpegVideoDecoder decoder;
    private final int numInputBuffers;
    private final int numOutputBuffers;
    private final int threads;

    static {
        DEFAULT_INPUT_BUFFER_SIZE = ((Util.ceilDivide(1280, 64) * Util.ceilDivide(720, 64)) * 6144) / 2;
    }

    public ExperimentalFfmpegVideoRenderer(long r11, Handler r13, VideoRendererEventListener r14, int r15) {
        this(r11, r13, r14, r15, Runtime.getRuntime().availableProcessors(), 4, 4);
    }

    public ExperimentalFfmpegVideoRenderer(long r1, Handler r3, VideoRendererEventListener r4, int r5, int r6, int r7, int r8) {
        super(r1, r3, r4, r5);
        this.threads = r6;
        this.numInputBuffers = r7;
        this.numOutputBuffers = r8;
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    public DecoderReuseEvaluation canReuseDecoder(String r7, Format r8, Format r9) {
        return new DecoderReuseEvaluation(r7, r8, r9, 0, 8);
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    public Decoder<DecoderInputBuffer, VideoDecoderOutputBuffer, FfmpegDecoderException> createDecoder(Format r7, CryptoConfig r8) {
        TraceUtil.beginSection("createFfmpegVideoDecoder");
        int r82 = r7.maxInputSize;
        if (r82 == (-1)) goto L5;
    L4:
        int r3 = r82;
        int r4 = Math.max(this.threads, 1);
        ExperimentalFfmpegVideoDecoder r0 = new ExperimentalFfmpegVideoDecoder(this.numInputBuffers, this.numOutputBuffers, r3, r4, r7);
        this.decoder = r0;
        TraceUtil.endSection();
        return r0;
    L5:
        r82 = DEFAULT_INPUT_BUFFER_SIZE;
        goto L4
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "ExperimentalFfmpegVideoRenderer";
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    public void renderOutputBufferToSurface(VideoDecoderOutputBuffer r1, Surface r2) {
        ExperimentalFfmpegVideoDecoder r0 = this.decoder;     // Catch: Throwable -> L7
        if (r0 == null) goto L10;
        r0.renderToSurface(r1, r2);     // Catch: Throwable -> L7
        r1.release();
        return;
    L10:
        throw new FfmpegDecoderException("Failed to render output buffer to surface: decoder is not initialized.");     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        r1.release();
        throw th;
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    public void setDecoderOutputMode(int r1) {
        ExperimentalFfmpegVideoDecoder r0 = this.decoder;
        if (r0 == null) goto L6;
        r0.setOutputMode(r1);
        return;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final int supportsFormat(Format r3) {
        String r2 = r3.sampleMimeType;
        if (FfmpegLibrary.isAvailable() == false) goto L18;
        if (MimeTypes.isVideo(r2) == false) goto L18;
        if (FfmpegLibrary.supportsFormat(r2) == true) goto L12;
        return RendererCapabilities.create(1);
    L12:
        if (r3.cryptoType == 0) goto L16;
        return RendererCapabilities.create(2);
    L16:
        return RendererCapabilities.create(4, 8, 0);
    L18:
        return RendererCapabilities.create(0);
    }
}
