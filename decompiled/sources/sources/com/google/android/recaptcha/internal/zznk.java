package com.google.android.recaptcha.internal;

import android.security.keystore.KeyGenParameterSpec;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;

/* loaded from: classes5.dex */
public final class zznk implements zznf {
    private final String zza;
    private final Lazy zzb;

    public zznk(String r1) {
        this.zza = "recck";
        this.zzb = LazyKt.lazy(zznj.zza);
    }

    @Override // com.google.android.recaptcha.internal.zznf
    public final byte[] zza(byte[] r3) {
        Cipher r0 = Cipher.getInstance("AES/GCM/NoPadding");
        r0.init(1, zzc());
        return ArraysKt.plus(r0.getIV(), r0.doFinal(r3));
    }

    @Override // com.google.android.recaptcha.internal.zznf
    public final byte[] zzb(byte[] r6) {
        Cipher r0 = Cipher.getInstance("AES/GCM/NoPadding");     // Catch: Exception -> L4
        r0.init(2, zzc(), new GCMParameterSpec(128, r6, 0, 12));     // Catch: Exception -> L4
        return r0.doFinal(r6, 12, r6.length - 12);
    L4:
        return null;
    }

    public final SecretKey zzc() {
        KeyStore r0 = (KeyStore) this.zzb.getValue();
        String r3 = this.zza;
        KeyStore.SecretKeyEntry r1 = null;
        KeyStore.Entry r02 = r0.getEntry(r3, null);
        if ((r02 instanceof KeyStore.SecretKeyEntry) == false) goto L5;
        r1 = (KeyStore.SecretKeyEntry) r02;
    L5:
        if (r1 == null) goto L10;
        SecretKey r03 = r1.getSecretKey();
        if (r03 == null) goto L10;
        return r03;
    L10:
        KeyGenerator r04 = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        r04.init(new KeyGenParameterSpec.Builder(r3, 3).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
        return r04.generateKey();
    }
}
