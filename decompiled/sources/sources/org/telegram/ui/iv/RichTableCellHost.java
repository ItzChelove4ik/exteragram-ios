package org.telegram.ui.iv;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class RichTableCellHost extends FrameLayout {
    public TL_iv.pageTableCell cell;
    public final RichEditText editText;

    public RichTableCellHost(Context r3, Theme.ResourcesProvider r4) {
        super(r3);
        RichEditText r0 = new RichEditText(r3, r4);
        this.editText = r0;
        r0.setTextSize(1, Math.max(8, SharedConfig.fontSize - 2));
        r0.setAllowNewlines(true);
        setCompact(false);
        addView(r0, LayoutHelper.createFrame(-1, -2, 51));
    }

    private void applyAlignment() {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.editText.getLayoutParams();
        TL_iv.pageTableCell r1 = this.cell;
        if (r1.align_right == false) goto L6;
        int r2 = 5;
    L10:
        if (r1.valign_middle == false) goto L13;
        int r12 = r2 | 16;
    L16:
        r0.gravity = r12;
        this.editText.setLayoutParams(r0);
        TL_iv.pageTableCell r02 = this.cell;
        if (r02.align_right == false) goto L20;
        int r03 = 53;
    L23:
        this.editText.setGravity(r03);
        this.editText.setTypeface(AndroidUtilities.regular());
        return;
    L20:
        if (r02.align_center == false) goto L22;
        r03 = 49;
        goto L23
    L22:
        r03 = 51;
        goto L23
    L13:
        if (r1.valign_bottom == false) goto L15;
        r12 = r2 | 80;
        goto L16
    L15:
        r12 = r2 | 48;
        goto L16
    L6:
        if (r1.align_center == false) goto L8;
        r2 = 1;
        goto L10
    L8:
        r2 = 3;
        goto L10
    }

    public void applyHeaderWithDefaultBold(boolean r6) {
        if (this.cell != null) goto L5;
        return;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.editText.getText());
        if (r0.length() > 0) goto L8;
    L10:
        boolean r1 = false;
    L11:
        TableModel.setHeader(this.cell, r6);
        if (r6 == true) goto L14;
        if (r1 == false) goto L18;
        RichTextStyle.setStyle(r0, 0, r0.length(), 1, false);
    L18:
        TableModel.applyStyledText(this.cell, r0);
        bind(this.cell);
        return;
    L14:
        if (r0.length() <= 0) goto L18;
        RichTextStyle.setStyle(r0, 0, r0.length(), 1, true);
        goto L18
    L8:
        if ((RichTextStyle.stylesFullyCovering(r0, 0, r0.length()) & 1) == 0) goto L10;
        r1 = true;
        goto L11
    }

    public void bind(TL_iv.pageTableCell r4) {
        this.cell = r4;
        applyAlignment();
        CharSequence r0 = TableModel.readStyledText(r4);
        if (r4.header == false) goto L9;
        boolean r2 = true;
        if (r0.length() != 0) goto L7;
    L10:
        this.editText.setAutoBold(r2);
        this.editText.setTextSilently(Emoji.replaceEmoji(r0, this.editText.getPaint().getFontMetricsInt(), false));
        this.editText.invalidateEffects();
        return;
    L7:
        if ((RichTextStyle.stylesFullyCovering(r0, 0, r0.length()) & 1) != 0) goto L10;
    L9:
        r2 = false;
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r7) {
        if (this.editText.getVisibility() != 0) goto L24;
        if (r7.getActionMasked() == 3) goto L24;
        float r0 = r7.getX();
        float r1 = r7.getY();
        boolean r3 = false;
        if (r0 >= this.editText.getLeft()) goto L9;
    L11:
        boolean r2 = false;
    L13:
        if (r1 >= this.editText.getTop()) goto L15;
    L16:
        r3 = true;
    L17:
        if (r2 == false) goto L24;
        if (r3 == false) goto L24;
        if (this.editText.getHeight() <= 0) goto L24;
        float r12 = Math.max(0.0f, Math.min(r1 - this.editText.getTop(), this.editText.getHeight() - 1));
        MotionEvent r72 = MotionEvent.obtain(r7);
        r72.setLocation(r0 - this.editText.getLeft(), r12);
        boolean r6 = this.editText.onTouchEvent(r72);
        r72.recycle();
        return r6;
    L15:
        if (r1 < this.editText.getBottom()) goto L17;
    L9:
        if (r0 >= this.editText.getRight()) goto L11;
        r2 = true;
    L24:
        return super.dispatchTouchEvent(r7);
    }

    public void refreshFromCell() {
        if (this.cell != null) goto L5;
        return;
    L5:
        applyAlignment();
        invalidate();
    }

    public void setCompact(boolean r5) {
        RichEditText r0 = this.editText;
        if (r5 == false) goto L6;
        r0.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        this.editText.setMinHeight(AndroidUtilities.dp(18.0f));
        return;
    L6:
        r0.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(9.0f));
        this.editText.setMinHeight(AndroidUtilities.dp(36.0f));
    }

    public void setLocked(boolean r1) {
        this.editText.setLocked(r1);
    }
}
