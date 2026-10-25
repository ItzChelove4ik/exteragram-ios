package org.telegram.ui.Cells;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class LetterSectionCell extends FrameLayout {
    private TextView textView;

    public LetterSectionCell(Context r8) {
        super(r8);
        setLayoutParams(new ViewGroup.LayoutParams(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(64.0f)));
        TextView r82 = new TextView(getContext());
        this.textView = r82;
        r82.setTextSize(1, 22.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        this.textView.setGravity(17);
        addView(this.textView, LayoutHelper.createFrame(-1, -1.0f, 119, 12.0f, 0.0f, 0.0f, 0.0f));
    }

    public void setCellHeight(int r3) {
        setLayoutParams(new ViewGroup.LayoutParams(AndroidUtilities.dp(54.0f), r3));
    }

    public void setLetter(String r1) {
        this.textView.setText(r1.toUpperCase());
    }
}
