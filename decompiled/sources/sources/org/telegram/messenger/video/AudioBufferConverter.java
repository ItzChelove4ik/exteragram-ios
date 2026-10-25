package org.telegram.messenger.video;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import org.telegram.messenger.video.remix.AudioRemixer;
import org.telegram.messenger.video.remix.DefaultAudioRemixer;
import org.telegram.messenger.video.resample.AudioResampler;
import org.telegram.messenger.video.resample.DefaultAudioResampler;

/* loaded from: classes6.dex */
public class AudioBufferConverter {
    private static final int BYTES_PER_SHORT = 2;
    private static final String TAG = "AudioBufferConverter";
    private final AudioRemixer mRemixer;
    private final AudioResampler mResampler;

    static {
    }

    public AudioBufferConverter() {
        this.mRemixer = new DefaultAudioRemixer();
        this.mResampler = new DefaultAudioResampler();
    }

    private void checkChannels(int r3, int r4) {
        if (r3 != 6) goto L8;
        if (r4 == 1) goto L18;
        if (r4 != 2) goto L8;
        return;
    L18:
        return;
    L8:
        if (r3 == 1) goto L13;
        if (r3 == 2) goto L13;
        throw new UnsupportedOperationException("Input channel count (" + r3 + ") not supported.");
    L13:
        if (r4 == 1) goto L19;
        if (r4 != 2) goto L17;
        return;
    L17:
        throw new UnsupportedOperationException("Output channel count (" + r4 + ") not supported.");
    }

    private ShortBuffer createBuffer(int r2) {
        ShortBuffer r1 = ByteBuffer.allocateDirect(r2 * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        r1.clear();
        r1.limit(r2);
        return r1;
    }

    public int calculateRequiredOutputSize(int r1, int r2, int r3, int r4, int r5) {
        checkChannels(r3, r5);
        return (int) Math.ceil((this.mRemixer.getRemixedSize(r1, r3, r5) * r4) / r2);
    }

    public ShortBuffer convert(ShortBuffer r5, int r6, int r7, int r8, int r9) {
        checkChannels(r7, r9);
        int r0 = this.mRemixer.getRemixedSize(r5.remaining(), r7, r9);
        ShortBuffer r52 = createBuffer(r0);
        this.mRemixer.remix(r5, r7, r52, r9);
        r52.rewind();
        ShortBuffer r72 = createBuffer(((int) Math.ceil((r0 * r8) / r6)) + 10);
        this.mResampler.resample(r52, r6, r72, r8, r9);
        r72.limit(r72.position());
        r72.rewind();
        return r72;
    }
}
