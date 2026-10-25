package androidx.core.view.insets;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
class SystemBarStateMonitor {
    private final ArrayList<Callback> mCallbacks;
    private int mColorHint;
    private final View mDetector;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;

    /* renamed from: androidx.core.view.insets.SystemBarStateMonitor$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ SystemBarStateMonitor this$0;
        final /* synthetic */ ViewGroup val$rootView;

        public AnonymousClass1(SystemBarStateMonitor r1, Context r2, ViewGroup r3) {
            this.this$0 = r1;
            this.val$rootView = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r3) {
            Drawable r32 = this.val$rootView.getBackground();
            if ((r32 instanceof ColorDrawable) == false) goto L5;
            int r33 = ((ColorDrawable) r32).getColor();
        L7:
            if (SystemBarStateMonitor.access$000(this.this$0) == r33) goto L11;
            SystemBarStateMonitor.access$002(this.this$0, r33);
            int r0 = SystemBarStateMonitor.access$100(this.this$0).size() - 1;
        L9:
            if (r0 < 0) goto L13;
            ((Callback) SystemBarStateMonitor.access$100(this.this$0).get(r0)).onColorHintChanged(r33);
            r0 = r0 - 1;
            goto L9
        L13:
            return;
        L11:
            return;
        L5:
            r33 = 0;
            goto L7
        }
    }

    /* renamed from: androidx.core.view.insets.SystemBarStateMonitor$2, reason: invalid class name */
    public class AnonymousClass2 extends WindowInsetsAnimationCompat.Callback {
        private final HashMap<WindowInsetsAnimationCompat, Integer> mAnimationSidesMap;
        final /* synthetic */ SystemBarStateMonitor this$0;

        public AnonymousClass2(SystemBarStateMonitor r1, int r2) {
            this.this$0 = r1;
            super(r2);
            this.mAnimationSidesMap = new HashMap();
        }

        private boolean animatesSystemBars(WindowInsetsAnimationCompat r1) {
            if ((r1.getTypeMask() & WindowInsetsCompat.Type.systemBars()) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
        public void onEnd(WindowInsetsAnimationCompat r2) {
            if (animatesSystemBars(r2) == false) goto L8;
            this.mAnimationSidesMap.remove(r2);
            int r22 = SystemBarStateMonitor.access$100(this.this$0).size() - 1;
        L6:
            if (r22 < 0) goto L10;
            ((Callback) SystemBarStateMonitor.access$100(this.this$0).get(r22)).onAnimationEnd();
            r22 = r22 - 1;
            goto L6
        L10:
            return;
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
        public void onPrepare(WindowInsetsAnimationCompat r2) {
            if (animatesSystemBars(r2) == false) goto L8;
            int r22 = SystemBarStateMonitor.access$100(this.this$0).size() - 1;
        L6:
            if (r22 < 0) goto L10;
            ((Callback) SystemBarStateMonitor.access$100(this.this$0).get(r22)).onAnimationStart();
            r22 = r22 - 1;
            goto L6
        L10:
            return;
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
        public WindowInsetsCompat onProgress(WindowInsetsCompat r7, List<WindowInsetsAnimationCompat> r8) {
            RectF r0 = new RectF(1.0f, 1.0f, 1.0f, 1.0f);
            int r1 = r8.size() - 1;
            int r2 = 0;
        L3:
            if (r1 < 0) goto L20;
            WindowInsetsAnimationCompat r3 = r8.get(r1);
            Integer r4 = this.mAnimationSidesMap.get(r3);
            if (r4 == null) goto L19;
            int r42 = r4.intValue();
            float r32 = r3.getAlpha();
            if ((r42 & 1) == 0) goto L10;
            r0.left = r32;
        L10:
            if ((r42 & 2) == 0) goto L13;
            r0.top = r32;
        L13:
            if ((r42 & 4) == 0) goto L16;
            r0.right = r32;
        L16:
            if ((r42 & 8) == 0) goto L18;
            r0.bottom = r32;
        L18:
            r2 = r2 | r42;
        L19:
            r1 = r1 - 1;
            goto L3
        L20:
            Insets r82 = SystemBarStateMonitor.access$200(this.this$0, r7);
            int r12 = SystemBarStateMonitor.access$100(this.this$0).size() - 1;
        L21:
            if (r12 < 0) goto L23;
            ((Callback) SystemBarStateMonitor.access$100(this.this$0).get(r12)).onAnimationProgress(r2, r82, r0);
            r12 = r12 - 1;
            goto L21
        L23:
            return r7;
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
        public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat r6, WindowInsetsAnimationCompat.BoundsCompat r7) {
            if (animatesSystemBars(r6) == true) goto L5;
            return r7;
        L5:
            Insets r0 = r7.getUpperBound();
            Insets r1 = r7.getLowerBound();
            if (r0.left == r1.left) goto L8;
            int r2 = 1;
        L10:
            if (r0.top == r1.top) goto L13;
            r2 = r2 | 2;
        L13:
            if (r0.right == r1.right) goto L16;
            r2 = r2 | 4;
        L16:
            if (r0.bottom == r1.bottom) goto L18;
            r2 = r2 | 8;
        L18:
            this.mAnimationSidesMap.put(r6, Integer.valueOf(r2));
            return r7;
        L8:
            r2 = 0;
            goto L10
        }
    }

    /* renamed from: androidx.core.view.insets.SystemBarStateMonitor$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnAttachStateChangeListener {
        final /* synthetic */ View val$child;
        final /* synthetic */ ViewGroup val$group;
        final /* synthetic */ int val$index;

        public AnonymousClass3(ViewGroup r1, View r2, int r3) {
            this.val$group = r1;
            this.val$child = r2;
            this.val$index = r3;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r4) {
            this.val$group.addView(this.val$child, this.val$index);
            r4.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r4) {
            this.val$group.addView(this.val$child, this.val$index);
            r4.removeOnAttachStateChangeListener(this);
        }
    }

    public interface Callback {
        void onAnimationEnd();

        void onAnimationProgress(int r1, Insets r2, RectF r3);

        void onAnimationStart();

        void onColorHintChanged(int r1);

        void onInsetsChanged(Insets r1, Insets r2);
    }

    public static /* synthetic */ void $r8$lambda$AIasHc4haC19mIVmA0UM953xlq0(SystemBarStateMonitor r2) {
        ViewParent r0 = r2.mDetector.getParent();
        if ((r0 instanceof ViewGroup) == false) goto L6;
        ((ViewGroup) r0).removeView(r2.mDetector);
        return;
    }

    /* renamed from: $r8$lambda$nKGC9baBGIw6pzxW6_YrSZDe-_Q, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m672$r8$lambda$nKGC9baBGIw6pzxW6_YrSZDe_Q(SystemBarStateMonitor r3, View r4, WindowInsetsCompat r5) {
        Insets r42 = r3.getInsets(r5);
        Insets r0 = r3.getInsetsIgnoringVisibility(r5);
        if (r42.equals(r3.mInsets) == true) goto L5;
    L6:
        r3.mInsets = r42;
        r3.mInsetsIgnoringVisibility = r0;
        int r1 = r3.mCallbacks.size() - 1;
    L7:
        if (r1 < 0) goto L9;
        r3.mCallbacks.get(r1).onInsetsChanged(r42, r0);
        r1 = r1 - 1;
    L9:
        return r5;
    L5:
        if (r0.equals(r3.mInsetsIgnoringVisibility) == true) goto L9;
        goto L6
    }

    public SystemBarStateMonitor(ViewGroup r4) {
        this.mCallbacks = new ArrayList();
        Insets r0 = Insets.NONE;
        this.mInsets = r0;
        this.mInsetsIgnoringVisibility = r0;
        Drawable r02 = r4.getBackground();
        if ((r02 instanceof ColorDrawable) == false) goto L5;
        int r03 = ((ColorDrawable) r02).getColor();
    L6:
        this.mColorHint = r03;
        AnonymousClass1 r04 = new AnonymousClass1(this, r4.getContext(), r4);
        this.mDetector = r04;
        r04.setVisibility(8);
        r04.setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener(r04, new SystemBarStateMonitor$$ExternalSyntheticLambda0(this));
        ViewCompat.setWindowInsetsAnimationCallback(r04, new AnonymousClass2(this, 0));
        addViewWhenReady(r4, r04, 0);
        return;
    L5:
        r03 = 0;
        goto L6
    }

    public static /* synthetic */ int access$000(SystemBarStateMonitor r0) {
        return r0.mColorHint;
    }

    public static /* synthetic */ int access$002(SystemBarStateMonitor r0, int r1) {
        r0.mColorHint = r1;
        return r1;
    }

    public static /* synthetic */ ArrayList access$100(SystemBarStateMonitor r0) {
        return r0.mCallbacks;
    }

    public static /* synthetic */ Insets access$200(SystemBarStateMonitor r0, WindowInsetsCompat r1) {
        return r0.getInsets(r1);
    }

    private static void addViewWhenReady(ViewGroup r4, View r5, int r6) {
        int r0 = r4.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L8;
        View r1 = r4.getChildAt(r0);
        if (r1.isAttachedToWindow() != r4.isAttachedToWindow()) goto L9;
        r0 = r0 - 1;
    L9:
        if (r1 != null) goto L12;
        r4.addView(r5, r6);
        return;
    L12:
        r1.addOnAttachStateChangeListener(new AnonymousClass3(r4, r5, r6));
        return;
    L8:
        r1 = null;
        goto L9
    }

    private Insets getInsets(WindowInsetsCompat r2) {
        return Insets.min(r2.getInsets(WindowInsetsCompat.Type.systemBars()), r2.getInsets(WindowInsetsCompat.Type.tappableElement()));
    }

    private Insets getInsetsIgnoringVisibility(WindowInsetsCompat r2) {
        return Insets.min(r2.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()), r2.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.tappableElement()));
    }

    public void addCallback(Callback r3) {
        if (this.mCallbacks.contains(r3) == false) goto L5;
        return;
    L5:
        this.mCallbacks.add(r3);
        r3.onInsetsChanged(this.mInsets, this.mInsetsIgnoringVisibility);
        r3.onColorHintChanged(this.mColorHint);
    }

    public void detachFromWindow() {
        this.mDetector.post(new SystemBarStateMonitor$$ExternalSyntheticLambda1(this));
    }

    public boolean hasCallback() {
        return !this.mCallbacks.isEmpty();
    }

    public void removeCallback(Callback r1) {
        this.mCallbacks.remove(r1);
    }
}
