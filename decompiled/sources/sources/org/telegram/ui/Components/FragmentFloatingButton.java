package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.FabUiHelper;
import com.exteragram.messenger.utils.ui.UIUtil;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class FragmentFloatingButton extends FrameLayout implements FactorAnimator.Target {
    private final int ANIMATOR_ID_BUTTON_VISIBLE;
    private final int ANIMATOR_ID_PROGRESS_VISIBLE;
    private ArrayList<View> additionalContentViews;
    private float additionalTranslationY;
    private final BoolAnimator animatorButtonVisible;
    private final BoolAnimator animatorProgressVisible;
    private BlurredBackgroundDrawable iBlur3Background;
    private BlurredBackgroundSourceColor iBlur3SourceColor;
    public final RLottieImageView imageView;
    private float internalTranslationY;
    private final boolean isSubButton;
    public final RadialProgressView progressView;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean setTranslationInternal;

    public FragmentFloatingButton(Context r2, Theme.ResourcesProvider r3) {
        this(r2, r3, false);
    }

    public FragmentFloatingButton(Context r10, Theme.ResourcesProvider r11, boolean r12) {
        super(r10);
        this.ANIMATOR_ID_BUTTON_VISIBLE = 0;
        this.ANIMATOR_ID_PROGRESS_VISIBLE = 1;
        CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorButtonVisible = new BoolAnimator(0, this, r5, 380, true);
        this.animatorProgressVisible = new BoolAnimator(1, this, r5, 380);
        this.resourcesProvider = r11;
        this.isSubButton = r12;
        RLottieImageView r9 = new RLottieImageView(r10);
        this.imageView = r9;
        r9.setScaleType(ImageView.ScaleType.CENTER);
        addView(r9, LayoutHelper.createFrame(-1, -1.0f));
        RadialProgressView r92 = new RadialProgressView(r10);
        this.progressView = r92;
        r92.setSize(AndroidUtilities.dp(18.0f));
        r92.setStrokeWidth(2.0f);
        addView(r92, LayoutHelper.createFrame(-1, -1.0f));
        setAnimatedVisibility(r92, 0.0f);
        ScaleStateListAnimator.apply(this);
        if (r12 == false) goto L5;
    L9:
        if (r12 == false) goto L11;
        BlurredBackgroundSourceColor r93 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r93;
        BlurredBackgroundDrawable r94 = r93.createDrawable();
        this.iBlur3Background = r94;
        r94.setColorProvider(BlurredBackgroundProviderImpl.subFloatingButton(r11));
        this.iBlur3Background.setRadius(AndroidUtilities.dp(FabUiHelper.getSubFabBackgroundRadiusDp()));
        this.iBlur3Background.setPadding(AndroidUtilities.dp(FabUiHelper.getSubFabPaddingDp()));
    L11:
        updateColors();
        return;
    L5:
        if (ExteraConfig.getSquareFab() == false) goto L7;
        ViewOutlineProvider r95 = ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(FabUiHelper.getFabCornerRadiusDp()));
    L8:
        setOutlineProvider(r95);
        setTranslationZ(AndroidUtilities.dpf2(FabUiHelper.getFabElevationDp()));
        goto L9
    L7:
        r95 = ViewOutlineProviderImpl.BOUNDS_OVAL;
        goto L8
    }

    public static FrameLayout.LayoutParams createDefaultLayoutParams() {
        int r0 = FabUiHelper.getFabSizeDp();
        float r1 = FabUiHelper.getFabSizeDp();
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 3;
    L7:
        return LayoutHelper.createFrame(r0, r1, r2 | 80, 20.0f, 0.0f, 20.0f, FabUiHelper.getFabBottomMarginDp());
    L5:
        r2 = 5;
        goto L7
    }

    public static FrameLayout.LayoutParams createDefaultLayoutParamsBig() {
        if (LocaleController.isRTL == false) goto L5;
        int r0 = 3;
    L7:
        return LayoutHelper.createFrame(56, 56.0f, r0 | 80, 20.0f, 0.0f, 20.0f, FabUiHelper.getFabBottomMarginDp());
    L5:
        r0 = 5;
        goto L7
    }

    public static FrameLayout.LayoutParams createSubButtonLayoutParams() {
        int r0 = FabUiHelper.getFabSizeDp();
        float r1 = FabUiHelper.getFabSizeDp();
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 3;
    L7:
        return LayoutHelper.createFrame(r0, r1, r2 | 80, 20.0f, 0.0f, 20.0f, FabUiHelper.getSubFabBottomMarginDp());
    L5:
        r2 = 5;
        goto L7
    }

    private void setAdditionalTranslationY(float r2) {
        if (this.additionalTranslationY == r2) goto L6;
        this.setTranslationInternal = true;
        super.setTranslationY(this.internalTranslationY + r2);
        this.setTranslationInternal = false;
        this.additionalTranslationY = r2;
        return;
    }

    public static void setAnimatedVisibility(View r3, float r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        r3.setAlpha(r4);
        r3.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r4));
        r3.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r4));
        if (r4 <= 0.0f) goto L7;
        int r42 = 0;
    L8:
        r3.setVisibility(r42);
        return;
    L7:
        r42 = 8;
        goto L8
    }

    public void addAdditionalView(View r2) {
        if (this.additionalContentViews != null) goto L5;
        this.additionalContentViews = new ArrayList();
    L5:
        this.additionalContentViews.add(r2);
        setAnimatedVisibility(r2, 1.0f - this.animatorProgressVisible.getFloatValue());
    }

    @Override // android.view.View
    public void draw(Canvas r2) {
        BlurredBackgroundDrawable r0 = this.iBlur3Background;
        if (r0 == null) goto L5;
        r0.draw(r2);
    L5:
        super.draw(r2);
    }

    public boolean getButtonVisible() {
        return this.animatorButtonVisible.getValue();
    }

    public boolean getProgressVisible() {
        return this.animatorProgressVisible.getValue();
    }

    @Override // android.view.View
    public float getTranslationY() {
        if (this.setTranslationInternal == false) goto L7;
        return super.getTranslationY();
    L7:
        return this.internalTranslationY;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r2, float r3, float r4, FactorAnimator r5) {
        int r52 = 0;
        boolean r53 = false;
        if (r2 != 0) goto L13;
        setAnimatedVisibility(this, r3);
        if (r3 < 0.99f) goto L7;
        r53 = true;
    L7:
        setClickable(r53);
        if (this.isSubButton == false) goto L10;
        float r22 = 64.0f;
    L11:
        setAdditionalTranslationY(AndroidUtilities.dp(r22) * (1.0f - r3));
        return;
    L10:
        r22 = 40.0f;
        goto L11
    L13:
        if (r2 != 1) goto L19;
        setAnimatedVisibility(this.progressView, r3);
        float r42 = 1.0f - r3;
        setAnimatedVisibility(this.imageView, r42);
        ArrayList<View> r1 = this.additionalContentViews;
        if (r1 == null) goto L21;
        int r23 = r1.size();
    L17:
        if (r52 >= r23) goto L22;
        View r32 = r1.get(r52);
        r52 = r52 + 1;
        setAnimatedVisibility(r32, r42);
        goto L17
    L22:
        return;
    L21:
        return;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        BlurredBackgroundDrawable r0 = this.iBlur3Background;
        if (r0 == null) goto L6;
        r0.setBounds(0, 0, r1, r2);
        return;
    }

    public void setAnimation(int r1, int r2) {
        this.imageView.setAnimation(r1, r2, r2);
    }

    public void setButtonVisible(boolean r1, boolean r2) {
        this.animatorButtonVisible.setValue(r1, r2);
    }

    public void setImageResource(int r1) {
        this.imageView.setImageResource(r1);
    }

    public void setProgressVisible(boolean r1, boolean r2) {
        this.animatorProgressVisible.setValue(r1, r2);
    }

    @Override // android.view.View
    public void setTranslationY(float r2) {
        if (this.internalTranslationY == r2) goto L6;
        this.setTranslationInternal = true;
        super.setTranslationY(this.additionalTranslationY + r2);
        this.setTranslationInternal = false;
        this.internalTranslationY = r2;
        return;
    }

    public void updateColors() {
        boolean r0 = this.isSubButton;
        RLottieImageView r1 = this.imageView;
        if (r0 == false) goto L6;
        int r02 = Theme.key_actionBarDefaultIcon;
        r1.setColorFilter(Theme.getColor(r02, this.resourcesProvider), PorterDuff.Mode.SRC_IN);
        this.progressView.setProgressColor(Theme.getColor(r02, this.resourcesProvider));
        this.iBlur3SourceColor.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        this.iBlur3Background.updateColors();
        invalidate();
        setBackground(Theme.createInsetRoundRectDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), AndroidUtilities.dp(FabUiHelper.getSubFabBackgroundRadiusDp()), AndroidUtilities.dp(FabUiHelper.getSubFabPaddingDp())));
        return;
    L6:
        int r03 = Theme.key_chats_actionIcon;
        r1.setColorFilter(Theme.getColor(r03, this.resourcesProvider), PorterDuff.Mode.SRC_IN);
        this.progressView.setProgressColor(Theme.getColor(r03, this.resourcesProvider));
        setBackground(UIUtil.createFabBackground(FabUiHelper.getFabSizeDp(), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), Theme.getColor(Theme.key_featuredStickers_addButtonPressed, this.resourcesProvider)));
    }
}
