package org.telegram.ui.Components.chat.buttons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProfileGooeyView$GPUImpl$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;

/* loaded from: classes7.dex */
public class ChatActivityBlurredRoundButton extends FrameLayout implements FactorAnimator.Target {
    private final BoolAnimator animatorIsEnabled;
    private final BoolAnimator animatorLoadingVisibility;
    private BlurredBackgroundDrawable backgroundDrawable;
    private float buttonScaleY;
    private int iconColor;
    private ImageView imageView;
    private CircularProgressDrawable loadingIndicatorDrawable;
    private ImageView loadingIndicatorView;
    private Theme.ResourcesProvider resourcesProvider;

    public ChatActivityBlurredRoundButton(Context r9) {
        super(r9);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorLoadingVisibility = new BoolAnimator(0, this, r3, 320);
        this.animatorIsEnabled = new BoolAnimator(1, this, r3, 320, true);
        this.buttonScaleY = 1.0f;
    }

    private void checkUi_IconViewVisibility() {
        float r0 = 1.0f - this.animatorLoadingVisibility.getFloatValue();
        float r2 = AndroidUtilities.lerp(r0 / 2.0f, r0, this.animatorIsEnabled.getFloatValue());
        ImageView r3 = this.imageView;
        if (r3 == null) goto L10;
        r3.setAlpha(r2);
        this.imageView.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        this.imageView.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r0) * this.buttonScaleY);
        ImageView r5 = this.imageView;
        if (r0 <= 0.0f) goto L7;
        int r02 = 0;
    L8:
        r5.setVisibility(r02);
        return;
    L7:
        r02 = 8;
        goto L8
    }

    private void checkUi_LoadingViewVisibility() {
        float r0 = this.animatorLoadingVisibility.getFloatValue();
        float r1 = AndroidUtilities.lerp(r0 / 2.0f, r0, this.animatorIsEnabled.getFloatValue());
        ImageView r2 = this.loadingIndicatorView;
        if (r2 == null) goto L12;
        r2.setAlpha(r1);
        this.loadingIndicatorView.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        this.loadingIndicatorView.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        if (r0 <= 0.0f) goto L7;
        int r02 = 0;
    L9:
        if (this.loadingIndicatorView.getVisibility() == r02) goto L13;
        this.loadingIndicatorView.setVisibility(r02);
        this.loadingIndicatorDrawable.reset();
        return;
    L13:
        return;
    L7:
        r02 = 8;
        goto L9
    }

    public static ChatActivityBlurredRoundButton create(Context r2, BlurredBackgroundDrawableViewFactory r3, BlurredBackgroundColorProvider r4, Theme.ResourcesProvider r5) {
        int r0 = Theme.getColor(Theme.key_glass_defaultIcon, r5);
        ChatActivityBlurredRoundButton r1 = new ChatActivityBlurredRoundButton(r2);
        r1.resourcesProvider = r5;
        r1.setBlurredBackgroundDrawable(r3.create(r1, r4));
        r1.setIconColor(r0);
        r1.setBackground(Theme.createInsetRoundRectDrawable(Theme.multAlpha(r0, 0.15f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f)));
        return r1;
    }

    public static ChatActivityBlurredRoundButton create(Context r2, BlurredBackgroundDrawableViewFactory r3, BlurredBackgroundColorProvider r4, Theme.ResourcesProvider r5, int r6, int r7) {
        int r0 = Theme.getColor(Theme.key_glass_defaultIcon, r5);
        ChatActivityBlurredRoundButton r1 = new ChatActivityBlurredRoundButton(r2);
        r1.resourcesProvider = r5;
        r1.setBlurredBackgroundDrawable(r3.create(r1, r4));
        r1.setIcon(r6, r7);
        r1.setIconColor(r0);
        r1.setBackground(Theme.createInsetRoundRectDrawable(Theme.multAlpha(r0, 0.15f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(4.0f)));
        return r1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
    }

    @Override // android.view.View
    public void draw(Canvas r2) {
        this.backgroundDrawable.draw(r2);
        super.draw(r2);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L5;
        checkUi_IconViewVisibility();
        checkUi_LoadingViewVisibility();
    L5:
        if (r1 != 1) goto L8;
        checkUi_IconViewVisibility();
        checkUi_LoadingViewVisibility();
        return;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.backgroundDrawable.setBounds(0, 0, r1, r2);
    }

    public void reverseIconByY() {
        this.buttonScaleY = -1.0f;
        checkUi_IconViewVisibility();
    }

    public void setBlurredBackgroundDrawable(BlurredBackgroundDrawable r2) {
        this.backgroundDrawable = r2;
        r2.setPadding(AndroidUtilities.dp(4.0f));
        this.backgroundDrawable.setRadius(AndroidUtilities.dp(24.0f));
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        setEnabled(r2, false);
    }

    public void setEnabled(boolean r1, boolean r2) {
        super.setEnabled(r1);
        this.animatorIsEnabled.setValue(r1, r2);
    }

    public void setIcon(int r2) {
        setIcon(r2, 48);
    }

    public void setIcon(int r3, int r4) {
        if (this.imageView != null) goto L7;
        if (r3 != 0) goto L6;
        return;
    L6:
        ImageView r0 = new ImageView(getContext());
        this.imageView = r0;
        r0.setScaleType(ImageView.ScaleType.CENTER);
        addView(this.imageView, LayoutHelper.createFrame(r4, r4, 17));
        checkUi_IconViewVisibility();
    L7:
        this.imageView.setImageResource(r3);
    }

    public void setIconColor(int r3) {
        ImageView r0 = this.imageView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.iconColor = r3;
        if (Build.VERSION.SDK_INT < 29) goto L9;
        ChatActivityBlurredRoundButton$$ExternalSyntheticApiModelOutline1.m();
        r0.setColorFilter(ChatActivityBlurredRoundButton$$ExternalSyntheticApiModelOutline0.m(r3, ProfileGooeyView$GPUImpl$$ExternalSyntheticApiModelOutline0.m()));
        return;
    L9:
        r0.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
    }

    public void setIconPadding(int r2) {
        ImageView r1 = this.imageView;
        if (r1 == null) goto L6;
        r1.setPadding(0, r2, 0, 0);
        return;
    }

    public void showLoading(boolean r9, boolean r10) {
        showLoading(r9, r10, 24.0f, 3.0f, 0.75f, 7.0f, 6.0f);
    }

    public void showLoading(boolean r4, boolean r5, float r6, float r7, float r8, float r9, float r10) {
        if (this.loadingIndicatorView != null) goto L12;
        if (r4 == true) goto L7;
        return;
    L7:
        if (ExteraConfig.getNewLoadingStyle() == false) goto L9;
        CircularProgressDrawable r0 = new CircularProgressDrawable(AndroidUtilities.dp(r6), AndroidUtilities.dp(r7), -9079435, this.iconColor);
        this.loadingIndicatorDrawable = r0;
        r0.setStyle(3, getContext());
        this.loadingIndicatorDrawable.setWavyValues(r8, r9, r10);
    L10:
        this.loadingIndicatorDrawable.setAngleOffset(90.0f);
        ImageView r62 = new ImageView(getContext());
        this.loadingIndicatorView = r62;
        r62.setBackground(this.loadingIndicatorDrawable);
        this.loadingIndicatorView.setVisibility(8);
        addView(this.loadingIndicatorView, LayoutHelper.createFrame(46, 46, 17));
        goto L12
    L9:
        this.loadingIndicatorDrawable = new CircularProgressDrawable(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(1.7f), -9079435);
    L12:
        if (this.animatorLoadingVisibility.getValue() == false) goto L14;
    L16:
        this.animatorLoadingVisibility.setValue(r4, r5);
        return;
    L14:
        if (this.animatorLoadingVisibility.getFloatValue() != 0.0f) goto L16;
        this.loadingIndicatorDrawable.reset();
        goto L16
    }

    public void updateColors() {
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.updateColors();
        invalidate();
    L5:
        int r02 = Theme.key_glass_defaultIcon;
        int r1 = Theme.getColor(r02, this.resourcesProvider);
        setIconColor(Theme.getColor(r02, this.resourcesProvider));
        setBackground(Theme.createInsetRoundRectDrawable(Theme.multAlpha(r1, 0.15f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(4.0f)));
    }
}
