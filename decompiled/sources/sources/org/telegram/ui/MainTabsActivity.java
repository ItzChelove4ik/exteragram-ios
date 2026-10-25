package org.telegram.ui;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.MainMenuItem;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.feed.FeedController;
import com.exteragram.messenger.feed.ui.FeedActivity;
import com.exteragram.messenger.feed.ui.FeedChannelsActivity;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.chats.MainMenuHelper;
import com.exteragram.messenger.utils.ui.AccountsUiHelper;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EdgeToEdgeSupportMode;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.HintsController;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProxyDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.RenderNodeWithHash;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.glass.GlassTabView;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.ViewPagerActivity;

/* loaded from: classes3.dex */
public class MainTabsActivity extends ViewPagerActivity implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private HintView2 accountSwitchHint;
    private boolean accountSwitchHintShown;
    private final BoolAnimator animatorTabsVisible;
    private int currentConnectionState;
    private DialogsActivity dialogsActivity;
    private boolean dropCallsFragmentAfterPageScroll;
    private View fadeView;
    private final RectF fragmentPosition;
    private BlurredBackgroundSourceColor iBlur3SourceColor;
    private BlurredBackgroundSourceRenderNode iBlur3SourceTabGlass;
    private int insetLeft;
    private int insetRight;
    private int navigationBarHeight;
    private NotificationCenter.ObserversGroup observersGroup;
    private ProxyDrawable proxyDrawable;
    private ActionBarMenuSubItem proxyMenuSubItem;
    private boolean tabletLayout;
    public GlassTabView[] tabs;
    private MainTabsLayout tabsView;
    private BlurredBackgroundDrawable tabsViewBackground;
    private FrameLayout tabsViewWrapper;
    private IUpdateLayout updateLayout;
    private UpdateLayoutWrapper updateLayoutWrapper;
    private ViewPositionWatcher viewPositionWatcher;

    /* renamed from: org.telegram.ui.MainTabsActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ MainTabsActivity this$0;

        public AnonymousClass1(MainTabsActivity r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r7) {
            r7.add(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            r7.add(SharedConfig.chatBlurEnabled());
            int r0 = this.this$0.fragmentsArr.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L21;
            BaseFragment r2 = this.this$0.fragmentsArr.valueAt(r1).fragment;
            View r3 = r2.fragmentView;
            if (r3 == null) goto L20;
            MainTabsActivity r4 = this.this$0;
            if (ViewPositionWatcher.computeRectInParent(r3, r4.contentView, MainTabsActivity.m16029$$Nest$fgetfragmentPosition(r4)) == false) goto L20;
            if (MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).right <= 0.0f) goto L20;
            if (MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).left >= this.this$0.fragmentView.getMeasuredWidth()) goto L20;
            if ((r2 instanceof TabFragmentDelegate) == false) goto L20;
            if (((TabFragmentDelegate) r2).getGlassSource() == null) goto L20;
            r7.addF(MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).left);
            r7.addF(MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).top);
            r7.add(r2.getClassGuid());
        L20:
            r1 = r1 + 1;
            goto L3
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r12) {
            int r0 = this.this$0.fragmentView.getMeasuredWidth();
            int r1 = this.this$0.fragmentView.getMeasuredHeight();
            r12.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            int r2 = this.this$0.fragmentsArr.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L21;
            BaseFragment r4 = this.this$0.fragmentsArr.valueAt(r3).fragment;
            View r5 = r4.fragmentView;
            if (r5 != null) goto L7;
        L6:
            Canvas r6 = r12;
        L20:
            r3 = r3 + 1;
            r12 = r6;
            goto L3
        L7:
            MainTabsActivity r62 = this.this$0;
            if (ViewPositionWatcher.computeRectInParent(r5, r62.contentView, MainTabsActivity.m16029$$Nest$fgetfragmentPosition(r62)) == false) goto L6;
            if (MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).right <= 0.0f) goto L6;
            if (MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).left >= this.this$0.fragmentView.getMeasuredWidth()) goto L6;
            if ((r4 instanceof TabFragmentDelegate) == false) goto L6;
            BlurredBackgroundSourceRenderNode r52 = ((TabFragmentDelegate) r4).getGlassSource();
            if (r52 == null) goto L6;
            r12.save();
            r12.translate(MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).left, MainTabsActivity.m16029$$Nest$fgetfragmentPosition(this.this$0).top);
            r6 = r12;
            r52.draw(r6, 0.0f, 0.0f, r0, r1);
            r6.restore();
            goto L20
        }
    }

    /* renamed from: org.telegram.ui.MainTabsActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements Bulletin.Delegate {
        final /* synthetic */ MainTabsActivity this$0;

        public AnonymousClass2(MainTabsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public boolean bottomOffsetAnimated() {
            return !BottomNavigationBar.floating();
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r2) {
            if (MainTabsActivity.m16039$$Nest$misBottomTabsEnabled(this.this$0) == false) goto L5;
            int r22 = Math.round(AndroidUtilities.dp(MainTabsUiHelper.getTabsFabOffsetDp()) * MainTabsActivity.m16028$$Nest$fgetanimatorTabsVisible(this.this$0).getFloatValue());
        L7:
            return MainTabsActivity.m16032$$Nest$fgetnavigationBarHeight(this.this$0) + r22;
        L5:
            r22 = 0;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.MainTabsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ MainTabsActivity this$0;

        public AnonymousClass3(MainTabsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r15) {
            int r0 = MainTabsActivity.m16038$$Nest$mgetEstBackgroundColor(this.this$0);
            if (MainTabsActivity.m16030$$Nest$fgetinsetLeft(this.this$0) == 0) goto L5;
            Canvas r2 = r15;
            r2.drawRect(0.0f, 0.0f, MainTabsActivity.m16030$$Nest$fgetinsetLeft(this.this$0), getHeight(), Theme.fillingPaint(r0));
        L7:
            if (MainTabsActivity.m16031$$Nest$fgetinsetRight(this.this$0) == 0) goto L9;
            r2.drawRect(getWidth() - MainTabsActivity.m16031$$Nest$fgetinsetRight(this.this$0), 0.0f, getWidth(), getHeight(), Theme.fillingPaint(r0));
        L9:
            super.dispatchDraw(r2);
            MainTabsActivity.m16034$$Nest$mblur3_invalidateBlur(this.this$0);
            MainTabsActivity.m16035$$Nest$mblur3_updateFadeColors(this.this$0);
            return;
        L5:
            r2 = r15;
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            MainTabsActivity.m16037$$Nest$mcheckUi_tabsPosition(this.this$0);
            MainTabsActivity.m16036$$Nest$mcheckUi_fadeView(this.this$0);
        }
    }

    public class MainTabsActivityControllerImpl implements MainTabsActivityController {
        private final int ownerPosition;
        final /* synthetic */ MainTabsActivity this$0;

        private MainTabsActivityControllerImpl(MainTabsActivity r1, int r2) {
            this.this$0 = r1;
            this.ownerPosition = r2;
        }

        public /* synthetic */ MainTabsActivityControllerImpl(MainTabsActivity r1, int r2, MainTabsActivityIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.ui.MainTabsActivityController
        public boolean openAccountSelector(View r1, View r2) {
            if (r1 != null) goto L6;
            return false;
        L6:
            return MainTabsActivity.m16040$$Nest$mopenAccountSelector(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.MainTabsActivityController
        public void setTabsVisible(boolean r3) {
            ViewPagerActivity.ViewPagerActivityPagerLayout r0 = this.this$0.viewPager;
            if (r0 != null) goto L5;
        L7:
            MainTabsLayout r02 = MainTabsActivity.m16033$$Nest$fgettabsView(this.this$0);
            MainTabsActivity r1 = this.this$0;
            if (r02 != null) goto L15;
            MainTabsActivity.m16028$$Nest$fgetanimatorTabsVisible(r1).changeValueSilently(r3);
            BoolAnimator r2 = MainTabsActivity.m16028$$Nest$fgetanimatorTabsVisible(this.this$0);
            if (r3 == false) goto L12;
            float r32 = 1.0f;
        L13:
            r2.changeValueSilently(r32);
            return;
        L12:
            r32 = 0.0f;
            goto L13
        L15:
            MainTabsActivity.m16028$$Nest$fgetanimatorTabsVisible(r1).setValue(r3, true);
            return;
        L5:
            if (r0.getTargetPosition() == this.ownerPosition) goto L7;
        }
    }

    public interface TabFragmentDelegate {
        default boolean canParentTabsSlide(MotionEvent r1, boolean r2) {
            return false;
        }

        BlurredBackgroundSourceRenderNode getGlassSource();

        default void onParentBecomeFullyVisible() {
        }

        void onParentScrollToTop();

        default void setParentTabsGlassInvalidationCallback(Runnable r1) {
        }

        default void updateMainTabsVisibility() {
        }
    }

    /* renamed from: $r8$lambda$-rw-bwE1sTyaNZ5c-JEv1t6B_jE, reason: not valid java name */
    public static /* synthetic */ boolean m16024$r8$lambda$rwbwE1sTyaNZ5cJEv1t6B_jE(MainTabsActivity r0, View r1) {
        return r0.lambda$createView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$0zhOzV2ZdjlFtZiE31fIjSBIU7g(MainTabsActivity r0) {
        r0.lambda$openAccountSelectorInternal$16();
    }

    public static /* synthetic */ boolean $r8$lambda$1RLZ09LG7UTwBssEhRFjQbkqHnc(MainTabsActivity r0, View r1) {
        return r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$1dUy9CW7v10qxhEAx_vTnxnLbHQ(MainTabsActivity r0) {
        r0.dumpCanvas();
    }

    public static /* synthetic */ void $r8$lambda$6hmGOTZPBMcQ1RCpnn9fOQphOac(MainTabsActivity r0) {
        r0.lambda$openSettingsTabOptions$21();
    }

    public static /* synthetic */ void $r8$lambda$7cepT3ubsddVzWg51dNQIVv9pWs(MainTabsActivity r0) {
        r0.invalidateTabsGlass();
    }

    public static /* synthetic */ void $r8$lambda$82VPqT0ThJBmuq1_ORNIXNFzSLk(MainTabsActivity r0) {
        r0.lambda$createView$3();
    }

    /* renamed from: $r8$lambda$AwUQmE_B2xb-zRkzLznpJDr5ZEE, reason: not valid java name */
    public static /* synthetic */ void m16025$r8$lambda$AwUQmE_B2xbzRkzLznpJDr5ZEE(MainTabsActivity r0, int r1, View r2) {
        r0.lambda$createView$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Oa8mLnWKip_m36QWA8jWLeW3x6M(MainTabsActivity r0) {
        r0.lambda$openCallsSelector$14();
    }

    public static /* synthetic */ void $r8$lambda$Q2PkilwbN2vT22qjLbs9nQc_hXI(MainTabsActivity r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$Q4I9DGC7gP8vlw283qiRgK4YPfQ(MainTabsActivity r0, ItemOptions r1) {
        r0.lambda$showFiltersMenu$19(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$QOV7jKE6_HebEDLsntk6kShGZBk(int r1) {
        if (r1 != MainMenuItem.SETTINGS.getId()) goto L5;
        return true;
    L5:
        if (r1 != MainMenuItem.PROFILE.getId()) goto L7;
        return true;
    L7:
        if (r1 != MainMenuItem.ARCHIVE.getId()) goto L9;
        return true;
    L9:
        if (r1 != MainMenuItem.SAVED.getId()) goto L11;
        return true;
    L11:
        if (ExteraConfig.getShowFeedTab() == true) goto L13;
        return false;
    L13:
        if (r1 == MainMenuItem.FEED.getId()) goto L23;
        return false;
    L23:
        return true;
    }

    public static /* synthetic */ void $r8$lambda$SVNUdgWjka1sBB7rn6S7ZsUcei0(MainTabsActivity r0) {
        r0.lambda$showAccountChangeHint$26();
    }

    public static /* synthetic */ void $r8$lambda$Uz2lf8FcWbSrV76M19JkAK_la3k(MainTabsActivity r0) {
        r0.lambda$createView$7();
    }

    public static /* synthetic */ void $r8$lambda$V9lKpsG4uIrZwz3u2nNWIhWOkmw(MainTabsActivity r0, ItemOptions r1, View r2) {
        r0.lambda$openSettingsTabOptions$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VQ3vkWcpIq_WvZis1HfUuxsyLKo(MainTabsActivity r0) {
        r0.lambda$createView$8();
    }

    public static /* synthetic */ void $r8$lambda$Vqs7unwEi7lP1Xo9a1xkqhRRIaw(MainTabsActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$_3rSTdkr43yheUd6aMjhDdzE3ow(MainTabsActivity r0) {
        r0.lambda$openCallsSelector$12();
    }

    public static /* synthetic */ void $r8$lambda$aHFDo81gPY7cPCC37oubc5Fwlow(MainTabsActivity r0, ItemOptions r1) {
        r0.lambda$openAccountSelectorInternal$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$aWrUvoPZTRFzYihx8ND71w5OAaI(MainTabsActivity r0) {
        r0.lambda$openCallsSelector$13();
    }

    /* renamed from: $r8$lambda$az-Yf27FVFuJYPWUUXyCwD2YpFQ, reason: not valid java name */
    public static /* synthetic */ void m16026$r8$lambda$azYf27FVFuJYPWUUXyCwD2YpFQ(MainTabsActivity r0) {
        r0.lambda$openSettingsTabOptions$22();
    }

    public static /* synthetic */ void $r8$lambda$b5ueJ_W3iFQr_Ix_ZKDYeNYgFRU(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$gk_qBIvEFvERvfqT0GrDwLuxrSQ(MainTabsActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$kytnmLb_hh6RJGHQKsqAXmgcvgs(MainTabsActivity r0, ItemOptions r1, int r2, View r3) {
        r0.lambda$showFiltersMenu$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nQbJRMDw3YnrhKqE_9ykeZkxnO8(MainTabsActivity r0) {
        r0.blur3_updateColors();
    }

    public static /* synthetic */ boolean $r8$lambda$rH0wCodmeg8A7WFuxz7qIVL0eU4(MainTabsActivity r0, View r1) {
        return r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$sY9sVhyoLmpCs-fBBy5Xn7oF1Qg, reason: not valid java name */
    public static /* synthetic */ void m16027$r8$lambda$sY9sVhyoLmpCsfBBy5Xn7oF1Qg(MainTabsActivity r0, View r1) {
        r0.lambda$openSettingsTabOptions$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$wbsIm8K0JZIOKdWm0M44R1ljSqU(MainTabsActivity r0) {
        r0.lambda$showAccountChangeHint$27();
    }

    public static /* synthetic */ boolean $r8$lambda$xkMeHX6JfrRR5n6e1M7h7v4GfAc(MainTabsActivity r0, View r1) {
        return r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$yq9AYxSZo7lfaBGtrzqXX6e9iLQ(MainTabsActivity r0, ItemOptions r1) {
        r0.lambda$showFiltersMenu$20(r1);
    }

    /* renamed from: -$$Nest$fgetanimatorTabsVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m16028$$Nest$fgetanimatorTabsVisible(MainTabsActivity r0) {
        return r0.animatorTabsVisible;
    }

    /* renamed from: -$$Nest$fgetfragmentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m16029$$Nest$fgetfragmentPosition(MainTabsActivity r0) {
        return r0.fragmentPosition;
    }

    /* renamed from: -$$Nest$fgetinsetLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16030$$Nest$fgetinsetLeft(MainTabsActivity r0) {
        return r0.insetLeft;
    }

    /* renamed from: -$$Nest$fgetinsetRight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16031$$Nest$fgetinsetRight(MainTabsActivity r0) {
        return r0.insetRight;
    }

    /* renamed from: -$$Nest$fgetnavigationBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16032$$Nest$fgetnavigationBarHeight(MainTabsActivity r0) {
        return r0.navigationBarHeight;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MainTabsLayout m16033$$Nest$fgettabsView(MainTabsActivity r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$mblur3_invalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16034$$Nest$mblur3_invalidateBlur(MainTabsActivity r0) {
        r0.blur3_invalidateBlur();
    }

    /* renamed from: -$$Nest$mblur3_updateFadeColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16035$$Nest$mblur3_updateFadeColors(MainTabsActivity r0) {
        r0.blur3_updateFadeColors();
    }

    /* renamed from: -$$Nest$mcheckUi_fadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16036$$Nest$mcheckUi_fadeView(MainTabsActivity r0) {
        r0.checkUi_fadeView();
    }

    /* renamed from: -$$Nest$mcheckUi_tabsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16037$$Nest$mcheckUi_tabsPosition(MainTabsActivity r0) {
        r0.checkUi_tabsPosition();
    }

    /* renamed from: -$$Nest$mgetEstBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16038$$Nest$mgetEstBackgroundColor(MainTabsActivity r0) {
        return r0.getEstBackgroundColor();
    }

    /* renamed from: -$$Nest$misBottomTabsEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16039$$Nest$misBottomTabsEnabled(MainTabsActivity r0) {
        return r0.isBottomTabsEnabled();
    }

    /* renamed from: -$$Nest$mopenAccountSelector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16040$$Nest$mopenAccountSelector(MainTabsActivity r0, View r1, View r2) {
        return r0.openAccountSelector(r1, r2);
    }

    public MainTabsActivity() {
        this(null);
    }

    public MainTabsActivity(Bundle r8) {
        this.animatorTabsVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380, true);
        this.fragmentPosition = new RectF();
        this.arguments = r8;
        initBlurSources();
    }

    private void blur3_invalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.iBlur3SourceTabGlass == null) goto L12;
        View r0 = this.fragmentView;
        if (r0 == null) goto L13;
        int r02 = r0.getMeasuredWidth();
        int r1 = this.fragmentView.getMeasuredHeight();
        this.iBlur3SourceTabGlass.setSize(r02, r1);
        this.iBlur3SourceTabGlass.updateDisplayListIfNeeded();
        return;
    L13:
        return;
    }

    private void blur3_updateColors() {
        blur3_updateFadeColors();
        BlurredBackgroundDrawable r0 = this.tabsViewBackground;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
        blur3_invalidateBlur();
        View r02 = this.fadeView;
        if (r02 == null) goto L8;
        r02.invalidate();
    L8:
        MainTabsLayout r03 = this.tabsView;
        if (r03 == null) goto L11;
        r03.invalidate();
    L11:
        GlassTabView[] r3 = this.tabs;
        if (r3 == null) goto L16;
        int r04 = r3.length;
        int r1 = 0;
    L14:
        if (r1 >= r04) goto L18;
        r3[r1].updateColorsLottie();
        r1 = r1 + 1;
        goto L14
    L18:
        return;
    }

    private void blur3_updateFadeColors() {
        this.iBlur3SourceColor.setColor(getEstBackgroundColor());
        View r2 = this.fadeView;
        if (r2 == null) goto L6;
        r2.invalidate();
        return;
    }

    private boolean canScrollInternal(MotionEvent r3, boolean r4) {
        if (isBottomTabsEnabled() == true) goto L5;
        return false;
    L5:
        Object r2 = getCurrentVisibleFragment();
        if ((r2 instanceof TabFragmentDelegate) == true) goto L8;
        return false;
    L8:
        return ((TabFragmentDelegate) r2).canParentTabsSlide(r3, r4);
    }

    private void checkContactsTabBadge() {
        if (this.tabsView != null) goto L5;
        return;
    L5:
        if (this.tabs[1] == null) goto L19;
        boolean r0 = ContactsController.hasContactsPermission();
        if (r0 == false) goto L10;
        MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts2", true).apply();
    L10:
        if (UserConfig.getInstance(this.currentAccount).syncContacts == false) goto L16;
        if (r0 == true) goto L16;
        if (MessagesController.getGlobalNotificationsSettings().getBoolean("askAboutContacts2", true) == false) goto L16;
        this.tabs[1].setCounter("!", true, true);
        return;
    L16:
        this.tabs[1].setCounter(null, true, true);
        return;
    }

    private void checkUi_callTabVisible(boolean r4, boolean r5) {
        MainTabsLayout r0 = this.tabsView;
        if (r0 == null) goto L6;
        r0.setViewVisible(this.tabs[2], !r4, r5);
        this.tabsView.setViewVisible(this.tabs[3], r4, r5);
        return;
    }

    private void checkUi_contactsOrFeedTabVisible(boolean r5) {
        if (this.tabsView == null) goto L12;
        boolean r0 = isFeedTabEnabled();
        this.tabsView.setViewVisible(this.tabs[5], r0, r5);
        MainTabsLayout r1 = this.tabsView;
        boolean r3 = true;
        GlassTabView r2 = this.tabs[1];
        if (r0 == false) goto L7;
    L9:
        r3 = false;
    L10:
        r1.setViewVisible(r2, r3, r5);
        return;
    L7:
        if (getUserConfig().showContactsTab == false) goto L9;
    }

    private void checkUi_fadeView() {
        if (this.viewPager != null) goto L5;
        return;
    L5:
        if (this.fadeView == null) goto L20;
        int r1 = 8;
        if (isBottomTabsEnabled() == true) goto L11;
        this.fadeView.setAlpha(0.0f);
        this.fadeView.setVisibility(8);
        return;
    L11:
        float r0 = 1.0f - MathUtils.clamp(Math.abs(getPositionProfile() - this.viewPager.getPositionAnimated()), 0.0f, 1.0f);
        float r3 = (1.0f - ((1.0f - AndroidUtilities.getNavigationBarThirdButtonsFactor(0.0f, 1.0f, this.navigationBarHeight)) * r0)) * this.animatorTabsVisible.getFloatValue();
        if (this.tabletLayout == false) goto L14;
        r3 = 0.0f;
    L14:
        this.fadeView.setAlpha(r3);
        this.fadeView.setTranslationY(r0 * AndroidUtilities.dp(48.0f));
        View r6 = this.fadeView;
        if (r3 <= 0.0f) goto L17;
        r1 = 0;
    L17:
        r6.setVisibility(r1);
        return;
    }

    private void checkUi_tabsPosition() {
        if (this.tabsView != null) goto L5;
        return;
    L5:
        if (this.tabsViewWrapper != null) goto L7;
        return;
    L7:
        if (this.updateLayoutWrapper == null) goto L32;
        int r1 = 8;
        if (isBottomTabsEnabled() == true) goto L14;
        this.tabsView.setClickable(false);
        this.tabsView.setEnabled(false);
        this.tabsView.setAlpha(0.0f);
        this.tabsView.setVisibility(8);
        return;
    L14:
        if (this.updateLayoutWrapper.isUpdateLayoutVisible() == false) goto L16;
        int r0 = AndroidUtilities.dp(44.0f);
    L17:
        int r4 = AndroidUtilities.dp(40.0f) + (-r0);
        float r5 = this.animatorTabsVisible.getFloatValue();
        AndroidUtilities.lerp(0.85f, 1.0f, r5);
        this.tabsViewWrapper.setTranslationY(AndroidUtilities.lerp(r4, r0, r5));
        MainTabsLayout r02 = this.tabsView;
        boolean r6 = true;
        if (r5 <= 0.5f) goto L20;
        boolean r7 = true;
    L21:
        r02.setClickable(r7);
        MainTabsLayout r03 = this.tabsView;
        if (r5 > 0.5f) goto L25;
        r6 = false;
    L25:
        r03.setEnabled(r6);
        this.tabsView.setAlpha(r5);
        MainTabsLayout r8 = this.tabsView;
        if (r5 <= 0.0f) goto L28;
        r1 = 0;
    L28:
        r8.setVisibility(r1);
        return;
    L20:
        r7 = false;
        goto L21
    L16:
        r0 = 0;
        goto L17
    }

    private void checkUnreadCount(boolean r7) {
        if (this.tabsView == null) goto L20;
        int r0 = MessagesStorage.getInstance(this.currentAccount).getMainUnreadCount();
        String r1 = null;
        if (r0 <= 0) goto L8;
        this.tabs[0].setCounter(LocaleController.formatNumber(r0, ','), false, r7);
    L10:
        if (this.tabs[5] != null) goto L12;
        return;
    L12:
        if (isFeedTabEnabled() == false) goto L19;
        int r02 = FeedController.getInstance(this.currentAccount).getUnreadCount();
        GlassTabView r6 = this.tabs[5];
        if (r02 <= 0) goto L16;
        r1 = LocaleController.formatNumber(r02, ',');
    L16:
        r6.setCounter(r1, false, r7);
        return;
    L19:
        return;
    L8:
        this.tabs[0].setCounter(null, false, r7);
        goto L10
    }

    private DialogsActivity createDialogsActivity(Bundle r4) {
        DialogsActivity r42 = (DialogsActivity) prepareTabFragment(new DialogsActivity(createDialogsArguments(r4)));
        r42.setMainTabsActivityController(new MainTabsActivityControllerImpl(this, getPositionChats(), null));
        return r42;
    }

    private Bundle createDialogsArguments(Bundle r2) {
        if (r2 == null) goto L5;
        Bundle r1 = new Bundle(r2);
    L6:
        r1.putBoolean("hasMainTabs", true);
        return r1;
    L5:
        r1 = new Bundle();
        goto L6
    }

    private int getEstBackgroundColor() {
        int r0 = getThemedColor(Theme.key_windowBackgroundGray);
        int r1 = getThemedColor(Theme.key_windowBackgroundWhite);
        ViewPagerActivity.ViewPagerActivityPagerLayout r3 = this.viewPager;
        if (r3 == null) goto L5;
        float r32 = r3.getPositionVisibility(0);
    L7:
        return ColorUtils.blendARGB(r0, r1, r32);
    L5:
        r32 = 1.0f;
        goto L7
    }

    private static int getIcon(MessagesController.DialogFilter r3) {
        int r32 = r3.flags;
        if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r32) != (MessagesController.DIALOG_FILTER_FLAG_CONTACTS | MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS)) goto L7;
        return R.drawable.msg_openprofile;
    L7:
        if ((MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ & r32) == 0) goto L13;
        int r0 = MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS;
        if ((r32 & r0) != r0) goto L13;
        return R.drawable.msg_markunread;
    L13:
        if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r32) != MessagesController.DIALOG_FILTER_FLAG_CHANNELS) goto L17;
        return R.drawable.msg_channel;
    L17:
        if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r32) != MessagesController.DIALOG_FILTER_FLAG_GROUPS) goto L21;
        return R.drawable.msg_groups;
    L21:
        if ((MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS & r32) != MessagesController.DIALOG_FILTER_FLAG_CONTACTS) goto L25;
        return R.drawable.msg_contacts;
    L25:
        if ((r32 & MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS) != MessagesController.DIALOG_FILTER_FLAG_BOTS) goto L29;
        return R.drawable.msg_bots;
    L29:
        return R.drawable.msg_folders;
    }

    private int getPositionCallsOrSettings() {
        if (hasContactsOrFeedTab() == false) goto L6;
        return 2;
    L6:
        return 1;
    }

    private int getPositionChats() {
        return 0;
    }

    private int getPositionContacts() {
        return 1;
    }

    private int getPositionProfile() {
        if (hasContactsOrFeedTab() == false) goto L6;
        return 3;
    L6:
        return 2;
    }

    private boolean hasContactsOrFeedTab() {
        if (getUserConfig().showContactsTab == false) goto L5;
        return true;
    L5:
        if (isFeedTabEnabled() == true) goto L11;
        return false;
    L11:
        return true;
    }

    private int indexToPosition(int r2) {
        if (r2 != 0) goto L6;
        return getPositionChats();
    L6:
        if (r2 == 1) goto L24;
        if (r2 == 5) goto L24;
        if (r2 == 2) goto L22;
        if (r2 == 3) goto L22;
        if (r2 == 4) goto L18;
        return 0;
    L18:
        return getPositionProfile();
    L22:
        return getPositionCallsOrSettings();
    L24:
        return getPositionContacts();
    }

    private void initBlurSources() {
        if (Build.VERSION.SDK_INT < 31) goto L5;
        BlurredBackgroundSourceRenderNode r0 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceTabGlass = r0;
        r0.setupRenderer(new AnonymousClass1(this));
    L6:
        this.iBlur3SourceColor = new BlurredBackgroundSourceColor();
        AnonymousClass2 r02 = new AnonymousClass2(this);
        Bulletin.addDelegate(this, r02);
        Bulletin.addDelegate(this.contentView, r02);
        return;
    L5:
        this.iBlur3SourceTabGlass = null;
        goto L6
    }

    private void invalidateTabsGlass() {
        if (Build.VERSION.SDK_INT < 31) goto L14;
        BlurredBackgroundSourceRenderNode r0 = this.iBlur3SourceTabGlass;
        if (r0 == null) goto L16;
        r0.invalidateDisplayList();
        blur3_invalidateBlur();
        MainTabsLayout r02 = this.tabsView;
        if (r02 == null) goto L10;
        r02.invalidate();
    L10:
        FrameLayout r2 = this.tabsViewWrapper;
        if (r2 == null) goto L15;
        r2.invalidate();
        return;
    L15:
        return;
    L16:
        return;
    }

    private boolean isBottomTabsEnabled() {
        return BottomNavigationBar.visible();
    }

    private boolean isDrawerAccountPreview() {
        Bundle r2 = this.arguments;
        if (r2 != null) goto L5;
    L8:
        return false;
    L5:
        if (r2.getBoolean("drawer_account_preview", false) == false) goto L8;
        return true;
    }

    private boolean isFeedTabEnabled() {
        return ExteraConfig.getShowFeedTab();
    }

    private /* synthetic */ boolean lambda$createView$0(View r6) {
        int r1 = indexToPosition(0);
        if (this.viewPager.getCurrentPosition() == r1) goto L5;
        selectTab(r1, true);
        this.viewPager.scrollToPosition(r1);
    L5:
        DialogsActivity r12 = this.dialogsActivity;
        if (r12 != null) goto L8;
    L17:
        return false;
    L8:
        if (r12.hasRightFragment() == true) goto L17;
        ArrayList<MessagesController.DialogFilter> r13 = getMessagesController().getDialogFilters();
        boolean r2 = ChatUtils.getInstance(this.currentAccount).hasArchivedChats();
        if (r13 != null) goto L13;
    L14:
        if (r2 == false) goto L17;
    L16:
        return showFiltersMenu(r6, r13, r2);
    L13:
        if (r13.size() > 1) goto L16;
        goto L14
    }

    private /* synthetic */ void lambda$createView$1() {
        new NewContactBottomSheet(this, getContext()).show();
    }

    private /* synthetic */ void lambda$createView$10(int r1, View r2) {
        if (this.viewPager.isManualScrolling() == false) goto L5;
        return;
    L5:
        if (this.viewPager.isTouch() == false) goto L8;
        return;
    L8:
        if (r1 == 5) goto L10;
    L15:
        int r12 = indexToPosition(r1);
        if (this.viewPager.getCurrentPosition() != r12) goto L21;
        Object r0 = getCurrentVisibleFragment();
        if ((r0 instanceof TabFragmentDelegate) == false) goto L24;
        ((TabFragmentDelegate) r0).onParentScrollToTop();
        return;
    L24:
        return;
    L21:
        selectTab(r12, true);
        this.viewPager.scrollToPosition(r12);
        return;
    L10:
        if (isFeedTabEnabled() == false) goto L15;
        if (AndroidUtilities.isTablet() == false) goto L15;
        FeedActivity.presentFeed(this);
    }

    private /* synthetic */ void lambda$createView$2() {
        Bundle r0 = new Bundle();
        r0.putBoolean("needFinishFragment", false);
        presentFragment(new CallLogActivity(r0));
    }

    private /* synthetic */ void lambda$createView$3() {
        getUserConfig().setShowContactsTab(null, false);
    }

    private /* synthetic */ boolean lambda$createView$4(View r4) {
        ItemOptions.makeOptions(this, r4).add(R.drawable.msg_contact_add, LocaleController.getString(R.string.NewContact), new MainTabsActivity$$ExternalSyntheticLambda15(this)).add(R.drawable.msg_calls, LocaleController.getString(R.string.VoipChatRecentCalls), new MainTabsActivity$$ExternalSyntheticLambda16(this)).add(R.drawable.msg_archive_hide, LocaleController.getString(R.string.HideContactsTab), new MainTabsActivity$$ExternalSyntheticLambda17(this)).setGravity(1).translate(0.0f, -AndroidUtilities.dp(4.0f)).setScrimViewBackground(MainTabsUiHelper.createMainTabsScrimBackground(this.resourceProvider, false)).setDismissOnMoveOutside(true).show();
        return true;
    }

    private /* synthetic */ boolean lambda$createView$5(View r1) {
        openSettingsTabOptions(r1);
        return true;
    }

    private /* synthetic */ void lambda$createView$6() {
        ExteraConfig.setShowFeedTab(false);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.feedTabVisibleToggled, new Object[0]);
    }

    private /* synthetic */ void lambda$createView$7() {
        ViewPagerActivity.FragmentState r0 = this.fragmentsArr.get(getPositionContacts());
        if (r0 == null) goto L7;
        BaseFragment r02 = r0.fragment;
        if ((r02 instanceof FeedActivity) == false) goto L7;
        ((FeedActivity) r02).markAllRead();
    L8:
        checkUnreadCount(true);
        return;
    L7:
        FeedController.getInstance(this.currentAccount).markAllRead();
        goto L8
    }

    private /* synthetic */ void lambda$createView$8() {
        presentFragment(new FeedChannelsActivity());
    }

    private /* synthetic */ boolean lambda$createView$9(View r4) {
        ItemOptions.makeOptions(this, r4).add(R.drawable.msg_archive_hide, LocaleController.getString(R.string.HideFeedTab), new MainTabsActivity$$ExternalSyntheticLambda26(this)).add(R.drawable.msg_markread, LocaleController.getString(R.string.FeedMarkAllRead), new MainTabsActivity$$ExternalSyntheticLambda27(this)).add(R.drawable.msg_settings, LocaleController.getString(R.string.FeedSettings), new MainTabsActivity$$ExternalSyntheticLambda28(this)).setGravity(1).translate(0.0f, -AndroidUtilities.dp(4.0f)).setScrimViewBackground(MainTabsUiHelper.createMainTabsScrimBackground(this.resourceProvider, false)).setDismissOnMoveOutside(true).show();
        return true;
    }

    private /* synthetic */ void lambda$openAccountSelectorInternal$16() {
        AndroidUtilities.runOnUIThread(new MainTabsActivity$$ExternalSyntheticLambda31(this), 1000);
    }

    private /* synthetic */ void lambda$openAccountSelectorInternal$17(ItemOptions r3) {
        r3.add(R.drawable.menu_download_round, "Dump Canvas", new MainTabsActivity$$ExternalSyntheticLambda30(this));
    }

    private /* synthetic */ void lambda$openCallsSelector$12() {
        CallLogActivity.openCreateCall(this);
    }

    private /* synthetic */ void lambda$openCallsSelector$13() {
        getUserConfig().setShowCallsTab(false);
        checkUi_callTabVisible(false, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.callTabsVisibleToggled, new Object[0]);
    }

    private /* synthetic */ void lambda$openCallsSelector$14() {
        getUserConfig().setShowCallsTab(true);
        checkUi_callTabVisible(true, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.callTabsVisibleToggled, new Object[0]);
    }

    private /* synthetic */ void lambda$openSettingsTabOptions$21() {
        Bundle r0 = new Bundle();
        r0.putInt("folderId", 1);
        presentFragment(new DialogsActivity(r0));
    }

    private /* synthetic */ void lambda$openSettingsTabOptions$22() {
        presentFragment(new ThemeActivity(1));
    }

    private /* synthetic */ void lambda$openSettingsTabOptions$23(View r7) {
        if (DialogsActivity.switchingTheme == false) goto L5;
        return;
    L5:
        DialogsActivity.switchingTheme = true;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r2 = "Blue";
        String r1 = r0.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r1) != null) goto L8;
    L9:
        r1 = "Blue";
    L10:
        String r4 = "Dark Blue";
        String r02 = r0.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r02) != null) goto L13;
    L14:
        r02 = "Dark Blue";
    L15:
        Theme.ThemeInfo r3 = Theme.getActiveTheme();
        if (r1.equals(r02) == true) goto L18;
        r4 = r02;
    L24:
        r2 = r1;
    L27:
        boolean r03 = r2.equals(r3.getKey());
        if (r03 == false) goto L30;
        Theme.ThemeInfo r12 = Theme.getTheme(r4);
    L31:
        switchTheme(r7, r12, r03);
        Theme.turnOffAutoNight(BulletinFactory.of(this), new MainTabsActivity$$ExternalSyntheticLambda29(this));
        return;
    L30:
        r12 = Theme.getTheme(r2);
        goto L31
    L18:
        if (r3.isDark() == false) goto L20;
    L25:
        r4 = r02;
        goto L27
    L20:
        if (r1.equals("Dark Blue") == true) goto L25;
        if (r1.equals("Night") == false) goto L24;
    L13:
        if (Theme.getTheme(r02).isDark() == true) goto L15;
    L8:
        if (Theme.getTheme(r1).isDark() == false) goto L10;
        goto L9
    }

    private /* synthetic */ void lambda$openSettingsTabOptions$25(ItemOptions r1, View r2) {
        r1.dismiss();
        presentFragment(new ProxyListActivity());
    }

    private /* synthetic */ void lambda$showAccountChangeHint$26() {
        AndroidUtilities.removeFromParent(this.accountSwitchHint);
    }

    private /* synthetic */ void lambda$showAccountChangeHint$27() {
        if (getContext() == null) goto L9;
        GlassTabView[] r0 = this.tabs;
        if (r0 == null) goto L10;
        float r1 = ((this.contentView.getWidth() - ((this.tabsView.getX() + r0[4].getX()) + r0.getWidth())) + (r0.getWidth() / 2.0f)) / AndroidUtilities.density;
        HintView2 r02 = new HintView2(getContext(), 3);
        this.accountSwitchHint = r02;
        r02.setTranslationY((-this.navigationBarHeight) + AndroidUtilities.dp(4.0f));
        this.accountSwitchHint.setPadding(AndroidUtilities.dp(7.33f), 0, AndroidUtilities.dp(7.33f), 0);
        this.accountSwitchHint.setMultilineText(false);
        this.accountSwitchHint.setCloseButton(true);
        this.accountSwitchHint.setText(LocaleController.getString(R.string.SwitchAccountHint));
        this.accountSwitchHint.setJoint(1.0f, (-r1) + 7.33f);
        this.contentView.addView(this.accountSwitchHint, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, MainTabsUiHelper.getTabsViewHeightDp()));
        this.accountSwitchHint.setOnHiddenListener(new MainTabsActivity$$ExternalSyntheticLambda24(this));
        this.accountSwitchHint.setDuration(8000);
        this.accountSwitchHint.show();
        HintsController.Hint.AccountSwitchHint.increment();
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$showFiltersMenu$18(ItemOptions r1, int r2, View r3) {
        r1.dismiss();
        this.dialogsActivity.switchToFilter(r2);
    }

    private /* synthetic */ void lambda$showFiltersMenu$19(ItemOptions r4) {
        r4.dismiss();
        Bundle r42 = new Bundle();
        r42.putLong("user_id", getUserConfig().getClientUserId());
        if (getMessagesController().checkCanOpenChat(r42, this) == false) goto L6;
        presentFragment(new ChatActivity(r42));
        return;
    }

    private /* synthetic */ void lambda$showFiltersMenu$20(ItemOptions r3) {
        r3.dismiss();
        Bundle r32 = new Bundle();
        r32.putInt("folderId", 1);
        presentFragment(new DialogsActivity(r32));
    }

    private boolean openAccountSelector(View r2, View r3) {
        return openAccountSelectorInternal(r2, r3, true);
    }

    private boolean openAccountSelectorInternal(View r1, View r2, boolean r3) {
        AccountsUiHelper.Builder r12 = AccountsUiHelper.menu(this, r1).fromBottom(r3).touchRelay(r2);
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        MainTabsActivity$$ExternalSyntheticLambda25 r0 = null;
    L6:
        boolean r02 = r12.extraItems(r0).show();
        if (r02 == false) goto L9;
        HintsController.Hint.AccountSwitchHint.doNotShowAgain();
    L9:
        return r02;
    L5:
        r0 = new MainTabsActivity$$ExternalSyntheticLambda25(this);
        goto L6
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void openSettingsTabOptions(View r7) {
        ItemOptions r1 = ItemOptions.makeOptions(this, r7, true);
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
        if (r2 != null) goto L6;
        r2 = this;
    L6:
        MainMenuHelper.MenuContext r22 = MainMenuHelper.createMenuContext(this.currentAccount, this, new MainTabsActivity$$ExternalSyntheticLambda10(this), MainMenuHelper.createPluginContextData(this.currentAccount, r2));
        Theme.ResourcesProvider r3 = this.resourceProvider;
        if (r3 == null) goto L9;
        boolean r32 = r3.isDark();
    L10:
        if (r32 == false) goto L12;
        int r4 = R.drawable.menu_day_mode_24;
    L13:
        if (r32 == false) goto L15;
        int r33 = R.string.SwitchThemeToDay;
    L16:
        r1.add(r4, LocaleController.getString(r33), new MainTabsActivity$$ExternalSyntheticLambda11(this, r7));
        r1.addGap();
        MainMenuHelper.addConfiguredItemOptions(r1, r22, new MainTabsActivity$$ExternalSyntheticLambda12());
        ApplicationLoader r72 = ApplicationLoader.applicationLoaderInstance;
        if (r72 == null) goto L20;
        r72.addItemOptions(r1);
    L20:
        if (SharedConfig.proxyList.isEmpty() == true) goto L25;
        r1.addGap();
        if (this.proxyDrawable != null) goto L24;
        this.proxyDrawable = new ProxyDrawable(getContext());
    L24:
        ActionBarMenuSubItem r73 = new ActionBarMenuSubItem(getContext(), false, false, this.resourceProvider);
        this.proxyMenuSubItem = r73;
        r73.setTextAndIcon(LocaleController.getString(R.string.MenuProxyTitle), 0, this.proxyDrawable);
        this.proxyMenuSubItem.setOnClickListener(new MainTabsActivity$$ExternalSyntheticLambda13(this, r1));
        updateProxyButton(false, false);
        r1.addView(this.proxyMenuSubItem);
    L25:
        r1.setGravity(1).translate(0.0f, -AndroidUtilities.dp(4.0f)).setScrimViewBackground(MainTabsUiHelper.createMainTabsScrimBackground(this.resourceProvider, false)).setDismissOnMoveOutside(true).setSwipebackGravity(false, true).setSwipebackCenterHorizontal(true).show();
        return;
    L15:
        r33 = R.string.SwitchThemeToNight;
        goto L16
    L12:
        r4 = R.drawable.menu_night_mode_24;
        goto L13
    L9:
        r32 = Theme.isCurrentThemeDark();
        goto L10
    }

    private <T extends BaseFragment> T prepareTabFragment(T r3) {
        r3.setCurrentAccount(this.currentAccount);
        r3.setInPreviewMode(isInPreviewMode());
        if ((r3 instanceof TabFragmentDelegate) == false) goto L5;
        ((TabFragmentDelegate) r3).setParentTabsGlassInvalidationCallback(new MainTabsActivity$$ExternalSyntheticLambda14(this));
    L5:
        return r3;
    }

    private void showAccountChangeHint() {
        if (this.accountSwitchHintShown == false) goto L6;
        return;
    L6:
        if (isDrawerAccountPreview() == false) goto L9;
        return;
    L9:
        if (isBottomTabsEnabled() == true) goto L12;
        return;
    L12:
        if (this.accountSwitchHint == null) goto L14;
    L16:
        this.accountSwitchHintShown = true;
        return;
    L14:
        if (HintsController.Hint.AccountSwitchHint.show() == false) goto L16;
        AndroidUtilities.runOnUIThread(new MainTabsActivity$$ExternalSyntheticLambda8(this), 1500);
        goto L16
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private boolean showFiltersMenu(View r17, ArrayList<MessagesController.DialogFilter> r18, boolean r19) {
        ItemOptions r2 = ItemOptions.makeOptions(this, r17);
        if (r18 != null) goto L5;
    L30:
        r2.add(R.drawable.msg_saved, LocaleController.getString(R.string.SavedMessages), new MainTabsActivity$$ExternalSyntheticLambda22(this, r2));
        r2.addIf(r19, R.drawable.msg_archive, LocaleController.getString(R.string.ArchivedChats), new MainTabsActivity$$ExternalSyntheticLambda23(this, r2));
        r2.setGravity(3).translate(0.0f, -AndroidUtilities.dp(4.0f)).setScrimViewBackground(MainTabsUiHelper.createMainTabsScrimBackground(this.resourceProvider, false)).setDismissOnMoveOutside(true).show();
        return true;
    L5:
        if (r18.isEmpty() == true) goto L30;
        int r5 = 0;
    L8:
        if (r5 >= r18.size()) goto L29;
        MessagesController.DialogFilter r6 = r18.get(r5);
        if (r6.isDefault() == false) goto L15;
        if (ExteraConfig.getHideAllChats() == false) goto L15;
    L28:
        r5 = r5 + 1;
    L15:
        if (r6.isDefault() == false) goto L17;
        String r7 = LocaleController.getString(R.string.FilterAllChats);
    L18:
        CharSequence r72 = Emoji.replaceEmoji(r7, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        ArrayList<TLRPC.MessageEntity> r8 = r6.entities;
        if (r8 != null) goto L21;
    L23:
        ActionBarMenuSubItem r82 = r2.add();
        r82.setTextAndIcon(r72, getIcon(r6));
        NotificationCenter.listenEmojiLoading(r82.textView);
        r82.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RLottieImageView r62 = r82.imageView;
        if (LocaleController.isRTL == false) goto L26;
        int r73 = 5;
    L27:
        r62.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, r73 | 16, 0.0f, 0.0f, 0.0f, 0.0f));
        r82.setOnClickListener(new MainTabsActivity$$ExternalSyntheticLambda21(this, r2, r5));
        goto L28
    L26:
        r73 = 3;
        goto L27
    L21:
        if (r8.isEmpty() == true) goto L23;
        r72 = MessageObject.replaceAnimatedEmoji(r72, r6.entities, Theme.chat_msgTextPaint.getFontMetricsInt());
        goto L23
    L17:
        r7 = r6.name;
        goto L18
    L29:
        r2.addGap();
        goto L30
    }

    private void switchTheme(View r10, Theme.ThemeInfo r11, boolean r12) {
        if (r10 != null) goto L4;
        return;
    L4:
        int[] r2 = new int[2];
        r10.getLocationInWindow(r2);
        r2[0] = r2[0] + (r10.getMeasuredWidth() / 2);
        r2[1] = r2[1] + (r10.getMeasuredHeight() / 2);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r11, Boolean.FALSE, r2, -1, Boolean.valueOf(r12), null, null, null, Boolean.TRUE});
    }

    private void updateProxyButton(boolean r6, boolean r7) {
        if (this.proxyDrawable != null) goto L5;
        return;
    L5:
        if (this.proxyMenuSubItem == null) goto L23;
        boolean r1 = false;
        boolean r72 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).getBoolean("proxy_enabled", false);
        int r0 = this.currentConnectionState;
        if (r0 != 3) goto L10;
    L11:
        r1 = true;
    L12:
        int r02 = getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon);
        this.proxyDrawable.setColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
        this.proxyMenuSubItem.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        ActionBarMenuSubItem r03 = this.proxyMenuSubItem;
        if (r72 == false) goto L19;
        r03.setItemHeight(56);
        ActionBarMenuSubItem r04 = this.proxyMenuSubItem;
        if (r1 == false) goto L17;
        int r2 = R.string.MenuProxyConnected;
    L18:
        r04.setSubtext(LocaleController.getString(r2));
    L20:
        this.proxyDrawable.setConnected(r72, r1, r6);
        return;
    L17:
        r2 = R.string.MenuProxyConnecting;
        goto L18
    L19:
        r03.setItemHeight(48);
        this.proxyMenuSubItem.setSubtext(null);
        goto L20
    L10:
        if (r0 != 5) goto L12;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.canBeginSlide() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public boolean canScrollBackward(MotionEvent r2) {
        return canScrollInternal(r2, false);
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public boolean canScrollForward(MotionEvent r2) {
        return canScrollInternal(r2, true);
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public boolean canScrollToPage(int r2) {
        if (AndroidUtilities.isTablet() == true) goto L5;
        return true;
    L5:
        if (isFeedTabEnabled() == true) goto L7;
        return true;
    L7:
        if (r2 != getPositionContacts()) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public BaseFragment createBaseFragmentAt(int r8) {
        if (r8 != getPositionContacts()) goto L9;
        if (isFeedTabEnabled() == false) goto L9;
        Bundle r0 = new Bundle();
        r0.putBoolean("hasMainTabs", isBottomTabsEnabled());
        FeedActivity r02 = (FeedActivity) prepareTabFragment(new FeedActivity(r0));
        r02.setMainTabsActivityController(new MainTabsActivityControllerImpl(this, r8, null));
        return r02;
    L9:
        if (r8 != getPositionContacts()) goto L15;
        if (getUserConfig().showContactsTab == false) goto L15;
        Bundle r03 = new Bundle();
        r03.putBoolean("needPhonebook", true);
        r03.putBoolean("needFinishFragment", false);
        r03.putBoolean("hasMainTabs", isBottomTabsEnabled());
        ContactsActivity r04 = (ContactsActivity) prepareTabFragment(new ContactsActivity(r03));
        r04.setMainTabsActivityController(new MainTabsActivityControllerImpl(this, r8, null));
        return r04;
    L15:
        if (r8 != getPositionCallsOrSettings()) goto L23;
        if (getUserConfig().showCallsTab == false) goto L20;
        Bundle r05 = new Bundle();
        r05.putBoolean("needFinishFragment", false);
        r05.putBoolean("hasMainTabs", isBottomTabsEnabled());
        CallLogActivity r06 = (CallLogActivity) prepareTabFragment(new CallLogActivity(r05));
        r06.setMainTabsActivityController(new MainTabsActivityControllerImpl(this, r8, null));
        return r06;
    L20:
        Bundle r07 = new Bundle();
        r07.putBoolean("hasMainTabs", isBottomTabsEnabled());
        SettingsActivity r08 = (SettingsActivity) prepareTabFragment(new SettingsActivity(r07));
        r08.setMainTabsActivityController(new MainTabsActivityControllerImpl(this, r8, null));
        return r08;
    L23:
        if (r8 != getPositionChats()) goto L27;
        DialogsActivity r82 = createDialogsActivity(this.arguments);
        this.dialogsActivity = r82;
        return r82;
    L27:
        if (r8 != getPositionProfile()) goto L30;
        Bundle r09 = new Bundle();
        r09.putLong("user_id", UserConfig.getInstance(this.currentAccount).getClientUserId());
        r09.putBoolean("my_profile", true);
        r09.putBoolean("hasMainTabs", isBottomTabsEnabled());
        ProfileActivity r010 = (ProfileActivity) prepareTabFragment(new ProfileActivity(r09));
        r010.setMainTabsActivityController(new MainTabsActivityControllerImpl(this, r8, null));
        return r010;
    L30:
        return null;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public FrameLayout createContentView(Context r2) {
        return new AnonymousClass3(this, r2);
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        FrameLayout r0 = this.contentView;
        if (r0 == null) goto L5;
        Bulletin.removeDelegate(r0);
    L5:
        super.createView(r10);
        this.tabletLayout = false;
        MainTabsLayout r1 = new MainTabsLayout(r10, this.resourceProvider);
        this.tabsView = r1;
        r1.setClipChildren(false);
        MainTabsUiHelper.applyTabsLayoutStyle(this.tabsView);
        GlassTabView[] r2 = new GlassTabView[6];
        this.tabs = r2;
        r2[0] = GlassTabView.createMainNavigationTab(r10, this.resourceProvider, GlassTabView.TabAnimation.CHATS, R.string.MainTabsChats);
        this.tabs[0].setOnLongClickListener(new MainTabsActivity$$ExternalSyntheticLambda0(this));
        this.tabs[1] = GlassTabView.createMainNavigationTab(r10, this.resourceProvider, GlassTabView.TabAnimation.CONTACTS, R.string.MainTabsContacts);
        this.tabs[1].setOnLongClickListener(new MainTabsActivity$$ExternalSyntheticLambda1(this));
        this.tabs[2] = GlassTabView.createMainNavigationTab(r10, this.resourceProvider, GlassTabView.TabAnimation.SETTINGS, R.string.Settings);
        this.tabs[2].setOnLongClickListener(new MainTabsActivity$$ExternalSyntheticLambda2(this));
        this.tabs[3] = GlassTabView.createMainNavigationTab(r10, this.resourceProvider, GlassTabView.TabAnimation.CALLS, R.string.MainTabsCalls);
        this.tabs[4] = GlassTabView.createMainNavigationAvatar(r10, this.resourceProvider, this.currentAccount, R.string.MainTabsProfile);
        this.tabs[3].setOnLongClickListener(new MainTabsActivity$$ExternalSyntheticLambda3(this));
        this.tabs[4].setOnLongClickListener(new MainTabsActivity$$ExternalSyntheticLambda4(this));
        this.tabs[5] = GlassTabView.createMainNavigationTab(r10, this.resourceProvider, GlassTabView.TabAnimation.FEED, R.string.Feed);
        this.tabs[5].setOnLongClickListener(new MainTabsActivity$$ExternalSyntheticLambda5(this));
        this.tabsView.addTabToIgnoreClick(this.tabs[0]);
        this.tabsView.addTabToIgnoreClick(this.tabs[1]);
        this.tabsView.addTabToIgnoreClick(this.tabs[4]);
        this.tabsView.addTabToIgnoreClick(this.tabs[3]);
        this.tabsView.addTabToIgnoreClick(this.tabs[5]);
        int r22 = 0;
    L6:
        GlassTabView[] r3 = this.tabs;
        if (r22 >= r3.length) goto L9;
        r3[r22].setOnClickListener(new MainTabsActivity$$ExternalSyntheticLambda6(this, r22));
        r22 = r22 + 1;
        goto L6
    L9:
        int[] r23 = {0, 5, 1, 2, 3, 4};
        int r32 = 0;
    L10:
        if (r32 >= 6) goto L12;
        int r5 = r23[r32];
        this.tabsView.addView(this.tabs[r5]);
        this.tabsView.setViewVisible(this.tabs[r5], true, false);
        r32 = r32 + 1;
        goto L10
    L12:
        checkUi_contactsOrFeedTabVisible(false);
        checkUi_callTabVisible(getUserConfig().showCallsTab, false);
        selectTab(this.viewPager.getCurrentPosition(), false);
        this.iBlur3SourceColor.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        ViewPositionWatcher r12 = this.viewPositionWatcher;
        if (r12 == null) goto L15;
        r12.shutdown();
    L15:
        this.viewPositionWatcher = new ViewPositionWatcher(this.contentView);
        BlurredBackgroundSource r24 = this.iBlur3SourceTabGlass;
        if (r24 != null) goto L19;
        r24 = this.iBlur3SourceColor;
    L19:
        BlurredBackgroundDrawableViewFactory r13 = new BlurredBackgroundDrawableViewFactory(r24);
        r13.setSourceRootView(this.viewPositionWatcher, this.contentView);
        r13.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        BlurredBackgroundDrawable r14 = r13.create(this.tabsView, BlurredBackgroundProviderImpl.mainTabs(this.resourceProvider));
        this.tabsViewBackground = r14;
        r14.setRadius(MainTabsUiHelper.getBackgroundRadius());
        this.tabsViewBackground.setPadding(MainTabsUiHelper.getBackgroundInset());
        this.tabsView.setBackground(this.tabsViewBackground);
        BlurredBackgroundDrawableViewFactory r15 = new BlurredBackgroundDrawableViewFactory(this.iBlur3SourceColor);
        r15.setSourceRootView(this.viewPositionWatcher, this.contentView);
        this.fadeView = new View(r10);
        BlurredBackgroundWithFadeDrawable r25 = new BlurredBackgroundWithFadeDrawable(r15.create(this.fadeView, null));
        r25.setFadeHeight(AndroidUtilities.dp(60.0f), true);
        this.fadeView.setBackground(r25);
        this.contentView.addView(this.fadeView, LayoutHelper.createFrame(-1, 0, 80));
        FrameLayout r16 = new FrameLayout(r10);
        this.tabsViewWrapper = r16;
        r16.setOnClickListener(new MainTabsActivity$$ExternalSyntheticLambda7());
        this.tabsViewWrapper.addView(this.tabsView, LayoutHelper.createFrame(-1, MainTabsUiHelper.getTabsViewHeightDp(), 81));
        this.tabsViewWrapper.setClipToPadding(false);
        this.contentView.addView(this.tabsViewWrapper, LayoutHelper.createFrame(-1, -2, 80));
        UpdateLayoutWrapper r17 = new UpdateLayoutWrapper(r10);
        this.updateLayoutWrapper = r17;
        this.contentView.addView(r17, LayoutHelper.createFrame(-1, -2, 80));
        IUpdateLayout r102 = ApplicationLoader.applicationLoaderInstance.takeUpdateLayout(getParentActivity(), this.updateLayoutWrapper);
        this.updateLayout = r102;
        if (r102 == null) goto L22;
        r102.updateAppUpdateViews(this.currentAccount, false);
    L22:
        updateLayout();
        checkUnreadCount(false);
        return this.contentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        boolean r1 = false;
        if (r4 != NotificationCenter.notificationsCountUpdated) goto L5;
    L127:
        View r42 = this.fragmentView;
        if (r42 != null) goto L130;
    L132:
        checkUnreadCount(r1);
        return;
    L130:
        if (r42.isAttachedToWindow() == false) goto L132;
        r1 = true;
        goto L132
    L5:
        if (r4 == NotificationCenter.updateInterfaces) goto L127;
        if (r4 == NotificationCenter.dialogsNeedReload) goto L127;
        if (r4 != NotificationCenter.appUpdateLoading) goto L16;
        IUpdateLayout r43 = this.updateLayout;
        if (r43 == null) goto L136;
        r43.updateFileProgress(null);
        this.updateLayout.updateAppUpdateViews(this.currentAccount, true);
        return;
    L136:
        return;
    L16:
        if (r4 != NotificationCenter.fileLoaded) goto L26;
        String r44 = (String) r6[0];
        if (SharedConfig.isAppUpdateAvailable() == true) goto L20;
        return;
    L20:
        if (FileLoader.getAttachFileName(SharedConfig.pendingAppUpdate.document).equals(r44) == false) goto L138;
        IUpdateLayout r45 = this.updateLayout;
        if (r45 == null) goto L139;
        r45.updateAppUpdateViews(this.currentAccount, true);
        return;
    L139:
        return;
    L138:
        return;
    L26:
        if (r4 != NotificationCenter.fileLoadFailed) goto L36;
        String r46 = (String) r6[0];
        if (SharedConfig.isAppUpdateAvailable() == true) goto L30;
        return;
    L30:
        if (FileLoader.getAttachFileName(SharedConfig.pendingAppUpdate.document).equals(r46) == false) goto L141;
        IUpdateLayout r47 = this.updateLayout;
        if (r47 == null) goto L142;
        r47.updateAppUpdateViews(this.currentAccount, true);
        return;
    L142:
        return;
    L141:
        return;
    L36:
        if (r4 != NotificationCenter.fileLoadProgressChanged) goto L42;
        IUpdateLayout r3 = this.updateLayout;
        if (r3 == null) goto L143;
        r3.updateFileProgress(r6);
        return;
    L143:
        return;
    L42:
        if (r4 != NotificationCenter.appUpdateAvailable) goto L53;
        IUpdateLayout r48 = this.updateLayout;
        if (r48 == null) goto L144;
        LaunchActivity r52 = LaunchActivity.instance;
        if (r52 == null) goto L145;
        int r32 = this.currentAccount;
        if (r52.getMainFragmentsStackSize() != 1) goto L50;
        r1 = true;
    L50:
        r48.updateAppUpdateViews(r32, r1);
        return;
    L145:
        return;
    L144:
        return;
    L53:
        if (r4 != NotificationCenter.needSetDayNightTheme) goto L57;
        clearAllHiddenFragments();
        return;
    L57:
        if (r4 != NotificationCenter.callTabsVisibleToggled) goto L67;
        checkUi_callTabVisible(getUserConfig().showCallsTab, true);
        ViewPagerActivity.ViewPagerActivityPagerLayout r49 = this.viewPager;
        if (r49 != null) goto L61;
    L64:
        dropFragmentAtPosition(getPositionCallsOrSettings());
        return;
    L61:
        if (r49.getCurrentPosition() != getPositionCallsOrSettings()) goto L64;
        this.viewPager.scrollToPosition(getPositionChats());
        selectTab(getPositionChats(), true);
        this.dropCallsFragmentAfterPageScroll = true;
        return;
    L67:
        if (r4 != NotificationCenter.contactsTabVisibleToggled) goto L86;
        boolean r410 = getUserConfig().showContactsTab;
        checkUi_contactsOrFeedTabVisible(true);
        ViewPagerActivity.ViewPagerActivityPagerLayout r53 = this.viewPager;
        if (r53 == null) goto L146;
        int r54 = r53.getCurrentPosition();
        if (isFeedTabEnabled() == true) goto L79;
        if (r410 == false) goto L75;
        if (r54 < 1) goto L79;
        r54 = r54 + 1;
        goto L79
    L75:
        if (r54 != 1) goto L77;
        r54 = 0;
        goto L79
    L77:
        if (r54 <= 1) goto L79;
        r54 = r54 - 1;
    L79:
        clearAllHiddenFragments();
        int r411 = 0;
    L81:
        if (r411 >= (getTabsCount() + 1)) goto L83;
        dropFragmentAtPosition(r411);
        r411 = r411 + 1;
        goto L81
    L83:
        ViewPagerActivity.ViewPagerActivityPagerLayout r412 = this.viewPager;
        r412.currentPosition = r54;
        r412.rebuild(false);
        selectTab(r54, false);
        return;
    L146:
        return;
    L86:
        if (r4 != NotificationCenter.feedTabVisibleToggled) goto L106;
        checkUi_contactsOrFeedTabVisible(true);
        checkUnreadCount(false);
        ViewPagerActivity.ViewPagerActivityPagerLayout r413 = this.viewPager;
        if (r413 == null) goto L147;
        int r414 = r413.getCurrentPosition();
        if (getUserConfig().showContactsTab == false) goto L92;
    L99:
        clearAllHiddenFragments();
        int r55 = 0;
    L101:
        if (r55 >= (getTabsCount() + 1)) goto L103;
        dropFragmentAtPosition(r55);
        r55 = r55 + 1;
        goto L101
    L103:
        ViewPagerActivity.ViewPagerActivityPagerLayout r56 = this.viewPager;
        r56.currentPosition = r414;
        r56.rebuild(false);
        selectTab(r414, false);
        return;
    L92:
        if (isFeedTabEnabled() == false) goto L95;
        if (r414 < 1) goto L99;
        r414 = r414 + 1;
        goto L99
    L95:
        if (r414 != 1) goto L97;
        r414 = 0;
        goto L99
    L97:
        if (r414 <= 1) goto L99;
        r414 = r414 - 1;
        goto L99
    L147:
        return;
    L106:
        if (r4 != NotificationCenter.mainUserInfoChanged) goto L114;
        GlassTabView[] r415 = this.tabs;
        if (r415 == null) goto L148;
        GlassTabView r416 = r415[4];
        if (r416 == null) goto L149;
        r416.updateUserAvatar(this.currentAccount);
        return;
    L149:
        return;
    L148:
        return;
    L114:
        if (r4 != NotificationCenter.contactsPermissionBadgeCheck) goto L118;
        checkContactsTabBadge();
        return;
    L118:
        if (r4 != NotificationCenter.proxySettingsChanged) goto L122;
        updateProxyButton(false, false);
        return;
    L122:
        if (r4 != NotificationCenter.didUpdateConnectionState) goto L150;
        int r417 = AccountInstance.getInstance(r5).getConnectionsManager().getConnectionState();
        if (this.currentConnectionState == r417) goto L151;
        this.currentConnectionState = r417;
        updateProxyButton(true, false);
        return;
    L151:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Animator getCustomSlideTransition(boolean r1, boolean r2, float r3) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getCustomSlideTransition(r1, r2, r3);
    }

    public DialogsActivity getDialogsActivity() {
        return this.dialogsActivity;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public EdgeToEdgeSupportMode getEdgeToEdgeSupportMode() {
        return EdgeToEdgeSupportMode.FULL;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public int getFragmentsCount() {
        return getTabsCount();
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public int getStartPosition() {
        return getPositionChats();
    }

    public int getTabsCount() {
        if (hasContactsOrFeedTab() == false) goto L6;
        return 4;
    L6:
        return 3;
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = super.getThemeDescriptions();
        MainTabsActivity$$ExternalSyntheticLambda9 r7 = new MainTabsActivity$$ExternalSyntheticLambda9(this);
        r0.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_windowBackgroundWhite));
        r0.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_dialogBackground));
        return r0;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public WindowInsetsCompat onApplyWindowInsets(View r8, WindowInsetsCompat r9) {
        if (this.updateLayoutWrapper == null) goto L41;
        if (this.fadeView == null) goto L41;
        if (this.viewPager == null) goto L41;
        if (this.tabsViewWrapper == null) goto L41;
        Insets r1 = AndroidUtilities.getDefaultWindowInsets(r9, false);
        this.insetLeft = r1.left;
        this.insetRight = r1.right;
        this.navigationBarHeight = r1.bottom;
        boolean r2 = this.updateLayoutWrapper.isUpdateLayoutVisible();
        if (r2 == false) goto L14;
        int r3 = AndroidUtilities.dp(44.0f);
    L15:
        this.updateLayoutWrapper.setPadding(0, 0, 0, this.navigationBarHeight);
        int r4 = this.navigationBarHeight + r3;
        if (isBottomTabsEnabled() == false) goto L18;
        int r5 = AndroidUtilities.dp(MainTabsUiHelper.getTabsViewHeightDp());
    L19:
        int r42 = r4 + r5;
        ViewGroup.MarginLayoutParams r52 = (ViewGroup.MarginLayoutParams) this.fadeView.getLayoutParams();
        if (r52.height == r42) goto L22;
        r52.height = r42;
        this.fadeView.setLayoutParams(r52);
    L22:
        if (r2 == false) goto L24;
        int r43 = this.navigationBarHeight + r3;
    L26:
        if (this.tabletLayout == false) goto L28;
        r43 = Math.max(r43, this.navigationBarHeight + AndroidUtilities.dp(MainTabsUiHelper.getTabsViewHeightDp()));
    L28:
        ViewGroup.MarginLayoutParams r32 = (ViewGroup.MarginLayoutParams) this.viewPager.getLayoutParams();
        if (r32.bottomMargin == r43) goto L31;
    L34:
        r32.leftMargin = r1.left;
        r32.rightMargin = r1.right;
        r32.bottomMargin = r43;
        this.viewPager.setLayoutParams(r32);
    L35:
        MainTabsUiHelper.applyTabsBottomInset(this.tabsView, this.tabsViewWrapper, r1.left, r1.right, this.navigationBarHeight);
        if (r2 == false) goto L38;
        r9 = r9.inset(0, 0, 0, this.navigationBarHeight);
    L38:
        checkUi_tabsPosition();
        checkUi_fadeView();
        return super.onApplyWindowInsets(r8, r9);
    L31:
        if (r32.leftMargin != r1.left) goto L34;
        if (r32.rightMargin == r1.right) goto L35;
    L24:
        r43 = 0;
        goto L26
    L18:
        r5 = 0;
        goto L19
    L14:
        r3 = 0;
    L41:
        return super.onApplyWindowInsets(r8, r9);
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r4) {
        boolean r0 = super.onBackPressed(r4);
        if (r0 == false) goto L10;
        int r1 = getStartPosition();
        if (this.viewPager.getCurrentPosition() == r1) goto L10;
        if (r4 == false) goto L11;
        this.viewPager.scrollToPosition(r1);
        return false;
    L11:
        return false;
    L10:
        return r0;
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        Object r1 = getCurrentVisibleFragment();
        if ((r1 instanceof TabFragmentDelegate) == false) goto L6;
        ((TabFragmentDelegate) r1).onParentBecomeFullyVisible();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBeginSlide() {
        super.onBeginSlide();
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onBeginSlide();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        updateLayout();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L7;
        checkUi_tabsPosition();
        checkUi_fadeView();
        Bulletin r0 = Bulletin.getVisibleBulletin();
        if (r0 == null) goto L8;
        r0.updatePosition();
        return;
    L8:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.observersGroup = NotificationCenter.getInstance(this.currentAccount).createObserversGroup(this).add(NotificationCenter.fileLoaded).add(NotificationCenter.fileLoadProgressChanged).add(NotificationCenter.fileLoadFailed).add(NotificationCenter.notificationsCountUpdated).add(NotificationCenter.updateInterfaces).add(NotificationCenter.dialogsNeedReload).add(NotificationCenter.callTabsVisibleToggled).add(NotificationCenter.contactsTabVisibleToggled).add(NotificationCenter.feedTabVisibleToggled).add(NotificationCenter.mainUserInfoChanged).add(NotificationCenter.didUpdateConnectionState).add(NotificationCenter.contactsPermissionBadgeCheck).addGlobal(NotificationCenter.appUpdateAvailable).addGlobal(NotificationCenter.appUpdateLoading).addGlobal(NotificationCenter.proxySettingsChanged).addGlobal(NotificationCenter.needSetDayNightTheme);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        Bulletin.removeDelegate(this);
        Bulletin.removeDelegate(this.contentView);
        ViewPositionWatcher r0 = this.viewPositionWatcher;
        if (r0 == null) goto L5;
        r0.shutdown();
        this.viewPositionWatcher = null;
    L5:
        NotificationCenter.ObserversGroup r02 = this.observersGroup;
        if (r02 == null) goto L8;
        r02.removeAllObservers();
        this.observersGroup = null;
    L8:
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        HintView2 r0 = this.accountSwitchHint;
        if (r0 == null) goto L6;
        r0.hide();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPreviewOpenAnimationEnd() {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onPreviewOpenAnimationEnd();
        return;
    }

    @Override // org.telegram.ui.ViewPagerActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        blur3_updateColors();
        checkUi_tabsPosition();
        checkUi_fadeView();
        checkContactsTabBadge();
        checkUnreadCount(true);
        showAccountChangeHint();
        updateProxyButton(false, false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onSlideProgress(boolean r1, float r2) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onSlideProgress(r1, r2);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onTransitionAnimationEnd(r1, r2);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationProgress(boolean r1, float r2) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onTransitionAnimationProgress(r1, r2);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.onTransitionAnimationStart(r1, r2);
        return;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public void onViewPagerScrollEnd() {
        if (this.tabsView == null) goto L5;
        selectTab(this.viewPager.getCurrentPosition(), true);
        setGestureSelectedOverride(0.0f, false);
    L5:
        blur3_invalidateBlur();
        ViewPagerActivity.ViewPagerActivityPagerLayout r0 = this.viewPager;
        if (r0 == null) goto L15;
        int r02 = r0.getCurrentPosition();
        if (r02 == getPositionCallsOrSettings()) goto L13;
        if (this.dropCallsFragmentAfterPageScroll == false) goto L13;
        dropFragmentAtPosition(getPositionCallsOrSettings());
        this.dropCallsFragmentAfterPageScroll = false;
    L13:
        if (r02 == getPositionProfile()) goto L15;
        dropFragmentAtPosition(getPositionProfile());
    L15:
        Object r3 = getCurrentVisibleFragment();
        if ((r3 instanceof TabFragmentDelegate) == false) goto L19;
        ((TabFragmentDelegate) r3).updateMainTabsVisibility();
        return;
    }

    @Override // org.telegram.ui.ViewPagerActivity
    public void onViewPagerTabAnimationUpdate(boolean r3) {
        boolean r0 = !r3;
        if (this.tabsView == null) goto L7;
        float r1 = this.viewPager.getPositionAnimated();
        setGestureSelectedOverride(r1, r0);
        if (r3 == true) goto L7;
        selectTab(Math.round(r1), true);
    L7:
        checkUi_fadeView();
        blur3_invalidateBlur();
        this.contentView.invalidate();
    }

    public boolean openAccountSelector(View r3) {
        return openAccountSelectorInternal(r3, null, false);
    }

    public boolean openCallsSelector(View r5) {
        if (getContext() != null) goto L5;
    L13:
        return false;
    L5:
        if (getParentActivity() == null) goto L13;
        ItemOptions r52 = ItemOptions.makeOptions(this, r5);
        r52.add(R.drawable.menu_call_create, LocaleController.getString(R.string.GroupCallCreate2), new MainTabsActivity$$ExternalSyntheticLambda18(this));
        if (getUserConfig().showCallsTab == false) goto L10;
        r52.add(R.drawable.msg_archive_hide, LocaleController.getString(R.string.HideCallTab), new MainTabsActivity$$ExternalSyntheticLambda19(this));
    L11:
        r52.translate(0.0f, -AndroidUtilities.dp(4.0f));
        r52.setScrimViewBackground(MainTabsUiHelper.createMainTabsScrimBackground(this.resourceProvider, false));
        r52.setDismissOnMoveOutside(true);
        r52.show();
        return true;
    L10:
        r52.add(R.drawable.menu_add_tab_24, LocaleController.getString(R.string.GroupCallShowInMainTabs), new MainTabsActivity$$ExternalSyntheticLambda20(this));
        goto L11
    }

    public DialogsActivity prepareDialogsActivity(Bundle r2) {
        this.dialogsActivity = createDialogsActivity(r2);
        putFragmentAtPosition(getPositionChats(), this.dialogsActivity);
        return this.dialogsActivity;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void prepareFragmentToSlide(boolean r1, boolean r2) {
        BaseFragment r0 = getCurrentVisibleFragment();
        if (r0 == null) goto L6;
        r0.prepareFragmentToSlide(r1, r2);
        return;
    }

    public void selectTab(int r5, boolean r6) {
        int r1 = 0;
    L3:
        GlassTabView[] r2 = this.tabs;
        if (r1 >= r2.length) goto L10;
        GlassTabView r22 = r2[r1];
        if (indexToPosition(r1) != r5) goto L8;
        boolean r3 = true;
    L9:
        r22.setSelected(r3, r6);
        r1 = r1 + 1;
        goto L3
    L8:
        r3 = false;
        goto L9
    }

    public void setGestureSelectedOverride(float r4, boolean r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.tabs.length) goto L6;
        this.tabs[r0].setGestureSelectedOverride(Math.max(0.0f, 1.0f - Math.abs(indexToPosition(r0) - r4)), r5);
        r0 = r0 + 1;
        goto L4
    L6:
        this.tabsView.invalidate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setInPreviewMode(boolean r4) {
        super.setInPreviewMode(r4);
        int r0 = this.fragmentsArr.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ViewPagerActivity.FragmentState r2 = this.fragmentsArr.valueAt(r1);
        if (r2 == null) goto L7;
        r2.fragment.setInPreviewMode(r4);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void updateLayout() {
    }
}
