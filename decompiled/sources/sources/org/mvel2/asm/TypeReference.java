package org.mvel2.asm;

import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class TypeReference {
    public static final int CAST = 71;
    public static final int CLASS_EXTENDS = 16;
    public static final int CLASS_TYPE_PARAMETER = 0;
    public static final int CLASS_TYPE_PARAMETER_BOUND = 17;
    public static final int CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT = 72;
    public static final int CONSTRUCTOR_REFERENCE = 69;
    public static final int CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT = 74;
    public static final int EXCEPTION_PARAMETER = 66;
    public static final int FIELD = 19;
    public static final int INSTANCEOF = 67;
    public static final int LOCAL_VARIABLE = 64;
    public static final int METHOD_FORMAL_PARAMETER = 22;
    public static final int METHOD_INVOCATION_TYPE_ARGUMENT = 73;
    public static final int METHOD_RECEIVER = 21;
    public static final int METHOD_REFERENCE = 70;
    public static final int METHOD_REFERENCE_TYPE_ARGUMENT = 75;
    public static final int METHOD_RETURN = 20;
    public static final int METHOD_TYPE_PARAMETER = 1;
    public static final int METHOD_TYPE_PARAMETER_BOUND = 18;
    public static final int NEW = 68;
    public static final int RESOURCE_VARIABLE = 65;
    public static final int THROWS = 23;
    private final int targetTypeAndInfo;

    public TypeReference(int r1) {
        this.targetTypeAndInfo = r1;
    }

    public static TypeReference newExceptionReference(int r2) {
        return new TypeReference((r2 << 8) | 385875968);
    }

    public static TypeReference newFormalParameterReference(int r2) {
        return new TypeReference((r2 << 16) | 369098752);
    }

    public static TypeReference newSuperTypeReference(int r2) {
        return new TypeReference(((r2 & 65535) << 8) | 268435456);
    }

    public static TypeReference newTryCatchReference(int r2) {
        return new TypeReference((r2 << 8) | 1107296256);
    }

    public static TypeReference newTypeArgumentReference(int r1, int r2) {
        return new TypeReference((r1 << 24) | r2);
    }

    public static TypeReference newTypeParameterBoundReference(int r1, int r2, int r3) {
        return new TypeReference(((r1 << 24) | (r2 << 16)) | (r3 << 8));
    }

    public static TypeReference newTypeParameterReference(int r1, int r2) {
        return new TypeReference((r1 << 24) | (r2 << 16));
    }

    public static TypeReference newTypeReference(int r1) {
        return new TypeReference(r1 << 24);
    }

    public static void putTarget(int r2, ByteVector r3) {
        int r0 = r2 >>> 24;
        if (r0 != 0) goto L5;
    L16:
        r3.putShort(r2 >>> 16);
        return;
    L5:
        if (r0 == 1) goto L16;
        switch(r0) {
            case 16: goto L14;
            case 17: goto L14;
            case 18: goto L14;
            case 19: goto L12;
            case 20: goto L12;
            case 21: goto L12;
            case 22: goto L16;
            case 23: goto L14;
            default: goto L7;
        };
    L7:
        switch(r0) {
            case 66: goto L14;
            case 67: goto L14;
            case 68: goto L14;
            case 69: goto L14;
            case 70: goto L14;
            case 71: goto L10;
            case 72: goto L10;
            case 73: goto L10;
            case 74: goto L10;
            case 75: goto L10;
            default: goto L8;
        };
    L8:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L10:
        r3.putInt(r2);
        return;
    L12:
        r3.putByte(r0);
        return;
    L14:
        r3.put12(r0, (r2 & 16776960) >> 8);
    }

    public int getExceptionIndex() {
        return (this.targetTypeAndInfo & 16776960) >> 8;
    }

    public int getFormalParameterIndex() {
        return (this.targetTypeAndInfo & 16711680) >> 16;
    }

    public int getSort() {
        return this.targetTypeAndInfo >>> 24;
    }

    public int getSuperTypeIndex() {
        return (short) ((this.targetTypeAndInfo & 16776960) >> 8);
    }

    public int getTryCatchBlockIndex() {
        return (this.targetTypeAndInfo & 16776960) >> 8;
    }

    public int getTypeArgumentIndex() {
        return this.targetTypeAndInfo & 255;
    }

    public int getTypeParameterBoundIndex() {
        return (this.targetTypeAndInfo & 65280) >> 8;
    }

    public int getTypeParameterIndex() {
        return (this.targetTypeAndInfo & 16711680) >> 16;
    }

    public int getValue() {
        return this.targetTypeAndInfo;
    }
}
