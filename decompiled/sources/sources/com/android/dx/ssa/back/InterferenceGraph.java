package com.android.dx.ssa.back;

import com.android.dx.ssa.SetFactory;
import com.android.dx.util.IntSet;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class InterferenceGraph {
    private final ArrayList<IntSet> interference;

    public InterferenceGraph(int r4) {
        this.interference = new ArrayList(r4);
        int r0 = 0;
    L3:
        if (r0 >= r4) goto L5;
        this.interference.add(SetFactory.makeInterferenceSet(r4));
        r0 = r0 + 1;
        goto L3
    }

    private void ensureCapacity(int r4) {
        int r0 = this.interference.size();
        this.interference.ensureCapacity(r4);
    L3:
        if (r0 >= r4) goto L5;
        this.interference.add(SetFactory.makeInterferenceSet(r4));
        r0 = r0 + 1;
        goto L3
    }

    public void add(int r2, int r3) {
        ensureCapacity(Math.max(r2, r3) + 1);
        this.interference.get(r2).add(r3);
        this.interference.get(r3).add(r2);
    }

    public void dumpToStdout() {
        int r0 = this.interference.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        System.out.println("Reg " + r1 + ":" + this.interference.get(r1).toString());
        r1 = r1 + 1;
        goto L3
    }

    public void mergeInterferenceSet(int r2, IntSet r3) {
        if (r2 >= this.interference.size()) goto L6;
        r3.merge(this.interference.get(r2));
        return;
    }
}
