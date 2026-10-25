package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;

/* loaded from: classes4.dex */
public abstract class zabe {
    private static final ExecutorService zaa = null;

    static {
        zaa = com.google.android.gms.internal.base.zan.zaa().zab(2, new NumberedThreadFactory("GAC_Executor"), 2);
    }

    public static ExecutorService zaa() {
        return zaa;
    }
}
