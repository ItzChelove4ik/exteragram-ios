package org.chromium.support_lib_boundary;

import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public interface WebViewProviderBoundaryInterface {

    @Retention(RetentionPolicy.SOURCE)
    public @interface JavaScriptInjectionTime {
        public static final int DOCUMENT_END = 1;
        public static final int DOCUMENT_START = 0;
    }

    InvocationHandler addDocumentStartJavaScript(String r1, String[] r2);

    InvocationHandler addJavaScriptOnEvent(String r1, String[] r2, int r3, String r4);

    void addWebMessageListener(String r1, String[] r2, InvocationHandler r3);

    void addWebMessageListener(String r1, String[] r2, InvocationHandler r3, String r4);

    void addWebViewNavigationListener(Executor r1, InvocationHandler r2);

    InvocationHandler[] createWebMessageChannel();

    int getJavaScriptWorld(String r1);

    InvocationHandler getProfile();

    WebChromeClient getWebChromeClient();

    WebViewClient getWebViewClient();

    InvocationHandler getWebViewNavigationClient();

    InvocationHandler getWebViewRenderer();

    InvocationHandler getWebViewRendererClient();

    void insertVisualStateCallback(long r1, InvocationHandler r3);

    boolean isAudioMuted();

    InvocationHandler navigate(String r1, InvocationHandler r2);

    void postMessageToMainFrame(InvocationHandler r1, Uri r2);

    void prerenderUrl(String r1, CancellationSignal r2, Executor r3, ValueCallback<Void> r4, ValueCallback<Throwable> r5);

    void prerenderUrl(String r1, CancellationSignal r2, Executor r3, InvocationHandler r4, ValueCallback<Void> r5, ValueCallback<Throwable> r6);

    void removeWebMessageListener(String r1);

    void removeWebMessageListener(String r1, String r2);

    void removeWebViewNavigationListener(InvocationHandler r1);

    void saveState(Bundle r1, int r2, boolean r3);

    void setAudioMuted(boolean r1);

    void setProfile(String r1);

    void setWebViewNavigationClient(InvocationHandler r1);

    void setWebViewRendererClient(InvocationHandler r1);
}
