package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class TypeIdItem extends IdItem {
    public TypeIdItem(CstType r1) {
        super(r1);
    }

    @Override // com.android.dx.dex.file.IdItem, com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        r1.getStringIds().intern(getDefiningClass().getDescriptor());
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_TYPE_ID_ITEM;
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 4;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r3, AnnotatedOutput r4) {
        CstString r0 = getDefiningClass().getDescriptor();
        int r32 = r3.getStringIds().indexOf(r0);
        if (r4.annotates() == false) goto L5;
        r4.annotate(0, indexString() + ' ' + r0.toHuman());
        StringBuilder r2 = new StringBuilder("  descriptor_idx: ");
        r2.append(Hex.u4(r32));
        r4.annotate(4, r2.toString());
    L5:
        r4.writeInt(r32);
    }
}
