package androidx.media3.extractor.metadata.id3;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class UrlLinkFrame extends Id3Frame {
    public final String description;
    public final String url;

    public UrlLinkFrame(String r1, String r2, String r3) {
        super(r1);
        this.description = r2;
        this.url = r3;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (UrlLinkFrame.class != r5.getClass()) goto L17;
        UrlLinkFrame r52 = (UrlLinkFrame) r5;
        if (this.id.equals(r52.id) == false) goto L17;
        if (Objects.equals(this.description, r52.description) == false) goto L17;
        if (Objects.equals(this.url, r52.url) == false) goto L17;
        return true;
    }

    public int hashCode() {
        int r1 = (527 + this.id.hashCode()) * 31;
        String r0 = this.description;
        int r2 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r12 = (r1 + r02) * 31;
        String r3 = this.url;
        if (r3 == null) goto L10;
        r2 = r3.hashCode();
    L10:
        return r12 + r2;
    L5:
        r02 = 0;
        goto L6
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": url=" + this.url;
    }
}
