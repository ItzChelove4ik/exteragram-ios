package androidx.car.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import java.util.Objects;

/* loaded from: classes3.dex */
public abstract class Session implements LifecycleOwner {
    private CarContext mCarContext;
    private final LifecycleObserver mLifecycleObserver;
    private LifecycleRegistry mRegistry;
    final LifecycleRegistry mRegistryPublic;

    public class LifecycleObserverImpl implements DefaultLifecycleObserver {
        final /* synthetic */ Session this$0;

        public LifecycleObserverImpl(Session r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onCreate(LifecycleOwner r1) {
            this.this$0.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner r3) {
            this.this$0.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            r3.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onPause(LifecycleOwner r1) {
            this.this$0.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(LifecycleOwner r1) {
            this.this$0.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onStart(LifecycleOwner r1) {
            this.this$0.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_START);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onStop(LifecycleOwner r1) {
            this.this$0.mRegistryPublic.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
    }

    public Session() {
        LifecycleObserverImpl r0 = new LifecycleObserverImpl(this);
        this.mLifecycleObserver = r0;
        this.mRegistry = new LifecycleRegistry(this);
        this.mRegistryPublic = new LifecycleRegistry(this);
        this.mRegistry.addObserver(r0);
        this.mCarContext = CarContext.create(this.mRegistry);
    }

    public void configure(Context r2, HandshakeInfo r3, HostInfo r4, ICarHost r5, Configuration r6) {
        this.mCarContext.updateHandshakeInfo(r3);
        this.mCarContext.updateHostInfo(r4);
        this.mCarContext.attachBaseContext(r2, r6);
        this.mCarContext.setCarHost(r5);
    }

    public final CarContext getCarContext() {
        CarContext r0 = this.mCarContext;
        Objects.requireNonNull(r0);
        return r0;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mRegistryPublic;
    }

    public void handleLifecycleEvent(Lifecycle.Event r1) {
        this.mRegistry.handleLifecycleEvent(r1);
    }

    public void onCarConfigurationChanged(Configuration r1) {
    }

    public void onCarConfigurationChangedInternal(Configuration r2) {
        this.mCarContext.onCarConfigurationChanged(r2);
        onCarConfigurationChanged(this.mCarContext.getResources().getConfiguration());
    }

    public abstract Screen onCreateScreen(Intent r1);

    public void onNewIntent(Intent r1) {
    }
}
