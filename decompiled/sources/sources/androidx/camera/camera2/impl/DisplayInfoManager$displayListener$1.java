package androidx.camera.camera2.impl;

import android.hardware.display.DisplayManager;
import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"androidx/camera/camera2/impl/DisplayInfoManager$displayListener$1", "Landroid/hardware/display/DisplayManager$DisplayListener;", "onDisplayAdded", _UrlKt.FRAGMENT_ENCODE_SET, "displayId", _UrlKt.FRAGMENT_ENCODE_SET, "onDisplayRemoved", "onDisplayChanged", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DisplayInfoManager$displayListener$1 implements DisplayManager.DisplayListener {
    final /* synthetic */ DisplayInfoManager this$0;

    public DisplayInfoManager$displayListener$1(DisplayInfoManager r1) {
        this.this$0 = r1;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int r2) {
        Object r22 = DisplayInfoManager.access$getLock$p(this.this$0);
        DisplayInfoManager r1 = this.this$0;
        monitor-enter(r22);
        DisplayInfoManager.access$setDisplays$p(r1, null);     // Catch: Throwable -> L8
        DisplayInfoManager.access$setPreviewSize$p(r1, null);     // Catch: Throwable -> L8
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r22);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int r2) {
        Object r22 = DisplayInfoManager.access$getLock$p(this.this$0);
        DisplayInfoManager r1 = this.this$0;
        monitor-enter(r22);
        DisplayInfoManager.access$setDisplays$p(r1, null);     // Catch: Throwable -> L8
        DisplayInfoManager.access$setPreviewSize$p(r1, null);     // Catch: Throwable -> L8
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r22);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int r2) {
        Object r22 = DisplayInfoManager.access$getLock$p(this.this$0);
        DisplayInfoManager r1 = this.this$0;
        monitor-enter(r22);
        DisplayInfoManager.access$setDisplays$p(r1, null);     // Catch: Throwable -> L8
        DisplayInfoManager.access$setPreviewSize$p(r1, null);     // Catch: Throwable -> L8
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r22);
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
