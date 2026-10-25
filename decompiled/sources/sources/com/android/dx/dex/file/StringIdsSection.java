package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: classes.dex */
public final class StringIdsSection extends UniformItemSection {
    private final TreeMap<CstString, StringIdItem> strings;

    public StringIdsSection(DexFile r3) {
        super("string_ids", r3, 4);
        this.strings = new TreeMap();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r2) {
        if (r2 == null) goto L9;
        throwIfNotPrepared();
        StringIdItem r1 = this.strings.get((CstString) r2);
        if (r1 == null) goto L7;
        return r1;
    L7:
        f$$ExternalSyntheticBUOutline1.m("not found");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    }

    public int indexOf(CstString r2) {
        if (r2 == null) goto L10;
        throwIfNotPrepared();
        StringIdItem r1 = this.strings.get(r2);
        if (r1 != null) goto L7;
        f$$ExternalSyntheticBUOutline1.m("not found");
        return 0;
    L7:
        return r1.getIndex();
    L10:
        f$$ExternalSyntheticBUOutline2.m("string == null");
        return 0;
    }

    public synchronized StringIdItem intern(StringIdItem r3) {
        monitor-enter(this);
        if (r3 == null) goto L14;
        throwIfPrepared();     // Catch: Throwable -> L11
        CstString r0 = r3.getValue();     // Catch: Throwable -> L11
        StringIdItem r1 = this.strings.get(r0);     // Catch: Throwable -> L11
        if (r1 == null) goto L8;
        monitor-exit(this);
        return r1;
    L8:
        this.strings.put(r0, r3);     // Catch: Throwable -> L11
        monitor-exit(this);
        return r3;
    L14:
        throw new NullPointerException("string == null");     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public StringIdItem intern(CstString r2) {
        return intern(new StringIdItem(r2));
    }

    public StringIdItem intern(String r3) {
        return intern(new StringIdItem(new CstString(r3)));
    }

    public synchronized void intern(CstNat r2) {
        monitor-enter(this);
        intern(r2.getName());     // Catch: Throwable -> L6
        intern(r2.getDescriptor());     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.strings.values();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<StringIdItem> r2 = this.strings.values().iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r2.next().setIndex(r0);
        r0 = r0 + 1;
        goto L4
    }

    public void writeHeaderPart(AnnotatedOutput r5) {
        throwIfNotPrepared();
        int r0 = this.strings.size();
        if (r0 != 0) goto L5;
        int r4 = 0;
    L7:
        if (r5.annotates() == false) goto L9;
        r5.annotate(4, "string_ids_size: " + Hex.u4(r0));
        r5.annotate(4, "string_ids_off:  " + Hex.u4(r4));
    L9:
        r5.writeInt(r0);
        r5.writeInt(r4);
        return;
    L5:
        r4 = getFileOffset();
        goto L7
    }
}
