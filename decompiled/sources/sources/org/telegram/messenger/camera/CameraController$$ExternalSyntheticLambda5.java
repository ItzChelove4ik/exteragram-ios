package org.telegram.messenger.camera;

import android.hardware.Camera;
import java.io.File;
import org.telegram.messenger.camera.CameraController;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraController$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ CameraController f$0;
    public final /* synthetic */ Camera f$1;
    public final /* synthetic */ CameraSession f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ File f$4;
    public final /* synthetic */ CameraInfo f$5;
    public final /* synthetic */ CameraController.VideoTakeCallback f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ CameraController$$ExternalSyntheticLambda5(CameraController r1, Camera r2, CameraSession r3, boolean r4, File r5, CameraInfo r6, CameraController.VideoTakeCallback r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraController.m5066$r8$lambda$9SwEbrMDAQ1JmesSwnxXdTkzE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
