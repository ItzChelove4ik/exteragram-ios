.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzba;
.source "SourceFile"


# instance fields
.field final synthetic zza:Ljava/util/concurrent/Executor;

.field final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;Ljava/util/concurrent/Executor;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;->zza:Ljava/util/concurrent/Executor;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzba;-><init>()V

    return-void
.end method


# virtual methods
.method public final zzd(Landroid/os/ParcelFileDescriptor;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzb()Landroid/os/ParcelFileDescriptor;

    move-result-object v2

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;->zza:Ljava/util/concurrent/Executor;

    invoke-direct {v1, p1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;-><init>(Landroid/os/ParcelFileDescriptor;Landroid/os/ParcelFileDescriptor;Ljava/util/concurrent/Executor;)V

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;

    move-result-object p0

    .line 2
    invoke-virtual {v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)V

    return-void
.end method
