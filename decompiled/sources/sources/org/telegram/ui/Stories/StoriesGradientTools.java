package org.telegram.ui.Stories;

import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.GradientTools;

/* loaded from: classes7.dex */
public class StoriesGradientTools extends GradientTools {
    int colorKey1;
    int colorKey2;

    public StoriesGradientTools() {
        int r0 = Theme.key_voipgroup_overlayGreen1;
        this.colorKey1 = r0;
        this.colorKey2 = Theme.key_voipgroup_overlayBlue1;
        this.isDiagonal = true;
        setColors(Theme.getColor(r0), Theme.getColor(this.colorKey2));
    }

    @Override // org.telegram.ui.Components.GradientTools
    public void updateBounds() {
        setColors(Theme.getColor(this.colorKey1), Theme.getColor(this.colorKey2));
        super.updateBounds();
    }
}
