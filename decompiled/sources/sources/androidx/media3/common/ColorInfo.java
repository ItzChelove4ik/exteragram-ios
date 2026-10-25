package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes3.dex */
public final class ColorInfo {
    private static final String FIELD_CHROMA_BITDEPTH = null;
    private static final String FIELD_COLOR_RANGE = null;
    private static final String FIELD_COLOR_SPACE = null;
    private static final String FIELD_COLOR_TRANSFER = null;
    private static final String FIELD_HDR_STATIC_INFO = null;
    private static final String FIELD_LUMA_BITDEPTH = null;
    public static final ColorInfo SDR_BT709_LIMITED = null;
    public static final ColorInfo SRGB_BT709_FULL = null;
    public final int chromaBitdepth;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    private int hashCode;
    public final byte[] hdrStaticInfo;
    public final int lumaBitdepth;

    /* renamed from: androidx.media3.common.ColorInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private int chromaBitdepth;
        private int colorRange;
        private int colorSpace;
        private int colorTransfer;
        private byte[] hdrStaticInfo;
        private int lumaBitdepth;

        public Builder() {
            this.colorSpace = -1;
            this.colorRange = -1;
            this.colorTransfer = -1;
            this.lumaBitdepth = -1;
            this.chromaBitdepth = -1;
        }

        private Builder(ColorInfo r2) {
            this.colorSpace = r2.colorSpace;
            this.colorRange = r2.colorRange;
            this.colorTransfer = r2.colorTransfer;
            this.hdrStaticInfo = r2.hdrStaticInfo;
            this.lumaBitdepth = r2.lumaBitdepth;
            this.chromaBitdepth = r2.chromaBitdepth;
        }

        public /* synthetic */ Builder(ColorInfo r1, AnonymousClass1 r2) {
            this(r1);
        }

        public ColorInfo build() {
            return new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, this.hdrStaticInfo, this.lumaBitdepth, this.chromaBitdepth, null);
        }

        public Builder setChromaBitdepth(int r1) {
            this.chromaBitdepth = r1;
            return this;
        }

        public Builder setColorRange(int r1) {
            this.colorRange = r1;
            return this;
        }

        public Builder setColorSpace(int r1) {
            this.colorSpace = r1;
            return this;
        }

        public Builder setColorTransfer(int r1) {
            this.colorTransfer = r1;
            return this;
        }

        public Builder setHdrStaticInfo(byte[] r1) {
            this.hdrStaticInfo = r1;
            return this;
        }

        public Builder setLumaBitdepth(int r1) {
            this.lumaBitdepth = r1;
            return this;
        }
    }

    static {
        SDR_BT709_LIMITED = new Builder().setColorSpace(1).setColorRange(2).setColorTransfer(3).build();
        SRGB_BT709_FULL = new Builder().setColorSpace(1).setColorRange(1).setColorTransfer(2).build();
        FIELD_COLOR_SPACE = Util.intToStringMaxRadix(0);
        FIELD_COLOR_RANGE = Util.intToStringMaxRadix(1);
        FIELD_COLOR_TRANSFER = Util.intToStringMaxRadix(2);
        FIELD_HDR_STATIC_INFO = Util.intToStringMaxRadix(3);
        FIELD_LUMA_BITDEPTH = Util.intToStringMaxRadix(4);
        FIELD_CHROMA_BITDEPTH = Util.intToStringMaxRadix(5);
    }

    private ColorInfo(int r1, int r2, int r3, byte[] r4, int r5, int r6) {
        this.colorSpace = r1;
        this.colorRange = r2;
        this.colorTransfer = r3;
        this.hdrStaticInfo = r4;
        this.lumaBitdepth = r5;
        this.chromaBitdepth = r6;
    }

    public /* synthetic */ ColorInfo(int r1, int r2, int r3, byte[] r4, int r5, int r6, AnonymousClass1 r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    private static String chromaBitdepthToString(int r1) {
        if (r1 != (-1)) goto L5;
        return "NA";
    L5:
        return r1 + "bit Chroma";
    }

    private static String colorRangeToString(int r2) {
        if (r2 != (-1)) goto L5;
        return "Unset color range";
    L5:
        if (r2 != 1) goto L7;
        return "Full range";
    L7:
        if (r2 != 2) goto L9;
        return "Limited range";
    L9:
        return "Undefined color range " + r2;
    }

    private static String colorSpaceToString(int r2) {
        if (r2 != (-1)) goto L5;
        return "Unset color space";
    L5:
        if (r2 != 6) goto L7;
        return "BT2020";
    L7:
        if (r2 != 1) goto L9;
        return "BT709";
    L9:
        if (r2 != 2) goto L11;
        return "BT601";
    L11:
        return "Undefined color space " + r2;
    }

    private static String colorTransferToString(int r2) {
        if (r2 != (-1)) goto L5;
        return "Unset color transfer";
    L5:
        if (r2 != 10) goto L7;
        return "Gamma 2.2";
    L7:
        if (r2 != 1) goto L9;
        return "Linear";
    L9:
        if (r2 != 2) goto L11;
        return "sRGB";
    L11:
        if (r2 != 3) goto L13;
        return "SDR SMPTE 170M";
    L13:
        if (r2 != 6) goto L15;
        return "ST2084 PQ";
    L15:
        if (r2 != 7) goto L17;
        return "HLG";
    L17:
        return "Undefined color transfer " + r2;
    }

    public static ColorInfo fromBundle(Bundle r8) {
        return new ColorInfo(r8.getInt(FIELD_COLOR_SPACE, -1), r8.getInt(FIELD_COLOR_RANGE, -1), r8.getInt(FIELD_COLOR_TRANSFER, -1), r8.getByteArray(FIELD_HDR_STATIC_INFO), r8.getInt(FIELD_LUMA_BITDEPTH, -1), r8.getInt(FIELD_CHROMA_BITDEPTH, -1));
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = false)
    public static boolean isEquivalentToAssumedSdrDefault(ColorInfo r4) {
        if (r4 != null) goto L5;
        return true;
    L5:
        int r1 = r4.colorSpace;
        if (r1 == (-1)) goto L9;
        if (r1 == 1) goto L9;
        if (r1 == 2) goto L9;
        return false;
    L9:
        int r12 = r4.colorRange;
        if (r12 == (-1)) goto L12;
        if (r12 == 2) goto L12;
        return false;
    L12:
        int r13 = r4.colorTransfer;
        if (r13 == (-1)) goto L17;
        if (r13 == 3) goto L17;
        return false;
    L17:
        if (r4.hdrStaticInfo != null) goto L29;
        int r14 = r4.chromaBitdepth;
        if (r14 == (-1)) goto L21;
        if (r14 == 8) goto L21;
        return false;
    L21:
        int r42 = r4.lumaBitdepth;
        if (r42 == (-1)) goto L24;
        if (r42 == 8) goto L24;
        return false;
    L24:
        return true;
    L29:
        return false;
    }

    @Pure
    public static int isoColorPrimariesToColorSpace(int r2) {
        if (r2 != 1) goto L5;
        return 1;
    L5:
        if (r2 != 9) goto L7;
        return 6;
    L7:
        if (r2 != 4) goto L9;
        return 2;
    L9:
        if (r2 == 5) goto L19;
        if (r2 != 6) goto L12;
        return 2;
    L12:
        if (r2 == 7) goto L21;
        return -1;
    L21:
        return 2;
    L19:
        return 2;
    }

    @Pure
    public static int isoTransferCharacteristicsToColorTransfer(int r3) {
        if (r3 != 1) goto L5;
        return 3;
    L5:
        if (r3 != 4) goto L7;
        return 10;
    L7:
        if (r3 != 13) goto L9;
        return 2;
    L9:
        if (r3 != 16) goto L11;
        return 6;
    L11:
        if (r3 == 18) goto L16;
        if (r3 == 6) goto L24;
        if (r3 == 7) goto L25;
        return -1;
    L25:
        return 3;
    L24:
        return 3;
    L16:
        return 7;
    }

    private static String lumaBitdepthToString(int r1) {
        if (r1 != (-1)) goto L5;
        return "NA";
    L5:
        return r1 + "bit Luma";
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L23:
        return false;
    L8:
        if (ColorInfo.class != r5.getClass()) goto L23;
        ColorInfo r52 = (ColorInfo) r5;
        if (this.colorSpace != r52.colorSpace) goto L23;
        if (this.colorRange != r52.colorRange) goto L23;
        if (this.colorTransfer != r52.colorTransfer) goto L23;
        if (Arrays.equals(this.hdrStaticInfo, r52.hdrStaticInfo) == false) goto L23;
        if (this.lumaBitdepth != r52.lumaBitdepth) goto L23;
        if (this.chromaBitdepth != r52.chromaBitdepth) goto L23;
        return true;
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L6;
        this.hashCode = ((((((((((527 + this.colorSpace) * 31) + this.colorRange) * 31) + this.colorTransfer) * 31) + Arrays.hashCode(this.hdrStaticInfo)) * 31) + this.lumaBitdepth) * 31) + this.chromaBitdepth;
    L6:
        return this.hashCode;
    }

    public boolean isBitdepthValid() {
        if (this.lumaBitdepth != (-1)) goto L5;
        return false;
    L5:
        if (this.chromaBitdepth == (-1)) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isDataSpaceValid() {
        if (this.colorSpace != (-1)) goto L5;
        return false;
    L5:
        if (this.colorRange != (-1)) goto L7;
        return false;
    L7:
        if (this.colorTransfer == (-1)) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isValid() {
        if (isBitdepthValid() == false) goto L5;
        return true;
    L5:
        if (isDataSpaceValid() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_COLOR_SPACE, this.colorSpace);
        r0.putInt(FIELD_COLOR_RANGE, this.colorRange);
        r0.putInt(FIELD_COLOR_TRANSFER, this.colorTransfer);
        r0.putByteArray(FIELD_HDR_STATIC_INFO, this.hdrStaticInfo);
        r0.putInt(FIELD_LUMA_BITDEPTH, this.lumaBitdepth);
        r0.putInt(FIELD_CHROMA_BITDEPTH, this.chromaBitdepth);
        return r0;
    }

    public String toLogString() {
        if (isDataSpaceValid() == false) goto L5;
        String r0 = Util.formatInvariant("%s/%s/%s", new Object[]{colorSpaceToString(this.colorSpace), colorRangeToString(this.colorRange), colorTransferToString(this.colorTransfer)});
    L7:
        if (isBitdepthValid() == false) goto L9;
        String r4 = this.lumaBitdepth + "/" + this.chromaBitdepth;
    L11:
        return r0 + "/" + r4;
    L9:
        r4 = "NA/NA";
        goto L11
    L5:
        r0 = "NA/NA/NA";
        goto L7
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("ColorInfo(");
        r0.append(colorSpaceToString(this.colorSpace));
        r0.append(", ");
        r0.append(colorRangeToString(this.colorRange));
        r0.append(", ");
        r0.append(colorTransferToString(this.colorTransfer));
        r0.append(", ");
        if (this.hdrStaticInfo == null) goto L5;
        boolean r2 = true;
    L6:
        r0.append(r2);
        r0.append(", ");
        r0.append(lumaBitdepthToString(this.lumaBitdepth));
        r0.append(", ");
        r0.append(chromaBitdepthToString(this.chromaBitdepth));
        r0.append(")");
        return r0.toString();
    L5:
        r2 = false;
        goto L6
    }
}
