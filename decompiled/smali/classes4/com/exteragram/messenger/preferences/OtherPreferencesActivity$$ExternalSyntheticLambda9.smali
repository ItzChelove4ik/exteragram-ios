.class public final synthetic Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

.field public final synthetic f$1:Lorg/telegram/tgnet/TLObject;

.field public final synthetic f$2:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

.field public final synthetic f$3:Lorg/telegram/tgnet/TLRPC$TL_error;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$1:Lorg/telegram/tgnet/TLObject;

    iput-object p3, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$2:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    iput-object p4, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$3:Lorg/telegram/tgnet/TLRPC$TL_error;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iget-object v1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$1:Lorg/telegram/tgnet/TLObject;

    iget-object v2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$2:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda9;->f$3:Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-static {v0, v1, v2, p0}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->$r8$lambda$wafXI4sX6H1dACsVEwON_Y0b8JM(Lorg/telegram/ui/ActionBar/AlertDialog;Lorg/telegram/tgnet/TLObject;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method
