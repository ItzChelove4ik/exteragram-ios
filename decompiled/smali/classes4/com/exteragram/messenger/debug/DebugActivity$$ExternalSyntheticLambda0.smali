.class public final synthetic Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/Utilities$CallbackReturn;


# instance fields
.field public final synthetic f$0:I


# direct methods
.method public synthetic constructor <init>(I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda0;->f$0:I

    return-void
.end method


# virtual methods
.method public final run(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget p0, p0, Lcom/exteragram/messenger/debug/DebugActivity$$ExternalSyntheticLambda0;->f$0:I

    check-cast p1, Ljava/lang/Integer;

    invoke-static {p0, p1}, Lcom/exteragram/messenger/debug/DebugActivity;->$r8$lambda$CbguHwmSr_eAdbsmez-XuoYkLaE(ILjava/lang/Integer;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method
