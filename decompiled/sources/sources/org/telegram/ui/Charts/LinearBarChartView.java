package org.telegram.ui.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class LinearBarChartView extends BaseChartView<ChartData, LineViewData> {
    public LinearBarChartView(Context r1) {
        super(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public LineViewData createLineViewData(ChartData.Line r2) {
        return new LineViewData(r2, true);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r23) {
        if (this.chartData == 0) goto L67;
        float r2 = this.chartWidth;
        ChartPickerDelegate r3 = this.pickerDelegate;
        float r4 = r3.pickerEnd;
        float r32 = r3.pickerStart;
        float r22 = r2 / (r4 - r32);
        float r33 = (r32 * r22) - BaseChartView.HORIZONTAL_PADDING;
        int r42 = 0;
        int r5 = 0;
    L6:
        if (r5 >= this.lines.size()) goto L77;
        LineViewData r6 = (LineViewData) this.lines.get(r5);
        if (r6.enabled == false) goto L10;
    L12:
        float[] r7 = this.chartData.xPercentage;
        if (r7.length >= 2) goto L15;
        float r72 = 0.0f;
    L16:
        long[] r9 = r6.line.y;
        int r12 = ((int) (BaseChartView.HORIZONTAL_PADDING / r72)) + 1;
        r6.chartPath.reset();
        int r13 = Math.max(r42, this.startXIndex - r12);
        int r122 = Math.min(this.chartData.xPercentage.length - 1, this.endXIndex + r12);
        int r15 = r42;
        boolean r14 = true;
    L18:
        if (r13 > r122) goto L36;
        long[] r17 = r9;
        long r8 = r17[r13];
        if (r8 >= 0) goto L22;
        float r20 = r22;
    L35:
        r13 = r13 + 1;
        r9 = r17;
        r22 = r20;
        goto L18
    L22:
        float r43 = (this.chartData.xPercentage[r13] * r22) - r33;
        float r92 = this.currentMinHeight;
        float r82 = (r8 - r92) / (this.currentMaxHeight - r92);
        float r93 = r6.paint.getStrokeWidth() / 2.0f;
        r20 = r22;
        float r10 = ((getMeasuredHeight() - this.chartBottom) - r93) - (r82 * (((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT) - r93));
        if (BaseChartView.USE_LINES == false) goto L30;
        if (r15 != 0) goto L26;
        float[] r24 = r6.linesPath;
        float r94 = r72 / 2.0f;
        r24[r15] = r43 - r94;
        r24[r15 + 1] = r10;
        float r44 = r43 + r94;
        r24[r15 + 2] = r44;
        r24[r15 + 3] = r10;
        int r83 = r15 + 5;
        r24[r15 + 4] = r44;
        r15 = r15 + 6;
        r24[r83] = r10;
        goto L35
    L26:
        float[] r25 = r6.linesPath;
        if (r13 != r122) goto L29;
        float r11 = r72 / 2.0f;
        float r16 = r43 - r11;
        r25[r15] = r16;
        r25[r15 + 1] = r10;
        r25[r15 + 2] = r16;
        r25[r15 + 3] = r10;
        float r45 = r43 + r11;
        r25[r15 + 4] = r45;
        r25[r15 + 5] = r10;
        r25[r15 + 6] = r45;
        r25[r15 + 7] = r10;
        int r84 = r15 + 9;
        r25[r15 + 8] = r45;
        r15 = r15 + 10;
        r25[r84] = (getMeasuredHeight() - this.chartBottom) - r93;
        goto L35
    L29:
        float r95 = r72 / 2.0f;
        float r112 = r43 - r95;
        r25[r15] = r112;
        r25[r15 + 1] = r10;
        r25[r15 + 2] = r112;
        r25[r15 + 3] = r10;
        float r46 = r43 + r95;
        r25[r15 + 4] = r46;
        r25[r15 + 5] = r10;
        int r85 = r15 + 7;
        r25[r15 + 6] = r46;
        r15 = r15 + 8;
        r25[r85] = r10;
        goto L35
    L30:
        Path r26 = r6.chartPath;
        if (r14 == false) goto L33;
        r26.moveTo(r43 - (r72 / 2.0f), r10);
        r14 = false;
    L34:
        r6.chartPath.lineTo(r43 + (r72 / 2.0f), r10);
        goto L35
    L33:
        r26.lineTo(r43 - (r72 / 2.0f), r10);
        goto L34
    L36:
        float r202 = r22;
        r23.save();
        int r27 = this.transitionMode;
        float r47 = 1.0f;
        if (r27 != 2) goto L45;
        TransitionParams r28 = this.transitionParams;
        float r73 = r28.progress;
        if (r73 <= 0.5f) goto L41;
        float r86 = 0.0f;
    L42:
        r23.scale((r73 * 2.0f) + 1.0f, 1.0f, r28.pX, r28.pY);
    L43:
        r47 = r86;
    L57:
        r6.paint.setAlpha((int) ((r6.alpha * 255.0f) * r47));
        int r29 = this.endXIndex - this.startXIndex;
        Paint r48 = r6.paint;
        if (r29 <= 100) goto L60;
        r48.setStrokeCap(Paint.Cap.SQUARE);
    L62:
        if (BaseChartView.USE_LINES == true) goto L64;
        r23.drawPath(r6.chartPath, r6.paint);
        int r62 = 0;
    L65:
        r23.restore();
    L66:
        r5 = r5 + 1;
        r42 = r62;
        r22 = r202;
        goto L6
    L64:
        float[] r210 = r6.linesPath;
        Paint r49 = r6.paint;
        r62 = 0;
        r23.drawLines(r210, 0, r15, r49);
        goto L65
    L60:
        r48.setStrokeCap(Paint.Cap.ROUND);
        goto L62
    L41:
        r86 = 1.0f - (r73 * 2.0f);
        goto L42
    L45:
        if (r27 != 1) goto L55;
        float r211 = this.transitionParams.progress;
        if (r211 >= 0.3f) goto L49;
        r86 = 0.0f;
    L50:
        r23.save();
        TransitionParams r212 = this.transitionParams;
        float r74 = r212.progress;
        if (r212.needScaleY == false) goto L53;
        r47 = r74;
    L53:
        r23.scale(r74, r47, r212.pX, r212.pY);
        goto L43
    L49:
        r86 = r211;
        goto L50
    L55:
        if (r27 != 3) goto L57;
        r47 = this.transitionParams.progress;
        goto L57
    L15:
        r72 = r7[1] * r22;
        goto L16
    L10:
        if (r6.alpha != 0.0f) goto L12;
        r202 = r22;
        r62 = r42;
        goto L66
    L77:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r20) {
        getMeasuredHeight();
        getMeasuredHeight();
        int r2 = this.lines.size();
        T r3 = this.chartData;
        if (r3 == 0) goto L51;
        float[] r32 = r3.xPercentage;
        if (r32.length >= 2) goto L7;
        float r33 = 1.0f;
    L8:
        int r6 = 0;
    L9:
        if (r6 >= r2) goto L63;
        LineViewData r7 = (LineViewData) this.lines.get(r6);
        float r9 = 0.0f;
        if (r7.enabled == false) goto L13;
    L15:
        r7.bottomLinePath.reset();
        int r8 = this.chartData.xPercentage.length;
        long[] r10 = r7.line.y;
        r7.chartPath.reset();
        int r11 = 0;
        int r12 = 0;
    L16:
        if (r11 >= r8) goto L41;
        long r13 = r10[r11];
        if (r13 >= 0) goto L20;
        int r4 = r2;
        float r18 = r33;
        float r17 = r9;
    L40:
        r11 = r11 + 1;
        r2 = r4;
        r9 = r17;
        r33 = r18;
        goto L16
    L20:
        T r15 = this.chartData;
        r17 = r9;
        float r5 = r15.xPercentage[r11] * this.pickerWidth;
        boolean r92 = BaseChartView.ANIMATE_PICKER_SIZES;
        if (r92 == false) goto L23;
        r4 = r2;
        float r22 = this.pickerMaxHeight;
        r18 = r33;
    L24:
        if (r92 == false) goto L26;
        float r34 = this.pickerMinHeight;
        float r93 = r22;
    L27:
        float r23 = (1.0f - ((r13 - r34) / (r93 - r34))) * this.pikerHeight;
        if (BaseChartView.USE_LINES == false) goto L35;
        if (r12 != 0) goto L31;
        float[] r35 = r7.linesPathBottom;
        float r94 = r18 / 2.0f;
        r35[r12] = r5 - r94;
        r35[r12 + 1] = r23;
        float r52 = r5 + r94;
        r35[r12 + 2] = r52;
        r35[r12 + 3] = r23;
        int r132 = r12 + 5;
        r35[r12 + 4] = r52;
        r12 = r12 + 6;
        r35[r132] = r23;
        goto L40
    L31:
        int r36 = r8 - 1;
        float[] r133 = r7.linesPathBottom;
        if (r11 != r36) goto L34;
        float r95 = r18 / 2.0f;
        float r14 = r5 - r95;
        r133[r12] = r14;
        r133[r12 + 1] = r23;
        r133[r12 + 2] = r14;
        r133[r12 + 3] = r23;
        float r53 = r5 + r95;
        r133[r12 + 4] = r53;
        r133[r12 + 5] = r23;
        r133[r12 + 6] = r53;
        r133[r12 + 7] = r23;
        int r24 = r12 + 9;
        r133[r12 + 8] = r53;
        r12 = r12 + 10;
        r133[r24] = r17;
        goto L40
    L34:
        float r96 = r18 / 2.0f;
        float r142 = r5 - r96;
        r133[r12] = r142;
        r133[r12 + 1] = r23;
        r133[r12 + 2] = r142;
        r133[r12 + 3] = r23;
        float r54 = r5 + r96;
        r133[r12 + 4] = r54;
        r133[r12 + 5] = r23;
        int r37 = r12 + 7;
        r133[r12 + 6] = r54;
        r12 = r12 + 8;
        r133[r37] = r23;
        goto L40
    L35:
        Path r38 = r7.bottomLinePath;
        if (r11 != 0) goto L38;
        r38.moveTo(r5 - (r18 / 2.0f), r23);
    L39:
        r7.bottomLinePath.lineTo(r5 + (r18 / 2.0f), r23);
        goto L40
    L38:
        r38.lineTo(r5 - (r18 / 2.0f), r23);
        goto L39
    L26:
        r93 = r22;
        r34 = r15.minValue;
        goto L27
    L23:
        r4 = r2;
        r18 = r33;
        r22 = r15.maxValue;
        goto L24
    L41:
        int r42 = r2;
        float r182 = r33;
        float r172 = r9;
        r7.linesPathBottomSize = r12;
        if (r7.enabled == false) goto L44;
    L46:
        r7.bottomLinePaint.setAlpha((int) (r7.alpha * 255.0f));
        if (BaseChartView.USE_LINES == false) goto L49;
        r20.drawLines(r7.linesPathBottom, 0, r7.linesPathBottomSize, r7.bottomLinePaint);
    L50:
        r6 = r6 + 1;
        r2 = r42;
        r33 = r182;
        goto L9
    L49:
        r20.drawPath(r7.bottomLinePath, r7.bottomLinePaint);
        goto L50
    L44:
        if (r7.alpha != r172) goto L46;
    L13:
        if (r7.alpha != 0.0f) goto L15;
        r42 = r2;
        r182 = r33;
        goto L50
    L63:
        return;
    L7:
        r33 = r32[1] * this.pickerWidth;
        goto L8
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void init() {
        this.useMinHeight = true;
        super.init();
    }
}
