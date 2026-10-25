package com.android.billingclient.api;

import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class ProductDetails {
    private final String zza;
    private final JSONObject zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final String zzi;
    private final List zzj;
    private final List zzk;

    public static final class InstallmentPlanDetails {
        private final int commitmentPaymentsCount;
        private final int subsequentCommitmentPaymentsCount;

        public InstallmentPlanDetails(JSONObject r2) {
            this.commitmentPaymentsCount = r2.getInt("commitmentPaymentsCount");
            this.subsequentCommitmentPaymentsCount = r2.optInt("subsequentCommitmentPaymentsCount");
        }
    }

    public static final class OneTimePurchaseOfferDetails {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final String zze;
        private final String zzf;
        private final List zzg;
        private final Long zzh;
        private final DiscountDisplayInfo zzi;
        private final ValidTimeWindow zzj;
        private final LimitedQuantityInfo zzk;
        private final String zzl;
        private final RentalDetails zzm;
        private final zzcs zzn;

        public static final class DiscountDisplayInfo {
            private final Integer zza;
            private final DiscountAmount zzb;

            public static final class DiscountAmount {
                private final String zza;
                private final long zzb;
                private final String zzc;

                public DiscountAmount(JSONObject r3) {
                    this.zza = r3.optString("formattedDiscountAmount");
                    this.zzb = r3.optLong("discountAmountMicros");
                    this.zzc = r3.optString("discountAmountCurrencyCode");
                }
            }

            public DiscountDisplayInfo(JSONObject r4) {
                DiscountAmount r2 = null;
                if (r4.has("percentageDiscount") == false) goto L5;
                Integer r0 = Integer.valueOf(r4.optInt("percentageDiscount"));
            L6:
                this.zza = r0;
                JSONObject r42 = r4.optJSONObject("discountAmount");
                if (r42 == null) goto L10;
                r2 = new DiscountAmount(r42);
            L10:
                this.zzb = r2;
                return;
            L5:
                r0 = null;
                goto L6
            }
        }

        public static final class LimitedQuantityInfo {
            private final int zza;
            private final int zzb;

            public LimitedQuantityInfo(JSONObject r2) {
                this.zza = r2.getInt("maximumQuantity");
                this.zzb = r2.getInt("remainingQuantity");
            }
        }

        public static final class RentalDetails {
            private final String rentalExpirationPeriod;
            private final String rentalPeriod;

            public RentalDetails(JSONObject r3) {
                this.rentalPeriod = r3.getString("rentalPeriod");
                String r32 = r3.optString("rentalExpirationPeriod");
                if (true != r32.isEmpty()) goto L5;
                r32 = null;
            L5:
                this.rentalExpirationPeriod = r32;
            }
        }

        public static final class ValidTimeWindow {
            private final Long zza;
            private final Long zzb;

            public ValidTimeWindow(JSONObject r4) {
                Long r2 = null;
                if (r4.has("startTimeMillis") == false) goto L5;
                Long r0 = Long.valueOf(r4.optLong("startTimeMillis"));
            L6:
                this.zza = r0;
                if (r4.has("endTimeMillis") == false) goto L9;
                r2 = Long.valueOf(r4.optLong("endTimeMillis"));
            L9:
                this.zzb = r2;
                return;
            L5:
                r0 = null;
                goto L6
            }
        }

        public OneTimePurchaseOfferDetails(JSONObject r6) {
            this.zza = r6.optString("formattedPrice");
            this.zzb = r6.optLong("priceAmountMicros");
            this.zzc = r6.optString("priceCurrencyCode");
            String r0 = r6.optString("offerIdToken");
            zzcs r2 = null;
            if (true != r0.isEmpty()) goto L5;
            r0 = null;
        L5:
            this.zzd = r0;
            String r02 = r6.optString("offerId");
            if (true != r02.isEmpty()) goto L8;
            r02 = null;
        L8:
            this.zze = r02;
            String r03 = r6.optString("purchaseOptionId");
            if (true != r03.isEmpty()) goto L11;
            r03 = null;
        L11:
            this.zzf = r03;
            r6.optInt("offerType");
            JSONArray r04 = r6.optJSONArray("offerTags");
            this.zzg = new ArrayList();
            if (r04 == null) goto L18;
            int r1 = 0;
        L15:
            if (r1 >= r04.length()) goto L18;
            this.zzg.add(r04.getString(r1));
            r1 = r1 + 1;
        L18:
            if (r6.has("fullPriceMicros") == false) goto L20;
            Long r05 = Long.valueOf(r6.optLong("fullPriceMicros"));
        L21:
            this.zzh = r05;
            JSONObject r06 = r6.optJSONObject("discountDisplayInfo");
            if (r06 != null) goto L24;
            DiscountDisplayInfo r12 = null;
        L25:
            this.zzi = r12;
            JSONObject r07 = r6.optJSONObject("validTimeWindow");
            if (r07 != null) goto L28;
            ValidTimeWindow r13 = null;
        L29:
            this.zzj = r13;
            JSONObject r08 = r6.optJSONObject("limitedQuantityInfo");
            if (r08 != null) goto L32;
            LimitedQuantityInfo r14 = null;
        L33:
            this.zzk = r14;
            this.zzl = r6.optString("serializedDocid");
            JSONObject r09 = r6.optJSONObject("preorderDetails");
            if (r09 == null) goto L36;
            r09.getLong("preorderReleaseTimeMillis");
            r09.getLong("preorderPresaleEndTimeMillis");
        L36:
            JSONObject r010 = r6.optJSONObject("rentalDetails");
            if (r010 != null) goto L39;
            RentalDetails r15 = null;
        L40:
            this.zzm = r15;
            JSONObject r011 = r6.optJSONObject("autoPayDetails");
            if (r011 == null) goto L44;
            r2 = new zzcs(r011);
        L44:
            this.zzn = r2;
            JSONArray r5 = r6.optJSONArray("pricingPhases");
            if (r5 != null) goto L47;
            return;
        L47:
            new PricingPhases(r5);
            return;
        L39:
            r15 = new RentalDetails(r010);
            goto L40
        L32:
            r14 = new LimitedQuantityInfo(r08);
            goto L33
        L28:
            r13 = new ValidTimeWindow(r07);
            goto L29
        L24:
            r12 = new DiscountDisplayInfo(r06);
            goto L25
        L20:
            r05 = null;
            goto L21
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public String getOfferToken() {
            return this.zzd;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public final zzcs zza() {
            return this.zzn;
        }

        public final String zzb() {
            return this.zzl;
        }
    }

    public static final class PricingPhase {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final int zze;
        private final int zzf;

        public PricingPhase(JSONObject r3) {
            this.zzd = r3.optString("billingPeriod");
            this.zzc = r3.optString("priceCurrencyCode");
            this.zza = r3.optString("formattedPrice");
            this.zzb = r3.optLong("priceAmountMicros");
            this.zzf = r3.optInt("recurrenceMode");
            this.zze = r3.optInt("billingCycleCount");
        }

        public String getBillingPeriod() {
            return this.zzd;
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }
    }

    public static class PricingPhases {
        private final List zza;

        public PricingPhases(JSONArray r5) {
            ArrayList r0 = new ArrayList();
            if (r5 == null) goto L11;
            int r1 = 0;
        L6:
            if (r1 >= r5.length()) goto L11;
            JSONObject r2 = r5.optJSONObject(r1);
            if (r2 == null) goto L10;
            r0.add(new PricingPhase(r2));
        L10:
            r1 = r1 + 1;
        L11:
            this.zza = r0;
        }

        public List<PricingPhase> getPricingPhaseList() {
            return this.zza;
        }
    }

    public static final class SubscriptionOfferDetails {
        private final String zza;
        private final String zzb;
        private final String zzc;
        private final PricingPhases zzd;
        private final List zze;
        private final InstallmentPlanDetails zzf;

        public SubscriptionOfferDetails(JSONObject r6) {
            this.zza = r6.optString("basePlanId");
            String r1 = r6.optString("offerId");
            InstallmentPlanDetails r4 = null;
            if (true != r1.isEmpty()) goto L5;
            r1 = null;
        L5:
            this.zzb = r1;
            this.zzc = r6.getString("offerIdToken");
            this.zzd = new PricingPhases(r6.getJSONArray("pricingPhases"));
            JSONObject r12 = r6.optJSONObject("installmentPlanDetails");
            if (r12 == null) goto L9;
            r4 = new InstallmentPlanDetails(r12);
        L9:
            this.zzf = r4;
            JSONObject r13 = r6.optJSONObject("transitionPlanDetails");
            if (r13 == null) goto L15;
            r13.getString("productId");
            r13.optString("title");
            r13.optString("name");
            r13.optString(MediaTrack.ROLE_DESCRIPTION);
            r13.optString("basePlanId");
            JSONObject r0 = r13.optJSONObject("pricingPhase");
            if (r0 == null) goto L15;
            new PricingPhase(r0);
        L15:
            ArrayList r02 = new ArrayList();
            JSONArray r62 = r6.optJSONArray("offerTags");
            if (r62 == null) goto L21;
            int r14 = 0;
        L19:
            if (r14 >= r62.length()) goto L21;
            r02.add(r62.getString(r14));
            r14 = r14 + 1;
        L21:
            this.zze = r02;
        }

        public String getOfferToken() {
            return this.zzc;
        }

        public PricingPhases getPricingPhases() {
            return this.zzd;
        }
    }

    public ProductDetails(String r7) {
        this.zza = r7;
        JSONObject r0 = new JSONObject(r7);
        this.zzb = r0;
        String r72 = r0.optString("productId");
        this.zzc = r72;
        String r1 = r0.optString("type");
        this.zzd = r1;
        if (TextUtils.isEmpty(r72) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Product id cannot be empty.");
        throw null;
    L5:
        if (TextUtils.isEmpty(r1) == true) goto L33;
        this.zze = r0.optString("title");
        this.zzf = r0.optString("name");
        this.zzg = r0.optString(MediaTrack.ROLE_DESCRIPTION);
        r0.optString("packageDisplayName");
        r0.optString("iconUrl");
        this.zzh = r0.optString("skuDetailsToken");
        this.zzi = r0.optString("serializedDocid");
        JSONArray r73 = r0.optJSONArray("subscriptionOfferDetails");
        int r02 = 0;
        if (r73 == null) goto L14;
        ArrayList r12 = new ArrayList();
        int r3 = 0;
    L10:
        if (r3 >= r73.length()) goto L12;
        r12.add(new SubscriptionOfferDetails(r73.getJSONObject(r3)));
        r3 = r3 + 1;
        goto L10
    L12:
        this.zzj = r12;
    L21:
        JSONObject r74 = this.zzb.optJSONObject("oneTimePurchaseOfferDetails");
        JSONArray r13 = this.zzb.optJSONArray("oneTimePurchaseOfferDetailsList");
        ArrayList r32 = new ArrayList();
        if (r13 != null) goto L24;
        if (r74 == null) goto L31;
        r32.add(new OneTimePurchaseOfferDetails(r74));
        this.zzk = r32;
        return;
    L31:
        this.zzk = null;
        return;
    L24:
        if (r02 >= r13.length()) goto L26;
        r32.add(new OneTimePurchaseOfferDetails(r13.getJSONObject(r02)));
        r02 = r02 + 1;
        goto L24
    L26:
        this.zzk = r32;
        return;
    L14:
        if (r1.equals("subs") == false) goto L16;
    L19:
        ArrayList r75 = new ArrayList();
    L20:
        this.zzj = r75;
        goto L21
    L16:
        if (r1.equals("play_pass_subs") == true) goto L19;
        r75 = null;
        goto L20
    L33:
        f$$ExternalSyntheticBUOutline1.m("Product type cannot be empty.");
        throw null;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ProductDetails) == true) goto L10;
        return false;
    L10:
        return TextUtils.equals(this.zza, ((ProductDetails) r2).zza);
    }

    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() {
        List r1 = this.zzk;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return (OneTimePurchaseOfferDetails) r1.get(0);
    }

    public List<OneTimePurchaseOfferDetails> getOneTimePurchaseOfferDetailsList() {
        return this.zzk;
    }

    public String getProductId() {
        return this.zzc;
    }

    public String getProductType() {
        return this.zzd;
    }

    public List<SubscriptionOfferDetails> getSubscriptionOfferDetails() {
        return this.zzj;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public String toString() {
        List r0 = this.zzj;
        return "ProductDetails{jsonString='" + this.zza + "', parsedJson=" + this.zzb.toString() + ", productId='" + this.zzc + "', productType='" + this.zzd + "', title='" + this.zze + "', productDetailsToken='" + this.zzh + "', subscriptionOfferDetails=" + String.valueOf(r0) + "}";
    }

    public final String zza() {
        return this.zzb.optString("packageName");
    }

    public final String zzb() {
        return this.zzh;
    }

    public String zzc() {
        return this.zzi;
    }

    public final List zzd() {
        return this.zzk;
    }
}
