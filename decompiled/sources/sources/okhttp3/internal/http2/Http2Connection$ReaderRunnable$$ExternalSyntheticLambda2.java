package okhttp3.internal.http2;

import kotlin.jvm.functions.Function0;
import okhttp3.internal.http2.Http2Connection;

/* loaded from: classes.dex */
public final /* synthetic */ class Http2Connection$ReaderRunnable$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ Http2Connection.ReaderRunnable f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Settings f$2;

    public /* synthetic */ Http2Connection$ReaderRunnable$$ExternalSyntheticLambda2(Http2Connection.ReaderRunnable r1, boolean r2, Settings r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Http2Connection.ReaderRunnable.$r8$lambda$x5ZPvPMHRGx0rtr2nCeHtpvAJmY(this.f$0, this.f$1, this.f$2);
    }
}
