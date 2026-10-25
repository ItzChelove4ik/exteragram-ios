package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    private static Set<String> sEnabledNotificationListenerPackages;
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock = null;
    private static final Object sLock = null;
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;

    /* loaded from: classes3.dex */
    public static class Api24Impl {
        public static boolean areNotificationsEnabled(NotificationManager r0) {
            return r0.areNotificationsEnabled();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api26Impl {
        public static void createNotificationChannel(NotificationManager r0, NotificationChannel r1) {
            r0.createNotificationChannel(r1);
        }
    }

    /* loaded from: classes3.dex */
    public static class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        public NotifyTask(String r1, int r2, String r3, Notification r4) {
            this.packageName = r1;
            this.id = r2;
            this.tag = r3;
            this.notif = r4;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel r4) {
            r4.notify(this.packageName, this.id, this.tag, this.notif);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + "]";
        }
    }

    /* loaded from: classes3.dex */
    public static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName r1, IBinder r2) {
            this.componentName = r1;
            this.iBinder = r2;
        }
    }

    /* loaded from: classes3.dex */
    public static class SideChannelManager implements Handler.Callback, ServiceConnection {
        private Set<String> mCachedEnabledPackages;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map<ComponentName, ListenerRecord> mRecordMap;

        public static class ListenerRecord {
            boolean bound;
            final ComponentName componentName;
            int retryCount;
            INotificationSideChannel service;
            ArrayDeque<Task> taskQueue;

            public ListenerRecord(ComponentName r3) {
                this.bound = false;
                this.taskQueue = new ArrayDeque();
                this.retryCount = 0;
                this.componentName = r3;
            }
        }

        public SideChannelManager(Context r2) {
            this.mRecordMap = new HashMap();
            this.mCachedEnabledPackages = new HashSet();
            this.mContext = r2;
            HandlerThread r22 = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = r22;
            r22.start();
            this.mHandler = new Handler(r22.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord r4) {
            if (r4.bound == false) goto L6;
            return true;
        L6:
            boolean r0 = this.mContext.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(r4.componentName), this, 33);
            r4.bound = r0;
            if (r0 == false) goto L9;
            r4.retryCount = 0;
        L11:
            return r4.bound;
        L9:
            Log.w("NotifManCompat", "Unable to bind to listener " + r4.componentName);
            this.mContext.unbindService(this);
            goto L11
        }

        private void ensureServiceUnbound(ListenerRecord r2) {
            if (r2.bound == false) goto L5;
            this.mContext.unbindService(this);
            r2.bound = false;
        L5:
            r2.service = null;
        }

        private void handleQueueTask(Task r4) {
            updateListenerMap();
            Iterator<ListenerRecord> r0 = this.mRecordMap.values().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            ListenerRecord r1 = r0.next();
            r1.taskQueue.add(r4);
            processListenerQueue(r1);
            goto L4
        }

        private void handleRetryListenerQueue(ComponentName r2) {
            ListenerRecord r22 = this.mRecordMap.get(r2);
            if (r22 == null) goto L6;
            processListenerQueue(r22);
            return;
        }

        private void handleServiceConnected(ComponentName r2, IBinder r3) {
            ListenerRecord r22 = this.mRecordMap.get(r2);
            if (r22 == null) goto L6;
            r22.service = INotificationSideChannel.Stub.asInterface(r3);
            r22.retryCount = 0;
            processListenerQueue(r22);
            return;
        }

        private void handleServiceDisconnected(ComponentName r2) {
            ListenerRecord r22 = this.mRecordMap.get(r2);
            if (r22 == null) goto L6;
            ensureServiceUnbound(r22);
            return;
        }

        private void processListenerQueue(ListenerRecord r6) {
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L6;
            Log.d("NotifManCompat", "Processing component " + r6.componentName + ", " + r6.taskQueue.size() + " queued tasks");
        L6:
            if (r6.taskQueue.isEmpty() == false) goto L9;
            return;
        L9:
            if (ensureServiceBound(r6) == true) goto L11;
        L31:
            scheduleListenerRetry(r6);
            return;
        L11:
            if (r6.service == null) goto L31;
        L13:
            Task r2 = r6.taskQueue.peek();
            if (r2 == null) goto L28;
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L21;
            Log.d("NotifManCompat", "Sending task " + r2);     // Catch: RemoteException -> L19 DeadObjectException -> L24
        L21:
            r2.send(r6.service);     // Catch: RemoteException -> L19 DeadObjectException -> L24
            r6.taskQueue.remove();     // Catch: RemoteException -> L19 DeadObjectException -> L24
            goto L13
        L19:
            e = move-exception;
            Log.w("NotifManCompat", "RemoteException communicating with " + r6.componentName, e);
        L25:
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L28;
            Log.d("NotifManCompat", "Remote service has died: " + r6.componentName);
        L28:
            if (r6.taskQueue.isEmpty() == true) goto L38;
            scheduleListenerRetry(r6);
            return;
        }

        private void scheduleListenerRetry(ListenerRecord r6) {
            if (this.mHandler.hasMessages(3, r6.componentName) == false) goto L5;
            return;
        L5:
            int r0 = r6.retryCount;
            int r1 = r0 + 1;
            r6.retryCount = r1;
            if (r1 <= 6) goto L9;
            Log.w("NotifManCompat", "Giving up on delivering " + r6.taskQueue.size() + " tasks to " + r6.componentName + " after " + r6.retryCount + " retries");
            r6.taskQueue.clear();
            return;
        L9:
            int r02 = (1 << r0) * 1000;
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L12;
            Log.d("NotifManCompat", "Scheduling retry for " + r02 + " ms");
        L12:
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, r6.componentName), r02);
        }

        private void updateListenerMap() {
            Set<String> r0 = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (r0.equals(this.mCachedEnabledPackages) == true) goto L33;
            this.mCachedEnabledPackages = r0;
            List<ResolveInfo> r1 = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet r2 = new HashSet();
            Iterator<ResolveInfo> r12 = r1.iterator();
        L7:
            if (r12.hasNext() == false) goto L15;
            ResolveInfo r3 = r12.next();
            if (r0.contains(r3.serviceInfo.packageName) == false) goto L7;
            ServiceInfo r6 = r3.serviceInfo;
            ComponentName r5 = new ComponentName(r6.packageName, r6.name);
            if (r3.serviceInfo.permission != null) goto L13;
            r2.add(r5);
            goto L7
        L13:
            Log.w("NotifManCompat", "Permission present on component " + r5 + ", not adding listener record.");
            goto L7
        L15:
            Iterator r02 = r2.iterator();
        L17:
            if (r02.hasNext() == false) goto L24;
            ComponentName r13 = (ComponentName) r02.next();
            if (this.mRecordMap.containsKey(r13) == true) goto L17;
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L23;
            Log.d("NotifManCompat", "Adding listener record for " + r13);
        L23:
            this.mRecordMap.put(r13, new ListenerRecord(r13));
            goto L17
        L24:
            Iterator<Map.Entry<ComponentName, ListenerRecord>> r03 = this.mRecordMap.entrySet().iterator();
        L26:
            if (r03.hasNext() == false) goto L52;
            Map.Entry<ComponentName, ListenerRecord> r14 = r03.next();
            if (r2.contains(r14.getKey()) == true) goto L26;
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L32;
            Log.d("NotifManCompat", "Removing listener record for " + r14.getKey());
        L32:
            ensureServiceUnbound(r14.getValue());
            r03.remove();
            goto L26
        L52:
            return;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message r4) {
            int r0 = r4.what;
            if (r0 == 0) goto L17;
            if (r0 != 1) goto L6;
            ServiceConnectedEvent r42 = (ServiceConnectedEvent) r4.obj;
            handleServiceConnected(r42.componentName, r42.iBinder);
            return true;
        L6:
            if (r0 != 2) goto L8;
            handleServiceDisconnected((ComponentName) r4.obj);
            return true;
        L8:
            if (r0 == 3) goto L11;
            return false;
        L11:
            handleRetryListenerQueue((ComponentName) r4.obj);
            return true;
        L17:
            handleQueueTask((Task) r4.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName r4, IBinder r5) {
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L5;
            Log.d("NotifManCompat", "Connected to service " + r4);
        L5:
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(r4, r5)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName r4) {
            if (Log.isLoggable("NotifManCompat", 3) == false) goto L5;
            Log.d("NotifManCompat", "Disconnected from service " + r4);
        L5:
            this.mHandler.obtainMessage(2, r4).sendToTarget();
        }

        public void queueTask(Task r2) {
            this.mHandler.obtainMessage(0, r2).sendToTarget();
        }
    }

    /* loaded from: classes3.dex */
    public interface Task {
        void send(INotificationSideChannel r1);
    }

    static {
        sEnabledNotificationListenersLock = new Object();
        sEnabledNotificationListenerPackages = new HashSet();
        sLock = new Object();
    }

    private NotificationManagerCompat(Context r2) {
        this.mContext = r2;
        this.mNotificationManager = (NotificationManager) r2.getSystemService("notification");
    }

    public static NotificationManagerCompat from(Context r1) {
        return new NotificationManagerCompat(r1);
    }

    public static Set<String> getEnabledListenerPackages(Context r6) {
        String r62 = Settings.Secure.getString(r6.getContentResolver(), "enabled_notification_listeners");
        Object r0 = sEnabledNotificationListenersLock;
        monitor-enter(r0);
        if (r62 != null) goto L21;
    L16:
        Set<String> r63 = sEnabledNotificationListenerPackages;     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return r63;
    L12:
        th = move-exception;
        throw th;
    L21:
        if (r62.equals(sEnabledNotificationListeners) == true) goto L16;
        String[] r1 = r62.split(":", -1);     // Catch: Throwable -> L12
        HashSet r2 = new HashSet(r1.length);     // Catch: Throwable -> L12
        int r3 = r1.length;     // Catch: Throwable -> L12
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L15;
        ComponentName r5 = ComponentName.unflattenFromString(r1[r4]);     // Catch: Throwable -> L12
        if (r5 == null) goto L14;
        r2.add(r5.getPackageName());     // Catch: Throwable -> L12
    L14:
        r4 = r4 + 1;     // Catch: Throwable -> L12
        goto L8
    L15:
        sEnabledNotificationListenerPackages = r2;     // Catch: Throwable -> L12
        sEnabledNotificationListeners = r62;     // Catch: Throwable -> L12
        goto L16
    }

    private void pushSideChannelQueue(Task r3) {
        Object r0 = sLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (sSideChannelManager != null) goto L9;
        sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());     // Catch: Throwable -> L7
    L9:
        sSideChannelManager.queueTask(r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    private static boolean useSideChannelForNotification(Notification r1) {
        Bundle r12 = NotificationCompat.getExtras(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.getBoolean("android.support.useSideChannel") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean areNotificationsEnabled() {
        return Api24Impl.areNotificationsEnabled(this.mNotificationManager);
    }

    public void cancel(int r2) {
        cancel(null, r2);
    }

    public void cancel(String r1, int r2) {
        this.mNotificationManager.cancel(r1, r2);
    }

    public void createNotificationChannel(NotificationChannel r3) {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        Api26Impl.createNotificationChannel(this.mNotificationManager, r3);
        return;
    }

    public void createNotificationChannel(NotificationChannelCompat r1) {
        createNotificationChannel(r1.getNotificationChannel());
    }

    public void notify(int r2, Notification r3) {
        notify(null, r2, r3);
    }

    public void notify(String r3, int r4, Notification r5) {
        if (useSideChannelForNotification(r5) == false) goto L6;
        pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), r4, r3, r5));
        this.mNotificationManager.cancel(r3, r4);
        return;
    L6:
        this.mNotificationManager.notify(r3, r4, r5);
    }
}
