package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class OneLocalsArray extends LocalsArray {
    private final TypeBearer[] locals;

    public OneLocalsArray(int r2) {
        if (r2 == 0) goto L4;
        boolean r0 = true;
    L5:
        super(r0);
        this.locals = new TypeBearer[r2];
        return;
    L4:
        r0 = false;
        goto L5
    }

    private static TypeBearer throwSimException(int r3, String r4) {
        throw new SimException("local " + Hex.u2(r3) + ": " + r4);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void annotate(ExceptionWithContext r5) {
        int r0 = 0;
    L3:
        TypeBearer[] r1 = this.locals;
        if (r0 >= r1.length) goto L10;
        TypeBearer r12 = r1[r0];
        if (r12 != null) goto L8;
        String r13 = "<invalid>";
    L9:
        r5.addContext("locals[" + Hex.u2(r0) + "]: " + r13);
        r0 = r0 + 1;
        goto L3
    L8:
        r13 = r12.toString();
        goto L9
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public /* bridge */ /* synthetic */ LocalsArray copy() {
        return copy();
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public OneLocalsArray copy() {
        OneLocalsArray r0 = new OneLocalsArray(this.locals.length);
        TypeBearer[] r4 = this.locals;
        System.arraycopy(r4, 0, r0.locals, 0, r4.length);
        return r0;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer get(int r1) {
        TypeBearer r0 = this.locals[r1];
        if (r0 == null) goto L5;
        return r0;
    L5:
        return throwSimException(r1, "invalid");
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer getCategory1(int r3) {
        TypeBearer r2 = get(r3);
        Type r0 = r2.getType();
        if (r0.isUninitialized() == false) goto L7;
        return throwSimException(r3, "uninitialized instance");
    L7:
        if (r0.isCategory2() == true) goto L9;
        return r2;
    L9:
        return throwSimException(r3, "category-2");
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer getCategory2(int r2) {
        TypeBearer r1 = get(r2);
        if (r1.getType().isCategory1() == true) goto L5;
        return r1;
    L5:
        return throwSimException(r2, "category-1");
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public int getMaxLocals() {
        return this.locals.length;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer getOrNull(int r1) {
        return this.locals[r1];
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public OneLocalsArray getPrimary() {
        return this;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void invalidate(int r2) {
        throwIfImmutable();
        this.locals[r2] = null;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void makeInitialized(Type r6) {
        int r0 = this.locals.length;
        if (r0 == 0) goto L11;
        throwIfImmutable();
        Type r1 = r6.getInitializedType();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L15;
        TypeBearer[] r3 = this.locals;
        if (r3[r2] != r6) goto L10;
        r3[r2] = r1;
    L10:
        r2 = r2 + 1;
        goto L6
    L15:
        return;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public LocalsArray merge(LocalsArray r2) {
        if ((r2 instanceof OneLocalsArray) == false) goto L7;
        return merge((OneLocalsArray) r2);
    L7:
        return r2.merge(this);
    }

    public OneLocalsArray merge(OneLocalsArray r3) {
        return Merger.mergeLocals(this, r3);
    L4:
        e = move-exception;
        e.addContext("underlay locals:");
        this.annotate(e);
        e.addContext("overlay locals:");
        r3.annotate(e);
        throw e;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public LocalsArraySet mergeWithSubroutineCaller(LocalsArray r2, int r3) {
        return new LocalsArraySet(getMaxLocals()).mergeWithSubroutineCaller(r2, r3);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void set(int r4, TypeBearer r5) {
        throwIfImmutable();
        TypeBearer r52 = r5.getFrameType();     // Catch: NullPointerException -> L18
        if (r4 >= 0) goto L6;
        IntList$$ExternalSyntheticBUOutline0.m("idx < 0");
        return;
    L6:
        if (r52.getType().isCategory2() == false) goto L8;
        this.locals[r4 + 1] = null;
    L8:
        TypeBearer[] r0 = this.locals;
        r0[r4] = r52;
        if (r4 == 0) goto L22;
        int r42 = r4 - 1;
        TypeBearer r53 = r0[r42];
        if (r53 != null) goto L13;
        return;
    L13:
        if (r53.getType().isCategory2() == false) goto L24;
        this.locals[r42] = null;
        return;
    L24:
        return;
    L22:
        return;
    L18:
        f$$ExternalSyntheticBUOutline2.m("type == null");
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void set(RegisterSpec r2) {
        set(r2.getReg(), r2);
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L3:
        TypeBearer[] r2 = this.locals;
        if (r1 >= r2.length) goto L11;
        TypeBearer r22 = r2[r1];
        if (r22 != null) goto L8;
        String r23 = "<invalid>";
    L9:
        r0.append("locals[" + Hex.u2(r1) + "]: " + r23 + "\n");
        r1 = r1 + 1;
        goto L3
    L8:
        r23 = r22.toString();
        goto L9
    L11:
        return r0.toString();
    }
}
