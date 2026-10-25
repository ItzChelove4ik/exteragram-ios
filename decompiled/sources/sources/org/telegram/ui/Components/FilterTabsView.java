package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Property;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.TabIconsMode;
import com.exteragram.messenger.utils.ui.FolderIcons;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Stories.recorder.HintView2;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class FilterTabsView extends FrameLayout {
    private final Property<FilterTabsView, Float> COLORS;
    private int aActiveTextColorKey;
    private int aBackgroundColorKey;
    private int aTabLineColorKey;
    private int aUnactiveTextColorKey;
    private int activeTextColorKey;
    private final ListAdapter adapter;
    private int additionalTabWidth;
    private int allTabsWidth;
    private boolean animatingIndicator;
    private float animatingIndicatorProgress;
    private final Runnable animationRunnable;
    private float animationTime;
    private float animationValue;
    private int backgroundColorKey;
    BlurredBackgroundDrawable blurredBackgroundDrawable;
    private final Path clipPath;
    private AnimatorSet colorChangeAnimator;
    private final Paint counterPaint;
    private int currentPosition;
    private FilterTabsViewDelegate delegate;
    private final Paint deletePaint;
    private float editingAnimationProgress;
    private boolean editingForwardAnimation;
    private float editingStartAnimationProgress;
    private ColorFilter emojiColorFilter;
    private final SparseIntArray idToPosition;
    private boolean ignoreLayout;
    private final CubicBezierInterpolator interpolator;
    private boolean invalidated;
    private boolean isEditing;
    private boolean isStaticAllChats;
    DefaultItemAnimator itemAnimator;
    private long lastAnimationTime;
    private long lastEditingAnimationTime;
    private final LinearLayoutManager layoutManager;
    private final RecyclerListView listView;
    private final int listViewPaddingH;
    private Drawable lockDrawable;
    private int lockDrawableColor;
    private int manualScrollingToId;
    private int manualScrollingToPosition;
    private int oldAnimatedTab;
    private boolean orderChanged;
    private final SparseIntArray positionToCount;
    private final SparseIntArray positionToId;
    private final SparseIntArray positionToStableId;
    private final SparseIntArray positionToWidth;
    private final SparseIntArray positionToX;
    private int prevLayoutWidth;
    private int previousId;
    private int previousPosition;
    private final Theme.ResourcesProvider resourcesProvider;
    private int scrollingToChild;
    private int selectedTabId;
    private int selectorColorKey;
    private final GradientDrawable selectorDrawable;
    private int tabLineColorKey;
    private final ArrayList<Tab> tabs;
    private final TextPaint textCounterPaint;
    public final TextPaint textPaint;
    private int unactiveTextColorKey;

    /* renamed from: org.telegram.ui.Components.FilterTabsView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FilterTabsView this$0;

        public AnonymousClass1(FilterTabsView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FilterTabsView.m10589$$Nest$fgetanimatingIndicator(this.this$0) == false) goto L20;
            long r0 = SystemClock.elapsedRealtime() - FilterTabsView.m10604$$Nest$fgetlastAnimationTime(this.this$0);
            if (r0 <= 17) goto L8;
            r0 = 17;
        L8:
            FilterTabsView r2 = this.this$0;
            FilterTabsView.m10626$$Nest$fputanimationTime(r2, FilterTabsView.m10592$$Nest$fgetanimationTime(r2) + (r0 / 320.0f));
            FilterTabsView r02 = this.this$0;
            r02.setAnimationIdicatorProgress(FilterTabsView.m10602$$Nest$fgetinterpolator(r02).getInterpolation(FilterTabsView.m10592$$Nest$fgetanimationTime(this.this$0)));
            if (FilterTabsView.m10592$$Nest$fgetanimationTime(this.this$0) <= 1.0f) goto L11;
            FilterTabsView.m10626$$Nest$fputanimationTime(this.this$0, 1.0f);
        L11:
            float r03 = FilterTabsView.m10592$$Nest$fgetanimationTime(this.this$0);
            FilterTabsView r22 = this.this$0;
            if (r03 >= 1.0f) goto L15;
            AndroidUtilities.runOnUIThread(FilterTabsView.m10591$$Nest$fgetanimationRunnable(r22));
            return;
        L15:
            FilterTabsView.m10625$$Nest$fputanimatingIndicator(r22, false);
            this.this$0.setEnabled(true);
            if (FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0) == null) goto L19;
            FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0).onPageScrolled(1.0f);
            return;
        L19:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.FilterTabsView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimationProperties.FloatProperty<FilterTabsView> {
        final /* synthetic */ FilterTabsView this$0;

        public AnonymousClass2(FilterTabsView r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(FilterTabsView r1) {
            return Float.valueOf(FilterTabsView.m10593$$Nest$fgetanimationValue(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((FilterTabsView) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(FilterTabsView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(FilterTabsView r4, float r5) {
            FilterTabsView.m10627$$Nest$fputanimationValue(this.this$0, r5);
            int r0 = Theme.getColor(FilterTabsView.m10616$$Nest$fgettabLineColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            int r1 = Theme.getColor(FilterTabsView.m10584$$Nest$fgetaTabLineColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            FilterTabsView.m10615$$Nest$fgetselectorDrawable(this.this$0).setColor(ColorUtils.blendARGB(r0, r1, r5));
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).invalidateViews();
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).invalidate();
            r4.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.FilterTabsView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ FilterTabsView this$0;

        public AnonymousClass3(FilterTabsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(View r1) {
            if (this.this$0.isEnabled() == true) goto L5;
            return false;
        L5:
            if (FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0).canPerformActions() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean canHighlightChildAt(View r4, float r5, float r6) {
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == false) goto L11;
            TabView r0 = (TabView) r4;
            float r1 = AndroidUtilities.dp(6.0f);
            if ((TabView.m10645$$Nest$fgetrect(r0).left - r1) >= r5) goto L11;
            if ((TabView.m10645$$Nest$fgetrect(r0).right + r1) <= r5) goto L11;
            return false;
        L11:
            return super.canHighlightChildAt(r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.FilterTabsView$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ FilterTabsView this$0;

        /* renamed from: org.telegram.ui.Components.FilterTabsView$4$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass4 this$1;
            final /* synthetic */ TabView val$tabView;

            public AnonymousClass1(AnonymousClass4 r1, TabView r2) {
                this.this$1 = r1;
                this.val$tabView = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                this.val$tabView.clearTransitionParams();
            }
        }

        /* renamed from: $r8$lambda$DghHiTLqtaEB-diK1Uy8XexT6xo, reason: not valid java name */
        public static /* synthetic */ void m10640$r8$lambda$DghHiTLqtaEBdiK1Uy8XexT6xo(TabView r0, ValueAnimator r1) {
            r0.changeProgress = ((Float) r1.getAnimatedValue()).floatValue();
            r0.invalidate();
        }

        public static /* synthetic */ void $r8$lambda$qSYMhCcDX4c0Jgxkz86d1appNsM(AnonymousClass4 r0, ValueAnimator r1) {
            r0.lambda$runPendingAnimations$0(r1);
        }

        public AnonymousClass4(FilterTabsView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$runPendingAnimations$0(ValueAnimator r1) {
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).invalidate();
            this.this$0.invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
        public boolean animateMove(RecyclerView.ViewHolder r9, RecyclerView.ItemAnimator.ItemHolderInfo r10, int r11, int r12, int r13, int r14) {
            View r0 = r9.itemView;
            if ((r0 instanceof TabView) == false) goto L20;
            int r3 = r11 + ((int) r0.getTranslationX());
            int r4 = r12 + ((int) r9.itemView.getTranslationY());
            resetAnimation(r9);
            int r102 = r13 - r3;
            int r112 = r14 - r4;
            if (r102 == 0) goto L7;
            r0.setTranslationX(-r102);
        L7:
            if (r112 == 0) goto L9;
            r0.setTranslationY(-r112);
        L9:
            TabView r122 = (TabView) r9.itemView;
            boolean r02 = r122.animateChange();
            if (r02 == false) goto L12;
            r122.changeProgress = 0.0f;
            r122.animateChange = true;
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).invalidate();
            this.this$0.invalidate();
        L12:
            if (r102 != 0) goto L17;
            if (r112 != 0) goto L17;
            if (r02 == true) goto L17;
            dispatchMoveFinished(r9);
            return false;
        L17:
            this.mPendingMoves.add(new DefaultItemAnimator.MoveInfo(r9, r3, r4, r13, r14));
            return true;
        L20:
            return super.animateMove(r9, r10, r11, r12, r13, r14);
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void animateMoveImpl(RecyclerView.ViewHolder r2, DefaultItemAnimator.MoveInfo r3) {
            super.animateMoveImpl(r2, r3);
            View r22 = r2.itemView;
            if ((r22 instanceof TabView) == false) goto L11;
            TabView r23 = (TabView) r22;
            if (r23.animateChange == false) goto L12;
            ValueAnimator r32 = r23.changeAnimator;
            if (r32 == null) goto L9;
            r32.removeAllListeners();
            r23.changeAnimator.removeAllUpdateListeners();
            r23.changeAnimator.cancel();
        L9:
            ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r33.addUpdateListener(new FilterTabsView$4$$ExternalSyntheticLambda1(r23));
            r33.addListener(new AnonymousClass1(this, r23));
            r23.changeAnimator = r33;
            r33.setDuration(getMoveDuration());
            r33.start();
            return;
        L12:
            return;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void endAnimation(RecyclerView.ViewHolder r2) {
            super.endAnimation(r2);
            r2.itemView.setTranslationX(0.0f);
            View r1 = r2.itemView;
            if ((r1 instanceof TabView) == false) goto L6;
            ((TabView) r1).clearTransitionParams();
            return;
        }

        @Override // androidx.recyclerview.widget.SimpleItemAnimator
        public void onMoveFinished(RecyclerView.ViewHolder r2) {
            super.onMoveFinished(r2);
            r2.itemView.setTranslationX(0.0f);
            View r1 = r2.itemView;
            if ((r1 instanceof TabView) == false) goto L6;
            ((TabView) r1).clearTransitionParams();
            return;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void runPendingAnimations() {
            boolean r0 = this.mPendingRemovals.isEmpty();
            boolean r1 = this.mPendingMoves.isEmpty();
            boolean r2 = this.mPendingChanges.isEmpty();
            boolean r3 = this.mPendingAdditions.isEmpty();
            if (r0 == false) goto L7;
            if (r1 == false) goto L7;
            if (r3 == false) goto L7;
            if (r2 == false) goto L7;
        L8:
            super.runPendingAnimations();
            return;
        L7:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.1f});
            r02.addUpdateListener(new FilterTabsView$4$$ExternalSyntheticLambda0(this));
            r02.setDuration(getMoveDuration());
            r02.start();
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.FilterTabsView$5, reason: invalid class name */
    public class AnonymousClass5 extends LinearLayoutManager {
        final /* synthetic */ FilterTabsView this$0;

        /* renamed from: org.telegram.ui.Components.FilterTabsView$5$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1, Context r2) {
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

        public AnonymousClass5(FilterTabsView r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollHorizontallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
            if (FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0).isTabMenuVisible() == false) goto L6;
            r2 = 0;
        L6:
            return super.scrollHorizontallyBy(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.FilterTabsView$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ FilterTabsView this$0;

        public AnonymousClass6(FilterTabsView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).invalidate();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.FilterTabsView$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ FilterTabsView this$0;

        public AnonymousClass7(FilterTabsView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            FilterTabsView r22 = this.this$0;
            FilterTabsView.m10637$$Nest$fputtabLineColorKey(r22, FilterTabsView.m10584$$Nest$fgetaTabLineColorKey(r22));
            FilterTabsView r23 = this.this$0;
            FilterTabsView.m10628$$Nest$fputbackgroundColorKey(r23, FilterTabsView.m10583$$Nest$fgetaBackgroundColorKey(r23));
            FilterTabsView r24 = this.this$0;
            FilterTabsView.m10624$$Nest$fputactiveTextColorKey(r24, FilterTabsView.m10582$$Nest$fgetaActiveTextColorKey(r24));
            FilterTabsView r25 = this.this$0;
            FilterTabsView.m10638$$Nest$fputunactiveTextColorKey(r25, FilterTabsView.m10585$$Nest$fgetaUnactiveTextColorKey(r25));
            FilterTabsView.m10622$$Nest$fputaTabLineColorKey(this.this$0, -1);
            FilterTabsView.m10620$$Nest$fputaActiveTextColorKey(this.this$0, -1);
            FilterTabsView.m10623$$Nest$fputaUnactiveTextColorKey(this.this$0, -1);
            FilterTabsView.m10621$$Nest$fputaBackgroundColorKey(this.this$0, -1);
        }
    }

    public interface FilterTabsViewDelegate {
        boolean canPerformActions();

        boolean didSelectTab(TabView r1, boolean r2);

        int getTabCounter(int r1);

        boolean isTabMenuVisible();

        void onDeletePressed(int r1);

        void onPageReorder(int r1, int r2);

        void onPageScrolled(float r1);

        void onPageSelected(Tab r1, boolean r2);

        void onSamePageSelected();

        default void onTabSelected(Tab r1, boolean r2, boolean r3) {
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final Context mContext;
        final /* synthetic */ FilterTabsView this$0;

        public ListAdapter(FilterTabsView r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FilterTabsView.m10617$$Nest$fgettabs(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).get(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        public void moveElementToStart(int r8) {
            int r0 = FilterTabsView.m10617$$Nest$fgettabs(this.this$0).size();
            if (r8 < 0) goto L36;
            if (r8 >= r0) goto L37;
            ArrayList<MessagesController.DialogFilter> r02 = MessagesController.getInstance(UserConfig.selectedAccount).getDialogFilters();
            int r1 = FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).get(r8);
            int r2 = ((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r8)).id;
            int r3 = r8 - 1;
        L7:
            if (r3 < 0) goto L9;
            FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).put(r3 + 1, FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).get(r3));
            r3 = r3 - 1;
            goto L7
        L9:
            MessagesController.DialogFilter r32 = r02.remove(r8);
            r32.order = 0;
            r02.add(0, r32);
            FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).put(0, r1);
            FilterTabsView.m10617$$Nest$fgettabs(this.this$0).add(0, (Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).remove(r8));
            ((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(0)).id = r2;
            int r12 = 0;
        L10:
            if (r12 > r8) goto L12;
            ((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r12)).id = r12;
            r02.get(r12).order = r12;
            r12 = r12 + 1;
            goto L10
        L12:
            int r03 = 0;
        L13:
            if (r03 > r8) goto L29;
            if (FilterTabsView.m10596$$Nest$fgetcurrentPosition(this.this$0) != r03) goto L22;
            FilterTabsView r13 = this.this$0;
            if (r03 != r8) goto L19;
            int r33 = 0;
        L20:
            FilterTabsView.m10636$$Nest$fputselectedTabId(r13, r33);
            FilterTabsView.m10629$$Nest$fputcurrentPosition(r13, r33);
            goto L22
        L19:
            r33 = r03 + 1;
        L22:
            if (FilterTabsView.m10612$$Nest$fgetpreviousPosition(this.this$0) != r03) goto L28;
            FilterTabsView r14 = this.this$0;
            if (r03 != r8) goto L26;
            int r34 = 0;
        L27:
            FilterTabsView.m10634$$Nest$fputpreviousId(r14, r34);
            FilterTabsView.m10635$$Nest$fputpreviousPosition(r14, r34);
            goto L28
        L26:
            r34 = r03 + 1;
        L28:
            r03 = r03 + 1;
            goto L13
        L29:
            notifyItemMoved(r8, 0);
            FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0).onPageReorder(((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r8)).id, r2);
            FilterTabsView.m10639$$Nest$mupdateTabsWidths(this.this$0);
            FilterTabsView.m10633$$Nest$fputorderChanged(this.this$0, true);
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).setItemAnimator(this.this$0.itemAnimator);
            return;
        L37:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            TabView r22 = (TabView) r2.itemView;
            if (TabView.m10644$$Nest$fgetcurrentTab(r22) == null) goto L5;
            int r0 = r22.getId();
        L6:
            r22.setTab((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r3), r3);
            if (r0 != r22.getId()) goto L9;
            return;
        L9:
            if (TabView.m10644$$Nest$fgetcurrentTab(r22).isLocked == false) goto L11;
            float r1 = 1.0f;
        L12:
            TabView.m10649$$Nest$fputprogressToLocked(r22, r1);
            return;
        L11:
            r1 = 0.0f;
            goto L12
        L5:
            r0 = -1;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new TabView(this.this$0, this.mContext));
        }

        public void swapElements(int r8, int r9) {
            int r0 = FilterTabsView.m10617$$Nest$fgettabs(this.this$0).size();
            if (r8 < 0) goto L40;
            if (r9 < 0) goto L41;
            if (r8 >= r0) goto L42;
            if (r9 >= r0) goto L43;
            ArrayList<MessagesController.DialogFilter> r02 = MessagesController.getInstance(UserConfig.selectedAccount).getDialogFilters();
            if (ExteraConfig.getHideAllChats() == false) goto L23;
            int r1 = 0;
            int r2 = 0;
        L12:
            if (r2 >= r02.size()) goto L17;
            if (r02.get(r2).isDefault() == true) goto L15;
            r2 = r2 + 1;
            goto L12
        L15:
            r1 = r2;
        L17:
            if (r8 < r1) goto L19;
            int r22 = r8 + 1;
        L20:
            if (r9 < r1) goto L22;
            int r12 = r9 + 1;
        L24:
            MessagesController.DialogFilter r3 = r02.get(r22);
            MessagesController.DialogFilter r4 = r02.get(r12);
            int r5 = r3.order;
            r3.order = r4.order;
            r4.order = r5;
            r02.set(r22, r4);
            r02.set(r12, r3);
            Tab r03 = (Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r8);
            Tab r13 = (Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r9);
            int r23 = r03.id;
            r03.id = r13.id;
            r13.id = r23;
            int r24 = FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).get(r8);
            FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).put(r8, FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).get(r9));
            FilterTabsView.m10610$$Nest$fgetpositionToStableId(this.this$0).put(r9, r24);
            FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0).onPageReorder(r13.id, r03.id);
            int r25 = FilterTabsView.m10596$$Nest$fgetcurrentPosition(this.this$0);
            FilterTabsView r32 = this.this$0;
            if (r25 != r8) goto L28;
            FilterTabsView.m10629$$Nest$fputcurrentPosition(r32, r9);
            FilterTabsView.m10636$$Nest$fputselectedTabId(this.this$0, r03.id);
        L30:
            int r26 = FilterTabsView.m10612$$Nest$fgetpreviousPosition(this.this$0);
            FilterTabsView r33 = this.this$0;
            if (r26 != r8) goto L34;
            FilterTabsView.m10635$$Nest$fputpreviousPosition(r33, r9);
            FilterTabsView.m10634$$Nest$fputpreviousId(this.this$0, r03.id);
        L36:
            FilterTabsView.m10617$$Nest$fgettabs(this.this$0).set(r8, r13);
            FilterTabsView.m10617$$Nest$fgettabs(this.this$0).set(r9, r03);
            FilterTabsView.m10639$$Nest$mupdateTabsWidths(this.this$0);
            FilterTabsView.m10633$$Nest$fputorderChanged(this.this$0, true);
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).setItemAnimator(this.this$0.itemAnimator);
            notifyItemMoved(r8, r9);
            return;
        L34:
            if (FilterTabsView.m10612$$Nest$fgetpreviousPosition(r33) != r9) goto L36;
            FilterTabsView.m10635$$Nest$fputpreviousPosition(this.this$0, r8);
            FilterTabsView.m10634$$Nest$fputpreviousId(this.this$0, r13.id);
            goto L36
        L28:
            if (FilterTabsView.m10596$$Nest$fgetcurrentPosition(r32) != r9) goto L30;
            FilterTabsView.m10629$$Nest$fputcurrentPosition(this.this$0, r8);
            FilterTabsView.m10636$$Nest$fputselectedTabId(this.this$0, r13.id);
        L22:
            r12 = r9;
            goto L24
        L19:
            r22 = r8;
            goto L20
        L23:
            r22 = r8;
            goto L22
        L43:
            return;
        L42:
            return;
        L41:
            return;
        }
    }

    public class Tab {
        public int counter;
        public String emoticon;
        public int iconWidth;
        public int id;
        public boolean isDefault;
        public boolean isLocked;
        public boolean noanimate;
        public CharSequence realTitle;
        final /* synthetic */ FilterTabsView this$0;
        public CharSequence title;
        public int titleWidth;

        public Tab(FilterTabsView r1, int r2, CharSequence r3, String r4, boolean r5) {
            this.this$0 = r1;
            this.id = r2;
            if (ExteraConfig.getTabIcons() == TabIconsMode.ICONS_ONLY) goto L5;
            CharSequence r12 = r3;
        L6:
            this.title = r12;
            this.realTitle = r3;
            this.noanimate = r5;
            this.emoticon = r4;
            return;
        L5:
            r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }

        public int getWidth(boolean r5) {
            this.iconWidth = FolderIcons.getTotalIconWidth();
            int r0 = (int) Math.ceil(HintView2.measureCorrectly(this.title, this.this$0.textPaint));
            this.titleWidth = r0;
            int r02 = r0 + this.iconWidth;
            int r1 = 0;
            if (r5 == false) goto L9;
            int r2 = FilterTabsView.m10597$$Nest$fgetdelegate(this.this$0).getTabCounter(this.id);
            if (r2 >= 0) goto L7;
            r2 = 0;
        L7:
            if (r5 == false) goto L10;
            this.counter = r2;
        L10:
            if (r2 <= 0) goto L13;
            String r52 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r2)});
            int r4 = (Math.max(AndroidUtilities.dp(7.333f), (int) Math.ceil(FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).measureText(r52))) + AndroidUtilities.dp(10.0f)) + AndroidUtilities.dp(-2.0f);
        L19:
            return Math.max(AndroidUtilities.dp(16.0f), r02 + r4);
        L13:
            if (this.isDefault == false) goto L15;
        L17:
            r4 = r1;
            goto L19
        L15:
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == false) goto L17;
            r1 = AndroidUtilities.dp(12.333f);
            goto L17
        L9:
            r2 = this.counter;
            goto L10
        }

        public boolean setTitle(String r3, ArrayList<TLRPC.MessageEntity> r4, boolean r5) {
            if (TextUtils.equals(this.realTitle, r3) == false) goto L5;
            return false;
        L5:
            this.realTitle = r3;
            SpannableStringBuilder r0 = new SpannableStringBuilder(r3);
            this.title = r0;
            CharSequence r32 = Emoji.replaceEmoji(r0, this.this$0.textPaint.getFontMetricsInt(), false);
            this.title = r32;
            this.title = MessageObject.replaceAnimatedEmoji(r32, r4, this.this$0.textPaint.getFontMetricsInt());
            if (ExteraConfig.getTabIcons() == TabIconsMode.ICONS_ONLY) goto L8;
            CharSequence r33 = this.title;
        L9:
            this.title = r33;
            this.noanimate = r5;
            return true;
        L8:
            r33 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L9
        }
    }

    public class TabView extends View {
        public boolean animateChange;
        public boolean animateCounterChange;
        private float animateFromCountWidth;
        private float animateFromCounterWidth;
        private int animateFromIconWidth;
        float animateFromIconX;
        int animateFromTabCount;
        private float animateFromTabWidth;
        float animateFromTextX;
        private int animateFromTitleWidth;
        private float animateFromWidth;
        private boolean animateIconChange;
        private boolean animateIconWidth;
        boolean animateIconX;
        boolean animateTabCounter;
        private boolean animateTabWidth;
        private boolean animateTextChange;
        private boolean animateTextChangeOut;
        boolean animateTextX;
        private boolean attached;
        public ValueAnimator changeAnimator;
        public float changeProgress;
        private String currentEmoticon;
        private boolean currentNoanimate;
        private int currentPosition;
        private Tab currentTab;
        private CharSequence currentText;
        private Drawable icon;
        private Drawable iconAnimateInDrawable;
        private Drawable iconAnimateOutDrawable;
        StaticLayout inCounter;
        private int lastCountWidth;
        private float lastCounterWidth;
        String lastEmoticon;
        private int lastIconWidth;
        float lastIconX;
        int lastTabCount;
        private float lastTabWidth;
        float lastTextX;
        CharSequence lastTitle;
        StaticLayout lastTitleLayout;
        private int lastTitleWidth;
        private float lastWidth;
        private float locIconXOffset;
        StaticLayout outCounter;
        private float progressToLocked;
        private final RectF rect;
        StaticLayout stableCounter;
        private float tabCounterVisible;
        private int tabWidth;
        private int textHeight;
        private StaticLayout textLayout;
        private AnimatedEmojiSpan.EmojiGroupedSpans textLayoutEmojis;
        private int textOffsetX;
        final /* synthetic */ FilterTabsView this$0;
        private StaticLayout titleAnimateInLayout;
        private AnimatedEmojiSpan.EmojiGroupedSpans titleAnimateInLayoutEmojis;
        private StaticLayout titleAnimateOutLayout;
        private AnimatedEmojiSpan.EmojiGroupedSpans titleAnimateOutLayoutEmojis;
        private StaticLayout titleAnimateStableLayout;
        private AnimatedEmojiSpan.EmojiGroupedSpans titleAnimateStableLayoutEmojis;
        private float titleXOffset;

        /* renamed from: org.telegram.ui.Components.FilterTabsView$TabView$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ TabView this$1;
            final /* synthetic */ int val$num;
            final /* synthetic */ float val$x;

            public AnonymousClass1(TabView r1, int r2, float r3) {
                this.this$1 = r1;
                this.val$num = r2;
                this.val$x = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                TabView r42 = this.this$1;
                int r0 = this.val$num;
                if (r0 != 5) goto L5;
                float r1 = 0.0f;
            L6:
                r42.shakeLockIcon(r1, r0 + 1);
                TabView.m10648$$Nest$fputlocIconXOffset(this.this$1, 0.0f);
                this.this$1.invalidate();
                return;
            L5:
                r1 = -this.val$x;
                goto L6
            }
        }

        /* renamed from: $r8$lambda$NpeKnZ7BpCXZk-7UfscRiAH9N9A, reason: not valid java name */
        public static /* synthetic */ void m10641$r8$lambda$NpeKnZ7BpCXZk7UfscRiAH9N9A(TabView r0, ValueAnimator r1) {
            r0.lambda$shakeLockIcon$0(r1);
        }

        /* renamed from: -$$Nest$fgetanimateFromTabWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10642$$Nest$fgetanimateFromTabWidth(TabView r0) {
            return r0.animateFromTabWidth;
        }

        /* renamed from: -$$Nest$fgetanimateTabWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10643$$Nest$fgetanimateTabWidth(TabView r0) {
            return r0.animateTabWidth;
        }

        /* renamed from: -$$Nest$fgetcurrentTab, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Tab m10644$$Nest$fgetcurrentTab(TabView r0) {
            return r0.currentTab;
        }

        /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m10645$$Nest$fgetrect(TabView r0) {
            return r0.rect;
        }

        /* renamed from: -$$Nest$fgettabCounterVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10646$$Nest$fgettabCounterVisible(TabView r0) {
            return r0.tabCounterVisible;
        }

        /* renamed from: -$$Nest$fgettabWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10647$$Nest$fgettabWidth(TabView r0) {
            return r0.tabWidth;
        }

        /* renamed from: -$$Nest$fputlocIconXOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10648$$Nest$fputlocIconXOffset(TabView r0, float r1) {
            r0.locIconXOffset = r1;
        }

        /* renamed from: -$$Nest$fputprogressToLocked, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10649$$Nest$fputprogressToLocked(TabView r0, float r1) {
            r0.progressToLocked = r1;
        }

        public TabView(FilterTabsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
            this.lastTabCount = -1;
        }

        private ColorMatrixColorFilter createColorFilterWithAlpha(int r6, float r7) {
            return new ColorMatrixColorFilter(new float[]{0.0f, 0.0f, 0.0f, 0.0f, Color.red(r6), 0.0f, 0.0f, 0.0f, 0.0f, Color.green(r6), 0.0f, 0.0f, 0.0f, 0.0f, Color.blue(r6), 0.0f, 0.0f, 0.0f, ((int) (r7 * 255.0f)) / 255.0f, 0.0f});
        }

        private float getCurrentIconAlpha() {
            boolean r0 = this.animateIconWidth;
            Tab r1 = this.currentTab;
            if (r0 == false) goto L16;
            int r02 = r1.iconWidth;
            if (r02 > 0) goto L7;
        L10:
            if (r02 == 0) goto L12;
            return 1.0f;
        L12:
            if (this.animateFromIconWidth > 0) goto L14;
            return 1.0f;
        L14:
            return 1.0f - this.changeProgress;
        L7:
            if (this.animateFromIconWidth != 0) goto L10;
            return this.changeProgress;
        L16:
            if (r1.iconWidth <= 0) goto L18;
            return 1.0f;
        L18:
            return 0.0f;
        }

        private /* synthetic */ void lambda$shakeLockIcon$0(ValueAnimator r1) {
            this.locIconXOffset = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        public boolean animateChange() {
            int r1 = this.currentTab.counter;
            int r2 = this.lastTabCount;
            if (r1 == r2) goto L20;
            this.animateTabCounter = true;
            this.animateFromTabCount = r2;
            this.animateFromCountWidth = this.lastCountWidth;
            this.animateFromCounterWidth = this.lastCounterWidth;
            if (r2 <= 0) goto L19;
            if (r1 <= 0) goto L19;
            String r7 = String.valueOf(r2);
            String r12 = String.valueOf(this.currentTab.counter);
            if (r7.length() != r12.length()) goto L18;
            SpannableStringBuilder r9 = new SpannableStringBuilder(r7);
            SpannableStringBuilder r22 = new SpannableStringBuilder(r12);
            SpannableStringBuilder r5 = new SpannableStringBuilder(r12);
            int r6 = 0;
        L11:
            if (r6 >= r7.length()) goto L17;
            if (r7.charAt(r6) != r12.charAt(r6)) goto L15;
            int r10 = r6 + 1;
            r9.setSpan(new EmptyStubSpan(), r6, r10, 0);
            r22.setSpan(new EmptyStubSpan(), r6, r10, 0);
        L16:
            r6 = r6 + 1;
            goto L11
        L15:
            r5.setSpan(new EmptyStubSpan(), r6, r6 + 1, 0);
            goto L16
        L17:
            int r13 = (int) Math.ceil(Theme.dialogs_countTextPaint.measureText(r7));
            TextPaint r102 = FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0);
            Layout.Alignment r14 = Layout.Alignment.ALIGN_CENTER;
            this.outCounter = new StaticLayout(r9, r102, r13, r14, 1.0f, 0.0f, false);
            this.stableCounter = new StaticLayout(r5, FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0), r13, r14, 1.0f, 0.0f, false);
            this.inCounter = new StaticLayout(r22, FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0), r13, r14, 1.0f, 0.0f, false);
            goto L19
        L18:
            int r92 = (int) Math.ceil(Theme.dialogs_countTextPaint.measureText(r7));
            TextPaint r8 = FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0);
            Layout.Alignment r103 = Layout.Alignment.ALIGN_CENTER;
            this.outCounter = new StaticLayout(r7, r8, r92, r103, 1.0f, 0.0f, false);
            this.inCounter = new StaticLayout(r12, FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0), (int) Math.ceil(Theme.dialogs_countTextPaint.measureText(r12)), r103, 1.0f, 0.0f, false);
        L19:
            boolean r15 = true;
        L22:
            if (this.currentTab.counter <= 0) goto L24;
            int r23 = Math.max(AndroidUtilities.dp(7.333f), (int) Math.ceil(FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).measureText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r2)})))) + AndroidUtilities.dp(10.0f);
        L25:
            int r52 = this.currentTab.iconWidth;
            int r62 = this.lastIconWidth;
            if (r52 == r62) goto L29;
            this.animateIconWidth = true;
            this.animateFromIconWidth = r62;
            r15 = true;
        L29:
            if (ExteraConfig.getTabIcons() == TabIconsMode.ICONS_ONLY) goto L35;
            int r63 = this.currentTab.titleWidth + r52;
            if (r23 == 0) goto L33;
            int r24 = r23 + AndroidUtilities.dp(6.0f);
        L34:
            int r64 = r63 + r24;
        L39:
            float r25 = ((getMeasuredWidth() - r64) / 2) + r52;
            float r53 = this.lastTextX;
            if (r25 == r53) goto L42;
            this.animateTextX = true;
            this.animateFromTextX = r53;
            r15 = true;
        L42:
            CharSequence r26 = this.lastTitle;
            if (r26 == null) goto L94;
            if (this.currentTab.title.equals(r26) == true) goto L94;
            if (this.lastTitle.length() <= this.currentTab.title.length()) goto L49;
            CharSequence r16 = this.lastTitle;
            CharSequence r27 = this.currentTab.title;
            boolean r54 = true;
        L50:
            int r72 = AndroidUtilities.charSequenceIndexOf(r16, r27);
            float r93 = 0.0f;
            int r104 = 26;
            if (r72 < 0) goto L78;
            CharSequence r122 = Emoji.replaceEmoji(r16, this.this$0.textPaint.getFontMetricsInt(), false);
            SpannableStringBuilder r142 = new SpannableStringBuilder(r122);
            SpannableStringBuilder r132 = new SpannableStringBuilder(r122);
            if (r72 == 0) goto L56;
            r132.setSpan(new EmptyStubSpan(), 0, r72, 0);
        L56:
            if ((r27.length() + r72) == r16.length()) goto L58;
            r132.setSpan(new EmptyStubSpan(), r27.length() + r72, r16.length(), 0);
        L58:
            r142.setSpan(new EmptyStubSpan(), r72, r27.length() + r72, 0);
            TextPaint r152 = this.this$0.textPaint;
            int r162 = AndroidUtilities.dp(400.0f);
            Layout.Alignment r17 = Layout.Alignment.ALIGN_NORMAL;
            StaticLayout r133 = new StaticLayout(r142, r152, r162, r17, 1.0f, 0.0f, false);
            this.titleAnimateInLayout = r133;
            if (this.attached == true) goto L61;
        L65:
            StaticLayout r153 = new StaticLayout(r132, this.this$0.textPaint, AndroidUtilities.dp(400.0f), r17, 1.0f, 0.0f, false);
            this.titleAnimateStableLayout = r153;
            if (this.attached == true) goto L68;
        L72:
            this.animateTextChange = true;
            this.animateTextChangeOut = r54;
            if (r72 == 0) goto L76;
            r93 = -this.titleAnimateStableLayout.getPrimaryHorizontal(r72);
        L76:
            this.titleXOffset = r93;
            this.animateFromTitleWidth = this.lastTitleWidth;
            this.titleAnimateOutLayout = null;
            AnimatedEmojiSpan.release(this, this.titleAnimateOutLayoutEmojis);
        L77:
            r15 = true;
            goto L94
        L68:
            if (this.currentTab.noanimate == true) goto L71;
            r104 = 0;
        L71:
            this.titleAnimateStableLayoutEmojis = AnimatedEmojiSpan.update(r104, this, this.titleAnimateStableLayoutEmojis, new Layout[]{r153});
            goto L72
        L61:
            if (this.currentTab.noanimate == false) goto L63;
            int r28 = 26;
        L64:
            this.titleAnimateInLayoutEmojis = AnimatedEmojiSpan.update(r28, this, this.titleAnimateInLayoutEmojis, new Layout[]{r133});
            goto L65
        L63:
            r28 = 0;
            goto L64
        L78:
            CharSequence r134 = this.currentTab.title;
            TextPaint r143 = this.this$0.textPaint;
            int r154 = AndroidUtilities.dp(400.0f);
            Layout.Alignment r163 = Layout.Alignment.ALIGN_NORMAL;
            StaticLayout r123 = new StaticLayout(r134, r143, r154, r163, 1.0f, 0.0f, false);
            this.titleAnimateInLayout = r123;
            if (this.attached == true) goto L81;
        L85:
            StaticLayout r164 = new StaticLayout(this.lastTitle, this.this$0.textPaint, AndroidUtilities.dp(400.0f), r163, 1.0f, 0.0f, false);
            this.titleAnimateOutLayout = r164;
            if (this.attached == true) goto L88;
        L92:
            this.titleAnimateStableLayout = null;
            AnimatedEmojiSpan.release(this, this.titleAnimateStableLayoutEmojis);
            this.animateTextChange = true;
            this.titleXOffset = 0.0f;
            this.animateFromTitleWidth = this.lastTitleWidth;
            goto L77
        L88:
            if (this.currentTab.noanimate == true) goto L91;
            r104 = 0;
        L91:
            this.titleAnimateOutLayoutEmojis = AnimatedEmojiSpan.update(r104, this, this.titleAnimateOutLayoutEmojis, new Layout[]{r164});
            goto L92
        L81:
            if (this.currentTab.noanimate == false) goto L83;
            int r18 = 26;
        L84:
            this.titleAnimateInLayoutEmojis = AnimatedEmojiSpan.update(r18, this, this.titleAnimateInLayoutEmojis, new Layout[]{r123});
            goto L85
        L83:
            r18 = 0;
            goto L84
        L49:
            r16 = this.currentTab.title;
            r27 = this.lastTitle;
            r54 = false;
        L94:
            if (ExteraConfig.getTabIcons() == TabIconsMode.TITLES_ONLY) goto L104;
            float r29 = (int) ((getMeasuredWidth() - r64) / 2.0f);
            float r55 = this.lastIconX;
            if (r29 == r55) goto L98;
            this.animateIconX = true;
            this.animateFromIconX = r55;
            r15 = true;
        L98:
            String r210 = this.lastEmoticon;
            if (r210 == null) goto L104;
            if (this.currentTab.emoticon.equals(r210) == true) goto L104;
            int r19 = FolderIcons.getIconWidth();
            Rect r211 = new Rect(0, 0, r19, r19);
            this.iconAnimateOutDrawable = getResources().getDrawable(FolderIcons.getTabIcon(this.lastEmoticon)).mutate();
            this.iconAnimateInDrawable = getResources().getDrawable(FolderIcons.getTabIcon(this.currentTab.emoticon)).mutate();
            this.iconAnimateOutDrawable.setBounds(r211);
            this.iconAnimateInDrawable.setBounds(r211);
            this.iconAnimateOutDrawable.setTint(this.this$0.textPaint.getColor());
            this.iconAnimateInDrawable.setTint(this.this$0.textPaint.getColor());
            this.animateIconChange = true;
            r15 = true;
        L104:
            if (r64 == this.lastTabWidth) goto L106;
        L109:
            this.animateTabWidth = true;
            this.animateFromTabWidth = this.lastTabWidth;
            this.animateFromWidth = this.lastWidth;
            return true;
        L106:
            if (getMeasuredWidth() != this.lastWidth) goto L109;
            return r15;
        L33:
            r24 = 0;
            goto L34
        L35:
            if (r23 == 0) goto L37;
            int r212 = r23 + AndroidUtilities.dp(6.0f);
        L38:
            r64 = r52 + r212;
            goto L39
        L37:
            r212 = 0;
            goto L38
        L24:
            r23 = 0;
            goto L25
        L20:
            r15 = false;
            goto L22
        }

        public void clearTransitionParams() {
            this.animateChange = false;
            this.animateIconWidth = false;
            this.animateTabCounter = false;
            this.animateCounterChange = false;
            this.animateTextChange = false;
            this.animateTextX = false;
            this.animateIconX = false;
            this.animateIconChange = false;
            this.animateTabWidth = false;
            this.changeAnimator = null;
            invalidate();
        }

        @Override // android.view.View
        public int getId() {
            return this.currentTab.id;
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            this.attached = true;
            super.onAttachedToWindow();
            int r3 = 26;
            if (this.currentTab.noanimate == false) goto L5;
            int r1 = 26;
        L6:
            this.textLayoutEmojis = AnimatedEmojiSpan.update(r1, this, this.textLayoutEmojis, new Layout[]{this.textLayout});
            if (this.currentTab.noanimate == false) goto L9;
            int r12 = 26;
        L10:
            this.titleAnimateInLayoutEmojis = AnimatedEmojiSpan.update(r12, this, this.titleAnimateInLayoutEmojis, new Layout[]{this.titleAnimateInLayout});
            if (this.currentTab.noanimate == false) goto L13;
            int r13 = 26;
        L14:
            this.titleAnimateOutLayoutEmojis = AnimatedEmojiSpan.update(r13, this, this.titleAnimateOutLayoutEmojis, new Layout[]{this.titleAnimateOutLayout});
            if (this.currentTab.noanimate == true) goto L18;
            r3 = 0;
        L18:
            this.titleAnimateStableLayoutEmojis = AnimatedEmojiSpan.update(r3, this, this.titleAnimateStableLayoutEmojis, new Layout[]{this.titleAnimateStableLayout});
            return;
        L13:
            r13 = 0;
            goto L14
        L9:
            r12 = 0;
            goto L10
        L5:
            r1 = 0;
            goto L6
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            this.attached = false;
            super.onDetachedFromWindow();
            this.animateChange = false;
            this.animateTabCounter = false;
            this.animateCounterChange = false;
            this.animateTextChange = false;
            this.animateTextX = false;
            this.animateIconX = false;
            this.animateIconChange = false;
            this.animateTabWidth = false;
            ValueAnimator r0 = this.changeAnimator;
            if (r0 == null) goto L5;
            r0.removeAllListeners();
            this.changeAnimator.removeAllUpdateListeners();
            this.changeAnimator.cancel();
            this.changeAnimator = null;
        L5:
            invalidate();
            AnimatedEmojiSpan.release(this, this.textLayoutEmojis);
            AnimatedEmojiSpan.release(this, this.titleAnimateInLayoutEmojis);
            AnimatedEmojiSpan.release(this, this.titleAnimateOutLayoutEmojis);
            AnimatedEmojiSpan.release(this, this.titleAnimateStableLayoutEmojis);
        }

        @Override // android.view.View
        @SuppressLint({"DrawAllocation"})
        public void onDraw(Canvas r35) {
            Canvas r1 = r35;
            if (this.currentTab.isDefault == true) goto L5;
        L8:
            boolean r12 = true;
        L10:
            if (this.currentTab.isDefault == true) goto L13;
            if (r12 == false) goto L13;
            boolean r13 = true;
        L15:
            if (r12 == true) goto L17;
        L30:
            float r17 = 400.0f;
            float r18 = 1.0f;
        L31:
            int r2 = FilterTabsView.m10608$$Nest$fgetmanualScrollingToId(this.this$0);
            FilterTabsView r4 = this.this$0;
            if (r2 == (-1)) goto L34;
            int r22 = FilterTabsView.m10608$$Nest$fgetmanualScrollingToId(r4);
            int r42 = FilterTabsView.m10614$$Nest$fgetselectedTabId(this.this$0);
        L35:
            int r6 = this.currentTab.id;
            FilterTabsView r7 = this.this$0;
            if (r6 != r22) goto L39;
            int r62 = FilterTabsView.m10586$$Nest$fgetactiveTextColorKey(r7);
            int r72 = FilterTabsView.m10582$$Nest$fgetaActiveTextColorKey(this.this$0);
            int r8 = FilterTabsView.m10619$$Nest$fgetunactiveTextColorKey(this.this$0);
            int r9 = FilterTabsView.m10585$$Nest$fgetaUnactiveTextColorKey(this.this$0);
            int r10 = Theme.key_chats_tabUnreadActiveBackground;
            int r16 = Theme.key_chats_tabUnreadUnactiveBackground;
        L40:
            FilterTabsView r15 = this.this$0;
            if (r72 < 0) goto L43;
            int r63 = Theme.getColor(r62, FilterTabsView.m10613$$Nest$fgetresourcesProvider(r15));
            int r73 = Theme.getColor(r72, FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            if (FilterTabsView.m10589$$Nest$fgetanimatingIndicator(this.this$0) == false) goto L55;
        L56:
            int r152 = this.currentTab.id;
            if (r152 == r22) goto L61;
            if (r152 == r42) goto L61;
        L60:
            FilterTabsView r82 = this.this$0;
            r82.textPaint.setColor(ColorUtils.blendARGB(r63, r73, FilterTabsView.m10593$$Nest$fgetanimationValue(r82)));
        L62:
            FilterTabsView.m10630$$Nest$fputemojiColorFilter(this.this$0, new PorterDuffColorFilter(this.this$0.textPaint.getColor(), PorterDuff.Mode.SRC_IN));
            int r5 = this.animateFromTabCount;
            if (r5 == 0) goto L65;
        L67:
            boolean r153 = false;
        L68:
            if (r5 > 0) goto L70;
        L74:
            boolean r21 = false;
        L75:
            if (r5 > 0) goto L77;
        L81:
            boolean r222 = false;
        L82:
            int r64 = this.currentTab.counter;
            if (r64 > 0) goto L88;
            if (r21 == true) goto L88;
            String r52 = null;
            int r74 = 0;
            float r23 = 0.0f;
        L93:
            if (r13 == true) goto L95;
        L99:
            if (r74 == 0) goto L104;
            if (r21 == true) goto L104;
            if (r52 == null) goto L103;
            float r65 = r18;
        L105:
            this.tabCounterVisible = r65;
            TabIconsMode r66 = ExteraConfig.getTabIcons();
            TabIconsMode r83 = TabIconsMode.ICONS_ONLY;
            Tab r92 = this.currentTab;
            if (r66 == r83) goto L116;
            int r67 = r92.iconWidth + r92.titleWidth;
            if (r74 == 0) goto L114;
            if (r21 == true) goto L114;
            if (r52 == null) goto L112;
            float r84 = r18;
        L113:
            int r85 = AndroidUtilities.dp(r84 * (-2.0f)) + r74;
        L115:
            this.tabWidth = r67 + r85;
        L125:
            Tab r86 = this.currentTab;
            float r68 = ((getMeasuredWidth() - this.tabWidth) / 2.0f) + r86.iconWidth;
            if (this.animateTextX == false) goto L129;
            float r93 = this.changeProgress;
            float r25 = 0.0f;
            r68 = (r68 * r93) + (this.animateFromTextX * (r18 - r93));
        L128:
            float r14 = r68;
            if (TextUtils.equals(r86.title, this.currentText) == true) goto L138;
            this.currentText = this.currentTab.title;
            StaticLayout r26 = new StaticLayout(this.currentText, this.this$0.textPaint, AndroidUtilities.dp(r17), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.textLayout = r26;
            if (this.currentTab.noanimate == false) goto L135;
            int r87 = 26;
        L136:
            this.textLayoutEmojis = AnimatedEmojiSpan.update(r87, this, this.textLayoutEmojis, new Layout[]{r26});
            this.textHeight = this.textLayout.getHeight();
            this.textOffsetX = (int) (-this.textLayout.getLineLeft(0));
            goto L138
        L135:
            r87 = 0;
        L138:
            if (this.animateTextChange == false) goto L167;
            float r3 = this.titleXOffset;
            boolean r69 = this.animateTextChangeOut;
            float r88 = this.changeProgress;
            if (r69 == true) goto L143;
            r88 = r18 - r88;
        L143:
            float r262 = r3 * r88;
            if (this.titleAnimateStableLayout == null) goto L146;
            r1.save();
            r1.translate((this.textOffsetX + r14) + r262, ((getMeasuredHeight() - this.textHeight) / 2.0f) + r18);
            this.titleAnimateStableLayout.draw(r1);
            boolean r29 = r13;
            String r132 = r52;
            boolean r20 = r12;
            int r122 = r42;
            int r28 = r10;
            int r11 = r22;
            boolean r30 = r153;
            int r154 = r74;
            float r31 = r14;
            AnimatedEmojiSpan.drawAnimatedEmojis(r1, this.titleAnimateStableLayout, this.titleAnimateStableLayoutEmojis, 0.0f, null, computeVerticalScrollOffset() - AndroidUtilities.dp(6.0f), computeVerticalScrollOffset() + computeVerticalScrollExtent(), 0.0f, 1.0f, FilterTabsView.m10601$$Nest$fgetemojiColorFilter(this.this$0));
            r1.restore();
        L148:
            if (this.titleAnimateInLayout == null) goto L157;
            r1.save();
            int r24 = this.this$0.textPaint.getAlpha();
            TextPaint r32 = this.this$0.textPaint;
            float r43 = r24;
            boolean r53 = this.animateTextChangeOut;
            float r610 = this.changeProgress;
            if (r53 == false) goto L152;
            r610 = r18 - r610;
        L152:
            r32.setAlpha((int) (r43 * r610));
            r1.translate((r31 + this.textOffsetX) + r262, ((getMeasuredHeight() - this.textHeight) / 2.0f) + r18);
            this.titleAnimateInLayout.draw(r1);
            StaticLayout r27 = this.titleAnimateInLayout;
            AnimatedEmojiSpan.EmojiGroupedSpans r33 = this.titleAnimateInLayoutEmojis;
            float r611 = computeVerticalScrollOffset() - AndroidUtilities.dp(6.0f);
            float r75 = computeVerticalScrollOffset() + computeVerticalScrollExtent();
            boolean r54 = this.animateTextChangeOut;
            float r89 = this.changeProgress;
            if (r54 == false) goto L155;
            r89 = r18 - r89;
        L155:
            AnimatedEmojiSpan.drawAnimatedEmojis(r1, r27, r33, 0.0f, null, r611, r75, 0.0f, r89, FilterTabsView.m10601$$Nest$fgetemojiColorFilter(this.this$0));
            r1.restore();
            this.this$0.textPaint.setAlpha(r24);
        L157:
            if (this.titleAnimateOutLayout == null) goto L171;
            r1.save();
            int r142 = this.this$0.textPaint.getAlpha();
            TextPaint r210 = this.this$0.textPaint;
            float r34 = r142;
            boolean r44 = this.animateTextChangeOut;
            float r55 = this.changeProgress;
            if (r44 == true) goto L162;
            r55 = r18 - r55;
        L162:
            r210.setAlpha((int) (r34 * r55));
            r1.translate((r31 + this.textOffsetX) + r262, ((getMeasuredHeight() - this.textHeight) / 2.0f) + r18);
            this.titleAnimateOutLayout.draw(r1);
            StaticLayout r211 = this.titleAnimateOutLayout;
            AnimatedEmojiSpan.EmojiGroupedSpans r36 = this.titleAnimateOutLayoutEmojis;
            float r612 = computeVerticalScrollOffset() - AndroidUtilities.dp(6.0f);
            float r76 = computeVerticalScrollOffset() + computeVerticalScrollExtent();
            boolean r45 = this.animateTextChangeOut;
            float r56 = this.changeProgress;
            if (r45 == true) goto L166;
            r56 = r18 - r56;
        L166:
            AnimatedEmojiSpan.drawAnimatedEmojis(r1, r211, r36, 0.0f, null, r612, r76, 0.0f, r56, FilterTabsView.m10601$$Nest$fgetemojiColorFilter(this.this$0));
            r1.restore();
            this.this$0.textPaint.setAlpha(r142);
        L171:
            float r212 = getCurrentIconAlpha();
            if (r212 <= r25) goto L189;
            int r37 = FolderIcons.getIconWidth();
            if (TextUtils.equals(this.currentTab.emoticon, this.currentEmoticon) == true) goto L176;
            this.currentEmoticon = this.currentTab.emoticon;
            Rect r46 = new Rect(0, 0, r37, r37);
            Drawable r57 = getResources().getDrawable(FolderIcons.getTabIcon(this.currentTab.emoticon)).mutate();
            this.icon = r57;
            r57.setBounds(r46);
        L176:
            int r47 = this.this$0.textPaint.getColor();
            int r58 = (int) ((getMeasuredWidth() - this.tabWidth) / 2.0f);
            if (this.animateIconX == false) goto L179;
            float r613 = this.changeProgress;
            r58 = (int) ((r58 * r613) + (this.animateFromIconX * (r18 - r613)));
        L179:
            int r38 = (int) ((getMeasuredHeight() - r37) / 2.0f);
            if (this.animateIconChange == true) goto L182;
            r1.save();
            r1.translate(r58, r38);
            this.icon.setColorFilter(createColorFilterWithAlpha(r47, r212));
            this.icon.draw(r1);
            r1.restore();
        L188:
            int r77 = r58;
        L190:
            if (r30 == true) goto L199;
            if (r132 != null) goto L199;
            if (r29 == true) goto L194;
        L198:
            float r213 = r23;
            if (r20 == true) goto L293;
        L295:
            this.lastEmoticon = this.currentEmoticon;
            this.lastTextX = r31;
            this.lastIconX = r77;
            Tab r39 = this.currentTab;
            this.lastTabCount = r39.counter;
            this.lastTitleLayout = this.textLayout;
            this.lastTitle = this.currentText;
            this.lastTitleWidth = r39.titleWidth;
            this.lastCountWidth = r154;
            this.lastCounterWidth = r213;
            this.lastIconWidth = r39.iconWidth;
            this.lastTabWidth = this.tabWidth;
            this.lastWidth = getMeasuredWidth();
            if (this.currentTab.isLocked == true) goto L302;
            if (this.progressToLocked != r25) goto L302;
            return;
        L302:
            if (FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0) != null) goto L304;
            FilterTabsView.m10631$$Nest$fputlockDrawable(this.this$0, ContextCompat.getDrawable(getContext(), R.drawable.other_lockedfolders));
        L304:
            boolean r214 = this.currentTab.isLocked;
            if (r214 == false) goto L309;
            float r48 = this.progressToLocked;
            if (r48 == r18) goto L309;
            this.progressToLocked = r48 + 0.10666667f;
        L311:
            this.progressToLocked = Utilities.clamp(this.progressToLocked, r18, r25);
            int r215 = Theme.getColor(FilterTabsView.m10619$$Nest$fgetunactiveTextColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            if (FilterTabsView.m10585$$Nest$fgetaUnactiveTextColorKey(this.this$0) < 0) goto L315;
            r215 = ColorUtils.blendARGB(r215, Theme.getColor(FilterTabsView.m10585$$Nest$fgetaUnactiveTextColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)), FilterTabsView.m10593$$Nest$fgetanimationValue(this.this$0));
        L315:
            if (FilterTabsView.m10607$$Nest$fgetlockDrawableColor(this.this$0) == r215) goto L317;
            FilterTabsView.m10632$$Nest$fputlockDrawableColor(this.this$0, r215);
            FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).setColorFilter(new PorterDuffColorFilter(r215, PorterDuff.Mode.MULTIPLY));
        L317:
            int r216 = (int) (((getMeasuredWidth() - FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).getIntrinsicWidth()) / 2.0f) + this.locIconXOffset);
            int r310 = getMeasuredHeight() - AndroidUtilities.dp(12.0f);
            FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).setBounds(r216, r310, FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).getIntrinsicWidth() + r216, FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).getIntrinsicHeight() + r310);
            if (this.progressToLocked == 1.0f) goto L321;
            r1.save();
            float r217 = this.progressToLocked;
            r1.scale(r217, r217, FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).getBounds().centerX(), FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).getBounds().centerY());
            FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).draw(r1);
            r1.restore();
            return;
        L321:
            FilterTabsView.m10606$$Nest$fgetlockDrawable(this.this$0).draw(r1);
            return;
        L309:
            if (r214 == true) goto L311;
            this.progressToLocked -= 0.10666667f;
            goto L311
        L293:
            if (FilterTabsView.m10599$$Nest$fgeteditingAnimationProgress(this.this$0) == r25) goto L295;
            r1.restore();
            goto L295
        L194:
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == true) goto L199;
            if (FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0) == r25) goto L198;
        L199:
            int r218 = FilterTabsView.m10583$$Nest$fgetaBackgroundColorKey(this.this$0);
            FilterTabsView r311 = this.this$0;
            if (r218 >= 0) goto L202;
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(r311).setColor(Theme.getColor(FilterTabsView.m10594$$Nest$fgetbackgroundColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)));
        L204:
            if (Theme.hasThemeKey(r28) == true) goto L206;
        L217:
            FilterTabsView.m10595$$Nest$fgetcounterPaint(this.this$0).setColor(this.this$0.textPaint.getColor());
        L218:
            int r219 = this.currentTab.titleWidth;
            float r312 = r219;
            boolean r49 = this.animateTextChange;
            if (r49 == false) goto L222;
            float r313 = this.animateFromTitleWidth;
            float r59 = this.changeProgress;
            r312 = (r313 * (r18 - r59)) + (r219 * r59);
        L222:
            if (r49 == true) goto L224;
        L227:
            float r143 = r31 + r312;
            int r220 = AndroidUtilities.dp(5.0f);
        L226:
            float r144 = r143 + r220;
            int r221 = (getMeasuredHeight() - AndroidUtilities.dp(17.333f)) / 2;
            if (r29 == true) goto L231;
        L236:
            FilterTabsView.m10595$$Nest$fgetcounterPaint(this.this$0).setAlpha(255);
        L237:
            if (r222 == false) goto L241;
            float r510 = this.animateFromCountWidth;
            float r614 = r154;
            if (r510 == r614) goto L241;
            float r810 = this.changeProgress;
            float r511 = (r510 * (r18 - r810)) + (r614 * r810);
        L242:
            if (r222 == false) goto L244;
            float r615 = this.animateFromCounterWidth;
            float r811 = this.changeProgress;
            r23 = (r615 * (r18 - r811)) + (r23 * r811);
        L244:
            float r812 = r221;
            this.rect.set(r144, r812, r511 + r144, AndroidUtilities.dp(17.333f) + r221);
            if (r30 == true) goto L247;
            if (r21 == true) goto L247;
        L252:
            RectF r512 = this.rect;
            float r616 = AndroidUtilities.density;
            r1.drawRoundRect(r512, r616 * 11.5f, r616 * 11.5f, FilterTabsView.m10595$$Nest$fgetcounterPaint(this.this$0));
            if (r222 == true) goto L255;
            if (r132 == null) goto L282;
            if (r29 == false) goto L281;
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).setAlpha((int) ((r18 - FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0)) * 255.0f));
        L281:
            RectF r314 = this.rect;
            r1.drawText(r132, r314.left + ((r314.width() - r23) / 2.0f), r221 + AndroidUtilities.dp(12.5f), FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0));
        L282:
            if (r30 == true) goto L284;
            if (r21 == true) goto L284;
        L285:
            if (r29 == false) goto L198;
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == false) goto L289;
        L290:
            FilterTabsView.m10598$$Nest$fgetdeletePaint(this.this$0).setColor(FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).getColor());
            FilterTabsView.m10598$$Nest$fgetdeletePaint(this.this$0).setAlpha((int) (FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0) * 255.0f));
            float r813 = AndroidUtilities.dp(3.0f);
            r1.drawLine(this.rect.centerX() - r813, this.rect.centerY() - r813, this.rect.centerX() + r813, this.rect.centerY() + r813, FilterTabsView.m10598$$Nest$fgetdeletePaint(this.this$0));
            r1 = r35;
            r1.drawLine(this.rect.centerX() - r813, this.rect.centerY() + r813, this.rect.centerX() + r813, this.rect.centerY() - r813, FilterTabsView.m10598$$Nest$fgetdeletePaint(this.this$0));
            goto L198
        L289:
            if (FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0) == r25) goto L198;
        L284:
            r1.restore();
            goto L285
        L255:
            if (this.inCounter == null) goto L259;
            int r223 = AndroidUtilities.dp(17.333f);
            int r513 = this.inCounter.getLineBottom(0);
            int r617 = this.inCounter.getLineTop(0);
        L257:
            r812 = r812 + ((r223 - (r513 - r617)) / 2.0f);
        L264:
            float r814 = r812 - AndroidUtilities.dp(0.5f);
            if (r29 == false) goto L267;
            float r224 = r18 - FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0);
        L269:
            if (this.inCounter == null) goto L272;
            r1.save();
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).setAlpha((int) ((r224 * 255.0f) * this.changeProgress));
            RectF r514 = this.rect;
            r1.translate(r514.left + ((r514.width() - r23) / 2.0f), ((r18 - this.changeProgress) * AndroidUtilities.dp(15.0f)) + r814);
            this.inCounter.draw(r1);
            r1.restore();
        L272:
            if (this.outCounter == null) goto L275;
            r1.save();
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).setAlpha((int) ((r224 * 255.0f) * (r18 - this.changeProgress)));
            RectF r515 = this.rect;
            r1.translate(r515.left + ((r515.width() - r23) / 2.0f), (this.changeProgress * (-AndroidUtilities.dp(15.0f))) + r814);
            this.outCounter.draw(r1);
            r1.restore();
        L275:
            if (this.stableCounter == null) goto L277;
            r1.save();
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).setAlpha((int) (r224 * 255.0f));
            RectF r225 = this.rect;
            r1.translate(r225.left + ((r225.width() - r23) / 2.0f), r814);
            this.stableCounter.draw(r1);
            r1.restore();
        L277:
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).setAlpha(255);
            goto L282
        L267:
            r224 = r18;
            goto L269
        L259:
            if (this.outCounter == null) goto L262;
            r223 = AndroidUtilities.dp(17.333f);
            r513 = this.outCounter.getLineBottom(0);
            r617 = this.outCounter.getLineTop(0);
            goto L257
        L262:
            if (this.stableCounter == null) goto L264;
            r223 = AndroidUtilities.dp(17.333f);
            r513 = this.stableCounter.getLineBottom(0);
            r617 = this.stableCounter.getLineTop(0);
        L247:
            r1.save();
            float r516 = this.changeProgress;
            if (r30 == true) goto L251;
            r516 = r18 - r516;
        L251:
            r1.scale(r516, r516, this.rect.centerX(), this.rect.centerY());
        L241:
            r511 = r154;
            goto L242
        L231:
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == false) goto L233;
        L234:
            if (r132 != null) goto L236;
            FilterTabsView.m10595$$Nest$fgetcounterPaint(this.this$0).setAlpha((int) (FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0) * 255.0f));
            goto L237
        L233:
            if (FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0) == r25) goto L236;
        L224:
            if (this.titleAnimateOutLayout != null) goto L227;
            r143 = ((r31 - this.titleXOffset) + r262) + r312;
            r220 = AndroidUtilities.dp(5.0f);
            goto L226
        L206:
            if (Theme.hasThemeKey(r16) == false) goto L217;
            int r226 = Theme.getColor(r28, FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            if (FilterTabsView.m10589$$Nest$fgetanimatingIndicator(this.this$0) == false) goto L210;
        L211:
            int r315 = this.currentTab.id;
            if (r315 == r11) goto L216;
            if (r315 == r122) goto L216;
        L215:
            FilterTabsView.m10595$$Nest$fgetcounterPaint(this.this$0).setColor(r226);
        L216:
            FilterTabsView.m10595$$Nest$fgetcounterPaint(this.this$0).setColor(ColorUtils.blendARGB(Theme.getColor(r16, FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)), r226, FilterTabsView.m10590$$Nest$fgetanimatingIndicatorProgress(this.this$0)));
            goto L218
        L210:
            if (FilterTabsView.m10609$$Nest$fgetmanualScrollingToPosition(this.this$0) == (-1)) goto L215;
        L202:
            FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).setColor(ColorUtils.blendARGB(Theme.getColor(FilterTabsView.m10594$$Nest$fgetbackgroundColorKey(r311), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)), Theme.getColor(FilterTabsView.m10583$$Nest$fgetaBackgroundColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)), FilterTabsView.m10593$$Nest$fgetanimationValue(this.this$0)));
            goto L204
        L182:
            if (this.iconAnimateOutDrawable == null) goto L185;
            r1.save();
            r1.translate(r58, r38);
            this.iconAnimateOutDrawable.setColorFilter(createColorFilterWithAlpha(r47, (r18 - this.changeProgress) * r212));
            this.iconAnimateOutDrawable.draw(r1);
            r1.restore();
        L185:
            if (this.iconAnimateInDrawable == null) goto L188;
            r1.save();
            r1.translate(r58, r38);
            this.iconAnimateInDrawable.setColorFilter(createColorFilterWithAlpha(r47, this.changeProgress * r212));
            this.iconAnimateInDrawable.draw(r1);
            r1.restore();
            goto L188
        L189:
            r77 = 0;
            goto L190
        L146:
            r11 = r22;
            r28 = r10;
            r20 = r12;
            r29 = r13;
            r31 = r14;
            r30 = r153;
            r122 = r42;
            r132 = r52;
            r154 = r74;
            goto L148
        L167:
            r11 = r22;
            r28 = r10;
            r20 = r12;
            r29 = r13;
            r31 = r14;
            r30 = r153;
            r122 = r42;
            r132 = r52;
            r154 = r74;
            if (this.textLayout == null) goto L170;
            r1.save();
            r1.translate(r31 + this.textOffsetX, ((getMeasuredHeight() - this.textHeight) / 2.0f) + r18);
            this.textLayout.draw(r1);
            AnimatedEmojiSpan.drawAnimatedEmojis(r1, this.textLayout, this.textLayoutEmojis, 0.0f, null, computeVerticalScrollOffset() - AndroidUtilities.dp(6.0f), computeVerticalScrollOffset() + computeVerticalScrollExtent(), 0.0f, 1.0f, FilterTabsView.m10601$$Nest$fgetemojiColorFilter(this.this$0));
            r1.restore();
        L170:
            r262 = r25;
            goto L171
        L129:
            r25 = 0.0f;
            goto L128
        L112:
            r84 = FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0);
        L114:
            r85 = 0;
            goto L115
        L116:
            int r618 = r92.iconWidth;
            if (r74 == 0) goto L123;
            if (r21 == true) goto L123;
            if (r52 == null) goto L121;
            float r815 = r18;
        L122:
            int r816 = AndroidUtilities.dp(r815 * (-2.0f)) + r74;
        L124:
            this.tabWidth = r618 + r816;
            goto L125
        L121:
            r815 = FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0);
        L123:
            r816 = 0;
            goto L124
        L103:
            r65 = FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0);
        L104:
            r65 = 0.0f;
            goto L105
        L95:
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == false) goto L97;
        L98:
            r74 = (int) (r74 + ((AndroidUtilities.dp(17.333f) - r74) * FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0)));
            goto L99
        L97:
            if (FilterTabsView.m10600$$Nest$fgeteditingStartAnimationProgress(this.this$0) == 0.0f) goto L99;
        L88:
            if (r21 == false) goto L90;
            r52 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r5)});
        L91:
            float r619 = (int) Math.ceil(FilterTabsView.m10618$$Nest$fgettextCounterPaint(this.this$0).measureText(r52));
            r74 = ((int) Math.max(AndroidUtilities.dp(7.333f), r619)) + AndroidUtilities.dp(10.0f);
            r23 = r619;
            goto L93
        L90:
            r52 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r64)});
            goto L91
        L77:
            if (this.currentTab.counter <= 0) goto L81;
            if (this.animateTabCounter == false) goto L81;
            r222 = true;
            goto L82
        L70:
            if (this.currentTab.counter != 0) goto L74;
            if (this.animateTabCounter == false) goto L74;
            r21 = true;
            goto L75
        L65:
            if (this.animateTabCounter == false) goto L67;
            r153 = true;
        L61:
            int r817 = Theme.getColor(r8, FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            int r94 = Theme.getColor(r9, FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0));
            FilterTabsView r155 = this.this$0;
            r155.textPaint.setColor(ColorUtils.blendARGB(ColorUtils.blendARGB(r817, r94, FilterTabsView.m10593$$Nest$fgetanimationValue(r155)), ColorUtils.blendARGB(r63, r73, FilterTabsView.m10593$$Nest$fgetanimationValue(this.this$0)), FilterTabsView.m10590$$Nest$fgetanimatingIndicatorProgress(this.this$0)));
            goto L62
        L55:
            if (FilterTabsView.m10609$$Nest$fgetmanualScrollingToPosition(this.this$0) == (-1)) goto L60;
        L43:
            if (FilterTabsView.m10589$$Nest$fgetanimatingIndicator(r15) == false) goto L45;
        L46:
            int r78 = this.currentTab.id;
            if (r78 == r22) goto L51;
            if (r78 == r42) goto L51;
        L50:
            FilterTabsView r79 = this.this$0;
            r79.textPaint.setColor(Theme.getColor(r62, FilterTabsView.m10613$$Nest$fgetresourcesProvider(r79)));
        L51:
            FilterTabsView r710 = this.this$0;
            r710.textPaint.setColor(ColorUtils.blendARGB(Theme.getColor(r8, FilterTabsView.m10613$$Nest$fgetresourcesProvider(r710)), Theme.getColor(r62, FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)), FilterTabsView.m10590$$Nest$fgetanimatingIndicatorProgress(this.this$0)));
            goto L62
        L45:
            if (FilterTabsView.m10608$$Nest$fgetmanualScrollingToId(this.this$0) == (-1)) goto L50;
        L39:
            r62 = FilterTabsView.m10619$$Nest$fgetunactiveTextColorKey(r7);
            r72 = FilterTabsView.m10585$$Nest$fgetaUnactiveTextColorKey(this.this$0);
            r8 = FilterTabsView.m10586$$Nest$fgetactiveTextColorKey(this.this$0);
            r9 = FilterTabsView.m10585$$Nest$fgetaUnactiveTextColorKey(this.this$0);
            r10 = Theme.key_chats_tabUnreadUnactiveBackground;
            r16 = Theme.key_chats_tabUnreadActiveBackground;
            goto L40
        L34:
            r22 = FilterTabsView.m10614$$Nest$fgetselectedTabId(r4);
            r42 = FilterTabsView.m10611$$Nest$fgetpreviousId(this.this$0);
            goto L35
        L17:
            if (FilterTabsView.m10599$$Nest$fgeteditingAnimationProgress(this.this$0) == 0.0f) goto L30;
            r1.save();
            float r517 = FilterTabsView.m10599$$Nest$fgeteditingAnimationProgress(this.this$0);
            float r818 = -1.0f;
            if ((this.currentPosition % 2) != 0) goto L21;
            float r711 = 1.0f;
        L22:
            float r518 = (float) Math.sin((((r517 * r711) + (r6 % 2)) * 3.141592653589793d) * 2.5d);
            double r620 = (SystemClock.elapsedRealtime() / 400.0f) * 3.141592653589793d;
            if ((this.currentPosition % 2) != 0) goto L25;
            float r95 = 1.0f;
        L26:
            double r621 = (float) (r620 * r95);
            r17 = 400.0f;
            r18 = 1.0f;
            double r96 = Math.cos(r621) * AndroidUtilities.dp(0.33f);
            if ((this.currentPosition % 2) != 0) goto L29;
            r818 = 1.0f;
        L29:
            r1.translate((float) (r96 * r818), (float) (Math.sin(r621) * (-AndroidUtilities.dp(0.33f))));
            r1.rotate(r518 * 1.4f, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
            goto L31
        L25:
            r95 = -1.0f;
            goto L26
        L21:
            r711 = -1.0f;
        L13:
            r13 = false;
            goto L15
        L5:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L8;
            r12 = false;
            goto L10
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
            super.onInitializeAccessibilityNodeInfo(r5);
            if (this.currentTab != null) goto L5;
        L9:
            boolean r0 = false;
        L10:
            r5.setSelected(r0);
            r5.addAction(16);
            r5.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, LocaleController.getString(R.string.AccDescrOpenMenu2)));
            if (this.currentTab == null) goto L20;
            StringBuilder r02 = new StringBuilder();
            r02.append(this.currentTab.title);
            Tab r4 = this.currentTab;
            if (r4 == null) goto L15;
            int r42 = r4.counter;
        L16:
            if (r42 <= 0) goto L18;
            r02.append("\n");
            r02.append(LocaleController.formatPluralString("AccDescrUnreadCount", r42, new Object[0]));
        L18:
            r5.setContentDescription(r02);
            return;
        L15:
            r42 = 0;
            goto L16
        L20:
            return;
        L5:
            if (FilterTabsView.m10614$$Nest$fgetselectedTabId(this.this$0) == (-1)) goto L9;
            if (this.currentTab.id != FilterTabsView.m10614$$Nest$fgetselectedTabId(this.this$0)) goto L9;
            r0 = true;
            goto L10
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            setMeasuredDimension((this.currentTab.getWidth(false) + FolderIcons.getPaddingTab()) + FilterTabsView.m10588$$Nest$fgetadditionalTabWidth(this.this$0), View.MeasureSpec.getSize(r3));
        }

        public void setTab(Tab r6, int r7) {
            this.currentTab = r6;
            this.currentPosition = r7;
            setContentDescription(r6.title);
            requestLayout();
            boolean r62 = this.currentNoanimate;
            Tab r72 = this.currentTab;
            if (r72 != null) goto L5;
        L7:
            boolean r73 = false;
        L8:
            if (r62 == r73) goto L30;
            AnimatedEmojiSpan.release(this, this.textLayoutEmojis);
            AnimatedEmojiSpan.release(this, this.titleAnimateInLayoutEmojis);
            AnimatedEmojiSpan.release(this, this.titleAnimateOutLayoutEmojis);
            AnimatedEmojiSpan.release(this, this.titleAnimateStableLayoutEmojis);
            if (this.attached == false) goto L28;
            int r74 = 26;
            if (this.currentTab.noanimate == false) goto L14;
            int r63 = 26;
        L15:
            this.textLayoutEmojis = AnimatedEmojiSpan.update(r63, this, this.textLayoutEmojis, new Layout[]{this.textLayout});
            if (this.currentTab.noanimate == false) goto L18;
            int r64 = 26;
        L19:
            this.titleAnimateInLayoutEmojis = AnimatedEmojiSpan.update(r64, this, this.titleAnimateInLayoutEmojis, new Layout[]{this.titleAnimateInLayout});
            if (this.currentTab.noanimate == false) goto L22;
            int r65 = 26;
        L23:
            this.titleAnimateOutLayoutEmojis = AnimatedEmojiSpan.update(r65, this, this.titleAnimateOutLayoutEmojis, new Layout[]{this.titleAnimateOutLayout});
            if (this.currentTab.noanimate == true) goto L27;
            r74 = 0;
        L27:
            this.titleAnimateStableLayoutEmojis = AnimatedEmojiSpan.update(r74, this, this.titleAnimateStableLayoutEmojis, new Layout[]{this.titleAnimateStableLayout});
            goto L28
        L22:
            r65 = 0;
            goto L23
        L18:
            r64 = 0;
            goto L19
        L14:
            r63 = 0;
        L28:
            this.currentNoanimate = this.currentTab.noanimate;
            return;
        L30:
            return;
        L5:
            if (r72.noanimate == false) goto L7;
            r73 = true;
            goto L8
        }

        public void shakeLockIcon(float r6, int r7) {
            if (r7 != 6) goto L6;
            this.locIconXOffset = 0.0f;
            return;
        L6:
            AnimatorSet r0 = new AnimatorSet();
            ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, AndroidUtilities.dp(r6)});
            r2.addUpdateListener(new FilterTabsView$TabView$$ExternalSyntheticLambda0(this));
            r0.playTogether(new Animator[]{r2});
            r0.setDuration(50);
            r0.addListener(new AnonymousClass1(this, r7, r6));
            r0.start();
        }
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        private final Runnable resetDefaultPosition;
        final /* synthetic */ FilterTabsView this$0;

        /* renamed from: $r8$lambda$9ErsA5445WtFt-4A__3f3jR2Wh0, reason: not valid java name */
        public static /* synthetic */ void m10650$r8$lambda$9ErsA5445WtFt4A__3f3jR2Wh0(TouchHelperCallback r0) {
            r0.lambda$new$0();
        }

        public TouchHelperCallback(FilterTabsView r1) {
            this.this$0 = r1;
            this.resetDefaultPosition = new FilterTabsView$TouchHelperCallback$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$new$0() {
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L14;
            int r1 = 0;
        L7:
            if (r1 >= FilterTabsView.m10617$$Nest$fgettabs(this.this$0).size()) goto L19;
            if (((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(r1)).isDefault == false) goto L13;
            if (r1 == 0) goto L13;
            FilterTabsView.m10587$$Nest$fgetadapter(this.this$0).moveElementToStart(r1);
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).scrollToPosition(0);
            this.this$0.onDefaultTabMoved();
            return;
        L13:
            r1 = r1 + 1;
            goto L7
        L19:
            return;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
            r2.itemView.setBackground(null);
            r2.itemView.setTag(R.id.dragging, null);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r2, RecyclerView.ViewHolder r3) {
            if (ExteraConfig.getHideAllChats() == true) goto L15;
            if (FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0) == false) goto L13;
            if (r3.getAdapterPosition() != 0) goto L15;
            if (((Tab) FilterTabsView.m10617$$Nest$fgettabs(this.this$0).get(0)).isDefault == false) goto L15;
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L15;
        L13:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        L15:
            return ItemTouchHelper.Callback.makeMovementFlags(12, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return FilterTabsView.m10603$$Nest$fgetisEditing(this.this$0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3) {
            if (ExteraConfig.getHideAllChats() == false) goto L5;
        L12:
            FilterTabsView.m10587$$Nest$fgetadapter(this.this$0).swapElements(r2.getAdapterPosition(), r3.getAdapterPosition());
            return true;
        L5:
            if (r2.getAdapterPosition() == 0) goto L9;
            if (r3.getAdapterPosition() != 0) goto L12;
        L9:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L12;
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r4, int r5) {
            if (r5 == 0) goto L4;
            FilterTabsView.m10605$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            r4.itemView.setPressed(true);
            r4.itemView.setBackgroundColor(Theme.getColor(FilterTabsView.m10594$$Nest$fgetbackgroundColorKey(this.this$0), FilterTabsView.m10613$$Nest$fgetresourcesProvider(this.this$0)));
        L5:
            super.onSelectedChanged(r4, r5);
            if (r4 == null) goto L13;
            View r3 = r4.itemView;
            int r42 = R.id.dragging;
            if (r5 != 2) goto L10;
            Boolean r52 = Boolean.TRUE;
        L11:
            r3.setTag(r42, r52);
            return;
        L10:
            r52 = null;
            goto L11
        L13:
            return;
        L4:
            AndroidUtilities.cancelRunOnUIThread(this.resetDefaultPosition);
            AndroidUtilities.runOnUIThread(this.resetDefaultPosition, 320);
            goto L5
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public static /* synthetic */ void $r8$lambda$AIINp7iYPWr04rmofQhQbBfS2JE(FilterTabsView r0, View r1, int r2, float r3, float r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Bz-Qz5y6icLbkdkE40-xG-gPlzs, reason: not valid java name */
    public static /* synthetic */ boolean m10581$r8$lambda$BzQz5y6icLbkdkE40xGgPlzs(FilterTabsView r0, View r1, int r2) {
        return r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LUlLhaIciS00vlgmp_dSDwB023s(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$fgetaActiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10582$$Nest$fgetaActiveTextColorKey(FilterTabsView r0) {
        return r0.aActiveTextColorKey;
    }

    /* renamed from: -$$Nest$fgetaBackgroundColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10583$$Nest$fgetaBackgroundColorKey(FilterTabsView r0) {
        return r0.aBackgroundColorKey;
    }

    /* renamed from: -$$Nest$fgetaTabLineColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10584$$Nest$fgetaTabLineColorKey(FilterTabsView r0) {
        return r0.aTabLineColorKey;
    }

    /* renamed from: -$$Nest$fgetaUnactiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10585$$Nest$fgetaUnactiveTextColorKey(FilterTabsView r0) {
        return r0.aUnactiveTextColorKey;
    }

    /* renamed from: -$$Nest$fgetactiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10586$$Nest$fgetactiveTextColorKey(FilterTabsView r0) {
        return r0.activeTextColorKey;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m10587$$Nest$fgetadapter(FilterTabsView r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetadditionalTabWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10588$$Nest$fgetadditionalTabWidth(FilterTabsView r0) {
        return r0.additionalTabWidth;
    }

    /* renamed from: -$$Nest$fgetanimatingIndicator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10589$$Nest$fgetanimatingIndicator(FilterTabsView r0) {
        return r0.animatingIndicator;
    }

    /* renamed from: -$$Nest$fgetanimatingIndicatorProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10590$$Nest$fgetanimatingIndicatorProgress(FilterTabsView r0) {
        return r0.animatingIndicatorProgress;
    }

    /* renamed from: -$$Nest$fgetanimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10591$$Nest$fgetanimationRunnable(FilterTabsView r0) {
        return r0.animationRunnable;
    }

    /* renamed from: -$$Nest$fgetanimationTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10592$$Nest$fgetanimationTime(FilterTabsView r0) {
        return r0.animationTime;
    }

    /* renamed from: -$$Nest$fgetanimationValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10593$$Nest$fgetanimationValue(FilterTabsView r0) {
        return r0.animationValue;
    }

    /* renamed from: -$$Nest$fgetbackgroundColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10594$$Nest$fgetbackgroundColorKey(FilterTabsView r0) {
        return r0.backgroundColorKey;
    }

    /* renamed from: -$$Nest$fgetcounterPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m10595$$Nest$fgetcounterPaint(FilterTabsView r0) {
        return r0.counterPaint;
    }

    /* renamed from: -$$Nest$fgetcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10596$$Nest$fgetcurrentPosition(FilterTabsView r0) {
        return r0.currentPosition;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FilterTabsViewDelegate m10597$$Nest$fgetdelegate(FilterTabsView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdeletePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m10598$$Nest$fgetdeletePaint(FilterTabsView r0) {
        return r0.deletePaint;
    }

    /* renamed from: -$$Nest$fgeteditingAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10599$$Nest$fgeteditingAnimationProgress(FilterTabsView r0) {
        return r0.editingAnimationProgress;
    }

    /* renamed from: -$$Nest$fgeteditingStartAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10600$$Nest$fgeteditingStartAnimationProgress(FilterTabsView r0) {
        return r0.editingStartAnimationProgress;
    }

    /* renamed from: -$$Nest$fgetemojiColorFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorFilter m10601$$Nest$fgetemojiColorFilter(FilterTabsView r0) {
        return r0.emojiColorFilter;
    }

    /* renamed from: -$$Nest$fgetinterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CubicBezierInterpolator m10602$$Nest$fgetinterpolator(FilterTabsView r0) {
        return r0.interpolator;
    }

    /* renamed from: -$$Nest$fgetisEditing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10603$$Nest$fgetisEditing(FilterTabsView r0) {
        return r0.isEditing;
    }

    /* renamed from: -$$Nest$fgetlastAnimationTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m10604$$Nest$fgetlastAnimationTime(FilterTabsView r2) {
        return r2.lastAnimationTime;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m10605$$Nest$fgetlistView(FilterTabsView r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlockDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m10606$$Nest$fgetlockDrawable(FilterTabsView r0) {
        return r0.lockDrawable;
    }

    /* renamed from: -$$Nest$fgetlockDrawableColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10607$$Nest$fgetlockDrawableColor(FilterTabsView r0) {
        return r0.lockDrawableColor;
    }

    /* renamed from: -$$Nest$fgetmanualScrollingToId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10608$$Nest$fgetmanualScrollingToId(FilterTabsView r0) {
        return r0.manualScrollingToId;
    }

    /* renamed from: -$$Nest$fgetmanualScrollingToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10609$$Nest$fgetmanualScrollingToPosition(FilterTabsView r0) {
        return r0.manualScrollingToPosition;
    }

    /* renamed from: -$$Nest$fgetpositionToStableId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m10610$$Nest$fgetpositionToStableId(FilterTabsView r0) {
        return r0.positionToStableId;
    }

    /* renamed from: -$$Nest$fgetpreviousId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10611$$Nest$fgetpreviousId(FilterTabsView r0) {
        return r0.previousId;
    }

    /* renamed from: -$$Nest$fgetpreviousPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10612$$Nest$fgetpreviousPosition(FilterTabsView r0) {
        return r0.previousPosition;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m10613$$Nest$fgetresourcesProvider(FilterTabsView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetselectedTabId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10614$$Nest$fgetselectedTabId(FilterTabsView r0) {
        return r0.selectedTabId;
    }

    /* renamed from: -$$Nest$fgetselectorDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GradientDrawable m10615$$Nest$fgetselectorDrawable(FilterTabsView r0) {
        return r0.selectorDrawable;
    }

    /* renamed from: -$$Nest$fgettabLineColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10616$$Nest$fgettabLineColorKey(FilterTabsView r0) {
        return r0.tabLineColorKey;
    }

    /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10617$$Nest$fgettabs(FilterTabsView r0) {
        return r0.tabs;
    }

    /* renamed from: -$$Nest$fgettextCounterPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m10618$$Nest$fgettextCounterPaint(FilterTabsView r0) {
        return r0.textCounterPaint;
    }

    /* renamed from: -$$Nest$fgetunactiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10619$$Nest$fgetunactiveTextColorKey(FilterTabsView r0) {
        return r0.unactiveTextColorKey;
    }

    /* renamed from: -$$Nest$fputaActiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10620$$Nest$fputaActiveTextColorKey(FilterTabsView r0, int r1) {
        r0.aActiveTextColorKey = r1;
    }

    /* renamed from: -$$Nest$fputaBackgroundColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10621$$Nest$fputaBackgroundColorKey(FilterTabsView r0, int r1) {
        r0.aBackgroundColorKey = r1;
    }

    /* renamed from: -$$Nest$fputaTabLineColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10622$$Nest$fputaTabLineColorKey(FilterTabsView r0, int r1) {
        r0.aTabLineColorKey = r1;
    }

    /* renamed from: -$$Nest$fputaUnactiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10623$$Nest$fputaUnactiveTextColorKey(FilterTabsView r0, int r1) {
        r0.aUnactiveTextColorKey = r1;
    }

    /* renamed from: -$$Nest$fputactiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10624$$Nest$fputactiveTextColorKey(FilterTabsView r0, int r1) {
        r0.activeTextColorKey = r1;
    }

    /* renamed from: -$$Nest$fputanimatingIndicator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10625$$Nest$fputanimatingIndicator(FilterTabsView r0, boolean r1) {
        r0.animatingIndicator = r1;
    }

    /* renamed from: -$$Nest$fputanimationTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10626$$Nest$fputanimationTime(FilterTabsView r0, float r1) {
        r0.animationTime = r1;
    }

    /* renamed from: -$$Nest$fputanimationValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10627$$Nest$fputanimationValue(FilterTabsView r0, float r1) {
        r0.animationValue = r1;
    }

    /* renamed from: -$$Nest$fputbackgroundColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10628$$Nest$fputbackgroundColorKey(FilterTabsView r0, int r1) {
        r0.backgroundColorKey = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10629$$Nest$fputcurrentPosition(FilterTabsView r0, int r1) {
        r0.currentPosition = r1;
    }

    /* renamed from: -$$Nest$fputemojiColorFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10630$$Nest$fputemojiColorFilter(FilterTabsView r0, ColorFilter r1) {
        r0.emojiColorFilter = r1;
    }

    /* renamed from: -$$Nest$fputlockDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10631$$Nest$fputlockDrawable(FilterTabsView r0, Drawable r1) {
        r0.lockDrawable = r1;
    }

    /* renamed from: -$$Nest$fputlockDrawableColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10632$$Nest$fputlockDrawableColor(FilterTabsView r0, int r1) {
        r0.lockDrawableColor = r1;
    }

    /* renamed from: -$$Nest$fputorderChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10633$$Nest$fputorderChanged(FilterTabsView r0, boolean r1) {
        r0.orderChanged = r1;
    }

    /* renamed from: -$$Nest$fputpreviousId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10634$$Nest$fputpreviousId(FilterTabsView r0, int r1) {
        r0.previousId = r1;
    }

    /* renamed from: -$$Nest$fputpreviousPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10635$$Nest$fputpreviousPosition(FilterTabsView r0, int r1) {
        r0.previousPosition = r1;
    }

    /* renamed from: -$$Nest$fputselectedTabId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10636$$Nest$fputselectedTabId(FilterTabsView r0, int r1) {
        r0.selectedTabId = r1;
    }

    /* renamed from: -$$Nest$fputtabLineColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10637$$Nest$fputtabLineColorKey(FilterTabsView r0, int r1) {
        r0.tabLineColorKey = r1;
    }

    /* renamed from: -$$Nest$fputunactiveTextColorKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10638$$Nest$fputunactiveTextColorKey(FilterTabsView r0, int r1) {
        r0.unactiveTextColorKey = r1;
    }

    /* renamed from: -$$Nest$mupdateTabsWidths, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10639$$Nest$mupdateTabsWidths(FilterTabsView r0) {
        r0.updateTabsWidths();
    }

    public FilterTabsView(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        TextPaint r2 = new TextPaint(1);
        this.textCounterPaint = r2;
        TextPaint r3 = new TextPaint(1);
        this.deletePaint = r3;
        this.counterPaint = new Paint(1);
        this.emojiColorFilter = new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_IN);
        this.tabs = new ArrayList();
        this.selectedTabId = -1;
        this.manualScrollingToPosition = -1;
        this.manualScrollingToId = -1;
        this.scrollingToChild = -1;
        this.tabLineColorKey = Theme.key_actionBarTabLine;
        this.activeTextColorKey = Theme.key_actionBarTabActiveText;
        this.unactiveTextColorKey = Theme.key_actionBarTabUnactiveText;
        this.selectorColorKey = Theme.key_actionBarTabSelector;
        this.backgroundColorKey = Theme.key_actionBarDefault;
        this.aTabLineColorKey = -1;
        this.aActiveTextColorKey = -1;
        this.aUnactiveTextColorKey = -1;
        this.aBackgroundColorKey = -1;
        this.interpolator = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.positionToId = new SparseIntArray(5);
        this.positionToStableId = new SparseIntArray(5);
        this.idToPosition = new SparseIntArray(5);
        this.positionToWidth = new SparseIntArray(5);
        this.positionToCount = new SparseIntArray(5);
        this.positionToX = new SparseIntArray(5);
        this.animationRunnable = new AnonymousClass1(this);
        this.COLORS = new AnonymousClass2(this, "animationValue");
        this.clipPath = new Path();
        this.oldAnimatedTab = -1;
        this.resourcesProvider = r11;
        r2.setTextSize(AndroidUtilities.dpf2(11.0f));
        r2.setTypeface(AndroidUtilities.bold());
        r0.setTextSize(AndroidUtilities.dpf2(14.0f));
        r0.setTypeface(AndroidUtilities.bold());
        r3.setStyle(Paint.Style.STROKE);
        r3.setStrokeCap(Paint.Cap.ROUND);
        r3.setStrokeWidth(AndroidUtilities.dp(1.5f));
        GradientDrawable r02 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        this.selectorDrawable = r02;
        float r22 = AndroidUtilities.dpf2(14.0f);
        r02.setCornerRadii(new float[]{r22, r22, r22, r22, r22, r22, r22, r22});
        r02.setColor(Theme.getColor(this.tabLineColorKey, r11));
        setHorizontalScrollBarEnabled(false);
        AnonymousClass3 r03 = new AnonymousClass3(this, r10);
        this.listView = r03;
        r03.setOverScrollMode(2);
        r03.setClipChildren(false);
        AnonymousClass4 r23 = new AnonymousClass4(this);
        this.itemAnimator = r23;
        r23.setDelayAnimations(false);
        r03.setItemAnimator(this.itemAnimator);
        r03.setSelectorType(9);
        r03.setSelectorRadius(6);
        r03.setSelectorDrawableColor(Theme.getColor(this.selectorColorKey, r11));
        AnonymousClass5 r112 = new AnonymousClass5(this, r10, 0, false);
        this.layoutManager = r112;
        r03.setLayoutManager(r112);
        new ItemTouchHelper(new TouchHelperCallback(this)).attachToRecyclerView(r03);
        int r113 = Math.max(0, AndroidUtilities.dp(23.5f) - (FolderIcons.getPaddingTab() / 2));
        this.listViewPaddingH = r113;
        r03.setPadding(r113, 0, r113, 0);
        r03.setClipToPadding(false);
        r03.setDrawSelectorBehind(true);
        ListAdapter r114 = new ListAdapter(this, r10);
        this.adapter = r114;
        r114.setHasStableIds(true);
        r03.setAdapter(r114);
        r03.setOnItemClickListener(new FilterTabsView$$ExternalSyntheticLambda0(this));
        r03.setOnItemLongClickListener(new FilterTabsView$$ExternalSyntheticLambda1(this));
        r03.setOnScrollListener(new AnonymousClass6(this));
        r03.setAdaptiveOverScroll();
        addView(r03, LayoutHelper.createFrame(-1, -1.0f));
    }

    private void drawSelector(Canvas r14) {
        int r0 = getMeasuredHeight();
        this.selectorDrawable.setAlpha((int) (this.listView.getAlpha() * 255.0f));
        if (this.animatingIndicator == false) goto L5;
    L19:
        int r1 = this.layoutManager.findFirstVisibleItemPosition();
        if (r1 != (-1)) goto L21;
    L18:
        float r2 = 0.0f;
        float r6 = 0.0f;
    L42:
        if (r2 == 0.0f) goto L45;
        r14.save();
        r14.translate(this.listView.getTranslationX(), 0.0f);
        r14.scale(this.listView.getScaleX(), 1.0f, this.listView.getPivotX() + this.listView.getX(), this.listView.getPivotY());
        float r12 = this.additionalTabWidth / 2.0f;
        int r02 = (r0 / 2) - AndroidUtilities.dp(14.0f);
        this.selectorDrawable.setBounds((int) ((r6 - AndroidUtilities.dp(12.5f)) - r12), r02, (int) (((r6 + r2) + AndroidUtilities.dp(12.5f)) + r12), AndroidUtilities.dp(28.0f) + r02);
        this.selectorDrawable.setAlpha(31);
        this.selectorDrawable.draw(r14);
        r14.restore();
        return;
    L45:
        return;
    L21:
        RecyclerView.ViewHolder r22 = this.listView.findViewHolderForAdapterPosition(r1);
        if (r22 == null) goto L18;
        if (this.animatingIndicator == false) goto L26;
        int r62 = this.previousPosition;
        int r7 = this.currentPosition;
    L27:
        int r8 = this.positionToX.get(r62);
        int r9 = this.positionToX.get(r7);
        int r10 = this.positionToWidth.get(r62);
        int r11 = this.positionToWidth.get(r7);
        if (this.positionToCount.get(r62) == 0) goto L30;
        float r63 = 1.0f;
    L32:
        if (this.positionToCount.get(r7) == 0) goto L34;
        float r72 = 1.0f;
    L36:
        if (this.additionalTabWidth == 0) goto L39;
        float r13 = AndroidUtilities.lerp(r8, r9, this.animatingIndicatorProgress);
        int r23 = FolderIcons.getPaddingTab();
    L38:
        float r15 = r13 + (r23 / 2.0f);
        r2 = AndroidUtilities.lerp(r10, r11, this.animatingIndicatorProgress);
        AndroidUtilities.lerp(r63, r72, this.animatingIndicatorProgress);
        r6 = r15;
        goto L42
    L39:
        r13 = AndroidUtilities.lerp(r8, r9, this.animatingIndicatorProgress) - (this.positionToX.get(r1) - r22.itemView.getLeft());
        r23 = FolderIcons.getPaddingTab();
        goto L38
    L34:
        r72 = 0.0f;
        goto L36
    L30:
        r63 = 0.0f;
        goto L32
    L26:
        r62 = this.currentPosition;
        r7 = this.manualScrollingToPosition;
        goto L27
    L5:
        if (this.manualScrollingToPosition != (-1)) goto L19;
        RecyclerView.ViewHolder r16 = this.listView.findViewHolderForAdapterPosition(this.currentPosition);
        if (r16 == null) goto L18;
        TabView r17 = (TabView) r16.itemView;
        if (TabView.m10643$$Nest$fgetanimateTabWidth(r17) == false) goto L12;
        float r24 = AndroidUtilities.lerp(TabView.m10642$$Nest$fgetanimateFromTabWidth(r17), TabView.m10647$$Nest$fgettabWidth(r17), r17.changeProgress);
    L13:
        r2 = Math.max(AndroidUtilities.dp(16.0f), r24);
        if (TabView.m10643$$Nest$fgetanimateTabWidth(r17) == false) goto L16;
        float r64 = AndroidUtilities.lerp(TabView.m10642$$Nest$fgetanimateFromTabWidth(r17) + AndroidUtilities.dp(20.0f), r17.getMeasuredWidth(), r17.changeProgress);
    L17:
        r6 = (int) (r17.getX() + ((r64 - r2) / 2.0f));
        TabView.m10646$$Nest$fgettabCounterVisible(r17);
        goto L42
    L16:
        r64 = r17.getMeasuredWidth();
        goto L17
    L12:
        r24 = TabView.m10647$$Nest$fgettabWidth(r17);
        goto L13
    }

    private Tab findDefaultTab() {
        int r0 = 0;
    L4:
        if (r0 >= this.tabs.size()) goto L10;
        if (this.tabs.get(r0).isDefault == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.tabs.get(r0);
    L10:
        return null;
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, float r3, float r4) {
        if (this.delegate.canPerformActions() == false) goto L27;
        TabView r12 = (TabView) r1;
        if (this.isEditing == false) goto L17;
        if (r2 == 0) goto L9;
    L10:
        float r22 = AndroidUtilities.dp(6.0f);
        if ((TabView.m10645$$Nest$fgetrect(r12).left - r22) < r3) goto L13;
        return;
    L13:
        if ((TabView.m10645$$Nest$fgetrect(r12).right + r22) <= r3) goto L26;
        this.delegate.onDeletePressed(TabView.m10644$$Nest$fgetcurrentTab(r12).id);
        return;
    L26:
        return;
    L9:
        if (ExteraConfig.getHideAllChats() == true) goto L10;
        return;
    L17:
        if (r2 != this.currentPosition) goto L22;
        FilterTabsViewDelegate r32 = this.delegate;
        if (r32 == null) goto L22;
        r32.onSamePageSelected();
        return;
    L22:
        scrollToTab(TabView.m10644$$Nest$fgetcurrentTab(r12), r2);
        return;
    }

    private /* synthetic */ boolean lambda$new$1(View r5, int r6) {
        if (this.delegate.canPerformActions() == true) goto L5;
    L15:
        return false;
    L5:
        if (this.isEditing == true) goto L15;
        FilterTabsViewDelegate r0 = this.delegate;
        TabView r52 = (TabView) r5;
        if (r6 != this.currentPosition) goto L9;
        boolean r62 = true;
    L11:
        if (r0.didSelectTab(r52, r62) == false) goto L15;
        this.listView.hideSelector(true);
        return true;
    L9:
        r62 = false;
        goto L11
    }

    private void scrollToChild(int r2, boolean r3) {
        if (this.tabs.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.scrollingToChild == r2) goto L17;
        if (r2 >= 0) goto L8;
        return;
    L8:
        if (r2 >= this.tabs.size()) goto L19;
        this.scrollingToChild = r2;
        RecyclerListView r1 = this.listView;
        if (r3 == false) goto L14;
        r1.smoothScrollToPosition(r2);
        return;
    L14:
        r1.scrollToPosition(r2);
        return;
    L19:
        return;
    }

    private void updateTabsWidths() {
        this.positionToX.clear();
        this.positionToWidth.clear();
        this.positionToCount.clear();
        int r0 = this.listViewPaddingH;
        int r1 = this.tabs.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        int r4 = this.tabs.get(r3).getWidth(false);
        this.positionToWidth.put(r3, r4);
        this.positionToCount.put(r3, this.tabs.get(r3).counter);
        this.positionToX.put(r3, (this.additionalTabWidth / 2) + r0);
        r0 = r0 + ((r4 + FolderIcons.getPaddingTab()) + this.additionalTabWidth);
        r3 = r3 + 1;
        goto L3
    }

    public void addTab(int r7, int r8, String r9, String r10, ArrayList<TLRPC.MessageEntity> r11, boolean r12, boolean r13, boolean r14) {
        int r0 = this.tabs.size();
        if (r0 == 0) goto L5;
    L7:
        this.positionToId.put(r0, r7);
        this.positionToStableId.put(r0, r8);
        this.idToPosition.put(r7, r0);
        int r4 = this.selectedTabId;
        if (r4 == (-1)) goto L11;
        if (r4 != r7) goto L11;
        this.currentPosition = r0;
    L11:
        Tab r02 = new Tab(this, r7, text(r9, r11), r10, r12);
        r02.isDefault = r13;
        r02.isLocked = r14;
        this.allTabsWidth += r02.getWidth(true) + FolderIcons.getPaddingTab();
        this.tabs.add(r02);
        return;
    L5:
        if (this.selectedTabId != (-1)) goto L7;
        this.selectedTabId = r7;
        goto L7
    }

    public void animateColorsTo(int r2, int r3, int r4, int r5, int r6) {
        AnimatorSet r0 = this.colorChangeAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.aTabLineColorKey = r2;
        this.aActiveTextColorKey = r3;
        this.aUnactiveTextColorKey = r4;
        this.aBackgroundColorKey = r6;
        this.selectorColorKey = r5;
        this.listView.setSelectorDrawableColor(Theme.getColor(r5, this.resourcesProvider));
        AnimatorSet r22 = new AnimatorSet();
        this.colorChangeAnimator = r22;
        r22.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, this.COLORS, new float[]{0.0f, 1.0f})});
        this.colorChangeAnimator.setDuration(320);
        this.colorChangeAnimator.addListener(new AnonymousClass7(this));
        this.colorChangeAnimator.start();
    }

    public void checkTabsCounter() {
        int r0 = this.tabs.size();
        int r1 = 0;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L20;
        Tab r4 = this.tabs.get(r2);
        if (r4.counter == this.delegate.getTabCounter(r4.id)) goto L19;
        if (this.delegate.getTabCounter(r4.id) < 0) goto L19;
        if (this.positionToWidth.get(r2) != r4.getWidth(true)) goto L15;
        if (this.invalidated == true) goto L15;
        r3 = true;
    L15:
        this.invalidated = true;
        requestLayout();
        this.allTabsWidth = 0;
    L16:
        if (r1 >= r0) goto L18;
        this.allTabsWidth += this.tabs.get(r1).getWidth(true) + FolderIcons.getPaddingTab();
        r1 = r1 + 1;
        goto L16
    L18:
        r3 = true;
    L19:
        r2 = r2 + 1;
    L20:
        if (r3 == false) goto L30;
        this.listView.setItemAnimator(this.itemAnimator);
        this.adapter.notifyDataSetChanged();
        return;
    }

    public boolean currentTabIsDefault() {
        Tab r0 = findDefaultTab();
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0.id != this.selectedTabId) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r2) {
        r2.save();
        r2.clipPath(this.clipPath);
        super.dispatchDraw(r2);
        r2.restore();
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r9, View r10, long r11) {
        boolean r112 = super.drawChild(r9, r10, r11);
        if (r10 != this.listView) goto L5;
        drawSelector(r9);
    L5:
        long r92 = SystemClock.elapsedRealtime();
        long r0 = Math.min(17, r92 - this.lastEditingAnimationTime);
        this.lastEditingAnimationTime = r92;
        boolean r93 = this.isEditing;
        boolean r102 = false;
        boolean r2 = true;
        if (r93 == false) goto L8;
    L9:
        boolean r4 = this.editingForwardAnimation;
        float r5 = this.editingAnimationProgress;
        if (r4 == false) goto L25;
        if (r5 > 0.0f) goto L14;
        boolean r42 = true;
    L15:
        float r52 = r5 + (r0 / 420.0f);
        this.editingAnimationProgress = r52;
        if (r93 == true) goto L22;
        if (r42 == false) goto L22;
        if (r52 < 0.0f) goto L22;
        this.editingAnimationProgress = 0.0f;
    L22:
        if (this.editingAnimationProgress < 1.0f) goto L36;
        this.editingAnimationProgress = 1.0f;
        this.editingForwardAnimation = false;
    L36:
        r102 = true;
    L38:
        if (r93 == false) goto L44;
        float r94 = this.editingStartAnimationProgress;
        if (r94 >= 1.0f) goto L50;
        float r95 = r94 + (r0 / 180.0f);
        this.editingStartAnimationProgress = r95;
        if (r95 <= 1.0f) goto L51;
        this.editingStartAnimationProgress = 1.0f;
    L51:
        if (r2 == false) goto L53;
        this.listView.invalidateViews();
        this.listView.invalidate();
        invalidate();
    L53:
        return r112;
    L50:
        r2 = r102;
        goto L51
    L44:
        if (r93 == true) goto L50;
        float r96 = this.editingStartAnimationProgress;
        if (r96 <= 0.0f) goto L50;
        float r97 = r96 - (r0 / 180.0f);
        this.editingStartAnimationProgress = r97;
        if (r97 >= 0.0f) goto L51;
        this.editingStartAnimationProgress = 0.0f;
        goto L51
    L14:
        r42 = false;
        goto L15
    L25:
        if (r5 < 0.0f) goto L27;
        r102 = true;
    L27:
        float r53 = r5 - (r0 / 420.0f);
        this.editingAnimationProgress = r53;
        if (r93 == true) goto L34;
        if (r102 == false) goto L34;
        if (r53 > 0.0f) goto L34;
        this.editingAnimationProgress = 0.0f;
    L34:
        if (this.editingAnimationProgress > (-1.0f)) goto L36;
        this.editingAnimationProgress = -1.0f;
        this.editingForwardAnimation = true;
        goto L36
    L8:
        if (this.editingAnimationProgress == 0.0f) goto L38;
        goto L9
    }

    public void finishAddingTabs(boolean r3) {
        RecyclerListView r0 = this.listView;
        if (r3 == false) goto L5;
        DefaultItemAnimator r32 = this.itemAnimator;
    L6:
        r0.setItemAnimator(r32);
        this.adapter.notifyDataSetChanged();
        this.delegate.onTabSelected(this.tabs.get(this.currentPosition), false, false);
        this.oldAnimatedTab = this.currentPosition;
        return;
    L5:
        r32 = null;
        goto L6
    }

    public int getCurrentTabId() {
        return this.selectedTabId;
    }

    public int getCurrentTabStableId() {
        return this.positionToStableId.get(this.currentPosition, -1);
    }

    public int getDefaultTabId() {
        Tab r0 = findDefaultTab();
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.id;
    }

    public int getFirstTabId() {
        return this.positionToId.get(0, 0);
    }

    public int getLastTabId() {
        return this.positionToId.get(getTabsCount() - 1, 0);
    }

    public RecyclerListView getListView() {
        return this.listView;
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

    public int getSelectorColorKey() {
        return this.selectorColorKey;
    }

    public Drawable getSelectorDrawable() {
        return this.selectorDrawable;
    }

    public int getStableId(int r2) {
        return this.positionToStableId.get(r2, -1);
    }

    public Tab getTab(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < getTabsCount()) goto L7;
        return null;
    L7:
        return this.tabs.get(r2);
    }

    public RecyclerListView getTabsContainer() {
        return this.listView;
    }

    public int getTabsCount() {
        return this.tabs.size();
    }

    public boolean isAnimatingIndicator() {
        return this.animatingIndicator;
    }

    public boolean isEditing() {
        return this.isEditing;
    }

    public boolean isEmpty() {
        return this.tabs.isEmpty();
    }

    public boolean isFirstTab() {
        if (this.currentPosition > 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isFirstTabSelected() {
        if (this.tabs.isEmpty() == false) goto L6;
        return true;
    L6:
        if (this.selectedTabId != this.tabs.get(0).id) goto L8;
        return true;
    L8:
        return false;
    }

    public boolean isLocked(int r4) {
        int r1 = 0;
    L4:
        if (r1 >= this.tabs.size()) goto L10;
        if (this.tabs.get(r1).id == r4) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return this.tabs.get(r1).isLocked;
    L10:
        return false;
    }

    public void notifyTabCounterChanged(int r6) {
        int r62 = this.idToPosition.get(r6, -1);
        if (r62 >= 0) goto L5;
        return;
    L5:
        if (r62 >= this.tabs.size()) goto L26;
        Tab r0 = this.tabs.get(r62);
        if (r0.counter != this.delegate.getTabCounter(r0.id)) goto L10;
        return;
    L10:
        if (this.delegate.getTabCounter(r0.id) < 0) goto L27;
        this.listView.invalidateViews();
        if (this.positionToWidth.get(r62) == r0.getWidth(true)) goto L15;
    L16:
        this.invalidated = true;
        requestLayout();
        this.listView.setItemAnimator(this.itemAnimator);
        ListAdapter r63 = this.adapter;
        if (r63 == null) goto L19;
        r63.notifyDataSetChanged();
    L19:
        int r64 = 0;
        this.allTabsWidth = 0;
        int r02 = this.tabs.size();
    L20:
        if (r64 >= r02) goto L28;
        this.allTabsWidth += this.tabs.get(r64).getWidth(true) + FolderIcons.getPaddingTab();
        r64 = r64 + 1;
        goto L20
    L28:
        return;
    L15:
        if (this.invalidated == true) goto L16;
        return;
    L27:
        return;
    }

    public void onDefaultTabMoved() {
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
        FilterTabsViewDelegate r0 = this.delegate;
        if (r0 == null) goto L12;
        r0.onPageScrolled(1.0f);
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r11, int r12) {
        if (this.tabs.isEmpty() == true) goto L31;
        int r0 = View.MeasureSpec.getSize(r11) - (this.listViewPaddingH * 2);
        Tab r1 = findDefaultTab();
        if (r1 != null) goto L9;
        if (ExteraConfig.getHideAllChats() == false) goto L31;
    L9:
        if (ExteraConfig.getHideAllChats() == true) goto L23;
        int r2 = 0;
        int r6 = 0;
    L12:
        if (r2 >= this.tabs.size()) goto L17;
        Tab r7 = this.tabs.get(r2);
        if (r7 == r1) goto L16;
        r6 = r6 + (r7.getWidth(true) + FolderIcons.getPaddingTab());
    L16:
        r2 = r2 + 1;
        goto L12
    L17:
        String r22 = LocaleController.getString(R.string.FilterAllChats);
        String r72 = LocaleController.getString(R.string.FilterAllChatsShort);
        r1.setTitle(r22, null, false);
        int r8 = r1.getWidth(true) + FolderIcons.getPaddingTab();
        if (this.isStaticAllChats == false) goto L20;
    L22:
        r1.setTitle(r22, null, false);
        this.allTabsWidth = (r6 + r1.getWidth(true)) + FolderIcons.getPaddingTab();
        goto L23
    L20:
        if ((r8 + r6) <= r0) goto L22;
        r22 = r72;
    L23:
        int r13 = this.allTabsWidth;
        int r23 = this.additionalTabWidth;
        if (r13 >= r0) goto L26;
        int r02 = (r0 - r13) / this.tabs.size();
    L27:
        this.additionalTabWidth = r02;
        if (r23 == r02) goto L30;
        this.ignoreLayout = true;
        RecyclerView.ItemAnimator r03 = this.listView.getItemAnimator();
        this.listView.setItemAnimator(null);
        this.adapter.notifyDataSetChanged();
        this.listView.setItemAnimator(r03);
        this.ignoreLayout = false;
    L30:
        updateTabsWidths();
        this.invalidated = false;
        goto L31
    L26:
        r02 = 0;
    L31:
        super.onMeasure(r11, r12);
    }

    @Override // android.view.View
    public void onSizeChanged(int r9, int r10, int r11, int r12) {
        super.onSizeChanged(r9, r10, r11, r12);
        this.clipPath.rewind();
        this.clipPath.addRoundRect(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), r9 - AndroidUtilities.dp(9.0f), r10 - AndroidUtilities.dp(9.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Path.Direction.CW);
    }

    public void removeTabs() {
        this.tabs.clear();
        this.positionToId.clear();
        this.positionToStableId.clear();
        this.idToPosition.clear();
        this.positionToWidth.clear();
        this.positionToCount.clear();
        this.positionToX.clear();
        this.allTabsWidth = 0;
        this.currentPosition = 0;
        this.selectedTabId = -1;
        this.previousPosition = 0;
        this.previousId = -1;
        this.scrollingToChild = -1;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void resetTabId() {
        this.selectedTabId = -1;
    }

    public void scrollToTab(Tab r7, int r8) {
        int r0 = this.currentPosition;
        if (r0 != r8) goto L8;
        if (this.selectedTabId != r7.id) goto L8;
        return;
    L8:
        if (r7.isLocked == false) goto L14;
        FilterTabsViewDelegate r6 = this.delegate;
        if (r6 == null) goto L25;
        r6.onPageSelected(r7, false);
        return;
    L25:
        return;
    L14:
        if (r0 >= r8) goto L16;
        boolean r3 = true;
    L17:
        this.scrollingToChild = -1;
        this.previousPosition = r0;
        this.previousId = this.selectedTabId;
        this.currentPosition = r8;
        this.selectedTabId = r7.id;
        if (this.animatingIndicator == false) goto L20;
        AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
        this.animatingIndicator = false;
    L20:
        this.animationTime = 0.0f;
        this.animatingIndicatorProgress = 0.0f;
        this.animatingIndicator = true;
        setEnabled(false);
        AndroidUtilities.runOnUIThread(this.animationRunnable, 16);
        FilterTabsViewDelegate r02 = this.delegate;
        if (r02 == null) goto L23;
        r02.onPageSelected(r7, r3);
        this.delegate.onTabSelected(r7, r3, true);
        this.oldAnimatedTab = this.currentPosition;
    L23:
        scrollToChild(r8, true);
        return;
    L16:
        r3 = false;
        goto L17
    }

    public void selectFirstTab() {
        if (this.tabs.isEmpty() == false) goto L5;
        return;
    L5:
        scrollToTab(this.tabs.get(0), 0);
    }

    public void selectLastTab() {
        if (this.tabs.isEmpty() == false) goto L5;
        return;
    L5:
        scrollToTab(this.tabs.get(r0.size() - 1), this.tabs.size() - 1);
    }

    public void selectTabWithId(int r9, float r10) {
        int r0 = this.idToPosition.get(r9, -1);
        if (r0 >= 0) goto L6;
        return;
    L6:
        if (r10 >= 0.0f) goto L9;
        r10 = 0.0f;
    L12:
        if (r10 <= 0.0f) goto L14;
        this.manualScrollingToPosition = r0;
        this.manualScrollingToId = r9;
    L15:
        this.animatingIndicatorProgress = r10;
        this.listView.invalidateViews();
        this.listView.invalidate();
        invalidate();
        boolean r3 = false;
        if (r10 >= 1.0f) goto L18;
        boolean r2 = true;
    L19:
        scrollToChild(r0, r2);
        if (r10 < 0.5f) goto L24;
        if (this.oldAnimatedTab == r0) goto L24;
    L27:
        int r22 = this.manualScrollingToPosition;
        int r7 = this.currentPosition;
        if (r22 == r7) goto L37;
        if (r10 >= 0.5f) goto L32;
        r0 = r7;
    L32:
        FilterTabsViewDelegate r23 = this.delegate;
        Tab r6 = this.tabs.get(r0);
        if (this.currentPosition >= r0) goto L35;
        r3 = true;
    L35:
        r23.onTabSelected(r6, r3, true);
        this.oldAnimatedTab = r0;
    L37:
        if (r10 < 1.0f) goto L40;
        this.manualScrollingToPosition = -1;
        this.manualScrollingToId = -1;
        this.currentPosition = r0;
        this.selectedTabId = r9;
        return;
    L40:
        return;
    L24:
        if (r10 > 0.5f) goto L37;
        if (this.oldAnimatedTab == this.currentPosition) goto L37;
    L18:
        r2 = false;
        goto L19
    L14:
        this.manualScrollingToPosition = -1;
        this.manualScrollingToId = -1;
        goto L15
    L9:
        if (r10 <= 1.0f) goto L12;
        r10 = 1.0f;
        goto L12
    }

    public boolean selectTabWithStableId(int r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.tabs.size()) goto L10;
        if (this.positionToStableId.get(r1, -1) == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        this.currentPosition = r1;
        this.selectedTabId = this.positionToId.get(r1);
        return true;
    L10:
        return false;
    }

    public void setAnimationIdicatorProgress(float r2) {
        this.animatingIndicatorProgress = r2;
        this.listView.invalidateViews();
        this.listView.invalidate();
        invalidate();
        FilterTabsViewDelegate r1 = this.delegate;
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
        this.backgroundColorKey = r5;
        this.activeTextColorKey = r2;
        this.unactiveTextColorKey = r3;
        this.selectorDrawable.setColor(Theme.getColor(r1, this.resourcesProvider));
        this.listView.setSelectorDrawableColor(Theme.getColor(r4, this.resourcesProvider));
        this.listView.invalidateViews();
        this.listView.invalidate();
        invalidate();
    }

    public void setDelegate(FilterTabsViewDelegate r1) {
        this.delegate = r1;
    }

    public void setIsEditing(boolean r8) {
        this.isEditing = r8;
        this.editingForwardAnimation = true;
        this.listView.invalidateViews();
        this.listView.invalidate();
        this.adapter.notifyDataSetChanged();
        invalidate();
        if (this.isEditing == false) goto L5;
        return;
    L5:
        if (this.orderChanged == false) goto L19;
        MessagesStorage.getInstance(UserConfig.selectedAccount).saveDialogFiltersOrder();
        TLRPC.TL_messages_updateDialogFiltersOrder r82 = new TLRPC.TL_messages_updateDialogFiltersOrder();
        ArrayList<MessagesController.DialogFilter> r0 = MessagesController.getInstance(UserConfig.selectedAccount).getDialogFilters();
        int r1 = r0.size();
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L13;
        MessagesController.DialogFilter r4 = r0.get(r3);
        boolean r5 = r4.isDefault();
        ArrayList<Integer> r6 = r82.order;
        if (r5 == false) goto L11;
        r6.add(0);
    L12:
        r3 = r3 + 1;
        goto L7
    L11:
        r6.add(Integer.valueOf(r4.id));
        goto L12
    L13:
        MessagesController.getInstance(UserConfig.selectedAccount).lockFiltersInternal();
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r82, new FilterTabsView$$ExternalSyntheticLambda2());
        this.orderChanged = false;
        return;
    }

    public void setStaticAllChats(boolean r1) {
        this.isStaticAllChats = r1;
    }

    public void shakeLock(int r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L13;
        if ((this.listView.getChildAt(r1) instanceof TabView) == false) goto L12;
        TabView r2 = (TabView) this.listView.getChildAt(r1);
        if (TabView.m10644$$Nest$fgetcurrentTab(r2).id != r5) goto L12;
        r2.shakeLockIcon(1.0f, 0);
        r2.performHapticFeedback(3);     // Catch: Exception -> L14
        return;
    L21:
        return;
    L12:
        r1 = r1 + 1;
        goto L4
    }

    public void stopAnimatingIndicator() {
        this.animatingIndicator = false;
    }

    public CharSequence text(String r3, ArrayList<TLRPC.MessageEntity> r4) {
        return MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(new SpannableStringBuilder(r3), this.textPaint.getFontMetricsInt(), false), r4, this.textPaint.getFontMetricsInt());
    }

    public void updateColors() {
        BlurredBackgroundDrawable r0 = this.blurredBackgroundDrawable;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
        invalidate();
    }
}
