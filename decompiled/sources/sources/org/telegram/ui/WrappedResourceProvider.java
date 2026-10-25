package org.telegram.ui;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class WrappedResourceProvider implements Theme.ResourcesProvider {
    Theme.ResourcesProvider resourcesProvider;
    public SparseIntArray sparseIntArray;

    public WrappedResourceProvider(Theme.ResourcesProvider r2) {
        this.sparseIntArray = new SparseIntArray();
        this.resourcesProvider = r2;
        appendColors();
    }

    public void appendColors() {
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public void applyServiceShaderMatrix(int r1, int r2, float r3, float r4) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L6;
        Theme.applyServiceShaderMatrix(r1, r2, r3, r4);
        return;
    L6:
        r0.applyServiceShaderMatrix(r1, r2, r3, r4);
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public ColorFilter getAnimatedEmojiColorFilter() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L7;
        return Theme.getAnimatedEmojiColorFilter(null);
    L7:
        return r0.getAnimatedEmojiColorFilter();
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public int getColor(int r2) {
        int r0 = this.sparseIntArray.indexOfKey(r2);
        if (r0 >= 0) goto L5;
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        if (r1 != null) goto L11;
        return Theme.getColor(r2);
    L11:
        return r1.getColor(r2);
    L5:
        return this.sparseIntArray.valueAt(r0);
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public int getColorOrDefault(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L7;
        return Theme.getColor(r1);
    L7:
        return r0.getColorOrDefault(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public int getCurrentColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L7;
        return Theme.getColor(r1);
    L7:
        return r0.getCurrentColor(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public Drawable getDrawable(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L7;
        return Theme.getThemeDrawable(r1);
    L7:
        return r0.getDrawable(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public Paint getPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L7;
        return Theme.getThemePaint(r1);
    L7:
        return r0.getPaint(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public boolean hasGradientService() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 != null) goto L7;
        return Theme.hasGradientService();
    L7:
        return r0.hasGradientService();
    }

    @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
    public void setAnimatedColor(int r1, int r2) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L6;
        r0.setAnimatedColor(r1, r2);
        return;
    }
}
