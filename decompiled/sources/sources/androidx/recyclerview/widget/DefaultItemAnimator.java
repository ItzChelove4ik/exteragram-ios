package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildVars;

/* loaded from: classes.dex */
public class DefaultItemAnimator extends SimpleItemAnimator {
    private static final boolean DEBUG = false;
    private static TimeInterpolator sDefaultInterpolator;
    private Runnable animationUpdatesListener;
    protected ArrayList<ChangeInfo> currentChanges;
    protected ArrayList<MoveInfo> currentMoves;
    protected boolean delayAnimations;
    private long delayIncrement;
    protected ArrayList<RecyclerView.ViewHolder> mAddAnimations;
    protected ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList;
    protected ArrayList<RecyclerView.ViewHolder> mChangeAnimations;
    protected ArrayList<ArrayList<ChangeInfo>> mChangesList;
    protected ArrayList<RecyclerView.ViewHolder> mMoveAnimations;
    protected ArrayList<ArrayList<MoveInfo>> mMovesList;
    protected ArrayList<RecyclerView.ViewHolder> mPendingAdditions;
    protected ArrayList<ChangeInfo> mPendingChanges;
    protected ArrayList<MoveInfo> mPendingMoves;
    protected ArrayList<RecyclerView.ViewHolder> mPendingRemovals;
    protected ArrayList<RecyclerView.ViewHolder> mRemoveAnimations;
    protected Interpolator translationInterpolator;

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ArrayList val$moves;

        public AnonymousClass1(DefaultItemAnimator r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$moves = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList r0 = this.val$moves;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            Object r3 = r0.get(r2);
            r2 = r2 + 1;
            MoveInfo r32 = (MoveInfo) r3;
            this.this$0.animateMoveImpl(r32.holder, r32);
            this.this$0.currentMoves.add(r32);
            goto L3
        L5:
            this.val$moves.clear();
            this.this$0.mMovesList.remove(this.val$moves);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ArrayList val$changes;

        public AnonymousClass2(DefaultItemAnimator r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$changes = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList r0 = this.val$changes;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            Object r3 = r0.get(r2);
            r2 = r2 + 1;
            ChangeInfo r32 = (ChangeInfo) r3;
            this.this$0.animateChangeImpl(r32);
            this.this$0.currentChanges.add(r32);
            goto L3
        L5:
            this.val$changes.clear();
            this.this$0.mChangesList.remove(this.val$changes);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ArrayList val$additions;

        public AnonymousClass3(DefaultItemAnimator r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$additions = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int r0 = this.val$additions.size() - 1;
            int r1 = Integer.MAX_VALUE;
        L3:
            ArrayList r2 = this.val$additions;
            if (r0 < 0) goto L6;
            r1 = Math.min(r1, ((RecyclerView.ViewHolder) r2.get(r0)).getAdapterPosition());
            r0 = r0 - 1;
            goto L3
        L6:
            int r02 = r2.size();
        L7:
            r02 = r02 - 1;
            ArrayList r22 = this.val$additions;
            if (r02 < 0) goto L10;
            this.this$0.animateAddImpl((RecyclerView.ViewHolder) r22.get(r02), (r2.getAdapterPosition() - r1) * DefaultItemAnimator.m939$$Nest$fgetdelayIncrement(this.this$0));
            goto L7
        L10:
            r22.clear();
            this.this$0.mAdditionsList.remove(this.val$additions);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass4(DefaultItemAnimator r1, RecyclerView.ViewHolder r2, ViewPropertyAnimator r3, View r4) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$animation = r3;
            this.val$view = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$animation.setListener(null);
            this.val$view.setAlpha(1.0f);
            if (this.this$0.animateByScale(this.val$view) <= 0.0f) goto L5;
            this.val$view.setScaleX(1.0f);
            this.val$view.setScaleY(1.0f);
        L5:
            this.val$view.setTranslationX(0.0f);
            this.val$view.setTranslationY(0.0f);
            this.this$0.onRemoveAnimationUpdate(this.val$holder);
            if (DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0) == null) goto L8;
            DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0).run();
        L8:
            this.this$0.dispatchRemoveFinished(this.val$holder);
            this.this$0.mRemoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchRemoveStarting(this.val$holder);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$5, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass5(DefaultItemAnimator r1, RecyclerView.ViewHolder r2, View r3, ViewPropertyAnimator r4) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$view = r3;
            this.val$animation = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            this.val$view.setAlpha(1.0f);
            if (this.this$0.animateByScale(this.val$view) <= 0.0f) goto L6;
            this.val$view.setScaleX(1.0f);
            this.val$view.setScaleY(1.0f);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$animation.setListener(null);
            this.this$0.onAddAnimationUpdate(this.val$holder);
            if (DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0) == null) goto L5;
            DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0).run();
        L5:
            this.this$0.dispatchAddFinished(this.val$holder);
            this.this$0.mAddAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchAddStarting(this.val$holder);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ int val$deltaX;
        final /* synthetic */ int val$deltaY;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass6(DefaultItemAnimator r1, RecyclerView.ViewHolder r2, int r3, View r4, int r5, ViewPropertyAnimator r6) {
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
            if (this.val$deltaY == 0) goto L9;
            this.val$view.setTranslationY(0.0f);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$animation.setListener(null);
            this.this$0.onMoveAnimationUpdate(this.val$holder);
            if (DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0) == null) goto L5;
            DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0).run();
        L5:
            this.this$0.dispatchMoveFinished(this.val$holder);
            this.this$0.mMoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
            this.this$0.afterAnimateMoveImpl(this.val$holder);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchMoveStarting(this.val$holder);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$7, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ChangeInfo val$changeInfo;
        final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
        final /* synthetic */ View val$view;

        public AnonymousClass7(DefaultItemAnimator r1, ChangeInfo r2, ViewPropertyAnimator r3, View r4) {
            this.this$0 = r1;
            this.val$changeInfo = r2;
            this.val$oldViewAnim = r3;
            this.val$view = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$oldViewAnim.setListener(null);
            this.val$view.setAlpha(1.0f);
            if (this.this$0.animateByScale(this.val$view) <= 0.0f) goto L5;
            this.val$view.setScaleX(1.0f);
            this.val$view.setScaleY(1.0f);
        L5:
            this.val$view.setTranslationX(0.0f);
            this.val$view.setTranslationY(0.0f);
            this.this$0.onChangeAnimationUpdate(this.val$changeInfo.oldHolder);
            if (DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0) == null) goto L8;
            DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0).run();
        L8:
            this.this$0.dispatchChangeFinished(this.val$changeInfo.oldHolder, true);
            this.this$0.mChangeAnimations.remove(this.val$changeInfo.oldHolder);
            this.this$0.dispatchFinishedWhenDone();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.this$0.dispatchChangeStarting(this.val$changeInfo.oldHolder, true);
        }
    }

    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$8, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ DefaultItemAnimator this$0;
        final /* synthetic */ ChangeInfo val$changeInfo;
        final /* synthetic */ View val$newView;
        final /* synthetic */ ViewPropertyAnimator val$newViewAnimation;

        public AnonymousClass8(DefaultItemAnimator r1, ChangeInfo r2, ViewPropertyAnimator r3, View r4) {
            this.this$0 = r1;
            this.val$changeInfo = r2;
            this.val$newViewAnimation = r3;
            this.val$newView = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$newViewAnimation.setListener(null);
            this.val$newView.setAlpha(1.0f);
            if (this.this$0.animateByScale(this.val$newView) <= 0.0f) goto L5;
            this.val$newView.setScaleX(1.0f);
            this.val$newView.setScaleY(1.0f);
        L5:
            this.val$newView.setTranslationX(0.0f);
            this.val$newView.setTranslationY(0.0f);
            this.this$0.onChangeAnimationUpdate(this.val$changeInfo.newHolder);
            if (DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0) == null) goto L8;
            DefaultItemAnimator.m938$$Nest$fgetanimationUpdatesListener(this.this$0).run();
        L8:
            this.this$0.dispatchChangeFinished(this.val$changeInfo.newHolder, false);
            this.this$0.mChangeAnimations.remove(this.val$changeInfo.newHolder);
            this.this$0.dispatchFinishedWhenDone();
            DefaultItemAnimator r32 = this.this$0;
            ChangeInfo r2 = this.val$changeInfo;
            r32.afterAnimateChangeImpl(r2.oldHolder, r2.newHolder);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.this$0.dispatchChangeStarting(this.val$changeInfo.newHolder, false);
        }
    }

    /* loaded from: classes4.dex */
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

    /* renamed from: $r8$lambda$7hwpwi--unu9m6aGQh6I8zySTbI, reason: not valid java name */
    public static /* synthetic */ void m933$r8$lambda$7hwpwiunu9m6aGQh6I8zySTbI(DefaultItemAnimator r0, RecyclerView.ViewHolder r1, ValueAnimator r2) {
        r0.lambda$animateAddImpl$1(r1, r2);
    }

    /* renamed from: $r8$lambda$CZ6bhXrdh991-CVtOmXu6Jhq070, reason: not valid java name */
    public static /* synthetic */ void m934$r8$lambda$CZ6bhXrdh991CVtOmXu6Jhq070(DefaultItemAnimator r0, RecyclerView.ViewHolder r1, ValueAnimator r2) {
        r0.lambda$animateRemoveImpl$0(r1, r2);
    }

    /* renamed from: $r8$lambda$UjykIUCZ2otN2T8yQ9cmn-JQnBw, reason: not valid java name */
    public static /* synthetic */ void m935$r8$lambda$UjykIUCZ2otN2T8yQ9cmnJQnBw(DefaultItemAnimator r0, RecyclerView.ViewHolder r1, ValueAnimator r2) {
        r0.lambda$animateMoveImpl$2(r1, r2);
    }

    /* renamed from: $r8$lambda$ZcJ7nk-5e3IQEIVTkDXb8E43Vr0, reason: not valid java name */
    public static /* synthetic */ void m936$r8$lambda$ZcJ7nk5e3IQEIVTkDXb8E43Vr0(DefaultItemAnimator r0, ChangeInfo r1, ValueAnimator r2) {
        r0.lambda$animateChangeImpl$3(r1, r2);
    }

    /* renamed from: $r8$lambda$q-o3yeEFTJSB_GpxX4y6sM5qgvg, reason: not valid java name */
    public static /* synthetic */ void m937$r8$lambda$qo3yeEFTJSB_GpxX4y6sM5qgvg(DefaultItemAnimator r0, ChangeInfo r1, ValueAnimator r2) {
        r0.lambda$animateChangeImpl$4(r1, r2);
    }

    /* renamed from: -$$Nest$fgetanimationUpdatesListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m938$$Nest$fgetanimationUpdatesListener(DefaultItemAnimator r0) {
        return r0.animationUpdatesListener;
    }

    /* renamed from: -$$Nest$fgetdelayIncrement, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m939$$Nest$fgetdelayIncrement(DefaultItemAnimator r2) {
        return r2.delayIncrement;
    }

    static {
        DEBUG = BuildVars.DEBUG_VERSION;
    }

    public DefaultItemAnimator() {
        this.mPendingRemovals = new ArrayList();
        this.mPendingAdditions = new ArrayList();
        this.mPendingMoves = new ArrayList();
        this.mPendingChanges = new ArrayList();
        this.mAdditionsList = new ArrayList();
        this.mMovesList = new ArrayList();
        this.mChangesList = new ArrayList();
        this.currentMoves = new ArrayList();
        this.currentChanges = new ArrayList();
        this.mAddAnimations = new ArrayList();
        this.mMoveAnimations = new ArrayList();
        this.mRemoveAnimations = new ArrayList();
        this.mChangeAnimations = new ArrayList();
        this.delayAnimations = true;
        this.delayIncrement = 0;
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

    private /* synthetic */ void lambda$animateAddImpl$1(RecyclerView.ViewHolder r1, ValueAnimator r2) {
        onAddAnimationUpdate(r1);
        Runnable r0 = this.animationUpdatesListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$animateChangeImpl$3(ChangeInfo r1, ValueAnimator r2) {
        onChangeAnimationUpdate(r1.oldHolder);
        Runnable r0 = this.animationUpdatesListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$animateChangeImpl$4(ChangeInfo r1, ValueAnimator r2) {
        onChangeAnimationUpdate(r1.newHolder);
        Runnable r0 = this.animationUpdatesListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$animateMoveImpl$2(RecyclerView.ViewHolder r1, ValueAnimator r2) {
        onMoveAnimationUpdate(r1);
        Runnable r0 = this.animationUpdatesListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$animateRemoveImpl$0(RecyclerView.ViewHolder r1, ValueAnimator r2) {
        onRemoveAnimationUpdate(r1);
        Runnable r0 = this.animationUpdatesListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    public void afterAnimateChangeImpl(RecyclerView.ViewHolder r1, RecyclerView.ViewHolder r2) {
    }

    public void afterAnimateMoveImpl(RecyclerView.ViewHolder r1) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(RecyclerView.ViewHolder r4) {
        resetAnimation(r4);
        r4.itemView.setAlpha(0.0f);
        if (animateByScale(r4.itemView) <= 0.0f) goto L5;
        View r0 = r4.itemView;
        r0.setScaleX(1.0f - animateByScale(r0));
        View r02 = r4.itemView;
        r02.setScaleY(1.0f - animateByScale(r02));
    L5:
        this.mPendingAdditions.add(r4);
        checkIsRunning();
        return true;
    }

    public void animateAddImpl(RecyclerView.ViewHolder r3) {
        animateAddImpl(r3, 0);
    }

    public void animateAddImpl(RecyclerView.ViewHolder r6, long r7) {
        View r0 = r6.itemView;
        ViewPropertyAnimator r1 = r0.animate();
        this.mAddAnimations.add(r6);
        r1.alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(getAddDuration()).setStartDelay(getAddDelay() + r7).setInterpolator(getAddInterpolator());
        r1.setUpdateListener(new DefaultItemAnimator$$ExternalSyntheticLambda1(this, r6));
        r1.setListener(new AnonymousClass5(this, r6, r0, r1)).start();
    }

    public float animateByScale(View r1) {
        return 0.0f;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder r8, RecyclerView.ViewHolder r9, RecyclerView.ItemAnimator.ItemHolderInfo r10, int r11, int r12, int r13, int r14) {
        if (r8 == r9) goto L4;
        float r7 = r8.itemView.getTranslationX();
        float r142 = r8.itemView.getTranslationY();
        float r1 = r8.itemView.getAlpha();
        resetAnimation(r8);
        int r2 = (int) ((r13 - r11) - r7);
        int r3 = (int) ((r14 - r12) - r142);
        r8.itemView.setTranslationX(r7);
        r8.itemView.setTranslationY(r142);
        r8.itemView.setAlpha(r1);
        if (r9 == null) goto L10;
        resetAnimation(r9);
        r9.itemView.setTranslationX(-r2);
        r9.itemView.setTranslationY(-r3);
        r9.itemView.setAlpha(0.5f);
        if (animateByScale(r9.itemView) <= 0.0f) goto L10;
        View r72 = r9.itemView;
        r72.setScaleX(1.0f - animateByScale(r72));
        View r73 = r9.itemView;
        r73.setScaleY(1.0f - animateByScale(r73));
    L10:
        this.mPendingChanges.add(new ChangeInfo(r8, r9, r11, r12, r13, r14));
        checkIsRunning();
        return true;
    L4:
        return animateMove(r8, r10, r11, r12, r13, r14);
    }

    public void animateChangeImpl(ChangeInfo r3) {
        animateChangeImpl(r3, 0);
    }

    public void animateChangeImpl(ChangeInfo r12, long r13) {
        RecyclerView.ViewHolder r0 = r12.oldHolder;
        View r1 = null;
        if (r0 != null) goto L5;
        View r2 = null;
    L6:
        RecyclerView.ViewHolder r3 = r12.newHolder;
        if (r3 == null) goto L9;
        r1 = r3.itemView;
    L9:
        beforeAnimateChangeImpl(r0, r3);
        if (r2 == null) goto L15;
        ViewPropertyAnimator r4 = r2.animate().setDuration(getChangeRemoveDuration()).setStartDelay(getChangeDelay());
        this.mChangeAnimations.add(r12.oldHolder);
        r4.translationX(r12.toX - r12.fromX);
        r4.translationY(r12.toY - r12.fromY);
        r4.alpha(0.0f);
        if (animateByScale(r2) <= 0.0f) goto L14;
        r4.scaleX(1.0f - animateByScale(r2)).scaleY(1.0f - animateByScale(r2));
    L14:
        r4.setUpdateListener(new DefaultItemAnimator$$ExternalSyntheticLambda2(this, r12));
        r4.setStartDelay(r13).setInterpolator(getChangeInterpolator()).setListener(new AnonymousClass7(this, r12, r4, r2)).start();
    L15:
        if (r1 == null) goto L21;
        ViewPropertyAnimator r22 = r1.animate();
        this.mChangeAnimations.add(r12.newHolder);
        r22.translationX(0.0f).translationY(0.0f).setDuration(getChangeAddDuration()).setStartDelay((getChangeDelay() + (getChangeDuration() - getChangeAddDuration())) + r13).setInterpolator(getChangeInterpolator()).alpha(1.0f);
        if (animateByScale(r1) <= 0.0f) goto L19;
        r22.scaleX(1.0f).scaleY(1.0f);
    L19:
        r22.setUpdateListener(new DefaultItemAnimator$$ExternalSyntheticLambda3(this, r12));
        r22.setListener(new AnonymousClass8(this, r12, r22, r1)).start();
        return;
    L21:
        return;
    L5:
        r2 = r0.itemView;
        goto L6
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
        this.mPendingMoves.add(new MoveInfo(r8, r3, r4, r12, r13));
        checkIsRunning();
        return true;
    }

    public void animateMoveImpl(RecyclerView.ViewHolder r3, MoveInfo r4) {
        animateMoveImpl(r3, r4, 0);
    }

    public void animateMoveImpl(RecyclerView.ViewHolder r11, MoveInfo r12, long r13) {
        int r0 = r12.fromX;
        int r1 = r12.fromY;
        int r2 = r12.toX;
        int r122 = r12.toY;
        View r7 = r11.itemView;
        int r6 = r2 - r0;
        int r8 = r122 - r1;
        if (r6 == 0) goto L5;
        r7.animate().translationX(0.0f);
    L5:
        if (r8 == 0) goto L7;
        r7.animate().translationY(0.0f);
    L7:
        ViewPropertyAnimator r9 = r7.animate();
        this.mMoveAnimations.add(r11);
        r9.setUpdateListener(new DefaultItemAnimator$$ExternalSyntheticLambda4(this, r11));
        Interpolator r123 = this.translationInterpolator;
        if (r123 == null) goto L10;
        r9.setInterpolator(r123);
    L11:
        beforeAnimateMoveImpl(r11);
        r9.setDuration(getMoveDuration()).setStartDelay(getMoveDelay() + r13).setListener(new AnonymousClass6(this, r11, r6, r7, r8, r9)).start();
        return;
    L10:
        r9.setInterpolator(getMoveInterpolator());
        goto L11
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(RecyclerView.ViewHolder r1, RecyclerView.ItemAnimator.ItemHolderInfo r2) {
        resetAnimation(r1);
        this.mPendingRemovals.add(r1);
        checkIsRunning();
        return true;
    }

    public void animateRemoveImpl(RecyclerView.ViewHolder r3) {
        animateRemoveImpl(r3, 0);
    }

    public void animateRemoveImpl(RecyclerView.ViewHolder r7, long r8) {
        View r0 = r7.itemView;
        ViewPropertyAnimator r1 = r0.animate();
        this.mRemoveAnimations.add(r7);
        if (getRemoveDelay() <= 0) goto L5;
        r0.bringToFront();
    L5:
        r1.setDuration(getRemoveDuration()).setStartDelay(getRemoveDelay() + r8).setInterpolator(getRemoveInterpolator()).alpha(0.0f).scaleX(1.0f - animateByScale(r0)).scaleY(1.0f - animateByScale(r0));
        r1.setUpdateListener(new DefaultItemAnimator$$ExternalSyntheticLambda0(this, r7));
        r1.setListener(new AnonymousClass4(this, r7, r1, r0)).start();
    }

    public void beforeAnimateChangeImpl(RecyclerView.ViewHolder r1, RecyclerView.ViewHolder r2) {
    }

    public void beforeAnimateMoveImpl(RecyclerView.ViewHolder r1) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder r2, List<Object> r3) {
        if (r3.isEmpty() == true) goto L5;
        return true;
    L5:
        if (super.canReuseUpdatedViewHolder(r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void cancelAll(List<RecyclerView.ViewHolder> r2) {
        int r1 = r2.size() - 1;
    L3:
        if (r1 < 0) goto L5;
        r2.get(r1).itemView.animate().cancel();
        r1 = r1 - 1;
        goto L3
    }

    public void checkIsRunning() {
    }

    public void dispatchFinishedWhenDone() {
        if (isRunning() == true) goto L6;
        dispatchAnimationsFinished();
        onAllAnimationsDone();
        this.currentMoves.clear();
        this.currentChanges.clear();
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
        if (this.mPendingRemovals.remove(r8) == false) goto L13;
        r0.setAlpha(1.0f);
        r0.setScaleX(1.0f);
        r0.setScaleY(1.0f);
        dispatchRemoveFinished(r8);
    L13:
        if (this.mPendingAdditions.remove(r8) == false) goto L15;
        r0.setAlpha(1.0f);
        r0.setScaleX(1.0f);
        r0.setScaleY(1.0f);
        dispatchAddFinished(r8);
    L15:
        int r12 = this.mChangesList.size() - 1;
    L16:
        if (r12 < 0) goto L21;
        ArrayList<ChangeInfo> r4 = this.mChangesList.get(r12);
        endChangeAnimation(r4, r8);
        if (r4.isEmpty() == false) goto L20;
        this.mChangesList.remove(r12);
    L20:
        r12 = r12 - 1;
        goto L16
    L21:
        int r13 = this.mMovesList.size() - 1;
    L22:
        if (r13 < 0) goto L32;
        ArrayList<MoveInfo> r42 = this.mMovesList.get(r13);
        int r5 = r42.size() - 1;
    L24:
        if (r5 < 0) goto L31;
        if (r42.get(r5).holder == r8) goto L27;
        r5 = r5 - 1;
        goto L24
    L27:
        r0.setTranslationY(0.0f);
        r0.setTranslationX(0.0f);
        dispatchMoveFinished(r8);
        r42.remove(r5);
        if (r42.isEmpty() == false) goto L31;
        this.mMovesList.remove(r13);
    L31:
        r13 = r13 - 1;
        goto L22
    L32:
        int r14 = this.mAdditionsList.size() - 1;
    L33:
        if (r14 < 0) goto L44;
        ArrayList<RecyclerView.ViewHolder> r43 = this.mAdditionsList.get(r14);
        if (r43.remove(r8) == false) goto L42;
        r0.setAlpha(1.0f);
        if (animateByScale(r0) <= 0.0f) goto L39;
        r0.setScaleX(1.0f);
        r0.setScaleY(1.0f);
    L39:
        dispatchAddFinished(r8);
        if (r43.isEmpty() == false) goto L42;
        this.mAdditionsList.remove(r14);
    L42:
        r14 = r14 - 1;
        goto L33
    L44:
        if (this.mRemoveAnimations.remove(r8) == false) goto L51;
        if (BuildVars.DEBUG_VERSION == false) goto L51;
        Segment$$ExternalSyntheticBUOutline1.m("after animation is cancelled, item should not be in mRemoveAnimations list");
        return;
    L51:
        if (this.mAddAnimations.remove(r8) == false) goto L58;
        if (BuildVars.DEBUG_VERSION == false) goto L58;
        Segment$$ExternalSyntheticBUOutline1.m("after animation is cancelled, item should not be in mAddAnimations list");
        return;
    L58:
        if (this.mChangeAnimations.remove(r8) == false) goto L65;
        if (BuildVars.DEBUG_VERSION == false) goto L65;
        Segment$$ExternalSyntheticBUOutline1.m("after animation is cancelled, item should not be in mChangeAnimations list");
        return;
    L65:
        if (this.mMoveAnimations.remove(r8) == true) goto L67;
    L71:
        dispatchFinishedWhenDone();
        return;
    L67:
        if (BuildVars.DEBUG_VERSION == false) goto L71;
        Segment$$ExternalSyntheticBUOutline1.m("after animation is cancelled, item should not be in mMoveAnimations list");
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
        dispatchRemoveFinished(this.mPendingRemovals.get(r02));
        this.mPendingRemovals.remove(r02);
        r02 = r02 - 1;
        goto L7
    L9:
        int r03 = this.mPendingAdditions.size();
    L10:
        r03 = r03 - 1;
        if (r03 < 0) goto L16;
        RecyclerView.ViewHolder r32 = this.mPendingAdditions.get(r03);
        r32.itemView.setAlpha(1.0f);
        if (animateByScale(r32.itemView) <= 0.0f) goto L15;
        r32.itemView.setScaleX(1.0f);
        r32.itemView.setScaleY(1.0f);
    L15:
        dispatchAddFinished(r32);
        this.mPendingAdditions.remove(r03);
        goto L10
    L16:
        int r04 = this.mPendingChanges.size();
    L17:
        r04 = r04 - 1;
        ArrayList<ChangeInfo> r33 = this.mPendingChanges;
        if (r04 < 0) goto L20;
        endChangeAnimationIfNecessary(r33.get(r04));
        goto L17
    L20:
        r33.clear();
        if (isRunning() == true) goto L23;
        return;
    L23:
        int r05 = this.mMovesList.size() - 1;
    L24:
        if (r05 < 0) goto L32;
        ArrayList<MoveInfo> r34 = this.mMovesList.get(r05);
        int r4 = r34.size() - 1;
    L26:
        if (r4 < 0) goto L31;
        MoveInfo r5 = r34.get(r4);
        View r6 = r5.holder.itemView;
        r6.setTranslationY(0.0f);
        r6.setTranslationX(0.0f);
        dispatchMoveFinished(r5.holder);
        r34.remove(r4);
        if (r34.isEmpty() == false) goto L30;
        this.mMovesList.remove(r34);
    L30:
        r4 = r4 - 1;
        goto L26
    L31:
        r05 = r05 - 1;
        goto L24
    L32:
        int r06 = this.mAdditionsList.size() - 1;
    L33:
        if (r06 < 0) goto L44;
        ArrayList<RecyclerView.ViewHolder> r35 = this.mAdditionsList.get(r06);
        int r42 = r35.size() - 1;
    L35:
        if (r42 < 0) goto L43;
        RecyclerView.ViewHolder r52 = r35.get(r42);
        View r62 = r52.itemView;
        r62.setAlpha(1.0f);
        if (animateByScale(r62) <= 0.0f) goto L39;
        r62.setScaleX(1.0f);
        r62.setScaleY(1.0f);
    L39:
        dispatchAddFinished(r52);
        r35.remove(r42);
        if (r35.isEmpty() == false) goto L42;
        this.mAdditionsList.remove(r35);
    L42:
        r42 = r42 - 1;
        goto L35
    L43:
        r06 = r06 - 1;
        goto L33
    L44:
        int r07 = this.mChangesList.size() - 1;
    L45:
        if (r07 < 0) goto L53;
        ArrayList<ChangeInfo> r1 = this.mChangesList.get(r07);
        int r22 = r1.size() - 1;
    L47:
        if (r22 < 0) goto L52;
        endChangeAnimationIfNecessary(r1.get(r22));
        if (r1.isEmpty() == false) goto L51;
        this.mChangesList.remove(r1);
    L51:
        r22 = r22 - 1;
        goto L47
    L52:
        r07 = r07 - 1;
        goto L45
    L53:
        cancelAll(this.mRemoveAnimations);
        cancelAll(this.mMoveAnimations);
        cancelAll(this.mAddAnimations);
        cancelAll(this.mChangeAnimations);
        dispatchAnimationsFinished();
    }

    public void endChangeAnimationIfNecessary(ChangeInfo r2) {
        RecyclerView.ViewHolder r0 = r2.oldHolder;
        if (r0 == null) goto L5;
        endChangeAnimationIfNecessary(r2, r0);
    L5:
        RecyclerView.ViewHolder r02 = r2.newHolder;
        if (r02 == null) goto L9;
        endChangeAnimationIfNecessary(r2, r02);
        return;
    }

    public boolean endChangeAnimationIfNecessary(ChangeInfo r5, RecyclerView.ViewHolder r6) {
        boolean r3 = false;
        if (r5.newHolder != r6) goto L6;
        r5.newHolder = null;
    L8:
        r6.itemView.setAlpha(1.0f);
        if (animateByScale(r6.itemView) <= 0.0f) goto L11;
        r6.itemView.setScaleX(1.0f);
        r6.itemView.setScaleY(1.0f);
    L11:
        r6.itemView.setTranslationX(0.0f);
        r6.itemView.setTranslationY(0.0f);
        dispatchChangeFinished(r6, r3);
        return true;
    L6:
        if (r5.oldHolder != r6) goto L13;
        r5.oldHolder = null;
        r3 = true;
        goto L8
    L13:
        return false;
    }

    public long getAddAnimationDelay(long r1, long r3, long r5) {
        return r1 + Math.max(r3, r5);
    }

    public long getMoveAnimationDelay() {
        return getRemoveDuration();
    }

    public float getTargetY(View r4) {
        int r0 = this.currentMoves.size() - 1;
    L3:
        if (r0 < 0) goto L9;
        if (this.currentMoves.get(r0).holder.itemView == r4) goto L7;
        r0 = r0 - 1;
        goto L3
    L7:
        return Math.min(Math.min(r1.toY, r1.fromY), r4.getY());
    L9:
        int r02 = this.currentChanges.size() - 1;
    L10:
        if (r02 < 0) goto L20;
        ChangeInfo r1 = this.currentChanges.get(r02);
        if (r1.oldHolder.itemView == r4) goto L18;
        if (r1.newHolder.itemView == r4) goto L18;
        r02 = r02 - 1;
    L18:
        return Math.min(Math.min(r1.toY, r1.fromY), r4.getY());
    L20:
        return r4.getY();
    }

    public boolean isHolderAdding(RecyclerView.ViewHolder r1) {
        return this.mAddAnimations.contains(r1);
    }

    public boolean isHolderRemoving(RecyclerView.ViewHolder r1) {
        return this.mRemoveAnimations.contains(r1);
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
        if (this.mPendingRemovals.isEmpty() == true) goto L11;
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

    public void listenToAnimationUpdates(Runnable r1) {
        this.animationUpdatesListener = r1;
    }

    public void onAddAnimationUpdate(RecyclerView.ViewHolder r1) {
    }

    public void onAllAnimationsDone() {
    }

    public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
    }

    public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
    }

    public void onRemoveAnimationUpdate(RecyclerView.ViewHolder r1) {
    }

    public void resetAnimation(RecyclerView.ViewHolder r3) {
        if (sDefaultInterpolator != null) goto L5;
        sDefaultInterpolator = new ValueAnimator().getInterpolator();
    L5:
        r3.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(r3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
        boolean r1 = this.mPendingRemovals.isEmpty();
        boolean r2 = this.mPendingMoves.isEmpty();
        boolean r3 = this.mPendingChanges.isEmpty();
        boolean r4 = this.mPendingAdditions.isEmpty();
        if (r1 == false) goto L8;
        if (r2 == false) goto L8;
        if (r4 == false) goto L8;
        if (r3 == false) goto L8;
        return;
    L8:
        ArrayList<RecyclerView.ViewHolder> r5 = this.mPendingRemovals;
        int r6 = r5.size();
        int r8 = 0;
    L9:
        if (r8 >= r6) goto L11;
        RecyclerView.ViewHolder r9 = r5.get(r8);
        r8 = r8 + 1;
        animateRemoveImpl(r9);
        goto L9
    L11:
        this.mPendingRemovals.clear();
        if (r2 == true) goto L18;
        ArrayList<MoveInfo> r52 = new ArrayList();
        r52.addAll(this.mPendingMoves);
        this.mMovesList.add(r52);
        this.mPendingMoves.clear();
        AnonymousClass1 r62 = new AnonymousClass1(this, r52);
        if (this.delayAnimations == false) goto L17;
        if (r1 == true) goto L17;
        ViewCompat.postOnAnimationDelayed(r52.get(0).holder.itemView, r62, getMoveAnimationDelay());
    L17:
        r62.run();
    L18:
        if (r3 == true) goto L24;
        ArrayList<ChangeInfo> r53 = new ArrayList();
        r53.addAll(this.mPendingChanges);
        this.mChangesList.add(r53);
        this.mPendingChanges.clear();
        AnonymousClass2 r63 = new AnonymousClass2(this, r53);
        if (this.delayAnimations == false) goto L23;
        if (r1 == true) goto L23;
        ViewCompat.postOnAnimationDelayed(r53.get(0).oldHolder.itemView, r63, getRemoveDuration());
    L23:
        r63.run();
    L24:
        if (r4 == true) goto L44;
        ArrayList<RecyclerView.ViewHolder> r82 = new ArrayList();
        r82.addAll(this.mPendingAdditions);
        this.mAdditionsList.add(r82);
        this.mPendingAdditions.clear();
        AnonymousClass3 r92 = new AnonymousClass3(this, r82);
        if (this.delayAnimations == false) goto L41;
        if (r1 == false) goto L30;
        if (r2 == false) goto L30;
        if (r3 == true) goto L41;
    L30:
        long r42 = 0;
        if (r1 == true) goto L33;
        long r10 = getRemoveDuration();
    L34:
        if (r2 == true) goto L36;
        long r12 = getMoveDuration();
    L37:
        if (r3 == true) goto L39;
        r42 = getChangeDuration();
    L39:
        long r32 = r12;
        ViewCompat.postOnAnimationDelayed(r82.get(0).itemView, r92, getAddAnimationDelay(r10, r32, r42));
        return;
    L36:
        r12 = 0;
        goto L37
    L33:
        r10 = 0;
    L41:
        r92.run();
        return;
    }

    public void setDelayAnimations(boolean r1) {
        this.delayAnimations = r1;
    }

    public void setDelayIncrement(long r1) {
        this.delayIncrement = r1;
    }

    public void setTranslationInterpolator(Interpolator r1) {
        this.translationInterpolator = r1;
    }
}
