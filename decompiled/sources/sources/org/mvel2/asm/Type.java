package org.mvel2.asm;

import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import okhttp3.internal.url._UrlKt;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class Type {
    public static final int ARRAY = 9;
    public static final int BOOLEAN = 1;
    public static final Type BOOLEAN_TYPE = null;
    public static final int BYTE = 3;
    public static final Type BYTE_TYPE = null;
    public static final int CHAR = 2;
    public static final Type CHAR_TYPE = null;
    public static final int DOUBLE = 8;
    public static final Type DOUBLE_TYPE = null;
    public static final int FLOAT = 6;
    public static final Type FLOAT_TYPE = null;
    public static final int INT = 5;
    private static final int INTERNAL = 12;
    public static final Type INT_TYPE = null;
    public static final int LONG = 7;
    public static final Type LONG_TYPE = null;
    public static final int METHOD = 11;
    public static final int OBJECT = 10;
    private static final String PRIMITIVE_DESCRIPTORS = "VZCBSIFJD";
    public static final int SHORT = 4;
    public static final Type SHORT_TYPE = null;
    public static final int VOID = 0;
    public static final Type VOID_TYPE = null;
    private final int sort;
    private final int valueBegin;
    private final String valueBuffer;
    private final int valueEnd;

    static {
        VOID_TYPE = new Type(0, PRIMITIVE_DESCRIPTORS, 0, 1);
        BOOLEAN_TYPE = new Type(1, PRIMITIVE_DESCRIPTORS, 1, 2);
        CHAR_TYPE = new Type(2, PRIMITIVE_DESCRIPTORS, 2, 3);
        BYTE_TYPE = new Type(3, PRIMITIVE_DESCRIPTORS, 3, 4);
        SHORT_TYPE = new Type(4, PRIMITIVE_DESCRIPTORS, 4, 5);
        INT_TYPE = new Type(5, PRIMITIVE_DESCRIPTORS, 5, 6);
        FLOAT_TYPE = new Type(6, PRIMITIVE_DESCRIPTORS, 6, 7);
        LONG_TYPE = new Type(7, PRIMITIVE_DESCRIPTORS, 7, 8);
        DOUBLE_TYPE = new Type(8, PRIMITIVE_DESCRIPTORS, 8, 9);
    }

    private Type(int r1, String r2, int r3, int r4) {
        this.sort = r1;
        this.valueBuffer = r2;
        this.valueBegin = r3;
        this.valueEnd = r4;
    }

    private static void appendDescriptor(Class<?> r1, StringBuilder r2) {
    L3:
        if (r1.isArray() == false) goto L6;
        r2.append('[');
        r1 = r1.getComponentType();
        goto L3
    L6:
        if (r1.isPrimitive() == true) goto L8;
        r2.append('L');
        r2.append(getInternalName(r1));
        r2.append(';');
        return;
    L8:
        if (r1 != Integer.TYPE) goto L11;
        char r12 = 'I';
    L34:
        r2.append(r12);
        return;
    L11:
        if (r1 != Void.TYPE) goto L14;
        r12 = 'V';
        goto L34
    L14:
        if (r1 != Boolean.TYPE) goto L17;
        r12 = 'Z';
        goto L34
    L17:
        if (r1 != Byte.TYPE) goto L20;
        r12 = 'B';
        goto L34
    L20:
        if (r1 != Character.TYPE) goto L23;
        r12 = 'C';
        goto L34
    L23:
        if (r1 != Short.TYPE) goto L26;
        r12 = 'S';
        goto L34
    L26:
        if (r1 != Double.TYPE) goto L29;
        r12 = 'D';
        goto L34
    L29:
        if (r1 != Float.TYPE) goto L32;
        r12 = 'F';
        goto L34
    L32:
        if (r1 != Long.TYPE) goto L36;
        r12 = 'J';
        goto L34
    L36:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
    }

    private void appendDescriptor(StringBuilder r3) {
        int r0 = this.sort;
        if (r0 != 10) goto L7;
        r3.append(this.valueBuffer, this.valueBegin - 1, this.valueEnd + 1);
        return;
    L7:
        if (r0 != 12) goto L10;
        r3.append('L');
        r3.append(this.valueBuffer, this.valueBegin, this.valueEnd);
        r3.append(';');
        return;
    L10:
        r3.append(this.valueBuffer, this.valueBegin, this.valueEnd);
    }

    public static Type[] getArgumentTypes(String r10) {
        int r0 = 0;
        int r3 = 0;
        int r2 = 1;
    L4:
        if (r10.charAt(r2) == ')') goto L13;
    L6:
        if (r10.charAt(r2) != '[') goto L8;
        r2 = r2 + 1;
        goto L6
    L8:
        int r4 = r2 + 1;
        if (r10.charAt(r2) != 'L') goto L11;
        r2 = Math.max(r4, r10.indexOf(59, r4) + 1);
    L12:
        r3 = r3 + 1;
        goto L4
    L11:
        r2 = r4;
        goto L12
    L13:
        Type[] r22 = new Type[r3];
        int r32 = 1;
    L15:
        if (r10.charAt(r32) == ')') goto L24;
        int r42 = r32;
    L18:
        if (r10.charAt(r42) != '[') goto L20;
        r42 = r42 + 1;
        goto L18
    L20:
        int r9 = r42 + 1;
        if (r10.charAt(r42) != 'L') goto L23;
        r9 = Math.max(r9, r10.indexOf(59, r9) + 1);
    L23:
        r22[r0] = getTypeInternal(r10, r32, r9);
        r0 = r0 + 1;
        r32 = r9;
        goto L15
    L24:
        return r22;
    }

    public static Type[] getArgumentTypes(Method r3) {
        Class<?>[] r32 = r3.getParameterTypes();
        Type[] r0 = new Type[r32.length];
        int r1 = r32.length - 1;
    L3:
        if (r1 < 0) goto L5;
        r0[r1] = getType(r32[r1]);
        r1 = r1 - 1;
        goto L3
    L5:
        return r0;
    }

    public static int getArgumentsAndReturnSizes(String r7) {
        int r0 = 1;
        char r1 = r7.charAt(1);
        int r2 = 1;
        int r3 = 1;
    L4:
        if (r1 == ')') goto L17;
        if (r1 == 'J') goto L15;
        if (r1 == 'D') goto L15;
    L9:
        if (r7.charAt(r2) != '[') goto L11;
        r2 = r2 + 1;
        goto L9
    L11:
        int r12 = r2 + 1;
        if (r7.charAt(r2) != 'L') goto L14;
        r12 = Math.max(r12, r7.indexOf(59, r12) + 1);
    L14:
        r3 = r3 + 1;
        r2 = r12;
    L16:
        r1 = r7.charAt(r2);
    L15:
        r2 = r2 + 1;
        r3 = r3 + 2;
        goto L16
    L17:
        char r72 = r7.charAt(r2 + 1);
        if (r72 == 'V') goto L20;
        if (r72 == 'J') goto L23;
        if (r72 == 'D') goto L23;
    L25:
        return (r3 << 2) | r0;
    L23:
        r0 = 2;
        goto L25
    L20:
        return r3 << 2;
    }

    public static String getConstructorDescriptor(Constructor<?> r4) {
        StringBuilder r0 = new StringBuilder();
        r0.append('(');
        Class<?>[] r42 = r4.getParameterTypes();
        int r1 = r42.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        appendDescriptor(r42[r2], r0);
        r2 = r2 + 1;
        goto L3
    L5:
        r0.append(")V");
        return r0.toString();
    }

    public static String getDescriptor(Class<?> r1) {
        StringBuilder r0 = new StringBuilder();
        appendDescriptor(r1, r0);
        return r0.toString();
    }

    public static String getInternalName(Class<?> r2) {
        return r2.getName().replace('.', '/');
    }

    public static String getMethodDescriptor(Method r5) {
        StringBuilder r0 = new StringBuilder();
        r0.append('(');
        Class<?>[] r1 = r5.getParameterTypes();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        appendDescriptor(r1[r3], r0);
        r3 = r3 + 1;
        goto L3
    L5:
        r0.append(')');
        appendDescriptor(r5.getReturnType(), r0);
        return r0.toString();
    }

    public static String getMethodDescriptor(Type r4, Type... r5) {
        StringBuilder r0 = new StringBuilder();
        r0.append('(');
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r5[r2].appendDescriptor(r0);
        r2 = r2 + 1;
        goto L3
    L5:
        r0.append(')');
        r4.appendDescriptor(r0);
        return r0.toString();
    }

    public static Type getMethodType(String r4) {
        return new Type(11, r4, 0, r4.length());
    }

    public static Type getMethodType(Type r0, Type... r1) {
        return getType(getMethodDescriptor(r0, r1));
    }

    public static Type getObjectType(String r4) {
        if (r4.charAt(0) != '[') goto L5;
        int r2 = 9;
    L7:
        return new Type(r2, r4, 0, r4.length());
    L5:
        r2 = 12;
        goto L7
    }

    public static Type getReturnType(String r2) {
        return getTypeInternal(r2, getReturnTypeOffset(r2), r2.length());
    }

    public static Type getReturnType(Method r0) {
        return getType(r0.getReturnType());
    }

    public static int getReturnTypeOffset(String r4) {
        int r1 = 1;
    L4:
        if (r4.charAt(r1) == ')') goto L13;
    L6:
        if (r4.charAt(r1) != '[') goto L8;
        r1 = r1 + 1;
        goto L6
    L8:
        int r2 = r1 + 1;
        if (r4.charAt(r1) == 'L') goto L10;
        r1 = r2;
        goto L4
    L10:
        r1 = Math.max(r2, r4.indexOf(59, r2) + 1);
        goto L4
    L13:
        return r1 + 1;
    }

    public static Type getType(Class<?> r1) {
        if (r1.isPrimitive() == false) goto L43;
        if (r1 != Integer.TYPE) goto L9;
        return INT_TYPE;
    L9:
        if (r1 != Void.TYPE) goto L13;
        return VOID_TYPE;
    L13:
        if (r1 != Boolean.TYPE) goto L17;
        return BOOLEAN_TYPE;
    L17:
        if (r1 != Byte.TYPE) goto L21;
        return BYTE_TYPE;
    L21:
        if (r1 != Character.TYPE) goto L25;
        return CHAR_TYPE;
    L25:
        if (r1 != Short.TYPE) goto L29;
        return SHORT_TYPE;
    L29:
        if (r1 != Double.TYPE) goto L33;
        return DOUBLE_TYPE;
    L33:
        if (r1 != Float.TYPE) goto L37;
        return FLOAT_TYPE;
    L37:
        if (r1 == Long.TYPE) goto L39;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L39:
        return LONG_TYPE;
    L43:
        return getType(getDescriptor(r1));
    }

    public static Type getType(String r2) {
        return getTypeInternal(r2, 0, r2.length());
    }

    public static Type getType(Constructor<?> r0) {
        return getType(getConstructorDescriptor(r0));
    }

    public static Type getType(Method r0) {
        return getType(getMethodDescriptor(r0));
    }

    private static Type getTypeInternal(String r2, int r3, int r4) {
        char r0 = r2.charAt(r3);
        if (r0 == '(') goto L46;
        if (r0 == 'F') goto L44;
        if (r0 == 'L') goto L42;
        if (r0 == 'S') goto L40;
        if (r0 == 'V') goto L38;
        if (r0 == 'I') goto L36;
        if (r0 == 'J') goto L34;
        if (r0 == 'Z') goto L32;
        if (r0 == '[') goto L30;
        switch(r0) {
            case 66: goto L28;
            case 67: goto L26;
            case 68: goto L24;
            default: goto L21;
        };
    L21:
        f$$ExternalSyntheticBUOutline1.m("Invalid descriptor: ".concat(r2));
        return null;
    L24:
        return DOUBLE_TYPE;
    L26:
        return CHAR_TYPE;
    L28:
        return BYTE_TYPE;
    L30:
        return new Type(9, r2, r3, r4);
    L32:
        return BOOLEAN_TYPE;
    L34:
        return LONG_TYPE;
    L36:
        return INT_TYPE;
    L38:
        return VOID_TYPE;
    L40:
        return SHORT_TYPE;
    L42:
        return new Type(10, r2, r3 + 1, r4 - 1);
    L44:
        return FLOAT_TYPE;
    L46:
        return new Type(11, r2, r3, r4);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof Type) == true) goto L8;
        return false;
    L8:
        Type r82 = (Type) r8;
        int r1 = this.sort;
        int r3 = 10;
        if (r1 != 12) goto L11;
        r1 = 10;
    L11:
        int r5 = r82.sort;
        if (r5 == 12) goto L15;
        r3 = r5;
    L15:
        if (r1 == r3) goto L17;
        return false;
    L17:
        int r12 = this.valueBegin;
        int r32 = this.valueEnd;
        int r4 = r82.valueBegin;
        if ((r32 - r12) == (r82.valueEnd - r4)) goto L20;
        return false;
    L20:
        if (r12 >= r32) goto L25;
        if (this.valueBuffer.charAt(r12) != r82.valueBuffer.charAt(r4)) goto L23;
        r12 = r12 + 1;
        r4 = r4 + 1;
        goto L20
    L23:
        return false;
    L25:
        return true;
    }

    public Type[] getArgumentTypes() {
        return getArgumentTypes(getDescriptor());
    }

    public int getArgumentsAndReturnSizes() {
        return getArgumentsAndReturnSizes(getDescriptor());
    }

    public String getClassName() {
        switch(this.sort) {
            case 0: goto L29;
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L23;
            case 4: goto L21;
            case 5: goto L19;
            case 6: goto L17;
            case 7: goto L15;
            case 8: goto L13;
            case 9: goto L8;
            case 10: goto L7;
            case 11: goto L4;
            case 12: goto L7;
            default: goto L4;
        };
    L4:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L8:
        StringBuilder r0 = new StringBuilder(getElementType().getClassName());
        int r2 = getDimensions();
    L9:
        if (r2 <= 0) goto L12;
        r0.append(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
        r2 = r2 - 1;
        goto L9
    L12:
        return r0.toString();
    L13:
        return "double";
    L15:
        return "long";
    L17:
        return "float";
    L19:
        return "int";
    L21:
        return "short";
    L23:
        return "byte";
    L25:
        return "char";
    L27:
        return "boolean";
    L29:
        return "void";
    L7:
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
    }

    public String getDescriptor() {
        int r0 = this.sort;
        if (r0 == 10) goto L5;
        String r1 = this.valueBuffer;
        if (r0 != 12) goto L11;
        return "L" + r1.substring(this.valueBegin, this.valueEnd) + ';';
    L11:
        return r1.substring(this.valueBegin, this.valueEnd);
    L5:
        return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1);
    }

    public int getDimensions() {
        int r0 = 1;
    L4:
        if (this.valueBuffer.charAt(this.valueBegin + r0) != '[') goto L6;
        r0 = r0 + 1;
        goto L4
    L6:
        return r0;
    }

    public Type getElementType() {
        int r0 = getDimensions();
        return getTypeInternal(this.valueBuffer, this.valueBegin + r0, this.valueEnd);
    }

    public String getInternalName() {
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }

    public int getOpcode(int r2) {
        if (r2 == 46) goto L33;
        if (r2 == 79) goto L33;
        switch(this.sort) {
            case 0: goto L28;
            case 1: goto L50;
            case 2: goto L50;
            case 3: goto L50;
            case 4: goto L50;
            case 5: goto L50;
            case 6: goto L27;
            case 7: goto L25;
            case 8: goto L23;
            case 9: goto L14;
            case 10: goto L14;
            case 11: goto L12;
            case 12: goto L14;
            default: goto L9;
        };
    L9:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L12:
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L28:
        if (r2 != 172) goto L31;
        return 177;
    L31:
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L50:
        return r2;
    L14:
        if (r2 == 21) goto L21;
        if (r2 == 54) goto L21;
        if (r2 == 172) goto L21;
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L21:
        return r2 + 4;
    L23:
        return r2 + 3;
    L25:
        return r2 + 1;
    L27:
        return r2 + 2;
    L33:
        switch(this.sort) {
            case 0: goto L49;
            case 1: goto L48;
            case 2: goto L46;
            case 3: goto L48;
            case 4: goto L44;
            case 5: goto L51;
            case 6: goto L42;
            case 7: goto L40;
            case 8: goto L38;
            case 9: goto L36;
            case 10: goto L36;
            case 11: goto L49;
            case 12: goto L36;
            default: goto L34;
        };
    L34:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L49:
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L51:
        return r2;
    L36:
        return r2 + 4;
    L38:
        return r2 + 3;
    L40:
        return r2 + 1;
    L42:
        return r2 + 2;
    L44:
        return r2 + 7;
    L46:
        return r2 + 6;
    L48:
        return r2 + 5;
    }

    public Type getReturnType() {
        return getReturnType(getDescriptor());
    }

    public int getSize() {
        switch(this.sort) {
            case 0: goto L10;
            case 1: goto L8;
            case 2: goto L8;
            case 3: goto L8;
            case 4: goto L8;
            case 5: goto L8;
            case 6: goto L8;
            case 7: goto L6;
            case 8: goto L6;
            case 9: goto L8;
            case 10: goto L8;
            case 11: goto L4;
            case 12: goto L8;
            default: goto L4;
        };
    L4:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L6:
        return 2;
    L8:
        return 1;
    L10:
        return 0;
    }

    public int getSort() {
        int r1 = this.sort;
        if (r1 != 12) goto L6;
        return 10;
    L6:
        return r1;
    }

    public int hashCode() {
        int r0 = this.sort;
        if (r0 != 12) goto L5;
        int r1 = 10;
    L6:
        int r12 = r1 * 13;
        if (r0 < 9) goto L11;
        int r02 = this.valueBegin;
        int r2 = this.valueEnd;
    L9:
        if (r02 >= r2) goto L11;
        r12 = (r12 + this.valueBuffer.charAt(r02)) * 17;
        r02 = r02 + 1;
    L11:
        return r12;
    L5:
        r1 = r0;
        goto L6
    }

    public String toString() {
        return getDescriptor();
    }
}
