package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes3.dex */
public class RecyclerItemsEnterAnimator {
    boolean alwaysCheckItemsAlpha;
    public boolean animateAlphaProgressView;
    ArrayList<AnimatorSet> currentAnimations;
    HashSet<View> ignoreView;
    boolean invalidateAlpha;
    private final SparseArray<Float> listAlphaItems;
    private final RecyclerListView listView;
    ArrayList<ViewTreeObserver.OnPreDrawListener> preDrawListeners;

    /* renamed from: org.telegram.ui.Components.RecyclerItemsEnterAnimator$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ RecyclerItemsEnterAnimator this$0;
        final /* synthetic */ View val$finalProgressView;
        final /* synthetic */ RecyclerView.LayoutManager val$layoutManager;

        public AnonymousClass1(RecyclerItemsEnterAnimator r1, View r2, RecyclerView.LayoutManager r3) {
            this.this$0 = r1;
            this.val$finalProgressView = r2;
            this.val$layoutManager = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$finalProgressView.setAlpha(1.0f);
            this.val$layoutManager.stopIgnoringView(this.val$finalProgressView);
            this.this$0.ignoreView.remove(this.val$finalProgressView);
            RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(this.this$0).removeView(this.val$finalProgressView);
        }
    }

    /* renamed from: org.telegram.ui.Components.RecyclerItemsEnterAnimator$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ RecyclerItemsEnterAnimator this$0;
        final /* synthetic */ int val$finalFrom;
        final /* synthetic */ View val$finalProgressView;

        /* renamed from: org.telegram.ui.Components.RecyclerItemsEnterAnimator$2$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ int val$position;

            public AnonymousClass1(AnonymousClass2 r1, int r2) {
                this.this$1 = r1;
                this.val$position = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                RecyclerItemsEnterAnimator.m12436$$Nest$fgetlistAlphaItems(this.this$1.this$0).remove(this.val$position);
                RecyclerItemsEnterAnimator r1 = this.this$1.this$0;
                r1.invalidateAlpha = true;
                RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(r1).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.RecyclerItemsEnterAnimator$2$2, reason: invalid class name and collision with other inner class name */
        public class C00582 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ AnimatorSet val$animatorSet;

            public C00582(AnonymousClass2 r1, AnimatorSet r2) {
                this.this$1 = r1;
                this.val$animatorSet = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                this.this$1.this$0.currentAnimations.remove(this.val$animatorSet);
                if (this.this$1.this$0.currentAnimations.isEmpty() == false) goto L6;
                RecyclerItemsEnterAnimator.m12436$$Nest$fgetlistAlphaItems(this.this$1.this$0).clear();
                RecyclerItemsEnterAnimator r1 = this.this$1.this$0;
                r1.invalidateAlpha = true;
                RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(r1).invalidate();
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$07OKuAEvGn5trJ0ROWE07yElCQo(AnonymousClass2 r0, int r1, ValueAnimator r2) {
            r0.lambda$onPreDraw$0(r1, r2);
        }

        public AnonymousClass2(RecyclerItemsEnterAnimator r1, View r2, int r3) {
            this.this$0 = r1;
            this.val$finalProgressView = r2;
            this.val$finalFrom = r3;
        }

        private /* synthetic */ void lambda$onPreDraw$0(int r2, ValueAnimator r3) {
            Float r32 = (Float) r3.getAnimatedValue();
            RecyclerItemsEnterAnimator.m12436$$Nest$fgetlistAlphaItems(this.this$0).put(r2, r32);
            RecyclerItemsEnterAnimator r1 = this.this$0;
            r1.invalidateAlpha = true;
            RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(r1).invalidate();
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            this.this$0.preDrawListeners.remove(this);
            int r0 = RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(this.this$0).getChildCount();
            AnimatorSet r1 = new AnimatorSet();
            int r3 = 0;
        L3:
            RecyclerItemsEnterAnimator r4 = this.this$0;
            if (r3 >= r0) goto L13;
            View r42 = RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(r4).getChildAt(r3);
            int r5 = RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(r42);
            if (r42 == this.val$finalProgressView) goto L12;
            if (r5 < (this.val$finalFrom - 1)) goto L12;
            if (RecyclerItemsEnterAnimator.m12436$$Nest$fgetlistAlphaItems(this.this$0).get(r5, null) != null) goto L12;
            RecyclerItemsEnterAnimator.m12436$$Nest$fgetlistAlphaItems(this.this$0).put(r5, Float.valueOf(0.0f));
            RecyclerItemsEnterAnimator r6 = this.this$0;
            r6.invalidateAlpha = true;
            RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(r6).invalidate();
            ValueAnimator r62 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r62.addUpdateListener(new RecyclerItemsEnterAnimator$2$$ExternalSyntheticLambda0(this, r5));
            r62.addListener(new AnonymousClass1(this, r5));
            r62.setStartDelay((int) ((Math.min(RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(this.this$0).getMeasuredHeight(), Math.max(0, r42.getTop())) / RecyclerItemsEnterAnimator.m12437$$Nest$fgetlistView(this.this$0).getMeasuredHeight()) * 100.0f));
            r62.setDuration(200);
            r1.playTogether(new Animator[]{r62});
        L12:
            r3 = r3 + 1;
            goto L3
        L13:
            r4.currentAnimations.add(r1);
            r1.start();
            r1.addListener(new C00582(this, r1));
            return false;
        }
    }

    /* renamed from: -$$Nest$fgetlistAlphaItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray m12436$$Nest$fgetlistAlphaItems(RecyclerItemsEnterAnimator r0) {
        return r0.listAlphaItems;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m12437$$Nest$fgetlistView(RecyclerItemsEnterAnimator r0) {
        return r0.listView;
    }

    public RecyclerItemsEnterAnimator(RecyclerListView r2, boolean r3) {
        this.listAlphaItems = new SparseArray();
        this.ignoreView = new HashSet();
        this.animateAlphaProgressView = true;
        this.currentAnimations = new ArrayList();
        this.preDrawListeners = new ArrayList();
        this.listView = r2;
        this.alwaysCheckItemsAlpha = r3;
        r2.setItemsEnterAnimator(this);
    }

    public void cancel() {
        int r1 = 0;
        if (this.currentAnimations.isEmpty() == true) goto L8;
        ArrayList r0 = new ArrayList(this.currentAnimations);
        int r2 = 0;
    L6:
        if (r2 >= r0.size()) goto L8;
        ((AnimatorSet) r0.get(r2)).end();
        ((AnimatorSet) r0.get(r2)).cancel();
        r2 = r2 + 1;
    L8:
        this.currentAnimations.clear();
    L10:
        if (r1 >= this.preDrawListeners.size()) goto L12;
        this.listView.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListeners.get(r1));
        r1 = r1 + 1;
        goto L10
    L12:
        this.preDrawListeners.clear();
        this.listAlphaItems.clear();
        this.listView.invalidate();
        this.invalidateAlpha = true;
    }

    public void dispatchDraw() {
        if (this.invalidateAlpha == false) goto L5;
    L8:
        int r1 = 0;
    L10:
        if (r1 >= this.listView.getChildCount()) goto L20;
        View r2 = this.listView.getChildAt(r1);
        int r3 = this.listView.getChildAdapterPosition(r2);
        if (r3 < 0) goto L19;
        if (this.ignoreView.contains(r2) == true) goto L19;
        Float r32 = this.listAlphaItems.get(r3, null);
        if (r32 != null) goto L18;
        r2.setAlpha(1.0f);
        goto L19
    L18:
        r2.setAlpha(r32.floatValue());
    L19:
        r1 = r1 + 1;
        goto L10
    L20:
        this.invalidateAlpha = false;
        return;
    L5:
        if (this.alwaysCheckItemsAlpha == true) goto L8;
    }

    public View getProgressView() {
        int r0 = this.listView.getChildCount();
        View r1 = null;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        View r3 = this.listView.getChildAt(r2);
        if (this.listView.getChildAdapterPosition(r3) < 0) goto L9;
        if ((r3 instanceof FlickerLoadingView) == false) goto L9;
        r1 = r3;
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        return r1;
    }

    public void onDetached() {
        cancel();
    }

    public void showItemsAnimated(int r7) {
        View r0 = getProgressView();
        RecyclerView.LayoutManager r1 = this.listView.getLayoutManager();
        if (r0 == null) goto L10;
        if (r1 == null) goto L10;
        this.listView.removeView(r0);
        this.ignoreView.add(r0);
        this.listView.addView(r0);
        r1.ignoreView(r0);
        if (this.animateAlphaProgressView == false) goto L8;
        Animator r2 = ObjectAnimator.ofFloat(r0, View.ALPHA, new float[]{r0.getAlpha(), 0.0f});
    L9:
        r2.addListener(new AnonymousClass1(this, r0, r1));
        r2.start();
        r7 = r7 - 1;
        goto L10
    L8:
        r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    L10:
        AnonymousClass2 r12 = new AnonymousClass2(this, r0, r7);
        this.preDrawListeners.add(r12);
        this.listView.getViewTreeObserver().addOnPreDrawListener(r12);
    }
}
