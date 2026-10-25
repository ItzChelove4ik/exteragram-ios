package com.google.mlkit.nl.languageid.bundled.internal;

import com.google.android.gms.internal.mlkit_language_id_bundled.zbi;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate;
import java.util.List;

/* loaded from: classes.dex */
public class ThickLanguageIdRegistrar implements ComponentRegistrar {
    public ThickLanguageIdRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zbi.zbg(Component.intoSetBuilder(LanguageIdentifierCreatorDelegate.class).factory(new zba()).build());
    }
}
