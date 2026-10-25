package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.Cells.IMessageCell;
import org.telegram.ui.Components.RecyclerListView;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class RecyclerAnimationScrollHelper {
    private AnimationCallback animationCallback;
    private ValueAnimator animator;
    public boolean forceUseStableId;
    public boolean isDialogs;
    private LinearLayoutManager layoutManager;
    private HashMap<Long, View> oldStableIds;
    public SparseArray<View> positionToOldView;
    private RecyclerListView recyclerView;
    private int scrollDirection;
    private ScrollListener scrollListener;

    /* renamed from: org.telegram.ui.Components.RecyclerAnimationScrollHelper$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements View.OnLayoutChangeListener {
        final /* synthetic */ RecyclerAnimationScrollHelper this$0;
        final /* synthetic */ RecyclerView.Adapter val$adapter;
        final /* synthetic */ AnimatableAdapter val$finalAnimatableAdapter;
        final /* synthetic */ ArrayList val$oldViews;
        final /* synthetic */ boolean val$scrollDown;

        /* renamed from: org.telegram.ui.Components.RecyclerAnimationScrollHelper$1$1, reason: invalid class name and collision with other inner class name */
        public class C00571 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ ArrayList val$incomingViews;

            public C00571(AnonymousClass1 r1, ArrayList r2) {
                this.this$1 = r1;
                this.val$incomingViews = r2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r8) {
                if (RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$1.this$0) != null) goto L5;
                return;
            L5:
                RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).fastScrollAnimationRunning = false;
                ArrayList r82 = this.this$1.val$oldViews;
                int r1 = r82.size();
                int r2 = 0;
            L7:
                if (r2 >= r1) goto L14;
                Object r5 = r82.get(r2);
                r2 = r2 + 1;
                View r52 = (View) r5;
                if ((r52 instanceof IMessageCell) == false) goto L11;
                ((IMessageCell) r52).setAnimationRunning(false, true);
            L11:
                r52.setTranslationY(0.0f);
                RecyclerAnimationScrollHelper.m12431$$Nest$fgetlayoutManager(this.this$1.this$0).stopIgnoringView(r52);
                RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).removeView(r52);
                if (RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$1.this$0) == null) goto L7;
                RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$1.this$0).ignoreView(r52, false);
                RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$1.this$0).recycleView(r52);
                goto L7
            L14:
                RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).setScrollEnabled(true);
                RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).setVerticalScrollBarEnabled(true);
                if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L17;
            L25:
                int r83 = RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).getChildCount();
                int r12 = 0;
            L26:
                if (r12 >= r83) goto L31;
                View r22 = RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).getChildAt(r12);
                if ((r22 instanceof IMessageCell) == false) goto L30;
                ((IMessageCell) r22).setAnimationRunning(false, false);
            L30:
                r22.setTranslationY(0.0f);
                r12 = r12 + 1;
                goto L26
            L31:
                ArrayList r84 = this.val$incomingViews;
                int r13 = r84.size();
                int r23 = 0;
            L32:
                if (r23 >= r13) goto L37;
                Object r4 = r84.get(r23);
                r23 = r23 + 1;
                View r42 = (View) r4;
                if ((r42 instanceof IMessageCell) == false) goto L36;
                ((IMessageCell) r42).setAnimationRunning(false, false);
            L36:
                r42.setTranslationY(0.0f);
                goto L32
            L37:
                AnimatableAdapter r85 = this.this$1.val$finalAnimatableAdapter;
                if (r85 == null) goto L41;
                r85.onAnimationEnd();
            L41:
                if (RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$1.this$0) == null) goto L43;
                RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$1.this$0).onEndAnimation();
            L43:
                this.this$1.this$0.positionToOldView.clear();
                RecyclerAnimationScrollHelper.m12435$$Nest$fputanimator(this.this$1.this$0, null);
                return;
            L17:
                if (RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).mChildHelper.getChildCount() == RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).getChildCount()) goto L19;
                GlShader$$ExternalSyntheticBUOutline1.m("views count in child helper must be quals views count in recycler view");
                return;
            L19:
                if (RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$1.this$0).mChildHelper.getHiddenChildCount() == 0) goto L25;
                GlShader$$ExternalSyntheticBUOutline1.m("hidden child count must be 0");
            }
        }

        public static /* synthetic */ void $r8$lambda$9T1ZXwceH73WmQ9KucH0H5e2P98(AnonymousClass1 r0, ArrayList r1, boolean r2, int r3, int r4, ArrayList r5, ValueAnimator r6) {
            r0.lambda$onLayoutChange$0(r1, r2, r3, r4, r5, r6);
        }

        public AnonymousClass1(RecyclerAnimationScrollHelper r1, RecyclerView.Adapter r2, ArrayList r3, boolean r4, AnimatableAdapter r5) {
            this.this$0 = r1;
            this.val$adapter = r2;
            this.val$oldViews = r3;
            this.val$scrollDown = r4;
            this.val$finalAnimatableAdapter = r5;
        }

        private /* synthetic */ void lambda$onLayoutChange$0(ArrayList r8, boolean r9, int r10, int r11, ArrayList r12, ValueAnimator r13) {
            float r132 = ((Float) r13.getAnimatedValue()).floatValue();
            int r0 = r8.size();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L13;
            View r3 = (View) r8.get(r2);
            float r4 = r3.getY();
            if ((r3.getY() + r3.getMeasuredHeight()) < 0.0f) goto L12;
            if (r4 > RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getMeasuredHeight()) goto L12;
            if (r9 == false) goto L11;
            r3.setTranslationY((-r10) * r132);
            goto L12
        L11:
            r3.setTranslationY(r10 * r132);
        L12:
            r2 = r2 + 1;
            goto L3
        L13:
            int r112 = r11 - RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getPaddingBottom();
            int r82 = r12.size();
        L14:
            if (r1 >= r82) goto L20;
            View r02 = (View) r12.get(r1);
            if (r9 == false) goto L18;
            r02.setTranslationY((r10 * (1.0f - r132)) + r112);
        L19:
            r1 = r1 + 1;
            goto L14
        L18:
            r02.setTranslationY((-r10) * (1.0f - r132));
            goto L19
        L20:
            RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).invalidate();
            if (RecyclerAnimationScrollHelper.m12434$$Nest$fgetscrollListener(this.this$0) == null) goto L32;
            RecyclerAnimationScrollHelper.m12434$$Nest$fgetscrollListener(this.this$0).onScroll();
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25) {
            RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).removeOnLayoutChangeListener(this);
            ArrayList r1 = new ArrayList();
            RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).stopScroll();
            int r2 = RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getChildCount();
            int r4 = 0;
            int r5 = 0;
            int r6 = 0;
            int r7 = 0;
            boolean r8 = false;
        L3:
            RecyclerAnimationScrollHelper r9 = this.this$0;
            if (r4 >= r2) goto L35;
            View r92 = RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(r9).getChildAt(r4);
            r1.add(r92);
            if (r92.getTop() >= r5) goto L9;
            r5 = r92.getTop();
        L9:
            if (r92.getBottom() <= r6) goto L12;
            r6 = r92.getBottom();
        L12:
            if ((r92 instanceof IMessageCell) == false) goto L14;
            ((IMessageCell) r92).setAnimationRunning(true, false);
        L14:
            RecyclerView.Adapter r11 = this.val$adapter;
            if (r11 == null) goto L34;
            if (r11.hasStableIds() == false) goto L19;
        L20:
            long r112 = this.val$adapter.getItemId(RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getChildAdapterPosition(r92));
            if (RecyclerAnimationScrollHelper.m12432$$Nest$fgetoldStableIds(this.this$0).containsKey(Long.valueOf(r112)) == false) goto L34;
            View r113 = (View) RecyclerAnimationScrollHelper.m12432$$Nest$fgetoldStableIds(this.this$0).get(Long.valueOf(r112));
            if (r113 == 0) goto L34;
            if ((r113 instanceof IMessageCell) == false) goto L27;
            ((IMessageCell) r113).setAnimationRunning(false, false);
        L27:
            this.val$oldViews.remove(r113);
            if (RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$0) == null) goto L30;
            RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$0).recycleView(r113);
        L30:
            int r82 = r92.getTop() - r113.getTop();
            if (r82 == 0) goto L33;
            r7 = r82;
        L33:
            r8 = true;
            goto L34
        L19:
            if (this.this$0.forceUseStableId == true) goto L20;
        L34:
            r4 = r4 + 1;
            goto L3
        L35:
            RecyclerAnimationScrollHelper.m12432$$Nest$fgetoldStableIds(r9).clear();
            ArrayList r26 = this.val$oldViews;
            int r42 = r26.size();
            int r12 = 0;
            int r13 = 0;
            int r114 = Integer.MAX_VALUE;
        L36:
            if (r13 >= r42) goto L51;
            Object r14 = r26.get(r13);
            r13 = r13 + 1;
            View r142 = (View) r14;
            int r15 = r142.getBottom();
            int r3 = r142.getTop();
            if (r15 <= r12) goto L40;
            r12 = r15;
        L40:
            if (r3 >= r114) goto L43;
            r114 = r3;
        L43:
            if (r142.getParent() != null) goto L48;
            RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).addView(r142);
            RecyclerAnimationScrollHelper.m12431$$Nest$fgetlayoutManager(this.this$0).ignoreView(r142);
            if (RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$0) == null) goto L48;
            RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$0).ignoreView(r142, true);
        L48:
            if ((r142 instanceof IMessageCell) == false) goto L36;
            ((IMessageCell) r142).setAnimationRunning(true, true);
            goto L36
        L51:
            if (r114 != Integer.MAX_VALUE) goto L53;
            int r32 = 0;
        L55:
            if (RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$0) == null) goto L58;
            RecyclerAnimationScrollHelper.m12429$$Nest$fgetanimationCallback(this.this$0).onPreAnimation();
        L58:
            if (this.val$oldViews.isEmpty() == false) goto L61;
            int r27 = Math.abs(r7);
        L69:
            int r33 = RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getPaddingBottom();
            if (RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0) == null) goto L72;
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).removeAllListeners();
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).cancel();
        L72:
            RecyclerAnimationScrollHelper.m12435$$Nest$fputanimator(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).addUpdateListener(new RecyclerAnimationScrollHelper$1$$ExternalSyntheticLambda0(this, this.val$oldViews, this.val$scrollDown, r27, r33, r1));
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).addListener(new C00571(this, r1));
            RecyclerAnimationScrollHelper r16 = this.this$0;
            long r93 = 300;
            if (r16.isDialogs == false) goto L81;
            if (r8 == false) goto L76;
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(r16).setDuration(150);
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).setInterpolator(CubicBezierInterpolator.EASE_OUT);
        L89:
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).start();
            return;
        L76:
            long r110 = (long) (((r27 / RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(r16).getMeasuredHeight()) + 1.0f) * 200.0f);
            if (r110 < 300) goto L80;
            r93 = r110;
        L80:
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).setDuration(Math.min(r93, 1300));
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            goto L89
        L81:
            if (r8 == false) goto L83;
            long r111 = 600;
        L88:
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).setDuration(r111);
            RecyclerAnimationScrollHelper.m12430$$Nest$fgetanimator(this.this$0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            goto L89
        L83:
            long r115 = (long) (((r27 / RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(r16).getMeasuredHeight()) + 1.0f) * 200.0f);
            if (r115 < 300) goto L87;
            r93 = r115;
        L87:
            r111 = Math.min(r93, 1300);
            goto L88
        L61:
            if (this.val$scrollDown == true) goto L65;
            r12 = RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getHeight() - r32;
        L65:
            if (this.val$scrollDown == false) goto L67;
            int r28 = -r5;
        L68:
            r27 = r28 + r12;
            goto L69
        L67:
            r28 = r6 - RecyclerAnimationScrollHelper.m12433$$Nest$fgetrecyclerView(this.this$0).getHeight();
            goto L68
        L53:
            r32 = r114;
            goto L55
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class AnimatableAdapter extends RecyclerListView.SelectionAdapter {
        public boolean animationRunning;
        private ArrayList<Integer> rangeInserted;
        private ArrayList<Integer> rangeRemoved;
        private boolean shouldNotifyDataSetChanged;

        public AnimatableAdapter() {
            this.rangeInserted = new ArrayList();
            this.rangeRemoved = new ArrayList();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            if (this.animationRunning == true) goto L6;
            super.notifyDataSetChanged();
            return;
        L6:
            this.shouldNotifyDataSetChanged = true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemChanged(int r2) {
            if (this.animationRunning == true) goto L6;
            super.notifyItemChanged(r2);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemInserted(int r2) {
            if (this.animationRunning == true) goto L6;
            super.notifyItemInserted(r2);
            return;
        L6:
            this.rangeInserted.add(Integer.valueOf(r2));
            this.rangeInserted.add(1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeChanged(int r2, int r3) {
            if (this.animationRunning == true) goto L6;
            super.notifyItemRangeChanged(r2, r3);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeInserted(int r2, int r3) {
            if (this.animationRunning == true) goto L6;
            super.notifyItemRangeInserted(r2, r3);
            return;
        L6:
            this.rangeInserted.add(Integer.valueOf(r2));
            this.rangeInserted.add(Integer.valueOf(r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeRemoved(int r2, int r3) {
            if (this.animationRunning == true) goto L6;
            super.notifyItemRangeRemoved(r2, r3);
            return;
        L6:
            this.rangeRemoved.add(Integer.valueOf(r2));
            this.rangeRemoved.add(Integer.valueOf(r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRemoved(int r2) {
            if (this.animationRunning == true) goto L6;
            super.notifyItemRemoved(r2);
            return;
        L6:
            this.rangeRemoved.add(Integer.valueOf(r2));
            this.rangeRemoved.add(1);
        }

        public void onAnimationEnd() {
            this.animationRunning = false;
            if (this.shouldNotifyDataSetChanged == false) goto L5;
        L10:
            notifyDataSetChanged();
            return;
        L5:
            if (this.rangeInserted.isEmpty() == false) goto L10;
            if (this.rangeRemoved.isEmpty() == false) goto L10;
        }

        public void onAnimationStart() {
            this.animationRunning = true;
            this.shouldNotifyDataSetChanged = false;
            this.rangeInserted.clear();
            this.rangeRemoved.clear();
        }
    }

    /* loaded from: classes7.dex */
    public static class AnimationCallback {
        public AnimationCallback() {
        }

        public void ignoreView(View r1, boolean r2) {
        }

        public abstract void onEndAnimation();

        public void onPreAnimation() {
        }

        public void onStartAnimation() {
        }

        public void recycleView(View r1) {
        }
    }

    public interface ScrollListener {
        void onScroll();
    }

    /* renamed from: $r8$lambda$R-kUhow_cP8ejqEJB4dkr7kroH8, reason: not valid java name */
    public static /* synthetic */ void m12428$r8$lambda$RkUhow_cP8ejqEJB4dkr7kroH8(RecyclerAnimationScrollHelper r0, int r1, int r2, boolean r3, boolean r4) {
        r0.lambda$scrollToPosition$0(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetanimationCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationCallback m12429$$Nest$fgetanimationCallback(RecyclerAnimationScrollHelper r0) {
        return r0.animationCallback;
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m12430$$Nest$fgetanimator(RecyclerAnimationScrollHelper r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m12431$$Nest$fgetlayoutManager(RecyclerAnimationScrollHelper r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetoldStableIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m12432$$Nest$fgetoldStableIds(RecyclerAnimationScrollHelper r0) {
        return r0.oldStableIds;
    }

    /* renamed from: -$$Nest$fgetrecyclerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m12433$$Nest$fgetrecyclerView(RecyclerAnimationScrollHelper r0) {
        return r0.recyclerView;
    }

    /* renamed from: -$$Nest$fgetscrollListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollListener m12434$$Nest$fgetscrollListener(RecyclerAnimationScrollHelper r0) {
        return r0.scrollListener;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12435$$Nest$fputanimator(RecyclerAnimationScrollHelper r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    public RecyclerAnimationScrollHelper(RecyclerListView r2, LinearLayoutManager r3) {
        this.positionToOldView = new SparseArray();
        this.oldStableIds = new HashMap();
        this.recyclerView = r2;
        this.layoutManager = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void clear() {
        this.recyclerView.setVerticalScrollBarEnabled(true);
        RecyclerListView r0 = this.recyclerView;
        r0.fastScrollAnimationRunning = false;
        RecyclerView.Adapter r02 = r0.getAdapter();
        if ((r02 instanceof AnimatableAdapter) == false) goto L5;
        ((AnimatableAdapter) r02).onAnimationEnd();
    L5:
        this.animator = null;
        int r03 = this.recyclerView.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r03) goto L11;
        View r3 = this.recyclerView.getChildAt(r2);
        r3.setTranslationY(0.0f);
        if ((r3 instanceof IMessageCell) == false) goto L10;
        ((IMessageCell) r3).setAnimationRunning(false, false);
    L10:
        r2 = r2 + 1;
        goto L6
    }

    private /* synthetic */ void lambda$scrollToPosition$0(int r7, int r8, boolean r9, boolean r10) {
        scrollToPosition(r7, r8, r9, r10, false);
    }

    public void cancel() {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        clear();
    }

    public void scrollToPosition(int r7, int r8, boolean r9, boolean r10) {
        scrollToPosition(r7, r8, r9, r10, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void scrollToPosition(int r10, int r11, boolean r12, boolean r13, boolean r14) {
        RecyclerListView r0 = this.recyclerView;
        if (r0.fastScrollAnimationRunning == false) goto L6;
        return;
    L6:
        if (r0.getItemAnimator() == null) goto L15;
        RecyclerListView r02 = this.recyclerView;
        if (r14 == false) goto L12;
        RecyclerAnimationScrollHelper r1 = this;
        int r2 = r10;
        int r3 = r11;
        boolean r4 = r12;
        boolean r5 = r13;
        if (r02.getItemAnimator().isRunning(new RecyclerAnimationScrollHelper$$ExternalSyntheticLambda0(r1, r2, r3, r4, r5)) == true) goto L75;
    L16:
        if (r5 == false) goto L68;
        if (r1.scrollDirection == (-1)) goto L68;
        int r9 = r1.recyclerView.getChildCount();
        if (r9 == 0) goto L66;
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == false) goto L66;
        if (r1.scrollDirection != 0) goto L28;
        boolean r52 = true;
    L29:
        r1.recyclerView.setScrollEnabled(false);
        boolean r102 = r4;
        ArrayList r42 = new ArrayList();
        r1.positionToOldView.clear();
        RecyclerView.Adapter r132 = r1.recyclerView.getAdapter();
        r1.oldStableIds.clear();
        int r142 = 0;
    L30:
        RecyclerListView r03 = r1.recyclerView;
        if (r142 >= r9) goto L50;
        View r04 = r03.getChildAt(r142);
        r42.add(r04);
        r1.positionToOldView.put(r1.layoutManager.getPosition(r04), r04);
        if (r132 == null) goto L47;
        if (r132.hasStableIds() == true) goto L39;
        if (r1.forceUseStableId == false) goto L47;
    L39:
        if (r1.forceUseStableId == false) goto L44;
        int r6 = ((RecyclerView.LayoutParams) r04.getLayoutParams()).mViewHolder.getAdapterPosition();
        if (r6 < 0) goto L49;
        long r62 = r132.getItemId(r6);
    L45:
        r1.oldStableIds.put(Long.valueOf(r62), r04);
    L49:
        r142 = r142 + 1;
        goto L30
    L44:
        r62 = ((RecyclerView.LayoutParams) r04.getLayoutParams()).mViewHolder.getItemId();
    L47:
        if ((r04 instanceof IMessageCell) == false) goto L49;
        ((IMessageCell) r04).setAnimationRunning(true, true);
        goto L49
    L50:
        r03.prepareForFastScroll();
        if ((r132 instanceof AnimatableAdapter) == false) goto L54;
        AnimatableAdapter r92 = (AnimatableAdapter) r132;
    L53:
        AnimatableAdapter r63 = r92;
        r1.layoutManager.scrollToPositionWithOffset(r2, r3, r102);
        if (r132 == null) goto L58;
        r132.notifyDataSetChanged();
    L58:
        r1.recyclerView.stopScroll();
        r1.recyclerView.setVerticalScrollBarEnabled(false);
        AnimationCallback r93 = r1.animationCallback;
        if (r93 == null) goto L61;
        r93.onStartAnimation();
    L61:
        r1.recyclerView.fastScrollAnimationRunning = true;
        if (r63 == null) goto L64;
        r63.onAnimationStart();
    L64:
        r1.recyclerView.addOnLayoutChangeListener(new AnonymousClass1(r1, r132, r42, r52, r63));
        return;
    L54:
        r92 = null;
        goto L53
    L28:
        r52 = false;
    L66:
        r1.layoutManager.scrollToPositionWithOffset(r2, r3, r4);
        return;
    L68:
        r1.layoutManager.scrollToPositionWithOffset(r2, r3, r4);
        return;
    L75:
        return;
    L12:
        r1 = this;
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r5 = r13;
        if (r02.getItemAnimator().isRunning() == false) goto L16;
        return;
    L15:
        r1 = this;
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r5 = r13;
        goto L16
    }

    public void setAnimationCallback(AnimationCallback r1) {
        this.animationCallback = r1;
    }

    public void setScrollDirection(int r1) {
        this.scrollDirection = r1;
    }

    public void setScrollListener(ScrollListener r1) {
        this.scrollListener = r1;
    }
}
