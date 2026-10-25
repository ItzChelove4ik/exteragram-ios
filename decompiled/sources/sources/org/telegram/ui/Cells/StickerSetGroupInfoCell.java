package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class StickerSetGroupInfoCell extends LinearLayout {
    private TextView addButton;
    private boolean isLast;

    public StickerSetGroupInfoCell(Context r12) {
        super(r12);
        setOrientation(1);
        TextView r1 = new TextView(r12);
        r1.setTextColor(Theme.getColor(Theme.key_chat_emojiPanelTrendingDescription));
        r1.setTextSize(1, 14.0f);
        r1.setText(LocaleController.getString(R.string.GroupStickersInfo));
        addView(r1, LayoutHelper.createLinear(-1, -2, 51, 17, 4, 17, 0));
        TextView r13 = new TextView(r12);
        this.addButton = r13;
        r13.setPadding(AndroidUtilities.dp(17.0f), 0, AndroidUtilities.dp(17.0f), 0);
        this.addButton.setGravity(17);
        this.addButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.addButton.setTextSize(1, 14.0f);
        this.addButton.setTypeface(AndroidUtilities.bold());
        this.addButton.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
        this.addButton.setText(LocaleController.getString(R.string.ChooseStickerSet).toUpperCase());
        addView(this.addButton, LayoutHelper.createLinear(-2, 28, 51, 17, 10, 14, 8));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        if (this.isLast == false) goto L10;
        View r22 = (View) getParent();
        if (r22 == null) goto L11;
        int r32 = ((r22.getMeasuredHeight() - r22.getPaddingBottom()) - r22.getPaddingTop()) - AndroidUtilities.dp(24.0f);
        if (getMeasuredHeight() >= r32) goto L12;
        setMeasuredDimension(getMeasuredWidth(), r32);
        return;
    L12:
        return;
    L11:
        return;
    }

    public void setAddOnClickListener(View.OnClickListener r1) {
        this.addButton.setOnClickListener(r1);
    }

    public void setIsLast(boolean r1) {
        this.isLast = r1;
        requestLayout();
    }
}
