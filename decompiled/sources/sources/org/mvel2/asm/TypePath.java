package org.mvel2.asm;

import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class TypePath {
    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int TYPE_ARGUMENT = 3;
    public static final int WILDCARD_BOUND = 2;
    private final byte[] typePathContainer;
    private final int typePathOffset;

    public TypePath(byte[] r1, int r2) {
        this.typePathContainer = r1;
        this.typePathOffset = r2;
    }

    public static TypePath fromString(String r9) {
        if (r9 != null) goto L5;
        return null;
    L5:
        if (r9.length() == 0) goto L52;
        int r1 = r9.length();
        ByteVector r2 = new ByteVector(r1);
        r2.putByte(0);
        int r4 = 0;
    L9:
        if (r4 >= r1) goto L38;
        int r6 = r4 + 1;
        char r42 = r9.charAt(r4);
        if (r42 != '[') goto L14;
        r2.put11(0, 0);
    L19:
        r4 = r6;
        goto L9
    L14:
        if (r42 != '.') goto L17;
        r2.put11(1, 0);
        goto L19
    L17:
        if (r42 != '*') goto L21;
        r2.put11(2, 0);
        goto L19
    L21:
        if (r42 < '0') goto L36;
        if (r42 > '9') goto L36;
        int r43 = r42 - '0';
    L25:
        if (r6 >= r1) goto L35;
        int r8 = r6 + 1;
        char r62 = r9.charAt(r6);
        if (r62 < '0') goto L31;
        if (r62 > '9') goto L31;
        r43 = ((r43 * 10) + r62) - 48;
        r6 = r8;
    L31:
        if (r62 != ';') goto L33;
        r6 = r8;
        goto L35
    L33:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L35:
        r2.put11(3, r43);
    L36:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L38:
        byte[] r92 = r2.data;
        r92[0] = (byte) (r2.length / 2);
        return new TypePath(r92, 0);
    L52:
        return null;
    }

    public static void put(TypePath r2, ByteVector r3) {
        if (r2 != null) goto L5;
        r3.putByte(0);
        return;
    L5:
        byte[] r0 = r2.typePathContainer;
        int r22 = r2.typePathOffset;
        r3.putByteArray(r0, r22, (r0[r22] * 2) + 1);
    }

    public int getLength() {
        return this.typePathContainer[this.typePathOffset];
    }

    public int getStep(int r2) {
        return this.typePathContainer[(this.typePathOffset + (r2 * 2)) + 1];
    }

    public int getStepArgument(int r2) {
        return this.typePathContainer[(this.typePathOffset + (r2 * 2)) + 2];
    }

    public String toString() {
        int r0 = getLength();
        StringBuilder r1 = new StringBuilder(r0 * 2);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        int r3 = getStep(r2);
        if (r3 != 0) goto L7;
        r1.append('[');
    L18:
        r2 = r2 + 1;
        goto L3
    L7:
        if (r3 != 1) goto L9;
        r1.append('.');
        goto L18
    L9:
        if (r3 != 2) goto L11;
        r1.append('*');
        goto L18
    L11:
        if (r3 != 3) goto L13;
        r1.append(getStepArgument(r2));
        r1.append(';');
        goto L18
    L13:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L20:
        return r1.toString();
    }
}
