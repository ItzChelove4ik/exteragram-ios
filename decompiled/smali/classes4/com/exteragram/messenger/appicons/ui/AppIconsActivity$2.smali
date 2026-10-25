.class Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$2;
.super Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 143
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$2;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onScrolled(Landroidx/recyclerview/widget/RecyclerView;II)V
    .locals 0

    .line 146
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$2;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p0, p3}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mupdateCollapse(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    return-void
.end method
