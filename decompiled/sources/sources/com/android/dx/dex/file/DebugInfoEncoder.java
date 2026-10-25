package com.android.dx.dex.file;

import com.android.dex.util.ExceptionWithContext;
import com.android.dx.dex.code.LocalList;
import com.android.dx.dex.code.PositionList;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class DebugInfoEncoder {
    private static final boolean DEBUG = false;
    private int address;
    private AnnotatedOutput annotateTo;
    private final int codeSize;
    private PrintWriter debugPrint;
    private final Prototype desc;
    private final DexFile file;
    private final boolean isStatic;
    private final LocalList.Entry[] lastEntryForReg;
    private int line;
    private final LocalList locals;
    private final ByteArrayAnnotatedOutput output;
    private final PositionList positions;
    private String prefix;
    private final int regSize;
    private boolean shouldConsume;

    /* renamed from: com.android.dx.dex.file.DebugInfoEncoder$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<PositionList.Entry> {
        final /* synthetic */ DebugInfoEncoder this$0;

        public AnonymousClass1(DebugInfoEncoder r1) {
            this.this$0 = r1;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(PositionList.Entry r1, PositionList.Entry r2) {
            return r1.getAddress() - r2.getAddress();
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(PositionList.Entry r1, PositionList.Entry r2) {
            return compare2(r1, r2);
        }

        @Override // java.util.Comparator
        public boolean equals(Object r1) {
            if (r1 != this) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: com.android.dx.dex.file.DebugInfoEncoder$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<LocalList.Entry> {
        final /* synthetic */ DebugInfoEncoder this$0;

        public AnonymousClass2(DebugInfoEncoder r1) {
            this.this$0 = r1;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(LocalList.Entry r1, LocalList.Entry r2) {
            return r1.getRegister() - r2.getRegister();
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(LocalList.Entry r1, LocalList.Entry r2) {
            return compare2(r1, r2);
        }

        @Override // java.util.Comparator
        public boolean equals(Object r1) {
            if (r1 != this) goto L5;
            return true;
        L5:
            return false;
        }
    }

    public DebugInfoEncoder(PositionList r2, LocalList r3, DexFile r4, int r5, int r6, boolean r7, CstMethodRef r8) {
        this.address = 0;
        this.line = 1;
        this.positions = r2;
        this.locals = r3;
        this.file = r4;
        this.desc = r8.getPrototype();
        this.isStatic = r7;
        this.codeSize = r5;
        this.regSize = r6;
        this.output = new ByteArrayAnnotatedOutput();
        this.lastEntryForReg = new LocalList.Entry[r6];
    }

    private void annotate(int r3, String r4) {
        if (this.prefix == null) goto L5;
        r4 = this.prefix + r4;
    L5:
        AnnotatedOutput r0 = this.annotateTo;
        if (r0 != null) goto L8;
    L12:
        PrintWriter r2 = this.debugPrint;
        if (r2 == null) goto L16;
        r2.println(r4);
        return;
    L16:
        return;
    L8:
        if (this.shouldConsume == true) goto L11;
        r3 = 0;
    L11:
        r0.annotate(r3, r4);
        goto L12
    }

    private ArrayList<PositionList.Entry> buildSortedPositions() {
        PositionList r0 = this.positions;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        ArrayList<PositionList.Entry> r2 = new ArrayList(r02);
    L7:
        if (r1 >= r02) goto L9;
        r2.add(this.positions.get(r1));
        r1 = r1 + 1;
        goto L7
    L9:
        Collections.sort(r2, new AnonymousClass1(this));
        return r2;
    L5:
        r02 = r0.size();
        goto L6
    }

    private static int computeOpcode(int r2, int r3) {
        if (r2 >= (-4)) goto L5;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("Parameter out of range");
        return 0;
    L5:
        if (r2 > 10) goto L8;
        return ((r2 - (-4)) + (r3 * 15)) + 10;
    }

    private byte[] convert0() {
        ArrayList<PositionList.Entry> r0 = buildSortedPositions();
        emitHeader(r0, extractMethodArguments());
        this.output.writeByte(7);
        if (this.annotateTo == null) goto L5;
    L6:
        annotate(1, String.format("%04x: prologue end", new Object[]{Integer.valueOf(this.address)}));
    L7:
        int r1 = r0.size();
        int r2 = this.locals.size();
        int r3 = 0;
        int r4 = 0;
    L8:
        r3 = emitLocalsAtAddress(r3);
        r4 = emitPositionsAtAddress(r4, r0);
        if (r3 >= r2) goto L11;
        int r6 = this.locals.get(r3).getAddress();
    L12:
        if (r4 >= r1) goto L14;
        int r7 = r0.get(r4).getAddress();
    L15:
        int r8 = Math.min(r7, r6);
        if (r8 == Integer.MAX_VALUE) goto L22;
        if (r8 != this.codeSize) goto L24;
        if (r6 != Integer.MAX_VALUE) goto L24;
        if (r7 == Integer.MAX_VALUE) goto L22;
    L24:
        if (r8 == r7) goto L25;
        emitAdvancePc(r8 - this.address);
        goto L8
    L25:
        emitPosition(r0.get(r4));
        r4 = r4 + 1;
    L22:
        emitEndSequence();
        return this.output.toByteArray();
    L14:
        r7 = Integer.MAX_VALUE;
        goto L15
    L11:
        r6 = Integer.MAX_VALUE;
        goto L12
    L5:
        if (this.debugPrint == null) goto L7;
        goto L6
    }

    private void emitAdvanceLine(int r4) {
        int r0 = this.output.getCursor();
        this.output.writeByte(2);
        this.output.writeSleb128(r4);
        this.line += r4;
        if (this.annotateTo == null) goto L5;
    L8:
        annotate(this.output.getCursor() - r0, String.format("line = %d", new Object[]{Integer.valueOf(this.line)}));
        return;
    L5:
        if (this.debugPrint != null) goto L8;
    }

    private void emitAdvancePc(int r4) {
        int r0 = this.output.getCursor();
        this.output.writeByte(1);
        this.output.writeUleb128(r4);
        this.address += r4;
        if (this.annotateTo == null) goto L5;
    L8:
        annotate(this.output.getCursor() - r0, String.format("%04x: advance pc", new Object[]{Integer.valueOf(this.address)}));
        return;
    L5:
        if (this.debugPrint != null) goto L8;
    }

    private void emitEndSequence() {
        this.output.writeByte(0);
        if (this.annotateTo == null) goto L5;
    L8:
        annotate(1, "end sequence");
        return;
    L5:
        if (this.debugPrint != null) goto L8;
    }

    private void emitHeader(ArrayList<PositionList.Entry> r13, ArrayList<LocalList.Entry> r14) {
        int r1 = 0;
        if (this.annotateTo == null) goto L5;
    L8:
        boolean r0 = true;
    L9:
        int r2 = this.output.getCursor();
        if (r13.size() <= 0) goto L12;
        this.line = r13.get(0).getPosition().getLine();
    L12:
        this.output.writeUleb128(this.line);
        if (r0 == false) goto L15;
        annotate(this.output.getCursor() - r2, "line_start: " + this.line);
    L15:
        int r132 = getParamBase();
        StdTypeList r22 = this.desc.getParameterTypes();
        int r3 = r22.size();
        if (this.isStatic == true) goto L23;
        int r4 = r14.size();
        int r5 = 0;
    L18:
        if (r5 >= r4) goto L22;
        LocalList.Entry r6 = r14.get(r5);
        r5 = r5 + 1;
        LocalList.Entry r62 = r6;
        if (r132 != r62.getRegister()) goto L18;
        this.lastEntryForReg[r132] = r62;
    L22:
        r132 = r132 + 1;
    L23:
        int r42 = this.output.getCursor();
        this.output.writeUleb128(r3);
        if (r0 == false) goto L26;
        annotate(this.output.getCursor() - r42, String.format("parameters_size: %04x", new Object[]{Integer.valueOf(r3)}));
    L26:
        int r43 = 0;
    L27:
        if (r43 >= r3) goto L50;
        Type r52 = r22.get(r43);
        int r63 = this.output.getCursor();
        int r7 = r14.size();
        int r8 = 0;
    L30:
        if (r8 >= r7) goto L38;
        LocalList.Entry r10 = r14.get(r8);
        r8 = r8 + 1;
        LocalList.Entry r102 = r10;
        if (r132 != r102.getRegister()) goto L30;
        if (r102.getSignature() == null) goto L36;
        emitStringIndex(null);
    L37:
        this.lastEntryForReg[r132] = r102;
    L39:
        if (r102 != null) goto L41;
        emitStringIndex(null);
    L41:
        if (r0 == false) goto L49;
        if (r102 != null) goto L44;
    L47:
        String r72 = "<unnamed>";
    L48:
        annotate(this.output.getCursor() - r63, "parameter " + r72 + " v" + r132);
        goto L49
    L44:
        if (r102.getSignature() != null) goto L47;
        r72 = r102.getName().toHuman();
    L49:
        r132 = r132 + r52.getCategory();
        r43 = r43 + 1;
        goto L27
    L36:
        emitStringIndex(r102.getName());
        goto L37
    L38:
        r102 = null;
        goto L39
    L50:
        LocalList.Entry[] r133 = this.lastEntryForReg;
        int r142 = r133.length;
    L51:
        if (r1 >= r142) goto L59;
        LocalList.Entry r02 = r133[r1];
        if (r02 == null) goto L58;
        if (r02.getSignature() == null) goto L58;
        emitLocalStartExtended(r02);
    L58:
        r1 = r1 + 1;
        goto L51
    L59:
        return;
    L5:
        if (this.debugPrint != null) goto L8;
        r0 = false;
        goto L9
    }

    private void emitLocalEnd(LocalList.Entry r4) {
        int r0 = this.output.getCursor();
        this.output.writeByte(5);
        this.output.writeUleb128(r4.getRegister());
        if (this.annotateTo == null) goto L5;
    L8:
        annotate(this.output.getCursor() - r0, String.format("%04x: -local %s", new Object[]{Integer.valueOf(this.address), entryAnnotationString(r4)}));
        return;
    L5:
        if (this.debugPrint != null) goto L8;
    }

    private void emitLocalRestart(LocalList.Entry r4) {
        int r0 = this.output.getCursor();
        this.output.writeByte(6);
        emitUnsignedLeb128(r4.getRegister());
        if (this.annotateTo == null) goto L5;
    L8:
        annotate(this.output.getCursor() - r0, String.format("%04x: +local restart %s", new Object[]{Integer.valueOf(this.address), entryAnnotationString(r4)}));
        return;
    L5:
        if (this.debugPrint != null) goto L8;
    }

    private void emitLocalStart(LocalList.Entry r4) {
        if (r4.getSignature() == null) goto L6;
        emitLocalStartExtended(r4);
        return;
    L6:
        int r0 = this.output.getCursor();
        this.output.writeByte(3);
        emitUnsignedLeb128(r4.getRegister());
        emitStringIndex(r4.getName());
        emitTypeIndex(r4.getType());
        if (this.annotateTo == null) goto L9;
    L12:
        annotate(this.output.getCursor() - r0, String.format("%04x: +local %s", new Object[]{Integer.valueOf(this.address), entryAnnotationString(r4)}));
        return;
    L9:
        if (this.debugPrint != null) goto L12;
    }

    private void emitLocalStartExtended(LocalList.Entry r4) {
        int r0 = this.output.getCursor();
        this.output.writeByte(4);
        emitUnsignedLeb128(r4.getRegister());
        emitStringIndex(r4.getName());
        emitTypeIndex(r4.getType());
        emitStringIndex(r4.getSignature());
        if (this.annotateTo == null) goto L5;
    L8:
        annotate(this.output.getCursor() - r0, String.format("%04x: +localx %s", new Object[]{Integer.valueOf(this.address), entryAnnotationString(r4)}));
        return;
    L5:
        if (this.debugPrint != null) goto L8;
    }

    private int emitLocalsAtAddress(int r6) {
        int r0 = this.locals.size();
    L3:
        if (r6 >= r0) goto L24;
        if (this.locals.get(r6).getAddress() != this.address) goto L24;
        int r2 = r6 + 1;
        LocalList.Entry r62 = this.locals.get(r6);
        int r1 = r62.getRegister();
        LocalList.Entry[] r3 = this.lastEntryForReg;
        LocalList.Entry r4 = r3[r1];
        if (r62 == r4) goto L23;
        r3[r1] = r62;
        if (r62.isStart() == false) goto L21;
        if (r4 != null) goto L13;
    L19:
        emitLocalStart(r62);
        goto L23
    L13:
        if (r62.matches(r4) == false) goto L19;
        if (r4.isStart() == true) goto L17;
        emitLocalRestart(r62);
        goto L23
    L17:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        return 0;
    L21:
        if (r62.getDisposition() == LocalList.Disposition.END_REPLACED) goto L23;
        emitLocalEnd(r62);
    L23:
        r6 = r2;
    L24:
        return r6;
    }

    private void emitPosition(PositionList.Entry r5) {
        int r0 = r5.getPosition().getLine();
        int r52 = r5.getAddress();
        int r02 = r0 - this.line;
        int r53 = r52 - this.address;
        if (r53 < 0) goto L24;
        int r2 = 0;
        if (r02 >= (-4)) goto L7;
    L8:
        emitAdvanceLine(r02);
        r02 = 0;
    L9:
        int r1 = computeOpcode(r02, r53);
        if ((r1 & (-256)) <= 0) goto L15;
        emitAdvancePc(r53);
        r1 = computeOpcode(r02, 0);
        if ((r1 & (-256)) <= 0) goto L14;
        emitAdvanceLine(r02);
        r1 = computeOpcode(0, 0);
        r53 = 0;
    L16:
        this.output.writeByte(r1);
        this.line += r2;
        int r03 = this.address + r53;
        this.address = r03;
        if (this.annotateTo == null) goto L19;
    L22:
        annotate(1, String.format("%04x: line %d", new Object[]{Integer.valueOf(r03), Integer.valueOf(this.line)}));
        return;
    L19:
        if (this.debugPrint != null) goto L22;
        return;
    L14:
        r53 = 0;
    L15:
        r2 = r02;
        goto L16
    L7:
        if (r02 <= 10) goto L9;
    L24:
        GlShader$$ExternalSyntheticBUOutline1.m("Position entries must be in ascending address order");
    }

    private int emitPositionsAtAddress(int r4, ArrayList<PositionList.Entry> r5) {
        int r0 = r5.size();
    L3:
        if (r4 >= r0) goto L7;
        if (r5.get(r4).getAddress() != this.address) goto L7;
        int r1 = r4 + 1;
        emitPosition(r5.get(r4));
        r4 = r1;
    L7:
        return r4;
    }

    private void emitStringIndex(CstString r2) {
        if (r2 == null) goto L8;
        DexFile r0 = this.file;
        if (r0 == null) goto L8;
        this.output.writeUleb128(r0.getStringIds().indexOf(r2) + 1);
        return;
    L8:
        this.output.writeUleb128(0);
    }

    private void emitTypeIndex(CstType r2) {
        if (r2 == null) goto L8;
        DexFile r0 = this.file;
        if (r0 == null) goto L8;
        this.output.writeUleb128(r0.getTypeIds().indexOf(r2) + 1);
        return;
    L8:
        this.output.writeUleb128(0);
    }

    private void emitUnsignedLeb128(int r1) {
        if (r1 < 0) goto L5;
        this.output.writeUleb128(r1);
        return;
    L5:
        MathProcessor$$ExternalSyntheticBUOutline0.m("Signed value where unsigned required: ", r1);
    }

    private String entryAnnotationString(LocalList.Entry r4) {
        StringBuilder r3 = new StringBuilder(RegisterSpec.PREFIX);
        r3.append(r4.getRegister());
        r3.append(' ');
        CstString r1 = r4.getName();
        if (r1 != null) goto L5;
        r3.append("null");
    L6:
        r3.append(' ');
        CstType r12 = r4.getType();
        if (r12 != null) goto L9;
        r3.append("null");
    L10:
        CstString r42 = r4.getSignature();
        if (r42 == null) goto L14;
        r3.append(' ');
        r3.append(r42.toHuman());
    L14:
        return r3.toString();
    L9:
        r3.append(r12.toHuman());
        goto L10
    L5:
        r3.append(r1.toHuman());
        goto L6
    }

    private ArrayList<LocalList.Entry> extractMethodArguments() {
        ArrayList<LocalList.Entry> r0 = new ArrayList(this.desc.getParameterTypes().size());
        int r1 = getParamBase();
        BitSet r2 = new BitSet(this.regSize - r1);
        int r3 = this.locals.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L12;
        LocalList.Entry r5 = this.locals.get(r4);
        int r6 = r5.getRegister();
        if (r6 < r1) goto L11;
        int r62 = r6 - r1;
        if (r2.get(r62) == true) goto L11;
        r2.set(r62);
        r0.add(r5);
    L11:
        r4 = r4 + 1;
        goto L3
    L12:
        Collections.sort(r0, new AnonymousClass2(this));
        return r0;
    }

    private int getParamBase() {
        return (this.regSize - this.desc.getParameterTypes().getWordCount()) - (!this.isStatic ? 1 : 0);
    }

    public byte[] convert() {
        return convert0();
    L4:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while encoding debug info");
    }

    public byte[] convertAndAnnotate(String r1, PrintWriter r2, AnnotatedOutput r3, boolean r4) {
        this.prefix = r1;
        this.debugPrint = r2;
        this.annotateTo = r3;
        this.shouldConsume = r4;
        return convert();
    }
}
