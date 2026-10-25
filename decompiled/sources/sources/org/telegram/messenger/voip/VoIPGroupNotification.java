package org.telegram.messenger.voip;

import android.app.Activity;
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
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.VoIPFragment;

/* loaded from: classes6.dex */
public class VoIPGroupNotification {
    public static long currentCallId;
    public static State currentState;
    private static HashSet<Integer> ignoreCalls;
    private static Runnable missRunnable;

    public static final class State implements VoIPServiceState {
        public final long call_id;
        private final int currentAccount;
        private boolean destroyed;
        public final long dialogId;
        private final TLRPC.GroupCall groupCall;
        private final TLRPC.InputGroupCall inputGroupCall;
        public final int msg_id;
        private final ArrayList<TLRPC.GroupCallParticipant> participants;
        private final boolean video;

        /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5285$$Nest$fgetcurrentAccount(State r0) {
            return r0.currentAccount;
        }

        /* renamed from: -$$Nest$fgetgroupCall, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.GroupCall m5286$$Nest$fgetgroupCall(State r0) {
            return r0.groupCall;
        }

        public State(int r1, long r2, long r4, int r6, boolean r7, TLRPC.GroupCall r8, ArrayList<TLRPC.GroupCallParticipant> r9) {
            this.currentAccount = r1;
            this.dialogId = r2;
            this.call_id = r4;
            this.msg_id = r6;
            TLRPC.TL_inputGroupCallInviteMessage r12 = new TLRPC.TL_inputGroupCallInviteMessage();
            this.inputGroupCall = r12;
            r12.msg_id = r6;
            this.groupCall = r8;
            this.participants = r9;
            this.video = r7;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public void acceptIncomingCall() {
            VoIPGroupNotification.answer(ApplicationLoader.applicationContext, this.currentAccount, this.msg_id);
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public void declineIncomingCall() {
            VoIPGroupNotification.decline(ApplicationLoader.applicationContext, this.currentAccount, this.msg_id);
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
            return this.groupCall;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public ArrayList<TLRPC.GroupCallParticipant> getGroupParticipants() {
            return this.participants;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public TL_phone.PhoneCall getPrivateCall() {
            return null;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public TLRPC.User getUser() {
            return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public boolean isCallingVideo() {
            return this.video;
        }

        @Override // org.telegram.messenger.voip.VoIPServiceState
        public boolean isConference() {
            return true;
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

    public static /* synthetic */ void $r8$lambda$6xjOsDUB65Zk6_tWg6lepZcnZis(int r0, TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(r0).processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$GeUrYuer4Jev4M0zkXmaQaIUu6M(TLObject r22, int r23, long r24, long r26, int r28, boolean r29, Context r30, String r31) {
        if ((r22 instanceof TL_phone.groupCall) == false) goto L7;
        TL_phone.groupCall r0 = (TL_phone.groupCall) r22;
        MessagesController.getInstance(r23).putUsers(r0.users, false);
        MessagesController.getInstance(r23).putChats(r0.chats, false);
        currentState = new State(r23, r24, r26, r28, r29, r0.call, r0.participants);
        showNotification(r30, r23, r26, r28, r24, r31);
        return;
    L7:
        if (ignoreCalls != null) goto L9;
        ignoreCalls = new HashSet();
    L9:
        ignoreCalls.add(Integer.valueOf(r28));
    }

    public static /* synthetic */ void $r8$lambda$l9oGtBJzWocEzM9fCtddt9UoyiE(int r2, long r3, long r5, int r7, boolean r8, Context r9, String r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new VoIPGroupNotification$$ExternalSyntheticLambda1(r11, r2, r3, r5, r7, r8, r9, r10));
    }

    public static /* synthetic */ void $r8$lambda$tW7okBD1WxZ70P4qwGA8VQbrC6M(Context r0, int r1, int r2) {
        decline(r0, r1, r2);
    }

    public VoIPGroupNotification() {
    }

    public static void answer(Context r4, int r5, int r6) {
        Runnable r0 = missRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        VoIPPreNotificationService.stopRinging();
        State r02 = currentState;
        if (r02 != null) goto L8;
        return;
    L8:
        if (r02.msg_id != r6) goto L13;
        TLRPC.GroupCall r03 = State.m5286$$Nest$fgetgroupCall(r02);
        boolean r1 = currentState.isCallingVideo();
        currentState = null;
        currentCallId = 0;
        ((NotificationManager) r4.getSystemService("notification")).cancel(203);
        TLRPC.TL_inputGroupCallInviteMessage r42 = new TLRPC.TL_inputGroupCallInviteMessage();
        r42.msg_id = r6;
        VoIPHelper.joinConference(LaunchActivity.instance, r5, r42, r1, r03);
        return;
    }

    public static void decline(Context r2, int r3, int r4) {
        Runnable r0 = missRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        currentState = null;
        currentCallId = 0;
        ((NotificationManager) r2.getSystemService("notification")).cancel(203);
        VoIPPreNotificationService.stopRinging();
        TL_phone.declineConferenceCallInvite r22 = new TL_phone.declineConferenceCallInvite();
        r22.msg_id = r4;
        ConnectionsManager.getInstance(r3).sendRequest(r22, new VoIPGroupNotification$$ExternalSyntheticLambda2(r3));
        if (VoIPFragment.getInstance() == null) goto L9;
        VoIPFragment.getInstance().finish();
        return;
    }

    public static void hide(Context r2) {
        State r0 = currentState;
        if (r0 != null) goto L5;
        return;
    L5:
        hide(r2, State.m5285$$Nest$fgetcurrentAccount(r0), currentState.msg_id);
    }

    public static void hide(Context r1, int r2, int r3) {
        State r0 = currentState;
        if (r0 != null) goto L5;
        return;
    L5:
        if (State.m5285$$Nest$fgetcurrentAccount(r0) == r2) goto L7;
        return;
    L7:
        if (currentState.msg_id != r3) goto L19;
        Runnable r22 = missRunnable;
        if (r22 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(r22);
    L12:
        currentState = null;
        currentCallId = 0;
        ((NotificationManager) r1.getSystemService("notification")).cancel(203);
        VoIPPreNotificationService.stopRinging();
        if (VoIPFragment.getInstance() == null) goto L18;
        VoIPFragment.getInstance().finish();
        return;
    L18:
        return;
    }

    public static void hideByCallId(Context r2, int r3, long r4) {
        State r0 = currentState;
        if (r0 != null) goto L5;
        return;
    L5:
        if (State.m5285$$Nest$fgetcurrentAccount(r0) == r3) goto L7;
        return;
    L7:
        if (currentState.call_id != r4) goto L19;
        Runnable r32 = missRunnable;
        if (r32 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(r32);
    L12:
        currentState = null;
        currentCallId = 0;
        ((NotificationManager) r2.getSystemService("notification")).cancel(203);
        VoIPPreNotificationService.stopRinging();
        if (VoIPFragment.getInstance() == null) goto L18;
        VoIPFragment.getInstance().finish();
        return;
    L18:
        return;
    }

    public static void open(Context r1, int r2, int r3) {
        Runnable r0 = missRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        VoIPPreNotificationService.stopRinging();
        State r02 = currentState;
        if (r02 != null) goto L8;
        return;
    L8:
        if (r02.msg_id != r3) goto L18;
        ((NotificationManager) r1.getSystemService("notification")).cancel(203);
        VoIPPreNotificationService.stopRinging();
        Activity r12 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
        if (r12 != null) goto L13;
        r12 = LaunchActivity.instance;
    L13:
        if (r12 == null) goto L17;
        VoIPFragment.show(r12, r2);
        return;
    L17:
        return;
    }

    public static void request(Context r12, int r13, long r14, String r16, long r17, int r19, boolean r20) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        if (currentCallId == r17) goto L28;
        State r0 = currentState;
        if (r0 == null) goto L12;
        if (r0.call_id != r17) goto L12;
        return;
    L12:
        if (VoIPService.getSharedInstance() == null) goto L18;
        if (currentState == null) goto L29;
        hide(r12);
        return;
    L29:
        return;
    L18:
        if (MessagesController.getInstance(r13).callRequestsDisabled == true) goto L31;
        HashSet<Integer> r02 = ignoreCalls;
        if (r02 != null) goto L23;
    L25:
        currentCallId = r17;
        TL_phone.getGroupCall r03 = new TL_phone.getGroupCall();
        TLRPC.TL_inputGroupCallInviteMessage r1 = new TLRPC.TL_inputGroupCallInviteMessage();
        r03.call = r1;
        r1.msg_id = r19;
        r03.limit = 3;
        ConnectionsManager.getInstance(r13).sendRequest(r03, new VoIPGroupNotification$$ExternalSyntheticLambda3(r13, r14, r17, r19, r20, r12, r16));
        return;
    L23:
        if (r02.contains(Integer.valueOf(r19)) == false) goto L25;
        return;
    L31:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v9 */
    private static void showNotification(Context r21, int r22, long r23, int r25, long r26, String r28) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        NotificationManager r8 = (NotificationManager) r21.getSystemService("notification");
        Intent r12 = new Intent(r21, LaunchActivity.class).setAction("voip").putExtra("group_call_invite_msg_id", r25).putExtra("currentAccount", r22);
        Notification.Builder r15 = new Notification.Builder(r21).setContentTitle(LocaleController.getString(R.string.VoipGroupInCallBranding)).setSmallIcon(R.drawable.call).setContentIntent(PendingIntent.getActivity(r21, 0, r12, 301989888));
        SharedPreferences r0 = MessagesController.getGlobalNotificationsSettings();
        int r6 = r0.getInt("calls_notification_channel", 0);
        NotificationChannel r7 = r8.getNotificationChannel("incoming_calls2" + r6);
        if (r7 == null) goto L8;
        r8.deleteNotificationChannel(r7.getId());
    L8:
        NotificationChannel r72 = r8.getNotificationChannel("incoming_calls3" + r6);
        if (r72 == null) goto L11;
        r8.deleteNotificationChannel(r72.getId());
    L11:
        NotificationChannel r73 = r8.getNotificationChannel("incoming_calls4" + r6);
        if (r73 != null) goto L14;
    L23:
        boolean r74 = true;
    L25:
        if (r74 == false) goto L36;
        AudioAttributes r75 = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(2).setUsage(2).build();
        CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1.m();
        Intent r18 = r12;
        NotificationChannel r122 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("incoming_calls4" + r6, LocaleController.getString(R.string.IncomingCallsSystemSetting), 4);
        r122.setSound(null, r75);     // Catch: Exception -> L29
    L31:
        r122.setDescription(LocaleController.getString(R.string.IncomingCallsSystemSettingDescription));
        r122.enableVibration(false);
        r122.enableLights(false);
        r122.setBypassDnd(true);
        r8.createNotificationChannel(r122);     // Catch: Exception -> L34
    L37:
        r15.setChannelId("incoming_calls4" + r6);
        Intent r02 = new Intent(r21, VoIPActionsReceiver.class);
        r02.setAction(r21.getPackageName() + ".DECLINE_CALL");
        r02.putExtra("call_id", r23);
        r02.putExtra("group_call_invite_msg_id", r25);
        r02.putExtra("currentAccount", r22);
        String r123 = LocaleController.getString(R.string.VoipDeclineCall);
        int r13 = Build.VERSION.SDK_INT;
        if (r13 >= 31) goto L41;
        SpannableString r14 = new SpannableString(r123);
        NotificationManager r19 = r8;
        Notification.Builder r20 = r15;
        int r152 = 0;
        r14.setSpan(new ForegroundColorSpan(-769226), 0, r14.length(), 0);
        r123 = r14;
    L42:
        PendingIntent r03 = PendingIntent.getBroadcast(r21, r152, r02, 301989888);
        Intent r82 = new Intent(r21, VoIPActionsReceiver.class);
        r82.setAction(r21.getPackageName() + ".ANSWER_CALL");
        r82.putExtra("call_id", r23);
        r82.putExtra("group_call_invite_msg_id", r25);
        r82.putExtra("currentAccount", r22);
        String r3 = LocaleController.getString(R.string.VoipAnswerCall);
        if (r13 >= 31) goto L45;
        SpannableString r4 = new SpannableString(r3);
        int r153 = 0;
        r4.setSpan(new ForegroundColorSpan(-16733696), 0, r4.length(), 0);
        r3 = r4;
    L46:
        PendingIntent r42 = PendingIntent.getActivity(r21, r153, new Intent(r21, LaunchActivity.class).setAction("voip_answer").putExtra("group_call_invite_msg_id", r25).putExtra("currentAccount", r22), 301989888);
        Notification.Builder r76 = r20;
        r76.setPriority(2);
        r76.setShowWhen(r153);
        boolean r83 = VoIPPreNotificationService.canUseFullScreenIntent(r19);
        r76.setColor(-13851168);
        r76.setVibrate(new long[r153]);
        r76.setCategory("call");
        if (r83 == false) goto L49;
        r76.setFullScreenIntent(PendingIntent.getActivity(r21, r153, r18, 33554432), true);
    L49:
        Intent r84 = new Intent(ApplicationLoader.applicationContext, VoIPActionsReceiver.class);
        r84.setAction(r21.getPackageName() + ".HIDE_CALL");
        r84.putExtra("group_call_invite_msg_id", r25);
        r84.putExtra("currentAccount", r22);
        r76.setDeleteIntent(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, r84, 167772160));
        TLObject r62 = MessagesController.getInstance(r22).getUserOrChat(r26);
        Bitmap r10 = VoIPService.getRoundAvatarBitmap(r21, r22, r62);
        if (TextUtils.isEmpty(r28) == true) goto L52;
        String r63 = r28;
    L54:
        if (TextUtils.isEmpty(r63) == false) goto L57;
        r63 = "___";
    L57:
        if (VoIPPreNotificationService.canUseCallStyle(r19) == false) goto L59;
        r76.setStyle(Notification.CallStyle.forIncomingCall(VoIPGroupNotification$$ExternalSyntheticApiModelOutline0.m().setName(r63).setIcon(Icon.createWithAdaptiveBitmap(r10)).build(), r03, r42));
    L60:
        r19.notify(203, r76.build());
        VoIPPreNotificationService.startRinging(r21, r22, r26);
        Runnable r04 = missRunnable;
        if (r04 == null) goto L63;
        AndroidUtilities.cancelRunOnUIThread(r04);
    L63:
        VoIPGroupNotification$$ExternalSyntheticLambda4 r05 = new VoIPGroupNotification$$ExternalSyntheticLambda4(r21, r22, r25);
        missRunnable = r05;
        AndroidUtilities.runOnUIThread(r05, MessagesController.getInstance(r22).callRingTimeout);
        return;
    L59:
        r76.setContentText(r63).setLargeIcon(r10).addAction(R.drawable.ic_call_end_white_24dp, r123, r03).addAction(R.drawable.ic_call, r3, r42);
        goto L60
    L52:
        r63 = ContactsController.formatName(r62);
        goto L54
    L45:
        r153 = 0;
        goto L46
    L41:
        r19 = r8;
        r20 = r15;
        r152 = 0;
    L34:
        e = move-exception;
        FileLog.e(e);
    L29:
        e = move-exception;
        FileLog.e(e);
        goto L31
    L36:
        r18 = r12;
        goto L37
    L14:
        if (r73.getImportance() < 4) goto L20;
        if (r73.getSound() != null) goto L20;
        r74 = false;
    L20:
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("User messed up the notification channel; deleting it and creating a proper one");
    L22:
        r8.deleteNotificationChannel("incoming_calls4" + r6);
        r6 = r6 + 1;
        r0.edit().putInt("calls_notification_channel", r6).commit();
        goto L23
    }
}
