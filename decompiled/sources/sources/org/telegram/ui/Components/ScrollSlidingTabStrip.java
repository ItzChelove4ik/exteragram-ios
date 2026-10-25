package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import com.android.dx.rop.code.RegisterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class ScrollSlidingTabStrip extends HorizontalScrollView {
    public static float EXPANDED_WIDTH = 64.0f;
    private boolean animateFromPosition;
    boolean animateToExpanded;
    int currentDragPosition;
    SparseArray<StickerTabView> currentPlayingImages;
    SparseArray<StickerTabView> currentPlayingImagesTmp;
    private int currentPosition;
    private AnimatedFloat currentPositionAnimated;
    private LinearLayout.LayoutParams defaultExpandLayoutParams;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private ScrollSlidingTabStripDelegate delegate;
    private int dividerPadding;
    float dragDx;
    private boolean dragEnabled;
    float draggindViewDxOnScreen;
    float draggindViewXOnScreen;
    View draggingView;
    float draggingViewOutProgress;
    private float expandOffset;
    float expandProgress;
    ValueAnimator expandStickerAnimator;
    boolean expanded;
    private SparseArray<View> futureTabsPositions;
    private int imageReceiversPlayingNum;
    private int indicatorColor;
    private GradientDrawable indicatorDrawable;
    private int indicatorHeight;
    private final boolean isGlassDesign;
    private long lastAnimationTime;
    private int lastScrollX;
    private RectF leftTabBounds;
    Runnable longClickRunnable;
    boolean longClickRunning;
    private float positionAnimationProgress;
    float pressedX;
    float pressedY;
    private HashMap<String, View> prevTypes;
    private Paint rectPaint;
    private final Theme.ResourcesProvider resourcesProvider;
    private RectF rightTabBounds;
    private int scrollByOnNextMeasure;
    private int scrollOffset;
    boolean scrollRight;
    Runnable scrollRunnable;
    long scrollStartTime;
    private Paint selectorPaint;
    private boolean shouldExpand;
    private boolean showSelected;
    private AnimatedFloat showSelectedAlpha;
    private float startAnimationPosition;
    int startDragFromPosition;
    float startDragFromX;
    private float stickerTabExpandedWidth;
    private float stickerTabWidth;
    private RectF tabBounds;
    private int tabCount;
    private int tabPadding;
    private HashMap<String, View> tabTypes;
    private LinearLayout tabsContainer;
    private float touchSlop;
    private Type type;
    private int underlineColor;
    private int underlineHeight;

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ScrollSlidingTabStrip this$0;

        public AnonymousClass1(ScrollSlidingTabStrip r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollSlidingTabStrip r0 = this.this$0;
            r0.longClickRunning = false;
            float r2 = r0.getScrollX();
            ScrollSlidingTabStrip r3 = this.this$0;
            r0.startDragFromX = r2 + r3.pressedX;
            r3.dragDx = 0.0f;
            int r22 = ((int) Math.ceil(r3.startDragFromX / ScrollSlidingTabStrip.m12532$$Nest$mgetTabSize(r3))) - 1;
            ScrollSlidingTabStrip r32 = this.this$0;
            r32.currentDragPosition = r22;
            r32.startDragFromPosition = r22;
            if (ScrollSlidingTabStrip.m12531$$Nest$mcanSwap(r32, r22) == false) goto L16;
            if (r22 >= 0) goto L7;
            return;
        L7:
            if (r22 >= ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).getChildCount()) goto L15;
            this.this$0.performHapticFeedback(0);     // Catch: Exception -> L11
        L9:
            ScrollSlidingTabStrip r1 = this.this$0;
            r1.draggindViewDxOnScreen = 0.0f;
            r1.draggingViewOutProgress = 0.0f;
            r1.draggingView = ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(r1).getChildAt(r22);
            ScrollSlidingTabStrip r02 = this.this$0;
            r02.draggindViewXOnScreen = r02.draggingView.getX() - this.this$0.getScrollX();
            this.this$0.draggingView.invalidate();
            ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).invalidate();
            this.this$0.invalidateOverlays();
            this.this$0.invalidate();
            return;
        L15:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ ScrollSlidingTabStrip this$0;

        public AnonymousClass2(ScrollSlidingTabStrip r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r3, View r4, long r5) {
            if ((r4 instanceof StickerTabView) == false) goto L6;
            ((StickerTabView) r4).updateExpandProgress(this.this$0.expandProgress);
        L6:
            if (r4 != this.this$0.draggingView) goto L10;
            return true;
        L10:
            return super.drawChild(r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$3, reason: invalid class name */
    public class AnonymousClass3 extends Transition {
        final /* synthetic */ ScrollSlidingTabStrip this$0;

        /* renamed from: $r8$lambda$aBGDzis3VJf58HGAn-vNhZaaY_8, reason: not valid java name */
        public static /* synthetic */ void m12533$r8$lambda$aBGDzis3VJf58HGAnvNhZaaY_8(AnonymousClass3 r0, ValueAnimator r1) {
            r0.lambda$createAnimator$0(r1);
        }

        public AnonymousClass3(ScrollSlidingTabStrip r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$createAnimator$0(ValueAnimator r1) {
            this.this$0.invalidate();
        }

        @Override // android.transition.Transition
        public void captureEndValues(TransitionValues r1) {
        }

        @Override // android.transition.Transition
        public void captureStartValues(TransitionValues r1) {
        }

        @Override // android.transition.Transition
        public Animator createAnimator(ViewGroup r1, TransitionValues r2, TransitionValues r3) {
            ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r12.addUpdateListener(new ScrollSlidingTabStrip$3$$ExternalSyntheticLambda0(this));
            return r12;
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ScrollSlidingTabStrip this$0;
        final /* synthetic */ boolean val$expanded;
        final /* synthetic */ float val$x;

        public AnonymousClass4(ScrollSlidingTabStrip r1, boolean r2, float r3) {
            this.this$0 = r1;
            this.val$expanded = r2;
            this.val$x = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r7) {
            ScrollSlidingTabStrip r72 = this.this$0;
            r72.expandStickerAnimator = null;
            if (this.val$expanded == false) goto L5;
            float r0 = 1.0f;
        L6:
            r72.expandProgress = r0;
            int r02 = 0;
        L7:
            int r2 = ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).getChildCount();
            ScrollSlidingTabStrip r3 = this.this$0;
            if (r02 >= r2) goto L10;
            ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(r3).getChildAt(r02).invalidate();
            r02 = r02 + 1;
            goto L7
        L10:
            ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(r3).invalidate();
            this.this$0.updatePosition();
            if (this.val$expanded == true) goto L34;
            float r03 = ScrollSlidingTabStrip.m12527$$Nest$fgetstickerTabWidth(this.this$0) * ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).getChildCount();
            float r22 = (this.this$0.getScrollX() + this.val$x) / (ScrollSlidingTabStrip.m12526$$Nest$fgetstickerTabExpandedWidth(this.this$0) * ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).getChildCount());
            float r32 = (r03 - this.this$0.getMeasuredWidth()) / r03;
            float r4 = this.val$x;
            if (r22 <= r32) goto L15;
            r4 = 0.0f;
            r22 = r32;
        L15:
            float r04 = r03 * r22;
            if ((r04 - r4) >= 0.0f) goto L18;
            r04 = r4;
        L18:
            ScrollSlidingTabStrip.m12529$$Nest$fputexpandOffset(this.this$0, (r1.getScrollX() + r4) - r04);
            ScrollSlidingTabStrip.m12530$$Nest$fputscrollByOnNextMeasure(this.this$0, (int) (r04 - r4));
            if (ScrollSlidingTabStrip.m12525$$Nest$fgetscrollByOnNextMeasure(this.this$0) >= 0) goto L21;
            ScrollSlidingTabStrip.m12530$$Nest$fputscrollByOnNextMeasure(this.this$0, 0);
        L21:
            int r05 = 0;
        L22:
            int r1 = ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).getChildCount();
            ScrollSlidingTabStrip r23 = this.this$0;
            if (r05 >= r1) goto L28;
            View r12 = ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(r23).getChildAt(r05);
            if ((r12 instanceof StickerTabView) == false) goto L27;
            ((StickerTabView) r12).setExpanded(false);
        L27:
            r12.getLayoutParams().width = AndroidUtilities.dp(33.0f);
            r05 = r05 + 1;
            goto L22
        L28:
            r23.animateToExpanded = false;
            r23.getLayoutParams().height = AndroidUtilities.dp(36.0f);
            ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).requestLayout();
            return;
        L34:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ ScrollSlidingTabStrip this$0;

        public AnonymousClass5(ScrollSlidingTabStrip r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ScrollSlidingTabStrip r22 = this.this$0;
            if (r22.draggingView == null) goto L6;
            r22.invalidateOverlays();
            this.this$0.draggingView.invalidate();
            ScrollSlidingTabStrip.m12528$$Nest$fgettabsContainer(this.this$0).invalidate();
            this.this$0.invalidate();
            this.this$0.draggingView = null;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ ScrollSlidingTabStrip this$0;

        public AnonymousClass6(ScrollSlidingTabStrip r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            long r0 = System.currentTimeMillis() - this.this$0.scrollStartTime;
            int r3 = -1;
            if (r0 >= 3000) goto L9;
            int r02 = Math.max(1, AndroidUtilities.dp(1.0f));
            if (this.this$0.scrollRight == false) goto L16;
        L6:
            r3 = 1;
        L16:
            this.this$0.scrollBy(r02 * r3, 0);
            AndroidUtilities.runOnUIThread(this.this$0.scrollRunnable);
            return;
        L9:
            if (r0 >= 5000) goto L13;
            r02 = Math.max(1, AndroidUtilities.dp(2.0f));
            if (this.this$0.scrollRight == false) goto L16;
        L13:
            r02 = Math.max(1, AndroidUtilities.dp(4.0f));
            if (this.this$0.scrollRight == false) goto L16;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTabStrip$7, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Components$ScrollSlidingTabStrip$Type = null;

        static {
            int[] r0 = new int[Type.values().length];
            $SwitchMap$org$telegram$ui$Components$ScrollSlidingTabStrip$Type = r0;
            r0[Type.LINE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$org$telegram$ui$Components$ScrollSlidingTabStrip$Type[Type.TAB.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public interface ScrollSlidingTabStripDelegate {
        void onPageSelected(int r1);
    }

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type LINE = null;
        public static final Type TAB = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{LINE, TAB};
        }

        static {
            LINE = new Type("LINE", 0);
            TAB = new Type("TAB", 1);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    /* renamed from: $r8$lambda$JPqEHdA0HVeMZx5hJYyTiK-mkOg, reason: not valid java name */
    public static /* synthetic */ void m12521$r8$lambda$JPqEHdA0HVeMZx5hJYyTiKmkOg(ScrollSlidingTabStrip r0, View r1) {
        r0.lambda$addStickerTab$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$WVJTKAAlDfgbs1dOpCDlH1TZvls(ScrollSlidingTabStrip r0, boolean r1, float r2, ValueAnimator r3) {
        r0.lambda$expandStickers$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$gxl4Vwx_4nFfnaw-1r5mGnm7Rf8, reason: not valid java name */
    public static /* synthetic */ void m12522$r8$lambda$gxl4Vwx_4nFfnaw1r5mGnm7Rf8(ScrollSlidingTabStrip r0, View r1) {
        r0.lambda$addStickerTab$2(r1);
    }

    /* renamed from: $r8$lambda$rN1XpAL99l3FhO-Kz5nWHtYhzjs, reason: not valid java name */
    public static /* synthetic */ void m12523$r8$lambda$rN1XpAL99l3FhOKz5nWHtYhzjs(ScrollSlidingTabStrip r0, ValueAnimator r1) {
        r0.lambda$checkLongPress$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$tIe8EjI6CDewOrmC1u1arpctE_Q(ScrollSlidingTabStrip r0, View r1) {
        r0.lambda$addEmojiTab$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$yrvSzBenowFrLwk2V5ROvaldYP0(ScrollSlidingTabStrip r0, View r1) {
        r0.lambda$addIconTab$0(r1);
    }

    /* renamed from: $r8$lambda$zAjP-TfjJw33Q7C6nbR9C2qWzf0, reason: not valid java name */
    public static /* synthetic */ void m12524$r8$lambda$zAjPTfjJw33Q7C6nbR9C2qWzf0(ScrollSlidingTabStrip r0, View r1) {
        r0.lambda$addStickerIconTab$1(r1);
    }

    /* renamed from: -$$Nest$fgetscrollByOnNextMeasure, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12525$$Nest$fgetscrollByOnNextMeasure(ScrollSlidingTabStrip r0) {
        return r0.scrollByOnNextMeasure;
    }

    /* renamed from: -$$Nest$fgetstickerTabExpandedWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12526$$Nest$fgetstickerTabExpandedWidth(ScrollSlidingTabStrip r0) {
        return r0.stickerTabExpandedWidth;
    }

    /* renamed from: -$$Nest$fgetstickerTabWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12527$$Nest$fgetstickerTabWidth(ScrollSlidingTabStrip r0) {
        return r0.stickerTabWidth;
    }

    /* renamed from: -$$Nest$fgettabsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m12528$$Nest$fgettabsContainer(ScrollSlidingTabStrip r0) {
        return r0.tabsContainer;
    }

    /* renamed from: -$$Nest$fputexpandOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12529$$Nest$fputexpandOffset(ScrollSlidingTabStrip r0, float r1) {
        r0.expandOffset = r1;
    }

    /* renamed from: -$$Nest$fputscrollByOnNextMeasure, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12530$$Nest$fputscrollByOnNextMeasure(ScrollSlidingTabStrip r0, int r1) {
        r0.scrollByOnNextMeasure = r1;
    }

    /* renamed from: -$$Nest$mcanSwap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12531$$Nest$mcanSwap(ScrollSlidingTabStrip r0, int r1) {
        return r0.canSwap(r1);
    }

    /* renamed from: -$$Nest$mgetTabSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12532$$Nest$mgetTabSize(ScrollSlidingTabStrip r0) {
        return r0.getTabSize();
    }

    static {
    }

    public ScrollSlidingTabStrip(Context r9, Theme.ResourcesProvider r10, boolean r11) {
        super(r9);
        this.imageReceiversPlayingNum = 1;
        this.type = Type.LINE;
        this.tabTypes = new HashMap();
        this.prevTypes = new HashMap();
        this.futureTabsPositions = new SparseArray();
        CubicBezierInterpolator r2 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.currentPositionAnimated = new AnimatedFloat(this, 350, r2);
        this.leftTabBounds = new RectF();
        this.rightTabBounds = new RectF();
        this.tabBounds = new RectF();
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.indicatorDrawable = new GradientDrawable();
        this.scrollOffset = AndroidUtilities.dp(33.0f);
        this.underlineHeight = AndroidUtilities.dp(2.0f);
        this.dividerPadding = AndroidUtilities.dp(12.0f);
        this.tabPadding = AndroidUtilities.dp(24.0f);
        this.lastScrollX = 0;
        this.currentPlayingImages = new SparseArray();
        this.currentPlayingImagesTmp = new SparseArray();
        this.longClickRunnable = new AnonymousClass1(this);
        this.expanded = false;
        this.stickerTabExpandedWidth = AndroidUtilities.dp(EXPANDED_WIDTH);
        this.stickerTabWidth = AndroidUtilities.dp(33.0f);
        this.scrollByOnNextMeasure = -1;
        this.selectorPaint = new Paint();
        this.showSelected = true;
        this.showSelectedAlpha = new AnimatedFloat(this, 350, r2);
        this.scrollRunnable = new AnonymousClass6(this);
        this.resourcesProvider = r10;
        this.isGlassDesign = r11;
        this.touchSlop = ViewConfiguration.get(r9).getScaledTouchSlop();
        setFillViewport(true);
        setWillNotDraw(false);
        setHorizontalScrollBarEnabled(false);
        AnonymousClass2 r102 = new AnonymousClass2(this, r9);
        this.tabsContainer = r102;
        r102.setOrientation(0);
        this.tabsContainer.setPadding(AndroidUtilities.dp(9.5f), 0, AndroidUtilities.dp(9.5f), 0);
        addView(this.tabsContainer, new FrameLayout.LayoutParams(-1, -1, 16));
        Paint r92 = new Paint();
        this.rectPaint = r92;
        r92.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(AndroidUtilities.dp(33.0f), -1);
        this.defaultExpandLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
    }

    private boolean canSwap(int r3) {
        if (this.dragEnabled == true) goto L5;
        return false;
    L5:
        if (r3 >= 0) goto L7;
    L17:
        return false;
    L7:
        if (r3 >= this.tabsContainer.getChildCount()) goto L17;
        View r2 = this.tabsContainer.getChildAt(r3);
        if ((r2 instanceof StickerTabView) == false) goto L17;
        StickerTabView r22 = (StickerTabView) r2;
        if (r22.type != 0) goto L17;
        if (r22.isChatSticker == true) goto L17;
        return true;
    }

    private void checkViewIndex(String r2, View r3, int r4) {
        HashMap<String, View> r0 = this.prevTypes;
        if (r0 == null) goto L5;
        r0.remove(r2);
    L5:
        this.futureTabsPositions.put(r4, r3);
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    private int getTabSize() {
        if (this.animateToExpanded == false) goto L5;
        float r0 = EXPANDED_WIDTH;
    L7:
        return AndroidUtilities.dp(r0);
    L5:
        r0 = 33.0f;
        goto L7
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$addEmojiTab$3(View r2) {
        this.delegate.onPageSelected(((Integer) r2.getTag(R.id.index_tag)).intValue());
    }

    private /* synthetic */ void lambda$addIconTab$0(View r2) {
        this.delegate.onPageSelected(((Integer) r2.getTag(R.id.index_tag)).intValue());
    }

    private /* synthetic */ void lambda$addStickerIconTab$1(View r2) {
        this.delegate.onPageSelected(((Integer) r2.getTag(R.id.index_tag)).intValue());
    }

    private /* synthetic */ void lambda$addStickerTab$2(View r2) {
        this.delegate.onPageSelected(((Integer) r2.getTag(R.id.index_tag)).intValue());
    }

    private /* synthetic */ void lambda$addStickerTab$4(View r2) {
        this.delegate.onPageSelected(((Integer) r2.getTag(R.id.index_tag)).intValue());
    }

    private /* synthetic */ void lambda$checkLongPress$6(ValueAnimator r1) {
        this.draggingViewOutProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidateOverlays();
    }

    private /* synthetic */ void lambda$expandStickers$5(boolean r5, float r6, ValueAnimator r7) {
        if (r5 == true) goto L10;
        float r52 = this.stickerTabWidth * this.tabsContainer.getChildCount();
        float r0 = (getScrollX() + r6) / (this.stickerTabExpandedWidth * this.tabsContainer.getChildCount());
        float r1 = (r52 - getMeasuredWidth()) / r52;
        if (r0 <= r1) goto L6;
        r0 = r1;
        r6 = 0.0f;
    L6:
        float r53 = r52 * r0;
        if ((r53 - r6) >= 0.0f) goto L9;
        r53 = r6;
    L9:
        this.expandOffset = (getScrollX() + r6) - r53;
    L10:
        this.expandProgress = ((Float) r7.getAnimatedValue()).floatValue();
        int r54 = 0;
    L11:
        int r62 = this.tabsContainer.getChildCount();
        LinearLayout r72 = this.tabsContainer;
        if (r54 >= r62) goto L14;
        r72.getChildAt(r54).invalidate();
        r54 = r54 + 1;
        goto L11
    L14:
        r72.invalidate();
        updatePosition();
    }

    private void scrollToChild(int r5) {
        if (this.tabCount != 0) goto L5;
        return;
    L5:
        if (this.tabsContainer.getChildAt(r5) == null) goto L23;
        int r0 = this.tabsContainer.getChildAt(r5).getLeft();
        if (r5 <= 0) goto L10;
        r0 = r0 - this.scrollOffset;
    L10:
        int r52 = getScrollX();
        if (r0 != this.lastScrollX) goto L13;
        return;
    L13:
        if (r0 >= r52) goto L17;
        this.lastScrollX = r0;
        smoothScrollTo(r0, 0);
        return;
    L17:
        if ((this.scrollOffset + r0) <= ((r52 + getWidth()) - (this.scrollOffset * 2))) goto L22;
        int r02 = (r0 - getWidth()) + (this.scrollOffset * 3);
        this.lastScrollX = r02;
        smoothScrollTo(r02, 0);
        return;
    L22:
        return;
    }

    private void startScroll(boolean r5) {
        this.scrollRight = r5;
        if (this.scrollStartTime > 0) goto L5;
        this.scrollStartTime = System.currentTimeMillis();
    L5:
        AndroidUtilities.runOnUIThread(this.scrollRunnable, 16);
    }

    private void stopScroll() {
        this.scrollStartTime = -1;
        AndroidUtilities.cancelRunOnUIThread(this.scrollRunnable);
    }

    public View addEmojiTab(int r7, Emoji.EmojiDrawable r8, TLRPC.Document r9) {
        String r72 = "tab" + r7;
        int r0 = this.tabCount;
        this.tabCount = r0 + 1;
        StickerTabView r1 = (StickerTabView) this.prevTypes.get(r72);
        boolean r2 = true;
        if (r1 == null) goto L5;
        checkViewIndex(r72, r1, r0);
    L6:
        r1.isChatSticker = false;
        r1.setTag(R.id.index_tag, Integer.valueOf(r0));
        r1.setTag(R.id.parent_tag, r8);
        r1.setTag(R.id.object_tag, r9);
        if (r0 == this.currentPosition) goto L10;
        r2 = false;
    L10:
        r1.setSelected(r2);
        this.tabTypes.put(r72, r1);
        return r1;
    L5:
        r1 = new StickerTabView(getContext(), 2);
        r1.setFocusable(true);
        r1.setOnClickListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda2(this));
        r1.setExpanded(this.expanded);
        r1.updateExpandProgress(this.expandProgress);
        this.tabsContainer.addView(r1, r0);
        goto L6
    }

    public FrameLayout addIconTab(int r6, Drawable r7) {
        String r62 = "tab" + r6;
        int r0 = this.tabCount;
        this.tabCount = r0 + 1;
        FrameLayout r1 = (FrameLayout) this.prevTypes.get(r62);
        boolean r2 = true;
        if (r1 == null) goto L5;
        checkViewIndex(r62, r1, r0);
    L6:
        r1.setTag(R.id.index_tag, Integer.valueOf(r0));
        if (r0 == this.currentPosition) goto L10;
        r2 = false;
    L10:
        r1.setSelected(r2);
        this.tabTypes.put(r62, r1);
        return r1;
    L5:
        r1 = new FrameLayout(getContext());
        ImageView r3 = new ImageView(getContext());
        r3.setImageDrawable(r7);
        r3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r1.addView(r3, LayoutHelper.createFrame(24, 24, 17));
        r1.setFocusable(true);
        r1.setOnClickListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda3(this));
        this.tabsContainer.addView(r1, r0);
        goto L6
    }

    public StickerTabView addStickerIconTab(int r6, Drawable r7) {
        String r62 = "tab" + r6;
        int r0 = this.tabCount;
        this.tabCount = r0 + 1;
        StickerTabView r1 = (StickerTabView) this.prevTypes.get(r62);
        boolean r2 = true;
        if (r1 == null) goto L5;
        checkViewIndex(r62, r1, r0);
    L6:
        r1.isChatSticker = false;
        r1.setTag(R.id.index_tag, Integer.valueOf(r0));
        if (r0 == this.currentPosition) goto L10;
        r2 = false;
    L10:
        r1.setSelected(r2);
        this.tabTypes.put(r62, r1);
        return r1;
    L5:
        r1 = new StickerTabView(getContext(), 1);
        r1.iconView.setImageDrawable(r7);
        r1.setFocusable(true);
        r1.setOnClickListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda5(this));
        r1.setExpanded(this.expanded);
        r1.updateExpandProgress(this.expandProgress);
        this.tabsContainer.addView(r1, r0);
        goto L6
    }

    public View addStickerTab(TLObject r8, TLRPC.Document r9, TLRPC.TL_messages_stickerSet r10) {
        StringBuilder r0 = new StringBuilder("set");
        if (r10 != null) goto L5;
        long r1 = r9.id;
    L6:
        r0.append(r1);
        String r02 = r0.toString();
        int r12 = this.tabCount;
        this.tabCount = r12 + 1;
        StickerTabView r2 = (StickerTabView) this.prevTypes.get(r02);
        boolean r3 = false;
        if (r2 == null) goto L9;
        checkViewIndex(r02, r2, r12);
    L10:
        r2.imageView.setLayerNum(this.imageReceiversPlayingNum);
        r2.isChatSticker = false;
        r2.setTag(r8);
        r2.setTag(R.id.index_tag, Integer.valueOf(r12));
        r2.setTag(R.id.parent_tag, r10);
        r2.setTag(R.id.object_tag, r9);
        if (r12 != this.currentPosition) goto L13;
        r3 = true;
    L13:
        r2.setSelected(r3);
        this.tabTypes.put(r02, r2);
        return r2;
    L9:
        r2 = new StickerTabView(getContext(), 0);
        r2.setFocusable(true);
        r2.setOnClickListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda1(this));
        r2.setExpanded(this.expanded);
        r2.updateExpandProgress(this.expandProgress);
        this.tabsContainer.addView(r2, r12);
        goto L10
    L5:
        r1 = r10.set.id;
        goto L6
    }

    public void addStickerTab(TLRPC.Chat r9) {
        String r0 = "chat" + r9.id;
        int r1 = this.tabCount;
        this.tabCount = r1 + 1;
        StickerTabView r2 = (StickerTabView) this.prevTypes.get(r0);
        boolean r3 = false;
        if (r2 == null) goto L5;
        checkViewIndex(r0, r2, r1);
    L6:
        r2.isChatSticker = true;
        r2.setTag(R.id.index_tag, Integer.valueOf(r1));
        if (r1 != this.currentPosition) goto L9;
        r3 = true;
    L9:
        r2.setSelected(r3);
        this.tabTypes.put(r0, r2);
        return;
    L5:
        r2 = new StickerTabView(getContext(), 0);
        r2.setFocusable(true);
        r2.setOnClickListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda0(this));
        this.tabsContainer.addView(r2, r1);
        r2.setRoundImage();
        AvatarDrawable r5 = new AvatarDrawable();
        r5.setTextSize(AndroidUtilities.dp(14.0f));
        r5.setInfo(UserConfig.selectedAccount, r9);
        BackupImageView r6 = r2.imageView;
        r6.setLayerNum(this.imageReceiversPlayingNum);
        r6.setForUserOrChat(r9, r5);
        r6.setAspectFit(true);
        r2.setExpanded(this.expanded);
        r2.updateExpandProgress(this.expandProgress);
        r2.textView.setText(r9.title);
        goto L6
    }

    public void beginUpdate(boolean r4) {
        this.prevTypes = this.tabTypes;
        this.tabTypes = new HashMap();
        this.futureTabsPositions.clear();
        this.tabCount = 0;
        if (r4 == false) goto L6;
        AutoTransition r42 = new AutoTransition();
        r42.setDuration(250);
        r42.setOrdering(0);
        r42.addTransition(new AnonymousClass3(this));
        TransitionManager.beginDelayedTransition(this.tabsContainer, r42);
        return;
    }

    @Override // android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        this.longClickRunning = false;
        AndroidUtilities.cancelRunOnUIThread(this.longClickRunnable);
    }

    public boolean checkLongPress(MotionEvent r8) {
        if (r8.getAction() != 0) goto L8;
        if (this.draggingView != null) goto L8;
        this.longClickRunning = true;
        AndroidUtilities.runOnUIThread(this.longClickRunnable, 500);
        this.pressedX = r8.getX();
        this.pressedY = r8.getY();
    L8:
        if (this.longClickRunning == false) goto L17;
        if (r8.getAction() != 2) goto L17;
        if (Math.abs(r8.getX() - this.pressedX) <= this.touchSlop) goto L14;
    L15:
        this.longClickRunning = false;
        AndroidUtilities.cancelRunOnUIThread(this.longClickRunnable);
        goto L17
    L14:
        if (Math.abs(r8.getY() - this.pressedY) > this.touchSlop) goto L15;
    L17:
        if (r8.getAction() != 2) goto L56;
        if (this.draggingView == null) goto L56;
        float r0 = getScrollX() + r8.getX();
        int r2 = ((int) Math.ceil(r0 / getTabSize())) - 1;
        int r4 = this.currentDragPosition;
        if (r2 == r4) goto L34;
        if (r2 >= r4) goto L29;
    L24:
        if (canSwap(r2) == true) goto L34;
        if (r2 == this.currentDragPosition) goto L34;
        r2 = r2 + 1;
    L29:
        if (canSwap(r2) == true) goto L34;
        if (r2 == this.currentDragPosition) goto L34;
        r2 = r2 - 1;
    L34:
        if (this.currentDragPosition != r2) goto L36;
    L46:
        this.dragDx = r0 - this.startDragFromX;
        this.draggindViewDxOnScreen = this.pressedX - r8.getX();
        float r82 = r8.getX();
        if (r82 >= (this.draggingView.getMeasuredWidth() / 2.0f)) goto L50;
        startScroll(false);
    L53:
        this.tabsContainer.invalidate();
        invalidateOverlays();
        return true;
    L50:
        if (r82 <= (getMeasuredWidth() - (this.draggingView.getMeasuredWidth() / 2.0f))) goto L52;
        startScroll(true);
        goto L53
    L52:
        stopScroll();
        goto L53
    L36:
        if (canSwap(r2) == false) goto L46;
        int r42 = 0;
    L39:
        if (r42 >= this.tabsContainer.getChildCount()) goto L45;
        if (r42 == this.currentDragPosition) goto L44;
        ((StickerTabView) this.tabsContainer.getChildAt(r42)).saveXPosition();
    L44:
        r42 = r42 + 1;
        goto L39
    L45:
        this.startDragFromX += (r2 - this.currentDragPosition) * getTabSize();
        this.currentDragPosition = r2;
        this.tabsContainer.removeView(this.draggingView);
        this.tabsContainer.addView(this.draggingView, this.currentDragPosition);
        invalidate();
    L56:
        if (r8.getAction() != 1) goto L58;
    L59:
        stopScroll();
        AndroidUtilities.cancelRunOnUIThread(this.longClickRunnable);
        if (this.draggingView == null) goto L68;
        int r83 = this.startDragFromPosition;
        int r02 = this.currentDragPosition;
        if (r83 == r02) goto L67;
        stickerSetPositionChanged(r83, r02);
        int r84 = 0;
    L65:
        if (r84 >= this.tabsContainer.getChildCount()) goto L67;
        this.tabsContainer.getChildAt(r84).setTag(R.id.index_tag, Integer.valueOf(r84));
        r84 = r84 + 1;
    L67:
        ValueAnimator r85 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r85.addUpdateListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda6(this));
        r85.addListener(new AnonymousClass5(this));
        r85.start();
    L68:
        this.longClickRunning = false;
        invalidateOverlays();
    L69:
        return false;
    L58:
        if (r8.getAction() != 3) goto L69;
        goto L59
    }

    public void commitUpdate() {
        HashMap<String, View> r0 = this.prevTypes;
        if (r0 == null) goto L9;
        Iterator<Map.Entry<String, View>> r02 = r0.entrySet().iterator();
    L6:
        if (r02.hasNext() == false) goto L8;
        Map.Entry<String, View> r1 = r02.next();
        this.tabsContainer.removeView(r1.getValue());
        goto L6
    L8:
        this.prevTypes.clear();
    L9:
        int r03 = this.futureTabsPositions.size();
        int r12 = 0;
    L10:
        SparseArray<View> r2 = this.futureTabsPositions;
        if (r12 >= r03) goto L16;
        int r22 = r2.keyAt(r12);
        View r3 = this.futureTabsPositions.valueAt(r12);
        if (this.tabsContainer.indexOfChild(r3) == r22) goto L15;
        this.tabsContainer.removeView(r3);
        this.tabsContainer.addView(r3, r22);
    L15:
        r12 = r12 + 1;
        goto L10
    L16:
        r2.clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r18) {
        float r1 = this.stickerTabWidth - this.stickerTabExpandedWidth;
        float r2 = this.expandOffset * (1.0f - this.expandProgress);
        int r5 = 0;
    L4:
        if (r5 >= this.tabsContainer.getChildCount()) goto L12;
        if ((this.tabsContainer.getChildAt(r5) instanceof StickerTabView) == false) goto L11;
        StickerTabView r6 = (StickerTabView) this.tabsContainer.getChildAt(r5);
        r6.animateIfPositionChanged(this);
        if (this.animateToExpanded == false) goto L10;
        r6.setTranslationX((((r5 * r1) * (1.0f - this.expandProgress)) + r2) + r6.dragOffset);
        goto L11
    L10:
        r6.setTranslationX(r6.dragOffset);
    L11:
        r5 = r5 + 1;
        goto L4
    L12:
        float r12 = getHeight();
        if (this.animateToExpanded == false) goto L15;
        r12 = getHeight() - (AndroidUtilities.dp(50.0f) * (1.0f - this.expandProgress));
    L15:
        float r9 = r12;
        AnimatedFloat r13 = this.showSelectedAlpha;
        if (this.showSelected == false) goto L18;
        float r22 = 1.0f;
    L19:
        float r14 = r13.set(r22);
        if (isInEditMode() == false) goto L22;
    L64:
        Canvas r52 = r18;
    L65:
        super.dispatchDraw(r18);
        if (isInEditMode() == false) goto L68;
        return;
    L68:
        if (this.tabCount != 0) goto L70;
        return;
    L70:
        if (this.underlineHeight <= 0) goto L79;
        this.rectPaint.setColor(this.underlineColor);
        r52.drawRect(0.0f, r9 - this.underlineHeight, this.tabsContainer.getWidth(), r9, this.rectPaint);
        return;
    L79:
        return;
    L22:
        if (this.tabCount == 0) goto L64;
        if (this.indicatorHeight < 0) goto L64;
        float r23 = this.currentPositionAnimated.set(this.currentPosition);
        double r62 = r23;
        int r8 = (int) Math.floor(r62);
        int r63 = (int) Math.ceil(r62);
        View r7 = null;
        if (r8 >= 0) goto L28;
    L31:
        View r10 = null;
    L32:
        if (r63 >= 0) goto L34;
    L37:
        float r11 = r9 / 2.0f;
        if (r10 == null) goto L49;
        if (r7 == null) goto L49;
        float r24 = r23 - r8;
        float r53 = AndroidUtilities.lerp((r10.getLeft() + r10.getTranslationX()) + (AndroidUtilities.lerp(AndroidUtilities.dp(33.0f), AndroidUtilities.dp(EXPANDED_WIDTH), this.expandProgress) / 2.0f), (r7.getLeft() + r7.getTranslationX()) + (AndroidUtilities.lerp(AndroidUtilities.dp(33.0f), AndroidUtilities.dp(EXPANDED_WIDTH), this.expandProgress) / 2.0f), r24);
        if ((r10 instanceof StickerTabView) == false) goto L43;
        float r82 = ((StickerTabView) r10).getTextWidth();
    L45:
        if ((r7 instanceof StickerTabView) == false) goto L47;
        float r72 = ((StickerTabView) r7).getTextWidth();
    L48:
        float r54 = AndroidUtilities.lerp(r82, r72, r24);
        float r25 = r53;
    L59:
        float r73 = AndroidUtilities.dp(30.0f);
        float r122 = (1.25f - ((Math.abs(0.5f - this.currentPositionAnimated.getTransitionProgressInterpolated()) * 0.25f) * 2.0f)) * r73;
        float r74 = r73 * (((Math.abs(0.5f - this.currentPositionAnimated.getTransitionProgressInterpolated()) * 0.1f) * 2.0f) + 0.9f);
        float r83 = CubicBezierInterpolator.EASE_IN.getInterpolation(this.expandProgress);
        float r55 = AndroidUtilities.lerp(r122, r54 + AndroidUtilities.dp(10.0f), r83);
        float r112 = r11 + AndroidUtilities.lerp(0, AndroidUtilities.dp(26.0f), r83);
        float r56 = r55 / 2.0f;
        float r75 = (r74 * AndroidUtilities.lerp(1.0f, 0.55f, r83)) / 2.0f;
        this.tabBounds.set(r25 - r56, r112 - r75, r25 + r56, r112 + r75);
        boolean r26 = this.isGlassDesign;
        Paint r3 = this.selectorPaint;
        if (r26 == false) goto L62;
        r3.setColor(getGlassIconColor(0.05f));
    L63:
        RectF r15 = this.tabBounds;
        r52 = r18;
        r52.drawRoundRect(r15, r15.height() / 2.0f, this.tabBounds.height() / 2.0f, this.selectorPaint);
        goto L65
    L62:
        r3.setColor(ColorUtils.setAlphaComponent(getThemedColor(Theme.key_chat_emojiPanelIcon), 46));
        this.selectorPaint.setAlpha((int) (r2.getAlpha() * r14));
        goto L63
    L47:
        r72 = 0.0f;
        goto L48
    L43:
        r82 = 0.0f;
    L49:
        if (r10 == null) goto L53;
        r25 = (r10.getLeft() + r10.getTranslationX()) + (AndroidUtilities.lerp(AndroidUtilities.dp(33.0f), AndroidUtilities.dp(EXPANDED_WIDTH), this.expandProgress) / 2.0f);
        if ((r10 instanceof StickerTabView) == false) goto L58;
        r54 = ((StickerTabView) r10).getTextWidth();
    L58:
        r54 = 0.0f;
        goto L59
    L53:
        if (r7 == null) goto L57;
        r25 = (r7.getLeft() + r7.getTranslationX()) + (AndroidUtilities.lerp(AndroidUtilities.dp(33.0f), AndroidUtilities.dp(EXPANDED_WIDTH), this.expandProgress) / 2.0f);
        if ((r7 instanceof StickerTabView) == false) goto L58;
        r54 = ((StickerTabView) r7).getTextWidth();
        goto L59
    L57:
        r25 = 0.0f;
        goto L58
    L34:
        if (r63 >= this.tabsContainer.getChildCount()) goto L37;
        r7 = this.tabsContainer.getChildAt(r63);
        goto L37
    L28:
        if (r8 >= this.tabsContainer.getChildCount()) goto L31;
        r10 = this.tabsContainer.getChildAt(r8);
        goto L32
    L18:
        r22 = 0.0f;
        goto L19
    }

    public void drawOverlays(Canvas r5) {
        if (this.draggingView == null) goto L9;
        r5.save();
        float r0 = this.draggindViewXOnScreen - this.draggindViewDxOnScreen;
        float r1 = this.draggingViewOutProgress;
        if (r1 <= 0.0f) goto L7;
        r0 = (r0 * (1.0f - r1)) + ((this.draggingView.getX() - getScrollX()) * this.draggingViewOutProgress);
    L7:
        r5.translate(r0, 0.0f);
        this.draggingView.draw(r5);
        r5.restore();
        return;
    }

    public void expandStickers(float r5, boolean r6) {
        if (this.expanded == r6) goto L30;
        this.expanded = r6;
        int r0 = 0;
        if (r6 == true) goto L7;
        fling(0);
    L7:
        ValueAnimator r1 = this.expandStickerAnimator;
        if (r1 == null) goto L10;
        r1.removeAllListeners();
        this.expandStickerAnimator.cancel();
    L10:
        float r12 = this.expandProgress;
        if (r6 == false) goto L13;
        float r2 = 1.0f;
    L14:
        ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{r12, r2});
        this.expandStickerAnimator = r22;
        r22.addUpdateListener(new ScrollSlidingTabStrip$$ExternalSyntheticLambda4(this, r6, r5));
        this.expandStickerAnimator.addListener(new AnonymousClass4(this, r6, r5));
        this.expandStickerAnimator.start();
        if (r6 == false) goto L24;
        this.animateToExpanded = true;
    L17:
        int r23 = this.tabsContainer.getChildCount();
        LinearLayout r3 = this.tabsContainer;
        if (r0 >= r23) goto L23;
        View r24 = r3.getChildAt(r0);
        if ((r24 instanceof StickerTabView) == false) goto L22;
        ((StickerTabView) r24).setExpanded(true);
    L22:
        r24.getLayoutParams().width = AndroidUtilities.dp(EXPANDED_WIDTH);
        r0 = r0 + 1;
        goto L17
    L23:
        r3.requestLayout();
        getLayoutParams().height = AndroidUtilities.dp(86.0f);
    L24:
        if (r6 == false) goto L31;
        float r02 = (this.stickerTabExpandedWidth * this.tabsContainer.getChildCount()) * ((getScrollX() + r5) / (this.stickerTabWidth * this.tabsContainer.getChildCount()));
        this.expandOffset = r02 - (getScrollX() + r5);
        this.scrollByOnNextMeasure = (int) (r02 - r5);
        return;
    L31:
        return;
    L13:
        r2 = 0.0f;
        goto L14
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public float getExpandedOffset() {
        if (this.animateToExpanded == true) goto L5;
        return 0.0f;
    L5:
        return AndroidUtilities.dp(50.0f) * this.expandProgress;
    }

    public Type getType() {
        return this.type;
    }

    public void invalidateOverlays() {
    }

    public void invalidateTabs() {
        int r0 = this.tabsContainer.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.tabsContainer.getChildAt(r1).invalidate();
        r1 = r1 + 1;
        goto L3
    }

    public boolean isDragging() {
        if (this.draggingView == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (checkLongPress(r2) == false) goto L5;
        return true;
    L5:
        if (super.onInterceptTouchEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        setImages();
        int r12 = this.scrollByOnNextMeasure;
        if (r12 < 0) goto L6;
        scrollTo(r12, 0);
        this.scrollByOnNextMeasure = -1;
        return;
    }

    public void onPageScrolled(int r7, int r8) {
        int r0 = this.currentPosition;
        if (r0 != r7) goto L6;
        return;
    L6:
        if (this.tabsContainer.getChildAt(r0) == null) goto L8;
        this.startAnimationPosition = r0.getLeft();
        this.positionAnimationProgress = 0.0f;
        this.animateFromPosition = true;
        this.lastAnimationTime = SystemClock.elapsedRealtime();
    L9:
        this.currentPosition = r7;
        if (r7 < this.tabsContainer.getChildCount()) goto L12;
        return;
    L12:
        this.positionAnimationProgress = 0.0f;
        int r02 = 0;
    L14:
        if (r02 >= this.tabsContainer.getChildCount()) goto L21;
        View r1 = this.tabsContainer.getChildAt(r02);
        if (r02 != r7) goto L18;
        boolean r4 = true;
    L19:
        r1.setSelected(r4);
        r02 = r02 + 1;
        goto L14
    L18:
        r4 = false;
        goto L19
    L21:
        if (this.expandStickerAnimator != null) goto L26;
        if (r8 != r7) goto L25;
        if (r7 <= 1) goto L25;
        scrollToChild(r7 - 1);
    L25:
        scrollToChild(r7);
    L26:
        invalidate();
        return;
    L8:
        this.animateFromPosition = false;
        goto L9
    }

    @Override // android.view.View
    public void onScrollChanged(int r1, int r2, int r3, int r4) {
        super.onScrollChanged(r1, r2, r3, r4);
        setImages();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (checkLongPress(r2) == false) goto L5;
        return true;
    L5:
        if (super.onTouchEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void selectTab(int r2) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= this.tabCount) goto L9;
        this.tabsContainer.getChildAt(r2).performClick();
        return;
    }

    public void setCurrentPosition(int r1) {
        this.currentPosition = r1;
    }

    public void setDelegate(ScrollSlidingTabStripDelegate r1) {
        this.delegate = r1;
    }

    public void setDragEnabled(boolean r1) {
        this.dragEnabled = r1;
    }

    public void setImageReceiversLayerNum(int r1) {
        this.imageReceiversPlayingNum = r1;
    }

    public void setImages() {
        float r2 = AndroidUtilities.dp(33.0f);
        float r1 = AndroidUtilities.dp(EXPANDED_WIDTH - 33.0f);
        float r3 = this.expandProgress;
        float r22 = r2 + (r1 * r3);
        if (this.animateToExpanded == false) goto L5;
        float r12 = this.expandOffset * (1.0f - r3);
    L6:
        int r13 = (int) (((getScrollX() - r12) - this.tabsContainer.getPaddingLeft()) / r22);
        int r23 = Math.min(this.tabsContainer.getChildCount(), (((int) Math.ceil(getMeasuredWidth() / r22)) + r13) + 1);
        if (this.animateToExpanded == false) goto L14;
        r13 = r13 - 2;
        r23 = r23 + 2;
        if (r13 >= 0) goto L12;
        r13 = 0;
    L12:
        if (r23 <= this.tabsContainer.getChildCount()) goto L14;
        r23 = this.tabsContainer.getChildCount();
    L14:
        this.currentPlayingImagesTmp.clear();
        int r32 = 0;
    L16:
        if (r32 >= this.currentPlayingImages.size()) goto L18;
        this.currentPlayingImagesTmp.put(this.currentPlayingImages.valueAt(r32).index, this.currentPlayingImages.valueAt(r32));
        r32 = r32 + 1;
        goto L16
    L18:
        this.currentPlayingImages.clear();
    L19:
        String r33 = null;
        if (r13 >= r23) goto L107;
        View r6 = this.tabsContainer.getChildAt(r13);
        if ((r6 instanceof StickerTabView) == false) goto L106;
        StickerTabView r7 = (StickerTabView) r6;
        if (r7.type != 2) goto L37;
        Object r62 = r7.getTag(R.id.parent_tag);
        Object r8 = r7.getTag(R.id.object_tag);
        if ((r62 instanceof Drawable) == false) goto L28;
        Drawable r63 = (Drawable) r62;
    L29:
        boolean r9 = r8 instanceof TLRPC.Document;
        BackupImageView r10 = r7.imageView;
        if (r9 == false) goto L36;
        ImageLocation r64 = ImageLocation.getForDocument((TLRPC.Document) r8);
        if (LiteMode.isEnabled(1) == true) goto L34;
        String r82 = "36_36_firstframe";
    L35:
        r10.setImage(r64, r82, null, null);
    L105:
        this.currentPlayingImages.put(r7.index, r7);
        this.currentPlayingImagesTmp.remove(r7.index);
        goto L106
    L34:
        r82 = "36_36_nolimit";
        goto L35
    L36:
        r10.setImageDrawable(r63);
        goto L105
    L28:
        r63 = null;
        goto L29
    L37:
        Object r83 = r6.getTag();
        Object r15 = r6.getTag(R.id.parent_tag);
        TLRPC.Document r65 = (TLRPC.Document) r6.getTag(R.id.object_tag);
        if ((r83 instanceof TLRPC.Document) == false) goto L45;
        if (r7.inited == true) goto L42;
        r7.svgThumb = DocumentObject.getSvgThumb((TLRPC.Document) r83, Theme.key_emptyListPlaceholder, 0.2f);
    L42:
        ImageLocation r84 = ImageLocation.getForDocument(r65);
        String r92 = null;
    L43:
        ImageLocation r132 = r84;
        if (r7.inited == false) goto L56;
    L59:
        if (r132 == null) goto L106;
        r7.inited = true;
        SvgHelper.SvgDrawable r14 = r7.svgThumb;
        BackupImageView r85 = r7.imageView;
        boolean r11 = LiteMode.isEnabled(1);
        if (r11 == true) goto L64;
        String r122 = "40_40_firstframe";
    L65:
        if (r92 == null) goto L67;
    L72:
        if (r92 == null) goto L88;
        if (r92.equalsIgnoreCase(RegisterSpec.PREFIX) == false) goto L88;
    L75:
        if (r92 != null) goto L84;
        if (r11 == true) goto L79;
        r85.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r65.thumbs, 90), r65), "40_40", r14, 0, r15);
    L102:
        if ((r15 instanceof TLRPC.TL_messages_stickerSet) == false) goto L104;
        r33 = ((TLRPC.TL_messages_stickerSet) r15).set.title;
    L104:
        r7.textView.setText(r33);
        goto L105
    L79:
        if (r14 == null) goto L81;
        r85.setImage(ImageLocation.getForDocument(r65), r122, r14, 0, r15);
        goto L102
    L81:
        Object r16 = r15;
        r85.setImage(ImageLocation.getForDocument(r65), r122, r132, null, 0, r16);
    L82:
        r15 = r16;
        goto L102
    L84:
        if (r14 == null) goto L86;
        r85.setImage(r132, r122, r14, 0, r15);
        goto L102
    L86:
        r16 = r15;
        r85.setImage(r132, r122, null, null, 0, r16);
    L88:
        if (r92 == null) goto L90;
    L91:
        if (r92 == null) goto L98;
        if (r92.equalsIgnoreCase("a") == false) goto L98;
    L94:
        if (r14 == null) goto L96;
        r85.setImage(r132, r122, r14, 0, r15);
        goto L102
    L96:
        r16 = r15;
        r85.setImage(r132, r122, r132, null, 0, r16);
    L98:
        if (r132.imageType != 1) goto L100;
        r85.setImage(r132, r122, "tgs", r14, r15);
        goto L102
    L100:
        r85.setImage(r132, r122, "webp", r14, r15);
        goto L102
    L90:
        if (MessageObject.isAnimatedStickerDocument(r65, true) == true) goto L94;
    L67:
        if (MessageObject.isVideoSticker(r65) == false) goto L72;
        ArrayList<TLRPC.PhotoSize> r5 = r65.thumbs;
        if (r5 == null) goto L72;
        if (r5.size() > 0) goto L75;
    L64:
        r122 = "40_40";
        goto L65
    L56:
        if (r7.svgThumb != null) goto L59;
        if (r65 == null) goto L59;
        r7.svgThumb = DocumentObject.getSvgThumb(r65, Theme.key_emptyListPlaceholder, 0.2f);
        goto L59
    L45:
        if ((r83 instanceof TLRPC.PhotoSize) == false) goto L106;
        TLRPC.PhotoSize r86 = (TLRPC.PhotoSize) r83;
        if ((r15 instanceof TLRPC.TL_messages_stickerSet) == false) goto L51;
        TLRPC.StickerSet r93 = ((TLRPC.TL_messages_stickerSet) r15).set;
        int r123 = r93.thumb_version;
        if (r7.inited == true) goto L52;
        ArrayList<TLRPC.PhotoSize> r94 = r93.thumbs;
        r7.svgThumb = DocumentObject.getSvgThumb(r94, Theme.key_emptyListPlaceholder, 0.2f, DocumentObject.containsPhotoSizeType(r94, RegisterSpec.PREFIX));
    L52:
        r92 = r86.type;
        r84 = ImageLocation.getForSticker(r86, r65, r123);
        goto L43
    L51:
        r123 = 0;
    L106:
        r13 = r13 + 1;
        goto L19
    L107:
        int r52 = 0;
    L109:
        if (r52 >= this.currentPlayingImagesTmp.size()) goto L114;
        if (this.currentPlayingImagesTmp.valueAt(r52) == this.draggingView) goto L113;
        this.currentPlayingImagesTmp.valueAt(r52).imageView.setImageDrawable(null);
    L113:
        r52 = r52 + 1;
        goto L109
    L114:
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    public void setIndicatorColor(int r1) {
        this.indicatorColor = r1;
        invalidate();
    }

    public void setIndicatorHeight(int r1) {
        this.indicatorHeight = r1;
        invalidate();
    }

    public void setShouldExpand(boolean r1) {
        this.shouldExpand = r1;
        requestLayout();
    }

    public void setType(Type r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (this.type == r6) goto L15;
        this.type = r6;
        int r62 = AnonymousClass7.$SwitchMap$org$telegram$ui$Components$ScrollSlidingTabStrip$Type[r6.ordinal()];
        if (r62 != 1) goto L8;
        this.indicatorDrawable.setCornerRadius(0.0f);
        return;
    L8:
        if (r62 != 2) goto L16;
        float r63 = AndroidUtilities.dpf2(3.0f);
        this.indicatorDrawable.setCornerRadii(new float[]{r63, r63, r63, r63, 0.0f, 0.0f, 0.0f, 0.0f});
        return;
    L16:
        return;
    }

    public void setUnderlineColor(int r1) {
        this.underlineColor = r1;
        invalidate();
    }

    public void setUnderlineColorResource(int r2) {
        this.underlineColor = getResources().getColor(r2);
        invalidate();
    }

    public void setUnderlineHeight(int r2) {
        if (this.underlineHeight == r2) goto L6;
        this.underlineHeight = r2;
        invalidate();
        return;
    }

    public void showSelected(boolean r1) {
        this.showSelected = r1;
        invalidate();
    }

    public void stickerSetPositionChanged(int r1, int r2) {
    }

    public void updatePosition() {
    }

    public void updateTabStyles() {
        int r0 = 0;
    L4:
        if (r0 >= this.tabCount) goto L10;
        View r1 = this.tabsContainer.getChildAt(r0);
        if (this.shouldExpand == false) goto L8;
        r1.setLayoutParams(this.defaultExpandLayoutParams);
    L9:
        r0 = r0 + 1;
        goto L4
    L8:
        r1.setLayoutParams(this.defaultTabLayoutParams);
        goto L9
    }
}
