package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public abstract class BaseAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    protected AudioProcessor.AudioFormat inputAudioFormat;
    private boolean inputEnded;
    protected AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer outputBuffer;
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;

    public BaseAudioProcessor() {
        ByteBuffer r0 = AudioProcessor.EMPTY_BUFFER;
        this.buffer = r0;
        this.outputBuffer = r0;
        AudioProcessor.AudioFormat r02 = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = r02;
        this.pendingOutputAudioFormat = r02;
        this.inputAudioFormat = r02;
        this.outputAudioFormat = r02;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat r1) {
        this.pendingInputAudioFormat = r1;
        this.pendingOutputAudioFormat = onConfigure(r1);
        if (isActive() == false) goto L7;
        return this.pendingOutputAudioFormat;
    L7:
        return AudioProcessor.AudioFormat.NOT_SET;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void flush() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
        this.inputAudioFormat = this.pendingInputAudioFormat;
        this.outputAudioFormat = this.pendingOutputAudioFormat;
        onFlush();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        ByteBuffer r0 = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return r0;
    }

    public final boolean hasPendingOutput() {
        return this.outputBuffer.hasRemaining();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        if (this.pendingOutputAudioFormat == AudioProcessor.AudioFormat.NOT_SET) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        if (this.inputEnded == true) goto L5;
        return false;
    L5:
        if (this.outputBuffer != AudioProcessor.EMPTY_BUFFER) goto L10;
        return true;
    L10:
        return false;
    }

    public abstract AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r1);

    public void onFlush() {
    }

    public void onQueueEndOfStream() {
    }

    public void onReset() {
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueEndOfStream() {
        this.inputEnded = true;
        onQueueEndOfStream();
    }

    public final ByteBuffer replaceOutputBuffer(int r2) {
        if (this.buffer.capacity() >= r2) goto L5;
        this.buffer = ByteBuffer.allocateDirect(r2).order(ByteOrder.nativeOrder());
    L6:
        ByteBuffer r22 = this.buffer;
        this.outputBuffer = r22;
        return r22;
    L5:
        this.buffer.clear();
        goto L6
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void reset() {
        ByteBuffer r0 = AudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = r0;
        this.inputEnded = false;
        this.buffer = r0;
        AudioProcessor.AudioFormat r02 = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = r02;
        this.pendingOutputAudioFormat = r02;
        this.inputAudioFormat = r02;
        this.outputAudioFormat = r02;
        onReset();
    }
}
