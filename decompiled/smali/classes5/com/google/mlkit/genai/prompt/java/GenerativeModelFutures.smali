.class public abstract Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0008&\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bJ\u0015\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00030\u0002H&\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0002H&\u00a2\u0006\u0004\u0008\u0007\u0010\u0005J\u001d\u0010\u000b\u001a\u0008\u0012\u0004\u0012\u00020\n0\u00022\u0006\u0010\t\u001a\u00020\u0008H&\u00a2\u0006\u0004\u0008\u000b\u0010\u000cJ\u0015\u0010\r\u001a\u0008\u0012\u0004\u0012\u00020\n0\u0002H&\u00a2\u0006\u0004\u0008\r\u0010\u0005J\u001d\u0010\u0011\u001a\u0008\u0012\u0004\u0012\u00020\u00100\u00022\u0006\u0010\u000f\u001a\u00020\u000eH&\u00a2\u0006\u0004\u0008\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0002H&\u00a2\u0006\u0004\u0008\u0013\u0010\u0005J\u0015\u0010\u0015\u001a\u0008\u0012\u0004\u0012\u00020\u00140\u0002H&\u00a2\u0006\u0004\u0008\u0015\u0010\u0005J\u0015\u0010\u0016\u001a\u0008\u0012\u0004\u0012\u00020\u00140\u0002H&\u00a2\u0006\u0004\u0008\u0016\u0010\u0005J\u001d\u0010\u0018\u001a\u0008\u0012\u0004\u0012\u00020\u00170\u00022\u0006\u0010\u000f\u001a\u00020\u000eH&\u00a2\u0006\u0004\u0008\u0018\u0010\u0012J%\u0010\u0018\u001a\u0008\u0012\u0004\u0012\u00020\u00170\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0019H&\u00a2\u0006\u0004\u0008\u0018\u0010\u001a\u00a8\u0006\u001c"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;",
        "",
        "Lcom/google/common/util/concurrent/ListenableFuture;",
        "",
        "getBaseModelName",
        "()Lcom/google/common/util/concurrent/ListenableFuture;",
        "",
        "checkStatus",
        "Lcom/google/mlkit/genai/common/DownloadCallback;",
        "callback",
        "Ljava/lang/Void;",
        "download",
        "(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;",
        "warmup",
        "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
        "request",
        "Lcom/google/mlkit/genai/prompt/CountTokensResponse;",
        "countTokens",
        "(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;",
        "getTokenLimit",
        "",
        "isSystemPromptAvailable",
        "isThinkingModeAvailable",
        "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
        "generateContent",
        "Lcom/google/mlkit/genai/common/StreamingCallback;",
        "(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)Lcom/google/common/util/concurrent/ListenableFuture;",
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
.field public static final Companion:Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->Companion:Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->Companion:Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;

    invoke-virtual {v0, p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures$Companion;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public abstract checkStatus()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end method

.method public abstract countTokens(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/CountTokensResponse;",
            ">;"
        }
    .end annotation
.end method

.method public abstract download(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/common/DownloadCallback;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Void;",
            ">;"
        }
    .end annotation
.end method

.method public abstract generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
            ">;"
        }
    .end annotation
.end method

.method public abstract generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/GenerateContentRequest;",
            "Lcom/google/mlkit/genai/common/StreamingCallback;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/GenerateContentResponse;",
            ">;"
        }
    .end annotation
.end method

.method public abstract getBaseModelName()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end method

.method public abstract getTokenLimit()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end method

.method public abstract isSystemPromptAvailable()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end method

.method public abstract isThinkingModeAvailable()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end method

.method public abstract warmup()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Void;",
            ">;"
        }
    .end annotation
.end method
