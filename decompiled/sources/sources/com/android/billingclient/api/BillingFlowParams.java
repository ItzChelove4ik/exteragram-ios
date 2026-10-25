package com.android.billingclient.api;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.billingclient.api.ProductDetails;
import com.google.android.gms.internal.play_billing.zzbg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class BillingFlowParams {
    private boolean zza;
    private String zzb;
    private String zzc;
    private SubscriptionUpdateParams zzd;
    private com.google.android.gms.internal.play_billing.zzbt zze;
    private ArrayList zzf;
    private boolean zzg;

    public static class Builder {
        private String zza;
        private String zzb;
        private List zzc;
        private ArrayList zzd;
        private boolean zze;
        private SubscriptionUpdateParams.Builder zzf;

        public /* synthetic */ Builder(zzcf r1) {
            SubscriptionUpdateParams.Builder r12 = SubscriptionUpdateParams.newBuilder();
            SubscriptionUpdateParams.Builder.zza(r12);
            this.zzf = r12;
        }

        public BillingFlowParams build() {
            ArrayList r0 = this.zzd;
            boolean r1 = true;
            if (r0 != null) goto L5;
        L7:
            boolean r02 = false;
        L8:
            List r3 = this.zzc;
            if (r3 != null) goto L11;
        L13:
            boolean r32 = false;
        L15:
            if (r02 == true) goto L20;
            if (r32 == true) goto L20;
            f$$ExternalSyntheticBUOutline1.m("Details of the products must be provided.");
            return null;
        L20:
            if (r02 == false) goto L25;
            if (r32 == false) goto L25;
            f$$ExternalSyntheticBUOutline1.m("Set SkuDetails or ProductDetailsParams, not both.");
            return null;
        L25:
            if (r02 == true) goto L27;
            List r5 = this.zzc;
            if (r5 == null) goto L45;
            Iterator r52 = r5.iterator();
        L39:
            if (r52.hasNext() == false) goto L45;
            if (((ProductDetailsParams) r52.next()) != null) goto L39;
            f$$ExternalSyntheticBUOutline1.m("ProductDetailsParams cannot be null.");
            return null;
        L45:
            BillingFlowParams r53 = new BillingFlowParams(null);
            if (r02 == true) goto L62;
            if (r32 == true) goto L49;
        L51:
            r1 = false;
        L52:
            BillingFlowParams.zzl(r53, r1);
            BillingFlowParams.zzn(r53, this.zza);
            BillingFlowParams.zzo(r53, this.zzb);
            BillingFlowParams.zzr(r53, this.zzf.build());
            ArrayList r03 = this.zzd;
            if (r03 == null) goto L55;
            ArrayList r12 = new ArrayList(r03);
        L56:
            BillingFlowParams.zzq(r53, r12);
            BillingFlowParams.zzm(r53, this.zze);
            List r7 = this.zzc;
            if (r7 == null) goto L59;
            com.google.android.gms.internal.play_billing.zzbt r72 = com.google.android.gms.internal.play_billing.zzbt.zzj(r7);
        L60:
            BillingFlowParams.zzp(r53, r72);
            return r53;
        L59:
            r72 = com.google.android.gms.internal.play_billing.zzbt.zzk();
            goto L60
        L55:
            r12 = new ArrayList();
            goto L56
        L49:
            if (((ProductDetailsParams) this.zzc.get(0)).zza().zza().isEmpty() == true) goto L51;
        L62:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzd.get(0));
            throw null;
        L27:
            if (this.zzd.contains(null) == false) goto L29;
            f$$ExternalSyntheticBUOutline1.m("SKU cannot be null.");
            return null;
        L29:
            if (this.zzd.size() <= 1) goto L45;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzd.get(0));
            throw null;
        L11:
            if (r3.isEmpty() == true) goto L13;
            r32 = true;
            goto L15
        L5:
            if (r0.isEmpty() == true) goto L7;
            r02 = true;
            goto L8
        }

        public Builder setObfuscatedAccountId(String r1) {
            this.zza = r1;
            return this;
        }

        public Builder setObfuscatedProfileId(String r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setProductDetailsParamsList(List<ProductDetailsParams> r2) {
            this.zzc = new ArrayList(r2);
            return this;
        }

        public Builder setSubscriptionUpdateParams(SubscriptionUpdateParams r1) {
            this.zzf = SubscriptionUpdateParams.zzb(r1);
            return this;
        }
    }

    public static final class ProductDetailsParams {
        private final ProductDetails zza;
        private final String zzb;

        public static class Builder {
            private ProductDetails zza;
            private String zzb;

            public /* synthetic */ Builder(zzcf r1) {
            }

            public static /* bridge */ /* synthetic */ ProductDetails zza(Builder r0) {
                return r0.zza;
            }

            public static /* bridge */ /* synthetic */ String zzb(Builder r0) {
                return r0.zzb;
            }

            public ProductDetailsParams build() {
                zzbg.zzc(this.zza, "ProductDetails is required for constructing ProductDetailsParams.");
                return new ProductDetailsParams(this, null);
            }

            public Builder setOfferToken(String r2) {
                if (TextUtils.isEmpty(r2) == true) goto L6;
                this.zzb = r2;
                return this;
            L6:
                f$$ExternalSyntheticBUOutline1.m("offerToken can not be empty");
                return null;
            }

            public Builder setProductDetails(ProductDetails r2) {
                this.zza = r2;
                if (r2.getOneTimePurchaseOfferDetails() == null) goto L7;
                r2.getOneTimePurchaseOfferDetails().getClass();
                ProductDetails.OneTimePurchaseOfferDetails r22 = r2.getOneTimePurchaseOfferDetails();
                if (r22.getOfferToken() == null) goto L7;
                this.zzb = r22.getOfferToken();
            L7:
                return this;
            }
        }

        public /* synthetic */ ProductDetailsParams(Builder r1, zzcf r2) {
            this.zza = Builder.zza(r1);
            this.zzb = Builder.zzb(r1);
        }

        public static Builder newBuilder() {
            return new Builder(null);
        }

        public final ProductDetails zza() {
            return this.zza;
        }

        public final String zzb() {
            return this.zzb;
        }
    }

    public static class SubscriptionUpdateParams {
        private String zza;
        private String zzb;
        private int zzc;

        public static class Builder {
            private String zza;
            private String zzb;
            private boolean zzc;
            private int zzd;

            public /* synthetic */ Builder(zzcf r1) {
                this.zzd = 0;
            }

            public static /* synthetic */ Builder zza(Builder r1) {
                r1.zzc = true;
                return r1;
            }

            public SubscriptionUpdateParams build() {
                boolean r1 = true;
                if (TextUtils.isEmpty(this.zza) == true) goto L5;
            L8:
                boolean r0 = TextUtils.isEmpty(this.zzb);
                if (r1 == false) goto L16;
                if (r0 == true) goto L16;
                f$$ExternalSyntheticBUOutline1.m("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                return null;
            L16:
                if (this.zzc == true) goto L21;
                if (r1 == true) goto L21;
                if (r0 == false) goto L21;
                f$$ExternalSyntheticBUOutline1.m("Old SKU purchase information(token/id) or original external transaction id must be provided.");
                return null;
            L21:
                SubscriptionUpdateParams r02 = new SubscriptionUpdateParams(null);
                SubscriptionUpdateParams.zze(r02, this.zza);
                SubscriptionUpdateParams.zzg(r02, this.zzd);
                SubscriptionUpdateParams.zzf(r02, this.zzb);
                return r02;
            L5:
                if (TextUtils.isEmpty(null) == false) goto L8;
                r1 = false;
                goto L8
            }

            public Builder setOldPurchaseToken(String r1) {
                this.zza = r1;
                return this;
            }

            public Builder setOriginalExternalTransactionId(String r1) {
                this.zzb = r1;
                return this;
            }

            public Builder setSubscriptionReplacementMode(int r1) {
                this.zzd = r1;
                return this;
            }

            @Deprecated
            public final Builder zzb(String r1) {
                this.zza = r1;
                return this;
            }
        }

        public /* synthetic */ SubscriptionUpdateParams(zzcf r1) {
            this.zzc = 0;
        }

        public static Builder newBuilder() {
            return new Builder(null);
        }

        public static /* bridge */ /* synthetic */ Builder zzb(SubscriptionUpdateParams r2) {
            Builder r0 = newBuilder();
            r0.zzb(r2.zza);
            r0.setSubscriptionReplacementMode(r2.zzc);
            r0.setOriginalExternalTransactionId(r2.zzb);
            return r0;
        }

        public static /* bridge */ /* synthetic */ void zze(SubscriptionUpdateParams r0, String r1) {
            r0.zza = r1;
        }

        public static /* bridge */ /* synthetic */ void zzf(SubscriptionUpdateParams r0, String r1) {
            r0.zzb = r1;
        }

        public static /* bridge */ /* synthetic */ void zzg(SubscriptionUpdateParams r0, int r1) {
            r0.zzc = r1;
        }

        public final int zza() {
            return this.zzc;
        }

        public final String zzc() {
            return this.zza;
        }

        public final String zzd() {
            return this.zzb;
        }
    }

    public /* synthetic */ BillingFlowParams(zzcf r1) {
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public static /* bridge */ /* synthetic */ void zzl(BillingFlowParams r0, boolean r1) {
        r0.zza = r1;
    }

    public static /* bridge */ /* synthetic */ void zzm(BillingFlowParams r0, boolean r1) {
        r0.zzg = r1;
    }

    public static /* bridge */ /* synthetic */ void zzn(BillingFlowParams r0, String r1) {
        r0.zzb = r1;
    }

    public static /* bridge */ /* synthetic */ void zzo(BillingFlowParams r0, String r1) {
        r0.zzc = r1;
    }

    public static /* bridge */ /* synthetic */ void zzp(BillingFlowParams r0, com.google.android.gms.internal.play_billing.zzbt r1) {
        r0.zze = r1;
    }

    public static /* bridge */ /* synthetic */ void zzq(BillingFlowParams r0, ArrayList r1) {
        r0.zzf = r1;
    }

    public static /* bridge */ /* synthetic */ void zzr(BillingFlowParams r0, SubscriptionUpdateParams r1) {
        r0.zzd = r1;
    }

    public int zza() {
        return 0;
    }

    public final int zzb() {
        return this.zzd.zza();
    }

    public long zzc() {
        return 0;
    }

    public final BillingResult zzd() {
        if (this.zze.isEmpty() == true) goto L5;
        int r1 = 0;
        ProductDetailsParams r0 = (ProductDetailsParams) this.zze.get(0);
        int r2 = 1;
    L8:
        if (r2 >= this.zze.size()) goto L17;
        ProductDetailsParams r3 = (ProductDetailsParams) this.zze.get(r2);
        if (r3.zza().getProductType().equals(r0.zza().getProductType()) == true) goto L16;
        if (r3.zza().getProductType().equals("play_pass_subs") == true) goto L16;
        return zzcj.zza(5, "All products should have same ProductType.");
    L16:
        r2 = r2 + 1;
        goto L8
    L17:
        String r22 = r0.zza().zza();
        HashMap r32 = new HashMap();
        HashSet r6 = new HashSet();
        com.google.android.gms.internal.play_billing.zzbt r10 = this.zze;
        int r7 = r10.size();
    L18:
        if (r1 >= r7) goto L40;
        ProductDetailsParams r8 = (ProductDetailsParams) r10.get(r1);
        if (r8.zza().getSubscriptionOfferDetails() == null) goto L27;
        if (r8.zzb() != null) goto L27;
        return zzcj.zza(5, String.format("offerToken is required for constructing ProductDetailsParams for subscriptions. Missing value for product id: %s", new Object[]{r8.zza().getProductId()}));
    L27:
        if (r32.containsKey(r8.zza().getProductId()) == true) goto L29;
        r32.put(r8.zza().getProductId(), r8);
        if (r0.zza().getProductType().equals("play_pass_subs") == true) goto L39;
        if (r8.zza().getProductType().equals("play_pass_subs") == true) goto L39;
        if (r22.equals(r8.zza().zza()) == true) goto L39;
        return zzcj.zza(5, "All products must have the same package name.");
    L39:
        r1 = r1 + 1;
        goto L18
    L29:
        return zzcj.zza(5, String.format("ProductId can not be duplicated. Invalid product id: %s.", new Object[]{r8.zza().getProductId()}));
    L40:
        Iterator r102 = r6.iterator();
    L42:
        if (r102.hasNext() == false) goto L47;
        String r12 = (String) r102.next();
        if (r32.containsKey(r12) == false) goto L42;
        ProductDetailsParams r103 = (ProductDetailsParams) r32.get(r12);
        return zzcj.zza(5, String.format("OldProductId must not be one of the products to be purchased. Invalid old product id: %s.", new Object[]{r12}));
    L47:
        List<ProductDetails.OneTimePurchaseOfferDetails> r104 = r0.zza().getOneTimePurchaseOfferDetailsList();
        String r02 = r0.zzb();
        if (r02 == null) goto L63;
        if (r104 == null) goto L63;
        Iterator<ProductDetails.OneTimePurchaseOfferDetails> r105 = r104.iterator();
    L52:
        if (r105.hasNext() == false) goto L56;
        ProductDetails.OneTimePurchaseOfferDetails r13 = r105.next();
        if (r02.equals(r13.getOfferToken()) == false) goto L52;
    L57:
        if (r13 == null) goto L63;
        if (r13.zza() == null) goto L63;
        return zzcj.zza(5, "Both autoPayDetails and autoPayBalanceThreshold is required for constructing ProductDetailsParams for autopay.");
    L56:
        r13 = null;
    L63:
        return zzcj.zzi;
    L5:
        return zzcj.zzi;
    }

    public final String zze() {
        return this.zzb;
    }

    public final String zzf() {
        return this.zzc;
    }

    public String zzg() {
        return null;
    }

    public final String zzh() {
        return this.zzd.zzc();
    }

    public final String zzi() {
        return this.zzd.zzd();
    }

    public final ArrayList zzj() {
        ArrayList r0 = new ArrayList();
        r0.addAll(this.zzf);
        return r0;
    }

    public final List zzk() {
        return this.zze;
    }

    public final boolean zzs() {
        return this.zzg;
    }

    public final boolean zzt() {
        if (this.zzb == null) goto L5;
        return true;
    L5:
        if (this.zzc == null) goto L7;
        return true;
    L7:
        if (this.zzd.zzd() == null) goto L9;
        return true;
    L9:
        if (this.zzd.zza() == 0) goto L11;
        return true;
    L11:
        if (this.zza == false) goto L13;
        return true;
    L13:
        if (this.zzg == true) goto L27;
        com.google.android.gms.internal.play_billing.zzbt r4 = this.zze;
        if (r4 == null) goto L19;
        int r1 = r4.size();
        int r2 = 0;
    L17:
        if (r2 >= r1) goto L19;
        ProductDetailsParams r3 = (ProductDetailsParams) r4.get(r2);
        r2 = r2 + 1;
    L19:
        return false;
    L27:
        return true;
    }
}
