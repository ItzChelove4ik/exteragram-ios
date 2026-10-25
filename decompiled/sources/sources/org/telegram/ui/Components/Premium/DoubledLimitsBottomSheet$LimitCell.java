package org.telegram.ui.Components.Premium;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
class DoubledLimitsBottomSheet$LimitCell extends LinearLayout {
    LimitPreviewView previewView;
    TextView subtitle;
    TextView title;

    public DoubledLimitsBottomSheet$LimitCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        setOrientation(1);
        setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
        TextView r1 = new TextView(r12);
        this.title = r1;
        r1.setTextSize(1, 15.0f);
        this.title.setTypeface(AndroidUtilities.bold());
        this.title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
        addView(this.title, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 16, 0, 16, 0));
        TextView r14 = new TextView(r12);
        this.subtitle = r14;
        r14.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r13));
        this.subtitle.setTextSize(1, 14.0f);
        addView(this.subtitle, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 16, 1, 16, 0));
        LimitPreviewView r2 = new LimitPreviewView(r12, 0, 10, 20, r13);
        this.previewView = r2;
        addView(r2, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 8, 0, 21));
    }

    @SuppressLint({"SetTextI18n"})
    public void setData(DoubledLimitsBottomSheet$Limit r4) {
        this.title.setText(r4.title);
        this.subtitle.setText(r4.subtitle);
        this.previewView.premiumCount.setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r4.premiumLimit)}));
        this.previewView.defaultCount.setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r4.defaultLimit)}));
    }
}
