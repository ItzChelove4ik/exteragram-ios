package androidx.media3.session;

import androidx.media3.session.ConnectedControllersManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ConnectedControllersManager f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ ConnectedControllersManager.ConnectedControllerRecord f$2;
    public final /* synthetic */ AtomicBoolean f$3;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda3(ConnectedControllersManager r1, AtomicBoolean r2, ConnectedControllersManager.ConnectedControllerRecord r3, AtomicBoolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConnectedControllersManager.m863$r8$lambda$BTX49EHhVm6N_fSIVxVw5T6308(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
