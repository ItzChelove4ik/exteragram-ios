package org.telegram.ui.Components.chat;

import java.util.Arrays;
import java.util.Iterator;
import me.vkryl.android.AnimatorUtils;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import me.vkryl.core.BitwiseUtils;

/* loaded from: classes7.dex */
public class ChatActivityBottomViewsVisibilityController implements ReplaceAnimator.Callback {
    private final Runnable onValuesChanged;
    private final ReplaceAnimator<Integer> replaceAnimator;
    private int visibilityFlags;
    private final float[] visibilityValues;

    public ChatActivityBottomViewsVisibilityController(Runnable r5) {
        this.visibilityValues = new float[32];
        this.visibilityFlags = 1;
        this.replaceAnimator = new ReplaceAnimator(this, AnimatorUtils.DECELERATE_INTERPOLATOR, 240);
        this.onValuesChanged = r5;
    }

    private void onItemChanged() {
        Arrays.fill(this.visibilityValues, 0.0f);
        Iterator<ListAnimator.Entry<Integer>> r0 = this.replaceAnimator.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ListAnimator.Entry<Integer> r1 = r0.next();
        int r2 = r1.item.intValue();
        this.visibilityValues[r2] = r1.getVisibility();
        goto L4
    L6:
        this.onValuesChanged.run();
    }

    public int getCurrentPriorityContainerId() {
        return 31 - Integer.numberOfLeadingZeros(this.visibilityFlags);
    }

    public float getVisibility(int r1) {
        return this.visibilityValues[r1];
    }

    @Override // me.vkryl.android.animator.ReplaceAnimator.Callback
    public void onForceApplyChanges(ReplaceAnimator<?> r1) {
        onItemChanged();
    }

    @Override // me.vkryl.android.animator.ReplaceAnimator.Callback
    public void onItemChanged(ReplaceAnimator<?> r1) {
        onItemChanged();
    }

    public void setViewVisible(int r4, boolean r5, boolean r6) {
        int r0 = getCurrentPriorityContainerId();
        this.visibilityFlags = BitwiseUtils.setFlag(this.visibilityFlags, 1 << r4, r5);
        int r42 = getCurrentPriorityContainerId();
        if (r0 == r42) goto L6;
        this.replaceAnimator.replace(Integer.valueOf(r42), r6);
        return;
    }
}
