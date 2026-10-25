package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.CombinedDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class TextInfoCell extends TextInfoPrivacyCell {
    private final Theme.ResourcesProvider resourcesProvider;

    public TextInfoCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1, r2);
        this.resourcesProvider = r2;
    }

    public void setBackground(boolean r4) {
        Context r0 = getContext();
        if (r4 == false) goto L5;
        int r42 = R.drawable.greydivider_bottom;
    L6:
        Drawable r43 = Theme.getThemedDrawable(r0, r42, Theme.getColor(Theme.key_windowBackgroundGrayShadow, this.resourcesProvider));
        CombinedDrawable r1 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider)), r43, 0, 0);
        r1.setFullsize(true);
        setBackground(r1);
        return;
    L5:
        r42 = R.drawable.greydivider;
        goto L6
    }
}
