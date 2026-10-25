package androidx.credentials.playservices.controllers;

import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.playservices.controllers.ResponseUtils;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* loaded from: classes3.dex */
public final /* synthetic */ class ResponseUtils$Companion$$ExternalSyntheticLambda4 implements Function0 {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ CredentialManagerCallback f$1;
    public final /* synthetic */ GetCredentialException f$2;

    public /* synthetic */ ResponseUtils$Companion$$ExternalSyntheticLambda4(Executor r1, CredentialManagerCallback r2, GetCredentialException r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return ResponseUtils.Companion.m685$r8$lambda$udknnXJUkCi6PtqNHxZRjzMAXc(this.f$0, this.f$1, this.f$2);
    }
}
