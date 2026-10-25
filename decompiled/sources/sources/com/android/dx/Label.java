package com.android.dx;

import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.InsnList;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class Label {
    Label alternateSuccessor;
    List<Label> catchLabels;
    Code code;
    int id;
    final List<Insn> instructions;
    boolean marked;
    Label primarySuccessor;

    public Label() {
        this.instructions = new ArrayList();
        this.marked = false;
        this.catchLabels = Collections.EMPTY_LIST;
        this.id = -1;
    }

    public void compact() {
        int r0 = 0;
    L4:
        if (r0 >= this.catchLabels.size()) goto L9;
    L6:
        if (this.catchLabels.get(r0).isEmpty() == false) goto L8;
        List<Label> r1 = this.catchLabels;
        r1.set(r0, r1.get(r0).primarySuccessor);
        goto L6
    L8:
        r0 = r0 + 1;
    L9:
        Label r02 = this.primarySuccessor;
        if (r02 == null) goto L14;
        if (r02.isEmpty() == false) goto L14;
        this.primarySuccessor = this.primarySuccessor.primarySuccessor;
    L14:
        Label r03 = this.alternateSuccessor;
        if (r03 == null) goto L27;
        if (r03.isEmpty() == false) goto L19;
        this.alternateSuccessor = this.alternateSuccessor.primarySuccessor;
        goto L14
    L19:
        return;
    }

    public boolean isEmpty() {
        return this.instructions.isEmpty();
    }

    public BasicBlock toBasicBlock() {
        InsnList r0 = new InsnList(this.instructions.size());
        int r1 = 0;
    L4:
        if (r1 >= this.instructions.size()) goto L6;
        r0.set(r1, this.instructions.get(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        r0.setImmutable();
        IntList r12 = new IntList();
        Iterator<Label> r2 = this.catchLabels.iterator();
    L8:
        if (r2.hasNext() == false) goto L10;
        r12.add(r2.next().id);
        goto L8
    L10:
        Label r22 = this.primarySuccessor;
        if (r22 == null) goto L13;
        int r23 = r22.id;
        r12.add(r23);
    L14:
        Label r3 = this.alternateSuccessor;
        if (r3 == null) goto L17;
        r12.add(r3.id);
    L17:
        r12.setImmutable();
        return new BasicBlock(this.id, r0, r12, r23);
    L13:
        r23 = -1;
        goto L14
    }
}
