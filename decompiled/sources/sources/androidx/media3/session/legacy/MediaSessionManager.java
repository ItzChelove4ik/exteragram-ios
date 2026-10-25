package androidx.media3.session.legacy;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class MediaSessionManager {
    static final boolean DEBUG = false;
    private static final Object lock = null;
    private static volatile MediaSessionManager sessionManager;
    MediaSessionManagerImpl impl;

    public static class MediaSessionManagerImpl {
        private static final boolean DEBUG = false;
        ContentResolver contentResolver;
        Context context;

        static {
            DEBUG = MediaSessionManager.DEBUG;
        }

        public MediaSessionManagerImpl(Context r1) {
            this.context = r1;
            this.contentResolver = r1.getContentResolver();
        }

        private boolean hasMediaControlPermission(RemoteUserInfoImpl r3) {
            if (this.context.checkPermission("android.permission.MEDIA_CONTENT_CONTROL", r3.getPid(), r3.getUid()) != 0) goto L6;
            return true;
        L6:
            return false;
        }

        private boolean isPermissionGranted(RemoteUserInfoImpl r4, String r5) {
            int r0 = r4.getPid();
            Context r3 = this.context;
            if (r0 >= 0) goto L9;
            if (r3.getPackageManager().checkPermission(r5, r4.getPackageName()) != 0) goto L7;
            return true;
        L7:
            return false;
        L9:
            if (r3.checkPermission(r5, r4.getPid(), r4.getUid()) != 0) goto L11;
            return true;
        L11:
            return false;
        }

        public boolean isEnabledNotificationListener(RemoteUserInfoImpl r5) {
            String r4 = Settings.Secure.getString(this.contentResolver, "enabled_notification_listeners");
            if (r4 == null) goto L14;
            String[] r42 = r4.split(":");
            int r1 = 0;
        L6:
            if (r1 >= r42.length) goto L14;
            ComponentName r2 = ComponentName.unflattenFromString(r42[r1]);
            if (r2 == null) goto L13;
            if (r2.getPackageName().equals(r5.getPackageName()) == false) goto L13;
            return true;
        L13:
            r1 = r1 + 1;
        L14:
            return false;
        }

        public boolean isTrustedForMediaControl(RemoteUserInfoImpl r5) {
            if (hasMediaControlPermission(r5) == false) goto L24;
            return true;
        L24:
            if (this.context.getPackageManager().getApplicationInfo(r5.getPackageName(), 0) != null) goto L10;
            return false;
        L10:
            if (isPermissionGranted(r5, "android.permission.STATUS_BAR_SERVICE") == false) goto L12;
        L19:
            return true;
        L12:
            if (isPermissionGranted(r5, "android.permission.MEDIA_CONTENT_CONTROL") == true) goto L19;
            if (r5.getUid() == 1000) goto L19;
            if (isEnabledNotificationListener(r5) == true) goto L19;
            return false;
        L21:
            if (DEBUG == false) goto L23;
            Log.d("MediaSessionManager", "Package " + r5.getPackageName() + " doesn't exist");
        L23:
            return false;
        }
    }

    public static final class RemoteUserInfo {
        RemoteUserInfoImpl impl;

        public RemoteUserInfo(MediaSessionManager.RemoteUserInfo r3) {
            String r0 = RemoteUserInfoImplApi28.getPackageName(r3);
            if (r0 != null) goto L5;
            f$$ExternalSyntheticBUOutline2.m("package shouldn't be null");
            throw null;
        L5:
            if (TextUtils.isEmpty(r0) == true) goto L8;
            this.impl = new RemoteUserInfoImplApi28(r3);
            return;
        L8:
            f$$ExternalSyntheticBUOutline1.m("packageName should be nonempty");
            throw null;
        }

        public RemoteUserInfo(String r3, int r4, int r5) {
            if (r3 != null) goto L5;
            f$$ExternalSyntheticBUOutline2.m("package shouldn't be null");
            throw null;
        L5:
            if (TextUtils.isEmpty(r3) == false) goto L7;
            f$$ExternalSyntheticBUOutline1.m("packageName should be nonempty");
            throw null;
        L7:
            if (Build.VERSION.SDK_INT < 28) goto L10;
            this.impl = new RemoteUserInfoImplApi28(r3, r4, r5);
            return;
        L10:
            this.impl = new RemoteUserInfoImplBase(r3, r4, r5);
        }

        public boolean equals(Object r2) {
            if (this != r2) goto L6;
            return true;
        L6:
            if ((r2 instanceof RemoteUserInfo) == true) goto L10;
            return false;
        L10:
            return this.impl.equals(((RemoteUserInfo) r2).impl);
        }

        public String getPackageName() {
            return this.impl.getPackageName();
        }

        public int getPid() {
            return this.impl.getPid();
        }

        public int getUid() {
            return this.impl.getUid();
        }

        public int hashCode() {
            return this.impl.hashCode();
        }
    }

    public interface RemoteUserInfoImpl {
        String getPackageName();

        int getPid();

        int getUid();
    }

    public static final class RemoteUserInfoImplApi28 extends RemoteUserInfoImplBase {
        public RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo r3) {
            super(r3.getPackageName(), r3.getPid(), r3.getUid());
        }

        public RemoteUserInfoImplApi28(String r1, int r2, int r3) {
            super(r1, r2, r3);
        }

        public static String getPackageName(MediaSessionManager.RemoteUserInfo r0) {
            return r0.getPackageName();
        }
    }

    public static class RemoteUserInfoImplBase implements RemoteUserInfoImpl {
        private final String packageName;
        private final int pid;
        private final int uid;

        public RemoteUserInfoImplBase(String r1, int r2, int r3) {
            this.packageName = r1;
            this.pid = r2;
            this.uid = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof RemoteUserInfoImplBase) == true) goto L8;
            return false;
        L8:
            RemoteUserInfoImplBase r52 = (RemoteUserInfoImplBase) r5;
            if (this.pid < 0) goto L22;
            if (r52.pid < 0) goto L22;
            if (TextUtils.equals(this.packageName, r52.packageName) == true) goto L16;
        L20:
            return false;
        L16:
            if (this.pid != r52.pid) goto L20;
            if (this.uid != r52.uid) goto L20;
            return true;
        L22:
            if (TextUtils.equals(this.packageName, r52.packageName) == true) goto L24;
        L26:
            return false;
        L24:
            if (this.uid != r52.uid) goto L26;
            return true;
        }

        @Override // androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfoImpl
        public String getPackageName() {
            return this.packageName;
        }

        @Override // androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfoImpl
        public int getPid() {
            return this.pid;
        }

        @Override // androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfoImpl
        public int getUid() {
            return this.uid;
        }

        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{this.packageName, Integer.valueOf(this.uid)});
        }
    }

    static {
        DEBUG = Log.isLoggable("MediaSessionManager", 3);
        lock = new Object();
    }

    private MediaSessionManager(Context r2) {
        this.impl = new MediaSessionManagerImpl(r2);
    }

    public static MediaSessionManager getSessionManager(Context r2) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (sessionManager != null) goto L9;
        sessionManager = new MediaSessionManager(r2.getApplicationContext());     // Catch: Throwable -> L7
    L9:
        MediaSessionManager r22 = sessionManager;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r22;
    }

    public boolean isTrustedForMediaControl(RemoteUserInfo r1) {
        return this.impl.isTrustedForMediaControl(r1.impl);
    }
}
