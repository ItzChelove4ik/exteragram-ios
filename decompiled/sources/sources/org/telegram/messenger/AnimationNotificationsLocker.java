package org.telegram.messenger;

/* loaded from: classes.dex */
public class AnimationNotificationsLocker {
    final int[] allowedNotifications;
    int currentAccount;
    boolean disabled;
    int globalNotificationsIndex;
    int notificationsIndex;

    public AnimationNotificationsLocker() {
        this(null);
    }

    public AnimationNotificationsLocker(int[] r2) {
        this.currentAccount = UserConfig.selectedAccount;
        this.notificationsIndex = -1;
        this.globalNotificationsIndex = -1;
        this.allowedNotifications = r2;
    }

    public void disable() {
        this.disabled = true;
    }

    public void lock() {
        if (this.disabled == false) goto L5;
        return;
    L5:
        int r0 = UserConfig.selectedAccount;
        if (this.currentAccount == r0) goto L8;
        NotificationCenter.getInstance(r0).onAnimationFinish(this.notificationsIndex);
        this.notificationsIndex = -1;
        this.currentAccount = r0;
    L8:
        this.notificationsIndex = NotificationCenter.getInstance(r0).setAnimationInProgress(this.notificationsIndex, this.allowedNotifications);
        this.globalNotificationsIndex = NotificationCenter.getGlobalInstance().setAnimationInProgress(this.globalNotificationsIndex, this.allowedNotifications);
    }

    public void unlock() {
        if (this.disabled == false) goto L5;
        return;
    L5:
        NotificationCenter.getInstance(this.currentAccount).onAnimationFinish(this.notificationsIndex);
        NotificationCenter.getGlobalInstance().onAnimationFinish(this.globalNotificationsIndex);
    }
}
