package androidx.car.app;

import androidx.car.app.managers.Manager;
import androidx.car.app.managers.ManagerFactory;
import androidx.lifecycle.Lifecycle;

/* loaded from: classes3.dex */
public final /* synthetic */ class CarContext$$ExternalSyntheticLambda6 implements ManagerFactory {
    public final /* synthetic */ CarContext f$0;
    public final /* synthetic */ HostDispatcher f$1;
    public final /* synthetic */ Lifecycle f$2;

    public /* synthetic */ CarContext$$ExternalSyntheticLambda6(CarContext r1, HostDispatcher r2, Lifecycle r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.car.app.managers.ManagerFactory
    public final Manager create() {
        return CarContext.$r8$lambda$1IuE5DcaI6ei0ccTfAZBaHqwYWc(this.f$0, this.f$1, this.f$2);
    }
}
