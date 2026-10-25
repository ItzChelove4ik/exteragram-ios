package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstMethodHandle;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class MethodHandlesSection extends UniformItemSection {
    private final TreeMap<CstMethodHandle, MethodHandleItem> methodHandles;

    public MethodHandlesSection(DexFile r3) {
        super("method_handles", r3, 8);
        this.methodHandles = new TreeMap();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r2) {
        if (r2 == null) goto L9;
        throwIfNotPrepared();
        MethodHandleItem r1 = this.methodHandles.get((CstMethodHandle) r2);
        if (r1 == null) goto L7;
        return r1;
    L7:
        f$$ExternalSyntheticBUOutline1.m("not found");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    }

    public int indexOf(CstMethodHandle r1) {
        return this.methodHandles.get(r1).getIndex();
    }

    public void intern(CstMethodHandle r2) {
        if (r2 == null) goto L7;
        throwIfPrepared();
        if (this.methodHandles.get(r2) != null) goto L9;
        MethodHandleItem r0 = new MethodHandleItem(r2);
        this.methodHandles.put(r2, r0);
        return;
    L9:
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("methodHandle == null");
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.methodHandles.values();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<MethodHandleItem> r3 = this.methodHandles.values().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L6;
        r3.next().setIndex(r0);
        r0 = r0 + 1;
        goto L4
    }
}
