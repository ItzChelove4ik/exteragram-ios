.class public final synthetic Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/appicons/AppIcon;

.field public final synthetic f$1:I

.field public final synthetic f$2:Z

.field public final synthetic f$3:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/appicons/AppIcon;IZLjava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$0:Lcom/exteragram/messenger/appicons/AppIcon;

    iput p2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$1:I

    iput-boolean p3, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$2:Z

    iput-object p4, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$3:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$0:Lcom/exteragram/messenger/appicons/AppIcon;

    iget v1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$1:I

    iget-boolean v2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$2:Z

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;->f$3:Ljava/lang/String;

    invoke-static {v0, v1, v2, p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->$r8$lambda$xCNjtrhQGHVyqdjOo4Qfmhh5oYI(Lcom/exteragram/messenger/appicons/AppIcon;IZLjava/lang/String;)V

    return-void
.end method
