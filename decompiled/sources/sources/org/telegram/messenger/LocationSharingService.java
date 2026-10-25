package org.telegram.messenger;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes5.dex */
public class LocationSharingService extends Service implements NotificationCenter.NotificationCenterDelegate {
    private NotificationCompat.Builder builder;
    private Handler handler;
    private Runnable runnable;

    public static /* synthetic */ void $r8$lambda$5QMbiw4Kk1Rud7adl4PudWg9W3Y(LocationSharingService r0) {
        r0.lambda$onCreate$1();
    }

    /* renamed from: $r8$lambda$SGGdfU93IazPnN-CFc4oSRCju48, reason: not valid java name */
    public static /* synthetic */ void m4260$r8$lambda$SGGdfU93IazPnNCFc4oSRCju48(LocationSharingService r0) {
        r0.lambda$didReceivedNotification$2();
    }

    /* renamed from: $r8$lambda$jDpuTo-nJalawPNBnRWljHyRr88, reason: not valid java name */
    public static /* synthetic */ void m4261$r8$lambda$jDpuTonJalawPNBnRWljHyRr88() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        LocationController.getInstance(r0).update();
        r0 = r0 + 1;
        goto L4
    }

    public LocationSharingService() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.liveLocationsChanged);
    }

    private ArrayList<LocationController.SharingLocationInfo> getInfos() {
        ArrayList<LocationController.SharingLocationInfo> r3 = new ArrayList();
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L9;
        ArrayList<LocationController.SharingLocationInfo> r1 = LocationController.getInstance(r0).sharingLocationsUI;
        if (r1.isEmpty() == true) goto L8;
        r3.addAll(r1);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r3;
    }

    private /* synthetic */ void lambda$didReceivedNotification$2() {
        if (getInfos().isEmpty() == false) goto L6;
        stopSelf();
        return;
    L6:
        updateNotification(true);
    }

    private /* synthetic */ void lambda$onCreate$1() {
        this.handler.postDelayed(this.runnable, 1000);
        Utilities.stageQueue.postRunnable(new LocationSharingService$$ExternalSyntheticLambda0());
    }

    private void updateNotification(boolean r5) {
        if (this.builder == null) goto L21;
        ArrayList<LocationController.SharingLocationInfo> r0 = getInfos();
        if (r0.size() != 1) goto L15;
        LocationController.SharingLocationInfo r02 = r0.get(0);
        long r1 = r02.messageObject.getDialogId();
        int r03 = r02.messageObject.currentAccount;
        if (DialogObject.isUserDialog(r1) == false) goto L10;
        String r04 = UserObject.getFirstName(MessagesController.getInstance(r03).getUser(Long.valueOf(r1)));
        String r12 = LocaleController.getString(R.string.AttachLiveLocationIsSharing);
    L16:
        String r05 = String.format(r12, new Object[]{LocaleController.getString(R.string.AttachLiveLocation), r04});
        this.builder.setTicker(r05);
        this.builder.setContentText(r05);
        if (r5 == false) goto L20;
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(6, this.builder.build());
        return;
    L20:
        return;
    L10:
        TLRPC.Chat r06 = MessagesController.getInstance(r03).getChat(Long.valueOf(-r1));
        if (r06 == null) goto L13;
        r04 = r06.title;
    L14:
        r12 = LocaleController.getString(R.string.AttachLiveLocationIsSharingChat);
        goto L16
    L13:
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L15:
        r04 = LocaleController.formatPluralString("Chats", r0.size(), new Object[0]);
        r12 = LocaleController.getString(R.string.AttachLiveLocationIsSharingChats);
        goto L16
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.liveLocationsChanged) goto L8;
        Handler r12 = this.handler;
        if (r12 == null) goto L9;
        r12.post(new LocationSharingService$$ExternalSyntheticLambda2(this));
        return;
    L9:
        return;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.handler = new Handler();
        LocationSharingService$$ExternalSyntheticLambda1 r0 = new LocationSharingService$$ExternalSyntheticLambda1(this);
        this.runnable = r0;
        this.handler.postDelayed(r0, 1000);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Handler r0 = this.handler;
        if (r0 == null) goto L5;
        r0.removeCallbacks(this.runnable);
    L5:
        stopForeground(true);
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).cancel(6);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.liveLocationsChanged);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r5, int r6, int r7) {
        if (getInfos().isEmpty() == false) goto L15;
        stopSelf();
    L15:
    L9:
        e = move-exception;
        FileLog.e(e);
    L14:
        return 2;
    L7:
        if (this.builder != null) goto L11;
        Intent r62 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);     // Catch: Exception -> L9
        r62.setAction("org.tmessages.openlocations");     // Catch: Exception -> L9
        r62.addCategory("android.intent.category.LAUNCHER");     // Catch: Exception -> L9
        PendingIntent r63 = PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r62, 167772160);     // Catch: Exception -> L9
        NotificationCompat.Builder r0 = new NotificationCompat.Builder(ApplicationLoader.applicationContext);     // Catch: Exception -> L9
        this.builder = r0;     // Catch: Exception -> L9
        r0.setWhen(System.currentTimeMillis());     // Catch: Exception -> L9
        this.builder.setSmallIcon(R.drawable.live_loc);     // Catch: Exception -> L9
        this.builder.setContentIntent(r63);     // Catch: Exception -> L9
        NotificationsController.checkOtherNotificationsChannel();     // Catch: Exception -> L9
        this.builder.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);     // Catch: Exception -> L9
        this.builder.setContentTitle(LocaleController.getString(R.string.AppName));     // Catch: Exception -> L9
        Intent r64 = new Intent(ApplicationLoader.applicationContext, StopLiveLocationReceiver.class);     // Catch: Exception -> L9
        this.builder.addAction(0, LocaleController.getString(R.string.StopLiveLocation), PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 2, r64, 167772160));     // Catch: Exception -> L9
    L11:
        updateNotification(false);     // Catch: Exception -> L9
        startForeground(6, this.builder.build());     // Catch: Exception -> L9
        goto L14
    }
}
