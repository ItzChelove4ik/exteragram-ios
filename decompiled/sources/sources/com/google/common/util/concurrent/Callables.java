package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public abstract class Callables {
    public static /* synthetic */ Object $r8$lambda$igoGSsmydPxoPHWhodWU_i0bGEk(Object r0) {
        return r0;
    }

    public static <T> Callable<T> returning(T r1) {
        return new Callables$$ExternalSyntheticLambda0(r1);
    }
}
