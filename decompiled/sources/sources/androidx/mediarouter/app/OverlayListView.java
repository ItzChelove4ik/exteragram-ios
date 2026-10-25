package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final class OverlayListView extends ListView {
    private final List<OverlayObject> mOverlayObjects;

    public static class OverlayObject {
        private BitmapDrawable mBitmap;
        private float mCurrentAlpha;
        private Rect mCurrentBounds;
        private int mDeltaY;
        private long mDuration;
        private float mEndAlpha;
        private Interpolator mInterpolator;
        private boolean mIsAnimationEnded;
        private boolean mIsAnimationStarted;
        private OnAnimationEndListener mListener;
        private float mStartAlpha;
        private Rect mStartRect;
        private long mStartTime;

        public interface OnAnimationEndListener {
            void onAnimationEnd();
        }

        public OverlayObject(BitmapDrawable r2, Rect r3) {
            this.mCurrentAlpha = 1.0f;
            this.mStartAlpha = 1.0f;
            this.mEndAlpha = 1.0f;
            this.mBitmap = r2;
            this.mStartRect = r3;
            this.mCurrentBounds = new Rect(r3);
            BitmapDrawable r22 = this.mBitmap;
            if (r22 == null) goto L6;
            r22.setAlpha((int) (this.mCurrentAlpha * 255.0f));
            this.mBitmap.setBounds(this.mCurrentBounds);
            return;
        }

        public BitmapDrawable getBitmapDrawable() {
            return this.mBitmap;
        }

        public boolean isAnimationStarted() {
            return this.mIsAnimationStarted;
        }

        public OverlayObject setAlphaAnimation(float r1, float r2) {
            this.mStartAlpha = r1;
            this.mEndAlpha = r2;
            return this;
        }

        public OverlayObject setAnimationEndListener(OnAnimationEndListener r1) {
            this.mListener = r1;
            return this;
        }

        public OverlayObject setDuration(long r1) {
            this.mDuration = r1;
            return this;
        }

        public OverlayObject setInterpolator(Interpolator r1) {
            this.mInterpolator = r1;
            return this;
        }

        public OverlayObject setTranslateYAnimation(int r1) {
            this.mDeltaY = r1;
            return this;
        }

        public void startAnimation(long r1) {
            this.mStartTime = r1;
            this.mIsAnimationStarted = true;
        }

        public void stopAnimation() {
            this.mIsAnimationStarted = true;
            this.mIsAnimationEnded = true;
            OnAnimationEndListener r1 = this.mListener;
            if (r1 == null) goto L6;
            r1.onAnimationEnd();
            return;
        }

        public boolean update(long r6) {
            if (this.mIsAnimationEnded == false) goto L6;
            return false;
        L6:
            float r0 = 0.0f;
            float r62 = Math.max(0.0f, Math.min(1.0f, (r6 - this.mStartTime) / this.mDuration));
            if (this.mIsAnimationStarted == false) goto L10;
            r0 = r62;
        L10:
            Interpolator r63 = this.mInterpolator;
            if (r63 != null) goto L13;
            float r64 = r0;
        L14:
            int r1 = (int) (this.mDeltaY * r64);
            Rect r2 = this.mCurrentBounds;
            Rect r3 = this.mStartRect;
            r2.top = r3.top + r1;
            r2.bottom = r3.bottom + r1;
            float r12 = this.mStartAlpha;
            float r13 = r12 + ((this.mEndAlpha - r12) * r64);
            this.mCurrentAlpha = r13;
            BitmapDrawable r65 = this.mBitmap;
            if (r65 == null) goto L19;
            if (r2 == null) goto L19;
            r65.setAlpha((int) (r13 * 255.0f));
            this.mBitmap.setBounds(this.mCurrentBounds);
        L19:
            if (this.mIsAnimationStarted == false) goto L26;
            if (r0 < 1.0f) goto L26;
            this.mIsAnimationEnded = true;
            OnAnimationEndListener r66 = this.mListener;
            if (r66 == null) goto L26;
            r66.onAnimationEnd();
        L26:
            return !this.mIsAnimationEnded;
        L13:
            r64 = r63.getInterpolation(r0);
            goto L14
        }
    }

    public OverlayListView(Context r1) {
        super(r1);
        this.mOverlayObjects = new ArrayList();
    }

    public OverlayListView(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.mOverlayObjects = new ArrayList();
    }

    public OverlayListView(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.mOverlayObjects = new ArrayList();
    }

    public void addOverlayObject(OverlayObject r1) {
        this.mOverlayObjects.add(r1);
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        super.onDraw(r5);
        if (this.mOverlayObjects.size() <= 0) goto L13;
        Iterator<OverlayObject> r0 = this.mOverlayObjects.iterator();
    L6:
        if (r0.hasNext() == false) goto L19;
        OverlayObject r1 = r0.next();
        BitmapDrawable r2 = r1.getBitmapDrawable();
        if (r2 == null) goto L11;
        r2.draw(r5);
    L11:
        if (r1.update(getDrawingTime()) == true) goto L6;
        r0.remove();
        goto L6
    L19:
        return;
    }

    public void startAnimationAll() {
        Iterator<OverlayObject> r0 = this.mOverlayObjects.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        OverlayObject r1 = r0.next();
        if (r1.isAnimationStarted() == true) goto L4;
        r1.startAnimation(getDrawingTime());
        goto L4
    }

    public void stopAnimationAll() {
        Iterator<OverlayObject> r1 = this.mOverlayObjects.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().stopAnimation();
        goto L4
    }
}
