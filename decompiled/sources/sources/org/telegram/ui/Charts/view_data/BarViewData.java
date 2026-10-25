package org.telegram.ui.Charts.view_data;

import android.graphics.Paint;
import androidx.core.graphics.ColorUtils;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.data.ChartData;

/* loaded from: classes6.dex */
public class BarViewData extends LineViewData {
    public int blendColor;
    private Theme.ResourcesProvider resourcesProvider;
    public final Paint unselectedPaint;

    public BarViewData(ChartData.Line r3, Theme.ResourcesProvider r4) {
        super(r3, false);
        Paint r32 = new Paint();
        this.unselectedPaint = r32;
        this.blendColor = 0;
        this.resourcesProvider = r4;
        Paint r42 = this.paint;
        Paint.Style r1 = Paint.Style.STROKE;
        r42.setStyle(r1);
        r32.setStyle(r1);
        this.paint.setAntiAlias(false);
    }

    @Override // org.telegram.ui.Charts.view_data.LineViewData
    public void updateColors() {
        super.updateColors();
        this.blendColor = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), this.lineColor, 0.3f);
    }
}
