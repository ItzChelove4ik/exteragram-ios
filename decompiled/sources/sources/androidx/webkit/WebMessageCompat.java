package androidx.webkit;

import java.util.Objects;

/* loaded from: classes4.dex */
public class WebMessageCompat {
    private final byte[] mArrayBuffer;
    private final WebMessagePortCompat[] mPorts;
    private final String mString;
    private final int mType;

    public WebMessageCompat(String r1, WebMessagePortCompat[] r2) {
        this.mString = r1;
        this.mArrayBuffer = null;
        this.mPorts = r2;
        this.mType = 0;
    }

    public WebMessageCompat(byte[] r1, WebMessagePortCompat[] r2) {
        Objects.requireNonNull(r1);
        this.mArrayBuffer = r1;
        this.mString = null;
        this.mPorts = r2;
        this.mType = 1;
    }

    private void checkType(int r3) {
        if (r3 != this.mType) goto L5;
        return;
    L5:
        WebMessageCompat$$ExternalSyntheticBUOutline0.m("Wrong data accessor type detected. ", typeToString(this.mType), " expected, but got ", typeToString(r3));
    }

    private String typeToString(int r1) {
        if (r1 != 0) goto L4;
        return "String";
    L4:
        if (r1 == 1) goto L7;
        return "Unknown";
    L7:
        return "ArrayBuffer";
    }

    public byte[] getArrayBuffer() {
        checkType(1);
        Objects.requireNonNull(this.mArrayBuffer);
        return this.mArrayBuffer;
    }

    public String getData() {
        checkType(0);
        return this.mString;
    }

    public int getType() {
        return this.mType;
    }
}
