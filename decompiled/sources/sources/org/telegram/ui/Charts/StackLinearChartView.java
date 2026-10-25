package org.telegram.ui.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.data.StackLinearChartData;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.StackLinearViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;

/* loaded from: classes6.dex */
public class StackLinearChartView<T extends StackLinearViewData> extends BaseChartView<StackLinearChartData, T> {
    private float[] mapPoints;
    private Matrix matrix;
    Path ovalPath;
    boolean[] skipPoints;
    float[] startFromY;

    public StackLinearChartView(Context r1) {
        super(r1);
        this.matrix = new Matrix();
        this.mapPoints = new float[2];
        this.ovalPath = new Path();
        this.superDraw = true;
        this.useAlphaSignature = true;
        this.drawPointOnSelection = false;
    }

    private int quarterForPoint(float r4, float r5) {
        float r0 = this.chartArea.centerX();
        float r3 = this.chartArea.centerY() + AndroidUtilities.dp(16.0f);
        if (r4 >= r0) goto L5;
    L8:
        if (r4 < r0) goto L14;
        if (r5 < r3) goto L14;
        return 1;
    L14:
        if (r4 < r0) goto L16;
        return 3;
    L16:
        if (r5 < r3) goto L21;
        return 2;
    L21:
        return 3;
    L5:
        if (r5 > r3) goto L8;
        return 0;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public /* bridge */ /* synthetic */ LineViewData createLineViewData(ChartData.Line r1) {
        return createLineViewData(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public T createLineViewData(ChartData.Line r1) {
        return (T) new StackLinearViewData(r1);
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawChart(Canvas r41) {
        if (this.chartData == 0) goto L222;
        float r2 = this.chartWidth;
        ChartPickerDelegate r3 = this.pickerDelegate;
        float r4 = r3.pickerEnd;
        float r32 = r3.pickerStart;
        float r22 = r2 / (r4 - r32);
        float r33 = (r32 * r22) - BaseChartView.HORIZONTAL_PADDING;
        float r42 = this.chartArea.centerX();
        float r5 = this.chartArea.centerY() + AndroidUtilities.dp(16.0f);
        int r7 = 0;
    L6:
        if (r7 >= this.lines.size()) goto L8;
        ((StackLinearViewData) this.lines.get(r7)).chartPath.reset();
        ((StackLinearViewData) this.lines.get(r7)).chartPathPicker.reset();
        r7 = r7 + 1;
        goto L6
    L8:
        r41.save();
        boolean[] r72 = this.skipPoints;
        if (r72 != null) goto L11;
    L12:
        this.skipPoints = new boolean[((StackLinearChartData) this.chartData).lines.size()];
        this.startFromY = new float[((StackLinearChartData) this.chartData).lines.size()];
    L13:
        int r73 = this.transitionMode;
        if (r73 != 2) goto L28;
        float r74 = this.transitionParams.progress / 0.6f;
        if (r74 <= 1.0f) goto L18;
        r74 = 1.0f;
    L18:
        this.ovalPath.reset();
        float r13 = this.chartArea.width();
        float r14 = this.chartArea.height();
        RectF r142 = this.chartArea;
        if (r13 <= r14) goto L21;
        float r132 = r142.width();
    L22:
        float r143 = this.chartArea.width();
        float r15 = this.chartArea.height();
        RectF r152 = this.chartArea;
        if (r143 <= r15) goto L25;
        float r144 = r152.height();
    L26:
        float r145 = r144 * 0.45f;
        float r146 = r145 + (((r132 - r145) / 2.0f) * (1.0f - this.transitionParams.progress));
        RectF r133 = new RectF();
        float r16 = 1.0f;
        float r17 = 0.0f;
        r133.set(r42 - r146, r5 - r146, r42 + r146, r5 + r146);
        this.ovalPath.addRoundRect(r133, r146, r146, Path.Direction.CW);
        r41.clipPath(this.ovalPath);
        float r9 = r74;
    L27:
        int r75 = 255;
    L32:
        T r11 = this.chartData;
        if (((StackLinearChartData) r11).xPercentage.length >= 2) goto L35;
        float r112 = r16;
    L36:
        int r113 = ((int) (BaseChartView.HORIZONTAL_PADDING / r112)) + 1;
        int r12 = Math.max(0, (this.startXIndex - r113) - 1);
        int r114 = Math.min(((StackLinearChartData) this.chartData).xPercentage.length - 1, (this.endXIndex + r113) + 1);
        boolean r19 = false;
        int r147 = r12;
        float r6 = r17;
        float r153 = r6;
    L37:
        if (r147 > r114) goto L200;
        float r20 = r17;
        int r8 = 0;
        int r10 = 0;
        int r21 = 0;
    L40:
        if (r8 >= this.lines.size()) goto L51;
        LineViewData r134 = (LineViewData) this.lines.get(r8);
        float r25 = r22;
        if (r134.enabled == false) goto L44;
    L46:
        int r27 = r8;
        float r26 = r9;
        long r82 = r134.line.y[r147];
        if (r82 <= 0) goto L49;
        r20 = r20 + (r82 * r134.alpha);
        r10 = r10 + 1;
    L49:
        r21 = r27;
    L50:
        r8 = r27 + 1;
        r22 = r25;
        r9 = r26;
        goto L40
    L44:
        if (r134.alpha != r17) goto L46;
        r27 = r8;
        r26 = r9;
        goto L50
    L51:
        float r252 = r22;
        float r262 = r9;
        float r83 = r17;
        int r23 = 0;
    L53:
        if (r23 >= this.lines.size()) goto L199;
        LineViewData r92 = (LineViewData) this.lines.get(r23);
        if (r92.enabled == false) goto L57;
    L59:
        long[] r135 = r92.line.y;
        float r272 = r33;
        if (r10 != 1) goto L66;
        if (r135[r147] != 0) goto L64;
    L63:
        float r28 = r6;
        int r34 = r75;
        float r62 = r17;
    L69:
        T r76 = this.chartData;
        int r29 = r34;
        float r35 = (((StackLinearChartData) r76).xPercentage[r147] * r252) - r272;
        if (r147 != r114) goto L72;
        float r77 = getMeasuredWidth();
    L73:
        int r36 = r21;
        if (r62 != r17) goto L77;
        if (r23 != r36) goto L77;
        r19 = true;
    L77:
        float r63 = ((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT) * r62;
        float r64 = ((getMeasuredHeight() - this.chartBottom) - r63) - r83;
        this.startFromY[r23] = r64;
        float r65 = getMeasuredHeight() - this.chartBottom;
        if (r147 != r114) goto L80;
        float r332 = r65;
        r28 = r35;
    L83:
        float r352 = r77;
        if (this.transitionMode != 2) goto L113;
        if (r23 == r36) goto L113;
        int r66 = (r35 > r42 ? 1 : (r35 == r42 ? 0 : -1));
        TransitionParams r78 = this.transitionParams;
        if (r66 >= 0) goto L89;
        int r362 = r66;
        float r67 = r78.startX[r23];
        float r79 = r78.startY[r23];
    L90:
        float r37 = r42 - r67;
        float r38 = r5 - r79;
        float r68 = (((r35 - r67) * r38) / r37) + r79;
        float r710 = r16 - r262;
        float r212 = r64 * r710;
        float r69 = r68 * r262;
        float r213 = r212 + r69;
        float r333 = (r332 * r710) + r69;
        float r610 = r38 / r37;
        if (r610 <= r17) goto L94;
        float r372 = r710;
        double r611 = Math.toDegrees(-Math.atan(r610));
    L95:
        float r612 = ((float) r611) - 90.0f;
        if (r35 < r42) goto L104;
        float[] r711 = this.mapPoints;
        r711[0] = r35;
        r711[1] = r213;
        this.matrix.reset();
        float r382 = r612;
        this.matrix.postRotate(this.transitionParams.progress * r382, r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        float[] r613 = this.mapPoints;
        float r712 = r613[0];
        float r214 = r613[1];
        if (r712 >= r42) goto L100;
        r712 = r42;
    L100:
        r613[0] = r35;
        r613[1] = r333;
        this.matrix.reset();
        this.matrix.postRotate(this.transitionParams.progress * r382, r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        int r215 = r10;
        float r102 = r214;
        float r713 = r712;
        float r353 = r83;
        float r84 = this.mapPoints[1];
        if (r362 >= 0) goto L103;
        float r614 = r42;
    L114:
        int r31 = r12;
        if (r147 != r12) goto L122;
        float r122 = getMeasuredHeight();
        long[] r363 = r135;
        if (this.transitionMode != 2) goto L120;
        if (r23 == r36) goto L120;
        float[] r123 = this.mapPoints;
        r123[0] = r17 - r42;
        r123[1] = r122;
        this.matrix.reset();
        Matrix r124 = this.matrix;
        TransitionParams r136 = this.transitionParams;
        float r373 = r153;
        float r154 = r136.progress;
        r124.postRotate((r382 * r154) + (r154 * r136.angle[r23]), r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        float[] r125 = this.mapPoints;
        float r137 = r125[0];
        float r126 = r125[1];
    L121:
        r92.chartPath.moveTo(r137, r126);
        this.skipPoints[r23] = false;
    L123:
        TransitionParams r127 = this.transitionParams;
        if (r127 != null) goto L126;
        float r128 = r17;
    L127:
        if (r62 != r17) goto L144;
        if (r147 <= 0) goto L144;
        if (r363[r147 - 1] != 0) goto L144;
        if (r147 >= r114) goto L144;
        if (r363[r147 + 1] != 0) goto L144;
        if (this.transitionMode == 2) goto L144;
        if (this.skipPoints[r23] == true) goto L142;
        Path r138 = r92.chartPath;
        if (r23 != r36) goto L141;
        r138.lineTo(r614, r84 * (r16 - r128));
        goto L142
    L141:
        r138.lineTo(r614, r84);
    L142:
        this.skipPoints[r23] = true;
    L154:
        if (r147 != r114) goto L195;
        float r615 = getMeasuredWidth();
        float r85 = getMeasuredHeight();
        if (this.transitionMode != 2) goto L159;
        if (r23 == r36) goto L159;
        float[] r129 = this.mapPoints;
        r129[0] = r615 + r42;
        r129[1] = r85;
        this.matrix.reset();
        Matrix r616 = this.matrix;
        TransitionParams r86 = this.transitionParams;
        r616.postRotate(r86.progress * r86.angle[r23], r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        float[] r617 = this.mapPoints;
        float r87 = r617[0];
        float r618 = r617[1];
    L161:
        if (this.transitionMode != 2) goto L196;
        if (r23 == r36) goto L195;
        TransitionParams r619 = this.transitionParams;
        float r88 = r619.startX[r23];
        float r620 = (r5 - r619.startY[r23]) / (r42 - r88);
        if (r620 <= r17) goto L167;
        double r1210 = Math.toDegrees(-Math.atan(r620));
    L168:
        TransitionParams r89 = this.transitionParams;
        float r1211 = r89.startX[r23];
        float r810 = r89.startY[r23];
        float[] r139 = this.mapPoints;
        r139[0] = r1211;
        r139[1] = r810;
        this.matrix.reset();
        Matrix r811 = this.matrix;
        TransitionParams r1212 = this.transitionParams;
        float r1310 = r1212.progress;
        r811.postRotate(((((float) r1210) - 90.0f) * r1310) + (r1310 * r1212.angle[r23]), r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        float[] r621 = this.mapPoints;
        float r812 = r621[0];
        float r622 = r621[1];
        if (Math.abs(r713 - r812) < 0.001d) goto L171;
    L182:
        int r714 = quarterForPoint(r713, r102);
        int r623 = quarterForPoint(r812, r622);
    L183:
        if (r714 > r623) goto L195;
        if (r714 != 0) goto L188;
        float r1213 = r17;
        r92.chartPath.lineTo(getMeasuredWidth(), r1213);
    L194:
        r714 = r714 + 1;
        r17 = r1213;
        goto L183
    L188:
        if (r714 != 1) goto L190;
        r92.chartPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
        r1213 = 0.0f;
        goto L194
    L190:
        Path r813 = r92.chartPath;
        if (r714 != 2) goto L193;
        r1213 = 0.0f;
        r813.lineTo(0.0f, getMeasuredHeight());
        goto L194
    L193:
        r1213 = 0.0f;
        r813.lineTo(0.0f, 0.0f);
        goto L194
    L171:
        if (r622 >= r5) goto L175;
        if (r102 >= r5) goto L175;
    L179:
        if (this.transitionParams.angle[r23] != (-180.0f)) goto L181;
        r623 = 0;
        r714 = 0;
        goto L183
    L181:
        r714 = 0;
        r623 = 3;
    L175:
        if (r622 <= r5) goto L182;
        if (r102 <= r5) goto L182;
    L167:
        r1210 = Math.toDegrees(Math.atan(Math.abs(r620)));
        goto L168
    L196:
        float r1214 = r17;
    L197:
        r83 = r353 + r63;
        r6 = r28;
        r153 = r373;
    L198:
        r23 = r23 + 1;
        r17 = r1214;
        r10 = r215;
        r75 = r29;
        r12 = r31;
        r21 = r36;
        r33 = r272;
    L159:
        r92.chartPath.lineTo(r615, r85);
    L195:
        r1214 = r17;
    L144:
        if (this.skipPoints[r23] == false) goto L149;
        Path r1311 = r92.chartPath;
        if (r23 != r36) goto L148;
        r1311.lineTo(r614, r84 * (r16 - r128));
        goto L149
    L148:
        r1311.lineTo(r614, r84);
    L149:
        Path r624 = r92.chartPath;
        if (r23 != r36) goto L152;
        r624.lineTo(r713, (r16 - r128) * r102);
    L153:
        this.skipPoints[r23] = false;
        goto L154
    L152:
        r624.lineTo(r713, r102);
        goto L153
    L126:
        r128 = r127.progress;
    L120:
        r373 = r153;
        r137 = r17;
        r126 = r122;
        goto L121
    L122:
        r363 = r135;
        r373 = r153;
        goto L123
    L103:
        r614 = r35;
        goto L114
    L104:
        r382 = r612;
        if (r352 < r42) goto L107;
        r614 = (r35 * r372) + (r42 * r262);
        float r715 = (r213 * r372) + (r5 * r262);
        r353 = r83;
        r215 = r10;
        r84 = r715;
        r102 = r84;
        r713 = r614;
        goto L114
    L107:
        float[] r716 = this.mapPoints;
        r716[0] = r35;
        r716[1] = r213;
        this.matrix.reset();
        Matrix r717 = this.matrix;
        TransitionParams r625 = this.transitionParams;
        r353 = r83;
        float r814 = r625.progress;
        r717.postRotate((r814 * r382) + (r814 * r625.angle[r23]), r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        float[] r626 = this.mapPoints;
        float r718 = r626[0];
        float r815 = r626[1];
        if (r352 < r42) goto L111;
        float[] r216 = r626;
        float r627 = this.transitionParams.progress;
        r216[0] = (r35 * (r16 - r627)) + (r627 * r42);
    L112:
        r216[1] = r333;
        this.matrix.reset();
        Matrix r628 = this.matrix;
        TransitionParams r719 = this.transitionParams;
        float r816 = r719.progress;
        r628.postRotate((r816 * r382) + (r816 * r719.angle[r23]), r42, r5);
        this.matrix.mapPoints(this.mapPoints);
        float[] r629 = this.mapPoints;
        float r720 = r629[0];
        r84 = r629[1];
        r614 = r720;
        r713 = r718;
        r215 = r10;
        r102 = r815;
        goto L114
    L111:
        r216 = r626;
        r216[0] = r35;
        goto L112
    L94:
        r372 = r710;
        r611 = Math.toDegrees(Math.atan(Math.abs(r610)));
        goto L95
    L89:
        r362 = r66;
        r67 = r78.endX[r23];
        r79 = r78.endY[r23];
    L113:
        r353 = r83;
        r215 = r10;
        r102 = r64;
        r382 = r17;
        r614 = r35;
        r713 = r614;
        r84 = r332;
        goto L114
    L80:
        r332 = r65;
        if (r147 != r12) goto L83;
        r153 = r35;
        goto L83
    L72:
        r77 = (((StackLinearChartData) r76).xPercentage[r147 + 1] * r252) - r272;
        goto L73
    L64:
        r28 = r6;
        r62 = r92.alpha;
        r34 = r75;
        goto L69
    L66:
        if (r20 == r17) goto L63;
        r28 = r6;
        r34 = r75;
        r62 = (r135[r147] * r92.alpha) / r20;
        goto L69
    L57:
        if (r92.alpha != r17) goto L59;
        r272 = r33;
        r29 = r75;
        r31 = r12;
        r1214 = r17;
        r36 = r21;
        r215 = r10;
        goto L198
    L199:
        r147 = r147 + 1;
        r22 = r252;
        r9 = r262;
        r12 = r12;
        goto L37
    L200:
        int r292 = r75;
        r41.save();
        r41.clipRect(r153, BaseChartView.SIGNATURE_TEXT_HEIGHT, r6, getMeasuredHeight() - this.chartBottom);
        if (r19 == false) goto L203;
        r41.drawColor(Theme.getColor(Theme.key_statisticChartLineEmpty));
    L203:
        int r24 = this.lines.size() - 1;
    L204:
        if (r24 < 0) goto L206;
        LineViewData r39 = (LineViewData) this.lines.get(r24);
        r39.paint.setAlpha(r292);
        r41.drawPath(r39.chartPath, r39.paint);
        r39.paint.setAlpha(255);
        r24 = r24 - 1;
        goto L204
    L206:
        r41.restore();
        r41.restore();
        return;
    L35:
        r112 = ((StackLinearChartData) r11).xPercentage[1] * r22;
        goto L36
    L25:
        r144 = r152.width();
        goto L26
    L21:
        r132 = r142.height();
        goto L22
    L28:
        r16 = 1.0f;
        r17 = 0.0f;
        if (r73 != 3) goto L31;
        r75 = (int) (this.transitionParams.progress * 255.0f);
        r9 = 0.0f;
        goto L32
    L31:
        r9 = 0.0f;
        goto L27
    L11:
        if (r72.length >= ((StackLinearChartData) this.chartData).lines.size()) goto L13;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void drawPickerChart(Canvas r26) {
        if (this.chartData == 0) goto L80;
        int r2 = this.lines.size();
        int r4 = 0;
    L5:
        if (r4 >= r2) goto L7;
        ((StackLinearViewData) this.lines.get(r4)).chartPathPicker.reset();
        r4 = r4 + 1;
        goto L5
    L7:
        T r22 = this.chartData;
        int r42 = ((StackLinearChartData) r22).simplifiedSize;
        boolean[] r5 = this.skipPoints;
        if (r5 != null) goto L10;
    L11:
        this.skipPoints = new boolean[((StackLinearChartData) this.chartData).lines.size()];
    L12:
        int r23 = 0;
        boolean r52 = false;
    L13:
        boolean r6 = true;
        if (r23 >= r42) goto L75;
        float r7 = 0.0f;
        float r9 = 0.0f;
        int r8 = 0;
        int r10 = 0;
        int r11 = 0;
    L16:
        long r13 = 0;
        if (r8 >= this.lines.size()) goto L28;
        LineViewData r12 = (LineViewData) this.lines.get(r8);
        if (r12.enabled == true) goto L24;
        if (r12.alpha != 0.0f) goto L24;
    L27:
        r8 = r8 + 1;
    L24:
        if (((StackLinearChartData) this.chartData).simplifiedY[r8][r23] <= 0) goto L26;
        r9 = r9 + (((StackLinearChartData) r11).simplifiedY[r8][r23] * r12.alpha);
        r10 = r10 + 1;
    L26:
        r11 = r8;
        goto L27
    L28:
        int r122 = r42 - 1;
        float r82 = (r23 / r122) * this.pickerWidth;
        float r16 = 0.0f;
        int r15 = 0;
    L30:
        if (r15 >= this.lines.size()) goto L73;
        LineViewData r3 = (LineViewData) this.lines.get(r15);
        long r18 = r13;
        if (r3.enabled == false) goto L34;
    L36:
        if (r10 != r6) goto L42;
        if (((StackLinearChartData) this.chartData).simplifiedY[r15][r23] != r18) goto L40;
    L39:
        boolean r14 = r6;
        float r132 = r7;
        float r20 = r132;
    L46:
        if (r132 != r20) goto L49;
        if (r15 != r11) goto L49;
        r52 = r14;
    L49:
        int r62 = this.pikerHeight;
        float r133 = r132 * r62;
        float r72 = (r62 - r133) - r16;
        if (r23 != 0) goto L52;
        boolean r21 = r14;
        int r222 = r42;
        float r43 = r20;
        r3.chartPathPicker.moveTo(r43, r62);
        this.skipPoints[r15] = false;
    L53:
        T r63 = this.chartData;
        if (((StackLinearChartData) r63).simplifiedY[r15][r23] != r18) goto L66;
        if (r23 <= 0) goto L66;
        if (((StackLinearChartData) r63).simplifiedY[r15][r23 - 1] != r18) goto L66;
        if (r23 >= r122) goto L66;
        if (((StackLinearChartData) r63).simplifiedY[r15][r23 + 1] != r18) goto L66;
        if (this.skipPoints[r15] == true) goto L64;
        r3.chartPathPicker.lineTo(r82, this.pikerHeight);
    L64:
        this.skipPoints[r15] = r21;
    L69:
        if (r23 != r122) goto L71;
        r3.chartPathPicker.lineTo(this.pickerWidth, this.pikerHeight);
    L71:
        r16 = r16 + r133;
    L72:
        r15 = r15 + 1;
        r7 = r43;
        r13 = r18;
        r6 = r21;
        r42 = r222;
    L66:
        if (this.skipPoints[r15] == false) goto L68;
        r3.chartPathPicker.lineTo(r82, this.pikerHeight);
    L68:
        r3.chartPathPicker.lineTo(r82, r72);
        this.skipPoints[r15] = false;
        goto L69
    L52:
        r222 = r42;
        r21 = r14;
        r43 = r20;
        goto L53
    L40:
        r132 = r3.alpha;
        r14 = r6;
        r20 = r7;
        goto L46
    L42:
        if (r9 == r7) goto L39;
        r14 = r6;
        r20 = r7;
        r132 = (((StackLinearChartData) this.chartData).simplifiedY[r15][r23] * r3.alpha) / r9;
        goto L46
    L34:
        if (r3.alpha != r7) goto L36;
        r222 = r42;
        r21 = r6;
        r43 = r7;
        goto L72
    L73:
        r23 = r23 + 1;
        goto L13
    L75:
        if (r52 == false) goto L77;
        r26.drawColor(Theme.getColor(Theme.key_statisticChartLineEmpty));
    L77:
        int r24 = this.lines.size() - 1;
    L78:
        if (r24 < 0) goto L90;
        LineViewData r32 = (LineViewData) this.lines.get(r24);
        r26.drawPath(r32.chartPathPicker, r32.paint);
        r24 = r24 - 1;
        goto L78
    L90:
        return;
    L10:
        if (r5.length >= ((StackLinearChartData) r22).lines.size()) goto L12;
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public void fillTransitionParams(TransitionParams r21) {
        T r1 = this.chartData;
        if (r1 == 0) goto L50;
        float r2 = this.chartWidth;
        ChartPickerDelegate r3 = this.pickerDelegate;
        float r4 = r3.pickerEnd;
        float r32 = r3.pickerStart;
        float r22 = r2 / (r4 - r32);
        float r42 = BaseChartView.HORIZONTAL_PADDING;
        float r33 = (r32 * r22) - r42;
        int r6 = 2;
        int r7 = 1;
        if (((StackLinearChartData) r1).xPercentage.length >= 2) goto L8;
        float r12 = 1.0f;
    L9:
        int r13 = ((int) (r42 / r12)) + 1;
        int r5 = 0;
        int r43 = Math.max(0, (this.startXIndex - r13) - 1);
        int r14 = Math.min(((StackLinearChartData) this.chartData).xPercentage.length - 1, (this.endXIndex + r13) + 1);
        this.transitionParams.startX = new float[((StackLinearChartData) this.chartData).lines.size()];
        this.transitionParams.startY = new float[((StackLinearChartData) this.chartData).lines.size()];
        this.transitionParams.endX = new float[((StackLinearChartData) this.chartData).lines.size()];
        this.transitionParams.endY = new float[((StackLinearChartData) this.chartData).lines.size()];
        this.transitionParams.angle = new float[((StackLinearChartData) this.chartData).lines.size()];
        int r8 = 0;
    L10:
        if (r8 >= r6) goto L60;
        if (r8 != r7) goto L13;
        int r9 = r14;
    L14:
        float r10 = 0.0f;
        int r11 = r5;
        int r132 = r11;
        float r122 = 0.0f;
    L16:
        if (r11 >= this.lines.size()) goto L26;
        LineViewData r142 = (LineViewData) this.lines.get(r11);
        if (r142.enabled == false) goto L20;
    L22:
        float r17 = r10;
        int r18 = r11;
        long r102 = r142.line.y[r9];
        if (r102 <= 0) goto L25;
        r122 = r122 + (r102 * r142.alpha);
        r132 = r132 + 1;
    L25:
        r11 = r18 + 1;
        r10 = r17;
        goto L16
    L20:
        if (r142.alpha != r10) goto L22;
        r17 = r10;
        r18 = r11;
        goto L25
    L26:
        float r172 = r10;
        int r52 = 0;
        int r103 = 0;
    L28:
        if (r52 >= this.lines.size()) goto L49;
        LineViewData r112 = (LineViewData) this.lines.get(r52);
        if (r112.enabled == false) goto L32;
    L34:
        long[] r143 = r112.line.y;
        if (r132 != r7) goto L41;
        if (r143[r9] != 0) goto L39;
    L38:
        float r113 = r172;
    L44:
        float r62 = (((StackLinearChartData) this.chartData).xPercentage[r9] * r22) - r33;
        float r114 = r113 * ((getMeasuredHeight() - this.chartBottom) - BaseChartView.SIGNATURE_TEXT_HEIGHT);
        float r104 = r103;
        float r72 = ((getMeasuredHeight() - this.chartBottom) - r114) - r104;
        r103 = (int) (r104 + r114);
        TransitionParams r115 = this.transitionParams;
        if (r8 != 0) goto L47;
        r115.startX[r52] = r62;
        r115.startY[r52] = r72;
    L48:
        r52 = r52 + 1;
        r7 = 1;
        goto L28
    L47:
        r115.endX[r52] = r62;
        r115.endY[r52] = r72;
        goto L48
    L39:
        r113 = r112.alpha;
        goto L44
    L41:
        if (r122 == r172) goto L38;
        r113 = (r143[r9] * r112.alpha) / r122;
        goto L44
    L32:
        if (r112.alpha != r172) goto L34;
    L49:
        r8 = r8 + 1;
        r5 = 0;
        r6 = 2;
        r7 = 1;
        goto L10
    L13:
        r9 = r43;
        goto L14
    L60:
        return;
    L8:
        r12 = ((StackLinearChartData) r1).xPercentage[1] * r22;
        goto L9
    }

    @Override // org.telegram.ui.Charts.BaseChartView
    public long findMaxValue(int r1, int r2) {
        return 100;
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
