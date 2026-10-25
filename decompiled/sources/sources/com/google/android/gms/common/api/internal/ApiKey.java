package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class ApiKey<O extends Api.ApiOptions> {
    private final int zaa;
    private final Api zab;
    private final Api.ApiOptions zac;
    private final String zad;

    private ApiKey(Api r1, Api.ApiOptions r2, String r3) {
        this.zab = r1;
        this.zac = r2;
        this.zad = r3;
        this.zaa = Objects.hashCode(new Object[]{r1, r2, r3});
    }

    public static <O extends Api.ApiOptions> ApiKey<O> getSharedApiKey(Api<O> r1, O r2, String r3) {
        return new ApiKey(r1, r2, r3);
    }

    public final boolean equals(Object r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (r5 != this) goto L9;
        return true;
    L9:
        if ((r5 instanceof ApiKey) == true) goto L11;
        return false;
    L11:
        ApiKey r52 = (ApiKey) r5;
        if (Objects.equal(this.zab, r52.zab) == true) goto L14;
    L18:
        return false;
    L14:
        if (Objects.equal(this.zac, r52.zac) == false) goto L18;
        if (Objects.equal(this.zad, r52.zad) == false) goto L18;
        return true;
    }

    public final int hashCode() {
        return this.zaa;
    }

    public final String zaa() {
        return this.zab.zad();
    }
}
