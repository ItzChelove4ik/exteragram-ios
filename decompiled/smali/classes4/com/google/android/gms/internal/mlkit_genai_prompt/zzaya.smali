.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

.field private final zzc:Z

.field private final zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;


# direct methods
.method private constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzc:Z

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    return-void
.end method

.method public static zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;)V

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/Object;
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    instance-of v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    if-eqz v0, :cond_0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzG()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    return-object p0

    .line 2
    :cond_0
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;->zzS()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxv;

    move-result-object p0

    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxv;->zzH()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 1

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 2
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p2, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 3
    invoke-virtual {v0, p2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzc:Z

    if-nez p0, :cond_1

    const/4 p0, 0x1

    return p0

    .line 4
    :cond_1
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzc(Ljava/lang/Object;)I
    .locals 1

    .line 1
    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 2
    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzc:Z

    if-nez p0, :cond_0

    return v0

    .line 3
    :cond_0
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzd(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    invoke-static {v0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-boolean v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzc:Z

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;

    .line 2
    invoke-static {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method public final zzg(Ljava/lang/Object;[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)V
    .locals 0

    .line 1
    move-object p0, p1

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object p3

    if-eq p2, p3, :cond_0

    goto :goto_0

    .line 4
    :cond_0
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object p2

    .line 2
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 3
    :goto_0
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    .line 4
    throw p0
.end method

.method public final zzh(Ljava/lang/Object;)V
    .locals 0

    .line 1
    move-object p0, p1

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzd()V

    .line 3
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public final zzi(Ljava/lang/Object;)Z
    .locals 0

    .line 1
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0
.end method
