package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class BaseMachine implements Machine {
    private int argCount;
    private TypeBearer[] args;
    private SwitchList auxCases;
    private Constant auxCst;
    private ArrayList<Constant> auxInitValues;
    private int auxInt;
    private int auxTarget;
    private Type auxType;
    private int localIndex;
    private boolean localInfo;
    private RegisterSpec localTarget;
    private final Prototype prototype;
    private int resultCount;
    private TypeBearer[] results;

    public BaseMachine(Prototype r1) {
        if (r1 == null) goto L6;
        this.prototype = r1;
        this.args = new TypeBearer[10];
        this.results = new TypeBearer[6];
        clearArgs();
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("prototype == null");
        throw null;
    }

    public static void throwLocalMismatch(TypeBearer r3, TypeBearer r4) {
        throw new SimException("local variable type mismatch: attempt to set or access a value of type " + r3.toHuman() + " using a local variable of type " + r4.toHuman() + ". This is symptomatic of .class transformation tools that ignore local variable information.");
    }

    public final void addResult(TypeBearer r3) {
        if (r3 == null) goto L5;
        TypeBearer[] r0 = this.results;
        int r1 = this.resultCount;
        r0[r1] = r3;
        this.resultCount = r1 + 1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("result == null");
    }

    public final TypeBearer arg(int r2) {
        if (r2 < this.argCount) goto L10;
        f$$ExternalSyntheticBUOutline1.m("n >= argCount");
        return null;
    L10:
        return this.args[r2];
    L6:
        f$$ExternalSyntheticBUOutline1.m("n < 0");
        return null;
    }

    public final int argCount() {
        return this.argCount;
    }

    public final int argWidth() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.argCount) goto L6;
        r1 = r1 + this.args[r0].getType().getCategory();
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void auxCstArg(Constant r1) {
        if (r1 == null) goto L5;
        this.auxCst = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
    }

    @Override // com.android.dx.cf.code.Machine
    public final void auxInitValues(ArrayList<Constant> r1) {
        this.auxInitValues = r1;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void auxIntArg(int r1) {
        this.auxInt = r1;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void auxSwitchArg(SwitchList r1) {
        if (r1 == null) goto L5;
        this.auxCases = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("cases == null");
    }

    @Override // com.android.dx.cf.code.Machine
    public final void auxTargetArg(int r1) {
        this.auxTarget = r1;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void auxType(Type r1) {
        this.auxType = r1;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void clearArgs() {
        this.argCount = 0;
        this.auxType = null;
        this.auxInt = 0;
        this.auxCst = null;
        this.auxTarget = 0;
        this.auxCases = null;
        this.auxInitValues = null;
        this.localIndex = -1;
        this.localInfo = false;
        this.localTarget = null;
        this.resultCount = -1;
    }

    public final void clearResult() {
        this.resultCount = 0;
    }

    public final SwitchList getAuxCases() {
        return this.auxCases;
    }

    public final Constant getAuxCst() {
        return this.auxCst;
    }

    public final int getAuxInt() {
        return this.auxInt;
    }

    public final int getAuxTarget() {
        return this.auxTarget;
    }

    public final Type getAuxType() {
        return this.auxType;
    }

    public final ArrayList<Constant> getInitValues() {
        return this.auxInitValues;
    }

    public final int getLocalIndex() {
        return this.localIndex;
    }

    public final boolean getLocalInfo() {
        return this.localInfo;
    }

    public final RegisterSpec getLocalTarget(boolean r5) {
        if (this.localTarget != null) goto L6;
        return null;
    L6:
        if (this.resultCount == 1) goto L13;
        StringBuilder r0 = new StringBuilder("local target with ");
        if (this.resultCount != 0) goto L10;
        String r4 = "no";
    L11:
        r0.append(r4);
        r0.append(" results");
        throw new SimException(r0.toString());
    L10:
        r4 = "multiple";
        goto L11
    L13:
        TypeBearer r02 = this.results[0];
        Type r2 = r02.getType();
        Type r3 = this.localTarget.getType();
        if (r2 != r3) goto L20;
        RegisterSpec r42 = this.localTarget;
        if (r5 == true) goto L18;
        return r42;
    L18:
        return r42.withType(r02);
    L20:
        if (Merger.isPossiblyAssignableFrom(r3, r2) == true) goto L24;
        throwLocalMismatch(r2, r3);
        return null;
    L24:
        if (r3 != Type.OBJECT) goto L27;
        this.localTarget = this.localTarget.withType(r02);
    L27:
        return this.localTarget;
    }

    @Override // com.android.dx.cf.code.Machine
    public Prototype getPrototype() {
        return this.prototype;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void localArg(Frame r3, int r4) {
        clearArgs();
        this.args[0] = r3.getLocals().get(r4);
        this.argCount = 1;
        this.localIndex = r4;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void localInfo(boolean r1) {
        this.localInfo = r1;
    }

    @Override // com.android.dx.cf.code.Machine
    public final void localTarget(int r1, Type r2, LocalItem r3) {
        this.localTarget = RegisterSpec.makeLocalOptional(r1, r2, r3);
    }

    @Override // com.android.dx.cf.code.Machine
    public final void popArgs(Frame r4, int r5) {
        ExecutionStack r42 = r4.getStack();
        clearArgs();
        if (r5 <= this.args.length) goto L5;
        this.args = new TypeBearer[r5 + 10];
    L5:
        int r0 = r5 - 1;
    L6:
        if (r0 < 0) goto L8;
        this.args[r0] = r42.pop();
        r0 = r0 - 1;
        goto L6
    L8:
        this.argCount = r5;
    }

    @Override // com.android.dx.cf.code.Machine
    public void popArgs(Frame r4, Prototype r5) {
        StdTypeList r52 = r5.getParameterTypes();
        int r0 = r52.size();
        popArgs(r4, r0);
        int r42 = 0;
    L3:
        if (r42 >= r0) goto L9;
        if (Merger.isPossiblyAssignableFrom(r52.getType(r42), this.args[r42]) == false) goto L8;
        r42 = r42 + 1;
        goto L3
    L8:
        throw new SimException("at stack depth " + ((r0 - 1) - r42) + ", expected type " + r52.getType(r42).toHuman() + " but found " + this.args[r42].getType().toHuman());
    }

    @Override // com.android.dx.cf.code.Machine
    public final void popArgs(Frame r2, Type r3) {
        popArgs(r2, 1);
        if (Merger.isPossiblyAssignableFrom(r3, this.args[0]) == false) goto L5;
        return;
    L5:
        BaseMachine$$ExternalSyntheticBUOutline0.m(r3.toHuman(), this.args[0].getType().toHuman());
    }

    @Override // com.android.dx.cf.code.Machine
    public final void popArgs(Frame r2, Type r3, Type r4) {
        popArgs(r2, 2);
        if (Merger.isPossiblyAssignableFrom(r3, this.args[0]) == true) goto L5;
        BaseMachine$$ExternalSyntheticBUOutline0.m(r3.toHuman(), this.args[0].getType().toHuman());
        return;
    L5:
        if (Merger.isPossiblyAssignableFrom(r4, this.args[1]) == false) goto L7;
        return;
    L7:
        BaseMachine$$ExternalSyntheticBUOutline0.m(r4.toHuman(), this.args[1].getType().toHuman());
    }

    @Override // com.android.dx.cf.code.Machine
    public final void popArgs(Frame r2, Type r3, Type r4, Type r5) {
        popArgs(r2, 3);
        if (Merger.isPossiblyAssignableFrom(r3, this.args[0]) == true) goto L5;
        BaseMachine$$ExternalSyntheticBUOutline0.m(r3.toHuman(), this.args[0].getType().toHuman());
        return;
    L5:
        if (Merger.isPossiblyAssignableFrom(r4, this.args[1]) == true) goto L7;
        BaseMachine$$ExternalSyntheticBUOutline0.m(r4.toHuman(), this.args[1].getType().toHuman());
        return;
    L7:
        if (Merger.isPossiblyAssignableFrom(r5, this.args[2]) == false) goto L9;
        return;
    L9:
        BaseMachine$$ExternalSyntheticBUOutline0.m(r5.toHuman(), this.args[2].getType().toHuman());
    }

    public final TypeBearer result(int r2) {
        if (r2 < this.resultCount) goto L10;
        f$$ExternalSyntheticBUOutline1.m("n >= resultCount");
        return null;
    L10:
        return this.results[r2];
    L6:
        f$$ExternalSyntheticBUOutline1.m("n < 0");
        return null;
    }

    public final int resultCount() {
        int r1 = this.resultCount;
        if (r1 < 0) goto L6;
        return r1;
    L6:
        throw new SimException("results never set");
    }

    public final int resultWidth() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.resultCount) goto L6;
        r1 = r1 + this.results[r0].getType().getCategory();
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    public final void setResult(TypeBearer r3) {
        if (r3 == null) goto L5;
        this.results[0] = r3;
        this.resultCount = 1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("result == null");
    }

    public final void storeResults(Frame r3) {
        int r0 = this.resultCount;
        if (r0 < 0) goto L19;
        if (r0 == 0) goto L17;
        int r1 = 0;
        if (this.localTarget == null) goto L10;
        r3.getLocals().set(getLocalTarget(false));
        return;
    L10:
        ExecutionStack r32 = r3.getStack();
    L12:
        if (r1 >= this.resultCount) goto L23;
        if (this.localInfo == false) goto L16;
        r32.setLocal();
    L16:
        r32.push(this.results[r1]);
        r1 = r1 + 1;
        goto L12
    L23:
        return;
    L17:
        return;
    L19:
        throw new SimException("results never set");
    }
}
