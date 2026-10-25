package androidx.media3.extractor.avi;

import androidx.media3.common.util.ParsableByteArray;

/* loaded from: classes4.dex */
final class StreamNameChunk implements AviChunk {
    public final String name;

    private StreamNameChunk(String r1) {
        this.name = r1;
    }

    public static StreamNameChunk parseFrom(ParsableByteArray r2) {
        return new StreamNameChunk(r2.readString(r2.bytesLeft()));
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return 1852994675;
    }
}
