package com.android.dx.dex.code;

import com.android.dx.dex.DexOptions;
import java.util.ArrayList;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class OutputCollector {
    private final OutputFinisher finisher;
    private ArrayList<DalvInsn> suffix;

    public OutputCollector(DexOptions r2, int r3, int r4, int r5, int r6) {
        this.finisher = new OutputFinisher(r2, r3, r5, r6);
        this.suffix = new ArrayList(r4);
    }

    private void appendSuffixToOutput() {
        int r0 = this.suffix.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.finisher.add(this.suffix.get(r1));
        r1 = r1 + 1;
        goto L3
    L5:
        this.suffix = null;
    }

    public void add(DalvInsn r1) {
        this.finisher.add(r1);
    }

    public void addSuffix(DalvInsn r1) {
        this.suffix.add(r1);
    }

    public OutputFinisher getFinisher() {
        if (this.suffix == null) goto L6;
        appendSuffixToOutput();
        return this.finisher;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("already processed");
        return null;
    }

    public void reverseBranch(int r1, CodeAddress r2) {
        this.finisher.reverseBranch(r1, r2);
    }
}
