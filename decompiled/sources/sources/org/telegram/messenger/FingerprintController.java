package org.telegram.messenger;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import com.exteragram.messenger.utils.system.SystemUtils;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Locale;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class FingerprintController {
    private static final String KEY_ALIAS = "tmessages_passcode";
    private static Boolean hasChangedFingerprints;
    private static KeyPairGenerator keyPairGenerator;
    private static KeyStore keyStore;

    public static /* synthetic */ void $r8$lambda$lOETchSMcmrg0Q2cqdbnkD15TZ4(boolean r2) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didGenerateFingerprintKeyPair, new Object[]{Boolean.valueOf(r2)});
    }

    public static /* synthetic */ void $r8$lambda$xc1fdNiS8zryYptu2jLElP_yJgY(boolean r0) {
        generateNewKey(r0);
    }

    public FingerprintController() {
    }

    public static boolean checkDeviceFingerprintsChanged() {
        Boolean r0 = hasChangedFingerprints;
        if (r0 == null) goto L14;
        return r0.booleanValue();
    L14:
        Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding").init(2, keyStore.getKey(KEY_ALIAS, null));     // Catch: Exception -> L9 KeyPermanentlyInvalidatedException -> L12
        hasChangedFingerprints = Boolean.FALSE;     // Catch: Exception -> L9 KeyPermanentlyInvalidatedException -> L12
        return false;
    L12:
        hasChangedFingerprints = Boolean.TRUE;
        return true;
    L9:
        e = move-exception;
        FileLog.e(e);
        hasChangedFingerprints = Boolean.FALSE;
        return false;
    }

    public static void checkKeyReady() {
        checkKeyReady(true);
    }

    public static void checkKeyReady(boolean r2) {
        if (isKeyReady() == false) goto L5;
        return;
    L5:
        if (AndroidUtilities.isKeyguardSecure() == true) goto L7;
        return;
    L7:
        if (SystemUtils.hasBiometrics() == false) goto L12;
        Utilities.globalQueue.postRunnable(new FingerprintController$$ExternalSyntheticLambda0(r2));
        return;
    }

    public static void deleteInvalidKey() {
        getKeyStore().deleteEntry(KEY_ALIAS);     // Catch: KeyStoreException -> L5
    L7:
        hasChangedFingerprints = null;
        checkKeyReady(false);
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private static void generateNewKey(boolean r5) {
        KeyPairGenerator r0 = getKeyPairGenerator();
        if (r0 == null) goto L16;
        Locale r1 = Locale.getDefault();     // Catch: Exception -> L6 InvalidAlgorithmParameterException -> L11
        setLocale(Locale.ENGLISH);     // Catch: Exception -> L6 InvalidAlgorithmParameterException -> L11
        r0.initialize(new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setDigests(new String[]{"SHA-256", "SHA-512"}).setEncryptionPaddings(new String[]{"OAEPPadding"}).setUserAuthenticationRequired(true).build());     // Catch: Exception -> L6 InvalidAlgorithmParameterException -> L11
        r0.generateKeyPair();     // Catch: Exception -> L6 InvalidAlgorithmParameterException -> L11
        setLocale(r1);     // Catch: Exception -> L6 InvalidAlgorithmParameterException -> L11
        AndroidUtilities.runOnUIThread(new FingerprintController$$ExternalSyntheticLambda1(r5));     // Catch: Exception -> L6 InvalidAlgorithmParameterException -> L11
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        e = move-exception;
        if (e.getClass().getName().equals("android.security.KeyStoreException") == true) goto L18;
        FileLog.e(e);
        return;
    L18:
        return;
    }

    private static KeyPairGenerator getKeyPairGenerator() {
        KeyPairGenerator r0 = keyPairGenerator;
        if (r0 == null) goto L10;
        return r0;
    L10:
        KeyPairGenerator r02 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");     // Catch: Exception -> L7
        keyPairGenerator = r02;     // Catch: Exception -> L7
        return r02;
    L7:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static KeyStore getKeyStore() {
        KeyStore r0 = keyStore;
        if (r0 == null) goto L11;
        return r0;
    L11:
        KeyStore r1 = KeyStore.getInstance("AndroidKeyStore");     // Catch: Exception -> L8
        keyStore = r1;     // Catch: Exception -> L8
        r1.load(null);     // Catch: Exception -> L8
        return keyStore;
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static boolean isKeyReady() {
        return getKeyStore().containsAlias(KEY_ALIAS);
    L4:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    private static void setLocale(Locale r2) {
        Locale.setDefault(r2);
        Resources r0 = ApplicationLoader.applicationContext.getResources();
        Configuration r1 = r0.getConfiguration();
        r1.setLocale(r2);
        r0.updateConfiguration(r1, r0.getDisplayMetrics());
    }
}
