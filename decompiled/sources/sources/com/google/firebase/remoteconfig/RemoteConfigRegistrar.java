package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.component.AbtComponent;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Keep
/* loaded from: classes.dex */
public class RemoteConfigRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-rc";

    /* renamed from: $r8$lambda$IeHOsPj-kb5eYNLpYHwuM_yIUHY, reason: not valid java name */
    public static /* synthetic */ RemoteConfigComponent m1971$r8$lambda$IeHOsPjkb5eYNLpYHwuM_yIUHY(Qualified r7, ComponentContainer r8) {
        return new RemoteConfigComponent((Context) r8.get(Context.class), (ScheduledExecutorService) r8.get(r7), (FirebaseApp) r8.get(FirebaseApp.class), (FirebaseInstallationsApi) r8.get(FirebaseInstallationsApi.class), ((AbtComponent) r8.get(AbtComponent.class)).get("frc"), r8.getProvider(AnalyticsConnector.class));
    }

    public RemoteConfigRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        Qualified r3 = Qualified.qualified(Blocking.class, ScheduledExecutorService.class);
        return Arrays.asList(new Component[]{Component.builder(RemoteConfigComponent.class, new Class[]{FirebaseRemoteConfigInterop.class}).name(LIBRARY_NAME).add(Dependency.required(Context.class)).add(Dependency.required(r3)).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(FirebaseInstallationsApi.class)).add(Dependency.required(AbtComponent.class)).add(Dependency.optionalProvider(AnalyticsConnector.class)).factory(new RemoteConfigRegistrar$$ExternalSyntheticLambda0(r3)).eagerInDefaultApp().build(), LibraryVersionComponent.create(LIBRARY_NAME, "23.1.0")});
    }
}
