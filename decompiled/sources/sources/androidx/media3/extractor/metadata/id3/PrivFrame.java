package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class PrivFrame extends Id3Frame {
    public final String owner;
    public final byte[] privateData;

    public PrivFrame(String r2, byte[] r3) {
        super("PRIV");
        this.owner = r2;
        this.privateData = r3;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (PrivFrame.class != r5.getClass()) goto L15;
        PrivFrame r52 = (PrivFrame) r5;
        if (Objects.equals(this.owner, r52.owner) == false) goto L15;
        if (Arrays.equals(this.privateData, r52.privateData) == false) goto L15;
        return true;
    }

    public int hashCode() {
        String r0 = this.owner;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L7:
        return ((527 + r02) * 31) + Arrays.hashCode(this.privateData);
    L5:
        r02 = 0;
        goto L7
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": owner=" + this.owner;
    }
}
