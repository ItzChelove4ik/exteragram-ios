.class public final Lcom/google/android/recaptcha/internal/zzhk;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzos;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzos;->zza()Lcom/google/android/recaptcha/internal/zzos;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzhk;->zza:Lcom/google/android/recaptcha/internal/zzos;

    return-void
.end method


# virtual methods
.method public final zza(Ljava/util/concurrent/TimeUnit;)J
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzhk;->zza:Lcom/google/android/recaptcha/internal/zzos;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzos;->zzc(Ljava/util/concurrent/TimeUnit;)J

    move-result-wide p0

    return-wide p0
.end method

.method public final zzb()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzhk;->zza:Lcom/google/android/recaptcha/internal/zzos;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzos;->zzb()Lcom/google/android/recaptcha/internal/zzos;

    return-void
.end method
