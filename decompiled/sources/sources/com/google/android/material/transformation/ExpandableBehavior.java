package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.expandable.ExpandableWidget;
import java.util.List;

@Deprecated
/* loaded from: classes5.dex */
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
    private static final int STATE_COLLAPSED = 2;
    private static final int STATE_EXPANDED = 1;
    private static final int STATE_UNINITIALIZED = 0;
    private int currentState;

    /* renamed from: com.google.android.material.transformation.ExpandableBehavior$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ ExpandableBehavior this$0;
        final /* synthetic */ View val$child;
        final /* synthetic */ ExpandableWidget val$dep;
        final /* synthetic */ int val$expectedState;

        public AnonymousClass1(ExpandableBehavior r1, View r2, int r3, ExpandableWidget r4) {
            this.this$0 = r1;
            this.val$child = r2;
            this.val$expectedState = r3;
            this.val$dep = r4;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.val$child.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.access$000(this.this$0) != this.val$expectedState) goto L5;
            ExpandableBehavior r0 = this.this$0;
            ExpandableWidget r1 = this.val$dep;
            r0.onExpandedStateChange((View) r1, this.val$child, r1.isExpanded(), false);
        L5:
            return false;
        }
    }

    public ExpandableBehavior() {
        this.currentState = 0;
    }

    public ExpandableBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.currentState = 0;
    }

    public static /* synthetic */ int access$000(ExpandableBehavior r0) {
        return r0.currentState;
    }

    private boolean didStateChange(boolean r3) {
        int r2 = this.currentState;
        if (r3 == false) goto L10;
        if (r2 != 0) goto L6;
    L9:
        return true;
    L6:
        if (r2 == 2) goto L9;
        return false;
    L10:
        if (r2 != 1) goto L12;
        return true;
    L12:
        return false;
    }

    public static <T extends ExpandableBehavior> T from(View r2, Class<T> r3) {
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof CoordinatorLayout.LayoutParams) == false) goto L10;
        CoordinatorLayout.Behavior r23 = ((CoordinatorLayout.LayoutParams) r22).getBehavior();
        if ((r23 instanceof ExpandableBehavior) == true) goto L7;
        f$$ExternalSyntheticBUOutline1.m("The view is not associated with ExpandableBehavior");
        return null;
    L7:
        return r3.cast(r23);
    L10:
        f$$ExternalSyntheticBUOutline1.m("The view is not a child of CoordinatorLayout");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExpandableWidget findExpandableWidget(CoordinatorLayout r6, View r7) {
        List<View> r0 = r6.getDependencies(r7);
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        View r3 = r0.get(r2);
        if (layoutDependsOn(r6, r7, r3) == true) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        return (ExpandableWidget) r3;
    L9:
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public abstract boolean layoutDependsOn(CoordinatorLayout r1, View r2, View r3);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout r2, View r3, View r4) {
        ExpandableWidget r42 = (ExpandableWidget) r4;
        if (didStateChange(r42.isExpanded()) == true) goto L5;
        return false;
    L5:
        if (r42.isExpanded() == false) goto L7;
        int r22 = 1;
    L8:
        this.currentState = r22;
        return onExpandedStateChange((View) r42, r3, r42.isExpanded(), true);
    L7:
        r22 = 2;
        goto L8
    }

    public abstract boolean onExpandedStateChange(View r1, View r2, boolean r3, boolean r4);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r3, View r4, int r5) {
        if (r4.isLaidOut() == true) goto L15;
        ExpandableWidget r32 = findExpandableWidget(r3, r4);
        if (r32 != null) goto L7;
        return false;
    L7:
        if (didStateChange(r32.isExpanded()) == true) goto L9;
        return false;
    L9:
        if (r32.isExpanded() == false) goto L11;
        int r52 = 1;
    L12:
        this.currentState = r52;
        r4.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1(this, r4, r52, r32));
        return false;
    L11:
        r52 = 2;
        goto L12
    L15:
        return false;
    }
}
