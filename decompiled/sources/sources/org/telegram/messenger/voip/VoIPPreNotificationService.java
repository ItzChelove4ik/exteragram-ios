package org.telegram.messenger.voip;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.VoIPFragment;
import org.telegram.ui.VoIPPermissionActivity;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class VoIPPreNotificationService {
    public static State currentState;
    public static TL_phone.PhoneCall pendingCall;
    public static Intent pendingVoIP;
    private static MediaPlayer ringtonePlayer;
    private static final Object sync = null;
    private static Vibrator vibrator;

    public static final class State implements VoIPServiceState {
        private final TL_phone.PhoneCall call;
        private final int currentAccount;
        private boolean destroyed;
        private final long userId;

        public State(int r1, long r2, TL_phone.PhoneCall r4) {
            this.currentAccount = r1;
            this.userId = r2;
            this.call = r4;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public void acceptIncomingCall() {
            VoIPPreNotificationService.answer(ApplicationLoader.applicationContext);
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public void declineIncomingCall() {
            VoIPPreNotificationService.decline(ApplicationLoader.applicationContext, 1);
        }

        public void destroy() {
            if (this.destroyed == true) goto L10;
            this.destroyed = true;
            if (VoIPFragment.getInstance() == null) goto L9;
            VoIPFragment.getInstance().onStateChanged(getCallState());
            return;
        L9:
            return;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public int getCallState() {
            if (this.destroyed == false) goto L6;
            return 11;
        L6:
            return 15;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public TLRPC.GroupCall getGroupCall() {
            return null;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public ArrayList<TLRPC.GroupCallParticipant> getGroupParticipants() {
            return null;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public TL_phone.PhoneCall getPrivateCall() {
            return this.call;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public TLRPC.User getUser() {
            return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.userId));
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public boolean isCallingVideo() {
            TL_phone.PhoneCall r0 = this.call;
            if (r0 != null) goto L5;
            return false;
        L5:
            return r0.video;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public boolean isConference() {
            return false;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public boolean isOutgoing() {
            return false;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public void stopRinging() {
            VoIPPreNotificationService.stopRinging();
        }
    }

    /* renamed from: $r8$lambda$-xd9f9h0l8PWlBVsXRSKf-IBo_k, reason: not valid java name */
    public static /* synthetic */ void m5288$r8$lambda$xd9f9h0l8PWlBVsXRSKfIBo_k(int r1, TLObject r2, TLRPC.TL_error r3) {
        if (r3 == null) goto L8;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("(VoIPPreNotification) error on phone.discardCall: " + r3);
        return;
    L14:
        return;
    L8:
        if ((r2 instanceof TLRPC.TL_updates) == false) goto L11;
        MessagesController.getInstance(r1).processUpdates((TLRPC.TL_updates) r2, false);
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("(VoIPPreNotification) phone.discardCall " + r2);
        return;
    }

    public static /* synthetic */ void $r8$lambda$8xuOUE_SZMGh3Mddxb6Oo8iQDH4(MediaPlayer r0) {
        ringtonePlayer.start();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        FileLog.e(th);
    }

    public static /* synthetic */ void $r8$lambda$FKGlbdAziKn85Rug4hrUyX7sDaA(TLObject r2, TLRPC.TL_error r3, Context r4, Runnable r5) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.w("(VoIPPreNotification) receivedCall response = " + r2);
    L5:
        if (r3 != null) goto L7;
        if (r5 == null) goto L17;
        r5.run();
        return;
    L17:
        return;
    L7:
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.e("error on receivedCall: " + r3);
    L9:
        pendingVoIP = null;
        pendingCall = null;
        State r22 = currentState;
        if (r22 == null) goto L12;
        r22.destroy();
    L12:
        dismiss(r4, false);
    }

    public static /* synthetic */ void $r8$lambda$_XVfSiPmqqB3fzeto3OEFrjZsNA() {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.voipLaunchedInBackground == true) goto L7;
        return;
    L7:
        if (VoIPService.getSharedInstance() != null) goto L15;
        r0.voipLaunchedInBackground = false;
        VoIPFragment r1 = VoIPFragment.getInstance();
        if (r1 == null) goto L11;
        r1.finish();
    L11:
        r0.moveTaskToBack(true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$lDZeK9o170qagGUvU23zy7BTSdM(Intent r7, TL_phone.PhoneCall r8, Context r9, int r10, long r11, boolean r13) {
        pendingVoIP = r7;
        pendingCall = r8;
        ((NotificationManager) r9.getSystemService("notification")).notify(203, makeNotification(r9, r10, r11, r8.id, r13));
        startRinging(r9, r10, r11);
    }

    /* renamed from: $r8$lambda$nK1-Z-LTIlrG_zCIeNBBwOPP7xo, reason: not valid java name */
    public static /* synthetic */ void m5289$r8$lambda$nK1ZLTIlrG_zCIeNBBwOPP7xo(Context r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new VoIPPreNotificationService$$ExternalSyntheticLambda4(r3, r4, r1, r2));
    }

    static {
        sync = new Object();
    }

    public VoIPPreNotificationService() {
    }

    private static void acknowledge(Context r4, int r5, TL_phone.PhoneCall r6, Runnable r7) {
        if ((r6 instanceof TL_phone.TL_phoneCallDiscarded) == false) goto L12;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.w("Call " + r6.id + " was discarded before the voip pre notification started, stopping");
    L7:
        pendingVoIP = null;
        pendingCall = null;
        State r42 = currentState;
        if (r42 == null) goto L26;
        r42.destroy();
        return;
    L26:
        return;
    L12:
        if (XiaomiUtilities.isMIUI() == true) goto L14;
    L24:
        TL_phone.receivedCall r0 = new TL_phone.receivedCall();
        TLRPC.TL_inputPhoneCall r1 = new TLRPC.TL_inputPhoneCall();
        r0.peer = r1;
        r1.id = r6.id;
        r1.access_hash = r6.access_hash;
        ConnectionsManager.getInstance(r5).sendRequest(r0, new VoIPPreNotificationService$$ExternalSyntheticLambda5(r4, r7), 2);
        return;
    L14:
        if (XiaomiUtilities.isCustomPermissionGranted(XiaomiUtilities.OP_SHOW_WHEN_LOCKED) == true) goto L24;
        if (((KeyguardManager) r4.getSystemService("keyguard")).inKeyguardRestrictedInputMode() == false) goto L24;
        if (BuildVars.LOGS_ENABLED == false) goto L20;
        FileLog.e("MIUI: no permission to show when locked but the screen is locked. ¯\\_(ツ)_/¯");
    L20:
        pendingVoIP = null;
        pendingCall = null;
        State r43 = currentState;
        if (r43 == null) goto L27;
        r43.destroy();
        return;
    }

    public static void answer(Context r4) {
        FileLog.d("VoIPPreNotification.answer()");
        Intent r0 = pendingVoIP;
        if (r0 != null) goto L6;
        FileLog.d("VoIPPreNotification.answer(): pending intent is not found");
        return;
    L6:
        currentState = null;
        r0.getIntExtra("account", UserConfig.selectedAccount);
        if (VoIPService.getSharedInstance() == null) goto L9;
        VoIPService.getSharedInstance().acceptIncomingCall();
    L21:
        dismiss(r4, true);
        return;
    L9:
        pendingVoIP.putExtra("openFragment", true);
        if (PermissionRequest.hasPermission("android.permission.RECORD_AUDIO") == true) goto L12;
    L30:
        PendingIntent.getActivity(r4, 0, new Intent(r4, VoIPPermissionActivity.class).addFlags(268435456), 1107296256).send();     // Catch: Exception -> L25
        return;
    L25:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L32;
        FileLog.e("Error starting permission activity", e);
        return;
    L32:
        return;
    L12:
        if (isVideo() == false) goto L17;
        if (PermissionRequest.hasPermission("android.permission.CAMERA") == false) goto L30;
    L17:
        if (Build.VERSION.SDK_INT < 26) goto L19;
        r4.startForegroundService(pendingVoIP);
    L20:
        pendingVoIP = null;
        goto L21
    L19:
        r4.startService(pendingVoIP);
        goto L20
    }

    public static boolean canUseCallStyle(NotificationManager r2) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return false;
    L5:
        if (canUseFullScreenIntent(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean canUseFullScreenIntent(NotificationManager r2) {
        if (Build.VERSION.SDK_INT >= 34) goto L7;
        return true;
    L7:
        return r2.canUseFullScreenIntent();
    }

    public static void decline(Context r6, int r7) {
        FileLog.d("VoIPPreNotification.decline(" + r7 + ")");
        Intent r0 = pendingVoIP;
        if (r0 != null) goto L5;
    L19:
        FileLog.d("VoIPPreNotification.decline(" + r7 + "): pending intent or call is not found");
        return;
    L5:
        if (pendingCall == null) goto L19;
        int r02 = r0.getIntExtra("account", UserConfig.selectedAccount);
        TL_phone.discardCall r1 = new TL_phone.discardCall();
        TLRPC.TL_inputPhoneCall r2 = new TLRPC.TL_inputPhoneCall();
        r1.peer = r2;
        TL_phone.PhoneCall r3 = pendingCall;
        r2.access_hash = r3.access_hash;
        r2.id = r3.id;
        r1.duration = 0;
        r1.connection_id = 0;
        if (r7 != 2) goto L10;
        r1.reason = new TLRPC.TL_phoneCallDiscardReasonDisconnect();
    L17:
        FileLog.e("discardCall " + r1.reason);
        ConnectionsManager.getInstance(r02).sendRequest(r1, new VoIPPreNotificationService$$ExternalSyntheticLambda6(r02), 2);
        dismiss(r6, false);
        return;
    L10:
        if (r7 != 3) goto L12;
        r1.reason = new TLRPC.TL_phoneCallDiscardReasonMissed();
        goto L17
    L12:
        if (r7 == 4) goto L14;
        r1.reason = new TLRPC.TL_phoneCallDiscardReasonHangup();
        goto L17
    L14:
        r1.reason = new TLRPC.TL_phoneCallDiscardReasonBusy();
        goto L17
    }

    public static void dismiss(Context r1, boolean r2) {
        FileLog.d("VoIPPreNotification.dismiss()");
        pendingVoIP = null;
        pendingCall = null;
        State r0 = currentState;
        if (r0 == null) goto L5;
        r0.destroy();
    L5:
        ((NotificationManager) r1.getSystemService("notification")).cancel(203);
        stopRinging();
        if (r2 == true) goto L14;
        int r22 = 0;
    L9:
        if (r22 >= 16) goto L11;
        MessagesController.getInstance(r22).ignoreSetOnline = false;
        r22 = r22 + 1;
        goto L9
    L11:
        AndroidUtilities.runOnUIThread(new VoIPPreNotificationService$$ExternalSyntheticLambda7());
        return;
    }

    public static State getState() {
        return currentState;
    }

    public static boolean isVideo() {
        Intent r0 = pendingVoIP;
        if (r0 != null) goto L5;
    L8:
        return false;
    L5:
        if (r0.getBooleanExtra(MediaStreamTrack.VIDEO_TRACK_KIND, false) == false) goto L8;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v17 */
    private static Notification makeNotification(Context r16, int r17, long r18, long r20, boolean r22) {
        if (Build.VERSION.SDK_INT >= 33) goto L5;
        return null;
    L5:
        TLRPC.User r4 = MessagesController.getInstance(r17).getUser(Long.valueOf(r18));
        NotificationManager r6 = (NotificationManager) r16.getSystemService("notification");
        Intent r8 = new Intent(r16, LaunchActivity.class).setAction("voip");
        Notification.Builder r0 = new Notification.Builder(r16);
        if (r22 == false) goto L8;
        int r9 = R.string.VoipInVideoCallBranding;
    L9:
        Notification.Builder r11 = r0.setContentTitle(LocaleController.getString(r9)).setSmallIcon(R.drawable.call).setContentIntent(PendingIntent.getActivity(r16, 0, r8, 301989888));
        SharedPreferences r02 = MessagesController.getGlobalNotificationsSettings();
        int r13 = r02.getInt("calls_notification_channel", 0);
        NotificationChannel r14 = r6.getNotificationChannel("incoming_calls2" + r13);
        if (r14 == null) goto L12;
        r6.deleteNotificationChannel(r14.getId());
    L12:
        NotificationChannel r142 = r6.getNotificationChannel("incoming_calls3" + r13);
        if (r142 == null) goto L15;
        r6.deleteNotificationChannel(r142.getId());
    L15:
        NotificationChannel r143 = r6.getNotificationChannel("incoming_calls4" + r13);
        if (r143 != null) goto L18;
    L27:
        boolean r03 = true;
    L29:
        if (r03 == false) goto L41;
        AudioAttributes r04 = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(2).setUsage(2).build();
        CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1.m();
        NotificationChannel r10 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("incoming_calls4" + r13, LocaleController.getString(R.string.IncomingCallsSystemSetting), 4);
        r10.setSound(null, r04);     // Catch: Exception -> L33
    L35:
        r10.setDescription(LocaleController.getString(R.string.IncomingCallsSystemSettingDescription));
        r10.enableVibration(false);
        r10.enableLights(false);
        r10.setBypassDnd(true);
        r6.createNotificationChannel(r10);     // Catch: Exception -> L38
    L38:
        e = move-exception;
        FileLog.e(e);
        return null;
    L33:
        e = move-exception;
        FileLog.e(e);
    L41:
        r11.setChannelId("incoming_calls4" + r13);
        Intent r05 = new Intent(r16, VoIPActionsReceiver.class);
        r05.setAction(r16.getPackageName() + ".DECLINE_CALL");
        r05.putExtra("call_id", r20);
        String r12 = LocaleController.getString(R.string.VoipDeclineCall);
        int r132 = Build.VERSION.SDK_INT;
        if (r132 >= 31) goto L45;
        SpannableString r15 = new SpannableString(r12);
        int r144 = 0;
        r15.setSpan(new ForegroundColorSpan(-769226), 0, r15.length(), 0);
        r12 = r15;
    L46:
        PendingIntent r06 = PendingIntent.getBroadcast(r16, r144, r05, 301989888);
        Intent r92 = new Intent(r16, VoIPActionsReceiver.class);
        r92.setAction(r16.getPackageName() + ".ANSWER_CALL");
        r92.putExtra("call_id", r20);
        String r2 = LocaleController.getString(R.string.VoipAnswerCall);
        if (r132 >= 31) goto L49;
        SpannableString r3 = new SpannableString(r2);
        int r145 = 0;
        r3.setSpan(new ForegroundColorSpan(-16733696), 0, r3.length(), 0);
        r2 = r3;
    L50:
        PendingIntent r32 = PendingIntent.getActivity(r16, r145, new Intent(r16, LaunchActivity.class).setAction("voip_answer"), 301989888);
        r11.setPriority(2);
        r11.setShowWhen(r145);
        boolean r7 = canUseFullScreenIntent(r6);
        r11.setColor(-13851168);
        r11.setVibrate(new long[r145]);
        r11.setCategory("call");
        if (r7 == false) goto L53;
        r11.setFullScreenIntent(PendingIntent.getActivity(r16, r145, r8, 33554432), true);
    L53:
        if (r4 != null) goto L55;
    L57:
        Intent r72 = new Intent(ApplicationLoader.applicationContext, VoIPActionsReceiver.class);
        r72.setAction(r16.getPackageName() + ".HIDE_CALL");
        r11.setDeleteIntent(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, r72, 167772160));
        String r5 = ContactsController.formatName(r4);
        if (TextUtils.isEmpty(r5) == false) goto L60;
        r5 = "___";
    L60:
        Bitmap r1 = VoIPService.getRoundAvatarBitmap(r16, r17, r4);
        if (canUseCallStyle(r6) == false) goto L63;
        r11.setStyle(Notification.CallStyle.forIncomingCall(VoIPGroupNotification$$ExternalSyntheticApiModelOutline0.m().setName(r5).setIcon(Icon.createWithAdaptiveBitmap(r1)).build(), r06, r32));
    L65:
        return r11.build();
    L63:
        r11.setContentText(r5).setLargeIcon(r1).addAction(R.drawable.ic_call_end_white_24dp, r12, r06).addAction(R.drawable.ic_call, r2, r32);
        goto L65
    L55:
        if (TextUtils.isEmpty(r4.phone) == true) goto L57;
        r11.addPerson("tel:" + r4.phone);
        goto L57
    L49:
        r145 = 0;
        goto L50
    L45:
        r144 = 0;
        goto L46
    L18:
        if (r143.getImportance() < 4) goto L24;
        if (r143.getSound() != null) goto L24;
        r03 = false;
    L24:
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("User messed up the notification channel; deleting it and creating a proper one");
    L26:
        r6.deleteNotificationChannel("incoming_calls4" + r13);
        r13 = r13 + 1;
        r02.edit().putInt("calls_notification_channel", r13).commit();
        goto L27
    L8:
        r9 = R.string.VoipInCallBranding;
        goto L9
    }

    public static boolean open(Context r5) {
        if (VoIPService.getSharedInstance() == null) goto L5;
        return true;
    L5:
        Intent r0 = pendingVoIP;
        if (r0 != null) goto L8;
    L16:
        return false;
    L8:
        if (pendingCall == null) goto L16;
        r0.getIntExtra("account", UserConfig.selectedAccount);
        pendingVoIP.putExtra("openFragment", true);
        pendingVoIP.putExtra("accept", false);
        if (Build.VERSION.SDK_INT < 26) goto L13;
        r5.startForegroundService(pendingVoIP);
    L14:
        pendingVoIP = null;
        dismiss(r5, true);
        return true;
    L13:
        r5.startService(pendingVoIP);
        goto L14
    }

    public static void show(Context r10, Intent r11, TL_phone.PhoneCall r12) {
        FileLog.d("VoIPPreNotification.show()");
        if (r12 == null) goto L13;
        if (r11 == null) goto L13;
        TL_phone.PhoneCall r1 = pendingCall;
        if (r1 != null) goto L9;
    L11:
        dismiss(r10, false);
        pendingVoIP = r11;
        pendingCall = r12;
        int r6 = r11.getIntExtra("account", UserConfig.selectedAccount);
        long r7 = r11.getLongExtra("user_id", 0);
        boolean r9 = r12.video;
        currentState = new State(r6, r7, r12);
        acknowledge(r10, r6, r12, new VoIPPreNotificationService$$ExternalSyntheticLambda2(r11, r12, r10, r6, r7, r9));
        return;
    L9:
        if (r1.id != r12.id) goto L11;
        return;
    L13:
        dismiss(r10, false);
        FileLog.d("VoIPPreNotification.show(): call or intent is null");
    }

    public static void startRinging(Context r12, int r13, long r14) {
        SharedPreferences r132 = MessagesController.getNotificationsSettings(r13);
        AudioManager r5 = (AudioManager) r12.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r5.getRingerMode() == 0) goto L5;
        boolean r6 = true;
    L6:
        boolean r9 = r5.isWiredHeadsetOn();
        if (r6 == true) goto L9;
        return;
    L9:
        if (ringtonePlayer != null) goto L77;
        Object r62 = sync;
        monitor-enter(r62);
    L17:
        th = move-exception;
        throw th;
    L14:
        if (ringtonePlayer == null) goto L19;
        monitor-exit(r62);     // Catch: Throwable -> L17
        return;
    L19:
        MediaPlayer r10 = new MediaPlayer();     // Catch: Throwable -> L17
        ringtonePlayer = r10;     // Catch: Throwable -> L17
        r10.setOnPreparedListener(new VoIPPreNotificationService$$ExternalSyntheticLambda3());     // Catch: Throwable -> L17
        ringtonePlayer.setLooping(true);     // Catch: Throwable -> L17
        if (r9 == false) goto L22;
        ringtonePlayer.setAudioStreamType(0);     // Catch: Throwable -> L17
    L73:
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L17
        MediaPlayer r2 = ringtonePlayer;     // Catch: Throwable -> L17
        if (r2 == null) goto L45;
        r2.release();     // Catch: Throwable -> L17
        ringtonePlayer = null;     // Catch: Throwable -> L17
    L45:
        if (r132.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r14, false) == false) goto L47;
        int r133 = r132.getInt("calls_vibrate_" + r14, 0);     // Catch: Throwable -> L17
    L49:
        if (r133 == 2) goto L55;
        if (r133 == 4) goto L55;
        if (r5.getRingerMode() != 1) goto L54;
    L58:
        vibrator = (Vibrator) r12.getSystemService("vibrator");     // Catch: Throwable -> L17
        if (r133 != 1) goto L61;
        long r134 = 350;
    L64:
        long[] r122 = {0, r134, 500};     // Catch: Throwable -> L17
        if (Build.VERSION.SDK_INT < 33) goto L67;
        Vibrator r135 = vibrator;     // Catch: Throwable -> L17
        VibrationEffect r123 = VibrationEffect.createWaveform(r122, 0);     // Catch: Throwable -> L17
        VoIPPreNotificationService$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L17
        r135.vibrate(r123, VoIPPreNotificationService$$ExternalSyntheticApiModelOutline0.m().setUsage(33).build());     // Catch: Throwable -> L17
    L68:
        monitor-exit(r62);     // Catch: Throwable -> L17
        return;
    L67:
        vibrator.vibrate(r122, 0);     // Catch: Throwable -> L17
        goto L68
    L61:
        if (r133 != 3) goto L63;
        r134 = 1400;
        goto L64
    L63:
        r134 = 700;
        goto L64
    L54:
        if (r5.getRingerMode() == 2) goto L58;
    L55:
        if (r133 != 4) goto L68;
        if (r5.getRingerMode() != 1) goto L68;
    L47:
        r133 = r132.getInt("vibrate_calls", 0);     // Catch: Throwable -> L17
        goto L49
    L25:
        if (r132.getBoolean(NotificationsSettingsFacade.PROPERTY_CUSTOM + r14, false) == false) goto L29;
        String r3 = r132.getString("ringtone_path_" + r14, null);     // Catch: Throwable -> L17 Exception -> L27
    L30:
        if (r3 != null) goto L33;
        Uri r32 = RingtoneManager.getDefaultUri(1);     // Catch: Throwable -> L17 Exception -> L27
    L32:
        boolean r4 = true;
    L39:
        FileLog.d("start ringtone with " + r4 + " " + r32);     // Catch: Throwable -> L17 Exception -> L27
        ringtonePlayer.setDataSource(r12, r32);     // Catch: Throwable -> L17 Exception -> L27
        ringtonePlayer.prepareAsync();     // Catch: Throwable -> L17 Exception -> L27
    L33:
        Uri r42 = Settings.System.DEFAULT_RINGTONE_URI;     // Catch: Throwable -> L17 Exception -> L27
        if (r42 != null) goto L36;
    L38:
        r32 = Uri.parse(r3);     // Catch: Throwable -> L17 Exception -> L27
        r4 = false;
        goto L39
    L36:
        if (r3.equalsIgnoreCase(r42.getPath()) == false) goto L38;
        r32 = RingtoneManager.getDefaultUri(1);     // Catch: Throwable -> L17 Exception -> L27
        goto L32
    L29:
        r3 = r132.getString("CallsRingtonePath", null);     // Catch: Throwable -> L17 Exception -> L27
        goto L30
    L22:
        ringtonePlayer.setAudioStreamType(2);     // Catch: Throwable -> L17
        goto L73
    L77:
        return;
    L5:
        r6 = false;
        goto L6
    }

    public static void stopRinging() {
        Object r0 = sync;
        monitor-enter(r0);
        MediaPlayer r1 = ringtonePlayer;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.stop();     // Catch: Throwable -> L7
        ringtonePlayer.release();     // Catch: Throwable -> L7
        ringtonePlayer = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        Vibrator r02 = vibrator;
        if (r02 == null) goto L18;
        r02.cancel();
        vibrator = null;
        return;
    L18:
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
