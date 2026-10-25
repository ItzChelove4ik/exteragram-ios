package org.telegram.ui.Components;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PickerBottomLayoutViewer extends FrameLayout {
    public TextView cancelButton;
    public TextView doneButton;
    public TextView doneButtonBadgeTextView;
    private boolean isDarkTheme;

    public PickerBottomLayoutViewer(Context r2) {
        this(r2, true);
    }

    public PickerBottomLayoutViewer(Context r12, boolean r13) {
        super(r12);
        this.isDarkTheme = r13;
        if (r13 == false) goto L5;
        int r132 = -15066598;
    L6:
        setBackgroundColor(r132);
        TextView r133 = new TextView(r12);
        this.cancelButton = r133;
        r133.setTextSize(1, 14.0f);
        TextView r134 = this.cancelButton;
        int r4 = -15095832;
        if (this.isDarkTheme == false) goto L9;
        int r3 = -1;
    L10:
        r134.setTextColor(r3);
        this.cancelButton.setGravity(17);
        TextView r135 = this.cancelButton;
        int r6 = 788529152;
        if (this.isDarkTheme == false) goto L13;
        int r5 = -12763843;
    L14:
        r135.setBackground(Theme.createSelectorDrawable(r5, 0));
        this.cancelButton.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.cancelButton.setText(LocaleController.getString(R.string.Cancel).toUpperCase());
        this.cancelButton.setTypeface(AndroidUtilities.bold());
        addView(this.cancelButton, LayoutHelper.createFrame(-2, -1, 51));
        TextView r136 = new TextView(r12);
        this.doneButton = r136;
        r136.setTextSize(1, 14.0f);
        TextView r137 = this.doneButton;
        if (this.isDarkTheme == false) goto L17;
        r4 = -1;
    L17:
        r137.setTextColor(r4);
        this.doneButton.setGravity(17);
        TextView r138 = this.doneButton;
        if (this.isDarkTheme == false) goto L20;
        r6 = -12763843;
    L20:
        r138.setBackgroundDrawable(Theme.createSelectorDrawable(r6, 0));
        this.doneButton.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.doneButton.setText(LocaleController.getString(R.string.Send).toUpperCase());
        this.doneButton.setTypeface(AndroidUtilities.bold());
        addView(this.doneButton, LayoutHelper.createFrame(-2, -1, 53));
        TextView r139 = new TextView(r12);
        this.doneButtonBadgeTextView = r139;
        r139.setTypeface(AndroidUtilities.bold());
        this.doneButtonBadgeTextView.setTextSize(1, 13.0f);
        this.doneButtonBadgeTextView.setTextColor(-1);
        this.doneButtonBadgeTextView.setGravity(17);
        TextView r122 = this.doneButtonBadgeTextView;
        if (this.isDarkTheme == false) goto L23;
        int r1310 = R.drawable.photobadge;
    L24:
        r122.setBackgroundResource(r1310);
        this.doneButtonBadgeTextView.setMinWidth(AndroidUtilities.dp(23.0f));
        this.doneButtonBadgeTextView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.0f));
        addView(this.doneButtonBadgeTextView, LayoutHelper.createFrame(-2, 23.0f, 53, 0.0f, 0.0f, 7.0f, 0.0f));
        return;
    L23:
        r1310 = R.drawable.bluecounter;
        goto L24
    L13:
        r5 = 788529152;
        goto L14
    L9:
        r3 = -15095832;
        goto L10
    L5:
        r132 = -1;
        goto L6
    }

    public void updateSelectedCount(int r5, boolean r6) {
        TextView r0 = this.doneButtonBadgeTextView;
        int r1 = -15095832;
        if (r5 != 0) goto L13;
        r0.setVisibility(8);
        TextView r52 = this.doneButton;
        if (r6 == false) goto L9;
        r52.setTextColor(-6710887);
        this.doneButton.setEnabled(false);
        return;
    L9:
        if (this.isDarkTheme == false) goto L11;
        r1 = -1;
    L11:
        r52.setTextColor(r1);
        return;
    L13:
        r0.setVisibility(0);
        this.doneButtonBadgeTextView.setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r5)}));
        TextView r53 = this.doneButton;
        if (this.isDarkTheme == false) goto L16;
        r1 = -1;
    L16:
        r53.setTextColor(r1);
        if (r6 == false) goto L20;
        this.doneButton.setEnabled(true);
        return;
    }
}
