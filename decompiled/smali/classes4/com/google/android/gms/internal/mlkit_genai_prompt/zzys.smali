.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final zzb(Lkotlin/reflect/KClass;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;

    .line 2
    invoke-virtual {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;->zza(Lkotlin/reflect/KClass;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzl()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object p0

    return-object p0
.end method

.method public static final zzc(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;)Ljava/lang/String;
    .locals 2

    .line 1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzv()Lorg/json/JSONObject;

    move-result-object p1

    invoke-virtual {p1}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p1

    .line 2
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    return-object p0

    .line 3
    :cond_0
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    add-int/lit16 v0, v0, 0x92

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/2addr v0, v1

    new-instance v1, Ljava/lang/StringBuilder;

    add-int/lit8 v0, v0, 0x1

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\nContext:\nDo NOT generate unsafe content.\nIf contents are safe, you MUST output ONLY in the following JSON schema without any spaces or newlines:\n"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\n"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;)Lcom/google/mlkit/genai/prompt/GenerateContentResponse;
    .locals 7

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyr;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyr;-><init>()V

    .line 2
    invoke-static {v0, v1}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    new-instance v1, Ljava/util/HashSet;

    .line 3
    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    new-instance v2, Ljava/util/ArrayList;

    .line 4
    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 5
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    .line 6
    move-object v4, v3

    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    .line 7
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zza()Ljava/lang/String;

    move-result-object v4

    .line 8
    invoke-virtual {v1, v4}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 9
    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 10
    :cond_1
    new-instance v0, Ljava/util/ArrayList;

    const/16 v1, 0xa

    .line 11
    invoke-static {v2, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v3

    invoke-direct {v0, v3}, Ljava/util/ArrayList;-><init>(I)V

    .line 12
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x0

    :goto_1
    if-ge v4, v3, :cond_2

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    .line 13
    check-cast v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    .line 14
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zza()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzc()I

    move-result v5

    invoke-static {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zzf(I)I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-static {v6, v5}, Lcom/google/mlkit/genai/prompt/Candidate$Companion;->zza(Ljava/lang/String;Ljava/lang/Integer;)Lcom/google/mlkit/genai/prompt/Candidate;

    move-result-object v5

    .line 13
    invoke-interface {v0, v5}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 15
    :cond_2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzic;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    if-eqz p0, :cond_3

    new-instance v2, Ljava/util/ArrayList;

    .line 16
    invoke-static {p0, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {v2, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 17
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_2
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    .line 18
    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;

    .line 19
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zza()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhv;->zzc()I

    move-result v1

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zzf(I)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-static {v3, v1}, Lcom/google/mlkit/genai/prompt/Candidate$Companion;->zza(Ljava/lang/String;Ljava/lang/Integer;)Lcom/google/mlkit/genai/prompt/Candidate;

    move-result-object v1

    .line 18
    invoke-interface {v2, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 21
    :cond_3
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v2

    .line 22
    :cond_4
    invoke-static {v0, v2}, Lcom/google/mlkit/genai/prompt/GenerateContentResponse$Companion;->zzb(Ljava/util/List;Ljava/util/List;)Lcom/google/mlkit/genai/prompt/GenerateContentResponse;

    move-result-object p0

    return-object p0
.end method

.method public static final zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;Lcom/google/mlkit/genai/common/StreamingCallback;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;
    .locals 16

    move-object/from16 v0, p1

    move-object/from16 v1, p2

    .line 1
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zza()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v2

    new-instance v3, Ljava/util/ArrayList;

    .line 2
    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 3
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;

    move-result-object v4

    const-string v5, ""

    if-eqz v4, :cond_0

    invoke-virtual {v4}, Lcom/google/mlkit/genai/prompt/PromptPrefix;->getTextString()Ljava/lang/String;

    move-result-object v4

    if-nez v4, :cond_1

    :cond_0
    move-object v4, v5

    .line 4
    :cond_1
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzb()Lkotlin/reflect/KClass;

    move-result-object v6

    if-eqz v6, :cond_2

    invoke-static {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zzb(Lkotlin/reflect/KClass;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v6

    goto :goto_0

    :cond_2
    const/4 v6, 0x0

    .line 5
    :goto_0
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getSystemInstruction()Lcom/google/mlkit/genai/prompt/SystemInstruction;

    move-result-object v8

    if-eqz v8, :cond_3

    invoke-virtual {v8}, Lcom/google/mlkit/genai/prompt/SystemInstruction;->getTextString()Ljava/lang/String;

    move-result-object v8

    if-nez v8, :cond_4

    :cond_3
    move-object v8, v5

    .line 6
    :cond_4
    invoke-interface {v8}, Ljava/lang/CharSequence;->length()I

    move-result v9

    const/4 v10, 0x2

    if-lez v9, :cond_5

    invoke-static {v10, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzj(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    move-result-object v9

    .line 7
    invoke-interface {v3, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 8
    :cond_5
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object v9

    invoke-interface {v9}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v9

    const/4 v11, 0x0

    move v12, v11

    :cond_6
    invoke-interface {v9}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_8

    invoke-interface {v9}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Lcom/google/mlkit/genai/prompt/Content;

    .line 9
    invoke-virtual {v13}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v13

    invoke-interface {v13}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v13

    :cond_7
    :goto_1
    invoke-interface {v13}, Ljava/util/Iterator;->hasNext()Z

    move-result v14

    if-eqz v14, :cond_6

    invoke-interface {v13}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v14

    check-cast v14, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v15, v14, Lcom/google/mlkit/genai/prompt/TextPart;

    if-eqz v15, :cond_7

    .line 10
    check-cast v14, Lcom/google/mlkit/genai/prompt/TextPart;

    invoke-virtual {v14}, Lcom/google/mlkit/genai/prompt/TextPart;->getTextString()Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v14}, Ljava/lang/String;->length()I

    move-result v14

    add-int/2addr v12, v14

    goto :goto_1

    .line 11
    :cond_8
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object v9

    check-cast v9, Ljava/lang/Iterable;

    .line 12
    instance-of v13, v9, Ljava/util/Collection;

    if-eqz v13, :cond_a

    move-object v13, v9

    check-cast v13, Ljava/util/Collection;

    invoke-interface {v13}, Ljava/util/Collection;->isEmpty()Z

    move-result v13

    if-eqz v13, :cond_a

    :cond_9
    move v9, v11

    goto :goto_3

    .line 13
    :cond_a
    invoke-interface {v9}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v9

    :cond_b
    :goto_2
    invoke-interface {v9}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_9

    invoke-interface {v9}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Lcom/google/mlkit/genai/prompt/Content;

    .line 11
    invoke-virtual {v13}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v13

    check-cast v13, Ljava/lang/Iterable;

    .line 12
    instance-of v15, v13, Ljava/util/Collection;

    if-eqz v15, :cond_c

    move-object v15, v13

    check-cast v15, Ljava/util/Collection;

    invoke-interface {v15}, Ljava/util/Collection;->isEmpty()Z

    move-result v15

    if-eqz v15, :cond_c

    goto :goto_2

    .line 13
    :cond_c
    invoke-interface {v13}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v13

    :cond_d
    invoke-interface {v13}, Ljava/util/Iterator;->hasNext()Z

    move-result v15

    if-eqz v15, :cond_b

    invoke-interface {v13}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v15

    check-cast v15, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v15, v15, Lcom/google/mlkit/genai/prompt/TextPart;

    if-eqz v15, :cond_d

    const/4 v9, 0x1

    .line 14
    :goto_3
    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v13

    add-int/2addr v13, v12

    .line 15
    invoke-virtual {v8}, Ljava/lang/String;->length()I

    move-result v8

    add-int/2addr v13, v8

    const/16 v8, 0x7d00

    if-gt v13, v8, :cond_21

    .line 17
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object v8

    invoke-interface {v8}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v8

    const/4 v12, 0x0

    :cond_e
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_10

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Lcom/google/mlkit/genai/prompt/Content;

    .line 18
    invoke-virtual {v13}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v13

    invoke-interface {v13}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v13

    :cond_f
    :goto_4
    invoke-interface {v13}, Ljava/util/Iterator;->hasNext()Z

    move-result v15

    if-eqz v15, :cond_e

    invoke-interface {v13}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v15

    check-cast v15, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v7, v15, Lcom/google/mlkit/genai/prompt/TextPart;

    if-eqz v7, :cond_f

    .line 19
    move-object v12, v15

    check-cast v12, Lcom/google/mlkit/genai/prompt/TextPart;

    goto :goto_4

    :cond_10
    if-nez v9, :cond_11

    .line 20
    invoke-interface {v4}, Ljava/lang/CharSequence;->length()I

    move-result v7

    if-lez v7, :cond_11

    if-nez v1, :cond_11

    invoke-static {v11, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzj(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    move-result-object v7

    .line 21
    invoke-interface {v3, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 22
    :cond_11
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getContents()Ljava/util/List;

    move-result-object v7

    invoke-interface {v7}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v7

    const/4 v8, 0x1

    :cond_12
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_1a

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Lcom/google/mlkit/genai/prompt/Content;

    .line 23
    invoke-virtual {v9}, Lcom/google/mlkit/genai/prompt/Content;->getParts()Ljava/util/List;

    move-result-object v9

    invoke-interface {v9}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v9

    :goto_5
    invoke-interface {v9}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_12

    invoke-interface {v9}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v13

    check-cast v13, Lcom/google/mlkit/genai/prompt/Part;

    instance-of v15, v13, Lcom/google/mlkit/genai/prompt/TextPart;

    if-eqz v15, :cond_15

    .line 24
    move-object v15, v13

    check-cast v15, Lcom/google/mlkit/genai/prompt/TextPart;

    invoke-virtual {v15}, Lcom/google/mlkit/genai/prompt/TextPart;->getTextString()Ljava/lang/String;

    move-result-object v15

    if-eqz v8, :cond_13

    if-nez v1, :cond_13

    .line 25
    invoke-static {v15}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v4, v8}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15

    move v8, v11

    :cond_13
    if-ne v13, v12, :cond_14

    if-eqz v6, :cond_14

    .line 26
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc()Z

    move-result v13

    if-eqz v13, :cond_14

    .line 27
    invoke-static {v15, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zzc(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;)Ljava/lang/String;

    move-result-object v15

    :cond_14
    invoke-static {v11, v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzj(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    move-result-object v13

    .line 28
    invoke-interface {v3, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_5

    :cond_15
    instance-of v15, v13, Lcom/google/mlkit/genai/prompt/ImagePart;

    if-eqz v15, :cond_19

    .line 29
    check-cast v13, Lcom/google/mlkit/genai/prompt/ImagePart;

    invoke-virtual {v13}, Lcom/google/mlkit/genai/prompt/ImagePart;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v15

    .line 30
    invoke-virtual {v13}, Lcom/google/mlkit/genai/prompt/ImagePart;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v13

    invoke-virtual {v13}, Landroid/graphics/Bitmap;->getConfig()Landroid/graphics/Bitmap$Config;

    move-result-object v13

    if-nez v13, :cond_16

    sget-object v13, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    .line 31
    :cond_16
    invoke-virtual {v15, v13, v11}, Landroid/graphics/Bitmap;->copy(Landroid/graphics/Bitmap$Config;Z)Landroid/graphics/Bitmap;

    move-result-object v13

    .line 32
    invoke-virtual {v13}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v15

    .line 33
    invoke-virtual {v13}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v10

    .line 34
    invoke-static {v15, v10}, Ljava/lang/Math;->min(II)I

    move-result v14

    const/16 v11, 0x300

    if-le v14, v11, :cond_18

    int-to-float v11, v14

    int-to-float v14, v15

    int-to-float v10, v10

    const/high16 v15, 0x44400000    # 768.0f

    div-float/2addr v15, v11

    mul-float/2addr v10, v15

    mul-float/2addr v14, v15

    float-to-int v11, v14

    float-to-int v10, v10

    const/4 v14, 0x0

    .line 35
    invoke-static {v13, v11, v10, v14}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v10

    if-eq v10, v13, :cond_17

    .line 36
    invoke-virtual {v13}, Landroid/graphics/Bitmap;->recycle()V

    :cond_17
    move-object v13, v10

    goto :goto_6

    :cond_18
    const/4 v14, 0x0

    :goto_6
    invoke-static {v14, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzi(ILandroid/graphics/Bitmap;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    move-result-object v10

    .line 38
    invoke-interface {v3, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_7

    :cond_19
    move v14, v11

    :goto_7
    move v11, v14

    const/4 v10, 0x2

    goto/16 :goto_5

    :cond_1a
    move v14, v11

    if-nez v12, :cond_1c

    if-eqz v6, :cond_1b

    .line 39
    invoke-virtual/range {p0 .. p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaan;->zzc()Z

    move-result v4

    if-eqz v4, :cond_1c

    .line 40
    invoke-static {v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzys;->zzc(Ljava/lang/String;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v14, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzj(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    move-result-object v4

    .line 41
    invoke-interface {v3, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_8

    :cond_1b
    const/4 v6, 0x0

    :cond_1c
    :goto_8
    if-eqz v0, :cond_1d

    new-instance v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyk;

    invoke-direct {v7, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyk;-><init>(Lcom/google/mlkit/genai/common/StreamingCallback;)V

    goto :goto_9

    :cond_1d
    const/4 v7, 0x0

    .line 42
    :goto_9
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzx()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    move-result-object v0

    if-eqz v6, :cond_1e

    .line 43
    invoke-virtual {v0, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzr(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 44
    :cond_1e
    invoke-static {v3}, Lkotlin/collections/CollectionsKt;->toList(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 45
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getTemperature()F

    move-result v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 46
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getTopK()I

    move-result v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 47
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zze(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/4 v3, 0x1

    .line 48
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 49
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getMaxOutputTokens()I

    move-result v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzg(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/4 v3, 0x2

    .line 50
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 51
    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 52
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getCandidateCount()I

    move-result v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzh(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 53
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getSeed()I

    move-result v3

    invoke-virtual {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 54
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest;->getEnableThinking()Z

    move-result v2

    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzs(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    if-eqz v7, :cond_1f

    .line 55
    invoke-virtual {v0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzi(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    :cond_1f
    if-eqz v1, :cond_20

    .line 56
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzq(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 57
    :cond_20
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;

    move-result-object v0

    return-object v0

    .line 16
    :cond_21
    new-instance v0, Lcom/google/mlkit/genai/common/GenAiException;

    const-string v1, "Input text length exceeds the limit. Please check the countTokens API."

    const/16 v2, 0xc

    const/4 v3, 0x0

    invoke-direct {v0, v1, v3, v2}, Lcom/google/mlkit/genai/common/GenAiException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;I)V

    throw v0
.end method

.method private static final zzf(I)I
    .locals 1

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/16 p0, -0x64

    return p0

    :cond_0
    return v0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;)Lcom/google/mlkit/genai/prompt/CountTokensResponse;
    .locals 0

    .line 1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdz;->zza()I

    move-result p0

    new-instance p1, Lcom/google/mlkit/genai/prompt/CountTokensResponse;

    invoke-direct {p1, p0}, Lcom/google/mlkit/genai/prompt/CountTokensResponse;-><init>(I)V

    return-object p1
.end method
