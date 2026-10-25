package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotFullscreenButtons$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraView;
import org.telegram.messenger.camera.CameraView$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.video.VideoPlayerHolderBase;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.recorder.CollageLayout;
import org.telegram.ui.Stories.recorder.QRScanner;

/* loaded from: classes7.dex */
public abstract class CollageLayoutView2 extends FrameLayout implements ItemOptions.ScrimView {
    private final AnimatedFloat[] animatedColumns;
    private final AnimatedFloat animatedReordering;
    private final AnimatedFloat animatedRows;
    private boolean attached;
    private final BlurringShader.BlurManager blurManager;
    private Object blurRenderNode;
    private Drawable cameraThumbDrawable;
    private boolean cameraThumbVisible;
    public CameraView cameraView;
    private Object cameraViewBlurRenderNode;
    private Runnable cancelGestures;
    private final Path clipPath;
    private final FrameLayout containerView;
    private CollageLayout currentLayout;
    public Part currentPart;
    public float dx;
    public float dy;
    private boolean fastSeek;
    private final LinearGradient gradient;
    private final Matrix gradientMatrix;
    private final int gradientWidth;
    private final Paint highlightPaint;
    private final Path highlightPath;
    public boolean isMuted;
    private long lastPausedPosition;
    public float ldx;
    public float ldy;
    private final float[] lefts;
    public Part longPressedPart;
    private boolean needsBlur;
    public Part nextPart;
    private Runnable onCameraThumbClick;
    public Runnable onLongPressPart;
    private Runnable onResetState;
    public final ArrayList<Part> parts;
    private boolean playing;
    public Part pressedPart;
    private boolean preview;
    private long previewStartTime;
    private PreviewView previewView;
    public final QRScanner.QrRegionDrawer qrDrawer;
    private final float[] radii;
    private final RectF rect;
    public final ArrayList<Part> removingParts;
    private Object renderNode;
    public boolean reordering;
    public Part reorderingPart;
    public boolean reorderingTouch;
    private final Runnable resetReordering;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean restorePositionOnPlaying;
    private final float[] rights;
    private final Runnable syncRunnable;
    private TimelineView timelineView;
    public float tx;
    public float ty;

    public class Part {
        private ValueAnimator animator;
        public RectF bounds;
        public float boundsTransition;
        private StoryEntry content;
        private boolean current;
        public RectF fromBounds;
        public boolean hasBounds;
        private final AnimatedFloat highlightAnimated;
        public final ImageReceiver imageReceiver;
        private int index;
        public CollageLayout.Part part;
        private volatile long pendingSeek;
        public TextureView textureView;
        public boolean textureViewReady;
        final /* synthetic */ CollageLayoutView2 this$0;
        public VideoPlayerHolderBase videoPlayer;

        /* renamed from: org.telegram.ui.Stories.recorder.CollageLayoutView2$Part$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ Part this$1;

            public AnonymousClass1(Part r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r2) {
                Part r0 = this.this$1;
                r0.boundsTransition = ((Float) r2.getAnimatedValue()).floatValue();
                this.this$1.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.CollageLayoutView2$Part$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ Part this$1;

            public AnonymousClass2(Part r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                Part r22 = this.this$1;
                r22.boundsTransition = 1.0f;
                if (r22.this$0.removingParts.contains(r22) == false) goto L5;
                this.this$1.imageReceiver.onDetachedFromWindow();
                this.this$1.destroyContent();
                Part r23 = this.this$1;
                r23.this$0.removingParts.remove(r23);
            L5:
                this.this$1.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.CollageLayoutView2$Part$3, reason: invalid class name */
        public class AnonymousClass3 extends VideoPlayerHolderBase {
            final /* synthetic */ Part this$1;

            public static /* synthetic */ void $r8$lambda$s3R5Z6pdSoteR3IVqhymPvJ5uoE(AnonymousClass3 r0, int r1, int r2, int r3) {
                r0.lambda$onVideoSizeChanged$0(r1, r2, r3);
            }

            public AnonymousClass3(Part r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$onVideoSizeChanged$0(int r3, int r4, int r5) {
                StoryEntry r0 = Part.m19910$$Nest$fgetcontent(this.this$1);
                if (r0 != null) goto L6;
                return;
            L6:
                if (r0.width == r3) goto L8;
            L11:
                r0.width = r3;
                r0.height = r4;
                r0.orientation = r5;
                TextureView r2 = this.this$1.textureView;
                if (r2 == null) goto L16;
                r2.requestLayout();
                return;
            L16:
                return;
            L8:
                if (r0.height != r4) goto L11;
                if (r0.orientation != r5) goto L11;
            }

            @Override // org.telegram.messenger.video.VideoPlayerHolderBase
            public boolean needRepeat() {
                return !CollageLayoutView2.m19907$$Nest$fgetpreview(this.this$1.this$0);
            }

            @Override // org.telegram.messenger.video.VideoPlayerHolderBase
            public void onRenderedFirstFrame() {
                Part r1 = this.this$1;
                r1.textureViewReady = true;
                r1.this$0.invalidate();
            }

            @Override // org.telegram.messenger.video.VideoPlayerHolderBase
            public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
                AndroidUtilities.runOnUIThread(new CollageLayoutView2$Part$3$$ExternalSyntheticLambda0(this, r1, r2, r3));
            }
        }

        /* renamed from: -$$Nest$fgetcontent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StoryEntry m19910$$Nest$fgetcontent(Part r0) {
            return r0.content;
        }

        /* renamed from: -$$Nest$fgetcurrent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19911$$Nest$fgetcurrent(Part r0) {
            return r0.current;
        }

        /* renamed from: -$$Nest$fgethighlightAnimated, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedFloat m19912$$Nest$fgethighlightAnimated(Part r0) {
            return r0.highlightAnimated;
        }

        /* renamed from: -$$Nest$fgetindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19913$$Nest$fgetindex(Part r0) {
            return r0.index;
        }

        /* renamed from: -$$Nest$fgetpendingSeek, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m19914$$Nest$fgetpendingSeek(Part r2) {
            return r2.pendingSeek;
        }

        /* renamed from: -$$Nest$fputindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19915$$Nest$fputindex(Part r0, int r1) {
            r0.index = r1;
        }

        /* renamed from: -$$Nest$fputpendingSeek, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19916$$Nest$fputpendingSeek(Part r0, long r1) {
            r0.pendingSeek = r1;
        }

        public Part(CollageLayoutView2 r8) {
            this.this$0 = r8;
            this.highlightAnimated = new AnimatedFloat(r8, 0, 1200, CubicBezierInterpolator.EASE_OUT);
            this.imageReceiver = new ImageReceiver(r8);
            this.pendingSeek = -1;
            this.hasBounds = false;
            this.fromBounds = new RectF();
            this.bounds = new RectF();
            this.boundsTransition = 1.0f;
        }

        public void destroyContent() {
            VideoPlayerHolderBase r0 = this.videoPlayer;
            if (r0 == null) goto L5;
            r0.pause();
            this.videoPlayer.release(null);
            this.videoPlayer = null;
        L5:
            TextureView r02 = this.textureView;
            if (r02 == null) goto L8;
            AndroidUtilities.removeFromParent(r02);
            this.textureView = null;
        L8:
            this.textureViewReady = false;
        }

        public boolean hasContent() {
            if (this.content == null) goto L6;
            return true;
        L6:
            return false;
        }

        public void setContent(StoryEntry r9) {
            destroyContent();
            this.content = r9;
            StringBuilder r0 = new StringBuilder();
            r0.append((int) Math.ceil(AndroidUtilities.displaySize.x / AndroidUtilities.density));
            r0.append("_");
            r0.append((int) Math.ceil(AndroidUtilities.displaySize.y / AndroidUtilities.density));
            if (r9 != null) goto L5;
        L7:
            String r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            r0.append(r92);
            r0.append("_exif");
            String r3 = r0.toString();
            StoryEntry r93 = this.content;
            if (r93 != null) goto L12;
            this.imageReceiver.clearImage();
        L40:
            this.this$0.invalidate();
            return;
        L12:
            if (r93.isVideo == false) goto L39;
            Bitmap r02 = r93.blurredVideoThumb;
            if (r02 == null) goto L16;
            this.imageReceiver.setImageBitmap(r02);
        L23:
            TextureView r94 = new TextureView(this.this$0.getContext());
            this.textureView = r94;
            this.this$0.addView(r94);
            AnonymousClass3 r95 = new AnonymousClass3(this);
            this.videoPlayer = r95;
            r95.allowMultipleInstances(true);
            this.videoPlayer.with(this.textureView);
            this.videoPlayer.preparePlayer(Uri.fromFile(this.content.file), false, 1.0f);
            VideoPlayerHolderBase r96 = this.videoPlayer;
            CollageLayoutView2 r03 = this.this$0;
            if (r03.isMuted == false) goto L26;
        L31:
            float r04 = 0.0f;
        L32:
            r96.setVolume(r04);
            if (CollageLayoutView2.m19907$$Nest$fgetpreview(this.this$0) == true) goto L35;
        L38:
            this.videoPlayer.play();
            goto L40
        L35:
            if (CollageLayoutView2.m19906$$Nest$fgetplaying(this.this$0) == true) goto L38;
            this.videoPlayer.pause();
            goto L40
        L26:
            if (this.content.muted == true) goto L31;
            if (CollageLayoutView2.m19907$$Nest$fgetpreview(r03) == false) goto L31;
            r04 = this.content.videoVolume;
            goto L32
        L16:
            Bitmap r05 = r93.thumbBitmap;
            if (r05 == null) goto L19;
            this.imageReceiver.setImageBitmap(r05);
            goto L23
        L19:
            String r2 = r93.thumbPath;
            ImageReceiver r1 = this.imageReceiver;
            if (r2 == null) goto L22;
            r1.setImage(r2, r3, null, null, 0);
            goto L23
        L22:
            r1.clearImage();
            goto L23
        L39:
            this.imageReceiver.setImage(r93.file.getAbsolutePath(), r3, null, null, 0);
            goto L40
        L5:
            if (r9.isVideo == false) goto L7;
            r92 = "_g";
            goto L8
        }

        public void setCurrent(boolean r1) {
            this.current = r1;
        }

        public void setPart(CollageLayout.Part r4, boolean r5) {
            CollageLayout.Part r0 = this.part;
            if (r4 == null) goto L5;
            this.part = r4;
        L5:
            ValueAnimator r1 = this.animator;
            if (r1 == null) goto L8;
            r1.cancel();
            this.animator = null;
        L8:
            if (r5 == true) goto L10;
            CollageLayoutView2.m19908$$Nest$mlayout(this.this$0, this.bounds, r4);
            this.boundsTransition = 1.0f;
            if (r4 != null) goto L21;
            this.imageReceiver.onDetachedFromWindow();
            destroyContent();
            this.this$0.removingParts.remove(this);
        L21:
            this.this$0.invalidate();
            this.hasBounds = true;
            return;
        L10:
            if (this.hasBounds == true) goto L12;
            CollageLayoutView2.m19909$$Nest$mlayoutOut(this.this$0, this.fromBounds, r4);
        L13:
            CollageLayoutView2 r52 = this.this$0;
            if (r4 != null) goto L16;
            CollageLayoutView2.m19909$$Nest$mlayoutOut(r52, this.bounds, r0);
        L17:
            this.boundsTransition = 0.0f;
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.animator = r42;
            r42.addUpdateListener(new AnonymousClass1(this));
            this.animator.addListener(new AnonymousClass2(this));
            this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.animator.setDuration(360);
            this.animator.start();
            goto L21
        L16:
            CollageLayoutView2.m19908$$Nest$mlayout(r52, this.bounds, r4);
            goto L17
        L12:
            RectF r53 = this.fromBounds;
            AndroidUtilities.lerp(r53, this.bounds, this.boundsTransition, r53);
            goto L13
        }
    }

    public static /* synthetic */ void $r8$lambda$5jQfkr5aX0krmtw6oPrGzMHdxIU(CollageLayoutView2 r0, Float r1) {
        r0.lambda$onLongPress$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$BlCGMwGoirt0UuyA9LBLKQe06xM() {
    }

    public static /* synthetic */ void $r8$lambda$J_joLDZS_IT_gQqQf7qq9qwoCvE(CollageLayoutView2 r0) {
        r0.lambda$onLongPress$5();
    }

    public static /* synthetic */ void $r8$lambda$MVfPbVJ3baa9K6qL8ygN5rrg5tQ(CollageLayoutView2 r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$csgg4pnxAlyzBsH4OoTF1vnC8TI(CollageLayoutView2 r0) {
        r0.onLongPress();
    }

    public static /* synthetic */ void $r8$lambda$gEzJkh9Q7nHYp9shOEn3dO_DL9g(CollageLayoutView2 r0) {
        r0.lambda$onLongPress$2();
    }

    public static /* synthetic */ void $r8$lambda$gyrTHl3VvbNgnBvzKgKgE5Hkh1w(Part r2) {
        Part.m19916$$Nest$fputpendingSeek(r2, -1);
    }

    public static /* synthetic */ void $r8$lambda$qlZozd5VyDaZr8m4VHGMGp0TVyo(CollageLayoutView2 r0) {
        r0.lambda$onLongPress$3();
    }

    public static /* synthetic */ void $r8$lambda$uE0b4evOH_w4mh4hSCvGdBAIBV4(CollageLayoutView2 r0) {
        r0.lambda$new$7();
    }

    /* renamed from: -$$Nest$fgetplaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19906$$Nest$fgetplaying(CollageLayoutView2 r0) {
        return r0.playing;
    }

    /* renamed from: -$$Nest$fgetpreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19907$$Nest$fgetpreview(CollageLayoutView2 r0) {
        return r0.preview;
    }

    /* renamed from: -$$Nest$mlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19908$$Nest$mlayout(CollageLayoutView2 r0, RectF r1, CollageLayout.Part r2) {
        r0.layout(r1, r2);
    }

    /* renamed from: -$$Nest$mlayoutOut, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19909$$Nest$mlayoutOut(CollageLayoutView2 r0, RectF r1, CollageLayout.Part r2) {
        r0.layoutOut(r1, r2);
    }

    public CollageLayoutView2(Context r18, BlurringShader.BlurManager r19, FrameLayout r20, Theme.ResourcesProvider r21) {
        super(r18);
        this.qrDrawer = new QRScanner.QrRegionDrawer(new CollageLayoutView2$$ExternalSyntheticLambda1(this));
        this.currentLayout = new CollageLayout(".");
        ArrayList<Part> r7 = new ArrayList();
        this.parts = r7;
        this.removingParts = new ArrayList();
        Paint r8 = new Paint(1);
        this.highlightPaint = r8;
        this.highlightPath = new Path();
        this.radii = new float[8];
        this.resetReordering = new CollageLayoutView2$$ExternalSyntheticLambda2(this);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatedRows = new AnimatedFloat(this, 0, 320, r6);
        this.animatedColumns = new AnimatedFloat[]{new AnimatedFloat(this, 0, 320, r6), new AnimatedFloat(this, 0, 320, r6), new AnimatedFloat(this, 0, 320, r6), new AnimatedFloat(this, 0, 320, r6), new AnimatedFloat(this, 0, 320, r6)};
        this.animatedReordering = new AnimatedFloat(this, 0, 320, r6);
        this.lefts = new float[5];
        this.rights = new float[5];
        this.rect = new RectF();
        this.clipPath = new Path();
        this.cameraThumbVisible = true;
        this.playing = true;
        this.restorePositionOnPlaying = true;
        this.syncRunnable = new CollageLayoutView2$$ExternalSyntheticLambda3(this);
        this.blurManager = r19;
        this.containerView = r20;
        this.resourcesProvider = r21;
        setBackgroundColor(-14737633);
        Part r0 = new Part(this);
        r0.setPart(this.currentLayout.parts.get(0), false);
        r0.setCurrent(true);
        if (this.attached == false) goto L5;
        r0.imageReceiver.onAttachedToWindow();
    L5:
        r7.add(r0);
        this.currentPart = r0;
        this.nextPart = null;
        r8.setStyle(Paint.Style.STROKE);
        r8.setColor(-1);
        r8.setStrokeWidth(AndroidUtilities.dp(8.0f));
        int r2 = AndroidUtilities.dp(300.0f);
        this.gradientWidth = r2;
        LinearGradient r9 = new LinearGradient(0.0f, 0.0f, r2, 0.0f, new int[]{0, -1, -1, 0}, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
        this.gradient = r9;
        this.gradientMatrix = new Matrix();
        r8.setShader(r9);
        setWillNotDraw(false);
    }

    private void drawDrawable(Canvas r7, Drawable r8, RectF r9, float r10) {
        if (r8 != null) goto L4;
        return;
    L4:
        int r6 = r8.getIntrinsicWidth();
        int r0 = r8.getIntrinsicHeight();
        float r1 = Math.max(r9.width() / r6, r9.height() / r0);
        r7.save();
        r7.translate(r9.centerX(), r9.centerY());
        r7.clipRect((-r9.width()) / 2.0f, (-r9.height()) / 2.0f, r9.width() / 2.0f, r9.height() / 2.0f);
        r7.scale(r1, r1);
        r7.translate((-r6) / 2.0f, (-r0) / 2.0f);
        r8.setBounds(0, 0, r6, r0);
        r8.draw(r7);
        if (r10 <= 0.0f) goto L7;
        r7.drawColor(Theme.multAlpha(-16777216, r8.getAlpha() * r10));
    L7:
        r7.restore();
    }

    private void drawPart(Canvas r8, RectF r9, Part r10) {
        if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L5;
    L7:
        float r2 = 0.0f;
        if (r10 == this.reorderingPart) goto L10;
    L12:
        boolean r0 = false;
    L13:
        if (r10 == null) goto L30;
        if (Part.m19910$$Nest$fgetcontent(r10) == null) goto L30;
        TextureView r1 = r10.textureView;
        if (r1 != null) goto L19;
    L21:
        r10.imageReceiver.setImageCoords(r9.left, r9.top, r9.width(), r9.height());
        if (r10.imageReceiver.draw(r8) == true) goto L67;
        CameraView r102 = this.cameraView;
        if (r102 == null) goto L26;
    L28:
        drawView(r8, r102, r9, 0.0f);
        goto L67
    L26:
        if (this.cameraThumbVisible == false) goto L28;
        drawDrawable(r8, this.cameraThumbDrawable, r9, 0.0f);
    L67:
        if (r0 == false) goto L70;
        r8.restore();
        return;
    L70:
        return;
    L19:
        if (r10.textureViewReady == false) goto L21;
        drawView(r8, r1, r9, 0.0f);
    L30:
        if (r10 == null) goto L34;
        if (Part.m19911$$Nest$fgetcurrent(r10) == false) goto L34;
    L35:
        CameraView r12 = this.cameraView;
        if (r12 == null) goto L38;
    L45:
        if (r10 != null) goto L47;
    L48:
        r2 = 0.4f;
    L49:
        drawView(r8, r12, r9, r2);
        goto L67
    L47:
        if (Part.m19911$$Nest$fgetcurrent(r10) == true) goto L49;
    L38:
        if (this.cameraThumbVisible == false) goto L45;
        Drawable r13 = this.cameraThumbDrawable;
        if (r10 != null) goto L42;
    L43:
        r2 = 0.4f;
    L44:
        drawDrawable(r8, r13, r9, r2);
        goto L67
    L42:
        if (Part.m19911$$Nest$fgetcurrent(r10) == true) goto L44;
    L34:
        if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L35;
        setCameraNeedsBlur(!this.preview);
        if (this.cameraViewBlurRenderNode != null) goto L53;
    L57:
        drawView(r8, this.cameraView, r9, 0.75f);
    L58:
        CameraView r103 = this.cameraView;
        if (r103 == null) goto L67;
        ImageView r104 = r103.blurredStubView;
        if (r104 == null) goto L67;
        if (r104.getVisibility() != 0) goto L67;
        if (this.cameraView.blurredStubView.getAlpha() <= 0.0f) goto L67;
        drawView(r8, this.cameraView.blurredStubView, r9, 0.4f);
        goto L67
    L53:
        if (Build.VERSION.SDK_INT < 29) goto L57;
        if (r8.isHardwareAccelerated() == false) goto L57;
        RenderNode r105 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(this.cameraViewBlurRenderNode);
        float r14 = Math.max(r9.width() / r105.getWidth(), r9.height() / r105.getHeight());
        r8.save();
        r8.translate(r9.left, r9.top);
        r8.clipRect(0.0f, 0.0f, r9.width(), r9.height());
        r8.scale(r14, r14);
        r8.drawRenderNode(r105);
        r8.drawColor(1677721600);
        r8.restore();
        goto L58
    L10:
        if (this.animatedReordering.get() <= 0.0f) goto L12;
        r8.save();
        this.clipPath.rewind();
        RectF r02 = AndroidUtilities.rectTmp;
        r02.set(r9);
        r02.inset(AndroidUtilities.dp(10.0f) * this.animatedReordering.get(), AndroidUtilities.dp(10.0f) * this.animatedReordering.get());
        float r3 = AndroidUtilities.dp(12.0f) * this.animatedReordering.get();
        this.clipPath.addRoundRect(r02, r3, r3, Path.Direction.CW);
        r8.clipPath(this.clipPath);
        r0 = true;
        goto L13
    L5:
        if (r10 != this.longPressedPart) goto L7;
    }

    private void drawView(Canvas r7, View r8, RectF r9, float r10) {
        if (r8 == null) goto L29;
        float r0 = Math.max(r9.width() / r8.getWidth(), r9.height() / r8.getHeight());
        r7.save();
        r7.translate(r9.centerX(), r9.centerY());
        r7.clipRect((-r9.width()) / 2.0f, (-r9.height()) / 2.0f, r9.width() / 2.0f, r9.height() / 2.0f);
        r7.scale(r0, r0);
        r7.translate((-r8.getWidth()) / 2.0f, (-r8.getHeight()) / 2.0f);
        if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L7;
        r8.draw(r7);
    L19:
        if (r10 <= 0.0f) goto L21;
        r7.drawColor(Theme.multAlpha(-16777216, r8.getAlpha() * r10));
    L21:
        r7.restore();
        if (r8 != this.cameraView) goto L27;
        QRScanner.QrRegionDrawer r6 = this.qrDrawer;
        if (r6 == null) goto L28;
        r6.draw(r7, r9);
        return;
    L28:
        return;
    L27:
        return;
    L7:
        if ((r8 instanceof TextureView) == false) goto L10;
        TextureView r02 = (TextureView) r8;
    L13:
        if (r02 == null) goto L19;
        Bitmap r03 = r02.getBitmap();
        if (r03 == null) goto L19;
        r7.scale(r8.getWidth() / r03.getWidth(), r8.getHeight() / r03.getHeight());
        r7.drawBitmap(r03, 0.0f, 0.0f, null);
        goto L19
    L10:
        if ((r8 instanceof CameraView) == false) goto L12;
        r02 = ((CameraView) r8).getTextureView();
        goto L13
    L12:
        r02 = null;
        goto L13
    }

    private void finishNode(Canvas r4) {
        if (this.renderNode != null) goto L5;
        return;
    L5:
        if (Build.VERSION.SDK_INT >= 29) goto L7;
        return;
    L7:
        if (r4.isHardwareAccelerated() == false) goto L14;
        RenderNode r0 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(this.renderNode);
        r0.endRecording();
        r4.drawRenderNode(r0);
        Object r42 = this.blurRenderNode;
        if (r42 == null) goto L15;
        RenderNode r43 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r42);
        r43.setPosition(0, 0, getWidth(), getHeight());
        r43.beginRecording().drawRenderNode(r0);
        r43.endRecording();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.reordering == false) goto L6;
        this.reordering = false;
        invalidate();
        return;
    }

    private /* synthetic */ void lambda$new$7() {
        long r1 = getPosition();
        Part r3 = getMainPart();
        if (r3 != null) goto L5;
        long r5 = 0;
    L6:
        int r7 = 0;
    L7:
        boolean r9 = true;
        if (r7 >= this.parts.size()) goto L49;
        Part r8 = this.parts.get(r7);
        if (Part.m19910$$Nest$fgetcontent(r8) == null) goto L48;
        VideoPlayerHolderBase r10 = r8.videoPlayer;
        if (r10 == null) goto L48;
        long r13 = r10.getDuration();
        long r32 = Utilities.clamp((r1 + r5) - Part.m19910$$Nest$fgetcontent(r8).videoOffset, r13, 0);
        if (this.preview == true) goto L16;
    L17:
        float r102 = r32;
        float r12 = r13;
        if (r102 > (Part.m19910$$Nest$fgetcontent(r8).videoLeft * r12)) goto L20;
    L22:
        r9 = false;
    L23:
        float r11 = r13;
        long r33 = Utilities.clamp(r32, (long) (Part.m19910$$Nest$fgetcontent(r8).videoRight * r11), (long) (Part.m19910$$Nest$fgetcontent(r8).videoLeft * r11));
        if (r8.videoPlayer.isPlaying() == r9) goto L29;
        VideoPlayerHolderBase r103 = r8.videoPlayer;
        if (r9 == false) goto L28;
        r103.play();
        goto L29
    L28:
        r103.pause();
    L29:
        VideoPlayerHolderBase r92 = r8.videoPlayer;
        if (this.isMuted == false) goto L32;
    L37:
        float r104 = 0.0f;
    L38:
        r92.setVolume(r104);
        if (Part.m19914$$Nest$fgetpendingSeek(r8) < 0) goto L41;
        long r93 = Part.m19914$$Nest$fgetpendingSeek(r8);
    L43:
        if (Math.abs(r93 - r33) <= 450) goto L48;
        if (Part.m19914$$Nest$fgetpendingSeek(r8) >= 0) goto L48;
        VideoPlayerHolderBase r94 = r8.videoPlayer;
        Part.m19916$$Nest$fputpendingSeek(r8, r33);
        r94.seekTo(r33, this.fastSeek, new CollageLayoutView2$$ExternalSyntheticLambda4(r8));
        goto L48
    L41:
        r93 = r8.videoPlayer.getCurrentPosition();
        goto L43
    L32:
        if (Part.m19910$$Nest$fgetcontent(r8).muted == true) goto L37;
        if (this.preview == false) goto L37;
        r104 = Part.m19910$$Nest$fgetcontent(r8).videoVolume;
        goto L38
    L20:
        if (r102 >= (Part.m19910$$Nest$fgetcontent(r8).videoRight * r12)) goto L22;
    L16:
        if (this.playing == false) goto L22;
    L48:
        r7 = r7 + 1;
        goto L7
    L49:
        TimelineView r34 = this.timelineView;
        if (r34 == null) goto L52;
        r34.setProgress(r1);
    L52:
        PreviewView r14 = this.previewView;
        if (r14 == null) goto L56;
        r14.updateAudioPlayer(true);
        this.previewView.updateRoundPlayer(true);
    L56:
        if (this.preview == true) goto L58;
        return;
    L58:
        if (this.playing == false) goto L67;
        AndroidUtilities.runOnUIThread(this.syncRunnable, (long) (1000.0f / AndroidUtilities.screenRefreshRate));
        return;
    L67:
        return;
    L5:
        r5 = Part.m19910$$Nest$fgetcontent(r3).videoOffset + ((long) (Part.m19910$$Nest$fgetcontent(r3).videoLeft * Part.m19910$$Nest$fgetcontent(r3).duration));
        goto L6
    }

    private /* synthetic */ void lambda$onLongPress$1(Float r2) {
        StoryEntry r0 = Part.m19910$$Nest$fgetcontent(this.longPressedPart);
        r0.videoVolume = r2.floatValue();
        Part r1 = this.longPressedPart;
        VideoPlayerHolderBase r22 = r1.videoPlayer;
        if (r22 == null) goto L6;
        r22.setVolume(Part.m19910$$Nest$fgetcontent(r1).videoVolume);
        return;
    }

    private /* synthetic */ void lambda$onLongPress$2() {
        retake(this.longPressedPart);
    }

    private /* synthetic */ void lambda$onLongPress$3() {
        delete(this.longPressedPart);
    }

    private /* synthetic */ void lambda$onLongPress$5() {
        Part r1 = this.longPressedPart;
        if (r1 == null) goto L8;
        VideoPlayerHolderBase r12 = r1.videoPlayer;
        if (r12 == null) goto L9;
        r12.setVolume(0.0f);
        return;
    L9:
        return;
    }

    private void layout(RectF r8, CollageLayout.Part r9) {
        int r0 = getMeasuredWidth();
        int r7 = getMeasuredHeight();
        if (r0 <= 0) goto L5;
        if (r7 <= 0) goto L5;
    L6:
        float r02 = r0;
        CollageLayout r1 = r9.layout;
        int[] r2 = r1.columns;
        int r3 = r9.y;
        int r22 = r2[r3];
        int r92 = r9.x;
        float r72 = r7;
        int r12 = r1.h;
        r8.set((r02 / r22) * r92, (r72 / r12) * r3, (r02 / r22) * (r92 + 1), (r72 / r12) * (r3 + 1));
        return;
    L5:
        Point r73 = AndroidUtilities.displaySize;
        r0 = r73.x;
        r7 = r73.y;
        goto L6
    }

    private void layoutOut(RectF r11, CollageLayout.Part r12) {
        int r0 = getMeasuredWidth();
        int r1 = getMeasuredHeight();
        if (r0 <= 0) goto L5;
        if (r1 <= 0) goto L5;
    L6:
        layout(r11, r12);
        float r10 = r11.left;
        boolean r3 = false;
        if (r10 > 0.0f) goto L9;
        boolean r2 = true;
    L10:
        float r5 = r11.top;
        if (r5 > 0.0f) goto L13;
        boolean r6 = true;
    L14:
        float r02 = r0;
        if (r11.right < r02) goto L17;
        boolean r7 = true;
    L18:
        float r13 = r1;
        if (r11.bottom < r13) goto L21;
        r3 = true;
    L21:
        if (r2 == false) goto L27;
        if (r7 == false) goto L27;
        if (r6 == true) goto L27;
        if (r3 == true) goto L27;
        r11.offset(0.0f, r13 - r5);
        return;
    L27:
        if (r6 == false) goto L33;
        if (r3 == false) goto L33;
        if (r2 == true) goto L33;
        if (r7 == true) goto L33;
        r11.offset(0.0f, r02 - r10);
        return;
    L33:
        if (r7 == false) goto L36;
        if (r2 == true) goto L36;
        r11.offset(r11.width(), 0.0f);
    L36:
        if (r3 == false) goto L40;
        if (r6 == true) goto L41;
        r11.offset(0.0f, r11.height());
        return;
    L41:
        return;
    L40:
        return;
    L17:
        r7 = false;
        goto L18
    L13:
        r6 = false;
        goto L14
    L9:
        r2 = false;
    L5:
        Point r03 = AndroidUtilities.displaySize;
        int r14 = r03.x;
        r1 = r03.y;
        r0 = r14;
        goto L6
    }

    private void onLongPress() {
        if (this.reorderingTouch == false) goto L5;
        return;
    L5:
        if (this.preview == true) goto L34;
        Part r0 = this.longPressedPart;
        if (r0 == null) goto L12;
        VideoPlayerHolderBase r02 = r0.videoPlayer;
        if (r02 == null) goto L12;
        r02.setVolume(0.0f);
    L12:
        Part r03 = this.pressedPart;
        this.longPressedPart = r03;
        if (r03 != null) goto L15;
        return;
    L15:
        if (Part.m19910$$Nest$fgetcontent(r03) == null) goto L35;
        Runnable r04 = this.cancelGestures;
        if (r04 == null) goto L20;
        r04.run();
    L20:
        Part r05 = this.longPressedPart;
        VideoPlayerHolderBase r2 = r05.videoPlayer;
        if (r2 == null) goto L23;
        r2.setVolume(Part.m19910$$Nest$fgetcontent(r05).videoVolume);
    L23:
        FrameLayout r06 = new FrameLayout(getContext());
        ImageView r22 = new ImageView(getContext());
        r22.setImageResource(R.drawable.menu_lightbulb);
        r22.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r22.setScaleType(ImageView.ScaleType.CENTER);
        r06.addView(r22, LayoutHelper.createFrame(24, 24.0f, 19, 12.0f, 12.0f, 12.0f, 12.0f));
        TextView r23 = new TextView(getContext());
        r23.setText(LocaleController.getString(R.string.StoryCollageMenuHint));
        r23.setTextSize(1, 13.0f);
        r23.setTextColor(-1);
        r06.addView(r23, LayoutHelper.createFrame(-1, -2.0f, 23, 47.0f, 8.0f, 24.0f, 8.0f));
        ItemOptions r24 = ItemOptions.makeOptions(this.containerView, this.resourcesProvider, this);
        if (Part.m19910$$Nest$fgetcontent(this.longPressedPart).isVideo == false) goto L26;
        SliderView r1 = new SliderView(getContext(), 0).setMinMax(0.0f, 1.5f).setValue(Part.m19910$$Nest$fgetcontent(this.longPressedPart).videoVolume).setOnValueChange(new CollageLayoutView2$$ExternalSyntheticLambda5(this));
        r1.fixWidth = AndroidUtilities.dp(220.0f);
        r24.addView(r1).addSpaceGap();
    L26:
        r24.setFixedWidth(Opcodes.REM_INT_LIT8).add(R.drawable.menu_camera_retake, LocaleController.getString(R.string.StoreCollageRetake), new CollageLayoutView2$$ExternalSyntheticLambda6(this)).add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new CollageLayoutView2$$ExternalSyntheticLambda7(this)).addSpaceGap().addView(r06, LayoutHelper.createLinear(Opcodes.REM_INT_LIT8, -2)).setOnDismiss(new CollageLayoutView2$$ExternalSyntheticLambda8()).setGravity(1).allowCenter(true).setBlur(true, false).setRoundRadius(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(10.0f)).setOnDismiss(new CollageLayoutView2$$ExternalSyntheticLambda9(this)).show();
        performHapticFeedback(0, 1);     // Catch: Exception -> L29
        return;
    L36:
        return;
    L35:
        return;
    }

    public boolean cancelTouch() {
        if (this.pressedPart == null) goto L9;
        this.pressedPart = null;
        this.reorderingTouch = false;
        invalidate();
        Runnable r1 = this.onLongPressPart;
        if (r1 == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.onLongPressPart = null;
        return true;
    L10:
        return true;
    L9:
        return false;
    }

    public void clear(boolean r5) {
        ArrayList<Part> r52 = this.parts;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Part r2 = r52.get(r1);
        r1 = r1 + 1;
        r2.setContent(null);
        goto L3
    L5:
        updatePartsState();
    }

    public void delete(Part r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (this.parts.indexOf(r3) >= 0) goto L7;
        return;
    L7:
        CollageLayout r0 = this.currentLayout;
        CollageLayout r32 = r0.delete(r0.parts.indexOf(r3.part));
        if (r32.parts.size() > 1) goto L10;
        clear(true);
        invalidate();
    L10:
        setLayout(r32, true);
        this.reordering = true;
        updatePartsState();
        invalidate();
        Runnable r02 = this.onResetState;
        if (r02 == null) goto L13;
        r02.run();
    L13:
        onLayoutUpdate(r32);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r22) {
        int r2 = 0;
        if (this.renderNode != null) goto L5;
    L9:
        Canvas r1 = r22;
    L10:
        super.dispatchDraw(r1);
        if (hasLayout() == true) goto L25;
        if (this.reordering == true) goto L25;
        if (this.reorderingTouch == true) goto L25;
        if (this.animatedRows.get() != this.currentLayout.h) goto L25;
        if (this.animatedColumns[0].get() != this.currentLayout.columns[0]) goto L25;
        if (this.qrDrawer.hasNoDraw() == false) goto L25;
        setCameraNeedsBlur(false);
        finishNode(r22);
        return;
    L25:
        if (this.preview == false) goto L27;
        setCameraNeedsBlur(false);
    L27:
        r1.drawColor(-14737633);
        float r3 = this.animatedReordering.set(this.reorderingTouch);
        float r4 = this.animatedRows.set(this.currentLayout.h);
        int r5 = 0;
    L28:
        double r8 = r4;
        float r7 = 0.0f;
        if (r5 >= Math.ceil(r8)) goto L31;
        this.lefts[r5] = getMeasuredWidth();
        this.rights[r5] = 0.0f;
        r5 = r5 + 1;
        goto L28
    L31:
        int r52 = this.currentLayout.h;
    L32:
        AnimatedFloat[] r6 = this.animatedColumns;
        float r11 = 1.0f;
        if (r52 >= r6.length) goto L35;
        r6[r52].set(1.0f);
        r52 = r52 + 1;
        goto L32
    L35:
        int r53 = 0;
        boolean r10 = false;
        float r62 = 0.0f;
    L37:
        if (r53 >= this.parts.size()) goto L57;
        Part r12 = this.parts.get(r53);
        CollageLayout.Part r14 = r12.part;
        int r16 = r2;
        float r17 = r11;
        float r23 = this.animatedColumns[r14.y].set(r14.layout.columns[r2]);
        if (this.reordering == false) goto L41;
    L42:
        float r19 = r7;
        boolean r18 = true;
        AndroidUtilities.lerp(r12.fromBounds, r12.bounds, r12.boundsTransition, this.rect);
    L45:
        float[] r24 = this.lefts;
        int r72 = r14.y;
        r24[r72] = Math.min(r24[r72], this.rect.left);
        float[] r25 = this.rights;
        int r73 = r14.y;
        r25[r73] = Math.max(r25[r73], this.rect.right);
        r62 = Math.max(r62, this.rect.bottom);
        if (r3 <= r19) goto L51;
        if (r12 != this.reorderingPart) goto L51;
    L56:
        r53 = r53 + 1;
        r2 = r16;
        r11 = r17;
        r7 = r19;
    L51:
        if (this.preview == true) goto L53;
    L55:
        drawPart(r1, this.rect, r12);
        goto L56
    L53:
        if (r12.videoPlayer == null) goto L55;
        r10 = r18;
        goto L55
    L41:
        if (this.reorderingTouch == true) goto L42;
        r18 = true;
        r19 = r7;
        this.rect.set((getMeasuredWidth() / r23) * r14.x, (getMeasuredHeight() / r4) * r14.y, (getMeasuredWidth() / r23) * (r14.x + 1), (r14.y + 1) * (getMeasuredHeight() / r4));
        goto L45
    L57:
        int r162 = r2;
        float r192 = r7;
        float r172 = r11;
    L59:
        if (r2 >= this.removingParts.size()) goto L71;
        Part r54 = this.removingParts.get(r2);
        CollageLayout.Part r74 = r54.part;
        AnimatedFloat[] r112 = this.animatedColumns;
        int r122 = r74.y;
        AnimatedFloat r113 = r112[r122];
        int[] r13 = this.currentLayout.columns;
        if (r122 < r13.length) goto L63;
        float r123 = r172;
    L64:
        float r114 = r113.set(r123);
        int r20 = r2;
        this.rect.set((getMeasuredWidth() / r114) * r74.x, (getMeasuredHeight() / r4) * r74.y, (getMeasuredWidth() / r114) * (r74.x + 1), (getMeasuredHeight() / r4) * (r74.y + 1));
        float[] r26 = this.lefts;
        int r115 = r74.y;
        r26[r115] = Math.min(r26[r115], this.rect.left);
        float[] r27 = this.rights;
        int r75 = r74.y;
        r27[r75] = Math.max(r27[r75], this.rect.right);
        r62 = Math.max(r62, this.rect.bottom);
        if (this.preview == false) goto L69;
        if (r54.videoPlayer == null) goto L69;
        r10 = true;
    L69:
        drawPart(r1, this.rect, r54);
        r2 = r20 + 1;
        goto L59
    L63:
        r123 = r13[r122];
        goto L64
    L71:
        if (this.reorderingTouch == true) goto L86;
        int r28 = r162;
    L74:
        if (r28 >= Math.ceil(r8)) goto L83;
        if (this.lefts[r28] < r192) goto L79;
        this.rect.set(r192, (getMeasuredHeight() / r4) * r28, this.lefts[r28], (getMeasuredHeight() / r4) * (r28 + 1));
        drawPart(r1, this.rect, null);
    L79:
        if (this.rights[r28] >= getMeasuredWidth()) goto L81;
        this.rect.set(this.rights[r28], (getMeasuredHeight() / r4) * r28, getMeasuredWidth(), (getMeasuredHeight() / r4) * (r28 + 1));
        drawPart(r1, this.rect, null);
    L81:
        r28 = r28 + 1;
        r192 = 0.0f;
        goto L74
    L83:
        if (r62 >= getMeasuredHeight()) goto L85;
        float r142 = 0.0f;
        this.rect.set(0.0f, r62, getMeasuredWidth(), getMeasuredHeight());
        drawPart(r1, this.rect, null);
    L88:
        if (r3 <= r142) goto L96;
        Part r29 = this.reorderingPart;
        if (r29 == null) goto L96;
        CollageLayout.Part r55 = r29.part;
        float r63 = this.animatedColumns[r55.y].set(this.currentLayout.columns[r7]);
        if (this.reorderingTouch == false) goto L94;
        AndroidUtilities.lerp(r29.fromBounds, r29.bounds, r29.boundsTransition, this.rect);
    L95:
        r1.save();
        r1.translate(AndroidUtilities.lerp(this.ldx, this.dx, r29.boundsTransition) * r3, AndroidUtilities.lerp(this.ldy, this.dy, r29.boundsTransition) * r3);
        drawPart(r1, this.rect, r29);
        r1.restore();
        goto L96
    L94:
        this.rect.set((getMeasuredWidth() / r63) * r55.x, (getMeasuredHeight() / r4) * r55.y, (getMeasuredWidth() / r63) * (r55.x + 1), (getMeasuredHeight() / r4) * (r55.y + 1));
    L96:
        int r210 = r162;
    L98:
        if (r210 >= this.parts.size()) goto L135;
        Part r32 = this.parts.get(r210);
        CollageLayout.Part r56 = r32.part;
        float r64 = Part.m19912$$Nest$fgethighlightAnimated(r32).set(0.0f);
        if (r64 <= 0.0f) goto L134;
        float r76 = this.animatedColumns[r56.y].set(r56.layout.columns[r8]);
        if (this.reordering == false) goto L105;
    L108:
        AndroidUtilities.lerp(r32.fromBounds, r32.bounds, r32.boundsTransition, this.rect);
    L109:
        RectF r57 = AndroidUtilities.rectTmp;
        r57.set(this.rect);
        r57.inset(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        this.gradientMatrix.reset();
        Matrix r77 = this.gradientMatrix;
        float r82 = this.rect.left;
        int r9 = this.gradientWidth;
        r77.postTranslate(r82 + AndroidUtilities.lerp(((float) Math.sqrt((r9 * r9) + (r9 * r9))) * (-1.4f), (float) Math.sqrt((this.rect.width() * this.rect.width()) + (this.rect.height() * this.rect.height())), r172 - r64), 0.0f);
        this.gradientMatrix.postRotate(-25.0f);
        this.gradient.setLocalMatrix(this.gradientMatrix);
        this.highlightPaint.setAlpha(255);
        this.highlightPath.rewind();
        float[] r65 = this.radii;
        CollageLayout.Part r78 = r32.part;
        if (r78.x == 0) goto L112;
    L114:
        float r79 = 0.0f;
    L115:
        r65[1] = r79;
        r65[r162] = r79;
        float[] r66 = this.radii;
        CollageLayout.Part r710 = r32.part;
        if (r710.x == (r710.layout.w - 1)) goto L118;
    L120:
        float r711 = 0.0f;
    L121:
        r66[2] = r711;
        r66[1] = r711;
        float[] r67 = this.radii;
        CollageLayout.Part r712 = r32.part;
        int r83 = r712.x;
        CollageLayout r116 = r712.layout;
        if (r83 == (r116.w - 1)) goto L124;
    L126:
        float r713 = 0.0f;
    L127:
        r67[4] = r713;
        r67[3] = r713;
        float[] r68 = this.radii;
        CollageLayout.Part r33 = r32.part;
        if (r33.x == 0) goto L130;
    L132:
        float r34 = 0.0f;
    L133:
        r68[6] = r34;
        r68[5] = r34;
        this.highlightPath.addRoundRect(r57, this.radii, Path.Direction.CW);
        r1.drawPath(this.highlightPath, this.highlightPaint);
        goto L134
    L130:
        if (r33.y != (r33.layout.h - 1)) goto L132;
        r34 = AndroidUtilities.dp(8.0f);
        goto L133
    L124:
        if (r712.y != (r116.h - 1)) goto L126;
        r713 = AndroidUtilities.dp(8.0f);
        goto L127
    L118:
        if (r710.y != 0) goto L120;
        r711 = AndroidUtilities.dp(8.0f);
        goto L121
    L112:
        if (r78.y != 0) goto L114;
        r79 = AndroidUtilities.dp(8.0f);
        goto L115
    L105:
        if (this.reorderingTouch == true) goto L108;
        this.rect.set((getMeasuredWidth() / r76) * r56.x, (getMeasuredHeight() / r4) * r56.y, (getMeasuredWidth() / r76) * (r56.x + 1), (getMeasuredHeight() / r4) * (r56.y + 1));
    L134:
        r210 = r210 + 1;
        goto L98
    L135:
        if (r10 == false) goto L139;
        BlurringShader.BlurManager r15 = this.blurManager;
        if (r15 == null) goto L139;
        r15.invalidate();
    L139:
        finishNode(r22);
        return;
    L85:
        r142 = 0.0f;
        goto L88
    L86:
        r142 = r192;
        goto L88
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L9;
        if (r22.isHardwareAccelerated() == false) goto L9;
        RenderNode r110 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(this.renderNode);
        r110.setPosition(0, 0, getWidth(), getHeight());
        r1 = r110.beginRecording();
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r11) {
        if (hasLayout() == true) goto L5;
    L71:
        cancelTouch();
        return false;
    L5:
        if (this.preview == true) goto L71;
        if (r11.getPointerCount() <= 1) goto L11;
        cancelTouch();
        return false;
    L11:
        Part r0 = getPartAt(r11.getX(), r11.getY());
        if (r11.getAction() != 0) goto L17;
        this.tx = r11.getX();
        this.ty = r11.getY();
        this.reorderingTouch = false;
        this.dx = 0.0f;
        this.ldx = 0.0f;
        this.dy = 0.0f;
        this.ldy = 0.0f;
        this.pressedPart = r0;
        if (r0 == null) goto L65;
        CollageLayoutView2$$ExternalSyntheticLambda0 r02 = new CollageLayoutView2$$ExternalSyntheticLambda0(this);
        this.onLongPressPart = r02;
        AndroidUtilities.runOnUIThread(r02, ViewConfiguration.getLongPressTimeout());
    L65:
        if (this.pressedPart == null) goto L67;
    L70:
        return true;
    L67:
        if (super.dispatchTouchEvent(r11) == true) goto L70;
        return false;
    L17:
        if (r11.getAction() != 2) goto L52;
        if (MathUtils.distance(r11.getX(), r11.getY(), this.tx, this.ty) <= (AndroidUtilities.touchSlop * 1.2f)) goto L24;
        Runnable r3 = this.onLongPressPart;
        if (r3 == null) goto L24;
        AndroidUtilities.cancelRunOnUIThread(r3);
        this.onLongPressPart = null;
    L24:
        if (this.reorderingTouch == true) goto L36;
        if (getFilledProgress() < 1.0f) goto L36;
        if (this.pressedPart == null) goto L36;
        if (r0 == null) goto L36;
        if (MathUtils.distance(r11.getX(), r11.getY(), this.tx, this.ty) <= (AndroidUtilities.touchSlop * 1.2f)) goto L36;
        this.reorderingTouch = true;
        this.reorderingPart = this.pressedPart;
        this.dx = 0.0f;
        this.ldx = 0.0f;
        this.dy = 0.0f;
        this.ldy = 0.0f;
        invalidate();
        Runnable r03 = this.onLongPressPart;
        if (r03 == null) goto L65;
        AndroidUtilities.cancelRunOnUIThread(r03);
        this.onLongPressPart = null;
    L36:
        if (this.reorderingTouch == false) goto L46;
        if (this.reorderingPart == null) goto L46;
        int r04 = getPartIndexAt(r11.getX(), r11.getY());
        int r32 = this.parts.indexOf(this.reorderingPart);
        if (r04 < 0) goto L44;
        if (r32 < 0) goto L44;
        if (r04 == r32) goto L44;
        swap(r32, r04);
        float r05 = this.currentLayout.h;
        float r4 = this.animatedColumns[this.reorderingPart.part.y].get();
        this.rect.set((getMeasuredWidth() / r4) * r3.x, (getMeasuredHeight() / r05) * r3.y, (getMeasuredWidth() / r4) * (r3.x + 1), (getMeasuredHeight() / r05) * (r3.y + 1));
        this.ldx = this.dx;
        this.ldy = this.dy;
        this.tx = this.rect.centerX();
        this.ty = this.rect.centerY();
    L44:
        this.dx = r11.getX() - this.tx;
        this.dy = r11.getY() - this.ty;
        invalidate();
    L46:
        if (this.pressedPart == r0) goto L65;
        this.pressedPart = null;
        Runnable r112 = this.onLongPressPart;
        if (r112 == null) goto L50;
        AndroidUtilities.cancelRunOnUIThread(r112);
        this.onLongPressPart = null;
    L50:
        return true;
    L52:
        if (r11.getAction() != 1) goto L60;
        if (this.pressedPart == null) goto L65;
        this.pressedPart = null;
        this.reorderingTouch = false;
        invalidate();
        Runnable r113 = this.onLongPressPart;
        if (r113 == null) goto L58;
        AndroidUtilities.cancelRunOnUIThread(r113);
        this.onLongPressPart = null;
    L58:
        return true;
    L60:
        if (r11.getAction() != 3) goto L65;
        if (cancelTouch() == false) goto L65;
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        if (r3 != this.cameraView) goto L9;
        if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L9;
        return false;
    L9:
        return super.drawChild(r2, r3, r4);
    }

    @Override // org.telegram.ui.Components.ItemOptions.ScrimView
    public void drawScrim(Canvas r7, float r8) {
        Part r82 = this.longPressedPart;
        if (r82 == null) goto L6;
        CollageLayout.Part r83 = r82.part;
        float r1 = r83.layout.h;
        float r0 = this.animatedColumns[r83.y].set(r0.columns[r3]);
        this.rect.set((getMeasuredWidth() / r0) * r83.x, (getMeasuredHeight() / r1) * r83.y, (getMeasuredWidth() / r0) * (r83.x + 1), (getMeasuredHeight() / r1) * (r83.y + 1));
        drawPart(r7, this.rect, this.longPressedPart);
        return;
    }

    public void forceNotRestorePosition() {
    }

    public Object getBlurRenderNode() {
        if (this.renderNode != null) goto L8;
        if (Build.VERSION.SDK_INT < 31) goto L8;
        this.renderNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("CameraViewRenderNode");
        RenderNode r0 = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("CameraViewRenderNodeBlur");
        this.blurRenderNode = r0;
        BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r0);
        r0.setRenderEffect(RenderEffect.createBlurEffect(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f), CameraView$$ExternalSyntheticApiModelOutline0.m()));
    L8:
        return this.blurRenderNode;
    }

    @Override // org.telegram.ui.Components.ItemOptions.ScrimView
    public void getBounds(RectF r7) {
        Part r0 = this.longPressedPart;
        if (r0 == null) goto L6;
        CollageLayout.Part r02 = r0.part;
        float r2 = r02.layout.h;
        float r1 = this.animatedColumns[r02.y].set(r1.columns[r4]);
        r7.set((getMeasuredWidth() / r1) * r02.x, (getMeasuredHeight() / r2) * r02.y, (getMeasuredWidth() / r1) * (r02.x + 1), (getMeasuredHeight() / r2) * (r02.y + 1));
        return;
    L6:
        r7.set(0.0f, 0.0f, getWidth(), getHeight());
    }

    public ArrayList<StoryEntry> getContent() {
        ArrayList<StoryEntry> r0 = new ArrayList();
        ArrayList<Part> r5 = this.parts;
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        Part r3 = r5.get(r2);
        r2 = r2 + 1;
        Part r32 = r3;
        if (r32.hasContent() == false) goto L3;
        r0.add(Part.m19910$$Nest$fgetcontent(r32));
        goto L3
    L7:
        return r0;
    }

    public Part getCurrent() {
        return this.currentPart;
    }

    public long getDuration() {
        if (this.preview == true) goto L5;
        return 1;
    L5:
        Part r7 = getMainPart();
        if (r7 != null) goto L8;
    L12:
        return 1;
    L8:
        if (Part.m19910$$Nest$fgetcontent(r7) == null) goto L12;
        return Math.max(Math.min((long) (Part.m19910$$Nest$fgetcontent(r7).duration * (Part.m19910$$Nest$fgetcontent(r7).videoRight - Part.m19910$$Nest$fgetcontent(r7).videoLeft)), 59500), 1);
    }

    public int getFilledCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.parts.size()) goto L9;
        if (this.parts.get(r0).hasContent() == false) goto L8;
        r1 = r1 + 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public float getFilledProgress() {
        return getFilledCount() / getTotalCount();
    }

    public CollageLayout getLayout() {
        return this.currentLayout;
    }

    public Part getMainPart() {
        Part r1 = null;
        if (this.preview == true) goto L5;
        return null;
    L5:
        ArrayList<Part> r12 = this.parts;
        int r0 = r12.size();
        int r4 = 0;
        long r5 = 0;
    L6:
        if (r4 >= r0) goto L21;
        Part r7 = r12.get(r4);
        r4 = r4 + 1;
        Part r72 = r7;
        if (Part.m19910$$Nest$fgetcontent(r72) == null) goto L6;
        if (Part.m19910$$Nest$fgetcontent(r72).isVideo == false) goto L6;
        long r8 = Part.m19910$$Nest$fgetcontent(r72).duration;
        VideoPlayerHolderBase r10 = r72.videoPlayer;
        if (r10 == null) goto L19;
        if (r10.getDuration() <= 0) goto L19;
        r8 = r72.videoPlayer.getDuration();
    L19:
        if (r8 <= r5) goto L6;
        r1 = r72;
        r5 = r8;
        goto L6
    L21:
        return r1;
    }

    public Part getNext() {
        return this.nextPart;
    }

    public ArrayList<Integer> getOrder() {
        ArrayList<Integer> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.parts.size()) goto L6;
        r0.add(Integer.valueOf(Part.m19913$$Nest$fgetindex(this.parts.get(r1))));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public Part getPartAt(float r10, float r11) {
        float r0 = this.animatedRows.get();
        int r1 = 0;
    L4:
        if (r1 >= this.parts.size()) goto L9;
        Part r2 = this.parts.get(r1);
        float r4 = this.animatedColumns[r2.part.y].get();
        this.rect.set((getMeasuredWidth() / r4) * r3.x, (getMeasuredHeight() / r0) * r3.y, (getMeasuredWidth() / r4) * (r3.x + 1), (getMeasuredHeight() / r0) * (r3.y + 1));
        if (this.rect.contains(r10, r11) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r2;
    L9:
        return null;
    }

    public int getPartIndexAt(float r9, float r10) {
        float r0 = this.animatedRows.get();
        int r1 = 0;
    L4:
        if (r1 >= this.parts.size()) goto L9;
        float r3 = this.animatedColumns[this.parts.get(r1).part.y].get();
        this.rect.set((getMeasuredWidth() / r3) * r2.x, (getMeasuredHeight() / r0) * r2.y, (getMeasuredWidth() / r3) * (r2.x + 1), (getMeasuredHeight() / r0) * (r2.y + 1));
        if (this.rect.contains(r9, r10) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return -1;
    }

    public long getPosition() {
        if (this.preview == true) goto L7;
        return 0;
    L7:
        if (this.playing == false) goto L9;
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.previewStartTime;
        if (r2 <= getDuration()) goto L13;
        this.previewStartTime = r0 - (r2 % getDuration());
    L13:
        return r2;
    L9:
        return this.lastPausedPosition;
    }

    public long getPositionWithOffset() {
        long r1 = 0;
        if (this.preview == true) goto L5;
        return 0;
    L5:
        getPosition();
        Part r0 = getMainPart();
        if (r0 == null) goto L10;
        r1 = Part.m19910$$Nest$fgetcontent(r0).videoOffset + ((long) (Part.m19910$$Nest$fgetcontent(r0).videoLeft * Part.m19910$$Nest$fgetcontent(r0).duration));
    L10:
        return getPosition() + r1;
    }

    public int getTotalCount() {
        return this.parts.size();
    }

    public boolean hasContent() {
        ArrayList<Part> r4 = this.parts;
        int r0 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        Part r3 = r4.get(r2);
        r2 = r2 + 1;
        if (r3.hasContent() == false) goto L3;
        return true;
    L8:
        return false;
    }

    public boolean hasLayout() {
        if (this.currentLayout.parts.size() <= 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean hasVideo() {
        ArrayList<Part> r5 = this.parts;
        int r0 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        Part r3 = r5.get(r2);
        r2 = r2 + 1;
        Part r32 = r3;
        if (Part.m19910$$Nest$fgetcontent(r32) == null) goto L3;
        if (Part.m19910$$Nest$fgetcontent(r32).isVideo == false) goto L3;
        return true;
    L10:
        return false;
    }

    public void highlight(int r6) {
        ArrayList<Part> r0 = this.parts;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L11;
        Part r3 = r0.get(r2);
        r2 = r2 + 1;
        Part r32 = r3;
        if (Part.m19913$$Nest$fgetindex(r32) != r6) goto L3;
        Part.m19912$$Nest$fgethighlightAnimated(r32).set(1.0f, true);
        invalidate();
        return;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int r0 = 0;
    L4:
        if (r0 >= this.parts.size()) goto L6;
        this.parts.get(r0).imageReceiver.onAttachedToWindow();
        r0 = r0 + 1;
        goto L4
    L6:
        this.attached = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r1 = 0;
    L4:
        if (r1 >= this.parts.size()) goto L6;
        this.parts.get(r1).imageReceiver.onDetachedFromWindow();
        r1 = r1 + 1;
        goto L4
    L6:
        this.attached = false;
        AndroidUtilities.cancelRunOnUIThread(this.syncRunnable);
    }

    public abstract void onLayoutUpdate(CollageLayout r1);

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        int r102 = View.MeasureSpec.getSize(r10);
        int r112 = View.MeasureSpec.getSize(r11);
        setMeasuredDimension(r102, r112);
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L29;
        View r2 = getChildAt(r1);
        if (r2 != this.cameraView) goto L8;
        r2.measure(View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r112, TLObject.FLAG_30));
    L28:
        r1 = r1 + 1;
        goto L4
    L8:
        int r3 = 0;
    L10:
        if (r3 >= this.parts.size()) goto L15;
        if (r2 == this.parts.get(r3).textureView) goto L13;
        r3 = r3 + 1;
        goto L10
    L13:
        Part r32 = this.parts.get(r3);
    L16:
        if (r32 != null) goto L18;
    L27:
        r2.measure(View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r112, TLObject.FLAG_30));
        goto L28
    L18:
        if (Part.m19910$$Nest$fgetcontent(r32) == null) goto L27;
        if (Part.m19910$$Nest$fgetcontent(r32).width <= 0) goto L27;
        if (Part.m19910$$Nest$fgetcontent(r32).height <= 0) goto L27;
        int r5 = Part.m19910$$Nest$fgetcontent(r32).width;
        int r6 = Part.m19910$$Nest$fgetcontent(r32).height;
        if ((Part.m19910$$Nest$fgetcontent(r32).orientation % 90) != 1) goto L26;
        r6 = r5;
        r5 = r6;
    L26:
        float r33 = r5;
        float r62 = r6;
        float r52 = Math.min(1.0f, Math.max(r33 / r102, r62 / r112));
        r2.measure(View.MeasureSpec.makeMeasureSpec((int) (r33 * r52), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r62 * r52), TLObject.FLAG_30));
        goto L28
    L15:
        r32 = null;
        goto L16
    }

    public boolean push(StoryEntry r7) {
        if (r7 != null) goto L5;
    L15:
        Part r1 = this.currentPart;
        if (r1 == null) goto L18;
        r1.setContent(r7);
    L18:
        updatePartsState();
        requestLayout();
        if (this.currentPart != null) goto L22;
        return true;
    L22:
        return false;
    L5:
        if (r7.isVideo == false) goto L15;
        ArrayList<Part> r12 = this.parts;
        int r2 = r12.size();
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L15;
        Part r4 = r12.get(r3);
        r3 = r3 + 1;
        Part r42 = r4;
        if (Part.m19910$$Nest$fgetcontent(r42) == null) goto L7;
        if (Part.m19910$$Nest$fgetcontent(r42).isVideo == false) goto L7;
        if (Part.m19910$$Nest$fgetcontent(r42).videoVolume <= 0.0f) goto L7;
        r7.videoVolume = 0.0f;
        goto L15
    }

    public void retake(Part r2) {
        if (r2 == null) goto L9;
        r2.setContent(null);
        updatePartsState();
        invalidate();
        Runnable r1 = this.onResetState;
        if (r1 == null) goto L8;
        r1.run();
        return;
    L8:
        return;
    }

    public void seekTo(long r8, boolean r10) {
        if (this.preview == false) goto L13;
        long r82 = Utilities.clamp(r8, getDuration(), 0);
        if (this.playing == true) goto L8;
        this.lastPausedPosition = r82;
    L8:
        this.previewStartTime = System.currentTimeMillis() - r82;
        this.fastSeek = r10;
        if (this.preview == false) goto L12;
        AndroidUtilities.cancelRunOnUIThread(this.syncRunnable);
        this.syncRunnable.run();
        return;
    L12:
        return;
    }

    public void set(StoryEntry r3, boolean r4) {
        if (r3 != null) goto L4;
    L11:
        clear(true);
        return;
    L4:
        if (r3.collageContent == null) goto L11;
        setLayout(r3.collage, r4);
        int r42 = 0;
    L8:
        if (r42 >= this.parts.size()) goto L10;
        this.parts.get(r42).setContent(r3.collageContent.get(r42));
        r42 = r42 + 1;
        goto L8
    }

    public void setCameraNeedsBlur(boolean r2) {
        if (this.needsBlur != r2) goto L5;
        return;
    L5:
        this.needsBlur = r2;
        updateCameraNeedsBlur();
    }

    public void setCameraThumb(Drawable r1) {
        this.cameraThumbDrawable = r1;
        invalidate();
    }

    public void setCameraThumbVisible(boolean r1) {
        this.cameraThumbVisible = r1;
        invalidate();
    }

    public void setCameraView(CameraView r3) {
        CameraView r0 = this.cameraView;
        if (r0 == r3) goto L6;
        if (r0 == null) goto L6;
        r0.unlistenDraw(new CollageLayoutView2$$ExternalSyntheticLambda1(this));
        AndroidUtilities.removeFromParent(this.cameraView);
        this.cameraView = null;
        updateCameraNeedsBlur();
    L6:
        this.cameraView = r3;
        if (r3 == null) goto L9;
        addView(r3, LayoutHelper.createFrame(-1, -1, 119));
    L9:
        CameraView r02 = this.cameraView;
        if (r02 == null) goto L12;
        r02.unlistenDraw(new CollageLayoutView2$$ExternalSyntheticLambda1(this));
    L12:
        this.cameraView = r3;
        if (r3 == null) goto L15;
        r3.listenDraw(new CollageLayoutView2$$ExternalSyntheticLambda1(this));
    L15:
        updateCameraNeedsBlur();
        invalidate();
    }

    public void setCancelGestures(Runnable r1) {
        this.cancelGestures = r1;
    }

    public void setLayout(CollageLayout r5, boolean r6) {
        if (r5 != null) goto L4;
        r5 = new CollageLayout(".");
    L4:
        this.currentLayout = r5;
        AndroidUtilities.cancelRunOnUIThread(this.resetReordering);
        int r0 = 0;
    L6:
        if (r0 >= Math.max(r5.parts.size(), this.parts.size())) goto L26;
        if (r0 >= r5.parts.size()) goto L10;
        CollageLayout.Part r1 = r5.parts.get(r0);
    L12:
        if (r0 >= this.parts.size()) goto L14;
        Part r3 = this.parts.get(r0);
    L15:
        if (r3 != null) goto L21;
        if (r1 == null) goto L21;
        Part r2 = new Part(this);
        if (this.attached == false) goto L20;
        r2.imageReceiver.onAttachedToWindow();
    L20:
        r2.setPart(r1, r6);
        this.parts.add(r2);
    L25:
        r0 = r0 + 1;
    L21:
        if (r1 == null) goto L23;
        r3.setPart(r1, r6);
        goto L25
    L23:
        if (r3 == null) goto L25;
        this.removingParts.add(r3);
        this.parts.remove(r3);
        r3.setPart(null, r6);
        r0 = r0 - 1;
        goto L25
    L14:
        r3 = null;
        goto L15
    L10:
        r1 = null;
        goto L12
    L26:
        updatePartsState();
        invalidate();
        if (r6 == false) goto L35;
        AndroidUtilities.runOnUIThread(this.resetReordering, 360);
        return;
    }

    public void setMuted(boolean r2) {
        if (this.isMuted != r2) goto L5;
        return;
    L5:
        this.isMuted = r2;
    }

    public void setOnCameraThumbClick(Runnable r1) {
        this.onCameraThumbClick = r1;
    }

    public void setPlaying(boolean r3) {
        boolean r0 = this.restorePositionOnPlaying;
        this.restorePositionOnPlaying = true;
        if (this.playing == r3) goto L17;
        this.playing = r3;
        if (r3 == true) goto L9;
        this.lastPausedPosition = getPosition();
    L13:
        if (this.preview == false) goto L16;
        AndroidUtilities.cancelRunOnUIThread(this.syncRunnable);
        this.syncRunnable.run();
        return;
    L16:
        return;
    L9:
        if (r0 == false) goto L11;
        seekTo(this.lastPausedPosition, false);
        goto L13
    L11:
        this.fastSeek = false;
        goto L13
    }

    public void setPreview(boolean r7) {
        if (this.preview == r7) goto L40;
        this.preview = r7;
        int r0 = 0;
        if (r7 == false) goto L14;
        BlurringShader.BlurManager r1 = this.blurManager;
        if (r1 == null) goto L10;
        r1.invalidate();
    L10:
        int r12 = 0;
    L12:
        if (r12 >= this.parts.size()) goto L14;
        Part.m19915$$Nest$fputindex(this.parts.get(r12), r12);
        r12 = r12 + 1;
    L14:
        this.fastSeek = false;
        this.lastPausedPosition = 0;
        ArrayList<Part> r13 = this.parts;
        int r2 = r13.size();
    L15:
        if (r0 >= r2) goto L25;
        Part r3 = r13.get(r0);
        r0 = r0 + 1;
        Part r32 = r3;
        VideoPlayerHolderBase r4 = r32.videoPlayer;
        if (r4 == null) goto L15;
        r4.setAudioEnabled(r7, true);
        if (r7 == false) goto L24;
        if (this.playing == true) goto L24;
        r32.videoPlayer.pause();
    L24:
        r32.videoPlayer.play();
        goto L15
    L25:
        AndroidUtilities.cancelRunOnUIThread(this.syncRunnable);
        if (r7 == false) goto L39;
        this.previewStartTime = System.currentTimeMillis();
        AndroidUtilities.runOnUIThread(this.syncRunnable, (long) (1000.0f / AndroidUtilities.screenRefreshRate));
        return;
    L39:
        return;
    }

    public void setPreviewView(PreviewView r1) {
        this.previewView = r1;
    }

    public void setResetState(Runnable r1) {
        this.onResetState = r1;
    }

    public void setTimelineView(TimelineView r1) {
        this.timelineView = r1;
    }

    public void swap(int r2, int r3) {
        Collections.swap(this.parts, r2, r3);
        setLayout(this.currentLayout, true);
        this.reordering = true;
        invalidate();
    }

    public void updateCameraNeedsBlur() {
        CameraView r0 = this.cameraView;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L7:
        boolean r3 = false;
    L9:
        if (this.cameraViewBlurRenderNode == null) goto L11;
        r1 = true;
    L11:
        if (r3 != r1) goto L13;
        return;
    L13:
        if (r3 == false) goto L16;
        this.cameraViewBlurRenderNode = r0.getBlurRenderNode();
        return;
    L16:
        this.cameraViewBlurRenderNode = null;
        return;
    L5:
        if (this.needsBlur == false) goto L7;
        r3 = true;
        goto L9
    }

    public void updatePartsState() {
        this.currentPart = null;
        this.nextPart = null;
        int r1 = 0;
    L4:
        if (r1 >= this.parts.size()) goto L12;
        Part r2 = this.parts.get(r1);
        if (r2.hasContent() == true) goto L11;
        if (this.currentPart != null) goto L10;
        this.currentPart = r2;
        goto L11
    L10:
        this.nextPart = r2;
    L11:
        r1 = r1 + 1;
    L12:
        int r12 = 0;
    L14:
        if (r12 >= this.parts.size()) goto L20;
        Part r22 = this.parts.get(r12);
        if (r22 != this.currentPart) goto L18;
        boolean r3 = true;
    L19:
        r22.setCurrent(r3);
        r12 = r12 + 1;
        goto L14
    L18:
        r3 = false;
        goto L19
    }
}
