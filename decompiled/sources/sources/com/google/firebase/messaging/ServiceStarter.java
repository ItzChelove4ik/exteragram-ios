package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
public class ServiceStarter {
    private static ServiceStarter instance;
    private String firebaseMessagingServiceClassName;
    private Boolean hasAccessNetworkStatePermission;
    private Boolean hasWakeLockPermission;
    private final Queue<Intent> messagingEvents;

    private ServiceStarter() {
        this.firebaseMessagingServiceClassName = null;
        this.hasWakeLockPermission = null;
        this.hasAccessNetworkStatePermission = null;
        this.messagingEvents = new ArrayDeque();
    }

    private int doStartService(Context r4, Intent r5) {
        String r0 = resolveServiceClassName(r4, r5);
        if (r0 != null) goto L5;
    L26:
    L11:
        e = move-exception;
        Log.e("FirebaseMessaging", "Failed to start service while in background: " + e);
        return 402;
    L13:
        e = move-exception;
        Log.e("FirebaseMessaging", "Error while delivering the message to the serviceIntent", e);
        return 401;
    L9:
        if (hasWakeLockPermission(r4) == false) goto L15;
        ComponentName r3 = WakeLockHolder.startWakefulService(r4, r5);     // Catch: IllegalStateException -> L11 SecurityException -> L13
    L16:
        if (r3 != null) goto L20;
        Log.e("FirebaseMessaging", "Error while delivering the message: ServiceIntent not found.");     // Catch: IllegalStateException -> L11 SecurityException -> L13
        return 404;
    L20:
        return -1;
    L15:
        r3 = r4.startService(r5);     // Catch: IllegalStateException -> L11 SecurityException -> L13
        Log.d("FirebaseMessaging", "Missing wake lock permission, service start may be delayed");     // Catch: IllegalStateException -> L11 SecurityException -> L13
        goto L16
    L5:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L7;
        Log.d("FirebaseMessaging", "Restricting intent to a specific service: ".concat(r0));
    L7:
        r5.setClassName(r4.getPackageName(), r0);
        goto L26
    }

    public static synchronized ServiceStarter getInstance() {
        monitor-enter(ServiceStarter.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (instance != null) goto L9;
        instance = new ServiceStarter();     // Catch: Throwable -> L7
    L9:
        ServiceStarter r1 = instance;     // Catch: Throwable -> L7
        monitor-exit(ServiceStarter.class);
        return r1;
    }

    private synchronized String resolveServiceClassName(Context r5, Intent r6) {
        monitor-enter(this);
        String r1 = this.firebaseMessagingServiceClassName;     // Catch: Throwable -> L21
        if (r1 == null) goto L8;
        monitor-exit(this);
        return r1;
    L8:
        ResolveInfo r62 = r5.getPackageManager().resolveService(r6, 0);     // Catch: Throwable -> L21
        if (r62 == null) goto L30;
        ServiceInfo r63 = r62.serviceInfo;     // Catch: Throwable -> L21
        if (r63 == null) goto L30;
        if (r5.getPackageName().equals(r63.packageName) == false) goto L27;
        String r2 = r63.name;     // Catch: Throwable -> L21
        if (r2 == null) goto L27;
        if (r2.startsWith(".") == false) goto L23;
        this.firebaseMessagingServiceClassName = r5.getPackageName() + r63.name;     // Catch: Throwable -> L21
    L24:
        String r52 = this.firebaseMessagingServiceClassName;     // Catch: Throwable -> L21
        monitor-exit(this);
        return r52;
    L23:
        this.firebaseMessagingServiceClassName = r63.name;     // Catch: Throwable -> L21
    L27:
        Log.e("FirebaseMessaging", "Error resolving target intent service, skipping classname enforcement. Resolved service was: " + r63.packageName + "/" + r63.name);     // Catch: Throwable -> L21
        monitor-exit(this);
        return null;
    L30:
        Log.e("FirebaseMessaging", "Failed to resolve target intent service, skipping classname enforcement");     // Catch: Throwable -> L21
        monitor-exit(this);
        return null;
    L21:
        th = move-exception;
        throw th;
    }

    public Intent getMessagingEvent() {
        return this.messagingEvents.poll();
    }

    public boolean hasAccessNetworkStatePermission(Context r2) {
        if (this.hasAccessNetworkStatePermission != null) goto L10;
        if (r2.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) goto L7;
        boolean r22 = true;
    L8:
        this.hasAccessNetworkStatePermission = Boolean.valueOf(r22);
        goto L10
    L7:
        r22 = false;
    L10:
        if (this.hasWakeLockPermission.booleanValue() == true) goto L15;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L15;
        Log.d("FirebaseMessaging", "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
    L15:
        return this.hasAccessNetworkStatePermission.booleanValue();
    }

    public boolean hasWakeLockPermission(Context r2) {
        if (this.hasWakeLockPermission != null) goto L10;
        if (r2.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0) goto L7;
        boolean r22 = true;
    L8:
        this.hasWakeLockPermission = Boolean.valueOf(r22);
        goto L10
    L7:
        r22 = false;
    L10:
        if (this.hasWakeLockPermission.booleanValue() == true) goto L15;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L15;
        Log.d("FirebaseMessaging", "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
    L15:
        return this.hasWakeLockPermission.booleanValue();
    }

    public int startMessagingService(Context r3, Intent r4) {
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L5;
        Log.d("FirebaseMessaging", "Starting service");
    L5:
        this.messagingEvents.offer(r4);
        Intent r42 = new Intent("com.google.firebase.MESSAGING_EVENT");
        r42.setPackage(r3.getPackageName());
        return doStartService(r3, r42);
    }
}
