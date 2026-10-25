package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.dex.code.DalvInsnList;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.io.PrintWriter;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class CodeItem extends OffsettedItem {
    private static final int ALIGNMENT = 4;
    private static final int HEADER_SIZE = 16;
    private CatchStructs catches;
    private final DalvCode code;
    private DebugInfoItem debugInfo;
    private final boolean isStatic;
    private final CstMethodRef ref;
    private final TypeList throwsList;

    /* renamed from: com.android.dx.dex.file.CodeItem$1, reason: invalid class name */
    public class AnonymousClass1 implements DalvCode.AssignIndicesCallback {
        final /* synthetic */ CodeItem this$0;
        final /* synthetic */ DexFile val$file;

        public AnonymousClass1(CodeItem r1, DexFile r2) {
            this.this$0 = r1;
            this.val$file = r2;
        }

        @Override // com.android.dx.dex.code.DalvCode.AssignIndicesCallback
        public int getIndex(Constant r1) {
            IndexedItem r0 = this.val$file.findItemOrNull(r1);
            if (r0 != null) goto L7;
            return -1;
        L7:
            return r0.getIndex();
        }
    }

    public CodeItem(CstMethodRef r3, DalvCode r4, boolean r5, TypeList r6) {
        super(4, -1);
        if (r3 == null) goto L12;
        if (r4 == null) goto L10;
        if (r6 == null) goto L8;
        this.ref = r3;
        this.code = r4;
        this.isStatic = r5;
        this.throwsList = r6;
        this.catches = null;
        this.debugInfo = null;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("throwsList == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("code == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("ref == null");
        throw null;
    }

    private int getInsSize() {
        return this.ref.getParameterWordCount(this.isStatic);
    }

    private int getOutsSize() {
        return this.code.getInsns().getOutsSize();
    }

    private int getRegistersSize() {
        return this.code.getInsns().getRegistersSize();
    }

    private void writeCodes(DexFile r2, AnnotatedOutput r3) {
        this.code.getInsns().writeTo(r3);     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while writing instructions for " + this.ref.toHuman());
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r7) {
        MixedItemSection r0 = r7.getByteData();
        TypeIdsSection r1 = r7.getTypeIds();
        if (this.code.hasPositions() == false) goto L5;
    L6:
        DebugInfoItem r2 = new DebugInfoItem(this.code, this.isStatic, this.ref);
        this.debugInfo = r2;
        r0.add(r2);
    L8:
        if (this.code.hasAnyCatches() == false) goto L14;
        Iterator<Type> r02 = this.code.getCatchTypes().iterator();
    L11:
        if (r02.hasNext() == false) goto L13;
        r1.intern(r02.next());
        goto L11
    L13:
        this.catches = new CatchStructs(this.code);
    L14:
        Iterator<Constant> r6 = this.code.getInsnConstants().iterator();
    L16:
        if (r6.hasNext() == false) goto L18;
        r7.internIfAppropriate(r6.next());
        goto L16
    L18:
        return;
    L5:
        if (this.code.hasLocals() == false) goto L8;
        goto L6
    }

    public void debugPrint(PrintWriter r4, String r5, boolean r6) {
        r4.println(this.ref.toHuman() + ":");
        DalvInsnList r0 = this.code.getInsns();
        r4.println("regs: " + Hex.u2(getRegistersSize()) + "; ins: " + Hex.u2(getInsSize()) + "; outs: " + Hex.u2(getOutsSize()));
        r0.debugPrint(r4, r5, r6);
        StringBuilder r62 = new StringBuilder();
        r62.append(r5);
        r62.append("  ");
        String r63 = r62.toString();
        if (this.catches == null) goto L6;
        r4.print(r5);
        r4.println("catches");
        this.catches.debugPrint(r4, r63);
    L6:
        if (this.debugInfo == null) goto L9;
        r4.print(r5);
        r4.println("debug info");
        this.debugInfo.debugPrint(r4, r63);
        return;
    }

    public CstMethodRef getRef() {
        return this.ref;
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_CODE_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r2, int r3) {
        DexFile r22 = r2.getFile();
        this.code.assignIndices(new AnonymousClass1(this, r22));
        CatchStructs r32 = this.catches;
        if (r32 == null) goto L5;
        r32.encode(r22);
        int r23 = this.catches.writeSize();
    L6:
        int r33 = this.code.getInsns().codeSize();
        if ((r33 & 1) == 0) goto L9;
        r33 = r33 + 1;
    L9:
        setWriteSize(((r33 * 2) + 16) + r23);
        return;
    L5:
        r23 = 0;
        goto L6
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        return this.ref.toHuman();
    }

    public String toString() {
        return "CodeItem{" + toHuman() + "}";
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r14, AnnotatedOutput r15) {
        boolean r0 = r15.annotates();
        int r1 = getRegistersSize();
        int r2 = getOutsSize();
        int r3 = getInsSize();
        int r4 = this.code.getInsns().codeSize();
        if ((r4 & 1) == 0) goto L5;
        boolean r5 = true;
    L6:
        CatchStructs r7 = this.catches;
        if (r7 != null) goto L9;
        int r72 = 0;
    L10:
        DebugInfoItem r8 = this.debugInfo;
        if (r8 != null) goto L13;
        int r82 = 0;
    L15:
        if (r0 == false) goto L19;
        r15.annotate(0, offsetString() + ' ' + this.ref.toHuman());
        StringBuilder r10 = new StringBuilder("  registers_size: ");
        r10.append(Hex.u2(r1));
        r15.annotate(2, r10.toString());
        r15.annotate(2, "  ins_size:       " + Hex.u2(r3));
        r15.annotate(2, "  outs_size:      " + Hex.u2(r2));
        r15.annotate(2, "  tries_size:     " + Hex.u2(r72));
        r15.annotate(4, "  debug_off:      " + Hex.u4(r82));
        r15.annotate(4, "  insns_size:     " + Hex.u4(r4));
        if (this.throwsList.size() == 0) goto L19;
        r15.annotate(0, "  throws " + StdTypeList.toHuman(this.throwsList));
    L19:
        r15.writeShort(r1);
        r15.writeShort(r3);
        r15.writeShort(r2);
        r15.writeShort(r72);
        r15.writeInt(r82);
        r15.writeInt(r4);
        writeCodes(r14, r15);
        if (this.catches == null) goto L26;
        if (r5 == false) goto L25;
        if (r0 == false) goto L24;
        r15.annotate(2, "  padding: 0");
    L24:
        r15.writeShort(0);
    L25:
        this.catches.writeTo(r14, r15);
    L26:
        if (r0 == true) goto L28;
        return;
    L28:
        if (this.debugInfo == null) goto L32;
        r15.annotate(0, "  debug info");
        this.debugInfo.annotateTo(r14, r15, "    ");
        return;
    L32:
        return;
    L13:
        r82 = r8.getAbsoluteOffset();
        goto L15
    L9:
        r72 = r7.triesSize();
        goto L10
    L5:
        r5 = false;
        goto L6
    }
}
