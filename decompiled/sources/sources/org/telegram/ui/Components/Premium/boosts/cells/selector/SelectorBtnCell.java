package org.telegram.ui.Components.Premium.boosts.cells.selector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.RecyclerListView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SelectorBtnCell extends LinearLayout {
    private final AnimatedFloat alpha;
    private final Paint dividerPaint;
    private final RecyclerListView listView;
    private final Theme.ResourcesProvider resourcesProvider;

    public SelectorBtnCell(Context r2, Theme.ResourcesProvider r3, RecyclerListView r4) {
        super(r2);
        this.dividerPaint = new Paint(1);
        this.alpha = new AnimatedFloat(this);
        this.resourcesProvider = r3;
        this.listView = r4;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        this.dividerPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        RecyclerListView r0 = this.listView;
        Paint r1 = this.dividerPaint;
        float r2 = 1.0f;
        if (r0 == null) goto L9;
        AnimatedFloat r4 = this.alpha;
        if (r0.canScrollVertically(1) == true) goto L8;
        r2 = 0.0f;
    L8:
        r1.setAlpha((int) (r4.set(r2) * 255.0f));
    L10:
        r8.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.getShadowHeight(), this.dividerPaint);
        return;
    L9:
        r1.setAlpha((int) (this.alpha.set(1.0f) * 255.0f));
        goto L10
    }
}
