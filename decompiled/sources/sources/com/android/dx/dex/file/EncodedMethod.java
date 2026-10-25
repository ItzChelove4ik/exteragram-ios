package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.Leb128;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.io.PrintWriter;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class EncodedMethod extends EncodedMember implements Comparable<EncodedMethod> {
    private final CodeItem code;
    private final CstMethodRef method;

    public EncodedMethod(CstMethodRef r2, int r3, DalvCode r4, TypeList r5) {
        super(r3);
        if (r2 == null) goto L14;
        this.method = r2;
        if (r4 != null) goto L9;
        this.code = null;
        return;
    L9:
        if ((r3 & 8) == 0) goto L11;
        boolean r32 = true;
    L12:
        this.code = new CodeItem(r2, r4, r32, r5);
        return;
    L11:
        r32 = false;
        goto L12
    L14:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public void addContents(DexFile r3) {
        MethodIdsSection r0 = r3.getMethodIds();
        MixedItemSection r32 = r3.getWordData();
        r0.intern(this.method);
        CodeItem r2 = this.code;
        if (r2 == null) goto L6;
        r32.add(r2);
        return;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(EncodedMethod r1) {
        return this.method.compareTo2(r1.method);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(EncodedMethod r1) {
        return compareTo2(r1);
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public void debugPrint(PrintWriter r2, boolean r3) {
        CodeItem r0 = this.code;
        if (r0 != null) goto L6;
        r2.println(getRef().toHuman() + ": abstract or native");
        return;
    L6:
        r0.debugPrint(r2, "  ", r3);
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public int encode(DexFile r7, AnnotatedOutput r8, int r9, int r10) {
        int r72 = r7.getMethodIds().indexOf(this.method);
        int r92 = r72 - r9;
        int r0 = getAccessFlags();
        int r1 = OffsettedItem.getAbsoluteOffsetOr0(this.code);
        boolean r2 = true;
        if (r1 == 0) goto L5;
        boolean r4 = true;
    L7:
        if ((r0 & 1280) == 0) goto L10;
        r2 = false;
    L10:
        if (r4 == r2) goto L12;
        ByteString$$ExternalSyntheticBUOutline0.m("code vs. access_flags mismatch");
        return 0;
    L12:
        if (r8.annotates() == false) goto L14;
        r8.annotate(0, String.format("  [%x] %s", new Object[]{Integer.valueOf(r10), this.method.toHuman()}));
        r8.annotate(Leb128.unsignedLeb128Size(r92), "    method_idx:   " + Hex.u4(r72));
        r8.annotate(Leb128.unsignedLeb128Size(r0), "    access_flags: " + AccessFlags.methodString(r0));
        r8.annotate(Leb128.unsignedLeb128Size(r1), "    code_off:     " + Hex.u4(r1));
    L14:
        r8.writeUleb128(r92);
        r8.writeUleb128(r0);
        r8.writeUleb128(r1);
        return r72;
    L5:
        r4 = false;
        goto L7
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof EncodedMethod) == true) goto L6;
        return false;
    L6:
        if (compareTo2((EncodedMethod) r3) != 0) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // com.android.dx.dex.file.EncodedMember
    public final CstString getName() {
        return this.method.getNat().getName();
    }

    public final CstMethodRef getRef() {
        return this.method;
    }

    @Override // com.android.dx.util.ToHuman
    public final String toHuman() {
        return this.method.toHuman();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append(EncodedMethod.class.getName());
        r0.append('{');
        r0.append(Hex.u2(getAccessFlags()));
        r0.append(' ');
        r0.append(this.method);
        if (this.code == null) goto L5;
        r0.append(' ');
        r0.append(this.code);
    L5:
        r0.append('}');
        return r0.toString();
    }
}
