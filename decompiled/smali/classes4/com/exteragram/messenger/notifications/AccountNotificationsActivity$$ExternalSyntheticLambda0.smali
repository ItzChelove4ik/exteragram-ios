.class public final synthetic Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/core/util/Consumer;


# instance fields
.field public final synthetic f$0:I


# direct methods
.method public synthetic constructor <init>(I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$$ExternalSyntheticLambda0;->f$0:I

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$$ExternalSyntheticLambda0;->f$0:I

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;->$r8$lambda$0zg-YvVaHRH7kK1NsE-XFc5V7Yw(IZ)V

    return-void
.end method
