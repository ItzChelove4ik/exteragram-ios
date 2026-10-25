.class abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlt;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Ljava/util/stream/Collector;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlk;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlk;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlj;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlj;

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlh;

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzli;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzli;

    const/4 v4, 0x0

    new-array v5, v4, [Ljava/util/stream/Collector$Characteristics;

    invoke-static {v0, v1, v2, v3, v5}, Ljava/util/stream/Collector;->of(Ljava/util/function/Supplier;Ljava/util/function/BiConsumer;Ljava/util/function/BinaryOperator;Ljava/util/function/Function;[Ljava/util/stream/Collector$Characteristics;)Ljava/util/stream/Collector;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlt;->zza:Ljava/util/stream/Collector;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzls;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzls;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlr;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlr;

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlp;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlp;

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlq;

    new-array v5, v4, [Ljava/util/stream/Collector$Characteristics;

    .line 2
    invoke-static {v0, v1, v2, v3, v5}, Ljava/util/stream/Collector;->of(Ljava/util/function/Supplier;Ljava/util/function/BiConsumer;Ljava/util/function/BinaryOperator;Ljava/util/function/Function;[Ljava/util/stream/Collector$Characteristics;)Ljava/util/stream/Collector;

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlo;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlo;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzln;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzln;

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzll;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzll;

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;

    new-array v4, v4, [Ljava/util/stream/Collector$Characteristics;

    .line 3
    invoke-static {v0, v1, v2, v3, v4}, Ljava/util/stream/Collector;->of(Ljava/util/function/Supplier;Ljava/util/function/BiConsumer;Ljava/util/function/BinaryOperator;Ljava/util/function/Function;[Ljava/util/stream/Collector$Characteristics;)Ljava/util/stream/Collector;

    return-void
.end method

.method public static zza()Ljava/util/stream/Collector;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlt;->zza:Ljava/util/stream/Collector;

    return-object v0
.end method
