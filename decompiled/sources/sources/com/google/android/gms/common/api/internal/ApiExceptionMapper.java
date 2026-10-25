package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

/* loaded from: classes4.dex */
public class ApiExceptionMapper implements StatusExceptionMapper {
    public ApiExceptionMapper() {
    }

    @Override // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public final Exception getException(Status r1) {
        return ApiExceptionUtil.fromStatus(r1);
    }
}
