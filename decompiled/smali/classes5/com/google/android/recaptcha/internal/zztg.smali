.class public abstract Lcom/google/android/recaptcha/internal/zztg;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static zza()Lcom/google/android/recaptcha/internal/zzsc;
    .locals 1

    .line 1
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaat;->zza()Lcom/google/android/recaptcha/internal/zzsc;

    move-result-object v0
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    .line 2
    invoke-static {v0}, Lcom/google/protobuf/UnsafeUtil$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    const/4 v0, 0x0

    return-object v0
.end method
