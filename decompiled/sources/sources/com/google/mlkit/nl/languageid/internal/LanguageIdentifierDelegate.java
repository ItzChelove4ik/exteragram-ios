package com.google.mlkit.nl.languageid.internal;

import com.google.mlkit.nl.languageid.IdentifiedLanguage;
import java.util.List;

/* loaded from: classes5.dex */
public interface LanguageIdentifierDelegate {
    List<IdentifiedLanguage> identifyPossibleLanguages(String r1, float r2);

    void init();

    void release();
}
