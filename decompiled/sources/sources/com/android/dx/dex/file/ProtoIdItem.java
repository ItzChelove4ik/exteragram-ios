package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class ProtoIdItem extends IndexedItem {
    private TypeListItem parameterTypes;
    private final Prototype prototype;
    private final CstString shortForm;

    public ProtoIdItem(Prototype r3) {
        TypeListItem r0 = null;
        if (r3 == null) goto L10;
        this.prototype = r3;
        this.shortForm = makeShortForm(r3);
        StdTypeList r32 = r3.getParameterTypes();
        if (r32.size() == 0) goto L8;
        r0 = new TypeListItem(r32);
    L8:
        this.parameterTypes = r0;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("prototype == null");
        throw null;
    }

    private static CstString makeShortForm(Prototype r4) {
        StdTypeList r0 = r4.getParameterTypes();
        int r1 = r0.size();
        StringBuilder r2 = new StringBuilder(r1 + 1);
        r2.append(shortFormCharFor(r4.getReturnType()));
        int r42 = 0;
    L3:
        if (r42 >= r1) goto L6;
        r2.append(shortFormCharFor(r0.getType(r42)));
        r42 = r42 + 1;
        goto L3
    L6:
        return new CstString(r2.toString());
    }

    private static char shortFormCharFor(Type r1) {
        char r12 = r1.getDescriptor().charAt(0);
        if (r12 != '[') goto L6;
        return 'L';
    L6:
        return r12;
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r4) {
        StringIdsSection r0 = r4.getStringIds();
        TypeIdsSection r1 = r4.getTypeIds();
        MixedItemSection r42 = r4.getTypeLists();
        r1.intern(this.prototype.getReturnType());
        r0.intern(this.shortForm);
        TypeListItem r02 = this.parameterTypes;
        if (r02 == null) goto L6;
        this.parameterTypes = (TypeListItem) r42.intern(r02);
        return;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_PROTO_ID_ITEM;
    }

    @Override // com.android.dx.dex.file.Item
    public int writeSize() {
        return 12;
    }

    @Override // com.android.dx.dex.file.Item
    public void writeTo(DexFile r9, AnnotatedOutput r10) {
        int r0 = r9.getStringIds().indexOf(this.shortForm);
        int r92 = r9.getTypeIds().indexOf(this.prototype.getReturnType());
        int r1 = OffsettedItem.getAbsoluteOffsetOr0(this.parameterTypes);
        if (r10.annotates() == false) goto L10;
        StringBuilder r2 = new StringBuilder();
        r2.append(this.prototype.getReturnType().toHuman());
        r2.append(" proto(");
        StdTypeList r3 = this.prototype.getParameterTypes();
        int r4 = r3.size();
        int r6 = 0;
    L5:
        if (r6 >= r4) goto L9;
        if (r6 == 0) goto L8;
        r2.append(", ");
    L8:
        r2.append(r3.getType(r6).toHuman());
        r6 = r6 + 1;
        goto L5
    L9:
        r2.append(")");
        r10.annotate(0, indexString() + ' ' + r2.toString());
        r10.annotate(4, "  shorty_idx:      " + Hex.u4(r0) + " // " + this.shortForm.toQuoted());
        r10.annotate(4, "  return_type_idx: " + Hex.u4(r92) + " // " + this.prototype.getReturnType().toHuman());
        StringBuilder r8 = new StringBuilder("  parameters_off:  ");
        r8.append(Hex.u4(r1));
        r10.annotate(4, r8.toString());
    L10:
        r10.writeInt(r0);
        r10.writeInt(r92);
        r10.writeInt(r1);
    }
}
