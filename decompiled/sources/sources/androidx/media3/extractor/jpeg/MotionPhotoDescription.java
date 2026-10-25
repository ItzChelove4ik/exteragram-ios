package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import java.util.List;

/* loaded from: classes4.dex */
public final class MotionPhotoDescription {
    public final List<ContainerItem> items;
    public final long photoPresentationTimestampUs;

    public static final class ContainerItem {
        public final long length;
        public final String mime;
        public final long padding;
        public final String semantic;

        public ContainerItem(String r1, String r2, long r3, long r5) {
            this.mime = r1;
            this.semantic = r2;
            this.length = r3;
            this.padding = r5;
        }
    }

    public MotionPhotoDescription(long r1, List<ContainerItem> r3) {
        this.photoPresentationTimestampUs = r1;
        this.items = r3;
    }

    public MotionPhotoMetadata getMotionPhotoMetadata(long r22) {
        if (this.items.size() >= 2) goto L5;
        return null;
    L5:
        int r1 = this.items.size() - 1;
        long r6 = r22;
        long r9 = -1;
        long r11 = -1;
        long r15 = -1;
        long r17 = -1;
        boolean r8 = false;
    L6:
        if (r1 < 0) goto L21;
        ContainerItem r13 = this.items.get(r1);
        boolean r2 = "video/mp4".equals(r13.mime) | r8;
        if (r1 != 0) goto L11;
        r6 = r6 - r13.padding;
        long r132 = 0;
    L10:
        long r19 = r132;
        long r133 = r6;
        r6 = r19;
        if (r2 == true) goto L14;
    L16:
        r8 = r2;
    L17:
        if (r1 != 0) goto L19;
        r9 = r6;
        r11 = r133;
    L19:
        r1 = r1 - 1;
        goto L6
    L14:
        if (r6 == r133) goto L16;
        r17 = r133 - r6;
        r15 = r6;
        r8 = false;
        goto L17
    L11:
        r132 = r6 - r13.length;
        goto L10
    L21:
        if (r15 != (-1)) goto L23;
    L31:
        return null;
    L23:
        if (r17 == (-1)) goto L31;
        if (r9 == (-1)) goto L31;
        if (r11 == (-1)) goto L31;
        return new MotionPhotoMetadata(r9, r11, this.photoPresentationTimestampUs, r15, r17);
    }
}
