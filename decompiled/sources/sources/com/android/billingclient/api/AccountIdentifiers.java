package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final class AccountIdentifiers {
    private final String zza;
    private final String zzb;

    public AccountIdentifiers(String r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public String getObfuscatedAccountId() {
        return this.zza;
    }

    public String getObfuscatedProfileId() {
        return this.zzb;
    }
}
