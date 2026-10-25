package org.telegram.ui.Charts.data;

import org.json.JSONObject;
import org.telegram.messenger.SegmentTree;

/* loaded from: classes6.dex */
public class StackBarChartData extends ChartData {
    public long[] ySum;
    public SegmentTree ySumSegmentTree;

    public StackBarChartData(JSONObject r1) {
        super(r1);
        init();
    }

    public long findMax(int r1, int r2) {
        return this.ySumSegmentTree.rMaxQ(r1, r2);
    }

    public void init() {
        int r0 = this.lines.get(0).y.length;
        int r2 = this.lines.size();
        this.ySum = new long[r0];
        int r3 = 0;
    L3:
        long[] r4 = this.ySum;
        if (r3 >= r0) goto L9;
        r4[r3] = 0;
        int r42 = 0;
    L6:
        if (r42 >= r2) goto L8;
        long[] r5 = this.ySum;
        r5[r3] = r5[r3] + this.lines.get(r42).y[r3];
        r42 = r42 + 1;
        goto L6
    L8:
        r3 = r3 + 1;
        goto L3
    L9:
        this.ySumSegmentTree = new SegmentTree(r4);
    }
}
