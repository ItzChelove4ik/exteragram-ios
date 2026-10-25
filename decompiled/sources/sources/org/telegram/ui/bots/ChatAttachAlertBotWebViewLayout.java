package org.telegram.ui.bots;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RenderNode;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import androidx.core.view.GestureDetectorCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotFullscreenButtons$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;
import org.telegram.ui.ReportBottomSheet;
import org.telegram.ui.recyclerview.ChatListItemAnimator;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes7.dex */
public class ChatAttachAlertBotWebViewLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate {
    private ActionBarMenuSubItem addToHomeScreenItem;
    private long botId;
    private int currentAccount;
    private int customActionBarBackground;
    private int customBackground;
    private boolean destroyed;
    private boolean hasCustomActionBarBackground;
    private boolean hasCustomBackground;
    private boolean ignoreLayout;
    private boolean ignoreMeasure;
    private boolean isBotButtonAvailable;
    private long lastSwipeTime;
    private int measureOffsetY;
    private long monoforumTopicId;
    private boolean needCloseConfirmation;
    private boolean needReload;
    private ActionBarMenuItem otherItem;
    private long peerId;
    public ActionBarMenuSubItem pinItem;
    private Runnable pollRunnable;
    private WebProgressView progressView;
    private long queryId;
    private int replyToMsgId;
    public ActionBarMenuSubItem settingsItem;
    private boolean silent;
    private String startCommand;
    private WebViewSwipeContainer swipeContainer;
    private BotWebViewContainer webViewContainer;
    private ValueAnimator webViewScrollAnimator;

    /* renamed from: org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends BotWebViewContainer {
        final /* synthetic */ ChatAttachAlertBotWebViewLayout this$0;

        public AnonymousClass1(ChatAttachAlertBotWebViewLayout r1, Context r2, Theme.ResourcesProvider r3, int r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L8;
            if (ChatAttachAlertBotWebViewLayout.m21462$$Nest$fgetisBotButtonAvailable(this.this$0) == true) goto L8;
            ChatAttachAlertBotWebViewLayout.m21468$$Nest$fputisBotButtonAvailable(this.this$0, true);
            ChatAttachAlertBotWebViewLayout.m21466$$Nest$fgetwebViewContainer(this.this$0).restoreButtonData();
        L8:
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer
        public void onWebViewCreated(BotWebViewContainer.MyWebView r1) {
            super.onWebViewCreated(r1);
            ChatAttachAlertBotWebViewLayout.m21465$$Nest$fgetswipeContainer(this.this$0).setWebView(r1);
        }
    }

    /* renamed from: org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends WebViewSwipeContainer {
        final /* synthetic */ ChatAttachAlertBotWebViewLayout this$0;

        public AnonymousClass2(ChatAttachAlertBotWebViewLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(((View.MeasureSpec.getSize(r3) - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(84.0f)) + ChatAttachAlertBotWebViewLayout.m21463$$Nest$fgetmeasureOffsetY(this.this$0), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertBotWebViewLayout this$0;

        public AnonymousClass3(ChatAttachAlertBotWebViewLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ChatAttachAlertBotWebViewLayout.m21464$$Nest$fgetprogressView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertBotWebViewLayout this$0;
        final /* synthetic */ int val$toY;

        public AnonymousClass4(ChatAttachAlertBotWebViewLayout r1, int r2) {
            this.this$0 = r1;
            this.val$toY = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ChatAttachAlertBotWebViewLayout.m21466$$Nest$fgetwebViewContainer(this.this$0).getWebView() == null) goto L6;
            ChatAttachAlertBotWebViewLayout.m21466$$Nest$fgetwebViewContainer(this.this$0).getWebView().setScrollY(this.val$toY);
        L6:
            if (r3 != ChatAttachAlertBotWebViewLayout.m21467$$Nest$fgetwebViewScrollAnimator(this.this$0)) goto L9;
            ChatAttachAlertBotWebViewLayout.m21469$$Nest$fputwebViewScrollAnimator(this.this$0, null);
            return;
        }
    }

    public static class WebProgressView extends View {
        private final SimpleFloatPropertyCompat<WebProgressView> LOAD_PROGRESS_PROPERTY;
        private Paint bluePaint;
        private float loadProgress;
        private Theme.ResourcesProvider resourcesProvider;
        private SpringAnimation springAnimation;

        /* renamed from: $r8$lambda$bagxAIEh4fv6Pl5-Lu4CQ7XYVoQ, reason: not valid java name */
        public static /* synthetic */ float m21470$r8$lambda$bagxAIEh4fv6Pl5Lu4CQ7XYVoQ(WebProgressView r0) {
            return r0.loadProgress;
        }

        public WebProgressView(Context r4, Theme.ResourcesProvider r5) {
            super(r4);
            this.LOAD_PROGRESS_PROPERTY = new SimpleFloatPropertyCompat("loadProgress", new ChatAttachAlertBotWebViewLayout$WebProgressView$$ExternalSyntheticLambda0(), new ChatAttachAlertBotWebViewLayout$WebProgressView$$ExternalSyntheticLambda1()).setMultiplier(100.0f);
            Paint r42 = new Paint(1);
            this.bluePaint = r42;
            this.resourcesProvider = r5;
            r42.setColor(getThemedColor(Theme.key_featuredStickers_addButton));
            this.bluePaint.setStyle(Paint.Style.STROKE);
            this.bluePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.bluePaint.setStrokeCap(Paint.Cap.ROUND);
        }

        @Override // android.view.View
        public void draw(Canvas r9) {
            super.draw(r9);
            if (this.loadProgress <= 0.0f) goto L6;
            float r4 = getHeight() - (this.bluePaint.getStrokeWidth() / 2.0f);
            r9.drawLine(0.0f, r4, getWidth() * this.loadProgress, r4, this.bluePaint);
            return;
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.springAnimation = new SpringAnimation(this, this.LOAD_PROGRESS_PROPERTY).setSpring(new SpringForce().setStiffness(400.0f).setDampingRatio(1.0f));
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.springAnimation.cancel();
            this.springAnimation = null;
        }

        public void setLoadProgress(float r1) {
            this.loadProgress = r1;
            invalidate();
        }

        public void setLoadProgressAnimated(float r3) {
            SpringAnimation r0 = this.springAnimation;
            if (r0 != null) goto L6;
            setLoadProgress(r3);
            return;
        L6:
            r0.getSpring().setFinalPosition(r3 * 100.0f);
            this.springAnimation.start();
        }
    }

    public static class WebViewSwipeContainer extends FrameLayout {
        public static final SimpleFloatPropertyCompat<WebViewSwipeContainer> SWIPE_OFFSET_Y = null;
        private boolean allowFullSizeSwipe;
        private boolean allowSwipes;
        public boolean allowedScrollX;
        public boolean allowedScrollY;
        private Delegate delegate;
        private float drawnSwipeOffsetY;
        private boolean flingInProgress;
        private boolean fullsize;
        private final GestureDetectorCompat gestureDetector;
        private GenericProvider<Void, Boolean> isKeyboardVisible;
        public boolean isPinned;
        public boolean isScrolling;
        private boolean isSwipeDisallowed;
        private boolean isSwipeOffsetAnimationDisallowed;
        private final float minscroll;
        public float offsetY;
        private SpringAnimation offsetYAnimator;
        public boolean opened;
        private float pendingOffsetY;
        private float pendingSwipeOffsetY;
        private long pressDownTime;
        private float pressDownX;
        private float pressDownY;
        private Object renderNode;
        private SpringAnimation scrollAnimator;
        private Runnable scrollEndListener;
        private Runnable scrollListener;
        private boolean scrolledOut;
        public boolean shouldWaitWebViewScroll;
        public boolean stickToEdges;
        private float swipeOffsetY;
        private int swipeStickyRange;
        private float sy;
        public float topActionBarOffsetY;
        private BotWebViewContainer.MyWebView webView;

        /* renamed from: org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
            final /* synthetic */ WebViewSwipeContainer this$0;
            final /* synthetic */ int val$touchSlop;

            public AnonymousClass1(WebViewSwipeContainer r1, int r2) {
                this.this$0 = r1;
                this.val$touchSlop = r2;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent r5, MotionEvent r6, float r7, float r8) {
                if (WebViewSwipeContainer.m21479$$Nest$fgetisSwipeDisallowed(this.this$0) == true) goto L54;
                WebViewSwipeContainer r72 = this.this$0;
                if (r72.isPinned == true) goto L54;
                if (WebViewSwipeContainer.m21474$$Nest$fgetallowSwipes(r72) == false) goto L54;
                if (WebViewSwipeContainer.m21477$$Nest$fgetfullsize(this.this$0) == true) goto L11;
            L12:
                WebViewSwipeContainer r73 = this.this$0;
                if (r73.shouldWaitWebViewScroll == true) goto L15;
            L17:
                float r74 = AndroidUtilities.distance(r5.getX(), r5.getY(), r6.getX(), r6.getY());
                float r52 = r6.getEventTime() - r5.getEventTime();
                if (r8 < AndroidUtilities.dp(650.0f)) goto L49;
                if (r74 > AndroidUtilities.dp(200.0f)) goto L24;
                if (r52 <= 250.0f) goto L49;
            L24:
                if (WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0) != null) goto L26;
            L27:
                WebViewSwipeContainer.m21483$$Nest$fputflingInProgress(this.this$0, true);
                if (WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(this.this$0) >= WebViewSwipeContainer.m21481$$Nest$fgetswipeStickyRange(this.this$0)) goto L34;
                if (WebViewSwipeContainer.m21477$$Nest$fgetfullsize(this.this$0) == true) goto L34;
                this.this$0.stickTo(0.0f);
            L47:
                return true;
            L34:
                if (WebViewSwipeContainer.m21477$$Nest$fgetfullsize(this.this$0) == false) goto L45;
                if (WebViewSwipeContainer.m21473$$Nest$fgetallowFullSizeSwipe(this.this$0) == false) goto L45;
                float r53 = WebViewSwipeContainer.m21476$$Nest$fgetdrawnSwipeOffsetY(this.this$0);
                WebViewSwipeContainer r62 = this.this$0;
                if (r53 != ((-r62.offsetY) + r62.topActionBarOffsetY)) goto L40;
            L43:
                WebViewSwipeContainer r4 = this.this$0;
                r4.stickTo((-r4.offsetY) + r4.topActionBarOffsetY);
                goto L47
            L40:
                if (WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r62) > (-WebViewSwipeContainer.m21481$$Nest$fgetswipeStickyRange(this.this$0))) goto L45;
                if (r8 < AndroidUtilities.dp(1200.0f)) goto L43;
            L45:
                if (WebViewSwipeContainer.m21475$$Nest$fgetdelegate(this.this$0) == null) goto L47;
                WebViewSwipeContainer.m21475$$Nest$fgetdelegate(this.this$0).onDismiss(false);
                goto L47
            L26:
                if (WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).getScrollY() == 0) goto L27;
            L49:
                if (r8 > (-700.0f)) goto L54;
                float r54 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(this.this$0);
                WebViewSwipeContainer r63 = this.this$0;
                if (r54 <= ((-r63.offsetY) + r63.topActionBarOffsetY)) goto L54;
                WebViewSwipeContainer.m21483$$Nest$fputflingInProgress(r63, true);
                WebViewSwipeContainer r42 = this.this$0;
                r42.stickTo((-r42.offsetY) + r42.topActionBarOffsetY);
                return true;
            L15:
                if (r73.allowingScroll(false) == true) goto L17;
            L11:
                if (WebViewSwipeContainer.m21473$$Nest$fgetallowFullSizeSwipe(this.this$0) == true) goto L12;
            L54:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent r12, MotionEvent r13, float r14, float r15) {
                float r122 = WebViewSwipeContainer.m21486$$Nest$mcap(this.this$0, r15);
                WebViewSwipeContainer r132 = this.this$0;
                if (r132.isScrolling == false) goto L5;
            L50:
                WebViewSwipeContainer r133 = this.this$0;
                if (r133.isScrolling == true) goto L53;
            L78:
                return true;
            L53:
                if (r122 >= 0.0f) goto L64;
                float r134 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r133);
                WebViewSwipeContainer r142 = this.this$0;
                if (r134 <= ((-r142.offsetY) + r142.topActionBarOffsetY)) goto L57;
                WebViewSwipeContainer.m21485$$Nest$fputswipeOffsetY(r142, WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r142) - r122);
            L70:
                WebViewSwipeContainer r123 = this.this$0;
                float r135 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r123);
                WebViewSwipeContainer r143 = this.this$0;
                float r152 = (-r143.offsetY) + r143.topActionBarOffsetY;
                float r144 = r143.getHeight();
                WebViewSwipeContainer r0 = this.this$0;
                WebViewSwipeContainer.m21485$$Nest$fputswipeOffsetY(r123, MathUtils.clamp(r135, r152, (r144 - r0.offsetY) + r0.topActionBarOffsetY));
                WebViewSwipeContainer r124 = this.this$0;
                if (r124.isPinned == false) goto L73;
            L76:
                WebViewSwipeContainer r125 = this.this$0;
                float r136 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r125);
                WebViewSwipeContainer r145 = this.this$0;
                WebViewSwipeContainer.m21485$$Nest$fputswipeOffsetY(r125, Math.min(r136, (-r145.offsetY) + r145.topActionBarOffsetY));
            L77:
                this.this$0.invalidateTranslation();
                goto L78
            L73:
                if (WebViewSwipeContainer.m21477$$Nest$fgetfullsize(r124) == false) goto L77;
                if (WebViewSwipeContainer.m21473$$Nest$fgetallowFullSizeSwipe(this.this$0) == true) goto L77;
            L57:
                BotWebViewContainer.MyWebView r137 = WebViewSwipeContainer.m21482$$Nest$fgetwebView(r142);
                WebViewSwipeContainer r146 = this.this$0;
                if (r137 == null) goto L62;
                float r138 = WebViewSwipeContainer.m21482$$Nest$fgetwebView(r146).getScrollY() + r122;
                WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).setScrollY((int) MathUtils.clamp(r138, 0.0f, Math.max(WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).getContentHeight(), WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).getHeight()) - this.this$0.topActionBarOffsetY));
                if (r138 >= 0.0f) goto L70;
                WebViewSwipeContainer r126 = this.this$0;
                WebViewSwipeContainer.m21485$$Nest$fputswipeOffsetY(r126, WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r126) - r138);
                goto L70
            L62:
                WebViewSwipeContainer.m21485$$Nest$fputswipeOffsetY(r146, WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r146) - r122);
                goto L70
            L64:
                if (r122 <= 0.0f) goto L70;
                WebViewSwipeContainer.m21485$$Nest$fputswipeOffsetY(r133, WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r133) - r122);
                if (WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0) == null) goto L70;
                float r127 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(this.this$0);
                WebViewSwipeContainer r139 = this.this$0;
                if (r127 >= ((-r139.offsetY) + r139.topActionBarOffsetY)) goto L70;
                float r128 = WebViewSwipeContainer.m21482$$Nest$fgetwebView(r139).getScrollY();
                float r1310 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(this.this$0);
                WebViewSwipeContainer r147 = this.this$0;
                WebViewSwipeContainer.m21482$$Nest$fgetwebView(r147).setScrollY((int) MathUtils.clamp(r128 - ((r1310 + r147.offsetY) - r147.topActionBarOffsetY), 0.0f, Math.max(WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).getContentHeight(), WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).getHeight()) - this.this$0.topActionBarOffsetY));
                goto L70
            L5:
                if (WebViewSwipeContainer.m21479$$Nest$fgetisSwipeDisallowed(r132) == true) goto L50;
                if (WebViewSwipeContainer.m21474$$Nest$fgetallowSwipes(this.this$0) == false) goto L50;
                WebViewSwipeContainer r1311 = this.this$0;
                int r2 = 0;
                if (r1311.shouldWaitWebViewScroll == false) goto L15;
                float r1312 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(r1311);
                WebViewSwipeContainer r153 = this.this$0;
                if (r1312 != ((-r153.offsetY) + r153.topActionBarOffsetY)) goto L15;
                if (r153.allowingScroll(false) == false) goto L50;
            L15:
                if (((Boolean) WebViewSwipeContainer.m21478$$Nest$fgetisKeyboardVisible(this.this$0).provide(null)).booleanValue() == false) goto L20;
                float r1313 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(this.this$0);
                WebViewSwipeContainer r154 = this.this$0;
                if (r1313 != ((-r154.offsetY) + r154.topActionBarOffsetY)) goto L20;
                WebViewSwipeContainer.m21484$$Nest$fputisSwipeDisallowed(r154, true);
            L20:
                if (Math.abs(r122) < this.val$touchSlop) goto L38;
                if ((Math.abs(r122) * 1.5f) < Math.abs(r14)) goto L38;
                float r1314 = WebViewSwipeContainer.m21480$$Nest$fgetswipeOffsetY(this.this$0);
                WebViewSwipeContainer r3 = this.this$0;
                if (r1314 == ((-r3.offsetY) + r3.topActionBarOffsetY)) goto L26;
            L31:
                this.this$0.isScrolling = true;
                MotionEvent r129 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
            L33:
                if (r2 >= this.this$0.getChildCount()) goto L35;
                this.this$0.getChildAt(r2).dispatchTouchEvent(r129);
                r2 = r2 + 1;
                goto L33
            L35:
                r129.recycle();
                return true;
            L26:
                if (WebViewSwipeContainer.m21482$$Nest$fgetwebView(r3) == null) goto L31;
                if (r122 >= 0.0f) goto L38;
                if (WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0).getScrollY() == 0) goto L31;
            L38:
                if (WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0) == null) goto L46;
                BotWebViewContainer.MyWebView r1315 = WebViewSwipeContainer.m21482$$Nest$fgetwebView(this.this$0);
                if (r14 < 0.0f) goto L42;
                int r22 = 1;
            L44:
                if (r1315.canScrollHorizontally(r22) == false) goto L46;
            L49:
                WebViewSwipeContainer.m21484$$Nest$fputisSwipeDisallowed(this.this$0, true);
                goto L50
            L42:
                r22 = -1;
            L46:
                if (Math.abs(r14) < this.val$touchSlop) goto L50;
                if ((Math.abs(r14) * 1.5f) < Math.abs(r122)) goto L50;
                goto L49
            }
        }

        public interface Delegate {
            void onDismiss(boolean r1);
        }

        /* renamed from: $r8$lambda$B6SCKwI0Ul0i3QX6YTUA3w-NMAQ, reason: not valid java name */
        public static /* synthetic */ Boolean m21471$r8$lambda$B6SCKwI0Ul0i3QX6YTUA3wNMAQ(Void r0) {
            return Boolean.FALSE;
        }

        /* renamed from: $r8$lambda$BXPDav7lWXXIyR1Olq3-oGDxvoA, reason: not valid java name */
        public static /* synthetic */ void m21472$r8$lambda$BXPDav7lWXXIyR1Olq3oGDxvoA(WebViewSwipeContainer r0, float r1, float r2, boolean r3, float r4, DynamicAnimation r5, float r6, float r7) {
            r0.lambda$setOffsetY$1(r1, r2, r3, r4, r5, r6, r7);
        }

        public static /* synthetic */ void $r8$lambda$Rh8JMPlKxnazjnKCvZ1SgHFlQIQ(WebViewSwipeContainer r0) {
            r0.updateDrawn();
        }

        public static /* synthetic */ void $r8$lambda$VTGdtg7qVvXSG6tavlMcqIcuRhU(WebViewSwipeContainer r0, float r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            r0.lambda$setOffsetY$2(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$nlzq3YI7qUhDTQzPFCBVU6aKT6Q(WebViewSwipeContainer r0, Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            r0.lambda$stickTo$3(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetallowFullSizeSwipe, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21473$$Nest$fgetallowFullSizeSwipe(WebViewSwipeContainer r0) {
            return r0.allowFullSizeSwipe;
        }

        /* renamed from: -$$Nest$fgetallowSwipes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21474$$Nest$fgetallowSwipes(WebViewSwipeContainer r0) {
            return r0.allowSwipes;
        }

        /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Delegate m21475$$Nest$fgetdelegate(WebViewSwipeContainer r0) {
            return r0.delegate;
        }

        /* renamed from: -$$Nest$fgetdrawnSwipeOffsetY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m21476$$Nest$fgetdrawnSwipeOffsetY(WebViewSwipeContainer r0) {
            return r0.drawnSwipeOffsetY;
        }

        /* renamed from: -$$Nest$fgetfullsize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21477$$Nest$fgetfullsize(WebViewSwipeContainer r0) {
            return r0.fullsize;
        }

        /* renamed from: -$$Nest$fgetisKeyboardVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GenericProvider m21478$$Nest$fgetisKeyboardVisible(WebViewSwipeContainer r0) {
            return r0.isKeyboardVisible;
        }

        /* renamed from: -$$Nest$fgetisSwipeDisallowed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21479$$Nest$fgetisSwipeDisallowed(WebViewSwipeContainer r0) {
            return r0.isSwipeDisallowed;
        }

        /* renamed from: -$$Nest$fgetswipeOffsetY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m21480$$Nest$fgetswipeOffsetY(WebViewSwipeContainer r0) {
            return r0.swipeOffsetY;
        }

        /* renamed from: -$$Nest$fgetswipeStickyRange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m21481$$Nest$fgetswipeStickyRange(WebViewSwipeContainer r0) {
            return r0.swipeStickyRange;
        }

        /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BotWebViewContainer.MyWebView m21482$$Nest$fgetwebView(WebViewSwipeContainer r0) {
            return r0.webView;
        }

        /* renamed from: -$$Nest$fputflingInProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21483$$Nest$fputflingInProgress(WebViewSwipeContainer r0, boolean r1) {
            r0.flingInProgress = r1;
        }

        /* renamed from: -$$Nest$fputisSwipeDisallowed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21484$$Nest$fputisSwipeDisallowed(WebViewSwipeContainer r0, boolean r1) {
            r0.isSwipeDisallowed = r1;
        }

        /* renamed from: -$$Nest$fputswipeOffsetY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21485$$Nest$fputswipeOffsetY(WebViewSwipeContainer r0, float r1) {
            r0.swipeOffsetY = r1;
        }

        /* renamed from: -$$Nest$mcap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m21486$$Nest$mcap(WebViewSwipeContainer r0, float r1) {
            return r0.cap(r1);
        }

        static {
            SWIPE_OFFSET_Y = new SimpleFloatPropertyCompat("swipeOffsetY", new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda2(), new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda3());
        }

        public WebViewSwipeContainer(Context r4) {
            super(r4);
            this.topActionBarOffsetY = ActionBar.getCurrentActionBarHeight();
            this.offsetY = 0.0f;
            this.pendingOffsetY = -1.0f;
            this.pendingSwipeOffsetY = -2.1474836E9f;
            this.isKeyboardVisible = new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda4();
            this.allowSwipes = true;
            this.sy = 0.0f;
            this.scrolledOut = false;
            this.minscroll = AndroidUtilities.dp(60.0f);
            this.stickToEdges = true;
            this.gestureDetector = new GestureDetectorCompat(r4, new AnonymousClass1(this, ViewConfiguration.get(r4).getScaledTouchSlop()));
            updateStickyRange();
        }

        private float cap(float r3) {
            if (this.scrolledOut == false) goto L5;
            return r3;
        L5:
            float r0 = this.sy + r3;
            this.sy = r0;
            float r32 = Math.abs(r0);
            float r02 = this.minscroll;
            if (r32 <= r02) goto L13;
            this.scrolledOut = true;
            float r2 = this.sy;
            if (r2 <= 0.0f) goto L12;
            return r2 - r02;
        L12:
            return r2 + r02;
        L13:
            return 0.0f;
        }

        private /* synthetic */ void lambda$setOffsetY$1(float r1, float r2, boolean r3, float r4, DynamicAnimation r5, float r6, float r7) {
            this.offsetY = r6;
            if (r1 != 0.0f) goto L5;
            float r62 = 1.0f;
        L6:
            if (r3 == false) goto L8;
            this.swipeOffsetY = MathUtils.clamp(this.swipeOffsetY - (r62 * Math.max(0.0f, r1)), (-this.offsetY) + this.topActionBarOffsetY, (getHeight() - this.offsetY) + this.topActionBarOffsetY);
        L8:
            SpringAnimation r12 = this.scrollAnimator;
            if (r12 != null) goto L11;
        L13:
            invalidateTranslation();
            return;
        L11:
            if (r12.getSpring().getFinalPosition() != ((-r2) + this.topActionBarOffsetY)) goto L13;
            this.scrollAnimator.getSpring().setFinalPosition((-r4) + this.topActionBarOffsetY);
            goto L13
        L5:
            r62 = (r6 - r2) / r1;
            goto L6
        }

        private /* synthetic */ void lambda$setOffsetY$2(float r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            this.offsetYAnimator = null;
            if (r3 == true) goto L6;
            this.offsetY = r1;
            invalidateTranslation();
            return;
        L6:
            this.pendingOffsetY = r1;
        }

        private /* synthetic */ void lambda$stickTo$3(Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            if (r2 != this.scrollAnimator) goto L15;
            this.scrollAnimator = null;
            if (r1 == null) goto L7;
            r1.run();
        L7:
            Runnable r12 = this.scrollEndListener;
            if (r12 == null) goto L10;
            r12.run();
        L10:
            float r13 = this.pendingOffsetY;
            if (r13 == (-1.0f)) goto L13;
            boolean r32 = this.isSwipeOffsetAnimationDisallowed;
            this.isSwipeOffsetAnimationDisallowed = true;
            setOffsetY(r13);
            this.pendingOffsetY = -1.0f;
            this.isSwipeOffsetAnimationDisallowed = r32;
        L13:
            this.pendingSwipeOffsetY = -2.1474836E9f;
            return;
        }

        private void updateDrawn() {
            this.drawnSwipeOffsetY = this.swipeOffsetY;
        }

        private void updateStickyRange() {
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            float r02 = 8.0f;
        L6:
            this.swipeStickyRange = AndroidUtilities.dp(r02);
            return;
        L5:
            r02 = 64.0f;
            goto L6
        }

        public void allowThisScroll(boolean r1, boolean r2) {
            this.allowedScrollX = r1;
            this.allowedScrollY = r2;
        }

        public boolean allowingScroll(boolean r2) {
            BotWebViewContainer.MyWebView r0 = this.webView;
            if (r0 != null) goto L5;
            return true;
        L5:
            if (r0.injectedJS == false) goto L18;
            if (r2 == false) goto L11;
            if (this.allowedScrollX == true) goto L19;
            return false;
        L19:
            return true;
        L11:
            if (this.allowedScrollY == true) goto L20;
            return false;
        L20:
            return true;
        L18:
            return true;
        }

        public void cancelStickTo() {
            SpringAnimation r0 = this.offsetYAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            SpringAnimation r1 = this.scrollAnimator;
            if (r1 == null) goto L9;
            r1.cancel();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            if (r5.isHardwareAccelerated() == false) goto L12;
            Object r0 = this.renderNode;
            if (r0 == null) goto L7;
            RenderNode r02 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r0);
            r02.setPosition(0, 0, getWidth(), getHeight());
            Canvas r03 = r02.beginRecording();
        L8:
            super.dispatchDraw(r03);
            Object r4 = this.renderNode;
            if (r4 == null) goto L14;
            RenderNode r42 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r4);
            r42.endRecording();
            r5.drawRenderNode(r42);
            return;
        L14:
            return;
        L7:
            r03 = r5;
            goto L8
        L12:
            super.dispatchDraw(r5);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r10) {
            if (this.isScrolling == false) goto L8;
            if (r10.getActionIndex() == 0) goto L8;
            return false;
        L8:
            if (r10.getAction() != 0) goto L12;
            this.pressDownTime = r10.getEventTime();
            this.pressDownX = r10.getX();
            this.pressDownY = r10.getY();
            this.scrolledOut = false;
            this.sy = 0.0f;
            if (this.shouldWaitWebViewScroll == false) goto L12;
            this.allowedScrollX = false;
            this.allowedScrollY = false;
        L12:
            MotionEvent r0 = MotionEvent.obtain(r10);
            int r3 = r10.getActionIndex();
            if (Build.VERSION.SDK_INT < 29) goto L15;
            r0.setLocation(r10.getRawX(r3), r10.getRawY(r3));
        L16:
            boolean r32 = this.gestureDetector.onTouchEvent(r0);
            r0.recycle();
            if (r10.getAction() != 1) goto L19;
        L20:
            boolean r02 = this.isScrolling;
            this.isSwipeDisallowed = false;
            this.isScrolling = false;
            if (this.isPinned == false) goto L23;
        L60:
            boolean r9 = super.dispatchTouchEvent(r10);
            if (r9 == true) goto L66;
            if (r32 == true) goto L66;
            if (r10.getAction() != 0) goto L66;
            return true;
        L66:
            if (r9 == true) goto L70;
            if (r32 == true) goto L70;
            return false;
        L70:
            return true;
        L23:
            if (this.fullsize == false) goto L28;
            if (this.allowFullSizeSwipe == false) goto L60;
        L28:
            if (this.flingInProgress == false) goto L31;
            this.flingInProgress = false;
            goto L60
        L31:
            if (this.allowSwipes == false) goto L60;
            if (this.shouldWaitWebViewScroll == true) goto L35;
        L38:
            float r5 = this.swipeOffsetY;
            int r6 = this.swipeStickyRange;
            if (r5 > (-r6)) goto L44;
            if (this.stickToEdges == false) goto L60;
            stickTo((-this.offsetY) + this.topActionBarOffsetY);
            goto L60
        L44:
            if (r5 > (-r6)) goto L46;
        L50:
            float r2 = AndroidUtilities.distance(r10.getX(), r10.getY(), this.pressDownX, this.pressDownY);
            long r52 = r10.getEventTime() - this.pressDownTime;
            if (this.delegate == null) goto L58;
            if (r52 <= 250) goto L55;
        L56:
            this.delegate.onDismiss(!r02);
            goto L60
        L55:
            if (r2 > AndroidUtilities.dp(200.0f)) goto L56;
        L58:
            if (this.stickToEdges == false) goto L60;
            stickTo((-this.offsetY) + this.topActionBarOffsetY);
            goto L60
        L46:
            if (r5 > r6) goto L50;
            if (this.stickToEdges == false) goto L60;
            stickTo(0.0f);
            goto L60
        L35:
            if (this.swipeOffsetY == ((-this.offsetY) + this.topActionBarOffsetY)) goto L60;
            if (allowingScroll(false) == false) goto L60;
        L19:
            if (r10.getAction() != 3) goto L60;
        L15:
            r0.setLocation(r10.getX(r3) + (r10.getRawX() - r10.getX()), r10.getY(r3) + (r10.getRawY() - r10.getY()));
            goto L16
        }

        public float getOffsetY() {
            return this.offsetY;
        }

        public Object getRenderNode() {
            if (this.renderNode != null) goto L8;
            if (Build.VERSION.SDK_INT < 31) goto L8;
            this.renderNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("WebViewSwipeContainer");
        L8:
            return this.renderNode;
        }

        public float getSwipeOffsetY() {
            return this.swipeOffsetY;
        }

        public float getTopActionBarOffsetY() {
            return this.topActionBarOffsetY;
        }

        public void invalidateTranslation() {
            setTranslationY(Math.max(this.topActionBarOffsetY, this.offsetY + this.swipeOffsetY));
            AndroidUtilities.cancelRunOnUIThread(new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda5(this));
            AndroidUtilities.runOnUIThread(new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda5(this));
            Runnable r3 = this.scrollListener;
            if (r3 == null) goto L6;
            r3.run();
        L6:
            if (Bulletin.getVisibleBulletin() == null) goto L9;
            Bulletin.getVisibleBulletin().updatePosition();
            return;
        }

        public boolean isAllowedSwipes() {
            return this.allowSwipes;
        }

        public boolean isFullSize() {
            return this.fullsize;
        }

        public boolean isSwipeInProgress() {
            return this.isScrolling;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r1) {
            super.onConfigurationChanged(r1);
            updateStickyRange();
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean r1) {
            super.requestDisallowInterceptTouchEvent(r1);
            if (r1 == false) goto L6;
            this.isSwipeDisallowed = true;
            this.isScrolling = false;
            return;
        }

        public void setAllowFullSizeSwipe(boolean r1) {
            this.allowFullSizeSwipe = r1;
        }

        public void setAllowSwipes(boolean r2) {
            if (this.allowSwipes == r2) goto L6;
            this.allowSwipes = r2;
            return;
        }

        public void setDelegate(Delegate r1) {
            this.delegate = r1;
        }

        public void setForceOffsetY(float r1) {
            this.offsetY = r1;
            invalidateTranslation();
        }

        public void setFullSize(boolean r2) {
            if (this.fullsize == r2) goto L12;
            this.fullsize = r2;
            if (r2 == true) goto L7;
            stickTo(0.0f);
            return;
        L7:
            if (this.opened == false) goto L13;
            stickTo((-getOffsetY()) + getTopActionBarOffsetY());
            return;
        L13:
            return;
        }

        public void setIsKeyboardVisible(GenericProvider<Void, Boolean> r1) {
            this.isKeyboardVisible = r1;
        }

        public void setOffsetY(float r8) {
            if (this.pendingSwipeOffsetY == (-2.1474836E9f)) goto L6;
            this.pendingOffsetY = r8;
            return;
        L6:
            SpringAnimation r0 = this.offsetYAnimator;
            if (r0 == null) goto L9;
            r0.cancel();
        L9:
            float r4 = this.offsetY;
            float r3 = r8 - r4;
            if (Math.abs((this.swipeOffsetY + r4) - this.topActionBarOffsetY) > AndroidUtilities.dp(1.0f)) goto L13;
            boolean r02 = true;
        L12:
            boolean r5 = r02;
            if (this.isSwipeOffsetAnimationDisallowed == true) goto L21;
            SpringAnimation r03 = this.offsetYAnimator;
            if (r03 == null) goto L19;
            r03.cancel();
        L19:
            SpringAnimation r7 = new SpringAnimation(new FloatValueHolder(r4)).setSpring(new SpringForce(r8).setStiffness(1400.0f).setDampingRatio(1.0f)).addUpdateListener(new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda0(this, r3, r4, r5, r8)).addEndListener(new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda1(this, r8));
            this.offsetYAnimator = r7;
            r7.start();
            return;
        L21:
            this.offsetY = r8;
            if (r5 == false) goto L24;
            this.swipeOffsetY = MathUtils.clamp(this.swipeOffsetY - Math.max(0.0f, r3), (-this.offsetY) + this.topActionBarOffsetY, (getHeight() - this.offsetY) + this.topActionBarOffsetY);
        L24:
            invalidateTranslation();
            return;
        L13:
            r02 = false;
            goto L12
        }

        public void setScrollEndListener(Runnable r1) {
            this.scrollEndListener = r1;
        }

        public void setScrollListener(Runnable r1) {
            this.scrollListener = r1;
        }

        public void setShouldWaitWebViewScroll(boolean r1) {
            this.shouldWaitWebViewScroll = r1;
        }

        public void setSwipeOffsetAnimationDisallowed(boolean r1) {
            this.isSwipeOffsetAnimationDisallowed = r1;
        }

        public void setSwipeOffsetY(float r1) {
            this.swipeOffsetY = r1;
            invalidateTranslation();
        }

        public void setTopActionBarOffsetY(float r1) {
            this.topActionBarOffsetY = r1;
            invalidateTranslation();
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
        }

        public void setWebView(BotWebViewContainer.MyWebView r1) {
            this.webView = r1;
        }

        public void stickTo(float r2) {
            stickTo(r2, null);
        }

        public void stickTo(float r2, Runnable r3) {
            stickTo(r2, false, r3);
        }

        public void stickTo(float r2, boolean r3, Runnable r4) {
            if (this.isPinned == false) goto L5;
        L6:
            if (r3 == true) goto L9;
            r2 = (-getOffsetY()) + getTopActionBarOffsetY();
        L9:
            if (this.swipeOffsetY == r2) goto L23;
            SpringAnimation r32 = this.scrollAnimator;
            if (r32 != null) goto L13;
        L15:
            this.pendingSwipeOffsetY = r2;
            SpringAnimation r33 = this.offsetYAnimator;
            if (r33 == null) goto L18;
            r33.cancel();
        L18:
            SpringAnimation r34 = this.scrollAnimator;
            if (r34 == null) goto L21;
            r34.cancel();
        L21:
            SpringAnimation r22 = new SpringAnimation(this, SWIPE_OFFSET_Y, r2).setSpring(new SpringForce(r2).setStiffness(1200.0f).setDampingRatio(1.0f)).addEndListener(new ChatAttachAlertBotWebViewLayout$WebViewSwipeContainer$$ExternalSyntheticLambda6(this, r4));
            this.scrollAnimator = r22;
            r22.start();
            return;
        L13:
            if (r32.getSpring().getFinalPosition() != r2) goto L15;
        L23:
            if (r4 == null) goto L25;
            r4.run();
        L25:
            Runnable r1 = this.scrollEndListener;
            if (r1 == null) goto L29;
            r1.run();
            return;
        L29:
            return;
        L5:
            if (this.fullsize == false) goto L9;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$2fcCEzrjjjXCYKmYQp5ku4Z3rug(ChatAttachAlertBotWebViewLayout r0, Float r1) {
        r0.lambda$new$8(r1);
    }

    /* renamed from: $r8$lambda$41O-9uLZF4x3z6I2VMiIO8-nKNE, reason: not valid java name */
    public static /* synthetic */ void m21456$r8$lambda$41O9uLZF4x3z6I2VMiIO8nKNE(ChatAttachAlertBotWebViewLayout r0, ValueAnimator r1) {
        r0.lambda$onPanTransitionStart$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$7nrhuboaC5tWNEp_50I3md9fsxE(ChatAttachAlertBotWebViewLayout r0) {
        r0.lambda$new$3();
    }

    /* renamed from: $r8$lambda$D4Qx-n-UwGjQp4z7kQKSdn7zXdk, reason: not valid java name */
    public static /* synthetic */ void m21457$r8$lambda$D4QxnUwGjQp4z7kQKSdn7zXdk(ChatAttachAlertBotWebViewLayout r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$DsMc6MmiNj0E738tYWY1WPN8w40(ChatAttachAlertBotWebViewLayout r0, String r1) {
        r0.lambda$showJustAddedBulletin$14(r1);
    }

    /* renamed from: $r8$lambda$GBQMbm9Ll6WDa8-ahkRXUoclrpQ, reason: not valid java name */
    public static /* synthetic */ void m21458$r8$lambda$GBQMbm9Ll6WDa8ahkRXUoclrpQ(ChatAttachAlertBotWebViewLayout r0) {
        r0.requestEnableKeyboard();
    }

    public static /* synthetic */ void $r8$lambda$S27g5mTpyDXD0fQtUwIKquCv5Lg(ChatAttachAlertBotWebViewLayout r0, AlertDialog r1, int r2) {
        r0.lambda$onCheckDismissByUser$9(r1, r2);
    }

    /* renamed from: $r8$lambda$_9n-cjOB3ORO_6r_NM3O78n4rIk, reason: not valid java name */
    public static /* synthetic */ void m21459$r8$lambda$_9ncjOB3ORO_6r_NM3O78n4rIk(ChatAttachAlertBotWebViewLayout r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_SWbIenTUC3NEdgW7esKxBcNRZk(ChatAttachAlertBotWebViewLayout r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$requestWebView$13(r1, r2, r3);
    }

    public static /* synthetic */ Boolean $r8$lambda$gFbVN77pkc1r3CRc54r_bAD_xj8(ChatAttachAlertBotWebViewLayout r0, Void r1) {
        return r0.lambda$new$6(r1);
    }

    /* renamed from: $r8$lambda$h-5Ef5B8tpTXtjERYt3ws7EkLeM, reason: not valid java name */
    public static /* synthetic */ void m21460$r8$lambda$h5Ef5B8tpTXtjERYt3ws7EkLeM(ChatAttachAlertBotWebViewLayout r0, ValueAnimator r1) {
        r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$hVJl8jnTsqWMTeIi4eb9Hrp_d4k(ChatAttachAlertBotWebViewLayout r0, TLRPC.TL_error r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jBSyAx88Et7Sb8rfcn0wMS4UWls(ChatAttachAlertBotWebViewLayout r0) {
        r0.lambda$onShown$11();
    }

    public static /* synthetic */ void $r8$lambda$r_gZbisvceL70VpOeXNnhPUQl60(ChatAttachAlertBotWebViewLayout r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$y4tKjPnlla6WJyl5RbIOahXftPY(ChatAttachAlertBotWebViewLayout r0, boolean r1) {
        r0.lambda$new$5(r1);
    }

    /* renamed from: $r8$lambda$zHLZqPBtNu0M-836BOFMx8UIjXc, reason: not valid java name */
    public static /* synthetic */ void m21461$r8$lambda$zHLZqPBtNu0M836BOFMx8UIjXc(ChatAttachAlertBotWebViewLayout r0, TLObject r1, int r2) {
        r0.lambda$requestWebView$12(r1, r2);
    }

    /* renamed from: -$$Nest$fgetisBotButtonAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21462$$Nest$fgetisBotButtonAvailable(ChatAttachAlertBotWebViewLayout r0) {
        return r0.isBotButtonAvailable;
    }

    /* renamed from: -$$Nest$fgetmeasureOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21463$$Nest$fgetmeasureOffsetY(ChatAttachAlertBotWebViewLayout r0) {
        return r0.measureOffsetY;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebProgressView m21464$$Nest$fgetprogressView(ChatAttachAlertBotWebViewLayout r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetswipeContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebViewSwipeContainer m21465$$Nest$fgetswipeContainer(ChatAttachAlertBotWebViewLayout r0) {
        return r0.swipeContainer;
    }

    /* renamed from: -$$Nest$fgetwebViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotWebViewContainer m21466$$Nest$fgetwebViewContainer(ChatAttachAlertBotWebViewLayout r0) {
        return r0.webViewContainer;
    }

    /* renamed from: -$$Nest$fgetwebViewScrollAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m21467$$Nest$fgetwebViewScrollAnimator(ChatAttachAlertBotWebViewLayout r0) {
        return r0.webViewScrollAnimator;
    }

    /* renamed from: -$$Nest$fputisBotButtonAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21468$$Nest$fputisBotButtonAvailable(ChatAttachAlertBotWebViewLayout r0, boolean r1) {
        r0.isBotButtonAvailable = r1;
    }

    /* renamed from: -$$Nest$fputwebViewScrollAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21469$$Nest$fputwebViewScrollAnimator(ChatAttachAlertBotWebViewLayout r0, ValueAnimator r1) {
        r0.webViewScrollAnimator = r1;
    }

    public ChatAttachAlertBotWebViewLayout(ChatAttachAlert r13, Context r14, Theme.ResourcesProvider r15) {
        super(r13, r14, r15);
        this.pollRunnable = new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda3(this);
        ActionBarMenuItem r132 = this.parentAlert.actionBar.createMenu().addItem(0, R.drawable.ic_ab_other);
        this.otherItem = r132;
        r132.addSubItem(R.id.menu_open_bot, R.drawable.msg_bot, LocaleController.getString(R.string.BotWebViewOpenBot));
        this.pinItem = this.otherItem.addSubItem(R.id.menu_pin, R.drawable.msg_pin, LocaleController.getString(R.string.PinMessage));
        ActionBarMenuSubItem r133 = this.otherItem.addSubItem(R.id.menu_settings, R.drawable.msg_settings, LocaleController.getString(R.string.BotWebViewSettings));
        this.settingsItem = r133;
        r133.setVisibility(8);
        this.otherItem.addSubItem(R.id.menu_reload_page, R.drawable.msg_retry, LocaleController.getString(R.string.BotWebViewReloadPage));
        ActionBarMenuSubItem r134 = this.otherItem.addSubItem(R.id.menu_add_to_home_screen_bot, R.drawable.msg_home, LocaleController.getString(R.string.AddShortcut));
        this.addToHomeScreenItem = r134;
        r134.setVisibility(8);
        this.otherItem.addSubItem(R.id.menu_tos_bot, R.drawable.menu_intro, LocaleController.getString(R.string.BotWebViewToS));
        this.otherItem.addSubItem(R.id.menu_report_bot, R.drawable.msg_report, LocaleController.getString(R.string.BotWebViewReportBot));
        this.otherItem.addSubItem(R.id.menu_delete_bot, R.drawable.msg_delete, LocaleController.getString(R.string.BotWebViewDeleteBot));
        this.webViewContainer = new AnonymousClass1(this, r14, r15, getThemedColor(Theme.key_dialogBackground), true);
        AnonymousClass2 r12 = new AnonymousClass2(this, r14);
        this.swipeContainer = r12;
        r12.addView(this.webViewContainer, LayoutHelper.createFrame(-1, -1.0f));
        this.swipeContainer.setScrollListener(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda4(this));
        this.swipeContainer.setScrollEndListener(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda5(this));
        this.swipeContainer.setDelegate(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda6(this));
        this.swipeContainer.setIsKeyboardVisible(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda7(this));
        addView(this.swipeContainer, LayoutHelper.createFrame(-1, -1.0f));
        WebProgressView r122 = new WebProgressView(r14, r15);
        this.progressView = r122;
        addView(r122, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 0.0f, 84.0f));
        this.webViewContainer.setWebViewProgressListener(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda8(this));
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewTheme);
    }

    private /* synthetic */ void lambda$new$0(TLRPC.TL_error r3) {
        if (this.destroyed == false) goto L5;
        return;
    L5:
        if (r3 == null) goto L8;
        this.parentAlert.dismiss();
        return;
    L8:
        AndroidUtilities.runOnUIThread(this.pollRunnable, 60000);
    }

    private /* synthetic */ void lambda$new$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda15(this, r2));
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.destroyed == true) goto L22;
        TLRPC.TL_messages_prolongWebView r0 = new TLRPC.TL_messages_prolongWebView();
        r0.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botId);
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.peerId);
        r0.query_id = this.queryId;
        r0.silent = this.silent;
        if (this.replyToMsgId == 0) goto L11;
        TLRPC.InputReplyTo r1 = SendMessagesHelper.getInstance(this.currentAccount).createReplyInput(this.replyToMsgId);
        r0.reply_to = r1;
        if (this.monoforumTopicId == 0) goto L9;
        r1.monoforum_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(this.monoforumTopicId);
        r0.reply_to.flags |= 32;
    L9:
        r0.flags |= 1;
    L14:
        if (this.peerId >= 0) goto L20;
        TLRPC.ChatFull r12 = MessagesController.getInstance(this.currentAccount).getChatFull(-this.peerId);
        if (r12 == null) goto L20;
        TLRPC.Peer r13 = r12.default_send_as;
        if (r13 == null) goto L20;
        r0.send_as = MessagesController.getInstance(this.currentAccount).getInputPeer(r13);
        r0.flags |= 8192;
    L20:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda12(this));
        return;
    L11:
        if (this.monoforumTopicId == 0) goto L14;
        TLRPC.TL_inputReplyToMonoForum r14 = new TLRPC.TL_inputReplyToMonoForum();
        r0.reply_to = r14;
        r14.monoforum_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(this.monoforumTopicId);
        r0.flags |= 1;
        goto L14
    }

    private /* synthetic */ void lambda$new$3() {
        this.parentAlert.updateLayout(this, true, 0);
        this.webViewContainer.invalidateViewPortHeight();
        this.lastSwipeTime = System.currentTimeMillis();
    }

    private /* synthetic */ void lambda$new$4() {
        this.webViewContainer.invalidateViewPortHeight(true);
    }

    private /* synthetic */ void lambda$new$5(boolean r1) {
        if (onCheckDismissByUser() == true) goto L6;
        this.swipeContainer.stickTo(0.0f);
        return;
    }

    private /* synthetic */ Boolean lambda$new$6(Void r1) {
        if (this.parentAlert.sizeNotifierFrameLayout.getKeyboardHeight() < AndroidUtilities.dp(20.0f)) goto L5;
        boolean r0 = true;
    L7:
        return Boolean.valueOf(r0);
    L5:
        r0 = false;
        goto L7
    }

    private /* synthetic */ void lambda$new$7(ValueAnimator r1) {
        this.progressView.setAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$new$8(Float r3) {
        this.progressView.setLoadProgressAnimated(r3.floatValue());
        if (r3.floatValue() != 1.0f) goto L6;
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(200);
        r32.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r32.addUpdateListener(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda11(this));
        r32.addListener(new AnonymousClass3(this));
        r32.start();
        requestEnableKeyboard();
        return;
    }

    private /* synthetic */ void lambda$onCheckDismissByUser$9(AlertDialog r1, int r2) {
        this.parentAlert.dismiss();
    }

    private /* synthetic */ void lambda$onPanTransitionStart$10(ValueAnimator r2) {
        int r22 = ((Integer) r2.getAnimatedValue()).intValue();
        if (this.webViewContainer.getWebView() == null) goto L6;
        this.webViewContainer.getWebView().setScrollY(r22);
        return;
    }

    private /* synthetic */ void lambda$onShown$11() {
        this.webViewContainer.restoreButtonData();
    }

    private /* synthetic */ void lambda$requestWebView$12(TLObject r3, int r4) {
        if ((r3 instanceof TLRPC.TL_webViewResultUrl) == false) goto L6;
        TLRPC.TL_webViewResultUrl r32 = (TLRPC.TL_webViewResultUrl) r3;
        this.queryId = r32.query_id;
        this.webViewContainer.loadUrl(r4, r32.url, r32.same_origin);
        AndroidUtilities.runOnUIThread(this.pollRunnable);
        return;
    }

    private /* synthetic */ void lambda$requestWebView$13(int r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda13(this, r2, r1));
    }

    private /* synthetic */ void lambda$showJustAddedBulletin$14(String r2) {
        BulletinFactory.of(this.parentAlert.getContainer(), this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(r2)).setDuration(5000).show(true);
    }

    private void requestEnableKeyboard() {
        BaseFragment r0 = this.parentAlert.getBaseFragment();
        if ((r0 instanceof ChatActivity) == true) goto L5;
    L8:
        this.parentAlert.getWindow().setSoftInputMode(20);
        setFocusable(true);
        this.parentAlert.setFocusable(true);
        return;
    L5:
        if (((ChatActivity) r0).contentView.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L8;
        AndroidUtilities.hideKeyboard(this.parentAlert.baseFragment.getFragmentView());
        AndroidUtilities.runOnUIThread(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda10(this), 250);
    }

    public boolean canExpandByRequest() {
        return !this.swipeContainer.isSwipeInProgress();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.webViewResultSent) goto L9;
        long r32 = ((Long) r5[0]).longValue();
        if (this.queryId != r32) goto L12;
        this.webViewContainer.destroyWebView();
        this.needReload = true;
        this.parentAlert.dismiss();
        return;
    L12:
        return;
    L9:
        if (r3 != NotificationCenter.didSetNewTheme) goto L13;
        this.webViewContainer.updateFlickerBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        return;
    }

    public void disallowSwipeOffsetAnimation() {
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(true);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getButtonsHideOffset() {
        return ((int) this.swipeContainer.getTopActionBarOffsetY()) + AndroidUtilities.dp(12.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        return (int) (this.swipeContainer.getSwipeOffsetY() + this.swipeContainer.getOffsetY());
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCustomActionBarBackground() {
        return this.customActionBarBackground;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCustomBackground() {
        return this.customBackground;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return (int) this.swipeContainer.getOffsetY();
    }

    public String getStartCommand() {
        return this.startCommand;
    }

    public WebViewSwipeContainer getSwipeContainer() {
        return this.swipeContainer;
    }

    public BotWebViewContainer getWebViewContainer() {
        return this.webViewContainer;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean hasCustomActionBarBackground() {
        return this.hasCustomActionBarBackground;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean hasCustomBackground() {
        return this.hasCustomBackground;
    }

    public boolean isBotButtonAvailable() {
        return this.isBotButtonAvailable;
    }

    public boolean needReload() {
        if (this.needReload == false) goto L6;
        this.needReload = false;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onBackPressed() {
        if (this.webViewContainer.onBackPressed() == false) goto L5;
        return true;
    L5:
        onCheckDismissByUser();
        return true;
    }

    public boolean onCheckDismissByUser() {
        if (this.needCloseConfirmation == false) goto L10;
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        if (r0 == null) goto L7;
        String r02 = ContactsController.formatName(r0.first_name, r0.last_name);
    L8:
        AlertDialog r03 = new AlertDialog.Builder(getContext()).setTitle(r02).setMessage(LocaleController.getString(R.string.BotWebViewChangesMayNotBeSaved)).setPositiveButton(LocaleController.getString(R.string.BotWebViewCloseAnyway), new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda1(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r03.show();
        ((TextView) r03.getButton(-1)).setTextColor(getThemedColor(Theme.key_text_RedBold));
        return false;
    L7:
        r02 = null;
        goto L8
    L10:
        this.parentAlert.dismiss();
        return true;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.webViewResultSent);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewTheme);
        ActionBarMenu r0 = this.parentAlert.actionBar.createMenu();
        this.otherItem.removeAllSubItems();
        r0.removeView(this.otherItem);
        this.webViewContainer.destroyWebView();
        this.destroyed = true;
        AndroidUtilities.cancelRunOnUIThread(this.pollRunnable);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        onDestroy();
        super.onDetachedFromWindow();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismissWithTouchOutside() {
        onCheckDismissByUser();
        return false;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
        super.onHidden();
        this.parentAlert.setFocusable(false);
        this.parentAlert.getWindow().setSoftInputMode(48);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        super.onHide();
        this.otherItem.setVisibility(8);
        this.isBotButtonAvailable = false;
        if (this.webViewContainer.isBackButtonVisible() == true) goto L5;
        AndroidUtilities.updateImageViewImageAnimated(this.parentAlert.actionBar.getBackButton(), R.drawable.ic_ab_back);
    L5:
        this.parentAlert.actionBar.setBackground(null);
        if (this.webViewContainer.hasUserPermissions() == false) goto L9;
        this.webViewContainer.destroyWebView();
        this.needReload = true;
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.ignoreMeasure == false) goto L6;
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        return;
    L6:
        super.onMeasure(r2, r3);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onMenuItemClick(int r8) {
        if (r8 != (-1)) goto L9;
        if (this.webViewContainer.onBackPressed() == true) goto L58;
        onCheckDismissByUser();
        return;
    L58:
        return;
    L9:
        if (r8 != R.id.menu_open_bot) goto L13;
        Bundle r82 = new Bundle();
        r82.putLong("user_id", this.botId);
        this.parentAlert.baseFragment.presentFragment(new ChatActivity(r82));
        this.parentAlert.dismiss();
        return;
    L13:
        if (r8 != R.id.menu_pin) goto L24;
        WebViewSwipeContainer r83 = this.swipeContainer;
        boolean r0 = r83.isPinned;
        r83.isPinned = !r0;
        ActionBarMenuSubItem r84 = this.pinItem;
        if (r0 == true) goto L17;
        int r02 = R.string.UnpinMessage;
    L18:
        String r03 = LocaleController.getString(r02);
        if (this.swipeContainer.isPinned == false) goto L21;
        int r7 = R.drawable.msg_unpin;
    L22:
        r84.setTextAndIcon(r03, r7);
        return;
    L21:
        r7 = R.drawable.msg_pin;
        goto L22
    L17:
        r02 = R.string.PinMessage;
        goto L18
    L24:
        int r1 = 0;
        if (r8 != R.id.menu_reload_page) goto L32;
        if (this.webViewContainer.getWebView() == null) goto L29;
        this.webViewContainer.getWebView().animate().cancel();
        this.webViewContainer.getWebView().animate().alpha(0.0f).start();
    L29:
        this.progressView.setLoadProgress(0.0f);
        this.progressView.setAlpha(1.0f);
        this.progressView.setVisibility(0);
        this.webViewContainer.setBotUser(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId)));
        this.webViewContainer.loadFlickerAndSettingsItem(this.currentAccount, this.botId, this.settingsItem);
        this.webViewContainer.reload();
        return;
    L32:
        if (r8 != R.id.menu_delete_bot) goto L40;
        ArrayList<TLRPC.TL_attachMenuBot> r85 = MediaDataController.getInstance(this.currentAccount).getAttachMenuBots().bots;
        int r04 = r85.size();
    L34:
        if (r1 >= r04) goto L60;
        TLRPC.TL_attachMenuBot r2 = r85.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_attachMenuBot r22 = r2;
        if (r22.bot_id != this.botId) goto L34;
        this.parentAlert.onLongClickBotButton(r22, MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId)));
        return;
    L60:
        return;
    L40:
        if (r8 != R.id.menu_settings) goto L44;
        this.webViewContainer.onSettingsButtonPressed();
        return;
    L44:
        if (r8 != R.id.menu_add_to_home_screen_bot) goto L48;
        MediaDataController.getInstance(this.currentAccount).installShortcut(this.botId, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT);
        return;
    L48:
        if (r8 != R.id.menu_tos_bot) goto L52;
        Browser.openUrl(getContext(), LocaleController.getString(R.string.BotWebViewToSLink));
        return;
    L52:
        if (r8 != R.id.menu_report_bot) goto L59;
        ReportBottomSheet.openChat(this.currentAccount, getContext(), BulletinFactory.of(Bulletin.BulletinWindow.make(getContext()), this.resourcesProvider), this.botId);
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionEnd() {
        this.ignoreMeasure = false;
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(false);
        this.webViewContainer.setViewPortByMeasureSuppressed(false);
        requestLayout();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionStart(boolean r5, int r6) {
        if (r5 == false) goto L19;
        this.webViewContainer.setViewPortByMeasureSuppressed(true);
        float r52 = (-this.swipeContainer.getOffsetY()) + this.swipeContainer.getTopActionBarOffsetY();
        if (this.swipeContainer.getSwipeOffsetY() == r52) goto L7;
        this.swipeContainer.stickTo(r52);
        boolean r53 = true;
    L8:
        int r1 = this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() + r6;
        setMeasuredDimension(getMeasuredWidth(), r6);
        this.ignoreMeasure = true;
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(true);
        if (r53 == true) goto L17;
        ValueAnimator r54 = this.webViewScrollAnimator;
        if (r54 == null) goto L14;
        r54.cancel();
        this.webViewScrollAnimator = null;
    L14:
        if (this.webViewContainer.getWebView() == null) goto L18;
        int r55 = this.webViewContainer.getWebView().getScrollY();
        int r12 = (r1 - r6) + r55;
        ValueAnimator r56 = ValueAnimator.ofInt(new int[]{r55, r12}).setDuration(250);
        this.webViewScrollAnimator = r56;
        r56.setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR);
        this.webViewScrollAnimator.addUpdateListener(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda14(this));
        this.webViewScrollAnimator.addListener(new AnonymousClass4(this, r12));
        this.webViewScrollAnimator.start();
        return;
    L18:
        return;
    L17:
        return;
    L7:
        r53 = false;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        if (AndroidUtilities.isTablet() == true) goto L7;
        Point r32 = AndroidUtilities.displaySize;
        if (r32.x <= r32.y) goto L7;
        int r33 = (int) (r4 / 3.5f);
    L8:
        this.parentAlert.setAllowNestedScroll(true);
        if (r33 >= 0) goto L11;
        r33 = 0;
    L11:
        float r34 = r33;
        if (this.swipeContainer.getOffsetY() == r34) goto L15;
        this.ignoreLayout = true;
        this.swipeContainer.setOffsetY(r34);
        this.ignoreLayout = false;
        return;
    L15:
        return;
    L7:
        r33 = (r4 / 5) * 2;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r4) {
        CharSequence r42 = UserObject.getUserName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId)));
        TextPaint r1 = new TextPaint();     // Catch: Exception -> L14
        r1.setTextSize(AndroidUtilities.dp(20.0f));     // Catch: Exception -> L14
        r42 = Emoji.replaceEmoji(r42, r1.getFontMetricsInt(), false);     // Catch: Exception -> L14
    L4:
        this.parentAlert.actionBar.setTitle(r42);
        this.swipeContainer.setSwipeOffsetY(0.0f);
        if (this.webViewContainer.getWebView() == null) goto L8;
        this.webViewContainer.getWebView().scrollTo(0, 0);
    L8:
        if (this.parentAlert.getBaseFragment() == null) goto L10;
        this.webViewContainer.setParentActivity(this.parentAlert.getBaseFragment().getParentActivity());
    L10:
        this.otherItem.setVisibility(0);
        if (this.webViewContainer.isBackButtonVisible() == true) goto L17;
        AndroidUtilities.updateImageViewImageAnimated(this.parentAlert.actionBar.getBackButton(), R.drawable.ic_close_white);
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShown() {
        if (this.webViewContainer.isPageLoaded() == false) goto L5;
        requestEnableKeyboard();
    L5:
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(false);
        AndroidUtilities.runOnUIThread(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda0(this));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void requestWebView(int r5, long r6, long r8, boolean r10, int r11, String r12, long r13) {
        this.currentAccount = r5;
        this.peerId = r6;
        this.botId = r8;
        this.silent = r10;
        this.replyToMsgId = r11;
        this.monoforumTopicId = r13;
        this.startCommand = r12;
        if (this.addToHomeScreenItem == null) goto L8;
        boolean r0 = MediaDataController.getInstance(r5).canCreateAttachedMenuBotShortcut(r8);
        ActionBarMenuSubItem r2 = this.addToHomeScreenItem;
        if (r0 == false) goto L7;
        r2.setVisibility(0);
        goto L8
    L7:
        r2.setVisibility(8);
    L8:
        this.webViewContainer.setBotUser(MessagesController.getInstance(r5).getUser(Long.valueOf(r8)));
        this.webViewContainer.loadFlickerAndSettingsItem(r5, r8, this.settingsItem);
        TLRPC.TL_messages_requestWebView r02 = new TLRPC.TL_messages_requestWebView();
        r02.peer = MessagesController.getInstance(r5).getInputPeer(r6);
        r02.bot = MessagesController.getInstance(r5).getInputUser(r8);
        r02.silent = r10;
        r02.platform = "android";
        if (r6 >= 0) goto L15;
        TLRPC.ChatFull r62 = MessagesController.getInstance(r5).getChatFull(-r6);
        if (r62 == null) goto L15;
        TLRPC.Peer r63 = r62.default_send_as;
        if (r63 == null) goto L15;
        r02.send_as = MessagesController.getInstance(r5).getInputPeer(r63);
        r02.flags |= 8192;
    L15:
        if (r12 == null) goto L17;
        r02.start_param = r12;
        r02.flags |= 8;
    L17:
        if (r11 == 0) goto L23;
        TLRPC.InputReplyTo r64 = SendMessagesHelper.getInstance(r5).createReplyInput(r11);
        r02.reply_to = r64;
        if (r13 == 0) goto L21;
        r64.monoforum_peer_id = MessagesController.getInstance(r5).getInputPeer(r13);
        r02.reply_to.flags |= 32;
    L21:
        r02.flags |= 1;
    L25:
        JSONObject r65 = BotWebViewSheet.makeThemeParams(this.resourcesProvider);
        if (r65 == null) goto L28;
        TLRPC.TL_dataJSON r7 = new TLRPC.TL_dataJSON();
        r02.theme_params = r7;
        r7.data = r65.toString();
        r02.flags |= 4;
    L28:
        ConnectionsManager.getInstance(r5).sendRequest(r02, new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda9(this, r5));
        NotificationCenter.getInstance(r5).addObserver(this, NotificationCenter.webViewResultSent);
        return;
    L23:
        if (r13 == 0) goto L25;
        TLRPC.TL_inputReplyToMonoForum r66 = new TLRPC.TL_inputReplyToMonoForum();
        r02.reply_to = r66;
        r66.monoforum_peer_id = MessagesController.getInstance(r5).getInputPeer(r13);
        r02.flags |= 1;
        goto L25
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        WebViewSwipeContainer r0 = this.swipeContainer;
        r0.stickTo((-r0.getOffsetY()) + this.swipeContainer.getTopActionBarOffsetY());
    }

    public void setAllowSwipes(boolean r1) {
        this.swipeContainer.setAllowSwipes(r1);
    }

    public void setCustomActionBarBackground(int r2) {
        this.hasCustomActionBarBackground = true;
        this.customActionBarBackground = r2;
    }

    public void setCustomBackground(int r1) {
        this.customBackground = r1;
        this.hasCustomBackground = true;
    }

    public void setDelegate(BotWebViewContainer.Delegate r1) {
        this.webViewContainer.setDelegate(r1);
    }

    public void setMeasureOffsetY(int r1) {
        this.measureOffsetY = r1;
        this.swipeContainer.requestLayout();
    }

    public void setNeedCloseConfirmation(boolean r1) {
        this.needCloseConfirmation = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean shouldHideBottomButtons() {
        return false;
    }

    public void showJustAddedBulletin() {
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        ArrayList<TLRPC.TL_attachMenuBot> r1 = MediaDataController.getInstance(this.currentAccount).getAttachMenuBots().bots;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L7;
        TLRPC.TL_attachMenuBot r4 = r1.get(r3);
        r3 = r3 + 1;
        TLRPC.TL_attachMenuBot r42 = r4;
        if (r42.bot_id != this.botId) goto L3;
    L8:
        if (r42 != null) goto L10;
        return;
    L10:
        boolean r12 = r42.show_in_side_menu;
        if (r12 == true) goto L13;
    L15:
        if (r12 == false) goto L17;
        String r02 = LocaleController.formatString("BotAttachMenuShortcatAddedSide", R.string.BotAttachMenuShortcatAddedSide, new Object[]{r0.first_name});
    L18:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertBotWebViewLayout$$ExternalSyntheticLambda2(this, r02), 200);
        return;
    L17:
        r02 = LocaleController.formatString("BotAttachMenuShortcatAddedAttach", R.string.BotAttachMenuShortcatAddedAttach, new Object[]{r0.first_name});
        goto L18
    L13:
        if (r42.show_in_attach_menu == false) goto L15;
        r02 = LocaleController.formatString("BotAttachMenuShortcatAddedAttachAndSide", R.string.BotAttachMenuShortcatAddedAttachAndSide, new Object[]{r0.first_name});
        goto L18
    L7:
        r42 = null;
        goto L8
    }
}
