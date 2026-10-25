package com.google.firebase.messaging;

import android.R;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public abstract class CommonNotificationBuilder {
    private static final AtomicInteger requestCodeProvider = null;

    public static class DisplayNotificationInfo {
        public final int id;
        public final NotificationCompat.Builder notificationBuilder;
        public final String tag;

        public DisplayNotificationInfo(NotificationCompat.Builder r1, String r2, int r3) {
            this.notificationBuilder = r1;
            this.tag = r2;
            this.id = r3;
        }
    }

    static {
        requestCodeProvider = new AtomicInteger((int) SystemClock.elapsedRealtime());
    }

    private static PendingIntent createContentIntent(Context r0, NotificationParams r1, String r2, PackageManager r3) {
        Intent r22 = createTargetIntent(r2, r1, r3);
        if (r22 != null) goto L6;
        return null;
    L6:
        r22.addFlags(67108864);
        r22.putExtras(r1.paramsWithReservedKeysRemoved());
        if (shouldUploadMetrics(r1) == false) goto L10;
        r22.putExtra("gcm.n.analytics_data", r1.paramsForAnalyticsIntent());
    L10:
        return PendingIntent.getActivity(r0, generatePendingIntentRequestCode(), r22, getPendingIntentFlags(TLObject.FLAG_30));
    }

    private static PendingIntent createDeleteIntent(Context r2, Context r3, NotificationParams r4) {
        if (shouldUploadMetrics(r4) == true) goto L7;
        return null;
    L7:
        return createMessagingPendingIntent(r2, r3, new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS").putExtras(r4.paramsForAnalyticsIntent()));
    }

    private static PendingIntent createMessagingPendingIntent(Context r3, Context r4, Intent r5) {
        return PendingIntent.getBroadcast(r3, generatePendingIntentRequestCode(), new Intent("com.google.android.c2dm.intent.RECEIVE").setPackage(r4.getPackageName()).putExtra("wrapped_intent", r5), getPendingIntentFlags(TLObject.FLAG_30));
    }

    public static DisplayNotificationInfo createNotificationInfo(Context r5, Context r6, NotificationParams r7, String r8, Bundle r9) {
        String r0 = r6.getPackageName();
        Resources r1 = r6.getResources();
        PackageManager r2 = r6.getPackageManager();
        NotificationCompat.Builder r3 = new NotificationCompat.Builder(r6, r8);
        String r82 = r7.getPossiblyLocalizedString(r1, r0, "gcm.n.title");
        if (TextUtils.isEmpty(r82) == true) goto L5;
        r3.setContentTitle(r82);
    L5:
        String r83 = r7.getPossiblyLocalizedString(r1, r0, "gcm.n.body");
        if (TextUtils.isEmpty(r83) == true) goto L8;
        r3.setContentText(r83);
        r3.setStyle(new NotificationCompat.BigTextStyle().bigText(r83));
    L8:
        r3.setSmallIcon(getSmallIcon(r2, r1, r0, r7.getString("gcm.n.icon"), r9));
        Uri r84 = getSound(r0, r7, r1);
        if (r84 == null) goto L11;
        r3.setSound(r84);
    L11:
        r3.setContentIntent(createContentIntent(r5, r7, r0, r2));
        PendingIntent r52 = createDeleteIntent(r5, r6, r7);
        if (r52 == null) goto L14;
        r3.setDeleteIntent(r52);
    L14:
        Integer r53 = getColor(r6, r7.getString("gcm.n.color"), r9);
        if (r53 == null) goto L17;
        r3.setColor(r53.intValue());
    L17:
        r3.setAutoCancel(!r7.getBoolean("gcm.n.sticky"));
        r3.setLocalOnly(r7.getBoolean("gcm.n.local_only"));
        String r54 = r7.getString("gcm.n.ticker");
        if (r54 == null) goto L20;
        r3.setTicker(r54);
    L20:
        Integer r55 = r7.getNotificationPriority();
        if (r55 == null) goto L23;
        r3.setPriority(r55.intValue());
    L23:
        Integer r56 = r7.getVisibility();
        if (r56 == null) goto L26;
        r3.setVisibility(r56.intValue());
    L26:
        Integer r57 = r7.getNotificationCount();
        if (r57 == null) goto L29;
        r3.setNumber(r57.intValue());
    L29:
        Long r58 = r7.getLong("gcm.n.event_time");
        if (r58 == null) goto L32;
        r3.setShowWhen(true);
        r3.setWhen(r58.longValue());
    L32:
        long[] r59 = r7.getVibrateTimings();
        if (r59 == null) goto L35;
        r3.setVibrate(r59);
    L35:
        int[] r510 = r7.getLightSettings();
        if (r510 == null) goto L38;
        r3.setLights(r510[0], r510[1], r510[2]);
    L38:
        r3.setDefaults(getConsolidatedDefaults(r7));
        return new DisplayNotificationInfo(r3, getTag(r7), 0);
    }

    public static DisplayNotificationInfo createNotificationInfo(Context r2, NotificationParams r3) {
        Bundle r0 = getManifestMetadata(r2.getPackageManager(), r2.getPackageName());
        return createNotificationInfo(r2, r2, r3, getOrCreateChannel(r2, r3.getNotificationChannelId(), r0), r0);
    }

    private static Intent createTargetIntent(String r2, NotificationParams r3, PackageManager r4) {
        String r0 = r3.getString("gcm.n.click_action");
        if (TextUtils.isEmpty(r0) == true) goto L6;
        Intent r32 = new Intent(r0);
        r32.setPackage(r2);
        r32.setFlags(268435456);
        return r32;
    L6:
        Uri r33 = r3.getLink();
        if (r33 == null) goto L10;
        Intent r42 = new Intent("android.intent.action.VIEW");
        r42.setPackage(r2);
        r42.setData(r33);
        return r42;
    L10:
        Intent r22 = r4.getLaunchIntentForPackage(r2);
        if (r22 != null) goto L13;
        Log.w("FirebaseMessaging", "No activity found to launch app");
    L13:
        return r22;
    }

    private static int generatePendingIntentRequestCode() {
        return requestCodeProvider.incrementAndGet();
    }

    private static Integer getColor(Context r3, String r4, Bundle r5) {
        if (TextUtils.isEmpty(r4) == false) goto L16;
    L7:
        int r42 = r5.getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (r42 == 0) goto L18;
        return Integer.valueOf(ContextCompat.getColor(r3, r42));
    L11:
        Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
        return null;
    L18:
        return null;
    L16:
        return Integer.valueOf(Color.parseColor(r4));
    L6:
        Log.w("FirebaseMessaging", "Color is invalid: " + r4 + ". Notification will use default color.");
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    private static int getConsolidatedDefaults(NotificationParams r2) {
        boolean r0 = r2.getBoolean("gcm.n.default_sound");
        ?? r02 = r0;
        if (r2.getBoolean("gcm.n.default_vibrate_timings") == false) goto L6;
        r02 = (r0 ? 1 : 0) | 2;
    L6:
        if (r2.getBoolean("gcm.n.default_light_settings") == true) goto L8;
        return r02;
    L8:
        return r02 | 4;
    }

    private static Bundle getManifestMetadata(PackageManager r1, String r2) {
        ApplicationInfo r12 = r1.getApplicationInfo(r2, 128);     // Catch: PackageManager.NameNotFoundException -> L8
        if (r12 == null) goto L11;
        Bundle r13 = r12.metaData;     // Catch: PackageManager.NameNotFoundException -> L8
        if (r13 == null) goto L11;
        return r13;
    L11:
        return Bundle.EMPTY;
    L8:
        e = move-exception;
        Log.w("FirebaseMessaging", "Couldn't get own application info: " + e);
        goto L11
    }

    @TargetApi(26)
    public static String getOrCreateChannel(Context r5, String r6, Bundle r7) {
        if (Build.VERSION.SDK_INT >= 26) goto L30;
        return null;
    L30:
        if (r5.getPackageManager().getApplicationInfo(r5.getPackageName(), 0).targetSdkVersion >= 26) goto L8;
        return null;
    L8:
        NotificationManager r0 = (NotificationManager) r5.getSystemService(NotificationManager.class);
        if (TextUtils.isEmpty(r6) == false) goto L11;
    L14:
        String r62 = r7.getString("com.google.firebase.messaging.default_notification_channel_id");
        if (TextUtils.isEmpty(r62) == false) goto L17;
        Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
    L22:
        if (r0.getNotificationChannel("fcm_fallback_notification_channel") != null) goto L28;
        int r72 = r5.getResources().getIdentifier("fcm_fallback_notification_channel_label", "string", r5.getPackageName());
        if (r72 != 0) goto L26;
        Log.e("FirebaseMessaging", "String resource \"fcm_fallback_notification_channel_label\" is not found. Using default string channel name.");
        String r52 = "Misc";
    L27:
        r0.createNotificationChannel(CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("fcm_fallback_notification_channel", r52, 3));
        goto L28
    L26:
        r52 = r5.getString(r72);
    L28:
        return "fcm_fallback_notification_channel";
    L17:
        if (r0.getNotificationChannel(r62) == null) goto L19;
        return r62;
    L19:
        Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
        goto L22
    L11:
        if (r0.getNotificationChannel(r6) == null) goto L13;
        return r6;
    L13:
        Log.w("FirebaseMessaging", "Notification Channel requested (" + r6 + ") has not been created by the app. Manifest configuration, or default, value will be used.");
    L29:
        return null;
    }

    private static int getPendingIntentFlags(int r1) {
        return r1 | 67108864;
    }

    private static int getSmallIcon(PackageManager r3, Resources r4, String r5, String r6, Bundle r7) {
        if (TextUtils.isEmpty(r6) == true) goto L15;
        int r0 = r4.getIdentifier(r6, "drawable", r5);
        if (r0 != 0) goto L7;
    L9:
        int r02 = r4.getIdentifier(r6, "mipmap", r5);
        if (r02 != 0) goto L12;
    L14:
        Log.w("FirebaseMessaging", "Icon resource " + r6 + " not found. Notification will use default icon.");
        goto L15
    L12:
        if (isValidIcon(r4, r02) == false) goto L14;
        return r02;
    L7:
        if (isValidIcon(r4, r0) == false) goto L9;
        return r0;
    L15:
        int r62 = r7.getInt("com.google.firebase.messaging.default_notification_icon", 0);
        if (r62 != 0) goto L18;
    L28:
        r62 = r3.getApplicationInfo(r5, 0).icon;     // Catch: PackageManager.NameNotFoundException -> L21
    L23:
        if (r62 != 0) goto L25;
    L26:
        return R.drawable.sym_def_app_icon;
    L25:
        if (isValidIcon(r4, r62) == false) goto L26;
        return r62;
    L21:
        e = move-exception;
        Log.w("FirebaseMessaging", "Couldn't get own application info: " + e);
        goto L23
    L18:
        if (isValidIcon(r4, r62) == true) goto L23;
        goto L23
    }

    private static Uri getSound(String r1, NotificationParams r2, Resources r3) {
        String r22 = r2.getSoundResourceName();
        if (TextUtils.isEmpty(r22) == false) goto L7;
        return null;
    L7:
        if (PluginsConstants.Settings.DEFAULT.equals(r22) == true) goto L13;
        if (r3.getIdentifier(r22, "raw", r1) == 0) goto L13;
        return Uri.parse("android.resource://" + r1 + "/raw/" + r22);
    L13:
        return RingtoneManager.getDefaultUri(2);
    }

    private static String getTag(NotificationParams r2) {
        String r22 = r2.getString("gcm.n.tag");
        if (TextUtils.isEmpty(r22) == true) goto L6;
        return r22;
    L6:
        return "FCM-Notification:" + SystemClock.uptimeMillis();
    }

    @TargetApi(26)
    private static boolean isValidIcon(Resources r5, int r6) {
        if (Build.VERSION.SDK_INT == 26) goto L13;
        return true;
    L13:
    L11:
        Log.e("FirebaseMessaging", "Couldn't find resource " + r6 + ", treating it as an invalid icon");
        return false;
    L7:
        if (CommonNotificationBuilder$$ExternalSyntheticApiModelOutline0.m(r5.getDrawable(r6, null)) == false) goto L10;
        Log.e("FirebaseMessaging", "Adaptive icons cannot be used in notifications. Ignoring icon id: " + r6);     // Catch: Resources.NotFoundException -> L11
        return false;
    L10:
        return true;
    }

    public static boolean shouldUploadMetrics(NotificationParams r1) {
        return r1.getBoolean("google.c.a.e");
    }
}
