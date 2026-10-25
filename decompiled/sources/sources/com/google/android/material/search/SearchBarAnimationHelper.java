package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.animation.AnimatableView;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.ExpandCollapseAnimationHelper;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.search.SearchBar;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
class SearchBarAnimationHelper {
    private static final long COLLAPSE_DURATION_MS = 250;
    private static final long COLLAPSE_FADE_IN_CHILDREN_DURATION_MS = 100;
    private static final long EXPAND_DURATION_MS = 300;
    private static final long EXPAND_FADE_OUT_CHILDREN_DURATION_MS = 75;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_DURATION_MS = 250;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_IN_START_DELAY_MS = 500;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_OUT_START_DELAY_MS = 750;
    private static final long ON_LOAD_ANIM_SECONDARY_DURATION_MS = 250;
    private static final long ON_LOAD_ANIM_SECONDARY_START_DELAY_MS = 250;
    private final Set<AnimatorListenerAdapter> collapseAnimationListeners;
    private boolean collapsing;
    private Animator defaultCenterViewAnimator;
    private final Set<AnimatorListenerAdapter> expandAnimationListeners;
    private boolean expanding;
    private final Set<SearchBar.OnLoadAnimationCallback> onLoadAnimationCallbacks;
    private boolean onLoadAnimationFadeInEnabled;
    private Animator runningExpandOrCollapseAnimator;
    private Animator secondaryViewAnimator;

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchBarAnimationHelper this$0;

        public AnonymousClass1(SearchBarAnimationHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            SearchBarAnimationHelper.access$000(this.this$0, new SearchBarAnimationHelper$1$$ExternalSyntheticLambda0());
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchBarAnimationHelper this$0;
        final /* synthetic */ View val$centerView;
        final /* synthetic */ Animator val$secondaryViewAnimator;

        public AnonymousClass2(SearchBarAnimationHelper r1, View r2, Animator r3) {
            this.this$0 = r1;
            this.val$centerView = r2;
            this.val$secondaryViewAnimator = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$centerView.setVisibility(8);
            this.val$secondaryViewAnimator.start();
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchBarAnimationHelper this$0;

        public AnonymousClass3(SearchBarAnimationHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            SearchBarAnimationHelper.access$202(this.this$0, null);
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchBarAnimationHelper this$0;
        final /* synthetic */ SearchBar val$searchBar;

        public AnonymousClass4(SearchBarAnimationHelper r1, SearchBar r2) {
            this.this$0 = r1;
            this.val$searchBar = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            SearchBarAnimationHelper.access$102(this.this$0, false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.val$searchBar.setVisibility(4);
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchBarAnimationHelper this$0;

        public AnonymousClass5(SearchBarAnimationHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            SearchBarAnimationHelper.access$202(this.this$0, null);
        }
    }

    /* renamed from: com.google.android.material.search.SearchBarAnimationHelper$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchBarAnimationHelper this$0;
        final /* synthetic */ SearchBar val$searchBar;

        public AnonymousClass6(SearchBarAnimationHelper r1, SearchBar r2) {
            this.this$0 = r1;
            this.val$searchBar = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$searchBar.setVisibility(0);
            SearchBarAnimationHelper.access$302(this.this$0, false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.val$searchBar.stopOnLoadAnimation();
        }
    }

    public interface OnLoadAnimationInvocation {
        void invoke(SearchBar.OnLoadAnimationCallback r1);
    }

    /* renamed from: $r8$lambda$SDuWkQ1RZG4rrY5y-yQhNSj2cDI, reason: not valid java name */
    public static /* synthetic */ void m1889$r8$lambda$SDuWkQ1RZG4rrY5yyQhNSj2cDI(View r0, ValueAnimator r1) {
        r0.setAlpha(0.0f);
    }

    public static /* synthetic */ void $r8$lambda$f0fVVofiX_m3aDZHz_mlziQ_Bt0(SearchBarAnimationHelper r2, SearchBar r3, View r4, AppBarLayout r5, boolean r6) {
        r2.getClass();
        AnimatorSet r0 = new AnimatorSet();
        r0.playSequentially(new Animator[]{r2.getFadeOutChildrenAnimator(r3, r4), r2.getExpandAnimator(r3, r4, r5)});
        r0.addListener(new AnonymousClass3(r2));
        Iterator<AnimatorListenerAdapter> r32 = r2.expandAnimationListeners.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r0.addListener(r32.next());
        goto L4
    L6:
        if (r6 == false) goto L8;
        r0.setDuration(0);
    L8:
        r0.start();
        r2.runningExpandOrCollapseAnimator = r0;
    }

    public static /* synthetic */ void $r8$lambda$vZTEmJCpAmQlXswIkwShQsoE92A(MaterialShapeDrawable r1, View r2, ValueAnimator r3) {
        r1.setInterpolation(1.0f - r3.getAnimatedFraction());
        r2.setBackground(r1);
        r2.setAlpha(1.0f);
    }

    public SearchBarAnimationHelper() {
        this.onLoadAnimationCallbacks = new LinkedHashSet();
        this.expandAnimationListeners = new LinkedHashSet();
        this.collapseAnimationListeners = new LinkedHashSet();
        this.onLoadAnimationFadeInEnabled = true;
        this.runningExpandOrCollapseAnimator = null;
    }

    public static /* synthetic */ void access$000(SearchBarAnimationHelper r0, OnLoadAnimationInvocation r1) {
        r0.dispatchOnLoadAnimation(r1);
    }

    public static /* synthetic */ boolean access$102(SearchBarAnimationHelper r0, boolean r1) {
        r0.expanding = r1;
        return r1;
    }

    public static /* synthetic */ Animator access$202(SearchBarAnimationHelper r0, Animator r1) {
        r0.runningExpandOrCollapseAnimator = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$302(SearchBarAnimationHelper r0, boolean r1) {
        r0.collapsing = r1;
        return r1;
    }

    private void dispatchOnLoadAnimation(OnLoadAnimationInvocation r2) {
        Iterator<SearchBar.OnLoadAnimationCallback> r1 = this.onLoadAnimationCallbacks.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r2.invoke(r1.next());
        goto L4
    }

    private Animator getCollapseAnimator(SearchBar r3, View r4, AppBarLayout r5) {
        return getExpandCollapseAnimationHelper(r3, r4, r5).setDuration(250).addListener(new AnonymousClass6(this, r3)).getCollapseAnimator();
    }

    private Animator getDefaultCenterViewAnimator(View r11) {
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r1.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{r11}));
        TimeInterpolator r2 = AnimationUtils.LINEAR_INTERPOLATOR;
        r1.setInterpolator(r2);
        long r4 = 0;
        if (this.onLoadAnimationFadeInEnabled == false) goto L5;
        long r8 = 250;
    L6:
        r1.setDuration(r8);
        if (this.onLoadAnimationFadeInEnabled == false) goto L9;
        r4 = ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_IN_START_DELAY_MS;
    L9:
        r1.setStartDelay(r4);
        ValueAnimator r10 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r10.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{r11}));
        r10.setInterpolator(r2);
        r10.setDuration(250);
        r10.setStartDelay(ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_OUT_START_DELAY_MS);
        AnimatorSet r112 = new AnimatorSet();
        r112.playSequentially(new Animator[]{r1, r10});
        return r112;
    L5:
        r8 = 0;
        goto L6
    }

    private List<View> getEndAnchoredViews(View r5) {
        boolean r4 = ViewUtils.isLayoutRtl(r5);
        ArrayList r0 = new ArrayList();
        if ((r5 instanceof ViewGroup) == false) goto L16;
        ViewGroup r52 = (ViewGroup) r5;
        int r1 = 0;
    L6:
        if (r1 >= r52.getChildCount()) goto L16;
        View r2 = r52.getChildAt(r1);
        if (r4 == false) goto L10;
    L11:
        if (r4 == false) goto L15;
        if ((r2 instanceof ActionMenuView) == true) goto L15;
    L14:
        r0.add(r2);
    L15:
        r1 = r1 + 1;
        goto L6
    L10:
        if ((r2 instanceof ActionMenuView) == true) goto L14;
    L16:
        return r0;
    }

    private Animator getExpandAnimator(SearchBar r3, View r4, AppBarLayout r5) {
        return getExpandCollapseAnimationHelper(r3, r4, r5).setDuration(EXPAND_DURATION_MS).addListener(new AnonymousClass4(this, r3)).getExpandAnimator();
    }

    private ExpandCollapseAnimationHelper getExpandCollapseAnimationHelper(SearchBar r2, View r3, AppBarLayout r4) {
        ExpandCollapseAnimationHelper r22 = new ExpandCollapseAnimationHelper(r2, r3).setAdditionalUpdateListener(getExpandedViewBackgroundUpdateListener(r2, r3));
        if (r4 == null) goto L5;
        int r42 = r4.getTop();
    L7:
        return r22.setCollapsedViewOffsetY(r42).addEndAnchoredViews(getEndAnchoredViews(r3));
    L5:
        r42 = 0;
        goto L7
    }

    private ValueAnimator.AnimatorUpdateListener getExpandedViewBackgroundUpdateListener(SearchBar r2, View r3) {
        MaterialShapeDrawable r1 = MaterialShapeDrawable.createWithElevationOverlay(r3.getContext());
        r1.setCornerSize(r2.getCornerSize());
        r1.setElevation(r2.getElevation());
        return new SearchBarAnimationHelper$$ExternalSyntheticLambda4(r1, r3);
    }

    private List<View> getFadeChildren(SearchBar r2) {
        List<View> r1 = ViewUtils.getChildren(r2);
        if (r2.getCenterView() == null) goto L5;
        r1.remove(r2.getCenterView());
    L5:
        return r1;
    }

    private Animator getFadeInChildrenAnimator(SearchBar r3) {
        List<View> r2 = getFadeChildren(r3);
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r32.addUpdateListener(MultiViewUpdateListener.alphaListener(r2));
        r32.setDuration(COLLAPSE_FADE_IN_CHILDREN_DURATION_MS);
        r32.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return r32;
    }

    private Animator getFadeOutChildrenAnimator(SearchBar r3, View r4) {
        List<View> r2 = getFadeChildren(r3);
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r32.addUpdateListener(MultiViewUpdateListener.alphaListener(r2));
        r32.addUpdateListener(new SearchBarAnimationHelper$$ExternalSyntheticLambda0(r4));
        r32.setDuration(75);
        r32.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return r32;
    }

    private Animator getSecondaryActionMenuItemAnimator(View r3) {
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r2.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{r3}));
        r2.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        r2.setDuration(250);
        return r2;
    }

    private Animator getSecondaryViewAnimator(TextView r4, View r5) {
        AnimatorSet r0 = new AnimatorSet();
        r0.setStartDelay(250);
        r0.play(getTextViewAnimator(r4));
        if (r5 == null) goto L5;
        r0.play(getSecondaryActionMenuItemAnimator(r5));
    L5:
        return r0;
    }

    private Animator getTextViewAnimator(TextView r3) {
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r2.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{r3}));
        r2.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        r2.setDuration(250);
        return r2;
    }

    public void addCollapseAnimationListener(AnimatorListenerAdapter r1) {
        this.collapseAnimationListeners.add(r1);
    }

    public void addExpandAnimationListener(AnimatorListenerAdapter r1) {
        this.expandAnimationListeners.add(r1);
    }

    public void addOnLoadAnimationCallback(SearchBar.OnLoadAnimationCallback r1) {
        this.onLoadAnimationCallbacks.add(r1);
    }

    public boolean isCollapsing() {
        return this.collapsing;
    }

    public boolean isExpanding() {
        return this.expanding;
    }

    public boolean isOnLoadAnimationFadeInEnabled() {
        return this.onLoadAnimationFadeInEnabled;
    }

    public boolean removeCollapseAnimationListener(AnimatorListenerAdapter r1) {
        return this.collapseAnimationListeners.remove(r1);
    }

    public boolean removeExpandAnimationListener(AnimatorListenerAdapter r1) {
        return this.expandAnimationListeners.remove(r1);
    }

    public boolean removeOnLoadAnimationCallback(SearchBar.OnLoadAnimationCallback r1) {
        return this.onLoadAnimationCallbacks.remove(r1);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean r1) {
        this.onLoadAnimationFadeInEnabled = r1;
    }

    public void startCollapseAnimation(SearchBar r2, View r3, AppBarLayout r4, boolean r5) {
        if (isExpanding() == false) goto L7;
        Animator r0 = this.runningExpandOrCollapseAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        this.collapsing = true;
        AnimatorSet r02 = new AnimatorSet();
        r02.playSequentially(new Animator[]{getCollapseAnimator(r2, r3, r4), getFadeInChildrenAnimator(r2)});
        r02.addListener(new AnonymousClass5(this));
        Iterator<AnimatorListenerAdapter> r22 = this.collapseAnimationListeners.iterator();
    L9:
        if (r22.hasNext() == false) goto L11;
        r02.addListener(r22.next());
        goto L9
    L11:
        if (r5 == false) goto L13;
        r02.setDuration(0);
    L13:
        r02.start();
        this.runningExpandOrCollapseAnimator = r02;
    }

    public void startExpandAnimation(SearchBar r8, View r9, AppBarLayout r10, boolean r11) {
        if (isCollapsing() == false) goto L7;
        Animator r0 = this.runningExpandOrCollapseAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        this.expanding = true;
        r9.setVisibility(4);
        r9.post(new SearchBarAnimationHelper$$ExternalSyntheticLambda3(this, r8, r9, r10, r11));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startOnLoadAnimation(SearchBar r5) {
        dispatchOnLoadAnimation(new SearchBarAnimationHelper$$ExternalSyntheticLambda1());
        TextView r0 = r5.getTextView();
        View r1 = r5.getCenterView();
        View r52 = ToolbarUtils.getSecondaryActionMenuItemView(r5);
        Animator r2 = getSecondaryViewAnimator(r0, r52);
        r2.addListener(new AnonymousClass1(this));
        this.secondaryViewAnimator = r2;
        r0.setAlpha(0.0f);
        if (r52 == null) goto L6;
        r52.setAlpha(0.0f);
    L6:
        if ((r1 instanceof AnimatableView) == false) goto L9;
        ((AnimatableView) r1).startAnimation(new SearchBarAnimationHelper$$ExternalSyntheticLambda2(r2));
        return;
    L9:
        if (r1 == 0) goto L12;
        r1.setAlpha(0.0f);
        r1.setVisibility(0);
        Animator r53 = getDefaultCenterViewAnimator(r1);
        this.defaultCenterViewAnimator = r53;
        r53.addListener(new AnonymousClass2(this, r1, r2));
        r53.start();
        return;
    L12:
        r2.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void stopOnLoadAnimation(SearchBar r2) {
        Animator r0 = this.secondaryViewAnimator;
        if (r0 == null) goto L5;
        r0.end();
    L5:
        Animator r1 = this.defaultCenterViewAnimator;
        if (r1 == null) goto L8;
        r1.end();
    L8:
        View r12 = r2.getCenterView();
        if ((r12 instanceof AnimatableView) == false) goto L11;
        ((AnimatableView) r12).stopAnimation();
    L11:
        if (r12 == 0) goto L14;
        r12.setAlpha(0.0f);
        return;
    }
}
