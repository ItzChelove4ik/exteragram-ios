.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

.field private final synthetic zzb:Lcom/google/common/util/concurrent/ListenableFuture;

.field private final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;->zzb:Lcom/google/common/util/concurrent/ListenableFuture;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 2

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;->zzb:Lcom/google/common/util/concurrent/ListenableFuture;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgt;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;

    invoke-virtual {v0, v1, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zzc(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
