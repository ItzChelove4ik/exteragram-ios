package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcp extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    public zzcp(RemoteMediaPlayer r1, GoogleApiClient r2, int r3, int r4, JSONObject r5) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r7) {
        RemoteMediaPlayer r72 = this.zzd;
        int r0 = this.zza;
        int r1 = r72.zza(r0);
        int r3 = 0;
        if (r1 != (-1)) goto L6;
        setResult(new zzdb(this, new Status(0)));
        return;
    L6:
        int r2 = this.zzb;
        if (r2 >= 0) goto L10;
        setResult(new zzdb(this, new Status(CastStatusCodes.INVALID_REQUEST, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", new Object[]{Integer.valueOf(r2)}))));
        return;
    L10:
        if (r1 != r2) goto L13;
        setResult(new zzdb(this, new Status(0)));
        return;
    L13:
        MediaStatus r5 = r72.getMediaStatus();
        if (r5 != null) goto L17;
        setResult(new zzdb(this, new Status(CastStatusCodes.INVALID_REQUEST, String.format(Locale.ROOT, "Invalid request: Invalid MediaStatus", new Object[0]))));
        return;
    L17:
        if (r2 <= r1) goto L19;
        r2 = r2 + 1;
    L19:
        MediaQueueItem r12 = r5.getQueueItem(r2);
        if (r12 == null) goto L22;
        r3 = r12.getItemId();
    L22:
        r72.zzg().zzG(zzb(), new int[]{r0}, r3, this.zzc);
    }
}
