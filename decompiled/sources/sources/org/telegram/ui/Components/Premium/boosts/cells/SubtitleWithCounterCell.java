package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SubtitleWithCounterCell extends org.telegram.ui.Cells.HeaderCell {
    private final AnimatedTextView counterTextView;

    public SubtitleWithCounterCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10, r11);
        AnimatedTextView r0 = new AnimatedTextView(r10, true, true, true);
        this.counterTextView = r0;
        r0.setAnimationProperties(0.45f, 0, 240, CubicBezierInterpolator.EASE_OUT_QUINT);
        int r1 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r102 = 3;
    L6:
        r0.setGravity(r102);
        r0.setTextSize(AndroidUtilities.dp(15.0f));
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r11));
        if (LocaleController.isRTL == false) goto L9;
        r1 = 3;
    L9:
        addView(r0, LayoutHelper.createFrame(-2, 24.0f, r1 | 80, 24.0f, 0.0f, 24.0f, 0.0f));
        setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r11));
        return;
    L5:
        r102 = 5;
        goto L6
    }

    public void updateCounter(boolean r3, int r4) {
        if (r4 > 0) goto L4;
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        this.counterTextView.cancelAnimation();
        this.counterTextView.setText(r42, r3);
        return;
    L4:
        r42 = LocaleController.formatPluralString("BoostingBoostsCountTitle", r4, new Object[]{Integer.valueOf(r4)});
        goto L5
    }
}
