package org.telegram.ui.Stories.recorder;

import android.location.LocationListener;
import android.location.LocationManager;

/* loaded from: classes3.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ LocationListener[] f$0;
    public final /* synthetic */ LocationManager f$1;
    public final /* synthetic */ Runnable[] f$2;

    public /* synthetic */ Weather$$ExternalSyntheticLambda7(LocationListener[] r1, LocationManager r2, Runnable[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Weather.$r8$lambda$4S3GuGxOiu6SPlKA4XuA0c1MPc8(this.f$0, this.f$1, this.f$2);
    }
}
