.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

.field private final synthetic zzb:Ljava/lang/Object;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Ljava/lang/Object;Z)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;->zzb:Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfs;->zzb:Ljava/lang/Object;

    const/4 v2, 0x1

    invoke-direct {v0, v1, p0, v2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Ljava/lang/Object;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;)V

    .line 2
    invoke-static {v0}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    move-result-object p1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfh;

    .line 4
    invoke-virtual {p1, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zza(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
