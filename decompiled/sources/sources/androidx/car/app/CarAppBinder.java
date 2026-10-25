package androidx.car.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.util.Log;
import androidx.car.app.ICarApp;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.car.app.validation.HostValidator;
import androidx.car.app.versioning.CarAppApiLevels;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import com.chaquo.python.internal.Common;
import java.security.InvalidParameterException;
import java.util.Objects;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
final class CarAppBinder extends ICarApp.Stub {
    private Session mCurrentSession;
    private final SessionInfo mCurrentSessionInfo;
    private HandshakeInfo mHandshakeInfo;
    private HostValidator mHostValidator;
    private CarAppService mService;

    /* renamed from: $r8$lambda$-NmrdC46ynLALOfGH0FYY9n6M6o, reason: not valid java name */
    public static /* synthetic */ Object m629$r8$lambda$NmrdC46ynLALOfGH0FYY9n6M6o(CarAppBinder r1) {
        Session r12 = r1.mCurrentSession;
        Objects.requireNonNull(r12);
        r12.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$7Uack6mc_KLNEQNXfnJY9SPsiFU(CarAppBinder r1) {
        Session r12 = r1.mCurrentSession;
        Objects.requireNonNull(r12);
        r12.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        return null;
    }

    /* renamed from: $r8$lambda$9yq-tiei5dqpkoINH-KvUxBxuvs, reason: not valid java name */
    public static /* synthetic */ Object m630$r8$lambda$9yqtiei5dqpkoINHKvUxBxuvs(CarAppBinder r1) {
        Session r12 = r1.mCurrentSession;
        Objects.requireNonNull(r12);
        r12.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        return null;
    }

    public static /* synthetic */ void $r8$lambda$IlVDu2UN1ozorozvq1aLAqao2a8(CarAppBinder r2, String r3, IOnDoneCallback r4) {
        Session r22 = r2.mCurrentSession;
        Objects.requireNonNull(r22);
        r3.getClass();
        if (r3.equals(Common.ASSET_APP) == false) goto L5;
        RemoteUtils.sendSuccessResponseToHost(r4, "getManager", ((AppManager) r22.getCarContext().getCarService(AppManager.class)).getIInterface());
        return;
    L5:
        if (r3.equals("navigation") == true) goto L8;
        Log.e("CarApp", r3.concat("%s is not a valid manager"));
        RemoteUtils.sendFailureResponseToHost(r4, "getManager", new InvalidParameterException(r3.concat(" is not a valid manager type")));
        return;
    L8:
        RemoteUtils.sendSuccessResponseToHost(r4, "getManager", ((NavigationManager) r22.getCarContext().getCarService(NavigationManager.class)).getIInterface());
    }

    public static /* synthetic */ Object $r8$lambda$PVFAimZMMIOaPhkV65sW3A2TlPU(CarAppBinder r1) {
        Session r12 = r1.mCurrentSession;
        Objects.requireNonNull(r12);
        r12.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$XEfwwvAnIshhGblecnxdG4sh6nQ(CarAppBinder r1, Configuration r2) {
        Session r0 = r1.mCurrentSession;
        Objects.requireNonNull(r0);
        r1.onConfigurationChangedInternal(r0, r2);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$qV15wLbZchZqw7AYDWUTdB04thg(CarAppBinder r6, ICarHost r7, Configuration r8, Intent r9) {
        CarAppService r1 = r6.mService;
        Objects.requireNonNull(r1);
        Session r0 = r6.mCurrentSession;
        if (r0 != null) goto L5;
    L6:
        SessionInfo r02 = r6.mCurrentSessionInfo;
        Objects.requireNonNull(r02);
        r0 = r1.onCreateSession(r02);
        r6.mCurrentSession = r0;
    L7:
        HandshakeInfo r2 = r6.getHandshakeInfo();
        Objects.requireNonNull(r2);
        HostInfo r3 = r1.getHostInfo();
        Objects.requireNonNull(r3);
        r0.configure(r1, r2, r3, r7, r8);
        LifecycleRegistry r72 = (LifecycleRegistry) r0.getLifecycle();
        Lifecycle.State r82 = r72.getCurrentState();
        int r12 = ((ScreenManager) r0.getCarContext().getCarService(ScreenManager.class)).getScreenStackInternal().size();
        if (r82.isAtLeast(Lifecycle.State.CREATED) == false) goto L17;
        if (r12 < 1) goto L17;
        if (Log.isLoggable("CarApp", 3) == false) goto L15;
        Log.d("CarApp", "onAppCreate the app was already created");
    L15:
        r6.onNewIntentInternal(r0, r9);
        return null;
    L17:
        if (Log.isLoggable("CarApp", 3) == false) goto L19;
        Log.d("CarApp", "onAppCreate the app was not yet created or the screen stack was empty state: " + r72.getCurrentState() + ", stack size: " + r12);
    L19:
        r0.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        ((ScreenManager) r0.getCarContext().getCarService(ScreenManager.class)).push(r0.onCreateScreen(r9));
        return null;
    L5:
        if (r0.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) goto L7;
        goto L6
    }

    /* renamed from: $r8$lambda$r2-GCoVHE6N83Ffmv4fYzhYrccc, reason: not valid java name */
    public static /* synthetic */ Object m631$r8$lambda$r2GCoVHE6N83Ffmv4fYzhYrccc(CarAppBinder r1, Intent r2) {
        Session r0 = r1.mCurrentSession;
        Objects.requireNonNull(r0);
        r1.onNewIntentInternal(r0, r2);
        return null;
    }

    public CarAppBinder(CarAppService r1, SessionInfo r2) {
        this.mService = r1;
        this.mCurrentSessionInfo = r2;
    }

    private Lifecycle getCurrentLifecycle() {
        Session r0 = this.mCurrentSession;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getLifecycle();
    }

    private HostValidator getHostValidator() {
        if (this.mHostValidator != null) goto L6;
        CarAppService r0 = this.mService;
        Objects.requireNonNull(r0);
        this.mHostValidator = r0.createHostValidator();
    L6:
        return this.mHostValidator;
    }

    private void onConfigurationChangedInternal(Session r3, Configuration r4) {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable("CarApp", 3) == false) goto L5;
        Log.d("CarApp", "onCarConfigurationChanged configuration: " + r4);
    L5:
        r3.onCarConfigurationChangedInternal(r4);
    }

    private void onNewIntentInternal(Session r1, Intent r2) {
        ThreadUtils.checkMainThread();
        r1.onNewIntent(r2);
    }

    public void destroy() {
        onDestroyLifecycle();
        this.mCurrentSession = null;
        this.mHostValidator = null;
        this.mHandshakeInfo = null;
        this.mService = null;
    }

    @Override // androidx.car.app.ICarApp
    public void getAppInfo(IOnDoneCallback r2) {
        CarAppService r1 = this.mService;     // Catch: IllegalArgumentException -> L5
        Objects.requireNonNull(r1);     // Catch: IllegalArgumentException -> L5
        RemoteUtils.sendSuccessResponseToHost(r2, "getAppInfo", r1.getAppInfo());     // Catch: IllegalArgumentException -> L5
        return;
    L5:
        e = move-exception;
        RemoteUtils.sendFailureResponseToHost(r2, "getAppInfo", e);
    }

    public Session getCurrentSession() {
        return this.mCurrentSession;
    }

    public SessionInfo getCurrentSessionInfo() {
        return this.mCurrentSessionInfo;
    }

    public HandshakeInfo getHandshakeInfo() {
        return this.mHandshakeInfo;
    }

    @Override // androidx.car.app.ICarApp
    public void getManager(String r2, IOnDoneCallback r3) {
        ThreadUtils.runOnMain(new CarAppBinder$$ExternalSyntheticLambda5(this, r2, r3));
    }

    @Override // androidx.car.app.ICarApp
    public void onAppCreate(ICarHost r5, Intent r6, Configuration r7, IOnDoneCallback r8) {
        if (Log.isLoggable("CarApp", 3) == false) goto L5;
        Log.d("CarApp", "onAppCreate intent: " + r6);
    L5:
        RemoteUtils.dispatchCallFromHost(r8, "onAppCreate", new CarAppBinder$$ExternalSyntheticLambda4(this, r5, r7, r6));
        if (Log.isLoggable("CarApp", 3) == false) goto L9;
        Log.d("CarApp", "onAppCreate completed");
        return;
    }

    @Override // androidx.car.app.ICarApp
    public void onAppPause(IOnDoneCallback r3) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), r3, "onAppPause", new CarAppBinder$$ExternalSyntheticLambda1(this));
    }

    @Override // androidx.car.app.ICarApp
    public void onAppResume(IOnDoneCallback r3) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), r3, "onAppResume", new CarAppBinder$$ExternalSyntheticLambda7(this));
    }

    @Override // androidx.car.app.ICarApp
    public void onAppStart(IOnDoneCallback r3) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), r3, "onAppStart", new CarAppBinder$$ExternalSyntheticLambda2(this));
    }

    @Override // androidx.car.app.ICarApp
    public void onAppStop(IOnDoneCallback r3) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), r3, "onAppStop", new CarAppBinder$$ExternalSyntheticLambda3(this));
    }

    public void onAutoDriveEnabled() {
        Session r1 = this.mCurrentSession;
        if (r1 == null) goto L6;
        ((NavigationManager) r1.getCarContext().getCarService(NavigationManager.class)).onAutoDriveEnabled();
        return;
    }

    @Override // androidx.car.app.ICarApp
    public void onConfigurationChanged(Configuration r3, IOnDoneCallback r4) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), r4, "onConfigurationChanged", new CarAppBinder$$ExternalSyntheticLambda0(this, r3));
    }

    public void onDestroyLifecycle() {
        Session r0 = this.mCurrentSession;
        if (r0 == null) goto L5;
        r0.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    L5:
        this.mCurrentSession = null;
    }

    @Override // androidx.car.app.ICarApp
    public void onHandshakeCompleted(Bundleable r10, IOnDoneCallback r11) {
        CarAppService r2 = this.mService;
        Objects.requireNonNull(r2);
        HandshakeInfo r102 = (HandshakeInfo) r10.get();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        String r4 = r102.getHostPackageName();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        int r5 = Binder.getCallingUid();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        HostInfo r6 = new HostInfo(r4, r5);     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        if (getHostValidator().isValidHost(r6) == true) goto L11;
        RemoteUtils.sendFailureResponseToHost(r11, "onHandshakeCompleted", new IllegalArgumentException("Unknown host '" + r4 + "', uid:" + r5));     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        return;
    L11:
        AppInfo r1 = r2.getAppInfo();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        int r42 = r1.getMinCarAppApiLevel();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        int r12 = r1.getLatestCarAppApiLevel();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        int r52 = r102.getHostCarAppApiLevel();     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        if (r42 <= r52) goto L16;
        RemoteUtils.sendFailureResponseToHost(r11, "onHandshakeCompleted", new IllegalArgumentException("Host API level (" + r52 + ") is less than the app's min API level (" + r42 + ")"));     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        return;
    L16:
        if (r12 >= r52) goto L19;
        RemoteUtils.sendFailureResponseToHost(r11, "onHandshakeCompleted", new IllegalArgumentException("Host API level (" + r52 + ") is greater than the app's max API level (" + r12 + ")"));     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        return;
    L19:
        r2.setHostInfo(r6);     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        this.mHandshakeInfo = r102;     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        RemoteUtils.sendSuccessResponseToHost(r11, "onHandshakeCompleted", null);     // Catch: IllegalArgumentException -> L7 BundlerException -> L9
        return;
    L9:
        e = e;
    L21:
        r2.setHostInfo(null);
        RemoteUtils.sendFailureResponseToHost(r11, "onHandshakeCompleted", e);
        return;
    L7:
        e = e;
        goto L21
    }

    @Override // androidx.car.app.ICarApp
    public void onNewIntent(Intent r3, IOnDoneCallback r4) {
        RemoteUtils.dispatchCallFromHost(getCurrentLifecycle(), r4, "onNewIntent", new CarAppBinder$$ExternalSyntheticLambda6(this, r3));
    }

    public void setHandshakeInfo(HandshakeInfo r3) {
        int r0 = r3.getHostCarAppApiLevel();
        if (CarAppApiLevels.isValid(r0) == false) goto L6;
        this.mHandshakeInfo = r3;
        return;
    L6:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid Car App API level received: ", r0);
    }
}
