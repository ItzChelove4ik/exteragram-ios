.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfo;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Ljava/lang/Integer;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
