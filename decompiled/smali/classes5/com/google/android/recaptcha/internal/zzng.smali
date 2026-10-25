.class public final Lcom/google/android/recaptcha/internal/zzng;
.super Lcom/google/android/recaptcha/internal/zzoa;
.source "SourceFile"


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zznn;Lcom/google/android/recaptcha/internal/zznf;)V
    .locals 11

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzob;->zza:Lcom/google/android/recaptcha/internal/zzob;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzoe;->zza()Ljava/lang/String;

    move-result-object v4

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaka;->zzh()Lcom/google/android/recaptcha/internal/zzahe;

    move-result-object v9

    const/4 v10, 0x0

    const/16 v5, 0x3a

    const/16 v6, 0x38

    const/16 v7, 0x3c

    const/16 v8, 0x47

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    .line 3
    invoke-direct/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzoa;-><init>(Lcom/google/android/recaptcha/internal/zzoo;Lcom/google/android/recaptcha/internal/zznf;Ljava/lang/String;IIIILcom/google/android/recaptcha/internal/zzahe;Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method
