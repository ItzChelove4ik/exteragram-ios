.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzor;


# instance fields
.field final synthetic zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

.field final synthetic zzb:Ljava/lang/Runnable;

.field final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Ljava/lang/Runnable;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zzb:Ljava/lang/Runnable;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/Throwable;)V
    .locals 3

    .line 1
    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    invoke-virtual {v0, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    goto :goto_0

    .line 2
    :cond_0
    instance-of v0, p1, Ljava/util/concurrent/CancellationException;

    .line 4
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    if-eqz v0, :cond_1

    const/4 v0, 0x7

    .line 2
    const-string v2, "Operation was cancelled."

    .line 3
    invoke-static {v0, v2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 4
    invoke-virtual {v1, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    const-string v2, "AiCore request could not be completed."

    .line 5
    invoke-static {v0, v2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zze(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object p1

    .line 6
    invoke-virtual {v1, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    .line 1
    :goto_0
    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zzb:Ljava/lang/Runnable;

    .line 7
    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zzd(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final zzb(Ljava/lang/Object;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    invoke-virtual {v0, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->set(Ljava/lang/Object;)Z

    iget-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgq;->zzb:Ljava/lang/Runnable;

    .line 2
    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zzd(Ljava/lang/Runnable;)V

    return-void
.end method
