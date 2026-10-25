.class public final Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;
.super Lcom/google/mlkit/genai/common/DownloadStatus;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/mlkit/genai/common/DownloadStatus;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "DownloadCompleted"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0008\u00c6\u0002\u0018\u00002\u00020\u0001\u00a8\u0006\u0004"
    }
    d2 = {
        "Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;",
        "Lcom/google/mlkit/genai/common/DownloadStatus;",
        "<init>",
        "()V",
        "java.com.google.android.libraries.mlkit.granules.genai.common_mlkit_genai_common"
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
.field public static final INSTANCE:Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;

    invoke-direct {v0}, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;-><init>()V

    sput-object v0, Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;->INSTANCE:Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/google/mlkit/genai/common/DownloadStatus;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method
