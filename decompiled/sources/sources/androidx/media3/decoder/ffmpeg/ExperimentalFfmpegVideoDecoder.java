package androidx.media3.decoder.ffmpeg;

import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.List;

/* loaded from: classes3.dex */
final class ExperimentalFfmpegVideoDecoder extends SimpleDecoder<DecoderInputBuffer, VideoDecoderOutputBuffer, FfmpegDecoderException> {
    private final String codecName;
    private int degree;
    private final byte[] extraData;
    private long nativeContext;
    private volatile int outputMode;
    private final ArrayDeque<PendingInput> pendingInputs;

    public static final class PendingInput {
        public final ByteBuffer data;
        public final long timeUs;

        public PendingInput(ByteBuffer r1, long r2) {
            this.data = r1;
            this.timeUs = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$KsPZ8n3VnwUduE9ssA7RhBITdHg(ExperimentalFfmpegVideoDecoder r0, DecoderOutputBuffer r1) {
        r0.releaseOutputBuffer(r1);
    }

    public ExperimentalFfmpegVideoDecoder(int r8, int r9, int r10, int r11, Format r12) {
        super(new DecoderInputBuffer[r8], new VideoDecoderOutputBuffer[r9]);
        this.degree = 0;
        this.pendingInputs = new ArrayDeque();
        if (FfmpegLibrary.isAvailable() == false) goto L11;
        String r1 = (String) Assertions.checkNotNull(FfmpegLibrary.getCodecName(r12.sampleMimeType));
        this.codecName = r1;
        byte[] r2 = getExtraData(r12.sampleMimeType, r12.initializationData);
        this.extraData = r2;
        int r4 = r12.rotationDegrees;
        this.degree = r4;
        long r7 = ffmpegInitialize(r1, r2, r11, r4, r12.width, r12.height);
        this.nativeContext = r7;
        if (r7 == 0) goto L9;
        setInitialInputBufferSize(r10);
        return;
    L9:
        throw new FfmpegDecoderException("Failed to initialize decoder.");
    L11:
        throw new FfmpegDecoderException("Failed to load decoder native library.");
    }

    private static PendingInput copyPendingInput(ByteBuffer r1, long r2) {
        r1.position(0);
        ByteBuffer r0 = ByteBuffer.allocateDirect(r1.limit());
        r0.put(r1);
        r0.flip();
        return new PendingInput(r0, r2);
    }

    private native long ffmpegInitialize(String r1, byte[] r2, int r3, int r4, int r5, int r6);

    private native int ffmpegReceiveFrame(long r1, int r3, VideoDecoderOutputBuffer r4, boolean r5);

    private native void ffmpegRelease(long r1);

    private native int ffmpegRenderFrame(long r1, Surface r3, VideoDecoderOutputBuffer r4, int r5, int r6);

    private native long ffmpegReset(long r1);

    private native int ffmpegSendPacket(long r1, ByteBuffer r3, int r4, long r5);

    private static byte[] getExtraData(String r3, List<byte[]> r4) {
        int r32 = 0;
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        r1 = r1 + r4.get(r0).length;
        r0 = r0 + 1;
        goto L4
    L6:
        if (r1 <= 0) goto L12;
        byte[] r02 = new byte[r1];
        ByteBuffer r12 = ByteBuffer.wrap(r02);
    L9:
        if (r32 >= r4.size()) goto L11;
        r12.put(r4.get(r32));
        r32 = r32 + 1;
        goto L9
    L11:
        return r02;
    L12:
        return null;
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(2, FfmpegLibrary.getInputBufferPaddingSize());
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderOutputBuffer createOutputBuffer() {
        return createOutputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public VideoDecoderOutputBuffer createOutputBuffer() {
        return new VideoDecoderOutputBuffer(new ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0(this));
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderException createUnexpectedDecodeException(Throwable r1) {
        return createUnexpectedDecodeException(r1);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public FfmpegDecoderException createUnexpectedDecodeException(Throwable r2) {
        return new FfmpegDecoderException("Unexpected decode error", r2);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public /* bridge */ /* synthetic */ DecoderException decode(DecoderInputBuffer r1, DecoderOutputBuffer r2, boolean r3) {
        return decode(r1, (VideoDecoderOutputBuffer) r2, r3);
    }

    public FfmpegDecoderException decode(DecoderInputBuffer r19, VideoDecoderOutputBuffer r20, boolean r21) {
        if (r21 == false) goto L9;
        long r1 = ffmpegReset(this.nativeContext);
        this.nativeContext = r1;
        if (r1 == 0) goto L7;
        this.pendingInputs.clear();
        goto L9
    L7:
        return new FfmpegDecoderException("Error resetting (see logcat).");
    L9:
        ByteBuffer r9 = (ByteBuffer) Util.castNonNull(r19.data);
        boolean r10 = isAtLeastOutputStartTimeUs(r19.timeUs);
        boolean r11 = !r10;
    L11:
        if (this.pendingInputs.isEmpty() == true) goto L19;
        PendingInput r12 = this.pendingInputs.peek();
        long r2 = this.nativeContext;
        ByteBuffer r3 = r12.data;
        int r13 = ffmpegSendPacket(r2, r3, r3.limit(), r12.timeUs);
        if (r13 == (-3)) goto L19;
        this.pendingInputs.removeFirst();
        if (r13 != (-2)) goto L11;
        return new FfmpegDecoderException("ffmpegDecode error: (see logcat)");
    L19:
        int r14 = ffmpegSendPacket(this.nativeContext, r9, r9.limit(), r19.timeUs);
        if (r14 != (-3)) goto L34;
        this.pendingInputs.addLast(copyPendingInput(r9, r19.timeUs));
        if (r10 == false) goto L24;
        r20.init(r19.timeUs, this.outputMode, null);
    L24:
        int r0 = ffmpegReceiveFrame(this.nativeContext, this.outputMode, r20, r11);
        if (r0 == (-2)) goto L27;
        if (r0 != (-1)) goto L30;
        r20.shouldBeSkipped = true;
    L32:
        return null;
    L30:
        if (r10 == false) goto L32;
        r20.format = r19.format;
        goto L32
    L27:
        return new FfmpegDecoderException("ffmpegDecode error: (see logcat)");
    L34:
        if (r14 != (-1)) goto L37;
        r20.shouldBeSkipped = true;
        return null;
    L37:
        if (r14 == (-2)) goto L39;
        if (r10 == false) goto L42;
        r20.init(r19.timeUs, this.outputMode, null);
    L42:
        int r02 = ffmpegReceiveFrame(this.nativeContext, this.outputMode, r20, r11);
        if (r02 == (-2)) goto L45;
        if (r02 != (-1)) goto L48;
        r20.shouldBeSkipped = true;
    L48:
        if (r10 == false) goto L50;
        r20.format = r19.format;
    L50:
        return null;
    L45:
        return new FfmpegDecoderException("ffmpegDecode error: (see logcat)");
    L39:
        return new FfmpegDecoderException("ffmpegDecode error: (see logcat)");
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "ffmpeg" + FfmpegLibrary.getVersion() + "-" + this.codecName;
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public void release() {
        super.release();
        ffmpegRelease(this.nativeContext);
        this.nativeContext = 0;
    }

    public void renderToSurface(VideoDecoderOutputBuffer r10, Surface r11) {
        if (r10.mode != 1) goto L12;
        int r9 = ffmpegRenderFrame(this.nativeContext, r11, r10, r10.width, r10.height);
        if (r9 == (-2)) goto L10;
        if (r9 == (-4)) goto L10;
        return;
    L10:
        throw new FfmpegDecoderException("Buffer render error: " + r9);
    L12:
        throw new FfmpegDecoderException("Invalid output mode.");
    }

    public void setOutputMode(int r1) {
        this.outputMode = r1;
    }
}
