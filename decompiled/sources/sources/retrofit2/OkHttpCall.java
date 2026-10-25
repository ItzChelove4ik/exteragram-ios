package retrofit2;

import c.f$$ExternalSyntheticBUOutline2;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes3.dex */
final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private final Call.Factory callFactory;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private final Object instance;
    private okhttp3.Call rawCall;
    private final RequestFactory requestFactory;
    private final Converter<ResponseBody, T> responseConverter;

    /* renamed from: retrofit2.OkHttpCall$1, reason: invalid class name */
    public class AnonymousClass1 implements okhttp3.Callback {
        final /* synthetic */ OkHttpCall this$0;
        final /* synthetic */ Callback val$callback;

        public AnonymousClass1(OkHttpCall r1, Callback r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        private void callFailure(Throwable r2) {
            this.val$callback.onFailure(this.this$0, r2);     // Catch: Throwable -> L4
            return;
        L4:
            th = move-exception;
            Utils.throwIfFatal(th);
            th.printStackTrace();
        }

        @Override // okhttp3.Callback
        public void onFailure(okhttp3.Call r1, IOException r2) {
            callFailure(r2);
        }

        @Override // okhttp3.Callback
        public void onResponse(okhttp3.Call r1, okhttp3.Response r2) {
            Response<T> r12 = this.this$0.parseResponse(r2);     // Catch: Throwable -> L8
            this.val$callback.onResponse(this.this$0, r12);     // Catch: Throwable -> L5
            return;
        L5:
            th = move-exception;
            Utils.throwIfFatal(th);
            th.printStackTrace();
            return;
        L8:
            th = move-exception;
            Utils.throwIfFatal(th);
            callFailure(th);
        }
    }

    public static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        private final BufferedSource delegateSource;
        IOException thrownException;

        /* renamed from: retrofit2.OkHttpCall$ExceptionCatchingResponseBody$1, reason: invalid class name */
        public class AnonymousClass1 extends ForwardingSource {
            final /* synthetic */ ExceptionCatchingResponseBody this$0;

            public AnonymousClass1(ExceptionCatchingResponseBody r1, Source r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer r1, long r2) {
                return super.read(r1, r2);
            L4:
                e = move-exception;
                this.this$0.thrownException = e;
                throw e;
            }
        }

        public ExceptionCatchingResponseBody(ResponseBody r2) {
            this.delegate = r2;
            this.delegateSource = Okio.buffer(new AnonymousClass1(this, r2.source()));
        }

        @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.delegate.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.delegateSource;
        }

        public void throwIfCaught() {
            IOException r0 = this.thrownException;
            if (r0 != null) goto L5;
            return;
        L5:
            throw r0;
        }
    }

    public static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;
        private final MediaType contentType;

        public NoContentResponseBody(MediaType r1, long r2) {
            this.contentType = r1;
            this.contentLength = r2;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public OkHttpCall(RequestFactory r1, Object r2, Object[] r3, Call.Factory r4, Converter<ResponseBody, T> r5) {
        this.requestFactory = r1;
        this.instance = r2;
        this.args = r3;
        this.callFactory = r4;
        this.responseConverter = r5;
    }

    private okhttp3.Call createRawCall() {
        okhttp3.Call r3 = this.callFactory.newCall(this.requestFactory.create(this.instance, this.args));
        if (r3 == null) goto L5;
        return r3;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Call.Factory returned null.");
        return null;
    }

    private okhttp3.Call getRawCall() {
        okhttp3.Call r0 = this.rawCall;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Throwable r02 = this.creationFailure;
        if (r02 != null) goto L8;
        okhttp3.Call r03 = createRawCall();     // Catch: IOException -> L19 Throwable -> L21 RuntimeException -> L23
        this.rawCall = r03;     // Catch: IOException -> L19 Throwable -> L21 RuntimeException -> L23
        return r03;
    L21:
        e = move-exception;
        Utils.throwIfFatal(e);
        this.creationFailure = e;
        throw e;
    L8:
        if ((r02 instanceof IOException) == true) goto L16;
        if ((r02 instanceof RuntimeException) == false) goto L14;
        throw ((RuntimeException) r02);
    L14:
        throw ((Error) r02);
    L16:
        throw ((IOException) r02);
    }

    @Override // retrofit2.Call
    public void cancel() {
        this.canceled = true;
        monitor-enter(this);
        okhttp3.Call r0 = this.rawCall;     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        if (r0 == null) goto L14;
        r0.cancel();
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return mo22453clone();
    }

    @Override // retrofit2.Call
    /* renamed from: clone */
    public /* bridge */ /* synthetic */ Call mo22453clone() {
        return mo22453clone();
    }

    @Override // retrofit2.Call
    /* renamed from: clone */
    public OkHttpCall<T> mo22453clone() {
        return new OkHttpCall(this.requestFactory, this.instance, this.args, this.callFactory, this.responseConverter);
    }

    @Override // retrofit2.Call
    public void enqueue(Callback<T> r4) {
        Objects.requireNonNull(r4, "callback == null");
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.executed == true) goto L25;
        this.executed = true;     // Catch: Throwable -> L13
        okhttp3.Call r0 = this.rawCall;     // Catch: Throwable -> L13
        Throwable th = this.creationFailure;     // Catch: Throwable -> L13
        if (r0 != null) goto L15;
        if (th != null) goto L15;
        okhttp3.Call r2 = createRawCall();     // Catch: Throwable -> L11
        this.rawCall = r2;     // Catch: Throwable -> L11
        r0 = r2;
        goto L15
    L11:
        th = th;
        Utils.throwIfFatal(th);     // Catch: Throwable -> L13
        this.creationFailure = th;     // Catch: Throwable -> L13
    L15:
        monitor-exit(this);     // Catch: Throwable -> L13
        if (th == null) goto L20;
        r4.onFailure(this, th);
        return;
    L20:
        if (this.canceled == false) goto L22;
        r0.cancel();
    L22:
        r0.enqueue(new AnonymousClass1(this, r4));
        return;
    L25:
        throw new IllegalStateException("Already executed.");     // Catch: Throwable -> L13
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean r1 = true;
        if (this.canceled == false) goto L5;
        return true;
    L5:
        monitor-enter(this);
        okhttp3.Call r0 = this.rawCall;     // Catch: Throwable -> L11
        if (r0 != null) goto L9;
    L13:
        r1 = false;
    L14:
        monitor-exit(this);     // Catch: Throwable -> L11
        return r1;
    L9:
        if (r0.isCanceled() == false) goto L13;
    L11:
        th = move-exception;
        throw th;
    }

    public Response<T> parseResponse(okhttp3.Response r6) {
        ResponseBody r0 = r6.body();
        okhttp3.Response r62 = r6.newBuilder().body(new NoContentResponseBody(r0.contentType(), r0.contentLength())).build();
        int r1 = r62.code();
        if (r1 >= 200) goto L5;
    L28:
        Response<T> r5 = Response.error(Utils.buffer(r0), r62);     // Catch: Throwable -> L23
        r0.close();
        return r5;
    L23:
        th = move-exception;
        r0.close();
        throw th;
    L5:
        if (r1 >= 300) goto L28;
        if (r1 != 204) goto L10;
    L18:
        r0.close();
        return Response.success(null, r62);
    L10:
        if (r1 == 205) goto L18;
        ExceptionCatchingResponseBody r12 = new ExceptionCatchingResponseBody(r0);
        return Response.success(this.responseConverter.convert(r12), r62);
    L15:
        e = move-exception;
        r12.throwIfCaught();
        throw e;
    }

    @Override // retrofit2.Call
    public synchronized Request request() {
        monitor-enter(this);
        Request r0 = getRawCall().request();     // Catch: Throwable -> L6 IOException -> L8
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    L8:
        e = move-exception;
        throw new RuntimeException("Unable to create request.", e);     // Catch: Throwable -> L6
    }
}
