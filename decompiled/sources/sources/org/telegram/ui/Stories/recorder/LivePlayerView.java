package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Stories.LivePlayer;
import org.telegram.ui.Stories.PeerStoriesView;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.TextureViewRenderer;
import org.webrtc.VideoSink;

/* loaded from: classes7.dex */
public class LivePlayerView extends FrameLayout implements RendererCommon.RendererEvents, NotificationCenter.NotificationCenterDelegate {
    private final TextureView blurRenderer;
    private int currentAccount;
    private long dialogId;
    public final EmptyView emptyView;
    private Runnable firstFrameCallback;
    private boolean firstFrameRendered;
    private boolean ignoreLayout;
    private boolean isEmptyViewVisible;
    private float keyboardOffset;
    private View placeholderView;
    private PeerStoriesView.VideoPlayerSharedScope scope;
    public final SurfaceViewRenderer surfaceView;
    public final TextureViewRenderer textureView;
    private boolean textureVisible;
    public final BackupImageView thumb;

    public static class EmptyView extends FrameLayout {
        public final ButtonWithCounterView buttonView;
        private boolean hasSetImage;
        public final BackupImageView imageView;
        public final LinearLayout layout;
        public final TextView textView;

        /* renamed from: $r8$lambda$hSJLXY4uSxrNh54uM-zLml8e9oY, reason: not valid java name */
        public static /* synthetic */ void m20051$r8$lambda$hSJLXY4uSxrNh54uMzLml8e9oY(View r0) {
        }

        public EmptyView(Context r11) {
            super(r11);
            LinearLayout r0 = new LinearLayout(r11);
            this.layout = r0;
            r0.setOrientation(1);
            addView(r0, LayoutHelper.createFrame(-2, -2, 17));
            BackupImageView r2 = new BackupImageView(r11);
            this.imageView = r2;
            r0.addView(r2, LayoutHelper.createLinear(130, 130, 1));
            TextView r22 = new TextView(r11);
            this.textView = r22;
            r22.setTextColor(-1);
            r22.setText(LocaleController.getString(R.string.LiveStoryDisconnected));
            r22.setTextSize(1, 20.0f);
            r22.setTypeface(AndroidUtilities.bold());
            r0.addView(r22, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
            ButtonWithCounterView r1 = new ButtonWithCounterView(r11, null);
            this.buttonView = r1;
            r1.setText(LocaleController.getString(R.string.LiveStoryDisconnectedContinue), false);
            r0.addView(r1, LayoutHelper.createLinear((int) ((r1.text.getWidth() + AndroidUtilities.dp(24.0f)) / AndroidUtilities.density), 38, 1, 0, 18, 0, 0));
            r1.setOnClickListener(new LivePlayerView$EmptyView$$ExternalSyntheticLambda0());
            setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-16777216, -11184811}));
        }

        @Override // android.view.View
        public void setVisibility(int r4) {
            super.setVisibility(r4);
            if (r4 == 0) goto L5;
            return;
        L5:
            if (this.hasSetImage == true) goto L9;
            RLottieDrawable r42 = new RLottieDrawable(R.raw.utyan_empty2, AndroidUtilities.dp(130.0f), AndroidUtilities.dp(130.0f));
            this.imageView.setImageDrawable(r42);
            this.hasSetImage = true;
            return;
        }
    }

    /* renamed from: $r8$lambda$0xT-7p-sv3Asn8UGk1sOS4sI9nk, reason: not valid java name */
    public static /* synthetic */ void m20049$r8$lambda$0xT7psv3Asn8UGk1sOS4sI9nk(Runnable r0, View r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$ic7-EVrUbB5FKysRvOLHPE000J4, reason: not valid java name */
    public static /* synthetic */ void m20050$r8$lambda$ic7EVrUbB5FKysRvOLHPE000J4(LivePlayerView r0, boolean r1) {
        r0.lambda$setIsEmpty$0(r1);
    }

    public LivePlayerView(Context r5, int r6, boolean r7) {
        super(r5);
        this.currentAccount = r6;
        BackupImageView r62 = new BackupImageView(r5);
        this.thumb = r62;
        r62.setAlpha(0.75f);
        addView(r62, LayoutHelper.createFrame(-1, -1, 119));
        TextureView r63 = new TextureView(r5);
        this.blurRenderer = r63;
        addView(r63, LayoutHelper.createFrame(-1, -1, 119));
        if (r7 == false) goto L5;
        SurfaceViewRenderer r72 = new SurfaceViewRenderer(r5);
        this.surfaceView = r72;
        addView(r72, LayoutHelper.createFrame(-1, -1, 119));
        r72.setAlpha(1.0f);
        this.textureView = null;
    L6:
        EmptyView r64 = new EmptyView(r5);
        this.emptyView = r64;
        r64.setAlpha(0.0f);
        r64.setVisibility(8);
        addView(r64, LayoutHelper.createFrame(-1, -1, 119));
        return;
    L5:
        TextureViewRenderer r73 = new TextureViewRenderer(r5);
        this.textureView = r73;
        r73.setOpaque(false);
        r73.setEnableHardwareScaler(true);
        r73.setIsCamera(true);
        r73.setRotateTextureWithScreen(true);
        r73.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT);
        addView(r73, LayoutHelper.createFrame(-1, -1, 119));
        r73.setAlpha(1.0f);
        this.surfaceView = null;
        goto L6
    }

    private /* synthetic */ void lambda$setIsEmpty$0(boolean r1) {
        EmptyView r0 = this.emptyView;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    private void updateTranslations() {
        int r0 = getMeasuredWidth();
        int r1 = getMeasuredHeight();
        if (isAttachedToWindow() == false) goto L13;
        if (r0 <= 0) goto L14;
        if (r1 <= 0) goto L15;
        View r2 = this.textureView;
        if (r2 != null) goto L11;
        r2 = this.surfaceView;
    L11:
        int r3 = this.blurRenderer.getMeasuredWidth();
        int r4 = this.blurRenderer.getMeasuredHeight();
        this.blurRenderer.setPivotX(0.0f);
        this.blurRenderer.setPivotY(0.0f);
        float r02 = r0;
        float r32 = r3;
        float r12 = r1;
        float r42 = r4;
        float r5 = Math.max(r02 / r32, r12 / r42);
        this.blurRenderer.setScaleX(r5);
        this.blurRenderer.setScaleY(r5);
        this.blurRenderer.setTranslationX((r02 - (r32 * r5)) / 2.0f);
        this.blurRenderer.setTranslationY(((r12 - (r42 * r5)) / 2.0f) - (this.keyboardOffset / 2.0f));
        float r33 = r2.getMeasuredWidth();
        float r43 = r2.getMeasuredHeight();
        float r52 = Math.max(r33 / r02, r43 / r12);
        r2.setScaleX(r52);
        r2.setScaleY(r52);
        r2.setTranslationX((r02 - (r33 * r52)) / 2.0f);
        r2.setTranslationY(((r12 - (r43 * r52)) / 2.0f) - (this.keyboardOffset / 2.0f));
        return;
    L15:
        return;
    L14:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.liveStoryUpdated) goto L16;
        long r32 = ((Long) r5[0]).longValue();
        PeerStoriesView.VideoPlayerSharedScope r52 = this.scope;
        if (r52 == null) goto L17;
        LivePlayer r53 = r52.livePlayer;
        if (r53 != null) goto L9;
        return;
    L9:
        if (r53.getCallId() != r32) goto L19;
        boolean r33 = this.scope.livePlayer.isEmptyStream();
        if (this.scope.livePlayer.canContinueEmptyStream() == false) goto L13;
        LivePlayer r42 = this.scope.livePlayer;
        Objects.requireNonNull(r42);
        LivePlayerView$$ExternalSyntheticLambda0 r54 = new LivePlayerView$$ExternalSyntheticLambda0(r42);
    L14:
        setIsEmpty(r33, r54);
        return;
    L13:
        r54 = null;
        goto L14
    L19:
        return;
    L17:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
    }

    @Override // android.view.View
    public void draw(Canvas r4) {
        if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L10;
        TextureView r0 = this.blurRenderer;
        if (r0 == null) goto L12;
        Bitmap r02 = r0.getBitmap();
        if (r02 == null) goto L13;
        r4.save();
        r4.translate(this.blurRenderer.getX(), this.blurRenderer.getY());
        r4.scale((this.blurRenderer.getWidth() * this.blurRenderer.getScaleX()) / r02.getWidth(), (this.blurRenderer.getHeight() * this.blurRenderer.getScaleY()) / r02.getHeight());
        r4.drawBitmap(r02, 0.0f, 0.0f, null);
        r4.restore();
        return;
    L13:
        return;
    L12:
        return;
    L10:
        super.draw(r4);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L17;
        TextureViewRenderer r0 = this.textureView;
        if (r6 != r0) goto L10;
        Bitmap r62 = r0.getBitmap();
        if (r62 == null) goto L9;
        r5.save();
        r5.translate(this.textureView.getX(), this.textureView.getY());
        r5.scale((this.textureView.getWidth() * this.textureView.getScaleX()) / r62.getWidth(), (this.textureView.getHeight() * this.textureView.getScaleY()) / r62.getHeight());
        r5.drawBitmap(r62, 0.0f, 0.0f, null);
        r5.restore();
    L9:
        return true;
    L10:
        TextureView r02 = this.blurRenderer;
        if (r6 != r02) goto L17;
        Bitmap r63 = r02.getBitmap();
        if (r63 == null) goto L15;
        r5.save();
        r5.translate(this.blurRenderer.getX(), this.blurRenderer.getY());
        r5.scale((this.blurRenderer.getWidth() * this.blurRenderer.getScaleX()) / r63.getWidth(), (this.blurRenderer.getHeight() * this.blurRenderer.getScaleY()) / r63.getHeight());
        r5.drawBitmap(r63, 0.0f, 0.0f, null);
        r5.restore();
    L15:
        return true;
    L17:
        return super.drawChild(r5, r6, r7);
    }

    public Bitmap getBitmap() {
        TextureViewRenderer r0 = this.textureView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getBitmap();
    }

    public View getPlaceholderView() {
        if (this.placeholderView != null) goto L6;
        View r0 = new View(getContext());
        this.placeholderView = r0;
        addView(r0, LayoutHelper.createFrameMatchParent());
    L6:
        return this.placeholderView;
    }

    public VideoSink getSink() {
        TextureViewRenderer r0 = this.textureView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        SurfaceViewRenderer r1 = this.surfaceView;
        if (r1 == null) goto L8;
        return r1;
    L8:
        return null;
    }

    public View getTextureView() {
        TextureViewRenderer r0 = this.textureView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        SurfaceViewRenderer r1 = this.surfaceView;
        if (r1 == null) goto L8;
        return r1;
    L8:
        return null;
    }

    public boolean isAvailable() {
        TextureViewRenderer r0 = this.textureView;
        if (r0 == null) goto L7;
        return r0.isAvailable();
    L7:
        if (this.surfaceView == null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        SurfaceViewRenderer r0 = this.surfaceView;
        if (r0 == null) goto L5;
        r0.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), this);
    L5:
        TextureViewRenderer r02 = this.textureView;
        if (r02 == null) goto L8;
        r02.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), this);
        this.textureView.setBackgroundRenderer(this.blurRenderer);
    L8:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.liveStoryUpdated);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.firstFrameRendered = false;
        setTextureVisible(false, false);
        SurfaceViewRenderer r0 = this.surfaceView;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        TextureViewRenderer r02 = this.textureView;
        if (r02 == null) goto L8;
        r02.release();
    L8:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.liveStoryUpdated);
    }

    @Override // android.view.View
    public void onDraw(Canvas r1) {
        super.onDraw(r1);
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFirstFrameRendered() {
        if (this.firstFrameRendered == true) goto L10;
        PeerStoriesView.VideoPlayerSharedScope r0 = this.scope;
        if (r0 != null) goto L7;
    L9:
        this.firstFrameRendered = true;
        goto L10
    L7:
        if (r0.firstFrameRendered == true) goto L9;
        r0.firstFrameRendered = true;
        r0.invalidate();
    L10:
        setTextureVisible(true, true);
        Runnable r02 = this.firstFrameCallback;
        if (r02 == null) goto L14;
        r02.run();
        this.firstFrameCallback = null;
        return;
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFrameResolutionChanged(int r1, int r2, int r3) {
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        int r42 = r4 - r2;
        int r52 = r5 - r3;
        this.thumb.layout(0, 0, r42, r52);
        this.emptyView.layout(0, 0, r42, r52);
        View r12 = this.placeholderView;
        if (r12 == null) goto L5;
        r12.layout(0, 0, r42, r52);
    L5:
        TextureView r13 = this.blurRenderer;
        r13.layout(0, 0, r13.getMeasuredWidth(), this.blurRenderer.getMeasuredHeight());
        View r14 = this.textureView;
        if (r14 != null) goto L9;
        r14 = this.surfaceView;
    L9:
        r14.layout(0, 0, r14.getMeasuredWidth(), r14.getMeasuredHeight());
        updateTranslations();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        this.ignoreLayout = true;
        Display r0 = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        TextureViewRenderer r1 = this.textureView;
        if (r1 == null) goto L5;
        r1.setScreenRotation(r0.getRotation());
    L5:
        this.ignoreLayout = false;
        super.onMeasure(r4, r5);
        View r02 = this.textureView;
        if (r02 != null) goto L9;
        r02 = this.surfaceView;
    L9:
        ViewGroup.LayoutParams r12 = this.blurRenderer.getLayoutParams();
        r12.width = r02.getMeasuredWidth();
        ViewGroup.LayoutParams r13 = this.blurRenderer.getLayoutParams();
        r13.height = r02.getMeasuredHeight();
        super.onMeasure(r4, r5);
        TextureViewRenderer r3 = this.textureView;
        if (r3 == null) goto L13;
        r3.updateRotation();
        return;
    }

    public void release() {
        TextureViewRenderer r0 = this.textureView;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        SurfaceViewRenderer r02 = this.surfaceView;
        if (r02 == null) goto L8;
        r02.release();
    L8:
        this.firstFrameRendered = false;
        setTextureVisible(false, false);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void reset() {
        SurfaceViewRenderer r0 = this.surfaceView;
        if (r0 == null) goto L5;
        r0.clearImage();
    L5:
        TextureViewRenderer r02 = this.textureView;
        if (r02 == null) goto L8;
        r02.clearImage();
    L8:
        this.firstFrameRendered = false;
        setTextureVisible(false, false);
    }

    public void setAccount(int r3) {
        if (this.currentAccount != r3) goto L6;
        return;
    L6:
        if (isAttachedToWindow() == false) goto L9;
        NotificationCenter r0 = NotificationCenter.getInstance(this.currentAccount);
        int r1 = NotificationCenter.liveStoryUpdated;
        r0.removeObserver(this, r1);
        this.currentAccount = r3;
        NotificationCenter.getInstance(r3).addObserver(this, r1);
        return;
    L9:
        this.currentAccount = r3;
    }

    public void setIsEmpty(boolean r5, Runnable r6) {
        if (this.isEmptyViewVisible != r5) goto L5;
        return;
    L5:
        this.isEmptyViewVisible = r5;
        int r1 = 0;
        this.emptyView.setVisibility(0);
        ViewPropertyAnimator r0 = this.emptyView.animate();
        if (this.isEmptyViewVisible == false) goto L8;
        float r2 = 1.0f;
    L9:
        r0.alpha(r2).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).withEndAction(new LivePlayerView$$ExternalSyntheticLambda1(this, r5)).start();
        ButtonWithCounterView r02 = this.emptyView.buttonView;
        if (r5 == false) goto L13;
        if (r6 == null) goto L13;
    L14:
        r02.setVisibility(r1);
        ButtonWithCounterView r4 = this.emptyView.buttonView;
        if (r6 != null) goto L17;
        LivePlayerView$$ExternalSyntheticLambda2 r52 = null;
    L18:
        r4.setOnClickListener(r52);
        return;
    L17:
        r52 = new LivePlayerView$$ExternalSyntheticLambda2(r6);
    L13:
        r1 = 8;
        goto L14
    L8:
        r2 = 0.0f;
        goto L9
    }

    public void setKeyboardOffset(float r1) {
        this.keyboardOffset = r1;
        updateTranslations();
    }

    public void setOnFirstFrameCallback(Runnable r1) {
        this.firstFrameCallback = r1;
    }

    public void setScope(long r26, PeerStoriesView.VideoPlayerSharedScope r28) {
        if (r28 != null) goto L21;
        long r10 = this.dialogId;
        if (r10 == 0) goto L21;
        if (this.firstFrameRendered == false) goto L21;
        if (this.textureView == null) goto L21;
        File r0 = new File(FileLoader.getDirectory(4), "live" + r10 + ".jpg");
        Bitmap r102 = this.textureView.getBitmap();
        if (r102 == null) goto L21;
        Paint r11 = new Paint(3);
        if (r102.getWidth() <= r102.getHeight()) goto L15;
        int r12 = AndroidUtilities.dp(100.0f);
        int r13 = (int) ((r102.getHeight() / r102.getWidth()) * AndroidUtilities.dp(100.0f));
    L16:
        Bitmap r132 = Bitmap.createBitmap(r12, r13, Bitmap.Config.ARGB_8888);
        Canvas r14 = new Canvas(r132);
        float r122 = r12 / r102.getWidth();
        r14.scale(r122, r122);
        r14.drawBitmap(r102, 0.0f, 0.0f, r11);
        Utilities.stackBlurBitmap(r132, AndroidUtilities.dp(4.0f));
        r132.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(r0));     // Catch: Exception -> L19
    L19:
        e = move-exception;
        FileLog.e(e);
        goto L21
    L15:
        r13 = AndroidUtilities.dp(100.0f);
        r12 = (int) ((r102.getWidth() / r102.getHeight()) * AndroidUtilities.dp(100.0f));
    L21:
        boolean r103 = true;
        if (this.dialogId != r26) goto L24;
    L38:
        this.dialogId = r26;
        this.scope = r28;
        if (this.firstFrameRendered == false) goto L44;
        if (r28 == null) goto L44;
        if (r28.firstFrameRendered == true) goto L44;
        r28.firstFrameRendered = true;
        r28.invalidate();
    L44:
        if (r28 == null) goto L50;
        LivePlayer r02 = r28.livePlayer;
        if (r02 == null) goto L50;
        if (r02.isEmptyStream() == false) goto L50;
    L51:
        if (r28 == null) goto L57;
        LivePlayer r03 = r28.livePlayer;
        if (r03 == null) goto L57;
        if (r03.canContinueEmptyStream() == false) goto L57;
        LivePlayer r04 = r28.livePlayer;
        Objects.requireNonNull(r04);
        LivePlayerView$$ExternalSyntheticLambda0 r2 = new LivePlayerView$$ExternalSyntheticLambda0(r04);
    L58:
        setIsEmpty(r103, r2);
        return;
    L57:
        r2 = null;
    L50:
        r103 = false;
        goto L51
    L24:
        if (r26 != 0) goto L26;
        this.thumb.clearImage();
        goto L38
    L26:
        String r5 = new File(FileLoader.getDirectory(4), "live" + r26 + ".jpg").getAbsolutePath();
        int r6 = this.currentAccount;
        if (r26 <= 0) goto L33;
        TLRPC.User r05 = MessagesController.getInstance(r6).getUser(Long.valueOf(r26));
        ImageLocation r15 = ImageLocation.getForUser(this.currentAccount, r05, 1);
        if (r05 == null) goto L31;
        int r62 = AvatarDrawable.getColorForId(r05.id);
    L32:
        this.thumb.getImageReceiver().setImage(ImageLocation.getForPath(r5), "500_500_nocache", r15, "50_50_b2", null, null, new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{ColorUtils.blendARGB(r62, -16777216, 0.2f), ColorUtils.blendARGB(r62, -16777216, 0.4f)}), 0, null, r05, 0);
        goto L38
    L31:
        r62 = ColorUtils.blendARGB(-16777216, -1, 0.2f);
        goto L32
    L33:
        TLRPC.Chat r06 = MessagesController.getInstance(r6).getChat(Long.valueOf(-r26));
        ImageLocation r152 = ImageLocation.getForChat(this.currentAccount, r06, 1);
        if (r06 == null) goto L36;
        int r63 = AvatarDrawable.getColorForId(r06.id);
    L37:
        this.thumb.getImageReceiver().setImage(ImageLocation.getForPath(r5), "500_500_nocache", r152, "50_50_b2", null, null, new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{ColorUtils.blendARGB(r63, -16777216, 0.2f), ColorUtils.blendARGB(r63, -16777216, 0.4f)}), 0, null, r06, 0);
        goto L38
    L36:
        r63 = ColorUtils.blendARGB(-16777216, -1, 0.2f);
        goto L37
    }

    public void setSecure(boolean r1) {
        SurfaceViewRenderer r0 = this.surfaceView;
        if (r0 == null) goto L6;
        r0.setSecure(r1);
        return;
    }

    public void setTextureVisible(boolean r3, boolean r4) {
        if (this.textureVisible != r3) goto L6;
        if (r4 == false) goto L6;
        return;
    L6:
        float r0 = 0.0f;
        if (r4 == false) goto L13;
        ViewPropertyAnimator r2 = getTextureView().animate();
        if (r3 == false) goto L11;
        r0 = 1.0f;
    L11:
        r2.alpha(r0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
        return;
    L13:
        getTextureView().animate().cancel();
        View r22 = getTextureView();
        if (r3 == false) goto L16;
        r0 = 1.0f;
    L16:
        r22.setAlpha(r0);
    }
}
