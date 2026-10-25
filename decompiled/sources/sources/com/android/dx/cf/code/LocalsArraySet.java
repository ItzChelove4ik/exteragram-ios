package com.android.dx.cf.code;

import com.android.dex.util.ExceptionWithContext;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.Hex;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LocalsArraySet extends LocalsArray {
    private final OneLocalsArray primary;
    private final ArrayList<LocalsArray> secondaries;

    public LocalsArraySet(int r2) {
        if (r2 == 0) goto L4;
        boolean r0 = true;
    L5:
        super(r0);
        this.primary = new OneLocalsArray(r2);
        this.secondaries = new ArrayList();
        return;
    L4:
        r0 = false;
        goto L5
    }

    private LocalsArraySet(LocalsArraySet r5) {
        int r1 = 0;
        if (r5.getMaxLocals() <= 0) goto L5;
        boolean r0 = true;
    L6:
        super(r0);
        this.primary = r5.primary.copy();
        this.secondaries = new ArrayList(r5.secondaries.size());
        int r02 = r5.secondaries.size();
    L7:
        if (r1 >= r02) goto L13;
        LocalsArray r2 = r5.secondaries.get(r1);
        ArrayList<LocalsArray> r3 = this.secondaries;
        if (r2 != null) goto L11;
        r3.add(null);
    L12:
        r1 = r1 + 1;
        goto L7
    L11:
        r3.add(r2.copy());
        goto L12
    L13:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public LocalsArraySet(OneLocalsArray r2, ArrayList<LocalsArray> r3) {
        if (r2.getMaxLocals() <= 0) goto L5;
        boolean r0 = true;
    L6:
        super(r0);
        this.primary = r2;
        this.secondaries = r3;
        return;
    L5:
        r0 = false;
        goto L6
    }

    private LocalsArray getSecondaryForLabel(int r2) {
        if (r2 < this.secondaries.size()) goto L7;
        return null;
    L7:
        return this.secondaries.get(r2);
    }

    private LocalsArraySet mergeWithOne(OneLocalsArray r11) {
        OneLocalsArray r0 = this.primary.merge(r11.getPrimary());
        ArrayList r1 = new ArrayList(this.secondaries.size());
        int r2 = this.secondaries.size();
        int r4 = 0;
        boolean r5 = false;
    L3:
        if (r4 >= r2) goto L18;
        LocalsArray r6 = this.secondaries.get(r4);
        if (r6 != null) goto L23;
    L10:
        LocalsArray r7 = null;
    L11:
        if (r5 == true) goto L15;
        if (r6 != r7) goto L15;
        r5 = false;
    L16:
        r1.add(r7);
        r4 = r4 + 1;
    L15:
        r5 = true;
        goto L16
    L23:
        r7 = r6.merge(r11);     // Catch: SimException -> L8
    L8:
        e = move-exception;
        e.addContext("Merging one locals against caller block " + Hex.u2(r4));
        goto L10
    L18:
        if (this.primary != r0) goto L22;
        if (r5 == true) goto L22;
        return this;
    L22:
        return new LocalsArraySet(r0, r1);
    }

    private LocalsArraySet mergeWithSet(LocalsArraySet r14) {
        OneLocalsArray r0 = this.primary.merge(r14.getPrimary());
        int r1 = this.secondaries.size();
        int r2 = r14.secondaries.size();
        int r3 = Math.max(r1, r2);
        ArrayList r4 = new ArrayList(r3);
        int r6 = 0;
        boolean r7 = false;
    L3:
        if (r6 >= r3) goto L28;
        LocalsArray r8 = null;
        if (r6 >= r1) goto L7;
        LocalsArray r9 = this.secondaries.get(r6);
    L8:
        if (r6 >= r2) goto L10;
        LocalsArray r10 = r14.secondaries.get(r6);
    L11:
        if (r9 == r10) goto L16;
        if (r9 != null) goto L15;
        r8 = r10;
    L21:
        if (r7 == true) goto L25;
        if (r9 != r8) goto L25;
        r7 = false;
    L26:
        r4.add(r8);
        r6 = r6 + 1;
    L25:
        r7 = true;
        goto L26
    L15:
        if (r10 == null) goto L16;
        r8 = r9.merge(r10);     // Catch: SimException -> L19
    L19:
        e = move-exception;
        e.addContext("Merging locals set for caller block " + Hex.u2(r6));
    L16:
        r8 = r9;
        goto L21
    L10:
        r10 = null;
        goto L11
    L7:
        r9 = null;
        goto L8
    L28:
        if (this.primary != r0) goto L32;
        if (r7 == true) goto L32;
        return this;
    L32:
        return new LocalsArraySet(r0, r4);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void annotate(ExceptionWithContext r6) {
        r6.addContext("(locals array set; primary)");
        this.primary.annotate(r6);
        int r0 = this.secondaries.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        LocalsArray r2 = this.secondaries.get(r1);
        if (r2 == null) goto L7;
        r6.addContext("(locals array set: primary for caller " + Hex.u2(r1) + ')');
        r2.getPrimary().annotate(r6);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public LocalsArray copy() {
        return new LocalsArraySet(this);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer get(int r1) {
        return this.primary.get(r1);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer getCategory1(int r1) {
        return this.primary.getCategory1(r1);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer getCategory2(int r1) {
        return this.primary.getCategory2(r1);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public int getMaxLocals() {
        return this.primary.getMaxLocals();
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public TypeBearer getOrNull(int r1) {
        return this.primary.getOrNull(r1);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public OneLocalsArray getPrimary() {
        return this.primary;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void invalidate(int r4) {
        throwIfImmutable();
        this.primary.invalidate(r4);
        ArrayList<LocalsArray> r3 = this.secondaries;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        LocalsArray r2 = r3.get(r1);
        r1 = r1 + 1;
        LocalsArray r22 = r2;
        if (r22 == null) goto L3;
        r22.invalidate(r4);
        goto L3
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void makeInitialized(Type r4) {
        if (this.primary.getMaxLocals() == 0) goto L10;
        throwIfImmutable();
        this.primary.makeInitialized(r4);
        ArrayList<LocalsArray> r3 = this.secondaries;
        int r0 = r3.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L16;
        LocalsArray r2 = r3.get(r1);
        r1 = r1 + 1;
        LocalsArray r22 = r2;
        if (r22 == null) goto L6;
        r22.makeInitialized(r4);
        goto L6
    L16:
        return;
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public /* bridge */ /* synthetic */ LocalsArray merge(LocalsArray r1) {
        return merge(r1);
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public LocalsArraySet merge(LocalsArray r3) {
    L5:
        e = move-exception;
        e.addContext("underlay locals:");
        annotate(e);
        e.addContext("overlay locals:");
        r3.annotate(e);
        throw e;
    L3:
        if ((r3 instanceof LocalsArraySet) == false) goto L7;
        LocalsArraySet r2 = mergeWithSet((LocalsArraySet) r3);     // Catch: SimException -> L5
    L8:
        r2.setImmutable();
        return r2;
    L7:
        r2 = mergeWithOne((OneLocalsArray) r3);     // Catch: SimException -> L5
        goto L8
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public LocalsArraySet mergeWithSubroutineCaller(LocalsArray r9, int r10) {
        LocalsArray r0 = getSecondaryForLabel(r10);
        OneLocalsArray r1 = this.primary.merge(r9.getPrimary());
        if (r0 != r9) goto L5;
        r9 = r0;
    L8:
        if (r9 == r0) goto L10;
    L12:
        int r02 = this.secondaries.size();
        int r12 = Math.max(r10 + 1, r02);
        ArrayList r2 = new ArrayList(r12);
        int r4 = 0;
        OneLocalsArray r5 = null;
    L13:
        if (r4 >= r12) goto L24;
        if (r4 != r10) goto L16;
        LocalsArray r6 = r9;
    L19:
        if (r6 == null) goto L23;
        if (r5 != null) goto L22;
        r5 = r6.getPrimary();
        goto L23
    L22:
        r5 = r5.merge(r6.getPrimary());
    L23:
        r2.add(r6);
        r4 = r4 + 1;
        goto L13
    L16:
        if (r4 >= r02) goto L18;
        r6 = this.secondaries.get(r4);
        goto L19
    L18:
        r6 = null;
        goto L19
    L24:
        LocalsArraySet r8 = new LocalsArraySet(r5, r2);
        r8.setImmutable();
        return r8;
    L10:
        if (r1 != this.primary) goto L12;
        return this;
    L5:
        if (r0 == null) goto L8;
        r9 = r0.merge(r9);
        goto L8
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void set(int r4, TypeBearer r5) {
        throwIfImmutable();
        this.primary.set(r4, r5);
        ArrayList<LocalsArray> r3 = this.secondaries;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        LocalsArray r2 = r3.get(r1);
        r1 = r1 + 1;
        LocalsArray r22 = r2;
        if (r22 == null) goto L3;
        r22.set(r4, r5);
        goto L3
    }

    @Override // com.android.dx.cf.code.LocalsArray
    public void set(RegisterSpec r2) {
        set(r2.getReg(), r2);
    }

    @Override // com.android.dx.util.MutabilityControl
    public void setImmutable() {
        this.primary.setImmutable();
        ArrayList<LocalsArray> r0 = this.secondaries;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        LocalsArray r3 = r0.get(r2);
        r2 = r2 + 1;
        LocalsArray r32 = r3;
        if (r32 == null) goto L3;
        r32.setImmutable();
        goto L3
    L7:
        super.setImmutable();
    }

    public LocalsArray subArrayForLabel(int r1) {
        return getSecondaryForLabel(r1);
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        StringBuilder r0 = new StringBuilder("(locals array set; primary)\n");
        r0.append(getPrimary().toHuman());
        r0.append('\n');
        int r2 = this.secondaries.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        LocalsArray r4 = this.secondaries.get(r3);
        if (r4 == null) goto L7;
        r0.append("(locals array set: primary for caller " + Hex.u2(r3) + ")\n");
        r0.append(r4.getPrimary().toHuman());
        r0.append('\n');
    L7:
        r3 = r3 + 1;
        goto L3
    L9:
        return r0.toString();
    }
}
