package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class ChatUnreadCell extends FrameLayout {
    private final Theme.ResourcesProvider resourcesProvider;
    private final SimpleTextView textView;

    public ChatUnreadCell(Context r8, Theme.ResourcesProvider r9) {
        super(r8);
        this.resourcesProvider = r9;
        SimpleTextView r92 = new SimpleTextView(r8);
        this.textView = r92;
        r92.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(50.0f), getColor(Theme.key_chat_unreadMessagesStartBackground)));
        if (LocaleController.isRTL == false) goto L5;
        r92.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    L5:
        r92.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f));
        r92.setTextSize(14);
        r92.setGravity(17);
        r92.setTextColor(getColor(Theme.key_chat_unreadMessagesStartText));
        r92.setTypeface(AndroidUtilities.bold());
        addView(r92, LayoutHelper.createFrame(-2, -2.0f, 17, 32.0f, 0.0f, 32.0f, 0.0f));
    }

    private int getColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Integer r02 = Integer.valueOf(r0.getColor(r1));
    L6:
        if (r02 == null) goto L10;
        return r02.intValue();
    L10:
        return Theme.getColor(r1);
    L5:
        r02 = null;
        goto L6
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(46.0f), TLObject.FLAG_30));
    }

    public void setText(String r1) {
        this.textView.setText(r1);
    }
}
