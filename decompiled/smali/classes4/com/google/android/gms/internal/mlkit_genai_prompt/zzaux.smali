.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaux;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzd(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauw;
    .locals 1

    .line 1
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaus;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaus;-><init>()V

    const-string v0, "genai-prompt"

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaus;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauw;

    const/4 v0, 0x1

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauw;->zzb(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauw;

    .line 3
    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauw;->zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauw;

    return-object p0
.end method


# virtual methods
.method public abstract zza()Ljava/lang/String;
.end method

.method public abstract zzb()Z
.end method

.method public abstract zzc()I
.end method
