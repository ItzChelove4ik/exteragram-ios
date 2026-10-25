package com.android.dx.dex.file;

import com.android.dx.dex.code.CatchHandlerList;
import com.android.dx.dex.code.CatchTable;
import com.android.dx.dex.code.DalvCode;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ByteArrayAnnotatedOutput;
import com.android.dx.util.Hex;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class CatchStructs {
    private static final int TRY_ITEM_WRITE_SIZE = 8;
    private final DalvCode code;
    private int encodedHandlerHeaderSize;
    private byte[] encodedHandlers;
    private TreeMap<CatchHandlerList, Integer> handlerOffsets;
    private CatchTable table;

    public CatchStructs(DalvCode r2) {
        this.code = r2;
        this.table = null;
        this.encodedHandlers = null;
        this.encodedHandlerHeaderSize = 0;
        this.handlerOffsets = null;
    }

    private static void annotateAndConsumeHandlers(CatchHandlerList r1, int r2, int r3, String r4, PrintWriter r5, AnnotatedOutput r6) {
        String r12 = r1.toHuman(r4, Hex.u2(r2) + ": ");
        if (r5 == null) goto L5;
        r5.println(r12);
    L5:
        r6.annotate(r3, r12);
    }

    private void annotateEntries(String r15, PrintWriter r16, AnnotatedOutput r17) {
        PrintWriter r4 = r16;
        AnnotatedOutput r5 = r17;
        finishProcessingIfNecessary();
        int r1 = 0;
        if (r5 == null) goto L5;
        boolean r2 = true;
    L6:
        if (r2 == false) goto L8;
        int r3 = 6;
    L9:
        if (r2 == false) goto L11;
        int r6 = 2;
    L12:
        int r7 = this.table.size();
        String r8 = r15 + "  ";
        if (r2 == false) goto L15;
        r5.annotate(0, r15 + "tries:");
    L16:
        int r9 = 0;
    L17:
        if (r9 >= r7) goto L23;
        CatchTable.Entry r10 = this.table.get(r9);
        CatchHandlerList r11 = r10.getHandlers();
        String r102 = r8 + "try " + Hex.u2or4(r10.getStart()) + ".." + Hex.u2or4(r10.getEnd());
        String r112 = r11.toHuman(r8, _UrlKt.FRAGMENT_ENCODE_SET);
        if (r2 == false) goto L21;
        r5.annotate(r3, r102);
        r5.annotate(r6, r112);
    L22:
        r9 = r9 + 1;
        goto L17
    L21:
        r4.println(r102);
        r4.println(r112);
        goto L22
    L23:
        if (r2 == true) goto L25;
        return;
    L25:
        r5.annotate(0, r15 + "handlers:");
        r5.annotate(this.encodedHandlerHeaderSize, r8 + "size: " + Hex.u2(this.handlerOffsets.size()));
        Iterator<Map.Entry<CatchHandlerList, Integer>> r62 = this.handlerOffsets.entrySet().iterator();
        CatchHandlerList r0 = null;
    L27:
        if (r62.hasNext() == false) goto L33;
        Map.Entry<CatchHandlerList, Integer> r22 = r62.next();
        CatchHandlerList r72 = r22.getKey();
        int r92 = r22.getValue().intValue();
        if (r0 == null) goto L31;
        String r32 = r8;
        annotateAndConsumeHandlers(r0, r1, r92 - r1, r32, r4, r5);
    L32:
        r4 = r16;
        r5 = r17;
        r8 = r32;
        r0 = r72;
        r1 = r92;
        goto L27
    L31:
        r32 = r8;
        goto L32
    L33:
        annotateAndConsumeHandlers(r0, r1, this.encodedHandlers.length - r1, r8, r16, r17);
        return;
    L15:
        r4.println(r15 + "tries:");
        goto L16
    L11:
        r6 = 0;
        goto L12
    L8:
        r3 = 0;
        goto L9
    L5:
        r2 = false;
        goto L6
    }

    private void finishProcessingIfNecessary() {
        if (this.table != null) goto L6;
        this.table = this.code.getCatches();
        return;
    }

    public void debugPrint(PrintWriter r2, String r3) {
        annotateEntries(r3, r2, null);
    }

    public void encode(DexFile r10) {
        finishProcessingIfNecessary();
        TypeIdsSection r102 = r10.getTypeIds();
        int r0 = this.table.size();
        this.handlerOffsets = new TreeMap();
        int r2 = 0;
    L3:
        TreeMap<CatchHandlerList, Integer> r3 = this.handlerOffsets;
        if (r2 >= r0) goto L7;
        r3.put(this.table.get(r2).getHandlers(), null);
        r2 = r2 + 1;
        goto L3
    L7:
        if (r3.size() > 65535) goto L22;
        ByteArrayAnnotatedOutput r02 = new ByteArrayAnnotatedOutput();
        this.encodedHandlerHeaderSize = r02.writeUleb128(this.handlerOffsets.size());
        Iterator<Map.Entry<CatchHandlerList, Integer>> r22 = this.handlerOffsets.entrySet().iterator();
    L10:
        if (r22.hasNext() == false) goto L20;
        Map.Entry<CatchHandlerList, Integer> r32 = r22.next();
        CatchHandlerList r4 = r32.getKey();
        int r5 = r4.size();
        boolean r6 = r4.catchesAll();
        r32.setValue(Integer.valueOf(r02.getCursor()));
        if (r6 == false) goto L14;
        r02.writeSleb128(-(r5 - 1));
        r5 = r5 - 1;
    L15:
        int r33 = 0;
    L16:
        if (r33 >= r5) goto L18;
        CatchHandlerList.Entry r7 = r4.get(r33);
        r02.writeUleb128(r102.indexOf(r7.getExceptionType()));
        r02.writeUleb128(r7.getHandler());
        r33 = r33 + 1;
        goto L16
    L18:
        if (r6 == false) goto L10;
        r02.writeUleb128(r4.get(r5).getHandler());
        goto L10
    L14:
        r02.writeSleb128(r5);
        goto L15
    L20:
        this.encodedHandlers = r02.toByteArray();
        return;
    L22:
        ByteString$$ExternalSyntheticBUOutline0.m("too many catch handlers");
    }

    public int triesSize() {
        finishProcessingIfNecessary();
        return this.table.size();
    }

    public int writeSize() {
        return (triesSize() * 8) + this.encodedHandlers.length;
    }

    public void writeTo(DexFile r7, AnnotatedOutput r8) {
        finishProcessingIfNecessary();
        if (r8.annotates() == false) goto L5;
        annotateEntries("  ", null, r8);
    L5:
        int r72 = this.table.size();
        int r0 = 0;
    L6:
        if (r0 >= r72) goto L12;
        CatchTable.Entry r1 = this.table.get(r0);
        int r2 = r1.getStart();
        int r3 = r1.getEnd();
        int r4 = r3 - r2;
        if (r4 >= 65536) goto L11;
        r8.writeInt(r2);
        r8.writeShort(r4);
        r8.writeShort(this.handlerOffsets.get(r1.getHandlers()).intValue());
        r0 = r0 + 1;
        goto L6
    L11:
        throw new UnsupportedOperationException("bogus exception range: " + Hex.u4(r2) + ".." + Hex.u4(r3));
    L12:
        r8.write(this.encodedHandlers);
    }
}
