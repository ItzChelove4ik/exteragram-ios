package org.chromium.net.impl;

import android.content.Context;
import java.util.Arrays;
import org.chromium.net.CronetEngine;
import org.chromium.net.CronetProvider;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.httpflags.ResolvedFlags;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public class JavaCronetProvider extends CronetProvider {
    public JavaCronetProvider(Context r1) {
        super(r1);
    }

    private boolean shouldUseHttpEngine() {
        if (HttpEngineNativeProvider.isHttpEngineAvailable() == true) goto L5;
        return false;
    L5:
        ResolvedFlags.Value r2 = HttpFlagsForImpl.getHttpFlags(this.mContext, CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK).flags().get("Cronet_ForceHttpEngineInFallback");
        if (r2 != null) goto L8;
    L11:
        return false;
    L8:
        if (r2.getBoolValue() == false) goto L11;
        return true;
    }

    @Override // org.chromium.net.CronetProvider
    public CronetEngine.Builder createBuilder() {
        if (shouldUseHttpEngine() == false) goto L7;
        return new HttpEngineNativeProvider(this.mContext).createBuilder();
    L7:
        return new ExperimentalCronetEngine.Builder(new JavaCronetEngineBuilderImpl(this.mContext));
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if ((r2 instanceof JavaCronetProvider) == true) goto L6;
        return false;
    L6:
        if (this.mContext.equals(((JavaCronetProvider) r2).mContext) == true) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // org.chromium.net.CronetProvider
    public String getName() {
        return "Fallback-Cronet-Provider";
    }

    @Override // org.chromium.net.CronetProvider
    public String getVersion() {
        return ImplVersion.getCronetVersion();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{JavaCronetProvider.class, this.mContext});
    }

    @Override // org.chromium.net.CronetProvider
    public boolean isEnabled() {
        return true;
    }
}
