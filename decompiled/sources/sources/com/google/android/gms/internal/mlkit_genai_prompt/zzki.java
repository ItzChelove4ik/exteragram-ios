package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public abstract class zzki {
    private static final zzkh zza = null;
    private static final zzkh zzb = null;
    private static final zzkh zzc = null;
    private static final zzkh zzd = null;
    private static final zzkh zze = null;
    private static final zzkh zzf = null;
    private static final zzkh zzg = null;

    static {
        zza = new zzkh("engine", zzke.zza, zzjf.zza, zzkg.zza, zzkf.zza, null);
        zzb = new zzkh("model", zzjq.zza, zzjw.zza, zzjx.zza, zzjy.zza, null);
        zzc = new zzkh("lora", zzjz.zza, zzka.zza, zzkb.zza, zzkc.zza, null);
        zzd = new zzkh("drafter", zzkd.zza, zzjg.zza, zzjh.zza, zzji.zza, null);
        zze = new zzkh("image_encoder", zzjj.zza, zzjk.zza, zzjl.zza, zzjm.zza, null);
        zzf = new zzkh("audio_encoder", zzjn.zza, zzjo.zza, zzjp.zza, zzjr.zza, null);
        zzg = new zzkh("kv_cache", zzjs.zza, zzjt.zza, zzju.zza, zzjv.zza, null);
    }

    public static String zza(zzd r9) {
        if (r9 != null) goto L5;
        return "{}";
    L5:
        zzc r92 = (zzc) r9.zzK();
        StringBuilder r0 = new StringBuilder();
        r0.append("{");
        int r2 = 1;
    L6:
        switch(r2) {
            case 0: goto L15;
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L7:
        int r3 = 0;
    L16:
        if (r3 == 0) goto L35;
        switch((r3 - 1)) {
            case 1: goto L26;
            case 2: goto L25;
            case 3: goto L24;
            case 4: goto L23;
            case 5: goto L22;
            case 6: goto L21;
            case 7: goto L20;
            default: goto L19;
        };
    L19:
        zzkh r32 = null;
    L27:
        if (r32 == null) goto L33;
        long r4 = ((Long) r32.zzb().apply(r92)).longValue();
        int r6 = ((Integer) r32.zzc().apply(r92)).intValue();
        if (r4 > 0) goto L32;
        if (r6 <= 0) goto L33;
    L32:
        r0.append(r32.zza());
        r0.append(": (");
        r0.append(r4);
        r0.append(" ms, count: ");
        r0.append(r6);
        r0.append("), ");
    L33:
        r2 = r2 + 1;
        goto L6
    L20:
        r32 = zzg;
        goto L27
    L21:
        r32 = zzc;
        goto L27
    L22:
        r32 = zzd;
        goto L27
    L23:
        r32 = zzf;
        goto L27
    L24:
        r32 = zze;
        goto L27
    L25:
        r32 = zzb;
        goto L27
    L26:
        r32 = zza;
        goto L27
    L35:
        if (r0.length() <= 1) goto L37;
        r0.setLength(r0.length() - 2);
    L37:
        r0.append("}");
        return r0.toString();
    L8:
        r3 = 8;
        goto L16
    L9:
        r3 = 7;
        goto L16
    L10:
        r3 = 6;
        goto L16
    L11:
        r3 = 5;
        goto L16
    L12:
        r3 = 4;
        goto L16
    L13:
        r3 = 3;
        goto L16
    L14:
        r3 = 2;
        goto L16
    L15:
        r3 = 1;
        goto L16
    }
}
