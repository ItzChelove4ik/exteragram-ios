package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes5.dex */
public final class SignInOptions implements Api.ApiOptions {
    public static final SignInOptions zaa = null;
    private final boolean zab;
    private final boolean zac;
    private final String zad;
    private final boolean zae;
    private final String zaf;
    private final String zag;
    private final boolean zah;
    private final Long zai;
    private final Long zaj;

    static {
        zaa = new SignInOptions(false, false, null, false, null, null, false, null, null);
    }

    private SignInOptions(boolean r1, boolean r2, String r3, boolean r4, String r5, String r6, boolean r7, Long r8, Long r9) {
        this.zab = false;
        this.zac = false;
        this.zad = null;
        this.zae = false;
        this.zah = false;
        this.zaf = null;
        this.zag = null;
        this.zai = null;
        this.zaj = null;
    }

    public final boolean equals(Object r3) {
        if (r3 != this) goto L6;
        return true;
    L6:
        if ((r3 instanceof SignInOptions) == true) goto L9;
        return false;
    L9:
        if (Objects.equal(null, null) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equal(null, null) == false) goto L19;
        if (Objects.equal(null, null) == false) goto L19;
        if (Objects.equal(null, null) == false) goto L19;
        if (Objects.equal(null, null) == false) goto L19;
        return true;
    }

    public final int hashCode() {
        Boolean r0 = Boolean.FALSE;
        return Objects.hashCode(new Object[]{r0, r0, null, r0, r0, null, null, null, null});
    }
}
