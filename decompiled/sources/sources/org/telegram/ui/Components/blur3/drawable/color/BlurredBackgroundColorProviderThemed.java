package org.telegram.ui.Components.blur3.drawable.color;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class BlurredBackgroundColorProviderThemed implements BlurredBackgroundColorProvider {
    private float alpha;
    private int backgroundColor;
    private final int backgroundColorId;
    private final Theme.ResourcesProvider resourcesProvider;
    private int shadowColor;
    private int strokeColorBottom;
    private int strokeColorTop;

    public BlurredBackgroundColorProviderThemed(Theme.ResourcesProvider r2, int r3) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r0 = 0.85f;
    L6:
        this(r2, r3, r0);
        return;
    L5:
        r0 = 0.76f;
        goto L6
    }

    public BlurredBackgroundColorProviderThemed(Theme.ResourcesProvider r1, int r2, float r3) {
        this.resourcesProvider = r1;
        this.backgroundColorId = r2;
        this.alpha = r3;
        updateColors();
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getShadowColor() {
        return this.shadowColor;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getStrokeColorBottom() {
        return this.strokeColorBottom;
    }

    @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
    public int getStrokeColorTop() {
        return this.strokeColorTop;
    }

    public boolean isDark() {
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(this.backgroundColorId, this.resourcesProvider)) >= 0.721f) goto L6;
        return true;
    L6:
        return false;
    }

    public void updateColors() {
        this.backgroundColor = Theme.multAlpha(Theme.getColor(this.backgroundColorId, this.resourcesProvider), this.alpha);
        if (isDark() == false) goto L6;
        this.strokeColorTop = 687865855;
        this.strokeColorBottom = 352321535;
        this.shadowColor = 0;
        return;
    L6:
        this.strokeColorTop = -1;
        this.strokeColorBottom = -1;
        this.shadowColor = 536870912;
    }
}
