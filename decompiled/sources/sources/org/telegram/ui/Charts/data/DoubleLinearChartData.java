package org.telegram.ui.Charts.data;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public class DoubleLinearChartData extends ChartData {
    public float[] linesK;

    public DoubleLinearChartData(JSONObject r1) {
        super(r1);
    }

    @Override // org.telegram.ui.Charts.data.ChartData
    public void measure() {
        super.measure();
        int r0 = this.lines.size();
        long r1 = 0;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L8;
        long r5 = this.lines.get(r4).maxValue;
        if (r5 <= r1) goto L7;
        r1 = r5;
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        this.linesK = new float[r0];
    L9:
        if (r3 >= r0) goto L15;
        long r42 = this.lines.get(r3).maxValue;
        float[] r7 = this.linesK;
        if (r1 != r42) goto L13;
        r7[r3] = 1.0f;
    L14:
        r3 = r3 + 1;
        goto L9
    L13:
        r7[r3] = r1 / r42;
        goto L14
    }
}
