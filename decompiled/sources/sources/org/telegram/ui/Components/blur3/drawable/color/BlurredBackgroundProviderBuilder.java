package org.telegram.ui.Components.blur3.drawable.color;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes3.dex */
public class BlurredBackgroundProviderBuilder implements BlurredBackgroundProvider {
    private ColorProvider backgroundColor;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean shadowAlwaysVisible;
    private ColorProvider shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private ColorProvider strokeColorBottom;
    private ColorProvider strokeColorFull;
    private ColorProvider strokeColorTop;
    private float strokeWidthBottom;
    private float strokeWidthTop;

    public interface ColorProvider {
        int getColor(Theme.ResourcesProvider r1, boolean r2);
    }

    public static /* synthetic */ int $r8$lambda$L6TaFC4EiCuKVKYV83zwJJCpq8s(int r0, int r1, Theme.ResourcesProvider r2, boolean r3) {
        if (r3 == false) goto L4;
        return r0;
    L4:
        return r1;
    }

    public BlurredBackgroundProviderBuilder(Theme.ResourcesProvider r4) {
        this.resourcesProvider = r4;
        setShadowLayer(AndroidUtilities.dpf2(1.0f), 0.0f, AndroidUtilities.dpf2(0.33333334f));
        setStrokeWidth(AndroidUtilities.dpf2(1.0f), AndroidUtilities.dpf2(0.6666667f));
    }

    private static ColorProvider create(int r1, int r2) {
        return new BlurredBackgroundProviderBuilder$$ExternalSyntheticLambda0(r2, r1);
    }

    private int get(ColorProvider r1, int r2) {
        if (r1 != null) goto L4;
        return r2;
    L4:
        return r1.getColor(this.resourcesProvider, isDark());
    }

    private boolean isDark() {
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        if ((r1 instanceof DarkThemeResourceProvider) == true) goto L13;
        if (r1 == null) goto L9;
        if (r1.isDark() == true) goto L16;
        return false;
    L16:
        return true;
    L9:
        if (Theme.isCurrentThemeDark() == true) goto L17;
        return false;
    L17:
        return true;
    L13:
        return true;
    }

    public BlurredBackgroundProvider build() {
        return this;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getBackgroundColor() {
        return get(this.backgroundColor, 0);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getShadowColor() {
        return get(this.shadowColor, 0);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider
    public float getShadowDx() {
        return this.shadowDx;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider
    public float getShadowDy() {
        return this.shadowDy;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider
    public float getShadowRadius() {
        return this.shadowRadius;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getStrokeColorBottom() {
        return get(this.strokeColorBottom, 0);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getStrokeColorFull() {
        ColorProvider r0 = this.strokeColorFull;
        if (r0 == null) goto L7;
        return get(r0, 0);
    L7:
        return Theme.getDividerColor(this.resourcesProvider);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getStrokeColorTop() {
        return get(this.strokeColorTop, 0);
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider
    public float getStrokeWidthBottom() {
        return this.strokeWidthBottom;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider
    public float getStrokeWidthTop() {
        return this.strokeWidthTop;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider
    public boolean isShadowAlwaysVisible() {
        return this.shadowAlwaysVisible;
    }

    public BlurredBackgroundProviderBuilder setBackgroundColor(ColorProvider r1) {
        this.backgroundColor = r1;
        return this;
    }

    public BlurredBackgroundProviderBuilder setShadowAlwaysVisible() {
        this.shadowAlwaysVisible = true;
        return this;
    }

    public BlurredBackgroundProviderBuilder setShadowColor(int r1, int r2) {
        this.shadowColor = create(r1, r2);
        return this;
    }

    public BlurredBackgroundProviderBuilder setShadowColor(ColorProvider r1) {
        this.shadowColor = r1;
        return this;
    }

    public BlurredBackgroundProviderBuilder setShadowLayer(float r1, float r2, float r3) {
        this.shadowRadius = r1;
        this.shadowDx = r2;
        this.shadowDy = r3;
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeColorBottom(int r1, int r2) {
        this.strokeColorBottom = create(r1, r2);
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeColorBottom(ColorProvider r1) {
        this.strokeColorBottom = r1;
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeColorFull(int r1, int r2) {
        this.strokeColorFull = create(r1, r2);
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeColorFull(ColorProvider r1) {
        this.strokeColorFull = r1;
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeColorTop(int r1, int r2) {
        this.strokeColorTop = create(r1, r2);
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeColorTop(ColorProvider r1) {
        this.strokeColorTop = r1;
        return this;
    }

    public BlurredBackgroundProviderBuilder setStrokeWidth(float r1, float r2) {
        this.strokeWidthTop = r1;
        this.strokeWidthBottom = r2;
        return this;
    }
}
