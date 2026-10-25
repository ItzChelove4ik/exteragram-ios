package org.telegram.messenger;

import java.lang.Thread;

/* loaded from: classes.dex */
public final /* synthetic */ class ApplicationLoader$$ExternalSyntheticLambda3 implements Thread.UncaughtExceptionHandler {
    public final /* synthetic */ Thread.UncaughtExceptionHandler f$0;

    public /* synthetic */ ApplicationLoader$$ExternalSyntheticLambda3(Thread.UncaughtExceptionHandler r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread r1, Throwable r2) {
        ApplicationLoader.$r8$lambda$NY8LiAdXEiNm7oQtKqIv0_Zxl0c(this.f$0, r1, r2);
    }
}
