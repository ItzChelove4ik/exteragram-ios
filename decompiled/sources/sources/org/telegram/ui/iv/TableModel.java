package org.telegram.ui.iv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes7.dex */
public class TableModel {
    public int[][] anchorC;
    public int[][] anchorR;
    private final ArrayList<TL_iv.pageTableCell> anchorsRowMajor;
    public final TL_iv.pageBlockTable block;
    public int colCount;
    public TL_iv.pageTableCell[][] grid;
    public int rowCount;

    public static /* synthetic */ int $r8$lambda$VLVZrMHqzfOiBifSZE5rLTWaYxE(Object[] r1) {
        return ((Integer) r1[1]).intValue();
    }

    public static /* synthetic */ int $r8$lambda$hUWEgK6NjEiBDVZGfV8M3mrrKHg(IdentityHashMap r0, TL_iv.pageTableCell r1) {
        return ((int[]) r0.get(r1))[1];
    }

    public static /* synthetic */ int $r8$lambda$ux20aP1rLMTcuRT6KS3OttHdySI(TableModel r0, TL_iv.pageTableCell r1, TL_iv.pageTableCell r2) {
        return r0.lambda$mergeCells$0(r1, r2);
    }

    public TableModel(TL_iv.pageBlockTable r2) {
        this.anchorsRowMajor = new ArrayList();
        this.block = r2;
        rebuildFromBlock();
    }

    public static int alignOf(TL_iv.pageTableCell r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        if (r2.align_right == false) goto L10;
        return 2;
    L10:
        if (r2.align_center == false) goto L13;
        return 1;
    L13:
        return 0;
    }

    public static void applyPlainText(TL_iv.pageTableCell r3, String r4) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        if (r4 != null) goto L5;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.text = r4;
        r3.text = r0;
        int r42 = r3.flags;
        int r02 = r42 | 128;
        r3.flags = r02;
        if (r3.colspan <= 1) goto L9;
        int r43 = r42 | 130;
    L8:
        r3.flags = r43;
        int r44 = r3.rowspan;
        int r03 = r3.flags;
        if (r44 <= 1) goto L15;
        int r45 = r03 | 4;
    L13:
        r3.flags = r45;
        return;
    L15:
        r45 = r03 & (-5);
        goto L13
    L9:
        r43 = r02 & (-3);
        goto L8
    }

    public static void applyStyledText(TL_iv.pageTableCell r3, CharSequence r4) {
        r3.text = RichTextStyle.fromSpannable(r4);
        int r42 = r3.flags;
        int r0 = r42 | 128;
        r3.flags = r0;
        if (r3.colspan <= 1) goto L6;
        int r43 = r42 | 130;
    L5:
        r3.flags = r43;
        int r44 = r3.rowspan;
        int r02 = r3.flags;
        if (r44 <= 1) goto L12;
        int r45 = r02 | 4;
    L10:
        r3.flags = r45;
        return;
    L12:
        r45 = r02 & (-5);
        goto L10
    L6:
        r43 = r0 & (-3);
        goto L5
    }

    private static TL_iv.pageTableCell[][] growCols(TL_iv.pageTableCell[][] r5, int r6) {
        TL_iv.pageTableCell[][] r0 = (TL_iv.pageTableCell[][]) Array.newInstance(TL_iv.pageTableCell.class, new int[]{r5.length, r6});
        int r1 = 0;
    L4:
        if (r1 >= r5.length) goto L6;
        TL_iv.pageTableCell[] r2 = r5[r1];
        System.arraycopy(r2, 0, r0[r1], 0, r2.length);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static int[][] growIntCols(int[][] r6, int r7, int r8) {
        int[][] r0 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{r6.length, r7});
        int r1 = 0;
    L4:
        if (r1 >= r6.length) goto L9;
        int[] r3 = r6[r1];
        int r4 = r3.length;
        System.arraycopy(r3, 0, r0[r1], 0, r4);
    L6:
        if (r4 >= r7) goto L8;
        r0[r1][r4] = r8;
        r4 = r4 + 1;
        goto L6
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    private /* synthetic */ int lambda$mergeCells$0(TL_iv.pageTableCell r3, TL_iv.pageTableCell r4) {
        int r0 = anchorRowOf(r3);
        int r1 = anchorRowOf(r4);
        if (r0 == r1) goto L7;
        return Integer.compare(r0, r1);
    L7:
        return Integer.compare(anchorColOf(r3), anchorColOf(r4));
    }

    public static TL_iv.pageTableCell newEmptyCell() {
        TL_iv.pageTableCell r0 = new TL_iv.pageTableCell();
        applyPlainText(r0, _UrlKt.FRAGMENT_ENCODE_SET);
        return r0;
    }

    public static void normalizeForSend(TL_iv.pageBlockTable r8) {
        if (r8 != null) goto L5;
        return;
    L5:
        if (r8.title != null) goto L8;
        r8.title = new TL_iv.textEmpty();
    L8:
        if (r8.rows == null) goto L39;
        int r1 = 0;
    L12:
        if (r1 >= r8.rows.size()) goto L40;
        TL_iv.pageTableRow r2 = r8.rows.get(r1);
        if (r2.cells == null) goto L34;
        int r3 = 0;
    L18:
        if (r3 >= r2.cells.size()) goto L34;
        TL_iv.pageTableCell r4 = r2.cells.get(r3);
        if (r4.text != null) goto L22;
        applyPlainText(r4, _UrlKt.FRAGMENT_ENCODE_SET);
    L23:
        int r5 = r4.colspan;
        int r6 = r4.flags;
        if (r5 <= 1) goto L27;
        int r52 = r6 | 2;
    L26:
        r4.flags = r52;
        int r53 = r4.rowspan;
        int r62 = r4.flags;
        if (r53 <= 1) goto L32;
        int r54 = r62 | 4;
    L31:
        r4.flags = r54;
        r3 = r3 + 1;
        goto L18
    L32:
        r54 = r62 & (-5);
        goto L31
    L27:
        r52 = r6 & (-3);
        goto L26
    L22:
        r4.flags |= 128;
    L34:
        r1 = r1 + 1;
        goto L12
    L40:
        return;
    }

    public static String readPlainText(TL_iv.pageTableCell r0) {
        if (r0 == null) goto L8;
        TL_iv.RichText r02 = r0.text;
        if (r02 != null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return RichTextStyle.plainOf(r02);
    L8:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static CharSequence readStyledText(TL_iv.pageTableCell r0) {
        if (r0 == null) goto L8;
        TL_iv.RichText r02 = r0.text;
        if (r02 != null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return RichTextStyle.toSpannable(r02);
    L8:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private void rebuildAnchorList() {
        this.anchorsRowMajor.clear();
        LinkedHashSet r0 = new LinkedHashSet();
        int r2 = 0;
    L4:
        if (r2 >= this.rowCount) goto L15;
        int r3 = 0;
    L7:
        if (r3 >= this.colCount) goto L14;
        if (isAnchor(r2, r3) == false) goto L13;
        if (r0.add(this.grid[r2][r3]) == false) goto L13;
        this.anchorsRowMajor.add(this.grid[r2][r3]);
    L13:
        r3 = r3 + 1;
        goto L7
    L14:
        r2 = r2 + 1;
        goto L4
    }

    private void rewriteBlockRows(IdentityHashMap<TL_iv.pageTableCell, int[]> r12, int r13) {
        this.block.rows.clear();
        int r1 = 0;
    L3:
        if (r1 >= r13) goto L32;
        TL_iv.pageTableRow r2 = new TL_iv.pageTableRow();
        r2.cells = new ArrayList();
        ArrayList r3 = new ArrayList();
        Iterator<Map.Entry<TL_iv.pageTableCell, int[]>> r4 = r12.entrySet().iterator();
    L6:
        if (r4.hasNext() == false) goto L10;
        Map.Entry<TL_iv.pageTableCell, int[]> r5 = r4.next();
        if (r5.getValue()[0] != r1) goto L6;
        r3.add(r5.getKey());
        goto L6
    L10:
        Collections.sort(r3, Comparator.comparingInt(new TableModel$$ExternalSyntheticLambda1(r12)));
        int r42 = r3.size();
        int r52 = 0;
    L11:
        if (r52 >= r42) goto L31;
        Object r6 = r3.get(r52);
        r52 = r52 + 1;
        TL_iv.pageTableCell r62 = (TL_iv.pageTableCell) r6;
        int[] r7 = r12.get(r62);
        int r8 = r7[2];
        if (r8 > 1) goto L16;
        r8 = 0;
    L16:
        r62.rowspan = r8;
        int r72 = r7[3];
        if (r72 > 1) goto L20;
        r72 = 0;
    L20:
        r62.colspan = r72;
        int r9 = r62.flags;
        if (r8 == 0) goto L24;
        int r82 = r9 | 4;
    L23:
        r62.flags = r82;
        int r83 = r62.flags;
        if (r72 == 0) goto L29;
        int r73 = r83 | 2;
    L28:
        r62.flags = r73;
        r2.cells.add(r62);
        goto L11
    L29:
        r73 = r83 & (-3);
        goto L28
    L24:
        r82 = r9 & (-5);
        goto L23
    L31:
        this.block.rows.add(r2);
        r1 = r1 + 1;
        goto L3
    }

    public static void setAlign(TL_iv.pageTableCell r4, int r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        boolean r0 = false;
        if (r5 != 1) goto L7;
        boolean r2 = true;
    L8:
        r4.align_center = r2;
        if (r5 != 2) goto L11;
        r0 = true;
    L11:
        r4.align_right = r0;
        int r52 = r4.flags;
        if (r2 == false) goto L15;
        int r53 = r52 | 8;
    L14:
        r4.flags = r53;
        int r54 = r4.flags;
        if (r0 == false) goto L21;
        int r55 = r54 | 16;
    L19:
        r4.flags = r55;
        return;
    L21:
        r55 = r54 & (-17);
        goto L19
    L15:
        r53 = r52 & (-9);
        goto L14
    L7:
        r2 = false;
        goto L8
    }

    public static void setHeader(TL_iv.pageTableCell r1, boolean r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        r1.header = r2;
        int r0 = r1.flags;
        if (r2 == false) goto L9;
        int r22 = r0 | 1;
    L7:
        r1.flags = r22;
        return;
    L9:
        r22 = r0 & (-2);
        goto L7
    }

    public static void setVAlign(TL_iv.pageTableCell r4, int r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        boolean r0 = false;
        if (r5 != 1) goto L7;
        boolean r2 = true;
    L8:
        r4.valign_middle = r2;
        if (r5 != 2) goto L11;
        r0 = true;
    L11:
        r4.valign_bottom = r0;
        int r52 = r4.flags;
        if (r2 == false) goto L15;
        int r53 = r52 | 32;
    L14:
        r4.flags = r53;
        int r54 = r4.flags;
        if (r0 == false) goto L21;
        int r55 = r54 | 64;
    L19:
        r4.flags = r55;
        return;
    L21:
        r55 = r54 & (-65);
        goto L19
    L15:
        r53 = r52 & (-33);
        goto L14
    L7:
        r2 = false;
        goto L8
    }

    public static int spanCol(TL_iv.pageTableCell r0) {
        int r02 = r0.colspan;
        if (r02 == 0) goto L5;
        return r02;
    L5:
        return 1;
    }

    public static int spanRow(TL_iv.pageTableCell r0) {
        int r02 = r0.rowspan;
        if (r02 == 0) goto L5;
        return r02;
    L5:
        return 1;
    }

    public static int valignOf(TL_iv.pageTableCell r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        if (r2.valign_bottom == false) goto L10;
        return 2;
    L10:
        if (r2.valign_middle == false) goto L13;
        return 1;
    L13:
        return 0;
    }

    public void addColumn() {
        if (this.block.rows.isEmpty() == false) goto L5;
        TL_iv.pageTableRow r0 = new TL_iv.pageTableRow();
        ArrayList<TL_iv.pageTableCell> r1 = new ArrayList();
        r0.cells = r1;
        r1.add(newEmptyCell());
        this.block.rows.add(r0);
    L11:
        rebuildFromBlock();
        return;
    L5:
        ArrayList<TL_iv.pageTableRow> r02 = this.block.rows;
        int r12 = r02.size();
        int r2 = 0;
    L6:
        if (r2 >= r12) goto L11;
        TL_iv.pageTableRow r3 = r02.get(r2);
        r2 = r2 + 1;
        TL_iv.pageTableRow r32 = r3;
        if (r32.cells != null) goto L10;
        r32.cells = new ArrayList();
    L10:
        r32.cells.add(newEmptyCell());
        goto L6
    }

    public void addRow() {
        TL_iv.pageTableRow r0 = new TL_iv.pageTableRow();
        r0.cells = new ArrayList();
        int r1 = Math.max(this.colCount, 1);
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.cells.add(newEmptyCell());
        r2 = r2 + 1;
        goto L3
    L5:
        this.block.rows.add(r0);
        rebuildFromBlock();
    }

    public int anchorColOf(TL_iv.pageTableCell r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.rowCount) goto L14;
        int r2 = 0;
    L7:
        if (r2 >= this.colCount) goto L13;
        if (this.grid[r1][r2] == r5) goto L11;
        r2 = r2 + 1;
        goto L7
    L11:
        return this.anchorC[r1][r2];
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        return -1;
    }

    public int anchorRowOf(TL_iv.pageTableCell r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.rowCount) goto L14;
        int r2 = 0;
    L7:
        if (r2 >= this.colCount) goto L13;
        if (this.grid[r1][r2] == r5) goto L11;
        r2 = r2 + 1;
        goto L7
    L11:
        return this.anchorR[r1][r2];
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        return -1;
    }

    public List<TL_iv.pageTableCell> anchors() {
        return this.anchorsRowMajor;
    }

    public boolean deleteColumns(Set<Integer> r18) {
        if (r18 != null) goto L5;
        return false;
    L5:
        if (r18.isEmpty() == true) goto L61;
        boolean[] r2 = new boolean[this.colCount];
        Iterator<Integer> r3 = r18.iterator();
    L8:
        boolean r5 = true;
        if (r3.hasNext() == false) goto L15;
        int r4 = r3.next().intValue();
        if (r4 < 0) goto L8;
        if (r4 >= this.colCount) goto L8;
        r2[r4] = true;
        goto L8
    L15:
        int[] r32 = new int[this.colCount];
        int r42 = 0;
        int r6 = 0;
    L17:
        if (r42 >= this.colCount) goto L22;
        r32[r42] = r6;
        if (r2[r42] == true) goto L21;
        r6 = r6 + 1;
    L21:
        r42 = r42 + 1;
        goto L17
    L22:
        if (r6 != 0) goto L25;
        this.block.rows.clear();
        rebuildFromBlock();
        return true;
    L25:
        IdentityHashMap<TL_iv.pageTableCell, int[]> r43 = new IdentityHashMap();
        ArrayList<TL_iv.pageTableCell> r62 = this.anchorsRowMajor;
        int r7 = r62.size();
        int r8 = 0;
    L26:
        if (r8 >= r7) goto L41;
        TL_iv.pageTableCell r9 = r62.get(r8);
        r8 = r8 + 1;
        TL_iv.pageTableCell r92 = r9;
        int r10 = anchorRowOf(r92);
        int r11 = anchorColOf(r92);
        int r12 = spanRow(r92);
        int r13 = spanCol(r92);
        int r14 = -1;
        boolean r182 = r5;
        int r15 = r11;
        int r1 = 0;
    L29:
        if (r15 >= (r11 + r13)) goto L38;
        if (r15 >= this.colCount) goto L38;
        if (r2[r15] == true) goto L37;
        if (r14 >= 0) goto L36;
        r14 = r15;
    L36:
        r1 = r1 + 1;
    L37:
        r15 = r15 + 1;
    L38:
        if (r14 < 0) goto L39;
        r43.put(r92, new int[]{r10, r32[r14], r12, r1});
    L39:
        r5 = r182;
        goto L26
    L41:
        boolean r183 = r5;
        rewriteBlockRows(r43, this.rowCount);
        rebuildFromBlock();
        return r183;
    L61:
        return false;
    }

    public boolean deleteRows(Set<Integer> r19) {
        if (r19 != null) goto L5;
        return false;
    L5:
        if (r19.isEmpty() == true) goto L61;
        boolean[] r2 = new boolean[this.rowCount];
        Iterator<Integer> r3 = r19.iterator();
    L8:
        boolean r5 = true;
        if (r3.hasNext() == false) goto L15;
        int r4 = r3.next().intValue();
        if (r4 < 0) goto L8;
        if (r4 >= this.rowCount) goto L8;
        r2[r4] = true;
        goto L8
    L15:
        int[] r32 = new int[this.rowCount];
        int r42 = 0;
        int r6 = 0;
    L17:
        if (r42 >= this.rowCount) goto L22;
        r32[r42] = r6;
        if (r2[r42] == true) goto L21;
        r6 = r6 + 1;
    L21:
        r42 = r42 + 1;
        goto L17
    L22:
        if (r6 != 0) goto L25;
        this.block.rows.clear();
        rebuildFromBlock();
        return true;
    L25:
        IdentityHashMap<TL_iv.pageTableCell, int[]> r43 = new IdentityHashMap();
        ArrayList<TL_iv.pageTableCell> r7 = this.anchorsRowMajor;
        int r8 = r7.size();
        int r9 = 0;
    L26:
        if (r9 >= r8) goto L41;
        TL_iv.pageTableCell r10 = r7.get(r9);
        r9 = r9 + 1;
        TL_iv.pageTableCell r102 = r10;
        int r11 = anchorRowOf(r102);
        int r12 = anchorColOf(r102);
        int r13 = spanRow(r102);
        int r14 = spanCol(r102);
        int r15 = -1;
        boolean[] r17 = r2;
        boolean r192 = r5;
        int r1 = r11;
        int r52 = 0;
    L29:
        if (r1 >= (r11 + r13)) goto L38;
        if (r1 >= this.rowCount) goto L38;
        if (r17[r1] == true) goto L37;
        if (r15 >= 0) goto L36;
        r15 = r1;
    L36:
        r52 = r52 + 1;
    L37:
        r1 = r1 + 1;
    L38:
        if (r15 < 0) goto L39;
        r43.put(r102, new int[]{r32[r15], r12, r52, r14});
    L39:
        r5 = r192;
        r2 = r17;
        goto L26
    L41:
        boolean r193 = r5;
        rewriteBlockRows(r43, r6);
        rebuildFromBlock();
        return r193;
    L61:
        return false;
    }

    public int flatIndexOfAnchor(TL_iv.pageTableCell r1) {
        return this.anchorsRowMajor.indexOf(r1);
    }

    public boolean insertColumnAt(int r15) {
        if (this.rowCount == 0) goto L38;
        int r0 = this.colCount;
        if (r0 == 0) goto L38;
        int r2 = 0;
        if (r15 >= 0) goto L10;
        r15 = 0;
    L10:
        if (r15 > r0) goto L13;
        r0 = r15;
    L13:
        IdentityHashMap<TL_iv.pageTableCell, int[]> r152 = new IdentityHashMap();
        boolean[] r3 = new boolean[this.rowCount];
        ArrayList<TL_iv.pageTableCell> r4 = this.anchorsRowMajor;
        int r5 = r4.size();
        int r6 = 0;
    L14:
        if (r6 >= r5) goto L29;
        TL_iv.pageTableCell r7 = r4.get(r6);
        r6 = r6 + 1;
        TL_iv.pageTableCell r72 = r7;
        int r8 = anchorRowOf(r72);
        int r9 = anchorColOf(r72);
        int r10 = spanRow(r72);
        int r11 = spanCol(r72);
        if (r9 < r0) goto L18;
        int r12 = r9 + 1;
    L19:
        if (r9 >= r0) goto L28;
        if ((r9 + r11) <= r0) goto L28;
        r11 = r11 + 1;
        int r92 = r8;
    L24:
        if (r92 >= (r8 + r10)) goto L28;
        if (r92 >= this.rowCount) goto L28;
        r3[r92] = true;
        r92 = r92 + 1;
    L28:
        r152.put(r72, new int[]{r8, r12, r10, r11});
        goto L14
    L18:
        r12 = r9;
    L29:
        int r42 = this.rowCount;
        if (r2 >= r42) goto L36;
        if (r3[r2] == true) goto L35;
        r152.put(newEmptyCell(), new int[]{r2, r0, 1, 1});
    L35:
        r2 = r2 + 1;
        goto L29
    L36:
        rewriteBlockRows(r152, r42);
        rebuildFromBlock();
        return true;
    L38:
        addColumn();
        return true;
    }

    public boolean insertRowAt(int r15) {
        int r0 = this.rowCount;
        if (r0 != 0) goto L5;
    L38:
        addRow();
        return true;
    L5:
        if (this.colCount == 0) goto L38;
        int r2 = 0;
        if (r15 >= 0) goto L10;
        r15 = 0;
    L10:
        if (r15 > r0) goto L13;
        r0 = r15;
    L13:
        IdentityHashMap<TL_iv.pageTableCell, int[]> r152 = new IdentityHashMap();
        boolean[] r3 = new boolean[this.colCount];
        ArrayList<TL_iv.pageTableCell> r4 = this.anchorsRowMajor;
        int r5 = r4.size();
        int r6 = 0;
    L14:
        if (r6 >= r5) goto L30;
        TL_iv.pageTableCell r7 = r4.get(r6);
        r6 = r6 + 1;
        TL_iv.pageTableCell r72 = r7;
        int r8 = anchorRowOf(r72);
        int r9 = anchorColOf(r72);
        int r10 = spanRow(r72);
        int r11 = spanCol(r72);
        if (r8 < r0) goto L18;
        int r12 = r8 + 1;
    L19:
        if (r8 >= r0) goto L28;
        if ((r8 + r10) <= r0) goto L28;
        r10 = r10 + 1;
        int r82 = r9;
    L24:
        if (r82 >= (r9 + r11)) goto L28;
        if (r82 >= this.colCount) goto L28;
        r3[r82] = true;
        r82 = r82 + 1;
    L28:
        r152.put(r72, new int[]{r12, r9, r10, r11});
        goto L14
    L18:
        r12 = r8;
    L30:
        if (r2 >= this.colCount) goto L36;
        if (r3[r2] == true) goto L35;
        r152.put(newEmptyCell(), new int[]{r0, r2, 1, 1});
    L35:
        r2 = r2 + 1;
        goto L30
    L36:
        rewriteBlockRows(r152, this.rowCount + 1);
        rebuildFromBlock();
        return true;
    }

    public boolean isAnchor(int r2, int r3) {
        if (r2 < 0) goto L14;
        if (r3 >= 0) goto L5;
        return false;
    L5:
        if (r2 < this.rowCount) goto L7;
        return false;
    L7:
        if (r3 < this.colCount) goto L9;
        return false;
    L9:
        if (this.anchorR[r2][r3] == r2) goto L11;
        return false;
    L11:
        if (this.anchorC[r2][r3] != r3) goto L20;
        return true;
    L20:
        return false;
    L14:
        return false;
    }

    public boolean mergeCells(Set<TL_iv.pageTableCell> r14) {
        int r0 = 0;
        if (r14 != null) goto L5;
    L66:
        return false;
    L5:
        if (r14.size() < 2) goto L66;
        Iterator<TL_iv.pageTableCell> r1 = r14.iterator();
        int r3 = -1;
        int r5 = Integer.MAX_VALUE;
        int r6 = Integer.MAX_VALUE;
        int r4 = -1;
    L9:
        if (r1.hasNext() == false) goto L11;
        TL_iv.pageTableCell r7 = r1.next();
        int r9 = anchorRowOf(r7);
        int r10 = anchorColOf(r7);
        int r11 = spanRow(r7);
        int r72 = spanCol(r7);
        r5 = Math.min(r5, r9);
        r6 = Math.min(r6, r10);
        r3 = Math.max(r3, (r9 + r11) - 1);
        r4 = Math.max(r4, (r10 + r72) - 1);
        goto L9
    L11:
        HashSet r12 = new HashSet();
        int r73 = r5;
    L12:
        if (r73 > r3) goto L26;
        int r92 = r6;
    L14:
        if (r92 > r4) goto L24;
        if (r73 < 0) goto L23;
        if (r92 < 0) goto L23;
        if (r73 >= this.rowCount) goto L23;
        if (r92 >= this.colCount) goto L23;
        r12.add(this.grid[r73][r92]);
        r92 = r92 + 1;
    L23:
        return false;
    L24:
        r73 = r73 + 1;
        goto L12
    L26:
        if (r12.equals(new HashSet(r14)) == true) goto L28;
        return false;
    L28:
        StringBuilder r142 = new StringBuilder();
        ArrayList r74 = new ArrayList(r12);
        Collections.sort(r74, new TableModel$$ExternalSyntheticLambda2(this));
        int r93 = r74.size();
        int r102 = 0;
    L29:
        if (r102 >= r93) goto L36;
        Object r112 = r74.get(r102);
        r102 = r102 + 1;
        String r113 = readPlainText((TL_iv.pageTableCell) r112);
        if (r113.isEmpty() == true) goto L29;
        if (r142.length() <= 0) goto L35;
        r142.append("\n");
    L35:
        r142.append(r113);
        goto L29
    L36:
        TL_iv.pageTableCell r75 = this.grid[r5][r6];
        int r42 = (r4 - r6) + 1;
        int r32 = (r3 - r5) + 1;
        if (r42 > 1) goto L40;
        r42 = 0;
    L40:
        r75.colspan = r42;
        if (r32 <= 1) goto L43;
        r0 = r32;
    L43:
        r75.rowspan = r0;
        int r33 = r75.flags;
        if (r42 <= 0) goto L47;
        int r2 = 2 | r33;
    L46:
        r75.flags = r2;
        int r22 = r75.flags;
        if (r0 <= 0) goto L52;
        int r02 = r22 | 4;
    L51:
        r75.flags = r02;
        applyPlainText(r75, r142.toString());
        Iterator r143 = r12.iterator();
    L55:
        if (r143.hasNext() == false) goto L64;
        TL_iv.pageTableCell r03 = (TL_iv.pageTableCell) r143.next();
        if (r03 == r75) goto L55;
        int r13 = anchorRowOf(r03);
        if (r13 < 0) goto L55;
        if (r13 >= this.block.rows.size()) goto L55;
        this.block.rows.get(r13).cells.remove(r03);
        goto L55
    L64:
        rebuildFromBlock();
        return true;
    L52:
        r02 = r22 & (-5);
        goto L51
    L47:
        r2 = r33 & (-3);
        goto L46
    }

    public void rebuildFromBlock() {
        ArrayList<TL_iv.pageTableRow> r1 = this.block.rows;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        this.rowCount = r12;
        int r13 = 0;
        int r3 = 0;
    L7:
        int r4 = this.rowCount;
        if (r13 >= r4) goto L16;
        TL_iv.pageTableRow r42 = this.block.rows.get(r13);
        int r5 = 0;
        int r6 = 0;
    L11:
        if (r5 >= r42.cells.size()) goto L13;
        r6 = r6 + spanCol(r42.cells.get(r5));
        r5 = r5 + 1;
        goto L11
    L13:
        if (r6 <= r3) goto L15;
        r3 = r6;
    L15:
        r13 = r13 + 1;
        goto L7
    L16:
        TL_iv.pageTableCell[][] r52 = (TL_iv.pageTableCell[][]) Array.newInstance(TL_iv.pageTableCell.class, new int[]{Math.max(r4, 1), Math.max(r3, 1)});
        int[] r9 = {Math.max(this.rowCount, 1), Math.max(r3, 1)};
        Class r7 = Integer.TYPE;
        int[][] r8 = (int[][]) Array.newInstance(r7, r9);
        int[][] r92 = (int[][]) Array.newInstance(r7, new int[]{Math.max(this.rowCount, 1), Math.max(r3, 1)});
        int r10 = 0;
    L17:
        int r122 = -1;
        if (r10 >= r8.length) goto L24;
        int r11 = 0;
    L21:
        if (r11 >= r8[0].length) goto L23;
        r8[r10][r11] = -1;
        r92[r10][r11] = -1;
        r11 = r11 + 1;
        goto L21
    L23:
        r10 = r10 + 1;
        goto L17
    L24:
        int r102 = 0;
        int r112 = 0;
    L25:
        int r132 = this.rowCount;
        if (r102 >= r132) goto L51;
        TL_iv.pageTableRow r133 = this.block.rows.get(r102);
        int r14 = r2;
        int r15 = r14;
    L29:
        if (r14 >= r133.cells.size()) goto L50;
        TL_iv.pageTableCell r22 = r133.cells.get(r14);
        int r17 = spanCol(r22);
        int r18 = spanRow(r22);
    L31:
        if (r15 >= r3) goto L35;
        if (r52[r102][r15] == null) goto L35;
        r15 = r15 + 1;
    L35:
        int r62 = r15 + r17;
        if (r62 <= r3) goto L38;
        r3 = Math.max(r62, r3 * 2);
        r52 = growCols(r52, r3);
        r8 = growIntCols(r8, r3, r122);
        r92 = growIntCols(r92, r3, r122);
    L38:
        int r123 = r102;
    L40:
        if (r123 >= (r102 + r18)) goto L47;
        if (r123 >= this.rowCount) goto L47;
        int r16 = r15;
    L44:
        if (r16 >= r62) goto L46;
        r52[r123][r16] = r22;
        r8[r123][r16] = r102;
        r92[r123][r16] = r15;
        r16 = r16 + 1;
        goto L44
    L46:
        r123 = r123 + 1;
    L47:
        if (r62 <= r112) goto L49;
        r112 = r62;
    L49:
        r14 = r14 + 1;
        r15 = r62;
        r122 = -1;
        goto L29
    L50:
        r102 = r102 + 1;
        r2 = r15;
        r122 = -1;
        goto L25
    L51:
        int r162 = r2;
        this.colCount = r112;
        int r23 = Math.max(r132, 1);
        int[] r103 = new int[2];
        r103[1] = Math.max(this.colCount, 1);
        r103[r162] = r23;
        this.grid = (TL_iv.pageTableCell[][]) Array.newInstance(TL_iv.pageTableCell.class, r103);
        int r24 = Math.max(this.rowCount, 1);
        int[] r43 = new int[2];
        r43[1] = Math.max(this.colCount, 1);
        r43[r162] = r24;
        this.anchorR = (int[][]) Array.newInstance(r7, r43);
        int r25 = Math.max(this.rowCount, 1);
        int[] r44 = new int[2];
        r44[1] = Math.max(this.colCount, 1);
        r44[r162] = r25;
        this.anchorC = (int[][]) Array.newInstance(r7, r44);
        int r19 = r162;
    L53:
        if (r19 >= this.rowCount) goto L62;
        int r26 = r162;
    L56:
        if (r26 >= this.colCount) goto L61;
        TL_iv.pageTableCell[][] r32 = this.grid;
        r32[r19][r26] = r52[r19][r26];
        this.anchorR[r19][r26] = r8[r19][r26];
        this.anchorC[r19][r26] = r92[r19][r26];
        if (r32[r19][r26] != null) goto L60;
        TL_iv.pageTableCell r33 = newEmptyCell();
        this.grid[r19][r26] = r33;
        this.anchorR[r19][r26] = r19;
        this.anchorC[r19][r26] = r26;
        this.block.rows.get(r19).cells.add(r33);
    L60:
        r26 = r26 + 1;
        goto L56
    L61:
        r19 = r19 + 1;
        goto L53
    L62:
        rebuildAnchorList();
        return;
    L5:
        r12 = r1.size();
        goto L6
    }

    public boolean unmergeCell(TL_iv.pageTableCell r15) {
        if (r15 != null) goto L5;
        return false;
    L5:
        int r1 = anchorRowOf(r15);
        int r2 = anchorColOf(r15);
        if (r1 < 0) goto L35;
        if (r2 < 0) goto L35;
        int r3 = spanRow(r15);
        int r4 = spanCol(r15);
        if (r3 > 1) goto L13;
        if (r4 > 1) goto L13;
        return false;
    L13:
        r15.rowspan = 0;
        r15.colspan = 0;
        r15.flags &= -7;
        int r6 = r1;
    L15:
        if (r6 >= (r1 + r3)) goto L33;
        if (r6 >= this.rowCount) goto L33;
        TL_iv.pageTableRow r7 = this.block.rows.get(r6);
        ArrayList r8 = new ArrayList();
        ArrayList<TL_iv.pageTableCell> r9 = r7.cells;
        int r10 = r9.size();
        int r11 = 0;
    L19:
        if (r11 >= r10) goto L21;
        TL_iv.pageTableCell r12 = r9.get(r11);
        r11 = r11 + 1;
        TL_iv.pageTableCell r122 = r12;
        r8.add(new Object[]{r122, Integer.valueOf(anchorColOf(r122))});
        goto L19
    L21:
        int r92 = r2;
    L23:
        if (r92 >= (r2 + r4)) goto L29;
        if (r6 != r1) goto L27;
        if (r92 != r2) goto L27;
    L28:
        r92 = r92 + 1;
    L27:
        TL_iv.pageTableCell r102 = new TL_iv.pageTableCell();
        r102.header = r15.header;
        r102.align_center = r15.align_center;
        r102.align_right = r15.align_right;
        r102.valign_middle = r15.valign_middle;
        r102.valign_bottom = r15.valign_bottom;
        applyPlainText(r102, _UrlKt.FRAGMENT_ENCODE_SET);
        r8.add(new Object[]{r102, Integer.valueOf(r92)});
        goto L28
    L29:
        Collections.sort(r8, Comparator.comparingInt(new TableModel$$ExternalSyntheticLambda0()));
        r7.cells.clear();
        int r93 = r8.size();
        int r103 = 0;
    L30:
        if (r103 >= r93) goto L32;
        Object r112 = r8.get(r103);
        r103 = r103 + 1;
        r7.cells.add((TL_iv.pageTableCell) ((Object[]) r112)[0]);
        goto L30
    L32:
        r6 = r6 + 1;
    L33:
        rebuildFromBlock();
        return true;
    L35:
        return false;
    }
}
