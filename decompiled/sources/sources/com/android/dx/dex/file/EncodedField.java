package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.Leb128;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public final class EncodedField extends EncodedMember implements Comparable<EncodedField> {
    private final CstFieldRef field;

    public EncodedField(CstFieldRef r1, int r2) {
        super(r2);
        if (r1 == null) goto L6;
        this.field = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("field == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public void addContents(DexFile r1) {
        r1.getFieldIds().intern(this.field);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(EncodedField r1) {
        return this.field.compareTo2(r1.field);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(EncodedField r1) {
        return compareTo2(r1);
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public void debugPrint(PrintWriter r1, boolean r2) {
        r1.println(toString());
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public int encode(DexFile r3, AnnotatedOutput r4, int r5, int r6) {
        int r32 = r3.getFieldIds().indexOf(this.field);
        int r52 = r32 - r5;
        int r0 = getAccessFlags();
        if (r4.annotates() == false) goto L5;
        r4.annotate(0, String.format("  [%x] %s", new Object[]{Integer.valueOf(r6), this.field.toHuman()}));
        r4.annotate(Leb128.unsignedLeb128Size(r52), "    field_idx:    " + Hex.u4(r32));
        r4.annotate(Leb128.unsignedLeb128Size(r0), "    access_flags: " + AccessFlags.fieldString(r0));
    L5:
        r4.writeUleb128(r52);
        r4.writeUleb128(r0);
        return r32;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof EncodedField) == true) goto L6;
        return false;
    L6:
        if (compareTo2((EncodedField) r3) != 0) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public CstString getName() {
        return this.field.getNat().getName();
    }

    public CstFieldRef getRef() {
        return this.field;
    }

    public int hashCode() {
        return this.field.hashCode();
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return this.field.toHuman();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append(EncodedField.class.getName());
        r0.append('{');
        r0.append(Hex.u2(getAccessFlags()));
        r0.append(' ');
        r0.append(this.field);
        r0.append('}');
        return r0.toString();
    }
}
