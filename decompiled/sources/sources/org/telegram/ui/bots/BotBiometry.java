package org.telegram.ui.bots;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Pair;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class BotBiometry {
    private static final WeakHashMap<Pair<Integer, Long>, BotBiometry> instances = null;
    private static KeyStore keyStore;
    public boolean access_granted;
    public boolean access_requested;
    public final long botId;
    private Utilities.Callback2<Boolean, BiometricPrompt.AuthenticationResult> callback;
    public final Context context;
    public final int currentAccount;
    public boolean disabled;
    private String encrypted_token;
    private String iv;
    private BiometricPrompt prompt;

    /* renamed from: org.telegram.ui.bots.BotBiometry$1, reason: invalid class name */
    public class AnonymousClass1 extends BiometricPrompt.AuthenticationCallback {
        final /* synthetic */ BotBiometry this$0;

        public AnonymousClass1(BotBiometry r1) {
            this.this$0 = r1;
        }

        @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationError(int r3, CharSequence r4) {
            FileLog.d("BotBiometry onAuthenticationError " + r3 + " \"" + r4 + "\"");
            if (BotBiometry.m21309$$Nest$fgetcallback(this.this$0) == null) goto L6;
            Utilities.Callback2 r32 = BotBiometry.m21309$$Nest$fgetcallback(this.this$0);
            BotBiometry.m21310$$Nest$fputcallback(this.this$0, null);
            r32.run(Boolean.FALSE, null);
            return;
        }

        @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationFailed() {
            FileLog.d("BotBiometry onAuthenticationFailed");
        }

        @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult r3) {
            FileLog.d("BotBiometry onAuthenticationSucceeded");
            if (BotBiometry.m21309$$Nest$fgetcallback(this.this$0) == null) goto L6;
            Utilities.Callback2 r0 = BotBiometry.m21309$$Nest$fgetcallback(this.this$0);
            BotBiometry.m21310$$Nest$fputcallback(this.this$0, null);
            r0.run(Boolean.TRUE, r3);
            return;
        }
    }

    public static class Bot {
        public boolean disabled;
        public TLRPC.User user;

        private Bot(TLRPC.User r1, boolean r2) {
            this.user = r1;
            this.disabled = r2;
        }

        public /* synthetic */ Bot(TLRPC.User r1, boolean r2, BotBiometryIA r3) {
            this(r1, r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$AgFIv5GtY57vd6xtNQlyOnEYRpI(Utilities.Callback3 r0, BiometricPrompt.CryptoObject r1, Boolean r2, BiometricPrompt.AuthenticationResult r3) {
        r0.run(r2, r3, r1);
    }

    /* renamed from: $r8$lambda$RJKoBZmwFpKggQ7-0qGwMAC69F4, reason: not valid java name */
    public static /* synthetic */ void m21307$r8$lambda$RJKoBZmwFpKggQ70qGwMAC69F4(int r0, ArrayList r1, HashMap r2, Utilities.Callback r3) {
        AndroidUtilities.runOnUIThread(new BotBiometry$$ExternalSyntheticLambda1(MessagesStorage.getInstance(r0).getUsers(r1), r2, r3));
    }

    /* renamed from: $r8$lambda$SwnpH-R0cMtPMBDUwliQmdyIAAs, reason: not valid java name */
    public static /* synthetic */ void m21308$r8$lambda$SwnpHR0cMtPMBDUwliQmdyIAAs(BotBiometry r0, String r1, Utilities.Callback r2, Boolean r3, BiometricPrompt.AuthenticationResult r4, BiometricPrompt.CryptoObject r5) {
        r0.lambda$updateToken$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$pEiG_HCeyRVHgYVe8JMkCi7lud8(BotBiometry r0, Utilities.Callback2 r1, Boolean r2, BiometricPrompt.AuthenticationResult r3, BiometricPrompt.CryptoObject r4) {
        r0.lambda$requestToken$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$x4rPWn7Xb2qBrXzfzOciQf9ogj0(ArrayList r7, HashMap r8, Utilities.Callback r9) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r7.size()) goto L13;
        TLRPC.User r3 = (TLRPC.User) r7.get(r2);
        Boolean r4 = (Boolean) r8.get(Long.valueOf(r3.id));
        if (r4 != null) goto L8;
    L11:
        boolean r42 = true;
    L12:
        r0.add(new Bot(r3, r42, null));
        r2 = r2 + 1;
        goto L4
    L8:
        if (r4.booleanValue() == false) goto L11;
        r42 = false;
        goto L12
    L13:
        r9.run(r0);
    }

    /* renamed from: -$$Nest$fgetcallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback2 m21309$$Nest$fgetcallback(BotBiometry r0) {
        return r0.callback;
    }

    /* renamed from: -$$Nest$fputcallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21310$$Nest$fputcallback(BotBiometry r0, Utilities.Callback2 r1) {
        r0.callback = r1;
    }

    static {
        instances = new WeakHashMap();
    }

    private BotBiometry(Context r1, int r2, long r3) {
        this.context = r1.getApplicationContext();
        this.currentAccount = r2;
        this.botId = r3;
        load();
    }

    public static void clear() {
        Context r0 = ApplicationLoader.applicationContext;
        if (r0 != null) goto L5;
        return;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= 16) goto L9;
        r0.getSharedPreferences("2botbiometry_" + r2, 0).edit().clear().apply();
        r2 = r2 + 1;
        goto L7
    L9:
        instances.clear();
    }

    public static BotBiometry get(Context r3, int r4, long r5) {
        Pair<Integer, Long> r0 = new Pair(Integer.valueOf(r4), Long.valueOf(r5));
        WeakHashMap<Pair<Integer, Long>, BotBiometry> r1 = instances;
        BotBiometry r2 = r1.get(r0);
        if (r2 != null) goto L6;
        BotBiometry r22 = new BotBiometry(r3, r4, r5);
        r1.put(r0, r22);
        return r22;
    L6:
        return r2;
    }

    public static String getAvailableType(Context r2) {
        BiometricManager r22 = BiometricManager.from(r2);     // Catch: Exception -> L11
        if (r22 != null) goto L6;
        return null;
    L6:
        if (r22.canAuthenticate(15) == 0) goto L9;
        return null;
    L9:
        return "unknown";
    L11:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static void getBots(Context r7, int r8, Utilities.Callback<ArrayList<Bot>> r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        int r1 = 0;
        SharedPreferences r0 = r7.getSharedPreferences("2botbiometry_" + r8, 0);
        ArrayList r2 = new ArrayList();
        Iterator<Map.Entry<String, ?>> r02 = r0.getAll().entrySet().iterator();
    L6:
        if (r02.hasNext() == false) goto L14;
        String r3 = r02.next().getKey();
        if (r3.endsWith("_requested") == false) goto L6;
        r2.add(Long.valueOf(Long.parseLong(r3.substring(0, r3.length() - 10))));
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L6
    L14:
        HashMap r03 = new HashMap();
        int r32 = r2.size();
    L15:
        if (r1 >= r32) goto L23;
        Object r4 = r2.get(r1);
        r1 = r1 + 1;
        Long r42 = (Long) r4;
        BotBiometry r5 = get(r7, r8, r42.longValue());
        if (r5.access_granted == false) goto L15;
        if (r5.access_requested == false) goto L15;
        r03.put(r42, Boolean.valueOf(!r5.disabled));
        goto L15
    L23:
        if (r2.isEmpty() == false) goto L26;
        r9.run(new ArrayList());
        return;
    L26:
        MessagesStorage.getInstance(r8).getStorageQueue().postRunnable(new BotBiometry$$ExternalSyntheticLambda0(r8, r2, r03, r9));
    }

    private Cipher getCipher() {
        return Cipher.getInstance("AES/CBC/PKCS7Padding");
    }

    public static String getDeviceId(Context r2, int r3, long r4) {
        SharedPreferences r22 = r2.getSharedPreferences("2botbiometry_" + r3, 0);
        String r32 = r22.getString("device_id" + r4, null);
        if (r32 != null) goto L6;
        byte[] r33 = new byte[32];
        new SecureRandom().nextBytes(r33);
        String r34 = Utilities.bytesToHex(r33);
        r22.edit().putString("device_id" + r4, r34).apply();
        return r34;
    L6:
        return r32;
    }

    private SecretKey getSecretKey() {
        if (keyStore != null) goto L6;
        KeyStore r0 = KeyStore.getInstance("AndroidKeyStore");
        keyStore = r0;
        r0.load(null);
    L6:
        if (keyStore.containsAlias("9bot_" + this.botId) == true) goto L8;
        KeyGenParameterSpec.Builder r02 = new KeyGenParameterSpec.Builder("9bot_" + this.botId, 3);
        r02.setBlockModes(new String[]{"CBC"});
        r02.setEncryptionPaddings(new String[]{"PKCS7Padding"});
        r02.setUserAuthenticationRequired(true);
        if (Build.VERSION.SDK_INT < 30) goto L12;
        r02.setUserAuthenticationParameters(60, 2);
    L12:
        r02.setInvalidatedByBiometricEnrollment(true);
        KeyGenerator r7 = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        r7.init(r02.build());
        return r7.generateKey();
    L8:
        return (SecretKey) keyStore.getKey("9bot_" + this.botId, null);
    }

    private void initPrompt() {
        if (this.prompt == null) goto L5;
        return;
    L5:
        Executor r0 = ContextCompat.getMainExecutor(this.context);
        this.prompt = new BiometricPrompt(LaunchActivity.instance, r0, new AnonymousClass1(this));
    }

    private /* synthetic */ void lambda$requestToken$0(Utilities.Callback2 r3, Boolean r4, BiometricPrompt.AuthenticationResult r5, BiometricPrompt.CryptoObject r6) {
        String r0 = null;
        if (r5 != null) goto L24;
    L22:
        r3.run(r4, r0);
        return;
    L24:
    L8:
        e = move-exception;
        FileLog.e(e);
        r4 = Boolean.FALSE;
        goto L22
    L5:
        if (Build.VERSION.SDK_INT < 30) goto L10;
        r6 = makeCryptoObject(true);     // Catch: Exception -> L8
    L10:
        String r52 = this.encrypted_token;
        if (r6 == null) goto L17;
        if (TextUtils.isEmpty(r52) == true) goto L15;
        r0 = new String(r6.getCipher().doFinal(Utilities.hexToBytes(this.encrypted_token)), StandardCharsets.UTF_8);     // Catch: Exception -> L8
        goto L22
    L15:
        r0 = this.encrypted_token;     // Catch: Exception -> L8
        goto L22
    L17:
        if (TextUtils.isEmpty(r52) == true) goto L22;
        throw new RuntimeException("No cryptoObject found");     // Catch: Exception -> L8
    }

    private /* synthetic */ void lambda$updateToken$1(String r2, Utilities.Callback r3, Boolean r4, BiometricPrompt.AuthenticationResult r5, BiometricPrompt.CryptoObject r6) {
        if (r5 != null) goto L19;
    L17:
        r3.run(r4);
        return;
    L19:
    L6:
        e = move-exception;
        FileLog.e(e);
        r4 = Boolean.FALSE;
        goto L17
    L4:
        if (TextUtils.isEmpty(r2) == false) goto L9;
        this.encrypted_token = null;     // Catch: Exception -> L6
        this.iv = null;     // Catch: Exception -> L6
    L13:
        save();     // Catch: Exception -> L6
        goto L17
    L9:
        if (Build.VERSION.SDK_INT < 30) goto L11;
        r6 = makeCryptoObject(false);     // Catch: Exception -> L6
    L11:
        if (r6 == null) goto L15;
        this.encrypted_token = Utilities.bytesToHex(r6.getCipher().doFinal(r2.getBytes(StandardCharsets.UTF_8)));     // Catch: Exception -> L6
        this.iv = Utilities.bytesToHex(r6.getCipher().getIV());     // Catch: Exception -> L6
        goto L13
    L15:
        throw new RuntimeException("No cryptoObject found");     // Catch: Exception -> L6
    }

    private BiometricPrompt.CryptoObject makeCryptoObject(boolean r3) {
        Cipher r0 = getCipher();     // Catch: Exception -> L8
        SecretKey r1 = getSecretKey();     // Catch: Exception -> L8
        if (r3 == false) goto L5;
        r0.init(2, r1, new IvParameterSpec(Utilities.hexToBytes(this.iv)));     // Catch: Exception -> L8
    L6:
        return new BiometricPrompt.CryptoObject(r0);
    L5:
        r0.init(1, r1);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private void prompt(String r6, boolean r7, String r8, Utilities.Callback3<Boolean, BiometricPrompt.AuthenticationResult, BiometricPrompt.CryptoObject> r9) {
        BiometricPrompt.CryptoObject r0 = null;
        this.callback = null;
        initPrompt();     // Catch: Exception -> L32
        BiometricPrompt.CryptoObject r1 = makeCryptoObject(r7);
        TLRPC.User r2 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        BiometricPrompt.PromptInfo.Builder r22 = new BiometricPrompt.PromptInfo.Builder().setTitle(UserObject.getUserName(r2)).setNegativeButtonText(LocaleController.getString(R.string.Back)).setAllowedAuthenticators(15);
        if (TextUtils.isEmpty(r6) == true) goto L7;
        r22.setDescription(r6);
    L7:
        BiometricPrompt.PromptInfo r62 = r22.build();
        if (r1 == null) goto L21;
        if (r7 == true) goto L21;
        if (Build.VERSION.SDK_INT < 30) goto L21;
    L15:
        e = move-exception;
        FileLog.e(e);
        r1 = makeCryptoObject(r7);
        goto L21
    L13:
        if (TextUtils.isEmpty(r8) == false) goto L17;
        this.encrypted_token = null;     // Catch: Exception -> L15
    L18:
        save();     // Catch: Exception -> L15
        r9.run(Boolean.TRUE, null, null);     // Catch: Exception -> L15
        return;
    L17:
        this.encrypted_token = Utilities.bytesToHex(r1.getCipher().doFinal(r8.getBytes(StandardCharsets.UTF_8)));     // Catch: Exception -> L15
        this.iv = Utilities.bytesToHex(r1.getCipher().getIV());     // Catch: Exception -> L15
    L21:
        if (r1 != null) goto L23;
    L25:
        this.callback = new BotBiometry$$ExternalSyntheticLambda4(r9, r0);
        if (r1 != null) goto L28;
    L30:
        this.prompt.authenticate(r62);
        return;
    L28:
        if (Build.VERSION.SDK_INT >= 30) goto L30;
        this.prompt.authenticate(r62, r1);
        return;
    L23:
        if (Build.VERSION.SDK_INT >= 30) goto L25;
        r0 = r1;
    L32:
        e = move-exception;
        FileLog.e(e);
        r9.run(Boolean.FALSE, null, null);
    }

    public static void toggleBotDisabled(Context r2, int r3, long r4, boolean r6) {
        SharedPreferences r22 = r2.getSharedPreferences("2botbiometry_" + r3, 0);
        SharedPreferences.Editor r32 = r22.edit();
        r32.putBoolean(r4 + "_disabled", r6);
        if (r6 == false) goto L5;
    L7:
        r32.apply();
        return;
    L5:
        if (r22.getString(String.valueOf(r4), null) != null) goto L7;
        r32.putString(String.valueOf(r4), _UrlKt.FRAGMENT_ENCODE_SET);
        goto L7
    }

    public boolean asked() {
        return this.access_requested;
    }

    public JSONObject getStatus() {
        JSONObject r0 = new JSONObject();
        String r1 = getAvailableType(this.context);
        boolean r2 = false;
        if (r1 == null) goto L5;
        r0.put("available", true);
        r0.put("type", r1);
    L6:
        r0.put("access_requested", this.access_requested);
        if (this.access_granted == true) goto L9;
    L11:
        r0.put("access_granted", r2);
        r0.put("token_saved", !TextUtils.isEmpty(this.encrypted_token));
        r0.put("device_id", getDeviceId(this.context, this.currentAccount, this.botId));
        return r0;
    L9:
        if (this.disabled == true) goto L11;
        r2 = true;
        goto L11
    L5:
        r0.put("available", false);
        goto L6
    }

    public boolean granted() {
        return this.access_granted;
    }

    public void load() {
        SharedPreferences r0 = this.context.getSharedPreferences("2botbiometry_" + this.currentAccount, 0);
        this.encrypted_token = r0.getString(String.valueOf(this.botId), null);
        this.iv = r0.getString(String.valueOf(this.botId) + "_iv", null);
        boolean r3 = true;
        if (this.encrypted_token == null) goto L5;
        boolean r1 = true;
    L6:
        this.access_granted = r1;
        if (r1 == false) goto L9;
    L12:
        this.access_requested = r3;
        this.disabled = r0.getBoolean(this.botId + "_disabled", false);
        return;
    L9:
        if (r0.getBoolean(this.botId + "_requested", false) == true) goto L12;
        r3 = false;
        goto L12
    L5:
        r1 = false;
        goto L6
    }

    public void requestToken(String r3, Utilities.Callback2<Boolean, String> r4) {
        prompt(r3, true, null, new BotBiometry$$ExternalSyntheticLambda2(this, r4));
    }

    public void save() {
        SharedPreferences.Editor r0 = this.context.getSharedPreferences("2botbiometry_" + this.currentAccount, 0).edit();
        boolean r1 = this.access_requested;
        long r2 = this.botId;
        if (r1 == false) goto L5;
        r0.putBoolean(r2 + "_requested", true);
    L6:
        boolean r12 = this.access_granted;
        long r22 = this.botId;
        if (r12 == false) goto L16;
        String r13 = String.valueOf(r22);
        String r23 = this.encrypted_token;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r23 != null) goto L11;
        r23 = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        r0.putString(r13, r23);
        String r14 = String.valueOf(this.botId) + "_iv";
        String r24 = this.iv;
        if (r24 == null) goto L15;
        r3 = r24;
    L15:
        r0.putString(r14, r3);
    L17:
        boolean r15 = this.disabled;
        long r25 = this.botId;
        if (r15 == false) goto L20;
        r0.putBoolean(r25 + "_disabled", true);
    L21:
        r0.apply();
        return;
    L20:
        r0.remove(r25 + "_disabled");
        goto L21
    L16:
        r0.remove(String.valueOf(r22));
        r0.remove(String.valueOf(this.botId) + "_iv");
        goto L17
    L5:
        r0.remove(r2 + "_requested");
        goto L6
    }

    public void setGranted(boolean r2) {
        this.access_requested = true;
        this.access_granted = r2;
        save();
    }

    public void updateToken(String r2, String r3, Utilities.Callback<Boolean> r4) {
        prompt(r2, false, r3, new BotBiometry$$ExternalSyntheticLambda3(this, r3, r4));
    }
}
