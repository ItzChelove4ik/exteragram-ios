package androidx.media3.extractor.metadata.id3;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class CommentFrame extends Id3Frame {
    public final String description;
    public final String language;
    public final String text;

    public CommentFrame(String r2, String r3, String r4) {
        super("COMM");
        this.language = r2;
        this.description = r3;
        this.text = r4;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (CommentFrame.class != r5.getClass()) goto L17;
        CommentFrame r52 = (CommentFrame) r5;
        if (Objects.equals(this.description, r52.description) == false) goto L17;
        if (Objects.equals(this.language, r52.language) == false) goto L17;
        if (Objects.equals(this.text, r52.text) == false) goto L17;
        return true;
    }

    public int hashCode() {
        String r0 = this.language;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r2 = (527 + r02) * 31;
        String r03 = this.description;
        if (r03 == null) goto L9;
        int r04 = r03.hashCode();
    L10:
        int r22 = (r2 + r04) * 31;
        String r3 = this.text;
        if (r3 == null) goto L14;
        r1 = r3.hashCode();
    L14:
        return r22 + r1;
    L9:
        r04 = 0;
        goto L10
    L5:
        r02 = 0;
        goto L6
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": language=" + this.language + ", description=" + this.description + ", text=" + this.text;
    }
}
