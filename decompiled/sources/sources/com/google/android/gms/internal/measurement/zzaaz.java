package com.google.android.gms.internal.measurement;

import java.util.Comparator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzaaz implements Comparator {
    public zzaaz() {
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ((String) ((Map.Entry) r1).getKey()).compareTo((String) ((Map.Entry) r2).getKey());
    }
}
