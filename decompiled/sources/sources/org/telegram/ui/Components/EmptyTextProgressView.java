package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class EmptyTextProgressView extends FrameLayout {
    private boolean inLayout;
    private RLottieImageView lottieImageView;
    private View progressView;
    private final Theme.ResourcesProvider resourcesProvider;
    private int showAtPos;
    private TextView textView;
    private LinearLayout textViewLayout;

    /* renamed from: $r8$lambda$Vq18KDUqpLFj2-u41GI6no-lj-8, reason: not valid java name */
    public static /* synthetic */ boolean m10574$r8$lambda$Vq18KDUqpLFj2u41GI6nolj8(View r0, MotionEvent r1) {
        return true;
    }

    public EmptyTextProgressView(Context r2) {
        this(r2, null, null);
    }

    public EmptyTextProgressView(Context r18, View r19, Theme.ResourcesProvider r20) {
        View r2 = r19;
        super(r18);
        this.resourcesProvider = r20;
        if (r2 != null) goto L5;
        r2 = new RadialProgressView(r18);
        addView(r2, LayoutHelper.createFrame(-2, -2.0f));
    L6:
        this.progressView = r2;
        LinearLayout r5 = new LinearLayout(r18);
        this.textViewLayout = r5;
        r5.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.textViewLayout.setGravity(1);
        this.textViewLayout.setClipChildren(false);
        this.textViewLayout.setClipToPadding(false);
        this.textViewLayout.setOrientation(1);
        RLottieImageView r52 = new RLottieImageView(r18);
        this.lottieImageView = r52;
        r52.setScaleType(ImageView.ScaleType.FIT_XY);
        this.lottieImageView.setImportantForAccessibility(2);
        this.lottieImageView.setVisibility(8);
        this.textViewLayout.addView(this.lottieImageView, LayoutHelper.createLinear(150, 150, 17, 0, 0, 0, 20));
        TextView r53 = new TextView(r18);
        this.textView = r53;
        r53.setTextSize(1, 20.0f);
        this.textView.setTextColor(getThemedColor(Theme.key_emptyListPlaceholder));
        this.textView.setGravity(1);
        this.textView.setText(LocaleController.getString(R.string.NoResult));
        this.textViewLayout.addView(this.textView, LayoutHelper.createLinear(-2, -2, 17));
        addView(this.textViewLayout, LayoutHelper.createFrame(-2, -2.0f));
        AndroidUtilities.updateViewVisibilityAnimated(this.textView, false, 2.0f, false);
        AndroidUtilities.updateViewVisibilityAnimated(r2, false, 1.0f, false);
        setOnTouchListener(new EmptyTextProgressView$$ExternalSyntheticLambda0());
        return;
    L5:
        addView(r2, LayoutHelper.createFrame(-1, -1.0f));
        goto L6
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
        this.inLayout = true;
        int r102 = r10 - r8;
        int r112 = r11 - r9;
        int r82 = getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r82) goto L21;
        View r1 = getChildAt(r0);
        if (r1.getVisibility() == 8) goto L20;
        int r2 = (r102 - r1.getMeasuredWidth()) / 2;
        View r4 = this.progressView;
        if (r1 == r4) goto L10;
    L13:
        int r42 = this.showAtPos;
        if (r42 != 2) goto L16;
        int r43 = (AndroidUtilities.dp(100.0f) - r1.getMeasuredHeight()) / 2;
        int r3 = getPaddingTop();
    L12:
        int r44 = r43 + r3;
        r1.layout(r2, r44, r1.getMeasuredWidth() + r2, r1.getMeasuredHeight() + r44);
        goto L20
    L16:
        if (r42 != 1) goto L18;
        r43 = ((r112 / 2) - r1.getMeasuredHeight()) / 2;
        r3 = getPaddingTop();
        goto L12
    L18:
        r43 = (r112 - r1.getMeasuredHeight()) / 2;
        r3 = getPaddingTop();
        goto L12
    L10:
        if ((r4 instanceof FlickerLoadingView) == false) goto L13;
        r43 = (r112 - r1.getMeasuredHeight()) / 2;
        r3 = getPaddingTop();
    L20:
        r0 = r0 + 1;
        goto L3
    L21:
        this.inLayout = false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.inLayout == true) goto L6;
        super.requestLayout();
        return;
    }

    public void setLottie(int r3, int r4, int r5) {
        RLottieImageView r0 = this.lottieImageView;
        if (r3 == 0) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        if (r3 == 0) goto L10;
        this.lottieImageView.setAnimation(r3, r4, r5);
        this.lottieImageView.playAnimation();
        return;
    L10:
        return;
    L5:
        r1 = 8;
        goto L6
    }

    public void setProgressBarColor(int r2) {
        View r1 = this.progressView;
        if ((r1 instanceof RadialProgressView) == false) goto L6;
        ((RadialProgressView) r1).setProgressColor(r2);
        return;
    }

    public void setShowAtCenter(boolean r1) {
        this.showAtPos = r1 ? 1 : 0;
    }

    public void setShowAtTop(boolean r1) {
        if (r1 == false) goto L4;
        int r12 = 2;
    L5:
        this.showAtPos = r12;
        return;
    L4:
        r12 = 0;
        goto L5
    }

    public void setText(String r1) {
        this.textView.setText(r1);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setTextSize(int r2) {
        this.textView.setTextSize(1, r2);
    }

    public void setTopImage(int r5) {
        if (r5 != 0) goto L6;
        this.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        return;
    L6:
        Drawable r52 = getContext().getResources().getDrawable(r5).mutate();
        if (r52 == null) goto L9;
        r52.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_emptyListPlaceholder), PorterDuff.Mode.MULTIPLY));
    L9:
        this.textView.setCompoundDrawablesWithIntrinsicBounds(null, r52, null, null);
        this.textView.setCompoundDrawablePadding(AndroidUtilities.dp(1.0f));
    }

    public void showProgress() {
        showProgress(true);
    }

    public void showProgress(boolean r4) {
        AndroidUtilities.updateViewVisibilityAnimated(this.textView, false, 0.9f, r4);
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, true, 1.0f, r4);
    }

    public void showTextView() {
        AndroidUtilities.updateViewVisibilityAnimated(this.textView, true, 0.9f, true);
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 1.0f, true);
    }
}
