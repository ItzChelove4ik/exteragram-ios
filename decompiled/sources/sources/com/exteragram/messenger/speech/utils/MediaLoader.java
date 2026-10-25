package com.exteragram.messenger.speech.utils;

import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class MediaLoader implements NotificationCenter.NotificationCenterDelegate {
    private int copiedFiles;
    private final AccountInstance currentAccount;
    private final HashMap<String, MessageObject> loadingMessageObjects;
    private final ArrayList<MessageObject> messageObjects;
    private final MessagesStorage.IntCallback onFinishRunnable;
    private CountDownLatch waitingForFile;

    public static /* synthetic */ void $r8$lambda$LN1oJq2Nb6dpb1B2_tchLzlSv2E(MediaLoader r0) {
        r0.lambda$checkIfFinished$1();
    }

    public static /* synthetic */ void $r8$lambda$O6lO3rGguRaB9e7z0R4OyKGWsbo(MediaLoader r0) {
        r0.lambda$start$0();
    }

    public static /* synthetic */ void $r8$lambda$U5WikN_AuVWJrC2aWbRjtjm7O1A(MediaLoader r0, MessageObject r1) {
        r0.lambda$addMessageToLoad$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$erPzB86DDakKkxLs9ljxAHN6o3w(MediaLoader r0) {
        r0.lambda$checkIfFinished$2();
    }

    private MediaLoader(AccountInstance r2, ArrayList<MessageObject> r3, MessagesStorage.IntCallback r4) {
        this.loadingMessageObjects = new HashMap();
        this.currentAccount = r2;
        this.messageObjects = r3;
        this.onFinishRunnable = r4;
        r2.getNotificationCenter().addObserver(this, NotificationCenter.fileLoaded);
        r2.getNotificationCenter().addObserver(this, NotificationCenter.fileLoadFailed);
    }

    private void addMessageToLoad(MessageObject r2) {
        AndroidUtilities.runOnUIThread(new MediaLoader$$ExternalSyntheticLambda2(this, r2));
    }

    private void checkIfFinished() {
        if (this.loadingMessageObjects.isEmpty() == true) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(new MediaLoader$$ExternalSyntheticLambda1(this));
    }

    private /* synthetic */ void lambda$addMessageToLoad$3(MessageObject r4) {
        TLRPC.Document r0 = r4.getDocument();
        if (r0 != null) goto L5;
        return;
    L5:
        this.loadingMessageObjects.put(FileLoader.getAttachFileName(r0), r4);
        FileLoader r3 = this.currentAccount.getFileLoader();
        if (r4.shouldEncryptPhotoOrVideo() == false) goto L8;
        int r1 = 2;
    L9:
        r3.loadFile(r0, r4, 0, r1);
        return;
    L8:
        r1 = 0;
        goto L9
    }

    private /* synthetic */ void lambda$checkIfFinished$1() {
        this.onFinishRunnable.run(this.copiedFiles);
    }

    private /* synthetic */ void lambda$checkIfFinished$2() {
    L6:
        e = move-exception;
        FileLog.e(e);
    L8:
        this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoaded);
        this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoadFailed);
        return;
    L3:
        if (this.onFinishRunnable == null) goto L8;
        AndroidUtilities.runOnUIThread(new MediaLoader$$ExternalSyntheticLambda3(this));     // Catch: Exception -> L6
        goto L8
    }

    private /* synthetic */ void lambda$start$0() {
        int r0 = Build.VERSION.SDK_INT;     // Catch: Exception -> L58
        ArrayList<MessageObject> r1 = this.messageObjects;
        int r2 = 0;
        if (r0 < 29) goto L36;
        int r02 = r1.size();     // Catch: Exception -> L58
    L6:
        if (r2 >= r02) goto L56;
        MessageObject r12 = this.messageObjects.get(r2);     // Catch: Exception -> L58
        String r5 = r12.messageOwner.attachPath;     // Catch: Exception -> L58
        if (r5 != null) goto L10;
    L14:
        if (r5 != null) goto L16;
    L17:
        TLRPC.Document r52 = r12.getDocument();     // Catch: Exception -> L58
        if (TextUtils.isEmpty(FileLoader.getDocumentFileName(r52)) == false) goto L20;
    L26:
        r5 = null;
    L27:
        if (r5 != null) goto L29;
        r5 = FileLoader.getInstance(this.currentAccount.getCurrentAccount()).getPathToMessage(r12.messageOwner).toString();     // Catch: Exception -> L58
    L29:
        File r6 = new File(r5);     // Catch: Exception -> L58
        if (r6.exists() == true) goto L33;
        this.waitingForFile = new CountDownLatch(1);     // Catch: Exception -> L58
        addMessageToLoad(r12);     // Catch: Exception -> L58
        this.waitingForFile.await();     // Catch: Exception -> L58
    L33:
        if (r6.exists() == false) goto L35;
        this.copiedFiles++;
    L35:
        r2 = r2 + 1;     // Catch: Exception -> L58
        goto L6
    L20:
        if ((r12.messageOwner instanceof TLRPC.TL_message_secret) == true) goto L26;
        if (FileLoader.canSaveAsFile(r12) == false) goto L26;
        String r53 = FileLoader.getDocumentFileName(r52);     // Catch: Exception -> L58
        File r62 = FileLoader.getDirectory(5);     // Catch: Exception -> L58
        if (r62 == null) goto L26;
        r5 = new File(r62, r53).getAbsolutePath();     // Catch: Exception -> L58
        goto L27
    L16:
        if (r5.isEmpty() == false) goto L29;
    L10:
        if (r5.isEmpty() == true) goto L14;
        if (new File(r5).exists() == true) goto L14;
        r5 = null;
    L56:
        checkIfFinished();     // Catch: Exception -> L58
        return;
    L36:
        int r03 = r1.size();     // Catch: Exception -> L58
    L37:
        if (r2 >= r03) goto L56;
        MessageObject r13 = this.messageObjects.get(r2);     // Catch: Exception -> L58
        String r54 = r13.messageOwner.attachPath;     // Catch: Exception -> L58
        if (r54 != null) goto L41;
    L45:
        if (r54 != null) goto L47;
    L48:
        r54 = FileLoader.getInstance(this.currentAccount.getCurrentAccount()).getPathToMessage(r13.messageOwner).toString();     // Catch: Exception -> L58
    L49:
        File r63 = new File(r54);     // Catch: Exception -> L58
        if (r63.exists() == true) goto L53;
        this.waitingForFile = new CountDownLatch(1);     // Catch: Exception -> L58
        addMessageToLoad(r13);     // Catch: Exception -> L58
        this.waitingForFile.await();     // Catch: Exception -> L58
    L53:
        if (r63.exists() == false) goto L55;
        this.copiedFiles++;
    L55:
        r2 = r2 + 1;     // Catch: Exception -> L58
        goto L37
    L47:
        if (r54.isEmpty() == false) goto L49;
    L41:
        if (r54.isEmpty() == true) goto L45;
        if (new File(r54).exists() == true) goto L45;
        r54 = null;
    L58:
        e = move-exception;
        FileLog.e(e);
    }

    public static void loadFiles(AccountInstance r1, ArrayList<MessageObject> r2, MessagesStorage.IntCallback r3) {
        new MediaLoader(r1, r2, r3).start();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.fileLoaded) goto L5;
    L6:
        String r12 = (String) r3[0];
        if (this.loadingMessageObjects.remove(r12) == null) goto L11;
        this.waitingForFile.countDown();
        return;
    L11:
        return;
    L5:
        if (r1 == NotificationCenter.fileLoadFailed) goto L6;
    }

    public void start() {
        new Thread(new MediaLoader$$ExternalSyntheticLambda0(this)).start();
    }
}
