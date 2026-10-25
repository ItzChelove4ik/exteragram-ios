package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class BotSwitchCell extends FrameLayout {
    private TextView textView;

    public BotSwitchCell(Context r10) {
        super(r10);
        TextView r0 = new TextView(r10);
        this.textView = r0;
        r0.setTextSize(1, 15.0f);
        this.textView.setTextColor(Theme.getColor(Theme.key_chat_botSwitchToInlineText));
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setMaxLines(1);
        TextView r102 = this.textView;
        int r1 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r02 = 5;
    L6:
        r102.setGravity(r02);
        TextView r103 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        r1 = 5;
    L9:
        addView(r103, LayoutHelper.createFrame(-2, -2.0f, r1 | 16, 14.0f, 0.0f, 14.0f, 0.0f));
        return;
    L5:
        r02 = 3;
        goto L6
    }

    public TextView getTextView() {
        return this.textView;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
    }

    public void setText(String r1) {
        this.textView.setText(r1);
    }
}
