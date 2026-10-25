package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RLottieDrawable;

/* loaded from: classes3.dex */
public class DownloadProgressIcon extends View implements NotificationCenter.NotificationCenterDelegate {
    private int currentAccount;
    int currentColor;
    ArrayList<ProgressObserver> currentListeners;
    float currentProgress;
    RLottieDrawable downloadCompleteDrawable;
    ImageReceiver downloadCompleteImageReceiver;
    RLottieDrawable downloadDrawable;
    ImageReceiver downloadImageReceiver;
    Paint paint;
    Paint paint2;
    float progress;
    float progressDt;
    boolean showCompletedIcon;
    private boolean wasDrawn;

    public class ProgressObserver implements DownloadController.FileDownloadProgressListener {
        long downloaded;
        private final String fileName;
        final /* synthetic */ DownloadProgressIcon this$0;
        long total;

        /* renamed from: -$$Nest$fgetfileName, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m14679$$Nest$fgetfileName(ProgressObserver r0) {
            return r0.fileName;
        }

        private ProgressObserver(DownloadProgressIcon r1, String r2) {
            this.this$0 = r1;
            this.fileName = r2;
        }

        public /* synthetic */ ProgressObserver(DownloadProgressIcon r1, String r2, DownloadProgressIconIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return 0;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r1, long r2, long r4) {
            this.downloaded = r2;
            this.total = r4;
            this.this$0.updateProgress();
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r1) {
        }
    }

    public DownloadProgressIcon(int r9, Context r10) {
        super(r10);
        this.paint = new Paint(1);
        this.paint2 = new Paint(1);
        this.currentListeners = new ArrayList();
        this.downloadImageReceiver = new ImageReceiver(this);
        ImageReceiver r102 = new ImageReceiver(this);
        this.downloadCompleteImageReceiver = r102;
        this.currentAccount = r9;
        this.downloadImageReceiver.ignoreNotifications = true;
        r102.ignoreNotifications = true;
        this.downloadDrawable = new RLottieDrawable(R.raw.download_progress, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.downloadCompleteDrawable = new RLottieDrawable(R.raw.download_finish, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        RLottieDrawable r92 = this.downloadDrawable;
        int r103 = Theme.key_actionBarDefaultIcon;
        r92.setLayerColor("Shape Layer 3", Theme.getColor(r103));
        this.downloadDrawable.setLayerColor("Arrow 4", Theme.getColor(r103));
        this.downloadCompleteDrawable.setLayerColor("Shape Layer 8", Theme.getColor(r103));
        this.downloadCompleteDrawable.setLayerColor("Arrow 8", Theme.getColor(r103));
        this.downloadImageReceiver.setImageBitmap(this.downloadDrawable);
        this.downloadCompleteImageReceiver.setImageBitmap(this.downloadCompleteDrawable);
        this.downloadImageReceiver.setAutoRepeat(1);
        this.downloadDrawable.setAutoRepeat(1);
        this.downloadDrawable.start();
    }

    private void detachCurrentListeners() {
        int r0 = 0;
    L4:
        if (r0 >= this.currentListeners.size()) goto L6;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this.currentListeners.get(r0));
        r0 = r0 + 1;
        goto L4
    L6:
        this.currentListeners.clear();
    }

    private void updateDownloadingListeners() {
        DownloadController r0 = DownloadController.getInstance(this.currentAccount);
        HashMap r1 = new HashMap();
        int r3 = 0;
    L3:
        int r4 = this.currentListeners.size();
        ArrayList<ProgressObserver> r5 = this.currentListeners;
        if (r3 >= r4) goto L6;
        r1.put(ProgressObserver.m14679$$Nest$fgetfileName(r5.get(r3)), this.currentListeners.get(r3));
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this.currentListeners.get(r3));
        r3 = r3 + 1;
        goto L3
    L6:
        r5.clear();
        int r32 = 0;
    L8:
        if (r32 >= r0.downloadingFiles.size()) goto L17;
        String r42 = r0.downloadingFiles.get(r32).getFileName();
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r42) == false) goto L15;
        ProgressObserver r52 = (ProgressObserver) r1.get(r42);
        if (r52 != null) goto L14;
        r52 = new ProgressObserver(this, r42, null);
    L14:
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(r42, r52);
        this.currentListeners.add(r52);
    L15:
        r32 = r32 + 1;
        goto L8
    L17:
        if (this.currentListeners.size() == 0) goto L19;
        return;
    L19:
        if (this.wasDrawn == false) goto L21;
        return;
    L21:
        if (DownloadController.getInstance(this.currentAccount).hasUnviewedDownloads() == false) goto L24;
        this.progress = 1.0f;
        this.currentProgress = 1.0f;
        this.showCompletedIcon = true;
        return;
    L24:
        this.progress = 0.0f;
        this.currentProgress = 0.0f;
        this.showCompletedIcon = false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.onDownloadingFilesChanged) goto L6;
        updateDownloadingListeners();
        updateProgress();
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateDownloadingListeners();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.onDownloadingFilesChanged);
        this.downloadImageReceiver.onAttachedToWindow();
        this.downloadCompleteImageReceiver.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detachCurrentListeners();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.onDownloadingFilesChanged);
        this.downloadImageReceiver.onDetachedFromWindow();
        this.downloadCompleteImageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        super.onDraw(r10);
        if (getAlpha() == 0.0f) goto L40;
        int r0 = this.currentColor;
        int r2 = Theme.key_actionBarDefaultIcon;
        if (r0 == Theme.getColor(r2)) goto L8;
        this.currentColor = Theme.getColor(r2);
        this.paint.setColor(Theme.getColor(r2));
        this.paint2.setColor(Theme.getColor(r2));
        ImageReceiver r02 = this.downloadImageReceiver;
        int r4 = Theme.getColor(r2);
        PorterDuff.Mode r5 = PorterDuff.Mode.SRC_IN;
        r02.setColorFilter(new PorterDuffColorFilter(r4, r5));
        this.downloadCompleteImageReceiver.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2), r5));
        this.paint2.setAlpha(100);
    L8:
        float r03 = this.currentProgress;
        float r22 = this.progress;
        if (r03 == r22) goto L21;
        float r3 = this.progressDt;
        float r04 = r03 + r3;
        this.currentProgress = r04;
        if (r3 <= 0.0f) goto L16;
        if (r04 <= r22) goto L16;
        this.currentProgress = r22;
    L16:
        if (r3 < 0.0f) goto L18;
    L20:
        invalidate();
        goto L21
    L18:
        if (r04 >= r22) goto L20;
        this.currentProgress = r22;
    L21:
        int r05 = (getMeasuredHeight() / 2) + AndroidUtilities.dp(8.0f);
        float r32 = AndroidUtilities.dp(1.0f);
        float r42 = AndroidUtilities.dp(16.0f);
        RectF r6 = AndroidUtilities.rectTmp;
        float r06 = r05;
        float r7 = r06 - r32;
        float r07 = r06 + r32;
        r6.set(r42, r7, getMeasuredWidth() - r42, r07);
        r10.drawRoundRect(r6, r32, r32, this.paint2);
        r6.set(r42, r7, ((getMeasuredWidth() - (2.0f * r42)) * this.currentProgress) + r42, r07);
        r10.drawRoundRect(r6, r32, r32, this.paint);
        r10.save();
        r10.clipRect(0.0f, 0.0f, getMeasuredWidth(), r7);
        if (this.progress == 1.0f) goto L25;
        this.showCompletedIcon = false;
    L25:
        if (this.showCompletedIcon == false) goto L27;
        this.downloadCompleteImageReceiver.draw(r10);
    L29:
        if (this.progress == 1.0f) goto L31;
    L35:
        r10.restore();
        if (getAlpha() == 0.0f) goto L39;
        this.wasDrawn = true;
        return;
    L39:
        return;
    L31:
        if (this.showCompletedIcon == true) goto L35;
        if (this.downloadDrawable.getCurrentFrame() != 0) goto L35;
        this.downloadCompleteDrawable.setCurrentFrame(0, false);
        this.downloadCompleteDrawable.start();
        this.showCompletedIcon = true;
        goto L35
    L27:
        this.downloadImageReceiver.draw(r10);
        goto L29
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30));
        int r42 = AndroidUtilities.dp(15.0f);
        float r0 = r42;
        int r43 = r42 * 2;
        this.downloadImageReceiver.setImageCoords(r0, r0, getMeasuredWidth() - r43, getMeasuredHeight() - r43);
        this.downloadCompleteImageReceiver.setImageCoords(r0, r0, getMeasuredWidth() - r43, getMeasuredHeight() - r43);
    }

    @Override // android.view.View
    public void setAlpha(float r2) {
        if (r2 != 0.0f) goto L5;
        this.wasDrawn = false;
    L5:
        super.setAlpha(r2);
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        if (r2 == 0) goto L4;
        this.wasDrawn = false;
    L4:
        super.setVisibility(r2);
    }

    public void updateColors() {
        RLottieDrawable r0 = this.downloadDrawable;
        int r2 = Theme.key_actionBarDefaultIcon;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2), PorterDuff.Mode.SRC_IN));
        this.downloadCompleteDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2), PorterDuff.Mode.SRC));
        invalidate();
    }

    public void updateProgress() {
        MessagesStorage.getInstance(this.currentAccount);
        int r2 = 0;
        long r3 = 0;
        long r5 = 0;
    L4:
        if (r2 >= this.currentListeners.size()) goto L7;
        r3 = r3 + this.currentListeners.get(r2).total;
        r5 = r5 + this.currentListeners.get(r2).downloaded;
        r2 = r2 + 1;
        goto L4
    L7:
        if (r3 != 0) goto L9;
        this.progress = 1.0f;
    L10:
        float r0 = this.progress;
        if (r0 <= 1.0f) goto L14;
        this.progress = 1.0f;
    L16:
        this.progressDt = ((this.progress - this.currentProgress) * 16.0f) / 150.0f;
        invalidate();
        return;
    L14:
        if (r0 >= 0.0f) goto L16;
        this.progress = 0.0f;
        goto L16
    L9:
        this.progress = r5 / r3;
        goto L10
    }
}
