package androidx.car.app;

import androidx.car.app.managers.Manager;
import androidx.car.app.managers.ManagerFactory;
import androidx.lifecycle.Lifecycle;

/* loaded from: classes3.dex */
public final /* synthetic */ class CarContext$$ExternalSyntheticLambda2 implements ManagerFactory {
    public final /* synthetic */ CarContext f$0;
    public final /* synthetic */ Lifecycle f$1;

    public /* synthetic */ CarContext$$ExternalSyntheticLambda2(CarContext r1, Lifecycle r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.car.app.managers.ManagerFactory
    public final Manager create() {
        return CarContext.$r8$lambda$uI7jlReeLpvE9gQT_cKxXLPvj4w(this.f$0, this.f$1);
    }
}
