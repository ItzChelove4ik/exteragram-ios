package org.telegram.messenger.time;

import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes3.dex */
public class SunDate {
    private static final double DEGRAD = 0.017453292519943295d;
    private static final double INV360 = 0.002777777777777778d;
    private static final double RADEG = 57.29577951308232d;

    public SunDate() {
    }

    private static double GMST0(double r2) {
        return revolution((r2 * 0.985647352d) + 818.9874d);
    }

    private static double acosd(double r2) {
        return Math.acos(r2) * RADEG;
    }

    private static double atan2d(double r2, double r4) {
        return Math.atan2(r2, r4) * RADEG;
    }

    public static int[] calculateSunriseSunset(double r11, double r13) {
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(System.currentTimeMillis());
        double[] r9 = new double[2];
        sunRiseSetForYear(r0.get(1), r0.get(2) + 1, r0.get(5), r13, r11, r9);
        int r112 = (TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000) / 60;
        int r12 = ((int) (r9[0] * 60.0d)) + r112;
        int r132 = ((int) (r9[1] * 60.0d)) + r112;
        if (r12 >= 0) goto L5;
        r12 = r12 + 1440;
    L7:
        if (r132 >= 0) goto L9;
    L8:
        r132 = r132 + 1440;
    L12:
        return new int[]{r12, r132};
    L9:
        if (r132 <= 1440) goto L12;
    L5:
        if (r12 <= 1440) goto L7;
        r12 = r12 - 1440;
        goto L7
    }

    private static double cosd(double r2) {
        return Math.cos(r2 * DEGRAD);
    }

    private static long days_since_2000_Jan_0(int r4, int r5, int r6) {
        return ((((r4 * 367) - (((r4 + ((r5 + 9) / 12)) * 7) / 4)) + ((r5 * 275) / 9)) + r6) - 730530;
    }

    private static double rev180(double r4) {
        return r4 - (Math.floor((INV360 * r4) + 0.5d) * 360.0d);
    }

    private static double revolution(double r4) {
        return r4 - (Math.floor(INV360 * r4) * 360.0d);
    }

    private static double sind(double r2) {
        return Math.sin(r2 * DEGRAD);
    }

    private static int sunRiseSetForYear(int r11, int r12, int r13, double r14, double r16, double[] r18) {
        return sunRiseSetHelperForYear(r11, r12, r13, r14, r16, -0.5833333333333334d, 1, r18);
    }

    private static int sunRiseSetHelperForYear(int r17, int r18, int r19, double r20, double r22, double r24, int r26, double[] r27) {
        double[] r1 = new double[1];
        double[] r2 = new double[1];
        double[] r3 = new double[1];
        double r4 = (days_since_2000_Jan_0(r17, r18, r19) + 0.5d) - (r20 / 360.0d);
        double r6 = revolution((GMST0(r4) + 180.0d) + r20);
        sun_RA_decAtDay(r4, r1, r2, r3);
        double r9 = 12.0d;
        double r5 = 12.0d - (rev180(r6 - r1[0]) / 15.0d);
        double r11 = 0.2666d / r3[0];
        if (r26 == 0) goto L5;
        double r112 = r24 - r11;
    L6:
        double r113 = (sind(r112) - (sind(r22) * sind(r2[0]))) / (cosd(r22) * cosd(r2[0]));
        if (r113 < 1.0d) goto L10;
        int r12 = -1;
        r9 = 0.0d;
    L13:
        r27[0] = r5 - r9;
        r27[1] = r5 + r9;
        return r12;
    L10:
        if (r113 > (-1.0d)) goto L12;
        r12 = 1;
        goto L13
    L12:
        r9 = acosd(r113) / 15.0d;
        r12 = 0;
        goto L13
    L5:
        r112 = r24;
        goto L6
    }

    private static void sun_RA_decAtDay(double r8, double[] r10, double[] r11, double[] r12) {
        double[] r0 = new double[1];
        sunposAtDay(r8, r0, r12);
        double r2 = r12[0] * cosd(r0[0]);
        double r4 = r12[0] * sind(r0[0]);
        double r6 = 23.4393d - (r8 * 3.563E-7d);
        double r82 = cosd(r6) * r4;
        double r42 = r4 * sind(r6);
        r10[0] = atan2d(r82, r2);
        r11[0] = atan2d(r42, Math.sqrt((r2 * r2) + (r82 * r82)));
    }

    private static void sunposAtDay(double r10, double[] r12, double[] r13) {
        double r0 = revolution((0.9856002585d * r10) + 356.047d);
        double r2 = (4.70935E-5d * r10) + 282.9404d;
        double r4 = 0.016709d - (r10 * 1.151E-9d);
        double r102 = (((RADEG * r4) * sind(r0)) * ((cosd(r0) * r4) + 1.0d)) + r0;
        double r02 = cosd(r102) - r4;
        double r42 = Math.sqrt(1.0d - (r4 * r4)) * sind(r102);
        r13[0] = Math.sqrt((r02 * r02) + (r42 * r42));
        double r103 = atan2d(r42, r02) + r2;
        r12[0] = r103;
        if (r103 < 360.0d) goto L6;
        r12[0] = r103 - 360.0d;
        return;
    }

    private static double tand(double r2) {
        return Math.tan(r2 * DEGRAD);
    }
}
