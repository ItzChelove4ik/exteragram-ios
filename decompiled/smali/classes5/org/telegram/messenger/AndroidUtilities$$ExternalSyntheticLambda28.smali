.class public final synthetic Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:[Z

.field public final synthetic f$1:[Lorg/telegram/ui/Components/ButtonSpan$TextViewButtons;

.field public final synthetic f$2:Lorg/telegram/utils/proxy/ProxySettings;

.field public final synthetic f$3:Landroid/app/Activity;


# direct methods
.method public synthetic constructor <init>([Z[Lorg/telegram/ui/Components/ButtonSpan$TextViewButtons;Lorg/telegram/utils/proxy/ProxySettings;Landroid/app/Activity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$0:[Z

    iput-object p2, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$1:[Lorg/telegram/ui/Components/ButtonSpan$TextViewButtons;

    iput-object p3, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$2:Lorg/telegram/utils/proxy/ProxySettings;

    iput-object p4, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$3:Landroid/app/Activity;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$0:[Z

    iget-object v1, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$1:[Lorg/telegram/ui/Components/ButtonSpan$TextViewButtons;

    iget-object v2, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$2:Lorg/telegram/utils/proxy/ProxySettings;

    iget-object p0, p0, Lorg/telegram/messenger/AndroidUtilities$$ExternalSyntheticLambda28;->f$3:Landroid/app/Activity;

    invoke-static {v0, v1, v2, p0}, Lorg/telegram/messenger/AndroidUtilities;->$r8$lambda$5nhaQy2PXxEWeSNnFoicKyQz_A8([Z[Lorg/telegram/ui/Components/ButtonSpan$TextViewButtons;Lorg/telegram/utils/proxy/ProxySettings;Landroid/app/Activity;)V

    return-void
.end method
