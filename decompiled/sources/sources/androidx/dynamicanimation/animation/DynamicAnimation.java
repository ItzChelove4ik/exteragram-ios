package androidx.dynamicanimation.animation;

import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.DynamicAnimation;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements AnimationHandler.AnimationFrameCallback {
    public static final ViewProperty ALPHA = null;
    public static final ViewProperty ROTATION = null;
    public static final ViewProperty ROTATION_X = null;
    public static final ViewProperty ROTATION_Y = null;
    public static final ViewProperty SCALE_X = null;
    public static final ViewProperty SCALE_Y = null;
    public static final ViewProperty SCROLL_X = null;
    public static final ViewProperty SCROLL_Y = null;
    public static final ViewProperty TRANSLATION_X = null;
    public static final ViewProperty TRANSLATION_Y = null;
    public static final ViewProperty TRANSLATION_Z = null;
    public static final ViewProperty X = null;
    public static final ViewProperty Y = null;
    public static final ViewProperty Z = null;
    private AnimationHandler mAnimationHandler;
    private final ArrayList<OnAnimationEndListener> mEndListeners;
    private long mLastFrameTime;
    float mMaxValue;
    float mMinValue;
    private float mMinVisibleChange;
    final FloatPropertyCompat mProperty;
    boolean mRunning;
    boolean mStartValueIsSet;
    final Object mTarget;
    private final ArrayList<OnAnimationUpdateListener> mUpdateListeners;
    float mValue;
    float mVelocity;

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewProperty {
        public AnonymousClass1(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getTranslationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setTranslationX(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$10, reason: invalid class name */
    public class AnonymousClass10 extends ViewProperty {
        public AnonymousClass10(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setY(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$11, reason: invalid class name */
    public class AnonymousClass11 extends ViewProperty {
        public AnonymousClass11(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return ViewCompat.getZ(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            ViewCompat.setZ(r1, r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$12, reason: invalid class name */
    public class AnonymousClass12 extends ViewProperty {
        public AnonymousClass12(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getAlpha();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setAlpha(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$13, reason: invalid class name */
    public class AnonymousClass13 extends ViewProperty {
        public AnonymousClass13(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getScrollX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setScrollX((int) r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$14, reason: invalid class name */
    public class AnonymousClass14 extends ViewProperty {
        public AnonymousClass14(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getScrollY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setScrollY((int) r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$15, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass15 extends FloatPropertyCompat {
        final /* synthetic */ DynamicAnimation this$0;
        final /* synthetic */ FloatValueHolder val$floatValueHolder;

        public AnonymousClass15(DynamicAnimation r1, String r2, FloatValueHolder r3) {
            this.this$0 = r1;
            this.val$floatValueHolder = r3;
            super(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(Object r1) {
            return this.val$floatValueHolder.getValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(Object r1, float r2) {
            this.val$floatValueHolder.setValue(r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewProperty {
        public AnonymousClass2(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getTranslationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setTranslationY(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewProperty {
        public AnonymousClass3(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return ViewCompat.getTranslationZ(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            ViewCompat.setTranslationZ(r1, r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewProperty {
        public AnonymousClass4(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getScaleX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setScaleX(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewProperty {
        public AnonymousClass5(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getScaleY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setScaleY(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$6, reason: invalid class name */
    public class AnonymousClass6 extends ViewProperty {
        public AnonymousClass6(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getRotation();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setRotation(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$7, reason: invalid class name */
    public class AnonymousClass7 extends ViewProperty {
        public AnonymousClass7(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getRotationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setRotationX(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$8, reason: invalid class name */
    public class AnonymousClass8 extends ViewProperty {
        public AnonymousClass8(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getRotationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setRotationY(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$9, reason: invalid class name */
    public class AnonymousClass9 extends ViewProperty {
        public AnonymousClass9(String r2) {
            super(r2, null);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(View r1) {
            return r1.getX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(View r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(View r1, float r2) {
            r1.setX(r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(View r1, float r2) {
            setValue2(r1, r2);
        }
    }

    public static class MassState {
        float mValue;
        float mVelocity;

        public MassState() {
        }
    }

    /* loaded from: classes3.dex */
    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation r1, boolean r2, float r3, float r4);
    }

    /* loaded from: classes3.dex */
    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation r1, float r2, float r3);
    }

    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        private ViewProperty(String r1) {
            super(r1);
        }

        public /* synthetic */ ViewProperty(String r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    static {
        TRANSLATION_X = new AnonymousClass1("translationX");
        TRANSLATION_Y = new AnonymousClass2("translationY");
        TRANSLATION_Z = new AnonymousClass3("translationZ");
        SCALE_X = new AnonymousClass4("scaleX");
        SCALE_Y = new AnonymousClass5("scaleY");
        ROTATION = new AnonymousClass6("rotation");
        ROTATION_X = new AnonymousClass7("rotationX");
        ROTATION_Y = new AnonymousClass8("rotationY");
        X = new AnonymousClass9("x");
        Y = new AnonymousClass10("y");
        Z = new AnonymousClass11("z");
        ALPHA = new AnonymousClass12("alpha");
        SCROLL_X = new AnonymousClass13("scrollX");
        SCROLL_Y = new AnonymousClass14("scrollY");
    }

    public DynamicAnimation(FloatValueHolder r3) {
        this.mVelocity = 0.0f;
        this.mValue = Float.MAX_VALUE;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = Float.MAX_VALUE;
        this.mMinValue = -Float.MAX_VALUE;
        this.mLastFrameTime = 0;
        this.mEndListeners = new ArrayList();
        this.mUpdateListeners = new ArrayList();
        this.mTarget = null;
        this.mProperty = new AnonymousClass15(this, "FloatValueHolder", r3);
        this.mMinVisibleChange = 1.0f;
    }

    public <K> DynamicAnimation(K r3, FloatPropertyCompat<K> r4) {
        this.mVelocity = 0.0f;
        this.mValue = Float.MAX_VALUE;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = Float.MAX_VALUE;
        this.mMinValue = -Float.MAX_VALUE;
        this.mLastFrameTime = 0;
        this.mEndListeners = new ArrayList();
        this.mUpdateListeners = new ArrayList();
        this.mTarget = r3;
        this.mProperty = r4;
        if (r4 != ROTATION) goto L5;
    L22:
        this.mMinVisibleChange = 0.1f;
        return;
    L5:
        if (r4 == ROTATION_X) goto L22;
        if (r4 == ROTATION_Y) goto L22;
        if (r4 != ALPHA) goto L14;
        this.mMinVisibleChange = 0.00390625f;
        return;
    L14:
        if (r4 != SCALE_X) goto L16;
    L20:
        this.mMinVisibleChange = 0.002f;
        return;
    L16:
        if (r4 == SCALE_Y) goto L20;
        this.mMinVisibleChange = 1.0f;
    }

    private void endAnimationInternal(boolean r5) {
        int r0 = 0;
        this.mRunning = false;
        getAnimationHandler().removeCallback(this);
        this.mLastFrameTime = 0;
        this.mStartValueIsSet = false;
    L3:
        int r1 = this.mEndListeners.size();
        ArrayList<OnAnimationEndListener> r2 = this.mEndListeners;
        if (r0 >= r1) goto L9;
        if (r2.get(r0) == null) goto L8;
        this.mEndListeners.get(r0).onAnimationEnd(this, r5, this.mValue, this.mVelocity);
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        removeNullEntries(r2);
    }

    private float getPropertyValue() {
        return this.mProperty.getValue(this.mTarget);
    }

    private static <T> void removeEntry(ArrayList<T> r1, T r2) {
        int r22 = r1.indexOf(r2);
        if (r22 < 0) goto L6;
        r1.set(r22, null);
        return;
    }

    private static <T> void removeNullEntries(ArrayList<T> r2) {
        int r0 = r2.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (r2.get(r0) != null) goto L7;
        r2.remove(r0);
    L7:
        r0 = r0 - 1;
        goto L3
    }

    private void startAnimationInternal() {
        if (this.mRunning == true) goto L15;
        this.mRunning = true;
        if (this.mStartValueIsSet == true) goto L7;
        this.mValue = getPropertyValue();
    L7:
        float r0 = this.mValue;
        if (r0 <= this.mMaxValue) goto L10;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Starting value need to be in between min value and max value");
        return;
    L10:
        if (r0 < this.mMinValue) goto L13;
        getAnimationHandler().addAnimationFrameCallback(this, 0);
        return;
    }

    public T addEndListener(OnAnimationEndListener r2) {
        if (this.mEndListeners.contains(r2) == true) goto L5;
        this.mEndListeners.add(r2);
    L5:
        return this;
    }

    public T addUpdateListener(OnAnimationUpdateListener r2) {
        if (isRunning() == false) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Error: Update listeners must be added beforethe animation.");
        return null;
    L5:
        if (this.mUpdateListeners.contains(r2) == true) goto L7;
        this.mUpdateListeners.add(r2);
    L7:
        return this;
    }

    public void cancel() {
        if (getAnimationHandler().isCurrentThread() == false) goto L9;
        if (this.mRunning == false) goto L10;
        endAnimationInternal(true);
        return;
    L10:
        return;
    L9:
        throw new AndroidRuntimeException("Animations may only be canceled from the same thread as the animation handler");
    }

    @Override // androidx.dynamicanimation.animation.AnimationHandler.AnimationFrameCallback
    public boolean doAnimationFrame(long r5) {
        long r0 = this.mLastFrameTime;
        if (r0 != 0) goto L6;
        this.mLastFrameTime = r5;
        setPropertyValue(this.mValue);
        return false;
    L6:
        long r02 = r5 - r0;
        this.mLastFrameTime = r5;
        float r52 = getAnimationHandler().getDurationScale();
        if (r52 != 0.0f) goto L9;
        long r53 = 2147483647L;
    L10:
        boolean r54 = updateValueAndVelocity(r53);
        float r6 = Math.min(this.mValue, this.mMaxValue);
        this.mValue = r6;
        float r62 = Math.max(r6, this.mMinValue);
        this.mValue = r62;
        setPropertyValue(r62);
        if (r54 == false) goto L13;
        endAnimationInternal(false);
    L13:
        return r54;
    L9:
        r53 = (long) (r02 / r52);
        goto L10
    }

    public AnimationHandler getAnimationHandler() {
        AnimationHandler r0 = this.mAnimationHandler;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return AnimationHandler.getInstance();
    }

    public float getValueThreshold() {
        return this.mMinVisibleChange * 0.75f;
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public void removeEndListener(OnAnimationEndListener r1) {
        removeEntry(this.mEndListeners, r1);
    }

    public T setMaxValue(float r1) {
        this.mMaxValue = r1;
        return this;
    }

    public T setMinValue(float r1) {
        this.mMinValue = r1;
        return this;
    }

    public T setMinimumVisibleChange(float r2) {
        if (r2 <= 0.0f) goto L6;
        this.mMinVisibleChange = r2;
        setValueThreshold(r2 * 0.75f);
        return this;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Minimum visible change must be positive.");
        return null;
    }

    public void setPropertyValue(float r4) {
        this.mProperty.setValue(this.mTarget, r4);
        int r42 = 0;
    L3:
        int r0 = this.mUpdateListeners.size();
        ArrayList<OnAnimationUpdateListener> r1 = this.mUpdateListeners;
        if (r42 >= r0) goto L9;
        if (r1.get(r42) == null) goto L8;
        this.mUpdateListeners.get(r42).onAnimationUpdate(this, this.mValue, this.mVelocity);
    L8:
        r42 = r42 + 1;
        goto L3
    L9:
        removeNullEntries(r1);
    }

    public T setStartValue(float r1) {
        this.mValue = r1;
        this.mStartValueIsSet = true;
        return this;
    }

    public T setStartVelocity(float r1) {
        this.mVelocity = r1;
        return this;
    }

    public abstract void setValueThreshold(float r1);

    public void start() {
        if (getAnimationHandler().isCurrentThread() == false) goto L9;
        if (this.mRunning == true) goto L10;
        startAnimationInternal();
        return;
    L10:
        return;
    L9:
        throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
    }

    public abstract boolean updateValueAndVelocity(long r1);
}
