package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstInvokeDynamic;
import com.android.dx.rop.cst.CstKnownNull;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.Bits;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class BytecodeArray {
    public static final Visitor EMPTY_VISITOR = null;
    private final ByteArray bytes;
    private final ConstantPool pool;

    public static class BaseVisitor implements Visitor {
        private int previousOffset;

        public BaseVisitor() {
            this.previousOffset = -1;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public int getPreviousOffset() {
            return this.previousOffset;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void setPreviousOffset(int r1) {
            this.previousOffset = r1;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitBranch(int r1, int r2, int r3, int r4) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitConstant(int r1, int r2, int r3, Constant r4, int r5) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitInvalid(int r1, int r2, int r3) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitLocal(int r1, int r2, int r3, int r4, Type r5, int r6) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNewarray(int r1, int r2, CstType r3, ArrayList<Constant> r4) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNoArgs(int r1, int r2, int r3, Type r4) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitSwitch(int r1, int r2, int r3, SwitchList r4, int r5) {
        }
    }

    public class ConstantParserVisitor extends BaseVisitor {
        Constant cst;
        int length;
        final /* synthetic */ BytecodeArray this$0;
        int value;

        public ConstantParserVisitor(BytecodeArray r1) {
            this.this$0 = r1;
        }

        private void clear() {
            this.length = 0;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public int getPreviousOffset() {
            return -1;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void setPreviousOffset(int r1) {
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitBranch(int r1, int r2, int r3, int r4) {
            clear();
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitConstant(int r1, int r2, int r3, Constant r4, int r5) {
            this.cst = r4;
            this.length = r3;
            this.value = r5;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitInvalid(int r1, int r2, int r3) {
            clear();
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitLocal(int r1, int r2, int r3, int r4, Type r5, int r6) {
            clear();
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNewarray(int r1, int r2, CstType r3, ArrayList<Constant> r4) {
            clear();
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNoArgs(int r1, int r2, int r3, Type r4) {
            clear();
        }

        @Override // com.android.dx.cf.code.BytecodeArray.BaseVisitor, com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitSwitch(int r1, int r2, int r3, SwitchList r4, int r5) {
            clear();
        }
    }

    public interface Visitor {
        int getPreviousOffset();

        void setPreviousOffset(int r1);

        void visitBranch(int r1, int r2, int r3, int r4);

        void visitConstant(int r1, int r2, int r3, Constant r4, int r5);

        void visitInvalid(int r1, int r2, int r3);

        void visitLocal(int r1, int r2, int r3, int r4, Type r5, int r6);

        void visitNewarray(int r1, int r2, CstType r3, ArrayList<Constant> r4);

        void visitNoArgs(int r1, int r2, int r3, Type r4);

        void visitSwitch(int r1, int r2, int r3, SwitchList r4, int r5);
    }

    static {
        EMPTY_VISITOR = new BaseVisitor();
    }

    public BytecodeArray(ByteArray r2, ConstantPool r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.bytes = r2;
        this.pool = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("pool == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("bytes == null");
        throw null;
    }

    private int parseLookupswitch(int r10, Visitor r11) {
        int r0 = (r10 + 4) & (-4);
        int r1 = r10 + 1;
        int r2 = 0;
        int r8 = 0;
    L3:
        ByteArray r3 = this.bytes;
        if (r1 >= r0) goto L6;
        r8 = (r8 << 8) | r3.getUnsignedByte(r1);
        r1 = r1 + 1;
        goto L3
    L6:
        int r12 = r3.getInt(r0) + r10;
        int r32 = this.bytes.getInt(r0 + 4);
        int r02 = r0 + 8;
        SwitchList r7 = new SwitchList(r32);
    L7:
        if (r2 >= r32) goto L9;
        int r4 = this.bytes.getInt(r02);
        int r5 = this.bytes.getInt(r02 + 4) + r10;
        r02 = r02 + 8;
        r7.add(r4, r5);
        r2 = r2 + 1;
        goto L7
    L9:
        r7.setDefaultTarget(r12);
        r7.removeSuperfluousDefaults();
        r7.setImmutable();
        int r6 = r02 - r10;
        r11.visitSwitch(171, r10, r6, r7, r8);
        return r6;
    }

    private int parseNewarray(int r12, Visitor r13) {
        int r0 = this.bytes.getUnsignedByte(r12 + 1);
        switch(r0) {
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            case 7: goto L10;
            case 8: goto L9;
            case 9: goto L8;
            case 10: goto L7;
            case 11: goto L6;
            default: goto L5;
        };
    L6:
        CstType r1 = CstType.LONG_ARRAY;
    L14:
        int r2 = r13.getPreviousOffset();
        ConstantParserVisitor r3 = new ConstantParserVisitor(this);
        int r4 = 0;
        if (r2 < 0) goto L21;
        parseInstruction(r2, r3);
        if ((r3.cst instanceof CstInteger) == false) goto L21;
        if ((r3.length + r2) != r12) goto L21;
        int r22 = r3.value;
    L22:
        int r5 = r12 + 2;
        ArrayList<Constant> r6 = new ArrayList();
        if (r22 == 0) goto L65;
    L24:
        int r8 = r5 + 1;
        if (this.bytes.getUnsignedByte(r5) != 89) goto L65;
        parseInstruction(r8, r3);
        int r7 = r3.length;
        if (r7 == 0) goto L65;
        if ((r3.cst instanceof CstInteger) == false) goto L65;
        if (r3.value != r4) goto L65;
        int r82 = r8 + r7;
        parseInstruction(r82, r3);
        int r72 = r3.length;
        if (r72 == 0) goto L65;
        Constant r9 = r3.cst;
        if ((r9 instanceof CstLiteralBits) == false) goto L65;
        int r83 = r82 + r72;
        r6.add(r9);
        int r92 = r83 + 1;
        int r73 = this.bytes.getUnsignedByte(r83);
        switch(r0) {
            case 4: goto L61;
            case 5: goto L58;
            case 6: goto L55;
            case 7: goto L52;
            case 8: goto L61;
            case 9: goto L49;
            case 10: goto L46;
            case 11: goto L43;
            default: goto L65;
        };
    L43:
        if (r73 != 80) goto L65;
    L63:
        r4 = r4 + 1;
        r5 = r92;
        goto L24
    L46:
        if (r73 == 79) goto L63;
    L49:
        if (r73 == 86) goto L63;
    L52:
        if (r73 == 82) goto L63;
    L55:
        if (r73 == 81) goto L63;
    L58:
        if (r73 == 85) goto L63;
    L61:
        if (r73 == 84) goto L63;
    L65:
        if (r4 < 2) goto L70;
        if (r4 != r22) goto L70;
        int r52 = r5 - r12;
        r13.visitNewarray(r12, r52, r1, r6);
        return r52;
    L70:
        r13.visitNewarray(r12, 2, r1, null);
        return 2;
    L21:
        r22 = 0;
        goto L22
    L7:
        r1 = CstType.INT_ARRAY;
        goto L14
    L8:
        r1 = CstType.SHORT_ARRAY;
        goto L14
    L9:
        r1 = CstType.BYTE_ARRAY;
        goto L14
    L10:
        r1 = CstType.DOUBLE_ARRAY;
        goto L14
    L11:
        r1 = CstType.FLOAT_ARRAY;
        goto L14
    L12:
        r1 = CstType.CHAR_ARRAY;
        goto L14
    L13:
        r1 = CstType.BOOLEAN_ARRAY;
        goto L14
    L5:
        throw new SimException("bad newarray code " + Hex.u1(r0));
    }

    private int parseTableswitch(int r10, Visitor r11) {
        int r0 = (r10 + 4) & (-4);
        int r1 = r10 + 1;
        int r2 = 0;
        int r8 = 0;
    L3:
        ByteArray r3 = this.bytes;
        if (r1 >= r0) goto L6;
        r8 = (r8 << 8) | r3.getUnsignedByte(r1);
        r1 = r1 + 1;
        goto L3
    L6:
        int r12 = r3.getInt(r0) + r10;
        int r32 = this.bytes.getInt(r0 + 4);
        int r4 = this.bytes.getInt(r0 + 8);
        int r5 = (r4 - r32) + 1;
        int r02 = r0 + 12;
        if (r32 > r4) goto L14;
        SwitchList r7 = new SwitchList(r5);
    L9:
        if (r2 >= r5) goto L11;
        int r42 = this.bytes.getInt(r02) + r10;
        r02 = r02 + 4;
        r7.add(r32 + r2, r42);
        r2 = r2 + 1;
        goto L9
    L11:
        r7.setDefaultTarget(r12);
        r7.removeSuperfluousDefaults();
        r7.setImmutable();
        int r6 = r02 - r10;
        r11.visitSwitch(171, r10, r6, r7, r8);
        return r6;
    L14:
        throw new SimException("low / high inversion");
    }

    private int parseWide(int r12, Visitor r13) {
        int r3 = this.bytes.getUnsignedByte(r12 + 1);
        int r8 = this.bytes.getUnsignedShort(r12 + 2);
        if (r3 != 132) goto L5;
        int r11 = this.bytes.getShort(r12 + 4);
        r13.visitLocal(r3, r12, 6, r8, Type.INT, r11);
        return 6;
    L5:
        if (r3 == 169) goto L30;
        switch(r3) {
            case 21: goto L28;
            case 22: goto L26;
            case 23: goto L24;
            case 24: goto L22;
            case 25: goto L20;
            default: goto L7;
        };
    L7:
        switch(r3) {
            case 54: goto L18;
            case 55: goto L16;
            case 56: goto L14;
            case 57: goto L12;
            case 58: goto L10;
            default: goto L8;
        };
    L8:
        r13.visitInvalid(196, r12, 1);
        return 1;
    L10:
        r13.visitLocal(54, r12, 4, r8, Type.OBJECT, 0);
        return 4;
    L12:
        r13.visitLocal(54, r12, 4, r8, Type.DOUBLE, 0);
        return 4;
    L14:
        r13.visitLocal(54, r12, 4, r8, Type.FLOAT, 0);
        return 4;
    L16:
        r13.visitLocal(54, r12, 4, r8, Type.LONG, 0);
        return 4;
    L18:
        r13.visitLocal(54, r12, 4, r8, Type.INT, 0);
        return 4;
    L20:
        r13.visitLocal(21, r12, 4, r8, Type.OBJECT, 0);
        return 4;
    L22:
        r13.visitLocal(21, r12, 4, r8, Type.DOUBLE, 0);
        return 4;
    L24:
        r13.visitLocal(21, r12, 4, r8, Type.FLOAT, 0);
        return 4;
    L26:
        r13.visitLocal(21, r12, 4, r8, Type.LONG, 0);
        return 4;
    L28:
        r13.visitLocal(21, r12, 4, r8, Type.INT, 0);
        return 4;
    L30:
        r13.visitLocal(r3, r12, 4, r8, Type.RETURN_ADDRESS, 0);
        return 4;
    }

    public int byteLength() {
        return this.bytes.size() + 4;
    }

    public void forEach(Visitor r4) {
        int r0 = this.bytes.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r1 = r1 + parseInstruction(r1, r4);
        goto L3
    }

    public ByteArray getBytes() {
        return this.bytes;
    }

    public int[] getInstructionOffsets() {
        int r0 = this.bytes.size();
        int[] r1 = Bits.makeBitSet(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        Bits.set(r1, r2, true);
        r2 = r2 + parseInstruction(r2, null);
        goto L3
    L5:
        return r1;
    }

    public int parseInstruction(int r13, Visitor r14) {
        if (r14 != null) goto L5;
        r14 = EMPTY_VISITOR;
    L5:
        Visitor r2 = r14;
        int r3 = this.bytes.getUnsignedByte(r13);     // Catch: RuntimeException -> L29 SimException -> L31
        ByteOps.opInfo(r3);     // Catch: RuntimeException -> L29 SimException -> L31
        int r142 = 0;
        int r4 = 172;
        switch(r3) {
            case 0: goto L208;
            case 1: goto L206;
            case 2: goto L204;
            case 3: goto L202;
            case 4: goto L200;
            case 5: goto L198;
            case 6: goto L196;
            case 7: goto L194;
            case 8: goto L192;
            case 9: goto L190;
            case 10: goto L188;
            case 11: goto L186;
            case 12: goto L184;
            case 13: goto L182;
            case 14: goto L180;
            case 15: goto L178;
            case 16: goto L176;
            case 17: goto L174;
            case 18: goto L169;
            case 19: goto L164;
            case 20: goto L162;
            case 21: goto L160;
            case 22: goto L158;
            case 23: goto L156;
            case 24: goto L154;
            case 25: goto L152;
            case 26: goto L150;
            case 27: goto L150;
            case 28: goto L150;
            case 29: goto L150;
            case 30: goto L148;
            case 31: goto L148;
            case 32: goto L148;
            case 33: goto L148;
            case 34: goto L146;
            case 35: goto L146;
            case 36: goto L146;
            case 37: goto L146;
            case 38: goto L144;
            case 39: goto L144;
            case 40: goto L144;
            case 41: goto L144;
            case 42: goto L142;
            case 43: goto L142;
            case 44: goto L142;
            case 45: goto L142;
            case 46: goto L140;
            case 47: goto L138;
            case 48: goto L136;
            case 49: goto L134;
            case 50: goto L132;
            case 51: goto L130;
            case 52: goto L128;
            case 53: goto L126;
            case 54: goto L124;
            case 55: goto L122;
            case 56: goto L120;
            case 57: goto L118;
            case 58: goto L116;
            case 59: goto L114;
            case 60: goto L114;
            case 61: goto L114;
            case 62: goto L114;
            case 63: goto L112;
            case 64: goto L112;
            case 65: goto L112;
            case 66: goto L112;
            case 67: goto L110;
            case 68: goto L110;
            case 69: goto L110;
            case 70: goto L110;
            case 71: goto L108;
            case 72: goto L108;
            case 73: goto L108;
            case 74: goto L108;
            case 75: goto L106;
            case 76: goto L106;
            case 77: goto L106;
            case 78: goto L106;
            case 79: goto L104;
            case 80: goto L102;
            case 81: goto L100;
            case 82: goto L98;
            case 83: goto L96;
            case 84: goto L94;
            case 85: goto L92;
            case 86: goto L90;
            case 87: goto L88;
            case 88: goto L88;
            case 89: goto L88;
            case 90: goto L88;
            case 91: goto L88;
            case 92: goto L88;
            case 93: goto L88;
            case 94: goto L88;
            case 95: goto L88;
            case 96: goto L86;
            case 97: goto L84;
            case 98: goto L82;
            case 99: goto L80;
            case 100: goto L86;
            case 101: goto L84;
            case 102: goto L82;
            case 103: goto L80;
            case 104: goto L86;
            case 105: goto L84;
            case 106: goto L82;
            case 107: goto L80;
            case 108: goto L86;
            case 109: goto L84;
            case 110: goto L82;
            case 111: goto L80;
            case 112: goto L86;
            case 113: goto L84;
            case 114: goto L82;
            case 115: goto L80;
            case 116: goto L86;
            case 117: goto L84;
            case 118: goto L82;
            case 119: goto L80;
            case 120: goto L86;
            case 121: goto L84;
            case 122: goto L86;
            case 123: goto L84;
            case 124: goto L86;
            case 125: goto L84;
            case 126: goto L86;
            case 127: goto L84;
            case 128: goto L86;
            case 129: goto L84;
            case 130: goto L86;
            case 131: goto L84;
            case 132: goto L78;
            case 133: goto L76;
            case 134: goto L74;
            case 135: goto L72;
            case 136: goto L70;
            case 137: goto L74;
            case 138: goto L72;
            case 139: goto L70;
            case 140: goto L76;
            case 141: goto L72;
            case 142: goto L70;
            case 143: goto L76;
            case 144: goto L74;
            case 145: goto L70;
            case 146: goto L70;
            case 147: goto L70;
            case 148: goto L70;
            case 149: goto L70;
            case 150: goto L70;
            case 151: goto L70;
            case 152: goto L70;
            case 153: goto L68;
            case 154: goto L68;
            case 155: goto L68;
            case 156: goto L68;
            case 157: goto L68;
            case 158: goto L68;
            case 159: goto L68;
            case 160: goto L68;
            case 161: goto L68;
            case 162: goto L68;
            case 163: goto L68;
            case 164: goto L68;
            case 165: goto L68;
            case 166: goto L68;
            case 167: goto L68;
            case 168: goto L68;
            case 169: goto L66;
            case 170: goto L63;
            case 171: goto L61;
            case 172: goto L58;
            case 173: goto L56;
            case 174: goto L54;
            case 175: goto L52;
            case 176: goto L50;
            case 177: goto L48;
            case 178: goto L41;
            case 179: goto L41;
            case 180: goto L41;
            case 181: goto L41;
            case 182: goto L41;
            case 183: goto L41;
            case 184: goto L41;
            case 185: goto L39;
            case 186: goto L37;
            case 187: goto L41;
            case 188: goto L36;
            case 189: goto L41;
            case 190: goto L70;
            case 191: goto L48;
            case 192: goto L41;
            case 193: goto L41;
            case 194: goto L48;
            case 195: goto L48;
            case 196: goto L34;
            case 197: goto L21;
            case 198: goto L68;
            case 199: goto L68;
            case 200: goto L15;
            case 201: goto L15;
            default: goto L9;
        };
    L9:
        r2.visitInvalid(r3, r13, 1);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L15:
        int r12 = this.bytes.getInt(r13 + 1) + r13;     // Catch: RuntimeException -> L11 SimException -> L13
        if (r3 != 200) goto L18;
        int r143 = 167;
    L19:
        r2.visitBranch(r143, r13, 5, r12);     // Catch: RuntimeException -> L11 SimException -> L13
        return 5;
    L18:
        r143 = 168;
        goto L19
    L21:
        int r144 = this.bytes.getUnsignedShort(r13 + 1);     // Catch: RuntimeException -> L29 SimException -> L31
        r2.visitConstant(r3, r13, 4, this.pool.get(r144), this.bytes.getUnsignedByte(r13 + 3));     // Catch: RuntimeException -> L25 SimException -> L27
        return 4;
    L37:
        r2.visitConstant(r3, r13, 5, (CstInvokeDynamic) this.pool.get(this.bytes.getUnsignedShort(r13 + 1)), 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 5;
    L39:
        r2.visitConstant(r3, r13, 5, this.pool.get(this.bytes.getUnsignedShort(r13 + 1)), this.bytes.getUnsignedByte(r13 + 3) | (this.bytes.getUnsignedByte(r13 + 4) << 8));     // Catch: RuntimeException -> L25 SimException -> L27
        return 5;
    L41:
        r4 = r13;
        r2.visitConstant(r3, r4, 3, this.pool.get(this.bytes.getUnsignedShort(r4 + 1)), 0);     // Catch: RuntimeException -> L44 SimException -> L46
        return 3;
    L46:
        e = e;
    L28:
        SimException r122 = e;
    L212:
        r122.addContext("...at bytecode offset " + Hex.u4(r4));
        throw r122;
    L44:
        e = e;
    L26:
        RuntimeException r123 = e;
    L210:
        SimException r132 = new SimException(r123);
        r132.addContext("...at bytecode offset " + Hex.u4(r4));
        throw r132;
    L48:
        r2.visitNoArgs(r3, r13, 1, Type.VOID);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L50:
        r2.visitNoArgs(172, r13, 1, Type.OBJECT);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L52:
        r2.visitNoArgs(172, r13, 1, Type.DOUBLE);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L54:
        r2.visitNoArgs(172, r13, 1, Type.FLOAT);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L56:
        r2.visitNoArgs(172, r13, 1, Type.LONG);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L58:
        r2.visitNoArgs(172, r13, 1, Type.INT);     // Catch: RuntimeException -> L11 SimException -> L13
        return 1;
    L68:
        r2.visitBranch(r3, r13, 3, r13 + this.bytes.getShort(r13 + 1));     // Catch: RuntimeException -> L25 SimException -> L27
        return 3;
    L70:
        r2.visitNoArgs(r3, r13, 1, Type.INT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L72:
        r2.visitNoArgs(r3, r13, 1, Type.DOUBLE);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L74:
        r2.visitNoArgs(r3, r13, 1, Type.FLOAT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L76:
        r2.visitNoArgs(r3, r13, 1, Type.LONG);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L78:
        r2.visitLocal(r3, r13, 3, this.bytes.getUnsignedByte(r13 + 1), Type.INT, this.bytes.getByte(r13 + 2));     // Catch: RuntimeException -> L25 SimException -> L27
        return 3;
    L80:
        r2.visitNoArgs(r3 - 3, r13, 1, Type.DOUBLE);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L82:
        r2.visitNoArgs(r3 - 2, r13, 1, Type.FLOAT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L84:
        r2.visitNoArgs(r3 - 1, r13, 1, Type.LONG);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L86:
        r2.visitNoArgs(r3, r13, 1, Type.INT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L88:
        r2.visitNoArgs(r3, r13, 1, Type.VOID);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L90:
        r2.visitNoArgs(79, r13, 1, Type.SHORT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L92:
        r2.visitNoArgs(79, r13, 1, Type.CHAR);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L94:
        r2.visitNoArgs(79, r13, 1, Type.BYTE);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L96:
        r2.visitNoArgs(79, r13, 1, Type.OBJECT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L98:
        r2.visitNoArgs(79, r13, 1, Type.DOUBLE);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L100:
        r2.visitNoArgs(79, r13, 1, Type.FLOAT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L102:
        r2.visitNoArgs(79, r13, 1, Type.LONG);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L104:
        r2.visitNoArgs(79, r13, 1, Type.INT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L106:
        r2.visitLocal(54, r13, 1, r3 - 75, Type.OBJECT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L108:
        r2.visitLocal(54, r13, 1, r3 - 71, Type.DOUBLE, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L110:
        r2.visitLocal(54, r13, 1, r3 - 67, Type.FLOAT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L112:
        r2.visitLocal(54, r13, 1, r3 - 63, Type.LONG, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L114:
        r2.visitLocal(54, r13, 1, r3 - 59, Type.INT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L116:
        r2.visitLocal(54, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.OBJECT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L118:
        r2.visitLocal(54, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.DOUBLE, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L120:
        r2.visitLocal(54, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.FLOAT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L122:
        r2.visitLocal(54, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.LONG, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L124:
        r2.visitLocal(54, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.INT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L126:
        r2.visitNoArgs(46, r13, 1, Type.SHORT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L128:
        r2.visitNoArgs(46, r13, 1, Type.CHAR);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L130:
        r2.visitNoArgs(46, r13, 1, Type.BYTE);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L132:
        r2.visitNoArgs(46, r13, 1, Type.OBJECT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L134:
        r2.visitNoArgs(46, r13, 1, Type.DOUBLE);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L136:
        r2.visitNoArgs(46, r13, 1, Type.FLOAT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L138:
        r2.visitNoArgs(46, r13, 1, Type.LONG);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L140:
        r2.visitNoArgs(46, r13, 1, Type.INT);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L142:
        r2.visitLocal(21, r13, 1, r3 - 42, Type.OBJECT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L144:
        r2.visitLocal(21, r13, 1, r3 - 38, Type.DOUBLE, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L146:
        r2.visitLocal(21, r13, 1, r3 - 34, Type.FLOAT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L148:
        r2.visitLocal(21, r13, 1, r3 - 30, Type.LONG, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L150:
        r2.visitLocal(21, r13, 1, r3 - 26, Type.INT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L152:
        r2.visitLocal(21, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.OBJECT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L154:
        r2.visitLocal(21, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.DOUBLE, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L156:
        r2.visitLocal(21, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.FLOAT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L158:
        r2.visitLocal(21, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.LONG, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L160:
        r2.visitLocal(21, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.INT, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L162:
        r2.visitConstant(20, r13, 3, this.pool.get(this.bytes.getUnsignedShort(r13 + 1)), 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 3;
    L164:
        Constant r6 = this.pool.get(this.bytes.getUnsignedShort(r13 + 1));     // Catch: RuntimeException -> L25 SimException -> L27
        if ((r6 instanceof CstInteger) == false) goto L167;
        r142 = ((CstInteger) r6).getValue();     // Catch: RuntimeException -> L25 SimException -> L27
    L167:
        r2.visitConstant(18, r13, 3, r6, r142);     // Catch: RuntimeException -> L25 SimException -> L27
        return 3;
    L169:
        Constant r62 = this.pool.get(this.bytes.getUnsignedByte(r13 + 1));     // Catch: RuntimeException -> L25 SimException -> L27
        if ((r62 instanceof CstInteger) == false) goto L172;
        r142 = ((CstInteger) r62).getValue();     // Catch: RuntimeException -> L25 SimException -> L27
    L172:
        r2.visitConstant(18, r13, 2, r62, r142);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L174:
        int r7 = this.bytes.getShort(r13 + 1);     // Catch: RuntimeException -> L25 SimException -> L27
        r2.visitConstant(18, r13, 3, CstInteger.make(r7), r7);     // Catch: RuntimeException -> L25 SimException -> L27
        return 3;
    L176:
        int r72 = this.bytes.getByte(r13 + 1);     // Catch: RuntimeException -> L25 SimException -> L27
        r2.visitConstant(18, r13, 2, CstInteger.make(r72), r72);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L178:
        r2.visitConstant(18, r13, 1, CstDouble.VALUE_1, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L180:
        r2.visitConstant(18, r13, 1, CstDouble.VALUE_0, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L182:
        r2.visitConstant(18, r13, 1, CstFloat.VALUE_2, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L184:
        r2.visitConstant(18, r13, 1, CstFloat.VALUE_1, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L186:
        r2.visitConstant(18, r13, 1, CstFloat.VALUE_0, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L188:
        r2.visitConstant(18, r13, 1, CstLong.VALUE_1, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L190:
        r2.visitConstant(18, r13, 1, CstLong.VALUE_0, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L192:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_5, 5);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L194:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_4, 4);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L196:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_3, 3);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L198:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_2, 2);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L200:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_1, 1);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L202:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_0, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L204:
        r2.visitConstant(18, r13, 1, CstInteger.VALUE_M1, -1);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L206:
        r2.visitConstant(18, r13, 1, CstKnownNull.THE_ONE, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L208:
        r2.visitNoArgs(r3, r13, 1, Type.VOID);     // Catch: RuntimeException -> L25 SimException -> L27
        return 1;
    L34:
        return parseWide(r13, r2);
    L36:
        return parseNewarray(r13, r2);
    L61:
        return parseLookupswitch(r13, r2);
    L63:
        return parseTableswitch(r13, r2);
    L66:
        r2.visitLocal(r3, r13, 2, this.bytes.getUnsignedByte(r13 + 1), Type.RETURN_ADDRESS, 0);     // Catch: RuntimeException -> L25 SimException -> L27
        return 2;
    L13:
        e = move-exception;
        r122 = e;
        r4 = r13;
    L11:
        e = move-exception;
        r123 = e;
        r4 = r13;
    L27:
        e = e;
    L25:
        e = e;
    L31:
        e = e;
        r4 = r13;
    L29:
        e = e;
        r4 = r13;
        goto L26
    }

    public void processWorkSet(int[] r2, Visitor r3) {
        if (r3 == null) goto L7;
    L3:
        int r0 = Bits.findFirst(r2, 0);
        if (r0 < 0) goto L5;
        Bits.clear(r2, r0);
        parseInstruction(r0, r3);
        r3.setPreviousOffset(r0);
        goto L3
    L5:
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("visitor == null");
    }

    public int size() {
        return this.bytes.size();
    }
}
