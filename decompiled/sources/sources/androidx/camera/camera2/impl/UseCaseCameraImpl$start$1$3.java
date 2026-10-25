package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.core.Logger;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCamera.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCamera.kt\nandroidx/camera/camera2/impl/UseCaseCameraImpl$start$1$3\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,229:1\n146#2,4:230\n*S KotlinDebug\n*F\n+ 1 UseCaseCamera.kt\nandroidx/camera/camera2/impl/UseCaseCameraImpl$start$1$3\n*L\n124#1:230,4\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraImpl$start$1$3 implements Function1<Throwable, Unit> {
    public static final UseCaseCameraImpl$start$1$3 INSTANCE = null;

    static {
        INSTANCE = new UseCaseCameraImpl$start$1$3();
    }

    public UseCaseCameraImpl$start$1$3() {
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if ((r2 instanceof CancellationException) == true) goto L10;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L11;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Surface setup error!", r2);
        return;
    L11:
        return;
    }
}
