.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;

.field private final zzb:Ljava/util/concurrent/Callable;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;Ljava/util/concurrent/Callable;)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;-><init>()V

    .line 3
    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 2
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zzb:Ljava/util/concurrent/Callable;

    return-void
.end method


# virtual methods
.method public final zza()Z
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->isDone()Z

    move-result p0

    return p0
.end method

.method public final zzb()Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zzb:Ljava/util/concurrent/Callable;

    invoke-interface {p0}, Ljava/util/concurrent/Callable;->call()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzc(Ljava/lang/Object;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->zzh(Ljava/lang/Object;)Z

    return-void
.end method

.method public final zzd(Ljava/lang/Throwable;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->zzi(Ljava/lang/Throwable;)Z

    return-void
.end method

.method public final zzf()Ljava/lang/String;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;->zzb:Ljava/util/concurrent/Callable;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
