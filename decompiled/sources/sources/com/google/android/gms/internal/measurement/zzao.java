package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public interface zzao {
    public static final zzao zzf = null;
    public static final zzao zzg = null;
    public static final zzao zzh = null;
    public static final zzao zzi = null;
    public static final zzao zzj = null;
    public static final zzao zzk = null;
    public static final zzao zzl = null;
    public static final zzao zzm = null;

    static {
        zzf = new zzat();
        zzg = new zzam();
        zzh = new zzag("continue");
        zzi = new zzag("break");
        zzj = new zzag("return");
        zzk = new zzaf(Boolean.TRUE);
        zzl = new zzaf(Boolean.FALSE);
        zzm = new zzas(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    String zzc();

    zzao zzcG(String r1, zzg r2, List r3);

    Double zzd();

    Boolean zze();

    Iterator zzf();

    zzao zzt();
}
