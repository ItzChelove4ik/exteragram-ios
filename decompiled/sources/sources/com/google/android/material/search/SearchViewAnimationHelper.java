package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationCoordinator;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.FadeThroughUpdateListener;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.RectEvaluator;
import com.google.android.material.internal.ReversableAnimatedValueInterpolator;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.search.SearchView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
class SearchViewAnimationHelper {
    private static final float CONTENT_FROM_SCALE = 0.95f;
    private static final int DEFAULT_DURATION_MS = 100;
    private static final TimeInterpolator DEFAULT_INTERPOLATOR = null;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS = 42;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_ALPHA_DURATION_MS = 83;
    private static final long HIDE_CONTENT_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_SCALE_DURATION_MS = 250;
    private static final long HIDE_DURATION_MS = 250;
    private static final long HIDE_TRANSLATE_DURATION_MS = 300;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS = 50;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 250;
    private static final long SHOW_CONTENT_ALPHA_DURATION_MS = 150;
    private static final long SHOW_CONTENT_ALPHA_START_DELAY_MS = 75;
    private static final long SHOW_CONTENT_SCALE_DURATION_MS = 300;
    private static final long SHOW_DURATION_MS = 300;
    private static final long SHOW_SCRIM_ALPHA_DURATION_MS = 100;
    private static final long SHOW_TRANSLATE_DURATION_MS = 350;
    private static final long SHOW_TRANSLATE_KEYBOARD_START_DELAY_MS = 150;
    AnimationCoordinator activeCoordinator;
    AnimatorSet activeTranslateAnimatorSet;
    final AnimationDelegate animationDelegate;
    private final MaterialMainContainerBackHelper backHelper;
    private AnimatorSet backProgressAnimatorSet;
    private final View backgroundView;
    private final ImageButton clearButton;
    private final TouchObserverFrameLayout contentContainer;
    private final Context context;
    private final View divider;
    private final TextView dummyTextView;
    private final Toolbar dummyToolbar;
    private final int durationShort1;
    private final int durationShort2;
    private final EditText editText;
    private final FrameLayout headerContainer;
    private final ClippableRoundedCornerLayout rootView;
    private final View scrim;
    private SearchBar searchBar;
    private final TextView searchPrefix;
    private final SearchView searchView;
    private final TimeInterpolator standardAccelerateInterpolator;
    private final TimeInterpolator standardDecelerateInterpolator;
    private final Toolbar toolbar;
    private final FrameLayout toolbarContainer;

    /* renamed from: com.google.android.material.search.SearchViewAnimationHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements AnimationCoordinator.Listener {
        final /* synthetic */ SearchViewAnimationHelper this$0;
        final /* synthetic */ AnimationCoordinator val$coordinator;
        final /* synthetic */ boolean val$show;

        public AnonymousClass1(SearchViewAnimationHelper r1, boolean r2, AnimationCoordinator r3) {
            this.this$0 = r1;
            this.val$show = r2;
            this.val$coordinator = r3;
        }

        @Override // com.google.android.material.animation.AnimationCoordinator.Listener
        public void onAnimationsEnd() {
            this.this$0.animationDelegate.onAnimationEnd(this.val$show);
            if (SearchViewAnimationHelper.access$100(this.this$0).isAdjustNothingSoftInputMode() == true) goto L5;
            SearchViewAnimationHelper.access$100(this.this$0).requestFocusAndShowKeyboardIfNeeded();
        L5:
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.SHOWN);
            SearchViewAnimationHelper r0 = this.this$0;
            if (r0.activeCoordinator != this.val$coordinator) goto L9;
            r0.activeCoordinator = null;
            return;
        }

        @Override // com.google.android.material.animation.AnimationCoordinator.Listener
        public void onAnimationsStart() {
            this.this$0.animationDelegate.onAnimationStart(this.val$show);
            SearchViewAnimationHelper.access$200(this.this$0).setVisibility(0);
            SearchViewAnimationHelper.access$600(this.this$0).stopOnLoadAnimation();
        }
    }

    /* renamed from: com.google.android.material.search.SearchViewAnimationHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements AnimationCoordinator.Listener {
        final /* synthetic */ SearchViewAnimationHelper this$0;
        final /* synthetic */ AnimationCoordinator val$coordinator;
        final /* synthetic */ boolean val$show;

        public AnonymousClass2(SearchViewAnimationHelper r1, boolean r2, AnimationCoordinator r3) {
            this.this$0 = r1;
            this.val$show = r2;
            this.val$coordinator = r3;
        }

        @Override // com.google.android.material.animation.AnimationCoordinator.Listener
        public void onAnimationsEnd() {
            this.this$0.animationDelegate.onAnimationEnd(this.val$show);
            SearchViewAnimationHelper.access$200(this.this$0).setVisibility(8);
            if (SearchViewAnimationHelper.access$100(this.this$0).isAdjustNothingSoftInputMode() == true) goto L5;
            SearchViewAnimationHelper.access$300(this.this$0).clearFocus();
        L5:
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.HIDDEN);
            SearchViewAnimationHelper r0 = this.this$0;
            if (r0.activeCoordinator != this.val$coordinator) goto L9;
            r0.activeCoordinator = null;
            return;
        }

        @Override // com.google.android.material.animation.AnimationCoordinator.Listener
        public void onAnimationsStart() {
            this.this$0.animationDelegate.onAnimationStart(this.val$show);
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.HIDING);
        }
    }

    /* renamed from: com.google.android.material.search.SearchViewAnimationHelper$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchViewAnimationHelper this$0;
        final /* synthetic */ AnimatorSet val$animatorSet;

        public AnonymousClass3(SearchViewAnimationHelper r1, AnimatorSet r2) {
            this.this$0 = r1;
            this.val$animatorSet = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (SearchViewAnimationHelper.access$100(this.this$0).isAdjustNothingSoftInputMode() == true) goto L5;
            SearchViewAnimationHelper.access$100(this.this$0).requestFocusAndShowKeyboardIfNeeded();
        L5:
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.SHOWN);
            SearchViewAnimationHelper r22 = this.this$0;
            if (r22.activeTranslateAnimatorSet != this.val$animatorSet) goto L9;
            r22.activeTranslateAnimatorSet = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            SearchViewAnimationHelper.access$200(this.this$0).setVisibility(0);
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.SHOWING);
        }
    }

    /* renamed from: com.google.android.material.search.SearchViewAnimationHelper$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchViewAnimationHelper this$0;
        final /* synthetic */ AnimatorSet val$animatorSet;

        public AnonymousClass4(SearchViewAnimationHelper r1, AnimatorSet r2) {
            this.this$0 = r1;
            this.val$animatorSet = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            SearchViewAnimationHelper.access$200(this.this$0).setVisibility(8);
            if (SearchViewAnimationHelper.access$100(this.this$0).isAdjustNothingSoftInputMode() == true) goto L5;
            SearchViewAnimationHelper.access$300(this.this$0).clearFocus();
        L5:
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.HIDDEN);
            SearchViewAnimationHelper r22 = this.this$0;
            if (r22.activeTranslateAnimatorSet != this.val$animatorSet) goto L9;
            r22.activeTranslateAnimatorSet = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            SearchViewAnimationHelper.access$100(this.this$0).setTransitionState(SearchView.TransitionState.HIDING);
        }
    }

    public interface AnimationDelegate {
        AnimatorSet getExpandCollapseAnimatorSet(boolean r1);

        List<SpringAnimation> getExpandCollapseSpringAnimations(boolean r1);

        void onAnimationEnd(boolean r1);

        void onAnimationStart(boolean r1);

        void setUpDummyToolbarIfNeeded();

        void startButtonsTranslationAnimation();
    }

    public class ContainedAnimationDelegate implements AnimationDelegate {
        final /* synthetic */ SearchViewAnimationHelper this$0;

        /* renamed from: com.google.android.material.search.SearchViewAnimationHelper$ContainedAnimationDelegate$1, reason: invalid class name */
        public class AnonymousClass1 extends FloatPropertyCompat<View> {
            final /* synthetic */ ContainedAnimationDelegate this$1;

            public AnonymousClass1(ContainedAnimationDelegate r1, String r2) {
                this.this$1 = r1;
                super(r2);
            }

            /* renamed from: getValue, reason: avoid collision after fix types in other method */
            public float getValue2(View r1) {
                return r1.getWidth();
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public /* bridge */ /* synthetic */ float getValue(View r1) {
                return getValue2(r1);
            }

            /* renamed from: setValue, reason: avoid collision after fix types in other method */
            public void setValue2(View r1, float r2) {
                ContainedAnimationDelegate.access$2800(this.this$1, r1, (int) r2);
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
                setValue2(r1, r2);
            }
        }

        public static /* synthetic */ void $r8$lambda$6URjXNR7jTuHw2NNxIDGHhwZUSU(ContainedAnimationDelegate r1, ValueAnimator r2) {
            r1.setWidth(SearchViewAnimationHelper.access$2200(r1.this$0), ((Integer) r2.getAnimatedValue()).intValue());
        }

        public static /* synthetic */ void $r8$lambda$hqbvop16BkV8NlN9PzC9mY8yPMk(ContainedAnimationDelegate r0, boolean r1, Toolbar r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
            if (r1 == false) goto L5;
            r0.setWidth(r2, -1);
            return;
        L5:
            r0.getClass();
        }

        /* renamed from: $r8$lambda$lORhqfiXmNX9A8IrgvQNrlF8-RM, reason: not valid java name */
        public static /* synthetic */ void m1896$r8$lambda$lORhqfiXmNX9A8IrgvQNrlF8RM(ContainedAnimationDelegate r0, ValueAnimator r1) {
            SearchViewAnimationHelper.access$2300(r0.this$0, ((Float) r1.getAnimatedValue()).floatValue());
        }

        public static /* synthetic */ void $r8$lambda$q6M_sf_zgyraq6wKlJrDRN0UWVQ(ContainedAnimationDelegate r0, ValueAnimator r1) {
            SearchViewAnimationHelper.access$1400(r0.this$0).setAlpha(((Float) r1.getAnimatedValue()).floatValue());
        }

        public ContainedAnimationDelegate(SearchViewAnimationHelper r1) {
            this.this$0 = r1;
        }

        public static /* synthetic */ void access$2800(ContainedAnimationDelegate r0, View r1, int r2) {
            r0.setWidth(r1, r2);
        }

        private ValueAnimator getAlphaValueAnimator(boolean r1) {
            float[] r0 = {1.0f, 0.0f};
            if (r1 == false) goto L7;
            r0 = {x0016: FILL_ARRAY_DATA , data: [0, 1065353216} // fill-array;
            return ValueAnimator.ofFloat(r0);
        L7:
            return ValueAnimator.ofFloat(r0);
        }

        private Animator getBackgroundAlphaAnimator(boolean r4) {
            ValueAnimator r0 = getAlphaValueAnimator(r4);
            r0.setDuration(SearchViewAnimationHelper.access$2400(this.this$0));
            if (r4 == false) goto L5;
            long r1 = 0;
        L6:
            r0.setStartDelay(r1);
            SearchViewAnimationHelper r12 = this.this$0;
            if (r4 == false) goto L9;
            TimeInterpolator r42 = SearchViewAnimationHelper.access$2600(r12);
        L10:
            r0.setInterpolator(r42);
            r0.addUpdateListener(new SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda2(this));
            return r0;
        L9:
            r42 = SearchViewAnimationHelper.access$2700(r12);
            goto L10
        L5:
            r1 = SearchViewAnimationHelper.access$2500(this.this$0);
            goto L6
        }

        private Animator getContentAlphaAnimator(boolean r4) {
            ValueAnimator r0 = getAlphaValueAnimator(r4);
            r0.setDuration(SearchViewAnimationHelper.access$2400(this.this$0));
            if (r4 == false) goto L5;
            long r1 = SearchViewAnimationHelper.access$2500(this.this$0);
        L6:
            r0.setStartDelay(r1);
            SearchViewAnimationHelper r12 = this.this$0;
            if (r4 == false) goto L9;
            TimeInterpolator r42 = SearchViewAnimationHelper.access$2700(r12);
        L10:
            r0.setInterpolator(r42);
            r0.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{SearchViewAnimationHelper.access$1800(this.this$0)}));
            return r0;
        L9:
            r42 = SearchViewAnimationHelper.access$2600(r12);
            goto L10
        L5:
            r1 = 0;
            goto L6
        }

        private SpringAnimation getDummyTextTranslationXSpringAnimation(boolean r2) {
            return getTextTranslationXSpringAnimation(r2, SearchViewAnimationHelper.access$2200(this.this$0));
        }

        private Animator getDummyTextViewWidthAnimator(boolean r4) {
            SearchViewAnimationHelper r0 = this.this$0;
            if (r4 == false) goto L5;
            TextView r02 = SearchViewAnimationHelper.access$600(r0).getTextView();
        L6:
            SearchViewAnimationHelper r1 = this.this$0;
            if (r4 == false) goto L9;
            TextView r12 = SearchViewAnimationHelper.access$300(r1);
        L10:
            ValueAnimator r03 = ValueAnimator.ofInt(new int[]{r02.getWidth(), r12.getWidth()});
            r03.setDuration(SearchViewAnimationHelper.access$2400(this.this$0));
            SearchViewAnimationHelper r13 = this.this$0;
            if (r4 == false) goto L13;
            TimeInterpolator r42 = SearchViewAnimationHelper.access$2600(r13);
        L14:
            r03.setInterpolator(r42);
            r03.addUpdateListener(new SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda3(this));
            return r03;
        L13:
            r42 = SearchViewAnimationHelper.access$2700(r13);
            goto L14
        L9:
            r12 = SearchViewAnimationHelper.access$600(r1).getTextView();
            goto L10
        L5:
            r02 = SearchViewAnimationHelper.access$300(r0);
            goto L6
        }

        private SpringAnimation getDummyToolbarTranslationXSpringAnimation(boolean r2) {
            return getToolbarTranslationXSpringAnimation(r2, SearchViewAnimationHelper.access$400(this.this$0));
        }

        private SpringAnimation getDummyToolbarWidthSpringAnimation(boolean r2) {
            return getToolbarWidthSpringAnimation(r2, SearchViewAnimationHelper.access$400(this.this$0));
        }

        private SpringAnimation getEditTextTranslationXSpringAnimation(boolean r2) {
            return getTextTranslationXSpringAnimation(r2, SearchViewAnimationHelper.access$300(this.this$0));
        }

        private Animator getSearchBarSiblingsTranslationAnimator(boolean r8) {
            AnimatorSet r0 = new AnimatorSet();
            AppBarLayout r1 = SearchViewAnimationHelper.access$600(this.this$0).getAppBarLayoutParentIfExists();
            if (SearchViewAnimationHelper.access$600(this.this$0) == null) goto L21;
            if (r1 == null) goto L21;
            View r2 = getStartSiblingView(r1);
            View r3 = getEndSiblingView(r1);
            boolean r4 = ViewUtils.isLayoutRtl(SearchViewAnimationHelper.access$600(this.this$0));
            int r5 = r1.getWidth();
            if (r2 == null) goto L14;
            Rect r6 = ViewUtils.calculateOffsetRectFromBounds(r1, r2);
            if (r4 == false) goto L12;
            int r62 = r5 - r6.left;
        L13:
            r0.playTogether(new Animator[]{getSiblingTranslationAnimator(r2, r8, r62)});
            r0.playTogether(new Animator[]{getSiblingAlphaAnimator(r2, r8)});
            goto L14
        L12:
            r62 = -r6.right;
        L14:
            if (r3 == null) goto L20;
            Rect r12 = ViewUtils.calculateOffsetRectFromBounds(r1, r3);
            if (r4 == false) goto L18;
            float r13 = -r12.right;
        L19:
            r0.playTogether(new Animator[]{getSiblingTranslationAnimator(r3, r8, r13)});
            r0.playTogether(new Animator[]{getSiblingAlphaAnimator(r3, r8)});
            goto L20
        L18:
            r13 = r5 - r12.left;
        L20:
            r0.setDuration(SearchViewAnimationHelper.access$2400(this.this$0));
            r0.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        L21:
            return r0;
        }

        private Animator getSiblingAlphaAnimator(View r1, boolean r2) {
            ValueAnimator r0 = getAlphaValueAnimator(!r2);
            r0.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{r1}));
            return r0;
        }

        private Animator getSiblingTranslationAnimator(View r2, boolean r3, float r4) {
            if (r3 == false) goto L5;
            float r0 = 0.0f;
        L6:
            if (r3 == true) goto L9;
            r4 = 0.0f;
        L9:
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r0, r4});
            r1.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{r2}));
            return r1;
        L5:
            r0 = r4;
            goto L6
        }

        private SpringAnimation getSpringAnimation(View r2, FloatPropertyCompat<View> r3, float r4, float r5) {
            SpringAnimation r0 = new SpringAnimation(r2, r3);
            r0.setSpring(MotionUtils.resolveThemeSpringForce(SearchViewAnimationHelper.access$2900(this.this$0), R.attr.motionSpringFastSpatial, R.style.Motion_Material3_Spring_Standard_Default_Spatial));
            r0.setStartValue(r4);
            r0.getSpring().setFinalPosition(r5);
            return r0;
        }

        private SpringAnimation getTextTranslationXSpringAnimation(boolean r4, View r5) {
            TextView r0 = SearchViewAnimationHelper.access$600(this.this$0).getPlaceholderTextView();
            if (TextUtils.isEmpty(r0.getText()) == true) goto L5;
            if (r4 == true) goto L5;
        L6:
            float r1 = SearchViewAnimationHelper.access$2100(this.this$0, r0, r5) - getToolbarTranslationX(SearchViewAnimationHelper.access$1400(this.this$0));
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.access$600(this.this$0)) == false) goto L10;
            r1 = r1 + (r0.getWidth() - r5.getWidth());
        L10:
            if (r4 == false) goto L12;
            float r2 = r1;
        L13:
            if (r4 == false) goto L16;
            r1 = 0.0f;
        L16:
            return getSpringAnimation(r5, DynamicAnimation.TRANSLATION_X, r2, r1);
        L12:
            r2 = 0.0f;
        L5:
            r0 = SearchViewAnimationHelper.access$600(this.this$0).getTextView();
            goto L6
        }

        private View getToolbarActionMenuView() {
            ViewParent r1 = SearchViewAnimationHelper.access$600(this.this$0).getParent();
            if ((r1 instanceof Toolbar) == true) goto L7;
            return null;
        L7:
            return ToolbarUtils.getActionMenuView((Toolbar) r1);
        }

        private Animator getToolbarAlphaAnimator(boolean r4) {
            ValueAnimator r0 = getAlphaValueAnimator(r4);
            r0.setDuration(SearchViewAnimationHelper.access$2400(this.this$0));
            SearchViewAnimationHelper r1 = this.this$0;
            if (r4 == false) goto L5;
            TimeInterpolator r42 = SearchViewAnimationHelper.access$2600(r1);
        L6:
            r0.setInterpolator(r42);
            r0.addUpdateListener(new SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda1(this));
            return r0;
        L5:
            r42 = SearchViewAnimationHelper.access$2700(r1);
            goto L6
        }

        private SpringAnimation getToolbarContainerTranslationYSpringAnimation(boolean r4) {
            int r0 = getToolbarTranslationY();
            if (r4 == false) goto L5;
            int r2 = r0;
        L6:
            if (r4 == false) goto L9;
            r0 = 0;
        L9:
            return getSpringAnimation(SearchViewAnimationHelper.access$1900(this.this$0), DynamicAnimation.TRANSLATION_Y, r2, r0);
        L5:
            r2 = 0;
            goto L6
        }

        private View getToolbarNavigationIconButton() {
            ViewParent r1 = SearchViewAnimationHelper.access$600(this.this$0).getParent();
            if ((r1 instanceof Toolbar) == true) goto L7;
            return null;
        L7:
            return ToolbarUtils.getNavigationIconButton((Toolbar) r1);
        }

        private int getToolbarTranslationX(Toolbar r4) {
            SearchViewAnimationHelper r0 = this.this$0;
            int r02 = SearchViewAnimationHelper.access$1600(r0, SearchViewAnimationHelper.access$600(r0));
            int r1 = SearchViewAnimationHelper.access$1900(this.this$0).getPaddingStart();
            int r42 = ((ViewGroup.MarginLayoutParams) r4.getLayoutParams()).getMarginStart();
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.access$600(this.this$0)) == false) goto L7;
            return (r02 + SearchViewAnimationHelper.access$600(this.this$0).getWidth()) - ((SearchViewAnimationHelper.access$1900(this.this$0).getWidth() - r1) - r42);
        L7:
            return (r02 - r1) - r42;
        }

        private SpringAnimation getToolbarTranslationXSpringAnimation(boolean r2) {
            return getToolbarTranslationXSpringAnimation(r2, SearchViewAnimationHelper.access$1400(this.this$0));
        }

        private SpringAnimation getToolbarTranslationXSpringAnimation(boolean r4, Toolbar r5) {
            int r0 = getToolbarTranslationX(r5);
            if (r4 == false) goto L5;
            int r2 = r0;
        L6:
            if (r4 == false) goto L9;
            r0 = 0;
        L9:
            return getSpringAnimation(r5, DynamicAnimation.TRANSLATION_X, r2, r0);
        L5:
            r2 = 0;
            goto L6
        }

        private int getToolbarTranslationY() {
            SearchViewAnimationHelper r0 = this.this$0;
            int r02 = SearchViewAnimationHelper.access$2000(r0, SearchViewAnimationHelper.access$600(r0));
            SearchViewAnimationHelper r2 = this.this$0;
            return r02 - SearchViewAnimationHelper.access$2000(r2, SearchViewAnimationHelper.access$1400(r2));
        }

        private int getToolbarWidth() {
            int r0 = SearchViewAnimationHelper.access$1900(this.this$0).getWidth();
            int r1 = SearchViewAnimationHelper.access$1900(this.this$0).getPaddingStart() + SearchViewAnimationHelper.access$1900(this.this$0).getPaddingEnd();
            ViewGroup.MarginLayoutParams r3 = (ViewGroup.MarginLayoutParams) SearchViewAnimationHelper.access$1400(this.this$0).getLayoutParams();
            int r02 = r0 - r1;
            return r02 - (r3.getMarginStart() + r3.getMarginEnd());
        }

        private SpringAnimation getToolbarWidthSpringAnimation(boolean r2) {
            return getToolbarWidthSpringAnimation(r2, SearchViewAnimationHelper.access$1400(this.this$0));
        }

        private SpringAnimation getToolbarWidthSpringAnimation(boolean r4, Toolbar r5) {
            int r0 = SearchViewAnimationHelper.access$600(this.this$0).getWidth();
            int r1 = getToolbarWidth();
            if (r4 == false) goto L5;
            int r2 = r0;
        L6:
            if (r4 == false) goto L8;
            r0 = r1;
        L8:
            SpringAnimation r02 = getSpringAnimation(r5, getWidthViewProperty(), r2, r0);
            r02.addEndListener(new SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda0(this, r4, r5));
            return r02;
        L5:
            r2 = r1;
            goto L6
        }

        private FloatPropertyCompat<View> getWidthViewProperty() {
            return new AnonymousClass1(this, "width");
        }

        private void setUpDummyTextViewIfNeeded() {
            TextView r0 = SearchViewAnimationHelper.access$600(this.this$0).getTextView();
            SearchViewAnimationHelper.access$2200(this.this$0).setText(r0.getText());
            SearchViewAnimationHelper.access$2200(this.this$0).setHint(r0.getHint());
            SearchViewAnimationHelper.access$2200(this.this$0).setVisibility(0);
        }

        private void setWidth(View r1, int r2) {
            ViewGroup.LayoutParams r0 = r1.getLayoutParams();
            r0.width = r2;
            r1.setLayoutParams(r0);
        }

        public View getEndSiblingView(AppBarLayout r3) {
            int r0 = SearchViewAnimationHelper.access$600(this.this$0).getEndSiblingViewId();
            if (r0 == (-1)) goto L7;
            return r3.findViewById(r0);
        L7:
            return getToolbarActionMenuView();
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public AnimatorSet getExpandCollapseAnimatorSet(boolean r8) {
            AnimatorSet r0 = new AnimatorSet();
            r0.playTogether(new Animator[]{getBackgroundAlphaAnimator(r8), getContentAlphaAnimator(r8), getToolbarAlphaAnimator(r8), getDummyTextViewWidthAnimator(r8), SearchViewAnimationHelper.access$900(this.this$0, r8), getSearchBarSiblingsTranslationAnimator(r8)});
            return r0;
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public List<SpringAnimation> getExpandCollapseSpringAnimations(boolean r8) {
            return Arrays.asList(new SpringAnimation[]{getToolbarWidthSpringAnimation(r8), getToolbarTranslationXSpringAnimation(r8), getDummyToolbarWidthSpringAnimation(r8), getDummyToolbarTranslationXSpringAnimation(r8), getToolbarContainerTranslationYSpringAnimation(r8), getEditTextTranslationXSpringAnimation(r8), getDummyTextTranslationXSpringAnimation(r8)});
        }

        public View getStartSiblingView(AppBarLayout r3) {
            int r0 = SearchViewAnimationHelper.access$600(this.this$0).getStartSiblingViewId();
            if (r0 == (-1)) goto L7;
            return r3.findViewById(r0);
        L7:
            return getToolbarNavigationIconButton();
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationEnd(boolean r2) {
            SearchViewAnimationHelper r0 = this.this$0;
            if (r2 == false) goto L5;
            SearchViewAnimationHelper.access$2300(r0, 1.0f);
            SearchViewAnimationHelper.access$1800(this.this$0).setAlpha(1.0f);
        L6:
            SearchViewAnimationHelper.access$400(this.this$0).setVisibility(4);
            setWidth(SearchViewAnimationHelper.access$2200(this.this$0), -2);
            return;
        L5:
            SearchViewAnimationHelper.access$2300(r0, 0.0f);
            SearchViewAnimationHelper.access$1800(this.this$0).setAlpha(0.0f);
            SearchViewAnimationHelper.access$600(this.this$0).setVisibility(0);
            goto L6
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationStart(boolean r2) {
            SearchViewAnimationHelper r0 = this.this$0;
            if (r2 == false) goto L5;
            SearchViewAnimationHelper.access$2300(r0, 0.0f);
            SearchViewAnimationHelper.access$1400(this.this$0).setAlpha(0.0f);
            SearchViewAnimationHelper.access$1800(this.this$0).setAlpha(0.0f);
            SearchViewAnimationHelper.access$600(this.this$0).setVisibility(4);
        L6:
            SearchViewAnimationHelper.access$400(this.this$0).setVisibility(0);
            return;
        L5:
            SearchViewAnimationHelper.access$2300(r0, 1.0f);
            SearchViewAnimationHelper.access$1800(this.this$0).setAlpha(1.0f);
            goto L6
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void setUpDummyToolbarIfNeeded() {
            setUpDummyTextViewIfNeeded();
            if (SearchViewAnimationHelper.access$600(this.this$0).getBackground() != null) goto L5;
        L7:
            Menu r0 = SearchViewAnimationHelper.access$400(this.this$0).getMenu();
            if (r0 == null) goto L11;
            r0.clear();
        L11:
            if (SearchViewAnimationHelper.access$500(this.this$0) == false) goto L14;
            SearchViewAnimationHelper.access$400(this.this$0).inflateMenu(SearchViewAnimationHelper.access$600(this.this$0).getMenuResId());
            SearchViewAnimationHelper r2 = this.this$0;
            SearchViewAnimationHelper.access$700(r2, SearchViewAnimationHelper.access$400(r2));
            return;
        L14:
            return;
        L5:
            if (SearchViewAnimationHelper.access$600(this.this$0).getBackground().getConstantState() == null) goto L7;
            SearchViewAnimationHelper.access$400(this.this$0).setBackground(SearchViewAnimationHelper.access$600(this.this$0).getBackground().getConstantState().newDrawable());
            goto L7
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void startButtonsTranslationAnimation() {
        }
    }

    public class DefaultAnimationDelegate implements AnimationDelegate {
        final /* synthetic */ SearchViewAnimationHelper this$0;

        public static /* synthetic */ void $r8$lambda$Xh4NQlxIA9zmse1qsLizeWusSbs(DefaultAnimationDelegate r2, ValueAnimator r3) {
            SearchViewAnimationHelper.access$300(r2.this$0).setAlpha(((Float) r3.getAnimatedValue()).floatValue());
            SearchViewAnimationHelper.access$600(r2.this$0).getTextView().setAlpha(1.0f - ((Float) r3.getAnimatedValue()).floatValue());
        }

        /* renamed from: $r8$lambda$mm9Vl7gBm_L8DAbth-XBLxaIDrw, reason: not valid java name */
        public static /* synthetic */ void m1897$r8$lambda$mm9Vl7gBm_L8DAbthXBLxaIDrw(DefaultAnimationDelegate r0, Rect r1, ValueAnimator r2) {
            r0.getClass();
            r1.right = ((Integer) r2.getAnimatedValue()).intValue();
            SearchViewAnimationHelper.access$300(r0.this$0).setClipBounds(r1);
        }

        public static /* synthetic */ void $r8$lambda$qIGmc5_uOjMwP8RsayWx6fvxohk(DefaultAnimationDelegate r0, float r1, float[] r2, Rect r3, ValueAnimator r4) {
            r0.getClass();
            float[] r12 = r0.lerpCornerRadii(r1, r2, r4.getAnimatedFraction());
            SearchViewAnimationHelper.access$200(r0.this$0).updateClipBoundsAndCornerRadii(r3, r12);
        }

        private DefaultAnimationDelegate(SearchViewAnimationHelper r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ DefaultAnimationDelegate(SearchViewAnimationHelper r1, AnonymousClass1 r2) {
            this(r1);
        }

        private void addActionMenuViewAnimatorIfNeeded(AnimatorSet r8) {
            ActionMenuView r0 = ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.access$1400(this.this$0));
            if (r0 != null) goto L5;
            return;
        L5:
            ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{SearchViewAnimationHelper.access$2100(this.this$0, ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.access$600(this.this$0)), r0), 0.0f});
            r3.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{r0}));
            ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{getFromTranslationY(), 0.0f});
            r7.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{r0}));
            r8.playTogether(new Animator[]{r3, r7});
        }

        private void addBackButtonTranslationAnimatorIfNeeded(AnimatorSet r8) {
            ImageButton r0 = ToolbarUtils.getNavigationIconButton(SearchViewAnimationHelper.access$1400(this.this$0));
            if (r0 != null) goto L5;
            return;
        L5:
            ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{SearchViewAnimationHelper.access$2100(this.this$0, ToolbarUtils.getNavigationIconButton(SearchViewAnimationHelper.access$600(this.this$0)), r0), 0.0f});
            r3.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{r0}));
            ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{getFromTranslationY(), 0.0f});
            r7.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{r0}));
            r8.playTogether(new Animator[]{r3, r7});
        }

        private void addEditTextClipAnimator(AnimatorSet r5) {
            if (SearchViewAnimationHelper.access$600(this.this$0) != null) goto L5;
            return;
        L5:
            if (TextUtils.equals(SearchViewAnimationHelper.access$300(this.this$0).getText(), SearchViewAnimationHelper.access$600(this.this$0).getText()) == false) goto L10;
            Rect r0 = new Rect(0, 0, SearchViewAnimationHelper.access$300(this.this$0).getWidth(), SearchViewAnimationHelper.access$300(this.this$0).getHeight());
            ValueAnimator r1 = ValueAnimator.ofInt(new int[]{SearchViewAnimationHelper.access$600(this.this$0).getTextView().getWidth(), SearchViewAnimationHelper.access$300(this.this$0).getWidth()});
            r1.addUpdateListener(new SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda2(this, r0));
            r5.playTogether(new Animator[]{r1});
            return;
        }

        private void addTextFadeAnimatorIfNeeded(AnimatorSet r3) {
            if (SearchViewAnimationHelper.access$600(this.this$0) != null) goto L5;
            return;
        L5:
            if (TextUtils.equals(SearchViewAnimationHelper.access$300(this.this$0).getText(), SearchViewAnimationHelper.access$600(this.this$0).getText()) == true) goto L10;
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r0.addUpdateListener(new SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda0(this));
            r3.playTogether(new Animator[]{r0});
            return;
        }

        private Animator getActionMenuViewsAlphaAnimator(boolean r4) {
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            if (r4 == false) goto L5;
            long r1 = 300;
        L6:
            r0.setDuration(r1);
            r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            if (SearchViewAnimationHelper.access$100(this.this$0).isMenuItemsAnimated() == false) goto L9;
            r0.addUpdateListener(new FadeThroughUpdateListener(ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.access$400(this.this$0)), ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.access$1400(this.this$0))));
        L9:
            return r0;
        L5:
            r1 = 250;
            goto L6
        }

        private AnimatorSet getButtonsTranslationAnimator(boolean r4) {
            AnimatorSet r0 = new AnimatorSet();
            addBackButtonTranslationAnimatorIfNeeded(r0);
            addActionMenuViewAnimatorIfNeeded(r0);
            if (r4 == false) goto L5;
            long r1 = 300;
        L6:
            r0.setDuration(r1);
            r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            return r0;
        L5:
            r1 = 250;
            goto L6
        }

        private Animator getContentAlphaAnimator(boolean r5) {
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            if (r5 == false) goto L5;
            long r2 = 150;
        L6:
            r1.setDuration(r2);
            if (r5 == false) goto L9;
            long r22 = 75;
        L10:
            r1.setStartDelay(r22);
            r1.setInterpolator(ReversableAnimatedValueInterpolator.of(r5, AnimationUtils.LINEAR_INTERPOLATOR));
            r1.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{SearchViewAnimationHelper.access$1700(this.this$0), SearchViewAnimationHelper.access$1800(this.this$0)}));
            return r1;
        L9:
            r22 = 0;
            goto L10
        L5:
            r2 = SearchViewAnimationHelper.HIDE_CONTENT_ALPHA_DURATION_MS;
            goto L6
        }

        private Animator getContentAnimator(boolean r4) {
            AnimatorSet r0 = new AnimatorSet();
            r0.playTogether(new Animator[]{getContentAlphaAnimator(r4), getDividerAnimator(r4), getContentScaleAnimator(r4)});
            return r0;
        }

        private Animator getContentScaleAnimator(boolean r4) {
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{SearchViewAnimationHelper.CONTENT_FROM_SCALE, 1.0f});
            if (r4 == false) goto L5;
            long r1 = 300;
        L6:
            r0.setDuration(r1);
            r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            r0.addUpdateListener(MultiViewUpdateListener.scaleListener(new View[]{SearchViewAnimationHelper.access$1800(this.this$0)}));
            return r0;
        L5:
            r1 = 250;
            goto L6
        }

        private Animator getDividerAnimator(boolean r4) {
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{(SearchViewAnimationHelper.access$1800(this.this$0).getHeight() * 0.050000012f) / 2.0f, 0.0f});
            if (r4 == false) goto L5;
            long r1 = 300;
        L6:
            r0.setDuration(r1);
            r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            r0.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{SearchViewAnimationHelper.access$1700(this.this$0)}));
            return r0;
        L5:
            r1 = 250;
            goto L6
        }

        private Animator getDummyToolbarAnimator(boolean r5) {
            return getTranslationAnimator(r5, SearchViewAnimationHelper.access$400(this.this$0), getFromTranslationXEnd(SearchViewAnimationHelper.access$400(this.this$0)) - (SearchViewAnimationHelper.access$600(this.this$0).getPaddingEnd() - SearchViewAnimationHelper.access$400(this.this$0).getPaddingEnd()), getFromTranslationY());
        }

        private Animator getEditTextAnimator(boolean r2) {
            return getTranslationAnimatorForText(r2, SearchViewAnimationHelper.access$300(this.this$0));
        }

        private int getFromTranslationXEnd(View r3) {
            int r32 = ((ViewGroup.MarginLayoutParams) r3.getLayoutParams()).getMarginEnd();
            SearchViewAnimationHelper r0 = this.this$0;
            int r02 = SearchViewAnimationHelper.access$1600(r0, SearchViewAnimationHelper.access$600(r0));
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.access$600(this.this$0)) == false) goto L7;
            return r02 - r32;
        L7:
            return ((r02 + SearchViewAnimationHelper.access$600(this.this$0).getWidth()) + r32) - SearchViewAnimationHelper.access$100(this.this$0).getWidth();
        }

        private int getFromTranslationY() {
            int r0 = SearchViewAnimationHelper.access$1900(this.this$0).getTop() + (SearchViewAnimationHelper.access$1900(this.this$0).getHeight() / 2);
            SearchViewAnimationHelper r1 = this.this$0;
            return (SearchViewAnimationHelper.access$2000(r1, SearchViewAnimationHelper.access$600(r1)) + (SearchViewAnimationHelper.access$600(this.this$0).getHeight() / 2)) - r0;
        }

        private Animator getHeaderContainerAnimator(boolean r4) {
            return getTranslationAnimator(r4, SearchViewAnimationHelper.access$1300(this.this$0), getFromTranslationXEnd(SearchViewAnimationHelper.access$1300(this.this$0)), getFromTranslationY());
        }

        private Animator getRootViewAnimator(boolean r7) {
            Rect r0 = SearchViewAnimationHelper.access$1100(this.this$0).getInitialHideToClipBounds();
            Rect r1 = SearchViewAnimationHelper.access$1100(this.this$0).getInitialHideFromClipBounds();
            if (r0 != null) goto L6;
            r0 = ViewUtils.calculateRectFromBounds(SearchViewAnimationHelper.access$100(this.this$0));
        L6:
            if (r1 != null) goto L9;
            r1 = ViewUtils.calculateOffsetRectFromBounds(SearchViewAnimationHelper.access$200(this.this$0), SearchViewAnimationHelper.access$600(this.this$0));
        L9:
            Rect r2 = new Rect(r1);
            float r3 = SearchViewAnimationHelper.access$600(this.this$0).getCornerSize();
            float[] r4 = maxCornerRadii(SearchViewAnimationHelper.access$200(this.this$0).getCornerRadii(), SearchViewAnimationHelper.access$1100(this.this$0).getExpandedCornerRadii());
            ValueAnimator r02 = ValueAnimator.ofObject(new RectEvaluator(r2), new Object[]{r1, r0});
            r02.addUpdateListener(new SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda1(this, r3, r4, r2));
            if (r7 == false) goto L12;
            long r12 = 300;
        L13:
            r02.setDuration(r12);
            r02.setInterpolator(ReversableAnimatedValueInterpolator.of(r7, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            return r02;
        L12:
            r12 = 250;
            goto L13
        }

        private Animator getScrimAlphaAnimator(boolean r5) {
            if (r5 == false) goto L4;
            TimeInterpolator r0 = AnimationUtils.LINEAR_INTERPOLATOR;
        L5:
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            if (r5 == false) goto L8;
            long r2 = 300;
        L9:
            r1.setDuration(r2);
            if (r5 == false) goto L12;
            long r22 = SearchViewAnimationHelper.SHOW_SCRIM_ALPHA_DURATION_MS;
        L13:
            r1.setStartDelay(r22);
            r1.setInterpolator(ReversableAnimatedValueInterpolator.of(r5, r0));
            r1.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{SearchViewAnimationHelper.access$1200(this.this$0)}));
            return r1;
        L12:
            r22 = 0;
            goto L13
        L8:
            r2 = 250;
            goto L9
        L4:
            r0 = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
            goto L5
        }

        private Animator getSearchPrefixAnimator(boolean r2) {
            return getTranslationAnimatorForText(r2, SearchViewAnimationHelper.access$1500(this.this$0));
        }

        private AnimatorSet getTextAnimator(boolean r4) {
            AnimatorSet r0 = new AnimatorSet();
            addTextFadeAnimatorIfNeeded(r0);
            addEditTextClipAnimator(r0);
            if (r4 == false) goto L5;
            long r1 = 300;
        L6:
            r0.setDuration(r1);
            r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.LINEAR_INTERPOLATOR));
            return r0;
        L5:
            r1 = 250;
            goto L6
        }

        private Animator getTranslationAnimator(boolean r5, View r6, int r7, int r8) {
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{r7, 0.0f});
            r0.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{r6}));
            ValueAnimator r82 = ValueAnimator.ofFloat(new float[]{r8, 0.0f});
            r82.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{r6}));
            AnimatorSet r62 = new AnimatorSet();
            r62.playTogether(new Animator[]{r0, r82});
            if (r5 == false) goto L5;
            long r72 = 300;
        L6:
            r62.setDuration(r72);
            r62.setInterpolator(ReversableAnimatedValueInterpolator.of(r5, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            return r62;
        L5:
            r72 = 250;
            goto L6
        }

        private Animator getTranslationAnimatorForText(boolean r4, View r5) {
            TextView r0 = SearchViewAnimationHelper.access$600(this.this$0).getPlaceholderTextView();
            if (TextUtils.isEmpty(r0.getText()) == true) goto L5;
            if (r4 == true) goto L5;
        L6:
            int r1 = SearchViewAnimationHelper.access$1600(this.this$0, r0) - SearchViewAnimationHelper.access$1600(this.this$0, r5);
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.access$600(this.this$0)) == false) goto L10;
            r1 = r1 + (r0.getWidth() - r5.getWidth());
        L10:
            return getTranslationAnimator(r4, r5, r1, getFromTranslationY());
        L5:
            r0 = SearchViewAnimationHelper.access$600(this.this$0).getTextView();
            goto L6
        }

        private float[] lerpCornerRadii(float r18, float[] r19, float r20) {
            return new float[]{AnimationUtils.lerp(r18, r19[0], r20), AnimationUtils.lerp(r18, r19[1], r20), AnimationUtils.lerp(r18, r19[2], r20), AnimationUtils.lerp(r18, r19[3], r20), AnimationUtils.lerp(r18, r19[4], r20), AnimationUtils.lerp(r18, r19[5], r20), AnimationUtils.lerp(r18, r19[6], r20), AnimationUtils.lerp(r18, r19[7], r20)};
        }

        private float[] maxCornerRadii(float[] r17, float[] r18) {
            return new float[]{Math.max(r17[0], r18[0]), Math.max(r17[1], r18[1]), Math.max(r17[2], r18[2]), Math.max(r17[3], r18[3]), Math.max(r17[4], r18[4]), Math.max(r17[5], r18[5]), Math.max(r17[6], r18[6]), Math.max(r17[7], r18[7])};
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public AnimatorSet getExpandCollapseAnimatorSet(boolean r13) {
            AnimatorSet r0 = new AnimatorSet();
            if (SearchViewAnimationHelper.access$800(this.this$0) != null) goto L5;
            r0.playTogether(new Animator[]{getButtonsTranslationAnimator(r13)});
        L5:
            r0.playTogether(new Animator[]{getScrimAlphaAnimator(r13), getRootViewAnimator(r13), SearchViewAnimationHelper.access$900(this.this$0, r13), getContentAnimator(r13), getHeaderContainerAnimator(r13), getDummyToolbarAnimator(r13), getActionMenuViewsAlphaAnimator(r13), getEditTextAnimator(r13), getSearchPrefixAnimator(r13), getTextAnimator(r13)});
            return r0;
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public List<SpringAnimation> getExpandCollapseSpringAnimations(boolean r1) {
            return new ArrayList();
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationEnd(boolean r4) {
            SearchViewAnimationHelper r0 = this.this$0;
            if (r4 == false) goto L5;
            float r2 = 1.0f;
        L6:
            SearchViewAnimationHelper.access$1000(r0, r2);
            SearchViewAnimationHelper.access$300(this.this$0).setAlpha(1.0f);
            if (SearchViewAnimationHelper.access$600(this.this$0) == null) goto L9;
            SearchViewAnimationHelper.access$600(this.this$0).getTextView().setAlpha(1.0f);
        L9:
            SearchViewAnimationHelper.access$300(this.this$0).setClipBounds(null);
            SearchViewAnimationHelper.access$200(this.this$0).resetClipBoundsAndCornerRadii();
            if (r4 == true) goto L13;
            SearchViewAnimationHelper.access$1100(this.this$0).clearExpandedCornerRadii();
            return;
        L13:
            return;
        L5:
            r2 = 0.0f;
            goto L6
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationStart(boolean r1) {
            SearchViewAnimationHelper r0 = this.this$0;
            if (r1 == false) goto L5;
            float r12 = 0.0f;
        L6:
            SearchViewAnimationHelper.access$1000(r0, r12);
            return;
        L5:
            r12 = 1.0f;
            goto L6
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void setUpDummyToolbarIfNeeded() {
            Menu r0 = SearchViewAnimationHelper.access$400(this.this$0).getMenu();
            if (r0 == null) goto L5;
            r0.clear();
        L5:
            boolean r02 = SearchViewAnimationHelper.access$500(this.this$0);
            SearchViewAnimationHelper r1 = this.this$0;
            if (r02 == false) goto L9;
            SearchViewAnimationHelper.access$400(r1).inflateMenu(SearchViewAnimationHelper.access$600(this.this$0).getMenuResId());
            SearchViewAnimationHelper r03 = this.this$0;
            SearchViewAnimationHelper.access$700(r03, SearchViewAnimationHelper.access$400(r03));
            SearchViewAnimationHelper.access$400(this.this$0).setVisibility(0);
            return;
        L9:
            SearchViewAnimationHelper.access$400(r1).setVisibility(8);
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void startButtonsTranslationAnimation() {
            getButtonsTranslationAnimator(false).start();
        }
    }

    public static /* synthetic */ void $r8$lambda$4nHpflMzJGprd9zBkTBoB3RS_zM(SearchViewAnimationHelper r2) {
        r2.rootView.setTranslationY(r0.getHeight());
        AnimatorSet r0 = r2.getTranslateAnimatorSet(true);
        r0.addListener(new AnonymousClass3(r2, r0));
        r0.start();
        r2.activeTranslateAnimatorSet = r0;
    }

    public static /* synthetic */ void $r8$lambda$WWXE9jFF650BkIjOhTeSBIog5c8(DrawerArrowDrawable r0, ValueAnimator r1) {
        r0.setProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    /* renamed from: $r8$lambda$XH7Tt31rKFxUHNqrtP-bWKMltC0, reason: not valid java name */
    public static /* synthetic */ void m1894$r8$lambda$XH7Tt31rKFxUHNqrtPbWKMltC0(SearchViewAnimationHelper r4) {
        r4.getClass();
        AnimationCoordinator r0 = new AnimationCoordinator();
        r0.addAnimator(r4.getExpandCollapseAnimatorSet(true));
        Iterator<SpringAnimation> r2 = r4.getExpandCollapseSpringAnimations(true).iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r0.addDynamicAnimation(r2.next());
        goto L4
    L6:
        r0.addListener(new AnonymousClass1(r4, true, r0));
        r0.start();
        r4.activeCoordinator = r0;
    }

    /* renamed from: $r8$lambda$bW219ByJ4-f-K-2F4KYc0jC_Tsc, reason: not valid java name */
    public static /* synthetic */ void m1895$r8$lambda$bW219ByJ4fK2F4KYc0jC_Tsc(FadeThroughDrawable r0, ValueAnimator r1) {
        r0.setProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    public static /* synthetic */ void $r8$lambda$yS_MhX_A9yAxJfIfqpcbcBf6riQ(ImageButton r0, ValueAnimator r1) {
        r0.setAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    static {
        DEFAULT_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
    }

    public SearchViewAnimationHelper(Context r3, SearchView r4, boolean r5) {
        this.context = r3;
        this.searchView = r4;
        this.scrim = r4.scrim;
        this.backgroundView = r4.backgroundView;
        ClippableRoundedCornerLayout r0 = r4.rootView;
        this.rootView = r0;
        this.headerContainer = r4.headerContainer;
        this.toolbarContainer = r4.toolbarContainer;
        this.toolbar = r4.toolbar;
        this.dummyToolbar = r4.dummyToolbar;
        this.searchPrefix = r4.searchPrefix;
        this.dummyTextView = r4.dummyTextView;
        this.editText = r4.editText;
        this.clearButton = r4.clearButton;
        this.divider = r4.divider;
        this.contentContainer = r4.contentContainer;
        this.backHelper = new MaterialMainContainerBackHelper(r0);
        int r42 = R.attr.motionEasingStandardAccelerateInterpolator;
        TimeInterpolator r02 = DEFAULT_INTERPOLATOR;
        this.standardAccelerateInterpolator = MotionUtils.resolveThemeInterpolator(r3, r42, r02);
        this.standardDecelerateInterpolator = MotionUtils.resolveThemeInterpolator(r3, R.attr.motionEasingStandardDecelerateInterpolator, r02);
        this.durationShort1 = MotionUtils.resolveThemeDuration(r3, R.attr.motionDurationShort1, 100);
        this.durationShort2 = MotionUtils.resolveThemeDuration(r3, R.attr.motionDurationShort2, 100);
        if (r5 == false) goto L5;
        AnimationDelegate r32 = new ContainedAnimationDelegate(this);
    L6:
        this.animationDelegate = r32;
        return;
    L5:
        r32 = new DefaultAnimationDelegate(this, null);
        goto L6
    }

    public static /* synthetic */ SearchView access$100(SearchViewAnimationHelper r0) {
        return r0.searchView;
    }

    public static /* synthetic */ void access$1000(SearchViewAnimationHelper r0, float r1) {
        r0.setContentViewsAlpha(r1);
    }

    public static /* synthetic */ MaterialMainContainerBackHelper access$1100(SearchViewAnimationHelper r0) {
        return r0.backHelper;
    }

    public static /* synthetic */ View access$1200(SearchViewAnimationHelper r0) {
        return r0.scrim;
    }

    public static /* synthetic */ FrameLayout access$1300(SearchViewAnimationHelper r0) {
        return r0.headerContainer;
    }

    public static /* synthetic */ Toolbar access$1400(SearchViewAnimationHelper r0) {
        return r0.toolbar;
    }

    public static /* synthetic */ TextView access$1500(SearchViewAnimationHelper r0) {
        return r0.searchPrefix;
    }

    public static /* synthetic */ int access$1600(SearchViewAnimationHelper r0, View r1) {
        return r0.getViewLeftFromSearchViewParent(r1);
    }

    public static /* synthetic */ View access$1700(SearchViewAnimationHelper r0) {
        return r0.divider;
    }

    public static /* synthetic */ TouchObserverFrameLayout access$1800(SearchViewAnimationHelper r0) {
        return r0.contentContainer;
    }

    public static /* synthetic */ FrameLayout access$1900(SearchViewAnimationHelper r0) {
        return r0.toolbarContainer;
    }

    public static /* synthetic */ ClippableRoundedCornerLayout access$200(SearchViewAnimationHelper r0) {
        return r0.rootView;
    }

    public static /* synthetic */ int access$2000(SearchViewAnimationHelper r0, View r1) {
        return r0.getViewTopFromSearchViewParent(r1);
    }

    public static /* synthetic */ int access$2100(SearchViewAnimationHelper r0, View r1, View r2) {
        return r0.getTranslationXBetweenViews(r1, r2);
    }

    public static /* synthetic */ TextView access$2200(SearchViewAnimationHelper r0) {
        return r0.dummyTextView;
    }

    public static /* synthetic */ void access$2300(SearchViewAnimationHelper r0, float r1) {
        r0.setBackgroundAlpha(r1);
    }

    public static /* synthetic */ int access$2400(SearchViewAnimationHelper r0) {
        return r0.durationShort2;
    }

    public static /* synthetic */ int access$2500(SearchViewAnimationHelper r0) {
        return r0.durationShort1;
    }

    public static /* synthetic */ TimeInterpolator access$2600(SearchViewAnimationHelper r0) {
        return r0.standardDecelerateInterpolator;
    }

    public static /* synthetic */ TimeInterpolator access$2700(SearchViewAnimationHelper r0) {
        return r0.standardAccelerateInterpolator;
    }

    public static /* synthetic */ Context access$2900(SearchViewAnimationHelper r0) {
        return r0.context;
    }

    public static /* synthetic */ EditText access$300(SearchViewAnimationHelper r0) {
        return r0.editText;
    }

    public static /* synthetic */ Toolbar access$400(SearchViewAnimationHelper r0) {
        return r0.dummyToolbar;
    }

    public static /* synthetic */ boolean access$500(SearchViewAnimationHelper r0) {
        return r0.shouldInflateDummyToolbar();
    }

    public static /* synthetic */ SearchBar access$600(SearchViewAnimationHelper r0) {
        return r0.searchBar;
    }

    public static /* synthetic */ void access$700(SearchViewAnimationHelper r0, Toolbar r1) {
        r0.setMenuItemsNotClickable(r1);
    }

    public static /* synthetic */ AnimatorSet access$800(SearchViewAnimationHelper r0) {
        return r0.backProgressAnimatorSet;
    }

    public static /* synthetic */ Animator access$900(SearchViewAnimationHelper r0, boolean r1) {
        return r0.getClearButtonAnimator(r1);
    }

    private void addBackButtonAnimatorIfNeeded(AnimatorSet r2, ImageButton r3) {
        SearchBar r1 = this.searchBar;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getNavigationIcon() != null) goto L10;
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r12.addUpdateListener(new SearchViewAnimationHelper$$ExternalSyntheticLambda5(r3));
        r2.playTogether(new Animator[]{r12});
        return;
    }

    private void addBackButtonProgressAnimatorIfNeeded(AnimatorSet r4) {
        ImageButton r0 = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (r0 != null) goto L5;
        return;
    L5:
        Drawable r1 = DrawableCompat.unwrap(r0.getDrawable());
        if (this.searchView.isAnimatedNavigationIcon() == false) goto L9;
        addDrawerArrowDrawableAnimatorIfNeeded(r4, r1);
        addFadeThroughDrawableAnimatorIfNeeded(r4, r1);
        addBackButtonAnimatorIfNeeded(r4, r0);
        return;
    L9:
        setFullDrawableProgressIfNeeded(r1);
    }

    private void addDrawerArrowDrawableAnimatorIfNeeded(AnimatorSet r2, Drawable r3) {
        if ((r3 instanceof DrawerArrowDrawable) == false) goto L6;
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r1.addUpdateListener(new SearchViewAnimationHelper$$ExternalSyntheticLambda0((DrawerArrowDrawable) r3));
        r2.playTogether(new Animator[]{r1});
        return;
    }

    private void addFadeThroughDrawableAnimatorIfNeeded(AnimatorSet r2, Drawable r3) {
        if ((r3 instanceof FadeThroughDrawable) == false) goto L6;
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r1.addUpdateListener(new SearchViewAnimationHelper$$ExternalSyntheticLambda2((FadeThroughDrawable) r3));
        r2.playTogether(new Animator[]{r1});
        return;
    }

    private AnimatorSet getButtonsProgressAnimator(boolean r4) {
        AnimatorSet r0 = new AnimatorSet();
        addBackButtonProgressAnimatorIfNeeded(r0);
        if (r4 == false) goto L5;
        long r1 = 300;
    L6:
        r0.setDuration(r1);
        r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return r0;
    L5:
        r1 = 250;
        goto L6
    }

    private Animator getClearButtonAnimator(boolean r4) {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (r4 == false) goto L5;
        long r1 = SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS;
    L6:
        r0.setDuration(r1);
        if (r4 == false) goto L9;
        long r12 = 250;
    L10:
        r0.setStartDelay(r12);
        r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r4, AnimationUtils.LINEAR_INTERPOLATOR));
        r0.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{this.clearButton}));
        return r0;
    L9:
        r12 = 0;
        goto L10
    L5:
        r1 = HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS;
        goto L6
    }

    private AnimatorSet getExpandCollapseAnimatorSet(boolean r3) {
        AnimatorSet r0 = this.animationDelegate.getExpandCollapseAnimatorSet(r3);
        if (this.backProgressAnimatorSet != null) goto L5;
        r0.playTogether(new Animator[]{getButtonsProgressAnimator(r3)});
    L5:
        return r0;
    }

    private List<SpringAnimation> getExpandCollapseSpringAnimations(boolean r1) {
        return this.animationDelegate.getExpandCollapseSpringAnimations(r1);
    }

    private AnimatorSet getTranslateAnimatorSet(boolean r3) {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{getTranslationYAnimator()});
        addBackButtonProgressAnimatorIfNeeded(r0);
        r0.setInterpolator(ReversableAnimatedValueInterpolator.of(r3, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        if (r3 == false) goto L5;
        long r2 = SHOW_TRANSLATE_DURATION_MS;
    L6:
        r0.setDuration(r2);
        return r0;
    L5:
        r2 = 300;
        goto L6
    }

    private int getTranslationXBetweenViews(View r3, View r4) {
        if (r3 != null) goto L10;
        int r32 = ((ViewGroup.MarginLayoutParams) r4.getLayoutParams()).getMarginStart();
        int r42 = this.searchBar.getPaddingStart();
        int r0 = getViewLeftFromSearchViewParent(this.searchBar);
        if (ViewUtils.isLayoutRtl(this.searchBar) == false) goto L8;
        return (((r0 + this.searchBar.getWidth()) + r32) - r42) - this.searchView.getRight();
    L8:
        return (r0 - r32) + r42;
    L10:
        return getViewLeftFromSearchViewParent(r3) - getViewLeftFromSearchViewParent(r4);
    }

    private Animator getTranslationYAnimator() {
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{this.rootView.getHeight(), 0.0f});
        r1.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{this.rootView}));
        return r1;
    }

    private int getViewLeftFromSearchViewParent(View r3) {
        int r0 = r3.getLeft();
        ViewParent r32 = r3.getParent();
    L4:
        if ((r32 instanceof View) == false) goto L8;
        if (r32 == this.searchView.getParent()) goto L8;
        r0 = r0 + ((View) r32).getLeft();
        r32 = r32.getParent();
    L8:
        return r0;
    }

    private int getViewTopFromSearchViewParent(View r3) {
        int r0 = r3.getTop();
        ViewParent r32 = r3.getParent();
    L4:
        if ((r32 instanceof View) == false) goto L8;
        if (r32 == this.searchView.getParent()) goto L8;
        r0 = r0 + ((View) r32).getTop();
        r32 = r32.getParent();
    L8:
        return r0;
    }

    private boolean hasVisibleMenuItems(Menu r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r3.size()) goto L13;
        if (r3.getItem(r0).isVisible() == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    private void setActionMenuViewAlphaIfNeeded(float r2) {
        if (this.searchView.isMenuItemsAnimated() == false) goto L8;
        ActionMenuView r1 = ToolbarUtils.getActionMenuView(this.toolbar);
        if (r1 == null) goto L9;
        r1.setAlpha(r2);
        return;
    L9:
        return;
    }

    private void setBackgroundAlpha(float r2) {
        this.backgroundView.getBackground().mutate().setAlpha((int) (r2 * 255.0f));
    }

    private void setContentViewsAlpha(float r2) {
        this.clearButton.setAlpha(r2);
        this.divider.setAlpha(r2);
        this.contentContainer.setAlpha(r2);
        setActionMenuViewAlphaIfNeeded(r2);
    }

    private void setFullDrawableProgressIfNeeded(Drawable r2) {
        if ((r2 instanceof DrawerArrowDrawable) == false) goto L6;
        ((DrawerArrowDrawable) r2).setProgress(1.0f);
    L6:
        if ((r2 instanceof FadeThroughDrawable) == false) goto L9;
        ((FadeThroughDrawable) r2).setProgress(1.0f);
        return;
    }

    private void setMenuItemsNotClickable(Toolbar r3) {
        ActionMenuView r2 = ToolbarUtils.getActionMenuView(r3);
        if (r2 == null) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= r2.getChildCount()) goto L10;
        View r1 = r2.getChildAt(r0);
        r1.setClickable(false);
        r1.setFocusable(false);
        r1.setFocusableInTouchMode(false);
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    private boolean shouldInflateDummyToolbar() {
        if (this.searchBar.getMenuResId() != (-1)) goto L5;
        return false;
    L5:
        if (this.searchView.isMenuItemsAnimated() == true) goto L7;
        return false;
    L7:
        if (hasVisibleMenuItems(this.searchBar.getMenu()) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private AnimatorSet startHideAnimationCollapse() {
        if (this.searchView.isAdjustNothingSoftInputMode() == false) goto L5;
        this.editText.clearFocus();
    L5:
        AnimationCoordinator r0 = new AnimationCoordinator();
        AnimatorSet r2 = getExpandCollapseAnimatorSet(false);
        r0.addAnimator(r2);
        Iterator<SpringAnimation> r3 = getExpandCollapseSpringAnimations(false).iterator();
    L7:
        if (r3.hasNext() == false) goto L9;
        r0.addDynamicAnimation(r3.next());
        goto L7
    L9:
        r0.addListener(new AnonymousClass2(this, false, r0));
        r0.start();
        this.activeCoordinator = r0;
        return r2;
    }

    private AnimatorSet startHideAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode() == false) goto L5;
        this.editText.clearFocus();
    L5:
        AnimatorSet r0 = getTranslateAnimatorSet(false);
        r0.addListener(new AnonymousClass4(this, r0));
        r0.start();
        this.activeTranslateAnimatorSet = r0;
        return r0;
    }

    private void startShowAnimationExpand() {
        if (this.searchView.isAdjustNothingSoftInputMode() == false) goto L5;
        this.searchView.requestFocusAndShowKeyboardIfNeeded();
    L5:
        this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
        this.animationDelegate.setUpDummyToolbarIfNeeded();
        this.editText.setText(this.searchBar.getText());
        EditText r0 = this.editText;
        r0.setSelection(r0.getText().length());
        this.rootView.setVisibility(4);
        this.rootView.post(new SearchViewAnimationHelper$$ExternalSyntheticLambda1(this));
    }

    private void startShowAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode() == false) goto L5;
        SearchView r0 = this.searchView;
        Objects.requireNonNull(r0);
        r0.postDelayed(new SearchViewAnimationHelper$$ExternalSyntheticLambda3(r0), 150);
    L5:
        this.rootView.setVisibility(4);
        this.rootView.post(new SearchViewAnimationHelper$$ExternalSyntheticLambda4(this));
    }

    public void cancelBackProgress() {
        this.backHelper.cancelBackProgress(this.searchBar);
        AnimatorSet r0 = this.backProgressAnimatorSet;
        if (r0 == null) goto L5;
        r0.reverse();
    L5:
        this.backProgressAnimatorSet = null;
    }

    public void cancelPendingAnimations() {
        AnimationCoordinator r0 = this.activeCoordinator;
        if (r0 == null) goto L5;
        r0.clear();
        this.activeCoordinator = null;
    L5:
        AnimatorSet r02 = this.activeTranslateAnimatorSet;
        if (r02 == null) goto L9;
        r02.cancel();
        this.activeTranslateAnimatorSet = null;
        return;
    }

    public void finishBackProgress() {
        long r0 = hide().getTotalDuration();
        this.backHelper.finishBackProgress(r0, this.searchBar);
        if (this.backProgressAnimatorSet == null) goto L5;
        this.animationDelegate.startButtonsTranslationAnimation();
        this.backProgressAnimatorSet.resume();
    L5:
        this.backProgressAnimatorSet = null;
    }

    public MaterialMainContainerBackHelper getBackHelper() {
        return this.backHelper;
    }

    public AnimatorSet hide() {
        cancelPendingAnimations();
        if (this.searchBar == null) goto L7;
        return startHideAnimationCollapse();
    L7:
        return startHideAnimationTranslate();
    }

    public BackEventCompat onHandleBackInvoked() {
        return this.backHelper.onHandleBackInvoked();
    }

    public void setSearchBar(SearchBar r1) {
        this.searchBar = r1;
    }

    public void show() {
        cancelPendingAnimations();
        if (this.searchBar == null) goto L6;
        startShowAnimationExpand();
        return;
    L6:
        startShowAnimationTranslate();
    }

    public void startBackProgress(BackEventCompat r2) {
        this.backHelper.startBackProgress(r2, this.searchBar);
    }

    public void updateBackProgress(BackEventCompat r4) {
        if (r4.getProgress() <= 0.0f) goto L17;
        MaterialMainContainerBackHelper r0 = this.backHelper;
        SearchBar r1 = this.searchBar;
        r0.updateBackProgress(r4, r1, r1.getCornerSize());
        AnimatorSet r02 = this.backProgressAnimatorSet;
        if (r02 == null) goto L8;
        r02.setCurrentPlayTime((long) (r4.getProgress() * this.backProgressAnimatorSet.getDuration()));
        return;
    L8:
        if (this.searchView.isAdjustNothingSoftInputMode() == false) goto L11;
        this.editText.clearFocus();
    L11:
        if (this.searchView.isAnimatedNavigationIcon() == true) goto L13;
        return;
    L13:
        AnimatorSet r42 = getButtonsProgressAnimator(false);
        this.backProgressAnimatorSet = r42;
        r42.start();
        this.backProgressAnimatorSet.pause();
        return;
    }
}
