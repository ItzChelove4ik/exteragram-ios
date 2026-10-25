package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzr implements OnFailureListener {
    public /* synthetic */ zzr() {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r2) {
        Log.e("OptionalModuleUtils", "Failed to check feature availability", r2);
    }
}
