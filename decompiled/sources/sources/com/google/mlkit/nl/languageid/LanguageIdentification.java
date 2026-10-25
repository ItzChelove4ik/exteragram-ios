package com.google.mlkit.nl.languageid;

import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierImpl;

/* loaded from: classes5.dex */
public abstract class LanguageIdentification {
    public static LanguageIdentifier getClient() {
        return ((LanguageIdentifierImpl.Factory) MlKitContext.getInstance().get(LanguageIdentifierImpl.Factory.class)).create(LanguageIdentificationOptions.zza);
    }
}
