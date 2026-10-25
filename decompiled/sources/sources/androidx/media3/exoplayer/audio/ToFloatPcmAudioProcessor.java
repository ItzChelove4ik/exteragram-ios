package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import kotlin.UByte;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class ToFloatPcmAudioProcessor extends BaseAudioProcessor {
    private static final int FLOAT_NAN_AS_INT = 0;

    static {
        FLOAT_NAN_AS_INT = Float.floatToIntBits(Float.NaN);
    }

    public ToFloatPcmAudioProcessor() {
    }

    private static void writePcm32BitFloat(int r4, ByteBuffer r5) {
        int r42 = Float.floatToIntBits((float) (r4 * 4.656612875245797E-10d));
        if (r42 != FLOAT_NAN_AS_INT) goto L5;
        r42 = Float.floatToIntBits(0.0f);
    L5:
        r5.putInt(r42);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat r3) {
        int r2 = r3.encoding;
        if (Util.isEncodingHighResolutionPcm(r2) == false) goto L11;
        if (r2 == 4) goto L9;
        return new AudioProcessor.AudioFormat(r3.sampleRate, r3.channelCount, 4);
    L9:
        return AudioProcessor.AudioFormat.NOT_SET;
    L11:
        throw new AudioProcessor.UnhandledAudioFormatException(r3);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer r6) {
        int r0 = r6.position();
        int r1 = r6.limit();
        int r2 = r1 - r0;
        int r3 = this.inputAudioFormat.encoding;
        if (r3 != 21) goto L5;
        ByteBuffer r5 = replaceOutputBuffer((r2 / 3) * 4);
    L22:
        if (r0 >= r1) goto L24;
        writePcm32BitFloat((((r6.get(r0) & UByte.MAX_VALUE) << 8) | ((r6.get(r0 + 1) & UByte.MAX_VALUE) << 16)) | ((r6.get(r0 + 2) & UByte.MAX_VALUE) << 24), r5);
        r0 = r0 + 3;
    L24:
        r6.position(r6.limit());
        r5.flip();
        return;
    L5:
        if (r3 != 22) goto L7;
        r5 = replaceOutputBuffer(r2);
    L19:
        if (r0 >= r1) goto L24;
        writePcm32BitFloat((((r6.get(r0) & UByte.MAX_VALUE) | ((r6.get(r0 + 1) & UByte.MAX_VALUE) << 8)) | ((r6.get(r0 + 2) & UByte.MAX_VALUE) << 16)) | ((r6.get(r0 + 3) & UByte.MAX_VALUE) << 24), r5);
        r0 = r0 + 4;
        goto L19
    L7:
        if (r3 != 1342177280) goto L9;
        r5 = replaceOutputBuffer((r2 / 3) * 4);
    L16:
        if (r0 >= r1) goto L24;
        writePcm32BitFloat((((r6.get(r0 + 2) & UByte.MAX_VALUE) << 8) | ((r6.get(r0 + 1) & UByte.MAX_VALUE) << 16)) | ((r6.get(r0) & UByte.MAX_VALUE) << 24), r5);
        r0 = r0 + 3;
        goto L16
    L9:
        if (r3 != 1610612736) goto L13;
        r5 = replaceOutputBuffer(r2);
    L11:
        if (r0 >= r1) goto L24;
        writePcm32BitFloat((((r6.get(r0 + 3) & UByte.MAX_VALUE) | ((r6.get(r0 + 2) & UByte.MAX_VALUE) << 8)) | ((r6.get(r0 + 1) & UByte.MAX_VALUE) << 16)) | ((r6.get(r0) & UByte.MAX_VALUE) << 24), r5);
        r0 = r0 + 4;
        goto L11
    L13:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }
}
