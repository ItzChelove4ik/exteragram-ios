package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.wearable.Wearable;

/* loaded from: classes5.dex */
abstract class zzu extends BaseImplementation$ApiMethodImpl {
    public zzu(GoogleApiClient r2) {
        super(Wearable.API, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
    public final /* bridge */ /* synthetic */ void setResult(Object r1) {
        super.setResult((Result) r1);
    }
}
