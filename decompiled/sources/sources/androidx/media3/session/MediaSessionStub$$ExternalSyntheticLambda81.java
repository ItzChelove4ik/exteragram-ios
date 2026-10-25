package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda81 implements AsyncFunction {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaSessionStub.MediaItemPlayerTask f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda81(MediaSessionImpl r1, MediaSession.ControllerInfo r2, MediaSessionStub.MediaItemPlayerTask r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object r3) {
        return MediaSessionStub.$r8$lambda$MGipxOQsujaGdggWl9JrNr2GU4w(this.f$0, this.f$1, this.f$2, (List) r3);
    }
}
