package androidx.car.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.util.Log;
import androidx.activity.OnBackPressedDispatcher;
import androidx.car.app.IOnRequestPermissionsListener;
import androidx.car.app.constraints.ConstraintManager;
import androidx.car.app.hardware.CarHardwareManager;
import androidx.car.app.managers.ManagerCache;
import androidx.car.app.managers.ResultManager;
import androidx.car.app.media.MediaPlaybackManager;
import androidx.car.app.navigation.NavigationManager;
import androidx.car.app.suggestion.SuggestionManager;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.chaquo.python.internal.Common;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class CarContext extends ContextWrapper {
    private int mCarAppApiLevel;
    private final HostDispatcher mHostDispatcher;
    private HostInfo mHostInfo;
    private final Lifecycle mLifecycle;
    private final ManagerCache mManagers;
    private final OnBackPressedDispatcher mOnBackPressedDispatcher;

    /* renamed from: androidx.car.app.CarContext$1, reason: invalid class name */
    class AnonymousClass1 extends IOnRequestPermissionsListener.Stub {
        final /* synthetic */ CarContext this$0;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Lifecycle val$lifecycle;
        final /* synthetic */ OnRequestPermissionsListener val$listener;

        /* renamed from: $r8$lambda$tA2s1S1_fRvhegVBgioc-DK7KTU, reason: not valid java name */
        public static /* synthetic */ void m634$r8$lambda$tA2s1S1_fRvhegVBgiocDK7KTU(OnRequestPermissionsListener r0, List r1, List r2) {
            r0.onRequestPermissionsResult(r1, r2);
        }

        public AnonymousClass1(CarContext r1, Lifecycle r2, Executor r3, OnRequestPermissionsListener r4) {
            this.this$0 = r1;
            this.val$lifecycle = r2;
            this.val$executor = r3;
        }

        @Override // androidx.car.app.IOnRequestPermissionsListener
        public void onRequestPermissionsResult(String[] r3, String[] r4) {
            if (this.val$lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED) == false) goto L6;
            List r32 = Arrays.asList(r3);
            List r42 = Arrays.asList(r4);
            this.val$executor.execute(new CarContext$1$$ExternalSyntheticLambda0(null, r32, r42));
            return;
        }
    }

    /* renamed from: androidx.car.app.CarContext$2, reason: invalid class name */
    public class AnonymousClass2 implements DefaultLifecycleObserver {
        final /* synthetic */ CarContext this$0;
        final /* synthetic */ HostDispatcher val$hostDispatcher;

        public AnonymousClass2(CarContext r1, HostDispatcher r2) {
            this.this$0 = r1;
            this.val$hostDispatcher = r2;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner r2) {
            this.val$hostDispatcher.resetHosts();
            r2.getLifecycle().removeObserver(this);
        }
    }

    public static /* synthetic */ SuggestionManager $r8$lambda$1IuE5DcaI6ei0ccTfAZBaHqwYWc(CarContext r0, HostDispatcher r1, Lifecycle r2) {
        r0.getClass();
        return SuggestionManager.create(r0, r1, r2);
    }

    public static /* synthetic */ MediaPlaybackManager $r8$lambda$7CC6CVHCHo4nEigSvYlAvcSVOH4(CarContext r0, HostDispatcher r1, Lifecycle r2) {
        r0.getClass();
        return MediaPlaybackManager.create(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$Oge6vCqhd2R3YU4xmfvqs-0fkwo, reason: not valid java name */
    public static /* synthetic */ AppManager m633$r8$lambda$Oge6vCqhd2R3YU4xmfvqs0fkwo(CarContext r0, HostDispatcher r1, Lifecycle r2) {
        r0.getClass();
        return AppManager.create(r0, r1, r2);
    }

    public static /* synthetic */ ResultManager $r8$lambda$PLFyesAEudIcWeAhSdKCU9rg_lM(CarContext r0) {
        r0.getClass();
        ResultManager.create(r0);
        return null;
    }

    public static /* synthetic */ void $r8$lambda$SCaUC9o5VLrmY9U6_6ZkDF4fiEA(CarContext r1) {
        ((ScreenManager) r1.getCarService(ScreenManager.class)).pop();
    }

    public static /* synthetic */ NavigationManager $r8$lambda$VoMvO9zmKiD220_sh3u0fxFT3G0(CarContext r0, HostDispatcher r1, Lifecycle r2) {
        r0.getClass();
        return NavigationManager.create(r0, r1, r2);
    }

    public static /* synthetic */ CarHardwareManager $r8$lambda$oRslYWvJSzlVhA1P74n6gPzrVNw(CarContext r0, HostDispatcher r1) {
        r0.getClass();
        return CarHardwareManager.create(r0, r1);
    }

    public static /* synthetic */ ConstraintManager $r8$lambda$rWkxYj29lgUN5flLv3OH1oavxkw(CarContext r0, HostDispatcher r1) {
        r0.getClass();
        return ConstraintManager.create(r0, r1);
    }

    public static /* synthetic */ ScreenManager $r8$lambda$uI7jlReeLpvE9gQT_cKxXLPvj4w(CarContext r0, Lifecycle r1) {
        r0.getClass();
        return ScreenManager.create(r0, r1);
    }

    public CarContext(Lifecycle r6, HostDispatcher r7) {
        super(null);
        ManagerCache r1 = new ManagerCache();
        this.mManagers = r1;
        this.mCarAppApiLevel = 0;
        this.mHostInfo = null;
        this.mHostDispatcher = r7;
        r1.addFactory(AppManager.class, Common.ASSET_APP, new CarContext$$ExternalSyntheticLambda0(this, r7, r6));
        r1.addFactory(NavigationManager.class, "navigation", new CarContext$$ExternalSyntheticLambda1(this, r7, r6));
        r1.addFactory(ScreenManager.class, "screen", new CarContext$$ExternalSyntheticLambda2(this, r6));
        r1.addFactory(ConstraintManager.class, "constraints", new CarContext$$ExternalSyntheticLambda3(this, r7));
        r1.addFactory(CarHardwareManager.class, "hardware", new CarContext$$ExternalSyntheticLambda4(this, r7));
        r1.addFactory(ResultManager.class, null, new CarContext$$ExternalSyntheticLambda5(this));
        r1.addFactory(SuggestionManager.class, "suggestion", new CarContext$$ExternalSyntheticLambda6(this, r7, r6));
        r1.addFactory(MediaPlaybackManager.class, "media_playback", new CarContext$$ExternalSyntheticLambda7(this, r7, r6));
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new CarContext$$ExternalSyntheticLambda8(this));
        this.mLifecycle = r6;
        r6.addObserver(new AnonymousClass2(this, r7));
    }

    public static CarContext create(Lifecycle r2) {
        return new CarContext(r2, new HostDispatcher());
    }

    public void attachBaseContext(Context r9, Configuration r10) {
        ThreadUtils.checkMainThread();
        if (getBaseContext() != null) goto L5;
        Object r0 = r9.getSystemService("display");
        Objects.requireNonNull(r0);
        attachBaseContext(r9.createDisplayContext(((DisplayManager) r0).createVirtualDisplay("CarAppService", r10.screenWidthDp, r10.screenHeightDp, r10.densityDpi, null, 8).getDisplay()).createConfigurationContext(r10));
    L5:
        onCarConfigurationChanged(r10);
    }

    public int getCarAppApiLevel() {
        int r0 = this.mCarAppApiLevel;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Car App API level hasn't been established yet");
        return 0;
    }

    public <T> T getCarService(Class<T> r1) {
        Objects.requireNonNull(r1);
        return (T) this.mManagers.getOrCreate(r1);
    }

    public OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public void onCarConfigurationChanged(Configuration r4) {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable("CarApp", 3) == false) goto L5;
        Log.d("CarApp", "Car configuration changed, configuration: " + r4 + ", displayMetrics: " + getResources().getDisplayMetrics());
    L5:
        Resources r0 = getResources();
        Objects.requireNonNull(r4);
        r0.updateConfiguration(r4, getResources().getDisplayMetrics());
    }

    public void setCarHost(ICarHost r1) {
        ThreadUtils.checkMainThread();
        HostDispatcher r0 = this.mHostDispatcher;
        Objects.requireNonNull(r1);
        r0.setCarHost(r1);
    }

    public void updateHandshakeInfo(HandshakeInfo r1) {
        this.mCarAppApiLevel = r1.getHostCarAppApiLevel();
    }

    public void updateHostInfo(HostInfo r1) {
        this.mHostInfo = r1;
    }
}
