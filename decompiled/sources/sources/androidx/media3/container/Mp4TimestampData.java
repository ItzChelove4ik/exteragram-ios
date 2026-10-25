package androidx.media3.container;

import androidx.media3.common.Metadata;
import com.google.common.primitives.Longs;

/* loaded from: classes3.dex */
public final class Mp4TimestampData implements Metadata.Entry {
    public final long creationTimestampSeconds;
    public final long modificationTimestampSeconds;
    public final long timescale;

    public Mp4TimestampData(long r1, long r3, long r5) {
        this.creationTimestampSeconds = r1;
        this.modificationTimestampSeconds = r3;
        this.timescale = r5;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof Mp4TimestampData) == true) goto L8;
        return false;
    L8:
        Mp4TimestampData r82 = (Mp4TimestampData) r8;
        if (this.creationTimestampSeconds == r82.creationTimestampSeconds) goto L11;
    L15:
        return false;
    L11:
        if (this.modificationTimestampSeconds != r82.modificationTimestampSeconds) goto L15;
        if (this.timescale != r82.timescale) goto L15;
        return true;
    }

    public int hashCode() {
        return ((((527 + Longs.hashCode(this.creationTimestampSeconds)) * 31) + Longs.hashCode(this.modificationTimestampSeconds)) * 31) + Longs.hashCode(this.timescale);
    }

    public String toString() {
        return "Mp4Timestamp: creation time=" + this.creationTimestampSeconds + ", modification time=" + this.modificationTimestampSeconds + ", timescale=" + this.timescale;
    }
}
