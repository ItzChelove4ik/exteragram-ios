package org.telegram.messenger.video.audio_input;

import java.nio.ShortBuffer;
import org.telegram.messenger.video.AudioBufferConverter;
import org.telegram.messenger.video.AudioConversions;
import org.telegram.messenger.video.AudioDecoder;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class GeneralAudioInput extends AudioInput {
    private AudioBufferConverter audioBufferConverter;
    private ShortBuffer buffer;
    private final AudioDecoder decoder;
    private boolean hasRemaining;
    private int outputChannelCount;
    private int outputSampleRate;
    private int requiredShortsForStartOffset;
    private int startOffsetShortsCounter;
    private long startOffsetUs;

    public GeneralAudioInput(String r2) {
        this.decoder = new AudioDecoder(r2);
        init();
    }

    public GeneralAudioInput(String r2, int r3) {
        this.decoder = new AudioDecoder(r2, r3);
        init();
    }

    private void decode() {
        ShortBuffer r0 = this.buffer;
        if (r0 != null) goto L5;
    L8:
        AudioDecoder.DecodedBufferData r02 = this.decoder.decode();
        if (r02.index < 0) goto L12;
        ShortBuffer r3 = r02.byteBuffer.asShortBuffer();
        this.buffer = this.audioBufferConverter.convert(r3, this.decoder.getSampleRate(), this.decoder.getChannelCount(), this.outputSampleRate, this.outputChannelCount);
        this.decoder.releaseOutputBuffer(r02.index);
        return;
    L12:
        this.buffer = null;
        return;
    L5:
        if (r0.remaining() <= 0) goto L8;
    }

    private void init() {
        this.audioBufferConverter = new AudioBufferConverter();
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public short getNext() {
        if (hasRemaining() == false) goto L22;
        int r0 = this.startOffsetShortsCounter;
        if (r0 >= this.requiredShortsForStartOffset) goto L8;
        this.startOffsetShortsCounter = r0 + 1;
        return 0;
    L8:
        decode();
        ShortBuffer r02 = this.buffer;
        if (r02 != null) goto L11;
    L13:
        short r03 = 0;
    L14:
        decode();
        ShortBuffer r1 = this.buffer;
        if (r1 != null) goto L17;
    L20:
        this.hasRemaining = false;
        return r03;
    L17:
        if (r1.remaining() < 1) goto L20;
        return r03;
    L11:
        if (r02.remaining() <= 0) goto L13;
        r03 = this.buffer.get();
        goto L14
    L22:
        GlShader$$ExternalSyntheticBUOutline1.m("Audio input has no remaining value.");
        return 0;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public int getSampleRate() {
        return this.decoder.getSampleRate();
    }

    public long getStartOffsetUs() {
        return this.startOffsetUs;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public boolean hasRemaining() {
        return this.hasRemaining;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public void release() {
        this.buffer = null;
        this.hasRemaining = false;
        this.decoder.stop();
        this.decoder.release();
    }

    public void setEndTimeUs(long r1) {
        this.decoder.setEndTimeUs(r1);
    }

    public void setStartOffsetUs(long r4) {
        if (r4 >= 0) goto L5;
        r4 = 0;
    L5:
        this.startOffsetUs = r4;
    }

    public void setStartTimeUs(long r1) {
        this.decoder.setStartTimeUs(r1);
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public void start(int r3, int r4) {
        this.outputSampleRate = r3;
        this.outputChannelCount = r4;
        this.hasRemaining = true;
        this.decoder.start();
        this.requiredShortsForStartOffset = AudioConversions.usToShorts(getStartOffsetUs(), this.outputSampleRate, this.outputChannelCount);
        this.startOffsetShortsCounter = 0;
    }
}
