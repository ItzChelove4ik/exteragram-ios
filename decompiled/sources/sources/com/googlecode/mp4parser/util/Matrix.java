package com.googlecode.mp4parser.util;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class Matrix {
    public static final Matrix ROTATE_0 = null;
    public static final Matrix ROTATE_180 = null;
    public static final Matrix ROTATE_270 = null;
    public static final Matrix ROTATE_90 = null;

    /* renamed from: a, reason: collision with root package name */
    double f138a;

    /* renamed from: b, reason: collision with root package name */
    double f139b;

    /* renamed from: c, reason: collision with root package name */
    double f140c;
    double d;
    double tx;
    double ty;
    double u;
    double v;
    double w;

    static {
        ROTATE_0 = new Matrix(1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        ROTATE_90 = new Matrix(0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        ROTATE_180 = new Matrix(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
        ROTATE_270 = new Matrix(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    }

    public Matrix(double r1, double r3, double r5, double r7, double r9, double r11, double r13, double r15, double r17) {
        this.u = r9;
        this.v = r11;
        this.w = r13;
        this.f138a = r1;
        this.f139b = r3;
        this.f140c = r5;
        this.d = r7;
        this.tx = r15;
        this.ty = r17;
    }

    public static Matrix fromByteBuffer(ByteBuffer r19) {
        return fromFileOrder(IsoTypeReader.readFixedPoint1616(r19), IsoTypeReader.readFixedPoint1616(r19), IsoTypeReader.readFixedPoint0230(r19), IsoTypeReader.readFixedPoint1616(r19), IsoTypeReader.readFixedPoint1616(r19), IsoTypeReader.readFixedPoint0230(r19), IsoTypeReader.readFixedPoint1616(r19), IsoTypeReader.readFixedPoint1616(r19), IsoTypeReader.readFixedPoint0230(r19));
    }

    public static Matrix fromFileOrder(double r19, double r21, double r23, double r25, double r27, double r29, double r31, double r33, double r35) {
        return new Matrix(r19, r21, r25, r27, r23, r29, r35, r31, r33);
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L38:
        return false;
    L8:
        if (getClass() != r7.getClass()) goto L38;
        Matrix r72 = (Matrix) r7;
        if (Double.compare(r72.f138a, this.f138a) == 0) goto L14;
        return false;
    L14:
        if (Double.compare(r72.f139b, this.f139b) == 0) goto L17;
        return false;
    L17:
        if (Double.compare(r72.f140c, this.f140c) == 0) goto L20;
        return false;
    L20:
        if (Double.compare(r72.d, this.d) == 0) goto L23;
        return false;
    L23:
        if (Double.compare(r72.tx, this.tx) == 0) goto L26;
        return false;
    L26:
        if (Double.compare(r72.ty, this.ty) == 0) goto L29;
        return false;
    L29:
        if (Double.compare(r72.u, this.u) == 0) goto L32;
        return false;
    L32:
        if (Double.compare(r72.v, this.v) == 0) goto L35;
        return false;
    L35:
        if (Double.compare(r72.w, this.w) == 0) goto L37;
        return false;
    L37:
        return true;
    }

    public void getContent(ByteBuffer r3) {
        IsoTypeWriter.writeFixedPoint1616(r3, this.f138a);
        IsoTypeWriter.writeFixedPoint1616(r3, this.f139b);
        IsoTypeWriter.writeFixedPoint0230(r3, this.u);
        IsoTypeWriter.writeFixedPoint1616(r3, this.f140c);
        IsoTypeWriter.writeFixedPoint1616(r3, this.d);
        IsoTypeWriter.writeFixedPoint0230(r3, this.v);
        IsoTypeWriter.writeFixedPoint1616(r3, this.tx);
        IsoTypeWriter.writeFixedPoint1616(r3, this.ty);
        IsoTypeWriter.writeFixedPoint0230(r3, this.w);
    }

    public int hashCode() {
        long r0 = Double.doubleToLongBits(this.u);
        int r02 = (int) (r0 ^ (r0 >>> 32));
        long r3 = Double.doubleToLongBits(this.v);
        int r03 = (r02 * 31) + ((int) (r3 ^ (r3 >>> 32)));
        long r32 = Double.doubleToLongBits(this.w);
        int r04 = (r03 * 31) + ((int) (r32 ^ (r32 >>> 32)));
        long r33 = Double.doubleToLongBits(this.f138a);
        int r05 = (r04 * 31) + ((int) (r33 ^ (r33 >>> 32)));
        long r34 = Double.doubleToLongBits(this.f139b);
        int r06 = (r05 * 31) + ((int) (r34 ^ (r34 >>> 32)));
        long r35 = Double.doubleToLongBits(this.f140c);
        int r07 = (r06 * 31) + ((int) (r35 ^ (r35 >>> 32)));
        long r36 = Double.doubleToLongBits(this.d);
        int r08 = (r07 * 31) + ((int) (r36 ^ (r36 >>> 32)));
        long r37 = Double.doubleToLongBits(this.tx);
        int r09 = (r08 * 31) + ((int) (r37 ^ (r37 >>> 32)));
        long r38 = Double.doubleToLongBits(this.ty);
        return (r09 * 31) + ((int) ((r38 >>> 32) ^ r38));
    }

    public String toString() {
        if (equals(ROTATE_0) == false) goto L7;
        return "Rotate 0°";
    L7:
        if (equals(ROTATE_90) == false) goto L11;
        return "Rotate 90°";
    L11:
        if (equals(ROTATE_180) == false) goto L15;
        return "Rotate 180°";
    L15:
        if (equals(ROTATE_270) == false) goto L19;
        return "Rotate 270°";
    L19:
        return "Matrix{u=" + this.u + ", v=" + this.v + ", w=" + this.w + ", a=" + this.f138a + ", b=" + this.f139b + ", c=" + this.f140c + ", d=" + this.d + ", tx=" + this.tx + ", ty=" + this.ty + '}';
    }
}
