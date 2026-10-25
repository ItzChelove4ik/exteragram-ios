package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzpz implements EncoderConfig {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ObjectEncoder zzb = null;
    private final Map zzc;
    private final Map zzd;
    private final ObjectEncoder zze;

    static {
        zzb = zzpy.zza;
    }

    public zzpz() {
        this.zzc = new HashMap();
        this.zzd = new HashMap();
        this.zze = zzb;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public final /* bridge */ /* synthetic */ EncoderConfig registerEncoder(Class r2, ObjectEncoder r3) {
        this.zzc.put(r2, r3);
        this.zzd.remove(r2);
        return this;
    }

    public final zzqa zza() {
        return new zzqa(new HashMap(this.zzc), new HashMap(this.zzd), this.zze);
    }
}
