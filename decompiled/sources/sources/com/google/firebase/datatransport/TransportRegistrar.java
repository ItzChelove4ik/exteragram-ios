package com.google.firebase.datatransport;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

@Keep
/* loaded from: classes.dex */
public class TransportRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-transport";

    /* renamed from: $r8$lambda$5P-SoDbsAOSc7vMtr9P22GdjNQw, reason: not valid java name */
    public static /* synthetic */ TransportFactory m1950$r8$lambda$5PSoDbsAOSc7vMtr9P22GdjNQw(ComponentContainer r1) {
        TransportRuntime.initialize((Context) r1.get(Context.class));
        return TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE);
    }

    public static /* synthetic */ TransportFactory $r8$lambda$Q9p8Nf35Faci7Q8zAMPraOdCTJ0(ComponentContainer r1) {
        TransportRuntime.initialize((Context) r1.get(Context.class));
        return TransportRuntime.getInstance().newFactory(CCTDestination.LEGACY_INSTANCE);
    }

    public static /* synthetic */ TransportFactory $r8$lambda$QkqDNTnBQvxCK9qVVo8puvSQXG0(ComponentContainer r1) {
        TransportRuntime.initialize((Context) r1.get(Context.class));
        return TransportRuntime.getInstance().newFactory(CCTDestination.LEGACY_INSTANCE);
    }

    public TransportRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(TransportFactory.class).name(LIBRARY_NAME).add(Dependency.required(Context.class)).factory(new TransportRegistrar$$ExternalSyntheticLambda0()).build(), Component.builder(Qualified.qualified(LegacyTransportBackend.class, TransportFactory.class)).add(Dependency.required(Context.class)).factory(new TransportRegistrar$$ExternalSyntheticLambda1()).build(), Component.builder(Qualified.qualified(TransportBackend.class, TransportFactory.class)).add(Dependency.required(Context.class)).factory(new TransportRegistrar$$ExternalSyntheticLambda2()).build(), LibraryVersionComponent.create(LIBRARY_NAME, "19.0.0")});
    }
}
