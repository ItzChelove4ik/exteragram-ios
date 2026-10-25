package org.telegram.ui.Components.emojiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.core.graphics.ColorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class FoundStickerPackButton extends ButtonWithCounterView implements FactorAnimator.Target {
    private final BoolAnimator animatorIsPrimary;
    private final Theme.ResourcesProvider resourcesProvider;

    public FoundStickerPackButton(Context r8, Theme.ResourcesProvider r9) {
        super(r8, r9);
        this.animatorIsPrimary = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 320, true);
        this.resourcesProvider = r9;
        setRound();
        setOutlineProvider(ViewOutlineProviderImpl.BOUNDS_ROUND_RECT);
    }

    private void checkUi_colors() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        boolean r02 = r0.isDark();
    L6:
        float r1 = this.animatorIsPrimary.getFloatValue();
        setElevation(AndroidUtilities.dp(1.0f) * (1.0f - r1));
        setColor(ColorUtils.blendARGB(getThemedColor(Theme.key_windowBackgroundWhite), getThemedColor(Theme.key_featuredStickers_addButton), r1));
        setTextColor(ColorUtils.blendARGB(getThemedColor(Theme.key_text_RedBold), getThemedColor(Theme.key_featuredStickers_buttonText), r1));
        if (Build.VERSION.SDK_INT < 28) goto L13;
        if (r02 == false) goto L11;
        setOutlineAmbientShadowColor(553648127);
        setOutlineSpotShadowColor(553648127);
        return;
    L11:
        setOutlineAmbientShadowColor(1610612736);
        setOutlineSpotShadowColor(1610612736);
        return;
    L13:
        return;
    L5:
        r02 = Theme.isCurrentThemeDark();
        goto L6
    }

    private int getThemedColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.getColor(r1);
    L7:
        return Theme.getColor(r1);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        checkUi_colors();
    }

    public void setIsPrimary(boolean r1, boolean r2) {
        this.animatorIsPrimary.setValue(r1, r2);
    }
}
