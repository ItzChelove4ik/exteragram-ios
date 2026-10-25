package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepName;

@KeepName
/* loaded from: classes4.dex */
public final class GooglePlayServicesIncorrectManifestValueException extends GooglePlayServicesManifestException {
    public GooglePlayServicesIncorrectManifestValueException(int r4) {
        int r0 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        int r1 = String.valueOf(r0).length();
        int r12 = r1 + 104;
        StringBuilder r2 = new StringBuilder((r12 + String.valueOf(r4).length()) + 194);
        r2.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
        r2.append(r0);
        r2.append(" but found ");
        r2.append(r4);
        r2.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        super(r4, r2.toString());
    }
}
