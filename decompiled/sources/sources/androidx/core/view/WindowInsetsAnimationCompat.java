package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.R$id;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {
    private Impl mImpl;

    /* loaded from: classes3.dex */
    public static final class BoundsCompat {
        private final Insets mLowerBound;
        private final Insets mUpperBound;

        private BoundsCompat(WindowInsetsAnimation.Bounds r2) {
            this.mLowerBound = Impl30.getLowerBounds(r2);
            this.mUpperBound = Impl30.getHigherBounds(r2);
        }

        public BoundsCompat(Insets r1, Insets r2) {
            this.mLowerBound = r1;
            this.mUpperBound = r2;
        }

        public static BoundsCompat toBoundsCompat(WindowInsetsAnimation.Bounds r1) {
            return new BoundsCompat(r1);
        }

        public Insets getLowerBound() {
            return this.mLowerBound;
        }

        public Insets getUpperBound() {
            return this.mUpperBound;
        }

        public WindowInsetsAnimation.Bounds toBounds() {
            return Impl30.createPlatformBounds(this);
        }

        public String toString() {
            return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
        }
    }

    public static abstract class Callback {
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP = 0;
        WindowInsetsCompat mDispachedInsets;
        private final int mDispatchMode;

        public Callback(int r1) {
            this.mDispatchMode = r1;
        }

        public final int getDispatchMode() {
            return this.mDispatchMode;
        }

        public void onEnd(WindowInsetsAnimationCompat r1) {
        }

        public void onPrepare(WindowInsetsAnimationCompat r1) {
        }

        public abstract WindowInsetsCompat onProgress(WindowInsetsCompat r1, List<WindowInsetsAnimationCompat> r2);

        public BoundsCompat onStart(WindowInsetsAnimationCompat r1, BoundsCompat r2) {
            return r2;
        }
    }

    public static class Impl {
        private float mAlpha;
        private final long mDurationMillis;
        private float mFraction;
        private final Interpolator mInterpolator;
        private final int mTypeMask;

        public Impl(int r2, Interpolator r3, long r4) {
            this.mAlpha = 1.0f;
            this.mTypeMask = r2;
            this.mInterpolator = r3;
            this.mDurationMillis = r4;
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public long getDurationMillis() {
            return this.mDurationMillis;
        }

        public float getInterpolatedFraction() {
            Interpolator r0 = this.mInterpolator;
            float r1 = this.mFraction;
            if (r0 != null) goto L5;
            return r1;
        L5:
            return r0.getInterpolation(r1);
        }

        public int getTypeMask() {
            return this.mTypeMask;
        }

        public void setFraction(float r1) {
            this.mFraction = r1;
        }
    }

    /* loaded from: classes3.dex */
    public static class Impl21 extends Impl {
        private static final Interpolator HIDE_IME_INTERPOLATOR = null;
        private static final Interpolator HIDE_SYSTEM_BAR_INTERPOLATOR = null;
        private static final Interpolator SHOW_IME_INTERPOLATOR = null;
        private static final Interpolator SHOW_SYSTEM_BAR_INTERPOLATOR = null;

        public static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
            final Callback mCallback;
            private WindowInsetsCompat mLastInsets;

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$1, reason: invalid class name */
            public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
                final /* synthetic */ Impl21OnApplyWindowInsetsListener this$0;
                final /* synthetic */ WindowInsetsAnimationCompat val$anim;
                final /* synthetic */ int val$animationMask;
                final /* synthetic */ WindowInsetsCompat val$startingInsets;
                final /* synthetic */ WindowInsetsCompat val$targetInsets;
                final /* synthetic */ View val$v;

                public AnonymousClass1(Impl21OnApplyWindowInsetsListener r1, WindowInsetsAnimationCompat r2, WindowInsetsCompat r3, WindowInsetsCompat r4, int r5, View r6) {
                    this.this$0 = r1;
                    this.val$anim = r2;
                    this.val$targetInsets = r3;
                    this.val$startingInsets = r4;
                    this.val$animationMask = r5;
                    this.val$v = r6;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator r4) {
                    this.val$anim.setFraction(r4.getAnimatedFraction());
                    WindowInsetsCompat r42 = Impl21.interpolateInsets(this.val$targetInsets, this.val$startingInsets, this.val$anim.getInterpolatedFraction(), this.val$animationMask);
                    List r0 = Collections.singletonList(this.val$anim);
                    Impl21.dispatchOnProgress(this.val$v, r42, r0);
                }
            }

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$2, reason: invalid class name */
            public class AnonymousClass2 extends AnimatorListenerAdapter {
                final /* synthetic */ Impl21OnApplyWindowInsetsListener this$0;
                final /* synthetic */ WindowInsetsAnimationCompat val$anim;
                final /* synthetic */ View val$v;

                public AnonymousClass2(Impl21OnApplyWindowInsetsListener r1, WindowInsetsAnimationCompat r2, View r3) {
                    this.this$0 = r1;
                    this.val$anim = r2;
                    this.val$v = r3;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    this.val$anim.setFraction(1.0f);
                    Impl21.dispatchOnEnd(this.val$v, this.val$anim);
                }
            }

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$Impl21$Impl21OnApplyWindowInsetsListener$3, reason: invalid class name */
            public class AnonymousClass3 implements Runnable {
                final /* synthetic */ Impl21OnApplyWindowInsetsListener this$0;
                final /* synthetic */ WindowInsetsAnimationCompat val$anim;
                final /* synthetic */ BoundsCompat val$animationBounds;
                final /* synthetic */ ValueAnimator val$animator;
                final /* synthetic */ View val$v;

                public AnonymousClass3(Impl21OnApplyWindowInsetsListener r1, View r2, WindowInsetsAnimationCompat r3, BoundsCompat r4, ValueAnimator r5) {
                    this.this$0 = r1;
                    this.val$v = r2;
                    this.val$anim = r3;
                    this.val$animationBounds = r4;
                    this.val$animator = r5;
                }

                @Override // java.lang.Runnable
                public void run() {
                    Impl21.dispatchOnStart(this.val$v, this.val$anim, this.val$animationBounds);
                    this.val$animator.start();
                }
            }

            public Impl21OnApplyWindowInsetsListener(View r1, Callback r2) {
                this.mCallback = r2;
                WindowInsetsCompat r12 = ViewCompat.getRootWindowInsets(r1);
                if (r12 == null) goto L5;
                WindowInsetsCompat r13 = new WindowInsetsCompat.Builder(r12).build();
            L6:
                this.mLastInsets = r13;
                return;
            L5:
                r13 = null;
                goto L6
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View r13, WindowInsets r14) {
                if (r13.isLaidOut() == true) goto L6;
                this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(r14, r13);
                return Impl21.forwardToViewIfNeeded(r13, r14);
            L6:
                WindowInsetsCompat r3 = WindowInsetsCompat.toWindowInsetsCompat(r14, r13);
                if (this.mLastInsets != null) goto L10;
                this.mLastInsets = ViewCompat.getRootWindowInsets(r13);
            L10:
                if (this.mLastInsets != null) goto L13;
                this.mLastInsets = r3;
                return Impl21.forwardToViewIfNeeded(r13, r14);
            L13:
                Callback r0 = Impl21.getCallback(r13);
                if (r0 != null) goto L16;
            L19:
                int[] r1 = new int[1];
                int[] r02 = new int[1];
                Impl21.buildAnimationMask(r3, this.mLastInsets, r1, r02);
                int r12 = r1[0];
                int r03 = r02[0];
                int r5 = r12 | r03;
                if (r5 != 0) goto L23;
                this.mLastInsets = r3;
                return Impl21.forwardToViewIfNeeded(r13, r14);
            L23:
                WindowInsetsCompat r4 = this.mLastInsets;
                Interpolator r04 = Impl21.createInsetInterpolator(r12, r03);
                if ((WindowInsetsCompat.Type.ime() & r5) == 0) goto L26;
                long r6 = 160;
            L27:
                WindowInsetsAnimationCompat r9 = new WindowInsetsAnimationCompat(r5, r04, r6);
                r9.setFraction(0.0f);
                ValueAnimator r11 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(r9.getDurationMillis());
                BoundsCompat r10 = Impl21.computeAnimationBounds(r3, r4, r5);
                Impl21.dispatchOnPrepare(r13, r9, r3, false);
                r11.addUpdateListener(new AnonymousClass1(this, r9, r3, r4, r5, r13));
                r11.addListener(new AnonymousClass2(this, r9, r13));
                OneShotPreDrawListener.add(r13, new AnonymousClass3(this, r13, r9, r10, r11));
                this.mLastInsets = r3;
                return Impl21.forwardToViewIfNeeded(r13, r14);
            L26:
                r6 = 250;
                goto L27
            L16:
                if (Objects.equals(r0.mDispachedInsets, r3) == false) goto L19;
                return Impl21.forwardToViewIfNeeded(r13, r14);
            }
        }

        static {
            SHOW_IME_INTERPOLATOR = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
            HIDE_IME_INTERPOLATOR = new FastOutLinearInInterpolator();
            SHOW_SYSTEM_BAR_INTERPOLATOR = new DecelerateInterpolator(1.5f);
            HIDE_SYSTEM_BAR_INTERPOLATOR = new AccelerateInterpolator(1.5f);
        }

        public Impl21(int r1, Interpolator r2, long r3) {
            super(r1, r2, r3);
        }

        @SuppressLint({"WrongConstant"})
        public static void buildAnimationMask(WindowInsetsCompat r9, WindowInsetsCompat r10, int[] r11, int[] r12) {
            int r1 = 1;
        L4:
            if (r1 > 512) goto L31;
            Insets r2 = r9.getInsets(r1);
            Insets r3 = r10.getInsets(r1);
            int r4 = r2.left;
            int r5 = r3.left;
            if (r4 <= r5) goto L8;
        L15:
            boolean r7 = true;
        L16:
            if (r4 >= r5) goto L18;
        L25:
            boolean r22 = true;
        L26:
            if (r7 == r22) goto L30;
            if (r7 == false) goto L29;
            r11[0] = r11[0] | r1;
            goto L30
        L29:
            r12[0] = r12[0] | r1;
        L30:
            r1 = r1 << 1;
            goto L4
        L18:
            if (r2.top < r3.top) goto L25;
            if (r2.right < r3.right) goto L25;
            if (r2.bottom < r3.bottom) goto L25;
            r22 = false;
            goto L26
        L8:
            if (r2.top > r3.top) goto L15;
            if (r2.right > r3.right) goto L15;
            if (r2.bottom > r3.bottom) goto L15;
            r7 = false;
            goto L16
        }

        public static BoundsCompat computeAnimationBounds(WindowInsetsCompat r4, WindowInsetsCompat r5, int r6) {
            Insets r42 = r4.getInsets(r6);
            Insets r52 = r5.getInsets(r6);
            return new BoundsCompat(Insets.of(Math.min(r42.left, r52.left), Math.min(r42.top, r52.top), Math.min(r42.right, r52.right), Math.min(r42.bottom, r52.bottom)), Insets.of(Math.max(r42.left, r52.left), Math.max(r42.top, r52.top), Math.max(r42.right, r52.right), Math.max(r42.bottom, r52.bottom)));
        }

        public static Interpolator createInsetInterpolator(int r1, int r2) {
            if ((WindowInsetsCompat.Type.ime() & r1) == 0) goto L7;
            return SHOW_IME_INTERPOLATOR;
        L7:
            if ((WindowInsetsCompat.Type.ime() & r2) == 0) goto L11;
            return HIDE_IME_INTERPOLATOR;
        L11:
            if ((r1 & WindowInsetsCompat.Type.systemBars()) == 0) goto L15;
            return SHOW_SYSTEM_BAR_INTERPOLATOR;
        L15:
            if ((WindowInsetsCompat.Type.systemBars() & r2) != 0) goto L17;
            return null;
        L17:
            return HIDE_SYSTEM_BAR_INTERPOLATOR;
        }

        private static View.OnApplyWindowInsetsListener createProxyListener(View r1, Callback r2) {
            return new Impl21OnApplyWindowInsetsListener(r1, r2);
        }

        public static void dispatchOnEnd(View r2, WindowInsetsAnimationCompat r3) {
            Callback r0 = getCallback(r2);
            if (r0 == null) goto L8;
            r0.onEnd(r3);
            if (r0.getDispatchMode() != 0) goto L8;
            return;
        L8:
            if ((r2 instanceof ViewGroup) == false) goto L13;
            ViewGroup r22 = (ViewGroup) r2;
            int r02 = 0;
        L11:
            if (r02 >= r22.getChildCount()) goto L16;
            dispatchOnEnd(r22.getChildAt(r02), r3);
            r02 = r02 + 1;
            goto L11
        L16:
            return;
        }

        public static void dispatchOnPrepare(View r2, WindowInsetsAnimationCompat r3, WindowInsetsCompat r4, boolean r5) {
            Callback r0 = getCallback(r2);
            int r1 = 0;
            if (r0 == null) goto L11;
            r0.mDispachedInsets = r4;
            if (r5 == true) goto L11;
            r0.onPrepare(r3);
            if (r0.getDispatchMode() != 0) goto L9;
            r5 = true;
            goto L11
        L9:
            r5 = false;
        L11:
            if ((r2 instanceof ViewGroup) == false) goto L16;
            ViewGroup r22 = (ViewGroup) r2;
        L14:
            if (r1 >= r22.getChildCount()) goto L18;
            dispatchOnPrepare(r22.getChildAt(r1), r3, r4, r5);
            r1 = r1 + 1;
            goto L14
        L18:
            return;
        }

        public static void dispatchOnProgress(View r2, WindowInsetsCompat r3, List<WindowInsetsAnimationCompat> r4) {
            Callback r0 = getCallback(r2);
            if (r0 == null) goto L8;
            r3 = r0.onProgress(r3, r4);
            if (r0.getDispatchMode() != 0) goto L8;
            return;
        L8:
            if ((r2 instanceof ViewGroup) == false) goto L13;
            ViewGroup r22 = (ViewGroup) r2;
            int r02 = 0;
        L11:
            if (r02 >= r22.getChildCount()) goto L16;
            dispatchOnProgress(r22.getChildAt(r02), r3, r4);
            r02 = r02 + 1;
            goto L11
        L16:
            return;
        }

        public static void dispatchOnStart(View r2, WindowInsetsAnimationCompat r3, BoundsCompat r4) {
            Callback r0 = getCallback(r2);
            if (r0 == null) goto L8;
            r0.onStart(r3, r4);
            if (r0.getDispatchMode() != 0) goto L8;
            return;
        L8:
            if ((r2 instanceof ViewGroup) == false) goto L13;
            ViewGroup r22 = (ViewGroup) r2;
            int r02 = 0;
        L11:
            if (r02 >= r22.getChildCount()) goto L16;
            dispatchOnStart(r22.getChildAt(r02), r3, r4);
            r02 = r02 + 1;
            goto L11
        L16:
            return;
        }

        public static WindowInsets forwardToViewIfNeeded(View r1, WindowInsets r2) {
            if (r1.getTag(R$id.tag_on_apply_window_listener) == null) goto L6;
            return r2;
        L6:
            return r1.onApplyWindowInsets(r2);
        }

        public static Callback getCallback(View r1) {
            Object r12 = r1.getTag(R$id.tag_window_insets_animation_callback);
            if ((r12 instanceof Impl21OnApplyWindowInsetsListener) == true) goto L5;
            return null;
        L5:
            return ((Impl21OnApplyWindowInsetsListener) r12).mCallback;
        }

        @SuppressLint({"WrongConstant"})
        public static WindowInsetsCompat interpolateInsets(WindowInsetsCompat r12, WindowInsetsCompat r13, float r14, int r15) {
            WindowInsetsCompat.Builder r0 = new WindowInsetsCompat.Builder(r12);
            int r1 = 1;
        L4:
            if (r1 > 512) goto L11;
            if ((r15 & r1) != 0) goto L8;
            r0.setInsets(r1, r12.getInsets(r1));
        L9:
            r1 = r1 << 1;
            goto L4
        L8:
            Insets r2 = r12.getInsets(r1);
            float r4 = r2.left - r13.getInsets(r1).left;
            float r5 = 1.0f - r14;
            r0.setInsets(r1, WindowInsetsCompat.insetInsets(r2, (int) ((r4 * r5) + 0.5d), (int) (((r2.top - r3.top) * r5) + 0.5d), (int) (((r2.right - r3.right) * r5) + 0.5d), (int) (((r2.bottom - r3.bottom) * r5) + 0.5d)));
            goto L9
        L11:
            return r0.build();
        }

        public static void setCallback(View r1, Callback r2) {
            if (r2 == null) goto L4;
            View.OnApplyWindowInsetsListener r22 = createProxyListener(r1, r2);
        L5:
            r1.setTag(R$id.tag_window_insets_animation_callback, r22);
            if (r1.getTag(R$id.tag_compat_insets_dispatch) == null) goto L8;
            return;
        L8:
            if (r1.getTag(R$id.tag_on_apply_window_listener) != null) goto L12;
            r1.setOnApplyWindowInsetsListener(r22);
            return;
        L12:
            return;
        L4:
            r22 = null;
            goto L5
        }
    }

    public static class Impl30 extends Impl {
        private final WindowInsetsAnimation mWrapped;

        public static class ProxyCallback extends WindowInsetsAnimation$Callback {
            private final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> mAnimations;
            private final Callback mCompat;
            private List<WindowInsetsAnimationCompat> mRORunningAnimations;
            private ArrayList<WindowInsetsAnimationCompat> mTmpRunningAnimations;

            public ProxyCallback(Callback r2) {
                super(r2.getDispatchMode());
                this.mAnimations = new HashMap();
                this.mCompat = r2;
            }

            private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation r2) {
                WindowInsetsAnimationCompat r0 = this.mAnimations.get(r2);
                if (r0 != null) goto L6;
                WindowInsetsAnimationCompat r02 = WindowInsetsAnimationCompat.toWindowInsetsAnimationCompat(r2);
                this.mAnimations.put(r2, r02);
                return r02;
            L6:
                return r0;
            }

            public void onEnd(WindowInsetsAnimation r3) {
                this.mCompat.onEnd(getWindowInsetsAnimationCompat(r3));
                this.mAnimations.remove(r3);
            }

            public void onPrepare(WindowInsetsAnimation r2) {
                this.mCompat.onPrepare(getWindowInsetsAnimationCompat(r2));
            }

            public WindowInsets onProgress(WindowInsets r4, List<WindowInsetsAnimation> r5) {
                ArrayList<WindowInsetsAnimationCompat> r0 = this.mTmpRunningAnimations;
                if (r0 != null) goto L5;
                ArrayList<WindowInsetsAnimationCompat> r02 = new ArrayList(r5.size());
                this.mTmpRunningAnimations = r02;
                this.mRORunningAnimations = Collections.unmodifiableList(r02);
            L6:
                int r03 = r5.size() - 1;
            L7:
                if (r03 < 0) goto L10;
                WindowInsetsAnimation r1 = WindowInsetsAnimationCompat$Impl30$ProxyCallback$$ExternalSyntheticApiModelOutline0.m(r5.get(r03));
                WindowInsetsAnimationCompat r2 = getWindowInsetsAnimationCompat(r1);
                r2.setFraction(r1.getFraction());
                this.mTmpRunningAnimations.add(r2);
                r03 = r03 - 1;
                goto L7
            L10:
                return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(r4), this.mRORunningAnimations).toWindowInsets();
            L5:
                r0.clear();
                goto L6
            }

            public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation r2, WindowInsetsAnimation.Bounds r3) {
                return this.mCompat.onStart(getWindowInsetsAnimationCompat(r2), BoundsCompat.toBoundsCompat(r3)).toBounds();
            }
        }

        public Impl30(int r1, Interpolator r2, long r3) {
            this(WindowInsetsAnimationCompat$Impl30$$ExternalSyntheticApiModelOutline0.m(r1, r2, r3));
        }

        public Impl30(WindowInsetsAnimation r5) {
            super(0, null, 0);
            this.mWrapped = r5;
        }

        public static WindowInsetsAnimation.Bounds createPlatformBounds(BoundsCompat r1) {
            WindowInsetsAnimationCompat$Impl30$$ExternalSyntheticApiModelOutline2.m();
            return WindowInsetsAnimationCompat$Impl30$$ExternalSyntheticApiModelOutline1.m(r1.getLowerBound().toPlatformInsets(), r1.getUpperBound().toPlatformInsets());
        }

        public static Insets getHigherBounds(WindowInsetsAnimation.Bounds r0) {
            return Insets.toCompatInsets(r0.getUpperBound());
        }

        public static Insets getLowerBounds(WindowInsetsAnimation.Bounds r0) {
            return Insets.toCompatInsets(r0.getLowerBound());
        }

        public static void setCallback(View r1, Callback r2) {
            if (r2 == null) goto L4;
            ProxyCallback r0 = new ProxyCallback(r2);
        L5:
            r1.setWindowInsetsAnimationCallback(r0);
            return;
        L4:
            r0 = null;
            goto L5
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getAlpha() {
            return this.mWrapped.getAlpha();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public long getDurationMillis() {
            return this.mWrapped.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getInterpolatedFraction() {
            return this.mWrapped.getInterpolatedFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public int getTypeMask() {
            return this.mWrapped.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public void setFraction(float r1) {
            this.mWrapped.setFraction(r1);
        }
    }

    public WindowInsetsAnimationCompat(int r3, Interpolator r4, long r5) {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        this.mImpl = new Impl30(r3, r4, r5);
        return;
    L6:
        this.mImpl = new Impl21(r3, r4, r5);
    }

    private WindowInsetsAnimationCompat(WindowInsetsAnimation r5) {
        this(0, null, 0);
        this.mImpl = new Impl30(r5);
    }

    public static void setCallback(View r2, Callback r3) {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        Impl30.setCallback(r2, r3);
        return;
    L6:
        Impl21.setCallback(r2, r3);
    }

    public static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation r1) {
        return new WindowInsetsAnimationCompat(r1);
    }

    public float getAlpha() {
        return this.mImpl.getAlpha();
    }

    public long getDurationMillis() {
        return this.mImpl.getDurationMillis();
    }

    public float getInterpolatedFraction() {
        return this.mImpl.getInterpolatedFraction();
    }

    public int getTypeMask() {
        return this.mImpl.getTypeMask();
    }

    public void setFraction(float r1) {
        this.mImpl.setFraction(r1);
    }
}
