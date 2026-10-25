package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes4.dex */
public class InvokePolymorphicInsn extends Insn {
    private static final CstString DEFAULT_DESCRIPTOR = null;
    private static final CstString VARHANDLE_COMPARE_AND_SET_DESCRIPTOR = null;
    private static final CstString VARHANDLE_SET_DESCRIPTOR = null;
    private final CstMethodRef callSiteMethod;
    private final CstProtoRef callSiteProto;
    private final TypeList catches;
    private final CstMethodRef polymorphicMethod;

    static {
        DEFAULT_DESCRIPTOR = new CstString("([Ljava/lang/Object;)Ljava/lang/Object;");
        VARHANDLE_SET_DESCRIPTOR = new CstString("([Ljava/lang/Object;)V");
        VARHANDLE_COMPARE_AND_SET_DESCRIPTOR = new CstString("([Ljava/lang/Object;)Z");
    }

    public InvokePolymorphicInsn(Rop r2, SourcePosition r3, RegisterSpecList r4, TypeList r5, CstMethodRef r6) {
        super(r2, r3, null, r4);
        if (r2.getBranchingness() != 6) goto L17;
        if (r5 == null) goto L15;
        this.catches = r5;
        if (r6 != null) goto L8;
        f$$ExternalSyntheticBUOutline2.m("callSiteMethod == null");
        throw null;
    L8:
        if (r6.isSignaturePolymorphic() == false) goto L11;
        this.callSiteMethod = r6;
        this.polymorphicMethod = makePolymorphicMethod(r6);
        this.callSiteProto = makeCallSiteProto(r6);
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("callSiteMethod is not signature polymorphic");
        throw null;
    L15:
        f$$ExternalSyntheticBUOutline2.m("catches == null");
        throw null;
    L17:
        IntegerType$$ExternalSyntheticBUOutline0.m("opcode with invalid branchingness: ", r2.getBranchingness());
        throw null;
    }

    private static CstProtoRef makeCallSiteProto(CstMethodRef r2) {
        return new CstProtoRef(r2.getPrototype(true));
    }

    private static CstMethodRef makePolymorphicMethod(CstMethodRef r5) {
        CstType r0 = r5.getDefiningClass();
        CstString r1 = r5.getNat().getName();
        String r2 = r5.getNat().getName().getString();
        if (r0.equals(CstType.METHOD_HANDLE) == false) goto L11;
        if (r2.equals("invoke") == true) goto L9;
        if (r2.equals("invokeExact") == false) goto L11;
    L9:
        return new CstMethodRef(r0, new CstNat(r1, DEFAULT_DESCRIPTOR));
    L11:
        if (r0.equals(CstType.VAR_HANDLE) == false) goto L147;
        r2.getClass();
        char r4 = 65535;
        switch(r2.hashCode()) {
            case -1946504908: goto L136;
            case -1686727776: goto L132;
            case -1671098288: goto L128;
            case -1292078254: goto L124;
            case -1117944904: goto L120;
            case -1103072857: goto L116;
            case -1032914329: goto L112;
            case -1032892181: goto L108;
            case -794517348: goto L104;
            case -567150350: goto L100;
            case -240822786: goto L96;
            case -230706875: goto L92;
            case -127361888: goto L88;
            case -37641530: goto L84;
            case 102230: goto L80;
            case 113762: goto L76;
            case 93645315: goto L72;
            case 101293086: goto L68;
            case 189872914: goto L64;
            case 282707520: goto L60;
            case 282724865: goto L56;
            case 353422447: goto L52;
            case 470702883: goto L48;
            case 685319959: goto L44;
            case 748071969: goto L40;
            case 937077366: goto L36;
            case 1245632875: goto L32;
            case 1352153939: goto L28;
            case 1483964149: goto L24;
            case 2002508693: goto L20;
            case 2013994287: goto L16;
            default: goto L139;
        };
    L139:
        switch(r4) {
            case 0: goto L146;
            case 1: goto L146;
            case 2: goto L144;
            case 3: goto L146;
            case 4: goto L144;
            case 5: goto L146;
            case 6: goto L146;
            case 7: goto L146;
            case 8: goto L146;
            case 9: goto L144;
            case 10: goto L144;
            case 11: goto L142;
            case 12: goto L146;
            case 13: goto L146;
            case 14: goto L146;
            case 15: goto L142;
            case 16: goto L146;
            case 17: goto L142;
            case 18: goto L146;
            case 19: goto L146;
            case 20: goto L146;
            case 21: goto L146;
            case 22: goto L142;
            case 23: goto L146;
            case 24: goto L146;
            case 25: goto L146;
            case 26: goto L146;
            case 27: goto L146;
            case 28: goto L146;
            case 29: goto L146;
            case 30: goto L144;
            default: goto L147;
        };
    L142:
        return new CstMethodRef(r0, new CstNat(r1, VARHANDLE_SET_DESCRIPTOR));
    L144:
        return new CstMethodRef(r0, new CstNat(r1, VARHANDLE_COMPARE_AND_SET_DESCRIPTOR));
    L146:
        return new CstMethodRef(r0, new CstNat(r1, DEFAULT_DESCRIPTOR));
    L16:
        if (r2.equals("weakCompareAndSetRelease") == false) goto L139;
        r4 = 30;
        goto L139
    L20:
        if (r2.equals("getAndSetAcquire") == false) goto L139;
        r4 = 29;
        goto L139
    L24:
        if (r2.equals("compareAndExchange") == false) goto L139;
        r4 = 28;
        goto L139
    L28:
        if (r2.equals("getAndBitwiseOr") == false) goto L139;
        r4 = 27;
        goto L139
    L32:
        if (r2.equals("getAndBitwiseXorAcquire") == false) goto L139;
        r4 = 26;
        goto L139
    L36:
        if (r2.equals("getAndAddAcquire") == false) goto L139;
        r4 = 25;
        goto L139
    L40:
        if (r2.equals("compareAndExchangeAcquire") == false) goto L139;
        r4 = 24;
        goto L139
    L44:
        if (r2.equals("getOpaque") == false) goto L139;
        r4 = 23;
        goto L139
    L48:
        if (r2.equals("setOpaque") == false) goto L139;
        r4 = 22;
        goto L139
    L52:
        if (r2.equals("getAndBitwiseAndAcquire") == false) goto L139;
        r4 = 21;
        goto L139
    L56:
        if (r2.equals("getAndSet") == false) goto L139;
        r4 = 20;
        goto L139
    L60:
        if (r2.equals("getAndAdd") == false) goto L139;
        r4 = 19;
        goto L139
    L64:
        if (r2.equals("getVolatile") == false) goto L139;
        r4 = 18;
        goto L139
    L68:
        if (r2.equals("setVolatile") == false) goto L139;
        r4 = 17;
        goto L139
    L72:
        if (r2.equals("getAndBitwiseOrAcquire") == false) goto L139;
        r4 = 16;
        goto L139
    L76:
        if (r2.equals("set") == false) goto L139;
        r4 = 15;
        goto L139
    L80:
        if (r2.equals("get") == false) goto L139;
        r4 = 14;
        goto L139
    L84:
        if (r2.equals("getAndSetRelease") == false) goto L139;
        r4 = '\r';
        goto L139
    L88:
        if (r2.equals("getAcquire") == false) goto L139;
        r4 = '\f';
        goto L139
    L92:
        if (r2.equals("setRelease") == false) goto L139;
        r4 = 11;
        goto L139
    L96:
        if (r2.equals("weakCompareAndSetAcquire") == false) goto L139;
        r4 = '\n';
        goto L139
    L100:
        if (r2.equals("weakCompareAndSetPlain") == false) goto L139;
        r4 = '\t';
        goto L139
    L104:
        if (r2.equals("getAndBitwiseXorRelease") == false) goto L139;
        r4 = '\b';
        goto L139
    L108:
        if (r2.equals("getAndBitwiseXor") == false) goto L139;
        r4 = 7;
        goto L139
    L112:
        if (r2.equals("getAndBitwiseAnd") == false) goto L139;
        r4 = 6;
        goto L139
    L116:
        if (r2.equals("getAndAddRelease") == false) goto L139;
        r4 = 5;
        goto L139
    L120:
        if (r2.equals("weakCompareAndSet") == false) goto L139;
        r4 = 4;
        goto L139
    L124:
        if (r2.equals("compareAndExchangeRelease") == false) goto L139;
        r4 = 3;
        goto L139
    L128:
        if (r2.equals("compareAndSet") == false) goto L139;
        r4 = 2;
        goto L139
    L132:
        if (r2.equals("getAndBitwiseAndRelease") == false) goto L139;
        r4 = 1;
        goto L139
    L136:
        if (r2.equals("getAndBitwiseOrRelease") == false) goto L139;
        r4 = 0;
    L147:
        Buffer$$ExternalSyntheticBUOutline4.m("Unknown signature polymorphic method: ", r5.toHuman());
        return null;
    }

    @Override // com.android.dx.rop.code.Insn
    public void accept(Insn.Visitor r1) {
        r1.visitInvokePolymorphicInsn(this);
    }

    public CstMethodRef getCallSiteMethod() {
        return this.callSiteMethod;
    }

    public CstProtoRef getCallSiteProto() {
        return this.callSiteProto;
    }

    @Override // com.android.dx.rop.code.Insn
    public TypeList getCatches() {
        return this.catches;
    }

    @Override // com.android.dx.rop.code.Insn
    public String getInlineString() {
        return getPolymorphicMethod().toString() + " " + getCallSiteProto().toString() + " " + ThrowingInsn.toCatchString(this.catches);
    }

    public CstMethodRef getPolymorphicMethod() {
        return this.polymorphicMethod;
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withAddedCatch(Type r7) {
        return new InvokePolymorphicInsn(getOpcode(), getPosition(), getSources(), this.catches.withAddedType(r7), getCallSiteMethod());
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withNewRegisters(RegisterSpec r7, RegisterSpecList r8) {
        return new InvokePolymorphicInsn(getOpcode(), getPosition(), r8, this.catches, getCallSiteMethod());
    }

    @Override // com.android.dx.rop.code.Insn
    public Insn withRegisterOffset(int r7) {
        return new InvokePolymorphicInsn(getOpcode(), getPosition(), getSources().withOffset(r7), this.catches, getCallSiteMethod());
    }
}
