package androidx.core.graphics;

import android.graphics.Color;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public abstract class ColorUtils {
    private static final ThreadLocal<double[]> TEMP_ARRAY = null;

    static {
        TEMP_ARRAY = new ThreadLocal();
    }

    public static int HSLToColor(float[] r6) {
        float r1 = r6[0];
        float r2 = r6[1];
        float r62 = r6[2];
        float r4 = (1.0f - Math.abs((r62 * 2.0f) - 1.0f)) * r2;
        float r63 = r62 - (0.5f * r4);
        float r5 = (1.0f - Math.abs(((r1 / 60.0f) % 2.0f) - 1.0f)) * r4;
        switch((((int) r1) / 60)) {
            case 0: goto L10;
            case 1: goto L9;
            case 2: goto L8;
            case 3: goto L7;
            case 4: goto L6;
            case 5: goto L5;
            case 6: goto L5;
            default: goto L4;
        };
    L4:
        int r64 = 0;
        int r12 = 0;
        int r3 = 0;
    L12:
        return Color.rgb(constrain(r12, 0, 255), constrain(r3, 0, 255), constrain(r64, 0, 255));
    L5:
        r12 = Math.round((r4 + r63) * 255.0f);
        r3 = Math.round(r63 * 255.0f);
        r64 = Math.round((r5 + r63) * 255.0f);
        goto L12
    L6:
        r12 = Math.round((r5 + r63) * 255.0f);
        r3 = Math.round(r63 * 255.0f);
        r64 = Math.round((r4 + r63) * 255.0f);
        goto L12
    L7:
        r12 = Math.round(r63 * 255.0f);
        r3 = Math.round((r5 + r63) * 255.0f);
        r64 = Math.round((r4 + r63) * 255.0f);
        goto L12
    L8:
        r12 = Math.round(r63 * 255.0f);
        r3 = Math.round((r4 + r63) * 255.0f);
        r64 = Math.round((r5 + r63) * 255.0f);
        goto L12
    L9:
        r12 = Math.round((r5 + r63) * 255.0f);
        r3 = Math.round((r4 + r63) * 255.0f);
        r64 = Math.round(r63 * 255.0f);
        goto L12
    L10:
        r12 = Math.round((r4 + r63) * 255.0f);
        r3 = Math.round((r5 + r63) * 255.0f);
        r64 = Math.round(r63 * 255.0f);
        goto L12
    }

    public static int LABToColor(double r7, double r9, double r11) {
        double[] r6 = getTempDouble3Array();
        LABToXYZ(r7, r9, r11, r6);
        return XYZToColor(r6[0], r6[1], r6[2]);
    }

    public static void LABToXYZ(double r19, double r21, double r23, double[] r25) {
        double r2 = (r19 + 16.0d) / 116.0d;
        double r6 = (r21 / 500.0d) + r2;
        double r8 = r2 - (r23 / 200.0d);
        double r12 = Math.pow(r6, 3.0d);
        if (r12 > 0.008856d) goto L7;
        r12 = ((r6 * 116.0d) - 16.0d) / 903.3d;
    L7:
        if (r19 <= 7.9996247999999985d) goto L9;
        double r22 = Math.pow(r2, 3.0d);
    L10:
        double r62 = Math.pow(r8, 3.0d);
        if (r62 > 0.008856d) goto L14;
        r62 = ((r8 * 116.0d) - 16.0d) / 903.3d;
    L14:
        r25[0] = r12 * 95.047d;
        r25[1] = r22 * 100.0d;
        r25[2] = r62 * 108.883d;
        return;
    L9:
        r22 = r19 / 903.3d;
        goto L10
    }

    public static void RGBToHSL(int r7, int r8, int r9, float[] r10) {
        float r72 = r7 / 255.0f;
        float r82 = r8 / 255.0f;
        float r92 = r9 / 255.0f;
        float r0 = Math.max(r72, Math.max(r82, r92));
        float r1 = Math.min(r72, Math.min(r82, r92));
        float r2 = r0 - r1;
        float r3 = (r0 + r1) / 2.0f;
        if (r0 != r1) goto L6;
        float r83 = 0.0f;
        float r22 = 0.0f;
    L13:
        float r84 = (r83 * 60.0f) % 360.0f;
        if (r84 >= 0.0f) goto L16;
        r84 = r84 + 360.0f;
    L16:
        r10[0] = constrain(r84, 0.0f, 360.0f);
        r10[1] = constrain(r22, 0.0f, 1.0f);
        r10[2] = constrain(r3, 0.0f, 1.0f);
        return;
    L6:
        if (r0 != r72) goto L9;
        r83 = ((r82 - r92) / r2) % 6.0f;
    L12:
        r22 = r2 / (1.0f - Math.abs((2.0f * r3) - 1.0f));
        goto L13
    L9:
        if (r0 != r82) goto L11;
        r83 = ((r92 - r72) / r2) + 2.0f;
        goto L12
    L11:
        r83 = 4.0f + ((r72 - r82) / r2);
        goto L12
    }

    public static void RGBToLAB(int r7, int r8, int r9, double[] r10) {
        RGBToXYZ(r7, r8, r9, r10);
        XYZToLAB(r10[0], r10[1], r10[2], r10);
    }

    public static void RGBToXYZ(int r20, int r21, int r22, double[] r23) {
        if (r23.length != 3) goto L20;
        double r1 = r20 / 255.0d;
        if (r1 >= 0.04045d) goto L8;
        double r12 = r1 / 12.92d;
    L9:
        double r3 = r21 / 255.0d;
        if (r3 >= 0.04045d) goto L13;
        double r32 = r3 / 12.92d;
    L14:
        double r5 = r22 / 255.0d;
        if (r5 >= 0.04045d) goto L17;
        double r52 = r5 / 12.92d;
    L18:
        r23[0] = (((0.4124d * r12) + (0.3576d * r32)) + (0.1805d * r52)) * 100.0d;
        r23[1] = (((0.2126d * r12) + (0.7152d * r32)) + (0.0722d * r52)) * 100.0d;
        r23[2] = (((r12 * 0.0193d) + (r32 * 0.1192d)) + (r52 * 0.9505d)) * 100.0d;
        return;
    L17:
        r52 = Math.pow((r5 + 0.055d) / 1.055d, 2.4d);
        goto L18
    L13:
        r32 = Math.pow((r3 + 0.055d) / 1.055d, 2.4d);
        goto L14
    L8:
        r12 = Math.pow((r1 + 0.055d) / 1.055d, 2.4d);
        goto L9
    L20:
        f$$ExternalSyntheticBUOutline1.m("outXyz must have a length of 3.");
    }

    public static int XYZToColor(double r17, double r19, double r21) {
        double r0 = (((3.2406d * r17) + ((-1.5372d) * r19)) + ((-0.4986d) * r21)) / 100.0d;
        double r4 = ((((-0.9689d) * r17) + (1.8758d * r19)) + (0.0415d * r21)) / 100.0d;
        double r6 = (((0.0557d * r17) + ((-0.204d) * r19)) + (1.057d * r21)) / 100.0d;
        if (r0 <= 0.0031308d) goto L5;
        double r02 = (Math.pow(r0, 0.4166666666666667d) * 1.055d) - 0.055d;
    L7:
        if (r4 <= 0.0031308d) goto L9;
        double r42 = (Math.pow(r4, 0.4166666666666667d) * 1.055d) - 0.055d;
    L11:
        if (r6 <= 0.0031308d) goto L13;
        double r2 = (Math.pow(r6, 0.4166666666666667d) * 1.055d) - 0.055d;
    L15:
        return Color.rgb(constrain((int) Math.round(r02 * 255.0d), 0, 255), constrain((int) Math.round(r42 * 255.0d), 0, 255), constrain((int) Math.round(r2 * 255.0d), 0, 255));
    L13:
        r2 = r6 * 12.92d;
        goto L15
    L9:
        r42 = r4 * 12.92d;
        goto L11
    L5:
        r02 = r0 * 12.92d;
        goto L7
    }

    public static void XYZToLAB(double r4, double r6, double r8, double[] r10) {
        if (r10.length != 3) goto L6;
        double r42 = pivotXyzComponent(r4 / 95.047d);
        double r62 = pivotXyzComponent(r6 / 100.0d);
        double r82 = pivotXyzComponent(r8 / 108.883d);
        r10[0] = Math.max(0.0d, (116.0d * r62) - 16.0d);
        r10[1] = (r42 - r62) * 500.0d;
        r10[2] = (r62 - r82) * 200.0d;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("outLab must have a length of 3.");
    }

    public static int blendARGB(int r5, int r6, float r7) {
        float r0 = 1.0f - r7;
        return Color.argb((int) ((Color.alpha(r5) * r0) + (Color.alpha(r6) * r7)), (int) ((Color.red(r5) * r0) + (Color.red(r6) * r7)), (int) ((Color.green(r5) * r0) + (Color.green(r6) * r7)), (int) ((Color.blue(r5) * r0) + (Color.blue(r6) * r7)));
    }

    public static double calculateContrast(int r4, int r5) {
        if (Color.alpha(r5) == 255) goto L5;
        Buffer$$ExternalSyntheticBUOutline4.m("background can not be translucent: #", Integer.toHexString(r5));
        return 0.0d;
    L5:
        if (Color.alpha(r4) >= 255) goto L7;
        r4 = compositeColors(r4, r5);
    L7:
        double r0 = calculateLuminance(r4) + 0.05d;
        double r42 = calculateLuminance(r5) + 0.05d;
        return Math.max(r0, r42) / Math.min(r0, r42);
    }

    public static double calculateLuminance(int r5) {
        double[] r0 = getTempDouble3Array();
        colorToXYZ(r5, r0);
        return r0[1] / 100.0d;
    }

    public static int calculateMinimumAlpha(int r8, int r9, float r10) {
        int r1 = 255;
        if (Color.alpha(r9) != 255) goto L19;
        double r4 = r10;
        if (calculateContrast(setAlphaComponent(r8, 255), r9) >= r4) goto L8;
        return -1;
    L8:
        int r102 = 0;
        int r0 = 0;
    L10:
        if (r102 > 10) goto L18;
        if ((r1 - r0) <= 1) goto L18;
        int r2 = (r0 + r1) / 2;
        if (calculateContrast(setAlphaComponent(r8, r2), r9) >= r4) goto L16;
        r0 = r2;
    L17:
        r102 = r102 + 1;
        goto L10
    L16:
        r1 = r2;
    L18:
        return r1;
    L19:
        Buffer$$ExternalSyntheticBUOutline4.m("background can not be translucent: #", Integer.toHexString(r9));
        return 0;
    }

    public static void colorToHSL(int r2, float[] r3) {
        RGBToHSL(Color.red(r2), Color.green(r2), Color.blue(r2), r3);
    }

    public static void colorToLAB(int r2, double[] r3) {
        RGBToLAB(Color.red(r2), Color.green(r2), Color.blue(r2), r3);
    }

    public static void colorToXYZ(int r2, double[] r3) {
        RGBToXYZ(Color.red(r2), Color.green(r2), Color.blue(r2), r3);
    }

    private static int compositeAlpha(int r0, int r1) {
        return 255 - (((255 - r1) * (255 - r0)) / 255);
    }

    public static int compositeColors(int r6, int r7) {
        int r0 = Color.alpha(r7);
        int r1 = Color.alpha(r6);
        int r2 = compositeAlpha(r1, r0);
        return Color.argb(r2, compositeComponent(Color.red(r6), r1, Color.red(r7), r0, r2), compositeComponent(Color.green(r6), r1, Color.green(r7), r0, r2), compositeComponent(Color.blue(r6), r1, Color.blue(r7), r0, r2));
    }

    private static int compositeComponent(int r0, int r1, int r2, int r3, int r4) {
        if (r4 != 0) goto L6;
        return 0;
    L6:
        return (((r0 * 255) * r1) + ((r2 * r3) * (255 - r1))) / (r4 * 255);
    }

    private static float constrain(float r1, float r2, float r3) {
        if (r1 >= r2) goto L6;
        return r2;
    L6:
        return Math.min(r1, r3);
    }

    private static int constrain(int r0, int r1, int r2) {
        if (r0 >= r1) goto L5;
        return r1;
    L5:
        return Math.min(r0, r2);
    }

    private static double[] getTempDouble3Array() {
        ThreadLocal<double[]> r0 = TEMP_ARRAY;
        double[] r1 = r0.get();
        if (r1 != null) goto L6;
        double[] r12 = new double[3];
        r0.set(r12);
        return r12;
    L6:
        return r1;
    }

    private static double pivotXyzComponent(double r2) {
        if (r2 <= 0.008856d) goto L7;
        return Math.pow(r2, 0.3333333333333333d);
    L7:
        return ((r2 * 903.3d) + 16.0d) / 116.0d;
    }

    public static int setAlphaComponent(int r1, int r2) {
        if (r2 >= 0) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("alpha must be between 0 and 255.");
        return 0;
    L4:
        if (r2 > 255) goto L7;
        return (r1 & 16777215) | (r2 << 24);
    }
}
