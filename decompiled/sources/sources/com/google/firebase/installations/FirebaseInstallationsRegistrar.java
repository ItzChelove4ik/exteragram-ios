package com.google.firebase.installations;

import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.concurrent.FirebaseExecutors;
import com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@Keep
/* loaded from: classes.dex */
public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-installations";

    public static /* synthetic */ FirebaseInstallationsApi $r8$lambda$vJ_ijnislX2JLJx5rFvt8ObqNeg(ComponentContainer r6) {
        return new FirebaseInstallations((FirebaseApp) r6.get(FirebaseApp.class), r6.getProvider(HeartBeatController.class), (ExecutorService) r6.get(Qualified.qualified(Background.class, ExecutorService.class)), FirebaseExecutors.newSequentialExecutor((Executor) r6.get(Qualified.qualified(Blocking.class, Executor.class))));
    }

    public FirebaseInstallationsRegistrar() {
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseInstallationsApi.class).name(LIBRARY_NAME).add(Dependency.required(FirebaseApp.class)).add(Dependency.optionalProvider(HeartBeatController.class)).add(Dependency.required(Qualified.qualified(Background.class, ExecutorService.class))).add(Dependency.required(Qualified.qualified(Blocking.class, Executor.class))).factory(new FirebaseInstallationsRegistrar$$ExternalSyntheticLambda0()).build(), HeartBeatConsumerComponent.create(), LibraryVersionComponent.create(LIBRARY_NAME, "19.1.2")});
    }
}
