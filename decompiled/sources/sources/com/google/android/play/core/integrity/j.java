package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;

/* loaded from: classes5.dex */
public final class j implements k {
    public j() {
    }

    @Override // com.google.android.play.core.integrity.k
    public final ApiException a(Bundle r2) {
        int r1 = r2.getInt("error");
        if (r1 != 0) goto L6;
        return null;
    L6:
        return new StandardIntegrityException(r1, null);
    }
}
