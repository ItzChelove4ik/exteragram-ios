package com.exteragram.messenger.pillstack.ui.pills.crypto.utils;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.export.api.ApiWrap$Poll$Answer$$ExternalSyntheticRecord0;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/* loaded from: classes.dex */
public abstract class PillStackCurrencies {
    private static final HashSet<String> AMBIGUOUS_SYMBOLS = null;
    private static final Map<String, CurrencyInfo> CURRENCIES = null;
    public static final String[] TARGET_CURRENCIES = null;

    public static final class CurrencyInfo extends RecordTag {
        private final String code;
        private final int nameResId;
        private final boolean suffixSymbol;
        private final String symbolOverride;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof CurrencyInfo) == false) goto L14;
            CurrencyInfo r32 = (CurrencyInfo) r3;
            if (this.suffixSymbol == r32.suffixSymbol) goto L7;
            return false;
        L7:
            if (this.nameResId == r32.nameResId) goto L9;
            return false;
        L9:
            if (Objects.equals(this.code, r32.code) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.symbolOverride, r32.symbolOverride) == false) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.code, Integer.valueOf(this.nameResId), this.symbolOverride, Boolean.valueOf(this.suffixSymbol)};
        }

        /* renamed from: -$$Nest$fgetnameResId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1536$$Nest$fgetnameResId(CurrencyInfo r0) {
            return r0.nameResId;
        }

        /* renamed from: -$$Nest$fgetsuffixSymbol, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m1537$$Nest$fgetsuffixSymbol(CurrencyInfo r0) {
            return r0.suffixSymbol;
        }

        /* renamed from: -$$Nest$fgetsymbolOverride, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1538$$Nest$fgetsymbolOverride(CurrencyInfo r0) {
            return r0.symbolOverride;
        }

        private CurrencyInfo(String r1, int r2, String r3, boolean r4) {
            this.code = r1;
            this.nameResId = r2;
            this.symbolOverride = r3;
            this.suffixSymbol = r4;
        }

        public /* synthetic */ CurrencyInfo(String r1, int r2, String r3, boolean r4, PillStackCurrenciesIA r5) {
            this(r1, r2, r3, r4);
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return ApiWrap$Poll$Answer$$ExternalSyntheticRecord0.m(this.suffixSymbol, this.nameResId, this.code, this.symbolOverride);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), CurrencyInfo.class, "code;nameResId;symbolOverride;suffixSymbol");
        }
    }

    static {
        AMBIGUOUS_SYMBOLS = new HashSet(Arrays.asList(new String[]{"$", "kr", "Fr", "₩"}));
        CURRENCIES = new HashMap();
        addCurrency("USD", R.string.CryptoCurrencyUsd, "$", false);
        addCurrency("EUR", R.string.CryptoCurrencyEur, null, false);
        addCurrency("RUB", R.string.CryptoCurrencyRub, "₽", true);
        addCurrency("GBP", R.string.CryptoCurrencyGbp, null, false);
        addCurrency("KZT", R.string.CryptoCurrencyKzt, "₸", true);
        addCurrency("TRY", R.string.CryptoCurrencyTry, "₺", true);
        addCurrency("UAH", R.string.CryptoCurrencyUah, "₴", true);
        addCurrency("PLN", R.string.CryptoCurrencyPln, "zł", true);
        addCurrency("AED", R.string.CryptoCurrencyAed, null, false);
        addCurrency("CNY", R.string.CryptoCurrencyCny, "CN¥", false);
        addCurrency("JPY", R.string.CryptoCurrencyJpy, null, false);
        addCurrency("BYN", R.string.CryptoCurrencyByn, "Br", true);
        addCurrency("ILS", R.string.CryptoCurrencyIls, "₪", false);
        addCurrency("CZK", R.string.CryptoCurrencyCzk, "Kč", true);
        addCurrency("INR", R.string.CryptoCurrencyInr, "₹", false);
        String[] r1 = new String[16];
        TARGET_CURRENCIES = r1;
        r1[0] = "AUTO";
        System.arraycopy(new String[]{"AED", "BYN", "CNY", "CZK", "EUR", "GBP", "ILS", "INR", "JPY", "KZT", "PLN", "RUB", "TRY", "UAH", "USD"}, 0, r1, 1, 15);
    }

    private static void addCurrency(String r6, int r7, String r8, boolean r9) {
        String r1 = normalize(r6);
        if (r1.isEmpty() == true) goto L6;
        CURRENCIES.put(r1, new CurrencyInfo(r1, r7, r8, r9, null));
        return;
    }

    public static String formatFiatPrice(BigDecimal r7, String r8) {
        if (r7 == null) goto L41;
        if (r8 == null) goto L41;
        if (r8.isEmpty() == true) goto L41;
        boolean r2 = false;
        int r1 = Math.max(0, BillingController.getInstance().getCurrencyExp(r8));     // Catch: Exception -> L42
        BigDecimal r72 = r7.setScale(r1, RoundingMode.HALF_UP);     // Catch: Exception -> L42
        Locale r3 = Locale.US;     // Catch: Exception -> L42
        NumberFormat r4 = NumberFormat.getNumberInstance(r3);     // Catch: Exception -> L42
        r4.setGroupingUsed(true);     // Catch: Exception -> L42
        r4.setMinimumFractionDigits(r1);     // Catch: Exception -> L42
        r4.setMaximumFractionDigits(r1);     // Catch: Exception -> L42
        String r73 = r4.format(r72);     // Catch: Exception -> L42
        String r12 = normalize(r8);     // Catch: Exception -> L42
        CurrencyInfo r42 = CURRENCIES.get(r12);     // Catch: Exception -> L42
        if (r42 == null) goto L12;
        String r6 = CurrencyInfo.m1538$$Nest$fgetsymbolOverride(r42);     // Catch: Exception -> L42
    L13:
        if (r6 == null) goto L15;
        r2 = true;
    L15:
        if (r2 == true) goto L21;
        r6 = Currency.getInstance(r12).getSymbol(r3);     // Catch: Exception -> L18
    L18:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L42
    L21:
        if (r6 == null) goto L40;
        if (r6.isEmpty() == true) goto L40;
        if (r6.equalsIgnoreCase(r12) == true) goto L40;
        if (r2 == false) goto L29;
    L32:
        if (r42 == null) goto L38;
        if (CurrencyInfo.m1537$$Nest$fgetsuffixSymbol(r42) == false) goto L38;
        return r73 + " " + r6;
    L38:
        return r6 + r73;
    L29:
        if (AMBIGUOUS_SYMBOLS.contains(r6) == false) goto L32;
        return r73 + " " + r8;
    L40:
        return r73 + " " + r8;
    L12:
        r6 = null;
    L41:
        return null;
    }

    public static String getCurrencyLabelWithCode(String r2) {
        String r22 = normalize(r2);
        CurrencyInfo r0 = CURRENCIES.get(r22);
        if (r0 != null) goto L6;
        return r22;
    L6:
        return LocaleController.getString(CurrencyInfo.m1536$$Nest$fgetnameResId(r0)) + " — " + r22;
    }

    public static String getCurrencyName(String r1) {
        String r12 = normalize(r1);
        CurrencyInfo r0 = CURRENCIES.get(r12);
        if (r0 != null) goto L6;
        return r12;
    L6:
        return LocaleController.getString(CurrencyInfo.m1536$$Nest$fgetnameResId(r0));
    }

    public static String[] getTargetCurrencies(String r7) {
        if (r7 == null) goto L20;
        if (r7.isEmpty() == true) goto L20;
        String[] r0 = TARGET_CURRENCIES;
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L7:
        if (r3 >= r1) goto L12;
        if (r7.equalsIgnoreCase(r0[r3]) == true) goto L11;
        r4 = r4 + 1;
    L11:
        r3 = r3 + 1;
        goto L7
    L12:
        String[] r02 = new String[r4];
        String[] r12 = TARGET_CURRENCIES;
        int r32 = r12.length;
        int r42 = 0;
    L13:
        if (r2 >= r32) goto L18;
        String r5 = r12[r2];
        if (r7.equalsIgnoreCase(r5) == true) goto L17;
        r02[r42] = r5;
        r42 = r42 + 1;
    L17:
        r2 = r2 + 1;
        goto L13
    L18:
        return r02;
    L20:
        return TARGET_CURRENCIES;
    }

    public static CharSequence getTargetCurrencyLabel(String r1) {
        if (r1 == null) goto L9;
        if ("AUTO".equalsIgnoreCase(r1) == true) goto L9;
        return getCurrencyLabelWithCode(r1);
    L9:
        return LocaleController.getString(R.string.QualityAuto);
    }

    public static CharSequence getTargetCurrencySubtext(String r1) {
        if (r1 == null) goto L9;
        if ("AUTO".equalsIgnoreCase(r1) == true) goto L9;
        return getCurrencyName(r1);
    L9:
        return LocaleController.getString(R.string.QualityAuto);
    }

    private static String normalize(String r1) {
        if (r1 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r1.trim().toUpperCase(Locale.ROOT);
    }
}
