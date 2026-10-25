package org.telegram.ui.Charts;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.SegmentTree;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.data.StackBarChartData;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.StackBarViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class StackBarChartView extends BaseChartView<StackBarChartData, StackBarViewData> {
    private long[] yMaxPoints;

    /* renamed from: org.telegram.ui.Charts.StackBarChartView$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ StackBarChartView this$0;

        public AnonymousClass1(StackBarChartView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            StackBarChartView r0 = this.this$0;
            r0.pickerMaxHeight = ((Float) r2.getAnimatedValue()).floatValue();
            StackBarChartView r1 = this.this$0;
            r1.invalidatePickerChart = true;
            r1.invalidate();
        }
    }

    public StackBarChartView(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
        this.superDraw = true;
        this.useAlphaSignature = true;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public /* bridge */ /* synthetic */ LineViewData createLineViewData(ChartData.Line r1) {
        return createLineViewData(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public StackBarViewData createLineViewData(ChartData.Line r2) {
        return new StackBarViewData(r2, this.resourcesProvider);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r23) {
        Canvas r1 = r23;
        T r2 = this.chartData;
        if (r2 != 0) goto L5;
        return;
    L5:
        float r3 = this.chartWidth;
        ChartPickerDelegate r4 = this.pickerDelegate;
        float r5 = r4.pickerEnd;
        float r42 = r4.pickerStart;
        float r7 = r3 / (r5 - r42);
        float r32 = BaseChartView.HORIZONTAL_PADDING;
        float r8 = (r42 * r7) - r32;
        boolean r9 = true;
        if (((StackBarChartData) r2).xPercentage.length >= 2) goto L8;
        float r10 = 1.0f;
        float r11 = 1.0f;
    L9:
        int r22 = ((int) (r32 / r11)) + 1;
        int r43 = 0;
        int r33 = Math.max(0, (this.startXIndex - r22) - 2);
        int r24 = Math.min(((StackBarChartData) this.chartData).xPercentage.length - 1, (this.endXIndex + r22) + 2);
        int r12 = 0;
    L11:
        if (r12 >= this.lines.size()) goto L13;
        ((LineViewData) this.lines.get(r12)).linesPathBottomSize = 0;
        r12 = r12 + 1;
        goto L11
    L13:
        r1.save();
        int r122 = this.transitionMode;
        float r14 = 0.0f;
        if (r122 != 2) goto L16;
        this.postTransition = true;
        this.selectionA = 0.0f;
        TransitionParams r52 = this.transitionParams;
        float r123 = r52.progress;
        float r15 = 1.0f - r123;
        float r16 = 2.0f;
        r1.scale((r123 * 2.0f) + 1.0f, 1.0f, r52.pX, r52.pY);
    L24:
        if (this.selectedIndex >= 0) goto L26;
    L28:
        r9 = false;
    L29:
        if (r33 > r24) goto L45;
        if (this.selectedIndex != r33) goto L34;
        if (r9 == false) goto L34;
    L44:
        r33 = r33 + 1;
        r14 = r14;
        r7 = r7;
        r24 = r24;
        r43 = 0;
    L34:
        int r53 = r43;
        float r124 = r14;
    L36:
        if (r53 >= this.lines.size()) goto L44;
        LineViewData r13 = (LineViewData) this.lines.get(r53);
        float r17 = r14;
        if (r13.enabled == false) goto L40;
    L42:
        long[] r142 = r13.line.y;
        float r18 = ((r11 / r16) + (((StackBarChartData) this.chartData).xPercentage[r33] * (r7 - r11))) - r8;
        float r19 = r7;
        float r6 = ((r142[r33] / this.currentMaxHeight) * ((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT)) * r13.alpha;
        float[] r143 = r13.linesPath;
        int r44 = r13.linesPathBottomSize;
        int r20 = r24;
        int r25 = r44 + 1;
        r13.linesPathBottomSize = r25;
        r143[r44] = r18;
        int r26 = r44 + 2;
        r13.linesPathBottomSize = r26;
        r143[r25] = ((getMeasuredHeight() - this.chartBottom) - r6) - r124;
        int r72 = r44 + 3;
        r13.linesPathBottomSize = r72;
        r143[r26] = r18;
        r13.linesPathBottomSize = r44 + 4;
        r143[r72] = (getMeasuredHeight() - this.chartBottom) - r124;
        r124 = r124 + r6;
    L43:
        r53 = r53 + 1;
        r14 = r17;
        r7 = r19;
        r24 = r20;
        goto L36
    L40:
        if (r13.alpha != r17) goto L42;
        r20 = r24;
        r19 = r7;
        goto L43
    L45:
        float r192 = r7;
        float r172 = r14;
        int r27 = 0;
    L47:
        if (r27 >= this.lines.size()) goto L63;
        StackBarViewData r34 = (StackBarViewData) this.lines.get(r27);
        if (r9 == false) goto L51;
    L54:
        Paint r45 = r34.unselectedPaint;
    L55:
        if (r9 == false) goto L58;
        r34.unselectedPaint.setColor(ColorUtils.blendARGB(r34.lineColor, r34.blendColor, this.selectionA));
    L58:
        if (this.postTransition == false) goto L61;
        r34.unselectedPaint.setColor(ColorUtils.blendARGB(r34.lineColor, r34.blendColor, 1.0f));
    L61:
        r45.setAlpha((int) (255.0f * r15));
        r45.setStrokeWidth(r10);
        r1.drawLines(r34.linesPath, 0, r34.linesPathBottomSize, r45);
        r27 = r27 + 1;
        goto L47
    L51:
        if (this.postTransition == true) goto L54;
        r45 = r34.paint;
        goto L55
    L63:
        if (r9 == false) goto L74;
        int r92 = 0;
        float r125 = r172;
    L66:
        if (r92 >= this.lines.size()) goto L74;
        LineViewData r28 = (LineViewData) this.lines.get(r92);
        if (r28.enabled == false) goto L70;
    L72:
        long[] r35 = r28.line.y;
        float r46 = ((r11 / r16) + (((StackBarChartData) this.chartData).xPercentage[this.selectedIndex] * (r192 - r11))) - r8;
        float r132 = ((r35[r6] / this.currentMaxHeight) * ((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT)) * r28.alpha;
        r28.paint.setStrokeWidth(r10);
        r28.paint.setAlpha((int) (r15 * 255.0f));
        r1.drawLine(r46, ((getMeasuredHeight() - this.chartBottom) - r132) - r125, r46, (getMeasuredHeight() - this.chartBottom) - r125, r28.paint);
        r125 = r125 + r132;
    L73:
        r92 = r92 + 1;
        r1 = r23;
        goto L66
    L70:
        if (r28.alpha != r172) goto L72;
    L74:
        r23.restore();
        return;
    L26:
        if (this.legendShowing == false) goto L28;
    L16:
        r16 = 2.0f;
        if (r122 != 1) goto L20;
        TransitionParams r54 = this.transitionParams;
        r15 = r54.progress;
        r1.scale(r15, 1.0f, r54.pX, r54.pY);
        goto L24
    L20:
        if (r122 != 3) goto L22;
        r15 = this.transitionParams.progress;
        goto L24
    L22:
        r15 = 1.0f;
        goto L24
    L8:
        float r47 = ((StackBarChartData) r2).xPercentage[1] * r7;
        r10 = ((StackBarChartData) r2).xPercentage[1] * (r7 - r47);
        r11 = r47;
        goto L9
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r19) {
        T r1 = this.chartData;
        if (r1 == 0) goto L51;
        int r12 = ((StackBarChartData) r1).xPercentage.length;
        int r2 = this.lines.size();
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= this.lines.size()) goto L8;
        ((LineViewData) this.lines.get(r4)).linesPathBottomSize = 0;
        r4 = r4 + 1;
        goto L6
    L8:
        boolean r5 = true;
        int r42 = Math.max(1, Math.round(r12 / 200.0f));
        long[] r6 = this.yMaxPoints;
        if (r6 != null) goto L11;
    L12:
        this.yMaxPoints = new long[r2];
    L13:
        int r62 = 0;
    L14:
        T r7 = this.chartData;
        if (r62 >= r12) goto L44;
        float r72 = ((StackBarChartData) r7).xPercentage[r62] * this.pickerWidth;
        int r8 = r3;
    L17:
        float r9 = 0.0f;
        if (r8 >= r2) goto L29;
        LineViewData r10 = (LineViewData) this.lines.get(r8);
        if (r10.enabled == false) goto L22;
    L24:
        long r102 = r10.line.y[r62];
        long[] r92 = this.yMaxPoints;
        if (r102 <= r92[r8]) goto L27;
        r92[r8] = r102;
    L27:
        r8 = r8 + 1;
        goto L17
    L22:
        if (r10.alpha != 0.0f) goto L24;
    L29:
        if ((r62 % r42) != 0) goto L43;
        int r82 = r3;
        float r103 = 0.0f;
    L31:
        if (r82 >= r2) goto L43;
        LineViewData r11 = (LineViewData) this.lines.get(r82);
        if (r11.enabled == true) goto L38;
        if (r11.alpha != r9) goto L38;
        int r17 = r12;
        boolean r16 = r5;
    L42:
        r82 = r82 + 1;
        r5 = r16;
        r12 = r17;
        r9 = 0.0f;
    L38:
        if (BaseChartView.ANIMATE_PICKER_SIZES == false) goto L40;
        float r122 = this.pickerMaxHeight;
    L41:
        long[] r13 = this.yMaxPoints;
        float r14 = (r13[r82] / r122) * r11.alpha;
        int r123 = this.pikerHeight;
        float r142 = r14 * r123;
        float[] r15 = r11.linesPath;
        r16 = r5;
        int r52 = r11.linesPathBottomSize;
        int r93 = r52 + 1;
        r11.linesPathBottomSize = r93;
        r15[r52] = r72;
        int r32 = r52 + 2;
        r11.linesPathBottomSize = r32;
        r17 = r12;
        r15[r93] = (r123 - r142) - r103;
        int r18 = r52 + 3;
        r11.linesPathBottomSize = r18;
        r15[r32] = r72;
        r11.linesPathBottomSize = r52 + 4;
        r15[r18] = r123 - r103;
        r103 = r103 + r142;
        r13[r82] = 0;
        goto L42
    L40:
        r122 = ((StackBarChartData) this.chartData).maxValue;
    L43:
        r62 = r62 + 1;
        r5 = r5;
        r12 = r12;
        r3 = 0;
        goto L14
    L44:
        boolean r162 = r5;
        if (((StackBarChartData) r7).xPercentage.length >= 2) goto L47;
        float r110 = 1.0f;
    L48:
        int r33 = 0;
    L49:
        if (r33 >= r2) goto L63;
        LineViewData r53 = (LineViewData) this.lines.get(r33);
        r53.paint.setStrokeWidth(r42 * r110);
        r53.paint.setAlpha(255);
        r19.drawLines(r53.linesPath, 0, r53.linesPathBottomSize, r53.paint);
        r33 = r33 + 1;
        goto L49
    L63:
        return;
    L47:
        r110 = ((StackBarChartData) r7).xPercentage[r162 ? 1 : 0] * this.pickerWidth;
        goto L48
    L11:
        if (r6.length >= r2) goto L13;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawSelection(Canvas r1) {
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public long findMaxValue(int r1, int r2) {
        return ((StackBarChartData) this.chartData).findMax(r1, r2);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public float getMinDistance() {
        return 0.1f;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void initPickerMaxHeight() {
        super.initPickerMaxHeight();
        this.pickerMaxHeight = 0.0f;
        int r0 = ((StackBarChartData) this.chartData).x.length;
        int r1 = this.lines.size();
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L14;
        long r4 = 0;
        int r6 = 0;
    L5:
        if (r6 >= r1) goto L10;
        StackBarViewData r7 = (StackBarViewData) this.lines.get(r6);
        if (r7.enabled == false) goto L9;
        r4 = r4 + r7.line.y[r3];
    L9:
        r6 = r6 + 1;
        goto L5
    L10:
        float r42 = r4;
        if (r42 <= this.pickerMaxHeight) goto L13;
        this.pickerMaxHeight = r42;
    L13:
        r3 = r3 + 1;
        goto L3
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void onCheckChanged() {
        int r0 = ((StackBarChartData) this.chartData).lines.get(0).y.length;
        int r2 = ((StackBarChartData) this.chartData).lines.size();
        ((StackBarChartData) this.chartData).ySum = new long[r0];
        int r3 = 0;
    L3:
        T r4 = this.chartData;
        if (r3 >= r0) goto L12;
        ((StackBarChartData) r4).ySum[r3] = 0;
        int r42 = 0;
    L6:
        if (r42 >= r2) goto L11;
        if (((StackBarViewData) this.lines.get(r42)).enabled == false) goto L10;
        T r5 = this.chartData;
        long[] r6 = ((StackBarChartData) r5).ySum;
        r6[r3] = r6[r3] + ((StackBarChartData) r5).lines.get(r42).y[r3];
    L10:
        r42 = r42 + 1;
        goto L6
    L11:
        r3 = r3 + 1;
        goto L3
    L12:
        ((StackBarChartData) r4).ySumSegmentTree = new SegmentTree(((StackBarChartData) r4).ySum);
        super.onCheckChanged();
    }

    @Override // org.telegram.ui.Charts.BaseChartView, android.view.View
    public void onDraw(Canvas r3) {
        tick();
        drawChart(r3);
        drawBottomLine(r3);
        this.tmpN = this.horizontalLines.size();
        int r0 = 0;
    L3:
        this.tmpI = r0;
        int r02 = this.tmpI;
        if (r02 >= this.tmpN) goto L6;
        drawHorizontalLines(r3, this.horizontalLines.get(r02));
        drawSignaturesToHorizontalLines(r3, this.horizontalLines.get(this.tmpI));
        r0 = this.tmpI + 1;
        goto L3
    L6:
        drawBottomSignature(r3);
        drawPicker(r3);
        drawSelection(r3);
        super.onDraw(r3);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void selectXOnChart(int r8, int r9) {
        T r92 = this.chartData;
        if (r92 == 0) goto L29;
        int r0 = this.selectedIndex;
        float r1 = this.chartFullWidth;
        float r2 = (this.pickerDelegate.pickerStart * r1) - BaseChartView.HORIZONTAL_PADDING;
        if (((StackBarChartData) r92).xPercentage.length >= 2) goto L8;
        float r3 = 1.0f;
    L9:
        float r82 = (r8 + r2) / (r1 - r3);
        this.selectedCoordinate = r82;
        if (r82 >= 0.0f) goto L13;
        this.selectedIndex = 0;
        this.selectedCoordinate = 0.0f;
    L22:
        if (r0 == this.selectedIndex) goto L28;
        this.legendShowing = true;
        animateLegend(true);
        moveLegend(r2);
        BaseChartView.DateSelectionListener r83 = this.dateSelectionListener;
        if (r83 == null) goto L26;
        r83.onDateSelected(getSelectedDate());
    L26:
        invalidate();
        runSmoothHaptic();
        return;
    L28:
        return;
    L13:
        if (r82 <= 1.0f) goto L15;
        this.selectedIndex = ((StackBarChartData) r92).x.length - 1;
        this.selectedCoordinate = 1.0f;
        goto L22
    L15:
        int r84 = ((StackBarChartData) r92).findIndex(this.startXIndex, this.endXIndex, r82);
        this.selectedIndex = r84;
        int r93 = this.endXIndex;
        if (r84 <= r93) goto L18;
        this.selectedIndex = r93;
    L18:
        int r85 = this.selectedIndex;
        int r94 = this.startXIndex;
        if (r85 >= r94) goto L22;
        this.selectedIndex = r94;
        goto L22
    L8:
        r3 = ((StackBarChartData) r92).xPercentage[1] * r1;
        goto L9
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void updatePickerMinMaxHeight() {
        if (BaseChartView.ANIMATE_PICKER_SIZES == false) goto L34;
        int r0 = ((StackBarChartData) this.chartData).x.length;
        int r1 = this.lines.size();
        int r5 = 0;
        long r6 = 0;
    L6:
        if (r5 >= r0) goto L18;
        int r8 = 0;
        long r9 = 0;
    L8:
        if (r8 >= r1) goto L14;
        StackBarViewData r11 = (StackBarViewData) this.lines.get(r8);
        if (r11.enabled == false) goto L12;
        r9 = r9 + r11.line.y[r5];
    L12:
        r8 = r8 + 1;
        goto L8
    L14:
        if (r9 <= r6) goto L16;
        r6 = r9;
    L16:
        r5 = r5 + 1;
        goto L6
    L18:
        if (r6 <= 0) goto L32;
        float r02 = r6;
        if (r02 == this.animatedToPickerMaxHeight) goto L33;
        this.animatedToPickerMaxHeight = r02;
        Animator r03 = this.pickerAnimator;
        if (r03 == null) goto L24;
        r03.cancel();
    L24:
        ValueAnimator r04 = createAnimator(this.pickerMaxHeight, this.animatedToPickerMaxHeight, new AnonymousClass1(this));
        this.pickerAnimator = r04;
        r04.start();
        return;
    L33:
        return;
    L32:
        return;
    }
}
