package org.telegram.ui.Components.Premium.boosts.cells.selector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SelectorLetterCell extends FrameLayout {
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView;

    public SelectorLetterCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.resourcesProvider = r10;
        setBackgroundColor(getThemedColor(Theme.key_graySection));
        TextView r92 = new TextView(getContext());
        this.textView = r92;
        r92.setTextSize(1, 14.0f);
        r92.setTypeface(AndroidUtilities.bold());
        r92.setTextColor(getThemedColor(Theme.key_graySectionText));
        int r0 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r102 = 5;
    L6:
        r92.setGravity(r102 | 16);
        if (LocaleController.isRTL == false) goto L9;
        r0 = 5;
    L9:
        addView(r92, LayoutHelper.createFrame(-1, -1.0f, r0 | 48, 16.0f, 0.0f, 16.0f, 0.0f));
        return;
    L5:
        r102 = 3;
        goto L6
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
    }

    public void setLetter(CharSequence r1) {
        this.textView.setText(r1);
    }

    public void setTextColor(int r1) {
        int r12 = getThemedColor(r1);
        this.textView.setTextColor(r12);
    }
}
