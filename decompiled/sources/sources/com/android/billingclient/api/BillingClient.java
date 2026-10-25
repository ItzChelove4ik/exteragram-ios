package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.play_billing.zzc;

/* loaded from: classes.dex */
public abstract class BillingClient {

    public static final class Builder {
        volatile boolean zza;
        private volatile PendingPurchasesParams zzd;
        private final Context zze;
        private volatile PurchasesUpdatedListener zzf;
        private volatile boolean zzl;
        private volatile boolean zzm;

        public /* synthetic */ Builder(Context r1, zzt r2) {
            this.zze = r1;
        }

        private final boolean zza() {
            Context r3 = this.zze;     // Catch: Exception -> L5
            return r3.getPackageManager().getApplicationInfo(r3.getPackageName(), 128).metaData.getBoolean("com.google.android.play.billingclient.enableBillingOverridesTesting", false);
        L5:
            e = move-exception;
            zzc.zzo("BillingClient", "Unable to retrieve metadata value for enableBillingOverridesTesting.", e);
            return false;
        }

        public BillingClient build() {
            Context r3 = this.zze;
            if (r3 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Please provide a valid Context.");
            return null;
        L5:
            if (this.zzf != null) goto L20;
            if (this.zzl == true) goto L14;
            if (this.zzm == true) goto L14;
            f$$ExternalSyntheticBUOutline1.m("Please provide a valid listener for purchases updates.");
            return null;
        L14:
            if (zza() == false) goto L18;
            return new zzce(null, r3, null, null, this);
        L18:
            return new BillingClientImpl(null, r3, null, null, this);
        L20:
            if (this.zzd != null) goto L22;
        L37:
            f$$ExternalSyntheticBUOutline1.m("Pending purchases for one-time products must be supported.");
            return null;
        L22:
            if (this.zzd.isEnabledForOneTimeProducts() == false) goto L37;
            if (this.zzf == null) goto L31;
            PendingPurchasesParams r2 = this.zzd;
            PurchasesUpdatedListener r4 = this.zzf;
            if (zza() == false) goto L30;
            return new zzce(null, r2, r3, r4, null, null, null, this);
        L30:
            return new BillingClientImpl(null, r2, r3, r4, null, null, null, this);
        L31:
            PendingPurchasesParams r22 = this.zzd;
            if (zza() == false) goto L36;
            return new zzce(null, r22, r3, null, null, null, this);
        L36:
            return new BillingClientImpl(null, r22, r3, null, null, null, this);
        }

        public Builder enablePendingPurchases(PendingPurchasesParams r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setListener(PurchasesUpdatedListener r1) {
            this.zzf = r1;
            return this;
        }
    }

    public BillingClient() {
    }

    public static Builder newBuilder(Context r2) {
        return new Builder(r2, null);
    }

    public abstract void consumeAsync(ConsumeParams r1, ConsumeResponseListener r2);

    public abstract boolean isReady();

    public abstract BillingResult launchBillingFlow(Activity r1, BillingFlowParams r2);

    public abstract void queryProductDetailsAsync(QueryProductDetailsParams r1, ProductDetailsResponseListener r2);

    public abstract void queryPurchasesAsync(QueryPurchasesParams r1, PurchasesResponseListener r2);

    public abstract void startConnection(BillingClientStateListener r1);
}
