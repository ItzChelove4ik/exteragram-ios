.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

.field private final zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;


# direct methods
.method public constructor <init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;

    invoke-direct {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;-><init>()V

    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;

    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarn;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarm;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    move-result-object p1

    .line 2
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    const-string p1, "genai-prompt"

    .line 3
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavk;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    return-void
.end method

.method private final zzl(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)I
    .locals 6

    .line 1
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    goto :goto_0

    :cond_0
    move v0, v1

    .line 2
    :goto_0
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    move v2, v1

    :cond_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/mlkit/genai/prompt/Content;

    .line 3
    invoke-virtual {v3}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_2
    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v5, v4, Lcom/google/mlkit/genai/prompt/TextPart;

    if-eqz v5, :cond_2

    .line 4
    check-cast v4, Lcom/google/mlkit/genai/prompt/TextPart;

    invoke-virtual {v4}, Lcom/google/mlkit/genai/prompt/TextPart;->getTextString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/2addr v2, v4

    goto :goto_1

    :cond_3
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    .line 5
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_5

    const/4 p1, 0x1

    if-ne p0, p1, :cond_4

    goto :goto_2

    :cond_4
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return v1

    :cond_5
    :goto_2
    add-int/2addr v0, v2

    return v0
.end method

.method private static final zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;
    .locals 4

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    const/4 v0, 0x0

    const/4 v1, 0x3

    const/4 v2, 0x2

    const/4 v3, 0x1

    if-eqz p0, :cond_7

    if-ne p0, v3, :cond_6

    .line 3
    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_5

    if-eq p0, v3, :cond_3

    if-eq p0, v2, :cond_1

    if-ne p0, v1, :cond_0

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfa:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_0
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-object v0

    :cond_1
    if-eqz p2, :cond_2

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfe:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_2
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_3
    if-eqz p2, :cond_4

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    .line 2
    :cond_4
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeY:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    .line 3
    :cond_5
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeZ:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    .line 1
    :cond_6
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-object v0

    .line 2
    :cond_7
    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_d

    if-eq p0, v3, :cond_b

    if-eq p0, v2, :cond_9

    if-ne p0, v1, :cond_8

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeU:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_8
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-object v0

    :cond_9
    if-eqz p2, :cond_a

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfe:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_a
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_b
    if-eqz p2, :cond_c

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzfc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_c
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeS:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0

    :cond_d
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;->zzeT:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    return-object p0
.end method


# virtual methods
.method public final zza()V
    .locals 4

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarg;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarh;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarh;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 3
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    .line 4
    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    const/4 v3, 0x0

    .line 5
    invoke-static {v1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    move-result-object v1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    .line 6
    invoke-virtual {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final zzb(II)V
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-virtual {p0, v0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;II)V

    return-void
.end method

.method public final zzc()V
    .locals 4

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarc;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzard;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzard;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 3
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    .line 4
    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    const/4 v3, 0x0

    .line 5
    invoke-static {v1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    move-result-object v1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    .line 6
    invoke-virtual {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final bridge synthetic zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 10

    .line 1
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    check-cast p3, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;

    .line 3
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v0

    new-instance v1, Ljava/util/ArrayList;

    .line 4
    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 5
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/mlkit/genai/prompt/Content;

    .line 6
    invoke-virtual {v3}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_1
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v5, v4, Lcom/google/mlkit/genai/prompt/ImagePart;

    if-eqz v5, :cond_1

    .line 7
    check-cast v4, Lcom/google/mlkit/genai/prompt/ImagePart;

    invoke-virtual {v4}, Lcom/google/mlkit/genai/prompt/ImagePart;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v5

    invoke-virtual {v5}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    .line 8
    invoke-virtual {v4}, Lcom/google/mlkit/genai/prompt/ImagePart;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v4

    invoke-virtual {v4}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    new-instance v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    invoke-direct {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;-><init>()V

    sget-object v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    .line 9
    invoke-virtual {v6, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    mul-int/2addr v5, v4

    int-to-long v4, v5

    .line 10
    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v6, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzb(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarb;

    move-result-object v4

    .line 12
    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_2
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    invoke-direct {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;-><init>()V

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;

    .line 13
    invoke-virtual {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzara;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    .line 14
    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzl(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)I

    move-result v3

    int-to-long v3, v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzb(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    .line 15
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object v3

    const/4 v4, 0x0

    if-eqz v3, :cond_3

    invoke-virtual {v3}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    goto :goto_1

    :cond_3
    move v3, v4

    .line 16
    :goto_1
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v5

    move v6, v4

    :cond_4
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_6

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/google/mlkit/genai/prompt/Content;

    .line 17
    invoke-virtual {v7}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v7

    invoke-interface {v7}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v7

    :cond_5
    :goto_2
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v8

    if-eqz v8, :cond_4

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v9, v8, Lcom/google/mlkit/genai/prompt/TextPart;

    if-eqz v9, :cond_5

    .line 18
    check-cast v8, Lcom/google/mlkit/genai/prompt/TextPart;

    invoke-virtual {v8}, Lcom/google/mlkit/genai/prompt/TextPart;->getTextString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/String;->length()I

    move-result v8

    add-int/2addr v6, v8

    goto :goto_2

    :cond_6
    new-instance v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;

    invoke-direct {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;-><init>()V

    iget-object v7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    .line 19
    sget-object v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    sget-object v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    invoke-virtual {v7}, Ljava/lang/Enum;->ordinal()I

    move-result v8

    const/4 v9, 0x1

    if-eqz v8, :cond_8

    if-ne v8, v9, :cond_7

    goto :goto_3

    :cond_7
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-void

    :cond_8
    :goto_3
    add-int/2addr v6, v3

    .line 20
    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-virtual {v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;

    .line 21
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zzb(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;

    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzasg;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;

    move-result-object v3

    .line 23
    invoke-virtual {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzash;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaqz;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarb;

    move-result-object v2

    .line 25
    invoke-interface {v1, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-direct {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;-><init>()V

    iget-object v6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    .line 26
    invoke-virtual {v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    new-instance v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    invoke-direct {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;-><init>()V

    .line 27
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getTemperature()F

    move-result v8

    invoke-static {v8}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v8

    invoke-virtual {v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zza(Ljava/lang/Float;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    .line 28
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getSeed()I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzb(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    .line 29
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getTopK()I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzc(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    .line 30
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getCandidateCount()I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzd(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    .line 31
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getMaxOutputTokens()I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zze(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    .line 32
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v6, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;

    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzark;->zzg()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarl;

    move-result-object v1

    .line 33
    invoke-virtual {v5, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    .line 34
    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzl(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzd(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {p3}, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;->getCandidates()Ljava/util/List;

    move-result-object p0

    .line 35
    check-cast p0, Ljava/lang/Iterable;

    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p3

    if-nez p3, :cond_9

    const/4 p0, 0x0

    goto :goto_7

    :cond_9
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/google/mlkit/genai/prompt/Candidate;

    invoke-virtual {p3}, Lcom/google/mlkit/genai/prompt/Candidate;->getText()Ljava/lang/String;

    move-result-object p3

    if-eqz p3, :cond_a

    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result p3

    goto :goto_4

    :cond_a
    move p3, v4

    :goto_4
    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    :cond_b
    :goto_5
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_d

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/mlkit/genai/prompt/Candidate;

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/Candidate;->getText()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_c

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    goto :goto_6

    :cond_c
    move v0, v4

    :goto_6
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p3, v0}, Ljava/lang/Comparable;->compareTo(Ljava/lang/Object;)I

    move-result v1

    if-gez v1, :cond_b

    move-object p3, v0

    goto :goto_5

    :cond_d
    move-object p0, p3

    :goto_7
    if-eqz p0, :cond_e

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    goto :goto_8

    :cond_e
    move p0, v4

    :goto_8
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zze(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    move-result-object p0

    .line 36
    invoke-virtual {v5, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;

    move-result-object p0

    .line 37
    invoke-virtual {v3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 38
    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object p0

    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    .line 39
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb()Lkotlin/reflect/KClass;

    move-result-object p2

    if-eqz p2, :cond_f

    move v4, v9

    .line 40
    :cond_f
    invoke-static {v7, p1, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    move-result-object p1

    .line 41
    invoke-virtual {v2, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;II)V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-direct {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;-><init>()V

    .line 2
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {v2, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-static {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    move-result-object p2

    .line 3
    invoke-virtual {v2, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    move-result-object p2

    .line 4
    invoke-virtual {v1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzari;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;

    move-result-object p2

    .line 5
    invoke-virtual {v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarj;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 6
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object p2

    .line 7
    sget-object p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb()Lkotlin/reflect/KClass;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    if-eqz p1, :cond_1

    const/4 p1, 0x1

    goto :goto_1

    :cond_1
    const/4 p1, 0x0

    .line 8
    :goto_1
    invoke-static {v0, p3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    move-result-object p1

    .line 9
    invoke-virtual {p0, p2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;II)V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-direct {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;-><init>()V

    .line 2
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {v2, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zza(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-static {p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;

    move-result-object p2

    .line 3
    invoke-virtual {v2, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzant;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    move-result-object p2

    .line 4
    invoke-virtual {v1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarf;

    move-result-object p2

    .line 5
    invoke-virtual {v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarf;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 6
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object p2

    .line 7
    sget-object p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb()Lkotlin/reflect/KClass;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    if-eqz p1, :cond_1

    const/4 p1, 0x1

    goto :goto_1

    :cond_1
    const/4 p1, 0x0

    .line 8
    :goto_1
    invoke-static {v0, p3, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    move-result-object p1

    .line 9
    invoke-virtual {p0, p2, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final zzg(II)V
    .locals 1

    const/4 v0, 0x0

    .line 1
    invoke-virtual {p0, v0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;II)V

    return-void
.end method

.method public final bridge synthetic zzh(Ljava/lang/Object;II)V
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;II)V

    return-void
.end method

.method public final bridge synthetic zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;Ljava/lang/Object;ILj$/time/Duration;)V
    .locals 3

    .line 1
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    if-eqz p4, :cond_0

    .line 3
    invoke-virtual {p4}, Lj$/time/Duration;->toMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p4

    invoke-virtual {p1, p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzf(Ljava/lang/Long;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    :cond_0
    iget-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;-><init>()V

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;-><init>()V

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;

    .line 4
    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaro;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    .line 5
    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    invoke-virtual {v1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zzc(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    .line 6
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object p3

    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzl(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)I

    move-result p3

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    invoke-virtual {p1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzd(Ljava/lang/Integer;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzars;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;

    move-result-object p1

    .line 7
    invoke-virtual {v1, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzart;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzare;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarf;

    move-result-object p1

    .line 8
    invoke-virtual {v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzarf;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;

    .line 9
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavd;->zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanv;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    .line 10
    sget-object p3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;

    .line 11
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb()Lkotlin/reflect/KClass;

    move-result-object p2

    if-eqz p2, :cond_1

    const/4 p2, 0x1

    goto :goto_0

    :cond_1
    const/4 p2, 0x0

    .line 12
    :goto_0
    invoke-static {p0, p3, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyi;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;

    move-result-object p0

    .line 13
    invoke-virtual {p4, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavc;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzauu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzanu;)V

    return-void
.end method

.method public final bridge synthetic zzj(Ljava/lang/Object;II)V
    .locals 0

    .line 1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;II)V

    return-void
.end method
