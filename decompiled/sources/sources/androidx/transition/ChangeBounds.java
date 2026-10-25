package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import java.util.Map;

/* loaded from: classes4.dex */
public class ChangeBounds extends Transition {
    private static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY = null;
    private static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY = null;
    private static final Property<View, PointF> POSITION_PROPERTY = null;
    private static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY = null;
    private static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY = null;
    private static final RectEvaluator sRectEvaluator = null;
    private static final String[] sTransitionProperties = null;
    private boolean mResizeClip;

    /* renamed from: androidx.transition.ChangeBounds$1, reason: invalid class name */
    public class AnonymousClass1 extends Property<ViewBounds, PointF> {
        public AnonymousClass1(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public PointF get2(ViewBounds r1) {
            return null;
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(ViewBounds r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(ViewBounds r1, PointF r2) {
            r1.setTopLeft(r2);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(ViewBounds r1, PointF r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: androidx.transition.ChangeBounds$2, reason: invalid class name */
    public class AnonymousClass2 extends Property<ViewBounds, PointF> {
        public AnonymousClass2(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public PointF get2(ViewBounds r1) {
            return null;
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(ViewBounds r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(ViewBounds r1, PointF r2) {
            r1.setBottomRight(r2);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(ViewBounds r1, PointF r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: androidx.transition.ChangeBounds$3, reason: invalid class name */
    public class AnonymousClass3 extends Property<View, PointF> {
        public AnonymousClass3(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public PointF get2(View r1) {
            return null;
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r3, PointF r4) {
            ViewUtils.setLeftTopRightBottom(r3, r3.getLeft(), r3.getTop(), Math.round(r4.x), Math.round(r4.y));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, PointF r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: androidx.transition.ChangeBounds$4, reason: invalid class name */
    public class AnonymousClass4 extends Property<View, PointF> {
        public AnonymousClass4(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public PointF get2(View r1) {
            return null;
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r3, PointF r4) {
            ViewUtils.setLeftTopRightBottom(r3, Math.round(r4.x), Math.round(r4.y), r3.getRight(), r3.getBottom());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, PointF r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: androidx.transition.ChangeBounds$5, reason: invalid class name */
    public class AnonymousClass5 extends Property<View, PointF> {
        public AnonymousClass5(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public PointF get2(View r1) {
            return null;
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r3, PointF r4) {
            int r2 = Math.round(r4.x);
            int r42 = Math.round(r4.y);
            ViewUtils.setLeftTopRightBottom(r3, r2, r42, r3.getWidth() + r2, r3.getHeight() + r42);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, PointF r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: androidx.transition.ChangeBounds$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        private final ViewBounds mViewBounds;
        final /* synthetic */ ChangeBounds this$0;
        final /* synthetic */ ViewBounds val$viewBounds;

        public AnonymousClass6(ChangeBounds r1, ViewBounds r2) {
            this.this$0 = r1;
            this.val$viewBounds = r2;
            this.mViewBounds = r2;
        }
    }

    public static class ClipListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final int mEndBottom;
        private final Rect mEndClip;
        private final boolean mEndClipIsNull;
        private final int mEndLeft;
        private final int mEndRight;
        private final int mEndTop;
        private boolean mIsCanceled;
        private final int mStartBottom;
        private final Rect mStartClip;
        private final boolean mStartClipIsNull;
        private final int mStartLeft;
        private final int mStartRight;
        private final int mStartTop;
        private final View mView;

        public ClipListener(View r1, Rect r2, boolean r3, Rect r4, boolean r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13) {
            this.mView = r1;
            this.mStartClip = r2;
            this.mStartClipIsNull = r3;
            this.mEndClip = r4;
            this.mEndClipIsNull = r5;
            this.mStartLeft = r6;
            this.mStartTop = r7;
            this.mStartRight = r8;
            this.mStartBottom = r9;
            this.mEndLeft = r10;
            this.mEndTop = r11;
            this.mEndRight = r12;
            this.mEndBottom = r13;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            onAnimationEnd(r2, false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3, boolean r4) {
            if (this.mIsCanceled == false) goto L5;
            return;
        L5:
            Rect r32 = null;
            if (r4 == false) goto L12;
            if (this.mStartClipIsNull == true) goto L15;
            r32 = this.mStartClip;
        L15:
            this.mView.setClipBounds(r32);
            View r33 = this.mView;
            if (r4 == false) goto L19;
            ViewUtils.setLeftTopRightBottom(r33, this.mStartLeft, this.mStartTop, this.mStartRight, this.mStartBottom);
            return;
        L19:
            ViewUtils.setLeftTopRightBottom(r33, this.mEndLeft, this.mEndTop, this.mEndRight, this.mEndBottom);
            return;
        L12:
            if (this.mEndClipIsNull == true) goto L15;
            r32 = this.mEndClip;
            goto L15
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            onAnimationStart(r2, false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r5, boolean r6) {
            int r52 = Math.max(this.mStartRight - this.mStartLeft, this.mEndRight - this.mEndLeft);
            int r0 = Math.max(this.mStartBottom - this.mStartTop, this.mEndBottom - this.mEndTop);
            if (r6 == false) goto L5;
            int r1 = this.mEndLeft;
        L6:
            if (r6 == false) goto L8;
            int r2 = this.mEndTop;
        L9:
            ViewUtils.setLeftTopRightBottom(this.mView, r1, r2, r52 + r1, r0 + r2);
            if (r6 == false) goto L12;
            Rect r53 = this.mEndClip;
        L13:
            this.mView.setClipBounds(r53);
            return;
        L12:
            r53 = this.mStartClip;
            goto L13
        L8:
            r2 = this.mStartTop;
            goto L9
        L5:
            r1 = this.mStartLeft;
            goto L6
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
            this.mIsCanceled = true;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r3) {
            Rect r32 = this.mView.getClipBounds();
            this.mView.setTag(R$id.transition_clip, r32);
            if (this.mEndClipIsNull == false) goto L5;
            Rect r33 = null;
        L6:
            this.mView.setClipBounds(r33);
            return;
        L5:
            r33 = this.mEndClip;
            goto L6
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r4) {
            Rect r42 = (Rect) this.mView.getTag(R$id.transition_clip);
            this.mView.setTag(R$id.transition_clip, null);
            this.mView.setClipBounds(r42);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
        }
    }

    public static class SuppressLayoutListener extends TransitionListenerAdapter {
        boolean mCanceled;
        final ViewGroup mParent;

        public SuppressLayoutListener(ViewGroup r2) {
            this.mCanceled = false;
            this.mParent = r2;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r2) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
            this.mCanceled = true;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r3) {
            if (this.mCanceled == true) goto L5;
            ViewGroupUtils.suppressLayout(this.mParent, false);
        L5:
            r3.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
            ViewGroupUtils.suppressLayout(this.mParent, true);
        }
    }

    public static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private final View mView;

        public ViewBounds(View r1) {
            this.mView = r1;
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }

        public void setBottomRight(PointF r2) {
            this.mRight = Math.round(r2.x);
            this.mBottom = Math.round(r2.y);
            int r22 = this.mBottomRightCalls + 1;
            this.mBottomRightCalls = r22;
            if (this.mTopLeftCalls != r22) goto L6;
            setLeftTopRightBottom();
            return;
        }

        public void setTopLeft(PointF r2) {
            this.mLeft = Math.round(r2.x);
            this.mTop = Math.round(r2.y);
            int r22 = this.mTopLeftCalls + 1;
            this.mTopLeftCalls = r22;
            if (r22 != this.mBottomRightCalls) goto L6;
            setLeftTopRightBottom();
            return;
        }
    }

    static {
        sTransitionProperties = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
        TOP_LEFT_PROPERTY = new AnonymousClass1(PointF.class, "topLeft");
        BOTTOM_RIGHT_PROPERTY = new AnonymousClass2(PointF.class, "bottomRight");
        BOTTOM_RIGHT_ONLY_PROPERTY = new AnonymousClass3(PointF.class, "bottomRight");
        TOP_LEFT_ONLY_PROPERTY = new AnonymousClass4(PointF.class, "topLeft");
        POSITION_PROPERTY = new AnonymousClass5(PointF.class, "position");
        sRectEvaluator = new RectEvaluator();
    }

    public ChangeBounds() {
        this.mResizeClip = false;
    }

    private void captureValues(TransitionValues r8) {
        View r0 = r8.view;
        if (r0.isLaidOut() == false) goto L5;
    L8:
        r8.values.put("android:changeBounds:bounds", new Rect(r0.getLeft(), r0.getTop(), r0.getRight(), r0.getBottom()));
        r8.values.put("android:changeBounds:parent", r8.view.getParent());
        if (this.mResizeClip == false) goto L13;
        r8.values.put("android:changeBounds:clip", r0.getClipBounds());
        return;
    L13:
        return;
    L5:
        if (r0.getWidth() != 0) goto L8;
        if (r0.getHeight() != 0) goto L8;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues r1) {
        captureValues(r1);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues r2) {
        captureValues(r2);
        if (this.mResizeClip == false) goto L8;
        Rect r1 = (Rect) r2.view.getTag(R$id.transition_clip);
        if (r1 == null) goto L9;
        r2.values.put("android:changeBounds:clip", r1);
        return;
    L9:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup r22, TransitionValues r23, TransitionValues r24) {
        if (r23 == null) goto L5;
        if (r24 == null) goto L5;
        Map<String, Object> r4 = r23.values;
        Map<String, Object> r5 = r24.values;
        ViewGroup r42 = (ViewGroup) r4.get("android:changeBounds:parent");
        ViewGroup r52 = (ViewGroup) r5.get("android:changeBounds:parent");
        if (r42 == null) goto L9;
        if (r52 == null) goto L9;
        View r7 = r24.view;
        Rect r43 = (Rect) r23.values.get("android:changeBounds:bounds");
        Rect r53 = (Rect) r24.values.get("android:changeBounds:bounds");
        int r12 = r43.left;
        int r6 = r53.left;
        int r13 = r43.top;
        int r8 = r53.top;
        int r14 = r43.right;
        int r9 = r53.right;
        int r15 = r43.bottom;
        int r44 = r53.bottom;
        int r54 = r14 - r12;
        int r10 = r15 - r13;
        int r11 = r9 - r6;
        int r3 = r44 - r8;
        Rect r1 = (Rect) r23.values.get("android:changeBounds:clip");
        Rect r2 = (Rect) r24.values.get("android:changeBounds:clip");
        if (r54 == 0) goto L13;
        if (r10 == 0) goto L13;
    L15:
        if (r12 != r6) goto L19;
        if (r13 != r8) goto L19;
        int r17 = 0;
    L20:
        if (r14 != r9) goto L22;
        if (r15 != r44) goto L22;
    L24:
        if (r1 != null) goto L26;
    L27:
        if (r1 != null) goto L30;
        if (r2 == null) goto L30;
    L29:
        r17 = r17 + 1;
    L30:
        int r32 = r17;
        if (r32 > 0) goto L33;
        return null;
    L33:
        if (this.mResizeClip == true) goto L46;
        ViewUtils.setLeftTopRightBottom(r7, r12, r13, r14, r15);
        if (r32 != 2) goto L41;
        if (r54 == r11) goto L38;
    L40:
        ViewBounds r25 = new ViewBounds(r7);
        ObjectAnimator r33 = ObjectAnimatorUtils.ofPointF(r25, TOP_LEFT_PROPERTY, getPathMotion().getPath(r12, r13, r6, r8));
        ObjectAnimator r45 = ObjectAnimatorUtils.ofPointF(r25, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath(r14, r15, r9, r44));
        AnimatorSet r55 = new AnimatorSet();
        r55.playTogether(new Animator[]{r33, r45});
        r55.addListener(new AnonymousClass6(this, r25));
        Animator r16 = r55;
    L70:
        if ((r7.getParent() instanceof ViewGroup) == false) goto L72;
        ViewGroup r26 = (ViewGroup) r7.getParent();
        ViewGroupUtils.suppressLayout(r26, true);
        getRootTransition().addListener(new SuppressLayoutListener(r26));
    L72:
        return r16;
    L38:
        if (r10 != r3) goto L40;
        r16 = ObjectAnimatorUtils.ofPointF(r7, POSITION_PROPERTY, getPathMotion().getPath(r12, r13, r6, r8));
        goto L70
    L41:
        if (r12 != r6) goto L45;
        if (r13 != r8) goto L45;
        r16 = ObjectAnimatorUtils.ofPointF(r7, BOTTOM_RIGHT_ONLY_PROPERTY, getPathMotion().getPath(r14, r15, r9, r44));
    L45:
        r16 = ObjectAnimatorUtils.ofPointF(r7, TOP_LEFT_ONLY_PROPERTY, getPathMotion().getPath(r12, r13, r6, r8));
        goto L70
    L46:
        ViewUtils.setLeftTopRightBottom(r7, r12, r13, Math.max(r54, r11) + r12, r13 + Math.max(r10, r3));
        if (r12 != r6) goto L51;
        if (r13 != r8) goto L51;
        ObjectAnimator r18 = null;
        int r19 = r44;
        int r20 = r6;
        int r162 = r9;
    L52:
        if (r1 != null) goto L54;
        boolean r92 = true;
    L55:
        if (r92 == false) goto L57;
        int r46 = 0;
        Rect r27 = new Rect(0, 0, r54, r10);
    L58:
        if (r2 != null) goto L60;
        int r56 = 1;
    L61:
        if (r56 == 0) goto L63;
        Rect r102 = new Rect(r46, r46, r11, r3);
    L65:
        if (r27.equals(r102) == true) goto L67;
        r7.setClipBounds(r27);
        ObjectAnimator r34 = ObjectAnimator.ofObject(r7, "clipBounds", sRectEvaluator, new Object[]{r27, r102});
        ClipListener r62 = new ClipListener(r7, r27, r92, r102, r56, r12, r13, r14, r15, r20, r8, r162, r19);
        r34.addListener(r62);
        addListener(r62);
    L68:
        r16 = TransitionUtils.mergeAnimators(r18, r34);
        goto L70
    L67:
        r34 = null;
        goto L68
    L63:
        r102 = r2;
        goto L65
    L60:
        r56 = r46;
        goto L61
    L57:
        r46 = 0;
        r27 = r1;
        goto L58
    L54:
        r92 = false;
    L51:
        r19 = r44;
        r162 = r9;
        r20 = r6;
        r18 = ObjectAnimatorUtils.ofPointF(r7, POSITION_PROPERTY, getPathMotion().getPath(r12, r13, r6, r8));
        goto L52
    L26:
        if (r1.equals(r2) == false) goto L29;
    L22:
        r17 = r17 + 1;
    L19:
        r17 = 1;
    L13:
        if (r11 == 0) goto L23;
        if (r3 != 0) goto L15;
    L23:
        r17 = 0;
    L9:
        return null;
    L5:
        return null;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }
}
