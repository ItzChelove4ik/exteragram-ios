package androidx.camera.core;

/* loaded from: classes3.dex */
public final class DynamicRange {
    public static final DynamicRange DOLBY_VISION_10_BIT = null;
    public static final DynamicRange DOLBY_VISION_8_BIT = null;
    public static final DynamicRange HDR10_10_BIT = null;
    public static final DynamicRange HDR10_PLUS_10_BIT = null;
    public static final DynamicRange HDR_UNSPECIFIED_10_BIT = null;
    public static final DynamicRange HLG_10_BIT = null;
    public static final DynamicRange SDR = null;
    public static final DynamicRange UNSPECIFIED = null;
    private final int mBitDepth;
    private final int mEncoding;

    static {
        UNSPECIFIED = new DynamicRange(0, 0);
        SDR = new DynamicRange(1, 8);
        HDR_UNSPECIFIED_10_BIT = new DynamicRange(2, 10);
        HLG_10_BIT = new DynamicRange(3, 10);
        HDR10_10_BIT = new DynamicRange(4, 10);
        HDR10_PLUS_10_BIT = new DynamicRange(5, 10);
        DOLBY_VISION_10_BIT = new DynamicRange(6, 10);
        DOLBY_VISION_8_BIT = new DynamicRange(6, 8);
    }

    public DynamicRange(int r1, int r2) {
        this.mEncoding = r1;
        this.mBitDepth = r2;
    }

    private static String getEncodingLabel(int r0) {
        switch(r0) {
            case 0: goto L17;
            case 1: goto L15;
            case 2: goto L13;
            case 3: goto L11;
            case 4: goto L9;
            case 5: goto L7;
            case 6: goto L5;
            default: goto L3;
        };
    L3:
        return "<Unknown>";
    L5:
        return "DOLBY_VISION";
    L7:
        return "HDR10_PLUS";
    L9:
        return "HDR10";
    L11:
        return "HLG";
    L13:
        return "HDR_UNSPECIFIED";
    L15:
        return "SDR";
    L17:
        return "UNSPECIFIED";
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof DynamicRange) == false) goto L12;
        DynamicRange r52 = (DynamicRange) r5;
        if (this.mEncoding != r52.getEncoding()) goto L12;
        if (this.mBitDepth != r52.getBitDepth()) goto L12;
        return true;
    L12:
        return false;
    }

    public int getBitDepth() {
        return this.mBitDepth;
    }

    public int getEncoding() {
        return this.mEncoding;
    }

    public int hashCode() {
        int r0 = (this.mEncoding ^ 1000003) * 1000003;
        return this.mBitDepth ^ r0;
    }

    public boolean is10BitHdr() {
        if (isFullySpecified() == true) goto L5;
        return false;
    L5:
        if (getEncoding() != 1) goto L7;
        return false;
    L7:
        if (getBitDepth() != 10) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean isFullySpecified() {
        if (getEncoding() != 0) goto L5;
        return false;
    L5:
        if (getEncoding() != 2) goto L7;
        return false;
    L7:
        if (getBitDepth() == 0) goto L13;
        return true;
    L13:
        return false;
    }

    public String toString() {
        return "DynamicRange@" + Integer.toHexString(System.identityHashCode(this)) + "{encoding=" + getEncodingLabel(this.mEncoding) + ", bitDepth=" + this.mBitDepth + "}";
    }
}
