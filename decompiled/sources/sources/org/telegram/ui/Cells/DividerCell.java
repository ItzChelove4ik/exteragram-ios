package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.DividerStyle;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class DividerCell extends View {
    private boolean forceDarkTheme;
    private Paint paint;
    private Theme.ResourcesProvider resourcesProvider;

    public DividerCell(Context r2) {
        this(r2, null);
    }

    public DividerCell(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.paint = new Paint();
        this.resourcesProvider = r3;
        setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        boolean r0 = this.forceDarkTheme;
        Paint r1 = this.paint;
        if (r0 == false) goto L5;
        r1.setColor(ColorUtils.blendARGB(-16777216, Theme.getColor(Theme.key_voipgroup_dialogBackground, this.resourcesProvider), 0.2f));
    L7:
        if (ExteraConfig.getDividerStyle() != DividerStyle.LINE) goto L10;
        r8.drawLine(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getPaddingTop(), this.paint);
        return;
    L10:
        return;
    L5:
        r1.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        goto L7
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), (getPaddingTop() + getPaddingBottom()) + 1);
    }

    public void setForceDarkTheme(boolean r1) {
        this.forceDarkTheme = r1;
    }
}
