package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.Leb128;
import com.android.dex.util.ByteArrayByteInput;
import com.android.dex.util.ByteInput;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.dex.code.DalvInsnList;
import com.android.dx.dex.code.DalvInsnList$$ExternalSyntheticBUOutline0;
import com.android.dx.dex.code.LocalList;
import com.android.dx.dex.code.PositionList;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class DebugInfoDecoder {
    private int address;
    private final int codesize;
    private final Prototype desc;
    private final byte[] encoded;
    private final DexFile file;
    private final boolean isStatic;
    private final LocalEntry[] lastEntryForReg;
    private int line;
    private final ArrayList<LocalEntry> locals;
    private final ArrayList<PositionEntry> positions;
    private final int regSize;
    private final int thisStringIdx;

    public static class LocalEntry {
        public int address;
        public boolean isStart;
        public int nameIndex;
        public int reg;
        public int signatureIndex;
        public int typeIndex;

        public LocalEntry(int r1, boolean r2, int r3, int r4, int r5, int r6) {
            this.address = r1;
            this.isStart = r2;
            this.reg = r3;
            this.nameIndex = r4;
            this.typeIndex = r5;
            this.signatureIndex = r6;
        }

        public String toString() {
            Integer r1 = Integer.valueOf(this.address);
            if (this.isStart == false) goto L6;
            String r0 = "start";
        L5:
            String r2 = r0;
            return String.format("[%x %s v%d %04x %04x %04x]", new Object[]{r1, r2, Integer.valueOf(this.reg), Integer.valueOf(this.nameIndex), Integer.valueOf(this.typeIndex), Integer.valueOf(this.signatureIndex)});
        L6:
            r0 = "end";
            goto L5
        }
    }

    public static class PositionEntry {
        public int address;
        public int line;

        public PositionEntry(int r1, int r2) {
            this.address = r1;
            this.line = r2;
        }
    }

    public DebugInfoDecoder(byte[] r2, int r3, int r4, boolean r5, CstMethodRef r6, DexFile r7) {
        this.line = 1;
        this.address = 0;
        if (r2 == null) goto L10;
        this.encoded = r2;
        this.isStatic = r5;
        this.desc = r6.getPrototype();
        this.file = r7;
        this.regSize = r4;
        this.positions = new ArrayList();
        this.locals = new ArrayList();
        this.codesize = r3;
        this.lastEntryForReg = new LocalEntry[r4];
        int r22 = r7.getStringIds().indexOf(new CstString("this"));     // Catch: IllegalArgumentException -> L7
    L8:
        this.thisStringIdx = r22;
        return;
    L7:
        r22 = -1;
        goto L8
    L10:
        f$$ExternalSyntheticBUOutline2.m("encoded == null");
        throw null;
    }

    private void decode0() {
        ByteArrayByteInput r0 = new ByteArrayByteInput(this.encoded);
        this.line = Leb128.readUnsignedLeb128(r0);
        int r1 = Leb128.readUnsignedLeb128(r0);
        StdTypeList r2 = this.desc.getParameterTypes();
        int r6 = getParamBase();
        if (r1 == r2.size()) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("Mismatch between parameters_size and prototype");
        return;
    L5:
        if (this.isStatic == true) goto L7;
        LocalEntry r3 = new LocalEntry(0, true, r6, this.thisStringIdx, 0, 0);
        this.locals.add(r3);
        this.lastEntryForReg[r6] = r3;
        r6 = r6 + 1;
    L7:
        int r32 = 0;
        int r7 = r6;
    L8:
        if (r32 >= r1) goto L14;
        Type r11 = r2.getType(r32);
        int r8 = readStringIndex(r0);
        if (r8 != (-1)) goto L12;
        LocalEntry r4 = new LocalEntry(0, true, r7, -1, 0, 0);
    L13:
        this.locals.add(r4);
        this.lastEntryForReg[r7] = r4;
        r7 = r7 + r11.getCategory();
        r32 = r32 + 1;
        goto L8
    L12:
        r4 = new LocalEntry(0, true, r7, r8, 0, 0);
    L14:
        int r12 = r0.readByte() & UByte.MAX_VALUE;
        switch(r12) {
            case 0: goto L43;
            case 1: goto L42;
            case 2: goto L41;
            case 3: goto L40;
            case 4: goto L39;
            case 5: goto L30;
            case 6: goto L21;
            case 7: goto L14;
            case 8: goto L14;
            case 9: goto L14;
            default: goto L17;
        };
    L17:
        if (r12 < 10) goto L19;
        int r22 = this.address + ((r12 - 10) / 15);
        this.address = r22;
        int r33 = this.line + ((r1 % 15) - 4);
        this.line = r33;
        this.positions.add(new PositionEntry(r22, r33));
        goto L14
    L19:
        MathProcessor$$ExternalSyntheticBUOutline0.m("Invalid extended opcode encountered ", r12);
        return;
    L21:
        int r5 = Leb128.readUnsignedLeb128(r0);
        LocalEntry r13 = this.lastEntryForReg[r5];     // Catch: NullPointerException -> L28
        if (r13.isStart == true) goto L27;
        LocalEntry r23 = new LocalEntry(this.address, true, r5, r13.nameIndex, r13.typeIndex, 0);     // Catch: NullPointerException -> L28
        this.locals.add(r23);
        this.lastEntryForReg[r5] = r23;
        goto L14
    L27:
        throw new RuntimeException("nonsensical RESTART_LOCAL on live register v" + r5);     // Catch: NullPointerException -> L28
    L28:
        MathProcessor$$ExternalSyntheticBUOutline0.m("Encountered RESTART_LOCAL on new v", r5);
        return;
    L30:
        int r9 = Leb128.readUnsignedLeb128(r0);
        LocalEntry r14 = this.lastEntryForReg[r9];     // Catch: NullPointerException -> L37
        if (r14.isStart == false) goto L36;
        LocalEntry r62 = new LocalEntry(this.address, false, r9, r14.nameIndex, r14.typeIndex, r14.signatureIndex);     // Catch: NullPointerException -> L37
        this.locals.add(r62);
        this.lastEntryForReg[r9] = r62;
        goto L14
    L36:
        throw new RuntimeException("nonsensical END_LOCAL on dead register v" + r9);     // Catch: NullPointerException -> L37
    L37:
        MathProcessor$$ExternalSyntheticBUOutline0.m("Encountered END_LOCAL on new v", r9);
        return;
    L39:
        int r42 = Leb128.readUnsignedLeb128(r0);
        LocalEntry r15 = new LocalEntry(this.address, true, r42, readStringIndex(r0), readStringIndex(r0), readStringIndex(r0));
        this.locals.add(r15);
        this.lastEntryForReg[r42] = r15;
        goto L14
    L40:
        int r82 = Leb128.readUnsignedLeb128(r0);
        LocalEntry r52 = new LocalEntry(this.address, true, r82, readStringIndex(r0), readStringIndex(r0), 0);
        this.locals.add(r52);
        this.lastEntryForReg[r82] = r52;
        goto L14
    L41:
        this.line += Leb128.readSignedLeb128(r0);
        goto L14
    L42:
        this.address += Leb128.readUnsignedLeb128(r0);
        goto L14
    }

    private int getParamBase() {
        return (this.regSize - this.desc.getParameterTypes().getWordCount()) - (!this.isStatic ? 1 : 0);
    }

    private int readStringIndex(ByteInput r1) {
        return Leb128.readUnsignedLeb128(r1) - 1;
    }

    public static void validateEncode(byte[] r8, DexFile r9, CstMethodRef r10, DalvCode r11, boolean r12) {
        PositionList r6 = r11.getPositions();
        LocalList r7 = r11.getLocals();
        DalvInsnList r112 = r11.getInsns();
        validateEncode0(r8, r112.codeSize(), r112.getRegistersSize(), r12, r10, r9, r6, r7);     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        System.err.println("instructions:");
        r112.debugPrint(System.err, "  ", true);
        System.err.println("local list:");
        r7.debugPrint(System.err, "  ");
        throw ExceptionWithContext.withContext(e, "while processing " + r10.toHuman());
    }

    private static void validateEncode0(byte[] r7, int r8, int r9, boolean r10, CstMethodRef r11, DexFile r12, PositionList r13, LocalList r14) {
        DebugInfoDecoder r0 = new DebugInfoDecoder(r7, r8, r9, r10, r11, r12);
        r0.decode();
        List<PositionEntry> r72 = r0.getPositionList();
        if (r72.size() != r13.size()) goto L65;
        Iterator<PositionEntry> r73 = r72.iterator();
    L6:
        if (r73.hasNext() == false) goto L17;
        PositionEntry r82 = r73.next();
        int r92 = r13.size() - 1;
    L8:
        if (r92 < 0) goto L15;
        PositionList.Entry r102 = r13.get(r92);
        if (r82.line != r102.getPosition().getLine()) goto L14;
        if (r82.address == r102.getAddress()) goto L6;
    L14:
        r92 = r92 - 1;
        goto L8
    L15:
        DalvInsnList$$ExternalSyntheticBUOutline0.m("Could not match position entry: ", r82.address, ", ", r82.line);
        return;
    L17:
        List<LocalEntry> r74 = r0.getLocals();
        int r83 = r0.thisStringIdx;
        int r93 = r74.size();
        int r103 = r0.getParamBase();
        int r112 = 0;
        int r122 = 0;
    L18:
        if (r122 >= r93) goto L34;
        LocalEntry r132 = r74.get(r122);
        int r02 = r132.nameIndex;
        if (r02 < 0) goto L22;
        if (r02 == r83) goto L22;
    L33:
        r122 = r122 + 1;
    L22:
        int r03 = r122 + 1;
    L23:
        if (r03 >= r93) goto L33;
        LocalEntry r1 = r74.get(r03);
        if (r1.address != 0) goto L33;
        if (r132.reg != r1.reg) goto L32;
        if (r1.isStart == false) goto L32;
        r74.set(r122, r1);
        r74.remove(r03);
        r93 = r93 - 1;
    L32:
        r03 = r03 + 1;
        goto L23
    L34:
        int r84 = r14.size();
        int r123 = 0;
    L35:
        if (r112 >= r84) goto L64;
        LocalList.Entry r133 = r14.get(r112);
        if (r133.getDisposition() == LocalList.Disposition.END_REPLACED) goto L63;
    L39:
        LocalEntry r04 = r74.get(r123);
        if (r04.nameIndex >= 0) goto L44;
        r123 = r123 + 1;
        if (r123 < r93) goto L39;
    L44:
        int r15 = r04.address;
        if (r04.reg != r133.getRegister()) goto L46;
        if (r04.isStart != r133.isStart()) goto L49;
        if (r15 == r133.getAddress()) goto L62;
        if (r15 != 0) goto L55;
        if (r04.reg >= r103) goto L62;
    L55:
        System.err.println("local address mismatch at orig " + r112 + " / decoded " + r123);
    L56:
        System.err.println("decoded locals:");
        Iterator<LocalEntry> r75 = r74.iterator();
    L58:
        if (r75.hasNext() == false) goto L60;
        LocalEntry r85 = r75.next();
        System.err.println("  " + r85);
        goto L58
    L60:
        GlShader$$ExternalSyntheticBUOutline1.m("local table problem");
        return;
    L62:
        r123 = r123 + 1;
        goto L63
    L49:
        System.err.println("local start/end mismatch at orig " + r112 + " / decoded " + r123);
        goto L56
    L46:
        System.err.println("local register mismatch at orig " + r112 + " / decoded " + r123);
    L63:
        r112 = r112 + 1;
        goto L35
    L64:
        return;
    L65:
        DalvInsnList$$ExternalSyntheticBUOutline0.m("Decoded positions table not same size was ", r72.size(), " expected ", r13.size());
    }

    public void decode() {
        decode0();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        throw ExceptionWithContext.withContext(e, "...while decoding debug info");
    }

    public List<LocalEntry> getLocals() {
        return this.locals;
    }

    public List<PositionEntry> getPositionList() {
        return this.positions;
    }
}
