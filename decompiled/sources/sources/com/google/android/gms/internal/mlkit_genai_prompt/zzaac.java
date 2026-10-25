package com.google.android.gms.internal.mlkit_genai_prompt;

import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzaac implements zzxj {
    public static final zzaac zza = null;

    static {
        zza = new zzaac();
    }

    private zzaac() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzxj
    public final /* bridge */ /* synthetic */ zzxi zza(JSONObject r7) {
        return new zzaab(r7.getString("id"), r7.getString("path"), r7.getLong("last_access_time"), r7.optString("model_name", "unknown"));
    }
}
