package org.telegram.ui.Components.poll;

import android.text.TextUtils;
import java.io.File;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class FileState {
    private final String attachFileName;
    public final String attachPath;
    public final int currentAccount;
    public final TLRPC.Document document;
    private boolean isExists;
    private boolean isLoading;
    public final MessageObject messageObject;

    public FileState(int r1, MessageObject r2, TLRPC.Document r3, String r4) {
        this.currentAccount = r1;
        this.messageObject = r2;
        this.document = r3;
        this.attachPath = r4;
        if (TextUtils.isEmpty(r4) == false) goto L6;
        r4 = FileLoader.getAttachFileName(r3);
    L6:
        this.attachFileName = r4;
        checkState();
    }

    public void checkState() {
        boolean r1 = false;
        if (this.attachPath == null) goto L5;
        boolean r0 = new File(this.attachPath).exists();
    L6:
        if (r0 == true) goto L8;
        r0 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.document).exists();
    L8:
        this.isExists = r0;
        if (TextUtils.isEmpty(this.attachFileName) == false) goto L11;
    L13:
        this.isLoading = r1;
        return;
    L11:
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(this.attachFileName) == false) goto L13;
        r1 = true;
        goto L13
    L5:
        r0 = false;
        goto L6
    }

    public void downloadCancel() {
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.document);
        checkState();
    }

    public void downloadStart() {
        FileLoader.getInstance(this.currentAccount).loadFile(this.document, this.messageObject, 2, 0);
        checkState();
    }

    public boolean isExists() {
        return this.isExists;
    }

    public boolean isLoading() {
        return this.isLoading;
    }
}
