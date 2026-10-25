package org.telegram.ui.Charts.view_data;

import android.graphics.Paint;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Charts.data.ChartData;

/* loaded from: classes6.dex */
public class StackLinearViewData extends LineViewData {
    public StackLinearViewData(ChartData.Line r3) {
        super(r3, false);
        this.paint.setStyle(Paint.Style.FILL);
        if (BaseChartView.USE_LINES == false) goto L6;
        this.paint.setAntiAlias(false);
        return;
    }
}
