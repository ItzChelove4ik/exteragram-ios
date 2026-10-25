package com.google.android.gms.internal.cast;

import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzep extends zzeo {
    final /* synthetic */ zzer zza;
    private final zzew zzb;

    public zzep(zzer r1, zzew r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.cast.zzeo, com.google.android.gms.internal.cast.zzey
    public final void zzb(int r11, int r12, Surface r13, ApiMetadata r14) {
        zzet.zzb().d("onConnected", new Object[0]);
        zzew r142 = this.zzb;
        DisplayManager r2 = (DisplayManager) r142.getContext().getSystemService("display");
        if (r2 != null) goto L6;
        Logger r122 = zzet.zzb();
        r122.e("Unable to get the display manager", new Object[0]);
        this.zza.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
        return;
    L6:
        zzer r1 = this.zza;
        zzet r9 = r1.zzc;
        r9.zza();
        if (r11 >= r12) goto L9;
        int r3 = r11;
    L10:
        r9.zze(r2.createVirtualDisplay("private_display", r11, r12, (r3 * 320) / 1080, r13, 2));
        if (r9.zzd() != null) goto L15;
        Logger r112 = zzet.zzb();
        r112.e("Unable to create virtual display", new Object[0]);
        r1.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
        return;
    L15:
        if (r9.zzd().getDisplay() != null) goto L22;
        Logger r113 = zzet.zzb();
        r113.e("Virtual display does not have a display", new Object[0]);
        r1.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
        return;
    L22:
        int r114 = r9.zzd().getDisplay().getDisplayId();     // Catch: Throwable -> L18
        ((zzez) r142.getService()).zzh(this, r114, zzff.zza(r142.getContext()));     // Catch: Throwable -> L18
        return;
    L18:
        Logger r123 = zzet.zzb();
        r123.e("Unable to provision the route's new virtual Display", new Object[0]);
        this.zza.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
        return;
    L9:
        r3 = r12;
        goto L10
    }

    @Override // com.google.android.gms.internal.cast.zzeo, com.google.android.gms.internal.cast.zzey
    public final void zzc(ApiMetadata r4) {
        zzet.zzb().d("onConnectedWithDisplay", new Object[0]);
        zzer r3 = this.zza;
        zzet r42 = r3.zzc;
        if (r42.zzd() != null) goto L6;
        Logger r0 = zzet.zzb();
        r0.e("There is no virtual display", new Object[0]);
        r3.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
        return;
    L6:
        Display r43 = r42.zzd().getDisplay();
        if (r43 == null) goto L10;
        r3.setResult(new zzes(r43));
        return;
    L10:
        Logger r02 = zzet.zzb();
        r02.e("Virtual display no longer has a display", new Object[0]);
        r3.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
    }

    @Override // com.google.android.gms.internal.cast.zzey
    public final void zzd(int r2, ApiMetadata r3) {
        int r32 = zzet.$r8$clinit;
        Object[] r22 = {Integer.valueOf(r2)};
        zzet.zzb().d("onError: %d", r22);
        zzer r1 = this.zza;
        r1.zzc.zza();
        r1.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
    }
}
