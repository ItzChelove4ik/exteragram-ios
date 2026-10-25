.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

.field private final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 3

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;

    .line 1
    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzge;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    invoke-direct {v1, v0, p1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgg;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    .line 2
    invoke-static {v1}, Landroidx/concurrent/futures/CallbackToFutureAdapter;->getFuture(Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    .line 3
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdh;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;

    move-result-object p1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgh;

    .line 4
    invoke-virtual {p1, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgu;->zza(Lcom/google/common/util/concurrent/ListenableFuture;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlc;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
