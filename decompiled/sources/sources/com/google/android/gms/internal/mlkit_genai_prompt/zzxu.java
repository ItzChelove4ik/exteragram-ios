package com.google.android.gms.internal.mlkit_genai_prompt;

import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzxu implements zzxj {
    public static final zzxu zza = null;

    static {
        zza = new zzxu();
    }

    private zzxu() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzxj
    public final /* bridge */ /* synthetic */ zzxi zza(JSONObject r5) {
        return new zzxt(r5.getString("name"), r5.getString("path"), r5.getString("prompt_prefix"), r5.optString("model_name", "unknown"));
    }
}
