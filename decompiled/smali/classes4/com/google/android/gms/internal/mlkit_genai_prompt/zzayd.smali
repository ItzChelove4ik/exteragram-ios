.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;


# instance fields
.field private final zzb:Ljava/util/concurrent/ConcurrentMap;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zzb:Ljava/util/concurrent/ConcurrentMap;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawt;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawt;

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;

    return-object v0
.end method

.method private zzc(Ljava/lang/Class;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;)",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg<",
            "TT;>;"
        }
    .end annotation

    .line 1
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->$r8$clinit:I

    .line 2
    const-class v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isAssignableFrom(Ljava/lang/Class;)Z

    move-result v1

    if-nez v1, :cond_0

    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavq;->$r8$clinit:I

    .line 3
    :cond_0
    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavq;->$r8$clinit:I

    .line 4
    invoke-virtual {v0, p1}, Ljava/lang/Class;->isAssignableFrom(Ljava/lang/Class;)Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_4

    .line 6
    :try_start_0
    invoke-virtual {p1, v0}, Ljava/lang/Class;->asSubclass(Ljava/lang/Class;)Ljava/lang/Class;

    move-result-object v0

    .line 7
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzM(Ljava/lang/Class;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object v0

    const/4 v1, 0x3

    .line 8
    invoke-virtual {v0, v1, v2, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 9
    move-object v4, v0

    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxu;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 11
    invoke-interface {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxu;->zza()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    move-result-object v0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawo;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;

    move-result-object v1

    .line 14
    invoke-interface {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxu;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    move-result-object v2

    invoke-static {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaya;

    move-result-object v0

    move-object v3, p1

    goto :goto_0

    .line 15
    :cond_1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayc;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayb;

    move-result-object v5

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxo;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxn;

    move-result-object v6

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    move-result-object v7

    .line 12
    invoke-interface {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxu;->zzc()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    const/4 v1, 0x1

    if-eq v0, v1, :cond_2

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawo;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;

    move-result-object v2

    :cond_2
    move-object v8, v2

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxt;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;

    move-result-object v9

    move-object v3, p1

    .line 13
    invoke-static/range {v3 .. v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzj(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxn;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;

    move-result-object v0

    .line 14
    :goto_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zzb:Ljava/util/concurrent/ConcurrentMap;

    .line 15
    invoke-interface {p0, v3, v0}, Ljava/util/concurrent/ConcurrentMap;->putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    if-eqz p0, :cond_3

    return-object p0

    :cond_3
    return-object v0

    :catch_0
    move-exception v0

    move-object v3, p1

    move-object p0, v0

    .line 10
    invoke-virtual {v3}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p1

    const-string v0, "Unable to get message info for "

    invoke-virtual {v0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v2

    :cond_4
    move-object v3, p1

    .line 5
    invoke-virtual {v3}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unsupported message type: "

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2
.end method


# virtual methods
.method public final zzb(Ljava/lang/Class;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zzb:Ljava/util/concurrent/ConcurrentMap;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    if-nez v0, :cond_0

    .line 2
    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zzc(Ljava/lang/Class;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object p0

    return-object p0

    .line 3
    :cond_0
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    return-object v0
.end method
