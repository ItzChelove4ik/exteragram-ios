.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgx;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;
.source "SourceFile"


# instance fields
.field private zza:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgz;
    .locals 0

    if-eqz p1, :cond_0

    .line 1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgx;->zza:Ljava/lang/String;

    return-object p0

    :cond_0
    const-string p0, "Null modelVersion"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline2;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzha;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgx;->zza:Ljava/lang/String;

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgy;

    invoke-direct {v1, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgy;-><init>(Ljava/lang/String;[B)V

    return-object v1

    :cond_0
    const-string p0, "Missing required properties: modelVersion"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method
