package org.mvel2.asm;

import c.f$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class Frame {
    static final int APPEND_FRAME = 252;
    private static final int ARRAY_OF = 67108864;
    private static final int BOOLEAN = 4194313;
    private static final int BYTE = 4194314;
    private static final int CHAR = 4194315;
    static final int CHOP_FRAME = 248;
    private static final int CONSTANT_KIND = 4194304;
    private static final int DIM_MASK = -67108864;
    private static final int DIM_SHIFT = 26;
    private static final int DIM_SIZE = 6;
    private static final int DOUBLE = 4194307;
    private static final int ELEMENT_OF = -67108864;
    private static final int FLAGS_SHIFT = 20;
    private static final int FLAGS_SIZE = 2;
    private static final int FLOAT = 4194306;
    static final int FULL_FRAME = 255;
    private static final int INTEGER = 4194305;
    private static final int ITEM_ASM_BOOLEAN = 9;
    private static final int ITEM_ASM_BYTE = 10;
    private static final int ITEM_ASM_CHAR = 11;
    private static final int ITEM_ASM_SHORT = 12;
    static final int ITEM_DOUBLE = 3;
    static final int ITEM_FLOAT = 2;
    static final int ITEM_INTEGER = 1;
    static final int ITEM_LONG = 4;
    static final int ITEM_NULL = 5;
    static final int ITEM_OBJECT = 7;
    static final int ITEM_TOP = 0;
    static final int ITEM_UNINITIALIZED = 8;
    static final int ITEM_UNINITIALIZED_THIS = 6;
    private static final int KIND_MASK = 62914560;
    private static final int KIND_SHIFT = 22;
    private static final int KIND_SIZE = 4;
    private static final int LOCAL_KIND = 16777216;
    private static final int LONG = 4194308;
    private static final int NULL = 4194309;
    private static final int REFERENCE_KIND = 8388608;
    static final int RESERVED = 128;
    static final int SAME_FRAME = 0;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    private static final int SHORT = 4194316;
    private static final int STACK_KIND = 20971520;
    private static final int TOP = 4194304;
    private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 1048576;
    private static final int UNINITIALIZED_KIND = 12582912;
    private static final int UNINITIALIZED_THIS = 4194310;
    private static final int VALUE_MASK = 1048575;
    private static final int VALUE_SIZE = 20;
    private int initializationCount;
    private int[] initializations;
    private int[] inputLocals;
    private int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    private short outputStackStart;
    private short outputStackTop;
    Label owner;

    public Frame(Label r1) {
        this.owner = r1;
    }

    private void addInitializedType(int r5) {
        if (this.initializations != null) goto L5;
        this.initializations = new int[2];
    L5:
        int r0 = this.initializations.length;
        int r1 = this.initializationCount;
        if (r1 < r0) goto L8;
        int[] r12 = new int[Math.max(r1 + 1, r0 * 2)];
        System.arraycopy(this.initializations, 0, r12, 0, r0);
        this.initializations = r12;
    L8:
        int[] r02 = this.initializations;
        int r13 = this.initializationCount;
        this.initializationCount = r13 + 1;
        r02[r13] = r5;
    }

    public static int getAbstractTypeFromApiFormat(SymbolTable r1, Object r2) {
        if ((r2 instanceof Integer) == false) goto L7;
        return ((Integer) r2).intValue() | 4194304;
    L7:
        if ((r2 instanceof String) == false) goto L11;
        return getAbstractTypeFromDescriptor(r1, Type.getObjectType((String) r2).getDescriptor(), 0);
    L11:
        return r1.addUninitializedType(_UrlKt.FRAGMENT_ENCODE_SET, ((Label) r2).bytecodeOffset) | UNINITIALIZED_KIND;
    }

    private static int getAbstractTypeFromDescriptor(SymbolTable r17, String r18, int r19) {
        char r2 = r18.charAt(r19);
        if (r2 != 'F') goto L5;
        return FLOAT;
    L5:
        if (r2 == 'L') goto L51;
        if (r2 != 'S') goto L9;
    L49:
        return INTEGER;
    L9:
        if (r2 != 'V') goto L11;
        return 0;
    L11:
        if (r2 == 'I') goto L49;
        if (r2 != 'J') goto L15;
        return LONG;
    L15:
        if (r2 == 'Z') goto L49;
        if (r2 == '[') goto L22;
        switch(r2) {
            case 66: goto L49;
            case 67: goto L49;
            case 68: goto L21;
            default: goto L19;
        };
    L19:
        f$$ExternalSyntheticBUOutline1.m("Invalid descriptor: ".concat(r18.substring(r19)));
        return 0;
    L21:
        return DOUBLE;
    L22:
        int r22 = r19 + 1;
    L24:
        if (r18.charAt(r22) != '[') goto L26;
        r22 = r22 + 1;
        goto L24
    L26:
        char r3 = r18.charAt(r22);
        if (r3 == 'F') goto L44;
        if (r3 == 'L') goto L43;
        if (r3 == 'S') goto L42;
        if (r3 == 'Z') goto L41;
        if (r3 == 'I') goto L40;
        if (r3 == 'J') goto L39;
        switch(r3) {
            case 66: goto L38;
            case 67: goto L37;
            case 68: goto L36;
            default: goto L34;
        };
    L34:
        f$$ExternalSyntheticBUOutline1.m("Invalid descriptor fragment: ".concat(r18.substring(r22)));
        return 0;
    L36:
        int r32 = DOUBLE;
    L46:
        return ((r22 - r19) << 26) | r32;
    L37:
        r32 = CHAR;
        goto L46
    L38:
        r32 = BYTE;
        goto L46
    L39:
        r32 = LONG;
        goto L46
    L40:
        r32 = INTEGER;
        goto L46
    L41:
        r32 = BOOLEAN;
        goto L46
    L42:
        r32 = SHORT;
        goto L46
    L43:
        r32 = r17.addType(r18.substring(r22 + 1, r18.length() - 1)) | 8388608;
        goto L46
    L44:
        r32 = FLOAT;
        goto L46
    L51:
        return r17.addType(r18.substring(r19 + 1, r18.length() - 1)) | 8388608;
    }

    public static int getAbstractTypeFromInternalName(SymbolTable r1, String r2) {
        return r1.addType(r2) | 8388608;
    }

    private int getConcreteOutputType(int r9, int r10) {
        int r0 = (-67108864) & r9;
        int r1 = KIND_MASK & r9;
        if (r1 != 16777216) goto L11;
        int r02 = r0 + this.inputLocals[r9 & VALUE_MASK];
        if ((r9 & 1048576) == 0) goto L9;
        if (r02 == LONG) goto L8;
        if (r02 != DOUBLE) goto L9;
    L8:
        return 4194304;
    L9:
        return r02;
    L11:
        if (r1 != STACK_KIND) goto L18;
        int r03 = r0 + this.inputStack[r10 - (r9 & VALUE_MASK)];
        if ((r9 & 1048576) == 0) goto L17;
        if (r03 == LONG) goto L16;
        if (r03 != DOUBLE) goto L17;
    L16:
        return 4194304;
    L17:
        return r03;
    L18:
        return r9;
    }

    private int getInitializedType(SymbolTable r9, int r10) {
        if (r10 != UNINITIALIZED_THIS) goto L5;
    L6:
        int r1 = 0;
    L8:
        if (r1 >= this.initializationCount) goto L24;
        int r2 = this.initializations[r1];
        int r3 = (-67108864) & r2;
        int r4 = KIND_MASK & r2;
        int r6 = r2 & VALUE_MASK;
        if (r4 != 16777216) goto L14;
        int r22 = this.inputLocals[r6];
    L12:
        r2 = r22 + r3;
    L16:
        if (r10 == r2) goto L18;
        r1 = r1 + 1;
        goto L8
    L18:
        if (r10 != UNINITIALIZED_THIS) goto L22;
        int r92 = r9.addType(r9.getClassName());
    L21:
        return 8388608 | r92;
    L22:
        r92 = r9.addType(r9.getType(r10 & VALUE_MASK).value);
        goto L21
    L14:
        if (r4 != STACK_KIND) goto L16;
        int[] r23 = this.inputStack;
        r22 = r23[r23.length - r6];
    L24:
        return r10;
    L5:
        if (((-4194304) & r10) != UNINITIALIZED_KIND) goto L24;
        goto L6
    }

    private int getLocal(int r3) {
        int[] r2 = this.outputLocals;
        if (r2 == null) goto L13;
        if (r3 >= r2.length) goto L13;
        int r1 = r2[r3];
        if (r1 != 0) goto L11;
        int r0 = 16777216 | r3;
        r2[r3] = r0;
        return r0;
    L11:
        return r1;
    L13:
        return r3 | 16777216;
    }

    private static boolean merge(SymbolTable r11, int r12, int[] r13, int r14) {
        int r0 = r13[r14];
        if (r0 != r12) goto L6;
        return false;
    L6:
        if ((67108863 & r12) != NULL) goto L11;
        if (r0 != NULL) goto L9;
        return false;
    L9:
        r12 = NULL;
    L11:
        if (r0 != 0) goto L14;
        r13[r14] = r12;
        return true;
    L14:
        int r5 = r0 & (-67108864);
        int r6 = 4194304;
        if (r5 == 0) goto L17;
    L27:
        if (r12 != NULL) goto L30;
        return false;
    L30:
        if ((r12 & (-4194304)) == ((-4194304) & r0)) goto L32;
        int r3 = r12 & (-67108864);
        if (r3 == 0) goto L39;
    L40:
        if (r3 != 0) goto L42;
    L44:
        if (r5 != 0) goto L46;
    L48:
        int r122 = Math.min(r3, r5) | 8388608;
        int r112 = r11.addType("java/lang/Object");
    L35:
        r6 = r122 | r112;
    L49:
        if (r6 == r0) goto L52;
        r13[r14] = r6;
        return true;
    L52:
        return false;
    L46:
        if ((r0 & KIND_MASK) == 8388608) goto L48;
        r5 = r5 - 67108864;
        goto L48
    L42:
        if ((r12 & KIND_MASK) == 8388608) goto L44;
        r3 = r3 - 67108864;
        goto L44
    L39:
        if ((r12 & KIND_MASK) != 8388608) goto L49;
    L32:
        if ((r0 & KIND_MASK) != 8388608) goto L34;
        r6 = ((r12 & (-67108864)) | 8388608) | r11.addMergedType(r12 & VALUE_MASK, VALUE_MASK & r0);
        goto L49
    L34:
        r122 = ((r12 & (-67108864)) - 67108864) | 8388608;
        r112 = r11.addType("java/lang/Object");
        goto L35
    L17:
        if ((r0 & KIND_MASK) == 8388608) goto L27;
        if (r0 != NULL) goto L49;
        if ((r12 & (-67108864)) == 0) goto L23;
    L26:
        r6 = r12;
        goto L49
    L23:
        if ((r12 & KIND_MASK) == 8388608) goto L26;
        r12 = 4194304;
        goto L26
    }

    private int pop() {
        short r0 = this.outputStackTop;
        if (r0 <= 0) goto L6;
        int[] r1 = this.outputStack;
        short r02 = (short) (r0 - 1);
        this.outputStackTop = r02;
        return r1[r02];
    L6:
        short r03 = (short) (this.outputStackStart - 1);
        this.outputStackStart = r03;
        return (-r03) | STACK_KIND;
    }

    private void pop(int r3) {
        short r0 = this.outputStackTop;
        if (r0 < r3) goto L6;
        this.outputStackTop = (short) (r0 - r3);
        return;
    L6:
        this.outputStackStart = (short) (this.outputStackStart - (r3 - r0));
        this.outputStackTop = 0;
    }

    private void pop(String r5) {
        char r0 = r5.charAt(0);
        if (r0 != '(') goto L7;
        pop((Type.getArgumentsAndReturnSizes(r5) >> 2) - 1);
        return;
    L7:
        if (r0 != 'J') goto L9;
    L13:
        pop(2);
        return;
    L9:
        if (r0 == 'D') goto L13;
        pop(1);
    }

    private void push(int r5) {
        if (this.outputStack != null) goto L5;
        this.outputStack = new int[10];
    L5:
        int r0 = this.outputStack.length;
        short r1 = this.outputStackTop;
        if (r1 < r0) goto L8;
        int[] r12 = new int[Math.max(r1 + 1, r0 * 2)];
        System.arraycopy(this.outputStack, 0, r12, 0, r0);
        this.outputStack = r12;
    L8:
        int[] r02 = this.outputStack;
        short r13 = this.outputStackTop;
        short r2 = (short) (r13 + 1);
        this.outputStackTop = r2;
        r02[r13] = r5;
        short r52 = (short) (this.outputStackStart + r2);
        Label r4 = this.owner;
        if (r52 <= r4.outputStackMax) goto L12;
        r4.outputStackMax = r52;
        return;
    }

    private void push(SymbolTable r4, String r5) {
        int r0 = 0;
        if (r5.charAt(0) != '(') goto L5;
        r0 = Type.getReturnTypeOffset(r5);
    L5:
        int r42 = getAbstractTypeFromDescriptor(r4, r5, r0);
        if (r42 == 0) goto L13;
        push(r42);
        if (r42 != LONG) goto L10;
    L11:
        push(4194304);
        return;
    L10:
        if (r42 == DOUBLE) goto L11;
        return;
    }

    public static void putAbstractType(SymbolTable r7, int r8, ByteVector r9) {
        int r0 = ((-67108864) & r8) >> 26;
        if (r0 != 0) goto L17;
        int r02 = r8 & VALUE_MASK;
        int r82 = r8 & KIND_MASK;
        if (r82 == 4194304) goto L15;
        if (r82 != 8388608) goto L8;
        r9.putByte(7).putShort(r7.addConstantClass(r7.getType(r02).value).index);
        return;
    L8:
        if (r82 != UNINITIALIZED_KIND) goto L11;
        r9.putByte(8).putShort((int) r7.getType(r02).data);
        return;
    L11:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L15:
        r9.putByte(r02);
        return;
    L17:
        StringBuilder r5 = new StringBuilder();
    L18:
        int r6 = r0 - 1;
        if (r0 <= 0) goto L22;
        r5.append('[');
        r0 = r6;
        goto L18
    L22:
        if ((r8 & KIND_MASK) != 8388608) goto L24;
        r5.append('L');
        r5.append(r7.getType(r8 & VALUE_MASK).value);
        r5.append(';');
    L43:
        r9.putByte(7).putShort(r7.addConstantClass(r5.toString()).index);
        return;
    L24:
        int r83 = r8 & VALUE_MASK;
        if (r83 != 1) goto L27;
        r5.append('I');
        goto L43
    L27:
        if (r83 != 2) goto L29;
        r5.append('F');
        goto L43
    L29:
        if (r83 != 3) goto L31;
        r5.append('D');
        goto L43
    L31:
        if (r83 == 4) goto L39;
        switch(r83) {
            case 9: goto L38;
            case 10: goto L37;
            case 11: goto L36;
            case 12: goto L35;
            default: goto L33;
        };
    L33:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L35:
        r5.append('S');
        goto L43
    L36:
        r5.append('C');
        goto L43
    L37:
        r5.append('B');
        goto L43
    L38:
        r5.append('Z');
        goto L43
    L39:
        r5.append('J');
        goto L43
    }

    private void setLocal(int r5, int r6) {
        if (this.outputLocals != null) goto L5;
        this.outputLocals = new int[10];
    L5:
        int r0 = this.outputLocals.length;
        if (r5 < r0) goto L8;
        int[] r1 = new int[Math.max(r5 + 1, r0 * 2)];
        System.arraycopy(this.outputLocals, 0, r1, 0, r0);
        this.outputLocals = r1;
    L8:
        this.outputLocals[r5] = r6;
    }

    public final void accept(MethodWriter r13) {
        int[] r0 = this.inputLocals;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L3:
        int r6 = 2;
        if (r2 >= r0.length) goto L14;
        int r5 = r0[r2];
        if (r5 == LONG) goto L10;
        if (r5 == DOUBLE) goto L10;
        r6 = 1;
    L10:
        r2 = r2 + r6;
        if (r5 == 4194304) goto L12;
        r3 = r3 + (r4 + 1);
        r4 = 0;
        goto L3
    L12:
        r4 = r4 + 1;
        goto L3
    L14:
        int[] r22 = this.inputStack;
        int r42 = 0;
        int r52 = 0;
    L16:
        if (r42 >= r22.length) goto L24;
        int r10 = r22[r42];
        if (r10 == LONG) goto L22;
        if (r10 == DOUBLE) goto L22;
        int r102 = 1;
    L23:
        r42 = r42 + r102;
        r52 = r52 + 1;
    L22:
        r102 = 2;
        goto L23
    L24:
        int r12 = r13.visitFrameStart(this.owner.bytecodeOffset, r3, r52);
        int r43 = 0;
    L25:
        int r103 = r3 - 1;
        if (r3 <= 0) goto L34;
        int r32 = r0[r43];
        if (r32 == LONG) goto L32;
        if (r32 == DOUBLE) goto L32;
        int r11 = 1;
    L33:
        r43 = r43 + r11;
        r13.visitAbstractType(r12, r32);
        r3 = r103;
        r12 = r12 + 1;
    L32:
        r11 = 2;
    L34:
        int r02 = r52 - 1;
        if (r52 <= 0) goto L43;
        int r33 = r22[r1];
        if (r33 == LONG) goto L41;
        if (r33 == DOUBLE) goto L41;
        int r44 = 1;
    L42:
        r1 = r1 + r44;
        r13.visitAbstractType(r12, r33);
        r52 = r02;
        r12 = r12 + 1;
    L41:
        r44 = 2;
        goto L42
    L43:
        r13.visitFrameEnd();
    }

    public final void copyFrom(Frame r2) {
        this.inputLocals = r2.inputLocals;
        this.inputStack = r2.inputStack;
        this.outputStackStart = 0;
        this.outputLocals = r2.outputLocals;
        this.outputStack = r2.outputStack;
        this.outputStackTop = r2.outputStackTop;
        this.initializationCount = r2.initializationCount;
        this.initializations = r2.initializations;
    }

    public void execute(int r19, int r20, Symbol r21, SymbolTable r22) {
        switch(r19) {
            case 0: goto L184;
            case 1: goto L182;
            case 2: goto L180;
            case 3: goto L180;
            case 4: goto L180;
            case 5: goto L180;
            case 6: goto L180;
            case 7: goto L180;
            case 8: goto L180;
            case 9: goto L178;
            case 10: goto L178;
            case 11: goto L176;
            case 12: goto L176;
            case 13: goto L176;
            case 14: goto L174;
            case 15: goto L174;
            case 16: goto L180;
            case 17: goto L180;
            case 18: goto L151;
            default: goto L4;
        };
    L4:
        switch(r19) {
            case 21: goto L180;
            case 22: goto L178;
            case 23: goto L176;
            case 24: goto L174;
            case 25: goto L149;
            default: goto L6;
        };
    L149:
        push(getLocal(r20));
        return;
    L6:
        switch(r19) {
            case 46: goto L147;
            case 47: goto L145;
            case 48: goto L143;
            case 49: goto L141;
            case 50: goto L135;
            case 51: goto L147;
            case 52: goto L147;
            case 53: goto L147;
            case 54: goto L122;
            case 55: goto L109;
            case 56: goto L122;
            case 57: goto L109;
            case 58: goto L122;
            default: goto L8;
        };
    L109:
        pop(1);
        setLocal(r20, pop());
        setLocal(r20 + 1, 4194304);
        if (r20 <= 0) goto L186;
        int r1 = r20 - 1;
        int r2 = getLocal(r1);
        if (r2 == LONG) goto L120;
        if (r2 == DOUBLE) goto L120;
        int r3 = r2 & KIND_MASK;
        if (r3 == 16777216) goto L118;
        if (r3 == STACK_KIND) goto L118;
        return;
    L118:
        setLocal(r1, r2 | 1048576);
        return;
    L120:
        setLocal(r1, 4194304);
        return;
    L186:
        return;
    L122:
        setLocal(r20, pop());
        if (r20 <= 0) goto L188;
        int r12 = r20 - 1;
        int r23 = getLocal(r12);
        if (r23 == LONG) goto L133;
        if (r23 == DOUBLE) goto L133;
        int r32 = r23 & KIND_MASK;
        if (r32 == 16777216) goto L131;
        if (r32 == STACK_KIND) goto L131;
        return;
    L131:
        setLocal(r12, r23 | 1048576);
        return;
    L133:
        setLocal(r12, 4194304);
        return;
    L188:
        return;
    L135:
        pop(1);
        int r13 = pop();
        if (r13 == NULL) goto L139;
        r13 = r13 - 67108864;
    L139:
        push(r13);
        return;
    L141:
        pop(2);
        push(DOUBLE);
        push(4194304);
        return;
    L143:
        pop(2);
        push(FLOAT);
        return;
    L145:
        pop(2);
        push(LONG);
        push(4194304);
        return;
    L147:
        pop(2);
        push(INTEGER);
        return;
    L8:
        switch(r19) {
            case 79: goto L107;
            case 80: goto L105;
            case 81: goto L107;
            case 82: goto L105;
            case 83: goto L107;
            case 84: goto L107;
            case 85: goto L107;
            case 86: goto L107;
            case 87: goto L103;
            case 88: goto L101;
            case 89: goto L99;
            case 90: goto L97;
            case 91: goto L95;
            case 92: goto L93;
            case 93: goto L91;
            case 94: goto L89;
            case 95: goto L87;
            case 96: goto L147;
            case 97: goto L85;
            case 98: goto L143;
            case 99: goto L83;
            case 100: goto L147;
            case 101: goto L85;
            case 102: goto L143;
            case 103: goto L83;
            case 104: goto L147;
            case 105: goto L85;
            case 106: goto L143;
            case 107: goto L83;
            case 108: goto L147;
            case 109: goto L85;
            case 110: goto L143;
            case 111: goto L83;
            case 112: goto L147;
            case 113: goto L85;
            case 114: goto L143;
            case 115: goto L83;
            case 116: goto L185;
            case 117: goto L185;
            case 118: goto L185;
            case 119: goto L185;
            case 120: goto L147;
            case 121: goto L81;
            case 122: goto L147;
            case 123: goto L81;
            case 124: goto L147;
            case 125: goto L81;
            case 126: goto L147;
            case 127: goto L85;
            case 128: goto L147;
            case 129: goto L85;
            case 130: goto L147;
            case 131: goto L85;
            case 132: goto L79;
            case 133: goto L77;
            case 134: goto L75;
            case 135: goto L73;
            case 136: goto L147;
            case 137: goto L143;
            case 138: goto L141;
            case 139: goto L71;
            case 140: goto L77;
            case 141: goto L73;
            case 142: goto L147;
            case 143: goto L145;
            case 144: goto L143;
            case 145: goto L185;
            case 146: goto L185;
            case 147: goto L185;
            case 148: goto L69;
            case 149: goto L147;
            case 150: goto L147;
            case 151: goto L69;
            case 152: goto L69;
            case 153: goto L103;
            case 154: goto L103;
            case 155: goto L103;
            case 156: goto L103;
            case 157: goto L103;
            case 158: goto L103;
            case 159: goto L101;
            case 160: goto L101;
            case 161: goto L101;
            case 162: goto L101;
            case 163: goto L101;
            case 164: goto L101;
            case 165: goto L101;
            case 166: goto L101;
            case 167: goto L185;
            case 168: goto L67;
            case 169: goto L67;
            case 170: goto L103;
            case 171: goto L103;
            case 172: goto L103;
            case 173: goto L101;
            case 174: goto L103;
            case 175: goto L101;
            case 176: goto L103;
            case 177: goto L185;
            case 178: goto L65;
            case 179: goto L63;
            case 180: goto L61;
            case 181: goto L59;
            case 182: goto L50;
            case 183: goto L50;
            case 184: goto L50;
            case 185: goto L50;
            case 186: goto L48;
            case 187: goto L46;
            case 188: goto L26;
            case 189: goto L20;
            case 190: goto L71;
            case 191: goto L103;
            case 192: goto L14;
            case 193: goto L71;
            case 194: goto L103;
            case 195: goto L103;
            default: goto L9;
        };
    L9:
        switch(r19) {
            case 197: goto L12;
            case 198: goto L103;
            case 199: goto L103;
            default: goto L10;
        };
    L10:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L12:
        pop(r20);
        push(r22, r21.value);
        return;
    L14:
        String r14 = r21.value;
        pop();
        if (r14.charAt(0) != '[') goto L18;
        push(r22, r14);
        return;
    L18:
        push(r22.addType(r14) | 8388608);
        return;
    L20:
        String r15 = r21.value;
        pop();
        if (r15.charAt(0) != '[') goto L24;
        push(r22, "[".concat(r15));
        return;
    L24:
        push(r22.addType(r15) | 75497472);
        return;
    L26:
        pop();
        switch(r20) {
            case 4: goto L44;
            case 5: goto L42;
            case 6: goto L40;
            case 7: goto L38;
            case 8: goto L36;
            case 9: goto L34;
            case 10: goto L32;
            case 11: goto L30;
            default: goto L28;
        };
    L28:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        push(71303172);
        return;
    L32:
        push(71303169);
        return;
    L34:
        push(71303180);
        return;
    L36:
        push(71303178);
        return;
    L38:
        push(71303171);
        return;
    L40:
        push(71303170);
        return;
    L42:
        push(71303179);
        return;
    L44:
        push(71303177);
        return;
    L46:
        push(r22.addUninitializedType(r21.value, r20) | UNINITIALIZED_KIND);
        return;
    L48:
        pop(r21.value);
        push(r22, r21.value);
        return;
    L50:
        pop(r21.value);
        if (r19 == 184) goto L57;
        int r24 = pop();
        if (r19 != 183) goto L57;
        if (r21.name.charAt(0) != '<') goto L57;
        addInitializedType(r24);
    L57:
        push(r22, r21.value);
        return;
    L59:
        pop(r21.value);
        pop();
        return;
    L61:
        pop(1);
        push(r22, r21.value);
        return;
    L63:
        pop(r21.value);
        return;
    L65:
        push(r22, r21.value);
        return;
    L67:
        f$$ExternalSyntheticBUOutline1.m("JSR/RET are not supported with computeFrames option");
        return;
    L69:
        pop(4);
        push(INTEGER);
        return;
    L71:
        pop(1);
        push(INTEGER);
        return;
    L73:
        pop(1);
        push(DOUBLE);
        push(4194304);
        return;
    L75:
        pop(1);
        push(FLOAT);
        return;
    L77:
        pop(1);
        push(LONG);
        push(4194304);
        return;
    L79:
        setLocal(r20, INTEGER);
        return;
    L81:
        pop(3);
        push(LONG);
        push(4194304);
        return;
    L83:
        pop(4);
        push(DOUBLE);
        push(4194304);
        return;
    L85:
        pop(4);
        push(LONG);
        push(4194304);
        return;
    L87:
        int r16 = pop();
        int r25 = pop();
        push(r16);
        push(r25);
        return;
    L89:
        int r17 = pop();
        int r26 = pop();
        int r33 = pop();
        int r4 = pop();
        push(r26);
        push(r17);
        push(r4);
        push(r33);
        push(r26);
        push(r17);
        return;
    L91:
        int r18 = pop();
        int r27 = pop();
        int r34 = pop();
        push(r27);
        push(r18);
        push(r34);
        push(r27);
        push(r18);
        return;
    L93:
        int r110 = pop();
        int r28 = pop();
        push(r28);
        push(r110);
        push(r28);
        push(r110);
        return;
    L95:
        int r111 = pop();
        int r29 = pop();
        int r35 = pop();
        push(r111);
        push(r35);
        push(r29);
        push(r111);
        return;
    L97:
        int r112 = pop();
        int r210 = pop();
        push(r112);
        push(r210);
        push(r112);
        return;
    L99:
        int r113 = pop();
        push(r113);
        push(r113);
        return;
    L101:
        pop(2);
        return;
    L103:
        pop(1);
        return;
    L105:
        pop(4);
        return;
    L107:
        pop(3);
        return;
    L185:
        return;
    L151:
        int r114 = r21.tag;
        switch(r114) {
            case 3: goto L172;
            case 4: goto L170;
            case 5: goto L168;
            case 6: goto L166;
            case 7: goto L164;
            case 8: goto L162;
            default: goto L153;
        };
    L153:
        switch(r114) {
            case 15: goto L160;
            case 16: goto L158;
            case 17: goto L156;
            default: goto L154;
        };
    L154:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L156:
        push(r22, r21.value);
        return;
    L158:
        push(r22.addType("java/lang/invoke/MethodType") | 8388608);
        return;
    L160:
        push(r22.addType("java/lang/invoke/MethodHandle") | 8388608);
        return;
    L162:
        push(r22.addType("java/lang/String") | 8388608);
        return;
    L164:
        push(r22.addType("java/lang/Class") | 8388608);
        return;
    L166:
        push(DOUBLE);
        push(4194304);
        return;
    L168:
        push(LONG);
        push(4194304);
        return;
    L170:
        push(FLOAT);
        return;
    L172:
        push(INTEGER);
        return;
    L174:
        push(DOUBLE);
        push(4194304);
        return;
    L176:
        push(FLOAT);
        return;
    L178:
        push(LONG);
        push(4194304);
        return;
    L180:
        push(INTEGER);
        return;
    L182:
        push(NULL);
        return;
    }

    public final int getInputStackSize() {
        return this.inputStack.length;
    }

    public final boolean merge(SymbolTable r9, Frame r10, int r11) {
        int r0 = this.inputLocals.length;
        int r1 = this.inputStack.length;
        boolean r3 = true;
        int r4 = 0;
        if (r10.inputLocals != null) goto L5;
        r10.inputLocals = new int[r0];
        boolean r2 = true;
    L6:
        int r5 = 0;
    L7:
        if (r5 >= r0) goto L21;
        int[] r6 = this.outputLocals;
        if (r6 != null) goto L11;
    L16:
        int r62 = this.inputLocals[r5];
    L18:
        if (this.initializations == null) goto L20;
        r62 = getInitializedType(r9, r62);
    L20:
        r2 = r2 | merge(r9, r62, r10.inputLocals, r5);
        r5 = r5 + 1;
        goto L7
    L11:
        if (r5 >= r6.length) goto L16;
        int r63 = r6[r5];
        if (r63 != 0) goto L15;
        r62 = this.inputLocals[r5];
        goto L18
    L15:
        r62 = getConcreteOutputType(r63, r1);
        goto L18
    L21:
        if (r11 <= 0) goto L31;
        int r12 = 0;
    L23:
        if (r12 >= r0) goto L26;
        r2 = r2 | merge(r9, this.inputLocals[r12], r10.inputLocals, r12);
        r12 = r12 + 1;
        goto L23
    L26:
        if (r10.inputStack != null) goto L28;
        r10.inputStack = new int[1];
    L30:
        return merge(r9, r11, r10.inputStack, 0) | r3;
    L28:
        r3 = r2;
        goto L30
    L31:
        int r112 = this.inputStack.length + this.outputStackStart;
        if (r10.inputStack != null) goto L34;
        r10.inputStack = new int[this.outputStackTop + r112];
    L35:
        int r02 = 0;
    L36:
        if (r02 >= r112) goto L42;
        int r22 = this.inputStack[r02];
        if (this.initializations == null) goto L40;
        r22 = getInitializedType(r9, r22);
    L40:
        r3 = r3 | merge(r9, r22, r10.inputStack, r02);
        r02 = r02 + 1;
    L42:
        if (r4 >= this.outputStackTop) goto L47;
        int r03 = getConcreteOutputType(this.outputStack[r4], r1);
        if (this.initializations == null) goto L46;
        r03 = getInitializedType(r9, r03);
    L46:
        r3 = r3 | merge(r9, r03, r10.inputStack, r112 + r4);
        r4 = r4 + 1;
        goto L42
    L47:
        return r3;
    L34:
        r3 = r2;
        goto L35
    L5:
        r2 = false;
        goto L6
    }

    public final void setInputFrameFromApiFormat(SymbolTable r8, int r9, Object[] r10, int r11, Object[] r12) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r9) goto L13;
        int r5 = r2 + 1;
        this.inputLocals[r2] = getAbstractTypeFromApiFormat(r8, r10[r1]);
        Object r4 = r10[r1];
        if (r4 != Opcodes.LONG) goto L8;
    L11:
        r2 = r2 + 2;
        this.inputLocals[r5] = 4194304;
    L12:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r4 == Opcodes.DOUBLE) goto L11;
        r2 = r5;
    L13:
        int[] r92 = this.inputLocals;
        if (r2 >= r92.length) goto L16;
        r92[r2] = 4194304;
        r2 = r2 + 1;
        goto L13
    L16:
        int r93 = 0;
        int r102 = 0;
    L17:
        if (r93 >= r11) goto L24;
        Object r13 = r12[r93];
        if (r13 != Opcodes.LONG) goto L21;
    L22:
        r102 = r102 + 1;
    L23:
        r93 = r93 + 1;
        goto L17
    L21:
        if (r13 != Opcodes.DOUBLE) goto L23;
    L24:
        this.inputStack = new int[r102 + r11];
        int r94 = 0;
        int r103 = 0;
    L25:
        if (r94 >= r11) goto L34;
        int r22 = r103 + 1;
        this.inputStack[r103] = getAbstractTypeFromApiFormat(r8, r12[r94]);
        Object r14 = r12[r94];
        if (r14 != Opcodes.LONG) goto L29;
    L32:
        r103 = r103 + 2;
        this.inputStack[r22] = 4194304;
    L33:
        r94 = r94 + 1;
        goto L25
    L29:
        if (r14 == Opcodes.DOUBLE) goto L32;
        r103 = r22;
        goto L33
    L34:
        this.outputStackTop = 0;
        this.initializationCount = 0;
    }

    public final void setInputFrameFromDescriptor(SymbolTable r9, int r10, String r11, int r12) {
        int[] r0 = new int[r12];
        this.inputLocals = r0;
        this.inputStack = new int[0];
        if ((r10 & 8) != 0) goto L8;
        int r2 = 1;
        if ((r10 & 262144) != 0) goto L7;
        r0[0] = r9.addType(r9.getClassName()) | 8388608;
    L9:
        Type[] r102 = Type.getArgumentTypes(r11);
        int r112 = r102.length;
        int r02 = 0;
    L11:
        if (r02 >= r112) goto L20;
        int r4 = getAbstractTypeFromDescriptor(r9, r102[r02].getDescriptor(), 0);
        int[] r5 = this.inputLocals;
        int r6 = r2 + 1;
        r5[r2] = r4;
        if (r4 != LONG) goto L15;
    L18:
        r2 = r2 + 2;
        r5[r6] = 4194304;
    L19:
        r02 = r02 + 1;
        goto L11
    L15:
        if (r4 == DOUBLE) goto L18;
        r2 = r6;
    L20:
        if (r2 >= r12) goto L22;
        this.inputLocals[r2] = 4194304;
        r2 = r2 + 1;
        goto L20
    L22:
        return;
    L7:
        r0[0] = UNINITIALIZED_THIS;
        goto L9
    L8:
        r2 = 0;
        goto L9
    }
}
