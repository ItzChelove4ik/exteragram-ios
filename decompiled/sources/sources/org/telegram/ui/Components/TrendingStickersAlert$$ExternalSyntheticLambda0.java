package org.telegram.ui.Components;

import org.telegram.ui.ActionBar.ThemeDescription;

/* loaded from: classes7.dex */
public final /* synthetic */ class TrendingStickersAlert$$ExternalSyntheticLambda0 implements ThemeDescription.ThemeDescriptionDelegate {
    public final /* synthetic */ TrendingStickersLayout f$0;

    public /* synthetic */ TrendingStickersAlert$$ExternalSyntheticLambda0(TrendingStickersLayout r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
    public final void didSetColor() {
        this.f$0.updateColors();
    }
}
