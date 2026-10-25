package org.telegram.ui.Charts.view_data;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Charts.data.ChartData;

/* loaded from: classes6.dex */
public class LineViewData {
    public float alpha;
    public ValueAnimator animatorIn;
    public ValueAnimator animatorOut;
    public final Paint bottomLinePaint;
    public final Path bottomLinePath;
    public final Path chartPath;
    public final Path chartPathPicker;
    public boolean enabled;
    public final ChartData.Line line;
    public int lineColor;
    public float[] linesPath;
    public float[] linesPathBottom;
    public int linesPathBottomSize;
    public final Paint paint;
    private Theme.ResourcesProvider resourcesProvider;
    public final Paint selectionPaint;

    public LineViewData(ChartData.Line r2, boolean r3) {
        this(r2, r3, null);
    }

    public LineViewData(ChartData.Line r6, boolean r7, Theme.ResourcesProvider r8) {
        Paint r0 = new Paint(1);
        this.bottomLinePaint = r0;
        Paint r2 = new Paint(1);
        this.paint = r2;
        Paint r3 = new Paint(1);
        this.selectionPaint = r3;
        this.bottomLinePath = new Path();
        this.chartPath = new Path();
        this.chartPathPicker = new Path();
        this.enabled = true;
        this.alpha = 1.0f;
        this.resourcesProvider = r8;
        this.line = r6;
        r2.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        Paint.Style r82 = Paint.Style.STROKE;
        r2.setStyle(r82);
        if (BaseChartView.USE_LINES == true) goto L5;
        r2.setStrokeJoin(Paint.Join.ROUND);
    L5:
        r2.setColor(r6.color);
        r0.setStrokeWidth(AndroidUtilities.dpf2(1.0f));
        r0.setStyle(r82);
        r0.setColor(r6.color);
        r3.setStrokeWidth(AndroidUtilities.dpf2(10.0f));
        r3.setStyle(r82);
        r3.setStrokeCap(Paint.Cap.ROUND);
        r3.setColor(r6.color);
        int r83 = r6.y.length;
        if (r7 == false) goto L8;
        int r84 = r83 * 8;
    L9:
        this.linesPath = new float[r84];
        int r62 = r6.y.length;
        if (r7 == false) goto L12;
        int r63 = r62 * 8;
    L13:
        this.linesPathBottom = new float[r63];
        return;
    L12:
        r63 = r62 << 2;
        goto L13
    L8:
        r84 = r83 << 2;
        goto L9
    }

    public void updateColors() {
        int r0 = this.line.colorKey;
        if (r0 >= 0) goto L5;
    L7:
        double r02 = ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        ChartData.Line r1 = this.line;
        if (r02 >= 0.5d) goto L10;
        int r03 = r1.colorDark;
    L11:
        this.lineColor = r03;
    L12:
        this.paint.setColor(this.lineColor);
        this.bottomLinePaint.setColor(this.lineColor);
        this.selectionPaint.setColor(this.lineColor);
        return;
    L10:
        r03 = r1.color;
        goto L11
    L5:
        if (Theme.hasThemeKey(r0) == false) goto L7;
        this.lineColor = Theme.getColor(this.line.colorKey, this.resourcesProvider);
        goto L12
    }
}
