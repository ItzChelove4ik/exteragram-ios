package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes7.dex */
public abstract class VideoSeekPreviewImage extends View implements NotificationCenter.NotificationCenterDelegate {
    private Paint bitmapPaint;
    private RectF bitmapRect;
    private BitmapShader bitmapShader;
    private Bitmap bitmapToDraw;
    private Bitmap bitmapToRecycle;
    private int currentPixel;
    private VideoSeekPreviewImageDelegate delegate;
    private String downloadingStoryBoardMapFilename;
    private TLRPC.Document downloadingStoryboardMapDocument;
    private boolean drawStoryBoard;
    private RectF dstR;
    private long duration;
    private AnimatedFileDrawable fileDrawable;
    private Drawable frameDrawable;
    private String frameTime;
    private boolean isQualities;
    private boolean isYoutube;
    private double lastPosition;
    private int listeningCurrentAccount;
    private Runnable loadRunnable;
    private Matrix matrix;
    private boolean open;
    private Paint paint;
    private float pendingProgress;
    private int pixelWidth;
    private Runnable progressRunnable;
    private boolean ready;
    private int storyBoardFrameHeight;
    private int storyBoardFrameWidth;
    private ArrayList<StoryBoardFrame> storyBoardMap;
    private long storyBoardMapDocId;
    private long storyBoardPictureDocId;
    private ImageReceiver storyBoardsReceiver;
    private final TextPaint textPaint;
    private int timeWidth;
    private Uri videoUri;
    private PhotoViewerWebView webView;
    private int ytImageHeight;
    private int ytImageWidth;
    private int ytImageX;
    private int ytImageY;
    private final Path ytPath;

    public static final class StoryBoardFrame {
        public final int left;
        public final double pts;
        public final int top;

        public StoryBoardFrame(double r1, int r3, int r4) {
            this.pts = r1;
            this.left = r3;
            this.top = r4;
        }
    }

    public interface VideoSeekPreviewImageDelegate {
        void onReady();
    }

    public static /* synthetic */ double $r8$lambda$02Cm8QagMtYNq50fbeSJacDhip8(StoryBoardFrame r2) {
        return r2.pts;
    }

    public static /* synthetic */ void $r8$lambda$7NbaxSJ8bNk2LQaugf1euGqM8Pc(VideoSeekPreviewImage r0) {
        r0.lambda$open$6();
    }

    /* renamed from: $r8$lambda$WcOg-mqGrgD1lew8_ovgbgDBTHA, reason: not valid java name */
    public static /* synthetic */ void m13513$r8$lambda$WcOgmqGrgD1lew8_ovgbgDBTHA(VideoSeekPreviewImage r0, VideoPlayer.VideoUri r1, MessageObject r2) {
        r0.lambda$open$5(r1, r2);
    }

    /* renamed from: $r8$lambda$aUiXlRt85B9-iLk4XwU9G2wH11c, reason: not valid java name */
    public static /* synthetic */ void m13514$r8$lambda$aUiXlRt85B9iLk4XwU9G2wH11c(VideoSeekPreviewImage r0) {
        r0.lambda$close$8();
    }

    /* renamed from: $r8$lambda$b2a8ONe1hnisSKTJIxF0-KIWYrE, reason: not valid java name */
    public static /* synthetic */ void m13515$r8$lambda$b2a8ONe1hnisSKTJIxF0KIWYrE(VideoSeekPreviewImage r0) {
        r0.lambda$open$4();
    }

    public static /* synthetic */ void $r8$lambda$eS5fhR0ZgUF5etYYVQ9V3aURfCY(VideoSeekPreviewImage r0, float r1, long r2) {
        r0.lambda$setProgress$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jmsdjSlW67hu_OYy7MzypO7Bby4(VideoSeekPreviewImage r0, Bitmap r1) {
        r0.lambda$setProgress$1(r1);
    }

    /* renamed from: $r8$lambda$tSU4ZggFB-Tx-_3CrHvYBiku78w, reason: not valid java name */
    public static /* synthetic */ void m13516$r8$lambda$tSU4ZggFBTx_3CrHvYBiku78w(VideoSeekPreviewImage r0, Uri r1, MessageObject r2) {
        r0.lambda$open$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xvC0vj1K0x2beWGUJ_fIzomv6Xo(VideoSeekPreviewImage r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public VideoSeekPreviewImage(Context r5, VideoSeekPreviewImageDelegate r6) {
        super(r5);
        this.currentPixel = -1;
        TextPaint r1 = new TextPaint(1);
        this.textPaint = r1;
        this.dstR = new RectF();
        this.paint = new Paint(2);
        this.bitmapPaint = new Paint(2);
        this.bitmapRect = new RectF();
        this.matrix = new Matrix();
        this.ytPath = new Path();
        this.listeningCurrentAccount = -1;
        setVisibility(4);
        this.frameDrawable = r5.getResources().getDrawable(R.drawable.videopreview);
        r1.setTextSize(AndroidUtilities.dp(13.0f));
        r1.setColor(-1);
        this.delegate = r6;
        ImageReceiver r52 = new ImageReceiver();
        this.storyBoardsReceiver = r52;
        r52.setParentView(this);
        this.storyBoardsReceiver.setDelegate(new VideoSeekPreviewImage$$ExternalSyntheticLambda3(this));
    }

    public static TLRPC.Document findDocumentById(MessageObject r6, long r7) {
        TLRPC.MessageMedia r62 = MessageObject.getMedia(r6);
        if (r62 != null) goto L5;
        return null;
    L5:
        TLRPC.Document r1 = r62.document;
        if (r1 != null) goto L8;
    L10:
        ArrayList<TLRPC.Document> r63 = r62.alt_documents;
        int r12 = r63.size();
        int r2 = 0;
    L11:
        if (r2 >= r12) goto L15;
        TLRPC.Document r3 = r63.get(r2);
        r2 = r2 + 1;
        TLRPC.Document r32 = r3;
        if (r32.id != r7) goto L11;
        return r32;
    L15:
        return null;
    L8:
        if (r1.id != r7) goto L10;
        return r1;
    }

    public static TLRPC.Document findDocumentByMimeType(MessageObject r5, String r6) {
        TLRPC.MessageMedia r52 = MessageObject.getMedia(r5);
        if (r52 != null) goto L5;
        return null;
    L5:
        TLRPC.Document r1 = r52.document;
        if (r1 != null) goto L8;
    L11:
        ArrayList<TLRPC.Document> r53 = r52.alt_documents;
        int r12 = r53.size();
        int r2 = 0;
    L12:
        if (r2 >= r12) goto L16;
        TLRPC.Document r3 = r53.get(r2);
        r2 = r2 + 1;
        TLRPC.Document r32 = r3;
        if (r6.equalsIgnoreCase(r32.mime_type) == false) goto L12;
        return r32;
    L16:
        return null;
    L8:
        if (r6.equalsIgnoreCase(r1.mime_type) == false) goto L11;
        return r52.document;
    }

    private /* synthetic */ void lambda$close$8() {
        this.pendingProgress = 0.0f;
        AnimatedFileDrawable r0 = this.fileDrawable;
        if (r0 == null) goto L6;
        r0.recycle();
        this.fileDrawable = null;
        return;
    }

    private /* synthetic */ void lambda$new$0(ImageReceiver r9, boolean r10, boolean r11, boolean r12) {
        if (r10 == true) goto L4;
        return;
    L4:
        if (this.webView == null) goto L6;
    L8:
        int r92 = AndroidUtilities.dp(150.0f);
        PhotoViewerWebView r102 = this.webView;
        if (r102 == null) goto L11;
        int r103 = r102.getYoutubeStoryboardImageCount((int) this.lastPosition);
        int r0 = (int) Math.ceil(r103 / 5.0f);
        float r3 = this.storyBoardsReceiver.getBitmapWidth() / Math.min(r103, 5);
        float r2 = this.storyBoardsReceiver.getBitmapHeight() / r0;
        int r02 = Math.min(this.webView.getYoutubeStoryboardImageIndex((int) this.lastPosition), r103 - 1) / 5;
        this.ytImageX = (int) ((r10 % 5) * r3);
        this.ytImageY = (int) (r02 * r2);
        this.ytImageWidth = (int) r3;
        this.ytImageHeight = (int) r2;
    L31:
        this.drawStoryBoard = true;
        float r104 = this.ytImageWidth / this.ytImageHeight;
        if (r104 <= 1.0f) goto L34;
        int r105 = (int) (r92 / r104);
    L35:
        ViewGroup.LayoutParams r122 = getLayoutParams();
        if (getVisibility() == 0) goto L38;
    L41:
        r122.width = r92;
        r122.height = r105;
        setVisibility(0);
        requestLayout();
        return;
    L38:
        if (r122.width != r92) goto L41;
        if (r122.height != r105) goto L41;
        return;
    L34:
        int r106 = (int) (r92 * r104);
        r105 = r92;
        r92 = r106;
        goto L35
    L11:
        int r107 = 0;
    L13:
        if (r107 >= this.storyBoardMap.size()) goto L28;
        StoryBoardFrame r03 = this.storyBoardMap.get(r107);
        if (r107 != 0) goto L17;
        double r1 = 0.0d;
    L19:
        if (r107 != (this.storyBoardMap.size() - 1)) goto L21;
        double r32 = 9.9999999E7d;
    L22:
        double r5 = this.lastPosition;
        if (r5 < r1) goto L27;
        if (r5 > r32) goto L27;
    L29:
        if (r03 == null) goto L48;
        this.ytImageX = r03.left;
        this.ytImageY = r03.top;
        this.ytImageWidth = this.storyBoardFrameWidth;
        this.ytImageHeight = this.storyBoardFrameHeight;
        goto L31
    L48:
        return;
    L27:
        r107 = r107 + 1;
        goto L13
    L21:
        r32 = this.storyBoardMap.get(r107 + 1).pts;
        goto L22
    L17:
        r1 = r03.pts;
        goto L19
    L28:
        r03 = null;
        goto L29
    L6:
        if (this.storyBoardMap != null) goto L8;
    }

    private /* synthetic */ void lambda$open$4() {
        this.open = true;
        this.loadRunnable = null;
        if (this.fileDrawable == null) goto L6;
        this.ready = true;
        this.delegate.onReady();
        return;
    }

    private /* synthetic */ void lambda$open$5(VideoPlayer.VideoUri r19, MessageObject r20) {
        if (r19.isCached() == false) goto L5;
        this.fileDrawable = new AnimatedFileDrawable(new File(r19.uri.getPath()), true, 0, 0, null, null, null, 0, 0, true, null);
    L19:
        this.duration = this.fileDrawable.getDurationMs();
        float r0 = this.pendingProgress;
        if (r0 == 0.0f) goto L22;
        setProgress(r20, r0, this.pixelWidth);
        this.pendingProgress = 0.0f;
    L22:
        AndroidUtilities.runOnUIThread(new VideoSeekPreviewImage$$ExternalSyntheticLambda6(this));
        return;
    L5:
        int r3 = UserConfig.selectedAccount;
        r3 = Utilities.parseInt(r19.uri.getQueryParameter("account"));     // Catch: Exception -> L8
    L7:
        int r15 = r3;
        Object r02 = FileLoader.getInstance(r15).getParentObject(Utilities.parseInt(r19.uri.getQueryParameter("rid")));     // Catch: Exception -> L12
    L11:
        Object r12 = r02;
        TLRPC.Document r10 = r19.document;
        if (FileLoader.getInstance(r15).isLoadingFile(FileLoader.getAttachFileName(r10)) == false) goto L17;
        String r03 = new File(FileLoader.getDirectory(4), r10.dc_id + "_" + r10.id + ".temp").getAbsolutePath();
    L18:
        this.fileDrawable = new AnimatedFileDrawable(new File(r03), true, r10.size, 1, r10, null, r12, 0, r15, true, null);
        goto L19
    L17:
        r03 = FileLoader.getInstance(r15).getPathToAttach(r10, false).getAbsolutePath();
    L12:
        e = move-exception;
        FileLog.e(e);
        r02 = null;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$open$6() {
        this.open = true;
        this.loadRunnable = null;
        if (this.fileDrawable == null) goto L6;
        this.ready = true;
        this.delegate.onReady();
        return;
    }

    private /* synthetic */ void lambda$open$7(Uri r18, MessageObject r19) {
        if ("tg".equals(r18.getScheme()) == false) goto L9;
        int r14 = Utilities.parseInt(r18.getQueryParameter("account"));
        Object r11 = FileLoader.getInstance(r14).getParentObject(Utilities.parseInt(r18.getQueryParameter("rid")));
        TLRPC.TL_document r9 = new TLRPC.TL_document();
        r9.access_hash = Utilities.parseLong(r18.getQueryParameter("hash")).longValue();
        r9.id = Utilities.parseLong(r18.getQueryParameter("id")).longValue();
        r9.size = Utilities.parseLong(r18.getQueryParameter("size")).longValue();
        r9.dc_id = Utilities.parseInt(r18.getQueryParameter("dc"));
        r9.mime_type = r18.getQueryParameter("mime");
        r9.file_reference = Utilities.hexToBytes(r18.getQueryParameter("reference"));
        TLRPC.TL_documentAttributeFilename r2 = new TLRPC.TL_documentAttributeFilename();
        r2.file_name = r18.getQueryParameter("name");
        r9.attributes.add(r2);
        r9.attributes.add(new TLRPC.TL_documentAttributeVideo());
        if (FileLoader.getInstance(r14).isLoadingFile(FileLoader.getAttachFileName(r9)) == false) goto L7;
        String r1 = new File(FileLoader.getDirectory(4), r9.dc_id + "_" + r9.id + ".temp").getAbsolutePath();
    L8:
        this.fileDrawable = new AnimatedFileDrawable(new File(r1), true, r9.size, 1, r9, null, r11, 0, r14, true, null);
    L10:
        this.duration = this.fileDrawable.getDurationMs();
        float r12 = this.pendingProgress;
        if (r12 == 0.0f) goto L13;
        setProgress(r19, r12, this.pixelWidth);
        this.pendingProgress = 0.0f;
    L13:
        AndroidUtilities.runOnUIThread(new VideoSeekPreviewImage$$ExternalSyntheticLambda5(this));
        return;
    L7:
        r1 = FileLoader.getInstance(r14).getPathToAttach(r9, false).getAbsolutePath();
        goto L8
    L9:
        this.fileDrawable = new AnimatedFileDrawable(new File(r18.getPath()), true, 0, 0, null, null, null, 0, 0, true, null);
        goto L10
    }

    private /* synthetic */ void lambda$setProgress$1(Bitmap r5) {
        if (r5 != null) goto L4;
    L20:
        this.progressRunnable = null;
        return;
    L4:
        if (this.bitmapToDraw == null) goto L9;
        Bitmap r0 = this.bitmapToRecycle;
        if (r0 == null) goto L8;
        r0.recycle();
    L8:
        this.bitmapToRecycle = this.bitmapToDraw;
    L9:
        this.bitmapToDraw = r5;
        Bitmap r1 = this.bitmapToDraw;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        BitmapShader r02 = new BitmapShader(r1, r2, r2);
        this.bitmapShader = r02;
        r02.setLocalMatrix(this.matrix);
        this.bitmapPaint.setShader(this.bitmapShader);
        invalidate();
        int r03 = AndroidUtilities.dp(150.0f);
        float r12 = r5.getWidth() / r5.getHeight();
        if (r12 <= 1.0f) goto L12;
        int r52 = (int) (r03 / r12);
    L13:
        ViewGroup.LayoutParams r13 = getLayoutParams();
        if (getVisibility() == 0) goto L16;
    L19:
        r13.width = r03;
        r13.height = r52;
        setVisibility(0);
        requestLayout();
        goto L20
    L16:
        if (r13.width != r03) goto L19;
        if (r13.height == r52) goto L20;
    L12:
        r03 = (int) (r03 * r12);
        r52 = r03;
        goto L13
    }

    private /* synthetic */ void lambda$setProgress$2(float r6, long r7) {
        if (this.fileDrawable != null) goto L6;
        this.pendingProgress = r6;
        return;
    L6:
        int r62 = Math.max(200, AndroidUtilities.dp(100.0f));
        Bitmap r72 = this.fileDrawable.getFrameAtTime(r7, false);
        if (r72 == null) goto L16;
        int r8 = r72.getWidth();
        int r0 = r72.getHeight();
        if (r8 <= r0) goto L11;
        int r82 = (int) (r0 / (r8 / r62));
    L18:
        Bitmap r1 = Bitmaps.createBitmap(r62, r82, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L15
        this.dstR.set(0.0f, 0.0f, r62, r82);     // Catch: Throwable -> L15
        Canvas r63 = new Canvas(r1);     // Catch: Throwable -> L15
        r63.drawBitmap(r72, null, this.dstR, this.paint);     // Catch: Throwable -> L15
        r63.setBitmap(null);     // Catch: Throwable -> L15
        r72 = r1;
    L15:
        r72 = null;
        goto L16
    L11:
        int r83 = (int) (r8 / (r0 / r62));
        r82 = r62;
        r62 = r83;
    L16:
        AndroidUtilities.runOnUIThread(new VideoSeekPreviewImage$$ExternalSyntheticLambda8(this, r72));
    }

    private void listen(int r3) {
        int r0 = this.listeningCurrentAccount;
        if (r0 != r3) goto L6;
        return;
    L6:
        if (r3 != (-1)) goto L8;
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.listeningCurrentAccount).removeObserver(this, NotificationCenter.fileLoadFailed);
    L9:
        this.listeningCurrentAccount = r3;
        return;
    L8:
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(r3).addObserver(this, NotificationCenter.fileLoadFailed);
        goto L9
    }

    public void close() {
        if (this.loadRunnable == null) goto L6;
        Utilities.globalQueue.cancelRunnable(this.loadRunnable);
        this.loadRunnable = null;
    L6:
        if (this.progressRunnable == null) goto L8;
        Utilities.globalQueue.cancelRunnable(this.progressRunnable);
        this.progressRunnable = null;
    L8:
        AnimatedFileDrawable r0 = this.fileDrawable;
        if (r0 == null) goto L11;
        r0.resetStream(true);
    L11:
        Utilities.globalQueue.postRunnable(new VideoSeekPreviewImage$$ExternalSyntheticLambda0(this));
        setVisibility(4);
        this.bitmapToDraw = null;
        this.bitmapShader = null;
        invalidate();
        this.currentPixel = -1;
        this.videoUri = null;
        this.ready = false;
        this.open = false;
        if (this.storyBoardMapDocId == 0) goto L15;
        this.storyBoardMapDocId = 0;
        this.downloadingStoryBoardMapFilename = null;
        this.downloadingStoryboardMapDocument = null;
        this.storyBoardMap = null;
        listen(-1);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        if (r5 != NotificationCenter.fileLoaded) goto L14;
        if (((String) r7[0]).equals(this.downloadingStoryBoardMapFilename) == false) goto L19;
        File r52 = FileLoader.getInstance(r6).getPathToAttach(this.downloadingStoryboardMapDocument);
        if (r52 != null) goto L9;
    L11:
        this.downloadingStoryBoardMapFilename = null;
        this.downloadingStoryboardMapDocument = null;
        listen(-1);
        return;
    L9:
        if (r52.exists() == false) goto L11;
        parseStoryBoardMap(r52);
        goto L11
    L19:
        return;
    L14:
        if (r5 == NotificationCenter.fileLoadFailed) goto L16;
        return;
    L16:
        if (((String) r7[0]).equals(this.downloadingStoryBoardMapFilename) == false) goto L21;
        this.downloadingStoryBoardMapFilename = null;
        this.downloadingStoryboardMapDocument = null;
        listen(-1);
        return;
    }

    public boolean isReady() {
        return this.ready;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.storyBoardsReceiver.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.storyBoardsReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        Bitmap r0 = this.bitmapToRecycle;
        if (r0 == null) goto L6;
        r0.recycle();
        this.bitmapToRecycle = null;
    L6:
        if (this.drawStoryBoard == false) goto L10;
        r10.save();
        this.ytPath.rewind();
        RectF r02 = AndroidUtilities.rectTmp;
        r02.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.ytPath.addRoundRect(r02, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Path.Direction.CW);
        r10.clipPath(this.ytPath);
        r10.scale(getWidth() / this.ytImageWidth, getHeight() / this.ytImageHeight);
        r10.translate(-this.ytImageX, -this.ytImageY);
        this.storyBoardsReceiver.setImageCoords(0.0f, 0.0f, r0.getBitmapWidth(), this.storyBoardsReceiver.getBitmapHeight());
        this.storyBoardsReceiver.draw(r10);
        r10.restore();
        this.frameDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.frameDrawable.draw(r10);
        r10.drawText(this.frameTime, (getMeasuredWidth() - this.timeWidth) / 2.0f, getMeasuredHeight() - AndroidUtilities.dp(9.0f), this.textPaint);
        return;
    L10:
        if (this.bitmapToDraw != null) goto L12;
        return;
    L12:
        if (this.bitmapShader == null) goto L16;
        this.matrix.reset();
        float r03 = getMeasuredWidth() / this.bitmapToDraw.getWidth();
        this.matrix.preScale(r03, r03);
        this.bitmapRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        r10.drawRoundRect(this.bitmapRect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.bitmapPaint);
        this.frameDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.frameDrawable.draw(r10);
        r10.drawText(this.frameTime, (getMeasuredWidth() - this.timeWidth) / 2.0f, getMeasuredHeight() - AndroidUtilities.dp(9.0f), this.textPaint);
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        setPivotY(getMeasuredHeight());
    }

    public void open(MessageObject r3, Uri r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.equals(this.videoUri) == false) goto L7;
        return;
    L7:
        if (this.open == false) goto L9;
        close();
    L9:
        this.isQualities = false;
        this.videoUri = r4;
        DispatchQueue r0 = Utilities.globalQueue;
        VideoSeekPreviewImage$$ExternalSyntheticLambda2 r1 = new VideoSeekPreviewImage$$ExternalSyntheticLambda2(this, r4, r3);
        this.loadRunnable = r1;
        r0.postRunnable(r1);
    }

    public void open(MessageObject r3, VideoPlayer.VideoUri r4) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.uri.equals(this.videoUri) == false) goto L8;
        return;
    L8:
        if (this.open == false) goto L10;
        close();
    L10:
        this.isQualities = true;
        this.videoUri = r4.uri;
        DispatchQueue r0 = Utilities.globalQueue;
        VideoSeekPreviewImage$$ExternalSyntheticLambda4 r1 = new VideoSeekPreviewImage$$ExternalSyntheticLambda4(this, r4, r3);
        this.loadRunnable = r1;
        r0.postRunnable(r1);
    }

    public void open(MessageObject r12, VideoPlayer r13) {
        if (r13 != null) goto L5;
        return;
    L5:
        if (r13.getQualitiesCount() <= 0) goto L37;
        int r0 = 0;
        VideoPlayer.VideoUri r3 = null;
    L8:
        if (r0 >= r13.getQualitiesCount()) goto L23;
        ArrayList<VideoPlayer.VideoUri> r4 = r13.getQuality(r0).uris;
        int r5 = r4.size();
        int r6 = 0;
    L10:
        if (r6 >= r5) goto L22;
        VideoPlayer.VideoUri r7 = r4.get(r6);
        r6 = r6 + 1;
        VideoPlayer.VideoUri r72 = r7;
        if (r3 == null) goto L21;
        if (r3.isCached() == true) goto L18;
        if (r72.isCached() == true) goto L21;
    L18:
        if (r3.isCached() != r72.isCached()) goto L10;
        if ((r72.width * r72.height) >= (r3.width * r3.height)) goto L10;
    L21:
        r3 = r72;
        goto L10
    L22:
        r0 = r0 + 1;
        goto L8
    L23:
        if (r3 != null) goto L25;
    L29:
        if (r3 != null) goto L31;
    L34:
        if (r3 == null) goto L36;
        r3.isCached();
    L36:
        open(r12, r3);
    L41:
        TLRPC.Document r132 = findDocumentByMimeType(r12, "application/x-tgstoryboardmap");
        if (r132 != null) goto L44;
        long r32 = 0;
    L46:
        if (this.storyBoardMapDocId == r32) goto L69;
        this.storyBoardMapDocId = r32;
        this.storyBoardMap = null;
        if (r132 == null) goto L57;
        File r33 = FileLoader.getInstance(r12.currentAccount).getPathToAttach(r132);
        if (r33 != null) goto L52;
    L55:
        this.downloadingStoryBoardMapFilename = FileLoader.getAttachFileName(r132);
        this.downloadingStoryboardMapDocument = r132;
        listen(r12.currentAccount);
        FileLoader.getInstance(r12.currentAccount).loadFile(r132, r12, 2, 0);
        return;
    L52:
        if (r33.exists() == false) goto L55;
        this.downloadingStoryBoardMapFilename = null;
        this.downloadingStoryboardMapDocument = null;
        listen(-1);
        parseStoryBoardMap(r33);
        return;
    L57:
        this.downloadingStoryBoardMapFilename = null;
        this.downloadingStoryboardMapDocument = null;
        listen(-1);
        parseStoryBoardMap(null);
        return;
    L69:
        return;
    L44:
        r32 = r132.id;
        goto L46
    L31:
        if (r3.isCached() == true) goto L34;
        close();
        return;
    L25:
        if (r3.isCached() == true) goto L29;
        VideoPlayer.Quality r133 = r13.getCurrentQuality();
        if (r133 == null) goto L29;
        r3 = r133.getDownloadUri();
        goto L29
    L37:
        Uri r134 = r13.getCurrentUri();
        if (r134 == null) goto L40;
        "file".equalsIgnoreCase(r134.getScheme());
    L40:
        open(r12, r134);
        goto L41
    }

    public void parseStoryBoardMap(File r14) {
        if (r14 != null) goto L27;
        this.storyBoardMap = null;
        return;
    L27:
        RandomAccessFile r1 = new RandomAccessFile(r14, "r");     // Catch: Exception -> L12
        ArrayList<StoryBoardFrame> r142 = new ArrayList();     // Catch: Exception -> L12
        long r3 = 0;
        int r5 = 0;
        int r6 = 0;
    L7:
        String r7 = r1.readLine();     // Catch: Exception -> L12
        if (r7 == null) goto L23;
        if (r7.startsWith("file=mtproto:") == true) goto L11;
        if (r7.startsWith("frame_width=") == true) goto L16;
        if (r7.startsWith("frame_height=") == true) goto L19;
        String[] r72 = r7.split(",");     // Catch: Exception -> L12
        if (r72.length != 3) goto L7;
        r142.add(new StoryBoardFrame(Double.parseDouble(r72[0]), Integer.parseInt(r72[1]), Integer.parseInt(r72[2])));     // Catch: Exception -> L12
        goto L7
    L19:
        r6 = Integer.parseInt(r7.substring(13));     // Catch: Exception -> L12
        goto L7
    L16:
        r5 = Integer.parseInt(r7.substring(12));     // Catch: Exception -> L12
        goto L7
    L11:
        r3 = Long.parseLong(r7.substring(13));     // Catch: Exception -> L12
        goto L7
    L23:
        Collections.sort(r142, Comparator.comparingDouble(new VideoSeekPreviewImage$$ExternalSyntheticLambda1()));     // Catch: Exception -> L12
        this.storyBoardPictureDocId = r3;     // Catch: Exception -> L12
        this.storyBoardFrameWidth = r5;     // Catch: Exception -> L12
        this.storyBoardFrameHeight = r6;     // Catch: Exception -> L12
        this.storyBoardMap = r142;     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        this.storyBoardMap = null;
    }

    public void setProgress(MessageObject r13, float r14, int r15) {
        this.webView = null;
        this.isYoutube = false;
        if (this.storyBoardMap == null) goto L8;
        TLRPC.Document r2 = findDocumentById(r13, this.storyBoardPictureDocId);
        if (r2 == null) goto L7;
        this.lastPosition = (this.duration * r14) / 1000.0d;
        this.storyBoardsReceiver.setImage(ImageLocation.getForDocument(r2), null, null, null, r13, 0);
        boolean r132 = true;
    L10:
        this.drawStoryBoard = r132;
        if (r15 == 0) goto L16;
        this.pixelWidth = r15;
        int r152 = ((int) (r15 * r14)) / 5;
        if (this.currentPixel == r152) goto L26;
        this.currentPixel = r152;
        goto L16
    L26:
        return;
    L16:
        long r22 = (long) (this.duration * r14);
        this.frameTime = AndroidUtilities.formatShortDuration((int) (r22 / 1000));
        this.timeWidth = (int) Math.ceil(this.textPaint.measureText(r15));
        invalidate();
        if (this.progressRunnable == null) goto L19;
        Utilities.globalQueue.cancelRunnable(this.progressRunnable);
    L19:
        if (r132 == false) goto L21;
        return;
    L21:
        AnimatedFileDrawable r133 = this.fileDrawable;
        if (r133 == null) goto L24;
        r133.resetStream(false);
    L24:
        DispatchQueue r134 = Utilities.globalQueue;
        VideoSeekPreviewImage$$ExternalSyntheticLambda7 r153 = new VideoSeekPreviewImage$$ExternalSyntheticLambda7(this, r14, r22);
        this.progressRunnable = r153;
        r134.postRunnable(r153);
        return;
    L7:
        this.storyBoardsReceiver.setImageBitmap(null);
    L9:
        r132 = false;
        goto L10
    L8:
        this.storyBoardsReceiver.setImageBitmap(null);
        goto L9
    }

    public void setProgressForYouTube(PhotoViewerWebView r8, float r9, int r10) {
        this.webView = r8;
        this.isYoutube = true;
        if (this.storyBoardMapDocId == 0) goto L5;
        this.storyBoardMapDocId = 0;
        this.downloadingStoryBoardMapFilename = null;
        this.downloadingStoryboardMapDocument = null;
        this.storyBoardMap = null;
        listen(-1);
    L5:
        if (r10 == 0) goto L10;
        this.pixelWidth = r10;
        int r102 = ((int) (r10 * r9)) / 5;
        if (this.currentPixel == r102) goto L18;
        this.currentPixel = r102;
        goto L10
    L18:
        return;
    L10:
        this.frameTime = AndroidUtilities.formatShortDuration((int) (((long) (r8.getVideoDuration() * r9)) / 1000));
        this.timeWidth = (int) Math.ceil(this.textPaint.measureText(r10));
        invalidate();
        if (this.progressRunnable == null) goto L13;
        Utilities.globalQueue.cancelRunnable(this.progressRunnable);
    L13:
        double r92 = (r9 * r8.getVideoDuration()) / 1000.0d;
        this.lastPosition = r92;
        String r1 = r8.getYoutubeStoryboard((int) r92);
        if (r1 == null) goto L17;
        this.storyBoardsReceiver.setImage(r1, null, null, null, 0);
        return;
    }
}
