.class public final Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0008\u0086\u0003\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\u0008"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;",
        "",
        "<init>",
        "()V",
        "from",
        "Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;",
        "generativeModel",
        "Lcom/google/mlkit/genai/prompt/GenerativeModel;",
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


# virtual methods
.method public final from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;
    .locals 0
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 1
    new-instance p0, Lcom/google/mlkit/genai/prompt/java/zzal;

    invoke-direct {p0, p1}, Lcom/google/mlkit/genai/prompt/java/zzal;-><init>(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    return-object p0
.end method
