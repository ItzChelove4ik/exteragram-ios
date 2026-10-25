package androidx.car.app.managers;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.car.app.CarAppMetadataHolderService;
import androidx.car.app.CarContext;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public interface ResultManager extends Manager {
    static ResultManager create(CarContext r2) {
        Bundle r22 = CarAppMetadataHolderService.getServiceInfo(r2).metaData;     // Catch: Throwable -> L12
        if (r22 == null) goto L6;
        String r23 = r22.getString("androidx.car.app.CarAppMetadataHolderService.RESULT_MANAGER");     // Catch: Throwable -> L12
    L7:
        if (r23 == null) goto L11;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Class.forName(r23).getConstructor(null).newInstance(null));     // Catch: Throwable -> L12
        return null;
    L11:
        throw new ClassNotFoundException("ResultManager metadata could not be found");     // Catch: Throwable -> L12
    L6:
        r23 = null;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("ResultManager not configured. Did you forget to add a dependency on the app-automotive artifact?");
        return null;
    }
}
