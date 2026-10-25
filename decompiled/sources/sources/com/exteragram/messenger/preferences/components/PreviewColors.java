package com.exteragram.messenger.preferences.components;

import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
public abstract class PreviewColors {
    public static int getBackgroundColor() {
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r1 = 0.05f;
    L7:
        return Theme.multAlpha(r0, r1);
    L5:
        r1 = 0.035f;
        goto L7
    }

    public static int getMockColor(boolean r1) {
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2);
        if (r1 == false) goto L5;
        float r12 = 0.4f;
    L7:
        return Theme.multAlpha(r0, r12);
    L5:
        r12 = 0.2f;
        goto L7
    }

    public static int getOutlineColor() {
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r1 = 0.05f;
    L7:
        return Theme.multAlpha(r0, r1 + 0.085f);
    L5:
        r1 = 0.035f;
        goto L7
    }
}
