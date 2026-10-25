package org.telegram.ui.bots;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill$$ExternalSyntheticLambda1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BotFullscreenButtons;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheetTabDialog;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.BottomSheetTabsOverlay;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OverlayActionBarLayoutDialog;
import org.telegram.ui.Components.PasscodeView;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ReportBottomSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.bots.BotButtons;
import org.telegram.ui.bots.BotDownloads;
import org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes3.dex */
public class BotWebViewSheet extends Dialog implements NotificationCenter.NotificationCenterDelegate, BottomSheetTabsOverlay.Sheet {
    private static final SimpleFloatPropertyCompat<BotWebViewSheet> ACTION_BAR_TRANSITION_PROGRESS_VALUE = null;
    public static HashSet<BotWebViewSheet> activeSheets;
    private static int shownLockedBots;
    private ActionBar actionBar;
    private int actionBarColor;
    private int actionBarColorKey;
    private boolean actionBarIsLight;
    private FrameLayout.LayoutParams actionBarLayoutParams;
    private Paint actionBarPaint;
    private Drawable actionBarShadow;
    private float actionBarTransitionProgress;
    public boolean attached;
    private boolean backButtonShown;
    private ValueAnimator backgroundColorAnimator;
    private Paint backgroundPaint;
    private BotButtons botButtons;
    private FrameLayout.LayoutParams botButtonsLayoutParams;
    private long botId;
    private BottomSheetTabs bottomTabs;
    private BottomSheetTabs.ClipTools bottomTabsClip;
    private FrameLayout bulletinContainer;
    private FrameLayout.LayoutParams bulletinContainerLayoutParams;
    private String buttonText;
    private int currentAccount;
    private TLRPC.BotApp currentWebApp;
    private boolean defaultFullsize;
    private Paint dimPaint;
    private boolean dismissed;
    private Bulletin downloadBulletin;
    private BotDownloads.DownloadBulletin downloadBulletinLayout;
    private String errorCode;
    private ArticleViewer.ErrorContainer errorContainer;
    private boolean errorShown;
    private HashMap<BotDownloads.FileDownload, ActionBarMenuSubItem> fileItems;
    private boolean forceExpnaded;
    public boolean fromTab;
    private boolean fullscreen;
    private ValueAnimator fullscreenAnimator;
    private boolean fullscreenBlur;
    private BotFullscreenButtons fullscreenButtons;
    private boolean fullscreenInProgress;
    private float fullscreenProgress;
    private float fullscreenTransitionProgress;
    private Boolean fullsize;
    private boolean hasSettings;
    private boolean ignoreLayout;
    private final Rect insets;
    private final Runnable invalidateRunnable;
    private int keyboardInset;
    private BotDownloads.FileDownload lastBulletinFile;
    private long lastSwipeTime;
    private BottomSheetTabs.WebTabData lastTab;
    private int lineColor;
    private Paint linePaint;
    private long monoforumTopicId;
    private int navBarColor;
    private final Rect navInsets;
    private boolean needCloseConfirmation;
    private boolean needsContext;
    private Utilities.Callback4<Boolean, Double, String, Double> onVerifiedAge;
    private ValueAnimator openAnimator;
    private float openedProgress;
    private ItemOptions options;
    private BotFullscreenButtons.OptionsIcon optionsIcon;
    private ActionBarMenuItem optionsItem;
    private boolean orientationLocked;
    private boolean overrideActionBarColor;
    private boolean overrideBackgroundColor;
    private Activity parentActivity;
    private PasscodeView passcodeView;
    private long peerId;
    private ActionBarMenuSubItem pinItem;
    private Runnable pollRunnable;
    private ChatAttachAlertBotWebViewLayout.WebProgressView progressView;
    private long queryId;
    private final Runnable relayoutRunnable;
    private int replyToMsgId;
    private WebViewRequestProps requestProps;
    private boolean resetOffsetY;
    private Theme.ResourcesProvider resourcesProvider;
    private BotSensors sensors;
    public boolean showExpanded;
    public float showOffsetY;
    private boolean silent;
    private SpringAnimation springAnimation;
    private boolean superDismissed;
    private ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer swipeContainer;
    private int swipeContainerFromHeight;
    private int swipeContainerFromWidth;
    private FrameLayout.LayoutParams swipeContainerLayoutParams;
    Drawable verifiedDrawable;
    private Boolean wasLightStatusBar;
    private BotWebViewContainer webViewContainer;
    private WindowView windowView;

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass1(BotWebViewSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            int r0 = View.MeasureSpec.getSize(r6);
            if (AndroidUtilities.isTablet() == true) goto L7;
            Point r1 = AndroidUtilities.displaySize;
            if (r1.x <= r1.y) goto L7;
            int r02 = (int) (r0 / 3.5f);
        L9:
            if (r02 >= 0) goto L11;
            r02 = 0;
        L11:
            float r03 = r02;
            if (getOffsetY() == r03) goto L19;
            if (BotWebViewSheet.m21383$$Nest$fgetdismissed(this.this$0) == true) goto L19;
            if (BotWebViewSheet.m21404$$Nest$fgetresetOffsetY(this.this$0) == false) goto L19;
            BotWebViewSheet.m21423$$Nest$fputignoreLayout(this.this$0, true);
            setOffsetY(r03);
            BotWebViewSheet.m21423$$Nest$fputignoreLayout(this.this$0, false);
            BotWebViewSheet.m21429$$Nest$fputresetOffsetY(this.this$0, false);
        L19:
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == false) goto L21;
        L27:
            int r62 = View.MeasureSpec.getSize(r6);
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == true) goto L31;
            r62 = (r62 - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight();
        L31:
            if (BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0) != null) goto L33;
        L35:
            super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(r62 + AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
            return;
        L33:
            if (BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).getTotalHeight() <= 0) goto L35;
            r62 = r62 - BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).getTotalHeight();
            goto L35
        L21:
            if (AndroidUtilities.isTablet() == false) goto L27;
            if (AndroidUtilities.isInMultiwindow == true) goto L27;
            if (AndroidUtilities.isSmallTablet() == true) goto L27;
            Point r52 = AndroidUtilities.displaySize;
            r5 = View.MeasureSpec.makeMeasureSpec((int) (Math.min(r52.x, r52.y) * 0.8f), TLObject.FLAG_30);
        L7:
            r02 = (r0 / 5) * 2;
            goto L9
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (BotWebViewSheet.m21390$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer, android.view.View
        public void setTranslationY(float r4) {
            super.setTranslationY(r4);
            if (BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0) == null) goto L6;
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setTranslationY(AndroidUtilities.dp(24.0f) + r4);
        L6:
            if (BotWebViewSheet.m21379$$Nest$fgetbulletinContainer(this.this$0) == null) goto L9;
            BotWebViewSheet.m21379$$Nest$fgetbulletinContainer(this.this$0).setTranslationY(AndroidUtilities.lerp(ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(24.0f), BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).top + AndroidUtilities.dp(70.0f), BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)) + BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY());
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass10(BotWebViewSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L9;
            if (BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).onBackPressed() == true) goto L12;
            this.this$0.onCheckDismissByUser();
            return;
        L12:
            return;
        L9:
            if (r2 != R.id.menu_collapse_bot) goto L13;
            BotWebViewSheet.m21418$$Nest$fputforceExpnaded(this.this$0, true);
            this.this$0.dismiss(true, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 implements View.OnLayoutChangeListener {
        final /* synthetic */ BotWebViewSheet this$0;

        public static /* synthetic */ void $r8$lambda$IrbHKu16ieCdQxKD5RCKEncA4cc(AnimationNotificationsLocker r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
            r0.unlock();
        }

        public AnonymousClass11(BotWebViewSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            r1.removeOnLayoutChangeListener(this);
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setSwipeOffsetY(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getHeight());
            BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0).setAlpha(1.0f);
            BotWebViewSheet r12 = this.this$0;
            if (r12.showOffsetY == Float.MAX_VALUE) goto L5;
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(r12).setSwipeOffsetAnimationDisallowed(true);
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setOffsetY(this.this$0.showOffsetY);
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setSwipeOffsetAnimationDisallowed(false);
        L5:
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).invalidateViewPortHeight(true, true);
            AnimationNotificationsLocker r13 = new AnimationNotificationsLocker();
            r13.lock();
            BotWebViewSheet r32 = this.this$0;
            if (r32.showExpanded == false) goto L8;
        L11:
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).stickTo((-BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getOffsetY()) + BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTopActionBarOffsetY(), new BotWebViewSheet$11$$ExternalSyntheticLambda0(r13));
        L12:
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).opened = true;
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == true) goto L15;
            return;
        L15:
            if (BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0) == null) goto L19;
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setAlpha(0.0f);
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).animate().alpha(1.0f).setDuration(220).start();
            return;
        L19:
            return;
        L8:
            if (r32.isFullSize() == true) goto L11;
            new SpringAnimation(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0), ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer.SWIPE_OFFSET_Y, 0.0f).setSpring(new SpringForce(0.0f).setDampingRatio(0.75f).setStiffness(500.0f)).addEndListener(new BotWebViewSheet$11$$ExternalSyntheticLambda1(r13)).start();
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$12, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ boolean val$opened;

        public AnonymousClass12(BotWebViewSheet r1, boolean r2) {
            this.this$0 = r1;
            this.val$opened = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            BotWebViewSheet r22 = this.this$0;
            if (this.val$opened == false) goto L5;
            float r0 = 1.0f;
        L6:
            BotWebViewSheet.m21428$$Nest$fputopenedProgress(r22, r0);
            this.this$0.checkNavBarColor();
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$13, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ int val$color;

        public AnonymousClass13(BotWebViewSheet r1, int r2) {
            this.this$0 = r1;
            this.val$color = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).setColor(this.val$color);
            BotWebViewSheet.m21434$$Nest$mupdateActionBarColors(this.this$0);
            BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0).invalidate();
            if (BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0) == null) goto L9;
            ArticleViewer.ErrorContainer r32 = BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0);
            if (AndroidUtilities.computePerceivedBrightness(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).getColor()) > 0.721f) goto L7;
            boolean r0 = true;
        L8:
            r32.setDark(r0, false);
            BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0).setBackgroundColor(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).getColor());
            goto L9
        L7:
            r0 = false;
        L9:
            BotWebViewSheet.m21436$$Nest$mupdateWebViewBackgroundColor(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$14, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass14 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ float val$fromButtonsTranslationX;
        final /* synthetic */ float val$fromTranslationX;
        final /* synthetic */ float val$fromTranslationY;
        final /* synthetic */ boolean val$fullscreen;
        final /* synthetic */ float val$toTranslationY;

        public AnonymousClass14(BotWebViewSheet r1, boolean r2, float r3, float r4, float r5, float r6) {
            this.this$0 = r1;
            this.val$fullscreen = r2;
            this.val$fromTranslationY = r3;
            this.val$toTranslationY = r4;
            this.val$fromTranslationX = r5;
            this.val$fromButtonsTranslationX = r6;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            BotWebViewSheet.m21421$$Nest$fputfullscreenTransitionProgress(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            BotWebViewSheet r42 = this.this$0;
            if (this.val$fullscreen == false) goto L5;
            float r0 = BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(r42);
        L6:
            BotWebViewSheet.m21420$$Nest$fputfullscreenProgress(r42, r0);
            BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).setAlpha(1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).setTranslationY((-ActionBar.getCurrentActionBarHeight()) * BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setTranslationY(AndroidUtilities.lerp(this.val$fromTranslationY, this.val$toTranslationY, BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(this.this$0)));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setTranslationX(AndroidUtilities.lerp(this.val$fromTranslationX, 0.0f, BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(this.this$0)));
            BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).setTranslationX(AndroidUtilities.lerp(this.val$fromButtonsTranslationX, 0.0f, BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(this.this$0)));
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setAlpha(BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0).invalidate();
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).setViewPortHeightOffset(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY() - this.val$toTranslationY);
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).invalidateViewPortHeight(false, false);
            BotWebViewSheet.m21435$$Nest$mupdateDownloadBulletinArrow(this.this$0);
            return;
        L5:
            r0 = 1.0f - BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(r42);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$15, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ float val$fromTranslationX;
        final /* synthetic */ boolean val$fullscreen;
        final /* synthetic */ float val$topoffset;

        public AnonymousClass15(BotWebViewSheet r1, boolean r2, float r3, float r4) {
            this.this$0 = r1;
            this.val$fullscreen = r2;
            this.val$topoffset = r3;
            this.val$fromTranslationX = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            BotWebViewSheet.m21419$$Nest$fputfullscreenInProgress(this.this$0, false);
            boolean r62 = this.val$fullscreen;
            BotWebViewSheet r1 = this.this$0;
            if (r62 == true) goto L5;
            r1.updateFullscreenLayout();
            this.this$0.updateWindowFlags();
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setForceOffsetY(this.val$topoffset - AndroidUtilities.dp(24.0f));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setTopActionBarOffsetY(this.val$topoffset - AndroidUtilities.dp(24.0f));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setSwipeOffsetY(0.0f);
        L6:
            BotWebViewSheet r63 = this.this$0;
            if (this.val$fullscreen == false) goto L9;
            float r12 = BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(r63);
        L10:
            BotWebViewSheet.m21420$$Nest$fputfullscreenProgress(r63, r12);
            BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).setAlpha(1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).setTranslationY((-ActionBar.getCurrentActionBarHeight()) * BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setAlpha(BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            if (this.val$fullscreen == false) goto L13;
            BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).setVisibility(8);
        L13:
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setSwipeOffsetAnimationDisallowed(false);
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setTranslationX(AndroidUtilities.lerp(this.val$fromTranslationX, 0.0f, BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(this.this$0)));
            BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).setTranslationX(0.0f);
            BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0).invalidate();
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).setViewPortHeightOffset(0.0f);
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).invalidateViewPortHeight(true, true);
            BotWebViewSheet.m21435$$Nest$mupdateDownloadBulletinArrow(this.this$0);
            return;
        L9:
            r12 = 1.0f - BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(r63);
            goto L10
        L5:
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(r1).setForceOffsetY(-AndroidUtilities.dp(24.0f));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setTopActionBarOffsetY(-AndroidUtilities.dp(24.0f));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setSwipeOffsetY(0.0f);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$16, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ int val$from;
        final /* synthetic */ int val$to;

        public AnonymousClass16(BotWebViewSheet r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$from = r2;
            this.val$to = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            BotWebViewSheet.m21426$$Nest$fputnavBarColor(this.this$0, ColorUtils.blendARGB(this.val$from, this.val$to, 1.0f));
            this.this$0.checkNavBarColor();
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$17, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ BotWebViewMenuContainer$ActionBarColorsAnimating val$actionBarColorsAnimating;
        final /* synthetic */ int val$from;
        final /* synthetic */ int val$to;

        public AnonymousClass17(BotWebViewSheet r1, int r2, int r3, BotWebViewMenuContainer$ActionBarColorsAnimating r4) {
            this.this$0 = r1;
            this.val$from = r2;
            this.val$to = r3;
            this.val$actionBarColorsAnimating = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            BotWebViewSheet.m21412$$Nest$fputactionBarColor(this.this$0, ColorUtils.blendARGB(this.val$from, this.val$to, 1.0f));
            this.this$0.checkNavBarColor();
            BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0).invalidate();
            BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).setBackgroundColor(BotWebViewSheet.m21370$$Nest$fgetactionBarColor(this.this$0));
            this.val$actionBarColorsAnimating.updateActionBar(BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0), 1.0f);
            BotWebViewSheet.m21425$$Nest$fputlineColor(this.this$0, this.val$actionBarColorsAnimating.getColor(Theme.key_sheet_scrollUp));
            BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends BotWebViewContainer {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass2(BotWebViewSheet r1, Context r2, Theme.ResourcesProvider r3, int r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer
        public void onErrorShown(boolean r5, int r6, String r7) {
            if (r5 == false) goto L9;
            this.this$0.createErrorContainer();
            BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0).set(UserObject.getUserName(MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0)))), r7);
            ArticleViewer.ErrorContainer r0 = BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0);
            if (AndroidUtilities.computePerceivedBrightness(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).getColor()) > 0.721f) goto L7;
            boolean r1 = true;
        L8:
            r0.setDark(r1, false);
            BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0).setBackgroundColor(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).getColor());
            BotWebViewSheet.m21416$$Nest$fputerrorCode(this.this$0, r7);
            goto L9
        L7:
            r1 = false;
        L9:
            ArticleViewer.ErrorContainer r72 = BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0);
            BotWebViewSheet.m21417$$Nest$fputerrorShown(this.this$0, r5);
            AndroidUtilities.updateViewVisibilityAnimated(r72, r5, 1.0f, false);
            invalidate();
        }

        @Override // org.telegram.ui.web.BotWebViewContainer
        public void onWebViewCreated(BotWebViewContainer.MyWebView r2) {
            super.onWebViewCreated(r2);
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setWebView(r2);
            if (BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0) == null) goto L5;
            BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0).attachWebView(r2);
        L5:
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setWebView(r2);
            BotWebViewSheet.m21436$$Nest$mupdateWebViewBackgroundColor(this.this$0);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer
        public void onWebViewDestroyed(BotWebViewContainer.MyWebView r2) {
            if (BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0) == null) goto L5;
            BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0).detachWebView(r2);
        L5:
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setWebView(null);
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 implements BotWebViewContainer.Delegate {
        private boolean sentWebViewData;
        final /* synthetic */ BotWebViewSheet this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public static /* synthetic */ Bulletin $r8$lambda$0UDwLq9f3JkAYxCMsqUq5HL3PPk(TLRPC.User r2, BulletinFactory.UndoObject r3, BulletinFactory r4) {
            return r4.createUsersBulletin(Arrays.asList(new TLRPC.User[]{r2}), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotLocationPermissionRequestGranted, new Object[]{UserObject.getUserName(r2)})), null, r3).setDuration(5000);
        }

        public static /* synthetic */ Bulletin $r8$lambda$3twIhmIucGZKfTd5EjDUYqiHZFY(TLRPC.User r2, BulletinFactory.UndoObject r3, BulletinFactory r4) {
            return r4.createUsersBulletin(Arrays.asList(new TLRPC.User[]{r2}), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotEmojiStatusPermissionRequestGranted, new Object[]{UserObject.getUserName(r2)})), null, r3).setDuration(5000);
        }

        public static /* synthetic */ void $r8$lambda$5UtNs1ueKV_o0SnCxW8kq5OG038(AnonymousClass3 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onEmojiStatusGranted$8(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$6dyFIisOT34RICXC2heX3FtgdwQ(AnonymousClass3 r0) {
            r0.lambda$onLocationGranted$2();
        }

        /* renamed from: $r8$lambda$6epxQ1-cAvW5JXGN-XoHnfIkuMU, reason: not valid java name */
        public static /* synthetic */ void m21437$r8$lambda$6epxQ1cAvW5JXGNXoHnfIkuMU(AnonymousClass3 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onSendWebViewData$1(r1, r2);
        }

        /* renamed from: $r8$lambda$82ivh9VCkNmNMnQRHgYAw5ps-OY, reason: not valid java name */
        public static /* synthetic */ Bulletin m21438$r8$lambda$82ivh9VCkNmNMnQRHgYAw5psOY(SpannableStringBuilder r1, BulletinFactory r2) {
            return r2.createSimpleBulletinDetail(R.raw.error, r1).setDuration(5000);
        }

        public static /* synthetic */ void $r8$lambda$HneKBUkvdXodr9ihMxTT_tqOOd4(AnonymousClass3 r0, String r1, String r2) {
            r0.lambda$onWebAppOpenInvoice$13(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$PWnVcRNfHMtyVnZZ7pbQWb_r6Vg(AlertDialog r0) {
            r0.dismiss();
        }

        public static /* synthetic */ Bulletin $r8$lambda$PuO7iSwAPxuRmDbkiIHVJtjRVx4(TLRPC.TL_error r0, BulletinFactory r1) {
            return r1.makeForError(r0);
        }

        /* renamed from: $r8$lambda$Rkc0i7A97FAmdP-3eqLVSp_O074, reason: not valid java name */
        public static /* synthetic */ void m21439$r8$lambda$Rkc0i7A97FAmdP3eqLVSp_O074(AnonymousClass3 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onEmojiStatusGranted$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$UuF1NechdXODrbMFv6vv22et81c(AnonymousClass3 r0) {
            r0.lambda$onLocationGranted$4();
        }

        public static /* synthetic */ boolean $r8$lambda$ayLKtF8em3lyGYQfK3PGCevX_ug(AnonymousClass3 r0, TLRPC.User r1, String r2, OverlayActionBarLayoutDialog r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
            return r0.lambda$onWebAppSwitchInlineQuery$15(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        public static /* synthetic */ Bulletin $r8$lambda$cx2Gre2knNwMcLgYvNvnHJgdV9Y(String r1, BulletinFactory r2) {
            return r2.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(r1));
        }

        public static /* synthetic */ void $r8$lambda$m05IgMj_2vKJ0JPalDDhNcibb8s(AnonymousClass3 r0) {
            r0.lambda$onEmojiStatusGranted$9();
        }

        public static /* synthetic */ void $r8$lambda$vpSP74aKGk4re1yRRB7SZOtdQfs(AnonymousClass3 r0, OverlayActionBarLayoutDialog r1, String r2, PaymentFormActivity.InvoiceStatus r3) {
            r0.lambda$onWebAppOpenInvoice$14(r1, r2, r3);
        }

        public static /* synthetic */ Bulletin $r8$lambda$wpW0HbHHbExY28AFjXPYCL46ic8(TLRPC.Document r1, BulletinFactory r2) {
            return r2.createEmojiBulletin(r1, LocaleController.getString(R.string.BotEmojiStatusUpdated));
        }

        public AnonymousClass3(BotWebViewSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$resourcesProvider = r3;
        }

        private /* synthetic */ void lambda$onEmojiStatusGranted$7(TLObject r1, TLRPC.TL_error r2) {
            boolean r12 = r1 instanceof TLRPC.TL_boolTrue;
            BotWebViewSheet r0 = this.this$0;
            if (r12 == false) goto L6;
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(r0).notifyEmojiStatusAccess("cancelled");
            return;
        L6:
            BotWebViewSheet.m21433$$Nest$mshowBulletin(r0, new BotWebViewSheet$3$$ExternalSyntheticLambda16(r2));
        }

        private /* synthetic */ void lambda$onEmojiStatusGranted$8(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new BotWebViewSheet$3$$ExternalSyntheticLambda15(this, r2, r3));
        }

        private /* synthetic */ void lambda$onEmojiStatusGranted$9() {
            TL_bots.toggleUserEmojiStatusPermission r0 = new TL_bots.toggleUserEmojiStatusPermission();
            r0.bot = MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).getInputUser(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0));
            r0.enabled = false;
            ConnectionsManager.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r0, new BotWebViewSheet$3$$ExternalSyntheticLambda5(this));
        }

        private /* synthetic */ void lambda$onLocationGranted$2() {
            BotLocation.get(this.this$0.getContext(), BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0), BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0)).setGranted(false, null);
        }

        private /* synthetic */ void lambda$onLocationGranted$4() {
            BaseFragment r0 = LaunchActivity.getSafeLastFragment();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getParentLayout() == null) goto L10;
            INavigationLayout r1 = r0.getParentLayout();
            r0.presentFragment(ProfileActivity.of(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0)));
            AndroidUtilities.scrollToFragmentRow(r1, "botPermissionLocation");
            this.this$0.dismiss(true);
            return;
        }

        private /* synthetic */ void lambda$onSendWebViewData$1(TLObject r2, TLRPC.TL_error r3) {
            if ((r2 instanceof TLRPC.TL_updates) == false) goto L5;
            MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).processUpdates((TLRPC.TL_updates) r2, false);
        L5:
            AndroidUtilities.runOnUIThread(new BotWebViewSheet$3$$ExternalSyntheticLambda10(this.this$0));
        }

        private /* synthetic */ void lambda$onWebAppOpenInvoice$13(String r1, String r2) {
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).onInvoiceStatusUpdate(r1, r2);
        }

        private /* synthetic */ void lambda$onWebAppOpenInvoice$14(OverlayActionBarLayoutDialog r2, String r3, PaymentFormActivity.InvoiceStatus r4) {
            if (r4 == PaymentFormActivity.InvoiceStatus.PENDING) goto L5;
            r2.dismiss();
        L5:
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).onInvoiceStatusUpdate(r3, r4.name().toLowerCase(Locale.ROOT));
        }

        private /* synthetic */ boolean lambda$onWebAppSwitchInlineQuery$15(TLRPC.User r1, String r2, OverlayActionBarLayoutDialog r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
            long r42 = ((MessagesStorage.TopicKey) r5.get(0)).dialogId;
            Bundle r62 = new Bundle();
            r62.putBoolean("scrollToTopOnResume", true);
            if (DialogObject.isEncryptedDialog(r42) == false) goto L6;
            r62.putInt("enc_id", DialogObject.getEncryptedChatId(r42));
        L9:
            r62.putString("start_text", "@" + UserObject.getPublicUsername(r1) + " " + r2);
            if ((BotWebViewSheet.m21399$$Nest$fgetparentActivity(this.this$0) instanceof LaunchActivity) == false) goto L17;
            BaseFragment r12 = ((LaunchActivity) BotWebViewSheet.m21399$$Nest$fgetparentActivity(this.this$0)).getActionBarLayout().getLastFragment();
            if (MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).checkCanOpenChat(r62, r12) == false) goto L17;
            r3.dismiss();
            BotWebViewSheet.m21415$$Nest$fputdismissed(this.this$0, true);
            AndroidUtilities.cancelRunOnUIThread(BotWebViewSheet.m21401$$Nest$fgetpollRunnable(this.this$0));
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).destroyWebView();
            NotificationCenter.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).removeObserver(this.this$0, NotificationCenter.webViewResultSent);
            NotificationCenter.getGlobalInstance().removeObserver(this.this$0, NotificationCenter.didSetNewTheme);
            if (BotWebViewSheet.m21406$$Nest$fgetsuperDismissed(this.this$0) == true) goto L16;
            BotWebViewSheet.access$001(this.this$0);
            BotWebViewSheet.m21431$$Nest$fputsuperDismissed(this.this$0, true);
        L16:
            r12.presentFragment(new INavigationLayout.NavigationParams(new ChatActivity(r62)).setRemoveLast(true));
        L17:
            return true;
        L6:
            if (DialogObject.isUserDialog(r42) == false) goto L8;
            r62.putLong("user_id", r42);
            goto L9
        L8:
            r62.putLong("chat_id", -r42);
            goto L9
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public BotSensors getBotSensors() {
            if (BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0) != null) goto L6;
            BotWebViewSheet r0 = this.this$0;
            BotWebViewSheet.m21430$$Nest$fputsensors(r0, new BotSensors(this.val$context, BotWebViewSheet.m21376$$Nest$fgetbotId(r0)));
            BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0).attachWebView(BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).getWebView());
        L6:
            return BotWebViewSheet.m21405$$Nest$fgetsensors(this.this$0);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public boolean isClipboardAvailable() {
            if (MediaDataController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).botInAttachMenu(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0)) == false) goto L5;
            return true;
        L5:
            if (MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).whitelistedBots.contains(Long.valueOf(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0))) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onCloseRequested(Runnable r1) {
            this.this$0.dismiss(r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onCloseToTabs() {
            this.this$0.dismiss(true);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onEmojiStatusGranted(boolean r4) {
            TLRPC.User r0 = MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0)));
            if (r4 == false) goto L6;
            BulletinFactory.UndoObject r42 = new BulletinFactory.UndoObject();
            r42.onUndo = new BotWebViewSheet$3$$ExternalSyntheticLambda0(this);
            BotWebViewSheet.m21433$$Nest$mshowBulletin(this.this$0, new BotWebViewSheet$3$$ExternalSyntheticLambda1(r0, r42));
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onEmojiStatusSet(TLRPC.Document r2) {
            BotWebViewSheet.m21433$$Nest$mshowBulletin(this.this$0, new BotWebViewSheet$3$$ExternalSyntheticLambda3(r2));
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public String onFullscreenRequested(boolean r3, boolean r4) {
            boolean r0 = BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0);
            BotWebViewSheet r2 = this.this$0;
            if (r0 == r3) goto L5;
            r2.setFullscreen(r3, true, r4);
            return null;
        L5:
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(r2) == false) goto L8;
            return "ALREADY_FULLSCREEN";
        L8:
            return null;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onLocationGranted(boolean r4) {
            TLRPC.User r0 = MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0)));
            if (r4 == false) goto L6;
            BulletinFactory.UndoObject r42 = new BulletinFactory.UndoObject();
            r42.undoText = LocaleController.getString(R.string.UndoNoCaps);
            r42.onUndo = new BotWebViewSheet$3$$ExternalSyntheticLambda11(this);
            BotWebViewSheet.m21433$$Nest$mshowBulletin(this.this$0, new BotWebViewSheet$3$$ExternalSyntheticLambda12(r0, r42));
            return;
        L6:
            SpannableStringBuilder r43 = new SpannableStringBuilder();
            r43.append(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotLocationPermissionRequestDeniedApp, new Object[]{UserObject.getUserName(r0)})));
            r43.append(" ");
            r43.append(AndroidUtilities.replaceArrows(AndroidUtilities.makeClickable(LocaleController.getString(R.string.BotLocationPermissionRequestDeniedAppSettings), new BotWebViewSheet$3$$ExternalSyntheticLambda13(this)), true));
            BotWebViewSheet.m21433$$Nest$mshowBulletin(this.this$0, new BotWebViewSheet$3$$ExternalSyntheticLambda14(r43));
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onOpenBackFromTabs() {
            if (BotWebViewSheet.m21392$$Nest$fgetlastTab(this.this$0) == null) goto L9;
            BottomSheetTabs r0 = LaunchActivity.instance.getBottomSheetTabs();
            if (r0 == null) goto L7;
            r0.openTab(BotWebViewSheet.m21392$$Nest$fgetlastTab(this.this$0));
        L7:
            BotWebViewSheet.m21424$$Nest$fputlastTab(this.this$0, null);
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onOrientationLockChanged(boolean r1) {
            this.this$0.lockOrientation(r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSendWebViewData(String r5) {
            if (BotWebViewSheet.m21403$$Nest$fgetqueryId(this.this$0) == 0) goto L5;
            return;
        L5:
            if (this.sentWebViewData == true) goto L10;
            this.sentWebViewData = true;
            TLRPC.TL_messages_sendWebViewData r0 = new TLRPC.TL_messages_sendWebViewData();
            r0.bot = MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).getInputUser(BotWebViewSheet.m21376$$Nest$fgetbotId(this.this$0));
            r0.random_id = Utilities.random.nextLong();
            r0.button_text = BotWebViewSheet.m21380$$Nest$fgetbuttonText(this.this$0);
            r0.data = r5;
            ConnectionsManager.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r0, new BotWebViewSheet$3$$ExternalSyntheticLambda4(this));
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetBackButtonVisible(boolean r3) {
            ImageView r0 = BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).getBackButton();
            BotWebViewSheet.m21414$$Nest$fputbackButtonShown(this.this$0, r3);
            if (r3 == false) goto L5;
            int r1 = R.drawable.ic_ab_back;
        L6:
            AndroidUtilities.updateImageViewImageAnimated(r0, r1);
            if (BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0) == null) goto L10;
            BotWebViewSheet.m21386$$Nest$fgetfullscreenButtons(this.this$0).setBack(r3, true);
            return;
        L10:
            return;
        L5:
            r1 = R.drawable.ic_close_white;
            goto L6
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetSettingsButtonVisible(boolean r1) {
            BotWebViewSheet.m21422$$Nest$fputhasSettings(this.this$0, r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetupMainButton(boolean r4, boolean r5, String r6, long r7, int r9, int r10, boolean r11, boolean r12) {
            BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).setMainState(BotButtons.ButtonState.of(r4, r5, r11, r12, r6, r7, r9, r10), true);
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == false) goto L6;
            this.this$0.updateFullscreenLayout();
            this.this$0.updateWindowFlags();
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetupSecondaryButton(boolean r4, boolean r5, String r6, long r7, int r9, int r10, boolean r11, boolean r12, String r13) {
            BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).setSecondaryState(BotButtons.ButtonState.of(r4, r5, r11, r12, r6, r7, r9, r10, r13), true);
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == false) goto L6;
            this.this$0.updateFullscreenLayout();
            this.this$0.updateWindowFlags();
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSharedTo(ArrayList<Long> r5) {
            if (r5.size() != 1) goto L5;
            String r52 = LocaleController.formatString(R.string.BotSharedToOne, new Object[]{MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).getPeerName(r5.get(0).longValue())});
        L6:
            BotWebViewSheet.m21433$$Nest$mshowBulletin(this.this$0, new BotWebViewSheet$3$$ExternalSyntheticLambda6(r52));
            return;
        L5:
            r52 = LocaleController.formatPluralString("BotSharedToMany", r5.size(), new Object[0]);
            goto L6
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppExpand() {
            if (BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).isSwipeInProgress() == false) goto L5;
            return;
        L5:
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).stickTo((-BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getOffsetY()) + BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTopActionBarOffsetY());
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppOpenInvoice(TLRPC.InputInvoice r9, String r10, TLObject r11) {
            BaseFragment r0 = ((LaunchActivity) BotWebViewSheet.m21399$$Nest$fgetparentActivity(this.this$0)).getActionBarLayout().getLastFragment();
            if ((r11 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L7;
            AndroidUtilities.hideKeyboard(BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0));
            AlertDialog r02 = new AlertDialog(this.this$0.getContext(), 3);
            r02.showDelayed(150);
            StarsController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).openPaymentForm(null, r9, (TLRPC.TL_payments_paymentFormStars) r11, new BotWebViewSheet$3$$ExternalSyntheticLambda7(r02), new BotWebViewSheet$3$$ExternalSyntheticLambda8(this, r10));
            return;
        L7:
            if ((r11 instanceof TLRPC.PaymentForm) == false) goto L10;
            TLRPC.PaymentForm r112 = (TLRPC.PaymentForm) r11;
            MessagesController.getInstance(BotWebViewSheet.m21381$$Nest$fgetcurrentAccount(this.this$0)).putUsers(r112.users, false);
            PaymentFormActivity r92 = new PaymentFormActivity(r112, r10, r0);
        L13:
            if (r92 == null) goto L16;
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).stickTo((-BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getOffsetY()) + BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTopActionBarOffsetY());
            AndroidUtilities.hideKeyboard(BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0));
            OverlayActionBarLayoutDialog r113 = new OverlayActionBarLayoutDialog(this.val$context, this.val$resourcesProvider);
            r113.show();
            r92.setPaymentFormCallback(new BotWebViewSheet$3$$ExternalSyntheticLambda9(this, r113, r10));
            r92.setResourcesProvider(this.val$resourcesProvider);
            r113.addFragment(r92);
            return;
        L16:
            return;
        L10:
            if ((r11 instanceof TLRPC.PaymentReceipt) == false) goto L12;
            r92 = new PaymentFormActivity((TLRPC.PaymentReceipt) r11);
            goto L13
        L12:
            r92 = null;
            goto L13
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetActionBarColor(int r2, int r3, boolean r4) {
            BotWebViewSheet.m21413$$Nest$fputactionBarColorKey(this.this$0, r2);
            this.this$0.setActionBarColor(r3, r4, true);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetBackgroundColor(int r2) {
            this.this$0.setBackgroundColor(r2, true, true);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetNavigationBarColor(int r2) {
            this.this$0.setNavigationBarColor(r2, true);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetupClosingBehavior(boolean r1) {
            BotWebViewSheet.m21427$$Nest$fputneedCloseConfirmation(this.this$0, r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSwipingBehavior(boolean r2) {
            if (BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0) == null) goto L6;
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).setAllowSwipes(r2);
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSwitchInlineQuery(TLRPC.User r5, String r6, List<String> r7) {
            if (r7.isEmpty() == true) goto L5;
            Bundle r0 = new Bundle();
            r0.putInt("dialogsType", 14);
            r0.putBoolean("onlySelect", true);
            r0.putBoolean("allowGroups", r7.contains("groups"));
            r0.putBoolean("allowMegagroups", r7.contains("groups"));
            r0.putBoolean("allowLegacyGroups", r7.contains("groups"));
            r0.putBoolean("allowUsers", r7.contains("users"));
            r0.putBoolean("allowChannels", r7.contains("channels"));
            r0.putBoolean("allowBots", r7.contains("bots"));
            DialogsActivity r72 = new DialogsActivity(r0);
            AndroidUtilities.hideKeyboard(BotWebViewSheet.m21411$$Nest$fgetwindowView(this.this$0));
            OverlayActionBarLayoutDialog r02 = new OverlayActionBarLayoutDialog(this.val$context, this.val$resourcesProvider);
            r72.setDelegate(new BotWebViewSheet$3$$ExternalSyntheticLambda2(this, r5, r6, r02));
            r02.show();
            r02.addFragment(r72);
            return;
        L5:
            if ((BotWebViewSheet.m21399$$Nest$fgetparentActivity(this.this$0) instanceof LaunchActivity) == false) goto L12;
            BaseFragment r73 = ((LaunchActivity) BotWebViewSheet.m21399$$Nest$fgetparentActivity(this.this$0)).getActionBarLayout().getLastFragment();
            if ((r73 instanceof ChatActivity) == false) goto L13;
            ((ChatActivity) r73).getChatActivityEnterView().setFieldText("@" + UserObject.getPublicUsername(r5) + " " + r6);
            this.this$0.dismiss();
            return;
        L13:
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends BotButtons {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass4(BotWebViewSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.bots.BotButtons, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == false) goto L5;
        L11:
            super.onMeasure(r2, r3);
            return;
        L5:
            if (AndroidUtilities.isTablet() == false) goto L11;
            if (AndroidUtilities.isInMultiwindow == true) goto L11;
            if (AndroidUtilities.isSmallTablet() == true) goto L11;
            Point r22 = AndroidUtilities.displaySize;
            r2 = View.MeasureSpec.makeMeasureSpec((int) (Math.min(r22.x, r22.y) * 0.8f), TLObject.FLAG_30);
            goto L11
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends ActionBar {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass5(BotWebViewSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (AndroidUtilities.isTablet() == true) goto L5;
        L9:
            super.onMeasure(r2, r3);
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isSmallTablet() == true) goto L9;
            Point r22 = AndroidUtilities.displaySize;
            r2 = View.MeasureSpec.makeMeasureSpec((int) (Math.min(r22.x, r22.y) * 0.8f), TLObject.FLAG_30);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass6(BotWebViewSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.onCheckDismissByUser();
            return;
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends ChatAttachAlertBotWebViewLayout.WebProgressView {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass7(BotWebViewSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            if (AndroidUtilities.isTablet() == true) goto L5;
        L9:
            super.onMeasure(r2, r3);
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isSmallTablet() == true) goto L9;
            Point r22 = AndroidUtilities.displaySize;
            r2 = View.MeasureSpec.makeMeasureSpec((int) (Math.min(r22.x, r22.y) * 0.8f), TLObject.FLAG_30);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass8(BotWebViewSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            BotWebViewSheet.m21402$$Nest$fgetprogressView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.bots.BotWebViewSheet$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends Drawable {
        final /* synthetic */ BotWebViewSheet this$0;

        public AnonymousClass9(BotWebViewSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.save();
            r3.translate(0.0f, AndroidUtilities.dp(1.0f));
            this.this$0.verifiedDrawable.setBounds(getBounds());
            this.this$0.verifiedDrawable.draw(r3);
            r3.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(20.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(20.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.this$0.verifiedDrawable.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.this$0.verifiedDrawable.setColorFilter(r1);
        }
    }

    /* loaded from: classes7.dex */
    public class WindowView extends SizeNotifierFrameLayout implements BottomSheetTabsOverlay.SheetView {
        private final Path clipPath;
        private boolean drawingFromOverlay;
        private final Paint navbarPaint;
        private final RectF rect;
        final /* synthetic */ BotWebViewSheet this$0;

        /* renamed from: org.telegram.ui.bots.BotWebViewSheet$WindowView$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ WindowView this$1;

            public AnonymousClass1(WindowView r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getTopOffset(int r1) {
                return AndroidUtilities.statusBarHeight;
            }
        }

        /* renamed from: -$$Nest$fgetdrawingFromOverlay, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21440$$Nest$fgetdrawingFromOverlay(WindowView r0) {
            return r0.drawingFromOverlay;
        }

        public WindowView(BotWebViewSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setClipChildren(false);
            setClipToPadding(false);
            setWillNotDraw(false);
            this.navbarPaint = new Paint(1);
            this.rect = new RectF();
            this.clipPath = new Path();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            if (this.drawingFromOverlay == false) goto L6;
            return;
        L6:
            if (BotWebViewSheet.m21400$$Nest$fgetpasscodeView(this.this$0).getVisibility() == 0) goto L24;
            if (BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0) >= 1.0f) goto L24;
            if (BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0) <= 0.0f) goto L24;
            this.navbarPaint.setColor(Theme.multAlpha(BotWebViewSheet.m21395$$Nest$fgetnavBarColor(this.this$0), BotWebViewSheet.m21397$$Nest$fgetopenedProgress(this.this$0)));
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).left <= 0) goto L15;
            r11.drawRect(0.0f, 0.0f, BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).left, getHeight(), this.navbarPaint);
        L15:
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).top <= 0) goto L18;
            r11.drawRect(0.0f, 0.0f, getWidth(), BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).top, this.navbarPaint);
        L18:
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).bottom <= 0) goto L21;
            r11.drawRect(0.0f, getHeight() - BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).bottom, getWidth(), getHeight(), this.navbarPaint);
        L21:
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).right <= 0) goto L24;
            r11.drawRect(getWidth() - BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).right, 0.0f, getWidth(), getHeight(), this.navbarPaint);
        L24:
            if (BotWebViewSheet.m21378$$Nest$fgetbottomTabsClip(this.this$0) != null) goto L26;
        L28:
            boolean r2 = false;
        L29:
            super.dispatchDraw(r11);
            if (r2 == false) goto L33;
            r11.restore();
        L33:
            if (BotWebViewSheet.m21400$$Nest$fgetpasscodeView(this.this$0).getVisibility() == 0) goto L53;
            this.navbarPaint.setColor(Theme.multAlpha(BotWebViewSheet.m21395$$Nest$fgetnavBarColor(this.this$0), BotWebViewSheet.m21397$$Nest$fgetopenedProgress(this.this$0)));
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).left <= 0) goto L38;
            r11.drawRect(0.0f, 0.0f, BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).left * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), getHeight(), this.navbarPaint);
        L38:
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).top <= 0) goto L41;
            r11.drawRect(0.0f, 0.0f, getWidth(), BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).top * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), this.navbarPaint);
        L41:
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).bottom <= 0) goto L50;
            float r1 = getHeight();
            float r22 = BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).bottom;
            if (BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0) != null) goto L45;
        L47:
            float r3 = 1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0);
        L48:
            r11.drawRect(0.0f, r1 - (r22 * r3), getWidth(), getHeight(), this.navbarPaint);
            goto L50
        L45:
            if (BotWebViewSheet.m21375$$Nest$fgetbotButtons(this.this$0).getTotalHeight() <= 0) goto L47;
            r3 = 1.0f;
        L50:
            if (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).right <= 0) goto L54;
            r11.drawRect(getWidth() - (BotWebViewSheet.m21396$$Nest$fgetnavInsets(this.this$0).right * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0))), 0.0f, getWidth(), getHeight(), this.navbarPaint);
            return;
        L54:
            return;
        L53:
            return;
        L26:
            if (AndroidUtilities.isTablet() == true) goto L28;
            r11.save();
            r11.translate(BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).left * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), 0.0f);
            BotWebViewSheet.m21378$$Nest$fgetbottomTabsClip(this.this$0).clip(r11, true, false, AndroidUtilities.lerp((getWidth() - BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).left) - BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).right, getWidth(), BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), getHeight(), 1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
            r11.translate((-BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).left) * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), 0.0f);
            r2 = true;
            goto L29
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r6) {
            LaunchActivity r0 = LaunchActivity.instance;
            if (r0 == null) goto L5;
            BottomSheetTabs r02 = r0.getBottomSheetTabs();
        L6:
            if (r02 == null) goto L18;
            if (BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0) == null) goto L18;
            int r1 = (int) (r02.getHeight(true) * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)));
            if (r6.getY() < ((getHeight() - BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).bottom) - r1)) goto L18;
            if (r6.getY() > (getHeight() - BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).bottom)) goto L18;
            if (AndroidUtilities.isTablet() == true) goto L18;
            return r02.touchEvent(r6.getAction(), r6.getX(), r6.getY() - ((getHeight() - BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).bottom) - r1));
        L18:
            return super.dispatchTouchEvent(r6);
        L5:
            r02 = null;
            goto L6
        }

        @Override // android.view.View
        public void draw(Canvas r13) {
            if (this.drawingFromOverlay == false) goto L5;
            return;
        L5:
            super.draw(r13);
            if (AndroidUtilities.isTablet() == false) goto L8;
            float r0 = 0.0f;
        L9:
            BotWebViewSheet.m21394$$Nest$fgetlinePaint(this.this$0).setColor(BotWebViewSheet.m21393$$Nest$fgetlineColor(this.this$0));
            BotWebViewSheet.m21394$$Nest$fgetlinePaint(this.this$0).setAlpha((int) ((BotWebViewSheet.m21394$$Nest$fgetlinePaint(this.this$0).getAlpha() * (1.0f - (Math.min(0.5f, r0) / 0.5f))) * (1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0))));
            r13.save();
            float r3 = 1.0f - r0;
            boolean r1 = AndroidUtilities.isTablet();
            BotWebViewSheet r2 = this.this$0;
            if (r1 == false) goto L13;
            float r02 = AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(r2).getTranslationY() + AndroidUtilities.dp(12.0f), AndroidUtilities.statusBarHeight / 2.0f, BotWebViewSheet.m21373$$Nest$fgetactionBarTransitionProgress(this.this$0));
        L12:
            float r8 = r02;
            r13.scale(r3, r3, getWidth() / 2.0f, r8);
            r13.drawLine((getWidth() / 2.0f) - AndroidUtilities.dp(16.0f), r8, (getWidth() / 2.0f) + AndroidUtilities.dp(16.0f), r8, BotWebViewSheet.m21394$$Nest$fgetlinePaint(this.this$0));
            r13.restore();
            BotWebViewSheet.m21372$$Nest$fgetactionBarShadow(this.this$0).setAlpha((int) (BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).getAlpha() * 255.0f));
            float r132 = (BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).getY() + BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).getTranslationY()) + BotWebViewSheet.m21369$$Nest$fgetactionBar(this.this$0).getHeight();
            BotWebViewSheet.m21372$$Nest$fgetactionBarShadow(this.this$0).setBounds(BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).left, (int) r132, getWidth() - BotWebViewSheet.m21391$$Nest$fgetinsets(this.this$0).right, (int) (r132 + BotWebViewSheet.m21372$$Nest$fgetactionBarShadow(this.this$0).getIntrinsicHeight()));
            BotWebViewSheet.m21372$$Nest$fgetactionBarShadow(this.this$0).draw(r13);
            return;
        L13:
            r02 = AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(r2).getTranslationY(), AndroidUtilities.statusBarHeight + (ActionBar.getCurrentActionBarHeight() / 2.0f), r0) + AndroidUtilities.dp(12.0f);
            goto L12
        L8:
            r0 = BotWebViewSheet.m21373$$Nest$fgetactionBarTransitionProgress(this.this$0);
            goto L9
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r8, View r9, long r10) {
            if (r9 == BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0)) goto L5;
        L11:
            boolean r0 = false;
        L12:
            boolean r7 = super.drawChild(r8, r9, r10);
            if (r0 == false) goto L15;
            r8.restore();
        L15:
            return r7;
        L5:
            if (BotWebViewSheet.m21387$$Nest$fgetfullscreenInProgress(this.this$0) == false) goto L11;
            if (BotWebViewSheet.m21408$$Nest$fgetswipeContainerFromHeight(this.this$0) <= 0) goto L11;
            if (BotWebViewSheet.m21409$$Nest$fgetswipeContainerFromWidth(this.this$0) <= 0) goto L11;
            r8.save();
            r8.clipRect(r9.getX(), r9.getY(), r9.getX() + AndroidUtilities.lerp(BotWebViewSheet.m21409$$Nest$fgetswipeContainerFromWidth(this.this$0), r9.getWidth(), BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(this.this$0)), r9.getY() + AndroidUtilities.lerp(BotWebViewSheet.m21408$$Nest$fgetswipeContainerFromHeight(this.this$0), r9.getHeight(), BotWebViewSheet.m21389$$Nest$fgetfullscreenTransitionProgress(this.this$0)));
            r0 = true;
            goto L12
        }

        @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.SheetView
        public float drawInto(Canvas r4, RectF r5, float r6, RectF r7, float r8, boolean r9) {
            this.rect.set(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getLeft(), BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY() + AndroidUtilities.dp(24.0f), BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getRight(), getHeight());
            AndroidUtilities.lerpCentered(this.rect, r5, r6, r7);
            r4.save();
            this.clipPath.rewind();
            float r52 = AndroidUtilities.dp(16.0f);
            float r92 = 1.0f;
            if (AndroidUtilities.isTablet() == true) goto L6;
            r92 = 1.0f - BotWebViewSheet.m21373$$Nest$fgetactionBarTransitionProgress(this.this$0);
        L6:
            float r53 = AndroidUtilities.lerp(r52 * r92, AndroidUtilities.dp(18.0f), r6);
            this.clipPath.addRoundRect(r7, r53, r53, Path.Direction.CW);
            r4.clipPath(this.clipPath);
            r4.drawPaint(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0));
            if (BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0) == null) goto L9;
            r4.save();
            r4.translate(r7.left, Math.max(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getY(), r7.top) + (r6 * AndroidUtilities.dp(51.0f)));
            BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).draw(r4);
            r4.restore();
        L9:
            r4.restore();
            return r53;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.SheetView
        public RectF getRect() {
            this.rect.set(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getLeft(), BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY() + AndroidUtilities.dp(24.0f), BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getRight(), getHeight());
            return this.rect;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this, new AnonymousClass1(this));
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this);
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            if (this.drawingFromOverlay == true) goto L31;
            super.onDraw(r11);
            if (BotWebViewSheet.m21400$$Nest$fgetpasscodeView(this.this$0).getVisibility() == 0) goto L30;
            r11.save();
            float r1 = 1.0f;
            if (BotWebViewSheet.m21378$$Nest$fgetbottomTabsClip(this.this$0) == null) goto L10;
            Canvas r3 = r11;
            BotWebViewSheet.m21378$$Nest$fgetbottomTabsClip(this.this$0).clip(r3, false, false, getWidth(), getHeight(), 1.0f - BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0));
        L12:
            if (BotWebViewSheet.m21398$$Nest$fgetoverrideBackgroundColor(this.this$0) == true) goto L20;
            int r112 = BotWebViewSheet.m21432$$Nest$mgetColor(this.this$0, Theme.key_windowBackgroundWhite);
            BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).setColor(r112);
            BotWebViewSheet.m21410$$Nest$fgetwebViewContainer(this.this$0).setFlickerViewColor(r112);
            if (BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0) == null) goto L20;
            ArticleViewer.ErrorContainer r113 = BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0);
            if (AndroidUtilities.computePerceivedBrightness(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).getColor()) > 0.721f) goto L18;
            boolean r4 = true;
        L19:
            r113.setDark(r4, false);
            BotWebViewSheet.m21384$$Nest$fgeterrorContainer(this.this$0).setBackgroundColor(BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0).getColor());
            goto L20
        L18:
            r4 = false;
        L20:
            RectF r114 = AndroidUtilities.rectTmp;
            r114.set(0.0f, 0.0f, getWidth(), getHeight());
            r3.drawRect(r114, BotWebViewSheet.m21382$$Nest$fgetdimPaint(this.this$0));
            if (BotWebViewSheet.m21377$$Nest$fgetbottomTabs(this.this$0) == null) goto L23;
            int r0 = BotWebViewSheet.m21377$$Nest$fgetbottomTabs(this.this$0).getHeight(true);
        L24:
            BotWebViewSheet.m21371$$Nest$fgetactionBarPaint(this.this$0).setColor(BotWebViewSheet.m21370$$Nest$fgetactionBarColor(this.this$0));
            float r42 = AndroidUtilities.dp(16.0f);
            if (AndroidUtilities.isTablet() == true) goto L28;
            r1 = 1.0f - BotWebViewSheet.m21373$$Nest$fgetactionBarTransitionProgress(this.this$0);
        L28:
            float r43 = r42 * r1;
            r114.set(AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getLeft(), 0, BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY(), 0.0f, BotWebViewSheet.m21373$$Nest$fgetactionBarTransitionProgress(this.this$0)), BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getRight(), (BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY() + AndroidUtilities.dp(24.0f)) + r43);
            r3.drawRoundRect(r114, r43, r43, BotWebViewSheet.m21371$$Nest$fgetactionBarPaint(this.this$0));
            r114.set(AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getLeft(), 0, BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY() + AndroidUtilities.dp(24.0f), AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getRight(), getWidth(), BotWebViewSheet.m21388$$Nest$fgetfullscreenProgress(this.this$0)), getHeight() - r0);
            r3.drawRect(r114, BotWebViewSheet.m21374$$Nest$fgetbackgroundPaint(this.this$0));
            r3.restore();
            return;
        L23:
            r0 = 0;
            goto L24
        L10:
            r3 = r11;
            goto L12
        L30:
            return;
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L13;
            if (r5.getY() > AndroidUtilities.lerp(BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getTranslationY() + AndroidUtilities.dp(24.0f), 0.0f, BotWebViewSheet.m21373$$Nest$fgetactionBarTransitionProgress(this.this$0))) goto L7;
        L10:
            this.this$0.dismiss(true, null);
            return true;
        L7:
            if (r5.getX() > BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getRight()) goto L10;
            if (r5.getX() < BotWebViewSheet.m21407$$Nest$fgetswipeContainer(this.this$0).getLeft()) goto L10;
        L13:
            return super.onTouchEvent(r5);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.SheetView
        public void setDrawingFromOverlay(boolean r2) {
            if (this.drawingFromOverlay == r2) goto L10;
            this.drawingFromOverlay = r2;
            invalidate();
            this.this$0.updateWindowFlags();
            if (LaunchActivity.instance != null) goto L7;
            return;
        L7:
            if (BotWebViewSheet.m21385$$Nest$fgetfullscreen(this.this$0) == false) goto L12;
            LaunchActivity.instance.setNavigationBarColor(BotWebViewSheet.m21395$$Nest$fgetnavBarColor(this.this$0));
            return;
        L12:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$2UpUXBw7E6MeUVyHnO5xTRXCwEI(BotWebViewSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestWebView$30(r1, r2);
    }

    /* renamed from: $r8$lambda$2f5umQ_31VEIuYwULHyrDV-jE4Y, reason: not valid java name */
    public static /* synthetic */ void m21350$r8$lambda$2f5umQ_31VEIuYwULHyrDVjE4Y(BotWebViewSheet r0, Boolean r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$47yMfI8gFjPF20eMFoqs6yWjJVM(BotWebViewSheet r0) {
        r0.lambda$new$15();
    }

    public static /* synthetic */ Bulletin $r8$lambda$5BrJMhKYBHcszFWfcD0qwzjvU_k(String r1, BulletinFactory r2) {
        return r2.createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(r1)).setDuration(5000);
    }

    public static /* synthetic */ void $r8$lambda$7tvMCrfGWAZ33wWaL6CXCYm6yq8(BotWebViewSheet r0) {
        r0.lambda$openOptions$37();
    }

    /* renamed from: $r8$lambda$8MHu8ULG-ulSmOWkq-iBVXNlD1E, reason: not valid java name */
    public static /* synthetic */ void m21351$r8$lambda$8MHu8ULGulSmOWkqiBVXNlD1E(BotWebViewSheet r0) {
        r0.lambda$new$10();
    }

    /* renamed from: $r8$lambda$BCIgM-PEyQbmlv_t7_U2fz4pnDU, reason: not valid java name */
    public static /* synthetic */ void m21352$r8$lambda$BCIgMPEyQbmlv_t7_U2fz4pnDU(BotWebViewSheet r0) {
        r0.lambda$openOptions$38();
    }

    /* renamed from: $r8$lambda$DgKYO82-WbcD7TPH95nHFX0lDZI, reason: not valid java name */
    public static /* synthetic */ void m21353$r8$lambda$DgKYO82WbcD7TPH95nHFX0lDZI(BotWebViewSheet r0, AlertDialog r1, int r2) {
        r0.lambda$onCheckDismissByUser$49(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EGY3gWg6mj6BjWn2wSuixccP2yE(BotWebViewSheet r0) {
        r0.openOptions();
    }

    public static /* synthetic */ void $r8$lambda$Gcb5WJkrU8WVt7DQrVbYrVdyjrY(BotWebViewSheet r0) {
        r0.lambda$openOptions$40();
    }

    public static /* synthetic */ void $r8$lambda$M1iIT4tP81cjmDtXZGag2HwG6cs(BotWebViewSheet r0) {
        r0.lambda$new$14();
    }

    public static /* synthetic */ void $r8$lambda$Mw36n8D4RWtZthJcWXAH6Ir1cks(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$O0WP2MuRjIOvy_wvv57D8UIpbXc(BotWebViewSheet r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$requestWebView$25(r1, r2);
    }

    /* renamed from: $r8$lambda$PeDm5zius66AEjIQvZlrq-rapYU, reason: not valid java name */
    public static /* synthetic */ void m21354$r8$lambda$PeDm5zius66AEjIQvZlrqrapYU(BotWebViewSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$QNglP01KvBGd4hpmDml78Oc2a0k(int r3, long r4, TLRPC.TL_attachMenuBot r6, Runnable r7, AlertDialog r8, int r9) {
        TLRPC.TL_messages_toggleBotInAttachMenu r82 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r82.bot = MessagesController.getInstance(r3).getInputUser(r4);
        r82.enabled = false;
        ConnectionsManager.getInstance(r3).sendRequest(r82, new BotWebViewSheet$$ExternalSyntheticLambda55(r3), 66);
        r6.show_in_side_menu = false;
        NotificationCenter.getInstance(r3).postNotificationName(NotificationCenter.attachMenuBotsDidLoad, new Object[0]);
        MediaDataController.getInstance(r3).uninstallShortcut(r4, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT);
        if (r7 == null) goto L6;
        r7.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$QNnLssW3fQ7qeC3p5SxDhMPpWSU(BotWebViewSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestWebView$24(r1, r2);
    }

    /* renamed from: $r8$lambda$S82fYoVWpj1D0VQ0tyRG-WGK9gk, reason: not valid java name */
    public static /* synthetic */ void m21355$r8$lambda$S82fYoVWpj1D0VQ0tyRGWGK9gk(BotWebViewSheet r0) {
        r0.lambda$openOptions$43();
    }

    public static /* synthetic */ void $r8$lambda$UBexEPtD0mzCtq526CLbHzIqDX8(BotWebViewSheet r0, TLRPC.UserFull r1) {
        r0.lambda$requestWebView$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$ULXNrV0t8jPXt34zFV9ORuNOQRM(BotWebViewSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestWebView$26(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Uk1OPGtpYkXZPWOmgS31oYKNe14(BotWebViewSheet r0, boolean r1) {
        r0.lambda$new$16(r1);
    }

    /* renamed from: $r8$lambda$VMwALkwMJt-aI7Q1jQ7yPSUok60, reason: not valid java name */
    public static /* synthetic */ void m21356$r8$lambda$VMwALkwMJtaI7Q1jQ7yPSUok60(BotWebViewSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestWebView$28(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VPyMPYdFk1rPjZ0zJg0OAmgKFAY(BotWebViewSheet r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$setNavigationBarColor$53(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$WGwDUJr4H_30rBS-xQ_z5RoLGnQ, reason: not valid java name */
    public static /* synthetic */ void m21357$r8$lambda$WGwDUJr4H_30rBSxQ_z5RoLGnQ(BotWebViewSheet r0, TLRPC.UserFull r1) {
        r0.lambda$requestWebView$20(r1);
    }

    /* renamed from: $r8$lambda$XBzB5629j-eWrRyJhB5VL3Jh3uA, reason: not valid java name */
    public static /* synthetic */ Boolean m21358$r8$lambda$XBzB5629jeWrRyJhB5VL3Jh3uA(BotWebViewSheet r0, Void r1) {
        return r0.lambda$new$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$YdEsMBMEyVYuRCAA5MK_DorFbTU(BotWebViewSheet r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$requestWebView$29(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ym162Q2uvzqlsqqiAVyn5p_rQds(BotWebViewSheet r0, ValueAnimator r1) {
        r0.lambda$setOpen$51(r1);
    }

    public static /* synthetic */ void $r8$lambda$Z1bSfvc7WGbAJwa_g904FN_LIOc(BotWebViewSheet r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$requestWebView$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZNUiUmGZuCvmdWCHncU8BS0i804(BotWebViewSheet r0) {
        r0.lambda$openOptions$41();
    }

    public static /* synthetic */ void $r8$lambda$ZmLTceaQTgrp_twnHWyjADwgpbQ(BotWebViewSheet r0) {
        r0.lambda$openOptions$39();
    }

    public static /* synthetic */ void $r8$lambda$_27sEcQdqmYG2ZwETiHoiDzUqGs(BotWebViewSheet r0, TLRPC.TL_error r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$a7g5Xrb_7vk5Q4dUJyaEuDXXf3k(BotWebViewSheet r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$setBackgroundColor$52(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$aMq0uA9DsiJl3k9F432ut6CzxX4(BotWebViewSheet r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$requestWebView$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$b_iI5snki_4_3lBTjMmpqBybE9E(BotWebViewSheet r0, int r1, int r2, BotWebViewMenuContainer$ActionBarColorsAnimating r3, ValueAnimator r4) {
        r0.lambda$setActionBarColor$54(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$dhmypthlwfdi0mzcNPXCnIIwOB8(BotWebViewSheet r1, float r2) {
        r1.actionBarTransitionProgress = r2;
        r1.windowView.invalidate();
        r1.actionBar.setAlpha(r2);
        r1.updateLightStatusBar();
        r1.updateDownloadBulletinArrow();
    }

    /* renamed from: $r8$lambda$diL0Vy-G9Fcsb9qUESWeeNBme8k, reason: not valid java name */
    public static /* synthetic */ void m21359$r8$lambda$diL0VyG9Fcsb9qUESWeeNBme8k(BotWebViewSheet r0, View r1) {
        r0.lambda$createErrorContainer$55(r1);
    }

    public static /* synthetic */ void $r8$lambda$eJMXEQSoEcdiqJn8n4S5tMSk65I(BotWebViewSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestWebView$32(r1, r2);
    }

    /* renamed from: $r8$lambda$eOT5U1u-XZp3i9BMOAn02FrxHpw, reason: not valid java name */
    public static /* synthetic */ void m21360$r8$lambda$eOT5U1uXZp3i9BMOAn02FrxHpw(BotWebViewSheet r0, View r1) {
        r0.lambda$requestWebView$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$evF7eKhHFXxSyPHiIpOIvMO9Qrg(BotWebViewSheet r0, Runnable r1) {
        r0.lambda$dismiss$50(r1);
    }

    public static /* synthetic */ void $r8$lambda$fatxnFTKYV7wYWPVu7v41zbcw3s(BotWebViewSheet r0) {
        r0.lambda$new$11();
    }

    public static /* synthetic */ WindowInsets $r8$lambda$hkzANNCJGfQJNNfI0MojNym5LmA(BotWebViewSheet r0, View r1, WindowInsets r2) {
        return r0.lambda$onCreate$18(r1, r2);
    }

    /* renamed from: $r8$lambda$i51czUNVwE0XuE5LZv5CWW-ALJY, reason: not valid java name */
    public static /* synthetic */ void m21361$r8$lambda$i51czUNVwE0XuE5LZv5CWWALJY(BotWebViewSheet r0, TLRPC.TL_webViewResultUrl r1, TLRPC.TL_error r2) {
        r0.lambda$requestWebView$33(r1, r2);
    }

    /* renamed from: $r8$lambda$jp9aF9w0GnNlYKAr6rI-Tx-5BtQ, reason: not valid java name */
    public static /* synthetic */ void m21362$r8$lambda$jp9aF9w0GnNlYKAr6rITx5BtQ(BotWebViewSheet r0, Float r1) {
        r0.lambda$new$13(r1);
    }

    /* renamed from: $r8$lambda$kcTS-N6uGjZh1sWuNW0ZECiQLWA, reason: not valid java name */
    public static /* synthetic */ void m21363$r8$lambda$kcTSN6uGjZh1sWuNW0ZECiQLWA(int r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda56(r0));
    }

    /* renamed from: $r8$lambda$kx7_NOsG2o-4vjrL2U9wUJxuoTk, reason: not valid java name */
    public static /* synthetic */ float m21364$r8$lambda$kx7_NOsG2o4vjrL2U9wUJxuoTk(BotWebViewSheet r0) {
        return r0.actionBarTransitionProgress;
    }

    public static /* synthetic */ void $r8$lambda$lVnYKLNsm9lSlXL6OOlxSVyOgps(BotWebViewSheet r0) {
        r0.lambda$openOptions$44();
    }

    public static /* synthetic */ void $r8$lambda$m1zU1JXk5NQS_jqubrgOPHU3MyI() {
    }

    /* renamed from: $r8$lambda$oNchPCyw5k_RX06eaUsgfDJ-fuA, reason: not valid java name */
    public static /* synthetic */ void m21365$r8$lambda$oNchPCyw5k_RX06eaUsgfDJfuA(int r2) {
        MediaDataController.getInstance(r2).loadAttachMenuBots(false, true);
    }

    public static /* synthetic */ Object $r8$lambda$qMLFitaQ3GBOlJN23I1tK38FGRE(int r1, boolean r2, Integer r3) {
        int r12 = Theme.blendOver(r1, r3.intValue());
        Integer r32 = Integer.valueOf(r12);
        if (r2 == true) goto L5;
        return r32;
    L5:
        return String.format(Locale.US, "#%02X%02X%02X", new Object[]{Integer.valueOf(Color.red(r12)), Integer.valueOf(Color.green(r12)), Integer.valueOf(Color.blue(r12))});
    }

    public static /* synthetic */ void $r8$lambda$qO8p3PsxFI_Pm1aa140UOVR7Qgk(BotWebViewSheet r0) {
        r0.lambda$new$6();
    }

    /* renamed from: $r8$lambda$rUiyGRXDCDVtUD2b9376-MDPs9Y, reason: not valid java name */
    public static /* synthetic */ void m21366$r8$lambda$rUiyGRXDCDVtUD2b9376MDPs9Y(BotWebViewSheet r0) {
        r0.lambda$openOptions$42();
    }

    public static /* synthetic */ void $r8$lambda$rvtCxScK4gF11t5UVZJFOcIzeg0(BotWebViewSheet r0, int r1, boolean r2) {
        r0.lambda$new$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sY_VywnmNVNhohTvMF1QEfT8Jso(BotWebViewSheet r0, ValueAnimator r1) {
        r0.lambda$new$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$skIE5xmNr8eO9CrnKAvdwqFXGRE(BotWebViewSheet r0) {
        r0.relayout();
    }

    /* renamed from: $r8$lambda$tut-O19ZUAyLrTDfbzKAzgGZp8U, reason: not valid java name */
    public static /* synthetic */ void m21367$r8$lambda$tutO19ZUAyLrTDfbzKAzgGZp8U(BotWebViewSheet r0, BotDownloads.FileDownload r1, View r2) {
        r0.lambda$updateDownloadBulletin$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vVEXsavnRsVZ7GrDkDZUHrKb8oc(BotWebViewSheet r0) {
        r0.lambda$openOptions$36();
    }

    public static /* synthetic */ void $r8$lambda$xJ8_fTiSuloYrMXXa7khhG0wBww(BotWebViewSheet r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$requestWebView$31(r1, r2);
    }

    /* renamed from: $r8$lambda$xpwaAgXqqyA-SRULz9s3VmE6rNA, reason: not valid java name */
    public static /* synthetic */ void m21368$r8$lambda$xpwaAgXqqyASRULz9s3VmE6rNA(BotWebViewSheet r0) {
        r0.lambda$new$9();
    }

    public static /* synthetic */ void $r8$lambda$xqSseYw7iESlbLap7n_ODTCDrgM(BotWebViewSheet r0, String r1) {
        r0.lambda$showJustAddedBulletin$1(r1);
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m21369$$Nest$fgetactionBar(BotWebViewSheet r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactionBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21370$$Nest$fgetactionBarColor(BotWebViewSheet r0) {
        return r0.actionBarColor;
    }

    /* renamed from: -$$Nest$fgetactionBarPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21371$$Nest$fgetactionBarPaint(BotWebViewSheet r0) {
        return r0.actionBarPaint;
    }

    /* renamed from: -$$Nest$fgetactionBarShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m21372$$Nest$fgetactionBarShadow(BotWebViewSheet r0) {
        return r0.actionBarShadow;
    }

    /* renamed from: -$$Nest$fgetactionBarTransitionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21373$$Nest$fgetactionBarTransitionProgress(BotWebViewSheet r0) {
        return r0.actionBarTransitionProgress;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21374$$Nest$fgetbackgroundPaint(BotWebViewSheet r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetbotButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotButtons m21375$$Nest$fgetbotButtons(BotWebViewSheet r0) {
        return r0.botButtons;
    }

    /* renamed from: -$$Nest$fgetbotId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21376$$Nest$fgetbotId(BotWebViewSheet r2) {
        return r2.botId;
    }

    /* renamed from: -$$Nest$fgetbottomTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomSheetTabs m21377$$Nest$fgetbottomTabs(BotWebViewSheet r0) {
        return r0.bottomTabs;
    }

    /* renamed from: -$$Nest$fgetbottomTabsClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomSheetTabs.ClipTools m21378$$Nest$fgetbottomTabsClip(BotWebViewSheet r0) {
        return r0.bottomTabsClip;
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m21379$$Nest$fgetbulletinContainer(BotWebViewSheet r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetbuttonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21380$$Nest$fgetbuttonText(BotWebViewSheet r0) {
        return r0.buttonText;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21381$$Nest$fgetcurrentAccount(BotWebViewSheet r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdimPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21382$$Nest$fgetdimPaint(BotWebViewSheet r0) {
        return r0.dimPaint;
    }

    /* renamed from: -$$Nest$fgetdismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21383$$Nest$fgetdismissed(BotWebViewSheet r0) {
        return r0.dismissed;
    }

    /* renamed from: -$$Nest$fgeterrorContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArticleViewer.ErrorContainer m21384$$Nest$fgeterrorContainer(BotWebViewSheet r0) {
        return r0.errorContainer;
    }

    /* renamed from: -$$Nest$fgetfullscreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21385$$Nest$fgetfullscreen(BotWebViewSheet r0) {
        return r0.fullscreen;
    }

    /* renamed from: -$$Nest$fgetfullscreenButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotFullscreenButtons m21386$$Nest$fgetfullscreenButtons(BotWebViewSheet r0) {
        return r0.fullscreenButtons;
    }

    /* renamed from: -$$Nest$fgetfullscreenInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21387$$Nest$fgetfullscreenInProgress(BotWebViewSheet r0) {
        return r0.fullscreenInProgress;
    }

    /* renamed from: -$$Nest$fgetfullscreenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21388$$Nest$fgetfullscreenProgress(BotWebViewSheet r0) {
        return r0.fullscreenProgress;
    }

    /* renamed from: -$$Nest$fgetfullscreenTransitionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21389$$Nest$fgetfullscreenTransitionProgress(BotWebViewSheet r0) {
        return r0.fullscreenTransitionProgress;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21390$$Nest$fgetignoreLayout(BotWebViewSheet r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m21391$$Nest$fgetinsets(BotWebViewSheet r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetlastTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomSheetTabs.WebTabData m21392$$Nest$fgetlastTab(BotWebViewSheet r0) {
        return r0.lastTab;
    }

    /* renamed from: -$$Nest$fgetlineColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21393$$Nest$fgetlineColor(BotWebViewSheet r0) {
        return r0.lineColor;
    }

    /* renamed from: -$$Nest$fgetlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21394$$Nest$fgetlinePaint(BotWebViewSheet r0) {
        return r0.linePaint;
    }

    /* renamed from: -$$Nest$fgetnavBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21395$$Nest$fgetnavBarColor(BotWebViewSheet r0) {
        return r0.navBarColor;
    }

    /* renamed from: -$$Nest$fgetnavInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m21396$$Nest$fgetnavInsets(BotWebViewSheet r0) {
        return r0.navInsets;
    }

    /* renamed from: -$$Nest$fgetopenedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21397$$Nest$fgetopenedProgress(BotWebViewSheet r0) {
        return r0.openedProgress;
    }

    /* renamed from: -$$Nest$fgetoverrideBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21398$$Nest$fgetoverrideBackgroundColor(BotWebViewSheet r0) {
        return r0.overrideBackgroundColor;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m21399$$Nest$fgetparentActivity(BotWebViewSheet r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetpasscodeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PasscodeView m21400$$Nest$fgetpasscodeView(BotWebViewSheet r0) {
        return r0.passcodeView;
    }

    /* renamed from: -$$Nest$fgetpollRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21401$$Nest$fgetpollRunnable(BotWebViewSheet r0) {
        return r0.pollRunnable;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertBotWebViewLayout.WebProgressView m21402$$Nest$fgetprogressView(BotWebViewSheet r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetqueryId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21403$$Nest$fgetqueryId(BotWebViewSheet r2) {
        return r2.queryId;
    }

    /* renamed from: -$$Nest$fgetresetOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21404$$Nest$fgetresetOffsetY(BotWebViewSheet r0) {
        return r0.resetOffsetY;
    }

    /* renamed from: -$$Nest$fgetsensors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotSensors m21405$$Nest$fgetsensors(BotWebViewSheet r0) {
        return r0.sensors;
    }

    /* renamed from: -$$Nest$fgetsuperDismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21406$$Nest$fgetsuperDismissed(BotWebViewSheet r0) {
        return r0.superDismissed;
    }

    /* renamed from: -$$Nest$fgetswipeContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer m21407$$Nest$fgetswipeContainer(BotWebViewSheet r0) {
        return r0.swipeContainer;
    }

    /* renamed from: -$$Nest$fgetswipeContainerFromHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21408$$Nest$fgetswipeContainerFromHeight(BotWebViewSheet r0) {
        return r0.swipeContainerFromHeight;
    }

    /* renamed from: -$$Nest$fgetswipeContainerFromWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21409$$Nest$fgetswipeContainerFromWidth(BotWebViewSheet r0) {
        return r0.swipeContainerFromWidth;
    }

    /* renamed from: -$$Nest$fgetwebViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotWebViewContainer m21410$$Nest$fgetwebViewContainer(BotWebViewSheet r0) {
        return r0.webViewContainer;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowView m21411$$Nest$fgetwindowView(BotWebViewSheet r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputactionBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21412$$Nest$fputactionBarColor(BotWebViewSheet r0, int r1) {
        r0.actionBarColor = r1;
    }

    /* renamed from: -$$Nest$fputactionBarColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21413$$Nest$fputactionBarColorKey(BotWebViewSheet r0, int r1) {
        r0.actionBarColorKey = r1;
    }

    /* renamed from: -$$Nest$fputbackButtonShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21414$$Nest$fputbackButtonShown(BotWebViewSheet r0, boolean r1) {
        r0.backButtonShown = r1;
    }

    /* renamed from: -$$Nest$fputdismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21415$$Nest$fputdismissed(BotWebViewSheet r0, boolean r1) {
        r0.dismissed = r1;
    }

    /* renamed from: -$$Nest$fputerrorCode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21416$$Nest$fputerrorCode(BotWebViewSheet r0, String r1) {
        r0.errorCode = r1;
    }

    /* renamed from: -$$Nest$fputerrorShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21417$$Nest$fputerrorShown(BotWebViewSheet r0, boolean r1) {
        r0.errorShown = r1;
    }

    /* renamed from: -$$Nest$fputforceExpnaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21418$$Nest$fputforceExpnaded(BotWebViewSheet r0, boolean r1) {
        r0.forceExpnaded = r1;
    }

    /* renamed from: -$$Nest$fputfullscreenInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21419$$Nest$fputfullscreenInProgress(BotWebViewSheet r0, boolean r1) {
        r0.fullscreenInProgress = r1;
    }

    /* renamed from: -$$Nest$fputfullscreenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21420$$Nest$fputfullscreenProgress(BotWebViewSheet r0, float r1) {
        r0.fullscreenProgress = r1;
    }

    /* renamed from: -$$Nest$fputfullscreenTransitionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21421$$Nest$fputfullscreenTransitionProgress(BotWebViewSheet r0, float r1) {
        r0.fullscreenTransitionProgress = r1;
    }

    /* renamed from: -$$Nest$fputhasSettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21422$$Nest$fputhasSettings(BotWebViewSheet r0, boolean r1) {
        r0.hasSettings = r1;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21423$$Nest$fputignoreLayout(BotWebViewSheet r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$fputlastTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21424$$Nest$fputlastTab(BotWebViewSheet r0, BottomSheetTabs.WebTabData r1) {
        r0.lastTab = r1;
    }

    /* renamed from: -$$Nest$fputlineColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21425$$Nest$fputlineColor(BotWebViewSheet r0, int r1) {
        r0.lineColor = r1;
    }

    /* renamed from: -$$Nest$fputnavBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21426$$Nest$fputnavBarColor(BotWebViewSheet r0, int r1) {
        r0.navBarColor = r1;
    }

    /* renamed from: -$$Nest$fputneedCloseConfirmation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21427$$Nest$fputneedCloseConfirmation(BotWebViewSheet r0, boolean r1) {
        r0.needCloseConfirmation = r1;
    }

    /* renamed from: -$$Nest$fputopenedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21428$$Nest$fputopenedProgress(BotWebViewSheet r0, float r1) {
        r0.openedProgress = r1;
    }

    /* renamed from: -$$Nest$fputresetOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21429$$Nest$fputresetOffsetY(BotWebViewSheet r0, boolean r1) {
        r0.resetOffsetY = r1;
    }

    /* renamed from: -$$Nest$fputsensors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21430$$Nest$fputsensors(BotWebViewSheet r0, BotSensors r1) {
        r0.sensors = r1;
    }

    /* renamed from: -$$Nest$fputsuperDismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21431$$Nest$fputsuperDismissed(BotWebViewSheet r0, boolean r1) {
        r0.superDismissed = r1;
    }

    /* renamed from: -$$Nest$mgetColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21432$$Nest$mgetColor(BotWebViewSheet r0, int r1) {
        return r0.getColor(r1);
    }

    /* renamed from: -$$Nest$mshowBulletin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21433$$Nest$mshowBulletin(BotWebViewSheet r0, Utilities.CallbackReturn r1) {
        r0.showBulletin(r1);
    }

    /* renamed from: -$$Nest$mupdateActionBarColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21434$$Nest$mupdateActionBarColors(BotWebViewSheet r0) {
        r0.updateActionBarColors();
    }

    /* renamed from: -$$Nest$mupdateDownloadBulletinArrow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21435$$Nest$mupdateDownloadBulletinArrow(BotWebViewSheet r0) {
        r0.updateDownloadBulletinArrow();
    }

    /* renamed from: -$$Nest$mupdateWebViewBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21436$$Nest$mupdateWebViewBackgroundColor(BotWebViewSheet r0) {
        r0.updateWebViewBackgroundColor();
    }

    static {
        activeSheets = new HashSet();
        ACTION_BAR_TRANSITION_PROGRESS_VALUE = new SimpleFloatPropertyCompat("actionBarTransitionProgress", new BotWebViewSheet$$ExternalSyntheticLambda0(), new BotWebViewSheet$$ExternalSyntheticLambda1()).setMultiplier(100.0f);
        shownLockedBots = 0;
    }

    public BotWebViewSheet(Context r19, Theme.ResourcesProvider r20) {
        super(r19, R.style.TransparentDialog);
        this.actionBarTransitionProgress = 0.0f;
        this.navInsets = new Rect();
        this.insets = new Rect();
        this.keyboardInset = 0;
        boolean r8 = true;
        this.linePaint = new Paint(1);
        this.dimPaint = new Paint();
        this.backgroundPaint = new Paint(1);
        this.actionBarPaint = new Paint(1);
        this.pollRunnable = new BotWebViewSheet$$ExternalSyntheticLambda14(this);
        this.actionBarColorKey = -1;
        this.defaultFullsize = false;
        BottomSheetTabs r10 = null;
        this.fullsize = null;
        this.fileItems = new HashMap();
        this.superDismissed = false;
        this.resetOffsetY = true;
        this.attached = false;
        this.resourcesProvider = r20;
        this.lineColor = Theme.getColor(Theme.key_sheet_scrollUp);
        AnonymousClass1 r0 = new AnonymousClass1(this, r19);
        this.swipeContainer = r0;
        r0.setAllowFullSizeSwipe(true);
        this.swipeContainer.setShouldWaitWebViewScroll(true);
        int r11 = Theme.key_windowBackgroundWhite;
        AnonymousClass2 r02 = new AnonymousClass2(this, r19, r20, getColor(r11), true);
        this.webViewContainer = r02;
        r02.setOnVerifiedAge(this.onVerifiedAge);
        this.webViewContainer.setDelegate(new AnonymousClass3(this, r19, r20));
        this.linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        this.dimPaint.setColor(TLObject.FLAG_30);
        this.actionBarColor = getColor(r11);
        int r03 = getColor(Theme.key_windowBackgroundGray);
        this.navBarColor = r03;
        AndroidUtilities.setNavigationBarColor(this, r03, false);
        WindowView r04 = new WindowView(this, r19);
        this.windowView = r04;
        Objects.requireNonNull(r04);
        BotWebViewSheet$$ExternalSyntheticLambda19 r4 = new BotWebViewSheet$$ExternalSyntheticLambda19(r04);
        this.invalidateRunnable = r4;
        BotWebViewSheet$$ExternalSyntheticLambda20 r05 = new BotWebViewSheet$$ExternalSyntheticLambda20(this);
        this.relayoutRunnable = r05;
        this.windowView.setDelegate(new BotWebViewSheet$$ExternalSyntheticLambda21(this));
        WindowView r5 = this.windowView;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r112 = this.swipeContainer;
        FrameLayout.LayoutParams r13 = LayoutHelper.createFrame(-1, -1, 49);
        this.swipeContainerLayoutParams = r13;
        r5.addView(r112, r13);
        AnonymousClass4 r52 = new AnonymousClass4(this, getContext(), r20);
        this.botButtons = r52;
        r52.setOnButtonClickListener(new BotWebViewSheet$$ExternalSyntheticLambda22(this));
        this.botButtons.setOnResizeListener(new BotWebViewSheet$$ExternalSyntheticLambda23(this));
        WindowView r53 = this.windowView;
        BotButtons r113 = this.botButtons;
        FrameLayout.LayoutParams r132 = LayoutHelper.createFrame(-1, -2, 81);
        this.botButtonsLayoutParams = r132;
        r53.addView(r113, r132);
        BotFullscreenButtons r54 = new BotFullscreenButtons(getContext());
        this.fullscreenButtons = r54;
        r54.setAlpha(0.0f);
        this.fullscreenButtons.setVisibility(8);
        if (MessagesController.getInstance(this.currentAccount).disableBotFullscreenBlur == false) goto L5;
    L7:
        r8 = false;
    L8:
        this.fullscreenBlur = r8;
        BotFullscreenButtons r55 = this.fullscreenButtons;
        if (r8 == false) goto L11;
        Object r82 = this.swipeContainer.getRenderNode();
    L12:
        r55.setParentRenderNode(r82);
        this.windowView.addView(this.fullscreenButtons, LayoutHelper.createFrame(-1, -1, 119));
        this.fullscreenButtons.setOnCloseClickListener(new BotWebViewSheet$$ExternalSyntheticLambda24(this));
        this.fullscreenButtons.setOnCollapseClickListener(new BotWebViewSheet$$ExternalSyntheticLambda25(this));
        this.fullscreenButtons.setOnMenuClickListener(new BotWebViewSheet$$ExternalSyntheticLambda26(this));
        FrameLayout r56 = new FrameLayout(r19);
        this.bulletinContainer = r56;
        WindowView r83 = this.windowView;
        FrameLayout.LayoutParams r114 = LayoutHelper.createFrame(-1, 200, 55);
        this.bulletinContainerLayoutParams = r114;
        r83.addView(r56, r114);
        this.actionBarShadow = ContextCompat.getDrawable(getContext(), R.drawable.header_shadow).mutate();
        AnonymousClass5 r57 = new AnonymousClass5(this, r19, r20);
        this.actionBar = r57;
        r57.setBackgroundColor(0);
        this.actionBar.setBackButtonImage(R.drawable.ic_close_white);
        updateActionBarColors();
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass6(this));
        this.actionBar.setAlpha(0.0f);
        WindowView r58 = this.windowView;
        ActionBar r6 = this.actionBar;
        FrameLayout.LayoutParams r7 = LayoutHelper.createFrame(-1, -2, 49);
        this.actionBarLayoutParams = r7;
        r58.addView(r6, r7);
        WindowView r59 = this.windowView;
        AnonymousClass7 r62 = new AnonymousClass7(this, r19, r20);
        this.progressView = r62;
        r59.addView(r62, LayoutHelper.createFrame(-1, -2.0f, 81, 0.0f, 0.0f, 0.0f, 0.0f));
        this.webViewContainer.setWebViewProgressListener(new BotWebViewSheet$$ExternalSyntheticLambda27(this));
        this.swipeContainer.addView(this.webViewContainer, LayoutHelper.createFrame(-1, -1.0f));
        this.swipeContainer.setScrollListener(new BotWebViewSheet$$ExternalSyntheticLambda15(this));
        this.swipeContainer.setScrollEndListener(new BotWebViewSheet$$ExternalSyntheticLambda16(this));
        this.swipeContainer.setDelegate(new BotWebViewSheet$$ExternalSyntheticLambda17(this));
        this.swipeContainer.setIsKeyboardVisible(new BotWebViewSheet$$ExternalSyntheticLambda18(this));
        PasscodeView r3 = new PasscodeView(r19);
        this.passcodeView = r3;
        this.windowView.addView(r3, LayoutHelper.createFrame(-1, -1.0f));
        setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
        updateFullscreenLayout();
        LaunchActivity r2 = LaunchActivity.instance;
        if (r2 == null) goto L15;
        r10 = r2.getBottomSheetTabs();
    L15:
        this.bottomTabs = r10;
        if (r10 == null) goto L19;
        r10.listen(r4, r05);
        this.bottomTabsClip = new BottomSheetTabs.ClipTools(this.bottomTabs);
        return;
    L19:
        return;
    L11:
        r82 = null;
        goto L12
    L5:
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L7;
        goto L7
    }

    public static /* synthetic */ void access$001(BotWebViewSheet r0) {
        super.dismiss();
    }

    private void applyAppBotSettings(TL_bots.botAppSettings r6, boolean r7) {
        if (r6 == null) goto L32;
        boolean r0 = Theme.isCurrentThemeDark();
        int r1 = r6.flags;
        if (r0 == false) goto L7;
        int r2 = 4;
    L9:
        if ((r2 & r1) == 0) goto L11;
        boolean r22 = true;
    L12:
        if (r0 == false) goto L14;
        int r4 = 16;
    L16:
        if ((r1 & r4) == 0) goto L21;
        if (r0 == false) goto L19;
        int r12 = r6.header_dark_color;
    L20:
        setActionBarColor(r12 | (-16777216), true, r7);
        goto L21
    L19:
        r12 = r6.header_color;
    L21:
        if (r22 == false) goto L31;
        if (r0 == false) goto L24;
        int r13 = r6.background_dark_color;
    L25:
        setBackgroundColor(r13 | (-16777216), true, r7);
        if (r0 == false) goto L28;
        int r62 = r6.background_dark_color;
    L29:
        setNavigationBarColor(r62 | (-16777216), r7);
        return;
    L28:
        r62 = r6.background_color;
        goto L29
    L24:
        r13 = r6.background_color;
        goto L25
    L31:
        return;
    L14:
        r4 = 8;
        goto L16
    L11:
        r22 = false;
        goto L12
    L7:
        r2 = 2;
        goto L9
    }

    public static void deleteBot(int r12, long r13, Runnable r15) {
        ArrayList<TLRPC.TL_attachMenuBot> r0 = MediaDataController.getInstance(r12).getAttachMenuBots().bots;
        int r1 = r0.size();
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L8;
        TLRPC.TL_attachMenuBot r4 = r0.get(r2);
        r2 = r2 + 1;
        TLRPC.TL_attachMenuBot r42 = r4;
        if (r42.bot_id != r13) goto L4;
        TLRPC.TL_attachMenuBot r10 = r42;
    L9:
        if (r10 != null) goto L11;
        return;
    L11:
        new AlertDialog.Builder(LaunchActivity.getLastFragment().getContext()).setTitle(LocaleController.getString(R.string.BotRemoveFromMenuTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotRemoveFromMenu, new Object[]{r10.short_name}))).setPositiveButton(LocaleController.getString(R.string.OK), new BotWebViewSheet$$ExternalSyntheticLambda54(r12, r13, r10, r15)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L8:
        r10 = null;
        goto L9
    }

    private int getColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$createErrorContainer$55(View r1) {
        BotWebViewContainer.MyWebView r0 = this.webViewContainer.getWebView();
        if (r0 == null) goto L6;
        r0.reload();
        return;
    }

    private /* synthetic */ void lambda$dismiss$50(Runnable r2) {
        if (this.superDismissed == true) goto L5;
        super.dismiss();
        this.superDismissed = true;
    L5:
        if (r2 == null) goto L8;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$new$10() {
        if (this.webViewContainer.onBackPressed() == true) goto L6;
        onCheckDismissByUser();
        return;
    }

    private /* synthetic */ void lambda$new$11() {
        this.forceExpnaded = true;
        dismiss(true, null);
    }

    private /* synthetic */ void lambda$new$12(ValueAnimator r1) {
        this.progressView.setAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$new$13(Float r3) {
        this.progressView.setLoadProgressAnimated(r3.floatValue());
        if (r3.floatValue() != 1.0f) goto L6;
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(200);
        r32.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r32.addUpdateListener(new BotWebViewSheet$$ExternalSyntheticLambda32(this));
        r32.addListener(new AnonymousClass8(this));
        r32.start();
        return;
    }

    private /* synthetic */ void lambda$new$14() {
        float r0 = this.swipeContainer.getSwipeOffsetY();
        Paint r2 = this.dimPaint;
        if (r0 <= 0.0f) goto L5;
        r2.setAlpha((int) ((1.0f - MathUtils.clamp(this.swipeContainer.getSwipeOffsetY() / this.swipeContainer.getHeight(), 0.0f, 1.0f)) * 64.0f));
    L6:
        this.windowView.invalidate();
        this.webViewContainer.invalidateViewPortHeight();
        if (this.springAnimation == null) goto L16;
        if ((1.0f - (Math.min(this.swipeContainer.getTopActionBarOffsetY(), this.swipeContainer.getTranslationY() - this.swipeContainer.getTopActionBarOffsetY()) / this.swipeContainer.getTopActionBarOffsetY())) <= 0.5f) goto L11;
        int r02 = 1;
    L12:
        float r03 = r02 * 100.0f;
        if (this.springAnimation.getSpring().getFinalPosition() == r03) goto L16;
        this.springAnimation.getSpring().setFinalPosition(r03);
        this.springAnimation.start();
        goto L16
    L11:
        r02 = 0;
    L16:
        if (this.fullscreen == false) goto L18;
        int r04 = this.insets.bottom;
    L19:
        this.lastSwipeTime = System.currentTimeMillis();
        return;
    L18:
        Math.max(0.0f, this.swipeContainer.getSwipeOffsetY());
        goto L19
    L5:
        r2.setAlpha(64);
        goto L6
    }

    private /* synthetic */ void lambda$new$15() {
        this.webViewContainer.invalidateViewPortHeight(true);
    }

    private /* synthetic */ void lambda$new$16(boolean r2) {
        if (this.fullscreen == false) goto L6;
        if (r2 == false) goto L6;
        return;
    L6:
        dismiss(true, null);
    }

    private /* synthetic */ Boolean lambda$new$17(Void r1) {
        if (this.windowView.getKeyboardHeight() < AndroidUtilities.dp(20.0f)) goto L5;
        boolean r0 = true;
    L7:
        return Boolean.valueOf(r0);
    L5:
        r0 = false;
        goto L7
    }

    private /* synthetic */ void lambda$new$4(TLRPC.TL_error r3) {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        if (r3 == null) goto L8;
        dismiss();
        return;
    L8:
        AndroidUtilities.runOnUIThread(this.pollRunnable, 60000);
    }

    private /* synthetic */ void lambda$new$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda52(this, r2));
    }

    private /* synthetic */ void lambda$new$6() {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        if (this.queryId == 0) goto L18;
        TLRPC.TL_messages_prolongWebView r0 = new TLRPC.TL_messages_prolongWebView();
        r0.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botId);
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.peerId);
        r0.query_id = this.queryId;
        r0.silent = this.silent;
        if (this.replyToMsgId == 0) goto L13;
        TLRPC.InputReplyTo r1 = SendMessagesHelper.getInstance(this.currentAccount).createReplyInput(this.replyToMsgId);
        r0.reply_to = r1;
        if (this.monoforumTopicId == 0) goto L11;
        r1.monoforum_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(this.monoforumTopicId);
        r0.reply_to.flags |= 32;
    L11:
        r0.flags |= 1;
    L15:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BotWebViewSheet$$ExternalSyntheticLambda51(this));
        return;
    L13:
        if (this.monoforumTopicId == 0) goto L15;
        TLRPC.TL_inputReplyToMonoForum r12 = new TLRPC.TL_inputReplyToMonoForum();
        r0.reply_to = r12;
        r12.monoforum_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(this.monoforumTopicId);
        r0.flags |= 1;
        goto L15
    }

    private /* synthetic */ void lambda$new$7(int r1, boolean r2) {
        if (r1 <= AndroidUtilities.dp(20.0f)) goto L6;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r12 = this.swipeContainer;
        r12.stickTo((-r12.getOffsetY()) + this.swipeContainer.getTopActionBarOffsetY());
        return;
    }

    private /* synthetic */ void lambda$new$8(Boolean r2) {
        if (this.webViewContainer == null) goto L10;
        boolean r22 = r2.booleanValue();
        BotWebViewContainer r1 = this.webViewContainer;
        if (r22 == false) goto L8;
        r1.onMainButtonPressed();
        return;
    L8:
        r1.onSecondaryButtonPressed();
        return;
    }

    private /* synthetic */ void lambda$new$9() {
        this.swipeContainer.requestLayout();
    }

    private /* synthetic */ void lambda$onCheckDismissByUser$49(AlertDialog r1, int r2) {
        dismiss();
    }

    private /* synthetic */ WindowInsets lambda$onCreate$18(View r7, WindowInsets r8) {
        WindowInsetsCompat r72 = WindowInsetsCompat.toWindowInsetsCompat(r8, r7);
        Insets r0 = r72.getInsets(WindowInsetsCompat.Type.navigationBars());
        this.navInsets.set(r0.left, r0.top, r0.right, r0.bottom);
        Insets r02 = r72.getInsets(WindowInsetsCompat.Type.displayCutout() | WindowInsetsCompat.Type.systemBars());
        this.insets.set(Math.max(r02.left, r8.getStableInsetLeft()), Math.max(r02.top, r8.getStableInsetTop()), Math.max(r02.right, r8.getStableInsetRight()), Math.max(r02.bottom, r8.getStableInsetBottom()));
        int r03 = Build.VERSION.SDK_INT;
        if (r03 > 28) goto L5;
        Rect r1 = this.insets;
        r1.top = Math.max(r1.top, AndroidUtilities.getStatusBarHeight(getContext()));
    L5:
        int r73 = r72.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        if (r73 > this.insets.bottom) goto L8;
    L10:
        this.keyboardInset = 0;
    L11:
        updateFullscreenLayout();
        if (r03 < 30) goto L16;
        return WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m();
    L16:
        return r8.consumeSystemWindowInsets();
    L8:
        if (r73 <= AndroidUtilities.dp(20.0f)) goto L10;
        this.keyboardInset = r73;
        goto L11
    }

    private /* synthetic */ void lambda$openOptions$36() {
        Activity r0 = this.parentActivity;
        if ((r0 instanceof LaunchActivity) == false) goto L5;
        ((LaunchActivity) r0).presentFragment(ChatActivity.of(this.botId));
    L5:
        dismiss(true);
    }

    private /* synthetic */ void lambda$openOptions$37() {
        this.webViewContainer.onSettingsButtonPressed();
    }

    private /* synthetic */ void lambda$openOptions$38() {
        this.swipeContainer.isPinned = !r0.isPinned;
        this.options = null;
    }

    private /* synthetic */ void lambda$openOptions$39() {
        if (this.webViewContainer.getWebView() == null) goto L5;
        this.webViewContainer.getWebView().animate().cancel();
        this.webViewContainer.getWebView().animate().alpha(0.0f).start();
    L5:
        this.progressView.setLoadProgress(0.0f);
        this.progressView.setAlpha(1.0f);
        this.progressView.setVisibility(0);
        this.webViewContainer.setBotUser(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId)));
        this.webViewContainer.loadFlickerAndSettingsItem(this.currentAccount, this.botId, null);
        this.webViewContainer.reload();
    }

    private /* synthetic */ void lambda$openOptions$40() {
        MediaDataController.getInstance(this.currentAccount).installShortcut(this.botId, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT);
    }

    private /* synthetic */ void lambda$openOptions$41() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.BotWebViewToSLink));
    }

    private /* synthetic */ void lambda$openOptions$42() {
        ReportBottomSheet.openChat(this.currentAccount, getContext(), BulletinFactory.of(Bulletin.BulletinWindow.make(getContext()), this.resourcesProvider), this.botId);
    }

    private /* synthetic */ void lambda$openOptions$43() {
        dismiss();
    }

    private /* synthetic */ void lambda$openOptions$44() {
        deleteBot(this.currentAccount, this.botId, new BotWebViewSheet$$ExternalSyntheticLambda53(this));
    }

    private /* synthetic */ void lambda$requestWebView$20(TLRPC.UserFull r2) {
        if (r2 == null) goto L9;
        TL_bots.BotInfo r22 = r2.bot_info;
        if (r22 == null) goto L10;
        TL_bots.botAppSettings r23 = r22.app_settings;
        if (r23 == null) goto L11;
        applyAppBotSettings(r23, true);
        return;
    L11:
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$requestWebView$21(TLRPC.UserFull r2) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda31(this, r2));
    }

    private /* synthetic */ void lambda$requestWebView$22(View r1) {
        openOptions();
    }

    private /* synthetic */ void lambda$requestWebView$23(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L9;
        WebViewRequestProps r12 = this.requestProps;
        if (r12 == null) goto L8;
        r12.applyResponse(r2);
        loadFromResponse();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$requestWebView$24(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda33(this, r3, r2));
    }

    private /* synthetic */ void lambda$requestWebView$25(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L9;
        WebViewRequestProps r12 = this.requestProps;
        if (r12 == null) goto L8;
        r12.applyResponse(r2);
        loadFromResponse();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$requestWebView$26(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda50(this, r3, r2));
    }

    private /* synthetic */ void lambda$requestWebView$27(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L9;
        WebViewRequestProps r12 = this.requestProps;
        if (r12 == null) goto L8;
        r12.applyResponse(r2);
        loadFromResponse();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$requestWebView$28(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda28(this, r3, r2));
    }

    private /* synthetic */ void lambda$requestWebView$29(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L9;
        WebViewRequestProps r12 = this.requestProps;
        if (r12 == null) goto L8;
        r12.applyResponse(r2);
        loadFromResponse();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$requestWebView$30(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda45(this, r3, r2));
    }

    private /* synthetic */ void lambda$requestWebView$31(TLRPC.TL_error r1, TLObject r2) {
        if (r1 != null) goto L9;
        WebViewRequestProps r12 = this.requestProps;
        if (r12 == null) goto L8;
        r12.applyResponse(r2);
        loadFromResponse();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$requestWebView$32(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda49(this, r3, r2));
    }

    private /* synthetic */ void lambda$requestWebView$33(TLRPC.TL_webViewResultUrl r1, TLRPC.TL_error r2) {
        if (r2 != null) goto L9;
        WebViewRequestProps r22 = this.requestProps;
        if (r22 == null) goto L8;
        r22.applyResponse(r1);
        loadFromResponse();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$setActionBarColor$54(int r1, int r2, BotWebViewMenuContainer$ActionBarColorsAnimating r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.actionBarColor = ColorUtils.blendARGB(r1, r2, r42);
        checkNavBarColor();
        this.windowView.invalidate();
        this.actionBar.setBackgroundColor(this.actionBarColor);
        r3.updateActionBar(this.actionBar, r42);
        this.lineColor = r3.getColor(Theme.key_sheet_scrollUp);
        this.windowView.invalidate();
    }

    private /* synthetic */ void lambda$setBackgroundColor$52(int r2, int r3, ValueAnimator r4) {
        this.backgroundPaint.setColor(ColorUtils.blendARGB(r2, r3, ((Float) r4.getAnimatedValue()).floatValue()));
        updateActionBarColors();
        this.windowView.invalidate();
        ArticleViewer.ErrorContainer r22 = this.errorContainer;
        if (r22 != null) goto L5;
    L9:
        updateWebViewBackgroundColor();
        return;
    L5:
        if (AndroidUtilities.computePerceivedBrightness(this.backgroundPaint.getColor()) > 0.721f) goto L7;
        boolean r32 = true;
    L8:
        r22.setDark(r32, false);
        this.errorContainer.setBackgroundColor(this.backgroundPaint.getColor());
        goto L9
    L7:
        r32 = false;
        goto L8
    }

    private /* synthetic */ void lambda$setNavigationBarColor$53(int r1, int r2, ValueAnimator r3) {
        this.navBarColor = ColorUtils.blendARGB(r1, r2, ((Float) r3.getAnimatedValue()).floatValue());
        checkNavBarColor();
    }

    private /* synthetic */ void lambda$setOpen$51(ValueAnimator r1) {
        this.openedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        checkNavBarColor();
    }

    private /* synthetic */ void lambda$showJustAddedBulletin$1(String r2) {
        showBulletin(new BotWebViewSheet$$ExternalSyntheticLambda58(r2));
    }

    private /* synthetic */ void lambda$updateDownloadBulletin$45(BotDownloads.FileDownload r1, View r2) {
        if (r1.isDownloading() == false) goto L5;
        r1.cancel();
    L6:
        ItemOptions r12 = this.options;
        if (r12 == null) goto L10;
        r12.dismiss();
        this.options = null;
        return;
    L10:
        return;
    L5:
        r1.open();
        goto L6
    }

    private void loadFromResponse() {
        if (this.requestProps == null) goto L26;
        long r2 = Math.max(0, 60000 - (System.currentTimeMillis() - this.requestProps.responseTime));
        String r4 = null;
        this.fullsize = null;
        TLObject r5 = this.requestProps.response;
        if ((r5 instanceof TLRPC.TL_webViewResultUrl) == false) goto L11;
        TLRPC.TL_webViewResultUrl r52 = (TLRPC.TL_webViewResultUrl) r5;
        this.queryId = r52.query_id;
        r4 = r52.url;
        boolean r0 = r52.same_origin;
        this.fullsize = Boolean.valueOf(r52.fullsize);
        boolean r1 = this.fromTab;
        if (r1 == true) goto L17;
        setFullscreen(r52.fullscreen, !r1);
    L17:
        if (r4 != null) goto L19;
    L21:
        AndroidUtilities.runOnUIThread(this.pollRunnable, r2);
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r02 = this.swipeContainer;
        if (r02 == null) goto L25;
        r02.setFullSize(isFullSize());
        return;
    L25:
        return;
    L19:
        if (this.fromTab == true) goto L21;
        MediaDataController.getInstance(this.currentAccount).increaseWebappRating(this.requestProps.botId);
        this.webViewContainer.loadUrl(this.currentAccount, r4, r0);
        goto L21
    L11:
        if ((r5 instanceof TLRPC.TL_appWebViewResultUrl) == false) goto L15;
        this.queryId = 0;
        r4 = ((TLRPC.TL_appWebViewResultUrl) r5).url;
    L13:
        r0 = false;
        goto L17
    L15:
        if ((r5 instanceof TLRPC.TL_simpleWebViewResultUrl) == false) goto L13;
        this.queryId = 0;
        r4 = ((TLRPC.TL_simpleWebViewResultUrl) r5).url;
        goto L13
    }

    public static JSONObject makeThemeParams(Theme.ResourcesProvider r1) {
        return makeThemeParams(r1, false);
    }

    public static JSONObject makeThemeParams(Theme.ResourcesProvider r4, boolean r5) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L4
        int r1 = Theme.blendOver(-16777216, Theme.getColor(Theme.key_dialogBackground, r4));     // Catch: Exception -> L4
        BotWebViewSheet$$ExternalSyntheticLambda29 r2 = new BotWebViewSheet$$ExternalSyntheticLambda29(r1, r5);     // Catch: Exception -> L4
        r0.put("bg_color", r2.run(Integer.valueOf(r1)));     // Catch: Exception -> L4
        r0.put("section_bg_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhite, r4))));     // Catch: Exception -> L4
        int r12 = Theme.key_windowBackgroundGray;     // Catch: Exception -> L4
        r0.put("secondary_bg_color", r2.run(Integer.valueOf(Theme.getColor(r12, r4))));     // Catch: Exception -> L4
        r0.put("text_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r4))));     // Catch: Exception -> L4
        r0.put("hint_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r4))));     // Catch: Exception -> L4
        r0.put("link_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r4))));     // Catch: Exception -> L4
        r0.put("button_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_featuredStickers_addButton, r4))));     // Catch: Exception -> L4
        r0.put("button_text_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_featuredStickers_buttonText, r4))));     // Catch: Exception -> L4
        r0.put("header_bg_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_actionBarDefault, r4))));     // Catch: Exception -> L4
        r0.put("accent_text_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4, r4))));     // Catch: Exception -> L4
        r0.put("section_header_text_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r4))));     // Catch: Exception -> L4
        r0.put("subtitle_text_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r4))));     // Catch: Exception -> L4
        r0.put("destructive_text_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_text_RedRegular, r4))));     // Catch: Exception -> L4
        r0.put("section_separator_color", r2.run(Integer.valueOf(Theme.getColor(Theme.key_divider, r4))));     // Catch: Exception -> L4
        r0.put("bottom_bar_bg_color", r2.run(Integer.valueOf(Theme.getColor(r12, r4))));     // Catch: Exception -> L4
        return r0;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static int navigationBarColor(int r2) {
        return Theme.adaptHSV(r2, 0.35f, -0.1f);
    }

    private void openOptions() {
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        ArrayList<TLRPC.TL_attachMenuBot> r1 = MediaDataController.getInstance(this.currentAccount).getAttachMenuBots().bots;
        int r2 = r1.size();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L7;
        TLRPC.TL_attachMenuBot r5 = r1.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_attachMenuBot r52 = r5;
        if (r52.bot_id != this.botId) goto L3;
    L8:
        ItemOptions r12 = this.options;
        if (r12 == null) goto L11;
        r12.dismiss();
    L11:
        WindowView r13 = this.windowView;
        Theme.ResourcesProvider r22 = this.resourcesProvider;
        if (this.fullscreen == false) goto L14;
        View r42 = this.fullscreenButtons;
    L15:
        ItemOptions r14 = ItemOptions.makeOptions(r13, r22, r42, true);
        this.options = r14;
        BotDownloads r23 = BotDownloads.get(getContext(), this.currentAccount, this.botId);
        this.fileItems.clear();
        if (r23.hasFiles() == false) goto L22;
        ItemOptions r43 = r14.makeSwipeback();
        r43.add(R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r14));
        r43.addGap();
        ArrayList<BotDownloads.FileDownload> r24 = r23.getFiles();
        int r7 = r24.size();
        int r8 = 0;
    L18:
        if (r8 >= r7) goto L20;
        BotDownloads.FileDownload r9 = r24.get(r8);
        r8 = r8 + 1;
        BotDownloads.FileDownload r92 = r9;
        this.fileItems.put(r92, r43.add(r92.file_name, _UrlKt.FRAGMENT_ENCODE_SET, new BotWebViewSheet$$ExternalSyntheticLambda35()).getLast());
        goto L18
    L20:
        updateDownloadBulletin();
        r43.setMinWidth(AndroidUtilities.dp(180.0f));
        r14.add(R.drawable.menu_download_round, LocaleController.getString(R.string.BotDownloads), new BotWebViewSheet$$ExternalSyntheticLambda36(r14, r43));
        r14.addGap();
    L22:
        if (this.onVerifiedAge != null) goto L24;
        boolean r25 = true;
    L25:
        ItemOptions r26 = r14.addIf(r25, R.drawable.msg_bot, LocaleController.getString(R.string.BotWebViewOpenBot), new BotWebViewSheet$$ExternalSyntheticLambda37(this));
        if (this.onVerifiedAge == null) goto L28;
    L30:
        boolean r44 = false;
    L31:
        ItemOptions r27 = r26.addIf(r44, R.drawable.msg_settings, LocaleController.getString(R.string.BotWebViewSettings), new BotWebViewSheet$$ExternalSyntheticLambda38(this));
        boolean r45 = this.swipeContainer.isPinned;
        if (r45 == false) goto L34;
        int r72 = R.drawable.msg_unpin;
    L35:
        if (r45 == false) goto L37;
        int r46 = R.string.UnpinMessage;
    L38:
        ItemOptions r28 = r27.add(r72, LocaleController.getString(r46), new BotWebViewSheet$$ExternalSyntheticLambda39(this)).add(R.drawable.msg_retry, LocaleController.getString(R.string.BotWebViewReloadPage), new BotWebViewSheet$$ExternalSyntheticLambda40(this));
        if (this.onVerifiedAge != null) goto L44;
        if (r0 == null) goto L44;
        if (r0.bot_has_main_app == false) goto L44;
        boolean r02 = true;
    L45:
        ItemOptions r03 = r28.addIf(r02, R.drawable.msg_home, LocaleController.getString(R.string.AddShortcut), new BotWebViewSheet$$ExternalSyntheticLambda41(this));
        if (this.onVerifiedAge != null) goto L48;
        boolean r29 = true;
    L49:
        ItemOptions r04 = r03.addIf(r29, R.drawable.menu_intro, LocaleController.getString(R.string.BotWebViewToS), new BotWebViewSheet$$ExternalSyntheticLambda42(this));
        if (this.onVerifiedAge != null) goto L52;
        boolean r210 = true;
    L53:
        ItemOptions r05 = r04.addIf(r210, R.drawable.msg_report, LocaleController.getString(R.string.BotWebViewReportBot), new BotWebViewSheet$$ExternalSyntheticLambda43(this));
        if (this.onVerifiedAge != null) goto L61;
        if (r52 == null) goto L61;
        if (r52.show_in_side_menu == false) goto L59;
    L60:
        boolean r211 = true;
    L62:
        r05.addIf(r211, R.drawable.msg_delete, LocaleController.getString(R.string.BotWebViewDeleteBot), new BotWebViewSheet$$ExternalSyntheticLambda34(this));
        if (this.actionBarColor == Theme.getColor(Theme.key_windowBackgroundWhite)) goto L78;
        int r47 = -1;
        if (AndroidUtilities.computePerceivedBrightness(this.actionBarColor) < 0.721f) goto L67;
        int r06 = -1;
    L69:
        if (AndroidUtilities.computePerceivedBrightness(r06) < 0.721f) goto L71;
        r47 = -16777216;
    L71:
        int r212 = Theme.multAlpha(r47, 0.85f);
        int r53 = Theme.multAlpha(r47, 0.1f);
        r14.setBackgroundColor(r06);
        int r07 = 0;
    L73:
        if (r07 >= r14.getItemsCount()) goto L78;
        View r73 = r14.getItemAt(r07);
        if ((r73 instanceof ActionBarMenuSubItem) == false) goto L77;
        ActionBarMenuSubItem r74 = (ActionBarMenuSubItem) r73;
        r74.setColors(r47, r212);
        r74.setSelectorColor(r53);
    L77:
        r07 = r07 + 1;
        goto L73
    L67:
        r06 = -15198183;
    L78:
        r14.setGravity(5).translate(-this.insets.right, 0.0f).forceTop(true).setDrawScrim(false).setDimAlpha(0).show();
        return;
    L59:
        if (r52.show_in_attach_menu == true) goto L60;
    L61:
        r211 = false;
        goto L62
    L52:
        r210 = false;
        goto L53
    L48:
        r29 = false;
    L44:
        r02 = false;
        goto L45
    L37:
        r46 = R.string.PinMessage;
        goto L38
    L34:
        r72 = R.drawable.msg_pin;
        goto L35
    L28:
        if (this.hasSettings == false) goto L30;
        r44 = true;
        goto L31
    L24:
        r25 = false;
        goto L25
    L14:
        r42 = this.optionsItem;
        goto L15
    L7:
        r52 = null;
        goto L8
    }

    private void preloadShortcutBotIcon(TLRPC.User r4, TLRPC.TL_attachMenuBot r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.show_in_side_menu == true) goto L6;
        return;
    L6:
        if (MediaDataController.getInstance(this.currentAccount).isShortcutAdded(this.botId, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT) == true) goto L18;
        if (r4 != null) goto L9;
        r4 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
    L9:
        if (r4 != null) goto L11;
        return;
    L11:
        if (r4.photo != null) goto L13;
        return;
    L13:
        if (FileLoader.getInstance(this.currentAccount).getPathToAttach(r4.photo.photo_small, true).exists() == true) goto L21;
        MediaDataController.getInstance(this.currentAccount).preloadImage(ImageLocation.getForUser(this.currentAccount, r4, 1), 0);
        return;
    L21:
        return;
    }

    private void relayout() {
        updateFullscreenLayout();
    }

    private void showBulletin(Utilities.CallbackReturn<BulletinFactory, Bulletin> r2) {
        r2.run(BulletinFactory.of(this.bulletinContainer, this.resourcesProvider)).show(true);
    }

    private void updateActionBarColors() {
        if (this.overrideActionBarColor == true) goto L5;
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTitleColor(getColor(r1));
        this.actionBar.setItemsColor(getColor(r1), false);
        this.actionBar.setItemsBackgroundColor(getColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setPopupBackgroundColor(getColor(Theme.key_actionBarDefaultSubmenuBackground), false);
        this.actionBar.setPopupItemsColor(getColor(Theme.key_actionBarDefaultSubmenuItem), false, false);
        this.actionBar.setPopupItemsColor(getColor(Theme.key_actionBarDefaultSubmenuItemIcon), true, false);
        this.actionBar.setPopupItemsSelectorColor(getColor(Theme.key_dialogButtonSelector), false);
    L5:
        this.webViewContainer.setFlickerViewColor(this.backgroundPaint.getColor());
    }

    private void updateDownloadBulletin() {
        BotDownloads r0 = BotDownloads.get(getContext(), this.currentAccount, this.botId);
        BotDownloads.FileDownload r1 = r0.getCurrent();
        if (r1 != null) goto L8;
        Bulletin r12 = this.downloadBulletin;
        if (r12 == null) goto L32;
        r12.hide();
        this.downloadBulletin = null;
    L32:
        updateDownloadBulletinArrow();
        Iterator<Map.Entry<BotDownloads.FileDownload, ActionBarMenuSubItem>> r13 = this.fileItems.entrySet().iterator();
    L34:
        if (r13.hasNext() == false) goto L50;
        Map.Entry<BotDownloads.FileDownload, ActionBarMenuSubItem> r2 = r13.next();
        ActionBarMenuSubItem r4 = r2.getValue();
        BotDownloads.FileDownload r22 = r2.getKey();
        r4.setText(r22.file_name);
        if (r22.isDownloading() == true) goto L38;
        r4.setSubtext(AndroidUtilities.formatFileSize(r22.size));
    L43:
        if (r22.isDownloading() == false) goto L46;
        r4.setRightIcon(R.drawable.msg_close);
        r4.subtextView.setPadding(0, 0, AndroidUtilities.dp(32.0f), 0);
    L49:
        r4.setOnClickListener(new BotWebViewSheet$$ExternalSyntheticLambda12(this, r22));
        goto L34
    L46:
        if (r22.cancelled == false) goto L48;
        r4.setVisibility(8);
        goto L49
    L48:
        r4.setRightIcon(0);
        r4.subtextView.setPadding(0, 0, 0, 0);
        goto L49
    L38:
        Pair<Long, Long> r5 = r22.getProgress();
        long r6 = ((Long) r5.second).longValue();
        Object r7 = r5.first;
        if (r6 <= 0) goto L41;
        r4.setSubtext(AndroidUtilities.formatFileSize(((Long) r7).longValue()) + " / " + AndroidUtilities.formatFileSize(((Long) r5.second).longValue()));
        goto L43
    L41:
        r4.setSubtext(AndroidUtilities.formatFileSize(((Long) r7).longValue()));
        goto L43
    L50:
        this.optionsIcon.setDownloading(r0.isDownloading());
        this.fullscreenButtons.setDownloading(r0.isDownloading());
        return;
    L8:
        if (r1.isDownloading() == false) goto L12;
        if (r1.shown == true) goto L12;
    L14:
        if (this.lastBulletinFile == r1) goto L18;
        Bulletin r42 = this.downloadBulletin;
        if (r42 == null) goto L18;
        r42.hide();
        this.downloadBulletin = null;
    L18:
        Bulletin r43 = this.downloadBulletin;
        if (r43 != null) goto L21;
    L22:
        this.lastBulletinFile = r1;
        FrameLayout r44 = this.bulletinContainer;
        BotDownloads.DownloadBulletin r62 = new BotDownloads.DownloadBulletin(getContext(), this.resourcesProvider);
        this.downloadBulletinLayout = r62;
        Bulletin r45 = Bulletin.make(r44, r62, 5000);
        this.downloadBulletin = r45;
        r45.show(true);
    L24:
        if (this.downloadBulletinLayout.set(r1) == false) goto L26;
        this.downloadBulletin = null;
    L26:
        r1.resaved = false;
        r1.shown = true;
        goto L32
    L21:
        if (r43.isShowing() == true) goto L24;
    L12:
        if (r1.resaved == true) goto L14;
        BotDownloads.DownloadBulletin r46 = this.downloadBulletinLayout;
        if (r46 == null) goto L32;
        this.lastBulletinFile = r1;
        if (r46.set(r1) == false) goto L32;
        this.downloadBulletin = null;
        goto L32
    }

    private void updateDownloadBulletinArrow() {
        BotDownloads.DownloadBulletin r0 = this.downloadBulletinLayout;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.fullscreen == false) goto L10;
        r0.setArrow(AndroidUtilities.lerp(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(26.0f), this.fullscreenProgress));
        return;
    L10:
        if (this.actionBarTransitionProgress <= 0.5f) goto L13;
        r0.setArrow(AndroidUtilities.dp(24.0f));
        return;
    L13:
        r0.setArrow(-1);
    }

    private void updateLightStatusBar() {
        boolean r1 = true;
        if (this.overrideActionBarColor == false) goto L5;
        boolean r0 = !this.actionBarIsLight;
    L14:
        Boolean r12 = this.wasLightStatusBar;
        if (r12 != null) goto L17;
    L19:
        this.wasLightStatusBar = Boolean.valueOf(r0);
        int r13 = this.windowView.getSystemUiVisibility();
        if (r0 == false) goto L22;
        int r02 = r13 | 8192;
    L23:
        this.windowView.setSystemUiVisibility(r02);
        return;
    L22:
        r02 = r13 & (-8193);
        goto L23
    L17:
        if (r12.booleanValue() != r0) goto L19;
        return;
    L5:
        int r03 = Theme.getColor(Theme.key_windowBackgroundWhite, null, true);
        if (AndroidUtilities.isTablet() == false) goto L8;
    L12:
        r1 = false;
    L13:
        r0 = r1;
        goto L14
    L8:
        if (ColorUtils.calculateLuminance(r03) < 0.7210000157356262d) goto L12;
        if (this.actionBarTransitionProgress < 0.85f) goto L12;
        goto L12
    }

    private void updateWebViewBackgroundColor() {
        BotWebViewContainer r0 = this.webViewContainer;
        if (r0 == null) goto L10;
        BotWebViewContainer.MyWebView r02 = r0.getWebView();
        if (r02 != null) goto L8;
        return;
    L8:
        r02.setBackgroundColor(this.backgroundPaint.getColor());
        return;
    }

    public void checkNavBarColor() {
        if (this.superDismissed == true) goto L7;
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L7;
        r0.checkSystemBarColors(true, true, true);
    L7:
        WindowView r2 = this.windowView;
        if (r2 == null) goto L11;
        r2.invalidate();
        return;
    }

    public ArticleViewer.ErrorContainer createErrorContainer() {
        if (this.errorContainer != null) goto L6;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r0 = this.swipeContainer;
        ArticleViewer.ErrorContainer r1 = new ArticleViewer.ErrorContainer(getContext());
        this.errorContainer = r1;
        r0.addView(r1, LayoutHelper.createFrame(-1, -1.0f));
        this.errorContainer.setTranslationY(-1.0f);
        this.errorContainer.getButtonView().setOnClickListener(new BotWebViewSheet$$ExternalSyntheticLambda44(this));
        this.errorContainer.setBackgroundColor(this.backgroundPaint.getColor());
        AndroidUtilities.updateViewVisibilityAnimated(this.errorContainer, this.errorShown, 1.0f, false);
    L6:
        return this.errorContainer;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.webViewResultSent) goto L9;
        long r32 = ((Long) r5[0]).longValue();
        if (this.queryId != r32) goto L16;
        dismiss();
        return;
    L16:
        return;
    L9:
        if (r3 != NotificationCenter.didSetNewTheme) goto L13;
        this.windowView.invalidate();
        this.webViewContainer.updateFlickerBackgroundColor(getColor(Theme.key_windowBackgroundWhite));
        updateActionBarColors();
        updateLightStatusBar();
        return;
    L13:
        if (r3 != NotificationCenter.botDownloadsUpdate) goto L17;
        updateDownloadBulletin();
        return;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        dismiss(null);
    }

    public void dismiss(Runnable r2) {
        dismiss(false, r2);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    public void dismiss(boolean r2) {
        dismiss(r2, null);
    }

    public void dismiss(boolean r6, Runnable r7) {
        if (this.dismissed == true) goto L37;
        int r1 = 0;
        if (this.onVerifiedAge == null) goto L8;
        r6 = false;
    L8:
        this.dismissed = true;
        setOpen(false);
        AndroidUtilities.cancelRunOnUIThread(this.pollRunnable);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.webViewResultSent);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.botDownloadsUpdate);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewTheme);
        if (r6 == false) goto L16;
        LaunchActivity r2 = LaunchActivity.instance;
        if (r2 != null) goto L13;
    L14:
        r6 = false;
        goto L16
    L13:
        if (r2.getBottomSheetTabsOverlay() == null) goto L14;
    L16:
        if (r6 == false) goto L21;
        SpringAnimation r62 = this.springAnimation;
        if (r62 == null) goto L20;
        r62.getSpring().setFinalPosition(0.0f);
        this.springAnimation.start();
    L20:
        LaunchActivity.instance.getBottomSheetTabsOverlay().dismissSheet(this);
    L32:
        activeSheets.remove(this);
        BottomSheetTabs r63 = this.bottomTabs;
        if (r63 == null) goto L36;
        r63.stopListening(this.invalidateRunnable, this.relayoutRunnable);
        return;
    L36:
        return;
    L21:
        BotButtons r64 = this.botButtons;
        if (r64 == null) goto L24;
        r64.animate().translationY(this.botButtons.getTotalHeight()).alpha(0.0f).setDuration(160).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    L24:
        this.webViewContainer.destroyWebView();
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r65 = this.swipeContainer;
        int r22 = r65.getHeight();
        BotButtons r3 = this.botButtons;
        if (r3 == null) goto L27;
        int r32 = r3.getTotalHeight();
    L28:
        int r23 = r22 + r32;
        Rect r33 = this.insets;
        int r24 = ((r23 + r33.top) + r33.bottom) + this.windowView.measureKeyboardHeight();
        if (isFullSize() == false) goto L31;
        r1 = AndroidUtilities.dp(200.0f);
    L31:
        r65.stickTo(r24 + r1, true, new BotWebViewSheet$$ExternalSyntheticLambda13(this, r7));
        goto L32
    L27:
        r32 = 0;
        goto L28
    }

    public Activity getActivity() {
        Activity r0 = getOwnerActivity();
        if (r0 != null) goto L5;
        r0 = LaunchActivity.instance;
    L5:
        if (r0 == null) goto L7;
        return r0;
    L7:
        return AndroidUtilities.findActivity(getContext());
    }

    public long getBotId() {
        return this.botId;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    public int getNavigationBarColor(int r2) {
        return ColorUtils.blendARGB(r2, this.navBarColor, this.openedProgress);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    /* renamed from: getWindowView */
    public /* bridge */ /* synthetic */ BottomSheetTabsOverlay.SheetView mo6194getWindowView() {
        return mo6194getWindowView();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    /* renamed from: getWindowView */
    public WindowView mo6194getWindowView() {
        return this.windowView;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    public boolean hadDialog() {
        return false;
    }

    public boolean isFullSize() {
        if (this.fullscreen == true) goto L14;
        Boolean r0 = this.fullsize;
        if (r0 != null) goto L10;
        if (this.defaultFullsize == true) goto L17;
        return false;
    L17:
        return true;
    L10:
        if (r0.booleanValue() == true) goto L18;
        return false;
    L18:
        return true;
    L14:
        return true;
    }

    public boolean isGuardBotTab(long r4, long r6) {
        WebViewRequestProps r3 = this.requestProps;
        if (r3 != null) goto L5;
    L16:
        return false;
    L5:
        if (r3.type != 5) goto L16;
        long r1 = r3.peerId;
        if (r1 != r4) goto L9;
    L10:
        TLObject r32 = r3.response;
        if ((r32 instanceof TLRPC.TL_webViewResultUrl) == false) goto L16;
        if (((TLRPC.TL_webViewResultUrl) r32).query_id != r6) goto L16;
        return true;
    L9:
        if (r1 != 0) goto L16;
        goto L10
    }

    public void lockOrientation(boolean r2) {
        if (this.orientationLocked != r2) goto L5;
        return;
    L5:
        this.orientationLocked = r2;
        if (this.attached == false) goto L11;
        if (r2 == false) goto L9;
        shownLockedBots++;
        goto L11
    L9:
        shownLockedBots--;
    L11:
        if (shownLockedBots <= 0) goto L14;
        AndroidUtilities.lockOrientation(getActivity());
        return;
    L14:
        AndroidUtilities.unlockOrientation(getActivity());
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAttached(true);
        if (this.springAnimation != null) goto L6;
        this.springAnimation = new SpringAnimation(this, ACTION_BAR_TRANSITION_PROGRESS_VALUE).setSpring(new SpringForce().setStiffness(1200.0f).setDampingRatio(1.0f));
        return;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (this.passcodeView.getVisibility() != 0) goto L9;
        if (getOwnerActivity() == null) goto L10;
        getOwnerActivity().finish();
        return;
    L10:
        return;
    L9:
        if (this.webViewContainer.onBackPressed() == true) goto L13;
        dismiss(true, null);
        return;
    }

    public boolean onCheckDismissByUser() {
        if (this.needCloseConfirmation == false) goto L10;
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        if (r0 == null) goto L7;
        String r02 = ContactsController.formatName(r0.first_name, r0.last_name);
    L8:
        AlertDialog r03 = new AlertDialog.Builder(getContext()).setTitle(r02).setMessage(LocaleController.getString(R.string.BotWebViewChangesMayNotBeSaved)).setPositiveButton(LocaleController.getString(R.string.BotWebViewCloseAnyway), new BotWebViewSheet$$ExternalSyntheticLambda48(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r03.show();
        ((TextView) r03.getButton(-1)).setTextColor(getColor(Theme.key_text_RedBold));
        return false;
    L7:
        r02 = null;
        goto L8
    L10:
        dismiss();
        return true;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r9) {
        super.onCreate(r9);
        Window r92 = getWindow();
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L5;
        r92.addFlags(-2147483392);
    L6:
        r92.setWindowAnimations(R.style.DialogNoAnimation);
        WindowManager.LayoutParams r1 = r92.getAttributes();
        r1.width = -1;
        r1.gravity = 51;
        r1.dimAmount = 0.0f;
        int r3 = r1.flags;
        int r4 = r3 & (-3);
        r1.flags = r4;
        r1.softInputMode = 16;
        r1.height = -1;
        boolean r5 = true;
        if (r0 < 28) goto L10;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r1, 1);
    L10:
        if (this.fullscreen == false) goto L12;
        r1.flags = r4 | 512;
    L13:
        r92.setAttributes(r1);
        r92.setStatusBarColor(0);
        this.windowView.setFitsSystemWindows(true);
        this.windowView.setSystemUiVisibility(1792);
        this.windowView.setOnApplyWindowInsetsListener(new BotWebViewSheet$$ExternalSyntheticLambda2(this));
        if (this.fullscreen == false) goto L20;
        BotButtons r93 = this.botButtons;
        if (r93 != null) goto L18;
    L19:
        WindowView r94 = this.windowView;
        r94.setSystemUiVisibility(r94.getSystemUiVisibility() | 2);
    L22:
        if (r0 >= 26) goto L24;
    L28:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewTheme);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.botDownloadsUpdate);
        return;
    L24:
        if (ColorUtils.calculateLuminance(this.navBarColor) >= 0.7210000157356262d) goto L27;
        r5 = false;
    L27:
        AndroidUtilities.setLightNavigationBar(this, r5);
        goto L28
    L18:
        if (r93.getTotalHeight() <= 0) goto L19;
    L20:
        WindowView r95 = this.windowView;
        r95.setSystemUiVisibility(r95.getSystemUiVisibility() & (-3));
        goto L22
    L12:
        r1.flags = r3 & (-515);
        goto L13
    L5:
        r92.addFlags(-2147417856);
        goto L6
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setAttached(false);
        SpringAnimation r0 = this.springAnimation;
        if (r0 == null) goto L6;
        r0.cancel();
        this.springAnimation = null;
        return;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        Context r0 = getContext();
        if ((r0 instanceof ContextWrapper) == false) goto L8;
        if ((r0 instanceof LaunchActivity) == true) goto L8;
        r0 = ((ContextWrapper) r0).getBaseContext();
    L8:
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        ((LaunchActivity) r0).addOverlayPasscodeView(this.passcodeView);
        return;
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        Context r0 = getContext();
        if ((r0 instanceof ContextWrapper) == false) goto L8;
        if ((r0 instanceof LaunchActivity) == true) goto L8;
        r0 = ((ContextWrapper) r0).getBaseContext();
    L8:
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        ((LaunchActivity) r0).removeOverlayPasscodeView(this.passcodeView);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    public void release() {
        if (this.superDismissed == false) goto L11;
        return;
    L11:
        super.dismiss();     // Catch: Exception -> L7
    L9:
        setOpen(false);
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    public void requestWebView(BaseFragment r18, WebViewRequestProps r19) {
        this.requestProps = r19;
        int r3 = r19.currentAccount;
        this.currentAccount = r3;
        this.peerId = r19.peerId;
        this.botId = r19.botId;
        this.replyToMsgId = r19.replyToMsgId;
        this.monoforumTopicId = r19.monoforumTopicId;
        this.silent = r19.silent;
        this.buttonText = r19.buttonText;
        this.currentWebApp = r19.app;
        TLRPC.User r32 = MessagesController.getInstance(r3).getUser(Long.valueOf(this.botId));
        CharSequence r4 = UserObject.getUserName(r32);
        boolean r5 = false;
        TextPaint r6 = new TextPaint();     // Catch: Exception -> L124
        r6.setTextSize(AndroidUtilities.dp(20.0f));     // Catch: Exception -> L124
        r4 = Emoji.replaceEmoji(r4, r6.getFontMetricsInt(), false);     // Catch: Exception -> L124
    L4:
        this.actionBar.setTitle(r4);
        TLRPC.UserFull r42 = MessagesController.getInstance(this.currentAccount).getUserFull(this.botId);
        if (r32 != null) goto L7;
    L8:
        if (r42 == null) goto L14;
        TLRPC.User r62 = r42.user;
        if (r62 == null) goto L14;
        if (r62.verified == false) goto L14;
    L13:
        Drawable r63 = getContext().getResources().getDrawable(R.drawable.verified_profile).mutate();
        this.verifiedDrawable = r63;
        r63.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        this.verifiedDrawable.setAlpha(255);
        this.actionBar.getTitleTextView().setDrawablePadding(AndroidUtilities.dp(2.0f));
        this.actionBar.getTitleTextView().setRightDrawable(new AnonymousClass9(this));
    L14:
        BotFullscreenButtons r64 = this.fullscreenButtons;
        if (r64 == null) goto L23;
        String r8 = UserObject.getUserName(r32);
        if (r32 != null) goto L19;
    L21:
        boolean r9 = false;
    L22:
        r64.setName(r8, r9);
        goto L23
    L19:
        if (r32.verified == false) goto L21;
        r9 = true;
    L23:
        ActionBarMenu r65 = this.actionBar.createMenu();
        r65.removeAllViews();
        ArrayList<TLRPC.TL_attachMenuBot> r82 = MediaDataController.getInstance(this.currentAccount).getAttachMenuBots().bots;
        int r92 = r82.size();
        int r10 = 0;
    L24:
        if (r10 >= r92) goto L28;
        TLRPC.TL_attachMenuBot r12 = r82.get(r10);
        r10 = r10 + 1;
        TLRPC.TL_attachMenuBot r122 = r12;
        if (r122.bot_id != this.botId) goto L24;
        TLRPC.TL_attachMenuBot r123 = r122;
    L30:
        if (this.fromTab == true) goto L42;
        if (r42 == null) goto L37;
        TL_bots.BotInfo r33 = r42.bot_info;
        if (r33 == null) goto L39;
        TL_bots.botAppSettings r34 = r33.app_settings;
        if (r34 == null) goto L39;
        applyAppBotSettings(r34, false);
    L39:
        if (r19.fullscreen == false) goto L42;
        setFullscreen(true, false);
        goto L42
    L37:
        MessagesController.getInstance(this.currentAccount).loadFullUser(r32, 0, true, new BotWebViewSheet$$ExternalSyntheticLambda3(this));
    L42:
        if (this.onVerifiedAge != null) goto L44;
        r65.addItem(R.id.menu_collapse_bot, R.drawable.arrow_more);
    L44:
        BotFullscreenButtons.OptionsIcon r35 = new BotFullscreenButtons.OptionsIcon(getContext());
        this.optionsIcon = r35;
        ActionBarMenuItem r36 = r65.addItem(0, r35);
        this.optionsItem = r36;
        r36.setOnClickListener(new BotWebViewSheet$$ExternalSyntheticLambda4(this));
        this.pinItem = this.optionsItem.addSubItem(R.id.menu_pin, R.drawable.msg_pin, LocaleController.getString(R.string.PinMessage));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass10(this));
        JSONObject r37 = makeThemeParams(this.resourcesProvider);
        this.webViewContainer.setBotUser(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId)));
        this.webViewContainer.loadFlickerAndSettingsItem(this.currentAccount, this.botId, null);
        preloadShortcutBotIcon(r19.botUser, r123);
        if (r19.response == null) goto L47;
        loadFromResponse();
        return;
    L47:
        int r43 = r19.type;
        if (r43 == 0) goto L108;
        if (r43 == 1) goto L91;
        if (r43 != 2) goto L52;
        TLRPC.TL_messages_requestWebView r1 = new TLRPC.TL_messages_requestWebView();
        r1.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botId);
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.botId);
        r1.platform = "android";
        r1.compact = r19.compact;
        r1.fullscreen = r19.fullscreen;
        r1.url = r19.buttonUrl;
        r1.flags |= 2;
        if (r37 == null) goto L90;
        TLRPC.TL_dataJSON r2 = new TLRPC.TL_dataJSON();
        r1.theme_params = r2;
        r2.data = r37.toString();
        r1.flags |= 4;
    L90:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new BotWebViewSheet$$ExternalSyntheticLambda5(this));
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.webViewResultSent);
        return;
    L52:
        if (r43 == 3) goto L74;
        if (r43 != 4) goto L55;
        TLRPC.TL_messages_requestMainWebView r44 = new TLRPC.TL_messages_requestMainWebView();
        r44.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r19.botId);
        r44.platform = "android";
        if ((r18 instanceof ChatActivity) == false) goto L67;
        ChatActivity r13 = (ChatActivity) r18;
        if (r13.getCurrentUser() == null) goto L66;
        TLRPC.InputPeer r14 = MessagesController.getInputPeer(r13.getCurrentUser());
    L68:
        r44.peer = r14;
        r44.compact = r19.compact;
        r44.fullscreen = r19.fullscreen;
        if (TextUtils.isEmpty(r19.startParam) == true) goto L71;
        r44.start_param = r19.startParam;
        r44.flags |= 2;
    L71:
        if (r37 == null) goto L73;
        TLRPC.TL_dataJSON r15 = new TLRPC.TL_dataJSON();
        r44.theme_params = r15;
        r15.data = r37.toString();
        r44.flags |= 1;
    L73:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r44, new BotWebViewSheet$$ExternalSyntheticLambda9(this), 66);
        return;
    L66:
        r14 = MessagesController.getInputPeer(r13.getCurrentChat());
        goto L68
    L67:
        r14 = MessagesController.getInstance(this.currentAccount).getInputPeer(r19.peerId);
        goto L68
    L55:
        if (r43 != 5) goto L136;
        TLRPC.TL_messages_requestChatJoinWebView r16 = new TLRPC.TL_messages_requestChatJoinWebView();
        r16.platform = "android";
        r16.query_id = r19.queryId;
        if (r37 == null) goto L60;
        TLRPC.TL_dataJSON r22 = new TLRPC.TL_dataJSON();
        r16.theme_params = r22;
        r22.data = r37.toString();
    L60:
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r16, new AiTonesController$$ExternalSyntheticLambda0(), new BotWebViewSheet$$ExternalSyntheticLambda10(this), 66);
        return;
    L136:
        return;
    L74:
        TLRPC.TL_messages_requestAppWebView r45 = new TLRPC.TL_messages_requestAppWebView();
        TLRPC.TL_inputBotAppID r52 = new TLRPC.TL_inputBotAppID();
        TLRPC.BotApp r7 = r19.app;
        r52.id = r7.id;
        r52.access_hash = r7.access_hash;
        r45.app = r52;
        r45.write_allowed = r19.allowWrite;
        r45.platform = "android";
        if ((r18 instanceof ChatActivity) == false) goto L80;
        ChatActivity r17 = (ChatActivity) r18;
        if (r17.getCurrentUser() == null) goto L79;
        TLRPC.InputPeer r110 = MessagesController.getInputPeer(r17.getCurrentUser());
    L81:
        r45.peer = r110;
        r45.compact = r19.compact;
        r45.fullscreen = r19.fullscreen;
        if (TextUtils.isEmpty(r19.startParam) == true) goto L84;
        r45.start_param = r19.startParam;
        r45.flags |= 2;
    L84:
        if (r37 == null) goto L86;
        TLRPC.TL_dataJSON r111 = new TLRPC.TL_dataJSON();
        r45.theme_params = r111;
        r111.data = r37.toString();
        r45.flags |= 4;
    L86:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r45, new BotWebViewSheet$$ExternalSyntheticLambda8(this), 66);
        return;
    L79:
        r110 = MessagesController.getInputPeer(r17.getCurrentChat());
        goto L81
    L80:
        r110 = MessagesController.getInputPeer(r19.botUser);
        goto L81
    L91:
        TLRPC.TL_messages_requestSimpleWebView r112 = new TLRPC.TL_messages_requestSimpleWebView();
        if ((r19.flags & 1) == 0) goto L94;
        boolean r46 = true;
    L95:
        r112.from_switch_webview = r46;
        r112.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botId);
        r112.platform = "android";
        if ((r19.flags & 2) == 0) goto L98;
        r5 = true;
    L98:
        r112.from_side_menu = r5;
        r112.compact = r19.compact;
        r112.fullscreen = r19.fullscreen;
        if (r37 == null) goto L102;
        TLRPC.TL_dataJSON r47 = new TLRPC.TL_dataJSON();
        r112.theme_params = r47;
        r47.data = r37.toString();
        r112.flags |= 1;
    L102:
        if (TextUtils.isEmpty(r19.buttonUrl) == true) goto L105;
        r112.flags |= 8;
        r112.url = r19.buttonUrl;
    L105:
        if (TextUtils.isEmpty(r19.startParam) == true) goto L107;
        r112.start_param = r19.startParam;
        r112.flags |= 16;
    L107:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r112, new BotWebViewSheet$$ExternalSyntheticLambda6(this));
        return;
    L94:
        r46 = false;
        goto L95
    L108:
        TLRPC.TL_messages_requestWebView r113 = new TLRPC.TL_messages_requestWebView();
        r113.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.peerId);
        r113.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botId);
        r113.platform = "android";
        r113.compact = r19.compact;
        r113.fullscreen = r19.fullscreen;
        String r23 = r19.buttonUrl;
        if (r23 == null) goto L112;
        r113.url = r23;
        r113.flags |= 2;
    L112:
        if (this.replyToMsgId == 0) goto L118;
        TLRPC.InputReplyTo r24 = SendMessagesHelper.getInstance(this.currentAccount).createReplyInput(this.replyToMsgId);
        r113.reply_to = r24;
        if (this.monoforumTopicId == 0) goto L116;
        r24.monoforum_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(this.monoforumTopicId);
        r113.reply_to.flags |= 32;
    L116:
        r113.flags |= 1;
    L120:
        if (r37 == null) goto L122;
        TLRPC.TL_dataJSON r25 = new TLRPC.TL_dataJSON();
        r113.theme_params = r25;
        r25.data = r37.toString();
        r113.flags |= 4;
    L122:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r113, new BotWebViewSheet$$ExternalSyntheticLambda7(this));
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.webViewResultSent);
        return;
    L118:
        if (this.monoforumTopicId == 0) goto L120;
        TLRPC.TL_inputReplyToMonoForum r26 = new TLRPC.TL_inputReplyToMonoForum();
        r113.reply_to = r26;
        r26.monoforum_peer_id = MessagesController.getInstance(this.currentAccount).getInputPeer(this.monoforumTopicId);
        r113.flags |= 1;
        goto L120
    L28:
        r123 = null;
        goto L30
    L7:
        if (r32.verified == true) goto L13;
        goto L8
    }

    public boolean restoreState(BaseFragment r11, BottomSheetTabs.WebTabData r12) {
        if (r12 != null) goto L5;
    L56:
        return false;
    L5:
        if (r12.props == null) goto L56;
        this.fromTab = true;
        boolean r2 = r12.overrideBackgroundColor;
        this.overrideBackgroundColor = r2;
        if (r2 == false) goto L11;
        setBackgroundColor(r12.backgroundColor, true, false);
    L11:
        if (r12.overrideActionBarColor == true) goto L16;
        int r22 = r12.actionBarColorKey;
        if (r22 >= 0) goto L15;
        r22 = Theme.key_windowBackgroundWhite;
    L15:
        int r23 = Theme.getColor(r22, this.resourcesProvider);
    L17:
        setActionBarColor(r23, r12.overrideActionBarColor, false);
        setNavigationBarColor(r12.navigationBarColor, false);
        this.showExpanded = r12.expanded;
        this.showOffsetY = r12.expandedOffset;
        BotWebViewContainer r24 = this.webViewContainer;
        boolean r3 = r12.backButton;
        this.backButtonShown = r3;
        r24.setIsBackButtonVisible(r3);
        this.swipeContainer.setAllowSwipes(r12.allowSwipes);
        ImageView r25 = this.actionBar.getBackButton();
        if (this.backButtonShown == false) goto L20;
        int r32 = R.drawable.ic_ab_back;
    L21:
        AndroidUtilities.updateImageViewImageAnimated(r25, r32);
        BotFullscreenButtons r26 = this.fullscreenButtons;
        if (r26 == null) goto L24;
        r26.setBack(this.backButtonShown, false);
    L24:
        this.needCloseConfirmation = r12.confirmDismiss;
        this.fullsize = Boolean.valueOf(r12.fullsize);
        this.needsContext = r12.needsContext;
        BotSensors r27 = r12.sensors;
        this.sensors = r27;
        if (r27 == null) goto L27;
        r27.resume();
    L27:
        BotButtons.ButtonsState r28 = r12.buttons;
        if (r28 == null) goto L30;
        this.botButtons.setState(r28, false);
    L30:
        setFullscreen(r12.fullscreen, false, r12.fullscreenBlur);
        WebViewRequestProps r29 = r12.props;
        if (r29 == null) goto L33;
        int r33 = r29.currentAccount;
    L34:
        this.currentAccount = r33;
        BotWebViewContainer.MyWebView r34 = r12.webView;
        if (r34 == null) goto L46;
        r34.onResume();
        this.webViewContainer.replaceWebView(this.currentAccount, r12.webView, r12.proxy, r12.trustedOrigin, r12.sameOrigin);
        BotWebViewContainer r210 = this.webViewContainer;
        if (r12.ready == false) goto L39;
    L42:
        boolean r35 = true;
    L43:
        r210.setState(r35, r12.lastUrl);
        if (Theme.isCurrentThemeDark() == r12.themeIsDark) goto L47;
        this.webViewContainer.notifyThemeChanged();
    L47:
        requestWebView(r11, r12.props);
        this.hasSettings = r12.settings;
        if (r12.error == false) goto L54;
        this.errorShown = true;
        createErrorContainer();
        ArticleViewer.ErrorContainer r112 = this.errorContainer;
        String r211 = UserObject.getUserName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId)));
        String r36 = r12.errorDescription;
        this.errorCode = r36;
        r112.set(r211, r36);
        ArticleViewer.ErrorContainer r113 = this.errorContainer;
        if (AndroidUtilities.computePerceivedBrightness(this.backgroundPaint.getColor()) > 0.721f) goto L52;
        boolean r212 = true;
    L53:
        r113.setDark(r212, false);
        this.errorContainer.setBackgroundColor(this.backgroundPaint.getColor());
        this.errorContainer.setVisibility(0);
        this.errorContainer.setAlpha(1.0f);
        goto L54
    L52:
        r212 = false;
    L54:
        lockOrientation(r12.orientationLocked);
        return true;
    L39:
        if (r12.webView.isPageLoaded() == true) goto L42;
        r35 = false;
        goto L43
    L46:
        r29.response = null;
        r29.responseTime = 0;
        goto L47
    L33:
        r33 = UserConfig.selectedAccount;
        goto L34
    L20:
        r32 = R.drawable.ic_close_white;
        goto L21
    L16:
        r23 = r12.actionBarColor;
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    public BottomSheetTabs.WebTabData saveState() {
        BottomSheetTabs.WebTabData r0 = new BottomSheetTabs.WebTabData();
        r0.actionBarColor = this.actionBarColor;
        r0.actionBarColorKey = this.actionBarColorKey;
        r0.overrideActionBarColor = this.overrideActionBarColor;
        r0.overrideBackgroundColor = this.overrideBackgroundColor;
        r0.backgroundColor = this.backgroundPaint.getColor();
        r0.props = this.requestProps;
        BotWebViewContainer r1 = this.webViewContainer;
        boolean r2 = false;
        if (r1 != null) goto L5;
    L7:
        boolean r12 = false;
    L8:
        r0.ready = r12;
        r0.themeIsDark = Theme.isCurrentThemeDark();
        BotWebViewContainer r13 = this.webViewContainer;
        BotWebViewContainer.BotWebViewProxy r4 = null;
        if (r13 == null) goto L11;
        String r14 = r13.getUrlLoaded();
    L12:
        r0.lastUrl = r14;
        BotWebViewContainer r15 = this.webViewContainer;
        if (r15 != null) goto L15;
    L17:
        boolean r16 = false;
    L18:
        r0.sameOrigin = r16;
        BotWebViewContainer r17 = this.webViewContainer;
        if (r17 == null) goto L21;
        String r18 = r17.getTrustedOrigin();
    L22:
        r0.trustedOrigin = r18;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r19 = this.swipeContainer;
        if (r19 == null) goto L27;
        if (r19.getSwipeOffsetY() >= 0.0f) goto L27;
    L34:
        boolean r110 = true;
    L35:
        r0.expanded = r110;
        r0.fullscreen = this.fullscreen;
        r0.fullscreenBlur = this.fullscreenBlur;
        Boolean r111 = this.fullsize;
        if (r111 != null) goto L38;
        boolean r112 = this.defaultFullsize;
    L39:
        r0.fullsize = r112;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r113 = this.swipeContainer;
        if (r113 == null) goto L42;
        float r114 = r113.getOffsetY();
    L43:
        r0.expandedOffset = r114;
        r0.needsContext = this.needsContext;
        r0.backButton = this.backButtonShown;
        r0.confirmDismiss = this.needCloseConfirmation;
        r0.settings = this.hasSettings;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r115 = this.swipeContainer;
        if (r115 != null) goto L46;
    L47:
        r2 = true;
    L48:
        r0.allowSwipes = r2;
        r0.buttons = this.botButtons.state;
        r0.navigationBarColor = this.navBarColor;
        BotSensors r116 = this.sensors;
        if (r116 == null) goto L51;
        r116.pause();
    L51:
        r0.sensors = this.sensors;
        BotWebViewContainer r117 = this.webViewContainer;
        if (r117 != null) goto L54;
        BotWebViewContainer.MyWebView r118 = null;
    L55:
        if (r118 == null) goto L61;
        this.webViewContainer.preserveWebView();
        r0.webView = r118;
        BotWebViewContainer r22 = this.webViewContainer;
        if (r22 == null) goto L60;
        r4 = r22.getBotProxy();
    L60:
        r0.proxy = r4;
        r0.viewWidth = r118.getWidth();
        r0.viewHeight = r118.getHeight();
        r118.onPause();
    L61:
        boolean r119 = this.errorShown;
        r0.error = r119;
        if (r119 == false) goto L64;
        r0.errorDescription = this.errorCode;
    L64:
        r0.orientationLocked = this.orientationLocked;
        this.lastTab = r0;
        return r0;
    L54:
        r118 = r117.getWebView();
        goto L55
    L46:
        if (r115.isAllowedSwipes() == false) goto L48;
    L42:
        r114 = Float.MAX_VALUE;
        goto L43
    L38:
        r112 = r111.booleanValue();
    L27:
        if (this.forceExpnaded == true) goto L34;
        if (isFullSize() == true) goto L34;
        if (this.fullscreen == true) goto L34;
        r110 = false;
        goto L35
    L21:
        r18 = null;
        goto L22
    L15:
        if (r15.isBridgeRestrictedToOrigin() == false) goto L17;
        r16 = true;
        goto L18
    L11:
        r14 = null;
        goto L12
    L5:
        if (r1.isPageLoaded() == false) goto L7;
        r12 = true;
        goto L8
    }

    public void setActionBarColor(int r9, boolean r10, boolean r11) {
        int r0 = this.actionBarColor;
        navigationBarColor(r9);
        BotWebViewMenuContainer$ActionBarColorsAnimating r1 = new BotWebViewMenuContainer$ActionBarColorsAnimating();
        int r3 = 0;
        if (this.overrideActionBarColor == false) goto L5;
        int r2 = this.actionBarColor;
    L6:
        r1.setFrom(r2, this.resourcesProvider);
        this.overrideActionBarColor = r10;
        if (ColorUtils.calculateLuminance(r9) >= 0.7210000157356262d) goto L9;
        boolean r102 = true;
    L10:
        this.actionBarIsLight = r102;
        if (this.overrideActionBarColor == false) goto L13;
        r3 = r9;
    L13:
        r1.setTo(r3, this.resourcesProvider);
        if (r11 == false) goto L16;
        ValueAnimator r103 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(200);
        r103.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r103.addUpdateListener(new BotWebViewSheet$$ExternalSyntheticLambda47(this, r0, r9, r1));
        r103.addListener(new AnonymousClass17(this, r0, r9, r1));
        r103.start();
    L17:
        updateLightStatusBar();
        return;
    L16:
        this.actionBarColor = r9;
        checkNavBarColor();
        this.windowView.invalidate();
        this.actionBar.setBackgroundColor(this.actionBarColor);
        r1.updateActionBar(this.actionBar, 1.0f);
        this.lineColor = r1.getColor(Theme.key_sheet_scrollUp);
        this.windowView.invalidate();
        goto L17
    L9:
        r102 = false;
        goto L10
    L5:
        r2 = 0;
        goto L6
    }

    public void setAttached(boolean r2) {
        if (this.attached != r2) goto L5;
        return;
    L5:
        this.attached = r2;
        boolean r0 = this.orientationLocked;
        if (r2 == false) goto L9;
        if (r0 == false) goto L12;
        shownLockedBots++;
    L12:
        if (shownLockedBots <= 0) goto L15;
        AndroidUtilities.lockOrientation(getActivity());
        return;
    L15:
        AndroidUtilities.unlockOrientation(getActivity());
        return;
    L9:
        if (r0 == false) goto L12;
        shownLockedBots--;
        goto L12
    }

    public void setBackgroundColor(int r4, boolean r5, boolean r6) {
        int r0 = this.backgroundPaint.getColor();
        this.overrideBackgroundColor = r5;
        ValueAnimator r52 = this.backgroundColorAnimator;
        if (r52 == null) goto L5;
        r52.cancel();
    L5:
        if (r6 == false) goto L8;
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(200);
        this.backgroundColorAnimator = r53;
        r53.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.backgroundColorAnimator.addUpdateListener(new BotWebViewSheet$$ExternalSyntheticLambda30(this, r0, r4));
        this.backgroundColorAnimator.addListener(new AnonymousClass13(this, r4));
        this.backgroundColorAnimator.start();
        return;
    L8:
        this.backgroundPaint.setColor(r4);
        updateActionBarColors();
        this.windowView.invalidate();
        ArticleViewer.ErrorContainer r42 = this.errorContainer;
        if (r42 != null) goto L11;
    L15:
        updateWebViewBackgroundColor();
        return;
    L11:
        if (AndroidUtilities.computePerceivedBrightness(this.backgroundPaint.getColor()) > 0.721f) goto L13;
        boolean r54 = true;
    L14:
        r42.setDark(r54, false);
        this.errorContainer.setBackgroundColor(this.backgroundPaint.getColor());
        goto L15
    L13:
        r54 = false;
        goto L14
    }

    public void setDefaultFullsize(boolean r2) {
        if (this.defaultFullsize == r2) goto L8;
        this.defaultFullsize = r2;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r22 = this.swipeContainer;
        if (r22 == null) goto L9;
        r22.setFullSize(isFullSize());
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheetTabsOverlay.Sheet
    public boolean setDialog(BottomSheetTabDialog r1) {
        return false;
    }

    public void setFullscreen(boolean r2, boolean r3) {
        setFullscreen(r2, r3, this.fullscreenBlur);
    }

    public void setFullscreen(boolean r13, boolean r14, boolean r15) {
        if (this.fullscreen != r13) goto L5;
        return;
    L5:
        this.fullscreen = r13;
        int r2 = 0;
        if (r15 == true) goto L8;
    L12:
        boolean r152 = false;
    L13:
        this.fullscreenBlur = r152;
        ValueAnimator r153 = this.fullscreenAnimator;
        if (r153 == null) goto L16;
        r153.cancel();
    L16:
        BotFullscreenButtons r154 = this.fullscreenButtons;
        if (r154 == null) goto L23;
        r154.setPreview(r13, r14);
        BotFullscreenButtons r155 = this.fullscreenButtons;
        if (this.fullscreenBlur == false) goto L21;
        Object r3 = this.swipeContainer.getRenderNode();
    L22:
        r155.setParentRenderNode(r3);
        goto L23
    L21:
        r3 = null;
    L23:
        this.swipeContainerFromWidth = this.swipeContainer.getWidth();
        this.swipeContainerFromHeight = this.swipeContainer.getHeight();
        this.resetOffsetY = false;
        if (r14 == false) goto L60;
        updateFullscreenLayout();
        updateWindowFlags();
        updateDownloadBulletinArrow();
        if (AndroidUtilities.isTablet() == true) goto L28;
    L32:
        float r142 = 0.0f;
    L33:
        int r4 = this.insets.left;
        if (r13 == false) goto L37;
        float r42 = r4 + r142;
    L36:
        float r10 = r42;
        if (r13 == false) goto L40;
    L39:
        float r11 = r142;
        if (r13 == false) goto L45;
        float r43 = this.swipeContainer.getTranslationY();
    L44:
        float r8 = r43;
        if (r13 == false) goto L49;
        int r44 = -AndroidUtilities.dp(24.0f);
    L48:
        float r45 = r44;
        float r46 = ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight;
        this.swipeContainer.cancelStickTo();
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(true);
        this.actionBar.setVisibility(0);
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r5 = this.swipeContainer;
        if (r13 == false) goto L53;
        r5.setTopActionBarOffsetY(-AndroidUtilities.dp(24.0f));
    L54:
        this.swipeContainer.invalidateTranslation();
        this.swipeContainer.invalidate();
        this.fullscreenTransitionProgress = 0.0f;
        if (r13 == false) goto L57;
        float r143 = 0.0f;
    L58:
        this.fullscreenProgress = r143;
        this.actionBar.setAlpha(1.0f - r143);
        this.actionBar.setTranslationY((-ActionBar.getCurrentActionBarHeight()) * this.fullscreenProgress);
        this.swipeContainer.setTranslationY(AndroidUtilities.lerp(r8, r45, this.fullscreenTransitionProgress));
        this.swipeContainer.setTranslationX(AndroidUtilities.lerp(r10, 0.0f, this.fullscreenTransitionProgress));
        this.botButtons.setTranslationX(AndroidUtilities.lerp(r11, 0.0f, this.fullscreenTransitionProgress));
        this.fullscreenButtons.setAlpha(this.fullscreenProgress);
        this.windowView.invalidate();
        this.webViewContainer.setViewPortHeightOffset(this.swipeContainer.getTranslationY() - r45);
        this.webViewContainer.invalidateViewPortHeight(false, false);
        this.fullscreenInProgress = true;
        ValueAnimator r144 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.fullscreenAnimator = r144;
        r144.addUpdateListener(new AnonymousClass14(this, r13, r8, r45, r10, r11));
        this.fullscreenAnimator.addListener(new AnonymousClass15(this, r13, r46, r10));
        this.fullscreenAnimator.setDuration(280);
        this.fullscreenAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.fullscreenAnimator.start();
        return;
    L57:
        r143 = 1.0f - 0.0f;
        goto L58
    L53:
        r5.setTopActionBarOffsetY(r46 - AndroidUtilities.dp(24.0f));
        goto L54
    L49:
        r44 = (ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(24.0f);
        goto L48
    L45:
        r43 = -AndroidUtilities.dp(24.0f);
        goto L44
    L40:
        r142 = -r142;
        goto L39
    L37:
        r42 = (-r4) - r142;
        goto L36
    L28:
        if (AndroidUtilities.isInMultiwindow == true) goto L32;
        if (AndroidUtilities.isSmallTablet() == true) goto L32;
        int r47 = AndroidUtilities.displaySize.x;
        r142 = (r47 - ((int) (Math.min(r47, r14.y) * 0.8f))) / 2.0f;
        goto L33
    L60:
        this.fullscreenInProgress = false;
        if (r13 == false) goto L63;
        float r12 = 1.0f;
    L64:
        this.fullscreenProgress = r12;
        this.fullscreenTransitionProgress = 0.0f;
        updateFullscreenLayout();
        updateWindowFlags();
        ActionBar r122 = this.actionBar;
        if (r13 == false) goto L67;
        r2 = 8;
    L67:
        r122.setVisibility(r2);
        this.actionBar.setAlpha(1.0f - this.fullscreenProgress);
        this.actionBar.setTranslationY((-ActionBar.getCurrentActionBarHeight()) * this.fullscreenProgress);
        this.botButtons.setTranslationX(0.0f);
        this.fullscreenButtons.setAlpha(this.fullscreenProgress);
        this.webViewContainer.setViewPortHeightOffset(0.0f);
        this.webViewContainer.invalidateViewPortHeight(true, true);
        updateDownloadBulletinArrow();
        return;
    L63:
        r12 = 0.0f;
        goto L64
    L8:
        if (MessagesController.getInstance(this.currentAccount).disableBotFullscreenBlur == true) goto L12;
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L12;
        r152 = true;
        goto L13
    }

    public void setNavigationBarColor(int r4, boolean r5) {
        int r0 = this.navBarColor;
        this.botButtons.setBackgroundColor(r4, r5);
        if (r5 == false) goto L5;
        ValueAnimator r52 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(200);
        r52.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r52.addUpdateListener(new BotWebViewSheet$$ExternalSyntheticLambda46(this, r0, r4));
        r52.addListener(new AnonymousClass16(this, r0, r4));
        r52.start();
    L6:
        AndroidUtilities.setNavigationBarColor(this, this.navBarColor, false);
        return;
    L5:
        this.navBarColor = r4;
        checkNavBarColor();
        goto L6
    }

    public void setNeedsContext(boolean r1) {
        this.needsContext = r1;
    }

    public void setOnVerifiedAge(Utilities.Callback4<Boolean, Double, String, Double> r1) {
        this.onVerifiedAge = r1;
        BotWebViewContainer r0 = this.webViewContainer;
        if (r0 == null) goto L6;
        r0.setOnVerifiedAge(r1);
        return;
    }

    public void setOpen(boolean r5) {
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r02 = this.openedProgress;
        float r1 = 0.0f;
        if (r5 == false) goto L8;
        float r3 = 1.0f;
    L10:
        if (Math.abs(r02 - r3) >= 0.01f) goto L12;
        return;
    L12:
        float r03 = this.openedProgress;
        if (r5 == false) goto L15;
        r1 = 1.0f;
    L15:
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{r03, r1});
        this.openAnimator = r04;
        r04.addListener(new AnonymousClass12(this, r5));
        this.openAnimator.addUpdateListener(new BotWebViewSheet$$ExternalSyntheticLambda11(this));
        this.openAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.openAnimator.setDuration(220);
        this.openAnimator.start();
        return;
    L8:
        r3 = 0.0f;
        goto L10
    }

    public void setParentActivity(Activity r1) {
        this.parentActivity = r1;
    }

    public void setWasOpenedByLinkIntent(boolean r1) {
        BotWebViewContainer r0 = this.webViewContainer;
        if (r0 == null) goto L6;
        r0.setWasOpenedByLinkIntent(r1);
        return;
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L5;
        return;
    L5:
        setOpen(true);
        this.windowView.setAlpha(0.0f);
        this.windowView.addOnLayoutChangeListener(new AnonymousClass11(this));
        super.show();
        this.superDismissed = false;
        activeSheets.add(this);
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
        String r02 = LocaleController.formatString(R.string.BotAttachMenuShortcatAddedSide, new Object[]{r0.first_name});
    L18:
        AndroidUtilities.runOnUIThread(new BotWebViewSheet$$ExternalSyntheticLambda57(this, r02), 200);
        return;
    L17:
        r02 = LocaleController.formatString(R.string.BotAttachMenuShortcatAddedAttach, new Object[]{r0.first_name});
        goto L18
    L13:
        if (r42.show_in_attach_menu == false) goto L15;
        r02 = LocaleController.formatString(R.string.BotAttachMenuShortcatAddedAttachAndSide, new Object[]{r0.first_name});
        goto L18
    L7:
        r42 = null;
        goto L8
    }

    public void updateFullscreenLayout() {
        this.fullscreenButtons.setInsets(this.insets);
        int r1 = 0;
        if (this.fullscreen == false) goto L20;
        BotButtons r0 = this.botButtons;
        if (r0 != null) goto L7;
    L9:
        int r02 = 0;
    L10:
        BotWebViewContainer r2 = this.webViewContainer;
        Rect r4 = this.insets;
        int r5 = r4.left;
        int r6 = r4.top;
        int r42 = r4.right;
        if (this.keyboardInset <= r02) goto L13;
    L12:
        int r7 = 0;
    L19:
        r2.reportSafeInsets(new Rect(r5, r6, r42, r7), AndroidUtilities.dp(46.0f));
        this.windowView.setPadding(0, 0, 0, Math.max(this.keyboardInset, r02));
    L25:
        this.swipeContainerLayoutParams.topMargin = AndroidUtilities.dp(24.0f);
        FrameLayout.LayoutParams r03 = this.actionBarLayoutParams;
        boolean r3 = this.fullscreen;
        if (r3 == true) goto L28;
        int r43 = 0;
    L29:
        r03.leftMargin = r43;
        r03.rightMargin = 0;
        FrameLayout.LayoutParams r04 = this.bulletinContainerLayoutParams;
        if (r3 == true) goto L32;
        int r44 = 0;
    L33:
        r04.leftMargin = r44;
        if (r3 == true) goto L36;
        int r32 = 0;
    L37:
        r04.rightMargin = r32;
        if (this.fullscreenInProgress == true) goto L44;
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(true);
        boolean r05 = this.fullscreen;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r33 = this.swipeContainer;
        if (r05 == false) goto L42;
        r33.setTopActionBarOffsetY(-AndroidUtilities.dp(24.0f));
    L43:
        this.swipeContainer.setSwipeOffsetAnimationDisallowed(false);
        this.swipeContainer.invalidateTranslation();
        this.swipeContainer.invalidate();
        this.swipeContainer.requestLayout();
        goto L44
    L42:
        r33.setTopActionBarOffsetY((ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(24.0f));
    L44:
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r06 = this.swipeContainer;
        if (r06 == null) goto L47;
        r06.setFullSize(isFullSize());
    L47:
        this.botButtons.requestLayout();
        this.windowView.requestLayout();
        BotFullscreenButtons r07 = this.fullscreenButtons;
        if (this.fullscreen == true) goto L51;
        r1 = 8;
    L51:
        r07.setVisibility(r1);
        return;
    L36:
        r32 = this.insets.right;
        goto L37
    L32:
        r44 = this.insets.left;
        goto L33
    L28:
        r43 = this.insets.left;
        goto L29
    L13:
        BotButtons r72 = this.botButtons;
        if (r72 != null) goto L16;
    L18:
        r7 = this.insets.bottom;
        goto L19
    L16:
        if (r72.getTotalHeight() <= 0) goto L18;
    L7:
        if (r0.getTotalHeight() <= 0) goto L9;
        r02 = this.insets.bottom;
        goto L10
    L20:
        this.webViewContainer.reportSafeInsets(new Rect(0, 0, 0, 0), 0);
        WindowView r08 = this.windowView;
        Rect r22 = this.insets;
        int r34 = r22.left;
        int r23 = r22.right;
        int r45 = this.keyboardInset;
        BottomSheetTabs r52 = this.bottomTabs;
        if (r52 == null) goto L23;
        int r53 = r52.getHeight(false);
    L24:
        r08.setPadding(r34, 0, r23, Math.max(r45, r53 + this.insets.bottom));
        goto L25
    L23:
        r53 = 0;
        goto L24
    }

    public void updateWindowFlags() {
        Window r0 = getWindow();     // Catch: Exception -> L24
        if (r0 != null) goto L5;
        return;
    L5:
        WindowManager.LayoutParams r1 = r0.getAttributes();     // Catch: Exception -> L24
        if (Build.VERSION.SDK_INT > 28) goto L8;
        int r2 = 1024;
    L9:
        boolean r3 = this.fullscreen;     // Catch: Exception -> L24
        if (r3 == false) goto L12;
        r1.flags = r2 | r1.flags;     // Catch: Exception -> L24
    L13:
        if (r3 == false) goto L21;
        BotButtons r22 = this.botButtons;     // Catch: Exception -> L24
        if (r22 == null) goto L19;
        if (r22.getTotalHeight() > 0) goto L21;
    L19:
        if (WindowView.m21440$$Nest$fgetdrawingFromOverlay(this.windowView) == true) goto L21;
        WindowView r5 = this.windowView;     // Catch: Exception -> L24
        r5.setSystemUiVisibility(r5.getSystemUiVisibility() | 2);     // Catch: Exception -> L24
    L22:
        r0.setAttributes(r1);     // Catch: Exception -> L24
        return;
    L21:
        WindowView r52 = this.windowView;     // Catch: Exception -> L24
        r52.setSystemUiVisibility(r52.getSystemUiVisibility() & (-3));     // Catch: Exception -> L24
        goto L22
    L12:
        int r4 = r1.flags;     // Catch: Exception -> L24
        r1.flags = (~r2) & r4;     // Catch: Exception -> L24
        goto L13
    L8:
        r2 = 512;
    L24:
        e = move-exception;
        FileLog.e(e);
    }
}
