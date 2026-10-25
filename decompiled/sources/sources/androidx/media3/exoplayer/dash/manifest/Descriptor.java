package androidx.media3.exoplayer.dash.manifest;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class Descriptor {
    public final String id;
    public final String schemeIdUri;
    public final String value;

    public Descriptor(String r1, String r2, String r3) {
        this.schemeIdUri = r1;
        this.value = r2;
        this.id = r3;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (Descriptor.class != r5.getClass()) goto L17;
        Descriptor r52 = (Descriptor) r5;
        if (Objects.equals(this.schemeIdUri, r52.schemeIdUri) == false) goto L17;
        if (Objects.equals(this.value, r52.value) == false) goto L17;
        if (Objects.equals(this.id, r52.id) == false) goto L17;
        return true;
    }

    public int hashCode() {
        int r0 = this.schemeIdUri.hashCode() * 31;
        String r1 = this.value;
        int r2 = 0;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L6:
        int r02 = (r0 + r12) * 31;
        String r3 = this.id;
        if (r3 == null) goto L10;
        r2 = r3.hashCode();
    L10:
        return r02 + r2;
    L5:
        r12 = 0;
        goto L6
    }
}
