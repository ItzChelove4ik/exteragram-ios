package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* loaded from: classes3.dex */
public final class SonicAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    private AudioProcessor.AudioFormat inputAudioFormat;
    private long inputBytes;
    private boolean inputEnded;
    private AudioProcessor.AudioFormat outputAudioFormat;
    private ByteBuffer outputBuffer;
    private long outputBytes;
    private AudioProcessor.AudioFormat pendingInputAudioFormat;
    private AudioProcessor.AudioFormat pendingOutputAudioFormat;
    private int pendingOutputSampleRate;
    private boolean pendingSonicRecreation;
    private float pitch;
    private ShortBuffer shortBuffer;
    private final boolean shouldBeActiveWithDefaultParameters;
    private Sonic sonic;
    private float speed;

    public SonicAudioProcessor() {
        this(false);
    }

    public SonicAudioProcessor(boolean r3) {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat r0 = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = r0;
        this.pendingOutputAudioFormat = r0;
        this.inputAudioFormat = r0;
        this.outputAudioFormat = r0;
        ByteBuffer r02 = AudioProcessor.EMPTY_BUFFER;
        this.buffer = r02;
        this.shortBuffer = r02.asShortBuffer();
        this.outputBuffer = r02;
        this.pendingOutputSampleRate = -1;
        this.shouldBeActiveWithDefaultParameters = r3;
    }

    private boolean areParametersSetToDefaultValues() {
        if (Math.abs(this.speed - 1.0f) < 1.0E-4f) goto L5;
        return false;
    L5:
        if (Math.abs(this.pitch - 1.0f) < 1.0E-4f) goto L7;
        return false;
    L7:
        if (this.pendingOutputAudioFormat.sampleRate != this.pendingInputAudioFormat.sampleRate) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat r4) {
        if (r4.encoding != 2) goto L10;
        int r0 = this.pendingOutputSampleRate;
        if (r0 != (-1)) goto L7;
        r0 = r4.sampleRate;
    L7:
        this.pendingInputAudioFormat = r4;
        AudioProcessor.AudioFormat r2 = new AudioProcessor.AudioFormat(r0, r4.channelCount, 2);
        this.pendingOutputAudioFormat = r2;
        this.pendingSonicRecreation = true;
        return r2;
    L10:
        throw new AudioProcessor.UnhandledAudioFormatException(r4);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void flush() {
        if (isActive() == false) goto L10;
        AudioProcessor.AudioFormat r0 = this.pendingInputAudioFormat;
        this.inputAudioFormat = r0;
        AudioProcessor.AudioFormat r1 = this.pendingOutputAudioFormat;
        this.outputAudioFormat = r1;
        if (this.pendingSonicRecreation == false) goto L7;
        this.sonic = new Sonic(r0.sampleRate, r0.channelCount, this.speed, this.pitch, r1.sampleRate);
        goto L10
    L7:
        Sonic r02 = this.sonic;
        if (r02 == null) goto L10;
        r02.flush();
    L10:
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public long getMediaDuration(long r11) {
        if (this.outputBytes < 1024) goto L11;
        long r6 = this.inputBytes - ((Sonic) Assertions.checkNotNull(this.sonic)).getPendingInputBytes();
        int r0 = this.outputAudioFormat.sampleRate;
        int r1 = this.inputAudioFormat.sampleRate;
        long r8 = this.outputBytes;
        if (r0 != r1) goto L9;
        return Util.scaleLargeTimestamp(r11, r6, r8);
    L9:
        return Util.scaleLargeTimestamp(r11, r6 * r0, r8 * r1);
    L11:
        return (long) (this.speed * r11);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        Sonic r0 = this.sonic;
        if (r0 == null) goto L11;
        int r1 = r0.getOutputSize();
        if (r1 <= 0) goto L11;
        if (this.buffer.capacity() >= r1) goto L9;
        ByteBuffer r2 = ByteBuffer.allocateDirect(r1).order(ByteOrder.nativeOrder());
        this.buffer = r2;
        this.shortBuffer = r2.asShortBuffer();
    L10:
        r0.getOutput(this.shortBuffer);
        this.outputBytes += r1;
        this.buffer.limit(r1);
        this.outputBuffer = this.buffer;
        goto L11
    L9:
        this.buffer.clear();
        this.shortBuffer.clear();
    L11:
        ByteBuffer r02 = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return r02;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        if (this.pendingOutputAudioFormat.sampleRate != (-1)) goto L5;
        return false;
    L5:
        if (this.shouldBeActiveWithDefaultParameters == false) goto L7;
        return true;
    L7:
        if (areParametersSetToDefaultValues() == true) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        if (this.inputEnded == false) goto L10;
        Sonic r1 = this.sonic;
        if (r1 != null) goto L7;
        return true;
    L7:
        if (r1.getOutputSize() != 0) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueEndOfStream() {
        Sonic r0 = this.sonic;
        if (r0 == null) goto L5;
        r0.queueEndOfStream();
    L5:
        this.inputEnded = true;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r8) {
        if (r8.hasRemaining() == true) goto L5;
        return;
    L5:
        Sonic r0 = (Sonic) Assertions.checkNotNull(this.sonic);
        ShortBuffer r1 = r8.asShortBuffer();
        int r2 = r8.remaining();
        this.inputBytes += r2;
        r0.queueInput(r1);
        r8.position(r8.position() + r2);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void reset() {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat r0 = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = r0;
        this.pendingOutputAudioFormat = r0;
        this.inputAudioFormat = r0;
        this.outputAudioFormat = r0;
        ByteBuffer r02 = AudioProcessor.EMPTY_BUFFER;
        this.buffer = r02;
        this.shortBuffer = r02.asShortBuffer();
        this.outputBuffer = r02;
        this.pendingOutputSampleRate = -1;
        this.pendingSonicRecreation = false;
        this.sonic = null;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public void setPitch(float r3) {
        if (r3 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (this.pitch == r3) goto L10;
        this.pitch = r3;
        this.pendingSonicRecreation = true;
        return;
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setSpeed(float r3) {
        if (r3 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (this.speed == r3) goto L10;
        this.speed = r3;
        this.pendingSonicRecreation = true;
        return;
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }
}
