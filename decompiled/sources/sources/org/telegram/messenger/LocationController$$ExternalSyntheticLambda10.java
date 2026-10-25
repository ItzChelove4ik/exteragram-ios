package org.telegram.messenger;

import android.location.Location;
import org.telegram.messenger.LocationController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class LocationController$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ LocationController.LocationFetchCallback f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_messageMediaVenue f$3;
    public final /* synthetic */ TLRPC.TL_messageMediaVenue f$4;
    public final /* synthetic */ Location f$5;

    public /* synthetic */ LocationController$$ExternalSyntheticLambda10(LocationController.LocationFetchCallback r1, String r2, String r3, TLRPC.TL_messageMediaVenue r4, TLRPC.TL_messageMediaVenue r5, Location r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LocationController.m4247$r8$lambda$8phspgl9XnVUhiI1NM3sbFuOEw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
