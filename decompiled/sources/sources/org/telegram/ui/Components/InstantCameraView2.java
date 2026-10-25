package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.Property;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.InstantCameraViewBase;
import org.telegram.ui.Components.TelegramRoundVideoUpload;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.Stories.recorder.FlashViews;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;
import org.telegram.utils.settings.SharedSettings;

/* loaded from: classes7.dex */
public final class InstantCameraView2 extends InstantCameraViewBase {
    private RoundVideoSession.OutputResolution activeOutputResolution;
    private float animationTranslationY;
    private final int buttonIconSize;
    private final LinearLayout buttonsLayout;
    private final CameraContainer cameraContainer;
    private RoundVideoSession.CameraCapabilities capabilities;
    private final int currentAccount;
    private final InstantCameraView.Delegate delegate;
    private boolean drawInitialPlaceholder;
    private final FlashViews.ImageViewInvertable flashButton;
    private RLottieDrawable flashOffDrawable;
    private RLottieDrawable flashOnDrawable;
    private final FlashViews flashViews;
    private boolean handlingPreviewTextureInvalidate;
    private boolean heavyOperationsStopped;
    private Bitmap lastBitmap;
    private long lastPreviewUiFrameRealtimeNs;
    private long lastVisibleDurationMs;
    private final ImageView muteImageView;
    private boolean muted;
    private boolean opened;
    private float panTranslationY;
    private final View parentView;
    private SendOptions pendingSend;
    private float pinchStartDistance;
    private boolean pinchZoomActive;
    private final Matrix previewBitmapMatrix;
    private final float[] previewBitmapMatrixValues;
    private final Paint previewBitmapPaint;
    private final FrameLayout previewContainer;
    private float previousWindowBrightness;
    private final RoundVideoProgressView progressView;
    private final int recordingGuid;
    private final Runnable recordingUiFallback;
    private boolean recordingUiFallbackRegistered;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean resumeNotificationPending;
    private final int[] screenPosition;
    private final boolean secretChat;
    private boolean sent;
    private RoundVideoSession session;
    private final RoundVideoSession.Listener sessionListener;
    private boolean setVisibilityFromPause;
    private boolean startedNotificationSent;
    private RoundVideoSession.StateInfo stateInfo;
    private boolean stopNotificationSent;
    private final FlashViews.ImageViewInvertable switchCameraButton;
    private RLottieDrawable switchCameraDrawable;
    private boolean switchPlaceholderVisible;
    private final BackupImageView textureOverlayView;
    private final TextureView textureView;
    private int textureViewSize;
    private TelegramRoundVideoUpload upload;
    private VideoEditedInfo videoEditedInfo;
    private AnimatorSet visibilityAnimator;
    private int zoomPointerId1;
    private int zoomPointerId2;
    private float zoomProgress;
    private ValueAnimator zoomResetAnimator;

    /* renamed from: org.telegram.ui.Components.InstantCameraView2$1, reason: invalid class name */
    public class AnonymousClass1 extends BackupImageView {
        private final CellFlickerDrawable flickerDrawable;
        final /* synthetic */ InstantCameraView2 this$0;
        final /* synthetic */ Paint val$placeholderDimPaint;

        public AnonymousClass1(InstantCameraView2 r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$placeholderDimPaint = r3;
            super(r2);
            this.flickerDrawable = new CellFlickerDrawable();
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r6) {
            super.onDraw(r6);
            if (InstantCameraView2.m11028$$Nest$fgetdrawInitialPlaceholder(this.this$0) == true) goto L5;
            return;
        L5:
            float r0 = Math.min(getWidth(), getHeight()) * 0.5f;
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(0.0f, 0.0f, getWidth(), getHeight());
            r6.drawRoundRect(r1, r0, r0, this.val$placeholderDimPaint);
            r1.inset(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            this.flickerDrawable.setParentWidth(getWidth());
            this.flickerDrawable.draw(r6, r1, r0, null);
            invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView2$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ InstantCameraView2 this$0;

        public AnonymousClass2(InstantCameraView2 r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r2, Outline r3) {
            r3.setOval(0, 0, r2.getWidth(), r2.getHeight());
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView2$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraView2 this$0;

        public AnonymousClass3(InstantCameraView2 r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != InstantCameraView2.m11041$$Nest$fgetvisibilityAnimator(this.this$0)) goto L6;
            this.this$0.hideCamera(true);
            InstantCameraView2.m11046$$Nest$fputsetVisibilityFromPause(this.this$0, false);
            this.this$0.setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView2$4, reason: invalid class name */
    public class AnonymousClass4 implements RoundVideoSession.Listener {
        final /* synthetic */ InstantCameraView2 this$0;

        public AnonymousClass4(InstantCameraView2 r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onCameraCapabilitiesChanged(RoundVideoSession.CameraCapabilities r2) {
            InstantCameraView2.m11042$$Nest$fputcapabilities(this.this$0, r2);
            if (r2.getActiveFacing() == null) goto L5;
            SharedSettings.roundVideoLastCamera.set(r2.getActiveFacing());
        L5:
            InstantCameraView2.m11062$$Nest$mupdateButtons(this.this$0);
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onCameraSwitchCompleted(RoundVideoSession.CameraFacing r3) {
            InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).animate().cancel();
            InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).setRotationY(-90.0f);
            InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).animate().rotationY(0.0f).setDuration(140).start();
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onCameraSwitchFirstFrame() {
            InstantCameraView2.m11053$$Nest$mhideCameraSwitchPlaceholder(this.this$0);
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onCameraSwitchStarted(RoundVideoSession.CameraFacing r3) {
            InstantCameraView2.m11059$$Nest$mshowCameraSwitchPlaceholder(this.this$0);
            InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).animate().cancel();
            InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).setCameraDistance(InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).getMeasuredHeight() * 8.0f);
            InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).animate().rotationY(90.0f).setDuration(120).start();
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onCompleted(RoundVideoSession.Result r1) {
            InstantCameraView2.m11051$$Nest$mcompleteSend(this.this$0, r1);
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onError(Exception r2) {
            InstantCameraView2.m11060$$Nest$mstartHeavyOperations(this.this$0);
            FileLog.e(r2);
            if (InstantCameraView2.m11039$$Nest$fgetupload(this.this$0) == null) goto L5;
            InstantCameraView2.m11039$$Nest$fgetupload(this.this$0).release(true);
        L5:
            InstantCameraView2.m11049$$Nest$fputupload(this.this$0, null);
            NotificationCenter.getInstance(InstantCameraView2.m11027$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.recordStartError, new Object[]{Integer.valueOf(InstantCameraView2.m11033$$Nest$fgetrecordingGuid(this.this$0))});
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onPreviewFirstFrame() {
            InstantCameraView2.m11054$$Nest$mhideInitialPlaceholder(this.this$0);
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onPreviewReady(long r8, long r10, long r12) {
            InstantCameraView2.m11032$$Nest$fgetprogressView(this.this$0).setProgress(r8 / 60000.0f);
            InstantCameraView2.m11044$$Nest$fputmuted(this.this$0, false);
            InstantCameraView2.m11030$$Nest$fgetmuteImageView(this.this$0).setAlpha(0.0f);
            if (InstantCameraView2.m11035$$Nest$fgetsession(this.this$0) != null) goto L5;
            File r0 = null;
        L6:
            if (r0 != null) goto L8;
            return;
        L8:
            InstantCameraView2 r2 = this.this$0;
            InstantCameraView2.m11050$$Nest$fputvideoEditedInfo(r2, InstantCameraView2.m11052$$Nest$mcreateVideoEditedInfo(r2, r0, r8, null));
            VideoEditedInfo r1 = InstantCameraView2.m11040$$Nest$fgetvideoEditedInfo(this.this$0);
            long r3 = -1;
            if (r10 <= 0) goto L11;
            long r5 = r10;
        L12:
            r1.startTime = r5;
            VideoEditedInfo r13 = InstantCameraView2.m11040$$Nest$fgetvideoEditedInfo(this.this$0);
            if (r12 >= r8) goto L15;
            r3 = r12;
        L15:
            r13.endTime = r3;
            NotificationCenter.getInstance(InstantCameraView2.m11027$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.audioDidSent, new Object[]{Integer.valueOf(InstantCameraView2.m11033$$Nest$fgetrecordingGuid(this.this$0)), InstantCameraView2.m11040$$Nest$fgetvideoEditedInfo(this.this$0), r0.getAbsolutePath(), new ArrayList()});
            float r82 = Math.max(1, r8);
            this.this$0.dispatchTrimRange(r10 / r82, r12 / r82);
            return;
        L11:
            r5 = -1;
            goto L12
        L5:
            r0 = InstantCameraView2.m11035$$Nest$fgetsession(this.this$0).getPreviewFile();
            goto L6
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.Listener
        public void onStateChanged(RoundVideoSession.StateInfo r9) {
            if (InstantCameraView2.m11037$$Nest$fgetstateInfo(this.this$0) != null) goto L5;
            RoundVideoSession.State r0 = null;
        L6:
            InstantCameraView2.m11048$$Nest$fputstateInfo(this.this$0, r9);
            RoundVideoSession.State r1 = RoundVideoSession.State.RECORDING;
            if (r0 == r1) goto L9;
        L11:
            InstantCameraView2 r3 = this.this$0;
            InstantCameraView2.m11043$$Nest$fputlastVisibleDurationMs(r3, Math.max(InstantCameraView2.m11029$$Nest$fgetlastVisibleDurationMs(r3), r9.getRecordedDurationMs()));
            RoundVideoSession.State r32 = r9.getState();
            InstantCameraView2 r4 = this.this$0;
            if (r32 != r1) goto L19;
            InstantCameraView2.m11061$$Nest$mstopHeavyOperations(r4);
            InstantCameraView2.m11054$$Nest$mhideInitialPlaceholder(this.this$0);
            InstantCameraView2.m11058$$Nest$msetRecordingUiFrameClockActive(this.this$0, true);
            InstantCameraView2.m11063$$Nest$mupdateRecordingUiFrame(this.this$0);
            boolean r33 = InstantCameraView2.m11036$$Nest$fgetstartedNotificationSent(this.this$0);
            InstantCameraView2 r42 = this.this$0;
            if (r33 == true) goto L17;
            InstantCameraView2.m11047$$Nest$fputstartedNotificationSent(r42, true);
            NotificationCenter.getInstance(InstantCameraView2.m11027$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.recordStarted, new Object[]{Integer.valueOf(InstantCameraView2.m11033$$Nest$fgetrecordingGuid(this.this$0)), Boolean.FALSE});
        L21:
            if (r9.getState() != RoundVideoSession.State.COMPLETED) goto L23;
        L26:
            InstantCameraView2.m11060$$Nest$mstartHeavyOperations(this.this$0);
        L27:
            if (r0 == r1) goto L29;
        L31:
            InstantCameraView2.m11062$$Nest$mupdateButtons(this.this$0);
            return;
        L29:
            if (r9.getState() != RoundVideoSession.State.PAUSING) goto L31;
            InstantCameraView2.m11056$$Nest$mpostRecordStopped(this.this$0, 2);
            goto L31
        L23:
            if (r9.getState() == RoundVideoSession.State.ERROR) goto L26;
            if (r9.getState() != RoundVideoSession.State.RELEASED) goto L27;
        L17:
            if (InstantCameraView2.m11034$$Nest$fgetresumeNotificationPending(r42) == false) goto L21;
            InstantCameraView2.m11045$$Nest$fputresumeNotificationPending(this.this$0, false);
            NotificationCenter.getInstance(InstantCameraView2.m11027$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.recordResumed, new Object[0]);
            goto L21
        L19:
            InstantCameraView2.m11058$$Nest$msetRecordingUiFrameClockActive(r4, false);
            InstantCameraView2.m11032$$Nest$fgetprogressView(this.this$0).setProgress(r9.getRecordedDurationMs() / r9.getMaximumDurationMs());
            goto L21
        L9:
            if (r9.getState() == r1) goto L11;
            InstantCameraView2.m11057$$Nest$msaveLastCameraBitmap(this.this$0, true);
            goto L11
        L5:
            r0 = InstantCameraView2.m11037$$Nest$fgetstateInfo(this.this$0).getState();
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView2$5, reason: invalid class name */
    public class AnonymousClass5 extends TextureView {
        final /* synthetic */ InstantCameraView2 this$0;

        public AnonymousClass5(InstantCameraView2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            InstantCameraView2.m11055$$Nest$monPreviewTextureInvalidated(this.this$0);
            super.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView2$6, reason: invalid class name */
    public class AnonymousClass6 extends ViewOutlineProvider {
        final /* synthetic */ InstantCameraView2 this$0;

        public AnonymousClass6(InstantCameraView2 r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r2, Outline r3) {
            r3.setOval(0, 0, r2.getWidth(), r2.getHeight());
        }
    }

    public final class CameraContainer extends InstantCameraViewBase.InstantViewCameraContainer {
        private float imageProgress;
        private ImageReceiver imageReceiver;
        private boolean messageTransition;
        final /* synthetic */ InstantCameraView2 this$0;

        /* renamed from: -$$Nest$fputmessageTransition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11064$$Nest$fputmessageTransition(CameraContainer r0, boolean r1) {
            r0.messageTransition = r1;
        }

        public CameraContainer(InstantCameraView2 r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            if (this.imageReceiver != null) goto L5;
            return;
        L5:
            float r0 = this.imageProgress;
            if (r0 >= 1.0f) goto L8;
            this.imageProgress = Math.min(1.0f, r0 + 0.064f);
            invalidate();
        L8:
            r4.save();
            r4.translate((InstantCameraView2.m11032$$Nest$fgetprogressView(this.this$0).getLeft() + InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).getLeft()) + InstantCameraView2.m11038$$Nest$fgettextureView(this.this$0).getLeft(), (InstantCameraView2.m11032$$Nest$fgetprogressView(this.this$0).getTop() + InstantCameraView2.m11031$$Nest$fgetpreviewContainer(this.this$0).getTop()) + InstantCameraView2.m11038$$Nest$fgettextureView(this.this$0).getTop());
            if (this.imageReceiver.getImageWidth() == InstantCameraView2.m11038$$Nest$fgettextureView(this.this$0).getWidth()) goto L11;
            float r02 = InstantCameraView2.m11038$$Nest$fgettextureView(this.this$0).getWidth() / this.imageReceiver.getImageWidth();
            r4.scale(r02, r02);
        L11:
            r4.translate(-this.imageReceiver.getImageX(), -this.imageReceiver.getImageY());
            float r03 = this.imageReceiver.getAlpha();
            this.imageReceiver.setAlpha(this.imageProgress);
            this.imageReceiver.draw(r4);
            this.imageReceiver.setAlpha(r03);
            r4.restore();
        }

        @Override // org.telegram.ui.Components.InstantCameraViewBase.InstantViewCameraContainer
        public void setImageReceiver(ImageReceiver r2) {
            if (this.imageReceiver != null) goto L5;
            this.imageProgress = 0.0f;
        L5:
            this.imageReceiver = r2;
            invalidate();
        }
    }

    public static final class SendOptions {
        final long effectId;
        final boolean notify;
        final int scheduleDate;
        final int scheduleRepeatPeriod;
        final long stars;
        final int ttl;

        public SendOptions(boolean r1, int r2, int r3, int r4, long r5, long r7) {
            this.notify = r1;
            this.scheduleDate = r2;
            this.scheduleRepeatPeriod = r3;
            this.ttl = r4;
            this.effectId = r5;
            this.stars = r7;
        }
    }

    /* renamed from: $r8$lambda$-xz0xiRorWem6Kokx6Y_6EmnxrE, reason: not valid java name */
    public static /* synthetic */ void m11025$r8$lambda$xz0xiRorWem6Kokx6Y_6EmnxrE(InstantCameraView2 r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$2nvx6S9-BGbAZJhTOQF1yyiYTM8, reason: not valid java name */
    public static /* synthetic */ void m11026$r8$lambda$2nvx6S9BGbAZJhTOQF1yyiYTM8(InstantCameraView2 r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$41xE2xPesorevj_3eH3NJC0vXT4(InstantCameraView2 r0, boolean r1) {
        r0.setScreenFlashEnabled(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$XzMRcYYFaKMxlP5hcNN2yOpprT8(InstantCameraView2 r0, View r1, MotionEvent r2) {
        return r0.handleZoomTouch(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fIfHmdKgucq3nYyFGuET6nOlKnE(InstantCameraView2 r0, boolean r1, ValueAnimator r2) {
        r0.lambda$startAnimation$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hrLwYMINBqQJjC2wHyITpfYpJKM(InstantCameraView2 r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$lXIFrsXMZ3XFgyJpoa8Aw0TKOkA(InstantCameraView2 r0, ValueAnimator r1) {
        r0.lambda$animateZoomReset$4(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11027$$Nest$fgetcurrentAccount(InstantCameraView2 r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdrawInitialPlaceholder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11028$$Nest$fgetdrawInitialPlaceholder(InstantCameraView2 r0) {
        return r0.drawInitialPlaceholder;
    }

    /* renamed from: -$$Nest$fgetlastVisibleDurationMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11029$$Nest$fgetlastVisibleDurationMs(InstantCameraView2 r2) {
        return r2.lastVisibleDurationMs;
    }

    /* renamed from: -$$Nest$fgetmuteImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m11030$$Nest$fgetmuteImageView(InstantCameraView2 r0) {
        return r0.muteImageView;
    }

    /* renamed from: -$$Nest$fgetpreviewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11031$$Nest$fgetpreviewContainer(InstantCameraView2 r0) {
        return r0.previewContainer;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoProgressView m11032$$Nest$fgetprogressView(InstantCameraView2 r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetrecordingGuid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11033$$Nest$fgetrecordingGuid(InstantCameraView2 r0) {
        return r0.recordingGuid;
    }

    /* renamed from: -$$Nest$fgetresumeNotificationPending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11034$$Nest$fgetresumeNotificationPending(InstantCameraView2 r0) {
        return r0.resumeNotificationPending;
    }

    /* renamed from: -$$Nest$fgetsession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession m11035$$Nest$fgetsession(InstantCameraView2 r0) {
        return r0.session;
    }

    /* renamed from: -$$Nest$fgetstartedNotificationSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11036$$Nest$fgetstartedNotificationSent(InstantCameraView2 r0) {
        return r0.startedNotificationSent;
    }

    /* renamed from: -$$Nest$fgetstateInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoSession.StateInfo m11037$$Nest$fgetstateInfo(InstantCameraView2 r0) {
        return r0.stateInfo;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m11038$$Nest$fgettextureView(InstantCameraView2 r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetupload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TelegramRoundVideoUpload m11039$$Nest$fgetupload(InstantCameraView2 r0) {
        return r0.upload;
    }

    /* renamed from: -$$Nest$fgetvideoEditedInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoEditedInfo m11040$$Nest$fgetvideoEditedInfo(InstantCameraView2 r0) {
        return r0.videoEditedInfo;
    }

    /* renamed from: -$$Nest$fgetvisibilityAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m11041$$Nest$fgetvisibilityAnimator(InstantCameraView2 r0) {
        return r0.visibilityAnimator;
    }

    /* renamed from: -$$Nest$fputcapabilities, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11042$$Nest$fputcapabilities(InstantCameraView2 r0, RoundVideoSession.CameraCapabilities r1) {
        r0.capabilities = r1;
    }

    /* renamed from: -$$Nest$fputlastVisibleDurationMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11043$$Nest$fputlastVisibleDurationMs(InstantCameraView2 r0, long r1) {
        r0.lastVisibleDurationMs = r1;
    }

    /* renamed from: -$$Nest$fputmuted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11044$$Nest$fputmuted(InstantCameraView2 r0, boolean r1) {
        r0.muted = r1;
    }

    /* renamed from: -$$Nest$fputresumeNotificationPending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11045$$Nest$fputresumeNotificationPending(InstantCameraView2 r0, boolean r1) {
        r0.resumeNotificationPending = r1;
    }

    /* renamed from: -$$Nest$fputsetVisibilityFromPause, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11046$$Nest$fputsetVisibilityFromPause(InstantCameraView2 r0, boolean r1) {
        r0.setVisibilityFromPause = r1;
    }

    /* renamed from: -$$Nest$fputstartedNotificationSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11047$$Nest$fputstartedNotificationSent(InstantCameraView2 r0, boolean r1) {
        r0.startedNotificationSent = r1;
    }

    /* renamed from: -$$Nest$fputstateInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11048$$Nest$fputstateInfo(InstantCameraView2 r0, RoundVideoSession.StateInfo r1) {
        r0.stateInfo = r1;
    }

    /* renamed from: -$$Nest$fputupload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11049$$Nest$fputupload(InstantCameraView2 r0, TelegramRoundVideoUpload r1) {
        r0.upload = r1;
    }

    /* renamed from: -$$Nest$fputvideoEditedInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11050$$Nest$fputvideoEditedInfo(InstantCameraView2 r0, VideoEditedInfo r1) {
        r0.videoEditedInfo = r1;
    }

    /* renamed from: -$$Nest$mcompleteSend, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11051$$Nest$mcompleteSend(InstantCameraView2 r0, RoundVideoSession.Result r1) {
        r0.completeSend(r1);
    }

    /* renamed from: -$$Nest$mcreateVideoEditedInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoEditedInfo m11052$$Nest$mcreateVideoEditedInfo(InstantCameraView2 r0, File r1, long r2, TelegramRoundVideoUpload.UploadInfo r4) {
        return r0.createVideoEditedInfo(r1, r2, r4);
    }

    /* renamed from: -$$Nest$mhideCameraSwitchPlaceholder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11053$$Nest$mhideCameraSwitchPlaceholder(InstantCameraView2 r0) {
        r0.hideCameraSwitchPlaceholder();
    }

    /* renamed from: -$$Nest$mhideInitialPlaceholder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11054$$Nest$mhideInitialPlaceholder(InstantCameraView2 r0) {
        r0.hideInitialPlaceholder();
    }

    /* renamed from: -$$Nest$monPreviewTextureInvalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11055$$Nest$monPreviewTextureInvalidated(InstantCameraView2 r0) {
        r0.onPreviewTextureInvalidated();
    }

    /* renamed from: -$$Nest$mpostRecordStopped, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11056$$Nest$mpostRecordStopped(InstantCameraView2 r0, int r1) {
        r0.postRecordStopped(r1);
    }

    /* renamed from: -$$Nest$msaveLastCameraBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11057$$Nest$msaveLastCameraBitmap(InstantCameraView2 r0, boolean r1) {
        r0.saveLastCameraBitmap(r1);
    }

    /* renamed from: -$$Nest$msetRecordingUiFrameClockActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11058$$Nest$msetRecordingUiFrameClockActive(InstantCameraView2 r0, boolean r1) {
        r0.setRecordingUiFrameClockActive(r1);
    }

    /* renamed from: -$$Nest$mshowCameraSwitchPlaceholder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11059$$Nest$mshowCameraSwitchPlaceholder(InstantCameraView2 r0) {
        r0.showCameraSwitchPlaceholder();
    }

    /* renamed from: -$$Nest$mstartHeavyOperations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11060$$Nest$mstartHeavyOperations(InstantCameraView2 r0) {
        r0.startHeavyOperations();
    }

    /* renamed from: -$$Nest$mstopHeavyOperations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11061$$Nest$mstopHeavyOperations(InstantCameraView2 r0) {
        r0.stopHeavyOperations();
    }

    /* renamed from: -$$Nest$mupdateButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11062$$Nest$mupdateButtons(InstantCameraView2 r0) {
        r0.updateButtons();
    }

    /* renamed from: -$$Nest$mupdateRecordingUiFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11063$$Nest$mupdateRecordingUiFrame(InstantCameraView2 r0) {
        r0.updateRecordingUiFrame();
    }

    public InstantCameraView2(Context r22, InstantCameraView.Delegate r23, Theme.ResourcesProvider r24, boolean r25) {
        super(r22);
        this.currentAccount = UserConfig.selectedAccount;
        this.screenPosition = new int[2];
        this.previewBitmapMatrix = new Matrix();
        this.previewBitmapMatrixValues = new float[9];
        this.previewBitmapPaint = new Paint(3);
        this.zoomPointerId1 = -1;
        this.zoomPointerId2 = -1;
        this.previousWindowBrightness = Float.NaN;
        this.recordingUiFallback = new InstantCameraView2$$ExternalSyntheticLambda1(this);
        this.sessionListener = new AnonymousClass4(this);
        this.delegate = r23;
        this.resourcesProvider = r24;
        this.recordingGuid = r23.getClassGuid();
        this.secretChat = r23.isSecretChat();
        this.parentView = r23.getFragmentView();
        setWillNotDraw(false);
        FlashViews r5 = new FlashViews(r22, null, this, null);
        this.flashViews = r5;
        r5.setWarmth(0.5f);
        addView(r5.backgroundView, LayoutHelper.createFrame(-1, -1, 119));
        CameraContainer r6 = new CameraContainer(this, r22);
        this.cameraContainer = r6;
        RoundVideoProgressView r8 = new RoundVideoProgressView(r22);
        this.progressView = r8;
        FrameLayout r9 = new FrameLayout(r22);
        this.previewContainer = r9;
        TextureView r10 = createTextureView(r22);
        this.textureView = r10;
        r9.addView(r10, LayoutHelper.createFrame(-1, -1, 119));
        Paint r11 = new Paint(1);
        r11.setColor(Color.argb(40, 0, 0, 0));
        AnonymousClass1 r13 = new AnonymousClass1(this, r22, r11);
        this.textureOverlayView = r13;
        r13.setOutlineProvider(new AnonymousClass2(this));
        r13.setClipToOutline(true);
        r9.addView(r13, LayoutHelper.createFrame(-1, -1, 119));
        r8.addView(r9, LayoutHelper.createFrame(-1, -1.0f, 119, 14.0f, 14.0f, 14.0f, 14.0f));
        r6.addView(r8, LayoutHelper.createFrame(-1, -1, 119));
        int r92 = AndroidUtilities.roundPlayingMessageSize;
        addView(r6, new FrameLayout.LayoutParams(r92, r92, 17));
        addView(r5.foregroundView, LayoutHelper.createFrame(-1, -1, 119));
        LinearLayout r3 = new LinearLayout(r22);
        this.buttonsLayout = r3;
        r3.setOrientation(0);
        r3.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        addView(r3, LayoutHelper.createFrame(-2, 56.0f, 83, 1.0f, 0.0f, 0.0f, 0.0f));
        FlashViews.ImageViewInvertable r4 = new FlashViews.ImageViewInvertable(r22);
        this.switchCameraButton = r4;
        ImageView.ScaleType r62 = ImageView.ScaleType.CENTER;
        r4.setScaleType(r62);
        r4.setContentDescription(LocaleController.getString(R.string.AccDescrSwitchCamera));
        r3.addView(r4, LayoutHelper.createLinear(44, 44));
        r4.setOnClickListener(new InstantCameraView2$$ExternalSyntheticLambda2(this));
        FlashViews.ImageViewInvertable r82 = new FlashViews.ImageViewInvertable(r22);
        this.flashButton = r82;
        r82.setScaleType(r62);
        r3.addView(r82, LayoutHelper.createLinear(44, 44));
        r82.setOnClickListener(new InstantCameraView2$$ExternalSyntheticLambda3(this));
        if (r25 == false) goto L5;
        float r32 = 24.0f;
    L6:
        int r33 = AndroidUtilities.dp(r32);
        this.buttonIconSize = r33;
        RLottieDrawable r7 = new RLottieDrawable(R.raw.roundcamera_flip, r33, r33);
        this.switchCameraDrawable = r7;
        r7.setCallback(r4);
        RLottieDrawable r34 = this.switchCameraDrawable;
        r34.setCurrentFrame(r34.getFramesCount() - 1);
        r4.setImageDrawable(this.switchCameraDrawable);
        updateFlashIcon();
        if (r25 == true) goto L9;
        r5.add(r4);
        r5.add(r82);
    L13:
        ImageView r2 = new ImageView(r22);
        this.muteImageView = r2;
        r2.setScaleType(r62);
        r2.setImageResource(R.drawable.video_mute);
        r2.setAlpha(0.0f);
        addView(r2, LayoutHelper.createFrame(48, 48, 17));
        r10.setOnTouchListener(new InstantCameraView2$$ExternalSyntheticLambda4(this));
        super.setVisibility(4);
        return;
    L9:
        if (r24 == null) goto L13;
        if (r24.isDark() == true) goto L13;
        r4.setInvert(0.6f);
        r82.setInvert(0.6f);
        goto L13
    L5:
        r32 = 28.0f;
        goto L6
    }

    private void animateZoomReset() {
        if (this.session != null) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.zoomResetAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.zoomProgress, 0.0f});
        this.zoomResetAnimator = r02;
        r02.setDuration(350);
        this.zoomResetAnimator.addUpdateListener(new InstantCameraView2$$ExternalSyntheticLambda6(this));
        this.zoomResetAnimator.start();
    }

    private void applyExternalTrim() {
        if (this.session == null) goto L15;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L7;
        return;
    L7:
        if (this.videoEditedInfo == null) goto L17;
        long r02 = r0.getRecordedDurationMs();
        long r2 = Math.max(0, this.videoEditedInfo.startTime);
        long r6 = this.videoEditedInfo.endTime;
        if (r6 < 0) goto L13;
        r02 = Math.min(r02, r6);
    L13:
        this.session.setTrimRange(r2, r02);
        return;
    L17:
        return;
    }

    private Bitmap applyPreviewBitmapTransform(Bitmap r7) {
        if (this.textureView.getWidth() > 0) goto L5;
    L12:
        return r7;
    L5:
        if (this.textureView.getHeight() <= 0) goto L12;
        this.textureView.getTransform(this.previewBitmapMatrix);
        if (this.previewBitmapMatrix.isIdentity() == true) goto L12;
        this.previewBitmapMatrix.getValues(this.previewBitmapMatrixValues);
        float r0 = r7.getWidth() / this.textureView.getWidth();
        float r1 = r7.getHeight() / this.textureView.getHeight();
        float[] r2 = this.previewBitmapMatrixValues;
        r2[1] = r2[1] * (r0 / r1);
        r2[2] = r2[2] * r0;
        r2[3] = r2[3] * (r1 / r0);
        r2[5] = r2[5] * r1;
        r2[6] = r2[6] / r0;
        r2[7] = r2[7] / r1;
        this.previewBitmapMatrix.setValues(r2);
        Bitmap r02 = Bitmap.createBitmap(r7.getWidth(), r7.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(r02).drawBitmap(r7, this.previewBitmapMatrix, this.previewBitmapPaint);
        r7.recycle();
        return r02;
    }

    private static float clamp(float r0, float r1, float r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    private void completeSend(RoundVideoSession.Result r24) {
        SendOptions r1 = this.pendingSend;
        if (r1 != null) goto L5;
        return;
    L5:
        this.pendingSend = null;
        this.sent = true;
        TelegramRoundVideoUpload r4 = this.upload;
        if (r4 != null) goto L8;
        TelegramRoundVideoUpload.UploadInfo r42 = null;
    L9:
        VideoEditedInfo r10 = createVideoEditedInfo(r24.getFile(), r24.getDurationMs(), r42);
        r10.muted = true ^ r24.hasAudio();
        MediaController.PhotoEntry r9 = new MediaController.PhotoEntry(0, 0, 0, r24.getFile().getAbsolutePath(), 0, true, 0, 0, 0);
        r9.ttl = r1.ttl;
        r9.effectId = r1.effectId;
        this.delegate.sendMedia(r9, r10, r1.notify, r1.scheduleDate, r1.scheduleRepeatPeriod, false, r1.stars);
        TelegramRoundVideoUpload r12 = this.upload;
        if (r12 == null) goto L12;
        r12.release(false);
    L12:
        this.upload = null;
        MediaController.getInstance().requestRecordAudioFocus(false);
        return;
    L8:
        r42 = r4.getUploadInfo(r24.getOutputId(), r24.getFile());
        goto L9
    }

    private TextureView createTextureView(Context r3) {
        AnonymousClass5 r0 = new AnonymousClass5(this, r3);
        r0.setOpaque(true);
        r0.setClickable(true);
        r0.setCameraDistance(AndroidUtilities.dp(8000.0f));
        r0.setOutlineProvider(new AnonymousClass6(this));
        r0.setClipToOutline(true);
        return r0;
    }

    private VideoEditedInfo createVideoEditedInfo(File r4, long r5, TelegramRoundVideoUpload.UploadInfo r7) {
        VideoEditedInfo r0 = new VideoEditedInfo();
        r0.startTime = -1;
        r0.endTime = -1;
        r0.estimatedDuration = r5;
        if (r7 != null) goto L5;
        long r52 = r4.length();
    L6:
        r0.estimatedSize = Math.max(1, r52);
        r0.roundVideo = true;
        RoundVideoSession r53 = this.session;
        if (r53 != null) goto L9;
    L12:
        int r54 = RoundVideoSession.FrameRate.FPS_30.getValue();
    L13:
        r0.framerate = r54;
        RoundVideoSession.OutputResolution r3 = this.activeOutputResolution;
        if (r3 != null) goto L16;
        int r32 = RoundVideoSession.OutputResolution.P480.getSize();
    L17:
        r0.originalWidth = r32;
        r0.resultWidth = r32;
        r0.originalHeight = r32;
        r0.resultHeight = r32;
        r0.originalPath = r4.getAbsolutePath();
        if (r7 == null) goto L20;
        r0.file = r7.inputFile;
        r0.encryptedFile = r7.encryptedFile;
        r0.key = r7.key;
        r0.iv = r7.iv;
    L20:
        return r0;
    L16:
        r32 = r3.getSize();
        goto L17
    L9:
        if (r53.getActiveFrameRate() == null) goto L12;
        r54 = this.session.getActiveFrameRate().getValue();
        goto L13
    L5:
        r52 = r7.size;
        goto L6
    }

    private void finishZoomGesture() {
        if (this.pinchZoomActive == true) goto L5;
        return;
    L5:
        this.pinchZoomActive = false;
        this.zoomPointerId1 = -1;
        this.zoomPointerId2 = -1;
        animateZoomReset();
    }

    private long getCurrentDurationMs() {
        if (this.session == null) goto L13;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L8;
        return 0;
    L8:
        if (r0.getState() != RoundVideoSession.State.RECORDING) goto L12;
        return this.session.getRecordedDurationMs();
    L12:
        return this.stateInfo.getRecordedDurationMs();
    L13:
        return 0;
    }

    private boolean handleZoomTouch(View r6, MotionEvent r7) {
        boolean r0 = false;
        if (this.session != null) goto L5;
    L65:
        return false;
    L5:
        if (this.stateInfo == null) goto L65;
        if (r7.getActionMasked() != 0) goto L16;
        ValueAnimator r62 = this.zoomResetAnimator;
        if (r62 == null) goto L12;
        r62.cancel();
    L12:
        this.pinchZoomActive = false;
        this.zoomPointerId1 = r7.getPointerId(0);
        this.zoomPointerId2 = -1;
        if (this.stateInfo.getState() != RoundVideoSession.State.PREVIEWING) goto L15;
        togglePreviewMuted();
    L15:
        return true;
    L16:
        float r4 = 0.0f;
        if (r7.getActionMasked() != 5) goto L28;
        if (r7.getPointerCount() != 2) goto L28;
        if (this.stateInfo.canControlCamera() == false) goto L28;
        this.zoomPointerId1 = r7.getPointerId(0);
        this.zoomPointerId2 = r7.getPointerId(1);
        float r63 = pointerDistance(r7, 0, 1);
        this.pinchStartDistance = r63;
        if (r63 <= 0.0f) goto L25;
        r0 = true;
    L25:
        this.pinchZoomActive = r0;
        this.zoomProgress = 0.0f;
        return true;
    L28:
        if (r7.getActionMasked() != 2) goto L48;
        if (this.pinchZoomActive == false) goto L48;
        int r64 = r7.findPointerIndex(this.zoomPointerId1);
        int r02 = r7.findPointerIndex(this.zoomPointerId2);
        if (r64 < 0) goto L45;
        if (r02 < 0) goto L45;
        float r65 = pointerDistance(r7, r64, r02) / this.pinchStartDistance;
        RoundVideoSession.CameraCapabilities r72 = this.capabilities;
        if (r72 != null) goto L38;
        float r73 = 1.0f;
    L39:
        float r66 = (Math.max(0.0f, r65 - 1.0f) / 1.5f) + 1.0f;
        if (r73 <= 1.0f) goto L43;
        r4 = clamp((r66 - 1.0f) / (r73 - 1.0f), 0.0f, 1.0f);
    L43:
        this.zoomProgress = r4;
        this.session.setZoom(r4);
        return true;
    L38:
        r73 = r72.getMaximumZoomRatio();
    L45:
        finishZoomGesture();
        return true;
    L48:
        if (r7.getActionMasked() != 6) goto L58;
        if (this.pinchZoomActive == false) goto L58;
        int r67 = r7.getPointerId(r7.getActionIndex());
        if (r67 != this.zoomPointerId1) goto L54;
    L55:
        finishZoomGesture();
    L56:
        return true;
    L54:
        if (r67 != this.zoomPointerId2) goto L56;
    L58:
        if (r7.getActionMasked() == 1) goto L62;
        if (r7.getActionMasked() == 3) goto L62;
    L64:
        return true;
    L62:
        if (this.pinchZoomActive == false) goto L64;
        finishZoomGesture();
        goto L64
    }

    private void hideCameraSwitchPlaceholder() {
        if (this.switchPlaceholderVisible == true) goto L5;
        return;
    L5:
        this.switchPlaceholderVisible = false;
        this.textureOverlayView.animate().cancel();
        this.textureOverlayView.animate().alpha(0.0f).setDuration(100).setInterpolator(new DecelerateInterpolator()).start();
    }

    private void hideInitialPlaceholder() {
        if (this.drawInitialPlaceholder == true) goto L5;
        return;
    L5:
        this.drawInitialPlaceholder = false;
        this.textureOverlayView.invalidate();
        this.textureOverlayView.animate().cancel();
        this.textureOverlayView.animate().alpha(0.0f).setDuration(120).setInterpolator(new DecelerateInterpolator()).start();
    }

    private boolean isRecordingState() {
        RoundVideoSession.StateInfo r1 = this.stateInfo;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getState() != RoundVideoSession.State.RECORDING) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$animateZoomReset$4(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.zoomProgress = r12;
        RoundVideoSession r0 = this.session;
        if (r0 == null) goto L6;
        r0.setZoom(r12);
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        if (isRecordingState() == false) goto L13;
        long r0 = SystemClock.elapsedRealtimeNanos();
        long r2 = this.lastPreviewUiFrameRealtimeNs;
        if (r2 != 0) goto L8;
    L11:
        updateRecordingUiFrame();
        return;
    L8:
        if ((r0 - r2) >= 70000000) goto L11;
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        switchCamera();
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        toggleFlash();
    }

    private /* synthetic */ void lambda$startAnimation$3(boolean r2, ValueAnimator r3) {
        if (r2 == false) goto L4;
        float r22 = 0.0f;
    L5:
        this.animationTranslationY = r22;
        updateTranslationY();
        return;
    L4:
        r22 = (getMeasuredHeight() * 0.5f) * ((Float) r3.getAnimatedValue()).floatValue();
        goto L5
    }

    private void onPreviewTextureInvalidated() {
        if (this.handlingPreviewTextureInvalidate == false) goto L5;
        return;
    L5:
        if (isRecordingState() == false) goto L17;
        this.handlingPreviewTextureInvalidate = true;
        this.lastPreviewUiFrameRealtimeNs = SystemClock.elapsedRealtimeNanos();     // Catch: Throwable -> L11
        updateRecordingUiFrame();     // Catch: Throwable -> L11
        this.handlingPreviewTextureInvalidate = false;
        return;
    L11:
        th = move-exception;
        this.handlingPreviewTextureInvalidate = false;
        throw th;
    }

    private void pauseForPreview() {
        if (this.session == null) goto L11;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getState() != RoundVideoSession.State.RECORDING) goto L13;
        postRecordStopped(2);
        this.session.pauseRecording();
        return;
    L13:
        return;
    }

    private static float pointerDistance(MotionEvent r2, int r3, int r4) {
        return (float) Math.hypot(r2.getX(r4) - r2.getX(r3), r2.getY(r4) - r2.getY(r3));
    }

    private void postRecordStopped(int r3) {
        if (this.stopNotificationSent == true) goto L5;
    L7:
        this.stopNotificationSent = true;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.recordStopped, new Object[]{Integer.valueOf(this.recordingGuid), Integer.valueOf(r3)});
        return;
    L5:
        if (r3 != 2) goto L7;
    }

    private void saveLastCameraBitmap(boolean r5) {
        if (this.textureView.isAvailable() == false) goto L57;
        Bitmap r0 = this.textureView.getBitmap(180, 180);
        if (r0 == null) goto L58;
    L22:
        th = move-exception;
        if (r0 == this.lastBitmap) goto L45;
        r0.recycle();
    L45:
        throw th;
    L9:
        if (r0.getWidth() == 0) goto L39;
        if (r0.getHeight() == 0) goto L39;
        if (r0.getPixel(r0.getWidth() / 2, r0.getHeight() / 2) == 0) goto L39;
        r0 = applyPreviewBitmapTransform(r0);     // Catch: Throwable -> L22
        Utilities.stackBlurBitmap(r0, 15);     // Catch: Throwable -> L22
        Bitmap r1 = this.lastBitmap;     // Catch: Throwable -> L22
        this.lastBitmap = r0;     // Catch: Throwable -> L22
        if (r1 == null) goto L24;
        if (r1 == r0) goto L24;
        if (r1.isRecycled() == true) goto L24;
        r1.recycle();     // Catch: Throwable -> L22
    L24:
        if (r5 == false) goto L36;
        FileOutputStream r52 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "icthumb.jpg"));     // Catch: Throwable -> L46
        r0.compress(Bitmap.CompressFormat.JPEG, 87, r52);     // Catch: Throwable -> L29
        r52.close();     // Catch: Throwable -> L46
        goto L36
    L29:
        th = move-exception;
        r52.close();     // Catch: Throwable -> L32
    L34:
        throw th;     // Catch: Throwable -> L46
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L46
    L36:
        if (r0 == this.lastBitmap) goto L54;
        r0.recycle();
        return;
    L54:
        return;
    L39:
        if (r0 == this.lastBitmap) goto L56;
        r0.recycle();
        return;
    L56:
        return;
    L58:
        return;
    }

    private void setRecordingUiFrameClockActive(boolean r4) {
        if (r4 != this.recordingUiFallbackRegistered) goto L5;
        return;
    L5:
        this.recordingUiFallbackRegistered = r4;
        this.lastPreviewUiFrameRealtimeNs = 0;
        if (r4 == false) goto L8;
        Choreographer60FpsContent.getInstance().addFrameCallback(this.recordingUiFallback, 30);
    L9:
        dispatchRecordingUiFrameClockActive(r4);
        return;
    L8:
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.recordingUiFallback);
        goto L9
    }

    private void setScreenFlashEnabled(boolean r4) {
        Activity r0 = this.delegate.getParentActivity();
        if (r0 != null) goto L5;
        return;
    L5:
        WindowManager.LayoutParams r1 = r0.getWindow().getAttributes();
        float r2 = this.previousWindowBrightness;
        if (r4 == false) goto L12;
        if (Float.isNaN(r2) == false) goto L10;
        this.previousWindowBrightness = r1.screenBrightness;
    L10:
        r1.screenBrightness = 1.0f;
        this.flashViews.flashIn(null);
    L15:
        r0.getWindow().setAttributes(r1);
        return;
    L12:
        if (Float.isNaN(r2) == true) goto L14;
        r1.screenBrightness = this.previousWindowBrightness;
        this.previousWindowBrightness = Float.NaN;
    L14:
        this.flashViews.flashOut();
        goto L15
    }

    private void showCameraSwitchPlaceholder() {
        saveLastCameraBitmap(false);
        Bitmap r0 = this.lastBitmap;
        if (r0 != null) goto L5;
        return;
    L5:
        this.textureOverlayView.setImageBitmap(r0);
        this.switchPlaceholderVisible = true;
        this.textureOverlayView.animate().cancel();
        this.textureOverlayView.setAlpha(1.0f);
    }

    private void showInitialPlaceholder() {
        if (this.drawInitialPlaceholder == false) goto L6;
        return;
    L6:
        if (this.lastBitmap == null) goto L15;
    L8:
        Bitmap r0 = this.lastBitmap;
        BackupImageView r1 = this.textureOverlayView;
        if (r0 == null) goto L11;
        r1.setImageBitmap(r0);
    L12:
        this.drawInitialPlaceholder = true;
        this.textureOverlayView.animate().cancel();
        this.textureOverlayView.setAlpha(1.0f);
        this.textureOverlayView.invalidate();
        return;
    L11:
        r1.setImageResource(R.drawable.icplaceholder);
        goto L12
    L15:
        this.lastBitmap = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "icthumb.jpg").getAbsolutePath());     // Catch: Throwable -> L14
        goto L8
    }

    private void startHeavyOperations() {
        if (this.heavyOperationsStopped == true) goto L5;
        return;
    L5:
        this.heavyOperationsStopped = false;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
    }

    private void stopHeavyOperations() {
        if (this.heavyOperationsStopped == false) goto L5;
        return;
    L5:
        this.heavyOperationsStopped = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
    }

    private void switchCamera() {
        if (this.session == null) goto L18;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L7;
        return;
    L7:
        if (this.capabilities != null) goto L9;
        return;
    L9:
        if (r0.canControlCamera() == false) goto L22;
        RoundVideoSession.CameraFacing r02 = this.capabilities.getRequestedFacing();
        RoundVideoSession.CameraFacing r1 = RoundVideoSession.CameraFacing.FRONT;
        if (r02 != r1) goto L15;
        r1 = RoundVideoSession.CameraFacing.BACK;
    L15:
        if (this.session.setCameraFacing(r1) == false) goto L21;
        this.switchCameraDrawable.setCurrentFrame(0);
        this.switchCameraDrawable.start();
        return;
    L21:
        return;
    L22:
        return;
    }

    private void toggleFlash() {
        RoundVideoSession r0 = this.session;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.stateInfo != null) goto L7;
        return;
    L7:
        if (this.capabilities != null) goto L10;
        return;
    L10:
        if (r0.setFlashEnabled(!r1.isFlashEnabled()) == false) goto L15;
        updateFlashIcon();
        return;
    }

    private void togglePreviewMuted() {
        if (this.session == null) goto L18;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getState() != RoundVideoSession.State.PREVIEWING) goto L20;
        boolean r02 = !this.muted;
        this.muted = r02;
        this.session.setPreviewMuted(r02);
        VideoEditedInfo r03 = this.videoEditedInfo;
        if (r03 == null) goto L12;
        r03.muted = this.muted;
    L12:
        this.muteImageView.animate().cancel();
        ViewPropertyAnimator r04 = this.muteImageView.animate();
        if (this.muted == false) goto L15;
        float r2 = 1.0f;
    L16:
        r04.alpha(r2).setDuration(180).start();
        return;
    L15:
        r2 = 0.0f;
        goto L16
    L20:
        return;
    }

    private void updateButtons() {
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        this.switchCameraButton.setEnabled(r02);
        FlashViews.ImageViewInvertable r3 = this.flashButton;
        if (r02 == false) goto L15;
        RoundVideoSession.CameraCapabilities r03 = this.capabilities;
        if (r03 == null) goto L15;
        if (r03.getFlashType() == RoundVideoSession.FlashType.NONE) goto L15;
        r1 = true;
    L15:
        r3.setEnabled(r1);
        updateFlashIcon();
        return;
    L5:
        if (r0.canControlCamera() == false) goto L7;
        r02 = true;
        goto L8
    }

    private void updateFlashIcon() {
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 == null) goto L12;
        if (r0.isFlashEnabled() == false) goto L12;
        if (this.flashOffDrawable != null) goto L9;
        int r1 = R.raw.roundcamera_flash_off;
        int r2 = this.buttonIconSize;
        RLottieDrawable r02 = new RLottieDrawable(r1, r2, r2);
        this.flashOffDrawable = r02;
        r02.setCallback(this.flashButton);
    L9:
        this.flashButton.setImageDrawable(this.flashOffDrawable);
        return;
    L12:
        if (this.flashOnDrawable != null) goto L14;
        int r12 = R.raw.roundcamera_flash_on;
        int r22 = this.buttonIconSize;
        RLottieDrawable r03 = new RLottieDrawable(r12, r22, r22);
        this.flashOnDrawable = r03;
        r03.setCallback(this.flashButton);
    L14:
        this.flashButton.setImageDrawable(this.flashOnDrawable);
    }

    private void updateRecordingUiFrame() {
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getState() != RoundVideoSession.State.RECORDING) goto L10;
        long r1 = Math.min(r0.getMaximumDurationMs(), (r0.getRecordedDurationMs() + SystemClock.elapsedRealtime()) - r0.getRecordingStartedAtRealtimeMs());
        this.lastVisibleDurationMs = r1;
        this.progressView.setProgress(r1 / r0.getMaximumDurationMs());
        dispatchRecordingUiFrame(r1);
        return;
    }

    private void updateTranslationY() {
        this.cameraContainer.setTranslationY(this.animationTranslationY + this.panTranslationY);
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void cancel(boolean r3) {
        if (this.session != null) goto L6;
        return;
    L6:
        if (r3 == false) goto L8;
        int r32 = 0;
    L9:
        postRecordStopped(r32);
        this.session.cancel();
        TelegramRoundVideoUpload r33 = this.upload;
        if (r33 == null) goto L12;
        r33.release(true);
    L12:
        this.upload = null;
        MediaController.getInstance().requestRecordAudioFocus(false);
        startAnimation(false, false);
        return;
    L8:
        r32 = 6;
        goto L9
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void changeVideoPreviewState(int r3, float r4) {
        if (this.session == null) goto L21;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getState() != RoundVideoSession.State.PREVIEWING) goto L24;
        applyExternalTrim();
        if (r3 != 0) goto L14;
        this.session.playPreview();
        return;
    L14:
        if (r3 != 1) goto L18;
        this.session.pausePreview();
        return;
    L18:
        if (r3 != 2) goto L23;
        this.session.seekPreview((long) (r4 * this.stateInfo.getRecordedDurationMs()));
        return;
    L23:
        return;
    L24:
        return;
    }

    public void destroy(boolean r4) {
        setRecordingUiFrameClockActive(false);
        ValueAnimator r0 = this.zoomResetAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        RoundVideoSession r02 = this.session;
        if (r02 == null) goto L8;
        r02.release();
        this.session = null;
    L8:
        TelegramRoundVideoUpload r03 = this.upload;
        if (r03 == null) goto L11;
        r03.release(!this.sent);
        this.upload = null;
    L11:
        setScreenFlashEnabled(false);
        MediaController.getInstance().requestRecordAudioFocus(false);
        startHeavyOperations();
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public View getButtonsLayout() {
        return this.buttonsLayout;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public InstantCameraViewBase.InstantViewCameraContainer getCameraContainer() {
        return this.cameraContainer;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public RectF getCameraRect() {
        this.textureView.getLocationOnScreen(this.screenPosition);
        return new RectF(this.screenPosition[0], r1[1], r2 + this.textureView.getWidth(), this.screenPosition[1] + this.textureView.getHeight());
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public View getMuteImageView() {
        return this.muteImageView;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public Paint getPaint() {
        return this.progressView.getPaint();
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public TextureView getTextureView() {
        return this.textureView;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void hideCamera(boolean r2) {
        destroy(r2);
        this.cameraContainer.setTranslationX(0.0f);
        this.animationTranslationY = 0.0f;
        updateTranslationY();
        this.cameraContainer.setImageReceiver(null);
        MediaController.getInstance().resumeByRewind();
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public boolean isPaused() {
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getState() != RoundVideoSession.State.PAUSING) goto L7;
        return true;
    L7:
        if (this.stateInfo.getState() != RoundVideoSession.State.PREVIEWING) goto L9;
        return true;
    L9:
        if (this.stateInfo.getState() != RoundVideoSession.State.RESUMING) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (getParent() == null) goto L6;
        getParent().requestDisallowInterceptTouchEvent(true);
    L6:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        if ((View.MeasureSpec.getSize(r6) - getPaddingBottom()) <= (View.MeasureSpec.getSize(r5) * 1.3f)) goto L5;
        int r0 = AndroidUtilities.roundPlayingMessageSize;
    L7:
        if (this.textureViewSize == r0) goto L9;
        this.textureViewSize = r0;
        this.cameraContainer.getLayoutParams().width = AndroidUtilities.dp(28.0f) + r0;
        this.cameraContainer.getLayoutParams().height = AndroidUtilities.dp(28.0f) + r0;
        ((FrameLayout.LayoutParams) this.muteImageView.getLayoutParams()).topMargin = (r0 / 2) - AndroidUtilities.dp(24.0f);
    L9:
        super.onMeasure(r5, r6);
        int r52 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30);
        int r62 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30);
        this.flashViews.backgroundView.measure(r52, r62);
        this.flashViews.foregroundView.measure(r52, r62);
        return;
    L5:
        r0 = AndroidUtilities.roundMessageSize;
        goto L7
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void onPanTranslationUpdate(float r2) {
        this.panTranslationY = r2 * 0.5f;
        updateTranslationY();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (r2.getAction() == 0) goto L5;
        return true;
    L5:
        if (r2.getY() <= (getMeasuredHeight() - getPaddingBottom())) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void send(int r11, boolean r12, int r13, int r14, int r15, long r16, long r18) {
        if (this.session != null) goto L5;
        return;
    L5:
        if (this.stateInfo != null) goto L8;
        return;
    L8:
        if (r11 != 3) goto L12;
        pauseForPreview();
        return;
    L12:
        if (r11 != 1) goto L14;
    L16:
        long r1 = getCurrentDurationMs();
        if (r1 >= 800) goto L21;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.audioRecordTooShort, new Object[]{Integer.valueOf(this.recordingGuid), Boolean.TRUE, Integer.valueOf((int) r1)});
        cancel(false);
        return;
    L21:
        if (this.stateInfo.getState() != RoundVideoSession.State.RECORDING) goto L23;
        postRecordStopped(5);
    L24:
        this.pendingSend = new SendOptions(r12, r13, r14, r15, r16, r18);
        this.session.finish(!this.muted);
        return;
    L23:
        applyExternalTrim();
        goto L24
    L14:
        if (r11 == 4) goto L16;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void setButtonsBackground(BlurredBackgroundDrawableViewFactory r2, BlurredBackgroundColorProvider r3) {
        BlurredBackgroundDrawable r22 = r2.create(this.buttonsLayout, r3);
        r22.setPadding(AndroidUtilities.dp(6.0f));
        r22.setRadius(AndroidUtilities.dp(21.0f));
        this.buttonsLayout.setBackground(r22);
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void setInternalPadding(int r2) {
        setPadding(0, 0, 0, r2);
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void setIsMessageTransition(boolean r1) {
        CameraContainer.m11064$$Nest$fputmessageTransition(this.cameraContainer, r1);
    }

    @Override // android.view.View
    public void setVisibility(int r6) {
        super.setVisibility(r6);
        this.buttonsLayout.setAlpha(0.0f);
        this.cameraContainer.setAlpha(0.0f);
        CameraContainer r0 = this.cameraContainer;
        float r3 = 0.1f;
        if (this.setVisibilityFromPause == false) goto L5;
        float r2 = 1.0f;
    L6:
        r0.setScaleX(r2);
        CameraContainer r02 = this.cameraContainer;
        if (this.setVisibilityFromPause == false) goto L9;
        r3 = 1.0f;
    L9:
        r02.setScaleY(r3);
        this.cameraContainer.setTranslationX(0.0f);
        this.muteImageView.setAlpha(0.0f);
        this.muteImageView.setScaleX(1.0f);
        this.muteImageView.setScaleY(1.0f);
        this.progressView.getPaint().setAlpha(0);
        Activity r5 = (Activity) getContext();     // Catch: Exception -> L16
        if (r6 != 0) goto L14;
        r5.getWindow().addFlags(128);     // Catch: Exception -> L16
        return;
    L14:
        r5.getWindow().clearFlags(128);     // Catch: Exception -> L16
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        r2 = 0.1f;
        goto L6
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void showCamera(boolean r5) {
        if (this.session == null) goto L5;
        return;
    L5:
        this.setVisibilityFromPause = r5;
        setVisibility(0);
        this.progressView.getPaint().setAlpha(255);
        this.sent = false;
        this.muted = false;
        this.startedNotificationSent = false;
        this.stopNotificationSent = false;
        this.lastVisibleDurationMs = 0;
        this.progressView.setProgress(0.0f);
        showInitialPlaceholder();
        this.upload = new TelegramRoundVideoUpload(this.currentAccount, this.secretChat);
        this.activeOutputResolution = (RoundVideoSession.OutputResolution) SharedSettings.roundVideoOutputResolution.get();
        this.session = new RoundVideoSession.Builder(getContext(), this.textureView).setOutputDirectory(new File(ApplicationLoader.getFilesDirFixed(), "cache")).setInitialFacing((RoundVideoSession.CameraFacing) SharedSettings.roundVideoLastCamera.get()).setOutputResolution(this.activeOutputResolution).setVideoBitrate(SharedSettings.roundVideoVideoBitrate.get()).setCameraResolution((RoundVideoSession.CameraResolution) SharedSettings.roundVideoCameraResolution.get()).setFrameRate((RoundVideoSession.FrameRate) SharedSettings.roundVideoFrameRate.get()).setCompositionEnabled(SharedSettings.roundVideoComposition.get()).setListener(this.sessionListener).setOutputListener(this.upload).setScreenFlashController(new InstantCameraView2$$ExternalSyntheticLambda5(this)).build();
        MediaController.getInstance().requestRecordAudioFocus(true);
        this.session.start();
        startAnimation(true, r5);
    }

    public void startAnimation(boolean r18, boolean r19) {
        dispatchAnimationState(r18, r19);
        AnimatorSet r3 = this.visibilityAnimator;
        if (r3 == null) goto L5;
        r3.removeAllListeners();
        this.visibilityAnimator.cancel();
    L5:
        PipRoundVideoView r32 = PipRoundVideoView.getInstance();
        if (r32 == null) goto L9;
        r32.showTemporary(!r18);
    L9:
        if (r18 == true) goto L11;
    L17:
        this.opened = r18;
        View r5 = this.parentView;
        if (r5 == null) goto L20;
        r5.invalidate();
    L20:
        if (r18 == false) goto L22;
    L24:
        float r52 = 0.0f;
    L25:
        if (r18 == false) goto L27;
        float r6 = 1.0f;
    L28:
        if (r18 == false) goto L30;
        float r7 = 0.0f;
    L31:
        ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{r6, r7});
        r72.addUpdateListener(new InstantCameraView2$$ExternalSyntheticLambda0(this, r19));
        AnimatorSet r2 = new AnimatorSet();
        this.visibilityAnimator = r2;
        LinearLayout r9 = this.buttonsLayout;
        if (r18 == false) goto L34;
        float r11 = 1.0f;
    L35:
        float[] r12 = {r11};
        Property r112 = View.ALPHA;
        ObjectAnimator r92 = ObjectAnimator.ofFloat(r9, r112, r12);
        CameraContainer r122 = this.cameraContainer;
        if (r18 == false) goto L38;
        float r13 = 1.0f;
    L39:
        ObjectAnimator r123 = ObjectAnimator.ofFloat(r122, r112, new float[]{r13});
        CameraContainer r132 = this.cameraContainer;
        float r14 = 0.1f;
        if (r18 == false) goto L42;
        float r15 = 1.0f;
    L43:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(r132, View.SCALE_X, new float[]{r15});
        CameraContainer r133 = this.cameraContainer;
        if (r18 == false) goto L46;
        r14 = 1.0f;
    L46:
        ObjectAnimator r134 = ObjectAnimator.ofFloat(r133, View.SCALE_Y, new float[]{r14});
        ObjectAnimator r53 = ObjectAnimator.ofFloat(this.cameraContainer, View.TRANSLATION_X, new float[]{r52});
        ImageView r142 = this.muteImageView;
        if (this.muted == false) goto L50;
        if (r18 == false) goto L50;
        float r16 = 1.0f;
    L51:
        r2.playTogether(new Animator[]{r92, r123, r33, r134, r53, ObjectAnimator.ofFloat(r142, r112, new float[]{r16}), r72});
        this.visibilityAnimator.setDuration(180);
        this.visibilityAnimator.setInterpolator(new DecelerateInterpolator());
        if (r18 == true) goto L54;
        this.visibilityAnimator.addListener(new AnonymousClass3(this));
    L55:
        this.visibilityAnimator.start();
        return;
    L54:
        setTranslationX(0.0f);
    L50:
        r16 = 0.0f;
        goto L51
    L42:
        r15 = 0.1f;
        goto L43
    L38:
        r13 = 0.0f;
        goto L39
    L34:
        r11 = 0.0f;
        goto L35
    L30:
        r7 = 1.0f;
        goto L31
    L27:
        r6 = 0.0f;
        goto L28
    L22:
        if (Math.max(getCurrentDurationMs(), this.lastVisibleDurationMs) <= 300) goto L24;
        r52 = AndroidUtilities.dp(24.0f) - (getMeasuredWidth() * 0.5f);
        goto L25
    L11:
        if (this.opened == true) goto L17;
        this.cameraContainer.setTranslationX(0.0f);
        if (r19 == false) goto L15;
        float r54 = 0.0f;
    L16:
        this.animationTranslationY = r54;
        updateTranslationY();
        goto L17
    L15:
        r54 = getMeasuredHeight() * 0.5f;
        goto L16
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void togglePause() {
        if (this.session == null) goto L15;
        RoundVideoSession.StateInfo r0 = this.stateInfo;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.getState() != RoundVideoSession.State.RECORDING) goto L12;
        pauseForPreview();
        return;
    L12:
        if (this.stateInfo.getState() != RoundVideoSession.State.PREVIEWING) goto L16;
        this.videoEditedInfo = null;
        this.resumeNotificationPending = true;
        this.stopNotificationSent = false;
        this.session.resumeRecording();
        return;
    L16:
        return;
    }
}
