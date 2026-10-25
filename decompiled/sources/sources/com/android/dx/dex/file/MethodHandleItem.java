package com.android.dx.dex.file;

import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstBaseMethodRef;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstInterfaceMethodRef;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class MethodHandleItem extends IndexedItem {
    private final int ITEM_SIZE;
    private final CstMethodHandle methodHandle;

    public MethodHandleItem(CstMethodHandle r2) {
        this.ITEM_SIZE = 8;
        this.methodHandle = r2;
    }

    private int getTargetIndex(DexFile r3) {
        Constant r0 = this.methodHandle.getRef();
        if (this.methodHandle.isAccessor() == false) goto L7;
        return r3.getFieldIds().indexOf((CstFieldRef) r0);
    L7:
        if (this.methodHandle.isInvocation() == true) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Unhandled invocation type");
        return 0;
    L9:
        if ((r0 instanceof CstInterfaceMethodRef) == false) goto L12;
        r0 = ((CstInterfaceMethodRef) r0).toMethodRef();
    L12:
        return r3.getMethodIds().indexOf((CstBaseMethodRef) r0);
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        r1.getMethodHandles().intern(this.methodHandle);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_METHOD_HANDLE_ITEM;
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 8;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r9, AnnotatedOutput r10) {
        int r0 = getTargetIndex(r9);
        int r1 = this.methodHandle.getMethodHandleType();
        if (r10.annotates() == false) goto L9;
        r10.annotate(0, indexString() + ' ' + this.methodHandle.toString());
        r10.annotate(2, "type:     " + Hex.u2(r1) + (" // " + CstMethodHandle.getMethodHandleTypeName(r1)));
        r10.annotate(2, "reserved: " + Hex.u2(0));
        String r2 = " // " + this.methodHandle.getRef().toString();
        if (this.methodHandle.isAccessor() == false) goto L7;
        r10.annotate(2, "fieldId:  " + Hex.u2(r0) + r2);
    L8:
        r10.annotate(2, "reserved: " + Hex.u2(0));
        goto L9
    L7:
        r10.annotate(2, "methodId: " + Hex.u2(r0) + r2);
    L9:
        r10.writeShort(r1);
        r10.writeShort(0);
        r10.writeShort(getTargetIndex(r9));
        r10.writeShort(0);
    }
}
