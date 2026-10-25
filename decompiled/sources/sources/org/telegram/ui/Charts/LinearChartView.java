package org.telegram.ui.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class LinearChartView extends BaseChartView<ChartData, LineViewData> {
    public LinearChartView(Context r1) {
        super(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public LineViewData createLineViewData(ChartData.Line r2) {
        return new LineViewData(r2, false);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r21) {
        if (this.chartData == 0) goto L62;
        float r2 = this.chartWidth;
        ChartPickerDelegate r3 = this.pickerDelegate;
        float r4 = r3.pickerEnd;
        float r32 = r3.pickerStart;
        float r22 = r2 / (r4 - r32);
        float r33 = (r32 * r22) - BaseChartView.HORIZONTAL_PADDING;
        int r42 = 0;
        int r5 = 0;
    L6:
        if (r5 >= this.lines.size()) goto L72;
        LineViewData r6 = (LineViewData) this.lines.get(r5);
        if (r6.enabled == false) goto L10;
    L12:
        float[] r7 = this.chartData.xPercentage;
        if (r7.length >= 2) goto L15;
        float r72 = 0.0f;
    L16:
        long[] r9 = r6.line.y;
        int r73 = ((int) (BaseChartView.HORIZONTAL_PADDING / r72)) + 1;
        r6.chartPath.reset();
        int r12 = Math.max(r42, this.startXIndex - r73);
        int r74 = Math.min(this.chartData.xPercentage.length - 1, this.endXIndex + r73);
        int r14 = r42;
        boolean r13 = true;
    L17:
        if (r12 > r74) goto L32;
        long[] r17 = r9;
        long r8 = r17[r12];
        if (r8 < 0) goto L31;
        float r15 = (this.chartData.xPercentage[r12] * r22) - r33;
        float r92 = this.currentMinHeight;
        float r82 = (r8 - r92) / (this.currentMaxHeight - r92);
        float r43 = r6.paint.getStrokeWidth() / 2.0f;
        float r93 = ((getMeasuredHeight() - this.chartBottom) - r43) - (r82 * (((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT) - r43));
        if (BaseChartView.USE_LINES == false) goto L27;
        float[] r44 = r6.linesPath;
        if (r14 != 0) goto L26;
        int r83 = r14 + 1;
        r44[r14] = r15;
        r14 = r14 + 2;
        r44[r83] = r93;
        goto L31
    L26:
        r44[r14] = r15;
        r44[r14 + 1] = r93;
        int r84 = r14 + 3;
        r44[r14 + 2] = r15;
        r14 = r14 + 4;
        r44[r84] = r93;
        goto L31
    L27:
        Path r45 = r6.chartPath;
        if (r13 == false) goto L30;
        r45.moveTo(r15, r93);
        r13 = false;
        goto L31
    L30:
        r45.lineTo(r15, r93);
    L31:
        r12 = r12 + 1;
        r9 = r17;
        goto L17
    L32:
        r21.save();
        int r46 = this.transitionMode;
        float r75 = 1.0f;
        if (r46 != 2) goto L40;
        TransitionParams r47 = this.transitionParams;
        float r85 = r47.progress;
        if (r85 <= 0.5f) goto L37;
        float r16 = 0.0f;
    L38:
        r21.scale((r85 * 2.0f) + 1.0f, 1.0f, r47.pX, r47.pY);
        r75 = r16;
    L52:
        r6.paint.setAlpha((int) ((r6.alpha * 255.0f) * r75));
        int r48 = this.endXIndex - this.startXIndex;
        Paint r76 = r6.paint;
        if (r48 <= 100) goto L55;
        r76.setStrokeCap(Paint.Cap.SQUARE);
    L57:
        if (BaseChartView.USE_LINES == true) goto L59;
        r21.drawPath(r6.chartPath, r6.paint);
        int r77 = 0;
    L60:
        r21.restore();
    L61:
        r5 = r5 + 1;
        r42 = r77;
        goto L6
    L59:
        r77 = 0;
        r21.drawLines(r6.linesPath, 0, r14, r6.paint);
        goto L60
    L55:
        r76.setStrokeCap(Paint.Cap.ROUND);
        goto L57
    L37:
        r16 = 1.0f - (r85 * 2.0f);
        goto L38
    L40:
        if (r46 != 1) goto L50;
        float r49 = this.transitionParams.progress;
        if (r49 >= 0.3f) goto L44;
        float r86 = 0.0f;
    L45:
        r21.save();
        TransitionParams r410 = this.transitionParams;
        float r94 = r410.progress;
        if (r410.needScaleY == false) goto L48;
        r75 = r94;
    L48:
        r21.scale(r94, r75, r410.pX, r410.pY);
        r75 = r86;
        goto L52
    L44:
        r86 = r49;
        goto L45
    L50:
        if (r46 != 3) goto L52;
        r75 = this.transitionParams.progress;
        goto L52
    L15:
        r72 = r7[1] * r22;
        goto L16
    L10:
        if (r6.alpha != 0.0f) goto L12;
        r77 = r42;
        goto L61
    L72:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r19) {
        getMeasuredHeight();
        getMeasuredHeight();
        int r2 = this.lines.size();
        if (this.chartData == 0) goto L45;
        int r4 = 0;
    L5:
        if (r4 >= r2) goto L56;
        LineViewData r5 = (LineViewData) this.lines.get(r4);
        float r7 = 0.0f;
        if (r5.enabled == false) goto L9;
    L12:
        r5.bottomLinePath.reset();
        int r6 = this.chartData.xPercentage.length;
        long[] r8 = r5.line.y;
        r5.chartPath.reset();
        int r9 = 0;
        int r10 = 0;
    L13:
        if (r9 >= r6) goto L35;
        long r11 = r8[r9];
        if (r11 >= 0) goto L17;
        int r17 = r4;
        float r16 = r7;
        long[] r42 = r8;
    L34:
        r9 = r9 + 1;
        r8 = r42;
        r7 = r16;
        r4 = r17;
        goto L13
    L17:
        T r13 = this.chartData;
        float r14 = r13.xPercentage[r9] * this.pickerWidth;
        boolean r15 = BaseChartView.ANIMATE_PICKER_SIZES;
        if (r15 == false) goto L20;
        r16 = r7;
        r17 = r4;
        float r3 = this.pickerMaxHeight;
    L21:
        if (r15 == false) goto L23;
        float r72 = this.pickerMinHeight;
        r42 = r8;
    L24:
        float r32 = (1.0f - ((r11 - r72) / (r3 - r72))) * this.pikerHeight;
        if (BaseChartView.USE_LINES == false) goto L30;
        float[] r73 = r5.linesPathBottom;
        if (r10 != 0) goto L29;
        int r82 = r10 + 1;
        r73[r10] = r14;
        r10 = r10 + 2;
        r73[r82] = r32;
        goto L34
    L29:
        r73[r10] = r14;
        r73[r10 + 1] = r32;
        int r83 = r10 + 3;
        r73[r10 + 2] = r14;
        r10 = r10 + 4;
        r73[r83] = r32;
        goto L34
    L30:
        Path r74 = r5.bottomLinePath;
        if (r9 != 0) goto L33;
        r74.moveTo(r14, r32);
        goto L34
    L33:
        r74.lineTo(r14, r32);
        goto L34
    L23:
        r42 = r8;
        r72 = r13.minValue;
        goto L24
    L20:
        r17 = r4;
        r16 = r7;
        r3 = r13.maxValue;
        goto L21
    L35:
        int r172 = r4;
        float r162 = r7;
        r5.linesPathBottomSize = r10;
        if (r5.enabled == false) goto L38;
    L40:
        r5.bottomLinePaint.setAlpha((int) (r5.alpha * 255.0f));
        if (BaseChartView.USE_LINES == false) goto L43;
        r19.drawLines(r5.linesPathBottom, 0, r5.linesPathBottomSize, r5.bottomLinePaint);
    L44:
        r4 = r172 + 1;
        goto L5
    L43:
        r19.drawPath(r5.bottomLinePath, r5.bottomLinePaint);
        goto L44
    L38:
        if (r5.alpha != r162) goto L40;
    L9:
        if (r5.alpha != 0.0f) goto L12;
        r172 = r4;
        goto L44
    L56:
        return;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void init() {
        this.useMinHeight = true;
        super.init();
    }
}
