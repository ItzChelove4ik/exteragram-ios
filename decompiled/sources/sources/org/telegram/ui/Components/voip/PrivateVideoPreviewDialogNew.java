package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.media.projection.MediaProjectionManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.io.File;
import java.io.FileOutputStream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BitmapShaderTools;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.webrtc.RendererCommon;

@TargetApi(21)
/* loaded from: classes7.dex */
public abstract class PrivateVideoPreviewDialogNew extends FrameLayout implements VoIPService.StateListener {
    private ActionBar actionBar;
    private final MotionBackgroundDrawable bgBlueViolet;
    private final BitmapShaderTools bgBlueVioletShaderTools;
    private final MotionBackgroundDrawable bgGreen;
    private final BitmapShaderTools bgGreenShaderTools;
    private final Camera camera;
    private boolean cameraReady;
    private final Path clipPath;
    private float closeProgress;
    private boolean isDismissed;
    private final Matrix matrixLeft;
    private final Matrix matrixRight;
    private float openProgress1;
    private float openProgress2;
    private float openTranslationX;
    private float openTranslationY;
    private float pageOffset;
    private TextView positiveButton;
    private boolean positiveButtonDrawText;
    private int previousPage;
    private int realCurrentPage;
    private ValueAnimator scrollAnimator;
    private final GestureDetector scrollGestureDetector;
    private final float startLocationX;
    private final float startLocationY;
    private int strangeCurrentPage;
    private VoIPTextureView textureView;
    private VoIpBitmapTextView[] titles;
    private LinearLayout titlesLayout;
    private FrameLayout viewPager;
    private int visibleCameraPage;

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        private boolean lockDragging;
        private boolean startDragging;
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public static /* synthetic */ void $r8$lambda$kIQVMwY2spbhqOKeSZ1qd_Zojb0(AnonymousClass1 r0, float r1) {
            r0.lambda$onScroll$0(r1);
        }

        public AnonymousClass1(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScroll$0(float r3) {
            PrivateVideoPreviewDialogNew r0 = this.this$0;
            if (r3 <= 0.0f) goto L8;
            if (PrivateVideoPreviewDialogNew.m13880$$Nest$fgetrealCurrentPage(r0) >= 2) goto L10;
            PrivateVideoPreviewDialogNew r32 = this.this$0;
            PrivateVideoPreviewDialogNew.m13889$$Nest$msetCurrentPage(r32, PrivateVideoPreviewDialogNew.m13880$$Nest$fgetrealCurrentPage(r32) + 1, true);
        L10:
            this.lockDragging = false;
            return;
        L8:
            if (PrivateVideoPreviewDialogNew.m13880$$Nest$fgetrealCurrentPage(r0) <= 0) goto L10;
            PrivateVideoPreviewDialogNew r33 = this.this$0;
            PrivateVideoPreviewDialogNew.m13889$$Nest$msetCurrentPage(r33, PrivateVideoPreviewDialogNew.m13880$$Nest$fgetrealCurrentPage(r33) - 1, true);
            goto L10
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r2) {
            this.startDragging = true;
            return super.onDown(r2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r7, MotionEvent r8, float r9, float r10) {
            float r0 = r7.getX() - r8.getX();
            float r1 = r7.getY() - r8.getY();
            if (Math.abs(r0) <= AndroidUtilities.getPixelsInCM(0.4f, true)) goto L15;
            if ((Math.abs(r0) / 3.0f) <= r1) goto L15;
            if (this.startDragging == false) goto L15;
            if (this.lockDragging == true) goto L15;
            this.startDragging = false;
            PrivateVideoPreviewDialogNew$1$$ExternalSyntheticLambda0 r12 = new PrivateVideoPreviewDialogNew$1$$ExternalSyntheticLambda0(this, r0);
            if (PrivateVideoPreviewDialogNew.m13881$$Nest$fgetscrollAnimator(this.this$0) == null) goto L13;
            this.lockDragging = true;
            AndroidUtilities.runOnUIThread(r12, (PrivateVideoPreviewDialogNew.m13881$$Nest$fgetscrollAnimator(this.this$0).getDuration() - PrivateVideoPreviewDialogNew.m13881$$Nest$fgetscrollAnimator(this.this$0).getCurrentPlayTime()) + 50);
            goto L15
        L13:
            r12.run();
        L15:
            return super.onScroll(r7, r8, r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass10(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            if (this.this$0.getParent() == null) goto L6;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass11(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.this$0.getParent() == null) goto L6;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass2(PrivateVideoPreviewDialogNew r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r2) {
            PrivateVideoPreviewDialogNew.m13882$$Nest$fgetscrollGestureDetector(this.this$0).onTouchEvent(r2);
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass3(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.dismiss(false, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$4, reason: invalid class name */
    public class AnonymousClass4 extends TextView {
        private final Paint[] gradientPaint;
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;
        private final Paint whitePaint;

        public AnonymousClass4(PrivateVideoPreviewDialogNew r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            Paint r52 = new Paint();
            this.whitePaint = r52;
            this.gradientPaint = new Paint[PrivateVideoPreviewDialogNew.m13884$$Nest$fgettitles(r4).length];
            PrivateVideoPreviewDialogNew.m13870$$Nest$fgetbgGreen(r4).setBounds(0, 0, 80, 80);
            PrivateVideoPreviewDialogNew.m13868$$Nest$fgetbgBlueViolet(r4).setBounds(0, 0, 80, 80);
            PrivateVideoPreviewDialogNew.m13871$$Nest$fgetbgGreenShaderTools(r4).setBounds(0.0f, 0.0f, 80.0f, 80.0f);
            PrivateVideoPreviewDialogNew.m13869$$Nest$fgetbgBlueVioletShaderTools(r4).setBounds(0.0f, 0.0f, 80.0f, 80.0f);
            PrivateVideoPreviewDialogNew.m13870$$Nest$fgetbgGreen(r4).setAlpha(255);
            PrivateVideoPreviewDialogNew.m13868$$Nest$fgetbgBlueViolet(r4).setAlpha(255);
            Canvas r3 = PrivateVideoPreviewDialogNew.m13871$$Nest$fgetbgGreenShaderTools(r4).getCanvas();
            PorterDuff.Mode r1 = PorterDuff.Mode.CLEAR;
            r3.drawColor(0, r1);
            PrivateVideoPreviewDialogNew.m13869$$Nest$fgetbgBlueVioletShaderTools(r4).getCanvas().drawColor(0, r1);
            PrivateVideoPreviewDialogNew.m13870$$Nest$fgetbgGreen(r4).draw(PrivateVideoPreviewDialogNew.m13871$$Nest$fgetbgGreenShaderTools(r4).getCanvas());
            PrivateVideoPreviewDialogNew.m13868$$Nest$fgetbgBlueViolet(r4).draw(PrivateVideoPreviewDialogNew.m13869$$Nest$fgetbgBlueVioletShaderTools(r4).getCanvas());
            r52.setColor(-1);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r8) {
            PrivateVideoPreviewDialogNew.m13871$$Nest$fgetbgGreenShaderTools(this.this$0).setBounds(-getX(), -getY(), this.this$0.getWidth() - getX(), this.this$0.getHeight() - getY());
            PrivateVideoPreviewDialogNew.m13869$$Nest$fgetbgBlueVioletShaderTools(this.this$0).setBounds(-getX(), -getY(), this.this$0.getWidth() - getX(), this.this$0.getHeight() - getY());
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.gradientPaint[PrivateVideoPreviewDialogNew.m13883$$Nest$fgetstrangeCurrentPage(this.this$0)].setAlpha(255);
            float r1 = AndroidUtilities.dp(8.0f) + ((int) ((AndroidUtilities.dp(26.0f) - AndroidUtilities.dp(8.0f)) * (1.0f - PrivateVideoPreviewDialogNew.m13876$$Nest$fgetopenProgress1(this.this$0))));
            r8.drawRoundRect(r0, r1, r1, this.gradientPaint[PrivateVideoPreviewDialogNew.m13883$$Nest$fgetstrangeCurrentPage(this.this$0)]);
            if (PrivateVideoPreviewDialogNew.m13877$$Nest$fgetpageOffset(this.this$0) <= 0.0f) goto L8;
            int r2 = PrivateVideoPreviewDialogNew.m13883$$Nest$fgetstrangeCurrentPage(this.this$0) + 1;
            Paint[] r4 = this.gradientPaint;
            if (r2 >= r4.length) goto L8;
            r4[PrivateVideoPreviewDialogNew.m13883$$Nest$fgetstrangeCurrentPage(this.this$0) + 1].setAlpha((int) (PrivateVideoPreviewDialogNew.m13877$$Nest$fgetpageOffset(this.this$0) * 255.0f));
            r8.drawRoundRect(r0, r1, r1, this.gradientPaint[PrivateVideoPreviewDialogNew.m13883$$Nest$fgetstrangeCurrentPage(this.this$0) + 1]);
        L8:
            if (PrivateVideoPreviewDialogNew.m13876$$Nest$fgetopenProgress1(this.this$0) >= 1.0f) goto L10;
            this.whitePaint.setAlpha((int) ((1.0f - PrivateVideoPreviewDialogNew.m13876$$Nest$fgetopenProgress1(this.this$0)) * 255.0f));
            r8.drawRoundRect(r0, r1, r1, this.whitePaint);
        L10:
            super.onDraw(r8);
            if (PrivateVideoPreviewDialogNew.m13879$$Nest$fgetpositiveButtonDrawText(this.this$0) == false) goto L14;
            r8.drawText(LocaleController.getString(R.string.VoipShareVideo), getWidth() / 2, (int) ((getHeight() / 2) - ((PrivateVideoPreviewDialogNew.m13878$$Nest$fgetpositiveButton(this.this$0).getPaint().descent() + PrivateVideoPreviewDialogNew.m13878$$Nest$fgetpositiveButton(this.this$0).getPaint().ascent()) / 2.0f)), PrivateVideoPreviewDialogNew.m13878$$Nest$fgetpositiveButton(this.this$0).getPaint());
            return;
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            int r12 = 0;
        L3:
            Paint[] r22 = this.gradientPaint;
            if (r12 >= r22.length) goto L12;
            if (r12 != 0) goto L7;
            r22[r12] = PrivateVideoPreviewDialogNew.m13871$$Nest$fgetbgGreenShaderTools(this.this$0).paint;
        L11:
            r12 = r12 + 1;
            goto L3
        L7:
            PrivateVideoPreviewDialogNew r32 = this.this$0;
            if (r12 != 1) goto L10;
            r22[r12] = PrivateVideoPreviewDialogNew.m13869$$Nest$fgetbgBlueVioletShaderTools(r32).paint;
            goto L11
        L10:
            r22[r12] = PrivateVideoPreviewDialogNew.m13871$$Nest$fgetbgGreenShaderTools(r32).paint;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$5, reason: invalid class name */
    public class AnonymousClass5 extends LinearLayout {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass5(PrivateVideoPreviewDialogNew r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            int r0 = getWidth() / 2;
            int r1 = getHeight() / 2;
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).save();
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).rotateY(7.0f);
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).getMatrix(PrivateVideoPreviewDialogNew.m13875$$Nest$fgetmatrixRight(this.this$0));
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).restore();
            float r3 = -r0;
            float r4 = -r1;
            PrivateVideoPreviewDialogNew.m13875$$Nest$fgetmatrixRight(this.this$0).preTranslate(r3, r4);
            float r5 = r0;
            float r12 = r1;
            PrivateVideoPreviewDialogNew.m13875$$Nest$fgetmatrixRight(this.this$0).postTranslate(r5, r12);
            r9.save();
            r9.clipRect(r0, 0, getWidth(), getHeight());
            r9.concat(PrivateVideoPreviewDialogNew.m13875$$Nest$fgetmatrixRight(this.this$0));
            super.dispatchDraw(r9);
            r9.restore();
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).save();
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).rotateY(-7.0f);
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).getMatrix(PrivateVideoPreviewDialogNew.m13874$$Nest$fgetmatrixLeft(this.this$0));
            PrivateVideoPreviewDialogNew.m13872$$Nest$fgetcamera(this.this$0).restore();
            PrivateVideoPreviewDialogNew.m13874$$Nest$fgetmatrixLeft(this.this$0).preTranslate(r3, r4);
            PrivateVideoPreviewDialogNew.m13874$$Nest$fgetmatrixLeft(this.this$0).postTranslate(r5, r12);
            r9.save();
            r9.clipRect(0, 0, r0, getHeight());
            r9.concat(PrivateVideoPreviewDialogNew.m13874$$Nest$fgetmatrixLeft(this.this$0));
            super.dispatchDraw(r9);
            r9.restore();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$6, reason: invalid class name */
    public class AnonymousClass6 implements RendererCommon.RendererEvents {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass6(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass7(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (PrivateVideoPreviewDialogNew.m13873$$Nest$fgetisDismissed(this.this$0) == true) goto L6;
            this.this$0.afterOpened();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;
        final /* synthetic */ int val$position;

        public AnonymousClass8(PrivateVideoPreviewDialogNew r1, int r2) {
            this.this$0 = r1;
            this.val$position = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PrivateVideoPreviewDialogNew.m13886$$Nest$fputpreviousPage(this.this$0, -1);
            PrivateVideoPreviewDialogNew.m13888$$Nest$fputstrangeCurrentPage(this.this$0, this.val$position);
            PrivateVideoPreviewDialogNew.m13885$$Nest$fputpageOffset(this.this$0, 0.0f);
            PrivateVideoPreviewDialogNew.m13887$$Nest$fputscrollAnimator(this.this$0, null);
            PrivateVideoPreviewDialogNew.m13890$$Nest$mupdateTitlesLayout(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.PrivateVideoPreviewDialogNew$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ PrivateVideoPreviewDialogNew this$0;

        public AnonymousClass9(PrivateVideoPreviewDialogNew r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            if (this.this$0.getParent() == null) goto L6;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$0b7u4cR2RTkpu6cQ1ODX4Rx8NNI(PrivateVideoPreviewDialogNew r0, ValueAnimator r1) {
        r0.lambda$dismiss$7(r1);
    }

    /* renamed from: $r8$lambda$0dWo-jYed3Kcx_dDfvU1drKaEmw, reason: not valid java name */
    public static /* synthetic */ void m13865$r8$lambda$0dWojYed3Kcx_dDfvU1drKaEmw(PrivateVideoPreviewDialogNew r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$M3XMwffBkmvNHvHAyA3lUuJTi4s(PrivateVideoPreviewDialogNew r0, int r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$WPB_lfR41WpcJ6jtTo7K7msS-UU, reason: not valid java name */
    public static /* synthetic */ void m13866$r8$lambda$WPB_lfR41WpcJ6jtTo7K7msSUU(PrivateVideoPreviewDialogNew r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$__x5dGC7XlP5VGy7P5kURsO0DkM(PrivateVideoPreviewDialogNew r0, ValueAnimator r1) {
        r0.lambda$setCurrentPage$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$dBeJMlv52m__Fv0YiOxEkZ_klOk(PrivateVideoPreviewDialogNew r0, ValueAnimator r1) {
        r0.lambda$dismiss$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$t5w2EEqtvJXtGdoJ4z81bzVca6M(PrivateVideoPreviewDialogNew r0, ValueAnimator r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$ylbO6R0O9f4Fdv-VIKMlQbg-OCI, reason: not valid java name */
    public static /* synthetic */ void m13867$r8$lambda$ylbO6R0O9f4FdvVIKMlQbgOCI(PrivateVideoPreviewDialogNew r0, ValueAnimator r1) {
        r0.lambda$dismiss$5(r1);
    }

    /* renamed from: -$$Nest$fgetbgBlueViolet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MotionBackgroundDrawable m13868$$Nest$fgetbgBlueViolet(PrivateVideoPreviewDialogNew r0) {
        return r0.bgBlueViolet;
    }

    /* renamed from: -$$Nest$fgetbgBlueVioletShaderTools, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShaderTools m13869$$Nest$fgetbgBlueVioletShaderTools(PrivateVideoPreviewDialogNew r0) {
        return r0.bgBlueVioletShaderTools;
    }

    /* renamed from: -$$Nest$fgetbgGreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MotionBackgroundDrawable m13870$$Nest$fgetbgGreen(PrivateVideoPreviewDialogNew r0) {
        return r0.bgGreen;
    }

    /* renamed from: -$$Nest$fgetbgGreenShaderTools, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShaderTools m13871$$Nest$fgetbgGreenShaderTools(PrivateVideoPreviewDialogNew r0) {
        return r0.bgGreenShaderTools;
    }

    /* renamed from: -$$Nest$fgetcamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Camera m13872$$Nest$fgetcamera(PrivateVideoPreviewDialogNew r0) {
        return r0.camera;
    }

    /* renamed from: -$$Nest$fgetisDismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13873$$Nest$fgetisDismissed(PrivateVideoPreviewDialogNew r0) {
        return r0.isDismissed;
    }

    /* renamed from: -$$Nest$fgetmatrixLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m13874$$Nest$fgetmatrixLeft(PrivateVideoPreviewDialogNew r0) {
        return r0.matrixLeft;
    }

    /* renamed from: -$$Nest$fgetmatrixRight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m13875$$Nest$fgetmatrixRight(PrivateVideoPreviewDialogNew r0) {
        return r0.matrixRight;
    }

    /* renamed from: -$$Nest$fgetopenProgress1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13876$$Nest$fgetopenProgress1(PrivateVideoPreviewDialogNew r0) {
        return r0.openProgress1;
    }

    /* renamed from: -$$Nest$fgetpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13877$$Nest$fgetpageOffset(PrivateVideoPreviewDialogNew r0) {
        return r0.pageOffset;
    }

    /* renamed from: -$$Nest$fgetpositiveButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m13878$$Nest$fgetpositiveButton(PrivateVideoPreviewDialogNew r0) {
        return r0.positiveButton;
    }

    /* renamed from: -$$Nest$fgetpositiveButtonDrawText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13879$$Nest$fgetpositiveButtonDrawText(PrivateVideoPreviewDialogNew r0) {
        return r0.positiveButtonDrawText;
    }

    /* renamed from: -$$Nest$fgetrealCurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13880$$Nest$fgetrealCurrentPage(PrivateVideoPreviewDialogNew r0) {
        return r0.realCurrentPage;
    }

    /* renamed from: -$$Nest$fgetscrollAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m13881$$Nest$fgetscrollAnimator(PrivateVideoPreviewDialogNew r0) {
        return r0.scrollAnimator;
    }

    /* renamed from: -$$Nest$fgetscrollGestureDetector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GestureDetector m13882$$Nest$fgetscrollGestureDetector(PrivateVideoPreviewDialogNew r0) {
        return r0.scrollGestureDetector;
    }

    /* renamed from: -$$Nest$fgetstrangeCurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13883$$Nest$fgetstrangeCurrentPage(PrivateVideoPreviewDialogNew r0) {
        return r0.strangeCurrentPage;
    }

    /* renamed from: -$$Nest$fgettitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIpBitmapTextView[] m13884$$Nest$fgettitles(PrivateVideoPreviewDialogNew r0) {
        return r0.titles;
    }

    /* renamed from: -$$Nest$fputpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13885$$Nest$fputpageOffset(PrivateVideoPreviewDialogNew r0, float r1) {
        r0.pageOffset = r1;
    }

    /* renamed from: -$$Nest$fputpreviousPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13886$$Nest$fputpreviousPage(PrivateVideoPreviewDialogNew r0, int r1) {
        r0.previousPage = r1;
    }

    /* renamed from: -$$Nest$fputscrollAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13887$$Nest$fputscrollAnimator(PrivateVideoPreviewDialogNew r0, ValueAnimator r1) {
        r0.scrollAnimator = r1;
    }

    /* renamed from: -$$Nest$fputstrangeCurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13888$$Nest$fputstrangeCurrentPage(PrivateVideoPreviewDialogNew r0, int r1) {
        r0.strangeCurrentPage = r1;
    }

    /* renamed from: -$$Nest$msetCurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13889$$Nest$msetCurrentPage(PrivateVideoPreviewDialogNew r0, int r1, boolean r2) {
        r0.setCurrentPage(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateTitlesLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13890$$Nest$mupdateTitlesLayout(PrivateVideoPreviewDialogNew r0) {
        r0.updateTitlesLayout();
    }

    public PrivateVideoPreviewDialogNew(Context r20, float r21, float r22) {
        super(r20);
        this.visibleCameraPage = 1;
        this.previousPage = -1;
        this.openProgress1 = 0.0f;
        this.openProgress2 = 0.0f;
        this.closeProgress = 0.0f;
        this.clipPath = new Path();
        this.camera = new Camera();
        this.matrixRight = new Matrix();
        this.matrixLeft = new Matrix();
        this.bgGreenShaderTools = new BitmapShaderTools(80, 80);
        this.bgBlueVioletShaderTools = new BitmapShaderTools(80, 80);
        this.bgGreen = new MotionBackgroundDrawable(-10497967, -16730994, -5649306, -10833593, 0, false, true);
        this.bgBlueViolet = new MotionBackgroundDrawable(-16735258, -14061833, -15151390, -12602625, 0, false, true);
        this.startLocationX = r21;
        this.startLocationY = r22;
        this.titles = new VoIpBitmapTextView[3];
        this.scrollGestureDetector = new GestureDetector(r20, new AnonymousClass1(this));
        AnonymousClass2 r7 = new AnonymousClass2(this, r20);
        this.viewPager = r7;
        r7.setClickable(true);
        addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f));
        VoIPTextureView r72 = new VoIPTextureView(r20, false, false);
        this.textureView = r72;
        r72.renderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FILL);
        VoIPTextureView r73 = this.textureView;
        r73.scaleType = VoIPTextureView.SCALE_TYPE_FIT;
        r73.clipToTexture = true;
        r73.renderer.setAlpha(0.0f);
        this.textureView.renderer.setRotateTextureWithScreen(true);
        this.textureView.renderer.setUseCameraRotation(true);
        addView(this.textureView, LayoutHelper.createFrame(-1, -1.0f));
        ActionBar r74 = new ActionBar(r20);
        this.actionBar = r74;
        r74.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setBackgroundColor(0);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_voipgroup_actionBarItems), false);
        this.actionBar.setOccupyStatusBar(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass3(this));
        addView(this.actionBar);
        AnonymousClass4 r75 = new AnonymousClass4(this, getContext());
        this.positiveButton = r75;
        r75.setMaxLines(1);
        this.positiveButton.setEllipsize(null);
        this.positiveButton.setMinWidth(AndroidUtilities.dp(64.0f));
        this.positiveButton.setTag(-1);
        this.positiveButton.setTextSize(1, 14.0f);
        TextView r76 = this.positiveButton;
        int r9 = Theme.key_voipgroup_nameText;
        r76.setTextColor(Theme.getColor(r9));
        this.positiveButton.setGravity(17);
        this.positiveButton.setTypeface(AndroidUtilities.bold());
        this.positiveButton.getPaint().setTextAlign(Paint.Align.CENTER);
        this.positiveButton.setContentDescription(LocaleController.getString(R.string.VoipShareVideo));
        this.positiveButton.setForeground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r9), 76)));
        this.positiveButton.setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f));
        this.positiveButton.setOnClickListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda0(this));
        addView(this.positiveButton, LayoutHelper.createFrame(52, 52.0f, 81, 0.0f, 0.0f, 0.0f, 80.0f));
        AnonymousClass5 r77 = new AnonymousClass5(this, r20);
        this.titlesLayout = r77;
        r77.setClipChildren(false);
        addView(this.titlesLayout, LayoutHelper.createFrame(-1, 64, 80));
        int r78 = 0;
    L4:
        if (r78 >= this.titles.length) goto L11;
        if (r78 != 0) goto L7;
        String r8 = LocaleController.getString(R.string.VoipPhoneScreen);
    L10:
        this.titles[r78] = new VoIpBitmapTextView(r20, r8);
        this.titles[r78].setContentDescription(r8);
        this.titles[r78].setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(10.0f), 0);
        this.titlesLayout.addView(this.titles[r78], LayoutHelper.createLinear(-2, -1));
        this.titles[r78].setOnClickListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda1(this, r78));
        r78 = r78 + 1;
        goto L4
    L7:
        if (r78 != 1) goto L9;
        r8 = LocaleController.getString(R.string.VoipFrontCamera);
        goto L10
    L9:
        r8 = LocaleController.getString(R.string.VoipBackCamera);
        goto L10
    L11:
        setWillNotDraw(false);
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r1 == null) goto L14;
        this.textureView.renderer.setMirror(r1.isFrontFaceCamera());
        this.textureView.renderer.init(VideoCapturerDevice.getEglBase().getEglBaseContext(), new AnonymousClass6(this));
        r1.setLocalSink(this.textureView.renderer, false);
    L14:
        createPages(this.viewPager);
        ValueAnimator r5 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r5.addUpdateListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda2(this, r21, r22));
        r5.addListener(new AnonymousClass7(this));
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r12.addUpdateListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda3(this));
        CubicBezierInterpolator r3 = CubicBezierInterpolator.DEFAULT;
        r5.setInterpolator(r3);
        r5.setDuration(320);
        r5.start();
        r12.setInterpolator(r3);
        r12.setDuration(320);
        r12.setStartDelay(32);
        r12.start();
        this.titlesLayout.setAlpha(0.0f);
        this.titlesLayout.setScaleY(0.8f);
        this.titlesLayout.setScaleX(0.8f);
        this.titlesLayout.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setStartDelay(120).setDuration(250).start();
        this.positiveButton.setTranslationY(AndroidUtilities.dp(53.0f));
        this.positiveButton.setTranslationX(((r21 - (AndroidUtilities.displaySize.x / 2.0f)) + AndroidUtilities.dp(8.0f)) + AndroidUtilities.dp(26.0f));
        this.positiveButton.animate().translationY(0.0f).translationX(0.0f).setDuration(320).start();
        this.positiveButtonDrawText = true;
        setCurrentPage(1, false);
    }

    private void createPages(FrameLayout r11) {
        FrameLayout r0 = new FrameLayout(getContext());
        r0.setBackground(new MotionBackgroundDrawable(-14602694, -13935795, -14395293, -14203560, true));
        ImageView r1 = new ImageView(getContext());
        r1.setScaleType(ImageView.ScaleType.CENTER);
        r1.setImageResource(R.drawable.screencast_big);
        r0.addView(r1, LayoutHelper.createFrame(82, 82.0f, 17, 0.0f, 0.0f, 0.0f, 60.0f));
        TextView r12 = new TextView(getContext());
        r12.setText(LocaleController.getString(R.string.VoipVideoPrivateScreenSharing));
        r12.setGravity(17);
        r12.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r12.setTextColor(-1);
        r12.setTextSize(1, 15.0f);
        r12.setTypeface(AndroidUtilities.bold());
        r0.addView(r12, LayoutHelper.createFrame(-1, -2.0f, 17, 21.0f, 28.0f, 21.0f, 0.0f));
        r0.setTag("screencast_stub");
        r0.setVisibility(8);
        r11.addView(r0);
        ImageView r02 = new ImageView(getContext());
        r02.setTag("image_stab");
        r02.setImageResource(R.drawable.icplaceholder);
        r02.setScaleType(ImageView.ScaleType.FIT_XY);
        r11.addView(r02);
    }

    private /* synthetic */ void lambda$dismiss$5(ValueAnimator r1) {
        this.closeProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$dismiss$6(ValueAnimator r4) {
        this.openProgress1 = ((Float) r4.getAnimatedValue()).floatValue();
        float r42 = this.startLocationX + AndroidUtilities.dp(28.0f);
        float r0 = this.startLocationY + AndroidUtilities.dp(52.0f);
        float r1 = this.openProgress1;
        this.openTranslationX = r42 - (r42 * r1);
        this.openTranslationY = r0 - (r1 * r0);
        invalidate();
    }

    private /* synthetic */ void lambda$dismiss$7(ValueAnimator r4) {
        this.openProgress2 = ((Float) r4.getAnimatedValue()).floatValue();
        int r42 = (AndroidUtilities.displaySize.x - AndroidUtilities.dp(36.0f)) - AndroidUtilities.dp(52.0f);
        this.positiveButton.getLayoutParams().width = AndroidUtilities.dp(52.0f) + ((int) (r42 * this.openProgress2));
        this.positiveButton.requestLayout();
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        if (this.isDismissed == false) goto L6;
        return;
    L6:
        if (this.realCurrentPage != 0) goto L9;
        MediaProjectionManager r22 = (MediaProjectionManager) getContext().getSystemService("media_projection");
        ((Activity) getContext()).startActivityForResult(r22.createScreenCaptureIntent(), 520);
        return;
    L9:
        dismiss(false, true);
    }

    private /* synthetic */ void lambda$new$1(int r2, View r3) {
        if (this.scrollAnimator == null) goto L5;
        return;
    L5:
        if (r3.getAlpha() == 0.0f) goto L10;
        setCurrentPage(r2, true);
        return;
    }

    private /* synthetic */ void lambda$new$2(float r2, float r3, ValueAnimator r4) {
        this.openProgress1 = ((Float) r4.getAnimatedValue()).floatValue();
        float r22 = r2 + AndroidUtilities.dp(28.0f);
        float r32 = r3 + AndroidUtilities.dp(52.0f);
        float r42 = this.openProgress1;
        this.openTranslationX = r22 - (r22 * r42);
        this.openTranslationY = r32 - (r42 * r32);
        invalidate();
    }

    private /* synthetic */ void lambda$new$3(ValueAnimator r4) {
        this.openProgress2 = ((Float) r4.getAnimatedValue()).floatValue();
        int r42 = (AndroidUtilities.displaySize.x - AndroidUtilities.dp(36.0f)) - AndroidUtilities.dp(52.0f);
        this.positiveButton.getLayoutParams().width = AndroidUtilities.dp(52.0f) + ((int) (r42 * this.openProgress2));
        this.positiveButton.requestLayout();
    }

    private /* synthetic */ void lambda$setCurrentPage$4(ValueAnimator r1) {
        this.pageOffset = ((Float) r1.getAnimatedValue()).floatValue();
        updateTitlesLayout();
    }

    private void saveLastCameraBitmap() {
        if (this.cameraReady == false) goto L21;
        Bitmap r2 = this.textureView.renderer.getBitmap();     // Catch: Throwable -> L15
        if (r2 == null) goto L18;
        Bitmap r1 = Bitmap.createBitmap(r2, 0, 0, r2.getWidth(), r2.getHeight(), this.textureView.renderer.getMatrix(), true);     // Catch: Throwable -> L15
        r2.recycle();     // Catch: Throwable -> L15
        Bitmap r22 = Bitmap.createScaledBitmap(r1, 80, (int) (r1.getHeight() / (r1.getWidth() / 80.0f)), true);     // Catch: Throwable -> L15
        if (r22 == null) goto L19;
        if (r22 == r1) goto L11;
        r1.recycle();     // Catch: Throwable -> L15
    L11:
        Utilities.blurBitmap(r22, 7);     // Catch: Throwable -> L15
        FileOutputStream r0 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "cthumb" + this.visibleCameraPage + ".jpg"));     // Catch: Throwable -> L15
        r22.compress(Bitmap.CompressFormat.JPEG, 87, r0);     // Catch: Throwable -> L15
        r0.close();     // Catch: Throwable -> L15
        View r9 = this.viewPager.findViewWithTag("image_stab");     // Catch: Throwable -> L15
        if ((r9 instanceof ImageView) == false) goto L20;
        ((ImageView) r9).setImageBitmap(r22);     // Catch: Throwable -> L15
        return;
    L20:
        return;
    L19:
        return;
    L18:
        return;
    L22:
        return;
    }

    private void setCurrentPage(int r7, boolean r8) {
        if (this.strangeCurrentPage == r7) goto L30;
        int r0 = this.realCurrentPage;
        if (r0 != r7) goto L8;
        return;
    L8:
        if (r8 == true) goto L10;
        this.realCurrentPage = r7;
        this.strangeCurrentPage = r7;
        this.pageOffset = 0.0f;
        updateTitlesLayout();
        this.textureView.setVisibility(0);
        this.cameraReady = false;
        this.visibleCameraPage = 1;
        showStub(true, false);
        return;
    L10:
        if (r0 == 0) goto L12;
        if (r7 != 0) goto L19;
        this.viewPager.findViewWithTag("screencast_stub").setVisibility(0);
        saveLastCameraBitmap();
        showStub(false, false);
        this.textureView.animate().alpha(0.0f).setDuration(250).start();
    L22:
        int r82 = this.realCurrentPage;
        if (r7 <= r82) goto L25;
        this.previousPage = r82;
        this.realCurrentPage = r82 + 1;
        this.scrollAnimator = ValueAnimator.ofFloat(new float[]{0.1f, 1.0f});
    L26:
        this.scrollAnimator.addUpdateListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda7(this));
        this.scrollAnimator.addListener(new AnonymousClass8(this, r7));
        this.scrollAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.scrollAnimator.setDuration(350);
        this.scrollAnimator.start();
        return;
    L25:
        this.previousPage = r82;
        this.realCurrentPage = r82 - 1;
        this.strangeCurrentPage = r7;
        this.scrollAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        goto L26
    L19:
        saveLastCameraBitmap();
        this.visibleCameraPage = r7;
        this.cameraReady = false;
        showStub(true, false);
        this.textureView.animate().alpha(0.0f).setDuration(250).start();
        if (VoIPService.getSharedInstance() == null) goto L22;
        VoIPService.getSharedInstance().switchCamera();
        goto L22
    L12:
        if (this.visibleCameraPage == r7) goto L16;
        this.visibleCameraPage = r7;
        this.cameraReady = false;
        showStub(true, true);
        if (VoIPService.getSharedInstance() == null) goto L22;
        VoIPService.getSharedInstance().switchCamera();
        goto L22
    L16:
        showStub(false, false);
        this.textureView.animate().alpha(1.0f).setDuration(250).start();
        goto L22
    }

    private void showStub(boolean r5, boolean r6) {
        ImageView r1 = (ImageView) this.viewPager.findViewWithTag("image_stab");
        if (r5 == true) goto L20;
        r1.setVisibility(8);
        return;
    L20:
        Bitmap r4 = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "cthumb" + this.visibleCameraPage + ".jpg").getAbsolutePath());     // Catch: Throwable -> L8
    L10:
        if (r4 != null) goto L12;
    L14:
        r1.setImageResource(R.drawable.icplaceholder);
    L16:
        if (r6 == false) goto L18;
        r1.setVisibility(0);
        r1.setAlpha(0.0f);
        r1.animate().alpha(1.0f).setDuration(250).start();
        return;
    L18:
        r1.setAlpha(1.0f);
        r1.setVisibility(0);
        return;
    L12:
        if (r4.getPixel(0, 0) == 0) goto L14;
        r1.setImageBitmap(r4);
    L8:
        r4 = null;
        goto L10
    }

    private void updateTitlesLayout() {
        VoIpBitmapTextView[] r0 = this.titles;
        int r1 = this.strangeCurrentPage;
        VoIpBitmapTextView r2 = r0[r1];
        if (r1 >= (r0.length - 1)) goto L5;
        VoIpBitmapTextView r02 = r0[r1 + 1];
    L6:
        float r12 = r2.getLeft() + (r2.getMeasuredWidth() / 2);
        float r22 = (getMeasuredWidth() / 2) - r12;
        if (r02 == null) goto L9;
        r22 = r22 - (((r02.getLeft() + (r02.getMeasuredWidth() / 2)) - r12) * this.pageOffset);
    L9:
        int r13 = 0;
    L10:
        VoIpBitmapTextView[] r5 = this.titles;
        float r8 = 0.7f;
        if (r13 >= r5.length) goto L22;
        int r6 = this.strangeCurrentPage;
        float r9 = 0.9f;
        if (r13 < r6) goto L21;
        if (r13 > (r6 + 1)) goto L21;
        float r10 = this.pageOffset;
        if (r13 != r6) goto L20;
        r8 = 1.0f - (0.3f * r10);
        r9 = 1.0f - (r10 * 0.1f);
        goto L21
    L20:
        r8 = 0.7f + (0.3f * r10);
        r9 = 0.9f + (r10 * 0.1f);
    L21:
        r5[r13].setAlpha(r8);
        this.titles[r13].setScaleX(r9);
        this.titles[r13].setScaleY(r9);
        this.titles[r13].setTranslationX(r22);
        r13 = r13 + 1;
        goto L10
    L22:
        this.positiveButton.invalidate();
        if (this.realCurrentPage != 0) goto L26;
        this.titles[2].setAlpha(this.pageOffset * 0.7f);
    L26:
        if (this.realCurrentPage != 2) goto L32;
        float r14 = this.pageOffset;
        VoIpBitmapTextView[] r62 = this.titles;
        if (r14 <= 0.0f) goto L30;
        r62[0].setAlpha((1.0f - r14) * 0.7f);
        goto L32
    L30:
        r62[0].setAlpha(0.0f);
    L32:
        if (this.realCurrentPage == 1) goto L34;
        return;
    L34:
        if (this.previousPage != 0) goto L37;
        this.titles[2].setAlpha(this.pageOffset * 0.7f);
    L37:
        if (this.previousPage != 2) goto L46;
        this.titles[0].setAlpha((1.0f - this.pageOffset) * 0.7f);
        return;
    L46:
        return;
    L5:
        r02 = null;
        goto L6
    }

    public abstract void afterOpened();

    public abstract void beforeClosed();

    public void dismiss(boolean r8, boolean r9) {
        if (this.isDismissed == false) goto L5;
        return;
    L5:
        if (this.openProgress1 != 1.0f) goto L17;
        beforeClosed();
        this.isDismissed = true;
        saveLastCameraBitmap();
        onDismiss(r8, r9);
        if (isHasVideoOnMainScreen() == false) goto L11;
        if (r9 == false) goto L11;
        ValueAnimator r82 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r82.addUpdateListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda4(this));
        r82.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r82.setStartDelay(60);
        r82.setDuration(350);
        r82.addListener(new AnonymousClass9(this));
        r82.start();
        this.positiveButton.animate().setStartDelay(60).alpha(0.0f).setDuration(100).start();
        this.actionBar.animate().setStartDelay(60).alpha(0.0f).setDuration(100).start();
        this.titlesLayout.animate().setStartDelay(60).alpha(0.0f).setDuration(100).start();
    L14:
        invalidate();
        return;
    L11:
        if (r9 == false) goto L13;
        animate().setStartDelay(60).alpha(0.0f).setDuration(350).setInterpolator(CubicBezierInterpolator.DEFAULT).setListener(new AnonymousClass10(this));
        goto L14
    L13:
        ValueAnimator r83 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r83.addUpdateListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda5(this));
        r83.addListener(new AnonymousClass11(this));
        ValueAnimator r92 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r92.addUpdateListener(new PrivateVideoPreviewDialogNew$$ExternalSyntheticLambda6(this));
        CubicBezierInterpolator r0 = CubicBezierInterpolator.DEFAULT;
        r83.setInterpolator(r0);
        r83.setDuration(320);
        r83.start();
        r92.setInterpolator(r0);
        r92.setDuration(320);
        r92.start();
        this.titlesLayout.setAlpha(1.0f);
        this.titlesLayout.setScaleY(1.0f);
        this.titlesLayout.setScaleX(1.0f);
        this.titlesLayout.animate().alpha(0.0f).scaleX(0.8f).scaleY(0.8f).setDuration(250).start();
        this.positiveButton.animate().translationY(AndroidUtilities.dp(53.0f)).translationX(((this.startLocationX - (AndroidUtilities.displaySize.x / 2.0f)) + AndroidUtilities.dp(8.0f)) + AndroidUtilities.dp(26.0f)).setDuration(192).start();
        animate().alpha(0.0f).setDuration(80).setStartDelay(240).start();
        goto L14
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r18) {
        if (this.openProgress1 >= 1.0f) goto L6;
        Point r2 = AndroidUtilities.displaySize;
        int r4 = r2.x;
        int r22 = (r2.y + AndroidUtilities.statusBarHeight) + AndroidUtilities.navigationBarHeight;
        float r12 = AndroidUtilities.dp(28.0f) - (AndroidUtilities.dp(28.0f) * this.openProgress1);
        this.clipPath.reset();
        Path r5 = this.clipPath;
        float r6 = this.startLocationX + AndroidUtilities.dp(33.5f);
        float r7 = this.startLocationY + AndroidUtilities.dp(26.6f);
        float r8 = AndroidUtilities.dp(26.0f);
        Path.Direction r14 = Path.Direction.CW;
        r5.addCircle(r6, r7, r8, r14);
        int r62 = AndroidUtilities.dp(52.0f);
        int r52 = AndroidUtilities.dp(52.0f);
        int r42 = AndroidUtilities.lerp(r62, r4, this.openProgress1);
        int r23 = AndroidUtilities.lerp(r52, r22, this.openProgress1);
        float r82 = this.openTranslationX - ((1.0f - this.openProgress1) * AndroidUtilities.dp(20.0f));
        float r9 = this.openTranslationY - ((1.0f - this.openProgress1) * AndroidUtilities.dp(51.0f));
        this.clipPath.addRoundRect(r82, r9, r82 + r42, r9 + r23, r12, r12, r14);
        r18.clipPath(this.clipPath);
    L6:
        if (this.closeProgress <= 0.0f) goto L8;
        int[] r24 = getFloatingViewLocation();
        float r43 = this.closeProgress;
        int r53 = (int) (r24[0] * r43);
        int r63 = (int) (r24[1] * r43);
        int r25 = r24[2];
        float r83 = (r25 + ((r7 - r25) * (1.0f - r43))) / AndroidUtilities.displaySize.x;
        this.clipPath.reset();
        this.clipPath.addRoundRect(0.0f, 0.0f, getWidth() * r83, getHeight() * r83, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Path.Direction.CW);
        r18.translate(r53, r63);
        r18.clipPath(this.clipPath);
        r18.scale(r83, r83);
    L8:
        super.dispatchDraw(r18);
    }

    public int[] getFloatingViewLocation() {
        return null;
    }

    public abstract boolean isHasVideoOnMainScreen();

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L6;
        r0.registerStateListener(this);
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraFirstFrameAvailable() {
        if (this.cameraReady == true) goto L8;
        this.cameraReady = true;
        if (this.realCurrentPage == 0) goto L9;
        this.textureView.animate().alpha(1.0f).setDuration(250).start();
        return;
    L9:
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraSwitch(boolean r1) {
        update();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L6;
        r0.unregisterStateListener(this);
        return;
    }

    public abstract void onDismiss(boolean r1, boolean r2);

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateTitlesLayout();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        super.onMeasure(r7, r8);
        measureChildWithMargins(this.titlesLayout, View.MeasureSpec.makeMeasureSpec(0, 0), 0, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30), 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return true;
    }

    public void setBottomPadding(int r3) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.positiveButton.getLayoutParams();
        r0.bottomMargin = AndroidUtilities.dp(80.0f) + r3;
        ((FrameLayout.LayoutParams) this.titlesLayout.getLayoutParams()).bottomMargin = r3;
    }

    public void update() {
        if (VoIPService.getSharedInstance() == null) goto L6;
        this.textureView.renderer.setMirror(VoIPService.getSharedInstance().isFrontFaceCamera());
        return;
    }
}
