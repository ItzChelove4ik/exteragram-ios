package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionProvider;

/* loaded from: classes4.dex */
public final class zzba extends SessionProvider {
    private final CastOptions zza;
    private final zzbx zzb;

    public zzba(Context r3, CastOptions r4, zzbx r5) {
        if (r4.getSupportedNamespaces().isEmpty() == false) goto L5;
        String r0 = CastMediaControlIntent.categoryForCast(r4.getReceiverApplicationId());
    L6:
        super(r3, r0);
        this.zza = r4;
        this.zzb = r5;
        return;
    L5:
        r0 = CastMediaControlIntent.categoryForCast(r4.getReceiverApplicationId(), r4.getSupportedNamespaces());
        goto L6
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final Session createSession(String r8) {
        Context r1 = getContext();
        String r2 = getCategory();
        Context r3 = getContext();
        CastOptions r4 = this.zza;
        zzbx r5 = this.zzb;
        return new CastSession(r1, r2, r8, r4, r5, new com.google.android.gms.cast.framework.media.internal.zzs(r3, r4, r5));
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final boolean isSessionRecoverable() {
        return this.zza.getResumeSavedSession();
    }
}
