package androidx.media3.container;

import androidx.media3.common.Metadata;

/* loaded from: classes3.dex */
public final class Mp4AlternateGroupData implements Metadata.Entry {
    public final int alternateGroup;

    public Mp4AlternateGroupData(int r1) {
        this.alternateGroup = r1;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof Mp4AlternateGroupData) == true) goto L9;
        return false;
    L9:
        if (this.alternateGroup != ((Mp4AlternateGroupData) r4).alternateGroup) goto L11;
        return true;
    L11:
        return false;
    }

    public int hashCode() {
        return this.alternateGroup;
    }

    public String toString() {
        return "Mp4AlternateGroup: " + this.alternateGroup;
    }
}
