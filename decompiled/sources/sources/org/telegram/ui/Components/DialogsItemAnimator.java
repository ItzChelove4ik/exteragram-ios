package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import java.util.ArrayList;
import java.util.List;
import org.telegram.ui.Adapters.DialogsAdapter;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.DialogsEmptyCell;

/* loaded from: classes3.dex */
public abstract class DialogsItemAnimator extends SimpleItemAnimator {
    private static TimeInterpolator sDefaultInterpolator;
    private int bottomClip;
    private final RecyclerListView listView;
    ArrayList<RecyclerView.ViewHolder> mAddAnimations;
    ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList;
    ArrayList<RecyclerView.ViewHolder> mChangeAnimations;
    ArrayList<ArrayList<ChangeInfo>> mChangesList;
    ArrayList<RecyclerView.ViewHolder> mMoveAnimations;
    ArrayList<ArrayList<MoveInfo>> mMovesList;
    private ArrayList<RecyclerView.ViewHolder> mPendingAdditions;
    private ArrayList<ChangeInfo> mPendingChanges;
    private ArrayList<MoveInfo> mPendingMoves;
    private ArrayList<RecyclerView.ViewHolder> mPendingRemovals;
    ArrayList<RecyclerView.ViewHolder> mRemoveAnimations;
    private DialogCell removingDialog;
    private int topClip;

    /* renamed from: org.telegram.ui.Components.DialogsItemAnimator$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsItemAnimator this$0;
        final /* synthetic */ DialogCell val$dialogCell;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;

        public AnonymousClass1(DialogsItemAnimator r1, RecyclerView.ViewHolder r2, DialogCell r3) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$dialogCell = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            r2.removeAllListeners();
            this.val$dialogCell.setClipProgress(0.0f);
            this.val$dialogCell.setElevation(0.0f);
            this.this$0.dispatchRemoveFinished(this.val$holder);
            this.this$0.mRemoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchRemoveStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.Components.DialogsItemAnimator$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsItemAnimator this$0;
        final /* synthetic */ DialogCell val$dialogCell;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;

        public AnonymousClass2(DialogsItemAnimator r1, RecyclerView.ViewHolder r2, DialogCell r3) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$dialogCell = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            r2.removeAllListeners();
            this.val$dialogCell.setClipProgress(0.0f);
            this.val$dialogCell.setElevation(0.0f);
            this.this$0.dispatchRemoveFinished(this.val$holder);
            this.this$0.mRemoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchRemoveStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.Components.DialogsItemAnimator$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass3(DialogsItemAnimator r1, RecyclerView.ViewHolder r2, ViewPropertyAnimator r3, View r4) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$animation = r3;
            this.val$view = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$animation.setListener(null);
            this.val$view.setAlpha(1.0f);
            this.this$0.dispatchRemoveFinished(this.val$holder);
            this.this$0.mRemoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchRemoveStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.Components.DialogsItemAnimator$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass4(DialogsItemAnimator r1, RecyclerView.ViewHolder r2, View r3, ViewPropertyAnimator r4) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$view = r3;
            this.val$animation = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.val$view.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$animation.setListener(null);
            this.this$0.dispatchAddFinished(this.val$holder);
            this.this$0.mAddAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
            View r1 = this.val$holder.itemView;
            if ((r1 instanceof DialogCell) == false) goto L6;
            ((DialogCell) r1).setMoving(false);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchAddStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.Components.DialogsItemAnimator$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ int val$deltaX;
        final /* synthetic */ int val$deltaY;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass5(DialogsItemAnimator r1, RecyclerView.ViewHolder r2, int r3, View r4, int r5, ViewPropertyAnimator r6) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$deltaX = r3;
            this.val$view = r4;
            this.val$deltaY = r5;
            this.val$animation = r6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (this.val$deltaX == 0) goto L6;
            this.val$view.setTranslationX(0.0f);
        L6:
            if (this.val$deltaY == 0) goto L8;
            this.val$view.setTranslationY(0.0f);
        L8:
            View r1 = this.val$holder.itemView;
            if ((r1 instanceof DialogCell) == false) goto L13;
            ((DialogCell) r1).setMoving(false);
            return;
        L13:
            if ((r1 instanceof DialogsAdapter.LastEmptyView) == false) goto L16;
            ((DialogsAdapter.LastEmptyView) r1).moving = false;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$animation.setListener(null);
            this.this$0.dispatchMoveFinished(this.val$holder);
            this.this$0.mMoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
            View r32 = this.val$holder.itemView;
            if ((r32 instanceof DialogCell) == false) goto L6;
            ((DialogCell) r32).setMoving(false);
        L8:
            this.val$view.setTranslationX(0.0f);
            this.val$view.setTranslationY(0.0f);
            return;
        L6:
            if ((r32 instanceof DialogsAdapter.LastEmptyView) == false) goto L8;
            ((DialogsAdapter.LastEmptyView) r32).moving = false;
            goto L8
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchMoveStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.Components.DialogsItemAnimator$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsItemAnimator this$0;
        final /* synthetic */ AnimatorSet val$animatorSet;
        final /* synthetic */ ChangeInfo val$changeInfo;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;

        public AnonymousClass6(DialogsItemAnimator r1, ChangeInfo r2, RecyclerView.ViewHolder r3, AnimatorSet r4) {
            this.this$0 = r1;
            this.val$changeInfo = r2;
            this.val$holder = r3;
            this.val$animatorSet = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$holder.itemView.setAlpha(1.0f);
            this.val$animatorSet.removeAllListeners();
            this.this$0.dispatchChangeFinished(this.val$changeInfo.oldHolder, true);
            this.this$0.mChangeAnimations.remove(this.val$changeInfo.oldHolder);
            this.this$0.dispatchFinishedWhenDone();
            this.this$0.dispatchChangeFinished(this.val$changeInfo.newHolder, false);
            this.this$0.mChangeAnimations.remove(this.val$changeInfo.newHolder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r3) {
            this.this$0.dispatchChangeStarting(this.val$changeInfo.oldHolder, true);
            this.this$0.dispatchChangeStarting(this.val$changeInfo.newHolder, false);
        }
    }

    /* loaded from: classes7.dex */
    public static class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        private ChangeInfo(RecyclerView.ViewHolder r1, RecyclerView.ViewHolder r2) {
            this.oldHolder = r1;
            this.newHolder = r2;
        }

        public ChangeInfo(RecyclerView.ViewHolder r1, RecyclerView.ViewHolder r2, int r3, int r4, int r5, int r6) {
            this(r1, r2);
            this.fromX = r3;
            this.fromY = r4;
            this.toX = r5;
            this.toY = r6;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }

    /* loaded from: classes7.dex */
    public static class MoveInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        public MoveInfo(RecyclerView.ViewHolder r1, int r2, int r3, int r4, int r5) {
            this.holder = r1;
            this.fromX = r2;
            this.fromY = r3;
            this.toX = r4;
            this.toY = r5;
        }
    }

    /* renamed from: $r8$lambda$0X9__Jgk5t3jGYmhhLS66-XhRG8, reason: not valid java name */
    public static /* synthetic */ void m10126$r8$lambda$0X9__Jgk5t3jGYmhhLS66XhRG8(DialogsItemAnimator r0, ArrayList r1) {
        r0.lambda$runPendingAnimations$0(r1);
    }

    /* renamed from: $r8$lambda$MLhMjxvbvthR-blYRfHX6GIRy3g, reason: not valid java name */
    public static /* synthetic */ void m10127$r8$lambda$MLhMjxvbvthRblYRfHX6GIRy3g(DialogsItemAnimator r0, ArrayList r1) {
        r0.lambda$runPendingAnimations$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$WbEvYed8lNWUJuPViNmpxPkMZU4(DialogsItemAnimator r0, ArrayList r1) {
        r0.lambda$runPendingAnimations$1(r1);
    }

    static {
        sDefaultInterpolator = new DecelerateInterpolator();
    }

    public DialogsItemAnimator(RecyclerListView r2) {
        this.mPendingRemovals = new ArrayList();
        this.mPendingAdditions = new ArrayList();
        this.mPendingMoves = new ArrayList();
        this.mPendingChanges = new ArrayList();
        this.mAdditionsList = new ArrayList();
        this.mMovesList = new ArrayList();
        this.mChangesList = new ArrayList();
        this.mAddAnimations = new ArrayList();
        this.mMoveAnimations = new ArrayList();
        this.mRemoveAnimations = new ArrayList();
        this.mChangeAnimations = new ArrayList();
        setSupportsChangeAnimations(false);
        this.listView = r2;
    }

    private void animateRemoveImpl(RecyclerView.ViewHolder r10) {
        View r0 = r10.itemView;
        this.mRemoveAnimations.add(r10);
        if ((r0 instanceof DialogCell) == false) goto L16;
        DialogCell r1 = (DialogCell) r0;
        DialogCell r4 = this.removingDialog;
        if (r0 == r4) goto L7;
        ObjectAnimator r02 = ObjectAnimator.ofFloat(r1, View.ALPHA, new float[]{1.0f}).setDuration(180);
        r02.setInterpolator(sDefaultInterpolator);
        r02.addListener(new AnonymousClass2(this, r10, r1));
        r02.start();
        return;
    L7:
        if (this.topClip == Integer.MAX_VALUE) goto L10;
        int r03 = r4.getMeasuredHeight();
        int r42 = this.topClip;
        this.bottomClip = r03 - r42;
        this.removingDialog.setTopClip(r42);
        this.removingDialog.setBottomClip(this.bottomClip);
    L12:
        r1.setElevation(-1.0f);
        r1.setOutlineProvider(null);
        ObjectAnimator r04 = ObjectAnimator.ofFloat(r1, AnimationProperties.CLIP_DIALOG_CELL_PROGRESS, new float[]{1.0f}).setDuration(180);
        r04.setInterpolator(sDefaultInterpolator);
        r04.addListener(new AnonymousClass1(this, r10, r1));
        r04.start();
        return;
    L10:
        if (this.bottomClip == Integer.MAX_VALUE) goto L12;
        int r05 = r4.getMeasuredHeight() - this.bottomClip;
        this.topClip = r05;
        this.removingDialog.setTopClip(r05);
        this.removingDialog.setBottomClip(this.bottomClip);
        goto L12
    L16:
        ViewPropertyAnimator r12 = r0.animate();
        r12.setDuration(180).alpha(0.0f).setListener(new AnonymousClass3(this, r10, r12, r0)).start();
    }

    private void endChangeAnimation(List<ChangeInfo> r4, RecyclerView.ViewHolder r5) {
        int r0 = r4.size() - 1;
    L3:
        if (r0 < 0) goto L12;
        ChangeInfo r1 = r4.get(r0);
        if (endChangeAnimationIfNecessary(r1, r5) == false) goto L11;
        if (r1.oldHolder != null) goto L11;
        if (r1.newHolder != null) goto L11;
        r4.remove(r1);
    L11:
        r0 = r0 - 1;
        goto L3
    }

    private void endChangeAnimationIfNecessary(ChangeInfo r2) {
        RecyclerView.ViewHolder r0 = r2.oldHolder;
        if (r0 == null) goto L5;
        endChangeAnimationIfNecessary(r2, r0);
    L5:
        RecyclerView.ViewHolder r02 = r2.newHolder;
        if (r02 == null) goto L9;
        endChangeAnimationIfNecessary(r2, r02);
        return;
    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo r5, RecyclerView.ViewHolder r6) {
        boolean r3 = false;
        if (r5.newHolder != r6) goto L6;
        r5.newHolder = null;
    L8:
        r6.itemView.setAlpha(1.0f);
        r6.itemView.setTranslationX(0.0f);
        r6.itemView.setTranslationY(0.0f);
        dispatchChangeFinished(r6, r3);
        return true;
    L6:
        if (r5.oldHolder != r6) goto L10;
        r5.oldHolder = null;
        r3 = true;
        goto L8
    L10:
        return false;
    }

    private /* synthetic */ void lambda$runPendingAnimations$0(ArrayList r11) {
        int r0 = r11.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r11.get(r1);
        r1 = r1 + 1;
        MoveInfo r22 = (MoveInfo) r2;
        animateMoveImpl(r22.holder, null, r22.fromX, r22.fromY, r22.toX, r22.toY);
        goto L3
    L5:
        r11.clear();
        this.mMovesList.remove(r11);
    }

    private /* synthetic */ void lambda$runPendingAnimations$1(ArrayList r4) {
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r4.get(r1);
        r1 = r1 + 1;
        animateChangeImpl((ChangeInfo) r2);
        goto L3
    L5:
        r4.clear();
        this.mChangesList.remove(r4);
    }

    private /* synthetic */ void lambda$runPendingAnimations$2(ArrayList r4) {
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r4.get(r1);
        r1 = r1 + 1;
        animateAddImpl((RecyclerView.ViewHolder) r2);
        goto L3
    L5:
        r4.clear();
        this.mAdditionsList.remove(r4);
    }

    private void resetAnimation(RecyclerView.ViewHolder r3) {
        r3.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(r3);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(RecyclerView.ViewHolder r4) {
        resetAnimation(r4);
        View r0 = r4.itemView;
        if ((r0 instanceof DialogCell) == true) goto L5;
        r0.setAlpha(0.0f);
    L5:
        this.mPendingAdditions.add(r4);
        if (this.mPendingAdditions.size() <= 2) goto L14;
        int r42 = 0;
    L9:
        if (r42 >= this.mPendingAdditions.size()) goto L14;
        this.mPendingAdditions.get(r42).itemView.setAlpha(0.0f);
        if ((this.mPendingAdditions.get(r42).itemView instanceof DialogCell) == false) goto L13;
        ((DialogCell) this.mPendingAdditions.get(r42).itemView).setMoving(true);
    L13:
        r42 = r42 + 1;
    L14:
        return true;
    }

    public void animateAddImpl(RecyclerView.ViewHolder r6) {
        View r0 = r6.itemView;
        this.mAddAnimations.add(r6);
        ViewPropertyAnimator r1 = r0.animate();
        r1.alpha(1.0f).setDuration(180).setListener(new AnonymousClass4(this, r6, r0, r1)).start();
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3, RecyclerView.ItemAnimator.ItemHolderInfo r4, int r5, int r6, int r7, int r8) {
        if ((r2.itemView instanceof DialogCell) == false) goto L6;
        resetAnimation(r2);
        resetAnimation(r3);
        r2.itemView.setAlpha(1.0f);
        r3.itemView.setAlpha(0.0f);
        r3.itemView.setTranslationX(0.0f);
        this.mPendingChanges.add(new ChangeInfo(r2, r3, r5, r6, r7, r8));
        return true;
    L6:
        return false;
    }

    public void animateChangeImpl(ChangeInfo r10) {
        RecyclerView.ViewHolder r0 = r10.oldHolder;
        RecyclerView.ViewHolder r1 = r10.newHolder;
        if (r0 == null) goto L8;
        if (r1 == null) goto L9;
        AnimatorSet r2 = new AnimatorSet();
        r2.setDuration(180);
        View r3 = r0.itemView;
        Property r4 = View.ALPHA;
        r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{0.0f}), ObjectAnimator.ofFloat(r1.itemView, r4, new float[]{1.0f})});
        this.mChangeAnimations.add(r10.oldHolder);
        this.mChangeAnimations.add(r10.newHolder);
        r2.addListener(new AnonymousClass6(this, r10, r0, r2));
        r2.start();
        return;
    L9:
        return;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder r8, RecyclerView.ItemAnimator.ItemHolderInfo r9, int r10, int r11, int r12, int r13) {
        View r92 = r8.itemView;
        int r3 = r10 + ((int) r92.getTranslationX());
        int r4 = r11 + ((int) r8.itemView.getTranslationY());
        resetAnimation(r8);
        int r102 = r12 - r3;
        int r112 = r13 - r4;
        if (r102 != 0) goto L7;
        if (r112 != 0) goto L7;
        dispatchMoveFinished(r8);
        return false;
    L7:
        if (r102 == 0) goto L9;
        r92.setTranslationX(-r102);
    L9:
        if (r112 == 0) goto L11;
        r92.setTranslationY(-r112);
    L11:
        View r93 = r8.itemView;
        if ((r93 instanceof DialogCell) == false) goto L15;
        ((DialogCell) r93).setMoving(true);
    L17:
        this.mPendingMoves.add(new MoveInfo(r8, r3, r4, r12, r13));
        return true;
    L15:
        if ((r93 instanceof DialogsAdapter.LastEmptyView) == false) goto L17;
        ((DialogsAdapter.LastEmptyView) r93).moving = true;
        goto L17
    }

    public void animateMoveImpl(RecyclerView.ViewHolder r3, RecyclerView.ItemAnimator.ItemHolderInfo r4, int r5, int r6, int r7, int r8) {
        View r62 = r3.itemView;
        int r52 = r7 - r5;
        int r72 = r8 - r6;
        if (r52 == 0) goto L5;
        r62.animate().translationX(0.0f);
    L5:
        if (r72 == 0) goto L7;
        r62.animate().translationY(0.0f);
    L7:
        if (r6 <= r8) goto L9;
        this.bottomClip = r6 - r8;
    L10:
        DialogCell r42 = this.removingDialog;
        if (r42 != null) goto L13;
    L18:
        ViewPropertyAnimator r82 = r62.animate();
        this.mMoveAnimations.add(r3);
        r82.setDuration(180).setListener(new AnonymousClass5(this, r3, r52, r62, r72, r82)).start();
        return;
    L13:
        if (this.topClip == Integer.MAX_VALUE) goto L16;
        int r43 = r42.getMeasuredHeight();
        int r83 = this.topClip;
        this.bottomClip = r43 - r83;
        this.removingDialog.setTopClip(r83);
        this.removingDialog.setBottomClip(this.bottomClip);
        goto L18
    L16:
        if (this.bottomClip == Integer.MAX_VALUE) goto L18;
        int r44 = r42.getMeasuredHeight() - this.bottomClip;
        this.topClip = r44;
        this.removingDialog.setTopClip(r44);
        this.removingDialog.setBottomClip(this.bottomClip);
        goto L18
    L9:
        this.topClip = r72;
        goto L10
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(RecyclerView.ViewHolder r5, RecyclerView.ItemAnimator.ItemHolderInfo r6) {
        resetAnimation(r5);
        this.mPendingRemovals.add(r5);
        DialogCell r62 = null;
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L12;
        View r1 = this.listView.getChildAt(r0);
        if (r1.getTop() <= Integer.MIN_VALUE) goto L10;
        if ((r1 instanceof DialogCell) == false) goto L10;
        r62 = (DialogCell) r1;
    L10:
        r0 = r0 + 1;
        goto L4
    L12:
        if (r5.itemView != r62) goto L20;
        this.removingDialog = r62;
        return true;
    L20:
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder r1, List<Object> r2) {
        return r1.itemView instanceof DialogsEmptyCell;
    }

    public void cancelAll(List<RecyclerView.ViewHolder> r2) {
        int r1 = r2.size() - 1;
    L3:
        if (r1 < 0) goto L5;
        r2.get(r1).itemView.animate().cancel();
        r1 = r1 - 1;
        goto L3
    }

    public void dispatchFinishedWhenDone() {
        if (isRunning() == true) goto L6;
        dispatchAnimationsFinished();
        onAllAnimationsDone();
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(RecyclerView.ViewHolder r8) {
        View r0 = r8.itemView;
        r0.animate().cancel();
        int r1 = this.mPendingMoves.size();
    L3:
        r1 = r1 - 1;
        if (r1 < 0) goto L9;
        if (this.mPendingMoves.get(r1).holder != r8) goto L3;
        r0.setTranslationY(0.0f);
        r0.setTranslationX(0.0f);
        dispatchMoveFinished(r8);
        this.mPendingMoves.remove(r1);
        goto L3
    L9:
        endChangeAnimation(this.mPendingChanges, r8);
        if (this.mPendingRemovals.remove(r8) == false) goto L17;
        if ((r0 instanceof DialogCell) == false) goto L14;
        ((DialogCell) r0).setClipProgress(0.0f);
    L15:
        dispatchRemoveFinished(r8);
        goto L17
    L14:
        r0.setAlpha(1.0f);
    L17:
        if (this.mPendingAdditions.remove(r8) == true) goto L19;
    L23:
        int r12 = this.mChangesList.size() - 1;
    L24:
        if (r12 < 0) goto L29;
        ArrayList<ChangeInfo> r4 = this.mChangesList.get(r12);
        endChangeAnimation(r4, r8);
        if (r4.isEmpty() == false) goto L28;
        this.mChangesList.remove(r12);
    L28:
        r12 = r12 - 1;
        goto L24
    L29:
        int r13 = this.mMovesList.size() - 1;
    L30:
        if (r13 < 0) goto L40;
        ArrayList<MoveInfo> r42 = this.mMovesList.get(r13);
        int r5 = r42.size() - 1;
    L32:
        if (r5 < 0) goto L39;
        if (r42.get(r5).holder == r8) goto L35;
        r5 = r5 - 1;
        goto L32
    L35:
        r0.setTranslationY(0.0f);
        r0.setTranslationX(0.0f);
        dispatchMoveFinished(r8);
        r42.remove(r5);
        if (r42.isEmpty() == false) goto L39;
        this.mMovesList.remove(r13);
    L39:
        r13 = r13 - 1;
        goto L30
    L40:
        int r14 = this.mAdditionsList.size() - 1;
    L41:
        if (r14 < 0) goto L52;
        ArrayList<RecyclerView.ViewHolder> r2 = this.mAdditionsList.get(r14);
        if (r2.remove(r8) == false) goto L51;
        if ((r0 instanceof DialogCell) == false) goto L47;
        ((DialogCell) r0).setClipProgress(1.0f);
    L48:
        dispatchAddFinished(r8);
        if (r2.isEmpty() == false) goto L51;
        this.mAdditionsList.remove(r14);
        goto L51
    L47:
        r0.setAlpha(1.0f);
    L51:
        r14 = r14 - 1;
        goto L41
    L52:
        this.mRemoveAnimations.remove(r8);
        this.mAddAnimations.remove(r8);
        this.mChangeAnimations.remove(r8);
        this.mMoveAnimations.remove(r8);
        dispatchFinishedWhenDone();
        return;
    L19:
        if ((r0 instanceof DialogCell) == false) goto L21;
        ((DialogCell) r0).setClipProgress(0.0f);
    L22:
        dispatchAddFinished(r8);
        goto L23
    L21:
        r0.setAlpha(1.0f);
        goto L22
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
        int r0 = this.mPendingMoves.size();
    L3:
        r0 = r0 - 1;
        if (r0 < 0) goto L6;
        MoveInfo r2 = this.mPendingMoves.get(r0);
        View r3 = r2.holder.itemView;
        r3.setTranslationY(0.0f);
        r3.setTranslationX(0.0f);
        dispatchMoveFinished(r2.holder);
        this.mPendingMoves.remove(r0);
        goto L3
    L6:
        int r02 = this.mPendingRemovals.size() - 1;
    L7:
        if (r02 < 0) goto L9;
        RecyclerView.ViewHolder r22 = this.mPendingRemovals.get(r02);
        View r32 = r22.itemView;
        r32.setTranslationY(0.0f);
        r32.setTranslationX(0.0f);
        dispatchRemoveFinished(r22);
        this.mPendingRemovals.remove(r02);
        r02 = r02 - 1;
        goto L7
    L9:
        int r03 = this.mPendingAdditions.size();
    L10:
        r03 = r03 - 1;
        if (r03 < 0) goto L17;
        RecyclerView.ViewHolder r33 = this.mPendingAdditions.get(r03);
        View r4 = r33.itemView;
        if ((r4 instanceof DialogCell) == false) goto L15;
        ((DialogCell) r4).setClipProgress(0.0f);
    L16:
        dispatchAddFinished(r33);
        this.mPendingAdditions.remove(r03);
        goto L10
    L15:
        r4.setAlpha(1.0f);
        goto L16
    L17:
        int r04 = this.mPendingChanges.size();
    L18:
        r04 = r04 - 1;
        ArrayList<ChangeInfo> r34 = this.mPendingChanges;
        if (r04 < 0) goto L21;
        endChangeAnimationIfNecessary(r34.get(r04));
        goto L18
    L21:
        r34.clear();
        if (isRunning() == true) goto L24;
        return;
    L24:
        int r05 = this.mMovesList.size() - 1;
    L25:
        if (r05 < 0) goto L33;
        ArrayList<MoveInfo> r35 = this.mMovesList.get(r05);
        int r42 = r35.size() - 1;
    L27:
        if (r42 < 0) goto L32;
        MoveInfo r5 = r35.get(r42);
        View r6 = r5.holder.itemView;
        r6.setTranslationY(0.0f);
        r6.setTranslationX(0.0f);
        dispatchMoveFinished(r5.holder);
        r35.remove(r42);
        if (r35.isEmpty() == false) goto L31;
        this.mMovesList.remove(r35);
    L31:
        r42 = r42 - 1;
        goto L27
    L32:
        r05 = r05 - 1;
        goto L25
    L33:
        int r06 = this.mAdditionsList.size() - 1;
    L34:
        if (r06 < 0) goto L46;
        ArrayList<RecyclerView.ViewHolder> r36 = this.mAdditionsList.get(r06);
        int r43 = r36.size() - 1;
    L36:
        if (r43 < 0) goto L45;
        RecyclerView.ViewHolder r52 = r36.get(r43);
        View r62 = r52.itemView;
        if ((r62 instanceof DialogCell) == false) goto L40;
        ((DialogCell) r62).setClipProgress(0.0f);
    L41:
        dispatchAddFinished(r52);
        r36.remove(r43);
        if (r36.isEmpty() == false) goto L44;
        this.mAdditionsList.remove(r36);
    L44:
        r43 = r43 - 1;
        goto L36
    L40:
        r62.setAlpha(1.0f);
        goto L41
    L45:
        r06 = r06 - 1;
        goto L34
    L46:
        int r07 = this.mChangesList.size() - 1;
    L47:
        if (r07 < 0) goto L55;
        ArrayList<ChangeInfo> r1 = this.mChangesList.get(r07);
        int r23 = r1.size() - 1;
    L49:
        if (r23 < 0) goto L54;
        endChangeAnimationIfNecessary(r1.get(r23));
        if (r1.isEmpty() == false) goto L53;
        this.mChangesList.remove(r1);
    L53:
        r23 = r23 - 1;
        goto L49
    L54:
        r07 = r07 - 1;
        goto L47
    L55:
        cancelAll(this.mRemoveAnimations);
        cancelAll(this.mMoveAnimations);
        cancelAll(this.mAddAnimations);
        cancelAll(this.mChangeAnimations);
        dispatchAnimationsFinished();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean isRunning() {
        if (this.mPendingAdditions.isEmpty() == true) goto L5;
        return true;
    L5:
        if (this.mPendingChanges.isEmpty() == true) goto L7;
        return true;
    L7:
        if (this.mPendingMoves.isEmpty() == true) goto L9;
        return true;
    L9:
        if (this.mPendingChanges.isEmpty() == true) goto L11;
        return true;
    L11:
        if (this.mMoveAnimations.isEmpty() == true) goto L13;
        return true;
    L13:
        if (this.mRemoveAnimations.isEmpty() == true) goto L15;
        return true;
    L15:
        if (this.mAddAnimations.isEmpty() == true) goto L17;
        return true;
    L17:
        if (this.mChangeAnimations.isEmpty() == true) goto L19;
        return true;
    L19:
        if (this.mMovesList.isEmpty() == true) goto L21;
        return true;
    L21:
        if (this.mAdditionsList.isEmpty() == true) goto L23;
        return true;
    L23:
        if (this.mChangesList.isEmpty() == false) goto L38;
        return false;
    L38:
        return true;
    }

    public void onAllAnimationsDone() {
    }

    public void onListScroll(int r7) {
        int r1 = 0;
        if (this.mPendingRemovals.isEmpty() == true) goto L8;
        int r0 = this.mPendingRemovals.size();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L8;
        View r3 = this.mPendingRemovals.get(r2).itemView;
        r3.setTranslationY(r3.getTranslationY() + r7);
        r2 = r2 + 1;
    L8:
        if (this.mRemoveAnimations.isEmpty() == true) goto L12;
        int r02 = this.mRemoveAnimations.size();
    L10:
        if (r1 >= r02) goto L15;
        View r22 = this.mRemoveAnimations.get(r1).itemView;
        r22.setTranslationY(r22.getTranslationY() + r7);
        r1 = r1 + 1;
        goto L10
    L15:
        return;
    }

    public void prepareForRemove() {
        this.topClip = Integer.MAX_VALUE;
        this.bottomClip = Integer.MAX_VALUE;
        this.removingDialog = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
        boolean r0 = this.mPendingRemovals.isEmpty();
        boolean r1 = this.mPendingMoves.isEmpty();
        boolean r2 = this.mPendingChanges.isEmpty();
        boolean r3 = this.mPendingAdditions.isEmpty();
        if (r0 == false) goto L8;
        if (r1 == false) goto L8;
        if (r3 == false) goto L8;
        if (r2 == false) goto L8;
        return;
    L8:
        ArrayList<RecyclerView.ViewHolder> r02 = this.mPendingRemovals;
        int r4 = r02.size();
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L11;
        RecyclerView.ViewHolder r6 = r02.get(r5);
        r5 = r5 + 1;
        animateRemoveImpl(r6);
        goto L9
    L11:
        this.mPendingRemovals.clear();
        if (r1 == true) goto L14;
        ArrayList<MoveInfo> r03 = new ArrayList(this.mPendingMoves);
        this.mMovesList.add(r03);
        this.mPendingMoves.clear();
        new DialogsItemAnimator$$ExternalSyntheticLambda0(this, r03).run();
    L14:
        if (r2 == true) goto L16;
        ArrayList<ChangeInfo> r04 = new ArrayList(this.mPendingChanges);
        this.mChangesList.add(r04);
        this.mPendingChanges.clear();
        new DialogsItemAnimator$$ExternalSyntheticLambda1(this, r04).run();
    L16:
        if (r3 == true) goto L20;
        ArrayList<RecyclerView.ViewHolder> r05 = new ArrayList(this.mPendingAdditions);
        this.mAdditionsList.add(r05);
        this.mPendingAdditions.clear();
        new DialogsItemAnimator$$ExternalSyntheticLambda2(this, r05).run();
        return;
    }
}
