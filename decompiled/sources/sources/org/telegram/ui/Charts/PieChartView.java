package org.telegram.ui.Charts;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;
import java.util.ArrayList;
import kotlin.time.DurationKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.data.StackLinearChartData;
import org.telegram.ui.Charts.view_data.ChartHorizontalLinesData;
import org.telegram.ui.Charts.view_data.LegendSignatureView;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.PieLegendView;
import org.telegram.ui.Charts.view_data.StackLinearViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class PieChartView extends StackLinearChartView<PieChartViewData> {
    float MAX_TEXT_SIZE;
    float MIN_TEXT_SIZE;
    int currentSelection;
    float[] darawingValuesPercentage;
    float emptyDataAlpha;
    boolean isEmpty;
    int lastEndIndex;
    int lastStartIndex;
    String[] lookupTable;
    int oldW;
    PieLegendView pieLegendView;
    RectF rectF;
    float sum;
    TextPaint textPaint;
    float[] values;

    /* renamed from: $r8$lambda$0UlhreTqkyaMb-OGQtgvdr0b_2Y, reason: not valid java name */
    public static /* synthetic */ void m7374$r8$lambda$0UlhreTqkyaMbOGQtgvdr0b_2Y(PieChartView r0, PieChartViewData r1, ValueAnimator r2) {
        r0.lambda$updateCharValues$0(r1, r2);
    }

    public PieChartView(Context r7) {
        super(r7);
        this.currentSelection = -1;
        this.rectF = new RectF();
        this.MIN_TEXT_SIZE = AndroidUtilities.dp(9.0f);
        this.MAX_TEXT_SIZE = AndroidUtilities.dp(13.0f);
        this.lookupTable = new String[101];
        this.emptyDataAlpha = 1.0f;
        this.oldW = 0;
        this.lastStartIndex = -1;
        this.lastEndIndex = -1;
        int r2 = 1;
    L4:
        if (r2 > 100) goto L6;
        this.lookupTable[r2] = r2 + "%";
        r2 = r2 + 1;
        goto L4
    L6:
        TextPaint r22 = new TextPaint(1);
        this.textPaint = r22;
        r22.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setColor(-1);
        this.textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.canCaptureChartSelection = true;
    }

    private /* synthetic */ void lambda$updateCharValues$0(PieChartViewData r1, ValueAnimator r2) {
        r1.drawingPart = ((Float) r2.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void updateCharValues(float r12, float r13, boolean r14) {
        if (this.values == null) goto L54;
        int r0 = ((StackLinearChartData) this.chartData).xPercentage.length;
        int r1 = this.lines.size();
        int r3 = 0;
        int r5 = -1;
        int r6 = -1;
        int r4 = 0;
    L6:
        if (r4 >= r0) goto L15;
        T r7 = this.chartData;
        if (((StackLinearChartData) r7).xPercentage[r4] < r12) goto L12;
        if (r6 != (-1)) goto L12;
        r6 = r4;
    L12:
        if (((StackLinearChartData) r7).xPercentage[r4] > r13) goto L14;
        r5 = r4;
    L14:
        r4 = r4 + 1;
        goto L6
    L15:
        if (r5 >= r6) goto L17;
        r6 = r5;
    L17:
        if (r14 == false) goto L19;
    L23:
        this.lastEndIndex = r5;
        this.lastStartIndex = r6;
        this.isEmpty = true;
        this.sum = 0.0f;
        int r132 = 0;
    L24:
        if (r132 >= r1) goto L26;
        this.values[r132] = 0.0f;
        r132 = r132 + 1;
    L26:
        if (r6 > r5) goto L38;
        int r133 = 0;
    L28:
        if (r133 >= r1) goto L37;
        float[] r02 = this.values;
        r02[r133] = r02[r133] + ((StackLinearChartData) this.chartData).lines.get(r133).y[r6];
        this.sum += ((StackLinearChartData) this.chartData).lines.get(r133).y[r6];
        if (this.isEmpty == false) goto L36;
        if (((PieChartViewData) this.lines.get(r133)).enabled == false) goto L36;
        if (((StackLinearChartData) this.chartData).lines.get(r133).y[r6] <= 0) goto L36;
        this.isEmpty = false;
    L36:
        r133 = r133 + 1;
        goto L28
    L37:
        r6 = r6 + 1;
        goto L26
    L38:
        if (r14 == true) goto L48;
    L39:
        if (r3 >= r1) goto L72;
        PieChartViewData r134 = (PieChartViewData) this.lines.get(r3);
        Animator r142 = r134.animator;
        if (r142 == null) goto L43;
        r142.cancel();
    L43:
        float r143 = this.sum;
        if (r143 != 0.0f) goto L46;
        float r03 = 0.0f;
    L47:
        ValueAnimator r144 = createAnimator(r134.drawingPart, r03, new PieChartView$$ExternalSyntheticLambda0(this, r134));
        r134.animator = r144;
        r144.start();
        r3 = r3 + 1;
        goto L39
    L46:
        r03 = this.values[r3] / r143;
        goto L47
    L72:
        return;
    L48:
        if (r3 >= r1) goto L73;
        float r135 = this.sum;
        ArrayList<L> r145 = this.lines;
        if (r135 != 0.0f) goto L52;
        ((PieChartViewData) r145.get(r3)).drawingPart = 0.0f;
    L53:
        r3 = r3 + 1;
        goto L48
    L52:
        ((PieChartViewData) r145.get(r3)).drawingPart = this.values[r3] / this.sum;
        goto L53
    L73:
        return;
    L19:
        if (this.lastEndIndex != r5) goto L23;
        if (this.lastStartIndex != r6) goto L23;
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public LegendSignatureView createLegendView() {
        PieLegendView r0 = new PieLegendView(getContext());
        this.pieLegendView = r0;
        return r0;
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView
    public PieChartViewData createLineViewData(ChartData.Line r1) {
        return new PieChartViewData(r1);
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView
    public /* bridge */ /* synthetic */ LineViewData createLineViewData(ChartData.Line r1) {
        return createLineViewData(r1);
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView
    public /* bridge */ /* synthetic */ StackLinearViewData createLineViewData(ChartData.Line r1) {
        return createLineViewData(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawBottomLine(Canvas r1) {
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawBottomSignature(Canvas r1) {
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r26) {
        if (this.chartData == 0) goto L102;
        if (r26 == null) goto L7;
        r26.save();
    L7:
        int r8 = 1;
        if (this.transitionMode != 1) goto L10;
        float r2 = this.transitionParams.progress;
        int r22 = (int) ((r2 * r2) * 255.0f);
    L11:
        boolean r3 = this.isEmpty;
        float r4 = this.emptyDataAlpha;
        float r9 = 1.0f;
        float r10 = 0.0f;
        if (r3 == false) goto L20;
        if (r4 == 0.0f) goto L25;
        float r42 = r4 - 0.12f;
        this.emptyDataAlpha = r42;
        if (r42 >= 0.0f) goto L18;
        this.emptyDataAlpha = 0.0f;
    L18:
        invalidate();
    L25:
        float r32 = this.emptyDataAlpha;
        int r11 = (int) (r22 * r32);
        float r33 = (r32 * 0.6f) + 0.4f;
        if (r26 == null) goto L28;
        r26.scale(r33, r33, this.chartArea.centerX(), this.chartArea.centerY());
    L28:
        float r23 = this.chartArea.width();
        float r34 = this.chartArea.height();
        RectF r35 = this.chartArea;
        if (r23 <= r34) goto L31;
        float r24 = r35.height();
    L32:
        float r25 = (int) (r24 * 0.45f);
        this.rectF.set(this.chartArea.centerX() - r25, (this.chartArea.centerY() + AndroidUtilities.dp(16.0f)) - r25, this.chartArea.centerX() + r25, (this.chartArea.centerY() + AndroidUtilities.dp(16.0f)) + r25);
        int r12 = this.lines.size();
        float r14 = 0.0f;
        int r27 = 0;
    L33:
        if (r27 >= r12) goto L36;
        r14 = r14 + (((PieChartViewData) this.lines.get(r27)).drawingPart * ((PieChartViewData) this.lines.get(r27)).alpha);
        r27 = r27 + 1;
        goto L33
    L36:
        if (r14 != 0.0f) goto L40;
        if (r26 == null) goto L100;
        r26.restore();
        return;
    L100:
        return;
    L40:
        float r15 = -90.0f;
        float r36 = -90.0f;
        int r28 = 0;
    L42:
        if (r28 >= r12) goto L70;
        if (((PieChartViewData) this.lines.get(r28)).alpha <= r10) goto L46;
    L48:
        ((PieChartViewData) this.lines.get(r28)).paint.setAlpha(r11);
        float r18 = (((PieChartViewData) this.lines.get(r28)).drawingPart / r14) * ((PieChartViewData) this.lines.get(r28)).alpha;
        this.darawingValuesPercentage[r28] = r18;
        if (r18 != r10) goto L51;
    L50:
        int r13 = r28;
        int r7 = r8;
        float r20 = r10;
        float r232 = r14;
    L69:
        r28 = r13 + 1;
        r8 = r7;
        r10 = r20;
        r14 = r232;
        goto L42
    L51:
        if (r26 == null) goto L53;
        r26.save();
    L53:
        double r5 = ((r18 / 2.0f) * 360.0f) + r36;
        if (((PieChartViewData) this.lines.get(r28)).selectionA <= r10) goto L60;
        r20 = r10;
        float r43 = BaseChartView.INTERPOLATOR.getInterpolation(((PieChartViewData) this.lines.get(r28)).selectionA);
        if (r26 == null) goto L58;
        r232 = r14;
        double r132 = r43;
        int r242 = r8;
        r26.translate((float) ((Math.cos(Math.toRadians(r5)) * AndroidUtilities.dp(8.0f)) * r132), (float) ((Math.sin(Math.toRadians(r5)) * AndroidUtilities.dp(8.0f)) * r132));
    L61:
        ((PieChartViewData) this.lines.get(r28)).paint.setStyle(Paint.Style.FILL_AND_STROKE);
        ((PieChartViewData) this.lines.get(r28)).paint.setStrokeWidth(1.0f);
        ((PieChartViewData) this.lines.get(r28)).paint.setAntiAlias(!BaseChartView.USE_LINES);
        if (r26 == null) goto L67;
        r7 = r242;
        if (this.transitionMode == r7) goto L66;
        r13 = r28;
        r26.drawArc(this.rectF, r36, r18 * 360.0f, true, ((PieChartViewData) this.lines.get(r28)).paint);
        ((PieChartViewData) this.lines.get(r13)).paint.setStyle(Paint.Style.STROKE);
        r26.restore();
    L68:
        ((PieChartViewData) this.lines.get(r13)).paint.setAlpha(255);
        r36 = r36 + (r18 * 360.0f);
        goto L69
    L66:
        r13 = r28;
        goto L68
    L67:
        r13 = r28;
        r7 = r242;
        goto L68
    L58:
        r242 = r8;
    L59:
        r232 = r14;
        goto L61
    L60:
        r242 = r8;
        r20 = r10;
        goto L59
    L46:
        if (((PieChartViewData) this.lines.get(r28)).enabled == true) goto L48;
    L70:
        float r202 = r10;
        float r233 = r14;
        if (r26 == null) goto L101;
        int r133 = 0;
    L73:
        if (r133 >= r12) goto L91;
        if (((PieChartViewData) this.lines.get(r133)).alpha <= r202) goto L77;
    L79:
        float r29 = (((PieChartViewData) this.lines.get(r133)).drawingPart * ((PieChartViewData) this.lines.get(r133)).alpha) / r233;
        r26.save();
        double r37 = ((r29 / 2.0f) * 360.0f) + r15;
        if (((PieChartViewData) this.lines.get(r133)).selectionA <= r202) goto L82;
        float r52 = BaseChartView.INTERPOLATOR.getInterpolation(((PieChartViewData) this.lines.get(r133)).selectionA);
        double r82 = r52;
        float r222 = r9;
        float r21 = r15;
        r26.translate((float) ((Math.cos(Math.toRadians(r37)) * AndroidUtilities.dp(8.0f)) * r82), (float) ((Math.sin(Math.toRadians(r37)) * AndroidUtilities.dp(8.0f)) * r82));
    L83:
        int r53 = (int) (100.0f * r29);
        if (r29 < 0.02f) goto L89;
        if (r53 <= 0) goto L89;
        if (r53 > 100) goto L89;
        float r6 = (float) ((this.rectF.width() * 0.42f) * Math.sqrt(r222 - r29));
        this.textPaint.setTextSize(this.MIN_TEXT_SIZE + (this.MAX_TEXT_SIZE * r29));
        this.textPaint.setAlpha((int) (r11 * ((PieChartViewData) this.lines.get(r133)).alpha));
        double r92 = r6;
        r26.drawText(this.lookupTable[r53], (float) (this.rectF.centerX() + (Math.cos(Math.toRadians(r37)) * r92)), ((float) (this.rectF.centerY() + (r92 * Math.sin(Math.toRadians(r37))))) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.textPaint);
    L89:
        r26.restore();
        ((PieChartViewData) this.lines.get(r133)).paint.setAlpha(255);
        r15 = r21 + (r29 * 360.0f);
    L90:
        r133 = r133 + 1;
        r9 = r222;
        goto L73
    L82:
        r222 = r9;
        r21 = r15;
        goto L83
    L77:
        if (((PieChartViewData) this.lines.get(r133)).enabled == true) goto L79;
        r222 = r9;
        goto L90
    L91:
        r26.restore();
        return;
    L101:
        return;
    L31:
        r24 = r35.width();
        goto L32
    L20:
        if (r4 == 1.0f) goto L25;
        float r44 = r4 + 0.12f;
        this.emptyDataAlpha = r44;
        if (r44 <= 1.0f) goto L24;
        this.emptyDataAlpha = 1.0f;
    L24:
        invalidate();
        goto L25
    L10:
        r22 = 255;
        goto L11
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawHorizontalLines(Canvas r1, ChartHorizontalLinesData r2) {
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r22) {
        T r1 = this.chartData;
        if (r1 == 0) goto L49;
        int r12 = ((StackLinearChartData) r1).xPercentage.length;
        int r2 = this.lines.size();
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= this.lines.size()) goto L8;
        ((LineViewData) this.lines.get(r4)).linesPathBottomSize = 0;
        r4 = r4 + 1;
        goto L6
    L8:
        float r5 = (1.0f / ((StackLinearChartData) this.chartData).xPercentage.length) * this.pickerWidth;
        int r42 = 0;
    L9:
        if (r42 >= r12) goto L46;
        float r6 = (r5 / 2.0f) + (((StackLinearChartData) this.chartData).xPercentage[r42] * (this.pickerWidth - r5));
        float r7 = 0.0f;
        int r8 = 1;
        int r9 = r3;
        int r11 = r9;
        float r10 = 0.0f;
        boolean r122 = true;
    L11:
        if (r9 >= r2) goto L23;
        LineViewData r13 = (LineViewData) this.lines.get(r9);
        boolean r14 = r13.enabled;
        if (r14 == false) goto L15;
    L17:
        int r16 = r42;
        float r32 = r13.line.y[r16] * r13.alpha;
        r10 = r10 + r32;
        if (r32 <= 0.0f) goto L22;
        r11 = r11 + 1;
        if (r14 == false) goto L22;
        r122 = false;
    L22:
        r9 = r9 + 1;
        r42 = r16;
        goto L11
    L15:
        if (r13.alpha != 0.0f) goto L17;
        r16 = r42;
        goto L22
    L23:
        int r162 = r42;
        float r43 = 0.0f;
        int r33 = 0;
    L24:
        if (r33 >= r2) goto L45;
        LineViewData r92 = (LineViewData) this.lines.get(r33);
        if (r92.enabled == false) goto L28;
    L30:
        long[] r132 = r92.line.y;
        if (r11 != r8) goto L37;
        if (r132[r162] != 0) goto L35;
    L34:
        float r133 = r7;
    L43:
        int r142 = this.pikerHeight;
        float r134 = r133 * r142;
        float[] r15 = r92.linesPath;
        int r72 = r92.linesPathBottomSize;
        int r82 = r72 + 1;
        r92.linesPathBottomSize = r82;
        r15[r72] = r6;
        int r19 = r12;
        int r17 = r72 + 2;
        r92.linesPathBottomSize = r17;
        r15[r82] = (r142 - r134) - r43;
        int r18 = r72 + 3;
        r92.linesPathBottomSize = r18;
        r15[r17] = r6;
        r92.linesPathBottomSize = r72 + 4;
        r15[r18] = r142 - r43;
        r43 = r43 + r134;
    L44:
        r33 = r33 + 1;
        r12 = r19;
        r7 = 0.0f;
        r8 = 1;
        goto L24
    L35:
        r133 = r92.alpha;
        goto L43
    L37:
        if (r10 == r7) goto L34;
        if (r122 == false) goto L42;
        float r143 = r92.alpha;
        float r135 = (r132[r162] / r10) * r143;
    L41:
        r133 = r135 * r143;
        goto L43
    L42:
        r135 = r132[r162] / r10;
        r143 = r92.alpha;
        goto L41
    L28:
        if (r92.alpha != r7) goto L30;
        r19 = r12;
        goto L44
    L45:
        r42 = r162 + 1;
        r3 = 0;
        goto L9
    L46:
        int r110 = 0;
    L47:
        if (r110 >= r2) goto L61;
        LineViewData r34 = (LineViewData) this.lines.get(r110);
        r34.paint.setStrokeWidth(r5);
        r34.paint.setAlpha(255);
        r34.paint.setAntiAlias(false);
        r22.drawLines(r34.linesPath, 0, r34.linesPathBottomSize, r34.paint);
        r110 = r110 + 1;
        goto L47
    L61:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawSelection(Canvas r1) {
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawSignaturesToHorizontalLines(Canvas r1, ChartHorizontalLinesData r2) {
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView
    public void fillTransitionParams(TransitionParams r6) {
        drawChart(null);
        float r0 = 0.0f;
        int r1 = 0;
    L3:
        float[] r2 = this.darawingValuesPercentage;
        if (r1 >= r2.length) goto L6;
        r0 = r0 + r2[r1];
        r6.angle[r1] = (360.0f * r0) - 180.0f;
        r1 = r1 + 1;
        goto L3
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void onActionUp() {
        this.currentSelection = -1;
        this.pieLegendView.setVisibility(8);
        invalidate();
    }

    @Override // org.telegram.ui.Charts.StackLinearChartView, org.telegram.ui.Charts.BaseChartView, android.view.View
    public void onDraw(Canvas r6) {
        if (this.chartData == 0) goto L22;
        int r0 = 0;
    L6:
        if (r0 >= this.lines.size()) goto L22;
        int r1 = this.currentSelection;
        ArrayList<L> r2 = this.lines;
        if (r0 != r1) goto L16;
        if (((PieChartViewData) r2.get(r0)).selectionA >= 1.0f) goto L21;
        ((PieChartViewData) this.lines.get(r0)).selectionA += 0.1f;
        if (((PieChartViewData) this.lines.get(r0)).selectionA <= 1.0f) goto L14;
        ((PieChartViewData) this.lines.get(r0)).selectionA = 1.0f;
    L14:
        invalidate();
    L21:
        r0 = r0 + 1;
        goto L6
    L16:
        if (((PieChartViewData) r2.get(r0)).selectionA <= 0.0f) goto L21;
        ((PieChartViewData) this.lines.get(r0)).selectionA -= 0.1f;
        if (((PieChartViewData) this.lines.get(r0)).selectionA >= 0.0f) goto L20;
        ((PieChartViewData) this.lines.get(r0)).selectionA = 0.0f;
    L20:
        invalidate();
    L22:
        super.onDraw(r6);
    }

    @Override // org.telegram.ui.Charts.BaseChartView, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        if (getMeasuredWidth() == this.oldW) goto L10;
        this.oldW = getMeasuredWidth();
        float r12 = this.chartArea.width();
        float r22 = this.chartArea.height();
        RectF r23 = this.chartArea;
        if (r12 <= r22) goto L7;
        float r13 = r23.height();
    L8:
        int r14 = (int) (r13 * 0.45f);
        this.MIN_TEXT_SIZE = r14 / 13;
        this.MAX_TEXT_SIZE = r14 / 7;
        return;
    L7:
        r13 = r23.width();
        goto L8
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void onPickerDataChanged(boolean r1, boolean r2, boolean r3) {
        super.onPickerDataChanged(r1, r2, r3);
        T r12 = this.chartData;
        if (r12 != 0) goto L5;
        return;
    L5:
        if (((StackLinearChartData) r12).xPercentage == null) goto L10;
        ChartPickerDelegate r13 = this.pickerDelegate;
        updateCharValues(r13.pickerStart, r13.pickerEnd, r2);
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView, org.telegram.ui.Charts.ChartPickerDelegate.Listener
    public void onPickerJumpTo(float r2, float r3, boolean r4) {
        if (this.chartData != 0) goto L5;
        return;
    L5:
        if (r4 == false) goto L8;
        updateCharValues(r2, r3, false);
        return;
    L8:
        updateIndexes();
        invalidate();
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void selectXOnChart(int r14, int r15) {
        if (this.chartData != 0) goto L5;
        return;
    L5:
        if (this.isEmpty == true) goto L44;
        float r142 = (float) (Math.toDegrees(Math.atan2((this.chartArea.centerY() + AndroidUtilities.dp(16.0f)) - r15, this.chartArea.centerX() - r14)) - 90.0d);
        float r152 = 0.0f;
        if (r142 >= 0.0f) goto L10;
        r142 = (float) (r142 + 360.0d);
    L10:
        float r143 = r142 / 360.0f;
        int r2 = 0;
        float r4 = 0.0f;
        int r3 = 0;
    L12:
        if (r3 >= this.lines.size()) goto L25;
        if (((PieChartViewData) this.lines.get(r3)).enabled == true) goto L19;
        if (((PieChartViewData) this.lines.get(r3)).alpha != 0.0f) goto L19;
    L24:
        r3 = r3 + 1;
    L19:
        if (r143 <= r4) goto L23;
        float r5 = this.darawingValuesPercentage[r3];
        if (r143 >= (r4 + r5)) goto L23;
        r152 = r4 + r5;
    L27:
        if (this.currentSelection == r3) goto L37;
        if (r3 < 0) goto L37;
        this.currentSelection = r3;
        invalidate();
        this.pieLegendView.setVisibility(0);
        LineViewData r144 = (LineViewData) this.lines.get(r3);
        this.pieLegendView.setData(r144.line.name, (int) this.values[this.currentSelection], r144.lineColor);
        this.pieLegendView.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE));
        double r7 = this.rectF.width() / 2.0f;
        double r9 = (r152 * 360.0f) - 90.0f;
        double r145 = (r4 * 360.0f) - 90.0f;
        int r0 = (int) Math.min(this.rectF.centerX() + (Math.cos(Math.toRadians(r9)) * r7), this.rectF.centerX() + (Math.cos(Math.toRadians(r145)) * r7));
        if (r0 < 0) goto L34;
        r2 = r0;
    L34:
        if ((this.pieLegendView.getMeasuredWidth() + r2) <= (getMeasuredWidth() - AndroidUtilities.dp(16.0f))) goto L36;
        r2 = r2 - ((this.pieLegendView.getMeasuredWidth() + r2) - (getMeasuredWidth() - AndroidUtilities.dp(16.0f)));
    L36:
        int r146 = ((int) Math.min(this.rectF.centerY(), (int) Math.min(this.rectF.centerY() + (Math.sin(Math.toRadians(r145)) * r7), this.rectF.centerY() + (r7 * Math.sin(Math.toRadians(r9)))))) - AndroidUtilities.dp(50.0f);
        this.pieLegendView.setTranslationX(r2);
        this.pieLegendView.setTranslationY(r146);
        AndroidUtilities.vibrateCursor(this);
    L37:
        moveLegend();
        return;
    L23:
        r4 = r4 + this.darawingValuesPercentage[r3];
        goto L24
    L25:
        r3 = -1;
        r4 = 0.0f;
        goto L27
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public /* bridge */ /* synthetic */ boolean setData(ChartData r1) {
        return setData((StackLinearChartData) r1);
    }

    public boolean setData(StackLinearChartData r3) {
        boolean r0 = super.setData(r3);
        if (r3 == null) goto L5;
        this.values = new float[r3.lines.size()];
        this.darawingValuesPercentage = new float[r3.lines.size()];
        onPickerDataChanged(false, true, false);
    L5:
        return r0;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void updatePicker(ChartData r8, long r9) {
        int r0 = r8.x.length;
        long r92 = r9 - (r9 % DurationKt.MILLIS_IN_DAY);
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r92 < r8.x[r2]) goto L7;
        r3 = r2;
    L7:
        r2 = r2 + 1;
        goto L3
    L9:
        if (r8.xPercentage.length >= 2) goto L11;
        float r93 = 0.5f;
    L12:
        if (r3 != 0) goto L15;
        ChartPickerDelegate r7 = this.pickerDelegate;
        r7.pickerStart = 0.0f;
        r7.pickerEnd = r93;
        return;
    L15:
        int r82 = r8.x.length - 1;
        ChartPickerDelegate r22 = this.pickerDelegate;
        if (r3 < r82) goto L19;
        r22.pickerStart = 1.0f - r93;
        r22.pickerEnd = 1.0f;
        return;
    L19:
        float r83 = r3 * r93;
        r22.pickerStart = r83;
        float r84 = r83 + r93;
        r22.pickerEnd = r84;
        if (r84 <= 1.0f) goto L22;
        r22.pickerEnd = 1.0f;
    L22:
        onPickerDataChanged(true, true, false);
        return;
    L11:
        r93 = 1.0f / r8.x.length;
        goto L12
    }
}
