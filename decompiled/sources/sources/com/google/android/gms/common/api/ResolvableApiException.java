package com.google.android.gms.common.api;

import android.app.Activity;

/* loaded from: classes4.dex */
public class ResolvableApiException extends ApiException {
    public ResolvableApiException(Status r1) {
        super(r1);
    }

    public void startResolutionForResult(Activity r1, int r2) {
        getStatus().startResolutionForResult(r1, r2);
    }
}
