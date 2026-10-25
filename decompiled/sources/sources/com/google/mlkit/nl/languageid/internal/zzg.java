package com.google.mlkit.nl.languageid.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.ModelResource;
import com.google.mlkit.nl.languageid.IdentifiedLanguage;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.TranslateController;

/* loaded from: classes.dex */
public final class zzg extends ModelResource {
    private LanguageIdentifierDelegate zza;
    private LanguageIdentificationOptions zzb;
    private final Context zzc;
    private final LanguageIdentifierCreatorDelegate zzd;
    private final boolean zze;

    public zzg(Context r1, LanguageIdentifierCreatorDelegate r2) {
        this.zzc = r1;
        this.zzd = r2;
        if (r2.getPriority() != 100) goto L5;
        boolean r12 = true;
    L6:
        this.zze = r12;
        return;
    L5:
        r12 = false;
        goto L6
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void load() {
        this.taskQueue.checkIsRunningOnCurrentThread();
        if (this.zza != null) goto L6;
        LanguageIdentifierDelegate r0 = this.zzd.create(this.zzc, this.zzb);
        this.zza = r0;
        r0.init();
        return;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void release() {
        this.taskQueue.checkIsRunningOnCurrentThread();
        LanguageIdentifierDelegate r0 = this.zza;
        if (r0 == null) goto L6;
        r0.release();
        this.zza = null;
        return;
    }

    public final String zzc(String r3, float r4) {
        if (this.zza != null) goto L6;
        load();
    L6:
        if (r3.isEmpty() == false) goto L8;
        return TranslateController.UNKNOWN_LANGUAGE;
    L8:
        Iterator<IdentifiedLanguage> r2 = ((LanguageIdentifierDelegate) Preconditions.checkNotNull(this.zza)).identifyPossibleLanguages(r3, r4).iterator();
    L10:
        if (r2.hasNext() == false) goto L14;
        IdentifiedLanguage r32 = r2.next();
        if ("unknown".equals(r32.getLanguageTag()) == true) goto L10;
        String r22 = r32.getLanguageTag();
    L16:
        if (r22.isEmpty() == false) goto L19;
        return TranslateController.UNKNOWN_LANGUAGE;
    L19:
        if ("iw".equals(r22) == false) goto L25;
        return "he";
    L25:
        return r22;
    L14:
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    }

    public final void zze(LanguageIdentificationOptions r1) {
        this.zzb = r1;
    }

    public final boolean zzf() {
        return this.zze;
    }
}
