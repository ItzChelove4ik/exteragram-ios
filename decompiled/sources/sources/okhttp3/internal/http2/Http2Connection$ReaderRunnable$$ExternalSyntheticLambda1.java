package okhttp3.internal.http2;

import kotlin.jvm.functions.Function0;
import okhttp3.internal.http2.Http2Connection;

/* loaded from: classes.dex */
public final /* synthetic */ class Http2Connection$ReaderRunnable$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ Http2Connection f$0;
    public final /* synthetic */ Http2Stream f$1;

    public /* synthetic */ Http2Connection$ReaderRunnable$$ExternalSyntheticLambda1(Http2Connection r1, Http2Stream r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Http2Connection.ReaderRunnable.m3755$r8$lambda$XZ_EeCBqRdcY_YZDRWVjMHYQQs(this.f$0, this.f$1);
    }
}
