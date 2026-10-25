package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzm extends SessionTransferCallback {
    final /* synthetic */ zzn zza;

    public zzm(zzn r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferFailed(int r4, int r5) {
        int r0 = zzn.$r8$clinit;
        Object[] r02 = {Integer.valueOf(r4), Integer.valueOf(r5)};
        zzn.zzi().d("onTransferFailed with type = %d and reason = %d", r02);
        zzn r3 = this.zza;
        r3.zze();
        zzp r03 = r3.zzk();
        zzo r1 = r3.zzm();
        r3.zzj().zzd(r03.zzg(r1, r4, r5), 232);
        r3.zzp(false);
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferred(int r3, SessionState r4) {
        int r42 = zzn.$r8$clinit;
        Object[] r43 = {Integer.valueOf(r3)};
        zzn.zzi().d("onTransferred with type = %d", r43);
        zzn r2 = this.zza;
        r2.zze();
        zzp r44 = r2.zzk();
        zzo r0 = r2.zzm();
        r2.zzj().zzd(r44.zzf(r0, r3), 231);
        r2.zzp(false);
        r2.zzn(null);
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferring(int r4) {
        int r0 = zzn.$r8$clinit;
        Object[] r02 = {Integer.valueOf(r4)};
        zzn.zzi().d("onTransferring with type = %d", r02);
        zzn r3 = this.zza;
        r3.zzp(true);
        r3.zze();
        zzp r03 = r3.zzk();
        zzo r1 = r3.zzm();
        r3.zzj().zzd(r03.zzf(r1, r4), 230);
    }
}
