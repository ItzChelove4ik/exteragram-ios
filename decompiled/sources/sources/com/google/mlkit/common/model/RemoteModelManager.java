package com.google.mlkit.common.model;

import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class RemoteModelManager {
    private final Map zza;

    public static class RemoteModelManagerRegistration {
        private final Class zza;
        private final Provider zzb;

        public <RemoteT extends RemoteModel> RemoteModelManagerRegistration(Class<RemoteT> r1, Provider<Object> r2) {
            this.zza = r1;
            this.zzb = r2;
        }

        public final Provider zza() {
            return this.zzb;
        }

        public final Class zzb() {
            return this.zza;
        }
    }

    public RemoteModelManager(Set<RemoteModelManagerRegistration> r4) {
        this.zza = new HashMap();
        Iterator<RemoteModelManagerRegistration> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        RemoteModelManagerRegistration r0 = r42.next();
        this.zza.put(r0.zzb(), r0.zza());
        goto L4
    }
}
