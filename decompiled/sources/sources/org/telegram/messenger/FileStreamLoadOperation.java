package org.telegram.messenger;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import j$.net.URLEncoder;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLRPC;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes.dex */
public class FileStreamLoadOperation implements DataSource, FileLoadOperationStream {
    public static final ConcurrentHashMap<Long, FileStreamLoadOperation> allStreams = null;
    private static final ConcurrentHashMap<Long, Integer> priorityMap = null;
    private long bytesRemaining;
    private long bytesTransferred;
    private CountDownLatch countDownLatch;
    private int currentAccount;
    File currentFile;
    private long currentOffset;
    private DataSpec dataSpec;
    private TLRPC.Document document;
    private RandomAccessFile file;
    protected boolean isNetwork;
    private int listenerCount;
    private final ArrayList<TransferListener> listeners;
    private FileLoadOperation loadOperation;
    private boolean opened;
    private Object parentObject;
    private long requestedLength;
    private Uri uri;

    static {
        allStreams = new ConcurrentHashMap();
        priorityMap = new ConcurrentHashMap();
    }

    public FileStreamLoadOperation() {
        this.isNetwork = true;
        this.listeners = new ArrayList(1);
    }

    @Deprecated
    public FileStreamLoadOperation(TransferListener r1) {
        this();
        if (r1 == null) goto L6;
        addTransferListener(r1);
        return;
    }

    private int getCurrentPriority() {
        Integer r3 = priorityMap.getOrDefault(Long.valueOf(this.document.id), null);
        if (r3 != null) goto L5;
        return 3;
    L5:
        return r3.intValue();
    }

    public static int getStreamPrioriy(TLRPC.Document r4) {
        if (r4 != null) goto L5;
        return 3;
    L5:
        Integer r42 = priorityMap.get(Long.valueOf(r4.id));
        if (r42 != null) goto L9;
        return 3;
    L9:
        return r42.intValue();
    }

    public static Uri prepareUri(int r7, TLRPC.Document r8, Object r9) {
        String r3 = FileLoader.getAttachFileName(r8);
        File r4 = FileLoader.getInstance(r7).getPathToAttach(r8);
        if (r4 != null) goto L5;
    L17:
        StringBuilder r42 = new StringBuilder("?account=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(r7);     // Catch: UnsupportedEncodingException -> L14
        r42.append("&id=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(r8.id);     // Catch: UnsupportedEncodingException -> L14
        r42.append("&hash=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(r8.access_hash);     // Catch: UnsupportedEncodingException -> L14
        r42.append("&dc=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(r8.dc_id);     // Catch: UnsupportedEncodingException -> L14
        r42.append("&size=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(r8.size);     // Catch: UnsupportedEncodingException -> L14
        r42.append("&mime=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(URLEncoder.encode(r8.mime_type, "UTF-8"));     // Catch: UnsupportedEncodingException -> L14
        r42.append("&rid=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(FileLoader.getInstance(r7).getFileReference(r9));     // Catch: UnsupportedEncodingException -> L14
        r42.append("&name=");     // Catch: UnsupportedEncodingException -> L14
        r42.append(URLEncoder.encode(FileLoader.getDocumentFileName(r8), "UTF-8"));     // Catch: UnsupportedEncodingException -> L14
        r42.append("&reference=");     // Catch: UnsupportedEncodingException -> L14
        byte[] r72 = r8.file_reference;     // Catch: UnsupportedEncodingException -> L14
        if (r72 != null) goto L12;
        r72 = new byte[0];     // Catch: UnsupportedEncodingException -> L14
    L12:
        r42.append(Utilities.bytesToHex(r72));     // Catch: UnsupportedEncodingException -> L14
        return Uri.parse("tg://" + r3 + r42.toString());
    L14:
        e = move-exception;
        FileLog.e(e);
        return null;
    L5:
        if (r4.exists() == false) goto L17;
        return Uri.fromFile(r4);
    }

    public static void setPriorityForDocument(TLRPC.Document r3, int r4) {
        if (r3 == null) goto L5;
        priorityMap.put(Long.valueOf(r3.id), Integer.valueOf(r4));
        return;
    }

    @Override // androidx.media3.datasource.DataSource
    public final void addTransferListener(TransferListener r2) {
        Assertions.checkNotNull(r2);
        if (this.listeners.contains(r2) == true) goto L6;
        this.listeners.add(r2);
        this.listenerCount++;
        return;
    }

    public final void bytesTransferred(int r5) {
        DataSpec r0 = (DataSpec) Util.castNonNull(this.dataSpec);
        int r1 = 0;
    L4:
        if (r1 >= this.listenerCount) goto L6;
        this.listeners.get(r1).onBytesTransferred(this, r0, this.isNetwork, r5);
        r1 = r1 + 1;
        goto L4
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        FileLog.e("FileStreamLoadOperation " + this.document.id + " close me=" + this);
        FileLoadOperation r0 = this.loadOperation;
        if (r0 == null) goto L5;
        r0.removeStreamListener(this);
    L5:
        RandomAccessFile r02 = this.file;
        if (r02 != null) goto L19;
    L12:
        this.uri = null;
        allStreams.remove(Long.valueOf(this.document.id));
        if (this.opened == false) goto L15;
        this.opened = false;
        transferEnded();
    L15:
        CountDownLatch r03 = this.countDownLatch;
        if (r03 == null) goto L21;
        r03.countDown();
        this.countDownLatch = null;
        return;
    L21:
        return;
    L19:
        r02.close();     // Catch: Exception -> L9
    L11:
        this.file = null;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    @Override // androidx.media3.datasource.DataSource
    public /* bridge */ /* synthetic */ Map getResponseHeaders() {
        return super.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // org.telegram.messenger.FileLoadOperationStream
    public void newDataAvailable() {
        CountDownLatch r0 = this.countDownLatch;
        this.countDownLatch = null;
        if (r0 == null) goto L6;
        r0.countDown();
        return;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r11) {
        this.uri = r11.uri;
        transferInitializing(r11);
        int r0 = Utilities.parseInt(this.uri.getQueryParameter("account"));
        this.currentAccount = r0;
        this.parentObject = FileLoader.getInstance(r0).getParentObject(Utilities.parseInt(this.uri.getQueryParameter("rid")));
        TLRPC.TL_document r02 = new TLRPC.TL_document();
        this.document = r02;
        r02.access_hash = Utilities.parseLong(this.uri.getQueryParameter("hash")).longValue();
        this.document.id = Utilities.parseLong(this.uri.getQueryParameter("id")).longValue();
        this.document.size = Utilities.parseLong(this.uri.getQueryParameter("size")).longValue();
        this.document.dc_id = Utilities.parseInt(this.uri.getQueryParameter("dc"));
        this.document.mime_type = this.uri.getQueryParameter("mime");
        this.document.file_reference = Utilities.hexToBytes(this.uri.getQueryParameter("reference"));
        TLRPC.TL_documentAttributeFilename r03 = new TLRPC.TL_documentAttributeFilename();
        r03.file_name = this.uri.getQueryParameter("name");
        this.document.attributes.add(r03);
        boolean r04 = this.document.mime_type.startsWith(MediaStreamTrack.VIDEO_TRACK_KIND);
        TLRPC.Document r1 = this.document;
        if (r04 == false) goto L6;
        r1.attributes.add(new TLRPC.TL_documentAttributeVideo());
    L8:
        allStreams.put(Long.valueOf(this.document.id), this);
        this.currentOffset = r11.position;
        this.requestedLength = r11.length;
        this.loadOperation = FileLoader.getInstance(this.currentAccount).loadStreamFile(this, this.document, null, this.parentObject, this.currentOffset, false, getCurrentPriority());
        this.bytesTransferred = 0;
        long r05 = this.document.size - r11.position;
        this.bytesRemaining = r05;
        long r3 = this.requestedLength;
        if (r3 == (-1)) goto L11;
        this.bytesRemaining = Math.min(r05, r3);
    L11:
        this.opened = true;
        transferStarted(r11);
        FileLoadOperation r10 = this.loadOperation;
        if (r10 == null) goto L20;
        File r102 = r10.getCurrentFile();
        this.currentFile = r102;
        if (r102 == null) goto L20;
        RandomAccessFile r103 = new RandomAccessFile(this.currentFile, "r");     // Catch: Throwable -> L22
        this.file = r103;     // Catch: Throwable -> L22
        r103.seek(this.currentOffset);     // Catch: Throwable -> L22
        if (this.loadOperation.isFinished() == false) goto L20;
        this.isNetwork = false;     // Catch: Throwable -> L22
        long r104 = this.currentFile.length() - this.currentOffset;     // Catch: Throwable -> L22
        this.bytesRemaining = r104;     // Catch: Throwable -> L22
        long r06 = this.requestedLength;     // Catch: Throwable -> L22
        if (r06 == (-1)) goto L20;
        this.bytesRemaining = Math.min(r104, r06 - this.bytesTransferred);     // Catch: Throwable -> L22
    L20:
        FileLog.e("FileStreamLoadOperation " + this.document.id + " open operation=" + this.loadOperation + " currentFile=" + this.currentFile + " file=" + this.file + " bytesRemaining=" + this.bytesRemaining + " me=" + this);
        FileLog.e("FileStreamLoadOperation " + this.document.id + " " + MessageObject.getVideoWidth(this.document) + "x" + MessageObject.getVideoWidth(this.document) + " mime_type=" + this.document.mime_type + " codec=" + MessageObject.getVideoCodec(this.document) + " size=" + this.document.size);
        return this.bytesRemaining;
    L6:
        if (r1.mime_type.startsWith(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L8;
        this.document.attributes.add(new TLRPC.TL_documentAttributeAudio());
        goto L8
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r13, int r14, int r15) {
        if (r15 != 0) goto L5;
        return 0;
    L5:
        long r1 = this.bytesRemaining;
        if (r1 != 0) goto L10;
        return -1;
    L10:
        if (r1 >= r15) goto L12;
        r15 = (int) r1;
    L12:
        int r12 = 0;
    L13:
        if (r12 == 0) goto L71;
    L16:
        RandomAccessFile r2 = this.file;     // Catch: Exception -> L60 InterruptedException -> L63
        if (r2 != null) goto L53;
    L18:
        r12 = (int) this.loadOperation.getDownloadedLengthFromOffset(this.currentOffset, r15)[0];     // Catch: Exception -> L60 InterruptedException -> L63
        if (r12 != 0) goto L26;
        this.countDownLatch = new CountDownLatch(1);     // Catch: Exception -> L60 InterruptedException -> L63
        FileStreamLoadOperation r4 = this;
        FileLoadOperation r122 = FileLoader.getInstance(this.currentAccount).loadStreamFile(r4, this.document, null, this.parentObject, this.currentOffset, false, this.getCurrentPriority());     // Catch: Exception -> L60 InterruptedException -> L63
        FileStreamLoadOperation r3 = r4;
        FileLoadOperation r22 = r3.loadOperation;     // Catch: Exception -> L60 InterruptedException -> L63
        if (r22 == r122) goto L23;
        r22.removeStreamListener(r3);     // Catch: Exception -> L60 InterruptedException -> L63
        r3.loadOperation = r122;     // Catch: Exception -> L60 InterruptedException -> L63
    L23:
        CountDownLatch r123 = r3.countDownLatch;     // Catch: Exception -> L60 InterruptedException -> L63
        if (r123 == null) goto L27;
        r123.await();     // Catch: Exception -> L60 InterruptedException -> L63
        r3.countDownLatch = null;     // Catch: Exception -> L60 InterruptedException -> L63
    L27:
        File r124 = r3.loadOperation.getCurrentFileFast();     // Catch: Exception -> L60 InterruptedException -> L63
        if (r3.file == null) goto L32;
        if (Objects.equals(r3.currentFile, r124) == false) goto L32;
    L52:
        FileStreamLoadOperation this = r3;
    L32:
        if (BuildVars.LOGS_ENABLED == false) goto L34;
        FileLog.d("check stream file " + r124);     // Catch: Exception -> L60 InterruptedException -> L63
    L34:
        RandomAccessFile r23 = r3.file;     // Catch: Exception -> L60 InterruptedException -> L63
        if (r23 != null) goto L67;
    L37:
        r3.currentFile = r124;     // Catch: Exception -> L60 InterruptedException -> L63
        if (r124 == null) goto L52;
        RandomAccessFile r125 = new RandomAccessFile(r3.currentFile, "r");     // Catch: Throwable -> L45
        r3.file = r125;     // Catch: Throwable -> L45
        r125.seek(r3.currentOffset);     // Catch: Throwable -> L45
        if (r3.loadOperation.isFinished() == false) goto L52;
        r3.isNetwork = false;     // Catch: Throwable -> L45
        long r42 = r3.currentFile.length() - r3.currentOffset;     // Catch: Throwable -> L45
        r3.bytesRemaining = r42;     // Catch: Throwable -> L45
        long r6 = r3.requestedLength;     // Catch: Throwable -> L45
        if (r6 == (-1)) goto L52;
        r3.bytesRemaining = Math.min(r42, r6 - r3.bytesTransferred);     // Catch: Throwable -> L45
        goto L52
    L63:
        e = move-exception;
        FileLog.e(e);
        return -3;
    L60:
        e = move-exception;
        throw new IOException(e);
    L46:
        if (r3.loadOperation.isFinished() == false) goto L52;
        if (r3.currentFile.exists() == true) goto L52;
        FileLoader.getInstance(r3.currentAccount).cancelLoadFile(r3.loadOperation.getFileName());     // Catch: Exception -> L60 InterruptedException -> L63
        FileLoadOperation r126 = FileLoader.getInstance(r3.currentAccount).loadStreamFile(r3, r3.document, null, r3.parentObject, r3.currentOffset, false, r3.getCurrentPriority());     // Catch: Exception -> L60 InterruptedException -> L63
        FileLoadOperation r24 = r3.loadOperation;     // Catch: Exception -> L60 InterruptedException -> L63
        if (r24 == r126) goto L52;
        r24.removeStreamListener(r3);     // Catch: Exception -> L60 InterruptedException -> L63
        r3.loadOperation = r126;     // Catch: Exception -> L60 InterruptedException -> L63
        goto L52
    L67:
        r23.close();     // Catch: Exception -> L66
        goto L37
    L26:
        r3 = this;
        goto L27
    L53:
        FileStreamLoadOperation r32 = this;
        if (r32.opened == true) goto L56;
        return 0;
    L56:
        int r127 = r2.read(r13, r14, r12);     // Catch: Exception -> L60 InterruptedException -> L63
        if (r127 <= 0) goto L59;
        long r0 = r127;
        r32.currentOffset += r0;
        r32.bytesRemaining -= r0;
        r32.bytesTransferred += r0;
        r32.bytesTransferred(r127);     // Catch: Exception -> L60 InterruptedException -> L63
    L59:
        return r127;
    L71:
        if (this.opened == true) goto L18;
        goto L16
    }

    public final void transferEnded() {
        DataSpec r0 = (DataSpec) Util.castNonNull(this.dataSpec);
        int r1 = 0;
    L4:
        if (r1 >= this.listenerCount) goto L6;
        this.listeners.get(r1).onTransferEnd(this, r0, this.isNetwork);
        r1 = r1 + 1;
        goto L4
    L6:
        this.dataSpec = null;
    }

    public final void transferInitializing(DataSpec r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.listenerCount) goto L6;
        this.listeners.get(r0).onTransferInitializing(this, r4, this.isNetwork);
        r0 = r0 + 1;
        goto L4
    }

    public final void transferStarted(DataSpec r4) {
        this.dataSpec = r4;
        int r0 = 0;
    L4:
        if (r0 >= this.listenerCount) goto L6;
        this.listeners.get(r0).onTransferStart(this, r4, this.isNetwork);
        r0 = r0 + 1;
        goto L4
    }
}
