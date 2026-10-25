package androidx.car.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.IBinder;

/* loaded from: classes3.dex */
public class CarAppMetadataHolderService extends Service {

    public static class Api24Impl {
        public static int getDisabledComponentFlag() {
            return 512;
        }
    }

    private CarAppMetadataHolderService() {
    }

    public static ServiceInfo getServiceInfo(Context r4) {
        int r0 = Api24Impl.getDisabledComponentFlag() | 128;
        return r4.getPackageManager().getServiceInfo(new ComponentName(r4, CarAppMetadataHolderService.class), r0);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        throw new UnsupportedOperationException();
    }
}
