package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewOutlineProvider;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RadialProgress2;

/* loaded from: classes6.dex */
public class PatternCell extends BackupImageView implements DownloadController.FileDownloadProgressListener {
    private final int SIZE;
    private int TAG;
    private MotionBackgroundDrawable backgroundDrawable;
    private Paint backgroundPaint;
    private int currentAccount;
    private int currentBackgroundColor;
    private int currentGradientAngle;
    private int currentGradientColor1;
    private int currentGradientColor2;
    private int currentGradientColor3;
    private TLRPC.TL_wallPaper currentPattern;
    private PatternCellDelegate delegate;
    private LinearGradient gradientShader;
    private int maxWallpaperSize;
    private RadialProgress2 radialProgress;
    private RectF rect;

    /* renamed from: org.telegram.ui.Cells.PatternCell$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewOutlineProvider {
        final /* synthetic */ PatternCell this$0;

        public AnonymousClass1(PatternCell r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), r7.getMeasuredWidth() - AndroidUtilities.dp(1.0f), r7.getMeasuredHeight() - AndroidUtilities.dp(1.0f), AndroidUtilities.dp(6.0f));
        }
    }

    public interface PatternCellDelegate {
        int getBackgroundColor();

        int getBackgroundGradientAngle();

        int getBackgroundGradientColor1();

        int getBackgroundGradientColor2();

        int getBackgroundGradientColor3();

        int getCheckColor();

        float getIntensity();

        int getPatternColor();

        TLRPC.TL_wallPaper getSelectedPattern();
    }

    public PatternCell(Context r3, int r4, PatternCellDelegate r5) {
        super(r3);
        this.SIZE = 100;
        this.rect = new RectF();
        this.currentAccount = UserConfig.selectedAccount;
        setRoundRadius(AndroidUtilities.dp(6.0f));
        this.maxWallpaperSize = r4;
        this.delegate = r5;
        RadialProgress2 r32 = new RadialProgress2(this);
        this.radialProgress = r32;
        r32.setProgressRect(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(70.0f), AndroidUtilities.dp(70.0f));
        this.backgroundPaint = new Paint(3);
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        setOutlineProvider(new AnonymousClass1(this));
        setClipToOutline(true);
    }

    private void updateButtonState(Object r6, boolean r7, boolean r8) {
        boolean r0 = r6 instanceof TLRPC.TL_wallPaper;
        if (r0 == true) goto L10;
        if ((r6 instanceof MediaController.SearchImage) == true) goto L10;
        this.radialProgress.setIcon(6, r7, r8);
        return;
    L10:
        if (r0 == false) goto L15;
        TLRPC.TL_wallPaper r62 = (TLRPC.TL_wallPaper) r6;
        String r02 = FileLoader.getAttachFileName(r62.document);
        if (TextUtils.isEmpty(r02) == true) goto L32;
        File r63 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r62.document, true);
    L22:
        boolean r64 = r63.exists();
        int r2 = this.currentAccount;
        if (r64 == false) goto L26;
        DownloadController.getInstance(r2).removeLoadingFileObserver(this);
        this.radialProgress.setProgress(1.0f, r8);
        this.radialProgress.setIcon(6, r7, r8);
        return;
    L26:
        DownloadController.getInstance(r2).addLoadingFileObserver(r02, null, this);
        FileLoader.getInstance(this.currentAccount).isLoadingFile(r02);
        Float r65 = ImageLoader.getInstance().getFileProgress(r02);
        RadialProgress2 r03 = this.radialProgress;
        if (r65 == null) goto L29;
        r03.setProgress(r65.floatValue(), r8);
    L30:
        this.radialProgress.setIcon(10, r7, r8);
        return;
    L29:
        r03.setProgress(0.0f, r8);
        goto L30
    L32:
        return;
    L15:
        MediaController.SearchImage r66 = (MediaController.SearchImage) r6;
        TLRPC.Photo r04 = r66.photo;
        if (r04 == null) goto L18;
        TLRPC.PhotoSize r67 = FileLoader.getClosestPhotoSizeWithSize(r04.sizes, this.maxWallpaperSize, true);
        File r05 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r67, true);
        r02 = FileLoader.getAttachFileName(r67);
        r63 = r05;
    L20:
        if (TextUtils.isEmpty(r02) == false) goto L22;
        return;
    L18:
        r63 = ImageLoader.getHttpFilePath(r66.imageUrl, "jpg");
        r02 = r63.getName();
        goto L20
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
    }

    @Override // org.telegram.ui.Components.BackupImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateSelected(false);
    }

    @Override // org.telegram.ui.Components.BackupImageView, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas r24) {
        float r2 = this.delegate.getIntensity();
        this.imageReceiver.setBlendMode(null);
        int r6 = this.delegate.getBackgroundColor();
        int r7 = this.delegate.getBackgroundGradientColor1();
        int r8 = this.delegate.getBackgroundGradientColor2();
        int r9 = this.delegate.getBackgroundGradientColor3();
        int r3 = this.delegate.getBackgroundGradientAngle();
        int r12 = this.delegate.getCheckColor();
        if (r7 != 0) goto L5;
        this.gradientShader = null;
        this.backgroundDrawable = null;
        this.imageReceiver.setGradientBitmap(null);
    L31:
        MotionBackgroundDrawable r22 = this.backgroundDrawable;
        if (r22 == null) goto L34;
        r22.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.backgroundDrawable.draw(r24);
    L38:
        super.onDraw(r24);
        if (this.radialProgress.getIcon() == 4) goto L42;
        this.radialProgress.setColors(r12, r12, -1, -1);
        this.radialProgress.draw(r24);
        return;
    L42:
        return;
    L34:
        this.backgroundPaint.setShader(this.gradientShader);
        if (this.gradientShader != null) goto L37;
        this.backgroundPaint.setColor(r6);
    L37:
        this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        r24.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.backgroundPaint);
        goto L38
    L5:
        if (this.gradientShader != null) goto L7;
    L16:
        this.currentBackgroundColor = r6;
        this.currentGradientColor1 = r7;
        this.currentGradientColor2 = r8;
        this.currentGradientColor3 = r9;
        this.currentGradientAngle = r3;
        if (r8 == 0) goto L29;
        this.gradientShader = null;
        MotionBackgroundDrawable r5 = this.backgroundDrawable;
        if (r5 == null) goto L21;
        r5.setColors(r6, r7, r8, r9, 0, false);
    L22:
        ImageReceiver r32 = this.imageReceiver;
        if (r2 >= 0.0f) goto L25;
        r32.setGradientBitmap(this.backgroundDrawable.getBitmap());
        goto L31
    L25:
        r32.setGradientBitmap(null);
        int r23 = Build.VERSION.SDK_INT;
        ImageReceiver r33 = this.imageReceiver;
        if (r23 < 29) goto L28;
        r33.setBlendMode(PatternCell$$ExternalSyntheticApiModelOutline0.m());
        goto L31
    L28:
        r33.setColorFilter(new PorterDuffColorFilter(this.delegate.getPatternColor(), PorterDuff.Mode.SRC_IN));
        goto L31
    L21:
        MotionBackgroundDrawable r52 = new MotionBackgroundDrawable(r6, r7, r8, r9, true);
        this.backgroundDrawable = r52;
        r52.setRoundRadius(AndroidUtilities.dp(6.0f));
        this.backgroundDrawable.setParentView(this);
        goto L22
    L29:
        Rect r25 = BackgroundGradientDrawable.getGradientPoints(r3, getMeasuredWidth(), getMeasuredHeight());
        this.gradientShader = new LinearGradient(r25.left, r25.top, r25.right, r25.bottom, new int[]{r6, r7}, null, Shader.TileMode.CLAMP);
        this.backgroundDrawable = null;
        this.imageReceiver.setGradientBitmap(null);
        goto L31
    L7:
        if (r6 != this.currentBackgroundColor) goto L16;
        if (r7 != this.currentGradientColor1) goto L16;
        if (r8 != this.currentGradientColor2) goto L16;
        if (r9 != this.currentGradientColor3) goto L16;
        if (r3 == this.currentGradientAngle) goto L31;
        goto L16
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r6, boolean r7) {
        TLRPC.TL_wallPaper r62 = this.delegate.getSelectedPattern();
        TLRPC.TL_wallPaper r0 = this.currentPattern;
        if (r0 != null) goto L5;
        if (r62 != null) goto L5;
    L10:
        if (r7 == false) goto L13;
        this.radialProgress.setIcon(4, false, true);
        return;
    L13:
        updateButtonState(r0, true, r7);
        return;
    L5:
        if (r62 == null) goto L15;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.id == r62.id) goto L10;
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(AndroidUtilities.dp(100.0f), AndroidUtilities.dp(100.0f));
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
        this.radialProgress.setProgress(Math.min(1.0f, r2 / r4), true);
        TLRPC.TL_wallPaper r12 = this.delegate.getSelectedPattern();
        TLRPC.TL_wallPaper r22 = this.currentPattern;
        if (r22 != null) goto L5;
        if (r12 != null) goto L5;
    L10:
        if (this.radialProgress.getIcon() == 10) goto L16;
        updateButtonState(this.currentPattern, false, true);
        return;
    L16:
        return;
    L5:
        if (r12 == null) goto L13;
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.id == r12.id) goto L10;
        return;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r7) {
        this.radialProgress.setProgress(1.0f, true);
        TLRPC.TL_wallPaper r72 = this.delegate.getSelectedPattern();
        TLRPC.TL_wallPaper r0 = this.currentPattern;
        if (r0 != null) goto L5;
        if (r72 != null) goto L5;
    L9:
        updateButtonState(r0, false, true);
        return;
    L5:
        if (r72 == null) goto L11;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.id == r72.id) goto L9;
        return;
    }

    public void setPattern(TLRPC.TL_wallPaper r13) {
        this.currentPattern = r13;
        if (r13 == null) goto L5;
        PatternCell r2 = this;
        r2.setImage(ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r13.document.thumbs, AndroidUtilities.dp(100.0f)), r13.document), "100_100", null, null, "png", 0, 1, r13);
    L6:
        r2.updateSelected(false);
        return;
    L5:
        r2 = this;
        r2.setImageDrawable(null);
        goto L6
    }

    public void updateSelected(boolean r8) {
        TLRPC.TL_wallPaper r0 = this.delegate.getSelectedPattern();
        TLRPC.TL_wallPaper r1 = this.currentPattern;
        if (r1 != null) goto L5;
        if (r0 != null) goto L5;
    L9:
        updateButtonState(r0, false, r8);
    L11:
        invalidate();
        return;
    L5:
        if (r0 == null) goto L10;
        if (r1 == null) goto L10;
        if (r1.id == r0.id) goto L9;
    L10:
        this.radialProgress.setIcon(4, false, r8);
        goto L11
    }
}
