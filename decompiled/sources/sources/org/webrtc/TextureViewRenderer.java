package org.webrtc;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Looper;
import android.view.TextureView;
import android.view.View;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.ActionBar.Theme$$ExternalSyntheticLambda9;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.GlGenericDrawer;
import org.webrtc.RendererCommon;

/* loaded from: classes6.dex */
public class TextureViewRenderer extends TextureView implements TextureView.SurfaceTextureListener, VideoSink, RendererCommon.RendererEvents {
    private static final String TAG = "TextureViewRenderer";
    private TextureView backgroundRenderer;
    private int cameraRotation;
    private final TextureEglRenderer eglRenderer;
    private boolean enableFixedSize;
    private boolean isCamera;
    private int maxTextureSize;
    private boolean mirror;
    private OrientationHelper orientationHelper;
    private VideoSink parentSink;
    private RendererCommon.RendererEvents rendererEvents;
    private final String resourceName;
    private boolean rotateTextureWithScreen;
    public int rotatedFrameHeight;
    public int rotatedFrameWidth;
    private int screenRotation;
    private int surfaceHeight;
    private int surfaceWidth;
    int textureRotation;
    Runnable updateScreenRunnable;
    boolean useCameraRotation;
    private int videoHeight;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure;
    private int videoWidth;

    /* renamed from: org.webrtc.TextureViewRenderer$1, reason: invalid class name */
    public class AnonymousClass1 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ TextureViewRenderer this$0;

        public AnonymousClass1(TextureViewRenderer r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r1, int r2, int r3) {
            this.this$0.createBackgroundSurface(r1);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r2) {
            ThreadUtils.checkIsOnMainThread();
            TextureViewRenderer.m22394$$Nest$fgeteglRenderer(this.this$0).releaseEglSurface(null, true);
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    /* renamed from: org.webrtc.TextureViewRenderer$2, reason: invalid class name */
    public class AnonymousClass2 extends OrientationHelper {
        final /* synthetic */ TextureViewRenderer this$0;

        public AnonymousClass2(TextureViewRenderer r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.OrientationHelper
        public void onOrientationUpdate(int r1) {
            if (TextureViewRenderer.m22395$$Nest$fgetisCamera(this.this$0) == true) goto L6;
            this.this$0.updateRotation();
            return;
        }
    }

    public static class TextureEglRenderer extends EglRenderer implements TextureView.SurfaceTextureListener {
        private static final String TAG = "TextureEglRenderer";
        private int frameRotation;
        private boolean isFirstFrameRendered;
        private boolean isRenderingPaused;
        private final Object layoutLock;
        private RendererCommon.RendererEvents rendererEvents;
        private int rotatedFrameHeight;
        private int rotatedFrameWidth;

        public static /* synthetic */ void $r8$lambda$umNbtu99JJgfdYsTu7ho2XQJfL8(TextureEglRenderer r0) {
            r0.lambda$onFirstFrameRendered$0();
        }

        /* renamed from: -$$Nest$fgetisFirstFrameRendered, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22396$$Nest$fgetisFirstFrameRendered(TextureEglRenderer r0) {
            return r0.isFirstFrameRendered;
        }

        /* renamed from: -$$Nest$fgetlayoutLock, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object m22397$$Nest$fgetlayoutLock(TextureEglRenderer r0) {
            return r0.layoutLock;
        }

        /* renamed from: -$$Nest$fputisFirstFrameRendered, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22398$$Nest$fputisFirstFrameRendered(TextureEglRenderer r0, boolean r1) {
            r0.isFirstFrameRendered = r1;
        }

        public TextureEglRenderer(String r1) {
            super(r1);
            this.layoutLock = new Object();
        }

        private /* synthetic */ void lambda$onFirstFrameRendered$0() {
            this.isFirstFrameRendered = true;
            this.rendererEvents.onFirstFrameRendered();
        }

        private void logD(String r2) {
            Logging.d(TAG, this.name + ": " + r2);
        }

        private void updateFrameDimensionsAndReportEvents(VideoFrame r6) {
            Object r1 = this.layoutLock;
            monitor-enter(r1);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.isRenderingPaused == false) goto L11;
            monitor-exit(r1);     // Catch: Throwable -> L8
            return;
        L11:
            if (this.rotatedFrameWidth == r6.getRotatedWidth()) goto L13;
        L16:
            logD("Reporting frame resolution changed to " + r6.getBuffer().getWidth() + "x" + r6.getBuffer().getHeight() + " with rotation " + r6.getRotation());     // Catch: Throwable -> L8
            RendererCommon.RendererEvents r0 = this.rendererEvents;     // Catch: Throwable -> L8
            if (r0 == null) goto L19;
            r0.onFrameResolutionChanged(r6.getBuffer().getWidth(), r6.getBuffer().getHeight(), r6.getRotation());     // Catch: Throwable -> L8
        L19:
            this.rotatedFrameWidth = r6.getRotatedWidth();     // Catch: Throwable -> L8
            this.rotatedFrameHeight = r6.getRotatedHeight();     // Catch: Throwable -> L8
            this.frameRotation = r6.getRotation();     // Catch: Throwable -> L8
        L20:
            monitor-exit(r1);     // Catch: Throwable -> L8
            return;
        L13:
            if (this.rotatedFrameHeight != r6.getRotatedHeight()) goto L16;
            if (this.frameRotation == r6.getRotation()) goto L20;
            goto L16
        }

        @Override // org.webrtc.EglRenderer
        public void disableFpsReduction() {
            Object r0 = this.layoutLock;
            monitor-enter(r0);
            this.isRenderingPaused = false;     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
            super.disableFpsReduction();
            return;
        L9:
            th = move-exception;
            throw th;
        }

        public void init(EglBase.Context r2, RendererCommon.RendererEvents r3, int[] r4, RendererCommon.GlDrawer r5) {
            ThreadUtils.checkIsOnMainThread();
            this.rendererEvents = r3;
            Object r32 = this.layoutLock;
            monitor-enter(r32);
            this.isFirstFrameRendered = false;     // Catch: Throwable -> L9
            this.rotatedFrameWidth = 0;     // Catch: Throwable -> L9
            this.rotatedFrameHeight = 0;     // Catch: Throwable -> L9
            this.frameRotation = 0;     // Catch: Throwable -> L9
            monitor-exit(r32);     // Catch: Throwable -> L9
            super.init(r2, r4, r5);
            return;
        L9:
            th = move-exception;
            throw th;
        }

        @Override // org.webrtc.EglRenderer
        public void init(EglBase.Context r2, int[] r3, RendererCommon.GlDrawer r4) {
            init(r2, null, r3, r4);
        }

        @Override // org.webrtc.EglRenderer
        public void onFirstFrameRendered() {
            AndroidUtilities.runOnUIThread(new TextureViewRenderer$TextureEglRenderer$$ExternalSyntheticLambda0(this));
        }

        @Override // org.webrtc.EglRenderer, org.webrtc.VideoSink
        public void onFrame(VideoFrame r1) {
            updateFrameDimensionsAndReportEvents(r1);
            super.onFrame(r1);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r1, int r2, int r3) {
            ThreadUtils.checkIsOnMainThread();
            createEglSurface(r1);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r4) {
            ThreadUtils.checkIsOnMainThread();
            CountDownLatch r42 = new CountDownLatch(1);
            releaseEglSurface(new Theme$$ExternalSyntheticLambda9(r42), false);
            ThreadUtils.awaitUninterruptibly(r42);
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r2, int r3, int r4) {
            ThreadUtils.checkIsOnMainThread();
            logD("surfaceChanged: size: " + r3 + "x" + r4);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }

        @Override // org.webrtc.EglRenderer
        public void pauseVideo() {
            Object r0 = this.layoutLock;
            monitor-enter(r0);
            this.isRenderingPaused = true;     // Catch: Throwable -> L9
            monitor-exit(r0);     // Catch: Throwable -> L9
            super.pauseVideo();
            return;
        L9:
            th = move-exception;
            throw th;
        }

        @Override // org.webrtc.EglRenderer
        public void setFpsReduction(float r3) {
            Object r0 = this.layoutLock;
            monitor-enter(r0);
            if (r3 != 0.0f) goto L7;
            boolean r1 = true;
        L15:
            this.isRenderingPaused = r1;     // Catch: Throwable -> L12
            monitor-exit(r0);     // Catch: Throwable -> L12
            super.setFpsReduction(r3);
            return;
        L12:
            th = move-exception;
            throw th;
        L7:
            r1 = false;
            goto L15
        }
    }

    /* renamed from: $r8$lambda$C4deZV-Q1neM-Z8_dORroprFizc, reason: not valid java name */
    public static /* synthetic */ void m22392$r8$lambda$C4deZVQ1neMZ8_dORroprFizc(TextureViewRenderer r0, int r1, int r2, int r3, int r4) {
        r0.lambda$onFrameResolutionChanged$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$QP3hk6TLKEXwWx1ONdvaX-3OJ18, reason: not valid java name */
    public static /* synthetic */ void m22393$r8$lambda$QP3hk6TLKEXwWx1ONdvaX3OJ18(TextureViewRenderer r0, int r1, int r2) {
        r0.lambda$updateVideoSizes$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgeteglRenderer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureEglRenderer m22394$$Nest$fgeteglRenderer(TextureViewRenderer r0) {
        return r0.eglRenderer;
    }

    /* renamed from: -$$Nest$fgetisCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22395$$Nest$fgetisCamera(TextureViewRenderer r0) {
        return r0.isCamera;
    }

    public TextureViewRenderer(Context r2) {
        super(r2);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        String r22 = getResourceName();
        this.resourceName = r22;
        this.eglRenderer = new TextureEglRenderer(r22);
        setSurfaceTextureListener(this);
    }

    private String getResourceName() {
        return getResources().getResourceEntryName(getId());
    L4:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private /* synthetic */ void lambda$onFrameResolutionChanged$0(int r2, int r3, int r4, int r5) {
        this.updateScreenRunnable = null;
        this.videoWidth = r2;
        this.videoHeight = r3;
        this.rotatedFrameWidth = r4;
        this.rotatedFrameHeight = r5;
        updateSurfaceSize();
        requestLayout();
    }

    private /* synthetic */ void lambda$updateVideoSizes$1(int r2, int r3) {
        this.updateScreenRunnable = null;
        this.rotatedFrameWidth = r2;
        this.rotatedFrameHeight = r3;
        updateSurfaceSize();
        requestLayout();
    }

    private void logD(String r2) {
        Logging.d(TAG, this.resourceName + ": " + r2);
    }

    private void onRotationChanged() {
        if (this.useCameraRotation == false) goto L5;
        int r0 = OrientationHelper.cameraOrientation;
    L6:
        boolean r1 = this.mirror;
        if (r1 == false) goto L9;
        r0 = 360 - r0;
    L9:
        int r02 = -r0;
        if (this.useCameraRotation == false) goto L22;
        int r2 = this.screenRotation;
        if (r2 != 1) goto L18;
        if (r1 == false) goto L15;
        int r12 = 90;
    L16:
        r02 = r02 + r12;
        goto L22
    L15:
        r12 = -90;
        goto L16
    L18:
        if (r2 != 3) goto L22;
        if (r1 == false) goto L21;
        r12 = 270;
        goto L16
    L21:
        r12 = -270;
    L22:
        this.eglRenderer.setRotation(r02);
        this.eglRenderer.setMirror(this.mirror);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    private void postOrRun(Runnable r2) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L6;
        r2.run();
        return;
    L6:
        AndroidUtilities.runOnUIThread(r2);
    }

    private void updateSurfaceSize() {
        ThreadUtils.checkIsOnMainThread();
        if (this.enableFixedSize == true) goto L5;
    L24:
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        return;
    L5:
        if (this.rotatedFrameWidth == 0) goto L24;
        if (this.rotatedFrameHeight == 0) goto L24;
        if (getWidth() == 0) goto L24;
        if (getHeight() == 0) goto L24;
        float r0 = getWidth() / getHeight();
        int r1 = this.rotatedFrameWidth;
        int r3 = this.rotatedFrameHeight;
        if ((r1 / r3) <= r0) goto L15;
        r1 = (int) (r3 * r0);
    L16:
        int r02 = Math.min(getWidth(), r1);
        int r12 = Math.min(getHeight(), r3);
        logD("updateSurfaceSize. Layout size: " + getWidth() + "x" + getHeight() + ", frame size: " + this.rotatedFrameWidth + "x" + this.rotatedFrameHeight + ", requested surface size: " + r02 + "x" + r12 + ", old surface size: " + this.surfaceWidth + "x" + this.surfaceHeight);
        if (r02 == this.surfaceWidth) goto L19;
    L22:
        this.surfaceWidth = r02;
        this.surfaceHeight = r12;
        return;
    L19:
        if (r12 != this.surfaceHeight) goto L22;
        return;
    L15:
        r3 = (int) (r3 / r0);
        goto L16
    }

    private void updateVideoSizes() {
        int r0 = this.videoHeight;
        if (r0 == 0) goto L59;
        int r1 = this.videoWidth;
        if (r1 != 0) goto L7;
        return;
    L7:
        if (this.rotateTextureWithScreen == true) goto L9;
        int r02 = this.textureRotation - OrientationHelper.cameraOrientation;
        if (r02 == 0) goto L35;
        if (r02 == 180) goto L35;
        if (r02 == (-180)) goto L35;
        int r12 = this.videoHeight;
    L36:
        if (r02 == 0) goto L41;
        if (r02 == 180) goto L41;
        if (r02 == (-180)) goto L41;
        r0 = this.videoWidth;
    L42:
        int r3 = r12;
    L44:
        if (this.rotatedFrameWidth == r3) goto L46;
    L47:
        Object r13 = TextureEglRenderer.m22397$$Nest$fgetlayoutLock(this.eglRenderer);
        monitor-enter(r13);
        Runnable r2 = this.updateScreenRunnable;     // Catch: Throwable -> L52
        if (r2 == null) goto L54;
        AndroidUtilities.cancelRunOnUIThread(r2);     // Catch: Throwable -> L52
    L54:
        TextureViewRenderer$$ExternalSyntheticLambda1 r22 = new TextureViewRenderer$$ExternalSyntheticLambda1(this, r3, r0);     // Catch: Throwable -> L52
        this.updateScreenRunnable = r22;     // Catch: Throwable -> L52
        postOrRun(r22);     // Catch: Throwable -> L52
        monitor-exit(r13);     // Catch: Throwable -> L52
        return;
    L52:
        th = move-exception;
        throw th;
    L46:
        if (this.rotatedFrameHeight != r0) goto L47;
        return;
    L41:
        r0 = this.videoHeight;
    L35:
        r12 = this.videoWidth;
        goto L36
    L9:
        if (this.useCameraRotation == false) goto L16;
        int r23 = this.screenRotation;
        if (r23 != 0) goto L13;
        r3 = r0;
    L14:
        if (r23 != 0) goto L44;
        r0 = r1;
        goto L44
    L13:
        r3 = r1;
        goto L14
    L16:
        int r24 = this.textureRotation;
        if (r24 == 0) goto L22;
        if (r24 == 180) goto L22;
        if (r24 == (-180)) goto L22;
        int r5 = r0;
    L23:
        if (r24 == 0) goto L28;
        if (r24 == 180) goto L28;
        if (r24 == (-180)) goto L28;
        r0 = r1;
    L28:
        r3 = r5;
    L22:
        r5 = r1;
        goto L23
    }

    public void addFrameListener(EglRenderer.FrameListener r1, float r2) {
        this.eglRenderer.addFrameListener(r1, r2);
    }

    public void addFrameListener(EglRenderer.FrameListener r1, float r2, RendererCommon.GlDrawer r3) {
        this.eglRenderer.addFrameListener(r1, r2, r3);
    }

    public void clearFirstFrame() {
        TextureEglRenderer r1 = this.eglRenderer;
        r1.firstFrameRendered = false;
        TextureEglRenderer.m22398$$Nest$fputisFirstFrameRendered(r1, false);
    }

    public void clearImage() {
        this.eglRenderer.clearImage();
        TextureEglRenderer.m22398$$Nest$fputisFirstFrameRendered(this.eglRenderer, false);
    }

    public void createBackgroundSurface(SurfaceTexture r1) {
        this.eglRenderer.createBackgroundSurface(r1);
    }

    public void disableFpsReduction() {
        this.eglRenderer.disableFpsReduction();
    }

    public void getRenderBufferBitmap(GlGenericDrawer.TextureCallback r1) {
        this.eglRenderer.getTexture(r1);
    }

    public void init(EglBase.Context r3, RendererCommon.RendererEvents r4) {
        init(r3, r4, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context r1, RendererCommon.RendererEvents r2, int[] r3, RendererCommon.GlDrawer r4) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = r2;
        this.rotatedFrameWidth = 0;
        this.rotatedFrameHeight = 0;
        this.eglRenderer.init(r1, this, r3, r4);
    }

    public boolean isFirstFrameRendered() {
        return TextureEglRenderer.m22396$$Nest$fgetisFirstFrameRendered(this.eglRenderer);
    }

    public void onFirstFrameRendered() {
        RendererCommon.RendererEvents r0 = this.rendererEvents;
        if (r0 == null) goto L6;
        r0.onFirstFrameRendered();
        return;
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame r1) {
        this.eglRenderer.onFrame(r1);
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFrameResolutionChanged(int r8, int r9, int r10) {
        RendererCommon.RendererEvents r0 = this.rendererEvents;
        if (r0 == null) goto L5;
        r0.onFrameResolutionChanged(r8, r9, r10);
    L5:
        this.textureRotation = r10;
        boolean r02 = this.rotateTextureWithScreen;
        boolean r1 = this.isCamera;
        if (r02 == false) goto L30;
        if (r1 == false) goto L10;
        onRotationChanged();
    L10:
        if (this.useCameraRotation == false) goto L19;
        int r102 = this.screenRotation;
        if (r102 != 0) goto L14;
        int r03 = r9;
    L15:
        if (r102 != 0) goto L17;
    L16:
        int r103 = r8;
    L18:
        int r6 = r103;
        int r5 = r03;
        Object r104 = TextureEglRenderer.m22397$$Nest$fgetlayoutLock(this.eglRenderer);
        monitor-enter(r104);
        Runnable r04 = this.updateScreenRunnable;     // Catch: Throwable -> L48
        if (r04 == null) goto L50;
        AndroidUtilities.cancelRunOnUIThread(r04);     // Catch: Throwable -> L48
    L50:
        TextureViewRenderer$$ExternalSyntheticLambda0 r12 = new TextureViewRenderer$$ExternalSyntheticLambda0(this, r8, r9, r5, r6);     // Catch: Throwable -> L48
        this.updateScreenRunnable = r12;     // Catch: Throwable -> L48
        postOrRun(r12);     // Catch: Throwable -> L48
        monitor-exit(r104);     // Catch: Throwable -> L48
        return;
    L48:
        th = move-exception;
        throw th;
    L17:
        r103 = r9;
        goto L18
    L14:
        r03 = r8;
        goto L15
    L19:
        int r105 = this.textureRotation;
        if (r105 == 0) goto L25;
        if (r105 == 180) goto L25;
        if (r105 == (-180)) goto L25;
        r03 = r9;
    L26:
        if (r105 == 0) goto L17;
        if (r105 == 180) goto L17;
        if (r105 != (-180)) goto L16;
    L25:
        r03 = r8;
        goto L26
    L30:
        if (r1 == false) goto L32;
        this.eglRenderer.setRotation(-OrientationHelper.cameraRotation);
    L32:
        int r106 = r10 - OrientationHelper.cameraOrientation;
        if (r106 == 0) goto L38;
        if (r106 == 180) goto L38;
        if (r106 == (-180)) goto L38;
        r03 = r9;
    L39:
        if (r106 == 0) goto L17;
        if (r106 == 180) goto L17;
        if (r106 != (-180)) goto L16;
    L38:
        r03 = r8;
        goto L39
    }

    @Override // android.view.View
    public void onMeasure(int r8, int r9) {
        ThreadUtils.checkIsOnMainThread();
        if (this.isCamera == false) goto L5;
    L7:
        int r0 = this.maxTextureSize;
        RendererCommon.VideoLayoutMeasure r1 = this.videoLayoutMeasure;
        if (r0 <= 0) goto L10;
        Point r82 = r1.measure(this.isCamera, View.MeasureSpec.makeMeasureSpec(Math.min(r0, View.MeasureSpec.getSize(r8)), View.MeasureSpec.getMode(r8)), View.MeasureSpec.makeMeasureSpec(Math.min(this.maxTextureSize, View.MeasureSpec.getSize(r9)), View.MeasureSpec.getMode(r9)), this.rotatedFrameWidth, this.rotatedFrameHeight);
    L11:
        setMeasuredDimension(r82.x, r82.y);
        if (this.rotatedFrameWidth != 0) goto L14;
    L16:
        updateSurfaceSize();
        return;
    L14:
        if (this.rotatedFrameHeight == 0) goto L16;
        this.eglRenderer.setLayoutAspectRatio(getMeasuredWidth() / getMeasuredHeight());
        goto L16
    L10:
        r82 = r1.measure(this.isCamera, r8, r9, this.rotatedFrameWidth, this.rotatedFrameHeight);
        goto L11
    L5:
        if (this.rotateTextureWithScreen == false) goto L7;
        updateVideoSizes();
        goto L7
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture r2, int r3, int r4) {
        ThreadUtils.checkIsOnMainThread();
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        updateSurfaceSize();
        this.eglRenderer.onSurfaceTextureAvailable(r2, r3, r4);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture r3) {
        VideoSink r0 = this.parentSink;
        if ((r0 instanceof VoIPService.ProxyVideoSink) == false) goto L5;
        VoIPService.ProxyVideoSink r02 = (VoIPService.ProxyVideoSink) r0;
        r02.removeTarget(this);
        r02.removeBackground(this);
    L5:
        this.eglRenderer.onSurfaceTextureDestroyed(r3);
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        this.surfaceWidth = r2;
        this.surfaceHeight = r3;
        this.eglRenderer.onSurfaceTextureSizeChanged(r1, r2, r3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        this.eglRenderer.onSurfaceTextureUpdated(r1);
    }

    public void pauseVideo() {
        this.eglRenderer.pauseVideo();
    }

    public void release() {
        this.eglRenderer.release();
        OrientationHelper r1 = this.orientationHelper;
        if (r1 == null) goto L6;
        r1.stop();
        return;
    }

    public void removeFrameListener(EglRenderer.FrameListener r1) {
        this.eglRenderer.removeFrameListener(r1);
    }

    public void setBackgroundRenderer(TextureView r2) {
        if (LiteMode.isEnabled(512) == true) goto L5;
        return;
    L5:
        this.backgroundRenderer = r2;
        if (r2 != null) goto L9;
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.releaseEglSurface(null, true);
        return;
    L9:
        r2.setSurfaceTextureListener(new AnonymousClass1(this));
    }

    public void setEnableHardwareScaler(boolean r1) {
        ThreadUtils.checkIsOnMainThread();
        this.enableFixedSize = r1;
        updateSurfaceSize();
    }

    public void setFpsReduction(float r1) {
        this.eglRenderer.setFpsReduction(r1);
    }

    public void setIsCamera(boolean r1) {
        this.isCamera = r1;
        if (r1 == true) goto L6;
        AnonymousClass2 r12 = new AnonymousClass2(this);
        this.orientationHelper = r12;
        r12.start();
        return;
    }

    public void setMaxTextureSize(int r1) {
        this.maxTextureSize = r1;
    }

    public void setMirror(boolean r2) {
        if (this.mirror == r2) goto L10;
        this.mirror = r2;
        if (this.rotateTextureWithScreen == false) goto L7;
        onRotationChanged();
    L8:
        updateSurfaceSize();
        requestLayout();
        return;
    L7:
        this.eglRenderer.setMirror(r2);
        goto L8
    }

    @Override // org.webrtc.VideoSink
    public void setParentSink(VideoSink r1) {
        this.parentSink = r1;
    }

    public void setRotateTextureWithScreen(boolean r2) {
        if (this.rotateTextureWithScreen == r2) goto L6;
        this.rotateTextureWithScreen = r2;
        requestLayout();
        return;
    }

    @Override // android.view.View
    public void setRotation(float r1) {
        super.setRotation(r1);
    }

    @Override // android.view.View
    public void setRotationX(float r1) {
        super.setRotationX(r1);
    }

    @Override // android.view.View
    public void setRotationY(float r1) {
        super.setRotationY(r1);
    }

    public void setScalingType(RendererCommon.ScalingType r2) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(r2);
        requestLayout();
    }

    public void setScalingType(RendererCommon.ScalingType r2, RendererCommon.ScalingType r3) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(r2, r3);
        requestLayout();
    }

    public void setScreenRotation(int r1) {
        this.screenRotation = r1;
        onRotationChanged();
        updateVideoSizes();
    }

    public void setUseCameraRotation(boolean r2) {
        if (this.useCameraRotation == r2) goto L6;
        this.useCameraRotation = r2;
        onRotationChanged();
        updateVideoSizes();
        return;
    }

    public void updateRotation() {
        if (this.orientationHelper != null) goto L5;
        return;
    L5:
        if (this.rotatedFrameWidth != 0) goto L7;
        return;
    L7:
        if (this.rotatedFrameHeight == 0) goto L31;
        View r0 = (View) getParent();
        if (r0 == null) goto L32;
        int r1 = this.orientationHelper.getOrientation();
        float r2 = getMeasuredWidth();
        float r3 = getMeasuredHeight();
        float r4 = r0.getMeasuredWidth();
        float r02 = r0.getMeasuredHeight();
        if (r1 == 90) goto L17;
        if (r1 == 270) goto L17;
        float r5 = r2;
        float r7 = r3;
    L19:
        if (r5 >= r7) goto L21;
        float r22 = Math.max(r5 / r2, r7 / r3);
    L22:
        float r52 = r5 * r22;
        float r72 = r7 * r22;
        if (Math.abs((r52 / r72) - (r4 / r02)) >= 0.1f) goto L25;
        r22 = r22 * Math.max(r4 / r52, r02 / r72);
    L25:
        if (r1 != 270) goto L27;
        r1 = -90;
    L27:
        animate().scaleX(r22).scaleY(r22).rotation(-r1).setDuration(180).start();
        return;
    L21:
        r22 = Math.min(r5 / r2, r7 / r3);
    L17:
        r7 = r2;
        r5 = r3;
        goto L19
    L32:
        return;
    }
}
