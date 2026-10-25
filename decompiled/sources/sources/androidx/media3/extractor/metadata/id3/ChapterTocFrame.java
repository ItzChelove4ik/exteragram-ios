package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ChapterTocFrame extends Id3Frame {
    public final String[] children;
    public final String elementId;
    public final boolean isOrdered;
    public final boolean isRoot;
    private final Id3Frame[] subFrames;

    public ChapterTocFrame(String r2, boolean r3, boolean r4, String[] r5, Id3Frame[] r6) {
        super("CTOC");
        this.elementId = r2;
        this.isRoot = r3;
        this.isOrdered = r4;
        this.children = r5;
        this.subFrames = r6;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L21:
        return false;
    L8:
        if (ChapterTocFrame.class != r5.getClass()) goto L21;
        ChapterTocFrame r52 = (ChapterTocFrame) r5;
        if (this.isRoot != r52.isRoot) goto L21;
        if (this.isOrdered != r52.isOrdered) goto L21;
        if (Objects.equals(this.elementId, r52.elementId) == false) goto L21;
        if (Arrays.equals(this.children, r52.children) == false) goto L21;
        if (Arrays.equals(this.subFrames, r52.subFrames) == false) goto L21;
        return true;
    }

    public int hashCode() {
        int r0 = (((527 + (this.isRoot ? 1 : 0)) * 31) + (this.isOrdered ? 1 : 0)) * 31;
        String r2 = this.elementId;
        if (r2 == null) goto L5;
        int r22 = r2.hashCode();
    L7:
        return r0 + r22;
    L5:
        r22 = 0;
        goto L7
    }
}
