package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class BinaryFrame extends Id3Frame {
    public final byte[] data;

    public BinaryFrame(String r1, byte[] r2) {
        super(r1);
        this.data = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (BinaryFrame.class != r5.getClass()) goto L15;
        BinaryFrame r52 = (BinaryFrame) r5;
        if (this.id.equals(r52.id) == false) goto L15;
        if (Arrays.equals(this.data, r52.data) == false) goto L15;
        return true;
    }

    public int hashCode() {
        return ((527 + this.id.hashCode()) * 31) + Arrays.hashCode(this.data);
    }
}
