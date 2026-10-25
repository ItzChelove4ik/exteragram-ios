.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgs;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;


# direct methods
.method public synthetic constructor <init>(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgs;->zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;

    return-void
.end method


# virtual methods
.method public final synthetic run()V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgs;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;

    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;->zza()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Throwable;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgs;->zza:Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;

    invoke-virtual {p0, v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;->setException(Ljava/lang/Throwable;)Z

    return-void
.end method
