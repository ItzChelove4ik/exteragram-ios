.class public final Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/mlkit/genai/prompt/GenerateContentRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0010\u0007\n\u0002\u0008\u0006\n\u0002\u0010\u0008\n\u0002\u0008\u000f\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u000e\n\u0002\u0008\u0006\n\u0002\u0010\u000b\n\u0002\u0008\u0007\u0018\u00002\u00020\u0001B\u0017\u0008\u0016\u0012\u000c\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\u0008\u0005\u0010\u0006B\u0011\u0008\u0016\u0012\u0006\u0010\u0008\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0005\u0010\tB\u0019\u0008\u0016\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0008\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0005\u0010\u000cB\u0019\u0008\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0008\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0005\u0010\u000fB!\u0008\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0008\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0005\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\u0008\u0012\u0010\u0013R$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008\u000e\u0010\u0014\u001a\u0004\u0008\u0015\u0010\u0016\"\u0004\u0008\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008\u001a\u0010\u001b\u001a\u0004\u0008\u001c\u0010\u001d\"\u0004\u0008\u001e\u0010\u001fR$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008!\u0010\"\u001a\u0004\u0008#\u0010$\"\u0004\u0008%\u0010&R$\u0010\'\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008\'\u0010\"\u001a\u0004\u0008(\u0010$\"\u0004\u0008)\u0010&R$\u0010*\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008*\u0010\"\u001a\u0004\u0008+\u0010$\"\u0004\u0008,\u0010&R$\u0010-\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008-\u0010\"\u001a\u0004\u0008.\u0010$\"\u0004\u0008/\u0010&R$\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u00081\u00102\u001a\u0004\u00083\u00104\"\u0004\u00085\u00106R$\u00108\u001a\u0004\u0018\u0001078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u00088\u00109\u001a\u0004\u0008:\u0010;\"\u0004\u0008<\u0010=R\"\u0010?\u001a\u00020>8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\u0008?\u0010@\u001a\u0004\u0008A\u0010B\"\u0004\u0008C\u0010D\u00a8\u0006E"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;",
        "",
        "",
        "Lcom/google/mlkit/genai/prompt/Content;",
        "contents",
        "<init>",
        "(Ljava/util/List;)V",
        "Lcom/google/mlkit/genai/prompt/TextPart;",
        "text",
        "(Lcom/google/mlkit/genai/prompt/TextPart;)V",
        "Lcom/google/mlkit/genai/prompt/ImagePart;",
        "image",
        "(Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V",
        "Lcom/google/mlkit/genai/prompt/SystemInstruction;",
        "systemInstruction",
        "(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/TextPart;)V",
        "(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V",
        "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
        "build",
        "()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
        "Lcom/google/mlkit/genai/prompt/SystemInstruction;",
        "getSystemInstruction",
        "()Lcom/google/mlkit/genai/prompt/SystemInstruction;",
        "setSystemInstruction",
        "(Lcom/google/mlkit/genai/prompt/SystemInstruction;)V",
        "",
        "temperature",
        "Ljava/lang/Float;",
        "getTemperature",
        "()Ljava/lang/Float;",
        "setTemperature",
        "(Ljava/lang/Float;)V",
        "",
        "seed",
        "Ljava/lang/Integer;",
        "getSeed",
        "()Ljava/lang/Integer;",
        "setSeed",
        "(Ljava/lang/Integer;)V",
        "topK",
        "getTopK",
        "setTopK",
        "candidateCount",
        "getCandidateCount",
        "setCandidateCount",
        "maxOutputTokens",
        "getMaxOutputTokens",
        "setMaxOutputTokens",
        "Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "promptPrefix",
        "Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "getPromptPrefix",
        "()Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "setPromptPrefix",
        "(Lcom/google/mlkit/genai/prompt/PromptPrefix;)V",
        "",
        "cachedContextName",
        "Ljava/lang/String;",
        "getCachedContextName",
        "()Ljava/lang/String;",
        "setCachedContextName",
        "(Ljava/lang/String;)V",
        "",
        "enableThinking",
        "Z",
        "getEnableThinking",
        "()Z",
        "setEnableThinking",
        "(Z)V",
        "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"
    }
    k = 0x1
    mv = {
        0x2,
        0x3,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private final zza:Ljava/util/List;

.field private zzb:Lcom/google/mlkit/genai/prompt/SystemInstruction;

.field private zzc:Ljava/lang/Float;

.field private zzd:Ljava/lang/Integer;

.field private zze:Ljava/lang/Integer;

.field private zzf:Ljava/lang/Integer;

.field private zzg:Ljava/lang/Integer;

.field private zzh:Lcom/google/mlkit/genai/prompt/PromptPrefix;

.field private zzi:Ljava/lang/String;

.field private zzj:Z


# direct methods
.method public constructor <init>(Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V
    .locals 1

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget-object v0, Lcom/google/mlkit/genai/prompt/Content;->Companion:Lcom/google/mlkit/genai/prompt/Content$Companion;

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/Content$Companion;->builder()Lcom/google/mlkit/genai/prompt/Content$Builder;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/google/mlkit/genai/prompt/Content$Builder;->addPart(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;

    invoke-virtual {v0, p2}, Lcom/google/mlkit/genai/prompt/Content$Builder;->addPart(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/Content$Builder;->build()Lcom/google/mlkit/genai/prompt/Content;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zza:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V
    .locals 0

    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzb:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    sget-object p1, Lcom/google/mlkit/genai/prompt/Content;->Companion:Lcom/google/mlkit/genai/prompt/Content$Companion;

    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/Content$Companion;->builder()Lcom/google/mlkit/genai/prompt/Content$Builder;

    move-result-object p1

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/Content$Builder;->addPart(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;

    invoke-virtual {p1, p3}, Lcom/google/mlkit/genai/prompt/Content$Builder;->addPart(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;

    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/Content$Builder;->build()Lcom/google/mlkit/genai/prompt/Content;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zza:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/TextPart;)V
    .locals 0

    .line 4
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzb:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    sget-object p1, Lcom/google/mlkit/genai/prompt/Content;->Companion:Lcom/google/mlkit/genai/prompt/Content$Companion;

    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/Content$Companion;->builder()Lcom/google/mlkit/genai/prompt/Content$Builder;

    move-result-object p1

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/Content$Builder;->addPart(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;

    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/Content$Builder;->build()Lcom/google/mlkit/genai/prompt/Content;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zza:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Lcom/google/mlkit/genai/prompt/TextPart;)V
    .locals 1

    .line 5
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget-object v0, Lcom/google/mlkit/genai/prompt/Content;->Companion:Lcom/google/mlkit/genai/prompt/Content$Companion;

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/Content$Companion;->builder()Lcom/google/mlkit/genai/prompt/Content$Builder;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/google/mlkit/genai/prompt/Content$Builder;->addPart(Lcom/google/mlkit/genai/prompt/Part;)Lcom/google/mlkit/genai/prompt/Content$Builder;

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/Content$Builder;->build()Lcom/google/mlkit/genai/prompt/Content;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zza:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/google/mlkit/genai/prompt/Content;",
            ">;)V"
        }
    .end annotation

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zza:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public final build()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;
    .locals 12

    .line 1
    iget-object v6, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zza:Ljava/util/List;

    move-object v0, v6

    check-cast v0, Ljava/util/Collection;

    const/4 v1, 0x0

    if-eqz v0, :cond_15

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_15

    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzc:Ljava/lang/Float;

    const/4 v2, 0x0

    if-eqz v0, :cond_1

    .line 2
    invoke-virtual {v0}, Ljava/lang/Number;->floatValue()F

    move-result v3

    cmpg-float v2, v3, v2

    if-ltz v2, :cond_0

    const/high16 v2, 0x3f800000    # 1.0f

    cmpl-float v2, v3, v2

    if-gtz v2, :cond_0

    invoke-virtual {v0}, Ljava/lang/Number;->floatValue()F

    move-result v2

    goto :goto_0

    :cond_0
    const-string/jumbo p0, "temperature must be between 0 and 1"

    .line 18
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    .line 2
    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzd:Ljava/lang/Integer;

    const/4 v3, 0x0

    if-eqz v0, :cond_3

    .line 3
    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v4

    if-ltz v4, :cond_2

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    goto :goto_1

    :cond_2
    const-string p0, "seed must be non-negative"

    .line 4
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_3
    move v0, v3

    .line 3
    :goto_1
    iget-object v4, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zze:Ljava/lang/Integer;

    if-eqz v4, :cond_5

    .line 5
    invoke-virtual {v4}, Ljava/lang/Number;->intValue()I

    move-result v5

    if-lez v5, :cond_4

    invoke-virtual {v4}, Ljava/lang/Number;->intValue()I

    move-result v4

    goto :goto_2

    :cond_4
    const-string/jumbo p0, "topK must be positive"

    .line 6
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_5
    const/4 v4, 0x3

    .line 5
    :goto_2
    iget-object v5, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzf:Ljava/lang/Integer;

    const/4 v7, 0x1

    if-eqz v5, :cond_7

    .line 7
    invoke-virtual {v5}, Ljava/lang/Number;->intValue()I

    move-result v8

    if-lez v8, :cond_6

    const/16 v9, 0x8

    if-gt v8, v9, :cond_6

    invoke-virtual {v5}, Ljava/lang/Number;->intValue()I

    move-result v5

    goto :goto_3

    :cond_6
    const-string p0, "candidateCount must be between 1 and 8"

    .line 8
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_7
    move v5, v7

    .line 7
    :goto_3
    iget-object v8, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzg:Ljava/lang/Integer;

    const/16 v9, 0x1000

    if-eqz v8, :cond_9

    .line 9
    invoke-virtual {v8}, Ljava/lang/Number;->intValue()I

    move-result v10

    if-lez v10, :cond_8

    if-gt v10, v9, :cond_8

    invoke-virtual {v8}, Ljava/lang/Number;->intValue()I

    move-result v9

    goto :goto_4

    .line 10
    :cond_8
    invoke-static {v10}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x34

    const-string v0, "maxOutputTokens must be between 1 and 4096, but was "

    .line 11
    invoke-static {p0, v0, v10}, Lcom/google/android/gms/cast/internal/zzar$$ExternalSyntheticBUOutline1;->m(ILjava/lang/Object;I)V

    return-object v1

    :cond_9
    :goto_4
    if-eqz v6, :cond_e

    .line 12
    move-object v8, v6

    check-cast v8, Ljava/lang/Iterable;

    .line 13
    instance-of v10, v8, Ljava/util/Collection;

    if-eqz v10, :cond_a

    move-object v10, v8

    check-cast v10, Ljava/util/Collection;

    invoke-interface {v10}, Ljava/util/Collection;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_a

    goto :goto_6

    .line 14
    :cond_a
    invoke-interface {v8}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v8

    :cond_b
    :goto_5
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v10

    if-eqz v10, :cond_e

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lcom/google/mlkit/genai/prompt/Content;

    .line 12
    invoke-virtual {v10}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v10

    check-cast v10, Ljava/lang/Iterable;

    .line 13
    instance-of v11, v10, Ljava/util/Collection;

    if-eqz v11, :cond_c

    move-object v11, v10

    check-cast v11, Ljava/util/Collection;

    invoke-interface {v11}, Ljava/util/Collection;->isEmpty()Z

    move-result v11

    if-eqz v11, :cond_c

    goto :goto_5

    .line 14
    :cond_c
    invoke-interface {v10}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v10

    :cond_d
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v11

    if-eqz v11, :cond_b

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v11, v11, Lcom/google/mlkit/genai/prompt/ImagePart;

    if-eqz v11, :cond_d

    move v3, v7

    .line 13
    :cond_e
    :goto_6
    iget-object v7, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzh:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    if-eqz v7, :cond_10

    if-nez v3, :cond_f

    goto :goto_7

    .line 14
    :cond_f
    const-string p0, "promptPrefix is not supported for image input"

    .line 17
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_10
    :goto_7
    if-eqz v7, :cond_12

    .line 13
    iget-object v8, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzi:Ljava/lang/String;

    if-nez v8, :cond_11

    goto :goto_8

    :cond_11
    const-string p0, "promptPrefix and cacheContextName cannot be set at the same time"

    .line 15
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :cond_12
    :goto_8
    iget-object v8, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzi:Ljava/lang/String;

    if-eqz v8, :cond_13

    if-nez v3, :cond_14

    :cond_13
    move v1, v2

    move v2, v0

    goto :goto_9

    :cond_14
    const-string p0, "cacheContextName is not supported for image input"

    .line 16
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1

    :goto_9
    new-instance v0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move v3, v4

    move v4, v5

    move v5, v9

    iget-object v9, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzb:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    iget-boolean v10, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzj:Z

    const/4 v11, 0x0

    invoke-direct/range {v0 .. v11}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;-><init>(FIIIILjava/util/List;Lcom/google/mlkit/genai/prompt/PromptPrefix;Ljava/lang/String;Lcom/google/mlkit/genai/prompt/SystemInstruction;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-object v0

    .line 17
    :cond_15
    const-string p0, "contents cannot be empty"

    .line 1
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method public final setCachedContextName(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzi:Ljava/lang/String;

    return-void
.end method

.method public final setCandidateCount(Ljava/lang/Integer;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzf:Ljava/lang/Integer;

    return-void
.end method

.method public final setEnableThinking(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzj:Z

    return-void
.end method

.method public final setMaxOutputTokens(Ljava/lang/Integer;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzg:Ljava/lang/Integer;

    return-void
.end method

.method public final setPromptPrefix(Lcom/google/mlkit/genai/prompt/PromptPrefix;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzh:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    return-void
.end method

.method public final setSeed(Ljava/lang/Integer;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzd:Ljava/lang/Integer;

    return-void
.end method

.method public final setSystemInstruction(Lcom/google/mlkit/genai/prompt/SystemInstruction;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzb:Lcom/google/mlkit/genai/prompt/SystemInstruction;

    return-void
.end method

.method public final setTemperature(Ljava/lang/Float;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zzc:Ljava/lang/Float;

    return-void
.end method

.method public final setTopK(Ljava/lang/Integer;)V
    .locals 0

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->zze:Ljava/lang/Integer;

    return-void
.end method
