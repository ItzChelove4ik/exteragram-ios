package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Property;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowInsets;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.exteragram.messenger.utils.ui.PredictiveBackAnimationHelper;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper;
import com.google.android.material.slider.Slider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BackButtonMenu;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugController;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugProvider;
import org.telegram.ui.Components.GroupCallPip;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.SlideChooseView;
import org.telegram.ui.EmptyBaseFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes3.dex */
public class ActionBarLayout extends FrameLayout implements INavigationLayout, FloatingDebugProvider {
    private static Paint scrimPaint;
    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
    private ArrayList<int[]> animateEndColors;
    private int animateSetThemeAccentIdAfterAnimation;
    private Theme.ThemeInfo animateSetThemeAfterAnimation;
    private boolean animateSetThemeAfterAnimationApply;
    private boolean animateSetThemeNightAfterAnimation;
    private ArrayList<int[]> animateStartColors;
    private boolean animateThemeAfterAnimation;
    protected boolean animationInProgress;
    private float animationProgress;
    public INavigationLayout.ThemeAnimationSettings.onAnimationProgress animationProgressListener;
    private Runnable animationRunnable;
    private WindowTransitionAnimationHelper.WindowEdgeExtension aospEdgeExtension;
    private WindowTransitionAnimationHelper.WindowEdgeExtension aospEdgeExtensionBack;
    private Drawable aospTransitionBackgroundDrawable;
    private float aospTransitionDuration;
    private boolean attached;
    private AnimatorSet backAnimator;
    private boolean backAnimatorIsBack;
    private View backgroundView;
    private boolean beginTrackingSent;
    private BottomSheetTabs bottomSheetTabs;
    private BottomSheetTabs.ClipTools bottomSheetTabsClip;
    private float cachedBottomLeftRadius;
    private float cachedBottomRightRadius;
    private float cachedTopLeftRadius;
    private float cachedTopRightRadius;
    private final Path clipPath;
    public LayoutContainer containerView;
    public LayoutContainer containerViewBack;
    private ActionBar currentActionBar;
    private AnimatorSet currentAnimation;
    private int currentNavigationBarColor;
    private SpringAnimation currentSpringAnimation;
    Runnable debugBlackScreenRunnable;
    private DecelerateInterpolator decelerateInterpolator;
    private boolean delayedAnimationResumed;
    private Runnable delayedOpenAnimationRunnable;
    private INavigationLayout.INavigationLayoutDelegate delegate;
    private DrawerLayoutContainer drawerLayoutContainer;
    private List<BaseFragment> fragmentsStack;
    private final AnimatedFloat hasSheetsAnimator;
    public boolean highlightActionButtons;
    private boolean inActionMode;
    private boolean inBubbleMode;
    private boolean inPreviewMode;
    public float innerTranslationX;
    private final Runnable invalidateRunnable;
    public boolean isKeyboardVisible;
    private boolean isLayersLayout;
    private boolean isRightLayout;
    private boolean isSheet;
    ArrayList<String> lastActions;
    private long lastFrameTime;
    private boolean lastPortrait;
    private WindowInsetsCompat lastWindowInsetsCompat;
    private View layoutToIgnore;
    private final boolean main;
    private boolean maybeStartTracking;
    private int[] measureSpec;
    public MessageDrawable messageDrawableOutMediaStart;
    public MessageDrawable messageDrawableOutStart;
    private BaseFragment newFragment;
    AnimationNotificationsLocker notificationsLocker;
    private BaseFragment oldFragment;
    private Runnable onCloseAnimationEndRunnable;
    private Runnable onFragmentStackChangedListener;
    private Runnable onOpenAnimationEndRunnable;
    private boolean openingAnimation;
    private Runnable overlayAction;
    private int overrideWidthOffset;
    private OvershootInterpolator overshootInterpolator;
    protected Activity parentActivity;
    private final PredictiveBackAnimationHelper predictiveBackAnimation;
    private Drawable predictiveBackBackgroundDrawable;
    private boolean predictiveBackHasProgress;
    private boolean predictiveBackInProgress;
    private boolean predictiveBackLeft;
    private final RectF predictiveBackRect;
    private float predictiveBackY;
    private boolean predictiveInput;
    private ArrayList<ThemeDescription> presentingFragmentDescriptions;
    private ColorDrawable previewBackgroundDrawable;
    private boolean previewFinishInProgress;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout previewMenu;
    private boolean previewOpenAnimationInProgress;
    private List<BackButtonMenu.PulledDialog> pulledDialogs;
    private float[] radii;
    private boolean rebuildAfterAnimation;
    private boolean rebuildLastAfterAnimation;
    private Rect rect;
    private final Runnable relayoutRunnable;
    private boolean removeActionBarExtraHeight;
    private int savedBottomSheetTabsTop;
    public LayoutContainer sheetContainer;
    private EmptyBaseFragment sheetFragment;
    private boolean showLastAfterAnimation;
    private SpringForce springForce;
    private boolean springIsBack;
    private boolean springIsLayout;
    private boolean springIsPreview;
    private Drawable springRouteBackgroundDrawable;
    private float springRouteYRatio;
    private FloatValueHolder springValueHolder;
    INavigationLayout.StartColorsProvider startColorsProvider;
    protected boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    private Insets systemAndDisplayAndImeInsets;
    private Insets systemAndDisplayInsets;
    private boolean tabsEvents;
    private float themeAnimationValue;
    private ArrayList<ThemeDescription.ThemeDescriptionDelegate> themeAnimatorDelegate;
    private ArrayList<ArrayList<ThemeDescription>> themeAnimatorDescriptions;
    private AnimatorSet themeAnimatorSet;
    private String titleOverlayText;
    private int titleOverlayTextId;
    private boolean transitionAnimationInProgress;
    private boolean transitionAnimationPreviewMode;
    private long transitionAnimationStartTime;
    private boolean useAlphaAnimations;
    private VelocityTracker velocityTracker;
    private Runnable waitingForKeyboardCloseRunnable;
    private Window window;
    private boolean withShadow;

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends EmptyBaseFragment {
        final /* synthetic */ ActionBarLayout this$0;

        public AnonymousClass1(ActionBarLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void updateSheetsVisibility() {
            super.updateSheetsVisibility();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass10(ActionBarLayout r1, BaseFragment r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ActionBarLayout.m5475$$Nest$fputpreviewOpenAnimationInProgress(this.this$0, false);
            this.val$fragment.onPreviewOpenAnimationEnd();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ ActionBarLayout this$0;

        public AnonymousClass11(ActionBarLayout r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionBarLayout.m5467$$Nest$fgetwaitingForKeyboardCloseRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            ActionBarLayout.m5478$$Nest$fputwaitingForKeyboardCloseRunnable(this.this$0, null);
            ActionBarLayout.m5481$$Nest$mstartLayoutAnimation(this.this$0, false, true, false);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarLayout this$0;

        public AnonymousClass12(ActionBarLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ActionBarLayout.m5479$$Nest$monAnimationEndCheck(this.this$0, false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r3) {
            ActionBarLayout.m5477$$Nest$fputtransitionAnimationStartTime(this.this$0, System.currentTimeMillis());
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ INavigationLayout.ThemeAnimationSettings val$settings;

        public AnonymousClass13(ActionBarLayout r1, INavigationLayout.ThemeAnimationSettings r2) {
            this.this$0 = r1;
            this.val$settings = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(ActionBarLayout.m5464$$Nest$fgetthemeAnimatorSet(this.this$0)) == false) goto L8;
            ActionBarLayout.m5463$$Nest$fgetthemeAnimatorDescriptions(this.this$0).clear();
            ActionBarLayout.m5440$$Nest$fgetanimateStartColors(this.this$0).clear();
            ActionBarLayout.m5439$$Nest$fgetanimateEndColors(this.this$0).clear();
            ActionBarLayout.m5462$$Nest$fgetthemeAnimatorDelegate(this.this$0).clear();
            Theme.setAnimatingColor(false);
            ActionBarLayout.m5474$$Nest$fputpresentingFragmentDescriptions(this.this$0, null);
            ActionBarLayout r22 = this.this$0;
            r22.animationProgressListener = null;
            ActionBarLayout.m5476$$Nest$fputthemeAnimatorSet(r22, null);
            Runnable r1 = this.val$settings.afterAnimationRunnable;
            if (r1 == null) goto L9;
            r1.run();
            return;
        L9:
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.notificationsLocker.unlock();
            if (r2.equals(ActionBarLayout.m5464$$Nest$fgetthemeAnimatorSet(this.this$0)) == false) goto L8;
            ActionBarLayout.m5463$$Nest$fgetthemeAnimatorDescriptions(this.this$0).clear();
            ActionBarLayout.m5440$$Nest$fgetanimateStartColors(this.this$0).clear();
            ActionBarLayout.m5439$$Nest$fgetanimateEndColors(this.this$0).clear();
            ActionBarLayout.m5462$$Nest$fgetthemeAnimatorDelegate(this.this$0).clear();
            Theme.setAnimatingColor(false);
            ActionBarLayout.m5474$$Nest$fputpresentingFragmentDescriptions(this.this$0, null);
            ActionBarLayout r22 = this.this$0;
            r22.animationProgressListener = null;
            ActionBarLayout.m5476$$Nest$fputthemeAnimatorSet(r22, null);
            Runnable r1 = this.val$settings.afterAnimationRunnable;
            if (r1 == null) goto L9;
            r1.run();
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ boolean val$backAnimation;

        public AnonymousClass2(ActionBarLayout r1, boolean r2) {
            this.this$0 = r1;
            this.val$backAnimation = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            this.cancelled = true;
            ActionBarLayout.m5473$$Nest$fputpredictiveBackInProgress(this.this$0, false);
            this.this$0.containerView.setAlpha(1.0f);
            ActionBarLayout.m5480$$Nest$monSlideAnimationEnd(this.this$0, true);
            ActionBarLayout.m5470$$Nest$fputbackAnimator(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            ActionBarLayout.m5473$$Nest$fputpredictiveBackInProgress(this.this$0, false);
            this.this$0.containerView.setAlpha(1.0f);
            ActionBarLayout.m5480$$Nest$monSlideAnimationEnd(this.this$0, this.val$backAnimation);
            ActionBarLayout.m5470$$Nest$fputbackAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ boolean val$backAnimation;

        public AnonymousClass3(ActionBarLayout r1, boolean r2) {
            this.this$0 = r1;
            this.val$backAnimation = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            this.cancelled = true;
            ActionBarLayout.m5473$$Nest$fputpredictiveBackInProgress(this.this$0, false);
            ActionBarLayout.m5457$$Nest$fgetpredictiveBackAnimation(this.this$0).reset();
            this.this$0.containerView.setAlpha(1.0f);
            ActionBarLayout.m5480$$Nest$monSlideAnimationEnd(this.this$0, true);
            ActionBarLayout.m5470$$Nest$fputbackAnimator(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            ActionBarLayout.m5473$$Nest$fputpredictiveBackInProgress(this.this$0, false);
            ActionBarLayout.m5457$$Nest$fgetpredictiveBackAnimation(this.this$0).reset();
            this.this$0.containerView.setAlpha(1.0f);
            ActionBarLayout.m5480$$Nest$monSlideAnimationEnd(this.this$0, this.val$backAnimation);
            ActionBarLayout.m5470$$Nest$fputbackAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ boolean val$first;
        final /* synthetic */ boolean val$open;
        final /* synthetic */ boolean val$preview;
        final /* synthetic */ boolean val$useAospTransition;

        public AnonymousClass4(ActionBarLayout r1, boolean r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            this.val$first = r2;
            this.val$useAospTransition = r3;
            this.val$preview = r4;
            this.val$open = r5;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionBarLayout.m5442$$Nest$fgetanimationRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            Integer r1 = null;
            ActionBarLayout.m5469$$Nest$fputanimationRunnable(this.this$0, null);
            if (this.val$first == false) goto L8;
            ActionBarLayout.m5477$$Nest$fputtransitionAnimationStartTime(this.this$0, System.currentTimeMillis());
        L8:
            long r2 = System.nanoTime() / 1000000;
            long r4 = r2 - ActionBarLayout.m5453$$Nest$fgetlastFrameTime(this.this$0);
            if (r4 <= 40) goto L14;
            if (this.val$first == false) goto L14;
            r4 = 0;
        L18:
            ActionBarLayout.m5472$$Nest$fputlastFrameTime(this.this$0, r2);
            if (this.val$useAospTransition == false) goto L22;
            float r0 = ActionBarLayout.m5443$$Nest$fgetaospTransitionDuration(this.this$0);
        L27:
            ActionBarLayout r22 = this.this$0;
            ActionBarLayout.m5468$$Nest$fputanimationProgress(r22, ActionBarLayout.m5441$$Nest$fgetanimationProgress(r22) + (r4 / r0));
            if (ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0) <= 1.0f) goto L31;
            ActionBarLayout.m5468$$Nest$fputanimationProgress(this.this$0, 1.0f);
        L31:
            if (ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0) == null) goto L34;
            ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0).onTransitionAnimationProgress(true, ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0));
        L34:
            if (ActionBarLayout.m5455$$Nest$fgetoldFragment(this.this$0) == null) goto L37;
            ActionBarLayout.m5455$$Nest$fgetoldFragment(this.this$0).onTransitionAnimationProgress(false, ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0));
        L37:
            if (ActionBarLayout.m5455$$Nest$fgetoldFragment(this.this$0) == null) goto L39;
            Integer r02 = Integer.valueOf(ActionBarLayout.m5455$$Nest$fgetoldFragment(this.this$0).getNavigationBarColor());
        L41:
            if (ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0) == null) goto L44;
            r1 = Integer.valueOf(ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0).getNavigationBarColor());
        L44:
            if (ActionBarLayout.m5455$$Nest$fgetoldFragment(this.this$0) == null) goto L50;
            if (ActionBarLayout.m5455$$Nest$fgetoldFragment(this.this$0).isSupportEdgeToEdge() == false) goto L50;
            if (r1 == null) goto L50;
            r02 = r1;
        L50:
            if (ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0) == null) goto L56;
            if (ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0).isSupportEdgeToEdge() == false) goto L56;
            if (r02 == null) goto L56;
            r1 = r02;
        L56:
            if (ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0) == null) goto L72;
            if (r02 == null) goto L72;
            if (r1 == null) goto L72;
            int r03 = ColorUtils.blendARGB(r02.intValue(), r1.intValue(), MathUtils.clamp(ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0) * 4.0f, 0.0f, 1.0f));
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0) != null) goto L62;
        L70:
            ActionBarLayout.m5454$$Nest$fgetnewFragment(this.this$0).setNavigationBarColor(r03);
            goto L72
        L62:
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0).sheetsStack == null) goto L70;
            int r12 = 0;
        L65:
            if (r12 >= ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0).sheetsStack.size()) goto L70;
            BaseFragment.AttachedSheet r42 = ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0).sheetsStack.get(r12);
            if (r42.attachedToParent() == false) goto L69;
            r03 = r42.getNavigationBarColor(r03);
        L69:
            r12 = r12 + 1;
        L72:
            if (this.val$useAospTransition == false) goto L75;
            float r04 = ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0);
        L82:
            if (this.val$useAospTransition == false) goto L85;
            ActionBarLayout r13 = this.this$0;
            WindowTransitionAnimationHelper.apply(r13.containerView, r13.containerViewBack, this.val$open, r04);
            this.this$0.invalidate();
        L100:
            float r05 = ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0);
            ActionBarLayout r14 = this.this$0;
            if (r05 >= 1.0f) goto L104;
            ActionBarLayout.m5481$$Nest$mstartLayoutAnimation(r14, this.val$open, false, this.val$preview);
            return;
        L104:
            ActionBarLayout.m5479$$Nest$monAnimationEndCheck(r14, false);
            return;
        L85:
            if (this.val$open == false) goto L93;
            float r15 = MathUtils.clamp(r04, 0.0f, 1.0f);
            this.this$0.containerView.setAlpha(r15);
            boolean r5 = this.val$preview;
            ActionBarLayout r8 = this.this$0;
            if (r5 == false) goto L92;
            float r52 = (0.3f * r04) + 0.7f;
            r8.containerView.setScaleX(r52);
            this.this$0.containerView.setScaleY(r52);
            if (ActionBarLayout.m5459$$Nest$fgetpreviewMenu(this.this$0) == null) goto L91;
            float r82 = 1.0f - r04;
            this.this$0.containerView.setTranslationY(AndroidUtilities.dp(40.0f) * r82);
            ActionBarLayout.m5459$$Nest$fgetpreviewMenu(this.this$0).setTranslationY((-AndroidUtilities.dp(70.0f)) * r82);
            float r06 = (r04 * 0.05f) + 0.95f;
            ActionBarLayout.m5459$$Nest$fgetpreviewMenu(this.this$0).setScaleX(r06);
            ActionBarLayout.m5459$$Nest$fgetpreviewMenu(this.this$0).setScaleY(r06);
        L91:
            ActionBarLayout.m5458$$Nest$fgetpreviewBackgroundDrawable(this.this$0).setAlpha((int) (46.0f * r15));
            Theme.moveUpDrawable.setAlpha((int) (r15 * 255.0f));
            this.this$0.containerView.invalidate();
            this.this$0.invalidate();
            goto L100
        L92:
            r8.containerView.setTranslationX(AndroidUtilities.dp(48.0f) * (1.0f - r04));
            goto L100
        L93:
            float r16 = 1.0f - r04;
            float r53 = MathUtils.clamp(r16, 0.0f, 1.0f);
            this.this$0.containerViewBack.setAlpha(r53);
            boolean r83 = this.val$preview;
            ActionBarLayout r9 = this.this$0;
            if (r83 == false) goto L99;
            float r17 = (r16 * 0.1f) + 0.9f;
            r9.containerViewBack.setScaleX(r17);
            this.this$0.containerViewBack.setScaleY(r17);
            ActionBarLayout.m5458$$Nest$fgetpreviewBackgroundDrawable(this.this$0).setAlpha((int) (46.0f * r53));
            if (ActionBarLayout.m5459$$Nest$fgetpreviewMenu(this.this$0) != null) goto L98;
            Theme.moveUpDrawable.setAlpha((int) (r53 * 255.0f));
        L98:
            this.this$0.containerView.invalidate();
            this.this$0.invalidate();
            goto L100
        L99:
            r9.containerViewBack.setTranslationX(AndroidUtilities.dp(48.0f) * r04);
            goto L100
        L75:
            if (this.val$preview == true) goto L77;
            r04 = ActionBarLayout.m5445$$Nest$fgetdecelerateInterpolator(this.this$0).getInterpolation(ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0));
            goto L82
        L77:
            if (this.val$open == false) goto L79;
            r04 = ActionBarLayout.m5456$$Nest$fgetovershootInterpolator(this.this$0).getInterpolation(ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0));
            goto L82
        L79:
            r04 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(ActionBarLayout.m5441$$Nest$fgetanimationProgress(this.this$0));
            goto L82
        L39:
            r02 = null;
            goto L41
        L22:
            if (this.val$preview == true) goto L24;
        L26:
            r0 = 150.0f;
            goto L27
        L24:
            if (this.val$open == false) goto L26;
            r0 = 190.0f;
        L14:
            if (r4 <= 18) goto L18;
            if (this.val$useAospTransition == true) goto L18;
            r4 = 18;
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends ViewOutlineProvider {
        private final Path path;
        final /* synthetic */ ActionBarLayout this$0;

        public AnonymousClass5(ActionBarLayout r1) {
            this.this$0 = r1;
            this.path = new Path();
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r11, Outline r12) {
            float r0 = AndroidUtilities.dp(29.0f);
            float r1 = AndroidUtilities.dp(12.0f);
            this.path.rewind();
            this.path.addRoundRect(0.0f, 0.0f, r11.getWidth(), r11.getHeight(), new float[]{r0, r0, r0, r0, r1, r1, r1, r1}, Path.Direction.CW);
            int r112 = Build.VERSION.SDK_INT;
            Path r10 = this.path;
            if (r112 < 30) goto L6;
            r12.setPath(r10);
            return;
        L6:
            r12.setConvexPath(r10);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarLayout this$0;

        public AnonymousClass6(ActionBarLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ActionBarLayout.m5479$$Nest$monAnimationEndCheck(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ BaseFragment val$currentFragment;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ boolean val$noDelay;
        final /* synthetic */ boolean val$preview;

        public AnonymousClass7(ActionBarLayout r1, boolean r2, BaseFragment r3, BaseFragment r4, boolean r5) {
            this.this$0 = r1;
            this.val$noDelay = r2;
            this.val$currentFragment = r3;
            this.val$fragment = r4;
            this.val$preview = r5;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionBarLayout.m5467$$Nest$fgetwaitingForKeyboardCloseRunnable(this.this$0) != this) goto L21;
            ActionBarLayout.m5478$$Nest$fputwaitingForKeyboardCloseRunnable(this.this$0, null);
            if (this.val$noDelay == false) goto L13;
            BaseFragment r0 = this.val$currentFragment;
            if (r0 == null) goto L10;
            r0.onTransitionAnimationStart(false, false);
        L10:
            this.val$fragment.onTransitionAnimationStart(true, false);
            ActionBarLayout.m5481$$Nest$mstartLayoutAnimation(this.this$0, true, true, this.val$preview);
            return;
        L13:
            if (ActionBarLayout.m5447$$Nest$fgetdelayedOpenAnimationRunnable(this.this$0) == null) goto L20;
            AndroidUtilities.cancelRunOnUIThread(ActionBarLayout.m5447$$Nest$fgetdelayedOpenAnimationRunnable(this.this$0));
            boolean r02 = ActionBarLayout.m5446$$Nest$fgetdelayedAnimationResumed(this.this$0);
            ActionBarLayout r3 = this.this$0;
            if (r02 == false) goto L18;
            ActionBarLayout.m5447$$Nest$fgetdelayedOpenAnimationRunnable(r3).run();
            return;
        L18:
            AndroidUtilities.runOnUIThread(ActionBarLayout.m5447$$Nest$fgetdelayedOpenAnimationRunnable(r3), 200);
            return;
        L20:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ BaseFragment val$currentFragment;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ boolean val$preview;

        public AnonymousClass8(ActionBarLayout r1, BaseFragment r2, BaseFragment r3, boolean r4) {
            this.this$0 = r1;
            this.val$currentFragment = r2;
            this.val$fragment = r3;
            this.val$preview = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionBarLayout.m5447$$Nest$fgetdelayedOpenAnimationRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            ActionBarLayout.m5471$$Nest$fputdelayedOpenAnimationRunnable(this.this$0, null);
            BaseFragment r0 = this.val$currentFragment;
            if (r0 == null) goto L8;
            r0.onTransitionAnimationStart(false, false);
        L8:
            this.val$fragment.onTransitionAnimationStart(true, false);
            ActionBarLayout.m5481$$Nest$mstartLayoutAnimation(this.this$0, true, true, this.val$preview);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarLayout$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ ActionBarLayout this$0;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ boolean val$preview;

        public AnonymousClass9(ActionBarLayout r1, BaseFragment r2, boolean r3) {
            this.this$0 = r1;
            this.val$fragment = r2;
            this.val$preview = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionBarLayout.m5447$$Nest$fgetdelayedOpenAnimationRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            ActionBarLayout.m5471$$Nest$fputdelayedOpenAnimationRunnable(this.this$0, null);
            this.val$fragment.onTransitionAnimationStart(true, false);
            ActionBarLayout.m5481$$Nest$mstartLayoutAnimation(this.this$0, true, true, this.val$preview);
        }
    }

    public class LayoutContainer extends FrameLayout {
        private int backgroundColor;
        private Paint backgroundPaint;
        private boolean drawNavigationBar;
        private EdgeToEdgeSupportMode edgeToEdgeSupportMode;
        private int fragmentPanTranslationOffset;
        private boolean isKeyboardVisible;
        private boolean isSupportEdgeToEdge;
        private Matrix navbarGradientMatrix;
        private Rect rect;
        final /* synthetic */ ActionBarLayout this$0;
        private boolean wasPortrait;

        /* renamed from: -$$Nest$fgetedgeToEdgeSupportMode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EdgeToEdgeSupportMode m5482$$Nest$fgetedgeToEdgeSupportMode(LayoutContainer r0) {
            return r0.edgeToEdgeSupportMode;
        }

        /* renamed from: -$$Nest$fgetisKeyboardVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5483$$Nest$fgetisKeyboardVisible(LayoutContainer r0) {
            return r0.isKeyboardVisible;
        }

        /* renamed from: -$$Nest$fgetisSupportEdgeToEdge, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5484$$Nest$fgetisSupportEdgeToEdge(LayoutContainer r0) {
            return r0.isSupportEdgeToEdge;
        }

        public LayoutContainer(ActionBarLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new Rect();
            this.backgroundPaint = new Paint();
            this.navbarGradientMatrix = new Matrix();
            setWillNotDraw(false);
        }

        private void drawInsets(Canvas r10, int r11, boolean r12) {
            int r0 = getPaddingLeft();
            int r1 = getPaddingRight();
            int r2 = getPaddingBottom();
            Paint r8 = Theme.fillingPaint(r11);
            if (r0 > 0) goto L5;
        L7:
            Canvas r3 = r10;
        L8:
            if (r1 > 0) goto L10;
        L12:
            if (r2 > 0) goto L14;
            return;
        L14:
            if (this.edgeToEdgeSupportMode == EdgeToEdgeSupportMode.NONE) goto L16;
            if (r12 == true) goto L16;
            return;
        L16:
            r3.drawRect(0.0f, getHeight() - (r2 + 1), getWidth(), getHeight(), r8);
            return;
        L10:
            if (this.edgeToEdgeSupportMode == EdgeToEdgeSupportMode.FULL) goto L12;
            r3.drawRect(getWidth() - (r1 + 1), 0.0f, getWidth(), getHeight(), r8);
            goto L12
        L5:
            if (this.edgeToEdgeSupportMode == EdgeToEdgeSupportMode.FULL) goto L7;
            r3 = r10;
            r3.drawRect(0.0f, 0.0f, r0 + 1, getHeight(), r8);
            goto L8
        }

        private void updateChildrenAccessibilityImportance() {
            int r0 = getChildCount();     // Catch: Exception -> L27
            int r1 = r0 - 1;
        L4:
            if (r1 < 0) goto L11;
            View r3 = getChildAt(r1);     // Catch: Exception -> L27
            if ((r3 instanceof BaseFragment.AttachedSheetWindow) == false) goto L10;
            if (r3.getVisibility() != 0) goto L10;
        L12:
            int r4 = 0;
        L13:
            if (r4 >= r0) goto L26;
            View r5 = getChildAt(r4);     // Catch: Exception -> L27
            if (r5 == null) goto L25;
            if (r1 == (-1)) goto L21;
            if (r4 == r1) goto L21;
            int r6 = 4;
        L23:
            if (r5.getImportantForAccessibility() == r6) goto L25;
            r5.setImportantForAccessibility(r6);     // Catch: Exception -> L27
        L21:
            r6 = 0;
        L25:
            r4 = r4 + 1;
            goto L13
        L26:
            return;
        L10:
            r1 = r1 - 1;
            goto L4
        L11:
            r1 = -1;
        L27:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.view.ViewGroup
        public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
            super.addView(r1, r2, r3);
            ViewCompat.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            if (ActionBarLayout.m5452$$Nest$fgetisLayersLayout(this.this$0) == false) goto L7;
            super.dispatchDraw(r10);
            return;
        L7:
            if (ActionBarLayout.m5448$$Nest$fgetdrawerLayoutContainer(this.this$0) == null) goto L9;
            int r0 = ActionBarLayout.m5448$$Nest$fgetdrawerLayoutContainer(this.this$0).getInternalNavbarPaint().getColor();
        L10:
            ActionBarLayout r1 = this.this$0;
            boolean r3 = false;
            if (this != r1.sheetContainer) goto L21;
            AnimatedFloat r12 = ActionBarLayout.m5450$$Nest$fgethasSheetsAnimator(r1);
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0) != null) goto L15;
        L17:
            boolean r2 = false;
        L18:
            float r13 = r12.set(r2);
            if (r13 <= 0.0f) goto L36;
            drawInsets(r10, Theme.multAlpha(r0, r13), this.drawNavigationBar);
        L36:
            super.dispatchDraw(r10);
            if (r3 == true) goto L39;
            return;
        L39:
            if (Math.max(getPaddingBottom(), ActionBarLayout.m5461$$Nest$fgetsystemAndDisplayInsets(this.this$0).bottom) <= 0) goto L45;
            r10.drawRect(0.0f, getHeight() - (r1 + 1), getWidth(), getHeight(), Theme.fillingPaint(r0));
            return;
        L45:
            return;
        L15:
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0).hasSheet() == false) goto L17;
            r2 = true;
            goto L18
        L21:
            BaseFragment r14 = r1.getLastFragment();
            if (r14 == null) goto L36;
            if (r14.inPreviewMode == true) goto L36;
            if (this == this.this$0.containerView) goto L28;
        L35:
            drawInsets(r10, r0, r3);
            goto L36
        L28:
            if (this.edgeToEdgeSupportMode == EdgeToEdgeSupportMode.NONE) goto L35;
            int r15 = getChildCount();
            int r22 = 0;
        L30:
            if (r22 >= r15) goto L35;
            if ((getChildAt(r22) instanceof BaseFragment.AttachedSheetWindow) == true) goto L33;
            r22 = r22 + 1;
            goto L30
        L33:
            r3 = true;
            goto L35
        L9:
            r0 = Theme.getColor(Theme.key_windowBackgroundGray);
            goto L10
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r6) {
            r6.getAction();
            if (ActionBarLayout.m5451$$Nest$fgetinPreviewMode(this.this$0) == true) goto L5;
        L7:
            boolean r0 = false;
        L9:
            if (r0 == true) goto L13;
            if (ActionBarLayout.m5466$$Nest$fgettransitionAnimationPreviewMode(this.this$0) == true) goto L13;
        L18:
            if (ActionBarLayout.m5465$$Nest$fgettransitionAnimationInProgress(this.this$0) == true) goto L20;
        L26:
            if (r0 == true) goto L38;
        L32:
            if (super.dispatchTouchEvent(r6) == false) goto L35;
            return true;
        L35:
            return false;
        L30:
            th = move-exception;
            FileLog.e(th);
        L37:
            return false;
        L38:
            if (this == this.this$0.containerView) goto L35;
        L20:
            if (this == this.this$0.containerView) goto L26;
            if (r6.getActionMasked() != 0) goto L24;
        L25:
            return false;
        L24:
            if (r6.getActionMasked() != 5) goto L26;
        L13:
            if (r6.getActionMasked() == 0) goto L37;
            if (r6.getActionMasked() != 5) goto L18;
        L5:
            if (ActionBarLayout.m5459$$Nest$fgetpreviewMenu(this.this$0) != null) goto L7;
            r0 = true;
            goto L9
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (ExteraConfig.getInAppVibration() == true) goto L5;
            VibratorUtils.disableHapticFeedback(r8);
            VibratorUtils.disableHapticFeedback(this);
            VibratorUtils.disableHapticFeedback(this.this$0);
        L5:
            BaseFragment.AttachedSheet r1 = null;
            if (ActionBarLayout.m5449$$Nest$fgetfragmentsStack(this.this$0).isEmpty() == true) goto L8;
            BaseFragment r0 = (BaseFragment) ActionBarLayout.m5449$$Nest$fgetfragmentsStack(this.this$0).get(ActionBarLayout.m5449$$Nest$fgetfragmentsStack(this.this$0).size() - 1);
        L10:
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0) != null) goto L12;
        L16:
            if (r0 == null) goto L18;
            r1 = r0.getLastSheet();
        L18:
            if (r1 == null) goto L25;
            if (r1.isFullyVisible() == false) goto L25;
            if (r1.getWindowView() == r8) goto L25;
            return true;
        L25:
            if ((r8 instanceof ActionBar) == true) goto L27;
            int r02 = getChildCount();
            int r12 = 0;
            int r3 = 0;
        L29:
            if (r3 >= r02) goto L41;
            View r4 = getChildAt(r3);
            if (r4 == r8) goto L40;
            if ((r4 instanceof ActionBar) == false) goto L40;
            ActionBar r5 = (ActionBar) r4;
            if (r4.getVisibility() != 0) goto L40;
            if (r5.getCastShadows() == false) goto L41;
            r12 = r5.getMeasuredHeight();
            int r03 = r5.getShadowAlpha();
        L42:
            boolean r92 = super.drawChild(r7, r8, r9);
            if (r12 != 0) goto L45;
        L47:
            return r92;
        L45:
            if ((r8 instanceof BaseFragment.AttachedSheetWindow) == true) goto L47;
            this.this$0.drawHeaderShadow(r7, r03, r12 + 1);
        L40:
            r3 = r3 + 1;
        L41:
            r03 = 255;
            goto L42
        L27:
            return super.drawChild(r7, r8, r9);
        L12:
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0).sheetsStack == null) goto L16;
            if (ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0).sheetsStack.isEmpty() == true) goto L16;
            r0 = ActionBarLayout.m5460$$Nest$fgetsheetFragment(this.this$0);
            goto L16
        L8:
            r0 = null;
            goto L10
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            if (Build.VERSION.SDK_INT < 28) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.fragmentPanTranslationOffset == 0) goto L8;
            int r0 = Theme.key_windowBackgroundWhite;
            if (this.backgroundColor == Theme.getColor(r0)) goto L7;
            Paint r1 = this.backgroundPaint;
            int r02 = Theme.getColor(r0);
            this.backgroundColor = r02;
            r1.setColor(r02);
        L7:
            Canvas r12 = r8;
            r12.drawRect(0.0f, (getMeasuredHeight() - this.fragmentPanTranslationOffset) - 3, getMeasuredWidth(), getMeasuredHeight(), this.backgroundPaint);
        L9:
            super.onDraw(r12);
            return;
        L8:
            r12 = r8;
            goto L9
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
            int r72 = getChildCount();
            int r82 = getPaddingLeft();
            boolean r92 = false;
            int r102 = 0;
        L3:
            if (r102 >= r72) goto L8;
            View r112 = getChildAt(r102);
            if ((r112 instanceof ActionBar) == true) goto L6;
            r102 = r102 + 1;
            goto L3
        L6:
            int r103 = r112.getMeasuredHeight();
            r112.layout(r82, 0, r112.getMeasuredWidth() + r82, r103);
        L9:
            int r113 = 0;
        L10:
            if (r113 >= r72) goto L23;
            View r0 = getChildAt(r113);
            if ((r0 instanceof ActionBar) == true) goto L22;
            FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r0.getLayoutParams();
            if (r0.getTag(R.id.sheet_attached_to_fragment_tag) == null) goto L16;
        L21:
            int r2 = r1.leftMargin;
            r0.layout(r82 + r2, r1.topMargin, (r2 + r82) + r0.getMeasuredWidth(), r1.topMargin + r0.getMeasuredHeight());
            goto L22
        L16:
            if (r0.getFitsSystemWindows() == true) goto L21;
            if ((r0 instanceof BaseFragment.AttachedSheetWindow) == true) goto L21;
            int r22 = r1.leftMargin;
            r0.layout(r82 + r22, r1.topMargin + r103, (r22 + r82) + r0.getMeasuredWidth(), (r1.topMargin + r103) + r0.getMeasuredHeight());
        L22:
            r113 = r113 + 1;
            goto L10
        L23:
            View r73 = getRootView();
            getWindowVisibleDisplayFrame(this.rect);
            int r83 = r73.getHeight();
            if (this.rect.top == 0) goto L26;
            int r104 = AndroidUtilities.statusBarHeight;
        L27:
            int r84 = (r83 - r104) - AndroidUtilities.getViewInset(r73);
            Rect r74 = this.rect;
            if ((r84 - (r74.bottom - r74.top)) <= 0) goto L30;
            r92 = true;
        L30:
            this.isKeyboardVisible = r92;
            if (ActionBarLayout.m5467$$Nest$fgetwaitingForKeyboardCloseRunnable(this.this$0) == null) goto L44;
            ActionBarLayout r75 = this.this$0;
            if (r75.containerView.isKeyboardVisible == false) goto L35;
            return;
        L35:
            if (r75.containerViewBack.isKeyboardVisible == true) goto L46;
            AndroidUtilities.cancelRunOnUIThread(ActionBarLayout.m5467$$Nest$fgetwaitingForKeyboardCloseRunnable(r75));
            ActionBarLayout.m5467$$Nest$fgetwaitingForKeyboardCloseRunnable(this.this$0).run();
            ActionBarLayout.m5478$$Nest$fputwaitingForKeyboardCloseRunnable(this.this$0, null);
            return;
        L46:
            return;
        L44:
            return;
        L26:
            r104 = 0;
            goto L27
        L8:
            r103 = 0;
            goto L9
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r13, int r14) {
            int r6 = View.MeasureSpec.getSize(r13);
            int r7 = View.MeasureSpec.getSize(r14);
            if (r7 <= r6) goto L5;
            boolean r1 = true;
        L7:
            if (this.wasPortrait != r1) goto L9;
        L11:
            this.wasPortrait = r1;
            int r9 = getChildCount();
            getWindowVisibleDisplayFrame(this.rect);
            if (ActionBarLayout.m5444$$Nest$fgetbottomSheetTabs(this.this$0) == null) goto L14;
            ActionBarLayout.m5444$$Nest$fgetbottomSheetTabs(this.this$0).updateCurrentAccount();
        L14:
            int r12 = 0;
        L15:
            if (r12 >= r9) goto L20;
            View r2 = getChildAt(r12);
            if ((r2 instanceof ActionBar) == true) goto L18;
            r12 = r12 + 1;
            goto L15
        L18:
            r2.measure(View.MeasureSpec.makeMeasureSpec((r6 - getPaddingLeft()) - getPaddingRight(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7, 0));
            int r10 = r2.getMeasuredHeight();
        L21:
            int r11 = 0;
        L22:
            if (r11 >= r9) goto L47;
            View r15 = getChildAt(r11);
            if ((r15 instanceof ActionBar) == true) goto L46;
            if ((r15 instanceof BaseFragment.AttachedSheetWindow) == false) goto L36;
            if (this.this$0.getBottomTabsHeight(false) <= 0) goto L30;
        L33:
            int r5 = 0;
        L34:
            measureChildWithMargins(r15, r13, 0, r14, r5);
            goto L46
        L30:
            if (this.isSupportEdgeToEdge == false) goto L33;
            r5 = ActionBarLayout.m5461$$Nest$fgetsystemAndDisplayInsets(this.this$0).bottom;
            goto L34
        L36:
            if (r15.getTag(R.id.sheet_attached_to_fragment_tag) != null) goto L42;
            if (r15.getFitsSystemWindows() == true) goto L42;
            measureChildWithMargins(r15, r13, 0, r14, r10);
        L42:
            if (this.isSupportEdgeToEdge == false) goto L44;
            int r52 = ActionBarLayout.m5461$$Nest$fgetsystemAndDisplayInsets(this.this$0).bottom;
        L45:
            measureChildWithMargins(r15, r13, 0, r14, r52);
            goto L46
        L44:
            r52 = 0;
        L46:
            r11 = r11 + 1;
            goto L22
        L47:
            setMeasuredDimension(r6, r7);
            return;
        L20:
            r10 = 0;
            goto L21
        L9:
            if (this.this$0.isInPreviewMode() == false) goto L11;
            this.this$0.finishPreviewFragment();
            goto L11
        L5:
            r1 = false;
            goto L7
        }

        @Override // android.view.ViewGroup
        public void onViewAdded(View r1) {
            super.onViewAdded(r1);
            updateChildrenAccessibilityImportance();
        }

        @Override // android.view.ViewGroup
        public void onViewRemoved(View r1) {
            super.onViewRemoved(r1);
            updateChildrenAccessibilityImportance();
        }

        public void setDrawNavigationBar(boolean r2) {
            if (this.drawNavigationBar == r2) goto L6;
            this.drawNavigationBar = r2;
            invalidate();
            return;
        }

        public void setFragmentPanTranslationOffset(int r1) {
            this.fragmentPanTranslationOffset = r1;
            invalidate();
        }

        public void setShouldHandleBottomInsets(EdgeToEdgeSupportMode r2) {
            if (this.edgeToEdgeSupportMode == r2) goto L10;
            this.edgeToEdgeSupportMode = r2;
            if (r2 == EdgeToEdgeSupportMode.NONE) goto L7;
            boolean r22 = true;
        L8:
            this.isSupportEdgeToEdge = r22;
            ViewCompat.requestApplyInsets((View) getParent());
            return;
        L7:
            r22 = false;
            goto L8
        }
    }

    /* renamed from: $r8$lambda$-CP0QG6balmCRm1C4fi7uEVVTe4, reason: not valid java name */
    public static /* synthetic */ void m5430$r8$lambda$CP0QG6balmCRm1C4fi7uEVVTe4(ActionBarLayout r0) {
        r0.lambda$removeAllFragments$18();
    }

    public static /* synthetic */ void $r8$lambda$2FQUc0QdVkB9Visy0Jd3azI9whk(ActionBarLayout r0, View r1, float r2, float r3, DynamicAnimation r4, float r5, float r6) {
        r0.lambda$expandPreviewFragment$13(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$2tDoNto02bU6FMEnNjnrzUviNTY(ActionBarLayout r0, BaseFragment r1) {
        r0.lambda$closeLastFragment$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$60VZcu2qGQpUK8f5zryyWeMRrcs(ActionBarLayout r0, BaseFragment r1, BaseFragment r2) {
        r0.lambda$closeLastFragment$15(r1, r2);
    }

    /* renamed from: $r8$lambda$7hRm_whDvhhf-gLAnGphhnNo56k, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m5431$r8$lambda$7hRm_whDvhhfgLAnGphhnNo56k(ActionBarLayout r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ADZDqkw6T3IiZToPuNCgQSjk01E(ActionBarLayout r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$animateBackEndAnimation$3(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Bv-lhhQrzWgpPiFZFhhdUOdYyZw, reason: not valid java name */
    public static /* synthetic */ void m5432$r8$lambda$BvlhhQrzWgpPiFZFhhdUOdYyZw(ActionBarLayout r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$startLayoutAnimation$9(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$IPs-Bq26nHLC-UK-Q6btJp2gIts, reason: not valid java name */
    public static /* synthetic */ void m5433$r8$lambda$IPsBq26nHLCUKQ6btJp2gIts(ActionBarLayout r0, BaseFragment r1, BaseFragment r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        r0.lambda$expandPreviewFragment$14(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$N6NJzIl5Gxe-lHjJ7XxUW-6i8ek, reason: not valid java name */
    public static /* synthetic */ void m5434$r8$lambda$N6NJzIl5GxelHjJ7XxUW6i8ek(ActionBarLayout r0) {
        r0.lambda$presentFragment$12();
    }

    public static /* synthetic */ void $r8$lambda$R0lo6n_0oBRSACVZVygQpTbOlUM(ActionBarLayout r0, float r1, ValueAnimator r2) {
        r0.lambda$animatePredictiveBackEndAnimation$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$SRhR532OMLzV5YB07qSNrnK1PaQ(ActionBarLayout r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$onTouchEvent$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Tr3LdGf9g1Wom28s8er_m8aVeMU(ActionBarLayout r0, boolean r1, ActionBarPopupWindow.ActionBarPopupWindowLayout r2, boolean r3, BaseFragment r4, BaseFragment r5) {
        r0.lambda$presentFragment$11(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UZtW8bjXy06Ne93FJ19y6UCZLDY(ActionBarLayout r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$animatePredictiveBackEndAnimation$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$XUVL6fVbvt2rSHSJZM27c0L0ZWM(ActionBarLayout r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$onTouchEvent$1(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$YRTybSgFk_AXjuZ-DvRuF3L8MWw, reason: not valid java name */
    public static /* synthetic */ void m5435$r8$lambda$YRTybSgFk_AXjuZDvRuF3L8MWw(ActionBarLayout r0) {
        r0.lambda$new$21();
    }

    /* renamed from: $r8$lambda$ZK-21WOEQGOgweZxmEMba7XCoGw, reason: not valid java name */
    public static /* synthetic */ void m5436$r8$lambda$ZK21WOEQGOgweZxmEMba7XCoGw(ActionBarLayout r0, int r1, INavigationLayout.ThemeAnimationSettings r2, Runnable r3) {
        r0.lambda$animateThemedValues$19(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$_e6t_fL8pk0l9F9IFRH9Ho6AsjQ(ActionBarLayout r0) {
        r0.lambda$closeLastFragment$16();
    }

    public static /* synthetic */ void $r8$lambda$hGBpCxypbtszOn40lhVss2rpg4c(ActionBarLayout r0, boolean r1, DynamicAnimation r2, float r3, float r4) {
        r0.lambda$startLayoutAnimation$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$oBanL9mfcq65LqUbtmY138bGQjE(Runnable r0) {
        AndroidUtilities.runOnUIThread(r0);
    }

    /* renamed from: $r8$lambda$u-u7i2oc2QvApLQEA0NgbXMG_PA, reason: not valid java name */
    public static /* synthetic */ void m5437$r8$lambda$uu7i2oc2QvApLQEA0NgbXMG_PA(ActionBarLayout r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$animateBackEndAnimation$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$uYE0g1BeALyXJH8qSFKqqZ1GTd8(BaseFragment r1, BaseFragment r2) {
        if (r1 == null) goto L5;
        r1.onTransitionAnimationEnd(false, false);
    L5:
        r2.onTransitionAnimationEnd(true, false);
        r2.onBecomeFullyVisible();
    }

    /* renamed from: $r8$lambda$vUd1HCvvwXoBb-J9aduHiPMw9xs, reason: not valid java name */
    public static /* synthetic */ void m5438$r8$lambda$vUd1HCvvwXoBbJ9aduHiPMw9xs(ActionBarLayout r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$startLayoutAnimation$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$veGqOLEGpzYjUNxBVyv_PVAe1x0(ActionBarLayout r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$startLayoutAnimation$8(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetanimateEndColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5439$$Nest$fgetanimateEndColors(ActionBarLayout r0) {
        return r0.animateEndColors;
    }

    /* renamed from: -$$Nest$fgetanimateStartColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5440$$Nest$fgetanimateStartColors(ActionBarLayout r0) {
        return r0.animateStartColors;
    }

    /* renamed from: -$$Nest$fgetanimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5441$$Nest$fgetanimationProgress(ActionBarLayout r0) {
        return r0.animationProgress;
    }

    /* renamed from: -$$Nest$fgetanimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5442$$Nest$fgetanimationRunnable(ActionBarLayout r0) {
        return r0.animationRunnable;
    }

    /* renamed from: -$$Nest$fgetaospTransitionDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5443$$Nest$fgetaospTransitionDuration(ActionBarLayout r0) {
        return r0.aospTransitionDuration;
    }

    /* renamed from: -$$Nest$fgetbottomSheetTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomSheetTabs m5444$$Nest$fgetbottomSheetTabs(ActionBarLayout r0) {
        return r0.bottomSheetTabs;
    }

    /* renamed from: -$$Nest$fgetdecelerateInterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DecelerateInterpolator m5445$$Nest$fgetdecelerateInterpolator(ActionBarLayout r0) {
        return r0.decelerateInterpolator;
    }

    /* renamed from: -$$Nest$fgetdelayedAnimationResumed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5446$$Nest$fgetdelayedAnimationResumed(ActionBarLayout r0) {
        return r0.delayedAnimationResumed;
    }

    /* renamed from: -$$Nest$fgetdelayedOpenAnimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5447$$Nest$fgetdelayedOpenAnimationRunnable(ActionBarLayout r0) {
        return r0.delayedOpenAnimationRunnable;
    }

    /* renamed from: -$$Nest$fgetdrawerLayoutContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DrawerLayoutContainer m5448$$Nest$fgetdrawerLayoutContainer(ActionBarLayout r0) {
        return r0.drawerLayoutContainer;
    }

    /* renamed from: -$$Nest$fgetfragmentsStack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m5449$$Nest$fgetfragmentsStack(ActionBarLayout r0) {
        return r0.fragmentsStack;
    }

    /* renamed from: -$$Nest$fgethasSheetsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m5450$$Nest$fgethasSheetsAnimator(ActionBarLayout r0) {
        return r0.hasSheetsAnimator;
    }

    /* renamed from: -$$Nest$fgetinPreviewMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5451$$Nest$fgetinPreviewMode(ActionBarLayout r0) {
        return r0.inPreviewMode;
    }

    /* renamed from: -$$Nest$fgetisLayersLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5452$$Nest$fgetisLayersLayout(ActionBarLayout r0) {
        return r0.isLayersLayout;
    }

    /* renamed from: -$$Nest$fgetlastFrameTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5453$$Nest$fgetlastFrameTime(ActionBarLayout r2) {
        return r2.lastFrameTime;
    }

    /* renamed from: -$$Nest$fgetnewFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m5454$$Nest$fgetnewFragment(ActionBarLayout r0) {
        return r0.newFragment;
    }

    /* renamed from: -$$Nest$fgetoldFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m5455$$Nest$fgetoldFragment(ActionBarLayout r0) {
        return r0.oldFragment;
    }

    /* renamed from: -$$Nest$fgetovershootInterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OvershootInterpolator m5456$$Nest$fgetovershootInterpolator(ActionBarLayout r0) {
        return r0.overshootInterpolator;
    }

    /* renamed from: -$$Nest$fgetpredictiveBackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PredictiveBackAnimationHelper m5457$$Nest$fgetpredictiveBackAnimation(ActionBarLayout r0) {
        return r0.predictiveBackAnimation;
    }

    /* renamed from: -$$Nest$fgetpreviewBackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorDrawable m5458$$Nest$fgetpreviewBackgroundDrawable(ActionBarLayout r0) {
        return r0.previewBackgroundDrawable;
    }

    /* renamed from: -$$Nest$fgetpreviewMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout m5459$$Nest$fgetpreviewMenu(ActionBarLayout r0) {
        return r0.previewMenu;
    }

    /* renamed from: -$$Nest$fgetsheetFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyBaseFragment m5460$$Nest$fgetsheetFragment(ActionBarLayout r0) {
        return r0.sheetFragment;
    }

    /* renamed from: -$$Nest$fgetsystemAndDisplayInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m5461$$Nest$fgetsystemAndDisplayInsets(ActionBarLayout r0) {
        return r0.systemAndDisplayInsets;
    }

    /* renamed from: -$$Nest$fgetthemeAnimatorDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5462$$Nest$fgetthemeAnimatorDelegate(ActionBarLayout r0) {
        return r0.themeAnimatorDelegate;
    }

    /* renamed from: -$$Nest$fgetthemeAnimatorDescriptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5463$$Nest$fgetthemeAnimatorDescriptions(ActionBarLayout r0) {
        return r0.themeAnimatorDescriptions;
    }

    /* renamed from: -$$Nest$fgetthemeAnimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m5464$$Nest$fgetthemeAnimatorSet(ActionBarLayout r0) {
        return r0.themeAnimatorSet;
    }

    /* renamed from: -$$Nest$fgettransitionAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5465$$Nest$fgettransitionAnimationInProgress(ActionBarLayout r0) {
        return r0.transitionAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgettransitionAnimationPreviewMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5466$$Nest$fgettransitionAnimationPreviewMode(ActionBarLayout r0) {
        return r0.transitionAnimationPreviewMode;
    }

    /* renamed from: -$$Nest$fgetwaitingForKeyboardCloseRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5467$$Nest$fgetwaitingForKeyboardCloseRunnable(ActionBarLayout r0) {
        return r0.waitingForKeyboardCloseRunnable;
    }

    /* renamed from: -$$Nest$fputanimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5468$$Nest$fputanimationProgress(ActionBarLayout r0, float r1) {
        r0.animationProgress = r1;
    }

    /* renamed from: -$$Nest$fputanimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5469$$Nest$fputanimationRunnable(ActionBarLayout r0, Runnable r1) {
        r0.animationRunnable = r1;
    }

    /* renamed from: -$$Nest$fputbackAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5470$$Nest$fputbackAnimator(ActionBarLayout r0, AnimatorSet r1) {
        r0.backAnimator = r1;
    }

    /* renamed from: -$$Nest$fputdelayedOpenAnimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5471$$Nest$fputdelayedOpenAnimationRunnable(ActionBarLayout r0, Runnable r1) {
        r0.delayedOpenAnimationRunnable = r1;
    }

    /* renamed from: -$$Nest$fputlastFrameTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5472$$Nest$fputlastFrameTime(ActionBarLayout r0, long r1) {
        r0.lastFrameTime = r1;
    }

    /* renamed from: -$$Nest$fputpredictiveBackInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5473$$Nest$fputpredictiveBackInProgress(ActionBarLayout r0, boolean r1) {
        r0.predictiveBackInProgress = r1;
    }

    /* renamed from: -$$Nest$fputpresentingFragmentDescriptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5474$$Nest$fputpresentingFragmentDescriptions(ActionBarLayout r0, ArrayList r1) {
        r0.presentingFragmentDescriptions = r1;
    }

    /* renamed from: -$$Nest$fputpreviewOpenAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5475$$Nest$fputpreviewOpenAnimationInProgress(ActionBarLayout r0, boolean r1) {
        r0.previewOpenAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputthemeAnimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5476$$Nest$fputthemeAnimatorSet(ActionBarLayout r0, AnimatorSet r1) {
        r0.themeAnimatorSet = r1;
    }

    /* renamed from: -$$Nest$fputtransitionAnimationStartTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5477$$Nest$fputtransitionAnimationStartTime(ActionBarLayout r0, long r1) {
        r0.transitionAnimationStartTime = r1;
    }

    /* renamed from: -$$Nest$fputwaitingForKeyboardCloseRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5478$$Nest$fputwaitingForKeyboardCloseRunnable(ActionBarLayout r0, Runnable r1) {
        r0.waitingForKeyboardCloseRunnable = r1;
    }

    /* renamed from: -$$Nest$monAnimationEndCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5479$$Nest$monAnimationEndCheck(ActionBarLayout r0, boolean r1) {
        r0.onAnimationEndCheck(r1);
    }

    /* renamed from: -$$Nest$monSlideAnimationEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5480$$Nest$monSlideAnimationEnd(ActionBarLayout r0, boolean r1) {
        r0.onSlideAnimationEnd(r1);
    }

    /* renamed from: -$$Nest$mstartLayoutAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5481$$Nest$mstartLayoutAnimation(ActionBarLayout r0, boolean r1, boolean r2, boolean r3) {
        r0.startLayoutAnimation(r1, r2, r3);
    }

    public ActionBarLayout(Context r5, boolean r6) {
        super(r5);
        this.highlightActionButtons = false;
        this.decelerateInterpolator = new DecelerateInterpolator(1.5f);
        this.overshootInterpolator = new OvershootInterpolator(1.02f);
        this.accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        this.animateStartColors = new ArrayList();
        this.animateEndColors = new ArrayList();
        this.startColorsProvider = new INavigationLayout.StartColorsProvider();
        this.themeAnimatorDescriptions = new ArrayList();
        this.themeAnimatorDelegate = new ArrayList();
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.rect = new Rect();
        this.overrideWidthOffset = -1;
        this.clipPath = new Path();
        this.radii = new float[8];
        this.invalidateRunnable = new ActionBarLayout$$ExternalSyntheticLambda6(this);
        this.relayoutRunnable = new ActionBarLayout$$ExternalSyntheticLambda7(this);
        this.measureSpec = new int[2];
        this.hasSheetsAnimator = new AnimatedFloat(this, 280, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.predictiveBackAnimation = new PredictiveBackAnimationHelper();
        this.predictiveBackRect = new RectF();
        this.openingAnimation = true;
        this.lastActions = new ArrayList();
        this.debugBlackScreenRunnable = new ActionBarLayout$$ExternalSyntheticLambda8(this);
        Insets r0 = Insets.NONE;
        this.systemAndDisplayInsets = r0;
        this.systemAndDisplayAndImeInsets = r0;
        this.parentActivity = (Activity) r5;
        this.main = r6;
        if (scrimPaint != null) goto L5;
        scrimPaint = new Paint();
    L5:
        ViewCompat.setOnApplyWindowInsetsListener(this, new ActionBarLayout$$ExternalSyntheticLambda9(this));
    }

    private void addEndDescriptions(ArrayList<ThemeDescription> r4) {
        if (r4 == null) goto L7;
        int[] r0 = new int[r4.size()];
        this.animateEndColors.add(r0);
        int r3 = r4.size();
        int r1 = 0;
    L5:
        if (r1 >= r3) goto L9;
        r0[r1] = r4.get(r1).getSetColor();
        r1 = r1 + 1;
        goto L5
    L9:
        return;
    }

    private void addStartDescriptions(ArrayList<ThemeDescription> r6) {
        if (r6 == null) goto L12;
        this.themeAnimatorDescriptions.add(r6);
        int[] r0 = new int[r6.size()];
        this.animateStartColors.add(r0);
        int r1 = r6.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L17;
        ThemeDescription r3 = r6.get(r2);
        r0[r2] = r3.getSetColor();
        ThemeDescription.ThemeDescriptionDelegate r32 = r3.setDelegateDisabled();
        if (r32 == null) goto L11;
        if (this.themeAnimatorDelegate.contains(r32) == true) goto L11;
        this.themeAnimatorDelegate.add(r32);
    L11:
        r2 = r2 + 1;
        goto L5
    L17:
        return;
    }

    private float adjustPredictiveBackProgress(float r3) {
        return Utilities.clamp01(r3 * Utilities.clamp(ExteraConfig.getPredictiveBackIntensity(), 2.0f, 0.0f));
    }

    private void animateBackEndAnimation(boolean r15) {
        if (this.fragmentsStack.isEmpty() == true) goto L5;
        List<BaseFragment> r0 = this.fragmentsStack;
        BaseFragment r02 = r0.get(r0.size() - 1);
    L6:
        if (r02 != null) goto L8;
        return;
    L8:
        float r2 = this.containerView.getX();
        if (this.predictiveBackInProgress == false) goto L15;
        if (this.predictiveBackHasProgress == false) goto L15;
        animatePredictiveBackEndAnimation(r15);
        return;
    L15:
        if (ExteraConfig.getSpringSwipeback() == false) goto L28;
        this.springIsLayout = false;
        this.springIsPreview = false;
        this.springIsBack = r15;
        float r03 = 1000.0f;
        float r22 = (r2 / this.containerView.getMeasuredWidth()) * 1000.0f;
        FloatValueHolder r3 = this.springValueHolder;
        if (r3 != null) goto L19;
        this.springValueHolder = new FloatValueHolder(r22);
    L21:
        if (this.currentSpringAnimation != null) goto L23;
        this.currentSpringAnimation = new SpringAnimation(this.springValueHolder);
        SpringForce r23 = new SpringForce();
        this.springForce = r23;
        this.currentSpringAnimation.setSpring(r23);
        this.currentSpringAnimation.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda22(this));
        this.currentSpringAnimation.addEndListener(new ActionBarLayout$$ExternalSyntheticLambda23(this));
    L23:
        SpringForce r24 = this.springForce;
        if (r15 == false) goto L26;
        r03 = 0.0f;
    L26:
        r24.setFinalPosition(r03);
        this.springForce.setStiffness(900.0f);
        this.springForce.setDampingRatio(1.0f);
        this.currentSpringAnimation.setStartVelocity(0.0f);
        this.currentSpringAnimation.start();
        this.animationInProgress = true;
        this.layoutToIgnore = this.containerViewBack;
        return;
    L19:
        r3.setValue(r22);
        goto L21
    L28:
        AnimatorSet r32 = new AnimatorSet();
        boolean r6 = r02.shouldOverrideSlideTransition(false, r15);
        LayoutContainer r7 = this.containerView;
        Property r10 = View.TRANSLATION_X;
        if (r15 == true) goto L45;
        r2 = Math.abs(r7.getMeasuredWidth() - r2);
        int r72 = (int) ((200.0f / this.containerView.getMeasuredWidth()) * r2);
        if (newBackTransitions() == false) goto L33;
        int r11 = 380;
    L34:
        int r73 = Math.max(r72, r11);
        if (r6 == true) goto L57;
        LayoutContainer r62 = this.containerView;
        int r112 = r62.getMeasuredWidth();
        if (this.predictiveBackInProgress == false) goto L39;
        int r12 = AndroidUtilities.dp(56.0f);
    L40:
        float[] r122 = {r112 + r12};
        long r113 = r73;
        r32.playTogether(new Animator[]{ObjectAnimator.ofFloat(r62, r10, r122).setDuration(r113), ObjectAnimator.ofFloat(this, "innerTranslationX", new float[]{this.containerView.getMeasuredWidth()}).setDuration(r113)});
        if (newBackTransitions() == false) goto L57;
        r32.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        if (ExteraConfig.getSpringSwipeback() == false) goto L57;
        r32.play(ObjectAnimator.ofFloat(this.containerViewBack, r10, new float[]{0.0f}).setDuration(r113));
        goto L57
    L39:
        r12 = 0;
    L57:
        Animator r04 = r02.getCustomSlideTransition(false, r15, r2);
        if (r04 == null) goto L60;
        r32.playTogether(new Animator[]{r04});
    L60:
        List<BaseFragment> r05 = this.fragmentsStack;
        BaseFragment r06 = r05.get(r05.size() - 2);
        if (r06 == null) goto L65;
        Animator r07 = r06.getCustomSlideTransition(false, r15, r2);
        if (r07 == null) goto L65;
        r32.playTogether(new Animator[]{r07});
    L65:
        r32.addListener(new AnonymousClass2(this, r15));
        this.backAnimator = r32;
        this.backAnimatorIsBack = r15;
        this.animationInProgress = true;
        this.layoutToIgnore = this.containerViewBack;
        r32.start();
        return;
    L33:
        r11 = 50;
        goto L34
    L45:
        int r74 = (int) ((320.0f / r7.getMeasuredWidth()) * r2);
        if (newBackTransitions() == false) goto L48;
        int r114 = 320;
    L49:
        int r75 = Math.max(r74, r114);
        if (r6 == true) goto L57;
        long r115 = r75;
        r32.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.containerView, r10, new float[]{0.0f}).setDuration(r115), ObjectAnimator.ofFloat(this, "innerTranslationX", new float[]{0.0f}).setDuration(r115)});
        if (newBackTransitions() == false) goto L55;
        r32.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L55:
        if (ExteraConfig.getSpringSwipeback() == false) goto L57;
        r32.play(ObjectAnimator.ofFloat(this.containerViewBack, r10, new float[]{(-this.containerView.getMeasuredWidth()) * 0.35f}).setDuration(r115));
        goto L57
    L48:
        r114 = 120;
        goto L49
    L5:
        r02 = null;
        goto L6
    }

    private void animatePredictiveBackEndAnimation(boolean r7) {
        float r0 = this.predictiveBackAnimation.getProgress();
        float r1 = this.containerView.getAlpha();
        float r2 = this.predictiveBackAnimation.getSlideDistance();
        if (r7 == false) goto L5;
        ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{r0, 0.0f});
        r22.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda24(this, r0, r1));
        r22.setDuration(Math.max(120, (int) (r0 * 220.0f)));
        r22.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L6:
        AnimatorSet r02 = new AnimatorSet();
        r02.playTogether(new Animator[]{r22});
        r02.addListener(new AnonymousClass3(this, r7));
        this.backAnimator = r02;
        this.backAnimatorIsBack = r7;
        this.animationInProgress = true;
        this.layoutToIgnore = this.containerViewBack;
        r02.start();
        return;
    L5:
        this.predictiveBackAnimation.prepareCommit();
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r03.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda25(this, r2));
        r03.setDuration(this.predictiveBackAnimation.getPostCommitDuration());
        r22 = r03;
        goto L6
    }

    private void applyPredictiveBackProgress(float r2, float r3, boolean r4) {
        if (r4 == false) goto L4;
        float r22 = adjustPredictiveBackProgress(r2);
    L6:
        if (r22 <= 0.0f) goto L8;
    L11:
        boolean r0 = true;
    L12:
        this.predictiveBackHasProgress = r0;
        this.predictiveBackY = r3;
        this.predictiveBackAnimation.update(r22, r3);
        this.containerView.setTranslationX(0.0f);
        this.containerViewBack.setTranslationX(0.0f);
        setInnerTranslationX(this.predictiveBackAnimation.getSlideDistance());
        this.containerView.invalidate();
        this.containerViewBack.invalidate();
        return;
    L8:
        if (this.animationInProgress == true) goto L11;
        r0 = false;
        goto L12
    L4:
        r22 = Utilities.clamp01(r2);
        goto L6
    }

    private void applySpringProgress(float r11) {
        float r112 = Utilities.clamp01(r11 / 1000.0f);
        this.animationProgress = r112;
        BaseFragment r1 = null;
        Integer r12 = null;
        if (this.springIsPreview == false) goto L37;
        boolean r0 = this.openingAnimation;
        BaseFragment r6 = this.newFragment;
        if (r0 == false) goto L27;
        if (r6 == null) goto L8;
        r6.onTransitionAnimationProgress(true, r112);
    L8:
        BaseFragment r02 = this.oldFragment;
        if (r02 == null) goto L11;
        r02.onTransitionAnimationProgress(false, r112);
    L11:
        BaseFragment r03 = this.oldFragment;
        if (r03 == null) goto L14;
        Integer r04 = Integer.valueOf(r03.getNavigationBarColor());
    L15:
        BaseFragment r2 = this.newFragment;
        if (r2 == null) goto L19;
        r12 = Integer.valueOf(r2.getNavigationBarColor());
    L19:
        if (this.newFragment == null) goto L22;
        if (r04 == null) goto L22;
        this.newFragment.setNavigationBarColor(ColorUtils.blendARGB(r04.intValue(), r12.intValue(), MathUtils.clamp(4.0f * r112, 0.0f, 1.0f)));
    L22:
        float r05 = MathUtils.clamp(r112, 0.0f, 1.0f);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        float r13 = (r112 * 0.5f) + 0.5f;
        this.containerView.setScaleX(r13);
        this.containerView.setScaleY(r13);
        this.containerView.setAlpha(r05);
        if (this.previewMenu == null) goto L25;
        float r5 = 1.0f - r112;
        this.containerView.setTranslationY(AndroidUtilities.dp(40.0f) * r5);
        this.previewMenu.setTranslationY((-AndroidUtilities.dp(70.0f)) * r5);
        float r113 = (r112 * 0.05f) + 0.95f;
        this.previewMenu.setScaleX(r113);
        this.previewMenu.setScaleY(r113);
    L25:
        this.previewBackgroundDrawable.setAlpha((int) (46.0f * r05));
        Theme.moveUpDrawable.setAlpha((int) (r05 * 255.0f));
        this.containerView.invalidate();
        invalidate();
        return;
    L14:
        r04 = null;
        goto L15
    L27:
        if (r6 == null) goto L29;
        r6.onTransitionAnimationProgress(true, r112);
    L29:
        BaseFragment r06 = this.oldFragment;
        if (r06 == null) goto L32;
        r06.onTransitionAnimationProgress(false, r112);
    L32:
        float r114 = 1.0f - r112;
        float r07 = MathUtils.clamp(r114, 0.0f, 1.0f);
        this.containerViewBack.setTranslationX(0.0f);
        this.containerViewBack.setTranslationY(0.0f);
        float r115 = (r114 * 0.5f) + 0.5f;
        this.containerViewBack.setScaleX(r115);
        this.containerViewBack.setScaleY(r115);
        this.containerViewBack.setAlpha(r07);
        this.previewBackgroundDrawable.setAlpha((int) (46.0f * r07));
        if (this.previewMenu != null) goto L35;
        Theme.moveUpDrawable.setAlpha((int) (r07 * 255.0f));
    L35:
        this.containerView.invalidate();
        invalidate();
        return;
    L37:
        float r08 = (getWidth() - getPaddingLeft()) - getPaddingRight();
        if (this.springIsLayout == false) goto L51;
        BaseFragment r14 = this.newFragment;
        if (r14 == null) goto L42;
        r14.onTransitionAnimationProgress(true, r112);
    L42:
        BaseFragment r15 = this.oldFragment;
        if (r15 == null) goto L46;
        r15.onTransitionAnimationProgress(false, r112);
    L46:
        if (this.openingAnimation == false) goto L49;
        float r22 = (1.0f - r112) * r08;
        this.containerView.setTranslationX(r22);
        this.containerViewBack.setTranslationX(((-r112) * 0.35f) * r08);
        setInnerTranslationX(r22);
        float r116 = AndroidUtilities.lerp(0.85f, 1.0f, r112);
        this.containerView.setScaleX(r116);
        this.containerView.setScaleY(r116);
        return;
    L49:
        float r23 = r112 * r08;
        this.containerViewBack.setTranslationX(r23);
        this.containerView.setTranslationX(((-(1.0f - r112)) * 0.35f) * r08);
        setInnerTranslationX(r23);
        float r117 = AndroidUtilities.lerp(1.0f, 0.85f, r112);
        this.containerViewBack.setScaleX(r117);
        this.containerViewBack.setScaleY(r117);
        return;
    L51:
        this.containerView.setTranslationX(r0.getMeasuredWidth() * r112);
        this.containerViewBack.setTranslationX((-(this.containerView.getMeasuredWidth() - (this.containerView.getMeasuredWidth() * r112))) * 0.35f);
        setInnerTranslationX(this.containerView.getMeasuredWidth() * r112);
        float r09 = AndroidUtilities.lerp(1.0f, 0.85f, r112);
        this.containerView.setScaleX(r09);
        this.containerView.setScaleY(r09);
        this.containerView.setTranslationY(this.springRouteYRatio * Math.max(0.0f, ((this.containerView.getMeasuredHeight() * (1.0f - r09)) / 2.0f) - AndroidUtilities.dp(8.0f)));
        if (this.fragmentsStack.size() <= 1) goto L55;
        r1 = this.fragmentsStack.get(r0.size() - 2);
    L55:
        if (this.springIsBack == true) goto L62;
        BaseFragment r10 = getLastFragment();
        if (r10 == null) goto L59;
        r10.onTransitionAnimationProgress(false, r112);
    L59:
        if (r1 == null) goto L65;
        r1.onTransitionAnimationProgress(true, r112);
        return;
    L65:
        return;
    L62:
        if (r1 == null) goto L66;
        r1.onTransitionAnimationProgress(true, 1.0f - r112);
        return;
    }

    private void attachView(BaseFragment r5) {
        View r0 = r5.fragmentView;
        if (r0 != null) goto L11;
        r0 = r5.performCreateView(this.parentActivity);
        if (r0 == null) goto L15;
        if (r5.isSupportEdgeToEdge() == false) goto L15;
        if (r5.drawEdgeNavigationBar() == false) goto L15;
        ViewCompat.setOnApplyWindowInsetsListener(r0, new ActionBarLayout$$ExternalSyntheticLambda0(r5));
        this.containerView.invalidate();
    L15:
        if (r5.hasOwnBackground == false) goto L17;
    L19:
        this.containerView.addView(r0, LayoutHelper.createFrame(-1, -1.0f));
        this.containerView.setShouldHandleBottomInsets(r5.getEdgeToEdgeSupportMode());
        this.containerView.setDrawNavigationBar(r5.drawEdgeNavigationBar());
        ActionBar r02 = r5.actionBar;
        if (r02 != null) goto L22;
    L30:
        r5.setTitleOverlayTextIfActionBarAttached(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        r5.attachSheets(this.containerView);
        return;
    L22:
        if (r02.shouldAddToContainer() == false) goto L30;
        if (this.removeActionBarExtraHeight == false) goto L26;
        r5.actionBar.setOccupyStatusBar(false);
    L26:
        ViewGroup r03 = (ViewGroup) r5.actionBar.getParent();
        if (r03 == null) goto L29;
        r03.removeView(r5.actionBar);
    L29:
        this.containerView.addView(r5.actionBar);
        goto L30
    L17:
        if (r0.getBackground() != null) goto L19;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        goto L19
    L11:
        ViewGroup r1 = (ViewGroup) r0.getParent();
        if (r1 == null) goto L15;
        r5.onRemoveFromParent();
        r1.removeView(r0);
        goto L15
    }

    private void attachViewTo(BaseFragment r6, int r7) {
        View r0 = r6.fragmentView;
        if (r0 != null) goto L11;
        r0 = r6.performCreateView(this.parentActivity);
        if (r0 == null) goto L15;
        if (r6.isSupportEdgeToEdge() == false) goto L15;
        if (r6.drawEdgeNavigationBar() == false) goto L15;
        ViewCompat.setOnApplyWindowInsetsListener(r0, new ActionBarLayout$$ExternalSyntheticLambda0(r6));
        this.containerView.invalidate();
    L15:
        if (r6.hasOwnBackground == false) goto L17;
    L19:
        LayoutContainer r1 = this.containerView;
        r1.addView(r0, Utilities.clamp(r7, r1.getChildCount(), 0), LayoutHelper.createFrame(-1, -1.0f));
        this.containerView.setShouldHandleBottomInsets(r6.getEdgeToEdgeSupportMode());
        this.containerView.setDrawNavigationBar(r6.drawEdgeNavigationBar());
        ActionBar r72 = r6.actionBar;
        if (r72 != null) goto L22;
    L30:
        r6.setTitleOverlayTextIfActionBarAttached(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        r6.attachSheets(this.containerView);
        return;
    L22:
        if (r72.shouldAddToContainer() == false) goto L30;
        if (this.removeActionBarExtraHeight == false) goto L26;
        r6.actionBar.setOccupyStatusBar(false);
    L26:
        ViewGroup r73 = (ViewGroup) r6.actionBar.getParent();
        if (r73 == null) goto L29;
        r73.removeView(r6.actionBar);
    L29:
        this.containerView.addView(r6.actionBar);
        goto L30
    L17:
        if (r0.getBackground() != null) goto L19;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        goto L19
    L11:
        ViewGroup r12 = (ViewGroup) r0.getParent();
        if (r12 == null) goto L15;
        r6.onRemoveFromParent();
        r12.removeView(r0);
        goto L15
    }

    private void checkNeedRebuild() {
        if (this.rebuildAfterAnimation == false) goto L7;
        rebuildAllFragmentViews(this.rebuildLastAfterAnimation, this.showLastAfterAnimation);
        this.rebuildAfterAnimation = false;
        return;
    L7:
        if (this.animateThemeAfterAnimation == false) goto L13;
        INavigationLayout.ThemeAnimationSettings r0 = new INavigationLayout.ThemeAnimationSettings(this.animateSetThemeAfterAnimation, this.animateSetThemeAccentIdAfterAnimation, this.animateSetThemeNightAfterAnimation, false);
        boolean r2 = this.animateSetThemeAfterAnimationApply;
        if (r2 == true) goto L11;
        r0.applyTrulyTheme = r2;
        r0.applyTheme = r2;
    L11:
        animateThemedValues(r0, null);
        this.animateSetThemeAfterAnimation = null;
        this.animateThemeAfterAnimation = false;
        return;
    }

    private void closeLastFragmentInternalRemoveOld(BaseFragment r2) {
        r2.finishing = true;
        r2.onPause();
        r2.onFragmentDestroy();
        r2.setParentLayout(null);
        this.fragmentsStack.remove(r2);
        this.containerViewBack.setVisibility(4);
        this.containerViewBack.setTranslationY(0.0f);
        bringChildToFront(this.containerView);
        LayoutContainer r22 = this.sheetContainer;
        if (r22 == null) goto L5;
        bringChildToFront(r22);
    L5:
        onFragmentStackChanged("closeLastFragmentInternalRemoveOld");
    }

    private void dispatchApplyWindowInsetsInternal(View r9, WindowInsetsCompat r10) {
        if (this.isLayersLayout == true) goto L5;
        boolean r0 = isRightLayout();
        boolean r3 = true;
        if (isLayersLayout() == true) goto L25;
        if (r0 == true) goto L25;
        if ((getParent() instanceof RelativeLayout) == false) goto L25;
        boolean r2 = true;
    L26:
        Insets r4 = this.systemAndDisplayInsets;
        Insets r5 = this.systemAndDisplayAndImeInsets;
        if ((r9 instanceof BottomSheetTabs) == false) goto L37;
        if (r0 == false) goto L30;
        int r8 = 0;
    L31:
        if (r2 == false) goto L33;
        int r102 = 0;
    L34:
        AndroidUtilities.setViewLayoutMargins(r9, r8, 0, r102, r4.bottom);
        return;
    L33:
        r102 = r4.right;
        goto L34
    L30:
        r8 = r4.left;
        goto L31
    L37:
        if ((r9 instanceof LayoutContainer) == false) goto L72;
        ViewGroup.MarginLayoutParams r6 = (ViewGroup.MarginLayoutParams) r9.getLayoutParams();
        LayoutContainer r62 = (LayoutContainer) r9;
        int r82 = getBottomTabsHeight(false);
        if (r82 <= 0) goto L41;
        int r42 = r4.bottom + r82;
    L43:
        if (LayoutContainer.m5482$$Nest$fgetedgeToEdgeSupportMode(r62) == EdgeToEdgeSupportMode.FULL) goto L46;
        r3 = false;
    L46:
        if (r3 == true) goto L50;
        if (r0 == true) goto L50;
        int r83 = r5.left;
    L51:
        if (r3 == true) goto L55;
        if (r2 == true) goto L55;
        int r7 = r5.right;
    L56:
        if (r3 == false) goto L60;
        if (r0 == true) goto L60;
        int r02 = 0;
    L61:
        if (r3 == false) goto L65;
        if (r2 == true) goto L65;
        int r22 = 0;
    L67:
        if (LayoutContainer.m5482$$Nest$fgetedgeToEdgeSupportMode(r62) != EdgeToEdgeSupportMode.NONE) goto L69;
        r42 = Math.max(r42, r5.bottom);
        ViewCompat.dispatchApplyWindowInsets(r9, WindowInsetsCompat.CONSUMED);
    L70:
        r9.setPadding(r83, 0, r7, r42);
        return;
    L69:
        ViewCompat.dispatchApplyWindowInsets(r9, r10.inset(r02, 0, r22, r42));
    L65:
        r22 = r5.right;
    L60:
        r02 = r5.left;
    L55:
        r7 = 0;
    L50:
        r83 = 0;
        goto L51
    L41:
        r42 = 0;
        goto L43
    L72:
        return;
    L25:
        r2 = false;
        goto L26
    L5:
        if ((r9 instanceof LayoutContainer) == true) goto L7;
    L17:
        ViewCompat.dispatchApplyWindowInsets(r9, WindowInsetsCompat.CONSUMED);
        return;
    L7:
        if (LayoutContainer.m5484$$Nest$fgetisSupportEdgeToEdge((LayoutContainer) r9) == false) goto L17;
        int r103 = r10.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        if ((getParent() instanceof View) == false) goto L11;
        View r03 = (View) getParent();
    L12:
        if (r03 == null) goto L14;
        int r84 = Math.max(0, r03.getHeight() - getBottom());
    L15:
        ViewCompat.dispatchApplyWindowInsets(r9, new WindowInsetsCompat.Builder(WindowInsetsCompat.CONSUMED).setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, Math.max(0, r103 - r84))).build());
        return;
    L14:
        r84 = 0;
        goto L15
    L11:
        r03 = null;
        goto L12
    }

    private void drawAospEdgeExtension(Canvas r6, View r7) {
        if (Build.VERSION.SDK_INT < 29) goto L34;
        float r0 = r7.getTranslationX();
        if (r0 == 0.0f) goto L35;
        boolean r2 = false;
        if (r0 <= 0.0f) goto L11;
        boolean r1 = true;
    L13:
        if (r7 != this.containerView) goto L15;
        r2 = true;
    L15:
        if (r2 == false) goto L17;
        WindowTransitionAnimationHelper.WindowEdgeExtension r3 = this.aospEdgeExtension;
    L18:
        if (r3 != null) goto L24;
        r3 = new WindowTransitionAnimationHelper.WindowEdgeExtension();
        if (r2 == false) goto L22;
        this.aospEdgeExtension = r3;
        goto L24
    L22:
        this.aospEdgeExtensionBack = r3;
    L24:
        if (r3.isCaptured() == false) goto L26;
    L28:
        float r5 = WindowTransitionAnimationHelper.getSlideDistance();
        if (r1 == false) goto L31;
        float r02 = r0 - r5;
    L32:
        r3.draw(r6, r02, r5, r7.getAlpha());
        return;
    L31:
        r02 = r0 + r7.getWidth();
        goto L32
    L26:
        if (r3.capture(r7, r1) == true) goto L28;
        return;
    L17:
        r3 = this.aospEdgeExtensionBack;
        goto L18
    L11:
        r1 = false;
        goto L13
    L35:
        return;
    }

    private void drawPreviewDrawables(Canvas r6, ViewGroup r7) {
        if (r7.getChildAt(0) == null) goto L8;
        this.previewBackgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.previewBackgroundDrawable.draw(r6);
        if (this.previewMenu != null) goto L9;
        int r0 = AndroidUtilities.dp(32.0f);
        int r5 = (getMeasuredWidth() - r0) / 2;
        int r72 = (int) ((r1.getTop() + r7.getTranslationY()) - AndroidUtilities.dp(20.0f));
        Theme.moveUpDrawable.setBounds(r5, r72, r0 + r5, (r0 / 2) + r72);
        Theme.moveUpDrawable.draw(r6);
        return;
    L9:
        return;
    }

    public static View findScrollingChild(ViewGroup r6, float r7, float r8) {
        int r0 = r6.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L24;
        View r2 = r6.getChildAt(r1);
        if (r2.getVisibility() != 0) goto L23;
        Rect r3 = AndroidUtilities.rectTmp2;
        r2.getHitRect(r3);
        if (r3.contains((int) r7, (int) r8) == false) goto L23;
        if (r2.canScrollHorizontally(-1) == true) goto L22;
        if ((r2 instanceof SeekBarView) == true) goto L22;
        if ((r2 instanceof SlideChooseView) == true) goto L22;
        if ((r2 instanceof Slider) == true) goto L22;
        if ((r2 instanceof ViewGroup) == false) goto L23;
        r2 = findScrollingChild((ViewGroup) r2, r7 - r3.left, r8 - r3.top);
        if (r2 == null) goto L23;
    L22:
        return r2;
    L23:
        r1 = r1 + 1;
        goto L3
    L24:
        return null;
    }

    private LayoutContainer getBackgroundView() {
        if (this.transitionAnimationInProgress == false) goto L9;
        if (this.openingAnimation == true) goto L9;
        return this.containerView;
    L9:
        return this.containerViewBack;
    }

    private LayoutContainer getForegroundView() {
        if (this.transitionAnimationInProgress == false) goto L10;
        if (this.openingAnimation == true) goto L10;
        return this.containerViewBack;
    L10:
        return this.containerView;
    }

    private float getPredictiveBackStartCornerRadius() {
        return Math.max(Math.max(this.cachedTopLeftRadius, this.cachedTopRightRadius), Math.max(this.cachedBottomLeftRadius, this.cachedBottomRightRadius));
    }

    private void globallyUpdateColors(ViewGroup r4) {
        if (r4 == null) goto L14;
        int r0 = 0;
    L6:
        if (r0 >= r4.getChildCount()) goto L18;
        KeyEvent.Callback r1 = r4.getChildAt(r0);
        if ((r1 instanceof Theme.Colorable) == false) goto L11;
        ((Theme.Colorable) r1).updateColors();
    L11:
        if ((r1 instanceof ViewGroup) == false) goto L13;
        globallyUpdateColors((ViewGroup) r1);
    L13:
        r0 = r0 + 1;
        goto L6
    L18:
        return;
    }

    private /* synthetic */ void lambda$animateBackEndAnimation$2(DynamicAnimation r1, float r2, float r3) {
        applySpringProgress(r2);
    }

    private /* synthetic */ void lambda$animateBackEndAnimation$3(DynamicAnimation r1, boolean r2, float r3, float r4) {
        if (this.springIsLayout == false) goto L6;
        onAnimationEndCheck(false);
        setInnerTranslationX(0.0f);
        return;
    L6:
        this.predictiveBackInProgress = false;
        onSlideAnimationEnd(this.springIsBack);
    }

    private /* synthetic */ void lambda$animatePredictiveBackEndAnimation$4(float r5, float r6, ValueAnimator r7) {
        float r0 = ((Float) r7.getAnimatedValue()).floatValue();
        applyPredictiveBackProgress(r0, this.predictiveBackY, false);
        PredictiveBackAnimationHelper r1 = this.predictiveBackAnimation;
        float r2 = 0.0f;
        if (r5 == 0.0f) goto L6;
        r2 = r0 / r5;
    L6:
        r1.setScrimAlphaMultiplier(r2);
        if (this.containerView.getAlpha() == 1.0f) goto L10;
        this.containerView.setAlpha(r6 + ((1.0f - r6) * r7.getAnimatedFraction()));
        return;
    }

    private /* synthetic */ void lambda$animatePredictiveBackEndAnimation$5(float r3, ValueAnimator r4) {
        this.predictiveBackAnimation.updateCommitProgress(((Float) r4.getAnimatedValue()).floatValue());
        this.containerView.setAlpha(this.predictiveBackAnimation.getClosingAlpha());
        setInnerTranslationX(r3 + ((this.containerView.getMeasuredWidth() - r3) * r4.getAnimatedFraction()));
        this.containerView.invalidate();
        this.containerViewBack.invalidate();
    }

    private /* synthetic */ void lambda$animateThemedValues$19(int r8, INavigationLayout.ThemeAnimationSettings r9, Runnable r10) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= r8) goto L40;
        if (r1 != 0) goto L8;
        BaseFragment r3 = getLastFragment();
    L15:
        if (r3 == null) goto L39;
        if (r9.resourcesProvider != null) goto L19;
    L22:
        ArrayList<ThemeDescription> r22 = r3.getThemeDescriptions();
        addStartDescriptions(r22);
        Dialog r5 = r3.visibleDialog;
        if ((r5 instanceof BottomSheet) == false) goto L26;
        addStartDescriptions(((BottomSheet) r5).getThemeDescriptions());
    L28:
        if (r1 != 0) goto L32;
        Runnable r52 = r9.afterStartDescriptionsAddedRunnable;
        if (r52 == null) goto L32;
        r52.run();
    L32:
        addEndDescriptions(r22);
        Dialog r23 = r3.visibleDialog;
        if ((r23 instanceof BottomSheet) == false) goto L36;
        addEndDescriptions(((BottomSheet) r23).getThemeDescriptions());
    L38:
        r2 = true;
        goto L39
    L36:
        if ((r23 instanceof AlertDialog) == false) goto L38;
        addEndDescriptions(((AlertDialog) r23).getThemeDescriptions());
        goto L38
    L26:
        if ((r5 instanceof AlertDialog) == false) goto L28;
        addStartDescriptions(((AlertDialog) r5).getThemeDescriptions());
        goto L28
    L19:
        if (this.messageDrawableOutStart != null) goto L21;
        MessageDrawable r24 = new MessageDrawable(0, true, false, this.startColorsProvider);
        this.messageDrawableOutStart = r24;
        r24.isCrossfadeBackground = true;
        MessageDrawable r25 = new MessageDrawable(1, true, false, this.startColorsProvider);
        this.messageDrawableOutMediaStart = r25;
        r25.isCrossfadeBackground = true;
    L21:
        this.startColorsProvider.saveColors(r9.resourcesProvider);
    L39:
        r1 = r1 + 1;
        goto L4
    L8:
        if (this.inPreviewMode == true) goto L12;
        if (this.transitionAnimationPreviewMode == false) goto L39;
    L12:
        if (this.fragmentsStack.size() <= 1) goto L39;
        List<BaseFragment> r53 = this.fragmentsStack;
        r3 = r53.get(r53.size() - 2);
        goto L15
    L40:
        if (r2 == true) goto L42;
    L68:
        if (r10 == null) goto L78;
        r10.run();
        return;
    L78:
        return;
    L42:
        if (r9.onlyTopFragment == true) goto L54;
        int r82 = this.fragmentsStack.size();
        if (this.inPreviewMode == false) goto L46;
    L49:
        int r12 = 2;
    L50:
        int r83 = r82 - r12;
        int r13 = 0;
    L51:
        if (r13 >= r83) goto L54;
        BaseFragment r26 = this.fragmentsStack.get(r13);
        r26.clearViews();
        r26.setParentLayout(this);
        r13 = r13 + 1;
        goto L51
    L46:
        if (this.transitionAnimationPreviewMode == true) goto L49;
        r12 = 1;
    L54:
        if (r9.instant == false) goto L61;
        setThemeAnimationValue(1.0f);
        this.themeAnimatorDescriptions.clear();
        this.animateStartColors.clear();
        this.animateEndColors.clear();
        this.themeAnimatorDelegate.clear();
        this.presentingFragmentDescriptions = null;
        this.animationProgressListener = null;
        Runnable r7 = r9.afterAnimationRunnable;
        if (r7 == null) goto L58;
        r7.run();
    L58:
        if (r10 == null) goto L77;
        r10.run();
        return;
    L77:
        return;
    L61:
        Theme.setAnimatingColor(true);
        setThemeAnimationValue(0.0f);
        Runnable r14 = r9.beforeAnimationRunnable;
        if (r14 == null) goto L64;
        r14.run();
    L64:
        INavigationLayout.ThemeAnimationSettings.onAnimationProgress r15 = r9.animationProgress;
        this.animationProgressListener = r15;
        if (r15 == null) goto L67;
        r15.setProgress(0.0f);
    L67:
        this.notificationsLocker.lock();
        AnimatorSet r84 = new AnimatorSet();
        this.themeAnimatorSet = r84;
        r84.addListener(new AnonymousClass13(this, r9));
        this.themeAnimatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "themeAnimationValue", new float[]{0.0f, 1.0f})});
        this.themeAnimatorSet.setDuration(r9.duration);
        this.themeAnimatorSet.start();
        goto L68
    }

    private /* synthetic */ void lambda$closeLastFragment$15(BaseFragment r4, BaseFragment r5) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.previewMenu;
        if (r0 == null) goto L8;
        ViewGroup r02 = (ViewGroup) r0.getParent();
        if (r02 == null) goto L8;
        r02.removeView(this.previewMenu);
    L8:
        if (this.inPreviewMode == false) goto L10;
    L13:
        resetViewProperties(this.containerViewBack);
        this.inPreviewMode = false;
        this.previewMenu = null;
        this.transitionAnimationPreviewMode = false;
    L14:
        closeLastFragmentInternalRemoveOld(r4);
        r4.setRemovingFromStack(false);
        r4.onTransitionAnimationEnd(false, true);
        r5.onTransitionAnimationEnd(true, true);
        r5.onBecomeFullyVisible();
        return;
    L10:
        if (this.transitionAnimationPreviewMode == true) goto L13;
        this.containerViewBack.setTranslationX(0.0f);
        this.containerViewBack.setScaleX(1.0f);
        this.containerViewBack.setScaleY(1.0f);
        goto L14
    }

    private /* synthetic */ void lambda$closeLastFragment$16() {
        onAnimationEndCheck(false);
    }

    private /* synthetic */ void lambda$closeLastFragment$17(BaseFragment r2) {
        removeFragmentFromStackInternal(r2, false);
        setVisibility(8);
        View r1 = this.backgroundView;
        if (r1 == null) goto L6;
        r1.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$expandPreviewFragment$13(View r6, float r7, float r8, DynamicAnimation r9, float r10, float r11) {
        float r102 = r10 / 1000.0f;
        float r92 = this.rect.width() / Math.max(1, r6.getWidth());
        float r112 = this.rect.height() / Math.max(1, r6.getHeight());
        float r1 = AndroidUtilities.lerp(r92, 1.0f, r102);
        float r2 = AndroidUtilities.lerp(r112, 1.0f, r102);
        Rect r3 = this.rect;
        float r4 = r3.left - (r3.centerX() * (1.0f - r92));
        Rect r93 = this.rect;
        r6.setPivotX(this.rect.centerX());
        r6.setPivotY(this.rect.centerY());
        r6.setScaleX(r1);
        r6.setScaleY(r2);
        r6.setTranslationX(AndroidUtilities.lerp(r4, 0.0f, r102));
        r6.setTranslationY(AndroidUtilities.lerp(r93.top - (r93.centerY() * (1.0f - r112)), 0.0f, r102));
        this.containerView.setTranslationY(AndroidUtilities.lerp(r7, 0.0f, r102));
        ActionBarPopupWindow.ActionBarPopupWindowLayout r62 = this.previewMenu;
        if (r62 == null) goto L6;
        r62.setTranslationY(AndroidUtilities.lerp(r8, getHeight(), r102));
        return;
    }

    private /* synthetic */ void lambda$expandPreviewFragment$14(BaseFragment r1, BaseFragment r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        presentFragmentInternalRemoveOld(false, r1);
        this.previewOpenAnimationInProgress = false;
        r2.onPreviewOpenAnimationEnd();
    }

    private /* synthetic */ void lambda$new$21() {
        if (this.attached == true) goto L5;
        return;
    L5:
        if (getLastFragment() != null) goto L7;
        return;
    L7:
        if (this.containerView.getChildCount() == 0) goto L9;
        return;
    L9:
        if (BuildVars.DEBUG_VERSION == false) goto L11;
        FileLog.e(new RuntimeException(TextUtils.join(", ", this.lastActions)));
    L11:
        rebuildAllFragmentViews(true, true);
    }

    private /* synthetic */ void lambda$onTouchEvent$0(DynamicAnimation r1, float r2, float r3) {
        applySpringProgress(r2);
    }

    private /* synthetic */ void lambda$onTouchEvent$1(DynamicAnimation r1, boolean r2, float r3, float r4) {
        if (this.springIsLayout == false) goto L6;
        onAnimationEndCheck(false);
        setInnerTranslationX(0.0f);
        return;
    L6:
        this.predictiveBackInProgress = false;
        onSlideAnimationEnd(this.springIsBack);
    }

    private /* synthetic */ void lambda$presentFragment$11(boolean r3, ActionBarPopupWindow.ActionBarPopupWindowLayout r4, boolean r5, BaseFragment r6, BaseFragment r7) {
        if (r3 == false) goto L5;
        this.inPreviewMode = true;
        this.previewMenu = r4;
        this.transitionAnimationPreviewMode = false;
    L6:
        resetViewProperties(this.containerView);
        if (r6 == null) goto L9;
        r6.onTransitionAnimationEnd(false, false);
    L9:
        r7.onTransitionAnimationEnd(true, false);
        r7.onBecomeFullyVisible();
        return;
    L5:
        presentFragmentInternalRemoveOld(r5, r6);
        goto L6
    }

    private /* synthetic */ void lambda$presentFragment$12() {
        onAnimationEndCheck(false);
    }

    private /* synthetic */ void lambda$removeAllFragments$18() {
        this.backgroundView.setVisibility(8);
    }

    private /* synthetic */ void lambda$startLayoutAnimation$6(boolean r4, DynamicAnimation r5, float r6, float r7) {
        float r62 = r6 / 1000.0f;
        this.animationProgress = r62;
        BaseFragment r52 = this.newFragment;
        if (r52 == null) goto L5;
        r52.onTransitionAnimationProgress(true, r62);
    L5:
        BaseFragment r53 = this.oldFragment;
        if (r53 == null) goto L8;
        r53.onTransitionAnimationProgress(false, this.animationProgress);
    L8:
        BaseFragment r54 = this.oldFragment;
        Integer r63 = null;
        if (r54 == null) goto L11;
        Integer r55 = Integer.valueOf(r54.getNavigationBarColor());
    L12:
        BaseFragment r72 = this.newFragment;
        if (r72 == null) goto L16;
        r63 = Integer.valueOf(r72.getNavigationBarColor());
    L16:
        if (this.newFragment == null) goto L19;
        if (r55 == null) goto L19;
        this.newFragment.setNavigationBarColor(ColorUtils.blendARGB(r55.intValue(), r63.intValue(), MathUtils.clamp(this.animationProgress * 4.0f, 0.0f, 1.0f)));
    L19:
        float r56 = this.animationProgress;
        if (r4 == false) goto L26;
        float r42 = MathUtils.clamp(r56, 0.0f, 1.0f);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        float r57 = (this.animationProgress * 0.5f) + 0.5f;
        this.containerView.setScaleX(r57);
        this.containerView.setScaleY(r57);
        this.containerView.setAlpha(r42);
        if (this.previewMenu == null) goto L24;
        this.containerView.setTranslationY(AndroidUtilities.dp(40.0f) * (1.0f - this.animationProgress));
        this.previewMenu.setTranslationY((-AndroidUtilities.dp(70.0f)) * (1.0f - this.animationProgress));
        this.previewMenu.setScaleX((this.animationProgress * 0.05f) + 0.95f);
        this.previewMenu.setScaleY((this.animationProgress * 0.05f) + 0.95f);
    L24:
        this.previewBackgroundDrawable.setAlpha((int) (46.0f * r42));
        Theme.moveUpDrawable.setAlpha((int) (r42 * 255.0f));
        this.containerView.invalidate();
        invalidate();
        return;
    L26:
        float r43 = MathUtils.clamp(1.0f - r56, 0.0f, 1.0f);
        this.containerViewBack.setTranslationX(0.0f);
        this.containerViewBack.setTranslationY(0.0f);
        float r0 = ((1.0f - this.animationProgress) * 0.5f) + 0.5f;
        this.containerViewBack.setScaleX(r0);
        this.containerViewBack.setScaleY(r0);
        this.containerViewBack.setAlpha(r43);
        this.previewBackgroundDrawable.setAlpha((int) (46.0f * r43));
        if (this.previewMenu != null) goto L29;
        Theme.moveUpDrawable.setAlpha((int) (r43 * 255.0f));
    L29:
        this.containerView.invalidate();
        invalidate();
        return;
    L11:
        r55 = null;
        goto L12
    }

    private /* synthetic */ void lambda$startLayoutAnimation$7(DynamicAnimation r1, boolean r2, float r3, float r4) {
        onAnimationEndCheck(false);
        setInnerTranslationX(0.0f);
    }

    private /* synthetic */ void lambda$startLayoutAnimation$8(DynamicAnimation r1, float r2, float r3) {
        applySpringProgress(r2);
    }

    private /* synthetic */ void lambda$startLayoutAnimation$9(DynamicAnimation r1, boolean r2, float r3, float r4) {
        if (this.springIsLayout == false) goto L6;
        onAnimationEndCheck(false);
        setInnerTranslationX(0.0f);
        return;
    L6:
        this.predictiveBackInProgress = false;
        onSlideAnimationEnd(this.springIsBack);
    }

    private boolean newBackTransitions() {
        if (this.predictiveBackInProgress == true) goto L5;
        return false;
    L5:
        if (this.predictiveBackHasProgress == false) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void observeDebugItemsFromView(List<FloatingDebugController.DebugItem> r3, View r4) {
        if ((r4 instanceof FloatingDebugProvider) == false) goto L6;
        r3.addAll(((FloatingDebugProvider) r4).onGetDebugItems());
    L6:
        if ((r4 instanceof ViewGroup) == false) goto L11;
        ViewGroup r42 = (ViewGroup) r4;
        int r0 = 0;
    L9:
        if (r0 >= r42.getChildCount()) goto L13;
        observeDebugItemsFromView(r3, r42.getChildAt(r0));
        r0 = r0 + 1;
        goto L9
    L13:
        return;
    }

    private void onAnimationEndCheck(boolean r3) {
        onCloseAnimationEnd();
        onOpenAnimationEnd();
        Runnable r0 = this.waitingForKeyboardCloseRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.waitingForKeyboardCloseRunnable = null;
    L5:
        AnimatorSet r02 = this.currentAnimation;
        if (r02 == null) goto L10;
        if (r3 == false) goto L9;
        r02.cancel();
    L9:
        this.currentAnimation = null;
    L10:
        SpringAnimation r03 = this.currentSpringAnimation;
        if (r03 == null) goto L15;
        if (r3 == false) goto L14;
        r03.cancel();
    L14:
        this.currentSpringAnimation = null;
    L15:
        Runnable r32 = this.animationRunnable;
        if (r32 == null) goto L18;
        AndroidUtilities.cancelRunOnUIThread(r32);
        this.animationRunnable = null;
    L18:
        setAlpha(1.0f);
        resetViewProperties(this.containerView);
        resetViewProperties(this.containerViewBack);
        this.springRouteBackgroundDrawable = null;
        this.aospTransitionBackgroundDrawable = null;
        WindowTransitionAnimationHelper.WindowEdgeExtension r33 = this.aospEdgeExtension;
        if (r33 == null) goto L21;
        r33.release();
    L21:
        WindowTransitionAnimationHelper.WindowEdgeExtension r2 = this.aospEdgeExtensionBack;
        if (r2 == null) goto L25;
        r2.release();
        return;
    }

    private WindowInsetsCompat onApplyWindowInsets(View r6, WindowInsetsCompat r7) {
        int r62 = 0;
        Insets r0 = AndroidUtilities.getDefaultWindowInsets(r7, false);
        Insets r2 = AndroidUtilities.getDefaultWindowInsets(r7, true);
        this.systemAndDisplayInsets = r0;
        this.systemAndDisplayAndImeInsets = r2;
        this.lastWindowInsetsCompat = r7;
        if (Build.VERSION.SDK_INT < 31) goto L23;
        WindowInsets r02 = r7.toWindowInsets();
        if (r02 == null) goto L23;
        RoundedCorner r22 = r02.getRoundedCorner(0);
        RoundedCorner r1 = r02.getRoundedCorner(1);
        RoundedCorner r3 = r02.getRoundedCorner(2);
        RoundedCorner r03 = r02.getRoundedCorner(3);
        float r4 = 0.0f;
        if (r22 != null) goto L9;
        float r23 = 0.0f;
    L10:
        this.cachedTopLeftRadius = r23;
        if (r1 != null) goto L13;
        float r12 = 0.0f;
    L14:
        this.cachedTopRightRadius = r12;
        if (r3 != null) goto L17;
        float r13 = 0.0f;
    L18:
        this.cachedBottomRightRadius = r13;
        if (r03 == null) goto L22;
        r4 = r03.getRadius();
    L22:
        this.cachedBottomLeftRadius = r4;
        goto L23
    L17:
        r13 = r3.getRadius();
        goto L18
    L13:
        r12 = r1.getRadius();
        goto L14
    L9:
        r23 = r22.getRadius();
    L23:
        int r04 = getChildCount();
    L24:
        if (r62 >= r04) goto L27;
        dispatchApplyWindowInsetsInternal(getChildAt(r62), r7);
        r62 = r62 + 1;
        goto L24
    L27:
        return WindowInsetsCompat.CONSUMED;
    }

    private void onCloseAnimationEnd() {
        if (this.transitionAnimationInProgress == true) goto L5;
        return;
    L5:
        if (this.onCloseAnimationEndRunnable == null) goto L15;
        AnimatorSet r0 = this.currentAnimation;
        if (r0 == null) goto L9;
        this.currentAnimation = null;
        r0.cancel();
    L9:
        this.transitionAnimationInProgress = false;
        this.layoutToIgnore = null;
        this.transitionAnimationPreviewMode = false;
        this.transitionAnimationStartTime = 0;
        this.previewFinishInProgress = false;
        this.newFragment = null;
        this.oldFragment = null;
        Runnable r02 = this.onCloseAnimationEndRunnable;
        this.onCloseAnimationEndRunnable = null;
        if (r02 == null) goto L12;
        r02.run();
    L12:
        checkNeedRebuild();
        checkNeedRebuild();
        return;
    }

    private void onFragmentStackChanged(String r2) {
        Runnable r0 = this.onFragmentStackChangedListener;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        ImageLoader.getInstance().onFragmentStackChanged();
        checkBlackScreen(r2);
    }

    private void onOpenAnimationEnd() {
        if (this.transitionAnimationInProgress == false) goto L8;
        Runnable r0 = this.onOpenAnimationEndRunnable;
        if (r0 == null) goto L9;
        this.transitionAnimationInProgress = false;
        this.layoutToIgnore = null;
        this.transitionAnimationPreviewMode = false;
        this.transitionAnimationStartTime = 0;
        this.newFragment = null;
        this.oldFragment = null;
        this.onOpenAnimationEndRunnable = null;
        r0.run();
        checkNeedRebuild();
        return;
    L9:
        return;
    }

    private void onSlideAnimationEnd(boolean r7) {
        List<BaseFragment> r0 = this.fragmentsStack;
        if (r7 == true) goto L16;
        if (r0.size() >= 2) goto L8;
        checkBlackScreen("onSlideAnimationEnd exit");
        return;
    L8:
        List<BaseFragment> r72 = this.fragmentsStack;
        BaseFragment r73 = r72.get(r72.size() - 1);
        r73.prepareFragmentToSlide(true, false);
        r73.onPause();
        r73.onFragmentDestroy();
        r73.setParentLayout(null);
        List<BaseFragment> r74 = this.fragmentsStack;
        r74.remove(r74.size() - 1);
        onFragmentStackChanged("onSlideAnimationEnd");
        LayoutContainer r75 = this.containerView;
        r75.setAlpha(1.0f);
        LayoutContainer r02 = this.containerViewBack;
        this.containerView = r02;
        this.containerViewBack = r75;
        bringChildToFront(r02);
        View r76 = this.sheetContainer;
        if (r76 == null) goto L12;
        bringChildToFront(r76);
    L12:
        if (this.fragmentsStack.size() <= 0) goto L14;
        List<BaseFragment> r77 = this.fragmentsStack;
        BaseFragment r78 = r77.get(r77.size() - 1);
        this.currentActionBar = r78.actionBar;
        r78.onResume();
        r78.onBecomeFullyVisible();
        r78.prepareFragmentToSlide(false, false);
    L14:
        this.layoutToIgnore = this.containerView;
    L31:
        this.containerViewBack.setVisibility(4);
        this.startedTracking = false;
        this.animationInProgress = false;
        this.containerView.setTranslationX(0.0f);
        this.containerViewBack.setTranslationX(0.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        this.containerView.setTranslationY(0.0f);
        this.containerViewBack.setScaleX(1.0f);
        this.containerViewBack.setScaleY(1.0f);
        this.containerViewBack.setTranslationY(0.0f);
        this.containerView.setLayerType(0, null);
        this.predictiveBackAnimation.reset();
        this.predictiveBackBackgroundDrawable = null;
        this.springRouteBackgroundDrawable = null;
        setInnerTranslationX(0.0f);
        return;
    L16:
        if (r0.size() < 2) goto L30;
        List<BaseFragment> r79 = this.fragmentsStack;
        r79.get(r79.size() - 1).prepareFragmentToSlide(true, false);
        List<BaseFragment> r710 = this.fragmentsStack;
        BaseFragment r711 = r710.get(r710.size() - 2);
        r711.prepareFragmentToSlide(false, false);
        r711.onPause();
        View r03 = r711.fragmentView;
        if (r03 == null) goto L22;
        ViewGroup r04 = (ViewGroup) r03.getParent();
        if (r04 == null) goto L22;
        r711.onRemoveFromParent();
        BaseFragment.removeViewFromParent(r04, r711.fragmentView);
    L22:
        ActionBar r05 = r711.actionBar;
        if (r05 != null) goto L25;
    L29:
        r711.detachSheets();
        goto L30
    L25:
        if (r05.shouldAddToContainer() == false) goto L29;
        ViewGroup r06 = (ViewGroup) r711.actionBar.getParent();
        if (r06 == null) goto L29;
        BaseFragment.removeViewFromParent(r06, r711.actionBar);
    L30:
        this.layoutToIgnore = null;
        goto L31
    }

    private void prepareForMoving() {
        this.maybeStartTracking = false;
        this.startedTracking = true;
        LayoutContainer r2 = this.containerViewBack;
        this.layoutToIgnore = r2;
        r2.setVisibility(0);
        this.beginTrackingSent = false;
        List<BaseFragment> r22 = this.fragmentsStack;
        BaseFragment r23 = r22.get(r22.size() - 2);
        View r3 = r23.fragmentView;
        if (r3 != null) goto L11;
        r3 = r23.performCreateView(this.parentActivity);
        if (r3 == null) goto L11;
        if (r23.isSupportEdgeToEdge() == false) goto L11;
        if (r23.drawEdgeNavigationBar() == false) goto L11;
        ViewCompat.setOnApplyWindowInsetsListener(r3, new ActionBarLayout$$ExternalSyntheticLambda0(r23));
        this.containerViewBack.invalidate();
    L11:
        ViewGroup r5 = (ViewGroup) r3.getParent();
        if (r5 == null) goto L14;
        r23.onRemoveFromParent();
        r5.removeView(r3);
    L14:
        this.containerViewBack.addView(r3);
        this.containerViewBack.setShouldHandleBottomInsets(r23.getEdgeToEdgeSupportMode());
        this.containerViewBack.setDrawNavigationBar(r23.drawEdgeNavigationBar());
        FrameLayout.LayoutParams r52 = (FrameLayout.LayoutParams) r3.getLayoutParams();
        r52.width = -1;
        r52.height = -1;
        r52.leftMargin = 0;
        r52.rightMargin = 0;
        r52.bottomMargin = 0;
        r52.topMargin = 0;
        r3.setLayoutParams(r52);
        ActionBar r53 = r23.actionBar;
        if (r53 != null) goto L17;
    L22:
        r23.setTitleOverlayTextIfActionBarAttached(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        r23.attachSheets(this.containerViewBack);
        if (r23.hasOwnBackground == false) goto L25;
    L27:
        r23.onResume();
        if (this.themeAnimatorSet == null) goto L30;
        this.presentingFragmentDescriptions = r23.getThemeDescriptions();
    L30:
        this.containerView.setLayerType(2, null);
        List<BaseFragment> r32 = this.fragmentsStack;
        r32.get(r32.size() - 1).prepareFragmentToSlide(true, true);
        r23.prepareFragmentToSlide(false, true);
        if (ExteraConfig.getSpringSwipeback() == true) goto L33;
        return;
    L33:
        if (this.predictiveBackInProgress == true) goto L37;
        this.springRouteYRatio = 0.0f;
        this.springRouteBackgroundDrawable = PredictiveBackAnimationHelper.getTransitionBackground(this.fragmentsStack, getLastFragment());
        return;
    L37:
        return;
    L25:
        if (r3.getBackground() != null) goto L27;
        r3.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        goto L27
    L17:
        if (r53.shouldAddToContainer() == false) goto L22;
        AndroidUtilities.removeFromParent(r23.actionBar);
        if (this.removeActionBarExtraHeight == false) goto L21;
        r23.actionBar.setOccupyStatusBar(false);
    L21:
        this.containerViewBack.addView(r23.actionBar);
        goto L22
    }

    private void presentFragmentInternalRemoveOld(boolean r2, BaseFragment r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        r3.onBecomeFullyHidden();
        r3.onPause();
        if (r2 == false) goto L7;
        r3.onFragmentDestroy();
        r3.setParentLayout(null);
        this.fragmentsStack.remove(r3);
        onFragmentStackChanged("presentFragmentInternalRemoveOld");
    L28:
        this.containerViewBack.setVisibility(4);
        return;
    L7:
        View r22 = r3.fragmentView;
        if (r22 == null) goto L20;
        ViewGroup r23 = (ViewGroup) r22.getParent();
        if (r23 == null) goto L20;
        r3.onRemoveFromParent();
        BaseFragment.removeViewFromParent(r23, r3.fragmentView);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        r23.removeView(r3.fragmentView);     // Catch: Exception -> L18
    L18:
        e = move-exception;
        FileLog.e(e);
    L20:
        ActionBar r24 = r3.actionBar;
        if (r24 != null) goto L23;
    L27:
        r3.detachSheets();
        goto L28
    L23:
        if (r24.shouldAddToContainer() == false) goto L27;
        ViewGroup r25 = (ViewGroup) r3.actionBar.getParent();
        if (r25 == null) goto L27;
        BaseFragment.removeViewFromParent(r25, r3.actionBar);
        goto L27
    }

    private void removeFragmentFromStackInternal(BaseFragment r4, boolean r5) {
        if (this.fragmentsStack.contains(r4) == true) goto L6;
        return;
    L6:
        if (r5 == false) goto L11;
        List<BaseFragment> r1 = this.fragmentsStack;
        if (r1.get(r1.size() - 1) != r4) goto L11;
        r4.finishFragment();
        return;
    L11:
        List<BaseFragment> r12 = this.fragmentsStack;
        if (r12.get(r12.size() - 1) == r4) goto L14;
    L17:
        r4.onPause();
        r4.onFragmentDestroy();
        r4.setParentLayout(null);
        this.fragmentsStack.remove(r4);
        onFragmentStackChanged("removeFragmentFromStackInternal " + r5);
        return;
    L14:
        if (this.fragmentsStack.size() <= 1) goto L17;
        r4.finishFragment(false);
    }

    private void resetViewProperties(View r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        r1.setAlpha(1.0f);
        r1.setScaleX(1.0f);
        r1.setScaleY(1.0f);
        r1.setTranslationX(0.0f);
        r1.setTranslationY(0.0f);
    }

    private boolean shouldOpenFragmentOverlay(Dialog r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.isShowing() == true) goto L6;
        return false;
    L6:
        if ((r1 instanceof ChatAttachAlert) == false) goto L8;
        return true;
    L8:
        if ((r1 instanceof BotWebViewSheet) == false) goto L15;
        return true;
    L15:
        return false;
    }

    private void startLayoutAnimation(boolean r10, boolean r11, boolean r12) {
        if (r12 == false) goto L5;
    L7:
        boolean r6 = false;
    L8:
        boolean r2 = ExteraConfig.getSpringAnimations();
        if (r11 == false) goto L13;
        this.animationProgress = 0.0f;
        this.lastFrameTime = System.nanoTime() / 1000000;
        if (r6 == false) goto L13;
        this.aospTransitionBackgroundDrawable = PredictiveBackAnimationHelper.getTransitionBackground(this.fragmentsStack, getLastFragment());
        this.aospTransitionDuration = WindowTransitionAnimationHelper.getDuration();
    L13:
        if (r2 == false) goto L42;
        float r112 = 0.6f;
        float r22 = 800.0f;
        if (r12 == false) goto L21;
        SpringAnimation r0 = new SpringAnimation(new FloatValueHolder(0.0f));
        SpringForce r122 = new SpringForce(1000.0f);
        if (r10 == false) goto L19;
        r22 = 650.0f;
    L19:
        SpringAnimation r113 = r0.setSpring(r122.setStiffness(r22).setDampingRatio(0.6f));
        r113.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda18(this, r10));
        r113.addEndListener(new ActionBarLayout$$ExternalSyntheticLambda19(this));
        r113.start();
        return;
    L21:
        this.openingAnimation = r10;
        this.springIsLayout = true;
        this.springIsPreview = r12;
        this.springIsBack = false;
        this.springRouteBackgroundDrawable = PredictiveBackAnimationHelper.getTransitionBackground(this.fragmentsStack, getLastFragment());
        if (r10 == false) goto L24;
        this.containerView.setScaleX(0.85f);
        this.containerView.setScaleY(0.85f);
    L24:
        FloatValueHolder r02 = this.springValueHolder;
        if (r02 != null) goto L27;
        this.springValueHolder = new FloatValueHolder(0.0f);
    L29:
        if (this.currentSpringAnimation != null) goto L31;
        this.currentSpringAnimation = new SpringAnimation(this.springValueHolder);
        SpringForce r03 = new SpringForce();
        this.springForce = r03;
        this.currentSpringAnimation.setSpring(r03);
        this.currentSpringAnimation.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda20(this));
        this.currentSpringAnimation.addEndListener(new ActionBarLayout$$ExternalSyntheticLambda21(this));
    L31:
        this.springForce.setFinalPosition(1000.0f);
        SpringForce r04 = this.springForce;
        if (r12 == false) goto L35;
        if (r10 == false) goto L36;
        r22 = 650.0f;
    L36:
        r04.setStiffness(r22);
        SpringForce r102 = this.springForce;
        if (r12 == true) goto L40;
        r112 = 1.0f;
    L40:
        r102.setDampingRatio(r112);
        this.currentSpringAnimation.setStartVelocity(0.0f);
        this.currentSpringAnimation.start();
        return;
    L35:
        r22 = 900.0f;
        goto L36
    L27:
        r02.setValue(0.0f);
        goto L29
    L42:
        AnonymousClass4 r3 = new AnonymousClass4(this, r11, r6, r12, r10);
        this.animationRunnable = r3;
        AndroidUtilities.runOnUIThread(r3);
        return;
    L5:
        if (ExteraConfig.getAospTransitions() == false) goto L7;
        r6 = true;
        goto L8
    }

    private boolean transitionBlocksNavigation() {
        if (checkTransitionAnimation() == true) goto L7;
        return false;
    L7:
        if (this.inPreviewMode == false) goto L9;
    L15:
        return true;
    L9:
        if (this.previewOpenAnimationInProgress == true) goto L15;
        if (this.startedTracking == true) goto L15;
        onAnimationEndCheck(true);
        return this.transitionAnimationInProgress;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean addFragmentToStack(BaseFragment r7, int r8) {
        INavigationLayout.INavigationLayoutDelegate r0 = this.delegate;
        if (r0 == null) goto L7;
        if (r0.needAddFragmentToStack(r7, this) == true) goto L7;
    L8:
        return false;
    L7:
        if (r7.onFragmentCreate() == false) goto L8;
        if (this.fragmentsStack.contains(r7) == false) goto L12;
        return false;
    L12:
        r7.setParentLayout(this);
        if (r8 == (-1)) goto L21;
        if (r8 == (-2)) goto L21;
        if (r8 != (-3)) goto L19;
        attachViewTo(r7, 0);
        r8 = 0;
    L19:
        this.fragmentsStack.add(r8, r7);
        onFragmentStackChanged("addFragmentToStack");
    L40:
        if (this.useAlphaAnimations == true) goto L44;
        setVisibility(0);
        View r6 = this.backgroundView;
        if (r6 == null) goto L44;
        r6.setVisibility(0);
    L44:
        return true;
    L21:
        if (this.fragmentsStack.isEmpty() == true) goto L35;
        List<BaseFragment> r02 = this.fragmentsStack;
        BaseFragment r03 = r02.get(r02.size() - 1);
        r03.onPause();
        ActionBar r4 = r03.actionBar;
        if (r4 != null) goto L25;
    L29:
        View r42 = r03.fragmentView;
        if (r42 == null) goto L34;
        ViewGroup r43 = (ViewGroup) r42.getParent();
        if (r43 == null) goto L34;
        r03.onRemoveFromParent();
        r43.removeView(r03.fragmentView);
    L34:
        r03.detachSheets();
        goto L35
    L25:
        if (r4.shouldAddToContainer() == false) goto L29;
        ViewGroup r44 = (ViewGroup) r03.actionBar.getParent();
        if (r44 == null) goto L29;
        r44.removeView(r03.actionBar);
    L35:
        this.fragmentsStack.add(r7);
        if (r8 == (-2)) goto L38;
        attachView(r7);
        r7.onResume();
        r7.onTransitionAnimationEnd(false, true);
        r7.onTransitionAnimationEnd(true, true);
        r7.onBecomeFullyVisible();
    L38:
        onFragmentStackChanged("addFragmentToStack " + r8);
        goto L40
    }

    @Override // android.view.ViewGroup
    public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
        super.addView(r1, r2, r3);
        WindowInsetsCompat r22 = this.lastWindowInsetsCompat;
        if (r22 == null) goto L6;
        dispatchApplyWindowInsetsInternal(r1, r22);
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean allowSwipe() {
        EmptyBaseFragment r0 = this.sheetFragment;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.getLastSheet() != null) goto L7;
        return true;
    L7:
        if (this.sheetFragment.getLastSheet().isShown() == false) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void animateThemedValues(INavigationLayout.ThemeAnimationSettings r4, Runnable r5) {
        if (this.transitionAnimationInProgress == false) goto L5;
    L33:
        this.animateThemeAfterAnimation = true;
        this.animateSetThemeAfterAnimation = r4.theme;
        this.animateSetThemeNightAfterAnimation = r4.nightTheme;
        this.animateSetThemeAccentIdAfterAnimation = r4.accentId;
        this.animateSetThemeAfterAnimationApply = r4.applyTrulyTheme;
        if (r5 == null) goto L37;
        r5.run();
        return;
    L37:
        return;
    L5:
        if (this.startedTracking == true) goto L33;
        AnimatorSet r0 = this.themeAnimatorSet;
        if (r0 == null) goto L11;
        r0.cancel();
        this.themeAnimatorSet = null;
    L11:
        if (r4.onlyTopFragment == false) goto L13;
        int r02 = 1;
    L14:
        ActionBarLayout$$ExternalSyntheticLambda13 r2 = new ActionBarLayout$$ExternalSyntheticLambda13(this, r02, r4, r5);
        if (r02 >= 1) goto L17;
    L31:
        r2.run();
        return;
    L17:
        if (r4.applyTheme == false) goto L31;
        if (r4.applyTrulyTheme == false) goto L31;
        int r3 = r4.accentId;
        if (r3 == (-1)) goto L25;
        Theme.ThemeInfo r03 = r4.theme;
        if (r03 == null) goto L25;
        r03.setCurrentAccentId(r3);
        Theme.saveThemeAccents(r4.theme, true, false, true, false);
    L25:
        Theme.ThemeInfo r32 = r4.theme;
        if (r5 != null) goto L29;
        Theme.applyTheme(r32, r4.nightTheme);
        r2.run();
        return;
    L29:
        Theme.applyThemeInBackground(r32, r4.nightTheme, new ActionBarLayout$$ExternalSyntheticLambda14(r2));
        return;
    L13:
        r02 = this.fragmentsStack.size();
        goto L14
    }

    public void bringToFront(int r6) {
        if (this.fragmentsStack.isEmpty() == false) goto L5;
        return;
    L5:
        if ((this.fragmentsStack.size() - 1) == r6) goto L7;
    L9:
        int r1 = 0;
    L10:
        List<BaseFragment> r2 = this.fragmentsStack;
        if (r1 >= r6) goto L25;
        BaseFragment r22 = r2.get(r1);
        ActionBar r3 = r22.actionBar;
        if (r3 != null) goto L15;
    L19:
        View r32 = r22.fragmentView;
        if (r32 == null) goto L24;
        ViewGroup r33 = (ViewGroup) r32.getParent();
        if (r33 == null) goto L24;
        r22.onPause();
        r22.onRemoveFromParent();
        r33.removeView(r22.fragmentView);
    L24:
        r1 = r1 + 1;
        goto L10
    L15:
        if (r3.shouldAddToContainer() == false) goto L19;
        ViewGroup r34 = (ViewGroup) r22.actionBar.getParent();
        if (r34 == null) goto L19;
        r34.removeView(r22.actionBar);
        goto L19
    L25:
        BaseFragment r62 = r2.get(r6);
        r62.setParentLayout(this);
        View r12 = r62.fragmentView;
        if (r12 != null) goto L34;
        r12 = r62.performCreateView(this.parentActivity);
        if (r12 != null) goto L30;
    L37:
        this.containerView.addView(r12, LayoutHelper.createFrame(-1, -1.0f));
        this.containerView.setShouldHandleBottomInsets(r62.getEdgeToEdgeSupportMode());
        this.containerView.setDrawNavigationBar(r62.drawEdgeNavigationBar());
        ActionBar r23 = r62.actionBar;
        if (r23 != null) goto L40;
    L45:
        r62.setTitleOverlayTextIfActionBarAttached(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        r62.attachSheets(this.containerView);
        r62.onResume();
        r62.onBecomeFullyVisible();
        this.currentActionBar = r62.actionBar;
        if (r62.hasOwnBackground == false) goto L48;
        return;
    L48:
        if (r12.getBackground() != null) goto L57;
        r12.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        return;
    L57:
        return;
    L40:
        if (r23.shouldAddToContainer() == false) goto L45;
        if (this.removeActionBarExtraHeight == false) goto L44;
        r62.actionBar.setOccupyStatusBar(false);
    L44:
        AndroidUtilities.removeFromParent(r62.actionBar);
        this.containerView.addView(r62.actionBar);
        goto L45
    L30:
        if (r62.isSupportEdgeToEdge() == false) goto L37;
        if (r62.drawEdgeNavigationBar() == false) goto L37;
        ViewCompat.setOnApplyWindowInsetsListener(r12, new ActionBarLayout$$ExternalSyntheticLambda0(r62));
        this.containerView.invalidate();
        goto L37
    L34:
        ViewGroup r24 = (ViewGroup) r12.getParent();
        if (r24 == null) goto L37;
        r62.onRemoveFromParent();
        r24.removeView(r12);
        goto L37
    L7:
        if (this.fragmentsStack.get(r6).fragmentView == null) goto L9;
    }

    public void checkBlackScreen(String r3) {
        if (BuildVars.DEBUG_VERSION == false) goto L11;
        int r1 = 0;
        this.lastActions.add(0, r3 + " " + this.fragmentsStack.size());
        if (this.lastActions.size() <= 20) goto L11;
        ArrayList<String> r32 = new ArrayList();
    L8:
        if (r1 >= 10) goto L10;
        r32.add(this.lastActions.get(r1));
        r1 = r1 + 1;
        goto L8
    L10:
        this.lastActions = r32;
    L11:
        AndroidUtilities.cancelRunOnUIThread(this.debugBlackScreenRunnable);
        AndroidUtilities.runOnUIThread(this.debugBlackScreenRunnable, 500);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean checkTransitionAnimation() {
        if (this.transitionAnimationPreviewMode == false) goto L7;
        return false;
    L7:
        if (this.transitionAnimationInProgress == false) goto L14;
        if (this.transitionAnimationStartTime >= (System.currentTimeMillis() - 1500)) goto L11;
    L12:
        onAnimationEndCheck(true);
        goto L14
    L11:
        if (this.inPreviewMode == true) goto L12;
    L14:
        return this.transitionAnimationInProgress;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void closeLastFragment(boolean r2) {
        closeLastFragment(r2, false);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void closeLastFragment(boolean r12, boolean r13) {
        BaseFragment r0 = getLastFragment();
        if (r0 != null) goto L5;
    L7:
        INavigationLayout.INavigationLayoutDelegate r02 = this.delegate;
        if (r02 == null) goto L12;
        if (r02.needCloseLastFragment(this) == true) goto L12;
        return;
    L12:
        if (this.onCloseAnimationEndRunnable == null) goto L16;
        if (checkTransitionAnimation() == false) goto L16;
        return;
    L16:
        if (transitionBlocksNavigation() == false) goto L18;
        return;
    L18:
        if (this.fragmentsStack.isEmpty() == false) goto L21;
        return;
    L21:
        if (this.parentActivity.getCurrentFocus() == null) goto L23;
        AndroidUtilities.hideKeyboard(this.parentActivity.getCurrentFocus());
    L23:
        setInnerTranslationX(0.0f);
        if (r13 == false) goto L26;
    L33:
        boolean r122 = false;
    L34:
        List<BaseFragment> r3 = this.fragmentsStack;
        BaseFragment r32 = r3.get(r3.size() - 1);
        AnimatorSet r6 = null;
        if (this.fragmentsStack.size() <= 1) goto L37;
        List<BaseFragment> r4 = this.fragmentsStack;
        BaseFragment r42 = r4.get(r4.size() - 2);
    L39:
        if (r42 == null) goto L122;
        AndroidUtilities.setLightStatusBar(this.parentActivity, r42.isLightStatusBar());
        LayoutContainer r132 = this.containerView;
        this.containerView = this.containerViewBack;
        this.containerViewBack = r132;
        r42.setParentLayout(this);
        View r133 = r42.fragmentView;
        if (r133 != null) goto L50;
        r133 = r42.performCreateView(this.parentActivity);
        if (r133 == null) goto L50;
        if (r42.isSupportEdgeToEdge() == false) goto L50;
        if (r42.drawEdgeNavigationBar() == false) goto L50;
        ViewCompat.setOnApplyWindowInsetsListener(r133, new ActionBarLayout$$ExternalSyntheticLambda0(r42));
        this.containerView.invalidate();
    L50:
        if (this.inPreviewMode == true) goto L67;
        this.containerView.setVisibility(0);
        ViewGroup r5 = (ViewGroup) r133.getParent();
        if (r5 == null) goto L58;
        r42.onRemoveFromParent();
        r5.removeView(r133);     // Catch: Exception -> L56
    L56:
        e = move-exception;
        FileLog.e(e);
    L58:
        this.containerView.addView(r133);
        this.containerView.setShouldHandleBottomInsets(r42.getEdgeToEdgeSupportMode());
        this.containerView.setDrawNavigationBar(r42.drawEdgeNavigationBar());
        FrameLayout.LayoutParams r52 = (FrameLayout.LayoutParams) r133.getLayoutParams();
        r52.width = -1;
        r52.height = -1;
        r52.leftMargin = 0;
        r52.rightMargin = 0;
        r52.bottomMargin = 0;
        r52.topMargin = 0;
        r133.setLayoutParams(r52);
        ActionBar r53 = r42.actionBar;
        if (r53 != null) goto L61;
    L66:
        r42.setTitleOverlayTextIfActionBarAttached(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        r42.attachSheets(this.containerView);
        goto L67
    L61:
        if (r53.shouldAddToContainer() == false) goto L66;
        if (this.removeActionBarExtraHeight == false) goto L65;
        r42.actionBar.setOccupyStatusBar(false);
    L65:
        AndroidUtilities.removeFromParent(r42.actionBar);
        this.containerView.addView(r42.actionBar);
    L67:
        this.newFragment = r42;
        this.oldFragment = r32;
        r42.onTransitionAnimationStart(true, true);
        r32.onTransitionAnimationStart(false, true);
        r42.onResume();
        if (this.themeAnimatorSet == null) goto L70;
        this.presentingFragmentDescriptions = r42.getThemeDescriptions();
    L70:
        this.currentActionBar = r42.actionBar;
        if (r42.hasOwnBackground == false) goto L73;
    L75:
        if (r122 == false) goto L111;
        this.transitionAnimationStartTime = System.currentTimeMillis();
        this.transitionAnimationInProgress = true;
        this.layoutToIgnore = this.containerView;
        r32.setRemovingFromStack(true);
        this.onCloseAnimationEndRunnable = new ActionBarLayout$$ExternalSyntheticLambda3(this, r32, r42);
        if (this.inPreviewMode == false) goto L79;
    L81:
        if (r6 == null) goto L83;
        this.currentAnimation = r6;
        if (Bulletin.getVisibleBulletin() != null) goto L107;
    L109:
        onFragmentStackChanged("closeLastFragment");
    L131:
        r32.onFragmentClosed();
        return;
    L107:
        if (Bulletin.getVisibleBulletin().isShowing() == false) goto L109;
        Bulletin.getVisibleBulletin().hide();
        goto L109
    L83:
        if (this.inPreviewMode == true) goto L90;
        if (this.transitionAnimationPreviewMode == true) goto L90;
        if (ExteraConfig.getAospTransitions() == false) goto L90;
        WindowTransitionAnimationHelper.apply(this.containerView, this.containerViewBack, false, 0.0f);
    L90:
        if (this.inPreviewMode == true) goto L97;
        if (LayoutContainer.m5483$$Nest$fgetisKeyboardVisible(this.containerView) == false) goto L94;
    L95:
        AnonymousClass11 r123 = new AnonymousClass11(this);
        this.waitingForKeyboardCloseRunnable = r123;
        AndroidUtilities.runOnUIThread(r123, 200);
        goto L109
    L94:
        if (LayoutContainer.m5483$$Nest$fgetisKeyboardVisible(this.containerViewBack) == true) goto L95;
    L97:
        if (this.inPreviewMode == false) goto L99;
    L102:
        boolean r124 = true;
    L103:
        startLayoutAnimation(false, true, r124);
        goto L109
    L99:
        if (this.transitionAnimationPreviewMode == true) goto L102;
        r124 = false;
        goto L103
    L79:
        if (this.transitionAnimationPreviewMode == true) goto L81;
        r6 = r32.onCustomTransitionAnimation(false, new ActionBarLayout$$ExternalSyntheticLambda4(this));
        goto L81
    L111:
        if (this.inPreviewMode == false) goto L113;
    L114:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r125 = this.previewMenu;
        if (r125 == null) goto L119;
        ViewGroup r126 = (ViewGroup) r125.getParent();
        if (r126 == null) goto L119;
        r126.removeView(this.previewMenu);
    L119:
        resetViewProperties(this.containerViewBack);
        this.inPreviewMode = false;
        this.previewMenu = null;
        this.transitionAnimationPreviewMode = false;
    L120:
        closeLastFragmentInternalRemoveOld(r32);
        r32.onTransitionAnimationEnd(false, true);
        r42.onTransitionAnimationEnd(true, true);
        r42.onBecomeFullyVisible();
        goto L131
    L113:
        if (this.transitionAnimationPreviewMode == false) goto L120;
    L73:
        if (r133.getBackground() != null) goto L75;
        r133.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        goto L75
    L122:
        if (this.useAlphaAnimations == false) goto L128;
        if (r13 == true) goto L128;
        this.transitionAnimationStartTime = System.currentTimeMillis();
        this.transitionAnimationInProgress = true;
        this.layoutToIgnore = this.containerView;
        this.onCloseAnimationEndRunnable = new ActionBarLayout$$ExternalSyntheticLambda5(this, r32);
        ArrayList r127 = new ArrayList();
        Property r03 = View.ALPHA;
        r127.add(ObjectAnimator.ofFloat(this, r03, new float[]{1.0f, 0.0f}));
        r127.add(ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{1.0f, 0.9f}));
        r127.add(ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{1.0f, 0.9f}));
        View r134 = this.backgroundView;
        if (r134 == null) goto L127;
        r127.add(ObjectAnimator.ofFloat(r134, r03, new float[]{1.0f, 0.0f}));
    L127:
        AnimatorSet r135 = new AnimatorSet();
        this.currentAnimation = r135;
        r135.playTogether(r127);
        this.currentAnimation.setInterpolator(this.accelerateDecelerateInterpolator);
        this.currentAnimation.setDuration(200);
        this.currentAnimation.addListener(new AnonymousClass12(this));
        this.currentAnimation.start();
    L128:
        removeFragmentFromStackInternal(r32, false);
        setVisibility(8);
        View r11 = this.backgroundView;
        if (r11 == null) goto L131;
        r11.setVisibility(8);
        goto L131
    L37:
        r42 = null;
        goto L39
    L26:
        if (this.inPreviewMode == false) goto L28;
    L32:
        r122 = true;
        goto L34
    L28:
        if (this.transitionAnimationPreviewMode == true) goto L32;
        if (r12 == false) goto L33;
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == false) goto L33;
    L5:
        if (r0.closeLastFragment() == false) goto L7;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        if (this.bottomSheetTabs != null) goto L5;
    L7:
        Canvas r2 = r9;
    L8:
        this.withShadow = true;
        if (this.isLayersLayout == false) goto L12;
        r2.save();
        float r92 = AndroidUtilities.dp(24.0f);
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(0.0f, 0.0f, getWidth(), getHeight());
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r0, r92, r92, Path.Direction.CW);
        r2.clipPath(this.clipPath);
    L12:
        if (newBackTransitions() == false) goto L14;
    L19:
        Drawable r93 = this.springRouteBackgroundDrawable;
        if (r93 != null) goto L23;
        r93 = this.predictiveBackBackgroundDrawable;
    L23:
        PredictiveBackAnimationHelper.drawTransitionBackground(r2, r93, getWidth(), getHeight(), this.rect);
    L24:
        super.dispatchDraw(r2);
        if (this.isLayersLayout == false) goto L28;
        r2.restore();
        return;
    L28:
        return;
    L14:
        if (this.springRouteBackgroundDrawable != null) goto L19;
        Drawable r94 = this.aospTransitionBackgroundDrawable;
        if (r94 == null) goto L24;
        PredictiveBackAnimationHelper.drawTransitionBackground(r2, r94, getWidth(), getHeight(), this.rect);
        goto L24
    L5:
        if (getBottomTabsHeight(true) <= 0) goto L7;
        r2 = r9;
        r2.drawRect(0.0f, getHeight() - (this.systemAndDisplayInsets.bottom + this.bottomSheetTabs.getMeasuredHeight()), getWidth(), getHeight(), this.bottomSheetTabs.getBackgroundPaint());
        goto L8
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEventPreIme(KeyEvent r3) {
        if (r3 == null) goto L17;
        if (r3.getKeyCode() != 4) goto L17;
        if (r3.getAction() != 1) goto L17;
        INavigationLayout.INavigationLayoutDelegate r0 = this.delegate;
        if (r0 == null) goto L12;
        if (r0.onPreIme() == false) goto L12;
    L13:
        return true;
    L12:
        if (super.dispatchKeyEventPreIme(r3) == true) goto L13;
        return false;
    L17:
        return super.dispatchKeyEventPreIme(r3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r7) {
        if (r7.getY() <= ((getHeight() - getBottomTabsHeight(true)) - this.systemAndDisplayInsets.bottom)) goto L5;
        boolean r0 = true;
    L6:
        EmptyBaseFragment r3 = this.sheetFragment;
        BaseFragment.AttachedSheet r4 = null;
        if (r3 != null) goto L9;
    L14:
        BaseFragment.AttachedSheet r32 = null;
    L15:
        if (r32 == null) goto L17;
    L25:
        r4 = r32;
    L27:
        if (r4 == null) goto L41;
        if (r7.getAction() != 0) goto L32;
        this.tabsEvents = r0;
    L32:
        if (this.tabsEvents == true) goto L41;
        if (r7.getAction() != 1) goto L36;
    L37:
        this.tabsEvents = false;
    L39:
        return r4.getWindowView().dispatchTouchEvent(r7);
    L36:
        if (r7.getAction() != 3) goto L39;
    L41:
        if (r7.getAction() != 1) goto L43;
    L44:
        this.tabsEvents = false;
    L46:
        return super.dispatchTouchEvent(r7);
    L43:
        if (r7.getAction() != 3) goto L46;
    L17:
        if (getLastFragment() == null) goto L25;
        if (getLastFragment().getLastSheet() == null) goto L25;
        r32 = getLastFragment().getLastSheet();
        if (r32.attachedToParent() == false) goto L27;
        if (r32.getWindowView() != null) goto L25;
    L9:
        if (r3.getLastSheet() == null) goto L14;
        r32 = this.sheetFragment.getLastSheet();
        if (r32.attachedToParent() == false) goto L14;
        if (r32.getWindowView() != null) goto L15;
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r30, View r31, long r32) {
        DrawerLayoutContainer r1 = this.drawerLayoutContainer;
        if (r1 != null) goto L5;
    L21:
        int r10 = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int r11 = ((int) this.innerTranslationX) + getPaddingRight();
        int r12 = getPaddingLeft();
        int r2 = getPaddingLeft() + r10;
        boolean r122 = newBackTransitions();
        if (r31 != getBackgroundView()) goto L31;
        if (r122 == false) goto L25;
    L28:
        r2 = getPaddingLeft() + r10;
    L29:
        int r14 = r12;
        int r15 = r2;
    L33:
        int r13 = r30.save();
        if (r31 == this.bottomSheetTabs) goto L39;
        BottomSheetTabs.ClipTools r16 = this.bottomSheetTabsClip;
        if (r16 == null) goto L38;
        float r162 = 1.0f;
        int r132 = r13;
        r16.clip(r30, this.withShadow, this.isKeyboardVisible, getWidth(), ((int) getY()) + getHeight(), 1.0f);
        this.withShadow = false;
    L41:
        if (this.aospTransitionBackgroundDrawable == null) goto L48;
        if (r31 != this.containerView) goto L45;
    L46:
        drawAospEdgeExtension(r30, r31);
        goto L48
    L45:
        if (r31 == this.containerViewBack) goto L46;
    L48:
        if (r122 == true) goto L50;
    L52:
        float r9 = 0.0f;
        if (this.springRouteBackgroundDrawable != null) goto L55;
    L57:
        Canvas r17 = r30;
    L59:
        if (Build.VERSION.SDK_INT >= 31) goto L61;
    L73:
        boolean r23 = true;
    L100:
        int r22 = r17.save();
        if (r122 == true) goto L103;
    L107:
        r23 = false;
    L109:
        if (isTransitionAnimationInProgress() == true) goto L117;
        if (this.inPreviewMode == true) goto L117;
        if (this.previewOpenAnimationInProgress == true) goto L117;
        if (r23 == true) goto L117;
        r17.clipRect(r14, 0, r15, getHeight());
    L117:
        if (this.inPreviewMode == false) goto L119;
    L120:
        LayoutContainer r3 = this.containerView;
        if (r31 != r3) goto L123;
        drawPreviewDrawables(r17, r3);
    L123:
        boolean r6 = super.drawChild(r30, r31, r32);
        r17.restoreToCount(r22);
        if (r122 == false) goto L126;
    L146:
        r17.restoreToCount(r132);
        return r6;
    L126:
        if (this.springRouteBackgroundDrawable != null) goto L146;
        if (r11 != 0) goto L131;
        int r33 = -1;
        if (this.overrideWidthOffset == (-1)) goto L146;
    L132:
        int r24 = this.overrideWidthOffset;
        if (r24 != r33) goto L137;
        r24 = r10 - r11;
    L137:
        if (r31 != getBackgroundView()) goto L146;
        float r25 = MathUtils.clamp(r24 / r10, 0.0f, 0.8f);
        Paint r34 = scrimPaint;
        if (ExteraConfig.getSpringSwipeback() == false) goto L141;
        int r4 = 102;
    L142:
        r34.setColor(Color.argb((int) (r4 * r25), 0, 0, 0));
        if (this.overrideWidthOffset == (-1)) goto L145;
        r30.drawRect(0.0f, 0.0f, getWidth(), getHeight() * 1.5f, scrimPaint);
        r17 = r30;
        goto L146
    L145:
        r30.drawRect(r14, 0.0f, r15, getHeight() * 1.5f, scrimPaint);
        r17 = r30;
        goto L146
    L141:
        r4 = 120;
        goto L142
    L131:
        r33 = -1;
        goto L132
    L119:
        if (this.transitionAnimationPreviewMode == false) goto L123;
    L103:
        if (r31 == getForegroundView()) goto L109;
        if (r31 != getBackgroundView()) goto L107;
    L61:
        if (this.isSheet == true) goto L73;
        if (r11 != 0) goto L75;
        if (this.overrideWidthOffset != (-1)) goto L75;
        if (r122 == true) goto L75;
        if (r31.getScaleX() != r162) goto L75;
        if (r31.getScaleY() != r162) goto L75;
        if (this.springRouteBackgroundDrawable == null) goto L73;
    L75:
        if (r31 != getForegroundView()) goto L91;
        RectF r26 = AndroidUtilities.rectTmp;
        r23 = true;
        float r5 = r11;
        r26.set(r5, r9, r11 + getWidth(), getHeight());
        if (r122 == false) goto L80;
        this.predictiveBackAnimation.getClosingRect(this.predictiveBackRect);
        float r52 = this.predictiveBackAnimation.getClosingScale();
        RectF r62 = this.predictiveBackRect;
        r17.translate(r62.left, r62.top);
        r17.scale(r52, r52);
        r26.set(r9, r9, r31.getWidth(), r31.getHeight());
        r14 = getPaddingLeft();
        r15 = r10 + getPaddingLeft();
    L84:
        float[] r35 = this.radii;
        float r42 = this.cachedTopLeftRadius;
        r35[1] = r42;
        r35[0] = r42;
        float r43 = this.cachedTopRightRadius;
        r35[3] = r43;
        r35[2] = r43;
        float r44 = this.cachedBottomRightRadius;
        r35[5] = r44;
        r35[4] = r44;
        float r45 = this.cachedBottomLeftRadius;
        r35[7] = r45;
        r35[6] = r45;
        if (r122 == false) goto L88;
        float r36 = this.predictiveBackAnimation.getCornerRadius() / Math.max(0.01f, this.predictiveBackAnimation.getClosingScale());
        float[] r46 = this.radii;
        r46[1] = r36;
        r46[0] = r36;
        r46[3] = r36;
        r46[2] = r36;
        r46[5] = r36;
        r46[4] = r36;
        r46[7] = r36;
        r46[6] = r36;
    L90:
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r26, this.radii, Path.Direction.CW);
        r17.clipPath(this.clipPath);
        goto L100
    L88:
        if (this.isRightLayout == false) goto L90;
        float r37 = Utilities.clamp01(Math.abs(r11) / AndroidUtilities.dpf2(12.0f));
        float[] r47 = this.radii;
        r47[0] = r47[0] * r37;
        r47[1] = r47[1] * r37;
        r47[6] = r47[6] * r37;
        r47[7] = r47[7] * r37;
        goto L90
    L80:
        if (r31.getScaleX() == r162) goto L82;
    L83:
        float r63 = r31.getScaleX();
        float r7 = r31.getScaleY();
        float r53 = r5 + (r31.getWidth() / 2.0f);
        float r48 = (getHeight() / 2.0f) + r31.getTranslationY();
        r26.set(r53 - ((r31.getWidth() / 2.0f) * r63), r48 - ((getHeight() / 2.0f) * r7), r53 + ((getWidth() / 2.0f) * r63), r48 + ((getHeight() / 2.0f) * r7));
        goto L84
    L82:
        if (r31.getScaleY() == r162) goto L84;
    L91:
        r23 = true;
        if (r31 != getBackgroundView()) goto L100;
        if (r122 == false) goto L97;
        this.predictiveBackAnimation.getEnteringRect(this.predictiveBackRect);
        float r27 = this.predictiveBackAnimation.getEnteringScale();
        RectF r38 = this.predictiveBackRect;
        r17.translate(r38.left, r38.top);
        r17.scale(r27, r27);
        RectF r39 = AndroidUtilities.rectTmp;
        r39.set(0.0f, 0.0f, r31.getWidth(), r31.getHeight());
        float r49 = this.predictiveBackAnimation.getCornerRadius() / Math.max(0.01f, r27);
        float[] r28 = this.radii;
        r28[1] = r49;
        r28[0] = r49;
        r28[3] = r49;
        r28[2] = r49;
        r28[5] = r49;
        r28[4] = r49;
        r28[7] = r49;
        r28[6] = r49;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r39, this.radii, Path.Direction.CW);
        r17.clipPath(this.clipPath);
        int r29 = getPaddingLeft();
    L95:
        r15 = r10 + r29;
        goto L100
    L97:
        if (this.springRouteBackgroundDrawable == null) goto L99;
        float r210 = r31.getTranslationX();
        RectF r310 = AndroidUtilities.rectTmp;
        r310.set(r210, 0.0f, r31.getWidth() + r210, getHeight());
        float[] r211 = this.radii;
        float r410 = this.cachedTopLeftRadius;
        r211[1] = r410;
        r211[0] = r410;
        float r411 = this.cachedTopRightRadius;
        r211[3] = r411;
        r211[2] = r411;
        float r412 = this.cachedBottomRightRadius;
        r211[5] = r412;
        r211[4] = r412;
        float r413 = this.cachedBottomLeftRadius;
        r211[7] = r413;
        r211[6] = r413;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r310, this.radii, Path.Direction.CW);
        r17.clipPath(this.clipPath);
        r29 = getPaddingLeft();
        goto L95
    L99:
        r15 = r15 + ((int) Math.max(this.cachedTopLeftRadius, this.cachedBottomLeftRadius));
        goto L100
    L55:
        if (r31 != getForegroundView()) goto L57;
        scrimPaint.setColor(Color.argb((int) (this.predictiveBackAnimation.getScrimAlpha(Theme.isCurrentThemeDark()) * MathUtils.clamp((r10 - r11) / r10, 0.0f, 0.8f)), 0, 0, 0));
        r17 = r30;
        r17.drawRect(0.0f, 0.0f, getWidth(), getHeight(), scrimPaint);
        goto L59
    L50:
        if (r31 != getForegroundView()) goto L52;
        scrimPaint.setColor(Color.argb(this.predictiveBackAnimation.getScrimAlpha(Theme.isCurrentThemeDark()), 0, 0, 0));
        r9 = 0.0f;
        r17 = r30;
        r17.drawRect(0.0f, 0.0f, getWidth(), getHeight(), scrimPaint);
        goto L59
    L38:
        r162 = 1.0f;
        r132 = r13;
        goto L41
    L39:
        r162 = 1.0f;
        r132 = r13;
        goto L41
    L25:
        if (this.springRouteBackgroundDrawable != null) goto L28;
        r2 = AndroidUtilities.dp(1.0f) + r11;
        goto L29
    L31:
        if (r31 != getForegroundView()) goto L29;
        r15 = r2;
        r14 = r11;
        goto L33
    L5:
        if (r1.isDrawCurrentPreviewFragmentAbove() == false) goto L21;
        if (this.inPreviewMode == false) goto L9;
    L12:
        BaseFragment r18 = this.oldFragment;
        if (r18 != null) goto L15;
    L17:
        LayoutContainer r19 = this.containerView;
    L18:
        if (r31 != r19) goto L21;
        this.drawerLayoutContainer.invalidate();
        return false;
    L15:
        if (r18.inPreviewMode == false) goto L17;
        r19 = this.containerViewBack;
        goto L18
    L9:
        if (this.transitionAnimationPreviewMode == true) goto L12;
        if (this.previewOpenAnimationInProgress == false) goto L21;
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void drawCurrentPreviewFragment(Canvas r11, Drawable r12) {
        if (this.inPreviewMode == false) goto L5;
    L10:
        BaseFragment r0 = this.oldFragment;
        if (r0 != null) goto L13;
    L15:
        LayoutContainer r02 = this.containerView;
    L16:
        drawPreviewDrawables(r11, r02);
        if (r02.getAlpha() >= 1.0f) goto L19;
        Canvas r3 = r11;
        r3.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r02.getAlpha() * 255.0f), 31);
    L20:
        r3.concat(r02.getMatrix());
        r02.draw(r3);
        if (r12 == null) goto L25;
        View r10 = r02.getChildAt(0);
        if (r10 == null) goto L25;
        ViewGroup.MarginLayoutParams r112 = (ViewGroup.MarginLayoutParams) r10.getLayoutParams();
        Rect r1 = new Rect();
        r10.getLocalVisibleRect(r1);
        r1.offset(r112.leftMargin, r112.topMargin);
        r1.top += AndroidUtilities.statusBarHeight - 1;
        r12.setAlpha((int) (r02.getAlpha() * 255.0f));
        r12.setBounds(r1);
        r12.draw(r3);
    L25:
        r3.restore();
        return;
    L19:
        r3 = r11;
        r3.save();
        goto L20
    L13:
        if (r0.inPreviewMode == false) goto L15;
        r02 = this.containerViewBack;
        goto L16
    L5:
        if (this.transitionAnimationPreviewMode == true) goto L10;
        if (this.previewOpenAnimationInProgress == true) goto L10;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void drawHeaderShadow(Canvas r2, int r3) {
        drawHeaderShadow(r2, Theme.forcedDividerPaint.getAlpha(), r3);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void drawHeaderShadow(Canvas r9, int r10, int r11) {
        int r0 = Theme.forcedDividerPaint.getAlpha();
        Theme.forcedDividerPaint.setAlpha(Math.min(r10, r0));
        float r4 = r11;
        r9.drawLine(0.0f, r4, getMeasuredWidth(), r4, Theme.forcedDividerPaint);
        Theme.forcedDividerPaint.setAlpha(r0);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void expandPreviewFragment() {
        this.previewOpenAnimationInProgress = true;
        this.inPreviewMode = false;
        List<BaseFragment> r3 = this.fragmentsStack;
        BaseFragment r32 = r3.get(r3.size() - 2);
        List<BaseFragment> r4 = this.fragmentsStack;
        BaseFragment r42 = r4.get(r4.size() - 1);
        r42.fragmentView.setOutlineProvider(null);
        r42.fragmentView.setClipToOutline(false);
        FrameLayout.LayoutParams r6 = (FrameLayout.LayoutParams) r42.fragmentView.getLayoutParams();
        r6.leftMargin = 0;
        r6.rightMargin = 0;
        r6.bottomMargin = 0;
        r6.topMargin = 0;
        r6.height = -1;
        r42.fragmentView.setLayoutParams(r6);
        if (ExteraConfig.getSpringAnimations() == false) goto L11;
        View r1 = r42.fragmentView;
        this.rect.set(r1.getLeft(), r1.getTop(), r1.getRight(), r1.getBottom());
        ActionBarPopupWindow.ActionBarPopupWindowLayout r5 = this.previewMenu;
        if (r5 == null) goto L7;
        float r52 = r5.getTranslationY();
    L8:
        float r8 = this.containerView.getTranslationY();
        SpringAnimation r62 = new SpringAnimation(new FloatValueHolder(0.0f)).setSpring(new SpringForce(1000.0f).setStiffness(750.0f).setDampingRatio(0.6f));
        r62.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda15(this, r1, r8, r52));
        r62.addEndListener(new ActionBarLayout$$ExternalSyntheticLambda16(this, r32, r42));
        r62.start();
        performHapticFeedback(3);
        this.containerView.setShouldHandleBottomInsets(r42.getEdgeToEdgeSupportMode());
        r42.setInPreviewMode(false);
        r42.setInMenuMode(false);
        AndroidUtilities.setLightStatusBar(this.parentActivity, r42.isLightStatusBar());     // Catch: Exception -> L16
        return;
    L7:
        r52 = 0.0f;
        goto L8
    L11:
        presentFragmentInternalRemoveOld(false, r32);
        AnimatorSet r33 = new AnimatorSet();
        r33.playTogether(new Animator[]{ObjectAnimator.ofFloat(r42.fragmentView, View.SCALE_X, new float[]{1.0f, 1.05f, 1.0f}), ObjectAnimator.ofFloat(r42.fragmentView, View.SCALE_Y, new float[]{1.0f, 1.05f, 1.0f})});
        r33.setDuration(200);
        r33.setInterpolator(new CubicBezierInterpolator(0.42d, 0.0d, 0.58d, 1.0d));
        r33.addListener(new AnonymousClass10(this, r42));
        r33.start();
        performHapticFeedback(3);     // Catch: Exception -> L17
    L13:
        this.containerView.setShouldHandleBottomInsets(r42.getEdgeToEdgeSupportMode());
        this.containerView.setDrawNavigationBar(r42.drawEdgeNavigationBar());
        r42.setInPreviewMode(false);
        r42.setInMenuMode(false);
        AndroidUtilities.setLightStatusBar(this.parentActivity, r42.isLightStatusBar());     // Catch: Exception -> L16
        return;
    }

    public boolean extendActionMode(Menu r3) {
        if (this.fragmentsStack.isEmpty() == true) goto L7;
        List<BaseFragment> r2 = this.fragmentsStack;
        if (r2.get(r2.size() - 1).extendActionMode(r3) == false) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void finishPreviewFragment() {
        if (this.previewFinishInProgress == false) goto L5;
        return;
    L5:
        if (this.inPreviewMode == false) goto L7;
    L9:
        this.previewFinishInProgress = true;
        Runnable r1 = this.delayedOpenAnimationRunnable;
        if (r1 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.delayedOpenAnimationRunnable = null;
    L12:
        closeLastFragment(true);
        if (this.transitionAnimationInProgress == true) goto L17;
        this.previewFinishInProgress = false;
        return;
    L17:
        return;
    L7:
        if (this.transitionAnimationPreviewMode == true) goto L9;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public /* bridge */ /* synthetic */ BaseFragment getBackgroundFragment() {
        return super.getBackgroundFragment();
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public /* bridge */ /* synthetic */ BottomSheet getBottomSheet() {
        return super.getBottomSheet();
    }

    public BottomSheetTabs getBottomSheetTabs() {
        return this.bottomSheetTabs;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public int getBottomTabsHeight(boolean r2) {
        if (this.main == false) goto L8;
        BottomSheetTabs r1 = this.bottomSheetTabs;
        if (r1 != null) goto L7;
        return 0;
    L7:
        return r1.getHeight(r2);
    L8:
        return 0;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public float getCurrentPreviewFragmentAlpha() {
        if (this.inPreviewMode == false) goto L5;
    L11:
        BaseFragment r0 = this.oldFragment;
        if (r0 != null) goto L14;
    L16:
        LayoutContainer r1 = this.containerView;
    L18:
        return r1.getAlpha();
    L14:
        if (r0.inPreviewMode == false) goto L16;
        r1 = this.containerViewBack;
        goto L18
    L5:
        if (this.transitionAnimationPreviewMode == true) goto L11;
        if (this.previewOpenAnimationInProgress == true) goto L11;
        return 0.0f;
    }

    public DrawerLayoutContainer getDrawerLayoutContainer() {
        return this.drawerLayoutContainer;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public List<BaseFragment> getFragmentStack() {
        return this.fragmentsStack;
    }

    @Keep
    public float getInnerTranslationX() {
        return this.innerTranslationX;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public BaseFragment getLastFragment() {
        if (this.fragmentsStack.isEmpty() == false) goto L7;
        return null;
    L7:
        return this.fragmentsStack.get(r1.size() - 1);
    }

    public BaseFragment getLastFragmentIncludeMainTabs() {
        BaseFragment r1 = getLastFragment();
        if ((r1 instanceof MainTabsActivity) == true) goto L5;
        return r1;
    L5:
        return ((MainTabsActivity) r1).getCurrentVisibleFragment();
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public MessageDrawable getMessageDrawableOutMediaStart() {
        return this.messageDrawableOutMediaStart;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public MessageDrawable getMessageDrawableOutStart() {
        return this.messageDrawableOutStart;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public FrameLayout getOverlayContainerView() {
        return this;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public /* bridge */ /* synthetic */ Activity getParentActivity() {
        return super.getParentActivity();
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public List<BackButtonMenu.PulledDialog> getPulledDialogs() {
        return this.pulledDialogs;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public /* bridge */ /* synthetic */ BaseFragment getSafeLastFragment() {
        return super.getSafeLastFragment();
    }

    public EmptyBaseFragment getSheetFragment() {
        return getSheetFragment(true);
    }

    public EmptyBaseFragment getSheetFragment(boolean r4) {
        if (this.parentActivity != null) goto L7;
        return null;
    L7:
        if (this.sheetFragment != null) goto L16;
        AnonymousClass1 r42 = new AnonymousClass1(this);
        this.sheetFragment = r42;
        r42.setParentLayout(this);
        EmptyBaseFragment r43 = this.sheetFragment;
        View r0 = r43.fragmentView;
        if (r0 != null) goto L12;
        r0 = r43.performCreateView(this.parentActivity);
    L12:
        if (r0.getParent() == this.sheetContainer) goto L14;
        AndroidUtilities.removeFromParent(r0);
        this.sheetContainer.addView(r0, LayoutHelper.createFrame(-1, -1.0f));
        this.sheetContainer.setShouldHandleBottomInsets(this.sheetFragment.getEdgeToEdgeSupportMode());
        this.sheetContainer.setDrawNavigationBar(this.sheetFragment.drawEdgeNavigationBar());
    L14:
        this.sheetFragment.onResume();
        this.sheetFragment.onBecomeFullyVisible();
    L16:
        return this.sheetFragment;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    @Keep
    public float getThemeAnimationValue() {
        return this.themeAnimationValue;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public /* bridge */ /* synthetic */ ViewGroup getView() {
        return super.getView();
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public Window getWindow() {
        Window r0 = this.window;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (getParentActivity() != null) goto L8;
        return null;
    L8:
        return getParentActivity().getWindow();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isInBubbleMode() {
        return this.inBubbleMode;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isInPassivePreviewMode() {
        if (this.inPreviewMode == false) goto L7;
        if (this.previewMenu != null) goto L7;
        return true;
    L7:
        if (this.transitionAnimationPreviewMode == true) goto L12;
        return false;
    L12:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isInPreviewMode() {
        if (this.inPreviewMode == false) goto L5;
        return true;
    L5:
        if (this.transitionAnimationPreviewMode == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isIncomingFragmentTouchable() {
        if (this.transitionAnimationInProgress == true) goto L5;
        return false;
    L5:
        if (this.onOpenAnimationEndRunnable != null) goto L7;
        return false;
    L7:
        if (this.containerView.getAlpha() < 1.0f) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isLayersLayout() {
        return this.isLayersLayout;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isPreviewOpenAnimationInProgress() {
        return this.previewOpenAnimationInProgress;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isRightLayout() {
        return this.isRightLayout;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isSheet() {
        return this.isSheet;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isSwipeInProgress() {
        return this.startedTracking;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean isTransitionAnimationInProgress() {
        if (this.transitionAnimationInProgress == false) goto L5;
        return true;
    L5:
        if (this.animationInProgress == true) goto L11;
        return false;
    L11:
        return true;
    }

    public int measureKeyboardHeight() {
        View r0 = getRootView();
        getWindowVisibleDisplayFrame(this.rect);
        Rect r1 = this.rect;
        if (r1.bottom == 0) goto L5;
    L7:
        int r12 = r0.getHeight();
        if (this.rect.top == 0) goto L10;
        int r2 = AndroidUtilities.statusBarHeight;
    L11:
        int r13 = (r12 - r2) - AndroidUtilities.getViewInset(r0);
        Rect r4 = this.rect;
        return Math.max(0, r13 - (r4.bottom - r4.top));
    L10:
        r2 = 0;
        goto L11
    L5:
        if (r1.top != 0) goto L7;
        return 0;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void movePreviewFragment(float r4) {
        if (this.inPreviewMode == true) goto L5;
        return;
    L5:
        if (this.previewMenu == null) goto L7;
        return;
    L7:
        if (this.transitionAnimationPreviewMode == true) goto L22;
        float r0 = this.containerView.getTranslationY();
        float r42 = -r4;
        if (r42 <= 0.0f) goto L13;
    L11:
        r42 = 0.0f;
    L16:
        if (r0 == r42) goto L21;
        this.containerView.setTranslationY(r42);
        invalidate();
        return;
    L21:
        return;
    L13:
        if (r42 >= (-AndroidUtilities.dp(60.0f))) goto L16;
        expandPreviewFragment();
        goto L11
    }

    public void onActionModeFinished(Object r2) {
        ActionBar r22 = this.currentActionBar;
        if (r22 == null) goto L5;
        r22.setVisibility(0);
    L5:
        this.inActionMode = false;
    }

    public void onActionModeStarted(Object r2) {
        ActionBar r22 = this.currentActionBar;
        if (r22 == null) goto L5;
        r22.setVisibility(8);
    L5:
        this.inActionMode = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
    }

    public void onBackCancelled() {
        if (this.predictiveInput == true) goto L5;
        return;
    L5:
        this.predictiveInput = false;
        animateBackEndAnimation(true);
    }

    public void onBackInvoked() {
        if (this.predictiveInput == true) goto L6;
        onBackPressed();
        return;
    L6:
        this.predictiveInput = false;
        animateBackEndAnimation(false);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void onBackPressed() {
        if (this.transitionAnimationPreviewMode == false) goto L5;
        return;
    L5:
        if (this.startedTracking == false) goto L7;
        return;
    L7:
        if (transitionBlocksNavigation() == false) goto L9;
        return;
    L9:
        if (this.fragmentsStack.isEmpty() == false) goto L12;
        return;
    L12:
        if (GroupCallPip.onBackPressed() == false) goto L15;
        return;
    L15:
        if (storyViewerAttached() == true) goto L24;
        ActionBar r0 = this.currentActionBar;
        if (r0 == null) goto L24;
        if (r0.isActionModeShowed() == true) goto L24;
        ActionBar r02 = this.currentActionBar;
        if (r02.isSearchFieldVisible == false) goto L24;
        r02.closeSearchField();
        return;
    L24:
        EmptyBaseFragment r03 = this.sheetFragment;
        if (r03 != null) goto L27;
    L29:
        List<BaseFragment> r04 = this.fragmentsStack;
        if (r04.get(r04.size() - 1).onBackPressed(true) == true) goto L32;
        return;
    L32:
        if (this.fragmentsStack.isEmpty() == true) goto L39;
        closeLastFragment(true);
        return;
    L39:
        return;
    L27:
        if (r03.onBackPressed(true) == true) goto L29;
    }

    public void onBackProgress(float r2, float r3) {
        if (this.predictiveInput == true) goto L5;
        return;
    L5:
        applyPredictiveBackProgress(r2, r3, true);
    }

    public boolean onBackStarted(float r10, float r11) {
        boolean r1 = false;
        if (this.animationInProgress == false) goto L10;
        AnimatorSet r0 = this.backAnimator;
        if (r0 == null) goto L8;
        r0.end();
        this.backAnimator = null;
        if (this.animationInProgress == false) goto L10;
    L8:
        return false;
    L10:
        if (this.predictiveBackInProgress == false) goto L12;
    L49:
        return false;
    L12:
        if (this.predictiveInput == true) goto L49;
        if (this.transitionAnimationPreviewMode == true) goto L49;
        if (this.startedTracking == true) goto L49;
        if (checkTransitionAnimation() == true) goto L49;
        if (this.fragmentsStack.size() <= 1) goto L49;
        if (isInPreviewMode() == false) goto L26;
        return false;
    L26:
        EmptyBaseFragment r02 = this.sheetFragment;
        if (r02 != null) goto L29;
    L31:
        List<BaseFragment> r03 = this.fragmentsStack;
        BaseFragment r04 = r03.get(r03.size() - 1);
        if (r04.onBackPressed(false) == true) goto L35;
        return false;
    L35:
        if (r04.hasShownSheet() == true) goto L49;
        if (r04.canBeginSlide() == false) goto L49;
        this.predictiveBackHasProgress = false;
        this.predictiveBackInProgress = true;
        this.predictiveInput = true;
        if (r10 >= (AndroidUtilities.displaySize.x / 2.0f)) goto L42;
        r1 = true;
    L42:
        this.predictiveBackLeft = r1;
        this.predictiveBackY = r11;
        this.predictiveBackAnimation.start(this.containerView.getMeasuredWidth(), getMeasuredHeight(), r11, this.predictiveBackLeft, getPredictiveBackStartCornerRadius());
        prepareForMoving();
        this.predictiveBackBackgroundDrawable = PredictiveBackAnimationHelper.getTransitionBackground(this.fragmentsStack, getLastFragment());
        Activity r102 = this.parentActivity;
        if (r102 != null) goto L45;
    L47:
        r04.onBeginSlide();
        return true;
    L45:
        if (r102.getCurrentFocus() == null) goto L47;
        AndroidUtilities.hideKeyboard(this.parentActivity.getCurrentFocus());
        goto L47
    L29:
        if (r02.hasShownSheet() == false) goto L31;
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r5) {
        super.onConfigurationChanged(r5);
        if (this.fragmentsStack.isEmpty() == true) goto L10;
        int r0 = this.fragmentsStack.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        BaseFragment r2 = this.fragmentsStack.get(r1);
        r2.onConfigurationChanged(r5);
        Dialog r22 = r2.visibleDialog;
        if ((r22 instanceof BottomSheet) == false) goto L9;
        ((BottomSheet) r22).onConfigurationChanged(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.ui.Components.FloatingDebug.FloatingDebugProvider
    public List<FloatingDebugController.DebugItem> onGetDebugItems() {
        BaseFragment r0 = getLastFragment();
        if (r0 == 0) goto L10;
        ArrayList r1 = new ArrayList();
        if ((r0 instanceof FloatingDebugProvider) == false) goto L7;
        r1.addAll(((FloatingDebugProvider) r0).onGetDebugItems());
    L7:
        observeDebugItemsFromView(r1, r0.getFragmentView());
        return r1;
    L10:
        return Collections.EMPTY_LIST;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (this.animationInProgress == false) goto L5;
        return true;
    L5:
        if (this.previewOpenAnimationInProgress == false) goto L7;
        return true;
    L7:
        if (checkTransitionAnimation() == false) goto L11;
        if (isIncomingFragmentTouchable() == true) goto L11;
        return true;
    L11:
        if (onTouchEvent(r2) == true) goto L19;
        return false;
    L19:
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent r3) {
        if (r2 != 82) goto L12;
        if (checkTransitionAnimation() == true) goto L12;
        if (this.startedTracking == true) goto L12;
        ActionBar r0 = this.currentActionBar;
        if (r0 == null) goto L12;
        r0.onMenuButtonPressed();
    L12:
        return super.onKeyUp(r2, r3);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        int r1 = 0;
        if (getHeight() <= getWidth()) goto L5;
        boolean r102 = true;
    L7:
        if (this.lastPortrait == r102) goto L9;
        this.lastPortrait = r102;
        this.savedBottomSheetTabsTop = 0;
    L9:
        int r103 = getChildCount();
        int r0 = getPaddingLeft();
        int r132 = (r13 - r11) - getPaddingRight();
        int r112 = getPaddingTop();
        int r142 = (r14 - r12) - getPaddingBottom();
    L10:
        if (r1 >= r103) goto L55;
        View r122 = getChildAt(r1);
        if (r122.getVisibility() == 8) goto L54;
        BottomSheetTabs r3 = this.bottomSheetTabs;
        if (r122 != r3) goto L16;
        r3.updateCurrentAccount();
    L16:
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) r122.getLayoutParams();
        int r4 = r122.getMeasuredWidth();
        int r5 = r122.getMeasuredHeight();
        int r6 = r32.gravity;
        if (r6 != (-1)) goto L19;
        r6 = 8388659;
    L19:
        int r7 = Gravity.getAbsoluteGravity(r6, getLayoutDirection());
        int r62 = r6 & 112;
        int r72 = r7 & 7;
        if (r72 != 1) goto L22;
        int r73 = ((((r132 - r0) - r4) / 2) + r0) + r32.leftMargin;
        int r8 = r32.rightMargin;
    L25:
        int r74 = r73 - r8;
    L28:
        if (r62 != 16) goto L30;
        int r63 = ((((r142 - r112) - r5) / 2) + r112) + r32.topMargin;
        int r33 = r32.bottomMargin;
    L36:
        int r34 = r63 - r33;
    L40:
        if (r122 != this.bottomSheetTabs) goto L51;
        if (this.savedBottomSheetTabsTop == 0) goto L51;
        if (this.isKeyboardVisible == false) goto L46;
    L49:
        r34 = this.savedBottomSheetTabsTop;
    L53:
        r122.layout(r74, r34, r4 + r74, r5 + r34);
        goto L54
    L46:
        if ((getParent() instanceof View) == false) goto L51;
        if (((View) getParent()).getHeight() > getHeight()) goto L49;
    L51:
        if (r122 != this.bottomSheetTabs) goto L53;
        this.savedBottomSheetTabsTop = r34;
        goto L53
    L30:
        if (r62 != 48) goto L32;
        int r35 = r32.topMargin;
    L34:
        r34 = r35 + r112;
        goto L40
    L32:
        if (r62 == 80) goto L35;
        r35 = r32.topMargin;
        goto L34
    L35:
        r63 = r142 - r5;
        r33 = r32.bottomMargin;
        goto L36
    L22:
        if (r72 == 5) goto L24;
        r74 = r32.leftMargin + r0;
        goto L28
    L24:
        r73 = r132 - r4;
        r8 = r32.rightMargin;
    L54:
        r1 = r1 + 1;
        goto L10
    L55:
        return;
    L5:
        r102 = false;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void onLowMemory() {
        Iterator<BaseFragment> r1 = this.fragmentsStack.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onLowMemory();
        goto L4
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        boolean r1 = true;
        if (this.fragmentsStack.isEmpty() == true) goto L5;
        List<BaseFragment> r0 = this.fragmentsStack;
        BaseFragment r02 = r0.get(r0.size() - 1);
    L6:
        if (r02 != null) goto L8;
    L13:
        INavigationLayout.INavigationLayoutDelegate r03 = this.delegate;
        if (r03 == null) goto L17;
        int[] r3 = this.measureSpec;
        r3[0] = r6;
        r3[1] = r7;
        r03.onMeasureOverride(r3);
        int[] r62 = this.measureSpec;
        int r72 = r62[0];
        r7 = r62[1];
        r6 = r72;
    L17:
        if (measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L20;
        r1 = false;
    L20:
        this.isKeyboardVisible = r1;
        super.onMeasure(r6, r7);
        return;
    L8:
        if (r02.isSupportEdgeToEdge() == true) goto L13;
        if (storyViewerAttached() == false) goto L13;
        int r12 = measureKeyboardHeight();
        r02.setKeyboardHeightFromParent(r12);
        super.onMeasure(r6, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r7) + r12, TLObject.FLAG_30));
        return;
    L5:
        r02 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void onPause() {
        if (this.fragmentsStack.isEmpty() == true) goto L5;
        this.fragmentsStack.get(r0.size() - 1).onPause();
    L5:
        EmptyBaseFragment r2 = this.sheetFragment;
        if (r2 == null) goto L9;
        r2.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void onResume() {
        if (this.fragmentsStack.isEmpty() == true) goto L5;
        this.fragmentsStack.get(r0.size() - 1).onResume();
    L5:
        EmptyBaseFragment r2 = this.sheetFragment;
        if (r2 == null) goto L9;
        r2.onResume();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r13) {
        if (checkTransitionAnimation() == false) goto L5;
    L154:
        return false;
    L5:
        if (this.inActionMode == true) goto L154;
        if (this.animationInProgress == true) goto L154;
        if (this.predictiveBackInProgress == true) goto L154;
        if (this.fragmentsStack.size() <= 1) goto L153;
        if (allowSwipe() == false) goto L153;
        if (r13 != null) goto L17;
    L27:
        float r3 = 0.0f;
        if (r13 != null) goto L30;
    L69:
        if (r13 != null) goto L71;
    L145:
        if (r13 != null) goto L153;
        this.maybeStartTracking = false;
        this.startedTracking = false;
        this.layoutToIgnore = null;
        LayoutContainer r132 = this.containerView;
        if (r132 == null) goto L149;
        r132.setLayerType(0, null);
    L149:
        VelocityTracker r133 = this.velocityTracker;
        if (r133 == null) goto L153;
        r133.recycle();
        this.velocityTracker = null;
        goto L153
    L71:
        if (r13.getPointerId(0) != this.startedTrackingPointerId) goto L145;
        if (r13.getAction() == 3) goto L79;
        if (r13.getAction() == 1) goto L79;
        if (r13.getAction() != 6) goto L145;
    L79:
        if (this.velocityTracker != null) goto L81;
        this.velocityTracker = VelocityTracker.obtain();
    L81:
        this.velocityTracker.addMovement(r13);
        this.velocityTracker.computeCurrentVelocity(1000);
        List<BaseFragment> r4 = this.fragmentsStack;
        BaseFragment r42 = r4.get(r4.size() - 1);
        if (this.inPreviewMode == true) goto L104;
        if (this.transitionAnimationPreviewMode == true) goto L104;
        if (this.startedTracking == true) goto L104;
        if (r42.isSwipeBackEnabled(r13) == false) goto L104;
        float r9 = this.velocityTracker.getXVelocity();
        float r10 = this.velocityTracker.getYVelocity();
        if (this.containerView.getX() <= AndroidUtilities.getPixelsInCM(0.15f, true)) goto L104;
        if (r9 < AppUtils.getSwipeVelocity()) goto L104;
        if (r9 <= Math.abs(r10)) goto L104;
        if (r42.canBeginSlide() == false) goto L104;
        this.startedTrackingX = (int) r13.getX();
        prepareForMoving();
        if (this.beginTrackingSent == true) goto L104;
        if (((Activity) getContext()).getCurrentFocus() == null) goto L102;
        AndroidUtilities.hideKeyboard(((Activity) getContext()).getCurrentFocus());
    L102:
        this.beginTrackingSent = true;
    L104:
        if (this.startedTracking == false) goto L139;
        float r134 = this.containerView.getX();
        float r43 = this.velocityTracker.getXVelocity();
        float r5 = this.velocityTracker.getYVelocity();
        if (newBackTransitions() == false) goto L113;
        if (r134 < (AndroidUtilities.dp(56.0f) / 2)) goto L115;
        if (r43 < (-1000.0f)) goto L115;
    L119:
        boolean r52 = false;
    L121:
        if (ExteraConfig.getSpringSwipeback() == false) goto L138;
        this.springIsLayout = false;
        this.springIsPreview = false;
        this.springIsBack = r52;
        float r135 = (r134 / this.containerView.getMeasuredWidth()) * 1000.0f;
        FloatValueHolder r6 = this.springValueHolder;
        if (r6 != null) goto L125;
        this.springValueHolder = new FloatValueHolder(r135);
    L127:
        if (this.currentSpringAnimation != null) goto L129;
        this.currentSpringAnimation = new SpringAnimation(this.springValueHolder);
        SpringForce r136 = new SpringForce();
        this.springForce = r136;
        this.currentSpringAnimation.setSpring(r136);
        this.currentSpringAnimation.addUpdateListener(new ActionBarLayout$$ExternalSyntheticLambda1(this));
        this.currentSpringAnimation.addEndListener(new ActionBarLayout$$ExternalSyntheticLambda2(this));
    L129:
        SpringForce r137 = this.springForce;
        if (r52 == true) goto L133;
        r3 = 1000.0f;
    L133:
        r137.setFinalPosition(r3);
        this.springForce.setStiffness(900.0f);
        this.springForce.setDampingRatio(1.0f);
        this.currentSpringAnimation.setStartVelocity((r43 / this.containerView.getMeasuredWidth()) * 1000.0f);
        this.currentSpringAnimation.start();
        this.animationInProgress = true;
        this.layoutToIgnore = this.containerViewBack;
        VelocityTracker r138 = this.velocityTracker;
        if (r138 == null) goto L137;
        r138.recycle();
        this.velocityTracker = null;
    L137:
        return this.startedTracking;
    L125:
        r6.setValue(r135);
        goto L127
    L138:
        animateBackEndAnimation(r52);
    L142:
        VelocityTracker r139 = this.velocityTracker;
        if (r139 == null) goto L153;
        r139.recycle();
        this.velocityTracker = null;
    L115:
        if (r43 >= AppUtils.getSwipeVelocity()) goto L117;
    L118:
        r52 = true;
        goto L121
    L117:
        if (Math.abs(r43) >= Math.abs(r5)) goto L119;
    L113:
        if (r134 >= (this.containerView.getMeasuredWidth() / 4.0f)) goto L119;
    L139:
        this.maybeStartTracking = false;
        this.startedTracking = false;
        this.layoutToIgnore = null;
        LayoutContainer r1310 = this.containerView;
        if (r1310 == null) goto L142;
        r1310.setLayerType(0, null);
        goto L142
    L30:
        if (r13.getAction() != 2) goto L69;
        if (r13.getPointerId(0) != this.startedTrackingPointerId) goto L69;
        if (this.velocityTracker != null) goto L36;
        this.velocityTracker = VelocityTracker.obtain();
    L36:
        int r0 = Math.max(0, (int) (r13.getX() - this.startedTrackingX));
        int r62 = Math.abs(((int) r13.getY()) - this.startedTrackingY);
        this.velocityTracker.addMovement(r13);
        if (this.transitionAnimationInProgress == true) goto L55;
        if (this.inPreviewMode == true) goto L55;
        if (this.maybeStartTracking == false) goto L55;
        if (this.startedTracking == true) goto L55;
        if (r0 < AndroidUtilities.getPixelsInCM(0.15f, true)) goto L55;
        if ((Math.abs(r0) / 3) <= r62) goto L55;
        List<BaseFragment> r02 = this.fragmentsStack;
        if (r02.get(r02.size() - 1).canBeginSlide() == true) goto L51;
    L53:
        this.maybeStartTracking = false;
        goto L153
    L51:
        if (findScrollingChild(this, r13.getX(), r13.getY()) != null) goto L53;
        this.startedTrackingX = (int) r13.getX();
        prepareForMoving();
    L55:
        if (this.startedTracking == false) goto L153;
        if (this.beginTrackingSent == false) goto L59;
    L62:
        boolean r1 = newBackTransitions();
        LayoutContainer r2 = this.containerView;
        if (r1 == false) goto L65;
        float r1311 = r0;
        r2.setTranslationX((r1311 / getWidth()) * (AndroidUtilities.dp(56.0f) * 5));
        setInnerTranslationX((r1311 / getWidth()) * (AndroidUtilities.dp(56.0f) * 5));
        goto L153
    L65:
        float r12 = r0;
        r2.setTranslationX(r12);
        if (ExteraConfig.getSpringSwipeback() == false) goto L68;
        this.containerViewBack.setTranslationX((-(this.containerView.getMeasuredWidth() - r0)) * 0.35f);
        float r03 = AndroidUtilities.lerp(1.0f, 0.85f, Utilities.clamp01(r12 / this.containerView.getMeasuredWidth()));
        this.containerView.setScaleX(r03);
        this.containerView.setScaleY(r03);
        float r22 = Math.max(0.0f, ((this.containerView.getMeasuredHeight() * (1.0f - r03)) / 2.0f) - AndroidUtilities.dp(8.0f));
        float r1312 = Utilities.clamp((r13.getY() - this.startedTrackingY) / (this.containerView.getMeasuredHeight() / 2.0f), 1.0f, -1.0f);
        this.springRouteYRatio = r1312;
        this.containerView.setTranslationY(r1312 * r22);
    L68:
        setInnerTranslationX(r12);
        goto L153
    L59:
        if (this.parentActivity.getCurrentFocus() == null) goto L61;
        AndroidUtilities.hideKeyboard(this.parentActivity.getCurrentFocus());
    L61:
        List<BaseFragment> r14 = this.fragmentsStack;
        r14.get(r14.size() - 1).onBeginSlide();
        this.beginTrackingSent = true;
        goto L62
    L17:
        if (r13.getAction() != 0) goto L27;
        List<BaseFragment> r32 = this.fragmentsStack;
        if (r32.get(r32.size() - 1).isSwipeBackEnabled(r13) == true) goto L24;
        this.maybeStartTracking = false;
        this.startedTracking = false;
        LayoutContainer r122 = this.containerView;
        if (r122 == null) goto L23;
        r122.setLayerType(0, null);
    L23:
        return false;
    L24:
        this.startedTrackingPointerId = r13.getPointerId(0);
        this.maybeStartTracking = true;
        this.startedTrackingX = (int) r13.getX();
        this.startedTrackingY = (int) r13.getY();
        VelocityTracker r1313 = this.velocityTracker;
        if (r1313 == null) goto L153;
        r1313.clear();
    L153:
        return this.startedTracking;
    }

    public void onUserLeaveHint() {
        if (this.fragmentsStack.isEmpty() == true) goto L5;
        this.fragmentsStack.get(r0.size() - 1).onUserLeaveHint();
    L5:
        EmptyBaseFragment r2 = this.sheetFragment;
        if (r2 == null) goto L9;
        r2.onUserLeaveHint();
        return;
    }

    public void parentDraw(View r3, Canvas r4) {
        if (this.bottomSheetTabs != null) goto L5;
        return;
    L5:
        if (getHeight() >= r3.getHeight()) goto L9;
        r4.save();
        r4.translate(getX() + this.bottomSheetTabs.getX(), getY() + this.bottomSheetTabs.getY());
        this.bottomSheetTabs.draw(r4);
        r4.restore();
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public boolean presentFragment(INavigationLayout.NavigationParams r22) {
        BaseFragment r4 = r22.fragment;
        boolean r2 = r22.removeLast;
        boolean r3 = r22.noAnimation;
        boolean r5 = r22.checkPresentFromDelegate;
        boolean r6 = r22.preview;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r32 = r22.menuView;
        if (r4 != null) goto L5;
    L231:
        return false;
    L5:
        if (transitionBlocksNavigation() == true) goto L231;
        INavigationLayout.INavigationLayoutDelegate r9 = this.delegate;
        if (r9 == null) goto L12;
        if (r5 == false) goto L12;
        if (r9.needPresentFragment(this, r22) == false) goto L231;
    L12:
        if (r4.onFragmentCreate() == false) goto L231;
        EdgeToEdgeSupportMode r0 = r4.getEdgeToEdgeSupportMode();
        EdgeToEdgeSupportMode r52 = EdgeToEdgeSupportMode.NONE;
        if (r0 == r52) goto L17;
        boolean r10 = true;
    L18:
        boolean r11 = r4.drawEdgeNavigationBar();
        BaseFragment r12 = getLastFragment();
        if (r12 == null) goto L21;
        Dialog r14 = r12.getVisibleDialog();
    L22:
        if (r14 != null) goto L28;
        LaunchActivity r15 = LaunchActivity.instance;
        if (r15 == null) goto L28;
        if (r15.getVisibleDialog() == null) goto L28;
        r14 = LaunchActivity.instance.getVisibleDialog();
    L28:
        if (r12 == null) goto L34;
        if (shouldOpenFragmentOverlay(r14) == false) goto L34;
        BaseFragment.BottomSheetParams r02 = new BaseFragment.BottomSheetParams();
        r02.transitionFromLeft = true;
        r02.allowNestedScroll = false;
        r12.showAsSheet(r4, r02);
        return true;
    L34:
        if (BuildVars.LOGS_ENABLED == false) goto L36;
        FileLog.d("present fragment " + r4.getClass().getSimpleName() + " args=" + r4.getArguments());
    L36:
        StoryViewer.closeGlobalInstances();
        BottomSheetTabs r122 = this.bottomSheetTabs;
        if (r122 == null) goto L42;
        if (r122.doNotDismiss == true) goto L42;
        LaunchActivity.dismissAllWeb();
    L42:
        if (this.inPreviewMode == true) goto L44;
    L49:
        r4.setInPreviewMode(r6);
        ActionBarPopupWindow.ActionBarPopupWindowLayout r123 = this.previewMenu;
        if (r123 != null) goto L52;
    L55:
        this.previewMenu = r32;
        if (r32 == null) goto L58;
        boolean r124 = true;
    L59:
        r4.setInMenuMode(r124);
        if (this.parentActivity.getCurrentFocus() != null) goto L62;
    L65:
        if (r6 == true) goto L71;
        if (r3 == false) goto L68;
    L70:
        boolean r7 = false;
    L72:
        boolean r125 = ExteraConfig.getSpringAnimations();
        if (this.fragmentsStack.isEmpty() == true) goto L75;
        List<BaseFragment> r142 = this.fragmentsStack;
        BaseFragment r143 = r142.get(r142.size() - 1);
    L76:
        r4.setParentLayout(this);
        View r152 = r4.fragmentView;
        if (r152 != null) goto L85;
        r152 = r4.performCreateView(this.parentActivity);
        if (r152 != null) goto L81;
    L88:
        this.containerViewBack.addView(r152);
        LayoutContainer r13 = this.containerViewBack;
        if (r6 == false) goto L92;
        r0 = r52;
    L92:
        r13.setShouldHandleBottomInsets(r0);
        LayoutContainer r03 = this.containerViewBack;
        if (r6 == true) goto L96;
        if (r11 == false) goto L96;
        boolean r53 = true;
    L97:
        r03.setDrawNavigationBar(r53);
        if (r32 == null) goto L100;
        this.containerViewBack.addView(r32);
        r32.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE));
        int r54 = r32.getMeasuredHeight() + AndroidUtilities.dp(24.0f);
        FrameLayout.LayoutParams r112 = (FrameLayout.LayoutParams) r32.getLayoutParams();
        r112.width = -2;
        r112.height = -2;
        r112.topMargin = ((getMeasuredHeight() - AndroidUtilities.navigationBarHeight) - r54) - AndroidUtilities.dp(6.0f);
        r32.setLayoutParams(r112);
    L101:
        FrameLayout.LayoutParams r113 = (FrameLayout.LayoutParams) r152.getLayoutParams();
        r113.width = -1;
        r113.height = -1;
        if (r6 == false) goto L118;
        int r04 = r4.getPreviewHeight();
        int r17 = AndroidUtilities.statusBarHeight;
        if (r04 > 0) goto L106;
    L108:
        if (r32 == null) goto L110;
        float r05 = 0.0f;
    L111:
        int r06 = AndroidUtilities.dp(r05);
        r113.bottomMargin = r06;
        r113.topMargin = r06;
        int r92 = AndroidUtilities.statusBarHeight;
        int r07 = r06 + r92;
        r113.topMargin = r07;
        if (r10 == false) goto L115;
        r113.topMargin = r07 + r92;
    L115:
        if (r32 == null) goto L117;
        r113.bottomMargin += r54 + AndroidUtilities.dp(8.0f);
    L117:
        int r08 = AndroidUtilities.dp(8.0f);
        r113.leftMargin = r08;
        r113.rightMargin = r08;
    L119:
        r152.setLayoutParams(r113);
        ActionBar r09 = r4.actionBar;
        if (r09 != null) goto L122;
    L127:
        r4.setTitleOverlayTextIfActionBarAttached(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        r4.attachSheets(this.containerViewBack);
        this.fragmentsStack.add(r4);
        onFragmentStackChanged("presentFragment");
        r4.onResume();
        this.currentActionBar = r4.actionBar;
        if (r4.hasOwnBackground == false) goto L130;
    L132:
        LayoutContainer r010 = this.containerView;
        LayoutContainer r55 = this.containerViewBack;
        this.containerView = r55;
        this.containerViewBack = r010;
        r55.setVisibility(0);
        setInnerTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        if (r6 == true) goto L135;
    L151:
        bringChildToFront(this.containerView);
        LayoutContainer r011 = this.sheetContainer;
        if (r011 == null) goto L154;
        bringChildToFront(r011);
    L154:
        if (r7 == true) goto L159;
        presentFragmentInternalRemoveOld(r2, r143);
        View r012 = this.backgroundView;
        if (r012 == null) goto L159;
        r012.setVisibility(0);
    L159:
        if (this.themeAnimatorSet == null) goto L162;
        this.presentingFragmentDescriptions = r4.getThemeDescriptions();
    L162:
        if (r7 == true) goto L172;
        if (r6 == true) goto L172;
        View r013 = this.backgroundView;
        if (r013 == null) goto L168;
        r013.setAlpha(1.0f);
        this.backgroundView.setVisibility(0);
    L168:
        if (r143 == null) goto L170;
        r143.onTransitionAnimationStart(false, false);
        r143.onTransitionAnimationEnd(false, false);
    L170:
        r4.onTransitionAnimationStart(true, false);
        r4.onTransitionAnimationEnd(true, false);
        r4.onBecomeFullyVisible();
        return true;
    L172:
        if (this.useAlphaAnimations == true) goto L174;
    L182:
        this.transitionAnimationPreviewMode = r6;
        this.transitionAnimationStartTime = System.currentTimeMillis();
        this.transitionAnimationInProgress = true;
        this.layoutToIgnore = this.containerView;
        BaseFragment r56 = r143;
        this.onOpenAnimationEndRunnable = new ActionBarLayout$$ExternalSyntheticLambda11(this, r6, r32, r2, r56, r4);
        boolean r014 = r4.needDelayOpenAnimation();
        boolean r57 = !r014;
        if (r014 == true) goto L187;
        if (r56 == null) goto L186;
        r56.onTransitionAnimationStart(false, false);
    L186:
        boolean r015 = true;
        r4.onTransitionAnimationStart(true, false);
    L188:
        this.delayedAnimationResumed = false;
        this.oldFragment = r56;
        this.newFragment = r4;
        if (r6 == true) goto L191;
        AnimatorSet r62 = r4.onCustomTransitionAnimation(r015, new ActionBarLayout$$ExternalSyntheticLambda12(this));
    L192:
        if (r62 != null) goto L222;
        if (r6 == false) goto L195;
    L197:
        if (r125 == false) goto L202;
        LayoutContainer r016 = this.containerView;
        if (r6 == false) goto L201;
        r016.setAlpha(0.0f);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setScaleX(0.5f);
        this.containerView.setScaleY(0.5f);
    L207:
        if (LayoutContainer.m5483$$Nest$fgetisKeyboardVisible(this.containerView) == false) goto L209;
    L215:
        if (r56 == null) goto L218;
        if (r6 == true) goto L218;
        r56.saveKeyboardPositionBeforeTransition();
    L218:
        this.waitingForKeyboardCloseRunnable = new AnonymousClass7(this, r57, r56, r4, r6);
        if (r4.needDelayOpenAnimation() == false) goto L221;
        this.delayedOpenAnimationRunnable = new AnonymousClass8(this, r56, r4, r6);
    L221:
        AndroidUtilities.runOnUIThread(this.waitingForKeyboardCloseRunnable, 250);
    L181:
        return true;
    L209:
        if (LayoutContainer.m5483$$Nest$fgetisKeyboardVisible(this.containerViewBack) == true) goto L215;
        if (r4.needDelayOpenAnimation() == false) goto L214;
        AnonymousClass9 r017 = new AnonymousClass9(this, r4, r6);
        this.delayedOpenAnimationRunnable = r017;
        AndroidUtilities.runOnUIThread(r017, 200);
        goto L181
    L214:
        startLayoutAnimation(true, true, r6);
        goto L181
    L201:
        r016.setTranslationX((getWidth() - getPaddingLeft()) - getPaddingRight());
        goto L207
    L202:
        this.containerView.setAlpha(0.0f);
        LayoutContainer r018 = this.containerView;
        if (r6 == false) goto L205;
        r018.setTranslationX(0.0f);
        this.containerView.setScaleX(0.9f);
        this.containerView.setScaleY(0.9f);
        goto L207
    L205:
        r018.setTranslationX(48.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        goto L207
    L195:
        if (ExteraConfig.getAospTransitions() == false) goto L197;
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        WindowTransitionAnimationHelper.apply(this.containerView, this.containerViewBack, true, 0.0f);
        goto L207
    L222:
        if (r6 == false) goto L224;
    L229:
        this.currentAnimation = r62;
        goto L181
    L224:
        if (LayoutContainer.m5483$$Nest$fgetisKeyboardVisible(this.containerView) == false) goto L226;
    L227:
        if (r56 == null) goto L229;
        r56.saveKeyboardPositionBeforeTransition();
        goto L229
    L226:
        if (LayoutContainer.m5483$$Nest$fgetisKeyboardVisible(this.containerViewBack) == false) goto L229;
    L191:
        r62 = null;
        goto L192
    L187:
        r015 = true;
        goto L188
    L174:
        if (this.fragmentsStack.size() != 1) goto L182;
        presentFragmentInternalRemoveOld(r2, r143);
        this.transitionAnimationStartTime = System.currentTimeMillis();
        this.transitionAnimationInProgress = true;
        this.layoutToIgnore = this.containerView;
        this.onOpenAnimationEndRunnable = new ActionBarLayout$$ExternalSyntheticLambda10(r143, r4);
        ArrayList r019 = new ArrayList();
        Property r58 = View.ALPHA;
        r019.add(ObjectAnimator.ofFloat(this, r58, new float[]{0.0f, 1.0f}));
        r019.add(ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{0.9f, 1.0f}));
        r019.add(ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{0.9f, 1.0f}));
        View r33 = this.backgroundView;
        if (r33 == null) goto L178;
        r33.setVisibility(0);
        r019.add(ObjectAnimator.ofFloat(this.backgroundView, r58, new float[]{0.0f, 1.0f}));
    L178:
        if (r143 == null) goto L180;
        r143.onTransitionAnimationStart(false, false);
    L180:
        r4.onTransitionAnimationStart(true, false);
        AnimatorSet r23 = new AnimatorSet();
        this.currentAnimation = r23;
        r23.playTogether(r019);
        this.currentAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.currentAnimation.setDuration(200);
        this.currentAnimation.addListener(new AnonymousClass6(this));
        this.currentAnimation.start();
        goto L181
    L135:
        if ((r4 instanceof ChatActivity) == false) goto L140;
        if (r32 == null) goto L139;
        r152.setOutlineProvider(new AnonymousClass5(this));
    L144:
        r152.setClipToOutline(true);
        r152.setElevation(AndroidUtilities.dp(4.0f));
        if (Build.VERSION.SDK_INT < 28) goto L148;
        r152.setOutlineSpotShadowColor(-1342177280);
        r152.setOutlineAmbientShadowColor(-1342177280);
    L148:
        if (this.previewBackgroundDrawable != null) goto L150;
        this.previewBackgroundDrawable = new ColorDrawable(771751936);
    L150:
        this.previewBackgroundDrawable.setAlpha(0);
        Theme.moveUpDrawable.setAlpha(0);
        goto L151
    L139:
        r152.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(29.0f)));
        goto L144
    L140:
        if (r32 != null) goto L142;
        float r020 = 24.0f;
    L143:
        r152.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(r020)));
        goto L144
    L142:
        r020 = 12.0f;
        goto L143
    L130:
        if (r152.getBackground() != null) goto L132;
        r152.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        goto L132
    L122:
        if (r09.shouldAddToContainer() == false) goto L127;
        if (this.removeActionBarExtraHeight == false) goto L126;
        r4.actionBar.setOccupyStatusBar(false);
    L126:
        AndroidUtilities.removeFromParent(r4.actionBar);
        this.containerViewBack.addView(r4.actionBar);
        goto L127
    L110:
        r05 = 24.0f;
        goto L111
    L106:
        if (r04 >= (getMeasuredHeight() - r17)) goto L108;
        r113.height = r04;
        r113.topMargin = r17 + (((getMeasuredHeight() - r17) - r04) / 2);
        goto L115
    L118:
        r113.leftMargin = 0;
        r113.rightMargin = 0;
        r113.bottomMargin = 0;
        r113.topMargin = 0;
        goto L119
    L100:
        r54 = 0;
    L96:
        r53 = false;
        goto L97
    L81:
        if (r4.isSupportEdgeToEdge() == false) goto L88;
        if (r4.drawEdgeNavigationBar() == false) goto L88;
        ViewCompat.setOnApplyWindowInsetsListener(r152, new ActionBarLayout$$ExternalSyntheticLambda0(r4));
        this.containerViewBack.invalidate();
        goto L88
    L85:
        ViewGroup r132 = (ViewGroup) r152.getParent();
        if (r132 == null) goto L88;
        r4.onRemoveFromParent();
        r132.removeView(r152);
        goto L88
    L75:
        r143 = null;
        goto L76
    L68:
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == false) goto L70;
    L71:
        r7 = true;
        goto L72
    L62:
        if (r4.hideKeyboardOnShow() == false) goto L65;
        if (r6 == true) goto L65;
        AndroidUtilities.hideKeyboard(this.parentActivity.getCurrentFocus());
        goto L65
    L58:
        r124 = false;
        goto L59
    L52:
        if (r123.getParent() == null) goto L54;
        ((ViewGroup) this.previewMenu.getParent()).removeView(this.previewMenu);
    L54:
        this.previewMenu = null;
        goto L55
    L44:
        if (this.transitionAnimationPreviewMode == false) goto L49;
        Runnable r126 = this.delayedOpenAnimationRunnable;
        if (r126 == null) goto L48;
        AndroidUtilities.cancelRunOnUIThread(r126);
        this.delayedOpenAnimationRunnable = null;
    L48:
        closeLastFragment(false, true);
        goto L49
    L21:
        r14 = null;
        goto L22
    L17:
        r10 = false;
        goto L18
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void rebuildAllFragmentViews(boolean r4, boolean r5) {
        if (this.transitionAnimationInProgress == false) goto L5;
    L22:
        this.rebuildAfterAnimation = true;
        this.rebuildLastAfterAnimation = r4;
        this.showLastAfterAnimation = r5;
        return;
    L5:
        if (this.startedTracking == true) goto L22;
        int r0 = this.fragmentsStack.size();
        if (r4 == true) goto L11;
        r0 = r0 - 1;
    L11:
        if (this.inPreviewMode == false) goto L13;
        r0 = r0 - 1;
    L13:
        int r1 = 0;
    L14:
        if (r1 >= r0) goto L16;
        this.fragmentsStack.get(r1).clearViews();
        this.fragmentsStack.get(r1).setParentLayout(this);
        r1 = r1 + 1;
        goto L14
    L16:
        INavigationLayout.INavigationLayoutDelegate r02 = this.delegate;
        if (r02 == null) goto L19;
        r02.onRebuildAllFragments(this, r4);
    L19:
        if (r5 == false) goto L25;
        showLastFragment();
        return;
    }

    public void rebuildLogout() {
        this.containerView.removeAllViews();
        this.containerViewBack.removeAllViews();
        this.currentActionBar = null;
        this.newFragment = null;
        this.oldFragment = null;
    }

    public void relayout() {
        requestLayout();
        this.containerView.requestLayout();
        this.containerViewBack.requestLayout();
        this.sheetContainer.requestLayout();
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void removeAllFragments() {
    L3:
        if (this.fragmentsStack.size() <= 0) goto L5;
        removeFragmentFromStackInternal(this.fragmentsStack.get(0), false);
        goto L3
    L5:
        View r0 = this.backgroundView;
        if (r0 == null) goto L10;
        r0.animate().alpha(0.0f).setDuration(180).withEndAction(new ActionBarLayout$$ExternalSyntheticLambda17(this)).start();
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void removeFragmentFromStack(BaseFragment r4, boolean r5) {
        boolean r1 = true;
        if (this.fragmentsStack.size() <= 0) goto L7;
        List<BaseFragment> r0 = this.fragmentsStack;
        if (r0.get(r0.size() - 1) != r4) goto L7;
    L10:
        onOpenAnimationEnd();
        onCloseAnimationEnd();
    L11:
        checkBlackScreen("removeFragmentFromStack " + r5);
        if (this.useAlphaAnimations == false) goto L20;
        if (this.fragmentsStack.size() != 1) goto L20;
        if (AndroidUtilities.isTablet() == false) goto L20;
        closeLastFragment(true);
        return;
    L20:
        if (this.delegate == null) goto L27;
        if (this.fragmentsStack.size() != 1) goto L27;
        if (AndroidUtilities.isTablet() == false) goto L27;
        this.delegate.needCloseLastFragment(this);
    L27:
        if (r4.allowFinishFragmentInsteadOfRemoveFromStack() == false) goto L30;
        if (r5 == true) goto L30;
    L31:
        removeFragmentFromStackInternal(r4, r1);
        return;
    L30:
        r1 = false;
    L7:
        if (this.fragmentsStack.size() <= 1) goto L11;
        if (this.fragmentsStack.get(r0.size() - 2) != r4) goto L11;
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r2) {
        onTouchEvent(null);
        super.requestDisallowInterceptTouchEvent(r2);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void resumeDelayedFragmentAnimation() {
        this.delayedAnimationResumed = true;
        Runnable r0 = this.delayedOpenAnimationRunnable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.waitingForKeyboardCloseRunnable != null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.delayedOpenAnimationRunnable.run();
        this.delayedOpenAnimationRunnable = null;
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setBackgroundView(View r1) {
        this.backgroundView = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setDelegate(INavigationLayout.INavigationLayoutDelegate r1) {
        this.delegate = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setDrawerLayoutContainer(DrawerLayoutContainer r1) {
        this.drawerLayoutContainer = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setFragmentPanTranslationOffset(int r1) {
        LayoutContainer r0 = this.containerView;
        if (r0 == null) goto L6;
        r0.setFragmentPanTranslationOffset(r1);
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setFragmentStack(List<BaseFragment> r4) {
        this.fragmentsStack = r4;
        BottomSheetTabs r42 = this.bottomSheetTabs;
        if (r42 == null) goto L6;
        r42.stopListening(this.invalidateRunnable, this.relayoutRunnable);
        AndroidUtilities.removeFromParent(this.bottomSheetTabs);
        this.bottomSheetTabs = null;
    L6:
        if (this.main == false) goto L10;
        BottomSheetTabs r43 = new BottomSheetTabs(this.parentActivity, this);
        this.bottomSheetTabs = r43;
        this.bottomSheetTabsClip = new BottomSheetTabs.ClipTools(r43);
        this.bottomSheetTabs.listen(this.invalidateRunnable, this.relayoutRunnable);
        FrameLayout.LayoutParams r44 = new FrameLayout.LayoutParams(-1, AndroidUtilities.dp(76.0f));
        r44.gravity = 87;
        addView(this.bottomSheetTabs, r44);
        if (LaunchActivity.instance.getBottomSheetTabsOverlay() == null) goto L10;
        LaunchActivity.instance.getBottomSheetTabsOverlay().setTabsView(this.bottomSheetTabs);
    L10:
        LayoutContainer r45 = this.containerViewBack;
        if (r45 == null) goto L13;
        AndroidUtilities.removeFromParent(r45);
    L13:
        LayoutContainer r46 = new LayoutContainer(this, this.parentActivity);
        this.containerViewBack = r46;
        addView(r46);
        FrameLayout.LayoutParams r47 = (FrameLayout.LayoutParams) this.containerViewBack.getLayoutParams();
        r47.width = -1;
        r47.height = -1;
        r47.gravity = 51;
        this.containerViewBack.setLayoutParams(r47);
        LayoutContainer r48 = this.containerView;
        if (r48 == null) goto L16;
        AndroidUtilities.removeFromParent(r48);
    L16:
        LayoutContainer r49 = new LayoutContainer(this, this.parentActivity);
        this.containerView = r49;
        addView(r49);
        FrameLayout.LayoutParams r410 = (FrameLayout.LayoutParams) this.containerView.getLayoutParams();
        r410.width = -1;
        r410.height = -1;
        r410.gravity = 51;
        this.containerView.setLayoutParams(r410);
        LayoutContainer r411 = this.sheetContainer;
        if (r411 == null) goto L19;
        AndroidUtilities.removeFromParent(r411);
    L19:
        LayoutContainer r412 = new LayoutContainer(this, this.parentActivity);
        this.sheetContainer = r412;
        this.hasSheetsAnimator.setParent(r412);
        addView(this.sheetContainer);
        FrameLayout.LayoutParams r413 = (FrameLayout.LayoutParams) this.sheetContainer.getLayoutParams();
        r413.width = -1;
        r413.height = -1;
        r413.gravity = 51;
        this.sheetContainer.setLayoutParams(r413);
        EmptyBaseFragment r414 = this.sheetFragment;
        if (r414 == null) goto L28;
        r414.setParentLayout(this);
        EmptyBaseFragment r415 = this.sheetFragment;
        View r1 = r415.fragmentView;
        if (r1 != null) goto L25;
        r1 = r415.performCreateView(this.parentActivity);
    L25:
        if (r1.getParent() == this.sheetContainer) goto L27;
        AndroidUtilities.removeFromParent(r1);
        this.sheetContainer.addView(r1, LayoutHelper.createFrame(-1, -1.0f));
        this.sheetContainer.setShouldHandleBottomInsets(this.sheetFragment.getEdgeToEdgeSupportMode());
    L27:
        this.sheetFragment.onResume();
        this.sheetFragment.onBecomeFullyVisible();
    L28:
        Iterator<BaseFragment> r416 = this.fragmentsStack.iterator();
    L30:
        if (r416.hasNext() == false) goto L32;
        r416.next().setParentLayout(this);
        goto L30
    }

    public void setFragmentStackChangedListener(Runnable r1) {
        this.onFragmentStackChangedListener = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setHighlightActionButtons(boolean r1) {
        this.highlightActionButtons = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setInBubbleMode(boolean r1) {
        this.inBubbleMode = r1;
    }

    @Keep
    public void setInnerTranslationX(float r4) {
        this.innerTranslationX = r4;
        invalidate();
        if (this.fragmentsStack.size() >= 2) goto L5;
        return;
    L5:
        if (this.containerView.getMeasuredWidth() > 0) goto L7;
        return;
    L7:
        if (newBackTransitions() == false) goto L9;
        float r42 = Utilities.clamp01(r4 / (AndroidUtilities.dp(56.0f) * 6));
    L10:
        List<BaseFragment> r0 = this.fragmentsStack;
        BaseFragment r02 = r0.get(r0.size() - 2);
        r02.onSlideProgress(false, r42);
        BaseFragment r3 = this.fragmentsStack.get(r3.size() - 1);
        float r43 = MathUtils.clamp(r42 * 2.0f, 0.0f, 1.0f);
        if (r3.isBeginToShow() == false) goto L18;
        int r1 = r3.getNavigationBarColor();
        int r03 = r02.getNavigationBarColor();
        if (r1 == r03) goto L19;
        r3.setNavigationBarColor(ColorUtils.blendARGB(r1, r03, r43));
        return;
    L19:
        return;
    L18:
        return;
    L9:
        r42 = r4 / this.containerView.getMeasuredWidth();
        goto L10
    }

    public void setIsLayersLayout() {
        this.isLayersLayout = true;
    }

    public void setIsRightLayout() {
        this.isRightLayout = true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setIsSheet(boolean r1) {
        this.isSheet = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setNavigationBarColor(int r3) {
        if (this.currentNavigationBarColor == r3) goto L5;
        this.currentNavigationBarColor = r3;
        invalidate();
    L5:
        DrawerLayoutContainer r0 = this.drawerLayoutContainer;
        if (r0 == null) goto L8;
        r0.setInternalNavigationBarColor(r3);
    L8:
        BottomSheetTabs r02 = this.bottomSheetTabs;
        if (r02 != null) goto L11;
        return;
    L11:
        if (this.startedTracking == false) goto L13;
    L15:
        boolean r2 = false;
    L16:
        r02.setNavigationBarColor(r3, r2);
        return;
    L13:
        if (this.animationInProgress == true) goto L15;
        r2 = true;
        goto L16
    }

    public void setOverrideWidthOffset(int r1) {
        this.overrideWidthOffset = r1;
        invalidate();
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setPulledDialogs(List<BackButtonMenu.PulledDialog> r1) {
        this.pulledDialogs = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setRemoveActionBarExtraHeight(boolean r1) {
        this.removeActionBarExtraHeight = r1;
    }

    @Keep
    public void setThemeAnimationValue(float r19) {
        this.themeAnimationValue = r19;
        int r2 = this.themeAnimatorDescriptions.size();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L8;
        ArrayList<ThemeDescription> r5 = this.themeAnimatorDescriptions.get(r4);
        int[] r6 = this.animateStartColors.get(r4);
        int[] r7 = this.animateEndColors.get(r4);
        int r8 = r5.size();
        int r9 = 0;
    L5:
        if (r9 >= r8) goto L7;
        int r10 = Color.red(r7[r9]);
        int r11 = Color.green(r7[r9]);
        int r12 = Color.blue(r7[r9]);
        int r13 = Color.alpha(r7[r9]);
        int r14 = Color.red(r6[r9]);
        int r15 = Color.green(r6[r9]);
        int r3 = Color.blue(r6[r9]);
        int r17 = r2;
        int r22 = Color.argb(Math.min(255, (int) (Color.alpha(r6[r9]) + ((r13 - r2) * r19))), Math.min(255, (int) (r14 + ((r10 - r14) * r19))), Math.min(255, (int) (r15 + ((r11 - r15) * r19))), Math.min(255, (int) (r3 + ((r12 - r3) * r19))));
        ThemeDescription r32 = r5.get(r9);
        r32.setAnimatedColor(r22);
        r32.setColor(r22, false, false);
        r9 = r9 + 1;
        r4 = r4;
        r2 = r17;
        goto L5
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        int r23 = this.themeAnimatorDelegate.size();
        int r42 = 0;
    L9:
        if (r42 >= r23) goto L14;
        ThemeDescription.ThemeDescriptionDelegate r33 = this.themeAnimatorDelegate.get(r42);
        if (r33 == null) goto L13;
        r33.didSetColor();
        r33.onAnimationProgress(r19);
    L13:
        r42 = r42 + 1;
        goto L9
    L14:
        ArrayList<ThemeDescription> r24 = this.presentingFragmentDescriptions;
        if (r24 == null) goto L19;
        int r25 = r24.size();
        int r43 = 0;
    L17:
        if (r43 >= r25) goto L19;
        ThemeDescription r34 = this.presentingFragmentDescriptions.get(r43);
        r34.setColor(Theme.getColor(r34.getCurrentKey(), r34.resourcesProvider), false, false);
        r43 = r43 + 1;
    L19:
        INavigationLayout.ThemeAnimationSettings.onAnimationProgress r26 = this.animationProgressListener;
        if (r26 == null) goto L22;
        r26.setProgress(r19);
    L22:
        INavigationLayout.INavigationLayoutDelegate r27 = this.delegate;
        if (r27 == null) goto L25;
        r27.onThemeProgress(r19);
    L25:
        globallyUpdateColors(this);
    }

    public void setTitleOverlayText(String r3, int r4, Runnable r5) {
        this.titleOverlayText = r3;
        this.titleOverlayTextId = r4;
        this.overlayAction = r5;
        int r32 = 0;
    L4:
        if (r32 >= this.fragmentsStack.size()) goto L6;
        this.fragmentsStack.get(r32).setTitleOverlayText(this.titleOverlayText, this.titleOverlayTextId, r5);
        r32 = r32 + 1;
        goto L4
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setUseAlphaAnimations(boolean r1) {
        this.useAlphaAnimations = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void setWindow(Window r1) {
        this.window = r1;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void showLastFragment() {
        if (this.fragmentsStack.isEmpty() == false) goto L5;
        return;
    L5:
        bringToFront(this.fragmentsStack.size() - 1);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void startActivityForResult(Intent r3, int r4) {
        if (this.parentActivity != null) goto L6;
        return;
    L6:
        if (this.transitionAnimationInProgress == false) goto L20;
        AnimatorSet r0 = this.currentAnimation;
        if (r0 == null) goto L10;
        r0.cancel();
        this.currentAnimation = null;
    L10:
        SpringAnimation r02 = this.currentSpringAnimation;
        if (r02 == null) goto L14;
        r02.cancel();
        this.currentSpringAnimation = null;
    L14:
        if (this.onCloseAnimationEndRunnable == null) goto L17;
        onCloseAnimationEnd();
    L19:
        this.containerView.invalidate();
        goto L20
    L17:
        if (this.onOpenAnimationEndRunnable == null) goto L19;
        onOpenAnimationEnd();
    L20:
        if (r3 == null) goto L23;
        this.parentActivity.startActivityForResult(r3, r4);
        return;
    }

    public boolean storyViewerAttached() {
        if (this.fragmentsStack.isEmpty() == true) goto L5;
        List<BaseFragment> r2 = this.fragmentsStack;
        BaseFragment r22 = r2.get(r2.size() - 1);
    L6:
        if (r22 != null) goto L8;
        return false;
    L8:
        if (r22.getLastStoryViewer() != null) goto L10;
        return false;
    L10:
        if (r22.getLastStoryViewer().attachedToParent() == false) goto L15;
        return true;
    L15:
        return false;
    L5:
        r22 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout
    public void updateTitleOverlay() {
        BaseFragment r0 = getLastFragment();
        if (r0 == null) goto L6;
        r0.setTitleOverlayText(this.titleOverlayText, this.titleOverlayTextId, this.overlayAction);
        return;
    }
}
