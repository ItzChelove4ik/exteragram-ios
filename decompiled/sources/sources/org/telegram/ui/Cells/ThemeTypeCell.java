package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class ThemeTypeCell extends FrameLayout {
    private ImageView checkImage;
    private boolean needDivider;
    private TextView textView;

    public ThemeTypeCell(Context r13) {
        super(r13);
        setWillNotDraw(false);
        TextView r0 = new TextView(r13);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        TextView r02 = this.textView;
        int r2 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 5;
    L6:
        r02.setGravity(r1 | 16);
        TextView r03 = this.textView;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L9;
        int r4 = 5;
    L10:
        int r7 = r4 | 48;
        float r42 = 21.0f;
        if (r12 == false) goto L13;
        float r8 = 71.0f;
    L14:
        if (r12 == true) goto L17;
        r42 = 23.0f;
    L17:
        addView(r03, LayoutHelper.createFrame(-1, -1.0f, r7, r8, 0.0f, r42, 0.0f));
        ImageView r04 = new ImageView(r13);
        this.checkImage = r04;
        r04.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addedIcon), PorterDuff.Mode.MULTIPLY));
        this.checkImage.setImageResource(R.drawable.sticker_added);
        ImageView r132 = this.checkImage;
        if (LocaleController.isRTL == true) goto L21;
        r2 = 5;
    L21:
        addView(r132, LayoutHelper.createFrame(19, 14.0f, r2 | 16, 23.0f, 0.0f, 23.0f, 0.0f));
        return;
    L13:
        r8 = 21.0f;
        goto L14
    L9:
        r4 = 3;
        goto L10
    L5:
        r1 = 3;
        goto L6
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(20.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(20.0f);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void setTypeChecked(boolean r1) {
        ImageView r0 = this.checkImage;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 4;
        goto L6
    }

    public void setValue(String r2, boolean r3, boolean r4) {
        this.textView.setText(r2);
        ImageView r22 = this.checkImage;
        if (r3 == false) goto L5;
        int r32 = 0;
    L6:
        r22.setVisibility(r32);
        this.needDivider = r4;
        return;
    L5:
        r32 = 4;
        goto L6
    }
}
