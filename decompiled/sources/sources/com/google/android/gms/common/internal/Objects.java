package com.google.android.gms.common.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class Objects {

    /* loaded from: classes4.dex */
    public static final class ToStringHelper {
        private final List zza;
        private final Object zzb;

        public /* synthetic */ ToStringHelper(Object r1, byte[] r2) {
            Preconditions.checkNotNull(r1);
            this.zzb = r1;
            this.zza = new ArrayList();
        }

        public ToStringHelper add(String r4, Object r5) {
            Preconditions.checkNotNull(r4);
            int r0 = r4.length();
            String r52 = String.valueOf(r5);
            int r02 = r0 + 1;
            StringBuilder r2 = new StringBuilder(r02 + r52.length());
            r2.append(r4);
            r2.append("=");
            r2.append(r52);
            String r42 = r2.toString();
            this.zza.add(r42);
            return this;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder(100);
            r0.append(this.zzb.getClass().getSimpleName());
            r0.append('{');
            List r4 = this.zza;
            int r1 = r4.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L8;
            r0.append((String) r4.get(r2));
            if (r2 >= (r1 - 1)) goto L7;
            r0.append(", ");
        L7:
            r2 = r2 + 1;
            goto L3
        L8:
            r0.append('}');
            return r0.toString();
        }
    }

    public static boolean checkBundlesEquality(Bundle r5, Bundle r6) {
        if (r5 == null) goto L19;
        if (r6 == null) goto L19;
        if (r5.size() == r6.size()) goto L9;
        return false;
    L9:
        Set<String> r2 = r5.keySet();
        if (r2.containsAll(r6.keySet()) == true) goto L12;
        return false;
    L12:
        Iterator<String> r22 = r2.iterator();
    L14:
        if (r22.hasNext() == false) goto L18;
        String r3 = r22.next();
        if (equal(r5.get(r3), r6.get(r3)) == true) goto L14;
        return false;
    L18:
        return true;
    L19:
        if (r5 != r6) goto L21;
        return true;
    L21:
        return false;
    }

    public static boolean equal(Object r2, Object r3) {
        if (r2 != r3) goto L5;
        return true;
    L5:
        if (r2 != null) goto L7;
    L9:
        return false;
    L7:
        if (r2.equals(r3) == false) goto L9;
        return true;
    }

    public static int hashCode(Object... r0) {
        return Arrays.hashCode(r0);
    }

    public static ToStringHelper toStringHelper(Object r2) {
        return new ToStringHelper(r2, null);
    }
}
