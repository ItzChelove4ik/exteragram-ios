package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.os.Build;
import android.widget.EdgeEffect;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public final class EdgeEffectTrackerFactory extends RecyclerView.EdgeEffectFactory {
    private final TrackingEdgeEffect[] edgeEffects;
    private final ArrayList<OnEdgeEffectListener> listeners;

    public interface OnEdgeEffectListener {
        void onEdgeEffectVisibilityChange(int r1, boolean r2);
    }

    public static final class TrackingEdgeEffect extends EdgeEffect {
        private final int direction;
        private boolean lastVisibility;
        private final OnEdgeEffectListener listener;
        private final Runnable mCheckEdgeVisibility;
        private final RecyclerView view;

        public static /* synthetic */ void $r8$lambda$G1OeVQw5DUwVNnt8kT1FBlhHTuY(TrackingEdgeEffect r0) {
            r0.checkEdgeVisibility();
        }

        public TrackingEdgeEffect(RecyclerView r2, int r3, OnEdgeEffectListener r4) {
            super(r2.getContext());
            this.mCheckEdgeVisibility = new EdgeEffectTrackerFactory$TrackingEdgeEffect$$ExternalSyntheticLambda0(this);
            this.view = r2;
            this.direction = r3;
            this.listener = r4;
        }

        private void checkEdgeVisibility() {
            boolean r0 = isVisible();
            if (this.lastVisibility == r0) goto L8;
            this.lastVisibility = r0;
            OnEdgeEffectListener r1 = this.listener;
            if (r1 == null) goto L9;
            r1.onEdgeEffectVisibilityChange(this.direction, r0);
            return;
        L9:
            return;
        }

        @Override // android.widget.EdgeEffect
        public boolean draw(Canvas r2) {
            boolean r22 = super.draw(r2);
            this.view.postOnAnimation(this.mCheckEdgeVisibility);
            return r22;
        }

        @Override // android.widget.EdgeEffect
        public void finish() {
            super.finish();
            checkEdgeVisibility();
        }

        public boolean isVisible() {
            if (isFinished() == false) goto L5;
            return false;
        L5:
            if (Build.VERSION.SDK_INT >= 31) goto L7;
            return true;
        L7:
            if (getDistance() == 0.0f) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // android.widget.EdgeEffect
        public void onAbsorb(int r1) {
            super.onAbsorb(r1);
            checkEdgeVisibility();
        }

        @Override // android.widget.EdgeEffect
        public void onPull(float r1) {
            super.onPull(r1);
            checkEdgeVisibility();
        }

        @Override // android.widget.EdgeEffect
        public void onPull(float r1, float r2) {
            super.onPull(r1, r2);
            checkEdgeVisibility();
        }

        @Override // android.widget.EdgeEffect
        public float onPullDistance(float r1, float r2) {
            float r12 = super.onPullDistance(r1, r2);
            checkEdgeVisibility();
            return r12;
        }

        @Override // android.widget.EdgeEffect
        public void onRelease() {
            super.onRelease();
            checkEdgeVisibility();
        }

        @Override // android.widget.EdgeEffect
        public void setSize(int r1, int r2) {
            super.setSize(r1, r2);
            checkEdgeVisibility();
        }
    }

    /* renamed from: $r8$lambda$mcDHJKNrZmiJOLsXJ_d-cHO8dfo, reason: not valid java name */
    public static /* synthetic */ void m10133$r8$lambda$mcDHJKNrZmiJOLsXJ_dcHO8dfo(EdgeEffectTrackerFactory r0, int r1, boolean r2) {
        r0.onEdgeEffectVisibilityChange(r1, r2);
    }

    public EdgeEffectTrackerFactory() {
        this.edgeEffects = new TrackingEdgeEffect[4];
        this.listeners = new ArrayList();
    }

    private void onEdgeEffectVisibilityChange(int r4, boolean r5) {
        ArrayList<OnEdgeEffectListener> r3 = this.listeners;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        OnEdgeEffectListener r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onEdgeEffectVisibilityChange(r4, r5);
        goto L3
    }

    public void addEdgeEffectListener(OnEdgeEffectListener r1) {
        this.listeners.add(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory
    public EdgeEffect createEdgeEffect(RecyclerView r3, int r4) {
        TrackingEdgeEffect r0 = new TrackingEdgeEffect(r3, r4, new EdgeEffectTrackerFactory$$ExternalSyntheticLambda0(this));
        this.edgeEffects[r4] = r0;
        return r0;
    }

    public boolean hasVisibleEdges() {
        TrackingEdgeEffect[] r4 = this.edgeEffects;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        TrackingEdgeEffect r3 = r4[r2];
        if (r3 == null) goto L10;
        if (r3.isVisible() == false) goto L10;
        return true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return false;
    }
}
