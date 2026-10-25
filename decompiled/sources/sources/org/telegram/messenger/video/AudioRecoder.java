package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.messenger.video.audio_input.AudioInput;

/* loaded from: classes6.dex */
public class AudioRecoder {
    private static final int BYTES_PER_SHORT = 2;
    private final int DEFAULT_BIT_RATE;
    private final int DEFAULT_CHANNEL_COUNT;
    private final int DEFAULT_SAMPLE_RATE;
    private final int TIMEOUT_USEC;
    ArrayList<AudioInput> audioInputs;
    private int channelCount;
    private boolean decoderDone;
    private final MediaCodec encoder;
    private boolean encoderDone;
    private ByteBuffer[] encoderInputBuffers;
    private boolean encoderInputDone;
    private long encoderInputPresentationTimeUs;
    private final MediaCodec.BufferInfo encoderOutputBufferInfo;
    private ByteBuffer[] encoderOutputBuffers;
    private boolean extractorDone;
    public final MediaFormat format;
    AudioInput mainInput;
    private int pendingAudioDecoderOutputBufferIndex;
    private int sampleRate;
    private long totalDurationUs;

    public AudioRecoder(ArrayList<AudioInput> r7, long r8) {
        this.TIMEOUT_USEC = 2500;
        this.DEFAULT_SAMPLE_RATE = 44100;
        this.DEFAULT_BIT_RATE = 128000;
        this.DEFAULT_CHANNEL_COUNT = 2;
        this.encoderOutputBufferInfo = new MediaCodec.BufferInfo();
        int r3 = 0;
        this.extractorDone = false;
        this.decoderDone = false;
        this.encoderInputDone = false;
        this.pendingAudioDecoderOutputBufferIndex = -1;
        this.sampleRate = 44100;
        this.channelCount = 2;
        this.encoderInputPresentationTimeUs = 0;
        this.audioInputs = r7;
        this.totalDurationUs = r8;
        this.mainInput = r7.get(0);
        int r82 = 0;
    L4:
        if (r82 >= r7.size()) goto L9;
        if (r7.get(r82).getSampleRate() <= this.sampleRate) goto L8;
        this.sampleRate = r7.get(r82).getSampleRate();
    L8:
        r82 = r82 + 1;
        goto L4
    L9:
        MediaCodec r9 = MediaCodec.createEncoderByType(MediaController.AUDIO_MIME_TYPE);
        this.encoder = r9;
        MediaFormat r83 = MediaFormat.createAudioFormat(MediaController.AUDIO_MIME_TYPE, this.sampleRate, this.channelCount);
        this.format = r83;
        r83.setInteger("bitrate", 128000);
        r9.configure(r83, null, null, 1);
        r9.start();
        this.encoderInputBuffers = r9.getInputBuffers();
        this.encoderOutputBuffers = r9.getOutputBuffers();
    L11:
        if (r3 >= r7.size()) goto L13;
        r7.get(r3).start(this.sampleRate, this.channelCount);
        r3 = r3 + 1;
        goto L11
    }

    private boolean isInputAvailable() {
        if (this.encoderInputPresentationTimeUs <= this.totalDurationUs) goto L7;
        return false;
    L7:
        return this.mainInput.hasRemaining();
    }

    private void mix(ShortBuffer r9) {
        int r0 = r9.remaining();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        if (isInputAvailable() == false) goto L29;
        int r3 = 0;
        boolean r4 = false;
        short r5 = 0;
    L9:
        if (r3 >= this.audioInputs.size()) goto L17;
        if (isInputAvailable() == false) goto L17;
        if (this.audioInputs.get(r3).hasRemaining() == false) goto L16;
        r5 = (short) (r5 + (((short) (r6.getNext() * r6.getVolume())) / this.audioInputs.size()));
        r4 = true;
    L16:
        r3 = r3 + 1;
    L17:
        if (r4 == false) goto L19;
        r9.put(r5);
    L19:
        r2 = r2 + 1;
        goto L3
    L29:
        return;
    }

    public void release() {
        this.encoder.stop();     // Catch: Exception -> L8
        int r0 = 0;
    L3:
        if (r0 >= this.audioInputs.size()) goto L7;
        this.audioInputs.get(r0).release();     // Catch: Exception -> L8
        r0 = r0 + 1;
        goto L3
    L7:
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public boolean step(MediaCodecVideoConvertor.Muxer r12, int r13) {
        if (this.encoderInputDone == true) goto L11;
        int r5 = this.encoder.dequeueInputBuffer(2500);
        if (r5 < 0) goto L11;
        boolean r0 = isInputAvailable();
        MediaCodec r4 = this.encoder;
        if (r0 == false) goto L9;
        ShortBuffer r02 = r4.getInputBuffer(r5).asShortBuffer();
        mix(r02);
        this.encoder.queueInputBuffer(r5, 0, r02.position() * 2, this.encoderInputPresentationTimeUs, 1);
        this.encoderInputPresentationTimeUs += AudioConversions.shortsToUs(r02.position(), this.sampleRate, this.channelCount);
        goto L11
    L9:
        r4.queueInputBuffer(r5, 0, 0, 0, 4);
        this.encoderInputDone = true;
    L11:
        if (this.encoderDone == true) goto L35;
        int r03 = this.encoder.dequeueOutputBuffer(this.encoderOutputBufferInfo, 2500);
        if (r03 != (-1)) goto L17;
        return this.encoderDone;
    L17:
        if (r03 != (-3)) goto L20;
        this.encoderOutputBuffers = this.encoder.getOutputBuffers();
    L20:
        if (r03 == (-2)) goto L22;
        ByteBuffer r2 = this.encoderOutputBuffers[r03];
        MediaCodec.BufferInfo r3 = this.encoderOutputBufferInfo;
        if ((r3.flags & 2) == 0) goto L28;
        this.encoder.releaseOutputBuffer(r03, false);
        return this.encoderDone;
    L28:
        if (r3.size == 0) goto L31;
        r12.writeSampleData(r13, r2, r3, false);
    L31:
        if ((this.encoderOutputBufferInfo.flags & 4) == 0) goto L33;
        this.encoderDone = true;
    L33:
        this.encoder.releaseOutputBuffer(r03, false);
        goto L35
    L22:
        return this.encoderDone;
    L35:
        return this.encoderDone;
    }
}
