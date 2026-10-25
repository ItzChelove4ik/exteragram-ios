package org.telegram.messenger.voip;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.voip.VoIPHelper;

/* loaded from: classes6.dex */
public final class VoIPPendingCall {
    private AccountInstance accountInstance;
    private final Activity activity;
    private Handler handler;
    private NotificationCenter notificationCenter;
    private final NotificationCenter.NotificationCenterDelegate observer;
    private final Runnable releaseRunnable;
    private boolean released;
    private final long userId;
    private final boolean video;

    /* renamed from: $r8$lambda$KTTuUc5NlKV-7f1eijTJF3HSWbA, reason: not valid java name */
    public static /* synthetic */ void m5287$r8$lambda$KTTuUc5NlKV7f1eijTJF3HSWbA(VoIPPendingCall r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$OkKQTE2globG8Xsd75DEPGnjtVU(VoIPPendingCall r0, int r1, int r2, Object[] r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    private VoIPPendingCall(Activity r3, long r4, boolean r6, long r7, AccountInstance r9) {
        VoIPPendingCall$$ExternalSyntheticLambda0 r0 = new VoIPPendingCall$$ExternalSyntheticLambda0(this);
        this.observer = r0;
        VoIPPendingCall$$ExternalSyntheticLambda1 r1 = new VoIPPendingCall$$ExternalSyntheticLambda1(this);
        this.releaseRunnable = r1;
        this.activity = r3;
        this.userId = r4;
        this.video = r6;
        this.accountInstance = r9;
        if (onConnectionStateUpdated(false) == true) goto L6;
        NotificationCenter r32 = NotificationCenter.getInstance(UserConfig.selectedAccount);
        this.notificationCenter = r32;
        r32.addObserver(r0, NotificationCenter.didUpdateConnectionState);
        Handler r33 = new Handler(Looper.myLooper());
        this.handler = r33;
        r33.postDelayed(r1, r7);
        return;
    }

    private boolean isAirplaneMode() {
        if (Settings.System.getInt(this.activity.getContentResolver(), "airplane_mode_on", 0) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isConnected(AccountInstance r1) {
        if (r1.getConnectionsManager().getConnectionState() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$0(int r1, int r2, Object[] r3) {
        if (r1 != NotificationCenter.didUpdateConnectionState) goto L6;
        onConnectionStateUpdated(false);
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        onConnectionStateUpdated(true);
    }

    private boolean onConnectionStateUpdated(boolean r9) {
        if (this.released == true) goto L23;
        if (r9 == false) goto L6;
    L9:
        MessagesController r92 = this.accountInstance.getMessagesController();
        TLRPC.User r2 = r92.getUser(Long.valueOf(this.userId));
        if (r2 == null) goto L19;
        TLRPC.UserFull r6 = r92.getUserFull(r2.id);
        boolean r3 = this.video;
        if (r6 != null) goto L14;
    L16:
        boolean r4 = false;
    L17:
        VoIPHelper.startCall(r2, r3, r4, this.activity, r6, this.accountInstance);
    L21:
        release();
        return true;
    L14:
        if (r6.video_calls_available == false) goto L16;
        r4 = true;
        goto L17
    L19:
        if (isAirplaneMode() == false) goto L21;
        VoIPHelper.startCall(null, this.video, false, this.activity, null, this.accountInstance);
        goto L21
    L6:
        if (isConnected(this.accountInstance) == true) goto L9;
        if (isAirplaneMode() == true) goto L9;
    L23:
        return false;
    }

    public static VoIPPendingCall startOrSchedule(Activity r8, long r9, boolean r11, AccountInstance r12) {
        return new VoIPPendingCall(r8, r9, r11, 1000, r12);
    }

    public void release() {
        if (this.released == true) goto L12;
        NotificationCenter r0 = this.notificationCenter;
        if (r0 == null) goto L7;
        r0.removeObserver(this.observer, NotificationCenter.didUpdateConnectionState);
    L7:
        Handler r02 = this.handler;
        if (r02 == null) goto L10;
        r02.removeCallbacks(this.releaseRunnable);
    L10:
        this.released = true;
        return;
    }
}
