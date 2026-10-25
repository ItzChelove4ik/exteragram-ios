package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {
    private static final boolean DEBUG = false;
    private static final String TAG = "SimpleItemAnimator";
    protected boolean alwaysCreateMoveAnimationIfPossible;
    protected boolean disabledMoveAnimations;
    boolean mSupportsChangeAnimations;

    public SimpleItemAnimator() {
        this.mSupportsChangeAnimations = true;
    }

    private int getLastNotRemovedPosition(RecyclerView r4, int r5) {
        int r3 = -1;
        if (r4 == null) goto L23;
        if (r5 == (-1)) goto L23;
        int r0 = 0;
    L8:
        if (r0 >= r4.getChildCount()) goto L23;
        View r1 = r4.getChildAt(r0);
        if (r1 == null) goto L22;
        RecyclerView.ViewHolder r12 = r4.getChildViewHolder(r1);
        if (r12 == null) goto L22;
        if (r12.isRemoved() == true) goto L22;
        int r13 = r12.mOldOldPosition;
        if (r13 < 0) goto L22;
        if (r13 >= r5) goto L22;
        if (r13 <= r3) goto L22;
        r3 = r13;
    L22:
        r0 = r0 + 1;
    L23:
        return r3;
    }

    public abstract boolean animateAdd(RecyclerView.ViewHolder r1);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateAppearance(RecyclerView.ViewHolder r9, RecyclerView.ItemAnimator.ItemHolderInfo r10, RecyclerView.ItemAnimator.ItemHolderInfo r11) {
        if (this.disabledMoveAnimations == true) goto L16;
        if (r10 == null) goto L16;
        int r4 = r10.left;
        int r6 = r11.left;
        if (r4 != r6) goto L14;
        if (r10.top != r11.top) goto L14;
        if (this.alwaysCreateMoveAnimationIfPossible == false) goto L16;
    L14:
        return animateMove(r9, r10, r4, r10.top, r6, r11.top);
    L16:
        return animateAdd(r9);
    }

    public abstract boolean animateChange(RecyclerView.ViewHolder r1, RecyclerView.ViewHolder r2, RecyclerView.ItemAnimator.ItemHolderInfo r3, int r4, int r5, int r6, int r7);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder r9, RecyclerView.ViewHolder r10, RecyclerView.ItemAnimator.ItemHolderInfo r11, RecyclerView.ItemAnimator.ItemHolderInfo r12) {
        int r4 = r11.left;
        int r5 = r11.top;
        if (r10.shouldIgnore() == false) goto L5;
        int r6 = r11.left;
        r12 = r11;
    L7:
        return animateChange(r9, r10, r11, r4, r5, r6, r12.top);
    L5:
        r6 = r12.left;
        goto L7
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateDisappearance(RecyclerView r8, RecyclerView.ViewHolder r9, RecyclerView.ItemAnimator.ItemHolderInfo r10, RecyclerView.ItemAnimator.ItemHolderInfo r11) {
        int r3 = r10.left;
        int r4 = r10.top;
        View r0 = r9.itemView;
        if (r11 != null) goto L6;
        int r1 = r0.getLeft();
    L5:
        int r5 = r1;
        if (r11 != null) goto L10;
        int r112 = r0.getTop();
    L9:
        int r6 = r112;
        if (this.disabledMoveAnimations == true) goto L21;
        if (r9.isRemoved() == true) goto L21;
        if (r3 != r5) goto L19;
        if (r4 == r6) goto L21;
    L19:
        r0.layout(r5, r6, r0.getWidth() + r5, r0.getHeight() + r6);
        return animateMove(r9, r10, r3, r4, r5, r6);
    L21:
        int r7 = getLastNotRemovedPosition(r8, r9.mOldOldPosition);
        r9.mOldCompoundPosition = (r7 * 1000) + (r9.mOldOldPosition - r7);
        return animateRemove(r9, r10);
    L10:
        r112 = r11.top;
        goto L9
    L6:
        r1 = r11.left;
        goto L5
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder r1, RecyclerView.ItemAnimator.ItemHolderInfo r2, int r3, int r4, int r5, int r6);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animatePersistence(RecyclerView.ViewHolder r9, RecyclerView.ItemAnimator.ItemHolderInfo r10, RecyclerView.ItemAnimator.ItemHolderInfo r11) {
        if (this.disabledMoveAnimations == true) goto L12;
        int r4 = r10.left;
        int r6 = r11.left;
        if (r4 != r6) goto L11;
        if (r10.top == r11.top) goto L12;
    L11:
        return animateMove(r9, r10, r4, r10.top, r6, r11.top);
    L12:
        dispatchMoveFinished(r9);
        return false;
    }

    public abstract boolean animateRemove(RecyclerView.ViewHolder r1, RecyclerView.ItemAnimator.ItemHolderInfo r2);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder r1) {
        if (this.mSupportsChangeAnimations == true) goto L5;
        return true;
    L5:
        if (r1.isInvalid() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final void dispatchAddFinished(RecyclerView.ViewHolder r1) {
        onAddFinished(r1);
        dispatchAnimationFinished(r1);
    }

    public final void dispatchAddStarting(RecyclerView.ViewHolder r1) {
        onAddStarting(r1);
    }

    public final void dispatchChangeFinished(RecyclerView.ViewHolder r1, boolean r2) {
        onChangeFinished(r1, r2);
        dispatchAnimationFinished(r1);
    }

    public final void dispatchChangeStarting(RecyclerView.ViewHolder r1, boolean r2) {
        onChangeStarting(r1, r2);
    }

    public final void dispatchMoveFinished(RecyclerView.ViewHolder r1) {
        onMoveFinished(r1);
        dispatchAnimationFinished(r1);
    }

    public final void dispatchMoveStarting(RecyclerView.ViewHolder r1) {
        onMoveStarting(r1);
    }

    public final void dispatchRemoveFinished(RecyclerView.ViewHolder r1) {
        onRemoveFinished(r1);
        dispatchAnimationFinished(r1);
    }

    public final void dispatchRemoveStarting(RecyclerView.ViewHolder r1) {
        onRemoveStarting(r1);
    }

    public boolean getSupportsChangeAnimations() {
        return this.mSupportsChangeAnimations;
    }

    public void onAddFinished(RecyclerView.ViewHolder r1) {
    }

    public void onAddStarting(RecyclerView.ViewHolder r1) {
    }

    public void onChangeFinished(RecyclerView.ViewHolder r1, boolean r2) {
    }

    public void onChangeStarting(RecyclerView.ViewHolder r1, boolean r2) {
    }

    public void onMoveFinished(RecyclerView.ViewHolder r1) {
    }

    public void onMoveStarting(RecyclerView.ViewHolder r1) {
    }

    public void onRemoveFinished(RecyclerView.ViewHolder r1) {
    }

    public void onRemoveStarting(RecyclerView.ViewHolder r1) {
    }

    public void setSupportsChangeAnimations(boolean r1) {
        this.mSupportsChangeAnimations = r1;
    }
}
