package androidx.media3.session;

import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSession$Callback$$ExternalSyntheticLambda0 implements AsyncFunction {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ MediaSession$Callback$$ExternalSyntheticLambda0(int r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object r4) {
        return MediaSession.Callback.m865$r8$lambda$lhwU59qlBPXWPmpGG20yrEFlHo(this.f$0, this.f$1, (List) r4);
    }
}
