package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.NotificationCenter;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugView$$ExternalSyntheticLambda10;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class ScrollSlidingTextTabStrip extends HorizontalScrollView implements Theme.Colorable {
    private int activeTextColorKey;
    private int allTextWidth;
    private int animateFromIndicatorWidth;
    private int animateFromIndicaxtorX;
    private int animateIndicatorStartWidth;
    private int animateIndicatorStartX;
    private int animateIndicatorToWidth;
    private int animateIndicatorToX;
    private boolean animatingIndicator;
    public long animationDuration;
    private float animationIdicatorProgress;
    private final Runnable animationRunnable;
    private float animationTime;
    BlurredBackgroundDrawable backgroundDrawable;
    public final Path clipPath;
    private int currentPosition;
    private ScrollSlidingTabStripDelegate delegate;
    private View dragging;
    private boolean fitsItems;
    private SparseIntArray idToPosition;
    private int indicatorWidth;
    private float indicatorWidthAnimationDx;
    private int indicatorX;
    private float indicatorXAnimationDx;
    private CubicBezierInterpolator interpolator;
    private boolean isOpen;
    private long lastAnimationTime;
    private final AnimatedFloat left;
    private final AnimatedFloat open;
    private SparseIntArray positionToId;
    private SparseIntArray positionToWidth;
    private int prevLayoutWidth;
    private SparseIntArray prevPositionToWidth;
    private final RectF prevRect;
    private int previousPosition;
    private final RectF rect;
    private final AnimatedFloat rectT;
    private boolean reordering;
    private final Theme.ResourcesProvider resourcesProvider;
    private int scrollingToChild;
    private int selectedTabId;
    private int selectorColorKey;
    private GradientDrawable selectorDrawable;
    private boolean setInitialTab;
    private int tabCount;
    private int tabLineColorKey;
    private final LinearLayout tabsContainer;
    private int unactiveTextColorKey;
    private boolean useMinimalWidth;
    private boolean useSameWidth;

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTextTabStrip$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ScrollSlidingTextTabStrip this$0;

        public AnonymousClass1(ScrollSlidingTextTabStrip r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ScrollSlidingTextTabStrip.m12534$$Nest$fgetanimatingIndicator(this.this$0) == false) goto L20;
            long r0 = SystemClock.elapsedRealtime() - ScrollSlidingTextTabStrip.m12542$$Nest$fgetlastAnimationTime(this.this$0);
            if (r0 <= 17) goto L8;
            r0 = 17;
        L8:
            ScrollSlidingTextTabStrip r2 = this.this$0;
            ScrollSlidingTextTabStrip.m12550$$Nest$fputanimationTime(r2, ScrollSlidingTextTabStrip.m12536$$Nest$fgetanimationTime(r2) + (r0 / this.this$0.animationDuration));
            ScrollSlidingTextTabStrip r02 = this.this$0;
            r02.setAnimationIdicatorProgress(ScrollSlidingTextTabStrip.m12541$$Nest$fgetinterpolator(r02).getInterpolation(ScrollSlidingTextTabStrip.m12536$$Nest$fgetanimationTime(this.this$0)));
            if (ScrollSlidingTextTabStrip.m12536$$Nest$fgetanimationTime(this.this$0) <= 1.0f) goto L11;
            ScrollSlidingTextTabStrip.m12550$$Nest$fputanimationTime(this.this$0, 1.0f);
        L11:
            float r03 = ScrollSlidingTextTabStrip.m12536$$Nest$fgetanimationTime(this.this$0);
            ScrollSlidingTextTabStrip r22 = this.this$0;
            if (r03 >= 1.0f) goto L15;
            AndroidUtilities.runOnUIThread(ScrollSlidingTextTabStrip.m12535$$Nest$fgetanimationRunnable(r22));
            return;
        L15:
            ScrollSlidingTextTabStrip.m12549$$Nest$fputanimatingIndicator(r22, false);
            this.this$0.setEnabled(true);
            if (ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0) == null) goto L19;
            ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0).onPageScrolled(1.0f);
            return;
        L19:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTextTabStrip$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        private float sx;
        private float sy;
        final /* synthetic */ ScrollSlidingTextTabStrip this$0;

        public static /* synthetic */ void $r8$lambda$ZDOzmN_LFOHNwHSA0yRFwRD7_nI(AnonymousClass2 r0, ValueAnimator r1) {
            r0.lambda$dispatchTouchEvent$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$ZSWjsLXOD97oI8ZSSoNJIoxcr3M(AnonymousClass2 r0, ValueAnimator r1) {
            r0.lambda$dispatchTouchEvent$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$gFzUWTEY6uAGrKBtlDMso_zJMHY(AnonymousClass2 r0, ValueAnimator r1) {
            r0.lambda$dispatchTouchEvent$1(r1);
        }

        public AnonymousClass2(ScrollSlidingTextTabStrip r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private View find(float r5, float r6) {
            int r0 = 0;
        L4:
            if (r0 >= getChildCount()) goto L15;
            View r1 = getChildAt(r0);
            if (r5 < r1.getX()) goto L14;
            if (r5 > (r1.getX() + r1.getWidth())) goto L14;
            if (r6 < r1.getY()) goto L14;
            if (r6 > (r1.getY() + r1.getHeight())) goto L14;
            return r1;
        L14:
            r0 = r0 + 1;
            goto L4
        L15:
            return null;
        }

        private int findPosition(float r6, int r7) {
            int r1 = 0;
            if (r6 >= 0.0f) goto L6;
            return 0;
        L6:
            if (r1 >= getChildCount()) goto L14;
            View r0 = getChildAt(r1);
            float r2 = r0.getLeft() + (r0.getWidth() / 2.0f);
            if (r6 < (r2 - (Math.min(r7, r0.getWidth()) / 2.0f))) goto L12;
            if (r6 > (r2 + (Math.min(r7, r0.getWidth()) / 2.0f))) goto L12;
            return r1;
        L12:
            r1 = r1 + 1;
            goto L6
        L14:
            return getChildCount() - 1;
        }

        private /* synthetic */ void lambda$dispatchTouchEvent$0(ValueAnimator r1) {
            invalidate();
            this.this$0.invalidate();
        }

        private /* synthetic */ void lambda$dispatchTouchEvent$1(ValueAnimator r1) {
            invalidate();
            this.this$0.invalidate();
        }

        private /* synthetic */ void lambda$dispatchTouchEvent$2(ValueAnimator r1) {
            invalidate();
            this.this$0.invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r18) {
            if (ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0) == null) goto L89;
            if (ScrollSlidingTextTabStrip.m12545$$Nest$fgetreordering(this.this$0) == false) goto L89;
            if (r18.getAction() != 0) goto L20;
            ScrollSlidingTextTabStrip.m12552$$Nest$fputdragging(this.this$0, find(r18.getX(), r18.getY()));
            if (ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0) != null) goto L12;
        L18:
            boolean r16 = false;
        L82:
            if (ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0) == null) goto L84;
        L87:
            return true;
        L84:
            if (super.dispatchTouchEvent(r18) == true) goto L87;
            return r16;
        L12:
            if (getParent() == null) goto L18;
            if (ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0).canReorder(ScrollSlidingTextTabStrip.m12543$$Nest$fgetpositionToId(this.this$0).get(indexOfChild(ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0)))) == true) goto L17;
            ScrollSlidingTextTabStrip.m12552$$Nest$fputdragging(this.this$0, null);
            return super.dispatchTouchEvent(r18);
        L17:
            this.sx = r18.getX();
            this.sy = r18.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
            goto L18
        L20:
            if (r18.getAction() == 2) goto L22;
            r16 = false;
            if (r18.getAction() != 1) goto L76;
            if (ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0) == null) goto L74;
            ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).animate().translationX(0.0f).translationY(0.0f).setDuration(320).setUpdateListener(new ScrollSlidingTextTabStrip$2$$ExternalSyntheticLambda1(this)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L74:
            ScrollSlidingTextTabStrip.m12552$$Nest$fputdragging(this.this$0, null);
            goto L82
        L76:
            if (r18.getAction() != 3) goto L82;
            if (ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0) == null) goto L80;
            ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).animate().translationX(0.0f).translationY(0.0f).setDuration(320).setUpdateListener(new ScrollSlidingTextTabStrip$2$$ExternalSyntheticLambda2(this)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L80:
            ScrollSlidingTextTabStrip.m12552$$Nest$fputdragging(this.this$0, null);
            goto L82
        L22:
            if (ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0) == null) goto L18;
            ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).setTranslationX(r18.getX() - this.sx);
            int r1 = indexOfChild(ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0));
            if (ScrollSlidingTextTabStrip.m12537$$Nest$fgetcurrentPosition(this.this$0) != r1) goto L27;
            this.this$0.invalidate();
        L27:
            if (r18.getX() >= AndroidUtilities.dp(16.0f)) goto L30;
            this.this$0.scrollBy(-AndroidUtilities.dp(1.0f), 0);
        L32:
            int r4 = findPosition(ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getX() + (ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getWidth() / 2.0f), ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getWidth());
            if (r4 == r1) goto L18;
            if (ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0).canReorder(ScrollSlidingTextTabStrip.m12543$$Nest$fgetpositionToId(this.this$0).get(r4)) == false) goto L18;
            View r5 = getChildAt(r4);
            int r9 = r5.getLeft();
            int r10 = ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getLeft();
            float r11 = this.sx;
            if (r9 <= r10) goto L39;
            this.sx = r11 + (((r5.getLeft() + r5.getWidth()) - ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getWidth()) - ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getLeft());
        L40:
            ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).setTranslationX(r18.getX() - this.sx);
            ViewGroup.LayoutParams r92 = ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getLayoutParams();
            ViewGroup.LayoutParams r102 = r5.getLayoutParams();
            int r112 = ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getLeft();
            int r12 = r5.getLeft();
            int r13 = ScrollSlidingTextTabStrip.m12537$$Nest$fgetcurrentPosition(this.this$0);
            ScrollSlidingTextTabStrip r14 = this.this$0;
            if (r13 != r1) goto L44;
            ScrollSlidingTextTabStrip.m12551$$Nest$fputcurrentPosition(r14, r4);
        L46:
            int r132 = ScrollSlidingTextTabStrip.m12544$$Nest$fgetpreviousPosition(this.this$0);
            ScrollSlidingTextTabStrip r142 = this.this$0;
            if (r132 != r1) goto L50;
            ScrollSlidingTextTabStrip.m12554$$Nest$fputpreviousPosition(r142, r4);
        L52:
            ScrollSlidingTextTabStrip.m12553$$Nest$fputprevLayoutWidth(this.this$0, -1);
            int r133 = ScrollSlidingTextTabStrip.m12543$$Nest$fgetpositionToId(this.this$0).get(r1);
            int r143 = ScrollSlidingTextTabStrip.m12543$$Nest$fgetpositionToId(this.this$0).get(r4);
            ScrollSlidingTextTabStrip.m12543$$Nest$fgetpositionToId(this.this$0).put(r1, r143);
            ScrollSlidingTextTabStrip.m12540$$Nest$fgetidToPosition(this.this$0).put(r143, r1);
            ScrollSlidingTextTabStrip.m12543$$Nest$fgetpositionToId(this.this$0).put(r4, r133);
            ScrollSlidingTextTabStrip.m12540$$Nest$fgetidToPosition(this.this$0).put(r133, r4);
            ScrollSlidingTextTabStrip.m12548$$Nest$fgettabsContainer(this.this$0).removeViewAt(Math.max(r1, r4));
            ScrollSlidingTextTabStrip.m12548$$Nest$fgettabsContainer(this.this$0).removeViewAt(Math.min(r1, r4));
            LinearLayout r134 = ScrollSlidingTextTabStrip.m12548$$Nest$fgettabsContainer(this.this$0);
            if (r1 >= r4) goto L55;
            View r144 = r5;
        L56:
            int r15 = Math.min(r1, r4);
            r16 = false;
            if (r1 >= r4) goto L59;
            ViewGroup.LayoutParams r2 = r102;
        L60:
            r134.addView(r144, r15, r2);
            LinearLayout r22 = ScrollSlidingTextTabStrip.m12548$$Nest$fgettabsContainer(this.this$0);
            if (r1 >= r4) goto L63;
            View r135 = ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0);
        L64:
            int r145 = Math.max(r1, r4);
            if (r1 < r4) goto L68;
            r92 = r102;
        L68:
            r22.addView(r135, r145, r92);
            r5.setTranslationX(r12 - r112);
            r5.animate().translationX(0.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setUpdateListener(new ScrollSlidingTextTabStrip$2$$ExternalSyntheticLambda0(this)).start();
            goto L82
        L63:
            r135 = r5;
            goto L64
        L59:
            r2 = r92;
            goto L60
        L55:
            r144 = ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0);
            goto L56
        L50:
            if (ScrollSlidingTextTabStrip.m12544$$Nest$fgetpreviousPosition(r142) != r4) goto L52;
            ScrollSlidingTextTabStrip.m12554$$Nest$fputpreviousPosition(this.this$0, r1);
            goto L52
        L44:
            if (ScrollSlidingTextTabStrip.m12537$$Nest$fgetcurrentPosition(r14) != r4) goto L46;
            ScrollSlidingTextTabStrip.m12551$$Nest$fputcurrentPosition(this.this$0, r1);
            goto L46
        L39:
            this.sx = r11 + (r5.getLeft() - ScrollSlidingTextTabStrip.m12539$$Nest$fgetdragging(this.this$0).getLeft());
            goto L40
        L30:
            if (r18.getX() < (getWidth() - AndroidUtilities.dp(16.0f))) goto L32;
            this.this$0.scrollBy(AndroidUtilities.dp(1.0f), 0);
        L89:
            return super.dispatchTouchEvent(r18);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (ScrollSlidingTextTabStrip.m12547$$Nest$fgetsetInitialTab(this.this$0) == true) goto L5;
            return;
        L5:
            if (ScrollSlidingTextTabStrip.m12540$$Nest$fgetidToPosition(this.this$0).indexOfKey(ScrollSlidingTextTabStrip.m12546$$Nest$fgetselectedTabId(this.this$0)) >= 0) goto L7;
            return;
        L7:
            if (ScrollSlidingTextTabStrip.m12548$$Nest$fgettabsContainer(this.this$0).getChildAt(ScrollSlidingTextTabStrip.m12540$$Nest$fgetidToPosition(this.this$0).get(ScrollSlidingTextTabStrip.m12546$$Nest$fgetselectedTabId(this.this$0))) == null) goto L12;
            ScrollSlidingTextTabStrip r12 = this.this$0;
            ScrollSlidingTextTabStrip.m12556$$Nest$mscrollToChild(r12, ScrollSlidingTextTabStrip.m12540$$Nest$fgetidToPosition(r12).get(ScrollSlidingTextTabStrip.m12546$$Nest$fgetselectedTabId(this.this$0)), false);
            ScrollSlidingTextTabStrip.m12555$$Nest$fputsetInitialTab(this.this$0, false);
            return;
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ScrollSlidingTextTabStrip$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatedEmojiSpan.TextViewEmojis {
        private AnimatedFloat reorderingAlpha;
        private Shaker shaker;
        final /* synthetic */ ScrollSlidingTextTabStrip this$0;
        final /* synthetic */ int val$id;

        public AnonymousClass3(ScrollSlidingTextTabStrip r2, Context r3, int r4) {
            this.this$0 = r2;
            this.val$id = r4;
            super(r3);
            this.reorderingAlpha = new AnimatedFloat(this, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan.TextViewEmojis, android.widget.TextView, android.view.View
        public void onDraw(Canvas r10) {
            float r0 = this.reorderingAlpha.set(ScrollSlidingTextTabStrip.m12545$$Nest$fgetreordering(this.this$0));
            if (ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0) == null) goto L17;
            if (ScrollSlidingTextTabStrip.m12538$$Nest$fgetdelegate(this.this$0).canReorder(this.val$id) == false) goto L17;
            if (r0 > 0.0f) goto L9;
        L12:
            super.onDraw(r10);
            if (r0 <= 0.0f) goto L24;
            r10.restore();
            return;
        L24:
            return;
        L9:
            if (this.shaker != null) goto L11;
            this.shaker = new Shaker(this);
        L11:
            r10.save();
            this.shaker.concat(r10, r0, getWidth() / 2.0f, getHeight() / 2.0f);
        L17:
            if (r0 <= 0.0f) goto L19;
            Canvas r3 = r10;
            r3.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (AndroidUtilities.lerp(1.0f, 0.5f, r0) * 255.0f));
        L20:
            super.onDraw(r3);
            if (r0 <= 0.0f) goto L25;
            r3.restore();
            return;
        L25:
            return;
        L19:
            r3 = r10;
            goto L20
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            if (ScrollSlidingTextTabStrip.m12546$$Nest$fgetselectedTabId(this.this$0) != this.val$id) goto L5;
            boolean r1 = true;
        L6:
            r2.setSelected(r1);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public interface ScrollSlidingTabStripDelegate {
        default boolean canReorder(int r1) {
            return false;
        }

        void onPageScrolled(float r1);

        void onPageSelected(int r1, boolean r2);

        default void onSamePageSelected() {
        }

        default boolean showOptions(int r1, View r2) {
            return false;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$50igx82KOVtUqZu0zVUQnGUlMkM(ScrollSlidingTextTabStrip r0, int r1, View r2) {
        return r0.lambda$addTextTab$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BsuSmzsGEjokZ_Ix7V9BW4R1Rf8(ScrollSlidingTextTabStrip r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$onLayout$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$RAg2TGrvTXnXiX2CVYFMqP4ZDtY(ScrollSlidingTextTabStrip r0, int r1, View r2) {
        r0.lambda$addTextTab$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetanimatingIndicator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12534$$Nest$fgetanimatingIndicator(ScrollSlidingTextTabStrip r0) {
        return r0.animatingIndicator;
    }

    /* renamed from: -$$Nest$fgetanimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12535$$Nest$fgetanimationRunnable(ScrollSlidingTextTabStrip r0) {
        return r0.animationRunnable;
    }

    /* renamed from: -$$Nest$fgetanimationTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12536$$Nest$fgetanimationTime(ScrollSlidingTextTabStrip r0) {
        return r0.animationTime;
    }

    /* renamed from: -$$Nest$fgetcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12537$$Nest$fgetcurrentPosition(ScrollSlidingTextTabStrip r0) {
        return r0.currentPosition;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTabStripDelegate m12538$$Nest$fgetdelegate(ScrollSlidingTextTabStrip r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdragging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12539$$Nest$fgetdragging(ScrollSlidingTextTabStrip r0) {
        return r0.dragging;
    }

    /* renamed from: -$$Nest$fgetidToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m12540$$Nest$fgetidToPosition(ScrollSlidingTextTabStrip r0) {
        return r0.idToPosition;
    }

    /* renamed from: -$$Nest$fgetinterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CubicBezierInterpolator m12541$$Nest$fgetinterpolator(ScrollSlidingTextTabStrip r0) {
        return r0.interpolator;
    }

    /* renamed from: -$$Nest$fgetlastAnimationTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12542$$Nest$fgetlastAnimationTime(ScrollSlidingTextTabStrip r2) {
        return r2.lastAnimationTime;
    }

    /* renamed from: -$$Nest$fgetpositionToId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m12543$$Nest$fgetpositionToId(ScrollSlidingTextTabStrip r0) {
        return r0.positionToId;
    }

    /* renamed from: -$$Nest$fgetpreviousPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12544$$Nest$fgetpreviousPosition(ScrollSlidingTextTabStrip r0) {
        return r0.previousPosition;
    }

    /* renamed from: -$$Nest$fgetreordering, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12545$$Nest$fgetreordering(ScrollSlidingTextTabStrip r0) {
        return r0.reordering;
    }

    /* renamed from: -$$Nest$fgetselectedTabId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12546$$Nest$fgetselectedTabId(ScrollSlidingTextTabStrip r0) {
        return r0.selectedTabId;
    }

    /* renamed from: -$$Nest$fgetsetInitialTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12547$$Nest$fgetsetInitialTab(ScrollSlidingTextTabStrip r0) {
        return r0.setInitialTab;
    }

    /* renamed from: -$$Nest$fgettabsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m12548$$Nest$fgettabsContainer(ScrollSlidingTextTabStrip r0) {
        return r0.tabsContainer;
    }

    /* renamed from: -$$Nest$fputanimatingIndicator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12549$$Nest$fputanimatingIndicator(ScrollSlidingTextTabStrip r0, boolean r1) {
        r0.animatingIndicator = r1;
    }

    /* renamed from: -$$Nest$fputanimationTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12550$$Nest$fputanimationTime(ScrollSlidingTextTabStrip r0, float r1) {
        r0.animationTime = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12551$$Nest$fputcurrentPosition(ScrollSlidingTextTabStrip r0, int r1) {
        r0.currentPosition = r1;
    }

    /* renamed from: -$$Nest$fputdragging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12552$$Nest$fputdragging(ScrollSlidingTextTabStrip r0, View r1) {
        r0.dragging = r1;
    }

    /* renamed from: -$$Nest$fputprevLayoutWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12553$$Nest$fputprevLayoutWidth(ScrollSlidingTextTabStrip r0, int r1) {
        r0.prevLayoutWidth = r1;
    }

    /* renamed from: -$$Nest$fputpreviousPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12554$$Nest$fputpreviousPosition(ScrollSlidingTextTabStrip r0, int r1) {
        r0.previousPosition = r1;
    }

    /* renamed from: -$$Nest$fputsetInitialTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12555$$Nest$fputsetInitialTab(ScrollSlidingTextTabStrip r0, boolean r1) {
        r0.setInitialTab = r1;
    }

    /* renamed from: -$$Nest$mscrollToChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12556$$Nest$mscrollToChild(ScrollSlidingTextTabStrip r0, int r1, boolean r2) {
        r0.scrollToChild(r1, r2);
    }

    public ScrollSlidingTextTabStrip(Context r2) {
        this(r2, null);
    }

    public ScrollSlidingTextTabStrip(Context r8, Theme.ResourcesProvider r9) {
        super(r8);
        this.selectedTabId = -1;
        this.scrollingToChild = -1;
        this.tabLineColorKey = Theme.key_actionBarTabLine;
        this.activeTextColorKey = Theme.key_actionBarTabActiveText;
        this.unactiveTextColorKey = Theme.key_actionBarTabUnactiveText;
        this.selectorColorKey = Theme.key_actionBarTabSelector;
        CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.interpolator = r1;
        this.positionToId = new SparseIntArray(5);
        this.idToPosition = new SparseIntArray(5);
        this.prevPositionToWidth = new SparseIntArray(5);
        this.positionToWidth = new SparseIntArray(5);
        this.animationDuration = 200;
        this.animationRunnable = new AnonymousClass1(this);
        this.clipPath = new Path();
        this.prevRect = new RectF();
        this.rect = new RectF();
        this.rectT = new AnimatedFloat(this, 420, r1);
        this.left = new AnimatedFloat(this, 420, r1);
        this.open = new AnimatedFloat(this, 420, r1);
        this.isOpen = true;
        this.resourcesProvider = r9;
        this.selectorDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        float r92 = AndroidUtilities.dpf2(14.0f);
        this.selectorDrawable.setCornerRadii(new float[]{r92, r92, r92, r92, r92, r92, r92, r92});
        setFillViewport(true);
        setWillNotDraw(false);
        setHorizontalScrollBarEnabled(false);
        AnonymousClass2 r93 = new AnonymousClass2(this, r8);
        this.tabsContainer = r93;
        r93.setOrientation(0);
        r93.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
        r93.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(r93);
        updateColors();
    }

    private void checkBoundsAndClipping() {
        float r0 = this.rectT.set(1.0f);
        this.rect.set(getPaddingLeft(), 0.0f, getMeasuredWidth() - getPaddingRight(), getMeasuredHeight());
        this.rect.inset(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
        RectF r2 = this.prevRect;
        if (r0 < 1.0f) goto L5;
        r2.set(this.rect);
    L6:
        float r02 = this.rect.height() / 2.0f;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.rect, r02, r02, Path.Direction.CW);
        BlurredBackgroundDrawable r1 = this.backgroundDrawable;
        if (r1 == null) goto L10;
        r1.setAlpha(255);
        this.backgroundDrawable.setBounds(((int) this.rect.left) - AndroidUtilities.dp(7.0f), 0, ((int) this.rect.right) + AndroidUtilities.dp(7.0f), getMeasuredHeight());
        this.backgroundDrawable.setRadius(r02);
        return;
    L10:
        return;
    L5:
        RectF r12 = this.rect;
        AndroidUtilities.lerp(r2, r12, r0, r12);
        goto L6
    }

    private int getChildWidth(TextView r1) {
        r1.getLayout();
        return r1.getMeasuredWidth();
    }

    private /* synthetic */ void lambda$addTextTab$0(int r2, View r3) {
        scrollTo(r2, this.tabsContainer.indexOfChild(r3), r3);
    }

    private /* synthetic */ boolean lambda$addTextTab$1(int r3, View r4) {
        if (this.reordering == false) goto L5;
        return false;
    L5:
        ScrollSlidingTabStripDelegate r2 = this.delegate;
        if (r2 != null) goto L8;
    L11:
        return false;
    L8:
        if (r2.showOptions(r3, r4) == false) goto L11;
        return true;
    }

    private /* synthetic */ void lambda$onLayout$2(int r1, int r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.indicatorXAnimationDx = r1 * r32;
        this.indicatorWidthAnimationDx = r2 * r32;
        this.tabsContainer.invalidate();
        invalidate();
    }

    private void scrollToChild(int r6, boolean r7) {
        if (this.tabCount != 0) goto L5;
        return;
    L5:
        if (this.scrollingToChild == r6) goto L26;
        this.scrollingToChild = r6;
        TextView r62 = (TextView) this.tabsContainer.getChildAt(r6);
        if (r62 == null) goto L27;
        int r0 = getScrollX();
        int r1 = r62.getLeft();
        int r63 = r62.getMeasuredWidth();
        if ((r1 - AndroidUtilities.dp(50.0f)) >= r0) goto L17;
        if (r7 == false) goto L15;
        smoothScrollTo(r1 - AndroidUtilities.dp(50.0f), 0);
        return;
    L15:
        scrollTo(r1 - AndroidUtilities.dp(50.0f), 0);
        return;
    L17:
        int r12 = r1 + r63;
        if ((AndroidUtilities.dp(21.0f) + r12) <= (r0 + getWidth())) goto L25;
        if (r7 == false) goto L22;
        smoothScrollTo(r12, 0);
        return;
    L22:
        scrollTo(r12, 0);
        return;
    L25:
        return;
    L27:
        return;
    }

    private void setAnimationProgressInernal(TextView r17, TextView r18, float r19) {
        if (r17 == null) goto L8;
        if (r18 == null) goto L9;
        int r3 = processColor(Theme.getColor(this.activeTextColorKey, this.resourcesProvider));
        int r4 = processColor(Theme.getColor(this.unactiveTextColorKey, this.resourcesProvider));
        int r5 = Color.red(r3);
        int r6 = Color.green(r3);
        int r7 = Color.blue(r3);
        int r32 = Color.alpha(r3);
        int r8 = Color.red(r4);
        int r9 = Color.green(r4);
        int r10 = Color.blue(r4);
        int r42 = Color.alpha(r4);
        r18.setTextColor(Color.argb((int) (r32 + ((r42 - r32) * r19)), (int) (r5 + ((r8 - r5) * r19)), (int) (r6 + ((r9 - r6) * r19)), (int) (r7 + ((r10 - r7) * r19))));
        r17.setTextColor(Color.argb((int) (r42 + ((r32 - r42) * r19)), (int) (r8 + ((r5 - r8) * r19)), (int) (r9 + ((r6 - r9) * r19)), (int) (r10 + ((r7 - r10) * r19))));
        this.indicatorX = (int) (this.animateIndicatorStartX + ((this.animateIndicatorToX - r1) * r19));
        this.indicatorWidth = (int) (this.animateIndicatorStartWidth + ((this.animateIndicatorToWidth - r1) * r19));
        invalidate();
        return;
    L9:
        return;
    }

    public void addTextTab(int r2, CharSequence r3) {
        addTextTab(r2, r3, null);
    }

    public void addTextTab(int r6, CharSequence r7, SparseArray<View> r8) {
        int r0 = this.tabCount;
        this.tabCount = r0 + 1;
        if (r0 == 0) goto L5;
    L7:
        this.positionToId.put(r0, r6);
        this.idToPosition.put(r6, r0);
        int r2 = this.selectedTabId;
        if (r2 == (-1)) goto L11;
        if (r2 != r6) goto L11;
        this.currentPosition = r0;
        this.prevLayoutWidth = 0;
    L11:
        if (r8 == null) goto L13;
        TextView r22 = (TextView) r8.get(r6);
        r8.delete(r6);
    L14:
        if (r22 != null) goto L16;
        r22 = new AnonymousClass3(this, getContext(), r6);
        r22.setGravity(17);
        r22.setTextAlignment(4);
        r22.setTextSize(1, 15.0f);
        r22.setSingleLine();
        r22.setMaxLines(1);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r22.setOnClickListener(new ScrollSlidingTextTabStrip$$ExternalSyntheticLambda1(this, r6));
        r22.setOnLongClickListener(new ScrollSlidingTextTabStrip$$ExternalSyntheticLambda2(this, r6));
        NotificationCenter.listenEmojiLoading(r22);
    L16:
        r22.setText(Emoji.replaceEmoji(r7, r22.getPaint().getFontMetricsInt(), false));
        int r62 = ((int) Math.ceil(HintView2.measureCorrectly(r6, r22.getPaint()))) + AndroidUtilities.dp(32.0f);
        this.tabsContainer.addView(r22, LayoutHelper.createLinear(0, -1));
        this.allTextWidth += r62;
        this.positionToWidth.put(r0, r62);
        updateColors();
        return;
    L13:
        r22 = null;
        goto L14
    L5:
        if (this.selectedTabId != (-1)) goto L7;
        this.selectedTabId = r6;
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        r5.save();
        if (this.backgroundDrawable != null) goto L5;
    L8:
        super.dispatchDraw(r5);
        r5.restore();
        return;
    L5:
        if (this.rectT.set(1.0f) >= 1.0f) goto L7;
        checkBoundsAndClipping();
    L7:
        r5.translate(getScrollX(), 0.0f);
        this.backgroundDrawable.setShadowAlpha(this.open.set(this.isOpen));
        this.backgroundDrawable.draw(r5);
        r5.clipPath(this.clipPath);
        r5.translate(-getScrollX(), 0.0f);
        r5.translate(this.left.set(0.0f), 0.0f);
        goto L8
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r7, View r8, long r9) {
        if (r8 != this.tabsContainer) goto L11;
        boolean r82 = super.drawChild(r7, r8, r9);
        int r92 = getMeasuredHeight();
        float r10 = this.indicatorX + this.indicatorXAnimationDx;
        float r0 = (this.indicatorWidth + r10) + this.indicatorWidthAnimationDx;
        View r1 = this.tabsContainer.getChildAt(this.currentPosition);
        if (this.reordering == false) goto L8;
        if (r1 == null) goto L8;
        r10 = r10 + r1.getTranslationX();
        r0 = r0 + r1.getTranslationX();
    L8:
        int r12 = this.selectorDrawable.getAlpha();
        this.selectorDrawable.setAlpha((int) (r12 * this.tabsContainer.getAlpha()));
        this.selectorDrawable.setBounds((getPaddingLeft() + ((int) r10)) + AndroidUtilities.dp(4.0f), getPaddingTop() + AndroidUtilities.dp(4.0f), (getPaddingLeft() + ((int) r0)) - AndroidUtilities.dp(4.0f), (r92 - getPaddingBottom()) - AndroidUtilities.dp(4.0f));
        this.selectorDrawable.draw(r7);
        this.selectorDrawable.setAlpha(r12);
        return r82;
    L11:
        return super.drawChild(r7, r8, r9);
    }

    public void finishAddingTabs() {
        int r0 = this.tabsContainer.getChildCount();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L28;
        TextView r3 = (TextView) this.tabsContainer.getChildAt(r2);
        if (this.currentPosition != r2) goto L7;
        int r4 = this.activeTextColorKey;
    L8:
        r3.setTag(Integer.valueOf(r4));
        if (this.currentPosition != r2) goto L11;
        int r42 = this.activeTextColorKey;
    L12:
        r3.setTextColor(processColor(Theme.getColor(r42, this.resourcesProvider)));
        if (this.useMinimalWidth == true) goto L15;
        if (r2 != 0) goto L27;
        int r43 = r3.getLayoutParams().width;
        ViewGroup.LayoutParams r5 = r3.getLayoutParams();
        if (r0 != 1) goto L23;
        int r6 = -2;
    L24:
        r5.width = r6;
        if (r43 == r3.getLayoutParams().width) goto L27;
        r3.requestLayout();
        goto L27
    L23:
        r6 = 0;
    L27:
        r2 = r2 + 1;
        goto L3
    L15:
        if (this.prevPositionToWidth.size() == this.positionToWidth.size()) goto L17;
    L18:
        r3.requestLayout();
        goto L27
    L17:
        if (this.prevPositionToWidth.get(r2) == this.positionToWidth.get(r2)) goto L27;
    L11:
        r42 = this.unactiveTextColorKey;
        goto L12
    L7:
        r4 = this.unactiveTextColorKey;
        goto L8
    L28:
        this.prevPositionToWidth.clear();
    L30:
        if (r1 >= this.positionToWidth.size()) goto L32;
        this.prevPositionToWidth.put(this.positionToWidth.keyAt(r1), this.positionToWidth.valueAt(r1));
        r1 = r1 + 1;
        goto L30
    }

    @Keep
    public float getAnimationIdicatorProgress() {
        return this.animationIdicatorProgress;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
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

    public Drawable getSelectorDrawable() {
        return this.selectorDrawable;
    }

    public ArrayList<Integer> getTabIds() {
        ArrayList<Integer> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.tabsContainer.getChildCount()) goto L6;
        r0.add(Integer.valueOf(this.positionToId.get(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public ViewGroup getTabsContainer() {
        return this.tabsContainer;
    }

    public int getTabsCount() {
        return this.tabCount;
    }

    public boolean hasTab(int r2) {
        if (this.idToPosition.get(r2, -1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isAnimatingIndicator() {
        return this.animatingIndicator;
    }

    public boolean isReordering() {
        return this.reordering;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        int r52 = r5 - r3;
        if (this.prevLayoutWidth == r52) goto L19;
        this.prevLayoutWidth = r52;
        this.scrollingToChild = -1;
        if (this.animatingIndicator == false) goto L9;
        AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
        this.animatingIndicator = false;
        setEnabled(true);
        ScrollSlidingTabStripDelegate r22 = this.delegate;
        if (r22 == null) goto L9;
        r22.onPageScrolled(1.0f);
    L9:
        TextView r23 = (TextView) this.tabsContainer.getChildAt(this.currentPosition);
        if (r23 == null) goto L19;
        this.indicatorWidth = getChildWidth(r23);
        int r42 = r23.getLeft();
        int r24 = r23.getMeasuredWidth();
        int r53 = this.indicatorWidth;
        int r43 = r42 + ((r24 - r53) / 2);
        this.indicatorX = r43;
        int r25 = this.animateFromIndicaxtorX;
        if (r25 <= 0) goto L19;
        int r0 = this.animateFromIndicatorWidth;
        if (r0 <= 0) goto L19;
        if (r25 != r43) goto L17;
        if (r0 != r53) goto L17;
    L18:
        this.animateFromIndicaxtorX = 0;
        this.animateFromIndicatorWidth = 0;
    L17:
        ValueAnimator r44 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r44.addUpdateListener(new ScrollSlidingTextTabStrip$$ExternalSyntheticLambda0(this, r25 - r43, r0 - r53));
        r44.setDuration(200);
        r44.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r44.start();
    L19:
        checkBoundsAndClipping();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r15, int r16) {
        int r0 = View.MeasureSpec.getSize(r15) - AndroidUtilities.dp(22.0f);
        int r1 = this.tabsContainer.getChildCount();
        boolean r2 = false;
        int r3 = 0;
    L3:
        LinearLayout r4 = this.tabsContainer;
        if (r3 >= r1) goto L24;
        View r42 = r4.getChildAt(r3);
        LinearLayout.LayoutParams r8 = (LinearLayout.LayoutParams) r42.getLayoutParams();
        float r9 = r8.weight;
        int r10 = r8.width;
        if (this.useMinimalWidth == false) goto L8;
        r8.weight = 0.0f;
        r8.width = this.positionToWidth.get(r3);
    L19:
        if (Math.abs(r9 - r8.weight) <= 0.001f) goto L21;
    L22:
        r42.setLayoutParams(r8);
        r42.requestLayout();
    L23:
        r3 = r3 + 1;
        goto L3
    L21:
        if (r10 == r8.width) goto L23;
    L8:
        int r11 = this.allTextWidth;
        if (r11 <= r0) goto L12;
        r8.weight = 0.0f;
        r8.width = -2;
        goto L19
    L12:
        if (this.useSameWidth == false) goto L14;
        r8.weight = 1.0f / r1;
        r8.width = 0;
        goto L19
    L14:
        if (r3 != 0) goto L17;
        if (r1 != 1) goto L17;
        r8.weight = 0.0f;
        r8.width = -2;
    L17:
        r8.weight = AndroidUtilities.lerp(1.0f / r1, (1.0f / r11) * this.positionToWidth.get(r3), 0.5f);
        r8.width = -2;
        goto L19
    L24:
        float r32 = r4.getWeightSum();
        if (r1 != 1) goto L27;
    L30:
        this.tabsContainer.setWeightSum(0.0f);
    L32:
        if (Math.abs(r32 - this.tabsContainer.getWeightSum()) <= 0.1f) goto L34;
        this.tabsContainer.requestLayout();
    L34:
        super.onMeasure(r15, r16);
        if (getMeasuredWidth() >= View.MeasureSpec.getSize(r15)) goto L37;
        r2 = true;
    L37:
        this.fitsItems = r2;
        checkBoundsAndClipping();
        return;
    L27:
        if (this.allTextWidth > r0) goto L30;
        this.tabsContainer.setWeightSum(1.0f);
        goto L32
    }

    public int processColor(int r1) {
        return r1;
    }

    public void recordIndicatorParams() {
        this.animateFromIndicaxtorX = this.indicatorX;
        this.animateFromIndicatorWidth = this.indicatorWidth;
    }

    public SparseArray<View> removeTabs() {
        SparseArray<View> r0 = new SparseArray();
        int r2 = 0;
    L4:
        if (r2 >= this.tabsContainer.getChildCount()) goto L6;
        r0.put(this.positionToId.get(r2), this.tabsContainer.getChildAt(r2));
        r2 = r2 + 1;
        goto L4
    L6:
        this.positionToId.clear();
        this.idToPosition.clear();
        this.prevPositionToWidth.clear();
        this.positionToWidth.clear();
        this.tabsContainer.removeAllViews();
        this.allTextWidth = 0;
        this.tabCount = 0;
        return r0;
    }

    public void scrollTo(int r3) {
        scrollTo(r3, this.idToPosition.get(r3), null);
    }

    public void scrollTo(int r6, int r7, View r8) {
        if (r7 < 0) goto L28;
        if (r8 == null) goto L5;
    L7:
        int r0 = this.currentPosition;
        if (r7 != r0) goto L14;
        ScrollSlidingTabStripDelegate r1 = this.delegate;
        if (r1 == null) goto L14;
        r1.onSamePageSelected();
        return;
    L14:
        if (r0 >= r7) goto L16;
        boolean r3 = true;
    L17:
        this.scrollingToChild = -1;
        this.previousPosition = r0;
        this.currentPosition = r7;
        this.selectedTabId = r6;
        if (this.animatingIndicator == false) goto L20;
        AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
        this.animatingIndicator = false;
    L20:
        this.animationTime = 0.0f;
        this.animatingIndicator = true;
        this.animateIndicatorStartX = this.indicatorX;
        this.animateIndicatorStartWidth = this.indicatorWidth;
        if (r8 == null) goto L23;
        TextView r82 = (TextView) r8;
        this.animateIndicatorToWidth = getChildWidth(r82);
        this.animateIndicatorToX = r82.getLeft() + ((r82.getMeasuredWidth() - this.animateIndicatorToWidth) / 2);
    L23:
        setEnabled(false);
        AndroidUtilities.runOnUIThread(this.animationRunnable, 16);
        ScrollSlidingTabStripDelegate r83 = this.delegate;
        if (r83 == null) goto L26;
        r83.onPageSelected(r6, r3);
    L26:
        scrollToChild(r7, true);
        return;
    L16:
        r3 = false;
        goto L17
    L5:
        if (this.animatingIndicator == false) goto L7;
        return;
    }

    public void selectTabWithId(int r8, float r9) {
        int r0 = this.idToPosition.get(r8, -1);
        if (r0 < 0) goto L26;
        int r1 = this.currentPosition;
        if (r1 != r0) goto L9;
        return;
    L9:
        if (r9 >= 0.0f) goto L12;
        r9 = 0.0f;
    L14:
        TextView r12 = (TextView) this.tabsContainer.getChildAt(r1);
        TextView r2 = (TextView) this.tabsContainer.getChildAt(r0);
        if (r12 == null) goto L22;
        if (r2 == null) goto L22;
        this.animateIndicatorStartWidth = getChildWidth(r12);
        this.animateIndicatorStartX = r12.getLeft() + ((r12.getMeasuredWidth() - this.animateIndicatorStartWidth) / 2);
        this.animateIndicatorToWidth = getChildWidth(r2);
        this.animateIndicatorToX = r2.getLeft() + ((r2.getMeasuredWidth() - this.animateIndicatorToWidth) / 2);
        setAnimationProgressInernal(r2, r12, r9);
        if (r9 < 1.0f) goto L20;
        r12.setTag(Integer.valueOf(this.unactiveTextColorKey));
        r2.setTag(Integer.valueOf(this.activeTextColorKey));
    L20:
        scrollToChild(this.tabsContainer.indexOfChild(r2), true);
    L22:
        if (r9 < 1.0f) goto L25;
        this.currentPosition = r0;
        this.selectedTabId = r8;
        return;
    L25:
        return;
    L12:
        if (r9 <= 1.0f) goto L14;
        r9 = 1.0f;
        goto L14
    }

    @Keep
    public void setAnimationIdicatorProgress(float r4) {
        this.animationIdicatorProgress = r4;
        TextView r0 = (TextView) this.tabsContainer.getChildAt(this.currentPosition);
        TextView r1 = (TextView) this.tabsContainer.getChildAt(this.previousPosition);
        if (r1 == null) goto L13;
        if (r0 == null) goto L15;
        setAnimationProgressInernal(r0, r1, r4);
        if (r4 < 1.0f) goto L9;
        r1.setTag(Integer.valueOf(this.unactiveTextColorKey));
        r0.setTag(Integer.valueOf(this.activeTextColorKey));
    L9:
        ScrollSlidingTabStripDelegate r3 = this.delegate;
        if (r3 == null) goto L14;
        r3.onPageScrolled(r4);
        return;
    L14:
        return;
    L15:
        return;
    }

    public void setBlurredBackground(BlurredBackgroundDrawable r1) {
        this.backgroundDrawable = r1;
        r1.setCallback(this);
    }

    public void setColors(int r1, int r2, int r3, int r4) {
        this.tabLineColorKey = r1;
        this.activeTextColorKey = r2;
        this.unactiveTextColorKey = r3;
        this.selectorColorKey = r4;
        updateColors();
    }

    public void setDelegate(ScrollSlidingTabStripDelegate r1) {
        this.delegate = r1;
    }

    @Override // android.view.View
    public void setEnabled(boolean r4) {
        super.setEnabled(r4);
        int r0 = this.tabsContainer.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.tabsContainer.getChildAt(r1).setEnabled(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void setInitialTabId(int r2) {
        this.setInitialTab = true;
        this.selectedTabId = r2;
        int r22 = this.idToPosition.get(r2);
        if (((TextView) this.tabsContainer.getChildAt(r22)) == null) goto L6;
        this.currentPosition = r22;
        this.prevLayoutWidth = 0;
        finishAddingTabs();
        requestLayout();
        return;
    }

    public void setOpen(boolean r6) {
        if (r6 != this.isOpen) goto L5;
        return;
    L5:
        this.isOpen = r6;
        float r0 = 6.0f;
        if (r6 == false) goto L8;
        float r62 = 0.0f;
    L9:
        int r63 = AndroidUtilities.dp(r62);
        int r2 = getPaddingTop();
        if (this.isOpen == false) goto L12;
        float r3 = 0.0f;
    L13:
        setPadding(r63, r2, AndroidUtilities.dp(r3), getPaddingBottom());
        invalidate();
        if (this.fitsItems == true) goto L20;
        this.prevRect.set(this.rect);
        this.rectT.force(0.0f);
        AnimatedFloat r64 = this.left;
        if (this.isOpen == true) goto L19;
        r0 = -6.0f;
    L19:
        r64.force(AndroidUtilities.dp(r0));
    L20:
        checkBoundsAndClipping();
        return;
    L12:
        r3 = 6.0f;
        goto L13
    L8:
        r62 = 6.0f;
        goto L9
    }

    public void setReordering(boolean r2) {
        if (this.reordering != r2) goto L5;
        return;
    L5:
        this.reordering = r2;
        AndroidUtilities.forEachViews(this.tabsContainer, new FloatingDebugView$$ExternalSyntheticLambda10());
    }

    public void setUseMinimalWidth(boolean r3) {
        this.useMinimalWidth = r3;
        LinearLayout r32 = this.tabsContainer;
        if (this.useMinimalWidth == false) goto L5;
        int r2 = -2;
    L6:
        r32.setLayoutParams(new FrameLayout.LayoutParams(r2, -1));
        return;
    L5:
        r2 = -1;
        goto L6
    }

    public void setUseSameWidth(boolean r1) {
        this.useSameWidth = r1;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        int r0 = this.tabsContainer.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        TextView r2 = (TextView) this.tabsContainer.getChildAt(r1);
        if (this.currentPosition != r1) goto L7;
        int r3 = this.activeTextColorKey;
    L8:
        r2.setTextColor(processColor(Theme.getColor(r3, this.resourcesProvider)));
        r1 = r1 + 1;
        goto L3
    L7:
        r3 = this.unactiveTextColorKey;
        goto L8
    L9:
        this.selectorDrawable.setColor(Theme.multAlpha(processColor(Theme.getColor(this.activeTextColorKey, this.resourcesProvider)), 0.15f));
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.backgroundDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
