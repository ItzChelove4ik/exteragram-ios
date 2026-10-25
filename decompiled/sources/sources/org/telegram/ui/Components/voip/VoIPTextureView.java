package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.webrtc.RendererCommon;
import org.webrtc.TextureViewRenderer;

/* loaded from: classes7.dex */
public class VoIPTextureView extends FrameLayout {
    public static int SCALE_TYPE_ADAPTIVE = 2;
    public static int SCALE_TYPE_FILL = 0;
    public static int SCALE_TYPE_FIT = 1;
    public static int SCALE_TYPE_NONE = 3;
    int animateFromHeight;
    float animateFromRendererH;
    float animateFromRendererW;
    float animateFromThumbScale;
    int animateFromWidth;
    float animateFromX;
    float animateFromY;
    long animateNextDuration;
    boolean animateOnNextLayout;
    ArrayList<Animator> animateOnNextLayoutAnimations;
    boolean animateWithParent;
    public float animationProgress;
    float aninateFromScale;
    float aninateFromScaleBlur;
    final boolean applyRotation;
    public View backgroundView;
    public TextureView blurRenderer;
    public Bitmap cameraLastBitmap;
    float clipHorizontal;
    boolean clipToTexture;
    float clipVertical;
    ValueAnimator currentAnimation;
    float currentClipHorizontal;
    float currentClipVertical;
    float currentThumbScale;
    boolean ignoreLayout;
    public final ImageView imageView;
    final boolean isCamera;
    private View placeholderView;
    public final TextureViewRenderer renderer;
    float roundRadius;
    public float scaleTextureToFill;
    private float scaleTextureToFillBlur;
    private float scaleThumb;
    public int scaleType;
    private boolean screencast;
    private ImageView screencastImage;
    private TextView screencastText;
    private FrameLayout screencastView;
    public float stubVisibleProgress;
    private Bitmap thumb;

    /* renamed from: org.telegram.ui.Components.voip.VoIPTextureView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextureViewRenderer {
        final /* synthetic */ VoIPTextureView this$0;

        public AnonymousClass1(VoIPTextureView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.webrtc.TextureViewRenderer, org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            super.onFirstFrameRendered();
            this.this$0.onFirstFrameRendered();
        }

        @Override // android.view.TextureView, android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPTextureView$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ VoIPTextureView this$0;

        public AnonymousClass2(VoIPTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r9, Outline r10) {
            VoIPTextureView r0 = this.this$0;
            if (r0.roundRadius >= 1.0f) goto L6;
            r10.setRect((int) r0.currentClipHorizontal, (int) r0.currentClipVertical, (int) (r9.getMeasuredWidth() - this.this$0.currentClipHorizontal), (int) (r9.getMeasuredHeight() - this.this$0.currentClipVertical));
            return;
        L6:
            int r3 = (int) r0.currentClipHorizontal;
            int r4 = (int) r0.currentClipVertical;
            int r5 = (int) (r9.getMeasuredWidth() - this.this$0.currentClipHorizontal);
            float r92 = r9.getMeasuredHeight();
            VoIPTextureView r8 = this.this$0;
            r10.setRoundRect(r3, r4, r5, (int) (r92 - r8.currentClipVertical), r8.roundRadius);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPTextureView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPTextureView this$0;

        public AnonymousClass3(VoIPTextureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            VoIPTextureView r32 = this.this$0;
            r32.currentClipVertical = 0.0f;
            r32.currentClipHorizontal = 0.0f;
            r32.renderer.setScaleX(r32.scaleTextureToFill);
            VoIPTextureView r33 = this.this$0;
            r33.renderer.setScaleY(r33.scaleTextureToFill);
            VoIPTextureView r34 = this.this$0;
            TextureView r1 = r34.blurRenderer;
            if (r1 == null) goto L5;
            r1.setScaleX(VoIPTextureView.m13988$$Nest$fgetscaleTextureToFillBlur(r34));
            VoIPTextureView r35 = this.this$0;
            r35.blurRenderer.setScaleY(VoIPTextureView.m13988$$Nest$fgetscaleTextureToFillBlur(r35));
        L5:
            this.this$0.setTranslationY(0.0f);
            this.this$0.setTranslationX(0.0f);
            VoIPTextureView r36 = this.this$0;
            r36.currentThumbScale = VoIPTextureView.m13989$$Nest$fgetscaleThumb(r36);
            this.this$0.currentAnimation = null;
        }
    }

    public static /* synthetic */ void $r8$lambda$KqvEkLPHEwHiQbF7rU1G6QVBo9E(VoIPTextureView r0, float r1, float r2, float r3, float r4, float r5, ValueAnimator r6) {
        r0.lambda$onLayout$0(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$fgetscaleTextureToFillBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13988$$Nest$fgetscaleTextureToFillBlur(VoIPTextureView r0) {
        return r0.scaleTextureToFillBlur;
    }

    /* renamed from: -$$Nest$fgetscaleThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13989$$Nest$fgetscaleThumb(VoIPTextureView r0) {
        return r0.scaleThumb;
    }

    static {
    }

    public VoIPTextureView(Context r7, boolean r8, boolean r9) {
        this(r7, r8, r9, true, false);
    }

    public VoIPTextureView(Context r21, boolean r22, boolean r23, boolean r24, boolean r25) {
        super(r21);
        this.stubVisibleProgress = 1.0f;
        this.animateOnNextLayoutAnimations = new ArrayList();
        this.aninateFromScale = 1.0f;
        this.aninateFromScaleBlur = 1.0f;
        this.animateFromThumbScale = 1.0f;
        this.isCamera = r22;
        this.applyRotation = r23;
        ImageView r5 = new ImageView(r21);
        this.imageView = r5;
        AnonymousClass1 r6 = new AnonymousClass1(this, r21);
        this.renderer = r6;
        r6.setFpsReduction(30.0f);
        r6.setOpaque(false);
        r6.setEnableHardwareScaler(true);
        r6.setIsCamera(!r23);
        if (r22 == true) goto L9;
        if (r23 == false) goto L9;
        View r13 = new View(r21);
        this.backgroundView = r13;
        r13.setBackgroundColor(-14999773);
        addView(this.backgroundView, LayoutHelper.createFrame(-1, -1.0f));
        if (r25 == false) goto L8;
        TextureView r132 = new TextureView(r21);
        this.blurRenderer = r132;
        addView(r132, LayoutHelper.createFrame(-1, -2, 17));
    L8:
        r6.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT);
        addView(r6, LayoutHelper.createFrame(-1, -2, 17));
    L16:
        addView(r5);
        TextureView r1 = this.blurRenderer;
        if (r1 == null) goto L19;
        r1.setOpaque(false);
    L19:
        FrameLayout r12 = new FrameLayout(getContext());
        this.screencastView = r12;
        r12.setBackground(new MotionBackgroundDrawable(-14602694, -13935795, -14395293, -14203560, true));
        addView(this.screencastView, LayoutHelper.createFrame(-1, -1.0f));
        this.screencastView.setVisibility(8);
        ImageView r14 = new ImageView(getContext());
        this.screencastImage = r14;
        r14.setScaleType(ImageView.ScaleType.CENTER);
        this.screencastImage.setImageResource(R.drawable.screencast_big);
        this.screencastView.addView(this.screencastImage, LayoutHelper.createFrame(82, 82.0f, 17, 0.0f, 0.0f, 0.0f, 60.0f));
        TextView r15 = new TextView(getContext());
        this.screencastText = r15;
        r15.setText(LocaleController.getString(R.string.VoipVideoScreenSharing));
        this.screencastText.setGravity(17);
        this.screencastText.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.screencastText.setTextColor(-1);
        this.screencastText.setTextSize(1, 15.0f);
        this.screencastText.setTypeface(AndroidUtilities.bold());
        this.screencastView.addView(this.screencastText, LayoutHelper.createFrame(-1, -2.0f, 17, 21.0f, 28.0f, 21.0f, 0.0f));
        if (r24 == false) goto L22;
        setOutlineProvider(new AnonymousClass2(this));
        setClipToOutline(true);
    L22:
        if (r22 == true) goto L24;
    L29:
        if (r23 == true) goto L35;
        this.renderer.setScreenRotation(((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRotation());
        return;
    L35:
        return;
    L24:
        if (this.cameraLastBitmap != null) goto L29;
        Bitmap r16 = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "voip_icthumb.jpg").getAbsolutePath());     // Catch: Throwable -> L32
        this.cameraLastBitmap = r16;     // Catch: Throwable -> L32
        if (r16 != null) goto L28;
        this.cameraLastBitmap = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "icthumb.jpg").getAbsolutePath());     // Catch: Throwable -> L32
    L28:
        r5.setImageBitmap(this.cameraLastBitmap);     // Catch: Throwable -> L32
        r5.setScaleType(ImageView.ScaleType.FIT_XY);     // Catch: Throwable -> L32
    L9:
        if (r22 == true) goto L13;
        if (r25 == false) goto L12;
        TextureView r133 = new TextureView(r21);
        this.blurRenderer = r133;
        addView(r133, LayoutHelper.createFrame(-1, -2, 17));
    L12:
        addView(r6, LayoutHelper.createFrame(-1, -2, 17));
        goto L16
    L13:
        if (r25 == false) goto L15;
        TextureView r134 = new TextureView(r21);
        this.blurRenderer = r134;
        addView(r134, LayoutHelper.createFrame(-1, -2, 17));
    L15:
        addView(r6);
        goto L16
    }

    private /* synthetic */ void lambda$onLayout$0(float r3, float r4, float r5, float r6, float r7, ValueAnimator r8) {
        float r82 = ((Float) r8.getAnimatedValue()).floatValue();
        float r0 = 1.0f - r82;
        this.animationProgress = r0;
        this.currentClipVertical = this.clipVertical * r82;
        this.currentClipHorizontal = this.clipHorizontal * r82;
        invalidateOutline();
        invalidate();
        float r32 = (r3 * r82) + (this.scaleTextureToFill * r0);
        this.renderer.setScaleX(r32);
        this.renderer.setScaleY(r32);
        float r42 = (r4 * r82) + (this.scaleTextureToFillBlur * r0);
        TextureView r33 = this.blurRenderer;
        if (r33 == null) goto L5;
        r33.setScaleX(r42);
        this.blurRenderer.setScaleY(r42);
    L5:
        setTranslationX(r5 * r82);
        setTranslationY(r6 * r82);
        this.currentThumbScale = (r7 * r82) + (this.scaleThumb * r0);
    }

    public void animateToLayout() {
        if (this.animateOnNextLayout == false) goto L5;
        return;
    L5:
        if (getMeasuredHeight() != 0) goto L7;
        return;
    L7:
        if (getMeasuredWidth() == 0) goto L19;
        this.animateFromHeight = getMeasuredHeight();
        this.animateFromWidth = getMeasuredWidth();
        if (this.animateWithParent == true) goto L12;
    L14:
        this.animateFromY = getY();
        this.animateFromX = getX();
    L15:
        this.aninateFromScale = this.scaleTextureToFill;
        this.aninateFromScaleBlur = this.scaleTextureToFillBlur;
        this.animateFromThumbScale = this.scaleThumb;
        this.animateFromRendererW = this.renderer.getMeasuredWidth();
        this.animateFromRendererH = this.renderer.getMeasuredHeight();
        this.animateOnNextLayout = true;
        requestLayout();
        return;
    L12:
        if (getParent() == null) goto L14;
        View r0 = (View) getParent();
        this.animateFromY = r0.getY();
        this.animateFromX = r0.getX();
        goto L15
    }

    public void attachBackgroundRenderer() {
        TextureView r0 = this.blurRenderer;
        if (r0 == null) goto L8;
        this.renderer.setBackgroundRenderer(r0);
        if (this.renderer.isFirstFrameRendered() == true) goto L9;
        this.blurRenderer.setAlpha(0.0f);
        return;
    L9:
        return;
    }

    public void cancelAnimation() {
        this.animateOnNextLayout = false;
        this.animateNextDuration = 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        super.dispatchDraw(r7);
        if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L17;
        if (this.blurRenderer == null) goto L11;
        r7.save();
        r7.translate(this.blurRenderer.getX(), this.blurRenderer.getY());
        Bitmap r0 = this.blurRenderer.getBitmap();
        if (r0 == null) goto L9;
        r7.scale(this.blurRenderer.getWidth() / r0.getWidth(), this.blurRenderer.getHeight() / r0.getHeight());
        r7.drawBitmap(r0, 0.0f, 0.0f, null);
    L9:
        r7.restore();
    L11:
        if (this.renderer == null) goto L17;
        r7.save();
        r7.translate(this.renderer.getX(), this.renderer.getY());
        Bitmap r02 = this.renderer.getBitmap();
        if (r02 == null) goto L15;
        r7.scale(this.renderer.getWidth() / r02.getWidth(), this.renderer.getHeight() / r02.getHeight());
        r7.drawBitmap(r02, 0.0f, 0.0f, null);
    L15:
        r7.restore();
    L17:
        if (this.imageView.getVisibility() == 0) goto L19;
        return;
    L19:
        if (this.renderer.isFirstFrameRendered() == false) goto L27;
        float r72 = this.stubVisibleProgress - 0.10666667f;
        this.stubVisibleProgress = r72;
        if (r72 > 0.0f) goto L24;
        this.stubVisibleProgress = 0.0f;
        this.imageView.setVisibility(8);
        return;
    L24:
        invalidate();
        this.imageView.setAlpha(this.stubVisibleProgress);
        return;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L11;
        if (r3 != this.renderer) goto L7;
        return false;
    L7:
        if (r3 != this.blurRenderer) goto L11;
        return false;
    L11:
        return super.drawChild(r2, r3, r4);
    }

    public View getPlaceholderView() {
        if (this.placeholderView != null) goto L6;
        View r0 = new View(getContext());
        this.placeholderView = r0;
        addView(r0, LayoutHelper.createFrameMatchParent());
    L6:
        return this.placeholderView;
    }

    public boolean isInAnimation() {
        if (this.currentAnimation == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void onFirstFrameRendered() {
        invalidate();
        if (this.renderer.getAlpha() == 1.0f) goto L5;
        this.renderer.animate().setDuration(300).alpha(1.0f);
    L5:
        TextureView r0 = this.blurRenderer;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.getAlpha() == 1.0f) goto L12;
        this.blurRenderer.animate().setDuration(300).alpha(1.0f);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        super.onLayout(r8, r9, r10, r11, r12);
        if (this.blurRenderer == null) goto L6;
        this.scaleTextureToFillBlur = Math.max(getMeasuredHeight() / this.blurRenderer.getMeasuredHeight(), getMeasuredWidth() / this.blurRenderer.getMeasuredWidth());
    L6:
        if (this.applyRotation == true) goto L9;
        this.renderer.updateRotation();
    L9:
        if (this.scaleType != SCALE_TYPE_NONE) goto L15;
        TextureView r82 = this.blurRenderer;
        if (r82 == null) goto L95;
        r82.setScaleX(this.scaleTextureToFillBlur);
        this.blurRenderer.setScaleY(this.scaleTextureToFillBlur);
        return;
    L95:
        return;
    L15:
        if (this.renderer.getMeasuredHeight() != 0) goto L17;
    L48:
        this.scaleTextureToFill = 1.0f;
        if (this.currentAnimation == null) goto L51;
    L54:
        if (this.thumb == null) goto L57;
        this.scaleThumb = Math.max(getMeasuredWidth() / this.thumb.getWidth(), getMeasuredHeight() / this.thumb.getHeight());
    L57:
        if (this.animateOnNextLayout == false) goto L88;
        this.aninateFromScale /= this.renderer.getMeasuredWidth() / this.animateFromRendererW;
        this.aninateFromScaleBlur /= this.renderer.getMeasuredWidth() / this.animateFromRendererW;
        int r83 = 0;
        this.animateOnNextLayout = false;
        if (this.animateWithParent == true) goto L61;
    L63:
        float r122 = this.animateFromY - getTop();
        float r0 = this.animateFromX - getLeft();
    L64:
        this.clipVertical = 0.0f;
        this.clipHorizontal = 0.0f;
        if (this.animateFromHeight == getMeasuredHeight()) goto L67;
        float r92 = (getMeasuredHeight() - this.animateFromHeight) / 2.0f;
        this.clipVertical = r92;
        r122 = r122 - r92;
    L67:
        float r5 = r122;
        if (this.animateFromWidth == getMeasuredWidth()) goto L70;
        float r93 = (getMeasuredWidth() - this.animateFromWidth) / 2.0f;
        this.clipHorizontal = r93;
        r0 = r0 - r93;
    L70:
        float r4 = r0;
        setTranslationY(r5);
        setTranslationX(r4);
        ValueAnimator r94 = this.currentAnimation;
        if (r94 == null) goto L73;
        r94.removeAllListeners();
        this.currentAnimation.cancel();
    L73:
        this.renderer.setScaleX(this.aninateFromScale);
        this.renderer.setScaleY(this.aninateFromScale);
        TextureView r95 = this.blurRenderer;
        if (r95 == null) goto L76;
        r95.setScaleX(this.aninateFromScaleBlur);
        this.blurRenderer.setScaleY(this.aninateFromScaleBlur);
    L76:
        this.currentClipVertical = this.clipVertical;
        this.currentClipHorizontal = this.clipHorizontal;
        invalidateOutline();
        invalidate();
        float r2 = this.aninateFromScale;
        float r3 = this.aninateFromScaleBlur;
        float r6 = this.animateFromThumbScale;
        ValueAnimator r96 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.currentAnimation = r96;
        r96.addUpdateListener(new VoIPTextureView$$ExternalSyntheticLambda0(this, r2, r3, r4, r5, r6));
        long r97 = this.animateNextDuration;
        ValueAnimator r1 = this.currentAnimation;
        if (r97 == 0) goto L79;
        r1.setDuration(r97);
    L80:
        this.currentAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.currentAnimation.addListener(new AnonymousClass3(this));
        this.currentAnimation.start();
        if (this.animateOnNextLayoutAnimations.isEmpty() == false) goto L83;
    L85:
        this.animateOnNextLayoutAnimations.clear();
        this.animateNextDuration = 0;
        return;
    L83:
        if (r83 >= this.animateOnNextLayoutAnimations.size()) goto L85;
        this.animateOnNextLayoutAnimations.get(r83).start();
        r83 = r83 + 1;
        goto L83
    L79:
        r1.setDuration(350);
        goto L80
    L61:
        if (getParent() == null) goto L63;
        View r98 = (View) getParent();
        r122 = this.animateFromY - r98.getTop();
        r0 = this.animateFromX - r98.getLeft();
        goto L64
    L88:
        if (this.currentAnimation != null) goto L96;
        this.renderer.setScaleX(this.scaleTextureToFill);
        this.renderer.setScaleY(this.scaleTextureToFill);
        TextureView r84 = this.blurRenderer;
        if (r84 == null) goto L92;
        r84.setScaleX(this.scaleTextureToFillBlur);
        this.blurRenderer.setScaleY(this.scaleTextureToFillBlur);
    L92:
        this.currentThumbScale = this.scaleThumb;
        return;
    L96:
        return;
    L51:
        if (this.animateOnNextLayout == true) goto L54;
        this.currentClipHorizontal = 0.0f;
        this.currentClipVertical = 0.0f;
        goto L54
    L17:
        if (this.renderer.getMeasuredWidth() == 0) goto L48;
        if (getMeasuredHeight() == 0) goto L48;
        if (getMeasuredWidth() == 0) goto L48;
        int r85 = this.scaleType;
        if (r85 != SCALE_TYPE_FILL) goto L27;
        this.scaleTextureToFill = Math.max(getMeasuredHeight() / this.renderer.getMeasuredHeight(), getMeasuredWidth() / this.renderer.getMeasuredWidth());
        goto L54
    L27:
        if (r85 != SCALE_TYPE_ADAPTIVE) goto L38;
        if (Math.abs((getMeasuredHeight() / getMeasuredWidth()) - 1.0f) >= 0.02f) goto L32;
        this.scaleTextureToFill = Math.max(getMeasuredHeight() / this.renderer.getMeasuredHeight(), getMeasuredWidth() / this.renderer.getMeasuredWidth());
        goto L54
    L32:
        if (getMeasuredWidth() > getMeasuredHeight()) goto L34;
    L36:
        this.scaleTextureToFill = Math.min(getMeasuredHeight() / this.renderer.getMeasuredHeight(), getMeasuredWidth() / this.renderer.getMeasuredWidth());
        goto L54
    L34:
        if (this.renderer.getMeasuredHeight() <= this.renderer.getMeasuredWidth()) goto L36;
        this.scaleTextureToFill = Math.max(getMeasuredHeight() / this.renderer.getMeasuredHeight(), (getMeasuredWidth() / 2.0f) / this.renderer.getMeasuredWidth());
        goto L54
    L38:
        if (r85 != SCALE_TYPE_FIT) goto L54;
        this.scaleTextureToFill = Math.min(getMeasuredHeight() / this.renderer.getMeasuredHeight(), getMeasuredWidth() / this.renderer.getMeasuredWidth());
        if (this.clipToTexture == false) goto L54;
        if (this.animateWithParent == true) goto L54;
        if (this.currentAnimation != null) goto L54;
        if (this.animateOnNextLayout == true) goto L54;
        this.currentClipHorizontal = (getMeasuredWidth() - this.renderer.getMeasuredWidth()) / 2.0f;
        this.currentClipVertical = (getMeasuredHeight() - this.renderer.getMeasuredHeight()) / 2.0f;
        invalidateOutline();
        goto L54
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.applyRotation == true) goto L5;
        this.ignoreLayout = true;
        Display r0 = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        this.renderer.setScreenRotation(r0.getRotation());
        this.ignoreLayout = false;
    L5:
        super.onMeasure(r3, r4);
        updateRendererSize();
        super.onMeasure(r3, r4);
        this.renderer.updateRotation();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void saveCameraLastBitmap() {
        Bitmap r3 = this.renderer.getBitmap(150, 150);
        if (r3 != null) goto L5;
        return;
    L5:
        if (r3.getPixel(0, 0) == 0) goto L13;
        Utilities.blurBitmap(r3, 3);
        FileOutputStream r1 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "voip_icthumb.jpg"));     // Catch: Throwable -> L9
        r3.compress(Bitmap.CompressFormat.JPEG, 100, r1);     // Catch: Throwable -> L9
        r1.close();     // Catch: Throwable -> L9
        return;
    L14:
        return;
    }

    public void setAnimateNextDuration(long r1) {
        this.animateNextDuration = r1;
    }

    public void setAnimateWithParent(boolean r1) {
        this.animateWithParent = r1;
    }

    public void setIsScreencast(boolean r4) {
        this.screencast = r4;
        FrameLayout r0 = this.screencastView;
        if (r4 == false) goto L5;
        int r42 = 0;
    L6:
        r0.setVisibility(r42);
        boolean r43 = this.screencast;
        TextureViewRenderer r02 = this.renderer;
        if (r43 == false) goto L13;
        r02.setVisibility(8);
        TextureView r44 = this.blurRenderer;
        if (r44 == null) goto L11;
        r44.setVisibility(8);
    L11:
        this.imageView.setVisibility(8);
        return;
    L13:
        r02.setVisibility(0);
        TextureView r3 = this.blurRenderer;
        if (r3 == null) goto L17;
        r3.setVisibility(0);
        return;
    L17:
        return;
    L5:
        r42 = 8;
        goto L6
    }

    public void setRoundCorners(float r2) {
        if (this.roundRadius == r2) goto L6;
        this.roundRadius = r2;
        invalidateOutline();
        return;
    }

    public void setScreenshareMiniProgress(float r5, boolean r6) {
        if (this.screencast == true) goto L5;
        return;
    L5:
        float r0 = ((View) getParent()).getScaleX();
        float r2 = 1.0f;
        this.screencastText.setAlpha(1.0f - r5);
        if (r6 == true) goto L9;
        r2 = 1.0f / r0;
        float r1 = (0.4f / r0) * r5;
    L8:
        float r22 = r2 - r1;
        this.screencastImage.setScaleX(r22);
        this.screencastImage.setScaleY(r22);
        this.screencastImage.setTranslationY(AndroidUtilities.dp(60.0f) * r5);
        return;
    L9:
        r1 = 0.4f * r5;
        goto L8
    }

    public void setStub(VoIPTextureView r4) {
        if (this.screencast == false) goto L5;
        return;
    L5:
        Bitmap r0 = r4.renderer.getBitmap();
        if (r0 != null) goto L8;
    L11:
        this.imageView.setImageDrawable(r4.imageView.getDrawable());
    L12:
        this.stubVisibleProgress = 1.0f;
        this.imageView.setVisibility(0);
        this.imageView.setAlpha(1.0f);
        return;
    L8:
        if (r0.getPixel(0, 0) == 0) goto L11;
        this.imageView.setImageBitmap(r0);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        goto L12
    }

    public void setThumb(Bitmap r1) {
        this.thumb = r1;
    }

    public void synchOrRunAnimation(Animator r2) {
        if (this.animateOnNextLayout == false) goto L6;
        this.animateOnNextLayoutAnimations.add(r2);
        return;
    L6:
        r2.start();
    }

    public void updateRendererSize() {
        TextureView r0 = this.blurRenderer;
        if (r0 == null) goto L6;
        ViewGroup.LayoutParams r02 = r0.getLayoutParams();
        r02.width = this.renderer.getMeasuredWidth();
        ViewGroup.LayoutParams r03 = this.blurRenderer.getLayoutParams();
        r03.height = this.renderer.getMeasuredHeight();
        return;
    }

    public void updateRotation() {
        if (this.applyRotation == true) goto L6;
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        return;
    }
}
