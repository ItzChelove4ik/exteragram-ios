package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.components.ActionRow;
import com.exteragram.messenger.utils.chats.GlassMenuHelper;
import java.util.ArrayList;
import java.util.HashMap;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CloseProgressDrawable2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;

/* loaded from: classes3.dex */
public class ActionBarMenuItem extends FrameLayout {
    private int additionalXOffset;
    private int additionalYOffset;
    private boolean allowCloseAnimation;
    private boolean animateClear;
    private boolean animationEnabled;
    private boolean centerTitleVisibleForPartialAlpha;
    private ImageView clearButton;
    private AnimatorSet clearButtonAnimator;
    private ArrayList<FiltersView.MediaFilterData> currentSearchFilters;
    private ActionBarMenuItemDelegate delegate;
    private float dimMenu;
    private boolean fixBackground;
    private boolean forceSmoothKeyboard;
    protected RLottieImageView iconView;
    private int iconViewResId;
    private boolean ignoreOnTextChange;
    private boolean isSearchField;
    private boolean layoutInScreen;
    private ArrayList<Item> lazyList;
    private HashMap<Integer, Item> lazyMap;
    protected ActionBarMenuItemSearchListener listener;
    private int[] location;
    private boolean longClickEnabled;
    private boolean measurePopup;
    private final AnimationNotificationsLocker notificationsLocker;
    private View.OnClickListener onClickListener;
    protected boolean overrideMenuClick;
    private ActionBarMenu parentMenu;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout popupLayout;
    private ActionBarPopupWindow popupWindow;
    private boolean processedPopupClick;
    private CloseProgressDrawable2 progressDrawable;
    private Rect rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private View searchAdditionalButton;
    private FrameLayout searchContainer;
    AnimatorSet searchContainerAnimator;
    private EditTextBoldCursor searchField;
    private TextView searchFieldCaption;
    private CharSequence searchFieldHint;
    private CharSequence searchFieldText;
    private LinearLayout searchFilterLayout;
    private ArrayList<SearchFilterView> searchFilterViews;
    public int searchItemPaddingStart;
    public int searchRightMargin;
    private int selectedFilterIndex;
    private View selectedMenuView;
    private Runnable showMenuRunnable;
    private View showSubMenuFrom;
    private boolean showSubmenuByMove;
    private ActionBarSubMenuItemDelegate subMenuDelegate;
    private BlurredBackgroundDrawableViewFactory subMenuFactory;
    private int subMenuOpenSide;
    protected TextView textView;
    private float transitionOffset;
    private boolean wrapSearchInScrollView;
    private FrameLayout wrappedSearchFrameLayout;
    private int xOffset;
    private int yOffset;

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends LinearLayout {
        final /* synthetic */ ActionBarMenuItem this$0;
        final /* synthetic */ View val$topView;

        public AnonymousClass1(ActionBarMenuItem r1, Context r2, View r3) {
            this.this$0 = r1;
            this.val$topView = r3;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(this.this$0).measure(r4, r5);
            PopupSwipeBackLayout r0 = ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(this.this$0).getSwipeBack();
            View r1 = this.val$topView;
            if (r0 == null) goto L5;
            ViewGroup.LayoutParams r02 = r1.getLayoutParams();
            r02.width = ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(this.this$0).getSwipeBack().getChildAt(0).getMeasuredWidth();
        L6:
            super.onMeasure(r4, r5);
            return;
        L5:
            ViewGroup.LayoutParams r03 = r1.getLayoutParams();
            r03.width = ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(this.this$0).getMeasuredWidth() - AndroidUtilities.dp(16.0f);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 implements TextWatcher {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass11(ActionBarMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            boolean r12 = ActionBarMenuItem.m5497$$Nest$fgetignoreOnTextChange(this.this$0);
            ActionBarMenuItem r22 = this.this$0;
            if (r12 == false) goto L6;
            ActionBarMenuItem.m5513$$Nest$fputignoreOnTextChange(r22, false);
            return;
        L6:
            ActionBarMenuItemSearchListener r13 = r22.listener;
            if (r13 == null) goto L9;
            r13.onTextChanged(ActionBarMenuItem.m5506$$Nest$fgetsearchField(r22));
        L9:
            ActionBarMenuItem.m5516$$Nest$mcheckClearButton(this.this$0);
            if (ActionBarMenuItem.m5495$$Nest$fgetcurrentSearchFilters(this.this$0).isEmpty() == false) goto L12;
            return;
        L12:
            if (TextUtils.isEmpty(ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getText()) == false) goto L14;
            return;
        L14:
            if (ActionBarMenuItem.m5509$$Nest$fgetselectedFilterIndex(this.this$0) < 0) goto L19;
            ActionBarMenuItem.m5515$$Nest$fputselectedFilterIndex(this.this$0, -1);
            ActionBarMenuItem.m5519$$Nest$monFiltersChanged(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 extends ImageView {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass12(ActionBarMenuItem r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            getBackground().draw(r2);
            super.draw(r2);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            clearAnimation();
            Object r0 = getTag();
            ActionBarMenuItem r1 = this.this$0;
            if (r0 != null) goto L6;
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(r1).setVisibility(4);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setAlpha(0.0f);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setRotation(45.0f);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setScaleX(0.0f);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setScaleY(0.0f);
            return;
        L6:
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(r1).setAlpha(1.0f);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setRotation(0.0f);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setScaleX(1.0f);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setScaleY(1.0f);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 extends CloseProgressDrawable2 {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass13(ActionBarMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.CloseProgressDrawable2
        public int getCurrentColor() {
            return ActionBarMenuItem.m5499$$Nest$fgetparentMenu(this.this$0).parentActionBar.itemsColor;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass14(ActionBarMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setVisibility(4);
            ActionBarMenuItem.m5512$$Nest$fputclearButtonAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass15(ActionBarMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ActionBarMenuItem.m5512$$Nest$fputclearButtonAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuItem this$0;
        final /* synthetic */ ArrayList val$menuIcons;

        public AnonymousClass2(ActionBarMenuItem r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$menuIcons = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ActionBarMenuItem.m5505$$Nest$fgetsearchContainer(this.this$0).setAlpha(0.0f);
            int r32 = 0;
        L4:
            if (r32 >= this.val$menuIcons.size()) goto L6;
            ((View) this.val$menuIcons.get(r32)).setAlpha(1.0f);
            r32 = r32 + 1;
            goto L4
        L6:
            ActionBarMenuItem.m5505$$Nest$fgetsearchContainer(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarMenuItem this$0;
        final /* synthetic */ ArrayList val$menuIcons;

        public AnonymousClass3(ActionBarMenuItem r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$menuIcons = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ActionBarMenuItem.m5505$$Nest$fgetsearchContainer(this.this$0).setAlpha(1.0f);
            int r32 = 0;
        L4:
            if (r32 >= this.val$menuIcons.size()) goto L6;
            ((View) this.val$menuIcons.get(r32)).setAlpha(0.0f);
            r32 = r32 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends Visibility {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass4(ActionBarMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Visibility
        public Animator onAppear(ViewGroup r3, View r4, TransitionValues r5, TransitionValues r6) {
            if ((r4 instanceof SearchFilterView) == false) goto L7;
            AnimatorSet r2 = new AnimatorSet();
            r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_X, new float[]{0.5f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_Y, new float[]{0.5f, 1.0f})});
            r2.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r2;
        L7:
            return ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f});
        }

        @Override // android.transition.Visibility
        public Animator onDisappear(ViewGroup r7, View r8, TransitionValues r9, TransitionValues r10) {
            if ((r8 instanceof SearchFilterView) == false) goto L7;
            AnimatorSet r6 = new AnimatorSet();
            r6.playTogether(new Animator[]{ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{r8.getAlpha(), 0.0f}), ObjectAnimator.ofFloat(r8, View.SCALE_X, new float[]{r8.getScaleX(), 0.5f}), ObjectAnimator.ofFloat(r8, View.SCALE_Y, new float[]{r8.getScaleX(), 0.5f})});
            r6.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r6;
        L7:
            return ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{1.0f, 0.0f});
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 implements Transition.TransitionListener {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass5(ActionBarMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
            ActionBarMenuItem.m5498$$Nest$fgetnotificationsLocker(this.this$0).unlock();
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            ActionBarMenuItem.m5498$$Nest$fgetnotificationsLocker(this.this$0).unlock();
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
            ActionBarMenuItem.m5498$$Nest$fgetnotificationsLocker(this.this$0).lock();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ ActionBarMenuItem this$0;
        final /* synthetic */ float val$oldX;

        public AnonymousClass6(ActionBarMenuItem r1, float r2) {
            this.this$0 = r1;
            this.val$oldX = r2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            if (ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getX() == this.val$oldX) goto L5;
            ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).setTranslationX(this.val$oldX - ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getX());
        L5:
            ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).animate().translationX(0.0f).setDuration(250).setStartDelay(0).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
            return true;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends FrameLayout {
        private boolean ignoreRequestLayout;
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass7(ActionBarMenuItem r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r22 = 0;
            if (LocaleController.isRTL == true) goto L9;
            if (ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(this.this$0).getVisibility() != 0) goto L9;
            int r12 = ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(this.this$0).getMeasuredWidth();
            r22 = AndroidUtilities.dp(4.0f) + r12;
        L9:
            if (ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(this.this$0).getVisibility() != 0) goto L11;
            r22 = r22 + ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(this.this$0).getMeasuredWidth();
        L11:
            ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).layout(r22, ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getTop(), ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getMeasuredWidth() + r22, ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).getBottom());
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r14, int r15) {
            if (ActionBarMenuItem.m5510$$Nest$fgetwrapSearchInScrollView(this.this$0) == true) goto L7;
            measureChildWithMargins(ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0), r14, 0, r15, 0);
            AnonymousClass7 r7 = this;
            int r9 = r14;
            int r11 = r15;
            if (ActionBarMenuItem.m5504$$Nest$fgetsearchAdditionalButton(r7.this$0) == null) goto L8;
            r7.measureChildWithMargins(ActionBarMenuItem.m5504$$Nest$fgetsearchAdditionalButton(r7.this$0), r9, 0, r11, 0);
        L8:
            boolean r13 = LocaleController.isRTL;
            ActionBarMenuItem r142 = r7.this$0;
            if (r13 == true) goto L25;
            if (ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(r142).getVisibility() != 0) goto L13;
            r7.measureChildWithMargins(ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(r7.this$0), r9, View.MeasureSpec.getSize(r9) / 2, r11, 0);
            int r10 = ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(r7.this$0).getMeasuredWidth() + AndroidUtilities.dp(4.0f);
        L14:
            int r132 = View.MeasureSpec.getSize(r9);
            r7.ignoreRequestLayout = true;
            r7.measureChildWithMargins(ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(r7.this$0), r9, r10, r11, 0);
            if (ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(r7.this$0).getVisibility() != 0) goto L17;
            int r143 = ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(r7.this$0).getMeasuredWidth();
        L18:
            EditTextBoldCursor r8 = ActionBarMenuItem.m5506$$Nest$fgetsearchField(r7.this$0);
            int r102 = r10 + r143;
            if (ActionBarMenuItem.m5504$$Nest$fgetsearchAdditionalButton(r7.this$0) == null) goto L21;
            int r152 = ActionBarMenuItem.m5504$$Nest$fgetsearchAdditionalButton(r7.this$0).getMeasuredWidth();
        L22:
            r7.measureChildWithMargins(r8, r9, r102 + r152, r11, 0);
            r7.ignoreRequestLayout = false;
            r7.setMeasuredDimension(Math.max(r143 + ActionBarMenuItem.m5506$$Nest$fgetsearchField(r7.this$0).getMeasuredWidth(), r132), View.MeasureSpec.getSize(r11));
            return;
        L21:
            r152 = 0;
            goto L22
        L17:
            r143 = 0;
            goto L18
        L13:
            r10 = 0;
            goto L14
        L25:
            if (ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(r142).getVisibility() != 0) goto L27;
            r7.measureChildWithMargins(ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(r7.this$0), r9, View.MeasureSpec.getSize(r9) / 2, r11, 0);
            int r103 = ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(r7.this$0).getMeasuredWidth() + AndroidUtilities.dp(4.0f);
        L28:
            int r133 = View.MeasureSpec.getSize(r9);
            r7.ignoreRequestLayout = true;
            r7.measureChildWithMargins(ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(r7.this$0), r9, r103, r11, 0);
            if (ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(r7.this$0).getVisibility() != 0) goto L31;
            int r144 = ActionBarMenuItem.m5508$$Nest$fgetsearchFilterLayout(r7.this$0).getMeasuredWidth();
        L32:
            r7.measureChildWithMargins(ActionBarMenuItem.m5506$$Nest$fgetsearchField(r7.this$0), View.MeasureSpec.makeMeasureSpec(r133 - AndroidUtilities.dp(12.0f), 0), r103 + r144, r11, 0);
            r7.ignoreRequestLayout = false;
            r7.setMeasuredDimension(Math.max(r144 + ActionBarMenuItem.m5506$$Nest$fgetsearchField(r7.this$0).getMeasuredWidth(), r133), View.MeasureSpec.getSize(r11));
            return;
        L31:
            r144 = 0;
            goto L32
        L27:
            r103 = 0;
            goto L28
        L7:
            r7 = this;
            r9 = r14;
            r11 = r15;
            goto L8
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreRequestLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            super.setAlpha(r2);
            if (ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0) != null) goto L5;
            return;
        L5:
            if (ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).getTag() == null) goto L9;
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setAlpha(r2);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setScaleX(r2);
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setScaleY(r2);
            return;
        }

        @Override // android.view.View
        public void setVisibility(int r2) {
            super.setVisibility(r2);
            if (ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0) == null) goto L6;
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(this.this$0).setVisibility(r2);
        L6:
            if (ActionBarMenuItem.m5504$$Nest$fgetsearchAdditionalButton(this.this$0) == null) goto L9;
            ActionBarMenuItem.m5504$$Nest$fgetsearchAdditionalButton(this.this$0).setVisibility(r2);
        L9:
            if (ActionBarMenuItem.m5511$$Nest$fgetwrappedSearchFrameLayout(this.this$0) == null) goto L12;
            ActionBarMenuItem.m5511$$Nest$fgetwrappedSearchFrameLayout(this.this$0).setVisibility(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends HorizontalScrollView {
        boolean isDragging;
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass8(ActionBarMenuItem r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private void checkDragg(MotionEvent r3) {
            if (r3.getAction() != 0) goto L7;
            this.isDragging = true;
            return;
        L7:
            if (r3.getAction() != 1) goto L9;
        L12:
            this.isDragging = false;
            return;
        L9:
            if (r3.getAction() == 3) goto L12;
        }

        @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            checkDragg(r1);
            return super.onInterceptTouchEvent(r1);
        }

        @Override // android.widget.HorizontalScrollView, android.view.View
        public void onOverScrolled(int r2, int r3, boolean r4, boolean r5) {
            if (this.isDragging == true) goto L5;
            return;
        L5:
            super.onOverScrolled(r2, r3, r4, r5);
        }

        @Override // android.widget.HorizontalScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            checkDragg(r1);
            return super.onTouchEvent(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends EditTextBoldCursor {
        final /* synthetic */ ActionBarMenuItem this$0;

        public AnonymousClass9(ActionBarMenuItem r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int r2, KeyEvent r3) {
            if (r2 != 67) goto L21;
            if (ActionBarMenuItem.m5506$$Nest$fgetsearchField(this.this$0).length() != 0) goto L21;
            if (ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(this.this$0).getVisibility() != 0) goto L11;
            if (ActionBarMenuItem.m5507$$Nest$fgetsearchFieldCaption(this.this$0).length() <= 0) goto L11;
        L12:
            boolean r22 = ActionBarMenuItem.m5518$$Nest$mhasRemovableFilters(this.this$0);
            ActionBarMenuItem r32 = this.this$0;
            if (r22 == false) goto L18;
            FiltersView.MediaFilterData r23 = (FiltersView.MediaFilterData) ActionBarMenuItem.m5495$$Nest$fgetcurrentSearchFilters(r32).get(ActionBarMenuItem.m5495$$Nest$fgetcurrentSearchFilters(this.this$0).size() - 1);
            ActionBarMenuItemSearchListener r33 = this.this$0.listener;
            if (r33 == null) goto L17;
            r33.onSearchFilterCleared(r23);
        L17:
            this.this$0.removeSearchFilter(r23);
        L19:
            return true;
        L18:
            ActionBarMenuItem.m5494$$Nest$fgetclearButton(r32).callOnClick();
        L11:
            if (ActionBarMenuItem.m5518$$Nest$mhasRemovableFilters(this.this$0) == true) goto L12;
        L21:
            return super.onKeyDown(r2, r3);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setMeasuredDimension(Math.max(View.MeasureSpec.getSize(r1), getMeasuredWidth()) + AndroidUtilities.dp(3.0f), getMeasuredHeight());
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onSelectionChanged(int r1, int r2) {
            super.onSelectionChanged(r1, r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            boolean r0 = super.onTouchEvent(r3);
            if (r3.getAction() == 1) goto L5;
        L7:
            return r0;
        L5:
            if (AndroidUtilities.showKeyboard(this) == true) goto L7;
            clearFocus();
            requestFocus();
            goto L7
        }
    }

    /* loaded from: classes6.dex */
    public interface ActionBarMenuItemDelegate {
        void onItemClick(int r1);
    }

    public static class ActionBarMenuItemSearchListener {
        public ActionBarMenuItemSearchListener() {
        }

        public boolean canClearCaption() {
            return true;
        }

        public boolean canCollapseSearch() {
            return true;
        }

        public boolean canToggleSearch() {
            return true;
        }

        public boolean forceShowClear() {
            return false;
        }

        public Animator getCustomToggleTransition() {
            return null;
        }

        public void onCaptionCleared() {
        }

        public void onLayout(int r1, int r2, int r3, int r4) {
        }

        public void onPreToggleSearch() {
        }

        public void onSearchCollapse() {
        }

        public void onSearchExpand() {
        }

        public void onSearchFilterCleared(FiltersView.MediaFilterData r1) {
        }

        public void onSearchPressed(EditText r1) {
        }

        public void onTextChanged(EditText r1) {
        }

        public boolean showClearForCaption() {
            return true;
        }
    }

    /* loaded from: classes6.dex */
    public interface ActionBarSubMenuItemDelegate {
        void onHideSubMenu();

        void onShowSubMenu();
    }

    public static class Item {
        public LinearLayout.LayoutParams customLayoutParams;
        public View customView;
        public boolean dismiss;
        public int icon;
        private Integer iconColor;
        public Drawable iconDrawable;
        public int id;
        public boolean needCheck;
        private View.OnClickListener overrideClickListener;
        private View.OnLongClickListener overrideLongClickListener;
        private int rightIconVisibility;
        public CharSequence text;
        private Integer textColor;
        public int textSizeDp;
        private View view;
        public View viewToSwipeBack;
        public int viewType;
        private int visibility;

        public static /* synthetic */ void $r8$lambda$EMcch9trWqU0Ih_MgId08MnuK7A(ActionBarMenuSubItem r0, View r1) {
            r0.openSwipeBack();
        }

        /* renamed from: $r8$lambda$UCfl1Ou2_sj1JpK5Oaz6AY-mdyM, reason: not valid java name */
        public static /* synthetic */ void m5520$r8$lambda$UCfl1Ou2_sj1JpK5Oaz6AYmdyM(Item r0, ActionBarMenuItem r1, View r2) {
            r0.lambda$add$0(r1, r2);
        }

        /* renamed from: $r8$lambda$XCtaINqCCgAfz-9UMfY_1BLwZN8, reason: not valid java name */
        public static /* synthetic */ void m5521$r8$lambda$XCtaINqCCgAfz9UMfY_1BLwZN8(ActionBarMenuItem r1, int r2) {
            if (ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r1).getSwipeBack() == null) goto L6;
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r1).getSwipeBack().openForeground(r2);
            return;
        }

        /* renamed from: -$$Nest$madd, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m5522$$Nest$madd(Item r0, ActionBarMenuItem r1) {
            return r0.add(r1);
        }

        /* renamed from: -$$Nest$smasColoredGap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Item m5523$$Nest$smasColoredGap() {
            return asColoredGap();
        }

        /* renamed from: -$$Nest$smasCustom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Item m5524$$Nest$smasCustom(View r0, LinearLayout.LayoutParams r1) {
            return asCustom(r0, r1);
        }

        /* renamed from: -$$Nest$smasSubItem, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Item m5525$$Nest$smasSubItem(int r0, int r1, Drawable r2, CharSequence r3, boolean r4, boolean r5) {
            return asSubItem(r0, r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$smasSwipeBackItem, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Item m5526$$Nest$smasSwipeBackItem(int r0, Drawable r1, String r2, View r3) {
            return asSwipeBackItem(r0, r1, r2, r3);
        }

        /* renamed from: -$$Nest$smasText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Item m5527$$Nest$smasText(CharSequence r0, int r1) {
            return asText(r0, r1);
        }

        private Item(int r2) {
            this.visibility = 0;
            this.rightIconVisibility = 0;
            this.viewType = r2;
        }

        private View add(ActionBarMenuItem r15) {
            ActionBarMenuItem.m5517$$Nest$mcreatePopupLayout(r15);
            if (this.view == null) goto L5;
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addView(this.view);
        L35:
            View r152 = this.view;
            if (r152 == null) goto L44;
            r152.setVisibility(this.visibility);
            View.OnClickListener r153 = this.overrideClickListener;
            if (r153 == null) goto L40;
            this.view.setOnClickListener(r153);
        L40:
            View.OnLongClickListener r154 = this.overrideLongClickListener;
            if (r154 == null) goto L44;
            this.view.setOnLongClickListener(r154);
        L44:
            return this.view;
        L5:
            int r2 = this.viewType;
            if (r2 != 0) goto L16;
            ActionBarMenuSubItem r7 = new ActionBarMenuSubItem(r15.getContext(), this.needCheck, false, false, ActionBarMenuItem.m5503$$Nest$fgetresourcesProvider(r15));
            r7.setTextAndIcon(this.text, this.icon, this.iconDrawable);
            r7.setMinimumWidth(AndroidUtilities.dp(196.0f));
            r7.setTag(Integer.valueOf(this.id));
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addView(r7);
            LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) r7.getLayoutParams();
            if (LocaleController.isRTL == false) goto L10;
            r0.gravity = 5;
        L10:
            r0.width = -1;
            r0.height = AndroidUtilities.dp(48.0f);
            r7.setLayoutParams(r0);
            r7.setOnClickListener(new ActionBarMenuItem$Item$$ExternalSyntheticLambda0(this, r15));
            Integer r155 = this.textColor;
            if (r155 != null) goto L13;
        L15:
            this.view = r7;
            goto L35
        L13:
            if (this.iconColor == null) goto L15;
            r7.setColors(r155.intValue(), this.iconColor.intValue());
            goto L15
        L16:
            if (r2 != 1) goto L19;
            ActionBarPopupWindow.GapView r02 = new ActionBarPopupWindow.GapView(r15.getContext(), ActionBarMenuItem.m5503$$Nest$fgetresourcesProvider(r15), Theme.key_actionBarDefaultSubmenuSeparator);
            r02.setTag(R.id.fit_width_tag, 1);
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addView(r02, LayoutHelper.createLinear(-1, 8));
            this.view = r02;
            goto L35
        L19:
            if (r2 != 3) goto L22;
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addView(this.customView, this.customLayoutParams);
            this.view = this.customView;
            goto L35
        L22:
            if (r2 != 2) goto L33;
            ActionBarMenuSubItem r8 = new ActionBarMenuSubItem(r15.getContext(), false, false, false, ActionBarMenuItem.m5503$$Nest$fgetresourcesProvider(r15));
            r8.setTextAndIcon(this.text, this.icon, this.iconDrawable);
            r8.setMinimumWidth(AndroidUtilities.dp(196.0f));
            r8.setRightIcon(R.drawable.msg_arrowright);
            r8.getRightIcon().setVisibility(this.rightIconVisibility);
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addView(r8);
            LinearLayout.LayoutParams r1 = (LinearLayout.LayoutParams) r8.getLayoutParams();
            if (LocaleController.isRTL == false) goto L26;
            r1.gravity = 5;
        L26:
            r1.width = -1;
            r1.height = AndroidUtilities.dp(48.0f);
            r8.setLayoutParams(r1);
            r8.openSwipeBackLayout = new ActionBarMenuItem$Item$$ExternalSyntheticLambda1(r15, ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addViewToSwipeBack(this.viewToSwipeBack));
            r8.setOnClickListener(new ActionBarMenuItem$Item$$ExternalSyntheticLambda2(r8));
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).swipeBackGravityRight = true;
            Integer r156 = this.textColor;
            if (r156 != null) goto L29;
        L31:
            this.view = r8;
            goto L35
        L29:
            if (this.iconColor == null) goto L31;
            r8.setColors(r156.intValue(), this.iconColor.intValue());
            goto L31
        L33:
            if (r2 != 4) goto L35;
            LinkSpanDrawable.LinksTextView r22 = new LinkSpanDrawable.LinksTextView(r15.getContext());
            r22.setTag(R.id.fit_width_tag, 1);
            r22.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
            r22.setTextSize(1, this.textSizeDp);
            r22.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
            r22.setMovementMethod(LinkMovementMethod.getInstance());
            r22.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
            r22.setText(this.text);
            r22.setMaxWidth(AndroidUtilities.dp(200.0f));
            ActionBarMenuItem.m5500$$Nest$fgetpopupLayout(r15).addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 8, 0, 0));
            this.view = r22;
            goto L35
        }

        private static Item asColoredGap() {
            return new Item(1);
        }

        private static Item asCustom(View r2, LinearLayout.LayoutParams r3) {
            Item r0 = new Item(3);
            r0.customView = r2;
            r0.customLayoutParams = r3;
            return r0;
        }

        private static Item asSubItem(int r2, int r3, Drawable r4, CharSequence r5, boolean r6, boolean r7) {
            Item r0 = new Item(0);
            r0.id = r2;
            r0.icon = r3;
            r0.iconDrawable = r4;
            r0.text = r5;
            r0.dismiss = r6;
            r0.needCheck = r7;
            return r0;
        }

        private static Item asSwipeBackItem(int r2, Drawable r3, String r4, View r5) {
            Item r0 = new Item(2);
            r0.icon = r2;
            r0.iconDrawable = r3;
            r0.text = r4;
            r0.viewToSwipeBack = r5;
            return r0;
        }

        private static Item asText(CharSequence r2, int r3) {
            Item r0 = new Item(4);
            r0.text = r2;
            r0.textSizeDp = r3;
            return r0;
        }

        private /* synthetic */ void lambda$add$0(ActionBarMenuItem r2, View r3) {
            if (ActionBarMenuItem.m5501$$Nest$fgetpopupWindow(r2) == null) goto L13;
            if (ActionBarMenuItem.m5501$$Nest$fgetpopupWindow(r2).isShowing() == false) goto L13;
            if (this.dismiss == false) goto L13;
            if (ActionBarMenuItem.m5502$$Nest$fgetprocessedPopupClick(r2) == true) goto L21;
            ActionBarMenuItem.m5514$$Nest$fputprocessedPopupClick(r2, true);
            ActionBarMenuItem.m5501$$Nest$fgetpopupWindow(r2).dismiss(ActionBarMenuItem.m5493$$Nest$fgetallowCloseAnimation(r2));
            goto L13
        L21:
            return;
        L13:
            if (ActionBarMenuItem.m5499$$Nest$fgetparentMenu(r2) == null) goto L17;
            ActionBarMenuItem.m5499$$Nest$fgetparentMenu(r2).onItemClick(((Integer) r3.getTag()).intValue());
            return;
        L17:
            if (ActionBarMenuItem.m5496$$Nest$fgetdelegate(r2) == null) goto L20;
            ActionBarMenuItem.m5496$$Nest$fgetdelegate(r2).onItemClick(((Integer) r3.getTag()).intValue());
            return;
        }

        public void openSwipeBack() {
            View r1 = this.view;
            if ((r1 instanceof ActionBarMenuSubItem) == false) goto L6;
            ((ActionBarMenuSubItem) r1).openSwipeBack();
            return;
        }

        public void setColors(int r3, int r4) {
            Integer r0 = this.textColor;
            if (r0 != null) goto L5;
        L10:
            this.textColor = Integer.valueOf(r3);
            this.iconColor = Integer.valueOf(r4);
            View r2 = this.view;
            if ((r2 instanceof ActionBarMenuSubItem) == false) goto L15;
            ((ActionBarMenuSubItem) r2).setColors(r3, r4);
            return;
        L15:
            return;
        L5:
            if (this.iconColor == null) goto L10;
            if (r0.intValue() != r3) goto L10;
            if (this.iconColor.intValue() != r4) goto L10;
        }

        public void setIcon(int r2) {
            if (r2 == this.icon) goto L8;
            this.icon = r2;
            View r1 = this.view;
            if ((r1 instanceof ActionBarMenuSubItem) == false) goto L9;
            ((ActionBarMenuSubItem) r1).setIcon(r2);
            return;
        L9:
            return;
        }

        public void setOnClickListener(View.OnClickListener r1) {
            this.overrideClickListener = r1;
            View r0 = this.view;
            if (r0 == null) goto L6;
            r0.setOnClickListener(r1);
            return;
        }

        public void setOnLongClickListener(View.OnLongClickListener r1) {
            this.overrideLongClickListener = r1;
            View r0 = this.view;
            if (r0 == null) goto L6;
            r0.setOnLongClickListener(r1);
            return;
        }

        public void setRightIconVisibility(int r2) {
            if (this.rightIconVisibility == r2) goto L8;
            this.rightIconVisibility = r2;
            View r22 = this.view;
            if ((r22 instanceof ActionBarMenuSubItem) == false) goto L9;
            ((ActionBarMenuSubItem) r22).getRightIcon().setVisibility(this.rightIconVisibility);
            return;
        L9:
            return;
        }

        public void setText(CharSequence r2) {
            this.text = r2;
            View r1 = this.view;
            if ((r1 instanceof ActionBarMenuSubItem) == false) goto L7;
            ((ActionBarMenuSubItem) r1).setText(r2);
            return;
        L7:
            if ((r1 instanceof TextView) == false) goto L10;
            ((TextView) r1).setText(r2);
            return;
        }

        public void setVisibility(int r1) {
            this.visibility = r1;
            View r0 = this.view;
            if (r0 == null) goto L6;
            r0.setVisibility(r1);
            return;
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes6.dex */
    public static class ReactionFilterView extends SearchFilterView {
        private boolean attached;
        private ReactionsLayoutInBubble.ReactionButton reactionButton;

        /* renamed from: org.telegram.ui.ActionBar.ActionBarMenuItem$ReactionFilterView$1, reason: invalid class name */
        public class AnonymousClass1 extends ReactionsLayoutInBubble.ReactionButton {
            final /* synthetic */ ReactionFilterView this$0;

            public AnonymousClass1(ReactionFilterView r1, ReactionsLayoutInBubble.ReactionButton r2, int r3, View r4, TLRPC.ReactionCount r5, boolean r6, boolean r7, Theme.ResourcesProvider r8) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public int getCacheType() {
                return 9;
            }

            @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
            public void updateColors(float r4) {
                this.lastDrawnBackgroundColor = ColorUtils.blendARGB(this.fromBackgroundColor, Theme.getColor(Theme.key_chat_inReactionButtonBackground, this.this$0.resourcesProvider), r4);
                this.lastDrawnTagDotColor = ColorUtils.blendARGB(this.fromTagDotColor, 1526726655, r4);
            }
        }

        public ReactionFilterView(Context r1, Theme.ResourcesProvider r2, boolean r3) {
            super(r1, r2, r3);
            removeAllViews();
            setBackground(null);
            setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.attached == true) goto L9;
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L7;
            r0.attach();
        L7:
            this.attached = true;
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.attached == false) goto L9;
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L7;
            r0.detach();
        L7:
            this.attached = false;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            ReactionsLayoutInBubble.ReactionButton r0 = this.reactionButton;
            if (r0 == null) goto L6;
            r0.draw(r10, ((getWidth() - AndroidUtilities.dp(4.0f)) - this.reactionButton.width) / 2.0f, (getHeight() - this.reactionButton.height) / 2.0f, 1.0f, 1.0f, false, false, 0.0f);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.SearchFilterView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(AndroidUtilities.dp(49.0f), AndroidUtilities.dp(32.0f));
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.SearchFilterView
        public void setData(FiltersView.MediaFilterData r11) {
            TLRPC.TL_reactionCount r5 = new TLRPC.TL_reactionCount();
            r5.count = 1;
            r5.reaction = r11.reaction.toTLReaction();
            AnonymousClass1 r0 = new AnonymousClass1(this, null, UserConfig.selectedAccount, this, r5, false, true, this.resourcesProvider);
            this.reactionButton = r0;
            r0.isTag = true;
            r0.width = AndroidUtilities.dp(44.33f);
            ReactionsLayoutInBubble.ReactionButton r10 = this.reactionButton;
            r10.height = AndroidUtilities.dp(28.0f);
            ReactionsLayoutInBubble.ReactionButton r102 = this.reactionButton;
            r102.choosen = true;
            if (this.attached == false) goto L6;
            r102.attach();
            return;
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes6.dex */
    public static class SearchFilterView extends FrameLayout implements FactorAnimator.Target {
        private final BoolAnimator animatorIsSelected;
        BackupImageView avatarImageView;
        ImageView closeIconView;
        FiltersView.MediaFilterData data;
        private boolean glass;
        private boolean isCommunity;
        private int mBackgroundColor;
        private int mBackgroundRadius;
        Runnable removeSelectionRunnable;
        protected final Theme.ResourcesProvider resourcesProvider;
        Drawable thumbDrawable;
        TextView titleView;
        private final boolean whiteBg;

        /* renamed from: $r8$lambda$OD30k7YnDP-7lhkRHuqqqWo8aiY, reason: not valid java name */
        public static /* synthetic */ void m5528$r8$lambda$OD30k7YnDP7lhkRHuqqqWo8aiY(SearchFilterView r0) {
            r0.lambda$new$0();
        }

        public SearchFilterView(Context r11, Theme.ResourcesProvider r12, boolean r13) {
            super(r11);
            this.animatorIsSelected = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
            this.removeSelectionRunnable = new ActionBarMenuItem$SearchFilterView$$ExternalSyntheticLambda0(this);
            this.resourcesProvider = r12;
            this.whiteBg = r13;
            BackupImageView r10 = new BackupImageView(r11);
            this.avatarImageView = r10;
            addView(r10, LayoutHelper.createFrame(32, 32.0f));
            ImageView r102 = new ImageView(r11);
            this.closeIconView = r102;
            r102.setImageResource(R.drawable.ic_close_white);
            addView(this.closeIconView, LayoutHelper.createFrame(24, 24.0f, 16, 8.0f, 0.0f, 0.0f, 0.0f));
            TextView r103 = new TextView(r11);
            this.titleView = r103;
            r103.setSingleLine();
            this.titleView.setEllipsize(TextUtils.TruncateAt.END);
            this.titleView.setTextSize(1, 14.0f);
            addView(this.titleView, LayoutHelper.createFrame(-2, -2.0f, 16, 38.0f, 0.0f, 12.0f, 0.0f));
            this.mBackgroundRadius = AndroidUtilities.dp(28.0f);
            updateColors();
        }

        private /* synthetic */ void lambda$new$0() {
            setSelectedForDelete(false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            float r4 = getWidth();
            float r5 = getHeight();
            int r0 = this.mBackgroundRadius;
            r10.drawRoundRect(0.0f, 0.0f, r4, r5, r0, r0, Theme.fillingPaint(this.mBackgroundColor));
            super.dispatchDraw(r10);
        }

        public FiltersView.MediaFilterData getFilter() {
            return this.data;
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public boolean isSelectedForDelete() {
            return this.animatorIsSelected.getValue();
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            if (r1 != 0) goto L5;
            updateColors();
            invalidate();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.isCommunity == false) goto L6;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(135.0f), Integer.MIN_VALUE), r3);
            return;
        L6:
            super.onMeasure(r2, r3);
        }

        public void setData(FiltersView.MediaFilterData r13) {
            this.data = r13;
            this.isCommunity = false;
            this.titleView.setText(r13.getTitle());
            CombinedDrawable r2 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(32.0f), r13.iconResFilled);
            this.thumbDrawable = r2;
            int r3 = Theme.key_featuredStickers_addButton;
            Theme.setCombinedDrawableColor(r2, getThemedColor(r3), false);
            Drawable r22 = this.thumbDrawable;
            int r4 = Theme.key_featuredStickers_buttonText;
            Theme.setCombinedDrawableColor(r22, getThemedColor(r4), true);
            int r23 = r13.filterType;
            if (r23 != 4) goto L21;
            TLObject r132 = r13.chat;
            if ((r132 instanceof TLRPC.User) == false) goto L13;
            TLRPC.User r133 = (TLRPC.User) r132;
            if (UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id != r133.id) goto L10;
            CombinedDrawable r134 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(32.0f), R.drawable.chats_saved);
            r134.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            Theme.setCombinedDrawableColor(r134, getThemedColor(r3), false);
            Theme.setCombinedDrawableColor(r134, getThemedColor(r4), true);
            this.avatarImageView.setImageDrawable(r134);
            return;
        L10:
            this.avatarImageView.getImageReceiver().setRoundRadius(ExteraConfig.getAvatarCorners(32.0f));
            this.avatarImageView.getImageReceiver().setForUserOrChat(r133, this.thumbDrawable);
            return;
        L13:
            if ((r132 instanceof TLRPC.Chat) == false) goto L26;
            TLRPC.Chat r135 = (TLRPC.Chat) r132;
            this.isCommunity = ChatObject.isCommunity(r135);
            ImageReceiver r24 = this.avatarImageView.getImageReceiver();
            if (this.isCommunity == false) goto L17;
            AvatarCornerType r32 = AvatarCornerType.COMMUNITY;
        L18:
            int r0 = ExteraConfig.getAvatarCorners(32.0f, false, r32);
            this.mBackgroundRadius = r0;
            r24.setRoundRadius(r0);
            this.avatarImageView.getImageReceiver().setForUserOrChat(r135, this.thumbDrawable);
            return;
        L17:
            r32 = AvatarCornerType.DEFAULT;
            goto L18
        L26:
            return;
        L21:
            if (r23 != 7) goto L24;
            CombinedDrawable r136 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(32.0f), R.drawable.chats_archive);
            r136.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            Theme.setCombinedDrawableColor(r136, getThemedColor(r3), false);
            Theme.setCombinedDrawableColor(r136, getThemedColor(r4), true);
            this.avatarImageView.setImageDrawable(r136);
            return;
        L24:
            this.avatarImageView.setImageDrawable(this.thumbDrawable);
        }

        public void setExpanded(boolean r3) {
            TextView r0 = this.titleView;
            if (r3 == false) goto L6;
            r0.setVisibility(0);
            return;
        L6:
            r0.setVisibility(8);
            setSelectedForDelete(false);
        }

        public void setGlass() {
            this.glass = true;
            updateColors();
        }

        public void setSelectedForDelete(boolean r3) {
            if (this.animatorIsSelected.getValue() == r3) goto L10;
            AndroidUtilities.cancelRunOnUIThread(this.removeSelectionRunnable);
            this.animatorIsSelected.setValue(r3, true);
            if (r3 == false) goto L9;
            AndroidUtilities.runOnUIThread(this.removeSelectionRunnable, 2000);
            return;
        L9:
            return;
        }

        public void updateColors() {
            float r0 = this.animatorIsSelected.getFloatValue();
            if (this.glass == false) goto L6;
            int r1 = Theme.multAlpha(getThemedColor(Theme.key_windowBackgroundWhiteBlackText), 0.075f);
        L10:
            int r2 = Theme.key_featuredStickers_addButton;
            int r3 = getThemedColor(r2);
            int r4 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
            int r5 = Theme.key_featuredStickers_buttonText;
            int r6 = getThemedColor(r5);
            this.mBackgroundColor = ColorUtils.blendARGB(r1, r3, r0);
            this.titleView.setTextColor(ColorUtils.blendARGB(r4, r6, r0));
            this.closeIconView.setColorFilter(r6);
            this.closeIconView.setAlpha(r0);
            float r32 = 0.82f * r0;
            this.closeIconView.setScaleX(r32);
            this.closeIconView.setScaleY(r32);
            Drawable r12 = this.thumbDrawable;
            if (r12 == null) goto L13;
            Theme.setCombinedDrawableColor(r12, getThemedColor(r2), false);
            Theme.setCombinedDrawableColor(this.thumbDrawable, getThemedColor(r5), true);
        L13:
            this.avatarImageView.setAlpha(1.0f - r0);
            FiltersView.MediaFilterData r02 = this.data;
            if (r02 != null) goto L16;
        L18:
            invalidate();
            return;
        L16:
            if (r02.filterType != 7) goto L18;
            setData(r02);
            goto L18
        L6:
            if (this.whiteBg == false) goto L8;
            int r13 = Theme.key_windowBackgroundWhite;
        L9:
            r1 = getThemedColor(r13);
            goto L10
        L8:
            r13 = Theme.key_groupcreate_spanBackground;
            goto L9
        }
    }

    /* renamed from: $r8$lambda$0CRrSTPpNmP-tEc6aMPWdUdxYCs, reason: not valid java name */
    public static /* synthetic */ void m5486$r8$lambda$0CRrSTPpNmPtEc6aMPWdUdxYCs(ActionBarMenuItem r0, int r1) {
        r0.lambda$addSwipeBackItem$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$1BkxEOe36yDJfE9T2OjuIxo9rbo(ActionBarMenuItem r0) {
        r0.lambda$toggleSearch$13();
    }

    /* renamed from: $r8$lambda$AF-m9AAfpSfDBYG8X6IeCxufkOs, reason: not valid java name */
    public static /* synthetic */ void m5487$r8$lambda$AFm9AAfpSfDBYG8X6IeCxufkOs(ActionBarMenuItem r0, boolean r1, View r2) {
        r0.lambda$addSubItem$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AHLMy2Ls29ESetmXEDhMdCPRyro(ActionBarMenuItem r0, KeyEvent r1) {
        r0.lambda$createPopupLayout$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$BpDgmQJODS9mYvTqYCFUzk_lFeM(ActionBarMenuItem r0, View r1) {
        r0.lambda$checkCreateSearchField$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$Es2sVPRRuxoN0TnD4M6o3kvHgFI(ActionBarMenuItem r0, View r1) {
        r0.lambda$addSubItem$5(r1);
    }

    /* renamed from: $r8$lambda$J2QW95eE-5XQbG9e1-iuzlQZxKE, reason: not valid java name */
    public static /* synthetic */ void m5488$r8$lambda$J2QW95eE5XQbG9e1iuzlQZxKE(ActionBarMenuItem r0) {
        r0.lambda$onTouchEvent$0();
    }

    /* renamed from: $r8$lambda$Kj8G-ZoN4-TcaNjCduNKuYOLQ5A, reason: not valid java name */
    public static /* synthetic */ void m5489$r8$lambda$Kj8GZoN4TcaNjCduNKuYOLQ5A(ActionBarMenuItem r0, SearchFilterView r1, View r2) {
        r0.lambda$onFiltersChanged$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MCvP1dapGoOjoEU0luVYBHLQbOI(ActionBarMenuSubItem r0, View r1) {
        r0.openSwipeBack();
    }

    /* renamed from: $r8$lambda$UANx7-6TzgUXs4Py0TueS6uHw40, reason: not valid java name */
    public static /* synthetic */ void m5490$r8$lambda$UANx76TzgUXs4Py0TueS6uHw40(ActionBarMenuItem r0, View r1) {
        r0.lambda$addSubItem$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$VhR1mwHNRCEWSFURtEbpmKx8CBw(ActionBarMenuItem r0, View r1) {
        r0.lambda$addSubItem$4(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$XAcQCFHs6rciCpbpPRNkICjfqHY(ActionBarMenuItem r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$checkCreateSearchField$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$XgSDqKzZKRMIsILsmQsi18V_Cao(ActionBarMenuItem r0, ValueAnimator r1) {
        r0.lambda$checkClearButton$17(r1);
    }

    /* renamed from: $r8$lambda$_qSSrOhOs91NYP_ouP-3SwUkPPg, reason: not valid java name */
    public static /* synthetic */ boolean m5491$r8$lambda$_qSSrOhOs91NYP_ouP3SwUkPPg(ActionBarMenuItem r0, View r1, MotionEvent r2) {
        return r0.lambda$createPopupLayout$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cBnZ4bmyKD3jK6XEVdXWFA3ks68(ActionBarMenuItem r0) {
        r0.lambda$toggleSubMenu$11();
    }

    public static /* synthetic */ void $r8$lambda$dR8C58EUbTIXFRRe6XaorFvkN2k(ActionBarMenuItem r0, ValueAnimator r1) {
        r0.lambda$checkClearButton$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$f6dz8kzrB5yS2MMSYM2DE_RsXzI(ActionBarMenuItem r0, Utilities.Callback r1) {
        r0.lambda$setOnMenuDismiss$12(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$wbiPIW5qQEv_lErRx1tS3MW8CoY(ActionBarMenuItem r0, View r1, int r2, KeyEvent r3) {
        return r0.lambda$toggleSubMenu$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$yDM-3GgM4yzCg-HSlJryJ8UO8x0, reason: not valid java name */
    public static /* synthetic */ void m5492$r8$lambda$yDM3GgM4yzCgHSlJryJ8UO8x0(ActionRow.ActionItem r0, View r1) {
        r0.action.onClick(r1);
    }

    /* renamed from: -$$Nest$fgetallowCloseAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5493$$Nest$fgetallowCloseAnimation(ActionBarMenuItem r0) {
        return r0.allowCloseAnimation;
    }

    /* renamed from: -$$Nest$fgetclearButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m5494$$Nest$fgetclearButton(ActionBarMenuItem r0) {
        return r0.clearButton;
    }

    /* renamed from: -$$Nest$fgetcurrentSearchFilters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5495$$Nest$fgetcurrentSearchFilters(ActionBarMenuItem r0) {
        return r0.currentSearchFilters;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItemDelegate m5496$$Nest$fgetdelegate(ActionBarMenuItem r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5497$$Nest$fgetignoreOnTextChange(ActionBarMenuItem r0) {
        return r0.ignoreOnTextChange;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m5498$$Nest$fgetnotificationsLocker(ActionBarMenuItem r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetparentMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenu m5499$$Nest$fgetparentMenu(ActionBarMenuItem r0) {
        return r0.parentMenu;
    }

    /* renamed from: -$$Nest$fgetpopupLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout m5500$$Nest$fgetpopupLayout(ActionBarMenuItem r0) {
        return r0.popupLayout;
    }

    /* renamed from: -$$Nest$fgetpopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m5501$$Nest$fgetpopupWindow(ActionBarMenuItem r0) {
        return r0.popupWindow;
    }

    /* renamed from: -$$Nest$fgetprocessedPopupClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5502$$Nest$fgetprocessedPopupClick(ActionBarMenuItem r0) {
        return r0.processedPopupClick;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m5503$$Nest$fgetresourcesProvider(ActionBarMenuItem r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsearchAdditionalButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5504$$Nest$fgetsearchAdditionalButton(ActionBarMenuItem r0) {
        return r0.searchAdditionalButton;
    }

    /* renamed from: -$$Nest$fgetsearchContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m5505$$Nest$fgetsearchContainer(ActionBarMenuItem r0) {
        return r0.searchContainer;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m5506$$Nest$fgetsearchField(ActionBarMenuItem r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetsearchFieldCaption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5507$$Nest$fgetsearchFieldCaption(ActionBarMenuItem r0) {
        return r0.searchFieldCaption;
    }

    /* renamed from: -$$Nest$fgetsearchFilterLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m5508$$Nest$fgetsearchFilterLayout(ActionBarMenuItem r0) {
        return r0.searchFilterLayout;
    }

    /* renamed from: -$$Nest$fgetselectedFilterIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5509$$Nest$fgetselectedFilterIndex(ActionBarMenuItem r0) {
        return r0.selectedFilterIndex;
    }

    /* renamed from: -$$Nest$fgetwrapSearchInScrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5510$$Nest$fgetwrapSearchInScrollView(ActionBarMenuItem r0) {
        return r0.wrapSearchInScrollView;
    }

    /* renamed from: -$$Nest$fgetwrappedSearchFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m5511$$Nest$fgetwrappedSearchFrameLayout(ActionBarMenuItem r0) {
        return r0.wrappedSearchFrameLayout;
    }

    /* renamed from: -$$Nest$fputclearButtonAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5512$$Nest$fputclearButtonAnimator(ActionBarMenuItem r0, AnimatorSet r1) {
        r0.clearButtonAnimator = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5513$$Nest$fputignoreOnTextChange(ActionBarMenuItem r0, boolean r1) {
        r0.ignoreOnTextChange = r1;
    }

    /* renamed from: -$$Nest$fputprocessedPopupClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5514$$Nest$fputprocessedPopupClick(ActionBarMenuItem r0, boolean r1) {
        r0.processedPopupClick = r1;
    }

    /* renamed from: -$$Nest$fputselectedFilterIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5515$$Nest$fputselectedFilterIndex(ActionBarMenuItem r0, int r1) {
        r0.selectedFilterIndex = r1;
    }

    /* renamed from: -$$Nest$mcheckClearButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5516$$Nest$mcheckClearButton(ActionBarMenuItem r0) {
        r0.checkClearButton();
    }

    /* renamed from: -$$Nest$mcreatePopupLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5517$$Nest$mcreatePopupLayout(ActionBarMenuItem r0) {
        r0.createPopupLayout();
    }

    /* renamed from: -$$Nest$mhasRemovableFilters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5518$$Nest$mhasRemovableFilters(ActionBarMenuItem r0) {
        return r0.hasRemovableFilters();
    }

    /* renamed from: -$$Nest$monFiltersChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5519$$Nest$monFiltersChanged(ActionBarMenuItem r0) {
        r0.onFiltersChanged();
    }

    public ActionBarMenuItem(Context r7, ActionBarMenu r8, int r9, int r10) {
        this(r7, r8, r9, r10, false);
    }

    public ActionBarMenuItem(Context r8, ActionBarMenu r9, int r10, int r11, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, r11, false, r12);
    }

    public ActionBarMenuItem(Context r8, ActionBarMenu r9, int r10, int r11, boolean r12) {
        this(r8, r9, r10, r11, r12, null);
    }

    public ActionBarMenuItem(Context r4, ActionBarMenu r5, int r6, int r7, boolean r8, Theme.ResourcesProvider r9) {
        super(r4);
        this.searchFilterViews = new ArrayList();
        this.allowCloseAnimation = true;
        this.animationEnabled = true;
        this.animateClear = true;
        this.measurePopup = true;
        this.showSubmenuByMove = true;
        this.currentSearchFilters = new ArrayList();
        this.selectedFilterIndex = -1;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.centerTitleVisibleForPartialAlpha = true;
        this.resourcesProvider = r9;
        if (r6 == 0) goto L8;
        if (r8 == false) goto L6;
        int r92 = 5;
    L7:
        setBackgroundDrawable(Theme.createSelectorDrawable(r6, r92));
        goto L8
    L6:
        r92 = 1;
    L8:
        this.parentMenu = r5;
        if (r8 == false) goto L15;
        TextView r82 = new TextView(r4);
        this.textView = r82;
        r82.setTextSize(1, 15.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setGravity(17);
        this.textView.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        this.textView.setImportantForAccessibility(2);
        if (r7 == 0) goto L13;
        this.textView.setTextColor(r7);
    L13:
        addView(this.textView, LayoutHelper.createFrame(-2, -1.0f));
        return;
    L15:
        RLottieImageView r83 = new RLottieImageView(r4);
        this.iconView = r83;
        r83.setScaleType(ImageView.ScaleType.CENTER);
        this.iconView.setImportantForAccessibility(2);
        addView(this.iconView, LayoutHelper.createFrame(-1, -1.0f));
        if (r7 == 0) goto L19;
        this.iconView.setColorFilter(new PorterDuffColorFilter(r7, PorterDuff.Mode.SRC_IN));
        return;
    }

    public static View addGap(int r2, ActionBarPopupWindow.ActionBarPopupWindowLayout r3) {
        View r0 = new View(r3.getContext());
        r0.setTag(Integer.valueOf(r2));
        r0.setTag(R.id.object_tag, 1);
        r0.setTag(R.id.fit_width_tag, 1);
        r3.addView(r0);
        LinearLayout.LayoutParams r22 = (LinearLayout.LayoutParams) r0.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r22.gravity = 5;
    L5:
        r22.width = -1;
        r22.height = AndroidUtilities.dp(6.0f);
        r0.setLayoutParams(r22);
        return r0;
    }

    public static ActionBarMenuSubItem addItem(ViewGroup r7, int r8, CharSequence r9, boolean r10, Theme.ResourcesProvider r11) {
        return addItem(false, false, r7, r8, r9, r10, r11);
    }

    public static ActionBarMenuSubItem addItem(boolean r6, boolean r7, ViewGroup r8, int r9, CharSequence r10, boolean r11, Theme.ResourcesProvider r12) {
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(r8.getContext(), r11, r6, r7, r12);
        r0.setTextAndIcon(r10, r9);
        r0.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r8.addView(r0);
        LinearLayout.LayoutParams r62 = (LinearLayout.LayoutParams) r0.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r62.gravity = 5;
    L5:
        r62.width = -1;
        r62.height = AndroidUtilities.dp(48.0f);
        r0.setLayoutParams(r62);
        return r0;
    }

    public static void addText(ActionBarPopupWindow.ActionBarPopupWindowLayout r5, String r6, Theme.ResourcesProvider r7) {
        TextView r0 = new TextView(r5.getContext());
        r0.setTextSize(1, 13.0f);
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r7));
        r0.setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        r0.setText(r6);
        r0.setTag(R.id.fit_width_tag, 1);
        r0.setMaxWidth(AndroidUtilities.dp(200.0f));
        r5.addView(r0, LayoutHelper.createLinear(-1, -2));
    }

    private void checkClearButton() {
        if (this.clearButton == null) goto L47;
        boolean r1 = hasRemovableFilters();
        Property r7 = View.ROTATION;
        Property r8 = View.SCALE_Y;
        Property r9 = View.SCALE_X;
        Property r10 = View.ALPHA;
        if (r1 == true) goto L34;
        if (TextUtils.isEmpty(this.searchField.getText()) == false) goto L34;
        ActionBarMenuItemSearchListener r12 = this.listener;
        if (r12 != null) goto L11;
    L14:
        TextView r13 = this.searchFieldCaption;
        if (r13 == null) goto L23;
        if (r13.getVisibility() != 0) goto L23;
        ActionBarMenuItemSearchListener r14 = this.listener;
        if (r14 == null) goto L34;
        if (r14.showClearForCaption() == true) goto L34;
    L23:
        if (this.clearButton.getTag() == null) goto L48;
        this.clearButton.setTag(null);
        AnimatorSet r15 = this.clearButtonAnimator;
        if (r15 == null) goto L28;
        r15.cancel();
    L28:
        if (this.animateClear == false) goto L31;
        AnimatorSet r16 = new AnimatorSet().setDuration(180);
        r16.setInterpolator(new DecelerateInterpolator());
        ValueAnimator r11 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r11.addUpdateListener(new ActionBarMenuItem$$ExternalSyntheticLambda15(this));
        r16.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.clearButton, r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.clearButton, r9, new float[]{0.0f}), ObjectAnimator.ofFloat(this.clearButton, r8, new float[]{0.0f}), ObjectAnimator.ofFloat(this.clearButton, r7, new float[]{45.0f}), r11});
        r16.addListener(new AnonymousClass14(this));
        r16.start();
        this.clearButtonAnimator = r16;
        return;
    L31:
        this.clearButton.setAlpha(0.0f);
        this.clearButton.setRotation(45.0f);
        this.clearButton.setScaleX(0.0f);
        this.clearButton.setScaleY(0.0f);
        this.clearButton.setVisibility(4);
        this.animateClear = true;
        return;
    L48:
        return;
    L11:
        if (r12.forceShowClear() == false) goto L14;
    L34:
        if (this.clearButton.getTag() != null) goto L49;
        this.clearButton.setTag(1);
        AnimatorSet r17 = this.clearButtonAnimator;
        if (r17 == null) goto L38;
        r17.cancel();
    L38:
        this.clearButton.setVisibility(0);
        if (this.animateClear == false) goto L42;
        AnimatorSet r18 = new AnimatorSet().setDuration(180);
        r18.setInterpolator(new DecelerateInterpolator());
        ValueAnimator r112 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r112.addUpdateListener(new ActionBarMenuItem$$ExternalSyntheticLambda16(this));
        r18.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.clearButton, r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.clearButton, r9, new float[]{1.0f}), ObjectAnimator.ofFloat(this.clearButton, r8, new float[]{1.0f}), ObjectAnimator.ofFloat(this.clearButton, r7, new float[]{0.0f}), r112});
        r18.addListener(new AnonymousClass15(this));
        r18.start();
        this.clearButtonAnimator = r18;
        return;
    L42:
        this.clearButton.setAlpha(1.0f);
        this.clearButton.setRotation(0.0f);
        this.clearButton.setScaleX(1.0f);
        this.clearButton.setScaleY(1.0f);
        View r19 = this.searchAdditionalButton;
        if (r19 == null) goto L45;
        r19.setTranslationX(0.0f);
    L45:
        this.animateClear = true;
        return;
    L49:
        return;
    }

    private void checkCreateSearchField() {
        if (this.searchContainer == null) goto L5;
        return;
    L5:
        if (this.isSearchField == false) goto L47;
        AnonymousClass7 r0 = new AnonymousClass7(this, getContext());
        this.searchContainer = r0;
        if (this.searchItemPaddingStart == 0) goto L9;
        boolean r1 = true;
    L10:
        r0.setClipChildren(r1);
        this.wrappedSearchFrameLayout = null;
        if (this.wrapSearchInScrollView == false) goto L17;
        this.wrappedSearchFrameLayout = new FrameLayout(getContext());
        AnonymousClass8 r02 = new AnonymousClass8(this, getContext());
        r02.addView(this.searchContainer, LayoutHelper.createScroll(-2, -1, 0));
        r02.setHorizontalScrollBarEnabled(false);
        if (this.searchItemPaddingStart == 0) goto L15;
        boolean r4 = true;
    L16:
        r02.setClipChildren(r4);
        this.wrappedSearchFrameLayout.addView(r02, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 48.0f, 0.0f));
        this.parentMenu.addView(this.wrappedSearchFrameLayout, 0, LayoutHelper.createLinear(0, -1, 1.0f, this.searchItemPaddingStart, 0, 0, 0));
    L18:
        this.searchContainer.setVisibility(8);
        TextView r03 = new TextView(getContext());
        this.searchFieldCaption = r03;
        r03.setTextSize(1, 18.0f);
        TextView r04 = this.searchFieldCaption;
        int r6 = Theme.key_actionBarDefaultSearch;
        r04.setTextColor(getThemedColor(r6));
        this.searchFieldCaption.setSingleLine(true);
        this.searchFieldCaption.setEllipsize(TextUtils.TruncateAt.END);
        this.searchFieldCaption.setVisibility(8);
        TextView r05 = this.searchFieldCaption;
        if (LocaleController.isRTL == false) goto L21;
        int r42 = 5;
    L22:
        r05.setGravity(r42);
        AnonymousClass9 r06 = new AnonymousClass9(this, getContext());
        this.searchField = r06;
        r06.setScrollContainer(false);
        this.searchField.setCursorWidth(1.5f);
        this.searchField.setCursorColor(getThemedColor(r6));
        this.searchField.setTextSize(1, 18.0f);
        this.searchField.setHintTextColor(getThemedColor(Theme.key_actionBarDefaultSearchPlaceholder));
        this.searchField.setTextColor(getThemedColor(r6));
        this.searchField.setSingleLine(true);
        this.searchField.setBackgroundResource(0);
        this.searchField.setPadding(0, 0, 0, 0);
        this.searchField.setInputType(this.searchField.getInputType() | 524288);
        this.searchField.setOnEditorActionListener(new ActionBarMenuItem$$ExternalSyntheticLambda12(this));
        this.searchField.addTextChangedListener(new AnonymousClass11(this));
        this.searchField.setImeOptions(234881027);
        this.searchField.setTextIsSelectable(false);
        this.searchField.setHighlightColor(getThemedColor(Theme.key_chat_inTextSelectionHighlight));
        this.searchField.setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor));
        CharSequence r07 = this.searchFieldHint;
        if (r07 == null) goto L25;
        this.searchField.setHint(r07);
        setContentDescription(this.searchFieldHint);
    L25:
        CharSequence r08 = this.searchFieldText;
        if (r08 == null) goto L28;
        this.searchField.setText(r08);
    L28:
        LinearLayout r09 = new LinearLayout(getContext());
        this.searchFilterLayout = r09;
        r09.setOrientation(0);
        this.searchFilterLayout.setVisibility(0);
        boolean r010 = LocaleController.isRTL;
        FrameLayout r43 = this.searchContainer;
        if (r010 == true) goto L35;
        r43.addView(this.searchFieldCaption, LayoutHelper.createFrame(-2, 36.0f, 19, 0.0f, 5.5f, 0.0f, 0.0f));
        FrameLayout r011 = this.searchContainer;
        EditTextBoldCursor r44 = this.searchField;
        if (this.wrapSearchInScrollView == false) goto L33;
        float r13 = 0.0f;
    L34:
        r011.addView(r44, LayoutHelper.createFrame(-1, 36.0f, 16, 6.0f, 0.0f, r13, 0.0f));
        this.searchContainer.addView(this.searchFilterLayout, LayoutHelper.createFrame(-2, 32.0f, 16, 0.0f, 0.0f, 48.0f, 0.0f));
    L40:
        this.searchFilterLayout.setClipChildren(false);
        AnonymousClass12 r012 = new AnonymousClass12(this, getContext());
        this.clearButton = r012;
        AnonymousClass13 r3 = new AnonymousClass13(this);
        this.progressDrawable = r3;
        r012.setImageDrawable(r3);
        this.clearButton.setBackground(Theme.createSelectorDrawable(this.parentMenu.parentActionBar.itemsActionModeBackgroundColor, 1));
        this.clearButton.setScaleType(ImageView.ScaleType.CENTER);
        this.clearButton.setAlpha(0.0f);
        this.clearButton.setRotation(45.0f);
        this.clearButton.setScaleX(0.0f);
        this.clearButton.setScaleY(0.0f);
        this.clearButton.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda13(this));
        this.clearButton.setContentDescription(LocaleController.getString(R.string.ClearButton));
        if (this.wrapSearchInScrollView == false) goto L44;
        this.wrappedSearchFrameLayout.addView(this.clearButton, LayoutHelper.createFrame(48, -1, 21));
        return;
    L44:
        this.searchContainer.addView(this.clearButton, LayoutHelper.createFrame(48, -1, 21));
        return;
    L33:
        r13 = 48.0f;
        goto L34
    L35:
        r43.addView(this.searchFilterLayout, LayoutHelper.createFrame(-2, 32.0f, 16, 0.0f, 0.0f, 48.0f, 0.0f));
        FrameLayout r013 = this.searchContainer;
        EditTextBoldCursor r45 = this.searchField;
        if (this.wrapSearchInScrollView == false) goto L38;
        float r132 = 0.0f;
    L39:
        r013.addView(r45, LayoutHelper.createFrame(-1, 36.0f, 16, 0.0f, 0.0f, r132, 0.0f));
        this.searchContainer.addView(this.searchFieldCaption, LayoutHelper.createFrame(-2, 36.0f, 21, 0.0f, 5.5f, 48.0f, 0.0f));
        goto L40
    L38:
        r132 = 48.0f;
        goto L39
    L21:
        r42 = 3;
        goto L22
    L15:
        r4 = false;
        goto L16
    L17:
        this.parentMenu.addView(this.searchContainer, 0, LayoutHelper.createLinear(0, -1, 1.0f, this.searchItemPaddingStart + 6, 0, this.searchRightMargin, 0));
        goto L18
    L9:
        r1 = false;
        goto L10
    }

    private void createPopupLayout() {
        if (this.popupLayout == null) goto L5;
        return;
    L5:
        this.rect = new Rect();
        this.location = new int[2];
        this.popupLayout = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext(), R.drawable.popup_fixed_alert4, this.resourcesProvider, 1);
        updateSubMenuGlass();
        this.popupLayout.setOnTouchListener(new ActionBarMenuItem$$ExternalSyntheticLambda10(this));
        this.popupLayout.setDispatchKeyEventListener(new ActionBarMenuItem$$ExternalSyntheticLambda11(this));
    }

    private Item findLazyItem(int r1) {
        HashMap<Integer, Item> r0 = this.lazyMap;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(Integer.valueOf(r1));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean hasRemovableFilters() {
        if (this.currentSearchFilters.isEmpty() == false) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.currentSearchFilters.size()) goto L13;
        if (this.currentSearchFilters.get(r0).removable == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$addSubItem$4(View r3) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
    L10:
        ActionBarMenu r02 = this.parentMenu;
        if (r02 == null) goto L14;
        r02.onItemClick(((Integer) r3.getTag()).intValue());
        return;
    L14:
        ActionBarMenuItemDelegate r2 = this.delegate;
        if (r2 == null) goto L18;
        r2.onItemClick(((Integer) r3.getTag()).intValue());
        return;
    L18:
        return;
    L5:
        if (r0.isShowing() == false) goto L10;
        if (this.processedPopupClick == true) goto L19;
        this.processedPopupClick = true;
        this.popupWindow.dismiss(this.allowCloseAnimation);
        goto L10
    }

    private /* synthetic */ void lambda$addSubItem$5(View r3) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
    L13:
        ActionBarMenu r02 = this.parentMenu;
        if (r02 == null) goto L17;
        r02.onItemClick(((Integer) r3.getTag()).intValue());
        return;
    L17:
        ActionBarMenuItemDelegate r2 = this.delegate;
        if (r2 == null) goto L21;
        r2.onItemClick(((Integer) r3.getTag()).intValue());
        return;
    L21:
        return;
    L5:
        if (r0.isShowing() == false) goto L13;
        if (this.processedPopupClick == true) goto L22;
        this.processedPopupClick = true;
        if (this.allowCloseAnimation == true) goto L12;
        this.popupWindow.setAnimationStyle(R.style.PopupAnimation);
    L12:
        this.popupWindow.dismiss(this.allowCloseAnimation);
        goto L13
    }

    private /* synthetic */ void lambda$addSubItem$6(boolean r2, View r3) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
    L11:
        ActionBarMenu r22 = this.parentMenu;
        if (r22 == null) goto L15;
        r22.onItemClick(((Integer) r3.getTag()).intValue());
        return;
    L15:
        ActionBarMenuItemDelegate r1 = this.delegate;
        if (r1 == null) goto L19;
        r1.onItemClick(((Integer) r3.getTag()).intValue());
        return;
    L19:
        return;
    L5:
        if (r0.isShowing() == false) goto L11;
        if (r2 == false) goto L11;
        if (this.processedPopupClick == true) goto L20;
        this.processedPopupClick = true;
        this.popupWindow.dismiss(this.allowCloseAnimation);
        goto L11
    }

    private /* synthetic */ void lambda$addSubItem$7(View r2) {
        ActionBarMenu r0 = this.parentMenu;
        if (r0 == null) goto L6;
        r0.onItemClick(((Integer) r2.getTag()).intValue());
        return;
    L6:
        ActionBarMenuItemDelegate r1 = this.delegate;
        if (r1 == null) goto L10;
        r1.onItemClick(((Integer) r2.getTag()).intValue());
        return;
    }

    private /* synthetic */ void lambda$addSwipeBackItem$8(int r2) {
        if (this.popupLayout.getSwipeBack() == null) goto L6;
        this.popupLayout.getSwipeBack().openForeground(r2);
        return;
    }

    private /* synthetic */ void lambda$checkClearButton$17(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        View r1 = this.searchAdditionalButton;
        if (r1 == null) goto L6;
        r1.setTranslationX(AndroidUtilities.dp(32.0f) * r22);
        return;
    }

    private /* synthetic */ void lambda$checkClearButton$18(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        View r1 = this.searchAdditionalButton;
        if (r1 == null) goto L6;
        r1.setTranslationX(AndroidUtilities.dp(32.0f) * r22);
        return;
    }

    private /* synthetic */ boolean lambda$checkCreateSearchField$15(TextView r1, int r2, KeyEvent r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.getAction() != 1) goto L8;
        if (r3.getKeyCode() != 84) goto L8;
    L11:
        AndroidUtilities.hideKeyboard(this.searchField);
        ActionBarMenuItemSearchListener r12 = this.listener;
        if (r12 == null) goto L19;
        r12.onSearchPressed(this.searchField);
        return false;
    L19:
        return false;
    L8:
        if (r3.getAction() == 0) goto L10;
        return false;
    L10:
        if (r3.getKeyCode() == 66) goto L11;
        return false;
    }

    private /* synthetic */ void lambda$checkCreateSearchField$16(View r3) {
        if (this.searchField.length() == 0) goto L6;
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L28:
        this.searchField.requestFocus();
        AndroidUtilities.showKeyboard(this.searchField);
        return;
    L6:
        if (hasRemovableFilters() == false) goto L17;
        this.searchField.hideActionMode();
        int r32 = 0;
    L9:
        if (r32 >= this.currentSearchFilters.size()) goto L16;
        if (this.listener == null) goto L15;
        if (this.currentSearchFilters.get(r32).removable == false) goto L15;
        this.listener.onSearchFilterCleared(this.currentSearchFilters.get(r32));
    L15:
        r32 = r32 + 1;
        goto L9
    L16:
        clearSearchFilters();
        goto L28
    L17:
        TextView r33 = this.searchFieldCaption;
        if (r33 == null) goto L28;
        if (r33.getVisibility() != 0) goto L28;
        ActionBarMenuItemSearchListener r34 = this.listener;
        if (r34 != null) goto L24;
    L25:
        this.searchFieldCaption.setVisibility(8);
        ActionBarMenuItemSearchListener r35 = this.listener;
        if (r35 == null) goto L28;
        r35.onCaptionCleared();
        goto L28
    L24:
        if (r34.canClearCaption() == false) goto L28;
        goto L25
    }

    private /* synthetic */ boolean lambda$createPopupLayout$2(View r2, MotionEvent r3) {
        if (r3.getActionMasked() != 0) goto L13;
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.isShowing() == false) goto L15;
        r2.getHitRect(this.rect);
        if (this.rect.contains((int) r3.getX(), (int) r3.getY()) == true) goto L16;
        this.popupWindow.dismiss();
        return false;
    L16:
        return false;
    L15:
        return false;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$createPopupLayout$3(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.popupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.popupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$onFiltersChanged$14(SearchFilterView r2, View r3) {
        int r32 = this.currentSearchFilters.indexOf(r2.getFilter());
        if (this.selectedFilterIndex == r32) goto L7;
        this.selectedFilterIndex = r32;
        onFiltersChanged();
        return;
    L7:
        if (r2.getFilter().removable == true) goto L9;
        return;
    L9:
        if (r2.isSelectedForDelete() == true) goto L12;
        r2.setSelectedForDelete(true);
        return;
    L12:
        FiltersView.MediaFilterData r22 = r2.getFilter();
        removeSearchFilter(r22);
        ActionBarMenuItemSearchListener r33 = this.listener;
        if (r33 == null) goto L17;
        r33.onSearchFilterCleared(r22);
        this.listener.onTextChanged(this.searchField);
        return;
    }

    private /* synthetic */ void lambda$onTouchEvent$0() {
        if (getParent() == null) goto L5;
        getParent().requestDisallowInterceptTouchEvent(true);
    L5:
        toggleSubMenu();
    }

    private /* synthetic */ void lambda$setOnMenuDismiss$12(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(Boolean.valueOf(this.processedPopupClick));
        return;
    }

    private /* synthetic */ void lambda$toggleSearch$13() {
        EditTextBoldCursor r0 = this.searchField;
        if (r0 == null) goto L6;
        r0.requestFocus();
        AndroidUtilities.showKeyboard(this.searchField);
        return;
    }

    private /* synthetic */ boolean lambda$toggleSubMenu$10(View r1, int r2, KeyEvent r3) {
        if (r2 == 82) goto L5;
        return false;
    L5:
        if (r3.getRepeatCount() == 0) goto L7;
        return false;
    L7:
        if (r3.getAction() != 1) goto L17;
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L11;
        return false;
    L11:
        if (r12.isShowing() == false) goto L19;
        this.popupWindow.dismiss();
        return true;
    L19:
        return false;
    L17:
        return false;
    }

    private /* synthetic */ void lambda$toggleSubMenu$11() {
        onDismiss();
        ActionBarSubMenuItemDelegate r0 = this.subMenuDelegate;
        if (r0 == null) goto L6;
        r0.onHideSubMenu();
        return;
    }

    private void layoutLazyItems() {
        if (this.lazyList != null) goto L5;
        return;
    L5:
        int r0 = 0;
    L6:
        int r1 = this.lazyList.size();
        ArrayList<Item> r2 = this.lazyList;
        if (r0 >= r1) goto L9;
        Item.m5522$$Nest$madd(r2.get(r0), this);
        r0 = r0 + 1;
        goto L6
    L9:
        r2.clear();
    }

    private void onFiltersChanged() {
        boolean r0 = this.currentSearchFilters.isEmpty();
        ArrayList r1 = new ArrayList(this.currentSearchFilters);
        FrameLayout r2 = this.searchContainer;
        if (r2 == null) goto L8;
        if (r2.getTag() == null) goto L8;
        TransitionSet r22 = new TransitionSet();
        ChangeBounds r4 = new ChangeBounds();
        r4.setDuration(150);
        r22.addTransition(new AnonymousClass4(this).setDuration(150)).addTransition(r4);
        r22.setOrdering(0);
        r22.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r22.addListener(new AnonymousClass5(this));
        TransitionManager.beginDelayedTransition(this.searchFilterLayout, r22);
    L8:
        if (this.searchFilterLayout == null) goto L16;
        int r23 = 0;
    L11:
        if (r23 >= this.searchFilterLayout.getChildCount()) goto L16;
        if (r1.remove(((SearchFilterView) this.searchFilterLayout.getChildAt(r23)).getFilter()) == true) goto L15;
        this.searchFilterLayout.removeViewAt(r23);
        r23 = r23 - 1;
    L15:
        r23 = r23 + 1;
    L16:
        int r24 = 0;
    L18:
        if (r24 >= r1.size()) goto L25;
        FiltersView.MediaFilterData r5 = (FiltersView.MediaFilterData) r1.get(r24);
        if (r5.reaction == null) goto L22;
        SearchFilterView r6 = new ReactionFilterView(getContext(), this.resourcesProvider, false);
    L23:
        r6.setData(r5);
        r6.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda0(this, r6));
        this.searchFilterLayout.addView(r6, LayoutHelper.createLinear(-2, -1, 0, 0, 0, 6, 0));
        r24 = r24 + 1;
        goto L18
    L22:
        r6 = new SearchFilterView(getContext(), this.resourcesProvider, false);
        goto L23
    L25:
        if (this.searchFilterLayout == null) goto L34;
        int r12 = 0;
    L28:
        if (r12 >= this.searchFilterLayout.getChildCount()) goto L34;
        SearchFilterView r25 = (SearchFilterView) this.searchFilterLayout.getChildAt(r12);
        if (r12 != this.selectedFilterIndex) goto L32;
        boolean r52 = true;
    L33:
        r25.setExpanded(r52);
        r12 = r12 + 1;
        goto L28
    L32:
        r52 = false;
    L34:
        LinearLayout r13 = this.searchFilterLayout;
        if (r13 == null) goto L40;
        if (r0 == true) goto L38;
        Integer r02 = 1;
    L39:
        r13.setTag(r02);
        goto L40
    L38:
        r02 = null;
    L40:
        float r03 = this.searchField.getX();
        if (this.searchContainer.getTag() == null) goto L43;
        this.searchField.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass6(this, r03));
    L43:
        checkClearButton();
    }

    private Item putLazyItem(Item r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (this.lazyList != null) goto L8;
        this.lazyList = new ArrayList();
    L8:
        this.lazyList.add(r2);
        if (this.lazyMap != null) goto L11;
        this.lazyMap = new HashMap();
    L11:
        this.lazyMap.put(Integer.valueOf(r2.id), r2);
        return r2;
    }

    private void updateOrShowPopup(boolean r9, boolean r10) {
        ActionBarMenu r0 = this.parentMenu;
        if (r0 == null) goto L6;
        int r02 = (-r0.parentActionBar.getMeasuredHeight()) + this.parentMenu.getTop();
        int r1 = this.parentMenu.getPaddingTop();
    L11:
        int r5 = (r02 + r1) + this.yOffset;
        if (r9 == false) goto L14;
        this.popupLayout.scrollToTop();
    L14:
        View r03 = this.showSubMenuFrom;
        if (r03 != null) goto L17;
        r03 = this;
    L17:
        ActionBarMenu r12 = this.parentMenu;
        if (r12 == null) goto L34;
        ActionBar r3 = r12.parentActionBar;
        if (this.subMenuOpenSide != 0) goto L26;
        if (r9 == false) goto L23;
        this.popupWindow.showAsDropDown(r3, ((((r03.getLeft() + this.parentMenu.getLeft()) + r03.getMeasuredWidth()) - this.popupWindow.getContentView().getMeasuredWidth()) + ((int) getTranslationX())) + this.xOffset, r5);
    L23:
        if (r10 == false) goto L57;
        this.popupWindow.update(r3, ((((r03.getLeft() + this.parentMenu.getLeft()) + r03.getMeasuredWidth()) - this.popupWindow.getContentView().getMeasuredWidth()) + ((int) getTranslationX())) + this.xOffset, r5, -1, -1);
        return;
    L57:
        return;
    L26:
        if (r9 == false) goto L31;
        boolean r92 = this.forceSmoothKeyboard;
        ActionBarPopupWindow r04 = this.popupWindow;
        if (r92 == false) goto L30;
        r04.showAtLocation(r3, 51, ((getLeft() - AndroidUtilities.dp(8.0f)) + ((int) getTranslationX())) + this.xOffset, r5);
        goto L31
    L30:
        r04.showAsDropDown(r3, ((getLeft() - AndroidUtilities.dp(8.0f)) + ((int) getTranslationX())) + this.xOffset, r5);
    L31:
        if (r10 == false) goto L58;
        this.popupWindow.update(r3, ((getLeft() - AndroidUtilities.dp(8.0f)) + ((int) getTranslationX())) + this.xOffset, r5, -1, -1);
        return;
    L58:
        return;
    L34:
        int r13 = this.subMenuOpenSide;
        if (r13 != 0) goto L45;
        if (getParent() == null) goto L59;
        View r32 = (View) getParent();
        if (r9 == false) goto L41;
        this.popupWindow.showAsDropDown(r32, (((getLeft() + getMeasuredWidth()) - this.popupWindow.getContentView().getMeasuredWidth()) + this.additionalXOffset) + this.xOffset, r5);
    L41:
        if (r10 == false) goto L60;
        this.popupWindow.update(r32, (((getLeft() + getMeasuredWidth()) - this.popupWindow.getContentView().getMeasuredWidth()) + this.additionalXOffset) + this.xOffset, r5, -1, -1);
        return;
    L60:
        return;
    L59:
        return;
    L45:
        if (r13 != 1) goto L52;
        if (r9 == false) goto L48;
        this.popupWindow.showAsDropDown(this, ((-AndroidUtilities.dp(8.0f)) + this.additionalXOffset) + this.xOffset, r5);
    L48:
        if (r10 == false) goto L61;
        this.popupWindow.update(this, ((-AndroidUtilities.dp(8.0f)) + this.additionalXOffset) + this.xOffset, r5, -1, -1);
        return;
    L61:
        return;
    L52:
        if (r9 == false) goto L54;
        this.popupWindow.showAsDropDown(this, ((getMeasuredWidth() - this.popupWindow.getContentView().getMeasuredWidth()) + this.additionalXOffset) + this.xOffset, r5);
    L54:
        if (r10 == false) goto L62;
        this.popupWindow.update(this, ((getMeasuredWidth() - this.popupWindow.getContentView().getMeasuredWidth()) + this.additionalXOffset) + this.xOffset, r5, -1, -1);
        return;
    L62:
        return;
    L6:
        float r05 = getScaleY();
        float r14 = getMeasuredHeight() * r05;
        if (this.subMenuOpenSide == 2) goto L9;
        float r2 = getTranslationY();
    L10:
        r02 = -((int) (r14 - (r2 / r05)));
        r1 = this.additionalYOffset;
        goto L11
    L9:
        r2 = 0.0f;
        goto L10
    }

    private void updateSubMenuGlass() {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.popupLayout;
        if (r0 == null) goto L9;
        BlurredBackgroundDrawableViewFactory r1 = this.subMenuFactory;
        if (r1 == null) goto L10;
        Theme.ResourcesProvider r3 = this.resourcesProvider;
        GlassMenuHelper.applyToReusedMenu(r1, r3, r0, GlassMenuHelper.isEnabled(r3));
        return;
    L10:
        return;
    }

    public ActionBarPopupWindow.GapView addColoredGap() {
        return addColoredGap(-1);
    }

    public ActionBarPopupWindow.GapView addColoredGap(int r5) {
        createPopupLayout();
        ActionBarPopupWindow.GapView r0 = new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider, Theme.key_actionBarDefaultSubmenuSeparator);
        if (r5 == (-1)) goto L5;
        r0.setTag(Integer.valueOf(r5));
    L5:
        r0.setTag(R.id.fit_width_tag, 1);
        this.popupLayout.addView(r0, LayoutHelper.createLinear(-1, 8));
        return r0;
    }

    public View addDivider(int r3) {
        createPopupLayout();
        TextView r0 = new TextView(getContext());
        r0.setBackgroundColor(r3);
        r0.setMinimumWidth(AndroidUtilities.dp(196.0f));
        this.popupLayout.addView(r0);
        LinearLayout.LayoutParams r2 = (LinearLayout.LayoutParams) r0.getLayoutParams();
        r2.width = -1;
        r2.height = 1;
        int r32 = AndroidUtilities.dp(3.0f);
        r2.bottomMargin = r32;
        r2.topMargin = r32;
        r0.setLayoutParams(r2);
        return r0;
    }

    public void addSearchFilter(FiltersView.MediaFilterData r2) {
        this.currentSearchFilters.add(r2);
        if (this.searchContainer.getTag() == null) goto L5;
        this.selectedFilterIndex = this.currentSearchFilters.size() - 1;
    L5:
        onFiltersChanged();
    }

    public View addSubItem(int r2, View r3) {
        createPopupLayout();
        r3.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r3.setTag(Integer.valueOf(r2));
        this.popupLayout.addView(r3);
        LinearLayout.LayoutParams r22 = (LinearLayout.LayoutParams) r3.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r22.gravity = 5;
    L5:
        r22.width = -1;
        r22.height = AndroidUtilities.dp(48.0f);
        r3.setLayoutParams(r22);
        r3.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda18(this));
        return r3;
    }

    public TextView addSubItem(int r6, CharSequence r7) {
        createPopupLayout();
        TextView r0 = new TextView(getContext());
        r0.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        r0.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (LocaleController.isRTL == true) goto L5;
        r0.setGravity(16);
    L6:
        r0.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r0.setTextSize(1, 16.0f);
        r0.setMinWidth(AndroidUtilities.dp(196.0f));
        r0.setSingleLine(true);
        r0.setEllipsize(TextUtils.TruncateAt.END);
        r0.setTag(Integer.valueOf(r6));
        r0.setText(r7);
        this.popupLayout.addView(r0);
        LinearLayout.LayoutParams r62 = (LinearLayout.LayoutParams) r0.getLayoutParams();
        if (LocaleController.isRTL == false) goto L9;
        r62.gravity = 5;
    L9:
        r62.width = -1;
        r62.height = AndroidUtilities.dp(48.0f);
        r0.setLayoutParams(r62);
        r0.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda7(this));
        return r0;
    L5:
        r0.setGravity(21);
        goto L6
    }

    public ActionBarMenuSubItem addSubItem(int r9, int r10, Drawable r11, CharSequence r12, boolean r13, boolean r14) {
        return addSubItem(r9, r10, r11, r12, r13, r14, this.resourcesProvider);
    }

    public ActionBarMenuSubItem addSubItem(int r7, int r8, Drawable r9, CharSequence r10, boolean r11, boolean r12, Theme.ResourcesProvider r13) {
        createPopupLayout();
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(getContext(), r12, false, false, r13);
        r0.setTextAndIcon(r10, r8, r9);
        r0.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r0.setTag(Integer.valueOf(r7));
        this.popupLayout.addView(r0);
        LinearLayout.LayoutParams r72 = (LinearLayout.LayoutParams) r0.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r72.gravity = 5;
    L5:
        r72.width = -1;
        r72.height = AndroidUtilities.dp(48.0f);
        r0.setLayoutParams(r72);
        r0.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda2(this, r11));
        return r0;
    }

    public ActionBarMenuSubItem addSubItem(int r8, int r9, CharSequence r10) {
        return addSubItem(r8, r9, null, r10, true, false);
    }

    public ActionBarMenuSubItem addSubItem(int r9, int r10, CharSequence r11, Theme.ResourcesProvider r12) {
        return addSubItem(r9, r10, null, r11, true, false, r12);
    }

    public ActionBarMenuSubItem addSubItem(int r8, int r9, CharSequence r10, boolean r11) {
        return addSubItem(r8, r9, null, r10, true, r11);
    }

    public void addSubItem(int r2, View r3, int r4, int r5) {
        createPopupLayout();
        r3.setLayoutParams(new LinearLayout.LayoutParams(r4, r5));
        this.popupLayout.addView(r3);
        r3.setTag(Integer.valueOf(r2));
        r3.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda17(this));
        r3.setBackgroundDrawable(Theme.getSelectorDrawable(false));
    }

    public void addSubItem(View r2, int r3, int r4) {
        createPopupLayout();
        this.popupLayout.addView(r2, new LinearLayout.LayoutParams(r3, r4));
    }

    public ActionBarMenuSubItem addSwipeBackItem(int r7, Drawable r8, String r9, View r10) {
        createPopupLayout();
        ActionBarMenuSubItem r0 = new ActionBarMenuSubItem(getContext(), false, false, false, this.resourcesProvider);
        r0.setTextAndIcon(r9, r7, r8);
        r0.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r0.setRightIcon(R.drawable.msg_arrowright);
        this.popupLayout.addView(r0);
        LinearLayout.LayoutParams r72 = (LinearLayout.LayoutParams) r0.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r72.gravity = 5;
    L5:
        r72.width = -1;
        r72.height = AndroidUtilities.dp(48.0f);
        r0.setLayoutParams(r72);
        r0.openSwipeBackLayout = new ActionBarMenuItem$$ExternalSyntheticLambda8(this, this.popupLayout.addViewToSwipeBack(r10));
        r0.setOnClickListener(new ActionBarMenuItem$$ExternalSyntheticLambda9(r0));
        this.popupLayout.swipeBackGravityRight = true;
        return r0;
    }

    public void checkHideMenuItem() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.popupLayout.getItemsCount()) goto L9;
        if (this.popupLayout.getItemAt(r1).getVisibility() == 0) goto L11;
        r1 = r1 + 1;
    L11:
        if (r0 == getVisibility()) goto L16;
        setVisibility(r0);
        return;
    L16:
        return;
    L9:
        r0 = 8;
        goto L11
    }

    public void clearFocusOnSearchView() {
        this.searchField.clearFocus();
        AndroidUtilities.hideKeyboard(this.searchField);
    }

    public void clearSearchFilters() {
        int r0 = 0;
    L4:
        if (r0 >= this.currentSearchFilters.size()) goto L9;
        if (this.currentSearchFilters.get(r0).removable == false) goto L8;
        this.currentSearchFilters.remove(r0);
        r0 = r0 - 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        onFiltersChanged();
    }

    public void clearSearchText() {
        this.searchFieldText = null;
        EditTextBoldCursor r1 = this.searchField;
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public void closeSubMenu() {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isShowing() == false) goto L9;
        this.popupWindow.dismiss();
        return;
    }

    @Override // android.view.View
    public void draw(Canvas r2) {
        if (this.fixBackground == false) goto L5;
        getBackground().draw(r2);
    L5:
        super.draw(r2);
    }

    public void forceUpdatePopupPosition() {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isShowing() == false) goto L10;
        this.popupLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x - AndroidUtilities.dp(40.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        updateOrShowPopup(true, true);
        return;
    }

    public View getContentView() {
        RLottieImageView r0 = this.iconView;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return this.textView;
    }

    public RLottieImageView getIconView() {
        return this.iconView;
    }

    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public ActionBarPopupWindow.ActionBarPopupWindowLayout getPopupLayout() {
        if (this.popupLayout != null) goto L6;
        createPopupLayout();
    L6:
        return this.popupLayout;
    }

    public ImageView getSearchClearButton() {
        return this.clearButton;
    }

    public FrameLayout getSearchContainer() {
        return this.searchContainer;
    }

    public EditTextBoldCursor getSearchField() {
        checkCreateSearchField();
        return this.searchField;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public int getVisibleSubItemsCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.popupLayout.getItemsCount()) goto L11;
        View r2 = this.popupLayout.getItemAt(r0);
        if (r2 == null) goto L10;
        if (r2.getVisibility() != 0) goto L10;
        r1 = r1 + 1;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return r1;
    }

    public boolean hasSubItem(int r3) {
        if (findLazyItem(r3) == null) goto L5;
        return true;
    L5:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r2 = this.popupLayout;
        if (r2 != null) goto L9;
        return false;
    L9:
        if (r2.findViewWithTag(Integer.valueOf(r3)) == null) goto L11;
        return true;
    L11:
        return false;
    }

    public boolean hasSubMenu() {
        if (this.popupLayout != null) goto L11;
        ArrayList<Item> r1 = this.lazyList;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isEmpty() == false) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    public void hideSubItem(int r3) {
        Item r0 = findLazyItem(r3);
        if (r0 == null) goto L5;
        r0.setVisibility(8);
    L5:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r02 = this.popupLayout;
        if (r02 == null) goto L16;
        View r32 = r02.findViewWithTag(Integer.valueOf(r3));
        if (r32 != null) goto L11;
        return;
    L11:
        if (r32.getVisibility() == 8) goto L15;
        r32.setVisibility(8);
        this.measurePopup = true;
        return;
    L15:
        return;
    }

    public boolean isSearchField() {
        return this.isSearchField;
    }

    public boolean isSearchFieldVisible() {
        FrameLayout r0 = this.searchContainer;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSearchFieldVisible2() {
        FrameLayout r0 = this.searchContainer;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getTag() == null) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSubItemVisible(int r2) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r1 = this.popupLayout;
        if (r1 != null) goto L5;
        return false;
    L5:
        View r12 = r1.findViewWithTag(Integer.valueOf(r2));
        if (r12 != null) goto L8;
    L11:
        return false;
    L8:
        if (r12.getVisibility() != 0) goto L11;
        return true;
    }

    public boolean isSubMenuShowing() {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isVisibleForCenterTitle() {
        if (getVisibility() == 0) goto L5;
        return false;
    L5:
        float r0 = getAlpha();
        if (r0 < 1.0f) goto L10;
        return true;
    L10:
        if (r0 > 0.0f) goto L13;
        return false;
    L13:
        return this.centerTitleVisibleForPartialAlpha;
    }

    public Item lazilyAddColoredGap() {
        return putLazyItem(Item.m5523$$Nest$smasColoredGap());
    }

    public Item lazilyAddSubItem(int r1, int r2, Drawable r3, CharSequence r4, boolean r5, boolean r6) {
        return putLazyItem(Item.m5525$$Nest$smasSubItem(r1, r2, r3, r4, r5, r6));
    }

    public Item lazilyAddSubItem(int r8, int r9, CharSequence r10) {
        return lazilyAddSubItem(r8, r9, null, r10, true, false);
    }

    public Item lazilyAddSwipeBackItem(int r1, Drawable r2, String r3, View r4) {
        return putLazyItem(Item.m5526$$Nest$smasSwipeBackItem(r1, r2, r3, r4));
    }

    public Item lazilyAddText(CharSequence r1, int r2) {
        return putLazyItem(Item.m5527$$Nest$smasText(r1, r2));
    }

    public Item lazilyAddView(View r1, LinearLayout.LayoutParams r2) {
        return putLazyItem(Item.m5524$$Nest$smasCustom(r1, r2));
    }

    public void onDismiss() {
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        if (this.iconView == null) goto L7;
        r2.setClassName("android.widget.ImageButton");
        return;
    L7:
        if (this.textView == null) goto L12;
        r2.setClassName("android.widget.Button");
        if (TextUtils.isEmpty(r2.getText()) == false) goto L13;
        r2.setText(this.textView.getText());
        return;
    L13:
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        ActionBarPopupWindow r22 = this.popupWindow;
        if (r22 != null) goto L5;
    L7:
        ActionBarMenuItemSearchListener r1 = this.listener;
        if (r1 == null) goto L11;
        r1.onLayout(r3, r4, r5, r6);
        return;
    L11:
        return;
    L5:
        if (r22.isShowing() == false) goto L7;
        updateOrShowPopup(false, true);
        goto L7
    }

    public void onSearchPressed() {
        ActionBarMenuItemSearchListener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onSearchPressed(this.searchField);
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        if (r14.getActionMasked() != 0) goto L14;
        if (this.longClickEnabled == false) goto L113;
        if (hasSubMenu() == false) goto L113;
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L11;
    L12:
        ActionBarMenuItem$$ExternalSyntheticLambda3 r02 = new ActionBarMenuItem$$ExternalSyntheticLambda3(this);
        this.showMenuRunnable = r02;
        AndroidUtilities.runOnUIThread(r02, 200);
        goto L113
    L11:
        if (r0.isShowing() == false) goto L12;
    L113:
        return super.onTouchEvent(r14);
    L14:
        if (r14.getActionMasked() == 2) goto L16;
        ActionBarPopupWindow r03 = this.popupWindow;
        if (r03 != null) goto L75;
    L109:
        View r04 = this.selectedMenuView;
        if (r04 == null) goto L113;
        r04.setSelected(false);
        this.selectedMenuView = null;
        goto L113
    L75:
        if (r03.isShowing() == false) goto L109;
        if (r14.getActionMasked() != 1) goto L109;
        View r05 = this.selectedMenuView;
        if (r05 == null) goto L107;
        r05.setSelected(false);
        View r06 = this.selectedMenuView;
        if ((r06 instanceof ActionBarMenuSubItem) == true) goto L83;
    L85:
        Object r07 = r06.getTag();
        if ((r07 instanceof ActionRow.ActionItem) == false) goto L99;
        ActionRow.ActionItem r08 = (ActionRow.ActionItem) r07;
        if (this.processedPopupClick == true) goto L90;
        this.processedPopupClick = true;
        if (r08.enabled == true) goto L94;
    L96:
        ActionBarPopupWindow r09 = this.popupWindow;
        if (r09 == null) goto L113;
        r09.dismiss(this.allowCloseAnimation);
        goto L113
    L94:
        if (r08.action == null) goto L96;
        AndroidUtilities.runOnUIThread(new ActionBarMenuItem$$ExternalSyntheticLambda4(r08, this.selectedMenuView));
        goto L96
    L90:
        return super.onTouchEvent(r14);
    L99:
        ActionBarMenu r010 = this.parentMenu;
        if (r010 == null) goto L102;
        r010.onItemClick(((Integer) this.selectedMenuView.getTag()).intValue());
    L105:
        this.popupWindow.dismiss(this.allowCloseAnimation);
        goto L113
    L102:
        ActionBarMenuItemDelegate r011 = this.delegate;
        if (r011 == null) goto L105;
        r011.onItemClick(((Integer) this.selectedMenuView.getTag()).intValue());
        goto L105
    L83:
        if (((ActionBarMenuSubItem) r06).openSwipeBackLayout == null) goto L85;
        r06.performClick();
        goto L113
    L107:
        if (this.showSubmenuByMove == false) goto L113;
        this.popupWindow.dismiss();
        goto L113
    L16:
        if (this.showSubmenuByMove == false) goto L31;
        if (hasSubMenu() == false) goto L31;
        ActionBarPopupWindow r012 = this.popupWindow;
        if (r012 == null) goto L24;
        if (r012.isShowing() == true) goto L31;
    L24:
        if (r14.getY() <= getHeight()) goto L113;
        if (getParent() == null) goto L28;
        getParent().requestDisallowInterceptTouchEvent(true);
    L28:
        toggleSubMenu();
        return true;
    L31:
        if (this.showSubmenuByMove == false) goto L113;
        ActionBarPopupWindow r013 = this.popupWindow;
        if (r013 == null) goto L113;
        if (r013.isShowing() == false) goto L113;
        getLocationOnScreen(this.location);
        float r014 = r14.getX() + this.location[0];
        float r1 = r14.getY();
        float r12 = r1 + r5[1];
        this.popupLayout.getLocationOnScreen(this.location);
        int[] r5 = this.location;
        float r015 = r014 - r5[0];
        float r13 = r12 - r5[1];
        this.selectedMenuView = null;
        int r2 = 0;
    L38:
        if (r2 >= this.popupLayout.getItemsCount()) goto L113;
        View r52 = this.popupLayout.getItemAt(r2);
        r52.getHitRect(this.rect);
        Object r6 = r52.getTag();
        if ((r6 instanceof Integer) == false) goto L44;
        if (((Integer) r6).intValue() >= 100) goto L44;
    L48:
        if (this.rect.contains((int) r015, (int) r13) == true) goto L50;
        r52.setPressed(false);
        r52.setSelected(false);
    L71:
        r2 = r2 + 1;
        goto L38
    L50:
        r52.setPressed(true);
        r52.setSelected(true);
        r52.drawableHotspotChanged(r015, r13 - r52.getTop());
        this.selectedMenuView = r52;
    L44:
        if ((r52 instanceof ActionBarMenuSubItem) == false) goto L52;
        if (((ActionBarMenuSubItem) r52).openSwipeBackLayout != null) goto L48;
    L52:
        if ((r52 instanceof ActionRow) == false) goto L71;
        ActionRow r62 = (ActionRow) r52;
        if (this.rect.contains((int) r015, (int) r13) == false) goto L65;
        float r7 = r015 - r52.getLeft();
        float r53 = r13 - r52.getTop();
        ViewGroup r63 = (ViewGroup) r62.getChildAt(0);
        if (r63 == null) goto L71;
        float r72 = r7 - r63.getLeft();
        float r54 = r53 - r63.getTop();
        int r8 = 0;
    L59:
        if (r8 >= r63.getChildCount()) goto L71;
        View r9 = r63.getChildAt(r8);
        r9.getHitRect(this.rect);
        if (this.rect.contains((int) r72, (int) r54) == false) goto L63;
        r9.setPressed(true);
        r9.setSelected(true);
        r9.drawableHotspotChanged(r72, r54 - r9.getTop());
        this.selectedMenuView = r9;
    L64:
        r8 = r8 + 1;
        goto L59
    L63:
        r9.setPressed(false);
        r9.setSelected(false);
        goto L64
    L65:
        ViewGroup r55 = (ViewGroup) r62.getChildAt(0);
        if (r55 == null) goto L71;
        int r64 = 0;
    L69:
        if (r64 >= r55.getChildCount()) goto L71;
        View r73 = r55.getChildAt(r64);
        r73.setPressed(false);
        r73.setSelected(false);
        r64 = r64 + 1;
        goto L69
    }

    public void openSearch(boolean r2) {
        checkCreateSearchField();
        FrameLayout r0 = this.searchContainer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() == 0) goto L12;
        ActionBarMenu r02 = this.parentMenu;
        if (r02 == null) goto L13;
        r02.parentActionBar.onSearchFieldVisibilityChanged(toggleSearch(r2));
        return;
    L13:
        return;
    }

    public void redrawPopup(int r2) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.popupLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getBackgroundColor() == r2) goto L13;
        this.popupLayout.setBackgroundColor(r2);
        ActionBarPopupWindow r22 = this.popupWindow;
        if (r22 != null) goto L9;
        return;
    L9:
        if (r22.isShowing() == false) goto L15;
        this.popupLayout.invalidate();
        return;
    L15:
        return;
    }

    public void removeAllSubItems() {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.popupLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.removeInnerViews();
    }

    public void removeSearchFilter(FiltersView.MediaFilterData r2) {
        if (r2.removable == true) goto L5;
        return;
    L5:
        this.currentSearchFilters.remove(r2);
        int r22 = this.selectedFilterIndex;
        if (r22 >= 0) goto L8;
    L9:
        this.selectedFilterIndex = this.currentSearchFilters.size() - 1;
    L10:
        onFiltersChanged();
        this.searchField.hideActionMode();
        return;
    L8:
        if (r22 <= (this.currentSearchFilters.size() - 1)) goto L10;
        goto L9
    }

    public void requestFocusOnSearchView() {
        if (this.searchContainer.getWidth() != 0) goto L5;
        return;
    L5:
        if (this.searchField.isFocused() == true) goto L9;
        this.searchField.requestFocus();
        AndroidUtilities.showKeyboard(this.searchField);
        return;
    }

    public ActionBarMenuItem setActionBarMenuItemSearchListener(ActionBarMenuItemSearchListener r1) {
        this.listener = r1;
        return this;
    }

    public void setAdditionalXOffset(int r1) {
        this.additionalXOffset = r1;
    }

    public void setAdditionalYOffset(int r1) {
        this.additionalYOffset = r1;
    }

    public ActionBarMenuItem setAllowCloseAnimation(boolean r1) {
        this.allowCloseAnimation = r1;
        return this;
    }

    @Override // android.view.View
    public void setAlpha(float r4) {
        boolean r0 = isVisibleForCenterTitle();
        float r1 = getAlpha();
        super.setAlpha(r4);
        if (r4 <= r1) goto L6;
        this.centerTitleVisibleForPartialAlpha = true;
    L9:
        if (ExteraConfig.getCenterTitle() == true) goto L11;
        return;
    L11:
        if (r0 == isVisibleForCenterTitle()) goto L19;
        ViewParent r3 = getParent();
        if ((r3 instanceof ActionBarMenu) == false) goto L20;
        Object r32 = ((ActionBarMenu) r3).getParent();
        if ((r32 instanceof View) == false) goto L21;
        ((View) r32).requestLayout();
        return;
    L21:
        return;
    L20:
        return;
    L19:
        return;
    L6:
        if (r4 >= r1) goto L9;
        this.centerTitleVisibleForPartialAlpha = false;
        goto L9
    }

    public void setBlurredBackgroundFactory(BlurredBackgroundDrawableViewFactory r1, BlurredBackgroundProvider r2) {
        this.subMenuFactory = r1;
        updateSubMenuGlass();
    }

    public void setDelegate(ActionBarMenuItemDelegate r1) {
        this.delegate = r1;
    }

    public void setDimMenu(float r1) {
        this.dimMenu = r1;
    }

    public void setFitSubItems(boolean r1) {
        this.popupLayout.setFitItems(r1);
    }

    public void setFixBackground(boolean r1) {
        this.fixBackground = r1;
        invalidate();
    }

    public void setForceSmoothKeyboard(boolean r1) {
        this.forceSmoothKeyboard = r1;
    }

    public void setIcon(int r2) {
        RLottieImageView r0 = this.iconView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.iconViewResId = r2;
        r0.setImageResource(r2);
    }

    public void setIcon(int r3, boolean r4) {
        RLottieImageView r0 = this.iconView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.iconViewResId == r3) goto L13;
        if (r4 == false) goto L10;
        this.iconViewResId = r3;
        AndroidUtilities.updateImageViewImageAnimated(r0, r3);
        return;
    L10:
        this.iconViewResId = r3;
        r0.setImageResource(r3);
        return;
    }

    public void setIcon(Drawable r3) {
        RLottieImageView r0 = this.iconView;
        if (r0 != null) goto L6;
        return;
    L6:
        if ((r3 instanceof RLottieDrawable) == false) goto L8;
        r0.setAnimation((RLottieDrawable) r3);
    L9:
        this.iconViewResId = 0;
        return;
    L8:
        r0.setImageDrawable(r3);
        goto L9
    }

    public void setIconColor(int r4) {
        RLottieImageView r0 = this.iconView;
        if (r0 == null) goto L5;
        r0.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.SRC_IN));
    L5:
        TextView r02 = this.textView;
        if (r02 == null) goto L8;
        r02.setTextColor(r4);
    L8:
        ImageView r3 = this.clearButton;
        if (r3 == null) goto L12;
        r3.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.SRC_IN));
        return;
    }

    public ActionBarMenuItem setIsSearchField(boolean r2) {
        return setIsSearchField(r2, false);
    }

    public ActionBarMenuItem setIsSearchField(boolean r2, boolean r3) {
        if (this.parentMenu != null) goto L5;
        return this;
    L5:
        this.isSearchField = r2;
        this.wrapSearchInScrollView = r3;
        return this;
    }

    public void setLayoutInScreen(boolean r1) {
        this.layoutInScreen = r1;
    }

    public void setLongClickEnabled(boolean r1) {
        this.longClickEnabled = r1;
    }

    public void setMenuXOffset(int r1) {
        this.xOffset = r1;
    }

    public void setMenuYOffset(int r1) {
        this.yOffset = r1;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r1) {
        this.onClickListener = r1;
        super.setOnClickListener(r1);
    }

    public void setOnMenuDismiss(Utilities.Callback<Boolean> r3) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L6;
        r0.setOnDismissListener(new ActionBarMenuItem$$ExternalSyntheticLambda14(this, r3));
        return;
    }

    public ActionBarMenuItem setOverrideMenuClick(boolean r1) {
        this.overrideMenuClick = r1;
        return this;
    }

    public void setPopupAnimationEnabled(boolean r2) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L5;
        r0.setAnimationEnabled(r2);
    L5:
        this.animationEnabled = r2;
    }

    public void setPopupItemsColor(int r5, boolean r6) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r4 = this.popupLayout;
        if (r4 == null) goto L16;
        LinearLayout r42 = r4.linearLayout;
        int r0 = r42.getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L22;
        View r2 = r42.getChildAt(r1);
        if ((r2 instanceof TextView) == false) goto L11;
        ((TextView) r2).setTextColor(r5);
    L15:
        r1 = r1 + 1;
        goto L6
    L11:
        if ((r2 instanceof ActionBarMenuSubItem) == false) goto L15;
        if (r6 == false) goto L14;
        ((ActionBarMenuSubItem) r2).setIconColor(r5);
        goto L15
    L14:
        ((ActionBarMenuSubItem) r2).setTextColor(r5);
        goto L15
    L22:
        return;
    }

    public void setPopupItemsSelectorColor(int r5) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r4 = this.popupLayout;
        if (r4 == null) goto L11;
        LinearLayout r42 = r4.linearLayout;
        int r0 = r42.getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L15;
        View r2 = r42.getChildAt(r1);
        if ((r2 instanceof ActionBarMenuSubItem) == false) goto L10;
        ((ActionBarMenuSubItem) r2).setSelectorColor(r5);
    L10:
        r1 = r1 + 1;
        goto L6
    L15:
        return;
    }

    public void setSearchAdditionalButton(View r1) {
        this.searchAdditionalButton = r1;
    }

    public void setSearchFieldCaption(CharSequence r3) {
        if (this.searchFieldCaption != null) goto L5;
        return;
    L5:
        boolean r0 = TextUtils.isEmpty(r3);
        TextView r1 = this.searchFieldCaption;
        if (r0 == false) goto L9;
        r1.setVisibility(8);
        return;
    L9:
        r1.setVisibility(0);
        this.searchFieldCaption.setText(r3);
    }

    public void setSearchFieldHint(CharSequence r2) {
        this.searchFieldHint = r2;
        if (this.searchFieldCaption != null) goto L5;
        return;
    L5:
        this.searchField.setHint(r2);
        setContentDescription(r2);
    }

    public void setSearchFieldText(CharSequence r2, boolean r3) {
        this.searchFieldText = r2;
        if (this.searchFieldCaption == null) goto L10;
        this.animateClear = r3;
        this.searchField.setText(r2);
        if (TextUtils.isEmpty(r2) == true) goto L9;
        this.searchField.setSelection(r2.length());
        return;
    L9:
        return;
    }

    public void setSearchPaddingStart(int r2) {
        this.searchItemPaddingStart = r2;
        FrameLayout r0 = this.searchContainer;
        if (r0 == null) goto L10;
        ((ViewGroup.MarginLayoutParams) r0.getLayoutParams()).leftMargin = AndroidUtilities.dp(r2);
        FrameLayout r22 = this.searchContainer;
        if (this.searchItemPaddingStart == 0) goto L7;
        boolean r02 = true;
    L8:
        r22.setClipChildren(r02);
        FrameLayout r1 = this.searchContainer;
        r1.setLayoutParams(r1.getLayoutParams());
        return;
    L7:
        r02 = false;
        goto L8
    }

    public void setShowSearchProgress(boolean r1) {
        CloseProgressDrawable2 r0 = this.progressDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r1 == false) goto L8;
        r0.startAnimation();
        return;
    L8:
        r0.stopAnimation();
    }

    public void setShowSubmenuByMove(boolean r1) {
        this.showSubmenuByMove = r1;
    }

    public void setShowedFromBottom(boolean r1) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.popupLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setShownFromBottom(r1);
    }

    public void setSubItemShown(int r1, boolean r2) {
        if (r2 == false) goto L5;
        showSubItem(r1);
        return;
    L5:
        hideSubItem(r1);
    }

    public void setSubMenuDelegate(ActionBarSubMenuItemDelegate r1) {
        this.subMenuDelegate = r1;
    }

    public void setSubMenuOpenSide(int r1) {
        this.subMenuOpenSide = r1;
    }

    public void setText(CharSequence r1) {
        TextView r0 = this.textView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setText(r1);
    }

    public void setTransitionOffset(float r1) {
        this.transitionOffset = r1;
        setTranslationX(0.0f);
    }

    @Override // android.view.View
    public void setTranslationX(float r2) {
        super.setTranslationX(r2 + this.transitionOffset);
    }

    public void setupPopupRadialSelectors(int r1) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = this.popupLayout;
        if (r0 == null) goto L6;
        r0.setupRadialSelectors(r1);
        return;
    }

    public void showSubItem(int r2) {
        showSubItem(r2, false);
    }

    public void showSubItem(int r4, boolean r5) {
        Item r52 = findLazyItem(r4);
        if (r52 == null) goto L5;
        r52.setVisibility(0);
    L5:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r53 = this.popupLayout;
        if (r53 == null) goto L16;
        View r42 = r53.findViewWithTag(Integer.valueOf(r4));
        if (r42 != null) goto L11;
        return;
    L11:
        if (r42.getVisibility() == 0) goto L15;
        r42.setAlpha(0.0f);
        r42.animate().alpha(1.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(150).start();
        r42.setVisibility(0);
        this.measurePopup = true;
        return;
    L15:
        return;
    }

    public boolean toggleSearch(boolean r15) {
        checkCreateSearchField();
        ActionBarMenuItemSearchListener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onPreToggleSearch();
    L6:
        if (this.searchContainer == null) goto L68;
        ActionBarMenuItemSearchListener r02 = this.listener;
        if (r02 != null) goto L10;
    L12:
        ActionBarMenuItemSearchListener r03 = this.listener;
        if (r03 == null) goto L18;
        Animator r04 = r03.getCustomToggleTransition();
        if (r04 == null) goto L18;
        r04.start();
        return true;
    L18:
        ArrayList r05 = new ArrayList();
        int r3 = 0;
    L20:
        if (r3 >= this.parentMenu.getChildCount()) goto L27;
        View r4 = this.parentMenu.getChildAt(r3);
        if ((r4 instanceof ActionBarMenuItem) == false) goto L26;
        RLottieImageView r42 = ((ActionBarMenuItem) r4).getIconView();
        if (r42 == null) goto L26;
        r05.add(r42);
    L26:
        r3 = r3 + 1;
        goto L20
    L27:
        Object r32 = this.searchContainer.getTag();
        FrameLayout r43 = this.searchContainer;
        Property r10 = View.ALPHA;
        if (r32 == null) goto L54;
        r43.setTag(null);
        AnimatorSet r33 = this.searchContainerAnimator;
        if (r33 == null) goto L32;
        r33.removeAllListeners();
        this.searchContainerAnimator.cancel();
    L32:
        AnimatorSet r34 = new AnimatorSet();
        this.searchContainerAnimator = r34;
        FrameLayout r44 = this.searchContainer;
        r34.playTogether(new Animator[]{ObjectAnimator.ofFloat(r44, r10, new float[]{r44.getAlpha(), 0.0f})});
        int r35 = 0;
    L34:
        if (r35 >= r05.size()) goto L36;
        ((View) r05.get(r35)).setAlpha(0.0f);
        this.searchContainerAnimator.playTogether(new Animator[]{ObjectAnimator.ofFloat((View) r05.get(r35), r10, new float[]{((View) r05.get(r35)).getAlpha(), 1.0f})});
        r35 = r35 + 1;
        goto L34
    L36:
        this.searchContainerAnimator.setDuration(150);
        this.searchContainerAnimator.addListener(new AnonymousClass2(this, r05));
        this.searchContainerAnimator.start();
        this.searchField.clearFocus();
        setVisibility(0);
        if (this.currentSearchFilters.isEmpty() == false) goto L39;
    L47:
        ActionBarMenuItemSearchListener r06 = this.listener;
        if (r06 == null) goto L50;
        r06.onSearchCollapse();
    L50:
        if (r15 == false) goto L52;
        AndroidUtilities.hideKeyboard(this.searchField);
    L52:
        this.parentMenu.requestLayout();
        requestLayout();
        return false;
    L39:
        if (this.listener == null) goto L47;
        int r07 = 0;
    L42:
        if (r07 >= this.currentSearchFilters.size()) goto L47;
        if (this.currentSearchFilters.get(r07).removable == false) goto L46;
        this.listener.onSearchFilterCleared(this.currentSearchFilters.get(r07));
    L46:
        r07 = r07 + 1;
        goto L42
    L54:
        r43.setVisibility(0);
        this.searchContainer.setAlpha(0.0f);
        AnimatorSet r36 = this.searchContainerAnimator;
        if (r36 == null) goto L57;
        r36.removeAllListeners();
        this.searchContainerAnimator.cancel();
    L57:
        AnimatorSet r37 = new AnimatorSet();
        this.searchContainerAnimator = r37;
        FrameLayout r45 = this.searchContainer;
        r37.playTogether(new Animator[]{ObjectAnimator.ofFloat(r45, r10, new float[]{r45.getAlpha(), 1.0f})});
        int r38 = 0;
    L58:
        int r46 = r05.size();
        AnimatorSet r8 = this.searchContainerAnimator;
        if (r38 >= r46) goto L61;
        r8.playTogether(new Animator[]{ObjectAnimator.ofFloat((View) r05.get(r38), r10, new float[]{((View) r05.get(r38)).getAlpha(), 0.0f})});
        r38 = r38 + 1;
        goto L58
    L61:
        r8.setDuration(150);
        this.searchContainerAnimator.addListener(new AnonymousClass3(this, r05));
        this.searchContainerAnimator.start();
        setVisibility(8);
        clearSearchFilters();
        this.searchField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.searchField.requestFocus();
        if (r15 == false) goto L64;
        AndroidUtilities.runOnUIThread(new ActionBarMenuItem$$ExternalSyntheticLambda1(this));
    L64:
        this.searchContainer.setTag(1);
        ActionBarMenuItemSearchListener r14 = this.listener;
        if (r14 == null) goto L67;
        r14.onSearchExpand();
    L67:
        return true;
    L10:
        if (r02.canToggleSearch() == true) goto L12;
    L68:
        return false;
    }

    public void toggleSubMenu() {
        toggleSubMenu(null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v7, types: [android.view.ViewGroup, android.widget.LinearLayout, org.telegram.ui.ActionBar.ActionBarMenuItem$1] */
    public void toggleSubMenu(View r12, View r13) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L5;
    L6:
        layoutLazyItems();
    L8:
        if (this.popupLayout == null) goto L84;
        ActionBarMenu r02 = this.parentMenu;
        if (r02 != null) goto L12;
    L18:
        Runnable r03 = this.showMenuRunnable;
        FrameLayout r1 = null;
        if (r03 == null) goto L21;
        AndroidUtilities.cancelRunOnUIThread(r03);
        this.showMenuRunnable = null;
    L21:
        ActionBarPopupWindow r04 = this.popupWindow;
        if (r04 != null) goto L24;
    L27:
        this.showSubMenuFrom = r13;
        ActionBarSubMenuItemDelegate r132 = this.subMenuDelegate;
        if (r132 == null) goto L31;
        r132.onShowSubMenu();
    L31:
        if (this.popupLayout.getParent() == null) goto L33;
        ((ViewGroup) this.popupLayout.getParent()).removeView(this.popupLayout);
    L33:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r133 = this.popupLayout;
        if (r12 == null) goto L51;
        ?? r134 = new AnonymousClass1(this, getContext(), r12);
        r134.setOrientation(1);
        r1 = new FrameLayout(getContext());
        r1.setAlpha(0.0f);
        ViewPropertyAnimator r4 = r1.animate().alpha(1.0f).setDuration(100);
        if (this.popupLayout.shownFromBottom == false) goto L38;
        long r5 = 165;
    L39:
        r4.setStartDelay(r5).start();
        if ((r12.getParent() instanceof ViewGroup) == false) goto L43;
        ((ViewGroup) r12.getParent()).removeView(r12);
    L43:
        if ((r12 instanceof ActionBarMenuSubItem) == true) goto L47;
        if ((r12 instanceof LinearLayout) == true) goto L47;
    L50:
        r1.addView(r12, LayoutHelper.createFrame(-1, -2.0f));
        r134.addView(r1, LayoutHelper.createLinear(-1, -2));
        r134.addView(this.popupLayout, LayoutHelper.createLinear(-2, -2, 0, 0, -10, 0, 0));
        this.popupLayout.setTopView(r1);
        ActionBarPopupWindow.ActionBarPopupWindowLayout r135 = r134;
    L52:
        updateSubMenuGlass();
        ActionBarPopupWindow r122 = new ActionBarPopupWindow(r135, -2, -2);
        this.popupWindow = r122;
        if (this.animationEnabled == false) goto L55;
        r122.setAnimationStyle(0);
    L56:
        boolean r123 = this.animationEnabled;
        if (r123 == true) goto L59;
        this.popupWindow.setAnimationEnabled(r123);
    L59:
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setClippingEnabled(true);
        if (this.layoutInScreen == false) goto L62;
        this.popupWindow.setLayoutInScreen(true);
    L62:
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.setSoftInputMode(0);
        r135.setFocusableInTouchMode(true);
        r135.setOnKeyListener(new ActionBarMenuItem$$ExternalSyntheticLambda5(this));
        this.popupWindow.setOnDismissListener(new ActionBarMenuItem$$ExternalSyntheticLambda6(this));
        r135.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x - AndroidUtilities.dp(40.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        if (r1 != null) goto L65;
    L73:
        this.measurePopup = false;
        this.processedPopupClick = false;
        this.popupWindow.setFocusable(true);
        if (r135.getMeasuredWidth() != 0) goto L76;
        boolean r124 = true;
    L77:
        updateOrShowPopup(true, r124);
        this.popupLayout.updateRadialSelectors();
        if (this.popupLayout.getSwipeBack() == null) goto L80;
        this.popupLayout.getSwipeBack().closeForeground(false);
    L80:
        this.popupWindow.startAnimation();
        float r125 = this.dimMenu;
        if (r125 <= 0.0f) goto L85;
        this.popupWindow.dimBehind(r125);
        return;
    L85:
        return;
    L76:
        r124 = false;
        goto L77
    L65:
        if (r1.getLayoutParams() == null) goto L73;
        if (this.popupLayout.getSwipeBack() == null) goto L73;
        View r126 = this.popupLayout.getSwipeBack().getChildAt(0);
        if (r126 == null) goto L73;
        if (r126.getMeasuredWidth() <= 0) goto L73;
        r1.getLayoutParams().width = r126.getMeasuredWidth() + AndroidUtilities.dp(16.0f);
        goto L73
    L55:
        r122.setAnimationStyle(R.style.PopupAnimation);
    L47:
        if (this.popupLayout.getGlassBackgroundFactory() == null) goto L49;
        r1.setBackground(GlassMenuHelper.createPanelBackground(this.popupLayout.getGlassBackgroundFactory(), this.resourcesProvider, r1));
        goto L50
    L49:
        Drawable r42 = ContextCompat.getDrawable(getContext(), R.drawable.popup_fixed_alert2).mutate();
        r42.setColorFilter(new PorterDuffColorFilter(this.popupLayout.getBackgroundColor(), PorterDuff.Mode.MULTIPLY));
        r1.setBackground(r42);
        goto L50
    L38:
        r5 = 0;
        goto L39
    L51:
        r133.setTopView(null);
        r135 = r133;
        goto L52
    L24:
        if (r04.isShowing() == false) goto L27;
        this.popupWindow.dismiss();
        return;
    L12:
        if (r02.isActionMode == false) goto L18;
        ActionBar r05 = r02.parentActionBar;
        if (r05 == null) goto L18;
        if (r05.isActionModeShowed() == true) goto L18;
        return;
    L84:
        return;
    L5:
        if (r0.isShowing() == true) goto L8;
        goto L6
    }

    public void updateColor() {
        int r1 = 0;
        if (this.searchFilterLayout == null) goto L12;
        int r0 = 0;
    L6:
        if (r0 >= this.searchFilterLayout.getChildCount()) goto L12;
        if ((this.searchFilterLayout.getChildAt(r0) instanceof SearchFilterView) == false) goto L10;
        ((SearchFilterView) this.searchFilterLayout.getChildAt(r0)).updateColors();
    L10:
        r0 = r0 + 1;
    L12:
        if (this.popupLayout != null) goto L14;
    L19:
        EditTextBoldCursor r02 = this.searchField;
        if (r02 == null) goto L29;
        int r12 = Theme.key_actionBarDefaultSearch;
        r02.setCursorColor(getThemedColor(r12));
        this.searchField.setHintTextColor(getThemedColor(Theme.key_actionBarDefaultSearchPlaceholder));
        this.searchField.setTextColor(getThemedColor(r12));
        this.searchField.setHighlightColor(getThemedColor(Theme.key_chat_inTextSelectionHighlight));
        this.searchField.setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor));
        return;
    L29:
        return;
    L14:
        if (r1 >= this.popupLayout.getItemsCount()) goto L19;
        if ((this.popupLayout.getItemAt(r1) instanceof ActionBarMenuSubItem) == false) goto L18;
        ((ActionBarMenuSubItem) this.popupLayout.getItemAt(r1)).setSelectorColor(getThemedColor(Theme.key_dialogButtonSelector));
    L18:
        r1 = r1 + 1;
        goto L14
    }
}
