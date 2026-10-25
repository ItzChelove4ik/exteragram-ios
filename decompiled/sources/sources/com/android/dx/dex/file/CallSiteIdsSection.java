package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstCallSite;
import com.android.dx.rop.cst.CstCallSiteRef;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class CallSiteIdsSection extends UniformItemSection {
    private final TreeMap<CstCallSiteRef, CallSiteIdItem> callSiteIds;
    private final TreeMap<CstCallSite, CallSiteItem> callSites;

    public CallSiteIdsSection(DexFile r3) {
        super("call_site_ids", r3, 4);
        this.callSiteIds = new TreeMap();
        this.callSites = new TreeMap();
    }

    public void addCallSiteItem(CstCallSite r1, CallSiteItem r2) {
        if (r1 == null) goto L8;
        if (r2 == null) goto L6;
        this.callSites.put(r1, r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("callSiteItem == null");
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("callSite == null");
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r2) {
        if (r2 == null) goto L9;
        throwIfNotPrepared();
        CallSiteIdItem r1 = this.callSiteIds.get((CstCallSiteRef) r2);
        if (r1 == null) goto L7;
        return r1;
    L7:
        f$$ExternalSyntheticBUOutline1.m("not found");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("cst == null");
        return null;
    }

    public CallSiteItem getCallSiteItem(CstCallSite r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("callSite == null");
        return null;
    L4:
        return this.callSites.get(r1);
    }

    public synchronized void intern(CstCallSiteRef r3) {
        monitor-enter(this);
        if (r3 == null) goto L13;
        throwIfPrepared();     // Catch: Throwable -> L8
        if (this.callSiteIds.get(r3) != null) goto L10;
        CallSiteIdItem r0 = new CallSiteIdItem(r3);     // Catch: Throwable -> L8
        this.callSiteIds.put(r3, r0);     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);
        return;
    L13:
        throw new NullPointerException("cstRef");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.callSiteIds.values();
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        Iterator<CallSiteIdItem> r3 = this.callSiteIds.values().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L6;
        r3.next().setIndex(r0);
        r0 = r0 + 1;
        goto L4
    }
}
