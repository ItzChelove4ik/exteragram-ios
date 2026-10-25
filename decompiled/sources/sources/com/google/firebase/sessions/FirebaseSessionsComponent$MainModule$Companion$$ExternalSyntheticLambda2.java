package com.google.firebase.sessions;

import androidx.datastore.core.CorruptionException;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final /* synthetic */ class FirebaseSessionsComponent$MainModule$Companion$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ SessionDataSerializer f$0;

    public /* synthetic */ FirebaseSessionsComponent$MainModule$Companion$$ExternalSyntheticLambda2(SessionDataSerializer r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return FirebaseSessionsComponent.MainModule.Companion.m1979$r8$lambda$un_PGJosXsgIuP9JGxZjMJMlHE(this.f$0, (CorruptionException) r1);
    }
}
