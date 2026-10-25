package org.telegram.utils.proxy;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayDeque;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.RequestTimeDelegate;
import org.telegram.utils.proxy.ProxySettings;

/* loaded from: classes6.dex */
public final class WebProxyConnectionTester {
    private static volatile WebProxyConnectionTester instance;
    private Request currentRequest;
    private Runnable nativeCheckTimeoutRunnable;
    private final ArrayDeque<Request> queue;
    private Runnable readyTimeoutRunnable;

    public static final class Request {
        final RequestTimeDelegate delegate;
        int port;
        final ProxySettings settings;
        final TestImplementation testImplementation;

        public Request(TestImplementation r1, ProxySettings r2, RequestTimeDelegate r3) {
            this.testImplementation = r1;
            this.settings = r2;
            this.delegate = r3;
        }
    }

    public interface TestImplementation {
        void doCheck(ProxySettings r1, int r2, RequestTimeDelegate r3);
    }

    /* renamed from: $r8$lambda$-r3LDqvZe-Wsch-I7s1_9SRdtqs, reason: not valid java name */
    public static /* synthetic */ void m22242$r8$lambda$r3LDqvZeWschI7s1_9SRdtqs(WebProxyConnectionTester r0, Request r1) {
        r0.lambda$processNext$2(r1);
    }

    /* renamed from: $r8$lambda$0Cw-P3fd1jmmL8sphiY2bpzYd7k, reason: not valid java name */
    public static /* synthetic */ void m22243$r8$lambda$0CwP3fd1jmmL8sphiY2bpzYd7k(WebProxyConnectionTester r0, Request r1) {
        r0.lambda$onTransportReady$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$Btp45qf0GPwxEwFhLVJHl6nDqaQ(WebProxyConnectionTester r0, Request r1, long r2) {
        r0.lambda$onTransportReady$5(r1, r2);
    }

    /* renamed from: $r8$lambda$VaftKsXTMeXQish5gmoS-deyXA8, reason: not valid java name */
    public static /* synthetic */ void m22244$r8$lambda$VaftKsXTMeXQish5gmoSdeyXA8(WebProxyConnectionTester r0, Request r1, long r2) {
        r0.lambda$onTransportReady$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bXzw5oXp5Pu9iSTPZhLKGU4XBVQ(WebProxyConnectionTester r0, Request r1) {
        r0.lambda$onTransportReady$3(r1);
    }

    /* renamed from: $r8$lambda$gmLiuQYj6J4B6tAEg3z7-02L4lc, reason: not valid java name */
    public static /* synthetic */ void m22245$r8$lambda$gmLiuQYj6J4B6tAEg3z702L4lc(WebProxyConnectionTester r0, Request r1) {
        r0.lambda$processNext$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$ufe7vls3QFXLZj8v4zidu7dGV6M(WebProxyConnectionTester r0, TestImplementation r1, ProxySettings r2, RequestTimeDelegate r3) {
        r0.lambda$checkProxy$0(r1, r2, r3);
    }

    private WebProxyConnectionTester() {
        this.queue = new ArrayDeque();
    }

    private void cancelNativeCheckTimeout() {
        Runnable r0 = this.nativeCheckTimeoutRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.nativeCheckTimeoutRunnable = null;
        return;
    }

    private void cancelReadyTimeout() {
        Runnable r0 = this.readyTimeoutRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.readyTimeoutRunnable = null;
        return;
    }

    private void failCurrent(Request r3) {
        if (this.currentRequest == r3) goto L5;
        return;
    L5:
        cancelReadyTimeout();
        cancelNativeCheckTimeout();
        WebProxyTransport.stopConnectionCheck();
        this.currentRequest = null;
        r3.delegate.run(-1);
        processNext();
    }

    public static WebProxyConnectionTester getInstance() {
        WebProxyConnectionTester r0 = instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(WebProxyConnectionTester.class);
        WebProxyConnectionTester r02 = instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new WebProxyConnectionTester();     // Catch: Throwable -> L9
        instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(WebProxyConnectionTester.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$checkProxy$0(TestImplementation r3, ProxySettings r4, RequestTimeDelegate r5) {
        this.queue.addLast(new Request(r3, r4, r5));
        processNext();
    }

    private /* synthetic */ void lambda$onTransportReady$3(Request r2) {
        if (this.currentRequest == r2) goto L5;
        return;
    L5:
        this.nativeCheckTimeoutRunnable = null;
        failCurrent(r2);
    }

    private /* synthetic */ void lambda$onTransportReady$4(Request r1, long r2) {
        onNativeCheckFinished(r1, r2);
    }

    private /* synthetic */ void lambda$onTransportReady$5(Request r2, long r3) {
        AndroidUtilities.runOnUIThread(new WebProxyConnectionTester$$ExternalSyntheticLambda6(this, r2, r3));
    }

    private /* synthetic */ void lambda$onTransportReady$6(Request r5) {
        if (this.currentRequest == r5) goto L5;
        return;
    L5:
        cancelReadyTimeout();
        int r0 = r5.port;
        if (r0 != 0) goto L9;
        failCurrent(r5);
        return;
    L9:
        WebProxyConnectionTester$$ExternalSyntheticLambda4 r1 = new WebProxyConnectionTester$$ExternalSyntheticLambda4(this, r5);
        this.nativeCheckTimeoutRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, 20000);
        r5.testImplementation.doCheck(r5.settings, r0, new WebProxyConnectionTester$$ExternalSyntheticLambda5(this, r5));
    }

    private /* synthetic */ void lambda$processNext$1(Request r2) {
        if (this.currentRequest == r2) goto L5;
        return;
    L5:
        this.readyTimeoutRunnable = null;
        failCurrent(r2);
    }

    private /* synthetic */ void lambda$processNext$2(Request r1) {
        onTransportReady(r1);
    }

    private void onNativeCheckFinished(Request r2, long r3) {
        if (this.currentRequest == r2) goto L5;
        return;
    L5:
        cancelNativeCheckTimeout();
        WebProxyTransport.stopConnectionCheck();
        this.currentRequest = null;
        r2.delegate.run(r3);
        processNext();
    }

    private void onTransportReady(Request r2) {
        AndroidUtilities.runOnUIThread(new WebProxyConnectionTester$$ExternalSyntheticLambda3(this, r2), 500);
    }

    private void processNext() {
        if (this.currentRequest != null) goto L13;
        Request r0 = this.queue.pollFirst();
        this.currentRequest = r0;
        if (r0 == null) goto L14;
        WebProxyConnectionTester$$ExternalSyntheticLambda1 r1 = new WebProxyConnectionTester$$ExternalSyntheticLambda1(this, r0);
        this.readyTimeoutRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        int r12 = WebProxyTransport.startConnectionCheck(r0.settings.getAddress(), r0.settings.getSecret(), new WebProxyConnectionTester$$ExternalSyntheticLambda2(this, r0));
        r0.port = r12;
        if (r12 != 0) goto L12;
        failCurrent(r0);
        return;
    L12:
        return;
    L14:
        return;
    }

    public void checkProxy(ProxySettings r3, RequestTimeDelegate r4, TestImplementation r5) {
        if (r5 == null) goto L12;
        if (r3 == null) goto L12;
        if (r3.getType() != ProxySettings.Type.WEB) goto L12;
        if (r3.isValid() == false) goto L12;
        if (r4 == null) goto L12;
        AndroidUtilities.runOnUIThread(new WebProxyConnectionTester$$ExternalSyntheticLambda0(this, r5, r3, r4));
        return;
    L12:
        if (r4 == null) goto L15;
        r4.run(-1);
        return;
    }
}
