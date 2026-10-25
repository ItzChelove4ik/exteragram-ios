package org.telegram.ui.Components.blur3.drawable.color.impl;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import com.exteragram.messenger.utils.ui.FabUiHelper;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProviderBuilder;

/* loaded from: classes3.dex */
public abstract class BlurredBackgroundProviderImpl {
    /* renamed from: $r8$lambda$-1vfWZdCmKAAke5Fas3luu-Aqq4, reason: not valid java name */
    public static /* synthetic */ int m13661$r8$lambda$1vfWZdCmKAAke5Fas3luuAqq4(Theme.ResourcesProvider r0, Theme.ResourcesProvider r1, boolean r2) {
        if (checkBlurEnabled(r0) == true) goto L10;
        if (r2 == false) goto L6;
        int r02 = Theme.key_actionBarDefault;
    L8:
        return ColorUtils.setAlphaComponent(Theme.getColor(r02, r1), 255);
    L6:
        r02 = Theme.key_chat_topPanelBackground;
        goto L8
    L10:
        if (LiteMode.isEnabled(262144) == false) goto L12;
        float r03 = 0.85f;
    L14:
        return Theme.multAlpha(Theme.getColor(Theme.key_chat_topPanelBackground, r1), r03);
    L12:
        r03 = 0.76f;
        goto L14
    }

    /* renamed from: $r8$lambda$95tzLDa89xntyIIe-_kss-csLsA, reason: not valid java name */
    public static /* synthetic */ int m13662$r8$lambda$95tzLDa89xntyIIe_ksscsLsA(Theme.ResourcesProvider r1, boolean r2) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r22 = 0.85f;
    L7:
        return Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, r1), r22);
    L5:
        r22 = 0.76f;
        goto L7
    }

    public static /* synthetic */ int $r8$lambda$E2ekdkVA5GhQenCIourmaRxh0b4(Theme.ResourcesProvider r1, boolean r2) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r22 = 0.85f;
    L7:
        return Theme.multAlpha(Theme.getColor(Theme.key_undo_background, r1), r22);
    L5:
        r22 = 0.76f;
        goto L7
    }

    /* renamed from: $r8$lambda$E5ak8mLTAF7-d3CTsOGRDBxI0Wo, reason: not valid java name */
    public static /* synthetic */ int m13663$r8$lambda$E5ak8mLTAF7d3CTsOGRDBxI0Wo(Theme.ResourcesProvider r0, Theme.ResourcesProvider r1, boolean r2) {
        if (checkBlurEnabled(r0) == true) goto L9;
        if (r2 == false) goto L6;
        int r02 = Theme.key_actionBarDefault;
    L8:
        return ColorUtils.setAlphaComponent(Theme.getColor(r02, r1), 255);
    L6:
        r02 = Theme.key_chat_topPanelBackground;
        goto L8
    L9:
        int r03 = Theme.getColor(Theme.key_chat_topPanelBackground, r1);
        boolean r12 = LiteMode.isEnabled(262144);
        if (ChatHeaderUiHelper.isMaterial3ChatHeaderStyle() == false) goto L14;
        if (r12 == false) goto L13;
        float r13 = 0.93f;
    L18:
        return Theme.multAlpha(r03, r13);
    L13:
        r13 = 0.9f;
        goto L18
    L14:
        if (r12 == false) goto L16;
        r13 = 0.85f;
        goto L18
    L16:
        r13 = 0.76f;
        goto L18
    }

    /* renamed from: $r8$lambda$Ey0ciPZzjiMsZuFQ-_v8uWwdfo0, reason: not valid java name */
    public static /* synthetic */ int m13664$r8$lambda$Ey0ciPZzjiMsZuFQ_v8uWwdfo0(Theme.ResourcesProvider r2, boolean r3) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r32 = 0.85f;
    L7:
        return solveSrcColor(Theme.getColor(Theme.key_windowBackgroundWhite, r2), Theme.getColor(Theme.key_chat_messagePanelBackground, r2), r32);
    L5:
        r32 = 0.76f;
        goto L7
    }

    public static /* synthetic */ int $r8$lambda$Fd_1kgro3whVhcO8eYoVc5pG_Ww(Theme.ResourcesProvider r0, boolean r1) {
        return Theme.getDividerColor(r0);
    }

    public static /* synthetic */ int $r8$lambda$KRa5xYxJoLBG4llc1t3CMeUYNfo(Theme.ResourcesProvider r0, boolean r1) {
        int r02 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground);
        if (r1 == false) goto L5;
        float r12 = 0.85f;
    L7:
        return Theme.multAlpha(r02, r12);
    L5:
        r12 = 0.825f;
        goto L7
    }

    /* renamed from: $r8$lambda$MB_-LqQxeNGauM_z9i_xeGRDLuU, reason: not valid java name */
    public static /* synthetic */ int m13665$r8$lambda$MB_LqQxeNGauM_z9i_xeGRDLuU(Theme.ResourcesProvider r0, boolean r1) {
        return 855638016;
    }

    public static /* synthetic */ int $r8$lambda$RlNs06gnkX92t5BmifrwF6d2b9I(Theme.ResourcesProvider r0, boolean r1) {
        return Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, r0), 0.7f);
    }

    /* renamed from: $r8$lambda$WRfHXHldZtMx9acDdcF-vqQPS80, reason: not valid java name */
    public static /* synthetic */ int m13666$r8$lambda$WRfHXHldZtMx9acDdcFvqQPS80(Theme.ResourcesProvider r0, Theme.ResourcesProvider r1, boolean r2) {
        if (checkBlurEnabled(r0) == true) goto L7;
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_chat_messagePanelBackground, r1), 255);
    L7:
        if (LiteMode.isEnabled(262144) == false) goto L9;
        float r02 = 0.85f;
    L11:
        return Theme.multAlpha(Theme.getColor(Theme.key_chat_messagePanelBackground, r1), r02);
    L9:
        r02 = 0.76f;
        goto L11
    }

    public static /* synthetic */ int $r8$lambda$aljF0JGFaKv3yL9Uyiq_MTaWaRA(Theme.ResourcesProvider r1, boolean r2) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r22 = 0.85f;
    L7:
        return Theme.multAlpha(Theme.getColor(Theme.key_dialogBackground, r1), r22);
    L5:
        r22 = 0.76f;
        goto L7
    }

    public static /* synthetic */ int $r8$lambda$bh8Wi0pKzsRvX8abf50Bjdo7WvI(Theme.ResourcesProvider r2, boolean r3) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r32 = 0.85f;
    L7:
        return solveSrcColor(Theme.getColor(Theme.key_windowBackgroundWhite, r2), Theme.getColor(Theme.key_glass_targetMainTopPanel, r2), r32);
    L5:
        r32 = 0.76f;
        goto L7
    }

    public static /* synthetic */ int $r8$lambda$cVQN6xfrBp8WTGSyjeBQRzVi88g(Theme.ResourcesProvider r0, boolean r1) {
        return Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, r0), 0.88f);
    }

    /* renamed from: $r8$lambda$gjk22_c-7zCYamLTAxT-Wm_snjw, reason: not valid java name */
    public static /* synthetic */ int m13667$r8$lambda$gjk22_c7zCYamLTAxTWm_snjw(Theme.ResourcesProvider r1, boolean r2) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r22 = 0.85f;
    L7:
        return Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, r1), r22);
    L5:
        r22 = 0.76f;
        goto L7
    }

    /* renamed from: $r8$lambda$kVTZ-_qz8TMV_AzYRhCiuQLJlNs, reason: not valid java name */
    public static /* synthetic */ int m13668$r8$lambda$kVTZ_qz8TMV_AzYRhCiuQLJlNs(Theme.ResourcesProvider r0, boolean r1) {
        return Theme.multAlpha(Theme.getColor(Theme.key_dialogBackground, r0), 0.78f);
    }

    /* renamed from: $r8$lambda$pG-SLx9dugH4cqe5Yg9zJYGZO10, reason: not valid java name */
    public static /* synthetic */ int m13669$r8$lambda$pGSLx9dugH4cqe5Yg9zJYGZO10(Theme.ResourcesProvider r0, boolean r1) {
        LiteMode.isEnabled(262144);
        return 0;
    }

    public static /* synthetic */ int $r8$lambda$q6hwrFcwlRx4ekaWdAlAGU3xaYo(Theme.ResourcesProvider r0, boolean r1) {
        return TLObject.FLAG_30;
    }

    /* renamed from: $r8$lambda$u9331ymO4dSaP3a7W9JEn0Yi-iY, reason: not valid java name */
    public static /* synthetic */ int m13670$r8$lambda$u9331ymO4dSaP3a7W9JEn0YiiY(Theme.ResourcesProvider r0, boolean r1) {
        if (LiteMode.isEnabled(256) == false) goto L5;
        int r02 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground);
        if (r1 == false) goto L9;
        float r12 = 0.85f;
    L11:
        return Theme.multAlpha(r02, r12);
    L9:
        r12 = 0.825f;
        goto L11
    L5:
        return Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground);
    }

    public static /* synthetic */ int $r8$lambda$wfoIaOdccp5qam9I3rr5JeZYqrU(Theme.ResourcesProvider r2, boolean r3) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r32 = 0.85f;
    L7:
        return solveSrcColor(Theme.getColor(Theme.key_windowBackgroundWhite, r2), Theme.getColor(Theme.key_glass_targetMainTabs, r2), r32);
    L5:
        r32 = 0.76f;
        goto L7
    }

    /* renamed from: $r8$lambda$xX6LfYFnk7J-GyLTGNY7oUWQ2ok, reason: not valid java name */
    public static /* synthetic */ int m13671$r8$lambda$xX6LfYFnk7JGyLTGNY7oUWQ2ok(Theme.ResourcesProvider r0, boolean r1) {
        return Theme.multAlpha(-16777216, 0.075f);
    }

    public static BlurredBackgroundProvider attachMenuSearch(Theme.ResourcesProvider r3) {
        return new BlurredBackgroundProviderBuilder(r3).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda4()).setStrokeColorTop(385875968, 402653183).setStrokeColorBottom(385875968, 402653183).setShadowColor(285212672, 83886079).setShadowLayer(AndroidUtilities.dpf2(2.0f), 0.0f, AndroidUtilities.dpf2(0.33333334f)).setStrokeWidth(AndroidUtilities.dpf2(0.4f), AndroidUtilities.dpf2(0.4f)).build();
    }

    public static BlurredBackgroundProvider bottomPanelChatActivity(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda16(r2)).setStrokeColorTop(-1, 687865855).setStrokeColorBottom(-1, 352321535).setShadowColor(536870912, 0).setStrokeWidth(AndroidUtilities.dpf2(0.5f), AndroidUtilities.dpf2(0.5f)).build();
    }

    public static BlurredBackgroundProvider bulletin(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda17()).setStrokeWidth(AndroidUtilities.dpf2(0.5f), AndroidUtilities.dpf2(0.5f)).build();
    }

    public static boolean checkBlurEnabled(int r3, Theme.ResourcesProvider r4) {
        if (r4 == null) goto L4;
        boolean r42 = r4.isDark();
    L5:
        boolean r0 = SharedConfig.chatBlurEnabled();
        if (r0 == false) goto L11;
        if (r42 == true) goto L11;
        if (MessagesController.getInstance(r3).config.disableBlurInLightTheme.get() == false) goto L11;
        r0 = false;
    L11:
        if (r0 == false) goto L16;
        if (r42 == false) goto L16;
        if (MessagesController.getInstance(r3).config.disableBlurInDarkTheme.get() == false) goto L16;
        return false;
    L16:
        return r0;
    L4:
        r42 = Theme.isCurrentThemeDark();
        goto L5
    }

    public static boolean checkBlurEnabled(Theme.ResourcesProvider r1) {
        return checkBlurEnabled(UserConfig.selectedAccount, r1);
    }

    public static BlurredBackgroundProvider counterMini(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda10()).setStrokeColorTop(1627389951, 1358954495).setStrokeColorBottom(603979776, 603979776).setShadowColor(0, 0).setShadowLayer(0.0f, 0.0f, 0.0f).setStrokeWidth(AndroidUtilities.dpf2(0.43f), AndroidUtilities.dpf2(0.43f)).build();
    }

    public static BlurredBackgroundProvider emojiViewButton(Theme.ResourcesProvider r3) {
        return new BlurredBackgroundProviderBuilder(r3).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda12()).setStrokeColorTop(-1, 687865855).setStrokeColorBottom(-1, 352321535).setShadowColor(TLObject.FLAG_30, 0).setShadowLayer(AndroidUtilities.dpf2(3.6666667f), 0.0f, AndroidUtilities.dpf2(0.6666667f)).setStrokeWidth(AndroidUtilities.dpf2(0.5f), AndroidUtilities.dpf2(0.5f)).build();
    }

    public static BlurredBackgroundProvider inputFieldDialogActivity(Theme.ResourcesProvider r0) {
        return topPanel(r0);
    }

    public static BlurredBackgroundProvider inputFieldShareAlert(Theme.ResourcesProvider r4) {
        return new BlurredBackgroundProviderBuilder(r4).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda1()).setStrokeColorTop(687865855, 687865855).setStrokeColorBottom(352321535, 352321535).setShadowColor(536870912, 0).setShadowLayer(AndroidUtilities.dpf2(3.3333333f), 0.0f, AndroidUtilities.dpf2(0.6666667f)).setStrokeWidth(AndroidUtilities.dpf2(1.0f), AndroidUtilities.dpf2(0.6666667f)).build();
    }

    public static BlurredBackgroundProvider mainTabs(Theme.ResourcesProvider r3) {
        BlurredBackgroundProviderBuilder r32 = new BlurredBackgroundProviderBuilder(r3).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda3()).setShadowColor(536870912, 83886079).setShadowLayer(AndroidUtilities.dpf2(2.667f), 0.0f, AndroidUtilities.dpf2(0.85f));
        MainTabsUiHelper.applyBackgroundStroke(r32);
        return r32.build();
    }

    public static BlurredBackgroundProvider messageMenuBackground(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda13()).setStrokeColorTop(1157627903, 0).setStrokeColorBottom(587202559, 0).setShadowColor(939524096, 0).setShadowLayer(AndroidUtilities.dpf2(3.5f), 0.0f, 0.0f).setStrokeWidth(AndroidUtilities.dpf2(0.6666667f), AndroidUtilities.dpf2(0.6666667f)).build();
    }

    public static BlurredBackgroundProvider photoViewer(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda11()).setStrokeColorTop(687865855, 687865855).setStrokeColorBottom(352321535, 352321535).setStrokeColorFull(687865855, 687865855).setStrokeWidth(AndroidUtilities.dpf2(0.6666667f), AndroidUtilities.dpf2(0.6666667f)).build();
    }

    public static BlurredBackgroundProvider photoViewerMenu(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda18()).setStrokeColorTop(687865855, 687865855).setStrokeColorBottom(352321535, 352321535).setStrokeWidth(AndroidUtilities.dpf2(0.6666667f), AndroidUtilities.dpf2(0.6666667f)).build();
    }

    public static BlurredBackgroundProvider premiumButton(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda8()).setStrokeColorTop(-1, 553648127).setStrokeColorBottom(0, 553648127).setShadowColor(1207959552, 83886079).setShadowLayer(AndroidUtilities.dpf2(0.6666667f), 0.0f, 0.0f).setStrokeWidth(AndroidUtilities.dpf2(0.67f), AndroidUtilities.dpf2(0.67f)).build();
    }

    public static BlurredBackgroundProvider scrimMenuBackground(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda2()).setStrokeColorTop(1157627903, 0).setStrokeColorBottom(587202559, 0).setShadowColor(637534208, 0).setShadowLayer(AndroidUtilities.dpf2(4.0f), 0.0f, 0.0f).setStrokeWidth(AndroidUtilities.dpf2(0.6666667f), AndroidUtilities.dpf2(0.6666667f)).build();
    }

    public static BlurredBackgroundProvider searchFloatingDate(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda15()).setStrokeColorTop(385875968, 402653183).setStrokeColorBottom(385875968, 402653183).setShadowColor(0, 0).setStrokeWidth(1.0f, 1.0f).build();
    }

    public static BlurredBackgroundProvider shadow(Theme.ResourcesProvider r3) {
        return new BlurredBackgroundProviderBuilder(r3).setStrokeColorTop(0, 687865855).setStrokeColorBottom(0, 352321535).setShadowColor(805306368, 83886079).setShadowLayer(AndroidUtilities.dpf2(4.0f), 0.0f, AndroidUtilities.dpf2(0.33333334f)).setStrokeWidth(AndroidUtilities.dpf2(0.4f), AndroidUtilities.dpf2(0.4f)).build();
    }

    public static int solveSrcColor(int r7, int r8, float r9) {
        float r92 = MathUtils.clamp(r9, 0.0f, 1.0f);
        if (r92 > 0.0f) goto L7;
        return Color.argb(0, 0, 0, 0);
    L7:
        if (r92 >= 1.0f) goto L9;
        int r0 = Color.red(r7);
        int r4 = Color.green(r7);
        int r72 = Color.blue(r7);
        int r5 = Color.red(r8);
        int r6 = Color.green(r8);
        int r82 = Color.blue(r8);
        float r1 = 1.0f - r92;
        return Color.argb(MathUtils.clamp(Math.round(r92 * 255.0f), 0, 255), MathUtils.clamp(Math.round((r5 - (r0 * r1)) / r92), 0, 255), MathUtils.clamp(Math.round((r6 - (r4 * r1)) / r92), 0, 255), MathUtils.clamp(Math.round((r82 - (r72 * r1)) / r92), 0, 255));
    L9:
        return Color.argb(255, Color.red(r8), Color.green(r8), Color.blue(r8));
    }

    public static BlurredBackgroundProvider subFloatingButton(Theme.ResourcesProvider r3) {
        return new BlurredBackgroundProviderBuilder(r3).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda14()).setStrokeColorTop(285212672, 117440511).setStrokeColorBottom(536870912, 301989887).setShadowColor(FabUiHelper.getSubFabShadowColor(), 83886079).setShadowLayer(AndroidUtilities.dpf2(FabUiHelper.getSubFabShadowRadiusDp()), 0.0f, AndroidUtilities.dpf2(FabUiHelper.getSubFabShadowDyDp())).setStrokeWidth(AndroidUtilities.dpf2(0.4f), AndroidUtilities.dpf2(0.4f)).setShadowAlwaysVisible().build();
    }

    public static BlurredBackgroundProvider tabsPanelNoShadow(Theme.ResourcesProvider r1) {
        return new BlurredBackgroundProviderBuilder(r1).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda19()).setStrokeColorTop(0, 0).setStrokeColorBottom(0, 0).setShadowColor(0, 0).build();
    }

    public static BlurredBackgroundProvider topPanel(Theme.ResourcesProvider r3) {
        return new BlurredBackgroundProviderBuilder(r3).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda0()).setStrokeColorTop(285212672, 117440511).setStrokeColorBottom(536870912, 301989887).setShadowColor(536870912, 83886079).setShadowLayer(AndroidUtilities.dpf2(2.667f), 0.0f, AndroidUtilities.dpf2(0.85f)).setStrokeWidth(AndroidUtilities.dpf2(0.4f), AndroidUtilities.dpf2(0.4f)).build();
    }

    public static BlurredBackgroundProvider topPanelChatActivity(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda6(r2)).setStrokeColorTop(-1, 553648127).setStrokeColorBottom(-1, 352321535).setStrokeColorFull(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda7()).setShadowColor(536870912, 0).setStrokeWidth(AndroidUtilities.dpf2(0.55f), AndroidUtilities.dpf2(0.55f)).build();
    }

    public static BlurredBackgroundProvider topPanelChatActivitySearchListBg(Theme.ResourcesProvider r1) {
        return new BlurredBackgroundProviderBuilder(r1).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda9()).setStrokeColorTop(0, 0).setStrokeColorBottom(0, 0).setStrokeColorFull(0, 0).setShadowColor(0, 0).setShadowLayer(0.0f, 0.0f, 0.0f).setStrokeWidth(0.0f, 0.0f).build();
    }

    public static BlurredBackgroundProvider topPanelChatActivityTags(Theme.ResourcesProvider r2) {
        return new BlurredBackgroundProviderBuilder(r2).setBackgroundColor(new BlurredBackgroundProviderImpl$$ExternalSyntheticLambda5(r2)).setStrokeColorTop(0, 0).setStrokeColorBottom(0, 0).setStrokeColorFull(0, 0).setShadowColor(0, 0).setShadowLayer(0.0f, 0.0f, 0.0f).setStrokeWidth(0.0f, 0.0f).build();
    }
}
