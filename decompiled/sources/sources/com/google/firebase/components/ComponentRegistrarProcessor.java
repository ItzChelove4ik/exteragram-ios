package com.google.firebase.components;

import java.util.List;

/* loaded from: classes.dex */
public interface ComponentRegistrarProcessor {
    public static final ComponentRegistrarProcessor NOOP = null;

    static {
        NOOP = new ComponentRegistrarProcessor$$ExternalSyntheticLambda0();
    }

    List<Component<?>> processRegistrar(ComponentRegistrar r1);
}
