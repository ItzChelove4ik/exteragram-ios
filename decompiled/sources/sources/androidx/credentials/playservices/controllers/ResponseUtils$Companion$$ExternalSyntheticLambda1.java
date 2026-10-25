package androidx.credentials.playservices.controllers;

import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.playservices.controllers.ResponseUtils;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class ResponseUtils$Companion$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ CredentialManagerCallback f$1;

    public /* synthetic */ ResponseUtils$Companion$$ExternalSyntheticLambda1(Executor r1, CredentialManagerCallback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return ResponseUtils.Companion.m683$r8$lambda$Y1y08ow1t71qyW_XseA_tVzVBA(this.f$0, this.f$1, (GetCredentialException) r2);
    }
}
