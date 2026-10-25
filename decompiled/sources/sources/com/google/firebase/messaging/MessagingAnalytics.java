package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.ProductData;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class MessagingAnalytics {
    public static boolean deliveryMetricsExportToBigQueryEnabled() {
        FirebaseApp.getInstance();     // Catch: IllegalStateException -> L19
        Context r2 = FirebaseApp.getInstance().getApplicationContext();
        SharedPreferences r3 = r2.getSharedPreferences("com.google.firebase.messaging", 0);
        if (r3.contains("export_to_big_query") == true) goto L7;
        PackageManager r32 = r2.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L21
        if (r32 == null) goto L18;
        ApplicationInfo r22 = r32.getApplicationInfo(r2.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L21
        if (r22 == null) goto L18;
        Bundle r33 = r22.metaData;     // Catch: PackageManager.NameNotFoundException -> L21
        if (r33 == null) goto L18;
        if (r33.containsKey("delivery_metrics_exported_to_big_query_enabled") == false) goto L18;
        return r22.metaData.getBoolean("delivery_metrics_exported_to_big_query_enabled", false);
    L18:
        return false;
    L7:
        return r3.getBoolean("export_to_big_query", false);
    L19:
        Log.i("FirebaseMessaging", "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
        return false;
    }

    public static MessagingClientEvent eventToProto(MessagingClientEvent.Event r4, Intent r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        Bundle r52 = r5.getExtras();
        if (r52 != null) goto L8;
        r52 = Bundle.EMPTY;
    L8:
        MessagingClientEvent.Builder r42 = MessagingClientEvent.newBuilder().setTtl(getTtl(r52)).setEvent(r4).setInstanceId(getInstanceId(r52)).setPackageName(getPackageName()).setSdkPlatform(MessagingClientEvent.SDKPlatform.ANDROID).setMessageType(getMessageTypeForFirelog(r52)).setPriority(getMessagePriorityForFirelog(r52));
        String r0 = getMessageId(r52);
        if (r0 == null) goto L11;
        r42.setMessageId(r0);
    L11:
        String r02 = getTopic(r52);
        if (r02 == null) goto L14;
        r42.setTopic(r02);
    L14:
        String r03 = getCollapseKey(r52);
        if (r03 == null) goto L17;
        r42.setCollapseKey(r03);
    L17:
        String r04 = getMessageLabel(r52);
        if (r04 == null) goto L20;
        r42.setAnalyticsLabel(r04);
    L20:
        String r05 = getComposerLabel(r52);
        if (r05 == null) goto L23;
        r42.setComposerLabel(r05);
    L23:
        long r06 = getProjectNumber(r52);
        if (r06 <= 0) goto L27;
        r42.setProjectNumber(r06);
    L27:
        return r42.build();
    }

    public static String getCollapseKey(Bundle r1) {
        return r1.getString("collapse_key");
    }

    public static String getComposerId(Bundle r1) {
        return r1.getString("google.c.a.c_id");
    }

    public static String getComposerLabel(Bundle r1) {
        return r1.getString("google.c.a.c_l");
    }

    public static String getInstanceId(Bundle r1) {
        String r12 = r1.getString("google.to");
        if (TextUtils.isEmpty(r12) == true) goto L10;
        return r12;
    L10:
        return (String) Tasks.await(FirebaseInstallations.getInstance(FirebaseApp.getInstance()).getId());
    L7:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static String getMessageChannel(Bundle r1) {
        return r1.getString("google.c.a.m_c");
    }

    public static String getMessageId(Bundle r1) {
        String r0 = r1.getString("google.message_id");
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1.getString("message_id");
    }

    public static String getMessageLabel(Bundle r1) {
        return r1.getString("google.c.a.m_l");
    }

    private static int getMessagePriority(String r1) {
        if ("high".equals(r1) == false) goto L7;
        return 1;
    L7:
        if ("normal".equals(r1) == false) goto L10;
        return 2;
    L10:
        return 0;
    }

    public static int getMessagePriorityForFirelog(Bundle r1) {
        int r12 = getPriority(r1);
        if (r12 != 2) goto L7;
        return 5;
    L7:
        if (r12 != 1) goto L10;
        return 10;
    L10:
        return 0;
    }

    public static String getMessageTime(Bundle r1) {
        return r1.getString("google.c.a.ts");
    }

    public static MessagingClientEvent.MessageType getMessageTypeForFirelog(Bundle r0) {
        if (r0 == null) goto L8;
        if (NotificationParams.isNotification(r0) == false) goto L8;
        return MessagingClientEvent.MessageType.DISPLAY_NOTIFICATION;
    L8:
        return MessagingClientEvent.MessageType.DATA_MESSAGE;
    }

    public static String getMessageTypeForScion(Bundle r0) {
        if (r0 != null) goto L4;
        return "data";
    L4:
        if (NotificationParams.isNotification(r0) == false) goto L9;
        return "display";
    L9:
        return "data";
    }

    public static String getPackageName() {
        return FirebaseApp.getInstance().getApplicationContext().getPackageName();
    }

    public static int getPriority(Bundle r2) {
        String r0 = r2.getString("google.delivered_priority");
        if (r0 != null) goto L10;
        if ("1".equals(r2.getString("google.priority_reduced")) == false) goto L8;
        return 2;
    L8:
        r0 = r2.getString("google.priority");
    L10:
        return getMessagePriority(r0);
    }

    public static long getProjectNumber(Bundle r6) {
        if (r6.containsKey("google.c.sender.id") == true) goto L31;
    L8:
        FirebaseApp r62 = FirebaseApp.getInstance();
        String r0 = r62.getOptions().getGcmSenderId();
        if (r0 != null) goto L33;
    L14:
        String r63 = r62.getOptions().getApplicationId();
        if (r63.startsWith("1:") == false) goto L37;
        String[] r64 = r63.split(":");
        if (r64.length >= 2) goto L23;
        return 0;
    L23:
        String r65 = r64[1];
        if (r65.isEmpty() == false) goto L35;
        return 0;
    L35:
        return Long.parseLong(r65);
    L28:
        e = move-exception;
        Log.w("FirebaseMessaging", "error parsing app ID", e);
    L30:
        return 0;
    L37:
        return Long.parseLong(r63);
    L18:
        e = move-exception;
        Log.w("FirebaseMessaging", "error parsing app ID", e);
        goto L30
    L33:
        return Long.parseLong(r0);
    L12:
        e = move-exception;
        Log.w("FirebaseMessaging", "error parsing sender ID", e);
        goto L14
    L31:
        return Long.parseLong(r6.getString("google.c.sender.id"));
    L6:
        e = move-exception;
        Log.w("FirebaseMessaging", "error parsing project number", e);
        goto L8
    }

    public static String getTopic(Bundle r1) {
        String r12 = r1.getString("from");
        if (r12 != null) goto L5;
        return null;
    L5:
        if (r12.startsWith("/topics/") == false) goto L9;
        return r12;
    L9:
        return null;
    }

    public static int getTtl(Bundle r2) {
        Object r22 = r2.get("google.ttl");
        if ((r22 instanceof Integer) == false) goto L7;
        return ((Integer) r22).intValue();
    L7:
        if ((r22 instanceof String) == false) goto L15;
        return Integer.parseInt((String) r22);
    L10:
        Log.w("FirebaseMessaging", "Invalid TTL: " + r22);
        return 0;
    L15:
        return 0;
    }

    public static String getUseDeviceTime(Bundle r2) {
        if (r2.containsKey("google.c.a.udt") == true) goto L5;
        return null;
    L5:
        return r2.getString("google.c.a.udt");
    }

    private static boolean isDirectBootMessage(Intent r1) {
        return FirebaseMessagingService.ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(r1.getAction());
    }

    public static void logNotificationDismiss(Intent r1) {
        logToScion("_nd", r1.getExtras());
    }

    public static void logNotificationForeground(Intent r1) {
        logToScion("_nf", r1.getExtras());
    }

    public static void logNotificationOpen(Bundle r1) {
        setUserPropertyIfRequired(r1);
        logToScion("_no", r1);
    }

    public static void logNotificationReceived(Intent r2) {
        if (shouldUploadScionMetrics(r2) == false) goto L6;
        logToScion("_nr", r2.getExtras());
    L6:
        if (shouldUploadFirelogAnalytics(r2) == false) goto L9;
        logToFirelog(MessagingClientEvent.Event.MESSAGE_DELIVERED, r2, FirebaseMessaging.getTransportFactory());
        return;
    }

    private static void logToFirelog(MessagingClientEvent.Event r5, Intent r6, TransportFactory r7) {
        if (r7 != null) goto L6;
        Log.e("FirebaseMessaging", "TransportFactory is null. Skip exporting message delivery metrics to Big Query");
        return;
    L6:
        MessagingClientEvent r52 = eventToProto(r5, r6);
        if (r52 != null) goto L14;
        return;
    L14:
        ProductData r62 = ProductData.withProductId(Integer.valueOf(r6.getIntExtra("google.product_id", 111881503)));     // Catch: RuntimeException -> L11
        r7.getTransport("FCM_CLIENT_EVENT_LOGGING", MessagingClientEventExtension.class, Encoding.of("proto"), new MessagingAnalytics$$ExternalSyntheticLambda0()).send(Event.ofData(MessagingClientEventExtension.newBuilder().setMessagingClientEvent(r52).build(), r62));     // Catch: RuntimeException -> L11
        return;
    L11:
        e = move-exception;
        Log.w("FirebaseMessaging", "Failed to send big query analytics payload.", e);
    }

    public static void logToScion(String r4, Bundle r5) {
        FirebaseApp.getInstance();     // Catch: IllegalStateException -> L46
        if (r5 != null) goto L6;
        r5 = new Bundle();
    L6:
        Bundle r1 = new Bundle();
        String r2 = getComposerId(r5);
        if (r2 == null) goto L9;
        r1.putString("_nmid", r2);
    L9:
        String r22 = getComposerLabel(r5);
        if (r22 == null) goto L12;
        r1.putString("_nmn", r22);
    L12:
        String r23 = getMessageLabel(r5);
        if (TextUtils.isEmpty(r23) == true) goto L15;
        r1.putString("label", r23);
    L15:
        String r24 = getMessageChannel(r5);
        if (TextUtils.isEmpty(r24) == true) goto L18;
        r1.putString("message_channel", r24);
    L18:
        String r25 = getTopic(r5);
        if (r25 == null) goto L21;
        r1.putString("_nt", r25);
    L21:
        String r26 = getMessageTime(r5);
        if (r26 != null) goto L52;
    L27:
        String r27 = getUseDeviceTime(r5);
        if (r27 != null) goto L48;
    L33:
        String r52 = getMessageTypeForScion(r5);
        if ("_nr".equals(r4) == false) goto L36;
    L37:
        r1.putString("_nmc", r52);
    L39:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L41;
        Log.d("FirebaseMessaging", "Logging to scion event=" + r4 + " scionPayload=" + r1);
    L41:
        AnalyticsConnector r53 = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (r53 == null) goto L44;
        r53.logEvent("fcm", r4, r1);
        return;
    L44:
        Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        return;
    L36:
        if ("_nf".equals(r4) == false) goto L39;
    L48:
        r1.putInt("_ndt", Integer.parseInt(r27));     // Catch: NumberFormatException -> L31
    L31:
        e = move-exception;
        Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", e);
        goto L33
    L52:
        r1.putInt("_nmt", Integer.parseInt(r26));     // Catch: NumberFormatException -> L25
    L25:
        e = move-exception;
        Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", e);
    L46:
        Log.e("FirebaseMessaging", "Default FirebaseApp has not been initialized. Skip logging event to GA.");
    }

    private static void setUserPropertyIfRequired(Bundle r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if ("1".equals(r5.getString("google.c.a.tc")) == false) goto L15;
        AnalyticsConnector r0 = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L9;
        Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
    L9:
        if (r0 == null) goto L12;
        String r52 = r5.getString("google.c.a.c_id");
        r0.setUserProperty("fcm", "_ln", r52);
        Bundle r1 = new Bundle();
        r1.putString("source", "Firebase");
        r1.putString("medium", "notification");
        r1.putString("campaign", r52);
        r0.logEvent("fcm", "_cmp", r1);
        return;
    L12:
        Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
        return;
    L15:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L18;
        Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
        return;
    }

    public static boolean shouldUploadFirelogAnalytics(Intent r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (isDirectBootMessage(r0) == false) goto L7;
        return false;
    L7:
        return deliveryMetricsExportToBigQueryEnabled();
    }

    public static boolean shouldUploadScionMetrics(Intent r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (isDirectBootMessage(r1) == false) goto L7;
        return false;
    L7:
        return shouldUploadScionMetrics(r1.getExtras());
    }

    public static boolean shouldUploadScionMetrics(Bundle r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        return "1".equals(r1.getString("google.c.a.e"));
    }
}
