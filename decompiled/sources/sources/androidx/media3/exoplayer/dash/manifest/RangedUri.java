package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import androidx.media3.common.util.UriUtil;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class RangedUri {
    private int hashCode;
    public final long length;
    private final String referenceUri;
    public final long start;

    public RangedUri(String r1, long r2, long r4) {
        if (r1 != null) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        this.referenceUri = r1;
        this.start = r2;
        this.length = r4;
    }

    public RangedUri attemptMerge(RangedUri r13, String r14) {
        String r1 = resolveUriString(r14);
        RangedUri r0 = null;
        if (r13 != null) goto L5;
    L26:
        return r0;
    L5:
        if (r1.equals(r13.resolveUriString(r14)) == false) goto L26;
        long r2 = this.length;
        long r4 = -1;
        if (r2 == (-1)) goto L17;
        long r22 = this.start;
        if ((r22 + r2) != r13.start) goto L17;
        long r12 = r13.length;
        if (r12 == (-1)) goto L16;
        r4 = r2 + r12;
    L16:
        return new RangedUri(r1, r22, r4);
    L17:
        long r23 = r13.length;
        if (r23 == (-1)) goto L26;
        long r132 = r13.start;
        if ((r132 + r23) != this.start) goto L26;
        if (r2 == (-1)) goto L25;
        r4 = r23 + r2;
    L25:
        r0 = new RangedUri(r1, r132, r4);
        goto L26
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L17:
        return false;
    L8:
        if (RangedUri.class != r7.getClass()) goto L17;
        RangedUri r72 = (RangedUri) r7;
        if (this.start != r72.start) goto L17;
        if (this.length != r72.length) goto L17;
        if (this.referenceUri.equals(r72.referenceUri) == false) goto L17;
        return true;
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L6;
        this.hashCode = ((((527 + ((int) this.start)) * 31) + ((int) this.length)) * 31) + this.referenceUri.hashCode();
    L6:
        return this.hashCode;
    }

    public Uri resolveUri(String r1) {
        return UriUtil.resolveToUri(r1, this.referenceUri);
    }

    public String resolveUriString(String r1) {
        return UriUtil.resolve(r1, this.referenceUri);
    }

    public String toString() {
        return "RangedUri(referenceUri=" + this.referenceUri + ", start=" + this.start + ", length=" + this.length + ")";
    }
}
