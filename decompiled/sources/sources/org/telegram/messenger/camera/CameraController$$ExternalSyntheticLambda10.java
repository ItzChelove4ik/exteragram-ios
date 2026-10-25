package org.telegram.messenger.camera;

import android.hardware.Camera;
import java.io.File;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraController$$ExternalSyntheticLambda10 implements Camera.PictureCallback {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ CameraInfo f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ CameraController$$ExternalSyntheticLambda10(File r1, CameraInfo r2, boolean r3, boolean r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.hardware.Camera.PictureCallback
    public final void onPictureTaken(byte[] r8, Camera r9) {
        CameraController.$r8$lambda$9kHymWf0mUGY67vDhLE1WCXqQDM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
