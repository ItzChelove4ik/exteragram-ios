package org.telegram.messenger;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.util.Consumer;
import androidx.core.util.Pair;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.PendingPurchasesParams;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryProductDetailsResult;
import com.android.billingclient.api.QueryPurchasesParams;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.PhoneFormat.CallingCodeInfo;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.utils.BillingUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.LoginActivity;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes.dex */
public class BillingController implements PurchasesUpdatedListener, BillingClientStateListener {
    public static final QueryProductDetailsParams.Product PREMIUM_PRODUCT = null;
    public static ProductDetails PREMIUM_PRODUCT_DETAILS = null;
    public static final String PREMIUM_PRODUCT_ID = "telegram_premium";
    public static boolean billingClientEmpty;
    private static NumberFormat currencyInstance;
    private static NumberFormat currencyInstanceRounded;
    private static BillingController instance;
    private final BillingClient billingClient;
    private final Map<String, Integer> currencyExpMap;
    private boolean isDisconnected;
    private String lastPremiumToken;
    private String lastPremiumTransaction;
    private Runnable onCanceled;
    private final Set<String> requestingTokens;
    private final Map<String, Consumer<BillingResult>> resultListeners;
    private ArrayList<Runnable> setupListeners;
    private int triesLeft;

    /* loaded from: classes5.dex */
    public interface ProductDetailsResponseListenerLegacy {
        void onProductDetailsResponse(BillingResult r1, List<ProductDetails> r2);
    }

    /* renamed from: $r8$lambda$-5TXidx5jCxPGUeApezhyT-jxJI, reason: not valid java name */
    public static /* synthetic */ void m3890$r8$lambda$5TXidx5jCxPGUeApezhyTjxJI(AccountInstance r1, TLRPC.TL_payments_assignPlayMarketTransaction r2, TL_update.TL_updateSentPhoneCode r3) {
        LoginActivity r0 = (LoginActivity) LaunchActivity.findFragment(LoginActivity.class);
        if (r0 != null) goto L7;
        r0 = new LoginActivity(r1.getCurrentAccount());
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L7;
        r12.presentFragment(r0);
    L7:
        r0.open(((TLRPC.TL_inputStorePaymentAuthCode) r2.purpose).phone_number, r3.sent_code);
    }

    public static /* synthetic */ void $r8$lambda$3XF6orYyv05MdAHAkRCyN7aUoAc(AtomicInteger r0, AtomicInteger r1, Runnable r2) {
        if (r0.incrementAndGet() != r1.get()) goto L7;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    /* renamed from: $r8$lambda$7S7m2ajqkQMNXDgm-VT1OONva18, reason: not valid java name */
    public static /* synthetic */ void m3891$r8$lambda$7S7m2ajqkQMNXDgmVT1OONva18(AtomicInteger r0, AtomicInteger r1, Runnable r2) {
        if (r0.incrementAndGet() != r1.get()) goto L7;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    /* renamed from: $r8$lambda$9yXG5IivqqZDCK_WwEGFs3-rLdM, reason: not valid java name */
    public static /* synthetic */ void m3892$r8$lambda$9yXG5IivqqZDCK_WwEGFs3rLdM(AlertDialog[] r1) {
        AlertDialog r12 = r1[0];
        if (r12 == null) goto L6;
        r12.dismiss();
        return;
    }

    /* renamed from: $r8$lambda$EhN0Ji9MOdfwSA_8t-ZH6GDJHwk, reason: not valid java name */
    public static /* synthetic */ void m3893$r8$lambda$EhN0Ji9MOdfwSA_8tZH6GDJHwk(ProductDetailsResponseListenerLegacy r0, BillingResult r1, QueryProductDetailsResult r2) {
        r0.onProductDetailsResponse(r1, r2.getProductDetailsList());
    }

    public static /* synthetic */ void $r8$lambda$JS41Ah8eXuKqqpqtE0li5QYxAs0(AtomicInteger r0, AtomicInteger r1, Runnable r2) {
        if (r0.incrementAndGet() != r1.get()) goto L7;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    /* renamed from: $r8$lambda$Kl1-L3qkMizzBY16StFyINnCgbs, reason: not valid java name */
    public static /* synthetic */ void m3894$r8$lambda$Kl1L3qkMizzBY16StFyINnCgbs(Purchase r1, List r2, String r3, AtomicInteger r4, Runnable r5, BillingResult r6, String r7) {
        if (r6.getResponseCode() != 0) goto L8;
        FileLog.d("BillingController.launchBillingFlow, consumed " + r1.getPurchaseToken() + ": OK");
        r2.add(r3);
        if (r4.get() != r2.size()) goto L12;
        r5.run();
        return;
    L12:
        return;
    L8:
        FileLog.d("BillingController.launchBillingFlow, consumed " + r1.getPurchaseToken() + ": " + r6.getResponseCode() + " " + r6.getDebugMessage());
        r2.add(null);
        if (r4.get() != r2.size()) goto L13;
        r5.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$LcArUVCCjPStygpFblSlQgCkLBc(AlertDialog[] r3) {
        AlertDialog r0 = new AlertDialog(ApplicationLoader.applicationContext, 3);
        r3[0] = r0;
        r0.showDelayed(500);
    }

    public static /* synthetic */ void $r8$lambda$To5Qv1Atpmri71RLs5yQmjxtrpw(BillingController r0, Activity r1, AccountInstance r2, TLRPC.InputStorePaymentPurpose r3, List r4, BillingFlowParams.SubscriptionUpdateParams r5) {
        r0.lambda$launchBillingFlow$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$V955oN6zg7cFRKDEBWfBXp505cQ(BillingController r0) {
        r0.lambda$onQueriedPremiumProductDetails$14();
    }

    public static /* synthetic */ void $r8$lambda$fm6vzwhgSd8IHdkNWkSenXxj2L8(TLRPC.InputStorePaymentPurpose r1, Purchase r2, Runnable r3, BillingResult r4, String r5) {
        StringBuilder r52 = new StringBuilder("BillingController consumeGiftPurchase ");
        r52.append(r1);
        r52.append(" ");
        r52.append(r2.getOrderId());
        r52.append(" ");
        r52.append(r2.getPurchaseToken());
        r52.append(" done: ");
        if (r4.getResponseCode() != 0) goto L5;
        Object r22 = "OK";
    L6:
        r52.append(r22);
        r52.append(" ");
        r52.append(r4.getDebugMessage());
        FileLog.d(r52.toString());
        if (r3 == null) goto L10;
        r3.run();
        return;
    L10:
        return;
    L5:
        r22 = Integer.valueOf(r4.getResponseCode());
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$i9PNm2BOVtiQAF5keu4Vb9ToLiM(List r1, AtomicInteger r2, Runnable r3) {
        r1.add(null);
        if (r2.get() != r1.size()) goto L6;
        r3.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$ki18f08mA4CzVhS_YfQCrLrODws(BillingController r0, Activity r1, AccountInstance r2, TLRPC.InputStorePaymentPurpose r3, List r4, BillingFlowParams.SubscriptionUpdateParams r5, BillingResult r6, List r7) {
        r0.lambda$launchBillingFlow$4(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$tRUmf9-vJxmm34cOLTpyCP4y26A, reason: not valid java name */
    public static /* synthetic */ void m3895$r8$lambda$tRUmf9vJxmm34cOLTpyCP4y26A(BillingController r0) {
        r0.lambda$onBillingServiceDisconnected$13();
    }

    public static /* synthetic */ void $r8$lambda$vPOwYM7KOubDsonnpybn96rjcAI(BillingController r0, AlertDialog[] r1, Purchase r2, TLRPC.TL_payments_assignPlayMarketTransaction r3, AccountInstance r4, BillingResult r5, AtomicInteger r6, AtomicInteger r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$onPurchasesUpdatedInternal$10(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$wR5W3_nTENpQ97HBl749OmZW5b8(BillingController r0, BillingResult r1, List r2) {
        r0.onQueriedPremiumProductDetails(r1, r2);
    }

    static {
        PREMIUM_PRODUCT = QueryProductDetailsParams.Product.newBuilder().setProductType("subs").setProductId(PREMIUM_PRODUCT_ID).build();
    }

    private BillingController(Context r2) {
        this.resultListeners = new HashMap();
        this.requestingTokens = Collections.newSetFromMap(new ConcurrentHashMap());
        this.currencyExpMap = new HashMap();
        this.setupListeners = new ArrayList();
        this.triesLeft = 0;
        this.billingClient = BillingClient.newBuilder(r2).enablePendingPurchases(PendingPurchasesParams.newBuilder().enableOneTimeProducts().build()).setListener(this).build();
    }

    private String getCurrencyByPhone(String r8) {
        if (TextUtils.isEmpty(r8) == false) goto L5;
        return null;
    L5:
        String r7 = PhoneFormat.stripExceptNumbers(r8);
        if (TextUtils.isEmpty(r7) == false) goto L8;
        return null;
    L8:
        CallingCodeInfo r72 = PhoneFormat.getInstance().findCallingCodeInfo(r7);
        if (r72 == null) goto L35;
        ArrayList<String> r82 = r72.countries;
        if (r82 == null) goto L35;
        if (r82.isEmpty() == true) goto L35;
        String r83 = r72.countries.get(0);
        String r2 = Locale.getDefault().getCountry();
        if (TextUtils.isEmpty(r2) == true) goto L23;
        ArrayList<String> r73 = r72.countries;
        int r3 = r73.size();
        int r4 = 0;
    L18:
        if (r4 >= r3) goto L23;
        String r5 = r73.get(r4);
        r4 = r4 + 1;
        String r52 = r5;
        if (r2.equalsIgnoreCase(r52) == false) goto L18;
        r83 = r52;
    L23:
        if (TextUtils.isEmpty(r83) == true) goto L35;
        if (r83.charAt(0) == '_') goto L35;
        Currency r74 = Currency.getInstance(new Locale(_UrlKt.FRAGMENT_ENCODE_SET, r83.toUpperCase(Locale.ROOT)));     // Catch: Exception -> L31
        if (r74 == null) goto L33;
        return r74.getCurrencyCode();
    L33:
        return null;
    L31:
        e = move-exception;
        FileLog.e(e);
    L35:
        return null;
    }

    public static BillingController getInstance() {
        if (instance != null) goto L6;
        instance = new BillingController(ApplicationLoader.applicationContext);
    L6:
        return instance;
    }

    public static String getResponseCodeString(int r1) {
        if (r1 == 12) goto L31;
        switch(r1) {
            case -3: goto L29;
            case -2: goto L27;
            case -1: goto L25;
            case 0: goto L23;
            case 1: goto L21;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            case 8: goto L7;
            default: goto L5;
        };
    L5:
        return "BILLING_UNKNOWN_ERROR";
    L7:
        return "ITEM_NOT_OWNED";
    L9:
        return "ITEM_ALREADY_OWNED";
    L11:
        return MediaError.ERROR_TYPE_ERROR;
    L13:
        return "DEVELOPER_ERROR";
    L15:
        return "ITEM_UNAVAILABLE";
    L17:
        return "BILLING_UNAVAILABLE";
    L19:
        return "SERVICE_UNAVAILABLE";
    L21:
        return "USER_CANCELED";
    L23:
        return "OK";
    L25:
        return "SERVICE_DISCONNECTED";
    L27:
        return "FEATURE_NOT_SUPPORTED";
    L29:
        return "SERVICE_TIMEOUT";
    L31:
        return "NETWORK_ERROR";
    }

    private /* synthetic */ void lambda$launchBillingFlow$1(Activity r8, AccountInstance r9, TLRPC.InputStorePaymentPurpose r10, List r11, BillingFlowParams.SubscriptionUpdateParams r12) {
        launchBillingFlow(r8, r9, r10, r11, r12, true);
    }

    private /* synthetic */ void lambda$launchBillingFlow$4(Activity r8, AccountInstance r9, TLRPC.InputStorePaymentPurpose r10, List r11, BillingFlowParams.SubscriptionUpdateParams r12, BillingResult r13, List r14) {
        if (r13.getResponseCode() != 0) goto L20;
        FileLog.d("BillingController.launchBillingFlow, checked consumables: OK");
        BillingController$$ExternalSyntheticLambda14 r0 = new BillingController$$ExternalSyntheticLambda14(this, r8, r9, r10, r11, r12);
        AtomicInteger r112 = new AtomicInteger(0);
        ArrayList r92 = new ArrayList();
        Iterator r142 = r14.iterator();
    L6:
        if (r142.hasNext() == false) goto L17;
        Purchase r82 = (Purchase) r142.next();
        if (r82.isAcknowledged() == true) goto L9;
        r112.incrementAndGet();
        onPurchasesUpdatedInternal(BillingResult.newBuilder().setResponseCode(0).build(), Collections.singletonList(r82), new BillingController$$ExternalSyntheticLambda16(r92, r112, r0));
        goto L6
    L9:
        Iterator r7 = r11.iterator();
    L11:
        if (r7.hasNext() == false) goto L6;
        String r102 = ((BillingFlowParams.ProductDetailsParams) r7.next()).zza().getProductId();
        if (r82.getProducts().contains(r102) == false) goto L11;
        r112.incrementAndGet();
        FileLog.d("BillingController.launchBillingFlow, consuming " + r82.getPurchaseToken());
        this.billingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(r82.getPurchaseToken()).build(), new BillingController$$ExternalSyntheticLambda15(r82, r92, r102, r112, r0));
        goto L6
    L17:
        if (r112.get() != 0) goto L31;
        r0.run();
        return;
    L31:
        return;
    L20:
        FileLog.d("BillingController.launchBillingFlow, checked consumables: " + r13.getResponseCode() + " " + r13.getDebugMessage());
        launchBillingFlow(r8, r9, r10, r11, r12, false);
    }

    private /* synthetic */ void lambda$onBillingServiceDisconnected$13() {
        startConnection();
    }

    private /* synthetic */ void lambda$onPurchasesUpdatedInternal$10(AlertDialog[] r5, Purchase r6, TLRPC.TL_payments_assignPlayMarketTransaction r7, AccountInstance r8, BillingResult r9, AtomicInteger r10, AtomicInteger r11, Runnable r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new BillingController$$ExternalSyntheticLambda4(r5));
        this.requestingTokens.remove(r6.getPurchaseToken());
        if ((r13 instanceof TLRPC.Updates) == false) goto L17;
        FileLog.d("BillingController.onPurchasesUpdatedInternal: " + r6.getOrderId() + " purchase is purchased and now assigned");
        if ((r7.purpose instanceof TLRPC.TL_inputStorePaymentAuthCode) == false) goto L9;
        ArrayList r52 = MessagesController.findUpdatesAndRemove((TLRPC.Updates) r13, TL_update.TL_updateSentPhoneCode.class);
        int r0 = r52.size();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L9;
        Object r2 = r52.get(r1);
        r1 = r1 + 1;
        AndroidUtilities.runOnUIThread(new BillingController$$ExternalSyntheticLambda5(r8, r7, (TL_update.TL_updateSentPhoneCode) r2));
    L9:
        r8.getMessagesController().processUpdates((TLRPC.Updates) r13, false);
        Iterator<String> r53 = r6.getProducts().iterator();
    L11:
        if (r53.hasNext() == false) goto L15;
        Consumer<BillingResult> r82 = this.resultListeners.remove(r53.next());
        if (r82 == null) goto L11;
        r82.accept(r9);
        goto L11
    L15:
        consumeGiftPurchase(r6, r7.purpose, new BillingController$$ExternalSyntheticLambda6(r10, r11, r12));
        BillingUtilities.cleanupPurchase(r6);
        return;
    L17:
        StringBuilder r54 = new StringBuilder("BillingController.onPurchasesUpdatedInternal: ");
        r54.append(r6.getOrderId());
        r54.append(" purchase is purchased and failed to assign: ");
        if (r14 != null) goto L20;
        String r83 = null;
    L21:
        r54.append(r83);
        FileLog.d(r54.toString());
        Runnable r55 = this.onCanceled;
        if (r55 == null) goto L24;
        r55.run();
        this.onCanceled = null;
    L24:
        if (r14 == null) goto L26;
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.billingConfirmPurchaseError, new Object[]{r7, r14});
    L26:
        AndroidUtilities.runOnUIThread(new BillingController$$ExternalSyntheticLambda7(r10, r11, r12));
        return;
    L20:
        r83 = r14.text;
        goto L21
    }

    private /* synthetic */ void lambda$onQueriedPremiumProductDetails$14() {
        queryProductDetails(Collections.singletonList(PREMIUM_PRODUCT), new BillingController$$ExternalSyntheticLambda1(this));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private void onQueriedPremiumProductDetails(BillingResult r3, List<ProductDetails> r4) {
        FileLog.d("Billing: Query product details finished " + r3 + ", " + r4);
        if (r3.getResponseCode() != 0) goto L16;
        Iterator<ProductDetails> r32 = r4.iterator();
    L6:
        if (r32.hasNext() == false) goto L11;
        ProductDetails r42 = r32.next();
        if (r42.getProductId().equals(PREMIUM_PRODUCT_ID) == false) goto L6;
        PREMIUM_PRODUCT_DETAILS = r42;
        goto L6
    L11:
        if (PREMIUM_PRODUCT_DETAILS != null) goto L14;
        switchToInvoice();
        return;
    L14:
        switchBackFromInvoice();
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.billingProductDetailsUpdated, new Object[0]);
        return;
    L16:
        switchToInvoice();
        int r33 = this.triesLeft - 1;
        this.triesLeft = r33;
        if (r33 > 0) goto L19;
        return;
    L19:
        if (r33 != 2) goto L21;
        long r34 = 1000;
    L22:
        AndroidUtilities.runOnUIThread(new BillingController$$ExternalSyntheticLambda11(this), r34);
        return;
    L21:
        r34 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        goto L22
    }

    private void switchBackFromInvoice() {
        if (billingClientEmpty == true) goto L5;
        return;
    L5:
        billingClientEmpty = false;
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.billingProductDetailsUpdated, new Object[0]);
    }

    private void switchToInvoice() {
        if (billingClientEmpty == false) goto L5;
        return;
    L5:
        billingClientEmpty = true;
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.billingProductDetailsUpdated, new Object[0]);
    }

    public void addResultListener(String r1, Consumer<BillingResult> r2) {
        this.resultListeners.put(r1, r2);
    }

    public void consumeGiftPurchase(Purchase r4, TLRPC.InputStorePaymentPurpose r5, Runnable r6) {
        if ((r5 instanceof TLRPC.TL_inputStorePaymentGiftPremium) == false) goto L5;
    L18:
        FileLog.d("BillingController consumeGiftPurchase " + r5 + " " + r4.getOrderId() + " " + r4.getPurchaseToken());
        this.billingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(r4.getPurchaseToken()).build(), new BillingController$$ExternalSyntheticLambda12(r5, r4, r6));
        return;
    L5:
        if ((r5 instanceof TLRPC.TL_inputStorePaymentPremiumGiftCode) == true) goto L18;
        if ((r5 instanceof TLRPC.TL_inputStorePaymentStarsTopup) == true) goto L18;
        if ((r5 instanceof TLRPC.TL_inputStorePaymentStarsGift) == true) goto L18;
        if ((r5 instanceof TLRPC.TL_inputStorePaymentPremiumGiveaway) == true) goto L18;
        if ((r5 instanceof TLRPC.TL_inputStorePaymentStarsGiveaway) == true) goto L18;
        if ((r5 instanceof TLRPC.TL_inputStorePaymentAuthCode) == true) goto L18;
    }

    public String formatCurrency(long r2, String r4) {
        return formatCurrency(r2, r4, getCurrencyExp(r4));
    }

    public String formatCurrency(long r7, String r9, int r10) {
        return formatCurrency(r7, r9, r10, false);
    }

    public String formatCurrency(long r5, String r7, int r8, boolean r9) {
        if (r7 == null) goto L31;
        if (r7.isEmpty() == true) goto L31;
        if ("TON".equalsIgnoreCase(r7) == false) goto L11;
        return "TON " + (r5 / 1.0E9d);
    L11:
        if ("XTR".equalsIgnoreCase(r7) == true) goto L13;
        Currency r4 = Currency.getInstance(r7);     // Catch: Exception -> L19
        if (r4 == null) goto L29;
        if (currencyInstance != null) goto L21;
        currencyInstance = NumberFormat.getCurrencyInstance();     // Catch: Exception -> L19
    L21:
        currencyInstance.setCurrency(r4);     // Catch: Exception -> L19
        if (r9 == false) goto L25;
        currencyInstance.setMaximumFractionDigits(0);     // Catch: Exception -> L19
        currencyInstance.setMinimumFractionDigits(0);     // Catch: Exception -> L19
        return currencyInstance.format(Math.round(r5 / Math.pow(10.0d, r8)));
    L25:
        int r42 = r4.getDefaultFractionDigits();     // Catch: Exception -> L19
        currencyInstance.setMinimumFractionDigits(r42);     // Catch: Exception -> L19
        currencyInstance.setMaximumFractionDigits(r42);     // Catch: Exception -> L19
        return currencyInstance.format(r5 / Math.pow(10.0d, r8));
    L29:
        return r5 + " " + r7;
    L19:
        e = move-exception;
        FileLog.e(e);
        goto L29
    L13:
        return "XTR " + LocaleController.formatNumber(r5, ',');
    L31:
        return String.valueOf(r5);
    }

    public int getCurrencyExp(String r2) {
        BillingUtilities.extractCurrencyExp(this.currencyExpMap);
        return this.currencyExpMap.getOrDefault(r2, 0).intValue();
    }

    public String getLastPremiumToken() {
        return this.lastPremiumToken;
    }

    public String getLastPremiumTransaction() {
        return this.lastPremiumTransaction;
    }

    public String getTargetCurrency(int r1, boolean r2) {
        if (r2 == false) goto L6;
        return "USD";
    L6:
        return getCurrencyByPhone(UserConfig.getInstance(r1).getClientPhone());
    }

    public boolean isReady() {
        return this.billingClient.isReady();
    }

    public void launchBillingFlow(Activity r8, AccountInstance r9, TLRPC.InputStorePaymentPurpose r10, List<BillingFlowParams.ProductDetailsParams> r11) {
        launchBillingFlow(r8, r9, r10, r11, null, false);
    }

    public void launchBillingFlow(Activity r8, AccountInstance r9, TLRPC.InputStorePaymentPurpose r10, List<BillingFlowParams.ProductDetailsParams> r11, BillingFlowParams.SubscriptionUpdateParams r12, boolean r13) {
        if (isReady() == false) goto L26;
        if (r8 != null) goto L7;
        return;
    L7:
        if ((r10 instanceof TLRPC.TL_inputStorePaymentGiftPremium) == false) goto L9;
    L14:
        if (r13 == true) goto L17;
        FileLog.d("BillingController.launchBillingFlow, checking consumables");
        queryPurchases("inapp", new BillingController$$ExternalSyntheticLambda13(this, r8, r9, r10, r11, r12));
        return;
    L17:
        if (r13 == false) goto L19;
        FileLog.d("BillingController.launchBillingFlow, consumables checked, launching flow...");
    L19:
        Pair<String, String> r7 = BillingUtilities.createDeveloperPayload(r10, r9);
        String r82 = r7.first;
        String r72 = r7.second;
        BillingFlowParams.Builder r92 = BillingFlowParams.newBuilder().setObfuscatedAccountId(r82).setObfuscatedProfileId(r72).setProductDetailsParamsList(r11);
        if (r12 == null) goto L22;
        r92.setSubscriptionUpdateParams(r12);
    L22:
        int r93 = this.billingClient.launchBillingFlow(r8, r92.build()).getResponseCode();
        if (r93 == 0) goto L27;
        FileLog.d("Billing: Launch Error: " + r93 + ", " + r82 + ", " + r72);
        return;
    L27:
        return;
    L9:
        if ((r10 instanceof TLRPC.TL_inputStorePaymentStarsTopup) == true) goto L14;
        if ((r10 instanceof TLRPC.TL_inputStorePaymentStarsGift) == false) goto L17;
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public void onBillingServiceDisconnected() {
        FileLog.d("Billing: Service disconnected");
        if (this.isDisconnected == false) goto L5;
        int r0 = 15000;
    L6:
        this.isDisconnected = true;
        AndroidUtilities.runOnUIThread(new BillingController$$ExternalSyntheticLambda3(this), r0);
        return;
    L5:
        r0 = 5000;
        goto L6
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public void onBillingSetupFinished(BillingResult r3) {
        FileLog.d("Billing: Setup finished with result " + r3);
        if (r3.getResponseCode() != 0) goto L16;
        int r32 = 0;
        this.isDisconnected = false;
        this.triesLeft = 3;
        queryProductDetails(Collections.singletonList(PREMIUM_PRODUCT), new BillingController$$ExternalSyntheticLambda1(this));     // Catch: Exception -> L7
    L9:
        queryPurchases("inapp", new BillingController$$ExternalSyntheticLambda2(this));
        queryPurchases("subs", new BillingController$$ExternalSyntheticLambda2(this));
        if (this.setupListeners.isEmpty() == true) goto L22;
    L11:
        int r0 = this.setupListeners.size();
        ArrayList<Runnable> r1 = this.setupListeners;
        if (r32 >= r0) goto L14;
        AndroidUtilities.runOnUIThread(r1.get(r32));
        r32 = r32 + 1;
        goto L11
    L14:
        r1.clear();
        return;
    L22:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    L16:
        if (this.isDisconnected == true) goto L24;
        switchToInvoice();
        return;
    }

    @Override // com.android.billingclient.api.PurchasesUpdatedListener
    public void onPurchasesUpdated(BillingResult r2, List<Purchase> r3) {
        onPurchasesUpdatedInternal(r2, r3, null);
    }

    public void onPurchasesUpdatedInternal(BillingResult r15, List<Purchase> r16, Runnable r17) {
        FileLog.d("Billing: Purchases updated: " + r15 + ", " + r16);
        if (r15.getResponseCode() != 0) goto L5;
        if (r16 == null) goto L51;
        if (r16.isEmpty() == true) goto L51;
        AtomicInteger r8 = new AtomicInteger(0);
        AtomicInteger r7 = new AtomicInteger(0);
        this.lastPremiumTransaction = null;
        Iterator<Purchase> r11 = r16.iterator();
    L19:
        if (r11.hasNext() == false) goto L47;
        Purchase r3 = r11.next();
        if (r3.getProducts().contains(PREMIUM_PRODUCT_ID) == false) goto L24;
        this.lastPremiumTransaction = r3.getOrderId();
        this.lastPremiumToken = r3.getPurchaseToken();
    L24:
        if (this.requestingTokens.contains(r3.getPurchaseToken()) == false) goto L26;
        FileLog.d("BillingController.onPurchasesUpdatedInternal: " + r3.getOrderId() + " purchase is already requesting...");
        goto L19
    L26:
        if (r3.getPurchaseState() == 1) goto L27;
        FileLog.d("BillingController.onPurchasesUpdatedInternal: " + r3.getOrderId() + " purchase is (state=" + r3.getPurchaseState() + "), (isAcknowledged=" + r3.isAcknowledged() + ")");
        goto L19
    L27:
        Pair<AccountInstance, TLRPC.InputStorePaymentPurpose> r0 = BillingUtilities.extractDeveloperPayload(r3);
        if (r0 == null) goto L43;
        if (r0.first == null) goto L43;
        if (r0.second == null) goto L43;
        if (r3.isAcknowledged() == false) goto L36;
        FileLog.d("BillingController.onPurchasesUpdatedInternal: " + r3.getOrderId() + " purchase is purchased and acknowledged: consuming");
        r8.incrementAndGet();
        consumeGiftPurchase(r3, r0.second, new BillingController$$ExternalSyntheticLambda10(r7, r8, r17));
        goto L19
    L36:
        FileLog.d("BillingController.onPurchasesUpdatedInternal: " + r3.getOrderId() + " purchase is purchased and not acknowledged: assigning (accountId=" + r0.first.getCurrentAccount() + ") (purpose=" + r0.second + ")");
        this.requestingTokens.add(r3.getPurchaseToken());
        TLRPC.TL_payments_assignPlayMarketTransaction r4 = new TLRPC.TL_payments_assignPlayMarketTransaction();
        TLRPC.TL_dataJSON r1 = new TLRPC.TL_dataJSON();
        r4.receipt = r1;
        r1.data = r3.getOriginalJson();
        r4.purpose = r0.second;
        AlertDialog[] r2 = new AlertDialog[1];
        AndroidUtilities.runOnUIThread(new BillingController$$ExternalSyntheticLambda8(r2));
        r8.incrementAndGet();
        AccountInstance r5 = r0.first;
        if ((r4.purpose instanceof TLRPC.TL_inputStorePaymentAuthCode) == false) goto L40;
        int r02 = 65608;
    L41:
        r5.getConnectionsManager().sendRequest(r4, new BillingController$$ExternalSyntheticLambda9(this, r2, r3, r4, r5, r15, r7, r8, r17), r02);
        goto L19
    L40:
        r02 = 65600;
    L43:
        FileLog.d("BillingController.onPurchasesUpdatedInternal: " + r3.getOrderId() + " purchase is purchased, but failed to extract saved payload");
        goto L19
    L47:
        if (r8.get() != 0) goto L72;
        if (r17 == null) goto L73;
        r17.run();
        return;
    L73:
        return;
    L72:
        return;
    L51:
        if (r17 == null) goto L74;
        r17.run();
        return;
    L74:
        return;
    L5:
        if (r15.getResponseCode() != 1) goto L7;
        PremiumPreviewFragment.sentPremiumBuyCanceled();
    L7:
        Runnable r152 = this.onCanceled;
        if (r152 == null) goto L10;
        r152.run();
        this.onCanceled = null;
    L10:
        if (r17 == null) goto L71;
        r17.run();
        return;
    }

    public void queryProductDetails(List<QueryProductDetailsParams.Product> r2, ProductDetailsResponseListenerLegacy r3) {
        if (isReady() == false) goto L6;
        this.billingClient.queryProductDetailsAsync(QueryProductDetailsParams.newBuilder().setProductList(r2).build(), new BillingController$$ExternalSyntheticLambda0(r3));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Billing: Controller should be ready for this call!");
    }

    public void queryPurchases(String r2, PurchasesResponseListener r3) {
        this.billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType(r2).build(), r3);
    }

    public void setOnCanceled(Runnable r1) {
        this.onCanceled = r1;
    }

    public void startConnection() {
        if (isReady() == true) goto L15;
        BillingUtilities.extractCurrencyExp(this.currencyExpMap);     // Catch: Exception -> L9
        if (BuildVars.useInvoiceBilling() == true) goto L14;
        this.billingClient.startConnection(this);     // Catch: Exception -> L9
        return;
    L14:
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public boolean startManageSubscription(Context r4, String r5) {
        r4.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://play.google.com/store/account/subscriptions?sku=%s&package=%s", new Object[]{r5, r4.getPackageName()}))));     // Catch: ActivityNotFoundException -> L5
        return true;
    L5:
        return false;
    }

    public void whenSetuped(Runnable r1) {
        this.setupListeners.add(r1);
    }
}
