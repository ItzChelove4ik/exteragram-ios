package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class zzhy {
    public zzhy() {
    }

    public static zzhx zzx() {
        zzhh r0 = new zzhh();
        r0.zzb(0.1f);
        r0.zzc(3);
        r0.zze(zzml.zzk());
        r0.zzf(0);
        r0.zzg(0);
        r0.zzk(0);
        r0.zzl(0);
        r0.zzm(0);
        r0.zzj(true);
        r0.zzh(1);
        r0.zzn(123);
        r0.zzo(zzeb.zza);
        r0.zzp(0);
        r0.zzd(1.0f);
        r0.zzs(false);
        r0.zzt(zzja.zza);
        return r0;
    }

    public abstract zzml zza();

    public abstract float zzb();

    public abstract int zzc();

    public abstract float zzd();

    public abstract zzml zze();

    public abstract int zzf();

    public abstract int zzg();

    public abstract int zzh();

    public abstract zzik zzi();

    public abstract boolean zzj();

    public abstract int zzk();

    public abstract int zzl();

    public abstract int zzm();

    public abstract File zzn();

    public abstract File zzo();

    public abstract File zzp();

    public abstract int zzq();

    public abstract zzeb zzr();

    public abstract int zzs();

    public abstract zzhu zzt();

    public abstract zzib zzu();

    public abstract boolean zzv();

    public abstract zzja zzw();

    public final zzcg zzy(Context r10, Executor r11) {
        zzcf r102 = zzcg.zza();
        zzhu r112 = zzt();
        zzdf r0 = null;
        if (r112 == null) goto L20;
        ParcelFileDescriptor r2 = r112.zzb();
        zzla.zzd(r2, "Cache file should not be null");
        long r3 = r2.getStatSize();
        long r5 = r2.getStatSize();
        if (r3 > 209715200) goto L18;
        zzml r113 = r112.zza();
        boolean r4 = true;
        if (r113.size() <= 1) goto L10;
        r4 = false;
    L10:
        zzla.zza(r4, "Only single message is supported for now.");
        ArrayList r32 = new ArrayList();
        int r42 = r113.size();
        int r52 = 0;
    L11:
        if (r52 >= r42) goto L17;
        zzhs r6 = (zzhs) r113.get(r52);
        if (r6.zzh() != 0) goto L15;
        int r7 = r6.zza();
        String r62 = r6.zzb();
        r62.getClass();
        r32.add(zzbz.zzd(r7, r62));
        r52 = r52 + 1;
        goto L11
    L15:
        int r9 = r6.zzh();
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r9).length() + 52, "Only TEXT type is supported for now. Received type: ", r9);
        return null;
    L17:
        r102.zzp(zzcb.zzb(r32, r2));
        goto L20
    L18:
        f$$ExternalSyntheticBUOutline1.m(zzlb.zzd("Cache file should be less than %s bytes: %s", new Object[]{209715200, Long.valueOf(r5)}));
        return null;
    L20:
        zzib r114 = zzu();
        if (r114 == null) goto L23;
        r102.zzs(r114.zzw());
    L23:
        zzik r115 = zzi();
        if (r115 == null) goto L26;
        r102.zzg(new zzhw(this, r115));
    L26:
        Bundle r116 = new Bundle();
        int r22 = zzml.$r8$clinit;
        zzmh r23 = new zzmh();
        new ArrayList();
        zzml r33 = zza();
        int r43 = r33.size();
        int r53 = 0;
    L27:
        if (r53 >= r43) goto L38;
        zzhs r63 = (zzhs) r33.get(r53);
        if (r63.zzh() == 0) goto L35;
        r63.zze();
        int r72 = r63.zza();
        Bitmap r64 = r63.zzc();
        if (r64 != null) goto L33;
        Bitmap r65 = null;
    L34:
        r65.getClass();
        r23.zzd(zzbz.zzc(r72, r65, false));
    L36:
        r53 = r53 + 1;
        goto L27
    L33:
        r65 = r64.asShared();
        goto L34
    L35:
        int r73 = r63.zza();
        String r66 = r63.zzb();
        r66.getClass();
        r23.zzd(zzbz.zzd(r73, r66));
        goto L36
    L38:
        if (r116.hasFileDescriptors() == false) goto L40;
        r102.zzl(r116);
    L40:
        r102.zza(r23.zze());
        r102.zzb(zzb());
        r102.zzc(zzc());
        r102.zzr(zzd());
        r102.zzd(zze());
        r102.zze(zzf());
        r102.zzf(zzg());
        r102.zzh(zzj());
        r102.zzi(zzh());
        r102.zzj(zzk());
        r102.zzk(zzl());
        r102.zzq(0);
        r102.zzm(zzq());
        r102.zzn(zzcv.zza());
        r102.zzo(0);
        r102.zzt(zzv());
        if (zzw().zza().isEmpty() == true) goto L46;
        zzja r92 = zzw();
        zzmh r117 = new zzmh();
        zzml r93 = r92.zza();
        if (r93.size() > 0) goto L48;
        r0 = new zzdf(r117.zze());
        goto L46
    L48:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r93.get(0));
        throw null;
    L46:
        r102.zzu(r0);
        return r102.zzv();
    }
}
