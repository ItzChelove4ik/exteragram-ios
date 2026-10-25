package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzgk extends zzgb {
    final /* synthetic */ zzgl zze;

    public /* synthetic */ zzgk(zzgl r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        super(r1);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r2, IBinder r3) {
        if (Objects.equals(r2, zzgl.zzt()) == false) goto L13;
        zzaq r22 = zzap.zzb(r3);
        r22.zzd(new zzgj(this));     // Catch: Throwable -> L7 RemoteException -> L9
        return;
    L7:
        e = move-exception;
        zzf(zzdo.zze(6, "AiCore service provider communication error.", e));
        return;
    L13:
        if (Objects.equals(r2, zzgl.zzq()) == false) goto L16;
        zze(zzam.zzb(r3));
        return;
    L16:
        zzf(zzdo.zze(0, "AiCore service is not connected. Unknown component ".concat(String.valueOf(r2)), null));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzgb
    public final void zzb() {
        Intent r0 = new Intent();
        r0.setComponent(zzgl.zzt());
        zzgl r1 = this.zze;
    L10:
        e = move-exception;
        zzf(zzdo.zze(601, "AiCore service failed to bind due to SecurityException.", e));
        return;
    L4:
        if (super.zzd(r0) == true) goto L12;
        r1.zza.unbindService(this);     // Catch: SecurityException -> L10
        Intent r02 = new Intent();     // Catch: SecurityException -> L10
        r02.setComponent(zzgl.zzq());     // Catch: SecurityException -> L10
        if (super.zzd(r02) == true) goto L17;
        zzf(zzdo.zze(601, "AiCore service failed to bind to primary or fallback.", null));     // Catch: SecurityException -> L10
        return;
    L17:
        return;
    }
}
