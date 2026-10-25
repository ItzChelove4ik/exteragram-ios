package androidx.media3.extractor.metadata.icy;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class IcyInfo implements Metadata.Entry {
    public final byte[] rawMetadata;
    public final String title;
    public final String url;

    public IcyInfo(byte[] r1, String r2, String r3) {
        this.rawMetadata = r1;
        this.title = r2;
        this.url = r3;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (IcyInfo.class == r3.getClass()) goto L10;
        return false;
    L10:
        return Arrays.equals(this.rawMetadata, ((IcyInfo) r3).rawMetadata);
    }

    public int hashCode() {
        return Arrays.hashCode(this.rawMetadata);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder r1) {
        String r0 = this.title;
        if (r0 == null) goto L6;
        r1.setTitle(r0);
        return;
    }

    public String toString() {
        return String.format("ICY: title=\"%s\", url=\"%s\", rawMetadata.length=\"%s\"", new Object[]{this.title, this.url, Integer.valueOf(this.rawMetadata.length)});
    }
}
