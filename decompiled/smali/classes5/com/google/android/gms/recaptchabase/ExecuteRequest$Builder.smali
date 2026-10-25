.class public final Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/gms/recaptchabase/ExecuteRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private final zza:Lcom/google/android/gms/recaptchabase/ExecuteRequest;


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/google/android/gms/recaptchabase/ExecuteRequest;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest;-><init>(Lcom/google/android/gms/recaptchabase/zza;)V

    iput-object v0, p0, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->zza:Lcom/google/android/gms/recaptchabase/ExecuteRequest;

    return-void
.end method


# virtual methods
.method public build()Lcom/google/android/gms/recaptchabase/ExecuteRequest;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->zza:Lcom/google/android/gms/recaptchabase/ExecuteRequest;

    return-object p0
.end method

.method public setExecuteId(Ljava/lang/String;)Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->zza:Lcom/google/android/gms/recaptchabase/ExecuteRequest;

    invoke-static {v0, p1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest;->zzc(Lcom/google/android/gms/recaptchabase/ExecuteRequest;Ljava/lang/String;)V

    return-object p0
.end method

.method public setNonce(Ljava/lang/String;)Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/recaptchabase/ExecuteRequest$Builder;->zza:Lcom/google/android/gms/recaptchabase/ExecuteRequest;

    invoke-static {v0, p1}, Lcom/google/android/gms/recaptchabase/ExecuteRequest;->zzd(Lcom/google/android/gms/recaptchabase/ExecuteRequest;Ljava/lang/String;)V

    return-object p0
.end method
