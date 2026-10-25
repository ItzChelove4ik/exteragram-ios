package com.google.android.datatransport.runtime;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.Encoding;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class EncodedPayload {
    private final byte[] bytes;
    private final Encoding encoding;

    public EncodedPayload(Encoding r2, byte[] r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.encoding = r2;
        this.bytes = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("bytes is null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("encoding is null");
        throw null;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof EncodedPayload) == true) goto L8;
        return false;
    L8:
        EncodedPayload r42 = (EncodedPayload) r4;
        if (this.encoding.equals(r42.encoding) == true) goto L12;
        return false;
    L12:
        return Arrays.equals(this.bytes, r42.bytes);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public int hashCode() {
        int r0 = (this.encoding.hashCode() ^ 1000003) * 1000003;
        return Arrays.hashCode(this.bytes) ^ r0;
    }

    public String toString() {
        return "EncodedPayload{encoding=" + this.encoding + ", bytes=[...]}";
    }
}
