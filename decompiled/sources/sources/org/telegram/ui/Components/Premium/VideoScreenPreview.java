package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.google.android.gms.cast.MediaError;
import j$.net.URLEncoder;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.video.VideoPlayerHolderBase;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AspectRatioFrameLayout;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.Premium.HelloParticles;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes7.dex */
public class VideoScreenPreview extends FrameLayout implements PagerHeaderView, NotificationCenter.NotificationCenterDelegate {
    private static final float[] speedScaleVideoTimestamps = null;
    boolean allowPlay;
    float aspectRatio;
    AspectRatioFrameLayout aspectRatioFrameLayout;
    String attachFileName;
    boolean attached;
    CellFlickerDrawable.DrawableInterface cellFlickerDrawable;
    int currentAccount;
    private TLRPC.Document document;
    File file;
    boolean firstFrameRendered;
    boolean fromTop;
    HelloParticles.Drawable helloParticlesDrawable;
    ImageReceiver imageReceiver;
    long lastFrameTime;
    private MatrixParticlesDrawable matrixParticlesDrawable;
    Runnable nextCheck;
    Paint phoneFrame1;
    Paint phoneFrame2;
    boolean play;
    float progress;
    private float roundRadius;
    RoundedBitmapDrawable roundedBitmapDrawable;
    int size;
    SpeedLineParticles$Drawable speedLinesDrawable;
    StarParticlesView.Drawable starDrawable;
    private final SvgHelper.SvgDrawable svgIcon;
    TextureView textureView;
    int type;
    VideoPlayerHolderBase videoPlayerBase;
    boolean visible;

    /* renamed from: org.telegram.ui.Components.Premium.VideoScreenPreview$1, reason: invalid class name */
    public class AnonymousClass1 extends AspectRatioFrameLayout {
        private final Path clipPath;
        final /* synthetic */ VideoScreenPreview this$0;

        public AnonymousClass1(VideoScreenPreview r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.clipPath = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            r2.save();
            r2.clipPath(this.clipPath);
            super.dispatchDraw(r2);
            r2.restore();
        }

        @Override // org.telegram.ui.AspectRatioFrameLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            this.clipPath.reset();
            VideoScreenPreview r42 = this.this$0;
            if (r42.fromTop == false) goto L5;
            AndroidUtilities.rectTmp.set(0.0f, -VideoScreenPreview.m12067$$Nest$fgetroundRadius(r42), getMeasuredWidth(), getMeasuredHeight());
        L6:
            float r43 = VideoScreenPreview.m12067$$Nest$fgetroundRadius(this.this$0) - AndroidUtilities.dp(3.0f);
            this.clipPath.addRoundRect(AndroidUtilities.rectTmp, r43, r43, Path.Direction.CW);
            return;
        L5:
            AndroidUtilities.rectTmp.set(0.0f, 0.0f, getMeasuredWidth(), (int) (getMeasuredHeight() + VideoScreenPreview.m12067$$Nest$fgetroundRadius(this.this$0)));
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.VideoScreenPreview$2, reason: invalid class name */
    public class AnonymousClass2 extends CombinedDrawable {
        final /* synthetic */ VideoScreenPreview this$0;

        public AnonymousClass2(VideoScreenPreview r1, Drawable r2, Drawable r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int r3, int r4, int r5, int r6) {
            VideoScreenPreview r0 = this.this$0;
            if (r0.fromTop == false) goto L6;
            super.setBounds(r3, (int) (r4 - VideoScreenPreview.m12067$$Nest$fgetroundRadius(r0)), r5, r6);
            return;
        L6:
            super.setBounds(r3, r4, r5, (int) (r6 + VideoScreenPreview.m12067$$Nest$fgetroundRadius(r0)));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.VideoScreenPreview$3, reason: invalid class name */
    public class AnonymousClass3 extends VideoPlayerHolderBase {
        final /* synthetic */ VideoScreenPreview this$0;

        /* renamed from: org.telegram.ui.Components.Premium.VideoScreenPreview$3$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                VideoScreenPreview r0 = this.this$1.this$0;
                r0.firstFrameRendered = true;
                r0.invalidate();
            }
        }

        public AnonymousClass3(VideoScreenPreview r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.video.VideoPlayerHolderBase
        public void onRenderedFirstFrame() {
            VideoScreenPreview r0 = this.this$0;
            TextureView r1 = r0.textureView;
            if (r1 != null) goto L6;
            return;
        L6:
            if (r0.firstFrameRendered == true) goto L9;
            r1.setAlpha(0.0f);
            this.this$0.textureView.animate().alpha(1.0f).setListener(new AnonymousClass1(this)).setDuration(200);
            return;
        }

        @Override // org.telegram.messenger.video.VideoPlayerHolderBase
        public void onStateChanged(boolean r3, int r4) {
            VideoPlayerHolderBase r32 = this.this$0.videoPlayerBase;
            if (r32 != null) goto L6;
            return;
        L6:
            if (r4 != 4) goto L10;
            r32.seekTo(0);
            this.this$0.videoPlayerBase.play();
            return;
        L10:
            if (r4 != 1) goto L13;
            r32.play();
            return;
        }
    }

    /* renamed from: $r8$lambda$GB22CY88-oBuS7VXRtDClFkraMo, reason: not valid java name */
    public static /* synthetic */ void m12065$r8$lambda$GB22CY88oBuS7VXRtDClFkraMo(VideoScreenPreview r0, TLRPC.Document r1) {
        r0.lambda$setVideo$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$WhtlH7b4NK_QXNI5jmAEaMFL6_w(VideoScreenPreview r0) {
        r0.checkVideo();
    }

    /* renamed from: $r8$lambda$iGIV-_Jgqna4RaVcVBxiLVI9smw, reason: not valid java name */
    public static /* synthetic */ void m12066$r8$lambda$iGIV_Jgqna4RaVcVBxiLVI9smw(VideoScreenPreview r0, File r1) {
        r0.lambda$setVideo$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$oqqJ5kFEZuqEQjRzuFj19R2Iil8() {
    }

    /* renamed from: -$$Nest$fgetroundRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12067$$Nest$fgetroundRadius(VideoScreenPreview r0) {
        return r0.roundRadius;
    }

    static {
        speedScaleVideoTimestamps = new float[]{0.02f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.02f};
    }

    public VideoScreenPreview(Context r9, SvgHelper.SvgDrawable r10, int r11, int r12, Theme.ResourcesProvider r13) {
        super(r9);
        this.phoneFrame1 = new Paint(1);
        this.phoneFrame2 = new Paint(1);
        this.fromTop = false;
        this.imageReceiver = new ImageReceiver(this);
        this.currentAccount = r11;
        this.type = r12;
        this.svgIcon = r10;
        this.phoneFrame1.setColor(-16777216);
        this.phoneFrame2.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_premiumGradient2, r13), -16777216, 0.5f));
        this.imageReceiver.setLayerNum(Integer.MAX_VALUE);
        setVideo();
        if (r12 != 1) goto L6;
        MatrixParticlesDrawable r132 = new MatrixParticlesDrawable();
        this.matrixParticlesDrawable = r132;
        r132.init();
    L42:
        if (r12 == 1) goto L45;
        if (r12 == 3) goto L45;
        if (r12 == 11) goto L45;
    L46:
        AnonymousClass1 r102 = new AnonymousClass1(this, r9);
        this.aspectRatioFrameLayout = r102;
        r102.setResizeMode(0);
        TextureView r103 = new TextureView(r9);
        this.textureView = r103;
        this.aspectRatioFrameLayout.addView(r103);
        setWillNotDraw(false);
        addView(this.aspectRatioFrameLayout);
        return;
    L45:
        this.fromTop = true;
        goto L46
    L6:
        if (r12 != 6) goto L8;
    L32:
        StarParticlesView.Drawable r2 = new StarParticlesView.Drawable(40);
        this.starDrawable = r2;
        r2.speedScale = 3.0f;
        r2.type = r12;
        if (r12 == 3) goto L38;
        if (r12 == 24) goto L38;
        if (r12 == 43) goto L38;
        r2.size1 = 14;
        r2.size2 = 16;
        r2.size3 = 15;
    L39:
        if (r12 != 43) goto L41;
        r2.useRotate = true;
    L41:
        r2.k3 = 0.98f;
        r2.k2 = 0.98f;
        r2.k1 = 0.98f;
        r2.speedScale = 4.0f;
        r2.resourcesProvider = r13;
        r2.colorKey = Theme.key_premiumStartSmallStarsColor2;
        r2.init();
    L38:
        r2.size1 = 14;
        r2.size2 = 18;
        r2.size3 = 18;
        goto L39
    L8:
        if (r12 == 9) goto L32;
        if (r12 == 3) goto L32;
        if (r12 == 7) goto L32;
        if (r12 == 11) goto L32;
        if (r12 == 4) goto L32;
        if (r12 == 24) goto L32;
        if (r12 == 43) goto L32;
        if (r12 != 2) goto L22;
        SpeedLineParticles$Drawable r133 = new SpeedLineParticles$Drawable(200);
        this.speedLinesDrawable = r133;
        r133.init();
        goto L42
    L22:
        if (r12 != 13) goto L25;
        HelloParticles.Drawable r134 = new HelloParticles.Drawable(25);
        this.helloParticlesDrawable = r134;
        r134.init();
        goto L42
    L25:
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L28;
        int r5 = 800;
    L31:
        StarParticlesView.Drawable r6 = new StarParticlesView.Drawable(r5);
        this.starDrawable = r6;
        r6.resourcesProvider = r13;
        r6.colorKey = Theme.key_premiumStartSmallStarsColor2;
        r6.size1 = 4;
        r6.k3 = 0.98f;
        r6.k2 = 0.98f;
        r6.k1 = 0.98f;
        r6.useRotate = true;
        r6.speedScale = 4.0f;
        r6.checkBounds = true;
        r6.checkTime = true;
        r6.useBlur = true;
        r6.roundEffect = false;
        r6.init();
        goto L42
    L28:
        if (SharedConfig.getDevicePerformanceClass() != 1) goto L30;
        r5 = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        goto L31
    L30:
        r5 = 100;
        goto L31
    }

    private void checkVideo() {
        File r0 = this.file;
        if (r0 == null) goto L7;
        if (r0.exists() == false) goto L7;
    L8:
        File r02 = this.file;
        if (r02 != null) goto L11;
    L22:
        this.aspectRatio = 0.671f;
    L24:
        if (this.allowPlay == false) goto L26;
        runVideoPlayer();
    L26:
        this.nextCheck = null;
        return;
    L11:
        if (r02.exists() == false) goto L22;
        if ((NotificationCenter.getGlobalInstance().getCurrentHeavyOperationFlags() & 512) == 0) goto L28;
        Runnable r03 = this.nextCheck;
        if (r03 == null) goto L17;
        AndroidUtilities.cancelRunOnUIThread(r03);
    L17:
        VideoScreenPreview$$ExternalSyntheticLambda1 r04 = new VideoScreenPreview$$ExternalSyntheticLambda1(this);
        this.nextCheck = r04;
        AndroidUtilities.runOnUIThread(r04, 300);
        return;
    L28:
        MediaMetadataRetriever r05 = new MediaMetadataRetriever();     // Catch: Exception -> L21
        r05.setDataSource(ApplicationLoader.applicationContext, Uri.fromFile(this.file));     // Catch: Exception -> L21
        int r2 = Integer.parseInt(r05.extractMetadata(18));     // Catch: Exception -> L21
        int r3 = Integer.parseInt(r05.extractMetadata(19));     // Catch: Exception -> L21
        r05.release();     // Catch: Exception -> L21
        this.aspectRatio = r2 / r3;     // Catch: Exception -> L21
    L21:
        this.aspectRatio = 0.671f;
    L7:
        if (SharedConfig.streamMedia == false) goto L26;
        goto L8
    }

    private /* synthetic */ void lambda$setVideo$0(File r1) {
        this.file = r1;
        checkVideo();
    }

    private /* synthetic */ void lambda$setVideo$1(TLRPC.Document r2) {
        AndroidUtilities.runOnUIThread(new VideoScreenPreview$$ExternalSyntheticLambda3(this, FileLoader.getInstance(this.currentAccount).getPathToAttach(r2)));
    }

    private void runVideoPlayer() {
        if (this.file != null) goto L7;
        if (SharedConfig.streamMedia == true) goto L7;
        return;
    L7:
        if (this.videoPlayerBase != null) goto L31;
        this.aspectRatioFrameLayout.setAspectRatio(this.aspectRatio, 0);
        AnonymousClass3 r3 = new AnonymousClass3(this);
        this.videoPlayerBase = r3;
        r3.with(this.textureView);
        File r32 = this.file;
        if (r32 != null) goto L12;
    L28:
        StringBuilder r33 = new StringBuilder("?account=");     // Catch: Exception -> L20
        r33.append(this.currentAccount);     // Catch: Exception -> L20
        r33.append("&id=");     // Catch: Exception -> L20
        r33.append(this.document.id);     // Catch: Exception -> L20
        r33.append("&hash=");     // Catch: Exception -> L20
        r33.append(this.document.access_hash);     // Catch: Exception -> L20
        r33.append("&dc=");     // Catch: Exception -> L20
        r33.append(this.document.dc_id);     // Catch: Exception -> L20
        r33.append("&size=");     // Catch: Exception -> L20
        r33.append(this.document.size);     // Catch: Exception -> L20
        r33.append("&mime=");     // Catch: Exception -> L20
        r33.append(URLEncoder.encode(this.document.mime_type, "UTF-8"));     // Catch: Exception -> L20
        r33.append("&rid=");     // Catch: Exception -> L20
        r33.append(FileLoader.getInstance(this.currentAccount).getFileReference(MediaDataController.getInstance(this.currentAccount).getPremiumPromo()));     // Catch: Exception -> L20
        r33.append("&name=");     // Catch: Exception -> L20
        r33.append(URLEncoder.encode(FileLoader.getDocumentFileName(this.document), "UTF-8"));     // Catch: Exception -> L20
        r33.append("&reference=");     // Catch: Exception -> L20
        byte[] r0 = this.document.file_reference;     // Catch: Exception -> L20
        if (r0 != null) goto L18;
        r0 = new byte[0];     // Catch: Exception -> L20
    L18:
        r33.append(Utilities.bytesToHex(r0));     // Catch: Exception -> L20
        Uri r02 = Uri.parse("tg://" + this.attachFileName + r33.toString());     // Catch: Exception -> L20
    L21:
        if (r02 == null) goto L30;
        this.videoPlayerBase.preparePlayer(r02, false, 1.0f);
        if (this.firstFrameRendered == true) goto L26;
        this.imageReceiver.stopAnimation();
        this.textureView.setAlpha(0.0f);
    L26:
        this.videoPlayerBase.seekTo(this.lastFrameTime + 60);
        this.videoPlayerBase.play();
        return;
    L30:
        return;
    L20:
        r02 = null;
        goto L21
    L12:
        if (r32.exists() == false) goto L28;
        r02 = Uri.fromFile(this.file);
        goto L21
    }

    private void setVideo() {
        TLRPC.TL_help_premiumPromo r6 = MediaDataController.getInstance(this.currentAccount).getPremiumPromo();
        String r0 = PremiumPreviewFragment.featureTypeToServerString(this.type);
        if (r6 == null) goto L27;
        int r1 = 0;
    L6:
        if (r1 >= r6.video_sections.size()) goto L11;
        if (r6.video_sections.get(r1).equals(r0) == true) goto L12;
        r1 = r1 + 1;
    L12:
        if (r1 < 0) goto L28;
        TLRPC.Document r02 = r6.videos.get(r1);
        AnonymousClass2 r4 = null;
        int r12 = 0;
    L15:
        if (r12 >= r02.thumbs.size()) goto L20;
        if ((r02.thumbs.get(r12) instanceof TLRPC.TL_photoStrippedSize) == false) goto L19;
        this.roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), ImageLoader.getStrippedPhotoBitmap(r02.thumbs.get(r12).bytes, "b"));
        CellFlickerDrawable r2 = new CellFlickerDrawable();
        r2.repeatProgress = 4.0f;
        r2.progress = 3.5f;
        r2.frameInside = true;
        this.cellFlickerDrawable = r2.getDrawableInterface(this, this.svgIcon);
        AnonymousClass2 r22 = new AnonymousClass2(this, this.roundedBitmapDrawable, this.cellFlickerDrawable);
        r22.setFullsize(true);
        r4 = r22;
    L19:
        r12 = r12 + 1;
        goto L15
    L20:
        this.attachFileName = FileLoader.getAttachFileName(r02);
        this.imageReceiver.setImage(null, null, r4, null, r6, 1);
        FileLoader.getInstance(this.currentAccount).loadFile(r02, r6, 3, 0);
        this.document = r02;
        Utilities.globalQueue.postRunnable(new VideoScreenPreview$$ExternalSyntheticLambda0(this, r02));
        return;
    L28:
        return;
    L11:
        r1 = -1;
        goto L12
    }

    private void stopVideoPlayer() {
        VideoPlayerHolderBase r0 = this.videoPlayerBase;
        if (r0 == null) goto L6;
        this.lastFrameTime = r0.getCurrentPosition();
        this.videoPlayerBase.release(new VideoScreenPreview$$ExternalSyntheticLambda2());
        this.videoPlayerBase = null;
        return;
    }

    private void updateAttachState() {
        if (this.visible == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.play == r0) goto L16;
        this.play = r0;
        ImageReceiver r2 = this.imageReceiver;
        if (r0 == false) goto L14;
        r2.onAttachedToWindow();
        return;
    L14:
        r2.onDetachedFromWindow();
        return;
    L16:
        return;
    L5:
        if (this.attached == false) goto L7;
        r0 = true;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.fileLoaded) goto L10;
        String r12 = (String) r3[0];
        String r22 = this.attachFileName;
        if (r22 != null) goto L7;
        return;
    L7:
        if (r22.equals(r12) == false) goto L12;
        this.file = (File) r3[1];
        checkVideo();
        return;
    L12:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        if (this.starDrawable != null) goto L11;
        if (this.speedLinesDrawable != null) goto L11;
        if (this.helloParticlesDrawable != null) goto L11;
        if (this.matrixParticlesDrawable != null) goto L11;
    L32:
        float r0 = (int) (Math.min(getMeasuredWidth(), getMeasuredHeight()) * 0.9f);
        float r4 = (getMeasuredWidth() - (0.671f * r0)) / 2.0f;
        float r1 = 0.0671f * r0;
        this.roundRadius = r1;
        if (this.fromTop == false) goto L35;
        AndroidUtilities.rectTmp.set(r4, -r1, getMeasuredWidth() - r4, r0);
    L36:
        RectF r12 = AndroidUtilities.rectTmp;
        r12.inset(-AndroidUtilities.dp(3.0f), -AndroidUtilities.dp(3.0f));
        r12.inset(-AndroidUtilities.dp(3.0f), -AndroidUtilities.dp(3.0f));
        r11.drawRoundRect(r12, this.roundRadius + AndroidUtilities.dp(3.0f), this.roundRadius + AndroidUtilities.dp(3.0f), this.phoneFrame2);
        r12.inset(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        float r5 = this.roundRadius;
        r11.drawRoundRect(r12, r5, r5, this.phoneFrame1);
        if (this.fromTop == false) goto L39;
        r12.set(r4, 0.0f, getMeasuredWidth() - r4, r0);
    L40:
        float r02 = this.roundRadius - AndroidUtilities.dp(3.0f);
        this.roundRadius = r02;
        RoundedBitmapDrawable r2 = this.roundedBitmapDrawable;
        if (r2 == null) goto L43;
        r2.setCornerRadius(r02);
    L43:
        CellFlickerDrawable.DrawableInterface r03 = this.cellFlickerDrawable;
        if (r03 == null) goto L46;
        r03.radius = this.roundRadius;
    L46:
        boolean r04 = this.fromTop;
        ImageReceiver r22 = this.imageReceiver;
        if (r04 == false) goto L49;
        float r05 = this.roundRadius;
        r22.setRoundRadius(0, 0, (int) r05, (int) r05);
    L51:
        if (this.firstFrameRendered == true) goto L53;
        this.imageReceiver.setImageCoords(r12.left, r12.top, r12.width(), r12.height());
        this.imageReceiver.draw(r11);
    L53:
        super.dispatchDraw(r11);
        if (this.fromTop == true) goto L57;
        r11.drawCircle(this.imageReceiver.getCenterX(), this.imageReceiver.getImageY() + AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), this.phoneFrame1);
        return;
    L57:
        return;
    L49:
        float r06 = this.roundRadius;
        r22.setRoundRadius((int) r06, (int) r06, 0, 0);
        goto L51
    L39:
        r12.set(r4, getMeasuredHeight() - r0, getMeasuredWidth() - r4, getMeasuredHeight());
        goto L40
    L35:
        AndroidUtilities.rectTmp.set(r4, getMeasuredHeight() - r0, getMeasuredWidth() - r4, getMeasuredHeight() + this.roundRadius);
    L11:
        if (this.progress >= 0.5f) goto L32;
        float r07 = (float) Math.pow(1.0f - r0, 2.0d);
        r11.save();
        r11.scale(r07, r07, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
        MatrixParticlesDrawable r08 = this.matrixParticlesDrawable;
        if (r08 == null) goto L15;
        r08.onDraw(r11);
    L31:
        r11.restore();
        invalidate();
        goto L32
    L15:
        StarParticlesView.Drawable r09 = this.starDrawable;
        if (r09 == null) goto L19;
        r09.onDraw(r11);
        goto L31
    L19:
        if (this.speedLinesDrawable != null) goto L21;
        HelloParticles.Drawable r010 = this.helloParticlesDrawable;
        if (r010 == null) goto L31;
        r010.onDraw(r11);
        goto L31
    L21:
        if (this.videoPlayerBase == null) goto L26;
        float r011 = Utilities.clamp(r0.getCurrentPosition() / this.videoPlayerBase.getDuration(), 1.0f, 0.0f);
        float[] r52 = speedScaleVideoTimestamps;
        float r6 = 1.0f / (r52.length - 1);
        int r7 = (int) (r011 / r6);
        int r8 = r7 + 1;
        float r012 = (r011 - (r7 * r6)) / r6;
        if (r8 >= r52.length) goto L25;
        float r62 = (r52[r7] * (1.0f - r012)) + (r52[r8] * r012);
    L27:
        float r42 = ((1.0f - Utilities.clamp(this.progress / 0.1f, 1.0f, 0.0f)) * 0.9f) + 0.1f;
        SpeedLineParticles$Drawable r013 = this.speedLinesDrawable;
        r013.speedScale = (r42 * 150.0f) * r62;
        r013.onDraw(r11);
        goto L31
    L25:
        r62 = r52[r7];
        goto L27
    L26:
        r62 = 0.2f;
        goto L27
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        updateAttachState();
        if (this.firstFrameRendered == true) goto L5;
        checkVideo();
    L5:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoaded);
    }

    public void onDestroy() {
        stopVideoPlayer();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        updateAttachState();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileLoaded);
        HelloParticles.Drawable r0 = this.helloParticlesDrawable;
        if (r0 == null) goto L5;
        r0.recycle();
        this.helloParticlesDrawable = null;
    L5:
        stopVideoPlayer();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        super.onLayout(r3, r4, r5, r6, r7);
        int r32 = getMeasuredWidth() << (getMeasuredHeight() + 16);
        float r42 = (int) (Math.min(getMeasuredWidth(), getMeasuredHeight()) * 0.9f);
        float r62 = (getMeasuredWidth() - (0.671f * r42)) / 2.0f;
        if (this.fromTop == false) goto L5;
        AndroidUtilities.rectTmp.set(r62, -this.roundRadius, getMeasuredWidth() - r62, r42);
    L7:
        if (this.size == r32) goto L40;
        this.size = r32;
        MatrixParticlesDrawable r33 = this.matrixParticlesDrawable;
        if (r33 == null) goto L11;
        r33.drawingRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.matrixParticlesDrawable.excludeRect.set(AndroidUtilities.rectTmp);
        this.matrixParticlesDrawable.excludeRect.inset(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
    L11:
        StarParticlesView.Drawable r34 = this.starDrawable;
        if (r34 == null) goto L33;
        int r52 = this.type;
        if (r52 != 6) goto L16;
    L31:
        r34.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.starDrawable.rect.inset(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f));
    L32:
        this.starDrawable.resetPositions();
        this.starDrawable.excludeRect.set(AndroidUtilities.rectTmp);
        this.starDrawable.excludeRect.inset(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        goto L33
    L16:
        if (r52 == 9) goto L31;
        if (r52 == 3) goto L31;
        if (r52 == 7) goto L31;
        if (r52 == 24) goto L31;
        if (r52 == 43) goto L31;
        if (r52 == 11) goto L31;
        if (r52 == 4) goto L31;
        RectF r35 = AndroidUtilities.rectTmp;
        float r53 = (int) (r35.width() * 0.4f);
        this.starDrawable.rect.set(r35.centerX() - r53, r35.centerY() - r53, r35.centerX() + r53, r35.centerY() + r53);
        this.starDrawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
    L33:
        SpeedLineParticles$Drawable r36 = this.speedLinesDrawable;
        if (r36 == null) goto L36;
        r36.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.speedLinesDrawable.screenRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.speedLinesDrawable.rect.inset(AndroidUtilities.dp(100.0f), AndroidUtilities.dp(100.0f));
        this.speedLinesDrawable.rect.offset(0.0f, getMeasuredHeight() * 0.1f);
        this.speedLinesDrawable.resetPositions();
    L36:
        HelloParticles.Drawable r37 = this.helloParticlesDrawable;
        if (r37 == null) goto L41;
        r37.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.helloParticlesDrawable.screenRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.helloParticlesDrawable.rect.inset(AndroidUtilities.dp(0.0f), getMeasuredHeight() * 0.1f);
        this.helloParticlesDrawable.resetPositions();
        return;
    L41:
        return;
    L40:
        return;
    L5:
        AndroidUtilities.rectTmp.set(r62, getMeasuredHeight() - r42, getMeasuredWidth() - r62, getMeasuredHeight() + this.roundRadius);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        int r0 = View.MeasureSpec.getSize(r6);
        int r1 = View.MeasureSpec.getSize(r7);
        float r2 = (int) (Math.min(r1, r0) * 0.9f);
        float r02 = r0;
        float r3 = (r02 - (0.671f * r2)) / 2.0f;
        this.roundRadius = 0.0671f * r2;
        this.aspectRatioFrameLayout.invalidateOutline();
        if (this.fromTop == false) goto L5;
        AndroidUtilities.rectTmp.set(r3, 0.0f, r02 - r3, r2);
    L6:
        ViewGroup.LayoutParams r03 = this.aspectRatioFrameLayout.getLayoutParams();
        RectF r12 = AndroidUtilities.rectTmp;
        r03.width = (int) r12.width();
        this.aspectRatioFrameLayout.getLayoutParams().height = (int) r12.height();
        ((ViewGroup.MarginLayoutParams) this.aspectRatioFrameLayout.getLayoutParams()).leftMargin = (int) r12.left;
        ((ViewGroup.MarginLayoutParams) this.aspectRatioFrameLayout.getLayoutParams()).topMargin = (int) r12.top;
        super.onMeasure(r6, r7);
        return;
    L5:
        float r13 = r1;
        AndroidUtilities.rectTmp.set(r3, r13 - r2, r02 - r3, r13);
        goto L6
    }

    @Override // org.telegram.ui.Components.Premium.PagerHeaderView
    public void setOffset(float r8) {
        boolean r3 = false;
        if (r8 >= 0.0f) goto L15;
        float r82 = (-r8) / getMeasuredWidth();
        setAlpha((Utilities.clamp(1.0f - r82, 1.0f, 0.0f) * 0.5f) + 0.5f);
        setRotationY(50.0f * r82);
        invalidate();
        if (this.fromTop == false) goto L7;
        setTranslationY(((-getMeasuredHeight()) * 0.3f) * r82);
    L8:
        this.progress = Math.abs(r82);
        if (r82 >= 1.0f) goto L11;
        boolean r0 = true;
    L13:
        if (r82 >= 0.1f) goto L28;
        r3 = true;
    L28:
        if (r0 == this.visible) goto L31;
        this.visible = r0;
        updateAttachState();
    L31:
        if (r3 == this.allowPlay) goto L38;
        this.allowPlay = r3;
        this.imageReceiver.setAllowStartAnimation(r3);
        if (this.allowPlay == false) goto L36;
        this.imageReceiver.startAnimation();
        runVideoPlayer();
        return;
    L36:
        stopVideoPlayer();
        this.imageReceiver.stopAnimation();
        return;
    L38:
        return;
    L11:
        r0 = false;
        goto L13
    L7:
        setTranslationY((getMeasuredHeight() * 0.3f) * r82);
        goto L8
    L15:
        float r83 = (-r8) / getMeasuredWidth();
        invalidate();
        setRotationY(50.0f * r83);
        if (this.fromTop == false) goto L18;
        setTranslationY((getMeasuredHeight() * 0.3f) * r83);
    L20:
        if (r83 <= (-1.0f)) goto L22;
        r0 = true;
    L24:
        if (r83 <= (-0.1f)) goto L26;
        r3 = true;
    L26:
        this.progress = Math.abs(r83);
        goto L28
    L22:
        r0 = false;
        goto L24
    L18:
        setTranslationY(((-getMeasuredHeight()) * 0.3f) * r83);
        goto L20
    }
}
