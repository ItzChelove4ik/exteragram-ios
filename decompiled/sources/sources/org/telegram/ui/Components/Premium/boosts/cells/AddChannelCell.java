package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CombinedDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class AddChannelCell extends FrameLayout {
    private final ImageView imageView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SimpleTextView textView;

    public AddChannelCell(Context r6, Theme.ResourcesProvider r7) {
        super(r6);
        this.resourcesProvider = r7;
        SimpleTextView r0 = new SimpleTextView(r6);
        this.textView = r0;
        r0.setTextSize(16);
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 5;
    L6:
        r0.setGravity(r1);
        int r12 = Theme.key_windowBackgroundWhiteBlueHeader;
        r0.setTextColor(Theme.getColor(r12, r7));
        r0.setTag(Integer.valueOf(r12));
        addView(r0);
        ImageView r13 = new ImageView(r6);
        this.imageView = r13;
        r13.setScaleType(ImageView.ScaleType.CENTER);
        addView(r13);
        r0.setText(LocaleController.getString(R.string.BoostingAddChannelOrGroup));
        Drawable r62 = getResources().getDrawable(R.drawable.poll_add_circle);
        Drawable r02 = getResources().getDrawable(R.drawable.poll_add_plus);
        int r3 = Theme.getColor(Theme.key_switchTrackChecked, r7);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r62.setColorFilter(new PorterDuffColorFilter(r3, r4));
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck, r7), r4));
        r13.setImageDrawable(new CombinedDrawable(r62, r02));
        setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r7));
        return;
    L5:
        r1 = 3;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        int r52 = r5 - r3;
        int r62 = ((r6 - r4) - this.textView.getTextHeight()) / 2;
        float r32 = 23.0f;
        if (LocaleController.isRTL == false) goto L9;
        int r22 = getMeasuredWidth() - this.textView.getMeasuredWidth();
        if (this.imageView.getVisibility() != 0) goto L7;
        r32 = 68.0f;
    L7:
        int r23 = r22 - AndroidUtilities.dp(r32);
    L12:
        SimpleTextView r33 = this.textView;
        r33.layout(r23, r62, r33.getMeasuredWidth() + r23, this.textView.getMeasuredHeight() + r62);
        if (LocaleController.isRTL == true) goto L15;
        int r24 = AndroidUtilities.dp(24.0f);
    L16:
        ImageView r34 = this.imageView;
        r34.layout(r24, 0, r34.getMeasuredWidth() + r24, this.imageView.getMeasuredHeight());
        return;
    L15:
        r24 = (r52 - this.imageView.getMeasuredWidth()) - AndroidUtilities.dp(24.0f);
        goto L16
    L9:
        if (this.imageView.getVisibility() != 0) goto L11;
        r32 = 68.0f;
    L11:
        r23 = AndroidUtilities.dp(r32);
        goto L12
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r52 = View.MeasureSpec.getSize(r5);
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r52 - AndroidUtilities.dp(94.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.imageView.measure(View.MeasureSpec.makeMeasureSpec(r52, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        setMeasuredDimension(r52, AndroidUtilities.dp(50.0f));
    }
}
