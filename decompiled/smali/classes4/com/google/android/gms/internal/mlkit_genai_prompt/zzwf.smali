.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;-><init>()V

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    move-result-object v0

    .line 2
    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    const-string v0, "genai-prompt"

    .line 3
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavk;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    return-void
.end method

.method private static final zze(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)I
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method


# virtual methods
.method public final zza()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarh;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarh;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 3
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeW:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    .line 4
    invoke-virtual {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final zzb(II)V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-direct {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;-><init>()V

    .line 2
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {v2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    move-result-object p1

    .line 3
    invoke-virtual {v2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    move-result-object p1

    .line 4
    invoke-virtual {v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;

    move-result-object p1

    .line 5
    invoke-virtual {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 6
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeV:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    .line 7
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final zzc()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzard;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzard;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 3
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object v0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeX:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    .line 4
    invoke-virtual {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final bridge synthetic zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 4

    .line 1
    check-cast p2, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    check-cast p3, Ljava/lang/Boolean;

    invoke-virtual {p3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 2
    new-instance p3, Ljava/util/ArrayList;

    .line 3
    invoke-direct {p3}, Ljava/util/ArrayList;-><init>()V

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;-><init>()V

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    .line 4
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    .line 5
    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zze(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)I

    move-result v1

    int-to-long v1, v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzb(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    .line 6
    invoke-virtual {p2}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    goto :goto_0

    :cond_0
    move v1, v2

    :goto_0
    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;

    invoke-direct {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;-><init>()V

    .line 7
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;

    .line 8
    invoke-virtual {v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zzb(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;

    move-result-object v1

    .line 10
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarb;

    move-result-object v0

    .line 12
    invoke-interface {p3, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-direct {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;-><init>()V

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    .line 13
    invoke-virtual {v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;-><init>()V

    .line 14
    invoke-static {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p3

    invoke-virtual {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzg()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarl;

    move-result-object p0

    .line 15
    invoke-virtual {v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    .line 16
    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;->zze(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzd(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    .line 17
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zze(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    move-result-object p0

    .line 18
    invoke-virtual {v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;

    move-result-object p0

    .line 19
    invoke-virtual {v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 20
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object p0

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeV:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    .line 21
    invoke-virtual {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method
