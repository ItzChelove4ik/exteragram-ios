.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    return-void
.end method

.method public static zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;
    .locals 3

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeu;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    move-result-object p0

    invoke-direct {v0, v1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    return-object v0
.end method


# virtual methods
.method public final bridge synthetic zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-result-object p0

    invoke-interface {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;->zzj(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzay;

    move-result-object p0

    return-object p0
.end method

.method public final synthetic zzf(Ljava/lang/Object;Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
    .locals 5

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzay;

    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;

    .line 2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    move-result-object v0

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzo()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v1, :cond_4

    if-eq v1, v2, :cond_3

    const/4 v4, 0x2

    if-eq v1, v4, :cond_2

    const/4 v4, 0x3

    if-eq v1, v4, :cond_1

    const/4 v4, 0x4

    if-ne v1, v4, :cond_0

    const-string v1, "When inference with cached state, there must be positive tokens been processed before"

    .line 3
    invoke-static {v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zza(ZLjava/lang/Object;)V

    const-string v1, "Messages must be non-null for INFERENCE_WITH_BLOCK_ATTENTION if cached state is used"

    .line 4
    invoke-static {v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zza(ZLjava/lang/Object;)V

    const-string v1, "cortana_type_inference_with_block_attention"

    .line 5
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 6
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    goto :goto_0

    .line 13
    :cond_0
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzo()I

    move-result p0

    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzen;->zza(I)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unsupported Cortana type in CortanaRequest: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 34
    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    .line 31
    :cond_1
    const-string v1, "cortana_type_inference_with_prefix_caching"

    .line 7
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    goto :goto_0

    :cond_2
    const-string v1, "cortana_type_inference_with_session_state_caching"

    .line 8
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    goto :goto_0

    :cond_3
    const-string v1, "cortana_type_prefix_caching"

    .line 9
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzl()Ljava/lang/String;

    move-result-object v1

    .line 10
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzk(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    goto :goto_0

    :cond_4
    const-string v1, "cortana_type_block_attention"

    .line 11
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzl()Ljava/lang/String;

    move-result-object v1

    .line 12
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzk(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 13
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 6
    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    new-instance v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;

    .line 14
    invoke-direct {v4, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzel;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;Ljava/util/concurrent/Executor;)V

    .line 15
    invoke-virtual {v0, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 16
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    .line 17
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const/4 v1, 0x0

    .line 18
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zze()I

    move-result v1

    .line 19
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzf()I

    move-result v1

    .line 20
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzd(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;->zzg()I

    move-result v1

    .line 21
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zze(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 22
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzh(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 23
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzi(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 24
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 25
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    .line 26
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 27
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 28
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzo()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;

    .line 29
    invoke-direct {v1, p0, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeq;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzer;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeo;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;)V

    .line 30
    invoke-virtual {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzay;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzax;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p0

    .line 31
    :try_start_0
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzb()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 32
    :catch_0
    invoke-interface {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;->zzd()V

    const/16 p1, 0xe

    .line 33
    invoke-interface {p3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zzb(I)V

    return-object p0
.end method

.method public final synthetic zzg(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzay;

    .line 2
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzay;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p0

    return-object p0
.end method
