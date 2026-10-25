package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ViewPropertyAnimatorCompatSet {
    final ArrayList<ViewPropertyAnimatorCompat> mAnimators;
    private long mDuration;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;

    /* renamed from: androidx.appcompat.view.ViewPropertyAnimatorCompatSet$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPropertyAnimatorListenerAdapter {
        private int mProxyEndCount;
        private boolean mProxyStarted;
        final /* synthetic */ ViewPropertyAnimatorCompatSet this$0;

        public AnonymousClass1(ViewPropertyAnimatorCompatSet r1) {
            this.this$0 = r1;
            this.mProxyStarted = false;
            this.mProxyEndCount = 0;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View r2) {
            int r22 = this.mProxyEndCount + 1;
            this.mProxyEndCount = r22;
            if (r22 != this.this$0.mAnimators.size()) goto L9;
            ViewPropertyAnimatorListener r23 = this.this$0.mListener;
            if (r23 == null) goto L7;
            r23.onAnimationEnd(null);
        L7:
            onEnd();
            return;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View r1) {
            if (this.mProxyStarted == true) goto L10;
            this.mProxyStarted = true;
            ViewPropertyAnimatorListener r0 = this.this$0.mListener;
            if (r0 == null) goto L9;
            r0.onAnimationStart(null);
            return;
        L9:
            return;
        }

        public void onEnd() {
            this.mProxyEndCount = 0;
            this.mProxyStarted = false;
            this.this$0.onAnimationsEnded();
        }
    }

    public ViewPropertyAnimatorCompatSet() {
        this.mDuration = -1;
        this.mProxyListener = new AnonymousClass1(this);
        this.mAnimators = new ArrayList();
    }

    public void cancel() {
        if (this.mIsStarted == true) goto L5;
        return;
    L5:
        ArrayList<ViewPropertyAnimatorCompat> r0 = this.mAnimators;
        int r1 = r0.size();
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        ViewPropertyAnimatorCompat r4 = r0.get(r3);
        r3 = r3 + 1;
        r4.cancel();
        goto L6
    L8:
        this.mIsStarted = false;
    }

    public void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat r2) {
        if (this.mIsStarted == true) goto L5;
        this.mAnimators.add(r2);
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat r3, ViewPropertyAnimatorCompat r4) {
        this.mAnimators.add(r3);
        r4.setStartDelay(r3.getDuration());
        this.mAnimators.add(r4);
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long r2) {
        if (this.mIsStarted == true) goto L5;
        this.mDuration = r2;
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator r2) {
        if (this.mIsStarted == true) goto L5;
        this.mInterpolator = r2;
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener r2) {
        if (this.mIsStarted == true) goto L5;
        this.mListener = r2;
    L5:
        return this;
    }

    public void start() {
        if (this.mIsStarted == false) goto L5;
        return;
    L5:
        ArrayList<ViewPropertyAnimatorCompat> r0 = this.mAnimators;
        int r1 = r0.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L17;
        ViewPropertyAnimatorCompat r3 = r0.get(r2);
        r2 = r2 + 1;
        ViewPropertyAnimatorCompat r32 = r3;
        long r4 = this.mDuration;
        if (r4 < 0) goto L10;
        r32.setDuration(r4);
    L10:
        Interpolator r42 = this.mInterpolator;
        if (r42 == null) goto L14;
        r32.setInterpolator(r42);
    L14:
        if (this.mListener == null) goto L16;
        r32.setListener(this.mProxyListener);
    L16:
        r32.start();
        goto L6
    L17:
        this.mIsStarted = true;
    }
}
