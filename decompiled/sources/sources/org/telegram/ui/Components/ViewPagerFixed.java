package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.AppUtils;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes3.dex */
public class ViewPagerFixed extends FrameLayout {
    private static final Interpolator interpolator = null;
    public Adapter adapter;
    private float additionalOffset;
    private boolean allowDisallowInterceptTouch;
    private boolean animatingForward;
    private boolean backAnimation;
    private float backProgress;
    public int currentPosition;
    public float currentProgress;
    private ValueAnimator manualScrolling;
    private int maximumVelocity;
    private boolean maybeStartTracking;
    protected int nextPosition;
    AnimationNotificationsLocker notificationsLocker;
    private Rect rect;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    private AnimatorSet tabsAnimation;
    private boolean tabsAnimationInProgress;
    TabsView tabsView;
    private final float touchSlop;
    ValueAnimator.AnimatorUpdateListener updateTabProgress;
    private VelocityTracker velocityTracker;
    protected View[] viewPages;
    private int[] viewTypes;
    protected SparseArray<View> viewsByType;

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass1(ViewPagerFixed r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r5) {
            if (ViewPagerFixed.m13537$$Nest$fgettabsAnimationInProgress(this.this$0) == false) goto L7;
            float r52 = Math.abs(this.this$0.viewPages[0].getTranslationX()) / this.this$0.viewPages[0].getMeasuredWidth();
            ViewPagerFixed r1 = this.this$0;
            float r2 = 1.0f - r52;
            r1.currentProgress = r2;
            TabsView r53 = r1.tabsView;
            if (r53 == null) goto L7;
            r53.selectTab(r1.nextPosition, r1.currentPosition, r2);
        L7:
            this.this$0.onTabAnimationUpdate(false);
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass2(ViewPagerFixed r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            ViewPagerFixed r52 = this.this$0;
            if (r52.viewPages[1] == null) goto L5;
            r52.swapViews();
            ViewPagerFixed r53 = this.this$0;
            r53.viewsByType.put(ViewPagerFixed.m13538$$Nest$fgetviewTypes(r53)[1], this.this$0.viewPages[1]);
            ViewPagerFixed r54 = this.this$0;
            r54.removeView(r54.viewPages[1]);
            ViewPagerFixed r55 = this.this$0;
            r55.setTranslationX(r55.viewPages[0], 0.0f);
            this.this$0.viewPages[1] = null;
        L5:
            ViewPagerFixed.m13540$$Nest$fputmanualScrolling(this.this$0, null);
            this.this$0.onTabAnimationUpdate(true);
            TabsView r56 = this.this$0.tabsView;
            if (r56 == null) goto L8;
            r56.listView.invalidate();
            this.this$0.tabsView.listView.invalidateViews();
            this.this$0.tabsView.invalidate();
        L8:
            this.this$0.onScrollEnd();
            this.this$0.notificationsLocker.unlock();
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends TabsView {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass3(ViewPagerFixed r1, Context r2, boolean r3, int r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView
        public void selectTab(int r2, int r3, float r4) {
            super.selectTab(r2, r3, r4);
            ViewPagerFixed r1 = this.this$0;
            if (r4 > 0.5f) goto L5;
            int r42 = r2;
        L6:
            if (r2 >= r3) goto L8;
            boolean r22 = true;
        L9:
            r1.onTabPageSelected(r42, r22);
            return;
        L8:
            r22 = false;
            goto L9
        L5:
            r42 = r3;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 implements TabsView.TabsViewDelegate {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass4(ViewPagerFixed r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public void applyReorder(ArrayList<Integer> r1) {
            Adapter r0 = this.this$0.adapter;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.applyReorder(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public boolean canPerformActions() {
            if (ViewPagerFixed.m13537$$Nest$fgettabsAnimationInProgress(this.this$0) == false) goto L5;
            return false;
        L5:
            if (ViewPagerFixed.m13536$$Nest$fgetstartedTracking(this.this$0) == true) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public boolean canReorder(int r1) {
            Adapter r0 = this.this$0.adapter;
            if (r0 != null) goto L7;
            return false;
        L7:
            return r0.canReorder(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public void invalidateBlur() {
            this.this$0.invalidateBlur();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public boolean needsTab(int r1) {
            Adapter r0 = this.this$0.adapter;
            if (r0 != null) goto L7;
            return true;
        L7:
            return r0.needsTab(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public void onPageScrolled(float r6) {
            ViewPagerFixed r2 = this.this$0;
            if (r6 != 1.0f) goto L10;
            if (r2.viewPages[1] == null) goto L7;
            r2.swapViews();
            ViewPagerFixed r62 = this.this$0;
            r62.viewsByType.put(ViewPagerFixed.m13538$$Nest$fgetviewTypes(r62)[1], this.this$0.viewPages[1]);
            ViewPagerFixed r63 = this.this$0;
            r63.removeView(r63.viewPages[1]);
            ViewPagerFixed r64 = this.this$0;
            r64.setTranslationX(r64.viewPages[0], 0.0f);
            this.this$0.viewPages[1] = null;
        L7:
            ViewPagerFixed r5 = this.this$0;
            r5.onTabScrollEnd(r5.currentPosition);
            return;
        L10:
            if (r2.viewPages[1] != null) goto L12;
            return;
        L12:
            boolean r1 = ViewPagerFixed.m13534$$Nest$fgetanimatingForward(r2);
            ViewPagerFixed r22 = this.this$0;
            if (r1 == false) goto L15;
            r22.setTranslationX(r22.viewPages[1], r1[0].getMeasuredWidth() * (1.0f - r6));
            ViewPagerFixed r0 = this.this$0;
            r0.setTranslationX(r0.viewPages[0], (-r1.getMeasuredWidth()) * r6);
        L16:
            this.this$0.onTabAnimationUpdate(false);
            return;
        L15:
            r22.setTranslationX(r22.viewPages[1], (-r1[0].getMeasuredWidth()) * (1.0f - r6));
            ViewPagerFixed r02 = this.this$0;
            r02.setTranslationX(r02.viewPages[0], r1.getMeasuredWidth() * r6);
            goto L16
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public void onPageSelected(int r3, boolean r4) {
            ViewPagerFixed.m13539$$Nest$fputanimatingForward(this.this$0, r4);
            ViewPagerFixed r0 = this.this$0;
            r0.nextPosition = r3;
            ViewPagerFixed.m13544$$Nest$mupdateViewForIndex(r0, 1);
            this.this$0.onTabPageSelected(r3, r4);
            int r02 = 0;
            View r32 = this.this$0.viewPages[0];
            if (r32 == null) goto L5;
            r02 = r32.getMeasuredWidth();
        L5:
            ViewPagerFixed r2 = this.this$0;
            View r33 = r2.viewPages[1];
            if (r33 == null) goto L12;
            if (r4 == false) goto L10;
            r2.setTranslationX(r33, r02);
            return;
        L10:
            r2.setTranslationX(r33, -r02);
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.TabsView.TabsViewDelegate
        public void onSamePageSelected() {
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ ViewPagerFixed this$0;
        final /* synthetic */ View val$view;

        public AnonymousClass5(ViewPagerFixed r1, View r2) {
            this.this$0 = r1;
            this.val$view = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            this.this$0.setTranslationX(this.val$view, ((Float) r2.getAnimatedValue()).floatValue());
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        boolean canceled;
        final /* synthetic */ ViewPagerFixed this$0;
        final /* synthetic */ float val$toTx;
        final /* synthetic */ View val$view;

        public AnonymousClass6(ViewPagerFixed r1, View r2, float r3) {
            this.this$0 = r1;
            this.val$view = r2;
            this.val$toTx = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            super.onAnimationCancel(r1);
            this.canceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.canceled == true) goto L6;
            this.this$0.setTranslationX(this.val$view, this.val$toTx);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass7(ViewPagerFixed r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            ViewPagerFixed.m13542$$Nest$fputtabsAnimation(this.this$0, null);
            ViewPagerFixed r52 = this.this$0;
            if (r52.nextPosition >= 0) goto L5;
            r52.onBack();
        L5:
            ViewPagerFixed r53 = this.this$0;
            if (r53.viewPages[1] != null) goto L8;
        L11:
            ViewPagerFixed.m13543$$Nest$fputtabsAnimationInProgress(this.this$0, false);
            ViewPagerFixed.m13541$$Nest$fputmaybeStartTracking(this.this$0, false);
            TabsView r54 = this.this$0.tabsView;
            if (r54 == null) goto L14;
            r54.setEnabled(true);
        L14:
            this.this$0.onTabAnimationUpdate(false);
            this.this$0.onScrollEnd();
            this.this$0.notificationsLocker.unlock();
            return;
        L8:
            if (ViewPagerFixed.m13535$$Nest$fgetbackAnimation(r53) == true) goto L10;
            this.this$0.swapViews();
        L10:
            ViewPagerFixed r55 = this.this$0;
            r55.viewsByType.put(ViewPagerFixed.m13538$$Nest$fgetviewTypes(r55)[1], this.this$0.viewPages[1]);
            ViewPagerFixed r56 = this.this$0;
            r56.removeView(r56.viewPages[1]);
            this.this$0.viewPages[1].setVisibility(8);
            this.this$0.viewPages[1] = null;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass8(ViewPagerFixed r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            ViewPagerFixed.m13542$$Nest$fputtabsAnimation(this.this$0, null);
            ViewPagerFixed r42 = this.this$0;
            View r1 = r42.viewPages[1];
            if (r1 == null) goto L5;
            r42.removeView(r1);
            this.this$0.viewPages[1] = null;
        L5:
            ViewPagerFixed.m13543$$Nest$fputtabsAnimationInProgress(this.this$0, false);
            TabsView r43 = this.this$0.tabsView;
            if (r43 == null) goto L9;
            r43.setEnabled(true);
            TabsView.m13574$$Nest$fputanimatingIndicator(this.this$0.tabsView, false);
            TabsView.m13576$$Nest$fputindicatorProgress2(this.this$0.tabsView, 1.0f);
            this.this$0.tabsView.listView.invalidateViews();
            this.this$0.tabsView.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ViewPagerFixed$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPagerFixed this$0;

        public AnonymousClass9(ViewPagerFixed r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            ViewPagerFixed.m13542$$Nest$fputtabsAnimation(this.this$0, null);
            ViewPagerFixed r52 = this.this$0;
            if (r52.nextPosition >= 0) goto L5;
            r52.onBack();
        L5:
            ViewPagerFixed r53 = this.this$0;
            if (r53.viewPages[1] != null) goto L8;
        L11:
            ViewPagerFixed.m13543$$Nest$fputtabsAnimationInProgress(this.this$0, false);
            ViewPagerFixed.m13541$$Nest$fputmaybeStartTracking(this.this$0, false);
            TabsView r54 = this.this$0.tabsView;
            if (r54 == null) goto L14;
            r54.setEnabled(true);
        L14:
            this.this$0.onTabAnimationUpdate(false);
            this.this$0.onScrollEnd();
            this.this$0.notificationsLocker.unlock();
            return;
        L8:
            if (ViewPagerFixed.m13535$$Nest$fgetbackAnimation(r53) == true) goto L10;
            this.this$0.swapViews();
        L10:
            ViewPagerFixed r55 = this.this$0;
            r55.viewsByType.put(ViewPagerFixed.m13538$$Nest$fgetviewTypes(r55)[1], this.this$0.viewPages[1]);
            ViewPagerFixed r56 = this.this$0;
            r56.removeView(r56.viewPages[1]);
            this.this$0.viewPages[1].setVisibility(8);
            this.this$0.viewPages[1] = null;
            goto L11
        }
    }

    public static abstract class Adapter {
        public Adapter() {
        }

        public void applyReorder(ArrayList<Integer> r1) {
        }

        public abstract void bindView(View r1, int r2, int r3);

        public boolean canReorder(int r1) {
            return false;
        }

        public boolean canScrollTo(int r1) {
            return true;
        }

        public abstract View createView(int r1);

        public abstract int getItemCount();

        public int getItemId(int r1) {
            return r1;
        }

        public CharSequence getItemTitle(int r1) {
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public int getItemViewType(int r1) {
            return 0;
        }

        public boolean hasStableId() {
            return false;
        }

        public boolean needsTab(int r1) {
            return true;
        }
    }

    public static class TabsView extends FrameLayout {
        private int activeTextColorKey;
        public ListAdapter adapter;
        private int additionalTabWidth;
        private int allTabsWidth;
        private boolean animatingIndicator;
        private float animatingIndicatorProgress;
        private Runnable animationRunnable;
        private float animationTime;
        private int backgroundColorKey;
        BlurredBackgroundDrawable blurredBackgroundDrawable;
        private final Paint counterPaint;
        private int currentPosition;
        private TabsViewDelegate delegate;
        private final Paint deletePaint;
        private float editingAnimationProgress;
        private boolean editingForwardAnimation;
        private float editingStartAnimationProgress;
        private float hideProgress;
        private SparseIntArray idToPosition;
        private boolean ignoreLayout;
        private float indicatorProgress2;
        private CubicBezierInterpolator interpolator;
        private boolean invalidated;
        private boolean isEditing;
        private boolean isInHiddenMode;
        private DefaultItemAnimator itemAnimator;
        private ItemTouchHelper itemTouchHelper;
        private long lastAnimationTime;
        float lastDrawnIndicatorW;
        float lastDrawnIndicatorX;
        public LinearLayoutManager layoutManager;
        public RecyclerListView listView;
        private int manualScrollingToId;
        private int manualScrollingToPosition;
        private Utilities.Callback2Return<Integer, View, Boolean> onTabLongClick;
        private boolean orderChanged;
        private float overrideFromW;
        private float overrideFromX;
        private SparseIntArray positionToId;
        private SparseIntArray positionToWidth;
        private SparseIntArray positionToX;
        private Utilities.Callback2Return<Integer, Integer, Boolean> preTabClick;
        private int prevLayoutWidth;
        private int previousId;
        private int previousPosition;
        private boolean reordering;
        private Theme.ResourcesProvider resourcesProvider;
        private int scrollingToChild;
        private int selectedTabId;
        private int selectorColorKey;
        private GradientDrawable selectorDrawable;
        private final Paint selectorPaint;
        private int selectorType;
        private int tabLineColorKey;
        public int tabMarginDp;
        private final ArrayList<Tab> tabs;
        ValueAnimator tabsAnimator;
        private final TextPaint textCounterPaint;
        private final TextPaint textPaint;
        private int unactiveTextColorKey;

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ TabsView this$0;

            public AnonymousClass1(TabsView r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TabsView.m13549$$Nest$fgetanimatingIndicator(this.this$0) == false) goto L20;
                long r0 = SystemClock.elapsedRealtime() - TabsView.m13562$$Nest$fgetlastAnimationTime(this.this$0);
                if (r0 <= 17) goto L8;
                r0 = 17;
            L8:
                TabsView r2 = this.this$0;
                TabsView.m13575$$Nest$fputanimationTime(r2, TabsView.m13552$$Nest$fgetanimationTime(r2) + (r0 / 200.0f));
                TabsView r02 = this.this$0;
                r02.setAnimationIdicatorProgress(TabsView.m13559$$Nest$fgetinterpolator(r02).getInterpolation(TabsView.m13552$$Nest$fgetanimationTime(this.this$0)));
                if (TabsView.m13552$$Nest$fgetanimationTime(this.this$0) <= 1.0f) goto L11;
                TabsView.m13575$$Nest$fputanimationTime(this.this$0, 1.0f);
            L11:
                float r03 = TabsView.m13552$$Nest$fgetanimationTime(this.this$0);
                TabsView r22 = this.this$0;
                if (r03 >= 1.0f) goto L15;
                AndroidUtilities.runOnUIThread(TabsView.m13551$$Nest$fgetanimationRunnable(r22));
                return;
            L15:
                TabsView.m13574$$Nest$fputanimatingIndicator(r22, false);
                this.this$0.setEnabled(true);
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0) == null) goto L19;
                TabsView.m13555$$Nest$fgetdelegate(this.this$0).onPageScrolled(1.0f);
                return;
            L19:
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$2, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass2 extends RecyclerListView {
            final /* synthetic */ TabsView this$0;

            public AnonymousClass2(TabsView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup
            public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
                super.addView(r1, r2, r3);
                if (TabsView.m13561$$Nest$fgetisInHiddenMode(this.this$0) == false) goto L6;
                r1.setScaleX(0.3f);
                r1.setScaleY(0.3f);
                r1.setAlpha(0.0f);
                return;
            L6:
                r1.setScaleX(1.0f);
                r1.setScaleY(1.0f);
                r1.setAlpha(1.0f);
            }

            @Override // org.telegram.ui.Components.RecyclerListView
            public boolean canHighlightChildAt(View r4, float r5, float r6) {
                if (TabsView.m13560$$Nest$fgetisEditing(this.this$0) == false) goto L11;
                TabView r0 = (TabView) r4;
                float r1 = AndroidUtilities.dp(6.0f);
                if ((TabView.m13579$$Nest$fgetrect(r0).left - r1) >= r5) goto L11;
                if ((TabView.m13579$$Nest$fgetrect(r0).right + r1) <= r5) goto L11;
                return false;
            L11:
                return super.canHighlightChildAt(r4, r5, r6);
            }

            @Override // android.view.View
            public void setAlpha(float r1) {
                super.setAlpha(r1);
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$3, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass3 extends LinearLayoutManager {
            final /* synthetic */ TabsView this$0;

            /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$3$1, reason: invalid class name */
            public class AnonymousClass1 extends LinearSmoothScroller {
                final /* synthetic */ AnonymousClass3 this$1;

                public AnonymousClass1(AnonymousClass3 r1, Context r2) {
                    this.this$1 = r1;
                    super(r2);
                }

                @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
                public void onTargetFound(View r5, RecyclerView.State r6, RecyclerView.SmoothScroller.Action r7) {
                    int r62 = calculateDxToMakeVisible(r5, getHorizontalSnapPreference());
                    if (r62 <= 0) goto L5;
                L14:
                    r62 = r62 + AndroidUtilities.dp(60.0f);
                L15:
                    int r52 = calculateDyToMakeVisible(r5, getVerticalSnapPreference());
                    int r0 = Math.max(180, calculateTimeForDeceleration((int) Math.sqrt((r62 * r62) + (r52 * r52))));
                    if (r0 <= 0) goto L19;
                    r7.update(-r62, -r52, r0, this.mDecelerateInterpolator);
                    return;
                L19:
                    return;
                L5:
                    if (r62 == 0) goto L7;
                L9:
                    if (r62 < 0) goto L13;
                    if (r62 != 0) goto L15;
                    if ((r5.getRight() + AndroidUtilities.dp(21.0f)) <= this.this$1.this$0.getMeasuredWidth()) goto L15;
                L13:
                    r62 = r62 - AndroidUtilities.dp(60.0f);
                    goto L15
                L7:
                    if ((r5.getLeft() - AndroidUtilities.dp(21.0f)) >= 0) goto L9;
                    goto L9
                }
            }

            public AnonymousClass3(TabsView r1, Context r2, int r3, boolean r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler r1, RecyclerView.State r2, AccessibilityNodeInfoCompat r3) {
                super.onInitializeAccessibilityNodeInfo(r1, r2, r3);
                if (TabsView.m13561$$Nest$fgetisInHiddenMode(this.this$0) == false) goto L6;
                r3.setVisibleToUser(false);
                return;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
                AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
                r22.setTargetPosition(r3);
                startSmoothScroll(r22);
            }
        }

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$4, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass4 extends RecyclerView.OnScrollListener {
            final /* synthetic */ TabsView this$0;

            public AnonymousClass4(TabsView r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$5, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass5 extends AnimatorListenerAdapter {
            final /* synthetic */ TabsView this$0;

            public AnonymousClass5(TabsView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                TabsView.m13574$$Nest$fputanimatingIndicator(this.this$0, false);
                this.this$0.setEnabled(true);
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0) == null) goto L5;
                TabsView.m13555$$Nest$fgetdelegate(this.this$0).onPageScrolled(1.0f);
            L5:
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$6, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass6 extends ItemTouchHelper.Callback {
            final /* synthetic */ TabsView this$0;

            public AnonymousClass6(TabsView r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0).canReorder(r2.getAdapterPosition()) == false) goto L7;
                return ItemTouchHelper.Callback.makeMovementFlags(12, 0);
            L7:
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
                super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
                this.this$0.invalidate();
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean onMove(RecyclerView r3, RecyclerView.ViewHolder r4, RecyclerView.ViewHolder r5) {
                int r32 = r4.getAdapterPosition();
                int r42 = r5.getAdapterPosition();
                int r0 = 0;
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0).canReorder(r32) == true) goto L5;
            L12:
                return false;
            L5:
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0).canReorder(r42) == false) goto L12;
                Utilities.swapItems(TabsView.m13570$$Nest$fgettabs(this.this$0), r32, r42);
                this.this$0.adapter.notifyItemMoved(r32, r42);
                ArrayList<Integer> r33 = new ArrayList();
                ArrayList r43 = TabsView.m13570$$Nest$fgettabs(this.this$0);
                int r52 = r43.size();
            L8:
                if (r0 >= r52) goto L10;
                Object r1 = r43.get(r0);
                r0 = r0 + 1;
                r33.add(Integer.valueOf(((Tab) r1).id));
                goto L8
            L10:
                TabsView.m13555$$Nest$fgetdelegate(this.this$0).applyReorder(r33);
                return true;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
            }
        }

        /* renamed from: org.telegram.ui.Components.ViewPagerFixed$TabsView$7, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass7 extends DefaultItemAnimator {
            final /* synthetic */ TabsView this$0;

            public AnonymousClass7(TabsView r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                super.onMoveAnimationUpdate(r1);
                this.this$0.invalidate();
            }
        }

        /* loaded from: classes7.dex */
        public class ListAdapter extends RecyclerListView.SelectionAdapter {
            private Context mContext;
            final /* synthetic */ TabsView this$0;

            public ListAdapter(TabsView r1, Context r2) {
                this.this$0 = r1;
                this.mContext = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return TabsView.m13570$$Nest$fgettabs(this.this$0).size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public long getItemId(int r1) {
                return ((Tab) TabsView.m13570$$Nest$fgettabs(this.this$0).get(r1)).id;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                return 0;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
                TabView r22 = (TabView) r2.itemView;
                r22.setTab((Tab) TabsView.m13570$$Nest$fgettabs(this.this$0).get(r3), r3);
                if (TabsView.m13566$$Nest$fgetreordering(this.this$0) == true) goto L5;
            L9:
                boolean r1 = false;
            L10:
                r22.setReordering(r1);
                return;
            L5:
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0) == null) goto L9;
                if (TabsView.m13555$$Nest$fgetdelegate(this.this$0).canReorder(r3) == false) goto L9;
                r1 = true;
                goto L10
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                return new RecyclerListView.Holder(new TabView(this.this$0, this.mContext));
            }
        }

        /* loaded from: classes7.dex */
        public static class Tab {
            public float alpha;
            public int counter;
            public int id;
            public CharSequence title;
            public int titleWidth;

            public Tab(int r2, CharSequence r3) {
                this.alpha = 1.0f;
                this.id = r2;
                this.title = r3;
            }

            public int getWidth(boolean r1, TextPaint r2) {
                int r12 = (int) Math.ceil(HintView2.measureCorrectly(this.title, r2));
                this.titleWidth = r12;
                return Math.max(0, r12);
            }
        }

        /* loaded from: classes7.dex */
        public class TabView extends View {
            private int currentPosition;
            private Tab currentTab;
            private CharSequence currentText;
            private RectF rect;
            private boolean reordering;
            private final AnimatedFloat shakeAlpha;
            private Shaker shaker;
            private int tabWidth;
            private Text text;
            private int textOffsetX;
            final /* synthetic */ TabsView this$0;

            /* renamed from: -$$Nest$fgetcurrentTab, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Tab m13578$$Nest$fgetcurrentTab(TabView r0) {
                return r0.currentTab;
            }

            /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
            public static /* bridge */ /* synthetic */ RectF m13579$$Nest$fgetrect(TabView r0) {
                return r0.rect;
            }

            /* renamed from: -$$Nest$fgettabWidth, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m13580$$Nest$fgettabWidth(TabView r0) {
                return r0.tabWidth;
            }

            public TabView(TabsView r3, Context r4) {
                this.this$0 = r3;
                super(r4);
                this.rect = new RectF();
                this.shakeAlpha = new AnimatedFloat(this, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
            }

            @Override // android.view.View
            public int getId() {
                return this.currentTab.id;
            }

            @Override // android.view.View
            @SuppressLint({"DrawAllocation"})
            public void onDraw(Canvas r23) {
                Canvas r1 = r23;
                r1.save();
                float r2 = this.shakeAlpha.set(this.reordering);
                if (r2 <= 0.0f) goto L9;
                if (this.shaker != null) goto L7;
                this.shaker = new Shaker(this);
            L7:
                r1.translate(getWidth() / 2.0f, getHeight() / 2.0f);
                this.shaker.concat(r1, r2);
                r1.translate((-getWidth()) / 2.0f, (-getHeight()) / 2.0f);
            L9:
                if (this.currentTab.id != Integer.MAX_VALUE) goto L11;
            L17:
                int r22 = TabsView.m13563$$Nest$fgetmanualScrollingToId(this.this$0);
                TabsView r3 = this.this$0;
                if (r22 == (-1)) goto L21;
                int r24 = TabsView.m13563$$Nest$fgetmanualScrollingToId(r3);
                int r32 = TabsView.m13568$$Nest$fgetselectedTabId(this.this$0);
            L20:
                int r12 = r24;
                int r13 = r32;
                int r25 = this.currentTab.id;
                TabsView r33 = this.this$0;
                if (r25 != r12) goto L26;
                int r26 = TabsView.m13547$$Nest$fgetactiveTextColorKey(r33);
                int r34 = TabsView.m13573$$Nest$fgetunactiveTextColorKey(this.this$0);
                int r4 = Theme.key_chats_tabUnreadActiveBackground;
                int r5 = Theme.key_chats_tabUnreadUnactiveBackground;
            L25:
                int r14 = r4;
                int r15 = r5;
                int r42 = TabsView.m13569$$Nest$fgetselectorType(this.this$0);
                TabsView r52 = this.this$0;
                if (r42 != 9) goto L31;
                TabsView.m13572$$Nest$fgettextPaint(r52).setColor(Theme.getColor(TabsView.m13573$$Nest$fgetunactiveTextColorKey(this.this$0), TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0)));
            L40:
                int r27 = this.currentTab.counter;
                if (r27 <= 0) goto L43;
                String r28 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r27)});
                int r43 = (int) Math.ceil(TabsView.m13571$$Nest$fgettextCounterPaint(this.this$0).measureText(r28));
                int r6 = Math.max(AndroidUtilities.dp(10.0f), r43) + AndroidUtilities.dp(10.0f);
            L45:
                if (this.currentTab.id != Integer.MAX_VALUE) goto L47;
            L51:
                float r17 = 0.0f;
            L53:
                int r7 = r6;
                int r53 = this.currentTab.titleWidth;
                if (r7 == 0) goto L59;
                if (r28 == null) goto L57;
                float r62 = 1.0f;
            L58:
                int r63 = AndroidUtilities.dp(r62 * 6.0f) + r7;
            L60:
                this.tabWidth = r53 + r63;
                int r19 = (getMeasuredWidth() - this.tabWidth) / 2;
                CharSequence r54 = this.currentTab.title;
                if (r54 != null) goto L65;
                if (this.currentText == null) goto L65;
            L66:
                Tab r55 = this.currentTab;
                float r20 = 2.0f;
                CharSequence r35 = Emoji.replaceEmoji(r55.title, TabsView.m13572$$Nest$fgettextPaint(this.this$0).getFontMetricsInt(), false);
                r55.title = r35;
                this.currentText = r35;
                Text r36 = this.text;
                if (r36 == null) goto L69;
                r36.detach();
            L69:
                this.text = new Text(this.currentText, TabsView.m13572$$Nest$fgettextPaint(this.this$0).getTextSize() / AndroidUtilities.density, TabsView.m13572$$Nest$fgettextPaint(this.this$0).getTypeface()).supportAnimatedEmojis(this);
            L71:
                Text r37 = this.text;
                if (r37 == null) goto L74;
                String r8 = r28;
                int r9 = r43;
                float r21 = 1.0f;
                r37.ellipsize(AndroidUtilities.dp(400.0f)).draw(r1, r19 + this.textOffsetX, getMeasuredHeight() / 2, TabsView.m13572$$Nest$fgettextPaint(this.this$0).getColor(), 1.0f);
                r1 = r1;
            L75:
                if (r8 == null) goto L77;
            L82:
                TabsView.m13571$$Nest$fgettextCounterPaint(this.this$0).setColor(Theme.getColor(TabsView.m13553$$Nest$fgetbackgroundColorKey(this.this$0), TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0)));
                if (Theme.hasThemeKey(r14) == true) goto L85;
            L96:
                TabsView.m13554$$Nest$fgetcounterPaint(this.this$0).setColor(TabsView.m13572$$Nest$fgettextPaint(this.this$0).getColor());
            L97:
                int r29 = (r19 + this.currentTab.titleWidth) + AndroidUtilities.dp(6.0f);
                int r38 = (getMeasuredHeight() - AndroidUtilities.dp(20.0f)) / 2;
                if (this.currentTab.id != Integer.MAX_VALUE) goto L100;
            L105:
                TabsView.m13554$$Nest$fgetcounterPaint(this.this$0).setAlpha(255);
            L106:
                this.rect.set(r29, r38, r29 + r7, AndroidUtilities.dp(20.0f) + r38);
                RectF r210 = this.rect;
                float r44 = AndroidUtilities.density;
                r1.drawRoundRect(r210, r44 * 11.5f, r44 * 11.5f, TabsView.m13554$$Nest$fgetcounterPaint(this.this$0));
                if (r8 == null) goto L113;
                if (this.currentTab.id == Integer.MAX_VALUE) goto L111;
                TabsView.m13571$$Nest$fgettextCounterPaint(this.this$0).setAlpha((int) ((r21 - TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0)) * 255.0f));
            L111:
                RectF r211 = this.rect;
                r1.drawText(r8, r211.left + ((r211.width() - r9) / r20), r38 + AndroidUtilities.dp(14.5f), TabsView.m13571$$Nest$fgettextCounterPaint(this.this$0));
            L113:
                if (this.currentTab.id != Integer.MAX_VALUE) goto L115;
            L120:
                if (this.currentTab.id != Integer.MAX_VALUE) goto L122;
            L124:
                r23.restore();
                return;
            L122:
                if (TabsView.m13557$$Nest$fgeteditingAnimationProgress(this.this$0) == r17) goto L124;
                r23.restore();
                goto L124
            L115:
                if (TabsView.m13560$$Nest$fgetisEditing(this.this$0) == false) goto L117;
            L118:
                TabsView.m13556$$Nest$fgetdeletePaint(this.this$0).setColor(TabsView.m13571$$Nest$fgettextCounterPaint(this.this$0).getColor());
                TabsView.m13556$$Nest$fgetdeletePaint(this.this$0).setAlpha((int) (TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0) * 255.0f));
                float r72 = AndroidUtilities.dp(3.0f);
                r1.drawLine(this.rect.centerX() - r72, this.rect.centerY() - r72, this.rect.centerX() + r72, this.rect.centerY() + r72, TabsView.m13556$$Nest$fgetdeletePaint(this.this$0));
                r23.drawLine(this.rect.centerX() - r72, this.rect.centerY() + r72, this.rect.centerX() + r72, this.rect.centerY() - r72, TabsView.m13556$$Nest$fgetdeletePaint(this.this$0));
                goto L120
            L117:
                if (TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0) == r17) goto L120;
            L100:
                if (TabsView.m13560$$Nest$fgetisEditing(this.this$0) == false) goto L102;
            L103:
                if (r8 != null) goto L105;
                TabsView.m13554$$Nest$fgetcounterPaint(this.this$0).setAlpha((int) (TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0) * 255.0f));
                goto L106
            L102:
                if (TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0) == r17) goto L105;
            L85:
                if (Theme.hasThemeKey(r15) == false) goto L96;
                int r212 = Theme.getColor(r14, TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0));
                if (TabsView.m13549$$Nest$fgetanimatingIndicator(this.this$0) == false) goto L89;
            L90:
                int r39 = this.currentTab.id;
                if (r39 == r12) goto L95;
                if (r39 == r13) goto L95;
            L94:
                TabsView.m13554$$Nest$fgetcounterPaint(this.this$0).setColor(r212);
            L95:
                TabsView.m13554$$Nest$fgetcounterPaint(this.this$0).setColor(ColorUtils.blendARGB(Theme.getColor(r15, TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0)), r212, TabsView.m13550$$Nest$fgetanimatingIndicatorProgress(this.this$0)));
                goto L97
            L89:
                if (TabsView.m13564$$Nest$fgetmanualScrollingToPosition(this.this$0) == (-1)) goto L94;
            L77:
                if (this.currentTab.id == Integer.MAX_VALUE) goto L120;
                if (TabsView.m13560$$Nest$fgetisEditing(this.this$0) == true) goto L82;
                if (TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0) == r17) goto L120;
            L74:
                r8 = r28;
                r9 = r43;
                r21 = 1.0f;
            L65:
                if (TextUtils.equals(r54, this.currentText) == false) goto L66;
                r20 = 2.0f;
                goto L71
            L57:
                r62 = TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0);
                goto L58
            L59:
                r63 = 0;
                goto L60
            L47:
                if (TabsView.m13560$$Nest$fgetisEditing(this.this$0) == false) goto L49;
            L52:
                r17 = 0.0f;
                r6 = (int) (r6 + ((AndroidUtilities.dp(20.0f) - r6) * TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0)));
                goto L53
            L49:
                if (TabsView.m13558$$Nest$fgeteditingStartAnimationProgress(this.this$0) == 0.0f) goto L51;
            L43:
                r28 = null;
                r43 = 0;
                r6 = 0;
                goto L45
            L31:
                if (TabsView.m13549$$Nest$fgetanimatingIndicator(r52) == false) goto L33;
            L34:
                int r45 = this.currentTab.id;
                if (r45 == r12) goto L39;
                if (r45 == r13) goto L39;
            L38:
                TabsView.m13572$$Nest$fgettextPaint(this.this$0).setColor(Theme.getColor(r26, TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0)));
            L39:
                TabsView.m13572$$Nest$fgettextPaint(this.this$0).setColor(ColorUtils.blendARGB(Theme.getColor(r34, TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0)), Theme.getColor(r26, TabsView.m13567$$Nest$fgetresourcesProvider(this.this$0)), TabsView.m13550$$Nest$fgetanimatingIndicatorProgress(this.this$0)));
                goto L40
            L33:
                if (TabsView.m13563$$Nest$fgetmanualScrollingToId(this.this$0) == (-1)) goto L38;
            L26:
                r26 = TabsView.m13573$$Nest$fgetunactiveTextColorKey(r33);
                r34 = TabsView.m13547$$Nest$fgetactiveTextColorKey(this.this$0);
                r4 = Theme.key_chats_tabUnreadUnactiveBackground;
                r5 = Theme.key_chats_tabUnreadActiveBackground;
                goto L25
            L21:
                r24 = TabsView.m13568$$Nest$fgetselectedTabId(r3);
                r32 = TabsView.m13565$$Nest$fgetpreviousId(this.this$0);
                goto L20
            L11:
                if (TabsView.m13557$$Nest$fgeteditingAnimationProgress(this.this$0) == 0.0f) goto L17;
                r1.save();
                float r213 = TabsView.m13557$$Nest$fgeteditingAnimationProgress(this.this$0);
                if ((this.currentPosition % 2) != 0) goto L15;
                float r310 = 1.0f;
            L16:
                float r214 = r213 * r310;
                r1.translate(AndroidUtilities.dp(0.66f) * r214, 0.0f);
                r1.rotate(r214, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                goto L17
            L15:
                r310 = -1.0f;
                goto L16
            }

            @Override // android.view.View
            public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
                super.onInitializeAccessibilityNodeInfo(r3);
                if (this.currentTab != null) goto L5;
            L9:
                boolean r2 = false;
            L10:
                r3.setSelected(r2);
                return;
            L5:
                if (TabsView.m13568$$Nest$fgetselectedTabId(this.this$0) == (-1)) goto L9;
                if (this.currentTab.id != TabsView.m13568$$Nest$fgetselectedTabId(this.this$0)) goto L9;
                r2 = true;
                goto L10
            }

            @Override // android.view.View
            public void onMeasure(int r3, int r4) {
                setMeasuredDimension((this.currentTab.getWidth(false, TabsView.m13572$$Nest$fgettextPaint(this.this$0)) + AndroidUtilities.dp(this.this$0.tabMarginDp * 2)) + TabsView.m13548$$Nest$fgetadditionalTabWidth(this.this$0), View.MeasureSpec.getSize(r4));
            }

            public void setReordering(boolean r2) {
                if (this.reordering != r2) goto L5;
                return;
            L5:
                this.reordering = r2;
                invalidate();
            }

            public void setTab(Tab r1, int r2) {
                this.currentTab = r1;
                this.currentPosition = r2;
                setContentDescription(r1.title);
                setAlpha(r1.alpha);
                requestLayout();
            }
        }

        /* loaded from: classes7.dex */
        public interface TabsViewDelegate {
            void applyReorder(ArrayList<Integer> r1);

            boolean canPerformActions();

            boolean canReorder(int r1);

            void invalidateBlur();

            boolean needsTab(int r1);

            void onPageScrolled(float r1);

            void onPageSelected(int r1, boolean r2);

            void onSamePageSelected();
        }

        public static /* synthetic */ void $r8$lambda$CWCWzGJfX566C7reINiSa8dBx7Q(TabsView r0, int r1) {
            r0.lambda$scrollToChild$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$IgCLbS7nevLyA0eife5X8sXGef4(TabsView r0, ValueAnimator r1) {
            r0.lambda$scrollToTab$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$Zutp278fGcMxc4jMT_XBssn2uF8(TabsView r0, View r1, int r2, float r3, float r4) {
            r0.lambda$new$0(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$e783_S2j1SyEmOpA-ixThlQT3_c, reason: not valid java name */
        public static /* synthetic */ void m13545$r8$lambda$e783_S2j1SyEmOpAixThlQT3_c(TabsView r0, boolean r1, View r2) {
            r0.lambda$setReordering$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$j6saSZONxGTLgsxkbyYqIhznOiw(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: $r8$lambda$rw7lRrTGudMv-sdshoqW4gxpYUs, reason: not valid java name */
        public static /* synthetic */ boolean m13546$r8$lambda$rw7lRrTGudMvsdshoqW4gxpYUs(TabsView r0, View r1, int r2) {
            return r0.lambda$new$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetactiveTextColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13547$$Nest$fgetactiveTextColorKey(TabsView r0) {
            return r0.activeTextColorKey;
        }

        /* renamed from: -$$Nest$fgetadditionalTabWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13548$$Nest$fgetadditionalTabWidth(TabsView r0) {
            return r0.additionalTabWidth;
        }

        /* renamed from: -$$Nest$fgetanimatingIndicator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13549$$Nest$fgetanimatingIndicator(TabsView r0) {
            return r0.animatingIndicator;
        }

        /* renamed from: -$$Nest$fgetanimatingIndicatorProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13550$$Nest$fgetanimatingIndicatorProgress(TabsView r0) {
            return r0.animatingIndicatorProgress;
        }

        /* renamed from: -$$Nest$fgetanimationRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m13551$$Nest$fgetanimationRunnable(TabsView r0) {
            return r0.animationRunnable;
        }

        /* renamed from: -$$Nest$fgetanimationTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13552$$Nest$fgetanimationTime(TabsView r0) {
            return r0.animationTime;
        }

        /* renamed from: -$$Nest$fgetbackgroundColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13553$$Nest$fgetbackgroundColorKey(TabsView r0) {
            return r0.backgroundColorKey;
        }

        /* renamed from: -$$Nest$fgetcounterPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m13554$$Nest$fgetcounterPaint(TabsView r0) {
            return r0.counterPaint;
        }

        /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TabsViewDelegate m13555$$Nest$fgetdelegate(TabsView r0) {
            return r0.delegate;
        }

        /* renamed from: -$$Nest$fgetdeletePaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m13556$$Nest$fgetdeletePaint(TabsView r0) {
            return r0.deletePaint;
        }

        /* renamed from: -$$Nest$fgeteditingAnimationProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13557$$Nest$fgeteditingAnimationProgress(TabsView r0) {
            return r0.editingAnimationProgress;
        }

        /* renamed from: -$$Nest$fgeteditingStartAnimationProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13558$$Nest$fgeteditingStartAnimationProgress(TabsView r0) {
            return r0.editingStartAnimationProgress;
        }

        /* renamed from: -$$Nest$fgetinterpolator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CubicBezierInterpolator m13559$$Nest$fgetinterpolator(TabsView r0) {
            return r0.interpolator;
        }

        /* renamed from: -$$Nest$fgetisEditing, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13560$$Nest$fgetisEditing(TabsView r0) {
            return r0.isEditing;
        }

        /* renamed from: -$$Nest$fgetisInHiddenMode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13561$$Nest$fgetisInHiddenMode(TabsView r0) {
            return r0.isInHiddenMode;
        }

        /* renamed from: -$$Nest$fgetlastAnimationTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m13562$$Nest$fgetlastAnimationTime(TabsView r2) {
            return r2.lastAnimationTime;
        }

        /* renamed from: -$$Nest$fgetmanualScrollingToId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13563$$Nest$fgetmanualScrollingToId(TabsView r0) {
            return r0.manualScrollingToId;
        }

        /* renamed from: -$$Nest$fgetmanualScrollingToPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13564$$Nest$fgetmanualScrollingToPosition(TabsView r0) {
            return r0.manualScrollingToPosition;
        }

        /* renamed from: -$$Nest$fgetpreviousId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13565$$Nest$fgetpreviousId(TabsView r0) {
            return r0.previousId;
        }

        /* renamed from: -$$Nest$fgetreordering, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13566$$Nest$fgetreordering(TabsView r0) {
            return r0.reordering;
        }

        /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m13567$$Nest$fgetresourcesProvider(TabsView r0) {
            return r0.resourcesProvider;
        }

        /* renamed from: -$$Nest$fgetselectedTabId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13568$$Nest$fgetselectedTabId(TabsView r0) {
            return r0.selectedTabId;
        }

        /* renamed from: -$$Nest$fgetselectorType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13569$$Nest$fgetselectorType(TabsView r0) {
            return r0.selectorType;
        }

        /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m13570$$Nest$fgettabs(TabsView r0) {
            return r0.tabs;
        }

        /* renamed from: -$$Nest$fgettextCounterPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m13571$$Nest$fgettextCounterPaint(TabsView r0) {
            return r0.textCounterPaint;
        }

        /* renamed from: -$$Nest$fgettextPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m13572$$Nest$fgettextPaint(TabsView r0) {
            return r0.textPaint;
        }

        /* renamed from: -$$Nest$fgetunactiveTextColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13573$$Nest$fgetunactiveTextColorKey(TabsView r0) {
            return r0.unactiveTextColorKey;
        }

        /* renamed from: -$$Nest$fputanimatingIndicator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13574$$Nest$fputanimatingIndicator(TabsView r0, boolean r1) {
            r0.animatingIndicator = r1;
        }

        /* renamed from: -$$Nest$fputanimationTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13575$$Nest$fputanimationTime(TabsView r0, float r1) {
            r0.animationTime = r1;
        }

        /* renamed from: -$$Nest$fputindicatorProgress2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13576$$Nest$fputindicatorProgress2(TabsView r0, float r1) {
            r0.indicatorProgress2 = r1;
        }

        /* renamed from: -$$Nest$msaveFromValues, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13577$$Nest$msaveFromValues(TabsView r0) {
            r0.saveFromValues();
        }

        public TabsView(Context r21, boolean r22, int r23, Theme.ResourcesProvider r24) {
            super(r21);
            this.indicatorProgress2 = 1.0f;
            TextPaint r5 = new TextPaint(1);
            this.textPaint = r5;
            TextPaint r7 = new TextPaint(1);
            this.textCounterPaint = r7;
            TextPaint r8 = new TextPaint(1);
            this.deletePaint = r8;
            this.counterPaint = new Paint(1);
            this.tabs = new ArrayList();
            this.tabMarginDp = 16;
            this.selectedTabId = -1;
            this.manualScrollingToPosition = -1;
            this.manualScrollingToId = -1;
            this.scrollingToChild = -1;
            this.tabLineColorKey = Theme.key_profile_tabSelectedLine;
            this.activeTextColorKey = Theme.key_profile_tabSelectedText;
            this.unactiveTextColorKey = Theme.key_profile_tabText;
            this.selectorColorKey = Theme.key_profile_tabSelector;
            this.backgroundColorKey = Theme.key_actionBarDefault;
            this.interpolator = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.positionToId = new SparseIntArray(5);
            this.idToPosition = new SparseIntArray(5);
            this.positionToWidth = new SparseIntArray(5);
            this.positionToX = new SparseIntArray(5);
            this.animationRunnable = new AnonymousClass1(this);
            this.selectorPaint = new Paint(1);
            this.resourcesProvider = r24;
            this.selectorType = r23;
            r7.setTextSize(AndroidUtilities.dp(13.0f));
            r7.setTypeface(AndroidUtilities.bold());
            if (r23 == 9) goto L8;
            if (r23 == 10) goto L8;
            if (r23 == (-2)) goto L8;
            float r14 = 15.0f;
        L9:
            r5.setTextSize(AndroidUtilities.dp(r14));
            r5.setTypeface(AndroidUtilities.bold());
            r8.setStyle(Paint.Style.STROKE);
            r8.setStrokeCap(Paint.Cap.ROUND);
            r8.setStrokeWidth(AndroidUtilities.dp(1.5f));
            GradientDrawable r52 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
            this.selectorDrawable = r52;
            r52.setColor(Theme.getColor(this.tabLineColorKey, r24));
            if (r23 != (-2)) goto L12;
            float r18 = AndroidUtilities.dpf2(13.0f);
            this.selectorDrawable.setCornerRadii(new float[]{r18, r18, r18, r18, r18, r18, r18, r18});
            boolean r182 = true;
        L13:
            setHorizontalScrollBarEnabled(false);
            AnonymousClass2 r6 = new AnonymousClass2(this, r21);
            this.listView = r6;
            r6.setOverScrollMode(2);
            RecyclerListView r62 = this.listView;
            if (r22 == false) goto L16;
            r62.setItemAnimator(null);
        L17:
            RecyclerListView r63 = this.listView;
            if (r23 != (-2)) goto L20;
            r63.setSelectorType(9);
            this.listView.setSelectorRadius(6);
        L27:
            this.listView.setSelectorDrawableColor(Theme.getColor(this.selectorColorKey, r24));
            RecyclerListView r4 = this.listView;
            AnonymousClass3 r53 = new AnonymousClass3(this, r21, 0, false);
            this.layoutManager = r53;
            r4.setLayoutManager(r53);
            this.listView.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
            this.listView.setClipToPadding(false);
            this.listView.setDrawSelectorBehind(r182);
            ListAdapter r42 = new ListAdapter(this, r21);
            this.adapter = r42;
            r42.setHasStableIds(r22);
            this.listView.setAdapter(this.adapter);
            this.listView.setOnItemClickListener(new ViewPagerFixed$TabsView$$ExternalSyntheticLambda3(this));
            this.listView.setOnItemLongClickListener(new ViewPagerFixed$TabsView$$ExternalSyntheticLambda4(this));
            this.listView.setOnScrollListener(new AnonymousClass4(this));
            if (r23 == 9) goto L33;
            if (r23 == 10) goto L33;
            addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
            return;
        L33:
            addView(this.listView, LayoutHelper.createFrame(-2, -1, 1));
            return;
        L20:
            if (r23 != 10) goto L22;
            int r82 = 9;
        L23:
            r63.setSelectorType(r82);
            RecyclerListView r64 = this.listView;
            if (r23 != 3) goto L26;
            r64.setSelectorRadius(0);
            goto L27
        L26:
            r64.setSelectorRadius(6);
            goto L27
        L22:
            r82 = r23;
            goto L23
        L16:
            ((DefaultItemAnimator) r62.getItemAnimator()).setDelayAnimations(false);
            goto L17
        L12:
            float r9 = AndroidUtilities.dpf2(3.0f);
            r182 = true;
            this.selectorDrawable.setCornerRadii(new float[]{r9, r9, r9, r9, 0.0f, 0.0f, 0.0f, 0.0f});
        L8:
            r14 = 14.0f;
            goto L9
        }

        private /* synthetic */ void lambda$new$0(View r2, int r3, float r4, float r5) {
            TabsViewDelegate r42 = this.delegate;
            if (r42 != null) goto L5;
        L7:
            TabView r22 = (TabView) r2;
            if (r3 != this.currentPosition) goto L13;
            TabsViewDelegate r43 = this.delegate;
            if (r43 == null) goto L13;
            r43.onSamePageSelected();
            return;
        L13:
            Utilities.Callback2Return<Integer, Integer, Boolean> r44 = this.preTabClick;
            if (r44 != null) goto L16;
        L18:
            scrollToTab(TabView.m13578$$Nest$fgetcurrentTab(r22).id, r3);
            return;
        L16:
            if (r44.run(Integer.valueOf(TabView.m13578$$Nest$fgetcurrentTab(r22).id), Integer.valueOf(r3)).booleanValue() == false) goto L18;
            return;
        L5:
            if (r42.canPerformActions() == true) goto L7;
        }

        private /* synthetic */ boolean lambda$new$1(View r1, int r2) {
            Utilities.Callback2Return<Integer, View, Boolean> r0 = this.onTabLongClick;
            if (r0 != null) goto L7;
            return false;
        L7:
            return r0.run(Integer.valueOf(TabView.m13578$$Nest$fgetcurrentTab((TabView) r1).id), r1).booleanValue();
        }

        private /* synthetic */ void lambda$scrollToChild$4(int r1) {
            this.listView.smoothScrollToPosition(r1);
        }

        private /* synthetic */ void lambda$scrollToTab$2(ValueAnimator r1) {
            float r12 = ((Float) r1.getAnimatedValue()).floatValue();
            setAnimationIdicatorProgress(r12);
            TabsViewDelegate r0 = this.delegate;
            if (r0 == null) goto L6;
            r0.onPageScrolled(r12);
            return;
        }

        private /* synthetic */ void lambda$setReordering$3(boolean r3, View r4) {
            int r0 = this.listView.getChildAdapterPosition(r4);
            if ((r4 instanceof TabView) == false) goto L14;
            TabView r42 = (TabView) r4;
            if (r3 == false) goto L11;
            TabsViewDelegate r2 = this.delegate;
            if (r2 == null) goto L11;
            if (r2.canReorder(r0) == false) goto L11;
            boolean r22 = true;
        L12:
            r42.setReordering(r22);
            return;
        L11:
            r22 = false;
            goto L12
        }

        private void saveFromValues() {
            this.overrideFromX = this.lastDrawnIndicatorX;
            this.overrideFromW = this.lastDrawnIndicatorW;
        }

        private void scrollToChild(int r3) {
            if (this.tabs.isEmpty() == false) goto L5;
            return;
        L5:
            if (this.scrollingToChild == r3) goto L20;
            if (r3 >= 0) goto L8;
            return;
        L8:
            if (r3 >= this.tabs.size()) goto L22;
            this.scrollingToChild = r3;
            if (this.listView.getVisibility() != 8) goto L13;
        L17:
            AndroidUtilities.runOnUIThread(new ViewPagerFixed$TabsView$$ExternalSyntheticLambda1(this, r3), 100);
            return;
        L13:
            if (this.listView.getMeasuredWidth() == 0) goto L17;
            this.listView.smoothScrollToPosition(r3);
            return;
        L22:
            return;
        }

        private void updateTabsWidths() {
            this.positionToX.clear();
            this.positionToWidth.clear();
            int r0 = AndroidUtilities.dp(7.0f);
            int r1 = this.tabs.size();
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            int r4 = this.tabs.get(r3).getWidth(false, this.textPaint);
            this.positionToWidth.put(r3, r4);
            this.positionToX.put(r3, (this.additionalTabWidth / 2) + r0);
            r0 = r0 + ((r4 + AndroidUtilities.dp(this.tabMarginDp * 2)) + this.additionalTabWidth);
            r3 = r3 + 1;
            goto L3
        }

        public void addTab(int r4, CharSequence r5) {
            int r0 = this.tabs.size();
            if (r0 == 0) goto L5;
        L7:
            this.positionToId.put(r0, r4);
            this.idToPosition.put(r4, r0);
            int r2 = this.selectedTabId;
            if (r2 == (-1)) goto L11;
            if (r2 != r4) goto L11;
            this.currentPosition = r0;
        L11:
            Tab r02 = new Tab(r4, r5);
            this.allTabsWidth += r02.getWidth(true, this.textPaint) + AndroidUtilities.dp(this.tabMarginDp * 2);
            this.tabs.add(r02);
            return;
        L5:
            if (this.selectedTabId != (-1)) goto L7;
            this.selectedTabId = r4;
            goto L7
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r9, View r10, long r11) {
            boolean r112 = super.drawChild(r9, r10, r11);
            if (r10 != this.listView) goto L59;
            int r102 = getMeasuredHeight();
            boolean r12 = this.isInHiddenMode;
            if (r12 == false) goto L12;
            float r1 = this.hideProgress;
            if (r1 == 1.0f) goto L12;
            float r13 = r1 + 0.1f;
            this.hideProgress = r13;
            if (r13 <= 1.0f) goto L11;
            this.hideProgress = 1.0f;
        L11:
            invalidate();
        L19:
            this.selectorDrawable.setAlpha((int) (this.listView.getAlpha() * 255.0f));
            int r2 = 0;
            if (this.animatingIndicator == false) goto L22;
        L29:
            int r122 = this.layoutManager.findFirstVisibleItemPosition();
            if (r122 != (-1)) goto L31;
        L28:
            int r123 = 0;
        L42:
            int r14 = (int) (r2 + this.listView.getX());
            if (r123 == 0) goto L59;
            int r22 = this.selectorType;
            if (r22 != 9) goto L47;
        L58:
            this.selectorPaint.setColor(Theme.multAlpha(this.textPaint.getColor(), 0.15f));
            float r103 = r102 / 2.0f;
            float r0 = AndroidUtilities.dp(26.0f);
            RectF r23 = AndroidUtilities.rectTmp;
            float r02 = r0 / 2.0f;
            r23.set(r14 - AndroidUtilities.dp(12.0f), r103 - r02, (r14 + r123) + AndroidUtilities.dp(12.0f), r103 + r02);
            r9.drawRoundRect(r23, r23.height() / 2.0f, r23.height() / 2.0f, this.selectorPaint);
            goto L59
        L47:
            if (r22 == 10) goto L58;
            float r24 = r14;
            this.lastDrawnIndicatorX = r24;
            float r3 = r123;
            this.lastDrawnIndicatorW = r3;
            float r5 = this.indicatorProgress2;
            if (r5 == 1.0f) goto L53;
            r14 = (int) AndroidUtilities.lerp(r24, r24, r5);
            r123 = (int) AndroidUtilities.lerp(this.lastDrawnIndicatorW, r3, this.indicatorProgress2);
        L53:
            if (this.selectorType != (-2)) goto L56;
            float r03 = this.additionalTabWidth / 2.0f;
            int r104 = (r102 / 2) - AndroidUtilities.dp(14.0f);
            this.selectorDrawable.setBounds((int) ((r14 - AndroidUtilities.dp(12.5f)) - r03), r104, (int) (((r14 + r123) + AndroidUtilities.dp(12.5f)) + r03), AndroidUtilities.dp(28.0f) + r104);
            this.selectorDrawable.setAlpha(31);
            this.selectorDrawable.draw(r9);
            return r112;
        L56:
            this.selectorDrawable.setBounds(r14, (int) ((r102 - AndroidUtilities.dpr(4.0f)) + (this.hideProgress * AndroidUtilities.dpr(4.0f))), r123 + r14, (int) (r102 + (this.hideProgress * AndroidUtilities.dpr(4.0f))));
            this.selectorDrawable.draw(r9);
            return r112;
        L31:
            RecyclerView.ViewHolder r15 = this.listView.findViewHolderForAdapterPosition(r122);
            if (r15 == null) goto L28;
            if (this.animatingIndicator == false) goto L36;
            int r25 = this.previousPosition;
            int r32 = this.currentPosition;
        L37:
            int r4 = this.positionToX.get(r25);
            int r52 = this.positionToX.get(r32);
            int r26 = this.positionToWidth.get(r25);
            int r33 = this.positionToWidth.get(r32);
            if (this.additionalTabWidth == 0) goto L40;
            int r124 = ((int) (r4 + ((r52 - r4) * this.animatingIndicatorProgress))) + AndroidUtilities.dp(this.tabMarginDp);
        L41:
            int r27 = (int) (r26 + ((r33 - r26) * this.animatingIndicatorProgress));
        L27:
            int r7 = r27;
            r2 = r124;
            r123 = r7;
            goto L42
        L40:
            r124 = (((int) (r4 + ((r52 - r4) * this.animatingIndicatorProgress))) - (this.positionToX.get(r122) - r15.itemView.getLeft())) + AndroidUtilities.dp(this.tabMarginDp);
            goto L41
        L36:
            r25 = this.currentPosition;
            r32 = this.manualScrollingToPosition;
            goto L37
        L22:
            if (this.manualScrollingToPosition != (-1)) goto L29;
            RecyclerView.ViewHolder r125 = this.listView.findViewHolderForAdapterPosition(this.currentPosition);
            if (r125 == null) goto L28;
            TabView r126 = (TabView) r125.itemView;
            r27 = TabView.m13580$$Nest$fgettabWidth(r126);
            r124 = (int) (r126.getX() + ((r126.getMeasuredWidth() - r27) / 2));
        L12:
            if (r12 == true) goto L19;
            float r127 = this.hideProgress;
            if (r127 == 0.0f) goto L19;
            float r128 = r127 - 0.12f;
            this.hideProgress = r128;
            if (r128 >= 0.0f) goto L18;
            this.hideProgress = 0.0f;
        L18:
            invalidate();
        L59:
            return r112;
        }

        public void finishAddingTabs() {
            this.adapter.notifyDataSetChanged();
        }

        public float getAnimatingIndicatorProgress() {
            return this.animatingIndicatorProgress;
        }

        public int getCurrentPosition() {
            return this.currentPosition;
        }

        public int getCurrentTabId() {
            return this.selectedTabId;
        }

        public int getFirstTabId() {
            return this.positionToId.get(0, 0);
        }

        public int getNextPageId(boolean r3) {
            SparseIntArray r0 = this.positionToId;
            int r2 = this.currentPosition;
            if (r3 == false) goto L5;
            int r32 = 1;
        L7:
            return r0.get(r2 + r32, -1);
        L5:
            r32 = -1;
            goto L7
        }

        public int getPageIdByPosition(int r2) {
            return this.positionToId.get(r2, -1);
        }

        public int getPreviousPosition() {
            return this.previousPosition;
        }

        public Drawable getSelectorDrawable() {
            return this.selectorDrawable;
        }

        public RecyclerListView getTabsContainer() {
            return this.listView;
        }

        public void hide(boolean r6, boolean r7) {
            this.isInHiddenMode = r6;
            int r0 = 0;
            float r1 = 1.0f;
            if (r7 == false) goto L20;
        L5:
            if (r0 >= this.listView.getChildCount()) goto L38;
            ViewPropertyAnimator r72 = this.listView.getChildAt(r0).animate();
            if (r6 == false) goto L9;
            float r3 = 0.0f;
        L10:
            ViewPropertyAnimator r73 = r72.alpha(r3);
            if (r6 == false) goto L13;
            float r32 = 0.0f;
        L14:
            ViewPropertyAnimator r74 = r73.scaleX(r32);
            if (r6 == false) goto L17;
            float r33 = 0.0f;
        L18:
            r74.scaleY(r33).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(220).start();
            r0 = r0 + 1;
            goto L5
        L17:
            r33 = 1.0f;
            goto L18
        L13:
            r32 = 1.0f;
            goto L14
        L9:
            r3 = 1.0f;
        L38:
            invalidate();
            return;
        L20:
            if (r0 >= this.listView.getChildCount()) goto L34;
            View r75 = this.listView.getChildAt(r0);
            if (r6 == false) goto L24;
            float r34 = 0.0f;
        L25:
            r75.setScaleX(r34);
            if (r6 == false) goto L28;
            float r35 = 0.0f;
        L29:
            r75.setScaleY(r35);
            if (r6 == false) goto L32;
            float r36 = 0.0f;
        L33:
            r75.setAlpha(r36);
            r0 = r0 + 1;
            goto L20
        L32:
            r36 = 1.0f;
            goto L33
        L28:
            r35 = 1.0f;
            goto L29
        L24:
            r34 = 1.0f;
            goto L25
        L34:
            if (r6 == true) goto L37;
            r1 = 0.0f;
        L37:
            this.hideProgress = r1;
            goto L38
        }

        public boolean isAnimatingIndicator() {
            return this.animatingIndicator;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r42 = r4 - r2;
            if (this.prevLayoutWidth == r42) goto L10;
            this.prevLayoutWidth = r42;
            this.scrollingToChild = -1;
            if (this.animatingIndicator == false) goto L11;
            AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
            this.animatingIndicator = false;
            setEnabled(true);
            TabsViewDelegate r0 = this.delegate;
            if (r0 == null) goto L12;
            r0.onPageScrolled(1.0f);
            return;
        L12:
            return;
        L11:
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            if (this.tabs.isEmpty() == true) goto L21;
            int r0 = (View.MeasureSpec.getSize(r7) - AndroidUtilities.dp(7.0f)) - AndroidUtilities.dp(7.0f);
            int r1 = this.additionalTabWidth;
            if (this.tabs.size() == 1) goto L16;
            int r2 = this.selectorType;
            if (r2 == 9) goto L16;
            if (r2 == 10) goto L16;
            int r22 = this.allTabsWidth;
            if (r22 >= r0) goto L14;
            int r02 = (r0 - r22) / this.tabs.size();
        L15:
            this.additionalTabWidth = r02;
        L18:
            if (r1 == this.additionalTabWidth) goto L20;
            this.ignoreLayout = true;
            this.adapter.notifyDataSetChanged();
            this.ignoreLayout = false;
        L20:
            updateTabsWidths();
            this.invalidated = false;
            goto L21
        L14:
            r02 = 0;
        L16:
            this.additionalTabWidth = 0;
        L21:
            super.onMeasure(r7, r8);
        }

        public void removeTabs() {
            this.tabs.clear();
            this.positionToId.clear();
            this.idToPosition.clear();
            this.positionToWidth.clear();
            this.positionToX.clear();
            this.allTabsWidth = 0;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        public void scrollToTab(int r6, int r7) {
            int r0 = this.currentPosition;
            if (r0 >= r7) goto L5;
            boolean r3 = true;
        L6:
            this.scrollingToChild = -1;
            this.previousPosition = r0;
            this.previousId = this.selectedTabId;
            TabsViewDelegate r02 = this.delegate;
            if (r02 != null) goto L9;
        L10:
            this.currentPosition = r7;
            this.selectedTabId = r6;
        L11:
            ValueAnimator r62 = this.tabsAnimator;
            if (r62 == null) goto L15;
            r62.cancel();
        L15:
            if (this.animatingIndicator == false) goto L17;
            this.animatingIndicator = false;
        L17:
            this.animationTime = 0.0f;
            this.animatingIndicatorProgress = 0.0f;
            this.animatingIndicator = true;
            setEnabled(false);
            TabsViewDelegate r63 = this.delegate;
            if (r63 == null) goto L20;
            r63.onPageSelected(r7, r3);
        L20:
            scrollToChild(this.currentPosition);
            ValueAnimator r64 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.tabsAnimator = r64;
            r64.addUpdateListener(new ViewPagerFixed$TabsView$$ExternalSyntheticLambda5(this));
            this.tabsAnimator.setDuration(250);
            this.tabsAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.tabsAnimator.addListener(new AnonymousClass5(this));
            this.tabsAnimator.start();
            return;
        L9:
            if (r02.needsTab(r7) == false) goto L11;
        L5:
            r3 = false;
            goto L6
        }

        public void selectTab(int r4, int r5, float r6) {
            if (r6 >= 0.0f) goto L6;
            r6 = 0.0f;
        L8:
            this.currentPosition = r4;
            this.selectedTabId = this.positionToId.get(r4);
            if (r6 <= 0.0f) goto L18;
            TabsViewDelegate r0 = this.delegate;
            if (r0 != null) goto L13;
        L16:
            this.manualScrollingToPosition = r5;
        L17:
            this.manualScrollingToId = this.positionToId.get(r5);
        L19:
            this.animatingIndicatorProgress = r6;
            this.listView.invalidateViews();
            invalidate();
            scrollToChild(r4);
            if (r6 < 1.0f) goto L22;
            this.manualScrollingToPosition = -1;
            this.manualScrollingToId = -1;
            this.currentPosition = r5;
            this.selectedTabId = this.positionToId.get(r5);
        L22:
            TabsViewDelegate r3 = this.delegate;
            if (r3 == null) goto L26;
            r3.invalidateBlur();
            return;
        L26:
            return;
        L13:
            if (r0.needsTab(r5) == true) goto L16;
            this.manualScrollingToPosition = r4;
            goto L17
        L18:
            this.manualScrollingToPosition = -1;
            this.manualScrollingToId = -1;
            goto L19
        L6:
            if (r6 <= 1.0f) goto L8;
            r6 = 1.0f;
            goto L8
        }

        public void selectTabWithId(int r6, float r7) {
            int r0 = this.idToPosition.get(r6, -1);
            if (r0 >= 0) goto L6;
            return;
        L6:
            if (r7 >= 0.0f) goto L9;
            r7 = 0.0f;
        L12:
            if (r7 <= 0.0f) goto L14;
            this.manualScrollingToPosition = r0;
            this.manualScrollingToId = r6;
        L15:
            this.animatingIndicatorProgress = r7;
            this.listView.invalidateViews();
            invalidate();
            scrollToChild(r0);
            if (r7 < 1.0f) goto L19;
            this.manualScrollingToPosition = -1;
            this.manualScrollingToId = -1;
            this.currentPosition = r0;
            this.selectedTabId = r6;
            return;
        L19:
            return;
        L14:
            this.manualScrollingToPosition = -1;
            this.manualScrollingToId = -1;
            goto L15
        L9:
            if (r7 <= 1.0f) goto L12;
            r7 = 1.0f;
            goto L12
        }

        public void setAnimationIdicatorProgress(float r2) {
            this.animatingIndicatorProgress = r2;
            this.listView.invalidateViews();
            invalidate();
            TabsViewDelegate r1 = this.delegate;
            if (r1 == null) goto L6;
            r1.onPageScrolled(r2);
            return;
        }

        public void setBlurredBackground(BlurredBackgroundDrawable r1) {
            this.blurredBackgroundDrawable = r1;
            setBackground(r1);
        }

        public void setColors(int r1, int r2, int r3, int r4, int r5) {
            this.tabLineColorKey = r1;
            this.activeTextColorKey = r2;
            this.unactiveTextColorKey = r3;
            this.selectorColorKey = r4;
            this.backgroundColorKey = r5;
            this.selectorDrawable.setColor(Theme.getColor(r1, this.resourcesProvider));
        }

        public void setDelegate(TabsViewDelegate r1) {
            this.delegate = r1;
        }

        public void setIsEditing(boolean r7) {
            this.isEditing = r7;
            this.editingForwardAnimation = true;
            this.listView.invalidateViews();
            invalidate();
            if (this.isEditing == false) goto L5;
            return;
        L5:
            if (this.orderChanged == false) goto L13;
            MessagesStorage.getInstance(UserConfig.selectedAccount).saveDialogFiltersOrder();
            TLRPC.TL_messages_updateDialogFiltersOrder r72 = new TLRPC.TL_messages_updateDialogFiltersOrder();
            ArrayList<MessagesController.DialogFilter> r0 = MessagesController.getInstance(UserConfig.selectedAccount).dialogFilters;
            int r1 = r0.size();
            int r3 = 0;
        L7:
            if (r3 >= r1) goto L9;
            MessagesController.DialogFilter r4 = r0.get(r3);
            r72.order.add(Integer.valueOf(r0.get(r3).id));
            r3 = r3 + 1;
            goto L7
        L9:
            ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r72, new ViewPagerFixed$TabsView$$ExternalSyntheticLambda0());
            this.orderChanged = false;
            return;
        }

        public void setOnTabLongClick(Utilities.Callback2Return<Integer, View, Boolean> r1) {
            this.onTabLongClick = r1;
        }

        public void setPreTabClick(Utilities.Callback2Return<Integer, Integer, Boolean> r1) {
            this.preTabClick = r1;
        }

        public void setReordering(boolean r4) {
            if (this.reordering != r4) goto L5;
            return;
        L5:
            this.reordering = r4;
            if (r4 == false) goto L11;
            if (this.itemTouchHelper != null) goto L11;
            this.itemTouchHelper = new ItemTouchHelper(new AnonymousClass6(this));
        L11:
            if (this.reordering == true) goto L13;
        L15:
            ItemTouchHelper r0 = this.itemTouchHelper;
            DefaultItemAnimator r1 = null;
            if (r0 == null) goto L21;
            if (r4 == false) goto L19;
            RecyclerListView r2 = this.listView;
        L20:
            r0.attachToRecyclerView(r2);
            goto L21
        L19:
            r2 = null;
        L21:
            RecyclerListView r02 = this.listView;
            if (r4 == false) goto L24;
            r1 = this.itemAnimator;
        L24:
            r02.setItemAnimator(r1);
            AndroidUtilities.forEachViews(this.listView, new ViewPagerFixed$TabsView$$ExternalSyntheticLambda2(this, r4));
            return;
        L13:
            if (this.itemAnimator != null) goto L15;
            AnonymousClass7 r03 = new AnonymousClass7(this);
            this.itemAnimator = r03;
            r03.setSupportsChangeAnimations(false);
            this.itemAnimator.setDelayAnimations(false);
            this.itemAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.itemAnimator.setDurations(350);
            goto L15
        }

        public void updateColors() {
            BlurredBackgroundDrawable r0 = this.blurredBackgroundDrawable;
            if (r0 == null) goto L5;
            r0.updateColors();
        L5:
            this.selectorDrawable.setColor(Theme.getColor(this.tabLineColorKey, this.resourcesProvider));
            this.listView.invalidateViews();
            this.listView.invalidate();
            invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$4JzCTcZsM3PhPpFqq1OyFv9PeXs(ViewPagerFixed r0, ValueAnimator r1) {
        r0.lambda$onTouchEventInternal$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$QVzFNI9M9etyAlt6x3isg_t6hrA(ViewPagerFixed r0, ValueAnimator r1) {
        r0.lambda$scrollToPosition$1(r1);
    }

    public static /* synthetic */ float $r8$lambda$aoN7Ghc9HAnODMyle0hchIDeT6c(float r2) {
        float r22 = r2 - 1.0f;
        return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
    }

    public static /* synthetic */ void $r8$lambda$kMQLuGaX3OrNxfyc0LpwizzOzPg(ViewPagerFixed r0, ValueAnimator r1) {
        r0.lambda$cancelTouches$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$zX0PdIZZPXlvZZA3lKSqiA_bWMo(ViewPagerFixed r0, ValueAnimator r1) {
        r0.lambda$rebuild$3(r1);
    }

    /* renamed from: -$$Nest$fgetanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13534$$Nest$fgetanimatingForward(ViewPagerFixed r0) {
        return r0.animatingForward;
    }

    /* renamed from: -$$Nest$fgetbackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13535$$Nest$fgetbackAnimation(ViewPagerFixed r0) {
        return r0.backAnimation;
    }

    /* renamed from: -$$Nest$fgetstartedTracking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13536$$Nest$fgetstartedTracking(ViewPagerFixed r0) {
        return r0.startedTracking;
    }

    /* renamed from: -$$Nest$fgettabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13537$$Nest$fgettabsAnimationInProgress(ViewPagerFixed r0) {
        return r0.tabsAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetviewTypes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m13538$$Nest$fgetviewTypes(ViewPagerFixed r0) {
        return r0.viewTypes;
    }

    /* renamed from: -$$Nest$fputanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13539$$Nest$fputanimatingForward(ViewPagerFixed r0, boolean r1) {
        r0.animatingForward = r1;
    }

    /* renamed from: -$$Nest$fputmanualScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13540$$Nest$fputmanualScrolling(ViewPagerFixed r0, ValueAnimator r1) {
        r0.manualScrolling = r1;
    }

    /* renamed from: -$$Nest$fputmaybeStartTracking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13541$$Nest$fputmaybeStartTracking(ViewPagerFixed r0, boolean r1) {
        r0.maybeStartTracking = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13542$$Nest$fputtabsAnimation(ViewPagerFixed r0, AnimatorSet r1) {
        r0.tabsAnimation = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13543$$Nest$fputtabsAnimationInProgress(ViewPagerFixed r0, boolean r1) {
        r0.tabsAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$mupdateViewForIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13544$$Nest$mupdateViewForIndex(ViewPagerFixed r0, int r1) {
        r0.updateViewForIndex(r1);
    }

    static {
        interpolator = new ViewPagerFixed$$ExternalSyntheticLambda0();
    }

    public ViewPagerFixed(Context r2) {
        this(r2, null);
    }

    public ViewPagerFixed(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.currentProgress = 1.0f;
        this.viewsByType = new SparseArray();
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.updateTabProgress = new AnonymousClass1(this);
        this.rect = new Rect();
        this.allowDisallowInterceptTouch = true;
        this.resourcesProvider = r3;
        this.touchSlop = AndroidUtilities.getPixelsInCM(0.3f, true);
        this.maximumVelocity = ViewConfiguration.get(r2).getScaledMaximumFlingVelocity();
        this.viewTypes = new int[2];
        this.viewPages = new View[2];
        setClipChildren(true);
    }

    public static float distanceInfluenceForSnapDuration(float r2) {
        return (float) Math.sin((r2 - 0.5f) * 0.47123894f);
    }

    private RecyclerListView findRecyclerView(View r4) {
        if ((r4 instanceof ViewGroup) == false) goto L15;
        ViewGroup r42 = (ViewGroup) r4;
        int r0 = 0;
    L6:
        if (r0 >= r42.getChildCount()) goto L21;
        View r1 = r42.getChildAt(r0);
        if ((r1 instanceof RecyclerListView) == true) goto L10;
        if ((r1 instanceof ViewGroup) == false) goto L14;
        findRecyclerView(r1);
    L14:
        r0 = r0 + 1;
        goto L6
    L10:
        return (RecyclerListView) r1;
    L21:
        return null;
    L15:
        return null;
    }

    private View findScrollingChild(ViewGroup r7, float r8, float r9) {
        int r0 = r7.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L18;
        View r2 = r7.getChildAt(r1);
        if (r2.getVisibility() != 0) goto L17;
        r2.getHitRect(this.rect);
        if (this.rect.contains((int) r8, (int) r9) == false) goto L17;
        if (r2.canScrollHorizontally(-1) == true) goto L11;
        if ((r2 instanceof ViewGroup) == false) goto L17;
        Rect r3 = this.rect;
        View r22 = findScrollingChild((ViewGroup) r2, r8 - r3.left, r9 - r3.top);
        if (r22 == null) goto L17;
        return r22;
    L11:
        return r2;
    L17:
        r1 = r1 + 1;
        goto L3
    L18:
        return null;
    }

    private /* synthetic */ void lambda$cancelTouches$4(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.backProgress = r12;
        onBackProgress(r12);
    }

    private /* synthetic */ void lambda$onTouchEventInternal$2(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.backProgress = r12;
        onBackProgress(r12);
    }

    private /* synthetic */ void lambda$rebuild$3(ValueAnimator r2) {
        this.updateTabProgress.onAnimationUpdate(r2);
        TabsView.m13576$$Nest$fputindicatorProgress2(this.tabsView, ((Float) r2.getAnimatedValue()).floatValue());
        this.tabsView.listView.invalidateViews();
        this.tabsView.invalidate();
    }

    private /* synthetic */ void lambda$scrollToPosition$1(ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        View r2 = this.viewPages[1];
        if (r2 != null) goto L6;
        return;
    L6:
        if (this.animatingForward == false) goto L8;
        setTranslationX(r2, r0[0].getMeasuredWidth() * (1.0f - r72));
        setTranslationX(this.viewPages[0], (-r0.getMeasuredWidth()) * r72);
    L9:
        this.currentProgress = r72;
        onTabAnimationUpdate(true);
        TabsView r73 = this.tabsView;
        if (r73 == null) goto L13;
        r73.listView.invalidate();
        this.tabsView.listView.invalidateViews();
        this.tabsView.invalidate();
        return;
    L13:
        return;
    L8:
        setTranslationX(r2, (-r0[0].getMeasuredWidth()) * (1.0f - r72));
        setTranslationX(this.viewPages[0], r0.getMeasuredWidth() * r72);
        goto L9
    }

    private boolean prepareForMoving(MotionEvent r7, boolean r8) {
        if (r8 == true) goto L9;
        if (this.currentPosition != 0) goto L9;
        this.backProgress = 0.0f;
        if (onBackProgress(0.0f) == true) goto L9;
    L14:
        return false;
    L9:
        if (r8 == false) goto L13;
        if (this.currentPosition == (this.adapter.getItemCount() - 1)) goto L14;
    L13:
        if (this.manualScrolling != null) goto L14;
        if (canScroll(r7) == true) goto L18;
        return false;
    L18:
        if (r8 == true) goto L20;
    L22:
        if (r8 == false) goto L24;
    L26:
        Adapter r2 = this.adapter;
        int r3 = -1;
        if (r2 == null) goto L35;
        int r4 = this.currentPosition;
        if (r8 == false) goto L31;
        int r5 = 1;
    L33:
        if (r2.canScrollTo(r4 + r5) == true) goto L35;
        return false;
    L31:
        r5 = -1;
    L35:
        getParent().requestDisallowInterceptTouchEvent(true);
        this.maybeStartTracking = false;
        this.startedTracking = true;
        onStartTracking();
        this.startedTrackingX = (int) (r7.getX() + this.additionalOffset);
        TabsView r72 = this.tabsView;
        if (r72 == null) goto L38;
        r72.setEnabled(false);
    L38:
        this.notificationsLocker.lock();
        this.animatingForward = r8;
        int r73 = this.currentPosition;
        if (r8 == false) goto L41;
        r3 = 1;
    L41:
        this.nextPosition = r73 + r3;
        updateViewForIndex(1);
        View r22 = this.viewPages[1];
        if (r22 == null) goto L46;
        if (r8 == false) goto L45;
        setTranslationX(r22, r7[0].getMeasuredWidth());
        goto L46
    L45:
        setTranslationX(r22, -r7[0].getMeasuredWidth());
    L46:
        onTabAnimationUpdate(false);
        return true;
    L24:
        if (canScrollBackward(r7) == true) goto L26;
        return false;
    L20:
        if (canScrollForward(r7) == true) goto L22;
        return false;
    }

    private void updateViewForIndex(int r6) {
        if (r6 != 0) goto L4;
        int r0 = this.currentPosition;
    L5:
        if (r0 >= 0) goto L7;
        return;
    L7:
        if (r0 >= this.adapter.getItemCount()) goto L31;
        View r1 = this.viewPages[r6];
        int[] r2 = this.viewTypes;
        if (r1 != null) goto L21;
        r2[r6] = this.adapter.getItemViewType(r0);
        View r12 = this.viewsByType.get(this.viewTypes[r6]);
        if (r12 != null) goto L14;
        r12 = this.adapter.createView(this.viewTypes[r6]);
    L16:
        if (r12.getParent() == null) goto L18;
        ((ViewGroup) r12.getParent()).removeView(r12);
    L18:
        addView(r12);
        r12.setTranslationX(getMeasuredWidth());
        this.viewPages[r6] = r12;
        this.adapter.bindView(r12, r0, this.viewTypes[r6]);
        this.viewPages[r6].setVisibility(0);
        return;
    L14:
        this.viewsByType.remove(this.viewTypes[r6]);
        goto L16
    L21:
        if (r2[r6] != this.adapter.getItemViewType(r0)) goto L24;
        this.adapter.bindView(this.viewPages[r6], r0, this.viewTypes[r6]);
        this.viewPages[r6].setVisibility(0);
        return;
    L24:
        this.viewsByType.put(this.viewTypes[r6], this.viewPages[r6]);
        this.viewPages[r6].setVisibility(8);
        removeView(this.viewPages[r6]);
        this.viewTypes[r6] = this.adapter.getItemViewType(r0);
        View r13 = this.viewsByType.get(this.viewTypes[r6]);
        if (r13 != null) goto L27;
        r13 = this.adapter.createView(this.viewTypes[r6]);
    L28:
        addView(r13);
        this.viewPages[r6] = r13;
        r13.setVisibility(0);
        Adapter r14 = this.adapter;
        r14.bindView(this.viewPages[r6], r0, r14.getItemViewType(r0));
        return;
    L27:
        this.viewsByType.remove(this.viewTypes[r6]);
        goto L28
    L31:
        return;
    L4:
        r0 = this.nextPosition;
        goto L5
    }

    public void addMoreTabs() {
    }

    public boolean canScroll(MotionEvent r1) {
        return true;
    }

    public boolean canScrollBackward(MotionEvent r1) {
        return true;
    }

    public boolean canScrollForward(MotionEvent r1) {
        return canScroll(r1);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int r4) {
        if (r4 != 0) goto L6;
        return false;
    L6:
        if (this.tabsAnimationInProgress == false) goto L8;
    L20:
        return true;
    L8:
        if (this.startedTracking == true) goto L20;
        if (r4 <= 0) goto L12;
        boolean r42 = true;
    L13:
        if (r42 == false) goto L15;
    L16:
        if (r42 == false) goto L20;
        if (this.currentPosition != (this.adapter.getItemCount() - 1)) goto L20;
    L19:
        return false;
    L15:
        if (this.currentPosition == 0) goto L19;
    L12:
        r42 = false;
        goto L13
    }

    public void cancelTouches() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L6;
        r0.computeCurrentVelocity(1000, this.maximumVelocity);
    L6:
        if (this.startedTracking == false) goto L69;
        float r02 = this.viewPages[0].getX();
        this.tabsAnimation = new AnimatorSet();
        if (this.additionalOffset == 0.0f) goto L28;
        float r3 = Math.abs(0.0f);
        boolean r5 = this.animatingForward;
        if (r3 <= 1500.0f) goto L12;
        this.backAnimation = false;
    L37:
        if (this.backAnimation == false) goto L47;
        float r03 = Math.abs(r02);
        boolean r32 = this.animatingForward;
        AnimatorSet r52 = this.tabsAnimation;
        if (r32 == false) goto L43;
        r52.playTogether(new Animator[]{translateAnimator(this.viewPages[0], 0.0f)});
        View r33 = this.viewPages[1];
        if (r33 == null) goto L58;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r33, r33.getMeasuredWidth())});
    L58:
        if (this.nextPosition >= 0) goto L64;
        float r34 = this.backProgress;
        if (this.backAnimation == false) goto L62;
        float r7 = 0.0f;
    L63:
        ValueAnimator r35 = ValueAnimator.ofFloat(new float[]{r34, r7});
        r35.addUpdateListener(new ViewPagerFixed$$ExternalSyntheticLambda4(this));
        this.tabsAnimation.playTogether(new Animator[]{r35});
        goto L64
    L62:
        r7 = 1.0f;
    L64:
        ValueAnimator r36 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r36.addUpdateListener(this.updateTabProgress);
        this.tabsAnimation.playTogether(new Animator[]{r36});
        this.tabsAnimation.setInterpolator(interpolator);
        int r37 = getMeasuredWidth();
        float r53 = r37 / 2;
        float r54 = r53 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (r03 * 1.0f) / r37)) * r53);
        float r38 = Math.abs(0.0f);
        if (r38 <= 0.0f) goto L67;
        int r04 = Math.round(Math.abs(r54 / r38) * 1000.0f) * 4;
    L68:
        this.tabsAnimation.setDuration(Math.max(150, Math.min(r04, MediaError.DetailedErrorCode.TEXT_UNKNOWN)));
        this.tabsAnimation.addListener(new AnonymousClass9(this));
        this.tabsAnimation.start();
        this.tabsAnimationInProgress = true;
        this.startedTracking = false;
        onTabAnimationUpdate(false);
    L72:
        VelocityTracker r05 = this.velocityTracker;
        if (r05 == null) goto L76;
        r05.recycle();
        this.velocityTracker = null;
        return;
    L76:
        return;
    L67:
        r04 = (int) (((r03 / getMeasuredWidth()) + 1.0f) * 100.0f);
        goto L68
    L43:
        r52.playTogether(new Animator[]{translateAnimator(this.viewPages[0], 0.0f)});
        View r39 = this.viewPages[1];
        if (r39 == null) goto L58;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r39, -r39.getMeasuredWidth())});
        goto L58
    L47:
        if (this.nextPosition < 0) goto L56;
        r03 = this.viewPages[0].getMeasuredWidth() - Math.abs(r02);
        boolean r310 = this.animatingForward;
        AnimatorSet r55 = this.tabsAnimation;
        if (r310 == false) goto L53;
        r55.playTogether(new Animator[]{translateAnimator(this.viewPages[0], -r3.getMeasuredWidth())});
        View r311 = this.viewPages[1];
        if (r311 == null) goto L58;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r311, 0.0f)});
        goto L58
    L53:
        r55.playTogether(new Animator[]{translateAnimator(this.viewPages[0], r3.getMeasuredWidth())});
        View r312 = this.viewPages[1];
        if (r312 == null) goto L58;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r312, 0.0f)});
        goto L58
    L56:
        r03 = 0.0f;
        goto L58
    L12:
        View[] r313 = this.viewPages;
        if (r5 == false) goto L23;
        View r314 = r313[1];
        if (r314 != null) goto L17;
        this.backAnimation = false;
        goto L37
    L17:
        if (r314.getX() <= (this.viewPages[0].getMeasuredWidth() >> 1)) goto L19;
        boolean r315 = true;
    L20:
        this.backAnimation = r315;
        goto L37
    L19:
        r315 = false;
        goto L20
    L23:
        if (r313[0].getX() >= (this.viewPages[0].getMeasuredWidth() >> 1)) goto L25;
        boolean r316 = true;
    L26:
        this.backAnimation = r316;
        goto L37
    L25:
        r316 = false;
        goto L26
    L28:
        if (Math.abs(r02) < (this.viewPages[0].getMeasuredWidth() / 3.0f)) goto L30;
    L34:
        boolean r317 = false;
    L35:
        this.backAnimation = r317;
        goto L37
    L30:
        if (Math.abs(0.0f) >= 3500.0f) goto L32;
    L33:
        r317 = true;
        goto L35
    L32:
        if (Math.abs(0.0f) >= Math.abs(0.0f)) goto L34;
    L69:
        this.maybeStartTracking = false;
        TabsView r06 = this.tabsView;
        if (r06 == null) goto L72;
        r06.setEnabled(true);
        goto L72
    }

    public boolean checkTabsAnimationInProgress() {
        if (this.tabsAnimationInProgress == false) goto L33;
        boolean r0 = this.backAnimation;
        View[] r2 = this.viewPages;
        int r3 = -1;
        if (r0 == false) goto L16;
        if (Math.abs(r2[0].getTranslationX()) >= 1.0f) goto L24;
        setTranslationX(this.viewPages[0], 0.0f);
        View[] r02 = this.viewPages;
        View r22 = r02[1];
        if (r22 == null) goto L14;
        int r03 = r02[0].getMeasuredWidth();
        if (this.animatingForward == false) goto L13;
        r3 = 1;
    L13:
        setTranslationX(r22, r03 * r3);
    L14:
        boolean r04 = true;
    L25:
        onTabAnimationUpdate(true);
        if (r04 == false) goto L32;
        AnimatorSet r05 = this.tabsAnimation;
        if (r05 == null) goto L30;
        r05.cancel();
        this.tabsAnimation = null;
    L30:
        this.tabsAnimationInProgress = false;
    L32:
        return this.tabsAnimationInProgress;
    L24:
        r04 = false;
        goto L25
    L16:
        if (Math.abs(r2[1].getTranslationX()) >= 1.0f) goto L24;
        View r06 = this.viewPages[0];
        int r23 = r06.getMeasuredWidth();
        if (this.animatingForward == true) goto L21;
        r3 = 1;
    L21:
        setTranslationX(r06, r23 * r3);
        View r07 = this.viewPages[1];
        if (r07 == null) goto L14;
        setTranslationX(r07, 0.0f);
        goto L14
    L33:
        return false;
    }

    public void clearViews() {
        this.viewsByType.clear();
    }

    public TabsView createTabsView(boolean r7, int r8) {
        AnonymousClass3 r0 = new AnonymousClass3(this, getContext(), r7, r8, this.resourcesProvider);
        this.tabsView = r0;
        r0.tabMarginDp = tabMarginDp();
        this.tabsView.setDelegate(new AnonymousClass4(this));
        fillTabs(false);
        return this.tabsView;
    }

    public void drawForBlur(Canvas r10) {
        int r1 = 0;
    L3:
        View[] r2 = this.viewPages;
        if (r1 >= r2.length) goto L19;
        View r22 = r2[r1];
        if (r22 == null) goto L18;
        if (r22.getVisibility() != 0) goto L18;
        RecyclerListView r23 = findRecyclerView(this.viewPages[r1]);
        if (r23 == null) goto L18;
        int r3 = 0;
    L13:
        if (r3 >= r23.getChildCount()) goto L18;
        View r4 = r23.getChildAt(r3);
        if (r4.getY() >= (AndroidUtilities.dp(203.0f) + AndroidUtilities.dp(100.0f))) goto L17;
        int r5 = r10.save();
        r10.translate(this.viewPages[r1].getX(), ((getY() + this.viewPages[r1].getY()) + r23.getY()) + r4.getY());
        r4.draw(r10);
        r10.restoreToCount(r5);
    L17:
        r3 = r3 + 1;
    L18:
        r1 = r1 + 1;
        goto L3
    }

    public void fillTabs(boolean r5) {
        if (this.adapter == null) goto L22;
        TabsView r0 = this.tabsView;
        if (r0 == null) goto L23;
        r0.removeTabs();
        int r02 = 0;
    L9:
        if (r02 >= this.adapter.getItemCount()) goto L14;
        if (this.adapter.needsTab(r02) == false) goto L13;
        this.tabsView.addTab(this.adapter.getItemId(r02), this.adapter.getItemTitle(r02));
    L13:
        r02 = r02 + 1;
        goto L9
    L14:
        addMoreTabs();
        if (r5 == false) goto L17;
        TransitionManager.beginDelayedTransition(this.tabsView.listView, TransitionExt.createSimpleTransition());
    L17:
        this.tabsView.finishAddingTabs();
        return;
    L23:
        return;
    }

    public float getAvailableTranslationX() {
        return AndroidUtilities.displaySize.x;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public float getCurrentPositionAlpha() {
        View r0 = this.viewPages[0];
        if (r0 != null) goto L5;
    L8:
        return 0.0f;
    L5:
        if (r0.getVisibility() != 0) goto L8;
        return Utilities.clamp(1.0f - Math.abs(this.viewPages[0].getTranslationX() / getAvailableTranslationX()), 1.0f, 0.0f);
    }

    public View getCurrentView() {
        return this.viewPages[0];
    }

    public long getManualScrollDuration() {
        return 540;
    }

    public int getNextPosition() {
        return this.nextPosition;
    }

    public float getNextPositionAlpha() {
        View r0 = this.viewPages[1];
        if (r0 != null) goto L5;
    L8:
        return 0.0f;
    L5:
        if (r0.getVisibility() != 0) goto L8;
        return Utilities.clamp(1.0f - Math.abs(this.viewPages[1].getTranslationX() / getAvailableTranslationX()), 1.0f, 0.0f);
    }

    public float getPositionAnimated() {
        View r0 = this.viewPages[0];
        if (r0 != null) goto L5;
    L7:
        float r1 = 0.0f;
    L8:
        View r02 = this.viewPages[1];
        if (r02 != null) goto L11;
        return r1;
    L11:
        if (r02.getVisibility() == 0) goto L13;
        return r1;
    L13:
        return r1 + (this.nextPosition * Utilities.clamp(1.0f - Math.abs(this.viewPages[1].getTranslationX() / getAvailableTranslationX()), 1.0f, 0.0f));
    L5:
        if (r0.getVisibility() != 0) goto L7;
        r1 = (this.currentPosition * Utilities.clamp(1.0f - Math.abs(this.viewPages[0].getTranslationX() / getAvailableTranslationX()), 1.0f, 0.0f)) + 0.0f;
        goto L8
    }

    public float getPositionVisibility(int r2) {
        if (getMeasuredWidth() != 0) goto L7;
        return MathUtils.clamp(1 - Math.abs(getCurrentPosition() - r2), 0, 1);
    L7:
        return MathUtils.clamp(1.0f - Math.abs(getPositionAnimated() - r2), 0.0f, 1.0f);
    }

    public View[] getViewPages() {
        return this.viewPages;
    }

    public void invalidateBlur() {
    }

    public boolean isCurrentTabFirst() {
        if (this.currentPosition != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isManualScrolling() {
        ValueAnimator r0 = this.manualScrolling;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRunning() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isTouch() {
        return this.startedTracking;
    }

    public void onBack() {
    }

    public boolean onBackProgress(float r1) {
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        TabsView r0 = this.tabsView;
        if (r0 == null) goto L9;
        if (r0.isAnimatingIndicator() == false) goto L9;
        return false;
    L9:
        if (checkTabsAnimationInProgress() == false) goto L12;
        return true;
    L12:
        onTouchEvent(r2);
        return this.startedTracking;
    }

    public void onItemSelected(View r1, View r2, int r3, int r4) {
    }

    public void onScrollEnd() {
    }

    public void onStartTracking() {
    }

    public void onTabAnimationUpdate(boolean r1) {
    }

    public void onTabPageSelected(int r1) {
    }

    public void onTabPageSelected(int r1, boolean r2) {
        onTabPageSelected(r1);
    }

    public void onTabScrollEnd(int r1) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return onTouchEventInternal(r1);
    }

    public boolean onTouchEventInternal(MotionEvent r11) {
        TabsView r0 = this.tabsView;
        if (r0 != null) goto L5;
    L7:
        if (r11 == null) goto L13;
        if (this.velocityTracker != null) goto L11;
        this.velocityTracker = VelocityTracker.obtain();
    L11:
        this.velocityTracker.addMovement(r11);
    L13:
        if (r11 != null) goto L15;
    L31:
        if (r11 != null) goto L33;
    L36:
        if (this.startedTracking == true) goto L45;
        if (r11 == null) goto L45;
        View r3 = findScrollingChild(this, r11.getX(), r11.getY());
        if (r3 == null) goto L45;
        if (r3.canScrollHorizontally(1) == false) goto L43;
    L44:
        return false;
    L43:
        if (r3.canScrollHorizontally(-1) == true) goto L44;
    L45:
        if (r11 == null) goto L54;
        if (r11.getAction() != 0) goto L54;
        if (this.startedTracking == true) goto L54;
        if (this.maybeStartTracking == true) goto L54;
        this.startedTrackingPointerId = r11.getPointerId(0);
        this.maybeStartTracking = true;
        this.startedTrackingX = (int) r11.getX();
        this.startedTrackingY = (int) r11.getY();
    L217:
        if (this.startedTracking == false) goto L219;
    L222:
        return true;
    L219:
        if (this.maybeStartTracking == true) goto L222;
        return false;
    L54:
        if (r11 == null) goto L111;
        if (r11.getAction() != 2) goto L111;
        if (r11.getPointerId(0) != this.startedTrackingPointerId) goto L111;
        int r32 = (int) ((r11.getX() - this.startedTrackingX) + this.additionalOffset);
        int r6 = Math.abs(((int) r11.getY()) - this.startedTrackingY);
        if (this.startedTracking == false) goto L84;
        boolean r7 = this.animatingForward;
        if (r7 == false) goto L64;
        if (r32 <= 0) goto L64;
    L66:
        if (r32 >= 0) goto L68;
        boolean r72 = true;
    L70:
        if (prepareForMoving(r11, r72) == true) goto L84;
        this.maybeStartTracking = true;
        this.startedTracking = false;
        setTranslationX(this.viewPages[0], 0.0f);
        View[] r02 = this.viewPages;
        View r73 = r02[1];
        if (r73 != null) goto L74;
    L79:
        this.nextPosition = 0;
        this.currentProgress = 1.0f;
        TabsView r03 = this.tabsView;
        if (r03 == null) goto L82;
        r03.selectTab(0, this.currentPosition, 1.0f);
    L82:
        onTabAnimationUpdate(false);
        goto L84
    L74:
        if (this.animatingForward == false) goto L77;
        int r04 = r02[0].getMeasuredWidth();
    L78:
        setTranslationX(r73, r04);
        goto L79
    L77:
        r04 = -r02[0].getMeasuredWidth();
        goto L78
    L68:
        r72 = false;
    L64:
        if (r7 == true) goto L84;
        if (r32 < 0) goto L66;
    L84:
        if (this.maybeStartTracking == false) goto L96;
        if (this.startedTracking == true) goto L96;
        int r05 = (int) (r11.getX() - this.startedTrackingX);
        if (Math.abs(r05) < this.touchSlop) goto L217;
        if (Math.abs(r05) <= r6) goto L217;
        if (r32 >= 0) goto L93;
        boolean r06 = true;
    L94:
        prepareForMoving(r11, r06);
        goto L217
    L93:
        r06 = false;
    L96:
        if (this.startedTracking == false) goto L217;
        float r112 = Math.abs(r32) / this.viewPages[0].getMeasuredWidth();
        if (this.nextPosition != (-1)) goto L100;
        this.backProgress = r112;
        onBackProgress(r112);
    L106:
        float r5 = 1.0f - r112;
        this.currentProgress = r5;
        TabsView r113 = this.tabsView;
        if (r113 == null) goto L109;
        r113.selectTab(this.nextPosition, this.currentPosition, r5);
    L109:
        onTabAnimationUpdate(false);
        goto L217
    L100:
        setTranslationX(this.viewPages[0], r32);
        View r4 = this.viewPages[1];
        if (r4 == null) goto L106;
        if (this.animatingForward == false) goto L105;
        setTranslationX(r4, r0[0].getMeasuredWidth() + r32);
        goto L106
    L105:
        setTranslationX(r4, r32 - r0[0].getMeasuredWidth());
    L111:
        if (r11 != null) goto L113;
    L120:
        VelocityTracker r62 = this.velocityTracker;
        if (r62 == null) goto L123;
        r62.computeCurrentVelocity(1000, this.maximumVelocity);
    L123:
        if (r11 != null) goto L125;
    L137:
        float r42 = 0.0f;
        float r63 = 0.0f;
    L139:
        if (this.startedTracking == false) goto L210;
        float r114 = this.viewPages[0].getX();
        this.tabsAnimation = new AnimatorSet();
        if (this.additionalOffset == 0.0f) goto L169;
        float r64 = Math.abs(r42);
        float r74 = AppUtils.getSwipeVelocity();
        boolean r75 = this.animatingForward;
        if (r64 <= r74) goto L153;
        if (r75 == false) goto L150;
        if (r42 <= 0.0f) goto L148;
    L147:
        boolean r65 = true;
    L152:
        this.backAnimation = r65;
    L178:
        if (this.backAnimation == false) goto L188;
        float r115 = Math.abs(r114);
        boolean r66 = this.animatingForward;
        AnimatorSet r76 = this.tabsAnimation;
        if (r66 == false) goto L184;
        r76.playTogether(new Animator[]{translateAnimator(this.viewPages[0], 0.0f)});
        View r67 = this.viewPages[1];
        if (r67 == null) goto L199;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r67, r67.getMeasuredWidth())});
    L199:
        if (this.nextPosition >= 0) goto L205;
        float r68 = this.backProgress;
        if (this.backAnimation == false) goto L203;
        float r77 = 0.0f;
    L204:
        ValueAnimator r69 = ValueAnimator.ofFloat(new float[]{r68, r77});
        r69.addUpdateListener(new ViewPagerFixed$$ExternalSyntheticLambda1(this));
        this.tabsAnimation.playTogether(new Animator[]{r69});
        goto L205
    L203:
        r77 = 1.0f;
    L205:
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r33.addUpdateListener(this.updateTabProgress);
        this.tabsAnimation.playTogether(new Animator[]{r33});
        this.tabsAnimation.setInterpolator(interpolator);
        int r34 = getMeasuredWidth();
        float r610 = r34 / 2;
        float r611 = r610 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (r115 * 1.0f) / r34)) * r610);
        float r35 = Math.abs(r42);
        if (r35 <= 0.0f) goto L208;
        int r116 = Math.round(Math.abs(r611 / r35) * 1000.0f) * 4;
    L209:
        this.tabsAnimation.setDuration(Math.max(150, Math.min(r116, MediaError.DetailedErrorCode.TEXT_UNKNOWN)));
        this.tabsAnimation.addListener(new AnonymousClass7(this));
        this.tabsAnimation.start();
        this.tabsAnimationInProgress = true;
        this.startedTracking = false;
        onTabAnimationUpdate(false);
    L213:
        VelocityTracker r117 = this.velocityTracker;
        if (r117 == null) goto L217;
        r117.recycle();
        this.velocityTracker = null;
        goto L217
    L208:
        r116 = (int) (((r115 / getMeasuredWidth()) + 1.0f) * 100.0f);
        goto L209
    L184:
        r76.playTogether(new Animator[]{translateAnimator(this.viewPages[0], 0.0f)});
        View r612 = this.viewPages[1];
        if (r612 == null) goto L199;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r612, -r612.getMeasuredWidth())});
        goto L199
    L188:
        if (this.nextPosition < 0) goto L197;
        r115 = this.viewPages[0].getMeasuredWidth() - Math.abs(r114);
        boolean r613 = this.animatingForward;
        AnimatorSet r78 = this.tabsAnimation;
        if (r613 == false) goto L194;
        r78.playTogether(new Animator[]{translateAnimator(this.viewPages[0], -r6.getMeasuredWidth())});
        View r614 = this.viewPages[1];
        if (r614 == null) goto L199;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r614, 0.0f)});
        goto L199
    L194:
        r78.playTogether(new Animator[]{translateAnimator(this.viewPages[0], r6.getMeasuredWidth())});
        View r615 = this.viewPages[1];
        if (r615 == null) goto L199;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r615, 0.0f)});
        goto L199
    L197:
        r115 = 0.0f;
    L148:
        r65 = false;
        goto L152
    L150:
        if (r42 >= 0.0f) goto L148;
    L153:
        View[] r616 = this.viewPages;
        if (r75 == false) goto L164;
        View r617 = r616[1];
        if (r617 != null) goto L158;
        this.backAnimation = false;
        goto L178
    L158:
        if (r617.getX() <= (this.viewPages[0].getMeasuredWidth() >> 1)) goto L160;
        boolean r618 = true;
    L161:
        this.backAnimation = r618;
        goto L178
    L160:
        r618 = false;
        goto L161
    L164:
        if (r616[0].getX() >= (this.viewPages[0].getMeasuredWidth() >> 1)) goto L166;
        boolean r619 = true;
    L167:
        this.backAnimation = r619;
        goto L178
    L166:
        r619 = false;
        goto L167
    L169:
        if (Math.abs(r114) < (this.viewPages[0].getMeasuredWidth() / 3.0f)) goto L171;
    L175:
        boolean r620 = false;
    L176:
        this.backAnimation = r620;
        goto L178
    L171:
        if (Math.abs(r42) >= AppUtils.getSwipeVelocity()) goto L173;
    L174:
        r620 = true;
        goto L176
    L173:
        if (Math.abs(r42) >= Math.abs(r63)) goto L175;
    L210:
        this.maybeStartTracking = false;
        TabsView r118 = this.tabsView;
        if (r118 == null) goto L213;
        r118.setEnabled(true);
        goto L213
    L125:
        if (r11.getAction() == 3) goto L137;
        r42 = this.velocityTracker.getXVelocity();
        r63 = this.velocityTracker.getYVelocity();
        if (this.startedTracking == true) goto L139;
        if (Math.abs(r42) < AppUtils.getSwipeVelocity()) goto L139;
        if (Math.abs(r42) <= Math.abs(r63)) goto L139;
        if (r42 >= 0.0f) goto L135;
        boolean r79 = true;
    L136:
        prepareForMoving(r11, r79);
        goto L139
    L135:
        r79 = false;
        goto L136
    L113:
        if (r11.getPointerId(0) != this.startedTrackingPointerId) goto L217;
        if (r11.getAction() == 3) goto L120;
        if (r11.getAction() == 1) goto L120;
        if (r11.getAction() != 6) goto L217;
    L33:
        if (r11.getAction() != 0) goto L36;
        this.additionalOffset = 0.0f;
        goto L36
    L15:
        if (r11.getAction() != 0) goto L31;
        if (checkTabsAnimationInProgress() == false) goto L31;
        this.startedTracking = true;
        onStartTracking();
        this.startedTrackingPointerId = r11.getPointerId(0);
        int r36 = (int) r11.getX();
        this.startedTrackingX = r36;
        boolean r43 = this.animatingForward;
        View[] r52 = this.viewPages;
        if (r43 == false) goto L25;
        if (r36 >= (r52[0].getMeasuredWidth() + this.viewPages[0].getTranslationX())) goto L23;
        this.additionalOffset = this.viewPages[0].getTranslationX();
    L30:
        this.tabsAnimation.removeAllListeners();
        this.tabsAnimation.cancel();
        this.tabsAnimationInProgress = false;
        goto L36
    L23:
        swapViews();
        this.animatingForward = false;
        this.additionalOffset = this.viewPages[0].getTranslationX();
        goto L30
    L25:
        if (r52[1] == null) goto L30;
        if (r36 >= (r4.getMeasuredWidth() + this.viewPages[1].getTranslationX())) goto L29;
        swapViews();
        this.animatingForward = true;
        this.additionalOffset = this.viewPages[0].getTranslationX();
        goto L30
    L29:
        this.additionalOffset = this.viewPages[0].getTranslationX();
        goto L30
    L5:
        if (TabsView.m13549$$Nest$fgetanimatingIndicator(r0) == false) goto L7;
        return false;
    }

    public void rebuild(boolean r9) {
        onTouchEvent(null);
        if (this.adapter.hasStableId() == true) goto L5;
        r9 = false;
    L5:
        AnimatorSet r1 = this.tabsAnimation;
        if (r1 == null) goto L8;
        r1.cancel();
        this.tabsAnimation = null;
    L8:
        View r12 = this.viewPages[1];
        if (r12 == null) goto L11;
        removeView(r12);
        this.viewPages[1] = null;
    L11:
        View[] r13 = this.viewPages;
        View r4 = r13[0];
        r13[1] = r4;
        if (r4 != null) goto L14;
    L17:
        int r14 = 0;
    L19:
        if (this.adapter.getItemCount() != 0) goto L28;
        View r92 = this.viewPages[1];
        if (r92 == null) goto L23;
        removeView(r92);
        this.viewPages[1] = null;
    L23:
        View r93 = this.viewPages[0];
        if (r93 == null) goto L61;
        removeView(r93);
        this.viewPages[0] = null;
        return;
    L61:
        return;
    L28:
        if (this.currentPosition <= (this.adapter.getItemCount() - 1)) goto L31;
        this.currentPosition = this.adapter.getItemCount() - 1;
    L31:
        if (this.currentPosition >= 0) goto L33;
        this.currentPosition = 0;
    L33:
        this.viewTypes[0] = this.adapter.getItemViewType(this.currentPosition);
        this.viewPages[0] = this.adapter.createView(this.viewTypes[0]);
        this.adapter.bindView(this.viewPages[0], this.currentPosition, this.viewTypes[0]);
        addView(this.viewPages[0]);
        this.viewPages[0].setVisibility(0);
        if (this.viewPages[0].getTag() != null) goto L36;
        int r42 = 0;
    L37:
        if (r42 != r14) goto L39;
        r9 = false;
    L39:
        if (r9 == false) goto L41;
        TabsView.m13577$$Nest$msaveFromValues(this.tabsView);
    L41:
        fillTabs(r9);
        if (r9 == false) goto L57;
        this.tabsAnimation = new AnimatorSet();
        View r94 = this.viewPages[1];
        if (r94 == null) goto L46;
        setTranslationX(r94, 0.0f);
    L46:
        View r95 = this.viewPages[0];
        if (r95 == null) goto L49;
        setTranslationX(r95, -getMeasuredWidth());
    L49:
        View r96 = this.viewPages[1];
        if (r96 == null) goto L52;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r96, getMeasuredWidth())});
    L52:
        View r97 = this.viewPages[0];
        if (r97 == null) goto L55;
        this.tabsAnimation.playTogether(new Animator[]{translateAnimator(r97, 0.0f)});
    L55:
        onTabAnimationUpdate(true);
        TabsView.m13576$$Nest$fputindicatorProgress2(this.tabsView, 0.0f);
        this.tabsView.listView.invalidateViews();
        this.tabsView.invalidate();
        ValueAnimator r98 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r98.addUpdateListener(new ViewPagerFixed$$ExternalSyntheticLambda3(this));
        this.tabsAnimation.playTogether(new Animator[]{r98});
        this.tabsAnimation.setInterpolator(interpolator);
        this.tabsAnimation.setDuration(220);
        this.tabsAnimation.addListener(new AnonymousClass8(this));
        this.tabsView.setEnabled(false);
        this.tabsAnimationInProgress = true;
        this.tabsAnimation.start();
        return;
    L57:
        View r99 = this.viewPages[1];
        if (r99 == null) goto L62;
        removeView(r99);
        this.viewPages[1] = null;
        return;
    L62:
        return;
    L36:
        r42 = ((Integer) this.viewPages[0].getTag()).intValue();
        goto L37
    L14:
        if (r4.getTag() == null) goto L17;
        r14 = ((Integer) this.viewPages[1].getTag()).intValue();
        goto L19
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r2) {
        if (this.allowDisallowInterceptTouch == true) goto L5;
    L9:
        super.requestDisallowInterceptTouchEvent(r2);
        return;
    L5:
        if (this.maybeStartTracking == false) goto L9;
        if (this.startedTracking == true) goto L9;
        onTouchEvent(null);
        goto L9
    }

    public void resetTouch() {
        if (this.startedTracking == true) goto L5;
        return;
    L5:
        this.maybeStartTracking = true;
        this.startedTracking = false;
        setTranslationX(this.viewPages[0], 0.0f);
        View[] r2 = this.viewPages;
        View r0 = r2[1];
        if (r0 != null) goto L8;
    L13:
        this.nextPosition = 0;
        this.currentProgress = 1.0f;
        TabsView r22 = this.tabsView;
        if (r22 == null) goto L16;
        r22.selectTab(0, this.currentPosition, 1.0f);
    L16:
        onTabAnimationUpdate(false);
        return;
    L8:
        if (this.animatingForward == false) goto L11;
        int r23 = r2[0].getMeasuredWidth();
    L12:
        setTranslationX(r0, r23);
        goto L13
    L11:
        r23 = -r2[0].getMeasuredWidth();
        goto L12
    }

    public boolean scrollToPosition(int r4) {
        int r1 = 0;
        if (r4 == this.currentPosition) goto L24;
        ValueAnimator r0 = this.manualScrolling;
        if (r0 != null) goto L7;
    L9:
        if (r0 == null) goto L12;
        r0.cancel();
        this.manualScrolling = null;
    L12:
        if (this.currentPosition >= r4) goto L14;
        boolean r02 = true;
    L15:
        this.animatingForward = r02;
        this.nextPosition = r4;
        updateViewForIndex(1);
        onTabPageSelected(r4, r02);
        View r42 = this.viewPages[0];
        if (r42 == null) goto L18;
        r1 = r42.getMeasuredWidth();
    L18:
        View[] r43 = this.viewPages;
        if (r02 == false) goto L21;
        setTranslationX(r43[1], r1);
    L22:
        ValueAnimator r44 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.manualScrolling = r44;
        r44.addUpdateListener(new ViewPagerFixed$$ExternalSyntheticLambda2(this));
        this.manualScrolling.addListener(new AnonymousClass2(this));
        this.manualScrolling.setDuration(getManualScrollDuration());
        this.manualScrolling.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.manualScrolling.start();
        return true;
    L21:
        setTranslationX(r43[1], -r1);
        goto L22
    L14:
        r02 = false;
        goto L15
    L7:
        if (this.nextPosition != r4) goto L9;
    L24:
        return false;
    }

    public void setAdapter(Adapter r5) {
        this.adapter = r5;
        this.viewTypes[0] = r5.getItemViewType(this.currentPosition);
        this.viewPages[0] = r5.createView(this.viewTypes[0]);
        if (this.viewPages[0] == null) goto L5;
    L7:
        r5.bindView(this.viewPages[0], this.currentPosition, this.viewTypes[0]);
        addView(this.viewPages[0]);
        this.viewPages[0].setVisibility(0);
        fillTabs(false);
        return;
    L5:
        if (this.currentPosition == 0) goto L7;
        this.currentPosition = 0;
        this.viewTypes[0] = r5.getItemViewType(0);
        this.viewPages[0] = r5.createView(this.viewTypes[0]);
        goto L7
    }

    public void setAllowDisallowInterceptTouch(boolean r1) {
        this.allowDisallowInterceptTouch = r1;
    }

    public void setPosition(int r6) {
        if (this.adapter != null) goto L5;
        this.currentPosition = r6;
        onTabAnimationUpdate(false);
    L5:
        AnimatorSet r0 = this.tabsAnimation;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        View r02 = this.viewPages[1];
        if (r02 == null) goto L11;
        this.viewsByType.put(this.viewTypes[1], r02);
        removeView(this.viewPages[1]);
        this.viewPages[1] = null;
    L11:
        int r03 = this.currentPosition;
        if (r03 == r6) goto L18;
        this.currentPosition = r6;
        this.nextPosition = 0;
        this.currentProgress = 1.0f;
        View r62 = this.viewPages[0];
        updateViewForIndex(0);
        onItemSelected(this.viewPages[0], r62, this.currentPosition, r03);
        setTranslationX(this.viewPages[0], 0.0f);
        TabsView r63 = this.tabsView;
        if (r63 == null) goto L16;
        r63.selectTab(this.currentPosition, this.nextPosition, this.currentProgress);
    L16:
        onTabAnimationUpdate(true);
        return;
    }

    public void setTranslationX(View r1, float r2) {
        r1.setTranslationX(r2);
    }

    public void swapViews() {
        View[] r0 = this.viewPages;
        View r2 = r0[0];
        View r4 = r0[1];
        r0[0] = r4;
        r0[1] = r2;
        int r02 = this.currentPosition;
        int r5 = this.nextPosition;
        this.currentPosition = r5;
        this.nextPosition = r02;
        this.currentProgress = 1.0f - this.currentProgress;
        int[] r6 = this.viewTypes;
        int r7 = r6[0];
        r6[0] = r6[1];
        r6[1] = r7;
        onItemSelected(r4, r2, r5, r02);
    }

    public int tabMarginDp() {
        return 16;
    }

    public ValueAnimator translateAnimator(View r4, float r5) {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{r4.getTranslationX(), r5});
        r0.addUpdateListener(new AnonymousClass5(this, r4));
        r0.addListener(new AnonymousClass6(this, r4, r5));
        return r0;
    }

    public void updateCurrent() {
        if (this.viewTypes[0] == this.adapter.getItemViewType(this.currentPosition)) goto L9;
        updateViewForIndex(0);
        View r0 = this.viewPages[1];
        if (r0 == null) goto L7;
        this.viewsByType.put(this.viewTypes[1], r0);
        removeView(this.viewPages[1]);
        this.viewPages[1] = null;
    L7:
        setTranslationX(this.viewPages[0], 0.0f);
        onTabAnimationUpdate(true);
        return;
    }
}
