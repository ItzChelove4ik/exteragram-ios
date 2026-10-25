package androidx.webkit;

import androidx.webkit.internal.WebViewFeatureInternal;

/* loaded from: classes4.dex */
public abstract class WebViewFeature {
    public static boolean isFeatureSupported(String r0) {
        return WebViewFeatureInternal.isSupported(r0);
    }
}
