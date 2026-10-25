.class public final Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest$Builder;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0008\u0018\u00002\u00020\u0001:\u0001\u000cR\u0014\u0010\u0002\u001a\u00020\u0003X\u0086\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\u000b\u00a8\u0006\r"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;",
        "",
        "name",
        "",
        "promptPrefix",
        "Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "<init>",
        "(Ljava/lang/String;Lcom/google/mlkit/genai/prompt/PromptPrefix;)V",
        "getName",
        "()Ljava/lang/String;",
        "getPromptPrefix",
        "()Lcom/google/mlkit/genai/prompt/PromptPrefix;",
        "Builder",
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
.field private final zza:Ljava/lang/String;

.field private final zzb:Lcom/google/mlkit/genai/prompt/PromptPrefix;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lcom/google/mlkit/genai/prompt/PromptPrefix;Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->zzb:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    return-void
.end method


# virtual methods
.method public final getName()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final getPromptPrefix()Lcom/google/mlkit/genai/prompt/PromptPrefix;
    .locals 0

    iget-object p0, p0, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;->zzb:Lcom/google/mlkit/genai/prompt/PromptPrefix;

    return-object p0
.end method
