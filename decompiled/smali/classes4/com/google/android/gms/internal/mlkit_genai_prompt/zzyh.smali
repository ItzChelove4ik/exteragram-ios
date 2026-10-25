.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyh;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Lkotlin/reflect/KClass;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;
    .locals 2

    .line 1
    const-class p0, Ljava/lang/String;

    invoke-static {p0}, Lkotlin/jvm/internal/Reflection;->getOrCreateKotlinClass(Ljava/lang/Class;)Lkotlin/reflect/KClass;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    const/4 v1, 0x2

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    goto/16 :goto_0

    .line 20
    :cond_0
    sget-object p0, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    .line 2
    invoke-static {p0}, Lkotlin/jvm/internal/Reflection;->getOrCreateKotlinClass(Ljava/lang/Class;)Lkotlin/reflect/KClass;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    const/4 v1, 0x4

    if-eqz p0, :cond_1

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    const-string v1, "int32"

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    goto :goto_0

    :cond_1
    sget-object p0, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    .line 3
    invoke-static {p0}, Lkotlin/jvm/internal/Reflection;->getOrCreateKotlinClass(Ljava/lang/Class;)Lkotlin/reflect/KClass;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_2

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    const-string v1, "int64"

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    goto :goto_0

    :cond_2
    sget-object p0, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    .line 4
    invoke-static {p0}, Lkotlin/jvm/internal/Reflection;->getOrCreateKotlinClass(Ljava/lang/Class;)Lkotlin/reflect/KClass;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_3

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    const/4 v1, 0x5

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    goto :goto_0

    :cond_3
    sget-object p0, Ljava/lang/Float;->TYPE:Ljava/lang/Class;

    .line 5
    invoke-static {p0}, Lkotlin/jvm/internal/Reflection;->getOrCreateKotlinClass(Ljava/lang/Class;)Lkotlin/reflect/KClass;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    const/4 v1, 0x3

    if-eqz p0, :cond_4

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    const-string v1, "float"

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    goto :goto_0

    :cond_4
    sget-object p0, Ljava/lang/Double;->TYPE:Ljava/lang/Class;

    .line 6
    invoke-static {p0}, Lkotlin/jvm/internal/Reflection;->getOrCreateKotlinClass(Ljava/lang/Class;)Lkotlin/reflect/KClass;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_5

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    move-result-object p0

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    const-string v1, "double"

    invoke-virtual {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;

    goto :goto_0

    :cond_5
    move-object p0, v0

    :goto_0
    if-eqz p0, :cond_6

    return-object p0

    .line 7
    :cond_6
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyg;->zzb()Ljava/util/Map;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 21
    invoke-interface {p1}, Lkotlin/reflect/KClass;->getSimpleName()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string p1, " is neither a registered @Generable type nor a directly supported basic type (String, Int, Boolean, etc.)."

    invoke-virtual {p0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 22
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method
