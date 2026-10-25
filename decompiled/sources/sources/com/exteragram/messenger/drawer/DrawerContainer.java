package com.exteragram.messenger.drawer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.ui.AccountsUiHelper;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.DrawerLayoutContainer;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ProxyListActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.ThemeActivity;

/* loaded from: classes.dex */
public class DrawerContainer extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final int COLOR_KEY_DRAWER_BACKGROUND = 0;
    private static final int COLOR_KEY_POPUP_ACCENT = 0;
    private static final FloatPropertyCompat<DrawerContainer> DRAWER_OFFSET = null;
    private final DrawerAccountPickerView accountPickerView;
    private boolean animationInterruptedByTouch;
    private final FrameLayout bulletinContainer;
    private float cachedBottomRightRadius;
    private float cachedTopRightRadius;
    private final Path clipPath;
    private final FrameLayout drawerPanel;
    private int drawerWidth;
    private final DrawerHeaderView headerView;
    private boolean isAnimating;
    private boolean isOpen;
    private final DrawerMenuView menuView;
    private View navigationTranslationTarget;
    private boolean notificationsRegistered;
    private boolean predictiveBackInProgress;
    private float predictiveBackStartProgress;
    private float progress;
    private final float[] radii;
    private final Rect rect;
    private final Paint scrimPaint;
    private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;
    private SpringAnimation springAnimation;
    private ValueAnimator standardAnimator;
    private float startProgress;
    private float startX;
    private float startY;
    private boolean startedEdgeSwipe;
    private boolean tapClosePending;
    private boolean tracking;
    private VelocityTracker velocityTracker;

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatPropertyCompat<DrawerContainer> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(DrawerContainer r1) {
            return DrawerContainer.m1301$$Nest$mgetDrawerOffset(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(DrawerContainer r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(DrawerContainer r1, float r2) {
            DrawerContainer.m1304$$Nest$msetDrawerOffset(r1, r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(DrawerContainer r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 extends SelectAnimatedEmojiDialog {
        final /* synthetic */ DrawerContainer this$0;
        final /* synthetic */ int val$account;
        final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

        public AnonymousClass2(DrawerContainer r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, int r10, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r11) {
            this.this$0 = r1;
            this.val$account = r10;
            this.val$popup = r11;
            super(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r3, Long r4, TLRPC.Document r5, TL_stars.TL_starGiftUnique r6, Integer r7) {
            if (r6 == null) goto L6;
            TLRPC.TL_inputEmojiStatusCollectible r32 = new TLRPC.TL_inputEmojiStatusCollectible();
            r32.collectible_id = r6.id;
            TLRPC.EmojiStatus r33 = r32;
            if (r7 == null) goto L11;
            r32.flags |= 1;
            r32.until = r7.intValue();
            r33 = r32;
        L11:
            MessagesController.getInstance(this.val$account).updateEmojiStatus(0, r33, r6);
            DrawerContainer.m1296$$Nest$fgetheaderView(this.this$0).updateUserInfo();
            if (this.val$popup[0] == null) goto L15;
            DrawerContainer.m1299$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            this.val$popup[0].dismiss();
            return;
        L15:
            return;
        L6:
            if (r4 != null) goto L8;
            r33 = new TLRPC.TL_emojiStatusEmpty();
            goto L11
        L8:
            TLRPC.TL_emojiStatus r34 = new TLRPC.TL_emojiStatus();
            r34.document_id = r4.longValue();
            r33 = r34;
            if (r7 == null) goto L11;
            r34.flags |= 1;
            r34.until = r7.intValue();
            r33 = r34;
            goto L11
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
        final /* synthetic */ DrawerContainer this$0;

        public AnonymousClass3(DrawerContainer r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            DrawerContainer.m1299$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass4 extends SelectAnimatedEmojiDialog {
        final /* synthetic */ DrawerContainer this$0;
        final /* synthetic */ BadgeDTO val$defaultBadge;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

        public static /* synthetic */ void $r8$lambda$LE8TkHMlursnOR_iJvZ4WWqHEns(AnonymousClass4 r0, String r1, BaseFragment r2) {
            r0.lambda$onEmojiSelected$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$f2WQgGCTCp_WE6VVhblch0STt4c(AnonymousClass4 r0, BaseFragment r1, String r2) {
            r0.lambda$onEmojiSelected$1(r1, r2);
        }

        public AnonymousClass4(DrawerContainer r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, int r10, boolean r11, BadgeDTO r12, BaseFragment r13, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r14) {
            this.this$0 = r1;
            this.val$defaultBadge = r12;
            this.val$fragment = r13;
            this.val$popup = r14;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        private /* synthetic */ void lambda$onEmojiSelected$0(String r2, BaseFragment r3) {
            if (r2 != null) goto L4;
        L5:
            BulletinFactory.of(DrawerContainer.m1295$$Nest$fgetbulletinContainer(this.this$0), r3.getResourceProvider()).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        L6:
            DrawerContainer.m1296$$Nest$fgetheaderView(this.this$0).updateUserInfo();
            DrawerContainer.m1294$$Nest$fgetaccountPickerView(this.this$0).loadAccounts();
            return;
        L4:
            if (r2.equals("ok") == true) goto L6;
            goto L5
        }

        private /* synthetic */ void lambda$onEmojiSelected$1(BaseFragment r2, String r3) {
            AndroidUtilities.runOnUIThread(new DrawerContainer$4$$ExternalSyntheticLambda1(this, r3, r2));
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5, String r6) {
            if (r2 != null) goto L7;
            BadgeDTO r12 = this.val$defaultBadge;
            if (r12 == null) goto L6;
            long r13 = r12.getDocumentId();
        L9:
            if (TextUtils.isEmpty(r6) == false) goto L11;
            r6 = null;
        L11:
            BadgeDTO r32 = new BadgeDTO(r13, r6);
            DrawerContainer.m1296$$Nest$fgetheaderView(this.this$0).updateUserInfo(r32);
            DrawerContainer.m1294$$Nest$fgetaccountPickerView(this.this$0).loadAccounts(r32);
            BadgesController.INSTANCE.updateBadge(r32, new DrawerContainer$4$$ExternalSyntheticLambda0(this, this.val$fragment));
            if (this.val$popup[0] == null) goto L15;
            DrawerContainer.m1299$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            this.val$popup[0].dismiss();
            return;
        L15:
            return;
        L6:
            r13 = 0;
            goto L9
        L7:
            r13 = r2.longValue();
            goto L9
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$5, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass5 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
        final /* synthetic */ DrawerContainer this$0;

        public AnonymousClass5(DrawerContainer r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            DrawerContainer.m1299$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$6, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass6 extends MainTabsActivity {
        final /* synthetic */ DrawerContainer this$0;
        final /* synthetic */ int val$account;

        public AnonymousClass6(DrawerContainer r1, Bundle r2, int r3) {
            this.this$0 = r1;
            this.val$account = r3;
            super(r2);
        }

        @Override // org.telegram.ui.MainTabsActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onPreviewOpenAnimationEnd() {
            super.onPreviewOpenAnimationEnd();
            DrawerContainer.m1303$$Nest$mrestoreDrawerAbovePreview(this.this$0);
            this.this$0.closeDrawer(false);
            int r2 = this.val$account;
            if (r2 == UserConfig.selectedAccount) goto L6;
            AccountsUiHelper.switchTo(r2);
            return;
        }

        @Override // org.telegram.ui.MainTabsActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onTransitionAnimationEnd(boolean r1, boolean r2) {
            super.onTransitionAnimationEnd(r1, r2);
            if (r1 == true) goto L7;
            if (r2 == false) goto L8;
            DrawerContainer.m1303$$Nest$mrestoreDrawerAbovePreview(this.this$0);
            return;
        L8:
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$7, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        private boolean canceled;
        final /* synthetic */ DrawerContainer this$0;
        final /* synthetic */ float val$target;
        final /* synthetic */ float val$targetOffset;

        public AnonymousClass7(DrawerContainer r1, float r2, float r3) {
            this.this$0 = r1;
            this.val$targetOffset = r2;
            this.val$target = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.canceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (DrawerContainer.m1297$$Nest$fgetstandardAnimator(this.this$0) != r2) goto L6;
            DrawerContainer.m1300$$Nest$fputstandardAnimator(this.this$0, null);
        L6:
            if (this.canceled == true) goto L13;
            DrawerContainer.m1298$$Nest$fputisAnimating(this.this$0, false);
            DrawerContainer.m1304$$Nest$msetDrawerOffset(this.this$0, this.val$targetOffset);
            if (this.val$target != 0.0f) goto L12;
            DrawerContainer.m1302$$Nest$monCloseComplete(this.this$0);
            return;
        L12:
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.drawer.DrawerContainer$8, reason: invalid class name */
    public class AnonymousClass8 implements Bulletin.Delegate {
        final /* synthetic */ DrawerContainer this$0;

        public AnonymousClass8(DrawerContainer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.navigationBarHeight;
        }
    }

    public static /* synthetic */ void $r8$lambda$2MhzqrZoH4ljkqxC19o1v_6tiCU(DrawerContainer r0) {
        r0.showBadgeSelect();
    }

    public static /* synthetic */ void $r8$lambda$5AxqjXimivKAKRvXnwuSpi5eNJI(DrawerContainer r0) {
        r0.updateColors();
    }

    public static /* synthetic */ void $r8$lambda$AmmL80yFgDAH_95gdy5d6_Ut_tM(DrawerContainer r0, ValueAnimator r1) {
        r0.lambda$animateProgress$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$BHS2N4wD7YhwRIy55TfY8jF65iI(BaseFragment r2) {
        r2.presentFragment(new ThemeActivity(1));
    }

    public static /* synthetic */ void $r8$lambda$IDp3x2OEWKaAI4rz3dVhcs5gHWo(DrawerContainer r0) {
        r0.lambda$setupCallbacks$0();
    }

    public static /* synthetic */ void $r8$lambda$Xrt8dpzcNSqFEuR8xn4wxZCFUGk(DrawerContainer r0) {
        r0.lambda$setupCallbacks$11();
    }

    public static /* synthetic */ void $r8$lambda$YhlaH9CtmKok8yqVGjNhMUMBjOk(DrawerContainer r0, float r1, float r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        r0.lambda$animateProgress$12(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$Zcb87tazylluPeddBq3uW2wSmC4(DrawerContainer r0) {
        r0.lambda$setupCallbacks$7();
    }

    /* renamed from: $r8$lambda$fVitWp-RFGIFuCa2ajtBefuY7rA, reason: not valid java name */
    public static /* synthetic */ void m1292$r8$lambda$fVitWpRFGIFuCa2ajtBefuY7rA(DrawerContainer r0) {
        r0.showStatusSelect();
    }

    public static /* synthetic */ void $r8$lambda$fXW3bkvaP9dmn9lGQEn0J5dTKSc(DrawerContainer r0) {
        r0.lambda$setupCallbacks$5();
    }

    public static /* synthetic */ void $r8$lambda$gjJ6tWGr0iDKWi30h8sUKFZ7Ulc(DrawerContainer r0) {
        r0.lambda$setupCallbacks$3();
    }

    /* renamed from: $r8$lambda$hu2BX-qNrTcH4vJTmwlW4XiAzdE, reason: not valid java name */
    public static /* synthetic */ void m1293$r8$lambda$hu2BXqNrTcH4vJTmwlW4XiAzdE(DrawerContainer r0) {
        r0.lambda$setupCallbacks$2();
    }

    public static /* synthetic */ void $r8$lambda$kLpQLcCMsBnxFrhEklO_YFCzt0I(DrawerContainer r0) {
        r0.lambda$setupCallbacks$8();
    }

    public static /* synthetic */ void $r8$lambda$oxtQPXqaqUy2bLlQRBx5SW0o5CA(DrawerContainer r0, int r1, View r2) {
        r0.lambda$setupCallbacks$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$p095OmiZWtfAv_mMrnN8HYWu07o(DrawerContainer r0) {
        r0.lambda$setupCallbacks$9();
    }

    public static /* synthetic */ void $r8$lambda$sAltj9hZxBCG4oOqvXYIICN4TqE(DrawerContainer r0) {
        r0.lambda$setupCallbacks$4();
    }

    public static /* synthetic */ void $r8$lambda$v0FrSdX1YIUWnMR7Raa8QDhDRK0(DrawerContainer r0) {
        r0.lambda$setupCallbacks$6();
    }

    /* renamed from: -$$Nest$fgetaccountPickerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DrawerAccountPickerView m1294$$Nest$fgetaccountPickerView(DrawerContainer r0) {
        return r0.accountPickerView;
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1295$$Nest$fgetbulletinContainer(DrawerContainer r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetheaderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DrawerHeaderView m1296$$Nest$fgetheaderView(DrawerContainer r0) {
        return r0.headerView;
    }

    /* renamed from: -$$Nest$fgetstandardAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m1297$$Nest$fgetstandardAnimator(DrawerContainer r0) {
        return r0.standardAnimator;
    }

    /* renamed from: -$$Nest$fputisAnimating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1298$$Nest$fputisAnimating(DrawerContainer r0, boolean r1) {
        r0.isAnimating = r1;
    }

    /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1299$$Nest$fputselectAnimatedEmojiDialog(DrawerContainer r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
        r0.selectAnimatedEmojiDialog = r1;
    }

    /* renamed from: -$$Nest$fputstandardAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1300$$Nest$fputstandardAnimator(DrawerContainer r0, ValueAnimator r1) {
        r0.standardAnimator = r1;
    }

    /* renamed from: -$$Nest$mgetDrawerOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1301$$Nest$mgetDrawerOffset(DrawerContainer r0) {
        return r0.getDrawerOffset();
    }

    /* renamed from: -$$Nest$monCloseComplete, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1302$$Nest$monCloseComplete(DrawerContainer r0) {
        r0.onCloseComplete();
    }

    /* renamed from: -$$Nest$mrestoreDrawerAbovePreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1303$$Nest$mrestoreDrawerAbovePreview(DrawerContainer r0) {
        r0.restoreDrawerAbovePreview();
    }

    /* renamed from: -$$Nest$msetDrawerOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1304$$Nest$msetDrawerOffset(DrawerContainer r0, float r1) {
        r0.setDrawerOffset(r1);
    }

    static {
        COLOR_KEY_DRAWER_BACKGROUND = Theme.key_windowBackgroundWhite;
        COLOR_KEY_POPUP_ACCENT = Theme.key_windowBackgroundWhiteBlueIcon;
        DRAWER_OFFSET = new AnonymousClass1("drawerOffset");
    }

    public DrawerContainer(Context r7) {
        super(r7);
        this.scrimPaint = new Paint();
        this.rect = new Rect();
        this.clipPath = new Path();
        this.radii = new float[8];
        this.cachedTopRightRadius = -1.0f;
        this.cachedBottomRightRadius = -1.0f;
        setVisibility(8);
        setTag("drawer_container");
        this.drawerWidth = calculateDrawerWidth();
        FrameLayout r0 = new FrameLayout(r7);
        this.drawerPanel = r0;
        r0.setBackgroundColor(Theme.getColor(COLOR_KEY_DRAWER_BACKGROUND));
        r0.setTranslationX(-this.drawerWidth);
        addView(r0, LayoutHelper.createFrame(-1, -1, 3));
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        r2.width = this.drawerWidth;
        r0.setLayoutParams(r2);
        LinearLayout r22 = new LinearLayout(r7);
        r22.setOrientation(1);
        r0.addView(r22, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r4 = new FrameLayout(r7);
        this.bulletinContainer = r4;
        r0.addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        DrawerHeaderView r02 = new DrawerHeaderView(r7);
        this.headerView = r02;
        r22.addView(r02, new LinearLayout.LayoutParams(-1, AndroidUtilities.dp(160.0f)));
        DrawerAccountPickerView r1 = new DrawerAccountPickerView(r7);
        this.accountPickerView = r1;
        r22.addView(r1, new LinearLayout.LayoutParams(-1, -2));
        DrawerMenuView r42 = new DrawerMenuView(r7);
        this.menuView = r42;
        LinearLayout.LayoutParams r72 = new LinearLayout.LayoutParams(-1, 0);
        r72.weight = 1.0f;
        r22.addView(r42, r72);
        setupCallbacks();
        r02.setChevronExpanded(r1.isExpanded());
    }

    private void animateProgress(float r3) {
        animateProgress(r3, false, 0.0f);
    }

    private void animateProgress(float r5, boolean r6, float r7) {
        cancelAnimations();
        this.isAnimating = true;
        float r1 = this.drawerWidth * r5;
        if (ExteraConfig.getSpringSwipeback() == false) goto L13;
        SpringAnimation r0 = new SpringAnimation(this, DRAWER_OFFSET);
        this.springAnimation = r0;
        SpringForce r2 = new SpringForce(r1);
        if (r6 == false) goto L7;
        float r62 = 1500.0f;
    L8:
        r0.setSpring(r2.setStiffness(r62).setDampingRatio(1.0f));
        if (r7 == 0.0f) goto L11;
        this.springAnimation.setStartVelocity(r7);
    L11:
        this.springAnimation.addEndListener(new DrawerContainer$$ExternalSyntheticLambda10(this, r1, r5));
        this.springAnimation.animateToFinalPosition(r1);
        return;
    L7:
        r62 = 950.0f;
        goto L8
    L13:
        ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{getDrawerOffset(), r1});
        this.standardAnimator = r72;
        r72.setDuration(getAnimationDuration(r1, r6));
        this.standardAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.standardAnimator.addUpdateListener(new DrawerContainer$$ExternalSyntheticLambda11(this));
        this.standardAnimator.addListener(new AnonymousClass7(this, r1, r5));
        this.standardAnimator.start();
    }

    private void applyClosedState() {
        this.progress = 0.0f;
        this.drawerPanel.setTranslationX(-this.drawerWidth);
        translateNavigationLayout(0.0f);
        if (getVisibility() == 8) goto L5;
        super.setVisibility(8);
    L5:
        this.tapClosePending = false;
    }

    private void applyDrawerPanelPadding() {
        this.drawerPanel.setPadding(0, AndroidUtilities.statusBarHeight, 0, 0);
    }

    private void beginClosedDrawerTracking(MotionEvent r4, float r5) {
        this.tracking = true;
        this.tapClosePending = false;
        if (this.isAnimating == false) goto L5;
        cancelAnimations();
    L5:
        super.setVisibility(0);
        applyDrawerPanelPadding();
        refreshContents();
        offsetTrackingStart(r4, r5);
        resetTrackingVelocity(r4);
        if (getParent() == null) goto L9;
        getParent().requestDisallowInterceptTouchEvent(true);
        return;
    }

    private void beginVisibleDrawerTracking(MotionEvent r3, float r4) {
        this.tracking = true;
        this.tapClosePending = false;
        if (this.isAnimating == false) goto L5;
        cancelAnimations();
    L5:
        offsetTrackingStart(r3, r4);
        resetTrackingVelocity(r3);
        if (getParent() == null) goto L9;
        getParent().requestDisallowInterceptTouchEvent(true);
        return;
    }

    private int calculateDrawerWidth() {
        return Math.min(AndroidUtilities.dp(300.0f), AndroidUtilities.displaySize.x - AndroidUtilities.dp(56.0f));
    }

    private boolean canOpen(MotionEvent r2) {
        BaseFragment r1 = getLastFragment();
        if ((r1 instanceof DialogsActivity) == true) goto L5;
        return false;
    L5:
        return ((DialogsActivity) r1).canOpenDrawerBySwipe(r2);
    }

    private boolean canStartClosedDrawerSwipe(MotionEvent r7) {
        if (canOpen(r7) == false) goto L29;
        ViewParent r0 = getParent();
        if ((r0 instanceof DrawerLayoutContainer) == false) goto L29;
        INavigationLayout r02 = ((DrawerLayoutContainer) r0).getParentActionBarLayout();
        if (r02 == null) goto L29;
        if (r02.getFragmentStack().size() != 1) goto L29;
        if (r02.allowSwipe() == false) goto L29;
        BaseFragment r2 = r02.getLastFragment();
        if (r2 != null) goto L16;
    L20:
        ViewGroup r03 = r02.getView();
        if (r03 != null) goto L23;
        return false;
    L23:
        r03.getHitRect(this.rect);
        if (this.rect.contains((int) r7.getX(), (int) r7.getY()) == true) goto L27;
        return false;
    L27:
        if (findScrollingChild(r03, r7.getX() - this.rect.left, r7.getY() - this.rect.top) != null) goto L29;
        return true;
    L16:
        if (r2.getLastSheet() == null) goto L20;
        if (r2.getLastSheet().attachedToParent() == false) goto L20;
        return false;
    L29:
        return false;
    }

    private void cancelAnimations() {
        SpringAnimation r0 = this.springAnimation;
        if (r0 == null) goto L5;
        this.springAnimation = null;
        r0.cancel();
    L5:
        ValueAnimator r02 = this.standardAnimator;
        if (r02 == null) goto L8;
        this.standardAnimator = null;
        r02.cancel();
    L8:
        this.isAnimating = false;
        setProgress(this.progress);
        if (this.isOpen == false) goto L11;
        return;
    L11:
        if (this.progress <= 0.001f) goto L13;
        return;
    L13:
        if (this.tracking == false) goto L15;
        return;
    L15:
        if (this.startedEdgeSwipe == true) goto L21;
        onCloseComplete();
        return;
    }

    private void dismissSelectionPopup() {
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r0 = this.selectAnimatedEmojiDialog;
        if (r0 == null) goto L6;
        r0.dismiss();
        this.selectAnimatedEmojiDialog = null;
        return;
    }

    private View findScrollingChild(ViewGroup r7, float r8, float r9) {
        int r0 = r7.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L19;
        View r2 = r7.getChildAt(r1);
        if (r2.getVisibility() != 0) goto L18;
        r2.getHitRect(this.rect);
        if (this.rect.contains((int) r8, (int) r9) == false) goto L18;
        if (r2.canScrollHorizontally(-1) == true) goto L12;
        if ((r2 instanceof ViewGroup) == false) goto L18;
        Rect r3 = this.rect;
        View r22 = findScrollingChild((ViewGroup) r2, r8 - r3.left, r9 - r3.top);
        if (r22 == null) goto L18;
        return r22;
    L12:
        return r2;
    L18:
        r1 = r1 + 1;
        goto L3
    L19:
        return null;
    }

    private void finishTracking() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L5;
        r0.computeCurrentVelocity(1000);
        float r02 = this.velocityTracker.getXVelocity();
        float r2 = this.velocityTracker.getYVelocity();
    L6:
        int r3 = AppUtils.getSwipeVelocity();
        float r4 = this.progress;
        if (this.isOpen == false) goto L9;
        float r5 = 1.25f;
    L10:
        float r52 = 1.0f / r5;
        boolean r53 = true;
        if (r4 >= r52) goto L17;
        if (r02 < r3) goto L21;
        if (Math.abs(r02) >= Math.abs(r2)) goto L17;
    L21:
        if (this.isOpen == true) goto L23;
    L25:
        r53 = false;
    L26:
        this.isOpen = false;
        animateProgress(0.0f, r53, r02);
    L34:
        VelocityTracker r03 = this.velocityTracker;
        if (r03 == null) goto L37;
        r03.recycle();
        this.velocityTracker = null;
    L37:
        this.tracking = false;
        this.startedEdgeSwipe = false;
        this.tapClosePending = false;
        return;
    L23:
        if (Math.abs(r02) < r3) goto L25;
    L17:
        if (r02 >= 0.0f) goto L28;
        if (Math.abs(r02) >= r3) goto L21;
    L28:
        if (this.isOpen == false) goto L30;
    L32:
        boolean r1 = false;
    L33:
        this.isOpen = true;
        animateProgress(1.0f, r1, r02);
        goto L34
    L30:
        if (Math.abs(r02) < r3) goto L32;
        r1 = true;
        goto L33
    L9:
        r5 = 5.0f;
        goto L10
    L5:
        r02 = 0.0f;
        r2 = 0.0f;
        goto L6
    }

    private long getAnimationDuration(float r3, boolean r4) {
        if (r4 == true) goto L5;
        return 300;
    L5:
        float r42 = getDrawerOffset();
        if (r3 <= r42) goto L9;
        r42 = this.drawerWidth - r42;
    L9:
        return Math.max((long) ((250.0f / Math.max(this.drawerWidth, 1)) * r42), 100);
    }

    private float getDrawerCloseTouchSlop() {
        return AndroidUtilities.getPixelsInCM(0.4f, true);
    }

    private float getDrawerOffset() {
        return this.drawerWidth * this.progress;
    }

    private float getDrawerOpenTouchSlop() {
        return AndroidUtilities.getPixelsInCM(0.2f, true);
    }

    private BaseFragment getLastFragment() {
        ViewGroup r1 = (ViewGroup) getParent();
        if ((r1 instanceof DrawerLayoutContainer) == false) goto L10;
        INavigationLayout r12 = ((DrawerLayoutContainer) r1).getParentActionBarLayout();
        if (r12 == null) goto L13;
        BaseFragment r13 = r12.getLastFragment();
        if ((r13 instanceof MainTabsActivity) == true) goto L9;
        return r13;
    L9:
        return ((MainTabsActivity) r13).getCurrentVisibleFragment();
    L13:
        return null;
    L10:
        return null;
    }

    private float getNavigationLayoutTranslation(float r2) {
        boolean r0 = ExteraConfig.getImmersiveDrawerAnimation();
        int r1 = this.drawerWidth;
        if (r0 == false) goto L7;
        float r12 = r1;
    L6:
        return r12 * r2;
    L7:
        r12 = r1 * r2;
        r2 = 0.3f;
        goto L6
    }

    private int getPopupWidth() {
        return (int) Math.min(AndroidUtilities.dp(324.0f), AndroidUtilities.displaySize.x * 0.95f);
    }

    private float getTrackingTouchSlop(float r2) {
        if (r2 >= 0.0f) goto L7;
        return getDrawerCloseTouchSlop();
    L7:
        return getDrawerOpenTouchSlop();
    }

    private boolean isClosingAnimationInProgress() {
        if (this.isAnimating == true) goto L5;
        return false;
    L5:
        if (this.isOpen == true) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$animateProgress$12(float r1, float r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        if (this.springAnimation != r3) goto L5;
        this.springAnimation = null;
    L5:
        if (r4 == true) goto L12;
        this.isAnimating = false;
        setDrawerOffset(r1);
        if (r2 != 0.0f) goto L11;
        onCloseComplete();
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$animateProgress$13(ValueAnimator r1) {
        setDrawerOffset(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$setupCallbacks$0() {
        this.accountPickerView.toggleExpand();
        this.headerView.setChevronExpanded(this.accountPickerView.isExpanded());
    }

    private /* synthetic */ void lambda$setupCallbacks$10(int r1, View r2) {
        showAccountPreview(r1);
    }

    private /* synthetic */ void lambda$setupCallbacks$11() {
        closeDrawer(true);
    }

    private /* synthetic */ void lambda$setupCallbacks$2() {
        if (DialogsActivity.switchingTheme == true) goto L38;
        int[] r3 = this.headerView.getThemeTogglePosition();
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r2 = "Blue";
        String r1 = r0.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r1) != null) goto L8;
    L9:
        r1 = "Blue";
    L10:
        String r5 = "Dark Blue";
        String r02 = r0.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r02) != null) goto L13;
    L14:
        r02 = "Dark Blue";
    L15:
        Theme.ThemeInfo r4 = Theme.getActiveTheme();
        if (r1.equals(r02) == true) goto L18;
        r5 = r02;
    L24:
        r2 = r1;
    L27:
        boolean r03 = r2.equals(r4.getKey());
        if (r03 == false) goto L30;
        Theme.ThemeInfo r12 = Theme.getTheme(r5);
    L31:
        if (r12 == null) goto L36;
        DialogsActivity.switchingTheme = true;
        this.headerView.animateThemeToggle(r03);
        NotificationCenter r11 = NotificationCenter.getGlobalInstance();
        int r122 = NotificationCenter.needSetDayNightTheme;
        Boolean r22 = Boolean.FALSE;
        r11.postNotificationName(r122, new Object[]{r12, r22, r3, -1, Boolean.valueOf(r03), this.headerView.getThemeToggleView(), null, null, r22, null});
        BaseFragment r13 = getLastFragment();
        if (r13 == null) goto L37;
        Theme.turnOffAutoNight(BulletinFactory.of(r13), new DrawerContainer$$ExternalSyntheticLambda14(r13));
        return;
    L37:
        return;
    L36:
        return;
    L30:
        r12 = Theme.getTheme(r2);
        goto L31
    L18:
        if (r4.isDark() == false) goto L20;
    L25:
        r5 = r02;
        goto L27
    L20:
        if (r1.equals("Dark Blue") == true) goto L25;
        if (r1.equals("Night") == false) goto L24;
    L13:
        if (Theme.getTheme(r02).isDark() == true) goto L15;
    L8:
        if (Theme.getTheme(r1).isDark() == false) goto L10;
    }

    private /* synthetic */ void lambda$setupCallbacks$3() {
        BaseFragment r2 = getLastFragment();
        if (r2 == null) goto L6;
        r2.presentFragment(new ThemeActivity(0));
        return;
    }

    private /* synthetic */ void lambda$setupCallbacks$4() {
        closeDrawer(true);
        AndroidUtilities.runOnUIThread(new DrawerContainer$$ExternalSyntheticLambda15(this), 200);
    }

    private /* synthetic */ void lambda$setupCallbacks$5() {
        BaseFragment r4 = getLastFragment();
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId());
        r0.putBoolean("my_profile", true);
        if (r4 == null) goto L6;
        r4.presentFragment(new ProfileActivity(r0));
        return;
    }

    private /* synthetic */ void lambda$setupCallbacks$6() {
        closeDrawer(true);
        AndroidUtilities.runOnUIThread(new DrawerContainer$$ExternalSyntheticLambda16(this), 200);
    }

    private /* synthetic */ void lambda$setupCallbacks$7() {
        BaseFragment r1 = getLastFragment();
        if (r1 == null) goto L6;
        r1.presentFragment(new ProxyListActivity());
        return;
    }

    private /* synthetic */ void lambda$setupCallbacks$8() {
        closeDrawer(true);
        AndroidUtilities.runOnUIThread(new DrawerContainer$$ExternalSyntheticLambda13(this), 200);
    }

    private /* synthetic */ void lambda$setupCallbacks$9() {
        closeDrawer(true);
    }

    private void offsetTrackingStart(MotionEvent r3, float r4) {
        this.startX += Math.signum(r4) * getTrackingTouchSlop(r4);
        this.startY = r3.getY();
        this.startProgress = this.progress;
    }

    private void onCloseComplete() {
        this.isOpen = false;
        this.tracking = false;
        this.startedEdgeSwipe = false;
        this.animationInterruptedByTouch = false;
        this.predictiveBackInProgress = false;
        this.predictiveBackStartProgress = 0.0f;
        setProgress(0.0f);
        this.tapClosePending = false;
        dismissSelectionPopup();
        this.menuView.clearMenu();
    }

    private void recycleVelocityTracker() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.velocityTracker = null;
        return;
    }

    private void refreshAccountViews(int r5, int r6) {
        boolean r1 = true;
        if ((MessagesController.UPDATE_MASK_AVATAR & r6) == 0) goto L5;
    L12:
        boolean r0 = true;
    L14:
        if ((MessagesController.UPDATE_MASK_AVATAR & r6) == 0) goto L16;
    L21:
        if (r0 == true) goto L23;
    L25:
        if (r1 == false) goto L28;
        this.accountPickerView.loadAccounts();
        return;
    L28:
        return;
    L23:
        if (r5 != UserConfig.selectedAccount) goto L25;
        this.headerView.updateUserInfo();
        goto L25
    L16:
        if ((MessagesController.UPDATE_MASK_NAME & r6) != 0) goto L21;
        if ((r6 & MessagesController.UPDATE_MASK_EMOJI_STATUS) != 0) goto L21;
        r1 = false;
        goto L21
    L5:
        if ((MessagesController.UPDATE_MASK_NAME & r6) != 0) goto L12;
        if ((MessagesController.UPDATE_MASK_PHONE & r6) != 0) goto L12;
        if ((MessagesController.UPDATE_MASK_EMOJI_STATUS & r6) != 0) goto L12;
        r0 = false;
        goto L14
    }

    private void refreshAccountViews(int r2, boolean r3) {
        if (r2 != UserConfig.selectedAccount) goto L5;
        this.headerView.updateUserInfo();
    L5:
        if (r3 == false) goto L8;
        this.accountPickerView.loadAccounts();
        return;
    }

    private void refreshContents() {
        this.headerView.updateUserInfo();
        this.accountPickerView.loadAccounts();
        BaseFragment r0 = getLastFragment();
        DrawerMenuView r2 = this.menuView;
        if (r0 == null) goto L6;
        r2.rebuildMenu(UserConfig.selectedAccount, r0);
        return;
    L6:
        r2.clearMenu();
    }

    private void registerNotifications() {
        if (this.notificationsRegistered == false) goto L5;
        return;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= 16) goto L9;
        NotificationCenter r1 = NotificationCenter.getInstance(r0);
        r1.addObserver(this, NotificationCenter.mainUserInfoChanged);
        r1.addObserver(this, NotificationCenter.userEmojiStatusUpdated);
        r1.addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        r1.addObserver(this, NotificationCenter.updateInterfaces);
        r1.addObserver(this, NotificationCenter.appDidLogout);
        r1.addObserver(this, NotificationCenter.attachMenuBotsDidLoad);
        r1.addObserver(this, NotificationCenter.didUpdateConnectionState);
        r0 = r0 + 1;
        goto L7
    L9:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewTheme);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.themeAccentListUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.notificationsCountUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.reloadInterface);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginMenuItemsUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxySettingsChanged);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxyPingUpdated);
        this.notificationsRegistered = true;
    }

    private void resetNavigationTranslationTarget() {
        View r0 = this.navigationTranslationTarget;
        if (r0 == null) goto L6;
        r0.setTranslationX(0.0f);
        this.navigationTranslationTarget = null;
        return;
    }

    private void resetTrackingVelocity(MotionEvent r2) {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 != null) goto L5;
        this.velocityTracker = VelocityTracker.obtain();
    L6:
        this.velocityTracker.addMovement(r2);
        return;
    L5:
        r0.clear();
        goto L6
    }

    private View resolveNavigationTranslationTarget(DrawerLayoutContainer r2, INavigationLayout r3) {
        ViewGroup r1 = r3.getView();
        Object r32 = r1.getParent();
        if ((r32 instanceof View) == false) goto L7;
        View r33 = (View) r32;
        if (r33.getParent() != r2) goto L7;
        return r33;
    L7:
        return r1;
    }

    private void restoreDrawerAbovePreview() {
        ViewParent r1 = getParent();
        if ((r1 instanceof DrawerLayoutContainer) == false) goto L6;
        ((DrawerLayoutContainer) r1).setDrawCurrentPreviewFragmentAbove(false);
        return;
    }

    private void setDrawerOffset(float r3) {
        float r0 = 0.0f;
        float r32 = Math.max(0.0f, Math.min(this.drawerWidth, r3));
        int r1 = this.drawerWidth;
        if (r1 == 0) goto L6;
        r0 = r32 / r1;
    L6:
        setProgress(r0);
    }

    private void setProgress(float r2) {
        this.progress = Math.max(0.0f, Math.min(1.0f, r2));
        syncDrawerState();
        invalidate();
    }

    private void settleInterruptedAnimation() {
        if (this.isAnimating == false) goto L5;
        return;
    L5:
        if (this.tracking == false) goto L7;
        return;
    L7:
        if (this.startedEdgeSwipe == false) goto L9;
        return;
    L9:
        if (this.predictiveBackInProgress == false) goto L12;
        return;
    L12:
        if (this.isOpen == false) goto L14;
        float r0 = 1.0f;
    L16:
        if (Math.abs(this.progress - r0) > 0.001f) goto L21;
        setProgress(r0);
        if (this.isOpen == true) goto L26;
        onCloseComplete();
        return;
    L26:
        return;
    L21:
        animateProgress(r0, true, 0.0f);
        return;
    L14:
        r0 = 0.0f;
        goto L16
    }

    private void setupCallbacks() {
        this.headerView.setOnChevronClick(new DrawerContainer$$ExternalSyntheticLambda0(this));
        this.headerView.setOnThemeToggle(new DrawerContainer$$ExternalSyntheticLambda1(this));
        this.headerView.setOnThemeToggleLongClick(new DrawerContainer$$ExternalSyntheticLambda2(this));
        this.headerView.setOnNavigateToProfile(new DrawerContainer$$ExternalSyntheticLambda3(this));
        this.headerView.setOnStatusClick(new DrawerContainer$$ExternalSyntheticLambda4(this));
        this.headerView.setOnBadgeClick(new DrawerContainer$$ExternalSyntheticLambda5(this));
        this.headerView.setOnProxyClick(new DrawerContainer$$ExternalSyntheticLambda6(this));
        this.accountPickerView.setOnAccountSelected(new DrawerContainer$$ExternalSyntheticLambda7(this));
        this.accountPickerView.setOnAccountLongClick(new DrawerContainer$$ExternalSyntheticLambda8(this));
        this.menuView.setOnItemClick(new DrawerContainer$$ExternalSyntheticLambda9(this));
    }

    private boolean shouldBlockClosedDrawerSwipe(float r3, float r4) {
        float r42 = Math.abs(r4);
        float r0 = AndroidUtilities.touchSlop;
        if (r0 > 0.0f) goto L7;
        r0 = getDrawerOpenTouchSlop();
    L7:
        if (r42 >= r0) goto L9;
        return false;
    L9:
        if (r42 <= Math.abs(r3)) goto L14;
        return true;
    L14:
        return false;
    }

    private boolean shouldPassClosingTouchThrough(MotionEvent r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.getAction() == 0) goto L6;
        return false;
    L6:
        if (r2.getX() <= (this.drawerPanel.getTranslationX() + this.drawerWidth)) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean shouldStartClosedDrawerTracking(float r2, float r3) {
        if (r2 > 0.0f) goto L5;
        return false;
    L5:
        if ((r2 / 3.0f) > r3) goto L7;
        return false;
    L7:
        if (Math.abs(r2) < getDrawerOpenTouchSlop()) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean shouldStartVisibleDrawerTracking(float r6, float r7) {
        if (r6 >= 0.0f) goto L11;
        if (Math.abs(r6) >= r7) goto L7;
    L9:
        return false;
    L7:
        if (Math.abs(r6) < getDrawerCloseTouchSlop()) goto L9;
        return true;
    L11:
        if (this.startProgress < 0.999f) goto L13;
    L19:
        return false;
    L13:
        if (r6 <= 0.0f) goto L19;
        if ((r6 / 3.0f) <= r7) goto L19;
        if (r6 < getDrawerOpenTouchSlop()) goto L19;
        return true;
    }

    private void showAccountPreview(int r6) {
        ViewParent r0 = getParent();
        if ((r0 instanceof DrawerLayoutContainer) == false) goto L11;
        DrawerLayoutContainer r02 = (DrawerLayoutContainer) r0;
        INavigationLayout r1 = r02.getParentActionBarLayout();
        if (r1 == null) goto L13;
        Bundle r2 = new Bundle();
        r2.putBoolean("drawer_account_preview", true);
        AnonymousClass6 r3 = new AnonymousClass6(this, r2, r6);
        r3.setCurrentAccount(r6);
        r3.prepareDialogsActivity(r2);
        if (r1.presentFragment(new INavigationLayout.NavigationParams(r3).setPreview(true).setCheckPresentFromDelegate(false)) == false) goto L12;
        r02.setDrawCurrentPreviewFragmentAbove(true);
        return;
    L12:
        return;
    L13:
        return;
    }

    private void showBadgeSelect() {
        BaseFragment r2 = getLastFragment();
        if (r2 == null) goto L28;
        BadgesController r0 = BadgesController.INSTANCE;
        if (r0.canChangeBadge() == true) goto L10;
        showCurrentBadgeBulletin(r2);
        return;
    L10:
        if (this.selectAnimatedEmojiDialog != null) goto L29;
        SimpleTextView r3 = this.headerView.getNameView();
        if (r3.getRightDrawable2() != null) goto L15;
        return;
    L15:
        int[] r5 = new int[2];
        r3.getLocationOnScreen(r5);
        int r7 = r5[0] + r3.rightDrawable2X;
        int r8 = getPopupWidth();
        int r82 = MathUtils.clamp(r7 - (r8 / 2), 0, AndroidUtilities.displaySize.x - r8);
        int r16 = r5[1] + r3.getHeight();
        BadgeDTO r12 = r0.getDefaultBadge();
        int r17 = UserConfig.selectedAccount;
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r14 = new SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[1];
        AnonymousClass4 r02 = new AnonymousClass4(this, r2, getContext(), true, Integer.valueOf(Math.max(0, r7 - r82)), 0, true, null, 16, Theme.getColor(COLOR_KEY_POPUP_ACCENT), true, r12, r2, r14);
        if (r12 == null) goto L18;
        r02.setDefaultBadge(Long.valueOf(r12.getDocumentId()));
    L18:
        r02.useAccentForPlus = true;
        BadgeDTO r22 = r0.getBadge(UserConfig.getInstance(r17).getCurrentUser());
        if (r22 == null) goto L25;
        if (r12 != null) goto L22;
    L24:
        r02.setSelected(Long.valueOf(r22.getDocumentId()));
    L26:
        AnonymousClass5 r23 = new AnonymousClass5(this, r02, -2, -2);
        this.selectAnimatedEmojiDialog = r23;
        r14[0] = r23;
        int[] r03 = new int[2];
        getLocationOnScreen(r03);
        r14[0].showAsDropDown(this, r82, (r16 - r03[1]) - AndroidUtilities.dp(16.0f), 51);
        r14[0].dimBehind();
        return;
    L22:
        if (r22.getDocumentId() != r12.getDocumentId()) goto L24;
        r02.setSelected(0L);
        goto L26
    L25:
        r02.setSelected(0L);
        goto L26
    L29:
        return;
    }

    private void showCurrentBadgeBulletin(BaseFragment r9) {
        TLRPC.User r3 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
        if (r3 != null) goto L5;
        return;
    L5:
        BadgesController.INSTANCE.showBadgeBulletin(r9, r3, null, UserConfig.selectedAccount, this.bulletinContainer, Boolean.FALSE);
    }

    private void showStatusSelect() {
        if (this.selectAnimatedEmojiDialog != null) goto L20;
        BaseFragment r2 = getLastFragment();
        if (r2 == null) goto L21;
        int r10 = UserConfig.selectedAccount;
        TLRPC.User r12 = UserConfig.getInstance(r10).getCurrentUser();
        if (r12 != null) goto L11;
        return;
    L11:
        if (MessagesController.getInstance(r10).isPremiumUser(r12) == false) goto L22;
        SimpleTextView r0 = this.headerView.getNameView();
        int[] r3 = new int[2];
        r0.getLocationOnScreen(r3);
        int r4 = r3[0] + r0.getRightDrawableX();
        int r5 = getPopupWidth();
        int r15 = MathUtils.clamp(r4 - (r5 / 2), 0, AndroidUtilities.displaySize.x - r5);
        int r16 = r3[1] + r0.getHeight();
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r11 = new SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[1];
        AnonymousClass2 r02 = new AnonymousClass2(this, r2, getContext(), true, Integer.valueOf(Math.max(0, r4 - r15)), 0, true, null, 16, r10, r11);
        r02.setExpireDateHint(DialogObject.getEmojiStatusUntil(r12.emoji_status));
        long r22 = DialogObject.getEmojiStatusDocumentId(r12.emoji_status);
        if (r22 == 0) goto L16;
        Long r23 = Long.valueOf(r22);
    L17:
        r02.setSelected(r23);
        r02.setSaveState(3);
        AnonymousClass3 r24 = new AnonymousClass3(this, r02, -2, -2);
        this.selectAnimatedEmojiDialog = r24;
        r11[0] = r24;
        int[] r03 = new int[2];
        getLocationOnScreen(r03);
        r11[0].showAsDropDown(this, r15, (r16 - r03[1]) - AndroidUtilities.dp(16.0f), 51);
        r11[0].dimBehind();
        return;
    L16:
        r23 = null;
        goto L17
    L22:
        return;
    L21:
        return;
    }

    private void syncDrawerState() {
        float r0 = this.progress;
        if (r0 <= 0.001f) goto L5;
    L14:
        this.drawerPanel.setTranslationX((-this.drawerWidth) * (1.0f - r0));
        float r02 = this.progress;
        if (r02 > 0.001f) goto L17;
        float r03 = 0.0f;
    L18:
        translateNavigationLayout(r03);
        if (getVisibility() == 0) goto L22;
        super.setVisibility(0);
        return;
    L22:
        return;
    L17:
        r03 = getNavigationLayoutTranslation(r02);
        goto L18
    L5:
        if (this.isAnimating == true) goto L14;
        if (this.tracking == true) goto L14;
        if (this.startedEdgeSwipe == true) goto L14;
        if (this.predictiveBackInProgress == true) goto L14;
        applyClosedState();
    }

    private void translateNavigationLayout(float r4) {
        ViewParent r0 = getParent();
        if ((r0 instanceof DrawerLayoutContainer) == false) goto L16;
        DrawerLayoutContainer r02 = (DrawerLayoutContainer) r0;
        INavigationLayout r1 = r02.getParentActionBarLayout();
        if (r1 != null) goto L8;
        resetNavigationTranslationTarget();
        return;
    L8:
        View r03 = resolveNavigationTranslationTarget(r02, r1);
        View r12 = this.navigationTranslationTarget;
        if (r12 == null) goto L12;
        if (r12 == r03) goto L12;
        r12.setTranslationX(0.0f);
    L12:
        this.navigationTranslationTarget = r03;
        if (r03 == null) goto L18;
        r03.setTranslationX(r4);
        return;
    L18:
        return;
    L16:
        resetNavigationTranslationTarget();
    }

    private void unregisterNotifications() {
        if (this.notificationsRegistered == true) goto L5;
        return;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= 16) goto L9;
        NotificationCenter r2 = NotificationCenter.getInstance(r1);
        r2.removeObserver(this, NotificationCenter.mainUserInfoChanged);
        r2.removeObserver(this, NotificationCenter.userEmojiStatusUpdated);
        r2.removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        r2.removeObserver(this, NotificationCenter.updateInterfaces);
        r2.removeObserver(this, NotificationCenter.appDidLogout);
        r2.removeObserver(this, NotificationCenter.attachMenuBotsDidLoad);
        r2.removeObserver(this, NotificationCenter.didUpdateConnectionState);
        r1 = r1 + 1;
        goto L7
    L9:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewTheme);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.themeAccentListUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.notificationsCountUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.reloadInterface);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginMenuItemsUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxySettingsChanged);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxyPingUpdated);
        this.notificationsRegistered = false;
    }

    private void updateColors() {
        this.drawerPanel.setBackgroundColor(Theme.getColor(COLOR_KEY_DRAWER_BACKGROUND));
        this.headerView.updateColors();
        this.accountPickerView.updateColors();
        this.menuView.updateColors();
        invalidate();
    }

    private void updateDrawerWidth() {
        this.drawerWidth = calculateDrawerWidth();
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.drawerPanel.getLayoutParams();
        r0.width = this.drawerWidth;
        this.drawerPanel.setLayoutParams(r0);
    }

    public void cancelPredictiveBack() {
        if (this.predictiveBackInProgress == true) goto L5;
        return;
    L5:
        boolean r0 = false;
        this.predictiveBackInProgress = false;
        if (this.predictiveBackStartProgress <= 0.001f) goto L8;
        r0 = true;
    L8:
        this.isOpen = r0;
        if (r0 == false) goto L11;
        float r02 = 1.0f;
    L12:
        animateProgress(r02, true, 0.0f);
        return;
    L11:
        r02 = 0.0f;
        goto L12
    }

    public void closeDrawer(boolean r3) {
        if (this.progress <= 0.001f) goto L5;
    L8:
        this.isOpen = false;
        if (r3 == false) goto L12;
        animateProgress(0.0f);
        return;
    L12:
        setProgress(0.0f);
        onCloseComplete();
        return;
    L5:
        if (this.isAnimating == true) goto L8;
        this.isOpen = false;
        onCloseComplete();
    }

    public void commitPredictiveBack() {
        if (this.predictiveBackInProgress == true) goto L6;
        closeDrawer(true);
        return;
    L6:
        this.predictiveBackInProgress = false;
        this.isOpen = false;
        if (this.progress > 0.001f) goto L10;
        onCloseComplete();
        return;
    L10:
        animateProgress(0.0f, true, 0.0f);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.mainUserInfoChanged) goto L5;
    L63:
        refreshAccountViews(r5, true);
        return;
    L5:
        if (r4 == NotificationCenter.userEmojiStatusUpdated) goto L63;
        if (r4 == NotificationCenter.currentUserPremiumStatusChanged) goto L63;
        if (r4 != NotificationCenter.updateInterfaces) goto L19;
        if (r6.length <= 0) goto L16;
        Object r42 = r6[0];
        if ((r42 instanceof Integer) == false) goto L16;
        refreshAccountViews(r5, ((Integer) r42).intValue());
    L16:
        this.menuView.updateUnreadCounters(UserConfig.selectedAccount);
        return;
    L19:
        if (r4 != NotificationCenter.didSetNewTheme) goto L23;
        updateColors();
        return;
    L23:
        if (r4 != NotificationCenter.themeAccentListUpdated) goto L27;
        AndroidUtilities.runOnUIThread(new DrawerContainer$$ExternalSyntheticLambda12(this));
        return;
    L27:
        if (r4 != NotificationCenter.notificationsCountUpdated) goto L31;
        this.accountPickerView.updateUnreadCounters();
        this.menuView.updateUnreadCounters(UserConfig.selectedAccount);
        return;
    L31:
        if (r4 != NotificationCenter.reloadInterface) goto L35;
        this.headerView.updateUserInfo();
        this.accountPickerView.updateUnreadCounters();
        this.menuView.updateUnreadCounters(UserConfig.selectedAccount);
        updateColors();
        return;
    L35:
        if (r4 != NotificationCenter.attachMenuBotsDidLoad) goto L43;
        if (r5 == UserConfig.selectedAccount) goto L39;
        return;
    L39:
        if (this.isOpen == false) goto L66;
        refreshContents();
        return;
    L66:
        return;
    L43:
        if (r4 != NotificationCenter.pluginMenuItemsUpdated) goto L49;
        if (this.isOpen == false) goto L67;
        refreshContents();
        return;
    L67:
        return;
    L49:
        if (r4 != NotificationCenter.proxySettingsChanged) goto L51;
    L61:
        this.headerView.updateProxyStatus();
        return;
    L51:
        if (r4 == NotificationCenter.proxyPingUpdated) goto L61;
        if (r4 == NotificationCenter.didUpdateConnectionState) goto L61;
        if (r4 != NotificationCenter.appDidLogout) goto L68;
        refreshAccountViews(r5, true);
        if (this.isOpen == false) goto L69;
        closeDrawer(false);
        return;
    L69:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        float r0 = this.progress;
        if (r0 > 0.0f) goto L6;
        super.dispatchDraw(r8);
        return;
    L6:
        float r02 = Math.max(0.0f, Math.min(1.0f, r0));
        if (ExteraConfig.getImmersiveDrawerAnimation() == true) goto L9;
    L11:
        int r03 = (int) (r02 * 102.0f);
        int r2 = 0;
        int r1 = 0;
        int r3 = 0;
    L12:
        this.scrimPaint.setColor(Color.argb(r03, r2, r3, r1));
        r8.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.scrimPaint);
        super.dispatchDraw(r8);
        return;
    L9:
        if (AndroidUtilities.isTablet() == true) goto L11;
        r03 = (int) (r02 * 160.0f);
        int r12 = Theme.getColor(COLOR_KEY_DRAWER_BACKGROUND);
        r2 = Color.red(r12);
        r3 = Color.green(r12);
        r1 = Color.blue(r12);
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        boolean r0 = super.dispatchTouchEvent(r3);
        int r32 = r3.getActionMasked();
        if (this.animationInterruptedByTouch == true) goto L5;
    L9:
        return r0;
    L5:
        if (r32 != 1) goto L7;
    L8:
        this.animationInterruptedByTouch = false;
        settleInterruptedAnimation();
        goto L9
    L7:
        if (r32 != 3) goto L9;
        goto L8
    }

    public void dispose() {
        cancelAnimations();
        onCloseComplete();
        recycleVelocityTracker();
        this.accountPickerView.dispose();
        unregisterNotifications();
        resetNavigationTranslationTarget();
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r7, View r8, long r9) {
        if (r8 != this.drawerPanel) goto L17;
        if (ExteraConfig.getImmersiveDrawerAnimation() == true) goto L17;
        float r0 = this.cachedTopRightRadius;
        if (r0 >= 0.0f) goto L10;
        r0 = AndroidUtilities.dp(24.0f);
    L10:
        float r2 = this.cachedBottomRightRadius;
        if (r2 >= 0.0f) goto L14;
        r2 = AndroidUtilities.dp(24.0f);
    L14:
        float[] r3 = this.radii;
        r3[1] = 0.0f;
        r3[0] = 0.0f;
        r3[3] = r0;
        r3[2] = r0;
        r3[5] = r2;
        r3[4] = r2;
        r3[7] = 0.0f;
        r3[6] = 0.0f;
        RectF r02 = AndroidUtilities.rectTmp;
        r02.set(r8.getX(), r8.getY(), r8.getX() + r8.getWidth(), r8.getY() + r8.getHeight());
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r02, this.radii, Path.Direction.CW);
        int r03 = r7.save();
        r7.clipPath(this.clipPath);
        boolean r6 = super.drawChild(r7, r8, r9);
        r7.restoreToCount(r03);
        return r6;
    L17:
        return super.drawChild(r7, r8, r9);
    }

    public boolean handleEdgeSwipeIntercept(MotionEvent r6) {
        if (ExteraConfig.getNavigationDrawer() == true) goto L6;
        return false;
    L6:
        if (r6.getAction() != 0) goto L15;
        this.startX = r6.getX();
        this.startY = r6.getY();
        this.startProgress = this.progress;
        this.startedEdgeSwipe = false;
        this.tracking = false;
        if (canStartClosedDrawerSwipe(r6) == false) goto L13;
        this.startedEdgeSwipe = true;
        if (this.velocityTracker != null) goto L12;
        this.velocityTracker = VelocityTracker.obtain();
    L12:
        this.velocityTracker.clear();
        this.velocityTracker.addMovement(r6);
    L13:
        return false;
    L15:
        if (this.startedEdgeSwipe == false) goto L34;
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L20;
        r0.addMovement(r6);
    L20:
        if (r6.getAction() != 2) goto L30;
        float r02 = r6.getX() - this.startX;
        float r3 = r6.getY() - this.startY;
        if (shouldBlockClosedDrawerSwipe(r02, r3) == false) goto L26;
        this.startedEdgeSwipe = false;
        return false;
    L26:
        if (shouldStartClosedDrawerTracking(r02, Math.abs(r3)) == false) goto L30;
        beginClosedDrawerTracking(r6, r02);
        return true;
    L30:
        if (r6.getAction() != 1) goto L32;
    L33:
        this.startedEdgeSwipe = false;
        goto L34
    L32:
        if (r6.getAction() == 3) goto L33;
    L34:
        return false;
    }

    public boolean handleEdgeSwipeTouch(MotionEvent r5) {
        if (ExteraConfig.getNavigationDrawer() == true) goto L6;
        return false;
    L6:
        if (this.startedEdgeSwipe == true) goto L11;
        if (this.tracking == true) goto L11;
        return false;
    L11:
        if (this.velocityTracker != null) goto L13;
        this.velocityTracker = VelocityTracker.obtain();
    L13:
        this.velocityTracker.addMovement(r5);
        int r0 = r5.getAction();
        if (r0 == 1) goto L25;
        if (r0 == 2) goto L21;
        if (r0 == 3) goto L25;
        return true;
    L21:
        if (this.tracking == false) goto L23;
        setProgress(Math.max(0.0f, Math.min(1.0f, this.startProgress + ((r5.getX() - this.startX) / this.drawerWidth))));
    L23:
        return true;
    L25:
        if (this.tracking == false) goto L27;
        finishTracking();
    L27:
        this.startedEdgeSwipe = false;
        return true;
    }

    public boolean isDrawerOpen() {
        if (this.progress <= 0.001f) goto L5;
        return true;
    L5:
        if (this.isAnimating == false) goto L7;
        return true;
    L7:
        if (this.predictiveBackInProgress == true) goto L14;
        return false;
    L14:
        return true;
    }

    public void onAccountChanged() {
        refreshContents();
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets r5) {
        if (ExteraConfig.getImmersiveDrawerAnimation() == true) goto L16;
        float r0 = AndroidUtilities.dp(24.0f);
        if (Build.VERSION.SDK_INT < 31) goto L14;
        RoundedCorner r1 = r5.getRoundedCorner(1);
        RoundedCorner r2 = r5.getRoundedCorner(2);
        if (r1 == null) goto L9;
        float r12 = Math.max(r0, r1.getRadius() / 2.0f);
    L10:
        this.cachedTopRightRadius = r12;
        if (r2 == null) goto L13;
        r0 = Math.max(r0, r2.getRadius() / 2.0f);
    L13:
        this.cachedBottomRightRadius = r0;
        goto L16
    L9:
        r12 = r0;
        goto L10
    L14:
        this.cachedTopRightRadius = r0;
        this.cachedBottomRightRadius = r0;
    L16:
        return super.onApplyWindowInsets(r5);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerNotifications();
        Bulletin.addDelegate(this.bulletinContainer, new AnonymousClass8(this));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bulletin.removeDelegate(this.bulletinContainer);
        cancelAnimations();
        onCloseComplete();
        dismissSelectionPopup();
        recycleVelocityTracker();
        this.accountPickerView.dispose();
        resetNavigationTranslationTarget();
        unregisterNotifications();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r6) {
        if (isClosingAnimationInProgress() == false) goto L7;
        return !shouldPassClosingTouchThrough(r6);
    L7:
        if (r6.getAction() != 0) goto L20;
        if (this.isAnimating == false) goto L11;
        cancelAnimations();
        this.animationInterruptedByTouch = true;
    L11:
        this.startX = r6.getX();
        this.startY = r6.getY();
        this.startProgress = this.progress;
        this.tracking = false;
        float r0 = this.drawerPanel.getTranslationX() + this.drawerWidth;
        if (r6.getX() <= r0) goto L14;
        boolean r3 = true;
    L15:
        this.tapClosePending = r3;
        if (r6.getX() <= r0) goto L18;
        return true;
    L18:
        return false;
    L14:
        r3 = false;
        goto L15
    L20:
        if (r6.getAction() != 2) goto L25;
        float r02 = r6.getX() - this.startX;
        if (shouldStartVisibleDrawerTracking(r02, Math.abs(r6.getY() - this.startY)) == false) goto L25;
        beginVisibleDrawerTracking(r6, r02);
        return true;
    L25:
        return false;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        updateDrawerWidth();
        setProgress(this.progress);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r5) {
        if (isClosingAnimationInProgress() == false) goto L7;
        return !shouldPassClosingTouchThrough(r5);
    L7:
        if (this.velocityTracker != null) goto L9;
        this.velocityTracker = VelocityTracker.obtain();
    L9:
        this.velocityTracker.addMovement(r5);
        int r0 = r5.getAction();
        boolean r2 = false;
        if (r0 == 0) goto L39;
        if (r0 == 1) goto L27;
        if (r0 == 2) goto L18;
        if (r0 == 3) goto L27;
    L37:
        return true;
    L18:
        if (this.tracking == true) goto L23;
        float r02 = r5.getX() - this.startX;
        if (shouldStartVisibleDrawerTracking(r02, Math.abs(r5.getY() - this.startY)) == false) goto L23;
        beginVisibleDrawerTracking(r5, r02);
    L23:
        if (this.tracking == false) goto L37;
        setProgress(Math.max(0.0f, Math.min(1.0f, this.startProgress + ((r5.getX() - this.startX) / this.drawerWidth))));
        return true;
    L27:
        if (this.tracking == false) goto L31;
        finishTracking();
        return true;
    L31:
        if (r5.getAction() == 1) goto L33;
    L36:
        this.tapClosePending = false;
        goto L37
    L33:
        if (this.tapClosePending == false) goto L36;
        this.tapClosePending = false;
        closeDrawer(true);
        return true;
    L39:
        if (this.isAnimating == false) goto L41;
        cancelAnimations();
        this.animationInterruptedByTouch = true;
    L41:
        this.startX = r5.getX();
        this.startY = r5.getY();
        this.startProgress = this.progress;
        this.tracking = false;
        if (r5.getX() <= (this.drawerPanel.getTranslationX() + this.drawerWidth)) goto L44;
        r2 = true;
    L44:
        this.tapClosePending = r2;
        return true;
    }

    public void openDrawer(boolean r4) {
        if (ExteraConfig.getNavigationDrawer() == true) goto L7;
        onCloseComplete();
        return;
    L7:
        if (this.progress >= 0.999f) goto L9;
    L12:
        this.isOpen = true;
        updateDrawerWidth();
        super.setVisibility(0);
        applyDrawerPanelPadding();
        refreshContents();
        if (r4 == false) goto L16;
        animateProgress(1.0f);
        return;
    L16:
        setProgress(1.0f);
        return;
    L9:
        if (this.isAnimating == true) goto L12;
        this.isOpen = true;
        setProgress(1.0f);
    }

    public boolean startPredictiveBack() {
        if (this.predictiveBackInProgress == false) goto L5;
    L19:
        return false;
    L5:
        if (this.tracking == true) goto L19;
        if (this.startedEdgeSwipe == true) goto L19;
        if (getVisibility() != 0) goto L19;
        if (this.isAnimating == false) goto L14;
        cancelAnimations();
    L14:
        float r0 = this.progress;
        if (r0 > 0.001f) goto L17;
        return false;
    L17:
        this.predictiveBackInProgress = true;
        this.predictiveBackStartProgress = r0;
        this.tapClosePending = false;
        super.setVisibility(0);
        return true;
    }

    public void toggleDrawer() {
        if (isDrawerOpen() == false) goto L6;
        closeDrawer(true);
        return;
    L6:
        openDrawer(true);
    }

    public void updatePredictiveBackProgress(float r4) {
        if (this.predictiveBackInProgress == true) goto L5;
        return;
    L5:
        float r42 = Math.max(0.0f, Math.min(1.0f, r4));
        setProgress(this.predictiveBackStartProgress * (1.0f - (r42 * 0.5f)));
    }
}
