.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzct;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdv;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzct;->zza()I

    move-result p0

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdv;-><init>(I)V

    return-object v0
.end method


# virtual methods
.method public abstract zza()I
.end method
