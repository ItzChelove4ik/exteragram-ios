package com.google.mlkit.vision.common.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class MultiFlavorDetectorCreator {
    private final Map zza;

    public interface DetectorCreator<DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> {
        DetectorT create(OptionsT r1);
    }

    /* loaded from: classes5.dex */
    public interface DetectorOptions<DetectorT> {
    }

    /* loaded from: classes5.dex */
    public interface MultiFlavorDetector {
    }

    public static class Registration {
        private final Class zza;
        private final Provider zzb;
        private final int zzc;

        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(Class<? extends OptionsT> r1, Provider<? extends DetectorCreator<DetectorT, OptionsT>> r2, int r3) {
            this.zza = r1;
            this.zzb = r2;
            this.zzc = r3;
        }

        public final int zza() {
            return this.zzc;
        }

        public final Provider zzb() {
            return this.zzb;
        }

        public final Class zzc() {
            return this.zza;
        }
    }

    public MultiFlavorDetectorCreator(Set r6) {
        this.zza = new HashMap();
        HashMap r0 = new HashMap();
        Iterator r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L10;
        Registration r1 = (Registration) r62.next();
        Class r2 = r1.zzc();
        if (this.zza.containsKey(r2) == false) goto L9;
        if (r1.zza() < ((Integer) Preconditions.checkNotNull((Integer) r0.get(r2))).intValue()) goto L4;
    L9:
        this.zza.put(r2, r1.zzb());
        r0.put(r2, Integer.valueOf(r1.zza()));
        goto L4
    }

    public static synchronized MultiFlavorDetectorCreator getInstance() {
        monitor-enter(MultiFlavorDetectorCreator.class);
        MultiFlavorDetectorCreator r1 = (MultiFlavorDetectorCreator) MlKitContext.getInstance().get(MultiFlavorDetectorCreator.class);     // Catch: Throwable -> L7
        monitor-exit(MultiFlavorDetectorCreator.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> DetectorT create(OptionsT r2) {
        return (DetectorT) ((DetectorCreator) ((Provider) Preconditions.checkNotNull((Provider) this.zza.get(r2.getClass()))).get()).create(r2);
    }
}
