.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbd;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

.field final synthetic zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

.field final synthetic zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbd;-><init>()V

    return-void
.end method


# virtual methods
.method public final zzb(Ljava/lang/String;J)V
    .locals 2

    .line 1
    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfz;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-direct {p1, v0, v1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfz;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;J)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzr()Ljava/util/concurrent/Executor;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final zzc(Ljava/lang/String;J)V
    .locals 2

    .line 1
    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-direct {p1, v0, v1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;J)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzr()Ljava/util/concurrent/Executor;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final zzd(Ljava/lang/String;ILjava/lang/String;)V
    .locals 7

    .line 1
    sget-object v0, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    filled-new-array {p1, v1, p3}, [Ljava/lang/Object;

    move-result-object p1

    const-string p3, "Feature %s failed with failure status %d and error %s."

    .line 2
    invoke-static {v0, p3, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const/4 p3, 0x1

    if-ne p2, p3, :cond_0

    const/16 p3, 0x1f5

    goto :goto_0

    :cond_0
    const/4 p3, 0x0

    :goto_0
    const/4 v0, 0x0

    .line 3
    invoke-static {p3, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;->zza(ILjava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;

    move-result-object v6

    iget-object v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfx;

    move-object v2, p0

    move v5, p2

    invoke-direct/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfx;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzdo;)V

    iget-object p0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzr()Ljava/util/concurrent/Executor;

    move-result-object p0

    .line 4
    invoke-interface {p0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    iget-object p0, v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    .line 5
    invoke-virtual {p0, v6}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    return-void
.end method

.method public final zze(Ljava/lang/String;)V
    .locals 2

    .line 1
    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfy;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-direct {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfy;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;->zzr()Ljava/util/concurrent/Executor;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzga;->zzc:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    const/4 p1, 0x0

    .line 2
    invoke-virtual {p0, p1}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->set(Ljava/lang/Object;)Z

    return-void
.end method
