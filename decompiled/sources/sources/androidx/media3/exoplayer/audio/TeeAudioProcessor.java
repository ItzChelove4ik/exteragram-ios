package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class TeeAudioProcessor extends BaseAudioProcessor {
    private final AudioBufferSink audioBufferSink;

    public interface AudioBufferSink {
        void flush(int r1, int r2, int r3);

        void handleBuffer(ByteBuffer r1);
    }

    public TeeAudioProcessor(AudioBufferSink r1) {
        this.audioBufferSink = (AudioBufferSink) Assertions.checkNotNull(r1);
    }

    private void flushSinkIfActive() {
        if (isActive() == false) goto L6;
        AudioBufferSink r0 = this.audioBufferSink;
        AudioProcessor.AudioFormat r3 = this.inputAudioFormat;
        r0.flush(r3.sampleRate, r3.channelCount, r3.encoding);
        return;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r1) {
        return r1;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        flushSinkIfActive();
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        flushSinkIfActive();
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        flushSinkIfActive();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r4) {
        int r0 = r4.remaining();
        if (r0 != 0) goto L5;
        return;
    L5:
        this.audioBufferSink.handleBuffer(Util.createReadOnlyByteBuffer(r4));
        replaceOutputBuffer(r0).put(r4).flip();
    }
}
