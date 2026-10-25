package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.Collection;
import java.util.TreeMap;

/* loaded from: classes.dex */
public final class FieldIdsSection extends MemberIdsSection {
    private final TreeMap<CstFieldRef, FieldIdItem> fieldIds;

    public FieldIdsSection(DexFile r2) {
        super("field_ids", r2);
        this.fieldIds = new TreeMap();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r2) {
        if (r2 == null) goto L9;
        throwIfNotPrepared();
        FieldIdItem r1 = this.fieldIds.get((CstFieldRef) r2);
        if (r1 == null) goto L7;
        return r1;
    L7:
        f$$ExternalSyntheticBUOutline1.m("not found");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    }

    public int indexOf(CstFieldRef r2) {
        if (r2 == null) goto L10;
        throwIfNotPrepared();
        FieldIdItem r1 = this.fieldIds.get(r2);
        if (r1 != null) goto L7;
        f$$ExternalSyntheticBUOutline1.m("not found");
        return 0;
    L7:
        return r1.getIndex();
    L10:
        f$$ExternalSyntheticBUOutline2.m("ref == null");
        return 0;
    }

    public synchronized FieldIdItem intern(CstFieldRef r3) {
        monitor-enter(this);
        if (r3 == null) goto L13;
        throwIfPrepared();     // Catch: Throwable -> L8
        FieldIdItem r0 = this.fieldIds.get(r3);     // Catch: Throwable -> L8
        if (r0 != null) goto L10;
        r0 = new FieldIdItem(r3);     // Catch: Throwable -> L8
        this.fieldIds.put(r3, r0);     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);
        return r0;
    L13:
        throw new NullPointerException("field == null");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.fieldIds.values();
    }

    public void writeHeaderPart(AnnotatedOutput r5) {
        throwIfNotPrepared();
        int r0 = this.fieldIds.size();
        if (r0 != 0) goto L5;
        int r4 = 0;
    L7:
        if (r5.annotates() == false) goto L9;
        r5.annotate(4, "field_ids_size:  " + Hex.u4(r0));
        r5.annotate(4, "field_ids_off:   " + Hex.u4(r4));
    L9:
        r5.writeInt(r0);
        r5.writeInt(r4);
        return;
    L5:
        r4 = getFileOffset();
        goto L7
    }
}
