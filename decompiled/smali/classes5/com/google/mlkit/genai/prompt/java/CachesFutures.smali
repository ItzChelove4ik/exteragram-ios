.class public abstract Lcom/google/mlkit/genai/prompt/java/CachesFutures;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010 \n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0004\u0008&\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010J\u001d\u0010\u0006\u001a\u0008\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u001b\u0010\t\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u00050\u00080\u0004H&\u00a2\u0006\u0004\u0008\t\u0010\nJ\u001d\u0010\u000e\u001a\u0008\u0012\u0004\u0012\u00020\r0\u00042\u0006\u0010\u000c\u001a\u00020\u000bH&\u00a2\u0006\u0004\u0008\u000e\u0010\u000f\u00a8\u0006\u0011"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/java/CachesFutures;",
        "",
        "Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;",
        "request",
        "Lcom/google/common/util/concurrent/ListenableFuture;",
        "Lcom/google/mlkit/genai/prompt/CachedContext;",
        "create",
        "(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)Lcom/google/common/util/concurrent/ListenableFuture;",
        "",
        "list",
        "()Lcom/google/common/util/concurrent/ListenableFuture;",
        "",
        "name",
        "",
        "delete",
        "(Ljava/lang/String;)Lcom/google/common/util/concurrent/ListenableFuture;",
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
.field public static final Companion:Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/google/mlkit/genai/prompt/java/CachesFutures;->Companion:Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/CachesFutures;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/google/mlkit/genai/prompt/java/CachesFutures;->Companion:Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;

    invoke-virtual {v0, p0}, Lcom/google/mlkit/genai/prompt/java/CachesFutures$Companion;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/CachesFutures;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public abstract create(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Lcom/google/mlkit/genai/prompt/CachedContext;",
            ">;"
        }
    .end annotation
.end method

.method public abstract delete(Ljava/lang/String;)Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end method

.method public abstract list()Lcom/google/common/util/concurrent/ListenableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/common/util/concurrent/ListenableFuture<",
            "Ljava/util/List<",
            "Lcom/google/mlkit/genai/prompt/CachedContext;",
            ">;>;"
        }
    .end annotation
.end method
