package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.cf.code.LocalVariableList;
import com.android.dx.dex.DexOptions;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstInterfaceMethodRef;
import com.android.dx.rop.cst.CstInvokeDynamic;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;
import com.android.dx.util.Hex;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class Simulator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOCAL_MISMATCH_ERROR = "This is symptomatic of .class transformation tools that ignore local variable information.";
    private final BytecodeArray code;
    private final DexOptions dexOptions;
    private final LocalVariableList localVariables;
    private final Machine machine;
    private ConcreteMethod method;
    private final SimVisitor visitor;

    public class SimVisitor implements BytecodeArray.Visitor {
        private Frame frame;
        private final Machine machine;
        private int previousOffset;
        final /* synthetic */ Simulator this$0;

        public SimVisitor(Simulator r1) {
            this.this$0 = r1;
            this.machine = Simulator.access$000(r1);
            this.frame = null;
        }

        private void checkReturnType(Type r4) {
            Type r0 = this.machine.getPrototype().getReturnType();
            if (Merger.isPossiblyAssignableFrom(r0, r4) == true) goto L6;
            Simulator.access$200(this.this$0, "return type mismatch: prototype indicates " + r0.toHuman() + ", but encountered type " + r4.toHuman());
            return;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public int getPreviousOffset() {
            return this.previousOffset;
        }

        public void setFrame(Frame r1) {
            if (r1 == null) goto L5;
            this.frame = r1;
            return;
        L5:
            f$$ExternalSyntheticBUOutline2.m("frame == null");
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void setPreviousOffset(int r1) {
            this.previousOffset = r1;
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitBranch(int r3, int r4, int r5, int r6) {
            switch(r3) {
                case 153: goto L10;
                case 154: goto L10;
                case 155: goto L10;
                case 156: goto L10;
                case 157: goto L10;
                case 158: goto L10;
                case 159: goto L9;
                case 160: goto L9;
                case 161: goto L9;
                case 162: goto L9;
                case 163: goto L9;
                case 164: goto L9;
                case 165: goto L8;
                case 166: goto L8;
                case 167: goto L7;
                case 168: goto L7;
                default: goto L3;
            };
        L3:
            switch(r3) {
                case 198: goto L6;
                case 199: goto L6;
                case 200: goto L7;
                case 201: goto L7;
                default: goto L4;
            };
        L4:
            visitInvalid(r3, r4, r5);
            return;
        L6:
            this.machine.popArgs(this.frame, Type.OBJECT);
        L11:
            this.machine.auxTargetArg(r6);
            this.machine.run(this.frame, r4, r3);
            return;
        L7:
            this.machine.clearArgs();
            goto L11
        L8:
            Machine r52 = this.machine;
            Frame r0 = this.frame;
            Type r1 = Type.OBJECT;
            r52.popArgs(r0, r1, r1);
            goto L11
        L9:
            Machine r53 = this.machine;
            Frame r02 = this.frame;
            Type r12 = Type.INT;
            r53.popArgs(r02, r12, r12);
            goto L11
        L10:
            this.machine.popArgs(this.frame, Type.INT);
            goto L11
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitConstant(int r4, int r5, int r6, Constant r7, int r8) {
            if (r4 == 18) goto L36;
            if (r4 == 19) goto L36;
            if (r4 != 189) goto L9;
            this.machine.popArgs(this.frame, Type.INT);
            Constant r72 = r7;
        L41:
            this.machine.auxIntArg(r8);
            this.machine.auxCstArg(r72);
            this.machine.run(this.frame, r5, r4);
            return;
        L9:
            if (r4 != 197) goto L11;
            Prototype r62 = Prototype.internInts(Type.VOID, r8);
            this.machine.popArgs(this.frame, r62);
            r72 = r7;
            goto L41
        L11:
            if (r4 != 192) goto L13;
        L32:
            this.machine.popArgs(this.frame, Type.OBJECT);
            r72 = r7;
            goto L41
        L13:
            if (r4 == 193) goto L32;
            switch(r4) {
                case 179: goto L31;
                case 180: goto L32;
                case 181: goto L30;
                case 182: goto L17;
                case 183: goto L17;
                case 184: goto L17;
                case 185: goto L17;
                case 186: goto L16;
                default: goto L15;
            };
        L15:
            this.machine.clearArgs();
            r72 = r7;
            goto L41
        L16:
            Simulator.access$700(this.this$0, r4);
            CstInvokeDynamic r73 = (CstInvokeDynamic) r7;
            Prototype r63 = r73.getPrototype();
            this.machine.popArgs(this.frame, r63);
            r72 = r73.addReference();
            goto L41
        L17:
            boolean r64 = r7 instanceof CstInterfaceMethodRef;
            Constant r74 = r7;
            if (r64 == false) goto L21;
            CstMethodRef r75 = ((CstInterfaceMethodRef) r7).toMethodRef();
            Simulator.access$500(this.this$0, r4, r75);
            r74 = r75;
        L21:
            if ((r74 instanceof CstMethodRef) == false) goto L26;
            if (((CstMethodRef) r74).isSignaturePolymorphic() == false) goto L26;
            Simulator.access$600(this.this$0, r4);
        L26:
            if (r4 != 184) goto L28;
            boolean r65 = true;
        L29:
            Prototype r66 = ((CstMethodRef) r74).getPrototype(r65);
            this.machine.popArgs(this.frame, r66);
            r72 = r74;
            goto L41
        L28:
            r65 = false;
            goto L29
        L30:
            Type r67 = ((CstFieldRef) r7).getType();
            this.machine.popArgs(this.frame, Type.OBJECT, r67);
            r72 = r7;
            goto L41
        L31:
            Type r68 = ((CstFieldRef) r7).getType();
            this.machine.popArgs(this.frame, r68);
            r72 = r7;
        L36:
            if ((r7 instanceof CstMethodHandle) == false) goto L38;
        L39:
            Simulator.access$800(this.this$0, r7);
        L40:
            this.machine.clearArgs();
            r72 = r7;
            goto L41
        L38:
            if ((r7 instanceof CstProtoRef) == false) goto L40;
            goto L39
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitInvalid(int r1, int r2, int r3) {
            throw new SimException("invalid opcode " + Hex.u1(r1));
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitLocal(int r7, int r8, int r9, int r10, Type r11, int r12) {
            if (r7 != 54) goto L5;
            int r1 = r8 + r9;
        L6:
            LocalVariableList.Item r13 = Simulator.access$400(this.this$0).pcAndIndexToLocal(r1, r10);
            LocalItem r2 = null;
            if (r13 == null) goto L11;
            Type r3 = r13.getType();
            if (r3.getBasicFrameType() == r11.getBasicFrameType()) goto L13;
            r3 = r11;
            r13 = null;
        L13:
            if (r7 == 21) goto L29;
            if (r7 != 54) goto L16;
            if (r13 == null) goto L28;
            r2 = r13.getLocalItem();
        L28:
            this.machine.popArgs(this.frame, r11);
            this.machine.auxType(r11);
            this.machine.localTarget(r10, r3, r2);
        L34:
            this.machine.run(this.frame, r8, r7);
            return;
        L16:
            if (r7 != 132) goto L18;
            if (r13 == null) goto L24;
            r2 = r13.getLocalItem();
        L24:
            this.machine.localArg(this.frame, r10);
            this.machine.localTarget(r10, r3, r2);
            this.machine.auxType(r11);
            this.machine.auxIntArg(r12);
            this.machine.auxCstArg(CstInteger.make(r12));
            goto L34
        L18:
            if (r7 == 169) goto L29;
            visitInvalid(r7, r8, r9);
            return;
        L29:
            this.machine.localArg(this.frame, r10);
            Machine r92 = this.machine;
            if (r13 == null) goto L32;
            boolean r102 = true;
        L33:
            r92.localInfo(r102);
            this.machine.auxType(r11);
            goto L34
        L32:
            r102 = false;
            goto L33
        L11:
            r3 = r11;
            goto L13
        L5:
            r1 = r8;
            goto L6
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNewarray(int r3, int r4, CstType r5, ArrayList<Constant> r6) {
            this.machine.popArgs(this.frame, Type.INT);
            this.machine.auxInitValues(r6);
            this.machine.auxCstArg(r5);
            this.machine.run(this.frame, r3, 188);
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitNoArgs(int r9, int r10, int r11, Type r12) {
            if (r9 != 0) goto L4;
        L138:
            this.machine.clearArgs();
        L139:
            this.machine.auxType(r12);
            this.machine.run(this.frame, r10, r9);
            return;
        L4:
            if (r9 != 190) goto L6;
            Type r112 = this.frame.getStack().peekType(0);
            if (r112.isArrayOrKnownNull() == true) goto L137;
            Simulator.access$200(this.this$0, "type mismatch: expected array type but encountered " + r112.toHuman());
        L137:
            this.machine.popArgs(this.frame, Type.OBJECT);
            goto L139
        L6:
            if (r9 != 191) goto L8;
        L133:
            this.machine.popArgs(this.frame, Type.OBJECT);
            goto L139
        L8:
            if (r9 == 194) goto L133;
            if (r9 == 195) goto L133;
            int r0 = 3;
            switch(r9) {
                case 0: goto L138;
                case 46: goto L128;
                case 79: goto L117;
                case 100: goto L116;
                case 104: goto L116;
                case 108: goto L116;
                case 112: goto L116;
                case 116: goto L115;
                case 120: goto L114;
                case 122: goto L114;
                case 124: goto L114;
                case 126: goto L116;
                case 128: goto L116;
                case 130: goto L116;
                case 172: goto L110;
                case 177: goto L108;
                default: goto L13;
            };
        L13:
            int r4 = 17;
            switch(r9) {
                case 87: goto L104;
                case 88: goto L92;
                case 89: goto L88;
                case 90: goto L80;
                case 91: goto L68;
                case 92: goto L92;
                case 93: goto L54;
                case 94: goto L32;
                case 95: goto L25;
                case 96: goto L116;
                default: goto L15;
            };
        L15:
            switch(r9) {
                case 133: goto L24;
                case 134: goto L24;
                case 135: goto L24;
                case 136: goto L23;
                case 137: goto L23;
                case 138: goto L23;
                case 139: goto L22;
                case 140: goto L22;
                case 141: goto L22;
                case 142: goto L21;
                case 143: goto L21;
                case 144: goto L21;
                case 145: goto L24;
                case 146: goto L24;
                case 147: goto L24;
                case 148: goto L20;
                case 149: goto L19;
                case 150: goto L19;
                case 151: goto L18;
                case 152: goto L18;
                default: goto L16;
            };
        L16:
            visitInvalid(r9, r10, r11);
            return;
        L18:
            Machine r113 = this.machine;
            Frame r02 = this.frame;
            Type r1 = Type.DOUBLE;
            r113.popArgs(r02, r1, r1);
            goto L139
        L19:
            Machine r114 = this.machine;
            Frame r03 = this.frame;
            Type r13 = Type.FLOAT;
            r114.popArgs(r03, r13, r13);
            goto L139
        L20:
            Machine r115 = this.machine;
            Frame r04 = this.frame;
            Type r14 = Type.LONG;
            r115.popArgs(r04, r14, r14);
            goto L139
        L21:
            this.machine.popArgs(this.frame, Type.DOUBLE);
            goto L139
        L22:
            this.machine.popArgs(this.frame, Type.FLOAT);
            goto L139
        L23:
            this.machine.popArgs(this.frame, Type.LONG);
            goto L139
        L24:
            this.machine.popArgs(this.frame, Type.INT);
            goto L139
        L25:
            ExecutionStack r116 = this.frame.getStack();
            if (r116.peekType(0).isCategory1() == false) goto L31;
            if (r116.peekType(1).isCategory1() == false) goto L31;
            this.machine.popArgs(this.frame, 2);
            this.machine.auxIntArg(18);
        L31:
            throw Simulator.access$100();
        L32:
            ExecutionStack r117 = this.frame.getStack();
            if (r117.peekType(0).isCategory2() == false) goto L43;
            if (r117.peekType(2).isCategory2() == false) goto L38;
            this.machine.popArgs(this.frame, 2);
            this.machine.auxIntArg(530);
            goto L139
        L38:
            if (r117.peekType(3).isCategory1() == false) goto L41;
            this.machine.popArgs(this.frame, 3);
            this.machine.auxIntArg(12819);
            goto L139
        L41:
            throw Simulator.access$100();
        L43:
            if (r117.peekType(1).isCategory1() == false) goto L53;
            if (r117.peekType(2).isCategory2() == false) goto L48;
            this.machine.popArgs(this.frame, 3);
            this.machine.auxIntArg(205106);
            goto L139
        L48:
            if (r117.peekType(3).isCategory1() == false) goto L51;
            this.machine.popArgs(this.frame, 4);
            this.machine.auxIntArg(4399427);
            goto L139
        L51:
            throw Simulator.access$100();
        L53:
            throw Simulator.access$100();
        L54:
            ExecutionStack r118 = this.frame.getStack();
            if (r118.peekType(0).isCategory2() == false) goto L62;
            if (r118.peekType(2).isCategory2() == true) goto L60;
            this.machine.popArgs(this.frame, 2);
            this.machine.auxIntArg(530);
            goto L139
        L60:
            throw Simulator.access$100();
        L62:
            if (r118.peekType(1).isCategory2() == true) goto L67;
            if (r118.peekType(2).isCategory2() == true) goto L67;
            this.machine.popArgs(this.frame, 3);
            this.machine.auxIntArg(205106);
        L67:
            throw Simulator.access$100();
        L68:
            ExecutionStack r119 = this.frame.getStack();
            if (r119.peekType(0).isCategory2() == true) goto L79;
            if (r119.peekType(1).isCategory2() == false) goto L74;
            this.machine.popArgs(this.frame, 2);
            this.machine.auxIntArg(530);
            goto L139
        L74:
            if (r119.peekType(2).isCategory1() == false) goto L77;
            this.machine.popArgs(this.frame, 3);
            this.machine.auxIntArg(12819);
            goto L139
        L77:
            throw Simulator.access$100();
        L79:
            throw Simulator.access$100();
        L80:
            ExecutionStack r1110 = this.frame.getStack();
            if (r1110.peekType(0).isCategory1() == false) goto L86;
            if (r1110.peekType(1).isCategory1() == false) goto L86;
            this.machine.popArgs(this.frame, 2);
            this.machine.auxIntArg(530);
        L86:
            throw Simulator.access$100();
        L92:
            ExecutionStack r1111 = this.frame.getStack();
            if (r1111.peekType(0).isCategory2() == false) goto L96;
            this.machine.popArgs(this.frame, 1);
        L99:
            if (r9 != 92) goto L139;
            this.machine.auxIntArg(r4);
            goto L139
        L96:
            if (r1111.peekType(1).isCategory1() == false) goto L102;
            this.machine.popArgs(this.frame, 2);
            r4 = 8481;
            goto L99
        L102:
            throw Simulator.access$100();
        L88:
            if (this.frame.getStack().peekType(0).isCategory2() == true) goto L91;
            this.machine.popArgs(this.frame, 1);
            this.machine.auxIntArg(17);
            goto L139
        L91:
            throw Simulator.access$100();
        L104:
            if (this.frame.getStack().peekType(0).isCategory2() == true) goto L107;
            this.machine.popArgs(this.frame, 1);
            goto L139
        L107:
            throw Simulator.access$100();
        L108:
            this.machine.clearArgs();
            checkReturnType(Type.VOID);
            goto L139
        L114:
            this.machine.popArgs(this.frame, r12, Type.INT);
            goto L139
        L115:
            this.machine.popArgs(this.frame, r12);
        L116:
            this.machine.popArgs(this.frame, r12, r12);
            goto L139
        L117:
            ExecutionStack r1112 = this.frame.getStack();
            if (r12.isCategory1() == false) goto L120;
            r0 = 2;
        L120:
            Type r15 = r1112.peekType(r0);
            boolean r1113 = r1112.peekLocal(r0);
            Type r05 = Simulator.access$300(r12, r15);
            if (r1113 == false) goto L127;
            Type r1114 = Type.KNOWN_NULL;
            if (r05 == r1114) goto L126;
            r1114 = r05.getComponentType();
        L126:
            r12 = r1114;
        L127:
            this.machine.popArgs(this.frame, r05, Type.INT, r12);
            goto L139
        L128:
            Type r1115 = Simulator.access$300(r12, this.frame.getStack().peekType(1));
            r12 = Type.KNOWN_NULL;
            if (r1115 == r12) goto L132;
            r12 = r1115.getComponentType();
        L132:
            this.machine.popArgs(this.frame, r1115, Type.INT);
            goto L139
        L110:
            if (r12 != Type.OBJECT) goto L112;
            Type r1116 = this.frame.getStack().peekType(0);
        L113:
            this.machine.popArgs(this.frame, r12);
            checkReturnType(r1116);
            goto L139
        L112:
            r1116 = r12;
            goto L113
        }

        @Override // com.android.dx.cf.code.BytecodeArray.Visitor
        public void visitSwitch(int r3, int r4, int r5, SwitchList r6, int r7) {
            this.machine.popArgs(this.frame, Type.INT);
            this.machine.auxIntArg(r7);
            this.machine.auxSwitchArg(r6);
            this.machine.run(this.frame, r4, r3);
        }
    }

    static {
    }

    public Simulator(Machine r2, ConcreteMethod r3, DexOptions r4) {
        if (r2 == null) goto L14;
        if (r3 == null) goto L12;
        if (r4 == null) goto L10;
        this.machine = r2;
        this.code = r3.getCode();
        this.method = r3;
        this.localVariables = r3.getLocalVariables();
        this.visitor = new SimVisitor(this);
        this.dexOptions = r4;
        if (r3.isDefaultOrStaticInterfaceMethod() == false) goto L16;
        checkInterfaceMethodDeclaration(r3);
        return;
    L16:
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("dexOptions == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("machine == null");
        throw null;
    }

    public static /* synthetic */ Machine access$000(Simulator r0) {
        return r0.machine;
    }

    public static /* synthetic */ SimException access$100() {
        return illegalTos();
    }

    public static /* synthetic */ void access$200(Simulator r0, String r1) {
        r0.fail(r1);
    }

    public static /* synthetic */ Type access$300(Type r0, Type r1) {
        return requiredArrayTypeFor(r0, r1);
    }

    public static /* synthetic */ LocalVariableList access$400(Simulator r0) {
        return r0.localVariables;
    }

    public static /* synthetic */ void access$500(Simulator r0, int r1, CstMethodRef r2) {
        r0.checkInvokeInterfaceSupported(r1, r2);
    }

    public static /* synthetic */ void access$600(Simulator r0, int r1) {
        r0.checkInvokeSignaturePolymorphic(r1);
    }

    public static /* synthetic */ void access$700(Simulator r0, int r1) {
        r0.checkInvokeDynamicSupported(r1);
    }

    public static /* synthetic */ void access$800(Simulator r0, Constant r1) {
        r0.checkConstMethodHandleSupported(r1);
    }

    private void checkConstMethodHandleSupported(Constant r3) {
        if (this.dexOptions.apiIsSupported(28) == true) goto L6;
        fail(String.format("invalid constant type %s requires --min-sdk-version >= %d (currently %d)", new Object[]{r3.typeName(), 28, Integer.valueOf(this.dexOptions.minSdkVersion)}));
        return;
    }

    private void checkInterfaceMethodDeclaration(ConcreteMethod r5) {
        if (this.dexOptions.apiIsSupported(24) == false) goto L5;
        return;
    L5:
        if (r5.isStaticMethod() == false) goto L7;
        String r0 = "static";
    L8:
        warn(String.format("defining a %s interface method requires --min-sdk-version >= %d (currently %d) for interface methods: %s.%s", new Object[]{r0, 24, Integer.valueOf(this.dexOptions.minSdkVersion), r5.getDefiningClass().toHuman(), r5.getNat().toHuman()}));
        return;
    L7:
        r0 = PluginsConstants.Settings.DEFAULT;
        goto L8
    }

    private void checkInvokeDynamicSupported(int r3) {
        if (this.dexOptions.apiIsSupported(26) == true) goto L6;
        fail(String.format("invalid opcode %02x - invokedynamic requires --min-sdk-version >= %d (currently %d)", new Object[]{Integer.valueOf(r3), 26, Integer.valueOf(this.dexOptions.minSdkVersion)}));
        return;
    }

    private void checkInvokeInterfaceSupported(int r6, CstMethodRef r7) {
        if (r6 != 185) goto L6;
        return;
    L6:
        if (this.dexOptions.apiIsSupported(24) == false) goto L8;
        return;
    L8:
        DexOptions r0 = this.dexOptions;
        boolean r2 = r0.allowAllInterfaceMethodInvokes;
        if (r6 != 184) goto L11;
        r2 = r2 & r0.apiIsSupported(21);
    L11:
        if (r6 != 184) goto L13;
        String r62 = "static";
    L14:
        if (r2 == false) goto L17;
        warn(String.format("invoking a %s interface method %s.%s strictly requires --min-sdk-version >= %d (experimental at current API level %d)", new Object[]{r62, r7.getDefiningClass().toHuman(), r7.getNat().toHuman(), 24, Integer.valueOf(this.dexOptions.minSdkVersion)}));
        return;
    L17:
        fail(String.format("invoking a %s interface method %s.%s strictly requires --min-sdk-version >= %d (blocked at current API level %d)", new Object[]{r62, r7.getDefiningClass().toHuman(), r7.getNat().toHuman(), 24, Integer.valueOf(this.dexOptions.minSdkVersion)}));
        return;
    L13:
        r62 = PluginsConstants.Settings.DEFAULT;
        goto L14
    }

    private void checkInvokeSignaturePolymorphic(int r3) {
        if (this.dexOptions.apiIsSupported(26) == true) goto L7;
        fail(String.format("invoking a signature-polymorphic requires --min-sdk-version >= %d (currently %d)", new Object[]{26, Integer.valueOf(this.dexOptions.minSdkVersion)}));
        return;
    L7:
        if (r3 == 182) goto L10;
        fail("Unsupported signature polymorphic invocation (" + ByteOps.opName(r3) + ")");
        return;
    }

    private void fail(String r2) {
        throw new SimException(String.format("ERROR in %s.%s: %s", new Object[]{this.method.getDefiningClass().toHuman(), this.method.getNat().toHuman(), r2}));
    }

    private static SimException illegalTos() {
        return new SimException("stack mismatch: illegal top-of-stack for opcode");
    }

    private static Type requiredArrayTypeFor(Type r1, Type r2) {
        Type r0 = Type.KNOWN_NULL;
        if (r2 != r0) goto L10;
        if (r1.isReference() == false) goto L8;
        return r0;
    L8:
        return r1.getArrayType();
    L10:
        if (r1 != Type.OBJECT) goto L17;
        if (r2.isArray() == false) goto L17;
        if (r2.getComponentType().isReference() == false) goto L17;
        return r2;
    L17:
        if (r1 != Type.BYTE) goto L22;
        Type r02 = Type.BOOLEAN_ARRAY;
        if (r2 != r02) goto L22;
        return r02;
    L22:
        return r1.getArrayType();
    }

    private void warn(String r3) {
        String r32 = String.format("WARNING in %s.%s: %s", new Object[]{this.method.getDefiningClass().toHuman(), this.method.getNat().toHuman(), r3});
        this.dexOptions.err.println(r32);
    }

    public int simulate(int r2, Frame r3) {
        this.visitor.setFrame(r3);
        return this.code.parseInstruction(r2, this.visitor);
    }

    public void simulate(ByteBlock r4, Frame r5) {
        int r0 = r4.getEnd();
        this.visitor.setFrame(r5);
        int r42 = r4.getStart();     // Catch: SimException -> L7
    L4:
        if (r42 >= r0) goto L9;
        int r1 = this.code.parseInstruction(r42, this.visitor);     // Catch: SimException -> L7
        this.visitor.setPreviousOffset(r42);     // Catch: SimException -> L7
        r42 = r42 + r1;
        goto L4
    L9:
        return;
    L7:
        e = move-exception;
        r5.annotate(e);
        throw e;
    }
}
