package com.google.firebase.platforminfo;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.platforminfo.LibraryVersionComponent;

/* loaded from: classes.dex */
public final /* synthetic */ class LibraryVersionComponent$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ LibraryVersionComponent.VersionExtractor f$1;

    public /* synthetic */ LibraryVersionComponent$$ExternalSyntheticLambda0(String r1, LibraryVersionComponent.VersionExtractor r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return LibraryVersionComponent.m1969$r8$lambda$yqYaNyKEB8h4Vc2fUd5Fnj2sbE(this.f$0, this.f$1, r2);
    }
}
