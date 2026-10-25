package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* loaded from: classes.dex */
public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    final AccessibilityDelegateCompat mItemDelegate;
    final RecyclerView mRecyclerView;

    public static class ItemDelegate extends AccessibilityDelegateCompat {
        final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

        public ItemDelegate(RecyclerViewAccessibilityDelegate r1) {
            this.mRecyclerViewDelegate = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            if (this.mRecyclerViewDelegate.shouldIgnore() == false) goto L5;
            return;
        L5:
            if (this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) goto L9;
            this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(r2, r3);
            return;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r2, int r3, Bundle r4) {
            if (super.performAccessibilityAction(r2, r3, r4) == false) goto L7;
            return true;
        L7:
            if (this.mRecyclerViewDelegate.shouldIgnore() == false) goto L9;
            return false;
        L9:
            if (this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() != null) goto L11;
            return false;
        L11:
            return this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(r2, r3, r4);
        }
    }

    public RecyclerViewAccessibilityDelegate(RecyclerView r1) {
        this.mRecyclerView = r1;
        this.mItemDelegate = new ItemDelegate(this);
    }

    public AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View r2, AccessibilityEvent r3) {
        super.onInitializeAccessibilityEvent(r2, r3);
        if ((r2 instanceof RecyclerView) == true) goto L5;
        return;
    L5:
        if (shouldIgnore() == true) goto L11;
        RecyclerView r22 = (RecyclerView) r2;
        if (r22.getLayoutManager() == null) goto L12;
        r22.getLayoutManager().onInitializeAccessibilityEvent(r3);
        return;
    L12:
        return;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
        super.onInitializeAccessibilityNodeInfo(r1, r2);
        if (shouldIgnore() == false) goto L5;
        return;
    L5:
        if (this.mRecyclerView.getLayoutManager() == null) goto L9;
        this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(r2);
        return;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View r1, int r2, Bundle r3) {
        if (super.performAccessibilityAction(r1, r2, r3) == false) goto L7;
        return true;
    L7:
        if (shouldIgnore() == false) goto L9;
        return false;
    L9:
        if (this.mRecyclerView.getLayoutManager() != null) goto L11;
        return false;
    L11:
        return this.mRecyclerView.getLayoutManager().performAccessibilityAction(r2, r3);
    }

    public boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }
}
