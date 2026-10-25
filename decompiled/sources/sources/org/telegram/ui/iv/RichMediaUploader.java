package org.telegram.ui.iv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class RichMediaUploader implements NotificationCenter.NotificationCenterDelegate {
    private final TLRPC.Document audioDocument;
    private boolean cancelled;
    private final int currentAccount;
    private TLRPC.InputFile documentInputFile;
    private String documentThumbPath;
    private boolean finished;
    private final boolean isAudio;
    private final boolean isDocument;
    private final boolean isVideo;
    private final Listener listener;
    private final String path;
    private int requestToken;
    private boolean started;
    private volatile String uploadPath;
    private boolean uploadingDocumentThumb;
    private final int videoDurationSec;
    private final int videoHeight;
    private final int videoWidth;

    public interface Listener {
        default void onAudioUploaded(TLRPC.Document r1) {
        }

        default void onDocumentUploaded(TLRPC.Document r1) {
        }

        void onError();

        default void onPhotoUploaded(TLRPC.Photo r1) {
        }

        void onProgress(float r1);

        default void onVideoUploaded(TLRPC.Document r1) {
        }

        default void onWidthHeightResolved(int r1, int r2) {
        }
    }

    public static /* synthetic */ void $r8$lambda$PSp52w4VeiAERX9yOV_uvDepvSw(RichMediaUploader r0, String r1) {
        r0.lambda$start$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$Phzw74W5gfond3CStwLsaoSj45E(RichMediaUploader r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$sendUploadMediaRequest$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TnIV524xJlHQGXZ2jhjpupIm7js(RichMediaUploader r0) {
        r0.lambda$start$0();
    }

    public static /* synthetic */ void $r8$lambda$djytL_LHEz2ZgRAA0BnLpqCPYKY(RichMediaUploader r0, TLObject r1) {
        r0.lambda$sendUploadMediaRequest$4(r1);
    }

    /* renamed from: $r8$lambda$oAnXQZJoAkKZtZKHBo-MVUd7r3k, reason: not valid java name */
    public static /* synthetic */ void m21889$r8$lambda$oAnXQZJoAkKZtZKHBoMVUd7r3k(RichMediaUploader r0) {
        r0.lambda$start$3();
    }

    /* renamed from: $r8$lambda$tLQJd4gP7s-pXzS1PQFAuewZF-Q, reason: not valid java name */
    public static /* synthetic */ void m21890$r8$lambda$tLQJd4gP7spXzS1PQFAuewZFQ(RichMediaUploader r0) {
        r0.lambda$start$1();
    }

    private RichMediaUploader(int r1, String r2, TLRPC.Document r3, Listener r4) {
        this.currentAccount = r1;
        this.path = r2;
        this.isVideo = false;
        this.isAudio = true;
        this.isDocument = false;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.videoDurationSec = 0;
        this.audioDocument = r3;
        this.listener = r4;
    }

    private RichMediaUploader(int r1, String r2, TLRPC.Document r3, boolean r4, Listener r5) {
        this.currentAccount = r1;
        this.path = r2;
        this.isVideo = false;
        this.isAudio = false;
        this.isDocument = r4;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.videoDurationSec = 0;
        this.audioDocument = r3;
        this.listener = r5;
    }

    public RichMediaUploader(int r1, String r2, boolean r3, int r4, int r5, int r6, Listener r7) {
        this.currentAccount = r1;
        this.path = r2;
        this.isVideo = r3;
        this.isAudio = false;
        this.isDocument = false;
        this.videoWidth = r4;
        this.videoHeight = r5;
        this.videoDurationSec = r6;
        this.audioDocument = null;
        this.listener = r7;
    }

    private void beginUpload(String r5) {
        if (this.cancelled == false) goto L5;
        return;
    L5:
        if (this.finished == true) goto L28;
        this.uploadPath = r5;
        NotificationCenter r52 = NotificationCenter.getInstance(this.currentAccount);
        r52.addObserver(this, NotificationCenter.fileUploaded);
        r52.addObserver(this, NotificationCenter.fileUploadFailed);
        r52.addObserver(this, NotificationCenter.fileUploadProgressChanged);
        if (this.isVideo == false) goto L11;
        int r53 = 33554432;
    L17:
        FileLoader r0 = FileLoader.getInstance(this.currentAccount);
        String r1 = this.uploadPath;
        if (this.isVideo == false) goto L20;
    L24:
        boolean r4 = false;
    L25:
        r0.uploadFile(r1, false, r4, r53);
        return;
    L20:
        if (this.isAudio == true) goto L24;
        if (this.isDocument == true) goto L24;
        r4 = true;
        goto L25
    L11:
        if (this.isAudio == false) goto L14;
        r53 = ConnectionsManager.FileTypeAudio;
        goto L17
    L14:
        if (this.isDocument == false) goto L16;
        r53 = 67108864;
        goto L17
    L16:
        r53 = 16777216;
        goto L17
    }

    private String ensureJpegPath(String r7) {
        BitmapFactory.Options r0 = new BitmapFactory.Options();     // Catch: Throwable -> L45
        r0.inJustDecodeBounds = true;     // Catch: Throwable -> L45
        BitmapFactory.decodeFile(r7, r0);     // Catch: Throwable -> L45
        String r02 = r0.outMimeType;     // Catch: Throwable -> L45
        if (r02 != null) goto L6;
    L10:
        boolean r03 = false;
    L11:
        float r2 = AndroidUtilities.getPhotoSize();     // Catch: Throwable -> L45
        Bitmap r22 = ImageLoader.loadBitmap(r7, null, r2, r2, true);     // Catch: Throwable -> L45
        if (r22 != null) goto L14;
        r22 = ImageLoader.loadBitmap(r7, null, 800.0f, 800.0f, true);     // Catch: Throwable -> L45
    L14:
        if (r22 == null) goto L44;
        File r1 = new File(FileLoader.getDirectory(4), "rich_jpeg_" + Math.abs(r7.hashCode()) + ".jpg");     // Catch: Throwable -> L45
        FileOutputStream r6 = new FileOutputStream(r1);     // Catch: Throwable -> L34
        boolean r3 = r22.compress(Bitmap.CompressFormat.JPEG, 89, r6);     // Catch: Throwable -> L36
        r6.close();     // Catch: Throwable -> L34
        r22.recycle();     // Catch: Throwable -> L45
        if (r3 == true) goto L23;
    L33:
        return r7;
    L23:
        if (r1.length() <= 0) goto L33;
        if (r03 == false) goto L32;
        long r23 = new File(r7).length();     // Catch: Throwable -> L45
        if (r23 <= 0) goto L32;
        if (r1.length() >= r23) goto L33;
    L32:
        return r1.getAbsolutePath();
    L36:
        th = move-exception;
        r6.close();     // Catch: Throwable -> L39
    L41:
        throw th;     // Catch: Throwable -> L34
    L39:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L34
    L34:
        th = move-exception;
        r22.recycle();     // Catch: Throwable -> L45
        throw th;     // Catch: Throwable -> L45
    L6:
        if (r02.equalsIgnoreCase("image/jpeg") == false) goto L8;
    L9:
        r03 = true;
        goto L11
    L8:
        if (r02.equalsIgnoreCase("image/jpg") == false) goto L10;
    L44:
        return r7;
    }

    private void finishWithAudio(TLRPC.Document r2) {
        this.finished = true;
        teardown();
        Listener r1 = this.listener;
        if (r1 == null) goto L6;
        r1.onAudioUploaded(r2);
        return;
    }

    private void finishWithDocument(TLRPC.Document r5) {
        if (r5 != null) goto L4;
    L19:
        finishWithError();
        return;
    L4:
        if (r5.id == 0) goto L19;
        if (r5.access_hash == 0) goto L19;
        this.finished = true;
        teardown();
        if (TextUtils.isEmpty(this.documentThumbPath) == false) goto L11;
    L15:
        Listener r4 = this.listener;
        if (r4 == null) goto L21;
        r4.onDocumentUploaded(r5);
        return;
    L21:
        return;
    L11:
        if (MessageObject.isDocumentHasThumb(r5) == false) goto L15;
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r5.thumbs, 320);
        if (r1 == null) goto L15;
        FileLoader.getInstance(this.currentAccount).setLocalPathTo(r1, this.documentThumbPath);
        AndroidUtilities.copyFileSafe(new File(this.documentThumbPath), FileLoader.getInstance(this.currentAccount).getPathToAttach(r1, true));
        goto L15
    }

    private void finishWithError() {
        this.finished = true;
        teardown();
        Listener r1 = this.listener;
        if (r1 == null) goto L6;
        r1.onError();
        return;
    }

    private void finishWithPhoto(TLRPC.Photo r2) {
        this.finished = true;
        teardown();
        Listener r1 = this.listener;
        if (r1 == null) goto L6;
        r1.onPhotoUploaded(r2);
        return;
    }

    private void finishWithVideo(TLRPC.Document r2) {
        this.finished = true;
        teardown();
        Listener r1 = this.listener;
        if (r1 == null) goto L6;
        r1.onVideoUploaded(r2);
        return;
    }

    public static RichMediaUploader forAudio(int r1, String r2, TLRPC.Document r3, Listener r4) {
        return new RichMediaUploader(r1, r2, r3, r4);
    }

    public static RichMediaUploader forDocument(int r6, String r7, TLRPC.Document r8, Listener r9) {
        return new RichMediaUploader(r6, r7, r8, true, r9);
    }

    private String generateDocumentThumb() {
        String r1 = null;
        if (this.audioDocument != null) goto L5;
    L51:
        return null;
    L5:
        if (TextUtils.isEmpty(this.path) == true) goto L51;
        String r0 = this.audioDocument.mime_type;     // Catch: Throwable -> L10
        if (r0 != null) goto L11;
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if (r02.startsWith("image/") == false) goto L16;
        Bitmap r03 = ImageLoader.loadBitmap(this.path, null, 320.0f, 320.0f, true);     // Catch: Throwable -> L10
    L20:
        if (r03 != null) goto L53;
        if (r03 != null) goto L23;
    L25:
        return null;
    L23:
        if (r03.isRecycled() == true) goto L25;
        r03.recycle();
        goto L25
    L53:
        ArrayList<TLRPC.DocumentAttribute> r2 = this.audioDocument.attributes;     // Catch: Throwable -> L52
        int r5 = r2.size();     // Catch: Throwable -> L52
        int r7 = 0;
    L27:
        if (r7 >= r5) goto L31;
        TLRPC.DocumentAttribute r8 = r2.get(r7);     // Catch: Throwable -> L52
        r7 = r7 + 1;     // Catch: Throwable -> L52
        if ((r8 instanceof TLRPC.TL_documentAttributeImageSize) == false) goto L27;
    L32:
        TLRPC.PhotoSize r22 = ImageLoader.scaleAndSaveImage(r03, 320.0f, 320.0f, 80, false);     // Catch: Throwable -> L52
        if (r22 == null) goto L35;
        this.audioDocument.thumbs.clear();     // Catch: Throwable -> L52
        this.audioDocument.thumbs.add(r22);     // Catch: Throwable -> L52
        this.audioDocument.flags |= 1;
        File r9 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r22, true);     // Catch: Throwable -> L52
        if (r9 == null) goto L44;
        if (r9.exists() == false) goto L44;
        r1 = r9.getAbsolutePath();     // Catch: Throwable -> L52
    L44:
        if (r03.isRecycled() == true) goto L46;
        r03.recycle();
    L46:
        return r1;
    L35:
        if (r03.isRecycled() == true) goto L37;
        r03.recycle();
    L37:
        return null;
    L31:
        TLRPC.TL_documentAttributeImageSize r23 = new TLRPC.TL_documentAttributeImageSize();     // Catch: Throwable -> L52
        r23.w = r03.getWidth();     // Catch: Throwable -> L52
        r23.h = r03.getHeight();     // Catch: Throwable -> L52
        this.audioDocument.attributes.add(r23);     // Catch: Throwable -> L52
    L47:
        if (r03 == null) goto L51;
        if (r03.isRecycled() == true) goto L51;
        r03.recycle();
        goto L51
    L16:
        if (r02.equals("video/mp4") == false) goto L19;
        r03 = SendMessagesHelper.createVideoThumbnail(this.path, 1);     // Catch: Throwable -> L10
        goto L20
    L19:
        r03 = null;
        goto L20
    L11:
        r02 = r0.toLowerCase();     // Catch: Throwable -> L10
    L10:
        r03 = null;
        goto L47
    }

    private /* synthetic */ void lambda$sendUploadMediaRequest$4(TLObject r2) {
        if (this.cancelled == false) goto L5;
        return;
    L5:
        this.requestToken = 0;
        if (this.isVideo == true) goto L19;
        if (this.isAudio == true) goto L19;
        if (this.isDocument == true) goto L19;
        if ((r2 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L32;
        TLRPC.Photo r22 = ((TLRPC.TL_messageMediaPhoto) r2).photo;
        if (r22 == null) goto L32;
        finishWithPhoto(r22);
        return;
    L32:
        finishWithError();
        return;
    L19:
        if ((r2 instanceof TLRPC.TL_messageMediaDocument) == false) goto L32;
        TLRPC.Document r23 = ((TLRPC.TL_messageMediaDocument) r2).document;
        if (r23 == null) goto L32;
        if (this.isDocument == false) goto L27;
        finishWithDocument(r23);
        return;
    L27:
        if (this.isAudio == false) goto L30;
        finishWithAudio(r23);
        return;
    L30:
        finishWithVideo(r23);
    }

    private /* synthetic */ void lambda$sendUploadMediaRequest$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new RichMediaUploader$$ExternalSyntheticLambda3(this, r1));
    }

    private /* synthetic */ void lambda$start$0() {
        if (this.cancelled == false) goto L5;
        return;
    L5:
        if (this.finished == true) goto L9;
        beginUpload(this.path);
        return;
    }

    private /* synthetic */ void lambda$start$1() {
        this.documentThumbPath = generateDocumentThumb();
        AndroidUtilities.runOnUIThread(new RichMediaUploader$$ExternalSyntheticLambda5(this));
    }

    private /* synthetic */ void lambda$start$2(String r2) {
        if (this.cancelled == false) goto L5;
        return;
    L5:
        if (this.finished == true) goto L10;
        beginUpload(r2);
        return;
    }

    private /* synthetic */ void lambda$start$3() {
        AndroidUtilities.runOnUIThread(new RichMediaUploader$$ExternalSyntheticLambda4(this, ensureJpegPath(this.path)));
    }

    private void resolvePhotoDimensions() {
        BitmapFactory.Options r0 = new BitmapFactory.Options();     // Catch: Exception -> L10
        r0.inJustDecodeBounds = true;     // Catch: Exception -> L10
        BitmapFactory.decodeFile(this.path, r0);     // Catch: Exception -> L10
        int r1 = r0.outWidth;     // Catch: Exception -> L10
        if (r1 <= 0) goto L13;
        int r02 = r0.outHeight;     // Catch: Exception -> L10
        if (r02 <= 0) goto L14;
        Listener r2 = this.listener;     // Catch: Exception -> L10
        if (r2 == null) goto L15;
        r2.onWidthHeightResolved(r1, r02);     // Catch: Exception -> L10
        return;
    L15:
        return;
    L14:
        return;
    L13:
        return;
    }

    private void sendUploadMediaRequest(TLRPC.InputFile r8, TLRPC.InputFile r9) {
        TLRPC.TL_messages_uploadMedia r0 = new TLRPC.TL_messages_uploadMedia();
        r0.peer = new TLRPC.TL_inputPeerSelf();
        if (this.isVideo == false) goto L6;
        TLRPC.TL_inputMediaUploadedDocument r92 = new TLRPC.TL_inputMediaUploadedDocument();
        r92.file = r8;
        r92.mime_type = "video/mp4";
        TLRPC.TL_documentAttributeVideo r82 = new TLRPC.TL_documentAttributeVideo();
        r82.supports_streaming = true;
        r82.duration = this.videoDurationSec;
        r82.w = this.videoWidth;
        r82.h = this.videoHeight;
        r92.attributes.add(r82);
        r0.media = r92;
    L35:
        this.requestToken = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new RichMediaUploader$$ExternalSyntheticLambda0(this));
        return;
    L6:
        if (this.isAudio == false) goto L8;
    L11:
        TLRPC.TL_inputMediaUploadedDocument r1 = new TLRPC.TL_inputMediaUploadedDocument();
        r1.file = r8;
        boolean r83 = this.isDocument;
        if (r83 == false) goto L14;
        String r3 = "application/octet-stream";
    L20:
        r1.mime_type = r3;
        TLRPC.Document r32 = this.audioDocument;
        if (r32 == null) goto L30;
        if (r83 == false) goto L28;
        ArrayList<TLRPC.DocumentAttribute> r84 = r32.attributes;
        int r33 = r84.size();
        int r4 = 0;
    L24:
        if (r4 >= r33) goto L30;
        TLRPC.DocumentAttribute r5 = r84.get(r4);
        r4 = r4 + 1;
        TLRPC.DocumentAttribute r52 = r5;
        if ((r52 instanceof TLRPC.TL_documentAttributeFilename) == false) goto L24;
        r1.attributes.add(r52);
        goto L24
    L28:
        r1.attributes.addAll(r32.attributes);
    L30:
        if (this.isDocument == false) goto L34;
        r1.force_file = true;
        if (r9 == null) goto L34;
        r1.thumb = r9;
        r1.flags |= 4;
    L34:
        r0.media = r1;
        goto L35
    L14:
        TLRPC.Document r34 = this.audioDocument;
        if (r34 == null) goto L19;
        r3 = r34.mime_type;
        if (r3 != null) goto L20;
    L19:
        r3 = "audio/mpeg";
        goto L20
    L8:
        if (this.isDocument == true) goto L11;
        TLRPC.TL_inputMediaUploadedPhoto r93 = new TLRPC.TL_inputMediaUploadedPhoto();
        r93.file = r8;
        r0.media = r93;
        goto L35
    }

    private void teardown() {
        NotificationCenter r0 = NotificationCenter.getInstance(this.currentAccount);
        r0.removeObserver(this, NotificationCenter.fileUploaded);
        r0.removeObserver(this, NotificationCenter.fileUploadFailed);
        r0.removeObserver(this, NotificationCenter.fileUploadProgressChanged);
    }

    public void cancel() {
        if (this.finished == false) goto L5;
        return;
    L5:
        if (this.cancelled == true) goto L20;
        this.cancelled = true;
        if (this.uploadPath == null) goto L12;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(this.uploadPath, false);     // Catch: Throwable -> L16
    L12:
        if (this.requestToken == 0) goto L14;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestToken, true);
        this.requestToken = 0;
    L14:
        teardown();
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        if (r6 == this.currentAccount) goto L5;
        return;
    L5:
        if (this.cancelled == false) goto L7;
        return;
    L7:
        if (this.finished == true) goto L62;
        String r0 = (String) r7[0];
        if (this.uploadPath != null) goto L12;
        return;
    L12:
        if (this.uploadPath.equals(r0) == true) goto L15;
        return;
    L15:
        if (r5 != NotificationCenter.fileUploaded) goto L33;
        TLRPC.InputFile r52 = (TLRPC.InputFile) r7[1];
        if (this.isDocument == false) goto L25;
        if (this.uploadingDocumentThumb == true) goto L25;
        if (TextUtils.isEmpty(this.documentThumbPath) == true) goto L25;
        this.documentInputFile = r52;
        this.uploadingDocumentThumb = true;
        this.uploadPath = this.documentThumbPath;
        FileLoader.getInstance(this.currentAccount).uploadFile(this.uploadPath, false, true, 16777216);
        return;
    L25:
        if (this.isDocument == true) goto L27;
    L30:
        sendUploadMediaRequest(r52, null);
        return;
    L27:
        if (this.uploadingDocumentThumb == false) goto L30;
        sendUploadMediaRequest(this.documentInputFile, r52);
        return;
    L33:
        if (r5 != NotificationCenter.fileUploadFailed) goto L45;
        if (this.isDocument == true) goto L37;
    L42:
        finishWithError();
        return;
    L37:
        if (this.uploadingDocumentThumb == false) goto L42;
        TLRPC.InputFile r53 = this.documentInputFile;
        if (r53 == null) goto L42;
        sendUploadMediaRequest(r53, null);
        return;
    L45:
        if (r5 != NotificationCenter.fileUploadProgressChanged) goto L59;
        long r54 = ((Long) r7[1]).longValue();
        long r02 = ((Long) r7[2]).longValue();
        Listener r72 = this.listener;
        if (r72 != null) goto L49;
        return;
    L49:
        if (this.uploadingDocumentThumb == false) goto L51;
        return;
    L51:
        if (r02 <= 0) goto L53;
        float r4 = r54 / r02;
    L54:
        r72.onProgress(r4);
        return;
    L53:
        r4 = 0.0f;
        goto L54
    L59:
        return;
    }

    public void start() {
        if (this.started == false) goto L5;
        return;
    L5:
        if (this.cancelled == false) goto L7;
        return;
    L7:
        if (this.finished == true) goto L32;
        this.started = true;
        if (this.isVideo == false) goto L21;
        Listener r0 = this.listener;
        if (r0 == null) goto L18;
        int r1 = this.videoWidth;
        if (r1 <= 0) goto L18;
        int r2 = this.videoHeight;
        if (r2 <= 0) goto L18;
        r0.onWidthHeightResolved(r1, r2);
    L18:
        beginUpload(this.path);
        return;
    L21:
        if (this.isDocument == false) goto L25;
        Utilities.globalQueue.postRunnable(new RichMediaUploader$$ExternalSyntheticLambda1(this));
        return;
    L25:
        if (this.isAudio == false) goto L28;
        beginUpload(this.path);
        return;
    L28:
        resolvePhotoDimensions();
        Utilities.globalQueue.postRunnable(new RichMediaUploader$$ExternalSyntheticLambda2(this));
        return;
    }
}
