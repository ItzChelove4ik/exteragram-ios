package org.telegram.ui.Components.Premium;

import com.android.billingclient.api.ProductDetails;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final class GiftPremiumBottomSheet$GiftTier {
    private int discount;
    public final TLRPC.TL_premiumGiftCodeOption giftCodeOption;
    public final TLRPC.TL_premiumGiftOption giftOption;
    public ProductDetails googlePlayProductDetails;
    private long pricePerMonth;
    private long pricePerMonthRegular;
    public final TLRPC.TL_premiumGiftCodeOption starsCodeOption;
    public final TLRPC.TL_premiumGiftOption starsOption;

    public GiftPremiumBottomSheet$GiftTier(TLRPC.TL_premiumGiftCodeOption r2, Object r3) {
        TLRPC.TL_premiumGiftCodeOption r0 = null;
        this.giftOption = null;
        this.giftCodeOption = r2;
        if ((r3 instanceof TLRPC.TL_premiumGiftOption) == false) goto L5;
        TLRPC.TL_premiumGiftOption r22 = (TLRPC.TL_premiumGiftOption) r3;
    L6:
        this.starsOption = r22;
        if ((r3 instanceof TLRPC.TL_premiumGiftCodeOption) == false) goto L9;
        r0 = (TLRPC.TL_premiumGiftCodeOption) r3;
    L9:
        this.starsCodeOption = r0;
        return;
    L5:
        r22 = null;
        goto L6
    }

    public GiftPremiumBottomSheet$GiftTier(TLRPC.TL_premiumGiftOption r2, Object r3) {
        this.giftOption = r2;
        TLRPC.TL_premiumGiftCodeOption r22 = null;
        this.giftCodeOption = null;
        if ((r3 instanceof TLRPC.TL_premiumGiftOption) == false) goto L5;
        TLRPC.TL_premiumGiftOption r0 = (TLRPC.TL_premiumGiftOption) r3;
    L6:
        this.starsOption = r0;
        if ((r3 instanceof TLRPC.TL_premiumGiftCodeOption) == false) goto L9;
        r22 = (TLRPC.TL_premiumGiftCodeOption) r3;
    L9:
        this.starsCodeOption = r22;
        return;
    L5:
        r0 = null;
        goto L6
    }

    public String getCurrency() {
        if (this.giftOption == null) goto L11;
        if (BuildVars.useInvoiceBilling() == true) goto L9;
        if (this.giftOption.store_product == null) goto L9;
    L18:
        ProductDetails r1 = this.googlePlayProductDetails;
        if (r1 != null) goto L23;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        return r1.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
    L9:
        return this.giftOption.currency;
    L11:
        if (this.giftCodeOption == null) goto L18;
        if (BuildVars.useInvoiceBilling() == true) goto L17;
        if (this.giftCodeOption.store_product != null) goto L18;
    L17:
        return this.giftCodeOption.currency;
    }

    public int getDiscount() {
        if (this.discount != 0) goto L14;
        if (getPricePerMonth() != 0) goto L9;
        return 0;
    L9:
        if (this.pricePerMonthRegular == 0) goto L14;
        int r0 = (int) ((1.0d - (getPricePerMonth() / this.pricePerMonthRegular)) * 100.0d);
        this.discount = r0;
        if (r0 != 0) goto L14;
        this.discount = -1;
    L14:
        return this.discount;
    }

    public String getFormattedPrice() {
        if (BuildVars.useInvoiceBilling() == true) goto L20;
        TLRPC.TL_premiumGiftOption r0 = this.giftOption;
        if (r0 != null) goto L7;
    L8:
        TLRPC.TL_premiumGiftCodeOption r02 = this.giftCodeOption;
        if (r02 == null) goto L14;
        if (r02.store_product == null) goto L20;
    L14:
        if (this.googlePlayProductDetails != null) goto L18;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        return BillingController.getInstance().formatCurrency(getPrice(), getCurrency(), 6);
    L7:
        if (r0.store_product != null) goto L8;
    L20:
        return BillingController.getInstance().formatCurrency(getPrice(), getCurrency());
    }

    public int getMonths() {
        TLRPC.TL_premiumGiftOption r0 = this.giftOption;
        if (r0 != null) goto L5;
        TLRPC.TL_premiumGiftCodeOption r1 = this.giftCodeOption;
        if (r1 != null) goto L9;
        return 1;
    L9:
        return r1.months;
    L5:
        return r0.months;
    }

    public long getPrice() {
        if (this.giftOption == null) goto L11;
        if (BuildVars.useInvoiceBilling() == true) goto L9;
        if (this.giftOption.store_product == null) goto L9;
    L18:
        ProductDetails r2 = this.googlePlayProductDetails;
        if (r2 != null) goto L23;
        return 0;
    L23:
        return r2.getOneTimePurchaseOfferDetails().getPriceAmountMicros();
    L9:
        return this.giftOption.amount;
    L11:
        if (this.giftCodeOption == null) goto L18;
        if (BuildVars.useInvoiceBilling() == true) goto L17;
        if (this.giftCodeOption.store_product != null) goto L18;
    L17:
        return this.giftCodeOption.amount;
    }

    public long getPricePerMonth() {
        if (this.pricePerMonth != 0) goto L8;
        long r0 = getPrice();
        if (r0 == 0) goto L8;
        this.pricePerMonth = r0 / getMonths();
    L8:
        return this.pricePerMonth;
    }

    public Object getStarsOption() {
        TLRPC.TL_premiumGiftOption r0 = this.starsOption;
        if (r0 == null) goto L5;
        return r0;
    L5:
        TLRPC.TL_premiumGiftCodeOption r1 = this.starsCodeOption;
        if (r1 == null) goto L8;
        return r1;
    L8:
        return null;
    }

    public long getStarsPrice() {
        TLRPC.TL_premiumGiftOption r0 = this.starsOption;
        if (r0 != null) goto L5;
        TLRPC.TL_premiumGiftCodeOption r2 = this.starsCodeOption;
        if (r2 != null) goto L9;
        return 0;
    L9:
        return r2.amount;
    L5:
        return r0.amount;
    }

    public String getStoreProduct() {
        TLRPC.TL_premiumGiftOption r0 = this.giftOption;
        if (r0 != null) goto L5;
        TLRPC.TL_premiumGiftCodeOption r1 = this.giftCodeOption;
        if (r1 != null) goto L9;
        return null;
    L9:
        return r1.store_product;
    L5:
        return r0.store_product;
    }

    public boolean isStarsPaymentAvailable() {
        if (this.starsOption == null) goto L5;
        return true;
    L5:
        if (this.starsCodeOption != null) goto L11;
        return false;
    L11:
        return true;
    }

    public void setGooglePlayProductDetails(ProductDetails r1) {
        this.googlePlayProductDetails = r1;
    }

    public void setPricePerMonthRegular(long r1) {
        this.pricePerMonthRegular = r1;
    }
}
