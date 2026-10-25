package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class AnimatedFileDrawableStream implements FileLoadOperationStream {
    private volatile boolean canceled;
    private volatile CountDownLatch countDownLatch;
    private int currentAccount;
    private int debugCanceledCount;
    private boolean debugReportSend;
    private TLRPC.Document document;
    private String finishedFilePath;
    private boolean finishedLoadingFile;
    private long lastOffset;
    private FileLoadOperation loadOperation;
    private int loadingPriority;
    private ImageLocation location;
    private Object parentObject;
    private boolean preview;
    private final Object sync;
    private boolean waitingForLoad;

    public AnimatedFileDrawableStream(TLRPC.Document r11, ImageLocation r12, Object r13, int r14, boolean r15, int r16, int r17) {
        this.sync = new Object();
        this.document = r11;
        this.location = r12;
        this.parentObject = r13;
        this.currentAccount = r14;
        this.preview = r15;
        this.loadingPriority = r16;
        this.loadOperation = FileLoader.getInstance(r14).loadStreamFile(this, this.document, this.location, this.parentObject, 0, this.preview, r16, r17);
    }

    private void cancelLoadingInternal() {
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.document);
        if (this.location == null) goto L6;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.location.location, "mp4");
        return;
    }

    public void cancel() {
        cancel(true);
    }

    public void cancel(boolean r6) {
        if (this.canceled == false) goto L5;
        return;
    L5:
        Object r0 = this.sync;
        monitor-enter(r0);
        CountDownLatch r1 = this.countDownLatch;     // Catch: Throwable -> L16
        if (r1 == null) goto L18;
        r1.countDown();     // Catch: Throwable -> L16
        if (r6 == false) goto L18;
        if (this.canceled == true) goto L18;
        if (this.preview == true) goto L18;
        FileLoader.getInstance(this.currentAccount).removeLoadingVideo(this.document, false, true);     // Catch: Throwable -> L16
    L18:
        Object r12 = this.parentObject;     // Catch: Throwable -> L16
        if ((r12 instanceof MessageObject) == false) goto L23;
        MessageObject r13 = (MessageObject) r12;     // Catch: Throwable -> L16
        if (DownloadController.getInstance(r13.currentAccount).isDownloading(r13.getId()) == false) goto L23;
        r6 = false;
    L23:
        if (r6 == false) goto L25;
        cancelLoadingInternal();     // Catch: Throwable -> L16
    L25:
        this.canceled = true;     // Catch: Throwable -> L16
        monitor-exit(r0);     // Catch: Throwable -> L16
        return;
    L16:
        th = move-exception;
        throw th;
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public TLRPC.Document getDocument() {
        return this.document;
    }

    public String getFinishedFilePath() {
        return this.finishedFilePath;
    }

    public ImageLocation getLocation() {
        return this.location;
    }

    public Object getParentObject() {
        return this.document;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean isFinishedLoadingFile() {
        return this.finishedLoadingFile;
    }

    public boolean isPreview() {
        return this.preview;
    }

    public boolean isWaitingForLoad() {
        return this.waitingForLoad;
    }

    @Override // org.telegram.messenger.FileLoadOperationStream
    public void newDataAvailable() {
        CountDownLatch r0 = this.countDownLatch;
        if (r0 == null) goto L6;
        r0.countDown();
        return;
    }

    public int read(int r22, int r23) {
        Object r2 = this.sync;
        monitor-enter(r2);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.canceled == false) goto L15;
        int r0 = this.debugCanceledCount + 1;     // Catch: Throwable -> L11
        this.debugCanceledCount = r0;     // Catch: Throwable -> L11
        if (this.debugReportSend == false) goto L9;
    L13:
        monitor-exit(r2);     // Catch: Throwable -> L11
        return 0;
    L9:
        if (r0 <= 200) goto L13;
        this.debugReportSend = true;     // Catch: Throwable -> L11
        FileLog.e(new RuntimeException("infinity stream reading!!!"));     // Catch: Throwable -> L11
        goto L13
    L15:
        monitor-exit(r2);     // Catch: Throwable -> L11
        if (r23 != 0) goto L18;
        return 0;
    L18:
        long r13 = 0;
        long r24 = 0;
    L20:
        if (r24 != r13) goto L96;
        CountDownLatch r15 = new CountDownLatch(1);     // Catch: Exception -> L93
        this.countDownLatch = r15;     // Catch: Exception -> L93
        long r5 = r22;
        long[] r02 = this.loadOperation.getDownloadedLengthFromOffset(r5, r23);     // Catch: Exception -> L93
        long r16 = r02[0];     // Catch: Exception -> L93
        long r25 = r02[1];     // Catch: Exception -> L34
        if (r25 <= r13) goto L25;
        boolean r03 = true;
    L27:
        if (r25 >= r13) goto L29;
        boolean r26 = true;
    L31:
        if (this.finishedLoadingFile == true) goto L37;
        if (r03 == false) goto L37;
        this.finishedLoadingFile = true;     // Catch: Exception -> L34
        this.finishedFilePath = this.loadOperation.getCacheFileFinal().getAbsolutePath();     // Catch: Exception -> L34
    L37:
        if (r16 != r13) goto L88;
        Object r4 = this.sync;     // Catch: Exception -> L34
        monitor-enter(r4);     // Catch: Exception -> L34
    L45:
        th = move-exception;
        throw th;     // Catch: Exception -> L34
    L41:
        if (this.canceled == true) goto L43;
        monitor-exit(r4);     // Catch: Throwable -> L45
        if (this.loadOperation.isPaused() == true) goto L60;
        if (r26 == true) goto L60;
        if (this.lastOffset != r5) goto L60;
        if (this.preview == true) goto L60;
        long r19 = r13;
        CountDownLatch r132 = null;
    L64:
        Object r27 = this.sync;     // Catch: Exception -> L34
        monitor-enter(r27);     // Catch: Exception -> L34
    L71:
        th = move-exception;
        throw th;     // Catch: Exception -> L34
    L67:
        if (this.canceled == true) goto L69;
        monitor-exit(r27);     // Catch: Throwable -> L71
        if (this.preview == true) goto L81;
        FileLoader.getInstance(this.currentAccount).setLoadingVideo(this.document, false, true);     // Catch: Exception -> L34
    L81:
        if (r15.getCount() == r19) goto L90;
        this.waitingForLoad = true;     // Catch: Exception -> L34
        r15.await();     // Catch: Exception -> L34
        this.waitingForLoad = false;     // Catch: Exception -> L34
    L90:
        if (this.countDownLatch != r15) goto L92;
        this.countDownLatch = r132;     // Catch: Exception -> L34
    L92:
        r24 = r16;
        r13 = r19;
        goto L20
    L69:
        if (this.countDownLatch != r15) goto L73;
        this.countDownLatch = r132;     // Catch: Throwable -> L71
    L73:
        cancelLoadingInternal();     // Catch: Throwable -> L71
        monitor-exit(r27);     // Catch: Throwable -> L71
        return 0;
    L60:
        r19 = r13;
        r132 = null;
        FileLoadOperation r04 = FileLoader.getInstance(this.currentAccount).loadStreamFile(this, this.document, this.location, this.parentObject, r5, this.preview, this.loadingPriority);     // Catch: Exception -> L34
        FileLoadOperation r28 = this.loadOperation;     // Catch: Exception -> L34
        if (r28 == r04) goto L63;
        r28.removeStreamListener(this);     // Catch: Exception -> L34
        this.loadOperation = r04;     // Catch: Exception -> L34
    L63:
        this.lastOffset = r5 + r16;     // Catch: Exception -> L34
        goto L64
    L43:
        if (this.countDownLatch != r15) goto L47;
        this.countDownLatch = null;     // Catch: Throwable -> L45
    L47:
        cancelLoadingInternal();     // Catch: Throwable -> L45
        monitor-exit(r4);     // Catch: Throwable -> L45
        return 0;
    L88:
        r19 = r13;
        r132 = null;
        goto L90
    L29:
        r26 = false;
        goto L31
    L25:
        r03 = false;
    L34:
        e = e;
        r24 = r16;
    L98:
        FileLog.e(e);
    L100:
        return (int) r24;
    L93:
        e = e;
        goto L98
    L96:
        this.lastOffset = r22 + r24;     // Catch: Exception -> L93
        goto L100
    }

    public void reset() {
        Object r0 = this.sync;
        monitor-enter(r0);
        this.canceled = false;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
