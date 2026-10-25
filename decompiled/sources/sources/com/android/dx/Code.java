package com.android.dx;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.DexMaker;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.code.ThrowingInsn;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class Code {
    private final List<Label> catchLabels;
    private final List<TypeId<?>> catchTypes;
    private StdTypeList catches;
    private Label currentLabel;
    private final List<Label> labels;
    private final List<Local<?>> locals;
    private boolean localsInitialized;
    private final MethodId<?, ?> method;
    private final List<Local<?>> parameters;
    private SourcePosition sourcePosition;
    private final Local<?> thisLocal;

    public Code(DexMaker.MethodDeclaration r5) {
        this.labels = new ArrayList();
        ArrayList r0 = new ArrayList();
        this.parameters = r0;
        this.locals = new ArrayList();
        this.sourcePosition = SourcePosition.NO_INFO;
        this.catchTypes = new ArrayList();
        this.catchLabels = new ArrayList();
        this.catches = StdTypeList.EMPTY;
        MethodId<?, ?> r1 = r5.method;
        this.method = r1;
        if (r5.isStatic() == false) goto L5;
        this.thisLocal = null;
    L6:
        TypeId<?>[] r52 = r1.parameters.types;
        int r02 = r52.length;
        int r12 = 0;
    L7:
        if (r12 >= r02) goto L9;
        TypeId<?> r2 = r52[r12];
        this.parameters.add(Local.get(this, r2));
        r12 = r12 + 1;
        goto L7
    L9:
        Label r53 = new Label();
        this.currentLabel = r53;
        adopt(r53);
        this.currentLabel.marked = true;
        return;
    L5:
        Local<?> r54 = Local.get(this, r1.declaringType);
        this.thisLocal = r54;
        r0.add(r54);
        goto L6
    }

    private void addInstruction(Insn r2) {
        addInstruction(r2, null);
    }

    private void addInstruction(Insn r5, Label r6) {
        Label r0 = this.currentLabel;
        if (r0 != null) goto L5;
    L42:
        Segment$$ExternalSyntheticBUOutline1.m("no current label");
        return;
    L5:
        if (r0.marked == false) goto L42;
        r0.instructions.add(r5);
        int r52 = r5.getOpcode().getBranchingness();
        if (r52 != 1) goto L9;
        if (r6 != null) goto L40;
        return;
    L40:
        Native$$ExternalSyntheticBUOutline5.m("unexpected branch: ", r6);
        return;
    L9:
        if (r52 != 2) goto L11;
        if (r6 != null) goto L36;
        this.currentLabel = null;
        return;
    L36:
        Native$$ExternalSyntheticBUOutline5.m("unexpected branch: ", r6);
        return;
    L11:
        if (r52 != 3) goto L13;
        if (r6 == null) goto L31;
        this.currentLabel.primarySuccessor = r6;
        this.currentLabel = null;
        return;
    L31:
        f$$ExternalSyntheticBUOutline1.m("branch == null");
        return;
    L13:
        if (r52 != 4) goto L15;
        if (r6 == null) goto L26;
        splitCurrentLabel(r6, Collections.EMPTY_LIST);
        return;
    L26:
        f$$ExternalSyntheticBUOutline1.m("branch == null");
        return;
    L15:
        if (r52 != 6) goto L21;
        if (r6 != null) goto L19;
        splitCurrentLabel(null, new ArrayList(this.catchLabels));
        return;
    L19:
        Native$$ExternalSyntheticBUOutline5.m("unexpected branch: ", r6);
        return;
    L21:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    private void adopt(Label r2) {
        Code r0 = r2.code;
        if (r0 != this) goto L5;
        return;
    L5:
        if (r0 != null) goto L8;
        r2.code = this;
        this.labels.add(r2);
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Cannot adopt label; it belongs to another Code");
    }

    private void cleanUpLabels() {
        Iterator<Label> r3 = this.labels.iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L9;
        Label r1 = r3.next();
        if (r1.isEmpty() == true) goto L7;
        r1.compact();
        r1.id = r0;
        r0 = r0 + 1;
        goto L4
    L7:
        r3.remove();
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> Local<T> coerce(Local<?> r2, TypeId<T> r3) {
        if (r2.type.equals(r3) == false) goto L5;
        return r2;
    L5:
        StringBuilder r1 = new StringBuilder("requested ");
        r1.append(r3);
        Utils$$ExternalSyntheticBUOutline1.m(r1, " but was ", r2.type);
        return null;
    }

    private static RegisterSpecList concatenate(Local<?> r4, Local<?>[] r5) {
        int r0 = 0;
        if (r4 == null) goto L5;
        int r1 = 1;
    L6:
        RegisterSpecList r2 = new RegisterSpecList(r5.length + r1);
        if (r4 == null) goto L10;
        r2.set(0, r4.spec());
    L10:
        if (r0 >= r5.length) goto L12;
        r2.set(r0 + r1, r5[r0].spec());
        r0 = r0 + 1;
        goto L10
    L12:
        return r2;
    L5:
        r1 = 0;
        goto L6
    }

    private Rop getCastRop(Type r2, Type r3) {
        if (r2.getBasicType() != 6) goto L18;
        int r1 = r3.getBasicType();
        if (r1 == 2) goto L16;
        if (r1 == 3) goto L14;
        if (r1 != 8) goto L18;
        return Rops.TO_SHORT;
    L14:
        return Rops.TO_CHAR;
    L16:
        return Rops.TO_BYTE;
    L18:
        return Rops.opConv(r3, r2);
    }

    private <D, R> void invoke(Rop r7, MethodId<D, R> r8, Local<? super R> r9, Local<? extends D> r10, Local<?>... r11) {
        addInstruction(new ThrowingCstInsn(r7, this.sourcePosition, concatenate(r10, r11), this.catches, r8.constant));
        if (r9 == null) goto L6;
        moveResult(r9, false);
        return;
    }

    private void loadConstantInternal(Local r9, Object r10) {
        if (r10 != null) goto L5;
        Rop r0 = Rops.CONST_OBJECT_NOTHROW;
    L4:
        Rop r2 = r0;
        int r02 = r2.getBranchingness();
        SourcePosition r3 = this.sourcePosition;
        if (r02 != 1) goto L10;
        addInstruction(new PlainCstInsn(r2, r3, r9.spec(), RegisterSpecList.EMPTY, Constants.getConstant(r10)));
        return;
    L10:
        addInstruction(new ThrowingCstInsn(r2, r3, RegisterSpecList.EMPTY, this.catches, Constants.getConstant(r10)));
        moveResult(r9, true);
        return;
    L5:
        r0 = Rops.opConst(r9.type.ropType);
        goto L4
    }

    private void moveResult(Local<?> r4, boolean r5) {
        if (r5 == false) goto L4;
        Rop r52 = Rops.opMoveResultPseudo(r4.type.ropType);
    L5:
        addInstruction(new PlainInsn(r52, this.sourcePosition, r4.spec(), RegisterSpecList.EMPTY));
        return;
    L4:
        r52 = Rops.opMoveResult(r4.type.ropType);
        goto L5
    }

    private void splitCurrentLabel(Label r3, List<Label> r4) {
        Label r0 = new Label();
        adopt(r0);
        Label r1 = this.currentLabel;
        r1.primarySuccessor = r0;
        r1.alternateSuccessor = r3;
        r1.catchLabels = r4;
        this.currentLabel = r0;
        r0.marked = true;
    }

    private StdTypeList toTypeList(List<TypeId<?>> r3) {
        StdTypeList r2 = new StdTypeList(r3.size());
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L6;
        r2.set(r0, r3.get(r0).ropType);
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }

    public void addCatchClause(TypeId<? extends Throwable> r2, Label r3) {
        if (this.catchTypes.contains(r2) == true) goto L6;
        adopt(r3);
        this.catchTypes.add(r2);
        this.catches = toTypeList(this.catchTypes);
        this.catchLabels.add(r3);
        return;
    L6:
        Native$$ExternalSyntheticBUOutline5.m("Already caught: ", r2);
    }

    public void aget(Local<?> r4, Local<?> r5, Local<Integer> r6) {
        addInstruction(new ThrowingInsn(Rops.opAget(r4.type.ropType), this.sourcePosition, RegisterSpecList.make(r5.spec(), r6.spec()), this.catches));
        moveResult(r4, true);
    }

    public void aput(Local<?> r4, Local<Integer> r5, Local<?> r6) {
        addInstruction(new ThrowingInsn(Rops.opAput(r6.type.ropType), this.sourcePosition, RegisterSpecList.make(r6.spec(), r4.spec(), r5.spec()), this.catches));
    }

    public <T> void arrayLength(Local<Integer> r5, Local<T> r6) {
        addInstruction(new ThrowingInsn(Rops.ARRAY_LENGTH, this.sourcePosition, RegisterSpecList.make(r6.spec()), this.catches));
        moveResult(r5, true);
    }

    public void cast(Local<?> r8, Local<?> r9) {
        if (r9.getType().ropType.isReference() == false) goto L6;
        addInstruction(new ThrowingCstInsn(Rops.CHECK_CAST, this.sourcePosition, RegisterSpecList.make(r9.spec()), this.catches, r8.type.constant));
        moveResult(r8, true);
        return;
    L6:
        addInstruction(new PlainInsn(getCastRop(r9.type.ropType, r8.type.ropType), this.sourcePosition, r8.spec(), r9.spec()));
    }

    public <T> void compare(Comparison r3, Label r4, Local<T> r5, Local<T> r6) {
        adopt(r4);
        addInstruction(new PlainInsn(r3.rop(StdTypeList.make(r5.type.ropType, r6.type.ropType)), this.sourcePosition, null, RegisterSpecList.make(r5.spec(), r6.spec())), r4);
    }

    public <T extends Number> void compareFloatingPoint(Local<Integer> r3, Local<T> r4, Local<T> r5, int r6) {
        if (r6 != 1) goto L6;
        Rop r62 = Rops.opCmpg(r4.type.ropType);
    L8:
        addInstruction(new PlainInsn(r62, this.sourcePosition, r3.spec(), RegisterSpecList.make(r4.spec(), r5.spec())));
        return;
    L6:
        if (r6 != (-1)) goto L10;
        r62 = Rops.opCmpl(r4.type.ropType);
        goto L8
    L10:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("expected 1 or -1 but was ", r6);
    }

    public void compareLongs(Local<Integer> r4, Local<Long> r5, Local<Long> r6) {
        addInstruction(new PlainInsn(Rops.CMPL_LONG, this.sourcePosition, r4.spec(), RegisterSpecList.make(r5.spec(), r6.spec())));
    }

    public <T> void compareZ(Comparison r4, Label r5, Local<?> r6) {
        adopt(r5);
        addInstruction(new PlainInsn(r4.rop(StdTypeList.make(r6.type.ropType)), this.sourcePosition, null, RegisterSpecList.make(r6.spec())), r5);
    }

    public <T> Local<T> getParameter(int r2, TypeId<T> r3) {
        if (this.thisLocal == null) goto L6;
        r2 = r2 + 1;
    L6:
        return coerce(this.parameters.get(r2), r3);
    }

    public <T> Local<T> getThis(TypeId<T> r2) {
        Local<?> r0 = this.thisLocal;
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("static methods cannot access 'this'");
        return null;
    L5:
        return coerce(r0, r2);
    }

    public <D, V> void iget(FieldId<D, ? extends V> r7, Local<V> r8, Local<D> r9) {
        addInstruction(new ThrowingCstInsn(Rops.opGetField(r8.type.ropType), this.sourcePosition, RegisterSpecList.make(r9.spec()), this.catches, r7.constant));
        moveResult(r8, true);
    }

    public void initializeLocals() {
        if (this.localsInitialized == true) goto L14;
        this.localsInitialized = true;
        Iterator<Local<?>> r0 = this.locals.iterator();
        int r2 = 0;
    L6:
        if (r0.hasNext() == false) goto L8;
        r2 = r2 + r0.next().initialize(r2);
        goto L6
    L8:
        ArrayList r02 = new ArrayList();
        Iterator<Local<?>> r3 = this.parameters.iterator();
        int r4 = r2;
    L10:
        if (r3.hasNext() == false) goto L12;
        Local<?> r5 = r3.next();
        CstInteger r12 = CstInteger.make(r4 - r2);
        r4 = r4 + r5.initialize(r4);
        r02.add(new PlainCstInsn(Rops.opMoveParam(r5.type.ropType), this.sourcePosition, r5.spec(), RegisterSpecList.EMPTY, r12));
        goto L10
    L12:
        this.labels.get(0).instructions.addAll(0, r02);
        return;
    L14:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
    }

    public void instanceOfType(Local<?> r7, Local<?> r8, TypeId<?> r9) {
        addInstruction(new ThrowingCstInsn(Rops.INSTANCE_OF, this.sourcePosition, RegisterSpecList.make(r8.spec()), this.catches, r9.constant));
        moveResult(r7, true);
    }

    public <D, R> void invokeDirect(MethodId<D, R> r8, Local<? super R> r9, Local<? extends D> r10, Local<?>... r11) {
        invoke(Rops.opInvokeDirect(r8.prototype(true)), r8, r9, r10, r11);
    }

    public <D, R> void invokeInterface(MethodId<D, R> r8, Local<? super R> r9, Local<? extends D> r10, Local<?>... r11) {
        invoke(Rops.opInvokeInterface(r8.prototype(true)), r8, r9, r10, r11);
    }

    public <R> void invokeStatic(MethodId<?, R> r8, Local<? super R> r9, Local<?>... r10) {
        invoke(Rops.opInvokeStatic(r8.prototype(true)), r8, r9, null, r10);
    }

    public <D, R> void invokeSuper(MethodId<D, R> r8, Local<? super R> r9, Local<? extends D> r10, Local<?>... r11) {
        invoke(Rops.opInvokeSuper(r8.prototype(true)), r8, r9, r10, r11);
    }

    public <D, R> void invokeVirtual(MethodId<D, R> r8, Local<? super R> r9, Local<? extends D> r10, Local<?>... r11) {
        invoke(Rops.opInvokeVirtual(r8.prototype(true)), r8, r9, r10, r11);
    }

    public <D, V> void iput(FieldId<D, V> r7, Local<? extends D> r8, Local<? extends V> r9) {
        addInstruction(new ThrowingCstInsn(Rops.opPutField(r9.type.ropType), this.sourcePosition, RegisterSpecList.make(r9.spec(), r8.spec()), this.catches, r7.constant));
    }

    public void jump(Label r6) {
        adopt(r6);
        addInstruction(new PlainInsn(Rops.GOTO, this.sourcePosition, null, RegisterSpecList.EMPTY), r6);
    }

    public <T> void loadConstant(Local<T> r1, T r2) {
        loadConstantInternal(r1, r2);
    }

    public void loadDeferredClassConstant(Local<Class> r1, TypeId r2) {
        loadConstantInternal(r1, r2);
    }

    public void mark(Label r2) {
        adopt(r2);
        if (r2.marked == true) goto L9;
        r2.marked = true;
        if (this.currentLabel == null) goto L7;
        jump(r2);
    L7:
        this.currentLabel = r2;
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("already marked");
    }

    public void monitorEnter(Local<?> r5) {
        addInstruction(new ThrowingInsn(Rops.MONITOR_ENTER, this.sourcePosition, RegisterSpecList.make(r5.spec()), this.catches));
    }

    public void monitorExit(Local<?> r5) {
        addInstruction(new ThrowingInsn(Rops.MONITOR_EXIT, this.sourcePosition, RegisterSpecList.make(r5.spec()), this.catches));
    }

    public <T> void move(Local<T> r4, Local<T> r5) {
        addInstruction(new PlainInsn(Rops.opMove(r5.type.ropType), this.sourcePosition, r4.spec(), r5.spec()));
    }

    public <T> void newArray(Local<T> r7, Local<Integer> r8) {
        addInstruction(new ThrowingCstInsn(Rops.opNewArray(r7.type.ropType), this.sourcePosition, RegisterSpecList.make(r8.spec()), this.catches, r7.type.constant));
        moveResult(r7, true);
    }

    public <T> void newInstance(Local<T> r7, MethodId<T, Void> r8, Local<?>... r9) {
        if (r7 == null) goto L5;
        addInstruction(new ThrowingCstInsn(Rops.NEW_INSTANCE, this.sourcePosition, RegisterSpecList.EMPTY, this.catches, r8.declaringType.constant));
        moveResult(r7, true);
        invokeDirect(r8, null, r7, r9);
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public <T> Local<T> newLocal(TypeId<T> r2) {
        if (this.localsInitialized == true) goto L6;
        Local<T> r22 = Local.get(this, r2);
        this.locals.add(r22);
        return r22;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot allocate locals after adding instructions");
        return null;
    }

    public <T1, T2> void op(BinaryOp r4, Local<T1> r5, Local<T1> r6, Local<T2> r7) {
        Rop r42 = r4.rop(StdTypeList.make(r6.type.ropType, r7.type.ropType));
        RegisterSpecList r62 = RegisterSpecList.make(r6.spec(), r7.spec());
        int r72 = r42.getBranchingness();
        SourcePosition r0 = this.sourcePosition;
        if (r72 != 1) goto L6;
        addInstruction(new PlainInsn(r42, r0, r5.spec(), r62));
        return;
    L6:
        addInstruction(new ThrowingInsn(r42, r0, r62, this.catches));
        moveResult(r5, true);
    }

    public <T> void op(UnaryOp r3, Local<T> r4, Local<T> r5) {
        addInstruction(new PlainInsn(r3.rop(r5.type), this.sourcePosition, r4.spec(), r5.spec()));
    }

    public int paramSize() {
        Iterator<Local<?>> r2 = this.parameters.iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r0 = r0 + r2.next().size();
        goto L4
    L6:
        return r0;
    }

    public Label removeCatchClause(TypeId<? extends Throwable> r3) {
        int r0 = this.catchTypes.indexOf(r3);
        if (r0 == (-1)) goto L6;
        this.catchTypes.remove(r0);
        this.catches = toTypeList(this.catchTypes);
        return this.catchLabels.remove(r0);
    L6:
        Native$$ExternalSyntheticBUOutline5.m("No catch clause: ", r3);
        return null;
    }

    public void returnValue(Local<?> r5) {
        if (r5.type.equals(this.method.returnType) == false) goto L6;
        addInstruction(new PlainInsn(Rops.opReturn(r5.type.ropType), this.sourcePosition, null, RegisterSpecList.make(r5.spec())));
        return;
    L6:
        StringBuilder r0 = new StringBuilder("declared ");
        r0.append(this.method.returnType);
        Utils$$ExternalSyntheticBUOutline1.m(r0, " but returned ", r5.type);
    }

    public void returnVoid() {
        if (this.method.returnType.equals(TypeId.VOID) == false) goto L6;
        addInstruction(new PlainInsn(Rops.RETURN_VOID, this.sourcePosition, null, RegisterSpecList.EMPTY));
        return;
    L6:
        Native$$ExternalSyntheticBUOutline2.m("declared ", this.method.returnType, " but returned void");
    }

    public <V> void sget(FieldId<?, ? extends V> r7, Local<V> r8) {
        addInstruction(new ThrowingCstInsn(Rops.opGetStatic(r8.type.ropType), this.sourcePosition, RegisterSpecList.EMPTY, this.catches, r7.constant));
        moveResult(r8, true);
    }

    public <V> void sput(FieldId<?, V> r7, Local<? extends V> r8) {
        addInstruction(new ThrowingCstInsn(Rops.opPutStatic(r8.type.ropType), this.sourcePosition, RegisterSpecList.make(r8.spec()), this.catches, r7.constant));
    }

    public void throwValue(Local<? extends Throwable> r5) {
        addInstruction(new ThrowingInsn(Rops.THROW, this.sourcePosition, RegisterSpecList.make(r5.spec()), this.catches));
    }

    public BasicBlockList toBasicBlocks() {
        if (this.localsInitialized == true) goto L5;
        initializeLocals();
    L5:
        cleanUpLabels();
        BasicBlockList r0 = new BasicBlockList(this.labels.size());
        int r1 = 0;
    L7:
        if (r1 >= this.labels.size()) goto L9;
        r0.set(r1, this.labels.get(r1).toBasicBlock());
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }
}
