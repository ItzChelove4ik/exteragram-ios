package org.telegram.ui.Charts.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.time.DurationKt;
import org.json.JSONObject;
import org.telegram.messenger.SegmentTree;
import org.telegram.ui.Charts.data.ChartData;

/* loaded from: classes6.dex */
public class StackLinearChartData extends ChartData {
    public int simplifiedSize;
    public long[][] simplifiedY;
    long[] ySum;
    SegmentTree ySumSegmentTree;

    public StackLinearChartData(JSONObject r14, boolean r15) {
        super(r14);
        if (r15 == false) goto L26;
        long[] r152 = new long[this.lines.size()];
        int[] r2 = new int[this.lines.size()];
        int r3 = 0;
        long r4 = 0;
    L6:
        if (r3 >= this.lines.size()) goto L14;
        int r6 = this.x.length;
        int r7 = 0;
    L8:
        if (r7 >= r6) goto L13;
        long r9 = this.lines.get(r3).y[r7];
        r152[r3] = r152[r3] + r9;
        if (r9 != 0) goto L12;
        r2[r3] = r2[r3] + 1;
    L12:
        r7 = r7 + 1;
        goto L8
    L13:
        r4 = r4 + r152[r3];
        r3 = r3 + 1;
        goto L6
    L14:
        ArrayList r32 = new ArrayList();
        int r62 = 0;
    L16:
        if (r62 >= this.lines.size()) goto L23;
        if ((r152[r62] / r4) >= 0.01d) goto L22;
        if (r2[r62] <= (this.x.length / 2.0f)) goto L22;
        r32.add(this.lines.get(r62));
    L22:
        r62 = r62 + 1;
        goto L16
    L23:
        int r153 = r32.size();
        int r22 = 0;
    L24:
        if (r22 >= r153) goto L26;
        Object r42 = r32.get(r22);
        r22 = r22 + 1;
        this.lines.remove((ChartData.Line) r42);
    L26:
        int r154 = this.lines.get(0).y.length;
        int r23 = this.lines.size();
        this.ySum = new long[r154];
        int r33 = 0;
    L27:
        long[] r43 = this.ySum;
        if (r33 >= r154) goto L33;
        r43[r33] = 0;
        int r44 = 0;
    L30:
        if (r44 >= r23) goto L32;
        long[] r5 = this.ySum;
        r5[r33] = r5[r33] + this.lines.get(r44).y[r33];
        r44 = r44 + 1;
        goto L30
    L32:
        r33 = r33 + 1;
        goto L27
    L33:
        this.ySumSegmentTree = new SegmentTree(r43);
    }

    public StackLinearChartData(ChartData r8, long r9) {
        int r92 = Arrays.binarySearch(r8.x, r9);
        int r10 = r92 - 4;
        int r93 = r92 + 4;
        if (r10 >= 0) goto L5;
        r93 = r93 + (-r10);
        r10 = 0;
    L5:
        long[] r1 = r8.x;
        if (r93 <= (r1.length - 1)) goto L8;
        r10 = r10 - (r93 - r1.length);
        r93 = r1.length - 1;
    L8:
        if (r10 >= 0) goto L10;
        r10 = 0;
    L10:
        int r12 = (r93 - r10) + 1;
        this.x = new long[r12];
        this.xPercentage = new float[r12];
        this.lines = new ArrayList();
        int r2 = 0;
    L12:
        if (r2 >= r8.lines.size()) goto L14;
        ChartData.Line r3 = new ChartData.Line(this);
        r3.y = new long[r12];
        r3.id = r8.lines.get(r2).id;
        r3.name = r8.lines.get(r2).name;
        r3.colorKey = r8.lines.get(r2).colorKey;
        r3.color = r8.lines.get(r2).color;
        r3.colorDark = r8.lines.get(r2).colorDark;
        this.lines.add(r3);
        r2 = r2 + 1;
        goto L12
    L14:
        int r13 = 0;
    L15:
        if (r10 > r93) goto L21;
        this.x[r13] = r8.x[r10];
        int r22 = 0;
    L18:
        if (r22 >= this.lines.size()) goto L20;
        this.lines.get(r22).y[r13] = r8.lines.get(r22).y[r10];
        r22 = r22 + 1;
        goto L18
    L20:
        r13 = r13 + 1;
        r10 = r10 + 1;
        goto L15
    L21:
        this.timeStep = DurationKt.MILLIS_IN_DAY;
        measure();
    }

    @Override // org.telegram.ui.Charts.data.ChartData
    public void measure() {
        super.measure();
        this.simplifiedSize = 0;
        int r1 = this.xPercentage.length;
        int r2 = this.lines.size();
        int r3 = Math.max(1, Math.round(r1 / 140.0f));
        int r5 = r1 / r3;
        this.simplifiedY = (long[][]) Array.newInstance(Long.TYPE, new int[]{r2, r5});
        long[] r6 = new long[r2];
        int r7 = 0;
    L3:
        if (r7 >= r1) goto L19;
        int r8 = 0;
    L5:
        if (r8 >= r2) goto L11;
        long r10 = this.lines.get(r8).y[r7];
        if (r10 <= r6[r8]) goto L9;
        r6[r8] = r10;
    L9:
        r8 = r8 + 1;
        goto L5
    L11:
        if ((r7 % r3) != 0) goto L18;
        int r82 = 0;
    L13:
        if (r82 >= r2) goto L15;
        this.simplifiedY[r82][this.simplifiedSize] = r6[r82];
        r6[r82] = 0;
        r82 = r82 + 1;
        goto L13
    L15:
        int r83 = this.simplifiedSize + 1;
        this.simplifiedSize = r83;
        if (r83 < r5) goto L18;
        return;
    L18:
        r7 = r7 + 1;
        goto L3
    }
}
