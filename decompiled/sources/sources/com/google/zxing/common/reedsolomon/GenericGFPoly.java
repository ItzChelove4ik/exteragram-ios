package com.google.zxing.common.reedsolomon;

import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    public GenericGFPoly(GenericGF r4, int[] r5) {
        if (r5.length == 0) goto L19;
        this.field = r4;
        int r42 = r5.length;
        int r0 = 1;
        if (r42 > 1) goto L7;
    L17:
        this.coefficients = r5;
        return;
    L7:
        if (r5[0] != 0) goto L17;
    L8:
        if (r0 >= r42) goto L12;
        if (r5[r0] != 0) goto L12;
        r0 = r0 + 1;
    L12:
        if (r0 != r42) goto L15;
        this.coefficients = new int[]{0};
        return;
    L15:
        int[] r43 = new int[r42 - r0];
        this.coefficients = r43;
        System.arraycopy(r5, r0, r43, 0, r43.length);
        return;
    L19:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public GenericGFPoly addOrSubtract(GenericGFPoly r8) {
        if (this.field.equals(r8.field) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("GenericGFPolys do not have same GenericGF field");
        return null;
    L5:
        if (isZero() == false) goto L8;
        return r8;
    L8:
        if (r8.isZero() == false) goto L10;
        return this;
    L10:
        int[] r0 = this.coefficients;
        int[] r82 = r8.coefficients;
        if (r0.length > r82.length) goto L14;
        r0 = r82;
        r82 = r0;
    L14:
        int[] r1 = new int[r0.length];
        int r2 = r0.length - r82.length;
        System.arraycopy(r0, 0, r1, 0, r2);
        int r3 = r2;
    L16:
        if (r3 >= r0.length) goto L19;
        r1[r3] = GenericGF.addOrSubtract(r82[r3 - r2], r0[r3]);
        r3 = r3 + 1;
        goto L16
    L19:
        return new GenericGFPoly(this.field, r1);
    }

    public GenericGFPoly[] divide(GenericGFPoly r8) {
        if (this.field.equals(r8.field) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("GenericGFPolys do not have same GenericGF field");
        return null;
    L5:
        if (r8.isZero() == true) goto L14;
        GenericGFPoly r0 = this.field.getZero();
        int r1 = r8.getCoefficient(r8.getDegree());
        int r12 = this.field.inverse(r1);
        GenericGFPoly r2 = this;
    L8:
        if (r2.getDegree() < r8.getDegree()) goto L13;
        if (r2.isZero() == true) goto L13;
        int r3 = r2.getDegree() - r8.getDegree();
        int r4 = this.field.multiply(r2.getCoefficient(r2.getDegree()), r12);
        GenericGFPoly r5 = r8.multiplyByMonomial(r3, r4);
        r0 = r0.addOrSubtract(this.field.buildMonomial(r3, r4));
        r2 = r2.addOrSubtract(r5);
    L13:
        return new GenericGFPoly[]{r0, r2};
    L14:
        f$$ExternalSyntheticBUOutline1.m("Divide by 0");
        return null;
    }

    public int evaluateAt(int r5) {
        int r0 = 0;
        if (r5 == 0) goto L5;
        int[] r1 = this.coefficients;
        int r2 = 1;
        if (r5 != 1) goto L12;
        int r4 = r1.length;
        int r52 = 0;
    L9:
        if (r0 >= r4) goto L11;
        r52 = GenericGF.addOrSubtract(r52, r1[r0]);
        r0 = r0 + 1;
        goto L9
    L11:
        return r52;
    L12:
        int r02 = r1[0];
        int r12 = r1.length;
    L13:
        if (r2 >= r12) goto L15;
        r02 = GenericGF.addOrSubtract(this.field.multiply(r5, r02), this.coefficients[r2]);
        r2 = r2 + 1;
        goto L13
    L15:
        return r02;
    L5:
        return getCoefficient(0);
    }

    public int getCoefficient(int r2) {
        return this.coefficients[(r1.length - 1) - r2];
    }

    public int[] getCoefficients() {
        return this.coefficients;
    }

    public int getDegree() {
        return this.coefficients.length - 1;
    }

    public boolean isZero() {
        if (this.coefficients[0] != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public GenericGFPoly multiply(int r6) {
        if (r6 != 0) goto L6;
        return this.field.getZero();
    L6:
        if (r6 != 1) goto L8;
        return this;
    L8:
        int r0 = this.coefficients.length;
        int[] r1 = new int[r0];
        int r2 = 0;
    L9:
        GenericGF r3 = this.field;
        if (r2 >= r0) goto L13;
        r1[r2] = r3.multiply(this.coefficients[r2], r6);
        r2 = r2 + 1;
        goto L9
    L13:
        return new GenericGFPoly(r3, r1);
    }

    public GenericGFPoly multiply(GenericGFPoly r13) {
        if (this.field.equals(r13.field) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("GenericGFPolys do not have same GenericGF field");
        return null;
    L5:
        if (isZero() == true) goto L18;
        if (r13.isZero() == true) goto L18;
        int[] r0 = this.coefficients;
        int r1 = r0.length;
        int[] r132 = r13.coefficients;
        int r2 = r132.length;
        int[] r3 = new int[(r1 + r2) - 1];
        int r5 = 0;
    L10:
        if (r5 >= r1) goto L16;
        int r6 = r0[r5];
        int r7 = 0;
    L12:
        if (r7 >= r2) goto L14;
        int r8 = r5 + r7;
        r3[r8] = GenericGF.addOrSubtract(r3[r8], this.field.multiply(r6, r132[r7]));
        r7 = r7 + 1;
        goto L12
    L14:
        r5 = r5 + 1;
        goto L10
    L16:
        return new GenericGFPoly(this.field, r3);
    L18:
        return this.field.getZero();
    }

    public GenericGFPoly multiplyByMonomial(int r5, int r6) {
        if (r5 < 0) goto L12;
        if (r6 == 0) goto L5;
        int r0 = this.coefficients.length;
        int[] r52 = new int[r5 + r0];
        int r1 = 0;
    L7:
        GenericGF r2 = this.field;
        if (r1 >= r0) goto L11;
        r52[r1] = r2.multiply(this.coefficients[r1], r6);
        r1 = r1 + 1;
        goto L7
    L11:
        return new GenericGFPoly(r2, r52);
    L5:
        return this.field.getZero();
    L12:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public String toString() {
        if (isZero() == false) goto L6;
        return MVEL.VERSION_SUB;
    L6:
        StringBuilder r0 = new StringBuilder(getDegree() * 8);
        int r1 = getDegree();
    L7:
        if (r1 < 0) goto L34;
        int r2 = getCoefficient(r1);
        if (r2 == 0) goto L32;
        if (r2 >= 0) goto L17;
        if (r1 != getDegree()) goto L14;
        r0.append("-");
    L15:
        r2 = -r2;
    L20:
        if (r1 == 0) goto L22;
        if (r2 != 1) goto L22;
    L28:
        if (r1 == 0) goto L32;
        if (r1 != 1) goto L31;
        r0.append('x');
        goto L32
    L31:
        r0.append("x^");
        r0.append(r1);
    L22:
        int r22 = this.field.log(r2);
        if (r22 != 0) goto L25;
        r0.append('1');
        goto L28
    L25:
        if (r22 != 1) goto L27;
        r0.append('a');
        goto L28
    L27:
        r0.append("a^");
        r0.append(r22);
        goto L28
    L14:
        r0.append(" - ");
        goto L15
    L17:
        if (r0.length() <= 0) goto L20;
        r0.append(" + ");
    L32:
        r1 = r1 - 1;
        goto L7
    L34:
        return r0.toString();
    }
}
