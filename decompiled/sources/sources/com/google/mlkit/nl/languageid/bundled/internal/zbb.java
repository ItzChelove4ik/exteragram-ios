package com.google.mlkit.nl.languageid.bundled.internal;

import android.content.Context;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierDelegate;

/* loaded from: classes5.dex */
public final class zbb implements LanguageIdentifierCreatorDelegate {
    public zbb() {
    }

    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate
    public final LanguageIdentifierDelegate create(Context r1, LanguageIdentificationOptions r2) {
        return new ThickLanguageIdentifier(r1, r2);
    }

    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate
    public final int getPriority() {
        return 100;
    }
}
