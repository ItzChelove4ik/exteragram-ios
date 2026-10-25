.class public final synthetic Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroid/graphics/Bitmap;

.field public final synthetic f$1:Ljava/lang/String;

.field public final synthetic f$2:I

.field public final synthetic f$3:Lcom/exteragram/messenger/appicons/AppIcon;

.field public final synthetic f$4:I


# direct methods
.method public synthetic constructor <init>(Landroid/graphics/Bitmap;Ljava/lang/String;ILcom/exteragram/messenger/appicons/AppIcon;I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$0:Landroid/graphics/Bitmap;

    iput-object p2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$1:Ljava/lang/String;

    iput p3, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$2:I

    iput-object p4, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$3:Lcom/exteragram/messenger/appicons/AppIcon;

    iput p5, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$4:I

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 4

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$0:Landroid/graphics/Bitmap;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$1:Ljava/lang/String;

    iget v2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$2:I

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$3:Lcom/exteragram/messenger/appicons/AppIcon;

    iget p0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;->f$4:I

    invoke-static {v0, v1, v2, v3, p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->$r8$lambda$5pAaOXpJhOh8Ffl7ErwtmWPqaac(Landroid/graphics/Bitmap;Ljava/lang/String;ILcom/exteragram/messenger/appicons/AppIcon;I)V

    return-void
.end method
