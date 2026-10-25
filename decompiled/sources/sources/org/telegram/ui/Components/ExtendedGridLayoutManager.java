package org.telegram.ui.Components;

import android.content.Context;
import android.util.SparseIntArray;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class ExtendedGridLayoutManager extends GridLayoutManager {
    private int calculatedWidth;
    private final boolean firstRowFullWidth;
    private int firstRowMax;
    private SparseIntArray itemSpans;
    private SparseIntArray itemsToRow;
    private final boolean lastRowFullWidth;
    private int lastSpanCount;
    private int rowsCount;

    public ExtendedGridLayoutManager(Context r2, int r3) {
        this(r2, r3, false);
    }

    public ExtendedGridLayoutManager(Context r2, int r3, boolean r4) {
        this(r2, r3, r4, false);
    }

    public ExtendedGridLayoutManager(Context r1, int r2, boolean r3, boolean r4) {
        super(r1, r2);
        this.itemSpans = new SparseIntArray();
        this.itemsToRow = new SparseIntArray();
        this.lastRowFullWidth = r3;
        this.firstRowFullWidth = r4;
    }

    private void checkLayout() {
        if (this.itemSpans.size() == getFlowItemCount()) goto L5;
    L10:
        this.calculatedWidth = getWidth();
        prepareLayout(getWidth());
        return;
    L5:
        if (this.calculatedWidth != getWidth()) goto L10;
        if (this.lastSpanCount != getSpanCount()) goto L10;
    }

    private void prepareLayout(float r17) {
        if (r17 != 0.0f) goto L5;
        float r1 = 100.0f;
    L6:
        this.itemSpans.clear();
        this.itemsToRow.clear();
        int r3 = 0;
        this.rowsCount = 0;
        this.firstRowMax = 0;
        int r4 = getFlowItemCount();
        this.lastSpanCount = r4;
        if (r4 != 0) goto L9;
        return;
    L9:
        int r2 = AndroidUtilities.dp(100.0f);
        int r5 = getSpanCount();
        int r6 = (this.lastRowFullWidth ? 1 : 0) + r4;
        int r7 = 0;
        int r9 = 0;
        int r8 = r5;
    L11:
        if (r7 >= r6) goto L65;
        if (r7 == 0) goto L14;
    L17:
        if (r7 >= r4) goto L19;
        Size r11 = sizeForItem(r7);
    L20:
        if (r11 != null) goto L25;
        if (r9 == 0) goto L23;
        int r112 = 1;
    L24:
        int r12 = r5;
    L38:
        if (r112 == 0) goto L53;
        if (r8 == 0) goto L50;
        if (r9 == 0) goto L50;
        int r113 = r8 / r9;
        int r13 = r7 - r9;
        int r14 = r13;
    L42:
        int r15 = r13 + r9;
        if (r14 >= r15) goto L49;
        int r152 = r15 - 1;
        SparseIntArray r32 = this.itemSpans;
        if (r14 != r152) goto L47;
        r32.put(r14, r32.get(r14) + r8);
    L48:
        r8 = r8 - r113;
        r14 = r14 + 1;
        goto L42
    L47:
        r32.put(r14, r32.get(r14) + r113);
        goto L48
    L49:
        this.itemsToRow.put(r7 - 1, this.rowsCount);
    L50:
        if (r7 == r4) goto L65;
        this.rowsCount++;
        r8 = r5;
        r9 = 0;
    L56:
        if (this.rowsCount != 0) goto L59;
        this.firstRowMax = Math.max(this.firstRowMax, r7);
    L59:
        if (r7 == (r4 - 1)) goto L61;
    L63:
        r9 = r9 + 1;
        r8 = r8 - r12;
        this.itemSpans.put(r7, r12);
    L64:
        r7 = r7 + 1;
        r3 = 0;
        goto L11
    L61:
        if (this.lastRowFullWidth == true) goto L63;
        this.itemsToRow.put(r7, this.rowsCount);
        goto L63
    L53:
        if (r8 >= r12) goto L56;
        r12 = r8;
        goto L56
    L23:
        r112 = r3;
        goto L24
    L25:
        r12 = Math.min(r5, (int) Math.floor(r5 * (((r11.width / r11.height) * r2) / r1)));
        if (r8 >= r12) goto L28;
    L33:
        int r132 = 1;
    L35:
        if (r11.full == false) goto L37;
        this.itemSpans.put(r7, r8);
        this.rowsCount++;
    L16:
        r9 = r3;
        r8 = r5;
        goto L64
    L37:
        r112 = r132;
        goto L38
    L28:
        if (r12 > 33) goto L30;
    L32:
        r132 = r3;
        goto L35
    L30:
        if (r8 >= (r12 - 15)) goto L32;
    L19:
        r11 = null;
        goto L20
    L14:
        if (this.firstRowFullWidth == false) goto L17;
        SparseIntArray r82 = this.itemSpans;
        r82.put(r7, r82.get(r7) + r5);
        this.itemsToRow.put(r3, this.rowsCount);
        this.rowsCount++;
    L65:
        this.rowsCount++;
        return;
    L5:
        r1 = r17;
        goto L6
    }

    private Size sizeForItem(int r1) {
        return fixSize(getSizeForItem(r1));
    }

    public Size fixSize(Size r4) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (r4.width != 0.0f) goto L9;
        r4.width = 100.0f;
    L9:
        if (r4.height != 0.0f) goto L11;
        r4.height = 100.0f;
    L11:
        float r3 = r4.width;
        float r0 = r4.height;
        float r1 = r3 / r0;
        if (r1 <= 4.0f) goto L14;
    L17:
        float r32 = Math.max(r3, r0);
        r4.width = r32;
        r4.height = r32;
        return r4;
    L14:
        if (r1 < 0.2f) goto L17;
        return r4;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler r1, RecyclerView.State r2) {
        return 1;
    }

    public int getFlowItemCount() {
        return getItemCount();
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler r1, RecyclerView.State r2) {
        return r2.getItemCount();
    }

    public Size getSizeForItem(int r1) {
        return new Size(100.0f, 100.0f);
    }

    public int getSpanSizeForItem(int r1) {
        checkLayout();
        return this.itemSpans.get(r1);
    }

    public boolean isFirstRow(int r1) {
        checkLayout();
        if (r1 > this.firstRowMax) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLastInRow(int r2) {
        checkLayout();
        if (this.itemsToRow.get(r2, Integer.MAX_VALUE) == Integer.MAX_VALUE) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}
