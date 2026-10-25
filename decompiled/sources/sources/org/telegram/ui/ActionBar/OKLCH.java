package org.telegram.ui.ActionBar;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public abstract class OKLCH {
    public static final double[] LMStoLab_M = null;
    public static final double[] LMStoXYZ_M = null;
    public static final double[] LabtoLMS_M = null;
    public static final double[] XYZtoLMS_M = null;
    public static final double[] fromXYZ_M = null;
    public static final double[] toXYZ_M = null;

    static {
        XYZtoLMS_M = new double[]{0.819022437996703d, 0.3619062600528904d, -0.1288737815209879d, 0.0329836539323885d, 0.9292868615863434d, 0.0361446663506424d, 0.0481771893596242d, 0.2642395317527308d, 0.6335478284694309d};
        LMStoXYZ_M = new double[]{1.2268798758459243d, -0.5578149944602171d, 0.2813910456659647d, -0.0405757452148008d, 1.112286803280317d, -0.0717110580655164d, -0.0763729366746601d, -0.4214933324022432d, 1.5869240198367816d};
        LMStoLab_M = new double[]{0.210454268309314d, 0.7936177747023054d, -0.0040720430116193d, 1.9779985324311684d, -2.42859224204858d, 0.450593709617411d, 0.0259040424655478d, 0.7827717124575296d, -0.8086757549230774d};
        LabtoLMS_M = new double[]{1.0d, 0.3963377773761749d, 0.2158037573099136d, 1.0d, -0.1055613458156586d, -0.0638541728258133d, 1.0d, -0.0894841775298119d, -1.2914855480194092d};
        toXYZ_M = new double[]{0.41239079926595934d, 0.357584339383878d, 0.1804807884018343d, 0.21263900587151027d, 0.715168678767756d, 0.07219231536073371d, 0.01933081871559182d, 0.11919477979462598d, 0.9505321522496607d};
        fromXYZ_M = new double[]{3.2409699419045226d, -1.537383177570094d, -0.4986107602930034d, -0.9692436362808796d, 1.8759675015077202d, 0.04155505740717559d, 0.05563007969699366d, -0.20397695888897652d, 1.0569715142428786d};
    }

    public static int adapt(int r11, int r12) {
        double[] r122 = rgb2oklch(rgb(r12));
        double[] r0 = rgb2oklch(rgb(r11));
        r0[2] = r122[2];
        if (Double.isNaN(r122[2]) == false) goto L5;
    L6:
        r0[1] = r122[1];
        if (Theme.isCurrentThemeDark() == true) goto L12;
        double r1 = r0[0];
        if (r1 >= 0.800000011920929d) goto L12;
        r0[0] = Utilities.clamp(r1 - 0.1d, 1.0d, 0.0d);
    L12:
        return ColorUtils.setAlphaComponent(rgb(oklch2rgb(r0)), Color.alpha(r11));
    L5:
        if (r122[1] >= 0.07999999821186066d) goto L12;
        goto L6
    }

    private static double[] multiply(double[] r16, double[] r17) {
        double r1 = r16[0];
        double r3 = r17[0];
        double r6 = r16[1];
        double r8 = r17[1];
        double r10 = r16[2];
        double r12 = r17[2];
        return new double[]{((r1 * r3) + (r6 * r8)) + (r10 * r12), ((r16[3] * r3) + (r16[4] * r8)) + (r16[5] * r12), ((r16[6] * r3) + (r16[7] * r8)) + (r16[8] * r12)};
    }

    public static double[] oklab2oklch(double[] r15) {
        double r1 = r15[0];
        double r4 = r15[1];
        double r7 = r15[2];
        double r9 = Math.sqrt(Math.pow(r4, 2.0d) + Math.pow(r7, 2.0d));
        if (Math.abs(r4) < 2.0E-4d) goto L5;
    L7:
        double r42 = ((((Math.atan2(r7, r4) * 180.0d) / 3.141592653589793d) % 360.0d) + 360.0d) % 360.0d;
    L9:
        return new double[]{r1, r9, r42};
    L5:
        if (Math.abs(r7) >= 2.0E-4d) goto L7;
        r42 = Double.NaN;
        goto L9
    }

    public static double[] oklab2xyz(double[] r5) {
        double[] r52 = multiply(LabtoLMS_M, r5);
        int r0 = 0;
    L4:
        if (r0 >= r52.length) goto L7;
        r52[r0] = Math.pow(r52[r0], 3.0d);
        r0 = r0 + 1;
        goto L4
    L7:
        return multiply(LMStoXYZ_M, r52);
    }

    public static double[] oklch2oklab(double[] r18) {
        double r1 = r18[0];
        double r4 = r18[1];
        double r7 = r18[2];
        double r14 = 0.0d;
        if (Double.isNaN(r7) == false) goto L5;
        double r16 = 0.0d;
    L7:
        if (Double.isNaN(r7) == true) goto L11;
        r14 = r4 * Math.sin((r7 * 3.141592653589793d) / 180.0d);
    L11:
        return new double[]{r1, r16, r14};
    L5:
        r16 = Math.cos((r7 * 3.141592653589793d) / 180.0d) * r4;
        goto L7
    }

    public static double[] oklch2rgb(double[] r0) {
        return xyz2rgbLinear(oklab2xyz(oklch2oklab(r0)));
    }

    public static int rgb(double[] r11) {
        return Color.rgb((int) Math.round(Utilities.clamp(r11[0], 1.0d, 0.0d) * 255.0d), (int) Math.round(Utilities.clamp(r11[1], 1.0d, 0.0d) * 255.0d), (int) Math.round(Utilities.clamp(r11[2], 1.0d, 0.0d) * 255.0d));
    }

    public static double[] rgb(int r8) {
        return new double[]{Color.red(r8) / 255.0d, Color.green(r8) / 255.0d, Color.blue(r8) / 255.0d};
    }

    public static double[] rgb2oklch(double[] r0) {
        return oklab2oklch(xyz2oklab(rgbLinear2xyz(r0)));
    }

    public static double[] rgbLinear2xyz(double[] r1) {
        return multiply(toXYZ_M, r1);
    }

    public static double[] xyz2oklab(double[] r3) {
        double[] r32 = multiply(XYZtoLMS_M, r3);
        int r0 = 0;
    L4:
        if (r0 >= r32.length) goto L7;
        r32[r0] = Math.cbrt(r32[r0]);
        r0 = r0 + 1;
        goto L4
    L7:
        return multiply(LMStoLab_M, r32);
    }

    public static double[] xyz2rgbLinear(double[] r1) {
        return multiply(fromXYZ_M, r1);
    }
}
