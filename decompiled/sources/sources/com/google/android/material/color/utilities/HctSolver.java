package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class HctSolver {
    static final double[] CRITICAL_PLANES = null;
    static final double[][] LINRGB_FROM_SCALED_DISCOUNT = null;
    static final double[][] SCALED_DISCOUNT_FROM_LINRGB = null;
    static final double[] Y_FROM_LINRGB = null;

    static {
        SCALED_DISCOUNT_FROM_LINRGB = new double[][]{new double[]{0.001200833568784504d, 0.002389694492170889d, 2.795742885861124E-4d}, new double[]{5.891086651375999E-4d, 0.0029785502573438758d, 3.270666104008398E-4d}, new double[]{1.0146692491640572E-4d, 5.364214359186694E-4d, 0.0032979401770712076d}};
        LINRGB_FROM_SCALED_DISCOUNT = new double[][]{new double[]{1373.2198709594231d, -1100.4251190754821d, -7.278681089101213d}, new double[]{-271.815969077903d, 559.6580465940733d, -32.46047482791194d}, new double[]{1.9622899599665666d, -57.173814538844006d, 308.7233197812385d}};
        Y_FROM_LINRGB = new double[]{0.2126d, 0.7152d, 0.0722d};
        CRITICAL_PLANES = new double[]{0.015176349177441876d, 0.045529047532325624d, 0.07588174588720938d, 0.10623444424209313d, 0.13658714259697685d, 0.16693984095186062d, 0.19729253930674434d, 0.2276452376616281d, 0.2579979360165119d, 0.28835063437139563d, 0.3188300904430532d, 0.350925934958123d, 0.3848314933096426d, 0.42057480301049466d, 0.458183274052838d, 0.4976837250274023d, 0.5391024159806381d, 0.5824650784040898d, 0.6277969426914107d, 0.6751227633498623d, 0.7244668422128921d, 0.775853049866786d, 0.829304845476233d, 0.8848452951698498d, 0.942497089126609d, 1.0022825574869039d, 1.0642236851973577d, 1.1283421258858297d, 1.1946592148522128d, 1.2631959812511864d, 1.3339731595349034d, 1.407011200216447d, 1.4823302800086415d, 1.5599503113873272d, 1.6398909516233677d, 1.7221716113234105d, 1.8068114625156377d, 1.8938294463134073d, 1.9832442801866852d, 2.075074464868551d, 2.1693382909216234d, 2.2660538449872063d, 2.36523901573795d, 2.4669114995532007d, 2.5710888059345764d, 2.6777882626779785d, 2.7870270208169257d, 2.898822059350997d, 3.0131901897720907d, 3.1301480604002863d, 3.2497121605402226d, 3.3718988244681087d, 3.4967242352587946d, 3.624204428461639d, 3.754355295633311d, 3.887192587735158d, 4.022731918402185d, 4.160988767090289d, 4.301978482107941d, 4.445716283538092d, 4.592217266055746d, 4.741496401646282d, 4.893568542229298d, 5.048448422192488d, 5.20615066083972d, 5.3666897647573375d, 5.5300801301023865d, 5.696336044816294d, 5.865471690767354d, 6.037501145825082d, 6.212438385869475d, 6.390297286737924d, 6.571091626112461d, 6.7548350853498045d, 6.941541251256611d, 7.131223617812143d, 7.323895587840543d, 7.5195704746346665d, 7.7182615035334345d, 7.919981813454504d, 8.124744458384042d, 8.332562408825165d, 8.543448553206703d, 8.757415699253682d, 8.974476575321063d, 9.194643831691977d, 9.417930041841839d, 9.644347703669503d, 9.873909240696694d, 10.106627003236781d, 10.342513269534024d, 10.58158024687427d, 10.8238400726681d, 11.069304815507364d, 11.317986476196008d, 11.569896988756009d, 11.825048221409341d, 12.083451977536606d, 12.345119996613247d, 12.610063955123938d, 12.878295467455942d, 13.149826086772048d, 13.42466730586372d, 13.702830557985108d, 13.984327217668513d, 14.269168601521828d, 14.55736596900856d, 14.848930523210871d, 15.143873411576273d, 15.44220572664832d, 15.743938506781891d, 16.04908273684337d, 16.35764934889634d, 16.66964922287304d, 16.985093187232053d, 17.30399201960269d, 17.62635644741625d, 17.95219714852476d, 18.281524751807332d, 18.614349837764564d, 18.95068293910138d, 19.290534541298456d, 19.633915083172692d, 19.98083495742689d, 20.331304511189067d, 20.685334046541502d, 21.042933821039977d, 21.404114048223256d, 21.76888489811322d, 22.137256497705877d, 22.50923893145328d, 22.884842241736916d, 23.264076429332462d, 23.6469514538663d, 24.033477234264016d, 24.42366364919083d, 24.817520537484558d, 25.21505769858089d, 25.61628489293138d, 26.021211842414342d, 26.429848230738664d, 26.842203703840827d, 27.258287870275353d, 27.678110301598522d, 28.10168053274597d, 28.529008062403893d, 28.96010235337422d, 29.39497283293396d, 29.83362889318845d, 30.276079891419332d, 30.722335150426627d, 31.172403958865512d, 31.62629557157785d, 32.08401920991837d, 32.54558406207592d, 33.010999283389665d, 33.4802739966603d, 33.953417292456834d, 34.430438229418264d, 34.911345834551085d, 35.39614910352207d, 35.88485700094671d, 36.37747846067349d, 36.87402238606382d, 37.37449765026789d, 37.87891309649659d, 38.38727753828926d, 38.89959975977785d, 39.41588851594697d, 39.93615253289054d, 40.460400508064545d, 40.98864111053629d, 41.520882981230194d, 42.05713473317016d, 42.597404951718396d, 43.141702194811224d, 43.6900349931913d, 44.24241185063697d, 44.798841244188324d, 45.35933162437017d, 45.92389141541209d, 46.49252901546552d, 47.065252796817916d, 47.64207110610409d, 48.22299226451468d, 48.808024568002054d, 49.3971762874833d, 49.9904556690408d, 50.587870934119984d, 51.189430279724725d, 51.79514187861014d, 52.40501387947288d, 53.0190544071392d, 53.637271562750364d, 54.259673423945976d, 54.88626804504493d, 55.517063457223934d, 56.15206766869424d, 56.79128866487574d, 57.43473440856916d, 58.08241284012621d, 58.734331877617365d, 59.39049941699807d, 60.05092333227251d, 60.715611475655585d, 61.38457167773311d, 62.057811747619894d, 62.7353394731159d, 63.417162620860914d, 64.10328893648692d, 64.79372614476921d, 65.48848194977529d, 66.18756403501224d, 66.89098006357258d, 67.59873767827808d, 68.31084450182222d, 69.02730813691093d, 69.74813616640164d, 70.47333615344107d, 71.20291564160104d, 71.93688215501312d, 72.67524319850172d, 73.41800625771542d, 74.16517879925733d, 74.9167682708136d, 75.67278210128072d, 76.43322770089146d, 77.1981124613393d, 77.96744375590167d, 78.74122893956174d, 79.51947534912904d, 80.30219030335869d, 81.08938110306934d, 81.88105503125999d, 82.67721935322541d, 83.4778813166706d, 84.28304815182372d, 85.09272707154808d, 85.90692527145302d, 86.72564993000343d, 87.54890820862819d, 88.3767072518277d, 89.2090541872801d, 90.04595612594655d, 90.88742016217518d, 91.73345337380438d, 92.58406282226491d, 93.43925555268066d, 94.29903859396902d, 95.16341895893969d, 96.03240364439274d, 96.9059996312159d, 97.78421388448044d, 98.6670533535366d, 99.55452497210776d};
    }

    private HctSolver() {
    }

    public static boolean areInCyclicOrder(double r0, double r2, double r4) {
        if (sanitizeRadians(r2 - r0) >= sanitizeRadians(r4 - r0)) goto L6;
        return true;
    L6:
        return false;
    }

    public static double[] bisectToLimit(double r19, double r21) {
        double[][] r0 = bisectToSegment(r19, r21);
        double[] r2 = r0[0];
        double r3 = hueOf(r2);
        double[] r02 = r0[1];
        int r6 = 0;
    L4:
        if (r6 >= 3) goto L25;
        double r7 = r2[r6];
        double r9 = r02[r6];
        if (r7 == r9) goto L23;
        if (r7 >= r9) goto L10;
        int r72 = criticalPlaneBelow(trueDelinearized(r7));
        int r8 = criticalPlaneAbove(trueDelinearized(r02[r6]));
    L11:
        int r13 = 0;
        double r17 = r3;
        int r32 = r72;
        int r4 = r8;
        double r73 = r17;
    L13:
        if (r13 >= 8) goto L22;
        if (Math.abs(r4 - r32) <= 1) goto L22;
        int r14 = (int) Math.floor((r32 + r4) / 2.0d);
        double[] r15 = setCoordinate(r2, CRITICAL_PLANES[r14], r02, r6);
        double r11 = hueOf(r15);
        if (areInCyclicOrder(r73, r21, r11) == false) goto L20;
        r4 = r14;
        r02 = r15;
    L21:
        r13 = r13 + 1;
        goto L13
    L20:
        r73 = r11;
        r32 = r14;
        r2 = r15;
    L22:
        r3 = r73;
        goto L23
    L10:
        r72 = criticalPlaneAbove(trueDelinearized(r7));
        r8 = criticalPlaneBelow(trueDelinearized(r02[r6]));
    L23:
        r6 = r6 + 1;
        goto L4
    L25:
        return midpoint(r2, r02);
    }

    public static double[][] bisectToSegment(double r19, double r21) {
        double[] r0 = {-1.0d, -1.0d, -1.0d};
        double[] r5 = r0;
        int r6 = 0;
        boolean r7 = false;
        double r9 = 0.0d;
        double r13 = 0.0d;
        boolean r8 = true;
    L4:
        if (r6 >= 12) goto L23;
        double[] r15 = nthVertex(r19, r6);
        if (r15[0] >= 0.0d) goto L8;
        double r17 = r13;
    L15:
        r13 = r17;
    L21:
        r6 = r6 + 1;
        goto L4
    L8:
        double r11 = hueOf(r15);
        if (r7 == true) goto L11;
        r7 = true;
        r9 = r11;
        r13 = r9;
        r0 = r15;
        r5 = r0;
        goto L21
    L11:
        if (r8 == true) goto L16;
        boolean r16 = areInCyclicOrder(r9, r11, r13);
        r17 = r13;
        r13 = r11;
        if (r16 == false) goto L15;
    L18:
        if (areInCyclicOrder(r9, r21, r13) == false) goto L20;
        r8 = false;
        r5 = r15;
        goto L21
    L20:
        r8 = false;
        r9 = r13;
        r0 = r15;
        goto L15
    L16:
        r17 = r13;
        r13 = r11;
        goto L18
    L23:
        return new double[][]{r0, r5};
    }

    public static double chromaticAdaptation(double r4) {
        double r0 = Math.pow(Math.abs(r4), 0.42d);
        return ((MathUtils.signum(r4) * 400.0d) * r0) / (r0 + 27.13d);
    }

    public static int criticalPlaneAbove(double r2) {
        return (int) Math.ceil(r2 - 0.5d);
    }

    public static int criticalPlaneBelow(double r2) {
        return (int) Math.floor(r2 - 0.5d);
    }

    public static int findResultByJ(double r35, double r37, double r39) {
        double r0 = Math.sqrt(r39) * 11.0d;
        ViewingConditions r4 = ViewingConditions.DEFAULT;
        double r7 = 1.0d;
        double r5 = 1.0d / Math.pow(1.64d - Math.pow(0.29d, r4.getN()), 0.73d);
        double r9 = 2.0d;
        double r11 = ((((Math.cos(r35 + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * r4.getNc()) * r4.getNcb();
        double r13 = Math.sin(r35);
        double r15 = Math.cos(r35);
        int r2 = 0;
    L4:
        if (r2 >= 5) goto L38;
        double r22 = r7;
        double r72 = r0 / 100.0d;
        if (r37 != 0.0d) goto L8;
    L12:
        double r24 = 0.0d;
    L11:
        double r26 = r9;
        double r352 = r0;
        double r02 = Math.pow(r24 * r5, 1.1111111111111112d);
        double r92 = (r4.getAw() * Math.pow(r72, (r22 / r4.getC()) / r4.getZ())) / r4.getNbb();
        double r03 = (((0.305d + r92) * 23.0d) * r02) / (((23.0d * r11) + ((r02 * 11.0d) * r15)) + ((108.0d * r02) * r13));
        double r73 = r03 * r15;
        double r04 = r03 * r13;
        double r93 = r92 * 460.0d;
        double[] r3 = MathUtils.matrixMultiply(new double[]{inverseChromaticAdaptation(((r93 + (451.0d * r73)) + (288.0d * r04)) / 1403.0d), inverseChromaticAdaptation(((r93 - (891.0d * r73)) - (261.0d * r04)) / 1403.0d), inverseChromaticAdaptation(((r93 - (r73 * 220.0d)) - (r04 * 6300.0d)) / 1403.0d)}, LINRGB_FROM_SCALED_DISCOUNT);
        double r74 = r3[0];
        if (r74 < 0.0d) goto L38;
        double r94 = r3[1];
        if (r94 < 0.0d) goto L38;
        double r242 = r3[2];
        if (r242 < 0.0d) goto L38;
        double[] r28 = Y_FROM_LINRGB;
        double r29 = ((r28[0] * r74) + (r28[1] * r94)) + (r28[2] * r242);
        if (r29 <= 0.0d) goto L22;
        if (r2 == 4) goto L30;
        double r75 = r29 - r39;
        if (Math.abs(r75) < 0.002d) goto L30;
        r0 = r352 - ((r75 * r352) / (r29 * r26));
        r2 = r2 + 1;
        r7 = r22;
        r9 = r26;
    L30:
        if (r3[0] > 100.01d) goto L38;
        if (r3[1] > 100.01d) goto L38;
        if (r3[2] > 100.01d) goto L38;
        return ColorUtils.argbFromLinrgb(r3);
    L22:
        return 0;
    L8:
        if (r0 == 0.0d) goto L12;
        r24 = r37 / Math.sqrt(r72);
    L38:
        return 0;
    }

    public static double hueOf(double[] r12) {
        double[] r122 = MathUtils.matrixMultiply(r12, SCALED_DISCOUNT_FROM_LINRGB);
        double r0 = chromaticAdaptation(r122[0]);
        double r2 = chromaticAdaptation(r122[1]);
        double r4 = chromaticAdaptation(r122[2]);
        double r8 = (((r0 * 11.0d) + ((-12.0d) * r2)) + r4) / 11.0d;
        return Math.atan2(((r0 + r2) - (r4 * 2.0d)) / 9.0d, r8);
    }

    public static double intercept(double r0, double r2, double r4) {
        return (r2 - r0) / (r4 - r0);
    }

    public static double inverseChromaticAdaptation(double r6) {
        double r0 = Math.abs(r6);
        return MathUtils.signum(r6) * Math.pow(Math.max(0.0d, (27.13d * r0) / (400.0d - r0)), 2.380952380952381d);
    }

    public static boolean isBounded(double r2) {
        if (0.0d <= r2) goto L5;
        return false;
    L5:
        if (r2 > 100.0d) goto L10;
        return true;
    L10:
        return false;
    }

    public static double[] lerpPoint(double[] r11, double r12, double[] r14) {
        double r1 = r11[0];
        double r13 = r1 + ((r14[0] - r1) * r12);
        double r4 = r11[1];
        double r42 = r4 + ((r14[1] - r4) * r12);
        double r7 = r11[2];
        return new double[]{r13, r42, r7 + ((r14[2] - r7) * r12)};
    }

    public static double[] midpoint(double[] r11, double[] r12) {
        return new double[]{(r11[0] + r12[0]) / 2.0d, (r11[1] + r12[1]) / 2.0d, (r11[2] + r12[2]) / 2.0d};
    }

    public static double[] nthVertex(double r17, int r19) {
        double[] r1 = Y_FROM_LINRGB;
        double r3 = r1[0];
        double r6 = r1[1];
        double r9 = r1[2];
        double r11 = 100.0d;
        if ((r19 % 4) > 1) goto L5;
        double r15 = 0.0d;
    L7:
        if ((r19 % 2) != 0) goto L10;
        r11 = 0.0d;
    L10:
        if (r19 >= 4) goto L18;
        double r62 = ((r17 - (r6 * r15)) - (r9 * r11)) / r3;
        if (isBounded(r62) == false) goto L16;
        return new double[]{r62, r15, r11};
    L16:
        return new double[]{-1.0d, -1.0d, -1.0d};
    L18:
        if (r19 >= 8) goto L25;
        double r32 = ((r17 - (r3 * r11)) - (r9 * r15)) / r6;
        if (isBounded(r32) == false) goto L24;
        return new double[]{r11, r32, r15};
    L24:
        return new double[]{-1.0d, -1.0d, -1.0d};
    L25:
        double r33 = ((r17 - (r3 * r15)) - (r6 * r11)) / r9;
        if (isBounded(r33) == false) goto L30;
        return new double[]{r15, r11, r33};
    L30:
        return new double[]{-1.0d, -1.0d, -1.0d};
    L5:
        r15 = 100.0d;
        goto L7
    }

    public static double sanitizeRadians(double r2) {
        return (r2 + 25.132741228718345d) % 6.283185307179586d;
    }

    public static double[] setCoordinate(double[] r6, double r7, double[] r9, int r10) {
        return lerpPoint(r6, intercept(r6[r10], r7, r9[r10]), r9);
    }

    public static Cam16 solveToCam(double r0, double r2, double r4) {
        return Cam16.fromInt(solveToInt(r0, r2, r4));
    }

    public static int solveToInt(double r8, double r10, double r12) {
        if (r10 < 1.0E-4d) goto L15;
        if (r12 < 1.0E-4d) goto L15;
        if (r12 > 99.9999d) goto L15;
        double r2 = (MathUtils.sanitizeDegreesDouble(r8) / 180.0d) * 3.141592653589793d;
        double r6 = ColorUtils.yFromLstar(r12);
        int r82 = findResultByJ(r2, r10, r6);
        if (r82 == 0) goto L13;
        return r82;
    L13:
        return ColorUtils.argbFromLinrgb(bisectToLimit(r6, r2));
    L15:
        return ColorUtils.argbFromLstar(r12);
    }

    public static double trueDelinearized(double r2) {
        double r22 = r2 / 100.0d;
        if (r22 > 0.0031308d) goto L5;
        double r23 = r22 * 12.92d;
    L7:
        return r23 * 255.0d;
    L5:
        r23 = (Math.pow(r22, 0.4166666666666667d) * 1.055d) - 0.055d;
        goto L7
    }
}
