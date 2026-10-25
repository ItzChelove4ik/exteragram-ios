package androidx.media3.extractor.metadata.mp4;

import androidx.media3.common.Metadata;
import com.google.common.primitives.Longs;

/* loaded from: classes4.dex */
public final class MotionPhotoMetadata implements Metadata.Entry {
    public final long photoPresentationTimestampUs;
    public final long photoSize;
    public final long photoStartPosition;
    public final long videoSize;
    public final long videoStartPosition;

    public MotionPhotoMetadata(long r1, long r3, long r5, long r7, long r9) {
        this.photoStartPosition = r1;
        this.photoSize = r3;
        this.photoPresentationTimestampUs = r5;
        this.videoStartPosition = r7;
        this.videoSize = r9;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L21:
        return false;
    L8:
        if (MotionPhotoMetadata.class != r7.getClass()) goto L21;
        MotionPhotoMetadata r72 = (MotionPhotoMetadata) r7;
        if (this.photoStartPosition != r72.photoStartPosition) goto L21;
        if (this.photoSize != r72.photoSize) goto L21;
        if (this.photoPresentationTimestampUs != r72.photoPresentationTimestampUs) goto L21;
        if (this.videoStartPosition != r72.videoStartPosition) goto L21;
        if (this.videoSize != r72.videoSize) goto L21;
        return true;
    }

    public int hashCode() {
        return ((((((((527 + Longs.hashCode(this.photoStartPosition)) * 31) + Longs.hashCode(this.photoSize)) * 31) + Longs.hashCode(this.photoPresentationTimestampUs)) * 31) + Longs.hashCode(this.videoStartPosition)) * 31) + Longs.hashCode(this.videoSize);
    }

    public String toString() {
        return "Motion photo metadata: photoStartPosition=" + this.photoStartPosition + ", photoSize=" + this.photoSize + ", photoPresentationTimestampUs=" + this.photoPresentationTimestampUs + ", videoStartPosition=" + this.videoStartPosition + ", videoSize=" + this.videoSize;
    }
}
