package com.google.android.gms.internal.measurement;

import android.accounts.Account;
import android.content.Context;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public abstract class zzsa {
    static final Account zza = null;
    private static final Pattern zzc = null;
    private static final Set zzd = null;
    private static final Set zze = null;

    static {
        zzc = Pattern.compile("[a-z]+(_[a-z]+)*");
        zza = zzrv.zza;
        zzd = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{PluginsConstants.Settings.DEFAULT, "unused", "special", "reserved", "shared", "virtual", "managed"})));
        zze = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"files", "cache", "managed", "directboot-files", "directboot-cache", "external"})));
    }

    public static zzrz zza(Context r2) {
        return new zzrz(r2, null);
    }

    public static void zzb(String r2) {
        Set r22 = zze;
        zzsq.zza(r22.contains("directboot-files"), "The only supported locations are %s: %s", new Object[]{r22, "directboot-files"});
    }

    public static void zzc(String r3) {
        zzsq.zza(zzc.matcher(r3).matches(), "Module must match [a-z]+(_[a-z]+)*: %s", new Object[]{r3});
        zzsq.zza(!zzd.contains(r3), "Module name is reserved and cannot be used: %s", new Object[]{r3});
    }
}
