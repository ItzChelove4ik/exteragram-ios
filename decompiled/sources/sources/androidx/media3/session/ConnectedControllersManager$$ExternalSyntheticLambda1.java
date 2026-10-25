package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda1 implements ConnectedControllersManager.AsyncCommand {
    public final /* synthetic */ ConnectedControllersManager f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ Player.Commands f$2;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda1(ConnectedControllersManager r1, MediaSession.ControllerInfo r2, Player.Commands r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
    public final ListenableFuture run() {
        return ConnectedControllersManager.$r8$lambda$Wn1Z224Rn9gy3x_xpq9X0idp7xg(this.f$0, this.f$1, this.f$2);
    }
}
