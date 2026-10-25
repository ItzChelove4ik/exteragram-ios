package org.scilab.forge.jlatexmath;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class SpaceAtom extends Atom {
    private static UnitConversion[] unitConversions;
    private static Map<String, Integer> units;
    private boolean blankSpace;
    private int blankType;
    private int dUnit;
    private float depth;
    private int hUnit;
    private float height;
    private int wUnit;
    private float width;

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$1, reason: invalid class name */
    public class AnonymousClass1 implements UnitConversion {
        public AnonymousClass1() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r1) {
            return r1.getTeXFont().getEM(r1.getStyle());
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$10, reason: invalid class name */
    public class AnonymousClass10 implements UnitConversion {
        public AnonymousClass10() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 65536.0f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$11, reason: invalid class name */
    public class AnonymousClass11 implements UnitConversion {
        public AnonymousClass11() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 0.996264f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$12, reason: invalid class name */
    public class AnonymousClass12 implements UnitConversion {
        public AnonymousClass12() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 1.0660349f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$13, reason: invalid class name */
    public class AnonymousClass13 implements UnitConversion {
        public AnonymousClass13() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 12.792419f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$14, reason: invalid class name */
    public class AnonymousClass14 implements UnitConversion {
        public AnonymousClass14() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r1) {
            return r1.getTeXFont().getDefaultRuleThickness(r1.getStyle());
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$2, reason: invalid class name */
    public class AnonymousClass2 implements UnitConversion {
        public AnonymousClass2() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return r2.getTeXFont().getXHeight(r2.getStyle(), r2.getLastFontId());
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$3, reason: invalid class name */
    public class AnonymousClass3 implements UnitConversion {
        public AnonymousClass3() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r1) {
            return 1.0f / r1.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$4, reason: invalid class name */
    public class AnonymousClass4 implements UnitConversion {
        public AnonymousClass4() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r1) {
            return TeXFormula.PIXELS_PER_POINT / r1.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$5, reason: invalid class name */
    public class AnonymousClass5 implements UnitConversion {
        public AnonymousClass5() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 12.0f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$6, reason: invalid class name */
    public class AnonymousClass6 implements UnitConversion {
        public AnonymousClass6() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            TeXFont r1 = r2.getTeXFont();
            return r1.getQuad(r2.getStyle(), r1.getMuFontId()) / 18.0f;
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$7, reason: invalid class name */
    public class AnonymousClass7 implements UnitConversion {
        public AnonymousClass7() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 28.346457f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$8, reason: invalid class name */
    public class AnonymousClass8 implements UnitConversion {
        public AnonymousClass8() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 2.8346457f) / r2.getSize();
        }
    }

    /* renamed from: org.scilab.forge.jlatexmath.SpaceAtom$9, reason: invalid class name */
    public class AnonymousClass9 implements UnitConversion {
        public AnonymousClass9() {
        }

        @Override // org.scilab.forge.jlatexmath.SpaceAtom.UnitConversion
        public float getPixelConversion(TeXEnvironment r2) {
            return (TeXFormula.PIXELS_PER_POINT * 72.0f) / r2.getSize();
        }
    }

    public interface UnitConversion {
        float getPixelConversion(TeXEnvironment r1);
    }

    static {
        HashMap r0 = new HashMap();
        units = r0;
        r0.put("em", 0);
        units.put("ex", 1);
        units.put("px", 2);
        units.put("pix", 2);
        units.put("pixel", 2);
        units.put("pt", 10);
        units.put("bp", 3);
        units.put("pica", 4);
        units.put("pc", 4);
        units.put("mu", 5);
        units.put("cm", 6);
        units.put("mm", 7);
        units.put("in", 8);
        units.put("sp", 9);
        units.put("dd", 11);
        units.put("cc", 12);
        unitConversions = new UnitConversion[]{new AnonymousClass1(), new AnonymousClass2(), new AnonymousClass3(), new AnonymousClass4(), new AnonymousClass5(), new AnonymousClass6(), new AnonymousClass7(), new AnonymousClass8(), new AnonymousClass9(), new AnonymousClass10(), new AnonymousClass11(), new AnonymousClass12(), new AnonymousClass13(), new AnonymousClass14()};
    }

    public SpaceAtom() {
        this.blankSpace = true;
    }

    public SpaceAtom(int r2) {
        this.blankSpace = true;
        this.blankType = r2;
    }

    public SpaceAtom(int r1, float r2, float r3, float r4) {
        checkUnit(r1);
        this.wUnit = r1;
        this.hUnit = r1;
        this.dUnit = r1;
        this.width = r2;
        this.height = r3;
        this.depth = r4;
    }

    public SpaceAtom(int r1, float r2, int r3, float r4, int r5, float r6) {
        checkUnit(r1);
        checkUnit(r3);
        checkUnit(r5);
        this.wUnit = r1;
        this.hUnit = r3;
        this.dUnit = r5;
        this.width = r2;
        this.height = r4;
        this.depth = r6;
    }

    public static void checkUnit(int r1) {
        if (r1 < 0) goto L7;
        if (r1 >= unitConversions.length) goto L7;
        return;
    L7:
        throw new InvalidUnitException();
    }

    public static float getFactor(int r1, TeXEnvironment r2) {
        return unitConversions[r1].getPixelConversion(r2);
    }

    public static float[] getLength(String r6) {
        if (r6 == null) goto L5;
        int r2 = 0;
    L8:
        if (r2 >= r6.length()) goto L22;
        if (Character.isLetter(r6.charAt(r2)) == true) goto L22;
        r2 = r2 + 1;
    L22:
        float r4 = Float.parseFloat(r6.substring(0, r2));     // Catch: NumberFormatException -> L20
        if (r2 == r6.length()) goto L17;
        int r62 = getUnit(r6.substring(r2).toLowerCase());
    L19:
        return new float[]{r62, r4};
    L17:
        r62 = 2;
    L21:
        return new float[]{Float.NaN};
    L5:
        return new float[]{2.0f, 0.0f};
    }

    public static int getUnit(String r1) {
        Integer r12 = units.get(r1);
        if (r12 != null) goto L7;
        return 2;
    L7:
        return r12.intValue();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r6) {
        if (this.blankSpace == false) goto L22;
        int r0 = this.blankType;
        if (r0 == 0) goto L7;
        if (r0 >= 0) goto L11;
        r0 = -r0;
    L11:
        if (r0 != 1) goto L14;
        Box r62 = Glue.get(7, 1, r6);
    L18:
        if (this.blankType >= 0) goto L20;
        r62.negWidth();
    L20:
        return r62;
    L14:
        if (r0 != 2) goto L16;
        r62 = Glue.get(2, 1, r6);
        goto L18
    L16:
        r62 = Glue.get(3, 1, r6);
        goto L18
    L7:
        return new StrutBox(r6.getSpace(), 0.0f, 0.0f, 0.0f);
    L22:
        return new StrutBox(this.width * getFactor(this.wUnit, r6), this.height * getFactor(this.hUnit, r6), this.depth * getFactor(this.dUnit, r6), 0.0f);
    }
}
