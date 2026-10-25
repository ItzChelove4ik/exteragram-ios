package androidx.media3.common.util;

import androidx.media3.common.util.ListenerSet;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes3.dex */
public final /* synthetic */ class ListenerSet$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ CopyOnWriteArraySet f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ListenerSet.Event f$2;

    public /* synthetic */ ListenerSet$$ExternalSyntheticLambda1(CopyOnWriteArraySet r1, int r2, ListenerSet.Event r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListenerSet.m757$r8$lambda$MawsDUsVhcg5uFQgolq5mcQJiM(this.f$0, this.f$1, this.f$2);
    }
}
