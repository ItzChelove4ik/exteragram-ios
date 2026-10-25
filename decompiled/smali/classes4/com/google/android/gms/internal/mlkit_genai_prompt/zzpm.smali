.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzol;
.source "SourceFile"

# interfaces
.implements Ljava/util/concurrent/RunnableFuture;


# instance fields
.field private volatile zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;


# direct methods
.method public constructor <init>(Ljava/util/concurrent/Callable;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzol;-><init>()V

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpl;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;Ljava/util/concurrent/Callable;)V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;

    return-void
.end method

.method public static zza(Ljava/lang/Runnable;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;

    invoke-static {p0, p1}, Ljava/util/concurrent/Executors;->callable(Ljava/lang/Runnable;Ljava/lang/Object;)Ljava/util/concurrent/Callable;

    move-result-object p0

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;-><init>(Ljava/util/concurrent/Callable;)V

    return-object v0
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;->run()V

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;

    return-void
.end method

.method public final zzc()V
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->zzg()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;

    if-eqz v0, :cond_0

    .line 2
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;->zze()V

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;

    return-void
.end method

.method public final zzd()Ljava/lang/String;
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpa;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    new-instance v1, Ljava/lang/StringBuilder;

    add-int/lit8 v0, v0, 0x7

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v0, "task=["

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "]"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-super {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc;->zzd()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
