package org.scilab.forge.jlatexmath;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class MatrixAtom extends Atom {
    public static final int ALIGN = 2;
    public static final int ALIGNAT = 3;
    public static final int ALIGNED = 6;
    public static final int ALIGNEDAT = 7;
    public static final int ARRAY = 0;
    public static final int FLALIGN = 4;
    public static final int MATRIX = 1;
    public static final int SMALLMATRIX = 5;
    private static SpaceAtom align;
    public static SpaceAtom hsep;
    private static final Box nullBox = null;
    public static SpaceAtom semihsep;
    public static SpaceAtom vsep_ext_bot;
    public static SpaceAtom vsep_ext_top;
    public static SpaceAtom vsep_in;
    private boolean isPartial;
    private ArrayOfAtoms matrix;
    private int[] position;
    private boolean spaceAround;
    private int type;
    private Map<Integer, VlineAtom> vlines;

    static {
        hsep = new SpaceAtom(0, 1.0f, 0.0f, 0.0f);
        semihsep = new SpaceAtom(0, 0.5f, 0.0f, 0.0f);
        vsep_in = new SpaceAtom(1, 0.0f, 1.0f, 0.0f);
        vsep_ext_top = new SpaceAtom(1, 0.0f, 0.4f, 0.0f);
        vsep_ext_bot = new SpaceAtom(1, 0.0f, 0.4f, 0.0f);
        nullBox = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        align = new SpaceAtom(2);
    }

    public MatrixAtom(ArrayOfAtoms r2, int r3) {
        this(false, r2, r3);
    }

    public MatrixAtom(ArrayOfAtoms r2, String r3) {
        this(false, r2, r3);
    }

    public MatrixAtom(boolean r2, ArrayOfAtoms r3, int r4) {
        this(r2, r3, r4, false);
    }

    public MatrixAtom(boolean r7, ArrayOfAtoms r8, int r9, int r10) {
        this(r7, r8, r9, r10, true);
    }

    public MatrixAtom(boolean r2, ArrayOfAtoms r3, int r4, int r5, boolean r6) {
        this.vlines = new HashMap();
        this.isPartial = r2;
        this.matrix = r3;
        this.type = r4;
        this.spaceAround = r6;
        this.position = new int[r3.col];
        int r22 = 0;
    L4:
        if (r22 >= this.matrix.col) goto L6;
        this.position[r22] = r5;
        r22 = r22 + 1;
        goto L4
    }

    public MatrixAtom(boolean r3, ArrayOfAtoms r4, int r5, boolean r6) {
        this.vlines = new HashMap();
        this.isPartial = r3;
        this.matrix = r4;
        this.type = r5;
        this.spaceAround = r6;
        int r32 = 0;
        if (r5 != 1) goto L5;
    L13:
        this.position = new int[r4.col];
    L15:
        if (r32 >= this.matrix.col) goto L22;
        this.position[r32] = 2;
        r32 = r32 + 1;
        goto L15
    L22:
        return;
    L5:
        if (r5 == 5) goto L13;
        this.position = new int[r4.col];
        int r42 = 0;
    L7:
        int r52 = this.matrix.col;
        if (r42 >= r52) goto L17;
        int[] r0 = this.position;
        r0[r42] = 1;
        int r1 = r42 + 1;
        if (r1 >= r52) goto L12;
        r0[r1] = 0;
    L12:
        r42 = r42 + 2;
        goto L7
    }

    public MatrixAtom(boolean r2, ArrayOfAtoms r3, String r4) {
        this(r2, r3, r4, false);
    }

    public MatrixAtom(boolean r2, ArrayOfAtoms r3, String r4, boolean r5) {
        this.vlines = new HashMap();
        this.isPartial = r2;
        this.matrix = r3;
        this.type = 0;
        this.spaceAround = r5;
        parsePositions(new StringBuffer(r4));
    }

    private Box generateMulticolumn(TeXEnvironment r7, Box[] r8, float[] r9, int r10, int r11) {
        MulticolumnAtom r102 = (MulticolumnAtom) this.matrix.array.get(r10).get(r11);
        int r0 = r102.getSkipped();
        float r1 = 0.0f;
        int r2 = r11;
        float r3 = 0.0f;
    L4:
        if (r2 >= ((r11 + r0) - 1)) goto L8;
        float r4 = r9[r2];
        r2 = r2 + 1;
        r3 = r3 + (r4 + r8[r2].getWidth());
        if (this.vlines.get(Integer.valueOf(r2)) == null) goto L4;
        r3 = r3 + this.vlines.get(Integer.valueOf(r2)).getWidth(r7);
        goto L4
    L8:
        float r32 = r3 + r9[r2];
        if (r102.createBox(r7).getWidth() > r32) goto L12;
        r1 = r32;
    L12:
        r102.setWidth(r1);
        return r102.createBox(r7);
    }

    private void parsePositions(StringBuffer r14) {
        int r2 = r14.length();
        ArrayList r3 = new ArrayList();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
    L3:
        if (r5 >= r2) goto L54;
        r6 = r6 + 1;
        if (r6 > 100000) goto L52;
        if (r2 > 10000) goto L52;
        char r8 = r14.charAt(r5);
        if (r8 == '\t') goto L51;
        if (r8 == ' ') goto L51;
        if (r8 != '*') goto L15;
        int r52 = r5 + 1;
        TeXParser r82 = new TeXParser(this.isPartial, r14.substring(r52), new TeXFormula(), false);
        String[] r22 = r82.getOptsArgs(2, 0);
        int r53 = r52 + r82.getPos();
        int r83 = Integer.parseInt(r22[1]);
        if (r83 < 0) goto L46;
        if (r83 > 4096) goto L46;
    L47:
        StringBuilder r9 = new StringBuilder(r22[2].length() * r83);
        int r10 = 0;
    L48:
        if (r10 >= r83) goto L50;
        r9.append(r22[2]);
        r10 = r10 + 1;
        goto L48
    L50:
        r14.insert(r53, r9.toString());
        r2 = r14.length();
    L42:
        r5 = r53 - 1;
    L46:
        r83 = 4096;
        goto L47
    L15:
        if (r8 != '@') goto L17;
        int r54 = r5 + 1;
        TeXParser r92 = new TeXParser(this.isPartial, r14.substring(r54), new TeXFormula(), false);
        Atom r84 = r92.getArgument();
        this.matrix.col++;
        int r102 = 0;
    L38:
        ArrayOfAtoms r11 = this.matrix;
        if (r102 >= r11.row) goto L41;
        r11.array.get(r102).add(r3.size(), r84);
        r102 = r102 + 1;
        goto L38
    L41:
        r3.add(5);
        r53 = r54 + r92.getPos();
        goto L42
    L17:
        if (r8 != 'c') goto L19;
        r3.add(2);
        goto L51
    L19:
        if (r8 != 'l') goto L21;
        r3.add(0);
        goto L51
    L21:
        if (r8 != 'r') goto L23;
        r3.add(1);
        goto L51
    L23:
        if (r8 == '|') goto L25;
        r3.add(2);
        goto L51
    L25:
        int r85 = 1;
    L26:
        int r103 = r5 + 1;
        if (r103 >= r2) goto L32;
        if (r14.charAt(r103) != '|') goto L33;
        r85 = r85 + 1;
        r5 = r103;
    L33:
        this.vlines.put(Integer.valueOf(r3.size()), new VlineAtom(r85));
        goto L51
    L32:
        r5 = r103;
    L51:
        r5 = r5 + 1;
    L52:
        Box$$ExternalSyntheticBUOutline0.m("Column specification is too complex");
        return;
    L54:
        int r142 = r3.size();
    L56:
        if (r142 >= this.matrix.col) goto L59;
        r3.add(2);
        r142 = r142 + 1;
        goto L56
    L59:
        if (r3.size() == 0) goto L65;
        Integer[] r143 = (Integer[]) r3.toArray(new Integer[0]);
        this.position = new int[r143.length];
    L62:
        if (r4 >= r143.length) goto L64;
        this.position[r4] = r143[r4].intValue();
        r4 = r4 + 1;
        goto L62
    L64:
        return;
    L65:
        this.position = new int[]{2};
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r28) {
        MatrixAtom r0 = this;
        ArrayOfAtoms r1 = r0.matrix;
        int r6 = r1.row;
        int r7 = r1.col;
        int r10 = 0;
        Box[][] r12 = (Box[][]) Array.newInstance(Box.class, new int[]{r6, r7});
        float[] r13 = new float[r6];
        float[] r14 = new float[r6];
        float[] r3 = new float[r7];
        float r15 = r28.getTeXFont().getDefaultRuleThickness(r28.getStyle());
        if (r0.type != 5) goto L5;
        TeXEnvironment r16 = r28.copy();
        r16.setStyle(4);
    L6:
        ArrayList r2 = new ArrayList();
        int r4 = 0;
    L7:
        float r5 = 0.0f;
        if (r4 >= r6) goto L24;
        r13[r4] = 0.0f;
        r14[r4] = 0.0f;
        int r52 = r10;
    L10:
        if (r52 >= r7) goto L23;
        Atom r8 = r0.matrix.array.get(r4).get(r52);     // Catch: Exception -> L13
    L14:
        Box[] r17 = r12[r4];
        if (r8 != null) goto L17;
        Box r18 = nullBox;
    L18:
        r17[r52] = r18;
        r13[r4] = Math.max(r12[r4][r52].getDepth(), r13[r4]);
        r14[r4] = Math.max(r12[r4][r52].getHeight(), r14[r4]);
        Box r9 = r12[r4][r52];
        float[] r19 = r3;
        if (r9.type == 12) goto L21;
        r19[r52] = Math.max(r9.getWidth(), r19[r52]);
    L22:
        r52 = r52 + 1;
        r3 = r19;
        goto L10
    L21:
        MulticolumnAtom r82 = (MulticolumnAtom) r8;
        r82.setRowColumn(r4, r52);
        r2.add(r82);
        goto L22
    L17:
        r18 = r8.createBox(r16);
    L13:
        r12[r4][r52 - 1].type = 11;
        r52 = r7 - 1;
        r8 = null;
        goto L14
    L23:
        r4 = r4 + 1;
        r10 = 0;
        goto L7
    L24:
        float[] r192 = r3;
        int r32 = 0;
    L26:
        if (r32 >= r2.size()) goto L37;
        MulticolumnAtom r42 = (MulticolumnAtom) r2.get(r32);
        int r83 = r42.getCol();
        int r92 = r42.getRow();
        int r43 = r42.getSkipped();
        float r20 = r5;
        int r102 = r83;
    L28:
        int r53 = r83 + r43;
        if (r102 >= r53) goto L32;
        r20 = r20 + r192[r102];
        r102 = r102 + 1;
        goto L28
    L32:
        if (r12[r92][r83].getWidth() <= r20) goto L36;
        float r93 = (r12[r92][r83].getWidth() - r20) / r43;
    L34:
        if (r83 >= r53) goto L36;
        r192[r83] = r192[r83] + r93;
        r83 = r83 + 1;
    L36:
        r32 = r32 + 1;
        r5 = 0.0f;
        goto L26
    L37:
        int r22 = 0;
        float r33 = 0.0f;
    L38:
        if (r22 >= r7) goto L40;
        r33 = r33 + r192[r22];
        r22 = r22 + 1;
        goto L38
    L40:
        Box[] r23 = r0.getColumnSep(r16, r33);
        float r84 = r33;
        int r34 = 0;
    L42:
        if (r34 >= (r7 + 1)) goto L47;
        r84 = r84 + r23[r34].getWidth();
        if (r0.vlines.get(Integer.valueOf(r34)) == null) goto L46;
        r84 = r84 + r0.vlines.get(Integer.valueOf(r34)).getWidth(r16);
    L46:
        r34 = r34 + 1;
        goto L42
    L47:
        VerticalBox r94 = new VerticalBox();
        Box r103 = vsep_in.createBox(r16);
        r94.add(vsep_ext_top.createBox(r16));
        int r44 = 0;
    L49:
        if (r44 >= r6) goto L98;
        HorizontalBox r35 = new HorizontalBox();
        int r54 = 0;
    L51:
        if (r54 >= r7) goto L91;
        Box[] r222 = r23;
        int r24 = r12[r44][r54].type;
        int r21 = r6;
        if (r24 == (-1)) goto L65;
        switch(r24) {
            case 11: goto L66;
            case 12: goto L65;
            case 13: goto L56;
            default: goto L55;
        };
    L55:
        float[] r232 = r192;
        Box[] r282 = r222;
        int r223 = r7;
    L90:
        r54 = r54 + 1;
        r0 = this;
        r23 = r282;
        r6 = r21;
        r7 = r223;
        r192 = r232;
        goto L51
    L56:
        HlineAtom r25 = (HlineAtom) r0.matrix.array.get(r44).get(r54);
        r25.setWidth(r84);
        if (r44 < 1) goto L62;
        int r233 = r44;
        if ((r0.matrix.array.get(r233 - 1).get(r54) instanceof HlineAtom) == false) goto L61;
        boolean r62 = false;
        r35.add(new StrutBox(0.0f, r15 * 2.0f, 0.0f, 0.0f));
        r25.setShift(((-r103.getHeight()) / 2.0f) + r15);
    L64:
        r35.add(r25.createBox(r16));
        r54 = r7;
        r282 = r222;
        r44 = r233;
        r223 = r54;
        r232 = r192;
    L61:
        r62 = false;
        r25.setShift((-r103.getHeight()) / 2.0f);
        goto L64
    L62:
        r233 = r44;
        goto L61
    L66:
        int r234 = r44;
        float r36 = r16.getTextwidth();
        if (r36 != Float.POSITIVE_INFINITY) goto L69;
        r36 = r192[r54];
    L69:
        HorizontalBox r45 = new HorizontalBox(r12[r234][r54], r36, 0);
        r54 = r7 - 1;
        r35 = r45;
        r282 = r222;
        r44 = r234;
        r223 = r7;
        r232 = r192;
    L65:
        int r235 = r44;
        if (r54 == 0) goto L72;
        TeXEnvironment r26 = r16;
    L77:
        if (r12[r235][r54].type != (-1)) goto L79;
        r35.add(new HorizontalBox(r12[r235][r54], r192[r54], r0.position[r54]));
        HorizontalBox r63 = r35;
        float[] r37 = r192;
        r282 = r222;
        r44 = r235;
        r16 = r26;
        boolean r27 = true;
        r223 = r7;
    L80:
        if (r27 == false) goto L87;
        int r72 = r54 + 1;
        r232 = r37;
        if (r0.vlines.get(Integer.valueOf(r72)) == null) goto L88;
        VlineAtom r29 = r0.vlines.get(Integer.valueOf(r72));
        r29.setHeight((r14[r44] + r13[r44]) + r103.getHeight());
        r29.setShift(r13[r44] + (r103.getHeight() / 2.0f));
        Box r210 = r29.createBox(r16);
        if (r54 >= (r223 - 1)) goto L86;
        r63.add(new HorizontalBox(r210, r282[r72].getWidth() + r210.getWidth(), 2));
    L89:
        r35 = r63;
        goto L90
    L86:
        r63.add(new HorizontalBox(r210, r282[r72].getWidth() + r210.getWidth(), 1));
    L88:
        r63.add(r282[r54 + 1]);
        goto L89
    L87:
        r232 = r37;
        goto L88
    L79:
        r63 = r35;
        r37 = r192;
        r44 = r235;
        r16 = r26;
        r223 = r7;
        Box r73 = r0.generateMulticolumn(r16, r222, r37, r44, r54);
        r282 = r222;
        MulticolumnAtom r211 = (MulticolumnAtom) r0.matrix.array.get(r44).get(r54);
        r54 = r54 + (r211.getSkipped() - 1);
        r63.add(r73);
        r27 = r211.hasRightVline();
        goto L80
    L72:
        if (r0.vlines.get(0) == null) goto L74;
        VlineAtom r46 = r0.vlines.get(0);
        r46.setHeight((r14[r235] + r13[r235]) + r103.getHeight());
        r46.setShift(r13[r235] + (r103.getHeight() / 2.0f));
        Box r212 = r46.createBox(r16);
        r26 = r16;
        r35.add(new HorizontalBox(r212, r222[0].getWidth() + r212.getWidth(), 0));
        goto L77
    L74:
        r26 = r16;
        r35.add(r222[0]);
        goto L77
    L91:
        Box[] r283 = r23;
        int r213 = r6;
        int r224 = r7;
        float[] r236 = r192;
        HorizontalBox r64 = r35;
        if (r12[r44][0].type == 13) goto L96;
        r64.setHeight(r14[r44]);
        r64.setDepth(r13[r44]);
        r94.add(r64);
        if (r44 >= (r213 - 1)) goto L97;
        r94.add(r103);
    L97:
        r44 = r44 + 1;
        r0 = this;
        r23 = r283;
        r6 = r213;
        r7 = r224;
        r192 = r236;
        goto L49
    L96:
        r94.add(r64);
        goto L97
    L98:
        r94.add(vsep_ext_bot.createBox(r16));
        float r02 = r94.getHeight() + r94.getDepth();
        float r110 = r16.getTeXFont().getAxisHeight(r16.getStyle());
        float r03 = r02 / 2.0f;
        r94.setHeight(r03 + r110);
        r94.setDepth(r03 - r110);
        return r94;
    L5:
        r16 = r28;
        goto L6
    }

    public Box[] getColumnSep(TeXEnvironment r10, float r11) {
        int r0 = this.matrix.col;
        Box[] r1 = new Box[r0 + 1];
        float r2 = r10.getTextwidth();
        int r3 = this.type;
        if (r3 != 6) goto L5;
    L6:
        r2 = Float.POSITIVE_INFINITY;
    L7:
        int r4 = 2;
        int r8 = 1;
        switch(r3) {
            case 0: goto L51;
            case 1: goto L47;
            case 2: goto L32;
            case 3: goto L22;
            case 4: goto L10;
            case 5: goto L47;
            case 6: goto L32;
            case 7: goto L22;
            default: goto L44;
        };
    L10:
        Box r9 = align.createBox(r10);
        if (r2 == Float.POSITIVE_INFINITY) goto L13;
        Box r112 = new StrutBox(Math.max(((r2 - r11) - ((r0 / 2) * r9.getWidth())) / ((float) Math.floor((r0 - 1) / 2)), 0.0f), 0.0f, 0.0f, 0.0f);
    L14:
        Box r102 = nullBox;
        r1[0] = r102;
        r1[r0] = r102;
    L15:
        if (r8 >= r0) goto L44;
        if ((r8 % 2) != 0) goto L19;
        r1[r8] = r112;
    L20:
        r8 = r8 + 1;
        goto L15
    L19:
        r1[r8] = r9;
        goto L20
    L13:
        r112 = hsep.createBox(r10);
        goto L14
    L32:
        Box r92 = align.createBox(r10);
        if (r2 == Float.POSITIVE_INFINITY) goto L35;
        Box r113 = new StrutBox(Math.max(((r2 - r11) - ((r0 / 2) * r92.getWidth())) / ((float) Math.floor((r0 + 3) / 2)), 0.0f), 0.0f, 0.0f, 0.0f);
    L36:
        r1[r0] = r113;
        int r103 = 0;
    L37:
        if (r103 >= r0) goto L44;
        if ((r103 % 2) != 0) goto L41;
        r1[r103] = r113;
    L42:
        r103 = r103 + 1;
        goto L37
    L41:
        r1[r103] = r92;
        goto L42
    L35:
        r113 = hsep.createBox(r10);
        goto L36
    L47:
        Box r93 = nullBox;
        r1[0] = r93;
        r1[r0] = r93;
        Box r94 = hsep.createBox(r10);
    L48:
        if (r8 >= r0) goto L65;
        r1[r8] = r94;
        r8 = r8 + 1;
    L65:
        return r1;
    L22:
        if (r2 == Float.POSITIVE_INFINITY) goto L24;
        float r95 = Math.max((r2 - r11) / 2.0f, 0.0f);
    L25:
        Box r104 = align.createBox(r10);
        Box r114 = nullBox;
        StrutBox r32 = new StrutBox(r95, 0.0f, 0.0f, 0.0f);
        r1[0] = r32;
        r1[r0] = r32;
    L26:
        if (r8 >= r0) goto L44;
        if ((r8 % 2) != 0) goto L30;
        r1[r8] = r114;
    L31:
        r8 = r8 + 1;
        goto L26
    L30:
        r1[r8] = r104;
        goto L31
    L24:
        r95 = 0.0f;
        goto L25
    L51:
        if (this.position[0] != 5) goto L53;
        r1[1] = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
    L55:
        if (this.spaceAround == false) goto L57;
        r1[0] = semihsep.createBox(r10);
    L58:
        r1[r0] = r1[0];
        Box r105 = hsep.createBox(r10);
    L59:
        if (r4 >= r0) goto L65;
        if (this.position[r4] != 5) goto L63;
        StrutBox r115 = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        r1[r4] = r115;
        r4 = r4 + 1;
        r1[r4] = r115;
    L64:
        r4 = r4 + 1;
        goto L59
    L63:
        r1[r4] = r105;
        goto L64
    L57:
        r1[0] = new StrutBox(0.0f, 0.0f, 0.0f, 0.0f);
        goto L58
    L53:
        r4 = 1;
    L44:
        if (r2 != Float.POSITIVE_INFINITY) goto L65;
        Box r96 = nullBox;
        r1[0] = r96;
        r1[r0] = r96;
        return r1;
    L5:
        if (r3 != 7) goto L7;
        goto L6
    }
}
