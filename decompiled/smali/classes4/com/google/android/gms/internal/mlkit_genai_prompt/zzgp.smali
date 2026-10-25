.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzb:Ljava/util/concurrent/Executor;


# direct methods
.method public constructor <init>(Landroid/os/ParcelFileDescriptor;Landroid/os/ParcelFileDescriptor;Ljava/util/concurrent/Executor;)V
    .locals 0

    .line 1
    invoke-static {p1, p2}, Landroid/util/Pair;->create(Ljava/lang/Object;Ljava/lang/Object;)Landroid/util/Pair;

    move-result-object p1

    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzl(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;->zzb:Ljava/util/concurrent/Executor;

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgo;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-interface {p0, v0}, Ljava/lang/Iterable;->forEach(Ljava/util/function/Consumer;)V

    return-void
.end method

.method public final synthetic zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;Landroid/util/Pair;)V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgn;

    invoke-direct {v0, p0, p2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgn;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;Landroid/util/Pair;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwh;)V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgp;->zzb:Ljava/util/concurrent/Executor;

    invoke-interface {p0, v0}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method
