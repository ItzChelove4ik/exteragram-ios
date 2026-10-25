package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class RichTableCellGrid extends ViewGroup {
    private final RectF arcRect;
    private boolean bottomBulge;
    private int bulgeColLeft;
    private int bulgeColRight;
    private final Paint bulgeFillPaint;
    private final Path bulgePath;
    private int bulgeRowBot;
    private int bulgeRowTop;
    private int[] colStarts;
    private int[] colWidths;
    private int dotColor;
    private int dotOnSelectionColor;
    private final Paint dotPaint;
    private final Paint headerPaint;
    private boolean leftBulge;
    private final Paint linePaint;
    private TableModel model;
    private Theme.ResourcesProvider resourcesProvider;
    private int[] rowHeights;
    private int[] rowStarts;
    private final RectF selRect;
    private int selectedFillBaseAlpha;
    private int selectedStrokeBaseAlpha;
    private final Paint selectedStrokePaint;
    private AnimatedFloat selectionFade;
    private CellSelectionProvider selectionProvider;
    private final Paint stripPaint;

    public interface CellSelectionProvider {
        boolean isSelected(TL_iv.pageTableCell r1);
    }

    public RichTableCellGrid(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.colWidths = new int[0];
        this.rowHeights = new int[0];
        this.colStarts = new int[0];
        this.rowStarts = new int[0];
        Paint r0 = new Paint(1);
        this.linePaint = r0;
        this.headerPaint = new Paint(1);
        this.stripPaint = new Paint(1);
        Paint r2 = new Paint(1);
        this.selectedStrokePaint = r2;
        this.dotPaint = new Paint(1);
        this.bulgeFillPaint = new Paint(1);
        this.selRect = new RectF();
        this.arcRect = new RectF();
        this.bulgePath = new Path();
        this.resourcesProvider = r12;
        setClipChildren(false);
        setClipToPadding(false);
        setWillNotDraw(false);
        Paint.Style r112 = Paint.Style.STROKE;
        r0.setStyle(r112);
        r0.setStrokeWidth(AndroidUtilities.dpf2(0.66f));
        r2.setStyle(r112);
        r2.setStrokeWidth(AndroidUtilities.dp(2.0f));
        r2.setStrokeJoin(Paint.Join.ROUND);
        r2.setStrokeCap(Paint.Cap.ROUND);
        this.selectionFade = new AnimatedFloat(this, 0, 220, CubicBezierInterpolator.EASE_OUT_QUINT);
        applyColors();
    }

    private TL_iv.pageTableCell activeCell() {
        TableModel r0 = this.model;
        TL_iv.pageTableCell r1 = null;
        if (r0 != null) goto L5;
    L26:
        return r1;
    L5:
        if (r0.rowCount == 0) goto L26;
        if (r0.colCount == 0) goto L26;
        TL_iv.pageTableCell r02 = findFocusedCell();
        if (r02 == null) goto L13;
        return r02;
    L13:
        if (this.selectionProvider != null) goto L15;
        return null;
    L15:
        Iterator<TL_iv.pageTableCell> r03 = this.model.anchors().iterator();
        int r2 = Integer.MAX_VALUE;
        int r3 = Integer.MAX_VALUE;
    L17:
        if (r03.hasNext() == false) goto L26;
        TL_iv.pageTableCell r4 = r03.next();
        if (this.selectionProvider.isSelected(r4) == false) goto L17;
        int r5 = this.model.anchorRowOf(r4);
        int r6 = this.model.anchorColOf(r4);
        if (r5 < r2) goto L25;
        if (r5 != r2) goto L17;
        if (r6 >= r3) goto L17;
    L25:
        r1 = r4;
        r2 = r5;
        r3 = r6;
        goto L17
    }

    private boolean areColsFullySelected(int r3, int r4) {
        if (r3 < 0) goto L13;
        if (r4 < r3) goto L13;
    L6:
        if (r3 > r4) goto L11;
        if (isColFullySelected(r3) == false) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    L13:
        return false;
    }

    private boolean areRowsFullySelected(int r3, int r4) {
        if (r3 < 0) goto L13;
        if (r4 < r3) goto L13;
    L6:
        if (r3 > r4) goto L11;
        if (isRowFullySelected(r3) == false) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    L13:
        return false;
    }

    private boolean colHasSelection(int r4) {
        if (r4 >= 0) goto L5;
    L15:
        return false;
    L5:
        if (r4 >= this.model.colCount) goto L15;
        int r1 = 0;
    L9:
        if (r1 >= this.model.rowCount) goto L15;
        if (isSelected(r1, r4) == true) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return true;
    }

    private void computeHandlesState() {
        this.bottomBulge = false;
        this.leftBulge = false;
        TL_iv.pageTableCell r0 = activeCell();
        if (r0 == null) goto L18;
        int r1 = this.model.anchorRowOf(r0);
        int r2 = this.model.anchorColOf(r0);
        if (r1 < 0) goto L16;
        if (r2 < 0) goto L19;
        int r3 = TableModel.spanRow(r0);
        int r02 = TableModel.spanCol(r0);
        if (isRowFullySelected(r1) == false) goto L13;
        this.leftBulge = true;
        int[] r4 = this.rowStarts;
        this.bulgeRowTop = r4[r1];
        this.bulgeRowBot = r4[Math.min(r1 + r3, this.model.rowCount)];
    L13:
        if (isColFullySelected(r2) == false) goto L17;
        this.bottomBulge = true;
        int[] r12 = this.colStarts;
        this.bulgeColLeft = r12[r2];
        this.bulgeColRight = r12[Math.min(r2 + r02, this.model.colCount)];
        return;
    L17:
        return;
    L19:
        return;
    L16:
        return;
    }

    private float cornerRadiusFor(int r7, int r8) {
        TableModel r0 = this.model;
        int r1 = r0.colCount;
        int r02 = r0.rowCount;
        int[] r2 = this.colStarts;
        int r3 = 0;
        if (r7 != r2[0]) goto L6;
        int r12 = 0;
    L8:
        int[] r72 = this.rowStarts;
        if (r8 != r72[0]) goto L12;
    L14:
        if (r12 < 0) goto L21;
        if (r3 < 0) goto L21;
        if (isSelected(r3, r12) == false) goto L21;
        return Math.min(AndroidUtilities.dpf2(10.0f), Math.min(this.colWidths[r12], this.rowHeights[r3]) / 2.0f);
    L21:
        return 0.0f;
    L12:
        if (r8 != r72[r02]) goto L21;
        r3 = r02 - 1;
        goto L14
    L6:
        if (r7 != r2[r1]) goto L21;
        r12 = r1 - 1;
        goto L8
    }

    private void drawBorders(Canvas r13) {
        float r0 = this.linePaint.getStrokeWidth() / 2.0f;
        float r1 = AndroidUtilities.dpf2(10.0f);
        RectF r2 = this.selRect;
        int[] r3 = this.colStarts;
        int[] r6 = this.rowStarts;
        TableModel r8 = this.model;
        r2.set(r3[0] + r0, r6[0] + r0, r3[r8.colCount] - r0, r6[r8.rowCount] - r0);
        r13.drawRoundRect(this.selRect, r1, r1, this.linePaint);
        int r02 = 1;
        int r12 = 1;
    L4:
        if (r12 >= this.model.colCount) goto L19;
        int r22 = this.colStarts[r12];
        int r62 = -1;
        int r11 = 0;
    L6:
        TableModel r7 = this.model;
        if (r11 >= r7.rowCount) goto L15;
        TL_iv.pageTableCell[] r72 = r7.grid[r11];
        if (r72[r12 - 1] == r72[r12]) goto L12;
        if (r62 >= 0) goto L14;
        r62 = this.rowStarts[r11];
    L14:
        r11 = r11 + 1;
        goto L6
    L12:
        if (r62 < 0) goto L14;
        float r73 = r22;
        r13.drawLine(r73, r62, r73, this.rowStarts[r11], this.linePaint);
        r62 = -1;
        goto L14
    L15:
        if (r62 < 0) goto L17;
        float r23 = r22;
        r13.drawLine(r23, r62, r23, this.rowStarts[r8], this.linePaint);
    L17:
        r12 = r12 + 1;
    L19:
        if (r02 >= this.model.rowCount) goto L33;
        int r14 = this.rowStarts[r02];
        int r5 = -1;
        int r24 = 0;
    L21:
        TableModel r63 = this.model;
        if (r24 >= r63.colCount) goto L30;
        TL_iv.pageTableCell[][] r64 = r63.grid;
        if (r64[r02 - 1][r24] == r64[r02][r24]) goto L27;
        if (r5 >= 0) goto L29;
        r5 = this.colStarts[r24];
    L29:
        r24 = r24 + 1;
        goto L21
    L27:
        if (r5 < 0) goto L29;
        float r74 = r14;
        r13.drawLine(r5, r74, this.colStarts[r24], r74, this.linePaint);
        r5 = -1;
        goto L29
    L30:
        if (r5 < 0) goto L32;
        float r15 = r14;
        r13.drawLine(r5, r15, this.colStarts[r7], r15, this.linePaint);
    L32:
        r02 = r02 + 1;
        goto L19
    }

    private void drawBottomBulgeFill(Canvas r13, int r14, int r15) {
        float r0 = r14 - AndroidUtilities.dpf2(1.0f);
        float r2 = r15 + AndroidUtilities.dpf2(1.0f);
        float r1 = this.rowStarts[this.model.rowCount];
        float r3 = AndroidUtilities.dp(16.0f) + r1;
        float r4 = Math.min(AndroidUtilities.dpf2(10.0f), (r2 - r0) / 2.0f);
        float r142 = cornerRadiusFor(r14, this.rowStarts[this.model.rowCount]);
        float r152 = cornerRadiusFor(r15, this.rowStarts[this.model.rowCount]);
        this.bulgePath.rewind();
        this.bulgePath.moveTo(r0, r1 - r142);
        this.bulgePath.lineTo(r0, r3 - r4);
        float r7 = r4 * 2.0f;
        float r8 = r3 - r7;
        this.arcRect.set(r0, r8, r0 + r7, r3);
        this.bulgePath.arcTo(this.arcRect, 180.0f, -90.0f);
        this.bulgePath.lineTo(r2 - r4, r3);
        this.arcRect.set(r2 - r7, r8, r2, r3);
        this.bulgePath.arcTo(this.arcRect, 90.0f, -90.0f);
        this.bulgePath.lineTo(r2, r1 - r152);
        if (r152 <= 0.0f) goto L5;
        float r153 = r152 * 2.0f;
        this.arcRect.set(r2 - r153, r1 - r153, r2, r1);
        this.bulgePath.arcTo(this.arcRect, 0.0f, 90.0f);
    L6:
        this.bulgePath.lineTo(r0 + r142, r1);
        if (r142 <= 0.0f) goto L9;
        float r143 = r142 * 2.0f;
        this.arcRect.set(r0, r1 - r143, r143 + r0, r1);
        this.bulgePath.arcTo(this.arcRect, 90.0f, 90.0f);
    L10:
        this.bulgePath.close();
        r13.drawPath(this.bulgePath, this.bulgeFillPaint);
        return;
    L9:
        this.bulgePath.lineTo(r0, r1);
        goto L10
    L5:
        this.bulgePath.lineTo(r2, r1);
        goto L6
    }

    private void drawBulgeFills(Canvas r6) {
        if (hasAnySelection() == false) goto L12;
        int r0 = firstSelectedRow();
        int r1 = lastSelectedRow();
        int r2 = firstSelectedCol();
        int r3 = lastSelectedCol();
        if (areRowsFullySelected(r0, r1) == false) goto L8;
        int[] r4 = this.rowStarts;
        drawLeftBulgeFill(r6, r4[r0], r4[r1 + 1]);
    L8:
        if (areColsFullySelected(r2, r3) == false) goto L18;
        int[] r02 = this.colStarts;
        drawBottomBulgeFill(r6, r02[r2], r02[r3 + 1]);
        return;
    L18:
        return;
    L12:
        if (this.leftBulge == false) goto L15;
        drawLeftBulgeFill(r6, this.bulgeRowTop, this.bulgeRowBot);
    L15:
        if (this.bottomBulge == false) goto L19;
        drawBottomBulgeFill(r6, this.bulgeColLeft, this.bulgeColRight);
        return;
    }

    private void drawCellBackgrounds(Canvas r12) {
        TableModel r0 = this.model;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.rowCount != 0) goto L7;
        return;
    L7:
        if (r0.colCount == 0) goto L45;
        TL_iv.pageBlockTable r02 = r0.block;
        int r1 = 0;
        if (r02 != null) goto L12;
    L14:
        boolean r03 = false;
    L15:
        r12.save();
        RectF r2 = this.selRect;
        float r4 = this.colStarts[0];
        float r6 = this.rowStarts[0];
        TableModel r7 = this.model;
        r2.set(r4, r6, r3[r7.colCount], r5[r7.rowCount]);
        this.bulgePath.rewind();
        this.bulgePath.addRoundRect(this.selRect, AndroidUtilities.dpf2(10.0f), AndroidUtilities.dpf2(10.0f), Path.Direction.CW);
        r12.clipPath(this.bulgePath);
    L17:
        if (r1 >= getChildCount()) goto L33;
        View r22 = getChildAt(r1);
        if ((r22 instanceof RichTableCellHost) == false) goto L32;
        RichTableCellHost r23 = (RichTableCellHost) r22;
        int r42 = this.model.anchorRowOf(r23.cell);
        int r5 = this.model.anchorColOf(r23.cell);
        if (r42 < 0) goto L32;
        if (r5 < 0) goto L32;
        int r62 = TableModel.spanCol(r23.cell);
        int r72 = TableModel.spanRow(r23.cell);
        int[] r8 = this.colStarts;
        int r9 = r8[r5];
        int r10 = this.rowStarts[r42];
        int r52 = r8[Math.min(r5 + r62, this.model.colCount)];
        int r63 = this.rowStarts[Math.min(r72 + r42, this.model.rowCount)];
        if (r23.cell.header == false) goto L28;
        r12.drawRect(r9, r10, r52, r63, this.headerPaint);
        goto L32
    L28:
        if (r03 == false) goto L32;
        if ((r42 % 2) != 0) goto L32;
        r12.drawRect(r9, r10, r52, r63, this.stripPaint);
    L32:
        r1 = r1 + 1;
        goto L17
    L33:
        r12.restore();
        return;
    L12:
        if (r02.striped == false) goto L14;
        r03 = true;
        goto L15
    }

    private void drawHandleDots(Canvas r19) {
        if (this.model == null) goto L80;
        float r2 = AndroidUtilities.dpf2(3.0f) / 2.0f;
        float r4 = AndroidUtilities.dp(8.0f);
        float r5 = (this.colStarts[0] - AndroidUtilities.dp(6.0f)) - r2;
        float r6 = (this.rowStarts[this.model.rowCount] + AndroidUtilities.dp(6.0f)) + r2;
        if (hasAnySelection() == false) goto L53;
        int r7 = firstSelectedRow();
        int r10 = lastSelectedRow();
        int r11 = firstSelectedCol();
        int r12 = lastSelectedCol();
        if (r7 < 0) goto L74;
        if (r11 < 0) goto L81;
        TL_iv.pageTableCell r13 = activeCell();
        boolean r14 = useCombinedRowHandle();
        if (r13 != null) goto L14;
        int r15 = r7;
    L15:
        if (r13 != null) goto L17;
        float r17 = 2.0f;
        int r3 = r15 + 1;
    L18:
        int[] r8 = this.rowStarts;
        if (r14 == false) goto L22;
        float r32 = r8[r7] + r8[r10 + 1];
    L21:
        float r33 = r32 / r17;
        Paint r82 = this.dotPaint;
        if (r14 == true) goto L26;
    L28:
        int r72 = this.dotColor;
    L29:
        r82.setColor(r72);
        int r73 = -1;
    L30:
        if (r73 > 1) goto L32;
        r19.drawCircle(r5, (r73 * r4) + r33, r2, this.dotPaint);
        r73 = r73 + 1;
        goto L30
    L32:
        boolean r34 = useCombinedColHandle();
        if (r13 != null) goto L35;
        int r52 = r11;
    L36:
        if (r13 != null) goto L38;
        int r74 = r52 + 1;
    L39:
        int[] r83 = this.colStarts;
        if (r34 == false) goto L43;
        int r53 = r83[r11];
        int r75 = r83[r12 + 1];
    L42:
        float r54 = (r53 + r75) / r17;
        Paint r76 = this.dotPaint;
        if (r34 == true) goto L47;
    L49:
        int r35 = this.dotColor;
    L50:
        r76.setColor(r35);
        int r84 = -1;
    L51:
        if (r84 > 1) goto L84;
        r19.drawCircle((r84 * r4) + r54, r6, r2, this.dotPaint);
        r84 = r84 + 1;
        goto L51
    L84:
        return;
    L47:
        if (areColsFullySelected(r11, r12) == false) goto L49;
        r35 = this.dotOnSelectionColor;
        goto L50
    L43:
        r53 = r83[r52];
        r75 = r83[r74];
        goto L42
    L38:
        r74 = Math.min(TableModel.spanCol(r13) + r52, this.model.colCount);
        goto L39
    L35:
        r52 = this.model.anchorColOf(r13);
        goto L36
    L26:
        if (areRowsFullySelected(r7, r10) == false) goto L28;
        r72 = this.dotOnSelectionColor;
        goto L29
    L22:
        r32 = r8[r15] + r8[r3];
        goto L21
    L17:
        r17 = 2.0f;
        r3 = Math.min(r15 + TableModel.spanRow(r13), this.model.rowCount);
        goto L18
    L14:
        r15 = this.model.anchorRowOf(r13);
        goto L15
    L81:
        return;
    L74:
        return;
    L53:
        TL_iv.pageTableCell r36 = activeCell();
        if (r36 == null) goto L82;
        int r77 = this.model.anchorRowOf(r36);
        int r85 = this.model.anchorColOf(r36);
        if (r77 < 0) goto L79;
        if (r85 < 0) goto L83;
        int r102 = TableModel.spanRow(r36);
        int r37 = TableModel.spanCol(r36);
        int[] r112 = this.rowStarts;
        float r78 = (r112[r77] + r112[Math.min(r77 + r102, this.model.rowCount)]) / 2.0f;
        Paint r103 = this.dotPaint;
        if (this.leftBulge == false) goto L63;
        int r113 = this.dotOnSelectionColor;
    L64:
        r103.setColor(r113);
        int r104 = -1;
    L65:
        if (r104 > 1) goto L67;
        r19.drawCircle(r5, (r104 * r4) + r78, r2, this.dotPaint);
        r104 = r104 + 1;
        goto L65
    L67:
        int[] r55 = this.colStarts;
        float r38 = (r55[r85] + r55[Math.min(r85 + r37, this.model.colCount)]) / 2.0f;
        Paint r56 = this.dotPaint;
        if (this.bottomBulge == false) goto L70;
        int r79 = this.dotOnSelectionColor;
    L71:
        r56.setColor(r79);
        int r86 = -1;
    L72:
        if (r86 > 1) goto L85;
        r19.drawCircle((r86 * r4) + r38, r6, r2, this.dotPaint);
        r86 = r86 + 1;
        goto L72
    L85:
        return;
    L70:
        r79 = this.dotColor;
        goto L71
    L63:
        r113 = this.dotColor;
        goto L64
    L83:
        return;
    L79:
        return;
    L82:
        return;
    }

    private void drawLeftBulgeFill(Canvas r13, int r14, int r15) {
        float r0 = r14 - AndroidUtilities.dpf2(1.0f);
        float r2 = r15 + AndroidUtilities.dpf2(1.0f);
        float r1 = this.colStarts[0] - AndroidUtilities.dp(16.0f);
        float r4 = Math.min(AndroidUtilities.dpf2(10.0f), (r2 - r0) / 2.0f);
        float r142 = cornerRadiusFor(this.colStarts[0], r14);
        float r152 = cornerRadiusFor(this.colStarts[0], r15);
        this.bulgePath.rewind();
        this.bulgePath.moveTo(this.colStarts[0] + r142, r0);
        this.bulgePath.lineTo(r1 + r4, r0);
        float r7 = r4 * 2.0f;
        float r8 = r1 + r7;
        this.arcRect.set(r1, r0, r8, r0 + r7);
        this.bulgePath.arcTo(this.arcRect, 270.0f, -90.0f);
        this.bulgePath.lineTo(r1, r2 - r4);
        this.arcRect.set(r1, r2 - r7, r8, r2);
        this.bulgePath.arcTo(this.arcRect, 180.0f, -90.0f);
        this.bulgePath.lineTo(this.colStarts[0] + r152, r2);
        if (r152 <= 0.0f) goto L5;
        RectF r42 = this.arcRect;
        int r82 = this.colStarts[0];
        float r153 = r152 * 2.0f;
        r42.set(r82, r2 - r153, r82 + r153, r2);
        this.bulgePath.arcTo(this.arcRect, 90.0f, 90.0f);
    L6:
        this.bulgePath.lineTo(this.colStarts[0], r0 + r142);
        if (r142 <= 0.0f) goto L9;
        RectF r154 = this.arcRect;
        int r12 = this.colStarts[0];
        float r143 = r142 * 2.0f;
        r154.set(r12, r0, r12 + r143, r143 + r0);
        this.bulgePath.arcTo(this.arcRect, 180.0f, 90.0f);
    L10:
        this.bulgePath.close();
        r13.drawPath(this.bulgePath, this.bulgeFillPaint);
        return;
    L9:
        this.bulgePath.lineTo(this.colStarts[0], r0);
        goto L10
    L5:
        this.bulgePath.lineTo(this.colStarts[0], r2);
        goto L6
    }

    private void drawSelCornerArc(Canvas r11, int r12, int r13, float r14) {
        float r0 = cornerRadiusFor(r12, r13);
        if (r0 > 0.0f) goto L6;
        return;
    L6:
        if (r12 != this.colStarts[0]) goto L8;
        float r122 = r12 + r0;
    L10:
        if (r13 != this.rowStarts[0]) goto L12;
        float r132 = r13 + r0;
    L13:
        this.arcRect.set(r122 - r0, r132 - r0, r122 + r0, r132 + r0);
        r11.drawArc(this.arcRect, r14, 90.0f, false, this.selectedStrokePaint);
        return;
    L12:
        r132 = r13 - r0;
        goto L13
    L8:
        r122 = r12 - r0;
        goto L10
    }

    private void drawSelHLine(Canvas r8, int r9, int r10, int r11) {
        if (r10 <= r9) goto L9;
        float r2 = r9 + cornerRadiusFor(r9, r11);
        float r4 = r10 - cornerRadiusFor(r10, r11);
        if (r4 <= r2) goto L8;
        float r3 = r11;
        r8.drawLine(r2, r3, r4, r3, this.selectedStrokePaint);
        return;
    L8:
        return;
    }

    private void drawSelVLine(Canvas r8, int r9, int r10, int r11) {
        if (r11 <= r10) goto L9;
        float r3 = r10 + cornerRadiusFor(r9, r10);
        float r5 = r11 - cornerRadiusFor(r9, r11);
        if (r5 <= r3) goto L8;
        float r2 = r9;
        r8.drawLine(r2, r3, r2, r5, this.selectedStrokePaint);
        return;
    L8:
        return;
    }

    private void drawSelectionOutline(Canvas r10) {
        if (this.model == null) goto L70;
        AnimatedFloat r0 = this.selectionFade;
        if (hasAnySelection() == false) goto L8;
        float r1 = 1.0f;
    L9:
        float r02 = r0.set(r1);
        if (r02 > 0.001f) goto L12;
        return;
    L12:
        this.selectedStrokePaint.setAlpha((int) (this.selectedStrokeBaseAlpha * r02));
        this.selectedStrokePaint.setStrokeWidth(AndroidUtilities.dpf2(2.0f) * Math.max(0.4f, r02));
        int r12 = 0;
    L13:
        int r3 = this.model.rowCount;
        if (r12 > r3) goto L32;
        int[] r5 = this.rowStarts;
        if (r12 >= r3) goto L18;
        int r32 = r5[r12];
    L19:
        int r52 = 0;
        int r6 = -1;
    L20:
        int r7 = this.model.colCount;
        if (r52 >= r7) goto L29;
        if (isSelected(r12 - 1, r52) == isSelected(r12, r52)) goto L26;
        if (r6 >= 0) goto L28;
        r6 = this.colStarts[r52];
    L28:
        r52 = r52 + 1;
        goto L20
    L26:
        if (r6 < 0) goto L28;
        drawSelHLine(r10, r6, this.colStarts[r52], r32);
        r6 = -1;
        goto L28
    L29:
        if (r6 < 0) goto L31;
        drawSelHLine(r10, r6, this.colStarts[r7], r32);
    L31:
        r12 = r12 + 1;
        goto L13
    L18:
        r32 = r5[r3];
        goto L19
    L32:
        int r13 = 0;
    L33:
        TableModel r33 = this.model;
        int r53 = r33.colCount;
        if (r13 > r53) goto L52;
        int[] r34 = this.colStarts;
        if (r13 >= r53) goto L38;
        int r35 = r34[r13];
    L39:
        int r54 = 0;
        int r62 = -1;
    L40:
        int r72 = this.model.rowCount;
        if (r54 >= r72) goto L49;
        if (isSelected(r54, r13 - 1) == isSelected(r54, r13)) goto L46;
        if (r62 >= 0) goto L48;
        r62 = this.rowStarts[r54];
    L48:
        r54 = r54 + 1;
        goto L40
    L46:
        if (r62 < 0) goto L48;
        drawSelVLine(r10, r35, r62, this.rowStarts[r54]);
        r62 = -1;
        goto L48
    L49:
        if (r62 < 0) goto L51;
        drawSelVLine(r10, r35, r62, this.rowStarts[r72]);
    L51:
        r13 = r13 + 1;
        goto L33
    L38:
        r35 = r34[r53];
        goto L39
    L52:
        int r14 = r33.rowCount;
        drawSelCornerArc(r10, this.colStarts[0], this.rowStarts[0], 180.0f);
        drawSelCornerArc(r10, this.colStarts[r53], this.rowStarts[0], 270.0f);
        drawSelCornerArc(r10, this.colStarts[0], this.rowStarts[r14], 90.0f);
        drawSelCornerArc(r10, this.colStarts[r53], this.rowStarts[r14], 0.0f);
        return;
    L8:
        r1 = 0.0f;
        goto L9
    }

    private int firstSelectedCol() {
        int r0 = 0;
    L4:
        if (r0 >= this.model.colCount) goto L9;
        if (colHasSelection(r0) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    private int firstSelectedRow() {
        int r0 = 0;
    L4:
        if (r0 >= this.model.rowCount) goto L9;
        if (rowHasSelection(r0) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    private boolean hasAnySelection() {
        TableModel r0 = this.model;
        if (r0 != null) goto L5;
    L14:
        return false;
    L5:
        if (this.selectionProvider == null) goto L14;
        Iterator<TL_iv.pageTableCell> r02 = r0.anchors().iterator();
    L9:
        if (r02.hasNext() == false) goto L14;
        TL_iv.pageTableCell r2 = r02.next();
        if (this.selectionProvider.isSelected(r2) == false) goto L9;
        return true;
    }

    private boolean isColFullySelected(int r4) {
        if (r4 >= 0) goto L5;
    L16:
        return false;
    L5:
        if (r4 >= this.model.colCount) goto L16;
        int r1 = 0;
    L9:
        if (r1 >= this.model.rowCount) goto L14;
        if (isSelected(r1, r4) == false) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    }

    private boolean isRowFullySelected(int r4) {
        if (r4 >= 0) goto L5;
    L16:
        return false;
    L5:
        if (r4 >= this.model.rowCount) goto L16;
        int r1 = 0;
    L9:
        if (r1 >= this.model.colCount) goto L14;
        if (isSelected(r4, r1) == false) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    }

    private boolean isSelected(int r4, int r5) {
        TableModel r0 = this.model;
        if (r0 == null) goto L16;
        CellSelectionProvider r3 = this.selectionProvider;
        if (r3 == null) goto L16;
        if (r4 < 0) goto L16;
        if (r4 >= r0.rowCount) goto L16;
        if (r5 < 0) goto L16;
        if (r5 >= r0.colCount) goto L16;
        return r3.isSelected(r0.grid[r4][r5]);
    L16:
        return false;
    }

    private int lastSelectedCol() {
        int r0 = this.model.colCount - 1;
    L3:
        if (r0 < 0) goto L8;
        if (colHasSelection(r0) == true) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return -1;
    }

    private int lastSelectedRow() {
        int r0 = this.model.rowCount - 1;
    L3:
        if (r0 < 0) goto L8;
        if (rowHasSelection(r0) == true) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return -1;
    }

    private void rebuildHosts() {
        int r0 = getChildCount() - 1;
    L3:
        if (r0 < 0) goto L8;
        if ((getChildAt(r0) instanceof RichTableCellHost) == false) goto L7;
        removeViewAt(r0);
    L7:
        r0 = r0 - 1;
        goto L3
    L8:
        TableModel r02 = this.model;
        if (r02 == null) goto L14;
        int r03 = r02.anchors().size();
        int r1 = 0;
    L12:
        if (r1 >= r03) goto L19;
        TL_iv.pageTableCell r2 = this.model.anchors().get(r1);
        RichTableCellHost r3 = new RichTableCellHost(getContext(), this.resourcesProvider);
        r3.setCompact(this.model.block.compact);
        r3.bind(r2);
        addView(r3);
        r1 = r1 + 1;
        goto L12
    L19:
        return;
    }

    private boolean rowHasSelection(int r4) {
        if (r4 >= 0) goto L5;
    L15:
        return false;
    L5:
        if (r4 >= this.model.rowCount) goto L15;
        int r1 = 0;
    L9:
        if (r1 >= this.model.colCount) goto L15;
        if (isSelected(r4, r1) == true) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return true;
    }

    private boolean useCombinedColHandle() {
        if (hasAnySelection() == true) goto L5;
        return false;
    L5:
        boolean r0 = areRowsFullySelected(firstSelectedRow(), lastSelectedRow());
        boolean r4 = areColsFullySelected(firstSelectedCol(), lastSelectedCol());
        if (r0 == false) goto L10;
        if (r4 == true) goto L12;
        return false;
    L12:
        return true;
    L10:
        return true;
    }

    private boolean useCombinedRowHandle() {
        if (hasAnySelection() == true) goto L5;
        return false;
    L5:
        boolean r0 = areRowsFullySelected(firstSelectedRow(), lastSelectedRow());
        if (areColsFullySelected(firstSelectedCol(), lastSelectedCol()) == false) goto L10;
        if (r0 == true) goto L12;
        return false;
    L12:
        return true;
    L10:
        return true;
    }

    public void applyColors() {
        this.linePaint.setColor(Theme.getColor(Theme.key_table_border, this.resourcesProvider));
        int r0 = Theme.getColor(Theme.key_switchTrack, this.resourcesProvider);
        int r1 = Color.red(r0);
        int r2 = Color.green(r0);
        int r02 = Color.blue(r0);
        this.headerPaint.setColor(Theme.getColor(Theme.key_table_background, this.resourcesProvider));
        this.stripPaint.setColor(Color.argb(20, r1, r2, r02));
        this.selectedFillBaseAlpha = 80;
        this.selectedStrokeBaseAlpha = 255;
        Paint r03 = this.selectedStrokePaint;
        int r12 = Theme.key_featuredStickers_addButton;
        r03.setColor(Theme.getColor(r12, this.resourcesProvider));
        Paint r04 = this.dotPaint;
        Paint.Style r22 = Paint.Style.FILL;
        r04.setStyle(r22);
        this.dotColor = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7, this.resourcesProvider);
        this.dotOnSelectionColor = Theme.getColor(Theme.key_featuredStickers_buttonText, this.resourcesProvider);
        this.dotPaint.setColor(this.dotColor);
        this.bulgeFillPaint.setStyle(r22);
        this.bulgeFillPaint.setColor(Theme.getColor(r12, this.resourcesProvider));
        invalidate();
    }

    public int colHandleAtGrid(int r5, int r6) {
        TableModel r0 = this.model;
        if (r0 != null) goto L5;
        return -1;
    L5:
        int r02 = this.rowStarts[r0.rowCount];
        if (r6 >= r02) goto L8;
    L30:
        return -1;
    L8:
        if (r6 >= ((r02 + AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(4.0f))) goto L30;
        if (useCombinedColHandle() == false) goto L20;
        int r62 = firstSelectedCol();
        int r03 = lastSelectedCol();
        if (r62 < 0) goto L19;
        int[] r4 = this.colStarts;
        if (r5 < r4[r62]) goto L19;
        if (r5 >= r4[r03 + 1]) goto L19;
        return r62;
    L19:
        return -1;
    L20:
        TL_iv.pageTableCell r63 = activeCell();
        if (r63 != null) goto L23;
        return -1;
    L23:
        int r04 = this.model.anchorColOf(r63);
        if (r04 >= 0) goto L26;
        return -1;
    L26:
        int r64 = TableModel.spanCol(r63);
        int[] r2 = this.colStarts;
        int r3 = r2[r04];
        int r42 = r2[Math.min(r64 + r04, this.model.colCount)];
        if (r5 < r3) goto L30;
        if (r5 >= r42) goto L30;
        return r04;
    }

    public int colHandleEnd(int r2) {
        if (useCombinedColHandle() == true) goto L5;
    L9:
        return r2;
    L5:
        if (r2 != firstSelectedCol()) goto L9;
        return lastSelectedCol();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r2) {
        computeHandlesState();
        drawCellBackgrounds(r2);
        super.dispatchDraw(r2);
        drawBulgeFills(r2);
        TableModel r0 = this.model;
        if (r0 == null) goto L9;
        TL_iv.pageBlockTable r02 = r0.block;
        if (r02 == null) goto L9;
        if (r02.bordered == false) goto L9;
        drawBorders(r2);
    L9:
        drawSelectionOutline(r2);
        drawHandleDots(r2);
    }

    public TL_iv.pageTableCell findFocusedCell() {
        View r0 = findFocus();
        if (r0 != null) goto L5;
        ViewParent r02 = null;
    L6:
        if (r02 == null) goto L13;
        if (r02 == this) goto L13;
        if ((r02 instanceof RichTableCellHost) == true) goto L11;
        r02 = r02.getParent();
        goto L6
    L11:
        return ((RichTableCellHost) r02).cell;
    L13:
        return null;
    L5:
        r02 = r0.getParent();
        goto L6
    }

    public TableModel getModel() {
        return this.model;
    }

    public RichTableCellHost hostForAnchor(TL_iv.pageTableCell r4) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L11;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichTableCellHost) == false) goto L10;
        RichTableCellHost r12 = (RichTableCellHost) r1;
        if (r12.cell != r4) goto L10;
        return r12;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        if (this.model == null) goto L17;
        int r22 = 0;
    L7:
        if (r22 >= getChildCount()) goto L23;
        View r32 = getChildAt(r22);
        if ((r32 instanceof RichTableCellHost) == false) goto L16;
        RichTableCellHost r33 = (RichTableCellHost) r32;
        int r42 = this.model.anchorRowOf(r33.cell);
        int r52 = this.model.anchorColOf(r33.cell);
        if (r42 < 0) goto L16;
        if (r52 < 0) goto L16;
        int r53 = this.colStarts[r52];
        int r43 = this.rowStarts[r42];
        r33.layout(r53, r43, r33.getMeasuredWidth() + r53, r33.getMeasuredHeight() + r43);
    L16:
        r22 = r22 + 1;
        goto L7
    L23:
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r19, int r20) {
        float r1 = 20.0f;
        int r2 = AndroidUtilities.dp(20.0f);
        int r4 = AndroidUtilities.dp(4.0f);
        int r3 = AndroidUtilities.dp(4.0f);
        int r5 = AndroidUtilities.dp(10.0f);
        TableModel r6 = this.model;
        int r7 = 0;
        if (r6 != null) goto L5;
    L143:
        setMeasuredDimension(View.MeasureSpec.getSize(r19), r4 + r5);
        this.colWidths = new int[0];
        this.rowHeights = new int[0];
        this.colStarts = new int[0];
        this.rowStarts = new int[0];
        return;
    L5:
        if (r6.rowCount == 0) goto L143;
        if (r6.colCount == 0) goto L143;
        int r62 = (View.MeasureSpec.getSize(r19) - r2) - r3;
        TableModel r8 = this.model;
        int r9 = r8.rowCount;
        int r10 = r8.colCount;
        this.colWidths = new int[r10];
        this.rowHeights = new int[r9];
        if (r8.block.compact == true) goto L13;
        r1 = 50.0f;
    L13:
        int r12 = AndroidUtilities.dp(r1);
        if (this.model.block.compact == false) goto L16;
        int r82 = 5;
    L18:
        if (r10 != 2) goto L20;
        int r122 = Math.max(0, (r62 / 2) - AndroidUtilities.dp(r82 * 4));
    L21:
        float r83 = r82 * 2;
        int r11 = Math.max(r12, r122 + AndroidUtilities.dp(r83));
        int r123 = 0;
    L22:
        if (r123 >= r10) goto L24;
        this.colWidths[r123] = r12;
        r123 = r123 + 1;
        goto L22
    L24:
        int r13 = 0;
    L25:
        int r132 = 1;
        if (r13 >= getChildCount()) goto L39;
        View r124 = getChildAt(r13);
        if ((r124 instanceof RichTableCellHost) == false) goto L38;
        RichTableCellHost r125 = (RichTableCellHost) r124;
        if (TableModel.spanCol(r125.cell) != 1) goto L38;
        int r133 = this.model.anchorColOf(r125.cell);
        if (r133 < 0) goto L38;
        if (r133 >= r10) goto L38;
        int r126 = Math.round(Layout.getDesiredWidth(r125.editText.getText(), r125.editText.getPaint())) + AndroidUtilities.dp(r83);
        int[] r14 = this.colWidths;
        r14[r133] = Math.max(r14[r133], Math.min(r11, r126));
    L38:
        r13 = r13 + 1;
        goto L25
    L39:
        int r15 = 0;
    L41:
        if (r15 >= getChildCount()) goto L60;
        View r127 = getChildAt(r15);
        if ((r127 instanceof RichTableCellHost) == true) goto L45;
    L44:
        int r192 = r132;
    L59:
        r15 = r15 + 1;
        r132 = r192;
        r7 = 0;
        goto L41
    L45:
        RichTableCellHost r128 = (RichTableCellHost) r127;
        int r142 = TableModel.spanCol(r128.cell);
        if (r142 <= r132) goto L44;
        r192 = r132;
        int r134 = this.model.anchorColOf(r128.cell);
        int r143 = Math.min(r10, r142 + r134);
        if (r134 < 0) goto L59;
        if (r134 >= r143) goto L59;
        int r16 = r7;
        int r152 = r134;
    L53:
        if (r152 >= r143) goto L55;
        r16 = r16 + this.colWidths[r152];
        r152 = r152 + 1;
        goto L53
    L55:
        int r72 = Math.min((r143 - r134) * r11, Math.round(Layout.getDesiredWidth(r128.editText.getText(), r128.editText.getPaint())) + AndroidUtilities.dp(r83)) - r16;
    L56:
        if (r134 >= r143) goto L59;
        if (r72 <= 0) goto L59;
        int r153 = ((r72 + r12) - 1) / (r143 - r134);
        int[] r129 = this.colWidths;
        r129[r134] = r129[r134] + r153;
        r72 = r72 - r153;
        r134 = r134 + 1;
        goto L56
    L60:
        int r193 = r132;
        int[] r17 = this.colWidths;
        int r73 = r17.length;
        int r84 = 0;
        int r112 = 0;
    L61:
        if (r84 >= r73) goto L63;
        r112 = r112 + r17[r84];
        r84 = r84 + 1;
        goto L61
    L63:
        if (r112 >= r62) goto L72;
        if (r10 <= 0) goto L72;
        int r74 = r62 - r112;
        int r18 = 0;
    L66:
        if (r18 >= r10) goto L72;
        if (r18 != (r10 - 1)) goto L70;
        int r85 = r74;
    L71:
        int[] r1210 = this.colWidths;
        int r135 = r1210[r18] + r85;
        r1210[r18] = r135;
        r74 = r74 - r85;
        r112 = r112 - (r135 - r85);
        r18 = r18 + 1;
        goto L66
    L70:
        r85 = Math.round((this.colWidths[r18] * r74) / r112);
    L72:
        int r75 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int r110 = 0;
    L74:
        if (r110 >= getChildCount()) goto L89;
        View r86 = getChildAt(r110);
        if ((r86 instanceof RichTableCellHost) == true) goto L78;
        int r172 = r110;
    L88:
        r110 = r172 + 1;
        r193 = 1;
        goto L74
    L78:
        RichTableCellHost r87 = (RichTableCellHost) r86;
        int r1211 = this.model.anchorRowOf(r87.cell);
        int r136 = this.model.anchorColOf(r87.cell);
        int r144 = TableModel.spanCol(r87.cell);
        r172 = r110;
        int r113 = r136;
        int r154 = 0;
    L80:
        if (r113 >= (r136 + r144)) goto L83;
        if (r113 >= r10) goto L83;
        r154 = r154 + this.colWidths[r113];
        r113 = r113 + 1;
    L83:
        r87.measure(View.MeasureSpec.makeMeasureSpec(r154, TLObject.FLAG_30), r75);
        if (TableModel.spanRow(r87.cell) != r193) goto L88;
        int r111 = r87.getMeasuredHeight();
        int[] r114 = this.rowHeights;
        if (r111 <= r114[r1211]) goto L88;
        r114[r1211] = r87.getMeasuredHeight();
        goto L88
    L89:
        int r115 = 0;
    L91:
        if (r115 >= getChildCount()) goto L117;
        View r76 = getChildAt(r115);
        if ((r76 instanceof RichTableCellHost) == false) goto L116;
        RichTableCellHost r77 = (RichTableCellHost) r76;
        int r88 = this.model.anchorRowOf(r77.cell);
        int r116 = TableModel.spanRow(r77.cell);
        if (r116 <= 1) goto L116;
        int r137 = r88;
        int r1212 = 0;
    L99:
        int r145 = r88 + r116;
        if (r137 >= r145) goto L103;
        if (r137 >= r9) goto L103;
        r1212 = r1212 + this.rowHeights[r137];
        r137 = r137 + 1;
    L103:
        int r78 = r77.getMeasuredHeight();
        if (r78 <= r1212) goto L116;
        int r79 = r78 - r1212;
        int r138 = r79 / Math.max(r116, 1);
        int r710 = r79 % Math.max(r116, 1);
    L106:
        if (r88 >= r145) goto L116;
        if (r88 >= r9) goto L116;
        int[] r117 = this.rowHeights;
        int r155 = r117[r88];
        if (r710 <= 0) goto L111;
        int r173 = 1;
    L112:
        r117[r88] = r155 + (r138 + r173);
        if (r710 <= 0) goto L115;
        r710 = r710 - 1;
    L115:
        r88 = r88 + 1;
        goto L106
    L111:
        r173 = 0;
    L116:
        r115 = r115 + 1;
        goto L91
    L117:
        int r118 = 0;
    L119:
        if (r118 >= getChildCount()) goto L135;
        View r711 = getChildAt(r118);
        if ((r711 instanceof RichTableCellHost) == true) goto L123;
        int r174 = r118;
    L134:
        r118 = r174 + 1;
        goto L119
    L123:
        RichTableCellHost r712 = (RichTableCellHost) r711;
        int r89 = this.model.anchorRowOf(r712.cell);
        int r119 = this.model.anchorColOf(r712.cell);
        int r1213 = TableModel.spanCol(r712.cell);
        int r139 = TableModel.spanRow(r712.cell);
        r174 = r118;
        int r156 = r119;
        int r146 = 0;
    L125:
        if (r156 >= (r119 + r1213)) goto L128;
        if (r156 >= r10) goto L128;
        r146 = r146 + this.colWidths[r156];
        r156 = r156 + 1;
    L128:
        int r1110 = r89;
        int r120 = 0;
    L130:
        if (r1110 >= (r89 + r139)) goto L133;
        if (r1110 >= r9) goto L133;
        r120 = r120 + this.rowHeights[r1110];
        r1110 = r1110 + 1;
    L133:
        r712.measure(View.MeasureSpec.makeMeasureSpec(r146, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r120, TLObject.FLAG_30));
        goto L134
    L135:
        int[] r121 = new int[r10 + 1];
        this.colStarts = r121;
        r121[0] = r2;
        int r130 = 0;
    L136:
        if (r130 >= r10) goto L138;
        int[] r713 = this.colStarts;
        int r810 = r130 + 1;
        r713[r810] = r713[r130] + this.colWidths[r130];
        r130 = r810;
        goto L136
    L138:
        int[] r131 = new int[r9 + 1];
        this.rowStarts = r131;
        r131[0] = r4;
        int r714 = 0;
    L139:
        if (r714 >= r9) goto L141;
        int[] r140 = this.rowStarts;
        int r42 = r714 + 1;
        r140[r42] = r140[r714] + this.rowHeights[r714];
        r714 = r42;
        goto L139
    L141:
        int r141 = this.colStarts[r10] + r3;
        setMeasuredDimension(Math.max(r141, (r62 + r2) + r3), this.rowStarts[r9] + r5);
        return;
    L20:
        r122 = Math.max(0, Math.round(r62 / 1.5f));
        goto L21
    L16:
        r82 = 12;
        goto L18
    }

    public void rebindAfterModelChange() {
        rebuildHosts();
        requestLayout();
        invalidate();
    }

    public void refreshCompact() {
        if (this.model != null) goto L5;
        return;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= getChildCount()) goto L12;
        View r1 = getChildAt(r0);
        if ((r1 instanceof RichTableCellHost) == false) goto L11;
        ((RichTableCellHost) r1).setCompact(this.model.block.compact);
    L11:
        r0 = r0 + 1;
        goto L7
    L12:
        requestLayout();
        invalidate();
    }

    public int rowHandleAtGrid(int r5, int r6) {
        if (this.model != null) goto L5;
    L29:
        return -1;
    L5:
        if (r5 < ((this.colStarts[0] - AndroidUtilities.dp(16.0f)) - AndroidUtilities.dp(4.0f))) goto L29;
        if (r5 >= this.colStarts[0]) goto L29;
        if (useCombinedRowHandle() == false) goto L19;
        int r52 = firstSelectedRow();
        int r0 = lastSelectedRow();
        if (r52 < 0) goto L18;
        int[] r4 = this.rowStarts;
        if (r6 < r4[r52]) goto L18;
        if (r6 >= r4[r0 + 1]) goto L18;
        return r52;
    L18:
        return -1;
    L19:
        TL_iv.pageTableCell r53 = activeCell();
        if (r53 != null) goto L22;
        return -1;
    L22:
        int r02 = this.model.anchorRowOf(r53);
        if (r02 >= 0) goto L25;
        return -1;
    L25:
        int r54 = TableModel.spanRow(r53);
        int[] r2 = this.rowStarts;
        int r3 = r2[r02];
        int r42 = r2[Math.min(r54 + r02, this.model.rowCount)];
        if (r6 < r3) goto L29;
        if (r6 >= r42) goto L29;
        return r02;
    }

    public int rowHandleEnd(int r2) {
        if (useCombinedRowHandle() == true) goto L5;
    L9:
        return r2;
    L5:
        if (r2 != firstSelectedRow()) goto L9;
        return lastSelectedRow();
    }

    public void setModel(TableModel r1) {
        this.model = r1;
        rebuildHosts();
    }

    public void setSelectionProvider(CellSelectionProvider r1) {
        this.selectionProvider = r1;
        invalidate();
    }
}
