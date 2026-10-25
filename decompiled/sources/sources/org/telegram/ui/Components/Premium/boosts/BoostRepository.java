package org.telegram.ui.Components.Premium.boosts;

import android.text.TextUtils;
import android.util.Pair;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.QueryProductDetailsParams;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public abstract class BoostRepository {
    private static HashMap<Integer, Pair<Long, List<TLRPC.TL_premiumGiftCodeOption>>> cachedGiftOptions;

    public static /* synthetic */ void $r8$lambda$01l9JSNrUwLbKPMPxlNeMSAZxK8(TLRPC.TL_error r0, Utilities.Callback r1, TLObject r2, MessagesController r3, Utilities.Callback r4) {
        if (r0 == null) goto L6;
        r1.run(r0);
        return;
    L6:
        if ((r2 instanceof TL_stories.TL_premium_myBoosts) == false) goto L9;
        TL_stories.TL_premium_myBoosts r22 = (TL_stories.TL_premium_myBoosts) r2;
        r3.putUsers(r22.users, false);
        r3.putChats(r22.chats, false);
        r4.run(r22);
        return;
    }

    public static /* synthetic */ void $r8$lambda$0ynvfwDwlETv4N5k_tFJOsBhlDc(Utilities.Callback r8, MessagesController r9, TLRPC.TL_inputInvoicePremiumGiftCode r10, BaseFragment r11, Utilities.Callback r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda22(r14, r8, r13, r9, r10, r11, r12));
    }

    /* renamed from: $r8$lambda$3os-lJHtVF5JIB7UG6Wwe0YSKaQ, reason: not valid java name */
    public static /* synthetic */ void m12079$r8$lambda$3oslJHtVF5JIB7UG6Wwe0YSKaQ(Utilities.Callback r8, MessagesController r9, TLRPC.TL_inputInvoicePremiumGiftCode r10, BaseFragment r11, Utilities.Callback r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda28(r14, r8, r13, r9, r10, r11, r12));
    }

    /* renamed from: $r8$lambda$4JpHzIJIb5bMpL6i3-zr-1vThO4, reason: not valid java name */
    public static /* synthetic */ void m12080$r8$lambda$4JpHzIJIb5bMpL6i3zr1vThO4(Utilities.Callback r0, HashMap r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$6YUWeNj5TBmUQU6EBQNuQA00DlA(Utilities.Callback r2, Utilities.Callback r3, PaymentFormActivity.InvoiceStatus r4) {
        if (r4 != PaymentFormActivity.InvoiceStatus.PAID) goto L7;
        r2.run(null);
        return;
    L7:
        if (r4 == PaymentFormActivity.InvoiceStatus.PENDING) goto L10;
        r3.run(null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$6mIo4ZzVe3n_3CBwnwUk9qDXvKQ(TLRPC.TL_error r0, Utilities.Callback r1, TLObject r2, Utilities.Callback r3) {
        if (r0 == null) goto L6;
        r1.run(r0);
        return;
    L6:
        if ((r2 instanceof TLRPC.payments_GiveawayInfo) == false) goto L9;
        r3.run((TLRPC.payments_GiveawayInfo) r2);
        return;
    }

    public static /* synthetic */ void $r8$lambda$7baPAvWLWuDmI8IBz6DwQS52J2g(TLRPC.TL_error r2, Utilities.Callback r3, TLObject r4, List r5, BillingResult r6, Utilities.Callback r7, BaseFragment r8, TLRPC.TL_inputStorePaymentPremiumGiftCode r9) {
        if (r2 == null) goto L5;
        r3.run(r2);
        return;
    L5:
        if (r4 == null) goto L8;
        BillingController.getInstance().addResultListener(((ProductDetails) r5.get(0)).getProductId(), new BoostRepository$$ExternalSyntheticLambda39(r6, r7));
        BillingController.getInstance().setOnCanceled(new BoostRepository$$ExternalSyntheticLambda40(r3));
        BillingController.getInstance().launchBillingFlow(r8.getParentActivity(), AccountInstance.getInstance(UserConfig.selectedAccount), r9, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails((ProductDetails) r5.get(0)).build()));
        return;
    }

    public static /* synthetic */ void $r8$lambda$7cbdrUvBWe5qggkb3uevHg8FLso(Utilities.Callback r1) {
        r1.run(null);
    }

    /* renamed from: $r8$lambda$8C8v8HmKNq3sCz_t0T-YlQjzcFA, reason: not valid java name */
    public static /* synthetic */ void m12081$r8$lambda$8C8v8HmKNq3sCz_t0TYlQjzcFA(Utilities.Callback r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda9(r4, r1, r3, r2));
    }

    public static /* synthetic */ void $r8$lambda$93mU_nmYohLDMzCY8V27ZWzqs0Q(MessagesStorage r1, Utilities.Callback r2) {
        HashMap<Long, Integer> r12 = r1.getSmallGroupsParticipantsCount();
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isEmpty() == true) goto L10;
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda12(r2, r12));
        return;
    }

    /* renamed from: $r8$lambda$9IAmvtx-Dgt8WGAwH1-j51UxhvQ, reason: not valid java name */
    public static /* synthetic */ void m12082$r8$lambda$9IAmvtxDgt8WGAwH1j51UxhvQ(Utilities.Callback r9, List r10, BillingResult r11, Utilities.Callback r12, BaseFragment r13, TLRPC.TL_inputStorePaymentPremiumGiftCode r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda38(r16, r9, r15, r10, r11, r12, r13, r14));
    }

    /* renamed from: $r8$lambda$9nAfEogN0soBn_lXcWgxn2tWJ-8, reason: not valid java name */
    public static /* synthetic */ void m12083$r8$lambda$9nAfEogN0soBn_lXcWgxn2tWJ8(TLRPC.Chat r0, int r1, List r2, Utilities.Callback r3) {
        if (r0 != null) goto L4;
        saveGiftOptionsToCache(r1, r2);
    L4:
        r3.run(r2);
    }

    /* renamed from: $r8$lambda$AYphyHq7F1OpltBjRDX6X-s4vps, reason: not valid java name */
    public static /* synthetic */ void m12084$r8$lambda$AYphyHq7F1OpltBjRDX6Xs4vps(TLRPC.TL_error r0, Utilities.Callback r1, TLObject r2, MessagesController r3, Utilities.Callback r4) {
        if (r0 == null) goto L6;
        r1.run(r0);
        return;
    L6:
        if ((r2 instanceof TL_stories.TL_premium_myBoosts) == false) goto L9;
        TL_stories.TL_premium_myBoosts r22 = (TL_stories.TL_premium_myBoosts) r2;
        r3.putUsers(r22.users, false);
        r3.putChats(r22.chats, false);
        r4.run(r22);
        return;
    }

    public static /* synthetic */ void $r8$lambda$C4jQG5DhGfHGAkCMZQkFcRhfFTU(TLObject r2, MessagesController r3, Utilities.Callback r4, Utilities.Callback r5, TLRPC.TL_error r6) {
        if ((r2 instanceof TLRPC.TL_payments_checkedGiftCode) == false) goto L5;
        TLRPC.TL_payments_checkedGiftCode r22 = (TLRPC.TL_payments_checkedGiftCode) r2;
        r3.putChats(r22.chats, false);
        r3.putUsers(r22.users, false);
        r4.run(r22);
    L5:
        r5.run(r6);
    }

    public static /* synthetic */ void $r8$lambda$CMETRqHVgoKx050Ho2CJyPi0jec(Utilities.Callback r6, TLObject r7, TLRPC.TL_error r8) {
        if (r7 == null) goto L34;
        TLRPC.TL_help_countriesList r72 = (TLRPC.TL_help_countriesList) r7;
        HashMap r82 = new HashMap();
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L5:
        if (r2 >= r72.countries.size()) goto L21;
        TLRPC.TL_help_country r3 = r72.countries.get(r2);
        boolean r4 = r3.iso2.equalsIgnoreCase("FT");
        String r5 = r3.name;
        if (r5 == null) goto L10;
        r3.default_name = r5;
    L10:
        if (r3.hidden == false) goto L13;
        if (r4 == true) goto L13;
    L19:
        r2 = r2 + 1;
    L13:
        if (r4 == false) goto L15;
        String r42 = LocaleController.getString(R.string.Fragment);
        r3.default_name = r42;
        r3.name = r42;
    L15:
        String r43 = r3.default_name.substring(0, 1).toUpperCase();
        List r52 = (List) r82.get(r43);
        if (r52 != null) goto L18;
        r52 = new ArrayList();
        r82.put(r43, r52);
        r0.add(r43);
    L18:
        r52.add(r3);
        goto L19
    L21:
        if (LocaleController.getInstance().getCurrentLocale() == null) goto L23;
        Locale r73 = LocaleController.getInstance().getCurrentLocale();
    L24:
        Collator r74 = Collator.getInstance(r73);
        Objects.requireNonNull(r74);
        BoostRepository$$ExternalSyntheticLambda23 r1 = new BoostRepository$$ExternalSyntheticLambda23(r74);
        Collections.sort(r0, r1);
        Iterator r75 = r82.values().iterator();
    L26:
        if (r75.hasNext() == false) goto L28;
        Collections.sort((List) r75.next(), new BoostRepository$$ExternalSyntheticLambda48(r1));
        goto L26
    L28:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda49(r6, r82, r0));
        return;
    L23:
        r73 = Locale.getDefault();
        goto L24
    }

    public static /* synthetic */ void $r8$lambda$D14eR3Q20yjKqtSujJDqV16f_sM(TLRPC.Chat r5, int r6, Utilities.Callback r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof Vector) == false) goto L23;
        Vector r82 = (Vector) r8;
        ArrayList r92 = new ArrayList();
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L6:
        if (r1 >= r82.objects.size()) goto L12;
        TLRPC.TL_premiumGiftCodeOption r2 = (TLRPC.TL_premiumGiftCodeOption) r82.objects.get(r1);
        r92.add(r2);
        if (r2.store_product == null) goto L10;
        r0.add(QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r2.store_product).build());
    L10:
        r1 = r1 + 1;
        goto L6
    L12:
        if (r0.isEmpty() == false) goto L14;
    L18:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda10(r5, r6, r92, r7));
        return;
    L14:
        if (isGoogleBillingAvailable() == false) goto L18;
        BillingController.getInstance().queryProductDetails(r0, new BoostRepository$$ExternalSyntheticLambda11(r92, r5, r6, r7));
        return;
    }

    /* renamed from: $r8$lambda$Essw-BawrvnmZnHQ3bnrVP4EnZM, reason: not valid java name */
    public static /* synthetic */ void m12085$r8$lambda$EsswBawrvnmZnHQ3bnrVP4EnZM(TLRPC.TL_error r2, Utilities.Callback r3, TLObject r4, List r5, BillingResult r6, Utilities.Callback r7, BaseFragment r8, TLRPC.TL_inputStorePaymentPremiumGiveaway r9) {
        if (r2 == null) goto L5;
        r3.run(r2);
        return;
    L5:
        if (r4 == null) goto L8;
        BillingController.getInstance().addResultListener(((ProductDetails) r5.get(0)).getProductId(), new BoostRepository$$ExternalSyntheticLambda41(r6, r7));
        BillingController.getInstance().setOnCanceled(new BoostRepository$$ExternalSyntheticLambda42(r3));
        BillingController.getInstance().launchBillingFlow(r8.getParentActivity(), AccountInstance.getInstance(UserConfig.selectedAccount), r9, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails((ProductDetails) r5.get(0)).build()));
        return;
    }

    /* renamed from: $r8$lambda$J0-yHjds9RgDYKvt_T-ecd4MSrg, reason: not valid java name */
    public static /* synthetic */ void m12086$r8$lambda$J0yHjds9RgDYKvt_Tecd4MSrg(Utilities.Callback r2, Utilities.Callback r3, PaymentFormActivity.InvoiceStatus r4) {
        if (r4 != PaymentFormActivity.InvoiceStatus.PAID) goto L7;
        r2.run(null);
        return;
    L7:
        if (r4 == PaymentFormActivity.InvoiceStatus.PENDING) goto L10;
        r3.run(null);
        return;
    }

    /* renamed from: $r8$lambda$JLbra93APvkVJSuYQ4UO-wsuxI4, reason: not valid java name */
    public static /* synthetic */ void m12087$r8$lambda$JLbra93APvkVJSuYQ4UOwsuxI4(Utilities.Callback r1) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda46(r1));
    }

    public static /* synthetic */ void $r8$lambda$M_DuwA6NnZQ_WwVEeKpsO70zSsg(Utilities.Callback r1) {
        r1.run(null);
    }

    public static /* synthetic */ void $r8$lambda$MvfVwe531_JXPz1NF_6y9mFwFNI(Utilities.Callback r1) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda50(r1));
    }

    public static /* synthetic */ void $r8$lambda$P9bJSmysFud1RqKZaihE1st3qxQ(TLRPC.TL_inputStorePaymentPremiumGiftCode r7, TLRPC.TL_premiumGiftCodeOption r8, ConnectionsManager r9, Utilities.Callback r10, Utilities.Callback r11, BaseFragment r12, BillingResult r13, List r14) {
        r7.currency = ((ProductDetails) r14.get(0)).getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
        r7.amount = (long) ((r0.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r8.currency)));
        TLRPC.TL_payments_canPurchaseStore r82 = new TLRPC.TL_payments_canPurchaseStore();
        r82.purpose = r7;
        r9.sendRequest(r82, new BoostRepository$$ExternalSyntheticLambda30(r10, r14, r13, r11, r12, r7));
    }

    public static /* synthetic */ void $r8$lambda$SapfoNmYCyrXnj1KgZ8WWB5oUOs(Utilities.Callback r0, List r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$SnbGWbKyrk5tdmfHyU6qTbSB00M(Utilities.Callback r1, Map r2, List r3) {
        r1.run(new Pair(r2, r3));
    }

    /* renamed from: $r8$lambda$T-cbho3VyDg1-WkbTTbUJuHfETs, reason: not valid java name */
    public static /* synthetic */ void m12088$r8$lambda$Tcbho3VyDg1WkbTTbUJuHfETs(TLRPC.TL_error r0, Utilities.Callback r1, Utilities.Callback r2) {
        if (r0 == null) goto L5;
        r1.run(r0);
        return;
    L5:
        r2.run(null);
    }

    public static /* synthetic */ void $r8$lambda$TOm6ENsklfEHu9RpiMaLZC8JNf0(Utilities.Callback r6, TLObject r7, TLRPC.TL_error r8) {
        if (r7 == null) goto L35;
        TLRPC.TL_help_countriesList r72 = (TLRPC.TL_help_countriesList) r7;
        HashMap r82 = new HashMap();
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L5:
        if (r2 >= r72.countries.size()) goto L21;
        TLRPC.TL_help_country r3 = r72.countries.get(r2);
        String r4 = r3.name;
        if (r4 == null) goto L10;
        r3.default_name = r4;
    L10:
        if (r3.hidden == true) goto L19;
        if (r3.iso2.equalsIgnoreCase("FT") == true) goto L19;
        String r42 = r3.default_name.substring(0, 1).toUpperCase();
        List r5 = (List) r82.get(r42);
        if (r5 != null) goto L18;
        r5 = new ArrayList();
        r82.put(r42, r5);
        r0.add(r42);
    L18:
        r5.add(r3);
    L19:
        r2 = r2 + 1;
        goto L5
    L21:
        if (LocaleController.getInstance().getCurrentLocale() == null) goto L23;
        Locale r73 = LocaleController.getInstance().getCurrentLocale();
    L24:
        Collator r74 = Collator.getInstance(r73);
        Objects.requireNonNull(r74);
        BoostRepository$$ExternalSyntheticLambda23 r1 = new BoostRepository$$ExternalSyntheticLambda23(r74);
        Collections.sort(r0, r1);
        Iterator r75 = r82.values().iterator();
    L26:
        if (r75.hasNext() == false) goto L28;
        Collections.sort((List) r75.next(), new BoostRepository$$ExternalSyntheticLambda24(r1));
        goto L26
    L28:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda25(r6, r82, r0));
        return;
    L23:
        r73 = Locale.getDefault();
        goto L24
    }

    public static /* synthetic */ void $r8$lambda$UWLhHkJVaeOtePsTVLUViRxh8vo(TLRPC.TL_error r2, Utilities.Callback r3, TLObject r4, MessagesController r5, TLRPC.TL_inputInvoicePremiumGiftCode r6, BaseFragment r7, Utilities.Callback r8) {
        if (r2 == null) goto L6;
        r3.run(r2);
        return;
    L6:
        if ((r4 instanceof TLRPC.PaymentForm) == false) goto L9;
        TLRPC.PaymentForm r42 = (TLRPC.PaymentForm) r4;
        r42.invoice.recurring = true;
        r5.putUsers(r42.users, false);
        PaymentFormActivity r22 = new PaymentFormActivity(r42, r6, r7);
    L12:
        if (r22 == null) goto L15;
        r22.setPaymentFormCallback(new BoostRepository$$ExternalSyntheticLambda37(r8, r3));
        LaunchActivity.getLastFragment().showAsSheet(r22, new BaseFragment.BottomSheetParams());
        return;
    L15:
        r3.run(null);
        return;
    L9:
        if ((r4 instanceof TLRPC.PaymentReceipt) == false) goto L11;
        r22 = new PaymentFormActivity((TLRPC.PaymentReceipt) r4);
        goto L12
    L11:
        r22 = null;
        goto L12
    }

    public static /* synthetic */ void $r8$lambda$VfNNzLz2r9dG197A_eLlLbrOSU8(TLRPC.TL_error r2, Utilities.Callback r3, TLObject r4, MessagesController r5, TLRPC.TL_inputInvoicePremiumGiftCode r6, BaseFragment r7, Utilities.Callback r8) {
        if (r2 == null) goto L6;
        r3.run(r2);
        return;
    L6:
        if ((r4 instanceof TLRPC.PaymentForm) == false) goto L9;
        TLRPC.PaymentForm r42 = (TLRPC.PaymentForm) r4;
        r42.invoice.recurring = true;
        r5.putUsers(r42.users, false);
        PaymentFormActivity r22 = new PaymentFormActivity(r42, r6, r7);
    L12:
        if (r22 == null) goto L15;
        r22.setPaymentFormCallback(new BoostRepository$$ExternalSyntheticLambda33(r8, r3));
        LaunchActivity.getLastFragment().showAsSheet(r22, new BaseFragment.BottomSheetParams());
        return;
    L15:
        r3.run(null);
        return;
    L9:
        if ((r4 instanceof TLRPC.PaymentReceipt) == false) goto L11;
        r22 = new PaymentFormActivity((TLRPC.PaymentReceipt) r4);
        goto L12
    L11:
        r22 = null;
        goto L12
    }

    public static /* synthetic */ void $r8$lambda$W5_e6TnV8SVJa9JuWe6Ji28UtSQ(Utilities.Callback r1) {
        r1.run(null);
    }

    /* renamed from: $r8$lambda$WUYk7o-urCJ9oJ0U0RVD3z63ZLo, reason: not valid java name */
    public static /* synthetic */ void m12089$r8$lambda$WUYk7ourCJ9oJ0U0RVD3z63ZLo(TLObject r7, MessagesController r8, Utilities.Callback r9) {
        if ((r7 instanceof TLRPC.TL_channels_channelParticipants) == false) goto L28;
        TLRPC.TL_channels_channelParticipants r72 = (TLRPC.TL_channels_channelParticipants) r7;
        int r1 = 0;
        r8.putUsers(r72.users, false);
        r8.putChats(r72.chats, false);
        long r2 = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        ArrayList r0 = new ArrayList();
    L6:
        if (r1 >= r72.participants.size()) goto L19;
        TLRPC.Peer r4 = r72.participants.get(r1).peer;
        if (r4 == null) goto L18;
        if (MessageObject.getPeerId(r4) == r2) goto L18;
        TLRPC.User r5 = r8.getUser(Long.valueOf(r4.user_id));
        if (r5 == null) goto L18;
        if (UserObject.isDeleted(r5) == true) goto L18;
        if (r5.bot == true) goto L18;
        r0.add(r8.getInputPeer(r4));
    L18:
        r1 = r1 + 1;
        goto L6
    L19:
        r9.run(r0);
        return;
    }

    /* renamed from: $r8$lambda$WauSsKIt7tDSBr-n145R8OroYD4, reason: not valid java name */
    public static /* synthetic */ void m12090$r8$lambda$WauSsKIt7tDSBrn145R8OroYD4(Utilities.Callback r1, Map r2, List r3) {
        r1.run(new Pair(r2, r3));
    }

    /* renamed from: $r8$lambda$YZXj8uMF8UZu6dbm6JHbNMRjT-c, reason: not valid java name */
    public static /* synthetic */ void m12091$r8$lambda$YZXj8uMF8UZu6dbm6JHbNMRjTc(Utilities.Callback r6, MessagesController r7, Utilities.Callback r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda2(r10, r6, r9, r7, r8));
    }

    public static /* synthetic */ void $r8$lambda$ZnykXPL35aKqFW_qp4dFSjG_3E0(MessagesController r4, long r5, Utilities.Callback r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TLRPC.TL_contacts_found) == false) goto L19;
        TLRPC.TL_contacts_found r82 = (TLRPC.TL_contacts_found) r8;
        int r0 = 0;
        r4.putChats(r82.chats, false);
        ArrayList r42 = new ArrayList();
    L6:
        if (r0 >= r82.chats.size()) goto L13;
        TLRPC.Chat r92 = r82.chats.get(r0);
        TLRPC.InputPeer r1 = MessagesController.getInputPeer(r92);
        if (r92.id == r5) goto L12;
        if (ChatObject.isBoostSupported(r92) == false) goto L12;
        r42.add(r1);
    L12:
        r0 = r0 + 1;
        goto L6
    L13:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda32(r7, r42));
        return;
    }

    /* renamed from: $r8$lambda$_LNcCYSxMJCk-O7LeIC1_aTSjrU, reason: not valid java name */
    public static /* synthetic */ int m12092$r8$lambda$_LNcCYSxMJCkO7LeIC1_aTSjrU(Comparator r0, TLRPC.TL_help_country r1, TLRPC.TL_help_country r2) {
        return r0.compare(r1.default_name, r2.default_name);
    }

    public static /* synthetic */ void $r8$lambda$_w_gaUlJUXZZLyliJu8K231X2jM(BillingResult r0, Utilities.Callback r1, BillingResult r2) {
        if (r0.getResponseCode() != 0) goto L6;
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda47(r1));
        return;
    }

    public static /* synthetic */ void $r8$lambda$a40JYBpE5PrUDHQjsroWMGf1XzQ(TLRPC.Chat r0, int r1, List r2, Utilities.Callback r3) {
        if (r0 != null) goto L4;
        saveGiftOptionsToCache(r1, r2);
    L4:
        r3.run(r2);
    }

    public static /* synthetic */ void $r8$lambda$aH74gVkTD7lttfJxjKuQVjVX7Mw(Utilities.Callback r0, TLRPC.TL_error r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$cCxOkE5YR6ccEJFuv6ReRxQ8V5o(TLRPC.TL_inputStorePaymentPremiumGiveaway r7, TLRPC.TL_premiumGiftCodeOption r8, ConnectionsManager r9, Utilities.Callback r10, Utilities.Callback r11, BaseFragment r12, BillingResult r13, List r14) {
        r7.currency = ((ProductDetails) r14.get(0)).getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
        r7.amount = (long) ((r0.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r8.currency)));
        TLRPC.TL_payments_canPurchaseStore r82 = new TLRPC.TL_payments_canPurchaseStore();
        r82.purpose = r7;
        r9.sendRequest(r82, new BoostRepository$$ExternalSyntheticLambda26(r10, r14, r13, r11, r12, r7));
    }

    /* renamed from: $r8$lambda$dZaHPtXWt_-GUT1qGhgn6-yh2-A, reason: not valid java name */
    public static /* synthetic */ void m12093$r8$lambda$dZaHPtXWt_GUT1qGhgn6yh2A(Utilities.Callback r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda31(r3, r0, r1));
    }

    public static /* synthetic */ void $r8$lambda$dvQtAQnEVi0_YaDGkdHmWyjCbOE(List r9, TLRPC.Chat r10, int r11, Utilities.Callback r12, BillingResult r13, List r14) {
        Iterator r132 = r14.iterator();
    L4:
        if (r132.hasNext() == false) goto L13;
        ProductDetails r142 = (ProductDetails) r132.next();
        ProductDetails.OneTimePurchaseOfferDetails r0 = r142.getOneTimePurchaseOfferDetails();
        Iterator r1 = r9.iterator();
    L7:
        if (r1.hasNext() == false) goto L4;
        TLRPC.TL_premiumGiftCodeOption r2 = (TLRPC.TL_premiumGiftCodeOption) r1.next();
        String r3 = r2.store_product;
        if (r3 == null) goto L7;
        if (r3.equals(r142.getProductId()) == false) goto L7;
        r2.amount = (long) ((r0.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r2.currency)));
        r2.currency = r0.getPriceCurrencyCode();
        goto L4
    L13:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda16(r10, r11, r9, r12));
    }

    public static /* synthetic */ void $r8$lambda$g_xySM0hQlLkMqWy7Mg2ggZA0YA(Utilities.Callback r0, MessagesController r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        if (r4 == null) goto L5;
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda35(r0, r4));
        return;
    L5:
        if (r3 == null) goto L8;
        r1.processUpdates((TLRPC.Updates) r3, false);
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda36(r2));
        return;
    }

    /* renamed from: $r8$lambda$ig8rwkvloatAUQHYz-F830bssQs, reason: not valid java name */
    public static /* synthetic */ void m12094$r8$lambda$ig8rwkvloatAUQHYzF830bssQs(BillingResult r0, Utilities.Callback r1, BillingResult r2) {
        if (r0.getResponseCode() != 0) goto L6;
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda52(r1));
        return;
    }

    public static /* synthetic */ void $r8$lambda$jIP5FFSfot6qY3xVOD0ONZgqdw8(Utilities.Callback r0, List r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$jtIygh0wfCd4nkIOMx1xfnvXeZk(Utilities.Callback r1) {
        r1.run(null);
    }

    public static /* synthetic */ void $r8$lambda$kRL9ZGJs3zmr5nJp2vPzCs7ok40(Utilities.Callback r1) {
        r1.run(Collections.EMPTY_LIST);
    }

    public static /* synthetic */ void $r8$lambda$ladrOkPC6UnvFyUkdVTtaiPm9aI(MessagesController r6, Utilities.Callback r7, Utilities.Callback r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda8(r9, r6, r7, r8, r10));
    }

    /* renamed from: $r8$lambda$lhhyahpoeYwfs-c7X-fxSqPRghU, reason: not valid java name */
    public static /* synthetic */ void m12095$r8$lambda$lhhyahpoeYwfsc7XfxSqPRghU(MessagesController r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda29(r2, r0, r1));
    }

    /* renamed from: $r8$lambda$s-eOjtmvaAd2X_F-KsmItj8H5ic, reason: not valid java name */
    public static /* synthetic */ void m12096$r8$lambda$seOjtmvaAd2X_FKsmItj8H5ic(Utilities.Callback r1) {
        r1.run(null);
    }

    public static /* synthetic */ void $r8$lambda$sUyLqFnw5T8JlloVbQXE6X66xhk(Utilities.Callback r6, MessagesController r7, Utilities.Callback r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda5(r10, r6, r9, r7, r8));
    }

    public static /* synthetic */ void $r8$lambda$sYP9mxeaslqmTa2PPz0LwaXUirU(Utilities.Callback r9, List r10, BillingResult r11, Utilities.Callback r12, BaseFragment r13, TLRPC.TL_inputStorePaymentPremiumGiveaway r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda34(r16, r9, r15, r10, r11, r12, r13, r14));
    }

    public static /* synthetic */ void $r8$lambda$yPg7LxTwonSa2qF9cLvxVWafz0M(MessagesController r3, boolean r4, Utilities.Callback r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.TL_contacts_found) == false) goto L26;
        TLRPC.TL_contacts_found r62 = (TLRPC.TL_contacts_found) r6;
        int r0 = 0;
        r3.putUsers(r62.users, false);
        ArrayList r32 = new ArrayList();
    L6:
        if (r0 >= r62.users.size()) goto L18;
        TLRPC.User r72 = r62.users.get(r0);
        if (r72.self == true) goto L17;
        if (UserObject.isDeleted(r72) == true) goto L17;
        if (r4 == true) goto L15;
        if (r72.bot == true) goto L17;
    L15:
        if (UserObject.isService(r72.id) == true) goto L17;
        r32.add(r72);
    L17:
        r0 = r0 + 1;
        goto L6
    L18:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda51(r5, r32));
        return;
    }

    /* renamed from: $r8$lambda$z_-Qvb9yMG7MJd9Nha8gnThuypI, reason: not valid java name */
    public static /* synthetic */ int m12097$r8$lambda$z_Qvb9yMG7MJd9Nha8gnThuypI(Comparator r0, TLRPC.TL_help_country r1, TLRPC.TL_help_country r2) {
        return r0.compare(r1.default_name, r2.default_name);
    }

    public static void applyBoost(long r3, List<Integer> r5, Utilities.Callback<TL_stories.TL_premium_myBoosts> r6, Utilities.Callback<TLRPC.TL_error> r7) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
        TL_stories.TL_premium_applyBoost r2 = new TL_stories.TL_premium_applyBoost();
        r2.peer = r1.getInputPeer(-r3);
        r2.flags |= 1;
        r2.slots.addAll(r5);
        r0.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda0(r7, r1, r6), 66);
    }

    public static void applyGiftCode(String r2, Utilities.Callback<Void> r3, Utilities.Callback<TLRPC.TL_error> r4) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_payments_applyGiftCode r1 = new TLRPC.TL_payments_applyGiftCode();
        r1.slug = r2;
        r0.sendRequest(r1, new BoostRepository$$ExternalSyntheticLambda18(r4, r3), 2);
    }

    public static int boostsPerSentGift() {
        return (int) MessagesController.getInstance(UserConfig.selectedAccount).boostsPerSentGift;
    }

    public static void checkGiftCode(String r3, Utilities.Callback<TLRPC.TL_payments_checkedGiftCode> r4, Utilities.Callback<TLRPC.TL_error> r5) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_payments_checkGiftCode r2 = new TLRPC.TL_payments_checkGiftCode();
        r2.slug = r3;
        r0.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda4(r1, r4, r5));
    }

    public static List<TLRPC.TL_premiumGiftCodeOption> filterGiftOptions(List<TLRPC.TL_premiumGiftCodeOption> r4, int r5) {
        ArrayList r0 = new ArrayList();
        Iterator<TLRPC.TL_premiumGiftCodeOption> r1 = r4.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        TLRPC.TL_premiumGiftCodeOption r2 = r1.next();
        String r3 = r2.store_product;
        if (r2.users != r5) goto L4;
        r0.add(r2);
        goto L4
    L9:
        if (r0.isEmpty() == false) goto L16;
        Iterator<TLRPC.TL_premiumGiftCodeOption> r42 = r4.iterator();
    L12:
        if (r42.hasNext() == false) goto L16;
        TLRPC.TL_premiumGiftCodeOption r52 = r42.next();
        if (r52.users != 1) goto L12;
        r0.add(r52);
    L16:
        return r0;
    }

    public static List<TLRPC.TL_premiumGiftCodeOption> filterGiftOptionsByBilling(List<TLRPC.TL_premiumGiftCodeOption> r3) {
        if (isGoogleBillingAvailable() == false) goto L11;
        ArrayList r0 = new ArrayList();
        Iterator<TLRPC.TL_premiumGiftCodeOption> r32 = r3.iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        TLRPC.TL_premiumGiftCodeOption r1 = r32.next();
        if (r1.store_product == null) goto L6;
        r0.add(r1);
        goto L6
    L10:
        return r0;
    L11:
        return r3;
    }

    public static List<TLRPC.TL_premiumGiftCodeOption> getCachedGiftOptions(int r6) {
        HashMap<Integer, Pair<Long, List<TLRPC.TL_premiumGiftCodeOption>>> r0 = cachedGiftOptions;
        if (r0 != null) goto L5;
        return null;
    L5:
        Pair<Long, List<TLRPC.TL_premiumGiftCodeOption>> r62 = r0.get(Integer.valueOf(r6));
        if (r62 != null) goto L8;
    L11:
        return null;
    L8:
        if ((System.currentTimeMillis() - ((Long) r62.first).longValue()) >= 1800000) goto L11;
        return (List) r62.second;
    }

    public static void getGiveawayInfo(MessageObject r5, Utilities.Callback<TLRPC.payments_GiveawayInfo> r6, Utilities.Callback<TLRPC.TL_error> r7) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_payments_getGiveawayInfo r2 = new TLRPC.TL_payments_getGiveawayInfo();
        r2.msg_id = r5.getId();
        r2.peer = r1.getInputPeer(MessageObject.getPeerId(r5.messageOwner.peer_id));
        r0.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda6(r7, r6));
    }

    public static void getMyBoosts(Utilities.Callback<TL_stories.TL_premium_myBoosts> r4, Utilities.Callback<TLRPC.TL_error> r5) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.sendRequest(new TL_stories.TL_premium_getMyBoosts(), new BoostRepository$$ExternalSyntheticLambda1(r5, r1, r4));
    }

    public static ArrayList<TLRPC.InputPeer> getMyChannels(long r8) {
        ArrayList<TLRPC.InputPeer> r0 = new ArrayList();
        MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
        ArrayList<TLRPC.Dialog> r2 = r1.getAllDialogs();
        int r3 = 0;
    L4:
        if (r3 >= r2.size()) goto L13;
        TLRPC.Dialog r4 = r2.get(r3);
        if (DialogObject.isChatDialog(r4.id) == false) goto L12;
        if (ChatObject.isBoostSupported(r1.getChat(Long.valueOf(-r4.id))) == false) goto L12;
        long r42 = r4.id;
        if ((-r42) == r8) goto L12;
        r0.add(r1.getInputPeer(r42));
    L12:
        r3 = r3 + 1;
        goto L4
    L13:
        return r0;
    }

    public static long giveawayAddPeersMax() {
        return MessagesController.getInstance(UserConfig.selectedAccount).giveawayAddPeersMax;
    }

    public static int giveawayBoostsPerPremium() {
        return (int) MessagesController.getInstance(UserConfig.selectedAccount).giveawayBoostsPerPremium;
    }

    public static long giveawayCountriesMax() {
        return MessagesController.getInstance(UserConfig.selectedAccount).giveawayCountriesMax;
    }

    public static long giveawayPeriodMax() {
        return MessagesController.getInstance(UserConfig.selectedAccount).giveawayPeriodMax;
    }

    public static void invalidateGiftOptionsToCache(int r1) {
        HashMap<Integer, Pair<Long, List<TLRPC.TL_premiumGiftCodeOption>>> r0 = cachedGiftOptions;
        if (r0 == null) goto L6;
        r0.remove(Integer.valueOf(r1));
        return;
    }

    public static boolean isGoogleBillingAvailable() {
        if (BuildVars.useInvoiceBilling() == false) goto L7;
        return false;
    L7:
        return BillingController.getInstance().isReady();
    }

    public static boolean isMultiBoostsAvailable() {
        if (MessagesController.getInstance(UserConfig.selectedAccount).boostsPerSentGift <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [org.telegram.tgnet.TLRPC$TL_inputStorePaymentStarsGiveaway] */
    /* JADX WARN: Type inference failed for: r1v3, types: [org.telegram.tgnet.TLRPC$InputStorePaymentPurpose] */
    /* JADX WARN: Type inference failed for: r1v4, types: [org.telegram.tgnet.TLRPC$TL_inputStorePaymentPremiumGiveaway] */
    public static void launchPreparedGiveaway(TL_stories.PrepaidGiveaway r3, List<TLObject> r4, List<TLObject> r5, TLRPC.Chat r6, int r7, boolean r8, boolean r9, boolean r10, int r11, String r12, Utilities.Callback<Void> r13, Utilities.Callback<TLRPC.TL_error> r14) {
        MessagesController r112 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        if ((r3 instanceof TL_stories.TL_prepaidGiveaway) == false) goto L18;
        ?? r1 = new TLRPC.TL_inputStorePaymentPremiumGiveaway();
        r1.only_new_subscribers = r8;
        r1.winners_are_visible = r9;
        r1.prize_description = r12;
        r1.until_date = r7;
        int r72 = r1.flags;
        r1.flags = r72 | 6;
        if (r10 == false) goto L7;
        r1.flags = r72 | 22;
    L7:
        r1.random_id = System.currentTimeMillis();
        r1.additional_peers = new ArrayList();
        r1.boost_peer = r112.getInputPeer(-r6.id);
        r1.currency = _UrlKt.FRAGMENT_ENCODE_SET;
        Iterator<TLObject> r52 = r5.iterator();
    L9:
        if (r52.hasNext() == false) goto L11;
        TLRPC.TL_help_country r73 = (TLRPC.TL_help_country) r52.next();
        r1.countries_iso2.add(r73.iso2);
        goto L9
    L11:
        Iterator<TLObject> r42 = r4.iterator();
    L13:
        if (r42.hasNext() == false) goto L32;
        TLObject r53 = r42.next();
        if ((r53 instanceof TLRPC.Chat) == false) goto L13;
        r1.additional_peers.add(r112.getInputPeer(-((TLRPC.Chat) r53).id));
    L32:
        TLRPC.TL_payments_launchPrepaidGiveaway r43 = new TLRPC.TL_payments_launchPrepaidGiveaway();
        r43.giveaway_id = r3.id;
        r43.peer = r112.getInputPeer(-r6.id);
        r43.purpose = r1;
        r0.sendRequest(r43, new BoostRepository$$ExternalSyntheticLambda27(r14, r112, r13));
        return;
    L18:
        if ((r3 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L46;
        r1 = new TLRPC.TL_inputStorePaymentStarsGiveaway();
        r1.only_new_subscribers = r8;
        r1.winners_are_visible = r9;
        r1.prize_description = r12;
        r1.until_date = r7;
        int r74 = r1.flags;
        r1.flags = r74 | 6;
        if (r10 == false) goto L22;
        r1.flags = r74 | 22;
    L22:
        r1.random_id = System.currentTimeMillis();
        r1.additional_peers = new ArrayList();
        r1.boost_peer = r112.getInputPeer(-r6.id);
        r1.currency = _UrlKt.FRAGMENT_ENCODE_SET;
        r1.stars = ((TL_stories.TL_prepaidStarsGiveaway) r3).stars;
        r1.users = r3.quantity;
        Iterator<TLObject> r54 = r5.iterator();
    L24:
        if (r54.hasNext() == false) goto L26;
        TLRPC.TL_help_country r75 = (TLRPC.TL_help_country) r54.next();
        r1.countries_iso2.add(r75.iso2);
        goto L24
    L26:
        Iterator<TLObject> r44 = r4.iterator();
    L28:
        if (r44.hasNext() == false) goto L32;
        TLObject r55 = r44.next();
        if ((r55 instanceof TLRPC.Chat) == false) goto L28;
        r1.additional_peers.add(r112.getInputPeer(-((TLRPC.Chat) r55).id));
        goto L28
    }

    public static void loadChatParticipants(long r2, int r4, String r5, int r6, int r7, Utilities.Callback<List<TLRPC.InputPeer>> r8) {
        MessagesController r42 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_channels_getParticipants r1 = new TLRPC.TL_channels_getParticipants();
        r1.channel = r42.getInputChannel(r2);
        if (r5 != null) goto L5;
        TLRPC.ChannelParticipantsFilter r22 = new TLRPC.TL_channelParticipantsRecent();
    L6:
        r1.filter = r22;
        if (r5 != null) goto L9;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        r22.q = r5;
        r1.offset = r6;
        r1.limit = r7;
        r0.sendRequest(r1, new BoostRepository$$ExternalSyntheticLambda21(r42, r8));
        return;
    L5:
        r22 = new TLRPC.TL_channelParticipantsSearch();
        goto L6
    }

    public static void loadCountries(Utilities.Callback<Pair<Map<String, List<TLRPC.TL_help_country>>, List<String>>> r3) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_help_getCountriesList r1 = new TLRPC.TL_help_getCountriesList();
        if (LocaleController.getInstance().getCurrentLocaleInfo() == null) goto L5;
        String r2 = LocaleController.getInstance().getCurrentLocaleInfo().getLangCode();
    L6:
        r1.lang_code = r2;
        r0.sendRequest(r1, new BoostRepository$$ExternalSyntheticLambda20(r3));
        return;
    L5:
        r2 = Locale.getDefault().getCountry();
        goto L6
    }

    public static void loadCountriesForPolls(Utilities.Callback<Pair<Map<String, List<TLRPC.TL_help_country>>, List<String>>> r3) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_help_getCountriesList r1 = new TLRPC.TL_help_getCountriesList();
        if (LocaleController.getInstance().getCurrentLocaleInfo() == null) goto L5;
        String r2 = LocaleController.getInstance().getCurrentLocaleInfo().getLangCode();
    L6:
        r1.lang_code = r2;
        r0.sendRequest(r1, new BoostRepository$$ExternalSyntheticLambda45(r3));
        return;
    L5:
        r2 = Locale.getDefault().getCountry();
        goto L6
    }

    public static int loadGiftOptions(int r5, TLRPC.Chat r6, Utilities.Callback<List<TLRPC.TL_premiumGiftCodeOption>> r7) {
        if (r6 != null) goto L7;
        List<TLRPC.TL_premiumGiftCodeOption> r0 = getCachedGiftOptions(r5);
        if (r0 == null) goto L7;
        r7.run(r0);
        return -1;
    L7:
        MessagesController r02 = MessagesController.getInstance(r5);
        ConnectionsManager r1 = ConnectionsManager.getInstance(r5);
        TLRPC.TL_payments_getPremiumGiftCodeOptions r2 = new TLRPC.TL_payments_getPremiumGiftCodeOptions();
        if (r6 == null) goto L11;
        r2.flags = 1;
        r2.boost_peer = r02.getInputPeer(-r6.id);
    L11:
        return r1.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda3(r6, r5, r7));
    }

    public static void loadParticipantsCount(Utilities.Callback<HashMap<Long, Integer>> r3) {
        MessagesStorage r0 = MessagesStorage.getInstance(UserConfig.selectedAccount);
        r0.getStorageQueue().postRunnable(new BoostRepository$$ExternalSyntheticLambda7(r0, r3));
    }

    public static void payGiftCode(List<TLObject> r1, TLRPC.TL_premiumGiftCodeOption r2, TLRPC.Chat r3, TLRPC.TL_textWithEntities r4, BaseFragment r5, Utilities.Callback<Void> r6, Utilities.Callback<TLRPC.TL_error> r7) {
        invalidateGiftOptionsToCache(UserConfig.selectedAccount);
        if (isGoogleBillingAvailable() == true) goto L6;
        payGiftCodeByInvoice(r1, r2, r3, r4, r5, r6, r7);
        return;
    L6:
        payGiftCodeByGoogle(r1, r2, r3, r4, r5, r6, r7);
    }

    public static void payGiftCodeByGoogle(List<TLObject> r5, TLRPC.TL_premiumGiftCodeOption r6, TLRPC.Chat r7, TLRPC.TL_textWithEntities r8, BaseFragment r9, Utilities.Callback<Void> r10, Utilities.Callback<TLRPC.TL_error> r11) {
        MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_inputStorePaymentPremiumGiftCode r62 = new TLRPC.TL_inputStorePaymentPremiumGiftCode();
        r62.users = new ArrayList();
        Iterator<TLObject> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        TLObject r3 = r52.next();
        if ((r3 instanceof TLRPC.User) == false) goto L4;
        r62.users.add(r0.getInputUser((TLRPC.User) r3));
        goto L4
    L8:
        if (r7 == null) goto L10;
        r62.flags = 1;
        r62.boost_peer = r0.getInputPeer(-r7.id);
    L10:
        if (r8 != null) goto L12;
    L14:
        BillingController.getInstance().queryProductDetails(Arrays.asList(new QueryProductDetailsParams.Product[]{QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r6.store_product).build()}), new BoostRepository$$ExternalSyntheticLambda14(r62, r6, r1, r11, r10, r9));
        return;
    L12:
        if (TextUtils.isEmpty(r8.text) == true) goto L14;
        r62.flags |= 2;
        r62.message = r8;
        goto L14
    }

    public static void payGiftCodeByInvoice(List<TLObject> r7, TLRPC.TL_premiumGiftCodeOption r8, TLRPC.Chat r9, TLRPC.TL_textWithEntities r10, BaseFragment r11, Utilities.Callback<Void> r12, Utilities.Callback<TLRPC.TL_error> r13) {
        MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_payments_getPaymentForm r2 = new TLRPC.TL_payments_getPaymentForm();
        TLRPC.TL_inputInvoicePremiumGiftCode r102 = new TLRPC.TL_inputInvoicePremiumGiftCode();
        TLRPC.TL_inputStorePaymentPremiumGiftCode r4 = new TLRPC.TL_inputStorePaymentPremiumGiftCode();
        r4.users = new ArrayList();
        Iterator<TLObject> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L8;
        TLObject r5 = r72.next();
        if ((r5 instanceof TLRPC.User) == false) goto L4;
        r4.users.add(r0.getInputUser((TLRPC.User) r5));
        goto L4
    L8:
        if (r10 != null) goto L10;
    L12:
        if (r9 == null) goto L14;
        r4.flags |= 1;
        r4.boost_peer = r0.getInputPeer(-r9.id);
    L14:
        r4.currency = r8.currency;
        r4.amount = r8.amount;
        r102.purpose = r4;
        r102.option = r8;
        JSONObject r73 = BotWebViewSheet.makeThemeParams(r11.getResourceProvider());
        if (r73 == null) goto L17;
        TLRPC.TL_dataJSON r82 = new TLRPC.TL_dataJSON();
        r2.theme_params = r82;
        r82.data = r73.toString();
        r2.flags |= 1;
    L17:
        r2.invoice = r102;
        r1.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda17(r13, r0, r102, r11, r12));
        return;
    L10:
        if (TextUtils.isEmpty(r10.text) == true) goto L12;
        r4.flags |= 2;
        r4.message = r10;
        goto L12
    }

    public static void payGiveAway(List<TLObject> r1, List<TLObject> r2, TLRPC.TL_premiumGiftCodeOption r3, TLRPC.Chat r4, int r5, boolean r6, BaseFragment r7, boolean r8, boolean r9, String r10, Utilities.Callback<Void> r11, Utilities.Callback<TLRPC.TL_error> r12) {
        if (isGoogleBillingAvailable() == true) goto L6;
        payGiveAwayByInvoice(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        return;
    L6:
        payGiveAwayByGoogle(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static void payGiveAwayByGoogle(List<TLObject> r3, List<TLObject> r4, TLRPC.TL_premiumGiftCodeOption r5, TLRPC.Chat r6, int r7, boolean r8, BaseFragment r9, boolean r10, boolean r11, String r12, Utilities.Callback<Void> r13, Utilities.Callback<TLRPC.TL_error> r14) {
        MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_inputStorePaymentPremiumGiveaway r42 = new TLRPC.TL_inputStorePaymentPremiumGiveaway();
        r42.only_new_subscribers = r8;
        r42.winners_are_visible = r10;
        r42.prize_description = r12;
        r42.until_date = r7;
        int r72 = r42.flags;
        r42.flags = r72 | 6;
        if (r11 == false) goto L5;
        r42.flags = r72 | 22;
    L5:
        r42.random_id = System.currentTimeMillis();
        r42.additional_peers = new ArrayList();
        Iterator<TLObject> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L11;
        TLObject r73 = r32.next();
        if ((r73 instanceof TLRPC.Chat) == false) goto L7;
        r42.additional_peers.add(r0.getInputPeer(-((TLRPC.Chat) r73).id));
        goto L7
    L11:
        r42.boost_peer = r0.getInputPeer(-r6.id);
        Iterator<TLObject> r33 = r4.iterator();
    L13:
        if (r33.hasNext() == false) goto L15;
        TLRPC.TL_help_country r62 = (TLRPC.TL_help_country) r33.next();
        r42.countries_iso2.add(r62.iso2);
        goto L13
    L15:
        QueryProductDetailsParams.Product r34 = QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r5.store_product).build();
        BillingController.getInstance().queryProductDetails(Arrays.asList(new QueryProductDetailsParams.Product[]{r34}), new BoostRepository$$ExternalSyntheticLambda19(r42, r5, r1, r14, r13, r9));
    }

    public static void payGiveAwayByInvoice(List<TLObject> r9, List<TLObject> r10, TLRPC.TL_premiumGiftCodeOption r11, TLRPC.Chat r12, int r13, boolean r14, BaseFragment r15, boolean r16, boolean r17, String r18, Utilities.Callback<Void> r19, Utilities.Callback<TLRPC.TL_error> r20) {
        MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_payments_getPaymentForm r2 = new TLRPC.TL_payments_getPaymentForm();
        TLRPC.TL_inputInvoicePremiumGiftCode r3 = new TLRPC.TL_inputInvoicePremiumGiftCode();
        TLRPC.TL_inputStorePaymentPremiumGiveaway r4 = new TLRPC.TL_inputStorePaymentPremiumGiveaway();
        r4.only_new_subscribers = r14;
        r4.winners_are_visible = r16;
        r4.prize_description = r18;
        r4.until_date = r13;
        int r5 = r4.flags;
        r4.flags = r5 | 6;
        if (r17 == false) goto L5;
        r4.flags = r5 | 22;
    L5:
        r4.random_id = System.currentTimeMillis();
        r4.additional_peers = new ArrayList();
        Iterator<TLObject> r92 = r9.iterator();
    L7:
        if (r92.hasNext() == false) goto L11;
        TLObject r52 = r92.next();
        if ((r52 instanceof TLRPC.Chat) == false) goto L7;
        r4.additional_peers.add(r0.getInputPeer(-((TLRPC.Chat) r52).id));
        goto L7
    L11:
        r4.boost_peer = r0.getInputPeer(-r12.id);
        r4.boost_peer = r0.getInputPeer(-r12.id);
        r4.currency = r11.currency;
        r4.amount = r11.amount;
        Iterator<TLObject> r93 = r10.iterator();
    L13:
        if (r93.hasNext() == false) goto L15;
        r4.countries_iso2.add(((TLRPC.TL_help_country) r93.next()).iso2);
        goto L13
    L15:
        r3.purpose = r4;
        r3.option = r11;
        JSONObject r94 = BotWebViewSheet.makeThemeParams(r15.getResourceProvider());
        if (r94 == null) goto L18;
        TLRPC.TL_dataJSON r102 = new TLRPC.TL_dataJSON();
        r2.theme_params = r102;
        r102.data = r94.toString();
        r2.flags |= 1;
    L18:
        r2.invoice = r3;
        r1.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda15(r20, r0, r3, r15, r19));
    }

    public static int prepareServerDate(long r4) {
        if (r4 >= (System.currentTimeMillis() + 120000)) goto L6;
        r4 = System.currentTimeMillis() + 120000;
    L6:
        return (int) (r4 / 1000);
    }

    public static void saveGiftOptionsToCache(int r4, List<TLRPC.TL_premiumGiftCodeOption> r5) {
        if (cachedGiftOptions != null) goto L5;
        cachedGiftOptions = new HashMap();
    L5:
        cachedGiftOptions.put(Integer.valueOf(r4), new Pair(Long.valueOf(System.currentTimeMillis()), r5));
    }

    public static void searchChats(long r1, int r3, String r4, int r5, Utilities.Callback<List<TLRPC.InputPeer>> r6) {
        MessagesController r32 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r52 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_contacts_search r0 = new TLRPC.TL_contacts_search();
        r0.q = r4;
        r0.limit = 50;
        r52.sendRequest(r0, new BoostRepository$$ExternalSyntheticLambda13(r32, r1, r6));
    }

    public static int searchContacts(String r3, boolean r4, Utilities.Callback<List<TLRPC.User>> r5) {
        MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r1 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        if (r3 != null) goto L5;
    L9:
        AndroidUtilities.runOnUIThread(new BoostRepository$$ExternalSyntheticLambda43(r5));
        return 0;
    L5:
        if (r3.isEmpty() == true) goto L9;
        TLRPC.TL_contacts_search r2 = new TLRPC.TL_contacts_search();
        r2.q = r3;
        r2.limit = 50;
        return r1.sendRequest(r2, new BoostRepository$$ExternalSyntheticLambda44(r0, r4, r5));
    }

    public static void searchContactsLocally(String r12, boolean r13, Utilities.Callback<List<TLRPC.User>> r14) {
        int r0 = UserConfig.selectedAccount;
        ArrayList r1 = new ArrayList();
        ArrayList<TLRPC.TL_contact> r2 = ContactsController.getInstance(r0).contacts;
        if (r2 != null) goto L5;
    L6:
        ContactsController.getInstance(r0).loadContacts(false, 0);
    L7:
        MessagesController r02 = MessagesController.getInstance(r0);
        String r122 = r12.toLowerCase();
        String r4 = AndroidUtilities.translitSafe(r122);
        if (r2 == null) goto L66;
        int r5 = 0;
    L11:
        if (r5 >= r2.size()) goto L66;
        TLRPC.TL_contact r6 = r2.get(r5);
        if (r6 == null) goto L65;
        TLRPC.User r62 = r02.getUser(Long.valueOf(r6.user_id));
        if (r62 == null) goto L65;
        if (r13 == true) goto L20;
        if (r62.bot == true) goto L65;
    L20:
        if (UserObject.isService(r62.id) == true) goto L65;
        if (UserObject.isUserSelf(r62) == true) goto L65;
        String r7 = UserObject.getUserName(r62).toLowerCase();
        String r8 = AndroidUtilities.translitSafe(r7);
        if (r7.startsWith(r122) == false) goto L27;
    L64:
        r1.add(r62);
        goto L65
    L27:
        if (r7.contains(" " + r122) == true) goto L64;
        if (r8.startsWith(r4) == true) goto L64;
        if (r8.contains(" " + r4) == true) goto L64;
        if (r62.usernames == null) goto L53;
        int r72 = 0;
    L37:
        if (r72 >= r62.usernames.size()) goto L65;
        TLRPC.TL_username r9 = r62.usernames.get(r72);
        if (r9 == null) goto L52;
        if (r9.active == false) goto L52;
        String r92 = r9.username.toLowerCase();
        if (r92.startsWith(r122) == true) goto L51;
        if (r92.contains("_" + r122) == true) goto L51;
        if (r92.startsWith(r4) == true) goto L51;
        if (r92.contains(" " + r4) == false) goto L52;
    L51:
        r1.add(r62);
    L52:
        r72 = r72 + 1;
        goto L37
    L53:
        String r73 = r62.username;
        if (r73 == null) goto L65;
        String r74 = r73.toLowerCase();
        if (r74.startsWith(r122) == false) goto L58;
    L63:
        r1.add(r62);
        goto L65
    L58:
        if (r74.contains("_" + r122) == true) goto L63;
        if (r74.startsWith(r4) == true) goto L63;
        if (r74.contains(" " + r4) == true) goto L63;
    L65:
        r5 = r5 + 1;
    L66:
        r14.run(r1);
        return;
    L5:
        if (r2.isEmpty() == false) goto L7;
        goto L6
    }
}
