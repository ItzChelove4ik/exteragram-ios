package com.exteragram.messenger.pillstack.ui.pills.crypto.utils;

import android.text.TextUtils;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.export.api.ApiWrap$ActionChatEditPhoto$$ExternalSyntheticRecord0;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.CacheFetcher;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public abstract class ExchangeRates {
    public static final String[] CRYPTO_CURRENCIES = null;
    public static final String[] MAIN_CURRENCIES = null;
    private static volatile State cacheValue;
    private static final CacheFetcher<Integer, State> fetcher = null;
    private static final ArrayList<Utilities.Callback4<Boolean, State, Long, Boolean>> pendingRemote = null;
    private static boolean remoteInFlight;

    /* renamed from: com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ExchangeRates$1, reason: invalid class name */
    public class AnonymousClass1 extends CacheFetcher<Integer, State> {

        /* renamed from: com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ExchangeRates$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes4.dex */
        public class C00221 implements Callback {
            final /* synthetic */ AnonymousClass1 this$0;

            public C00221(AnonymousClass1 r1) {
                this.this$0 = r1;
            }

            @Override // okhttp3.Callback
            public void onFailure(Call r1, IOException r2) {
                FileLog.e(r2);
                ExchangeRates.m1533$$Nest$smcompleteRemote(null);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call r4, Response r5) {
                if (r5.isSuccessful() == true) goto L20;
                r5.close();
                onFailure(r4, new IOException("Unexpected code " + r5));
                return;
            L20:
                ResponseBody r3 = r5.body();     // Catch: Exception -> L17
                ExchangeRates.m1533$$Nest$smcompleteRemote(ExchangeRates.m1535$$Nest$smparseState((CoinbaseResponse) ExteraConfig.getGSON().fromJson(r3.charStream(), CoinbaseResponse.class)));     // Catch: Throwable -> L10
                r3.close();     // Catch: Exception -> L17
                return;
            L10:
                th = move-exception;
                if (r3 != null) goto L21;
            L16:
                throw th;     // Catch: Exception -> L17
            L21:
                r3.close();     // Catch: Throwable -> L14
            L14:
                th = move-exception;
                th.addSuppressed(th);     // Catch: Exception -> L17
            L17:
                e = move-exception;
                FileLog.e(e);
                ExchangeRates.m1533$$Nest$smcompleteRemote(null);
            }
        }

        public AnonymousClass1(int r1) {
            super(r1);
        }

        /* renamed from: getLocal, reason: avoid collision after fix types in other method */
        public void getLocal2(int r1, Integer r2, Utilities.Callback2<Long, State> r3) {
            r3.run(0L, ExchangeRates.getCached());
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void getLocal(int r1, Integer r2, Utilities.Callback2<Long, State> r3) {
            getLocal2(r1, r2, r3);
        }

        /* renamed from: getRemote, reason: avoid collision after fix types in other method */
        public void getRemote2(int r1, Integer r2, long r3, Utilities.Callback4<Boolean, State, Long, Boolean> r5) {
            ArrayList r12 = ExchangeRates.m1529$$Nest$sfgetpendingRemote();
            monitor-enter(r12);
            ExchangeRates.m1529$$Nest$sfgetpendingRemote().add(r5);     // Catch: Throwable -> L8
            if (ExchangeRates.m1530$$Nest$sfgetremoteInFlight() == false) goto L10;
            monitor-exit(r12);     // Catch: Throwable -> L8
            return;
        L10:
            ExchangeRates.m1532$$Nest$sfputremoteInFlight(true);     // Catch: Throwable -> L8
            monitor-exit(r12);     // Catch: Throwable -> L8
            Request r13 = new Request.Builder().url("https://api.coinbase.com/v2/exchange-rates?currency=USD").build();
            ExteraHttpClient.INSTANCE.getClient().newCall(r13).enqueue(new C00221(this));
            return;
        L8:
            th = move-exception;
            throw th;
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void getRemote(int r1, Integer r2, long r3, Utilities.Callback4<Boolean, State, Long, Boolean> r5) {
            getRemote2(r1, r2, r3, r5);
        }

        @Override // org.telegram.messenger.CacheFetcher
        public long getSavedLastTimeRequested(int r3) {
            return PillStackConfig.getPreferences().getLong("exchangeRatesTimestamp", 0);
        }

        @Override // org.telegram.messenger.CacheFetcher
        public boolean saveLastTimeRequested() {
            return true;
        }

        /* renamed from: setLocal, reason: avoid collision after fix types in other method */
        public void setLocal2(int r1, Integer r2, State r3, long r4) {
            if (r3 != null) goto L4;
            return;
        L4:
            ExchangeRates.m1531$$Nest$sfputcacheValue(r3);
            PillStackConfig.getEditor().putString("exchangeRatesCache", ExteraConfig.getGSON().toJson(r3)).apply();     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void setLocal(int r1, Integer r2, State r3, long r4) {
            setLocal2(r1, r2, r3, r4);
        }

        @Override // org.telegram.messenger.CacheFetcher
        public void setSavedLastTimeRequested(int r1, long r2) {
            PillStackConfig.getEditor().putLong("exchangeRatesTimestamp", r2).apply();
        }
    }

    public static class CoinbaseResponse {

        @SerializedName("data")
        Data data;

        private CoinbaseResponse() {
        }
    }

    public static class Data {

        @SerializedName("currency")
        String currency;

        @SerializedName("rates")
        Map<String, String> rates;

        private Data() {
        }
    }

    public static final class State extends RecordTag {
        public static final DecimalFormat formatter = null;
        private final Map<String, BigDecimal> usdRates;

        private /* synthetic */ boolean $record$equals(Object r2) {
            if ((r2 instanceof State) == true) goto L5;
            return false;
        L5:
            if (Objects.equals(this.usdRates, ((State) r2).usdRates) == false) goto L10;
            return true;
        L10:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.usdRates};
        }

        static {
            DecimalFormat r0 = new DecimalFormat("#.##");
            formatter = r0;
            r0.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        }

        public State(Map<String, BigDecimal> r1) {
            this.usdRates = r1;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public double formatDonate(String r3, double r4) {
            BigDecimal r2 = getRate("USD", r3);
            if (r2 == null) goto L5;
            r4 = r2.doubleValue();
        L5:
            double r42 = r4 * RemoteUtils.getFloatConfigValue("donates_amount_usd", 5.0f).floatValue();
            if ("ton".equalsIgnoreCase(r3) == false) goto L9;
            r42 = r42 + ((RemoteUtils.getIntConfigValue("donates_ton_markup_percent", 10).intValue() / 100.0d) * r42);
        L9:
            return Double.parseDouble(formatter.format(r42));
        }

        public BigDecimal getRate(String r2, String r3) {
            BigDecimal r22 = getUsdRate(r2);
            BigDecimal r1 = getUsdRate(r3);
            if (r22 == null) goto L10;
            if (r1 != null) goto L6;
            return null;
        L6:
            if (r1.signum() != 0) goto L9;
            return null;
        L9:
            return r22.divide(r1, 12, RoundingMode.HALF_UP);
        L10:
            return null;
        }

        public BigDecimal getUsdRate(String r1) {
            if (r1 != null) goto L6;
            return null;
        L6:
            return this.usdRates.get(ExchangeRates.m1534$$Nest$smnormalize(r1));
        }

        public final int hashCode() {
            return ApiWrap$ActionChatEditPhoto$$ExternalSyntheticRecord0.m(this.usdRates);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), State.class, "usdRates");
        }

        public Map<String, BigDecimal> usdRates() {
            return this.usdRates;
        }
    }

    public static /* synthetic */ void $r8$lambda$cWqt41mowJXyOQJBhkZjVVpZpSM(int r1, int r2, Object[] r3) {
        if (r1 == NotificationCenter.pillStackSettingsChanged) goto L5;
        return;
    L5:
        if (PillStackConfig.shouldUpdatePill(r3, new int[]{PillType.GRAM.getId(), PillType.BTC.getId(), PillType.USD.getId()}) == false) goto L10;
        clearCache();
        return;
    }

    /* renamed from: -$$Nest$sfgetpendingRemote, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m1529$$Nest$sfgetpendingRemote() {
        return pendingRemote;
    }

    /* renamed from: -$$Nest$sfgetremoteInFlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1530$$Nest$sfgetremoteInFlight() {
        return remoteInFlight;
    }

    /* renamed from: -$$Nest$sfputcacheValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1531$$Nest$sfputcacheValue(State r0) {
        cacheValue = r0;
    }

    /* renamed from: -$$Nest$sfputremoteInFlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1532$$Nest$sfputremoteInFlight(boolean r0) {
        remoteInFlight = r0;
    }

    /* renamed from: -$$Nest$smcompleteRemote, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1533$$Nest$smcompleteRemote(State r0) {
        completeRemote(r0);
    }

    /* renamed from: -$$Nest$smnormalize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1534$$Nest$smnormalize(String r0) {
        return normalize(r0);
    }

    /* renamed from: -$$Nest$smparseState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ State m1535$$Nest$smparseState(CoinbaseResponse r0) {
        return parseState(r0);
    }

    static {
        MAIN_CURRENCIES = new String[]{"USD", "EUR", "RUB", "GBP", "KZT", "TRY", "UAH", "PLN", "AED", "CNY", "JPY", "BYN", "ILS", "CZK", "INR", "TON", "BTC", "ETH", "SOL"};
        CRYPTO_CURRENCIES = new String[]{"BTC", "ETH", "SOL", "TON", "USD", "EUR"};
        pendingRemote = new ArrayList();
        NotificationCenter.getGlobalInstance().addObserver(new ExchangeRates$$ExternalSyntheticLambda0(), NotificationCenter.pillStackSettingsChanged);
        fetcher = new AnonymousClass1(300000);
    }

    public static void clearCache() {
        PillStackConfig.getEditor().putLong("exchangeRatesTimestamp", 0).apply();
        fetcher.forceRequest(0, 0);
    }

    private static void completeRemote(State r8) {
        ArrayList<Utilities.Callback4<Boolean, State, Long, Boolean>> r1 = pendingRemote;
        monitor-enter(r1);
        int r2 = 0;
        remoteInFlight = false;     // Catch: Throwable -> L14
        ArrayList r3 = new ArrayList(r1);     // Catch: Throwable -> L14
        r1.clear();     // Catch: Throwable -> L14
        monitor-exit(r1);     // Catch: Throwable -> L14
        int r12 = r3.size();
    L8:
        if (r2 >= r12) goto L13;
        Object r4 = r3.get(r2);
        r2 = r2 + 1;
        Utilities.Callback4 r42 = (Utilities.Callback4) r4;
        if (r8 == null) goto L11;
        r42.run(Boolean.FALSE, r8, 0L, Boolean.TRUE);
        goto L8
    L11:
        r42.run(Boolean.TRUE, null, 0L, Boolean.FALSE);
        goto L8
    L13:
        return;
    L14:
        th = move-exception;
        throw th;
    }

    public static void fetch(Utilities.Callback<State> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        fetcher.fetch(0, 0, r3);
    }

    public static State getCached() {
        if (cacheValue != null) goto L11;
        String r0 = PillStackConfig.getPreferences().getString("exchangeRatesCache", null);     // Catch: Exception -> L8
        if (r0 == null) goto L11;
        cacheValue = (State) ExteraConfig.getGSON().fromJson(r0, State.class);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
    L11:
        return cacheValue;
    }

    public static boolean isSupportedCurrency(String r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        String r52 = normalize(r5);
        String[] r1 = MAIN_CURRENCIES;
        int r2 = r1.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L12;
        if (r1[r3].equals(r52) == true) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private static String normalize(String r1) {
        if (r1 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r1.trim().toUpperCase(Locale.ROOT);
    }

    private static State parseState(CoinbaseResponse r7) {
        if (r7 == null) goto L20;
        Data r1 = r7.data;
        if (r1 == null) goto L20;
        if (r1.rates == null) goto L20;
        HashMap r12 = new HashMap();
        String[] r2 = MAIN_CURRENCIES;
        int r3 = r2.length;
        int r4 = 0;
    L10:
        if (r4 >= r3) goto L16;
        String r5 = r2[r4];
        BigDecimal r6 = parseUsdRate(r5, r7.data.rates);
        if (r6 == null) goto L14;
        r12.put(r5, r6);
    L14:
        r4 = r4 + 1;
        goto L10
    L16:
        if (r12.isEmpty() == false) goto L19;
        return null;
    L19:
        return new State(r12);
    L20:
        return null;
    }

    private static BigDecimal parseUsdRate(String r3, Map<String, String> r4) {
        if ("USD".equals(r3) == true) goto L5;
        String r32 = r4.get(r3);
        if (r32 != null) goto L17;
        return null;
    L17:
        BigDecimal r0 = new BigDecimal(r32);     // Catch: Exception -> L14
        if (r0.signum() != 0) goto L12;
        return null;
    L12:
        return BigDecimal.ONE.divide(r0, 16, RoundingMode.HALF_UP);
    L14:
        e = move-exception;
        FileLog.e(e);
        return null;
    L5:
        return BigDecimal.ONE;
    }

    public static String resolveTargetCurrency(int r2, String r3) {
        String r32 = normalize(r3);
        if ("AUTO".equals(r32) == false) goto L12;
        String r22 = BillingController.getInstance().getTargetCurrency(r2, false);
        if (r22 != null) goto L7;
        return "USD";
    L7:
        String r23 = normalize(r22);
        if (isSupportedCurrency(r23) == false) goto L10;
        return r23;
    L10:
        return "USD";
    L12:
        if (TextUtils.isEmpty(r32) == false) goto L14;
    L16:
        return "USD";
    L14:
        if (isSupportedCurrency(r32) == false) goto L16;
        return r32;
    }
}
