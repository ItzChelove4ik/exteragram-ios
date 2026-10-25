package androidx.core.view;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class DisplayShapeCompat {
    static final DisplayShapeCompat EMPTY = null;
    private final Impl mImpl;

    public interface Impl {
    }

    public static class ImplBase implements Impl {
        private final int mDisplayHeight;
        private final String mDisplayShapeSpec;
        private final int mDisplayWidth;
        private final int mOffsetX;
        private final int mOffsetY;
        private final float mPhysicalPixelDisplaySizeRatio;
        private final int mRotation;
        private final float mScale;

        public ImplBase(String r1, int r2, int r3, float r4, int r5, int r6, int r7, float r8) {
            this.mDisplayShapeSpec = r1;
            this.mDisplayWidth = r2;
            this.mDisplayHeight = r3;
            this.mPhysicalPixelDisplaySizeRatio = r4;
            this.mRotation = r5;
            this.mOffsetX = r6;
            this.mOffsetY = r7;
            this.mScale = r8;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ImplBase) == true) goto L8;
            return false;
        L8:
            ImplBase r52 = (ImplBase) r5;
            if (Objects.equals(this.mDisplayShapeSpec, r52.mDisplayShapeSpec) == true) goto L11;
        L25:
            return false;
        L11:
            if (this.mDisplayWidth != r52.mDisplayWidth) goto L25;
            if (this.mDisplayHeight != r52.mDisplayHeight) goto L25;
            if (this.mPhysicalPixelDisplaySizeRatio != r52.mPhysicalPixelDisplaySizeRatio) goto L25;
            if (this.mRotation != r52.mRotation) goto L25;
            if (this.mOffsetX != r52.mOffsetX) goto L25;
            if (this.mOffsetY != r52.mOffsetY) goto L25;
            if (this.mScale != r52.mScale) goto L25;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mDisplayShapeSpec, Integer.valueOf(this.mDisplayWidth), Integer.valueOf(this.mDisplayHeight), Float.valueOf(this.mPhysicalPixelDisplaySizeRatio), Integer.valueOf(this.mRotation), Integer.valueOf(this.mOffsetX), Integer.valueOf(this.mOffsetY), Float.valueOf(this.mScale)});
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("DisplayShapeCompat{ spec=");
            String r1 = this.mDisplayShapeSpec;
            if (r1 == null) goto L5;
            Object r12 = Integer.valueOf(r1.hashCode());
        L6:
            r0.append(r12);
            r0.append(" displayWidth=");
            r0.append(this.mDisplayWidth);
            r0.append(" displayHeight=");
            r0.append(this.mDisplayHeight);
            r0.append(" physicalPixelDisplaySizeRatio=");
            r0.append(this.mPhysicalPixelDisplaySizeRatio);
            r0.append(" rotation=");
            r0.append(this.mRotation);
            r0.append(" offsetX=");
            r0.append(this.mOffsetX);
            r0.append(" offsetY=");
            r0.append(this.mOffsetY);
            r0.append(" scale=");
            r0.append(this.mScale);
            r0.append("}");
            return r0.toString();
        L5:
            r12 = "null";
            goto L6
        }
    }

    static {
        EMPTY = new DisplayShapeCompat(_UrlKt.FRAGMENT_ENCODE_SET, 0, 0, 1.0f, 0, 0, 0, 1.0f);
    }

    private DisplayShapeCompat(String r10, int r11, int r12, float r13, int r14, int r15, int r16, float r17) {
        this.mImpl = new ImplBase(r10, r11, r12, r13, r14, r15, r16, r17);
    }

    public static DisplayShapeCompat create(int r9, int r10, boolean r11, int r12, int r13, int r14, int r15) {
        return new DisplayShapeCompat(createSpecString(r9, r10, r11, r12, r13, r14, r15), r9, r10, 1.0f, 0, 0, 0, 1.0f);
    }

    private static String createSpecString(int r6, int r7, boolean r8, int r9, int r10, int r11, int r12) {
        if (r8 == false) goto L6;
        int r82 = r6 / 2;
        int r72 = r7 / 2;
        return "M0," + r72 + " A" + r82 + "," + r72 + " 0 1,1 " + r6 + "," + r72 + " A" + r82 + "," + r72 + " 0 1,1 0," + r72 + " Z";
    L6:
        StringBuilder r83 = new StringBuilder("M ");
        int r2 = Math.min(r6 / 2, r7 / 2);
        int r92 = Math.min(r2, r9);
        int r102 = Math.min(r2, r10);
        int r112 = Math.min(r2, r11);
        int r122 = Math.min(r2, r12);
        r83.append(r92);
        r83.append(",0 L ");
        r83.append(r6 - r102);
        r83.append(",0");
        if (r102 <= 0) goto L9;
        r83.append(" A ");
        r83.append(r102);
        r83.append(",");
        r83.append(r102);
        r83.append(" 0 0,1 ");
        r83.append(r6);
        r83.append(",");
        r83.append(r102);
    L9:
        r83.append(" L ");
        r83.append(r6);
        r83.append(",");
        r83.append(r7 - r112);
        if (r112 <= 0) goto L12;
        r83.append(" A ");
        r83.append(r112);
        r83.append(",");
        r83.append(r112);
        r83.append(" 0 0,1 ");
        r83.append(r6 - r112);
        r83.append(",");
        r83.append(r7);
    L12:
        r83.append(" L ");
        r83.append(r122);
        r83.append(",");
        r83.append(r7);
        if (r122 <= 0) goto L15;
        r83.append(" A ");
        r83.append(r122);
        r83.append(",");
        r83.append(r122);
        r83.append(" 0 0,1 0,");
        r83.append(r7 - r122);
    L15:
        if (r92 <= 0) goto L17;
        r83.append(" L 0,");
        r83.append(r92);
        r83.append(" A ");
        r83.append(r92);
        r83.append(",");
        r83.append(r92);
        r83.append(" 0 0,1 ");
        r83.append(r92);
        r83.append(",0");
    L17:
        r83.append(" Z");
        return r83.toString();
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof DisplayShapeCompat) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mImpl, ((DisplayShapeCompat) r2).mImpl);
    }

    public int hashCode() {
        return Objects.hashCode(this.mImpl);
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
