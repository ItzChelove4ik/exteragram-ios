.class public final Lcom/google/mlkit/genai/prompt/GenerationConfig;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/mlkit/genai/prompt/GenerationConfig$Builder;,
        Lcom/google/mlkit/genai/prompt/GenerationConfig$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\t\u0018\u0000 \r2\u00020\u0001:\u0002\u000c\rR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\u000b\u00a8\u0006\u000e"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/GenerationConfig;",
        "",
        "workerExecutor",
        "Ljava/util/concurrent/ExecutorService;",
        "modelConfig",
        "Lcom/google/mlkit/genai/prompt/ModelConfig;",
        "<init>",
        "(Ljava/util/concurrent/ExecutorService;Lcom/google/mlkit/genai/prompt/ModelConfig;)V",
        "getWorkerExecutor",
        "()Ljava/util/concurrent/ExecutorService;",
        "getModelConfig",
        "()Lcom/google/mlkit/genai/prompt/ModelConfig;",
        "Builder",
        "Companion",
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


# static fields
.field public static final Companion:Lcom/google/mlkit/genai/prompt/GenerationConfig$Companion;


# instance fields
.field private final zza:Ljava/util/concurrent/ExecutorService;

.field private final zzb:Lcom/google/mlkit/genai/prompt/ModelConfig;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/mlkit/genai/prompt/GenerationConfig$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/mlkit/genai/prompt/GenerationConfig$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/mlkit/genai/prompt/GenerationConfig;->Companion:Lcom/google/mlkit/genai/prompt/GenerationConfig$Companion;

    return-void
.end method

.method public synthetic constructor <init>(Ljava/util/concurrent/ExecutorService;Lcom/google/mlkit/genai/prompt/ModelConfig;Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/GenerationConfig;->zza:Ljava/util/concurrent/ExecutorService;

    iput-object p2, p0, Lcom/google/mlkit/genai/prompt/GenerationConfig;->zzb:Lcom/google/mlkit/genai/prompt/ModelConfig;

    return-void
.end method


# virtual methods
.method public final getModelConfig()Lcom/google/mlkit/genai/prompt/ModelConfig;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/GenerationConfig;->zzb:Lcom/google/mlkit/genai/prompt/ModelConfig;

    return-object p0
.end method

.method public final getWorkerExecutor()Ljava/util/concurrent/ExecutorService;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/GenerationConfig;->zza:Ljava/util/concurrent/ExecutorService;

    return-object p0
.end method
