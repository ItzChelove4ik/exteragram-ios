package androidx.dynamicanimation.animation;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class AnimationHandler {
    private static final ThreadLocal<AnimationHandler> sAnimatorHandler = null;
    final ArrayList<AnimationFrameCallback> mAnimationCallbacks;
    private final AnimationCallbackDispatcher mCallbackDispatcher;
    long mCurrentFrameTime;
    private final SimpleArrayMap<AnimationFrameCallback, Long> mDelayedCallbackStartTime;
    public float mDurationScale;
    public DurationScaleChangeListener mDurationScaleChangeListener;
    private boolean mListDirty;
    private final Runnable mRunnable;
    private FrameCallbackScheduler mScheduler;

    /* renamed from: androidx.dynamicanimation.animation.AnimationHandler$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public class AnimationCallbackDispatcher {
        final /* synthetic */ AnimationHandler this$0;

        private AnimationCallbackDispatcher(AnimationHandler r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AnimationCallbackDispatcher(AnimationHandler r1, AnonymousClass1 r2) {
            this(r1);
        }

        public void dispatchAnimationFrame() {
            AnimationHandler r0 = this.this$0;
            r0.mCurrentFrameTime = SystemClock.uptimeMillis();
            AnimationHandler r02 = this.this$0;
            r02.doAnimationFrame(r02.mCurrentFrameTime);
            if (this.this$0.mAnimationCallbacks.size() <= 0) goto L6;
            AnimationHandler.access$100(this.this$0).postFrameCallback(AnimationHandler.access$000(this.this$0));
            return;
        }
    }

    /* loaded from: classes.dex */
    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long r1);
    }

    public interface DurationScaleChangeListener {
        boolean register();

        boolean unregister();
    }

    public class DurationScaleChangeListener33 implements DurationScaleChangeListener {
        ValueAnimator.DurationScaleChangeListener mListener;
        final /* synthetic */ AnimationHandler this$0;

        public static /* synthetic */ void $r8$lambda$6PkR6ot4tcVZAoolxManZ5F8V1s(DurationScaleChangeListener33 r0, float r1) {
            r0.this$0.mDurationScale = r1;
        }

        public DurationScaleChangeListener33(AnimationHandler r1) {
            this.this$0 = r1;
        }

        @Override // androidx.dynamicanimation.animation.AnimationHandler.DurationScaleChangeListener
        public boolean register() {
            if (this.mListener != null) goto L6;
            AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0 r0 = new AnimationHandler$DurationScaleChangeListener33$$ExternalSyntheticLambda0(this);
            this.mListener = r0;
            return ValueAnimator.registerDurationScaleChangeListener(r0);
        L6:
            return true;
        }

        @Override // androidx.dynamicanimation.animation.AnimationHandler.DurationScaleChangeListener
        public boolean unregister() {
            boolean r0 = ValueAnimator.unregisterDurationScaleChangeListener(this.mListener);
            this.mListener = null;
            return r0;
        }
    }

    public static final class FrameCallbackScheduler16 implements FrameCallbackScheduler {
        private final Choreographer mChoreographer;
        private final Looper mLooper;

        public static /* synthetic */ void $r8$lambda$svrv50zXHmA8JWvDy7GTzVnyoog(Runnable r0, long r1) {
            r0.run();
        }

        public FrameCallbackScheduler16() {
            this.mChoreographer = Choreographer.getInstance();
            this.mLooper = Looper.myLooper();
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public boolean isCurrentThread() {
            if (Thread.currentThread() != this.mLooper.getThread()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
        public void postFrameCallback(Runnable r2) {
            this.mChoreographer.postFrameCallback(new AnimationHandler$FrameCallbackScheduler16$$ExternalSyntheticLambda0(r2));
        }
    }

    public static /* synthetic */ void $r8$lambda$59Kr21tfx_cd_h3lhgsFbrO2hqk(AnimationHandler r0) {
        r0.mCallbackDispatcher.dispatchAnimationFrame();
    }

    static {
        sAnimatorHandler = new ThreadLocal();
    }

    public AnimationHandler(FrameCallbackScheduler r3) {
        this.mDelayedCallbackStartTime = new SimpleArrayMap();
        this.mAnimationCallbacks = new ArrayList();
        this.mCallbackDispatcher = new AnimationCallbackDispatcher(this, null);
        this.mRunnable = new AnimationHandler$$ExternalSyntheticLambda0(this);
        this.mCurrentFrameTime = 0;
        this.mListDirty = false;
        this.mDurationScale = 1.0f;
        this.mScheduler = r3;
    }

    public static /* synthetic */ Runnable access$000(AnimationHandler r0) {
        return r0.mRunnable;
    }

    public static /* synthetic */ FrameCallbackScheduler access$100(AnimationHandler r0) {
        return r0.mScheduler;
    }

    private void cleanUpList() {
        if (this.mListDirty == false) goto L21;
        int r0 = this.mAnimationCallbacks.size();
    L5:
        r0 = r0 - 1;
        ArrayList<AnimationFrameCallback> r1 = this.mAnimationCallbacks;
        if (r0 < 0) goto L12;
        if (r1.get(r0) != null) goto L5;
        this.mAnimationCallbacks.remove(r0);
        goto L5
    L12:
        if (r1.size() == 0) goto L14;
    L16:
        this.mListDirty = false;
        return;
    L14:
        if (Build.VERSION.SDK_INT < 33) goto L16;
        this.mDurationScaleChangeListener.unregister();
        goto L16
    }

    public static AnimationHandler getInstance() {
        ThreadLocal<AnimationHandler> r0 = sAnimatorHandler;
        if (r0.get() != null) goto L6;
        r0.set(new AnimationHandler(new FrameCallbackScheduler16()));
    L6:
        return r0.get();
    }

    private boolean isCallbackDue(AnimationFrameCallback r5, long r6) {
        Long r0 = this.mDelayedCallbackStartTime.get(r5);
        if (r0 != null) goto L6;
        return true;
    L6:
        if (r0.longValue() >= r6) goto L9;
        this.mDelayedCallbackStartTime.remove(r5);
        return true;
    L9:
        return false;
    }

    public void addAnimationFrameCallback(AnimationFrameCallback r3, long r4) {
        if (this.mAnimationCallbacks.size() != 0) goto L11;
        this.mScheduler.postFrameCallback(this.mRunnable);
        if (Build.VERSION.SDK_INT < 33) goto L11;
        this.mDurationScale = ValueAnimator.getDurationScale();
        if (this.mDurationScaleChangeListener != null) goto L9;
        this.mDurationScaleChangeListener = new DurationScaleChangeListener33(this);
    L9:
        this.mDurationScaleChangeListener.register();
    L11:
        if (this.mAnimationCallbacks.contains(r3) == true) goto L14;
        this.mAnimationCallbacks.add(r3);
    L14:
        if (r4 <= 0) goto L17;
        this.mDelayedCallbackStartTime.put(r3, Long.valueOf(SystemClock.uptimeMillis() + r4));
        return;
    }

    public void doAnimationFrame(long r6) {
        long r0 = SystemClock.uptimeMillis();
        int r2 = 0;
    L4:
        if (r2 >= this.mAnimationCallbacks.size()) goto L12;
        AnimationFrameCallback r3 = this.mAnimationCallbacks.get(r2);
        if (r3 == null) goto L11;
        if (isCallbackDue(r3, r0) == false) goto L11;
        r3.doAnimationFrame(r6);
    L11:
        r2 = r2 + 1;
        goto L4
    L12:
        cleanUpList();
    }

    public float getDurationScale() {
        return this.mDurationScale;
    }

    public boolean isCurrentThread() {
        return this.mScheduler.isCurrentThread();
    }

    public void removeCallback(AnimationFrameCallback r3) {
        this.mDelayedCallbackStartTime.remove(r3);
        int r32 = this.mAnimationCallbacks.indexOf(r3);
        if (r32 < 0) goto L6;
        this.mAnimationCallbacks.set(r32, null);
        this.mListDirty = true;
        return;
    }
}
