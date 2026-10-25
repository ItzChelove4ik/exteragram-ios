package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes6.dex */
public class ComposeDrawable extends Drawable {
    private int alpha;
    private final AnimatedFloat animatedIconVisible;
    private final Drawable background;
    private final Drawable icon;
    private boolean iconVisible;
    private int tx;
    private int ty;
    private final ArrayList<View> views;

    public static /* synthetic */ void $r8$lambda$IxzCg1epB1ReofunKykETvZszuo(ComposeDrawable r0) {
        r0.invalidate();
    }

    public ComposeDrawable(Drawable r6, Drawable r7) {
        this.views = new ArrayList();
        this.iconVisible = false;
        this.animatedIconVisible = new AnimatedFloat(new ComposeDrawable$$ExternalSyntheticLambda0(this), 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.alpha = 255;
        this.background = r6;
        this.icon = r7;
    }

    private void invalidate() {
        ArrayList<View> r0 = this.views;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        View r3 = r0.get(r2);
        r2 = r2 + 1;
        r3.invalidate();
        goto L3
    L5:
        invalidateSelf();
    }

    public void addView(View r1) {
        this.views.add(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r8) {
        float r0 = this.animatedIconVisible.set(this.iconVisible);
        this.background.setAlpha(this.alpha);
        this.background.setBounds(getBounds());
        this.background.draw(r8);
        if (r0 <= 0.0f) goto L6;
        this.icon.setAlpha((int) (this.alpha * r0));
        this.icon.setBounds(getBounds().left + this.tx, getBounds().top + this.ty, (getBounds().left + this.tx) + this.icon.getIntrinsicWidth(), (getBounds().top + this.ty) + this.icon.getIntrinsicHeight());
        float r02 = AndroidUtilities.lerp(0.5f, 1.0f, r0);
        r8.save();
        r8.scale(r02, r02, this.icon.getBounds().centerX(), this.icon.getBounds().centerY());
        this.icon.draw(r8);
        r8.restore();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.background.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.background.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.background.setColorFilter(r2);
        this.icon.setColorFilter(r2);
    }

    public void setIconTranslate(int r1, int r2) {
        this.tx = r1;
        this.ty = r2;
    }

    public void setIconVisible(boolean r2) {
        setIconVisible(r2, true);
    }

    public void setIconVisible(boolean r2, boolean r3) {
        if (this.iconVisible != r2) goto L5;
        return;
    L5:
        this.iconVisible = r2;
        if (r3 == true) goto L8;
        this.animatedIconVisible.force(r2);
    L8:
        invalidate();
    }
}
