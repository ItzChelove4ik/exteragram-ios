package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda77 implements AsyncFunction {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaSessionStub.MediaItemsWithStartPositionPlayerTask f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda77(MediaSessionImpl r1, MediaSession.ControllerInfo r2, MediaSessionStub.MediaItemsWithStartPositionPlayerTask r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object r3) {
        return MediaSessionStub.m906$r8$lambda$2VLwnFvVDZXEX9OENpl0EQqWb0(this.f$0, this.f$1, this.f$2, (MediaSession.MediaItemsWithStartPosition) r3);
    }
}
