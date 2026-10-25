package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class GeobFrame extends Id3Frame {
    public final byte[] data;
    public final String description;
    public final String filename;
    public final String mimeType;

    public GeobFrame(String r2, String r3, String r4, byte[] r5) {
        super("GEOB");
        this.mimeType = r2;
        this.filename = r3;
        this.description = r4;
        this.data = r5;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L19:
        return false;
    L8:
        if (GeobFrame.class != r5.getClass()) goto L19;
        GeobFrame r52 = (GeobFrame) r5;
        if (Objects.equals(this.mimeType, r52.mimeType) == false) goto L19;
        if (Objects.equals(this.filename, r52.filename) == false) goto L19;
        if (Objects.equals(this.description, r52.description) == false) goto L19;
        if (Arrays.equals(this.data, r52.data) == false) goto L19;
        return true;
    }

    public int hashCode() {
        String r0 = this.mimeType;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r2 = (527 + r02) * 31;
        String r03 = this.filename;
        if (r03 == null) goto L9;
        int r04 = r03.hashCode();
    L10:
        int r22 = (r2 + r04) * 31;
        String r05 = this.description;
        if (r05 == null) goto L14;
        r1 = r05.hashCode();
    L14:
        return ((r22 + r1) * 31) + Arrays.hashCode(this.data);
    L9:
        r04 = 0;
        goto L10
    L5:
        r02 = 0;
        goto L6
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": mimeType=" + this.mimeType + ", filename=" + this.filename + ", description=" + this.description;
    }
}
