package androidx.media3.extractor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class OpusUtil {
    public static List<byte[]> buildInitializationData(byte[] r6) {
        long r0 = sampleCountToNanoseconds(getPreSkipSamples(r6));
        long r2 = sampleCountToNanoseconds(3840);
        ArrayList r4 = new ArrayList(3);
        r4.add(r6);
        r4.add(buildNativeOrderByteArray(r0));
        r4.add(buildNativeOrderByteArray(r2));
        return r4;
    }

    private static byte[] buildNativeOrderByteArray(long r2) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(r2).array();
    }

    public static int getChannelCount(byte[] r1) {
        return r1[9] & UByte.MAX_VALUE;
    }

    private static long getPacketDurationUs(byte r5, byte r6) {
        int r0 = r5 & UByte.MAX_VALUE;
        int r52 = r5 & 3;
        if (r52 == 0) goto L8;
        int r3 = 2;
        if (r52 == 1) goto L9;
        if (r52 == 2) goto L9;
        r3 = r6 & 63;
    L9:
        int r53 = r0 >> 3;
        int r62 = r53 & 3;
        if (r53 < 16) goto L13;
        int r54 = 2500 << r62;
    L19:
        return r3 * r54;
    L13:
        if (r53 < 12) goto L15;
        r54 = 10000 << (r53 & 1);
        goto L19
    L15:
        if (r62 != 3) goto L17;
        r54 = 60000;
        goto L19
    L17:
        r54 = 10000 << r62;
        goto L19
    L8:
        r3 = 1;
        goto L9
    }

    public static long getPacketDurationUs(byte[] r4) {
        byte r0 = 0;
        byte r1 = r4[0];
        if (r4.length <= 1) goto L6;
        r0 = r4[1];
    L6:
        return getPacketDurationUs(r1, r0);
    }

    public static int getPreSkipSamples(byte[] r2) {
        int r0 = (r2[11] & UByte.MAX_VALUE) << 8;
        return (r2[10] & UByte.MAX_VALUE) | r0;
    }

    public static boolean needToDecodeOpusFrame(long r2, long r4) {
        if ((r2 - r4) > (sampleCountToNanoseconds(3840) / 1000)) goto L6;
        return true;
    L6:
        return false;
    }

    public static int parseOggPacketAudioSampleCount(ByteBuffer r4) {
        int r0 = parseOggPacketForPreAudioSampleByteCount(r4);
        int r1 = (r4.get(r0 + 26) + 27) + r0;
        byte r02 = r4.get(r1);
        if ((r4.limit() - r1) <= 1) goto L5;
        byte r42 = r4.get(r1 + 1);
    L7:
        return (int) ((getPacketDurationUs(r02, r42) * 48000) / 1000000);
    L5:
        r42 = 0;
        goto L7
    }

    public static int parseOggPacketForPreAudioSampleByteCount(ByteBuffer r6) {
        int r1 = 0;
        if ((r6.get(5) & 2) != 0) goto L5;
        return 0;
    L5:
        byte r0 = r6.get(26);
        int r2 = 28;
        int r3 = 0;
        int r4 = 28;
    L6:
        if (r3 >= r0) goto L8;
        r4 = r4 + r6.get(r3 + 27);
        r3 = r3 + 1;
        goto L6
    L8:
        byte r02 = r6.get(r4 + 26);
    L9:
        if (r1 >= r02) goto L12;
        r2 = r2 + r6.get((r4 + 27) + r1);
        r1 = r1 + 1;
        goto L9
    L12:
        return r4 + r2;
    }

    public static int parsePacketAudioSampleCount(ByteBuffer r4) {
        byte r0 = 0;
        byte r1 = r4.get(0);
        if (r4.limit() <= 1) goto L6;
        r0 = r4.get(1);
    L6:
        return (int) ((getPacketDurationUs(r1, r0) * 48000) / 1000000);
    }

    private static long sampleCountToNanoseconds(long r2) {
        return (r2 * 1000000000) / 48000;
    }
}
