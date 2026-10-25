package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstKnownNull;
import com.android.dx.rop.cst.CstLiteral64;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.BitSet;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class InsnFormat {
    public static final boolean ALLOW_EXTENDED_OPCODES = true;

    public InsnFormat() {
    }

    public static int argIndex(DalvInsn r0) {
        int r02 = ((CstInteger) ((CstInsn) r0).getConstant()).getValue();
        if (r02 < 0) goto L5;
        return r02;
    L5:
        f$$ExternalSyntheticBUOutline1.m("bogus insn");
        return 0;
    }

    public static String branchComment(DalvInsn r1) {
        int r12 = ((TargetInsn) r1).getTargetOffset();
        if (r12 != ((short) r12)) goto L7;
        return Hex.s2(r12);
    L7:
        return Hex.s4(r12);
    }

    public static String branchString(DalvInsn r1) {
        int r12 = ((TargetInsn) r1).getTargetAddress();
        if (r12 != ((char) r12)) goto L7;
        return Hex.u2(r12);
    L7:
        return Hex.u4(r12);
    }

    public static short codeUnit(int r1, int r2) {
        if ((r1 & 255) == r1) goto L5;
        f$$ExternalSyntheticBUOutline1.m("low out of range 0..255");
        return 0;
    L5:
        if ((r2 & 255) == r2) goto L7;
        f$$ExternalSyntheticBUOutline1.m("high out of range 0..255");
        return 0;
    L7:
        return (short) (r1 | (r2 << 8));
    }

    public static short codeUnit(int r1, int r2, int r3, int r4) {
        if ((r1 & 15) == r1) goto L5;
        f$$ExternalSyntheticBUOutline1.m("n0 out of range 0..15");
        return 0;
    L5:
        if ((r2 & 15) == r2) goto L7;
        f$$ExternalSyntheticBUOutline1.m("n1 out of range 0..15");
        return 0;
    L7:
        if ((r3 & 15) == r3) goto L9;
        f$$ExternalSyntheticBUOutline1.m("n2 out of range 0..15");
        return 0;
    L9:
        if ((r4 & 15) == r4) goto L11;
        f$$ExternalSyntheticBUOutline1.m("n3 out of range 0..15");
        return 0;
    L11:
        return (short) (((r1 | (r2 << 4)) | (r3 << 8)) | (r4 << 12));
    }

    public static boolean isRegListSequential(RegisterSpecList r7) {
        int r0 = r7.size();
        if (r0 >= 2) goto L5;
        return true;
    L5:
        int r3 = r7.get(0).getReg();
        int r4 = 0;
    L6:
        if (r4 >= r0) goto L11;
        RegisterSpec r5 = r7.get(r4);
        if (r5.getReg() != r3) goto L9;
        r3 = r3 + r5.getCategory();
        r4 = r4 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    public static String literalBitsComment(CstLiteralBits r3, int r4) {
        StringBuilder r0 = new StringBuilder(20);
        r0.append("#");
        if ((r3 instanceof CstLiteral64) == false) goto L5;
        long r1 = ((CstLiteral64) r3).getLongBits();
    L7:
        if (r4 != 4) goto L9;
        r0.append(Hex.uNibble((int) r1));
    L24:
        return r0.toString();
    L9:
        if (r4 != 8) goto L11;
        r0.append(Hex.u1((int) r1));
        goto L24
    L11:
        if (r4 != 16) goto L13;
        r0.append(Hex.u2((int) r1));
        goto L24
    L13:
        if (r4 != 32) goto L15;
        r0.append(Hex.u4((int) r1));
        goto L24
    L15:
        if (r4 != 64) goto L17;
        r0.append(Hex.u8(r1));
        goto L24
    L17:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        return null;
    L5:
        r1 = r3.getIntBits();
        goto L7
    }

    public static String literalBitsString(CstLiteralBits r2) {
        StringBuilder r0 = new StringBuilder(100);
        r0.append('#');
        if ((r2 instanceof CstKnownNull) == false) goto L5;
        r0.append("null");
    L7:
        return r0.toString();
    L5:
        r0.append(r2.typeName());
        r0.append(' ');
        r0.append(r2.toHuman());
        goto L7
    }

    public static int makeByte(int r1, int r2) {
        if ((r1 & 15) == r1) goto L5;
        f$$ExternalSyntheticBUOutline1.m("low out of range 0..15");
        return 0;
    L5:
        if ((r2 & 15) == r2) goto L7;
        f$$ExternalSyntheticBUOutline1.m("high out of range 0..15");
        return 0;
    L7:
        return r1 | (r2 << 4);
    }

    public static short opcodeUnit(DalvInsn r1) {
        int r12 = r1.getOpcode().getOpcode();
        if (r12 >= 256) goto L5;
    L8:
        f$$ExternalSyntheticBUOutline1.m("opcode out of range 0..65535");
        return 0;
    L5:
        if (r12 > 65535) goto L8;
        return (short) r12;
    }

    public static short opcodeUnit(DalvInsn r1, int r2) {
        if ((r2 & 255) != r2) goto L10;
        int r12 = r1.getOpcode().getOpcode();
        if ((r12 & 255) == r12) goto L7;
        f$$ExternalSyntheticBUOutline1.m("opcode out of range 0..255");
        return 0;
    L7:
        return (short) (r12 | (r2 << 8));
    L10:
        f$$ExternalSyntheticBUOutline1.m("arg out of range 0..255");
        return 0;
    }

    public static String regListString(RegisterSpecList r4) {
        int r0 = r4.size();
        StringBuilder r1 = new StringBuilder((r0 * 5) + 2);
        r1.append('{');
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        if (r2 == 0) goto L6;
        r1.append(", ");
    L6:
        r1.append(r4.get(r2).regString());
        r2 = r2 + 1;
        goto L3
    L7:
        r1.append('}');
        return r1.toString();
    }

    public static String regRangeString(RegisterSpecList r6) {
        int r0 = r6.size();
        StringBuilder r1 = new StringBuilder(30);
        r1.append("{");
        if (r0 != 0) goto L5;
    L11:
        r1.append("}");
        return r1.toString();
    L5:
        if (r0 == 1) goto L10;
        RegisterSpec r02 = r6.get(r0 - 1);
        if (r02.getCategory() != 2) goto L9;
        r02 = r02.withOffset(1);
    L9:
        r1.append(r6.get(0).regString());
        r1.append("..");
        r1.append(r02.regString());
        goto L11
    L10:
        r1.append(r6.get(0).regString());
        goto L11
    }

    public static boolean signedFitsInByte(int r1) {
        if (((byte) r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean signedFitsInNibble(int r1) {
        if (r1 >= (-8)) goto L5;
        return false;
    L5:
        if (r1 > 7) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean signedFitsInShort(int r1) {
        if (((short) r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean unsignedFitsInByte(int r1) {
        if (r1 != (r1 & 255)) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean unsignedFitsInNibble(int r1) {
        if (r1 != (r1 & 15)) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean unsignedFitsInShort(int r1) {
        if (r1 != (65535 & r1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static void write(AnnotatedOutput r0, short r1) {
        r0.writeShort(r1);
    }

    public static void write(AnnotatedOutput r1, short r2, int r3) {
        write(r1, r2, (short) r3, (short) (r3 >> 16));
    }

    public static void write(AnnotatedOutput r1, short r2, int r3, short r4) {
        write(r1, r2, (short) r3, (short) (r3 >> 16), r4);
    }

    public static void write(AnnotatedOutput r6, short r7, int r8, short r9, short r10) {
        write(r6, r7, (short) r8, (short) (r8 >> 16), r9, r10);
    }

    public static void write(AnnotatedOutput r7, short r8, long r9) {
        write(r7, r8, (short) r9, (short) (r9 >> 16), (short) (r9 >> 32), (short) (r9 >> 48));
    }

    public static void write(AnnotatedOutput r0, short r1, short r2) {
        r0.writeShort(r1);
        r0.writeShort(r2);
    }

    public static void write(AnnotatedOutput r0, short r1, short r2, short r3) {
        r0.writeShort(r1);
        r0.writeShort(r2);
        r0.writeShort(r3);
    }

    public static void write(AnnotatedOutput r0, short r1, short r2, short r3, short r4) {
        r0.writeShort(r1);
        r0.writeShort(r2);
        r0.writeShort(r3);
        r0.writeShort(r4);
    }

    public static void write(AnnotatedOutput r0, short r1, short r2, short r3, short r4, short r5) {
        r0.writeShort(r1);
        r0.writeShort(r2);
        r0.writeShort(r3);
        r0.writeShort(r4);
        r0.writeShort(r5);
    }

    public boolean branchFits(TargetInsn r1) {
        return false;
    }

    public abstract int codeSize();

    public BitSet compatibleRegs(DalvInsn r1) {
        return new BitSet();
    }

    public abstract String insnArgString(DalvInsn r1);

    public abstract String insnCommentString(DalvInsn r1, boolean r2);

    public abstract boolean isCompatible(DalvInsn r1);

    public final String listingString(DalvInsn r3, boolean r4) {
        String r0 = r3.getOpcode().getName();
        String r1 = insnArgString(r3);
        String r2 = insnCommentString(r3, r4);
        StringBuilder r32 = new StringBuilder(100);
        r32.append(r0);
        if (r1.length() == 0) goto L6;
        r32.append(' ');
        r32.append(r1);
    L6:
        if (r2.length() == 0) goto L9;
        r32.append(" // ");
        r32.append(r2);
    L9:
        return r32.toString();
    }

    public abstract void writeTo(AnnotatedOutput r1, DalvInsn r2);
}
