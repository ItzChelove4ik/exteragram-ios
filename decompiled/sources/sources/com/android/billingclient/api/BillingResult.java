package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzc;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class BillingResult {
    private int zza;
    private int zzb;
    private String zzc;

    public static class Builder {
        private int zza;
        private int zzb;
        private String zzc;

        public /* synthetic */ Builder(zzci r1) {
            this.zzb = 0;
            this.zzc = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public BillingResult build() {
            BillingResult r0 = new BillingResult();
            BillingResult.zzc(r0, this.zza);
            BillingResult.zzb(r0, this.zzb);
            BillingResult.zza(r0, this.zzc);
            return r0;
        }

        public Builder setDebugMessage(String r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setOnPurchasesUpdatedSubResponseCode(int r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setResponseCode(int r1) {
            this.zza = r1;
            return this;
        }
    }

    public BillingResult() {
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public static /* bridge */ /* synthetic */ void zza(BillingResult r0, String r1) {
        r0.zzc = r1;
    }

    public static /* bridge */ /* synthetic */ void zzb(BillingResult r0, int r1) {
        r0.zzb = r1;
    }

    public static /* bridge */ /* synthetic */ void zzc(BillingResult r0, int r1) {
        r0.zza = r1;
    }

    public String getDebugMessage() {
        return this.zzc;
    }

    public int getOnPurchasesUpdatedSubResponseCode() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }

    public String toString() {
        return "Response Code: " + zzc.zzk(this.zza) + ", Debug Message: " + this.zzc;
    }
}
