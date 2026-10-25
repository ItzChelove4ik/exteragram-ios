package okhttp3.internal.http;

import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u0010\u0010\u0011JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001eJ\n\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\u0018\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\u0018\u0010%\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\u0018\u0010&\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010\u0002\u001a\u00020'H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\r\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u000e\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0014\u0010\u000f\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u000e\u0010\u001c\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "call", "Lokhttp3/internal/connection/RealCall;", "interceptors", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Interceptor;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "exchange", "Lokhttp3/internal/connection/Exchange;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "connectTimeoutMillis", "readTimeoutMillis", "writeTimeoutMillis", "<init>", "(Lokhttp3/internal/connection/RealCall;Ljava/util/List;ILokhttp3/internal/connection/Exchange;Lokhttp3/Request;III)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getRequest$okhttp", "()Lokhttp3/Request;", "getConnectTimeoutMillis$okhttp", "()I", "getReadTimeoutMillis$okhttp", "getWriteTimeoutMillis$okhttp", "calls", "copy", "copy$okhttp", "connection", "Lokhttp3/Connection;", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "Lokhttp3/Call;", "proceed", "Lokhttp3/Response;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealInterceptorChain.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealInterceptorChain.kt\nokhttp3/internal/http/RealInterceptorChain\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,139:1\n1#2:140\n*E\n"})
/* loaded from: classes.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final RealCall call;
    private int calls;
    private final int connectTimeoutMillis;
    private final Exchange exchange;
    private final int index;
    private final List<Interceptor> interceptors;
    private final int readTimeoutMillis;
    private final Request request;
    private final int writeTimeoutMillis;

    /* JADX WARN: Multi-variable type inference failed */
    public RealInterceptorChain(RealCall r1, List<? extends Interceptor> r2, int r3, Exchange r4, Request r5, int r6, int r7, int r8) {
        this.call = r1;
        this.interceptors = r2;
        this.index = r3;
        this.exchange = r4;
        this.request = r5;
        this.connectTimeoutMillis = r6;
        this.readTimeoutMillis = r7;
        this.writeTimeoutMillis = r8;
    }

    public static /* synthetic */ RealInterceptorChain copy$okhttp$default(RealInterceptorChain r0, int r1, Exchange r2, Request r3, int r4, int r5, int r6, int r7, Object r8) {
        if ((r7 & 1) == 0) goto L6;
        r1 = r0.index;
    L6:
        if ((r7 & 2) == 0) goto L9;
        r2 = r0.exchange;
    L9:
        if ((r7 & 4) == 0) goto L12;
        r3 = r0.request;
    L12:
        if ((r7 & 8) == 0) goto L15;
        r4 = r0.connectTimeoutMillis;
    L15:
        if ((r7 & 16) == 0) goto L18;
        r5 = r0.readTimeoutMillis;
    L18:
        if ((r7 & 32) == 0) goto L20;
        r6 = r0.writeTimeoutMillis;
    L20:
        int r72 = r5;
        int r82 = r6;
        Request r52 = r3;
        int r62 = r4;
        return r0.copy$okhttp(r1, r2, r52, r62, r72, r82);
    }

    @Override // okhttp3.Interceptor.Chain
    public Call call() {
        return this.call;
    }

    @Override // okhttp3.Interceptor.Chain
    public int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Override // okhttp3.Interceptor.Chain
    public Connection connection() {
        Exchange r0 = this.exchange;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getConnection$okhttp();
    }

    public final RealInterceptorChain copy$okhttp(int r10, Exchange r11, Request r12, int r13, int r14, int r15) {
        return new RealInterceptorChain(this.call, this.interceptors, r10, r11, r12, r13, r14, r15);
    }

    public final RealCall getCall$okhttp() {
        return this.call;
    }

    public final int getConnectTimeoutMillis$okhttp() {
        return this.connectTimeoutMillis;
    }

    public final Exchange getExchange$okhttp() {
        return this.exchange;
    }

    public final int getReadTimeoutMillis$okhttp() {
        return this.readTimeoutMillis;
    }

    public final Request getRequest$okhttp() {
        return this.request;
    }

    public final int getWriteTimeoutMillis$okhttp() {
        return this.writeTimeoutMillis;
    }

    @Override // okhttp3.Interceptor.Chain
    public Response proceed(Request r15) {
        if (this.index >= this.interceptors.size()) goto L29;
        this.calls++;
        Exchange r0 = this.exchange;
        if (r0 != null) goto L7;
    L15:
        RealInterceptorChain r14 = copy$okhttp$default(this, this.index + 1, null, r15, 0, 0, 0, 58, null);
        Interceptor r152 = this.interceptors.get(this.index);
        Response r02 = r152.intercept(r14);
        if (r02 != null) goto L18;
        RealInterceptorChain$$ExternalSyntheticBUOutline0.m("interceptor ", r152, " returned null");
        return null;
    L18:
        if (this.exchange != null) goto L20;
    L26:
        return r02;
    L20:
        if ((this.index + 1) >= this.interceptors.size()) goto L26;
        if (r14.calls == 1) goto L26;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("network interceptor ", r152, " must call proceed() exactly once");
        return null;
    L7:
        if (r0.getFinder$okhttp().getRoutePlanner().sameHostAndPort(r15.url()) == true) goto L9;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("network interceptor ", this.interceptors.get(this.index - 1), " must retain the same host and port");
        return null;
    L9:
        if (this.calls == 1) goto L15;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("network interceptor ", this.interceptors.get(this.index - 1), " must call proceed() exactly once");
        return null;
    L29:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    }

    @Override // okhttp3.Interceptor.Chain
    public int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @Override // okhttp3.Interceptor.Chain
    public Request request() {
        return this.request;
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withConnectTimeout(int r13, TimeUnit r14) {
        if (this.exchange == null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Timeouts can't be adjusted in a network interceptor");
        return null;
    L5:
        return copy$okhttp$default(this, 0, null, null, _UtilJvmKt.checkDuration("connectTimeout", r13, r14), 0, 0, 55, null);
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withReadTimeout(int r13, TimeUnit r14) {
        if (this.exchange == null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Timeouts can't be adjusted in a network interceptor");
        return null;
    L5:
        return copy$okhttp$default(this, 0, null, null, 0, _UtilJvmKt.checkDuration("readTimeout", r13, r14), 0, 47, null);
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withWriteTimeout(int r13, TimeUnit r14) {
        if (this.exchange == null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Timeouts can't be adjusted in a network interceptor");
        return null;
    L5:
        return copy$okhttp$default(this, 0, null, null, 0, 0, _UtilJvmKt.checkDuration("writeTimeout", r13, r14), 31, null);
    }

    @Override // okhttp3.Interceptor.Chain
    public int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }
}
