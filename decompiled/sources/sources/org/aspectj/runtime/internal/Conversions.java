package org.aspectj.runtime.internal;

/* loaded from: classes5.dex */
public abstract class Conversions {
    public static Object booleanObject(boolean r1) {
        return new Boolean(r1);
    }

    public static Object doubleObject(double r1) {
        return new Double(r1);
    }

    public static Object floatObject(float r1) {
        return new Float(r1);
    }

    public static Object intObject(int r1) {
        return new Integer(r1);
    }

    public static Object longObject(long r1) {
        return new Long(r1);
    }
}
