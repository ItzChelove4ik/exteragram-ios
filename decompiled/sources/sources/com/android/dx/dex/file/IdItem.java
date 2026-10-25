package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstType;

/* loaded from: classes.dex */
public abstract class IdItem extends IndexedItem {
    private final CstType type;

    public IdItem(CstType r1) {
        if (r1 == null) goto L6;
        this.type = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("type == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        r1.getTypeIds().intern(this.type);
    }

    public final CstType getDefiningClass() {
        return this.type;
    }
}
