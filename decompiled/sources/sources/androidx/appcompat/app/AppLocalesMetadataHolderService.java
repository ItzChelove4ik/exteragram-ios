package androidx.appcompat.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;

/* loaded from: classes3.dex */
public abstract class AppLocalesMetadataHolderService extends Service {

    public static class Api24Impl {
        public static int getDisabledComponentFlag() {
            return 512;
        }
    }

    public static ServiceInfo getServiceInfo(Context r4) {
        int r0 = Api24Impl.getDisabledComponentFlag() | 128;
        return r4.getPackageManager().getServiceInfo(new ComponentName(r4, AppLocalesMetadataHolderService.class), r0);
    }
}
