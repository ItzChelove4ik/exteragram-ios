package androidx.media3.extractor.avi;

import androidx.media3.common.util.ParsableByteArray;

/* loaded from: classes4.dex */
final class AviMainHeaderChunk implements AviChunk {
    public final int flags;
    public final int frameDurationUs;
    public final int streams;
    public final int totalFrames;

    private AviMainHeaderChunk(int r1, int r2, int r3, int r4) {
        this.frameDurationUs = r1;
        this.flags = r2;
        this.totalFrames = r3;
        this.streams = r4;
    }

    public static AviMainHeaderChunk parseFrom(ParsableByteArray r5) {
        int r0 = r5.readLittleEndianInt();
        r5.skipBytes(8);
        int r1 = r5.readLittleEndianInt();
        int r2 = r5.readLittleEndianInt();
        r5.skipBytes(4);
        int r3 = r5.readLittleEndianInt();
        r5.skipBytes(12);
        return new AviMainHeaderChunk(r0, r1, r2, r3);
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return 1751742049;
    }

    public boolean hasIndex() {
        if ((this.flags & 16) != 16) goto L6;
        return true;
    L6:
        return false;
    }
}
