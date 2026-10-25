package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstFieldRef;

/* loaded from: classes.dex */
public final class FieldIdItem extends MemberIdItem {
    public FieldIdItem(CstFieldRef r1) {
        super(r1);
    }

    @Override // com.android.dx.dex.file.MemberIdItem, com.android.dx.dex.file.IdItem, com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        super.addContents(r1);
        r1.getTypeIds().intern(getFieldRef().getType());
    }

    public CstFieldRef getFieldRef() {
        return (CstFieldRef) getRef();
    }

    @Override // com.android.dx.dex.file.MemberIdItem
    public int getTypoidIdx(DexFile r1) {
        return r1.getTypeIds().indexOf(getFieldRef().getType());
    }

    @Override // com.android.dx.dex.file.MemberIdItem
    public String getTypoidName() {
        return "type_idx";
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_FIELD_ID_ITEM;
    }
}
