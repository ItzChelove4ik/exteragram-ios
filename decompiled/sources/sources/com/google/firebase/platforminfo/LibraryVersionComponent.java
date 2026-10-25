package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;

/* loaded from: classes.dex */
public abstract class LibraryVersionComponent {

    public interface VersionExtractor<T> {
        String extract(T r1);
    }

    /* renamed from: $r8$lambda$y-qYaNyKEB8h4Vc2fUd5Fnj2sbE, reason: not valid java name */
    public static /* synthetic */ LibraryVersion m1969$r8$lambda$yqYaNyKEB8h4Vc2fUd5Fnj2sbE(String r1, VersionExtractor r2, ComponentContainer r3) {
        return LibraryVersion.create(r1, r2.extract((Context) r3.get(Context.class)));
    }

    public static Component<?> create(String r0, String r1) {
        return Component.intoSet(LibraryVersion.create(r0, r1), LibraryVersion.class);
    }

    public static Component<?> fromContext(String r2, VersionExtractor<Context> r3) {
        return Component.intoSetBuilder(LibraryVersion.class).add(Dependency.required(Context.class)).factory(new LibraryVersionComponent$$ExternalSyntheticLambda0(r2, r3)).build();
    }
}
