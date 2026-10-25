.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoj;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

.field private final synthetic zzb:Lcom/google/mlkit/genai/common/DownloadCallback;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Lcom/google/mlkit/genai/common/DownloadCallback;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;->zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;
    .locals 1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaud;->zzb:Lcom/google/mlkit/genai/common/DownloadCallback;

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-virtual {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzr(Lcom/google/mlkit/genai/common/DownloadCallback;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    return-object p0
.end method
