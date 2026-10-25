package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstBaseMethodRef;

/* loaded from: classes.dex */
public final class MethodIdItem extends MemberIdItem {
    public MethodIdItem(CstBaseMethodRef r1) {
        super(r1);
    }

    @Override // com.android.dx.dex.file.MemberIdItem, com.android.dx.dex.file.IdItem, com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        super.addContents(r1);
        r1.getProtoIds().intern(getMethodRef().getPrototype());
    }

    public CstBaseMethodRef getMethodRef() {
        return (CstBaseMethodRef) getRef();
    }

    @Override // com.android.dx.dex.file.MemberIdItem
    public int getTypoidIdx(DexFile r1) {
        return r1.getProtoIds().indexOf(getMethodRef().getPrototype());
    }

    @Override // com.android.dx.dex.file.MemberIdItem
    public String getTypoidName() {
        return "proto_idx";
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_METHOD_ID_ITEM;
    }
}
