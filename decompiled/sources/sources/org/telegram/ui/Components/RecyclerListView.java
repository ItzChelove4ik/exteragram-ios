package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Pair;
import android.util.SparseIntArray;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.DividerStyle;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.CollapseTextCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.EdgeEffectTrackerFactory;
import org.telegram.ui.Components.GestureDetectorFixDoubleTap;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.FiltersSetupActivity;

/* loaded from: classes3.dex */
public class RecyclerListView extends RecyclerView implements IBlur3Capture {
    public static final int TAG_ROUND_SECTION = 0;
    private static int[] attributes;
    private static boolean gotAttributes;
    private static final Method initializeScrollbars = null;
    private static final float[] radii = null;
    private static final Paint sectionBackgroundPaint = null;
    private static final Path sectionBackgroundPath = null;
    private static final Paint sectionBackgroundStrokePaint = null;
    private View.AccessibilityDelegate accessibilityDelegate;
    private boolean accessibilityEnabled;
    private int activeTouches;
    private boolean adaptiveOverScroll;
    private boolean allowItemsInteractionDuringAnimation;
    private boolean allowStopHeaveOperations;
    private boolean animateEmptyView;
    public boolean applyPaddingToSections;
    private Paint backgroundPaint;
    private boolean canCaptureSectionsDecorator;
    private Runnable clickRunnable;
    private final Path clipPath;
    private int currentChildPosition;
    private View currentChildView;
    private int currentFirst;
    int currentSelectedPosition;
    private int currentVisible;
    private boolean disableHighlightState;
    private boolean disallowInterceptTouchEvents;
    private View draggingChild;
    private Utilities.Callback5<Canvas, RectF, Float, Float, Float> drawSectionBackground;
    private boolean drawSelection;
    private boolean drawSelectorBehind;
    private final EdgeEffectTrackerFactory edgeEffectTrackerFactory;
    private View emptyView;
    int emptyViewAnimateToVisibility;
    private int emptyViewAnimationType;
    private FastScroll fastScroll;
    public boolean fastScrollAnimationRunning;
    public ArrayList<Long> forcedSections;
    private GestureDetectorFixDoubleTap gestureDetector;
    private GenericProvider<Integer, Integer> getSelectorColor;
    private ArrayList<View> headers;
    private ArrayList<View> headersCache;
    private boolean hiddenByEmptyView;
    private boolean hideIfEmpty;
    private int highlightPosition;
    private boolean ignoreClipChild;
    private boolean ignoreLayout;
    private boolean instantClick;
    private boolean interceptedByChild;
    private boolean isChildViewEnabled;
    private boolean isHidden;
    private Utilities.CallbackReturn<Integer, Boolean> isViewTypeSection;
    RecyclerItemsEnterAnimator itemsEnterAnimator;
    private long lastAlphaAnimationTime;
    float lastX;
    float lastY;
    int[] listPaddings;
    private boolean longPressCalled;
    boolean multiSelectionGesture;
    boolean multiSelectionGestureStarted;
    onMultiSelectionChanged multiSelectionListener;
    boolean multiselectScrollRunning;
    boolean multiselectScrollToTop;
    private final RecyclerView.AdapterDataObserver observer;
    private OnInterceptTouchListener onInterceptTouchListener;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListenerExtended onItemClickListenerExtended;
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemLongClickListenerExtended onItemLongClickListenerExtended;
    private RecyclerView.OnScrollListener onScrollListener;
    private FrameLayout overlayContainer;
    private IntReturnCallback pendingHighlightPosition;
    private View pinnedHeader;
    private float pinnedHeaderShadowAlpha;
    private Drawable pinnedHeaderShadowDrawable;
    private float pinnedHeaderShadowTargetAlpha;
    private Runnable removeHighlighSelectionRunnable;
    private ArrayList<SectionsDrawer.Section> removedSections;
    private boolean resetSelectorOnChanged;
    protected final Theme.ResourcesProvider resourcesProvider;
    private boolean scrollEnabled;
    public boolean scrolledByUserOnce;
    Runnable scroller;
    public boolean scrollingByUser;
    private int sectionOffset;
    private float sectionRadius;
    private float[] sectionRadiusBottom;
    private float[] sectionRadiusTop;
    private ArrayList<SectionsDrawer.Section> sections;
    private SectionsAdapter sectionsAdapter;
    private int sectionsCount;
    private ListSectionsDecoration sectionsItemDecoration;
    private int sectionsType;
    private boolean segmentedSectionsEnabled;
    private Runnable selectChildRunnable;
    HashSet<Integer> selectedPositions;
    protected Drawable selectorDrawable;
    private boolean selectorIsSection;
    protected int selectorPosition;
    private int selectorRadius;
    protected Rect selectorRect;
    private boolean selectorSectionHasNext;
    private boolean selectorSectionHasPrev;
    protected Consumer<Canvas> selectorTransformer;
    private int selectorType;
    protected View selectorView;
    private boolean selfOnLayout;
    private Matrix selfTransformationsMatrix;
    private boolean skipDrawSection;
    private int startSection;
    int startSelectionFrom;
    private boolean stoppedAllHeavyOperations;
    private int topBottomSelectorRadius;
    private int touchSlop;
    private int translateSelector;
    public boolean useLayoutPositionOnClick;
    boolean useRelativePositions;

    /* renamed from: org.telegram.ui.Components.RecyclerListView$1, reason: invalid class name */
    public class AnonymousClass1 extends View.AccessibilityDelegate {
        final /* synthetic */ RecyclerListView this$0;

        public AnonymousClass1(RecyclerListView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            if (r1.isEnabled() == false) goto L6;
            r2.addAction(16);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerListView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.AdapterDataObserver {
        final /* synthetic */ RecyclerListView this$0;

        public AnonymousClass2(RecyclerListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            RecyclerListView.m12473$$Nest$mcheckIfEmpty(this.this$0, true);
            if (RecyclerListView.m12461$$Nest$fgetresetSelectorOnChanged(this.this$0) == false) goto L7;
            RecyclerListView.m12468$$Nest$fputcurrentFirst(this.this$0, -1);
            if (RecyclerListView.m12460$$Nest$fgetremoveHighlighSelectionRunnable(this.this$0) != null) goto L7;
            this.this$0.selectorRect.setEmpty();
        L7:
            this.this$0.invalidate();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r1, int r2) {
            RecyclerListView.m12478$$Nest$minvalidateSegmentedSectionOffsets(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int r1, int r2) {
            RecyclerListView.m12473$$Nest$mcheckIfEmpty(this.this$0, true);
            if (RecyclerListView.m12459$$Nest$fgetpinnedHeader(this.this$0) != null) goto L5;
        L7:
            RecyclerListView.m12478$$Nest$minvalidateSegmentedSectionOffsets(this.this$0);
            return;
        L5:
            if (RecyclerListView.m12459$$Nest$fgetpinnedHeader(this.this$0).getAlpha() != 0.0f) goto L7;
            RecyclerListView.m12468$$Nest$fputcurrentFirst(this.this$0, -1);
            this.this$0.invalidateViews();
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int r1, int r2, int r3) {
            RecyclerListView.m12478$$Nest$minvalidateSegmentedSectionOffsets(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int r1, int r2) {
            RecyclerListView.m12473$$Nest$mcheckIfEmpty(this.this$0, true);
            RecyclerListView.m12478$$Nest$minvalidateSegmentedSectionOffsets(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerListView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ RecyclerListView this$0;

        public AnonymousClass3(RecyclerListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r12, int r13) {
            RecyclerListView.m12474$$Nest$mcheckStopHeavyOperations(this.this$0, r13);
            boolean r1 = false;
            if (r13 == 0) goto L16;
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) == null) goto L16;
            if (RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$0) == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$0));
            RecyclerListView.m12472$$Nest$fputselectChildRunnable(this.this$0, null);
        L9:
            MotionEvent r3 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
            RecyclerListView.m12447$$Nest$fgetgestureDetector(this.this$0).onTouchEvent(r3);     // Catch: Exception -> L12
        L14:
            RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).onTouchEvent(r3);
            r3.recycle();
            View r0 = RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0);
            RecyclerListView r32 = this.this$0;
            r32.onChildPressed(RecyclerListView.m12444$$Nest$fgetcurrentChildView(r32), 0.0f, 0.0f, false);
            RecyclerListView.m12467$$Nest$fputcurrentChildView(this.this$0, null);
            RecyclerListView.m12480$$Nest$mremoveSelection(this.this$0, r0, null);
            RecyclerListView.m12470$$Nest$fputinterceptedByChild(this.this$0, false);
        L12:
            e = move-exception;
            FileLog.e(e);
        L16:
            if (RecyclerListView.m12456$$Nest$fgetonScrollListener(this.this$0) == null) goto L18;
            RecyclerListView.m12456$$Nest$fgetonScrollListener(this.this$0).onScrollStateChanged(r12, r13);
        L18:
            RecyclerListView r11 = this.this$0;
            if (r13 != 1) goto L21;
        L22:
            r1 = true;
        L23:
            r11.scrollingByUser = r1;
            if (r1 == false) goto L29;
            r11.scrolledByUserOnce = true;
            return;
        L29:
            return;
        L21:
            if (r13 != 2) goto L23;
            goto L22
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            if (RecyclerListView.m12456$$Nest$fgetonScrollListener(this.this$0) == null) goto L5;
            RecyclerListView.m12456$$Nest$fgetonScrollListener(this.this$0).onScrolled(r3, r4, r5);
        L5:
            RecyclerListView r32 = this.this$0;
            if (r32.selectorPosition == (-1)) goto L11;
            r32.selectorRect.offset(-r4, -r5);
            RecyclerListView r33 = this.this$0;
            Drawable r42 = r33.selectorDrawable;
            if (r42 == null) goto L10;
            r42.setBounds(r33.selectorRect);
        L10:
            this.this$0.invalidate();
        L12:
            this.this$0.checkSection(false);
            if (r5 == 0) goto L18;
            if (RecyclerListView.m12446$$Nest$fgetfastScroll(this.this$0) == null) goto L18;
            RecyclerListView.m12446$$Nest$fgetfastScroll(this.this$0).showFloatingDate();
        L18:
            if (RecyclerListView.m12458$$Nest$fgetpendingHighlightPosition(this.this$0) == null) goto L21;
            RecyclerListView r2 = this.this$0;
            RecyclerListView.m12477$$Nest$mhighlightRowInternal(r2, RecyclerListView.m12458$$Nest$fgetpendingHighlightPosition(r2), 700, false);
            return;
        L21:
            return;
        L11:
            r32.selectorRect.setEmpty();
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerListView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ RecyclerListView this$0;

        public AnonymousClass4(RecyclerListView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (RecyclerListView.m12445$$Nest$fgetemptyView(this.this$0) == null) goto L6;
            RecyclerListView.m12445$$Nest$fgetemptyView(this.this$0).setVisibility(8);
        L6:
            if (this.this$0.hasSections() == false) goto L9;
            this.this$0.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerListView$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ RecyclerListView this$0;

        public AnonymousClass5(RecyclerListView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            super.requestLayout();
            int r0 = this.this$0.getMeasuredWidth();     // Catch: Exception -> L5
            int r1 = this.this$0.getMeasuredHeight();     // Catch: Exception -> L5
            measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));     // Catch: Exception -> L5
            layout(0, 0, RecyclerListView.m12457$$Nest$fgetoverlayContainer(this.this$0).getMeasuredWidth(), RecyclerListView.m12457$$Nest$fgetoverlayContainer(this.this$0).getMeasuredHeight());     // Catch: Exception -> L5
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerListView$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ RecyclerListView this$0;

        public AnonymousClass6(RecyclerListView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerListView r0 = this.this$0;
            r0.multiSelectionListener.getPaddings(r0.listPaddings);
            if (this.this$0.multiselectScrollToTop == false) goto L5;
            int r02 = -AndroidUtilities.dp(12.0f);
            RecyclerListView.m12475$$Nest$mchekMultiselect(this.this$0, 0.0f, r2.listPaddings[0]);
        L6:
            this.this$0.multiSelectionListener.scrollBy(r02);
            RecyclerListView r6 = this.this$0;
            if (r6.multiselectScrollRunning == false) goto L10;
            AndroidUtilities.runOnUIThread(r6.scroller);
            return;
        L10:
            return;
        L5:
            r02 = AndroidUtilities.dp(12.0f);
            RecyclerListView.m12475$$Nest$mchekMultiselect(this.this$0, 0.0f, r2.getMeasuredHeight() - this.this$0.listPaddings[1]);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerListView$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends Drawable {
        private final Paint paint;
        final /* synthetic */ RecyclerListView this$0;
        final /* synthetic */ View val$child;
        final /* synthetic */ Path val$clipPath;
        final /* synthetic */ RectF val$rect;

        public AnonymousClass7(RecyclerListView r1, View r2, Path r3, RectF r4) {
            this.this$0 = r1;
            this.val$child = r2;
            this.val$clipPath = r3;
            this.val$rect = r4;
            this.paint = new Paint(1);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            r4.save();
            r4.translate(-this.val$child.getX(), -this.val$child.getY());
            r4.clipPath(this.val$clipPath);
            this.paint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite, this.this$0.resourcesProvider), this.paint.getAlpha()));
            r4.drawRect(this.val$rect, this.paint);
            r4.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public class FastScroll extends View {
        private int activeColor;
        private Path arrowPath;
        BlurredBackgroundDrawable blurredCircleDrawable;
        BlurredBackgroundDrawable blurredTagDrawable;
        private float bubbleProgress;
        private String currentLetter;
        Drawable fastScrollBackgroundDrawable;
        Drawable fastScrollShadowDrawable;
        private float floatingDateProgress;
        private boolean floatingDateVisible;
        private boolean fromTop;
        private float fromWidth;
        Runnable hideFloatingDateRunnable;
        private StaticLayout inLetterLayout;
        private int inactiveColor;
        boolean isMoving;
        boolean isRtl;
        public boolean isVisible;
        private float lastLetterY;
        private long lastUpdateTime;
        private float lastY;
        private StaticLayout letterLayout;
        private TextPaint letterPaint;
        private StaticLayout oldLetterLayout;
        private StaticLayout outLetterLayout;
        private Paint paint;
        private Paint paint2;
        private Path path;
        private int[] positionWithOffset;
        private boolean pressed;
        private float progress;
        private float[] radii;
        private RectF rect;
        private float replaceLayoutProgress;
        private int scrollX;
        private StaticLayout stableLetterLayout;
        private float startDy;
        long startTime;
        float startY;
        private float textX;
        private float textY;
        final /* synthetic */ RecyclerListView this$0;
        public int topOffset;
        float touchSlop;
        private int type;
        public boolean usePadding;
        float viewAlpha;
        float visibilityAlpha;

        /* renamed from: org.telegram.ui.Components.RecyclerListView$FastScroll$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ FastScroll this$1;

            public AnonymousClass1(FastScroll r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean r0 = FastScroll.m12483$$Nest$fgetpressed(this.this$1);
                FastScroll r1 = this.this$1;
                if (r0 == false) goto L6;
                AndroidUtilities.cancelRunOnUIThread(r1.hideFloatingDateRunnable);
                AndroidUtilities.runOnUIThread(this.this$1.hideFloatingDateRunnable, 4000);
                return;
            L6:
                FastScroll.m12484$$Nest$fputfloatingDateVisible(r1, false);
                this.this$1.invalidate();
            }
        }

        /* renamed from: -$$Nest$fgetpressed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12483$$Nest$fgetpressed(FastScroll r0) {
            return r0.pressed;
        }

        /* renamed from: -$$Nest$fputfloatingDateVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12484$$Nest$fputfloatingDateVisible(FastScroll r0, boolean r1) {
            r0.floatingDateVisible = r1;
        }

        /* renamed from: -$$Nest$mgetCurrentLetter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12485$$Nest$mgetCurrentLetter(FastScroll r0, boolean r1) {
            r0.getCurrentLetter(r1);
        }

        /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12486$$Nest$mupdateColors(FastScroll r0) {
            r0.updateColors();
        }

        public FastScroll(RecyclerListView r9, Context r10, int r11) {
            this.this$0 = r9;
            super(r10);
            this.usePadding = true;
            this.rect = new RectF();
            this.paint = new Paint(1);
            this.paint2 = new Paint(1);
            this.replaceLayoutProgress = 1.0f;
            this.letterPaint = new TextPaint(1);
            this.path = new Path();
            this.arrowPath = new Path();
            this.radii = new float[8];
            this.positionWithOffset = new int[2];
            this.hideFloatingDateRunnable = new AnonymousClass1(this);
            this.viewAlpha = 1.0f;
            this.type = r11;
            int r1 = 0;
            if (r11 != 0) goto L5;
            this.letterPaint.setTextSize(AndroidUtilities.dp(45.0f));
            this.isRtl = LocaleController.isRTL;
        L6:
            if (r1 >= 8) goto L9;
            this.radii[r1] = AndroidUtilities.dp(44.0f);
            r1 = r1 + 1;
            goto L6
        L9:
            if (this.isRtl == false) goto L12;
            float r112 = 10.0f;
        L16:
            this.scrollX = AndroidUtilities.dp(r112);
            if (r9.hasSections() == false) goto L23;
            int r92 = this.scrollX;
            if (this.isRtl == false) goto L21;
            float r113 = -4.0f;
        L22:
            this.scrollX = r92 + AndroidUtilities.dp(r113);
            goto L23
        L21:
            r113 = 6.0f;
        L23:
            updateColors();
            setFocusableInTouchMode(true);
            this.touchSlop = ViewConfiguration.get(r10).getScaledTouchSlop();
            this.fastScrollShadowDrawable = ContextCompat.getDrawable(r10, R.drawable.fast_scroll_shadow);
            return;
        L12:
            if (r11 != 0) goto L14;
            int r114 = 132;
        L15:
            r112 = r114 - 15;
            goto L16
        L14:
            r114 = 240;
            goto L15
        L5:
            this.isRtl = false;
            this.letterPaint.setTextSize(AndroidUtilities.dp(13.0f));
            this.letterPaint.setTypeface(AndroidUtilities.bold());
            Paint r3 = this.paint2;
            int r4 = Theme.key_windowBackgroundWhite;
            r3.setColor(Theme.getColor(r4, r9.resourcesProvider));
            Drawable r32 = ContextCompat.getDrawable(r10, R.drawable.calendar_date).mutate();
            this.fastScrollBackgroundDrawable = r32;
            r32.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(Theme.getColor(r4, r9.resourcesProvider), -1, 0.1f), PorterDuff.Mode.MULTIPLY));
            goto L6
        }

        private void getCurrentLetter(boolean r27) {
            RecyclerView.LayoutManager r1 = this.this$0.getLayoutManager();
            if ((r1 instanceof LinearLayoutManager) == false) goto L50;
            LinearLayoutManager r12 = (LinearLayoutManager) r1;
            boolean r3 = true;
            if (r12.getOrientation() != 1) goto L51;
            RecyclerView.Adapter r2 = this.this$0.getAdapter();
            if ((r2 instanceof FastScrollAdapter) == false) goto L52;
            FastScrollAdapter r22 = (FastScrollAdapter) r2;
            r22.getPositionForScrollProgress(this.this$0, this.progress, this.positionWithOffset);
            if (r27 == false) goto L11;
            int[] r5 = this.positionWithOffset;
            r12.scrollToPositionWithOffset(r5[0], (-r5[1]) + RecyclerListView.m12462$$Nest$fgetsectionOffset(this.this$0));
        L11:
            String r6 = r22.getLetter(this.positionWithOffset[0]);
            if (r6 != null) goto L19;
            StaticLayout r23 = this.letterLayout;
            if (r23 == null) goto L16;
            this.oldLetterLayout = r23;
        L16:
            this.letterLayout = null;
            return;
        L19:
            if (r6.equals(this.currentLetter) == true) goto L53;
            this.currentLetter = r6;
            if (this.type != 0) goto L23;
            this.letterLayout = new StaticLayout(r6, this.letterPaint, 1000, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        L42:
            this.oldLetterLayout = null;
            if (this.letterLayout.getLineCount() <= 0) goto L54;
            this.letterLayout.getLineWidth(0);
            this.letterLayout.getLineLeft(0);
            if (this.isRtl == false) goto L47;
            this.textX = (AndroidUtilities.dp(10.0f) + ((AndroidUtilities.dp(88.0f) - this.letterLayout.getLineWidth(0)) / 2.0f)) - this.letterLayout.getLineLeft(0);
        L48:
            this.textY = (AndroidUtilities.dp(88.0f) - this.letterLayout.getHeight()) / 2;
            return;
        L47:
            this.textX = ((AndroidUtilities.dp(88.0f) - this.letterLayout.getLineWidth(0)) / 2.0f) - this.letterLayout.getLineLeft(0);
            goto L48
        L54:
            return;
        L23:
            this.outLetterLayout = this.letterLayout;
            int r17 = ((int) this.letterPaint.measureText(r6)) + 1;
            TextPaint r7 = this.letterPaint;
            Layout.Alignment r18 = Layout.Alignment.ALIGN_NORMAL;
            this.letterLayout = new StaticLayout(r6, r7, r17, r18, 1.0f, 0.0f, false);
            if (this.outLetterLayout == null) goto L41;
            String[] r52 = r6.split(" ");
            String[] r24 = this.outLetterLayout.getText().toString().split(" ");
            if (r52 == null) goto L35;
            if (r24 == null) goto L35;
            if (r52.length != 2) goto L35;
            if (r24.length != 2) goto L35;
            if (r52[1].equals(r24[1]) == false) goto L35;
            String r72 = this.outLetterLayout.getText().toString();
            SpannableStringBuilder r8 = new SpannableStringBuilder(r72);
            r8.setSpan(new EmptyStubSpan(), r24[0].length(), r72.length(), 0);
            this.outLetterLayout = new StaticLayout(r8, this.letterPaint, ((int) this.letterPaint.measureText(r72)) + 1, r18, 1.0f, 0.0f, false);
            SpannableStringBuilder r15 = new SpannableStringBuilder(r6);
            r15.setSpan(new EmptyStubSpan(), r52[0].length(), r6.length(), 0);
            this.inLetterLayout = new StaticLayout(r15, this.letterPaint, r17, r18, 1.0f, 0.0f, false);
            SpannableStringBuilder r152 = new SpannableStringBuilder(r6);
            r152.setSpan(new EmptyStubSpan(), 0, r52[0].length(), 0);
            this.stableLetterLayout = new StaticLayout(r152, this.letterPaint, r17, r18, 1.0f, 0.0f, false);
        L36:
            this.fromWidth = this.outLetterLayout.getWidth();
            this.replaceLayoutProgress = 0.0f;
            if (getProgress() > this.lastLetterY) goto L40;
            r3 = false;
        L40:
            this.fromTop = r3;
        L35:
            this.inLetterLayout = this.letterLayout;
            this.stableLetterLayout = null;
        L41:
            this.lastLetterY = getProgress();
            goto L42
        L53:
            return;
        L52:
            return;
        L51:
            return;
        }

        private void updateColors() {
            if (this.type != 0) goto L5;
            int r0 = Theme.getColor(Theme.key_fastScrollInactive, this.this$0.resourcesProvider);
        L6:
            this.inactiveColor = r0;
            this.activeColor = Theme.getColor(Theme.key_fastScrollActive, this.this$0.resourcesProvider);
            this.paint.setColor(this.inactiveColor);
            int r02 = this.type;
            TextPaint r1 = this.letterPaint;
            if (r02 != 0) goto L9;
            r1.setColor(Theme.getColor(Theme.key_fastScrollText, this.this$0.resourcesProvider));
        L10:
            invalidate();
            return;
        L9:
            r1.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.this$0.resourcesProvider));
            goto L10
        L5:
            r0 = ColorUtils.setAlphaComponent(-16777216, 102);
            goto L6
        }

        public void applyBlurDrawables(BlurredBackgroundDrawableViewFactory r4, BlurredBackgroundProvider r5) {
            BlurredBackgroundDrawable r0 = r4.create(RecyclerListView.m12446$$Nest$fgetfastScroll(this.this$0), r5);
            this.blurredCircleDrawable = r0;
            r0.setPadding(AndroidUtilities.dp(4.0f));
            this.blurredCircleDrawable.setRadius(AndroidUtilities.dp(24.0f));
            BlurredBackgroundDrawable r42 = r4.create(RecyclerListView.m12446$$Nest$fgetfastScroll(this.this$0), r5);
            this.blurredTagDrawable = r42;
            r42.setPadding(AndroidUtilities.dp(6.0f));
            this.blurredTagDrawable.setThickness(AndroidUtilities.dp(4.0f));
            this.blurredTagDrawable.setRadius(AndroidUtilities.dp(14.0f));
        }

        public boolean fillDrawablesRect(RectF r2) {
            if (this.blurredCircleDrawable == null) goto L5;
        L9:
            r2.set(this.blurredTagDrawable.getBounds());
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(this.blurredCircleDrawable.getBounds());
            r2.union(r0);
            return true;
        L5:
            if (this.blurredTagDrawable != null) goto L9;
            return false;
        }

        @Override // android.view.View
        public float getAlpha() {
            return this.viewAlpha;
        }

        public float getProgress() {
            return this.progress;
        }

        public int getScrollBarY() {
            return ((int) Math.ceil((getMeasuredHeight() - AndroidUtilities.dp(54.0f)) * this.progress)) + AndroidUtilities.dp(17.0f);
        }

        @Override // android.view.View
        public boolean isPressed() {
            return this.pressed;
        }

        @Override // android.view.View
        public void layout(int r2, int r3, int r4, int r5) {
            if (RecyclerListView.m12464$$Nest$fgetselfOnLayout(this.this$0) == true) goto L5;
            return;
        L5:
            super.layout(r2, r3, r4, r5);
        }

        @Override // android.view.View
        public void onDraw(Canvas r22) {
            if (this.usePadding == false) goto L5;
            int r2 = getPaddingTop();
        L6:
            int r23 = r2 + ((int) Math.ceil(((getMeasuredHeight() - r2) - AndroidUtilities.dp(54.0f)) * this.progress));
            this.rect.set(this.scrollX, AndroidUtilities.dp(12.0f) + r23, this.scrollX + AndroidUtilities.dp(5.0f), AndroidUtilities.dp(42.0f) + r23);
            int r4 = this.type;
            Paint r5 = this.paint;
            if (r4 != 0) goto L9;
            char r16 = 0;
            r5.setColor(ColorUtils.blendARGB(this.inactiveColor, this.activeColor, this.bubbleProgress));
            r22.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint);
            float r18 = 12.0f;
            float r19 = 36.0f;
        L14:
            int r3 = this.type;
            if (r3 == 0) goto L17;
            if (r3 == 1) goto L60;
        L95:
            long r1 = System.currentTimeMillis();
            long r32 = r1 - this.lastUpdateTime;
            if (r32 >= 0) goto L98;
        L99:
            r32 = 17;
        L100:
            boolean r52 = this.isMoving;
            if (r52 == true) goto L103;
        L106:
            if (r52 == false) goto L110;
            if (this.letterLayout == null) goto L110;
        L121:
            boolean r12 = this.floatingDateVisible;
            if (r12 == false) goto L130;
            float r24 = this.floatingDateProgress;
            if (r24 == 1.0f) goto L130;
            float r25 = r24 + (r32 / 120.0f);
            this.floatingDateProgress = r25;
            if (r25 <= 1.0f) goto L128;
            this.floatingDateProgress = 1.0f;
        L128:
            invalidate();
            return;
        L130:
            if (r12 == true) goto L138;
            float r13 = this.floatingDateProgress;
            if (r13 == 0.0f) goto L139;
            float r14 = r13 - (r32 / 120.0f);
            this.floatingDateProgress = r14;
            if (r14 >= 0.0f) goto L136;
            this.floatingDateProgress = 0.0f;
        L136:
            invalidate();
            return;
        L139:
            return;
        L138:
            return;
        L110:
            if (this.bubbleProgress <= 0.0f) goto L121;
        L111:
            this.lastUpdateTime = r1;
            invalidate();
            if (this.isMoving == true) goto L114;
        L118:
            float r15 = this.bubbleProgress - (r32 / 120.0f);
            this.bubbleProgress = r15;
            if (r15 >= 0.0f) goto L121;
            this.bubbleProgress = 0.0f;
            goto L121
        L114:
            if (this.letterLayout == null) goto L118;
            float r17 = this.bubbleProgress + (r32 / 120.0f);
            this.bubbleProgress = r17;
            if (r17 <= 1.0f) goto L121;
            this.bubbleProgress = 1.0f;
            goto L121
        L103:
            if (this.letterLayout == null) goto L106;
            if (this.bubbleProgress < 1.0f) goto L111;
        L98:
            if (r32 <= 17) goto L100;
        L60:
            if (this.letterLayout == null) goto L95;
            if (this.floatingDateProgress == 0.0f) goto L95;
            r22.save();
            float r33 = (this.floatingDateProgress * 0.3f) + 0.7f;
            r22.scale(r33, r33, this.rect.right - AndroidUtilities.dp(r18), this.rect.centerY());
            float r26 = this.rect.centerY();
            float r34 = (this.rect.left - (AndroidUtilities.dp(30.0f) * this.bubbleProgress)) - AndroidUtilities.dp(8.0f);
            this.letterLayout.getHeight();
            AndroidUtilities.dp(6.0f);
            this.rect.set((r34 - ((this.replaceLayoutProgress * this.letterLayout.getWidth()) + (this.fromWidth * (1.0f - this.replaceLayoutProgress)))) - AndroidUtilities.dp(r19), (r26 - (this.letterLayout.getHeight() / 2.0f)) - AndroidUtilities.dp(8.0f), r34 - AndroidUtilities.dp(r18), ((this.letterLayout.getHeight() / 2.0f) + r26) + AndroidUtilities.dp(8.0f));
            int r42 = this.paint2.getAlpha();
            int r53 = this.letterPaint.getAlpha();
            this.paint2.setAlpha((int) (r42 * this.floatingDateProgress));
            if (this.blurredTagDrawable == null) goto L66;
            RectF r6 = this.rect;
            Rect r9 = AndroidUtilities.rectTmp2;
            r6.round(r9);
            r9.inset(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
            this.blurredTagDrawable.setBounds(r9);
            this.blurredTagDrawable.draw(r22);
        L67:
            float r62 = this.replaceLayoutProgress;
            if (r62 == 1.0f) goto L74;
            float r63 = r62 + 0.10666667f;
            this.replaceLayoutProgress = r63;
            if (r63 <= 1.0f) goto L72;
            this.replaceLayoutProgress = 1.0f;
            goto L74
        L72:
            invalidate();
        L74:
            if (this.replaceLayoutProgress == 1.0f) goto L93;
            r22.save();
            this.rect.inset(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f));
            r22.clipRect(this.rect);
            if (this.outLetterLayout == null) goto L83;
            this.letterPaint.setAlpha((int) ((r53 * this.floatingDateProgress) * (1.0f - this.replaceLayoutProgress)));
            r22.save();
            float r64 = (r34 - this.outLetterLayout.getWidth()) - AndroidUtilities.dp(24.0f);
            float r92 = r26 - (this.outLetterLayout.getHeight() / 2.0f);
            if (this.fromTop == false) goto L80;
            int r10 = -1;
        L81:
            r22.translate(r64, r92 + ((r10 * AndroidUtilities.dp(15.0f)) * this.replaceLayoutProgress));
            this.outLetterLayout.draw(r22);
            r22.restore();
            goto L83
        L80:
            r10 = 1;
        L83:
            if (this.inLetterLayout == null) goto L90;
            this.letterPaint.setAlpha((int) ((r53 * this.floatingDateProgress) * this.replaceLayoutProgress));
            r22.save();
            float r65 = (r34 - this.inLetterLayout.getWidth()) - AndroidUtilities.dp(24.0f);
            float r93 = r26 - (this.inLetterLayout.getHeight() / 2.0f);
            if (this.fromTop == false) goto L87;
            int r172 = 1;
        L88:
            r22.translate(r65, r93 + ((AndroidUtilities.dp(15.0f) * r172) * (1.0f - this.replaceLayoutProgress)));
            this.inLetterLayout.draw(r22);
            r22.restore();
            goto L90
        L87:
            r172 = -1;
        L90:
            if (this.stableLetterLayout == null) goto L92;
            this.letterPaint.setAlpha((int) (r53 * this.floatingDateProgress));
            r22.save();
            r22.translate((r34 - this.stableLetterLayout.getWidth()) - AndroidUtilities.dp(24.0f), r26 - (this.stableLetterLayout.getHeight() / 2.0f));
            this.stableLetterLayout.draw(r22);
            r22.restore();
        L92:
            r22.restore();
        L94:
            this.paint2.setAlpha(r42);
            this.letterPaint.setAlpha(r53);
            r22.restore();
            goto L95
        L93:
            this.letterPaint.setAlpha((int) (r53 * this.floatingDateProgress));
            r22.save();
            r22.translate((r34 - this.letterLayout.getWidth()) - AndroidUtilities.dp(24.0f), (r26 - (this.letterLayout.getHeight() / 2.0f)) + (AndroidUtilities.dp(15.0f) * (1.0f - this.replaceLayoutProgress)));
            this.letterLayout.draw(r22);
            r22.restore();
            goto L94
        L66:
            Drawable r66 = this.fastScrollBackgroundDrawable;
            RectF r94 = this.rect;
            r66.setBounds((int) r94.left, (int) r94.top, (int) r94.right, (int) r94.bottom);
            this.fastScrollBackgroundDrawable.setAlpha((int) (this.floatingDateProgress * 255.0f));
            this.fastScrollBackgroundDrawable.draw(r22);
            goto L67
        L17:
            if (this.isMoving == false) goto L19;
        L20:
            this.paint.setAlpha((int) (this.bubbleProgress * 255.0f));
            int r35 = AndroidUtilities.dp(30.0f) + r23;
            int r27 = r23 - AndroidUtilities.dp(46.0f);
            if (r27 > AndroidUtilities.dp(r18)) goto L23;
            float r43 = AndroidUtilities.dp(r18) - r27;
            r27 = AndroidUtilities.dp(r18);
        L24:
            r22.translate(AndroidUtilities.dp(10.0f), r27);
            if (r43 > AndroidUtilities.dp(29.0f)) goto L27;
            float r95 = AndroidUtilities.dp(44.0f);
            float r122 = AndroidUtilities.dp(4.0f) + ((r43 / AndroidUtilities.dp(29.0f)) * AndroidUtilities.dp(40.0f));
        L28:
            boolean r44 = this.isRtl;
            if (r44 == false) goto L34;
            float[] r142 = this.radii;
            if (r142[r16] == r95) goto L33;
        L39:
            float[] r143 = this.radii;
            if (r44 == false) goto L42;
            r143[1] = r95;
            r143[r16] = r95;
            r143[7] = r122;
            r143[6] = r122;
        L43:
            this.path.reset();
            RectF r45 = this.rect;
            if (this.isRtl == false) goto L46;
            float r54 = AndroidUtilities.dp(10.0f);
        L48:
            if (this.isRtl == false) goto L50;
            float r67 = 98.0f;
        L51:
            r45.set(r54, 0.0f, AndroidUtilities.dp(r67), AndroidUtilities.dp(88.0f));
            this.path.addRoundRect(this.rect, this.radii, Path.Direction.CW);
            this.path.close();
        L52:
            StaticLayout r46 = this.letterLayout;
            if (r46 != null) goto L56;
            r46 = this.oldLetterLayout;
        L56:
            if (r46 == null) goto L95;
            r22.save();
            float r55 = this.bubbleProgress;
            r22.scale(r55, r55, this.scrollX, r35 - r27);
            r22.drawPath(this.path, this.paint);
            r22.translate(this.textX, this.textY);
            r46.draw(r22);
            r22.restore();
            goto L95
        L50:
            r67 = 88.0f;
            goto L51
        L46:
            r54 = 0.0f;
            goto L48
        L42:
            r143[3] = r95;
            r143[2] = r95;
            r143[5] = r122;
            r143[4] = r122;
            goto L43
        L33:
            if (r142[6] != r122) goto L39;
        L34:
            if (r44 == true) goto L52;
            float[] r144 = this.radii;
            if (r144[2] != r95) goto L39;
            if (r144[4] == r122) goto L52;
        L27:
            float r96 = AndroidUtilities.dp(44.0f);
            float r123 = AndroidUtilities.dp(4.0f);
            float r47 = ((1.0f - ((r43 - AndroidUtilities.dp(29.0f)) / AndroidUtilities.dp(29.0f))) * AndroidUtilities.dp(40.0f)) + r123;
            r122 = r96;
            r95 = r47;
            goto L28
        L23:
            r43 = 0.0f;
            goto L24
        L19:
            if (this.bubbleProgress == 0.0f) goto L95;
        L9:
            r16 = 0;
            r5.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite, this.this$0.resourcesProvider), -1, 0.1f));
            float r48 = AndroidUtilities.dp(27.0f) + r23;
            BlurredBackgroundDrawable r56 = this.blurredCircleDrawable;
            if (r56 == null) goto L12;
            r18 = 12.0f;
            r56.setBounds(this.scrollX + AndroidUtilities.dp(-20.0f), AndroidUtilities.dp(-1.0f) + r23, this.scrollX + AndroidUtilities.dp(36.0f), r23 + AndroidUtilities.dp(55.0f));
            this.blurredCircleDrawable.draw(r22);
            r19 = 36.0f;
        L13:
            this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.this$0.resourcesProvider));
            r22.save();
            r22.translate(this.scrollX + AndroidUtilities.dp(4.0f), (AndroidUtilities.dp(34.0f) + r23) + (AndroidUtilities.dp(2.0f) * this.bubbleProgress));
            r22.drawPath(this.arrowPath, this.paint);
            r22.restore();
            r22.save();
            r22.translate(this.scrollX + AndroidUtilities.dp(4.0f), (AndroidUtilities.dp(24.0f) + r23) - (AndroidUtilities.dp(2.0f) * this.bubbleProgress));
            r22.rotate(180.0f, 0.0f, -AndroidUtilities.dp(2.0f));
            r22.drawPath(this.arrowPath, this.paint);
            r22.restore();
            goto L14
        L12:
            r18 = 12.0f;
            r19 = 36.0f;
            this.fastScrollShadowDrawable.setBounds(getMeasuredWidth() - this.fastScrollShadowDrawable.getIntrinsicWidth(), (int) (r48 - (this.fastScrollShadowDrawable.getIntrinsicHeight() / 2)), getMeasuredWidth(), (int) (r48 + (this.fastScrollShadowDrawable.getIntrinsicHeight() / 2)));
            this.fastScrollShadowDrawable.draw(r22);
            r22.drawCircle(this.scrollX + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(27.0f) + r23, AndroidUtilities.dp(24.0f), this.paint);
            goto L13
        L5:
            r2 = 0;
            goto L6
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            if (this.type != 0) goto L5;
            float r32 = 132.0f;
        L6:
            setMeasuredDimension(AndroidUtilities.dp(r32), View.MeasureSpec.getSize(r4));
            this.arrowPath.reset();
            this.arrowPath.setLastPoint(0.0f, 0.0f);
            this.arrowPath.lineTo(AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
            this.arrowPath.lineTo(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
            this.arrowPath.close();
            return;
        L5:
            r32 = 240.0f;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r8) {
            if (this.isVisible == true) goto L6;
            this.pressed = false;
            return false;
        L6:
            int r0 = r8.getAction();
            if (r0 == 0) goto L51;
            if (r0 != 1) goto L10;
        L37:
            RecyclerView.Adapter r82 = this.this$0.getAdapter();
            if (this.pressed == true) goto L40;
        L46:
            this.isMoving = false;
            this.pressed = false;
            this.lastUpdateTime = System.currentTimeMillis();
            invalidate();
            if ((r82 instanceof FastScrollAdapter) == false) goto L49;
            ((FastScrollAdapter) r82).onFinishFastScroll(this.this$0);
        L49:
            showFloatingDate();
            return true;
        L40:
            if (this.isMoving == true) goto L46;
            if ((System.currentTimeMillis() - this.startTime) >= 150) goto L46;
            if ((r82 instanceof FastScrollAdapter) == false) goto L46;
            ((FastScrollAdapter) r82).onFastScrollSingleTap();
            goto L46
        L10:
            if (r0 == 2) goto L16;
            if (r0 == 3) goto L37;
            return this.pressed;
        L16:
            if (this.pressed == true) goto L19;
            return true;
        L19:
            if (Math.abs(r8.getY() - this.startY) <= this.touchSlop) goto L22;
            this.isMoving = true;
        L22:
            if (this.isMoving == false) goto L36;
            float r83 = r8.getY();
            float r02 = AndroidUtilities.dp(12.0f) + this.startDy;
            float r1 = (getMeasuredHeight() - AndroidUtilities.dp(42.0f)) + this.startDy;
            if (r83 >= r02) goto L27;
            r83 = r02;
        L29:
            float r03 = r83 - this.lastY;
            this.lastY = r83;
            float r84 = this.progress + (r03 / (getMeasuredHeight() - AndroidUtilities.dp(54.0f)));
            this.progress = r84;
            if (r84 >= 0.0f) goto L33;
            this.progress = 0.0f;
        L35:
            getCurrentLetter(true);
            invalidate();
            goto L36
        L33:
            if (r84 <= 1.0f) goto L35;
            this.progress = 1.0f;
            goto L35
        L27:
            if (r83 <= r1) goto L29;
            r83 = r1;
        L36:
            return true;
        L51:
            float r04 = r8.getX();
            float r85 = r8.getY();
            this.lastY = r85;
            this.startY = r85;
            float r86 = ((float) Math.ceil((getMeasuredHeight() - AndroidUtilities.dp(54.0f)) * this.progress)) + AndroidUtilities.dp(12.0f);
            if (this.isRtl == false) goto L56;
            if (r04 <= AndroidUtilities.dp(25.0f)) goto L56;
        L85:
            return false;
        L56:
            if (this.isRtl == false) goto L58;
        L59:
            float r2 = this.lastY;
            if (r2 < r86) goto L85;
            if (r2 > (AndroidUtilities.dp(30.0f) + r86)) goto L85;
            if (this.type == 1) goto L67;
        L81:
            this.startDy = this.lastY - r86;
            this.startTime = System.currentTimeMillis();
            this.pressed = true;
            this.isMoving = false;
            this.lastUpdateTime = System.currentTimeMillis();
            invalidate();
            RecyclerView.Adapter r87 = this.this$0.getAdapter();
            showFloatingDate();
            if ((r87 instanceof FastScrollAdapter) == false) goto L84;
            ((FastScrollAdapter) r87).onStartFastScroll();
        L84:
            return true;
        L67:
            if (this.floatingDateVisible == true) goto L81;
            if (this.isRtl == false) goto L73;
            if (r04 <= AndroidUtilities.dp(25.0f)) goto L73;
        L80:
            return false;
        L73:
            if (this.isRtl == false) goto L75;
        L76:
            float r05 = this.lastY;
            if (r05 < r86) goto L80;
            if (r05 <= (AndroidUtilities.dp(30.0f) + r86)) goto L81;
        L75:
            if (r04 < (getMeasuredWidth() - AndroidUtilities.dp(25.0f))) goto L80;
        L58:
            if (r04 < AndroidUtilities.dp(107.0f)) goto L85;
            goto L59
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            if (this.viewAlpha == r2) goto L6;
            this.viewAlpha = r2;
            super.setAlpha(r2 * this.visibilityAlpha);
            return;
        }

        public void setIsVisible(boolean r2) {
            if (this.isVisible == r2) goto L10;
            this.isVisible = r2;
            if (r2 == false) goto L7;
            float r22 = 1.0f;
        L8:
            this.visibilityAlpha = r22;
            super.setAlpha(this.viewAlpha * r22);
            return;
        L7:
            r22 = 0.0f;
            goto L8
        }

        public void setProgress(float r1) {
            this.progress = r1;
            invalidate();
        }

        public void setVisibilityAlpha(float r2) {
            if (this.visibilityAlpha == r2) goto L6;
            this.visibilityAlpha = r2;
            super.setAlpha(this.viewAlpha * r2);
            return;
        }

        public void showFloatingDate() {
            if (this.type == 1) goto L6;
            return;
        L6:
            if (this.floatingDateVisible == true) goto L8;
            this.floatingDateVisible = true;
            invalidate();
        L8:
            AndroidUtilities.cancelRunOnUIThread(this.hideFloatingDateRunnable);
            AndroidUtilities.runOnUIThread(this.hideFloatingDateRunnable, 2000);
        }
    }

    public static abstract class FastScrollAdapter extends SelectionAdapter {
        public FastScrollAdapter() {
        }

        public boolean fastScrollIsVisible(RecyclerListView r1) {
            return true;
        }

        public abstract String getLetter(int r1);

        public abstract void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3);

        public float getScrollProgress(RecyclerListView r3) {
            return r3.computeVerticalScrollOffset() / ((getTotalItemsCount() * r3.getChildAt(0).getMeasuredHeight()) - r3.getMeasuredHeight());
        }

        public int getTotalItemsCount() {
            return getItemCount();
        }

        public void onFastScrollSingleTap() {
        }

        public void onFinishFastScroll(RecyclerListView r1) {
        }

        public void onStartFastScroll() {
        }
    }

    /* loaded from: classes7.dex */
    public static class FoucsableOnTouchListener implements View.OnTouchListener {
        private boolean onFocus;
        private float x;
        private float y;

        public FoucsableOnTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r6, MotionEvent r7) {
            ViewParent r0 = r6.getParent();
            if (r0 != null) goto L6;
            return false;
        L6:
            if (r7.getAction() != 0) goto L9;
            this.x = r7.getX();
            this.y = r7.getY();
            this.onFocus = true;
            r0.requestDisallowInterceptTouchEvent(true);
        L9:
            if (r7.getAction() != 2) goto L16;
            float r2 = this.x - r7.getX();
            float r3 = this.y - r7.getY();
            float r62 = ViewConfiguration.get(r6.getContext()).getScaledTouchSlop();
            if (this.onFocus == true) goto L13;
        L20:
            return false;
        L13:
            if (Math.sqrt((r2 * r2) + (r3 * r3)) <= r62) goto L20;
            this.onFocus = false;
            r0.requestDisallowInterceptTouchEvent(false);
            goto L20
        L16:
            if (r7.getAction() != 1) goto L18;
        L19:
            this.onFocus = false;
            r0.requestDisallowInterceptTouchEvent(false);
            goto L20
        L18:
            if (r7.getAction() != 3) goto L20;
            goto L19
        }
    }

    public interface HitTestable {
        boolean hasClickableNodeAt(float r1, float r2);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(View r1) {
            super(r1);
            if (ExteraConfig.getInAppVibration() == true) goto L6;
            VibratorUtils.disableHapticFeedback(r1);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public interface IntReturnCallback {
        int run();
    }

    /* loaded from: classes7.dex */
    public static class ListSectionsDecoration extends RecyclerView.ItemDecoration implements IBlur3Capture {
        private boolean enableTopPadding;
        public final Utilities.CallbackReturn<View, Boolean> isSectionItem;
        private int padding;
        public final RecyclerListView parent;

        /* renamed from: -$$Nest$fgetpadding, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12487$$Nest$fgetpadding(ListSectionsDecoration r0) {
            return r0.padding;
        }

        public ListSectionsDecoration(RecyclerListView r1, Utilities.CallbackReturn<View, Boolean> r2, int r3, boolean r4) {
            this.parent = r1;
            this.isSectionItem = r2;
            this.padding = r3;
            this.enableTopPadding = r4;
        }

        private boolean isSectionPosition(RecyclerView.Adapter r2, int r3) {
            if (r2 != null) goto L4;
            return false;
        L4:
            if (RecyclerListView.m12450$$Nest$fgetisViewTypeSection(this.parent) == null) goto L13;
            if (r3 >= 0) goto L7;
            return false;
        L7:
            if (r3 < r2.getItemCount()) goto L10;
            return false;
        L10:
            return ((Boolean) RecyclerListView.m12450$$Nest$fgetisViewTypeSection(this.parent).run(Integer.valueOf(r2.getItemViewType(r3)))).booleanValue();
        L13:
            return false;
        }

        @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
        public void capture(Canvas r1, RectF r2) {
            r1.save();
            r1.clipRect(r2);
            this.parent.drawSectionsBackgrounds(r1);
            r1.restore();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r5, View r6, RecyclerView r7, RecyclerView.State r8) {
            if (this.isSectionItem.run(r6).booleanValue() == false) goto L40;
            int r82 = this.padding;
            r5.right = r82;
            r5.left = r82;
            RecyclerView.ViewHolder r83 = r7.getChildViewHolder(r6);
            RecyclerView.Adapter r72 = r7.getAdapter();
            if (r83 == null) goto L41;
            if (r72 == null) goto L42;
            int r0 = r83.getAdapterPosition();
            if (r0 == (-1)) goto L35;
            boolean r84 = false;
            if (r0 != 0) goto L12;
            boolean r2 = true;
        L14:
            if (r0 != (r72.getItemCount() - 1)) goto L16;
            r84 = true;
        L16:
            if (r2 == false) goto L23;
            if (this.enableTopPadding == false) goto L20;
            int r22 = this.padding;
        L21:
            r5.top = r22;
            goto L23
        L20:
            r22 = AndroidUtilities.dp(4.0f);
        L23:
            if (RecyclerListView.m12482$$Nest$museSegmentedSections(this.parent) == true) goto L25;
        L27:
            if (r84 == false) goto L29;
            r5.bottom = this.padding;
        L29:
            Rect r4 = (Rect) r6.getTag(R.id.section_offsets_tag);
            if (r4 != null) goto L32;
            int r42 = R.id.section_offsets_tag;
            Rect r73 = new Rect();
            r6.setTag(r42, r73);
            r4 = r73;
        L32:
            r4.set(r5);
            return;
        L25:
            if (isSectionPosition(r72, r0 - 1) == false) goto L27;
            r5.top += AndroidUtilities.dp(2.0f);
            goto L27
        L12:
            r2 = false;
            goto L14
        L35:
            if (r83.isRemoved() == false) goto L43;
            Object r43 = r6.getTag(R.id.section_offsets_tag);
            if ((r43 instanceof Rect) == false) goto L44;
            r5.set((Rect) r43);
            return;
        L44:
            return;
        L43:
            return;
        L42:
            return;
        L41:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas r1, RecyclerView r2, RecyclerView.State r3) {
            if ((r2 instanceof RecyclerListView) == false) goto L6;
            ((RecyclerListView) r2).drawSectionsBackgrounds(r1);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public interface OnInterceptTouchListener {
        boolean onInterceptTouchEvent(MotionEvent r1);
    }

    public interface OnItemClickListener {
        void onItemClick(View r1, int r2);
    }

    public interface OnItemClickListenerExtended {
        default boolean hasDoubleTap(View r1, int r2) {
            return false;
        }

        default void onDoubleTap(View r1, int r2, float r3, float r4) {
        }

        void onItemClick(View r1, int r2, float r3, float r4);
    }

    public interface OnItemLongClickListener {
        boolean onItemClick(View r1, int r2);
    }

    public interface OnItemLongClickListenerExtended {
        boolean onItemClick(View r1, int r2, float r3, float r4);

        default void onLongClickRelease() {
        }

        default void onMove(float r1, float r2) {
        }
    }

    public class RecyclerListViewItemClickListener implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ RecyclerListView this$0;

        /* renamed from: org.telegram.ui.Components.RecyclerListView$RecyclerListViewItemClickListener$1, reason: invalid class name */
        public class AnonymousClass1 extends GestureDetectorFixDoubleTap.OnGestureListener {
            private View doubleTapView;
            final /* synthetic */ RecyclerListViewItemClickListener this$1;
            final /* synthetic */ RecyclerListView val$this$0;

            /* renamed from: org.telegram.ui.Components.RecyclerListView$RecyclerListViewItemClickListener$1$1, reason: invalid class name and collision with other inner class name */
            /* loaded from: classes7.dex */
            public class RunnableC00591 implements Runnable {
                final /* synthetic */ AnonymousClass1 this$2;
                final /* synthetic */ int val$position;
                final /* synthetic */ View val$view;
                final /* synthetic */ float val$x;
                final /* synthetic */ float val$y;

                public RunnableC00591(AnonymousClass1 r1, View r2, int r3, float r4, float r5) {
                    this.this$2 = r1;
                    this.val$view = r2;
                    this.val$position = r3;
                    this.val$x = r4;
                    this.val$y = r5;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (this != RecyclerListView.m12442$$Nest$fgetclickRunnable(this.this$2.this$1.this$0)) goto L5;
                    RecyclerListView.m12465$$Nest$fputclickRunnable(this.this$2.this$1.this$0, null);
                L5:
                    View r0 = this.val$view;
                    if (r0 == null) goto L22;
                    this.this$2.this$1.this$0.onChildPressed(r0, 0.0f, 0.0f, false);
                    if (RecyclerListView.m12448$$Nest$fgetinstantClick(this.this$2.this$1.this$0) == true) goto L23;
                    this.val$view.playSoundEffect(0);     // Catch: Exception -> L19
                L10:
                    this.val$view.sendAccessibilityEvent(1);
                    if (this.val$position == (-1)) goto L24;
                    OnItemClickListener r02 = RecyclerListView.m12452$$Nest$fgetonItemClickListener(this.this$2.this$1.this$0);
                    AnonymousClass1 r1 = this.this$2;
                    if (r02 == null) goto L16;
                    RecyclerListView.m12452$$Nest$fgetonItemClickListener(r1.this$1.this$0).onItemClick(this.val$view, this.val$position);
                    return;
                L16:
                    if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(r1.this$1.this$0) == null) goto L26;
                    OnItemClickListenerExtended r03 = RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$2.this$1.this$0);
                    View r12 = this.val$view;
                    r03.onItemClick(r12, this.val$position, this.val$x - r12.getX(), this.val$y - this.val$view.getY());
                    return;
                L26:
                    return;
                L24:
                    return;
                L23:
                    return;
                }
            }

            public AnonymousClass1(RecyclerListViewItemClickListener r1, RecyclerListView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            private void onPressItem(View r9, MotionEvent r10) {
                if (r9 != null) goto L4;
                return;
            L4:
                if (RecyclerListView.m12452$$Nest$fgetonItemClickListener(this.this$1.this$0) == null) goto L6;
            L7:
                float r5 = r10.getX();
                float r6 = r10.getY();
                this.this$1.this$0.onChildPressed(r9, r5, r6, true);
                int r4 = RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0);
                if (RecyclerListView.m12448$$Nest$fgetinstantClick(this.this$1.this$0) == true) goto L10;
            L18:
                RecyclerListView r0 = this.this$1.this$0;
                RunnableC00591 r1 = new RunnableC00591(this, r9, r4, r5, r6);
                RecyclerListView.m12465$$Nest$fputclickRunnable(r0, r1);
                AndroidUtilities.runOnUIThread(r1, ViewConfiguration.getPressedStateDuration());
                if (RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$1.this$0) == null) goto L27;
                AndroidUtilities.cancelRunOnUIThread(RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$1.this$0));
                RecyclerListView.m12472$$Nest$fputselectChildRunnable(this.this$1.this$0, null);
                RecyclerListView.m12467$$Nest$fputcurrentChildView(this.this$1.this$0, null);
                RecyclerListView.m12470$$Nest$fputinterceptedByChild(this.this$1.this$0, false);
                RecyclerListView.m12480$$Nest$mremoveSelection(this.this$1.this$0, r9, r10);
                return;
            L27:
                return;
            L10:
                if (r4 == (-1)) goto L18;
                r9.playSoundEffect(0);     // Catch: Exception -> L22
            L12:
                r9.sendAccessibilityEvent(1);
                OnItemClickListener r02 = RecyclerListView.m12452$$Nest$fgetonItemClickListener(this.this$1.this$0);
                RecyclerListViewItemClickListener r12 = this.this$1;
                if (r02 == null) goto L16;
                RecyclerListView.m12452$$Nest$fgetonItemClickListener(r12.this$0).onItemClick(r9, r4);
                goto L18
            L16:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(r12.this$0) == null) goto L18;
                RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0).onItemClick(r9, r4, r5 - r9.getX(), r6 - r9.getY());
                goto L18
            L6:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0) != null) goto L7;
            }

            @Override // org.telegram.ui.Components.GestureDetectorFixDoubleTap.OnGestureListener
            public boolean hasDoubleTap(MotionEvent r1) {
                if (RecyclerListView.m12455$$Nest$fgetonItemLongClickListenerExtended(this.this$1.this$0) == null) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent r5) {
                if (this.doubleTapView != null) goto L5;
                return false;
            L5:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0) != null) goto L7;
                return false;
            L7:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0).hasDoubleTap(this.doubleTapView, RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0)) == false) goto L13;
                RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0).onDoubleTap(this.doubleTapView, RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0), r5.getX(), r5.getY());
                this.doubleTapView = null;
                return true;
            L13:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent r1) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent r9) {
                if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0) != null) goto L5;
                return;
            L5:
                if (RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0) != (-1)) goto L7;
                return;
            L7:
                if (RecyclerListView.m12454$$Nest$fgetonItemLongClickListener(this.this$1.this$0) == null) goto L9;
            L11:
                View r0 = RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0);
                OnItemLongClickListener r1 = RecyclerListView.m12454$$Nest$fgetonItemLongClickListener(this.this$1.this$0);
                RecyclerListViewItemClickListener r2 = this.this$1;
                if (r1 == null) goto L18;
                if (RecyclerListView.m12454$$Nest$fgetonItemLongClickListener(r2.this$0).onItemClick(RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0), RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0)) == false) goto L30;
                r0.performHapticFeedback(0, 2);     // Catch: Exception -> L22
            L16:
                r0.sendAccessibilityEvent(2);
                return;
            L30:
                return;
            L18:
                if (RecyclerListView.m12455$$Nest$fgetonItemLongClickListenerExtended(r2.this$0).onItemClick(RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0), RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0), r9.getX() - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0).getX(), r9.getY() - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0).getY()) == false) goto L32;
                r0.performHapticFeedback(0, 2);     // Catch: Exception -> L23
            L20:
                r0.sendAccessibilityEvent(2);
                RecyclerListView.m12471$$Nest$fputlongPressCalled(this.this$1.this$0, true);
                return;
            L32:
                return;
            L9:
                if (RecyclerListView.m12455$$Nest$fgetonItemLongClickListenerExtended(this.this$1.this$0) != null) goto L11;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent r4) {
                if (this.doubleTapView != null) goto L5;
                return false;
            L5:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0) != null) goto L7;
                return false;
            L7:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0).hasDoubleTap(this.doubleTapView, RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0)) == false) goto L13;
                onPressItem(this.doubleTapView, r4);
                this.doubleTapView = null;
                return true;
            L13:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent r5) {
                if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0) != null) goto L5;
            L10:
                return false;
            L5:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0) != null) goto L7;
            L9:
                onPressItem(RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0), r5);
                goto L10
            L7:
                if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$1.this$0).hasDoubleTap(RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0), RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(this.this$1.this$0)) == false) goto L9;
                this.doubleTapView = RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$1.this$0);
                goto L10
            }
        }

        public static /* synthetic */ void $r8$lambda$wdkEVU02vwUHTzSpzZiieSRSka8(RecyclerListViewItemClickListener r0, float r1, float r2) {
            r0.lambda$onInterceptTouchEvent$0(r1, r2);
        }

        public RecyclerListViewItemClickListener(RecyclerListView r3, Context r4) {
            this.this$0 = r3;
            RecyclerListView.m12469$$Nest$fputgestureDetector(r3, new GestureDetectorFixDoubleTap(r4, new AnonymousClass1(this, r3)));
            RecyclerListView.m12447$$Nest$fgetgestureDetector(r3).setIsLongpressEnabled(false);
        }

        private /* synthetic */ void lambda$onInterceptTouchEvent$0(float r4, float r5) {
            if (RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$0) != null) goto L5;
            return;
        L5:
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) == null) goto L9;
            RecyclerListView r0 = this.this$0;
            r0.onChildPressed(RecyclerListView.m12444$$Nest$fgetcurrentChildView(r0), r4, r5, true);
            RecyclerListView.m12472$$Nest$fputselectChildRunnable(this.this$0, null);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView r19, MotionEvent r20) {
            int r3 = r20.getActionMasked();
            if (this.this$0.getScrollState() != 0) goto L5;
            boolean r4 = true;
        L7:
            if (r3 == 0) goto L10;
            if (r3 == 5) goto L10;
        L40:
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) != null) goto L42;
        L47:
            if (r3 == 0) goto L70;
            if (r3 == 5) goto L70;
            if (r3 == 1) goto L56;
            if (r3 == 6) goto L56;
            if (r3 == 3) goto L56;
            if (r4 == false) goto L56;
        L92:
            return false;
        L56:
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) == null) goto L92;
            if (RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$0) == null) goto L60;
            AndroidUtilities.cancelRunOnUIThread(RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$0));
            RecyclerListView.m12472$$Nest$fputselectChildRunnable(this.this$0, null);
        L60:
            View r42 = RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0);
            RecyclerListView r9 = this.this$0;
            r9.onChildPressed(RecyclerListView.m12444$$Nest$fgetcurrentChildView(r9), 0.0f, 0.0f, false);
            RecyclerListView.m12467$$Nest$fputcurrentChildView(this.this$0, null);
            RecyclerListView.m12470$$Nest$fputinterceptedByChild(this.this$0, false);
            RecyclerListView.m12480$$Nest$mremoveSelection(this.this$0, r42, r20);
            if (r3 == 1) goto L65;
            if (r3 == 6) goto L65;
            if (r3 != 3) goto L92;
        L65:
            if (RecyclerListView.m12455$$Nest$fgetonItemLongClickListenerExtended(this.this$0) == null) goto L92;
            if (RecyclerListView.m12451$$Nest$fgetlongPressCalled(this.this$0) == false) goto L92;
            RecyclerListView.m12455$$Nest$fgetonItemLongClickListenerExtended(this.this$0).onLongClickRelease();
            RecyclerListView.m12471$$Nest$fputlongPressCalled(this.this$0, false);
        L70:
            if (RecyclerListView.m12449$$Nest$fgetinterceptedByChild(this.this$0) == false) goto L72;
        L91:
            this.this$0.selectorRect.setEmpty();
            goto L92
        L72:
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) == null) goto L91;
            float r0 = r20.getX();
            float r32 = r20.getY();
            RecyclerListView.m12472$$Nest$fputselectChildRunnable(this.this$0, new RecyclerListView$RecyclerListViewItemClickListener$$ExternalSyntheticLambda0(this, r0, r32));
            AndroidUtilities.runOnUIThread(RecyclerListView.m12463$$Nest$fgetselectChildRunnable(this.this$0), ViewConfiguration.getTapTimeout());
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).isEnabled() == false) goto L90;
            RecyclerListView r43 = this.this$0;
            if (r43.canHighlightChildAt(RecyclerListView.m12444$$Nest$fgetcurrentChildView(r43), r0 - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).getX(), r32 - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).getY()) == false) goto L90;
            RecyclerListView r02 = this.this$0;
            RecyclerListView.m12479$$Nest$mpositionSelector(r02, RecyclerListView.m12443$$Nest$fgetcurrentChildPosition(r02), RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0));
            Drawable r03 = this.this$0.selectorDrawable;
            if (r03 == null) goto L89;
            Drawable r04 = r03.getCurrent();
            if ((r04 instanceof TransitionDrawable) == true) goto L82;
        L88:
            this.this$0.selectorDrawable.setHotspot(r20.getX(), r20.getY());
            goto L89
        L82:
            if (RecyclerListView.m12454$$Nest$fgetonItemLongClickListener(this.this$0) == null) goto L84;
        L87:
            ((TransitionDrawable) r04).startTransition(ViewConfiguration.getLongPressTimeout());
            goto L88
        L84:
            if (RecyclerListView.m12453$$Nest$fgetonItemClickListenerExtended(this.this$0) != null) goto L87;
            ((TransitionDrawable) r04).resetTransition();
        L89:
            RecyclerListView.m12481$$Nest$mupdateSelectorState(this.this$0);
        L90:
            this.this$0.selectorRect.setEmpty();
            goto L92
        L42:
            if (RecyclerListView.m12449$$Nest$fgetinterceptedByChild(this.this$0) == true) goto L47;
            RecyclerListView.m12447$$Nest$fgetgestureDetector(this.this$0).onTouchEvent(r20);     // Catch: Exception -> L45
        L45:
            e = move-exception;
            FileLog.e(e);
        L10:
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) != null) goto L40;
            if (r4 == false) goto L40;
            float r92 = r20.getX();
            float r10 = r20.getY();
            RecyclerListView.m12471$$Nest$fputlongPressCalled(this.this$0, false);
            RecyclerView.ItemAnimator r11 = this.this$0.getItemAnimator();
            if (RecyclerListView.m12441$$Nest$fgetallowItemsInteractionDuringAnimation(this.this$0) == true) goto L18;
            if (r11 == null) goto L18;
            if (r11.isRunning() == false) goto L18;
        L25:
            if ((RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) instanceof ViewGroup) == false) goto L29;
            float r93 = r20.getX() - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).getLeft();
            float r102 = r20.getY() - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).getTop();
            RecyclerListView r112 = this.this$0;
            if (RecyclerListView.m12476$$Nest$mhasClickableChild(r112, (ViewGroup) RecyclerListView.m12444$$Nest$fgetcurrentChildView(r112), r93, r102) == false) goto L29;
            RecyclerListView.m12467$$Nest$fputcurrentChildView(this.this$0, null);
        L29:
            RecyclerListView.m12466$$Nest$fputcurrentChildPosition(this.this$0, -1);
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0) == null) goto L40;
            RecyclerListView r94 = this.this$0;
            if (r94.useLayoutPositionOnClick == false) goto L34;
            RecyclerListView.m12466$$Nest$fputcurrentChildPosition(r94, r19.getChildLayoutPosition(RecyclerListView.m12444$$Nest$fgetcurrentChildView(r94)));
        L35:
            MotionEvent r05 = MotionEvent.obtain(0, 0, r20.getActionMasked(), r20.getX() - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).getLeft(), r20.getY() - RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).getTop(), 0);
            if (RecyclerListView.m12444$$Nest$fgetcurrentChildView(this.this$0).onTouchEvent(r05) == false) goto L38;
            RecyclerListView.m12470$$Nest$fputinterceptedByChild(this.this$0, true);
        L38:
            r05.recycle();
            goto L40
        L34:
            RecyclerListView.m12466$$Nest$fputcurrentChildPosition(r94, r19.getChildAdapterPosition(RecyclerListView.m12444$$Nest$fgetcurrentChildView(r94)));
        L18:
            if (this.this$0.allowSelectChildAtPosition(r92, r10) == false) goto L25;
            View r95 = this.this$0.findChildViewUnder(r92, r10);
            if (r95 == null) goto L25;
            if (this.this$0.allowSelectChildAtPosition(r95) == false) goto L25;
            RecyclerListView.m12467$$Nest$fputcurrentChildView(this.this$0, r95);
            goto L25
        L5:
            r4 = false;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean r1) {
            this.this$0.cancelClickRunnables(true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView r1, MotionEvent r2) {
        }
    }

    public static abstract class SectionsAdapter extends FastScrollAdapter {
        private int count;
        private ArrayList<Integer> hashes;
        private SparseIntArray sectionCache;
        private int sectionCount;
        private SparseIntArray sectionCountCache;
        private SparseIntArray sectionPositionCache;

        /* renamed from: org.telegram.ui.Components.RecyclerListView$SectionsAdapter$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 extends DiffUtil.Callback {
            final /* synthetic */ SectionsAdapter this$0;
            final /* synthetic */ ArrayList val$oldHashes;

            public AnonymousClass1(SectionsAdapter r1, ArrayList r2) {
                this.this$0 = r1;
                this.val$oldHashes = r2;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int r1, int r2) {
                return areItemsTheSame(r1, r2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int r2, int r3) {
                return Objects.equals(this.val$oldHashes.get(r2), SectionsAdapter.m12488$$Nest$fgethashes(this.this$0).get(r3));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return SectionsAdapter.m12488$$Nest$fgethashes(this.this$0).size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return this.val$oldHashes.size();
            }
        }

        /* renamed from: -$$Nest$fgethashes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12488$$Nest$fgethashes(SectionsAdapter r0) {
            return r0.hashes;
        }

        public SectionsAdapter() {
            this.hashes = new ArrayList();
            cleanupCache();
        }

        private int internalGetCountForSection(int r3) {
            int r0 = this.sectionCountCache.get(r3, Integer.MAX_VALUE);
            if (r0 == Integer.MAX_VALUE) goto L5;
            return r0;
        L5:
            int r02 = getCountForSection(r3);
            this.sectionCountCache.put(r3, r02);
            return r02;
        }

        private int internalGetSectionCount() {
            int r0 = this.sectionCount;
            if (r0 < 0) goto L5;
            return r0;
        L5:
            int r02 = getSectionCount();
            this.sectionCount = r02;
            return r02;
        }

        public void cleanupCache() {
            SparseIntArray r0 = this.sectionCache;
            if (r0 != null) goto L5;
            this.sectionCache = new SparseIntArray();
            this.sectionPositionCache = new SparseIntArray();
            this.sectionCountCache = new SparseIntArray();
        L6:
            this.count = -1;
            this.sectionCount = -1;
            return;
        L5:
            r0.clear();
            this.sectionPositionCache.clear();
            this.sectionCountCache.clear();
            goto L6
        }

        public abstract int getCountForSection(int r1);

        public int getHash(int r2, int r3) {
            return Objects.hash(new Object[]{Integer.valueOf((-49612) * r2), getItem(r2, r3)});
        }

        public final Object getItem(int r2) {
            return getItem(getSectionForPosition(r2), getPositionInSectionForPosition(r2));
        }

        public abstract Object getItem(int r1, int r2);

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.count;
            if (r0 < 0) goto L5;
            return r0;
        L5:
            int r02 = 0;
            this.count = 0;
            int r1 = internalGetSectionCount();
        L6:
            int r2 = this.count;
            if (r02 >= r1) goto L9;
            this.count = r2 + internalGetCountForSection(r02);
            r02 = r02 + 1;
            goto L6
        L9:
            return r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemViewType(int r2) {
            return getItemViewType(getSectionForPosition(r2), getPositionInSectionForPosition(r2));
        }

        public abstract int getItemViewType(int r1, int r2);

        public int getPositionInSectionForPosition(int r5) {
            int r0 = this.sectionPositionCache.get(r5, Integer.MAX_VALUE);
            if (r0 == Integer.MAX_VALUE) goto L5;
            return r0;
        L5:
            int r02 = internalGetSectionCount();
            int r1 = 0;
            int r2 = 0;
        L6:
            if (r1 >= r02) goto L13;
            int r3 = internalGetCountForSection(r1) + r2;
            if (r5 < r2) goto L12;
            if (r5 >= r3) goto L12;
            int r03 = r5 - r2;
            this.sectionPositionCache.put(r5, r03);
            return r03;
        L12:
            r1 = r1 + 1;
            r2 = r3;
            goto L6
        L13:
            return -1;
        }

        public abstract int getSectionCount();

        public final int getSectionForPosition(int r5) {
            int r0 = this.sectionCache.get(r5, Integer.MAX_VALUE);
            if (r0 == Integer.MAX_VALUE) goto L5;
            return r0;
        L5:
            int r02 = internalGetSectionCount();
            int r1 = 0;
            int r2 = 0;
        L6:
            if (r1 >= r02) goto L13;
            int r3 = internalGetCountForSection(r1) + r2;
            if (r5 < r2) goto L12;
            if (r5 >= r3) goto L12;
            this.sectionCache.put(r5, r1);
            return r1;
        L12:
            r1 = r1 + 1;
            r2 = r3;
            goto L6
        L13:
            return -1;
        }

        public abstract View getSectionHeaderView(int r1, View r2);

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            int r0 = r3.getAdapterPosition();
            return isEnabled(r3, getSectionForPosition(r0), getPositionInSectionForPosition(r0));
        }

        public abstract boolean isEnabled(RecyclerView.ViewHolder r1, int r2, int r3);

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            update(false);
        }

        public void notifySectionsChanged() {
            cleanupCache();
        }

        public abstract void onBindViewHolder(int r1, int r2, RecyclerView.ViewHolder r3);

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            onBindViewHolder(getSectionForPosition(r3), getPositionInSectionForPosition(r3), r2);
        }

        public void update(boolean r3) {
            ArrayList r0 = new ArrayList(this.hashes);
            updateHashes();
            if (r3 == false) goto L6;
            DiffUtil.calculateDiff(new AnonymousClass1(this, r0), true).dispatchUpdatesTo(this);
            return;
        L6:
            super.notifyDataSetChanged();
        }

        public void updateHashes() {
            cleanupCache();
            this.hashes.clear();
            int r0 = internalGetSectionCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L8;
            int r3 = internalGetCountForSection(r2);
            int r4 = 0;
        L5:
            if (r4 >= r3) goto L7;
            this.hashes.add(Integer.valueOf(getHash(r2, r4)));
            r4 = r4 + 1;
            goto L5
        L7:
            r2 = r2 + 1;
            goto L3
        }
    }

    /* loaded from: classes7.dex */
    public static class SectionsDrawer {
        private static final ArrayList<float[]> groups = null;

        public static class Section {
            public float alpha;
            public float from;
            public boolean round;
            public float to;

            public Section(float r1, float r2, float r3, boolean r4) {
                this.from = r1;
                this.to = r2;
                this.alpha = r3;
                this.round = r4;
            }
        }

        public static /* synthetic */ int $r8$lambda$rD3avRJ01jkStRx6Sr_T5YYoVF4(Section r0, Section r1) {
            return Float.compare(r0.from, r1.from);
        }

        static {
            groups = new ArrayList();
        }

        private static float[] calculateGroup(List<Section> r18, int r19, int r20, float r21) {
            float r2 = r21;
            float r3 = Float.MAX_VALUE;
            float r4 = Float.MIN_VALUE;
            int r5 = r19;
            float r6 = Float.MAX_VALUE;
            float r7 = Float.MIN_VALUE;
        L3:
            float r8 = 0.99f;
            if (r5 >= r20) goto L10;
            Section r9 = r18.get(r5);
            if (r9.alpha < 0.99f) goto L8;
            r6 = Math.min(r6, r9.from);
            r7 = Math.max(r7, r9.to);
        L8:
            r5 = r5 + 1;
            goto L3
        L10:
            if (r6 == Float.MAX_VALUE) goto L12;
            boolean r52 = true;
        L13:
            float r11 = 0.0f;
            int r12 = r19;
            float r13 = 0.0f;
        L14:
            if (r12 >= r20) goto L17;
            Section r14 = r18.get(r12);
            r3 = Math.min(r3, r14.from);
            r4 = Math.max(r4, r14.to);
            r13 = Math.max(r13, r14.alpha);
            r12 = r12 + 1;
            goto L14
        L17:
            if (r13 >= 0.001f) goto L19;
            return null;
        L19:
            if (r52 == true) goto L21;
            float r53 = r2;
            r7 = r4;
        L55:
            if (r7 > r3) goto L58;
            return null;
        L58:
            if ((r20 - r19) != 1) goto L63;
            if (r18.get(r19).round == false) goto L63;
            r53 = (r7 - r3) / 2.0f;
            r2 = r53;
        L63:
            return new float[]{r3, r7, r53, r2, r13};
        L21:
            int r32 = r19;
            float r42 = 0.0f;
            Section r54 = null;
        L22:
            if (r32 >= r20) goto L32;
            Section r132 = r18.get(r32);
            float r142 = r132.alpha;
            if (r142 < r8) goto L26;
            float r16 = r8;
        L31:
            r32 = r32 + 1;
            r8 = r16;
            goto L22
        L26:
            r16 = r8;
            float r82 = r132.from;
            if (r82 >= r6) goto L31;
            float r83 = (r6 - r82) * r142;
            if (r83 <= r42) goto L31;
            r42 = r83;
            r54 = r132;
            goto L31
        L32:
            float r162 = r8;
            int r33 = r19;
            Section r43 = null;
        L33:
            if (r33 >= r20) goto L43;
            Section r84 = r18.get(r33);
            float r133 = r84.alpha;
            if (r133 >= r162) goto L42;
            float r143 = r84.to;
            if (r143 <= r7) goto L42;
            float r144 = (r143 - r7) * r133;
            if (r144 <= r11) goto L42;
            r43 = r84;
            r11 = r144;
        L42:
            r33 = r33 + 1;
            goto L33
        L43:
            if (r54 == null) goto L47;
            float r34 = r54.alpha;
            if (r34 <= 0.001f) goto L47;
            float r35 = AndroidUtilities.lerp(r6, r54.from, r34);
            r53 = AndroidUtilities.lerp(r2, r54.alpha * r2, (r6 - r35) / ((r6 - r54.from) + 0.001f));
            r6 = r35;
        L48:
            r13 = 1.0f;
            if (r43 == null) goto L53;
            float r36 = r43.alpha;
            if (r36 <= 0.001f) goto L53;
            float r37 = AndroidUtilities.lerp(r7, r43.to, r36);
            r2 = AndroidUtilities.lerp(r2, r43.alpha * r2, (r37 - r7) / ((r43.to - r7) + 0.001f));
            r7 = r37;
        L53:
            r3 = r6;
        L47:
            r53 = r2;
            goto L48
        L12:
            r52 = false;
            goto L13
        }

        public static void draw(List<Section> r16, float r17, Utilities.Callback5<Float, Float, Float, Float, Float> r18, boolean r19) {
            if (r16 != null) goto L5;
            return;
        L5:
            if (r16.isEmpty() == true) goto L48;
            Collections.sort(r16, new RecyclerListView$SectionsDrawer$$ExternalSyntheticLambda0());
            groups.clear();
            if (r19 == false) goto L11;
            drawSegmentedSections(r16, r17, r18);
            return;
        L11:
            int r3 = 0;
        L13:
            if (r3 >= r16.size()) goto L24;
            float r4 = r16.get(r3).to;
            int r5 = r3 + 1;
        L16:
            if (r5 >= r16.size()) goto L20;
            if (r16.get(r5).from > (1.5f + r4)) goto L20;
            r4 = Math.max(r4, r16.get(r5).to);
            r5 = r5 + 1;
        L20:
            float[] r32 = calculateGroup(r16, r3, r5, r17);
            if (r32 == null) goto L23;
            groups.add(r32);
        L23:
            r3 = r5;
            goto L13
        L24:
            int r0 = 0;
        L25:
            ArrayList<float[]> r33 = groups;
            if (r0 >= r33.size()) goto L49;
            float[] r42 = r33.get(r0);
            float r52 = r42[0];
            float r7 = r42[1];
            float r8 = r42[2];
            float r9 = r42[3];
            float r43 = r42[4];
            if (r0 <= 0) goto L33;
            float r11 = r52 - r33.get(r0 - 1)[1];
            float r12 = r17 * 0.2f;
            if (r11 >= r12) goto L33;
            r8 = Math.min(r8, (r11 / r12) * r17);
        L33:
            if (r0 >= (r33.size() - 1)) goto L37;
            float r34 = r33.get(r0 + 1)[0] - r7;
            float r6 = r17 * 0.2f;
            if (r34 >= r6) goto L37;
            r9 = Math.min(r9, (r34 / r6) * r17);
        L37:
            r18.run(Float.valueOf(r52), Float.valueOf(r7), Float.valueOf(r8), Float.valueOf(r9), Float.valueOf(r43));
            r0 = r0 + 1;
            goto L25
        L49:
            return;
        }

        private static void drawSegmentedSections(List<Section> r20, float r21, Utilities.Callback5<Float, Float, Float, Float, Float> r22) {
            float r1 = Math.min(r21, AndroidUtilities.dp(4.0f));
            float r4 = AndroidUtilities.dp(2.0f) + 1.5f;
            int r6 = 0;
        L4:
            if (r6 >= r20.size()) goto L36;
            Section r7 = r20.get(r6);
            if (r7.alpha < 0.001f) goto L35;
            boolean r8 = true;
            if (r6 > 0) goto L11;
        L13:
            boolean r9 = false;
        L15:
            if (r6 < (r20.size() - 1)) goto L17;
        L19:
            r8 = false;
        L20:
            float r10 = r7.from;
            float r11 = r7.to;
            if (r11 <= r10) goto L35;
            if (r9 == false) goto L25;
            float r12 = r1;
        L26:
            if (r8 == false) goto L28;
            float r13 = r1;
        L29:
            if (r9 == true) goto L34;
            if (r8 == true) goto L34;
            if (r7.round == false) goto L34;
            r12 = (r11 - r10) / 2.0f;
            r13 = r12;
        L34:
            r22.run(Float.valueOf(r10), Float.valueOf(r11), Float.valueOf(r12), Float.valueOf(r13), Float.valueOf(r7.alpha));
            goto L35
        L28:
            r13 = r21;
            goto L29
        L25:
            r12 = r21;
            goto L26
        L17:
            if (r20.get(r6 + 1).from > (r7.to + r4)) goto L19;
        L11:
            if (r7.from > (r20.get(r6 - 1).to + r4)) goto L13;
            r9 = true;
        L35:
            r6 = r6 + 1;
            goto L4
        }
    }

    public static abstract class SelectionAdapter extends RecyclerView.Adapter {
        public SelectionAdapter() {
        }

        public int getSelectionBottomPadding(View r1) {
            return 0;
        }

        public abstract boolean isEnabled(RecyclerView.ViewHolder r1);
    }

    /* loaded from: classes7.dex */
    public interface onMultiSelectionChanged {
        boolean canSelect(int r1);

        int checkPosition(int r1, boolean r2);

        void getPaddings(int[] r1);

        default int getStartDragDistance() {
            return 0;
        }

        boolean limitReached();

        void onSelectionChanged(int r1, boolean r2, float r3, float r4);

        void scrollBy(int r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$2wEbk7S82O_026x6oum7LynZ9ss(RecyclerListView r1, Utilities.CallbackReturn r2, SparseIntArray r3, View r4) {
        if (r4.getParent() != r1) goto L5;
        Boolean r22 = (Boolean) r2.run(r4);     // Catch: Exception -> L10
        boolean r0 = r22.booleanValue();     // Catch: Exception -> L10
        RecyclerView.ViewHolder r12 = r1.getChildViewHolder(r4);     // Catch: Exception -> L10
        if (r12 == null) goto L9;
        r3.put(r12.getItemViewType(), r0 ? 1 : 0);     // Catch: Exception -> L10
    L9:
        return r22;
    L5:
        return Boolean.FALSE;
    L11:
        return Boolean.FALSE;
    }

    public static /* synthetic */ void $r8$lambda$8R2_5Tvsn9Mnc1AJmWR1h1znjPM(RecyclerListView r0, Canvas r1, Float r2, Float r3, Float r4, Float r5, Float r6) {
        r0.lambda$drawSectionsBackgrounds$7(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$X1n-IgPpRgCTc9PD-N1Qu44I-0M, reason: not valid java name */
    public static /* synthetic */ void m12438$r8$lambda$X1nIgPpRgCTc9PDN1Qu44I0M(RecyclerListView r0) {
        r0.lambda$onLayout$0();
    }

    public static /* synthetic */ void $r8$lambda$t7pYbJdB_Jl_24x5Eo6vjFbxIYc(Runnable r0, int r1, boolean r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$todbV2Ivu8XZPH3CTbR9h9z9NJU(RecyclerListView r0, Canvas r1, Float r2, Float r3, Float r4, Float r5, Float r6) {
        r0.lambda$drawSectionsBackgrounds$6(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ Boolean $r8$lambda$uLqj5sOj2BJSCnoGu0P0HShlnBQ(SparseIntArray r1, Integer r2) {
        int r12 = r1.get(r2.intValue(), -1);
        if (r12 == (-1)) goto L5;
        boolean r22 = true;
        if (r12 == 1) goto L11;
        r22 = false;
    L11:
        return Boolean.valueOf(r22);
    L5:
        return Boolean.TRUE;
    }

    /* renamed from: $r8$lambda$ykTl0wo3HGG-pLyHN9LKS4HQdCU, reason: not valid java name */
    public static /* synthetic */ Boolean m12439$r8$lambda$ykTl0wo3HGGpLyHN9LKS4HQdCU(View r1) {
        if ((r1 instanceof TextInfoPrivacyCell) == false) goto L5;
    L19:
        boolean r12 = false;
    L21:
        return Boolean.valueOf(r12);
    L5:
        if ((r1 instanceof ShadowSectionCell) == true) goto L19;
        if ((r1 instanceof FiltersSetupActivity.HintInnerCell) == true) goto L19;
        if ((r1 instanceof GraySectionCell) == true) goto L19;
        if ((r1 instanceof CollapseTextCell) == true) goto L19;
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L17;
        if ((r1 instanceof HeaderCell) == true) goto L19;
    L17:
        if (Objects.equals(r1.getTag(), -33024) == true) goto L19;
        r12 = true;
        goto L21
    }

    /* renamed from: $r8$lambda$zjfn-mkvkj-L6ntnGYbeOv07zF0, reason: not valid java name */
    public static /* synthetic */ void m12440$r8$lambda$zjfnmkvkjL6ntnGYbeOv07zF0(RecyclerListView r0) {
        r0.lambda$highlightRowInternal$1();
    }

    /* renamed from: -$$Nest$fgetallowItemsInteractionDuringAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12441$$Nest$fgetallowItemsInteractionDuringAnimation(RecyclerListView r0) {
        return r0.allowItemsInteractionDuringAnimation;
    }

    /* renamed from: -$$Nest$fgetclickRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12442$$Nest$fgetclickRunnable(RecyclerListView r0) {
        return r0.clickRunnable;
    }

    /* renamed from: -$$Nest$fgetcurrentChildPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12443$$Nest$fgetcurrentChildPosition(RecyclerListView r0) {
        return r0.currentChildPosition;
    }

    /* renamed from: -$$Nest$fgetcurrentChildView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12444$$Nest$fgetcurrentChildView(RecyclerListView r0) {
        return r0.currentChildView;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12445$$Nest$fgetemptyView(RecyclerListView r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetfastScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FastScroll m12446$$Nest$fgetfastScroll(RecyclerListView r0) {
        return r0.fastScroll;
    }

    /* renamed from: -$$Nest$fgetgestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GestureDetectorFixDoubleTap m12447$$Nest$fgetgestureDetector(RecyclerListView r0) {
        return r0.gestureDetector;
    }

    /* renamed from: -$$Nest$fgetinstantClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12448$$Nest$fgetinstantClick(RecyclerListView r0) {
        return r0.instantClick;
    }

    /* renamed from: -$$Nest$fgetinterceptedByChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12449$$Nest$fgetinterceptedByChild(RecyclerListView r0) {
        return r0.interceptedByChild;
    }

    /* renamed from: -$$Nest$fgetisViewTypeSection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.CallbackReturn m12450$$Nest$fgetisViewTypeSection(RecyclerListView r0) {
        return r0.isViewTypeSection;
    }

    /* renamed from: -$$Nest$fgetlongPressCalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12451$$Nest$fgetlongPressCalled(RecyclerListView r0) {
        return r0.longPressCalled;
    }

    /* renamed from: -$$Nest$fgetonItemClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnItemClickListener m12452$$Nest$fgetonItemClickListener(RecyclerListView r0) {
        return r0.onItemClickListener;
    }

    /* renamed from: -$$Nest$fgetonItemClickListenerExtended, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnItemClickListenerExtended m12453$$Nest$fgetonItemClickListenerExtended(RecyclerListView r0) {
        return r0.onItemClickListenerExtended;
    }

    /* renamed from: -$$Nest$fgetonItemLongClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnItemLongClickListener m12454$$Nest$fgetonItemLongClickListener(RecyclerListView r0) {
        return r0.onItemLongClickListener;
    }

    /* renamed from: -$$Nest$fgetonItemLongClickListenerExtended, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnItemLongClickListenerExtended m12455$$Nest$fgetonItemLongClickListenerExtended(RecyclerListView r0) {
        return r0.onItemLongClickListenerExtended;
    }

    /* renamed from: -$$Nest$fgetonScrollListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView.OnScrollListener m12456$$Nest$fgetonScrollListener(RecyclerListView r0) {
        return r0.onScrollListener;
    }

    /* renamed from: -$$Nest$fgetoverlayContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12457$$Nest$fgetoverlayContainer(RecyclerListView r0) {
        return r0.overlayContainer;
    }

    /* renamed from: -$$Nest$fgetpendingHighlightPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IntReturnCallback m12458$$Nest$fgetpendingHighlightPosition(RecyclerListView r0) {
        return r0.pendingHighlightPosition;
    }

    /* renamed from: -$$Nest$fgetpinnedHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12459$$Nest$fgetpinnedHeader(RecyclerListView r0) {
        return r0.pinnedHeader;
    }

    /* renamed from: -$$Nest$fgetremoveHighlighSelectionRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12460$$Nest$fgetremoveHighlighSelectionRunnable(RecyclerListView r0) {
        return r0.removeHighlighSelectionRunnable;
    }

    /* renamed from: -$$Nest$fgetresetSelectorOnChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12461$$Nest$fgetresetSelectorOnChanged(RecyclerListView r0) {
        return r0.resetSelectorOnChanged;
    }

    /* renamed from: -$$Nest$fgetsectionOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12462$$Nest$fgetsectionOffset(RecyclerListView r0) {
        return r0.sectionOffset;
    }

    /* renamed from: -$$Nest$fgetselectChildRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12463$$Nest$fgetselectChildRunnable(RecyclerListView r0) {
        return r0.selectChildRunnable;
    }

    /* renamed from: -$$Nest$fgetselfOnLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12464$$Nest$fgetselfOnLayout(RecyclerListView r0) {
        return r0.selfOnLayout;
    }

    /* renamed from: -$$Nest$fputclickRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12465$$Nest$fputclickRunnable(RecyclerListView r0, Runnable r1) {
        r0.clickRunnable = r1;
    }

    /* renamed from: -$$Nest$fputcurrentChildPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12466$$Nest$fputcurrentChildPosition(RecyclerListView r0, int r1) {
        r0.currentChildPosition = r1;
    }

    /* renamed from: -$$Nest$fputcurrentChildView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12467$$Nest$fputcurrentChildView(RecyclerListView r0, View r1) {
        r0.currentChildView = r1;
    }

    /* renamed from: -$$Nest$fputcurrentFirst, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12468$$Nest$fputcurrentFirst(RecyclerListView r0, int r1) {
        r0.currentFirst = r1;
    }

    /* renamed from: -$$Nest$fputgestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12469$$Nest$fputgestureDetector(RecyclerListView r0, GestureDetectorFixDoubleTap r1) {
        r0.gestureDetector = r1;
    }

    /* renamed from: -$$Nest$fputinterceptedByChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12470$$Nest$fputinterceptedByChild(RecyclerListView r0, boolean r1) {
        r0.interceptedByChild = r1;
    }

    /* renamed from: -$$Nest$fputlongPressCalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12471$$Nest$fputlongPressCalled(RecyclerListView r0, boolean r1) {
        r0.longPressCalled = r1;
    }

    /* renamed from: -$$Nest$fputselectChildRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12472$$Nest$fputselectChildRunnable(RecyclerListView r0, Runnable r1) {
        r0.selectChildRunnable = r1;
    }

    /* renamed from: -$$Nest$mcheckIfEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12473$$Nest$mcheckIfEmpty(RecyclerListView r0, boolean r1) {
        r0.checkIfEmpty(r1);
    }

    /* renamed from: -$$Nest$mcheckStopHeavyOperations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12474$$Nest$mcheckStopHeavyOperations(RecyclerListView r0, int r1) {
        r0.checkStopHeavyOperations(r1);
    }

    /* renamed from: -$$Nest$mchekMultiselect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12475$$Nest$mchekMultiselect(RecyclerListView r0, float r1, float r2) {
        return r0.chekMultiselect(r1, r2);
    }

    /* renamed from: -$$Nest$mhasClickableChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12476$$Nest$mhasClickableChild(RecyclerListView r0, ViewGroup r1, float r2, float r3) {
        return r0.hasClickableChild(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mhighlightRowInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12477$$Nest$mhighlightRowInternal(RecyclerListView r0, IntReturnCallback r1, int r2, boolean r3) {
        r0.highlightRowInternal(r1, r2, r3);
    }

    /* renamed from: -$$Nest$minvalidateSegmentedSectionOffsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12478$$Nest$minvalidateSegmentedSectionOffsets(RecyclerListView r0) {
        r0.invalidateSegmentedSectionOffsets();
    }

    /* renamed from: -$$Nest$mpositionSelector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12479$$Nest$mpositionSelector(RecyclerListView r0, int r1, View r2) {
        r0.positionSelector(r1, r2);
    }

    /* renamed from: -$$Nest$mremoveSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12480$$Nest$mremoveSelection(RecyclerListView r0, View r1, MotionEvent r2) {
        r0.removeSelection(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateSelectorState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12481$$Nest$mupdateSelectorState(RecyclerListView r0) {
        r0.updateSelectorState();
    }

    /* renamed from: -$$Nest$museSegmentedSections, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12482$$Nest$museSegmentedSections(RecyclerListView r0) {
        return r0.useSegmentedSections();
    }

    static {
        Method r0 = View.class.getDeclaredMethod("initializeScrollbars", new Class[]{TypedArray.class});     // Catch: Exception -> L4
    L5:
        initializeScrollbars = r0;
        TAG_ROUND_SECTION = R.id.round_section_tag;
        sectionBackgroundPaint = new Paint(1);
        sectionBackgroundStrokePaint = new Paint(1);
        sectionBackgroundPath = new Path();
        radii = new float[8];
        return;
    L4:
        r0 = null;
        goto L5
    }

    public RecyclerListView(Context r2) {
        this(r2, null);
    }

    @SuppressLint({"PrivateApi"})
    public RecyclerListView(Context r5, Theme.ResourcesProvider r6) {
        super(r5);
        this.drawSelection = true;
        this.allowItemsInteractionDuringAnimation = true;
        this.currentFirst = -1;
        this.currentVisible = -1;
        this.skipDrawSection = false;
        this.hideIfEmpty = true;
        this.selectorType = 2;
        this.selectorRect = new Rect();
        this.translateSelector = -1;
        this.scrollEnabled = true;
        this.lastX = Float.MAX_VALUE;
        this.lastY = Float.MAX_VALUE;
        this.accessibilityEnabled = true;
        this.accessibilityDelegate = new AnonymousClass1(this);
        this.resetSelectorOnChanged = true;
        this.observer = new AnonymousClass2(this);
        this.scroller = new AnonymousClass6(this);
        this.applyPaddingToSections = false;
        this.segmentedSectionsEnabled = true;
        this.clipPath = new Path();
        this.resourcesProvider = r6;
        EdgeEffectTrackerFactory r62 = new EdgeEffectTrackerFactory();
        this.edgeEffectTrackerFactory = r62;
        setEdgeEffectFactory(r62);
        setGlowColor(getThemedColor(Theme.key_actionBarDefault));
        Drawable r63 = Theme.getSelectorDrawable(getThemedColor(Theme.key_listSelector), false);
        this.selectorDrawable = r63;
        r63.setCallback(this);
    L8:
        th = move-exception;
        FileLog.e(th);
    L16:
        super.setOnScrollListener(new AnonymousClass3(this));
        addOnItemTouchListener(new RecyclerListViewItemClickListener(this, r5));
        if (ExteraConfig.getInAppVibration() == true) goto L22;
        VibratorUtils.disableHapticFeedback(this);
        return;
    L22:
        return;
    L4:
        if (gotAttributes == true) goto L11;
        int[] r64 = getResourceDeclareStyleableIntArray("com.android.internal", "View");     // Catch: Throwable -> L8
        attributes = r64;     // Catch: Throwable -> L8
        if (r64 != null) goto L10;
        attributes = new int[0];     // Catch: Throwable -> L8
    L10:
        gotAttributes = true;     // Catch: Throwable -> L8
    L11:
        TypedArray r65 = r5.getTheme().obtainStyledAttributes(attributes);     // Catch: Throwable -> L8
        Method r0 = initializeScrollbars;     // Catch: Throwable -> L8
        if (r0 == null) goto L16;
        r0.invoke(this, new Object[]{r65});     // Catch: Throwable -> L8
        goto L16
    }

    public static float bottom(View r1) {
        if (r1.getTag(R.id.dragging) == null) goto L7;
        return r1.getBottom();
    L7:
        return r1.getY() + r1.getHeight();
    }

    private static Pair<Utilities.CallbackReturn<View, Boolean>, Utilities.CallbackReturn<Integer, Boolean>> cachedIsViewTypeShadow(RecyclerListView r3, Utilities.CallbackReturn<View, Boolean> r4) {
        SparseIntArray r0 = new SparseIntArray();
        return new Pair(new RecyclerListView$$ExternalSyntheticLambda5(r3, r4, r0), new RecyclerListView$$ExternalSyntheticLambda6(r0));
    }

    private void cancelMultiselectScroll() {
        this.multiselectScrollRunning = false;
        AndroidUtilities.cancelRunOnUIThread(this.scroller);
    }

    private void checkIfEmpty(boolean r11) {
        if (this.isHidden == true) goto L56;
        int r1 = 0;
        if (getAdapter() == null) goto L48;
        if (this.emptyView == null) goto L48;
        boolean r0 = emptyViewIsVisible();
        if (r0 == false) goto L13;
        int r3 = 0;
    L15:
        if (this.animateEmptyView == true) goto L17;
    L18:
        r11 = false;
    L19:
        emptyViewUpdated(r0, r11);
        if (r11 == true) goto L22;
        this.emptyViewAnimateToVisibility = r3;
        this.emptyView.setVisibility(r3);
        this.emptyView.setAlpha(1.0f);
    L39:
        if (this.hideIfEmpty == false) goto L53;
        if (r0 == false) goto L43;
        r1 = 4;
    L43:
        if (getVisibility() == r1) goto L45;
        setVisibility(r1);
    L45:
        this.hiddenByEmptyView = true;
        return;
    L53:
        return;
    L22:
        if (this.emptyViewAnimateToVisibility == r3) goto L39;
        this.emptyViewAnimateToVisibility = r3;
        View r112 = this.emptyView;
        if (r3 != 0) goto L32;
        r112.animate().setListener(null).cancel();
        if (this.emptyView.getVisibility() != 8) goto L30;
        this.emptyView.setVisibility(0);
        this.emptyView.setAlpha(0.0f);
        if (this.emptyViewAnimationType != 1) goto L30;
        this.emptyView.setScaleX(0.7f);
        this.emptyView.setScaleY(0.7f);
    L30:
        this.emptyView.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
        goto L39
    L32:
        if (r112.getVisibility() == 8) goto L39;
        ViewPropertyAnimator r113 = this.emptyView.animate().alpha(0.0f);
        if (this.emptyViewAnimationType != 1) goto L36;
        r113.scaleY(0.7f).scaleX(0.7f);
    L36:
        r113.setDuration(150).setListener(new AnonymousClass4(this)).start();
        goto L39
    L17:
        if (SharedConfig.animationsEnabled() == true) goto L19;
    L13:
        r3 = 8;
    L48:
        if (this.hiddenByEmptyView == true) goto L50;
        return;
    L50:
        if (getVisibility() == 0) goto L55;
        setVisibility(0);
        this.hiddenByEmptyView = false;
        return;
    L55:
        return;
    }

    private void checkStopHeavyOperations(int r3) {
        boolean r1 = this.stoppedAllHeavyOperations;
        if (r3 != 0) goto L7;
        if (r1 == false) goto L12;
        this.stoppedAllHeavyOperations = false;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
        return;
    L12:
        return;
    L7:
        if (r1 == false) goto L9;
        return;
    L9:
        if (this.allowStopHeaveOperations == false) goto L14;
        this.stoppedAllHeavyOperations = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        return;
    }

    private boolean chekMultiselect(float r11, float r12) {
        int r0 = getMeasuredHeight();
        int[] r1 = this.listPaddings;
        float r122 = Math.min(r0 - r1[1], Math.max(r12, r1[0]));
        float r112 = Math.min(getMeasuredWidth(), Math.max(r11, 0.0f));
        int r02 = 0;
    L4:
        if (r02 >= getChildCount()) goto L64;
        this.multiSelectionListener.getPaddings(this.listPaddings);
        if (this.useRelativePositions == true) goto L63;
        View r13 = getChildAt(r02);
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set(r13.getLeft(), r13.getTop(), r13.getLeft() + r13.getMeasuredWidth(), r13.getTop() + r13.getMeasuredHeight());
        if (r4.contains(r112, r122) == false) goto L63;
        int r03 = getChildLayoutPosition(r13);
        int r14 = this.currentSelectedPosition;
        if (r14 == r03) goto L61;
        int r42 = this.startSelectionFrom;
        if (r14 > r42) goto L17;
        if (r03 > r42) goto L17;
        boolean r15 = false;
    L18:
        r03 = this.multiSelectionListener.checkPosition(r03, r15);
        int r43 = this.currentSelectedPosition;
        if (r15 == false) goto L40;
        if (r03 > r43) goto L22;
    L32:
        if (r43 <= r03) goto L61;
        if (r43 == this.startSelectionFrom) goto L39;
        if (this.multiSelectionListener.canSelect(r43) == false) goto L39;
        this.multiSelectionListener.onSelectionChanged(r43, false, r112, r122);
    L39:
        r43 = r43 - 1;
        goto L32
    L22:
        if (this.multiSelectionListener.limitReached() == true) goto L61;
        int r16 = this.currentSelectedPosition + 1;
    L24:
        if (r16 > r03) goto L61;
        if (r16 == this.startSelectionFrom) goto L31;
        if (this.multiSelectionListener.canSelect(r16) == false) goto L31;
        this.multiSelectionListener.onSelectionChanged(r16, true, r112, r122);
    L31:
        r16 = r16 + 1;
        goto L24
    L40:
        if (r03 <= r43) goto L50;
    L41:
        if (r43 >= r03) goto L61;
        if (r43 == this.startSelectionFrom) goto L48;
        if (this.multiSelectionListener.canSelect(r43) == false) goto L48;
        this.multiSelectionListener.onSelectionChanged(r43, false, r112, r122);
    L48:
        r43 = r43 + 1;
        goto L41
    L50:
        if (this.multiSelectionListener.limitReached() == true) goto L61;
        int r17 = this.currentSelectedPosition - 1;
    L52:
        if (r17 < r03) goto L61;
        if (r17 == this.startSelectionFrom) goto L59;
        if (this.multiSelectionListener.canSelect(r17) == false) goto L59;
        this.multiSelectionListener.onSelectionChanged(r17, true, r112, r122);
    L59:
        r17 = r17 - 1;
    L17:
        r15 = true;
    L61:
        if (this.multiSelectionListener.limitReached() == true) goto L64;
        this.currentSelectedPosition = r03;
    L63:
        r02 = r02 + 1;
    L64:
        return true;
    }

    private void clipChild(Canvas r6, View r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (this.sectionsItemDecoration.isSectionItem.run(r7).booleanValue() == false) goto L23;
        int r0 = getChildAdapterPosition(r7);
        boolean r2 = false;
        if (r0 != (-1)) goto L9;
        boolean r02 = false;
    L20:
        clipChildWithSection(r6, r7, r2, r02);
        return;
    L9:
        View r1 = findViewByPosition(r0 - 1);
        View r03 = findViewByPosition(r0 + 1);
        if (r1 != null) goto L12;
    L14:
        boolean r12 = false;
    L15:
        if (r03 != null) goto L17;
    L19:
        r02 = r2;
        r2 = r12;
        goto L20
    L17:
        if (this.sectionsItemDecoration.isSectionItem.run(r03).booleanValue() == false) goto L19;
        r2 = true;
        goto L19
    L12:
        if (this.sectionsItemDecoration.isSectionItem.run(r1).booleanValue() == false) goto L14;
        r12 = true;
        goto L15
    }

    private void clipChildWithSection(Canvas r7, View r8, boolean r9, boolean r10) {
        if (useSegmentedSections() == false) goto L6;
        clipChildWithSegmentedSection(r7, r8);
        return;
    L6:
        RectF r0 = AndroidUtilities.rectTmp;
        float r1 = r8.getX();
        if (this.applyPaddingToSections == false) goto L9;
        float r2 = getPaddingTop();
    L10:
        float r22 = Math.max(r2, top(r8));
        float r3 = r8.getX() + r8.getWidth();
        float r4 = getHeight();
        if (this.applyPaddingToSections == false) goto L13;
        float r5 = getPaddingBottom();
    L14:
        r0.set(r1, r22, r3, Math.min(r4 - r5, bottom(r8)));
        if (r9 == false) goto L27;
        if (r10 == false) goto L27;
        r10 = false;
        if (top(r8) < r0.top) goto L20;
        r9 = true;
    L22:
        if (bottom(r8) > r0.bottom) goto L24;
        r10 = true;
    L24:
        if (r9 == false) goto L27;
        if (r10 == false) goto L27;
        return;
    L20:
        r9 = false;
    L27:
        if (r9 == true) goto L35;
        if (r10 == true) goto L35;
        this.clipPath.rewind();
        if (isRoundSectionView(r8) == false) goto L32;
        float r82 = getSingleSectionRadius(r0);
    L33:
        this.clipPath.addRoundRect(r0, r82, r82, Path.Direction.CW);
        r7.clipPath(this.clipPath);
        return;
    L32:
        r82 = this.sectionRadius;
    L35:
        if (r9 == true) goto L38;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r0, this.sectionRadiusTop, Path.Direction.CW);
        r7.clipPath(this.clipPath);
        return;
    L38:
        if (r10 == true) goto L41;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r0, this.sectionRadiusBottom, Path.Direction.CW);
        r7.clipPath(this.clipPath);
        return;
    L41:
        return;
    L13:
        r5 = -this.sectionRadius;
        goto L14
    L9:
        r2 = -this.sectionRadius;
        goto L10
    }

    private void clipChildWithSegmentedSection(Canvas r5, View r6) {
        RectF r0 = AndroidUtilities.rectTmp;
        if (setSegmentedSectionRect(r6, r0) == true) goto L5;
        return;
    L5:
        this.clipPath.rewind();
        float[] r1 = radii;
        float r2 = getSegmentedSectionTopRadius(r6, r0);
        r1[3] = r2;
        r1[2] = r2;
        r1[1] = r2;
        r1[0] = r2;
        float r62 = getSegmentedSectionBottomRadius(r6, r0);
        r1[7] = r62;
        r1[6] = r62;
        r1[5] = r62;
        r1[4] = r62;
        this.clipPath.addRoundRect(r0, r1, Path.Direction.CW);
        r5.clipPath(this.clipPath);
    }

    private void clipSelector(Canvas r3, View r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (this.selectorIsSection == true) goto L7;
        return;
    L7:
        if (useSegmentedSections() == false) goto L10;
        clipChildWithSegmentedSection(r3, r4);
        return;
    L10:
        clipChildWithSection(r3, r4, this.selectorSectionHasPrev, this.selectorSectionHasNext);
    }

    private Drawable createClipBackgroundDrawable(View r2, RectF r3, Path r4) {
        return new AnonymousClass7(this, r2, r4, r3);
    }

    public static void drawBackgroundRect(Canvas r6, RectF r7, float r8, float r9, float r10, Theme.ResourcesProvider r11) {
        if (SharedConfig.shadowsInSections == false) goto L5;
        Paint r0 = sectionBackgroundStrokePaint;
        r0.setShadowLayer(AndroidUtilities.dpf2(0.33f), 0.0f, 0.0f, Theme.multAlpha(201326592, r10));
        r0.setColor(0);
        sectionBackgroundPaint.setShadowLayer(AndroidUtilities.dpf2(2.0f), 0.0f, AndroidUtilities.dpf2(0.33f), Theme.multAlpha(167772160, r10));
    L6:
        Paint r02 = sectionBackgroundPaint;
        r02.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, r11), r10));
        if (r8 == r9) goto L9;
        Path r102 = sectionBackgroundPath;
        r102.rewind();
        float[] r112 = radii;
        r112[3] = r8;
        r112[2] = r8;
        r112[1] = r8;
        r112[0] = r8;
        r112[7] = r9;
        r112[6] = r9;
        r112[5] = r9;
        r112[4] = r9;
        r102.addRoundRect(r7, r112, Path.Direction.CW);
        if (SharedConfig.shadowsInSections == false) goto L16;
        r6.drawPath(r102, sectionBackgroundStrokePaint);
    L16:
        r6.drawPath(r102, r02);
        return;
    L9:
        if (SharedConfig.shadowsInSections == false) goto L11;
        r6.drawRoundRect(r7, r8, r8, sectionBackgroundStrokePaint);
    L11:
        r6.drawRoundRect(r7, r8, r8, r02);
        return;
    L5:
        sectionBackgroundPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        goto L6
    }

    private void drawSectionBackground(Canvas r9, View r10, View r11, boolean r12, boolean r13) {
        if (r10 == null) goto L37;
        if (r11 == null) goto L38;
        float r1 = 0.0f;
        if ((r11 instanceof JoinToSendSettingsView) == false) goto L9;
        float r0 = ((JoinToSendSettingsView) r11).getBottomInfoMargin();
    L10:
        RectF r102 = AndroidUtilities.rectTmp;
        float r3 = r10.getLeft();
        if (this.applyPaddingToSections == false) goto L13;
        float r4 = getPaddingTop();
    L14:
        float r5 = top(r10);
        if (r12 == false) goto L17;
        float r6 = this.sectionRadius;
    L18:
        float r42 = Math.max(r4, r5 - r6);
        float r52 = r10.getRight();
        float r62 = getHeight();
        if (this.applyPaddingToSections == false) goto L21;
        float r7 = getPaddingBottom();
    L22:
        float r63 = r62 - r7;
        float r72 = bottom(r11);
        if (r13 == false) goto L25;
        r1 = this.sectionRadius;
    L25:
        r102.set(r3, r42, r52, Math.min(r63, (r72 + r1) - r0));
        if (r102.bottom < r102.top) goto L39;
        if (r10 != r11) goto L34;
        if (r12 == true) goto L34;
        if (r13 == true) goto L34;
        if (isRoundSectionView(r10) == false) goto L34;
        float r112 = getSingleSectionRadius(r102);
    L35:
        float r122 = r112;
        this.drawSectionBackground.run(r9, r102, Float.valueOf(r122), Float.valueOf(r122), Float.valueOf(r10.getAlpha()));
        return;
    L34:
        r112 = this.sectionRadius;
        goto L35
    L39:
        return;
    L21:
        r7 = -this.sectionRadius;
        goto L22
    L17:
        r6 = 0.0f;
        goto L18
    L13:
        r4 = -this.sectionRadius;
        goto L14
    L9:
        r0 = 0.0f;
        goto L10
    L38:
        return;
    }

    private void drawSegmentedSectionBackground(Canvas r8, View r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        if (this.drawSectionBackground == null) goto L12;
        RectF r3 = AndroidUtilities.rectTmp;
        if (setSegmentedSectionRect(r9, r3) == false) goto L13;
        this.drawSectionBackground.run(r8, r3, Float.valueOf(getSegmentedSectionTopRadius(r9, r3)), Float.valueOf(getSegmentedSectionBottomRadius(r9, r3)), Float.valueOf(r9.getAlpha()));
        return;
    L13:
        return;
    }

    private void drawSegmentedSectionsBackgrounds(Canvas r5) {
        if (this.sectionsItemDecoration == null) goto L23;
        int r0 = 0;
    L7:
        if (r0 >= getChildCount()) goto L32;
        View r1 = getChildAt(r0);
        if (r1 == this.draggingChild) goto L22;
        if (r1 == this.emptyView) goto L22;
        if (r1.getVisibility() != 0) goto L22;
        if (r1.getAlpha() <= 0.0f) goto L22;
        if (this.sectionsItemDecoration.isSectionItem.run(r1).booleanValue() == false) goto L22;
        if (isInsideForcedSection(getChildAdapterPosition(r1)) == true) goto L22;
        drawSegmentedSectionBackground(r5, r1);
    L22:
        r0 = r0 + 1;
        goto L7
    L32:
        return;
    }

    private void drawSelector(Canvas r2) {
        if (hasSections() == false) goto L6;
        r2.save();
        clipSelector(r2, this.selectorView);
        this.selectorDrawable.draw(r2);
        r2.restore();
        return;
    L6:
        this.selectorDrawable.draw(r2);
    }

    private void drawSelectors2(Canvas r8) {
        if (this.selectorRect.isEmpty() == true) goto L35;
        Drawable r0 = this.selectorDrawable;
        if (r0 == null) goto L36;
        int r1 = this.translateSelector;
        if (r1 == (-2)) goto L12;
        if (r1 == this.selectorPosition) goto L12;
    L18:
        r0.setBounds(this.selectorRect);
    L19:
        r8.save();
        int r02 = this.translateSelector;
        if (r02 != (-2)) goto L22;
    L23:
        Consumer<Canvas> r03 = this.selectorTransformer;
        if (r03 == null) goto L26;
        r03.accept(r8);
    L26:
        int r04 = this.translateSelector;
        if (r04 != (-2)) goto L29;
    L30:
        View r05 = this.selectorView;
        if (r05 == null) goto L33;
        r8.translate(r05.getX() - this.selectorRect.left, this.selectorView.getY() - this.selectorRect.top);
        this.selectorDrawable.setAlpha((int) (this.selectorView.getAlpha() * 255.0f));
    L33:
        drawSelector(r8);
        r8.restore();
        return;
    L29:
        if (r04 != this.selectorPosition) goto L33;
    L22:
        if (r02 != this.selectorPosition) goto L26;
    L12:
        if (this.selectorView == null) goto L18;
        if ((getAdapter() instanceof SelectionAdapter) == false) goto L16;
        int r06 = ((SelectionAdapter) getAdapter()).getSelectionBottomPadding(this.selectorView);
    L17:
        this.selectorDrawable.setBounds(this.selectorView.getLeft(), this.selectorView.getTop(), this.selectorView.getRight(), this.selectorView.getBottom() - r06);
        goto L19
    L16:
        r06 = 0;
        goto L17
    L36:
        return;
    }

    private void ensurePinnedHeaderLayout(View r4, boolean r5) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.isLayoutRequested() == true) goto L9;
        if (r5 == true) goto L9;
        return;
    L9:
        int r52 = this.sectionsType;
        if (r52 != 1) goto L17;
        ViewGroup.LayoutParams r3 = r4.getLayoutParams();
        int r53 = View.MeasureSpec.makeMeasureSpec(r3.height, TLObject.FLAG_30);
        r4.measure(View.MeasureSpec.makeMeasureSpec(r3.width, TLObject.FLAG_30), r53);     // Catch: Exception -> L14
    L23:
        r4.layout(0, 0, r4.getMeasuredWidth(), r4.getMeasuredHeight());
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L23
    L17:
        if (r52 != 2) goto L23;
        r4.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));     // Catch: Exception -> L21
    L21:
        e = move-exception;
        FileLog.e(e);
        goto L23
    }

    public static Class[] filterThemeDescription(int r6, Class[] r7) {
        if (ExteraConfig.getSectionsSeparatedHeaders() == true) goto L5;
    L27:
        return r7;
    L5:
        if (r6 != ThemeDescription.FLAG_CELLBACKGROUNDCOLOR) goto L27;
        if (r7 == null) goto L27;
        int r62 = r7.length;
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L9:
        if (r1 >= r62) goto L17;
        Class r4 = r7[r1];
        if (r4 == null) goto L15;
        if (r4.equals(HeaderCell.class) == true) goto L15;
        r2 = r2 + 1;
    L15:
        r1 = r1 + 1;
        goto L9
    L17:
        if (r2 == r7.length) goto L27;
        Class[] r63 = new Class[r2];
        int r12 = r7.length;
        int r22 = 0;
    L19:
        if (r0 >= r12) goto L26;
        Class r42 = r7[r0];
        if (r42 == null) goto L25;
        if (r42.equals(HeaderCell.class) == true) goto L25;
        r63[r22] = r42;
        r22 = r22 + 1;
    L25:
        r0 = r0 + 1;
        goto L19
    L26:
        return r63;
    }

    private int[] getDrawableStateForSelector() {
        int[] r2 = onCreateDrawableState(1);
        r2[r2.length - 1] = 16842919;
        return r2;
    }

    private View getSectionHeaderView(int r4, View r5) {
        if (r5 != null) goto L5;
        boolean r1 = true;
    L6:
        View r42 = this.sectionsAdapter.getSectionHeaderView(r4, r5);
        if (r1 == false) goto L9;
        ensurePinnedHeaderLayout(r42, false);
    L9:
        return r42;
    L5:
        r1 = false;
        goto L6
    }

    private float getSegmentedSectionBottomRadius(View r3, RectF r4) {
        boolean r0 = hasSegmentedSectionNeighbor(r3, -1);
        boolean r1 = hasSegmentedSectionNeighbor(r3, 1);
        if (r0 == true) goto L9;
        if (r1 == true) goto L9;
        if (isRoundSectionView(r3) == false) goto L9;
        return getSingleSectionRadius(r4);
    L9:
        float r2 = this.sectionRadius;
        if (r1 == true) goto L12;
        return r2;
    L12:
        return Math.min(r2, AndroidUtilities.dp(4.0f));
    }

    private float getSegmentedSectionTopRadius(View r3, RectF r4) {
        boolean r0 = hasSegmentedSectionNeighbor(r3, -1);
        boolean r1 = hasSegmentedSectionNeighbor(r3, 1);
        if (r0 == true) goto L9;
        if (r1 == true) goto L9;
        if (isRoundSectionView(r3) == false) goto L9;
        return getSingleSectionRadius(r4);
    L9:
        float r2 = this.sectionRadius;
        if (r0 == true) goto L12;
        return r2;
    L12:
        return Math.min(r2, AndroidUtilities.dp(4.0f));
    }

    private float getSingleSectionRadius(RectF r1) {
        return r1.height() / 2.0f;
    }

    private boolean hasAbove(View r2, int r3) {
        if (r2 == null) goto L16;
        if (r3 > 0) goto L16;
        if (getAdapter() == null) goto L16;
        if (this.isViewTypeSection == null) goto L16;
        int r22 = getChildAdapterPosition(r2);
        if (r22 == (-1)) goto L16;
        if (r22 == 0) goto L16;
        return this.isViewTypeSection.run(Integer.valueOf(getAdapter().getItemViewType(r22 - 1))).booleanValue();
    L16:
        return false;
    }

    private boolean hasBelow(View r3, int r4) {
        if (r3 != null) goto L5;
    L18:
        return false;
    L5:
        if (r4 < (getChildCount() - 1)) goto L18;
        if (getAdapter() == null) goto L18;
        if (this.isViewTypeSection == null) goto L18;
        int r32 = getChildAdapterPosition(r3);
        if (r32 == (-1)) goto L18;
        if (r32 == (getAdapter().getItemCount() - 1)) goto L18;
        return this.isViewTypeSection.run(Integer.valueOf(getAdapter().getItemViewType(r32 + 1))).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean hasClickableChild(ViewGroup r8, float r9, float r10) {
        int r0 = r8.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L29;
        View r2 = r8.getChildAt(r0);
        if (r2.getVisibility() != 0) goto L28;
        float r3 = r9 - r2.getLeft();
        float r4 = r10 - r2.getTop();
        if (r3 < 0.0f) goto L28;
        if (r3 > r2.getWidth()) goto L28;
        if (r4 < 0.0f) goto L28;
        if (r4 > r2.getHeight()) goto L28;
        if ((r2 instanceof HitTestable) == false) goto L21;
        if (((HitTestable) r2).hasClickableNodeAt(r3, r4) == true) goto L19;
    L24:
        if ((r2 instanceof ViewGroup) == false) goto L28;
        if (hasClickableChild((ViewGroup) r2, r3, r4) == false) goto L28;
        return true;
    L19:
        return true;
    L21:
        if (r2.isClickable() == false) goto L24;
        return true;
    L28:
        r0 = r0 - 1;
        goto L3
    L29:
        return false;
    }

    private boolean hasSectionChildAt(View r6, int r7, boolean r8) {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L29;
        if (r1 == r7) goto L28;
        View r2 = getChildAt(r1);
        if (r2 == this.draggingChild) goto L28;
        if (r2 == this.emptyView) goto L28;
        if (r2.getVisibility() != 0) goto L28;
        if (r2.getAlpha() <= 0.0f) goto L28;
        if (this.sectionsItemDecoration.isSectionItem.run(r2).booleanValue() == false) goto L28;
        RecyclerView.ViewHolder r3 = getChildViewHolder(r2);
        if (r3 == null) goto L28;
        if (r3.isRemoved() != r8) goto L28;
        if (Math.abs(r2.getY() - r6.getY()) >= AndroidUtilities.dp(2.0f)) goto L28;
        return true;
    L28:
        r1 = r1 + 1;
        goto L4
    L29:
        return false;
    }

    private boolean hasSegmentedSectionNeighbor(View r3, int r4) {
        if (r3 != null) goto L5;
    L19:
        return false;
    L5:
        if (getAdapter() == null) goto L19;
        if (this.isViewTypeSection == null) goto L19;
        int r32 = getChildAdapterPosition(r3);
        if (r32 != (-1)) goto L12;
        return false;
    L12:
        int r33 = r32 + r4;
        if (r33 < 0) goto L19;
        if (r33 >= getAdapter().getItemCount()) goto L19;
        return this.isViewTypeSection.run(Integer.valueOf(getAdapter().getItemViewType(r33))).booleanValue();
    }

    private void highlightRowInternal(IntReturnCallback r10, int r11, boolean r12) {
        Runnable r0 = this.removeHighlighSelectionRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.removeHighlighSelectionRunnable = null;
    L5:
        RecyclerView.ViewHolder r02 = findViewHolderForAdapterPosition(r10.run());
        if (r02 == null) goto L30;
        int r3 = r02.getLayoutPosition();
        this.highlightPosition = r3;
        positionSelector(r3, r02.itemView, false, -1.0f, -1.0f, true);
        Drawable r9 = this.selectorDrawable;
        if (r9 == null) goto L19;
        Drawable r92 = r9.getCurrent();
        if ((r92 instanceof TransitionDrawable) == true) goto L12;
    L18:
        this.selectorDrawable.setHotspot(r02.itemView.getMeasuredWidth() / 2, r02.itemView.getMeasuredHeight() / 2);
        goto L19
    L12:
        if (this.onItemLongClickListener == null) goto L14;
    L17:
        ((TransitionDrawable) r92).startTransition(ViewConfiguration.getLongPressTimeout());
        goto L18
    L14:
        if (this.onItemClickListenerExtended != null) goto L17;
        ((TransitionDrawable) r92).resetTransition();
    L19:
        Drawable r93 = this.selectorDrawable;
        if (r93 != null) goto L22;
    L26:
        if (r11 <= 0) goto L33;
        this.pendingHighlightPosition = null;
        RecyclerListView$$ExternalSyntheticLambda8 r94 = new RecyclerListView$$ExternalSyntheticLambda8(this);
        this.removeHighlighSelectionRunnable = r94;
        AndroidUtilities.runOnUIThread(r94, r11);
        return;
    L33:
        return;
    L22:
        if (r93.isStateful() == false) goto L26;
        if (this.selectorDrawable.setState(getDrawableStateForSelector()) == false) goto L26;
        invalidateDrawable(this.selectorDrawable);
        goto L26
    L30:
        if (r12 == false) goto L34;
        this.pendingHighlightPosition = r10;
        return;
    }

    private void invalidateSegmentedSectionOffsets() {
        if (this.sectionsItemDecoration != null) goto L5;
        return;
    L5:
        if (useSegmentedSections() == true) goto L7;
        return;
    L7:
        if (isComputingLayout() == true) goto L13;
        invalidateItemDecorations();
        return;
    }

    public static boolean isRoundSectionView(View r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (Boolean.TRUE.equals(r2.getTag(TAG_ROUND_SECTION)) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$drawSectionsBackgrounds$6(Canvas r5, Float r6, Float r7, Float r8, Float r9, Float r10) {
        RectF r62 = AndroidUtilities.rectTmp;
        r62.set(ListSectionsDecoration.m12487$$Nest$fgetpadding(this.sectionsItemDecoration), r6.floatValue(), getWidth() - ListSectionsDecoration.m12487$$Nest$fgetpadding(this.sectionsItemDecoration), r7.floatValue());
        this.drawSectionBackground.run(r5, r62, r8, r9, r10);
    }

    private /* synthetic */ void lambda$drawSectionsBackgrounds$7(Canvas r5, Float r6, Float r7, Float r8, Float r9, Float r10) {
        RectF r62 = AndroidUtilities.rectTmp;
        r62.set(getPaddingLeft() + ListSectionsDecoration.m12487$$Nest$fgetpadding(this.sectionsItemDecoration), r6.floatValue(), (getWidth() - ListSectionsDecoration.m12487$$Nest$fgetpadding(this.sectionsItemDecoration)) - getPaddingRight(), r7.floatValue());
        this.drawSectionBackground.run(r5, r62, r8, r9, r10);
    }

    private /* synthetic */ void lambda$highlightRowInternal$1() {
        this.removeHighlighSelectionRunnable = null;
        this.pendingHighlightPosition = null;
        Drawable r0 = this.selectorDrawable;
        if (r0 == null) goto L7;
        Drawable r02 = r0.getCurrent();
        if ((r02 instanceof TransitionDrawable) == false) goto L7;
        ((TransitionDrawable) r02).resetTransition();
    L7:
        Drawable r03 = this.selectorDrawable;
        if (r03 != null) goto L10;
        return;
    L10:
        if (r03.isStateful() == false) goto L14;
        this.selectorDrawable.setState(StateSet.NOTHING);
        return;
    }

    private /* synthetic */ void lambda$onLayout$0() {
        getAdapter().notifyDataSetChanged();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private void positionSelector(int r8, View r9) {
        positionSelector(r8, r9, false, -1.0f, -1.0f, false);
    }

    private void positionSelector(int r9, View r10, boolean r11, float r12, float r13, boolean r14) {
        Runnable r142 = this.removeHighlighSelectionRunnable;
        if (r142 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r142);
        this.removeHighlighSelectionRunnable = null;
        this.pendingHighlightPosition = null;
    L6:
        if (this.selectorDrawable != null) goto L9;
        return;
    L9:
        if (r9 == this.selectorPosition) goto L11;
        boolean r143 = true;
    L13:
        if ((getAdapter() instanceof SelectionAdapter) == false) goto L15;
        int r2 = ((SelectionAdapter) getAdapter()).getSelectionBottomPadding(r10);
    L17:
        if (r9 == (-1)) goto L19;
        this.selectorPosition = r9;
    L19:
        this.selectorView = r10;
        if (hasSections() == false) goto L39;
        ListSectionsDecoration r4 = this.sectionsItemDecoration;
        if (r4 == null) goto L39;
        if (r9 == (-1)) goto L39;
        if (r4.isSectionItem.run(r10).booleanValue() == false) goto L39;
        View r3 = findViewByPosition(r9 - 1);
        View r42 = findViewByPosition(r9 + 1);
        this.selectorIsSection = true;
        if (r3 != null) goto L29;
    L31:
        boolean r32 = false;
    L32:
        this.selectorSectionHasPrev = r32;
        if (r42 != null) goto L35;
    L37:
        boolean r33 = false;
    L38:
        this.selectorSectionHasNext = r33;
    L41:
        if (this.selectorType != 8) goto L44;
        Theme.setMaskDrawableRad(this.selectorDrawable, this.selectorRadius, 0);
    L56:
        this.selectorRect.set(r10.getLeft(), r10.getTop(), r10.getRight(), r10.getBottom() - r2);
        boolean r102 = r10.isEnabled();
        if (this.isChildViewEnabled == r102) goto L59;
        this.isChildViewEnabled = r102;
    L59:
        if (r143 == false) goto L61;
        this.selectorDrawable.setVisible(false, false);
        this.selectorDrawable.setState(StateSet.NOTHING);
    L61:
        setListSelectorColor(getSelectorColor(r9));
        this.selectorDrawable.setBounds(this.selectorRect);
        if (r143 == true) goto L64;
    L66:
        if (r11 == false) goto L69;
        this.selectorDrawable.setHotspot(r12, r13);
        return;
    L69:
        return;
    L64:
        if (getVisibility() != 0) goto L66;
        this.selectorDrawable.setVisible(true, false);
        goto L66
    L44:
        if (this.topBottomSelectorRadius <= 0) goto L56;
        if (getAdapter() == null) goto L56;
        Drawable r34 = this.selectorDrawable;
        if (r9 != 0) goto L50;
        int r43 = this.topBottomSelectorRadius;
    L52:
        if (r9 != (getAdapter().getItemCount() - 2)) goto L54;
        int r5 = this.topBottomSelectorRadius;
    L55:
        Theme.setMaskDrawableRad(r34, r43, r5);
        goto L56
    L54:
        r5 = 0;
        goto L55
    L50:
        r43 = 0;
        goto L52
    L35:
        if (this.sectionsItemDecoration.isSectionItem.run(r42).booleanValue() == false) goto L37;
        r33 = true;
        goto L38
    L29:
        if (this.sectionsItemDecoration.isSectionItem.run(r3).booleanValue() == false) goto L31;
        r32 = true;
    L39:
        this.selectorIsSection = false;
        goto L41
    L15:
        r2 = 0;
        goto L17
    L11:
        r143 = false;
        goto L13
    }

    private void removeSelection(View r2, MotionEvent r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (this.selectorRect.isEmpty() == false) goto L7;
        return;
    L7:
        if (r2.isEnabled() == false) goto L15;
        positionSelector(this.currentChildPosition, r2);
        Drawable r22 = this.selectorDrawable;
        if (r22 == null) goto L16;
        Drawable r23 = r22.getCurrent();
        if ((r23 instanceof TransitionDrawable) == false) goto L13;
        ((TransitionDrawable) r23).resetTransition();
    L13:
        if (r3 == null) goto L16;
        this.selectorDrawable.setHotspot(r3.getX(), r3.getY());
    L16:
        updateSelectorState();
        return;
    L15:
        this.selectorRect.setEmpty();
        goto L16
    }

    private void requestDisallowInterceptTouchEvent(View r1, boolean r2) {
        if (r1 == null) goto L12;
        ViewParent r12 = r1.getParent();
        if (r12 == null) goto L13;
        r12.requestDisallowInterceptTouchEvent(r2);
        ViewParent r0 = getTouchParent();
        if (r0 != null) goto L10;
        return;
    L10:
        r0.requestDisallowInterceptTouchEvent(r2);
        return;
    L13:
        return;
    }

    private boolean setSegmentedSectionRect(View r8, RectF r9) {
        if (r8 != null) goto L5;
    L23:
        return false;
    L5:
        if (this.sectionsItemDecoration == null) goto L23;
        if ((r8 instanceof JoinToSendSettingsView) == false) goto L10;
        float r1 = ((JoinToSendSettingsView) r8).getBottomInfoMargin();
    L11:
        float r2 = r8.getX();
        if (this.applyPaddingToSections == false) goto L14;
        float r3 = getPaddingTop();
    L15:
        float r32 = Math.max(r3, top(r8));
        float r4 = r8.getX() + r8.getWidth();
        float r5 = getHeight();
        if (this.applyPaddingToSections == false) goto L18;
        float r7 = getPaddingBottom();
    L19:
        r9.set(r2, r32, r4, Math.min(r5 - r7, bottom(r8) - r1));
        if (r9.bottom < r9.top) goto L23;
        return true;
    L18:
        r7 = -this.sectionRadius;
        goto L19
    L14:
        r3 = -this.sectionRadius;
        goto L15
    L10:
        r1 = 0.0f;
        goto L11
    }

    private void startMultiselectScroll(boolean r1) {
        this.multiselectScrollToTop = r1;
        if (this.multiselectScrollRunning == true) goto L6;
        this.multiselectScrollRunning = true;
        AndroidUtilities.cancelRunOnUIThread(this.scroller);
        AndroidUtilities.runOnUIThread(this.scroller);
        return;
    }

    public static float top(View r1) {
        if (r1.getTag(R.id.dragging) == null) goto L7;
        return r1.getTop();
    L7:
        return r1.getY();
    }

    private void updateSelectorState() {
        Drawable r0 = this.selectorDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isStateful() == true) goto L7;
        return;
    L7:
        if (this.currentChildView == null) goto L13;
        if (this.selectorDrawable.setState(getDrawableStateForSelector()) == false) goto L18;
        invalidateDrawable(this.selectorDrawable);
        return;
    L18:
        return;
    L13:
        if (this.removeHighlighSelectionRunnable != null) goto L19;
        this.selectorDrawable.setState(StateSet.NOTHING);
        return;
    }

    private boolean useSegmentedSections() {
        if (this.segmentedSectionsEnabled == true) goto L5;
        return false;
    L5:
        if (ExteraConfig.getDividerStyle() != DividerStyle.SEGMENTS) goto L10;
        return true;
    L10:
        return false;
    }

    public void addEdgeEffectListener(Runnable r2) {
        addEdgeEffectListener(new RecyclerListView$$ExternalSyntheticLambda7(r2));
    }

    public void addEdgeEffectListener(EdgeEffectTrackerFactory.OnEdgeEffectListener r1) {
        this.edgeEffectTrackerFactory.addEdgeEffectListener(r1);
    }

    public void addOverlayView(View r3, FrameLayout.LayoutParams r4) {
        if (this.overlayContainer != null) goto L5;
        this.overlayContainer = new AnonymousClass5(this, getContext());
    L5:
        this.overlayContainer.addView(r3, r4);
    }

    public boolean allowSelectChildAtPosition(float r1, float r2) {
        return true;
    }

    public boolean allowSelectChildAtPosition(View r1) {
        return true;
    }

    @Override // android.view.View
    public ViewPropertyAnimator animate() {
        return super.animate();
    }

    public boolean canHighlightChildAt(View r1, float r2, float r3) {
        return true;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int r2) {
        if (this.scrollEnabled == true) goto L5;
        return false;
    L5:
        if (super.canScrollVertically(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void cancelClickRunnables(boolean r4) {
        Runnable r0 = this.selectChildRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.selectChildRunnable = null;
    L5:
        View r02 = this.currentChildView;
        if (r02 == null) goto L10;
        if (r4 == false) goto L9;
        onChildPressed(r02, 0.0f, 0.0f, false);
    L9:
        this.currentChildView = null;
        removeSelection(r02, null);
    L10:
        this.selectorRect.setEmpty();
        Runnable r42 = this.clickRunnable;
        if (r42 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r42);
        this.clickRunnable = null;
    L13:
        this.interceptedByChild = false;
    }

    public void capture(Canvas r5, RectF r6) {
        long r0 = SystemClock.uptimeMillis();
        if (hasActiveEdgeEffects() == true) goto L5;
    L19:
        captureChildren(r5, r6, r0);
        return;
    L5:
        if (getOverScrollMode() == 2) goto L19;
        if (this.selfTransformationsMatrix != null) goto L9;
        this.selfTransformationsMatrix = new Matrix();
    L9:
        r5.save();
        if (getMatrix().invert(this.selfTransformationsMatrix) == false) goto L12;
        r5.concat(this.selfTransformationsMatrix);
    L12:
        r5.translate(-getX(), -getY());
        super.drawChild(r5, this, r0);     // Catch: Throwable -> L15
    L17:
        r5.restore();
        boolean r4 = BuildVars.DEBUG_VERSION;
        return;
    L15:
        th = move-exception;
        FileLog.e(th);
        goto L17
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public void captureCalculateHash(IBlur3Hash r8, RectF r9) {
        if (Build.VERSION.SDK_INT >= 29) goto L7;
        r8.unsupported();
        return;
    L7:
        if (hasActiveEdgeEffects() == true) goto L9;
    L12:
        int r0 = getItemDecorationCount();
        int r1 = 0;
        int r2 = 0;
    L13:
        if (r2 >= r0) goto L23;
        Object r3 = getItemDecorationAt(r2);
        if ((r3 instanceof IBlur3Capture) == false) goto L22;
        if (r3 == this.sectionsItemDecoration) goto L19;
    L21:
        ((IBlur3Capture) r3).captureCalculateHash(r8, r9);
        goto L22
    L19:
        if (this.canCaptureSectionsDecorator == true) goto L21;
    L22:
        r2 = r2 + 1;
        goto L13
    L23:
        int r02 = getChildCount();
    L24:
        if (r1 >= r02) goto L30;
        View r22 = getChildAt(r1);
        float r32 = r22.getX();
        float r4 = r22.getY();
        if (r9.intersects(r32, r4, r22.getWidth() + r32, r22.getHeight() + r4) == false) goto L29;
        r8.add(r22);
    L29:
        r1 = r1 + 1;
        goto L24
    L30:
        return;
    L9:
        if (getOverScrollMode() == 2) goto L12;
        r8.unsupported();
    }

    public void captureChildren(Canvas r9, RectF r10, long r11) {
        int r0 = getItemDecorationCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        Object r3 = getItemDecorationAt(r2);
        if ((r3 instanceof IBlur3Capture) == false) goto L12;
        if (r3 == this.sectionsItemDecoration) goto L9;
    L11:
        ((IBlur3Capture) r3).capture(r9, r10);
        goto L12
    L9:
        if (this.canCaptureSectionsDecorator == true) goto L11;
    L12:
        r2 = r2 + 1;
        goto L3
    L13:
        int r02 = getChildCount();
        int r22 = 0;
    L14:
        if (r22 >= r02) goto L20;
        View r32 = getChildAt(r22);
        float r4 = r32.getX();
        float r5 = r32.getY();
        if (r10.intersects(r4, r5, r32.getWidth() + r4, r32.getHeight() + r5) == false) goto L19;
        this.ignoreClipChild = true;
        drawChild(r9, r32, r11);
        this.ignoreClipChild = false;
    L19:
        r22 = r22 + 1;
        goto L14
    }

    public void checkIfEmpty() {
        checkIfEmpty(updateEmptyViewAnimated());
    }

    public void checkSection(boolean r18) {
        if (this.scrollingByUser == true) goto L6;
        if (r18 == true) goto L6;
    L8:
        if (this.sectionsType != 0) goto L10;
        return;
    L10:
        if (this.sectionsAdapter == null) goto L176;
    L11:
        RecyclerView.LayoutManager r1 = getLayoutManager();
        if ((r1 instanceof LinearLayoutManager) == false) goto L177;
        LinearLayoutManager r12 = (LinearLayoutManager) r1;
        if (r12.getOrientation() == 1) goto L16;
        return;
    L16:
        if (this.sectionsAdapter != null) goto L18;
        int r2 = r12.findFirstVisibleItemPosition();
        Math.abs(r12.findLastVisibleItemPosition() - r2);
        if (r2 != (-1)) goto L153;
        return;
    L153:
        if (this.scrollingByUser == true) goto L155;
        if (r18 == true) goto L155;
        return;
    L155:
        FastScroll r13 = this.fastScroll;
        if (r13 != null) goto L158;
        return;
    L158:
        if (r13.isPressed() == true) goto L183;
        RecyclerView.Adapter r14 = getAdapter();
        if ((r14 instanceof FastScrollAdapter) == false) goto L184;
        FastScrollAdapter r15 = (FastScrollAdapter) r14;
        float r22 = r15.getScrollProgress(this);
        this.fastScroll.setIsVisible(r15.fastScrollIsVisible(this));
        this.fastScroll.setProgress(Math.min(1.0f, r22));
        FastScroll.m12485$$Nest$mgetCurrentLetter(this.fastScroll, false);
        return;
    L184:
        return;
    L183:
        return;
    L18:
        if (this.sectionsType != 1) goto L20;
        int r23 = 0;
    L21:
        int r6 = this.sectionsType;
        float r7 = 32.0f;
        int r10 = Integer.MAX_VALUE;
        if (r6 == 1) goto L81;
        if (r6 == 3) goto L81;
        if (r6 != 2) goto L179;
        this.pinnedHeaderShadowTargetAlpha = 0.0f;
        if (this.sectionsAdapter.getItemCount() == 0) goto L185;
        int r16 = getChildCount();
        int r11 = 0;
        int r122 = 0;
        int r8 = Integer.MAX_VALUE;
        View r62 = null;
        View r9 = null;
    L31:
        if (r11 >= r16) goto L43;
        View r132 = getChildAt(r11);
        int r142 = r132.getBottom();
        if (r142 <= (this.sectionOffset + r23)) goto L42;
        if (r142 >= r10) goto L37;
        r9 = r132;
        r10 = r142;
    L37:
        r122 = Math.max(r122, r142);
        if (r142 < ((this.sectionOffset + r23) + AndroidUtilities.dp(32.0f))) goto L42;
        if (r142 >= r8) goto L42;
        r62 = r132;
        r8 = r142;
    L42:
        r11 = r11 + 1;
        goto L31
    L43:
        if (r9 == null) goto L186;
        RecyclerView.ViewHolder r17 = getChildViewHolder(r9);
        if (r17 == null) goto L187;
        int r19 = r17.getAdapterPosition();
        int r72 = this.sectionsAdapter.getSectionForPosition(r19);
        if (r72 >= 0) goto L52;
        return;
    L52:
        if (this.currentFirst == r72) goto L54;
    L55:
        View r82 = getSectionHeaderView(r72, this.pinnedHeader);
        this.pinnedHeader = r82;
        r82.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0));
        View r83 = this.pinnedHeader;
        r83.layout(0, 0, r83.getMeasuredWidth(), this.pinnedHeader.getMeasuredHeight());
        this.currentFirst = r72;
    L57:
        if (this.pinnedHeader == null) goto L62;
        if (r62 == null) goto L62;
        if (r62.getClass() == this.pinnedHeader.getClass()) goto L62;
        this.pinnedHeaderShadowTargetAlpha = 1.0f;
    L62:
        int r4 = this.sectionsAdapter.getCountForSection(r72);
        int r110 = this.sectionsAdapter.getPositionInSectionForPosition(r19);
        if (r122 != 0) goto L65;
    L67:
        int r5 = this.sectionOffset;
    L68:
        int r42 = r4 - 1;
        View r3 = this.pinnedHeader;
        if (r110 != r42) goto L78;
        int r111 = r3.getHeight();
        int r32 = ((r9.getTop() - r23) - this.sectionOffset) + r9.getHeight();
        if (r32 >= r111) goto L73;
        int r33 = r32 - r111;
    L74:
        View r112 = this.pinnedHeader;
        if (r33 >= 0) goto L77;
        r112.setTag(Integer.valueOf((r23 + r5) + r33));
    L79:
        invalidate();
        return;
    L77:
        r112.setTag(Integer.valueOf(r23 + r5));
        goto L79
    L73:
        r33 = r23;
        goto L74
    L78:
        r3.setTag(Integer.valueOf(r23 + r5));
        goto L79
    L65:
        if (r122 >= (getMeasuredHeight() - getPaddingBottom())) goto L67;
        r5 = -r23;
        goto L68
    L54:
        if (this.pinnedHeader != null) goto L57;
    L187:
        return;
    L186:
        return;
    L185:
        return;
    L179:
        return;
    L81:
        int r63 = getChildCount();
        int r123 = 0;
        int r143 = 0;
        int r113 = Integer.MAX_VALUE;
        View r133 = null;
    L82:
        if (r123 >= r63) goto L94;
        View r152 = getChildAt(r123);
        float r162 = r7;
        int r73 = r152.getBottom();
        if (r73 <= (this.sectionOffset + r23)) goto L93;
        if (r73 >= r10) goto L88;
        r10 = r73;
        r133 = r152;
    L88:
        r143 = Math.max(r143, r73);
        if (r73 < ((this.sectionOffset + r23) + AndroidUtilities.dp(r162))) goto L93;
        if (r73 >= r113) goto L93;
        r113 = r73;
    L93:
        r123 = r123 + 1;
        r7 = r162;
        goto L82
    L94:
        if (r133 == null) goto L189;
        RecyclerView.ViewHolder r64 = getChildViewHolder(r133);
        if (r64 == null) goto L190;
        int r65 = r64.getAdapterPosition();
        int r114 = Math.abs(r12.findLastVisibleItemPosition() - r65) + 1;
        if (this.scrollingByUser == true) goto L102;
        if (r18 == true) goto L102;
    L109:
        this.headersCache.addAll(this.headers);
        this.headers.clear();
        if (this.sectionsAdapter.getItemCount() != 0) goto L113;
        return;
    L113:
        if (this.currentFirst == r65) goto L115;
    L116:
        this.currentFirst = r65;
        this.currentVisible = r114;
        this.sectionsCount = 1;
        int r43 = this.sectionsAdapter.getSectionForPosition(r65);
        this.startSection = r43;
        int r44 = (this.sectionsAdapter.getCountForSection(r43) + r65) - this.sectionsAdapter.getPositionInSectionForPosition(r65);
    L118:
        if (r44 >= (r65 + r114)) goto L121;
        r44 = r44 + this.sectionsAdapter.getCountForSection(this.startSection + this.sectionsCount);
        this.sectionsCount++;
    L121:
        if (this.sectionsType == 3) goto L180;
        int r115 = this.startSection;
        int r34 = r65;
    L124:
        if (r115 >= (this.startSection + this.sectionsCount)) goto L193;
        if (this.headersCache.isEmpty() == true) goto L128;
        View r45 = this.headersCache.get(0);
        this.headersCache.remove(0);
    L129:
        View r46 = getSectionHeaderView(r115, r45);
        this.headers.add(r46);
        int r74 = this.sectionsAdapter.getCountForSection(r115);
        if (r115 != this.startSection) goto L144;
        int r84 = this.sectionsAdapter.getPositionInSectionForPosition(r34);
        if (r84 != (r74 - 1)) goto L135;
        r46.setTag(Integer.valueOf((-r46.getHeight()) + r23));
    L142:
        r74 = r74 - this.sectionsAdapter.getPositionInSectionForPosition(r65);
    L143:
        r34 = r34 + r74;
        r115 = r115 + 1;
        goto L124
    L135:
        if (r84 != (r74 - 2)) goto L141;
        View r85 = getChildAt(r34 - r65);
        if (r85 == null) goto L139;
        int r86 = r85.getTop() + r23;
    L140:
        r46.setTag(Integer.valueOf(Math.min(r86, 0)));
        goto L142
    L139:
        r86 = -AndroidUtilities.dp(100.0f);
        goto L140
    L141:
        r46.setTag(0);
        goto L142
    L144:
        View r87 = getChildAt(r34 - r65);
        if (r87 == null) goto L147;
        r46.setTag(Integer.valueOf(r87.getTop() + r23));
        goto L143
    L147:
        r46.setTag(Integer.valueOf(-AndroidUtilities.dp(100.0f)));
        goto L143
    L128:
        r45 = null;
        goto L129
    L193:
        return;
    L180:
        return;
    L115:
        if (this.currentVisible == r114) goto L121;
    L102:
        FastScroll r75 = this.fastScroll;
        if (r75 == null) goto L109;
        if (r75.isPressed() == true) goto L109;
        if ((getAdapter() instanceof FastScrollAdapter) == false) goto L109;
        this.fastScroll.setProgress(Math.min(1.0f, r65 / ((this.sectionsAdapter.getTotalItemsCount() - r114) + 1)));
        goto L109
    L190:
        return;
    L189:
        return;
    L20:
        r23 = getPaddingTop();
        goto L21
    L177:
        return;
    L176:
        return;
    L6:
        if (this.fastScroll != null) goto L11;
        goto L11
    }

    public void clickItem(View r2, int r3) {
        OnItemClickListener r0 = this.onItemClickListener;
        if (r0 == null) goto L6;
        r0.onItemClick(r2, r3);
        return;
    L6:
        OnItemClickListenerExtended r1 = this.onItemClickListenerExtended;
        if (r1 == null) goto L10;
        r1.onItemClick(r2, r3, 0.0f, 0.0f);
        return;
    }

    public void disableSections() {
        setSelectorDrawableColor(getThemedColor(Theme.key_listSelector));
        this.isViewTypeSection = null;
        this.sectionRadius = 0.0f;
        this.sectionRadiusTop = null;
        this.sectionRadiusBottom = null;
        this.drawSectionBackground = null;
        ListSectionsDecoration r1 = this.sectionsItemDecoration;
        if (r1 == null) goto L6;
        removeItemDecoration(r1);
        this.sectionsItemDecoration = null;
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        RecyclerItemsEnterAnimator r0 = this.itemsEnterAnimator;
        if (r0 == null) goto L6;
        r0.dispatchDraw();
    L6:
        if (this.drawSelection == true) goto L8;
    L10:
        super.dispatchDraw(r9);
        if (this.drawSelection == true) goto L13;
    L15:
        FrameLayout r02 = this.overlayContainer;
        if (r02 == null) goto L19;
        r02.draw(r9);
    L19:
        if (this.skipDrawSection == true) goto L71;
        int r03 = this.sectionsType;
        float r2 = 0.0f;
        if (r03 != 1) goto L36;
        if (this.sectionsAdapter != null) goto L26;
        return;
    L26:
        if (this.headers.isEmpty() == true) goto L66;
        int r04 = 0;
    L29:
        if (r04 >= this.headers.size()) goto L72;
        View r1 = this.headers.get(r04);
        int r4 = r9.save();
        int r5 = ((Integer) r1.getTag()).intValue();
        if (LocaleController.isRTL == false) goto L33;
        float r6 = getWidth() - r1.getWidth();
    L34:
        r9.translate(r6, r5);
        r9.clipRect(0, 0, getWidth(), r1.getMeasuredHeight());
        r1.draw(r9);
        r9.restoreToCount(r4);
        r04 = r04 + 1;
        goto L29
    L33:
        r6 = 0.0f;
        goto L34
    L72:
        return;
    L66:
        return;
    L36:
        if (r03 == 2) goto L38;
        return;
    L38:
        if (this.sectionsAdapter == null) goto L68;
        View r05 = this.pinnedHeader;
        if (r05 != null) goto L42;
        return;
    L42:
        if (r05.getAlpha() == 0.0f) goto L70;
        int r06 = r9.save();
        int r12 = ((Integer) this.pinnedHeader.getTag()).intValue();
        if (LocaleController.isRTL == false) goto L46;
        r2 = getWidth() - this.pinnedHeader.getWidth();
    L46:
        r9.translate(r2, r12);
        Drawable r13 = this.pinnedHeaderShadowDrawable;
        if (r13 == null) goto L60;
        r13.setBounds(0, this.pinnedHeader.getMeasuredHeight(), getWidth(), this.pinnedHeader.getMeasuredHeight() + this.pinnedHeaderShadowDrawable.getIntrinsicHeight());
        this.pinnedHeaderShadowDrawable.setAlpha((int) (this.pinnedHeaderShadowAlpha * 255.0f));
        this.pinnedHeaderShadowDrawable.draw(r9);
        long r14 = SystemClock.elapsedRealtime();
        long r42 = Math.min(20, r14 - this.lastAlphaAnimationTime);
        this.lastAlphaAnimationTime = r14;
        float r15 = this.pinnedHeaderShadowAlpha;
        float r22 = this.pinnedHeaderShadowTargetAlpha;
        if (r15 >= r22) goto L55;
        float r16 = r15 + (r42 / 180.0f);
        this.pinnedHeaderShadowAlpha = r16;
        if (r16 <= r22) goto L53;
        this.pinnedHeaderShadowAlpha = r22;
    L53:
        invalidate();
        goto L60
    L55:
        if (r15 <= r22) goto L60;
        float r17 = r15 - (r42 / 180.0f);
        this.pinnedHeaderShadowAlpha = r17;
        if (r17 >= r22) goto L59;
        this.pinnedHeaderShadowAlpha = r22;
    L59:
        invalidate();
    L60:
        r9.clipRect(0, 0, getWidth(), this.pinnedHeader.getMeasuredHeight());
        this.pinnedHeader.draw(r9);
        r9.restoreToCount(r06);
        return;
    L70:
        return;
    L68:
        return;
    L71:
        return;
    L13:
        if (this.drawSelectorBehind == true) goto L15;
        drawSelectors2(r9);
        goto L15
    L8:
        if (this.drawSelectorBehind == false) goto L10;
        drawSelectors2(r9);
        goto L10
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean dispatchNestedPreScroll(int r7, int r8, int[] r9, int[] r10, int r11) {
        if (this.longPressCalled == false) goto L10;
        OnItemLongClickListenerExtended r6 = this.onItemLongClickListenerExtended;
        if (r6 == null) goto L7;
        r6.onMove(r7, r8);
    L7:
        r9[0] = r7;
        r9[1] = r8;
        return true;
    L10:
        return super.dispatchNestedPreScroll(r7, r8, r9, r10, r11);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r5) {
        int r0 = r5.getAction();
        if (r0 == 0) goto L5;
        if (r0 == 1) goto L12;
        if (r0 == 3) goto L12;
    L17:
        FastScroll r02 = getFastScroll();
        if (r02 == null) goto L29;
        if (r02.isVisible == false) goto L29;
        if (r02.isMoving == false) goto L29;
        if (r5.getActionMasked() == 1) goto L29;
        if (r5.getActionMasked() == 3) goto L29;
        return true;
    L29:
        if (this.sectionsAdapter == null) goto L38;
        View r03 = this.pinnedHeader;
        if (r03 == null) goto L38;
        if (r03.getAlpha() == 0.0f) goto L38;
        if (this.pinnedHeader.dispatchTouchEvent(r5) == false) goto L38;
        return true;
    L38:
        return super.dispatchTouchEvent(r5);
    L12:
        int r04 = this.activeTouches - 1;
        this.activeTouches = r04;
        if (r04 != 0) goto L17;
        if (this.adaptiveOverScroll == false) goto L17;
        setOverScrollMode(2);
        goto L17
    L5:
        if (this.activeTouches == 0) goto L7;
    L9:
        this.activeTouches++;
        goto L17
    L7:
        if (this.adaptiveOverScroll == false) goto L9;
        setOverScrollMode(0);
        goto L9
    }

    public void drawBackgroundRect(Canvas r2, RectF r3, float r4, float r5, float r6) {
        drawBackgroundRect(r2, r3, r4, r5, r6, this.resourcesProvider);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        if (hasSections() == false) goto L11;
        if (this.ignoreClipChild == true) goto L11;
        if (r3 == this.draggingChild) goto L11;
        r2.save();
        clipChild(r2, r3);
        boolean r1 = super.drawChild(r2, r3, r4);
        r2.restore();
        return r1;
    L11:
        return super.drawChild(r2, r3, r4);
    }

    public void drawSectionBackground(Canvas r8, int r9, int r10, int r11) {
        drawSectionBackground(r8, r9, r10, r11, 0, 0);
    }

    public void drawSectionBackground(Canvas r10, int r11, int r12, int r13, int r14, int r15) {
        if (r12 < r11) goto L38;
        if (r11 < 0) goto L39;
        if (r12 < 0) goto L41;
        int r0 = 0;
        int r3 = Integer.MIN_VALUE;
        int r2 = Integer.MAX_VALUE;
        boolean r1 = false;
    L8:
        if (r0 >= getChildCount()) goto L20;
        View r4 = getChildAt(r0);
        if (r4 == null) goto L19;
        int r5 = getChildAdapterPosition(r4);
        int r6 = r4.getTop();
        if (r5 < r11) goto L19;
        if (r5 > r12) goto L19;
        r2 = Math.min(r6, r2);
        r3 = Math.max((int) (r6 + (r4.getHeight() * r4.getAlpha())), r3);
        if (r5 != r11) goto L19;
        if (r5 != r12) goto L19;
        r1 = isRoundSectionView(r4);
    L19:
        r0 = r0 + 1;
        goto L8
    L20:
        if (r2 < r3) goto L22;
        return;
    L22:
        if (this.backgroundPaint != null) goto L24;
        this.backgroundPaint = new Paint(1);
    L24:
        this.backgroundPaint.setColor(r13);
        if (r11 != r12) goto L29;
        if (r1 == false) goto L29;
        RectF r112 = AndroidUtilities.rectTmp;
        r112.set(0.0f, r2 - r14, getWidth(), r3 + r15);
        float r122 = getSingleSectionRadius(r112);
        r10.drawRoundRect(r112, r122, r122, this.backgroundPaint);
        return;
    L29:
        r10.drawRect(0.0f, r2 - r14, getWidth(), r3 + r15, this.backgroundPaint);
        return;
    L41:
        return;
    L39:
        return;
    }

    public void drawSectionsBackgrounds(Canvas r14) {
        if (this.drawSectionBackground != null) goto L6;
        return;
    L6:
        if (isAnimating() == false) goto L57;
        if (this.sections != null) goto L11;
        this.sections = new ArrayList();
    L11:
        if (this.removedSections != null) goto L13;
        this.removedSections = new ArrayList();
    L13:
        int r0 = 0;
    L15:
        if (r0 >= getChildCount()) goto L52;
        View r4 = getChildAt(r0);
        if (r4 == this.draggingChild) goto L50;
        if (r4 == this.emptyView) goto L50;
        if (r4.getVisibility() != 0) goto L50;
        if (r4.getAlpha() <= 0.0f) goto L50;
        if (this.sectionsItemDecoration.isSectionItem.run(r4).booleanValue() == false) goto L50;
        float r5 = top(r4);
        float r6 = bottom(r4);
        RecyclerView.ViewHolder r7 = getChildViewHolder(r4);
        if (r7.isRemoved() == false) goto L40;
        if (r4.getAlpha() >= 1.0f) goto L40;
        if (hasSectionChildAt(r4, r0, false) == true) goto L50;
        ArrayList<SectionsDrawer.Section> r72 = this.removedSections;
        if (useSegmentedSections() == true) goto L38;
        r5 = r5 - this.sectionRadius;
    L38:
        r72.add(new SectionsDrawer.Section(r5, r6, r4.getAlpha(), isRoundSectionView(r4)));
    L40:
        if (isInsideForcedSection(r7.getAdapterPosition()) == true) goto L50;
        float r8 = r4.getAlpha();
        if (r7.isRemoved() == false) goto L45;
    L49:
        this.sections.add(new SectionsDrawer.Section(r5, r6, r8, isRoundSectionView(r4)));
        goto L50
    L45:
        if (r8 >= 0.99f) goto L49;
        if (hasSectionChildAt(r4, r0, true) == false) goto L49;
        r8 = 1.0f;
    L50:
        r0 = r0 + 1;
        goto L15
    L52:
        if (this.removedSections.isEmpty() == true) goto L54;
        SectionsDrawer.draw(this.removedSections, this.sectionRadius, new RecyclerListView$$ExternalSyntheticLambda3(this, r14), useSegmentedSections());
        this.removedSections.clear();
    L54:
        SectionsDrawer.draw(this.sections, this.sectionRadius, new RecyclerListView$$ExternalSyntheticLambda4(this, r14), useSegmentedSections());
        this.sections.clear();
    L55:
        Canvas r73 = r14;
    L86:
        if (this.forcedSections == null) goto L123;
        int r142 = 0;
    L89:
        if (r142 >= this.forcedSections.size()) goto L155;
        long r42 = this.forcedSections.get(r142).longValue();
        int r02 = AndroidUtilities.unpackA(r42);
        int r43 = AndroidUtilities.unpackB(r42);
        float r52 = getHeight();
        float r62 = this.sectionRadius;
        float r53 = r52 + r62;
        float r63 = -r62;
        int r82 = 0;
    L92:
        if (r82 >= getChildCount()) goto L100;
        View r9 = getChildAt(r82);
        int r10 = getChildAdapterPosition(r9);
        if (r9 == this.draggingChild) goto L98;
        if (r10 < r02) goto L98;
        if (r10 > r43) goto L98;
        r53 = Math.min(r53, top(r9));
        r63 = Math.max(r63, bottom(r9));
    L98:
        r82 = r82 + 1;
        goto L92
    L100:
        if (r53 >= r63) goto L122;
        if (useSegmentedSections() == false) goto L121;
        int r54 = 0;
    L105:
        if (r54 >= getChildCount()) goto L122;
        View r64 = getChildAt(r54);
        int r83 = getChildAdapterPosition(r64);
        if (r64 == this.draggingChild) goto L120;
        if (r64 == this.emptyView) goto L120;
        if (r64.getVisibility() != 0) goto L120;
        if (r64.getAlpha() <= 0.0f) goto L120;
        if (r83 < r02) goto L120;
        if (r83 > r43) goto L120;
        if (this.sectionsItemDecoration.isSectionItem.run(r64).booleanValue() == false) goto L120;
        drawSegmentedSectionBackground(r73, r64);
    L120:
        r54 = r54 + 1;
        goto L105
    L121:
        RectF r84 = AndroidUtilities.rectTmp;
        r84.set(getPaddingLeft() + ListSectionsDecoration.m12487$$Nest$fgetpadding(this.sectionsItemDecoration), r53, (getWidth() - getPaddingRight()) - ListSectionsDecoration.m12487$$Nest$fgetpadding(this.sectionsItemDecoration), r63);
        this.drawSectionBackground.run(r73, r84, Float.valueOf(this.sectionRadius), Float.valueOf(this.sectionRadius), Float.valueOf(1.0f));
    L122:
        r142 = r142 + 1;
        goto L89
    L155:
        return;
    L123:
        return;
    L57:
        if (useSegmentedSections() == false) goto L59;
        drawSegmentedSectionsBackgrounds(r14);
        goto L55
    L59:
        View r85 = null;
        View r92 = null;
        int r55 = 0;
        int r65 = -1;
        int r74 = -1;
    L61:
        if (r55 >= getChildCount()) goto L84;
        View r12 = getChildAt(r55);
        if (r12 != this.draggingChild) goto L65;
    L82:
        drawSectionBackground(r14, r85, r92, hasAbove(r85, r65), hasBelow(r92, r74));
        r85 = null;
        r92 = null;
        r65 = -1;
        r74 = -1;
    L83:
        r55 = r55 + 1;
        goto L61
    L65:
        if (r12 == this.emptyView) goto L82;
        if (r12.getVisibility() != 0) goto L82;
        if (r12.getAlpha() <= 0.0f) goto L82;
        if (this.sectionsItemDecoration.isSectionItem.run(r12).booleanValue() == false) goto L82;
        if (isInsideForcedSection(getChildAdapterPosition(r12)) == true) goto L82;
        if (r85 != null) goto L77;
    L79:
        if (r85 != null) goto L81;
        r65 = r55;
        r85 = r12;
    L81:
        r74 = r55;
        r92 = r12;
        goto L83
    L77:
        if (Math.abs(r92.getAlpha() - r12.getAlpha()) <= 0.1f) goto L79;
        drawSectionBackground(r14, r85, r92, hasAbove(r85, r65), hasBelow(r92, r74));
        r85 = null;
        r65 = -1;
        goto L79
    L84:
        boolean r102 = hasAbove(r85, r65);
        boolean r11 = hasBelow(r92, r74);
        r73 = r14;
        drawSectionBackground(r73, r85, r92, r102, r11);
        goto L86
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateSelectorState();
    }

    public boolean emptyViewIsVisible() {
        if (getAdapter() != null) goto L5;
    L11:
        return false;
    L5:
        if (isFastScrollAnimationRunning() == true) goto L11;
        if (getAdapter().getItemCount() != 0) goto L11;
        return true;
    }

    public void emptyViewUpdated(boolean r1, boolean r2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public View findChildViewUnder(float r8, float r9) {
        int r0 = getChildCount();
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L31;
        int r2 = r0 - 1;
    L6:
        if (r2 < 0) goto L30;
        View r3 = getChildAt(r2);
        if ((r3 instanceof ChatMessageCell) == true) goto L12;
        if ((r3 instanceof ChatActionCell) == true) goto L12;
    L14:
        float r4 = 0.0f;
        if (r1 != 0) goto L17;
        float r5 = r3.getTranslationX();
    L18:
        if (r1 != 0) goto L21;
        r4 = r3.getTranslationY();
    L21:
        if (r8 < (r3.getLeft() + r5)) goto L29;
        if (r8 > (r3.getRight() + r5)) goto L29;
        if (r9 < (r3.getTop() + r4)) goto L29;
        if (r9 > (r3.getBottom() + r4)) goto L29;
        return r3;
    L29:
        r2 = r2 - 1;
        goto L6
    L17:
        r5 = 0.0f;
    L12:
        if (r3.getVisibility() != 4) goto L14;
    L30:
        r1 = r1 + 1;
        goto L4
    L31:
        return null;
    }

    public View findViewByPosition(int r6) {
        if (r6 != (-1)) goto L5;
        return null;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= getChildCount()) goto L13;
        View r3 = getChildAt(r2);
        int r4 = getChildAdapterPosition(r3);
        if (r4 == (-1)) goto L12;
        if (r4 != r6) goto L12;
        return r3;
    L12:
        r2 = r2 + 1;
        goto L7
    L13:
        return null;
    }

    public Drawable getClipBackground(View r2) {
        return getClipBackground(r2, false);
    }

    public Drawable getClipBackground(View r11, boolean r12) {
        if (r11.getParent() == this) goto L5;
    L70:
        return null;
    L5:
        if (hasSections() == false) goto L70;
        if (this.sectionsItemDecoration.isSectionItem.run(r11).booleanValue() == false) goto L70;
        boolean r2 = true;
        if (useSegmentedSections() == false) goto L16;
        RectF r122 = new RectF();
        if (setSegmentedSectionRect(r11, r122) == true) goto L14;
        return null;
    L14:
        Path r0 = new Path();
        float r1 = getSegmentedSectionTopRadius(r11, r122);
        float r4 = getSegmentedSectionBottomRadius(r11, r122);
        float[] r5 = radii;
        r5[3] = r1;
        r5[2] = r1;
        r5[1] = r1;
        r5[0] = r1;
        r5[7] = r4;
        r5[6] = r4;
        r5[5] = r4;
        r5[4] = r4;
        r0.addRoundRect(r122, r5, Path.Direction.CW);
        return createClipBackgroundDrawable(r11, r122, r0);
    L16:
        int r02 = getChildAdapterPosition(r11);
        if (r02 != (-1)) goto L19;
        boolean r03 = false;
        boolean r13 = false;
    L30:
        RectF r42 = new RectF();
        float r52 = r11.getX();
        if (this.applyPaddingToSections == false) goto L33;
        float r6 = getPaddingTop();
    L34:
        float r62 = Math.max(r6, top(r11));
        float r7 = r11.getX() + r11.getWidth();
        int r8 = getHeight();
        if (this.applyPaddingToSections == false) goto L37;
        int r9 = getPaddingBottom();
    L38:
        r42.set(r52, r62, r7, Math.min(r8 - r9, bottom(r11)));
        if (r13 == false) goto L55;
        if (r03 == false) goto L55;
        if (r12 == true) goto L55;
        if (top(r11) < r42.top) goto L45;
        r13 = true;
    L47:
        if (bottom(r11) <= r42.bottom) goto L50;
        r2 = false;
    L50:
        if (r13 == false) goto L54;
        if (r2 == false) goto L54;
        return Theme.createRoundRectDrawable(0, Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
    L54:
        r03 = r2;
        goto L55
    L45:
        r13 = false;
    L55:
        Path r22 = new Path();
        if (r13 == true) goto L58;
        if (r03 == true) goto L58;
    L59:
        r22.rewind();
        if (isRoundSectionView(r11) == false) goto L62;
        float r123 = getSingleSectionRadius(r42);
    L63:
        r22.addRoundRect(r42, r123, r123, Path.Direction.CW);
    L69:
        return createClipBackgroundDrawable(r11, r42, r22);
    L62:
        r123 = this.sectionRadius;
    L58:
        if (r12 == true) goto L59;
        if (r13 == true) goto L66;
        r22.rewind();
        r22.addRoundRect(r42, this.sectionRadiusTop, Path.Direction.CW);
        goto L69
    L66:
        if (r03 == true) goto L69;
        r22.rewind();
        r22.addRoundRect(r42, this.sectionRadiusBottom, Path.Direction.CW);
        goto L69
    L37:
        r9 = 0;
        goto L38
    L33:
        r6 = 0.0f;
        goto L34
    L19:
        View r14 = findViewByPosition(r02 - 1);
        View r04 = findViewByPosition(r02 + 1);
        if (r14 != null) goto L22;
    L24:
        r13 = false;
    L25:
        if (r04 != null) goto L27;
    L29:
        r03 = false;
        goto L30
    L27:
        if (this.sectionsItemDecoration.isSectionItem.run(r04).booleanValue() == false) goto L29;
        r03 = true;
        goto L30
    L22:
        if (this.sectionsItemDecoration.isSectionItem.run(r14).booleanValue() == false) goto L24;
        r13 = true;
        goto L25
    }

    public View getEmptyView() {
        return this.emptyView;
    }

    public FastScroll getFastScroll() {
        return this.fastScroll;
    }

    public ArrayList<View> getHeaders() {
        return this.headers;
    }

    public ArrayList<View> getHeadersCache() {
        return this.headersCache;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return this.onScrollListener;
    }

    public View getPinnedHeader() {
        return this.pinnedHeader;
    }

    public View getPressedChildView() {
        return this.currentChildView;
    }

    public int[] getResourceDeclareStyleableIntArray(String r2, String r3) {
        Field r22 = Class.forName(r2 + ".R$styleable").getField(r3);     // Catch: Throwable -> L8
        if (r22 == null) goto L7;
        return (int[]) r22.get(null);
    L7:
        return null;
    }

    public Integer getSelectorColor(int r1) {
        GenericProvider<Integer, Integer> r0 = this.getSelectorColor;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.provide(Integer.valueOf(r1));
    }

    public Drawable getSelectorDrawable() {
        return this.selectorDrawable;
    }

    public Rect getSelectorRect() {
        return this.selectorRect;
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public ViewParent getTouchParent() {
        return null;
    }

    public boolean hasActiveEdgeEffects() {
        return this.edgeEffectTrackerFactory.hasVisibleEdges();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean hasSections() {
        if (this.sectionsItemDecoration == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void hide() {
        if (this.isHidden == true) goto L16;
        this.isHidden = true;
        if (getVisibility() == 8) goto L8;
        setVisibility(8);
    L8:
        View r0 = this.emptyView;
        if (r0 != null) goto L11;
        return;
    L11:
        if (r0.getVisibility() == 8) goto L15;
        this.emptyView.setVisibility(8);
        return;
    L15:
        return;
    }

    public void hideSelector(boolean r4) {
        View r0 = this.currentChildView;
        if (r0 == null) goto L7;
        onChildPressed(r0, 0.0f, 0.0f, false);
        this.currentChildView = null;
        if (r4 == false) goto L7;
        removeSelection(r0, null);
    L7:
        if (r4 == true) goto L10;
        this.selectorDrawable.setState(StateSet.NOTHING);
        this.selectorRect.setEmpty();
        return;
    }

    public void highlightRow(IntReturnCallback r3) {
        highlightRowInternal(r3, 700, true);
    }

    public void highlightRow(IntReturnCallback r2, int r3) {
        highlightRowInternal(r2, r3, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void invalidateViews() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = getChildAt(r1);
        if ((r2 instanceof Theme.Colorable) == false) goto L7;
        ((Theme.Colorable) r2).updateColors();
    L7:
        r2.invalidate();
        r1 = r1 + 1;
        goto L3
    }

    public boolean isFastScrollAnimationRunning() {
        return this.fastScrollAnimationRunning;
    }

    public boolean isInsideForcedSection(int r6) {
        if (this.forcedSections == null) goto L15;
        if (r6 < 0) goto L15;
        int r0 = 0;
    L8:
        if (r0 >= this.forcedSections.size()) goto L15;
        long r2 = this.forcedSections.get(r0).longValue();
        int r4 = AndroidUtilities.unpackA(r2);
        int r22 = AndroidUtilities.unpackB(r2);
        if (r6 < r4) goto L14;
        if (r6 > r22) goto L14;
        return true;
    L14:
        r0 = r0 + 1;
    L15:
        return false;
    }

    public boolean isMultiselect() {
        return this.multiSelectionGesture;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.selectorDrawable;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    public boolean longClickItem(View r2, int r3) {
        OnItemLongClickListener r0 = this.onItemLongClickListener;
        if (r0 != null) goto L5;
        OnItemLongClickListenerExtended r1 = this.onItemLongClickListenerExtended;
        if (r1 != null) goto L9;
        return false;
    L9:
        return r1.onItemClick(r2, r3, 0.0f, 0.0f);
    L5:
        return r0.onItemClick(r2, r3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FastScroll r0 = this.fastScroll;
        if (r0 == null) goto L11;
        if (r0.getParent() == getParent()) goto L11;
        ViewGroup r02 = (ViewGroup) this.fastScroll.getParent();
        if (r02 == null) goto L9;
        r02.removeView(this.fastScroll);
    L9:
        ((ViewGroup) getParent()).addView(this.fastScroll);
    L11:
        if (ExteraConfig.getInAppVibration() == true) goto L14;
        VibratorUtils.disableHapticFeedback(getRootView());
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onChildAttachedToWindow(View r3) {
        if ((getAdapter() instanceof SelectionAdapter) == false) goto L9;
        RecyclerView.ViewHolder r0 = findContainingViewHolder(r3);
        if (r0 == null) goto L10;
        r3.setEnabled(((SelectionAdapter) getAdapter()).isEnabled(r0));
        if (this.accessibilityEnabled == false) goto L10;
        r3.setAccessibilityDelegate(this.accessibilityDelegate);
    L10:
        super.onChildAttachedToWindow(r3);
        return;
    L9:
        r3.setEnabled(false);
        r3.setAccessibilityDelegate(null);
        goto L10
    }

    public void onChildPressed(View r1, float r2, float r3, boolean r4) {
        if (this.disableHighlightState == true) goto L8;
        if (r1 == null) goto L9;
        r1.setPressed(r4);
        return;
    L9:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.selectorPosition = -1;
        this.selectorView = null;
        this.selectorIsSection = false;
        this.selectorRect.setEmpty();
        RecyclerItemsEnterAnimator r1 = this.itemsEnterAnimator;
        if (r1 == null) goto L6;
        r1.onDetached();
    L6:
        if (this.stoppedAllHeavyOperations == false) goto L9;
        this.stoppedAllHeavyOperations = false;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r4) {
        if (isEnabled() == true) goto L6;
        return false;
    L6:
        if (this.disallowInterceptTouchEvents == false) goto L8;
        requestDisallowInterceptTouchEvent(this, true);
    L8:
        OnInterceptTouchListener r0 = this.onInterceptTouchListener;
        if (r0 == null) goto L13;
        if (r0.onInterceptTouchEvent(r4) == false) goto L13;
    L14:
        return true;
    L13:
        if (super.onInterceptTouchEvent(r4) == true) goto L14;
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);     // Catch: Exception -> L7
        FastScroll r22 = this.fastScroll;     // Catch: Exception -> L7
        if (r22 == null) goto L15;
        this.selfOnLayout = true;     // Catch: Exception -> L7
        if (r22.usePadding == false) goto L9;
        int r23 = getPaddingTop();     // Catch: Exception -> L7
    L10:
        int r42 = r4 + r23;     // Catch: Exception -> L7
        FastScroll r24 = this.fastScroll;     // Catch: Exception -> L7
        if (r24.isRtl == false) goto L13;
        r24.layout(0, r42, r24.getMeasuredWidth(), this.fastScroll.getMeasuredHeight() + r42);     // Catch: Exception -> L7
    L14:
        this.selfOnLayout = false;     // Catch: Exception -> L7
        goto L15
    L13:
        int r25 = getMeasuredWidth() - this.fastScroll.getMeasuredWidth();     // Catch: Exception -> L7
        FastScroll r52 = this.fastScroll;     // Catch: Exception -> L7
        r52.layout(r25, r42, r52.getMeasuredWidth() + r25, this.fastScroll.getMeasuredHeight() + r42);     // Catch: Exception -> L7
        goto L14
    L9:
        r23 = r22.topOffset;     // Catch: Exception -> L7
    L15:
        checkSection(false);     // Catch: Exception -> L7
        IntReturnCallback r26 = this.pendingHighlightPosition;     // Catch: Exception -> L7
        if (r26 == null) goto L23;
        highlightRowInternal(r26, 700, false);     // Catch: Exception -> L7
        return;
    L23:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        post(new RecyclerListView$$ExternalSyntheticLambda2(this));
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, r4);
        FastScroll r32 = this.fastScroll;
        if (r32 != null) goto L5;
    L11:
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        return;
    L5:
        if (r32.getLayoutParams() == null) goto L11;
        FastScroll r33 = this.fastScroll;
        if (r33.usePadding == false) goto L9;
        int r34 = getPaddingTop();
    L10:
        int r42 = (getMeasuredHeight() - r34) - getPaddingBottom();
        this.fastScroll.getLayoutParams().height = r42;
        this.fastScroll.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(132.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30));
        goto L11
    L9:
        r34 = r33.topOffset;
        goto L10
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        FrameLayout r12 = this.overlayContainer;
        if (r12 == null) goto L5;
        r12.requestLayout();
    L5:
        int r13 = this.sectionsType;
        if (r13 != 1) goto L17;
        if (this.sectionsAdapter != null) goto L10;
        return;
    L10:
        if (this.headers.isEmpty() == true) goto L29;
        int r14 = 0;
    L14:
        if (r14 >= this.headers.size()) goto L31;
        ensurePinnedHeaderLayout(this.headers.get(r14), true);
        r14 = r14 + 1;
        goto L14
    L31:
        return;
    L29:
        return;
    L17:
        if (r13 == 2) goto L19;
        return;
    L19:
        if (this.sectionsAdapter == null) goto L28;
        View r15 = this.pinnedHeader;
        if (r15 == null) goto L30;
        ensurePinnedHeaderLayout(r15, true);
        return;
    L30:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        FastScroll r0 = this.fastScroll;
        if (r0 == null) goto L8;
        if (FastScroll.m12483$$Nest$fgetpressed(r0) == false) goto L8;
        return false;
    L8:
        if (this.multiSelectionGesture == true) goto L10;
    L43:
        this.lastX = Float.MAX_VALUE;
        this.lastY = Float.MAX_VALUE;
        this.multiSelectionGesture = false;
        this.multiSelectionGestureStarted = false;
        requestDisallowInterceptTouchEvent(this, false);
        cancelMultiselectScroll();
        return super.onTouchEvent(r7);
    L10:
        if (r7.getAction() == 0) goto L43;
        if (r7.getAction() == 1) goto L43;
        if (r7.getAction() == 3) goto L43;
        float r02 = r7.getRawY();
        if (this.lastX == Float.MAX_VALUE) goto L18;
    L20:
        int r2 = Math.max(this.touchSlop, this.multiSelectionListener.getStartDragDistance());
        if (this.multiSelectionGestureStarted == true) goto L26;
        if (Math.abs(r02 - this.lastY) <= r2) goto L26;
        this.multiSelectionGestureStarted = true;
        requestDisallowInterceptTouchEvent(this, true);
    L26:
        if (this.multiSelectionGestureStarted == false) goto L42;
        chekMultiselect(r7.getX(), r7.getY());
        this.multiSelectionListener.getPaddings(this.listPaddings);
        if (r7.getY() <= ((getMeasuredHeight() - AndroidUtilities.dp(56.0f)) - this.listPaddings[1])) goto L35;
        if (this.currentSelectedPosition < this.startSelectionFrom) goto L32;
    L33:
        startMultiselectScroll(false);
        goto L42
    L32:
        if (this.multiSelectionListener.limitReached() == false) goto L33;
    L35:
        if (r7.getY() < (AndroidUtilities.dp(56.0f) + this.listPaddings[0])) goto L37;
    L41:
        cancelMultiselectScroll();
        goto L42
    L37:
        if (this.currentSelectedPosition > this.startSelectionFrom) goto L39;
    L40:
        startMultiselectScroll(true);
        goto L42
    L39:
        if (this.multiSelectionListener.limitReached() == true) goto L41;
    L42:
        return true;
    L18:
        if (this.lastY != Float.MAX_VALUE) goto L20;
        this.lastX = r7.getRawX();
        this.lastY = r02;
        goto L20
    }

    public void relayoutPinnedHeader() {
        View r0 = this.pinnedHeader;
        if (r0 == null) goto L6;
        r0.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0));
        View r02 = this.pinnedHeader;
        r02.layout(0, 0, r02.getMeasuredWidth(), this.pinnedHeader.getMeasuredHeight());
        invalidate();
        return;
    }

    public void removeHighlightRow() {
        Runnable r0 = this.removeHighlighSelectionRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.removeHighlighSelectionRunnable.run();
        this.removeHighlighSelectionRunnable = null;
        this.selectorView = null;
        return;
    L6:
        this.removeHighlighSelectionRunnable = null;
        this.pendingHighlightPosition = null;
        View r02 = this.selectorView;
        if (r02 == null) goto L15;
        int r2 = this.highlightPosition;
        if (r2 == (-1)) goto L15;
        positionSelector(r2, r02);
        Drawable r03 = this.selectorDrawable;
        if (r03 == null) goto L13;
        r03.setState(new int[0]);
        invalidateDrawable(this.selectorDrawable);
    L13:
        this.selectorView = null;
        this.highlightPosition = -1;
        return;
    L15:
        Drawable r04 = this.selectorDrawable;
        if (r04 == null) goto L20;
        Drawable r05 = r04.getCurrent();
        if ((r05 instanceof TransitionDrawable) == false) goto L20;
        ((TransitionDrawable) r05).resetTransition();
    L20:
        Drawable r06 = this.selectorDrawable;
        if (r06 != null) goto L23;
        return;
    L23:
        if (r06.isStateful() == true) goto L25;
        return;
    L25:
        if (this.selectorDrawable.setState(StateSet.NOTHING) == false) goto L30;
        invalidateDrawable(this.selectorDrawable);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.fastScrollAnimationRunning == false) goto L5;
        return;
    L5:
        if (this.ignoreLayout == true) goto L10;
        super.requestLayout();
        return;
    }

    public void setAccessibilityEnabled(boolean r1) {
        this.accessibilityEnabled = r1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter r4) {
        RecyclerView.Adapter r0 = getAdapter();
        if (r0 == null) goto L5;
        r0.unregisterAdapterDataObserver(this.observer);
    L5:
        ArrayList<View> r02 = this.headers;
        if (r02 == null) goto L8;
        r02.clear();
        this.headersCache.clear();
    L8:
        this.currentFirst = -1;
        this.selectorPosition = -1;
        this.selectorView = null;
        this.selectorIsSection = false;
        this.selectorRect.setEmpty();
        this.pinnedHeader = null;
        if ((r4 instanceof SectionsAdapter) == false) goto L11;
        this.sectionsAdapter = (SectionsAdapter) r4;
    L12:
        super.setAdapter(r4);
        if (r4 == null) goto L15;
        r4.registerAdapterDataObserver(this.observer);
    L15:
        checkIfEmpty(false);
        return;
    L11:
        this.sectionsAdapter = null;
        goto L12
    }

    public void setAdaptiveOverScroll() {
        this.adaptiveOverScroll = true;
        setOverScrollMode(2);
    }

    public void setAllowItemsInteractionDuringAnimation(boolean r1) {
        this.allowItemsInteractionDuringAnimation = r1;
    }

    public void setAllowStopHeaveOperations(boolean r1) {
        this.allowStopHeaveOperations = r1;
    }

    public void setAnimateEmptyView(boolean r1, int r2) {
        this.animateEmptyView = r1;
        this.emptyViewAnimationType = r2;
    }

    public void setCaptureSectionsDecoratorAllowed(boolean r1) {
        this.canCaptureSectionsDecorator = r1;
    }

    public void setDisableHighlightState(boolean r1) {
        this.disableHighlightState = r1;
    }

    public void setDisallowInterceptTouchEvents(boolean r1) {
        this.disallowInterceptTouchEvents = r1;
    }

    public void setDraggingChild(View r1) {
        this.draggingChild = r1;
    }

    public void setDrawSelection(boolean r1) {
        this.drawSelection = r1;
    }

    public void setDrawSelectorBehind(boolean r1) {
        this.drawSelectorBehind = r1;
    }

    public void setEmptyView(View r3) {
        View r0 = this.emptyView;
        if (r0 == r3) goto L20;
        if (r0 == null) goto L7;
        r0.animate().setListener(null).cancel();
    L7:
        this.emptyView = r3;
        if (this.animateEmptyView == false) goto L12;
        if (r3 == null) goto L12;
        r3.setVisibility(8);
    L12:
        if (this.isHidden == false) goto L17;
        View r32 = this.emptyView;
        if (r32 == null) goto L19;
        this.emptyViewAnimateToVisibility = 8;
        r32.setVisibility(8);
        return;
    L19:
        return;
    L17:
        this.emptyViewAnimateToVisibility = -1;
        checkIfEmpty(false);
        return;
    }

    public void setFastScrollEnabled(int r3) {
        this.fastScroll = new FastScroll(this, getContext(), r3);
        if (getParent() == null) goto L6;
        ((ViewGroup) getParent()).addView(this.fastScroll);
        return;
    }

    public void setFastScrollVisible(boolean r3) {
        FastScroll r0 = this.fastScroll;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r3 == false) goto L7;
        int r1 = 0;
    L8:
        r0.setVisibility(r1);
        this.fastScroll.isVisible = r3;
        return;
    L7:
        r1 = 8;
        goto L8
    }

    public void setHideIfEmpty(boolean r1) {
        this.hideIfEmpty = r1;
    }

    public void setInstantClick(boolean r1) {
        this.instantClick = r1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setItemAnimator(RecyclerView.ItemAnimator r1) {
        super.setItemAnimator(r1);
    }

    public void setItemSelectorColorProvider(GenericProvider<Integer, Integer> r1) {
        this.getSelectorColor = r1;
    }

    public void setItemsEnterAnimator(RecyclerItemsEnterAnimator r1) {
        this.itemsEnterAnimator = r1;
    }

    public void setListSelectorColor(Integer r2) {
        Drawable r0 = this.selectorDrawable;
        if (r2 == null) goto L5;
        int r1 = r2.intValue();
    L10:
        Theme.setSelectorDrawableColor(r0, r1, true);
        return;
    L5:
        if (hasSections() == false) goto L7;
        int r22 = Theme.key_settings_listSelector;
    L8:
        r1 = getThemedColor(r22);
        goto L10
    L7:
        r22 = Theme.key_listSelector;
        goto L8
    }

    public void setOnInterceptTouchListener(OnInterceptTouchListener r1) {
        this.onInterceptTouchListener = r1;
    }

    public void setOnItemClickListener(OnItemClickListener r1) {
        this.onItemClickListener = r1;
    }

    public void setOnItemClickListener(OnItemClickListenerExtended r1) {
        this.onItemClickListenerExtended = r1;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener r3) {
        setOnItemLongClickListener(r3, ViewConfiguration.getLongPressTimeout());
    }

    public void setOnItemLongClickListener(OnItemLongClickListener r2, long r3) {
        this.onItemLongClickListener = r2;
        GestureDetectorFixDoubleTap r0 = this.gestureDetector;
        if (r2 == null) goto L5;
        boolean r22 = true;
    L6:
        r0.setIsLongpressEnabled(r22);
        this.gestureDetector.setLongpressDuration(r3);
        return;
    L5:
        r22 = false;
        goto L6
    }

    public void setOnItemLongClickListener(OnItemLongClickListenerExtended r3) {
        setOnItemLongClickListener(r3, ViewConfiguration.getLongPressTimeout());
    }

    public void setOnItemLongClickListener(OnItemLongClickListenerExtended r2, long r3) {
        this.onItemLongClickListenerExtended = r2;
        GestureDetectorFixDoubleTap r0 = this.gestureDetector;
        if (r2 == null) goto L5;
        boolean r22 = true;
    L6:
        r0.setIsLongpressEnabled(r22);
        this.gestureDetector.setLongpressDuration(r3);
        return;
    L5:
        r22 = false;
        goto L6
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setOnScrollListener(RecyclerView.OnScrollListener r1) {
        this.onScrollListener = r1;
    }

    public void setPadding(int r1, int r2, int r3, int r4, boolean r5) {
        if (r5 == false) goto L5;
        setPaddingWithoutRequestLayout(r1, r2, r3, r4);
        return;
    L5:
        setPadding(r1, r2, r3, r4);
    }

    public void setPaddingWithoutRequestLayout(int r2, int r3, int r4, int r5) {
        if (getPaddingLeft() == r2) goto L5;
    L12:
        this.ignoreLayout = true;
        setPadding(r2, r3, r4, r5);
        this.ignoreLayout = false;
        return;
    L5:
        if (getPaddingTop() != r3) goto L12;
        if (getPaddingRight() != r4) goto L12;
        if (getPaddingBottom() != r5) goto L12;
    }

    public void setPinnedHeaderShadowDrawable(Drawable r1) {
        this.pinnedHeaderShadowDrawable = r1;
    }

    public void setPinnedSectionOffsetY(int r1) {
        this.sectionOffset = r1;
        invalidate();
    }

    public void setResetSelectorOnChanged(boolean r1) {
        this.resetSelectorOnChanged = r1;
    }

    public void setScrollEnabled(boolean r1) {
        this.scrollEnabled = r1;
    }

    public void setSections() {
        setSections(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp()), false);
    }

    public void setSections(int r7, float r8, boolean r9) {
        setSections(new RecyclerListView$$ExternalSyntheticLambda0(), r7, r8, new RecyclerListView$$ExternalSyntheticLambda1(this), r9);
    }

    public void setSections(Utilities.CallbackReturn<View, Boolean> r9, int r10, float r11, Utilities.Callback5<Canvas, RectF, Float, Float, Float> r12, boolean r13) {
        Pair<Utilities.CallbackReturn<View, Boolean>, Utilities.CallbackReturn<Integer, Boolean>> r92 = cachedIsViewTypeShadow(this, r9);
        setSections((Utilities.CallbackReturn) r92.first, (Utilities.CallbackReturn) r92.second, r10, r11, r12, r13);
    }

    public void setSections(Utilities.CallbackReturn<View, Boolean> r12, Utilities.CallbackReturn<Integer, Boolean> r13, int r14, float r15, Utilities.Callback5<Canvas, RectF, Float, Float, Float> r16, boolean r17) {
        setSelectorDrawableColor(getThemedColor(Theme.key_settings_listSelector));
        this.isViewTypeSection = r13;
        this.sectionRadius = r15;
        this.sectionRadiusTop = new float[]{r15, r15, r15, r15, 0.0f, 0.0f, 0.0f, 0.0f};
        this.sectionRadiusBottom = new float[]{0.0f, 0.0f, 0.0f, 0.0f, r15, r15, r15, r15};
        this.drawSectionBackground = r16;
        RecyclerView.ItemDecoration r132 = this.sectionsItemDecoration;
        if (r132 == null) goto L5;
        removeItemDecoration(r132);
    L5:
        ListSectionsDecoration r133 = new ListSectionsDecoration(this, r12, r14, r17);
        this.sectionsItemDecoration = r133;
        addItemDecoration(r133);
    }

    public void setSections(boolean r3) {
        setSections(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp()), r3);
    }

    public void setSectionsType(int r2) {
        this.sectionsType = r2;
        if (r2 != 1) goto L5;
    L8:
        this.headers = new ArrayList();
        this.headersCache = new ArrayList();
        return;
    L5:
        if (r2 == 3) goto L8;
    }

    public void setSegmentedSectionsEnabled(boolean r2) {
        if (this.segmentedSectionsEnabled == r2) goto L10;
        this.segmentedSectionsEnabled = r2;
        if (this.sectionsItemDecoration == null) goto L9;
        invalidateItemDecorations();
        invalidate();
        return;
    L9:
        return;
    }

    public void setSelectorDrawableColor(int r5) {
        Drawable r0 = this.selectorDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        int r02 = this.selectorType;
        if (r02 != 100) goto L9;
        this.selectorDrawable = Theme.createSimpleSelectorRoundRectDrawable(0, 0, 0, 0);
    L26:
        Drawable r52 = this.selectorDrawable;
        if (r52 == null) goto L30;
        r52.setCallback(this);
        return;
    L30:
        return;
    L9:
        if (r02 != 8) goto L12;
        this.selectorDrawable = Theme.createRadSelectorDrawable(r5, this.selectorRadius, 0);
        goto L26
    L12:
        if (r02 != 9) goto L14;
        this.selectorDrawable = null;
        goto L26
    L14:
        int r1 = this.topBottomSelectorRadius;
        if (r1 <= 0) goto L17;
        this.selectorDrawable = Theme.createRadSelectorDrawable(r5, r1, r1);
        goto L26
    L17:
        int r12 = this.selectorRadius;
        if (r12 <= 0) goto L23;
        if (r02 == 1) goto L23;
        this.selectorDrawable = Theme.createSimpleSelectorRoundRectDrawable(r12, 0, r5, -16777216);
    L23:
        if (r02 != 2) goto L25;
        this.selectorDrawable = Theme.getSelectorDrawable(r5, false);
        goto L26
    L25:
        this.selectorDrawable = Theme.createSelectorDrawable(r5, r02, r12);
        goto L26
    }

    public void setSelectorRadius(int r1) {
        this.selectorRadius = r1;
    }

    public void setSelectorTransformer(Consumer<Canvas> r1) {
        this.selectorTransformer = r1;
    }

    public void setSelectorType(int r1) {
        this.selectorType = r1;
    }

    public void setSkipDrawSection(boolean r1) {
        this.skipDrawSection = r1;
    }

    public void setTopBottomSelectorRadius(int r1) {
        this.topBottomSelectorRadius = r1;
    }

    public void setTranslateSelector(boolean r1) {
        if (r1 == false) goto L4;
        int r12 = -2;
    L5:
        this.translateSelector = r12;
        return;
    L4:
        r12 = -1;
        goto L5
    }

    public void setTranslateSelectorPosition(int r1) {
        if (r1 > 0) goto L4;
        r1 = -1;
    L4:
        this.translateSelector = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        FastScroll r0 = this.fastScroll;
        if (r0 == null) goto L6;
        r0.setTranslationY(r1);
        return;
    }

    @Override // android.view.View
    public void setVerticalScrollBarEnabled(boolean r2) {
        if (attributes == null) goto L6;
        super.setVerticalScrollBarEnabled(r2);
        return;
    }

    @Override // android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
        if (r1 == 0) goto L6;
        this.hiddenByEmptyView = false;
        return;
    }

    public void show() {
        if (this.isHidden == true) goto L5;
        return;
    L5:
        this.isHidden = false;
        checkIfEmpty(false);
    }

    public void startMultiselect(int r2, boolean r3, onMultiSelectionChanged r4) {
        if (this.multiSelectionGesture == true) goto L5;
        this.listPaddings = new int[2];
        this.selectedPositions = new HashSet();
        requestDisallowInterceptTouchEvent(this, true);
        this.multiSelectionListener = r4;
        this.multiSelectionGesture = true;
        this.currentSelectedPosition = r2;
        this.startSelectionFrom = r2;
    L5:
        this.useRelativePositions = r3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void stopScroll() {
        super.stopScroll();     // Catch: NullPointerException -> L4
        return;
    }

    public boolean updateEmptyViewAnimated() {
        return isAttachedToWindow();
    }

    public void updateFastScrollColors() {
        FastScroll r0 = this.fastScroll;
        if (r0 == null) goto L6;
        FastScroll.m12486$$Nest$mupdateColors(r0);
        return;
    }

    public void updateSelector() {
        int r0 = this.selectorPosition;
        if (r0 == (-1)) goto L8;
        View r1 = this.selectorView;
        if (r1 == null) goto L9;
        positionSelector(r0, r1);
        invalidate();
        return;
    L9:
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.selectorDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
