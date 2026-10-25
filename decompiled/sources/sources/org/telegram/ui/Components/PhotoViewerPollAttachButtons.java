package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public class PhotoViewerPollAttachButtons extends LinearLayout {
    public final View editButton;
    public final View replaceButton;

    public PhotoViewerPollAttachButtons(Context r4) {
        super(r4);
        setOrientation(0);
        setGravity(17);
        setPadding(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        View r42 = createButton(R.drawable.msg_replace, LocaleController.getString(R.string.ReplaceAttachedPollMedia));
        this.replaceButton = r42;
        addView(r42, LayoutHelper.createLinear(-2, -1));
        View r43 = createButton(R.drawable.media_button_restore, LocaleController.getString(R.string.Edit));
        this.editButton = r43;
        addView(r43, LayoutHelper.createLinear(-2, -1));
    }

    private View createButton(int r9, String r10) {
        Context r8 = getContext();
        LinearLayout r0 = new LinearLayout(r8);
        r0.setOrientation(0);
        r0.setGravity(17);
        r0.setPadding(AndroidUtilities.dp(25.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(25.0f), AndroidUtilities.dp(7.0f));
        ImageView r1 = new ImageView(r8);
        r1.setImageResource(r9);
        r0.addView(r1, LayoutHelper.createLinear(24, 24, 0.0f, 0.0f, 8.0f, 0.0f));
        TextView r92 = new TextView(r8);
        r92.setGravity(16);
        r92.setText(r10);
        r92.setTextSize(2, 14.0f);
        r92.setSingleLine(true);
        r92.setTextColor(-1);
        r0.addView(r92, LayoutHelper.createLinear(-2, -2));
        ScaleStateListAnimator.apply(r0);
        return r0;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        View r0 = this.editButton;
        View r1 = this.replaceButton;
        ViewGroup.LayoutParams r2 = r0.getLayoutParams();
        ViewGroup.LayoutParams r3 = r0.getLayoutParams();
        int r4 = View.MeasureSpec.getSize(r10);
        int r5 = View.MeasureSpec.getSize(r11);
        int r6 = getPaddingLeft() + getPaddingRight();
        int r7 = getPaddingTop() + getPaddingBottom();
        int r42 = Math.max(0, r4 - r6);
        int r52 = View.MeasureSpec.makeMeasureSpec(Math.max(0, r5 - r7), TLObject.FLAG_30);
        int r62 = View.MeasureSpec.makeMeasureSpec(r42, Integer.MIN_VALUE);
        r0.measure(r62, r52);
        r1.measure(r62, r52);
        int r02 = Math.min(Math.max(r0.getMeasuredWidth(), r1.getMeasuredWidth()), r42 / 2);
        r3.width = r02;
        r2.width = r02;
        super.onMeasure(r10, r11);
    }
}
