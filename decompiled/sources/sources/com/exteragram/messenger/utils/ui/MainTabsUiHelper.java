package com.exteragram.messenger.utils.ui;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.config.BottomNavigationBar;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProviderBuilder;
import org.telegram.ui.MainTabsLayout;

/* loaded from: classes.dex */
public abstract class MainTabsUiHelper {
    public static void applyBackgroundStroke(BlurredBackgroundProviderBuilder r2) {
        if (isMaterial3NavigationBar() == false) goto L6;
        r2.setStrokeColorTop(0, 0).setStrokeColorBottom(0, 0).setStrokeColorFull(0, 0).setStrokeWidth(0.0f, 0.0f);
        return;
    L6:
        r2.setStrokeColorTop(285212672, 117440511).setStrokeColorBottom(536870912, 301989887).setStrokeWidth(AndroidUtilities.dpf2(0.4f), AndroidUtilities.dpf2(0.4f));
    }

    public static void applyMaterial3MainTabStyle(TextView r8) {
        r8.setIncludeFontPadding(false);
        r8.setLetterSpacing(0.04166667f);
        r8.setLayoutParams(LayoutHelper.createFrame(-1, 16.0f, 49, 0.0f, 42.0f, 0.0f, 0.0f));
    }

    public static void applyTabsBottomInset(MainTabsLayout r5, View r6, int r7, int r8, int r9) {
        int r0 = getTabsViewHeight(r9);
        ViewGroup.MarginLayoutParams r1 = (ViewGroup.MarginLayoutParams) r5.getLayoutParams();
        if (r1.height == r0) goto L5;
        r1.height = r0;
        r5.setLayoutParams(r1);
    L5:
        int r02 = getTabsInnerPaddingVertical();
        if (isMaterial3NavigationBar() == false) goto L8;
        int r12 = r9;
    L9:
        int r03 = r02 + r12;
        if (r5.getPaddingBottom() == r03) goto L13;
        r5.setPadding(r5.getPaddingLeft(), r5.getPaddingTop(), r5.getPaddingRight(), r03);
    L13:
        if (isMaterial3NavigationBar() == false) goto L15;
        r9 = 0;
    L15:
        r6.setPadding(r7, 0, r8, r9);
        return;
    L8:
        r12 = 0;
        goto L9
    }

    public static void applyTabsLayoutStyle(MainTabsLayout r4) {
        boolean r0 = isMaterial3NavigationBar();
        int r1 = 0;
        if (r0 == false) goto L5;
        int r2 = 0;
    L6:
        int r3 = getTabsInnerPaddingVertical();
        r4.setPadding(r2, r3, r2, r3);
        if (r0 == true) goto L10;
        r1 = AndroidUtilities.dp(344.0f);
    L10:
        r4.setMaxWidth(r1);
        r4.setFillAvailableWidth(r0);
        r4.setSwipeSelectionEnabled(!r0);
        r4.setDrawTopDivider(r0);
        return;
    L5:
        r2 = AndroidUtilities.dp(12.0f);
        goto L6
    }

    private static Drawable createMainTabsScrimBackground(int r6, boolean r7) {
        if (r7 == false) goto L5;
        ShapeDrawable r62 = Theme.createCircleDrawable(AndroidUtilities.dp(40.0f), r6);
    L4:
        ShapeDrawable r1 = r62;
        r1.getPaint().setShadowLayer(AndroidUtilities.dp(6.0f), 0.0f, AndroidUtilities.dp(1.0f), Theme.multAlpha(-16777216, 0.15f));
        if (isMaterial3NavigationBar() == true) goto L10;
        return r1;
    L10:
        return new InsetDrawable(r1, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
    L5:
        r62 = Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), r6);
        goto L4
    }

    public static Drawable createMainTabsScrimBackground(Theme.ResourcesProvider r1, boolean r2) {
        return createMainTabsScrimBackground(Theme.getColor(Theme.key_windowBackgroundWhite, r1), r2);
    }

    public static <K> SpringAnimation createMaterial3ColorSpring(K r1, FloatPropertyCompat<K> r2) {
        return new SpringAnimation(r1, r2).setMinimumVisibleChange(0.01f).setSpring(new SpringForce(0.0f).setDampingRatio(1.0f).setStiffness(1600.0f));
    }

    public static <K> SpringAnimation createMaterial3IndicatorSizeSpring(K r1, FloatPropertyCompat<K> r2) {
        return new SpringAnimation(r1, r2).setMinimumVisibleChange(0.01f).setSpring(new SpringForce(0.0f).setDampingRatio(0.8f).setStiffness(380.0f));
    }

    public static int getAdditionalNavigationBarHeight(boolean r0) {
        if (r0 == true) goto L4;
        return 0;
    L4:
        if (BottomNavigationBar.hidden() == false) goto L6;
        return 0;
    L6:
        if (BottomNavigationBar.floating() == false) goto L8;
        return 0;
    L8:
        return AndroidUtilities.dp(getTabsViewHeightDp());
    }

    public static int getBackgroundInset() {
        if (isMaterial3NavigationBar() == false) goto L7;
        return 0;
    L7:
        return AndroidUtilities.dp(7.666f);
    }

    public static float getBackgroundRadius() {
        if (isMaterial3NavigationBar() == false) goto L7;
        return 0.0f;
    L7:
        return AndroidUtilities.dp(28.0f);
    }

    public static int getFloatingTabsPadding(boolean r0) {
        if (r0 == true) goto L4;
        return 0;
    L4:
        if (BottomNavigationBar.floating() == true) goto L6;
        return 0;
    L6:
        return AndroidUtilities.dp(getTabsViewHeightDp());
    }

    public static float getMainTabCounterCenterY(boolean r1) {
        if (r1 == false) goto L6;
        return AndroidUtilities.dp(getMaterial3MainTabIconTopDp() + 6.0f);
    L6:
        return AndroidUtilities.dpf2(10.0f);
    }

    public static int getMainTabSelectedIndicatorColor(int r1, float r2) {
        return Theme.multAlpha(r1, r2 * 0.125f);
    }

    public static float getMaterial3MainTabAvatarTopDp() {
        return getMaterial3MainTabIconTopDp() + 1.0f;
    }

    public static float getMaterial3MainTabIconTopDp() {
        return 10.0f;
    }

    public static float getSelectedBackgroundScale(float r2) {
        return AndroidUtilities.lerp(0.6f, 1.0f, r2);
    }

    public static int getTabsFabOffset(boolean r0) {
        if (r0 == true) goto L4;
        return 0;
    L4:
        if (BottomNavigationBar.visible() == true) goto L6;
        return 0;
    L6:
        return AndroidUtilities.dp(getTabsFabOffsetDp());
    }

    public static int getTabsFabOffsetDp() {
        isMaterial3NavigationBar();
        return 64;
    }

    private static int getTabsInnerPaddingVertical() {
        if (isMaterial3NavigationBar() == false) goto L7;
        return 0;
    L7:
        return AndroidUtilities.dp(12.0f);
    }

    private static int getTabsViewHeight(int r1) {
        if (isMaterial3NavigationBar() == false) goto L7;
        return AndroidUtilities.dp(64.0f) + r1;
    L7:
        return AndroidUtilities.dp(72.0f);
    }

    public static int getTabsViewHeightDp() {
        if (isMaterial3NavigationBar() == false) goto L6;
        return 64;
    L6:
        return 72;
    }

    public static boolean isMaterial3NavigationBar() {
        return ExteraConfig.getNewNavigationBarStyle();
    }

    public static void setBlurBounds(RectF r2, View r3, int r4) {
        if (isMaterial3NavigationBar() == false) goto L5;
        int r0 = r3.getMeasuredHeight();
        int r1 = (r0 - AndroidUtilities.dp(64.0f)) - r4;
    L6:
        r2.set(0.0f, r1, r3.getMeasuredWidth(), r0);
        return;
    L5:
        r0 = (r3.getMeasuredHeight() - r4) - AndroidUtilities.dp(8.0f);
        r1 = r0 - AndroidUtilities.dp(56.0f);
        goto L6
    }

    public static void setMainTabSelectedIndicatorBounds(RectF r4, float r5, int r6, float r7) {
        float r0 = Math.min(AndroidUtilities.dp(56.0f), Math.max(0.0f, r5 - (AndroidUtilities.dp(4.0f) * 2.0f))) * Math.max(0.0f, r7);
        float r62 = Math.min(AndroidUtilities.dp(32.0f), r6);
        float r52 = (r5 - r0) / 2.0f;
        float r72 = AndroidUtilities.dp(6.0f);
        r4.set(r52, r72, r0 + r52, r62 + r72);
    }
}
