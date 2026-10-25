package androidx.media3.decoder.ffmpeg;

import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.decoder.SimpleDecoderOutputBuffer;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.List;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.telegram.messenger.MediaController;

/* loaded from: classes3.dex */
public final class FfmpegAudioDecoder extends SimpleDecoder<DecoderInputBuffer, SimpleDecoderOutputBuffer, FfmpegDecoderException> {
    private static final byte[] flacStreamMarker = null;
    private volatile int channelCount;
    private final String codecName;
    private final int encoding;
    private final byte[] extraData;
    private boolean hasOutputFormat;
    private long nativeContext;
    private int outputBufferSize;
    private volatile int sampleRate;

    public static /* synthetic */ void $r8$lambda$gv1CCAcyN_eE2cDrXqQpFXTVmX8(FfmpegAudioDecoder r0, DecoderOutputBuffer r1) {
        r0.releaseOutputBuffer(r1);
    }

    static {
        flacStreamMarker = new byte[]{102, 76, 97, 67};
    }

    public FfmpegAudioDecoder(Format r7, int r8, int r9, int r10, boolean r11) {
        super(new DecoderInputBuffer[r8], new SimpleDecoderOutputBuffer[r9]);
        if (FfmpegLibrary.isAvailable() == false) goto L19;
        Preconditions.checkNotNull(r7.sampleMimeType);
        String r1 = (String) Preconditions.checkNotNull(FfmpegLibrary.getCodecName(r7.sampleMimeType));
        this.codecName = r1;
        byte[] r2 = getExtraData(r7.sampleMimeType, r7.initializationData);
        this.extraData = r2;
        if (r11 == false) goto L7;
        int r82 = 4;
    L8:
        this.encoding = r82;
        if (r11 == false) goto L11;
        int r83 = 131070;
    L12:
        this.outputBufferSize = r83;
        long r6 = ffmpegInitialize(r1, r2, r11, r7.sampleRate, r7.channelCount);
        this.nativeContext = r6;
        if (r6 == 0) goto L17;
        setInitialInputBufferSize(r10);
        return;
    L17:
        throw new FfmpegDecoderException("Initialization failed.");
    L11:
        r83 = 65535;
        goto L12
    L7:
        r82 = 2;
        goto L8
    L19:
        throw new FfmpegDecoderException("Failed to load decoder native libraries.");
    }

    private static boolean arrayStartsWith(byte[] r4, byte[] r5) {
        if (r4.length >= r5.length) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r5.length) goto L12;
        if (r4[r0] != r5[r0]) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    private static byte[] extractFlacStreamInfo(byte[] r7) {
        byte[] r0 = flacStreamMarker;
        if (arrayStartsWith(r7, r0) == false) goto L5;
        int r02 = r0.length;
    L7:
        if ((r7.length - r02) != 34) goto L10;
        byte[] r1 = new byte[34];
        System.arraycopy(r7, r02, r1, 0, 34);
        return r1;
    L10:
        int r4 = r02 + 4;
        if (r7.length < r4) goto L19;
        int r12 = r7[r02] & ByteCompanionObject.MAX_VALUE;
        int r5 = (((r7[r02 + 1] & UByte.MAX_VALUE) << 16) | ((r7[r02 + 2] & UByte.MAX_VALUE) << 8)) | (r7[r02 + 3] & UByte.MAX_VALUE);
        if (r12 != 0) goto L21;
        if (r5 == 34) goto L16;
        return null;
    L16:
        if (r7.length < (r02 + 38)) goto L23;
        byte[] r03 = new byte[34];
        System.arraycopy(r7, r4, r03, 0, 34);
        return r03;
    L23:
        return null;
    L21:
        return null;
    L19:
        return null;
    L5:
        r02 = 0;
        goto L7
    }

    private native int ffmpegDecode(long r1, ByteBuffer r3, int r4, SimpleDecoderOutputBuffer r5, ByteBuffer r6, int r7);

    private native int ffmpegGetChannelCount(long r1);

    private native int ffmpegGetSampleRate(long r1);

    private native long ffmpegInitialize(String r1, byte[] r2, boolean r3, int r4, int r5);

    private native void ffmpegRelease(long r1);

    private native long ffmpegReset(long r1, byte[] r3);

    private static byte[] getAlacExtraData(List<byte[]> r3) {
        byte[] r32 = r3.get(0);
        int r1 = r32.length + 12;
        ByteBuffer r2 = ByteBuffer.allocate(r1);
        r2.putInt(r1);
        r2.putInt(1634492771);
        r2.putInt(0);
        r2.put(r32, 0, r32.length);
        return r2.array();
    }

    private static byte[] getExtraData(String r3, List<byte[]> r4) {
        r3.getClass();
        char r2 = 65535;
        switch(r3.hashCode()) {
            case -1003765268: goto L22;
            case -53558318: goto L18;
            case 1504470054: goto L14;
            case 1504619009: goto L10;
            case 1504891608: goto L6;
            default: goto L25;
        };
    L25:
        switch(r2) {
            case 0: goto L35;
            case 1: goto L33;
            case 2: goto L31;
            case 3: goto L29;
            case 4: goto L33;
            default: goto L26;
        };
    L26:
        return null;
    L29:
        return getFlacExtraData(r4);
    L31:
        return getAlacExtraData(r4);
    L33:
        return r4.get(0);
    L35:
        return getVorbisExtraData(r4);
    L6:
        if (r3.equals("audio/opus") == false) goto L25;
        r2 = 4;
        goto L25
    L10:
        if (r3.equals("audio/flac") == false) goto L25;
        r2 = 3;
        goto L25
    L14:
        if (r3.equals("audio/alac") == false) goto L25;
        r2 = 2;
        goto L25
    L18:
        if (r3.equals(MediaController.AUDIO_MIME_TYPE) == false) goto L25;
        r2 = 1;
        goto L25
    L22:
        if (r3.equals("audio/vorbis") == false) goto L25;
        r2 = 0;
        goto L25
    }

    private static byte[] getFlacExtraData(List<byte[]> r2) {
        int r0 = 0;
    L4:
        if (r0 >= r2.size()) goto L9;
        byte[] r1 = extractFlacStreamInfo(r2.get(r0));
        if (r1 != null) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private static byte[] getVorbisExtraData(List<byte[]> r5) {
        byte[] r1 = r5.get(0);
        byte[] r52 = r5.get(1);
        byte[] r3 = new byte[(r1.length + r52.length) + 6];
        r3[0] = (byte) (r1.length >> 8);
        r3[1] = (byte) (r1.length & 255);
        System.arraycopy(r1, 0, r3, 2, r1.length);
        r3[r1.length + 2] = 0;
        r3[r1.length + 3] = 0;
        r3[r1.length + 4] = (byte) (r52.length >> 8);
        r3[r1.length + 5] = (byte) (r52.length & 255);
        System.arraycopy(r52, 0, r3, r1.length + 6, r52.length);
        return r3;
    }

    private ByteBuffer growOutputBuffer(SimpleDecoderOutputBuffer r1, int r2) {
        this.outputBufferSize = r2;
        return r1.grow(r2);
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
    public SimpleDecoderOutputBuffer createOutputBuffer() {
        return new SimpleDecoderOutputBuffer(new FfmpegAudioDecoder$$ExternalSyntheticLambda0(this));
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
        return decode(r1, (SimpleDecoderOutputBuffer) r2, r3);
    }

    public FfmpegDecoderException decode(DecoderInputBuffer r9, SimpleDecoderOutputBuffer r10, boolean r11) {
        if (r11 == false) goto L7;
        long r0 = ffmpegReset(this.nativeContext, this.extraData);
        this.nativeContext = r0;
        if (r0 != 0) goto L7;
        return new FfmpegDecoderException("Error resetting (see logcat).");
    L7:
        ByteBuffer r3 = (ByteBuffer) Util.castNonNull(r9.data);
        int r8 = ffmpegDecode(this.nativeContext, r3, r3.limit(), r10, r10.init(r9.timeUs, this.outputBufferSize), this.outputBufferSize);
        if (r8 != (-2)) goto L12;
        return new FfmpegDecoderException("Error decoding (see logcat).");
    L12:
        if (r8 != (-1)) goto L15;
        r10.shouldBeSkipped = true;
        return null;
    L15:
        if (r8 != 0) goto L19;
        r10.shouldBeSkipped = true;
        return null;
    L19:
        if (this.hasOutputFormat == true) goto L26;
        this.channelCount = ffmpegGetChannelCount(this.nativeContext);
        this.sampleRate = ffmpegGetSampleRate(this.nativeContext);
        if (this.sampleRate == 0) goto L23;
    L25:
        this.hasOutputFormat = true;
        goto L26
    L23:
        if ("alac".equals(this.codecName) == false) goto L25;
        Preconditions.checkNotNull(this.extraData);
        ParsableByteArray r92 = new ParsableByteArray(this.extraData);
        r92.setPosition(this.extraData.length - 4);
        this.sampleRate = r92.readUnsignedIntToInt();
    L26:
        ByteBuffer r93 = (ByteBuffer) Preconditions.checkNotNull(r10.data);
        r93.position(0);
        r93.limit(r8);
        return null;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getEncoding() {
        return this.encoding;
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "ffmpeg" + FfmpegLibrary.getVersion() + "-" + this.codecName;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public void release() {
        super.release();
        ffmpegRelease(this.nativeContext);
        this.nativeContext = 0;
    }
}
