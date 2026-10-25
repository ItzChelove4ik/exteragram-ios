package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class AnimatedAvatarContainer extends FrameLayout {
    private int leftPadding;
    boolean occupyStatusBar;
    AnimatedTextView subtitleTextView;
    AnimatedTextView titleTextView;

    public AnimatedAvatarContainer(Context r17) {
        super(r17);
        this.occupyStatusBar = true;
        this.leftPadding = AndroidUtilities.dp(8.0f);
        AnimatedTextView r3 = new AnimatedTextView(r17, true, true, true);
        this.titleTextView = r3;
        int r4 = Theme.key_actionBarDefaultTitle;
        r3.setTextColor(Theme.getColor(r4));
        this.titleTextView.setTextSize(AndroidUtilities.dp(18.0f));
        this.titleTextView.setGravity(3);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.titleTextView.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(12.0f));
        addView(this.titleTextView);
        AnimatedTextView r32 = new AnimatedTextView(r17, true, true, true);
        this.subtitleTextView = r32;
        r32.setTag(Integer.valueOf(Theme.key_actionBarDefaultSubtitle));
        this.subtitleTextView.setTextColor(Theme.getColor(r4));
        this.subtitleTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.subtitleTextView.setGravity(3);
        this.subtitleTextView.setPadding(0, 0, AndroidUtilities.dp(10.0f), 0);
        addView(this.subtitleTextView);
        this.titleTextView.getDrawable().setAllowCancel(true);
        this.subtitleTextView.getDrawable().setAllowCancel(true);
        AnimatedTextView r9 = this.titleTextView;
        CubicBezierInterpolator r15 = CubicBezierInterpolator.DEFAULT;
        r9.setAnimationProperties(1.0f, 0, 150, r15);
        this.subtitleTextView.setAnimationProperties(1.0f, 0, 150, r15);
        setClipChildren(false);
    }

    public AnimatedTextView getSubtitleTextView() {
        return this.subtitleTextView;
    }

    public AnimatedTextView getTitle() {
        return this.titleTextView;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        int r32 = (ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(42.0f)) / 2;
        if (this.occupyStatusBar == false) goto L5;
        int r42 = AndroidUtilities.statusBarHeight;
    L6:
        int r33 = r32 + r42;
        int r43 = this.leftPadding;
        int r52 = this.subtitleTextView.getVisibility();
        AnimatedTextView r62 = this.titleTextView;
        if (r52 == 8) goto L9;
        r62.layout(r43, (AndroidUtilities.dp(1.0f) + r33) - this.titleTextView.getPaddingTop(), this.titleTextView.getMeasuredWidth() + r43, (((this.titleTextView.getTextHeight() + r33) + AndroidUtilities.dp(1.3f)) - this.titleTextView.getPaddingTop()) + this.titleTextView.getPaddingBottom());
    L10:
        this.subtitleTextView.layout(r43, AndroidUtilities.dp(20.0f) + r33, this.subtitleTextView.getMeasuredWidth() + r43, (r33 + this.subtitleTextView.getTextHeight()) + AndroidUtilities.dp(24.0f));
        return;
    L9:
        r62.layout(r43, (AndroidUtilities.dp(11.0f) + r33) - this.titleTextView.getPaddingTop(), this.titleTextView.getMeasuredWidth() + r43, (((this.titleTextView.getTextHeight() + r33) + AndroidUtilities.dp(11.0f)) - this.titleTextView.getPaddingTop()) + this.titleTextView.getPaddingBottom());
        goto L10
    L5:
        r42 = 0;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r72 = View.MeasureSpec.getSize(r7) + this.titleTextView.getPaddingRight();
        int r0 = r72 - AndroidUtilities.dp(16.0f);
        this.titleTextView.measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f) + this.titleTextView.getPaddingRight(), Integer.MIN_VALUE));
        this.subtitleTextView.measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), Integer.MIN_VALUE));
        setMeasuredDimension(r72, View.MeasureSpec.getSize(r8));
    }
}
