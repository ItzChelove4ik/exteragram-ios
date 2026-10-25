package androidx.car.app.navigation;

import android.util.Log;
import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.managers.Manager;
import androidx.car.app.navigation.INavigationManager;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class NavigationManager implements Manager {
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;
    private boolean mIsAutoDriveEnabled;
    private boolean mIsNavigating;
    private final INavigationManager.Stub mNavigationManager;
    private Executor mNavigationManagerCallbackExecutor;

    /* renamed from: androidx.car.app.navigation.NavigationManager$1, reason: invalid class name */
    public class AnonymousClass1 extends INavigationManager.Stub {
        final /* synthetic */ NavigationManager this$0;
        final /* synthetic */ Lifecycle val$lifecycle;

        public static /* synthetic */ Object $r8$lambda$GO2OrYvvWaXZTH1XkmNcVUSxmKU(AnonymousClass1 r0) {
            r0.this$0.onStopNavigation();
            return null;
        }

        public AnonymousClass1(NavigationManager r1, Lifecycle r2) {
            this.this$0 = r1;
            this.val$lifecycle = r2;
        }

        @Override // androidx.car.app.navigation.INavigationManager
        public void onStopNavigation(IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(this.val$lifecycle, r3, "onStopNavigation", new NavigationManager$1$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: androidx.car.app.navigation.NavigationManager$2, reason: invalid class name */
    public class AnonymousClass2 implements DefaultLifecycleObserver {
        final /* synthetic */ NavigationManager this$0;
        final /* synthetic */ Lifecycle val$lifecycle;

        public AnonymousClass2(NavigationManager r1, Lifecycle r2) {
            this.this$0 = r1;
            this.val$lifecycle = r2;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner r1) {
            this.this$0.onStopNavigation();
            this.val$lifecycle.removeObserver(this);
        }
    }

    public static /* synthetic */ void $r8$lambda$fzM06aQJ0HuFwkHHIffvASccIEo(NavigationManager r0) {
        r0.getClass();
    }

    public NavigationManager(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        Objects.requireNonNull(r1);
        this.mCarContext = r1;
        Objects.requireNonNull(r2);
        this.mHostDispatcher = r2;
        this.mNavigationManager = new AnonymousClass1(this, r3);
        r3.addObserver(new AnonymousClass2(this, r3));
    }

    public static NavigationManager create(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        Objects.requireNonNull(r3);
        return new NavigationManager(r1, r2, r3);
    }

    public INavigationManager.Stub getIInterface() {
        return this.mNavigationManager;
    }

    public void onAutoDriveEnabled() {
        ThreadUtils.checkMainThread();
        if (Log.isLoggable("CarApp.Nav", 3) == false) goto L5;
        Log.d("CarApp.Nav", "Executing onAutoDriveEnabled");
    L5:
        this.mIsAutoDriveEnabled = true;
        Log.w("CarApp.Nav", "NavigationManagerCallback not set, skipping onAutoDriveEnabled");
    }

    public void onStopNavigation() {
        ThreadUtils.checkMainThread();
        if (this.mIsNavigating == false) goto L10;
        this.mIsNavigating = false;
        Executor r0 = this.mNavigationManagerCallbackExecutor;
        if (r0 != null) goto L8;
        return;
    L8:
        r0.execute(new NavigationManager$$ExternalSyntheticLambda0(this));
        return;
    }
}
