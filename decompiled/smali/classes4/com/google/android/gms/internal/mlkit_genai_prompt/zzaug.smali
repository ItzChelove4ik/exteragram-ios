.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaua;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

.field private final synthetic zzc:Ljava/lang/Object;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/lang/Object;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;->zzc:Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public final synthetic zza(Lcom/google/mlkit/genai/common/GenAiException;)V
    .locals 1

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaug;->zzc:Ljava/lang/Object;

    invoke-virtual {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;->zzu(Ljava/lang/Object;Lcom/google/mlkit/genai/common/GenAiException;)V

    return-void
.end method
