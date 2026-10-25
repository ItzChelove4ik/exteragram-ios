package com.google.mlkit.nl.languageid.internal;

import java.util.Comparator;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzc implements Comparator {
    public static final /* synthetic */ zzc zza = null;

    static {
        zza = new zzc();
    }

    private /* synthetic */ zzc() {
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ((LanguageIdentifierCreatorDelegate) r2).getPriority() - ((LanguageIdentifierCreatorDelegate) r1).getPriority();
    }
}
