package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/impl/VideoUsageControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "reset", _UrlKt.FRAGMENT_ENCODE_SET, "isInVideoUsage", "()Z", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "Lkotlinx/atomicfu/AtomicInt;", "videoUsage", "Lkotlinx/atomicfu/AtomicInt;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVideoUsageControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VideoUsageControl.kt\nandroidx/camera/camera2/impl/VideoUsageControl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,73:1\n85#2,4:74\n85#2,4:78\n85#2,4:82\n85#2,4:86\n85#2,4:90\n*S KotlinDebug\n*F\n+ 1 VideoUsageControl.kt\nandroidx/camera/camera2/impl/VideoUsageControl\n*L\n37#1:74,4\n45#1:78,4\n47#1:82,4\n55#1:86,4\n60#1:90,4\n*E\n"})
/* loaded from: classes3.dex */
public final class VideoUsageControl implements UseCaseCameraControl {
    private UseCaseCameraRequestControl requestControl;
    private final AtomicInt videoUsage;

    public VideoUsageControl() {
        this.videoUsage = AtomicFU.atomic(0);
    }

    public final boolean isInVideoUsage() {
        int r3 = this.videoUsage.getValue();
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "isInVideoUsage: videoUsage = " + r3);
    L5:
        if (r3 <= 0) goto L8;
        return true;
    L8:
        return false;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        this.videoUsage.setValue(0);
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "reset: videoUsage = 0");
        return;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r1) {
        this.requestControl = r1;
    }
}
