package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.dex.code.DalvInsnList;
import com.android.dx.dex.code.LocalList;
import com.android.dx.dex.code.PositionList;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.util.AnnotatedOutput;
import java.io.PrintWriter;

/* loaded from: classes4.dex */
public class DebugInfoItem extends OffsettedItem {
    private static final int ALIGNMENT = 1;
    private static final boolean ENABLE_ENCODER_SELF_CHECK = false;
    private final DalvCode code;
    private byte[] encoded;
    private final boolean isStatic;
    private final CstMethodRef ref;

    public DebugInfoItem(DalvCode r3, boolean r4, CstMethodRef r5) {
        super(1, -1);
        if (r3 == null) goto L6;
        this.code = r3;
        this.isStatic = r4;
        this.ref = r5;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("code == null");
        throw null;
    }

    private byte[] encode(DexFile r1, String r2, PrintWriter r3, AnnotatedOutput r4, boolean r5) {
        return encode0(r1, r2, r3, r4, r5);
    }

    private byte[] encode0(DexFile r10, String r11, PrintWriter r12, AnnotatedOutput r13, boolean r14) {
        PositionList r2 = this.code.getPositions();
        LocalList r3 = this.code.getLocals();
        DalvInsnList r0 = this.code.getInsns();
        DebugInfoEncoder r1 = new DebugInfoEncoder(r2, r3, r10, r0.codeSize(), r0.getRegistersSize(), this.isStatic, this.ref);
        if (r12 != null) goto L8;
        if (r13 != null) goto L8;
        return r1.convert();
    L8:
        return r1.convertAndAnnotate(r11, r12, r13, r14);
    }

    @Override // com.android.dx.dex.file.Item
    public void addContents(DexFile r1) {
    }

    public void annotateTo(DexFile r7, AnnotatedOutput r8, String r9) {
        encode(r7, r9, null, r8, false);
    }

    public void debugPrint(PrintWriter r7, String r8) {
        encode(null, r8, r7, null, false);
    }

    @Override // com.android.dx.dex.file.Item
    public ItemType itemType() {
        return ItemType.TYPE_DEBUG_INFO_ITEM;
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void place0(Section r8, int r9) {
        DebugInfoItem r1 = this;
        byte[] r7 = r1.encode(r8.getFile(), null, null, null, false);     // Catch: RuntimeException -> L6
        r1.encoded = r7;     // Catch: RuntimeException -> L6
        r1.setWriteSize(r7.length);     // Catch: RuntimeException -> L6
        return;
    L6:
        e = e;
    L11:
        throw ExceptionWithContext.withContext(e, "...while placing debug info for " + r1.ref.toHuman());
    L8:
        e = e;
        r1 = this;
        goto L11
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public String toHuman() {
        throw new RuntimeException("unsupported");
    }

    @Override // com.android.dx.dex.file.OffsettedItem
    public void writeTo0(DexFile r8, AnnotatedOutput r9) {
        if (r9.annotates() == false) goto L5;
        r9.annotate(offsetString() + " debug info");
        DebugInfoItem r1 = this;
        AnnotatedOutput r5 = r9;
        r1.encode(r8, null, null, r5, true);
    L6:
        r5.write(r1.encoded);
        return;
    L5:
        r1 = this;
        r5 = r9;
        goto L6
    }
}
