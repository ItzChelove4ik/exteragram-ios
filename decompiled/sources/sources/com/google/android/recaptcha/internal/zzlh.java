package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import java.util.List;
import kotlin.collections.CollectionsKt;

/* loaded from: classes5.dex */
public final class zzlh implements zzlg {
    private final Application zza;
    private final StandardIntegrityManager zzb;
    private final zzgp zzc;
    private final zzon zzd;
    private final zzdv zze;
    private final zzip zzf;
    private final zzhq zzg;
    private final zzmv zzh;
    private final zzhr zzi;
    private final zzgy zzj;
    private final zzhu zzk;

    public zzlh(Application r1, zzii r2, zzgy r3, zzhu r4, StandardIntegrityManager r5, zzgp r6, zzon r7, zzhr r8, zzdv r9, zzip r10, zzhq r11, zzmv r12) {
        this.zza = r1;
        this.zzj = r3;
        this.zzk = r4;
        this.zzb = r5;
        this.zzc = r6;
        this.zzd = r7;
        this.zzi = r8;
        this.zze = r9;
        this.zzf = r10;
        this.zzg = r11;
        this.zzh = r12;
    }

    private final zzcx zzb(boolean r12) {
        List r0 = CollectionsKt.createListBuilder();
        r0.add(zzc(r12));
        zzha r4 = new zzha();
        zzfw r6 = new zzfw();
        zzgp r7 = this.zzc;
        zzhr r8 = this.zzi;
        zzmv r9 = this.zzh;
        zzhq r10 = this.zzg;
        zzgy r5 = this.zzj;
        r0.add(new zzgo(this.zza, this.zzk, r4, r5, r6, r7, r8, r9, r10));
        r0.add(this.zzf.zza());
        return new zzcx(new zzct(CollectionsKt.build(r0)));
    }

    private final zzeo zzc(boolean r8) {
        Application r0 = this.zza;
        return zzei.zza(r0, this.zzk, r0.getContentResolver(), this.zzb, this.zzc, this.zzi, r8);
    }

    @Override // com.google.android.recaptcha.internal.zzlg
    public final zzdp zza(boolean r8) {
        if (r8 == false) goto L7;
        zzcx r2 = zzb(false);
        zzct r3 = new zzct(CollectionsKt.listOf(zzc(true)));
        zzon r82 = this.zzd;
        zzdv r5 = this.zze;
        zzhu r6 = this.zzk;
        return new zzdo(r2, r3, r82.zzb(), r5, r6);
    L7:
        return zzb(true);
    }
}
