package org.telegram.ui.Components;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes7.dex */
final class TelegramRoundVideoUpload implements RoundVideoSession.OutputListener, NotificationCenter.NotificationCenterDelegate {
    private final int currentAccount;
    private final boolean encrypted;
    private final Map<Long, Generation> generations;
    private boolean released;

    public static final class Generation {
        long availableSize;
        TLRPC.InputEncryptedFile encryptedFile;
        final File file;
        long finalSize;
        final long id;
        TLRPC.InputFile inputFile;
        boolean invalidated;
        byte[] iv;
        byte[] key;
        boolean uploadStarted;

        public Generation(long r1, File r3) {
            this.id = r1;
            this.file = r3;
        }
    }

    public static final class UploadInfo {
        final TLRPC.InputEncryptedFile encryptedFile;
        final File file;
        final TLRPC.InputFile inputFile;
        final byte[] iv;
        final byte[] key;
        final long size;

        public UploadInfo(File r1, long r2, TLRPC.InputFile r4, TLRPC.InputEncryptedFile r5, byte[] r6, byte[] r7) {
            this.file = r1;
            this.size = r2;
            this.inputFile = r4;
            this.encryptedFile = r5;
            this.key = r6;
            this.iv = r7;
        }
    }

    public TelegramRoundVideoUpload(int r2, boolean r3) {
        this.generations = new HashMap();
        this.currentAccount = r2;
        this.encrypted = r3;
        NotificationCenter.getInstance(r2).addObserver(this, NotificationCenter.fileUploaded);
    }

    private void startUpload(Generation r10) {
        if (r10.uploadStarted == false) goto L5;
        return;
    L5:
        r10.uploadStarted = true;
        FileLoader.getInstance(this.currentAccount).uploadFile(r10.file.getAbsolutePath(), this.encrypted, false, 1, 33554432, false);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public synchronized void didReceivedNotification(int r4, int r5, Object... r6) {
        monitor-enter(this);
    L19:
        th = move-exception;
        throw th;
    L4:
        if (this.released == false) goto L6;
    L23:
        monitor-exit(this);
        return;
    L6:
        if (r4 != NotificationCenter.fileUploaded) goto L23;
        if (r6.length < 6) goto L23;
        String r42 = (String) r6[0];     // Catch: Throwable -> L19
        Iterator<Generation> r52 = this.generations.values().iterator();     // Catch: Throwable -> L19
    L12:
        if (r52.hasNext() == false) goto L21;
        Generation r0 = r52.next();     // Catch: Throwable -> L19
        if (r0.invalidated == true) goto L12;
        if (r0.file.getAbsolutePath().equals(r42) == false) goto L12;
        r0.inputFile = (TLRPC.InputFile) r6[1];     // Catch: Throwable -> L19
        r0.encryptedFile = (TLRPC.InputEncryptedFile) r6[2];     // Catch: Throwable -> L19
        r0.key = (byte[]) r6[3];     // Catch: Throwable -> L19
        r0.iv = (byte[]) r6[4];     // Catch: Throwable -> L19
        r0.finalSize = Math.max(r0.finalSize, ((Long) r6[5]).longValue());     // Catch: Throwable -> L19
    L21:
        monitor-exit(this);
    }

    public synchronized UploadInfo getUploadInfo(long r13, File r15) {
        monitor-enter(this);
        Generation r132 = this.generations.get(Long.valueOf(r13));     // Catch: Throwable -> L11
        if (r132 == null) goto L13;
        if (r132.invalidated == true) goto L13;
        UploadInfo r4 = new UploadInfo(r15, Math.max(r132.finalSize, r15.length()), r132.inputFile, r132.encryptedFile, r132.key, r132.iv);     // Catch: Throwable -> L11
        monitor-exit(this);
        return r4;
    L13:
        UploadInfo r0 = new UploadInfo(r15, r15.length(), null, null, null, null);     // Catch: Throwable -> L11
        monitor-exit(this);
        return r0;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.OutputListener
    public synchronized void onBytesAvailable(long r8, File r10, long r11, long r13) {
        monitor-enter(this);
        Generation r82 = this.generations.get(Long.valueOf(r8));     // Catch: Throwable -> L12
        if (this.released == true) goto L14;
        if (r82 == null) goto L14;
        if (r82.invalidated == true) goto L14;
        startUpload(r82);     // Catch: Throwable -> L12
        r82.availableSize = Math.max(r82.availableSize, r11 + r13);     // Catch: Throwable -> L12
        FileLoader.getInstance(this.currentAccount).checkUploadNewDataAvailable(r10.getAbsolutePath(), this.encrypted, r82.availableSize, 0);     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L14:
        monitor-exit(this);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.OutputListener
    public synchronized void onOutputCompleted(long r8, File r10, long r11, long r13, boolean r15) {
        monitor-enter(this);
        Generation r82 = this.generations.get(Long.valueOf(r8));     // Catch: Throwable -> L12
        if (this.released == true) goto L14;
        if (r82 == null) goto L14;
        if (r82.invalidated == true) goto L14;
        startUpload(r82);     // Catch: Throwable -> L12
        r82.availableSize = Math.max(r82.availableSize, r11);     // Catch: Throwable -> L12
        r82.finalSize = r11;     // Catch: Throwable -> L12
        FileLoader.getInstance(this.currentAccount).checkUploadNewDataAvailable(r10.getAbsolutePath(), this.encrypted, r82.availableSize, r11);     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L14:
        monitor-exit(this);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.OutputListener
    public synchronized void onOutputError(long r1, Exception r3) {
        monitor-enter(this);
        onOutputInvalidated(r1, RoundVideoSession.OutputInvalidationReason.ERROR);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.OutputListener
    public synchronized void onOutputInvalidated(long r1, RoundVideoSession.OutputInvalidationReason r3) {
        monitor-enter(this);
        Generation r12 = this.generations.remove(Long.valueOf(r1));     // Catch: Throwable -> L12
        if (r12 != null) goto L8;
        monitor-exit(this);
        return;
    L8:
        r12.invalidated = true;     // Catch: Throwable -> L12
        if (r12.uploadStarted == false) goto L14;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(r12.file.getAbsolutePath(), this.encrypted);     // Catch: Throwable -> L12
    L14:
        monitor-exit(this);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoSession.OutputListener
    public synchronized void onOutputStarted(long r4, File r6) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.released == false) goto L7;
        monitor-exit(this);
        return;
    L7:
        this.generations.put(Long.valueOf(r4), new Generation(r4, r6));     // Catch: Throwable -> L10
        monitor-exit(this);
    }

    public synchronized void release(boolean r4) {
        monitor-enter(this);
    L18:
        th = move-exception;
        throw th;
    L4:
        if (this.released == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        this.released = true;     // Catch: Throwable -> L18
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);     // Catch: Throwable -> L18
        if (r4 == false) goto L22;
        Iterator<Generation> r42 = this.generations.values().iterator();     // Catch: Throwable -> L18
    L11:
        if (r42.hasNext() == false) goto L22;
        Generation r0 = r42.next();     // Catch: Throwable -> L18
        if (r0.uploadStarted == false) goto L20;
        if (r0.invalidated == true) goto L20;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(r0.file.getAbsolutePath(), this.encrypted);     // Catch: Throwable -> L18
    L20:
        r42.remove();     // Catch: Throwable -> L18
    L22:
        monitor-exit(this);
    }
}
