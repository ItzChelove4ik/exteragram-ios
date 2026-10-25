package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.cast.zzeu;
import com.google.android.gms.internal.cast.zzez;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzw extends zzy {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzeu zzb;
    final /* synthetic */ CastRemoteDisplayClient zzc;
    final /* synthetic */ zzag zzd;

    public zzw(CastRemoteDisplayClient r1, TaskCompletionSource r2, zzeu r3, zzag r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzd = r4;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.zzey
    public final void zzb(int r11, int r12, Surface r13, ApiMetadata r14) {
        CastRemoteDisplayClient r0 = this.zzc;
        r0.zzb().d("onConnected", new Object[0]);
        DisplayManager r3 = (DisplayManager) r0.getApplicationContext().getSystemService("display");
        if (r3 != null) goto L6;
        Logger r122 = r0.zzb();
        r122.e("Unable to get the display manager", new Object[0]);
        TaskCompletionSource r10 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r10, r14);
        return;
    L6:
        r0.zza();
        r0.zzd(r3.createVirtualDisplay("private_display", r11, r12, (Math.min(r11, r12) * 320) / 1080, r13, 2));
        if (r0.zzc() != null) goto L10;
        Logger r123 = r0.zzb();
        r123.e("Unable to create virtual display", new Object[0]);
        TaskCompletionSource r102 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r102, r14);
        return;
    L10:
        Display r112 = r0.zzc().getDisplay();
        if (r112 != null) goto L18;
        Logger r124 = r0.zzb();
        r124.e("Virtual display does not have a display", new Object[0]);
        TaskCompletionSource r103 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r103, r14);
        return;
    L18:
        zzeu r125 = this.zzb;     // Catch: Throwable -> L16
        ((zzez) r125.getService()).zzh(this, r112.getDisplayId(), zzff.zza(r125.getContext()));     // Catch: Throwable -> L16
        return;
    L16:
        Logger r113 = this.zzc.zzb();
        r113.e("Unable to provision the route's new virtual Display", new Object[0]);
        TaskCompletionSource r104 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r104, r14);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.zzey
    public final void zzc(ApiMetadata r6) {
        CastRemoteDisplayClient r0 = this.zzc;
        r0.zzb().d("onConnectedWithDisplay", new Object[0]);
        if (r0.zzc() != null) goto L6;
        Logger r02 = r0.zzb();
        r02.e("There is no virtual display", new Object[0]);
        TaskCompletionSource r5 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r5, r6);
        return;
    L6:
        Display r1 = r0.zzc().getDisplay();
        if (r1 == null) goto L10;
        TaskCompletionSource r52 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, r1, r52, r6);
        return;
    L10:
        Logger r03 = r0.zzb();
        r03.e("Virtual display no longer has a display", new Object[0]);
        TaskCompletionSource r53 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r53, r6);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.zzey
    public final void zzd(int r4, ApiMetadata r5) {
        Object[] r42 = {Integer.valueOf(r4)};
        CastRemoteDisplayClient r0 = this.zzc;
        r0.zzb().d("onError: %d", r42);
        r0.zza();
        TaskCompletionSource r3 = this.zza;
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, r3, r5);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.zzey
    public final void zze(boolean r3, ApiMetadata r4) {
        Object[] r42 = {Boolean.valueOf(r3)};
        this.zzc.zzb().d("onRemoteDisplayMuteStateChanged: %b", r42);
        zzag r2 = this.zzd;
        if (r2 == null) goto L8;
        StringBuilder r0 = new StringBuilder(String.valueOf(r3).length() + 33);
        r0.append("onRemoteDisplayMuteStateChanged: ");
        r0.append(r3);
        String r43 = r0.toString();
        CastRemoteDisplayLocalService r22 = r2.zza;
        r22.zzg(r43);
        CastRemoteDisplayLocalService.Callbacks r23 = (CastRemoteDisplayLocalService.Callbacks) r22.zzl().get();
        if (r23 == null) goto L9;
        r23.onRemoteDisplayMuteStateChanged(r3);
        return;
    L9:
        return;
    }
}
