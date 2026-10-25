package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public class RecurrentPaymentsAcceptCell extends FrameLayout {
    private CheckBoxSquare checkBox;
    private LinkSpanDrawable.LinkCollector links;
    private TextView textView;

    public RecurrentPaymentsAcceptCell(Context r13, Theme.ResourcesProvider r14) {
        super(r13);
        CheckBoxSquare r0 = new CheckBoxSquare(r13, false);
        this.checkBox = r0;
        r0.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(false);
        CheckBoxSquare r02 = this.checkBox;
        int r3 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        addView(r02, LayoutHelper.createFrame(18, 18.0f, r2 | 16, 21.0f, 0.0f, 21.0f, 0.0f));
        LinkSpanDrawable.LinkCollector r22 = new LinkSpanDrawable.LinkCollector(this);
        this.links = r22;
        LinkSpanDrawable.LinksTextView r03 = new LinkSpanDrawable.LinksTextView(r13, r22, r14);
        this.textView = r03;
        r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r14));
        this.textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r14));
        this.textView.setTextSize(1, 15.0f);
        this.textView.setMaxLines(2);
        TextView r132 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r142 = 5;
    L10:
        r132.setGravity(r142 | 16);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r133 = this.textView;
        boolean r143 = LocaleController.isRTL;
        if (r143 == false) goto L13;
        r3 = 5;
    L13:
        int r6 = r3 | 48;
        if (r143 == false) goto L16;
        float r7 = 16.0f;
    L17:
        if (r143 == false) goto L19;
        float r9 = 58.0f;
    L20:
        addView(r133, LayoutHelper.createFrame(-1, -1.0f, r6, r7, 21.0f, r9, 21.0f));
        setWillNotDraw(false);
        return;
    L19:
        r9 = 16.0f;
        goto L20
    L16:
        r7 = 58.0f;
        goto L17
    L9:
        r142 = 3;
        goto L10
    L5:
        r2 = 3;
        goto L6
    }

    public CheckBoxSquare getCheckBox() {
        return this.checkBox;
    }

    public TextView getTextView() {
        return this.textView;
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        super.onDraw(r3);
        if (this.links == null) goto L9;
        r3.save();
        r3.translate(this.textView.getLeft(), this.textView.getTop());
        if (this.links.draw(r3) == false) goto L7;
        invalidate();
    L7:
        r3.restore();
        return;
    }

    public void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public void setText(CharSequence r1) {
        this.textView.setText(r1);
    }
}
