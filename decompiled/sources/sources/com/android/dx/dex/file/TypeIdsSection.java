package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.DexIndexOverflowException;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: classes.dex */
public final class TypeIdsSection extends UniformItemSection {
    private final TreeMap<Type, TypeIdItem> typeIds;

    public TypeIdsSection(DexFile r3) {
        super("type_ids", r3, 4);
        this.typeIds = new TreeMap();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r3) {
        if (r3 == null) goto L9;
        throwIfNotPrepared();
        Type r1 = ((CstType) r3).getClassType();
        TypeIdItem r2 = this.typeIds.get(r1);
        if (r2 == null) goto L7;
        return r2;
    L7:
        Native$$ExternalSyntheticBUOutline5.m("not found: ", r3);
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    }

    public int indexOf(CstType r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("type == null");
        return 0;
    L4:
        return indexOf(r1.getClassType());
    }

    public int indexOf(Type r2) {
        if (r2 == null) goto L10;
        throwIfNotPrepared();
        TypeIdItem r1 = this.typeIds.get(r2);
        if (r1 != null) goto L7;
        Native$$ExternalSyntheticBUOutline5.m("not found: ", r2);
        return 0;
    L7:
        return r1.getIndex();
    L10:
        f$$ExternalSyntheticBUOutline2.m("type == null");
        return 0;
    }

    public synchronized TypeIdItem intern(CstType r3) {
        monitor-enter(this);
        if (r3 == null) goto L13;
        throwIfPrepared();     // Catch: Throwable -> L8
        Type r0 = r3.getClassType();     // Catch: Throwable -> L8
        TypeIdItem r1 = this.typeIds.get(r0);     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        r1 = new TypeIdItem(r3);     // Catch: Throwable -> L8
        this.typeIds.put(r0, r1);     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);
        return r1;
    L13:
        throw new NullPointerException("type == null");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized TypeIdItem intern(Type r3) {
        monitor-enter(this);
        if (r3 == null) goto L13;
        throwIfPrepared();     // Catch: Throwable -> L8
        TypeIdItem r0 = this.typeIds.get(r3);     // Catch: Throwable -> L8
        if (r0 != null) goto L10;
        r0 = new TypeIdItem(new CstType(r3));     // Catch: Throwable -> L8
        this.typeIds.put(r3, r0);     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);
        return r0;
    L13:
        throw new NullPointerException("type == null");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.typeIds.values();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<? extends Item> r2 = items().iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        ((TypeIdItem) r2.next()).setIndex(r0);
        r0 = r0 + 1;
        goto L4
    }

    public void writeHeaderPart(AnnotatedOutput r5) {
        throwIfNotPrepared();
        int r0 = this.typeIds.size();
        if (r0 != 0) goto L5;
        int r1 = 0;
    L7:
        if (r0 > 65536) goto L14;
        if (r5.annotates() == false) goto L11;
        r5.annotate(4, "type_ids_size:   " + Hex.u4(r0));
        r5.annotate(4, "type_ids_off:    " + Hex.u4(r1));
    L11:
        r5.writeInt(r0);
        r5.writeInt(r1);
        return;
    L14:
        throw new DexIndexOverflowException(String.format("Too many type identifiers to fit in one dex file: %1$d; max is %2$d.%nYou may try using multi-dex. If multi-dex is enabled then the list of classes for the main dex list is too large.", new Object[]{Integer.valueOf(items().size()), 65536}));
    L5:
        r1 = getFileOffset();
        goto L7
    }
}
