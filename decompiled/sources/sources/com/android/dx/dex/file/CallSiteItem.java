package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstCallSite;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;

/* loaded from: classes4.dex */
public final class CallSiteItem extends OffsettedItem {
    private byte[] encodedForm;
    private final CstCallSite value;

    public CallSiteItem(CstCallSite r3) {
        super(1, writeSize(r3));
        this.value = r3;
    }

    private static int writeSize(CstCallSite r0) {
        return -1;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        ValueEncoder.addContents(r1, this.value);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_ENCODED_ARRAY_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r3, int r4) {
        ByteArrayAnnotatedOutput r42 = new ByteArrayAnnotatedOutput();
        new ValueEncoder(r3.getFile(), r42).writeArray(this.value, true);
        byte[] r32 = r42.toByteArray();
        this.encodedForm = r32;
        setWriteSize(r32.length);
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.value.toHuman();
    }

    public String toString() {
        return this.value.toString();
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r3, AnnotatedOutput r4) {
        if (r4.annotates() == false) goto L6;
        r4.annotate(0, offsetString() + " call site");
        new ValueEncoder(r3, r4).writeArray(this.value, true);
        return;
    L6:
        r4.write(this.encodedForm);
    }
}
