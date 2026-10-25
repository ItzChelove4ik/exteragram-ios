package org.telegram.ui.Components;

import org.telegram.ui.ActionBar.ThemeDescription;

/* loaded from: classes7.dex */
public final /* synthetic */ class SimpleThemeDescription$$ExternalSyntheticLambda0 implements ThemeDescription.ThemeDescriptionDelegate {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ SimpleThemeDescription$$ExternalSyntheticLambda0(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
    public final void didSetColor() {
        this.f$0.run();
    }
}
