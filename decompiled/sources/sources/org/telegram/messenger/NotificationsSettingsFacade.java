package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.NotificationsSoundActivity;

/* loaded from: classes.dex */
public class NotificationsSettingsFacade {
    public static final String PROPERTY_CONTENT_PREVIEW = "content_preview_";
    public static final String PROPERTY_CUSTOM = "custom_";
    public static final String PROPERTY_NOTIFY = "notify2_";
    public static final String PROPERTY_NOTIFY_UNTIL = "notifyuntil_";
    public static final String PROPERTY_SILENT = "silent_";
    public static final String PROPERTY_STORIES_NOTIFY = "stories_";
    private final int currentAccount;

    public static /* synthetic */ void $r8$lambda$R4bAn5d8TVQDgcXEupQCkLvhJDc(NotificationsSettingsFacade r0) {
        r0.lambda$applyDialogNotificationsSettings$0();
    }

    public static /* synthetic */ void $r8$lambda$UePc83DmoUyR7zP1YZStPW2HTxQ(NotificationsSettingsFacade r0, long r1, long r3, TLRPC.PeerNotifySettings r5) {
        r0.lambda$applyDialogNotificationsSettings$1(r1, r3, r5);
    }

    public NotificationsSettingsFacade(int r1) {
        this.currentAccount = r1;
    }

    private SharedPreferences getPreferences() {
        return MessagesController.getNotificationsSettings(this.currentAccount);
    }

    private /* synthetic */ void lambda$applyDialogNotificationsSettings$0() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$applyDialogNotificationsSettings$1(long r19, long r21, TLRPC.PeerNotifySettings r23) {
        String r7 = NotificationsController.getSharedPrefKey(r19, r21, true);
        MessagesController r8 = MessagesController.getInstance(this.currentAccount);
        ConnectionsManager r9 = ConnectionsManager.getInstance(this.currentAccount);
        MessagesStorage r10 = MessagesStorage.getInstance(this.currentAccount);
        NotificationsController r11 = NotificationsController.getInstance(this.currentAccount);
        int r12 = getPreferences().getInt(PROPERTY_NOTIFY + r7, -1);
        int r13 = getPreferences().getInt(PROPERTY_NOTIFY_UNTIL + r7, 0);
        SharedPreferences.Editor r15 = getPreferences().edit();
        if ((r23.flags & 2) == 0) goto L5;
        r15.putBoolean(PROPERTY_SILENT + r7, r23.silent);
    L7:
        if ((r23.flags & 64) == 0) goto L9;
        r15.putBoolean(PROPERTY_STORIES_NOTIFY + r7, !r23.stories_muted);
    L11:
        if (r21 != 0) goto L13;
        TLRPC.Dialog r82 = r8.dialogs_dict.get(r19);
    L14:
        if (r82 == null) goto L17;
        r82.notify_settings = r23;
    L17:
        if ((r23.flags & 4) != 0) goto L19;
        boolean r2 = true;
        if (r12 == (-1)) goto L59;
        if (r82 == null) goto L58;
        r82.notify_settings.mute_until = 0;
    L58:
        r15.remove(PROPERTY_NOTIFY + r7);
    L60:
        if (r21 == 0) goto L61;
    L43:
        boolean r92 = r2;
        applySoundSettings(r23.android_sound, r15, r19, r21, 0, false);
        r15.apply();
        if (r92 == false) goto L66;
        AndroidUtilities.runOnUIThread(new NotificationsSettingsFacade$$ExternalSyntheticLambda1(this));
        return;
    L66:
        return;
    L61:
        r10.setDialogFlags(r19, 0);
        goto L43
    L59:
        r2 = false;
        goto L60
    L19:
        if (r23.mute_until > r9.getCurrentTime()) goto L21;
        if (r12 == 0) goto L51;
        r2 = true;
        if (r12 == 1) goto L51;
        if (r82 == null) goto L49;
        int r6 = 0;
        r82.notify_settings.mute_until = 0;
    L50:
        r15.putInt(PROPERTY_NOTIFY + r7, r6);
    L52:
        if (r21 != 0) goto L43;
        r10.setDialogFlags(r19, 0);
        goto L43
    L49:
        r6 = 0;
    L51:
        r2 = false;
        goto L52
    L21:
        if (r23.mute_until <= (r9.getCurrentTime() + 31536000)) goto L31;
        if (r12 == 2) goto L29;
        r15.putInt(PROPERTY_NOTIFY + r7, 2);
        if (r82 == null) goto L27;
        r82.notify_settings.mute_until = Integer.MAX_VALUE;
    L27:
        r2 = true;
    L28:
        int r5 = 0;
    L41:
        if (r21 != 0) goto L43;
        r10.setDialogFlags(r19, (r5 << 32) | 1);
        r11.removeNotificationsForDialog(r19);
        goto L43
    L29:
        r2 = false;
        goto L28
    L31:
        if (r12 == 3) goto L33;
    L36:
        r15.putInt(PROPERTY_NOTIFY + r7, 3);
        r15.putInt(PROPERTY_NOTIFY_UNTIL + r7, r23.mute_until);
        if (r82 == null) goto L39;
        r82.notify_settings.mute_until = 0;
    L39:
        r2 = true;
    L40:
        r5 = r23.mute_until;
        goto L41
    L33:
        if (r13 != r23.mute_until) goto L36;
        r2 = false;
        goto L40
    L13:
        r82 = null;
        goto L14
    L9:
        r15.remove(PROPERTY_STORIES_NOTIFY + r7);
        goto L11
    L5:
        r15.remove(PROPERTY_SILENT + r7);
        goto L7
    }

    public void applyDialogNotificationsSettings(long r9, long r11, TLRPC.PeerNotifySettings r13) {
        if (r13 != null) goto L4;
        return;
    L4:
        Utilities.globalQueue.postRunnable(new NotificationsSettingsFacade$$ExternalSyntheticLambda0(this, r9, r11, r13));
    }

    public void applySoundSettings(TLRPC.NotificationSound r7, SharedPreferences.Editor r8, long r9, long r11, int r13, boolean r14) {
        if (r7 != null) goto L5;
        return;
    L5:
        if (r9 == 0) goto L7;
        String r112 = NotificationsController.getSharedPrefKey(r9, r11, true);
        String r12 = "sound_" + r112;
        String r132 = "sound_path_" + r112;
        String r113 = "sound_document_id_" + r112;
    L22:
        if ((r7 instanceof TLRPC.TL_notificationSoundLocal) == false) goto L34;
        TLRPC.TL_notificationSoundLocal r2 = (TLRPC.TL_notificationSoundLocal) r7;
        if ("Default".equalsIgnoreCase(r2.data) == false) goto L27;
        r7 = new TLRPC.TL_notificationSoundDefault();
        goto L34
    L27:
        if ("NoSound".equalsIgnoreCase(r2.data) == false) goto L29;
        r7 = new TLRPC.TL_notificationSoundNone();
        goto L34
    L29:
        String r5 = NotificationsSoundActivity.findRingtonePathByName(r2.title);
        if (r5 == null) goto L55;
        r2.data = r5;
        goto L34
    L55:
        return;
    L34:
        if ((r7 instanceof TLRPC.TL_notificationSoundDefault) == false) goto L38;
        r8.putString(r12, "Default");
        r8.putString(r132, "Default");
        r8.remove(r113);
        return;
    L38:
        if ((r7 instanceof TLRPC.TL_notificationSoundNone) == false) goto L42;
        r8.putString(r12, "NoSound");
        r8.putString(r132, "NoSound");
        r8.remove(r113);
        return;
    L42:
        if ((r7 instanceof TLRPC.TL_notificationSoundLocal) == false) goto L46;
        TLRPC.TL_notificationSoundLocal r72 = (TLRPC.TL_notificationSoundLocal) r7;
        r8.putString(r12, r72.title);
        r8.putString(r132, r72.data);
        r8.remove(r113);
        return;
    L46:
        if ((r7 instanceof TLRPC.TL_notificationSoundRingtone) == false) goto L53;
        TLRPC.TL_notificationSoundRingtone r73 = (TLRPC.TL_notificationSoundRingtone) r7;
        r8.putLong(r113, r73.id);
        MediaDataController.getInstance(this.currentAccount).checkRingtones(true);
        if (r14 == false) goto L51;
        if (r9 == 0) goto L51;
        r8.putBoolean(PROPERTY_CUSTOM + r9, true);
    L51:
        MediaDataController.getInstance(this.currentAccount).ringtoneDataStore.getDocument(r73.id);
        return;
    L53:
        return;
    L7:
        if (r13 != 0) goto L10;
        r12 = "GroupSound";
        r113 = "GroupSoundDocId";
        r132 = "GroupSoundPath";
        goto L22
    L10:
        if (r13 != 3) goto L12;
        r12 = "StoriesSound";
        r113 = "StoriesSoundDocId";
        r132 = "StoriesSoundPath";
        goto L22
    L12:
        if (r13 != 1) goto L15;
        r12 = "GlobalSound";
        r113 = "GlobalSoundDocId";
        r132 = "GlobalSoundPath";
        goto L22
    L15:
        if (r13 != 4) goto L17;
    L20:
        r12 = "ReactionSound";
        r113 = "ReactionSoundDocId";
        r132 = "ReactionSoundPath";
        goto L22
    L17:
        if (r13 == 5) goto L20;
        r12 = "ChannelSound";
        r113 = "ChannelSoundDocId";
        r132 = "ChannelSoundPath";
        goto L22
    }

    public void clearPreference(long r2, long r4) {
        String r22 = NotificationsController.getSharedPrefKey(r2, r4, true);
        getPreferences().edit().remove(PROPERTY_NOTIFY + r22).remove(PROPERTY_CUSTOM + r22).remove(PROPERTY_NOTIFY_UNTIL + r22).remove(PROPERTY_CONTENT_PREVIEW + r22).remove(PROPERTY_SILENT + r22).remove(PROPERTY_STORIES_NOTIFY + r22).apply();
    }

    public int getProperty(String r3, long r4, long r6, int r8) {
        String r62 = NotificationsController.getSharedPrefKey(r4, r6, true);
        if (getPreferences().contains(r3 + r62) == true) goto L5;
        String r42 = NotificationsController.getSharedPrefKey(r4, 0, true);
        return getPreferences().getInt(r3 + r42, r8);
    L5:
        return getPreferences().getInt(r3 + r62, r8);
    }

    public long getProperty(String r3, long r4, long r6, long r8) {
        String r62 = NotificationsController.getSharedPrefKey(r4, r6, true);
        if (getPreferences().contains(r3 + r62) == true) goto L5;
        String r42 = NotificationsController.getSharedPrefKey(r4, 0, true);
        return getPreferences().getLong(r3 + r42, r8);
    L5:
        return getPreferences().getLong(r3 + r62, r8);
    }

    public boolean getProperty(String r2, long r3, long r5, boolean r7) {
        String r52 = NotificationsController.getSharedPrefKey(r3, r5);
        if (getPreferences().contains(r2 + r52) == true) goto L5;
        String r32 = NotificationsController.getSharedPrefKey(r3, 0);
        return getPreferences().getBoolean(r2 + r32, r7);
    L5:
        return getPreferences().getBoolean(r2 + r52, r7);
    }

    public String getPropertyString(String r2, long r3, long r5, String r7) {
        String r52 = NotificationsController.getSharedPrefKey(r3, r5);
        if (getPreferences().contains(r2 + r52) == true) goto L5;
        String r32 = NotificationsController.getSharedPrefKey(r3, 0);
        return getPreferences().getString(r2 + r32, r7);
    L5:
        return getPreferences().getString(r2 + r52, r7);
    }

    public boolean isDefault(long r1, long r3) {
        NotificationsController.getSharedPrefKey(r1, r3, true);
        return false;
    }

    public void removeProperty(String r1, long r2, long r4) {
        String r22 = NotificationsController.getSharedPrefKey(r2, r4);
        getPreferences().edit().remove(r1 + r22).apply();
    }

    public void setSettingsForDialog(SharedPreferences.Editor r6, TLRPC.Dialog r7, TLRPC.PeerNotifySettings r8) {
        long r0 = MessageObject.getPeerId(r7.peer);
        if ((r7.notify_settings.flags & 2) == 0) goto L5;
        r6.putBoolean(PROPERTY_SILENT + r0, r7.notify_settings.silent);
    L6:
        ConnectionsManager r5 = ConnectionsManager.getInstance(this.currentAccount);
        TLRPC.PeerNotifySettings r82 = r7.notify_settings;
        if ((r82.flags & 4) != 0) goto L9;
        r6.remove(PROPERTY_NOTIFY + r0);
        return;
    L9:
        if (r82.mute_until > r5.getCurrentTime()) goto L11;
        r6.putInt(PROPERTY_NOTIFY + r0, 0);
        return;
    L11:
        if (r7.notify_settings.mute_until <= (r5.getCurrentTime() + 31536000)) goto L14;
        r6.putInt(PROPERTY_NOTIFY + r0, 2);
        r7.notify_settings.mute_until = Integer.MAX_VALUE;
        return;
    L14:
        r6.putInt(PROPERTY_NOTIFY + r0, 3);
        r6.putInt(PROPERTY_NOTIFY_UNTIL + r0, r7.notify_settings.mute_until);
        return;
    L5:
        r6.remove(PROPERTY_SILENT + r0);
        goto L6
    }
}
