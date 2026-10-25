package com.google.firebase;

import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$4<T> implements ComponentFactory {
    public static final FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$4<T> INSTANCE = null;

    static {
        INSTANCE = new FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$4();
    }

    public FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$4() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public /* bridge */ /* synthetic */ Object create(ComponentContainer r1) {
        return create(r1);
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final CoroutineDispatcher create(ComponentContainer r2) {
        return ExecutorsKt.from((Executor) r2.get(Qualified.qualified(UiThread.class, Executor.class)));
    }
}
