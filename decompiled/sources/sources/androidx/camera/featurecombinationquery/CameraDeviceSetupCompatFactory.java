package androidx.camera.featurecombinationquery;

import android.content.Context;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class CameraDeviceSetupCompatFactory {
    private CameraDeviceSetupCompatProvider mCamera2Provider;
    private final Context mContext;
    private CameraDeviceSetupCompatProvider mPlayServicesProvider;

    public CameraDeviceSetupCompatFactory(Context r3) {
        this.mContext = r3;
        if (Build.VERSION.SDK_INT < 35) goto L5;
        this.mCamera2Provider = new Camera2CameraDeviceSetupCompatProvider(r3);
    L5:
        this.mPlayServicesProvider = getPlayServicesCameraDeviceSetupCompatProvider();
    }

    private CameraDeviceSetupCompatProvider getPlayServicesCameraDeviceSetupCompatProvider() {
        ServiceInfo[] r1 = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 132).services;
        if (r1 != null) goto L7;
        return null;
    L7:
        int r2 = r1.length;
        int r3 = 0;
        String r4 = null;
    L8:
        if (r3 >= r2) goto L19;
        Bundle r5 = r1[r3].metaData;
        if (r5 == null) goto L18;
        String r52 = r5.getString("androidx.camera.featurecombinationquery.PLAY_SERVICES_IMPL_PROVIDER_KEY");
        if (r52 == null) goto L18;
        if (r4 != null) goto L16;
        r4 = r52;
        goto L18
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("Multiple Play Services CameraDeviceSetupCompat implementations found in the manifest.");
        return null;
    L18:
        r3 = r3 + 1;
        goto L8
    L19:
        if (r4 != null) goto L22;
        return null;
    L22:
        return instantiatePlayServicesImplProvider(r4);
    L23:
        return null;
    }

    private CameraDeviceSetupCompatProvider instantiatePlayServicesImplProvider(String r2) {
        return (CameraDeviceSetupCompatProvider) Class.forName(r2).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.mContext});
    L4:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to instantiate Play Services CameraDeviceSetupCompat implementation", e);
        return null;
    }

    public CameraDeviceSetupCompat getCameraDeviceSetupCompat(String r3) {
        ArrayList r0 = new ArrayList();
        CameraDeviceSetupCompatProvider r1 = this.mPlayServicesProvider;
        if (r1 == null) goto L5;
        r0.add(r1.getCameraDeviceSetupCompat(r3));
    L5:
        CameraDeviceSetupCompatProvider r2 = this.mCamera2Provider;
        if (r2 == null) goto L9;
        r0.add(r2.getCameraDeviceSetupCompat(r3));     // Catch: UnsupportedOperationException -> L10
    L9:
        return new AggregatedCameraDeviceSetupCompat(r0);
    }
}
