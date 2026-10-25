package androidx.camera.camera2.pipe.compat;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* synthetic */ class PruningCamera2DeviceManager$queue$1 extends FunctionReferenceImpl implements Function1<List<CameraRequest>, Unit> {
    public PruningCamera2DeviceManager$queue$1(Object r8) {
        super(1, r8, PruningCamera2DeviceManager.class, "prune", "prune$camera_camera2_pipe(Ljava/util/List;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(List<CameraRequest> r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(List<CameraRequest> r1) {
        ((PruningCamera2DeviceManager) this.receiver).prune$camera_camera2_pipe(r1);
    }
}
