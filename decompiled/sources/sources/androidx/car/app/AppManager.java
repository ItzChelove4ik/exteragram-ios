package androidx.car.app;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.HandlerThread;
import android.util.Log;
import androidx.car.app.IAppManager;
import androidx.car.app.managers.Manager;
import androidx.car.app.media.OpenMicrophoneRequest;
import androidx.car.app.media.OpenMicrophoneResponse;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import androidx.core.location.LocationListenerCompat;
import androidx.lifecycle.Lifecycle;
import com.chaquo.python.internal.Common;
import java.util.Objects;

/* loaded from: classes3.dex */
public class AppManager implements Manager {
    private final IAppManager.Stub mAppManager;
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;
    private final Lifecycle mLifecycle;
    private final LocationListenerCompat mLocationListener;
    final HandlerThread mLocationUpdateHandlerThread;

    /* renamed from: androidx.car.app.AppManager$1, reason: invalid class name */
    public class AnonymousClass1 extends IAppManager.Stub {
        final /* synthetic */ AppManager this$0;
        final /* synthetic */ CarContext val$carContext;

        public static /* synthetic */ Object $r8$lambda$0BYIOBGeQufkvO0GpWbLJJAjwE8(CarContext r0) {
            r0.getOnBackPressedDispatcher().onBackPressed();
            return null;
        }

        public static /* synthetic */ Object $r8$lambda$GRoiwQQxcfwY7PBpRDnZMPJPkh8(CarContext r1) {
            ((AppManager) r1.getCarService(AppManager.class)).stopLocationUpdates();
            return null;
        }

        public static /* synthetic */ Object $r8$lambda$kiPel4e4C6L1n4qbWD8qVxWes9Y(CarContext r1) {
            ((AppManager) r1.getCarService(AppManager.class)).startLocationUpdates();
            return null;
        }

        public AnonymousClass1(AppManager r1, CarContext r2) {
            this.this$0 = r1;
            this.val$carContext = r2;
        }

        @Override // androidx.car.app.IAppManager
        public void getTemplate(IOnDoneCallback r3) {
            Lifecycle r0 = this.this$0.getLifecycle();
            ScreenManager r2 = (ScreenManager) this.val$carContext.getCarService(ScreenManager.class);
            Objects.requireNonNull(r2);
            RemoteUtils.dispatchCallFromHost(r0, r3, "getTemplate", new AppManager$1$$ExternalSyntheticLambda1(r2));
        }

        @Override // androidx.car.app.IAppManager
        public void onBackPressed(IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(this.this$0.getLifecycle(), r3, "onBackPressed", new AppManager$1$$ExternalSyntheticLambda0(this.val$carContext));
        }

        @Override // androidx.car.app.IAppManager
        public void startLocationUpdates(IOnDoneCallback r8) {
            PackageManager r0 = this.val$carContext.getPackageManager();
            boolean r2 = false;
            if (r0.checkPermission("android.permission.ACCESS_FINE_LOCATION", this.val$carContext.getPackageName()) != (-1)) goto L5;
            boolean r1 = true;
        L7:
            if (r0.checkPermission("android.permission.ACCESS_COARSE_LOCATION", this.val$carContext.getPackageName()) != (-1)) goto L10;
            r2 = true;
        L10:
            if (r1 == false) goto L13;
            if (r2 == false) goto L13;
            RemoteUtils.sendFailureResponseToHost(r8, "startLocationUpdates", new SecurityException("Location permission(s) not granted."));
        L13:
            RemoteUtils.dispatchCallFromHost(this.this$0.getLifecycle(), r8, "startLocationUpdates", new AppManager$1$$ExternalSyntheticLambda3(this.val$carContext));
            return;
        L5:
            r1 = false;
            goto L7
        }

        @Override // androidx.car.app.IAppManager
        public void stopLocationUpdates(IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(this.this$0.getLifecycle(), r3, "stopLocationUpdates", new AppManager$1$$ExternalSyntheticLambda2(this.val$carContext));
        }
    }

    /* renamed from: $r8$lambda$-eRdGEweqivF9t3_swK7uQbdXz4, reason: not valid java name */
    public static /* synthetic */ OpenMicrophoneResponse m628$r8$lambda$eRdGEweqivF9t3_swK7uQbdXz4(OpenMicrophoneRequest r2, IAppHost r3) {
        Bundleable r22 = r3.openMicrophone(Bundleable.create(r2));     // Catch: BundlerException -> L8
        if (r22 != null) goto L6;
        return null;
    L6:
        return (OpenMicrophoneResponse) r22.get();
    L8:
        e = move-exception;
        Log.e("CarApp", "Cannot open microphone", e);
        return null;
    }

    public static /* synthetic */ void $r8$lambda$VtVWtpIxURiz0OeBU2n0ZIAdpM0(AppManager r2, Location r3) {
        r2.mHostDispatcher.dispatch(Common.ASSET_APP, "sendLocation", new AppManager$$ExternalSyntheticLambda3(r3));
    }

    public static /* synthetic */ Object $r8$lambda$cN4HC8fuyfXod6lQ1qmuMXVVog4(Location r0, IAppHost r1) {
        r1.sendLocation(r0);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$m3Q_6T8MfMcf0F5Std_z_SjF1jA(IAppHost r0) {
        r0.invalidate();
        return null;
    }

    public AppManager(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        this.mCarContext = r1;
        this.mHostDispatcher = r2;
        this.mLifecycle = r3;
        this.mAppManager = new AnonymousClass1(this, r1);
        this.mLocationUpdateHandlerThread = new HandlerThread("LocationUpdateThread");
        this.mLocationListener = new AppManager$$ExternalSyntheticLambda2(this);
    }

    public static AppManager create(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        Objects.requireNonNull(r3);
        return new AppManager(r1, r2, r3);
    }

    public IAppManager.Stub getIInterface() {
        return this.mAppManager;
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public void invalidate() {
        this.mHostDispatcher.dispatch(Common.ASSET_APP, "invalidate", new AppManager$$ExternalSyntheticLambda1());
    }

    public OpenMicrophoneResponse openMicrophone(OpenMicrophoneRequest r4) {
        return (OpenMicrophoneResponse) this.mHostDispatcher.dispatchForResult(Common.ASSET_APP, "openMicrophone", new AppManager$$ExternalSyntheticLambda0(r4));
    L4:
        e = move-exception;
        Log.e("CarApp", "Error getting microphone bytes from host", e);
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public void startLocationUpdates() {
        stopLocationUpdates();
        ((LocationManager) this.mCarContext.getSystemService("location")).requestLocationUpdates("fused", 1000, 1.0f, this.mLocationListener, this.mLocationUpdateHandlerThread.getLooper());
    }

    public void stopLocationUpdates() {
        ((LocationManager) this.mCarContext.getSystemService("location")).removeUpdates(this.mLocationListener);
    }
}
