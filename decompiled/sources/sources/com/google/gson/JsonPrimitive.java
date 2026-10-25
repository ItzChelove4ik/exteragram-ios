package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class JsonPrimitive extends JsonElement {
    private final Object value;

    public JsonPrimitive(Boolean r1) {
        Objects.requireNonNull(r1);
        this.value = r1;
    }

    public JsonPrimitive(Number r1) {
        Objects.requireNonNull(r1);
        this.value = r1;
    }

    public JsonPrimitive(String r1) {
        Objects.requireNonNull(r1);
        this.value = r1;
    }

    private static boolean isIntegral(JsonPrimitive r2) {
        Object r22 = r2.value;
        if ((r22 instanceof Number) == false) goto L18;
        Number r23 = (Number) r22;
        if ((r23 instanceof BigInteger) == false) goto L7;
        return true;
    L7:
        if ((r23 instanceof Long) == false) goto L9;
        return true;
    L9:
        if ((r23 instanceof Integer) == false) goto L11;
        return true;
    L11:
        if ((r23 instanceof Short) == false) goto L13;
        return true;
    L13:
        if ((r23 instanceof Byte) == true) goto L22;
        return false;
    L22:
        return true;
    L18:
        return false;
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if (r6 != null) goto L8;
    L54:
        return false;
    L8:
        if (JsonPrimitive.class != r6.getClass()) goto L54;
        JsonPrimitive r62 = (JsonPrimitive) r6;
        if (this.value != null) goto L17;
        if (r62.value != null) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (isIntegral(this) == true) goto L19;
    L31:
        Object r2 = this.value;
        if ((r2 instanceof Number) == false) goto L53;
        Object r3 = r62.value;
        if ((r3 instanceof Number) == false) goto L53;
        if ((r2 instanceof BigDecimal) == true) goto L38;
    L43:
        double r22 = getAsDouble();
        double r5 = r62.getAsDouble();
        if (r22 != r5) goto L46;
    L51:
        return true;
    L46:
        if (Double.isNaN(r22) == true) goto L48;
    L50:
        return false;
    L48:
        if (Double.isNaN(r5) == false) goto L50;
    L38:
        if ((r3 instanceof BigDecimal) == false) goto L43;
        if (getAsBigDecimal().compareTo(r62.getAsBigDecimal()) != 0) goto L42;
        return true;
    L42:
        return false;
    L53:
        return r2.equals(r62.value);
    L19:
        if (isIntegral(r62) == false) goto L31;
        if ((this.value instanceof BigInteger) == true) goto L30;
        if ((r62.value instanceof BigInteger) == true) goto L30;
        if (getAsNumber().longValue() != r62.getAsNumber().longValue()) goto L28;
        return true;
    L28:
        return false;
    L30:
        return getAsBigInteger().equals(r62.getAsBigInteger());
    }

    public BigDecimal getAsBigDecimal() {
        Object r0 = this.value;
        if ((r0 instanceof BigDecimal) == false) goto L7;
        return (BigDecimal) r0;
    L7:
        return NumberLimits.parseBigDecimal(getAsString());
    }

    public BigInteger getAsBigInteger() {
        Object r0 = this.value;
        if ((r0 instanceof BigInteger) == false) goto L7;
        return (BigInteger) r0;
    L7:
        if (isIntegral(this) == false) goto L11;
        return BigInteger.valueOf(getAsNumber().longValue());
    L11:
        return NumberLimits.parseBigInteger(getAsString());
    }

    @Override // com.google.gson.JsonElement
    public boolean getAsBoolean() {
        if (isBoolean() == false) goto L7;
        return ((Boolean) this.value).booleanValue();
    L7:
        return Boolean.parseBoolean(getAsString());
    }

    public double getAsDouble() {
        if (isNumber() == false) goto L7;
        return getAsNumber().doubleValue();
    L7:
        return Double.parseDouble(getAsString());
    }

    @Override // com.google.gson.JsonElement
    public float getAsFloat() {
        if (isNumber() == false) goto L7;
        return getAsNumber().floatValue();
    L7:
        return Float.parseFloat(getAsString());
    }

    @Override // com.google.gson.JsonElement
    public int getAsInt() {
        if (isNumber() == false) goto L7;
        return getAsNumber().intValue();
    L7:
        return Integer.parseInt(getAsString());
    }

    @Override // com.google.gson.JsonElement
    public long getAsLong() {
        if (isNumber() == false) goto L7;
        return getAsNumber().longValue();
    L7:
        return Long.parseLong(getAsString());
    }

    public Number getAsNumber() {
        Object r1 = this.value;
        if ((r1 instanceof Number) == false) goto L7;
        return (Number) r1;
    L7:
        if ((r1 instanceof String) == true) goto L9;
        ByteString$$ExternalSyntheticBUOutline0.m("Primitive is neither a number nor a string");
        return null;
    L9:
        return new LazilyParsedNumber((String) r1);
    }

    @Override // com.google.gson.JsonElement
    public String getAsString() {
        Object r0 = this.value;
        if ((r0 instanceof String) == false) goto L7;
        return (String) r0;
    L7:
        if (isNumber() == true) goto L9;
        boolean r02 = isBoolean();
        Object r3 = this.value;
        if (r02 == false) goto L15;
        return ((Boolean) r3).toString();
    L15:
        throw new AssertionError("Unexpected value type: " + r3.getClass());
    L9:
        return getAsNumber().toString();
    }

    public int hashCode() {
        if (this.value != null) goto L7;
        return 31;
    L7:
        if (isIntegral(this) == false) goto L11;
        long r2 = getAsNumber().longValue();
    L10:
        return (int) ((r2 >>> 32) ^ r2);
    L11:
        Object r0 = this.value;
        if ((r0 instanceof Number) == false) goto L15;
        r2 = Double.doubleToLongBits(getAsNumber().doubleValue());
        goto L10
    L15:
        return r0.hashCode();
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public boolean isString() {
        return this.value instanceof String;
    }
}
