package com.google.android.gms.internal.play_billing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.QueryProductDetailsParams;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.OneUIUtilities;

/* loaded from: classes5.dex */
public abstract class zzc {
    public static final int zza = 0;

    static {
        zza = Runtime.getRuntime().availableProcessors();
    }

    public static int zza(Intent r0, String r1) {
        if (r0 != null) goto L7;
        zzn("ProxyBillingActivity", "Got null intent!");
        return 0;
    L7:
        return zzp(r0.getExtras(), "ProxyBillingActivity");
    }

    public static int zzb(Bundle r2, String r3) {
        if (r2 != null) goto L6;
        zzn(r3, "Unexpected null bundle received!");
        return 6;
    L6:
        Object r22 = r2.get("RESPONSE_CODE");
        if (r22 != null) goto L11;
        zzm(r3, "getResponseCodeFromBundle() got null response code, assuming OK");
        return 0;
    L11:
        if ((r22 instanceof Integer) == true) goto L13;
        zzn(r3, "Unexpected type for bundle response code: ".concat(r22.getClass().getName()));
        return 6;
    L13:
        return ((Integer) r22).intValue();
    }

    public static Bundle zzc(Bundle r1, String r2, String r3, long r4) {
        r1.putString("playBillingLibraryVersion", r2);
        if (r3 == null) goto L5;
        r1.putString("playBillingLibraryWrapperVersion", r3);
    L5:
        r1.putLong("billingClientSessionId", r4);
        return r1;
    }

    public static Bundle zzd(BillingResult r3, zzie r4) {
        Bundle r0 = new Bundle();
        r0.putInt("RESPONSE_CODE", r3.getResponseCode());
        r0.putString("DEBUG_MESSAGE", r3.getDebugMessage());
        r0.putInt("LOG_REASON", r4.zza());
        return r0;
    }

    public static Bundle zze(BillingResult r0, zzie r1, String r2) {
        Bundle r02 = zzd(r0, r1);
        if (r2 == null) goto L5;
        r02.putString("ADDITIONAL_LOG_DETAILS", r2);
    L5:
        return r02;
    }

    public static Bundle zzf(String r5, String r6, ArrayList r7, String r8, String r9, zza r10, long r11) {
        Bundle r82 = new Bundle();
        zzc(r82, r5, r6, r11);
        r82.putBoolean("enablePendingPurchases", true);
        r82.putString("SKU_DETAILS_RESPONSE_FORMAT", "PRODUCT_DETAILS");
        r82.putStringArrayList("PRODUCT_TYPES_TO_RETURN_MULTIPLE_OFFERS", new ArrayList(zzbt.zzm("subs", "inapp")));
        r82.putStringArrayList("PRODUCT_TYPES_TO_RETURN_RENT_OFFERS", new ArrayList(zzbt.zzl("inapp")));
        r82.putBoolean("SHOULD_RETURN_UNFETCHED_PRODUCTS", true);
        if (zza.zzb(r10) == false) goto L5;
        r82.putBoolean("enablePendingPurchaseForSubscriptions", true);
    L5:
        ArrayList<String> r52 = new ArrayList();
        ArrayList<String> r92 = new ArrayList();
        ArrayList<String> r102 = new ArrayList();
        int r112 = r7.size();
        int r12 = 0;
        boolean r0 = false;
        boolean r1 = false;
    L7:
        if (r12 >= r112) goto L12;
        QueryProductDetailsParams.Product r3 = (QueryProductDetailsParams.Product) r7.get(r12);
        r52.add(null);
        r0 = r0 | (!TextUtils.isEmpty(null));
        r102.add(null);
        r1 = r1 | (!TextUtils.isEmpty(null));
        if (r3.zzb().equals("first_party") == false) goto L11;
        zzbg.zzc(null, "Serialized DocId is required for constructing ExtraParams to query ProductDetails for all first party products.");
        r92.add(null);
    L11:
        r12 = r12 + 1;
        goto L7
    L12:
        if (r0 == false) goto L15;
        r82.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", r52);
    L15:
        if (r92.isEmpty() == true) goto L18;
        r82.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", r92);
    L18:
        if (TextUtils.isEmpty(null) == true) goto L20;
        r82.putString("accountName", null);
    L20:
        if (r1 == false) goto L22;
        r82.putStringArrayList("SKU_DYNAMIC_PRODUCT_TOKEN_LIST", r102);
    L22:
        return r82;
    }

    public static BillingResult zzh(Intent r2, String r3) {
        if (r2 != null) goto L5;
        zzn("BillingHelper", "Got null intent!");
        BillingResult.Builder r22 = BillingResult.newBuilder();
        r22.setResponseCode(6);
        r22.setDebugMessage("An internal error occurred.");
        return r22.build();
    L5:
        BillingResult.Builder r0 = BillingResult.newBuilder();
        r0.setResponseCode(zzb(r2.getExtras(), r3));
        r0.setDebugMessage(zzj(r2.getExtras(), r3));
        return r0.build();
    }

    public static String zzj(Bundle r2, String r3) {
        if (r2 != null) goto L6;
        zzn(r3, "Unexpected null bundle received!");
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        Object r22 = r2.get("DEBUG_MESSAGE");
        if (r22 != null) goto L11;
        zzm(r3, "getDebugMessageFromBundle() got null response code, assuming OK");
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        if ((r22 instanceof String) == true) goto L13;
        zzn(r3, "Unexpected type for debug message: ".concat(r22.getClass().getName()));
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return (String) r22;
    }

    public static String zzk(int r0) {
        return zzb.zza(r0).toString();
    }

    public static List zzl(Bundle r6) {
        ArrayList<String> r0 = r6.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> r1 = r6.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        ArrayList r2 = new ArrayList();
        if (r0 == null) goto L16;
        if (r1 == null) goto L16;
        zzm("BillingHelper", "Found purchase list of " + r0.size() + " items");
        int r62 = 0;
    L8:
        if (r62 >= r0.size()) goto L15;
        if (r62 >= r1.size()) goto L15;
        Purchase r3 = zzq(r0.get(r62), r1.get(r62));
        if (r3 == null) goto L14;
        r2.add(r3);
    L14:
        r62 = r62 + 1;
    L15:
        return r2;
    L16:
        Purchase r63 = zzq(r6.getString("INAPP_PURCHASE_DATA"), r6.getString("INAPP_DATA_SIGNATURE"));
        if (r63 != null) goto L20;
        zzm("BillingHelper", "Couldn't find single purchase data as well.");
        return null;
    L20:
        r2.add(r63);
        return r2;
    }

    public static void zzm(String r3, String r4) {
        if (Log.isLoggable(r3, 2) == true) goto L5;
        return;
    L5:
        if (r4.isEmpty() == true) goto L11;
        int r0 = OneUIUtilities.ONE_UI_4_0;
    L8:
        if (r4.isEmpty() == true) goto L16;
        if (r0 <= 0) goto L17;
        int r1 = Math.min(r4.length(), Math.min(4000, r0));
        Log.v(r3, r4.substring(0, r1));
        r4 = r4.substring(r1);
        r0 = r0 - r1;
        goto L8
    L17:
        return;
    L16:
        return;
    L11:
        Log.v(r3, r4);
    }

    public static void zzn(String r1, String r2) {
        if (Log.isLoggable(r1, 5) == false) goto L6;
        Log.w(r1, r2);
        return;
    }

    public static void zzo(String r1, String r2, Throwable r3) {
        if (Log.isLoggable(r1, 5) == false) goto L13;
        if (r3 != null) goto L9;
        Log.w(r1, r2);     // Catch: Throwable -> L11
        return;
    L9:
        Log.w(r1, r2, r3);     // Catch: Throwable -> L11
        return;
    L13:
        return;
    }

    private static int zzp(Bundle r1, String r2) {
        if (r1 != null) goto L7;
        zzn(r2, "Unexpected null bundle received!");
        return 0;
    L7:
        return r1.getInt("IN_APP_MESSAGE_RESPONSE_CODE", 0);
    }

    private static Purchase zzq(String r3, String r4) {
        if (r3 == null) goto L10;
        if (r4 == null) goto L10;
        return new Purchase(r3, r4);
    L7:
        e = move-exception;
        zzn("BillingHelper", "Got JSONException while parsing purchase data: ".concat(e.toString()));
        return null;
    L10:
        zzm("BillingHelper", "Received a null purchase data.");
        return null;
    }
}
