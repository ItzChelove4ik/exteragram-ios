package androidx.media3.common.audio;

import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/* loaded from: classes3.dex */
public interface AudioProcessor {
    public static final ByteBuffer EMPTY_BUFFER = null;

    public static final class AudioFormat {
        public static final AudioFormat NOT_SET = null;
        public final int bytesPerFrame;
        public final int channelCount;
        public final int encoding;
        public final int sampleRate;

        static {
            NOT_SET = new AudioFormat(-1, -1, -1);
        }

        public AudioFormat(int r1, int r2, int r3) {
            this.sampleRate = r1;
            this.channelCount = r2;
            this.encoding = r3;
            if (Util.isEncodingLinearPcm(r3) == false) goto L5;
            int r12 = Util.getPcmFrameSize(r3, r2);
        L6:
            this.bytesPerFrame = r12;
            return;
        L5:
            r12 = -1;
            goto L6
        }

        public AudioFormat(Format r3) {
            this(r3.sampleRate, r3.channelCount, r3.pcmEncoding);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof AudioFormat) == true) goto L8;
            return false;
        L8:
            AudioFormat r52 = (AudioFormat) r5;
            if (this.sampleRate == r52.sampleRate) goto L11;
        L15:
            return false;
        L11:
            if (this.channelCount != r52.channelCount) goto L15;
            if (this.encoding != r52.encoding) goto L15;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.sampleRate), Integer.valueOf(this.channelCount), Integer.valueOf(this.encoding)});
        }

        public String toString() {
            return "AudioFormat[sampleRate=" + this.sampleRate + ", channelCount=" + this.channelCount + ", encoding=" + this.encoding + ']';
        }
    }

    public static final class UnhandledAudioFormatException extends Exception {
        public final AudioFormat inputAudioFormat;

        public UnhandledAudioFormatException(AudioFormat r2) {
            this("Unhandled input format:", r2);
        }

        public UnhandledAudioFormatException(String r2, AudioFormat r3) {
            super(r2 + " " + r3);
            this.inputAudioFormat = r3;
        }
    }

    static {
        EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
    }

    AudioFormat configure(AudioFormat r1);

    void flush();

    ByteBuffer getOutput();

    boolean isActive();

    boolean isEnded();

    void queueEndOfStream();

    void queueInput(ByteBuffer r1);

    void reset();
}
