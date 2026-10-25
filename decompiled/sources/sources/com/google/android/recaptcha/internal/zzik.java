package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import java.util.concurrent.Executors;
import okhttp3.internal.url._UrlKt;
import org.chromium.net.CronetEngine;
import org.chromium.net.impl.JavaCronetProvider;

/* loaded from: classes5.dex */
public final class zzik {
    public static final zzik zza = null;

    static {
        zza = new zzik();
    }

    private zzik() {
    }

    public static /* synthetic */ zzko zza(zzik r19, Application r20, zzii r21, int r22, Object r23) {
        zzih r6 = zzih.zza;
        zzhu r7 = new zzhu();
        zzna r8 = new zzna(new zznd(true), zzb(r20), Executors.newCachedThreadPool(zzhv.zza("rC", null)));
        zzms r13 = new zzms(r8);
        zzmi r14 = new zzmi(r8);
        zzmo r12 = new zzmo(r8);
        zzhq r11 = new zzhq(r6.zzb());
        zzlz r0 = new zzlz(new zzmm(r11, r8));
        zzmq r3 = new zzmq(r6.zza(), r8);
        zzlx r9 = new zzlx(r20, r0, r7, new zzlq(r20, null, 2, null));
        zzhh r15 = new zzhh(r20);
        zzlj r10 = new zzlj(r9, new zzli(r20, r15), new zzhd(null, 1, null));
        zzht r16 = new zzht();
        zznn r02 = new zznn(new zzno(r20));
        zzns r2 = new zzns(r20, new zzop(r20));
        zzgy r92 = new zzgy(r3, r2, r7);
        StandardIntegrityManager r17 = IntegrityManagerFactory.createStandard(r20);
        zzgp r32 = new zzgp(new zzg());
        zznk r4 = new zznk("recck");
        zzhr r33 = new zzhr(r20, GoogleApiAvailabilityLight.getInstance());
        zzon r5 = new zzon(new zzni(r2, r4), new zzng(r02, r4), new zznm(r2, r4));
        zzif r03 = new zzif(r20, r32, r33, r5, true);
        zzlh r04 = new zzlh(r20, r6, r92, r7, r17, r32, r5, r33, new zzdv(new zzmk(r11, r8), r15, r7), new zzij(r20, r33), r11, r12);
        zzlf r05 = new zzlf(r14, r11, r5, r7, r15);
        zzkw r06 = new zzkw(r20, r11, r13, r15, r7);
        zzid r122 = new zzid(_UrlKt.FRAGMENT_ENCODE_SET);
        return new zzko(r20, r7, r04, new zzhl(), r10, r16, r92, r5, r05, r06, r03, r122, r11);
    }

    private static final CronetEngine zzb(Application r1) {
        return new CronetEngine.Builder(r1).build();
    L9:
        return new JavaCronetProvider(r1).createBuilder().build();
    L6:
        return new JavaCronetProvider(r1).createBuilder().build();
    }
}
