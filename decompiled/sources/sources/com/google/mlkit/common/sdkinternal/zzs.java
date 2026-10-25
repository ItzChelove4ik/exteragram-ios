package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* loaded from: classes.dex */
public final /* synthetic */ class zzs implements ComponentFactory {
    public /* synthetic */ zzs() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return new SharedPrefManager((Context) r2.get(Context.class));
    }
}
