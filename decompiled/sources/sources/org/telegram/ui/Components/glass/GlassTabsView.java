package org.telegram.ui.Components.glass;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public abstract class GlassTabsView extends FrameLayout {
    private final Rect lensBounds;
    private final Rect lensBoundsForeground;
    private final Paint lensPaint;
    private float lensVisibility;
    public final LinearLayout linearLayout;

    public GlassTabsView(Context r5) {
        super(r5);
        this.lensBounds = new Rect();
        this.lensBoundsForeground = new Rect();
        this.lensPaint = new Paint(1);
        setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        LinearLayout r0 = new LinearLayout(r5);
        this.linearLayout = r0;
        r0.setOrientation(0);
        addView(r0, LayoutHelper.createFrame(-1, -1.0f));
    }

    private void checkBounds() {
        int r0 = AndroidUtilities.dp(this.lensVisibility * 7.0f);
        this.lensBoundsForeground.set(this.lensBounds);
        int r02 = -r0;
        this.lensBoundsForeground.inset(r02, r02);
    }

    public void setLensBounds(int r2, int r3, int r4, int r5) {
        this.lensBounds.set(r2, r3, r4, r5);
        checkBounds();
    }

    public void setLensColor(int r1, int r2) {
        this.lensPaint.setColor(r1);
    }

    public void setLensVisibility(float r1) {
        this.lensVisibility = r1;
        checkBounds();
    }
}
