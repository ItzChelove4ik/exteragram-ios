package org.telegram.ui.Charts;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.data.DoubleLinearChartData;
import org.telegram.ui.Charts.view_data.ChartHorizontalLinesData;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class DoubleLinearChartView extends BaseChartView<DoubleLinearChartData, LineViewData> {

    /* renamed from: org.telegram.ui.Charts.DoubleLinearChartView$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ DoubleLinearChartView this$0;

        public AnonymousClass1(DoubleLinearChartView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            DoubleLinearChartView r0 = this.this$0;
            r0.pickerMaxHeight = ((Float) r2.getAnimatedValue()).floatValue();
            DoubleLinearChartView r1 = this.this$0;
            r1.invalidatePickerChart = true;
            r1.invalidate();
        }
    }

    public DoubleLinearChartView(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public ChartHorizontalLinesData createHorizontalLinesData(long r15, long r17, int r19) {
        T r0 = this.chartData;
        float r3 = 1.0f;
        if (((DoubleLinearChartData) r0).linesK.length >= 2) goto L5;
    L4:
        float r10 = r3;
        return new ChartHorizontalLinesData(r15, r17, this.useMinHeight, r10, r19, this.signaturePaint, this.signaturePaint2);
    L5:
        char r2 = 0;
        if (((DoubleLinearChartData) r0).linesK[0] != 1.0f) goto L8;
        r2 = 1;
    L8:
        r3 = ((DoubleLinearChartData) r0).linesK[r2];
        goto L4
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public LineViewData createLineViewData(ChartData.Line r3) {
        return new LineViewData(r3, false, this.resourcesProvider);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r22) {
        if (this.chartData == 0) goto L70;
        float r2 = this.chartWidth;
        ChartPickerDelegate r3 = this.pickerDelegate;
        float r4 = r3.pickerEnd;
        float r32 = r3.pickerStart;
        float r23 = r2 / (r4 - r32);
        float r33 = (r32 * r23) - BaseChartView.HORIZONTAL_PADDING;
        r22.save();
        int r42 = this.transitionMode;
        float r5 = 2.0f;
        float r6 = 0.0f;
        int r7 = 2;
        float r8 = 1.0f;
        int r9 = 1;
        if (r42 != 2) goto L11;
        TransitionParams r43 = this.transitionParams;
        float r10 = r43.progress;
        if (r10 <= 0.5f) goto L9;
        float r11 = 0.0f;
    L10:
        r22.scale((r10 * 2.0f) + 1.0f, 1.0f, r43.pX, r43.pY);
    L21:
        int r44 = 0;
        int r102 = 0;
    L23:
        if (r102 >= this.lines.size()) goto L58;
        LineViewData r12 = (LineViewData) this.lines.get(r102);
        if (r12.enabled == false) goto L27;
    L29:
        long[] r13 = r12.line.y;
        r12.chartPath.reset();
        T r14 = this.chartData;
        if (((DoubleLinearChartData) r14).xPercentage.length >= r7) goto L32;
        float r142 = r8;
    L33:
        int r143 = ((int) (BaseChartView.HORIZONTAL_PADDING / r142)) + r9;
        int r15 = Math.max(r44, this.startXIndex - r143);
        float r16 = r5;
        int r52 = Math.min(((DoubleLinearChartData) this.chartData).xPercentage.length - r9, this.endXIndex + r143);
        int r144 = r44;
        int r62 = r9;
    L34:
        if (r15 > r52) goto L49;
        long r72 = r13[r15];
        if (r72 >= 0) goto L38;
        float r19 = r23;
    L48:
        r15 = r15 + 1;
        r23 = r19;
        goto L34
    L38:
        T r92 = this.chartData;
        float r45 = (((DoubleLinearChartData) r92).xPercentage[r15] * r23) - r33;
        float r73 = r72 * ((DoubleLinearChartData) r92).linesK[r102];
        float r82 = this.currentMinHeight;
        float r74 = (r73 - r82) / (this.currentMaxHeight - r82);
        float r83 = r12.paint.getStrokeWidth() / r16;
        r19 = r23;
        float r24 = ((getMeasuredHeight() - this.chartBottom) - r83) - (r74 * (((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT) - r83));
        if (BaseChartView.USE_LINES == false) goto L44;
        float[] r75 = r12.linesPath;
        if (r144 != 0) goto L43;
        int r84 = r144 + 1;
        r75[r144] = r45;
        r144 = r144 + 2;
        r75[r84] = r24;
        goto L48
    L43:
        r75[r144] = r45;
        r75[r144 + 1] = r24;
        int r85 = r144 + 3;
        r75[r144 + 2] = r45;
        r144 = r144 + 4;
        r75[r85] = r24;
        goto L48
    L44:
        Path r76 = r12.chartPath;
        if (r62 == 0) goto L47;
        r76.moveTo(r45, r24);
        r62 = 0;
        goto L48
    L47:
        r76.lineTo(r45, r24);
        goto L48
    L49:
        float r192 = r23;
        int r25 = this.endXIndex - this.startXIndex;
        Paint r46 = r12.paint;
        if (r25 <= 100) goto L52;
        r46.setStrokeCap(Paint.Cap.SQUARE);
    L53:
        r12.paint.setAlpha((int) ((r12.alpha * 255.0f) * r11));
        if (BaseChartView.USE_LINES == true) goto L56;
        r22.drawPath(r12.chartPath, r12.paint);
        int r53 = 0;
    L57:
        r102 = r102 + 1;
        r44 = r53;
        r5 = r16;
        r23 = r192;
        r6 = 0.0f;
        r7 = 2;
        r8 = 1.0f;
        r9 = 1;
        goto L23
    L56:
        r53 = 0;
        r22.drawLines(r12.linesPath, 0, r144, r12.paint);
        goto L57
    L52:
        r46.setStrokeCap(Paint.Cap.ROUND);
        goto L53
    L32:
        r142 = ((DoubleLinearChartData) r14).xPercentage[r9] * r23;
        goto L33
    L27:
        if (r12.alpha != r6) goto L29;
        r192 = r23;
        r16 = r5;
        r53 = r44;
        goto L57
    L58:
        r22.restore();
        return;
    L9:
        r11 = 1.0f - (r10 * 2.0f);
        goto L10
    L11:
        if (r42 != 1) goto L18;
        float r47 = this.transitionParams.progress;
        if (r47 >= 0.3f) goto L15;
        r11 = 0.0f;
    L16:
        r22.save();
        TransitionParams r48 = this.transitionParams;
        float r103 = r48.progress;
        r22.scale(r103, r103, r48.pX, r48.pY);
        goto L21
    L15:
        r11 = r47;
        goto L16
    L18:
        if (r42 != 3) goto L20;
        r11 = this.transitionParams.progress;
        goto L21
    L20:
        r11 = 1.0f;
        goto L21
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r20) {
        int r2 = getMeasuredHeight();
        int r3 = BaseChartView.PICKER_PADDING;
        int r22 = r2 - r3;
        int r4 = (getMeasuredHeight() - this.pikerHeight) - r3;
        int r32 = this.lines.size();
        if (this.chartData == 0) goto L42;
        int r6 = 0;
    L5:
        if (r6 >= r32) goto L53;
        LineViewData r7 = (LineViewData) this.lines.get(r6);
        float r9 = 0.0f;
        if (r7.enabled == false) goto L9;
    L12:
        r7.bottomLinePath.reset();
        int r8 = ((DoubleLinearChartData) this.chartData).xPercentage.length;
        long[] r10 = r7.line.y;
        r7.chartPath.reset();
        int r11 = 0;
        int r12 = 0;
    L13:
        if (r11 >= r8) goto L32;
        long r13 = r10[r11];
        if (r13 >= 0) goto L17;
        int r17 = r22;
        int r18 = r32;
        float r16 = r9;
    L31:
        r11 = r11 + 1;
        r9 = r16;
        r22 = r17;
        r32 = r18;
        goto L13
    L17:
        T r15 = this.chartData;
        r16 = r9;
        float r92 = ((DoubleLinearChartData) r15).xPercentage[r11] * this.pickerWidth;
        if (BaseChartView.ANIMATE_PICKER_SIZES == false) goto L20;
        float r5 = this.pickerMaxHeight;
        r17 = r22;
        r18 = r32;
    L21:
        float r33 = (1.0f - ((r13 * ((DoubleLinearChartData) r15).linesK[r6]) / r5)) * (r17 - r4);
        if (BaseChartView.USE_LINES == false) goto L27;
        float[] r23 = r7.linesPathBottom;
        if (r12 != 0) goto L26;
        int r52 = r12 + 1;
        r23[r12] = r92;
        r12 = r12 + 2;
        r23[r52] = r33;
        goto L31
    L26:
        r23[r12] = r92;
        r23[r12 + 1] = r33;
        int r53 = r12 + 3;
        r23[r12 + 2] = r92;
        r12 = r12 + 4;
        r23[r53] = r33;
        goto L31
    L27:
        Path r24 = r7.bottomLinePath;
        if (r11 != 0) goto L30;
        r24.moveTo(r92, r33);
        goto L31
    L30:
        r24.lineTo(r92, r33);
        goto L31
    L20:
        r17 = r22;
        r18 = r32;
        r5 = ((DoubleLinearChartData) r15).maxValue;
        goto L21
    L32:
        int r172 = r22;
        int r182 = r32;
        float r162 = r9;
        r7.linesPathBottomSize = r12;
        if (r7.enabled == false) goto L35;
    L37:
        r7.bottomLinePaint.setAlpha((int) (r7.alpha * 255.0f));
        if (BaseChartView.USE_LINES == false) goto L40;
        r20.drawLines(r7.linesPathBottom, 0, r7.linesPathBottomSize, r7.bottomLinePaint);
    L41:
        r6 = r6 + 1;
        r22 = r172;
        r32 = r182;
        goto L5
    L40:
        r20.drawPath(r7.bottomLinePath, r7.bottomLinePaint);
        goto L41
    L35:
        if (r7.alpha != r162) goto L37;
    L9:
        if (r7.alpha != 0.0f) goto L12;
        r172 = r22;
        r182 = r32;
        goto L41
    L53:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawSelection(Canvas r11) {
        int r0 = this.selectedIndex;
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (this.legendShowing == false) goto L21;
        int r1 = (int) (this.chartActiveLineAlpha * this.selectionA);
        float r2 = this.chartWidth;
        ChartPickerDelegate r3 = this.pickerDelegate;
        float r4 = r3.pickerEnd;
        float r32 = r3.pickerStart;
        float r22 = r2 / (r4 - r32);
        float r5 = (((DoubleLinearChartData) this.chartData).xPercentage[r0] * r22) - ((r32 * r22) - BaseChartView.HORIZONTAL_PADDING);
        this.selectedLinePaint.setAlpha(r1);
        r11.drawLine(r5, 0.0f, r5, this.chartArea.bottom, this.selectedLinePaint);
        this.tmpN = this.lines.size();
        int r112 = 0;
    L8:
        this.tmpI = r112;
        int r113 = this.tmpI;
        if (r113 >= this.tmpN) goto L22;
        LineViewData r114 = (LineViewData) this.lines.get(r113);
        if (r114.enabled == false) goto L13;
    L15:
        float r02 = r114.line.y[this.selectedIndex] * ((DoubleLinearChartData) this.chartData).linesK[this.tmpI];
        float r12 = this.currentMinHeight;
        float r13 = (getMeasuredHeight() - this.chartBottom) - (((r02 - r12) / (this.currentMaxHeight - r12)) * ((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT));
        r114.selectionPaint.setAlpha((int) ((r114.alpha * 255.0f) * this.selectionA));
        this.selectionBackgroundPaint.setAlpha((int) ((r114.alpha * 255.0f) * this.selectionA));
        r11.drawPoint(r5, r13, r114.selectionPaint);
        r11.drawPoint(r5, r13, this.selectionBackgroundPaint);
    L16:
        r112 = this.tmpI + 1;
        goto L8
    L13:
        if (r114.alpha != 0.0f) goto L15;
    L22:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawSignaturesToHorizontalLines(Canvas r19, ChartHorizontalLinesData r20) {
        ChartHorizontalLinesData r1 = r20;
        int r8 = r1.values.length;
        int r4 = 0;
        float r5 = 1.0f;
        if (((DoubleLinearChartData) this.chartData).linesK[0] != 1.0f) goto L5;
        int r10 = 1;
    L6:
        int r12 = (r10 + 1) % 2;
        if (r8 <= 2) goto L11;
        float r2 = (r2[1] - r2[0]) / (this.currentMaxHeight - this.currentMinHeight);
        if (r2 >= 0.1d) goto L11;
        float r13 = r2 / 0.1f;
    L12:
        int r22 = this.transitionMode;
        if (r22 != 2) goto L16;
        r5 = 1.0f - this.transitionParams.progress;
    L15:
        float r14 = r5;
        this.linePaint.setAlpha((int) ((r1.alpha * 0.1f) * r14));
        int r23 = getMeasuredHeight() - this.chartBottom;
        int r3 = BaseChartView.SIGNATURE_TEXT_HEIGHT;
        int r15 = r23 - r3;
        int r24 = (int) (r3 - this.signaturePaint.getTextSize());
    L22:
        if (r4 >= r8) goto L44;
        float r32 = getMeasuredHeight() - this.chartBottom;
        int r17 = r10;
        float r6 = r1.values[r4];
        float r7 = this.currentMinHeight;
        int r9 = (int) (r32 - (r15 * ((r6 - r7) / (this.currentMaxHeight - r7))));
        if (r1.valuesStr != null) goto L26;
    L35:
        int r102 = r24;
    L37:
        if (r1.valuesStr2 != null) goto L39;
        boolean r16 = true;
    L43:
        r4 = r4 + 1;
        r1 = r20;
        r24 = r102;
        r10 = r17;
        goto L22
    L39:
        if (this.lines.size() <= 1) goto L41;
        this.signaturePaint2.setColor(((LineViewData) this.lines.get(r17)).lineColor);
        this.signaturePaint2.setAlpha((int) (((r1.alpha * ((LineViewData) this.lines.get(r17)).alpha) * r14) * r13));
        r16 = true;
        r1.drawText(r19, 1, r4, getMeasuredWidth() - BaseChartView.HORIZONTAL_PADDING, r9 - r102, this.signaturePaint2);
        goto L43
    L41:
        r16 = true;
        goto L43
    L26:
        if (this.lines.size() <= 0) goto L35;
        if (r1.valuesStr2 != null) goto L30;
    L33:
        this.signaturePaint.setColor(Theme.getColor(Theme.key_statisticChartSignature, this.resourcesProvider));
        this.signaturePaint.setAlpha((int) (((r1.alpha * this.signaturePaintAlpha) * r14) * r13));
    L34:
        r102 = r24;
        r1.drawText(r19, 0, r4, BaseChartView.HORIZONTAL_PADDING, r9 - r24, this.signaturePaint);
        goto L37
    L30:
        if (this.lines.size() < 2) goto L33;
        this.signaturePaint.setColor(((LineViewData) this.lines.get(r12)).lineColor);
        this.signaturePaint.setAlpha((int) (((r1.alpha * ((LineViewData) this.lines.get(r12)).alpha) * r14) * r13));
        goto L34
    L44:
        return;
    L16:
        if (r22 != 1) goto L19;
        r5 = this.transitionParams.progress;
        goto L15
    L19:
        if (r22 != 3) goto L15;
        r5 = this.transitionParams.progress;
    L11:
        r13 = 1.0f;
        goto L12
    L5:
        r10 = 0;
        goto L6
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public long findMaxValue(int r10, int r11) {
        if (this.lines.isEmpty() == false) goto L5;
        return 0;
    L5:
        int r0 = this.lines.size();
        int r3 = 0;
        long r4 = 0;
    L6:
        if (r3 >= r0) goto L15;
        if (((LineViewData) this.lines.get(r3)).enabled == false) goto L10;
        long r6 = (long) (((DoubleLinearChartData) this.chartData).lines.get(r3).segmentTree.rMaxQ(r10, r11) * ((DoubleLinearChartData) this.chartData).linesK[r3]);
    L12:
        if (r6 <= r4) goto L14;
        r4 = r6;
    L14:
        r3 = r3 + 1;
        goto L6
    L10:
        r6 = 0;
        goto L12
    L15:
        return r4;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public long findMinValue(int r8, int r9) {
        if (this.lines.isEmpty() == false) goto L6;
        return 0;
    L6:
        int r0 = this.lines.size();
        long r1 = LongCompanionObject.MAX_VALUE;
        int r3 = 0;
    L7:
        if (r3 >= r0) goto L16;
        if (((LineViewData) this.lines.get(r3)).enabled == false) goto L11;
        long r4 = (int) (((DoubleLinearChartData) this.chartData).lines.get(r3).segmentTree.rMinQ(r8, r9) * ((DoubleLinearChartData) this.chartData).linesK[r3]);
    L13:
        if (r4 >= r1) goto L15;
        r1 = r4;
    L15:
        r3 = r3 + 1;
        goto L7
    L11:
        r4 = 2147483647L;
        goto L13
    L16:
        return r1;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void init() {
        this.useMinHeight = true;
        super.init();
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void updatePickerMinMaxHeight() {
        if (BaseChartView.ANIMATE_PICKER_SIZES == false) goto L37;
        int r1 = 0;
        if (((LineViewData) this.lines.get(0)).enabled == false) goto L9;
        super.updatePickerMinMaxHeight();
        return;
    L9:
        ArrayList<L> r0 = this.lines;
        int r2 = r0.size();
        long r5 = 0;
    L10:
        if (r1 >= r2) goto L17;
        Object r7 = r0.get(r1);
        r1 = r1 + 1;
        LineViewData r72 = (LineViewData) r7;
        if (r72.enabled == false) goto L10;
        long r73 = r72.line.maxValue;
        if (r73 <= r5) goto L10;
        r5 = r73;
        goto L10
    L17:
        if (this.lines.size() <= 1) goto L20;
        r5 = (long) (r5 * ((DoubleLinearChartData) this.chartData).linesK[1]);
    L20:
        if (r5 <= 0) goto L35;
        float r02 = r5;
        if (r02 == this.animatedToPickerMaxHeight) goto L36;
        this.animatedToPickerMaxHeight = r02;
        Animator r03 = this.pickerAnimator;
        if (r03 == null) goto L26;
        r03.cancel();
    L26:
        ValueAnimator r04 = createAnimator(this.pickerMaxHeight, this.animatedToPickerMaxHeight, new AnonymousClass1(this));
        this.pickerAnimator = r04;
        r04.start();
        return;
    L36:
        return;
    L35:
        return;
    }
}
