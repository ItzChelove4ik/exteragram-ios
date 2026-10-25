package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzaj extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    public zzaj(RemoteMediaClient r1, int r2, int r3, JSONObject r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        RemoteMediaClient r1 = this.zzd;
        MediaQueue r2 = r1.getMediaQueue();
        int r3 = this.zza;
        int r22 = r2.indexOfItemWithId(r3);
        int r4 = 0;
        if (r22 != (-1)) goto L12;
        MediaStatus r23 = (MediaStatus) Preconditions.checkNotNull(r1.getMediaStatus());
        int r6 = 0;
    L6:
        if (r6 >= r23.getQueueItemCount()) goto L11;
        if (((MediaQueueItem) Preconditions.checkNotNull(r23.getQueueItem(r6))).getItemId() == r3) goto L9;
        r6 = r6 + 1;
        goto L6
    L9:
        r22 = r6;
        goto L12
    L11:
        r22 = -1;
    L12:
        int r5 = this.zzb;
        if (r5 >= 0) goto L16;
        setResult(new zzbb(this, new Status(CastStatusCodes.INVALID_REQUEST, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", new Object[]{Integer.valueOf(r5)}))));
        return;
    L16:
        if (r22 != r5) goto L19;
        setResult(new zzbb(this, new Status(0)));
        return;
    L19:
        if (r5 <= r22) goto L21;
        r5 = r5 + 1;
    L21:
        Preconditions.checkMainThread("Must be called from the main thread.");
        int r0 = r1.getMediaQueue().itemIdAtIndex(r5);
        if (r0 == 0) goto L24;
        r4 = r0;
    L30:
        r1.zzt().zzG(zzb(), new int[]{r3}, r4, this.zzc);
        return;
    L24:
        MediaStatus r02 = r1.getMediaStatus();
        if (r02 == null) goto L30;
        MediaQueueItem r03 = r02.getQueueItem(r5);
        if (r03 == null) goto L30;
        r4 = r03.getItemId();
        goto L30
    }
}
