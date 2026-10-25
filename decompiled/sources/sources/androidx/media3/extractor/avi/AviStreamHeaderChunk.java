package androidx.media3.extractor.avi;

import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
final class AviStreamHeaderChunk implements AviChunk {
    public final int initialFrames;
    public final int length;
    public final int rate;
    public final int sampleSize;
    public final int scale;
    public final int streamType;
    public final int suggestedBufferSize;

    private AviStreamHeaderChunk(int r1, int r2, int r3, int r4, int r5, int r6, int r7) {
        this.streamType = r1;
        this.initialFrames = r2;
        this.scale = r3;
        this.rate = r4;
        this.length = r5;
        this.suggestedBufferSize = r6;
        this.sampleSize = r7;
    }

    public static AviStreamHeaderChunk parseFrom(ParsableByteArray r8) {
        int r1 = r8.readLittleEndianInt();
        r8.skipBytes(12);
        int r2 = r8.readLittleEndianInt();
        int r3 = r8.readLittleEndianInt();
        int r4 = r8.readLittleEndianInt();
        r8.skipBytes(4);
        int r5 = r8.readLittleEndianInt();
        int r6 = r8.readLittleEndianInt();
        r8.skipBytes(4);
        return new AviStreamHeaderChunk(r1, r2, r3, r4, r5, r6, r8.readLittleEndianInt());
    }

    public long getDurationUs() {
        return Util.scaleLargeTimestamp(this.length, this.scale * 1000000, this.rate);
    }

    public int getTrackType() {
        int r0 = this.streamType;
        if (r0 != 1935960438) goto L5;
        return 2;
    L5:
        if (r0 != 1935963489) goto L7;
        return 1;
    L7:
        if (r0 == 1937012852) goto L10;
        Log.w("AviStreamHeaderChunk", "Found unsupported streamType fourCC: " + Integer.toHexString(this.streamType));
        return -1;
    L10:
        return 3;
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return 1752331379;
    }
}
