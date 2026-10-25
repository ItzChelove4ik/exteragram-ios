package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import java.io.File;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class OtherDocumentPlaceholderDrawable extends RecyclableDrawable implements DownloadController.FileDownloadProgressListener {
    private static TextPaint buttonPaint;
    private static DecelerateInterpolator decelerateInterpolator;
    private static TextPaint docPaint;
    private static TextPaint namePaint;
    private static TextPaint openPaint;
    private static Paint paint;
    private static TextPaint percentPaint;
    private static Paint progressPaint;
    private static TextPaint sizePaint;
    private int TAG;
    private float animatedAlphaValue;
    private float animatedProgressValue;
    private float animationProgressStart;
    private float currentProgress;
    private long currentProgressTime;
    private String ext;
    private String fileName;
    private String fileSize;
    private long lastUpdateTime;
    private boolean loaded;
    private boolean loading;
    private MessageObject parentMessageObject;
    private View parentView;
    private String progress;
    private boolean progressVisible;
    private Drawable thumbDrawable;

    static {
        paint = new Paint();
        progressPaint = new Paint(1);
        docPaint = new TextPaint(1);
        namePaint = new TextPaint(1);
        sizePaint = new TextPaint(1);
        buttonPaint = new TextPaint(1);
        percentPaint = new TextPaint(1);
        openPaint = new TextPaint(1);
        decelerateInterpolator = new DecelerateInterpolator();
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(-14209998);
        docPaint.setColor(-1);
        namePaint.setColor(-1);
        sizePaint.setColor(-10327179);
        buttonPaint.setColor(-10327179);
        percentPaint.setColor(-1);
        openPaint.setColor(-1);
        docPaint.setTypeface(AndroidUtilities.bold());
        namePaint.setTypeface(AndroidUtilities.bold());
        buttonPaint.setTypeface(AndroidUtilities.bold());
        percentPaint.setTypeface(AndroidUtilities.bold());
        openPaint.setTypeface(AndroidUtilities.bold());
    }

    public OtherDocumentPlaceholderDrawable(Context r6, View r7, MessageObject r8) {
        this.lastUpdateTime = 0;
        this.currentProgress = 0.0f;
        this.animationProgressStart = 0.0f;
        this.currentProgressTime = 0;
        this.animatedProgressValue = 0.0f;
        this.animatedAlphaValue = 1.0f;
        docPaint.setTextSize(AndroidUtilities.dp(14.0f));
        namePaint.setTextSize(AndroidUtilities.dp(19.0f));
        sizePaint.setTextSize(AndroidUtilities.dp(15.0f));
        buttonPaint.setTextSize(AndroidUtilities.dp(15.0f));
        percentPaint.setTextSize(AndroidUtilities.dp(15.0f));
        openPaint.setTextSize(AndroidUtilities.dp(15.0f));
        progressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.parentView = r7;
        this.parentMessageObject = r8;
        this.TAG = DownloadController.getInstance(r8.currentAccount).generateObserverTag();
        TLRPC.Document r72 = r8.getDocument();
        if (r72 == null) goto L17;
        String r0 = FileLoader.getDocumentFileName(r8.getDocument());
        this.fileName = r0;
        if (TextUtils.isEmpty(r0) == false) goto L7;
        this.fileName = "name";
    L7:
        int r02 = this.fileName.lastIndexOf(46);
        if (r02 != (-1)) goto L10;
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        this.ext = r03;
        if (((int) Math.ceil(docPaint.measureText(r03))) <= AndroidUtilities.dp(40.0f)) goto L14;
        this.ext = TextUtils.ellipsize(this.ext, docPaint, AndroidUtilities.dp(40.0f), TextUtils.TruncateAt.END).toString();
    L14:
        this.thumbDrawable = r6.getResources().getDrawable(AndroidUtilities.getThumbForNameOrMime(this.fileName, r8.getDocument().mime_type, true)).mutate();
        this.fileSize = AndroidUtilities.formatFileSize(r72.size);
        if (((int) Math.ceil(namePaint.measureText(this.fileName))) <= AndroidUtilities.dp(320.0f)) goto L17;
        this.fileName = TextUtils.ellipsize(this.fileName, namePaint, AndroidUtilities.dp(320.0f), TextUtils.TruncateAt.END).toString();
        goto L17
    L10:
        r03 = this.fileName.substring(r02 + 1).toUpperCase();
    L17:
        checkFileExist();
    }

    private void updateAnimation() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        float r02 = this.animatedProgressValue;
        if (r02 == 1.0f) goto L13;
        float r4 = this.currentProgress;
        if (r02 == r4) goto L13;
        float r03 = this.animationProgressStart;
        float r6 = r4 - r03;
        if (r6 <= 0.0f) goto L12;
        long r7 = this.currentProgressTime + r2;
        this.currentProgressTime = r7;
        if (r7 < 300) goto L11;
        this.animatedProgressValue = r4;
        this.animationProgressStart = r4;
        this.currentProgressTime = 0;
        goto L12
    L11:
        this.animatedProgressValue = r03 + (r6 * decelerateInterpolator.getInterpolation(r7 / 300.0f));
    L12:
        this.parentView.invalidate();
    L13:
        float r04 = this.animatedProgressValue;
        if (r04 >= 1.0f) goto L16;
        return;
    L16:
        if (r04 != 1.0f) goto L25;
        float r05 = this.animatedAlphaValue;
        if (r05 == 0.0f) goto L26;
        float r06 = r05 - (r2 / 200.0f);
        this.animatedAlphaValue = r06;
        if (r06 > 0.0f) goto L22;
        this.animatedAlphaValue = 0.0f;
    L22:
        this.parentView.invalidate();
        return;
    L26:
        return;
    }

    public void checkFileExist() {
        MessageObject r0 = this.parentMessageObject;
        if (r0 == null) goto L24;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02.media == null) goto L24;
        if (TextUtils.isEmpty(r02.attachPath) == true) goto L11;
        if (new File(this.parentMessageObject.messageOwner.attachPath).exists() == false) goto L11;
    L13:
        String r03 = null;
    L14:
        this.loaded = false;
        if (r03 != null) goto L17;
        this.progressVisible = false;
        this.loading = false;
        this.loaded = true;
        DownloadController.getInstance(this.parentMessageObject.currentAccount).removeLoadingFileObserver(this);
    L25:
        this.parentView.invalidate();
        return;
    L17:
        DownloadController.getInstance(this.parentMessageObject.currentAccount).addLoadingFileObserver(r03, this);
        boolean r4 = FileLoader.getInstance(this.parentMessageObject.currentAccount).isLoadingFile(r03);
        this.loading = r4;
        if (r4 == false) goto L23;
        this.progressVisible = true;
        Float r04 = ImageLoader.getInstance().getFileProgress(r03);
        if (r04 != null) goto L22;
        r04 = Float.valueOf(0.0f);
    L22:
        setProgress(r04.floatValue(), false);
        goto L25
    L23:
        this.progressVisible = false;
    L11:
        if (FileLoader.getInstance(UserConfig.selectedAccount).getPathToMessage(this.parentMessageObject.messageOwner).exists() == true) goto L13;
        r03 = FileLoader.getAttachFileName(this.parentMessageObject.getDocument());
    L24:
        this.loading = false;
        this.loaded = true;
        this.progressVisible = false;
        setProgress(0.0f, false);
        DownloadController.getInstance(this.parentMessageObject.currentAccount).removeLoadingFileObserver(this);
        goto L25
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r12) {
        Rect r1 = getBounds();
        int r6 = r1.width();
        int r7 = r1.height();
        r12.save();
        r12.translate(r1.left, r1.top);
        r12.drawRect(0.0f, 0.0f, r6, r7, paint);
        int r72 = (r7 - AndroidUtilities.dp(240.0f)) / 2;
        int r2 = (r6 - AndroidUtilities.dp(48.0f)) / 2;
        this.thumbDrawable.setBounds(r2, r72, AndroidUtilities.dp(48.0f) + r2, AndroidUtilities.dp(48.0f) + r72);
        this.thumbDrawable.draw(r12);
        int r13 = (int) Math.ceil(docPaint.measureText(this.ext));
        r12.drawText(this.ext, (r6 - r13) / 2, AndroidUtilities.dp(31.0f) + r72, docPaint);
        int r14 = (int) Math.ceil(namePaint.measureText(this.fileName));
        r12.drawText(this.fileName, (r6 - r14) / 2, AndroidUtilities.dp(96.0f) + r72, namePaint);
        int r15 = (int) Math.ceil(sizePaint.measureText(this.fileSize));
        r12.drawText(this.fileSize, (r6 - r15) / 2, AndroidUtilities.dp(125.0f) + r72, sizePaint);
        if (this.loaded == false) goto L6;
        String r16 = LocaleController.getString(R.string.OpenFile);
        TextPaint r22 = openPaint;
        int r3 = 0;
    L10:
        r12.drawText(r16, (r6 - ((int) Math.ceil(r22.measureText(r16)))) / 2, (AndroidUtilities.dp(235.0f) + r72) + r3, r22);
        if (this.progressVisible == true) goto L13;
    L16:
        r12.restore();
        return;
    L13:
        if (this.progress == null) goto L15;
        int r17 = (int) Math.ceil(percentPaint.measureText(r1));
        r12.drawText(this.progress, (r6 - r17) / 2, AndroidUtilities.dp(210.0f) + r72, percentPaint);
    L15:
        int r62 = (r6 - AndroidUtilities.dp(240.0f)) / 2;
        int r73 = r72 + AndroidUtilities.dp(232.0f);
        progressPaint.setColor(-10327179);
        progressPaint.setAlpha((int) (this.animatedAlphaValue * 255.0f));
        float r23 = r73;
        r12.drawRect(((int) (AndroidUtilities.dp(240.0f) * this.animatedProgressValue)) + r62, r23, AndroidUtilities.dp(240.0f) + r62, AndroidUtilities.dp(2.0f) + r73, progressPaint);
        progressPaint.setColor(-1);
        progressPaint.setAlpha((int) (this.animatedAlphaValue * 255.0f));
        float r18 = r62;
        r12.drawRect(r18, r23, r18 + (AndroidUtilities.dp(240.0f) * this.animatedProgressValue), r73 + AndroidUtilities.dp(2.0f), progressPaint);
        updateAnimation();
        goto L16
    L6:
        if (this.loading == false) goto L8;
        r16 = LocaleController.getString(R.string.Cancel).toUpperCase();
    L9:
        r3 = AndroidUtilities.dp(28.0f);
        r22 = buttonPaint;
        goto L10
    L8:
        r16 = LocaleController.getString(R.string.TapToDownload);
        goto L9
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.parentView.getMeasuredHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.parentView.getMeasuredWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.parentView.getMeasuredHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.parentView.getMeasuredWidth();
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        checkFileExist();
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
        if (this.progressVisible == true) goto L5;
        checkFileExist();
    L5:
        setProgress(Math.min(1.0f, r2 / r4), true);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r2) {
        setProgress(1.0f, true);
        checkFileExist();
    }

    @Override // org.telegram.ui.Components.RecyclableDrawable
    public void recycle() {
        DownloadController.getInstance(this.parentMessageObject.currentAccount).removeLoadingFileObserver(this);
        this.parentView = null;
        this.parentMessageObject = null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        Drawable r0 = this.thumbDrawable;
        if (r0 == null) goto L5;
        r0.setAlpha(r1);
    L5:
        paint.setAlpha(r1);
        docPaint.setAlpha(r1);
        namePaint.setAlpha(r1);
        sizePaint.setAlpha(r1);
        buttonPaint.setAlpha(r1);
        percentPaint.setAlpha(r1);
        openPaint.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setProgress(float r2, boolean r3) {
        if (r3 == true) goto L4;
        this.animatedProgressValue = r2;
        this.animationProgressStart = r2;
    L5:
        this.progress = String.format("%d%%", new Object[]{Integer.valueOf((int) (100.0f * r2))});
        if (r2 == 1.0f) goto L8;
        this.animatedAlphaValue = 1.0f;
    L8:
        this.currentProgress = r2;
        this.currentProgressTime = 0;
        this.lastUpdateTime = System.currentTimeMillis();
        this.parentView.invalidate();
        return;
    L4:
        this.animationProgressStart = this.animatedProgressValue;
        goto L5
    }
}
