package androidx.car.app.media;

import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.managers.Manager;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.Objects;

/* loaded from: classes3.dex */
public class MediaPlaybackManager implements Manager {
    private final HostDispatcher mHostDispatcher;

    /* renamed from: androidx.car.app.media.MediaPlaybackManager$1, reason: invalid class name */
    public class AnonymousClass1 implements DefaultLifecycleObserver {
        final /* synthetic */ MediaPlaybackManager this$0;
        final /* synthetic */ Lifecycle val$lifecycle;

        public AnonymousClass1(MediaPlaybackManager r1, Lifecycle r2) {
            this.this$0 = r1;
            this.val$lifecycle = r2;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner r1) {
            this.val$lifecycle.removeObserver(this);
        }
    }

    public MediaPlaybackManager(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        this.mHostDispatcher = r2;
        r3.addObserver(new AnonymousClass1(this, r3));
    }

    public static MediaPlaybackManager create(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        Objects.requireNonNull(r3);
        return new MediaPlaybackManager(r1, r2, r3);
    }
}
