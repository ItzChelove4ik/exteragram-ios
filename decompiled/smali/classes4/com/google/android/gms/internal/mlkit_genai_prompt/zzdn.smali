.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdn;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzh(Landroid/content/Context;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;-><init>()V

    .line 2
    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdr;->zzk(Landroid/content/Context;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdl;

    .line 3
    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzb(Ljava/util/concurrent/Executor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    const/4 p0, 0x1

    .line 4
    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzc(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    sget-object p0, Lj$/time/Duration;->ZERO:Lj$/time/Duration;

    .line 5
    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzd(Lj$/time/Duration;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    .line 6
    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;->zzf(Ljava/util/Optional;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdk;

    return-object v0
.end method


# virtual methods
.method public abstract zza()Landroid/content/Context;
.end method

.method public abstract zzb()Ljava/util/concurrent/ExecutorService;
.end method

.method public abstract zzc()Ljava/util/concurrent/Executor;
.end method

.method public abstract zzd()Z
.end method

.method public abstract zze()Lj$/time/Duration;
.end method

.method public abstract zzf()Ljava/util/concurrent/ScheduledExecutorService;
.end method

.method public abstract zzg()Ljava/util/Optional;
.end method
