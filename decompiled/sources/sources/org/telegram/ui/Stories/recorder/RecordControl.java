package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.FlashViews;

/* loaded from: classes7.dex */
public class RecordControl extends View implements FlashViews.Invertable {
    private final float HALF_PI;
    private boolean a11yPrevCheck;
    private boolean a11yPrevDual;
    private boolean a11yPrevLoading;
    private boolean a11yPrevRecording;
    private boolean a11yPrevShowLock;
    private boolean a11yPrevStartIsVideo;
    private RecordControlAccessibilityHelper accessibilityHelper;
    public float amplitude;
    public final AnimatedFloat animatedAmplitude;
    private final Paint buttonPaint;
    private final Paint buttonPaintWhite;
    private final PointF check1;
    private final PointF check2;
    private final PointF check3;
    private final AnimatedFloat checkAnimated;
    private final Paint checkPaint;
    private final Path checkPath;
    private final Path circlePath;
    private final AnimatedFloat collage;
    private float collageProgress;
    private final AnimatedFloat collageProgressAnimated;
    private float cx;
    private float cy;
    private Delegate delegate;
    private boolean discardParentTouch;
    private boolean dual;
    private final AnimatedFloat dualT;
    private final ButtonBounce flipButton;
    private boolean flipButtonWasPressed;
    private final Drawable flipDrawableBlack;
    private float flipDrawableRotate;
    private final AnimatedFloat flipDrawableRotateT;
    private final Drawable flipDrawableWhite;
    private final ImageReceiver galleryImage;
    private final PointF h1;
    private final PointF h2;
    private final PointF h3;
    private final PointF h4;
    private final Paint hintLinePaintBlack;
    private final Paint hintLinePaintWhite;
    private long lastDuration;
    private float leftCx;
    private float[] loadingSegments;
    private final ButtonBounce lockButton;
    private final Drawable lockDrawable;
    private final AnimatedFloat lockedT;
    private boolean longpressRecording;
    private final Paint mainPaint;
    private final Path metaballsPath;
    private final CombinedDrawable noGalleryDrawable;
    private final Runnable onFlipLongPressRunnable;
    private final Runnable onRecordLongPressRunnable;
    private final Paint outlineFilledPaint;
    private final Paint outlinePaint;
    private float overrideStartModeIsVideoT;
    private final PointF p1;
    private final PointF p2;
    private final PointF p3;
    private final PointF p4;
    private final Drawable pauseDrawable;
    private final ButtonBounce recordButton;
    private final AnimatedFloat recordCx;
    private boolean recording;
    private boolean recordingLoading;
    private long recordingLoadingStart;
    private final AnimatedFloat recordingLoadingT;
    private final AnimatedFloat recordingLongT;
    private long recordingStart;
    private final AnimatedFloat recordingT;
    private RadialGradient redGradient;
    private final Matrix redMatrix;
    private final Paint redPaint;
    private float rightCx;
    private boolean showLock;
    private boolean startModeIsVideo;
    private final AnimatedFloat startModeIsVideoT;
    private boolean touch;
    private final AnimatedFloat touchIsButtonT;
    private final AnimatedFloat touchIsCenter2T;
    private final AnimatedFloat touchIsCenterT;
    private long touchStart;
    private final AnimatedFloat touchT;
    private float touchX;
    private float touchY;
    private final Drawable unlockDrawable;

    public interface Delegate {
        boolean canRecordAudio();

        long getMaxVideoDuration();

        default long getMaxVisibleVideoDuration() {
            return 60000;
        }

        void onCheckClick();

        void onFlipClick();

        void onFlipLongClick();

        void onGalleryClick();

        void onPhotoShoot();

        void onVideoDuration(long r1);

        void onVideoRecordEnd(boolean r1);

        void onVideoRecordLocked();

        void onVideoRecordStart(boolean r1, Runnable r2);

        void onZoom(float r1);

        default boolean showStoriesDrafts() {
            return true;
        }
    }

    public class RecordControlAccessibilityHelper extends ExploreByTouchHelper {
        final /* synthetic */ RecordControl this$0;
        private final Rect tmpRect;

        public static /* synthetic */ void $r8$lambda$aH7iXxwXz_ypY6zv2ZAEylcr6jU(RecordControlAccessibilityHelper r0) {
            r0.lambda$onPerformActionForVirtualView$0();
        }

        public RecordControlAccessibilityHelper(RecordControl r1, View r2) {
            this.this$0 = r1;
            super(r2);
            this.tmpRect = new Rect();
        }

        private /* synthetic */ void lambda$onPerformActionForVirtualView$0() {
            RecordControl.m20195$$Nest$fputrecordingStart(this.this$0, System.currentTimeMillis());
            RecordControl.m20190$$Nest$fputlastDuration(this.this$0, 0);
            RecordControl.m20192$$Nest$fputrecording(this.this$0, true);
            RecordControl.m20181$$Nest$fgetdelegate(this.this$0).onVideoDuration(RecordControl.m20182$$Nest$fgetlastDuration(this.this$0));
            this.this$0.invalidate();
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float r4, float r5) {
            if (Math.abs(r4 - RecordControl.m20183$$Nest$fgetleftCx(this.this$0)) > AndroidUtilities.dp(30.0f)) goto L13;
            if (Math.abs(r5 - RecordControl.m20180$$Nest$fgetcy(this.this$0)) > AndroidUtilities.dp(30.0f)) goto L13;
            if (this.this$0.hasCheck() == true) goto L13;
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == true) goto L13;
            return 0;
        L13:
            if (Math.abs(r4 - RecordControl.m20187$$Nest$fgetrightCx(this.this$0)) > AndroidUtilities.dp(30.0f)) goto L23;
            if (Math.abs(r5 - RecordControl.m20180$$Nest$fgetcy(this.this$0)) > AndroidUtilities.dp(30.0f)) goto L23;
            if (this.this$0.hasCheck() == true) goto L23;
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == true) goto L23;
            return 2;
        L23:
            if (Math.abs(r4 - RecordControl.m20179$$Nest$fgetcx(this.this$0)) <= AndroidUtilities.dp(60.0f)) goto L25;
            return Integer.MIN_VALUE;
        L25:
            if (Math.abs(r5 - RecordControl.m20180$$Nest$fgetcy(this.this$0)) > AndroidUtilities.dp(60.0f)) goto L30;
            return 1;
        L30:
            return Integer.MIN_VALUE;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> r2) {
            if (this.this$0.hasCheck() == false) goto L5;
        L7:
            r2.add(1);
            if (this.this$0.hasCheck() == false) goto L10;
            return;
        L10:
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == true) goto L14;
            r2.add(2);
            return;
        L14:
            return;
        L5:
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == true) goto L7;
            r2.add(0);
            goto L7
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int r4, int r5, Bundle r6) {
            if (RecordControl.m20181$$Nest$fgetdelegate(this.this$0) != null) goto L5;
        L44:
            return false;
        L5:
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == true) goto L44;
            if (r5 == 16) goto L11;
            return false;
        L11:
            if (r4 == 0) goto L35;
            if (r4 != 1) goto L14;
            boolean r42 = this.this$0.hasCheck();
            RecordControl r62 = this.this$0;
            if (r42 == false) goto L24;
            RecordControl.m20181$$Nest$fgetdelegate(r62).onCheckClick();
        L33:
            return true;
        L24:
            boolean r43 = RecordControl.m20185$$Nest$fgetrecording(r62);
            RecordControl r63 = this.this$0;
            if (r43 == false) goto L27;
            RecordControl.m20192$$Nest$fputrecording(r63, false);
            RecordControl.m20191$$Nest$fputlongpressRecording(this.this$0, false);
            RecordControl.m20194$$Nest$fputrecordingLoadingStart(this.this$0, SystemClock.elapsedRealtime());
            RecordControl.m20193$$Nest$fputrecordingLoading(this.this$0, true);
            RecordControl.m20181$$Nest$fgetdelegate(this.this$0).onVideoRecordEnd(false);
            this.this$0.invalidate();
            goto L33
        L27:
            boolean r44 = RecordControl.m20189$$Nest$fgetstartModeIsVideo(r63);
            RecordControl r64 = this.this$0;
            if (r44 == true) goto L30;
            RecordControl.m20181$$Nest$fgetdelegate(r64).onPhotoShoot();
            goto L33
        L30:
            if (RecordControl.m20181$$Nest$fgetdelegate(r64).canRecordAudio() == false) goto L33;
            RecordControl.m20190$$Nest$fputlastDuration(this.this$0, 0);
            RecordControl.m20195$$Nest$fputrecordingStart(this.this$0, System.currentTimeMillis());
            RecordControl.m20196$$Nest$fputshowLock(this.this$0, false);
            RecordControl.m20181$$Nest$fgetdelegate(this.this$0).onVideoRecordStart(false, new RecordControl$RecordControlAccessibilityHelper$$ExternalSyntheticLambda0(this));
            goto L33
        L14:
            if (r4 == 2) goto L17;
            return false;
        L17:
            if (this.this$0.hasCheck() == false) goto L19;
            return false;
        L19:
            this.this$0.rotateFlip(180.0f);
            RecordControl.m20181$$Nest$fgetdelegate(this.this$0).onFlipClick();
            return true;
        L35:
            if (this.this$0.hasCheck() == false) goto L38;
            return false;
        L38:
            if (RecordControl.m20185$$Nest$fgetrecording(this.this$0) == true) goto L40;
        L42:
            RecordControl.m20181$$Nest$fgetdelegate(this.this$0).onGalleryClick();
        L43:
            return true;
        L40:
            if (RecordControl.m20188$$Nest$fgetshowLock(this.this$0) == false) goto L42;
            RecordControl.m20191$$Nest$fputlongpressRecording(this.this$0, false);
            RecordControl.m20184$$Nest$fgetlockedT(this.this$0).set(1.0f, true);
            RecordControl.m20181$$Nest$fgetdelegate(this.this$0).onVideoRecordLocked();
            this.this$0.invalidate();
            goto L43
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int r8, AccessibilityNodeInfoCompat r9) {
            r9.setClassName("android.widget.Button");
            boolean r1 = false;
            if (r8 == 0) goto L32;
            if (r8 != 1) goto L6;
            float r82 = AndroidUtilities.dp(40.0f);
            this.tmpRect.set((int) (RecordControl.m20179$$Nest$fgetcx(this.this$0) - r82), (int) (RecordControl.m20180$$Nest$fgetcy(this.this$0) - r82), (int) (RecordControl.m20179$$Nest$fgetcx(this.this$0) + r82), (int) (RecordControl.m20180$$Nest$fgetcy(this.this$0) + r82));
            r9.setBoundsInParent(this.tmpRect);
            if (this.this$0.hasCheck() == false) goto L22;
            String r83 = LocaleController.getString(R.string.Send);
        L28:
            r9.setContentDescription(r83);
            r9.setEnabled(!RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0));
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == true) goto L48;
            r9.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            return;
        L48:
            return;
        L22:
            if (RecordControl.m20185$$Nest$fgetrecording(this.this$0) == false) goto L25;
            r83 = LocaleController.getString(R.string.AccDescrStopRecording);
            goto L28
        L25:
            if (RecordControl.m20189$$Nest$fgetstartModeIsVideo(this.this$0) == false) goto L27;
            r83 = LocaleController.getString(R.string.AccDescrStartRecording);
            goto L28
        L27:
            r83 = LocaleController.getString(R.string.AccDescrTakePhoto);
            goto L28
        L6:
            if (r8 == 2) goto L9;
            this.tmpRect.set(0, 0, 1, 1);
            r9.setBoundsInParent(this.tmpRect);
            r9.setVisibleToUser(false);
            r9.setContentDescription(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L9:
            float r84 = AndroidUtilities.dp(22.0f);
            this.tmpRect.set((int) (RecordControl.m20187$$Nest$fgetrightCx(this.this$0) - r84), (int) (RecordControl.m20180$$Nest$fgetcy(this.this$0) - r84), (int) (RecordControl.m20187$$Nest$fgetrightCx(this.this$0) + r84), (int) (RecordControl.m20180$$Nest$fgetcy(this.this$0) + r84));
            r9.setBoundsInParent(this.tmpRect);
            r9.setContentDescription(LocaleController.getString(R.string.AccDescrSwitchCamera));
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == false) goto L12;
        L14:
            r9.setEnabled(r1);
            if (r1 == false) goto L47;
            r9.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            return;
        L47:
            return;
        L12:
            if (this.this$0.hasCheck() == true) goto L14;
            r1 = true;
            goto L14
        L32:
            float r85 = AndroidUtilities.dp(22.0f);
            this.tmpRect.set((int) (RecordControl.m20183$$Nest$fgetleftCx(this.this$0) - r85), (int) (RecordControl.m20180$$Nest$fgetcy(this.this$0) - r85), (int) (RecordControl.m20183$$Nest$fgetleftCx(this.this$0) + r85), (int) (RecordControl.m20180$$Nest$fgetcy(this.this$0) + r85));
            r9.setBoundsInParent(this.tmpRect);
            if (RecordControl.m20185$$Nest$fgetrecording(this.this$0) == true) goto L35;
        L37:
            String r86 = LocaleController.getString(R.string.AccDescrCameraGallery);
        L38:
            r9.setContentDescription(r86);
            if (RecordControl.m20186$$Nest$fgetrecordingLoading(this.this$0) == false) goto L41;
        L43:
            r9.setEnabled(r1);
            if (r1 == false) goto L49;
            r9.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            return;
        L49:
            return;
        L41:
            if (this.this$0.hasCheck() == true) goto L43;
            r1 = true;
            goto L43
        L35:
            if (RecordControl.m20188$$Nest$fgetshowLock(this.this$0) == false) goto L37;
            r86 = LocaleController.getString(R.string.AccDescrLockRecording);
            goto L38
        }
    }

    /* renamed from: $r8$lambda$5Bec-GJR4l-6WOdnT5sJssEGgtM, reason: not valid java name */
    public static /* synthetic */ void m20176$r8$lambda$5BecGJR4l6WOdnT5sJssEGgtM(RecordControl r0) {
        r0.lambda$onDraw$3();
    }

    public static /* synthetic */ void $r8$lambda$dtyl4G0jnkQVyhow2DHmfjUHX8s(RecordControl r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$o-aU4dnUh9NM05mH6e1rn4d-jhY, reason: not valid java name */
    public static /* synthetic */ void m20177$r8$lambda$oaU4dnUh9NM05mH6e1rn4djhY(RecordControl r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$pTV5KAt3K95yCiwmvpKIt9CcKBg(RecordControl r0) {
        r0.lambda$onTouchEvent$4();
    }

    /* renamed from: $r8$lambda$peexlpDkfU1fab-Za7b8N5KY2Pw, reason: not valid java name */
    public static /* synthetic */ void m20178$r8$lambda$peexlpDkfU1fabZa7b8N5KY2Pw(RecordControl r0) {
        r0.lambda$new$2();
    }

    /* renamed from: -$$Nest$fgetcx, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20179$$Nest$fgetcx(RecordControl r0) {
        return r0.cx;
    }

    /* renamed from: -$$Nest$fgetcy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20180$$Nest$fgetcy(RecordControl r0) {
        return r0.cy;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m20181$$Nest$fgetdelegate(RecordControl r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetlastDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20182$$Nest$fgetlastDuration(RecordControl r2) {
        return r2.lastDuration;
    }

    /* renamed from: -$$Nest$fgetleftCx, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20183$$Nest$fgetleftCx(RecordControl r0) {
        return r0.leftCx;
    }

    /* renamed from: -$$Nest$fgetlockedT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m20184$$Nest$fgetlockedT(RecordControl r0) {
        return r0.lockedT;
    }

    /* renamed from: -$$Nest$fgetrecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20185$$Nest$fgetrecording(RecordControl r0) {
        return r0.recording;
    }

    /* renamed from: -$$Nest$fgetrecordingLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20186$$Nest$fgetrecordingLoading(RecordControl r0) {
        return r0.recordingLoading;
    }

    /* renamed from: -$$Nest$fgetrightCx, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20187$$Nest$fgetrightCx(RecordControl r0) {
        return r0.rightCx;
    }

    /* renamed from: -$$Nest$fgetshowLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20188$$Nest$fgetshowLock(RecordControl r0) {
        return r0.showLock;
    }

    /* renamed from: -$$Nest$fgetstartModeIsVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20189$$Nest$fgetstartModeIsVideo(RecordControl r0) {
        return r0.startModeIsVideo;
    }

    /* renamed from: -$$Nest$fputlastDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20190$$Nest$fputlastDuration(RecordControl r0, long r1) {
        r0.lastDuration = r1;
    }

    /* renamed from: -$$Nest$fputlongpressRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20191$$Nest$fputlongpressRecording(RecordControl r0, boolean r1) {
        r0.longpressRecording = r1;
    }

    /* renamed from: -$$Nest$fputrecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20192$$Nest$fputrecording(RecordControl r0, boolean r1) {
        r0.recording = r1;
    }

    /* renamed from: -$$Nest$fputrecordingLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20193$$Nest$fputrecordingLoading(RecordControl r0, boolean r1) {
        r0.recordingLoading = r1;
    }

    /* renamed from: -$$Nest$fputrecordingLoadingStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20194$$Nest$fputrecordingLoadingStart(RecordControl r0, long r1) {
        r0.recordingLoadingStart = r1;
    }

    /* renamed from: -$$Nest$fputrecordingStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20195$$Nest$fputrecordingStart(RecordControl r0, long r1) {
        r0.recordingStart = r1;
    }

    /* renamed from: -$$Nest$fputshowLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20196$$Nest$fputshowLock(RecordControl r0, boolean r1) {
        r0.showLock = r1;
    }

    public RecordControl(Context r28) {
        super(r28);
        ImageReceiver r7 = new ImageReceiver();
        this.galleryImage = r7;
        this.mainPaint = new Paint(1);
        Paint r9 = new Paint(1);
        this.outlinePaint = r9;
        Paint r10 = new Paint(1);
        this.outlineFilledPaint = r10;
        Paint r11 = new Paint(1);
        this.buttonPaint = r11;
        Paint r12 = new Paint(1);
        this.buttonPaintWhite = r12;
        Paint r13 = new Paint(1);
        this.redPaint = r13;
        Paint r14 = new Paint(1);
        this.hintLinePaintWhite = r14;
        Paint r15 = new Paint(1);
        this.hintLinePaintBlack = r15;
        Paint r0 = new Paint(1);
        this.checkPaint = r0;
        Matrix r2 = new Matrix();
        this.redMatrix = r2;
        this.recordButton = new ButtonBounce(this);
        this.flipButton = new ButtonBounce(this);
        this.lockButton = new ButtonBounce(this);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.flipDrawableRotateT = new AnimatedFloat(this, 0, 310, r6);
        this.dualT = new AnimatedFloat(this, 0, 330, r6);
        this.checkPath = new Path();
        this.check1 = new PointF(-AndroidUtilities.dpf2(9.666667f), AndroidUtilities.dpf2(2.3333333f));
        this.check2 = new PointF(-AndroidUtilities.dpf2(2.8333333f), AndroidUtilities.dpf2(8.666667f));
        this.check3 = new PointF(AndroidUtilities.dpf2(9.666667f), AndroidUtilities.dpf2(-3.6666667f));
        this.animatedAmplitude = new AnimatedFloat(this, 0, 200, CubicBezierInterpolator.DEFAULT);
        this.startModeIsVideoT = new AnimatedFloat(this, 0, 350, r6);
        this.overrideStartModeIsVideoT = -1.0f;
        this.startModeIsVideo = true;
        this.recordingT = new AnimatedFloat(this, 0, 350, r6);
        this.recordingLongT = new AnimatedFloat(this, 0, 850, r6);
        this.loadingSegments = new float[2];
        this.recordingLoadingT = new AnimatedFloat(this, 0, 350, r6);
        this.touchT = new AnimatedFloat(this, 0, 350, r6);
        this.touchIsCenterT = new AnimatedFloat(this, 0, 650, r6);
        this.touchIsCenter2T = new AnimatedFloat(this, 0, 160, CubicBezierInterpolator.EASE_IN);
        this.recordCx = new AnimatedFloat(this, 0, 750, r6);
        this.touchIsButtonT = new AnimatedFloat(this, 0, 650, r6);
        this.lockedT = new AnimatedFloat(this, 0, 320, r6);
        this.collage = new AnimatedFloat(this, 0, 320, r6);
        this.collageProgressAnimated = new AnimatedFloat(this, 0, 320, r6);
        this.checkAnimated = new AnimatedFloat(this, 0, 320, r6);
        this.onRecordLongPressRunnable = new RecordControl$$ExternalSyntheticLambda3(this);
        this.onFlipLongPressRunnable = new RecordControl$$ExternalSyntheticLambda4(this);
        this.metaballsPath = new Path();
        this.circlePath = new Path();
        this.HALF_PI = 1.5707964f;
        this.p1 = new PointF();
        this.p2 = new PointF();
        this.p3 = new PointF();
        this.p4 = new PointF();
        this.h1 = new PointF();
        this.h2 = new PointF();
        this.h3 = new PointF();
        this.h4 = new PointF();
        setWillNotDraw(false);
        RecordControlAccessibilityHelper r22 = new RecordControlAccessibilityHelper(this, this);
        this.accessibilityHelper = r22;
        ViewCompat.setAccessibilityDelegate(this, r22);
        RadialGradient r20 = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(48.0f), new int[]{-577231, -577231, -1}, new float[]{0.0f, 0.64f, 1.0f}, Shader.TileMode.CLAMP);
        this.redGradient = r20;
        r20.setLocalMatrix(r2);
        r13.setShader(this.redGradient);
        r9.setColor(-1);
        Paint.Style r23 = Paint.Style.STROKE;
        r9.setStyle(r23);
        Paint.Cap r5 = Paint.Cap.ROUND;
        r9.setStrokeCap(r5);
        r10.setColor(-577231);
        r10.setStrokeCap(r5);
        r10.setStyle(r23);
        r11.setColor(1677721600);
        r12.setColor(-1);
        r14.setColor(1493172223);
        r15.setColor(402653184);
        r14.setStyle(r23);
        r14.setStrokeCap(r5);
        r15.setStyle(r23);
        r15.setStrokeCap(r5);
        r0.setStyle(r23);
        r0.setStrokeJoin(Paint.Join.ROUND);
        r0.setStrokeCap(r5);
        if (Build.VERSION.SDK_INT < 29) goto L5;
        r0.setBlendMode(RecordControl$$ExternalSyntheticApiModelOutline0.m());
    L6:
        r7.setParentView(this);
        r7.setCrossfadeWithOldImage(true);
        r7.setRoundRadius(AndroidUtilities.dp(6.0f));
        Drawable r3 = r28.getResources().getDrawable(R.drawable.msg_media_gallery).mutate();
        PorterDuff.Mode r62 = PorterDuff.Mode.MULTIPLY;
        r3.setColorFilter(new PorterDuffColorFilter(1308622847, r62));
        CombinedDrawable r52 = new CombinedDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), -13750737), r3);
        this.noGalleryDrawable = r52;
        r52.setFullsize(false);
        r52.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        Drawable r02 = r28.getResources().getDrawable(R.drawable.msg_photo_switch2).mutate();
        this.flipDrawableWhite = r02;
        r02.setColorFilter(new PorterDuffColorFilter(-1, r62));
        Drawable r03 = r28.getResources().getDrawable(R.drawable.msg_photo_switch2).mutate();
        this.flipDrawableBlack = r03;
        r03.setColorFilter(new PorterDuffColorFilter(-16777216, r62));
        Drawable r04 = r28.getResources().getDrawable(R.drawable.msg_filled_unlockedrecord).mutate();
        this.unlockDrawable = r04;
        r04.setColorFilter(new PorterDuffColorFilter(-1, r62));
        Drawable r05 = r28.getResources().getDrawable(R.drawable.msg_filled_lockedrecord).mutate();
        this.lockDrawable = r05;
        r05.setColorFilter(new PorterDuffColorFilter(-16777216, r62));
        Drawable r06 = r28.getResources().getDrawable(R.drawable.msg_round_pause_m).mutate();
        this.pauseDrawable = r06;
        r06.setColorFilter(new PorterDuffColorFilter(-1, r62));
        updateGalleryImage();
        return;
    L5:
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        goto L6
    }

    private float dist(PointF r2, PointF r3) {
        return MathUtils.distance(r2.x, r2.y, r3.x, r3.y);
    }

    private void getVector(float r5, float r6, double r7, float r9, PointF r10) {
        double r2 = r9;
        r10.x = (float) (r5 + (Math.cos(r7) * r2));
        r10.y = (float) (r6 + (Math.sin(r7) * r2));
    }

    private boolean isPressed(float r3, float r4, float r5, float r6, float r7, boolean r8) {
        if (this.recording == false) goto L13;
        if (r8 == false) goto L9;
        if ((r6 - r4) <= AndroidUtilities.dp(100.0f)) goto L9;
        return false;
    L9:
        if (Math.abs(r5 - r3) > r7) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (MathUtils.distance(r3, r4, r5, r6) > r7) goto L15;
        return true;
    L15:
        return false;
    }

    private /* synthetic */ void lambda$new$0() {
        this.recordingStart = System.currentTimeMillis();
        this.recording = true;
        Delegate r0 = this.delegate;
        this.lastDuration = 0;
        r0.onVideoDuration(0);
    }

    private /* synthetic */ void lambda$new$1() {
        if (this.recording == false) goto L5;
        return;
    L5:
        if (hasCheck() == false) goto L8;
        return;
    L8:
        if (this.delegate.canRecordAudio() == true) goto L11;
        this.touch = false;
        this.recordButton.setPressed(false);
        this.flipButton.setPressed(false);
        this.lockButton.setPressed(false);
        return;
    L11:
        this.longpressRecording = true;
        this.showLock = true;
        this.delegate.onVideoRecordStart(true, new RecordControl$$ExternalSyntheticLambda5(this));
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.recording == false) goto L5;
        return;
    L5:
        if (hasCheck() == true) goto L9;
        this.delegate.onFlipLongClick();
        rotateFlip(360.0f);
        this.touch = false;
        this.recordButton.setPressed(false);
        this.flipButton.setPressed(false);
        this.lockButton.setPressed(false);
        return;
    }

    private /* synthetic */ void lambda$onDraw$3() {
        this.recording = false;
        this.longpressRecording = false;
        this.recordingLoadingStart = SystemClock.elapsedRealtime();
        this.recordingLoading = true;
        this.touch = false;
        this.recordButton.setPressed(false);
        this.flipButton.setPressed(false);
        this.lockButton.setPressed(false);
        this.delegate.onVideoRecordEnd(true);
    }

    private /* synthetic */ void lambda$onTouchEvent$4() {
        this.recordingStart = System.currentTimeMillis();
        this.lastDuration = 0;
        this.recording = true;
        this.delegate.onVideoDuration(0);
    }

    private void notifyAccessibilityIfChanged() {
        if (this.accessibilityHelper == null) goto L21;
        boolean r0 = hasCheck();
        boolean r1 = this.a11yPrevRecording;
        boolean r2 = this.recording;
        if (r1 == r2) goto L8;
    L19:
        this.a11yPrevRecording = r2;
        this.a11yPrevCheck = r0;
        this.a11yPrevDual = this.dual;
        this.a11yPrevStartIsVideo = this.startModeIsVideo;
        this.a11yPrevLoading = this.recordingLoading;
        this.a11yPrevShowLock = this.showLock;
        this.accessibilityHelper.invalidateRoot();
        return;
    L8:
        if (this.a11yPrevCheck != r0) goto L19;
        if (this.a11yPrevDual != this.dual) goto L19;
        if (this.a11yPrevStartIsVideo != this.startModeIsVideo) goto L19;
        if (this.a11yPrevLoading != this.recordingLoading) goto L19;
        if (this.a11yPrevShowLock != this.showLock) goto L19;
        return;
    }

    private static void setDrawableBounds(Drawable r2, float r3, float r4) {
        setDrawableBounds(r2, r3, r4, Math.max(r2.getIntrinsicWidth(), r2.getIntrinsicHeight()) / 2.0f);
    }

    private static void setDrawableBounds(Drawable r2, float r3, float r4, float r5) {
        r2.setBounds((int) (r3 - r5), (int) (r4 - r5), (int) (r3 + r5), (int) (r4 + r5));
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent r2) {
        RecordControlAccessibilityHelper r0 = this.accessibilityHelper;
        if (r0 == null) goto L9;
        if (r0.dispatchHoverEvent(r2) == false) goto L9;
        return true;
    L9:
        return super.dispatchHoverEvent(r2);
    }

    public boolean hasCheck() {
        if (this.collageProgress < 1.0f) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isTouch() {
        return this.discardParentTouch;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.galleryImage.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        this.galleryImage.onDetachedFromWindow();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r50) {
        AnimatedFloat r1 = this.recordingT;
        if (this.recording == false) goto L5;
        float r2 = 1.0f;
    L6:
        float r10 = r1.set(r2);
        AnimatedFloat r12 = this.recordingLongT;
        if (this.recording == false) goto L9;
        float r22 = 1.0f;
    L10:
        float r11 = r12.set(r22);
        float r13 = this.overrideStartModeIsVideoT;
        if (r13 >= 0.0f) goto L18;
        AnimatedFloat r14 = this.startModeIsVideoT;
        if (this.startModeIsVideo == false) goto L16;
        float r23 = 1.0f;
    L17:
        r13 = r14.set(r23);
        goto L18
    L16:
        r23 = 0.0f;
    L18:
        float r122 = Math.max(r10, r13);
        AnimatedFloat r15 = this.touchT;
        if (this.touch == false) goto L21;
        float r24 = 1.0f;
    L22:
        float r132 = r15.set(r24);
        AnimatedFloat r16 = this.touchIsCenterT;
        if (Math.abs(this.touchX - this.cx) < AndroidUtilities.dp(64.0f)) goto L25;
    L29:
        float r25 = 0.0f;
    L30:
        float r142 = r132 * r16.set(r25);
        AnimatedFloat r17 = this.touchIsCenter2T;
        if (Math.abs(this.touchX - this.cx) >= AndroidUtilities.dp(64.0f)) goto L33;
        float r26 = 1.0f;
    L34:
        float r152 = r132 * r17.set(r26);
        float r7 = Utilities.clamp((this.touchX - this.cx) / AndroidUtilities.dp(16.0f), 1.0f, -1.0f);
        float r172 = Utilities.clamp((this.touchX - this.cx) / AndroidUtilities.dp(64.0f), 1.0f, -1.0f);
        AnimatedFloat r18 = this.touchIsButtonT;
        if (Math.min(Math.abs(this.touchX - this.rightCx), Math.abs(this.touchX - this.leftCx)) >= AndroidUtilities.dp(16.0f)) goto L37;
        float r27 = 1.0f;
    L38:
        float r19 = r18.set(r27) * r132;
        AnimatedFloat r28 = this.collage;
        if (this.collageProgress <= 0.0f) goto L41;
        boolean r3 = true;
    L42:
        float r20 = 1.0f - r10;
        float r29 = r28.set(r3) * r20;
        float r21 = this.collageProgressAnimated.set(this.collageProgress);
        float r32 = this.checkAnimated.set(hasCheck());
        if (this.longpressRecording == false) goto L45;
        float r4 = (r10 * r122) * r132;
    L47:
        if (r4 <= 0.0f) goto L49;
        float r5 = this.cx - AndroidUtilities.dp(50.0f);
        float r242 = 1.0f;
        float r6 = this.cx + AndroidUtilities.dp(50.0f);
        float r232 = 0.0f;
        this.hintLinePaintWhite.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.hintLinePaintBlack.setStrokeWidth(AndroidUtilities.dp(2.0f));
        float r282 = r11;
        float r9 = r29;
        float r272 = r32;
        float r8 = r19;
        float r262 = r7;
        float r72 = r4;
        r50.drawLine(r6, this.cy, AndroidUtilities.lerp(r6, this.rightCx - AndroidUtilities.dp(30.0f), r4), this.cy, this.hintLinePaintBlack);
        r50.drawLine(r6, this.cy, AndroidUtilities.lerp(r6, this.rightCx - AndroidUtilities.dp(30.0f), r72), this.cy, this.hintLinePaintWhite);
        r50.drawLine(r5, this.cy, AndroidUtilities.lerp(r5, this.leftCx + AndroidUtilities.dp(30.0f), r72), this.cy, this.hintLinePaintBlack);
        Canvas r110 = r50;
        r110.drawLine(r5, this.cy, AndroidUtilities.lerp(r5, this.leftCx + AndroidUtilities.dp(30.0f), r72), this.cy, this.hintLinePaintWhite);
    L50:
        float r210 = this.cx;
        float r252 = AndroidUtilities.lerp(r210, this.recordCx.set((AndroidUtilities.dp(4.0f) * r262) + r210), r142);
        float r263 = AndroidUtilities.lerp(AndroidUtilities.lerp(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(12.0f), r10), AndroidUtilities.dp(32.0f) - (AndroidUtilities.dp(4.0f) * Math.abs(r172)), r142);
        float r211 = AndroidUtilities.lerp(AndroidUtilities.lerp(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(7.0f), r10), AndroidUtilities.dp(32.0f), r142);
        ButtonBounce r33 = this.recordButton;
        if (this.startModeIsVideo == false) goto L53;
        float r42 = r232;
    L54:
        float r34 = AndroidUtilities.lerp(r33.getScale(r42), (this.animatedAmplitude.set(this.amplitude) * 0.2f) + r242, r10);
        RectF r43 = AndroidUtilities.rectTmp;
        float r62 = r252 - r263;
        float r73 = this.cy;
        float r112 = r252 + r263;
        r43.set(r62, r73 - r263, r112, r73 + r263);
        float r31 = r242 - r272;
        this.mainPaint.setColor(ColorUtils.blendARGB(-1, -577231, r122 * r31));
        int r102 = (r272 > r232 ? 1 : (r272 == r232 ? 0 : -1));
        if (r102 <= 0) goto L57;
        r110.save();
        r110.scale(r34, r34, this.cx, this.cy);
        this.mainPaint.setAlpha((int) (r31 * 255.0f));
        r110.drawRoundRect(r43, r211, r211, this.mainPaint);
        r110.restore();
        int r38 = r102;
        float r292 = r132;
        float r103 = r272;
        float r39 = r62;
        float r133 = r34;
        float r273 = r122;
        float r322 = r152;
        RectF r153 = r43;
        float r123 = r211;
        float r35 = r112;
        r110.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
    L58:
        r110.scale(r133, r133, this.cx, this.cy);
        this.mainPaint.setAlpha(255);
        r110.drawRoundRect(r153, r123, r123, this.mainPaint);
        if (r38 <= 0) goto L64;
        this.checkPaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
        this.checkPath.rewind();
        Path r36 = this.checkPath;
        PointF r44 = this.check1;
        r36.moveTo(r44.x, r44.y);
        float r63 = r103 / 0.3f;
        float r40 = 0.3f;
        float r74 = r232;
        float r113 = r242;
        this.checkPath.lineTo(AndroidUtilities.lerp(this.check1.x, this.check2.x, Utilities.clamp(r63, r113, r74)), AndroidUtilities.lerp(this.check1.y, this.check2.y, Utilities.clamp(r63, r113, r74)));
        if (r103 <= 0.3f) goto L63;
        float r52 = (r103 - 0.3f) / 0.7f;
        this.checkPath.lineTo(AndroidUtilities.lerp(this.check2.x, this.check3.x, Utilities.clamp(r52, 1.0f, 0.0f)), AndroidUtilities.lerp(this.check2.y, this.check3.y, Utilities.clamp(r52, 1.0f, 0.0f)));
    L63:
        r110.translate(this.cx, this.cy);
        r110.drawPath(this.checkPath, this.checkPaint);
    L65:
        r110.restore();
        r110.save();
        float r212 = Math.max(r133, 1.0f);
        r110.scale(r212, r212, this.cx, this.cy);
        float r213 = Math.max(AndroidUtilities.dpf2(33.5f), (r263 + AndroidUtilities.lerp(AndroidUtilities.dpf2(4.5f), AndroidUtilities.dp(9.0f), r142)) + ((AndroidUtilities.dp(5.0f) * r9) * (1.0f - r142)));
        float r75 = AndroidUtilities.lerp(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(4.0f), r9);
        float r214 = AndroidUtilities.lerp(r213, (r263 - r75) - AndroidUtilities.dp(4.0f), r103);
        float r37 = this.cx;
        float r53 = this.cy;
        r153.set(r37 - r214, r53 - r214, r37 + r214, r53 + r214);
        this.outlinePaint.setStrokeWidth(r75);
        this.outlinePaint.setAlpha((int) ((AndroidUtilities.lerp(1.0f, r40, r9) * 255.0f) * r31));
        r110.drawCircle(this.cx, this.cy, r214, this.outlinePaint);
        if (r9 <= 0.0f) goto L68;
        boolean r215 = true;
    L70:
        if (r21 <= 0.0f) goto L72;
        boolean r310 = true;
    L74:
        if ((r215 & r310) == false) goto L76;
        this.outlinePaint.setAlpha(255);
        RectF r216 = r153;
        r110.drawArc(r216, -90.0f, r21 * 360.0f, false, this.outlinePaint);
    L77:
        long r104 = System.currentTimeMillis() - this.recordingStart;
        if (this.recording == false) goto L80;
        float r111 = 0.0f;
    L81:
        Delegate r311 = this.delegate;
        long r45 = 60000;
        if (r311 == null) goto L84;
        long r402 = r311.getMaxVideoDuration();
    L85:
        Delegate r312 = this.delegate;
        if (r312 == null) goto L88;
        long r422 = r312.getMaxVisibleVideoDuration();
    L89:
        float r313 = r104;
        if (r422 < 0) goto L93;
        r45 = r422;
    L93:
        float r46 = Math.min((r313 / r45) * 360.0f, 360.0f);
        float r314 = this.recordingLoadingT.set(this.recordingLoading);
        this.outlineFilledPaint.setStrokeWidth(r75);
        this.outlineFilledPaint.setAlpha((int) (Math.max(r314 * 0.7f, 1.0f - r111) * 255.0f));
        if (r314 > 0.0f) goto L97;
        r50.drawArc(r216, -90.0f, r46, false, this.outlineFilledPaint);
        Canvas r76 = r50;
    L96:
        RectF r154 = r216;
        if (this.recording == false) goto L112;
        invalidate();
        long r315 = r104 / 1000;
        if (r315 == (this.lastDuration / 1000)) goto L107;
        this.delegate.onVideoDuration(r315);
    L107:
        if (r402 > 0) goto L109;
    L111:
        this.lastDuration = r104;
        goto L112
    L109:
        if (r104 < r402) goto L111;
        post(new RecordControl$$ExternalSyntheticLambda1(this));
    L112:
        r76.restore();
        if (this.showLock == false) goto L117;
        float r114 = this.lockButton.getScale(0.2f) * r10;
        if (r114 <= 0.0f) goto L117;
        r76.save();
        r76.scale(r114, r114, this.leftCx, this.cy);
        r76.drawCircle(this.leftCx, this.cy, AndroidUtilities.dp(22.0f), this.buttonPaint);
        r76.rotate(-getRotation(), this.leftCx, this.cy);
        this.unlockDrawable.draw(r76);
        r76.restore();
    L117:
        float r115 = (this.lockButton.getScale(0.2f) * r20) * r31;
        if (r115 <= 0.0f) goto L120;
        r76.save();
        r76.scale(r115, r115, this.leftCx, this.cy);
        r76.rotate(-getRotation(), this.leftCx, this.cy);
        this.galleryImage.draw(r76);
        r76.restore();
    L120:
        AnimatedFloat r116 = this.dualT;
        if (this.dual == false) goto L123;
        float r217 = 1.0f;
    L124:
        float r117 = r116.set(r217);
        if (r117 <= 0.0f) goto L128;
        r76.save();
        float r218 = (this.flipButton.getScale(0.2f) * r117) * r31;
        r76.scale(r218, r218, this.rightCx, this.cy);
        r76.rotate(this.flipDrawableRotateT.set(this.flipDrawableRotate) - getRotation(), this.rightCx, this.cy);
        r76.drawCircle(this.rightCx, this.cy, AndroidUtilities.dp(22.0f), this.buttonPaintWhite);
        this.flipDrawableBlack.draw(r76);
        r76.restore();
    L128:
        if (r117 >= 1.0f) goto L131;
        r76.save();
        float r219 = (this.flipButton.getScale(0.2f) * (1.0f - r117)) * r31;
        r76.scale(r219, r219, this.rightCx, this.cy);
        r76.rotate(this.flipDrawableRotateT.set(this.flipDrawableRotate) - getRotation(), this.rightCx, this.cy);
        r76.drawCircle(this.rightCx, this.cy, AndroidUtilities.dp(22.0f), this.buttonPaint);
        this.flipDrawableWhite.draw(r76);
        r76.restore();
    L131:
        if (this.longpressRecording == true) goto L133;
    L135:
        float r82 = 0.0f;
    L136:
        AnimatedFloat r118 = this.lockedT;
        if (this.longpressRecording == false) goto L139;
    L141:
        float r220 = 0.0f;
    L142:
        float r105 = r118.set(r220);
        if (r82 <= 0.0f) goto L159;
        this.redPaint.setAlpha(255);
        r76.drawCircle(this.touchX, this.cy, r82, this.redPaint);
        float r134 = this.touchX;
        float r143 = Utilities.clamp(1.0f - ((Math.abs(r172) * r292) / 1.3f), 1.0f, 0.0f);
        float r162 = Math.abs(r252 - r134);
        if (r162 >= (r263 + (r82 * 2.0f))) goto L159;
        if (r143 >= 0.6f) goto L159;
        float r173 = r263 + r82;
        if (r162 >= r173) goto L151;
        float r221 = r263 * r263;
        float r54 = r162 * r162;
        float r182 = r82 * r82;
        double r316 = Math.acos(((r221 + r54) - r182) / ((r263 * 2.0f) * r162));
        double r119 = Math.acos(((r182 + r54) - r221) / (r1 * r162));
    L153:
        if (r134 <= r252) goto L155;
        double r192 = 0.0d;
    L156:
        double r55 = (float) Math.acos((r263 - r82) / r162);
        float r183 = 22.0f;
        float r2110 = r105;
        double r92 = r143;
        double r442 = (r55 - r316) * r92;
        double r423 = (r192 + r316) + r442;
        double r443 = (r192 - r316) - r442;
        double r462 = ((3.141592653589793d - r119) - r55) * r92;
        double r93 = ((r192 + 3.141592653589793d) - r119) - r462;
        double r193 = ((r192 - 3.141592653589793d) + r119) + r462;
        getVector(r252, this.cy, r423, r263, this.p1);
        getVector(r252, this.cy, r443, r263, this.p2);
        float r56 = r82;
        getVector(r134, this.cy, r93, r56, this.p3);
        getVector(r134, this.cy, r193, r56, this.p4);
        float r106 = r56;
        float r120 = Math.min(r143 * 2.4f, dist(this.p1, this.p3) / r173) * Math.min(1.0f, (r162 * 2.0f) / r173);
        float r57 = r263 * r120;
        float r135 = r106 * r120;
        PointF r121 = this.p1;
        getVector(r121.x, r121.y, r423 - 1.5707963705062866d, r57, this.h1);
        PointF r124 = this.p2;
        getVector(r124.x, r124.y, r443 + 1.5707963705062866d, r57, this.h2);
        PointF r125 = this.p3;
        getVector(r125.x, r125.y, r93 + 1.5707963705062866d, r135, this.h3);
        PointF r126 = this.p4;
        getVector(r126.x, r126.y, r193 - 1.5707963705062866d, r135, this.h4);
        float r136 = ((r292 * r273) * r10) * r322;
        if (r136 <= 0.0f) goto L160;
        this.metaballsPath.rewind();
        Path r127 = this.metaballsPath;
        PointF r222 = this.p1;
        r127.moveTo(r222.x, r222.y);
        Path r128 = this.metaballsPath;
        PointF r223 = this.h1;
        float r317 = r223.x;
        float r224 = r223.y;
        PointF r47 = this.h3;
        float r58 = r47.x;
        float r48 = r47.y;
        PointF r64 = this.p3;
        r128.cubicTo(r317, r224, r58, r48, r64.x, r64.y);
        Path r129 = this.metaballsPath;
        PointF r225 = this.p4;
        r129.lineTo(r225.x, r225.y);
        Path r130 = this.metaballsPath;
        PointF r226 = this.h4;
        float r318 = r226.x;
        float r227 = r226.y;
        PointF r49 = this.h2;
        float r59 = r49.x;
        float r410 = r49.y;
        PointF r65 = this.p2;
        r130.cubicTo(r318, r227, r59, r410, r65.x, r65.y);
        Path r131 = this.metaballsPath;
        PointF r228 = this.p1;
        r131.lineTo(r228.x, r228.y);
        this.redPaint.setAlpha((int) (r136 * 255.0f));
        r76.drawPath(this.metaballsPath, this.redPaint);
        float r137 = this.cy;
        float r319 = r39;
        r154.set(r319, r137 - r263, r35, r137 + r263);
        r76.drawRoundRect(r154, r123, r123, this.redPaint);
    L160:
        if (r82 <= 0.0f) goto L162;
    L163:
        float r138 = (this.lockButton.getScale(0.2f) * r10) * r31;
        r76.save();
        this.circlePath.rewind();
        if (r82 <= 0.0f) goto L167;
        this.circlePath.addCircle(this.touchX, this.cy, r106, Path.Direction.CW);
    L167:
        if (r2110 > 0.0f) goto L169;
    L171:
        r76.clipPath(this.circlePath);
        if (this.showLock == false) goto L174;
        r76.save();
        r76.scale(r138, r138, this.leftCx, this.cy);
        r76.drawCircle(this.leftCx, this.cy, AndroidUtilities.dp(r183), this.buttonPaintWhite);
        r76.rotate(-getRotation(), this.leftCx, this.cy);
        this.lockDrawable.draw(r76);
        r76.restore();
    L174:
        float r139 = this.flipButton.getScale(0.2f) * r31;
        r76.save();
        r76.scale(r139, r139, this.rightCx, this.cy);
        r76.rotate(this.flipDrawableRotateT.set(this.flipDrawableRotate) - getRotation(), this.rightCx, this.cy);
        r76.drawCircle(this.rightCx, this.cy, AndroidUtilities.dp(r183), this.buttonPaintWhite);
        this.flipDrawableBlack.draw(r76);
        r76.restore();
        r76.restore();
    L175:
        notifyAccessibilityIfChanged();
        return;
    L169:
        if (this.showLock == false) goto L171;
        this.circlePath.addCircle(this.leftCx, this.cy, (r2110 * AndroidUtilities.dp(r183)) * r138, Path.Direction.CW);
        goto L171
    L162:
        if (r2110 <= 0.0f) goto L175;
    L155:
        r192 = 3.141592653589793d;
        goto L156
    L151:
        r119 = 0.0d;
        r316 = 0.0d;
    L159:
        r183 = 22.0f;
        r2110 = r105;
        r106 = r82;
        goto L160
    L139:
        if (this.recording == false) goto L141;
        r220 = 1.0f;
        goto L142
    L133:
        if (hasCheck() == true) goto L135;
        r82 = AndroidUtilities.lerp(AndroidUtilities.dp(16.0f), AndroidUtilities.lerp(AndroidUtilities.dp(8.0f) + (AndroidUtilities.dp(8.0f) * Math.abs(r172)), AndroidUtilities.dp(22.0f), r8), Math.max(r8, r142)) * ((r292 * r273) * r10);
        goto L136
    L123:
        r217 = 0.0f;
        goto L124
    L97:
        RectF r77 = r216;
        CircularProgressDrawable.getSegments((SystemClock.elapsedRealtime() - this.recordingLoadingStart) % 5400, this.loadingSegments);
        invalidate();
        float[] r140 = this.loadingSegments;
        float r229 = r140[0];
        float r141 = r140[1];
        float r510 = (r229 + r141) / 2.0f;
        float r144 = Math.abs(r141 - r229) / 2.0f;
        if (this.recordingLoading == false) goto L100;
        float r411 = r46 / 2.0f;
        r510 = AndroidUtilities.lerp((-90.0f) + r411, r510, r314);
        r144 = AndroidUtilities.lerp(r411, r144, r314);
    L100:
        r216 = r77;
        r50.drawArc(r216, r510 - r144, r144 * 2.0f, false, this.outlineFilledPaint);
        r76 = r50;
        goto L96
    L88:
        r422 = 60000;
        goto L89
    L84:
        r402 = 60000;
        goto L85
    L80:
        r111 = 1.0f - r282;
        goto L81
    L76:
        r216 = r153;
        goto L77
    L72:
        r310 = false;
        goto L74
    L68:
        r215 = false;
        goto L70
    L64:
        r40 = 0.3f;
        goto L65
    L57:
        r38 = r102;
        r35 = r112;
        r292 = r132;
        r103 = r272;
        r39 = r62;
        r133 = r34;
        r273 = r122;
        r322 = r152;
        r123 = r211;
        r153 = r43;
        r110.save();
        goto L58
    L53:
        r42 = 0.2f;
        goto L54
    L49:
        r272 = r32;
        r262 = r7;
        r242 = 1.0f;
        r232 = 0.0f;
        r282 = r11;
        r8 = r19;
        r9 = r29;
        r110 = r50;
        goto L50
    L45:
        r4 = 0.0f;
        goto L47
    L41:
        r3 = false;
        goto L42
    L37:
        r27 = 0.0f;
        goto L38
    L33:
        r26 = 0.0f;
        goto L34
    L25:
        if (this.recording == false) goto L27;
    L28:
        r25 = 1.0f;
        goto L30
    L27:
        if (this.recordButton.isPressed() == false) goto L29;
    L21:
        r24 = 0.0f;
        goto L22
    L9:
        r22 = 0.0f;
        goto L10
    L5:
        r2 = 0.0f;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r6, int r7) {
        int r62 = View.MeasureSpec.getSize(r6);
        int r72 = AndroidUtilities.dp(100.0f);
        float r0 = r62;
        this.cx = r0 / 2.0f;
        this.cy = r72 / 2.0f;
        float r02 = Math.min(AndroidUtilities.dp(135.0f), r0 * 0.35f);
        float r1 = this.cx;
        this.leftCx = r1 - r02;
        float r12 = r1 + r02;
        this.rightCx = r12;
        setDrawableBounds(this.flipDrawableWhite, r12, this.cy, AndroidUtilities.dp(14.0f));
        setDrawableBounds(this.flipDrawableBlack, this.rightCx, this.cy, AndroidUtilities.dp(14.0f));
        setDrawableBounds(this.unlockDrawable, this.leftCx, this.cy);
        setDrawableBounds(this.lockDrawable, this.leftCx, this.cy);
        setDrawableBounds(this.pauseDrawable, this.leftCx, this.cy);
        this.galleryImage.setImageCoords(this.leftCx - AndroidUtilities.dp(20.0f), this.cy - AndroidUtilities.dp(20.0f), AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
        this.redMatrix.reset();
        this.redMatrix.postTranslate(this.cx, this.cy);
        this.redGradient.setLocalMatrix(this.redMatrix);
        setMeasuredDimension(r62, r72);
        RecordControlAccessibilityHelper r5 = this.accessibilityHelper;
        if (r5 == null) goto L6;
        r5.invalidateRoot();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r13) {
        int r0 = r13.getAction();
        float r6 = Utilities.clamp(r13.getX() + 0.0f, this.rightCx, this.leftCx);
        float r7 = r13.getY() + 0.0f;
        boolean r12 = isPressed(r6, r7, this.rightCx, this.cy, AndroidUtilities.dp(7.0f), true);
        boolean r1 = true;
        boolean r3 = false;
        if (this.recordingLoading == false) goto L5;
        this.recordButton.setPressed(false);
        this.flipButton.setPressed(false);
        this.lockButton.setPressed(false);
    L21:
        if (r0 != 0) goto L34;
        this.touch = true;
        if (this.recordButton.isPressed() == false) goto L25;
    L26:
        r3 = true;
    L27:
        this.discardParentTouch = r3;
        this.touchStart = System.currentTimeMillis();
        this.touchX = r6;
        this.touchY = r7;
        if (Math.abs(r6 - this.cx) >= AndroidUtilities.dp(50.0f)) goto L31;
        AndroidUtilities.runOnUIThread(this.onRecordLongPressRunnable, ViewConfiguration.getLongPressTimeout());
    L31:
        if (this.flipButton.isPressed() == false) goto L92;
        AndroidUtilities.runOnUIThread(this.onFlipLongPressRunnable, ViewConfiguration.getLongPressTimeout());
    L92:
        this.flipButtonWasPressed = r12;
        return r1;
    L25:
        if (this.flipButton.isPressed() == false) goto L27;
    L34:
        if (r0 == 2) goto L36;
        if (r0 == 1) goto L55;
        if (r0 == 3) goto L55;
        r1 = false;
    L55:
        if (this.touch == true) goto L57;
        return false;
    L57:
        this.touch = false;
        this.discardParentTouch = false;
        AndroidUtilities.cancelRunOnUIThread(this.onRecordLongPressRunnable);
        AndroidUtilities.cancelRunOnUIThread(this.onFlipLongPressRunnable);
        if (this.recording == true) goto L63;
        if (this.lockButton.isPressed() == false) goto L63;
        this.delegate.onGalleryClick();
    L88:
        this.longpressRecording = false;
        if (this.flipButton.isPressed() == false) goto L91;
        rotateFlip(180.0f);
        this.delegate.onFlipClick();
    L91:
        this.recordButton.setPressed(false);
        this.flipButton.setPressed(false);
        this.lockButton.setPressed(false);
        invalidate();
    L63:
        if (this.recording == false) goto L71;
        if (this.longpressRecording == false) goto L71;
        if (this.lockButton.isPressed() == false) goto L69;
        this.longpressRecording = false;
        this.lockedT.set(1.0f, true);
        this.delegate.onVideoRecordLocked();
        goto L88
    L69:
        this.recording = false;
        this.recordingLoadingStart = SystemClock.elapsedRealtime();
        this.recordingLoading = true;
        this.delegate.onVideoRecordEnd(false);
    L71:
        if (this.recordButton.isPressed() == false) goto L88;
        if (hasCheck() == false) goto L76;
        this.delegate.onCheckClick();
        goto L88
    L76:
        if (this.startModeIsVideo == true) goto L83;
        if (this.recording == true) goto L83;
        if (this.longpressRecording == true) goto L83;
        this.delegate.onPhotoShoot();
    L83:
        if (this.recording == false) goto L85;
        this.recording = false;
        this.recordingLoadingStart = SystemClock.elapsedRealtime();
        this.recordingLoading = true;
        this.delegate.onVideoRecordEnd(false);
        goto L88
    L85:
        if (this.delegate.canRecordAudio() == false) goto L88;
        this.lastDuration = 0;
        this.recordingStart = System.currentTimeMillis();
        this.showLock = false;
        this.delegate.onVideoRecordStart(false, new RecordControl$$ExternalSyntheticLambda2(this));
        goto L88
    L36:
        if (this.touch == true) goto L38;
        return false;
    L38:
        this.touchX = Utilities.clamp(r6, this.rightCx, this.leftCx);
        this.touchY = r7;
        invalidate();
        if (this.recording == false) goto L45;
        if (this.flipButtonWasPressed == true) goto L45;
        if (r12 == false) goto L45;
        rotateFlip(180.0f);
        this.delegate.onFlipClick();
    L45:
        if (this.recording == false) goto L92;
        if (this.longpressRecording == false) goto L92;
        this.delegate.onZoom(Utilities.clamp(((this.cy - AndroidUtilities.dp(48.0f)) - r7) / (AndroidUtilities.displaySize.y / 2.0f), 1.0f, 0.0f));
        goto L92
    L5:
        if (r0 != 0) goto L7;
    L8:
        this.recordButton.setPressed(isPressed(r6, r7, this.cx, this.cy, AndroidUtilities.dp(60.0f), false));
        ButtonBounce r132 = this.flipButton;
        if (isPressed(r6, r7, this.rightCx, this.cy, AndroidUtilities.dp(30.0f), true) == true) goto L11;
    L13:
        boolean r8 = false;
    L14:
        r132.setPressed(r8);
        ButtonBounce r133 = this.lockButton;
        if (isPressed(r6, r7, this.leftCx, this.cy, AndroidUtilities.dp(30.0f), false) == true) goto L17;
    L19:
        boolean r4 = false;
    L20:
        r133.setPressed(r4);
        goto L21
    L17:
        if (hasCheck() == true) goto L19;
        r4 = true;
        goto L20
    L11:
        if (hasCheck() == true) goto L13;
        r8 = true;
        goto L14
    L7:
        if (this.touch == false) goto L21;
        goto L8
    }

    public void rotateFlip(float r4) {
        AnimatedFloat r0 = this.flipDrawableRotateT;
        if (r4 <= 180.0f) goto L5;
        long r1 = 620;
    L6:
        r0.setDuration(r1);
        this.flipDrawableRotate += r4;
        invalidate();
        return;
    L5:
        r1 = 310;
        goto L6
    }

    public void setAmplitude(float r1, boolean r2) {
        this.amplitude = r1;
        if (r2 == true) goto L6;
        this.animatedAmplitude.set(r1, true);
        return;
    }

    public void setCollageProgress(float r3, boolean r4) {
        if (Math.abs(r3 - this.collageProgress) >= 0.01f) goto L5;
        return;
    L5:
        this.collageProgress = r3;
        if (r4 == true) goto L14;
        AnimatedFloat r42 = this.collage;
        if (r3 > 0.0f) goto L10;
    L12:
        boolean r0 = false;
    L13:
        r42.set(r0, true);
        this.collageProgressAnimated.set(r3, true);
        goto L14
    L10:
        if (this.recording == true) goto L12;
        r0 = true;
    L14:
        invalidate();
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setDual(boolean r2) {
        if (r2 == this.dual) goto L6;
        this.dual = r2;
        invalidate();
        return;
    }

    @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void setInvert(float r7) {
        this.outlinePaint.setColor(ColorUtils.blendARGB(-1, -16777216, r7));
        this.buttonPaint.setColor(ColorUtils.blendARGB(1677721600, 369098752, r7));
        this.hintLinePaintWhite.setColor(ColorUtils.blendARGB(1493172223, 285212671, r7));
        this.hintLinePaintBlack.setColor(ColorUtils.blendARGB(402653184, 805306368, r7));
        Drawable r0 = this.flipDrawableWhite;
        int r4 = ColorUtils.blendARGB(-1, -16777216, r7);
        PorterDuff.Mode r5 = PorterDuff.Mode.MULTIPLY;
        r0.setColorFilter(new PorterDuffColorFilter(r4, r5));
        this.unlockDrawable.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-1, -16777216, r7), r5));
    }

    public void startAsVideo(boolean r2) {
        this.overrideStartModeIsVideoT = -1.0f;
        this.startModeIsVideo = r2;
        invalidate();
    }

    public void startAsVideoT(float r1) {
        this.overrideStartModeIsVideoT = r1;
        invalidate();
    }

    public void stopRecording() {
        if (this.recording == true) goto L5;
        return;
    L5:
        this.recording = false;
        this.recordingLoadingStart = SystemClock.elapsedRealtime();
        this.recordingLoading = true;
        this.delegate.onVideoRecordEnd(false);
        this.recordButton.setPressed(false);
        this.flipButton.setPressed(false);
        this.lockButton.setPressed(false);
        invalidate();
    }

    public void stopRecordingLoading(boolean r3) {
        this.recordingLoading = false;
        if (r3 == true) goto L5;
        this.recordingLoadingT.set(false, true);
    L5:
        invalidate();
    }

    public void updateGalleryImage() {
        Delegate r1 = this.delegate;
        if (r1 != null) goto L5;
    L14:
        MediaController.AlbumEntry r12 = MediaController.allMediaAlbumEntry;
        if (r12 == null) goto L21;
        ArrayList<MediaController.PhotoEntry> r4 = r12.photos;
        if (r4 == null) goto L21;
        if (r4.isEmpty() == true) goto L21;
        MediaController.PhotoEntry r13 = r12.photos.get(0);
    L22:
        if (r13 == null) goto L27;
        String r3 = r13.thumbPath;
        if (r3 == null) goto L27;
        this.galleryImage.setImage(ImageLocation.getForPath(r3), "80_80", null, null, this.noGalleryDrawable, 0, null, null, 0);
        return;
    L27:
        if (r13 != null) goto L29;
    L36:
        this.galleryImage.setImageBitmap(this.noGalleryDrawable);
        return;
    L29:
        if (r13.path == null) goto L36;
        boolean r32 = r13.isVideo;
        ImageReceiver r42 = this.galleryImage;
        if (r32 == false) goto L34;
        r42.setImage(ImageLocation.getForPath("vthumb://" + r13.imageId + ":" + r13.path), "80_80", null, null, this.noGalleryDrawable, 0, null, null, 0);
        return;
    L34:
        r42.setOrientation(r13.orientation, r13.invert, true);
        this.galleryImage.setImage(ImageLocation.getForPath("thumb://" + r13.imageId + ":" + r13.path), "80_80", null, null, this.noGalleryDrawable, 0, null, null, 0);
        return;
    L21:
        r13 = null;
        goto L22
    L5:
        if (r1.showStoriesDrafts() == false) goto L14;
        ArrayList<StoryEntry> r14 = MessagesController.getInstance(this.galleryImage.getCurrentAccount()).getStoriesController().getDraftsController().drafts;
        this.galleryImage.setOrientation(0, 0, true);
        if (r14 == null) goto L14;
        if (r14.isEmpty() == true) goto L14;
        if (r14.get(0).draftThumbFile == null) goto L14;
        this.galleryImage.setImage(ImageLocation.getForPath(r14.get(0).draftThumbFile.getAbsolutePath()), "80_80", null, null, this.noGalleryDrawable, 0, null, null, 0);
    }
}
