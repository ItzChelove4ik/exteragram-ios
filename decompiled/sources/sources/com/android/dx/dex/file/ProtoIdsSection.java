package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.type.Prototype;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ProtoIdsSection extends UniformItemSection {
    private final TreeMap<Prototype, ProtoIdItem> protoIds;

    public ProtoIdsSection(DexFile r3) {
        super("proto_ids", r3, 4);
        this.protoIds = new TreeMap();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r3) {
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    L5:
        if ((r3 instanceof CstProtoRef) == false) goto L11;
        throwIfNotPrepared();
        ProtoIdItem r2 = this.protoIds.get(((CstProtoRef) r3).getPrototype());
        if (r2 == null) goto L9;
        return r2;
    L9:
        f$$ExternalSyntheticBUOutline1.m("not found");
        return null;
    L11:
        f$$ExternalSyntheticBUOutline1.m("cst not instance of CstProtoRef");
        return null;
    }

    public int indexOf(Prototype r2) {
        if (r2 == null) goto L10;
        throwIfNotPrepared();
        ProtoIdItem r1 = this.protoIds.get(r2);
        if (r1 != null) goto L7;
        f$$ExternalSyntheticBUOutline1.m("not found");
        return 0;
    L7:
        return r1.getIndex();
    L10:
        f$$ExternalSyntheticBUOutline2.m("prototype == null");
        return 0;
    }

    public synchronized ProtoIdItem intern(Prototype r3) {
        monitor-enter(this);
        if (r3 == null) goto L13;
        throwIfPrepared();     // Catch: Throwable -> L8
        ProtoIdItem r0 = this.protoIds.get(r3);     // Catch: Throwable -> L8
        if (r0 != null) goto L10;
        r0 = new ProtoIdItem(r3);     // Catch: Throwable -> L8
        this.protoIds.put(r3, r0);     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);
        return r0;
    L13:
        throw new NullPointerException("prototype == null");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.protoIds.values();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<? extends Item> r2 = items().iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        ((ProtoIdItem) r2.next()).setIndex(r0);
        r0 = r0 + 1;
        goto L4
    }

    public void writeHeaderPart(AnnotatedOutput r5) {
        throwIfNotPrepared();
        int r0 = this.protoIds.size();
        if (r0 != 0) goto L5;
        int r4 = 0;
    L7:
        if (r0 <= 65536) goto L9;
        ByteString$$ExternalSyntheticBUOutline0.m("too many proto ids");
        return;
    L9:
        if (r5.annotates() == false) goto L11;
        r5.annotate(4, "proto_ids_size:  " + Hex.u4(r0));
        r5.annotate(4, "proto_ids_off:   " + Hex.u4(r4));
    L11:
        r5.writeInt(r0);
        r5.writeInt(r4);
        return;
    L5:
        r4 = getFileOffset();
        goto L7
    }
}
