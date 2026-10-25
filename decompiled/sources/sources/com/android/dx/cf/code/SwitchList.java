package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.util.IntList;
import com.android.dx.util.MutabilityControl;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class SwitchList extends MutabilityControl {
    private int size;
    private final IntList targets;
    private final IntList values;

    public SwitchList(int r3) {
        super(true);
        this.values = new IntList(r3);
        this.targets = new IntList(r3 + 1);
        this.size = r3;
    }

    public void add(int r2, int r3) {
        throwIfImmutable();
        if (r3 < 0) goto L6;
        this.values.add(r2);
        this.targets.add(r3);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("target < 0");
    }

    public int getDefaultTarget() {
        return this.targets.get(this.size);
    }

    public int getTarget(int r1) {
        return this.targets.get(r1);
    }

    public IntList getTargets() {
        return this.targets;
    }

    public int getValue(int r1) {
        return this.values.get(r1);
    }

    public IntList getValues() {
        return this.values;
    }

    public void removeSuperfluousDefaults() {
        throwIfImmutable();
        int r0 = this.size;
        if (r0 != (this.targets.size() - 1)) goto L15;
        int r1 = this.targets.get(r0);
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r2 >= r0) goto L12;
        int r4 = this.targets.get(r2);
        if (r4 == r1) goto L11;
        if (r2 == r3) goto L10;
        this.targets.set(r3, r4);
        IntList r42 = this.values;
        r42.set(r3, r42.get(r2));
    L10:
        r3 = r3 + 1;
    L11:
        r2 = r2 + 1;
        goto L5
    L12:
        if (r3 == r0) goto L20;
        this.values.shrink(r3);
        this.targets.set(r3, r1);
        this.targets.shrink(r3 + 1);
        this.size = r3;
        return;
    L20:
        return;
    L15:
        f$$ExternalSyntheticBUOutline1.m("incomplete instance");
    }

    public void setDefaultTarget(int r3) {
        throwIfImmutable();
        if (r3 >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("target < 0");
        return;
    L5:
        if (this.targets.size() != this.size) goto L8;
        this.targets.add(r3);
        return;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("non-default elements not all set");
    }

    @Override // com.android.dx.util.MutabilityControl
    public void setImmutable() {
        this.values.setImmutable();
        this.targets.setImmutable();
        super.setImmutable();
    }

    public int size() {
        return this.size;
    }
}
