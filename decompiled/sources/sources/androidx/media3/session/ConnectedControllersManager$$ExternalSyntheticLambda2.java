package androidx.media3.session;

import androidx.media3.session.ConnectedControllersManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ConnectedControllersManager f$0;
    public final /* synthetic */ ConnectedControllersManager.AsyncCommand f$1;
    public final /* synthetic */ AtomicBoolean f$2;
    public final /* synthetic */ ConnectedControllersManager.ConnectedControllerRecord f$3;
    public final /* synthetic */ AtomicBoolean f$4;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda2(ConnectedControllersManager r1, ConnectedControllersManager.AsyncCommand r2, AtomicBoolean r3, ConnectedControllersManager.ConnectedControllerRecord r4, AtomicBoolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConnectedControllersManager.m864$r8$lambda$I1DSu1H7ZLMrmiN2sHpNMsO75M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
