package androidx.car.app;

import androidx.activity.result.ActivityResultCallback;
import java.util.Map;

/* loaded from: classes3.dex */
public final /* synthetic */ class CarAppPermissionActivity$$ExternalSyntheticLambda0 implements ActivityResultCallback {
    public final /* synthetic */ CarAppPermissionActivity f$0;
    public final /* synthetic */ IOnRequestPermissionsListener f$1;

    public /* synthetic */ CarAppPermissionActivity$$ExternalSyntheticLambda0(CarAppPermissionActivity r1, IOnRequestPermissionsListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object r2) {
        CarAppPermissionActivity.$r8$lambda$k1ognOoCW2FmgYy8_myqLBJYZBA(this.f$0, this.f$1, (Map) r2);
    }
}
