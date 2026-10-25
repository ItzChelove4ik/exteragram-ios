package org.telegram.ui.Components.poll.buttons;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class PollButtonDrawableBase extends Drawable {
    private int alpha;
    protected final Theme.ResourcesProvider resourcesProvider;
    protected final Drawable selectorDrawable;
    protected int selectorDrawableColor;

    public PollButtonDrawableBase(Theme.ResourcesProvider r2) {
        this.alpha = 255;
        int r0 = Theme.getColor(Theme.key_listSelector, r2);
        this.selectorDrawableColor = r0;
        this.resourcesProvider = r2;
        this.selectorDrawable = Theme.createRadSelectorDrawable(r0, 0, 0);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Drawable getSelectorDrawable() {
        return this.selectorDrawable;
    }

    public void onAlphaChanged(int r1) {
        this.selectorDrawable.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        this.selectorDrawable.setBounds(r1);
    }

    public void onSelectorColorChanged(int r2) {
        Theme.setSelectorDrawableColor(this.selectorDrawable, r2, false);
    }

    public void resetSelectors() {
        this.selectorDrawable.setState(StateSet.NOTHING);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int r2) {
        if (this.alpha == r2) goto L6;
        this.alpha = r2;
        onAlphaChanged(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public final void setSelectorsColor(int r2) {
        if (this.selectorDrawableColor == r2) goto L6;
        onSelectorColorChanged(r2);
        this.selectorDrawableColor = r2;
        return;
    }

    public void setupCallbacks(Drawable.Callback r1) {
        setCallback(r1);
        this.selectorDrawable.setCallback(r1);
    }

    public boolean verifyDrawable(Drawable r1) {
        if (r1 != this) goto L4;
        return true;
    L4:
        if (r1 == this.selectorDrawable) goto L10;
        return false;
    L10:
        return true;
    }
}
