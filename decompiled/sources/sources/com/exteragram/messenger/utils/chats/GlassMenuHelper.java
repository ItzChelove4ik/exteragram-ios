package com.exteragram.messenger.utils.chats;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.ScrimOptions;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProviderBuilder;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;

/* loaded from: classes4.dex */
public abstract class GlassMenuHelper {
    /* renamed from: $r8$lambda$VhYqc1_r5IlxZtuR_mFy6_NTT-k, reason: not valid java name */
    public static /* synthetic */ void m1777$r8$lambda$VhYqc1_r5IlxZtuR_mFy6_NTTk(BlurredBackgroundSourceBitmap r0, View r1, BlurredBackgroundDrawableViewFactory r2, Bitmap r3, Bitmap r4) {
        r0.setBitmap(r4);
        Blur3Utils.checkBitmapSourceMatrixScale(r0, r1);
        r2.invalidateAllLinkedViews();
    }

    public static /* synthetic */ int $r8$lambda$fBMhOBc4cJbUhtuUSJLcBiZ6eZE(Theme.ResourcesProvider r1, boolean r2) {
        int r12 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground);
        if (LiteMode.isEnabled(256) == true) goto L5;
        return r12;
    L5:
        if (r2 == false) goto L7;
        float r22 = 0.85f;
    L9:
        return Theme.multAlpha(r12, r22);
    L7:
        r22 = 0.825f;
        goto L9
    }

    public static void applyToGaps(View r1, int r2) {
        applyToGaps(r1, r2, false);
    }

    public static void applyToGaps(View r2, int r3, boolean r4) {
        if ((r2 instanceof ActionBarPopupWindow.GapView) == false) goto L7;
        ActionBarPopupWindow.GapView r22 = (ActionBarPopupWindow.GapView) r2;
        r22.setColor(r3);
        r22.setDividerVisible(r4);
        return;
    L7:
        if ((r2 instanceof ViewGroup) == false) goto L12;
        ViewGroup r23 = (ViewGroup) r2;
        int r0 = 0;
    L10:
        if (r0 >= r23.getChildCount()) goto L14;
        applyToGaps(r23.getChildAt(r0), r3, r4);
        r0 = r0 + 1;
        goto L10
    L14:
        return;
    }

    public static void applyToPopup(BlurredBackgroundDrawableViewFactory r1, Theme.ResourcesProvider r2, ActionBarPopupWindow.ActionBarPopupWindowLayout r3) {
        r3.setBackground(createPanelBackground(r1, r2, r3));
        r3.setGlassBackgroundFactory(r1);
        if (r3.getSwipeBack() == null) goto L6;
        r3.getSwipeBack().setForegroundDrawable(createFill(r1, r2, r3.getSwipeBack()));
        return;
    }

    public static void applyToReactions(BlurredBackgroundDrawableViewFactory r0, Theme.ResourcesProvider r1, ReactionsContainerLayout r2) {
        r2.setGlassBackground(r0, scrimMenuBackgroundFill(r1));
    }

    public static void applyToReusedMenu(BlurredBackgroundDrawableViewFactory r2, Theme.ResourcesProvider r3, ActionBarPopupWindow.ActionBarPopupWindowLayout r4, boolean r5) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (r5 == false) goto L8;
        applyToPopup(r2, r3, r4);
        applyToGaps(r4, separatorColor(true, r3));
        return;
    L8:
        r4.setGlassBackgroundFactory(null);
        r4.setBackgroundDrawable(r4.getResources().getDrawable(R.drawable.popup_fixed_alert4).mutate());
        r4.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r3));
        if (r4.getSwipeBack() == null) goto L11;
        r4.getSwipeBack().setForegroundDrawable(null);
    L11:
        applyToGaps(r4, separatorColor(false, r3), true);
    }

    public static void captureBlur(BlurredBackgroundSourceBitmap r1, BlurredBackgroundDrawableViewFactory r2, View r3) {
        ScrimOptions.makeGlobalBlurBitmaps(new GlassMenuHelper$$ExternalSyntheticLambda1(r1, r3, r2));
    }

    public static BlurredBackgroundDrawable createFill(BlurredBackgroundDrawableViewFactory r1, Theme.ResourcesProvider r2, View r3) {
        return r1.create(r3, true).setColorProvider(scrimMenuBackgroundFill(r2));
    }

    public static BlurredBackgroundDrawable createPanelBackground(BlurredBackgroundDrawableViewFactory r1, Theme.ResourcesProvider r2, View r3) {
        return r1.create(r3, true).setColorProvider(scrimMenuBackground(r2)).setRadius(AndroidUtilities.dp(12.0f)).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true);
    }

    public static void draw(BlurredBackgroundDrawable r7, Canvas r8, RectF r9, float r10, int r11) {
        draw(r7, r8, r9, r10, r11, 0.0f, 0.0f);
    }

    public static void draw(BlurredBackgroundDrawable r3, Canvas r4, RectF r5, float r6, int r7, float r8, float r9) {
        if (r8 == 0.0f) goto L5;
    L8:
        boolean r0 = true;
    L9:
        if (r0 == false) goto L11;
        r4.save();
        r4.translate(-r8, -r9);
    L11:
        r3.setRadius(r6);
        r3.setAlpha(r7);
        r3.setBounds(Math.round(r5.left + r8), Math.round(r5.top + r9), Math.round(r5.right + r8), Math.round(r5.bottom + r9));
        r3.draw(r4);
        if (r0 == false) goto L15;
        r4.restore();
        return;
    L15:
        return;
    L5:
        if (r9 != 0.0f) goto L8;
        r0 = false;
        goto L9
    }

    public static boolean isEnabled(int r1, Theme.ResourcesProvider r2) {
        if (ExteraConfig.getGlassMessageMenu() == true) goto L5;
        return false;
    L5:
        if (BlurredBackgroundProviderImpl.checkBlurEnabled(r1, r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isEnabled(Theme.ResourcesProvider r1) {
        if (ExteraConfig.getGlassMessageMenu() == true) goto L5;
        return false;
    L5:
        if (BlurredBackgroundProviderImpl.checkBlurEnabled(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static BlurredBackgroundProvider scrimMenuBackground(Theme.ResourcesProvider r2) {
        return scrimMenuGlass(r2).setShadowColor(637534208, 0).setShadowLayer(AndroidUtilities.dpf2(4.0f), 0.0f, 0.0f).setShadowAlwaysVisible().build();
    }

    public static BlurredBackgroundProvider scrimMenuBackgroundFill(Theme.ResourcesProvider r1) {
        return scrimMenuGlass(r1).setShadowColor(0, 0).build();
    }

    private static BlurredBackgroundProviderBuilder scrimMenuGlass(Theme.ResourcesProvider r1) {
        return new BlurredBackgroundProviderBuilder(r1).setBackgroundColor(new GlassMenuHelper$$ExternalSyntheticLambda0()).setStrokeColorTop(0, 0).setStrokeColorBottom(0, 0).setStrokeColorFull(0, 0).setStrokeWidth(0.0f, 0.0f);
    }

    public static int separatorColor(boolean r1, Theme.ResourcesProvider r2) {
        if (r1 == false) goto L4;
        if (r2 == null) goto L7;
        boolean r12 = r2.isDark();
    L8:
        int r22 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r2);
        if (r12 == false) goto L11;
        float r13 = 0.03f;
    L13:
        return Theme.multAlpha(r22, r13);
    L11:
        r13 = 0.06f;
        goto L13
    L7:
        r12 = Theme.isCurrentThemeDark();
        goto L8
    L4:
        return Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator, r2);
    }
}
