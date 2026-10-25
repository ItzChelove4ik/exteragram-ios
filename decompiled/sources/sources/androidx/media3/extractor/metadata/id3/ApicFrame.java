package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.MediaMetadata;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ApicFrame extends Id3Frame {
    public final String description;
    public final String mimeType;
    public final byte[] pictureData;
    public final int pictureType;

    public ApicFrame(String r2, String r3, int r4, byte[] r5) {
        super("APIC");
        this.mimeType = r2;
        this.description = r3;
        this.pictureType = r4;
        this.pictureData = r5;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L19:
        return false;
    L8:
        if (ApicFrame.class != r5.getClass()) goto L19;
        ApicFrame r52 = (ApicFrame) r5;
        if (this.pictureType != r52.pictureType) goto L19;
        if (Objects.equals(this.mimeType, r52.mimeType) == false) goto L19;
        if (Objects.equals(this.description, r52.description) == false) goto L19;
        if (Arrays.equals(this.pictureData, r52.pictureData) == false) goto L19;
        return true;
    }

    public int hashCode() {
        int r0 = (527 + this.pictureType) * 31;
        String r1 = this.mimeType;
        int r2 = 0;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L6:
        int r02 = (r0 + r12) * 31;
        String r13 = this.description;
        if (r13 == null) goto L10;
        r2 = r13.hashCode();
    L10:
        return ((r02 + r2) * 31) + Arrays.hashCode(this.pictureData);
    L5:
        r12 = 0;
        goto L6
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder r2) {
        r2.maybeSetArtworkData(this.pictureData, this.pictureType);
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": mimeType=" + this.mimeType + ", description=" + this.description;
    }
}
