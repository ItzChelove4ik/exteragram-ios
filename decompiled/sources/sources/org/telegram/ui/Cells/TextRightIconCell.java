package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextRightIconCell extends FrameLayout {
    private final ImageView ivIcon;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SimpleTextView textView;

    public TextRightIconCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.resourcesProvider = r11;
        SimpleTextView r0 = new SimpleTextView(r10);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r11));
        r0.setTextSize(16);
        r0.setGravity(19);
        addView(r0, LayoutHelper.createFrame(-1, 48.0f, 16, 22.0f, 0.0f, 56.0f, 0.0f));
        ImageView r02 = new ImageView(r10);
        this.ivIcon = r02;
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText5, r11), PorterDuff.Mode.SRC_IN));
        addView(r02, LayoutHelper.createFrame(24, 24.0f, 8388629, 0.0f, 0.0f, 16.0f, 0.0f));
        setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_dialogBackground));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (this.needDivider == false) goto L12;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        Paint r02 = r0.getPaint("paintDivider");
    L8:
        if (r02 != null) goto L10;
        r02 = Theme.dividerPaint;
    L10:
        r8.drawLine(AndroidUtilities.dp(22.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, r02);
        return;
    L7:
        r02 = null;
        goto L8
    }

    public void setDivider(boolean r1) {
        this.needDivider = r1;
    }

    public void setTextAndIcon(CharSequence r2, int r3) {
        this.textView.setText(r2);
        this.ivIcon.setImageResource(r3);
    }
}
