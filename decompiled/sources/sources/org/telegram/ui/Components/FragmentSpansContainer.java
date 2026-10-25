package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLObject;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class FragmentSpansContainer extends ScrollView {
    public final ArrayList<GroupCreateSpan> allSpans;
    private final int currentAccount;
    private Delegate delegate;
    private int fieldY;
    private boolean ignoreScrollEvent;
    public final LongSparseArray<GroupCreateSpan> selectedContacts;
    private final SpansContainer spansContainer;
    private int visualHeight;

    /* loaded from: classes7.dex */
    public interface Delegate {
        void onAfterMeasure(int r1);
    }

    /* loaded from: classes7.dex */
    public class SpansContainer extends ViewGroup {
        private View addingSpan;
        private int animationIndex;
        private boolean animationStarted;
        private final ArrayList<Animator> animators;
        private int containerHeight;
        private AnimatorSet currentAnimation;
        private int maxTy;
        private final ArrayList<View> removingSpans;
        final /* synthetic */ FragmentSpansContainer this$0;

        /* renamed from: org.telegram.ui.Components.FragmentSpansContainer$SpansContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass1(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                NotificationCenter.getInstance(FragmentSpansContainer.m10812$$Nest$fgetcurrentAccount(this.this$1.this$0)).onAnimationFinish(SpansContainer.m10818$$Nest$fgetanimationIndex(this.this$1));
                this.this$1.requestLayout();
            }
        }

        /* renamed from: org.telegram.ui.Components.FragmentSpansContainer$SpansContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass2(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SpansContainer.m10820$$Nest$fputaddingSpan(this.this$1, null);
                SpansContainer.m10822$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m10821$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: org.telegram.ui.Components.FragmentSpansContainer$SpansContainer$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ GroupCreateSpan val$span;

            public AnonymousClass3(SpansContainer r1, GroupCreateSpan r2) {
                this.this$1 = r1;
                this.val$span = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.removeView(this.val$span);
                SpansContainer.m10819$$Nest$fgetremovingSpans(this.this$1).clear();
                SpansContainer.m10822$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m10821$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: org.telegram.ui.Components.FragmentSpansContainer$SpansContainer$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ ArrayList val$spans;

            public AnonymousClass4(SpansContainer r1, ArrayList r2) {
                this.this$1 = r1;
                this.val$spans = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                int r0 = 0;
            L3:
                int r1 = this.val$spans.size();
                SpansContainer r2 = this.this$1;
                if (r0 >= r1) goto L6;
                r2.removeView((View) this.val$spans.get(r0));
                r0 = r0 + 1;
                goto L3
            L6:
                SpansContainer.m10819$$Nest$fgetremovingSpans(r2).clear();
                SpansContainer.m10822$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m10821$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: -$$Nest$fgetanimationIndex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10818$$Nest$fgetanimationIndex(SpansContainer r0) {
            return r0.animationIndex;
        }

        /* renamed from: -$$Nest$fgetremovingSpans, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10819$$Nest$fgetremovingSpans(SpansContainer r0) {
            return r0.removingSpans;
        }

        /* renamed from: -$$Nest$fputaddingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10820$$Nest$fputaddingSpan(SpansContainer r0, View r1) {
            r0.addingSpan = r1;
        }

        /* renamed from: -$$Nest$fputanimationStarted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10821$$Nest$fputanimationStarted(SpansContainer r0, boolean r1) {
            r0.animationStarted = r1;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10822$$Nest$fputcurrentAnimation(SpansContainer r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        public SpansContainer(FragmentSpansContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.animators = new ArrayList();
            this.removingSpans = new ArrayList();
            this.animationIndex = -1;
        }

        public void addSpan(GroupCreateSpan r6) {
            this.this$0.allSpans.add(r6);
            if (r6.isFlag == true) goto L5;
            this.this$0.selectedContacts.put(r6.getUid(), r6);
        L5:
            AnimatorSet r0 = this.currentAnimation;
            if (r0 != null) goto L8;
        L10:
            this.animationStarted = false;
            AnimatorSet r02 = new AnimatorSet();
            this.currentAnimation = r02;
            r02.addListener(new AnonymousClass2(this));
            this.currentAnimation.setDuration(150);
            this.addingSpan = r6;
            this.animators.clear();
            ArrayList<Animator> r03 = this.animators;
            Property r4 = View.SCALE_X;
            r03.add(ObjectAnimator.ofFloat(this.addingSpan, r4, new float[]{0.01f, 1.0f}));
            ArrayList<Animator> r04 = this.animators;
            Property r42 = View.SCALE_Y;
            r04.add(ObjectAnimator.ofFloat(this.addingSpan, r42, new float[]{0.01f, 1.0f}));
            ArrayList<Animator> r05 = this.animators;
            Property r3 = View.ALPHA;
            r05.add(ObjectAnimator.ofFloat(this.addingSpan, r3, new float[]{0.0f, 1.0f}));
            addView(r6);
            return;
        L8:
            if (r0.isRunning() == false) goto L10;
            this.currentAnimation.setupEndValues();
            this.currentAnimation.cancel();
            goto L10
        }

        public void endAnimation() {
            AnimatorSet r0 = this.currentAnimation;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRunning() == false) goto L9;
            this.currentAnimation.setupEndValues();
            this.currentAnimation.cancel();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r22 = getChildCount();
            int r42 = 0;
        L3:
            if (r42 >= r22) goto L5;
            View r52 = getChildAt(r42);
            r52.layout(0, 0, r52.getMeasuredWidth(), r52.getMeasuredHeight());
            r42 = r42 + 1;
            goto L3
        }

        @Override // android.view.View
        public void onMeasure(int r14, int r15) {
            int r152 = getChildCount();
            int r142 = View.MeasureSpec.getSize(r14);
            int r0 = r142 - AndroidUtilities.dp(26.0f);
            int r2 = AndroidUtilities.dp(10.0f);
            int r1 = AndroidUtilities.dp(10.0f);
            int r4 = 0;
            if (this.animationStarted == true) goto L5;
            this.maxTy = 0;
        L5:
            int r3 = 0;
            int r5 = 0;
            int r6 = 0;
        L7:
            if (r3 >= r152) goto L39;
            View r8 = getChildAt(r3);
            if ((r8 instanceof GroupCreateSpan) == false) goto L37;
            r8.measure(View.MeasureSpec.makeMeasureSpec(r142, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
            boolean r9 = this.removingSpans.contains(r8);
            if (r9 == true) goto L17;
            if ((r8.getMeasuredWidth() + r5) <= r0) goto L17;
            r2 = r2 + (r8.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r5 = 0;
        L17:
            if ((r8.getMeasuredWidth() + r6) <= r0) goto L19;
            r1 = r1 + (r8.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r6 = 0;
        L19:
            int r11 = AndroidUtilities.dp(13.0f) + r5;
            if (this.animationStarted == true) goto L34;
            if (r9 == false) goto L24;
            r8.setTranslationX(AndroidUtilities.dp(13.0f) + r6);
            r8.setTranslationY(r1);
            goto L34
        L24:
            if (this.removingSpans.isEmpty() == true) goto L32;
            float r112 = r11;
            if (r8.getTranslationX() == r112) goto L28;
            this.animators.add(ObjectAnimator.ofFloat(r8, View.TRANSLATION_X, new float[]{r112}));
        L28:
            float r113 = r2;
            if (r8.getTranslationY() == r113) goto L31;
            this.animators.add(ObjectAnimator.ofFloat(r8, View.TRANSLATION_Y, new float[]{r113}));
        L31:
            this.maxTy = Math.max(this.maxTy, r2);
            goto L34
        L32:
            r8.setTranslationX(r11);
            r8.setTranslationY(r2);
            this.maxTy = Math.max(this.maxTy, r2);
        L34:
            if (r9 == true) goto L36;
            r5 = r5 + (r8.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L36:
            r6 = r6 + (r8.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L37:
            r3 = r3 + 1;
            goto L7
        L39:
            if (AndroidUtilities.isTablet() == false) goto L41;
            int r153 = AndroidUtilities.dp(372.0f) / 3;
        L43:
            if ((r0 - r5) >= r153) goto L46;
            r2 = r2 + AndroidUtilities.dp(40.0f);
        L46:
            if ((r0 - r6) >= r153) goto L49;
            r1 = r1 + AndroidUtilities.dp(40.0f);
        L49:
            if (this.animationStarted == true) goto L54;
            int r12 = r1 + AndroidUtilities.dp(42.0f);
            FragmentSpansContainer.m10815$$Nest$fputfieldY(this.this$0, r2);
            if (this.currentAnimation == null) goto L53;
            this.containerHeight = r2 + AndroidUtilities.dp(42.0f);
            this.currentAnimation.playTogether(this.animators);
            this.currentAnimation.addListener(new AnonymousClass1(this));
            this.animationIndex = NotificationCenter.getInstance(FragmentSpansContainer.m10812$$Nest$fgetcurrentAccount(this.this$0)).setAnimationInProgress(this.animationIndex, null);
            this.currentAnimation.start();
            this.animationStarted = true;
            goto L54
        L53:
            this.containerHeight = r12;
        L54:
            FragmentSpansContainer r154 = this.this$0;
            int r02 = this.maxTy;
            if (r02 <= 0) goto L57;
            r4 = r02 + AndroidUtilities.dp(40.0f);
        L57:
            FragmentSpansContainer.m10817$$Nest$fputvisualHeight(r154, r4);
            setMeasuredDimension(r142, this.containerHeight);
            if (FragmentSpansContainer.m10813$$Nest$fgetdelegate(this.this$0) == null) goto L64;
            FragmentSpansContainer.m10813$$Nest$fgetdelegate(this.this$0).onAfterMeasure(FragmentSpansContainer.m10814$$Nest$fgetvisualHeight(this.this$0));
            return;
        L64:
            return;
        L41:
            Point r155 = AndroidUtilities.displaySize;
            r153 = (Math.min(r155.x, r155.y) - AndroidUtilities.dp(158.0f)) / 3;
            goto L43
        }

        public void removeAllSpans(boolean r7) {
            FragmentSpansContainer.m10816$$Nest$fputignoreScrollEvent(this.this$0, true);
            ArrayList r0 = new ArrayList(this.this$0.allSpans);
            this.this$0.allSpans.clear();
            this.removingSpans.clear();
            this.removingSpans.addAll(r0);
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r2 >= r0.size()) goto L6;
            ((GroupCreateSpan) r0.get(r2)).setOnClickListener(null);
            r2 = r2 + 1;
            goto L4
        L6:
            endAnimation();
            if (r7 == false) goto L12;
            this.animationStarted = false;
            AnimatorSet r72 = new AnimatorSet();
            this.currentAnimation = r72;
            r72.addListener(new AnonymousClass4(this, r0));
            this.animators.clear();
        L10:
            if (r1 >= r0.size()) goto L17;
            GroupCreateSpan r73 = (GroupCreateSpan) r0.get(r1);
            this.animators.add(ObjectAnimator.ofFloat(r73, View.SCALE_X, new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(r73, View.SCALE_Y, new float[]{1.0f, 0.01f}));
            this.animators.add(ObjectAnimator.ofFloat(r73, View.ALPHA, new float[]{1.0f, 0.0f}));
            r1 = r1 + 1;
        L17:
            requestLayout();
            return;
        L12:
            int r74 = 0;
        L14:
            if (r74 >= r0.size()) goto L16;
            removeView((View) r0.get(r74));
            r74 = r74 + 1;
            goto L14
        L16:
            this.removingSpans.clear();
            this.currentAnimation = null;
            this.animationStarted = false;
            goto L17
        }

        public void removeSpan(GroupCreateSpan r5) {
            FragmentSpansContainer.m10816$$Nest$fputignoreScrollEvent(this.this$0, true);
            if (r5.isFlag == true) goto L5;
            this.this$0.selectedContacts.remove(r5.getUid());
        L5:
            this.this$0.allSpans.remove(r5);
            r5.setOnClickListener(null);
            AnimatorSet r0 = this.currentAnimation;
            if (r0 == null) goto L8;
            r0.setupEndValues();
            this.currentAnimation.cancel();
        L8:
            this.animationStarted = false;
            AnimatorSet r02 = new AnimatorSet();
            this.currentAnimation = r02;
            r02.addListener(new AnonymousClass3(this, r5));
            this.currentAnimation.setDuration(150);
            this.removingSpans.clear();
            this.removingSpans.add(r5);
            this.animators.clear();
            ArrayList<Animator> r03 = this.animators;
            Property r3 = View.SCALE_X;
            r03.add(ObjectAnimator.ofFloat(r5, r3, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r04 = this.animators;
            Property r32 = View.SCALE_Y;
            r04.add(ObjectAnimator.ofFloat(r5, r32, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r05 = this.animators;
            Property r2 = View.ALPHA;
            r05.add(ObjectAnimator.ofFloat(r5, r2, new float[]{1.0f, 0.0f}));
            requestLayout();
        }
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10812$$Nest$fgetcurrentAccount(FragmentSpansContainer r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m10813$$Nest$fgetdelegate(FragmentSpansContainer r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetvisualHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10814$$Nest$fgetvisualHeight(FragmentSpansContainer r0) {
        return r0.visualHeight;
    }

    /* renamed from: -$$Nest$fputfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10815$$Nest$fputfieldY(FragmentSpansContainer r0, int r1) {
        r0.fieldY = r1;
    }

    /* renamed from: -$$Nest$fputignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10816$$Nest$fputignoreScrollEvent(FragmentSpansContainer r0, boolean r1) {
        r0.ignoreScrollEvent = r1;
    }

    /* renamed from: -$$Nest$fputvisualHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10817$$Nest$fputvisualHeight(FragmentSpansContainer r0, int r1) {
        r0.visualHeight = r1;
    }

    public FragmentSpansContainer(Context r2, int r3) {
        super(r2);
        this.selectedContacts = new LongSparseArray();
        this.allSpans = new ArrayList();
        this.currentAccount = r3;
        SpansContainer r32 = new SpansContainer(this, r2);
        this.spansContainer = r32;
        setVerticalScrollBarEnabled(false);
        addView(r32, LayoutHelper.createFrame(-1, -2.0f));
    }

    public void addSpan(GroupCreateSpan r1) {
        this.spansContainer.addSpan(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        int r0 = r4.getAction();
        float r1 = this.visualHeight;
        float r2 = r4.getY();
        if (r0 != 0) goto L9;
        if (r2 <= r1) goto L9;
        return false;
    L9:
        return super.dispatchTouchEvent(r4);
    }

    public void endAnimation() {
        this.spansContainer.endAnimation();
    }

    public ViewGroup getSpansContainer() {
        return this.spansContainer;
    }

    public void removeAllSpans(boolean r1) {
        this.spansContainer.removeAllSpans(r1);
    }

    public void removeSpan(GroupCreateSpan r1) {
        this.spansContainer.removeSpan(r1);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View r4, Rect r5, boolean r6) {
        if (this.ignoreScrollEvent == false) goto L6;
        this.ignoreScrollEvent = false;
        return false;
    L6:
        r5.offset(r4.getLeft() - r4.getScrollX(), r4.getTop() - r4.getScrollY());
        r5.top += this.fieldY + AndroidUtilities.dp(20.0f);
        r5.bottom += this.fieldY + AndroidUtilities.dp(50.0f);
        return super.requestChildRectangleOnScreen(r4, r5, r6);
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }
}
