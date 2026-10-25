package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.net.Uri;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.camera.core.Preview;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.CameraType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.VideoMessagesCamera;
import com.exteragram.messenger.camera.CameraDebugUtils;
import com.exteragram.messenger.camera.CameraXSession;
import com.exteragram.messenger.camera.InstantCameraZoomSlider;
import com.exteragram.messenger.camera.RoundVideoEncoder;
import com.exteragram.messenger.debug.DebugConfig;
import com.exteragram.messenger.debug.DebugOverlayView;
import com.exteragram.messenger.utils.system.SystemUtils;
import j$.io.DesugarFile;
import j$.io.FileRetargetInterface;
import j$.nio.file.Path;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.AutoDeleteMediaTask;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.camera.Camera2Session;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.camera.CameraInfo;
import org.telegram.messenger.camera.CameraSession;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.InstantCameraViewBase;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.Stories.recorder.DualCameraView;
import org.telegram.ui.Stories.recorder.FlashViews;
import org.telegram.ui.Stories.recorder.SliderView;
import org.telegram.ui.Stories.recorder.StoryEntry;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class InstantCameraView extends InstantCameraViewBase implements NotificationCenter.NotificationCenterDelegate {
    private static final int[] ALLOW_BIG_CAMERA_WHITELIST = null;
    private float animationTranslationY;
    private AnimatorSet animatorSet;
    private org.telegram.messenger.camera.Size aspectRatio;
    private volatile boolean bothCameras;
    private final LinearLayout buttonsLayout;
    private final int buttonsSizePx;
    private CameraXSession.CameraLifecycle camLifecycle;
    private Camera2Session camera2SessionCurrent;
    private Camera2Session[] camera2Sessions;
    private InstantViewCameraContainer cameraContainer;
    private File cameraFile;
    private volatile boolean cameraReady;
    private CameraSession cameraSession;
    private final int[] cameraTexture;
    private float cameraTextureAlpha;
    private volatile boolean cameraTextureAvailable;
    private CameraGLThread cameraThread;
    private volatile CameraXSession cameraXSession;
    private float cameraZoom;
    private boolean cancelled;
    private int currentAccount;
    private Delegate delegate;
    private final RoundVideoEncoder.Callback encoderCallback;
    private File encoderFile;
    private boolean encoderFinishRequested;
    private int encoderFrameRate;
    private int encoderSend;
    private SendOptions encoderSendOptions;
    private TLRPC.InputEncryptedFile encryptedFile;
    private TLRPC.InputFile file;
    private ValueAnimator finishZoomTransition;
    private Bitmap firstFrameThumb;
    private final FlashViews.ImageViewInvertable flashButton;
    private RLottieDrawable flashOffDrawable;
    private RLottieDrawable flashOnDrawable;
    private final FlashViews flashViews;
    private boolean flashing;
    private boolean flipAnimationInProgress;
    private boolean frontFlashing;
    private DispatchQueue generateKeyframeThumbsQueue;
    private float initialCameraZoom;
    private int internalPaddingBottom;
    private boolean isFrontface;
    boolean isInPinchToZoomTouchMode;
    private boolean isMessageTransition;
    private boolean isSecretChat;
    private ItemOptions itemOptions;
    private byte[] iv;
    private byte[] key;
    private final ArrayList<Bitmap> keyframeThumbs;
    private Bitmap lastBitmap;
    private final float[] mMVPMatrix;
    private final float[] mSTMatrix;
    boolean maybePinchToZoomTouchMode;
    private final float[] moldSTMatrix;
    private AnimatorSet muteAnimation;
    private ImageView muteImageView;
    private boolean needDrawFlickerStub;
    private final int[] oldCameraTexture;
    private org.telegram.messenger.camera.Size oldTexturePreviewSize;
    private FloatBuffer oldTextureTextureBuffer;
    public boolean opened;
    private Paint paint;
    private float panTranslationY;
    private View parentView;
    private org.telegram.messenger.camera.Size pictureSize;
    float pinchStartDistance;
    private int pointerId1;
    private int pointerId2;
    private final int[] position;
    private File previewFile;
    private org.telegram.messenger.camera.Size[] previewSize;
    private float progress;
    private Timer progressTimer;
    private long recordPlusTime;
    private long recordStartTime;
    private long recordedTime;
    private boolean recording;
    private int recordingGuid;
    private RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ScaleGestureDetector scaleGestureDetector;
    private float scaleX;
    private float scaleY;
    private CameraInfo selectedCamera;
    private boolean sentMedia;
    private boolean setVisibilityFromPause;
    private long size;
    private volatile int surfaceIndex;
    private final FlashViews.ImageViewInvertable switchCameraButton;
    private RLottieDrawable switchCameraDrawable;
    private FloatBuffer textureBuffer;
    private final float[] textureCoordsData;
    private BackupImageView textureOverlayView;
    private TextureView textureView;
    private int textureViewSize;
    private boolean updateTextureViewSize;
    private final boolean useCamera2;
    private FloatBuffer vertexBuffer;
    private boolean videoConvertFirstWrite;
    private VideoEditedInfo videoEditedInfo;
    private RoundVideoEncoder videoEncoder;
    private VideoPlayer videoPlayer;
    private Boolean wasFlashing;
    private ValueAnimator zoomAnimator;
    private InstantCameraZoomSlider zoomSlider;
    private boolean zoomWas;

    /* renamed from: org.telegram.ui.Components.InstantCameraView$1, reason: invalid class name */
    public class AnonymousClass1 extends Paint {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass1(InstantCameraView r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.graphics.Paint
        public void setAlpha(int r1) {
            super.setAlpha(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass10(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(InstantCameraView.m10927$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            this.this$0.hideCamera(true);
            InstantCameraView.m11000$$Nest$fputsetVisibilityFromPause(this.this$0, false);
            this.this$0.setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$11, reason: invalid class name */
    public class AnonymousClass11 extends TimerTask {
        final /* synthetic */ InstantCameraView this$0;

        public static /* synthetic */ void $r8$lambda$FKPNpQ2ZsfREAhTzCQW2TF6Pyj8(AnonymousClass11 r0) {
            r0.lambda$run$0();
        }

        public AnonymousClass11(InstantCameraView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
        L15:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (InstantCameraView.m10973$$Nest$fgetvideoPlayer(this.this$0) != null) goto L5;
            return;
        L5:
            if (InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0) == null) goto L21;
            long r2 = 0;
            if (InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0).endTime > 0) goto L9;
            return;
        L9:
            if (InstantCameraView.m10973$$Nest$fgetvideoPlayer(this.this$0).getCurrentPosition() < InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0).endTime) goto L23;
            VideoPlayer r0 = InstantCameraView.m10973$$Nest$fgetvideoPlayer(this.this$0);     // Catch: Exception -> L15
            if (InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0).startTime <= 0) goto L13;
            r2 = InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0).startTime;     // Catch: Exception -> L15
        L13:
            r0.seekTo(r2);     // Catch: Exception -> L15
            return;
        L23:
            return;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            AndroidUtilities.runOnUIThread(new InstantCameraView$11$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$12, reason: invalid class name */
    public class AnonymousClass12 implements RoundVideoEncoder.Callback {
        final /* synthetic */ InstantCameraView this$0;

        /* renamed from: $r8$lambda$7505tIqow5XLceCA-RQ-f1tGE6w, reason: not valid java name */
        public static /* synthetic */ void m11017$r8$lambda$7505tIqow5XLceCARQf1tGE6w(AnonymousClass12 r0, double r1) {
            r0.lambda$onAudioAmplitude$0(r1);
        }

        public AnonymousClass12(InstantCameraView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAudioAmplitude$0(double r3) {
            NotificationCenter.getInstance(InstantCameraView.m10940$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.recordProgressChanged, new Object[]{Integer.valueOf(InstantCameraView.m10960$$Nest$fgetrecordingGuid(this.this$0)), Double.valueOf(r3)});
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Callback
        public void onAudioAmplitude(double r2) {
            AndroidUtilities.runOnUIThread(new InstantCameraView$12$$ExternalSyntheticLambda0(this, r2));
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Callback
        public void onFinished(RoundVideoEncoder.FinishReason r1) {
            InstantCameraView.m11010$$Nest$mhandleEncoderFinished(this.this$0, r1);
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Callback
        public void onPaused(File r1) {
            InstantCameraView.m11011$$Nest$mhandleEncoderPaused(this.this$0, r1);
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Callback
        public void onRecordingStarted(boolean r4) {
            if (InstantCameraView.m10939$$Nest$fgetcancelled(this.this$0) == false) goto L13;
            return;
        L13:
            this.this$0.performHapticFeedback(3, 2);     // Catch: Exception -> L12
        L6:
            AndroidUtilities.lockOrientation(InstantCameraView.m10941$$Nest$fgetdelegate(this.this$0).getParentActivity());
            InstantCameraView r0 = this.this$0;
            if (r4 == false) goto L9;
            long r1 = InstantCameraView.m10959$$Nest$fgetrecordedTime(r0);
        L10:
            InstantCameraView.m10994$$Nest$fputrecordPlusTime(r0, r1);
            InstantCameraView.m10995$$Nest$fputrecordStartTime(this.this$0, System.currentTimeMillis());
            InstantCameraView.m10996$$Nest$fputrecording(this.this$0, true);
            InstantCameraView.m11016$$Nest$mupdateFlash(this.this$0);
            this.this$0.invalidate();
            NotificationCenter.getInstance(InstantCameraView.m10940$$Nest$fgetcurrentAccount(this.this$0)).postNotificationName(NotificationCenter.recordStarted, new Object[]{Integer.valueOf(InstantCameraView.m10960$$Nest$fgetrecordingGuid(this.this$0)), Boolean.FALSE});
            return;
        L9:
            r1 = 0;
            goto L10
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Callback
        public void onWriteData(long r3) {
            File r0 = InstantCameraView.m10943$$Nest$fgetencoderFile(this.this$0);
            if (r0 == null) goto L6;
            InstantCameraView.m11009$$Nest$mdidWriteData(this.this$0, r0, r3, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$13, reason: invalid class name */
    public class AnonymousClass13 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass13(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
            FileLog.e(r2);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r5, int r6) {
            if (InstantCameraView.m10973$$Nest$fgetvideoPlayer(this.this$0) != null) goto L6;
            return;
        L6:
            if (InstantCameraView.m10973$$Nest$fgetvideoPlayer(this.this$0).isPlaying() == true) goto L8;
            return;
        L8:
            if (r6 == 4) goto L10;
            return;
        L10:
            if (InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0) == null) goto L18;
            VideoPlayer r52 = InstantCameraView.m10973$$Nest$fgetvideoPlayer(this.this$0);
            long r2 = 0;
            if (InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0).startTime <= 0) goto L14;
            r2 = InstantCameraView.m10971$$Nest$fgetvideoEditedInfo(this.this$0).startTime;
        L14:
            r52.seekTo(r2);
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass14(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(InstantCameraView.m10953$$Nest$fgetmuteAnimation(this.this$0)) == false) goto L6;
            InstantCameraView.m10991$$Nest$fputmuteAnimation(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass15(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (InstantCameraView.m10945$$Nest$fgetfinishZoomTransition(this.this$0) == null) goto L6;
            InstantCameraView.m10987$$Nest$fputfinishZoomTransition(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$16, reason: invalid class name */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass16(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (InstantCameraView.m10945$$Nest$fgetfinishZoomTransition(this.this$0) == null) goto L6;
            InstantCameraView.m10987$$Nest$fputfinishZoomTransition(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$2, reason: invalid class name */
    public class AnonymousClass2 extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass2(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector r6) {
            if (InstantCameraView.m10938$$Nest$fgetcameraXSession(this.this$0) == null) goto L7;
            InstantCameraView.m10974$$Nest$fgetzoomSlider(this.this$0).scaleCameraXZoom((float) Math.pow(r6.getScaleFactor(), 2.0d));
            InstantCameraView r5 = this.this$0;
            InstantCameraView.m10981$$Nest$fputcameraZoom(r5, InstantCameraView.m10938$$Nest$fgetcameraXSession(r5).getLinearZoom());
            return true;
        L7:
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector r1) {
            InstantCameraView.m11006$$Nest$mcancelZoomAnimations(this.this$0);
            InstantCameraView.m10974$$Nest$fgetzoomSlider(this.this$0).beginPinchZoomGesture();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector r2) {
            this.this$0.finishZoom();
            super.onScaleEnd(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$3, reason: invalid class name */
    public class AnonymousClass3 extends InstantViewCameraContainer {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass3(InstantCameraView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            this.this$0.invalidate();
        }

        @Override // android.view.View
        public void setRotationY(float r1) {
            super.setRotationY(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewOutlineProvider {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass4(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r2, Outline r3) {
            r3.setOval(0, 0, InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0), InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$5, reason: invalid class name */
    public class AnonymousClass5 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ InstantCameraView this$0;
        final /* synthetic */ boolean[] val$didSwap;
        final /* synthetic */ Runnable val$doSwap;

        public AnonymousClass5(InstantCameraView r1, boolean[] r2, Runnable r3) {
            this.this$0 = r1;
            this.val$didSwap = r2;
            this.val$doSwap = r3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            if (r52 <= 0.5f) goto L8;
            boolean[] r1 = this.val$didSwap;
            if (r1[0] == true) goto L8;
            r1[0] = true;
            this.val$doSwap.run();
        L8:
            if (r52 < 0.5f) goto L11;
            r52 = r52 - 1.0f;
        L11:
            float r53 = r52 * 180.0f;
            InstantCameraView.m10930$$Nest$fgetcameraContainer(this.this$0).setRotationY(r53);
            InstantCameraView.m10966$$Nest$fgettextureOverlayView(this.this$0).setRotationY(r53);
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ InstantCameraView this$0;
        final /* synthetic */ boolean[] val$didSwap;
        final /* synthetic */ Runnable val$doSwap;

        public AnonymousClass6(InstantCameraView r1, boolean[] r2, Runnable r3) {
            this.this$0 = r1;
            this.val$didSwap = r2;
            this.val$doSwap = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            boolean[] r32 = this.val$didSwap;
            if (r32[0] == true) goto L5;
            r32[0] = true;
            this.val$doSwap.run();
        L5:
            InstantCameraView.m10930$$Nest$fgetcameraContainer(this.this$0).setRotationY(0.0f);
            InstantCameraView.m10966$$Nest$fgettextureOverlayView(this.this$0).setRotationY(0.0f);
            InstantCameraView.m10989$$Nest$fputflipAnimationInProgress(this.this$0, false);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$7, reason: invalid class name */
    public class AnonymousClass7 extends BackupImageView {
        CellFlickerDrawable flickerDrawable;
        final /* synthetic */ InstantCameraView this$0;
        final /* synthetic */ Paint val$blackoutPaint;

        public AnonymousClass7(InstantCameraView r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$blackoutPaint = r3;
            super(r2);
            this.flickerDrawable = new CellFlickerDrawable();
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            if (InstantCameraView.m10954$$Nest$fgetneedDrawFlickerStub(this.this$0) == false) goto L6;
            this.flickerDrawable.setParentWidth(InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0));
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0), InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0));
            float r1 = r0.width() / 2.0f;
            r5.drawRoundRect(r0, r1, r1, this.val$blackoutPaint);
            r0.inset(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            this.flickerDrawable.draw(r5, r0, r1, null);
            invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$8, reason: invalid class name */
    public class AnonymousClass8 extends File implements FileRetargetInterface {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass8(InstantCameraView r1, File r2, String r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // java.io.File
        public boolean delete() {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.e("delete camera file");
        L6:
            return super.delete();
        }

        @Override // java.io.File, j$.io.FileRetargetInterface
        public /* synthetic */ Path toPath() {
            return DesugarFile.toPath(this);
        }
    }

    /* renamed from: org.telegram.ui.Components.InstantCameraView$9, reason: invalid class name */
    public class AnonymousClass9 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ InstantCameraView this$0;

        public AnonymousClass9(InstantCameraView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r3, int r4, int r5) {
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.d("InstantCamera camera surface available");
        L6:
            if (InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0) != null) goto L16;
            if (r3 != null) goto L9;
            return;
        L9:
            if (InstantCameraView.m10939$$Nest$fgetcancelled(this.this$0) == false) goto L12;
            return;
        L12:
            if (BuildVars.LOGS_ENABLED == false) goto L14;
            FileLog.d("InstantCamera start create thread");
        L14:
            InstantCameraView.m10980$$Nest$fputcameraThread(this.this$0, new CameraGLThread(this.this$0, r3, r4, r5));
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r10) {
            InstantCameraView.m11006$$Nest$mcancelZoomAnimations(this.this$0);
            InstantCameraView.m10974$$Nest$fgetzoomSlider(this.this$0).unbindSession();
            if (InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0) == null) goto L5;
            InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0).shutdown(0, true, 0, 0, 0, 0);
            InstantCameraView.m10980$$Nest$fputcameraThread(this.this$0, null);
        L5:
            CameraType r102 = ExteraConfig.getCameraType();
            CameraType r1 = CameraType.CAMERA_X;
            InstantCameraView r2 = this.this$0;
            if (r102 != r1) goto L8;
            InstantCameraView.m11013$$Nest$mreleaseCameraXSession(r2);
            return true;
        L8:
            if (InstantCameraView.m10969$$Nest$fgetuseCamera2(r2) == false) goto L18;
            int r12 = 0;
        L10:
            int r22 = InstantCameraView.m10929$$Nest$fgetcamera2Sessions(this.this$0).length;
            InstantCameraView r3 = this.this$0;
            if (r12 >= r22) goto L16;
            if (InstantCameraView.m10929$$Nest$fgetcamera2Sessions(r3)[r12] == null) goto L15;
            InstantCameraView.m10929$$Nest$fgetcamera2Sessions(this.this$0)[r12].destroy(false);
            InstantCameraView.m10929$$Nest$fgetcamera2Sessions(this.this$0)[r12] = null;
        L15:
            r12 = r12 + 1;
            goto L10
        L16:
            InstantCameraView.m10975$$Nest$fputcamera2SessionCurrent(r3, null);
            return true;
        L18:
            if (InstantCameraView.m10933$$Nest$fgetcameraSession(this.this$0) == null) goto L27;
            CameraController.getInstance().close(InstantCameraView.m10933$$Nest$fgetcameraSession(this.this$0), null, null);
            InstantCameraView.m10977$$Nest$fputcameraSession(this.this$0, null);
            return true;
        L27:
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
            if (InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0) == null) goto L6;
            CameraGLThread.m11021$$Nest$fputsurfaceWidth(InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0), r2);
            CameraGLThread.m11020$$Nest$fputsurfaceHeight(InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0), r3);
            CameraGLThread.m11022$$Nest$mupdateScale(InstantCameraView.m10937$$Nest$fgetcameraThread(this.this$0));
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    public static class AudioBufferInfo {
        public ByteBuffer[] buffer;
        public boolean last;
        public int lastWroteBuffer;
        public long[] offset;
        public int[] read;
        public int results;

        public AudioBufferInfo() {
            this.buffer = new ByteBuffer[10];
            this.offset = new long[10];
            this.read = new int[10];
            int r1 = 0;
        L3:
            if (r1 >= 10) goto L5;
            this.buffer[r1] = ByteBuffer.allocateDirect(2048);
            this.buffer[r1].order(ByteOrder.nativeOrder());
            r1 = r1 + 1;
            goto L3
        }
    }

    public class CameraGLThread extends DispatchQueue {
        private final int DO_FLIP;
        private final int DO_REINIT_MESSAGE;
        private final int DO_RENDER_MESSAGE;
        private final int DO_SETORIENTATION_MESSAGE;
        private final int DO_SETSESSION_MESSAGE;
        private final int DO_SET_CAMERAX_PREVIEW_SIZE;
        private final int DO_SHUTDOWN_MESSAGE;
        private Integer cameraId;
        private final SurfaceTexture[] cameraSurface;
        private Object currentSession;
        private int drawProgram;
        private EGL10 egl10;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private final RoundVideoEncoder.FrameSnapshot frameSnapshotScratch;
        private boolean initied;
        private final AtomicInteger pendingRenderMask;
        private int positionHandle;
        private boolean recording;
        private volatile boolean running;
        private final int[] surfaceGeneration;
        private int surfaceHeight;
        private SurfaceTexture surfaceTexture;
        private int surfaceWidth;
        private int textureHandle;
        private int textureMatrixHandle;
        final /* synthetic */ InstantCameraView this$0;
        private int vertexMatrixHandle;

        public static /* synthetic */ void $r8$lambda$OL6bO9l4r8rOA1yoPr6YTrAWsmw() {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        }

        /* renamed from: $r8$lambda$QCTKTxafomWp2vNd-L6ilZLtOJY, reason: not valid java name */
        public static /* synthetic */ void m11018$r8$lambda$QCTKTxafomWp2vNdL6ilZLtOJY(CameraGLThread r0) {
            r0.lambda$onDraw$2();
        }

        public static /* synthetic */ void $r8$lambda$bfRzXZb72dJTpFcy4EYMnqi0NkI(CameraGLThread r0, int r1, int r2, SurfaceTexture r3) {
            r0.lambda$attachFrameListener$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$jw-ZzpiM_GVEUFCEGQOpW_2E2ew, reason: not valid java name */
        public static /* synthetic */ void m11019$r8$lambda$jwZzpiM_GVEUFCEGQOpW_2E2ew(CameraGLThread r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$skZWTlYnGXhUnVYhz3jQ9OaUs6c(CameraGLThread r0) {
            r0.lambda$onDraw$4();
        }

        /* renamed from: -$$Nest$fputsurfaceHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11020$$Nest$fputsurfaceHeight(CameraGLThread r0, int r1) {
            r0.surfaceHeight = r1;
        }

        /* renamed from: -$$Nest$fputsurfaceWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11021$$Nest$fputsurfaceWidth(CameraGLThread r0, int r1) {
            r0.surfaceWidth = r1;
        }

        /* renamed from: -$$Nest$mupdateScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11022$$Nest$mupdateScale(CameraGLThread r0) {
            r0.updateScale();
        }

        public CameraGLThread(InstantCameraView r4, SurfaceTexture r5, int r6, int r7) {
            this.this$0 = r4;
            super("CameraGLThread", false);
            this.cameraSurface = new SurfaceTexture[2];
            this.DO_RENDER_MESSAGE = 0;
            this.DO_SHUTDOWN_MESSAGE = 1;
            this.DO_REINIT_MESSAGE = 2;
            this.DO_SETSESSION_MESSAGE = 3;
            this.DO_FLIP = 4;
            this.DO_SETORIENTATION_MESSAGE = 5;
            this.DO_SET_CAMERAX_PREVIEW_SIZE = 6;
            this.cameraId = 0;
            this.running = true;
            this.pendingRenderMask = new AtomicInteger(0);
            this.surfaceGeneration = new int[2];
            this.frameSnapshotScratch = new RoundVideoEncoder.FrameSnapshot();
            this.surfaceTexture = r5;
            this.surfaceWidth = r6;
            this.surfaceHeight = r7;
            start();
            postRunnable(new InstantCameraView$CameraGLThread$$ExternalSyntheticLambda0(this));
        }

        private void attachFrameListener(int r4) {
            this.cameraSurface[r4].setOnFrameAvailableListener(new InstantCameraView$CameraGLThread$$ExternalSyntheticLambda1(this, r4, this.surfaceGeneration[r4]), getHandler());
        }

        private void fillFrameSnapshot(RoundVideoEncoder.FrameSnapshot r4, int r5, int r6) {
            SurfaceTexture r0 = this.cameraSurface[r5];
            r4.sourceTimestampNs = r0.getTimestamp();
            r4.arrivalTimeNs = System.nanoTime();
            r4.cameraId = r6;
            r4.surfaceIndex = r5;
            r4.textureId = InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[r5];
            r0.getTransformMatrix(r4.stMatrix);
            int r2 = 0;
            System.arraycopy(InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0, r4.mvpMatrix, 0, 16);
            System.arraycopy(InstantCameraView.m10965$$Nest$fgettextureCoordsData(this.this$0), 0, r4.textureCoords, 0, 8);
            org.telegram.messenger.camera.Size r3 = InstantCameraView.m10958$$Nest$fgetpreviewSize(this.this$0)[r5];
            if (r3 == null) goto L5;
            int r52 = r3.getWidth();
        L6:
            r4.previewWidth = r52;
            if (r3 == null) goto L9;
            r2 = r3.getHeight();
        L9:
            r4.previewHeight = r2;
            return;
        L5:
            r52 = 0;
            goto L6
        }

        private boolean initGL() {
            if (BuildVars.LOGS_ENABLED == false) goto L5;
            FileLog.d("InstantCamera start init gl");
        L5:
            EGL10 r0 = (EGL10) EGLContext.getEGL();
            this.egl10 = r0;
            EGLDisplay r02 = r0.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.eglDisplay = r02;
            int r2 = 0;
            if (r02 != EGL10.EGL_NO_DISPLAY) goto L13;
            if (BuildVars.LOGS_ENABLED == false) goto L10;
            FileLog.e("InstantCamera eglGetDisplay failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L10:
            finish();
            return false;
        L13:
            if (this.egl10.eglInitialize(r02, new int[2]) == false) goto L15;
            int[] r8 = new int[1];
            EGLConfig[] r6 = new EGLConfig[1];
            if (this.egl10.eglChooseConfig(this.eglDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 0, 12325, 0, 12326, 0, 12344}, r6, 1, r8) == true) goto L27;
            if (BuildVars.LOGS_ENABLED == false) goto L24;
            FileLog.e("InstantCamera eglChooseConfig failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L24:
            finish();
            return false;
        L27:
            if (r8[0] <= 0) goto L85;
            EGLConfig r3 = r6[0];
            EGLContext r4 = this.egl10.eglCreateContext(this.eglDisplay, r3, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            this.eglContext = r4;
            if (r4 != null) goto L36;
            if (BuildVars.LOGS_ENABLED == false) goto L33;
            FileLog.e("InstantCamera eglCreateContext failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L33:
            finish();
            return false;
        L36:
            if (this.surfaceTexture == null) goto L82;
            EGLSurface r32 = this.egl10.eglCreateWindowSurface(this.eglDisplay, r3, this.surfaceTexture, null);
            this.eglSurface = r32;
            if (r32 == null) goto L78;
            if (r32 == EGL10.EGL_NO_SURFACE) goto L78;
            if (this.egl10.eglMakeCurrent(this.eglDisplay, r32, r32, this.eglContext) == false) goto L45;
            InstantCameraView.m11003$$Nest$fputvertexBuffer(this.this$0, ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer());
            InstantCameraView.m10970$$Nest$fgetvertexBuffer(this.this$0).put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f}).position(0);
            updateTextureBuffer();
            Matrix.setIdentityM(InstantCameraView.m10951$$Nest$fgetmSTMatrix(this.this$0), 0);
            int r33 = InstantCameraView.m11012$$Nest$mloadShader(this.this$0, 35633, "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n   gl_Position = uMVPMatrix * aPosition;\n   vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n");
            int r42 = InstantCameraView.m11012$$Nest$mloadShader(this.this$0, 35632, "#extension GL_OES_EGL_image_external : require\nprecision lowp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
            if (r33 == 0) goto L73;
            if (r42 == 0) goto L73;
            int r5 = GLES20.glCreateProgram();
            this.drawProgram = r5;
            GLES20.glAttachShader(r5, r33);
            GLES20.glAttachShader(this.drawProgram, r42);
            GLES20.glLinkProgram(this.drawProgram);
            int[] r34 = new int[1];
            GLES20.glGetProgramiv(this.drawProgram, 35714, r34, 0);
            if (r34[0] == 0) goto L55;
            this.positionHandle = GLES20.glGetAttribLocation(this.drawProgram, "aPosition");
            this.textureHandle = GLES20.glGetAttribLocation(this.drawProgram, "aTextureCoord");
            this.vertexMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uMVPMatrix");
            this.textureMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uSTMatrix");
        L59:
            Matrix.setIdentityM(InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0);
            GLES20.glGenTextures(2, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0), 0);
        L60:
            if (r2 >= 2) goto L69;
            GLES20.glBindTexture(36197, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[r2]);
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, 10240, 9729);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            this.cameraSurface[r2] = new SurfaceTexture(InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[r2]);
            attachFrameListener(r2);
            if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L66;
            if (r2 == 0) goto L66;
            if (InstantCameraView.m10928$$Nest$fgetbothCameras(this.this$0) == true) goto L66;
        L67:
            r2 = r2 + 1;
        L66:
            InstantCameraView.m11007$$Nest$mcreateCamera(this.this$0, r2, this.cameraSurface[r2]);
            goto L67
        L69:
            if (BuildVars.LOGS_ENABLED == false) goto L71;
            FileLog.e("InstantCamera gl initied");
        L71:
            return true;
        L55:
            if (BuildVars.LOGS_ENABLED == false) goto L57;
            FileLog.e("InstantCamera failed link shader");
        L57:
            GLES20.glDeleteProgram(this.drawProgram);
            this.drawProgram = 0;
        L73:
            if (BuildVars.LOGS_ENABLED == false) goto L75;
            FileLog.e("InstantCamera failed creating shader");
        L75:
            finish();
            return false;
        L45:
            if (BuildVars.LOGS_ENABLED == false) goto L47;
            FileLog.e("InstantCamera eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L47:
            finish();
            return false;
        L78:
            if (BuildVars.LOGS_ENABLED == false) goto L80;
            FileLog.e("InstantCamera createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L80:
            finish();
            return false;
        L82:
            finish();
            return false;
        L85:
            if (BuildVars.LOGS_ENABLED == false) goto L87;
            FileLog.e("InstantCamera eglConfig not initialized");
        L87:
            finish();
            return false;
        L15:
            if (BuildVars.LOGS_ENABLED == false) goto L17;
            FileLog.e("InstantCamera eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
        L17:
            finish();
            return false;
        }

        private /* synthetic */ void lambda$attachFrameListener$1(int r2, int r3, SurfaceTexture r4) {
            if (this.running == true) goto L5;
            return;
        L5:
            if (this.cameraSurface[r2] == r4) goto L7;
            return;
        L7:
            if (this.surfaceGeneration[r2] != r3) goto L20;
            boolean r42 = true;
            InstantCameraView.m10979$$Nest$fputcameraTextureAvailable(this.this$0, true);
            if (r2 != 0) goto L12;
            boolean r0 = true;
        L13:
            if (r2 == 1) goto L16;
            r42 = false;
        L16:
            requestRender(r0, r42);
            return;
        L12:
            r0 = false;
            goto L13
        }

        private /* synthetic */ void lambda$new$0() {
            this.initied = initGL();
        }

        private /* synthetic */ void lambda$onDraw$2() {
            InstantCameraView.m10966$$Nest$fgettextureOverlayView(this.this$0).animate().setDuration(120).alpha(0.0f).setInterpolator(new DecelerateInterpolator()).start();
        }

        private /* synthetic */ void lambda$onDraw$4() {
            if (InstantCameraView.m10967$$Nest$fgettextureView(this.this$0) != null) goto L6;
            return;
        L6:
            if (InstantCameraView.m10946$$Nest$fgetfirstFrameThumb(this.this$0) == null) goto L8;
            InstantCameraView.m10946$$Nest$fgetfirstFrameThumb(this.this$0).recycle();
            InstantCameraView.m10988$$Nest$fputfirstFrameThumb(this.this$0, null);
        L8:
            InstantCameraView r2 = this.this$0;
            InstantCameraView.m10988$$Nest$fputfirstFrameThumb(r2, InstantCameraView.m10967$$Nest$fgettextureView(r2).getBitmap());
        }

        private void onDraw(Integer r10, boolean r11, boolean r12) {
            if (this.initied == false) goto L101;
            Integer r0 = this.cameraId;
            if (r0 == null) goto L11;
            if (r0.equals(r10) == true) goto L11;
            return;
        L11:
            if (this.eglContext.equals(this.egl10.eglGetCurrentContext()) == true) goto L13;
        L14:
            EGL10 r02 = this.egl10;
            EGLDisplay r1 = this.eglDisplay;
            EGLSurface r2 = this.eglSurface;
            if (r02.eglMakeCurrent(r1, r2, r2, this.eglContext) == false) goto L17;
        L21:
            if (r11 == false) goto L28;
            this.cameraSurface[0].updateTexImage();     // Catch: Throwable -> L24
        L24:
            th = move-exception;
            FileLog.e(th);
            return;
        L28:
            if (r12 == false) goto L35;
            this.cameraSurface[1].updateTexImage();     // Catch: Throwable -> L31
        L31:
            th = move-exception;
            FileLog.e(th);
            return;
        L35:
            if (InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0) != 0) goto L37;
            boolean r22 = r11;
        L38:
            CameraType r3 = ExteraConfig.getCameraType();
            CameraType r4 = CameraType.CAMERA_X;
            if (r3 != r4) goto L45;
            if (InstantCameraView.m10928$$Nest$fgetbothCameras(this.this$0) == false) goto L45;
            if (r22 == true) goto L45;
            return;
        L45:
            if (this.recording == false) goto L47;
            boolean r23 = false;
        L79:
            if (InstantCameraView.m10972$$Nest$fgetvideoEncoder(this.this$0) != null) goto L81;
        L91:
            this.cameraSurface[InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0)].getTransformMatrix(InstantCameraView.m10951$$Nest$fgetmSTMatrix(this.this$0));
            GLES20.glUseProgram(this.drawProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0)]);
            GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, InstantCameraView.m10970$$Nest$fgetvertexBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.positionHandle);
            GLES20.glVertexAttribPointer(this.textureHandle, 2, 5126, false, 8, InstantCameraView.m10964$$Nest$fgettextureBuffer(this.this$0));
            GLES20.glEnableVertexAttribArray(this.textureHandle);
            GLES20.glUniformMatrix4fv(this.textureMatrixHandle, 1, false, InstantCameraView.m10951$$Nest$fgetmSTMatrix(this.this$0), 0);
            GLES20.glUniformMatrix4fv(this.vertexMatrixHandle, 1, false, InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.positionHandle);
            GLES20.glDisableVertexAttribArray(this.textureHandle);
            GLES20.glBindTexture(36197, 0);
            GLES20.glUseProgram(0);
            this.egl10.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            if (r23 == false) goto L100;
            AndroidUtilities.runOnUIThread(new InstantCameraView$CameraGLThread$$ExternalSyntheticLambda4(this));
            return;
        L100:
            return;
        L81:
            if (InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0) != 0) goto L84;
            if (r11 == false) goto L84;
        L86:
            RoundVideoEncoder.FrameSnapshot r112 = this.frameSnapshotScratch;
            int r122 = InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0);
            if (InstantCameraView.m10928$$Nest$fgetbothCameras(this.this$0) == false) goto L89;
            int r102 = InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0);
        L90:
            fillFrameSnapshot(r112, r122, r102);
            InstantCameraView.m10972$$Nest$fgetvideoEncoder(this.this$0).frameAvailable(this.frameSnapshotScratch);
            goto L91
        L89:
            r102 = r10.intValue();
        L84:
            if (InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0) != 1) goto L91;
            if (r12 == false) goto L91;
        L47:
            if (InstantCameraView.m10972$$Nest$fgetvideoEncoder(this.this$0) != null) goto L53;
            InstantCameraView.m10999$$Nest$fputsentMedia(this.this$0, false);
            InstantCameraView.m11004$$Nest$fputvideoConvertFirstWrite(this.this$0, true);
            InstantCameraView.m10983$$Nest$fputencoderFinishRequested(this.this$0, false);
            InstantCameraView.m10985$$Nest$fputencoderSend(this.this$0, 0);
            InstantCameraView.m10986$$Nest$fputencoderSendOptions(this.this$0, null);
            InstantCameraView.m10949$$Nest$fgetkeyframeThumbs(this.this$0).clear();
            if (InstantCameraView.m10947$$Nest$fgetgenerateKeyframeThumbsQueue(this.this$0) == null) goto L51;
            InstantCameraView.m10947$$Nest$fgetgenerateKeyframeThumbsQueue(this.this$0).cleanupQueue();
            InstantCameraView.m10947$$Nest$fgetgenerateKeyframeThumbsQueue(this.this$0).recycle();
        L51:
            InstantCameraView.m10990$$Nest$fputgenerateKeyframeThumbsQueue(this.this$0, new DispatchQueue("keyframes_thumb_queue"));
            InstantCameraView r24 = this.this$0;
            InstantCameraView.m11005$$Nest$fputvideoEncoder(r24, new RoundVideoEncoder(new EncoderRenderer(r24, null), InstantCameraView.m10942$$Nest$fgetencoderCallback(this.this$0), InstantCameraView.m10948$$Nest$fgetisSecretChat(this.this$0)));
            InstantCameraView r25 = this.this$0;
            InstantCameraView.m10984$$Nest$fputencoderFrameRate(r25, InstantCameraView.m11015$$Nest$mresolveEncoderFrameRate(r25));
        L53:
            if (InstantCameraView.m10972$$Nest$fgetvideoEncoder(this.this$0).isStarted() == true) goto L55;
            r23 = true;
        L59:
            AndroidUtilities.runOnUIThread(new InstantCameraView$CameraGLThread$$ExternalSyntheticLambda3());
            InstantCameraView r32 = this.this$0;
            InstantCameraView.m10982$$Nest$fputencoderFile(r32, InstantCameraView.m10931$$Nest$fgetcameraFile(r32));
            InstantCameraView.m10972$$Nest$fgetvideoEncoder(this.this$0).startRecording(InstantCameraView.m10931$$Nest$fgetcameraFile(this.this$0), EGL14.eglGetCurrentContext(), InstantCameraView.m10944$$Nest$fgetencoderFrameRate(this.this$0));
            this.recording = true;
            if (ExteraConfig.getCameraType() == r4) goto L67;
            Object r33 = this.currentSession;
            if ((r33 instanceof CameraSession) == false) goto L65;
            int r34 = ((CameraSession) r33).getCurrentOrientation();
        L72:
            if (r34 != 90) goto L74;
        L75:
            float r35 = InstantCameraView.m10961$$Nest$fgetscaleX(this.this$0);
            InstantCameraView r42 = this.this$0;
            InstantCameraView.m10997$$Nest$fputscaleX(r42, InstantCameraView.m10962$$Nest$fgetscaleY(r42));
            InstantCameraView.m10998$$Nest$fputscaleY(this.this$0, r35);
        L76:
            this.recording = true;
            InstantCameraView.m11016$$Nest$mupdateFlash(this.this$0);
            goto L79
        L74:
            if (r34 != 270) goto L76;
        L65:
            if ((r33 instanceof Camera2Session) == false) goto L70;
            r34 = ((Camera2Session) r33).getCurrentOrientation();
        L70:
            r34 = 0;
            goto L72
        L67:
            CameraXSession r36 = InstantCameraView.m10938$$Nest$fgetcameraXSession(this.this$0);
            if (r36 == null) goto L70;
            r34 = r36.getDisplayOrientation();
            goto L72
        L55:
            if (InstantCameraView.m10932$$Nest$fgetcameraReady(this.this$0) == true) goto L57;
            InstantCameraView.m10976$$Nest$fputcameraReady(this.this$0, true);
            AndroidUtilities.runOnUIThread(new InstantCameraView$CameraGLThread$$ExternalSyntheticLambda2(this));
        L57:
            r23 = false;
            goto L59
        L37:
            r22 = r12;
            goto L38
        L17:
            if (BuildVars.LOGS_ENABLED == false) goto L99;
            FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
            return;
        L99:
            return;
        L13:
            if (this.eglSurface.equals(this.egl10.eglGetCurrentSurface(12377)) == true) goto L21;
        }

        private void updateScale() {
            if (InstantCameraView.m10958$$Nest$fgetpreviewSize(this.this$0)[InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0)] == null) goto L13;
            int r0 = InstantCameraView.m10958$$Nest$fgetpreviewSize(this.this$0)[InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0)].getWidth();
            float r2 = this.surfaceWidth / Math.min(r0, r1);
            int r02 = (int) (r0 * r2);
            int r1 = (int) (InstantCameraView.m10958$$Nest$fgetpreviewSize(this.this$0)[InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0)].getHeight() * r2);
            if (r02 != r1) goto L7;
            InstantCameraView.m10997$$Nest$fputscaleX(this.this$0, 1.0f);
            InstantCameraView.m10998$$Nest$fputscaleY(this.this$0, 1.0f);
        L11:
            FileLog.d("InstantCamera camera scaleX = " + InstantCameraView.m10961$$Nest$fgetscaleX(this.this$0) + " scaleY = " + InstantCameraView.m10962$$Nest$fgetscaleY(this.this$0));
            return;
        L7:
            InstantCameraView r3 = this.this$0;
            if (r02 <= r1) goto L10;
            InstantCameraView.m10997$$Nest$fputscaleX(r3, 1.0f);
            InstantCameraView.m10998$$Nest$fputscaleY(this.this$0, r02 / this.surfaceHeight);
            goto L11
        L10:
            InstantCameraView.m10997$$Nest$fputscaleX(r3, r1 / this.surfaceWidth);
            InstantCameraView.m10998$$Nest$fputscaleY(this.this$0, 1.0f);
            goto L11
        }

        private void updateTextureBuffer() {
            updateScale();
            float r0 = (1.0f / InstantCameraView.m10961$$Nest$fgetscaleX(this.this$0)) / 2.0f;
            float r1 = (1.0f / InstantCameraView.m10962$$Nest$fgetscaleY(this.this$0)) / 2.0f;
            float r3 = 0.5f - r0;
            float r4 = 0.5f - r1;
            float r02 = r0 + 0.5f;
            float r12 = r1 + 0.5f;
            float[] r5 = {r3, r4, r02, r4, r3, r12, r02, r12};
            System.arraycopy(r5, 0, InstantCameraView.m10965$$Nest$fgettextureCoordsData(this.this$0), 0, 8);
            InstantCameraView.m11002$$Nest$fputtextureBuffer(this.this$0, ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer());
            InstantCameraView.m10964$$Nest$fgettextureBuffer(this.this$0).put(r5).position(0);
        }

        public void finish() {
            int[] r0 = this.surfaceGeneration;
            r0[0] = r0[0] + 1;
            r0[1] = r0[1] + 1;
            this.pendingRenderMask.set(0);
            if (this.cameraSurface == null) goto L11;
            int r02 = 0;
        L6:
            if (r02 >= 2) goto L11;
            SurfaceTexture r4 = this.cameraSurface[r02];
            if (r4 == null) goto L10;
            r4.release();
            this.cameraSurface[r02] = null;
        L10:
            r02 = r02 + 1;
        L11:
            InstantCameraView.m10979$$Nest$fputcameraTextureAvailable(this.this$0, false);
            if (this.eglSurface == null) goto L31;
            EGLContext r03 = this.eglContext;
            if (r03 == null) goto L31;
            if (r03.equals(this.egl10.eglGetCurrentContext()) == true) goto L18;
        L19:
            EGL10 r04 = this.egl10;
            EGLDisplay r42 = this.eglDisplay;
            EGLSurface r5 = this.eglSurface;
            r04.eglMakeCurrent(r42, r5, r5, this.eglContext);
        L21:
            if (InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0) == null) goto L26;
            if (InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[0] == Integer.MIN_VALUE) goto L26;
            GLES20.glDeleteTextures(1, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0), 0);
            InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[0] = Integer.MIN_VALUE;
        L26:
            if (InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0) == null) goto L31;
            if (InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[1] == Integer.MIN_VALUE) goto L31;
            GLES20.glDeleteTextures(1, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0), 1);
            InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[1] = Integer.MIN_VALUE;
            goto L31
        L18:
            if (this.eglSurface.equals(this.egl10.eglGetCurrentSurface(12377)) == true) goto L21;
        L31:
            if (this.eglSurface == null) goto L33;
            EGL10 r05 = this.egl10;
            EGLDisplay r1 = this.eglDisplay;
            EGLSurface r3 = EGL10.EGL_NO_SURFACE;
            r05.eglMakeCurrent(r1, r3, r3, EGL10.EGL_NO_CONTEXT);
            this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = null;
        L33:
            EGLContext r06 = this.eglContext;
            if (r06 == null) goto L36;
            this.egl10.eglDestroyContext(this.eglDisplay, r06);
            this.eglContext = null;
        L36:
            EGLDisplay r07 = this.eglDisplay;
            if (r07 == null) goto L43;
            this.egl10.eglTerminate(r07);
            this.eglDisplay = null;
            return;
        }

        public void flipSurfaces() {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(4), 0);
            return;
        }

        @Override // org.telegram.messenger.DispatchQueue
        public void handleMessage(Message r7) {
            SendOptions r1 = null;
            boolean r2 = true;
            switch(r7.what) {
                case 0: goto L78;
                case 1: goto L55;
                case 2: goto L37;
                case 3: goto L20;
                case 4: goto L17;
                case 5: goto L9;
                case 6: goto L5;
                default: goto L100;
            };
        L5:
            int r0 = r7.arg1;
            InstantCameraView.m10958$$Nest$fgetpreviewSize(this.this$0)[r0] = (org.telegram.messenger.camera.Size) r7.obj;
            if (r0 != InstantCameraView.m10963$$Nest$fgetsurfaceIndex(this.this$0)) goto L92;
            updateTextureBuffer();
            return;
        L92:
            return;
        L9:
            CameraXSession r72 = InstantCameraView.m10938$$Nest$fgetcameraXSession(this.this$0);
            if (r72 == null) goto L12;
            int r73 = r72.getDisplayOrientation();
        L13:
            Matrix.setIdentityM(InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0);
            if (r73 == 0) goto L93;
            Matrix.rotateM(InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0, r73, 0.0f, 0.0f, 1.0f);
            return;
        L93:
            return;
        L12:
            r73 = 0;
            goto L13
        L17:
            InstantCameraView r74 = this.this$0;
            InstantCameraView.m11001$$Nest$fputsurfaceIndex(r74, 1 - InstantCameraView.m10963$$Nest$fgetsurfaceIndex(r74));
            updateTextureBuffer();
            requestRender(true, true);
            return;
        L37:
            EGL10 r75 = this.egl10;
            EGLDisplay r02 = this.eglDisplay;
            EGLSurface r4 = this.eglSurface;
            if (r75.eglMakeCurrent(r02, r4, r4, this.eglContext) == false) goto L40;
            int[] r76 = this.surfaceGeneration;
            r76[0] = r76[0] + 1;
            Handler r77 = getHandler();
            if (r77 == null) goto L46;
            r77.removeMessages(0);
        L46:
            int r78 = this.pendingRenderMask.getAndSet(0);
            SurfaceTexture r03 = this.cameraSurface[0];
            if (r03 == null) goto L49;
            r03.getTransformMatrix(InstantCameraView.m10952$$Nest$fgetmoldSTMatrix(this.this$0));
            this.cameraSurface[0].setOnFrameAvailableListener(null);
            this.cameraSurface[0].release();
            InstantCameraView.m10955$$Nest$fgetoldCameraTexture(this.this$0)[0] = InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[0];
            InstantCameraView.m10978$$Nest$fputcameraTextureAlpha(this.this$0, 0.0f);
            InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[0] = 0;
            InstantCameraView r04 = this.this$0;
            InstantCameraView.m10993$$Nest$fputoldTextureTextureBuffer(r04, InstantCameraView.m10964$$Nest$fgettextureBuffer(r04).duplicate());
            InstantCameraView r05 = this.this$0;
            InstantCameraView.m10992$$Nest$fputoldTexturePreviewSize(r05, InstantCameraView.m10958$$Nest$fgetpreviewSize(r05)[0]);
        L49:
            this.cameraId = Integer.valueOf(this.cameraId.intValue() + 1);
            InstantCameraView.m10976$$Nest$fputcameraReady(this.this$0, false);
            GLES20.glGenTextures(1, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0), 0);
            GLES20.glBindTexture(36197, InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[0]);
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, 10240, 9729);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            this.cameraSurface[0] = new SurfaceTexture(InstantCameraView.m10934$$Nest$fgetcameraTexture(this.this$0)[0]);
            attachFrameListener(0);
            InstantCameraView.m11007$$Nest$mcreateCamera(this.this$0, 0, this.cameraSurface[0]);
            updateTextureBuffer();
            if ((r78 & 2) != 0) goto L52;
            return;
        L52:
            if (this.cameraSurface[1] == null) goto L97;
            requestRender(false, true);
            return;
        L97:
            return;
        L40:
            if (BuildVars.LOGS_ENABLED == false) goto L95;
            FileLog.d("InstantCamera eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
            return;
        L95:
            return;
        L55:
            monitor-enter(this);
            finish();     // Catch: Throwable -> L74
            monitor-exit(this);     // Catch: Throwable -> L74
            if (this.recording == false) goto L70;
            Object r06 = r7.obj;
            if ((r06 instanceof SendOptions) == false) goto L65;
            if (((SendOptions) r06).ttl == (-2)) goto L70;
        L65:
            if (InstantCameraView.m10972$$Nest$fgetvideoEncoder(this.this$0) == null) goto L70;
            InstantCameraView r6 = this.this$0;
            int r07 = r7.arg1;
            Object r79 = r7.obj;
            if ((r79 instanceof SendOptions) == false) goto L69;
            r1 = (SendOptions) r79;
        L69:
            InstantCameraView.m11014$$Nest$mrequestStopRecording(r6, r07, r1);
        L70:
            Looper r62 = Looper.myLooper();
            if (r62 == null) goto L98;
            r62.quitSafely();
            return;
        L98:
            return;
        L74:
            th = move-exception;
            throw th;
        L78:
            int r08 = this.pendingRenderMask.getAndSet(0);
            if (r08 == 0) goto L99;
            Integer r710 = Integer.valueOf(r7.arg1);
            if ((r08 & 1) == 0) goto L83;
            boolean r12 = true;
        L85:
            if ((r08 & 2) != 0) goto L88;
            r2 = false;
        L88:
            onDraw(r710, r12, r2);
            return;
        L83:
            r12 = false;
            goto L85
        L99:
            return;
        L100:
            return;
        L20:
            if (BuildVars.LOGS_ENABLED == false) goto L22;
            FileLog.d("InstantCamera set gl renderer session");
        L22:
            Object r711 = r7.obj;
            Object r09 = this.currentSession;
            if (r09 == r711) goto L25;
            this.currentSession = r711;
            return;
        L25:
            if ((r09 instanceof CameraSession) == false) goto L28;
            int r712 = ((CameraSession) r09).getWorldAngle();
        L31:
            Matrix.setIdentityM(InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0);
            if (r712 == 0) goto L94;
            Matrix.rotateM(InstantCameraView.m10950$$Nest$fgetmMVPMatrix(this.this$0), 0, r712, 0.0f, 0.0f, 1.0f);
            return;
        L94:
            return;
        L28:
            if ((r09 instanceof Camera2Session) == false) goto L30;
            r712 = ((Camera2Session) r09).getWorldAngle();
            goto L31
        L30:
            r712 = 0;
            goto L31
        }

        public void reinitForNewCamera() {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(2), 0);
            return;
        }

        public void requestRender(boolean r5, boolean r6) {
            if (r6 == false) goto L5;
            int r62 = 2;
        L6:
            int r52 = (r5 ? 1 : 0) | r62;
            if (r52 == 0) goto L30;
            Handler r63 = getHandler();
            if (r63 == null) goto L26;
            monitor-enter(this);
        L16:
            th = move-exception;
            throw th;
        L13:
            if (this.running == true) goto L18;
            monitor-exit(this);     // Catch: Throwable -> L16
            return;
        L18:
            int r1 = this.pendingRenderMask.get();     // Catch: Throwable -> L16
            if (this.pendingRenderMask.compareAndSet(r1, r1 | r52) == false) goto L18;
            if (r1 != 0) goto L22;
            sendMessage(r63.obtainMessage(0, this.cameraId.intValue(), 0), 0);     // Catch: Throwable -> L16
        L22:
            monitor-exit(this);     // Catch: Throwable -> L16
            return;
        L26:
            return;
        L30:
            return;
        L5:
            r62 = 0;
            goto L6
        }

        public void setCameraXPreviewSize(int r3, int r4, int r5) {
            if (r3 >= 0) goto L4;
            return;
        L4:
            if (r3 >= InstantCameraView.m10958$$Nest$fgetpreviewSize(this.this$0).length) goto L13;
            if (r4 <= 0) goto L14;
            if (r5 <= 0) goto L16;
            Handler r0 = getHandler();
            if (r0 == null) goto L15;
            sendMessage(r0.obtainMessage(6, r3, 0, new org.telegram.messenger.camera.Size(r4, r5)), 0);
            return;
        L15:
            return;
        L16:
            return;
        L14:
            return;
        }

        public void setCurrentSession(Camera2Session r3) {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(3, r3), 0);
            return;
        }

        public void setCurrentSession(CameraSession r3) {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(3, r3), 0);
            return;
        }

        public void setOrientation() {
            Handler r0 = getHandler();
            if (r0 == null) goto L6;
            sendMessage(r0.obtainMessage(5), 0);
            return;
        }

        public void shutdown(int r13, boolean r14, int r15, int r16, int r17, long r18) {
            Handler r0 = getHandler();
            if (r0 == null) goto L19;
            monitor-enter(this);
            this.running = false;     // Catch: Throwable -> L11
            SurfaceTexture[] r2 = this.cameraSurface;     // Catch: Throwable -> L11
            int r3 = r2.length;     // Catch: Throwable -> L11
            int r4 = 0;
        L7:
            if (r4 >= r3) goto L14;
            SurfaceTexture r5 = r2[r4];     // Catch: Throwable -> L11
            if (r5 == null) goto L13;
            r5.setOnFrameAvailableListener(null);     // Catch: Throwable -> L11
        L13:
            r4 = r4 + 1;     // Catch: Throwable -> L11
            goto L7
        L14:
            r0.removeMessages(0);     // Catch: Throwable -> L11
            this.pendingRenderMask.set(0);     // Catch: Throwable -> L11
            sendMessage(r0.obtainMessage(1, r13, 0, new SendOptions(r14, r15, r16, r17, r18, 0)), 0);     // Catch: Throwable -> L11
            monitor-exit(this);     // Catch: Throwable -> L11
            return;
        L11:
            th = move-exception;
            throw th;
        }
    }

    /* loaded from: classes3.dex */
    public interface Delegate {
        int getClassGuid();

        long getDialogId();

        View getFragmentView();

        Activity getParentActivity();

        default boolean isInScheduleMode() {
            return false;
        }

        default boolean isSecretChat() {
            return false;
        }

        void sendMedia(MediaController.PhotoEntry r1, VideoEditedInfo r2, boolean r3, int r4, int r5, boolean r6, long r7);
    }

    public class EncoderRenderer implements RoundVideoEncoder.Renderer {
        private int alphaHandle;
        private boolean blendEnabled;
        private int drawProgram;
        private boolean firstThumbPending;
        private final FloatBuffer frameTextureBuffer;
        private long nextThumbActiveTimeNs;
        private InstantCameraVideoEncoderOverlayHelper overlayHelper;
        private int positionHandle;
        private int previewSizeHandle;
        private int resolutionHandle;
        private int texelSizeHandle;
        private int textureHandle;
        private int textureMatrixHandle;
        final /* synthetic */ InstantCameraView this$0;
        private long thumbActiveTimeNs;
        private final AtomicBoolean thumbTaskInFlight;
        private int vertexMatrixHandle;
        private int videoHeight;
        private int videoWidth;

        /* renamed from: $r8$lambda$lq20-0h_w5orpPzCtQdKfy18rek, reason: not valid java name */
        public static /* synthetic */ void m11023$r8$lambda$lq200h_w5orpPzCtQdKfy18rek(EncoderRenderer r0) {
            r0.lambda$onDrawEncoderFrame$0();
        }

        public static /* synthetic */ void $r8$lambda$qipNL0OzQQ9owIfpSA5U7jGvwQQ(EncoderRenderer r0) {
            r0.lambda$onDrawEncoderFrame$1();
        }

        private EncoderRenderer(InstantCameraView r2) {
            this.this$0 = r2;
            this.frameTextureBuffer = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
            this.firstThumbPending = true;
            this.thumbTaskInFlight = new AtomicBoolean();
        }

        public /* synthetic */ EncoderRenderer(InstantCameraView r1, InstantCameraViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onDrawEncoderFrame$0() {
            InstantCameraView.m10966$$Nest$fgettextureOverlayView(this.this$0).animate().setDuration(120).alpha(0.0f).setInterpolator(new DecelerateInterpolator()).start();
        }

        private /* synthetic */ void lambda$onDrawEncoderFrame$1() {
            InstantCameraView.m10966$$Nest$fgettextureOverlayView(this.this$0).animate().setDuration(120).alpha(0.0f).setInterpolator(new DecelerateInterpolator()).start();
        }

        private void maybeScheduleKeyframeThumb() {
            if (InstantCameraView.m10947$$Nest$fgetgenerateKeyframeThumbsQueue(this.this$0) != null) goto L5;
            return;
        L5:
            if (SharedConfig.getDevicePerformanceClass() == 2) goto L8;
            return;
        L8:
            if (this.firstThumbPending == true) goto L13;
            if (this.thumbActiveTimeNs >= this.nextThumbActiveTimeNs) goto L13;
            return;
        L13:
            if (this.thumbTaskInFlight.compareAndSet(false, true) == false) goto L20;
            this.firstThumbPending = false;
            this.nextThumbActiveTimeNs = this.thumbActiveTimeNs + 1100000000;
            InstantCameraView.m10947$$Nest$fgetgenerateKeyframeThumbsQueue(this.this$0).postRunnable(new GenerateKeyframeThumbTask(this.this$0, this.thumbTaskInFlight));
            return;
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Renderer
        public boolean onDrawEncoderFrame(long r19, RoundVideoEncoder.FrameSnapshot r21) {
            if (InstantCameraView.m10936$$Nest$fgetcameraTextureAvailable(this.this$0) == true) goto L5;
        L54:
            return false;
        L5:
            if (this.drawProgram == 0) goto L54;
            FloatBuffer r11 = InstantCameraView.m10970$$Nest$fgetvertexBuffer(this.this$0);
            FloatBuffer r17 = InstantCameraView.m10957$$Nest$fgetoldTextureTextureBuffer(this.this$0);
            if (r11 != null) goto L11;
            FileLog.d("InstantCamera encoder skip frame, no vertex buffer");
            return false;
        L11:
            this.frameTextureBuffer.clear();
            this.frameTextureBuffer.put(r21.textureCoords);
            this.frameTextureBuffer.position(0);
            InstantCameraVideoEncoderOverlayHelper r4 = this.overlayHelper;
            if (r4 == null) goto L14;
            r4.bind();
        L14:
            GLES20.glUseProgram(this.drawProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, r11);
            GLES20.glEnableVertexAttribArray(this.positionHandle);
            GLES20.glVertexAttribPointer(this.textureHandle, 2, 5126, false, 8, this.frameTextureBuffer);
            GLES20.glEnableVertexAttribArray(this.textureHandle);
            GLES20.glUniformMatrix4fv(this.vertexMatrixHandle, 1, false, r21.mvpMatrix, 0);
            GLES20.glUniform2f(this.resolutionHandle, this.videoWidth, this.videoHeight);
            if (InstantCameraView.m10955$$Nest$fgetoldCameraTexture(this.this$0)[0] == 0) goto L26;
            if (r17 == null) goto L26;
            if (InstantCameraView.m10928$$Nest$fgetbothCameras(this.this$0) == true) goto L26;
            if (this.blendEnabled == true) goto L23;
            GLES20.glEnable(3042);
            this.blendEnabled = true;
        L23:
            if (InstantCameraView.m10956$$Nest$fgetoldTexturePreviewSize(this.this$0) == null) goto L25;
            GLES20.glUniform2f(this.previewSizeHandle, InstantCameraView.m10956$$Nest$fgetoldTexturePreviewSize(this.this$0).getWidth(), InstantCameraView.m10956$$Nest$fgetoldTexturePreviewSize(this.this$0).getHeight());
        L25:
            GLES20.glVertexAttribPointer(this.textureHandle, 2, 5126, false, 8, r17);
            GLES20.glUniformMatrix4fv(this.textureMatrixHandle, 1, false, InstantCameraView.m10952$$Nest$fgetmoldSTMatrix(this.this$0), 0);
            GLES20.glUniform1f(this.alphaHandle, 1.0f);
            GLES20.glBindTexture(36197, InstantCameraView.m10955$$Nest$fgetoldCameraTexture(this.this$0)[0]);
            GLES20.glDrawArrays(5, 0, 4);
        L26:
            int r42 = r21.previewWidth;
            if (r42 <= 0) goto L31;
            int r12 = r21.previewHeight;
            if (r12 <= 0) goto L31;
            GLES20.glUniform2f(this.previewSizeHandle, r42, r12);
            GLES20.glUniform2f(this.texelSizeHandle, (1.0f / r21.previewWidth) / 2.0f, (1.0f / r21.previewHeight) / 2.0f);
        L31:
            int r43 = r21.textureId;
            if (r43 == Integer.MIN_VALUE) goto L34;
            GLES20.glUniformMatrix4fv(this.textureMatrixHandle, 1, false, r21.stMatrix, 0);
            GLES20.glUniform1f(this.alphaHandle, InstantCameraView.m10935$$Nest$fgetcameraTextureAlpha(this.this$0));
            GLES20.glBindTexture(36197, r43);
            GLES20.glDrawArrays(5, 0, 4);
        L34:
            GLES20.glDisableVertexAttribArray(this.positionHandle);
            GLES20.glDisableVertexAttribArray(this.textureHandle);
            GLES20.glBindTexture(36197, 0);
            GLES20.glUseProgram(0);
            InstantCameraVideoEncoderOverlayHelper r3 = this.overlayHelper;
            if (r3 == null) goto L39;
            r3.render(r19);
            if (this.blendEnabled == false) goto L39;
            GLES20.glEnable(3042);
        L39:
            this.thumbActiveTimeNs += r19;
            maybeScheduleKeyframeThumb();
            if (InstantCameraView.m10955$$Nest$fgetoldCameraTexture(this.this$0)[0] == 0) goto L51;
            if (InstantCameraView.m10935$$Nest$fgetcameraTextureAlpha(this.this$0) >= 1.0f) goto L51;
            if (InstantCameraView.m10928$$Nest$fgetbothCameras(this.this$0) == true) goto L51;
            InstantCameraView r32 = this.this$0;
            InstantCameraView.m10978$$Nest$fputcameraTextureAlpha(r32, InstantCameraView.m10935$$Nest$fgetcameraTextureAlpha(r32) + (r19 / 2.0E8f));
            if (InstantCameraView.m10935$$Nest$fgetcameraTextureAlpha(this.this$0) <= 1.0f) goto L53;
            GLES20.glDisable(3042);
            this.blendEnabled = false;
            InstantCameraView.m10978$$Nest$fputcameraTextureAlpha(this.this$0, 1.0f);
            GLES20.glDeleteTextures(1, InstantCameraView.m10955$$Nest$fgetoldCameraTexture(this.this$0), 0);
            InstantCameraView.m10955$$Nest$fgetoldCameraTexture(this.this$0)[0] = 0;
            if (InstantCameraView.m10932$$Nest$fgetcameraReady(this.this$0) == true) goto L53;
            InstantCameraView.m10976$$Nest$fputcameraReady(this.this$0, true);
            AndroidUtilities.runOnUIThread(new InstantCameraView$EncoderRenderer$$ExternalSyntheticLambda0(this));
        L53:
            return true;
        L51:
            if (InstantCameraView.m10932$$Nest$fgetcameraReady(this.this$0) == true) goto L53;
            InstantCameraView.m10976$$Nest$fputcameraReady(this.this$0, true);
            AndroidUtilities.runOnUIThread(new InstantCameraView$EncoderRenderer$$ExternalSyntheticLambda1(this));
            goto L53
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Renderer
        public void onEncoderSurfaceCreated(int r4, int r5) {
            this.videoWidth = r4;
            this.videoHeight = r5;
            this.blendEnabled = false;
            GLES20.glBlendFunc(770, 771);
            InstantCameraVideoEncoderOverlayHelper r52 = this.overlayHelper;
            if (r52 == null) goto L5;
            r52.destroy();
        L5:
            this.overlayHelper = new InstantCameraVideoEncoderOverlayHelper(this.videoWidth, this.videoHeight);
            int r53 = InstantCameraView.m11012$$Nest$mloadShader(this.this$0, 35633, "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n   gl_Position = uMVPMatrix * aPosition;\n   vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n");
            InstantCameraView r0 = this.this$0;
            if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L8;
            String r1 = "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform float alpha;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   vec4 color = texture2D(sTexture, vTextureCoord);\n   gl_FragColor = vec4(color.rgb * alpha, alpha);\n}\n";
        L9:
            int r02 = InstantCameraView.m11012$$Nest$mloadShader(r0, 35632, r1);
            if (r53 == 0) goto L18;
            if (r02 == 0) goto L19;
            int r12 = GLES20.glCreateProgram();
            this.drawProgram = r12;
            GLES20.glAttachShader(r12, r53);
            GLES20.glAttachShader(this.drawProgram, r02);
            GLES20.glLinkProgram(this.drawProgram);
            int[] r54 = new int[1];
            GLES20.glGetProgramiv(this.drawProgram, 35714, r54, 0);
            int r55 = r54[0];
            int r03 = this.drawProgram;
            if (r55 != 0) goto L16;
            GLES20.glDeleteProgram(r03);
            this.drawProgram = 0;
            return;
        L16:
            this.positionHandle = GLES20.glGetAttribLocation(r03, "aPosition");
            this.textureHandle = GLES20.glGetAttribLocation(this.drawProgram, "aTextureCoord");
            this.previewSizeHandle = GLES20.glGetUniformLocation(this.drawProgram, "preview");
            this.resolutionHandle = GLES20.glGetUniformLocation(this.drawProgram, "resolution");
            this.alphaHandle = GLES20.glGetUniformLocation(this.drawProgram, "alpha");
            this.vertexMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uMVPMatrix");
            this.textureMatrixHandle = GLES20.glGetUniformLocation(this.drawProgram, "uSTMatrix");
            this.texelSizeHandle = GLES20.glGetUniformLocation(this.drawProgram, "texelSize");
            return;
        L19:
            return;
        L18:
            return;
        L8:
            InstantCameraView r13 = this.this$0;
            r1 = InstantCameraView.m11008$$Nest$mcreateFragmentShaderV2(r13, InstantCameraView.m10958$$Nest$fgetpreviewSize(r13)[0]);
            goto L9
        }

        @Override // com.exteragram.messenger.camera.RoundVideoEncoder.Renderer
        public void onEncoderSurfaceDestroyed() {
            InstantCameraVideoEncoderOverlayHelper r0 = this.overlayHelper;
            if (r0 == null) goto L5;
            r0.destroy();
            this.overlayHelper = null;
        L5:
            int r02 = this.drawProgram;
            if (r02 == 0) goto L9;
            GLES20.glDeleteProgram(r02);
            this.drawProgram = 0;
            return;
        }
    }

    public class GenerateKeyframeThumbTask implements Runnable {
        private final AtomicBoolean inFlight;
        final /* synthetic */ InstantCameraView this$0;

        /* renamed from: $r8$lambda$RmkGsXgrLFJvw_j6i9jPS-6IrqA, reason: not valid java name */
        public static /* synthetic */ void m11024$r8$lambda$RmkGsXgrLFJvw_j6i9jPS6IrqA(GenerateKeyframeThumbTask r0, Bitmap r1) {
            r0.lambda$run$0(r1);
        }

        public GenerateKeyframeThumbTask(InstantCameraView r1, AtomicBoolean r2) {
            this.this$0 = r1;
            this.inFlight = r2;
        }

        private /* synthetic */ void lambda$run$0(Bitmap r3) {
            if (r3 == null) goto L6;
            if (r3.getPixel(0, 0) == 0) goto L6;
        L9:
            InstantCameraView.m10949$$Nest$fgetkeyframeThumbs(this.this$0).add(r3);
            return;
        L6:
            if (InstantCameraView.m10949$$Nest$fgetkeyframeThumbs(this.this$0).size() <= 1) goto L9;
            InstantCameraView.m10949$$Nest$fgetkeyframeThumbs(this.this$0).add((Bitmap) InstantCameraView.m10949$$Nest$fgetkeyframeThumbs(this.this$0).get(InstantCameraView.m10949$$Nest$fgetkeyframeThumbs(this.this$0).size() - 1));
        }

        @Override // java.lang.Runnable
        public void run() {
            TextureView r1 = InstantCameraView.m10967$$Nest$fgettextureView(this.this$0);     // Catch: Throwable -> L8
            if (r1 != null) goto L17;
        L12:
            this.inFlight.set(false);
            return;
        L17:
            AndroidUtilities.runOnUIThread(new InstantCameraView$GenerateKeyframeThumbTask$$ExternalSyntheticLambda0(this, r1.getBitmap(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f))));     // Catch: Throwable -> L8 Exception -> L10
        L10:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            this.inFlight.set(false);
            throw th;
        }
    }

    public class InstantViewCameraContainer extends InstantCameraViewBase.InstantViewCameraContainer {
        float imageProgress;
        ImageReceiver imageReceiver;
        final /* synthetic */ InstantCameraView this$0;

        public InstantViewCameraContainer(InstantCameraView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            r1.setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            float r0 = this.imageProgress;
            if (r0 == 1.0f) goto L9;
            float r02 = r0 + 0.064f;
            this.imageProgress = r02;
            if (r02 <= 1.0f) goto L7;
            this.imageProgress = 1.0f;
        L7:
            invalidate();
        L9:
            if (this.imageReceiver == null) goto L15;
            r4.save();
            if (this.imageReceiver.getImageWidth() == InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0)) goto L13;
            float r03 = InstantCameraView.m10968$$Nest$fgettextureViewSize(this.this$0) / this.imageReceiver.getImageWidth();
            r4.scale(r03, r03);
        L13:
            r4.translate(-this.imageReceiver.getImageX(), -this.imageReceiver.getImageY());
            float r04 = this.imageReceiver.getAlpha();
            this.imageReceiver.setAlpha(this.imageProgress);
            this.imageReceiver.draw(r4);
            this.imageReceiver.setAlpha(r04);
            r4.restore();
            return;
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

    public static class SendOptions {
        long effectId;
        boolean notify;
        int scheduleDate;
        int scheduleRepeatPeriod;
        long stars;
        int ttl;

        public SendOptions(boolean r1, int r2, int r3, int r4, long r5, long r7) {
            this.notify = r1;
            this.scheduleDate = r2;
            this.scheduleRepeatPeriod = r3;
            this.ttl = r4;
            this.effectId = r5;
            this.stars = r7;
        }
    }

    /* renamed from: $r8$lambda$00thkrk6hVlEXbaEQF_-7OfxrAg, reason: not valid java name */
    public static /* synthetic */ void m10924$r8$lambda$00thkrk6hVlEXbaEQF_7OfxrAg(InstantCameraView r0, Camera2Session r1) {
        r0.lambda$bindCamera2ZoomSlider$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$1Zw33tYmddytOZX7RhhNo5lL13M(InstantCameraView r0, CameraSession r1) {
        r0.lambda$createCamera$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$60KgVz_MOIuV_WldjQ4vzfUJpV4(InstantCameraView r0, CameraXSession r1) {
        r0.lambda$createCamera$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$6dHFuIFxuYbGUG7a79KPHNi5utI(InstantCameraView r0, Float r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$6wO0sp9sxcoWydB_xV4vSTDZ3lw(InstantCameraView r0, DebugOverlayView.ContentBuilder r1) {
        r0.populateCameraDebugOverlay(r1);
    }

    public static /* synthetic */ void $r8$lambda$C95YMj2FyCANH5H4rnGqIf8igwc(InstantCameraView r0, ValueAnimator r1) {
        r0.lambda$finishZoom$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$EVLSm7SQ9LvX3HDowYPztFvaLNw(InstantCameraView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ int $r8$lambda$JGI5kBaG7zbZNQIRRLRBaADb4XI(org.telegram.messenger.camera.Size r3, org.telegram.messenger.camera.Size r4) {
        float r0 = Math.abs(1.0f - (Math.min(r3.mHeight, r3.mWidth) / Math.max(r3.mHeight, r3.mWidth)));
        float r32 = Math.abs(1.0f - (Math.min(r4.mHeight, r4.mWidth) / Math.max(r4.mHeight, r4.mWidth)));
        if (r0 >= r32) goto L7;
        return -1;
    L7:
        if (r0 <= r32) goto L10;
        return 1;
    L10:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$KQ0JgUh6NgBbIjl8R6YSUbEKBZU(InstantCameraView r0, ValueAnimator r1) {
        r0.lambda$finishZoom$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$LeHUECwEpZw1C37dl7ylb5P5yRM(InstantCameraView r0, ValueAnimator r1) {
        r0.lambda$adjustZoom$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$NpLvse9CXXnv1X_7bFl2JL0Tzjo(InstantCameraView r0, int r1, SurfaceTexture r2) {
        r0.lambda$createCamera$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OU5qzP8hjtP7acYroxvFL245qdg(InstantCameraView r0, ValueAnimator r1) {
        r0.lambda$adjustZoom$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$OlNz90xgOCJil11CDmXTfZedutI(InstantCameraView r0, int r1, int r2, int r3) {
        r0.lambda$createCamera$15(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$RQKmJOtbpZYfJO3eqrCwph_i88o(InstantCameraView r0, View r1, MotionEvent r2) {
        return r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VCbx47UDplgrpdghX9vvodd0PME(InstantCameraView r0, File r1, SendOptions r2, VideoEditedInfo r3, boolean r4, int r5, int r6) {
        r0.lambda$handleEncoderFinished$20(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$VZgvTrEoect_1lhrcWm4wi8Uy1Y(InstantCameraView r0, boolean r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$_x7YBsbLKZKblXEM7TFN8Bnm2AM(InstantCameraView r0, boolean r1, ValueAnimator r2) {
        r0.lambda$startAnimation$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bTZBuCzfAKe7nKpI1hfb4jzYeTo(InstantCameraView r0) {
        r0.lambda$handleEncoderFinished$21();
    }

    public static /* synthetic */ void $r8$lambda$dwdTlZURzTW3rnHrKLBue44ayhQ(Bitmap r3) {
        FileOutputStream r1 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "icthumb.jpg"));     // Catch: Throwable -> L4
        r3.compress(Bitmap.CompressFormat.JPEG, 87, r1);     // Catch: Throwable -> L4
        r1.close();     // Catch: Throwable -> L4
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$eqgBRRwilQpcenqxHjHc4UkqOc0(InstantCameraView r0, Theme.ResourcesProvider r1, View r2) {
        return r0.lambda$new$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$l606wsdZLHJnEAFZVaZQa0FlDE8(InstantCameraView r0, float r1, boolean r2) {
        r0.lambda$new$8(r1, r2);
    }

    /* renamed from: $r8$lambda$mTkEPR7aQpzePoBed-g8XnLdXGU, reason: not valid java name */
    public static /* synthetic */ void m10925$r8$lambda$mTkEPR7aQpzePoBedg8XnLdXGU(InstantCameraView r0, SendOptions r1) {
        r0.lambda$requestStopRecording$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$ne343mnTPhk7J42gB0dqPR3bqck(InstantCameraView r0, CameraSession r1) {
        r0.lambda$createCamera$13(r1);
    }

    /* renamed from: $r8$lambda$oVD-fA8KErmVbuvIs-zpVtXHJkg, reason: not valid java name */
    public static /* synthetic */ void m10926$r8$lambda$oVDfA8KErmVbuvIszpVtXHJkg() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
    }

    public static /* synthetic */ void $r8$lambda$tFP9ihZA51UDSVe4pkaXNGPePSQ(InstantCameraView r0, boolean r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$v7buj4tgsTwWmdxkwuIz667cGec(InstantCameraView r0, Float r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$yAqRTuLDPQE1ejPKITAPXDPf97o(InstantCameraView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m10927$$Nest$fgetanimatorSet(InstantCameraView r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetbothCameras, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10928$$Nest$fgetbothCameras(InstantCameraView r0) {
        return r0.bothCameras;
    }

    /* renamed from: -$$Nest$fgetcamera2Sessions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Camera2Session[] m10929$$Nest$fgetcamera2Sessions(InstantCameraView r0) {
        return r0.camera2Sessions;
    }

    /* renamed from: -$$Nest$fgetcameraContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InstantViewCameraContainer m10930$$Nest$fgetcameraContainer(InstantCameraView r0) {
        return r0.cameraContainer;
    }

    /* renamed from: -$$Nest$fgetcameraFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ File m10931$$Nest$fgetcameraFile(InstantCameraView r0) {
        return r0.cameraFile;
    }

    /* renamed from: -$$Nest$fgetcameraReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10932$$Nest$fgetcameraReady(InstantCameraView r0) {
        return r0.cameraReady;
    }

    /* renamed from: -$$Nest$fgetcameraSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraSession m10933$$Nest$fgetcameraSession(InstantCameraView r0) {
        return r0.cameraSession;
    }

    /* renamed from: -$$Nest$fgetcameraTexture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10934$$Nest$fgetcameraTexture(InstantCameraView r0) {
        return r0.cameraTexture;
    }

    /* renamed from: -$$Nest$fgetcameraTextureAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10935$$Nest$fgetcameraTextureAlpha(InstantCameraView r0) {
        return r0.cameraTextureAlpha;
    }

    /* renamed from: -$$Nest$fgetcameraTextureAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10936$$Nest$fgetcameraTextureAvailable(InstantCameraView r0) {
        return r0.cameraTextureAvailable;
    }

    /* renamed from: -$$Nest$fgetcameraThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraGLThread m10937$$Nest$fgetcameraThread(InstantCameraView r0) {
        return r0.cameraThread;
    }

    /* renamed from: -$$Nest$fgetcameraXSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraXSession m10938$$Nest$fgetcameraXSession(InstantCameraView r0) {
        return r0.cameraXSession;
    }

    /* renamed from: -$$Nest$fgetcancelled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10939$$Nest$fgetcancelled(InstantCameraView r0) {
        return r0.cancelled;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10940$$Nest$fgetcurrentAccount(InstantCameraView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m10941$$Nest$fgetdelegate(InstantCameraView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetencoderCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoEncoder.Callback m10942$$Nest$fgetencoderCallback(InstantCameraView r0) {
        return r0.encoderCallback;
    }

    /* renamed from: -$$Nest$fgetencoderFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ File m10943$$Nest$fgetencoderFile(InstantCameraView r0) {
        return r0.encoderFile;
    }

    /* renamed from: -$$Nest$fgetencoderFrameRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10944$$Nest$fgetencoderFrameRate(InstantCameraView r0) {
        return r0.encoderFrameRate;
    }

    /* renamed from: -$$Nest$fgetfinishZoomTransition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m10945$$Nest$fgetfinishZoomTransition(InstantCameraView r0) {
        return r0.finishZoomTransition;
    }

    /* renamed from: -$$Nest$fgetfirstFrameThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m10946$$Nest$fgetfirstFrameThumb(InstantCameraView r0) {
        return r0.firstFrameThumb;
    }

    /* renamed from: -$$Nest$fgetgenerateKeyframeThumbsQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m10947$$Nest$fgetgenerateKeyframeThumbsQueue(InstantCameraView r0) {
        return r0.generateKeyframeThumbsQueue;
    }

    /* renamed from: -$$Nest$fgetisSecretChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10948$$Nest$fgetisSecretChat(InstantCameraView r0) {
        return r0.isSecretChat;
    }

    /* renamed from: -$$Nest$fgetkeyframeThumbs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10949$$Nest$fgetkeyframeThumbs(InstantCameraView r0) {
        return r0.keyframeThumbs;
    }

    /* renamed from: -$$Nest$fgetmMVPMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m10950$$Nest$fgetmMVPMatrix(InstantCameraView r0) {
        return r0.mMVPMatrix;
    }

    /* renamed from: -$$Nest$fgetmSTMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m10951$$Nest$fgetmSTMatrix(InstantCameraView r0) {
        return r0.mSTMatrix;
    }

    /* renamed from: -$$Nest$fgetmoldSTMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m10952$$Nest$fgetmoldSTMatrix(InstantCameraView r0) {
        return r0.moldSTMatrix;
    }

    /* renamed from: -$$Nest$fgetmuteAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m10953$$Nest$fgetmuteAnimation(InstantCameraView r0) {
        return r0.muteAnimation;
    }

    /* renamed from: -$$Nest$fgetneedDrawFlickerStub, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10954$$Nest$fgetneedDrawFlickerStub(InstantCameraView r0) {
        return r0.needDrawFlickerStub;
    }

    /* renamed from: -$$Nest$fgetoldCameraTexture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10955$$Nest$fgetoldCameraTexture(InstantCameraView r0) {
        return r0.oldCameraTexture;
    }

    /* renamed from: -$$Nest$fgetoldTexturePreviewSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ org.telegram.messenger.camera.Size m10956$$Nest$fgetoldTexturePreviewSize(InstantCameraView r0) {
        return r0.oldTexturePreviewSize;
    }

    /* renamed from: -$$Nest$fgetoldTextureTextureBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatBuffer m10957$$Nest$fgetoldTextureTextureBuffer(InstantCameraView r0) {
        return r0.oldTextureTextureBuffer;
    }

    /* renamed from: -$$Nest$fgetpreviewSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ org.telegram.messenger.camera.Size[] m10958$$Nest$fgetpreviewSize(InstantCameraView r0) {
        return r0.previewSize;
    }

    /* renamed from: -$$Nest$fgetrecordedTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m10959$$Nest$fgetrecordedTime(InstantCameraView r2) {
        return r2.recordedTime;
    }

    /* renamed from: -$$Nest$fgetrecordingGuid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10960$$Nest$fgetrecordingGuid(InstantCameraView r0) {
        return r0.recordingGuid;
    }

    /* renamed from: -$$Nest$fgetscaleX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10961$$Nest$fgetscaleX(InstantCameraView r0) {
        return r0.scaleX;
    }

    /* renamed from: -$$Nest$fgetscaleY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10962$$Nest$fgetscaleY(InstantCameraView r0) {
        return r0.scaleY;
    }

    /* renamed from: -$$Nest$fgetsurfaceIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10963$$Nest$fgetsurfaceIndex(InstantCameraView r0) {
        return r0.surfaceIndex;
    }

    /* renamed from: -$$Nest$fgettextureBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatBuffer m10964$$Nest$fgettextureBuffer(InstantCameraView r0) {
        return r0.textureBuffer;
    }

    /* renamed from: -$$Nest$fgettextureCoordsData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m10965$$Nest$fgettextureCoordsData(InstantCameraView r0) {
        return r0.textureCoordsData;
    }

    /* renamed from: -$$Nest$fgettextureOverlayView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m10966$$Nest$fgettextureOverlayView(InstantCameraView r0) {
        return r0.textureOverlayView;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m10967$$Nest$fgettextureView(InstantCameraView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgettextureViewSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10968$$Nest$fgettextureViewSize(InstantCameraView r0) {
        return r0.textureViewSize;
    }

    /* renamed from: -$$Nest$fgetuseCamera2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10969$$Nest$fgetuseCamera2(InstantCameraView r0) {
        return r0.useCamera2;
    }

    /* renamed from: -$$Nest$fgetvertexBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatBuffer m10970$$Nest$fgetvertexBuffer(InstantCameraView r0) {
        return r0.vertexBuffer;
    }

    /* renamed from: -$$Nest$fgetvideoEditedInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoEditedInfo m10971$$Nest$fgetvideoEditedInfo(InstantCameraView r0) {
        return r0.videoEditedInfo;
    }

    /* renamed from: -$$Nest$fgetvideoEncoder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoEncoder m10972$$Nest$fgetvideoEncoder(InstantCameraView r0) {
        return r0.videoEncoder;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m10973$$Nest$fgetvideoPlayer(InstantCameraView r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fgetzoomSlider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InstantCameraZoomSlider m10974$$Nest$fgetzoomSlider(InstantCameraView r0) {
        return r0.zoomSlider;
    }

    /* renamed from: -$$Nest$fputcamera2SessionCurrent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10975$$Nest$fputcamera2SessionCurrent(InstantCameraView r0, Camera2Session r1) {
        r0.camera2SessionCurrent = r1;
    }

    /* renamed from: -$$Nest$fputcameraReady, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10976$$Nest$fputcameraReady(InstantCameraView r0, boolean r1) {
        r0.cameraReady = r1;
    }

    /* renamed from: -$$Nest$fputcameraSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10977$$Nest$fputcameraSession(InstantCameraView r0, CameraSession r1) {
        r0.cameraSession = r1;
    }

    /* renamed from: -$$Nest$fputcameraTextureAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10978$$Nest$fputcameraTextureAlpha(InstantCameraView r0, float r1) {
        r0.cameraTextureAlpha = r1;
    }

    /* renamed from: -$$Nest$fputcameraTextureAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10979$$Nest$fputcameraTextureAvailable(InstantCameraView r0, boolean r1) {
        r0.cameraTextureAvailable = r1;
    }

    /* renamed from: -$$Nest$fputcameraThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10980$$Nest$fputcameraThread(InstantCameraView r0, CameraGLThread r1) {
        r0.cameraThread = r1;
    }

    /* renamed from: -$$Nest$fputcameraZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10981$$Nest$fputcameraZoom(InstantCameraView r0, float r1) {
        r0.cameraZoom = r1;
    }

    /* renamed from: -$$Nest$fputencoderFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10982$$Nest$fputencoderFile(InstantCameraView r0, File r1) {
        r0.encoderFile = r1;
    }

    /* renamed from: -$$Nest$fputencoderFinishRequested, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10983$$Nest$fputencoderFinishRequested(InstantCameraView r0, boolean r1) {
        r0.encoderFinishRequested = r1;
    }

    /* renamed from: -$$Nest$fputencoderFrameRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10984$$Nest$fputencoderFrameRate(InstantCameraView r0, int r1) {
        r0.encoderFrameRate = r1;
    }

    /* renamed from: -$$Nest$fputencoderSend, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10985$$Nest$fputencoderSend(InstantCameraView r0, int r1) {
        r0.encoderSend = r1;
    }

    /* renamed from: -$$Nest$fputencoderSendOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10986$$Nest$fputencoderSendOptions(InstantCameraView r0, SendOptions r1) {
        r0.encoderSendOptions = r1;
    }

    /* renamed from: -$$Nest$fputfinishZoomTransition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10987$$Nest$fputfinishZoomTransition(InstantCameraView r0, ValueAnimator r1) {
        r0.finishZoomTransition = r1;
    }

    /* renamed from: -$$Nest$fputfirstFrameThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10988$$Nest$fputfirstFrameThumb(InstantCameraView r0, Bitmap r1) {
        r0.firstFrameThumb = r1;
    }

    /* renamed from: -$$Nest$fputflipAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10989$$Nest$fputflipAnimationInProgress(InstantCameraView r0, boolean r1) {
        r0.flipAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputgenerateKeyframeThumbsQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10990$$Nest$fputgenerateKeyframeThumbsQueue(InstantCameraView r0, DispatchQueue r1) {
        r0.generateKeyframeThumbsQueue = r1;
    }

    /* renamed from: -$$Nest$fputmuteAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10991$$Nest$fputmuteAnimation(InstantCameraView r0, AnimatorSet r1) {
        r0.muteAnimation = r1;
    }

    /* renamed from: -$$Nest$fputoldTexturePreviewSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10992$$Nest$fputoldTexturePreviewSize(InstantCameraView r0, org.telegram.messenger.camera.Size r1) {
        r0.oldTexturePreviewSize = r1;
    }

    /* renamed from: -$$Nest$fputoldTextureTextureBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10993$$Nest$fputoldTextureTextureBuffer(InstantCameraView r0, FloatBuffer r1) {
        r0.oldTextureTextureBuffer = r1;
    }

    /* renamed from: -$$Nest$fputrecordPlusTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10994$$Nest$fputrecordPlusTime(InstantCameraView r0, long r1) {
        r0.recordPlusTime = r1;
    }

    /* renamed from: -$$Nest$fputrecordStartTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10995$$Nest$fputrecordStartTime(InstantCameraView r0, long r1) {
        r0.recordStartTime = r1;
    }

    /* renamed from: -$$Nest$fputrecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10996$$Nest$fputrecording(InstantCameraView r0, boolean r1) {
        r0.recording = r1;
    }

    /* renamed from: -$$Nest$fputscaleX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10997$$Nest$fputscaleX(InstantCameraView r0, float r1) {
        r0.scaleX = r1;
    }

    /* renamed from: -$$Nest$fputscaleY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10998$$Nest$fputscaleY(InstantCameraView r0, float r1) {
        r0.scaleY = r1;
    }

    /* renamed from: -$$Nest$fputsentMedia, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10999$$Nest$fputsentMedia(InstantCameraView r0, boolean r1) {
        r0.sentMedia = r1;
    }

    /* renamed from: -$$Nest$fputsetVisibilityFromPause, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11000$$Nest$fputsetVisibilityFromPause(InstantCameraView r0, boolean r1) {
        r0.setVisibilityFromPause = r1;
    }

    /* renamed from: -$$Nest$fputsurfaceIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11001$$Nest$fputsurfaceIndex(InstantCameraView r0, int r1) {
        r0.surfaceIndex = r1;
    }

    /* renamed from: -$$Nest$fputtextureBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11002$$Nest$fputtextureBuffer(InstantCameraView r0, FloatBuffer r1) {
        r0.textureBuffer = r1;
    }

    /* renamed from: -$$Nest$fputvertexBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11003$$Nest$fputvertexBuffer(InstantCameraView r0, FloatBuffer r1) {
        r0.vertexBuffer = r1;
    }

    /* renamed from: -$$Nest$fputvideoConvertFirstWrite, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11004$$Nest$fputvideoConvertFirstWrite(InstantCameraView r0, boolean r1) {
        r0.videoConvertFirstWrite = r1;
    }

    /* renamed from: -$$Nest$fputvideoEncoder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11005$$Nest$fputvideoEncoder(InstantCameraView r0, RoundVideoEncoder r1) {
        r0.videoEncoder = r1;
    }

    /* renamed from: -$$Nest$mcancelZoomAnimations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11006$$Nest$mcancelZoomAnimations(InstantCameraView r0) {
        r0.cancelZoomAnimations();
    }

    /* renamed from: -$$Nest$mcreateCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11007$$Nest$mcreateCamera(InstantCameraView r0, int r1, SurfaceTexture r2) {
        r0.createCamera(r1, r2);
    }

    /* renamed from: -$$Nest$mcreateFragmentShaderV2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m11008$$Nest$mcreateFragmentShaderV2(InstantCameraView r0, org.telegram.messenger.camera.Size r1) {
        return r0.createFragmentShaderV2(r1);
    }

    /* renamed from: -$$Nest$mdidWriteData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11009$$Nest$mdidWriteData(InstantCameraView r0, File r1, long r2, boolean r4) {
        r0.didWriteData(r1, r2, r4);
    }

    /* renamed from: -$$Nest$mhandleEncoderFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11010$$Nest$mhandleEncoderFinished(InstantCameraView r0, RoundVideoEncoder.FinishReason r1) {
        r0.handleEncoderFinished(r1);
    }

    /* renamed from: -$$Nest$mhandleEncoderPaused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11011$$Nest$mhandleEncoderPaused(InstantCameraView r0, File r1) {
        r0.handleEncoderPaused(r1);
    }

    /* renamed from: -$$Nest$mloadShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11012$$Nest$mloadShader(InstantCameraView r0, int r1, String r2) {
        return r0.loadShader(r1, r2);
    }

    /* renamed from: -$$Nest$mreleaseCameraXSession, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11013$$Nest$mreleaseCameraXSession(InstantCameraView r0) {
        r0.releaseCameraXSession();
    }

    /* renamed from: -$$Nest$mrequestStopRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11014$$Nest$mrequestStopRecording(InstantCameraView r0, int r1, SendOptions r2) {
        r0.requestStopRecording(r1, r2);
    }

    /* renamed from: -$$Nest$mresolveEncoderFrameRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11015$$Nest$mresolveEncoderFrameRate(InstantCameraView r0) {
        return r0.resolveEncoderFrameRate();
    }

    /* renamed from: -$$Nest$mupdateFlash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11016$$Nest$mupdateFlash(InstantCameraView r0) {
        r0.updateFlash();
    }

    static {
        ALLOW_BIG_CAMERA_WHITELIST = new int[]{285904780, -1394191079};
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public InstantCameraView(Context r22, Delegate r23, Theme.ResourcesProvider r24, boolean r25) {
        super(r22);
        this.currentAccount = UserConfig.selectedAccount;
        this.isFrontface = true;
        this.position = new int[2];
        this.cameraTexture = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        this.oldCameraTexture = new int[1];
        this.cameraTextureAlpha = 1.0f;
        this.previewSize = new org.telegram.messenger.camera.Size[2];
        if (SharedConfig.roundCamera16to9 == false) goto L5;
        org.telegram.messenger.camera.Size r5 = new org.telegram.messenger.camera.Size(16, 9);
    L6:
        this.aspectRatio = r5;
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_2) goto L9;
        boolean r52 = true;
    L10:
        this.useCamera2 = r52;
        this.camera2Sessions = new Camera2Session[2];
        this.mMVPMatrix = new float[16];
        this.mSTMatrix = new float[16];
        this.moldSTMatrix = new float[16];
        this.textureCoordsData = new float[8];
        this.encoderFrameRate = 30;
        this.keyframeThumbs = new ArrayList();
        this.encoderCallback = new AnonymousClass12(this);
        this.initialCameraZoom = 0.0f;
        if (r25 == false) goto L13;
        float r53 = 24.0f;
    L14:
        this.buttonsSizePx = AndroidUtilities.dp(r53);
        this.resourcesProvider = r24;
        this.parentView = r23.getFragmentView();
        setWillNotDraw(false);
        this.delegate = r23;
        this.recordingGuid = r23.getClassGuid();
        this.isSecretChat = r23.isSecretChat();
        AnonymousClass1 r54 = new AnonymousClass1(this, 1);
        this.paint = r54;
        r54.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        this.paint.setColor(-1);
        this.rect = new RectF();
        FlashViews r55 = new FlashViews(getContext(), null, this, null);
        this.flashViews = r55;
        r55.setWarmth(ExteraConfig.getFlashWarmth());
        r55.setIntensity(ExteraConfig.getFlashIntensity());
        addView(r55.backgroundView, LayoutHelper.createFrame(-1, -1, 119));
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), new AnonymousClass2(this));
        AnonymousClass3 r8 = new AnonymousClass3(this, r22);
        this.cameraContainer = r8;
        r8.setOutlineProvider(new AnonymousClass4(this));
        this.cameraContainer.setOnTouchListener(new InstantCameraView$$ExternalSyntheticLambda12(this));
        this.cameraContainer.setClipToOutline(true);
        this.cameraContainer.setWillNotDraw(false);
        View r82 = this.cameraContainer;
        int r12 = AndroidUtilities.roundPlayingMessageSize;
        addView(r82, new FrameLayout.LayoutParams(r12, r12, 17));
        addView(r55.foregroundView, LayoutHelper.createFrame(-1, -1, 119));
        LinearLayout r7 = new LinearLayout(r22);
        this.buttonsLayout = r7;
        r7.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        r7.setOrientation(0);
        addView(r7, LayoutHelper.createFrame(-2, 56.0f, 83, 1.0f, 0.0f, 0.0f, 0.0f));
        FlashViews.ImageViewInvertable r83 = new FlashViews.ImageViewInvertable(r22);
        this.switchCameraButton = r83;
        ImageView.ScaleType r10 = ImageView.ScaleType.CENTER;
        r83.setScaleType(r10);
        r83.setContentDescription(LocaleController.getString(R.string.AccDescrSwitchCamera));
        r7.addView(r83, LayoutHelper.createLinear(44, 44));
        r83.setOnClickListener(new InstantCameraView$$ExternalSyntheticLambda13(this));
        FlashViews.ImageViewInvertable r122 = new FlashViews.ImageViewInvertable(r22);
        this.flashButton = r122;
        r122.setScaleType(r10);
        r7.addView(r122, LayoutHelper.createLinear(44, 44));
        r122.setOnClickListener(new InstantCameraView$$ExternalSyntheticLambda14(this));
        r122.setOnLongClickListener(new InstantCameraView$$ExternalSyntheticLambda15(this, r24));
        updateFlash();
        if (r25 == true) goto L18;
        r55.add(r83);
        r55.add(r122);
    L20:
        ImageView r56 = new ImageView(r22);
        this.muteImageView = r56;
        r56.setScaleType(r10);
        this.muteImageView.setImageResource(R.drawable.video_mute);
        this.muteImageView.setAlpha(0.0f);
        addView(this.muteImageView, LayoutHelper.createFrame(48, 48, 17));
        Paint r57 = new Paint(1);
        r57.setColor(ColorUtils.setAlphaComponent(-16777216, 40));
        AnonymousClass7 r3 = new AnonymousClass7(this, getContext(), r57);
        this.textureOverlayView = r3;
        int r72 = AndroidUtilities.roundPlayingMessageSize;
        addView(r3, new FrameLayout.LayoutParams(r72, r72, 17));
        InstantCameraZoomSlider r32 = new InstantCameraZoomSlider(r22, r24);
        this.zoomSlider = r32;
        r32.setOnCameraZoomChangeListener(new InstantCameraView$$ExternalSyntheticLambda16(this));
        this.zoomSlider.setOpenAlpha(0.0f);
        addView(this.zoomSlider, LayoutHelper.createFrame(-2, -2, 17));
        if (DebugConfig.getDebugCameraMetrics() == false) goto L23;
        DebugOverlayView r2 = new DebugOverlayView(r22);
        r2.setDataSource(new InstantCameraView$$ExternalSyntheticLambda17(this));
        addView(r2, DebugOverlayView.createLayoutParams());
    L23:
        this.setVisibilityFromPause = false;
        setVisibility(4);
        return;
    L18:
        if (r24.isDark() == true) goto L20;
        r83.setInvert(0.6f);
        r122.setInvert(0.6f);
        goto L20
    L13:
        r53 = 28.0f;
        goto L14
    L9:
        r52 = false;
        goto L10
    L5:
        r5 = new org.telegram.messenger.camera.Size(4, 3);
        goto L6
    }

    private void adjustZoom(boolean r19) {
        if (isCameraReady() == false) goto L54;
        ValueAnimator r1 = this.zoomAnimator;
        if (r1 != null) goto L7;
    L9:
        cancelZoomAnimations();
        this.zoomSlider.beginSteppedZoomGesture();
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L29;
        float r12 = this.zoomSlider.getZoom();
        float r2 = this.zoomSlider.getMinimumZoom();
        float r122 = this.zoomSlider.getMaximumZoom();
        float r13 = Utilities.clamp(this.zoomSlider.getDisplayOneZoom(), r122, r2);
        double r14 = r122 / r13;
        int r10 = (int) Math.round(Math.log(r14) / Math.log(1.75d));
        if (r10 >= 1) goto L14;
        r10 = 1;
    L14:
        float r7 = (float) Math.pow(r14, 1.0d / r10);
        if (r19 == false) goto L21;
        if (r12 < r13) goto L27;
        r13 = r12 * r7;
    L27:
        ValueAnimator r15 = ValueAnimator.ofFloat(new float[]{r12, Utilities.clamp(r13, r122, r2)});
        this.zoomAnimator = r15;
        r15.setDuration(175);
        this.zoomAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.zoomAnimator.addUpdateListener(new InstantCameraView$$ExternalSyntheticLambda6(this));
        this.zoomAnimator.start();
        return;
    L21:
        if (r12 <= r13) goto L26;
        float r72 = r12 / r7;
        if (r72 < r13) goto L27;
        r13 = r72;
        goto L27
    L26:
        r13 = r2;
        goto L27
    L29:
        float r16 = this.cameraZoom;
        float r123 = 1.0f;
        if (this.useCamera2 == false) goto L45;
        Camera2Session r17 = this.camera2SessionCurrent;
        if (r17 == null) goto L56;
        float r18 = r17.getMinZoom();
        float r22 = this.camera2SessionCurrent.getMaxZoom();
        double r132 = r22;
        int r102 = (int) Math.round(Math.log(r132) / Math.log(1.75d));
        if (r102 >= 1) goto L37;
        r102 = 1;
    L37:
        float r73 = (float) Math.pow(r132, 1.0d / r102);
        float r8 = this.cameraZoom;
        if (r19 == false) goto L40;
        r123 = r8 * r73;
    L44:
        float r110 = Utilities.clamp(r123, r22, r18);
    L49:
        float r23 = this.cameraZoom;
        if (r23 == r110) goto L57;
        ValueAnimator r111 = ValueAnimator.ofFloat(new float[]{r23, r110});
        this.zoomAnimator = r111;
        r111.setDuration(175);
        this.zoomAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.zoomAnimator.addUpdateListener(new InstantCameraView$$ExternalSyntheticLambda7(this));
        this.zoomAnimator.start();
        return;
    L57:
        return;
    L40:
        float r82 = r8 / r73;
        if (r82 < 1.0f) goto L44;
        r123 = r82;
        goto L44
    L56:
        return;
    L45:
        if (r19 == false) goto L47;
        int r24 = 1;
    L48:
        r110 = Utilities.clamp(r16 + (r24 * 0.125f), 1.0f, 0.0f);
        goto L49
    L47:
        r24 = -1;
        goto L48
    L7:
        if (r1.isRunning() == false) goto L9;
        return;
    }

    @Deprecated
    private boolean allowBigSizeCamera() {
        if (SharedConfig.bigCameraForRound == false) goto L6;
        return true;
    L6:
        if (SharedConfig.deviceIsAboveAverage() == false) goto L9;
        return true;
    L9:
        if (Math.max(SharedConfig.getDevicePerformanceClass(), SharedConfig.getLegacyDevicePerformanceClass()) != 2) goto L11;
        return true;
    L11:
        int r5 = (Build.MANUFACTURER + " " + Build.DEVICE).toUpperCase().hashCode();
        int r2 = 0;
    L12:
        int[] r3 = ALLOW_BIG_CAMERA_WHITELIST;
        if (r2 >= r3.length) goto L18;
        if (r3[r2] == r5) goto L16;
        r2 = r2 + 1;
        goto L12
    L16:
        return true;
    L18:
        return false;
    }

    @Deprecated
    public static boolean allowBigSizeCameraDebug() {
        if (Math.max(SharedConfig.getDevicePerformanceClass(), SharedConfig.getLegacyDevicePerformanceClass()) != 2) goto L5;
        return true;
    L5:
        int r0 = (Build.MANUFACTURER + " " + Build.DEVICE).toUpperCase().hashCode();
        int r3 = 0;
    L6:
        int[] r4 = ALLOW_BIG_CAMERA_WHITELIST;
        if (r3 >= r4.length) goto L12;
        if (r4[r3] == r0) goto L10;
        r3 = r3 + 1;
        goto L6
    L10:
        return true;
    L12:
        return false;
    }

    private void bindCamera2ZoomSlider(Camera2Session r2) {
        if (r2 != null) goto L5;
        this.zoomSlider.unbindSession();
        return;
    L5:
        this.cameraZoom = r2.getZoom();
        this.zoomSlider.bindSession(r2);
        r2.whenDone(new InstantCameraView$$ExternalSyntheticLambda8(this, r2));
    }

    private void cancelZoomAnimations() {
        ValueAnimator r0 = this.zoomAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.zoomAnimator = null;
    L5:
        ValueAnimator r02 = this.finishZoomTransition;
        if (r02 == null) goto L9;
        r02.cancel();
        this.finishZoomTransition = null;
        return;
    }

    private boolean checkPointerIds(MotionEvent r5) {
        if (r5.getPointerCount() >= 2) goto L6;
        return false;
    L6:
        if (this.pointerId1 != r5.getPointerId(0)) goto L11;
        if (this.pointerId2 != r5.getPointerId(1)) goto L11;
        return true;
    L11:
        if (this.pointerId1 == r5.getPointerId(1)) goto L13;
    L15:
        return false;
    L13:
        if (this.pointerId2 != r5.getPointerId(0)) goto L15;
        return true;
    }

    @Deprecated
    private org.telegram.messenger.camera.Size chooseOptimalSize(ArrayList<org.telegram.messenger.camera.Size> r7) {
        ArrayList<org.telegram.messenger.camera.Size> r0 = new ArrayList();
        int r2 = 1200;
        if (allowBigSizeCamera() == false) goto L5;
        int r1 = 1440;
    L7:
        if (Build.MANUFACTURER.equalsIgnoreCase("Samsung") == true) goto L10;
        r2 = r1;
    L10:
        int r3 = 0;
    L12:
        if (r3 >= r7.size()) goto L20;
        if (Math.max(r7.get(r3).mHeight, r7.get(r3).mWidth) > r2) goto L18;
        if (Math.min(r7.get(r3).mHeight, r7.get(r3).mWidth) < 320) goto L18;
        r0.add(r7.get(r3));
    L18:
        r3 = r3 + 1;
        goto L12
    L20:
        if (r0.isEmpty() == true) goto L27;
        if (allowBigSizeCamera() == false) goto L27;
        Collections.sort(r0, new InstantCameraView$$ExternalSyntheticLambda9());
        return r0.get(0);
    L27:
        if (r0.isEmpty() == true) goto L29;
        r7 = r0;
    L29:
        boolean r02 = Build.MANUFACTURER.equalsIgnoreCase("Xiaomi");
        org.telegram.messenger.camera.Size r6 = this.aspectRatio;
        if (r02 == false) goto L34;
        return CameraController.chooseOptimalSize(r7, 640, 480, r6, false);
    L34:
        return CameraController.chooseOptimalSize(r7, 480, 270, r6, false);
    L5:
        r1 = 1200;
        goto L7
    }

    private void createCamera(int r2, SurfaceTexture r3) {
        AndroidUtilities.runOnUIThread(new InstantCameraView$$ExternalSyntheticLambda22(this, r2, r3));
    }

    private String createFragmentShaderV2(org.telegram.messenger.camera.Size r2) {
        if (SharedConfig.deviceIsLow() == false) goto L5;
        return "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform float alpha;\nuniform vec2 preview;\nuniform vec2 resolution;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   vec4 textColor = texture2D(sTexture, vTextureCoord);\n   gl_FragColor = vec4(textColor.rgb * alpha, alpha);\n}\n";
    L5:
        if (allowBigSizeCamera() == false) goto L15;
        if (r2 != null) goto L8;
        return "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform vec2 resolution;\nuniform vec2 preview;\nuniform float alpha;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   vec2 c_textureSize = preview;\n   vec2 c_onePixel = (1.0 / c_textureSize);\n   vec2 uv = vTextureCoord;\n   vec2 pixel = uv * c_textureSize + 0.5;\n   vec2 frac = fract(pixel);\n   pixel = (floor(pixel) / c_textureSize) - vec2(c_onePixel);\n   vec4 tl = texture2D(sTexture, pixel + vec2(0.0         , 0.0));\n   vec4 tr = texture2D(sTexture, pixel + vec2(c_onePixel.x, 0.0));\n   vec4 bl = texture2D(sTexture, pixel + vec2(0.0         , c_onePixel.y));\n   vec4 br = texture2D(sTexture, pixel + vec2(c_onePixel.x, c_onePixel.y));\n   vec4 x1 = mix(tl, tr, frac.x);\n   vec4 x2 = mix(bl, br, frac.x);\n   gl_FragColor = mix(x1, x2, frac.y) * alpha;\n}\n";
    L8:
        if ((Math.max(r2.getHeight(), r2.getWidth()) * 0.7f) < MessagesController.getInstance(this.currentAccount).roundVideoSize) goto L16;
        return "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform vec2 resolution;\nuniform vec2 preview;\nuniform float alpha;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   vec2 c_textureSize = preview;\n   vec2 c_onePixel = (1.0 / c_textureSize);\n   vec2 uv = vTextureCoord;\n   vec2 pixel = uv * c_textureSize + 0.5;\n   vec2 frac = fract(pixel);\n   pixel = (floor(pixel) / c_textureSize) - vec2(c_onePixel);\n   vec4 tl = texture2D(sTexture, pixel + vec2(0.0         , 0.0));\n   vec4 tr = texture2D(sTexture, pixel + vec2(c_onePixel.x, 0.0));\n   vec4 bl = texture2D(sTexture, pixel + vec2(0.0         , c_onePixel.y));\n   vec4 br = texture2D(sTexture, pixel + vec2(c_onePixel.x, c_onePixel.y));\n   vec4 x1 = mix(tl, tr, frac.x);\n   vec4 x2 = mix(bl, br, frac.x);\n   gl_FragColor = mix(x1, x2, frac.y) * alpha;\n}\n";
    L16:
        return "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform float alpha;\nuniform vec2 preview;\nuniform vec2 resolution;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   vec4 textColor = texture2D(sTexture, vTextureCoord);\n   gl_FragColor = vec4(textColor.rgb * alpha, alpha);\n}\n";
    L15:
        return "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform float alpha;\nuniform vec2 preview;\nuniform vec2 resolution;\nuniform samplerExternalOES sTexture;\nvoid main() {\n   vec4 textColor = texture2D(sTexture, vTextureCoord);\n   gl_FragColor = vec4(textColor.rgb * alpha, alpha);\n}\n";
    }

    private void didWriteData(File r15, long r16, boolean r18) {
        boolean r0 = this.videoConvertFirstWrite;
        int r1 = this.currentAccount;
        long r2 = 0;
        if (r0 == false) goto L11;
        FileLoader.getInstance(r1).uploadFile(r15.toString(), this.isSecretChat, false, 1, 33554432, false);
        this.videoConvertFirstWrite = false;
        if (r18 == false) goto L16;
        FileLoader r4 = FileLoader.getInstance(this.currentAccount);
        String r5 = r15.toString();
        boolean r6 = this.isSecretChat;
        if (r18 == false) goto L9;
        r2 = r15.length();
    L9:
        r4.checkUploadNewDataAvailable(r5, r6, r16, r2);
        return;
    L16:
        return;
    L11:
        FileLoader r7 = FileLoader.getInstance(r1);
        String r8 = r15.toString();
        boolean r9 = this.isSecretChat;
        if (r18 == false) goto L14;
        r2 = r15.length();
    L14:
        r7.checkUploadNewDataAvailable(r8, r9, r16, r2);
    }

    private void handleEncoderFailure() {
        if (this.recording == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.cancelled = true;
        this.recording = false;
        this.flashing = false;
        updateFlash();
        stopProgressTimer();
        VideoPlayer r3 = this.videoPlayer;
        if (r3 == null) goto L12;
        r3.releasePlayer(true);
        this.videoPlayer = null;
    L12:
        if (r0 == false) goto L14;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.recordStopped, new Object[]{Integer.valueOf(this.recordingGuid), 6});
    L14:
        CameraGLThread r5 = this.cameraThread;
        if (r5 == null) goto L17;
        r5.shutdown(0, true, 0, 0, 0, 0);
        this.cameraThread = null;
    L17:
        File r02 = this.cameraFile;
        if (r02 == null) goto L20;
        r02.delete();
        AutoDeleteMediaTask.unlockFile(this.cameraFile);
        this.cameraFile = null;
    L20:
        this.encoderFile = null;
        startAnimation(false, false);
        invalidate();
        return;
    L5:
        if (this.cameraThread != null) goto L8;
        r0 = false;
        goto L9
    }

    private void handleEncoderFinished(RoundVideoEncoder.FinishReason r32) {
        this.videoEncoder = null;
        if (r32 != RoundVideoEncoder.FinishReason.COMPLETED) goto L5;
        int r3 = this.encoderSend;
    L6:
        SendOptions r5 = this.encoderSendOptions;
        this.encoderSendOptions = null;
        File r6 = this.previewFile;
        if (r6 == null) goto L10;
        r6.delete();
        this.previewFile = null;
    L10:
        if (r3 == 2) goto L14;
        DispatchQueue r62 = this.generateKeyframeThumbsQueue;
        if (r62 == null) goto L14;
        r62.cleanupQueue();
        this.generateKeyframeThumbsQueue.recycle();
        this.generateKeyframeThumbsQueue = null;
    L14:
        FileLog.d("InstantCamera encoder finished send " + r3);
        File r63 = this.encoderFile;
        if (r3 == 0) goto L74;
        if (r63 == null) goto L74;
        if (this.sentMedia == true) goto L69;
        this.sentMedia = true;
        if (this.videoEditedInfo != null) goto L24;
        VideoEditedInfo r1 = new VideoEditedInfo();
        this.videoEditedInfo = r1;
        r1.startTime = -1;
        r1.endTime = -1;
    L24:
        if (this.videoEditedInfo.needConvert() == false) goto L39;
        this.file = null;
        this.encryptedFile = null;
        this.key = null;
        this.iv = null;
        VideoEditedInfo r12 = this.videoEditedInfo;
        long r122 = r12.estimatedDuration;
        double r14 = r122;
        long r16 = 0;
        long r8 = r12.startTime;
        if (r8 < 0) goto L28;
    L27:
        int r19 = r3;
        long r2 = r12.endTime;
        if (r2 < 0) goto L32;
        r122 = r2;
    L32:
        long r123 = r122 - r8;
        r12.estimatedDuration = r123;
        r12.estimatedSize = Math.max(1, (long) (this.size * (r123 / r14)));
        this.videoEditedInfo.bitrate = SystemUtils.getRoundVideoBitrate() * 1024;
        VideoEditedInfo r13 = this.videoEditedInfo;
        long r22 = r13.startTime;
        if (r22 <= 0) goto L35;
        r13.startTime = r22 * 1000;
    L35:
        long r23 = r13.endTime;
        if (r23 <= 0) goto L38;
        r13.endTime = r23 * 1000;
    L38:
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(r63.getAbsolutePath(), false);
    L40:
        VideoEditedInfo r15 = this.videoEditedInfo;
        r15.roundVideo = true;
        r15.file = this.file;
        r15.encryptedFile = this.encryptedFile;
        r15.key = this.key;
        r15.iv = this.iv;
        r15.framerate = this.encoderFrameRate;
        int r24 = SystemUtils.getRoundVideoResolution();
        r15.originalWidth = r24;
        r15.resultWidth = r24;
        VideoEditedInfo r17 = this.videoEditedInfo;
        int r25 = SystemUtils.getRoundVideoResolution();
        r17.originalHeight = r25;
        r17.resultHeight = r25;
        this.videoEditedInfo.originalPath = r63.getAbsolutePath();
        VideoEditedInfo r18 = this.videoEditedInfo;
        if (r19 == 1) goto L43;
        setupVideoPlayer(r63);
        r18.estimatedDuration = this.recordedTime;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.audioDidSent, new Object[]{Integer.valueOf(this.recordingGuid), r18, r63.getAbsolutePath(), this.keyframeThumbs});
    L72:
        didWriteData(r63, r16, true);
        MediaController.getInstance().requestRecordAudioFocus(false);
        return;
    L43:
        if (this.delegate.isInScheduleMode() == false) goto L46;
        AlertsCreator.createScheduleDatePickerDialog(this.delegate.getParentActivity(), this.delegate.getDialogId(), new InstantCameraView$$ExternalSyntheticLambda0(this, r63, r5, r18), new InstantCameraView$$ExternalSyntheticLambda1(this), this.resourcesProvider);
    L66:
        this.videoEditedInfo = null;
        goto L72
    L46:
        MediaController.PhotoEntry r192 = new MediaController.PhotoEntry(0, 0, 0, r63.getAbsolutePath(), 0, true, 0, 0, 0);
        if (r5 == null) goto L49;
        r192.ttl = r5.ttl;
        r192.effectId = r5.effectId;
    L49:
        Delegate r33 = this.delegate;
        if (r5 != null) goto L52;
    L55:
        boolean r232 = true;
    L56:
        if (r5 == null) goto L58;
        int r242 = r5.scheduleDate;
    L59:
        if (r5 == null) goto L61;
        int r252 = r5.scheduleRepeatPeriod;
    L62:
        if (r5 == null) goto L64;
        long r27 = r5.stars;
    L65:
        r33.sendMedia(r192, r18, r232, r242, r252, false, r27);
        goto L66
    L64:
        r27 = r16;
        goto L65
    L61:
        r252 = 0;
        goto L62
    L58:
        r242 = 0;
        goto L59
    L52:
        if (r5.notify == true) goto L55;
        r232 = false;
        goto L56
    L28:
        r8 = 0;
        goto L27
    L39:
        r19 = r3;
        r16 = 0;
        this.videoEditedInfo.estimatedSize = Math.max(1, this.size);
        goto L40
    L69:
        r16 = 0;
        VideoEditedInfo r110 = this.videoEditedInfo;
        if (r110 == null) goto L72;
        r110.notReadyYet = false;
    L74:
        if (r63 == null) goto L76;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(r63.getAbsolutePath(), false);
    L76:
        MediaController.getInstance().requestRecordAudioFocus(false);
        if (r32 == RoundVideoEncoder.FinishReason.FAILED) goto L79;
        return;
    L79:
        if (this.cancelled == true) goto L83;
        handleEncoderFailure();
        return;
    L83:
        return;
    L5:
        r3 = 0;
        goto L6
    }

    private void handleEncoderPaused(File r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (this.cancelled == true) goto L9;
        VideoEditedInfo r0 = new VideoEditedInfo();
        this.videoEditedInfo = r0;
        r0.roundVideo = true;
        r0.startTime = -1;
        r0.endTime = -1;
        r0.file = this.file;
        r0.encryptedFile = this.encryptedFile;
        r0.key = this.key;
        r0.iv = this.iv;
        r0.estimatedSize = Math.max(1, this.size);
        VideoEditedInfo r02 = this.videoEditedInfo;
        r02.framerate = this.encoderFrameRate;
        int r1 = SystemUtils.getRoundVideoResolution();
        r02.originalWidth = r1;
        r02.resultWidth = r1;
        VideoEditedInfo r03 = this.videoEditedInfo;
        int r12 = SystemUtils.getRoundVideoResolution();
        r03.originalHeight = r12;
        r03.resultHeight = r12;
        VideoEditedInfo r04 = this.videoEditedInfo;
        r04.originalPath = r6.getAbsolutePath();
        setupVideoPlayer(r6);
        VideoEditedInfo r05 = this.videoEditedInfo;
        r05.estimatedDuration = this.recordedTime;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.audioDidSent, new Object[]{Integer.valueOf(this.recordingGuid), this.videoEditedInfo, r6.getAbsolutePath(), this.keyframeThumbs});
        return;
    }

    @Deprecated
    private boolean initCamera() {
        if (this.useCamera2 == false) goto L5;
        return true;
    L5:
        ArrayList<CameraInfo> r0 = CameraController.getInstance().getCameras();
        if (r0 != null) goto L8;
        return false;
    L8:
        CameraInfo r3 = null;
        int r4 = 0;
    L10:
        if (r4 >= r0.size()) goto L25;
        CameraInfo r5 = r0.get(r4);
        if (r5.isFrontface() == true) goto L15;
        r3 = r5;
    L15:
        if (this.isFrontface == false) goto L19;
        if (r5.isFrontface() == false) goto L19;
    L22:
        this.selectedCamera = r5;
    L19:
        if (this.isFrontface == true) goto L23;
        if (r5.isFrontface() == false) goto L22;
    L23:
        r4 = r4 + 1;
        r3 = r5;
    L25:
        if (this.selectedCamera != null) goto L27;
        this.selectedCamera = r3;
    L27:
        CameraInfo r02 = this.selectedCamera;
        if (r02 != null) goto L30;
        return false;
    L30:
        ArrayList<org.telegram.messenger.camera.Size> r03 = r02.getPreviewSizes();
        ArrayList<org.telegram.messenger.camera.Size> r32 = this.selectedCamera.getPictureSizes();
        this.previewSize[0] = chooseOptimalSize(r03);
        org.telegram.messenger.camera.Size r42 = chooseOptimalSize(r32);
        this.pictureSize = r42;
        if (this.previewSize[0].mWidth == r42.mWidth) goto L68;
        int r43 = r03.size() - 1;
        boolean r52 = false;
    L33:
        if (r43 < 0) goto L49;
        org.telegram.messenger.camera.Size r6 = r03.get(r43);
        int r7 = r32.size() - 1;
    L35:
        if (r7 < 0) goto L46;
        org.telegram.messenger.camera.Size r8 = r32.get(r7);
        int r9 = r6.mWidth;
        org.telegram.messenger.camera.Size r10 = this.pictureSize;
        if (r9 < r10.mWidth) goto L45;
        int r11 = r6.mHeight;
        if (r11 < r10.mHeight) goto L45;
        if (r9 != r8.mWidth) goto L45;
        if (r11 != r8.mHeight) goto L45;
        this.previewSize[0] = r6;
        this.pictureSize = r8;
        r52 = true;
    L45:
        r7 = r7 - 1;
    L46:
        if (r52 == true) goto L49;
        r43 = r43 - 1;
    L49:
        if (r52 == true) goto L68;
        int r44 = r03.size() - 1;
    L51:
        if (r44 < 0) goto L68;
        org.telegram.messenger.camera.Size r62 = r03.get(r44);
        int r72 = r32.size() - 1;
    L53:
        if (r72 < 0) goto L64;
        org.telegram.messenger.camera.Size r82 = r32.get(r72);
        int r92 = r62.mWidth;
        if (r92 < 360) goto L63;
        int r112 = r62.mHeight;
        if (r112 < 360) goto L63;
        if (r92 != r82.mWidth) goto L63;
        if (r112 != r82.mHeight) goto L63;
        this.previewSize[0] = r62;
        this.pictureSize = r82;
        r52 = true;
    L63:
        r72 = r72 - 1;
    L64:
        if (r52 == true) goto L68;
        r44 = r44 - 1;
    L68:
        if (BuildVars.LOGS_ENABLED == false) goto L70;
        FileLog.d("InstantCamera preview w = " + this.previewSize[0].mWidth + " h = " + this.previewSize[0].mHeight);
    L70:
        return true;
    }

    private boolean isCameraSessionInitiated() {
        if (this.useCamera2 == false) goto L10;
        Camera2Session r3 = this.camera2SessionCurrent;
        if (r3 != null) goto L7;
    L9:
        return false;
    L7:
        if (r3.isInitiated() == false) goto L9;
        return true;
    L10:
        CameraSession r32 = this.cameraSession;
        if (r32 != null) goto L13;
    L15:
        return false;
    L13:
        if (r32.isInitied() == false) goto L15;
        return true;
    }

    private /* synthetic */ void lambda$adjustZoom$24(ValueAnimator r2) {
        if (this.cameraXSession == null) goto L6;
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.zoomSlider.setCameraXZoomRatio(r22);
        this.cameraZoom = this.cameraXSession.getLinearZoom();
        return;
    }

    private /* synthetic */ void lambda$adjustZoom$25(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.cameraZoom = r22;
        if (this.useCamera2 == false) goto L7;
        Camera2Session r0 = this.camera2SessionCurrent;
        if (r0 == null) goto L10;
        r0.setZoom(r22);
    L10:
        this.zoomSlider.syncZoom(this.cameraZoom);
        return;
    L7:
        CameraSession r02 = this.cameraSession;
        if (r02 == null) goto L10;
        r02.setZoom(r22);
        goto L10
    }

    private /* synthetic */ void lambda$bindCamera2ZoomSlider$11(Camera2Session r3) {
        if (this.camera2SessionCurrent == r3) goto L5;
        return;
    L5:
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_2) goto L9;
        this.cameraZoom = r3.getZoom();
        this.zoomSlider.bindSession(r3);
        return;
    }

    private /* synthetic */ void lambda$createCamera$13(CameraSession r9) {
        if (this.cameraSession != r9) goto L38;
        updateFlash();
        boolean r3 = false;
        Camera.Size r4 = r9.getCurrentPreviewSize();     // Catch: Exception -> L10
        if (r4.width == this.previewSize[0].getWidth()) goto L8;
    L12:
        this.previewSize[0] = new org.telegram.messenger.camera.Size(r4.width, r4.height);     // Catch: Exception -> L10
        FileLog.d("InstantCamera change preview size to w = " + this.previewSize[0].getWidth() + " h = " + this.previewSize[0].getHeight());     // Catch: Exception -> L10
    L34:
        Camera.Size r2 = r9.getCurrentPictureSize();     // Catch: Exception -> L20
        if (r2.width == this.pictureSize.getWidth()) goto L18;
    L22:
        this.pictureSize = new org.telegram.messenger.camera.Size(r2.width, r2.height);     // Catch: Exception -> L20
        FileLog.d("InstantCamera change picture size to w = " + this.pictureSize.getWidth() + " h = " + this.pictureSize.getHeight());     // Catch: Exception -> L20
        r3 = true;
    L26:
        if (BuildVars.LOGS_ENABLED == false) goto L28;
        FileLog.d("InstantCamera camera initied");
    L28:
        r9.setInitied();
        this.zoomSlider.bindSession(r9, this.cameraZoom);
        if (r3 == false) goto L39;
        CameraGLThread r8 = this.cameraThread;
        if (r8 == null) goto L40;
        r8.reinitForNewCamera();
        return;
    L40:
        return;
    L39:
        return;
    L18:
        if (r2.height == this.pictureSize.getHeight()) goto L26;
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L26
    L8:
        if (r4.height == this.previewSize[0].getHeight()) goto L34;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L34
    }

    private /* synthetic */ void lambda$createCamera$14(CameraSession r2) {
        CameraGLThread r0 = this.cameraThread;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.cameraSession != r2) goto L9;
        r0.setCurrentSession(r2);
        return;
    }

    private /* synthetic */ void lambda$createCamera$15(int r1, int r2, int r3) {
        CameraGLThread r0 = this.cameraThread;
        if (r0 == null) goto L6;
        r0.setCameraXPreviewSize(r1, r2, r3);
        return;
    }

    private /* synthetic */ void lambda$createCamera$16(CameraXSession r3) {
        if (this.cameraXSession == r3) goto L5;
        return;
    L5:
        boolean r0 = r3.isDualMode();
        if (r0 == false) goto L8;
    L14:
        this.bothCameras = r0;
        CameraGLThread r02 = this.cameraThread;
        if (r02 == null) goto L17;
        r02.setOrientation();
    L17:
        this.zoomSlider.bindSession(r3);
        return;
    L8:
        if (this.bothCameras == false) goto L14;
        if (this.surfaceIndex == 0) goto L14;
        CameraGLThread r1 = this.cameraThread;
        if (r1 == null) goto L14;
        r1.flipSurfaces();
        goto L14
    }

    private /* synthetic */ void lambda$createCamera$17(int r9, SurfaceTexture r10) {
        if (this.cameraThread != null) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d("InstantCamera create camera session " + r9);
    L9:
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L11;
        Preview.SurfaceProvider r102 = CameraXSession.createSurfaceProvider(getContext(), r10, new InstantCameraView$$ExternalSyntheticLambda25(this, r9));
        if (r9 != 0) goto L31;
        CameraXSession r92 = new CameraXSession(this.camLifecycle, r102);
        this.cameraXSession = r92;
        r92.initCamera(getContext(), this.isFrontface, this.bothCameras, new InstantCameraView$$ExternalSyntheticLambda26(this, r92));
        updateFlash();
        return;
    L31:
        if (this.bothCameras == false) goto L37;
        CameraXSession r8 = this.cameraXSession;
        if (r8 == null) goto L38;
        r8.setSecondSurfaceProvider(r102);
        return;
    L38:
        return;
    L37:
        return;
    L11:
        if (this.useCamera2 == true) goto L13;
        if (r9 == 1) goto L41;
        r10.setDefaultBufferSize(this.previewSize[0].getWidth(), this.previewSize[0].getHeight());
        CameraSession r2 = new CameraSession(this.selectedCamera, this.previewSize[0], this.pictureSize, 256, true);
        this.cameraSession = r2;
        updateFlash();
        this.cameraThread.setCurrentSession(r2);
        CameraController.getInstance().openRound(r2, r10, new InstantCameraView$$ExternalSyntheticLambda23(this, r2), new InstantCameraView$$ExternalSyntheticLambda24(this, r2));
        return;
    L41:
        return;
    L13:
        if (this.bothCameras == false) goto L18;
        Camera2Session r82 = this.camera2Sessions[r9];
        if (r82 == null) goto L36;
        r82.open(r10);
        return;
    L36:
        return;
    L18:
        if (r9 == 1) goto L40;
        this.cameraThread.setCurrentSession(this.camera2SessionCurrent);
        this.camera2SessionCurrent.open(r10);
        return;
    }

    private /* synthetic */ void lambda$finishZoom$22(ValueAnimator r2) {
        if (this.cameraXSession == null) goto L6;
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.zoomSlider.setCameraXZoomRatio(r22);
        this.cameraZoom = this.cameraXSession.getLinearZoom();
        return;
    }

    private /* synthetic */ void lambda$finishZoom$23(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.cameraZoom = r22;
        if (this.useCamera2 == false) goto L7;
        Camera2Session r0 = this.camera2SessionCurrent;
        if (r0 == null) goto L10;
        r0.setZoom(r22);
    L10:
        this.zoomSlider.syncZoom(this.cameraZoom);
        return;
    L7:
        CameraSession r02 = this.cameraSession;
        if (r02 == null) goto L10;
        r02.setZoom(r22);
        goto L10
    }

    private /* synthetic */ void lambda$handleEncoderFinished$20(File r13, SendOptions r14, VideoEditedInfo r15, boolean r16, int r17, int r18) {
        MediaController.PhotoEntry r0 = new MediaController.PhotoEntry(0, 0, 0, r13.getAbsolutePath(), 0, true, 0, 0, 0);
        if (r14 == null) goto L5;
        r0.ttl = r14.ttl;
        r0.effectId = r14.effectId;
    L5:
        Delegate r02 = this.delegate;
        if (r16 == true) goto L12;
        if (r14 == null) goto L12;
        if (r14.notify == true) goto L12;
        boolean r3 = false;
    L13:
        if (r17 == 0) goto L15;
        int r4 = r17;
    L18:
        if (r18 == 0) goto L20;
        int r5 = r18;
    L23:
        if (r14 == null) goto L26;
        long r6 = r14.stars;
    L27:
        r02.sendMedia(r0, r15, r3, r4, r5, false, r6);
        startAnimation(false, false);
        return;
    L26:
        r6 = 0;
        goto L27
    L20:
        if (r14 == null) goto L22;
        r5 = r14.scheduleRepeatPeriod;
        goto L23
    L22:
        r5 = 0;
        goto L23
    L15:
        if (r14 == null) goto L17;
        r4 = r14.scheduleDate;
        goto L18
    L17:
        r4 = 0;
    L12:
        r3 = true;
        goto L13
    }

    private /* synthetic */ void lambda$handleEncoderFinished$21() {
        startAnimation(false, false);
    }

    private /* synthetic */ boolean lambda$new$0(View r3, MotionEvent r4) {
        if (r4.getAction() == 0) goto L5;
        return false;
    L5:
        if (this.cameraXSession == null) goto L10;
        this.cameraXSession.focusToPoint(r4.getX(), r4.getY(), r3.getWidth(), r3.getHeight());
        return false;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$new$1(boolean r3) {
        if (this.bothCameras == true) goto L5;
    L8:
        if (r3 == false) goto L11;
        switchCameraX();
        return;
    L11:
        return;
    L5:
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_X) goto L8;
        switchCamera();
    }

    private /* synthetic */ void lambda$new$2(View r6) {
        if (isCameraReady() == false) goto L29;
        CameraType r62 = ExteraConfig.getCameraType();
        CameraType r0 = CameraType.CAMERA_X;
        if (r62 == r0) goto L8;
    L12:
        boolean r63 = false;
    L14:
        if (ExteraConfig.getCameraType() != r0) goto L16;
        if (r63 == true) goto L20;
        switchCameraX();
    L20:
        RLottieDrawable r3 = this.switchCameraDrawable;
        if (r3 == null) goto L24;
        r3.setCurrentFrame(0);
        this.switchCameraDrawable.start();
    L24:
        if (ExteraConfig.getCameraType() != r0) goto L27;
        if (r63 == true) goto L27;
        return;
    L27:
        this.flipAnimationInProgress = true;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r02.setDuration(450);
        r02.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        boolean[] r1 = new boolean[1];
        InstantCameraView$$ExternalSyntheticLambda18 r2 = new InstantCameraView$$ExternalSyntheticLambda18(this, r63);
        this.cameraContainer.setCameraDistance(r6.getMeasuredHeight() * 8.0f);
        this.textureOverlayView.setCameraDistance(r6.getMeasuredHeight() * 8.0f);
        r02.addUpdateListener(new AnonymousClass5(this, r1, r2));
        r02.addListener(new AnonymousClass6(this, r1, r2));
        r02.start();
        return;
    L16:
        if (this.bothCameras == true) goto L20;
        switchCamera();
        goto L20
    L8:
        if (this.cameraXSession == null) goto L12;
        if (this.cameraXSession.isDualMode() == false) goto L12;
        r63 = true;
        goto L14
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        this.flashing = !this.flashing;
        updateFlash();
    }

    private /* synthetic */ void lambda$new$4(Float r2) {
        ExteraConfig.setFlashWarmth(r2.floatValue());
        this.flashViews.setWarmth(r2.floatValue());
    }

    private /* synthetic */ void lambda$new$5(Float r2) {
        ExteraConfig.setFlashIntensity(r2.floatValue());
        this.flashViews.setIntensity(r2.floatValue());
    }

    private /* synthetic */ void lambda$new$6(boolean r1) {
        if (r1 == true) goto L5;
        this.flashing = false;
        updateFlash();
        return;
    }

    private /* synthetic */ boolean lambda$new$7(Theme.ResourcesProvider r5, View r6) {
        if (this.isFrontface == true) goto L5;
        return false;
    L5:
        if (isCameraReady() == false) goto L14;
        boolean r62 = this.flashing;
        if (r62 == true) goto L10;
        this.flashing = true;
        updateFlash();
    L10:
        this.itemOptions = ItemOptions.makeOptions(this, r5, this.flashButton).addView(new SliderView(getContext(), 1).setValue(ExteraConfig.getFlashWarmth()).setOnValueChange(new InstantCameraView$$ExternalSyntheticLambda19(this))).addSpaceGap().addView(new SliderView(getContext(), 2).setMinMax(0.5f, 1.0f).setValue(ExteraConfig.getFlashIntensity()).setOnValueChange(new InstantCameraView$$ExternalSyntheticLambda20(this))).setOnDismiss(new InstantCameraView$$ExternalSyntheticLambda21(this, r62)).setDimAlpha(50).setGravity(5).translate(AndroidUtilities.dp(46.0f), AndroidUtilities.dp(4.0f)).setBackgroundColor(-1155851493).show();
        return true;
    L14:
        return false;
    }

    private /* synthetic */ void lambda$new$8(float r1, boolean r2) {
        if (r2 == false) goto L4;
        cancelZoomAnimations();
    L4:
        this.cameraZoom = r1;
    }

    private /* synthetic */ void lambda$requestStopRecording$18(SendOptions r1) {
        sendMediaBeforeDone(r1);
    }

    private /* synthetic */ void lambda$startAnimation$9(boolean r2, ValueAnimator r3) {
        if (r2 == false) goto L4;
        float r22 = 0.0f;
    L5:
        this.animationTranslationY = r22;
        updateTranslationY();
        return;
    L4:
        r22 = (getMeasuredHeight() / 2.0f) * ((Float) r3.getAnimatedValue()).floatValue();
        goto L5
    }

    private int loadShader(int r2, String r3) {
        int r1 = GLES20.glCreateShader(r2);
        GLES20.glShaderSource(r1, r3);
        GLES20.glCompileShader(r1);
        int[] r22 = new int[1];
        GLES20.glGetShaderiv(r1, 35713, r22, 0);
        if (r22[0] == 0) goto L5;
        return r1;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.e(GLES20.glGetShaderInfoLog(r1));
    L7:
        GLES20.glDeleteShader(r1);
        return 0;
    }

    private void populateCameraDebugOverlay(DebugOverlayView.ContentBuilder r9) {
        DebugOverlayView.ContentBuilder r0 = r9.title("InstantCamera").kv("front", Boolean.valueOf(this.isFrontface)).kv("recording", Boolean.valueOf(this.recording)).kv("ready", Boolean.valueOf(this.cameraReady)).kv("dual", Boolean.valueOf(this.bothCameras)).kv("surface", Integer.valueOf(this.surfaceIndex)).kv("flash", Boolean.valueOf(this.flashing)).kv("frontFlash", Boolean.valueOf(this.frontFlashing)).kv("texture", Boolean.valueOf(this.cameraTextureAvailable));
        StringBuilder r1 = new StringBuilder("preview0=");
        boolean r3 = false;
        r1.append(CameraDebugUtils.formatCameraSize(this.previewSize[0]));
        r0.line(r1.toString()).line("preview1=" + CameraDebugUtils.formatCameraSize(this.previewSize[1])).line("zoom.stops=" + CameraDebugUtils.formatZoomStops(this.zoomSlider.getToggleStops())).line("zoom.lenses=" + CameraDebugUtils.formatZoomStops(this.zoomSlider.getOpticalZoomRatios()));
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L11;
        r9.section("CameraX");
        if (this.cameraXSession != null) goto L8;
        r9.line("session=null");
        return;
    L8:
        DebugOverlayView.ContentBuilder r92 = r9.kv("cx.init", Boolean.valueOf(this.cameraXSession.isInitiated())).kv("cx.ready", Boolean.valueOf(this.cameraXSession.isReady())).kv("cx.dual", Boolean.valueOf(this.cameraXSession.isDualMode())).kv("cx.front", Boolean.valueOf(this.cameraXSession.isFrontface())).line("cx.zoom=" + CameraDebugUtils.safeCameraXZoomRatio(this.cameraXSession) + " [" + CameraDebugUtils.safeCameraXMinZoomRatio(this.cameraXSession) + ".." + CameraDebugUtils.safeCameraXMaxZoomRatio(this.cameraXSession) + "]");
        StringBuilder r02 = new StringBuilder("cx.fpsRanges=");
        r02.append(CameraDebugUtils.getCameraXSupportedFpsRanges(this.cameraXSession));
        DebugOverlayView.ContentBuilder r93 = r92.line(r02.toString());
        StringBuilder r03 = new StringBuilder("bound=");
        r03.append(CameraDebugUtils.getCameraXBoundCameraList(this.cameraXSession));
        r93.line(r03.toString()).line("avail=" + CameraDebugUtils.getCameraXAvailableCameraList(this.cameraXSession)).line("phys=" + CameraDebugUtils.getCameraXPhysicalCameraList(this.cameraXSession));
        return;
    L11:
        if (this.useCamera2 == false) goto L18;
        r9.section("Camera2");
        Camera2Session r04 = this.camera2SessionCurrent;
        if (r04 != null) goto L16;
        r9.line("session=null");
        return;
    L16:
        DebugOverlayView.ContentBuilder r94 = r9.kv("c2.init", Boolean.valueOf(r04.isInitiated())).kv("c2.flash", Boolean.valueOf(this.camera2SessionCurrent.getFlash())).kv("c2.both", Boolean.valueOf(this.bothCameras)).line("c2.zoom=" + this.camera2SessionCurrent.getZoom() + " [" + this.camera2SessionCurrent.getMinZoom() + ".." + this.camera2SessionCurrent.getMaxZoom() + "]");
        StringBuilder r05 = new StringBuilder("c2.fpsRanges=");
        r05.append(CameraDebugUtils.getCamera2SupportedFpsRanges(this.camera2SessionCurrent));
        DebugOverlayView.ContentBuilder r95 = r94.line(r05.toString());
        StringBuilder r06 = new StringBuilder("ids=");
        r06.append(CameraDebugUtils.getCamera2CameraList(getContext()));
        r95.line(r06.toString());
        return;
    L18:
        DebugOverlayView.ContentBuilder r96 = r9.section("Camera1");
        CameraSession r07 = this.cameraSession;
        if (r07 != null) goto L21;
    L23:
        r96.kv("c1.init", Boolean.valueOf(r3)).kv("c1.zoom", Float.valueOf(this.cameraZoom)).line("c1.fpsRanges=" + CameraDebugUtils.getLegacySupportedFpsRanges(this.cameraSession)).line("ids=" + CameraDebugUtils.getLegacyCameraList());
        return;
    L21:
        if (r07.isInitied() == false) goto L23;
        r3 = true;
        goto L23
    }

    private void releaseCameraXSession() {
        if (this.cameraXSession != null) goto L5;
        return;
    L5:
        this.zoomSlider.unbindSession();
        this.cameraXSession.closeCamera();     // Catch: Exception -> L8
    L10:
        this.cameraXSession = null;
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    private void requestStopRecording(int r3, SendOptions r4) {
        RoundVideoEncoder r0 = this.videoEncoder;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.encoderFinishRequested == true) goto L10;
        this.encoderFinishRequested = true;
        this.encoderSend = r3;
        this.encoderSendOptions = r4;
        if (r3 != 1) goto L10;
        AndroidUtilities.runOnUIThread(new InstantCameraView$$ExternalSyntheticLambda3(this, r4));
    L10:
        if (r3 != 0) goto L12;
        r0.cancel();
    L13:
        AndroidUtilities.runOnUIThread(new InstantCameraView$$ExternalSyntheticLambda4());
        return;
    L12:
        r0.stop();
        goto L13
    }

    private int resolveEncoderFrameRate() {
        if (ExteraConfig.getExtendedFramesPerSecond() == true) goto L6;
        return 30;
    L6:
        if (this.useCamera2 == false) goto L13;
        Camera2Session r3 = this.camera2SessionCurrent;
        if (r3 != null) goto L10;
        return 30;
    L10:
        return r3.getRecordingFrameRate();
    L13:
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L18;
        CameraXSession r32 = this.cameraXSession;
        if (r32 == null) goto L18;
        return r32.getRecordingFrameRate();
    L18:
        return 30;
    }

    private void saveLastCameraBitmap() {
        Bitmap r0 = this.textureView.getBitmap();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getPixel(0, 0) == 0) goto L9;
        Bitmap r02 = Bitmap.createScaledBitmap(r0, 50, 50, true);
        this.lastBitmap = r02;
        Utilities.blurBitmap(r02, 7);
        Utilities.globalQueue.postRunnable(new InstantCameraView$$ExternalSyntheticLambda5(r02));
        return;
    }

    private void sendMediaBeforeDone(SendOptions r18) {
        File r2 = this.encoderFile;
        if (this.sentMedia == true) goto L34;
        if (r2 == null) goto L35;
        VideoEditedInfo r3 = this.videoEditedInfo;
        if (r3 == null) goto L10;
        if (r3.needConvert() == false) goto L10;
        return;
    L10:
        if (this.delegate.isInScheduleMode() == true) goto L37;
        this.sentMedia = true;
        VideoEditedInfo r4 = new VideoEditedInfo();
        this.videoEditedInfo = r4;
        r4.startTime = -1;
        r4.endTime = -1;
        r4.estimatedSize = Math.max(1, this.size);
        VideoEditedInfo r42 = this.videoEditedInfo;
        r42.roundVideo = true;
        r42.file = this.file;
        r42.encryptedFile = this.encryptedFile;
        r42.key = this.key;
        r42.iv = this.iv;
        r42.framerate = this.encoderFrameRate;
        int r5 = SystemUtils.getRoundVideoResolution();
        r42.originalWidth = r5;
        r42.resultWidth = r5;
        VideoEditedInfo r43 = this.videoEditedInfo;
        int r52 = SystemUtils.getRoundVideoResolution();
        r43.originalHeight = r52;
        r43.resultHeight = r52;
        this.videoEditedInfo.originalPath = r2.getAbsolutePath();
        VideoEditedInfo r44 = this.videoEditedInfo;
        r44.notReadyYet = true;
        r44.thumb = this.firstFrameThumb;
        r44.estimatedDuration = this.recordedTime;
        this.firstFrameThumb = null;
        MediaController.PhotoEntry r53 = new MediaController.PhotoEntry(0, 0, 0, r2.getAbsolutePath(), 0, true, 0, 0, 0);
        if (r18 == null) goto L15;
        r53.ttl = r18.ttl;
        r53.effectId = r18.effectId;
    L15:
        Delegate r54 = this.delegate;
        VideoEditedInfo r7 = this.videoEditedInfo;
        int r0 = 0;
        if (r18 != null) goto L18;
    L21:
        boolean r8 = true;
    L22:
        if (r18 == null) goto L24;
        int r9 = r18.scheduleDate;
    L25:
        if (r18 == null) goto L27;
        r0 = r18.scheduleRepeatPeriod;
    L27:
        int r10 = r0;
        if (r18 == null) goto L31;
        long r02 = r18.stars;
    L32:
        r54.sendMedia(r53, r7, r8, r9, r10, false, r02);
        return;
    L31:
        r02 = 0;
        goto L32
    L24:
        r9 = 0;
        goto L25
    L18:
        if (r18.notify == true) goto L21;
        r8 = false;
        goto L22
    L37:
        return;
    L35:
        return;
    }

    private void setupVideoPlayer(File r9) {
        VideoPlayer r0 = new VideoPlayer();
        this.videoPlayer = r0;
        r0.setDelegate(new AnonymousClass13(this));
        releaseCameraXSession();
        this.videoPlayer.setTextureView(this.textureView);
        this.videoPlayer.preparePlayer(Uri.fromFile(r9), "other");
        this.videoPlayer.play();
        this.videoPlayer.setMute(true);
        startProgressTimer();
        AnimatorSet r92 = new AnimatorSet();
        Property r5 = View.ALPHA;
        r92.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.buttonsLayout, r5, new float[]{0.0f}), ObjectAnimator.ofFloat(this.zoomSlider, InstantCameraZoomSlider.OPEN_ALPHA, new float[]{0.0f}), ObjectAnimator.ofInt(this.paint, AnimationProperties.PAINT_ALPHA, new int[]{0}), ObjectAnimator.ofFloat(this.muteImageView, r5, new float[]{1.0f})});
        r92.setDuration(180);
        r92.setInterpolator(new DecelerateInterpolator());
        r92.start();
    }

    private void startProgressTimer() {
        Timer r0 = this.progressTimer;
        if (r0 != null) goto L10;
    L8:
        Timer r1 = new Timer();
        this.progressTimer = r1;
        r1.schedule(new AnonymousClass11(this), 0, 17);
        return;
    L10:
        r0.cancel();     // Catch: Exception -> L6
        this.progressTimer = null;     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private void stopProgressTimer() {
        Timer r0 = this.progressTimer;
        if (r0 == null) goto L11;
        r0.cancel();     // Catch: Exception -> L6
        this.progressTimer = null;     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private void switchCamera() {
        if (this.cameraThread == null) goto L45;
        cancelZoomAnimations();
        this.zoomSlider.beginCameraSwitch();
        if (this.useCamera2 == true) goto L8;
    L9:
        saveLastCameraBitmap();
        Bitmap r0 = this.lastBitmap;
        if (r0 == null) goto L12;
        this.needDrawFlickerStub = false;
        this.textureOverlayView.setImageBitmap(r0);
        this.textureOverlayView.setAlpha(1.0f);
    L12:
        this.isFrontface = !this.isFrontface;
        if (ExteraConfig.getRememberLastUsedCamera() == true) goto L15;
    L21:
        updateFlash();
        if (this.useCamera2 == true) goto L24;
        CameraSession r02 = this.cameraSession;
        if (r02 == null) goto L37;
        r02.destroy();
        CameraController.getInstance().close(this.cameraSession, null, null);
        this.cameraSession = null;
    L37:
        initCamera();
        this.cameraReady = false;
        if (this.useCamera2 == false) goto L42;
        Camera2Session r03 = this.camera2SessionCurrent;
        if (r03 == null) goto L42;
        float r04 = r03.getZoom();
    L43:
        this.cameraZoom = r04;
        this.cameraThread.reinitForNewCamera();
        return;
    L42:
        r04 = 0.0f;
        goto L43
    L24:
        if (this.bothCameras == false) goto L27;
        Camera2Session r05 = this.camera2Sessions[!this.isFrontface ? 1 : 0];
        this.camera2SessionCurrent = r05;
        bindCamera2ZoomSlider(r05);
        this.cameraThread.flipSurfaces();
        return;
    L27:
        Camera2Session r06 = this.camera2SessionCurrent;
        if (r06 == null) goto L30;
        r06.destroy(false);
        this.camera2SessionCurrent = null;
        this.camera2Sessions[this.isFrontface ? 1 : 0] = null;
    L30:
        Camera2Session[] r07 = this.camera2Sessions;
        boolean r3 = this.isFrontface;
        int r4 = !r3 ? 1 : 0;
        Camera2Session r32 = Camera2Session.create(r3, SystemUtils.getRoundVideoResolution(), SystemUtils.getRoundVideoResolution());
        r07[r4] = r32;
        this.camera2SessionCurrent = r32;
        if (r32 != null) goto L33;
        return;
    L33:
        r32.setRecordingVideo(true);
        this.previewSize[0] = new org.telegram.messenger.camera.Size(this.camera2SessionCurrent.getPreviewWidth(), this.camera2SessionCurrent.getPreviewHeight());
        this.cameraThread.setCurrentSession(this.camera2SessionCurrent);
        bindCamera2ZoomSlider(this.camera2SessionCurrent);
        goto L37
    L15:
        if (ExteraConfig.getVideoMessagesCamera() == VideoMessagesCamera.ASK) goto L21;
        if (this.isFrontface == false) goto L19;
        VideoMessagesCamera r08 = VideoMessagesCamera.FRONT;
    L20:
        ExteraConfig.setVideoMessagesCamera(r08);
        goto L21
    L19:
        r08 = VideoMessagesCamera.REAR;
        goto L20
    L8:
        if (this.bothCameras == true) goto L12;
    }

    private void switchCameraX() {
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L36;
        cancelZoomAnimations();
        if (this.cameraXSession != null) goto L8;
    L10:
        boolean r0 = false;
    L11:
        this.isFrontface = true ^ this.isFrontface;
        if (ExteraConfig.getRememberLastUsedCamera() == true) goto L14;
    L20:
        updateFlash();
        if (r0 == false) goto L29;
        CameraGLThread r02 = this.cameraThread;
        if (r02 == null) goto L26;
        r02.flipSurfaces();
    L26:
        if (this.cameraXSession == null) goto L35;
        this.cameraXSession.switchCamera();
        this.zoomSlider.bindSession(this.cameraXSession);
        return;
    L35:
        return;
    L29:
        this.cameraReady = false;
        this.cameraZoom = 0.0f;
        if (this.cameraXSession == null) goto L33;
        this.cameraXSession.switchCamera();
        this.zoomSlider.bindSession(this.cameraXSession);
        return;
    L33:
        this.cameraThread.reinitForNewCamera();
        return;
    L14:
        if (ExteraConfig.getVideoMessagesCamera() == VideoMessagesCamera.ASK) goto L20;
        if (this.isFrontface == false) goto L18;
        VideoMessagesCamera r2 = VideoMessagesCamera.FRONT;
    L19:
        ExteraConfig.setVideoMessagesCamera(r2);
        goto L20
    L18:
        r2 = VideoMessagesCamera.REAR;
        goto L19
    L8:
        if (this.cameraXSession.isDualMode() == false) goto L10;
        r0 = true;
        goto L11
    }

    private void updateFlash() {
        if (this.flashing == true) goto L5;
    L9:
        boolean r0 = false;
    L11:
        if (this.frontFlashing == r0) goto L17;
        this.frontFlashing = r0;
        FlashViews r3 = this.flashViews;
        if (r0 == false) goto L15;
        r3.flashIn(null);
        goto L17
    L15:
        r3.flashOut();
    L17:
        if (this.flashing == true) goto L19;
    L21:
        boolean r02 = false;
    L23:
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_X) goto L43;
        if (this.useCamera2 == false) goto L34;
        Camera2Session r32 = this.camera2SessionCurrent;
        if (r32 == null) goto L48;
        if (r02 == true) goto L30;
    L32:
        boolean r03 = false;
    L33:
        r32.setFlash(r03);
        goto L48
    L30:
        if (this.isFrontface == true) goto L32;
        r03 = true;
    L48:
        if (this.flashButton == null) goto L75;
        Boolean r04 = this.wasFlashing;
        if (r04 != null) goto L52;
    L53:
        FlashViews.ImageViewInvertable r05 = this.flashButton;
        if (this.flashing == false) goto L56;
        int r33 = R.string.AccDescrCameraFlashOff;
    L57:
        r05.setContentDescription(LocaleController.getString(r33));
        if (this.flashing == true) goto L67;
        if (this.flashOnDrawable != null) goto L62;
        int r34 = R.raw.roundcamera_flash_on;
        int r4 = this.buttonsSizePx;
        RLottieDrawable r06 = new RLottieDrawable(r34, r4, r4);
        this.flashOnDrawable = r06;
        r06.setCallback(this.flashButton);
    L62:
        this.flashButton.setImageDrawable(this.flashOnDrawable);
        Boolean r07 = this.wasFlashing;
        RLottieDrawable r35 = this.flashOnDrawable;
        if (r07 != null) goto L65;
        r35.setCurrentFrame(r35.getFramesCount() - 1);
    L73:
        this.wasFlashing = Boolean.valueOf(this.flashing);
        return;
    L65:
        r35.setCurrentFrame(0);
        this.flashOnDrawable.start();
        goto L73
    L67:
        if (this.flashOffDrawable != null) goto L69;
        int r36 = R.raw.roundcamera_flash_off;
        int r42 = this.buttonsSizePx;
        RLottieDrawable r08 = new RLottieDrawable(r36, r42, r42);
        this.flashOffDrawable = r08;
        r08.setCallback(this.flashButton);
    L69:
        this.flashButton.setImageDrawable(this.flashOffDrawable);
        Boolean r09 = this.wasFlashing;
        RLottieDrawable r37 = this.flashOffDrawable;
        if (r09 != null) goto L72;
        r37.setCurrentFrame(r37.getFramesCount() - 1);
        goto L73
    L72:
        r37.setCurrentFrame(0);
        this.flashOffDrawable.start();
        goto L73
    L56:
        r33 = R.string.AccDescrCameraFlashOn;
        goto L57
    L52:
        if (r04.booleanValue() != this.flashing) goto L53;
        return;
    L75:
        return;
    L34:
        CameraSession r38 = this.cameraSession;
        if (r38 == null) goto L48;
        if (r02 == true) goto L38;
    L40:
        boolean r010 = false;
    L41:
        r38.setTorchEnabled(r010);
        goto L48
    L38:
        if (this.isFrontface == true) goto L40;
        r010 = true;
        goto L41
    L43:
        if (isCameraReady() == false) goto L48;
        if (this.cameraXSession == null) goto L48;
        this.cameraXSession.setTorchEnabled(r02);
        goto L48
    L19:
        if (this.recording == false) goto L21;
        r02 = true;
        goto L23
    L5:
        if (this.recording == false) goto L9;
        if (this.isFrontface == false) goto L9;
        r0 = true;
        goto L11
    }

    private void updateTranslationY() {
        this.textureOverlayView.setTranslationY(this.animationTranslationY + this.panTranslationY);
        this.cameraContainer.setTranslationY(this.animationTranslationY + this.panTranslationY);
        this.zoomSlider.setBaseTranslationY(this.animationTranslationY + this.panTranslationY);
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void cancel(boolean r13) {
        stopProgressTimer();
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L6;
        r0.releasePlayer(true);
        this.videoPlayer = null;
    L6:
        if (this.textureView != null) goto L8;
        return;
    L8:
        this.cancelled = true;
        this.recording = false;
        this.flashing = false;
        updateFlash();
        NotificationCenter r1 = NotificationCenter.getInstance(this.currentAccount);
        int r3 = NotificationCenter.recordStopped;
        Integer r4 = Integer.valueOf(this.recordingGuid);
        if (r13 == false) goto L11;
        int r132 = 0;
    L12:
        r1.postNotificationName(r3, new Object[]{r4, Integer.valueOf(r132)});
        if (this.cameraThread == null) goto L16;
        saveLastCameraBitmap();
        this.cameraThread.shutdown(0, true, 0, 0, 0, 0);
        this.cameraThread = null;
    L19:
        if (this.cameraFile != null) goto L21;
    L24:
        MediaController.getInstance().requestRecordAudioFocus(false);
        startAnimation(false, false);
        invalidate();
        return;
    L21:
        if (BuildVars.LOGS_ENABLED == false) goto L23;
        FileLog.e("delete camera file by cancel");
    L23:
        this.cameraFile.delete();
        AutoDeleteMediaTask.unlockFile(this.cameraFile);
        this.cameraFile = null;
        goto L24
    L16:
        if (this.videoEncoder == null) goto L19;
        requestStopRecording(0, new SendOptions(true, 0, 0, 0, 0, 0));
        goto L19
    L11:
        r132 = 6;
        goto L12
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void changeVideoPreviewState(int r3, float r4) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L17;
        if (r3 != 0) goto L9;
        startProgressTimer();
        this.videoPlayer.play();
        return;
    L9:
        if (r3 != 1) goto L13;
        stopProgressTimer();
        this.videoPlayer.pause();
        return;
    L13:
        if (r3 != 2) goto L16;
        r0.seekTo((long) (r4 * r0.getDuration()));
        return;
    L16:
        return;
    }

    public void destroy(boolean r5) {
        cancelZoomAnimations();
        this.zoomSlider.unbindSession();
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L5;
        releaseCameraXSession();
        return;
    L5:
        if (this.useCamera2 == false) goto L15;
        int r0 = 0;
    L7:
        Camera2Session[] r2 = this.camera2Sessions;
        if (r0 >= r2.length) goto L13;
        Camera2Session r22 = r2[r0];
        if (r22 == null) goto L12;
        r22.destroy(r5);
        this.camera2Sessions[r0] = null;
    L12:
        r0 = r0 + 1;
        goto L7
    L13:
        this.camera2SessionCurrent = null;
        return;
    L15:
        CameraSession r02 = this.cameraSession;
        if (r02 == null) goto L28;
        r02.destroy();
        CameraController r03 = CameraController.getInstance();
        CameraSession r23 = this.cameraSession;
        if (r5 == true) goto L20;
        CountDownLatch r52 = new CountDownLatch(1);
    L21:
        r03.close(r23, r52, null);
        this.cameraSession = null;
        return;
    L20:
        r52 = null;
        goto L21
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.fileUploaded) goto L12;
        String r12 = (String) r3[0];
        File r22 = this.cameraFile;
        if (r22 != null) goto L7;
        return;
    L7:
        if (r22.getAbsolutePath().equals(r12) == false) goto L14;
        this.file = (TLRPC.InputFile) r3[1];
        this.encryptedFile = (TLRPC.InputEncryptedFile) r3[2];
        this.size = ((Long) r3[5]).longValue();
        if (this.encryptedFile == null) goto L15;
        this.key = (byte[]) r3[3];
        this.iv = (byte[]) r3[4];
        return;
    L15:
        return;
    L14:
        return;
    }

    public void finishZoom() {
        this.zoomSlider.endPinchZoomGesture();
        if (this.finishZoomTransition == null) goto L5;
        return;
    L5:
        if (ExteraConfig.getStaticZoom() == false) goto L8;
        return;
    L8:
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_X) goto L10;
        float r0 = this.cameraZoom;
        float r1 = 0.0f;
        if (r0 > 0.0f) goto L25;
        return;
    L25:
        if (this.useCamera2 == false) goto L27;
        r1 = 1.0f;
    L27:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r0, r1});
        this.finishZoomTransition = r02;
        r02.addUpdateListener(new InstantCameraView$$ExternalSyntheticLambda11(this));
        this.finishZoomTransition.addListener(new AnonymousClass16(this));
        this.finishZoomTransition.setDuration(350);
        this.finishZoomTransition.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.finishZoomTransition.start();
        return;
    L10:
        if (this.cameraXSession == null) goto L30;
        float r03 = this.zoomSlider.getZoom();
        float r12 = Utilities.clamp(this.zoomSlider.getCameraXResetZoom(), this.zoomSlider.getMaximumZoom(), this.zoomSlider.getMinimumZoom());
        float r7 = this.zoomSlider.getDisplayOneZoom();
        if (r03 >= r7) goto L19;
        if (r12 < r7) goto L19;
        if (this.zoomSlider.getMinimumZoom() >= r7) goto L19;
        return;
    L19:
        if (Math.abs(r03 - r12) <= 0.001f) goto L31;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{r03, r12});
        this.finishZoomTransition = r04;
        r04.addUpdateListener(new InstantCameraView$$ExternalSyntheticLambda10(this));
        this.finishZoomTransition.addListener(new AnonymousClass15(this));
        this.finishZoomTransition.setDuration(350);
        this.finishZoomTransition.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.finishZoomTransition.start();
        return;
    L31:
        return;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public View getButtonsLayout() {
        return this.buttonsLayout;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public InstantViewCameraContainer getCameraContainer() {
        return this.cameraContainer;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public /* bridge */ /* synthetic */ InstantCameraViewBase.InstantViewCameraContainer getCameraContainer() {
        return getCameraContainer();
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public RectF getCameraRect() {
        this.cameraContainer.getLocationOnScreen(this.position);
        return new RectF(this.position[0], r1[1], r2 + this.cameraContainer.getWidth(), this.position[1] + this.cameraContainer.getHeight());
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public View getMuteImageView() {
        return this.muteImageView;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public Paint getPaint() {
        return this.paint;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public TextureView getTextureView() {
        return this.textureView;
    }

    public InstantCameraZoomSlider getZoomSlider() {
        return this.zoomSlider;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void hideCamera(boolean r2) {
        destroy(r2);
        this.cameraContainer.setTranslationX(0.0f);
        this.textureOverlayView.setTranslationX(0.0f);
        this.animationTranslationY = 0.0f;
        updateTranslationY();
        MediaController.getInstance().resumeByRewind();
        TextureView r22 = this.textureView;
        if (r22 == null) goto L7;
        ViewGroup r23 = (ViewGroup) r22.getParent();
        if (r23 == null) goto L7;
        r23.removeView(this.textureView);
    L7:
        this.textureView = null;
        this.cameraContainer.setImageReceiver(null);
    }

    public boolean isCameraReady() {
        if (ExteraConfig.getCameraType() == CameraType.CAMERA_X) goto L5;
        boolean r0 = isCameraSessionInitiated();
    L12:
        if (this.cameraReady == false) goto L17;
        if (r0 == false) goto L17;
        if (this.cameraThread == null) goto L17;
        return true;
    L17:
        return false;
    L5:
        if (this.cameraXSession != null) goto L7;
    L9:
        r0 = false;
        goto L12
    L7:
        if (this.cameraXSession.isReady() == false) goto L9;
        r0 = true;
        goto L12
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public boolean isPaused() {
        return !this.recording;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
        FlashViews r2 = this.flashViews;
        if (r2 == null) goto L6;
        r2.flashOut();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        float r0 = this.cameraContainer.getX();
        float r1 = this.cameraContainer.getY();
        this.rect.set(r0 - AndroidUtilities.dp(8.0f), r1 - AndroidUtilities.dp(8.0f), (r0 + this.cameraContainer.getMeasuredWidth()) + AndroidUtilities.dp(8.0f), (r1 + this.cameraContainer.getMeasuredHeight()) + AndroidUtilities.dp(8.0f));
        if (this.recording == false) goto L6;
        long r02 = (System.currentTimeMillis() - this.recordStartTime) + this.recordPlusTime;
        this.recordedTime = r02;
        this.progress = Math.min(1.0f, r02 / SystemUtils.getRoundVideoMaxDurationMs());
        invalidate();
    L6:
        if (this.progress == 0.0f) goto L12;
        r11.save();
        if (this.flipAnimationInProgress == true) goto L10;
        r11.scale(this.cameraContainer.getScaleX(), this.cameraContainer.getScaleY(), this.rect.centerX(), this.rect.centerY());
    L10:
        r11.drawArc(this.rect, -90.0f, this.progress * 360.0f, false, this.paint);
        r11.restore();
        return;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r3, KeyEvent r4) {
        if (r4.getAction() != 0) goto L9;
        if (r3 != 24) goto L9;
        adjustZoom(true);
        return true;
    L9:
        if (r4.getAction() != 0) goto L15;
        if (r3 != 25) goto L15;
        adjustZoom(false);
        return true;
    L15:
        return super.onKeyDown(r3, r4);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.updateTextureViewSize == true) goto L5;
    L12:
        super.onMeasure(r4, r5);
        int r42 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30);
        int r52 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30);
        this.flashViews.backgroundView.measure(r42, r52);
        this.flashViews.foregroundView.measure(r42, r52);
        return;
    L5:
        if ((View.MeasureSpec.getSize(r5) - getPaddingBottom()) <= (View.MeasureSpec.getSize(r4) * 1.3f)) goto L7;
        int r0 = AndroidUtilities.roundPlayingMessageSize;
    L9:
        if (r0 == this.textureViewSize) goto L11;
        this.textureViewSize = r0;
        ViewGroup.LayoutParams r02 = this.textureOverlayView.getLayoutParams();
        ViewGroup.LayoutParams r1 = this.textureOverlayView.getLayoutParams();
        int r2 = this.textureViewSize;
        r1.height = r2;
        r02.width = r2;
        ViewGroup.LayoutParams r03 = this.cameraContainer.getLayoutParams();
        ViewGroup.LayoutParams r12 = this.cameraContainer.getLayoutParams();
        int r22 = this.textureViewSize;
        r12.height = r22;
        r03.width = r22;
        ((FrameLayout.LayoutParams) this.muteImageView.getLayoutParams()).topMargin = (this.textureViewSize / 2) - AndroidUtilities.dp(24.0f);
        this.zoomSlider.setTextureViewSize(this.textureViewSize);
        this.textureOverlayView.setRoundRadius(this.textureViewSize / 2);
        this.cameraContainer.invalidateOutline();
    L11:
        this.updateTextureViewSize = false;
        goto L12
    L7:
        r0 = AndroidUtilities.roundMessageSize;
        goto L9
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void onPanTranslationUpdate(float r2) {
        this.panTranslationY = r2 / 2.0f;
        updateTranslationY();
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (getVisibility() == 0) goto L6;
        this.animationTranslationY = getMeasuredHeight() / 2.0f;
        updateTranslationY();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        if (r14.getAction() != 0) goto L8;
        if (r14.getY() <= (getMeasuredHeight() - getPaddingBottom())) goto L8;
        return false;
    L8:
        if (r14.getAction() != 0) goto L29;
        if (this.delegate == null) goto L29;
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L29;
        boolean r02 = r0.isMuted();
        this.videoPlayer.setMute(!r02);
        AnimatorSet r7 = this.muteAnimation;
        if (r7 == null) goto L16;
        r7.cancel();
    L16:
        AnimatorSet r72 = new AnimatorSet();
        this.muteAnimation = r72;
        ImageView r8 = this.muteImageView;
        if (r02 == true) goto L19;
        float r9 = 1.0f;
    L20:
        ObjectAnimator r82 = ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{r9});
        ImageView r92 = this.muteImageView;
        float r10 = 0.5f;
        if (r02 == true) goto L23;
        float r11 = 1.0f;
    L24:
        ObjectAnimator r93 = ObjectAnimator.ofFloat(r92, View.SCALE_X, new float[]{r11});
        ImageView r112 = this.muteImageView;
        if (r02 == true) goto L27;
        r10 = 1.0f;
    L27:
        r72.playTogether(new Animator[]{r82, r93, ObjectAnimator.ofFloat(r112, View.SCALE_Y, new float[]{r10})});
        this.muteAnimation.addListener(new AnonymousClass14(this));
        this.muteAnimation.setDuration(180);
        this.muteAnimation.setInterpolator(new DecelerateInterpolator());
        this.muteAnimation.start();
        goto L29
    L23:
        r11 = 0.5f;
        goto L24
    L19:
        r9 = 0.0f;
    L29:
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L33;
        getParent().requestDisallowInterceptTouchEvent(true);
        this.scaleGestureDetector.onTouchEvent(r14);
        return true;
    L33:
        if (r14.getActionMasked() == 0) goto L86;
        if (r14.getActionMasked() == 5) goto L86;
        if (r14.getActionMasked() != 2) goto L71;
        if (this.isInPinchToZoomTouchMode == false) goto L71;
        int r3 = -1;
        int r73 = -1;
        int r2 = 0;
    L43:
        if (r2 >= r14.getPointerCount()) goto L51;
        if (this.pointerId1 != r14.getPointerId(r2)) goto L48;
        r3 = r2;
    L48:
        if (this.pointerId2 != r14.getPointerId(r2)) goto L50;
        r73 = r2;
    L50:
        r2 = r2 + 1;
        goto L43
    L51:
        if (r3 == (-1)) goto L68;
        if (r73 == (-1)) goto L68;
        float r142 = (float) Math.hypot(r14.getX(r73) - r14.getX(r3), r14.getY(r73) - r14.getY(r3));
        float r1 = 0.002f;
        if (this.useCamera2 == false) goto L59;
        Camera2Session r03 = this.camera2SessionCurrent;
        if (r03 == null) goto L59;
        r1 = 0.002f * (r03.getMaxZoom() - this.camera2SessionCurrent.getMinZoom());
    L59:
        float r04 = this.initialCameraZoom + ((r142 - this.pinchStartDistance) * r1);
        this.cameraZoom = r04;
        if (this.useCamera2 == false) goto L64;
        Camera2Session r143 = this.camera2SessionCurrent;
        if (r143 == null) goto L67;
        float r144 = Utilities.clamp(r04, r143.getMaxZoom(), this.camera2SessionCurrent.getMinZoom());
        this.cameraZoom = r144;
        this.camera2SessionCurrent.setZoom(r144);
    L67:
        this.zoomSlider.syncZoom(this.cameraZoom);
        this.zoomWas = true;
    L84:
        return true;
    L64:
        float r145 = Utilities.clamp(r04, 1.0f, 0.0f);
        this.cameraZoom = r145;
        CameraSession r05 = this.cameraSession;
        if (r05 == null) goto L67;
        r05.setZoom(r145);
    L68:
        this.isInPinchToZoomTouchMode = false;
        finishZoom();
        return false;
    L71:
        if (r14.getActionMasked() == 1) goto L79;
        if (r14.getActionMasked() != 6) goto L77;
        if (checkPointerIds(r14) == true) goto L79;
    L77:
        if (r14.getActionMasked() != 3) goto L84;
    L79:
        if (this.isInPinchToZoomTouchMode == false) goto L84;
        this.isInPinchToZoomTouchMode = false;
        if (this.zoomWas == false) goto L83;
        finishZoom();
        goto L84
    L83:
        this.zoomSlider.endPinchZoomGesture();
    L86:
        if (this.maybePinchToZoomTouchMode == false) goto L97;
        if (this.isInPinchToZoomTouchMode == true) goto L97;
        if (r14.getPointerCount() != 2) goto L97;
        if (this.finishZoomTransition != null) goto L97;
        if (this.recording == false) goto L97;
        this.pinchStartDistance = (float) Math.hypot(r14.getX(1) - r14.getX(0), r14.getY(1) - r14.getY(0));
        this.pointerId1 = r14.getPointerId(0);
        this.pointerId2 = r14.getPointerId(1);
        this.isInPinchToZoomTouchMode = true;
        this.zoomSlider.beginPinchZoomGesture();
        this.zoomWas = false;
        this.initialCameraZoom = this.cameraZoom;
    L97:
        if (r14.getActionMasked() != 0) goto L99;
        RectF r06 = AndroidUtilities.rectTmp;
        r06.set(this.cameraContainer.getX(), this.cameraContainer.getY(), this.cameraContainer.getX() + this.cameraContainer.getMeasuredWidth(), this.cameraContainer.getY() + this.cameraContainer.getMeasuredHeight());
        this.maybePinchToZoomTouchMode = r06.contains(r14.getX(), r14.getY());
    L99:
        return true;
    }

    public void resetCameraFile() {
        this.cameraFile = null;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void send(int r21, boolean r22, int r23, int r24, int r25, long r26, long r28) {
        if (this.textureView == null) goto L70;
        stopProgressTimer();
        VideoPlayer r2 = this.videoPlayer;
        if (r2 == null) goto L8;
        r2.releasePlayer(true);
        this.videoPlayer = null;
    L8:
        int r27 = 4;
        if (r21 != 4) goto L47;
        if (this.videoEncoder == null) goto L17;
        if (this.recordedTime <= 800) goto L17;
        requestStopRecording(1, new SendOptions(r22, r23, r24, r25, r26, r28));
        return;
    L17:
        if (BuildVars.DEBUG_VERSION == false) goto L22;
        if (this.cameraFile.exists() == true) goto L22;
        FileLog.e(new RuntimeException("file not found :( round video"));
    L22:
        if (this.videoEditedInfo != null) goto L25;
        VideoEditedInfo r1 = new VideoEditedInfo();
        this.videoEditedInfo = r1;
        r1.startTime = -1;
        r1.endTime = -1;
    L25:
        if (this.videoEditedInfo.needConvert() == false) goto L40;
        this.file = null;
        this.encryptedFile = null;
        this.key = null;
        this.iv = null;
        VideoEditedInfo r12 = this.videoEditedInfo;
        long r4 = r12.estimatedDuration;
        double r8 = r4;
        long r10 = r12.startTime;
        if (r10 >= 0) goto L30;
        r10 = 0;
    L30:
        long r14 = r12.endTime;
        if (r14 < 0) goto L33;
        r4 = r14;
    L33:
        long r42 = r4 - r10;
        r12.estimatedDuration = r42;
        r12.estimatedSize = Math.max(1, (long) (this.size * (r42 / r8)));
        this.videoEditedInfo.bitrate = SystemUtils.getRoundVideoBitrate() * 1024;
        VideoEditedInfo r13 = this.videoEditedInfo;
        long r29 = r13.startTime;
        if (r29 <= 0) goto L36;
        r13.startTime = r29 * 1000;
    L36:
        long r210 = r13.endTime;
        if (r210 <= 0) goto L39;
        r13.endTime = r210 * 1000;
    L39:
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(this.cameraFile.getAbsolutePath(), false);
    L41:
        VideoEditedInfo r15 = this.videoEditedInfo;
        r15.file = this.file;
        r15.encryptedFile = this.encryptedFile;
        r15.key = this.key;
        r15.iv = this.iv;
        MediaController.PhotoEntry r82 = new MediaController.PhotoEntry(0, 0, 0, this.cameraFile.getAbsolutePath(), 0, true, 0, 0, 0);
        r82.ttl = r25;
        r82.effectId = r26;
        this.delegate.sendMedia(r82, this.videoEditedInfo, r22, r23, r24, false, r28);
        if (r23 == 0) goto L44;
        startAnimation(false, false);
    L44:
        MediaController.getInstance().requestRecordAudioFocus(false);
        return;
    L40:
        this.videoEditedInfo.estimatedSize = Math.max(1, this.size);
        goto L41
    L47:
        if (this.recordedTime >= 800) goto L49;
        boolean r5 = true;
    L50:
        this.cancelled = r5;
        this.recording = false;
        this.flashing = false;
        updateFlash();
        if (this.cancelled == true) goto L57;
        if (r21 != 3) goto L55;
        r27 = 2;
        goto L57
    L55:
        r27 = 5;
    L57:
        if (this.cameraThread == null) goto L66;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.recordStopped, new Object[]{Integer.valueOf(this.recordingGuid), Integer.valueOf(r27)});
        if (this.cancelled == false) goto L61;
        int r102 = 0;
    L64:
        saveLastCameraBitmap();
        this.cameraThread.shutdown(r102, r22, r23, r24, r25, r26);
        this.cameraThread = null;
        goto L66
    L61:
        if (r21 != 3) goto L63;
        r102 = 2;
        goto L64
    L63:
        r102 = 1;
    L66:
        if (this.cancelled == false) goto L69;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.audioRecordTooShort, new Object[]{Integer.valueOf(this.recordingGuid), Boolean.TRUE, Integer.valueOf((int) this.recordedTime)});
        startAnimation(false, false);
        MediaController.getInstance().requestRecordAudioFocus(false);
        return;
    L69:
        return;
    L49:
        r5 = false;
        goto L50
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void setButtonsBackground(BlurredBackgroundDrawableViewFactory r3, BlurredBackgroundColorProvider r4) {
        BlurredBackgroundDrawable r0 = r3.create(this.buttonsLayout, r4);
        r0.setPadding(AndroidUtilities.dp(6.0f));
        r0.setRadius(AndroidUtilities.dp(21.0f));
        this.buttonsLayout.setBackground(r0);
        InstantCameraZoomSlider r2 = this.zoomSlider;
        r2.setBlurBackground(r3.create(r2, r4));
    }

    public void setFrontface(boolean r1) {
        this.isFrontface = r1;
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void setInternalPadding(int r2) {
        this.internalPaddingBottom = r2;
        setPadding(0, 0, 0, r2);
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void setIsMessageTransition(boolean r1) {
        this.isMessageTransition = r1;
    }

    @Override // android.view.View
    public void setVisibility(int r5) {
        super.setVisibility(r5);
        this.buttonsLayout.setAlpha(0.0f);
        this.cameraContainer.setAlpha(0.0f);
        this.textureOverlayView.setAlpha(0.0f);
        this.muteImageView.setAlpha(0.0f);
        this.zoomSlider.setOpenAlpha(0.0f);
        float r1 = 1.0f;
        this.muteImageView.setScaleX(1.0f);
        this.muteImageView.setScaleY(1.0f);
        InstantViewCameraContainer r0 = this.cameraContainer;
        if (this.setVisibilityFromPause == false) goto L5;
        float r2 = 1.0f;
    L6:
        r0.setScaleX(r2);
        InstantViewCameraContainer r02 = this.cameraContainer;
        if (this.setVisibilityFromPause == false) goto L9;
        float r22 = 1.0f;
    L10:
        r02.setScaleY(r22);
        BackupImageView r03 = this.textureOverlayView;
        if (this.setVisibilityFromPause == false) goto L13;
        float r23 = 1.0f;
    L14:
        r03.setScaleX(r23);
        BackupImageView r04 = this.textureOverlayView;
        if (this.setVisibilityFromPause == true) goto L18;
        r1 = 0.1f;
    L18:
        r04.setScaleY(r1);
        if (this.cameraContainer.getMeasuredWidth() == 0) goto L30;
        this.cameraContainer.setPivotX(r0.getMeasuredWidth() / 2);
        this.cameraContainer.setPivotY(r0.getMeasuredHeight() / 2);
        this.textureOverlayView.setPivotX(r0.getMeasuredWidth() / 2);
        this.textureOverlayView.setPivotY(r0.getMeasuredHeight() / 2);
    L30:
        if (r5 != 0) goto L25;
        ((Activity) getContext()).getWindow().addFlags(128);     // Catch: Exception -> L27
        return;
    L25:
        ((Activity) getContext()).getWindow().clearFlags(128);     // Catch: Exception -> L27
        return;
    L27:
        e = move-exception;
        FileLog.e(e);
        return;
    L13:
        r23 = 0.1f;
        goto L14
    L9:
        r22 = 0.1f;
        goto L10
    L5:
        r2 = 0.1f;
        goto L6
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void showCamera(boolean r8) {
        if (this.textureView != null) goto L103;
        this.camLifecycle = new CameraXSession.CameraLifecycle();
        if (this.switchCameraDrawable != null) goto L8;
        int r2 = R.raw.roundcamera_flip;
        int r3 = this.buttonsSizePx;
        RLottieDrawable r0 = new RLottieDrawable(r2, r3, r3);
        this.switchCameraDrawable = r0;
        r0.setCurrentFrame(0);
        this.switchCameraDrawable.setCallback(this.switchCameraButton);
    L8:
        this.switchCameraButton.setImageDrawable(this.switchCameraDrawable);
        this.textureOverlayView.setAlpha(1.0f);
        this.textureOverlayView.invalidate();
        if (this.lastBitmap == null) goto L97;
    L11:
        Bitmap r02 = this.lastBitmap;
        BackupImageView r22 = this.textureOverlayView;
        if (r02 == null) goto L14;
        r22.setImageBitmap(r02);
    L15:
        this.cameraReady = false;
        this.selectedCamera = null;
        if (r8 == false) goto L18;
    L25:
        this.cancelled = false;
        this.file = null;
        this.encryptedFile = null;
        this.key = null;
        this.iv = null;
        this.needDrawFlickerStub = true;
        if (initCamera() == true) goto L29;
        return;
    L29:
        if (MediaController.getInstance().getPlayingMessageObject() != null) goto L31;
    L39:
        if (r8 == true) goto L41;
        this.cameraFile = new AnonymousClass8(this, FileLoader.getDirectory(3), System.currentTimeMillis() + "_" + SharedConfig.getLastLocalId() + ".mp4");
    L41:
        SharedConfig.saveConfig();
        AutoDeleteMediaTask.lockFile(this.cameraFile);
        if (BuildVars.LOGS_ENABLED == false) goto L45;
        FileLog.d("InstantCamera show round camera " + this.cameraFile.getAbsolutePath());
    L45:
        if (this.useCamera2 == false) goto L80;
        this.bothCameras = DualCameraView.roundDualAvailableStatic(getContext());
        if (this.bothCameras == false) goto L74;
        int r03 = 0;
    L50:
        if (r03 >= 2) goto L60;
        Camera2Session[] r32 = this.camera2Sessions;
        if (r32[r03] != null) goto L59;
        if (r03 != 0) goto L55;
        boolean r4 = true;
    L56:
        r32[r03] = Camera2Session.create(r4, SystemUtils.getRoundVideoResolution(), SystemUtils.getRoundVideoResolution());
        Camera2Session r33 = this.camera2Sessions[r03];
        if (r33 == null) goto L59;
        r33.setRecordingVideo(true);
        this.previewSize[r03] = new org.telegram.messenger.camera.Size(this.camera2Sessions[r03].getPreviewWidth(), this.camera2Sessions[r03].getPreviewHeight());
        goto L59
    L55:
        r4 = false;
    L59:
        r03 = r03 + 1;
        goto L50
    L60:
        updateFlash();
        Camera2Session[] r04 = this.camera2Sessions;
        boolean r34 = this.isFrontface;
        Camera2Session r42 = r04[!r34 ? 1 : 0];
        this.camera2SessionCurrent = r42;
        if (r42 == null) goto L66;
        if (r04[r34 ? 1 : 0] != null) goto L66;
        this.bothCameras = false;
    L66:
        if (this.bothCameras == true) goto L68;
    L70:
        int r05 = 0;
    L71:
        this.surfaceIndex = r05;
        if (this.camera2SessionCurrent == null) goto L105;
    L78:
        bindCamera2ZoomSlider(this.camera2SessionCurrent);
    L94:
        TextureView r06 = new TextureView(getContext());
        this.textureView = r06;
        r06.setSurfaceTextureListener(new AnonymousClass9(this));
        this.cameraContainer.addView(this.textureView, LayoutHelper.createFrame(-1, -1.0f));
        this.updateTextureViewSize = true;
        this.setVisibilityFromPause = r8;
        setVisibility(0);
        startAnimation(true, r8);
        MediaController.getInstance().requestRecordAudioFocus(true);
        return;
    L105:
        return;
    L68:
        if (this.isFrontface == true) goto L70;
        r05 = 1;
        goto L71
    L74:
        this.surfaceIndex = 0;
        Camera2Session[] r07 = this.camera2Sessions;
        boolean r35 = this.isFrontface;
        int r43 = !r35 ? 1 : 0;
        Camera2Session r36 = Camera2Session.create(r35, SystemUtils.getRoundVideoResolution(), SystemUtils.getRoundVideoResolution());
        r07[r43] = r36;
        this.camera2SessionCurrent = r36;
        if (r36 != null) goto L77;
        return;
    L77:
        r36.setRecordingVideo(true);
        this.previewSize[0] = new org.telegram.messenger.camera.Size(this.camera2SessionCurrent.getPreviewWidth(), this.camera2SessionCurrent.getPreviewHeight());
        goto L78
    L80:
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_X) goto L93;
        this.bothCameras = CameraXSession.isRoundDualAvailable(getContext());
        if (this.bothCameras == true) goto L84;
    L86:
        int r08 = 0;
    L87:
        this.surfaceIndex = r08;
        if (this.previewSize[0] != null) goto L90;
        int r09 = SystemUtils.getRoundVideoResolution();
        this.previewSize[0] = new org.telegram.messenger.camera.Size(r09, r09);
    L90:
        org.telegram.messenger.camera.Size[] r010 = this.previewSize;
        if (r010[1] != null) goto L94;
        r010[1] = r010[0];
        goto L94
    L84:
        if (this.isFrontface == true) goto L86;
        r08 = 1;
        goto L87
    L93:
        this.bothCameras = false;
        this.surfaceIndex = 0;
        goto L94
    L31:
        if (MediaController.getInstance().getPlayingMessageObject().isVideo() == false) goto L33;
    L38:
        MediaController.getInstance().cleanupPlayer(true, true);
        goto L39
    L33:
        if (MediaController.getInstance().getPlayingMessageObject().isRoundVideo() == true) goto L38;
        if (SharedConfig.pauseMusicOnRecord == false) goto L39;
        MediaController.getInstance().pauseByRewind();
        goto L39
    L18:
        if (ExteraConfig.getVideoMessagesCamera() != VideoMessagesCamera.ASK) goto L20;
    L24:
        updateFlash();
        this.recordedTime = 0;
        this.progress = 0.0f;
        goto L25
    L20:
        if (ExteraConfig.getVideoMessagesCamera() != VideoMessagesCamera.FRONT) goto L22;
        boolean r37 = true;
    L23:
        this.isFrontface = r37;
        goto L24
    L22:
        r37 = false;
        goto L23
    L14:
        r22.setImageResource(R.drawable.icplaceholder);
        goto L15
    L97:
        this.lastBitmap = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "icthumb.jpg").getAbsolutePath());     // Catch: Throwable -> L96
        goto L11
    }

    public void startAnimation(boolean r24, boolean r25) {
        dispatchAnimationState(r24, r25);
        AnimatorSet r3 = this.animatorSet;
        if (r3 == null) goto L5;
        r3.removeAllListeners();
        this.animatorSet.cancel();
    L5:
        PipRoundVideoView r32 = PipRoundVideoView.getInstance();
        if (r32 == null) goto L9;
        r32.showTemporary(!r24);
    L9:
        if (r24 == true) goto L11;
    L17:
        this.opened = r24;
        View r5 = this.parentView;
        if (r5 == null) goto L20;
        r5.invalidate();
    L20:
        this.animatorSet = new AnimatorSet();
        if (r24 == false) goto L23;
    L25:
        float r52 = 0.0f;
    L26:
        if (r24 == false) goto L28;
        float r6 = 1.0f;
    L29:
        if (r24 == false) goto L31;
        float r7 = 0.0f;
    L32:
        ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{r6, r7});
        r72.addUpdateListener(new InstantCameraView$$ExternalSyntheticLambda2(this, r25));
        AnimatorSet r2 = this.animatorSet;
        LinearLayout r9 = this.buttonsLayout;
        if (r24 == false) goto L35;
        float r11 = 1.0f;
    L36:
        float[] r12 = {r11};
        Property r112 = View.ALPHA;
        ObjectAnimator r92 = ObjectAnimator.ofFloat(r9, r112, r12);
        InstantCameraZoomSlider r122 = this.zoomSlider;
        AnimationProperties.FloatProperty<InstantCameraZoomSlider> r13 = InstantCameraZoomSlider.OPEN_ALPHA;
        if (r24 == false) goto L39;
        float r14 = 1.0f;
    L40:
        ObjectAnimator r123 = ObjectAnimator.ofFloat(r122, r13, new float[]{r14});
        ObjectAnimator r132 = ObjectAnimator.ofFloat(this.muteImageView, r112, new float[]{0.0f});
        Paint r142 = this.paint;
        Property<Paint, Integer> r15 = AnimationProperties.PAINT_ALPHA;
        if (r24 == false) goto L43;
        int r16 = 255;
    L44:
        ObjectAnimator r33 = ObjectAnimator.ofInt(r142, r15, new int[]{r16});
        InstantViewCameraContainer r143 = this.cameraContainer;
        if (r24 == false) goto L48;
        float r152 = 1.0f;
    L49:
        ObjectAnimator r8 = ObjectAnimator.ofFloat(r143, r112, new float[]{r152});
        InstantViewCameraContainer r144 = this.cameraContainer;
        if (r24 == false) goto L53;
        float r18 = 1.0f;
    L54:
        Property r153 = View.SCALE_X;
        ObjectAnimator r10 = ObjectAnimator.ofFloat(r144, r153, new float[]{r18});
        InstantViewCameraContainer r145 = this.cameraContainer;
        if (r24 == false) goto L57;
        float r182 = 1.0f;
    L58:
        Property r62 = View.SCALE_Y;
        ObjectAnimator r4 = ObjectAnimator.ofFloat(r145, r62, new float[]{r182});
        Property r1 = View.TRANSLATION_X;
        ObjectAnimator r34 = ObjectAnimator.ofFloat(this.cameraContainer, r1, new float[]{r52});
        BackupImageView r146 = this.textureOverlayView;
        if (r24 == false) goto L62;
        float r20 = 1.0f;
    L63:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r146, r112, new float[]{r20});
        BackupImageView r113 = this.textureOverlayView;
        if (r24 == false) goto L67;
        float r147 = 1.0f;
    L68:
        ObjectAnimator r43 = ObjectAnimator.ofFloat(r113, r153, new float[]{r147});
        BackupImageView r114 = this.textureOverlayView;
        if (r24 == false) goto L71;
        float r17 = 1.0f;
    L72:
        r2.playTogether(new Animator[]{r92, r123, r132, r33, r8, r10, r4, r34, r42, r43, ObjectAnimator.ofFloat(r114, r62, new float[]{r17}), ObjectAnimator.ofFloat(this.textureOverlayView, r1, new float[]{r52}), r72});
        if (r24 == true) goto L75;
        this.animatorSet.addListener(new AnonymousClass10(this));
    L76:
        this.animatorSet.setDuration(180);
        this.animatorSet.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.start();
        return;
    L75:
        setTranslationX(0.0f);
        goto L76
    L71:
        r17 = 0.1f;
        goto L72
    L67:
        r147 = 0.1f;
        goto L68
    L62:
        r20 = 0.0f;
        goto L63
    L57:
        r182 = 0.1f;
        goto L58
    L53:
        r18 = 0.1f;
        goto L54
    L48:
        r152 = 0.0f;
        goto L49
    L43:
        r16 = 0;
        goto L44
    L39:
        r14 = 0.0f;
        goto L40
    L35:
        r11 = 0.0f;
        goto L36
    L31:
        r7 = 1.0f;
        goto L32
    L28:
        r6 = 0.0f;
        goto L29
    L23:
        if (this.recordedTime <= 300) goto L25;
        r52 = AndroidUtilities.dp(24.0f) - (getMeasuredWidth() / 2.0f);
        goto L26
    L11:
        if (this.opened == true) goto L17;
        this.cameraContainer.setTranslationX(0.0f);
        this.textureOverlayView.setTranslationX(0.0f);
        if (r25 == false) goto L15;
        float r53 = 0.0f;
    L16:
        this.animationTranslationY = r53;
        updateTranslationY();
        goto L17
    L15:
        r53 = getMeasuredHeight() / 2.0f;
        goto L16
    }

    @Override // org.telegram.ui.Components.InstantCameraViewBase
    public void togglePause() {
        if (this.recording == false) goto L34;
        if (this.recordedTime >= 800) goto L7;
        boolean r0 = true;
    L8:
        this.cancelled = r0;
        this.recording = false;
        updateFlash();
        if (this.cameraThread == null) goto L23;
        NotificationCenter r02 = NotificationCenter.getInstance(this.currentAccount);
        int r5 = NotificationCenter.recordStopped;
        Integer r6 = Integer.valueOf(this.recordingGuid);
        if (this.cancelled == false) goto L13;
        int r7 = 4;
    L14:
        r02.postNotificationName(r5, new Object[]{r6, Integer.valueOf(r7)});
        saveLastCameraBitmap();
        CameraGLThread r72 = this.cameraThread;
        boolean r03 = this.cancelled;
        if (r03 == false) goto L17;
        int r8 = 0;
    L18:
        if (r03 == false) goto L20;
        int r12 = 0;
    L21:
        r72.shutdown(r8, true, 0, 0, r12, 0);
        this.cameraThread = null;
        goto L23
    L20:
        r12 = -2;
        goto L21
    L17:
        r8 = 2;
        goto L18
    L13:
        r7 = 2;
    L23:
        if (this.cancelled == false) goto L27;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.audioRecordTooShort, new Object[]{Integer.valueOf(this.recordingGuid), Boolean.TRUE, Integer.valueOf((int) this.recordedTime)});
        startAnimation(false, false);
        MediaController.getInstance().requestRecordAudioFocus(false);
        return;
    L27:
        if (this.videoEncoder == null) goto L45;
        File r04 = this.previewFile;
        if (r04 == null) goto L31;
        r04.delete();
    L31:
        File r05 = StoryEntry.makeCacheFile(this.currentAccount, true);
        this.previewFile = r05;
        this.videoEncoder.pause(r05);
        return;
    L45:
        return;
    L7:
        r0 = false;
        goto L8
    L34:
        if (this.videoEncoder == null) goto L46;
        hideCamera(false);
        VideoPlayer r06 = this.videoPlayer;
        if (r06 == null) goto L38;
        r06.releasePlayer(true);
        this.videoPlayer = null;
    L38:
        showCamera(true);
        performHapticFeedback(3, 2);     // Catch: Exception -> L42
    L40:
        AndroidUtilities.lockOrientation(this.delegate.getParentActivity());
        invalidate();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.recordResumed, new Object[0]);
        return;
    }
}
