package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.Crop.CropRotationWheel;
import org.telegram.ui.Components.Crop.CropTransform;
import org.telegram.ui.Components.Crop.CropView;

/* loaded from: classes7.dex */
public class PhotoCropView extends FrameLayout {
    public final Property<PhotoCropView, Float> ANIMATION_VALUE;
    public final Property<PhotoCropView, Float> PROGRESS_VALUE;
    private Paint circlePaint;
    public CropView cropView;
    private PhotoCropViewDelegate delegate;
    private float flashAlpha;
    private boolean inBubbleMode;
    public boolean isReset;
    private final Theme.ResourcesProvider resourcesProvider;
    private AnimatorSet thumbAnimation;
    private float thumbAnimationProgress;
    private ImageReceiver thumbImageView;
    private boolean thumbImageVisible;
    private boolean thumbImageVisibleOverride;
    private float thumbImageVisibleProgress;
    private AnimatorSet thumbOverrideAnimation;
    public CropRotationWheel wheelView;

    /* renamed from: org.telegram.ui.Components.PhotoCropView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimationProperties.FloatProperty<PhotoCropView> {
        final /* synthetic */ PhotoCropView this$0;

        public AnonymousClass1(PhotoCropView r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(PhotoCropView r1) {
            return Float.valueOf(PhotoCropView.m11688$$Nest$fgetthumbAnimationProgress(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((PhotoCropView) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(PhotoCropView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(PhotoCropView r1, float r2) {
            PhotoCropView.m11691$$Nest$fputthumbAnimationProgress(this.this$0, r2);
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoCropView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimationProperties.FloatProperty<PhotoCropView> {
        final /* synthetic */ PhotoCropView this$0;

        public AnonymousClass2(PhotoCropView r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(PhotoCropView r1) {
            return Float.valueOf(PhotoCropView.m11689$$Nest$fgetthumbImageVisibleProgress(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((PhotoCropView) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(PhotoCropView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(PhotoCropView r1, float r2) {
            PhotoCropView.m11692$$Nest$fputthumbImageVisibleProgress(this.this$0, r2);
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoCropView$3, reason: invalid class name */
    public class AnonymousClass3 implements CropView.CropViewListener {
        final /* synthetic */ PhotoCropView this$0;

        public AnonymousClass3(PhotoCropView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onAspectLock(boolean r1) {
            this.this$0.wheelView.setAspectLock(r1);
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onChange(boolean r2) {
            PhotoCropView r0 = this.this$0;
            r0.isReset = r2;
            if (PhotoCropView.m11687$$Nest$fgetdelegate(r0) == null) goto L6;
            PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0).onChange(r2);
            return;
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onTapUp() {
            if (PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0).onTapUp();
            return;
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onUpdate() {
            if (PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0).onUpdate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoCropView$4, reason: invalid class name */
    public class AnonymousClass4 implements CropRotationWheel.RotationWheelListener {
        final /* synthetic */ PhotoCropView this$0;

        public AnonymousClass4(PhotoCropView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void aspectRatioPressed() {
            this.this$0.cropView.showAspectRatioDialog();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public boolean mirror() {
            if (PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            return PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0).mirror();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void onChange(float r2) {
            this.this$0.cropView.setRotation(r2);
            PhotoCropView r22 = this.this$0;
            r22.isReset = false;
            if (PhotoCropView.m11687$$Nest$fgetdelegate(r22) == null) goto L6;
            PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0).onChange(false);
            return;
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void onEnd(float r1) {
            this.this$0.cropView.onRotationEnded();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void onStart() {
            this.this$0.cropView.onRotationBegan();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public boolean rotate90Pressed() {
            if (PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            return PhotoCropView.m11687$$Nest$fgetdelegate(this.this$0).rotate();
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoCropView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ PhotoCropView this$0;

        public AnonymousClass5(PhotoCropView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PhotoCropView.m11690$$Nest$fputthumbAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoCropView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ PhotoCropView this$0;

        public AnonymousClass6(PhotoCropView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PhotoCropView.m11693$$Nest$fputthumbOverrideAnimation(this.this$0, null);
        }
    }

    public interface PhotoCropViewDelegate {
        int getVideoThumbX();

        boolean mirror();

        void onChange(boolean r1);

        void onTapUp();

        void onUpdate();

        void onVideoThumbClick();

        boolean rotate();
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoCropViewDelegate m11687$$Nest$fgetdelegate(PhotoCropView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetthumbAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11688$$Nest$fgetthumbAnimationProgress(PhotoCropView r0) {
        return r0.thumbAnimationProgress;
    }

    /* renamed from: -$$Nest$fgetthumbImageVisibleProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11689$$Nest$fgetthumbImageVisibleProgress(PhotoCropView r0) {
        return r0.thumbImageVisibleProgress;
    }

    /* renamed from: -$$Nest$fputthumbAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11690$$Nest$fputthumbAnimation(PhotoCropView r0, AnimatorSet r1) {
        r0.thumbAnimation = r1;
    }

    /* renamed from: -$$Nest$fputthumbAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11691$$Nest$fputthumbAnimationProgress(PhotoCropView r0, float r1) {
        r0.thumbAnimationProgress = r1;
    }

    /* renamed from: -$$Nest$fputthumbImageVisibleProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11692$$Nest$fputthumbImageVisibleProgress(PhotoCropView r0, float r1) {
        r0.thumbImageVisibleProgress = r1;
    }

    /* renamed from: -$$Nest$fputthumbOverrideAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11693$$Nest$fputthumbOverrideAnimation(PhotoCropView r0, AnimatorSet r1) {
        r0.thumbOverrideAnimation = r1;
    }

    public PhotoCropView(Context r8, Theme.ResourcesProvider r9) {
        super(r8);
        this.isReset = true;
        this.thumbImageVisibleOverride = true;
        this.thumbAnimationProgress = 1.0f;
        this.flashAlpha = 0.0f;
        this.circlePaint = new Paint(1);
        this.ANIMATION_VALUE = new AnonymousClass1(this, "thumbAnimationProgress");
        this.PROGRESS_VALUE = new AnonymousClass2(this, "thumbImageVisibleProgress");
        this.resourcesProvider = r9;
        this.inBubbleMode = r8 instanceof BubbleActivity;
        CropView r92 = new CropView(r8);
        this.cropView = r92;
        r92.setListener(new AnonymousClass3(this));
        this.cropView.setBottomPadding(AndroidUtilities.dp(64.0f));
        addView(this.cropView);
        this.thumbImageView = new ImageReceiver(this);
        CropRotationWheel r93 = new CropRotationWheel(r8);
        this.wheelView = r93;
        r93.setListener(new AnonymousClass4(this));
        addView(this.wheelView, LayoutHelper.createFrame(-1, -2.0f, 81, 0.0f, 0.0f, 0.0f, 0.0f));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public void cancelThumbAnimation() {
        AnimatorSet r0 = this.thumbAnimation;
        if (r0 == null) goto L6;
        r0.cancel();
        this.thumbAnimation = null;
        this.thumbImageVisible = false;
        return;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r10, View r11, long r12) {
        boolean r122 = super.drawChild(r10, r11, r12);
        if (this.thumbImageVisible == false) goto L10;
        CropView r13 = this.cropView;
        if (r11 != r13) goto L10;
        RectF r112 = r13.getActualRect();
        int r132 = AndroidUtilities.dp(32.0f);
        int r0 = (this.delegate.getVideoThumbX() - (r132 / 2)) + AndroidUtilities.dp(2.0f);
        int r3 = getMeasuredHeight() - AndroidUtilities.dp(156.0f);
        float r4 = r112.left;
        float r6 = this.thumbAnimationProgress;
        float r42 = r4 + ((r0 - r4) * r6);
        float r5 = r112.top;
        float r52 = r5 + ((r3 - r5) * r6);
        float r62 = r112.width() + ((r132 - r112.width()) * this.thumbAnimationProgress);
        this.thumbImageView.setRoundRadius((int) (r62 / 2.0f));
        this.thumbImageView.setImageCoords(r42, r52, r62, r62);
        this.thumbImageView.setAlpha(this.thumbImageVisibleProgress);
        this.thumbImageView.draw(r10);
        if (this.flashAlpha <= 0.0f) goto L9;
        this.circlePaint.setColor(-1);
        this.circlePaint.setAlpha((int) (this.flashAlpha * 255.0f));
        r10.drawCircle(r112.centerX(), r112.centerY(), r112.width() / 2.0f, this.circlePaint);
    L9:
        this.circlePaint.setColor(getThemedColor(Theme.key_chat_editMediaButton));
        this.circlePaint.setAlpha(Math.min(255, (int) ((this.thumbAnimationProgress * 255.0f) * this.thumbImageVisibleProgress)));
        r10.drawCircle(r0 + r1, (r3 + r132) + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(3.0f), this.circlePaint);
    L10:
        return r122;
    }

    public float getRectSizeX() {
        return this.cropView.getCropWidth();
    }

    public float getRectSizeY() {
        return this.cropView.getCropHeight();
    }

    public float getRectX() {
        return this.cropView.getCropLeft() - AndroidUtilities.dp(14.0f);
    }

    public float getRectY() {
        float r0 = this.cropView.getCropTop() - AndroidUtilities.dp(14.0f);
        if (this.inBubbleMode == true) goto L5;
        int r2 = AndroidUtilities.statusBarHeight;
    L7:
        return r0 - r2;
    L5:
        r2 = 0;
        goto L7
    }

    public Bitmap getVideoThumb() {
        if (this.thumbImageVisible == true) goto L5;
        return null;
    L5:
        if (this.thumbImageVisibleOverride == true) goto L7;
        return null;
    L7:
        return this.thumbImageView.getBitmap();
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.cropView.invalidate();
    }

    public boolean isReady() {
        return this.cropView.isReady();
    }

    public void makeCrop(MediaController.MediaEditState r1) {
        this.cropView.makeCrop(r1);
    }

    public boolean mirror() {
        return this.cropView.mirror();
    }

    public void onAppear() {
        this.cropView.willShow();
    }

    public void onAppeared() {
        this.cropView.show();
    }

    public void onDisappear() {
        this.cropView.hide();
    }

    public void onHide() {
        this.cropView.onHide();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r4) {
        if (this.thumbImageVisibleOverride == false) goto L13;
        if (this.thumbImageVisible == false) goto L13;
        if (this.thumbImageView.isInsideImage(r4.getX(), r4.getY()) == false) goto L13;
        if (r4.getAction() != 1) goto L11;
        this.delegate.onVideoThumbClick();
    L11:
        return true;
    L13:
        return super.onInterceptTouchEvent(r4);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        this.cropView.updateLayout();
    }

    public void onShow() {
        this.cropView.onShow();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        if (this.thumbImageVisibleOverride == false) goto L13;
        if (this.thumbImageVisible == false) goto L13;
        if (this.thumbImageView.isInsideImage(r4.getX(), r4.getY()) == false) goto L13;
        if (r4.getAction() != 1) goto L11;
        this.delegate.onVideoThumbClick();
    L11:
        return true;
    L13:
        return super.onTouchEvent(r4);
    }

    public void reset(boolean r3) {
        this.wheelView.reset(true);
        this.cropView.reset(r3);
    }

    public boolean rotate(float r3) {
        CropRotationWheel r0 = this.wheelView;
        if (r0 == null) goto L6;
        r0.reset(false);
    L6:
        return this.cropView.rotate(r3);
    }

    public void setAspectRatio(float r1) {
        this.cropView.setAspectRatio(r1);
    }

    public void setBitmap(Bitmap r11, int r12, boolean r13, boolean r14, PaintingOverlay r15, CropTransform r16, VideoEditTextureView r17, MediaController.CropState r18) {
        requestLayout();
        int r9 = 0;
        this.thumbImageVisible = false;
        this.thumbImageView.setImageBitmap(null);
        this.cropView.setBitmap(r11, r12, r13, r14, r15, r16, r17, r18);
        this.wheelView.setFreeform(r13);
        boolean r122 = true;
        this.wheelView.reset(true);
        CropRotationWheel r112 = this.wheelView;
        if (r18 == null) goto L9;
        r112.setRotation(r18.cropRotate, false);
        CropRotationWheel r113 = this.wheelView;
        if (r18.transformRotation != 0) goto L8;
        r122 = false;
    L8:
        r113.setRotated(r122);
        this.wheelView.setMirrored(r18.mirrored);
    L10:
        CropRotationWheel r10 = this.wheelView;
        if (r13 == true) goto L14;
        r9 = 4;
    L14:
        r10.setVisibility(r9);
        return;
    L9:
        r112.setRotated(false);
        this.wheelView.setMirrored(false);
        goto L10
    }

    public void setDelegate(PhotoCropViewDelegate r1) {
        this.delegate = r1;
    }

    public void setFreeform(boolean r1) {
        this.cropView.setFreeform(r1);
    }

    public void setSubtitle(String r1) {
        this.cropView.setSubtitle(r1);
    }

    public void setVideoThumb(Bitmap r4, int r5) {
        if (r4 == null) goto L5;
        boolean r2 = true;
    L6:
        this.thumbImageVisible = r2;
        this.thumbImageView.setImageBitmap(r4);
        this.thumbImageView.setOrientation(r5, false);
        AnimatorSet r42 = this.thumbAnimation;
        if (r42 == null) goto L9;
        r42.cancel();
    L9:
        AnimatorSet r43 = this.thumbOverrideAnimation;
        if (r43 == null) goto L12;
        r43.cancel();
    L12:
        this.thumbImageVisibleOverride = true;
        this.thumbImageVisibleProgress = 1.0f;
        AnimatorSet r44 = new AnimatorSet();
        this.thumbAnimation = r44;
        r44.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, this.ANIMATION_VALUE, new float[]{0.0f, 1.0f})});
        this.thumbAnimation.setDuration(250);
        this.thumbAnimation.setInterpolator(new OvershootInterpolator(1.01f));
        this.thumbAnimation.addListener(new AnonymousClass5(this));
        this.thumbAnimation.start();
        return;
    L5:
        r2 = false;
        goto L6
    }

    public void setVideoThumbFlashAlpha(float r1) {
        this.flashAlpha = r1;
        invalidate();
    }

    public void setVideoThumbVisible(boolean r6) {
        if (this.thumbImageVisibleOverride != r6) goto L5;
        return;
    L5:
        this.thumbImageVisibleOverride = r6;
        AnimatorSet r0 = this.thumbOverrideAnimation;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        AnimatorSet r02 = new AnimatorSet();
        this.thumbOverrideAnimation = r02;
        Property<PhotoCropView, Float> r1 = this.PROGRESS_VALUE;
        if (r6 == false) goto L11;
        float r62 = 1.0f;
    L12:
        r02.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r1, new float[]{r62})});
        this.thumbOverrideAnimation.setDuration(180);
        this.thumbOverrideAnimation.addListener(new AnonymousClass6(this));
        this.thumbOverrideAnimation.start();
        return;
    L11:
        r62 = 0.0f;
        goto L12
    }
}
