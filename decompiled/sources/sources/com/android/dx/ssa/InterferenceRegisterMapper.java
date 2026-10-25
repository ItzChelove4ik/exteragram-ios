package com.android.dx.ssa;

import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.ssa.back.InterferenceGraph;
import com.android.dx.util.BitIntSet;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class InterferenceRegisterMapper extends BasicRegisterMapper {
    private final ArrayList<BitIntSet> newRegInterference;
    private final InterferenceGraph oldRegInterference;

    public InterferenceRegisterMapper(InterferenceGraph r1, int r2) {
        super(r2);
        this.newRegInterference = new ArrayList();
        this.oldRegInterference = r1;
    }

    private void addInterfence(int r4, int r5) {
        int r1 = r4 + 1;
        this.newRegInterference.ensureCapacity(r1);
    L4:
        if (r4 < this.newRegInterference.size()) goto L6;
        this.newRegInterference.add(new BitIntSet(r1));
        goto L4
    L6:
        this.oldRegInterference.mergeInterferenceSet(r5, this.newRegInterference.get(r4));
    }

    @Override // com.android.dx.ssa.BasicRegisterMapper
    public void addMapping(int r2, int r3, int r4) {
        super.addMapping(r2, r3, r4);
        addInterfence(r3, r2);
        if (r4 != 2) goto L6;
        addInterfence(r3 + 1, r2);
        return;
    }

    public boolean areAnyPinned(RegisterSpecList r7, int r8, int r9) {
        int r0 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L17;
        RegisterSpec r3 = r7.get(r2);
        int r4 = oldToNew(r3.getReg());
        if (r4 == r8) goto L15;
        if (r3.getCategory() == 2) goto L9;
    L10:
        if (r9 != 2) goto L14;
        if (r4 != (r8 + 1)) goto L14;
        return true;
    L14:
        r2 = r2 + 1;
        goto L3
    L9:
        if ((r4 + 1) != r8) goto L10;
        return true;
    L15:
        return true;
    L17:
        return false;
    }

    public boolean interferes(int r4, int r5, int r6) {
        if (r5 < this.newRegInterference.size()) goto L5;
        return false;
    L5:
        BitIntSet r0 = this.newRegInterference.get(r5);
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r6 != 1) goto L13;
        return r0.has(r4);
    L13:
        if (r0.has(r4) == false) goto L15;
    L18:
        return true;
    L15:
        if (interferes(r4, r5 + 1, r6 - 1) == true) goto L18;
        return false;
    }

    public boolean interferes(RegisterSpec r2, int r3) {
        return interferes(r2.getReg(), r3, r2.getCategory());
    }
}
