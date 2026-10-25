package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class ColorUtils {
    static final double[][] SRGB_TO_XYZ = null;
    static final double[] WHITE_POINT_D65 = null;
    static final double[][] XYZ_TO_SRGB = null;

    static {
        SRGB_TO_XYZ = new double[][]{new double[]{0.41233895d, 0.35762064d, 0.18051042d}, new double[]{0.2126d, 0.7152d, 0.0722d}, new double[]{0.01932141d, 0.11916382d, 0.95034478d}};
        XYZ_TO_SRGB = new double[][]{new double[]{3.2413774792388685d, -1.5376652402851851d, -0.49885366846268053d}, new double[]{-0.9691452513005321d, 1.8758853451067872d, 0.04156585616912061d}, new double[]{0.05562093689691305d, -0.20395524564742123d, 1.0571799111220335d}};
        WHITE_POINT_D65 = new double[]{95.047d, 100.0d, 108.883d};
    }

    private ColorUtils() {
    }

    public static int alphaFromArgb(int r0) {
        return (r0 >> 24) & 255;
    }

    public static int argbFromLab(double r9, double r11, double r13) {
        double[] r0 = WHITE_POINT_D65;
        double r92 = (r9 + 16.0d) / 116.0d;
        double r132 = r92 - (r13 / 200.0d);
        double r112 = labInvf((r11 / 500.0d) + r92);
        double r93 = labInvf(r92);
        double r133 = labInvf(r132);
        return argbFromXyz(r112 * r0[0], r93 * r0[1], r133 * r0[2]);
    }

    public static int argbFromLinrgb(double[] r4) {
        return argbFromRgb(delinearized(r4[0]), delinearized(r4[1]), delinearized(r4[2]));
    }

    public static int argbFromLstar(double r0) {
        int r02 = delinearized(yFromLstar(r0));
        return argbFromRgb(r02, r02, r02);
    }

    public static int argbFromRgb(int r1, int r2, int r3) {
        return ((((r1 & 255) << 16) | (-16777216)) | ((r2 & 255) << 8)) | (r3 & 255);
    }

    public static int argbFromXyz(double r11, double r13, double r15) {
        double[][] r0 = XYZ_TO_SRGB;
        double[] r2 = r0[0];
        double r3 = ((r2[0] * r11) + (r2[1] * r13)) + (r2[2] * r15);
        double[] r22 = r0[1];
        double r7 = ((r22[0] * r11) + (r22[1] * r13)) + (r22[2] * r15);
        double[] r02 = r0[2];
        double r1 = ((r02[0] * r11) + (r02[1] * r13)) + (r02[2] * r15);
        return argbFromRgb(delinearized(r3), delinearized(r7), delinearized(r1));
    }

    public static int blueFromArgb(int r0) {
        return r0 & 255;
    }

    public static int delinearized(double r2) {
        double r22 = r2 / 100.0d;
        if (r22 > 0.0031308d) goto L5;
        double r23 = r22 * 12.92d;
    L7:
        return MathUtils.clampInt(0, 255, (int) Math.round(r23 * 255.0d));
    L5:
        r23 = (Math.pow(r22, 0.4166666666666667d) * 1.055d) - 0.055d;
        goto L7
    }

    public static int greenFromArgb(int r0) {
        return (r0 >> 8) & 255;
    }

    public static boolean isOpaque(int r1) {
        if (alphaFromArgb(r1) < 255) goto L6;
        return true;
    L6:
        return false;
    }

    public static double labF(double r2) {
        if (r2 <= 0.008856451679035631d) goto L7;
        return Math.pow(r2, 0.3333333333333333d);
    L7:
        return ((r2 * 903.2962962962963d) + 16.0d) / 116.0d;
    }

    public static double[] labFromArgb(int r17) {
        double r0 = linearized(redFromArgb(r17));
        double r2 = linearized(greenFromArgb(r17));
        double r4 = linearized(blueFromArgb(r17));
        double[][] r6 = SRGB_TO_XYZ;
        double[] r8 = r6[0];
        double r9 = ((r8[0] * r0) + (r8[1] * r2)) + (r8[2] * r4);
        double[] r82 = r6[1];
        double r13 = ((r82[0] * r0) + (r82[1] * r2)) + (r82[2] * r4);
        double[] r62 = r6[2];
        double r15 = ((r62[0] * r0) + (r62[1] * r2)) + (r62[2] * r4);
        double[] r02 = WHITE_POINT_D65;
        double r92 = r9 / r02[0];
        double r132 = r13 / r02[1];
        double r152 = r15 / r02[2];
        double r03 = labF(r92);
        double r22 = labF(r132);
        return new double[]{(116.0d * r22) - 16.0d, (r03 - r22) * 500.0d, (r22 - labF(r152)) * 200.0d};
    }

    public static double labInvf(double r4) {
        double r0 = (r4 * r4) * r4;
        if (r0 <= 0.008856451679035631d) goto L6;
        return r0;
    L6:
        return ((r4 * 116.0d) - 16.0d) / 903.2962962962963d;
    }

    public static double linearized(int r6) {
        double r0 = r6 / 255.0d;
        if (r0 > 0.040449936d) goto L7;
        double r02 = r0 / 12.92d;
    L6:
        return r02 * 100.0d;
    L7:
        r02 = Math.pow((r0 + 0.055d) / 1.055d, 2.4d);
        goto L6
    }

    public static double lstarFromArgb(int r4) {
        return (labF(xyzFromArgb(r4)[1] / 100.0d) * 116.0d) - 16.0d;
    }

    public static double lstarFromY(double r2) {
        return (labF(r2 / 100.0d) * 116.0d) - 16.0d;
    }

    public static int redFromArgb(int r0) {
        return (r0 >> 16) & 255;
    }

    public static double[] whitePointD65() {
        return WHITE_POINT_D65;
    }

    public static double[] xyzFromArgb(int r7) {
        return MathUtils.matrixMultiply(new double[]{linearized(redFromArgb(r7)), linearized(greenFromArgb(r7)), linearized(blueFromArgb(r7))}, SRGB_TO_XYZ);
    }

    public static double yFromLstar(double r2) {
        return labInvf((r2 + 16.0d) / 116.0d) * 100.0d;
    }
}
