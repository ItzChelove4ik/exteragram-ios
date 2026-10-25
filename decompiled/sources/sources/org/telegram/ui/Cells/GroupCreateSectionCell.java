package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class GroupCreateSectionCell extends FrameLayout {
    private Drawable drawable;
    private TextView textView;

    public GroupCreateSectionCell(Context r10) {
        super(r10);
        setBackgroundColor(Theme.getColor(Theme.key_graySection));
        Drawable r102 = getResources().getDrawable(R.drawable.shadowdown);
        this.drawable = r102;
        r102.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_groupcreate_sectionShadow), PorterDuff.Mode.MULTIPLY));
        TextView r103 = new TextView(getContext());
        this.textView = r103;
        r103.setTextSize(1, 14.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setTextColor(Theme.getColor(Theme.key_groupcreate_sectionText));
        TextView r104 = this.textView;
        int r1 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r0 = 5;
    L6:
        r104.setGravity(r0 | 16);
        TextView r105 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        r1 = 5;
    L9:
        addView(r105, LayoutHelper.createFrame(-1, -1.0f, r1 | 48, 16.0f, 0.0f, 16.0f, 0.0f));
        return;
    L5:
        r0 = 3;
        goto L6
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        this.drawable.setBounds(0, getMeasuredHeight() - AndroidUtilities.dp(3.0f), getMeasuredWidth(), getMeasuredHeight());
        this.drawable.draw(r6);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40.0f), TLObject.FLAG_30));
    }

    public void setText(String r1) {
        this.textView.setText(r1);
    }
}
