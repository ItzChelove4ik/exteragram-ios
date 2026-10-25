package androidx.webkit.internal;

import android.os.Build;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes4.dex */
public abstract class ApiFeature implements ConditionallySupportedFeature {
    private static final Set<ApiFeature> sValues = null;
    private final String mInternalFeatureValue;
    private final String mPublicFeatureValue;

    public static class LAZY_HOLDER {
        static final Set<String> WEBVIEW_APK_FEATURES = null;

        static {
            WEBVIEW_APK_FEATURES = new HashSet(Arrays.asList(WebViewGlueCommunicator.getFactory().getWebViewFeatures()));
        }
    }

    public static class M extends ApiFeature {
        public M(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            return true;
        }
    }

    public static class N extends ApiFeature {
        public N(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            return true;
        }
    }

    public static class NoFramework extends ApiFeature {
        public NoFramework(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            return false;
        }
    }

    public static class NoFrameworkInternal extends ApiFeature {
        public NoFrameworkInternal(String r2) {
            super("IMPLEMENTATION_ONLY_FEATURE", r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            return false;
        }
    }

    public static class O extends ApiFeature {
        public O(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            if (Build.VERSION.SDK_INT < 26) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class O_MR1 extends ApiFeature {
        public O_MR1(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            if (Build.VERSION.SDK_INT < 27) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class P extends ApiFeature {
        public P(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            if (Build.VERSION.SDK_INT < 28) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class Q extends ApiFeature {
        public Q(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            if (Build.VERSION.SDK_INT < 29) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class T extends ApiFeature {
        public T(String r1, String r2) {
            super(r1, r2);
        }

        @Override // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            if (Build.VERSION.SDK_INT < 33) goto L6;
            return true;
        L6:
            return false;
        }
    }

    static {
        sValues = new HashSet();
    }

    public ApiFeature(String r1, String r2) {
        this.mPublicFeatureValue = r1;
        this.mInternalFeatureValue = r2;
        sValues.add(this);
    }

    public static Set<ApiFeature> values() {
        return Collections.unmodifiableSet(sValues);
    }

    @Override // androidx.webkit.internal.ConditionallySupportedFeature
    public String getPublicFeatureName() {
        return this.mPublicFeatureValue;
    }

    @Override // androidx.webkit.internal.ConditionallySupportedFeature
    public boolean isSupported() {
        if (isSupportedByFramework() == false) goto L5;
        return true;
    L5:
        if (isSupportedByWebView() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public abstract boolean isSupportedByFramework();

    public boolean isSupportedByWebView() {
        return BoundaryInterfaceReflectionUtil.containsFeature(LAZY_HOLDER.WEBVIEW_APK_FEATURES, this.mInternalFeatureValue);
    }
}
