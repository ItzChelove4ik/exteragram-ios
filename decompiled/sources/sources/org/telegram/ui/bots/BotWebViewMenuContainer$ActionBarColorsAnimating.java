package org.telegram.ui.bots;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.SparseIntArray;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class BotWebViewMenuContainer$ActionBarColorsAnimating {
    int[] colorKeys;
    SparseIntArray fromColors;
    public float progress;
    SparseIntArray toColors;

    public BotWebViewMenuContainer$ActionBarColorsAnimating() {
        this.fromColors = new SparseIntArray();
        this.toColors = new SparseIntArray();
        this.colorKeys = new int[]{Theme.key_windowBackgroundWhiteBlackText, Theme.key_actionBarWhiteSelector, Theme.key_actionBarDefaultSubmenuBackground, Theme.key_actionBarDefaultSubmenuItem, Theme.key_actionBarDefaultSubmenuItemIcon, Theme.key_dialogButtonSelector, Theme.key_sheet_scrollUp};
    }

    private void updateColors(SparseIntArray r7, int r8, Theme.ResourcesProvider r9) {
        int r0 = 0;
        if (r8 != 0) goto L8;
    L4:
        int[] r82 = this.colorKeys;
        if (r0 >= r82.length) goto L34;
        int r83 = r82[r0];
        r7.put(r83, Theme.getColor(r83, r9));
        r0 = r0 + 1;
        goto L4
    L34:
        return;
    L8:
        if (ColorUtils.calculateLuminance(r8) >= 0.5d) goto L10;
        int r1 = -1;
    L11:
        int r2 = ColorUtils.setAlphaComponent(r1, 60);
    L12:
        int[] r3 = this.colorKeys;
        if (r0 >= r3.length) goto L41;
        int r32 = r3[r0];
        if (r32 != Theme.key_actionBarDefaultSubmenuBackground) goto L17;
    L32:
        r7.put(r32, Theme.getColor(r32, r9));
    L33:
        r0 = r0 + 1;
        goto L12
    L17:
        if (r32 == Theme.key_actionBarDefaultSubmenuItem) goto L32;
        if (r32 == Theme.key_actionBarDefaultSubmenuItemIcon) goto L32;
        int r4 = Theme.key_dialogButtonSelector;
        if (r32 == r4) goto L32;
        if (r32 != Theme.key_sheet_scrollUp) goto L27;
        r7.put(r32, ColorUtils.blendARGB(r8, r1, 0.5f));
        goto L33
    L27:
        if (r32 == Theme.key_actionBarWhiteSelector) goto L31;
        if (r32 == r4) goto L31;
        r7.put(r32, r1);
    L31:
        r7.put(r32, r2);
        goto L33
    L41:
        return;
    L10:
        r1 = -16777216;
        goto L11
    }

    public int getColor(int r3) {
        return ColorUtils.blendARGB(this.fromColors.get(r3), this.toColors.get(r3), this.progress);
    }

    public void setFrom(int r2, Theme.ResourcesProvider r3) {
        updateColors(this.fromColors, r2, r3);
    }

    public void setTo(int r2, Theme.ResourcesProvider r3) {
        updateColors(this.toColors, r2, r3);
    }

    public void updateActionBar(ActionBar r5, float r6) {
        this.progress = r6;
        int r62 = Theme.key_windowBackgroundWhiteBlackText;
        r5.setTitleColor(getColor(r62));
        r5.setSubtitleColor(Theme.multAlpha(getColor(r62), 0.45f));
        r5.setItemsColor(getColor(r62), false);
        ImageView r0 = r5.backButtonImageView;
        if (r0 == null) goto L5;
        r0.setColorFilter(new PorterDuffColorFilter(getColor(r62), PorterDuff.Mode.SRC_IN));
    L5:
        r5.setItemsBackgroundColor(getColor(Theme.key_actionBarWhiteSelector), false);
    }
}
