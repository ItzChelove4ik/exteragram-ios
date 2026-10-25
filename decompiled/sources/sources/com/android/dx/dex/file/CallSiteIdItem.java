package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstCallSite;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class CallSiteIdItem extends IndexedItem implements Comparable {
    private static final int ITEM_SIZE = 4;
    CallSiteItem data;
    final CstCallSiteRef invokeDynamicRef;

    public CallSiteIdItem(CstCallSiteRef r1) {
        this.invokeDynamicRef = r1;
        this.data = null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r4) {
        CstCallSite r0 = this.invokeDynamicRef.getCallSite();
        CallSiteIdsSection r1 = r4.getCallSiteIds();
        CallSiteItem r2 = r1.getCallSiteItem(r0);
        if (r2 != null) goto L5;
        MixedItemSection r42 = r4.getByteData();
        r2 = new CallSiteItem(r0);
        r42.add(r2);
        r1.addCallSiteItem(r0, r2);
    L5:
        this.data = r2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object r1) {
        return this.invokeDynamicRef.compareTo2(((CallSiteIdItem) r1).invokeDynamicRef);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_CALL_SITE_ID_ITEM;
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 4;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r3, AnnotatedOutput r4) {
        int r32 = this.data.getAbsoluteOffset();
        if (r4.annotates() == false) goto L5;
        r4.annotate(0, indexString() + ' ' + this.invokeDynamicRef.toString());
        StringBuilder r2 = new StringBuilder("call_site_off: ");
        r2.append(Hex.u4(r32));
        r4.annotate(4, r2.toString());
    L5:
        r4.writeInt(r32);
    }
}
