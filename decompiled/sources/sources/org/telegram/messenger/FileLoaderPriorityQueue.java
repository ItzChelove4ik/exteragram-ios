package org.telegram.messenger;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class FileLoaderPriorityQueue {
    public static final int PRIORITY_VALUE_LOW = 0;
    public static final int PRIORITY_VALUE_MAX = 1048576;
    public static final int PRIORITY_VALUE_NORMAL = 65536;
    public static final int TYPE_LARGE = 1;
    public static final int TYPE_SMALL = 0;
    public ArrayList<FileLoadOperation> allOperations;
    Runnable checkOperationsRunnable;
    boolean checkOperationsScheduled;
    int currentAccount;
    String name;
    public ArrayList<FileLoadOperation> tmpListOperations;
    int type;
    final DispatchQueue workerQueue;

    public static /* synthetic */ void $r8$lambda$oeOkmLcjwvzhPNEBqMhc2Z1sUsg(FileLoaderPriorityQueue r0) {
        r0.lambda$new$0();
    }

    public FileLoaderPriorityQueue(int r2, String r3, int r4, DispatchQueue r5) {
        this.allOperations = new ArrayList();
        this.tmpListOperations = new ArrayList();
        this.checkOperationsScheduled = false;
        this.checkOperationsRunnable = new FileLoaderPriorityQueue$$ExternalSyntheticLambda0(this);
        this.currentAccount = r2;
        this.name = r3;
        this.type = r4;
        this.workerQueue = r5;
    }

    private void checkLoadingOperationInternal() {
        int r0 = this.type;
        int r1 = this.currentAccount;
        if (r0 != 1) goto L5;
        int r02 = MessagesController.getInstance(r1).largeQueueMaxActiveOperations;
    L6:
        this.tmpListOperations.clear();
        int r12 = 0;
        int r3 = 0;
        boolean r4 = false;
        int r5 = 0;
    L8:
        if (r3 >= this.allOperations.size()) goto L41;
        if (r3 <= 0) goto L11;
        FileLoadOperation r6 = this.allOperations.get(r3 - 1);
    L12:
        FileLoadOperation r7 = this.allOperations.get(r3);
        if (r3 <= 0) goto L30;
        if (r4 == true) goto L30;
        if (this.type != 1) goto L25;
        if (r6 == null) goto L25;
        if (r6.isStory == false) goto L25;
        if (r6.getPriority() < 1048576) goto L25;
        if (r7.getPriority() > 0) goto L25;
        r4 = true;
    L25:
        if (r5 <= 0) goto L30;
        if (r7.getPriority() != 0) goto L30;
        r4 = true;
    L30:
        if (r7.preFinished == false) goto L32;
        r02 = r02 + 1;
    L39:
        r3 = r3 + 1;
        goto L8
    L32:
        if (r4 == true) goto L36;
        if (r3 >= r02) goto L36;
        this.tmpListOperations.add(r7);
    L38:
        r5 = r7.getPriority();
    L36:
        if (r7.wasStarted() == false) goto L38;
        r7.pause();
        goto L38
    L11:
        r6 = null;
    L41:
        if (r12 >= this.tmpListOperations.size()) goto L43;
        this.tmpListOperations.get(r12).start();
        r12 = r12 + 1;
        goto L41
    L43:
        return;
    L5:
        r02 = MessagesController.getInstance(r1).smallQueueMaxActiveOperations;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        checkLoadingOperationInternal();
        this.checkOperationsScheduled = false;
    }

    public void add(FileLoadOperation r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r0 = 0;
        int r1 = 0;
    L6:
        if (r1 >= this.allOperations.size()) goto L12;
        if (this.allOperations.get(r1) != r4) goto L10;
        this.allOperations.remove(r1);
        r1 = r1 - 1;
    L10:
        r1 = r1 + 1;
    L12:
        if (r0 >= this.allOperations.size()) goto L17;
        if (r4.getPriority() > this.allOperations.get(r0).getPriority()) goto L18;
        r0 = r0 + 1;
    L18:
        ArrayList<FileLoadOperation> r3 = this.allOperations;
        if (r0 < 0) goto L22;
        r3.add(r0, r4);
        return;
    L22:
        r3.add(r4);
        return;
    L17:
        r0 = -1;
        goto L18
    }

    public void cancel(FileLoadOperation r1) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (this.allOperations.remove(r1) == false) goto L8;
        r1.cancel();
        return;
    }

    public void checkLoadingOperations() {
        checkLoadingOperations(false);
    }

    public void checkLoadingOperations(boolean r3) {
        if (r3 == false) goto L6;
        this.workerQueue.cancelRunnable(this.checkOperationsRunnable);
        this.checkOperationsRunnable.run();
        return;
    L6:
        if (this.checkOperationsScheduled == false) goto L8;
        return;
    L8:
        this.checkOperationsScheduled = true;
        this.workerQueue.cancelRunnable(this.checkOperationsRunnable);
        this.workerQueue.postRunnable(this.checkOperationsRunnable, 20);
    }

    public int getCount() {
        return this.allOperations.size();
    }

    public int getPosition(FileLoadOperation r1) {
        return this.allOperations.indexOf(r1);
    }

    public boolean remove(FileLoadOperation r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        return this.allOperations.remove(r1);
    }
}
