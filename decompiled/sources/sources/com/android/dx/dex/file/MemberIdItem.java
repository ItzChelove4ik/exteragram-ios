package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstMemberRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public abstract class MemberIdItem extends IdItem {
    private final CstMemberRef cst;

    public MemberIdItem(CstMemberRef r2) {
        super(r2.getDefiningClass());
        this.cst = r2;
    }

    @Override // com.android.dx.dex.file.IdItem, com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
        super.addContents(r1);
        r1.getStringIds().intern(getRef().getNat().getName());
    }

    public final CstMemberRef getRef() {
        return this.cst;
    }

    public abstract int getTypoidIdx(DexFile r1);

    public abstract String getTypoidName();

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 8;
    }

    @Override // com.android.dx.dex.file.Item
    public final void writeTo(DexFile r5, AnnotatedOutput r6) {
        TypeIdsSection r0 = r5.getTypeIds();
        StringIdsSection r1 = r5.getStringIds();
        CstNat r2 = this.cst.getNat();
        int r02 = r0.indexOf(getDefiningClass());
        int r12 = r1.indexOf(r2.getName());
        int r52 = getTypoidIdx(r5);
        if (r6.annotates() == false) goto L5;
        r6.annotate(0, indexString() + ' ' + this.cst.toHuman());
        StringBuilder r22 = new StringBuilder("  class_idx: ");
        r22.append(Hex.u2(r02));
        r6.annotate(2, r22.toString());
        r6.annotate(2, String.format("  %-10s %s", new Object[]{getTypoidName() + ':', Hex.u2(r52)}));
        StringBuilder r4 = new StringBuilder("  name_idx:  ");
        r4.append(Hex.u4(r12));
        r6.annotate(4, r4.toString());
    L5:
        r6.writeShort(r02);
        r6.writeShort(r52);
        r6.writeInt(r12);
    }
}
