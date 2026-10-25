package org.telegram.ui;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumButtonView;

/* loaded from: classes6.dex */
public class UnlockPremiumView extends FrameLayout {
    public final PremiumButtonView premiumButtonView;

    public UnlockPremiumView(Context r12, int r13, Theme.ResourcesProvider r14) {
        super(r12);
        LinearLayout r0 = new LinearLayout(r12);
        addView(r0, LayoutHelper.createFrame(-1, -2, 80));
        r0.setOrientation(1);
        TextView r2 = new TextView(r12);
        r2.setTextColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r14), 100));
        r2.setTextSize(1, 13.0f);
        r2.setGravity(17);
        if (r13 != 0) goto L5;
        r2.setText(LocaleController.getString(R.string.UnlockPremiumStickersDescription));
    L7:
        r0.addView(r2, LayoutHelper.createLinear(-1, -2, 0, 16, 17, 17, 16));
        PremiumButtonView r22 = new PremiumButtonView(r12, false, r14);
        this.premiumButtonView = r22;
        if (r13 != 0) goto L10;
        String r11 = LocaleController.getString(R.string.UnlockPremiumStickers);
    L11:
        SpannableStringBuilder r132 = new SpannableStringBuilder();
        r132.append("d ").setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r12, R.drawable.msg_premium_normal)), 0, 1, 0);
        r132.append(r11);
        r22.buttonTextView.setText(r132);
        r0.addView(r22, LayoutHelper.createLinear(-1, 48, 0, 16, 0, 16, 16));
        return;
    L10:
        r11 = LocaleController.getString(R.string.UnlockPremiumReactions);
        goto L11
    L5:
        if (r13 != 1) goto L7;
        r2.setText(LocaleController.getString(R.string.UnlockPremiumReactionsDescription));
        goto L7
    }
}
