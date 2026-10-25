package org.telegram.ui.Stories.recorder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.VideoEncodingService;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;

/* loaded from: classes7.dex */
public class DownloadButton extends ImageView {
    private BuildingVideo buildingVideo;
    private FrameLayout container;
    private int currentAccount;
    private StoryEntry currentEntry;
    private boolean downloading;
    private boolean downloadingVideo;
    private Utilities.Callback<Runnable> prepare;
    private boolean preparing;
    private CircularProgressDrawable progressDrawable;
    private Theme.ResourcesProvider resourcesProvider;
    private Uri savedToGalleryUri;
    private PreparingVideoToast toast;
    private boolean wasImageDownloading;
    private boolean wasVideoDownloading;

    public static class BuildingVideo implements NotificationCenter.NotificationCenterDelegate {
        final int currentAccount;
        final StoryEntry entry;
        final File file;
        private MessageObject messageObject;
        private final Runnable onCancel;
        private final Runnable onDone;
        private final Utilities.Callback<Float> onProgress;

        public static /* synthetic */ void $r8$lambda$W6bxIhZSHwTQJVG_wVynL5oyFpU(BuildingVideo r0, VideoEditedInfo r1) {
            r0.lambda$start$0(r1);
        }

        public BuildingVideo(int r1, StoryEntry r2, File r3, Runnable r4, Utilities.Callback<Float> r5, Runnable r6) {
            this.currentAccount = r1;
            this.entry = r2;
            this.file = r3;
            this.onDone = r4;
            this.onProgress = r5;
            this.onCancel = r6;
            start();
        }

        private /* synthetic */ void lambda$start$0(VideoEditedInfo r2) {
            MessageObject r0 = this.messageObject;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.videoEditedInfo = r2;
            MediaController.getInstance().scheduleVideoConvert(this.messageObject);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r4, int r5, Object... r6) {
            if (r4 != NotificationCenter.filePreparingStarted) goto L7;
            MessageObject r3 = (MessageObject) r6[0];
            return;
        L7:
            if (r4 != NotificationCenter.fileNewChunkAvailable) goto L18;
            if (((MessageObject) r6[0]) != this.messageObject) goto L30;
            String r42 = (String) r6[1];
            ((Long) r6[2]).getClass();
            long r43 = ((Long) r6[3]).longValue();
            Float r62 = (Float) r6[4];
            r62.floatValue();
            Utilities.Callback<Float> r1 = this.onProgress;
            if (r1 == null) goto L14;
            r1.run(r62);
        L14:
            if (r43 <= 0) goto L31;
            this.onDone.run();
            VideoEncodingService.stop();
            stop(false);
            return;
        L31:
            return;
        L30:
            return;
        L18:
            if (r4 == NotificationCenter.filePreparingFailed) goto L20;
            return;
        L20:
            if (((MessageObject) r6[0]) != this.messageObject) goto L33;
            stop(false);
            File r44 = this.file;     // Catch: Exception -> L27
            if (r44 == null) goto L25;
            r44.delete();     // Catch: Exception -> L27
        L25:
            this.onCancel.run();
            return;
        }

        public void start() {
            if (this.messageObject == null) goto L5;
            return;
        L5:
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingStarted);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileNewChunkAvailable);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingFailed);
            TLRPC.TL_message r4 = new TLRPC.TL_message();
            r4.id = 1;
            r4.attachPath = this.file.getAbsolutePath();
            this.messageObject = new MessageObject(this.currentAccount, r4, null, false, false);
            this.entry.getVideoEditedInfo(new DownloadButton$BuildingVideo$$ExternalSyntheticLambda0(this));
        }

        public void stop(boolean r3) {
            if (this.messageObject != null) goto L5;
            return;
        L5:
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingStarted);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileNewChunkAvailable);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingFailed);
            if (r3 == false) goto L8;
            MediaController.getInstance().cancelVideoConvert(this.messageObject);
        L8:
            this.messageObject = null;
        }
    }

    public static class PreparingVideoToast extends View {
        private final Paint backgroundPaint;
        private final ButtonBounce cancelButton;
        private final RectF currentRect;
        private boolean deleted;
        private final Paint dimPaint;
        private StaticLayout doneLayout;
        private float doneLayoutLeft;
        private float doneLayoutWidth;
        private final Paint greyPaint;
        private final RectF hiddenRect;
        private Runnable hideRunnable;
        private RLottieDrawable lottieDrawable;
        private Runnable onCancel;
        private final RectF prepareRect;
        private boolean preparing;
        private final StaticLayout preparingLayout;
        private final float preparingLayoutLeft;
        private final float preparingLayoutWidth;
        private float progress;
        private final AnimatedFloat progressT;
        private final AnimatedFloat showT;
        private boolean shown;
        private final AnimatedFloat t;
        private final TextPaint textPaint;
        private final TextPaint textPaint2;
        private final RectF toastRect;
        private final Paint whitePaint;

        public static /* synthetic */ void $r8$lambda$6unFM2JE8pbJ9VEjUSMzEtfgvQg(PreparingVideoToast r0) {
            r0.lambda$onDraw$0();
        }

        public PreparingVideoToast(Context r2) {
            this(r2, LocaleController.getString(R.string.PreparingVideo));
        }

        public PreparingVideoToast(Context r17, String r18) {
            super(r17);
            Paint r8 = new Paint(1);
            this.dimPaint = r8;
            TextPaint r10 = new TextPaint(1);
            this.textPaint = r10;
            TextPaint r11 = new TextPaint(1);
            this.textPaint2 = r11;
            Paint r12 = new Paint(1);
            this.backgroundPaint = r12;
            Paint r13 = new Paint(1);
            this.whitePaint = r13;
            Paint r14 = new Paint(1);
            this.greyPaint = r14;
            this.cancelButton = new ButtonBounce(this);
            this.shown = false;
            this.showT = new AnimatedFloat(0.0f, this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.preparing = true;
            float r9 = 0.0f;
            this.progress = 0.0f;
            this.t = new AnimatedFloat(this);
            this.progressT = new AnimatedFloat(this);
            this.prepareRect = new RectF();
            this.toastRect = new RectF();
            this.currentRect = new RectF();
            this.hiddenRect = new RectF();
            r8.setColor(1509949440);
            r10.setColor(-1);
            r11.setColor(-1);
            r12.setColor(-869783512);
            r13.setColor(-1);
            r14.setColor(872415231);
            Paint.Style r1 = Paint.Style.STROKE;
            r13.setStyle(r1);
            Paint.Cap r2 = Paint.Cap.ROUND;
            r13.setStrokeCap(r2);
            r13.setStrokeWidth(AndroidUtilities.dp(4.0f));
            r14.setStyle(r1);
            r14.setStrokeCap(r2);
            r14.setStrokeWidth(AndroidUtilities.dp(4.0f));
            r10.setTextSize(AndroidUtilities.dp(14.0f));
            r11.setTextSize(AndroidUtilities.dpf2(14.66f));
            StaticLayout r0 = new StaticLayout(r18, r10, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.preparingLayout = r0;
            if (r0.getLineCount() <= 0) goto L5;
            float r15 = r0.getLineWidth(0);
        L6:
            this.preparingLayoutWidth = r15;
            if (r0.getLineCount() <= 0) goto L9;
            r9 = r0.getLineLeft(0);
        L9:
            this.preparingLayoutLeft = r9;
            show();
            return;
        L5:
            r15 = 0.0f;
            goto L6
        }

        private void drawPreparing(Canvas r12, float r13) {
            float r2 = this.progressT.set(this.progress);
            float r7 = this.prepareRect.centerX();
            float r8 = this.prepareRect.top + AndroidUtilities.dp(48.0f);
            float r3 = AndroidUtilities.dp(25.0f);
            this.greyPaint.setAlpha((int) (51.0f * r13));
            r12.drawCircle(r7, r8, r3, this.greyPaint);
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(r7 - r3, r8 - r3, r7 + r3, r3 + r8);
            int r9 = (int) (255.0f * r13);
            this.whitePaint.setAlpha(r9);
            this.whitePaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
            r12.drawArc(r22, -90.0f, r2 * 360.0f, false, this.whitePaint);
            float r23 = this.cancelButton.getScale(0.15f);
            r12.save();
            r12.scale(r23, r23, r7, r8);
            this.whitePaint.setStrokeWidth(AndroidUtilities.dp(3.4f));
            r12.drawLine(r7 - AndroidUtilities.dp(7.0f), r8 - AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f) + r7, AndroidUtilities.dp(7.0f) + r8, this.whitePaint);
            r12.drawLine(r7 - AndroidUtilities.dp(7.0f), r8 + AndroidUtilities.dp(7.0f), r7 + AndroidUtilities.dp(7.0f), r8 - AndroidUtilities.dp(7.0f), this.whitePaint);
            r12.restore();
            r12.save();
            r12.translate((this.prepareRect.left + AndroidUtilities.dp(21.0f)) - this.preparingLayoutLeft, (this.prepareRect.bottom - AndroidUtilities.dp(18.0f)) - this.preparingLayout.getHeight());
            this.textPaint.setAlpha(r9);
            this.preparingLayout.draw(r12);
            r12.restore();
        }

        private void drawToast(Canvas r8, float r9) {
            RLottieDrawable r0 = this.lottieDrawable;
            if (r0 == null) goto L6;
            r0.setAlpha((int) (r9 * 255.0f));
            this.lottieDrawable.setBounds((int) (this.toastRect.left + AndroidUtilities.dp(9.0f)), (int) (this.toastRect.top + AndroidUtilities.dp(6.0f)), (int) (this.toastRect.left + AndroidUtilities.dp(45.0f)), (int) (this.toastRect.top + AndroidUtilities.dp(42.0f)));
            this.lottieDrawable.draw(r8);
        L6:
            if (this.doneLayout == null) goto L9;
            r8.save();
            r8.translate((this.toastRect.left + AndroidUtilities.dp(52.0f)) - this.doneLayoutLeft, this.toastRect.centerY() - (this.doneLayout.getHeight() / 2.0f));
            this.textPaint2.setAlpha((int) (r9 * 255.0f));
            this.doneLayout.draw(r8);
            r8.restore();
            return;
        }

        private /* synthetic */ void lambda$onDraw$0() {
            if ((getParent() instanceof ViewGroup) == false) goto L6;
            ((ViewGroup) getParent()).removeView(this);
            return;
        }

        public void hide() {
            Runnable r0 = this.hideRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.hideRunnable = null;
        L5:
            this.shown = false;
            invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            int r0 = r14.getSaveCount();
            AnimatedFloat r1 = this.showT;
            if (this.shown == false) goto L5;
            float r2 = 1.0f;
        L6:
            float r12 = r1.set(r2);
            AnimatedFloat r22 = this.t;
            if (this.preparing == false) goto L9;
            float r5 = 0.0f;
        L10:
            float r23 = r22.set(r5);
            float r6 = 1.0f - r23;
            this.dimPaint.setAlpha((int) ((90.0f * r6) * r12));
            r14.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.dimPaint);
            float r142 = Math.max(this.preparingLayoutWidth, AndroidUtilities.dp(54.0f)) + AndroidUtilities.dp(42.0f);
            float r52 = AndroidUtilities.dp(111.0f) + this.preparingLayout.getHeight();
            this.prepareRect.set((getWidth() - r142) / 2.0f, (getHeight() - r52) / 2.0f, (getWidth() + r142) / 2.0f, (getHeight() + r52) / 2.0f);
            float r143 = AndroidUtilities.dp(74.0f) + this.doneLayoutWidth;
            float r53 = AndroidUtilities.dp(48.0f);
            this.toastRect.set((getWidth() - r143) / 2.0f, (getHeight() - r53) / 2.0f, (getWidth() + r143) / 2.0f, (getHeight() + r53) / 2.0f);
            AndroidUtilities.lerp(this.prepareRect, this.toastRect, r23, this.currentRect);
            if (r12 < 1.0f) goto L13;
        L15:
            if (r12 < 1.0f) goto L17;
        L19:
            this.backgroundPaint.setAlpha((int) (204.0f * r12));
            r14.drawRoundRect(this.currentRect, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.backgroundPaint);
            r14.save();
            r14.clipRect(this.currentRect);
            if (r23 >= 1.0f) goto L23;
            drawPreparing(r14, r6 * r12);
        L23:
            if (r23 <= 0.0f) goto L25;
            drawToast(r14, r23 * r12);
        L25:
            r14.restoreToCount(r0);
            if (r12 <= 0.0f) goto L28;
            return;
        L28:
            if (this.shown == false) goto L30;
            return;
        L30:
            if (this.deleted == true) goto L35;
            this.deleted = true;
            post(new DownloadButton$PreparingVideoToast$$ExternalSyntheticLambda0(this));
            return;
        L35:
            return;
        L17:
            if (this.preparing == true) goto L19;
            r14.scale(AndroidUtilities.lerp(0.8f, 1.0f, r12), AndroidUtilities.lerp(0.8f, 1.0f, r12), this.currentRect.centerX(), this.currentRect.centerY());
            goto L19
        L13:
            if (this.preparing == false) goto L15;
            this.hiddenRect.set(getWidth() / 2.0f, getHeight() / 2.0f, getWidth() / 2.0f, getHeight() / 2.0f);
            RectF r54 = this.hiddenRect;
            RectF r8 = this.currentRect;
            AndroidUtilities.lerp(r54, r8, r12, r8);
            goto L15
        L9:
            r5 = 1.0f;
            goto L10
        L5:
            r2 = 0.0f;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            boolean r0 = this.currentRect.contains(r5.getX(), r5.getY());
            if (r5.getAction() != 0) goto L10;
            if (this.preparing == true) goto L7;
            if (r0 == false) goto L10;
        L7:
            this.cancelButton.setPressed(r0);
            return true;
        L10:
            if (r5.getAction() != 1) goto L23;
            if (this.cancelButton.isPressed() == false) goto L27;
            if (r0 == true) goto L15;
        L20:
            this.cancelButton.setPressed(false);
            return true;
        L15:
            if (this.preparing == false) goto L19;
            Runnable r52 = this.onCancel;
            if (r52 == null) goto L20;
            r52.run();
            goto L20
        L19:
            hide();
        L27:
            return super.onTouchEvent(r5);
        L23:
            if (r5.getAction() != 3) goto L27;
            this.cancelButton.setPressed(false);
            return true;
        }

        public void setDone(int r9, CharSequence r10, int r11) {
            RLottieDrawable r0 = this.lottieDrawable;
            if (r0 == null) goto L5;
            r0.setCallback(null);
            this.lottieDrawable.recycle(true);
        L5:
            RLottieDrawable r02 = new RLottieDrawable(r9, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f));
            this.lottieDrawable = r02;
            r02.setCallback(this);
            this.lottieDrawable.start();
            StaticLayout r03 = new StaticLayout(r10, this.textPaint2, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.doneLayout = r03;
            float r102 = 0.0f;
            if (r03.getLineCount() <= 0) goto L8;
            float r92 = this.doneLayout.getLineWidth(0);
        L9:
            this.doneLayoutWidth = r92;
            if (this.doneLayout.getLineCount() <= 0) goto L12;
            r102 = this.doneLayout.getLineLeft(0);
        L12:
            this.doneLayoutLeft = r102;
            this.preparing = false;
            invalidate();
            Runnable r93 = this.hideRunnable;
            if (r93 == null) goto L15;
            AndroidUtilities.cancelRunOnUIThread(r93);
        L15:
            DownloadButton$PreparingVideoToast$$ExternalSyntheticLambda1 r94 = new DownloadButton$PreparingVideoToast$$ExternalSyntheticLambda1(this);
            this.hideRunnable = r94;
            AndroidUtilities.runOnUIThread(r94, r11);
            return;
        L8:
            r92 = 0.0f;
            goto L9
        }

        public void setOnCancelListener(Runnable r1) {
            this.onCancel = r1;
        }

        public void setProgress(float r1) {
            this.progress = r1;
            invalidate();
        }

        public void show() {
            this.shown = true;
            invalidate();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.lottieDrawable) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$7UuLaFXG_rTecEgNEeqCDOpMvGs(DownloadButton r0, Float r1) {
        r0.lambda$onClickInternal$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$945T8mnLmcLn3YEBEODqXtzoRtg(DownloadButton r0) {
        r0.lambda$onClickInternal$1();
    }

    public static /* synthetic */ void $r8$lambda$F4rFgtsaf6vKAjNhLhnA9f0UmYY(DownloadButton r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$RMuRjg98fxKikfS7X0qAUkoy0hA(DownloadButton r0, File r1) {
        r0.lambda$onClickInternal$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$WjbbIa3A748GO_lBJHpFfczsAcg(DownloadButton r0) {
        r0.onClickInternal();
    }

    public static /* synthetic */ void $r8$lambda$atodHTd0Qklt29dLo2hhd1RBP9Q(DownloadButton r0, File r1) {
        r0.lambda$onClickInternal$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$bRG3N_Gj2l1bswOApmoX44aeDZE(DownloadButton r0) {
        r0.lambda$onClickInternal$5();
    }

    public static /* synthetic */ void $r8$lambda$epMODkQlgp9sWfEedsogqa_zds4(DownloadButton r0, File r1) {
        r0.lambda$onClickInternal$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$uoqqTpd2Db9xmT7V8hJzpId9vFI(DownloadButton r0, Uri r1) {
        r0.lambda$onClickInternal$6(r1);
    }

    /* renamed from: $r8$lambda$zKMBgX-v8HRrV9Hyb6IbAquFqFE, reason: not valid java name */
    public static /* synthetic */ void m19942$r8$lambda$zKMBgXv8HRrV9Hyb6IbAquFqFE(DownloadButton r0, Uri r1) {
        r0.lambda$onClickInternal$2(r1);
    }

    public DownloadButton(Context r1, Utilities.Callback<Runnable> r2, int r3, FrameLayout r4, Theme.ResourcesProvider r5) {
        super(r1);
        this.wasImageDownloading = true;
        this.wasVideoDownloading = true;
        this.prepare = r2;
        this.currentAccount = r3;
        this.container = r4;
        this.resourcesProvider = r5;
        setScaleType(ImageView.ScaleType.CENTER);
        setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        setBackground(Theme.createSelectorDrawable(553648127));
        setVisibility(8);
        setAlpha(0.0f);
        setOnClickListener(new DownloadButton$$ExternalSyntheticLambda0(this));
        this.progressDrawable = new CircularProgressDrawable(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(2.0f), -1);
        updateImage();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onClick();
    }

    private /* synthetic */ void lambda$onClickInternal$1() {
        this.preparing = false;
        BuildingVideo r1 = this.buildingVideo;
        if (r1 == null) goto L5;
        r1.stop(true);
        this.buildingVideo = null;
    L5:
        PreparingVideoToast r12 = this.toast;
        if (r12 == null) goto L8;
        r12.hide();
    L8:
        this.downloading = false;
        updateImage();
    }

    private /* synthetic */ void lambda$onClickInternal$2(Uri r5) {
        if (this.downloading == true) goto L5;
        return;
    L5:
        if (this.currentEntry == null) goto L10;
        this.toast.setDone(R.raw.ic_save_to_gallery, LocaleController.getString("VideoSavedHint"), 3500);
        this.downloading = false;
        updateImage();
        this.savedToGalleryUri = r5;
        return;
    }

    private /* synthetic */ void lambda$onClickInternal$3(File r9) {
        if (this.downloading == true) goto L5;
        return;
    L5:
        if (this.currentEntry == null) goto L10;
        MediaController.saveFile(r9.getAbsolutePath(), getContext(), 1, null, null, new DownloadButton$$ExternalSyntheticLambda8(this), false);
        return;
    }

    private /* synthetic */ void lambda$onClickInternal$4(Float r1) {
        PreparingVideoToast r0 = this.toast;
        if (r0 == null) goto L6;
        r0.setProgress(r1.floatValue());
        return;
    }

    private /* synthetic */ void lambda$onClickInternal$5() {
        if (this.downloading == true) goto L5;
        return;
    L5:
        if (this.currentEntry == null) goto L10;
        this.toast.setDone(R.raw.error, LocaleController.getString("VideoConvertFail"), 3500);
        this.downloading = false;
        updateImage();
        return;
    }

    private /* synthetic */ void lambda$onClickInternal$6(Uri r5) {
        this.downloading = false;
        updateImage();
        PreparingVideoToast r0 = this.toast;
        if (r0 == null) goto L5;
        r0.hide();
        this.toast = null;
    L5:
        PreparingVideoToast r02 = new PreparingVideoToast(getContext());
        this.toast = r02;
        r02.setDone(R.raw.ic_save_to_gallery, LocaleController.getString("PhotoSavedHint"), 2500);
        this.container.addView(this.toast);
        this.savedToGalleryUri = r5;
    }

    private /* synthetic */ void lambda$onClickInternal$7(File r8) {
        MediaController.saveFile(r8.getAbsolutePath(), getContext(), 0, null, null, new DownloadButton$$ExternalSyntheticLambda9(this), false);
    }

    private /* synthetic */ void lambda$onClickInternal$8(File r2) {
        this.currentEntry.buildPhoto(r2);
        if (this.downloading == true) goto L5;
        return;
    L5:
        if (this.currentEntry == null) goto L10;
        AndroidUtilities.runOnUIThread(new DownloadButton$$ExternalSyntheticLambda7(this, r2));
        return;
    }

    private void onClick() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 <= 28) goto L7;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L7;
    L13:
        if (this.downloading == false) goto L15;
        return;
    L15:
        if (this.currentEntry != null) goto L18;
        return;
    L18:
        if (this.savedToGalleryUri != null) goto L20;
    L29:
        this.downloading = true;
        PreparingVideoToast r1 = this.toast;
        if (r1 == null) goto L32;
        r1.hide();
        this.toast = null;
    L32:
        BuildingVideo r12 = this.buildingVideo;
        if (r12 == null) goto L35;
        r12.stop(true);
        this.buildingVideo = null;
    L35:
        Utilities.Callback<Runnable> r13 = this.prepare;
        if (r13 == null) goto L38;
        this.preparing = true;
        r13.run(new DownloadButton$$ExternalSyntheticLambda1(this));
    L38:
        updateImage();
        if (this.prepare != null) goto L46;
        onClickInternal();
        return;
    L46:
        return;
    L20:
        if (r0 < 30) goto L23;
        getContext().getContentResolver().delete(this.savedToGalleryUri, null);
        this.savedToGalleryUri = null;
        goto L29
    L23:
        if (r0 >= 29) goto L29;
        new File(this.savedToGalleryUri.toString()).delete();     // Catch: Exception -> L26
    L28:
        this.savedToGalleryUri = null;
    L26:
        e = move-exception;
        FileLog.e(e);
    L7:
        if (getContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L13;
        Activity r3 = AndroidUtilities.findActivity(getContext());
        if (r3 == null) goto L44;
        r3.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 113);
        return;
    }

    private void onClickInternal() {
        if (this.preparing == false) goto L17;
        StoryEntry r0 = this.currentEntry;
        if (r0 == null) goto L18;
        this.preparing = false;
        if (r0.wouldBeVideo() == false) goto L10;
        this.downloadingVideo = true;
        PreparingVideoToast r02 = new PreparingVideoToast(getContext());
        this.toast = r02;
        r02.setOnCancelListener(new DownloadButton$$ExternalSyntheticLambda2(this));
        this.container.addView(this.toast);
        File r5 = AndroidUtilities.generateVideoPath();
        this.buildingVideo = new BuildingVideo(this.currentAccount, this.currentEntry, r5, new DownloadButton$$ExternalSyntheticLambda3(this, r5), new DownloadButton$$ExternalSyntheticLambda4(this), new DownloadButton$$ExternalSyntheticLambda5(this));
    L15:
        updateImage();
        return;
    L10:
        this.downloadingVideo = false;
        File r03 = AndroidUtilities.generatePicturePath(false, "png");
        if (r03 != null) goto L14;
        this.toast.setDone(R.raw.error, LocaleController.getString("UnknownError"), 3500);
        this.downloading = false;
        updateImage();
        return;
    L14:
        Utilities.themeQueue.postRunnable(new DownloadButton$$ExternalSyntheticLambda6(this, r03));
        goto L15
    L18:
        return;
    }

    private void updateImage() {
        boolean r0 = this.wasImageDownloading;
        boolean r1 = this.downloading;
        boolean r2 = false;
        if (r1 == true) goto L5;
    L7:
        boolean r4 = false;
    L8:
        if (r0 == r4) goto L18;
        if (r1 == true) goto L11;
    L13:
        boolean r02 = false;
    L14:
        this.wasImageDownloading = r02;
        if (r02 == false) goto L17;
        AndroidUtilities.updateImageViewImageAnimated(this, this.progressDrawable);
        goto L18
    L17:
        AndroidUtilities.updateImageViewImageAnimated(this, R.drawable.media_download);
        goto L18
    L11:
        if (this.downloadingVideo == true) goto L13;
        r02 = true;
    L18:
        boolean r03 = this.wasVideoDownloading;
        if (this.downloading == true) goto L21;
    L23:
        boolean r12 = false;
    L24:
        if (r03 == r12) goto L36;
        clearAnimation();
        ViewPropertyAnimator r04 = animate();
        if (this.downloading == true) goto L28;
    L30:
        this.wasVideoDownloading = r2;
        if (r2 == false) goto L33;
        float r5 = 0.4f;
    L34:
        r04.alpha(r5).start();
        return;
    L33:
        r5 = 1.0f;
        goto L34
    L28:
        if (this.downloadingVideo == false) goto L30;
        r2 = true;
        goto L30
    L36:
        return;
    L21:
        if (this.downloadingVideo == false) goto L23;
        r12 = true;
        goto L24
    L5:
        if (this.downloadingVideo == true) goto L7;
        r4 = true;
        goto L8
    }

    public void setEntry(StoryEntry r4) {
        this.savedToGalleryUri = null;
        this.currentEntry = r4;
        BuildingVideo r1 = this.buildingVideo;
        if (r1 == null) goto L5;
        r1.stop(true);
        this.buildingVideo = null;
    L5:
        PreparingVideoToast r12 = this.toast;
        if (r12 == null) goto L8;
        r12.hide();
        this.toast = null;
    L8:
        if (r4 != null) goto L11;
        this.downloading = false;
        updateImage();
        return;
    }

    public void showFailedVideo() {
        showToast(R.raw.error, LocaleController.getString("VideoConvertFail"));
    }

    public void showToast(int r3, CharSequence r4) {
        PreparingVideoToast r0 = this.toast;
        if (r0 == null) goto L5;
        r0.hide();
        this.toast = null;
    L5:
        PreparingVideoToast r02 = new PreparingVideoToast(getContext());
        this.toast = r02;
        r02.setDone(r3, r4, 3500);
        this.container.addView(this.toast);
    }
}
