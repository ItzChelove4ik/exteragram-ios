package androidx.media3.extractor;

/* loaded from: classes4.dex */
public final class SeekPoint {
    public static final SeekPoint START = null;
    public final long position;
    public final long timeUs;

    static {
        START = new SeekPoint(0, 0);
    }

    public SeekPoint(long r1, long r3) {
        this.timeUs = r1;
        this.position = r3;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L15:
        return false;
    L8:
        if (SeekPoint.class != r7.getClass()) goto L15;
        SeekPoint r72 = (SeekPoint) r7;
        if (this.timeUs != r72.timeUs) goto L15;
        if (this.position != r72.position) goto L15;
        return true;
    }

    public int hashCode() {
        return (((int) this.timeUs) * 31) + ((int) this.position);
    }

    public String toString() {
        return "[timeUs=" + this.timeUs + ", position=" + this.position + "]";
    }
}
