package org.telegram.ui.Components;

import org.telegram.ui.ActionBar.ThemeDescription;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda8 implements ThemeDescription.ThemeDescriptionDelegate {
    public final /* synthetic */ StickersAlert f$0;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda8(StickersAlert r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
    public final void didSetColor() {
        this.f$0.updateColors();
    }
}
