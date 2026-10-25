package org.telegram.messenger.utils;

import android.util.Base64;
import androidx.core.util.Pair;
import androidx.media3.common.util.Util;
import com.android.billingclient.api.AccountIdentifiers;
import com.android.billingclient.api.Purchase;
import com.google.common.base.Charsets;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class BillingUtilities {

    /* loaded from: classes6.dex */
    public static class TL_savedPurpose extends TLObject {
        public int flags;
        public long id;
        public TLRPC.InputStorePaymentPurpose purpose;

        public TL_savedPurpose() {
        }

        public static TL_savedPurpose TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 495638674) goto L5;
            TL_savedPurpose r0 = null;
        L7:
            return (TL_savedPurpose) TLObject.TLdeserialize(TL_savedPurpose.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_savedPurpose();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.id = r3.readInt64(r4);
            if ((this.flags & 1) == 0) goto L6;
            this.purpose = TLRPC.InputStorePaymentPurpose.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(495638674);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.id);
            if ((this.flags & 1) == 0) goto L6;
            this.purpose.serializeToStream(r3);
            return;
        }
    }

    public static void cleanupPurchase(Purchase r0) {
        clearPurpose(r0.getAccountIdentifiers().getObfuscatedProfileId());
    }

    public static void clearPurpose(String r4) {
        FileLog.d("BillingUtilities.clearPurpose: got {" + r4 + "}");     // Catch: Exception -> L5
        SerializedData r1 = new SerializedData(Utilities.hexToBytes(r4));     // Catch: Exception -> L5
        TL_savedPurpose r42 = TL_savedPurpose.TLdeserialize(r1, r1.readInt32(true), true);     // Catch: Exception -> L5
        SerializedData r12 = new SerializedData(8);     // Catch: Exception -> L5
        r12.writeInt64(r42.id);     // Catch: Exception -> L5
        String r43 = Utilities.bytesToHex(r12.toByteArray());     // Catch: Exception -> L5
        r12.cleanup();     // Catch: Exception -> L5
        FileLog.d("BillingUtilities.clearPurpose: id_hex = " + r43);     // Catch: Exception -> L5
        ApplicationLoader.applicationContext.getSharedPreferences("purchases", 0).edit().remove(r43).apply();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static Pair<String, String> createDeveloperPayload(TLRPC.InputStorePaymentPurpose r4, AccountInstance r5) {
        if (r5.getUserConfig().isClientActivated() == false) goto L5;
        String r52 = Base64.encodeToString(String.valueOf(r5.getUserConfig().getClientUserId()).getBytes(Charsets.UTF_8), 0);
    L7:
        return Pair.create(r52, savePurpose(r4));
    L5:
        r52 = Base64.encodeToString(("account-" + r5.getCurrentAccount()).getBytes(Charsets.UTF_8), 0);
        goto L7
    }

    public static void extractCurrencyExp(Map<String, Integer> r6) {
        if (r6.isEmpty() == true) goto L14;
        return;
    L14:
        InputStream r0 = ApplicationLoader.applicationContext.getAssets().open("currencies.json");     // Catch: Exception -> L11
        JSONObject r1 = new JSONObject(new String(Util.toByteArray(r0), Charsets.UTF_8));     // Catch: Exception -> L11
        Iterator<String> r2 = r1.keys();     // Catch: Exception -> L11
    L7:
        if (r2.hasNext() == false) goto L9;
        String r3 = r2.next();     // Catch: Exception -> L11
        r6.put(r3, Integer.valueOf(r1.optJSONObject(r3).optInt("exp")));     // Catch: Exception -> L11
        goto L7
    L9:
        r0.close();     // Catch: Exception -> L11
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
    }

    public static Pair<AccountInstance, TLRPC.InputStorePaymentPurpose> extractDeveloperPayload(Purchase r6) {
        AccountIdentifiers r62 = r6.getAccountIdentifiers();
        if (r62 != null) goto L6;
        FileLog.d("Billing: Extract payload. No AccountIdentifiers");
        return null;
    L6:
        String r3 = r62.getObfuscatedAccountId();
        String r63 = r62.getObfuscatedProfileId();
        if (r3 != null) goto L9;
    L32:
        FileLog.d("Billing: Extract payload. Empty AccountIdentifiers");
        return null;
    L9:
        if (r3.isEmpty() == true) goto L32;
        if (r63 == null) goto L32;
        if (r63.isEmpty() == true) goto L32;
        TLRPC.InputStorePaymentPurpose r64 = getPurpose(r63);     // Catch: Exception -> L16
    L18:
        String r4 = new String(Base64.decode(r3, 0), Charsets.UTF_8);     // Catch: Exception -> L21
        FileLog.d("Billing: Extract payload. obfuscatedAccountIdString=".concat(r4));     // Catch: Exception -> L21
        if (r4.startsWith("account-") == false) goto L23;
        AccountInstance r0 = AccountInstance.getInstance(Integer.parseInt(r4.substring(8)));     // Catch: Exception -> L21
    L29:
        return Pair.create(r0, r64);
    L23:
        long r32 = Long.parseLong(r4);     // Catch: Exception -> L21
        AccountInstance r1 = findAccountById(r32);     // Catch: Exception -> L21
        if (r1 != null) goto L27;
        FileLog.d("Billing: Extract payload. AccountInstance not found, accountId=" + r32);     // Catch: Exception -> L21
        return null;
    L27:
        r0 = r1;
        goto L29
    L16:
        e = move-exception;
        FileLog.e("Billing: Extract payload, failed to get purpose", e);     // Catch: Exception -> L21
        r64 = null;
    L21:
        e = move-exception;
        FileLog.e("Billing: Extract Payload", e);
        return null;
    }

    private static AccountInstance findAccountById(long r4) {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L9;
        AccountInstance r1 = AccountInstance.getInstance(r0);
        if (r1.getUserConfig().getClientUserId() == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public static TLRPC.InputStorePaymentPurpose getPurpose(String r4) {
        FileLog.d("BillingUtilities.getPurpose " + r4);
        SerializedData r0 = new SerializedData(Utilities.hexToBytes(r4));
        TL_savedPurpose r1 = TL_savedPurpose.TLdeserialize(r0, r0.readInt32(true), true);
        r0.cleanup();
        if (r1.purpose == null) goto L6;
        FileLog.d("BillingUtilities.getPurpose: got purpose from received obfuscated profile id");
        return r1.purpose;
    L6:
        SerializedData r02 = new SerializedData(8);
        r02.writeInt64(r1.id);
        String r12 = Utilities.bytesToHex(r02.toByteArray());
        r02.cleanup();
        FileLog.d("BillingUtilities.getPurpose: searching purpose under " + r12);
        String r03 = ApplicationLoader.applicationContext.getSharedPreferences("purchases", 0).getString(r12, null);
        if (r03 == null) goto L10;
        FileLog.d("BillingUtilities.getPurpose: got {" + r03 + "} under " + r12);
        SerializedData r13 = new SerializedData(Utilities.hexToBytes(r03));
        TL_savedPurpose r42 = TL_savedPurpose.TLdeserialize(r13, r13.readInt32(true), true);
        r13.cleanup();
        return r42.purpose;
    L10:
        FileLog.d("BillingUtilities.getPurpose: purpose under " + r12 + " not found");
        GlShader$$ExternalSyntheticBUOutline0.m("no purpose under ", r12, " found :(");
        return null;
    }

    public static String savePurpose(TLRPC.InputStorePaymentPurpose r6) {
        long r0 = Utilities.random.nextLong();
        FileLog.d("BillingUtilities.savePurpose id=" + r0 + " paymentPurpose=" + r6);
        SerializedData r2 = new SerializedData(8);
        r2.writeInt64(r0);
        String r4 = Utilities.bytesToHex(r2.toByteArray());
        r2.cleanup();
        FileLog.d("BillingUtilities.savePurpose id_hex=" + r4 + " paymentPurpose=" + r6);
        TL_savedPurpose r22 = new TL_savedPurpose();
        r22.id = r0;
        r22.flags = 1;
        r22.purpose = r6;
        SerializedData r62 = new SerializedData(r22.getObjectSize());
        r22.serializeToStream(r62);
        String r02 = Utilities.bytesToHex(r62.toByteArray());
        r62.cleanup();
        if (r22.getObjectSize() <= 28) goto L5;
        FileLog.d("BillingUtilities.savePurpose: sending short version, original size is " + r22.getObjectSize() + " bytes");
        r22.flags = 0;
        r22.purpose = null;
    L5:
        SerializedData r63 = new SerializedData(r22.getObjectSize());
        r22.serializeToStream(r63);
        String r1 = Utilities.bytesToHex(r63.toByteArray());
        r63.cleanup();
        ApplicationLoader.applicationContext.getSharedPreferences("purchases", 0).edit().putString(r4, r02).apply();
        FileLog.d("BillingUtilities.savePurpose: saved {" + r02 + "} under " + r4);
        StringBuilder r64 = new StringBuilder("BillingUtilities.savePurpose: but sending {");
        r64.append(r1);
        r64.append("}");
        FileLog.d(r64.toString());
        return r1;
    }
}
