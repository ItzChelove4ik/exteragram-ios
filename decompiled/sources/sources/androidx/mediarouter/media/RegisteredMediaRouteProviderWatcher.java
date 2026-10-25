package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.mediarouter.media.MediaRouteProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final class RegisteredMediaRouteProviderWatcher {
    final Callback mCallback;
    private final Context mContext;
    private final Handler mHandler;
    private boolean mMediaTransferRestrictedToSelfProviders;
    private final PackageManager mPackageManager;
    private final ArrayList<RegisteredMediaRouteProvider> mProviders;
    private boolean mRunning;
    private final BroadcastReceiver mScanPackagesReceiver;
    private final Runnable mScanPackagesRunnable;

    /* renamed from: androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ RegisteredMediaRouteProviderWatcher this$0;

        public AnonymousClass1(RegisteredMediaRouteProviderWatcher r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            this.this$0.scanPackages();
        }
    }

    /* renamed from: androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ RegisteredMediaRouteProviderWatcher this$0;

        public AnonymousClass2(RegisteredMediaRouteProviderWatcher r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.scanPackages();
        }
    }

    public interface Callback {
        void addProvider(MediaRouteProvider r1);

        void releaseProviderController(RegisteredMediaRouteProvider r1, MediaRouteProvider.RouteController r2);

        void removeProvider(MediaRouteProvider r1);
    }

    /* renamed from: $r8$lambda$CMmJ9YW5HRKS-l5DEoLqRbUmwTI, reason: not valid java name */
    public static /* synthetic */ void m931$r8$lambda$CMmJ9YW5HRKSl5DEoLqRbUmwTI(RegisteredMediaRouteProviderWatcher r0, RegisteredMediaRouteProvider r1, MediaRouteProvider.RouteController r2) {
        r0.mCallback.releaseProviderController(r1, r2);
    }

    public RegisteredMediaRouteProviderWatcher(Context r2, Callback r3) {
        this.mProviders = new ArrayList();
        this.mScanPackagesReceiver = new AnonymousClass1(this);
        this.mScanPackagesRunnable = new AnonymousClass2(this);
        this.mContext = r2;
        this.mCallback = r3;
        this.mHandler = new Handler();
        this.mPackageManager = r2.getPackageManager();
    }

    private int findProvider(String r4, String r5) {
        int r0 = this.mProviders.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.mProviders.get(r1).hasComponentName(r4, r5) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    public static boolean listContainsServiceInfo(List<ServiceInfo> r4, ServiceInfo r5) {
        if (r5 == null) goto L17;
        if (r4 == null) goto L17;
        if (r4.isEmpty() == true) goto L17;
        Iterator<ServiceInfo> r42 = r4.iterator();
    L10:
        if (r42.hasNext() == false) goto L17;
        ServiceInfo r1 = r42.next();
        if (r5.packageName.equals(r1.packageName) == false) goto L10;
        if (r5.name.equals(r1.name) == false) goto L10;
        return true;
    L17:
        return false;
    }

    public List<ServiceInfo> getMediaRoute2ProviderServices() {
        Intent r0 = new Intent("android.media.MediaRoute2ProviderService");
        ArrayList r1 = new ArrayList();
        Iterator<ResolveInfo> r02 = this.mPackageManager.queryIntentServices(r0, 0).iterator();
    L4:
        if (r02.hasNext() == false) goto L11;
        ServiceInfo r2 = r02.next().serviceInfo;
        if (this.mMediaTransferRestrictedToSelfProviders == false) goto L10;
        if (TextUtils.equals(this.mContext.getPackageName(), r2.packageName) == false) goto L4;
    L10:
        r1.add(r2);
        goto L4
    L11:
        return r1;
    }

    public boolean isMediaTransferRestrictedToSelfProvidersForTesting() {
        return this.mMediaTransferRestrictedToSelfProviders;
    }

    public void rescan() {
        this.mHandler.post(this.mScanPackagesRunnable);
    }

    public void scanPackages() {
        if (this.mRunning == false) goto L43;
        List<ServiceInfo> r0 = new ArrayList();
        if (Build.VERSION.SDK_INT < 30) goto L8;
        r0 = getMediaRoute2ProviderServices();
    L8:
        int r3 = 0;
        Iterator<ResolveInfo> r1 = this.mPackageManager.queryIntentServices(new Intent(MediaRouteProviderProtocol.SERVICE_INTERFACE), 0).iterator();
    L10:
        if (r1.hasNext() == false) goto L26;
        ServiceInfo r2 = r1.next().serviceInfo;
        if (r2 == null) goto L10;
        if (MediaRouter.isMediaTransferEnabled() == false) goto L19;
        if (listContainsServiceInfo(r0, r2) == true) goto L10;
    L19:
        int r4 = findProvider(r2.packageName, r2.name);
        if (r4 < 0) goto L21;
        if (r4 < r3) goto L10;
        RegisteredMediaRouteProvider r22 = this.mProviders.get(r4);
        r22.start();
        r22.rebindIfDisconnected();
        int r5 = r3 + 1;
        Collections.swap(this.mProviders, r4, r3);
    L22:
        r3 = r5;
        goto L10
    L21:
        RegisteredMediaRouteProvider r42 = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(r2.packageName, r2.name));
        r42.setControllerCallback(new RegisteredMediaRouteProviderWatcher$$ExternalSyntheticLambda0(this, r42));
        r42.start();
        r5 = r3 + 1;
        this.mProviders.add(r3, r42);
        this.mCallback.addProvider(r42);
        goto L22
    L26:
        if (r3 >= this.mProviders.size()) goto L30;
        int r02 = this.mProviders.size() - 1;
    L28:
        if (r02 < r3) goto L44;
        RegisteredMediaRouteProvider r12 = this.mProviders.get(r02);
        this.mCallback.removeProvider(r12);
        this.mProviders.remove(r12);
        r12.setControllerCallback(null);
        r12.stop();
        r02 = r02 - 1;
        goto L28
    L44:
        return;
    L30:
        return;
    }

    public void setMediaTransferRestrictedToSelfProviders(boolean r1) {
        this.mMediaTransferRestrictedToSelfProviders = r1;
        rescan();
    }

    public void start() {
        if (this.mRunning == true) goto L6;
        this.mRunning = true;
        IntentFilter r0 = new IntentFilter();
        r0.addAction("android.intent.action.PACKAGE_ADDED");
        r0.addAction("android.intent.action.PACKAGE_REMOVED");
        r0.addAction("android.intent.action.PACKAGE_CHANGED");
        r0.addAction("android.intent.action.PACKAGE_REPLACED");
        r0.addAction("android.intent.action.PACKAGE_RESTARTED");
        r0.addDataScheme("package");
        this.mContext.registerReceiver(this.mScanPackagesReceiver, r0, null, this.mHandler);
        this.mHandler.post(this.mScanPackagesRunnable);
        return;
    }

    public void stop() {
        if (this.mRunning == false) goto L7;
        this.mRunning = false;
        this.mContext.unregisterReceiver(this.mScanPackagesReceiver);
        this.mHandler.removeCallbacks(this.mScanPackagesRunnable);
        int r0 = this.mProviders.size() - 1;
    L5:
        if (r0 < 0) goto L9;
        this.mProviders.get(r0).stop();
        r0 = r0 - 1;
        goto L5
    L9:
        return;
    }
}
