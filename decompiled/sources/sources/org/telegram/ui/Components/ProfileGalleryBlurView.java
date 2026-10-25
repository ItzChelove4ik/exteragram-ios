package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RecordingCanvas;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.ProfileMetaballView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class ProfileGalleryBlurView extends View {
    public int actionSize;
    private RenderNode actionsBlurNode;
    private ProfileActionsView actionsView;
    private final AnimatedFloat alpha;
    private RenderNode blurNode;
    private final Runnable blurTask;
    private final ProfileMetaballView.BlurBitmapHolder[] currentFrame;
    private int currentPosition;
    private int frameHeight;
    private int frameWidth;
    private final Runnable invalidateTask;
    private volatile boolean isBluring;
    private final ViewPager.OnPageChangeListener listener;
    private final SizeNotifierFrameLayout.IViewWithInvalidateCallback[] listeners;
    private final Object lock;
    private boolean loopInvalidate;
    private ProfileMusicView musicView;
    private boolean needNewFrame;
    private final ProfileMetaballView.BlurBitmapHolder[] nextFrame;
    private int offset;
    private final Paint[] paints;
    private boolean shouldBlurActions;
    public int size;
    private boolean sizeChanged;
    private boolean usingRenderNode;
    private ProfileGalleryView view;

    /* renamed from: org.telegram.ui.Components.ProfileGalleryBlurView$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ ProfileGalleryBlurView this$0;

        public AnonymousClass1(ProfileGalleryBlurView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r4, float r5, int r6) {
            if (ProfileGalleryBlurView.m12212$$Nest$fgetusingRenderNode(this.this$0) == false) goto L5;
        L12:
            int r52 = ProfileGalleryBlurView.m12210$$Nest$fgetcurrentPosition(this.this$0);
            int r0 = ProfileGalleryBlurView.m12211$$Nest$fgetoffset(this.this$0);
            ProfileGalleryBlurView.m12213$$Nest$fputcurrentPosition(this.this$0, r4);
            ProfileGalleryBlurView.m12214$$Nest$fputoffset(this.this$0, r6);
            if (r52 == ProfileGalleryBlurView.m12210$$Nest$fgetcurrentPosition(this.this$0)) goto L15;
        L18:
            ProfileGalleryBlurView.m12216$$Nest$mupdateContent(this.this$0);
            return;
        L15:
            if (r0 != ProfileGalleryBlurView.m12211$$Nest$fgetoffset(this.this$0)) goto L18;
            return;
        L5:
            if (Math.abs(r4 - ProfileGalleryBlurView.m12210$$Nest$fgetcurrentPosition(this.this$0)) != 1) goto L12;
            int r53 = ProfileGalleryBlurView.m12210$$Nest$fgetcurrentPosition(this.this$0);
            ProfileGalleryBlurView r1 = this.this$0;
            if (r4 <= r53) goto L10;
            ProfileGalleryBlurView.m12215$$Nest$mswap(r1, 0, 1, 1);
            goto L12
        L10:
            if (r4 >= ProfileGalleryBlurView.m12210$$Nest$fgetcurrentPosition(r1)) goto L12;
            ProfileGalleryBlurView.m12215$$Nest$mswap(this.this$0, 1, 0, 0);
            ProfileGalleryBlurView.m12215$$Nest$mswap(this.this$0, 2, 0, -1);
            goto L12
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$5hK5xNXrt8UXQLj7Lb6c8wOkUrI(ProfileGalleryBlurView r0) {
        r0.doBlur();
    }

    /* renamed from: $r8$lambda$Nro4LLGB67uasNuO2pf-DijJz4s, reason: not valid java name */
    public static /* synthetic */ void m12209$r8$lambda$Nro4LLGB67uasNuO2pfDijJz4s(ProfileGalleryBlurView r0) {
        r0.lambda$doBlur$0();
    }

    public static /* synthetic */ void $r8$lambda$gbtwMa5z6qDUm4hXzaGmL4EjEK4(ProfileGalleryBlurView r0) {
        r0.updateContent();
    }

    /* renamed from: -$$Nest$fgetcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12210$$Nest$fgetcurrentPosition(ProfileGalleryBlurView r0) {
        return r0.currentPosition;
    }

    /* renamed from: -$$Nest$fgetoffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12211$$Nest$fgetoffset(ProfileGalleryBlurView r0) {
        return r0.offset;
    }

    /* renamed from: -$$Nest$fgetusingRenderNode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12212$$Nest$fgetusingRenderNode(ProfileGalleryBlurView r0) {
        return r0.usingRenderNode;
    }

    /* renamed from: -$$Nest$fputcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12213$$Nest$fputcurrentPosition(ProfileGalleryBlurView r0, int r1) {
        r0.currentPosition = r1;
    }

    /* renamed from: -$$Nest$fputoffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12214$$Nest$fputoffset(ProfileGalleryBlurView r0, int r1) {
        r0.offset = r1;
    }

    /* renamed from: -$$Nest$mswap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12215$$Nest$mswap(ProfileGalleryBlurView r0, int r1, int r2, int r3) {
        r0.swap(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateContent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12216$$Nest$mupdateContent(ProfileGalleryBlurView r0) {
        r0.updateContent();
    }

    public ProfileGalleryBlurView(Context r11) {
        super(r11);
        if (Build.VERSION.SDK_INT < 31) goto L5;
        boolean r112 = true;
    L6:
        this.usingRenderNode = r112;
        this.lock = new Object();
        this.nextFrame = new ProfileMetaballView.BlurBitmapHolder[3];
        this.currentFrame = new ProfileMetaballView.BlurBitmapHolder[3];
        this.isBluring = false;
        Paint[] r0 = {new Paint(), new Paint()};
        this.paints = r0;
        this.blurTask = new ProfileGalleryBlurView$$ExternalSyntheticLambda1(this);
        this.invalidateTask = new ProfileGalleryBlurView$$ExternalSyntheticLambda2(this);
        this.currentPosition = -1;
        this.loopInvalidate = false;
        this.sizeChanged = false;
        this.needNewFrame = false;
        this.listeners = new SizeNotifierFrameLayout.IViewWithInvalidateCallback[3];
        AnimatedFloat r3 = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.DEFAULT);
        this.alpha = r3;
        this.listener = new AnonymousClass1(this);
        r3.set(1.0f, true);
        boolean r10 = this.usingRenderNode & SharedConfig.useNewBlur;
        this.usingRenderNode = r10;
        if (r10 == false) goto L10;
        setLayerType(2, null);
        return;
    L10:
        setLayerType(1, r0[0]);
        setLayerType(1, r0[1]);
        return;
    L5:
        r112 = false;
        goto L6
    }

    private void applyShader(Bitmap r11, int r12) {
        if (r12 >= 2) goto L10;
        if (r11 != null) goto L6;
        return;
    L6:
        if (r11.isRecycled() == true) goto L12;
        LinearGradient r2 = new LinearGradient(0.0f, 0.0f, 0.0f, this.size / 6.0f, new int[]{0, -1}, new float[]{0.0f, AndroidUtilities.dpf2(56.0f) / this.size}, Shader.TileMode.CLAMP);
        Shader.TileMode r1 = Shader.TileMode.MIRROR;
        this.paints[r12].setShader(new ComposeShader(new BitmapShader(r11, r1, r1), r2, PorterDuff.Mode.DST_IN));
        return;
    L12:
        return;
    }

    private void captureActionsBlurRenderNode(float r6, ProfileActivity.AvatarImageView r7, float r8, float r9) {
        initActionsRenderNode();
        if (this.shouldBlurActions == true) goto L11;
        ProfileActionsView r62 = this.actionsView;
        if (r62 == null) goto L7;
        r62.drawingBlur(false);
    L7:
        ProfileMusicView r5 = this.musicView;
        if (r5 == null) goto L25;
        r5.drawingBlur(false);
        return;
    L25:
        return;
    L11:
        float r0 = (getRenderNodeScale() * r8) * 8.0f;
        this.actionsBlurNode.setPosition(0, 0, (int) Math.ceil(r6 / r0), (int) ((this.actionSize + r9) / r0));
        RecordingCanvas r63 = this.actionsBlurNode.beginRecording();
        r63.scale(0.125f, 0.125f);
        r63.drawRenderNode(this.blurNode);
        this.actionsBlurNode.endRecording();
        this.actionsBlurNode.setAlpha(this.alpha.set(1.0f));
        ProfileActionsView r64 = this.actionsView;
        if (r64 == null) goto L17;
        RenderNode r2 = this.actionsBlurNode;
        if (r7 == null) goto L16;
        r64.drawingBlur(r2, r7, r0 / r8, -r9);
        goto L17
    L16:
        r64.drawingBlur(r2, null, r0, -r9);
    L17:
        ProfileMusicView r65 = this.musicView;
        if (r65 == null) goto L26;
        RenderNode r52 = this.actionsBlurNode;
        if (r7 == null) goto L23;
        r65.drawingBlur(r52, r7, r0 / r8, (-r9) + AndroidUtilities.dp(22.0f));
        return;
    L23:
        r65.drawingBlur(r52, null, r0, (-r9) + AndroidUtilities.dp(22.0f));
        return;
    }

    private boolean captureNextFrame() {
        ProfileGalleryView r0 = this.view;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L63:
        return false;
    L5:
        if (r0.isZooming() == true) goto L63;
        int r02 = (int) (this.view.getMeasuredWidth() / 6.0f);
        int r2 = (int) (this.size / 6.0f);
        if (r02 <= 0) goto L63;
        if (r2 <= 0) goto L63;
        this.frameWidth = r02;
        this.frameHeight = r2;
        if (this.sizeChanged == true) goto L14;
    L16:
        int r3 = this.nextFrame.length;
    L17:
        this.sizeChanged = false;
        this.needNewFrame = false;
        int r5 = 0;
    L18:
        if (r5 >= r3) goto L35;
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r6 = this.listeners[r5];
        if (r6 == null) goto L22;
        r6.listenInvalidate(null);
    L22:
        ProfileMetaballView.BlurBitmapHolder r62 = this.nextFrame[r5];
        if (r62 != null) goto L25;
    L26:
        if (r62 == null) goto L28;
        r62.recycle();
    L28:
        this.nextFrame[r5] = new ProfileMetaballView.BlurBitmapHolder(r02, r2);
    L30:
        if (this.nextFrame[r5].isBusy == false) goto L34;
        if (r3 != 1) goto L33;
        this.sizeChanged = true;
        goto L34
    L33:
        this.needNewFrame = true;
    L34:
        r5 = r5 + 1;
        goto L18
    L25:
        if (r62.canUse(r02, r2) == true) goto L30;
    L35:
        View r03 = this.view.getItemViewAt(this.currentPosition);
        drawView(r03, 0);
        if (r3 != 1) goto L42;
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r04 = this.listeners[0];
        if (r04 == null) goto L41;
        r04.listenInvalidate(this.invalidateTask);
    L41:
        return !this.sizeChanged;
    L42:
        View r22 = this.view.getItemViewAt(this.currentPosition + 1);
        drawView(r22, 1);
        if (this.offset != 0) goto L45;
        drawView(this.view.getItemViewAt(this.currentPosition - 1), 2);
    L45:
        int r32 = 0;
    L46:
        SizeNotifierFrameLayout.IViewWithInvalidateCallback[] r52 = this.listeners;
        if (r32 >= r52.length) goto L52;
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r53 = r52[r32];
        if (r53 == null) goto L51;
        r53.listenInvalidate(this.invalidateTask);
    L51:
        r32 = r32 + 1;
        goto L46
    L52:
        if (r03 == null) goto L56;
        if (r52[0] != null) goto L56;
    L60:
        r1 = true;
    L61:
        this.loopInvalidate = r1;
        return true;
    L56:
        if (this.offset == 0) goto L61;
        if (r22 == null) goto L61;
        if (r52[1] != null) goto L61;
    L14:
        if (this.needNewFrame == true) goto L16;
        r3 = 1;
        goto L17
    }

    private void doBlur() {
        Object r1 = this.lock;
        monitor-enter(r1);
        ProfileMetaballView.BlurBitmapHolder[] r0 = this.nextFrame;     // Catch: Throwable -> L55
        ProfileMetaballView.BlurBitmapHolder r3 = r0[0];     // Catch: Throwable -> L55
        ProfileMetaballView.BlurBitmapHolder[] r4 = this.currentFrame;     // Catch: Throwable -> L55
        ProfileMetaballView.BlurBitmapHolder[] r02 = {r3, r4[0], r0[1], r4[1], r0[2], r4[2]};     // Catch: Throwable -> L55
        monitor-exit(r1);     // Catch: Throwable -> L55
        int r12 = 0;
        boolean r32 = false;
    L8:
        if (r12 >= 6) goto L39;
        ProfileMetaballView.BlurBitmapHolder r42 = r02[r12];
        ProfileMetaballView.BlurBitmapHolder r5 = r02[r12 + 1];
        if (r42 == null) goto L38;
        if (r42.destroying == true) goto L38;
        if (r42.hasContent == false) goto L38;
        r42.lock();
        if (r5 != null) goto L18;
    L19:
        if (r5 == null) goto L21;
        r5.recycle();
    L21:
        r5 = new ProfileMetaballView.BlurBitmapHolder(r42);
        Object r6 = this.lock;
        monitor-enter(r6);
        this.currentFrame[indexOf(r42)] = r5;     // Catch: Throwable -> L34
        monitor-exit(r6);     // Catch: Throwable -> L34
    L25:
        Bitmap r33 = r42.bitmap;
        Utilities.stackBlurBitmap(r33, Math.max(10, r33.getWidth() / 180));
        Object r7 = this.lock;
        monitor-enter(r7);
        r5.clear();     // Catch: Throwable -> L30
        r5.canvas.drawBitmap(r33, 0.0f, 0.0f, null);     // Catch: Throwable -> L30
        r5.ready();     // Catch: Throwable -> L30
        applyShader(r5.bitmap, indexOf(r42));     // Catch: Throwable -> L30
        monitor-exit(r7);     // Catch: Throwable -> L30
        r42.clear();
        r42.unlock();
        r32 = true;
    L30:
        th = move-exception;
        throw th;
    L34:
        th = move-exception;
        throw th;
    L18:
        if (r5.canUse(r42) == true) goto L25;
    L38:
        r12 = r12 + 2;
        goto L8
    L39:
        if (r32 == false) goto L46;
        if (this.isBluring == false) goto L46;
        if (this.view == null) goto L46;
        postInvalidateOnAnimation();
    L46:
        if (this.isBluring == true) goto L48;
    L53:
        this.isBluring = false;
        return;
    L48:
        if (this.loopInvalidate == false) goto L50;
    L51:
        AndroidUtilities.runOnUIThread(new ProfileGalleryBlurView$$ExternalSyntheticLambda3(this));
        return;
    L50:
        if (this.needNewFrame == false) goto L53;
    L55:
        th = move-exception;
        throw th;
    }

    private void drawOpeningImageRenderNode(ImageReceiver r4, Canvas r5, float r6, float r7) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r3 = r4.getRoundRadius()[0];
        r4.setRoundRadius(0);
        r5.save();
        r5.translate(0.0f, r6 - r7);
        r4.draw(r5);
        r5.restore();
        r5.save();
        r5.scale(1.0f, -1.0f);
        r5.translate(0.0f, (-r7) - r6);
        r5.scale(1.0f, 2.0f, 0.0f, r7);
        r4.draw(r5);
        r5.restore();
        r4.setRoundRadius(r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void drawView(View r5, int r6) {
        ProfileMetaballView.BlurBitmapHolder r0 = this.nextFrame[r6];
        if (r5 != 0) goto L5;
    L7:
        if (r6 != 0) goto L9;
    L14:
        boolean r02 = r5 instanceof SizeNotifierFrameLayout.IViewWithInvalidateCallback;
        SizeNotifierFrameLayout.IViewWithInvalidateCallback[] r4 = this.listeners;
        if (r02 == false) goto L18;
        r4[r6] = (SizeNotifierFrameLayout.IViewWithInvalidateCallback) r5;
        return;
    L18:
        r4[r6] = null;
        return;
    L9:
        if (this.offset != 0) goto L11;
        return;
    L11:
        if (r6 == 1) goto L14;
        return;
    L5:
        if (r0.isBusy == true) goto L7;
        Canvas r1 = r0.canvas;
        r1.save();
        r1.scale(0.16666667f, 0.16666667f);
        r1.translate(0.0f, this.size - r5.getMeasuredHeight());
        r5.draw(r1);
        r1.restore();
        r0.ready();
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void drawViewWithRenderNode(Canvas r7, int r8) {
        View r0 = this.view.getItemViewAt(this.currentPosition + r8);
        if (r0 == 0) goto L5;
        int r1 = r0.getMeasuredHeight();
        r7.save();
        r7.translate(0.0f, this.size - r1);
        r0.draw(r7);
        r7.restore();
        r7.save();
        r7.scale(1.0f, -1.0f);
        r7.translate(0.0f, (-r1) - this.size);
        r7.scale(1.0f, 2.0f, 0.0f, r1);
        r0.draw(r7);
        r7.restore();
    L5:
        boolean r72 = r0 instanceof SizeNotifierFrameLayout.IViewWithInvalidateCallback;
        SizeNotifierFrameLayout.IViewWithInvalidateCallback[] r12 = this.listeners;
        if (r72 == false) goto L9;
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r02 = (SizeNotifierFrameLayout.IViewWithInvalidateCallback) r0;
        r12[r8] = r02;
        r02.listenInvalidate(this.invalidateTask);
        return;
    L9:
        r12[r8] = null;
    }

    private float getBlurRadius() {
        int r1 = SharedConfig.getDevicePerformanceClass();
        if (r1 != 1) goto L5;
        return 12.0f;
    L5:
        if (r1 == 2) goto L8;
        return 8.0f;
    L8:
        return 20.0f;
    }

    private float getRenderNodeScale() {
        return AndroidUtilities.dp(1.0f);
    }

    private int indexOf(ProfileMetaballView.BlurBitmapHolder r5) {
        int r1 = 0;
    L3:
        ProfileMetaballView.BlurBitmapHolder[] r2 = this.nextFrame;
        if (r1 >= r2.length) goto L9;
        if (r2[r1] == r5) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r1;
    L9:
        return 0;
    }

    private void initActionsRenderNode() {
        if (this.actionsView != null) goto L9;
        if (this.musicView != null) goto L9;
        this.shouldBlurActions = false;
        return;
    L9:
        if (this.actionsBlurNode != null) goto L11;
        this.actionsBlurNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("profileActionsBlurNode");
        ColorMatrix r0 = new ColorMatrix();
        AndroidUtilities.adjustSaturationColorMatrix(r0, 0.65f);
        AndroidUtilities.multiplyBrightnessColorMatrix(r0, 0.5f);
        this.actionsBlurNode.setRenderEffect(RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(r0)));
    L11:
        this.shouldBlurActions = true;
    }

    private void initRenderNode() {
        if (this.blurNode != null) goto L6;
        float r0 = getRenderNodeScale();
        this.blurNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("profileBlurNode");
        float[] r8 = {0.0f, AndroidUtilities.dpf2(56.0f) / this.size};
        Shader.TileMode r9 = Shader.TileMode.CLAMP;
        LinearGradient r2 = new LinearGradient(0.0f, 0.0f, 0.0f, this.size / r0, new int[]{0, -1}, r8, r9);
        float r02 = getBlurRadius();
        this.blurNode.setRenderEffect(RenderEffect.createBlendModeEffect(RenderEffect.createBlurEffect(r02, r02, r9), RenderEffect.createShaderEffect(r2), ProfileGalleryBlurView$$ExternalSyntheticApiModelOutline0.m()));
        return;
    }

    private /* synthetic */ void lambda$doBlur$0() {
        captureNextFrame();
        ProfileMetaballView.profileBlurQueue.postRunnable(this.blurTask);
    }

    private void swap(int r5, int r6, int r7) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ProfileMetaballView.BlurBitmapHolder[] r1 = this.nextFrame;     // Catch: Throwable -> L9
        ProfileMetaballView.BlurBitmapHolder r2 = r1[r5];     // Catch: Throwable -> L9
        r1[r5] = r1[r6];     // Catch: Throwable -> L9
        r1[r6] = r2;     // Catch: Throwable -> L9
        ProfileMetaballView.BlurBitmapHolder[] r12 = this.currentFrame;     // Catch: Throwable -> L9
        ProfileMetaballView.BlurBitmapHolder r22 = r12[r5];     // Catch: Throwable -> L9
        r12[r5] = r12[r6];     // Catch: Throwable -> L9
        r12[r6] = r22;     // Catch: Throwable -> L9
        if (r5 == 2) goto L7;
        Paint[] r13 = this.paints;     // Catch: Throwable -> L9
        Paint r23 = r13[r5];     // Catch: Throwable -> L9
        r13[r5] = r13[r6];     // Catch: Throwable -> L9
        r13[r6] = r23;     // Catch: Throwable -> L9
    L13:
        if (r7 == (-1)) goto L19;
        this.paints[r7].setShader(null);     // Catch: Throwable -> L9
        ProfileMetaballView.BlurBitmapHolder r4 = this.nextFrame[r7];     // Catch: Throwable -> L9
        if (r4 == null) goto L19;
        if (r4.isBusy == true) goto L19;
        r4.clear();     // Catch: Throwable -> L9
    L19:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L7:
        if (r22.hasContent == false) goto L13;
        applyShader(r22.bitmap, r6);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    private void updateContent() {
        this.needNewFrame = true;
        postInvalidateOnAnimation();
    }

    public void destroy() {
        ProfileGalleryView r0 = this.view;
        if (r0 == null) goto L5;
        r0.removeOnPageChangeListener(this.listener);
        this.view = null;
    L5:
        this.isBluring = false;
        ProfileMetaballView.profileBlurQueue.cancelRunnable(this.blurTask);
        if (Build.VERSION.SDK_INT < 29) goto L13;
        RenderNode r2 = this.blurNode;
        if (r2 == null) goto L10;
        r2.discardDisplayList();
        this.blurNode = null;
    L10:
        RenderNode r22 = this.actionsBlurNode;
        if (r22 == null) goto L13;
        r22.discardDisplayList();
        this.actionsBlurNode = null;
    L13:
        this.actionsView = null;
        this.musicView = null;
        Object r23 = this.lock;
        monitor-enter(r23);
        int r3 = 0;
    L17:
        if (r3 >= 3) goto L30;
        ProfileMetaballView.BlurBitmapHolder r4 = this.nextFrame[r3];     // Catch: Throwable -> L21
        if (r4 == null) goto L23;
        r4.recycle();     // Catch: Throwable -> L21
        this.nextFrame[r3] = null;     // Catch: Throwable -> L21
    L23:
        ProfileMetaballView.BlurBitmapHolder r42 = this.currentFrame[r3];     // Catch: Throwable -> L21
        if (r42 == null) goto L26;
        r42.recycle();     // Catch: Throwable -> L21
        this.currentFrame[r3] = null;     // Catch: Throwable -> L21
    L26:
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r43 = this.listeners[r3];     // Catch: Throwable -> L21
        if (r43 == null) goto L29;
        r43.listenInvalidate(null);     // Catch: Throwable -> L21
        this.listeners[r3] = null;     // Catch: Throwable -> L21
    L29:
        r3 = r3 + 1;     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        throw th;
    L30:
        this.paints[0].setShader(null);     // Catch: Throwable -> L21
        this.paints[1].setShader(null);     // Catch: Throwable -> L21
        monitor-exit(r23);     // Catch: Throwable -> L21
    }

    public void draw(Canvas r19, ProfileActivity.AvatarImageView r20, float r21, float r22, boolean r23, float r24, float r25) {
        ProfileGalleryView r1 = this.view;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isAttachedToWindow() == true) goto L7;
        return;
    L7:
        if (this.view.getVisibility() != 8) goto L10;
        return;
    L10:
        if (this.usingRenderNode == true) goto L12;
    L31:
        ProfileGalleryBlurView r9 = this;
        Canvas r0 = r19;
    L32:
        ProfileActionsView r12 = r9.actionsView;
        if (r12 == null) goto L35;
        r12.drawingBlur(false);
    L35:
        ProfileMusicView r13 = r9.musicView;
        if (r13 == null) goto L39;
        r13.drawingBlur(false);
    L39:
        if (r9.needNewFrame == false) goto L41;
    L50:
        boolean r14 = r9.captureNextFrame();
        if (r9.isBluring == true) goto L55;
        if (r14 == false) goto L55;
        r9.isBluring = true;
        DispatchQueue r15 = ProfileMetaballView.profileBlurQueue;
        r15.cancelRunnable(r9.blurTask);
        r15.postRunnable(r9.blurTask);
    L55:
        if (r9.paints[0].getShader() == null) goto L57;
    L59:
        Object r10 = r9.lock;
        monitor-enter(r10);
        float r11 = r21 / r9.frameWidth;     // Catch: Throwable -> L64
        if (r23 == false) goto L66;
        r0.translate(0.0f, (-r11) * r9.frameHeight);     // Catch: Throwable -> L64
    L66:
        r0.scale(r11, r11);     // Catch: Throwable -> L64
        float r122 = r9.actionSize / r11;     // Catch: Throwable -> L64
        if (r9.paints[0].getShader() == null) goto L69;
        r0.save();     // Catch: Throwable -> L64
        r0.translate((-r9.offset) / r11, 0.0f);     // Catch: Throwable -> L64
        r0.save();     // Catch: Throwable -> L64
        r0.scale(1.0f, 2.0f, 0.0f, r9.frameHeight);     // Catch: Throwable -> L64
        int r16 = r9.frameHeight;     // Catch: Throwable -> L64
        char r17 = 1;
        float r7 = 1.0f;
        r0.drawRect(0.0f, r16, r9.frameWidth, r16 + r122, r9.paints[0]);     // Catch: Throwable -> L64
        r19.restore();     // Catch: Throwable -> L64
        r9.paints[0].setAlpha((int) (r25 * 255.0f));     // Catch: Throwable -> L64
        int r02 = r9.frameHeight;     // Catch: Throwable -> L64
        r0 = r19;
        r0.drawRect(0.0f, r02 * r24, r9.frameWidth, r02, r9.paints[0]);     // Catch: Throwable -> L64
        r9.paints[0].setAlpha(255);     // Catch: Throwable -> L64
        r0.restore();     // Catch: Throwable -> L64
    L71:
        if (r9.offset != 0) goto L73;
    L75:
        monitor-exit(r10);     // Catch: Throwable -> L64
        return;
    L73:
        if (r9.paints[r17].getShader() == null) goto L75;
        r0.save();     // Catch: Throwable -> L64
        r0.translate(((-r9.offset) + r21) / r11, 0.0f);     // Catch: Throwable -> L64
        r0.save();     // Catch: Throwable -> L64
        r0.scale(r7, 2.0f, 0.0f, r9.frameHeight);     // Catch: Throwable -> L64
        int r18 = r9.frameHeight;     // Catch: Throwable -> L64
        r0.drawRect(0.0f, r18, r9.frameWidth, r18 + r122, r9.paints[r17]);     // Catch: Throwable -> L64
        r19.restore();     // Catch: Throwable -> L64
        r9.paints[r17].setAlpha((int) (r25 * 255.0f));     // Catch: Throwable -> L64
        int r03 = r9.frameHeight;     // Catch: Throwable -> L64
        r19.drawRect(0.0f, r03 * r24, r9.frameWidth, r03, r9.paints[r17]);     // Catch: Throwable -> L64
        r9.paints[r17].setAlpha(255);     // Catch: Throwable -> L64
        r19.restore();     // Catch: Throwable -> L64
        goto L75
    L69:
        r17 = 1;
        r7 = 1.0f;
    L64:
        th = move-exception;
        throw th;
    L57:
        if (r9.paints[1].getShader() != null) goto L59;
        return;
    L41:
        if (r9.sizeChanged == true) goto L50;
        if (r9.loopInvalidate == true) goto L50;
        if (r9.paints[0].getShader() != null) goto L55;
        if (r9.paints[1].getShader() != null) goto L55;
        if (r9.isBluring == true) goto L55;
    L12:
        if (Build.VERSION.SDK_INT < 31) goto L31;
        if (r19.isHardwareAccelerated() == false) goto L25;
        if (r20 == null) goto L17;
    L22:
        if (r20 == null) goto L83;
        drawOpeningRenderNode(r20, r19, r21, r22, r24, r25);
        return;
    L83:
        return;
    L17:
        if (getVisibility() != 0) goto L22;
        if (getAlpha() <= 0.0f) goto L22;
        drawRenderNode(r19, r21);
        return;
    L25:
        r9 = this;
        r0 = r19;
        if (r20 == null) goto L28;
        return;
    L28:
        if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L84;
        r9.usingRenderNode = false;
        r9.setLayerType(1, r9.paints[0]);
        r9.setLayerType(1, r9.paints[1]);
        goto L32
    }

    public void drawOpeningRenderNode(ProfileActivity.AvatarImageView r9, Canvas r10, float r11, float r12, float r13, float r14) {
        float r132 = 1.0f - r13;
        float r1 = r11 / this.view.getMeasuredWidth();
        int r2 = this.size;
        float r3 = r2 * r132;
        float r22 = (r2 * r132) * r1;
        float r133 = getRenderNodeScale() * r1;
        initRenderNode();
        this.blurNode.setPosition(0, 0, (int) (r11 / r133), (int) ((this.actionSize + r3) / r133));
        RecordingCanvas r4 = this.blurNode.beginRecording();
        float r0 = 1.0f / r133;
        r4.scale(r0, r0);
        AnimatedEmojiDrawable r02 = r9.animatedEmojiDrawable;
        if (r02 == null) goto L5;
        ImageReceiver r03 = r02.getImageReceiver();
    L6:
        drawOpeningImageRenderNode(r03, r4, r22, r12);
        if (r9.drawForeground == true) goto L9;
    L11:
        this.blurNode.endRecording();
        this.blurNode.setAlpha(r14);
        r10.translate(0.0f, -r22);
        r10.scale(r133, r133);
        r10.drawRenderNode(this.blurNode);
        captureActionsBlurRenderNode(r11, r9, r1, r3);
        return;
    L9:
        if (r9.foregroundAlpha <= 0.0f) goto L11;
        drawOpeningImageRenderNode(r9.foregroundImageReceiver, r4, r22, r12);
        goto L11
    L5:
        r03 = r9.imageReceiver;
        goto L6
    }

    public void drawRenderNode(Canvas r9, float r10) {
        initRenderNode();
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r0 = this.listeners[0];
        if (r0 == null) goto L5;
        r0.listenInvalidate(null);
    L5:
        SizeNotifierFrameLayout.IViewWithInvalidateCallback r02 = this.listeners[1];
        if (r02 == null) goto L8;
        r02.listenInvalidate(null);
    L8:
        float r03 = getRenderNodeScale();
        this.blurNode.setPosition(0, 0, (int) (r10 / r03), (int) ((this.size + this.actionSize) / r03));
        RecordingCanvas r4 = this.blurNode.beginRecording();
        float r6 = 1.0f / r03;
        r4.scale(r6, r6);
        r4.save();
        r4.translate(-this.offset, 0.0f);
        drawViewWithRenderNode(r4, 0);
        r4.restore();
        if (this.offset == 0) goto L11;
        r4.save();
        r4.translate((-this.offset) + r10, 0.0f);
        drawViewWithRenderNode(r4, 1);
        r4.restore();
    L11:
        this.blurNode.endRecording();
        this.blurNode.setAlpha(this.alpha.set(1.0f));
        r9.save();
        r9.scale(r03, r03);
        r9.drawRenderNode(this.blurNode);
        r9.restore();
        if (getVisibility() == 0) goto L14;
        return;
    L14:
        if (getAlpha() <= 0.0f) goto L18;
        captureActionsBlurRenderNode(r10, null, 1.0f, this.size);
        return;
    }

    public boolean isUsingRenderNode() {
        return this.usingRenderNode;
    }

    public void notifyUpdateSize() {
        this.sizeChanged = true;
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        draw(r10, null, this.view.getMeasuredWidth(), this.view.getMeasuredHeight(), false, 0.0f, 1.0f);
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.getSize(r2), this.size + this.actionSize);
    }

    public void restartAlpha() {
        if (this.usingRenderNode == false) goto L6;
        this.alpha.set(0.0f, true);
        invalidate();
        return;
    }

    public void setActionsView(ProfileActionsView r1) {
        this.actionsView = r1;
    }

    @Override // android.view.View
    public void setAlpha(float r2) {
        super.setAlpha(r2);
        if (r2 != 0.0f) goto L5;
        return;
    L5:
        if (this.usingRenderNode == false) goto L9;
        invalidate();
        return;
    }

    public void setMusicView(ProfileMusicView r1) {
        this.musicView = r1;
    }

    public void setSize(int r2) {
        if (this.actionSize == r2) goto L5;
        invalidate();
    L5:
        this.actionSize = r2;
        this.size = (int) (AndroidUtilities.dp(64.0f) * 1.5f);
    }

    public void setSuggestionView(ProfileSuggestionView r1) {
    }

    public void setView(ProfileGalleryView r2) {
        destroy();
        this.view = r2;
        this.currentPosition = r2.getCurrentItem();
        this.offset = 0;
        r2.addOnPageChangeListener(this.listener);
    }
}
