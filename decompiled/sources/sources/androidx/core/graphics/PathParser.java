package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import androidx.savedstate.Recreator$$ExternalSyntheticBUOutline0;
import c.a$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class PathParser {

    public static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        public ExtractFloatResult() {
        }
    }

    public static class PathDataNode {
        private final float[] mParams;
        private char mType;

        public PathDataNode(char r1, float[] r2) {
            this.mType = r1;
            this.mParams = r2;
        }

        public PathDataNode(PathDataNode r3) {
            this.mType = r3.mType;
            float[] r32 = r3.mParams;
            this.mParams = PathParser.copyOfRange(r32, 0, r32.length);
        }

        public static /* synthetic */ char access$000(PathDataNode r0) {
            return r0.mType;
        }

        public static /* synthetic */ char access$002(PathDataNode r0, char r1) {
            r0.mType = r1;
            return r1;
        }

        public static /* synthetic */ float[] access$100(PathDataNode r0) {
            return r0.mParams;
        }

        public static /* synthetic */ void access$200(Path r0, float[] r1, char r2, char r3, float[] r4) {
            addCommand(r0, r1, r2, r3, r4);
        }

        private static void addCommand(Path r26, float[] r27, char r28, char r29, float[] r30) {
            Path r0 = r26;
            boolean r12 = false;
            float r1 = r27[0];
            boolean r13 = true;
            float r2 = r27[1];
            char r14 = 2;
            float r3 = r27[2];
            char r15 = 3;
            float r4 = r27[3];
            float r5 = r27[4];
            float r6 = r27[5];
            switch(r29) {
                case 65: goto L10;
                case 67: goto L8;
                case 72: goto L7;
                case 76: goto L4;
                case 77: goto L4;
                case 81: goto L6;
                case 83: goto L6;
                case 84: goto L4;
                case 86: goto L7;
                case 90: goto L5;
                case 97: goto L10;
                case 99: goto L8;
                case 104: goto L7;
                case 108: goto L4;
                case 109: goto L4;
                case 113: goto L6;
                case 115: goto L6;
                case 116: goto L4;
                case 118: goto L7;
                case 122: goto L5;
                default: goto L4;
            };
        L4:
            int r18 = 2;
        L11:
            float r7 = r1;
            float r8 = r2;
            float r19 = r5;
            float r20 = r6;
            int r9 = 0;
            char r16 = r28;
        L13:
            if (r9 >= r30.length) goto L128;
            if (r29 != 'A') goto L17;
            float r17 = r7;
            float r22 = r8;
            boolean r21 = r12;
            boolean r222 = r13;
            char r23 = r14;
            char r24 = r15;
            int r142 = r9;
            int r122 = r142 + 5;
            float r32 = r30[r122];
            int r132 = r142 + 6;
            float r42 = r30[r132];
            float r52 = r30[r142];
            float r62 = r30[r142 + 1];
            float r72 = r30[r142 + 2];
            if (r30[r142 + 3] == 0.0f) goto L120;
            float r02 = 0.0f;
            boolean r82 = r222;
        L122:
            if (r30[r142 + 4] == r02) goto L125;
            boolean r92 = r222;
        L126:
            drawArc(r26, r17, r22, r32, r42, r52, r62, r72, r82, r92);
            r3 = r30[r122];
            r7 = r3;
            r4 = r30[r132];
            r8 = r4;
        L127:
            r9 = r142 + r18;
            r0 = r26;
            r16 = r29;
            r12 = r21;
            r13 = r222;
            r14 = r23;
            r15 = r24;
            goto L13
        L125:
            r92 = r21;
            goto L126
        L120:
            r02 = 0.0f;
            r82 = r21;
            goto L122
        L17:
            if (r29 != 'C') goto L19;
            r21 = r12;
            r222 = r13;
            r23 = r14;
            r24 = r15;
            r142 = r9;
            int r93 = r142 + 2;
            int r73 = r142 + 3;
            int r83 = r142 + 4;
            int r123 = r142 + 5;
            r0.cubicTo(r30[r142], r30[r142 + 1], r30[r93], r30[r73], r30[r83], r30[r123]);
            float r03 = r30[r83];
            float r110 = r30[r123];
            float r25 = r30[r93];
            float r33 = r30[r73];
            r7 = r03;
            r8 = r110;
            r4 = r33;
            r3 = r25;
            goto L127
        L19:
            if (r29 != 'H') goto L21;
            r21 = r12;
            r222 = r13;
            r23 = r14;
            r24 = r15;
            r142 = r9;
            r0.lineTo(r30[r142], r8);
            r7 = r30[r142];
            goto L127
        L21:
            if (r29 == 'Q') goto L114;
            r21 = r12;
            if (r29 != 'V') goto L25;
            r222 = r13;
            r23 = r14;
            r24 = r15;
            r142 = r9;
            r0.lineTo(r7, r30[r142]);
            float r210 = r30[r142];
        L81:
            r8 = r210;
            goto L127
        L25:
            if (r29 != 'a') goto L27;
            r222 = r13;
            r23 = r14;
            r24 = r15;
            int r124 = r9 + 5;
            float r34 = r30[r124] + r7;
            int r133 = r9 + 6;
            float r43 = r30[r133] + r8;
            float r53 = r30[r9];
            float r63 = r30[r9 + 1];
            float r04 = r30[r9 + 2];
            if (r30[r9 + 3] == 0.0f) goto L106;
            float r111 = 0.0f;
            float r211 = r8;
            boolean r84 = r222;
        L107:
            r142 = r9;
            if (r30[r9 + 4] == r111) goto L111;
            boolean r94 = r222;
        L110:
            float r112 = r7;
            drawArc(r26, r112, r211, r34, r43, r53, r63, r04, r84, r94);
            r7 = r112 + r30[r124];
            r8 = r211 + r30[r133];
            r3 = r7;
            r4 = r8;
            goto L127
        L111:
            r94 = r21;
            goto L110
        L106:
            r111 = 0.0f;
            r211 = r8;
            r84 = r21;
            goto L107
        L27:
            if (r29 == 'c') goto L102;
            r222 = r13;
            if (r29 != 'h') goto L31;
            r23 = r14;
            r24 = r15;
            r0.rLineTo(r30[r9], 0.0f);
            r7 = r7 + r30[r9];
        L48:
            r142 = r9;
            goto L127
        L31:
            if (r29 == 'q') goto L100;
            r23 = r14;
            if (r29 != 'v') goto L35;
            r24 = r15;
            r0.rLineTo(0.0f, r30[r9]);
            float r113 = r30[r9];
        L74:
            r8 = r8 + r113;
            goto L48
        L35:
            if (r29 != 'L') goto L37;
            r24 = r15;
            int r212 = r9 + 1;
            r0.lineTo(r30[r9], r30[r212]);
            float r114 = r30[r9];
            float r213 = r30[r212];
        L96:
            r7 = r114;
            r8 = r213;
            goto L48
        L37:
            if (r29 == 'M') goto L93;
            r24 = r15;
            if (r29 != 'S') goto L41;
            if (r16 == 'c') goto L90;
            if (r16 == 's') goto L90;
            if (r16 == 'C') goto L90;
            if (r16 == 'S') goto L90;
        L89:
            float r115 = r7;
            float r214 = r8;
            int r74 = r9 + 1;
            int r85 = r9 + 2;
            int r125 = r9 + 3;
            r0.cubicTo(r115, r214, r30[r9], r30[r74], r30[r85], r30[r125]);
            float r116 = r30[r9];
            float r215 = r30[r74];
            r7 = r30[r85];
            r8 = r30[r125];
            r142 = r9;
        L92:
            r3 = r116;
            r4 = r215;
        L90:
            r7 = (r7 * 2.0f) - r3;
            r8 = (r8 * 2.0f) - r4;
            goto L89
        L41:
            if (r29 != 'T') goto L43;
            if (r16 == 'q') goto L79;
            if (r16 == 't') goto L79;
            if (r16 == 'Q') goto L79;
            if (r16 == 'T') goto L79;
        L80:
            int r216 = r9 + 1;
            r0.quadTo(r7, r8, r30[r9], r30[r216]);
            float r117 = r30[r9];
            r210 = r30[r216];
            r3 = r7;
            r4 = r8;
            r142 = r9;
            r7 = r117;
        L79:
            r7 = (r7 * 2.0f) - r3;
            r8 = (r8 * 2.0f) - r4;
            goto L80
        L43:
            if (r29 != 'l') goto L45;
            int r217 = r9 + 1;
            r0.rLineTo(r30[r9], r30[r217]);
            r7 = r7 + r30[r9];
            r113 = r30[r217];
            goto L74
        L45:
            if (r29 == 'm') goto L68;
            if (r29 == 's') goto L57;
            if (r29 != 't') goto L48;
            if (r16 == 'q') goto L55;
            if (r16 == 't') goto L55;
            if (r16 == 'Q') goto L55;
            if (r16 == 'T') goto L55;
            float r118 = 0.0f;
            float r54 = 0.0f;
        L56:
            int r35 = r9 + 1;
            r0.rQuadTo(r54, r118, r30[r9], r30[r35]);
            float r55 = r54 + r7;
            float r119 = r118 + r8;
            r7 = r7 + r30[r9];
            r8 = r8 + r30[r35];
            r4 = r119;
            r3 = r55;
        L55:
            r54 = r7 - r3;
            r118 = r8 - r4;
            goto L56
        L57:
            if (r16 == 'c') goto L65;
            if (r16 == 's') goto L65;
            if (r16 == 'C') goto L65;
            if (r16 == 'S') goto L65;
            float r120 = 0.0f;
            float r218 = 0.0f;
        L66:
            int r126 = r9 + 1;
            int r134 = r9 + 2;
            int r143 = r9 + 3;
            r0.rCubicTo(r120, r218, r30[r9], r30[r126], r30[r134], r30[r143]);
            float r121 = r30[r9] + r7;
            float r219 = r30[r126] + r8;
            r7 = r7 + r30[r134];
            float r36 = r30[r143];
        L67:
            r8 = r8 + r36;
            r3 = r121;
            r4 = r219;
        L65:
            r218 = r8 - r4;
            r120 = r7 - r3;
            goto L66
        L68:
            float r127 = r30[r9];
            r7 = r7 + r127;
            float r220 = r30[r9 + 1];
            r8 = r8 + r220;
            if (r9 <= 0) goto L71;
            r0.rLineTo(r127, r220);
            goto L48
        L71:
            r0.rMoveTo(r127, r220);
            r19 = r7;
        L72:
            r20 = r8;
            goto L48
        L93:
            r24 = r15;
            r114 = r30[r9];
            r213 = r30[r9 + 1];
            if (r9 <= 0) goto L97;
            r0.lineTo(r114, r213);
            goto L96
        L97:
            r0.moveTo(r114, r213);
            r7 = r114;
            r19 = r7;
            r8 = r213;
            goto L72
        L100:
            r23 = r14;
            r24 = r15;
            int r221 = r9 + 1;
            int r44 = r9 + 2;
            int r64 = r9 + 3;
            r0.rQuadTo(r30[r9], r30[r221], r30[r44], r30[r64]);
            r121 = r30[r9] + r7;
            r219 = r30[r221] + r8;
            r7 = r7 + r30[r44];
            r36 = r30[r64];
            goto L67
        L102:
            r222 = r13;
            r23 = r14;
            r24 = r15;
            int r128 = r9 + 2;
            int r135 = r9 + 3;
            int r144 = r9 + 4;
            int r152 = r9 + 5;
            r0.rCubicTo(r30[r9], r30[r9 + 1], r30[r128], r30[r135], r30[r144], r30[r152]);
            float r05 = r30[r128] + r7;
            float r129 = r30[r135] + r8;
            r7 = r7 + r30[r144];
            r8 = r8 + r30[r152];
            r3 = r05;
            r4 = r129;
            goto L48
        L114:
            r21 = r12;
            r222 = r13;
            r23 = r14;
            r24 = r15;
            r142 = r9;
            int r95 = r142 + 1;
            int r37 = r142 + 2;
            int r56 = r142 + 3;
            r0.quadTo(r30[r142], r30[r95], r30[r37], r30[r56]);
            r116 = r30[r142];
            r215 = r30[r95];
            r7 = r30[r37];
            r8 = r30[r56];
            goto L92
        L128:
            r27[r12 ? 1 : 0] = r7;
            r27[r13 ? 1 : 0] = r8;
            r27[r14] = r3;
            r27[r15] = r4;
            r27[4] = r19;
            r27[5] = r20;
            return;
        L5:
            r0.close();
            r0.moveTo(r5, r6);
            r1 = r5;
            r3 = r1;
            r2 = r6;
            r4 = r2;
            goto L4
        L6:
            r18 = 4;
            goto L11
        L7:
            r18 = 1;
            goto L11
        L8:
            int r75 = 6;
        L9:
            r18 = r75;
            goto L11
        L10:
            r75 = 7;
            goto L9
        }

        private static void arcToBezier(Path r46, double r47, double r49, double r51, double r53, double r55, double r57, double r59, double r61, double r63) {
            double r0 = r51;
            int r4 = (int) Math.ceil(Math.abs((r63 * 4.0d) / 3.141592653589793d));
            double r5 = Math.cos(r59);
            double r7 = Math.sin(r59);
            double r9 = Math.cos(r61);
            double r11 = Math.sin(r61);
            double r13 = -r0;
            double r15 = r13 * r5;
            double r19 = r53 * r7;
            double r17 = (r15 * r11) - (r19 * r9);
            double r132 = r13 * r7;
            double r21 = r53 * r5;
            double r112 = (r11 * r132) + (r9 * r21);
            double r92 = r63 / r4;
            double r25 = r112;
            double r27 = r17;
            int r2 = 0;
            double r113 = r55;
            double r172 = r57;
            double r23 = r61;
        L3:
            if (r2 >= r4) goto L5;
            double r31 = r23 + r92;
            double r33 = Math.sin(r31);
            double r35 = Math.cos(r31);
            double r02 = (r47 + ((r0 * r5) * r35)) - (r19 * r33);
            int r532 = r2;
            double r22 = (r49 + ((r51 * r7) * r35)) + (r21 * r33);
            double r37 = (r15 * r33) - (r19 * r35);
            double r332 = (r33 * r132) + (r35 * r21);
            double r232 = r31 - r23;
            double r352 = Math.tan(r232 / 2.0d);
            double r233 = (Math.sin(r232) * (Math.sqrt(((r352 * 3.0d) * r352) + 4.0d) - 1.0d)) / 3.0d;
            double r114 = r113 + (r27 * r233);
            int r272 = r4;
            r46.rLineTo(0.0f, 0.0f);
            r46.cubicTo((float) r114, (float) (r172 + (r25 * r233)), (float) (r02 - (r233 * r37)), (float) (r22 - (r233 * r332)), (float) r02, (float) r22);
            r7 = r7;
            r92 = r92;
            r113 = r02;
            r132 = r132;
            r23 = r31;
            r25 = r332;
            r5 = r5;
            r0 = r51;
            r172 = r22;
            r2 = r532 + 1;
            r4 = r272;
            r27 = r37;
            goto L3
        }

        private static void drawArc(Path r43, float r44, float r45, float r46, float r47, float r48, float r49, float r50, boolean r51, boolean r52) {
            double r19 = Math.toRadians(r50);
            double r4 = Math.cos(r19);
            double r8 = Math.sin(r19);
            double r10 = r44;
            double r14 = r45;
            double r11 = r48;
            double r13 = ((r10 * r4) + (r14 * r8)) / r11;
            double r132 = r49;
            double r0 = (((-r44) * r8) + (r14 * r4)) / r132;
            double r02 = r47;
            double r25 = ((r46 * r4) + (r02 * r8)) / r11;
            double r03 = (((-r46) * r8) + (r02 * r4)) / r132;
            double r27 = r13 - r25;
            double r29 = r0 - r03;
            double r31 = (r13 + r25) / 2.0d;
            double r35 = (r0 + r03) / 2.0d;
            double r04 = (r27 * r27) + (r29 * r29);
            if (r04 != 0.0d) goto L6;
            Log.w("PathParser", " Points are coincident");
            return;
        L6:
            double r37 = (1.0d / r04) - 0.25d;
            if (r37 >= 0.0d) goto L10;
            Log.w("PathParser", "Points are too far apart " + r04);
            float r05 = (float) (Math.sqrt(r04) / 1.99999d);
            drawArc(r43, r44, r45, r46, r47, r48 * r05, r05 * r49, r50, r51, r52);
            return;
        L10:
            double r1 = Math.sqrt(r37);
            double r272 = r27 * r1;
            double r12 = r1 * r29;
            if (r51 != r52) goto L13;
            double r312 = r31 - r12;
            double r352 = r35 + r272;
        L14:
            double r21 = Math.atan2(r0 - r352, r13 - r312);
            double r15 = Math.atan2(r03 - r352, r25 - r312) - r21;
            if (r15 < 0.0d) goto L17;
            boolean r6 = true;
        L18:
            if (r52 == r6) goto L24;
            if (r15 <= 0.0d) goto L23;
            r15 = r15 - 6.283185307179586d;
            goto L24
        L23:
            r15 = r15 + 6.283185307179586d;
        L24:
            double r313 = r312 * r11;
            double r353 = r352 * r132;
            arcToBezier(r43, (r313 * r4) - (r353 * r8), (r313 * r8) + (r353 * r4), r11, r132, r10, r14, r19, r21, r15);
            return;
        L17:
            r6 = false;
            goto L18
        L13:
            r312 = r31 + r12;
            r352 = r35 - r272;
            goto L14
        }

        @Deprecated
        public static void nodesToPath(PathDataNode[] r0, Path r1) {
            PathParser.nodesToPath(r0, r1);
        }
    }

    private static void addNode(ArrayList<PathDataNode> r1, char r2, float[] r3) {
        r1.add(new PathDataNode(r2, r3));
    }

    public static boolean canMorph(PathDataNode[] r4, PathDataNode[] r5) {
        if (r4 == null) goto L21;
        if (r5 == null) goto L21;
        if (r4.length == r5.length) goto L9;
        return false;
    L9:
        int r1 = 0;
    L11:
        if (r1 >= r4.length) goto L19;
        if (PathDataNode.access$000(r4[r1]) != PathDataNode.access$000(r5[r1])) goto L18;
        if (PathDataNode.access$100(r4[r1]).length != PathDataNode.access$100(r5[r1]).length) goto L18;
        r1 = r1 + 1;
    L18:
        return false;
    L19:
        return true;
    L21:
        return false;
    }

    public static float[] copyOfRange(float[] r2, int r3, int r4) {
        if (r3 > r4) goto L10;
        int r0 = r2.length;
        if (r3 < 0) goto L9;
        if (r3 > r0) goto L9;
        int r42 = r4 - r3;
        int r02 = Math.min(r42, r0 - r3);
        float[] r43 = new float[r42];
        System.arraycopy(r2, r3, r43, 0, r02);
        return r43;
    L9:
        throw new ArrayIndexOutOfBoundsException();
    L10:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static PathDataNode[] createNodesFromPathData(String r7) {
        ArrayList r0 = new ArrayList();
        int r4 = 0;
        int r3 = 1;
    L4:
        if (r3 >= r7.length()) goto L10;
        int r32 = nextStart(r7, r3);
        String r42 = r7.substring(r4, r32).trim();
        if (r42.isEmpty() == true) goto L8;
        addNode(r0, r42.charAt(0), getFloats(r42));
    L8:
        r4 = r32;
        r3 = r32 + 1;
        goto L4
    L10:
        if ((r3 - r4) != 1) goto L15;
        if (r4 >= r7.length()) goto L15;
        addNode(r0, r7.charAt(r4), new float[0]);
    L15:
        return (PathDataNode[]) r0.toArray(new PathDataNode[0]);
    }

    public static Path createPathFromPathData(String r2) {
        Path r0 = new Path();
        PathDataNode.nodesToPath(createNodesFromPathData(r2), r0);     // Catch: RuntimeException -> L5
        return r0;
    L5:
        e = move-exception;
        a$$ExternalSyntheticBUOutline0.m("Error in parsing ", r2, e);
        return null;
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] r4) {
        PathDataNode[] r0 = new PathDataNode[r4.length];
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L6;
        r0[r1] = new PathDataNode(r4[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static void extract(String r8, int r9, ExtractFloatResult r10) {
        r10.mEndWithNegOrDot = false;
        int r1 = r9;
        boolean r2 = false;
        boolean r3 = false;
        boolean r4 = false;
    L4:
        if (r1 >= r8.length()) goto L25;
        char r5 = r8.charAt(r1);
        if (r5 != ' ') goto L8;
    L16:
        r2 = false;
        r4 = true;
    L22:
        if (r4 == true) goto L25;
        r1 = r1 + 1;
        goto L4
    L8:
        if (r5 != 'E') goto L10;
    L21:
        r2 = true;
        goto L22
    L10:
        if (r5 == 'e') goto L21;
        switch(r5) {
            case 44: goto L16;
            case 45: goto L17;
            case 46: goto L13;
            default: goto L20;
        };
    L13:
        if (r3 == true) goto L15;
        r2 = false;
        r3 = true;
        goto L22
    L15:
        r10.mEndWithNegOrDot = true;
        goto L16
    L17:
        if (r1 == r9) goto L20;
        if (r2 == true) goto L20;
        r10.mEndWithNegOrDot = true;
    L20:
        r2 = false;
    L25:
        r10.mEndPosition = r1;
    }

    private static float[] getFloats(String r8) {
        if (r8.charAt(0) == 'z') goto L23;
        if (r8.charAt(0) == 'Z') goto L23;
        float[] r1 = new float[r8.length()];     // Catch: NumberFormatException -> L12
        ExtractFloatResult r2 = new ExtractFloatResult();     // Catch: NumberFormatException -> L12
        int r3 = r8.length();     // Catch: NumberFormatException -> L12
        int r4 = 1;
        int r5 = 0;
    L8:
        if (r4 >= r3) goto L18;
        extract(r8, r4, r2);     // Catch: NumberFormatException -> L12
        int r6 = r2.mEndPosition;     // Catch: NumberFormatException -> L12
        if (r4 >= r6) goto L15;
        r1[r5] = Float.parseFloat(r8.substring(r4, r6));     // Catch: NumberFormatException -> L12
        r5 = r5 + 1;     // Catch: NumberFormatException -> L12
    L15:
        if (r2.mEndWithNegOrDot == true) goto L16;
        r4 = r6 + 1;     // Catch: NumberFormatException -> L12
        goto L8
    L16:
        r4 = r6;
        goto L8
    L18:
        return copyOfRange(r1, 0, r5);
    L12:
        e = move-exception;
        Recreator$$ExternalSyntheticBUOutline0.m("error in parsing \"", r8, "\"", e);
        return null;
    L23:
        return new float[0];
    }

    private static int nextStart(String r3, int r4) {
    L3:
        if (r4 >= r3.length()) goto L14;
        char r0 = r3.charAt(r4);
        if (((r0 - 'A') * (r0 - 'Z')) <= 0) goto L9;
        if (((r0 - 'a') * (r0 - 'z')) <= 0) goto L9;
    L13:
        r4 = r4 + 1;
    L9:
        if (r0 == 'e') goto L13;
        if (r0 == 'E') goto L13;
    L14:
        return r4;
    }

    public static void nodesToPath(PathDataNode[] r7, Path r8) {
        float[] r0 = new float[6];
        int r1 = r7.length;
        char r2 = 'm';
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        PathDataNode r4 = r7[r3];
        PathDataNode.access$200(r8, r0, r2, PathDataNode.access$000(r4), PathDataNode.access$100(r4));
        r2 = PathDataNode.access$000(r4);
        r3 = r3 + 1;
        goto L3
    }

    public static void updateNodes(PathDataNode[] r5, PathDataNode[] r6) {
        int r1 = 0;
    L4:
        if (r1 >= r6.length) goto L10;
        PathDataNode.access$002(r5[r1], PathDataNode.access$000(r6[r1]));
        int r2 = 0;
    L7:
        if (r2 >= PathDataNode.access$100(r6[r1]).length) goto L9;
        PathDataNode.access$100(r5[r1])[r2] = PathDataNode.access$100(r6[r1])[r2];
        r2 = r2 + 1;
        goto L7
    L9:
        r1 = r1 + 1;
        goto L4
    }
}
