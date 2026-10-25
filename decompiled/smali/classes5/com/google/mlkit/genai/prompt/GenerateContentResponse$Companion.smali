.class public final Lcom/google/mlkit/genai/prompt/GenerateContentResponse$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/mlkit/genai/prompt/GenerateContentResponse;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0008\u0086\u0003\u0018\u00002\u00020\u0001\u00a8\u0006\n"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/GenerateContentResponse$Companion;",
        "",
        "<init>",
        "()V",
        "create",
        "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
        "candidates",
        "",
        "Lcom/google/mlkit/genai/prompt/Candidate;",
        "thoughtProcess",
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


# direct methods
.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final zzb(Ljava/util/List;Ljava/util/List;)Lcom/google/mlkit/genai/prompt/GenerateContentResponse;
    .locals 2

    new-instance v0, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;

    const/4 v1, 0x0

    invoke-direct {v0, p0, p1, v1}, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;-><init>(Ljava/util/List;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-object v0
.end method
