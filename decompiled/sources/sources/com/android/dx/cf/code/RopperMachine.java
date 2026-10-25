package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.iface.Method;
import com.android.dx.cf.iface.MethodList;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.code.FillArrayDataInsn;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.InvokePolymorphicInsn;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.code.SwitchInsn;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.code.ThrowingInsn;
import com.android.dx.rop.code.TranslationAdvice;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.IntList;
import com.android.dx.util.ToHuman;
import java.util.ArrayList;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class RopperMachine extends ValueAwareMachine {
    private static final CstType ARRAY_REFLECT_TYPE = null;
    private static final CstMethodRef MULTIANEWARRAY_METHOD = null;
    private final TranslationAdvice advice;
    private boolean blockCanThrow;
    private TypeList catches;
    private boolean catchesUsed;
    private int extraBlockCount;
    private boolean hasJsr;
    private final ArrayList<Insn> insns;
    private final int maxLocals;
    private final ConcreteMethod method;
    private final MethodList methods;
    private int primarySuccessorIndex;
    private ReturnAddress returnAddress;
    private Rop returnOp;
    private SourcePosition returnPosition;
    private boolean returns;
    private final Ropper ropper;

    static {
        CstType r0 = new CstType(Type.internClassName("java/lang/reflect/Array"));
        ARRAY_REFLECT_TYPE = r0;
        MULTIANEWARRAY_METHOD = new CstMethodRef(r0, new CstNat(new CstString("newInstance"), new CstString("(Ljava/lang/Class;[I)Ljava/lang/Object;")));
    }

    public RopperMachine(Ropper r2, ConcreteMethod r3, TranslationAdvice r4, MethodList r5) {
        super(r3.getEffectiveDescriptor());
        if (r5 == null) goto L12;
        if (r2 == null) goto L10;
        if (r4 == null) goto L8;
        this.ropper = r2;
        this.method = r3;
        this.methods = r5;
        this.advice = r4;
        this.maxLocals = r3.getMaxLocals();
        this.insns = new ArrayList(25);
        this.catches = null;
        this.catchesUsed = false;
        this.returns = false;
        this.primarySuccessorIndex = -1;
        this.extraBlockCount = 0;
        this.blockCanThrow = false;
        this.returnOp = null;
        this.returnPosition = null;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("advice == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("ropper == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("methods == null");
        throw null;
    }

    private RegisterSpecList getSources(int r7, int r8) {
        int r0 = argCount();
        if (r0 == 0) goto L5;
        int r1 = getLocalIndex();
        if (r1 < 0) goto L9;
        RegisterSpecList r72 = new RegisterSpecList(1);
        r72.set(0, RegisterSpec.make(r1, arg(0)));
    L26:
        r72.setImmutable();
        return r72;
    L9:
        RegisterSpecList r12 = new RegisterSpecList(r0);
        int r4 = 0;
    L10:
        if (r4 >= r0) goto L13;
        RegisterSpec r5 = RegisterSpec.make(r8, arg(r4));
        r12.set(r4, r5);
        r8 = r8 + r5.getCategory();
        r4 = r4 + 1;
        goto L10
    L13:
        if (r7 == 79) goto L23;
        if (r7 != 181) goto L25;
        if (r0 != 2) goto L19;
        RegisterSpec r6 = r12.get(0);
        r12.set(0, r12.get(1));
        r12.set(1, r6);
        goto L25
    L19:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        return null;
    L25:
        r72 = r12;
        goto L26
    L23:
        if (r0 != 3) goto L28;
        RegisterSpec r62 = r12.get(0);
        RegisterSpec r73 = r12.get(1);
        r12.set(0, r12.get(2));
        r12.set(1, r62);
        r12.set(2, r73);
        goto L25
    L28:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        return null;
    L5:
        return RegisterSpecList.EMPTY;
    }

    private int jopToRopOpcode(int r4, Constant r5) {
        if (r4 != 0) goto L4;
        return 1;
    L4:
        if (r4 != 20) goto L6;
        return 5;
    L6:
        if (r4 != 21) goto L8;
        return 2;
    L8:
        if (r4 != 171) goto L10;
        return 13;
    L10:
        if (r4 != 172) goto L12;
        return 33;
    L12:
        if (r4 != 198) goto L14;
        return 7;
    L14:
        if (r4 == 199) goto L123;
        switch(r4) {
            case 0: goto L147;
            case 18: goto L146;
            case 46: goto L121;
            case 54: goto L145;
            case 79: goto L119;
            case 96: goto L117;
            case 100: goto L115;
            case 104: goto L113;
            case 108: goto L111;
            case 112: goto L109;
            case 116: goto L107;
            case 120: goto L105;
            case 122: goto L103;
            case 124: goto L101;
            case 126: goto L100;
            case 128: goto L99;
            case 130: goto L97;
            default: goto L16;
        };
    L16:
        switch(r4) {
            case 132: goto L141;
            case 133: goto L95;
            case 134: goto L95;
            case 135: goto L95;
            case 136: goto L95;
            case 137: goto L95;
            case 138: goto L95;
            case 139: goto L95;
            case 140: goto L95;
            case 141: goto L95;
            case 142: goto L95;
            case 143: goto L95;
            case 144: goto L95;
            case 145: goto L93;
            case 146: goto L91;
            case 147: goto L89;
            case 148: goto L87;
            case 149: goto L87;
            case 150: goto L85;
            case 151: goto L87;
            case 152: goto L85;
            case 153: goto L143;
            case 154: goto L142;
            case 155: goto L83;
            case 156: goto L81;
            case 157: goto L79;
            case 158: goto L77;
            case 159: goto L143;
            case 160: goto L142;
            case 161: goto L83;
            case 162: goto L81;
            case 163: goto L79;
            case 164: goto L77;
            case 165: goto L143;
            case 166: goto L142;
            case 167: goto L75;
            default: goto L17;
        };
    L17:
        int r0 = 0;
        switch(r4) {
            case 177: goto L144;
            case 178: goto L73;
            case 179: goto L71;
            case 180: goto L69;
            case 181: goto L67;
            case 182: goto L51;
            case 183: goto L43;
            case 184: goto L41;
            case 185: goto L39;
            case 186: goto L37;
            case 187: goto L35;
            case 188: goto L33;
            case 189: goto L33;
            case 190: goto L31;
            case 191: goto L29;
            case 192: goto L27;
            case 193: goto L25;
            case 194: goto L23;
            case 195: goto L21;
            default: goto L19;
        };
    L19:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        return 0;
    L21:
        return 37;
    L23:
        return 36;
    L25:
        return 44;
    L27:
        return 43;
    L29:
        return 35;
    L31:
        return 34;
    L33:
        return 41;
    L35:
        return 40;
    L37:
        return 59;
    L39:
        return 53;
    L41:
        return 49;
    L43:
        CstMethodRef r52 = (CstMethodRef) r5;
        if (r52.isInstanceInit() == false) goto L46;
    L50:
        return 52;
    L46:
        if (r52.getDefiningClass().equals(this.method.getDefiningClass()) == true) goto L50;
        return 51;
    L51:
        CstMethodRef r53 = (CstMethodRef) r5;
        if (r53.getDefiningClass().equals(this.method.getDefiningClass()) == false) goto L62;
    L54:
        if (r0 >= this.methods.size()) goto L62;
        Method r42 = this.methods.get(r0);
        if (AccessFlags.isPrivate(r42.getAccessFlags()) == false) goto L60;
        if (r53.getNat().equals(r42.getNat()) == false) goto L60;
        return 52;
    L60:
        r0 = r0 + 1;
    L62:
        if (r53.isSignaturePolymorphic() == false) goto L65;
        return 58;
    L65:
        return 50;
    L67:
        return 47;
    L69:
        return 45;
    L71:
        return 48;
    L73:
        return 46;
    L144:
        return 33;
    L75:
        return 6;
    L77:
        return 11;
    L79:
        return 12;
    L81:
        return 10;
    L83:
        return 9;
    L85:
        return 28;
    L87:
        return 27;
    L89:
        return 32;
    L91:
        return 31;
    L93:
        return 30;
    L95:
        return 29;
    L141:
        return 14;
    L142:
        return 8;
    L143:
        return 7;
    L97:
        return 22;
    L99:
        return 21;
    L100:
        return 20;
    L101:
        return 25;
    L103:
        return 24;
    L105:
        return 23;
    L107:
        return 19;
    L109:
        return 18;
    L111:
        return 17;
    L113:
        return 16;
    L115:
        return 15;
    L117:
        return 14;
    L119:
        return 39;
    L121:
        return 38;
    L145:
        return 2;
    L146:
        return 5;
    L147:
        return 1;
    L123:
        return 8;
    }

    private Insn makeInvokePolymorphicInsn(Rop r1, SourcePosition r2, RegisterSpecList r3, TypeList r4, Constant r5) {
        return new InvokePolymorphicInsn(r1, r2, r3, r4, (CstMethodRef) r5);
    }

    private void updateReturnOp(Rop r3, SourcePosition r4) {
        if (r3 == null) goto L17;
        if (r4 == null) goto L15;
        Rop r0 = this.returnOp;
        if (r0 != null) goto L8;
        this.returnOp = r3;
        this.returnPosition = r4;
        return;
    L8:
        if (r0 == r3) goto L10;
        StringBuilder r02 = new StringBuilder("return op mismatch: ");
        r02.append(r3);
        Rop r2 = this.returnOp;
        r02.append(", ");
        r02.append(r2);
        throw new SimException(r02.toString());
    L10:
        if (r4.getLine() <= this.returnPosition.getLine()) goto L19;
        this.returnPosition = r4;
        return;
    L19:
        return;
    L15:
        f$$ExternalSyntheticBUOutline2.m("pos == null");
        return;
    L17:
        f$$ExternalSyntheticBUOutline2.m("op == null");
    }

    public boolean canThrow() {
        return this.blockCanThrow;
    }

    public int getExtraBlockCount() {
        return this.extraBlockCount;
    }

    public ArrayList<Insn> getInsns() {
        return this.insns;
    }

    public int getPrimarySuccessorIndex() {
        return this.primarySuccessorIndex;
    }

    public ReturnAddress getReturnAddress() {
        return this.returnAddress;
    }

    public Rop getReturnOp() {
        return this.returnOp;
    }

    public SourcePosition getReturnPosition() {
        return this.returnPosition;
    }

    public boolean hasJsr() {
        return this.hasJsr;
    }

    public boolean hasRet() {
        if (this.returnAddress == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean returns() {
        return this.returns;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v8, types: [com.android.dx.rop.type.TypeBearer] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [com.android.dx.rop.code.RegisterSpecList, com.android.dx.rop.type.TypeList] */
    /* JADX WARN: Type inference failed for: r8v18, types: [com.android.dx.rop.code.Insn, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r9v14, types: [int] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v3, types: [com.android.dx.rop.type.TypeBearer] */
    @Override // com.android.dx.cf.code.ValueAwareMachine, com.android.dx.cf.code.Machine
    public void run(Frame r19, int r20, int r21) {
        int r1 = r21;
        int r2 = this.maxLocals + r19.getStack().size();
        RegisterSpecList r6 = getSources(r1, r2);
        int r9 = r6.size();
        super.run(r19, r20, r21);
        SourcePosition r12 = this.method.makeSourcePosistion(r20);
        int r10 = 0;
        if (r1 != 54) goto L5;
        boolean r3 = true;
    L6:
        RegisterSpec r32 = getLocalTarget(r3);
        int r4 = resultCount();
        if (r4 == 0) goto L9;
        if (r32 == null) goto L15;
    L14:
        RegisterSpec r22 = r32;
    L17:
        if (r22 == null) goto L19;
        TypeBearer r14 = r22;
    L20:
        Constant r15 = getAuxCst();
        if (r1 != 197) goto L30;
        this.blockCanThrow = true;
        this.extraBlockCount = 6;
        int r13 = r22.getNextReg();
        Type r33 = Type.INT_ARRAY;
        RegisterSpec r16 = RegisterSpec.make(r13, r33);
        this.insns.add(new ThrowingCstInsn(Rops.opFilledNewArray(r33, r9), r12, r6, this.catches, CstType.INT_ARRAY));
        this.insns.add(new PlainInsn(Rops.opMoveResult(r33), r12, r16, RegisterSpecList.EMPTY));
        Type r34 = ((CstType) r15).getClassType();
        int r42 = 0;
    L23:
        if (r42 >= r9) goto L25;
        r34 = r34.getComponentType();
        r42 = r42 + 1;
        goto L23
    L25:
        RegisterSpec r43 = RegisterSpec.make(r22.getReg(), Type.CLASS);
        if (r34.isPrimitive() == false) goto L28;
        Constant r62 = r15;
        CstFieldRef r152 = CstFieldRef.forPrimitiveType(r34);
        RegisterSpec r8 = null;
        int r5 = 0;
        boolean r35 = true;
        TypeBearer r7 = r14;
        r12 = r12;
        ThrowingCstInsn r102 = new ThrowingCstInsn(Rops.GET_STATIC_OBJECT, r12, RegisterSpecList.EMPTY, this.catches, r152);
    L29:
        this.insns.add(r102);
        Rop r103 = Rops.opMoveResultPseudo(r43.getType());
        RegisterSpecList r132 = RegisterSpecList.EMPTY;
        this.insns.add(new PlainInsn(r103, r12, r43, r132));
        RegisterSpec r104 = RegisterSpec.make(r22.getReg(), Type.OBJECT);
        CstMethodRef r153 = MULTIANEWARRAY_METHOD;
        this.insns.add(new ThrowingCstInsn(Rops.opInvokeStatic(r153.getPrototype()), r12, RegisterSpecList.make(r43, r16), this.catches, r153));
        this.insns.add(new PlainInsn(Rops.opMoveResult(r153.getPrototype().getReturnType()), r12, r104, r132));
        ?? r44 = RegisterSpecList.make(r104);
        r1 = 192;
        ?? r36 = r35;
    L41:
        int r105 = jopToRopOpcode(r1, r62);
        Rop r11 = Rops.ropFor(r105, r7, r44, r62);
        if (r22 != null) goto L44;
    L50:
        if (r22 != null) goto L52;
    L54:
        RegisterSpec r133 = r22;
        ToHuman r23 = r8;
    L56:
        if (r105 != 41) goto L59;
        Constant r154 = CstType.intern(r11.getResult());
        RegisterSpecList r45 = r44;
    L58:
        RegisterSpecList r142 = r45;
        Constant r52 = r154;
    L77:
        SwitchList r46 = getAuxCases();
        ArrayList<Constant> r63 = getInitValues();
        boolean r72 = r11.canThrow();
        this.blockCanThrow |= r72;
        if (r46 == null) goto L86;
        if (r46.size() != 0) goto L83;
        Insn r17 = new PlainInsn(Rops.GOTO, r12, null, RegisterSpecList.EMPTY);
        this.primarySuccessorIndex = 0;
        ToHuman r82 = r23;
        boolean r92 = r36;
    L82:
        Constant r155 = r52;
        ?? r83 = r82;
        ?? r93 = r92;
    L110:
        this.insns.add(r17);
        if (r83 == 0) goto L113;
        this.insns.add(r83);
    L113:
        if (r63 == null) goto L129;
        this.extraBlockCount += r93;
        this.insns.add(new FillArrayDataInsn(Rops.FILL_ARRAY_DATA, r12, RegisterSpecList.make(r83.getResult()), r63, r155));
        return;
    L129:
        return;
    L83:
        IntList r156 = r46.getValues();
        Insn r106 = new SwitchInsn(r11, r12, r133, r142, r156);
        this.primarySuccessorIndex = r156.size();
        ToHuman r84 = r23;
        boolean r94 = r36;
        r155 = r52;
    L84:
        r17 = r106;
        r83 = r84;
        r93 = r94;
        goto L110
    L86:
        if (r105 == 33) goto L88;
        if (r52 == null) goto L102;
        if (r72 == false) goto L101;
        int r18 = r11.getOpcode();
        TypeList r47 = this.catches;
        if (r18 != 58) goto L99;
        ToHuman r85 = r23;
        boolean r95 = r36;
        r17 = makeInvokePolymorphicInsn(r11, r12, r142, r47, r52);
        r155 = r52;
    L100:
        this.catchesUsed = r95;
        this.primarySuccessorIndex = this.catches.size();
        r83 = r85;
        r93 = r95;
        goto L110
    L99:
        r85 = r23;
        r95 = r36;
        r155 = r52;
        r17 = new ThrowingCstInsn(r11, r12, r142, r47, r155);
        goto L100
    L101:
        r84 = r23;
        r94 = r36;
        r155 = r52;
        r106 = new PlainCstInsn(r11, r12, r133, r142, r155);
        goto L84
    L102:
        ToHuman r86 = r23;
        boolean r96 = r36;
        r155 = r52;
        if (r72 == false) goto L109;
        ThrowingInsn r24 = new ThrowingInsn(r11, r12, r142, this.catches);
        this.catchesUsed = r96;
        if (r1 != 191) goto L107;
        this.primarySuccessorIndex = -1;
    L108:
        r17 = r24;
        r83 = r86;
        r93 = r96;
        goto L110
    L107:
        this.primarySuccessorIndex = this.catches.size();
        goto L108
    L109:
        r17 = new PlainInsn(r11, r12, r133, r142);
        r83 = r86;
        r93 = r96;
        goto L110
    L88:
        if (r142.size() == 0) goto L92;
        int r110 = 0;
        RegisterSpec r48 = r142.get(0);
        TypeBearer r73 = r48.getTypeBearer();
        if (r48.getReg() == 0) goto L93;
        this.insns.add(new PlainInsn(Rops.opMove(r73), r12, RegisterSpec.make(0, r73), r48));
    L93:
        PlainInsn r49 = new PlainInsn(Rops.GOTO, r12, null, RegisterSpecList.EMPTY);
        this.primarySuccessorIndex = r110;
        updateReturnOp(r11, r12);
        this.returns = r36;
        r82 = r23;
        r92 = r36;
        r17 = r49;
        goto L82
    L92:
        r110 = 0;
        goto L93
    L59:
        if (r62 == null) goto L61;
    L76:
        r142 = r44;
        r52 = r62;
        goto L77
    L61:
        if (r9 != 2) goto L76;
        ?? r97 = r44.get(r5).getTypeBearer();
        ?? r143 = r44.get(r36).getTypeBearer();
        if (r143.isConstant() == true) goto L67;
        if (r97.isConstant() == false) goto L76;
    L67:
        if (this.advice.hasConstantOperation(r11, r44.get(r5), r44.get(r36)) == false) goto L76;
        if (r143.isConstant() == false) goto L74;
        Constant r53 = (Constant) r143;
        RegisterSpecList r410 = r44.withoutLast();
        if (r11.getOpcode() != 15) goto L73;
        r53 = CstInteger.make(-((CstInteger) r143).getValue());
        r105 = 14;
    L73:
        r154 = r53;
        RegisterSpecList r411 = r410;
    L75:
        r11 = Rops.ropFor(r105, r7, r411, r154);
        r45 = r411;
        goto L58
    L74:
        r154 = (Constant) r97;
        r411 = r44.withoutFirst();
        goto L75
    L52:
        if (r11.canThrow() == false) goto L54;
        this.extraBlockCount += r36;
        r23 = new PlainInsn(Rops.opMoveResultPseudo(r22.getTypeBearer()), r12, r22, RegisterSpecList.EMPTY);
        r133 = r8;
        goto L56
    L44:
        if (r11.isCallLike() == false) goto L50;
        this.extraBlockCount += r36;
        if (r11.getOpcode() != 59) goto L48;
        Type r134 = ((CstCallSiteRef) r62).getReturnType();
    L49:
        ToHuman r144 = new PlainInsn(Rops.opMoveResult(r134), r12, r22, RegisterSpecList.EMPTY);
        r133 = r8;
        r23 = r144;
        goto L56
    L48:
        r134 = ((CstMethodRef) r62).getPrototype().getReturnType();
        goto L49
    L28:
        r12 = r12;
        r5 = 0;
        r8 = null;
        r7 = r14;
        r62 = r15;
        Rop r112 = Rops.CONST_OBJECT;
        RegisterSpecList r135 = RegisterSpecList.EMPTY;
        TypeList r145 = this.catches;
        CstType r157 = new CstType(r34);
        r35 = true;
        r102 = new ThrowingCstInsn(r112, r12, r135, r145, r157);
        goto L29
    L30:
        r44 = r6;
        r5 = 0;
        r36 = 1;
        r8 = null;
        r7 = r14;
        r62 = r15;
        if (r1 != 168) goto L35;
        this.hasJsr = true;
        return;
    L35:
        if (r1 != 169) goto L41;
        this.returnAddress = (ReturnAddress) arg(0);     // Catch: ClassCastException -> L38
        return;
    L38:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Argument to RET was not a ReturnAddress", e);
        return;
    L19:
        r14 = Type.VOID;
        goto L20
    L15:
        if (r4 != 1) goto L116;
        r32 = RegisterSpec.make(r2, result(0));
        goto L14
    L116:
        int r54 = this.ropper.getFirstTempStackReg();
        RegisterSpec[] r64 = new RegisterSpec[r9];
    L117:
        if (r10 >= r9) goto L119;
        RegisterSpec r111 = r6.get(r10);
        TypeBearer r74 = r111.getTypeBearer();
        RegisterSpec r87 = r111.withReg(r54);
        this.insns.add(new PlainInsn(Rops.opMove(r74), r12, r87, r111));
        r64[r10] = r87;
        r54 = r54 + r111.getCategory();
        r10 = r10 + 1;
        goto L117
    L119:
        int r113 = getAuxInt();
    L120:
        if (r113 == 0) goto L130;
        RegisterSpec r412 = r64[(r113 & 15) - 1];
        TypeBearer r55 = r412.getTypeBearer();
        this.insns.add(new PlainInsn(Rops.opMove(r55), r12, r412.withReg(r2), r412));
        r2 = r2 + r55.getType().getCategory();
        r113 = r113 >> 4;
        goto L120
    L130:
        return;
    L9:
        if (r1 != 87) goto L11;
        return;
    L11:
        if (r1 == 88) goto L128;
        r22 = null;
        goto L17
    L128:
        return;
    L5:
        r3 = false;
        goto L6
    }

    public void startBlock(TypeList r1) {
        this.catches = r1;
        this.insns.clear();
        this.catchesUsed = false;
        this.returns = false;
        this.primarySuccessorIndex = 0;
        this.extraBlockCount = 0;
        this.blockCanThrow = false;
        this.hasJsr = false;
        this.returnAddress = null;
    }

    public boolean wereCatchesUsed() {
        return this.catchesUsed;
    }
}
