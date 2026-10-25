package com.google.mlkit.nl.languageid.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
public final /* synthetic */ class zza implements ComponentFactory {
    public static final /* synthetic */ zza zza = null;

    static {
        zza = new zza();
    }

    private /* synthetic */ zza() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r3) {
        ArrayList r2 = new ArrayList(r3.setOf(LanguageIdentifierCreatorDelegate.class));
        Preconditions.checkState(!r2.isEmpty(), "No delegate creator registered.");
        Collections.sort(r2, zzc.zza);
        return new zzg((Context) r3.get(Context.class), (LanguageIdentifierCreatorDelegate) r2.get(0));
    }
}
