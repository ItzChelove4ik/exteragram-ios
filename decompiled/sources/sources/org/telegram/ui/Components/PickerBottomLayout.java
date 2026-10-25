package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PickerBottomLayout extends FrameLayout {
    public TextView cancelButton;
    public LinearLayout doneButton;
    public TextView doneButtonBadgeTextView;
    public TextView doneButtonTextView;
    private Theme.ResourcesProvider resourcesProvider;

    public PickerBottomLayout(Context r2, boolean r3) {
        this(r2, r3, null);
    }

    public PickerBottomLayout(Context r21, boolean r22, Theme.ResourcesProvider r23) {
        super(r21);
        this.resourcesProvider = r23;
        if (r22 == false) goto L5;
        int r3 = Theme.key_dialogBackground;
    L6:
        setBackgroundColor(Theme.getColor(r3, r23));
        TextView r32 = new TextView(r21);
        this.cancelButton = r32;
        r32.setTextSize(1, 14.0f);
        TextView r33 = this.cancelButton;
        int r6 = Theme.key_picker_enabledButton;
        r33.setTextColor(Theme.getColor(r6, r23));
        this.cancelButton.setGravity(17);
        this.cancelButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(r6, r23) & 268435455, 0));
        this.cancelButton.setPadding(AndroidUtilities.dp(33.0f), 0, AndroidUtilities.dp(33.0f), 0);
        this.cancelButton.setText(LocaleController.getString(R.string.Cancel).toUpperCase());
        this.cancelButton.setTypeface(AndroidUtilities.bold());
        addView(this.cancelButton, LayoutHelper.createFrame(-2, -1, 51));
        LinearLayout r34 = new LinearLayout(r21);
        this.doneButton = r34;
        r34.setOrientation(0);
        this.doneButton.setBackground(Theme.createSelectorDrawable(268435455 & Theme.getColor(r6, r23), 0));
        this.doneButton.setPadding(AndroidUtilities.dp(33.0f), 0, AndroidUtilities.dp(33.0f), 0);
        addView(this.doneButton, LayoutHelper.createFrame(-2, -1, 53));
        TextView r35 = new TextView(r21);
        this.doneButtonBadgeTextView = r35;
        r35.setTypeface(AndroidUtilities.bold());
        this.doneButtonBadgeTextView.setTextSize(1, 13.0f);
        this.doneButtonBadgeTextView.setTextColor(Theme.getColor(Theme.key_picker_badgeText, r23));
        this.doneButtonBadgeTextView.setGravity(17);
        ShapeDrawable r36 = Theme.createRoundRectDrawable(AndroidUtilities.dp(11.0f), Theme.getColor(Theme.key_picker_badge, r23));
        this.doneButtonBadgeTextView.setBackgroundDrawable(r36);
        this.doneButtonBadgeTextView.setMinWidth(AndroidUtilities.dp(23.0f));
        this.doneButtonBadgeTextView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.0f));
        this.doneButton.addView(this.doneButtonBadgeTextView, LayoutHelper.createLinear(-2, 23, 16, 0, 0, 10, 0));
        TextView r37 = new TextView(r21);
        this.doneButtonTextView = r37;
        r37.setTextSize(1, 14.0f);
        this.doneButtonTextView.setTextColor(Theme.getColor(r6, r23));
        this.doneButtonTextView.setGravity(17);
        this.doneButtonTextView.setCompoundDrawablePadding(AndroidUtilities.dp(8.0f));
        this.doneButtonTextView.setText(LocaleController.getString(R.string.Send).toUpperCase());
        this.doneButtonTextView.setTypeface(AndroidUtilities.bold());
        this.doneButton.addView(this.doneButtonTextView, LayoutHelper.createLinear(-2, -2, 16));
        return;
    L5:
        r3 = Theme.key_windowBackgroundWhite;
        goto L6
    }
}
