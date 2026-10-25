package org.telegram.ui.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.graphics.ColorUtils;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.view_data.BarViewData;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class BarChartView extends BaseChartView<ChartData, BarViewData> {
    public BarChartView(Context r1) {
        super(r1);
        this.superDraw = true;
        this.useAlphaSignature = true;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public BarViewData createLineViewData(ChartData.Line r2) {
        return new BarViewData(r2, this.resourcesProvider);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public /* bridge */ /* synthetic */ LineViewData createLineViewData(ChartData.Line r1) {
        return createLineViewData(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r25) {
        Canvas r1 = r25;
        T r2 = this.chartData;
        if (r2 == 0) goto L66;
        float r3 = this.chartWidth;
        ChartPickerDelegate r4 = this.pickerDelegate;
        float r5 = r4.pickerEnd;
        float r42 = r4.pickerStart;
        float r7 = r3 / (r5 - r42);
        float r8 = (r42 * r7) - BaseChartView.HORIZONTAL_PADDING;
        char r9 = 1;
        int r32 = this.startXIndex - 1;
        int r10 = 0;
        if (r32 >= 0) goto L7;
        int r11 = 0;
    L8:
        int r33 = this.endXIndex + 1;
        if (r33 <= (r2.lines.get(0).y.length - 1)) goto L11;
        r33 = this.chartData.lines.get(0).y.length - 1;
    L11:
        int r12 = r33;
        r1.save();
        float r13 = 0.0f;
        r1.clipRect(this.chartStart, 0.0f, this.chartEnd, getMeasuredHeight() - this.chartBottom);
        r1.save();
        int r22 = this.transitionMode;
        float r14 = 2.0f;
        int r15 = 2;
        float r34 = 1.0f;
        if (r22 != 2) goto L15;
        this.postTransition = true;
        this.selectionA = 0.0f;
        TransitionParams r23 = this.transitionParams;
        float r43 = r23.progress;
        float r52 = 1.0f - r43;
        r1.scale((r43 * 2.0f) + 1.0f, 1.0f, r23.pX, r23.pY);
    L14:
        float r16 = r52;
    L18:
        int r24 = 0;
    L20:
        if (r24 >= this.lines.size()) goto L57;
        BarViewData r44 = (BarViewData) this.lines.get(r24);
        if (r44.enabled == false) goto L24;
    L26:
        float[] r53 = this.chartData.xPercentage;
        if (r53.length >= r15) goto L29;
        float r54 = r34;
    L30:
        long[] r6 = r44.line.y;
        float r17 = r34;
        float r35 = r44.alpha;
        int r20 = r24;
        int r18 = r10;
        int r92 = r11;
        float r26 = r13;
        float r36 = r26;
        float r19 = r14;
        int r142 = r18;
    L31:
        if (r92 > r12) goto L39;
        float r222 = ((r54 / r19) + (this.chartData.xPercentage[r92] * r7)) - r8;
        int r232 = r11;
        float r112 = (getMeasuredHeight() - this.chartBottom) - (((r6[r92] / this.currentMaxHeight) * r35) * ((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT));
        if (r92 == this.selectedIndex) goto L35;
    L37:
        float[] r102 = r44.linesPath;
        r102[r142] = r222;
        r102[r142 + 1] = r112;
        int r113 = r142 + 3;
        r102[r142 + 2] = r222;
        r142 = r142 + 4;
        r102[r113] = getMeasuredHeight() - this.chartBottom;
    L38:
        r92 = r92 + 1;
        r11 = r232;
        goto L31
    L35:
        if (this.legendShowing == false) goto L37;
        r36 = r112;
        r26 = r222;
        r18 = 1;
        goto L38
    L39:
        int r233 = r11;
        if (r18 == 0) goto L42;
    L45:
        Paint r62 = r44.unselectedPaint;
    L46:
        r62.setStrokeWidth(r54);
        if (r18 == 0) goto L50;
        r44.unselectedPaint.setColor(ColorUtils.blendARGB(r44.lineColor, r44.blendColor, r17 - this.selectionA));
    L50:
        if (this.postTransition == false) goto L52;
        r13 = 0.0f;
        r44.unselectedPaint.setColor(ColorUtils.blendARGB(r44.lineColor, r44.blendColor, 0.0f));
    L53:
        int r93 = (int) (255.0f * r16);
        r62.setAlpha(r93);
        int r152 = 0;
        r1.drawLines(r44.linesPath, 0, r142, r62);
        if (r18 == 0) goto L56;
        r44.paint.setStrokeWidth(r54);
        r44.paint.setAlpha(r93);
        r1.drawLine(r26, r36, r26, getMeasuredHeight() - this.chartBottom, r44.paint);
        r44.paint.setAlpha(255);
    L56:
        r24 = r20 + 1;
        r1 = r25;
        r10 = r152;
        r34 = r17;
        r14 = r19;
        r11 = r233;
        r9 = 1;
        r15 = 2;
        goto L20
    L52:
        r13 = 0.0f;
        goto L53
    L42:
        if (this.postTransition == true) goto L45;
        r62 = r44.paint;
        goto L46
    L29:
        r54 = r53[r9] * r7;
        goto L30
    L24:
        if (r44.alpha != r13) goto L26;
        r20 = r24;
        r17 = r34;
        r152 = r10;
        r233 = r11;
        r19 = r14;
        goto L56
    L57:
        r25.restore();
        r25.restore();
        return;
    L15:
        if (r22 != 1) goto L17;
        TransitionParams r27 = this.transitionParams;
        r52 = r27.progress;
        r1.scale(r52, 1.0f, r27.pX, r27.pY);
        goto L14
    L17:
        r16 = 1.0f;
        goto L18
    L7:
        r11 = r32;
        goto L8
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r21) {
        int r1 = getMeasuredHeight();
        int r2 = BaseChartView.PICKER_PADDING;
        int r12 = r1 - r2;
        int r3 = (getMeasuredHeight() - this.pikerHeight) - r2;
        int r22 = this.lines.size();
        if (this.chartData == 0) goto L28;
        int r5 = 0;
    L5:
        if (r5 >= r22) goto L35;
        BarViewData r6 = (BarViewData) this.lines.get(r5);
        if (r6.enabled == false) goto L9;
    L11:
        r6.bottomLinePath.reset();
        float[] r7 = this.chartData.xPercentage;
        int r8 = r7.length;
        if (r7.length >= 2) goto L14;
        float r72 = 1.0f;
    L15:
        long[] r9 = r6.line.y;
        float r11 = r6.alpha;
        int r122 = 0;
        int r13 = 0;
    L16:
        if (r122 >= r8) goto L26;
        long r14 = r9[r122];
        if (r14 >= 0) goto L20;
        int r18 = r12;
        int r19 = r22;
    L25:
        r122 = r122 + 1;
        r12 = r18;
        r22 = r19;
        goto L16
    L20:
        T r10 = this.chartData;
        r18 = r12;
        float r4 = r10.xPercentage[r122] * this.pickerWidth;
        if (BaseChartView.ANIMATE_PICKER_SIZES == false) goto L23;
        float r15 = this.pickerMaxHeight;
        r19 = r22;
    L24:
        float r102 = (1.0f - ((r14 / r15) * r11)) * (r18 - r3);
        float[] r16 = r6.linesPath;
        r16[r13] = r4;
        r16[r13 + 1] = r102;
        int r23 = r13 + 3;
        r16[r13 + 2] = r4;
        r13 = r13 + 4;
        r16[r23] = getMeasuredHeight() - this.chartBottom;
        goto L25
    L23:
        r19 = r22;
        r15 = r10.maxValue;
        goto L24
    L26:
        int r182 = r12;
        int r192 = r22;
        r6.paint.setStrokeWidth(r72 + 2.0f);
        r21.drawLines(r6.linesPath, 0, r13, r6.paint);
    L27:
        r5 = r5 + 1;
        r12 = r182;
        r22 = r192;
        goto L5
    L14:
        r72 = r7[1] * this.pickerWidth;
        goto L15
    L9:
        if (r6.alpha != 0.0f) goto L11;
        r182 = r12;
        r192 = r22;
        goto L27
    L35:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawSelection(Canvas r1) {
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public float getMinDistance() {
        return 0.1f;
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
}
