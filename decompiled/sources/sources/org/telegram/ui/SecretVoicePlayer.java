package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.File;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AudioVisualizerDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EarListener;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SeekBarWaveform;
import org.telegram.ui.Components.ThanosEffect;
import org.telegram.ui.Components.TimerParticles;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class SecretVoicePlayer extends Dialog {
    private AudioVisualizerDrawable audioVisualizerDrawable;
    private AlertDialog backDialog;
    private Bitmap blurBitmap;
    private Paint blurBitmapPaint;
    private BitmapShader blurBitmapShader;
    private Matrix blurMatrix;
    private ChatMessageCell cell;
    private Runnable checkTimeRunnable;
    private float clipBottom;
    private float clipTop;
    private Runnable closeAction;
    private TextView closeButton;
    private FrameLayout containerView;
    public final Context context;
    private boolean dismissing;
    private float dtx;
    private float dty;
    private EarListener earListener;
    private boolean hasDestTranslation;
    private boolean hasTranslation;
    private float heightdiff;
    private HintView2 hintView;
    private Insets insets;
    private boolean isRound;
    private MessageObject messageObject;
    private ChatMessageCell myCell;
    private boolean open;
    private ValueAnimator open2Animator;
    private Runnable openAction;
    private ValueAnimator openAnimator;
    private float openProgress;
    private float openProgress2;
    private VideoPlayer player;
    private float progress;
    private final RectF rect;
    private boolean renderedFirstFrame;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean setCellInvisible;
    private TextureView textureView;
    private ThanosEffect thanosEffect;
    private float tx;
    private float ty;
    private FrameLayout windowView;

    /* renamed from: org.telegram.ui.SecretVoicePlayer$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ SecretVoicePlayer this$0;

        public AnonymousClass1(SecretVoicePlayer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L5;
        L7:
            Canvas r1 = r8;
        L9:
            if (SecretVoicePlayer.m18603$$Nest$fgetsetCellInvisible(this.this$0) == true) goto L11;
        L13:
            super.dispatchDraw(r1);
            return;
        L11:
            if (SecretVoicePlayer.m18588$$Nest$fgetcell(this.this$0) == null) goto L13;
            SecretVoicePlayer.m18588$$Nest$fgetcell(this.this$0).setVisibility(4);
            SecretVoicePlayer.m18609$$Nest$fputsetCellInvisible(this.this$0, false);
            goto L13
        L5:
            if (SecretVoicePlayer.m18585$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L7;
            SecretVoicePlayer.m18587$$Nest$fgetblurMatrix(this.this$0).reset();
            float r0 = getWidth() / SecretVoicePlayer.m18584$$Nest$fgetblurBitmap(this.this$0).getWidth();
            SecretVoicePlayer.m18587$$Nest$fgetblurMatrix(this.this$0).postScale(r0, r0);
            SecretVoicePlayer.m18586$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(SecretVoicePlayer.m18587$$Nest$fgetblurMatrix(this.this$0));
            SecretVoicePlayer.m18585$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r1 = r8;
            r1.drawRect(0.0f, 0.0f, getWidth(), getHeight(), SecretVoicePlayer.m18585$$Nest$fgetblurBitmapPaint(this.this$0));
            goto L9
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEventPreIme(KeyEvent r3) {
            if (r3 == null) goto L10;
            if (r3.getKeyCode() != 4) goto L10;
            if (r3.getAction() != 1) goto L10;
            this.this$0.dismiss();
            return true;
        L10:
            return super.dispatchKeyEventPreIme(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            SecretVoicePlayer.m18610$$Nest$msetupTranslation(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        private final Path clipPath;
        final /* synthetic */ SecretVoicePlayer this$0;

        public AnonymousClass2(SecretVoicePlayer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.clipPath = new Path();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (r8 != SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0)) goto L5;
        L13:
            r7.save();
            r7.clipRect(0.0f, AndroidUtilities.lerp(SecretVoicePlayer.m18591$$Nest$fgetclipTop(this.this$0), 0.0f, SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)), getWidth(), AndroidUtilities.lerp(SecretVoicePlayer.m18590$$Nest$fgetclipBottom(this.this$0), getHeight(), SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)));
            boolean r6 = super.drawChild(r7, r8, r9);
            r7.restore();
            return r6;
        L5:
            if (r8 == SecretVoicePlayer.m18594$$Nest$fgethintView(this.this$0)) goto L13;
            if (r8 != SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0)) goto L12;
            r7.save();
            this.clipPath.rewind();
            this.clipPath.addCircle(SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).getX() + SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerX(), SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).getY() + SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerY(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width() / 2.0f, Path.Direction.CW);
            r7.clipPath(this.clipPath);
            r7.clipRect(0.0f, AndroidUtilities.lerp(SecretVoicePlayer.m18591$$Nest$fgetclipTop(this.this$0), 0.0f, SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)), getWidth(), AndroidUtilities.lerp(SecretVoicePlayer.m18590$$Nest$fgetclipBottom(this.this$0), getHeight(), SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)));
            r7.translate(-SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).getX(), -SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).getY());
            r7.translate(SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).getX() + SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).left, SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).getY() + SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).top);
            r7.scale(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width() / SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).getMeasuredWidth(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).height() / SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).getMeasuredHeight(), SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).getX(), SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).getY());
            boolean r62 = super.drawChild(r7, r8, r9);
            r7.restore();
            return r62;
        L12:
            return super.drawChild(r7, r8, r9);
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$3, reason: invalid class name */
    public class AnonymousClass3 extends ChatMessageCell {
        private Paint clipPaint;
        private Path clipPath;
        final RectF fromRect;
        private Paint progressPaint;
        private RadialGradient radialGradient;
        private Matrix radialMatrix;
        private Paint radialPaint;
        private AnimatedFloat renderedFirstFrameT;
        private boolean setRect;
        final /* synthetic */ SecretVoicePlayer this$0;
        private TimerParticles timerParticles;
        final RectF toRect;
        final /* synthetic */ int val$finalHeight;
        final /* synthetic */ int val$finalWidth;

        public AnonymousClass3(SecretVoicePlayer r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6, int r7, int r8) {
            this.this$0 = r1;
            this.val$finalWidth = r7;
            this.val$finalHeight = r8;
            super(r2, r3, r4, r5, r6);
            this.setRect = false;
            this.fromRect = new RectF();
            this.toRect = new RectF();
            this.clipPath = new Path();
            this.progressPaint = new Paint(1);
            this.renderedFirstFrameT = new AnimatedFloat(0.0f, this, 0, 120, new LinearInterpolator());
        }

        private Paint getClipPaint() {
            if (this.clipPaint != null) goto L6;
            Paint r0 = new Paint(1);
            this.clipPaint = r0;
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        L6:
            return this.clipPaint;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawBlurredPhoto(Canvas r10) {
            if (this.radialPaint != null) goto L5;
            super.drawBlurredPhoto(r10);
        L18:
            r10.saveLayerAlpha(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0), (int) (SecretVoicePlayer.m18599$$Nest$fgetopenProgress2(this.this$0) * 178.0f), 31);
            this.progressPaint.setStyle(Paint.Style.STROKE);
            this.progressPaint.setStrokeWidth(AndroidUtilities.dp(3.33f));
            this.progressPaint.setColor(-1);
            this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
            RectF r4 = AndroidUtilities.rectTmp;
            r4.set(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0));
            r4.inset(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
            r10.drawArc(r4, -90.0f, (1.0f - SecretVoicePlayer.m18600$$Nest$fgetprogress(this.this$0)) * (-360.0f), false, this.progressPaint);
            if (this.timerParticles != null) goto L21;
            TimerParticles r102 = new TimerParticles(120);
            this.timerParticles = r102;
            r102.big = true;
        L21:
            this.progressPaint.setStrokeWidth(AndroidUtilities.dp(2.8f));
            this.timerParticles.draw(r10, this.progressPaint, r4, (1.0f - SecretVoicePlayer.m18600$$Nest$fgetprogress(this.this$0)) * (-360.0f), 1.0f);
            r10.restore();
            return;
        L5:
            if (SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L7;
        L16:
            this.radialMatrix.reset();
            float r0 = (SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width() / 76.8f) * SecretVoicePlayer.m18599$$Nest$fgetopenProgress2(this.this$0);
            this.radialMatrix.postScale(r0, r0);
            this.radialMatrix.postTranslate(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerX(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerY());
            this.radialGradient.setLocalMatrix(this.radialMatrix);
            r10.saveLayerAlpha(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0), 255, 31);
            super.drawBlurredPhoto(r10);
            r10.save();
            r10.drawRect(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0), this.radialPaint);
            r10.restore();
            r10.restore();
            goto L18
        L7:
            if (SecretVoicePlayer.m18602$$Nest$fgetrenderedFirstFrame(this.this$0) == false) goto L15;
            boolean r02 = this.drawingToBitmap;
            SecretVoicePlayer r42 = this.this$0;
            if (r02 == false) goto L13;
            Bitmap r03 = SecretVoicePlayer.m18604$$Nest$fgettextureView(r42).getBitmap();
            if (r03 == null) goto L14;
            r10.save();
            this.clipPath.rewind();
            this.clipPath.addCircle(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerX(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerY(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width() / 2.0f, Path.Direction.CW);
            r10.clipPath(this.clipPath);
            r10.scale(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width() / r03.getWidth(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).height() / r03.getHeight());
            r10.translate(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).left, SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).top);
            r10.drawBitmap(r03, 0.0f, 0.0f, null);
            r10.restore();
            r03.recycle();
        L14:
            getPhotoImage().setAlpha(Math.max(1.0f - this.renderedFirstFrameT.set(SecretVoicePlayer.m18602$$Nest$fgetrenderedFirstFrame(this.this$0)), 1.0f - SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)));
            getPhotoImage().draw(r10);
            goto L16
        L13:
            r10.drawCircle(SecretVoicePlayer.m18601$$Nest$fgetrect(r42).centerX(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).centerY(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width() / 2.0f, getClipPaint());
            goto L14
        L15:
            getPhotoImage().draw(r10);
            goto L16
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawBlurredPhotoParticles(Canvas r4) {
            AndroidUtilities.lerp(1.0f, 1.5f, SecretVoicePlayer.m18599$$Nest$fgetopenProgress2(this.this$0));
            super.drawBlurredPhotoParticles(r4);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawRadialProgress(Canvas r1) {
            super.drawRadialProgress(r1);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawReactionsLayout(Canvas r5, float r6, Integer r7) {
            r5.save();
            r5.translate(AndroidUtilities.lerp(0, -this.reactionsLayoutInBubble.x, SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)), AndroidUtilities.lerp(SecretVoicePlayer.m18588$$Nest$fgetcell(this.this$0).getBackgroundDrawableBottom() - getBackgroundDrawableBottom(), this.reactionsLayoutInBubble.totalHeight, SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)));
            super.drawReactionsLayout(r5, (1.0f - SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0)) * r6, r7);
            r5.restore();
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawTime(Canvas r5, float r6, boolean r7) {
            r5.save();
            if (SecretVoicePlayer.m18595$$Nest$fgetisRound(this.this$0) == false) goto L15;
            int r0 = this.timeWidth;
            int r2 = 0;
            if (SecretVoicePlayer.m18596$$Nest$fgetmessageObject(this.this$0) != null) goto L7;
        L14:
            r5.translate(((this.toRect.right - (r0 + AndroidUtilities.dp(8 + r2))) - this.timeX) * SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0), 0.0f);
            goto L15
        L7:
            if (SecretVoicePlayer.m18596$$Nest$fgetmessageObject(this.this$0).isOutOwner() == false) goto L14;
            if (SecretVoicePlayer.m18596$$Nest$fgetmessageObject(this.this$0) != null) goto L11;
        L13:
            r2 = r2 + 20;
            goto L14
        L11:
            if (SecretVoicePlayer.m18596$$Nest$fgetmessageObject(this.this$0).type != 19) goto L13;
            r2 = 4;
        L15:
            super.drawTime(r5, r6, r7);
            r5.restore();
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, org.telegram.ui.Cells.BaseCell
        public int getBoundsLeft() {
            return 0;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, org.telegram.ui.Cells.BaseCell
        public int getBoundsRight() {
            return getWidth();
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onDraw(Canvas r10) {
            if (SecretVoicePlayer.m18595$$Nest$fgetisRound(this.this$0) == true) goto L5;
            Canvas r2 = r10;
        L15:
            super.onDraw(r2);
            if (SecretVoicePlayer.m18595$$Nest$fgetisRound(this.this$0) == true) goto L18;
            return;
        L18:
            if (SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L20;
            return;
        L20:
            if (SecretVoicePlayer.m18602$$Nest$fgetrenderedFirstFrame(this.this$0) == false) goto L25;
            r2.restore();
            return;
        L25:
            return;
        L5:
            if (this.setRect == true) goto L7;
            this.fromRect.set(getPhotoImage().getImageX(), getPhotoImage().getImageY(), getPhotoImage().getImageX2(), getPhotoImage().getImageY2());
            float r0 = Math.min(getMeasuredWidth(), getMeasuredHeight()) * 0.92f;
            this.toRect.set((getMeasuredWidth() - r0) / 2.0f, (getMeasuredHeight() - r0) / 2.0f, (getMeasuredWidth() + r0) / 2.0f, (getMeasuredHeight() + r0) / 2.0f);
            this.setRect = true;
            this.radialGradient = new RadialGradient(0.0f, 0.0f, 48.0f, new int[]{-1, -1, 0}, new float[]{0.0f, 0.8f, 1.0f}, Shader.TileMode.CLAMP);
            Paint r22 = new Paint(1);
            this.radialPaint = r22;
            r22.setShader(this.radialGradient);
            this.radialPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            this.radialMatrix = new Matrix();
        L7:
            AndroidUtilities.lerp(this.fromRect, this.toRect, SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0));
            setImageCoords(SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).left, SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).top, SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width(), SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).height());
            getPhotoImage().setRoundRadius((int) SecretVoicePlayer.m18601$$Nest$fgetrect(this.this$0).width());
            if (SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L10;
        L12:
            r2 = r10;
        L13:
            this.radialProgressAlpha = 1.0f - SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0);
            goto L15
        L10:
            if (SecretVoicePlayer.m18602$$Nest$fgetrenderedFirstFrame(this.this$0) == false) goto L12;
            r2 = r10;
            r2.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            goto L13
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(this.val$finalWidth, this.val$finalHeight);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void setPressed(boolean r1) {
        }

        @Override // android.view.View
        public void setVisibility(int r2) {
            super.setVisibility(r2);
            if (SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0) != null) goto L5;
            return;
        L5:
            if (r2 != 8) goto L9;
            SecretVoicePlayer.m18604$$Nest$fgettextureView(this.this$0).setVisibility(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$4, reason: invalid class name */
    public class AnonymousClass4 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ SecretVoicePlayer this$0;

        public AnonymousClass4(SecretVoicePlayer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$5, reason: invalid class name */
    public class AnonymousClass5 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ SecretVoicePlayer this$0;

        /* renamed from: $r8$lambda$UV9QAK6upoR9GyBzC7yNiYMyp-4, reason: not valid java name */
        public static /* synthetic */ void m18612$r8$lambda$UV9QAK6upoR9GyBzC7yNiYMyp4(AnonymousClass5 r0) {
            r0.lambda$onRenderedFirstFrame$0();
        }

        public AnonymousClass5(SecretVoicePlayer r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onRenderedFirstFrame$0() {
            SecretVoicePlayer.m18608$$Nest$fputrenderedFirstFrame(this.this$0, true);
            SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
            AndroidUtilities.runOnUIThread(new SecretVoicePlayer$5$$ExternalSyntheticLambda0(this));
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r2, int r3) {
            SecretVoicePlayer r22 = this.this$0;
            if (r3 != 4) goto L6;
            r22.dismiss();
            return;
        L6:
            AndroidUtilities.cancelRunOnUIThread(SecretVoicePlayer.m18589$$Nest$fgetcheckTimeRunnable(r22));
            AndroidUtilities.runOnUIThread(SecretVoicePlayer.m18589$$Nest$fgetcheckTimeRunnable(this.this$0), 16);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$6, reason: invalid class name */
    public class AnonymousClass6 implements VideoPlayer.AudioVisualizerDelegate {
        final /* synthetic */ SecretVoicePlayer this$0;

        public AnonymousClass6(SecretVoicePlayer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.AudioVisualizerDelegate
        public boolean needUpdate() {
            if (SecretVoicePlayer.m18583$$Nest$fgetaudioVisualizerDrawable(this.this$0).getParentView() == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.AudioVisualizerDelegate
        public void onVisualizerUpdate(boolean r1, boolean r2, float[] r3) {
            SecretVoicePlayer.m18583$$Nest$fgetaudioVisualizerDrawable(this.this$0).setWaveform(r1, r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretVoicePlayer this$0;
        final /* synthetic */ Runnable val$after;
        final /* synthetic */ boolean val$open;

        public AnonymousClass7(SecretVoicePlayer r1, boolean r2, Runnable r3) {
            this.this$0 = r1;
            this.val$open = r2;
            this.val$after = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            SecretVoicePlayer r22 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            SecretVoicePlayer.m18606$$Nest$fputopenProgress(r22, r0);
            SecretVoicePlayer.m18605$$Nest$fgetwindowView(this.this$0).invalidate();
            SecretVoicePlayer.m18593$$Nest$fgetcontainerView(this.this$0).invalidate();
            SecretVoicePlayer.m18611$$Nest$mupdateTranslation(this.this$0);
            if (SecretVoicePlayer.m18592$$Nest$fgetcloseButton(this.this$0) == null) goto L10;
            SecretVoicePlayer.m18592$$Nest$fgetcloseButton(this.this$0).setAlpha(SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0));
        L10:
            if (SecretVoicePlayer.m18595$$Nest$fgetisRound(this.this$0) == false) goto L13;
            SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).invalidate();
        L13:
            if (SecretVoicePlayer.m18595$$Nest$fgetisRound(this.this$0) == false) goto L15;
        L19:
            Runnable r1 = this.val$after;
            if (r1 == null) goto L23;
            r1.run();
            return;
        L23:
            return;
        L15:
            if (SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0) == null) goto L19;
            if (SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).getSeekBarWaveform() == null) goto L19;
            SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).getSeekBarWaveform().setExplosionRate(SecretVoicePlayer.m18598$$Nest$fgetopenProgress(this.this$0));
            goto L19
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.SecretVoicePlayer$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretVoicePlayer this$0;
        final /* synthetic */ boolean val$open;

        public AnonymousClass8(SecretVoicePlayer r1, boolean r2) {
            this.this$0 = r1;
            this.val$open = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            SecretVoicePlayer r22 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            SecretVoicePlayer.m18607$$Nest$fputopenProgress2(r22, r0);
            if (SecretVoicePlayer.m18595$$Nest$fgetisRound(this.this$0) == false) goto L10;
            SecretVoicePlayer.m18597$$Nest$fgetmyCell(this.this$0).invalidate();
            return;
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$0WVbUCUa44GufO_NzLCIwc_Iig4(SecretVoicePlayer r0, View r1, Bitmap r2) {
        r0.lambda$prepareBlur$2(r1, r2);
    }

    /* renamed from: $r8$lambda$1Ec92Hm9xJnW6FgKE-BkOVfKFic, reason: not valid java name */
    public static /* synthetic */ void m18578$r8$lambda$1Ec92Hm9xJnW6FgKEBkOVfKFic(SecretVoicePlayer r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$10(r1);
    }

    /* renamed from: $r8$lambda$364p2cx8-o9cbYNbntgOzZIYF_s, reason: not valid java name */
    public static /* synthetic */ void m18579$r8$lambda$364p2cx8o9cbYNbntgOzZIYF_s(SecretVoicePlayer r0) {
        r0.lambda$dismiss$6();
    }

    /* renamed from: $r8$lambda$8zlg3J8Py2DYGmYy-RBh0XlwMPw, reason: not valid java name */
    public static /* synthetic */ void m18580$r8$lambda$8zlg3J8Py2DYGmYyRBh0XlwMPw(SecretVoicePlayer r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$BNjk63ko7jP_XFo2ci7_T-HCCvQ, reason: not valid java name */
    public static /* synthetic */ void m18581$r8$lambda$BNjk63ko7jP_XFo2ci7_THCCvQ(SecretVoicePlayer r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$4(r1, r2);
    }

    /* renamed from: $r8$lambda$CNT_WiGVst8DCVZm5C60B-1rWmA, reason: not valid java name */
    public static /* synthetic */ void m18582$r8$lambda$CNT_WiGVst8DCVZm5C60B1rWmA(SecretVoicePlayer r0) {
        r0.lambda$dismiss$8();
    }

    public static /* synthetic */ void $r8$lambda$J1ZiiWVYrf6k4Xwz80hTgPs6WWw(SecretVoicePlayer r0, View r1) {
        r0.lambda$setCell$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$e0q04baP5EkhRrnfk6nhcCsHpyg(SecretVoicePlayer r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$h6vQum0dN2nIqwBckm2t98pt7U8(SecretVoicePlayer r0) {
        r0.lambda$dismiss$7();
    }

    public static /* synthetic */ void $r8$lambda$oeOWgdPvPKIAQMKhPtQYl1olUb4(SecretVoicePlayer r0) {
        r0.checkTime();
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$r1vdSo3pdquTEbD5WKNS1Lg0gz8(SecretVoicePlayer r0, View r1, WindowInsetsCompat r2) {
        return r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$v6FPKMZ6YgTfxUqJOIcNQByDD4M(SecretVoicePlayer r0, boolean r1, ValueAnimator r2) {
        r0.lambda$animateOpenTo$9(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaudioVisualizerDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioVisualizerDrawable m18583$$Nest$fgetaudioVisualizerDrawable(SecretVoicePlayer r0) {
        return r0.audioVisualizerDrawable;
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m18584$$Nest$fgetblurBitmap(SecretVoicePlayer r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m18585$$Nest$fgetblurBitmapPaint(SecretVoicePlayer r0) {
        return r0.blurBitmapPaint;
    }

    /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m18586$$Nest$fgetblurBitmapShader(SecretVoicePlayer r0) {
        return r0.blurBitmapShader;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m18587$$Nest$fgetblurMatrix(SecretVoicePlayer r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m18588$$Nest$fgetcell(SecretVoicePlayer r0) {
        return r0.cell;
    }

    /* renamed from: -$$Nest$fgetcheckTimeRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18589$$Nest$fgetcheckTimeRunnable(SecretVoicePlayer r0) {
        return r0.checkTimeRunnable;
    }

    /* renamed from: -$$Nest$fgetclipBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18590$$Nest$fgetclipBottom(SecretVoicePlayer r0) {
        return r0.clipBottom;
    }

    /* renamed from: -$$Nest$fgetclipTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18591$$Nest$fgetclipTop(SecretVoicePlayer r0) {
        return r0.clipTop;
    }

    /* renamed from: -$$Nest$fgetcloseButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m18592$$Nest$fgetcloseButton(SecretVoicePlayer r0) {
        return r0.closeButton;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m18593$$Nest$fgetcontainerView(SecretVoicePlayer r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgethintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m18594$$Nest$fgethintView(SecretVoicePlayer r0) {
        return r0.hintView;
    }

    /* renamed from: -$$Nest$fgetisRound, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18595$$Nest$fgetisRound(SecretVoicePlayer r0) {
        return r0.isRound;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m18596$$Nest$fgetmessageObject(SecretVoicePlayer r0) {
        return r0.messageObject;
    }

    /* renamed from: -$$Nest$fgetmyCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m18597$$Nest$fgetmyCell(SecretVoicePlayer r0) {
        return r0.myCell;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18598$$Nest$fgetopenProgress(SecretVoicePlayer r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetopenProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18599$$Nest$fgetopenProgress2(SecretVoicePlayer r0) {
        return r0.openProgress2;
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18600$$Nest$fgetprogress(SecretVoicePlayer r0) {
        return r0.progress;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m18601$$Nest$fgetrect(SecretVoicePlayer r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetrenderedFirstFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18602$$Nest$fgetrenderedFirstFrame(SecretVoicePlayer r0) {
        return r0.renderedFirstFrame;
    }

    /* renamed from: -$$Nest$fgetsetCellInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18603$$Nest$fgetsetCellInvisible(SecretVoicePlayer r0) {
        return r0.setCellInvisible;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m18604$$Nest$fgettextureView(SecretVoicePlayer r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m18605$$Nest$fgetwindowView(SecretVoicePlayer r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18606$$Nest$fputopenProgress(SecretVoicePlayer r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18607$$Nest$fputopenProgress2(SecretVoicePlayer r0, float r1) {
        r0.openProgress2 = r1;
    }

    /* renamed from: -$$Nest$fputrenderedFirstFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18608$$Nest$fputrenderedFirstFrame(SecretVoicePlayer r0, boolean r1) {
        r0.renderedFirstFrame = r1;
    }

    /* renamed from: -$$Nest$fputsetCellInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18609$$Nest$fputsetCellInvisible(SecretVoicePlayer r0, boolean r1) {
        r0.setCellInvisible = r1;
    }

    /* renamed from: -$$Nest$msetupTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18610$$Nest$msetupTranslation(SecretVoicePlayer r0) {
        r0.setupTranslation();
    }

    /* renamed from: -$$Nest$mupdateTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18611$$Nest$mupdateTranslation(SecretVoicePlayer r0) {
        r0.updateTranslation();
    }

    public SecretVoicePlayer(Context r5) {
        super(r5, R.style.TransparentDialog);
        this.insets = Insets.NONE;
        this.rect = new RectF();
        this.clipTop = 0.0f;
        this.clipBottom = 0.0f;
        this.checkTimeRunnable = new SecretVoicePlayer$$ExternalSyntheticLambda0(this);
        this.progress = 0.0f;
        this.dismissing = false;
        this.context = r5;
        AndroidUtilities.enableEdgeToEdge(getWindow());
        AnonymousClass1 r1 = new AnonymousClass1(this, r5);
        this.windowView = r1;
        r1.setOnClickListener(new SecretVoicePlayer$$ExternalSyntheticLambda1(this));
        AnonymousClass2 r12 = new AnonymousClass2(this, r5);
        this.containerView = r12;
        r12.setClipToPadding(false);
        this.windowView.addView(this.containerView, LayoutHelper.createFrame(-1, -1, 119));
        ViewCompat.setOnApplyWindowInsetsListener(this.windowView, new SecretVoicePlayer$$ExternalSyntheticLambda2(this));
        if (SharedConfig.raiseToListen == false) goto L6;
        this.earListener = new EarListener(r5);
        return;
    }

    private void animateOpenTo(boolean r10, Runnable r11) {
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = this.open2Animator;
        if (r02 == null) goto L8;
        r02.cancel();
    L8:
        setupTranslation();
        float r03 = this.openProgress;
        float r1 = 0.0f;
        if (r10 == false) goto L11;
        float r3 = 1.0f;
    L12:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r03, r3});
        this.openAnimator = r32;
        r32.addUpdateListener(new SecretVoicePlayer$$ExternalSyntheticLambda3(this, r10));
        this.openAnimator.addListener(new AnonymousClass7(this, r10, r11));
        if (r10 == false) goto L15;
    L17:
        long r7 = 520;
    L18:
        ValueAnimator r112 = this.openAnimator;
        CubicBezierInterpolator r33 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r112.setInterpolator(r33);
        this.openAnimator.setDuration(r7);
        this.openAnimator.start();
        float r113 = this.openProgress2;
        if (r10 == false) goto L21;
        r1 = 1.0f;
    L21:
        ValueAnimator r114 = ValueAnimator.ofFloat(new float[]{r113, r1});
        this.open2Animator = r114;
        r114.addUpdateListener(new SecretVoicePlayer$$ExternalSyntheticLambda4(this));
        this.open2Animator.addListener(new AnonymousClass8(this, r10));
        this.open2Animator.setDuration((long) (r7 * 1.5f));
        this.open2Animator.setInterpolator(r33);
        this.open2Animator.start();
        return;
    L15:
        if (this.closeAction != null) goto L17;
        r7 = 330;
        goto L18
    L11:
        r3 = 0.0f;
        goto L12
    }

    private void checkTime() {
        if (this.player == null) goto L15;
        this.progress = r0.getCurrentPosition() / this.player.getDuration();
        ChatMessageCell r0 = this.myCell;
        if (r0 == null) goto L11;
        r0.overrideDuration((this.player.getDuration() - this.player.getCurrentPosition()) / 1000);
        this.myCell.updatePlayingMessageProgress();
        SeekBarWaveform r02 = this.myCell.getSeekBarWaveform();
        if (r02 == null) goto L11;
        r02.explodeAt(this.progress);
    L11:
        if (this.player.isPlaying() == false) goto L14;
        AndroidUtilities.cancelRunOnUIThread(this.checkTimeRunnable);
        AndroidUtilities.runOnUIThread(this.checkTimeRunnable, 16);
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$animateOpenTo$10(ValueAnimator r1) {
        this.openProgress2 = ((Float) r1.getAnimatedValue()).floatValue();
        if (this.isRound == false) goto L6;
        this.myCell.invalidate();
        return;
    }

    private /* synthetic */ void lambda$animateOpenTo$9(boolean r3, ValueAnimator r4) {
        this.openProgress = ((Float) r4.getAnimatedValue()).floatValue();
        this.windowView.invalidate();
        this.containerView.invalidate();
        if (this.isRound == false) goto L5;
        this.myCell.invalidate();
    L5:
        updateTranslation();
        TextView r42 = this.closeButton;
        if (r42 == null) goto L9;
        r42.setAlpha(this.openProgress);
    L9:
        if (this.isRound == true) goto L20;
        ChatMessageCell r43 = this.myCell;
        if (r43 != null) goto L13;
        return;
    L13:
        if (r43.getSeekBarWaveform() == null) goto L22;
        SeekBarWaveform r44 = this.myCell.getSeekBarWaveform();
        if (r3 == false) goto L17;
        CubicBezierInterpolator r32 = CubicBezierInterpolator.EASE_OUT;
    L18:
        r44.setExplosionRate(r32.getInterpolation(Utilities.clamp(this.openProgress * 1.25f, 1.0f, 0.0f)));
        return;
    L17:
        r32 = CubicBezierInterpolator.EASE_IN;
        goto L18
    L22:
        return;
    }

    private /* synthetic */ void lambda$dismiss$6() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismiss$7() {
        if (this.thanosEffect != null) goto L7;
        AndroidUtilities.runOnUIThread(new SecretVoicePlayer$$ExternalSyntheticLambda11(this));
        ChatMessageCell r0 = this.cell;
        if (r0 == null) goto L7;
        r0.setVisibility(0);
        this.cell.invalidate();
    L7:
        MediaController.getInstance().tryResumePausedAudio();
    }

    private /* synthetic */ void lambda$dismiss$8() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (this.closeAction != null) goto L6;
        dismiss();
        return;
    }

    private /* synthetic */ WindowInsetsCompat lambda$new$1(View r4, WindowInsetsCompat r5) {
        Insets r42 = AndroidUtilities.getDefaultWindowInsets(r5, false);
        this.insets = r42;
        this.containerView.setPadding(r42.left, r42.top, r42.right, r42.bottom);
        this.windowView.requestLayout();
        return WindowInsetsCompat.CONSUMED;
    }

    private /* synthetic */ void lambda$onBackPressed$4(AlertDialog r1, int r2) {
        AlertDialog r0 = this.backDialog;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    private /* synthetic */ void lambda$onBackPressed$5(AlertDialog r1, int r2) {
        AlertDialog r12 = this.backDialog;
        if (r12 == null) goto L5;
        r12.dismiss();
        this.backDialog = null;
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$prepareBlur$2(View r3, Bitmap r4) {
        if (r3 == null) goto L4;
        r3.setVisibility(0);
    L4:
        this.blurBitmap = r4;
        Paint r32 = new Paint(1);
        this.blurBitmapPaint = r32;
        Bitmap r0 = this.blurBitmap;
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r42 = new BitmapShader(r0, r1, r1);
        this.blurBitmapShader = r42;
        r32.setShader(r42);
        ColorMatrix r33 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L7;
        float r43 = 0.05f;
    L8:
        AndroidUtilities.adjustSaturationColorMatrix(r33, r43);
        if (Theme.isCurrentThemeDark() == false) goto L11;
        float r44 = -0.02f;
    L12:
        AndroidUtilities.adjustBrightnessColorMatrix(r33, r44);
        this.blurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r33));
        this.blurMatrix = new Matrix();
        return;
    L11:
        r44 = -0.04f;
        goto L12
    L7:
        r43 = 0.25f;
        goto L8
    }

    private /* synthetic */ void lambda$setCell$3(View r1) {
        dismiss();
    }

    private void prepareBlur(View r2) {
        if (r2 == null) goto L4;
        r2.setVisibility(4);
    L4:
        AndroidUtilities.makeGlobalBlurBitmap(new SecretVoicePlayer$$ExternalSyntheticLambda5(this, r2), 14.0f);
    }

    private void setupTranslation() {
        if (this.hasTranslation == false) goto L5;
        return;
    L5:
        if (this.windowView.getWidth() <= 0) goto L20;
        ChatMessageCell r0 = this.cell;
        if (r0 == null) goto L16;
        int[] r3 = new int[2];
        r0.getLocationOnScreen(r3);
        float r02 = r3[0] - this.insets.left;
        int r4 = this.windowView.getWidth();
        Insets r5 = this.insets;
        this.tx = r02 - ((((r4 - r5.left) - r5.right) - this.cell.getWidth()) / 2.0f);
        float r03 = r3[1] - this.insets.top;
        int r42 = this.windowView.getHeight();
        Insets r6 = this.insets;
        this.ty = r03 - (((((r42 - r6.top) - r6.bottom) - this.cell.getHeight()) - this.heightdiff) / 2.0f);
        if (this.hasDestTranslation == true) goto L15;
        this.hasDestTranslation = true;
        this.dtx = 0.0f;
        float r04 = (Utilities.clamp(r3[1] + (this.cell.getHeight() / 2.0f), this.windowView.getHeight() * 0.7f, this.windowView.getHeight() * 0.3f) - (this.cell.getHeight() / 2.0f)) - ((this.windowView.getHeight() - this.cell.getHeight()) / 2.0f);
        this.dty = r04;
        if (this.isRound == false) goto L14;
        this.dty = 0.0f;
        goto L15
    L14:
        this.dty = AndroidUtilities.lerp(0.0f, r04, 0.78f);
    L15:
        updateTranslation();
    L17:
        this.hasTranslation = true;
        return;
    L16:
        this.ty = 0.0f;
        this.tx = 0.0f;
        goto L17
    }

    private void updateTranslation() {
        if (this.thanosEffect != null) goto L10;
        this.myCell.setTranslationX(AndroidUtilities.lerp(this.tx, this.dtx, this.openProgress));
        this.myCell.setTranslationY(AndroidUtilities.lerp(this.ty, this.dty, this.openProgress));
        HintView2 r0 = this.hintView;
        if (r0 == null) goto L9;
        r0.setTranslationX(AndroidUtilities.lerp(this.tx, this.dtx, this.openProgress));
        this.hintView.setTranslationY(AndroidUtilities.lerp(this.ty, this.dty, this.openProgress));
        return;
    L9:
        return;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.dismissing == true) goto L32;
        AlertDialog r0 = this.backDialog;
        if (r0 == null) goto L8;
        r0.dismiss();
        this.backDialog = null;
    L8:
        this.dismissing = true;
        HintView2 r2 = this.hintView;
        if (r2 == null) goto L11;
        r2.hide();
    L11:
        VideoPlayer r22 = this.player;
        if (r22 == null) goto L15;
        r22.pause();
        this.player.releasePlayer(true);
        this.player = null;
    L15:
        if (this.isRound == true) goto L21;
        ChatMessageCell r23 = this.myCell;
        if (r23 == null) goto L21;
        if (r23.getSeekBarWaveform() == null) goto L21;
        this.myCell.getSeekBarWaveform().setExplosionRate(this.openProgress);
    L21:
        this.hasTranslation = false;
        setupTranslation();
        this.open = false;
        animateOpenTo(false, new SecretVoicePlayer$$ExternalSyntheticLambda9(this));
        this.windowView.invalidate();
        Runnable r24 = this.closeAction;
        if (r24 == null) goto L27;
        ChatMessageCell r3 = this.cell;
        if (r3 == null) goto L26;
        r3.makeVisibleAfterChange = true;
    L26:
        AndroidUtilities.runOnUIThread(r24);
        this.closeAction = null;
        ThanosEffect r02 = new ThanosEffect(this.context, null);
        this.thanosEffect = r02;
        this.windowView.addView(r02, LayoutHelper.createFrame(-1, -1, 119));
        this.thanosEffect.animate(this.myCell, 1.5f, new SecretVoicePlayer$$ExternalSyntheticLambda10(this));
        WindowManager.LayoutParams r03 = getWindow().getAttributes();
        r03.flags |= 16;
        getWindow().setAttributes(r03);
    L27:
        EarListener r4 = this.earListener;
        if (r4 == null) goto L31;
        r4.detach();
        return;
    L31:
        return;
    }

    public boolean isShown() {
        return !this.dismissing;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        AlertDialog r0 = this.backDialog;
        if (r0 == null) goto L7;
        r0.dismiss();
        this.backDialog = null;
        return;
    L7:
        if (this.dismissing == true) goto L24;
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L24;
        if (r02.isOutOwner() == true) goto L24;
        AlertDialog.Builder r03 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (this.isRound == false) goto L15;
        int r1 = R.string.VideoOnceCloseTitle;
    L16:
        AlertDialog.Builder r04 = r03.setTitle(LocaleController.getString(r1));
        if (this.isRound == false) goto L19;
        int r12 = R.string.VideoOnceCloseMessage;
    L20:
        AlertDialog r05 = r04.setMessage(LocaleController.getString(r12)).setPositiveButton(LocaleController.getString(R.string.Continue), new SecretVoicePlayer$$ExternalSyntheticLambda6(this)).setNegativeButton(LocaleController.getString(R.string.Delete), new SecretVoicePlayer$$ExternalSyntheticLambda7(this)).create();
        this.backDialog = r05;
        r05.show();
        TextView r3 = (TextView) this.backDialog.getButton(-2);
        if (r3 == null) goto L26;
        r3.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L26:
        return;
    L19:
        r12 = R.string.VoiceOnceCloseMessage;
        goto L20
    L15:
        r1 = R.string.VoiceOnceCloseTitle;
    L24:
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        Window r42 = getWindow();
        r42.setWindowAnimations(R.style.DialogNoAnimation);
        setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
        WindowManager.LayoutParams r0 = r42.getAttributes();
        r0.width = -1;
        r0.height = -1;
        r0.gravity = 119;
        r0.dimAmount = 0.0f;
        int r1 = r0.flags & (-3);
        r0.softInputMode = 48;
        r0.flags = (-2013069056) | r1;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        r0.flags = r1 | (-2013060864);
        AndroidUtilities.logFlagSecure();
    L5:
        r0.flags |= 1152;
        r42.setAttributes(r0);
        this.windowView.setSystemUiVisibility(1284);
        AndroidUtilities.setLightNavigationBar(this.windowView, !Theme.isCurrentThemeDark());
    }

    public void setCell(ChatMessageCell r20, Runnable r21, Runnable r22) {
        this.openAction = r21;
        this.closeAction = r22;
        ChatMessageCell r2 = this.myCell;
        if (r2 == null) goto L5;
        this.containerView.removeView(r2);
        this.myCell = null;
    L5:
        this.cell = r20;
        if (r20 == null) goto L8;
        MessageObject r23 = r20.getMessageObject();
    L9:
        this.messageObject = r23;
        if (r23 != null) goto L12;
    L14:
        boolean r24 = false;
    L15:
        this.isRound = r24;
        ChatMessageCell r25 = this.cell;
        if (r25 == null) goto L18;
        Theme.ResourcesProvider r26 = r25.getResourcesProvider();
    L19:
        this.resourcesProvider = r26;
        if (this.cell == null) goto L33;
        this.clipTop = r20.parentBoundsTop;
        this.clipBottom = r20.parentBoundsBottom;
        if ((r20.getParent() instanceof View) == false) goto L24;
        View r0 = (View) r20.getParent();
        this.clipTop += r0.getY();
        this.clipBottom += r0.getY();
    L24:
        int r7 = this.cell.getWidth();
        int r02 = this.cell.getHeight();
        if (this.isRound == false) goto L27;
        r02 = Math.min(AndroidUtilities.dp(360.0f), Math.min(r7, AndroidUtilities.displaySize.y));
    L27:
        int r8 = r02;
        this.heightdiff = r8 - this.cell.getHeight();
        int r12 = (int) Math.ceil((Math.min(r7, r8) * 0.92f) / AndroidUtilities.density);
        AnonymousClass3 r03 = new AnonymousClass3(this, getContext(), UserConfig.selectedAccount, false, null, this.cell.getResourcesProvider(), r7, r8);
        this.myCell = r03;
        this.cell.copyVisiblePartTo(r03);
        this.myCell.copySpoilerEffect2AttachIndexFrom(this.cell);
        this.myCell.setDelegate(new AnonymousClass4(this));
        ChatMessageCell r13 = this.myCell;
        MessageObject r14 = this.messageObject;
        MessageObject.GroupedMessages r15 = this.cell.getCurrentMessagesGroup();
        ChatMessageCell r04 = this.cell;
        r13.setMessageObject(r14, r15, r04.pinnedBottom, r04.pinnedTop, false);
        if (this.isRound == true) goto L32;
        AudioVisualizerDrawable r05 = new AudioVisualizerDrawable();
        this.audioVisualizerDrawable = r05;
        r05.setParentView(this.myCell);
        this.myCell.overrideAudioVisualizer(this.audioVisualizerDrawable);
        if (this.myCell.getSeekBarWaveform() == null) goto L32;
        this.myCell.getSeekBarWaveform().setExplosionRate(this.openProgress);
    L32:
        this.hasTranslation = false;
        this.containerView.addView(this.myCell, new FrameLayout.LayoutParams(this.cell.getWidth(), r8, 17));
    L34:
        TextureView r06 = this.textureView;
        if (r06 == null) goto L38;
        this.containerView.removeView(r06);
        this.textureView = null;
    L38:
        if (this.isRound == false) goto L40;
        this.renderedFirstFrame = false;
        TextureView r07 = new TextureView(this.context);
        this.textureView = r07;
        this.containerView.addView(r07, 0, LayoutHelper.createFrame(r12, r12));
    L40:
        MediaController.getInstance().pauseByRewind();
        VideoPlayer r08 = this.player;
        if (r08 == null) goto L43;
        r08.pause();
        this.player.releasePlayer(true);
        this.player = null;
    L43:
        ChatMessageCell r09 = this.cell;
        if (r09 != null) goto L46;
    L79:
        HintView2 r010 = this.hintView;
        if (r010 == null) goto L82;
        this.containerView.removeView(r010);
        this.hintView = null;
    L82:
        MessageObject r011 = this.messageObject;
        if (r011 != null) goto L85;
    L87:
        boolean r012 = false;
    L88:
        MessageObject r27 = this.messageObject;
        if (r27 != null) goto L91;
    L127:
        TextView r28 = this.closeButton;
        if (r28 == null) goto L130;
        this.containerView.removeView(r28);
        this.closeButton = null;
    L130:
        TextView r29 = new TextView(this.context);
        this.closeButton = r29;
        r29.setTextColor(-1);
        this.closeButton.setTypeface(AndroidUtilities.bold());
        boolean r210 = Theme.isCurrentThemeDark();
        TextView r5 = this.closeButton;
        if (r210 == false) goto L133;
        r5.setBackground(Theme.createSimpleSelectorRoundRectDrawable(64, 553648127, 872415231));
    L134:
        this.closeButton.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f));
        ScaleStateListAnimator.apply(this.closeButton);
        TextView r211 = this.closeButton;
        if (r012 == false) goto L137;
        int r3 = R.string.VoiceOnceClose;
    L138:
        r211.setText(LocaleController.getString(r3));
        this.closeButton.setOnClickListener(new SecretVoicePlayer$$ExternalSyntheticLambda8(this));
        this.containerView.addView(this.closeButton, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 18.0f));
        if (r012 == true) goto L149;
        ChatMessageCell r013 = this.myCell;
        if (r013 != null) goto L143;
        return;
    L143:
        if (r013.getMessageObject() != null) goto L145;
        return;
    L145:
        if (this.myCell.getMessageObject().messageOwner == null) goto L152;
        this.myCell.getMessageObject().messageOwner.media_unread = false;
        this.myCell.invalidate();
        return;
    L152:
        return;
    L149:
        return;
    L137:
        r3 = R.string.VoiceOnceDeleteClose;
        goto L138
    L133:
        r5.setBackground(Theme.createSimpleSelectorRoundRectDrawable(64, 771751936, 1140850688));
        goto L134
    L91:
        if (r27.getDialogId() == UserConfig.getInstance(this.messageObject.currentAccount).getClientUserId()) goto L127;
        HintView2 r212 = new HintView2(this.context, 3);
        this.hintView = r212;
        r212.setMultilineText(true);
        if (r012 == false) goto L107;
        long r52 = this.messageObject.getDialogId();
        MessageObject r72 = this.messageObject;
        String r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r52 <= 0) goto L99;
        TLRPC.User r213 = MessagesController.getInstance(r72.currentAccount).getUser(Long.valueOf(r52));
        if (r213 == null) goto L102;
        r82 = UserObject.getFirstName(r213);
    L102:
        HintView2 r214 = this.hintView;
        if (this.isRound == false) goto L105;
        int r53 = R.string.VideoOnceOutHint;
    L106:
        r214.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r53, new Object[]{r82})));
    L112:
        this.hintView.setRounding(12.0f);
        HintView2 r215 = this.hintView;
        if (r012 == false) goto L115;
    L117:
        float r6 = 0.0f;
    L118:
        r215.setPadding(AndroidUtilities.dp(r6), 0, 0, 0);
        boolean r216 = this.isRound;
        HintView2 r62 = this.hintView;
        if (r216 == false) goto L121;
        r62.setJointPx(0.5f, 0.0f);
        this.hintView.setTextAlign(Layout.Alignment.ALIGN_CENTER);
    L122:
        this.hintView.setTextSize(14.0f);
        HintView2 r217 = this.hintView;
        r217.setMaxWidthPx(HintView2.cutInFancyHalf(r217.getText(), this.hintView.getTextPaint()));
        boolean r218 = this.isRound;
        FrameLayout r54 = this.containerView;
        if (r218 == false) goto L125;
        r54.addView(this.hintView, LayoutHelper.createFrame((int) ((this.cell.getWidth() / AndroidUtilities.density) * 0.6f), 150.0f, 17, 0.0f, (-75.0f) - (((this.cell.getHeight() + this.heightdiff) / AndroidUtilities.density) / 2.0f), 0.0f, 0.0f));
    L126:
        this.hintView.show();
        goto L127
    L125:
        r54.addView(this.hintView, LayoutHelper.createFrame((int) ((this.cell.getWidth() / AndroidUtilities.density) * 0.6f), 150.0f, 17, ((((this.cell.getWidth() * (-0.39999998f)) / 2.0f) + this.cell.getBoundsLeft()) / AndroidUtilities.density) + 1.0f, ((-75.0f) - ((this.cell.getHeight() / AndroidUtilities.density) / 2.0f)) - 8.0f, 0.0f, 0.0f));
        goto L126
    L121:
        r62.setJointPx(0.0f, AndroidUtilities.dp(34.0f));
        this.hintView.setTextAlign(Layout.Alignment.ALIGN_NORMAL);
        goto L122
    L115:
        if (this.cell.pinnedBottom == true) goto L117;
        r6 = 6.0f;
        goto L118
    L105:
        r53 = R.string.VoiceOnceOutHint;
        goto L106
    L99:
        TLRPC.Chat r219 = MessagesController.getInstance(r72.currentAccount).getChat(Long.valueOf(-r52));
        if (r219 == null) goto L102;
        r82 = r219.title;
        goto L102
    L107:
        HintView2 r220 = this.hintView;
        if (this.isRound == false) goto L110;
        int r55 = R.string.VideoOnceHint;
    L111:
        r220.setText(AndroidUtilities.replaceTags(LocaleController.getString(r55)));
        goto L112
    L110:
        r55 = R.string.VoiceOnceHint;
        goto L111
    L85:
        if (r011.isOutOwner() == false) goto L87;
        r012 = true;
        goto L88
    L46:
        if (r09.getMessageObject() == null) goto L79;
        File r014 = FileLoader.getInstance(this.cell.getMessageObject().currentAccount).getPathToAttach(this.cell.getMessageObject().getDocument());
        if (r014 != null) goto L50;
    L52:
        if (r014 != null) goto L54;
    L55:
        r014 = FileLoader.getInstance(this.cell.getMessageObject().currentAccount).getPathToMessage(this.cell.getMessageObject().messageOwner);
        if (r014 != null) goto L58;
    L60:
        if (r014 == null) goto L64;
        if (r014.exists() == false) goto L64;
    L66:
        if (r014 != null) goto L68;
        return;
    L68:
        if (r014.exists() == false) goto L153;
        VideoPlayer r221 = new VideoPlayer();
        this.player = r221;
        r221.setDelegate(new AnonymousClass5(this));
        if (this.audioVisualizerDrawable == null) goto L74;
        this.player.setAudioVisualizerDelegate(new AnonymousClass6(this));
    L74:
        if (this.isRound == false) goto L76;
        this.player.setTextureView(this.textureView);
    L76:
        this.player.preparePlayer(Uri.fromFile(r014), "other");
        this.player.play();
        EarListener r015 = this.earListener;
        if (r015 == null) goto L79;
        r015.attachPlayer(this.player);
        goto L79
    L153:
        return;
    L64:
        if (this.cell.getMessageObject().messageOwner.attachPath == null) goto L66;
        r014 = new File(this.cell.getMessageObject().messageOwner.attachPath);
        goto L66
    L58:
        if (r014.exists() == true) goto L60;
        r014 = new File(r014.getPath() + ".enc");
        goto L60
    L54:
        if (r014.exists() == true) goto L60;
    L50:
        if (r014.exists() == true) goto L52;
        r014 = new File(r014.getPath() + ".enc");
        goto L52
    L33:
        r12 = 360;
        goto L34
    L18:
        r26 = null;
        goto L19
    L12:
        if (r23.isRoundVideo() == false) goto L14;
        r24 = true;
        goto L15
    L8:
        r23 = null;
        goto L9
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == false) goto L13;
        super.show();
        prepareBlur(this.cell);
        this.setCellInvisible = true;
        this.open = true;
        animateOpenTo(true, null);
        Runnable r0 = this.openAction;
        if (r0 == null) goto L8;
        AndroidUtilities.runOnUIThread(r0);
        this.openAction = null;
    L8:
        EarListener r2 = this.earListener;
        if (r2 == null) goto L12;
        r2.attach();
        return;
    L12:
        return;
    }
}
