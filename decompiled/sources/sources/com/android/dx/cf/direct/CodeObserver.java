package com.android.dx.cf.direct;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.ByteOps;
import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.cf.code.SwitchList;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstKnownNull;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class CodeObserver implements BytecodeArray.Visitor {
    private final ByteArray bytes;
    private final ParseObserver observer;

    public CodeObserver(ByteArray r2, ParseObserver r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.bytes = r2;
        this.observer = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("observer == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("bytes == null");
        throw null;
    }

    private String header(int r4) {
        int r0 = this.bytes.getUnsignedByte(r4);
        String r1 = ByteOps.opName(r0);
        if (r0 != 196) goto L6;
        r1 = r1 + " " + ByteOps.opName(this.bytes.getUnsignedByte(r4 + 1));
    L6:
        return Hex.u2(r4) + ": " + r1;
    }

    private void visitLiteralDouble(int r4, int r5, int r6, long r7) {
        if (r6 == 1) goto L5;
        String r42 = " #" + Hex.u8(r7);
    L6:
        this.observer.parsed(this.bytes, r5, r6, header(r5) + r42 + " // " + Double.longBitsToDouble(r7));
        return;
    L5:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void visitLiteralFloat(int r4, int r5, int r6, int r7) {
        if (r6 == 1) goto L5;
        String r42 = " #" + Hex.u4(r7);
    L6:
        this.observer.parsed(this.bytes, r5, r6, header(r5) + r42 + " // " + Float.intBitsToFloat(r7));
        return;
    L5:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void visitLiteralInt(int r4, int r5, int r6, int r7) {
        if (r6 != 1) goto L5;
        String r0 = " // ";
    L6:
        int r1 = this.bytes.getUnsignedByte(r5);
        if (r6 != 1) goto L9;
    L15:
        String r42 = "#" + Hex.s1(r7);
    L16:
        this.observer.parsed(this.bytes, r5, r6, header(r5) + r0 + r42);
        return;
    L9:
        if (r1 == 16) goto L15;
        if (r1 != 17) goto L14;
        r42 = "#" + Hex.s2(r7);
        goto L16
    L14:
        r42 = "#" + Hex.s4(r7);
        goto L16
    L5:
        r0 = " ";
        goto L6
    }

    private void visitLiteralLong(int r3, int r4, int r5, long r6) {
        if (r5 != 1) goto L5;
        String r0 = " // ";
    L6:
        if (r5 != 1) goto L8;
        String r32 = Hex.s1((int) r6);
    L9:
        this.observer.parsed(this.bytes, r4, r5, header(r4) + r0 + r32);
        return;
    L8:
        r32 = Hex.s8(r6);
        goto L9
    L5:
        r0 = " #";
        goto L6
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public int getPreviousOffset() {
        return -1;
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void setPreviousOffset(int r1) {
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitBranch(int r3, int r4, int r5, int r6) {
        if (r5 > 3) goto L5;
        String r32 = Hex.u2(r6);
    L6:
        this.observer.parsed(this.bytes, r4, r5, header(r4) + " " + r32);
        return;
    L5:
        r32 = Hex.u4(r6);
        goto L6
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitConstant(int r7, int r8, int r9, Constant r10, int r11) {
        if ((r10 instanceof CstKnownNull) == false) goto L7;
        visitNoArgs(r7, r8, r9, null);
        return;
    L7:
        if ((r10 instanceof CstInteger) == false) goto L11;
        visitLiteralInt(r7, r8, r9, r11);
        return;
    L11:
        if ((r10 instanceof CstLong) == false) goto L15;
        visitLiteralLong(r7, r8, r9, ((CstLong) r10).getValue());
        return;
    L15:
        if ((r10 instanceof CstFloat) == false) goto L19;
        visitLiteralFloat(r7, r8, r9, ((CstFloat) r10).getIntBits());
        return;
    L19:
        if ((r10 instanceof CstDouble) == false) goto L22;
        visitLiteralDouble(r7, r8, r9, ((CstDouble) r10).getLongBits());
        return;
    L22:
        if (r11 != 0) goto L24;
        String r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L28:
        this.observer.parsed(this.bytes, r8, r9, header(r8) + " " + r10 + r72);
        return;
    L24:
        if (r7 != 197) goto L26;
        r72 = ", " + Hex.u1(r11);
        goto L28
    L26:
        r72 = ", " + Hex.u2(r11);
        goto L28
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitInvalid(int r2, int r3, int r4) {
        this.observer.parsed(this.bytes, r3, r4, header(r3));
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitLocal(int r5, int r6, int r7, int r8, Type r9, int r10) {
        if (r7 > 3) goto L5;
        String r82 = Hex.u1(r8);
    L6:
        boolean r1 = true;
        if (r7 == 1) goto L10;
        r1 = false;
    L10:
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r5 != 132) goto L17;
        StringBuilder r52 = new StringBuilder(", #");
        if (r7 > 3) goto L15;
        String r102 = Hex.s1(r10);
    L16:
        r52.append(r102);
        String r53 = r52.toString();
    L19:
        if (r9.isCategory2() == false) goto L24;
        if (r1 == false) goto L22;
        String r92 = ",";
    L23:
        r3 = r92.concat(" category-2");
        goto L24
    L22:
        r92 = " //";
    L24:
        ParseObserver r93 = this.observer;
        ByteArray r103 = this.bytes;
        StringBuilder r0 = new StringBuilder();
        r0.append(header(r6));
        if (r1 == false) goto L27;
        String r4 = " // ";
    L28:
        r0.append(r4);
        r0.append(r82);
        r0.append(r53);
        r0.append(r3);
        r93.parsed(r103, r6, r7, r0.toString());
        return;
    L27:
        r4 = " ";
        goto L28
    L15:
        r102 = Hex.s2(r10);
        goto L16
    L17:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L5:
        r82 = Hex.u2(r8);
        goto L6
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitNewarray(int r4, int r5, CstType r6, ArrayList<Constant> r7) {
        if (r5 != 1) goto L5;
        String r72 = " // ";
    L6:
        String r62 = r6.getClassType().getComponentType().toHuman();
        this.observer.parsed(this.bytes, r4, r5, header(r4) + r72 + r62);
        return;
    L5:
        r72 = " ";
        goto L6
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitNoArgs(int r1, int r2, int r3, Type r4) {
        this.observer.parsed(this.bytes, r2, r3, header(r2));
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitSwitch(int r4, int r5, int r6, SwitchList r7, int r8) {
        int r42 = r7.size();
        StringBuilder r0 = new StringBuilder((r42 * 20) + 100);
        r0.append(header(r5));
        if (r8 == 0) goto L5;
        r0.append(" // padding: " + Hex.u4(r8));
    L5:
        r0.append('\n');
        int r1 = 0;
    L6:
        if (r1 >= r42) goto L8;
        r0.append("  ");
        r0.append(Hex.s4(r7.getValue(r1)));
        r0.append(": ");
        r0.append(Hex.u2(r7.getTarget(r1)));
        r0.append('\n');
        r1 = r1 + 1;
        goto L6
    L8:
        r0.append("  default: ");
        r0.append(Hex.u2(r7.getDefaultTarget()));
        this.observer.parsed(this.bytes, r5, r6, r0.toString());
    }
}
