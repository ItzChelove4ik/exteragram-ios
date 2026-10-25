package com.google.gson.internal;

import java.math.BigDecimal;

/* loaded from: classes.dex */
public final class LazilyParsedNumber extends Number {
    private final String value;

    public LazilyParsedNumber(String r1) {
        this.value = r1;
    }

    private BigDecimal asBigDecimal() {
        return NumberLimits.parseBigDecimal(this.value);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.value);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof LazilyParsedNumber) == true) goto L8;
        return false;
    L8:
        return this.value.equals(((LazilyParsedNumber) r2).value);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return Integer.parseInt(this.value);
    L4:
        return (int) Long.parseLong(this.value);
    L8:
        return this.asBigDecimal().intValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return Long.parseLong(this.value);
    L5:
        return asBigDecimal().longValue();
    }

    public String toString() {
        return this.value;
    }
}
