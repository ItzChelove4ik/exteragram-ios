package androidx.camera.camera2.config;

import androidx.camera.camera2.adapter.GraphStateToCameraStateAdapter;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.config.UseCaseCameraConfig;
import androidx.camera.camera2.impl.CameraGraphConfigProvider;
import androidx.camera.core.impl.SessionProcessor;
import kotlin.jvm.functions.Function0;

/* loaded from: classes3.dex */
public final /* synthetic */ class UseCaseCameraConfig$Companion$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ SessionConfigAdapter f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ CameraGraphConfigProvider f$3;
    public final /* synthetic */ GraphStateToCameraStateAdapter f$4;

    public /* synthetic */ UseCaseCameraConfig$Companion$$ExternalSyntheticLambda0(SessionConfigAdapter r1, boolean r2, SessionProcessor r3, CameraGraphConfigProvider r4, GraphStateToCameraStateAdapter r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return UseCaseCameraConfig.Companion.m39$r8$lambda$oX4lbhDoh8cSXf19LhLAjDjp3A(this.f$0, this.f$1, null, this.f$3, this.f$4);
    }
}
