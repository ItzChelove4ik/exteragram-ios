.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdp;


# static fields
.field public static final synthetic $r8$clinit:I = 0x0

.field private static final zze:Ljava/lang/String; = "zzig"


# instance fields
.field private final zzf:Landroid/content/Context;

.field private final zzg:Ljava/util/Optional;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    invoke-interface {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;->zzg()Landroid/content/Context;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzf:Landroid/content/Context;

    .line 2
    invoke-static {}, Ljava/util/Optional;->empty()Ljava/util/Optional;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzg:Ljava/util/Optional;

    return-void
.end method

.method public static zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzij;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;

    move-result-object p0

    invoke-direct {v0, v1, v2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdi;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdy;)V

    return-object v0
.end method

.method public static synthetic zzw(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzho;
    .locals 1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 1
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzw;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzie;

    .line 2
    invoke-interface {p0, v0}, Ljava/util/stream/Stream;->map(Ljava/util/function/Function;)Ljava/util/stream/Stream;

    move-result-object p0

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzj()Ljava/util/stream/Collector;

    move-result-object v0

    invoke-interface {p0, v0}, Ljava/util/stream/Stream;->collect(Ljava/util/stream/Collector;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhb;

    .line 4
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhb;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)V

    return-object v0
.end method

.method public static synthetic zzx(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;)V
    .locals 2

    .line 1
    :try_start_0
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzb()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zze:Ljava/lang/String;

    const-string v1, "Failed to close all file descriptors"

    .line 2
    invoke-static {v0, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-void
.end method


# virtual methods
.method public final bridge synthetic zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;)Ljava/lang/Object;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzg:Ljava/util/Optional;

    invoke-virtual {v0}, Ljava/util/Optional;->isPresent()Z

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzj()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-result-object p0

    invoke-interface {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzan;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;

    move-result-object p0

    return-object p0
.end method

.method public final bridge synthetic zzf(Ljava/lang/Object;Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
    .locals 2

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;

    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    .line 2
    invoke-interface {v0}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v0

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzif;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzif;

    invoke-interface {v0, v1}, Ljava/util/stream/Stream;->anyMatch(Ljava/util/function/Predicate;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdq;->zzd()I

    move-result v0

    const/16 v1, 0xc

    if-eq v0, v1, :cond_0

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zze:Ljava/lang/String;

    const-string p1, "Audio input is only supported for LEGION features"

    .line 3
    invoke-static {p0, p1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p0, 0x3

    .line 4
    invoke-interface {p3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;->zzb(I)V

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;

    .line 5
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;-><init>()V

    return-object p0

    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzf:Landroid/content/Context;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 6
    invoke-virtual {p2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzy(Landroid/content/Context;Ljava/util/concurrent/Executor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;

    move-result-object p2

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;

    .line 7
    invoke-direct {v0, p0, p3, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzid;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgw;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;)V

    invoke-virtual {p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbh;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p0

    return-object p0
.end method

.method public final bridge synthetic zzg(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;->zzd()I

    move-result p0

    if-gtz p0, :cond_0

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zze:Ljava/lang/String;

    const-string p1, "Ignoring prepareInferenceEngine because service is older than V2"

    .line 3
    invoke-static {p0, p1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p0, 0x3

    .line 4
    invoke-interface {p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;->zzc(I)V

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;

    .line 5
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;-><init>()V

    return-object p0

    .line 6
    :cond_0
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p0

    return-object p0
.end method

.method public final bridge synthetic zzh(Ljava/lang/Object;ZLjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;
    .locals 1

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;

    check-cast p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;

    .line 2
    invoke-virtual {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;->zzd()I

    move-result p2

    const/4 v0, 0x2

    if-ge p2, v0, :cond_0

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zze:Ljava/lang/String;

    const-string p1, "Ignoring getTokenInfo because service is older than V3"

    .line 3
    invoke-static {p0, p1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    const/16 p0, 0x10

    .line 4
    invoke-interface {p4, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbp;->zzc(I)V

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;

    .line 5
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzgv;-><init>()V

    return-object p0

    :cond_0
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzig;->zzf:Landroid/content/Context;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzpc;

    .line 6
    invoke-virtual {p1, p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzy(Landroid/content/Context;Ljava/util/concurrent/Executor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;

    move-result-object p0

    const/4 p1, 0x1

    .line 7
    invoke-virtual {p3, p0, p1, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbi;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzbp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzav;

    move-result-object p0

    return-object p0
.end method
