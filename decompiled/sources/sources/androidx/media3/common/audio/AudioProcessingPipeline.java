package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class AudioProcessingPipeline {
    private final List<AudioProcessor> activeAudioProcessors;
    private final ImmutableList<AudioProcessor> audioProcessors;
    private boolean inputEnded;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer[] outputBuffers;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;

    public AudioProcessingPipeline(ImmutableList<AudioProcessor> r2) {
        this.audioProcessors = r2;
        this.activeAudioProcessors = new ArrayList();
        this.outputBuffers = new ByteBuffer[0];
        AudioProcessor.AudioFormat r0 = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = r0;
        this.pendingOutputAudioFormat = r0;
        this.inputEnded = false;
    }

    private int getFinalOutputBufferIndex() {
        return this.outputBuffers.length - 1;
    }

    private void processData(ByteBuffer r10) {
        boolean r1 = true;
    L3:
        if (r1 == false) goto L33;
        boolean r2 = false;
        int r3 = 0;
    L6:
        if (r3 > getFinalOutputBufferIndex()) goto L32;
        if (this.outputBuffers[r3].hasRemaining() == true) goto L31;
        AudioProcessor r4 = this.activeAudioProcessors.get(r3);
        if (r4.isEnded() == true) goto L13;
        if (r3 <= 0) goto L20;
        ByteBuffer r5 = this.outputBuffers[r3 - 1];
    L23:
        long r6 = r5.remaining();
        r4.queueInput(r5);
        this.outputBuffers[r3] = r4.getOutput();
        if ((r6 - r5.remaining()) <= 0) goto L26;
    L29:
        boolean r42 = true;
    L30:
        r2 = r2 | r42;
        goto L31
    L26:
        if (this.outputBuffers[r3].hasRemaining() == true) goto L29;
        r42 = false;
        goto L30
    L20:
        if (r10.hasRemaining() == false) goto L22;
        r5 = r10;
        goto L23
    L22:
        r5 = AudioProcessor.EMPTY_BUFFER;
        goto L23
    L13:
        if (this.outputBuffers[r3].hasRemaining() == true) goto L31;
        if (r3 >= getFinalOutputBufferIndex()) goto L31;
        this.activeAudioProcessors.get(r3 + 1).queueEndOfStream();
    L31:
        r3 = r3 + 1;
        goto L6
    L32:
        r1 = r2;
        goto L3
    }

    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat r4) {
        if (r4.equals(AudioProcessor.AudioFormat.NOT_SET) == true) goto L14;
        int r0 = 0;
    L6:
        if (r0 >= this.audioProcessors.size()) goto L11;
        AudioProcessor r1 = this.audioProcessors.get(r0);
        AudioProcessor.AudioFormat r2 = r1.configure(r4);
        if (r1.isActive() == false) goto L10;
        Assertions.checkState(!r2.equals(AudioProcessor.AudioFormat.NOT_SET));
        r4 = r2;
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        this.pendingOutputAudioFormat = r4;
        return r4;
    L14:
        throw new AudioProcessor.UnhandledAudioFormatException(r4);
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof AudioProcessingPipeline) == true) goto L8;
        return false;
    L8:
        AudioProcessingPipeline r62 = (AudioProcessingPipeline) r6;
        if (this.audioProcessors.size() == r62.audioProcessors.size()) goto L11;
        return false;
    L11:
        int r1 = 0;
    L13:
        if (r1 >= this.audioProcessors.size()) goto L18;
        if (this.audioProcessors.get(r1) != r62.audioProcessors.get(r1)) goto L16;
        r1 = r1 + 1;
        goto L13
    L16:
        return false;
    L18:
        return true;
    }

    public void flush() {
        this.activeAudioProcessors.clear();
        this.outputAudioFormat = this.pendingOutputAudioFormat;
        int r0 = 0;
        this.inputEnded = false;
        int r1 = 0;
    L4:
        if (r1 >= this.audioProcessors.size()) goto L9;
        AudioProcessor r2 = this.audioProcessors.get(r1);
        r2.flush();
        if (r2.isActive() == false) goto L8;
        this.activeAudioProcessors.add(r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        this.outputBuffers = new ByteBuffer[this.activeAudioProcessors.size()];
    L11:
        if (r0 > getFinalOutputBufferIndex()) goto L13;
        this.outputBuffers[r0] = this.activeAudioProcessors.get(r0).getOutput();
        r0 = r0 + 1;
        goto L11
    }

    public ByteBuffer getOutput() {
        if (isOperational() == false) goto L5;
        ByteBuffer r0 = this.outputBuffers[getFinalOutputBufferIndex()];
        if (r0.hasRemaining() == false) goto L9;
        return r0;
    L9:
        processData(AudioProcessor.EMPTY_BUFFER);
        return this.outputBuffers[getFinalOutputBufferIndex()];
    L5:
        return AudioProcessor.EMPTY_BUFFER;
    }

    public int hashCode() {
        return this.audioProcessors.hashCode();
    }

    public boolean isEnded() {
        if (this.inputEnded == true) goto L5;
        return false;
    L5:
        if (this.activeAudioProcessors.get(getFinalOutputBufferIndex()).isEnded() == true) goto L7;
        return false;
    L7:
        if (this.outputBuffers[getFinalOutputBufferIndex()].hasRemaining() == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isOperational() {
        return !this.activeAudioProcessors.isEmpty();
    }

    public void queueEndOfStream() {
        if (isOperational() == true) goto L5;
        return;
    L5:
        if (this.inputEnded == true) goto L10;
        this.inputEnded = true;
        this.activeAudioProcessors.get(0).queueEndOfStream();
        return;
    }

    public void queueInput(ByteBuffer r2) {
        if (isOperational() == true) goto L5;
        return;
    L5:
        if (this.inputEnded == true) goto L10;
        processData(r2);
        return;
    }

    public void reset() {
        int r1 = 0;
    L4:
        if (r1 >= this.audioProcessors.size()) goto L6;
        AudioProcessor r2 = this.audioProcessors.get(r1);
        r2.flush();
        r2.reset();
        r1 = r1 + 1;
        goto L4
    L6:
        this.outputBuffers = new ByteBuffer[0];
        AudioProcessor.AudioFormat r12 = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = r12;
        this.pendingOutputAudioFormat = r12;
        this.inputEnded = false;
    }
}
