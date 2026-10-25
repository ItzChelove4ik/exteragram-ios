package org.telegram.messenger;

import android.os.Handler;
import android.os.Looper;
import org.telegram.messenger.NotificationCenter;

/* loaded from: classes5.dex */
public final class ContactsLoadingObserver {
    private final Callback callback;
    private final ContactsController contactsController;
    private final int currentAccount;
    private final Handler handler;
    private final NotificationCenter notificationCenter;
    private final NotificationCenter.NotificationCenterDelegate observer;
    private final Runnable releaseRunnable;
    private boolean released;

    public interface Callback {
        void onResult(boolean r1);
    }

    /* renamed from: $r8$lambda$kM2g-LFc-f9ylJneHGxlHgMj5Ho, reason: not valid java name */
    public static /* synthetic */ void m3974$r8$lambda$kM2gLFcf9ylJneHGxlHgMj5Ho(ContactsLoadingObserver r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$qc9N0DYOqntH5H6vQ53jE9XotdM(ContactsLoadingObserver r0, int r1, int r2, Object[] r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    private ContactsLoadingObserver(Callback r2) {
        this.observer = new ContactsLoadingObserver$$ExternalSyntheticLambda0(this);
        this.callback = r2;
        int r22 = UserConfig.selectedAccount;
        this.currentAccount = r22;
        this.releaseRunnable = new ContactsLoadingObserver$$ExternalSyntheticLambda1(this);
        this.contactsController = ContactsController.getInstance(r22);
        this.notificationCenter = NotificationCenter.getInstance(r22);
        this.handler = new Handler(Looper.myLooper());
    }

    private /* synthetic */ void lambda$new$0(int r1, int r2, Object[] r3) {
        if (r1 != NotificationCenter.contactsDidLoad) goto L6;
        onContactsLoadingStateUpdated(r2, false);
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        onContactsLoadingStateUpdated(this.currentAccount, true);
    }

    public static void observe(Callback r1, long r2) {
        new ContactsLoadingObserver(r1).start(r2);
    }

    private boolean onContactsLoadingStateUpdated(int r1, boolean r2) {
        if (this.released == true) goto L9;
        boolean r12 = this.contactsController.contactsLoaded;
        if (r12 == true) goto L7;
        if (r2 == true) goto L7;
        return false;
    L7:
        release();
        this.callback.onResult(r12);
        return true;
    L9:
        return false;
    }

    public void release() {
        if (this.released == true) goto L12;
        NotificationCenter r0 = this.notificationCenter;
        if (r0 == null) goto L7;
        r0.removeObserver(this.observer, NotificationCenter.contactsDidLoad);
    L7:
        Handler r02 = this.handler;
        if (r02 == null) goto L10;
        r02.removeCallbacks(this.releaseRunnable);
    L10:
        this.released = true;
        return;
    }

    public void start(long r4) {
        if (onContactsLoadingStateUpdated(this.currentAccount, false) == true) goto L6;
        this.notificationCenter.addObserver(this.observer, NotificationCenter.contactsDidLoad);
        this.handler.postDelayed(this.releaseRunnable, r4);
        return;
    }
}
