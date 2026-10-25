package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes4.dex */
public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable {
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    ArrayList<Animatable2Compat$AnimationCallback> mAnimationCallbacks;
    private Animator.AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final Drawable.Callback mCallback;
    private Context mContext;

    /* renamed from: androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ AnimatedVectorDrawableCompat this$0;

        public AnonymousClass1(AnimatedVectorDrawableCompat r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            this.this$0.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            this.this$0.scheduleSelf(r2, r3);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            this.this$0.unscheduleSelf(r2);
        }
    }

    /* renamed from: androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimatedVectorDrawableCompat this$0;

        public AnonymousClass2(AnimatedVectorDrawableCompat r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            ArrayList r52 = new ArrayList(this.this$0.mAnimationCallbacks);
            int r0 = r52.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            ((Animatable2Compat$AnimationCallback) r52.get(r1)).onAnimationEnd(this.this$0);
            r1 = r1 + 1;
            goto L3
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r5) {
            ArrayList r52 = new ArrayList(this.this$0.mAnimationCallbacks);
            int r0 = r52.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            ((Animatable2Compat$AnimationCallback) r52.get(r1)).onAnimationStart(this.this$0);
            r1 = r1 + 1;
            goto L3
        }
    }

    public static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
        AnimatorSet mAnimatorSet;
        ArrayList<Animator> mAnimators;
        int mChangingConfigurations;
        ArrayMap<Animator, String> mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context r3, AnimatedVectorDrawableCompatState r4, Drawable.Callback r5, Resources r6) {
            if (r4 == null) goto L19;
            this.mChangingConfigurations = r4.mChangingConfigurations;
            VectorDrawableCompat r32 = r4.mVectorDrawable;
            int r0 = 0;
            if (r32 == null) goto L11;
            Drawable.ConstantState r33 = r32.getConstantState();
            if (r6 == null) goto L9;
            this.mVectorDrawable = (VectorDrawableCompat) r33.newDrawable(r6);
        L10:
            VectorDrawableCompat r34 = (VectorDrawableCompat) this.mVectorDrawable.mutate();
            this.mVectorDrawable = r34;
            r34.setCallback(r5);
            this.mVectorDrawable.setBounds(r4.mVectorDrawable.getBounds());
            this.mVectorDrawable.setAllowCaching(false);
            goto L11
        L9:
            this.mVectorDrawable = (VectorDrawableCompat) r33.newDrawable();
        L11:
            ArrayList<Animator> r35 = r4.mAnimators;
            if (r35 == null) goto L20;
            int r36 = r35.size();
            this.mAnimators = new ArrayList(r36);
            this.mTargetNameMap = new ArrayMap(r36);
        L14:
            if (r0 >= r36) goto L16;
            Animator r52 = r4.mAnimators.get(r0);
            Animator r62 = r52.clone();
            String r53 = r4.mTargetNameMap.get(r52);
            r62.setTarget(this.mVectorDrawable.getTargetByName(r53));
            this.mAnimators.add(r62);
            this.mTargetNameMap.put(r62, r53);
            r0 = r0 + 1;
            goto L14
        L16:
            setupAnimatorSet();
            return;
        L20:
            return;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r1) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public void setupAnimatorSet() {
            if (this.mAnimatorSet != null) goto L5;
            this.mAnimatorSet = new AnimatorSet();
        L5:
            this.mAnimatorSet.playTogether(this.mAnimators);
        }
    }

    public static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState r1) {
            this.mDelegateState = r1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat r0 = new AnimatedVectorDrawableCompat();
            Drawable r2 = this.mDelegateState.newDrawable();
            r0.mDelegateDrawable = r2;
            r2.setCallback(r0.mCallback);
            return r0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r2) {
            AnimatedVectorDrawableCompat r0 = new AnimatedVectorDrawableCompat();
            Drawable r1 = this.mDelegateState.newDrawable(r2);
            r0.mDelegateDrawable = r1;
            r1.setCallback(r0.mCallback);
            return r0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r2, Resources.Theme r3) {
            AnimatedVectorDrawableCompat r0 = new AnimatedVectorDrawableCompat();
            Drawable r1 = this.mDelegateState.newDrawable(r2, r3);
            r0.mDelegateDrawable = r1;
            r1.setCallback(r0.mCallback);
            return r0;
        }
    }

    public AnimatedVectorDrawableCompat() {
        this(null, null, null);
    }

    private AnimatedVectorDrawableCompat(Context r2) {
        this(r2, null, null);
    }

    private AnimatedVectorDrawableCompat(Context r3, AnimatedVectorDrawableCompatState r4, Resources r5) {
        this.mArgbEvaluator = null;
        this.mAnimatorListener = null;
        this.mAnimationCallbacks = null;
        AnonymousClass1 r0 = new AnonymousClass1(this);
        this.mCallback = r0;
        this.mContext = r3;
        if (r4 == null) goto L6;
        this.mAnimatedVectorState = r4;
        return;
    L6:
        this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(r3, r4, r0, r5);
    }

    public static AnimatedVectorDrawableCompat create(Context r2, int r3) {
        AnimatedVectorDrawableCompat r0 = new AnimatedVectorDrawableCompat(r2);
        Drawable r22 = ResourcesCompat.getDrawable(r2.getResources(), r3, r2.getTheme());
        r0.mDelegateDrawable = r22;
        r22.setCallback(r0.mCallback);
        r0.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(r0.mDelegateDrawable.getConstantState());
        return r0;
    }

    private static void registerPlatformCallback(AnimatedVectorDrawable r0, Animatable2Compat$AnimationCallback r1) {
        r0.registerAnimationCallback(r1.getPlatformCallback());
    }

    private void removeAnimatorSetListener() {
        Animator.AnimatorListener r0 = this.mAnimatorListener;
        if (r0 == null) goto L6;
        this.mAnimatedVectorState.mAnimatorSet.removeListener(r0);
        this.mAnimatorListener = null;
        return;
    }

    private void setupAnimatorsForTarget(String r3, Animator r4) {
        r4.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(r3));
        AnimatedVectorDrawableCompatState r0 = this.mAnimatedVectorState;
        if (r0.mAnimators != null) goto L5;
        r0.mAnimators = new ArrayList();
        AnimatedVectorDrawableCompatState r02 = this.mAnimatedVectorState;
        r02.mTargetNameMap = new ArrayMap();
    L5:
        this.mAnimatedVectorState.mAnimators.add(r4);
        this.mAnimatedVectorState.mTargetNameMap.put(r4, r3);
    }

    private static boolean unregisterPlatformCallback(AnimatedVectorDrawable r0, Animatable2Compat$AnimationCallback r1) {
        return r0.unregisterAnimationCallback(r1.getPlatformCallback());
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme r1) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.applyTheme(r0, r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        return DrawableCompat.canApplyTheme(r0);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.draw(r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.draw(r2);
        if (this.mAnimatedVectorState.mAnimatorSet.isStarted() == false) goto L10;
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return DrawableCompat.getAlpha(r0);
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 != null) goto L5;
        int r02 = super.getChangingConfigurations();
        return this.mAnimatedVectorState.mChangingConfigurations | r02;
    L5:
        return r0.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return DrawableCompat.getColorFilter(r0);
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.mDelegateDrawable != null) goto L5;
        return null;
    L5:
        return new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getIntrinsicHeight();
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getIntrinsicWidth();
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getOpacity();
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect r1) {
        return super.getPadding(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources r2, XmlPullParser r3, AttributeSet r4) {
        inflate(r2, r3, r4, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources r7, XmlPullParser r8, AttributeSet r9, Resources.Theme r10) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.inflate(r0, r7, r8, r9, r10);
        return;
    L6:
        int r02 = r8.getEventType();
        int r1 = r8.getDepth() + 1;
    L7:
        if (r02 == 1) goto L34;
        if (r8.getDepth() >= r1) goto L13;
        if (r02 == 3) goto L34;
    L13:
        if (r02 != 2) goto L33;
        String r03 = r8.getName();
        if ("animated-vector".equals(r03) == false) goto L24;
        TypedArray r04 = TypedArrayUtils.obtainAttributes(r7, r10, r9, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
        int r3 = r04.getResourceId(0, 0);
        if (r3 == 0) goto L22;
        VectorDrawableCompat r32 = VectorDrawableCompat.create(r7, r3, r10);
        r32.setAllowCaching(false);
        r32.setCallback(this.mCallback);
        VectorDrawableCompat r4 = this.mAnimatedVectorState.mVectorDrawable;
        if (r4 == null) goto L21;
        r4.setCallback(null);
    L21:
        this.mAnimatedVectorState.mVectorDrawable = r32;
    L22:
        r04.recycle();
        goto L33
    L24:
        if ("target".equals(r03) == false) goto L33;
        TypedArray r05 = r7.obtainAttributes(r9, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
        String r33 = r05.getString(0);
        int r42 = r05.getResourceId(1, 0);
        if (r42 == 0) goto L32;
        Context r5 = this.mContext;
        if (r5 == null) goto L30;
        setupAnimatorsForTarget(r33, AnimatorInflaterCompat.loadAnimator(r5, r42));
        goto L32
    L30:
        r05.recycle();
        Segment$$ExternalSyntheticBUOutline1.m("Context can't be null when inflating animators");
        return;
    L32:
        r05.recycle();
    L33:
        r02 = r8.next();
    L34:
        this.mAnimatedVectorState.setupAnimatorSet();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return DrawableCompat.isAutoMirrored(r0);
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return ((AnimatedVectorDrawable) r0).isRunning();
    L7:
        return this.mAnimatedVectorState.mAnimatorSet.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.isStateful();
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L5;
        r0.mutate();
    L5:
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setBounds(r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setBounds(r2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public boolean onLevelChange(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.setLevel(r2);
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.setLevel(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.setState(r2);
    L7:
        return this.mAnimatedVectorState.mVectorDrawable.setState(r2);
    }

    public void registerAnimationCallback(Animatable2Compat$AnimationCallback r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        registerPlatformCallback((AnimatedVectorDrawable) r0, r2);
        return;
    L6:
        if (r2 != null) goto L9;
        return;
    L9:
        if (this.mAnimationCallbacks != null) goto L12;
        this.mAnimationCallbacks = new ArrayList();
    L12:
        if (this.mAnimationCallbacks.contains(r2) == false) goto L14;
        return;
    L14:
        this.mAnimationCallbacks.add(r2);
        if (this.mAnimatorListener != null) goto L17;
        this.mAnimatorListener = new AnonymousClass2(this);
    L17:
        this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setAlpha(r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setAlpha(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setAutoMirrored(r0, r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(r2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int r1) {
        super.setChangingConfigurations(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int r1, PorterDuff.Mode r2) {
        super.setColorFilter(r1, r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setColorFilter(r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setColorFilter(r2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean r1) {
        super.setFilterBitmap(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float r1, float r2) {
        super.setHotspot(r1, r2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int r1, int r2, int r3, int r4) {
        super.setHotspotBounds(r1, r2, r3, r4);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] r1) {
        return super.setState(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setTint(r0, r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setTint(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setTintList(r0, r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setTintList(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setTintMode(r0, r2);
        return;
    L6:
        this.mAnimatedVectorState.mVectorDrawable.setTintMode(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r2, boolean r3) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 != null) goto L5;
        this.mAnimatedVectorState.mVectorDrawable.setVisible(r2, r3);
        return super.setVisible(r2, r3);
    L5:
        return r0.setVisible(r2, r3);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        ((AnimatedVectorDrawable) r0).start();
        return;
    L7:
        if (this.mAnimatedVectorState.mAnimatorSet.isStarted() == false) goto L9;
        return;
    L9:
        this.mAnimatedVectorState.mAnimatorSet.start();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        ((AnimatedVectorDrawable) r0).stop();
        return;
    L6:
        this.mAnimatedVectorState.mAnimatorSet.end();
    }

    public boolean unregisterAnimationCallback(Animatable2Compat$AnimationCallback r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L5;
        unregisterPlatformCallback((AnimatedVectorDrawable) r0, r2);
    L5:
        ArrayList<Animatable2Compat$AnimationCallback> r02 = this.mAnimationCallbacks;
        if (r02 == null) goto L13;
        if (r2 == null) goto L15;
        boolean r22 = r02.remove(r2);
        if (this.mAnimationCallbacks.size() != 0) goto L12;
        removeAnimatorSetListener();
    L12:
        return r22;
    L15:
        return false;
    L13:
        return false;
    }
}
