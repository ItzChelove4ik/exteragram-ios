package com.google.firebase.messaging;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.datatransport.TransportBackend;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

@Keep
/* loaded from: classes.dex */
public class FirebaseMessagingRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-fcm";

    public static /* synthetic */ FirebaseMessaging $r8$lambda$h3MOJYVcG4o8djeewqRx1Jpi3s8(Qualified r8, ComponentContainer r9) {
        FirebaseApp r1 = (FirebaseApp) r9.get(FirebaseApp.class);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r9.get(FirebaseInstanceIdInternal.class));
        return new FirebaseMessaging(r1, null, r9.getProvider(UserAgentPublisher.class), r9.getProvider(HeartBeatInfo.class), (FirebaseInstallationsApi) r9.get(FirebaseInstallationsApi.class), r9.getProvider(r8), (Subscriber) r9.get(Subscriber.class));
    }

    public FirebaseMessagingRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    @Keep
    public List<Component<?>> getComponents() {
        Qualified r3 = Qualified.qualified(TransportBackend.class, TransportFactory.class);
        return Arrays.asList(new Component[]{Component.builder(FirebaseMessaging.class).name(LIBRARY_NAME).add(Dependency.required(FirebaseApp.class)).add(Dependency.optional(FirebaseInstanceIdInternal.class)).add(Dependency.optionalProvider(UserAgentPublisher.class)).add(Dependency.optionalProvider(HeartBeatInfo.class)).add(Dependency.required(FirebaseInstallationsApi.class)).add(Dependency.optionalProvider(r3)).add(Dependency.required(Subscriber.class)).factory(new FirebaseMessagingRegistrar$$ExternalSyntheticLambda0(r3)).alwaysEager().build(), LibraryVersionComponent.create(LIBRARY_NAME, "25.1.2")});
    }
}
