package androidx.webkit.internal;

import android.os.Build;
import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes4.dex */
public abstract class WebViewGlueCommunicator {

    public static class LAZY_FACTORY_HOLDER {
        static final WebViewProviderFactory INSTANCE = null;

        static {
            INSTANCE = WebViewGlueCommunicator.createGlueProviderFactory();
        }
    }

    public static WebViewProviderFactory createGlueProviderFactory() {
        goto L6
    L4:
        return new WebViewProviderFactoryAdapter((WebViewProviderFactoryBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewProviderFactoryBoundaryInterface.class, fetchGlueProviderFactoryImpl()));
    L7:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L6:
        return new IncompatibleApkWebViewProviderFactory();
    }

    private static InvocationHandler fetchGlueProviderFactoryImpl() {
        return (InvocationHandler) Class.forName("org.chromium.support_lib_glue.SupportLibReflectionUtil", false, getWebViewClassLoader()).getDeclaredMethod("createWebViewProviderFactory", null).invoke(null, null);
    }

    public static WebViewProviderFactory getFactory() {
        return LAZY_FACTORY_HOLDER.INSTANCE;
    }

    public static ClassLoader getWebViewClassLoader() {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return ApiHelperForP.getWebViewClassLoader();
    L7:
        return getWebViewProviderFactory().getClass().getClassLoader();
    }

    private static Object getWebViewProviderFactory() {
        Method r1 = WebView.class.getDeclaredMethod("getFactory", null);     // Catch: IllegalAccessException -> L5 InvocationTargetException -> L7 Throwable -> L9
        r1.setAccessible(true);     // Catch: IllegalAccessException -> L5 InvocationTargetException -> L7 Throwable -> L9
        return r1.invoke(null, null);
    L9:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
