package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import java.util.HashMap;
import java.util.Random;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Ref;

@SuppressLint({"TikTok.UnpropagatedTraceOnStartActivity"})
/* loaded from: classes4.dex */
public abstract class zzxa {
    static {
        Math.abs(new Random().nextInt());
        new HashMap();
    }

    @JvmStatic
    public static final Runnable zza(Runnable r3) {
        zzws r0 = zzvy.zzb(false);
        return new zzwz(new Ref.ObjectRef(), r0, r3);
    }

    @JvmStatic
    public static final AsyncCallable zzb(AsyncCallable r2) {
        return new zzwx(zzvy.zzb(false), r2);
    }

    @JvmStatic
    public static final AsyncFunction zzc(AsyncFunction r2) {
        return new zzwy(zzvy.zzb(false), r2);
    }
}
