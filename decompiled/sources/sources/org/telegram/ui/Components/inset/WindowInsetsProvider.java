package org.telegram.ui.Components.inset;

/* loaded from: classes3.dex */
public interface WindowInsetsProvider {
    float getAnimatedImeBottomInset();

    float getAnimatedMaxBottomInset();

    int getCurrentNavigationBarInset();

    int getInAppKeyboardRecommendedViewHeight();

    boolean inAppViewIsVisible();
}
