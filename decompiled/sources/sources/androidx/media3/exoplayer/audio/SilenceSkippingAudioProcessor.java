package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class SilenceSkippingAudioProcessor extends BaseAudioProcessor {
    private int bytesPerFrame;
    private byte[] contiguousOutputBuffer;
    private boolean enabled;
    private final long maxSilenceToKeepDurationUs;
    private byte[] maybeSilenceBuffer;
    private int maybeSilenceBufferContentsSize;
    private int maybeSilenceBufferStartIndex;
    private final int minVolumeToKeepPercentageWhenMuting;
    private final long minimumSilenceDurationUs;
    private int outputSilenceFramesSinceNoise;
    private final float silenceRetentionRatio;
    private final short silenceThresholdLevel;
    private long skippedFrames;
    private int state;

    public SilenceSkippingAudioProcessor() {
        this(100000, 0.2f, 2000000, 10, 1024);
    }

    public SilenceSkippingAudioProcessor(long r3, float r5, long r6, int r8, short r9) {
        boolean r0 = false;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
        if (r5 >= 0.0f) goto L5;
    L7:
        Assertions.checkArgument(r0);
        this.minimumSilenceDurationUs = r3;
        this.silenceRetentionRatio = r5;
        this.maxSilenceToKeepDurationUs = r6;
        this.minVolumeToKeepPercentageWhenMuting = r8;
        this.silenceThresholdLevel = r9;
        byte[] r32 = Util.EMPTY_BYTE_ARRAY;
        this.maybeSilenceBuffer = r32;
        this.contiguousOutputBuffer = r32;
        return;
    L5:
        if (r5 > 1.0f) goto L7;
        r0 = true;
        goto L7
    }

    private int alignToBytePerFrameBoundary(float r1) {
        return alignToBytePerFrameBoundary((int) r1);
    }

    private int alignToBytePerFrameBoundary(int r1) {
        int r0 = this.bytesPerFrame;
        return (r1 / r0) * r0;
    }

    private int calculateFadeInPercentage(int r2, int r3) {
        int r1 = this.minVolumeToKeepPercentageWhenMuting;
        return r1 + ((((100 - r1) * (r2 * 1000)) / r3) / 1000);
    }

    private int calculateFadeOutPercentage(int r1, int r2) {
        return (((this.minVolumeToKeepPercentageWhenMuting - 100) * ((r1 * 1000) / r2)) / 1000) + 100;
    }

    private int calculateShortenedSilenceLength(int r3) {
        int r0 = ((durationUsToFrames(this.maxSilenceToKeepDurationUs) - this.outputSilenceFramesSinceNoise) * this.bytesPerFrame) - (this.maybeSilenceBuffer.length / 2);
        if (r0 < 0) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkState(r1);
        return alignToBytePerFrameBoundary(Math.min((r3 * this.silenceRetentionRatio) + 0.5f, r0));
    L5:
        r1 = false;
        goto L6
    }

    private int durationUsToFrames(long r3) {
        return (int) ((r3 * this.inputAudioFormat.sampleRate) / 1000000);
    }

    private int findNoiseLimit(ByteBuffer r4) {
        int r0 = r4.limit() - 1;
    L4:
        if (r0 < r4.position()) goto L11;
        if (isNoise(r4.get(r0), r4.get(r0 - 1)) == true) goto L7;
        r0 = r0 - 2;
        goto L4
    L7:
        int r3 = this.bytesPerFrame;
        return ((r0 / r3) * r3) + r3;
    L11:
        return r4.position();
    }

    private int findNoisePosition(ByteBuffer r4) {
        int r0 = r4.position() + 1;
    L4:
        if (r0 >= r4.limit()) goto L11;
        if (isNoise(r4.get(r0), r4.get(r0 - 1)) == true) goto L7;
        r0 = r0 + 2;
        goto L4
    L7:
        int r3 = this.bytesPerFrame;
        return r3 * (r0 / r3);
    L11:
        return r4.limit();
    }

    private boolean isNoise(byte r1, byte r2) {
        if (Math.abs(twoByteSampleToInt(r1, r2)) <= this.silenceThresholdLevel) goto L6;
        return true;
    L6:
        return false;
    }

    private void modifyVolume(byte[] r4, int r5, int r6) {
        if (r6 == 3) goto L15;
        int r0 = 0;
    L6:
        if (r0 >= r5) goto L20;
        int r1 = twoByteSampleToInt(r4[r0 + 1], r4[r0]);
        if (r6 != 0) goto L11;
        int r2 = calculateFadeOutPercentage(r0, r5 - 1);
    L14:
        sampleIntToTwoBigEndianBytes(r4, r0, (r1 * r2) / 100);
        r0 = r0 + 2;
        goto L6
    L11:
        if (r6 != 2) goto L13;
        r2 = calculateFadeInPercentage(r0, r5 - 1);
        goto L14
    L13:
        r2 = this.minVolumeToKeepPercentageWhenMuting;
        goto L14
    L20:
        return;
    }

    private void output(ByteBuffer r2) {
        replaceOutputBuffer(r2.remaining()).put(r2).flip();
    }

    private void outputRange(byte[] r5, int r6, int r7) {
        if ((r6 % this.bytesPerFrame) != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0, "byteOutput size is not aligned to frame size " + r6);
        modifyVolume(r5, r6, r7);
        replaceOutputBuffer(r6).put(r5, 0, r6).flip();
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void outputShortenedSilenceBuffer(boolean r9) {
        int r0 = this.maybeSilenceBufferContentsSize;
        byte[] r1 = this.maybeSilenceBuffer;
        if (r0 == r1.length) goto L7;
        if (r9 == true) goto L7;
        return;
    L7:
        boolean r3 = false;
        if (this.outputSilenceFramesSinceNoise != 0) goto L17;
        if (r9 == false) goto L13;
        outputSilence(r0, 3);
        int r92 = r0;
    L11:
        int r12 = r92;
    L21:
        if ((r92 % this.bytesPerFrame) != 0) goto L23;
        boolean r2 = true;
    L24:
        Assertions.checkState(r2, "bytesConsumed is not aligned to frame size: %s" + r92);
        if (r0 < r12) goto L27;
        r3 = true;
    L27:
        Assertions.checkState(r3);
        this.maybeSilenceBufferContentsSize -= r92;
        int r02 = this.maybeSilenceBufferStartIndex + r92;
        this.maybeSilenceBufferStartIndex = r02;
        this.maybeSilenceBufferStartIndex = r02 % this.maybeSilenceBuffer.length;
        this.outputSilenceFramesSinceNoise = this.outputSilenceFramesSinceNoise + (r12 / this.bytesPerFrame);
        this.skippedFrames += (r92 - r12) / r2;
        return;
    L23:
        r2 = false;
        goto L24
    L13:
        if (r0 < (r1.length / 2)) goto L15;
        boolean r93 = true;
    L16:
        Assertions.checkState(r93);
        r92 = this.maybeSilenceBuffer.length / 2;
        outputSilence(r92, 0);
        goto L11
    L15:
        r93 = false;
        goto L16
    L17:
        if (r9 == false) goto L19;
        int r94 = r0 - (r1.length / 2);
        int r13 = (r1.length / 2) + r94;
        int r95 = calculateShortenedSilenceLength(r94) + (this.maybeSilenceBuffer.length / 2);
        outputSilence(r95, 2);
        r12 = r95;
        r92 = r13;
        goto L21
    L19:
        r92 = r0 - (r1.length / 2);
        r12 = calculateShortenedSilenceLength(r92);
        outputSilence(r12, 1);
        goto L21
    }

    private void outputSilence(int r8, int r9) {
        if (r8 != 0) goto L4;
        return;
    L4:
        boolean r1 = true;
        if (this.maybeSilenceBufferContentsSize < r8) goto L7;
        boolean r0 = true;
    L8:
        Assertions.checkArgument(r0);
        int r02 = this.maybeSilenceBufferStartIndex;
        if (r9 != 2) goto L17;
        int r3 = this.maybeSilenceBufferContentsSize;
        int r4 = r02 + r3;
        byte[] r5 = this.maybeSilenceBuffer;
        if (r4 > r5.length) goto L13;
        System.arraycopy(r5, (r02 + r3) - r8, this.contiguousOutputBuffer, 0, r8);
    L22:
        if ((r8 % this.bytesPerFrame) != 0) goto L24;
        boolean r03 = true;
    L25:
        Assertions.checkArgument(r03, "sizeToOutput is not aligned to frame size: " + r8);
        if (this.maybeSilenceBufferStartIndex < this.maybeSilenceBuffer.length) goto L29;
        r1 = false;
    L29:
        Assertions.checkState(r1);
        outputRange(this.contiguousOutputBuffer, r8, r9);
        return;
    L24:
        r03 = false;
        goto L25
    L13:
        int r32 = r3 - (r5.length - r02);
        if (r32 < r8) goto L16;
        System.arraycopy(r5, r32 - r8, this.contiguousOutputBuffer, 0, r8);
        goto L22
    L16:
        int r04 = r8 - r32;
        System.arraycopy(r5, r5.length - r04, this.contiguousOutputBuffer, 0, r04);
        System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, r04, r32);
        goto L22
    L17:
        int r33 = r02 + r8;
        byte[] r42 = this.maybeSilenceBuffer;
        if (r33 > r42.length) goto L20;
        System.arraycopy(r42, r02, this.contiguousOutputBuffer, 0, r8);
        goto L22
    L20:
        int r34 = r42.length - r02;
        System.arraycopy(r42, r02, this.contiguousOutputBuffer, 0, r34);
        System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, r34, r8 - r34);
        goto L22
    L7:
        r0 = false;
        goto L8
    }

    private void processNoisy(ByteBuffer r4) {
        int r0 = r4.limit();
        r4.limit(Math.min(r0, r4.position() + this.maybeSilenceBuffer.length));
        int r1 = findNoiseLimit(r4);
        if (r1 != r4.position()) goto L5;
        this.state = 1;
    L6:
        r4.limit(r0);
        return;
    L5:
        r4.limit(Math.min(r1, r4.capacity()));
        output(r4);
        goto L6
    }

    private static void sampleIntToTwoBigEndianBytes(byte[] r1, int r2, int r3) {
        if (r3 < 32767) goto L7;
        r1[r2] = -1;
        r1[r2 + 1] = ByteCompanionObject.MAX_VALUE;
        return;
    L7:
        if (r3 > (-32768)) goto L10;
        r1[r2] = 0;
        r1[r2 + 1] = ByteCompanionObject.MIN_VALUE;
        return;
    L10:
        r1[r2] = (byte) (r3 & 255);
        r1[r2 + 1] = (byte) (r3 >> 8);
    }

    private void shortenSilenceSilenceUntilNoise(ByteBuffer r11) {
        boolean r2 = true;
        if (this.maybeSilenceBufferStartIndex >= this.maybeSilenceBuffer.length) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        int r02 = r11.limit();
        int r1 = findNoisePosition(r11);
        int r4 = r1 - r11.position();
        int r5 = this.maybeSilenceBufferStartIndex;
        int r6 = this.maybeSilenceBufferContentsSize;
        int r7 = r5 + r6;
        byte[] r8 = this.maybeSilenceBuffer;
        if (r7 >= r8.length) goto L9;
        int r72 = r8.length - (r6 + r5);
        int r52 = r5 + r6;
    L10:
        if (r1 >= r02) goto L12;
        boolean r12 = true;
    L13:
        int r62 = Math.min(r4, r72);
        r11.limit(r11.position() + r62);
        r11.get(this.maybeSilenceBuffer, r52, r62);
        int r53 = this.maybeSilenceBufferContentsSize + r62;
        this.maybeSilenceBufferContentsSize = r53;
        if (r53 > this.maybeSilenceBuffer.length) goto L16;
        boolean r54 = true;
    L17:
        Assertions.checkState(r54);
        if (r12 == false) goto L21;
        if (r4 >= r72) goto L21;
    L22:
        outputShortenedSilenceBuffer(r2);
        if (r2 == false) goto L25;
        this.state = 0;
        this.outputSilenceFramesSinceNoise = 0;
    L25:
        r11.limit(r02);
        return;
    L21:
        r2 = false;
        goto L22
    L16:
        r54 = false;
        goto L17
    L12:
        r12 = false;
        goto L13
    L9:
        int r63 = r6 - (r8.length - r5);
        r72 = r5 - r63;
        r52 = r63;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    private static int twoByteSampleToInt(byte r0, byte r1) {
        int r02 = r0 << 8;
        return r02 | (r1 & UByte.MAX_VALUE);
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        if (super.isActive() == true) goto L5;
        return false;
    L5:
        if (this.enabled == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r2) {
        if (r2.encoding != 2) goto L10;
        if (r2.sampleRate == (-1)) goto L7;
        return r2;
    L7:
        return AudioProcessor.AudioFormat.NOT_SET;
    L10:
        throw new AudioProcessor.UnhandledAudioFormatException(r2);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        if (isActive() == false) goto L7;
        this.bytesPerFrame = this.inputAudioFormat.channelCount * 2;
        int r0 = alignToBytePerFrameBoundary(durationUsToFrames(this.minimumSilenceDurationUs) / 2) * 2;
        if (this.maybeSilenceBuffer.length == r0) goto L7;
        this.maybeSilenceBuffer = new byte[r0];
        this.contiguousOutputBuffer = new byte[r0];
    L7:
        this.state = 0;
        this.skippedFrames = 0;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.maybeSilenceBufferContentsSize <= 0) goto L6;
        outputShortenedSilenceBuffer(true);
        this.outputSilenceFramesSinceNoise = 0;
        return;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.enabled = false;
        byte[] r0 = Util.EMPTY_BYTE_ARRAY;
        this.maybeSilenceBuffer = r0;
        this.contiguousOutputBuffer = r0;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r3) {
    L3:
        if (r3.hasRemaining() == false) goto L14;
        if (hasPendingOutput() == true) goto L23;
        int r0 = this.state;
        if (r0 != 0) goto L9;
        processNoisy(r3);
        goto L3
    L9:
        if (r0 != 1) goto L11;
        shortenSilenceSilenceUntilNoise(r3);
        goto L3
    L11:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L23:
        return;
    }

    public void setEnabled(boolean r1) {
        this.enabled = r1;
    }
}
