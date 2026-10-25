package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex$$ExternalSyntheticBUOutline2;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.RegisterSpecSet;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstMemberRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.ssa.BasicRegisterMapper;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class OutputFinisher {
    private final DexOptions dexOptions;
    private boolean hasAnyLocalInfo;
    private boolean hasAnyPositionInfo;
    private ArrayList<DalvInsn> insns;
    private final int paramSize;
    private int reservedCount;
    private int reservedParameterCount;
    private final int unreservedRegCount;

    public OutputFinisher(DexOptions r1, int r2, int r3, int r4) {
        this.dexOptions = r1;
        this.unreservedRegCount = r3;
        this.insns = new ArrayList(r2);
        this.reservedCount = -1;
        this.hasAnyPositionInfo = false;
        this.hasAnyLocalInfo = false;
        this.paramSize = r4;
    }

    private static void addConstants(HashSet<Constant> r3, DalvInsn r4) {
        if ((r4 instanceof CstInsn) == false) goto L6;
        r3.add(((CstInsn) r4).getConstant());
        return;
    L6:
        int r1 = 0;
        if ((r4 instanceof MultiCstInsn) == false) goto L13;
        MultiCstInsn r42 = (MultiCstInsn) r4;
    L10:
        if (r1 >= r42.getNumberOfConstants()) goto L24;
        r3.add(r42.getConstant(r1));
        r1 = r1 + 1;
        goto L10
    L24:
        return;
    L13:
        if ((r4 instanceof LocalSnapshot) == false) goto L18;
        RegisterSpecSet r43 = ((LocalSnapshot) r4).getLocals();
        int r0 = r43.size();
    L15:
        if (r1 >= r0) goto L25;
        addConstants(r3, r43.get(r1));
        r1 = r1 + 1;
        goto L15
    L25:
        return;
    L18:
        if ((r4 instanceof LocalStart) == false) goto L23;
        addConstants(r3, ((LocalStart) r4).getLocal());
        return;
    }

    private static void addConstants(HashSet<Constant> r3, RegisterSpec r4) {
        if (r4 == null) goto L14;
        LocalItem r0 = r4.getLocalItem();
        CstString r1 = r0.getName();
        CstString r02 = r0.getSignature();
        Type r42 = r4.getType();
        if (r42 == Type.KNOWN_NULL) goto L7;
        r3.add(CstType.intern(r42));
    L8:
        if (r1 == null) goto L10;
        r3.add(r1);
    L10:
        if (r02 == null) goto L13;
        r3.add(r02);
        return;
    L13:
        return;
    L7:
        r3.add(CstType.intern(Type.OBJECT));
        goto L8
    }

    private void addReservedParameters(int r2) {
        shiftParameters(r2);
        this.reservedParameterCount += r2;
    }

    private void addReservedRegisters(int r2) {
        shiftAllRegisters(r2);
        this.reservedCount += r2;
    }

    private void align64bits(Dop[] r15) {
    L2:
        int r0 = ((this.unreservedRegCount + this.reservedCount) + this.reservedParameterCount) - this.paramSize;
        ArrayList<DalvInsn> r1 = this.insns;
        int r2 = r1.size();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
    L4:
        if (r8 >= r2) goto L23;
        DalvInsn r10 = r1.get(r8);
        r8 = r8 + 1;
        RegisterSpecList r102 = r10.getRegisters();
        int r11 = 0;
    L7:
        if (r11 >= r102.size()) goto L4;
        RegisterSpec r12 = r102.get(r11);
        if (r12.isCategory2() == false) goto L22;
        if (r12.getReg() < r0) goto L13;
        boolean r13 = true;
    L15:
        if (r12.isEvenRegister() == false) goto L19;
        if (r13 == false) goto L18;
        r5 = r5 + 1;
        goto L22
    L18:
        r7 = r7 + 1;
        goto L22
    L19:
        if (r13 == false) goto L21;
        r4 = r4 + 1;
        goto L22
    L21:
        r6 = r6 + 1;
        goto L22
    L13:
        r13 = false;
    L22:
        r11 = r11 + 1;
        goto L7
    L23:
        if (r4 <= r5) goto L26;
        if (r6 <= r7) goto L26;
        addReservedRegisters(1);
    L34:
        if (reserveRegisters(r15) == true) goto L2;
        return;
    L26:
        if (r4 <= r5) goto L28;
        addReservedParameters(1);
        goto L34
    L28:
        if (r6 <= r7) goto L35;
        addReservedRegisters(1);
        if (this.paramSize == 0) goto L34;
        if (r5 <= r4) goto L34;
        addReservedParameters(1);
        goto L34
    }

    private void assignAddresses() {
        int r0 = this.insns.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        DalvInsn r3 = this.insns.get(r1);
        r3.setAddress(r2);
        r2 = r2 + r3.codeSize();
        r1 = r1 + 1;
        goto L3
    }

    private void assignAddressesAndFixBranches() {
    L2:
        assignAddresses();
        if (fixBranches() == true) goto L2;
    }

    private static void assignIndices(CstInsn r2, DalvCode.AssignIndicesCallback r3) {
        Constant r0 = r2.getConstant();
        int r1 = r3.getIndex(r0);
        if (r1 < 0) goto L6;
        r2.setIndex(r1);
    L6:
        if ((r0 instanceof CstMemberRef) == false) goto L11;
        int r32 = r3.getIndex(((CstMemberRef) r0).getDefiningClass());
        if (r32 < 0) goto L12;
        r2.setClassIndex(r32);
        return;
    L12:
        return;
    }

    private static void assignIndices(MultiCstInsn r3, DalvCode.AssignIndicesCallback r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.getNumberOfConstants()) goto L9;
        Constant r1 = r3.getConstant(r0);
        r3.setIndex(r0, r4.getIndex(r1));
        if ((r1 instanceof CstMemberRef) == false) goto L8;
        r3.setClassIndex(r4.getIndex(((CstMemberRef) r1).getDefiningClass()));
    L8:
        r0 = r0 + 1;
        goto L4
    }

    private int calculateReservedCount(Dop[] r7) {
        int r0 = this.insns.size();
        int r1 = this.reservedCount;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        DalvInsn r3 = this.insns.get(r2);
        Dop r4 = r7[r2];
        Dop r5 = findOpcodeForInsn(r3, r4);
        if (r5 != null) goto L9;
        int r32 = r3.getMinimumRegisterRequirement(findExpandedOpcodeForInsn(r3).getFormat().compatibleRegs(r3));
        if (r32 <= r1) goto L11;
        r1 = r32;
    L11:
        r7[r2] = r5;
    L12:
        r2 = r2 + 1;
        goto L3
    L9:
        if (r4 != r5) goto L11;
    L13:
        return r1;
    }

    private Dop findExpandedOpcodeForInsn(DalvInsn r3) {
        Dop r2 = findOpcodeForInsn(r3.getLowRegVersion(), r3.getOpcode());
        if (r2 == null) goto L5;
        return r2;
    L5:
        Dex$$ExternalSyntheticBUOutline2.m("No expanded opcode for ", r3);
        return null;
    }

    private Dop findOpcodeForInsn(DalvInsn r3, Dop r4) {
    L2:
        if (r4 == null) goto L11;
        if (r4.getFormat().isCompatible(r3) == false) goto L10;
        if (this.dexOptions.forceJumbo == false) goto L11;
        if (r4.getOpcode() != 26) goto L11;
    L10:
        r4 = Dops.getNextOrNull(r4, this.dexOptions);
    L11:
        return r4;
    }

    private boolean fixBranches() {
        int r0 = this.insns.size();
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L25;
        DalvInsn r4 = this.insns.get(r2);
        if ((r4 instanceof TargetInsn) == false) goto L20;
        Dop r5 = r4.getOpcode();
        TargetInsn r7 = (TargetInsn) r4;
        if (r5.getFormat().branchFits(r7) == true) goto L20;
        if (r5.getFamily() != 40) goto L26;
        Dop r32 = findOpcodeForInsn(r4, r5);
        if (r32 == null) goto L15;
        this.insns.set(r2, r4.withOpcode(r32));
    L19:
        r3 = true;
        goto L20
    L15:
        ByteString$$ExternalSyntheticBUOutline0.m("method too long");
        return false;
    L26:
        int r42 = r2 + 1;     // Catch: ClassCastException -> L21 IndexOutOfBoundsException -> L23
        CodeAddress r33 = (CodeAddress) this.insns.get(r42);     // Catch: ClassCastException -> L21 IndexOutOfBoundsException -> L23
        this.insns.set(r2, new TargetInsn(Dops.GOTO, r7.getPosition(), RegisterSpecList.EMPTY, r7.getTarget()));
        this.insns.add(r2, r7.withNewTargetAndReversed(r33));
        r0 = r0 + 1;
        r2 = r42;
        goto L19
    L21:
        Segment$$ExternalSyntheticBUOutline1.m("unpaired TargetInsn");
        return false;
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("unpaired TargetInsn (dangling)");
        return false;
    L20:
        r2 = r2 + 1;
        goto L3
    L25:
        return r3;
    }

    private static boolean hasLocalInfo(DalvInsn r5) {
        if ((r5 instanceof LocalSnapshot) == false) goto L11;
        RegisterSpecSet r52 = ((LocalSnapshot) r5).getLocals();
        int r0 = r52.size();
        int r3 = 0;
    L5:
        if (r3 >= r0) goto L15;
        if (hasLocalInfo(r52.get(r3)) == true) goto L8;
        r3 = r3 + 1;
        goto L5
    L8:
        return true;
    L15:
        return false;
    L11:
        if ((r5 instanceof LocalStart) == false) goto L15;
        if (hasLocalInfo(((LocalStart) r5).getLocal()) == false) goto L15;
        return true;
    }

    private static boolean hasLocalInfo(RegisterSpec r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.getLocalItem().getName() == null) goto L9;
        return true;
    L9:
        return false;
    }

    private Dop[] makeOpcodesArray() {
        int r0 = this.insns.size();
        Dop[] r1 = new Dop[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = this.insns.get(r2).getOpcode();
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private void massageInstructions(Dop[] r6) {
        if (this.reservedCount != 0) goto L11;
        int r0 = this.insns.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L10;
        DalvInsn r2 = this.insns.get(r1);
        Dop r3 = r2.getOpcode();
        Dop r4 = r6[r1];
        if (r3 == r4) goto L9;
        this.insns.set(r1, r2.withOpcode(r4));
    L9:
        r1 = r1 + 1;
        goto L5
    L10:
        return;
    L11:
        this.insns = performExpansion(r6);
    }

    private ArrayList<DalvInsn> performExpansion(Dop[] r13) {
        int r0 = this.insns.size();
        ArrayList<DalvInsn> r1 = new ArrayList(r0 * 2);
        ArrayList r2 = new ArrayList();
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L29;
        DalvInsn r5 = this.insns.get(r4);
        Dop r6 = r5.getOpcode();
        Dop r7 = r13[r4];
        if (r7 == null) goto L7;
        DalvInsn r8 = null;
        DalvInsn r10 = null;
    L9:
        if ((r5 instanceof CodeAddress) == false) goto L13;
        CodeAddress r9 = (CodeAddress) r5;
        if (r9.getBindsClosely() == false) goto L13;
        r2.add(r9);
    L28:
        r4 = r4 + 1;
    L13:
        if (r8 == null) goto L16;
        r1.add(r8);
    L16:
        if ((r5 instanceof ZeroSizeInsn) == false) goto L18;
    L23:
        if (r7 == r6) goto L25;
        r5 = r5.withOpcode(r7);
    L25:
        r1.add(r5);
        if (r10 == null) goto L28;
        r1.add(r10);
        goto L28
    L18:
        if (r2.size() <= 0) goto L23;
        int r82 = r2.size();
        int r92 = 0;
    L20:
        if (r92 >= r82) goto L22;
        Object r11 = r2.get(r92);
        r92 = r92 + 1;
        r1.add((CodeAddress) r11);
        goto L20
    L22:
        r2.clear();
        goto L23
    L7:
        r7 = findExpandedOpcodeForInsn(r5);
        BitSet r83 = r7.getFormat().compatibleRegs(r5);
        DalvInsn r93 = r5.expandedPrefix(r83);
        r10 = r5.expandedSuffix(r83);
        r5 = r5.expandedVersion(r83);
        r8 = r93;
        goto L9
    L29:
        return r1;
    }

    private boolean reserveRegisters(Dop[] r8) {
        int r0 = this.reservedCount;
        if (r0 >= 0) goto L5;
        r0 = 0;
    L5:
        boolean r2 = false;
    L6:
        int r3 = calculateReservedCount(r8);
        if (r0 >= r3) goto L8;
        int r02 = r3 - r0;
        int r22 = this.insns.size();
        int r4 = 0;
    L11:
        if (r4 >= r22) goto L16;
        DalvInsn r5 = this.insns.get(r4);
        if ((r5 instanceof CodeAddress) == true) goto L15;
        this.insns.set(r4, r5.withRegisterOffset(r02));
    L15:
        r4 = r4 + 1;
        goto L11
    L16:
        r2 = true;
        r0 = r3;
        goto L6
    L8:
        this.reservedCount = r0;
        return r2;
    }

    private void shiftAllRegisters(int r5) {
        int r0 = this.insns.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        DalvInsn r2 = this.insns.get(r1);
        if ((r2 instanceof CodeAddress) == true) goto L7;
        this.insns.set(r1, r2.withRegisterOffset(r5));
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void shiftParameters(int r9) {
        int r0 = this.insns.size();
        int r1 = (this.unreservedRegCount + this.reservedCount) + this.reservedParameterCount;
        int r2 = r1 - this.paramSize;
        BasicRegisterMapper r3 = new BasicRegisterMapper(r1);
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r5 >= r1) goto L9;
        if (r5 < r2) goto L7;
        r3.addMapping(r5, r5 + r9, 1);
    L8:
        r5 = r5 + 1;
        goto L3
    L7:
        r3.addMapping(r5, r5, 1);
    L9:
        if (r4 >= r0) goto L14;
        DalvInsn r92 = this.insns.get(r4);
        if ((r92 instanceof CodeAddress) == true) goto L13;
        this.insns.set(r4, r92.withMapper(r3));
    L13:
        r4 = r4 + 1;
        goto L9
    }

    private void updateInfo(DalvInsn r3) {
        if (this.hasAnyPositionInfo == true) goto L8;
        if (r3.getPosition().getLine() < 0) goto L8;
        this.hasAnyPositionInfo = true;
    L8:
        if (this.hasAnyLocalInfo == false) goto L10;
        return;
    L10:
        if (hasLocalInfo(r3) == false) goto L14;
        this.hasAnyLocalInfo = true;
        return;
    }

    public void add(DalvInsn r2) {
        this.insns.add(r2);
        updateInfo(r2);
    }

    public void assignIndices(DalvCode.AssignIndicesCallback r5) {
        ArrayList<DalvInsn> r4 = this.insns;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        DalvInsn r2 = r4.get(r1);
        r1 = r1 + 1;
        DalvInsn r22 = r2;
        if ((r22 instanceof CstInsn) == true) goto L6;
        if ((r22 instanceof MultiCstInsn) == false) goto L3;
        assignIndices((MultiCstInsn) r22, r5);
        goto L3
    L6:
        assignIndices((CstInsn) r22, r5);
        goto L3
    }

    public DalvInsnList finishProcessingAndGetList() {
        if (this.reservedCount >= 0) goto L9;
        Dop[] r0 = makeOpcodesArray();
        reserveRegisters(r0);
        if (this.dexOptions.ALIGN_64BIT_REGS_IN_OUTPUT_FINISHER == false) goto L7;
        align64bits(r0);
    L7:
        massageInstructions(r0);
        assignAddressesAndFixBranches();
        return DalvInsnList.makeImmutable(this.insns, (this.reservedCount + this.unreservedRegCount) + this.reservedParameterCount);
    L9:
        ByteString$$ExternalSyntheticBUOutline0.m("already processed");
        return null;
    }

    public HashSet<Constant> getAllConstants() {
        HashSet<Constant> r0 = new HashSet(20);
        ArrayList<DalvInsn> r4 = this.insns;
        int r1 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        DalvInsn r3 = r4.get(r2);
        r2 = r2 + 1;
        addConstants(r0, r3);
        goto L3
    L5:
        return r0;
    }

    public boolean hasAnyLocalInfo() {
        return this.hasAnyLocalInfo;
    }

    public boolean hasAnyPositionInfo() {
        return this.hasAnyPositionInfo;
    }

    public void insert(int r2, DalvInsn r3) {
        this.insns.add(r2, r3);
        updateInfo(r3);
    }

    public void reverseBranch(int r2, CodeAddress r3) {
        int r0 = (this.insns.size() - r2) - 1;
        this.insns.set(r0, ((TargetInsn) this.insns.get(r0)).withNewTargetAndReversed(r3));
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("non-reversible instruction");
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("too few instructions");
    }
}
