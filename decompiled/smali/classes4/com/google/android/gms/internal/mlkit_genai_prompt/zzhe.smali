.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;
.source "SourceFile"


# instance fields
.field private zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private zzb:Landroid/os/ParcelFileDescriptor;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;
    .locals 0

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzb(Landroid/os/ParcelFileDescriptor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzht;
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;->zzb:Landroid/os/ParcelFileDescriptor;

    return-object p0
.end method

.method public final zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;
    .locals 3

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhf;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhe;->zzb:Landroid/os/ParcelFileDescriptor;

    invoke-direct {v2, v0, p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhf;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;Landroid/os/ParcelFileDescriptor;[B)V

    return-object v2

    :cond_0
    const-string p0, "Missing required properties: promptPrefix"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method
