package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import com.exteragram.messenger.utils.ui.TextPaint;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.FireworksEffect;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SnowflakesEffect;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes3.dex */
public class ActionBar extends FrameLayout implements FactorAnimator.Target, Theme.Colorable {
    private int actionBarColor;
    public ActionBarMenuOnItemClick actionBarMenuOnItemClick;
    private ActionBarMenu actionMode;
    private AnimatorSet actionModeAnimation;
    private int actionModeColor;
    private View actionModeExtraView;
    private View[] actionModeHidingViews;
    private View actionModeShowingView;
    private String actionModeTag;
    private View actionModeTop;
    private View actionModeTranslationView;
    protected boolean actionModeVisible;
    private boolean adaptiveBackground;
    private boolean adaptiveBackgroundHideTitle;
    private ValueAnimator adaptive_animator;
    private int adaptive_lowerColorKey;
    private int adaptive_topColorKey;
    private boolean addToContainer;
    private ActionBarAnimatedSubtitleOverlayContainer additionalSubTitleOverlayContainer;
    private SimpleTextView additionalSubtitleTextView;
    private int additionalTextLeft;
    private boolean allowOverlayTitle;
    private float animatedCenterTitleAvailableWidth;
    private float animatedCenterTitleX;
    private final BoolAnimator animatorAvatarContainerHasAvatar;
    private final FactorAnimator animatorAvatarContainerWidth;
    private final BoolAnimator animatorHasMenuItems;
    private final FactorAnimator animatorMenuItemsWidth;
    private boolean attachState;
    private boolean attached;
    private BackupImageView avatarSearchImageView;
    private Drawable backButtonDrawable;
    public ImageView backButtonImageView;
    private INavigationLayout.BackButtonState backButtonState;
    Runnable backgroundUpdateListener;
    public Paint blurScrimPaint;
    boolean blurredBackground;
    private boolean castShadows;
    private boolean centerScale;
    private int centerTitleAnimationTargetWidth;
    private int centerTitleAnimationTargetX;
    private ValueAnimator centerTitleLayoutAnimator;
    private ChatAvatarContainer chatAvatarContainer;
    private boolean clipContent;
    SizeNotifierFrameLayout contentView;
    private boolean doNotDrawChild;
    public boolean doNotDrawGlassMenu;
    private Runnable doOnActionModeFactorChanged;
    private boolean drawBackButton;
    private boolean drawGlassMiddlePill;
    EllipsizeSpanAnimator ellipsizeSpanAnimator;
    private int extraHeight;
    private boolean fireworks;
    private FireworksEffect fireworksEffect;
    private Paint.FontMetricsInt fontMetricsInt;
    private boolean forceDisableCenterTitle;
    private boolean forceSkipTouches;
    private int forcedMenuMinWidth;
    private int forcedMenuWidth;
    private boolean fromBottom;
    private BlurredBackgroundDrawable glassDrawable;
    private BlurredBackgroundDrawable glassDrawableBack;
    private float glassDrawableLeftRadius;
    private BlurredBackgroundDrawable glassDrawableMenu;
    private boolean glassMode;
    private boolean glassModeHasAvatar;
    private boolean glassOnlyBack;
    private int glassPadding;
    private int glassTitleTextSize;
    private boolean hasForcedMenuMinWidth;
    private boolean hasForcedMenuWidth;
    private boolean ignoreLayoutRequest;
    private View.OnTouchListener interceptTouchEventListener;
    private boolean interceptTouches;
    private boolean isAnimationsAllowed;
    private boolean isCenterTitle;
    private boolean isMenuOffsetSuppressed;
    protected boolean isSearchFieldVisible;
    protected int itemsActionModeBackgroundColor;
    protected int itemsActionModeColor;
    public int itemsBackgroundColor;
    protected int itemsColor;
    private int lastMeasuredWidth;
    private CharSequence lastOverlayTitle;
    private Drawable lastRightDrawable;
    private Runnable lastRunnable;
    private CharSequence lastTitle;
    private boolean mAlwaysApplyColorFilterToBackButton;
    public ActionBarMenu menu;
    public boolean menuOccupyBack;
    protected boolean occupyStatusBar;
    private boolean onTop;
    private float onTopAnimated;
    private boolean overlayTitleAnimation;
    boolean overlayTitleAnimationInProgress;
    private final Object[] overlayTitleToSet;
    protected BaseFragment parentFragment;
    int prevWidth;
    private Rect rect;
    Rect rectTmp;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean resumed;
    private View.OnClickListener rightDrawableOnClickListener;
    private float searchFactor;
    public float searchFieldVisibleAlpha;
    AnimatorSet searchVisibleAnimator;
    private int shadowAlpha;
    private SnowflakesEffect snowflakesEffect;
    private CharSequence subtitle;
    private SimpleTextView subtitleTextView;
    private boolean supportsHolidayImage;
    private Runnable titleActionRunnable;
    private boolean titleAnimationRunning;
    private AnimatorSet titleAnimator;
    private int titleColorToSet;
    private boolean titleOverlayShown;
    private int titleRightMargin;
    private final SimpleTextView[] titleTextView;
    private FrameLayout titlesContainer;
    private boolean useContainerForTitles;

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBarMenu {
        final /* synthetic */ ActionBar this$0;

        public AnonymousClass1(ActionBar r1, Context r2, ActionBar r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            ActionBar r0 = this.this$0;
            if (r0.blurredBackground == true) goto L5;
        L9:
            Canvas r2 = r8;
        L10:
            super.dispatchDraw(r2);
            return;
        L5:
            if (this.drawBlur == false) goto L9;
            if (ActionBar.m5408$$Nest$fgetactionModeColor(r0) == 0) goto L9;
            this.this$0.rectTmp.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
            ActionBar r02 = this.this$0;
            r02.blurScrimPaint.setColor(ActionBar.m5408$$Nest$fgetactionModeColor(r02));
            ActionBar r03 = this.this$0;
            r2 = r8;
            r03.contentView.drawBlurRect(r2, 0.0f, r03.rectTmp, r03.blurScrimPaint, true);
            goto L10
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            SizeNotifierFrameLayout r0 = this.this$0.contentView;
            if (r0 == null) goto L6;
            r0.blurBehindViews.add(this);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            SizeNotifierFrameLayout r0 = this.this$0.contentView;
            if (r0 == null) goto L6;
            r0.blurBehindViews.remove(this);
            return;
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            this.this$0.invalidate();
            if (ActionBar.m5413$$Nest$fgetdoOnActionModeFactorChanged(this.this$0) == null) goto L6;
            ActionBar.m5413$$Nest$fgetdoOnActionModeFactorChanged(this.this$0).run();
            return;
        }

        @Override // android.view.View
        public void setBackgroundColor(int r2) {
            ActionBar.m5420$$Nest$fputactionModeColor(this.this$0, r2);
            ActionBar r22 = this.this$0;
            if (r22.blurredBackground == true) goto L6;
            super.setBackgroundColor(ActionBar.m5408$$Nest$fgetactionModeColor(r22));
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$10, reason: invalid class name */
    public class AnonymousClass10 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ ArrayList val$startX;
        final /* synthetic */ ArrayList val$startY;
        final /* synthetic */ ArrayList val$viewsToAnimate;

        public AnonymousClass10(ActionBar r1, ArrayList r2, ArrayList r3, ArrayList r4) {
            this.this$0 = r1;
            this.val$viewsToAnimate = r2;
            this.val$startX = r3;
            this.val$startY = r4;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.getViewTreeObserver().removeOnPreDrawListener(this);
            int r1 = 0;
        L4:
            if (r1 >= this.val$viewsToAnimate.size()) goto L11;
            View r2 = (View) this.val$viewsToAnimate.get(r1);
            if ((r2 instanceof SimpleTextView) == false) goto L9;
            SimpleTextView r4 = (SimpleTextView) r2;
            float r5 = r4.getTextStartX();
            int r42 = r4.getTextStartY();
        L8:
            float r43 = r42;
            float r6 = ((Float) this.val$startX.get(r1)).floatValue() - r5;
            float r52 = ((Float) this.val$startY.get(r1)).floatValue() - r43;
            ObjectAnimator r44 = ObjectAnimator.ofFloat(r2, View.TRANSLATION_X, new float[]{r6, 0.0f});
            ObjectAnimator r22 = ObjectAnimator.ofFloat(r2, View.TRANSLATION_Y, new float[]{r52, 0.0f});
            AnimatorSet r53 = new AnimatorSet();
            r53.playTogether(new Animator[]{r44, r22});
            r53.setDuration(300);
            r53.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r53.start();
            r1 = r1 + 1;
            goto L4
        L9:
            r5 = r2.getLeft();
            r42 = r2.getTop();
            goto L8
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$11, reason: invalid class name */
    public class AnonymousClass11 implements View.OnAttachStateChangeListener {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ AnonymousClass10 val$listener;

        public AnonymousClass11(ActionBar r1, AnonymousClass10 r2) {
            this.this$0 = r1;
            this.val$listener = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            this.this$0.getViewTreeObserver().removeOnPreDrawListener(this.val$listener);
            this.this$0.removeOnAttachStateChangeListener(this);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$12, reason: invalid class name */
    public class AnonymousClass12 extends FrameLayout {
        final /* synthetic */ ActionBar this$0;

        public AnonymousClass12(ActionBar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), View.MeasureSpec.getSize(r2));
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$13, reason: invalid class name */
    public class AnonymousClass13 extends ActionBarAnimatedSubtitleOverlayContainer {
        final /* synthetic */ ActionBar this$0;

        public AnonymousClass13(ActionBar r1, Context r2, Theme.ResourcesProvider r3, EllipsizeSpanAnimator r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarAnimatedSubtitleOverlayContainer, me.vkryl.android.animator.ReplaceAnimator.Callback
        public void onItemChanged(ReplaceAnimator<?> r2) {
            super.onItemChanged(r2);
            float r22 = getTotalVisibility();
            if (ActionBar.m5418$$Nest$fgettitlesContainer(this.this$0) == null) goto L6;
            ActionBar.m5418$$Nest$fgettitlesContainer(this.this$0).setTranslationY(r22 * AndroidUtilities.dp(-11.0f));
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ boolean val$onTop;

        public AnonymousClass14(ActionBar r1, boolean r2) {
            this.this$0 = r1;
            this.val$onTop = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ActionBar r22 = this.this$0;
            if (this.val$onTop == false) goto L5;
            float r0 = 1.0f;
        L6:
            ActionBar.m5426$$Nest$fputonTopAnimated(r22, r0);
            ActionBar.m5428$$Nest$madaptive_updateColor(this.this$0);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ Runnable val$checkScroll;

        public AnonymousClass15(ActionBar r1, Runnable r2) {
            this.this$0 = r1;
            this.val$checkScroll = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.val$checkScroll.run();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$16, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ boolean val$onTop;

        public AnonymousClass16(ActionBar r1, boolean r2) {
            this.this$0 = r1;
            this.val$onTop = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ActionBar r22 = this.this$0;
            if (this.val$onTop == false) goto L5;
            float r0 = 1.0f;
        L6:
            ActionBar.m5426$$Nest$fputonTopAnimated(r22, r0);
            ActionBar.m5428$$Nest$madaptive_updateColor(this.this$0);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ boolean[] val$hideView;

        public AnonymousClass2(ActionBar r1, boolean[] r2) {
            this.this$0 = r1;
            this.val$hideView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0).equals(r2) == false) goto L9;
            ActionBar.m5419$$Nest$fputactionModeAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0).equals(r4) == false) goto L36;
            ActionBar.m5419$$Nest$fputactionModeAnimation(this.this$0, null);
            int r0 = 0;
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[0] == null) goto L10;
            ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[0].setVisibility(4);
        L10:
            if (ActionBar.m5416$$Nest$fgetsubtitleTextView(this.this$0) != null) goto L12;
        L14:
            ActionBarMenu r42 = this.this$0.menu;
            if (r42 == null) goto L18;
            r42.setVisibility(4);
        L18:
            if (ActionBar.m5410$$Nest$fgetactionModeHidingViews(this.this$0) != null) goto L20;
            return;
        L20:
            if (r0 >= ActionBar.m5410$$Nest$fgetactionModeHidingViews(this.this$0).length) goto L38;
            if (ActionBar.m5410$$Nest$fgetactionModeHidingViews(this.this$0)[r0] == null) goto L30;
            boolean[] r43 = this.val$hideView;
            if (r43 != null) goto L26;
        L29:
            ActionBar.m5410$$Nest$fgetactionModeHidingViews(this.this$0)[r0].setVisibility(4);
            goto L30
        L26:
            if (r0 >= r43.length) goto L29;
            if (r43[r0] == true) goto L29;
        L30:
            r0 = r0 + 1;
            goto L20
        L38:
            return;
        L12:
            if (TextUtils.isEmpty(ActionBar.m5415$$Nest$fgetsubtitle(this.this$0)) == true) goto L14;
            ActionBar.m5416$$Nest$fgetsubtitleTextView(this.this$0).setVisibility(4);
            goto L14
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            ActionBar.m5406$$Nest$fgetactionMode(this.this$0).setVisibility(0);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;

        public AnonymousClass3(ActionBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0).equals(r2) == false) goto L9;
            ActionBar.m5419$$Nest$fputactionModeAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ActionBar.m5407$$Nest$fgetactionModeAnimation(this.this$0).equals(r2) == false) goto L11;
            ActionBar.m5419$$Nest$fputactionModeAnimation(this.this$0, null);
            ActionBar.m5406$$Nest$fgetactionMode(this.this$0).setVisibility(4);
            if (ActionBar.m5409$$Nest$fgetactionModeExtraView(this.this$0) == null) goto L12;
            ActionBar.m5409$$Nest$fgetactionModeExtraView(this.this$0).setVisibility(4);
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ boolean val$ignoreTitles;
        final /* synthetic */ ArrayList val$viewsToHide;
        final /* synthetic */ boolean val$visible;

        public AnonymousClass4(ActionBar r1, ArrayList r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$viewsToHide = r2;
            this.val$visible = r3;
            this.val$ignoreTitles = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.val$viewsToHide.size()) goto L11;
            View r1 = (View) this.val$viewsToHide.get(r0);
            if (this.val$visible == false) goto L8;
            r1.setVisibility(4);
            r1.setAlpha(0.0f);
        L9:
            r0 = r0 + 1;
            goto L4
        L8:
            r1.setAlpha(1.0f);
            goto L9
        L11:
            if (this.val$visible == false) goto L21;
            if (this.val$ignoreTitles == true) goto L21;
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[0] == null) goto L18;
            ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[0].setVisibility(8);
        L18:
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] == null) goto L21;
            ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].setVisibility(8);
        L21:
            if (ActionBar.m5411$$Nest$fgetavatarSearchImageView(this.this$0) != null) goto L23;
            return;
        L23:
            if (this.val$visible == true) goto L30;
            ActionBar.m5411$$Nest$fgetavatarSearchImageView(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ int val$targetAvailableWidth;
        final /* synthetic */ int val$targetCenterTitleX;

        public AnonymousClass5(ActionBar r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$targetCenterTitleX = r2;
            this.val$targetAvailableWidth = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ActionBar.m5412$$Nest$fgetcenterTitleLayoutAnimator(this.this$0) != r2) goto L6;
            ActionBar.m5425$$Nest$fputcenterTitleLayoutAnimator(this.this$0, null);
        L6:
            if (this.cancelled == true) goto L8;
            ActionBar.m5422$$Nest$fputanimatedCenterTitleX(this.this$0, this.val$targetCenterTitleX);
            ActionBar.m5421$$Nest$fputanimatedCenterTitleAvailableWidth(this.this$0, this.val$targetAvailableWidth);
        L8:
            ActionBar.m5424$$Nest$fputcenterTitleAnimationTargetX(this.this$0, Integer.MIN_VALUE);
            ActionBar.m5423$$Nest$fputcenterTitleAnimationTargetWidth(this.this$0, -1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;

        public AnonymousClass6(ActionBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] != null) goto L5;
        L7:
            ActionBar r42 = this.this$0;
            r42.ellipsizeSpanAnimator.removeView(ActionBar.m5417$$Nest$fgettitleTextView(r42)[1]);
            ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] = null;
            ActionBar r43 = this.this$0;
            r43.overlayTitleAnimationInProgress = false;
            r43.setTitleOverlayText((String) ActionBar.m5414$$Nest$fgetoverlayTitleToSet(r43)[0], ((Integer) ActionBar.m5414$$Nest$fgetoverlayTitleToSet(this.this$0)[1]).intValue(), (Runnable) ActionBar.m5414$$Nest$fgetoverlayTitleToSet(this.this$0)[2]);
            return;
        L5:
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].getParent() == null) goto L7;
            ((ViewGroup) ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].getParent()).removeView(ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1]);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;
        final /* synthetic */ boolean val$crossfade;
        final /* synthetic */ boolean val$fromBottom;

        public AnonymousClass7(ActionBar r1, boolean r2, boolean r3) {
            this.this$0 = r1;
            this.val$crossfade = r2;
            this.val$fromBottom = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] != null) goto L5;
        L7:
            ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] = null;
            ActionBar.m5427$$Nest$fputtitleAnimationRunning(this.this$0, false);
            if (this.val$crossfade == true) goto L10;
        L12:
            this.this$0.requestLayout();
            return;
        L10:
            if (this.val$fromBottom == false) goto L12;
            ActionBar.m5416$$Nest$fgetsubtitleTextView(this.this$0).setVisibility(8);
            goto L12
        L5:
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].getParent() == null) goto L7;
            ((ViewGroup) ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].getParent()).removeView(ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1]);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBar this$0;

        public AnonymousClass8(ActionBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] != null) goto L5;
        L7:
            ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1] = null;
            ActionBar.m5427$$Nest$fputtitleAnimationRunning(this.this$0, false);
            this.this$0.requestLayout();
            return;
        L5:
            if (ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].getParent() == null) goto L7;
            ((ViewGroup) ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1].getParent()).removeView(ActionBar.m5417$$Nest$fgettitleTextView(this.this$0)[1]);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBar$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends ChangeBounds {
        final /* synthetic */ ActionBar this$0;

        /* renamed from: org.telegram.ui.ActionBar.ActionBar$9$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass9 this$1;
            final /* synthetic */ TransitionValues val$startValues;

            public AnonymousClass1(AnonymousClass9 r1, TransitionValues r2) {
                this.this$1 = r1;
                this.val$startValues = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                this.val$startValues.view.setLayerType(0, null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r2) {
                super.onAnimationStart(r2);
                this.val$startValues.view.setLayerType(2, null);
            }
        }

        public AnonymousClass9(ActionBar r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public void captureEndValues(TransitionValues r2) {
            super.captureEndValues(r2);
            View r1 = r2.view;
            if ((r1 instanceof SimpleTextView) == false) goto L6;
            float r12 = ((SimpleTextView) r1).getTextPaint().getTextSize();
            r2.values.put("text_size", Float.valueOf(r12));
            return;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public void captureStartValues(TransitionValues r2) {
            super.captureStartValues(r2);
            View r1 = r2.view;
            if ((r1 instanceof SimpleTextView) == false) goto L6;
            float r12 = ((SimpleTextView) r1).getTextPaint().getTextSize();
            r2.values.put("text_size", Float.valueOf(r12));
            return;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public Animator createAnimator(ViewGroup r6, TransitionValues r7, TransitionValues r8) {
            if (r7 == null) goto L13;
            if ((r7.view instanceof SimpleTextView) == false) goto L13;
            AnimatorSet r0 = new AnimatorSet();
            if (r8 == null) goto L10;
            Animator r62 = super.createAnimator(r6, r7, r8);
            float r1 = ((Float) r7.values.get("text_size")).floatValue() / ((Float) r8.values.get("text_size")).floatValue();
            r7.view.setScaleX(r1);
            r7.view.setScaleY(r1);
            if (r62 == null) goto L10;
            r0.playTogether(new Animator[]{r62});
        L10:
            r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r7.view, View.SCALE_X, new float[]{1.0f})});
            r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r7.view, View.SCALE_Y, new float[]{1.0f})});
            r0.addListener(new AnonymousClass1(this, r7));
            return r0;
        L13:
            return super.createAnimator(r6, r7, r8);
        }
    }

    public static class ActionBarMenuOnItemClick {
        public ActionBarMenuOnItemClick() {
        }

        public boolean canOpenMenu() {
            return true;
        }

        public void onItemClick(int r1) {
        }
    }

    /* renamed from: $r8$lambda$-lnumnguX8KMjKLOfI9NQ-NTlcE, reason: not valid java name */
    public static /* synthetic */ void m5400$r8$lambda$lnumnguX8KMjKLOfI9NQNTlcE(ActionBar r0, ValueAnimator r1) {
        r0.lambda$hideActionMode$3(r1);
    }

    /* renamed from: $r8$lambda$4X1MLK8j-mRNIqd2G5nAfOGYx3A, reason: not valid java name */
    public static /* synthetic */ void m5401$r8$lambda$4X1MLK8jmRNIqd2G5nAfOGYx3A(ActionBar r0, ValueAnimator r1) {
        r0.lambda$setAdaptiveBackground$8(r1);
    }

    /* renamed from: $r8$lambda$CM3cK5nqbPRjJrj4lTX6E_6Jh-Y, reason: not valid java name */
    public static /* synthetic */ void m5402$r8$lambda$CM3cK5nqbPRjJrj4lTX6E_6JhY(ActionBar r0, View r1) {
        r0.lambda$createBackButtonImage$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$KRQqxpMCLQ0_KACF4ydNNUc26VA(ActionBar r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$L16h8hd_aqpHHJL8gR2pq1w-ybw, reason: not valid java name */
    public static /* synthetic */ void m5403$r8$lambda$L16h8hd_aqpHHJL8gR2pq1wybw(ActionBar r0, ValueAnimator r1) {
        r0.lambda$showActionMode$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$SD7NJDGTaOYMYSI2rSc863DbVD4(ActionBar r0, SectionsScrollView r1) {
        r0.lambda$setAdaptiveBackground$9(r1);
    }

    /* renamed from: $r8$lambda$XAj7ZrMc2Ohy4w1kPd8nLtIg-iU, reason: not valid java name */
    public static /* synthetic */ void m5404$r8$lambda$XAj7ZrMc2Ohy4w1kPd8nLtIgiU(ActionBar r0, ValueAnimator r1) {
        r0.lambda$onSearchFieldVisibilityChanged$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$fAtKgqUtPKoIeIrwK9rLqa4p2Ic(ActionBar r0, float r1, int r2, float r3, int r4, ValueAnimator r5) {
        r0.lambda$updateCenterTitleLayoutAnimation$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$rEQE6Q6P9U9SDkkoXVfLmVaYokQ(ActionBar r0, ValueAnimator r1) {
        r0.lambda$setAdaptiveBackground$6(r1);
    }

    /* renamed from: $r8$lambda$tL2FqD-_ye-2ecDGtj7IFUiErJQ, reason: not valid java name */
    public static /* synthetic */ void m5405$r8$lambda$tL2FqD_ye2ecDGtj7IFUiErJQ(ActionBar r0, RecyclerView r1) {
        r0.lambda$setAdaptiveBackground$7(r1);
    }

    /* renamed from: -$$Nest$fgetactionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenu m5406$$Nest$fgetactionMode(ActionBar r0) {
        return r0.actionMode;
    }

    /* renamed from: -$$Nest$fgetactionModeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m5407$$Nest$fgetactionModeAnimation(ActionBar r0) {
        return r0.actionModeAnimation;
    }

    /* renamed from: -$$Nest$fgetactionModeColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5408$$Nest$fgetactionModeColor(ActionBar r0) {
        return r0.actionModeColor;
    }

    /* renamed from: -$$Nest$fgetactionModeExtraView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5409$$Nest$fgetactionModeExtraView(ActionBar r0) {
        return r0.actionModeExtraView;
    }

    /* renamed from: -$$Nest$fgetactionModeHidingViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View[] m5410$$Nest$fgetactionModeHidingViews(ActionBar r0) {
        return r0.actionModeHidingViews;
    }

    /* renamed from: -$$Nest$fgetavatarSearchImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m5411$$Nest$fgetavatarSearchImageView(ActionBar r0) {
        return r0.avatarSearchImageView;
    }

    /* renamed from: -$$Nest$fgetcenterTitleLayoutAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m5412$$Nest$fgetcenterTitleLayoutAnimator(ActionBar r0) {
        return r0.centerTitleLayoutAnimator;
    }

    /* renamed from: -$$Nest$fgetdoOnActionModeFactorChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5413$$Nest$fgetdoOnActionModeFactorChanged(ActionBar r0) {
        return r0.doOnActionModeFactorChanged;
    }

    /* renamed from: -$$Nest$fgetoverlayTitleToSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object[] m5414$$Nest$fgetoverlayTitleToSet(ActionBar r0) {
        return r0.overlayTitleToSet;
    }

    /* renamed from: -$$Nest$fgetsubtitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m5415$$Nest$fgetsubtitle(ActionBar r0) {
        return r0.subtitle;
    }

    /* renamed from: -$$Nest$fgetsubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView m5416$$Nest$fgetsubtitleTextView(ActionBar r0) {
        return r0.subtitleTextView;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView[] m5417$$Nest$fgettitleTextView(ActionBar r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fgettitlesContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m5418$$Nest$fgettitlesContainer(ActionBar r0) {
        return r0.titlesContainer;
    }

    /* renamed from: -$$Nest$fputactionModeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5419$$Nest$fputactionModeAnimation(ActionBar r0, AnimatorSet r1) {
        r0.actionModeAnimation = r1;
    }

    /* renamed from: -$$Nest$fputactionModeColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5420$$Nest$fputactionModeColor(ActionBar r0, int r1) {
        r0.actionModeColor = r1;
    }

    /* renamed from: -$$Nest$fputanimatedCenterTitleAvailableWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5421$$Nest$fputanimatedCenterTitleAvailableWidth(ActionBar r0, float r1) {
        r0.animatedCenterTitleAvailableWidth = r1;
    }

    /* renamed from: -$$Nest$fputanimatedCenterTitleX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5422$$Nest$fputanimatedCenterTitleX(ActionBar r0, float r1) {
        r0.animatedCenterTitleX = r1;
    }

    /* renamed from: -$$Nest$fputcenterTitleAnimationTargetWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5423$$Nest$fputcenterTitleAnimationTargetWidth(ActionBar r0, int r1) {
        r0.centerTitleAnimationTargetWidth = r1;
    }

    /* renamed from: -$$Nest$fputcenterTitleAnimationTargetX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5424$$Nest$fputcenterTitleAnimationTargetX(ActionBar r0, int r1) {
        r0.centerTitleAnimationTargetX = r1;
    }

    /* renamed from: -$$Nest$fputcenterTitleLayoutAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5425$$Nest$fputcenterTitleLayoutAnimator(ActionBar r0, ValueAnimator r1) {
        r0.centerTitleLayoutAnimator = r1;
    }

    /* renamed from: -$$Nest$fputonTopAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5426$$Nest$fputonTopAnimated(ActionBar r0, float r1) {
        r0.onTopAnimated = r1;
    }

    /* renamed from: -$$Nest$fputtitleAnimationRunning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5427$$Nest$fputtitleAnimationRunning(ActionBar r0, boolean r1) {
        r0.titleAnimationRunning = r1;
    }

    /* renamed from: -$$Nest$madaptive_updateColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5428$$Nest$madaptive_updateColor(ActionBar r0) {
        r0.adaptive_updateColor();
    }

    public ActionBar(Context r2) {
        this(r2, null);
    }

    public ActionBar(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.backButtonState = INavigationLayout.BackButtonState.BACK;
        this.titleTextView = new SimpleTextView[2];
        this.occupyStatusBar = true;
        this.addToContainer = true;
        this.interceptTouches = true;
        this.overlayTitleToSet = new Object[3];
        this.castShadows = true;
        this.shadowAlpha = 255;
        this.titleColorToSet = 0;
        this.animatedCenterTitleX = Float.NaN;
        this.animatedCenterTitleAvailableWidth = Float.NaN;
        this.centerTitleAnimationTargetX = Integer.MIN_VALUE;
        this.centerTitleAnimationTargetWidth = -1;
        this.lastMeasuredWidth = -1;
        this.blurScrimPaint = new Paint();
        this.rectTmp = new Rect();
        this.ellipsizeSpanAnimator = new EllipsizeSpanAnimator(this);
        this.drawGlassMiddlePill = true;
        this.glassPadding = AndroidUtilities.dp(6.0f);
        this.glassTitleTextSize = 17;
        CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorAvatarContainerWidth = new FactorAnimator(0, this, r5, 380);
        this.animatorAvatarContainerHasAvatar = new BoolAnimator(0, this, r5, 380);
        this.animatorMenuItemsWidth = new FactorAnimator(0, this, r5, 320);
        this.animatorHasMenuItems = new BoolAnimator(0, this, r5, 320);
        this.onTop = true;
        this.onTopAnimated = 1.0f;
        this.resourcesProvider = r10;
        setOnClickListener(new ActionBar$$ExternalSyntheticLambda3(this));
    }

    private void adaptive_updateColor() {
        if (this.adaptiveBackground == true) goto L6;
        return;
    L6:
        if (this.adaptiveBackgroundHideTitle == false) goto L14;
        FrameLayout r0 = this.titlesContainer;
        if (r0 == null) goto L10;
        r0.setAlpha(1.0f - this.onTopAnimated);
        goto L14
    L10:
        SimpleTextView r02 = this.titleTextView[0];
        if (r02 == null) goto L14;
        r02.setAlpha(1.0f - this.onTopAnimated);
    L14:
        if (this.glassMode == true) goto L34;
        float r03 = this.onTopAnimated;
        int r3 = this.adaptive_lowerColorKey;
        if (r3 != (-1)) goto L19;
        int r32 = 0;
    L20:
        int r5 = this.adaptive_topColorKey;
        if (r5 != (-1)) goto L23;
        int r4 = 0;
    L24:
        if (r4 != 0) goto L26;
        r4 = ColorUtils.setAlphaComponent(r32, 0);
    L26:
        if (r32 != 0) goto L28;
        r32 = ColorUtils.setAlphaComponent(r4, 0);
    L28:
        setBackgroundColor(ColorUtils.blendARGB(r32, r4, r03));
        setShadowAlpha((int) ((1.0f - this.onTopAnimated) * 255.0f));
        if (this.blurredBackground == false) goto L32;
        invalidate();
        return;
    L32:
        return;
    L23:
        r4 = Theme.getColor(r5, this.resourcesProvider);
        goto L24
    L19:
        r32 = Theme.getColor(r3, this.resourcesProvider);
        goto L20
    }

    private void applyGlassPadding() {
        BlurredBackgroundDrawable r0 = this.glassDrawable;
        if (r0 == null) goto L5;
        r0.setPadding(this.glassPadding);
    L5:
        BlurredBackgroundDrawable r02 = this.glassDrawableBack;
        if (r02 == null) goto L8;
        r02.setPadding(this.glassPadding);
    L8:
        BlurredBackgroundDrawable r03 = this.glassDrawableMenu;
        if (r03 == null) goto L11;
        r03.setPadding(this.glassPadding);
    L11:
        ActionBarMenu r04 = this.menu;
        if (r04 == null) goto L14;
        r04.setTranslationX(getGlassMenuTranslationX());
    L14:
        ActionBarMenu r05 = this.actionMode;
        if (r05 == null) goto L17;
        r05.setTranslationX((-this.glassPadding) - AndroidUtilities.dp(4.0f));
    L17:
        ImageView r06 = this.backButtonImageView;
        if (r06 == null) goto L21;
        r06.setPadding(0, 0, 0, 0);
        this.backButtonImageView.setTranslationX(this.glassPadding - AndroidUtilities.dp(4.0f));
        return;
    }

    private void checkBackButtonLayerType() {
        ImageView r0 = this.backButtonImageView;
        if (r0 == null) goto L17;
        Drawable r02 = r0.getDrawable();
        if ((r02 instanceof BackDrawable) == false) goto L8;
    L11:
        int r03 = 2;
    L13:
        if (this.backButtonImageView.getLayerType() == r03) goto L16;
        this.backButtonImageView.setLayerType(r03, null);
        this.backButtonImageView.invalidate();
        return;
    L16:
        return;
    L8:
        if ((r02 instanceof MenuDrawable) == true) goto L11;
        r03 = 0;
        goto L13
    }

    private void createBackButtonImage() {
        if (this.backButtonImageView != null) goto L10;
        ImageView r0 = new ImageView(getContext());
        this.backButtonImageView = r0;
        r0.setScaleType(ImageView.ScaleType.CENTER);
        this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsBackgroundColor));
        this.backButtonImageView.setPadding(AndroidUtilities.dp(1.0f), 0, 0, 0);
        addView(this.backButtonImageView, LayoutHelper.createFrame(54, 54, 51));
        this.backButtonImageView.setOnClickListener(new ActionBar$$ExternalSyntheticLambda7(this));
        this.backButtonImageView.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
        if (this.glassMode == false) goto L9;
        applyGlassPadding();
        return;
    L9:
        return;
    }

    private void createSubtitleTextView() {
        if (this.subtitleTextView == null) goto L5;
        return;
    L5:
        SimpleTextView r0 = new SimpleTextView(getContext());
        this.subtitleTextView = r0;
        r0.setGravity(getSubtitleGravity());
        this.subtitleTextView.setVisibility(8);
        this.subtitleTextView.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubtitle));
        addView(this.subtitleTextView, 0, LayoutHelper.createFrame(-2, -2, 51));
    }

    private void createTitleTextView(int r5) {
        SimpleTextView[] r0 = this.titleTextView;
        if (r0[r5] == null) goto L5;
        return;
    L5:
        r0[r5] = new SimpleTextView(getContext());
        this.titleTextView[r5].setGravity(getTitleGravity());
        int r02 = this.titleColorToSet;
        SimpleTextView[] r1 = this.titleTextView;
        if (r02 == 0) goto L8;
        r1[r5].setTextColor(r02);
    L9:
        SimpleTextView r03 = this.titleTextView[r5];
        r03.setEmojiColor(r03.getTextColor());
        this.titleTextView[r5].setTypeface(AndroidUtilities.bold());
        this.titleTextView[r5].setDrawablePadding(AndroidUtilities.dp(4.0f));
        this.titleTextView[r5].setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        this.titleTextView[r5].setRightDrawableTopPadding(-AndroidUtilities.dp(1.0f));
        if (this.useContainerForTitles == false) goto L13;
        this.titlesContainer.addView(this.titleTextView[r5], 0, LayoutHelper.createFrame(-2, -2, 51));
        return;
    L13:
        addView(this.titleTextView[r5], 0, LayoutHelper.createFrame(-2, -2, 51));
        return;
    L8:
        r1[r5].setTextColor(getThemedColor(Theme.key_actionBarDefaultTitle));
        goto L9
    }

    public static View findChildUnder(ViewGroup r4, float r5, float r6, View r7) {
        int r0 = r4.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L18;
        View r1 = r4.getChildAt(r0);
        if (r1.getVisibility() != 0) goto L17;
        if (r1 == r7) goto L17;
        if (r5 < r1.getX()) goto L17;
        if (r5 > (r1.getX() + r1.getWidth())) goto L17;
        if (r6 < r1.getTop()) goto L17;
        if (r6 > r1.getBottom()) goto L17;
        return r1;
    L17:
        r0 = r0 - 1;
        goto L3
    L18:
        return null;
    }

    private int getAdaptiveCenterTitleAvailableWidth(int r1, int r2) {
        return Math.max(0, Math.max(r2, getCenterTitleRightBound(r1)) - r2);
    }

    private int getAdaptiveCenterTitleCenterX(int r1, int r2) {
        return r2 + ((Math.max(r2, getCenterTitleRightBound(r1)) - r2) / 2);
    }

    private int getAnimatedCenterTitleAvailableWidth(int r2) {
        if (Float.isNaN(this.animatedCenterTitleAvailableWidth) == false) goto L6;
        return r2;
    L6:
        return Math.max(0, Math.round(this.animatedCenterTitleAvailableWidth));
    }

    private int getAnimatedCenterTitleX(int r2) {
        if (Float.isNaN(this.animatedCenterTitleX) == false) goto L6;
        return r2;
    L6:
        return Math.round(this.animatedCenterTitleX);
    }

    private int getCenterTitleRightBound(int r4) {
        ActionBarMenu r0 = this.menu;
        if (r0 != null) goto L5;
    L17:
        int r3 = AndroidUtilities.dp(16.0f);
    L14:
        return r4 - r3;
    L5:
        if (r0.getVisibility() == 8) goto L17;
        int r02 = this.menu.getMeasuredWidth();
        if (shouldCenterTitle() == false) goto L10;
        r02 = this.menu.getVisibleItemsMeasuredWidthForCenterTitle();
    L10:
        if (this.glassMode == false) goto L16;
        if (r02 <= 0) goto L16;
        r4 = (r4 - r02) - this.glassPadding;
        r3 = AndroidUtilities.dp(16.0f);
    L16:
        return r4 - r02;
    }

    private int getCenteredTitleAvailableWidth(int r2, int r3, boolean r4) {
        if (r4 == true) goto L4;
        int r42 = Math.max(0, r2 - AndroidUtilities.dp(120.0f));
        int r1 = Math.max(r3, getCenterTitleRightBound(r2));
        int r22 = r2 / 2;
        return Math.min(r42, Math.max(0, Math.min(r22 - r3, r1 - r22)) * 2);
    L4:
        return getAdaptiveCenterTitleAvailableWidth(r2, r3);
    }

    public static int getCurrentActionBarHeight() {
        Point r0 = AndroidUtilities.displaySize;
        if (r0.x <= r0.y) goto L7;
        return AndroidUtilities.dp(48.0f);
    L7:
        return AndroidUtilities.dp(56.0f);
    }

    private float getGlassMenuTranslationX() {
        return -AndroidUtilities.lerp(this.glassPadding + AndroidUtilities.dp(4.0f), this.glassPadding - AndroidUtilities.dp(1.0f), this.searchFieldVisibleAlpha);
    }

    private int getGlassPillGap() {
        return Math.min(this.glassPadding, AndroidUtilities.dp(6.0f));
    }

    private int getSearchFieldBackReserve() {
        if (this.menuOccupyBack == false) goto L7;
        return 0;
    L7:
        if (this.glassMode == false) goto L15;
        if (AndroidUtilities.isTablet() == false) goto L11;
        float r0 = 56.0f;
    L13:
        return (AndroidUtilities.dp(r0) + (this.glassPadding * 2)) + getGlassPillGap();
    L11:
        r0 = 48.0f;
        goto L13
    L15:
        if (AndroidUtilities.isTablet() == false) goto L17;
        float r2 = 74.0f;
    L19:
        return AndroidUtilities.dp(r2);
    L17:
        r2 = 66.0f;
        goto L19
    }

    private int getSubtitleGravity() {
        if (shouldCenterTitle() == false) goto L6;
        return 17;
    L6:
        return 3;
    }

    private int getTargetCenterTitleX(int r1, int r2, boolean r3) {
        if (r3 == false) goto L6;
        return getAdaptiveCenterTitleCenterX(r1, r2);
    L6:
        return r1 / 2;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getTitleGravity() {
        if (shouldCenterTitle() == false) goto L6;
        return 17;
    L6:
        return 19;
    }

    private int getTitleLeft(boolean r2) {
        boolean r0 = this.glassMode;
        if (r2 == true) goto L14;
        float r22 = 18.0f;
        if (r0 == false) goto L10;
        int r1 = this.glassPadding;
        int r23 = AndroidUtilities.dp(18.0f);
    L8:
        return r1 + r23;
    L10:
        if (AndroidUtilities.isTablet() == false) goto L13;
        r22 = 26.0f;
    L13:
        return AndroidUtilities.dp(r22);
    L14:
        if (r0 == false) goto L17;
        r1 = this.glassPadding;
        r23 = AndroidUtilities.dp(70.0f);
        goto L8
    L17:
        if (shouldUseDialogsDrawerTitleOffset() == false) goto L25;
        if (AndroidUtilities.isTablet() == false) goto L21;
        float r12 = 68.0f;
    L23:
        return AndroidUtilities.dp(r12);
    L21:
        r12 = 56.0f;
        goto L23
    L25:
        if (AndroidUtilities.isTablet() == false) goto L27;
        float r13 = 80.0f;
    L29:
        return AndroidUtilities.dp(r13);
    L27:
        r13 = 72.0f;
        goto L29
    }

    private int getTitleViewCenterX(int r2, SimpleTextView r3) {
        if (this.useContainerForTitles == true) goto L5;
    L11:
        return r2;
    L5:
        if (this.titlesContainer == null) goto L11;
        if (r3 == null) goto L11;
        ViewParent r32 = r3.getParent();
        FrameLayout r1 = this.titlesContainer;
        if (r32 != r1) goto L11;
        return Math.round(r2 - r1.getTranslationX());
    }

    private Drawable getVisibleTitleRightDrawable(Drawable r2) {
        if (r2 != null) goto L5;
    L15:
        return null;
    L5:
        if (ExteraConfig.getHideActionBarStatus() == true) goto L15;
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L15;
        if ((r2 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == true) goto L12;
    L14:
        return r2;
    L12:
        if (((AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r2).isEmpty() == false) goto L14;
        return null;
    }

    private /* synthetic */ void lambda$createBackButtonImage$1(View r1) {
        if (this.actionModeVisible == false) goto L5;
    L8:
        ActionBarMenuOnItemClick r0 = this.actionBarMenuOnItemClick;
        if (r0 == null) goto L12;
        r0.onItemClick(-1);
        return;
    L12:
        return;
    L5:
        if (this.isSearchFieldVisible == false) goto L8;
        closeSearchField();
    }

    private /* synthetic */ void lambda$hideActionMode$3(ValueAnimator r1) {
        Runnable r0 = this.backgroundUpdateListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (isSearchFieldVisible() == true) goto L10;
        Runnable r0 = this.titleActionRunnable;
        if (r0 == null) goto L9;
        r0.run();
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$onSearchFieldVisibilityChanged$4(ValueAnimator r3) {
        this.searchFieldVisibleAlpha = ((Float) r3.getAnimatedValue()).floatValue();
        if (this.glassDrawable == null) goto L8;
        if (this.glassModeHasAvatar == false) goto L8;
        float r32 = AndroidUtilities.dp(23.0f);
        float r0 = AndroidUtilities.lerp(this.glassDrawableLeftRadius, r32, this.searchFieldVisibleAlpha);
        this.glassDrawable.setRadius(r0, r32, r32, r0);
    L8:
        if (this.glassMode == false) goto L13;
        ActionBarMenu r33 = this.menu;
        if (r33 == null) goto L12;
        r33.setTranslationX(getGlassMenuTranslationX());
    L12:
        invalidate();
    L13:
        Runnable r2 = this.backgroundUpdateListener;
        if (r2 == null) goto L17;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$setAdaptiveBackground$6(ValueAnimator r1) {
        this.onTopAnimated = ((Float) r1.getAnimatedValue()).floatValue();
        adaptive_updateColor();
    }

    private /* synthetic */ void lambda$setAdaptiveBackground$7(RecyclerView r5) {
        boolean r52 = r5.canScrollVertically(-1);
        boolean r0 = !r52;
        if (this.onTop != r0) goto L5;
        return;
    L5:
        ValueAnimator r1 = this.adaptive_animator;
        if (r1 == null) goto L8;
        r1.cancel();
    L8:
        float r12 = this.onTopAnimated;
        this.onTop = r0;
        if (r52 == true) goto L11;
        float r53 = 1.0f;
    L12:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{r12, r53});
        this.adaptive_animator = r54;
        r54.addUpdateListener(new ActionBar$$ExternalSyntheticLambda9(this));
        this.adaptive_animator.addListener(new AnonymousClass14(this, r0));
        this.adaptive_animator.setDuration(320);
        this.adaptive_animator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.adaptive_animator.start();
        return;
    L11:
        r53 = 0.0f;
        goto L12
    }

    private /* synthetic */ void lambda$setAdaptiveBackground$8(ValueAnimator r1) {
        this.onTopAnimated = ((Float) r1.getAnimatedValue()).floatValue();
        adaptive_updateColor();
    }

    private /* synthetic */ void lambda$setAdaptiveBackground$9(SectionsScrollView r5) {
        boolean r52 = r5.canScrollVertically(-1);
        boolean r0 = !r52;
        if (this.onTop != r0) goto L5;
        return;
    L5:
        ValueAnimator r1 = this.adaptive_animator;
        if (r1 == null) goto L8;
        r1.cancel();
    L8:
        float r12 = this.onTopAnimated;
        this.onTop = r0;
        if (r52 == true) goto L11;
        float r53 = 1.0f;
    L12:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{r12, r53});
        this.adaptive_animator = r54;
        r54.addUpdateListener(new ActionBar$$ExternalSyntheticLambda8(this));
        this.adaptive_animator.addListener(new AnonymousClass16(this, r0));
        this.adaptive_animator.setDuration(320);
        this.adaptive_animator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.adaptive_animator.start();
        return;
    L11:
        r53 = 0.0f;
        goto L12
    }

    private /* synthetic */ void lambda$showActionMode$2(ValueAnimator r1) {
        Runnable r0 = this.backgroundUpdateListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$updateCenterTitleLayoutAnimation$5(float r1, int r2, float r3, int r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.animatedCenterTitleX = r1 + ((r2 - r1) * r52);
        this.animatedCenterTitleAvailableWidth = r3 + ((r4 - r3) * r52);
        requestLayout();
    }

    private void resetCenterTitleLayoutAnimation() {
        ValueAnimator r0 = this.centerTitleLayoutAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.centerTitleLayoutAnimator = null;
    L5:
        this.animatedCenterTitleX = Float.NaN;
        this.animatedCenterTitleAvailableWidth = Float.NaN;
        this.centerTitleAnimationTargetX = Integer.MIN_VALUE;
        this.centerTitleAnimationTargetWidth = -1;
    }

    private void setupGlass(BlurredBackgroundDrawableViewFactory r5, BlurredBackgroundColorProvider r6, boolean r7, boolean r8) {
        setBackground(null);
        setClipChildren(false);
        this.glassMode = true;
        this.glassModeHasAvatar = r7;
        float r1 = AndroidUtilities.dp(23.0f);
        if (r7 == false) goto L9;
        boolean r72 = ExteraConfig.getNewChatHeaderStyle();
        int r2 = ChatHeaderUiHelper.getChatAvatarSizeDp();
        int r3 = ChatHeaderUiHelper.getAvatarSizePx(r2);
        int r82 = ChatHeaderUiHelper.getAvatarRadius(r2, r8);
        if (r72 == false) goto L7;
        float r73 = AndroidUtilities.dp(3.33f);
    L8:
        this.glassDrawableLeftRadius = Math.min(r1, r82 + r73);
    L10:
        BlurredBackgroundDrawable r74 = r5.create(this).setColorProvider(r6).setPadding(this.glassPadding);
        float r83 = this.glassDrawableLeftRadius;
        this.glassDrawable = r74.setRadius(r83, r1, r1, r83);
        this.glassDrawableBack = r5.create(this).setColorProvider(r6).setRadius(r1).setPadding(this.glassPadding);
        this.glassDrawableMenu = r5.create(this).setColorProvider(r6).setRadius(r1).setPadding(this.glassPadding);
        ActionBarMenu r52 = this.menu;
        if (r52 == null) goto L13;
        r52.setGlassMode(true);
    L13:
        ActionBarMenu r53 = this.actionMode;
        if (r53 == null) goto L16;
        r53.setGlassMode(true);
    L16:
        applyGlassPadding();
        return;
    L7:
        r73 = (AndroidUtilities.dp(46.0f) - r3) / 2.0f;
        goto L8
    L9:
        this.glassDrawableLeftRadius = r1;
        goto L10
    }

    private boolean shouldAnimateCenterTitleLayout() {
        if (this.attached == true) goto L5;
        return false;
    L5:
        if (getWindowToken() != null) goto L7;
        return false;
    L7:
        if (this.isSearchFieldVisible == false) goto L9;
        return false;
    L9:
        if (this.titleAnimationRunning == true) goto L16;
        return true;
    L16:
        return false;
    }

    private boolean shouldCenterTitle() {
        if (this.forceDisableCenterTitle == false) goto L5;
        return false;
    L5:
        if (this.isCenterTitle == false) goto L7;
        return true;
    L7:
        if (ExteraConfig.getCenterTitle() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean shouldUseAdaptiveCenterTitle() {
        if (shouldCenterTitle() == false) goto L10;
        ActionBarMenu r1 = this.menu;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.getVisibleItemsCount() <= 2) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean shouldUseDialogsDrawerTitleOffset() {
        if ((this.backButtonDrawable instanceof MenuDrawable) == true) goto L5;
        return false;
    L5:
        if (ExteraConfig.getNavigationDrawer() == true) goto L7;
        return false;
    L7:
        if ((this.parentFragment instanceof DialogsActivity) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private void updateAttachState() {
        if (this.attached == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.attachState == r0) goto L16;
        this.attachState = r0;
        EllipsizeSpanAnimator r2 = this.ellipsizeSpanAnimator;
        if (r0 == false) goto L14;
        r2.onAttachedToWindow();
        return;
    L14:
        r2.onDetachedFromWindow();
        return;
    L16:
        return;
    L5:
        if (this.resumed == false) goto L7;
        r0 = true;
        goto L9
    }

    private void updateCenterTitleLayoutAnimation(int r9, int r10, boolean r11) {
        if (shouldCenterTitle() == true) goto L7;
        resetCenterTitleLayoutAnimation();
        return;
    L7:
        if (Float.isNaN(this.animatedCenterTitleX) == true) goto L40;
        if (Float.isNaN(this.animatedCenterTitleAvailableWidth) == true) goto L40;
        if (r11 == true) goto L18;
        ValueAnimator r112 = this.centerTitleLayoutAnimator;
        if (r112 == null) goto L16;
        r112.cancel();
        this.centerTitleLayoutAnimator = null;
    L16:
        this.animatedCenterTitleX = r9;
        this.animatedCenterTitleAvailableWidth = r10;
        this.centerTitleAnimationTargetX = Integer.MIN_VALUE;
        this.centerTitleAnimationTargetWidth = -1;
        return;
    L18:
        float r4 = this.animatedCenterTitleX;
        float r6 = this.animatedCenterTitleAvailableWidth;
        float r113 = r9;
        if (Math.abs(r4 - r113) >= 0.5f) goto L29;
        float r3 = r10;
        if (Math.abs(r6 - r3) >= 0.5f) goto L29;
        ValueAnimator r92 = this.centerTitleLayoutAnimator;
        if (r92 != null) goto L25;
    L27:
        this.animatedCenterTitleX = r113;
        this.animatedCenterTitleAvailableWidth = r3;
        this.centerTitleAnimationTargetX = Integer.MIN_VALUE;
        this.centerTitleAnimationTargetWidth = -1;
        return;
    L25:
        if (r92.isRunning() == true) goto L27;
        this.centerTitleLayoutAnimator = null;
    L29:
        ValueAnimator r114 = this.centerTitleLayoutAnimator;
        if (r114 != null) goto L32;
    L36:
        if (r114 == null) goto L38;
        r114.cancel();
        this.centerTitleLayoutAnimator = null;
    L38:
        ValueAnimator r115 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.centerTitleLayoutAnimator = r115;
        r115.setDuration(260);
        this.centerTitleLayoutAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.centerTitleLayoutAnimator.addUpdateListener(new ActionBar$$ExternalSyntheticLambda6(this, r4, r9, r6, r10));
        this.centerTitleLayoutAnimator.addListener(new AnonymousClass5(this, r9, r10));
        this.centerTitleAnimationTargetX = r9;
        this.centerTitleAnimationTargetWidth = r10;
        this.centerTitleLayoutAnimator.start();
        return;
    L32:
        if (this.centerTitleAnimationTargetX != r9) goto L36;
        if (this.centerTitleAnimationTargetWidth != r10) goto L36;
        return;
    L40:
        this.animatedCenterTitleX = r9;
        this.animatedCenterTitleAvailableWidth = r10;
    }

    private void updateTitleGravity() {
        int r0 = getTitleGravity();
        int r1 = getSubtitleGravity();
        SimpleTextView[] r2 = this.titleTextView;
        int r3 = r2.length;
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L8;
        SimpleTextView r5 = r2[r4];
        if (r5 == null) goto L7;
        r5.setGravity(r0);
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        SimpleTextView r02 = this.subtitleTextView;
        if (r02 == null) goto L11;
        r02.setGravity(r1);
    L11:
        SimpleTextView r6 = this.additionalSubtitleTextView;
        if (r6 == null) goto L18;
        r6.setGravity(r1);
        return;
    }

    public boolean actionModeIsExist(String r2) {
        if (this.actionMode == null) goto L12;
        String r1 = this.actionModeTag;
        if (r1 != null) goto L7;
        if (r2 != null) goto L7;
        return true;
    L7:
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.equals(r2) == false) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    public void alwaysApplyColorFilterToBackButton() {
        this.mAlwaysApplyColorFilterToBackButton = true;
    }

    public void beginDelayedTransition() {
        if (LocaleController.isRTL == true) goto L6;
        TransitionSet r0 = new TransitionSet();
        r0.setOrdering(0);
        r0.addTransition(new Fade());
        r0.addTransition(new AnonymousClass9(this));
        this.centerScale = false;
        r0.setDuration(220);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        TransitionManager.beginDelayedTransition(this, r0);
        return;
    }

    public void checkAvatarContainerWidth(boolean r5) {
        ChatAvatarContainer r0 = this.chatAvatarContainer;
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r02 = r0.hasVisibleAvatar();
        int r1 = Math.min(getMeasuredWidth() - AndroidUtilities.dp(116.0f), this.chatAvatarContainer.getVisualWidth());
        FactorAnimator r2 = this.animatorAvatarContainerWidth;
        if (r5 == false) goto L10;
        float r12 = r1;
        if (r2.getToFactor() == r12) goto L11;
        this.animatorAvatarContainerWidth.animateTo(r12);
    L11:
        this.animatorAvatarContainerHasAvatar.setValue(r02, r5);
        return;
    L10:
        r2.forceFactor(r1);
        goto L11
    }

    public void checkMenuItemsWidth() {
        ActionBarMenu r0 = this.menu;
        boolean r2 = false;
        if (r0 == null) goto L5;
        int r02 = (r0.getItemsWidth() - AndroidUtilities.dp(1.0f)) - AndroidUtilities.dp(1.0f);
    L6:
        int r03 = Math.max(0, r02);
        ActionBarMenu r3 = this.actionMode;
        if (r3 == null) goto L9;
        int r32 = (r3.getItemsWidth() - AndroidUtilities.dp(1.0f)) - AndroidUtilities.dp(1.0f);
    L10:
        int r1 = Math.max(0, r32);
        AndroidUtilities.dp(46.0f);
        if (this.actionModeVisible == false) goto L13;
        r03 = r1;
    L13:
        BoolAnimator r12 = this.animatorHasMenuItems;
        if (r03 <= 0) goto L16;
        r2 = true;
    L16:
        r12.setValue(r2, this.isAnimationsAllowed);
        float r04 = r03;
        if (this.animatorMenuItemsWidth.getToFactor() == r04) goto L24;
        boolean r13 = this.isAnimationsAllowed;
        FactorAnimator r5 = this.animatorMenuItemsWidth;
        if (r13 == false) goto L22;
        r5.animateTo(r04);
        return;
    L22:
        r5.forceFactor(r04);
        return;
    L24:
        return;
    L9:
        r32 = 0;
        goto L10
    L5:
        r02 = 0;
        goto L6
    }

    public void closeSearchField() {
        closeSearchField(true);
    }

    public void closeSearchField(boolean r2) {
        if (this.isSearchFieldVisible == false) goto L9;
        ActionBarMenu r1 = this.menu;
        if (r1 == null) goto L10;
        r1.closeSearchField(r2);
        return;
    L10:
        return;
    }

    public ActionBarMenu createActionMode() {
        return createActionMode(true, null);
    }

    public ActionBarMenu createActionMode(boolean r2, String r3) {
        boolean r22 = actionModeIsExist(r3);
        ActionBarMenu r0 = this.actionMode;
        if (r22 == false) goto L5;
        return r0;
    L5:
        if (r0 == null) goto L7;
        removeView(r0);
        this.actionMode = null;
    L7:
        this.actionModeTag = r3;
        AnonymousClass1 r23 = new AnonymousClass1(this, getContext(), this);
        this.actionMode = r23;
        if (this.glassMode == false) goto L10;
        float r32 = (-this.glassPadding) - AndroidUtilities.dp(4.0f);
    L11:
        r23.setTranslationX(r32);
        this.actionMode.setGlassMode(this.glassMode);
        ActionBarMenu r24 = this.actionMode;
        r24.isActionMode = true;
        r24.setClickable(true);
        if (this.glassMode == true) goto L14;
        this.actionMode.setBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefault));
    L14:
        addView(this.actionMode, indexOfChild(this.backButtonImageView));
        ActionBarMenu r25 = this.actionMode;
        if (this.occupyStatusBar == false) goto L17;
        int r33 = AndroidUtilities.statusBarHeight;
    L18:
        r25.setPadding(0, r33, 0, 0);
        FrameLayout.LayoutParams r26 = (FrameLayout.LayoutParams) this.actionMode.getLayoutParams();
        r26.height = -1;
        r26.width = -1;
        r26.bottomMargin = this.extraHeight;
        r26.gravity = 5;
        this.actionMode.setLayoutParams(r26);
        this.actionMode.setVisibility(4);
        return this.actionMode;
    L17:
        r33 = 0;
        goto L18
    L10:
        r32 = 0.0f;
        goto L11
    }

    public FrameLayout createAdditionalSubTitleOverlayContainer() {
        if (this.additionalSubTitleOverlayContainer != null) goto L6;
        AnonymousClass13 r0 = new AnonymousClass13(this, getContext(), this.resourcesProvider, this.ellipsizeSpanAnimator);
        this.additionalSubTitleOverlayContainer = r0;
        r0.setClipChildren(false);
        addView(this.additionalSubTitleOverlayContainer);
    L6:
        return this.additionalSubTitleOverlayContainer;
    }

    public void createAdditionalSubtitleTextView() {
        if (this.additionalSubtitleTextView == null) goto L5;
        return;
    L5:
        SimpleTextView r0 = new SimpleTextView(getContext());
        this.additionalSubtitleTextView = r0;
        r0.setGravity(getSubtitleGravity());
        this.additionalSubtitleTextView.setVisibility(8);
        this.additionalSubtitleTextView.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubtitle));
        addView(this.additionalSubtitleTextView, 0, LayoutHelper.createFrame(-2, -2, 51));
    }

    public ActionBarMenu createMenu() {
        ActionBarMenu r0 = this.menu;
        if (r0 == null) goto L5;
        return r0;
    L5:
        ActionBarMenu r02 = new ActionBarMenu(getContext(), this);
        this.menu = r02;
        addView(r02, 0, LayoutHelper.createFrame(-2, -1, 5));
        return this.menu;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r19) {
        int r1 = this.glassPadding;
        int r3 = getGlassPillGap();
        int r4 = AndroidUtilities.dp(46.0f);
        float r5 = getActionModeFactor();
        if (this.hasForcedMenuWidth == false) goto L5;
        int r6 = this.forcedMenuWidth;
    L6:
        float r8 = 1.0f;
        if (this.hasForcedMenuMinWidth == false) goto L9;
        r6 = Math.max((int) (this.forcedMenuMinWidth * (1.0f - this.searchFactor)), r6);
    L9:
        ImageView r7 = this.backButtonImageView;
        if (r7 != null) goto L12;
    L14:
        boolean r72 = false;
    L15:
        int r11 = (getHeight() - ((getCurrentActionBarHeight() + r4) / 2)) - r1;
        int r13 = r1 * 2;
        int r12 = (r11 + r4) + r13;
        float r15 = 0.0f;
        if (this.glassOnlyBack == false) goto L19;
        float r16 = 1.0f;
        r8 = 0.0f;
    L23:
        if (this.glassDrawable != null) goto L25;
    L51:
        ChatAvatarContainer r14 = this.chatAvatarContainer;
        if (r14 == null) goto L54;
        r14.setTranslationX(0.0f);
    L54:
        BlurredBackgroundDrawable r17 = this.glassDrawableBack;
        if (r17 == null) goto L58;
        if (r72 == false) goto L58;
        r17.setBounds(0, r11, r4 + r13, r12);
        this.glassDrawableBack.draw(r19);
    L58:
        BlurredBackgroundDrawable r18 = this.glassDrawableMenu;
        if (r18 == null) goto L71;
        if (r6 <= 0) goto L71;
        if (this.glassOnlyBack == true) goto L71;
        if (this.doNotDrawGlassMenu == true) goto L71;
        r18.setBounds((getWidth() - Math.max(r4, r6)) - r13, r11, getWidth(), r12);
        BlurredBackgroundDrawable r110 = this.glassDrawableMenu;
        if (this.hasForcedMenuWidth == false) goto L68;
        int r32 = 255;
    L69:
        r110.setAlpha(r32);
        this.glassDrawableMenu.draw(r19);
        goto L71
    L68:
        r32 = (int) (this.animatorHasMenuItems.getFloatValue() * 255.0f);
    L71:
        if (this.blurredBackground == false) goto L79;
        if (this.actionBarColor == 0) goto L79;
        this.rectTmp.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.blurScrimPaint.setColor(this.actionBarColor);
        boolean r111 = this.adaptiveBackground;
        SizeNotifierFrameLayout r112 = this.contentView;
        if (r111 == false) goto L78;
        r112.drawBlurRect(r19, getY(), this.rectTmp, this.blurScrimPaint, true, r16 - this.onTopAnimated);
        goto L79
    L78:
        r112.drawBlurRect(r19, getY(), this.rectTmp, this.blurScrimPaint, true);
    L79:
        this.isAnimationsAllowed = true;
        if (this.doNotDrawChild == false) goto L82;
        return;
    L82:
        super.dispatchDraw(r19);
        return;
    L25:
        if (r8 <= 0.0f) goto L51;
        if (this.hasForcedMenuWidth == false) goto L29;
    L32:
        if (r6 <= 0) goto L34;
        int r9 = r3;
    L35:
        int r92 = r9 + r6;
        int r33 = r3 + r4;
        int r142 = Math.max(r92, r33);
        if (this.chatAvatarContainer != null) goto L38;
        float r10 = 0.0f;
    L39:
        int r93 = AndroidUtilities.lerp(r92, r142, r10);
        if (r72 == false) goto L42;
        int r102 = r33;
    L44:
        if (this.chatAvatarContainer == null) goto L47;
        r15 = r16 - this.animatorAvatarContainerHasAvatar.getFloatValue();
    L47:
        int r34 = AndroidUtilities.lerp(r102, r33, r15);
        int r103 = getWidth() - r93;
        int r94 = r103 - r34;
        if (this.chatAvatarContainer == null) goto L50;
        int r52 = AndroidUtilities.lerp(Math.min(r94, ((int) this.animatorAvatarContainerWidth.getFactor()) + r13), r94, Math.max(this.searchFactor, r5));
        r34 = ((r103 + r34) - r52) / 2;
        r103 = r34 + r52;
        float r113 = ((r1 + r34) + this.chatAvatarContainer.getGlassPillContentLeft(r4)) - ((ViewGroup.MarginLayoutParams) this.chatAvatarContainer.getLayoutParams()).leftMargin;
        this.chatAvatarContainer.setTranslationX(r113);
        this.chatAvatarContainer.setPivotX((r5.getMeasuredWidth() / 2.0f) - r113);
    L50:
        this.glassDrawable.setBounds(r34, r11, r103, r12);
        this.glassDrawable.setAlpha((int) (r8 * 255.0f));
        this.glassDrawable.draw(r19);
        goto L54
    L42:
        r102 = 0;
        goto L44
    L38:
        r10 = r16 - this.animatorAvatarContainerHasAvatar.getFloatValue();
        goto L39
    L34:
        r9 = 0;
        goto L35
    L29:
        if (this.hasForcedMenuMinWidth == true) goto L32;
        r9 = (int) (r3 * this.animatorHasMenuItems.getFloatValue());
        goto L35
    L19:
        if (this.drawGlassMiddlePill == false) goto L21;
        r16 = 1.0f;
        goto L23
    L21:
        r16 = 1.0f;
        r8 = Math.max(this.searchFactor, this.searchFieldVisibleAlpha);
        goto L23
    L12:
        if (r7.getVisibility() != 0) goto L14;
        r72 = true;
        goto L15
    L5:
        r6 = (int) this.animatorMenuItemsWidth.getFactor();
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r8) {
        if (this.chatAvatarContainer == null) goto L43;
        if (this.glassMode == false) goto L43;
        if (r8.getAction() != 0) goto L43;
        int r0 = (int) r8.getX();
        int r1 = (int) r8.getY();
        float r2 = r0;
        float r3 = r1;
        View r4 = findChildUnder(this, r2, r3, this.chatAvatarContainer);
        if (r4 != null) goto L11;
        r4 = findChildUnder(this, r2, r3, null);
    L11:
        BlurredBackgroundDrawable r22 = this.glassDrawable;
        boolean r32 = true;
        if (r22 != null) goto L14;
    L16:
        boolean r23 = false;
    L18:
        if (this.drawGlassMiddlePill == false) goto L20;
    L22:
        boolean r6 = false;
    L23:
        boolean r24 = r23 | r6;
        if (r4 != null) goto L26;
    L40:
        if (r24 == true) goto L43;
        return false;
    L26:
        if (r4 == this.chatAvatarContainer) goto L40;
        BlurredBackgroundDrawable r42 = this.glassDrawableBack;
        if (r42 != null) goto L30;
    L32:
        boolean r43 = false;
    L33:
        boolean r25 = r24 | r43;
        BlurredBackgroundDrawable r44 = this.glassDrawableMenu;
        if (r44 != null) goto L36;
    L38:
        r32 = false;
    L39:
        r24 = r25 | r32;
        goto L40
    L36:
        if (r44.getBounds().contains(r0, r1) == false) goto L38;
    L30:
        if (r42.getBounds().contains(r0, r1) == false) goto L32;
        r43 = true;
        goto L33
    L20:
        if (r4 != this.chatAvatarContainer) goto L22;
        r6 = true;
        goto L23
    L14:
        if (r22.getBounds().contains(r0, r1) == false) goto L16;
        r23 = true;
    L43:
        return super.dispatchTouchEvent(r8);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r8, View r9, long r10) {
        BaseFragment r0 = this.parentFragment;
        if (r0 == null) goto L10;
        if (r0.getParentLayout() == null) goto L10;
        if (this.parentFragment.getParentLayout().isActionBarInCrossfade() == false) goto L10;
        return false;
    L10:
        if (this.drawBackButton == true) goto L12;
    L14:
        boolean r02 = shouldClipChild(r9);
        if (r02 == false) goto L21;
        r8.save();
        float r3 = -getTranslationY();
        if (this.occupyStatusBar == false) goto L19;
        int r4 = AndroidUtilities.statusBarHeight;
    L20:
        r8.clipRect(0.0f, r3 + r4, getMeasuredWidth(), getMeasuredHeight());
        goto L21
    L19:
        r4 = 0;
    L21:
        boolean r102 = super.drawChild(r8, r9, r10);
        if (this.supportsHolidayImage == true) goto L24;
    L50:
        if (r02 == false) goto L52;
        r8.restore();
    L52:
        return r102;
    L24:
        if (this.titleOverlayShown == true) goto L50;
        if (LocaleController.isRTL == true) goto L50;
        SimpleTextView[] r11 = this.titleTextView;
        if (r9 != r11[0]) goto L30;
    L35:
        Drawable r112 = Theme.getCurrentHolidayDrawable();
        if (r112 != null) goto L38;
    L49:
        drawHolidayEffect(r8);
        goto L50
    L38:
        if (r9 != this.titlesContainer) goto L40;
        SimpleTextView r32 = this.titleTextView[0];
    L41:
        if (r32 == null) goto L49;
        if (r32.getVisibility() != 0) goto L49;
        if ((r32.getText() instanceof String) == false) goto L49;
        TextPaint r42 = r32.getTextPaint();
        r42.getFontMetricsInt(this.fontMetricsInt);
        r42.getTextBounds((String) r32.getText(), 0, 1, this.rect);
        int r1 = (r32.getTextStartX() + Theme.getCurrentHolidayDrawableXOffset()) + ((this.rect.width() - (r112.getIntrinsicWidth() + Theme.getCurrentHolidayDrawableXOffset())) / 2);
        int r2 = ((r32.getTextStartY() + Theme.getCurrentHolidayDrawableYOffset()) + ((int) Math.ceil((r32.getTextHeight() - this.rect.height()) / 2.0f))) + ((int) (AndroidUtilities.dp(8.0f) * (1.0f - this.titlesContainer.getScaleY())));
        r112.setBounds(r1, r2 - r112.getIntrinsicHeight(), r112.getIntrinsicWidth() + r1, r2);
        r112.setAlpha((int) ((this.titlesContainer.getAlpha() * 255.0f) * r32.getAlpha()));
        r112.draw(r8);
        if (this.overlayTitleAnimationInProgress == false) goto L49;
        r9.invalidate();
        invalidate();
        goto L49
    L40:
        r32 = (SimpleTextView) r9;
        goto L41
    L30:
        if (r9 == r11[1]) goto L35;
        if (r9 != this.titlesContainer) goto L50;
        if (this.useContainerForTitles == false) goto L50;
    L12:
        if (r9 != this.backButtonImageView) goto L14;
        return true;
    }

    public boolean drawHolidayEffect(Canvas r5) {
        BaseFragment r0 = this.parentFragment;
        if (r0 == null) goto L10;
        if (r0.getParentLayout() == null) goto L10;
        if (this.parentFragment.getParentLayout().isActionBarInCrossfade() == false) goto L10;
        return false;
    L10:
        if (this.supportsHolidayImage == true) goto L12;
    L38:
        return false;
    L12:
        if (this.titleOverlayShown == true) goto L38;
        if (LocaleController.isRTL == true) goto L38;
        if (Theme.canStartHolidayAnimation() == false) goto L38;
        boolean r02 = this.fireworks;
        if (r02 == false) goto L21;
    L23:
        if (r02 == true) goto L25;
    L28:
        if (this.snowflakesEffect != null) goto L30;
        FireworksEffect r03 = this.fireworksEffect;
        if (r03 == null) goto L38;
        r03.onDraw(this, r5);
        return true;
    L30:
        if (LiteMode.isEnabled(32) == true) goto L32;
        return false;
    L32:
        this.snowflakesEffect.onDraw(this, r5);
        return true;
    L25:
        if (this.snowflakesEffect == null) goto L28;
        this.snowflakesEffect = null;
        this.fireworksEffect = new FireworksEffect();
        goto L28
    L21:
        if (this.snowflakesEffect != null) goto L23;
        this.fireworksEffect = null;
        SnowflakesEffect r04 = new SnowflakesEffect(0);
        this.snowflakesEffect = r04;
        r04.occupyStatusBar = this.occupyStatusBar;
        goto L28
    }

    public ActionBarMenuOnItemClick getActionBarMenuOnItemClick() {
        return this.actionBarMenuOnItemClick;
    }

    public ActionBarMenu getActionMode() {
        return this.actionMode;
    }

    public float getActionModeFactor() {
        ActionBarMenu r0 = this.actionMode;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getAlpha();
    }

    public FrameLayout getAdditionalSubTitleOverlayContainer() {
        return this.additionalSubTitleOverlayContainer;
    }

    public SimpleTextView getAdditionalSubtitleTextView() {
        return this.additionalSubtitleTextView;
    }

    public ImageView getBackButton() {
        return this.backButtonImageView;
    }

    public Drawable getBackButtonDrawable() {
        return this.backButtonDrawable;
    }

    public INavigationLayout.BackButtonState getBackButtonState() {
        return this.backButtonState;
    }

    public int getBackgroundColor() {
        return this.actionBarColor;
    }

    public boolean getCastShadows() {
        return this.castShadows;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public int getGlassMiddlePillChildLeft(int r4) {
        if (this.glassMode == true) goto L6;
        return -1;
    L6:
        int r0 = this.glassPadding;
        int r1 = AndroidUtilities.dp(46.0f);
        ImageView r2 = this.backButtonImageView;
        if (r2 != null) goto L9;
    L11:
        int r3 = 0;
    L13:
        return Math.round((r3 + r0) + ((r1 - r4) / 2.0f));
    L9:
        if (r2.getVisibility() != 0) goto L11;
        r3 = getGlassPillGap() + r1;
        goto L13
    }

    public boolean getOccupyStatusBar() {
        return this.occupyStatusBar;
    }

    public BackupImageView getSearchAvatarImageView() {
        return this.avatarSearchImageView;
    }

    public int getShadowAlpha() {
        return this.shadowAlpha;
    }

    public String getSubtitle() {
        if (this.subtitleTextView == null) goto L9;
        CharSequence r1 = this.subtitle;
        if (r1 != null) goto L8;
        return null;
    L8:
        return r1.toString();
    L9:
        return null;
    }

    public SimpleTextView getSubtitleTextView() {
        return this.subtitleTextView;
    }

    public String getTitle() {
        SimpleTextView r1 = this.titleTextView[0];
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getText().toString();
    }

    public Paint.FontMetricsInt getTitleFontMetricsInt() {
        SimpleTextView r0 = this.titleTextView[0];
        if (r0 != null) goto L13;
        android.text.TextPaint r02 = new android.text.TextPaint(1);
        if (AndroidUtilities.isTablet() == false) goto L7;
    L9:
        float r2 = 20.0f;
    L10:
        r02.setTextSize(AndroidUtilities.dp(r2));
        return r02.getFontMetricsInt();
    L7:
        if (getResources().getConfiguration().orientation != 2) goto L9;
        r2 = 18.0f;
        goto L10
    L13:
        return r0.getPaint().getFontMetricsInt();
    }

    public SimpleTextView getTitleTextView() {
        return this.titleTextView[0];
    }

    public SimpleTextView getTitleTextView2() {
        return this.titleTextView[1];
    }

    public FrameLayout getTitlesContainer() {
        return this.titlesContainer;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void hideActionMode() {
        ActionBarMenu r0 = this.actionMode;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.actionModeVisible == false) goto L81;
        r0.hideAllPopupMenus();
        this.actionModeVisible = false;
        checkMenuItemsWidth();
        ArrayList r1 = new ArrayList();
        Property r6 = View.ALPHA;
        r1.add(ObjectAnimator.ofFloat(this.actionMode, r6, new float[]{0.0f}));
        View[] r2 = this.actionModeHidingViews;
        if (r2 == null) goto L15;
        int r7 = r2.length;
        int r8 = 0;
    L10:
        if (r8 >= r7) goto L15;
        View r9 = r2[r8];
        if (r9 == null) goto L14;
        r9.setVisibility(0);
        r1.add(ObjectAnimator.ofFloat(r9, r6, new float[]{1.0f}));
    L14:
        r8 = r8 + 1;
    L15:
        View r22 = this.actionModeTranslationView;
        Property r72 = View.TRANSLATION_Y;
        if (r22 == null) goto L18;
        r1.add(ObjectAnimator.ofFloat(r22, r72, new float[]{0.0f}));
        this.actionModeTranslationView = null;
    L18:
        View r23 = this.actionModeShowingView;
        if (r23 == null) goto L21;
        r1.add(ObjectAnimator.ofFloat(r23, r6, new float[]{0.0f}));
    L21:
        View r24 = this.actionModeExtraView;
        if (r24 == null) goto L25;
        r1.add(ObjectAnimator.ofFloat(r24, r72, new float[]{r24.getMeasuredHeight()}));
    L25:
        if (this.isSearchFieldVisible == true) goto L34;
        SimpleTextView r25 = this.titleTextView[0];
        if (r25 == null) goto L30;
        r1.add(ObjectAnimator.ofFloat(r25, r6, new float[]{1.0f}));
    L30:
        if (this.subtitleTextView == null) goto L34;
        if (TextUtils.isEmpty(this.subtitle) == true) goto L34;
        r1.add(ObjectAnimator.ofFloat(this.subtitleTextView, r6, new float[]{1.0f}));
    L34:
        ActionBarMenu r26 = this.menu;
        if (r26 == null) goto L37;
        r1.add(ObjectAnimator.ofFloat(r26, r6, new float[]{1.0f}));
    L37:
        int r27 = this.actionBarColor;
        if (r27 != 0) goto L40;
    L46:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
    L47:
        AnimatorSet r28 = this.actionModeAnimation;
        if (r28 == null) goto L50;
        r28.cancel();
    L50:
        AnimatorSet r29 = new AnimatorSet();
        this.actionModeAnimation = r29;
        r29.playTogether(r1);
        if (this.backgroundUpdateListener == null) goto L53;
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r12.addUpdateListener(new ActionBar$$ExternalSyntheticLambda0(this));
        this.actionModeAnimation.playTogether(new Animator[]{r12});
    L53:
        this.actionModeAnimation.setDuration(200);
        this.actionModeAnimation.addListener(new AnonymousClass3(this));
        this.actionModeAnimation.start();
        if (this.isSearchFieldVisible == true) goto L63;
        SimpleTextView r13 = this.titleTextView[0];
        if (r13 == null) goto L59;
        r13.setVisibility(0);
    L59:
        if (this.subtitleTextView == null) goto L63;
        if (TextUtils.isEmpty(this.subtitle) == true) goto L63;
        this.subtitleTextView.setVisibility(0);
    L63:
        ActionBarMenu r14 = this.menu;
        if (r14 == null) goto L66;
        r14.setVisibility(0);
    L66:
        ImageView r02 = this.backButtonImageView;
        if (r02 == null) goto L80;
        Drawable r03 = r02.getDrawable();
        if ((r03 instanceof BackDrawable) == false) goto L72;
        ((BackDrawable) r03).setRotation(0.0f, true);
    L74:
        this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsBackgroundColor));
        return;
    L72:
        if ((r03 instanceof MenuDrawable) == false) goto L74;
        ((MenuDrawable) r03).setRotation(0.0f, true);
        goto L74
    L80:
        return;
    L40:
        if (this.glassMode == true) goto L46;
        if (ColorUtils.calculateLuminance(r27) >= 0.699999988079071d) goto L45;
        AndroidUtilities.setLightStatusBar((Activity) getContext(), false);
        goto L47
    L45:
        AndroidUtilities.setLightStatusBar((Activity) getContext(), true);
        goto L47
    }

    public boolean isActionModeShowed() {
        if (this.actionMode != null) goto L5;
        return false;
    L5:
        if (this.actionModeVisible == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isActionModeShowed(String r2) {
        if (this.actionMode != null) goto L5;
        return false;
    L5:
        if (this.actionModeVisible == false) goto L17;
        String r1 = this.actionModeTag;
        if (r1 != null) goto L9;
        if (r2 != null) goto L9;
        return true;
    L9:
        if (r1 != null) goto L11;
        return false;
    L11:
        if (r1.equals(r2) == false) goto L19;
        return true;
    L19:
        return false;
    L17:
        return false;
    }

    public boolean isSearchFieldVisible() {
        return this.isSearchFieldVisible;
    }

    public void listenToBackgroundUpdate(Runnable r1) {
        this.backgroundUpdateListener = r1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        updateAttachState();
        if (this.actionModeVisible == false) goto L16;
        int r1 = this.actionModeColor;
        if (r1 != 0) goto L7;
        r1 = this.actionBarColor;
    L7:
        if (r1 != 0) goto L9;
    L15:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        goto L16
    L9:
        if (this.glassMode == true) goto L15;
        if (ColorUtils.calculateLuminance(r1) >= 0.699999988079071d) goto L14;
        AndroidUtilities.setLightStatusBar((Activity) getContext(), false);
        goto L16
    L14:
        AndroidUtilities.setLightStatusBar((Activity) getContext(), true);
    L16:
        Drawable r0 = this.lastRightDrawable;
        if ((r0 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == false) goto L26;
        SimpleTextView r12 = this.titleTextView[0];
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = (AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r0;
        if (r12 != null) goto L21;
    L23:
        r12 = null;
    L24:
        r02.setParentView(r12);
        return;
    L21:
        if (r12.getRightDrawable() != this.lastRightDrawable) goto L23;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        boolean r0 = false;
        this.attached = false;
        ValueAnimator r1 = this.centerTitleLayoutAnimator;
        if (r1 == null) goto L5;
        r1.cancel();
        this.centerTitleLayoutAnimator = null;
    L5:
        updateAttachState();
        if (this.actionModeVisible == true) goto L8;
    L19:
        Drawable r7 = this.lastRightDrawable;
        if ((r7 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == false) goto L23;
        ((AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r7).setParentView(null);
        return;
    L23:
        return;
    L8:
        if (this.actionBarColor != 0) goto L10;
    L18:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        goto L19
    L10:
        if (this.actionModeColor == 0) goto L18;
        if (this.glassMode == true) goto L18;
        Window r12 = ((Activity) getContext()).getWindow();
        if (ColorUtils.calculateLuminance(this.actionBarColor) < 0.699999988079071d) goto L17;
        r0 = true;
    L17:
        AndroidUtilities.setLightStatusBar(r12, r0);
        goto L19
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        invalidate();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r5) {
        if (this.supportsHolidayImage == true) goto L5;
    L22:
        View.OnTouchListener r0 = this.interceptTouchEventListener;
        if (r0 == null) goto L27;
        if (r0.onTouch(this, r5) == false) goto L27;
        return true;
    L27:
        if (super.onInterceptTouchEvent(r5) == true) goto L31;
        return false;
    L31:
        return true;
    L5:
        if (this.titleOverlayShown == true) goto L22;
        if (LocaleController.isRTL == true) goto L22;
        if (r5.getAction() != 0) goto L22;
        Drawable r02 = Theme.getCurrentHolidayDrawable();
        if (r02 == null) goto L22;
        if (r02.getBounds().contains((int) r5.getX(), (int) r5.getY()) == false) goto L22;
        boolean r03 = this.fireworks;
        this.fireworks = !r03;
        if (r03 == false) goto L17;
    L20:
        this.fireworksEffect = null;
        SnowflakesEffect r04 = new SnowflakesEffect(0);
        this.snowflakesEffect = r04;
        r04.occupyStatusBar = this.occupyStatusBar;
    L21:
        this.titleTextView[0].invalidate();
        invalidate();
        goto L22
    L17:
        if (this.snowflakesEffect == null) goto L20;
        this.snowflakesEffect = null;
        this.fireworksEffect = new FireworksEffect();
        goto L21
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r14, int r15, int r16, int r17, int r18) {
        if (this.occupyStatusBar == false) goto L5;
        int r142 = AndroidUtilities.statusBarHeight;
    L7:
        if (this.prevWidth == getMeasuredWidth()) goto L9;
        this.prevWidth = getMeasuredWidth();
        checkAvatarContainerWidth(this.animatorAvatarContainerWidth.isAnimating());
    L9:
        ImageView r1 = this.backButtonImageView;
        if (r1 != null) goto L12;
    L14:
        int r12 = getTitleLeft(false);
    L15:
        int r13 = r12 + this.additionalTextLeft;
        ActionBarMenu r4 = this.menu;
        if (r4 != null) goto L18;
    L24:
        boolean r42 = shouldUseAdaptiveCenterTitle();
        int r5 = getTargetCenterTitleX(getMeasuredWidth(), r13, r42);
        updateCenterTitleLayoutAnimation(r5, getCenteredTitleAvailableWidth(getMeasuredWidth(), r13, r42), shouldAnimateCenterTitleLayout());
        int r43 = getAnimatedCenterTitleX(r5);
        int r52 = 0;
    L25:
        float r6 = 2.0f;
        if (r52 >= 2) goto L59;
        SimpleTextView r8 = this.titleTextView[r52];
        if (r8 == null) goto L57;
        if (r8.getVisibility() == 8) goto L57;
        boolean r82 = this.fromBottom;
        if (r82 == false) goto L34;
        if (r52 != 0) goto L34;
    L37:
        if (this.overlayTitleAnimation == true) goto L39;
    L41:
        SimpleTextView r83 = this.subtitleTextView;
        if (r83 != null) goto L44;
    L52:
        int r62 = (getCurrentActionBarHeight() - this.titleTextView[r52].getTextHeight()) / 2;
    L53:
        boolean r84 = shouldCenterTitle();
        SimpleTextView[] r9 = this.titleTextView;
        if (r84 == false) goto L56;
        int r85 = getTitleViewCenterX(r43, r9[r52]);
        SimpleTextView r92 = this.titleTextView[r52];
        int r63 = r62 + r142;
        r92.layout(r85 - (r92.getMeasuredWidth() / 2), r63 - this.titleTextView[r52].getPaddingTop(), r85 + (this.titleTextView[r52].getMeasuredWidth() / 2), ((r63 + this.titleTextView[r52].getTextHeight()) - this.titleTextView[r52].getPaddingTop()) + this.titleTextView[r52].getPaddingBottom());
        goto L57
    L56:
        SimpleTextView r7 = r9[r52];
        int r64 = r62 + r142;
        r7.layout(r13, r64 - r7.getPaddingTop(), this.titleTextView[r52].getMeasuredWidth() + r13, ((r64 + this.titleTextView[r52].getTextHeight()) - this.titleTextView[r52].getPaddingTop()) + this.titleTextView[r52].getPaddingBottom());
        goto L57
    L44:
        if (r83.getVisibility() == 8) goto L52;
        int r86 = (((getCurrentActionBarHeight() / 2) - this.titleTextView[r52].getTextHeight()) / 2) + AndroidUtilities.dp(2.0f);
        if (AndroidUtilities.isTablet() == false) goto L48;
    L50:
        r6 = 3.0f;
    L51:
        r62 = AndroidUtilities.dp(r6) + r86;
        goto L53
    L48:
        if (getResources().getConfiguration().orientation != 2) goto L50;
    L39:
        if (this.titleAnimationRunning == false) goto L41;
        r62 = (getCurrentActionBarHeight() - this.titleTextView[r52].getTextHeight()) / 2;
    L34:
        if (r82 == true) goto L41;
        if (r52 != 1) goto L41;
    L57:
        r52 = r52 + 1;
        goto L25
    L59:
        if (this.additionalSubTitleOverlayContainer == null) goto L64;
        int r53 = ((getCurrentActionBarHeight() / 2) + (((getCurrentActionBarHeight() / 2) - this.additionalSubTitleOverlayContainer.getMeasuredHeight()) / 2)) - AndroidUtilities.dp(2.0f);
        boolean r87 = shouldCenterTitle();
        ActionBarAnimatedSubtitleOverlayContainer r93 = this.additionalSubTitleOverlayContainer;
        if (r87 == false) goto L63;
        int r54 = r53 + r142;
        r93.layout(r43 - (r93.getMeasuredWidth() / 2), r54, (this.additionalSubTitleOverlayContainer.getMeasuredWidth() / 2) + r43, this.additionalSubTitleOverlayContainer.getMeasuredHeight() + r54);
        goto L64
    L63:
        int r55 = r53 + r142;
        r93.layout(r13, r55, r93.getMeasuredWidth() + r13, this.additionalSubTitleOverlayContainer.getMeasuredHeight() + r55);
    L64:
        SimpleTextView r56 = this.subtitleTextView;
        if (r56 != null) goto L67;
    L72:
        SimpleTextView r57 = this.additionalSubtitleTextView;
        if (r57 != null) goto L75;
    L83:
        BackupImageView r19 = this.avatarSearchImageView;
        if (r19 == null) goto L86;
        r19.layout(AndroidUtilities.dp(64.0f), ((getCurrentActionBarHeight() - this.avatarSearchImageView.getMeasuredHeight()) / 2) + r142, AndroidUtilities.dp(64.0f) + this.avatarSearchImageView.getMeasuredWidth(), r142 + ((getCurrentActionBarHeight() + this.avatarSearchImageView.getMeasuredHeight()) / 2));
    L86:
        int r143 = getChildCount();
        int r110 = 0;
    L87:
        if (r110 >= r143) goto L128;
        View r44 = getChildAt(r110);
        if (r44.getVisibility() == 8) goto L127;
        SimpleTextView[] r58 = this.titleTextView;
        if (r44 == r58[0]) goto L127;
        if (r44 == r58[1]) goto L127;
        if (r44 == this.additionalSubTitleOverlayContainer) goto L127;
        if (r44 == this.subtitleTextView) goto L127;
        if (r44 == this.menu) goto L127;
        if (r44 == this.backButtonImageView) goto L127;
        if (r44 == this.additionalSubtitleTextView) goto L127;
        if (r44 == this.avatarSearchImageView) goto L127;
        FrameLayout.LayoutParams r59 = (FrameLayout.LayoutParams) r44.getLayoutParams();
        int r65 = r44.getMeasuredWidth();
        int r88 = r44.getMeasuredHeight();
        int r94 = r59.gravity;
        if (r94 != (-1)) goto L110;
        r94 = 51;
    L110:
        int r10 = r94 & 112;
        int r95 = r94 & 7;
        if (r95 != 1) goto L113;
        int r96 = ((getMeasuredWidth() - r65) / 2) + r59.leftMargin;
        int r11 = r59.rightMargin;
    L116:
        int r97 = r96 - r11;
    L119:
        if (r10 != 16) goto L121;
        int r102 = (((r18 - r16) - r88) / 2) + r59.topMargin;
        int r510 = r59.bottomMargin;
    L124:
        int r511 = r102 - r510;
    L126:
        r44.layout(r97, r511, r65 + r97, r88 + r511);
        goto L127
    L121:
        if (r10 == 80) goto L123;
        r511 = r59.topMargin;
        goto L126
    L123:
        r102 = (r18 - r16) - r88;
        r510 = r59.bottomMargin;
        goto L124
    L113:
        if (r95 == 5) goto L115;
        r97 = r59.leftMargin;
        goto L119
    L115:
        r96 = getMeasuredWidth() - r65;
        r11 = r59.rightMargin;
    L127:
        r110 = r110 + 1;
        goto L87
    L128:
        return;
    L75:
        if (r57.getVisibility() == 8) goto L83;
        int r512 = (getCurrentActionBarHeight() / 2) + (((getCurrentActionBarHeight() / 2) - this.additionalSubtitleTextView.getTextHeight()) / 2);
        if (AndroidUtilities.isTablet() == true) goto L79;
        int r66 = getResources().getConfiguration().orientation;
    L79:
        int r513 = r512 - AndroidUtilities.dp(1.0f);
        boolean r67 = shouldCenterTitle();
        SimpleTextView r89 = this.additionalSubtitleTextView;
        if (r67 == false) goto L82;
        int r514 = r513 + r142;
        r89.layout(r43 - (r89.getMeasuredWidth() / 2), r514, r43 + (this.additionalSubtitleTextView.getMeasuredWidth() / 2), this.additionalSubtitleTextView.getTextHeight() + r514);
        goto L83
    L82:
        int r515 = r513 + r142;
        r89.layout(r13, r515, r89.getMeasuredWidth() + r13, this.additionalSubtitleTextView.getTextHeight() + r515);
        goto L83
    L67:
        if (r56.getVisibility() == 8) goto L72;
        int r516 = ((getCurrentActionBarHeight() / 2) + (((getCurrentActionBarHeight() / 2) - this.subtitleTextView.getTextHeight()) / 2)) - AndroidUtilities.dp(2.0f);
        boolean r68 = shouldCenterTitle();
        SimpleTextView r810 = this.subtitleTextView;
        if (r68 == false) goto L71;
        int r517 = r516 + r142;
        r810.layout(r43 - (r810.getMeasuredWidth() / 2), r517, (this.subtitleTextView.getMeasuredWidth() / 2) + r43, this.subtitleTextView.getTextHeight() + r517);
        goto L72
    L71:
        int r518 = r516 + r142;
        r810.layout(r13, r518, r810.getMeasuredWidth() + r13, this.subtitleTextView.getTextHeight() + r518);
        goto L72
    L18:
        if (r4.getVisibility() == 8) goto L24;
        if (this.menu.searchFieldVisible() == false) goto L22;
        int r45 = getSearchFieldBackReserve();
    L23:
        ActionBarMenu r519 = this.menu;
        r519.layout(r45, r142, r519.getMeasuredWidth() + r45, this.menu.getMeasuredHeight() + r142);
        goto L24
    L22:
        r45 = getMeasuredWidth() - this.menu.getMeasuredWidth();
        goto L23
    L12:
        if (r1.getVisibility() == 8) goto L14;
        ImageView r111 = this.backButtonImageView;
        r111.layout(0, r142, r111.getMeasuredWidth(), this.backButtonImageView.getMeasuredHeight() + r142);
        r12 = getTitleLeft(true);
        goto L15
    L5:
        r142 = 0;
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r19, int r20) {
        ActionBar r0 = this;
        int r1 = View.MeasureSpec.getSize(r19);
        int r2 = View.MeasureSpec.getSize(r20);
        int r3 = getCurrentActionBarHeight();
        int r4 = View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30);
        int r5 = r0.lastMeasuredWidth;
        if (r5 <= 0) goto L6;
        if (r5 == r1) goto L6;
        r0.resetCenterTitleLayoutAnimation();
    L6:
        r0.lastMeasuredWidth = r1;
        int r7 = 1;
        r0.ignoreLayoutRequest = true;
        View r52 = r0.actionModeTop;
        if (r52 == null) goto L9;
        ((FrameLayout.LayoutParams) r52.getLayoutParams()).height = AndroidUtilities.statusBarHeight;
    L9:
        ActionBarMenu r53 = r0.actionMode;
        if (r53 != null) goto L12;
    L16:
        r0.ignoreLayoutRequest = false;
        if (r0.occupyStatusBar == false) goto L19;
        int r54 = AndroidUtilities.statusBarHeight;
    L20:
        r0.setMeasuredDimension(r1, (r3 + r54) + r0.extraHeight);
        ImageView r32 = r0.backButtonImageView;
        if (r32 != null) goto L23;
    L25:
        int r33 = r0.getTitleLeft(false);
    L26:
        ActionBarMenu r55 = r0.menu;
        if (r55 == null) goto L47;
        if (r55.getVisibility() == 8) goto L47;
        if (r0.menu.searchFieldVisible() == false) goto L38;
        if (r0.isSearchFieldVisible == true) goto L38;
        r0.menu.measure(View.MeasureSpec.makeMeasureSpec(r1, Integer.MIN_VALUE), r4);
        int r56 = r0.menu.getItemsMeasuredWidth(true);
        int r12 = View.MeasureSpec.makeMeasureSpec((r1 - r0.getSearchFieldBackReserve()) + r0.menu.getItemsMeasuredWidth(true), TLObject.FLAG_30);
        if (r0.isMenuOffsetSuppressed == true) goto L45;
        r0.menu.translateXItems(-r56);
    L45:
        r0.menu.measure(r12, r4);
    L38:
        if (r0.isSearchFieldVisible == false) goto L42;
        r12 = View.MeasureSpec.makeMeasureSpec(r1 - r0.getSearchFieldBackReserve(), TLObject.FLAG_30);
        if (r0.isMenuOffsetSuppressed == true) goto L45;
        r0.menu.translateXItems(0.0f);
        goto L45
    L42:
        r12 = View.MeasureSpec.makeMeasureSpec(r1, Integer.MIN_VALUE);
        if (r0.isMenuOffsetSuppressed == true) goto L45;
        r0.menu.translateXItems(0.0f);
    L47:
        if (r0.shouldCenterTitle() == true) goto L49;
        r0.resetCenterTitleLayoutAnimation();
    L49:
        boolean r42 = r0.shouldUseAdaptiveCenterTitle();
        int r57 = 0;
    L51:
        if (r57 >= 2) goto L171;
        SimpleTextView r13 = r0.titleTextView[0];
        if (r13 != null) goto L55;
    L56:
        SimpleTextView r132 = r0.subtitleTextView;
        if (r132 != null) goto L59;
    L169:
        int r202 = r7;
    L170:
        r57 = r57 + 1;
        r7 = r202;
        goto L51
    L59:
        if (r132.getVisibility() == 8) goto L169;
    L61:
        if (r0.shouldCenterTitle() == false) goto L63;
        int r133 = r0.getAnimatedCenterTitleAvailableWidth(r0.getCenteredTitleAvailableWidth(r1, r33, r42));
    L68:
        int r134 = Math.max(r133, 0);
        boolean r14 = r0.fromBottom;
        int r15 = 20;
        if (r14 == false) goto L71;
        if (r57 != 0) goto L71;
    L74:
        if (r0.overlayTitleAnimation == true) goto L76;
    L86:
        r202 = r7;
        SimpleTextView r72 = r0.titleTextView[0];
        if (r72 != null) goto L89;
    L115:
        SimpleTextView r73 = r0.titleTextView[r57];
        if (r73 != null) goto L118;
    L128:
        SimpleTextView r74 = r0.subtitleTextView;
        if (r74 != null) goto L131;
    L139:
        SimpleTextView r75 = r0.additionalSubtitleTextView;
        if (r75 != null) goto L142;
    L148:
        SimpleTextView r76 = r0.titleTextView[r57];
        if (r76 != null) goto L151;
    L156:
        SimpleTextView r77 = r0.subtitleTextView;
        if (r77 != null) goto L159;
    L161:
        ActionBarAnimatedSubtitleOverlayContainer r78 = r0.additionalSubTitleOverlayContainer;
        if (r78 == null) goto L164;
        r78.measure(View.MeasureSpec.makeMeasureSpec(r134, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r2, Integer.MIN_VALUE));
    L164:
        SimpleTextView r79 = r0.additionalSubtitleTextView;
        if (r79 == null) goto L170;
        if (r79.getVisibility() == 8) goto L170;
        r0.additionalSubtitleTextView.measure(View.MeasureSpec.makeMeasureSpec(r134, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), Integer.MIN_VALUE));
        goto L170
    L159:
        if (r77.getVisibility() == 8) goto L161;
        r0.subtitleTextView.measure(View.MeasureSpec.makeMeasureSpec(r134, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), Integer.MIN_VALUE));
        goto L161
    L151:
        if (r76.getVisibility() == 8) goto L156;
        r0.titleTextView[r57].measure(View.MeasureSpec.makeMeasureSpec(r134, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec((AndroidUtilities.dp(24.0f) + r0.titleTextView[r57].getPaddingTop()) + r0.titleTextView[r57].getPaddingBottom(), Integer.MIN_VALUE));
        boolean r710 = r0.centerScale;
        SimpleTextView[] r122 = r0.titleTextView;
        if (r710 == false) goto L155;
        CharSequence r711 = r122[r57].getText();
        SimpleTextView r123 = r0.titleTextView[r57];
        r123.setPivotX(r123.getTextPaint().measureText(r711, 0, r711.length()) / 2.0f);
        r0.titleTextView[r57].setPivotY(AndroidUtilities.dp(24.0f) >> 1);
        goto L156
    L155:
        r122[r57].setPivotX(0.0f);
        r0.titleTextView[r57].setPivotY(0.0f);
        goto L156
    L142:
        if (AndroidUtilities.isTablet() == false) goto L144;
    L146:
        int r142 = 16;
    L147:
        r75.setTextSize(r142);
        goto L148
    L144:
        if (r0.getResources().getConfiguration().orientation != 2) goto L146;
        r142 = 14;
        goto L147
    L131:
        if (r74.getVisibility() == 8) goto L139;
        SimpleTextView r712 = r0.subtitleTextView;
        if (AndroidUtilities.isTablet() == false) goto L135;
    L137:
        int r143 = 16;
    L138:
        r712.setTextSize(r143);
        goto L139
    L135:
        if (r0.getResources().getConfiguration().orientation != 2) goto L137;
        r143 = 14;
        goto L138
    L118:
        if (r73.getVisibility() == 8) goto L128;
        SimpleTextView r713 = r0.titleTextView[r57];
        if (r0.glassMode == false) goto L123;
        r15 = r0.glassTitleTextSize;
    L127:
        r713.setTextSize(r15);
        goto L128
    L123:
        if (AndroidUtilities.isTablet() == true) goto L127;
        if (r0.getResources().getConfiguration().orientation != 2) goto L127;
        r15 = 18;
        goto L127
    L89:
        if (r72.getVisibility() == 8) goto L115;
        SimpleTextView r714 = r0.subtitleTextView;
        if (r714 == null) goto L115;
        if (r714.getVisibility() == 8) goto L115;
        SimpleTextView r715 = r0.titleTextView[r57];
        if (r715 != null) goto L97;
    L104:
        SimpleTextView r716 = r0.subtitleTextView;
        if (AndroidUtilities.isTablet() == false) goto L107;
        int r124 = 16;
    L108:
        r716.setTextSize(r124);
        SimpleTextView r717 = r0.additionalSubtitleTextView;
        if (r717 == null) goto L148;
        if (AndroidUtilities.isTablet() == false) goto L113;
        int r144 = 16;
    L114:
        r717.setTextSize(r144);
        goto L148
    L113:
        r144 = 14;
        goto L114
    L107:
        r124 = 14;
        goto L108
    L97:
        if (r0.glassMode == false) goto L100;
        r15 = r0.glassTitleTextSize;
    L103:
        r715.setTextSize(r15);
        goto L104
    L100:
        if (AndroidUtilities.isTablet() == true) goto L103;
        r15 = 18;
        goto L103
    L76:
        if (r0.titleAnimationRunning == false) goto L86;
        SimpleTextView r145 = r0.titleTextView[r57];
        r202 = r7;
        if (r0.glassMode == false) goto L81;
        r15 = r0.glassTitleTextSize;
    L85:
        r145.setTextSize(r15);
        goto L148
    L81:
        if (AndroidUtilities.isTablet() == true) goto L85;
        if (r0.getResources().getConfiguration().orientation != 2) goto L85;
        r15 = 18;
    L71:
        if (r14 == true) goto L86;
        if (r57 != r7) goto L86;
    L63:
        ActionBarMenu r135 = r0.menu;
        if (r135 == null) goto L66;
        int r136 = r135.getMeasuredWidth();
    L67:
        r133 = (((r1 - r136) - AndroidUtilities.dp(16.0f)) - r33) - r0.titleRightMargin;
        goto L68
    L66:
        r136 = 0;
        goto L67
    L55:
        if (r13.getVisibility() != 8) goto L61;
    L171:
        int r203 = r7;
        BackupImageView r16 = r0.avatarSearchImageView;
        if (r16 == null) goto L174;
        r16.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30));
    L174:
        int r718 = r0.getChildCount();
        int r10 = 0;
    L175:
        if (r10 >= r718) goto L197;
        View r17 = r0.getChildAt(r10);
        if (r17.getVisibility() == 8) goto L196;
        SimpleTextView[] r22 = r0.titleTextView;
        if (r17 == r22[0]) goto L196;
        if (r17 == r22[r203]) goto L196;
        if (r17 == r0.additionalSubTitleOverlayContainer) goto L196;
        if (r17 == r0.subtitleTextView) goto L196;
        if (r17 == r0.menu) goto L196;
        if (r17 == r0.backButtonImageView) goto L196;
        if (r17 == r0.additionalSubtitleTextView) goto L196;
        if (r17 == r0.avatarSearchImageView) goto L196;
        r0.measureChildWithMargins(r17, r19, 0, View.MeasureSpec.makeMeasureSpec(r0.getMeasuredHeight(), TLObject.FLAG_30), 0);
    L196:
        r10 = r10 + 1;
        r0 = this;
        goto L175
    L197:
        return;
    L23:
        if (r32.getVisibility() == 8) goto L25;
        r0.backButtonImageView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(54.0f), TLObject.FLAG_30), r4);
        r33 = r0.getTitleLeft(true);
        goto L26
    L19:
        r54 = 0;
        goto L20
    L12:
        if (r0.occupyStatusBar == false) goto L14;
        int r9 = AndroidUtilities.statusBarHeight;
    L15:
        r53.setPadding(0, r9, 0, 0);
        goto L16
    L14:
        r9 = 0;
        goto L15
    }

    public void onMenuButtonPressed() {
        if (isActionModeShowed() == true) goto L10;
        ActionBarMenu r1 = this.menu;
        if (r1 == null) goto L9;
        r1.onMenuButtonPressed();
        return;
    L9:
        return;
    }

    public void onPause() {
        this.resumed = false;
        updateAttachState();
        ActionBarMenu r1 = this.menu;
        if (r1 == null) goto L6;
        r1.hideAllPopupMenus();
        return;
    }

    public void onResume() {
        this.resumed = true;
        updateAttachState();
    }

    public boolean onSearchChangedIgnoreTitles() {
        return false;
    }

    public void onSearchFieldVisibilityChanged(boolean r14) {
        this.isSearchFieldVisible = r14;
        checkMenuItemsWidth();
        AnimatorSet r0 = this.searchVisibleAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.searchVisibleAnimator = new AnimatorSet();
        ArrayList r02 = new ArrayList();
        boolean r1 = onSearchChangedIgnoreTitles();
        if (r1 == true) goto L19;
        SimpleTextView r3 = this.titleTextView[0];
        if (r3 == null) goto L11;
        r02.add(r3);
    L11:
        if (this.subtitleTextView == null) goto L19;
        if (TextUtils.isEmpty(this.subtitle) == true) goto L19;
        r02.add(this.subtitleTextView);
        SimpleTextView r32 = this.subtitleTextView;
        if (r14 == false) goto L17;
        int r4 = 4;
    L18:
        r32.setVisibility(r4);
        goto L19
    L17:
        r4 = 0;
    L19:
        float r33 = this.searchFieldVisibleAlpha;
        float r42 = 0.0f;
        if (r14 == false) goto L22;
        float r6 = 1.0f;
    L23:
        ValueAnimator r62 = ValueAnimator.ofFloat(new float[]{r33, r6});
        r62.addUpdateListener(new ActionBar$$ExternalSyntheticLambda1(this));
        this.searchVisibleAnimator.playTogether(new Animator[]{r62});
        int r63 = 0;
    L24:
        int r7 = r02.size();
        Property r8 = View.ALPHA;
        if (r63 >= r7) goto L42;
        View r72 = (View) r02.get(r63);
        float r9 = 0.95f;
        if (r14 == true) goto L29;
        r72.setVisibility(0);
        r72.setAlpha(0.0f);
        r72.setScaleX(0.95f);
        r72.setScaleY(0.95f);
    L29:
        AnimatorSet r10 = this.searchVisibleAnimator;
        if (r14 == false) goto L32;
        float r11 = 0.0f;
    L33:
        r10.playTogether(new Animator[]{ObjectAnimator.ofFloat(r72, r8, new float[]{r11})});
        AnimatorSet r82 = this.searchVisibleAnimator;
        if (r14 == false) goto L36;
        float r102 = 0.95f;
    L37:
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(r72, View.SCALE_Y, new float[]{r102})});
        AnimatorSet r83 = this.searchVisibleAnimator;
        if (r14 == true) goto L41;
        r9 = 1.0f;
    L41:
        r83.playTogether(new Animator[]{ObjectAnimator.ofFloat(r72, View.SCALE_X, new float[]{r9})});
        r63 = r63 + 1;
        goto L24
    L36:
        r102 = 1.0f;
        goto L37
    L32:
        r11 = 1.0f;
        goto L33
    L42:
        BackupImageView r64 = this.avatarSearchImageView;
        if (r64 == null) goto L49;
        r64.setVisibility(0);
        AnimatorSet r65 = this.searchVisibleAnimator;
        BackupImageView r73 = this.avatarSearchImageView;
        if (r14 == false) goto L47;
        float r92 = 1.0f;
    L48:
        r65.playTogether(new Animator[]{ObjectAnimator.ofFloat(r73, r8, new float[]{r92})});
        goto L49
    L47:
        r92 = 0.0f;
    L49:
        this.centerScale = true;
        requestLayout();
        this.searchVisibleAnimator.addListener(new AnonymousClass4(this, r02, r14, r1));
        this.searchVisibleAnimator.setDuration(150).start();
        ImageView r13 = this.backButtonImageView;
        if (r13 == null) goto L61;
        Drawable r132 = r13.getDrawable();
        if ((r132 instanceof MenuDrawable) == false) goto L62;
        MenuDrawable r133 = (MenuDrawable) r132;
        r133.setRotateToBack(true);
        if (r14 == false) goto L56;
        r42 = 1.0f;
    L56:
        r133.setRotation(r42, true);
        return;
    L62:
        return;
    L61:
        return;
    L22:
        r6 = 0.0f;
        goto L23
    }

    public void onSearchPressed() {
        this.menu.onSearchPressed();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (this.forceSkipTouches == false) goto L6;
        return false;
    L6:
        if (super.onTouchEvent(r3) == false) goto L8;
        return true;
    L8:
        if (this.interceptTouches == true) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View r1) {
        super.onViewAdded(r1);
    }

    public void openSearchField(String r3, boolean r4) {
        ActionBarMenu r0 = this.menu;
        if (r0 == null) goto L8;
        if (r3 == null) goto L9;
        boolean r2 = this.isSearchFieldVisible;
        r0.openSearchField(!r2, !r2, r3, r4);
        return;
    L9:
        return;
    }

    public void refreshTitlePosition(boolean r13) {
        int r0 = getTitleGravity();
        int r1 = getSubtitleGravity();
        int r3 = 0;
        if (r13 == true) goto L17;
    L4:
        if (r3 >= 2) goto L9;
        SimpleTextView r132 = this.titleTextView[r3];
        if (r132 == null) goto L8;
        r132.setGravity(r0);
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        SimpleTextView r133 = this.subtitleTextView;
        if (r133 == null) goto L12;
        r133.setGravity(r1);
    L12:
        SimpleTextView r134 = this.additionalSubtitleTextView;
        if (r134 == null) goto L15;
        r134.setGravity(r1);
    L15:
        requestLayout();
        return;
    L17:
        ArrayList r135 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        int r6 = 0;
    L18:
        if (r6 >= 2) goto L25;
        SimpleTextView r7 = this.titleTextView[r6];
        if (r7 == null) goto L24;
        if (r7.getVisibility() != 0) goto L24;
        r135.add(this.titleTextView[r6]);
    L24:
        r6 = r6 + 1;
        goto L18
    L25:
        SimpleTextView r62 = this.subtitleTextView;
        if (r62 != null) goto L28;
    L30:
        SimpleTextView r63 = this.additionalSubtitleTextView;
        if (r63 != null) goto L33;
    L35:
        int r64 = r135.size();
        int r72 = 0;
    L36:
        if (r72 >= r64) goto L43;
        Object r8 = r135.get(r72);
        r72 = r72 + 1;
        View r82 = (View) r8;
        r82.animate().cancel();
        if ((r82 instanceof SimpleTextView) == false) goto L41;
        float r10 = r9.getTextStartX() + r82.getTranslationX();
        float r9 = ((SimpleTextView) r82).getTextStartY();
        float r83 = r82.getTranslationY();
    L40:
        float r92 = r9 + r83;
        r4.add(Float.valueOf(r10));
        r5.add(Float.valueOf(r92));
        goto L36
    L41:
        r10 = r82.getTranslationX() + r82.getLeft();
        r9 = r82.getTop();
        r83 = r82.getTranslationY();
    L43:
        if (r3 >= 2) goto L48;
        SimpleTextView r65 = this.titleTextView[r3];
        if (r65 == null) goto L47;
        r65.setGravity(r0);
    L47:
        r3 = r3 + 1;
        goto L43
    L48:
        SimpleTextView r02 = this.subtitleTextView;
        if (r02 == null) goto L51;
        r02.setGravity(r1);
    L51:
        SimpleTextView r03 = this.additionalSubtitleTextView;
        if (r03 == null) goto L54;
        r03.setGravity(r1);
    L54:
        requestLayout();
        AnonymousClass10 r04 = new AnonymousClass10(this, r135, r4, r5);
        getViewTreeObserver().addOnPreDrawListener(r04);
        addOnAttachStateChangeListener(new AnonymousClass11(this, r04));
        return;
    L33:
        if (r63.getVisibility() != 0) goto L35;
        r135.add(this.additionalSubtitleTextView);
        goto L35
    L28:
        if (r62.getVisibility() != 0) goto L30;
        r135.add(this.subtitleTextView);
        goto L30
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayoutRequest == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setActionBarMenuOnItemClick(ActionBarMenuOnItemClick r1) {
        this.actionBarMenuOnItemClick = r1;
    }

    public void setActionModeColor(int r1) {
        ActionBarMenu r0 = this.actionMode;
        if (r0 == null) goto L6;
        r0.setBackgroundColor(r1);
        return;
    }

    public void setActionModeOverrideColor(int r1) {
        this.actionModeColor = r1;
    }

    public void setActionModeTopColor(int r1) {
        View r0 = this.actionModeTop;
        if (r0 == null) goto L6;
        r0.setBackgroundColor(r1);
        return;
    }

    public void setAdaptiveBackground(RecyclerView r4) {
        setAdaptiveBackground(r4, false, Theme.key_windowBackgroundGray, Theme.key_actionBarDefault);
    }

    public void setAdaptiveBackground(RecyclerView r3, boolean r4) {
        setAdaptiveBackground(r3, r4, Theme.key_windowBackgroundGray, Theme.key_actionBarDefault);
    }

    public void setAdaptiveBackground(RecyclerView r1, boolean r2, int r3, int r4) {
        this.adaptive_topColorKey = r3;
        this.adaptive_lowerColorKey = r4;
        ActionBar$$ExternalSyntheticLambda5 r32 = new ActionBar$$ExternalSyntheticLambda5(this, r1);
        r1.addOnScrollListener(new AnonymousClass15(this, r32));
        this.adaptiveBackgroundHideTitle = r2;
        if (this.adaptiveBackground == false) goto L6;
        r32.run();
        return;
    L6:
        this.adaptiveBackground = true;
        boolean r12 = r1.canScrollVertically(-1);
        this.onTop = !r12;
        if (r12 == true) goto L9;
        float r13 = 1.0f;
    L10:
        this.onTopAnimated = r13;
        adaptive_updateColor();
        return;
    L9:
        r13 = 0.0f;
        goto L10
    }

    public void setAdaptiveBackground(SectionsScrollView r3) {
        setAdaptiveBackground(r3, Theme.key_windowBackgroundGray, Theme.key_actionBarDefault);
    }

    public void setAdaptiveBackground(SectionsScrollView r1, int r2, int r3) {
        this.adaptive_topColorKey = r2;
        this.adaptive_lowerColorKey = r3;
        adaptive_updateColor();
        ActionBar$$ExternalSyntheticLambda2 r22 = new ActionBar$$ExternalSyntheticLambda2(this, r1);
        r1.onScroll(r22);
        if (this.adaptiveBackground == false) goto L6;
        r22.run();
        return;
    L6:
        this.adaptiveBackground = true;
        boolean r12 = r1.canScrollVertically(-1);
        this.onTop = !r12;
        if (r12 == true) goto L9;
        float r13 = 1.0f;
    L10:
        this.onTopAnimated = r13;
        adaptive_updateColor();
        return;
    L9:
        r13 = 0.0f;
        goto L10
    }

    public void setAddToContainer(boolean r1) {
        this.addToContainer = r1;
    }

    public void setAdditionalTextLeft(int r1) {
        this.additionalTextLeft = r1;
    }

    public void setAllowOverlayTitle(boolean r1) {
        this.allowOverlayTitle = r1;
    }

    public void setBackButtonContentDescription(CharSequence r1) {
        ImageView r0 = this.backButtonImageView;
        if (r0 == null) goto L6;
        r0.setContentDescription(r1);
        return;
    }

    public void setBackButtonDrawable(Drawable r4) {
        if (this.backButtonImageView != null) goto L5;
        createBackButtonImage();
    L5:
        ImageView r0 = this.backButtonImageView;
        if (r4 != null) goto L8;
        int r2 = 8;
    L9:
        r0.setVisibility(r2);
        ImageView r02 = this.backButtonImageView;
        this.backButtonDrawable = r4;
        r02.setImageDrawable(r4);
        if ((r4 instanceof BackDrawable) == false) goto L17;
        BackDrawable r42 = (BackDrawable) r4;
        if (isActionModeShowed() == false) goto L14;
        float r03 = 1.0f;
    L15:
        r42.setRotation(r03, false);
        r42.setRotatedColor(this.itemsActionModeColor);
        r42.setColor(this.itemsColor);
    L25:
        if (this.mAlwaysApplyColorFilterToBackButton == false) goto L27;
        this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(this.itemsColor, PorterDuff.Mode.SRC_IN));
    L27:
        checkBackButtonLayerType();
        return;
    L14:
        r03 = 0.0f;
        goto L15
    L17:
        if ((r4 instanceof MenuDrawable) == false) goto L20;
        MenuDrawable r43 = (MenuDrawable) r4;
        r43.setBackColor(this.actionBarColor);
        r43.setIconColor(this.itemsColor);
        goto L25
    L20:
        if ((r4 instanceof BitmapDrawable) == false) goto L22;
    L23:
        this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(this.itemsColor, PorterDuff.Mode.SRC_IN));
        goto L25
    L22:
        if ((r4 instanceof VectorDrawable) == false) goto L25;
    L8:
        r2 = 0;
        goto L9
    }

    public void setBackButtonImage(int r4) {
        if (this.backButtonImageView != null) goto L5;
        createBackButtonImage();
    L5:
        ImageView r0 = this.backButtonImageView;
        if (r4 != 0) goto L8;
        int r1 = 8;
    L9:
        r0.setVisibility(r1);
        this.backButtonImageView.setImageResource(r4);
        this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(this.itemsColor, PorterDuff.Mode.SRC_IN));
        checkBackButtonLayerType();
        return;
    L8:
        r1 = 0;
        goto L9
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        this.actionBarColor = r2;
        if (this.blurredBackground == true) goto L5;
        super.setBackgroundColor(r2);
    L5:
        ImageView r1 = this.backButtonImageView;
        if (r1 == null) goto L11;
        Drawable r12 = r1.getDrawable();
        if ((r12 instanceof MenuDrawable) == false) goto L12;
        ((MenuDrawable) r12).setBackColor(r2);
        return;
    L12:
        return;
    }

    public void setCastShadows(boolean r2) {
        if (this.castShadows != r2) goto L5;
    L7:
        this.castShadows = r2;
        return;
    L5:
        if ((getParent() instanceof View) == false) goto L7;
        ((View) getParent()).invalidate();
        invalidate();
        goto L7
    }

    public void setChatAvatarContainer(ChatAvatarContainer r1) {
        this.chatAvatarContainer = r1;
    }

    public void setClipContent(boolean r1) {
        this.clipContent = r1;
    }

    public void setDrawBackButton(boolean r1) {
        this.drawBackButton = r1;
        ImageView r0 = this.backButtonImageView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void setDrawBlurBackground(SizeNotifierFrameLayout r2) {
        this.blurredBackground = true;
        this.contentView = r2;
        r2.blurBehindViews.add(this);
        setBackground(null);
    }

    public void setDrawGlassMiddlePill(boolean r2) {
        if (this.drawGlassMiddlePill == r2) goto L6;
        this.drawGlassMiddlePill = r2;
        invalidate();
        return;
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        super.setEnabled(r2);
        ImageView r0 = this.backButtonImageView;
        if (r0 == null) goto L5;
        r0.setEnabled(r2);
    L5:
        ActionBarMenu r02 = this.menu;
        if (r02 == null) goto L8;
        r02.setEnabled(r2);
    L8:
        ActionBarMenu r1 = this.actionMode;
        if (r1 == null) goto L12;
        r1.setEnabled(r2);
        return;
    }

    public void setExtraHeight(int r2) {
        this.extraHeight = r2;
        ActionBarMenu r22 = this.actionMode;
        if (r22 == null) goto L6;
        FrameLayout.LayoutParams r23 = (FrameLayout.LayoutParams) r22.getLayoutParams();
        r23.bottomMargin = this.extraHeight;
        this.actionMode.setLayoutParams(r23);
        return;
    }

    public void setForceDisableCenterTitle(boolean r2) {
        if (this.forceDisableCenterTitle != r2) goto L5;
        return;
    L5:
        this.forceDisableCenterTitle = r2;
        resetCenterTitleLayoutAnimation();
        updateTitleGravity();
        requestLayout();
    }

    public void setForceSkipTouches(boolean r1) {
        this.forceSkipTouches = r1;
    }

    public void setForcedMenuMinWidth(int r2) {
        this.hasForcedMenuMinWidth = true;
        if (this.forcedMenuMinWidth == r2) goto L6;
        this.forcedMenuMinWidth = r2;
        invalidate();
        return;
    }

    public void setForcedMenuWidth(int r2) {
        this.hasForcedMenuWidth = true;
        if (this.forcedMenuWidth == r2) goto L6;
        this.forcedMenuWidth = r2;
        invalidate();
        return;
    }

    public void setGlassOnlyBack() {
        this.glassOnlyBack = true;
    }

    public void setGlassPadding(int r2) {
        if (this.glassPadding == r2) goto L10;
        this.glassPadding = r2;
        if (this.glassMode == false) goto L9;
        applyGlassPadding();
        requestLayout();
        invalidate();
        return;
    L9:
        return;
    }

    public void setGlassShadowAlpha(float r2) {
        BlurredBackgroundDrawable r0 = this.glassDrawable;
        if (r0 == null) goto L5;
        r0.setShadowAlpha(r2);
    L5:
        BlurredBackgroundDrawable r02 = this.glassDrawableBack;
        if (r02 == null) goto L8;
        r02.setShadowAlpha(r2);
    L8:
        BlurredBackgroundDrawable r03 = this.glassDrawableMenu;
        if (r03 == null) goto L11;
        r03.setShadowAlpha(r2);
    L11:
        invalidate();
    }

    public void setGlassTitleTextSize(int r2) {
        if (this.glassTitleTextSize == r2) goto L10;
        this.glassTitleTextSize = r2;
        if (this.glassMode == false) goto L9;
        requestLayout();
        return;
    L9:
        return;
    }

    public void setInterceptTouchEventListener(View.OnTouchListener r1) {
        this.interceptTouchEventListener = r1;
    }

    public void setInterceptTouches(boolean r1) {
        this.interceptTouches = r1;
    }

    public void setItemsBackgroundColor(int r1, boolean r2) {
        if (r2 == false) goto L12;
        this.itemsActionModeBackgroundColor = r1;
        if (this.actionModeVisible == false) goto L8;
        ImageView r22 = this.backButtonImageView;
        if (r22 == null) goto L8;
        r22.setBackgroundDrawable(Theme.createSelectorDrawable(r1));
    L8:
        ActionBarMenu r0 = this.actionMode;
        if (r0 == null) goto L19;
        r0.updateItemsBackgroundColor();
        return;
    L19:
        return;
    L12:
        this.itemsBackgroundColor = r1;
        ImageView r23 = this.backButtonImageView;
        if (r23 == null) goto L15;
        r23.setBackgroundDrawable(Theme.createSelectorDrawable(r1));
    L15:
        ActionBarMenu r02 = this.menu;
        if (r02 == null) goto L20;
        r02.updateItemsBackgroundColor();
        return;
    }

    public void setItemsColor(int r3, boolean r4) {
        if (r4 == false) goto L16;
        this.itemsActionModeColor = r3;
        ActionBarMenu r42 = this.actionMode;
        if (r42 == null) goto L6;
        r42.updateItemsColor();
    L6:
        ImageView r43 = this.backButtonImageView;
        if (r43 == null) goto L33;
        Drawable r44 = r43.getDrawable();
        if ((r44 instanceof BackDrawable) == false) goto L12;
        ((BackDrawable) r44).setRotatedColor(r3);
        goto L33
    L12:
        if ((r44 instanceof BitmapDrawable) == false) goto L14;
    L15:
        this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
        goto L33
    L14:
        if ((r44 instanceof VectorDrawable) == true) goto L15;
    L33:
        ImageView r32 = this.backButtonImageView;
        if (r32 != null) goto L36;
        return;
    L36:
        if (this.mAlwaysApplyColorFilterToBackButton == false) goto L40;
        r32.setColorFilter(new PorterDuffColorFilter(this.itemsColor, PorterDuff.Mode.SRC_IN));
        return;
    L40:
        return;
    L16:
        this.itemsColor = r3;
        ImageView r45 = this.backButtonImageView;
        if (r45 == null) goto L30;
        if (r3 == 0) goto L30;
        Drawable r46 = r45.getDrawable();
        if ((r46 instanceof BackDrawable) == false) goto L23;
        ((BackDrawable) r46).setColor(r3);
        goto L30
    L23:
        if ((r46 instanceof MenuDrawable) == false) goto L26;
        ((MenuDrawable) r46).setIconColor(r3);
        goto L30
    L26:
        if ((r46 instanceof BitmapDrawable) == false) goto L28;
    L29:
        this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
        goto L30
    L28:
        if ((r46 instanceof VectorDrawable) == true) goto L29;
    L30:
        ActionBarMenu r33 = this.menu;
        if (r33 == null) goto L33;
        r33.updateItemsColor();
        goto L33
    }

    public void setMenuOffsetSuppressed(boolean r1) {
        this.isMenuOffsetSuppressed = r1;
    }

    public void setOccupyStatusBar(boolean r2) {
        this.occupyStatusBar = r2;
        ActionBarMenu r1 = this.actionMode;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r2 == false) goto L7;
        int r22 = AndroidUtilities.statusBarHeight;
    L8:
        r1.setPadding(0, r22, 0, 0);
        return;
    L7:
        r22 = 0;
        goto L8
    }

    public void setOnActionModeFactorChangeListener(Runnable r1) {
        this.doOnActionModeFactorChanged = r1;
    }

    public void setOverlayTitleAnimation(boolean r1) {
        this.overlayTitleAnimation = r1;
    }

    public void setPopupBackgroundColor(int r2, boolean r3) {
        if (r3 == false) goto L7;
        ActionBarMenu r0 = this.actionMode;
        if (r0 == null) goto L7;
        r0.redrawPopup(r2);
        return;
    L7:
        if (r3 == true) goto L12;
        ActionBarMenu r1 = this.menu;
        if (r1 == null) goto L13;
        r1.redrawPopup(r2);
        return;
    L13:
        return;
    }

    public void setPopupItemsColor(int r2, boolean r3, boolean r4) {
        if (r4 == false) goto L7;
        ActionBarMenu r0 = this.actionMode;
        if (r0 == null) goto L7;
        r0.setPopupItemsColor(r2, r3);
        return;
    L7:
        if (r4 == true) goto L12;
        ActionBarMenu r1 = this.menu;
        if (r1 == null) goto L13;
        r1.setPopupItemsColor(r2, r3);
        return;
    L13:
        return;
    }

    public void setPopupItemsSelectorColor(int r2, boolean r3) {
        if (r3 == false) goto L7;
        ActionBarMenu r0 = this.actionMode;
        if (r0 == null) goto L7;
        r0.setPopupItemsSelectorColor(r2);
        return;
    L7:
        if (r3 == true) goto L12;
        ActionBarMenu r1 = this.menu;
        if (r1 == null) goto L13;
        r1.setPopupItemsSelectorColor(r2);
        return;
    L13:
        return;
    }

    public void setRightDrawableOnClick(View.OnClickListener r3) {
        this.rightDrawableOnClickListener = r3;
        SimpleTextView r0 = this.titleTextView[0];
        if (r0 == null) goto L5;
        r0.setRightDrawableOnClick(r3);
    L5:
        SimpleTextView r32 = this.titleTextView[1];
        if (r32 == null) goto L9;
        r32.setRightDrawableOnClick(this.rightDrawableOnClickListener);
        return;
    }

    public void setSearchAvatarImageView(BackupImageView r2) {
        BackupImageView r0 = this.avatarSearchImageView;
        if (r0 == r2) goto L12;
        if (r0 == null) goto L7;
        removeView(r0);
    L7:
        this.avatarSearchImageView = r2;
        if (r2 == null) goto L11;
        addView(r2);
        return;
    L11:
        return;
    }

    public void setSearchCursorColor(int r1) {
        ActionBarMenu r0 = this.menu;
        if (r0 == null) goto L6;
        r0.setSearchCursorColor(r1);
        return;
    }

    public void setSearchFactor(float r2) {
        if (this.searchFactor == r2) goto L6;
        this.searchFactor = r2;
        invalidate();
        return;
    }

    public void setSearchFieldText(String r1) {
        this.menu.setSearchFieldText(r1);
    }

    public void setSearchFilter(FiltersView.MediaFilterData r1) {
        ActionBarMenu r0 = this.menu;
        if (r0 == null) goto L6;
        r0.setFilter(r1);
        return;
    }

    public void setSearchTextColor(int r1, boolean r2) {
        ActionBarMenu r0 = this.menu;
        if (r0 == null) goto L6;
        r0.setSearchTextColor(r1, r2);
        return;
    }

    public void setShadowAlpha(int r2) {
        if (this.shadowAlpha != r2) goto L6;
        return;
    L6:
        if ((getParent() instanceof View) == false) goto L8;
        ((View) getParent()).invalidate();
        invalidate();
    L8:
        this.shadowAlpha = r2;
    }

    public void setSkipDrawChild(boolean r2) {
        if (this.doNotDrawChild == r2) goto L6;
        this.doNotDrawChild = r2;
        invalidate();
        return;
    }

    public void setSubtitle(CharSequence r4) {
        if (r4 == null) goto L7;
        if (this.subtitleTextView != null) goto L7;
        createSubtitleTextView();
    L7:
        if (this.subtitleTextView == null) goto L19;
        boolean r0 = TextUtils.isEmpty(r4);
        SimpleTextView r1 = this.subtitleTextView;
        if (r0 == false) goto L11;
    L13:
        int r2 = 8;
    L14:
        r1.setVisibility(r2);
        this.subtitleTextView.setAlpha(1.0f);
        if (r0 == true) goto L17;
        this.subtitleTextView.setText(r4);
    L17:
        this.subtitle = r4;
        return;
    L11:
        if (this.isSearchFieldVisible == true) goto L13;
        r2 = 0;
        goto L14
    }

    public void setSubtitleColor(int r2) {
        if (this.subtitleTextView != null) goto L5;
        createSubtitleTextView();
    L5:
        this.subtitleTextView.setTextColor(r2);
    }

    public void setSupportsHolidayImage(boolean r1) {
        this.supportsHolidayImage = r1;
        if (r1 == false) goto L5;
        this.fontMetricsInt = new Paint.FontMetricsInt();
        this.rect = new Rect();
    L5:
        invalidate();
    }

    public void setTitle(CharSequence r2) {
        setTitle(r2, null);
    }

    public void setTitle(CharSequence r5, Drawable r6) {
        if (r5 != null) goto L5;
    L7:
        SimpleTextView r1 = this.titleTextView[0];
        if (r1 == null) goto L34;
        if (r5 != null) goto L11;
    L13:
        Typeface r2 = AndroidUtilities.bold();
    L14:
        r1.setTypeface(r2);
        SimpleTextView r12 = this.titleTextView[0];
        if (r5 != null) goto L17;
    L19:
        int r22 = 4;
    L20:
        r12.setVisibility(r22);
        SimpleTextView r13 = this.titleTextView[0];
        this.lastTitle = r5;
        r13.setText(r5);
        Drawable r52 = this.titleTextView[0].getRightDrawable();
        View.OnClickListener r23 = null;
        if (this.attached == true) goto L23;
    L25:
        this.lastRightDrawable = r6;
        Drawable r53 = getVisibleTitleRightDrawable(r6);
        this.titleTextView[0].setRightDrawable(r53);
        if (this.attached == true) goto L28;
    L30:
        SimpleTextView r62 = this.titleTextView[0];
        if (r53 == null) goto L33;
        r23 = this.rightDrawableOnClickListener;
    L33:
        r62.setRightDrawableOnClick(r23);
        goto L34
    L28:
        if ((r53 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == false) goto L30;
        ((AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r53).setParentView(this.titleTextView[0]);
        goto L30
    L23:
        if ((r52 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == false) goto L25;
        ((AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r52).setParentView(null);
        goto L25
    L17:
        if (this.isSearchFieldVisible == true) goto L19;
        r22 = 0;
        goto L20
    L11:
        if (r5.toString().equalsIgnoreCase("экстераграм") == false) goto L13;
        r2 = AndroidUtilities.getTypeface("fonts/impact.ttf");
    L34:
        this.fromBottom = false;
        return;
    L5:
        if (this.titleTextView[0] != null) goto L7;
        createTitleTextView(0);
        goto L7
    }

    public void setTitleActionRunnable(Runnable r1) {
        this.titleActionRunnable = r1;
        this.lastRunnable = r1;
    }

    public void setTitleAnimated(CharSequence r7, boolean r8, long r9) {
        setTitleAnimated(r7, r8, r9, null);
    }

    public void setTitleAnimated(CharSequence r9, boolean r10, long r11, Interpolator r13) {
        if (this.titleTextView[0] == null) goto L48;
        if (r9 == null) goto L48;
        if (this.overlayTitleAnimation == true) goto L9;
    L11:
        boolean r0 = false;
    L13:
        if (r0 == true) goto L15;
    L22:
        SimpleTextView r5 = this.titleTextView[1];
        if (r5 != null) goto L25;
    L28:
        SimpleTextView[] r52 = this.titleTextView;
        r52[1] = r52[0];
        r52[0] = null;
        setTitle(r9);
        this.fromBottom = r10;
        this.titleTextView[0].setAlpha(0.0f);
        if (r0 == true) goto L35;
        SimpleTextView r53 = this.titleTextView[0];
        int r6 = AndroidUtilities.dp(20.0f);
        if (r10 == true) goto L34;
        r6 = -r6;
    L34:
        r53.setTranslationY(r6);
    L35:
        ViewPropertyAnimator r1 = this.titleTextView[0].animate().alpha(1.0f).translationY(0.0f).setDuration(r11);
        if (r13 == null) goto L38;
        r1.setInterpolator(r13);
    L38:
        r1.start();
        this.titleAnimationRunning = true;
        ViewPropertyAnimator r12 = this.titleTextView[1].animate().alpha(0.0f);
        if (r0 == true) goto L44;
        int r92 = AndroidUtilities.dp(20.0f);
        if (r10 == false) goto L43;
        r92 = -r92;
    L43:
        r12.translationY(r92);
    L44:
        if (r13 == null) goto L46;
        r12.setInterpolator(r13);
    L46:
        r12.setDuration(r11).setListener(new AnonymousClass7(this, r0, r10)).start();
        requestLayout();
        return;
    L25:
        if (r5.getParent() == null) goto L27;
        ((ViewGroup) this.titleTextView[1].getParent()).removeView(this.titleTextView[1]);
    L27:
        this.titleTextView[1] = null;
        goto L28
    L15:
        if (this.subtitleTextView.getVisibility() == 0) goto L17;
        this.subtitleTextView.setVisibility(0);
        this.subtitleTextView.setAlpha(0.0f);
    L17:
        ViewPropertyAnimator r54 = this.subtitleTextView.animate();
        if (r10 == false) goto L20;
        float r62 = 0.0f;
    L21:
        r54.alpha(r62).setDuration(220).start();
        goto L22
    L20:
        r62 = 1.0f;
        goto L21
    L9:
        if (TextUtils.isEmpty(this.subtitle) == true) goto L11;
        r0 = true;
    L48:
        setTitle(r9);
    }

    public void setTitleAnimatedX(CharSequence r7, Drawable r8, boolean r9, int r10) {
        SimpleTextView[] r0 = this.titleTextView;
        if (r0[0] == null) goto L21;
        if (r7 == null) goto L21;
        SimpleTextView r02 = r0[1];
        if (r02 != null) goto L9;
    L12:
        AnimatorSet r03 = this.titleAnimator;
        if (r03 == null) goto L15;
        r03.cancel();
        this.titleAnimator = null;
    L15:
        SimpleTextView[] r04 = this.titleTextView;
        r04[1] = r04[0];
        r04[0] = null;
        setTitle(r7, r8);
        this.titleAnimationRunning = true;
        int r72 = AndroidUtilities.dp(10.0f);
        if (r9 == false) goto L18;
        int r82 = -1;
    L19:
        float r73 = r72 * r82;
        this.titleTextView[1].setTranslationX(0.0f);
        this.titleTextView[1].setTranslationY(0.0f);
        this.titleTextView[0].setTranslationX(-r73);
        this.titleTextView[0].setTranslationY(0.0f);
        this.titleTextView[0].setAlpha(0.0f);
        this.titleTextView[1].setAlpha(1.0f);
        this.titleTextView[0].setVisibility(0);
        this.titleTextView[1].setVisibility(0);
        ArrayList r83 = new ArrayList();
        Property r5 = View.ALPHA;
        r83.add(ObjectAnimator.ofFloat(this.titleTextView[1], r5, new float[]{0.0f}));
        r83.add(ObjectAnimator.ofFloat(this.titleTextView[0], r5, new float[]{1.0f}));
        SimpleTextView r05 = this.titleTextView[1];
        float[] r3 = {r73};
        Property r74 = View.TRANSLATION_X;
        r83.add(ObjectAnimator.ofFloat(r05, r74, r3));
        r83.add(ObjectAnimator.ofFloat(this.titleTextView[0], r74, new float[]{0.0f}));
        AnimatorSet r75 = new AnimatorSet();
        this.titleAnimator = r75;
        r75.playTogether(r83);
        this.titleAnimator.addListener(new AnonymousClass8(this));
        this.titleAnimator.setDuration(r10);
        this.titleAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.titleAnimator.start();
        requestLayout();
        return;
    L18:
        r82 = 1;
        goto L19
    L9:
        if (r02.getParent() == null) goto L11;
        ((ViewGroup) this.titleTextView[1].getParent()).removeView(this.titleTextView[1]);
    L11:
        this.titleTextView[1] = null;
    L21:
        setTitle(r7, r8);
    }

    public void setTitleColor(int r3) {
        if (this.titleTextView[0] != null) goto L5;
        createTitleTextView(0);
    L5:
        this.titleColorToSet = r3;
        this.titleTextView[0].setTextColor(r3);
        this.titleTextView[0].setEmojiColor(r3);
        SimpleTextView r0 = this.titleTextView[1];
        if (r0 == null) goto L9;
        r0.setTextColor(r3);
        this.titleTextView[1].setEmojiColor(r3);
        return;
    }

    public void setTitleOverlayText(String r7, int r8, Runnable r9) {
        if (this.allowOverlayTitle == true) goto L5;
        return;
    L5:
        if (this.parentFragment.parentLayout == null) goto L87;
        Object[] r0 = this.overlayTitleToSet;
        r0[0] = r7;
        r0[1] = Integer.valueOf(r8);
        this.overlayTitleToSet[2] = r9;
        if (this.overlayTitleAnimationInProgress == true) goto L88;
        CharSequence r02 = this.lastOverlayTitle;
        if (r02 != null) goto L13;
        if (r7 != null) goto L13;
        return;
    L13:
        if (r02 != null) goto L15;
    L17:
        this.lastOverlayTitle = r7;
        Drawable r2 = null;
        if (this.additionalSubTitleOverlayContainer != null) goto L20;
    L24:
        if (r7 == null) goto L26;
        CharSequence r82 = LocaleController.getString(r7, r8);
    L27:
        if (r7 != null) goto L30;
        r2 = getVisibleTitleRightDrawable(this.lastRightDrawable);
    L30:
        if (r7 == null) goto L34;
        int r03 = TextUtils.indexOf(r82, "...");
        if (r03 < 0) goto L34;
        SpannableString r83 = SpannableString.valueOf(r82);
        this.ellipsizeSpanAnimator.wrap(r83, r03);
        boolean r04 = true;
        CharSequence r84 = r83;
    L35:
        if (r7 == null) goto L37;
        boolean r72 = true;
    L38:
        this.titleOverlayShown = r72;
        if (r84 == null) goto L43;
        if (this.titleTextView[0] != null) goto L43;
    L70:
        createTitleTextView(0);
        if (this.supportsHolidayImage == false) goto L73;
        this.titleTextView[0].invalidate();
        invalidate();
    L73:
        this.titleTextView[0].setText(r84);
        this.titleTextView[0].setDrawablePadding(AndroidUtilities.dp(4.0f));
        this.titleTextView[0].setRightDrawable(r2);
        this.titleTextView[0].setRightDrawableOnClick(this.rightDrawableOnClickListener);
        if ((r2 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == false) goto L76;
        ((AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r2).setParentView(this.titleTextView[0]);
    L76:
        EllipsizeSpanAnimator r73 = this.ellipsizeSpanAnimator;
        if (r04 == false) goto L79;
        r73.addView(this.titleTextView[0]);
    L80:
        if (r9 != null) goto L83;
        r9 = this.lastRunnable;
    L83:
        this.titleActionRunnable = r9;
        return;
    L79:
        r73.removeView(this.titleTextView[0]);
    L43:
        if (getMeasuredWidth() == 0) goto L70;
        SimpleTextView r4 = this.titleTextView[0];
        if (r4 != null) goto L47;
    L49:
        SimpleTextView r42 = this.titleTextView[0];
        if (r42 == null) goto L80;
        r42.animate().cancel();
        SimpleTextView r43 = this.titleTextView[1];
        if (r43 == null) goto L55;
        r43.animate().cancel();
    L55:
        if (this.titleTextView[1] != null) goto L57;
        createTitleTextView(1);
    L57:
        this.titleTextView[1].setText(r84);
        this.titleTextView[1].setDrawablePadding(AndroidUtilities.dp(4.0f));
        this.titleTextView[1].setRightDrawable(r2);
        this.titleTextView[1].setRightDrawableOnClick(this.rightDrawableOnClickListener);
        if ((r2 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == false) goto L60;
        ((AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) r2).setParentView(this.titleTextView[1]);
    L60:
        if (r04 == false) goto L62;
        this.ellipsizeSpanAnimator.addView(this.titleTextView[1]);
    L62:
        this.overlayTitleAnimationInProgress = true;
        SimpleTextView[] r74 = this.titleTextView;
        SimpleTextView r85 = r74[1];
        r74[1] = r74[0];
        r74[0] = r85;
        r85.setAlpha(0.0f);
        this.titleTextView[0].setTranslationY(-AndroidUtilities.dp(20.0f));
        ViewPropertyAnimator r86 = this.titleTextView[0].animate();
        float r22 = 1.0f;
        if (this.adaptiveBackgroundHideTitle == false) goto L65;
        r22 = 1.0f - this.onTopAnimated;
    L65:
        r86.alpha(r22).translationY(0.0f).setDuration(220).start();
        ViewPropertyAnimator r75 = this.titleTextView[1].animate().alpha(0.0f);
        if (this.subtitleTextView != null) goto L68;
        r75.translationY(AndroidUtilities.dp(20.0f));
    L69:
        requestLayout();
        this.centerScale = true;
        r75.setDuration(220).setListener(new AnonymousClass6(this)).start();
        goto L80
    L68:
        r75.scaleY(0.7f).scaleX(0.7f);
        goto L69
    L47:
        if (r4.getVisibility() == 0) goto L49;
    L37:
        r72 = false;
    L34:
        r04 = false;
        r84 = r82;
        goto L35
    L26:
        r82 = this.lastTitle;
        goto L27
    L20:
        if (r8 != R.string.ConnectingToProxyWithDots) goto L22;
        CharSequence r05 = AndroidUtilities.replaceArrows(LocaleController.getString(R.string.TitleSetupProxy), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(2.0f));
    L23:
        this.additionalSubTitleOverlayContainer.setText(r05, true);
        goto L24
    L22:
        r05 = null;
        goto L23
    L15:
        if (r02.equals(r7) == false) goto L17;
        return;
    L88:
        return;
    }

    public void setTitleRightMargin(int r1) {
        this.titleRightMargin = r1;
    }

    public void setTitleScrollNonFitText(boolean r2) {
        this.titleTextView[0].setScrollNonFitText(r2);
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        if (this.clipContent == false) goto L6;
        invalidate();
        return;
    }

    public void setUseContainerForTitles() {
        this.useContainerForTitles = true;
        if (this.titlesContainer != null) goto L6;
        AnonymousClass12 r0 = new AnonymousClass12(this, getContext());
        this.titlesContainer = r0;
        addView(r0);
        return;
    }

    public void setupGlass(BlurredBackgroundDrawableViewFactory r2, BlurredBackgroundColorProvider r3) {
        setupGlass(r2, r3, false, false);
    }

    public void setupGlass(BlurredBackgroundDrawableViewFactory r2, BlurredBackgroundColorProvider r3, boolean r4) {
        setupGlass(r2, r3, true, r4);
    }

    public boolean shouldAddToContainer() {
        return this.addToContainer;
    }

    public boolean shouldClipChild(View r5) {
        if (this.clipContent == false) goto L19;
        SimpleTextView[] r0 = this.titleTextView;
        if (r5 != r0[0]) goto L7;
    L18:
        return true;
    L7:
        if (r5 == r0[1]) goto L18;
        if (r5 == this.subtitleTextView) goto L18;
        if (r5 == this.menu) goto L18;
        if (r5 == this.backButtonImageView) goto L18;
        if (r5 == this.additionalSubtitleTextView) goto L18;
        if (r5 == this.titlesContainer) goto L18;
    L19:
        return false;
    }

    public void showActionMode() {
        showActionMode(true, null, null, null, null, null, 0);
    }

    public void showActionMode(boolean r9) {
        showActionMode(r9, null, null, null, null, null, 0);
    }

    public void showActionMode(boolean r20, View r21, View r22, View[] r23, boolean[] r24, View r25, int r26) {
        if (this.actionMode != null) goto L5;
        return;
    L5:
        if (this.actionModeVisible == true) goto L144;
        this.actionModeVisible = true;
        checkMenuItemsWidth();
        if (r20 == false) goto L69;
        ArrayList r13 = new ArrayList();
        Property r9 = View.ALPHA;
        r13.add(ObjectAnimator.ofFloat(this.actionMode, r9, new float[]{0.0f, 1.0f}));
        if (r23 == null) goto L18;
        int r8 = r23.length;
        int r14 = 0;
    L12:
        if (r14 >= r8) goto L18;
        View r11 = r23[r14];
        if (r11 == null) goto L16;
        r13.add(ObjectAnimator.ofFloat(r11, r9, new float[]{1.0f, 0.0f}));
    L16:
        r14 = r14 + 1;
    L18:
        if (r22 == null) goto L20;
        r13.add(ObjectAnimator.ofFloat(r22, r9, new float[]{0.0f, 1.0f}));
    L20:
        Property r82 = View.TRANSLATION_Y;
        if (r25 == null) goto L23;
        r13.add(ObjectAnimator.ofFloat(r25, r82, new float[]{r26}));
        this.actionModeTranslationView = r25;
    L23:
        this.actionModeExtraView = r21;
        this.actionModeShowingView = r22;
        this.actionModeHidingViews = r23;
        if (r21 == null) goto L27;
        r13.add(ObjectAnimator.ofFloat(r21, r82, new float[]{0.0f}));
    L27:
        if (this.actionModeColor == 0) goto L29;
    L41:
        int r1 = this.actionModeColor;
        if (r1 != 0) goto L44;
        r1 = this.actionBarColor;
    L44:
        if (r1 != 0) goto L46;
    L52:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
    L53:
        AnimatorSet r12 = this.actionModeAnimation;
        if (r12 == null) goto L56;
        r12.cancel();
    L56:
        AnimatorSet r15 = new AnimatorSet();
        this.actionModeAnimation = r15;
        r15.playTogether(r13);
        if (this.backgroundUpdateListener == null) goto L59;
        ValueAnimator r16 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r16.addUpdateListener(new ActionBar$$ExternalSyntheticLambda4(this));
        this.actionModeAnimation.playTogether(new Animator[]{r16});
    L59:
        this.actionModeAnimation.setDuration(200);
        this.actionModeAnimation.addListener(new AnonymousClass2(this, r24));
        this.actionModeAnimation.start();
        ImageView r17 = this.backButtonImageView;
        if (r17 == null) goto L142;
        Drawable r18 = r17.getDrawable();
        if ((r18 instanceof BackDrawable) == false) goto L65;
        ((BackDrawable) r18).setRotation(1.0f, true);
    L67:
        this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsActionModeBackgroundColor));
        return;
    L65:
        if ((r18 instanceof MenuDrawable) == false) goto L67;
        ((MenuDrawable) r18).setRotation(1.0f, true);
        goto L67
    L142:
        return;
    L46:
        if (this.glassMode == true) goto L52;
        if (ColorUtils.calculateLuminance(r1) >= 0.699999988079071d) goto L51;
        AndroidUtilities.setLightStatusBar((Activity) getContext(), false);
        goto L53
    L51:
        AndroidUtilities.setLightStatusBar((Activity) getContext(), true);
        goto L53
    L29:
        if (this.isSearchFieldVisible == true) goto L38;
        SimpleTextView r19 = this.titleTextView[0];
        if (r19 == null) goto L34;
        r13.add(ObjectAnimator.ofFloat(r19, r9, new float[]{0.0f}));
    L34:
        if (this.subtitleTextView == null) goto L38;
        if (TextUtils.isEmpty(this.subtitle) == true) goto L38;
        r13.add(ObjectAnimator.ofFloat(this.subtitleTextView, r9, new float[]{0.0f}));
    L38:
        ActionBarMenu r110 = this.menu;
        if (r110 == null) goto L41;
        r13.add(ObjectAnimator.ofFloat(r110, r9, new float[]{0.0f}));
        goto L41
    L69:
        float r182 = 0.0f;
        this.actionMode.setAlpha(1.0f);
        if (r23 == null) goto L77;
        int r83 = r23.length;
        int r92 = 0;
    L72:
        if (r92 >= r83) goto L77;
        View r10 = r23[r92];
        if (r10 == null) goto L76;
        r10.setAlpha(r182);
    L76:
        r92 = r92 + 1;
        r182 = 0.0f;
    L77:
        if (r22 == null) goto L79;
        r22.setAlpha(1.0f);
    L79:
        if (r25 == null) goto L81;
        r25.setTranslationY(r26);
        this.actionModeTranslationView = r25;
    L81:
        this.actionModeExtraView = r21;
        if (r21 == null) goto L84;
        r21.setTranslationY(0.0f);
    L84:
        this.actionModeShowingView = r22;
        this.actionModeHidingViews = r23;
        int r111 = this.actionModeColor;
        if (r111 != 0) goto L87;
        r111 = this.actionBarColor;
    L87:
        if (r111 != 0) goto L89;
    L95:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
    L96:
        this.actionMode.setVisibility(0);
        SimpleTextView r112 = this.titleTextView[0];
        if (r112 == null) goto L100;
        r112.setVisibility(4);
    L100:
        if (this.subtitleTextView != null) goto L102;
    L104:
        ActionBarMenu r113 = this.menu;
        if (r113 == null) goto L108;
        r113.setVisibility(4);
    L108:
        if (this.actionModeHidingViews == null) goto L121;
        int r114 = 0;
    L110:
        View[] r3 = this.actionModeHidingViews;
        if (r114 >= r3.length) goto L121;
        View r32 = r3[r114];
        if (r32 == null) goto L120;
        if (r24 != null) goto L116;
    L119:
        r32.setVisibility(4);
        goto L120
    L116:
        if (r114 >= r24.length) goto L119;
        if (r24[r114] == true) goto L119;
    L120:
        r114 = r114 + 1;
    L121:
        ImageView r115 = this.backButtonImageView;
        if (r115 == null) goto L143;
        Drawable r116 = r115.getDrawable();
        if ((r116 instanceof BackDrawable) == false) goto L127;
        ((BackDrawable) r116).setRotation(1.0f, false);
    L129:
        this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsActionModeBackgroundColor));
        return;
    L127:
        if ((r116 instanceof MenuDrawable) == false) goto L129;
        ((MenuDrawable) r116).setRotation(1.0f, false);
        goto L129
    L143:
        return;
    L102:
        if (TextUtils.isEmpty(this.subtitle) == true) goto L104;
        this.subtitleTextView.setVisibility(4);
        goto L104
    L89:
        if (this.glassMode == true) goto L95;
        if (ColorUtils.calculateLuminance(r111) >= 0.699999988079071d) goto L94;
        AndroidUtilities.setLightStatusBar((Activity) getContext(), false);
        goto L96
    L94:
        AndroidUtilities.setLightStatusBar((Activity) getContext(), true);
        goto L96
    }

    public void showActionModeTop() {
        if (this.occupyStatusBar == true) goto L5;
        return;
    L5:
        if (this.actionModeTop != null) goto L9;
        View r0 = new View(getContext());
        this.actionModeTop = r0;
        r0.setBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultTop));
        addView(this.actionModeTop);
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.actionModeTop.getLayoutParams();
        r02.height = AndroidUtilities.statusBarHeight;
        r02.width = -1;
        r02.gravity = 51;
        this.actionModeTop.setLayoutParams(r02);
        return;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        adaptive_updateColor();
        BlurredBackgroundDrawable r0 = this.glassDrawable;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
        BlurredBackgroundDrawable r02 = this.glassDrawableMenu;
        if (r02 == null) goto L8;
        r02.updateColors();
    L8:
        BlurredBackgroundDrawable r03 = this.glassDrawableBack;
        if (r03 == null) goto L11;
        r03.updateColors();
    L11:
        ActionBarAnimatedSubtitleOverlayContainer r1 = this.additionalSubTitleOverlayContainer;
        if (r1 == null) goto L15;
        r1.updateColors();
        return;
    }
}
