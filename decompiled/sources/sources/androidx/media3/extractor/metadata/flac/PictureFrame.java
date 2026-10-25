package androidx.media3.extractor.metadata.flac;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class PictureFrame implements Metadata.Entry {
    public final int colors;
    public final int depth;
    public final String description;
    public final int height;
    public final String mimeType;
    public final byte[] pictureData;
    public final int pictureType;
    public final int width;

    public PictureFrame(int r1, String r2, String r3, int r4, int r5, int r6, int r7, byte[] r8) {
        this.pictureType = r1;
        this.mimeType = r2;
        this.description = r3;
        this.width = r4;
        this.height = r5;
        this.depth = r6;
        this.colors = r7;
        this.pictureData = r8;
    }

    public static PictureFrame fromPictureBlock(ParsableByteArray r10) {
        int r1 = r10.readInt();
        String r2 = MimeTypes.normalizeMimeType(r10.readString(r10.readInt(), StandardCharsets.US_ASCII));
        String r3 = r10.readString(r10.readInt());
        int r4 = r10.readInt();
        int r5 = r10.readInt();
        int r6 = r10.readInt();
        int r7 = r10.readInt();
        int r0 = r10.readInt();
        byte[] r8 = new byte[r0];
        r10.readBytes(r8, 0, r0);
        return new PictureFrame(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L27:
        return false;
    L8:
        if (PictureFrame.class != r5.getClass()) goto L27;
        PictureFrame r52 = (PictureFrame) r5;
        if (this.pictureType != r52.pictureType) goto L27;
        if (this.mimeType.equals(r52.mimeType) == false) goto L27;
        if (this.description.equals(r52.description) == false) goto L27;
        if (this.width != r52.width) goto L27;
        if (this.height != r52.height) goto L27;
        if (this.depth != r52.depth) goto L27;
        if (this.colors != r52.colors) goto L27;
        if (Arrays.equals(this.pictureData, r52.pictureData) == false) goto L27;
        return true;
    }

    public int hashCode() {
        return ((((((((((((((527 + this.pictureType) * 31) + this.mimeType.hashCode()) * 31) + this.description.hashCode()) * 31) + this.width) * 31) + this.height) * 31) + this.depth) * 31) + this.colors) * 31) + Arrays.hashCode(this.pictureData);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder r2) {
        r2.maybeSetArtworkData(this.pictureData, this.pictureType);
    }

    public String toString() {
        return "Picture: mimeType=" + this.mimeType + ", description=" + this.description;
    }
}
