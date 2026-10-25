package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.Action;

/* loaded from: classes5.dex */
public final class AssistActionBuilder extends Action.Builder {
    private String zza;

    public AssistActionBuilder() {
        super("AssistAction");
    }

    @Override // com.google.firebase.appindexing.Action.Builder
    public Action build() {
        Preconditions.checkNotNull(this.zza, "setActionToken is required before calling build().");
        Preconditions.checkNotNull(zzc(), "setActionStatus is required before calling build().");
        put("actionToken", new String[]{this.zza});
        if (zza() != null) goto L6;
        setName("AssistAction");
    L6:
        if (zzb() != null) goto L13;
        String r0 = String.valueOf(this.zza);
        if (r0.length() == 0) goto L10;
        String r02 = "https://developers.google.com/actions?invocation=".concat(r0);
    L11:
        setUrl(r02);
        goto L13
    L10:
        r02 = new String("https://developers.google.com/actions?invocation=");
    L13:
        return super.build();
    }

    public AssistActionBuilder setActionToken(String r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
        return this;
    }
}
