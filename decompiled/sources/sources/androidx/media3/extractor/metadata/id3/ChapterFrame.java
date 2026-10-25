package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ChapterFrame extends Id3Frame {
    public final String chapterId;
    public final long endOffset;
    public final int endTimeMs;
    public final long startOffset;
    public final int startTimeMs;
    private final Id3Frame[] subFrames;

    public ChapterFrame(String r2, int r3, int r4, long r5, long r7, Id3Frame[] r9) {
        super("CHAP");
        this.chapterId = r2;
        this.startTimeMs = r3;
        this.endTimeMs = r4;
        this.startOffset = r5;
        this.endOffset = r7;
        this.subFrames = r9;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L23:
        return false;
    L8:
        if (ChapterFrame.class != r7.getClass()) goto L23;
        ChapterFrame r72 = (ChapterFrame) r7;
        if (this.startTimeMs != r72.startTimeMs) goto L23;
        if (this.endTimeMs != r72.endTimeMs) goto L23;
        if (this.startOffset != r72.startOffset) goto L23;
        if (this.endOffset != r72.endOffset) goto L23;
        if (Objects.equals(this.chapterId, r72.chapterId) == false) goto L23;
        if (Arrays.equals(this.subFrames, r72.subFrames) == false) goto L23;
        return true;
    }

    public int hashCode() {
        int r0 = (((((((527 + this.startTimeMs) * 31) + this.endTimeMs) * 31) + ((int) this.startOffset)) * 31) + ((int) this.endOffset)) * 31;
        String r3 = this.chapterId;
        if (r3 == null) goto L5;
        int r32 = r3.hashCode();
    L7:
        return r0 + r32;
    L5:
        r32 = 0;
        goto L7
    }
}
