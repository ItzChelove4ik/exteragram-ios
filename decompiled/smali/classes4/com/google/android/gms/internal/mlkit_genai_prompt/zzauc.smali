.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauc;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauc;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    check-cast p1, Ljava/lang/Throwable;

    .line 1
    instance-of v0, p1, Lcom/google/mlkit/genai/common/GenAiException;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/google/mlkit/genai/common/GenAiException;

    goto :goto_1

    .line 12
    :cond_0
    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    if-eqz v0, :cond_1

    .line 2
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    .line 3
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzg()I

    move-result v1

    .line 5
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zzh()Lj$/time/Duration;

    move-result-object v2

    invoke-direct {v0, p1, v1, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/Throwable;ILj$/time/Duration;)V

    :goto_0
    move-object p1, v0

    goto :goto_1

    .line 6
    :cond_1
    instance-of v0, p1, Ljava/util/concurrent/CancellationException;

    if-eqz v0, :cond_2

    .line 7
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 8
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlb;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x7

    invoke-direct {v0, v1, p1, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    goto :goto_0

    .line 9
    :cond_2
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    .line 10
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlb;->zza(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-direct {v0, v1, p1, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    goto :goto_0

    .line 1
    :goto_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauc;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;

    .line 11
    invoke-interface {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;->zza(Lcom/google/mlkit/genai/common/GenAiException;)V

    .line 12
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzot;->zzc(Ljava/lang/Throwable;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
