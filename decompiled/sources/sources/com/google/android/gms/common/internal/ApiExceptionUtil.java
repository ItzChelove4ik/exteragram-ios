package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class ApiExceptionUtil {
    public static ApiException fromStatus(Status r1) {
        if (r1.hasResolution() == false) goto L7;
        return new ResolvableApiException(r1);
    L7:
        return new ApiException(r1);
    }
}
