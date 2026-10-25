.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

.field private final synthetic zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

.field private final synthetic zzc:J


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;J)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iput-wide p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;->zzc:J

    return-void
.end method


# virtual methods
.method public final synthetic run()V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    iget-wide v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfw;->zzc:J

    invoke-interface {v0, v1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;J)V

    return-void
.end method
