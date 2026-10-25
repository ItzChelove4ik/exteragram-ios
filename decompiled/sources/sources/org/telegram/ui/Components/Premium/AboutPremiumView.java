package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class AboutPremiumView extends LinearLayout {
    public AboutPremiumView(Context r14) {
        super(r14);
        setOrientation(1);
        setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        TextView r1 = new TextView(r14);
        r1.setTextSize(1, 14.0f);
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r1.setTextColor(Theme.getColor(r3));
        r1.setTypeface(AndroidUtilities.bold());
        r1.setText(LocaleController.getString(R.string.AboutPremiumTitle));
        addView(r1);
        TextView r12 = new TextView(r14);
        r12.setTextSize(1, 14.0f);
        r12.setTextColor(Theme.getColor(r3));
        r12.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AboutPremiumDescription)));
        addView(r12, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 0, 0, 0));
        TextView r13 = new TextView(r14);
        r13.setTextSize(1, 14.0f);
        r13.setTextColor(Theme.getColor(r3));
        r13.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AboutPremiumDescription2)));
        addView(r13, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 24, 0, 0));
    }
}
