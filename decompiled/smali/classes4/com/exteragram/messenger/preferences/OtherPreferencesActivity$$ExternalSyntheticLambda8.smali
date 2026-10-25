.class public final synthetic Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/tgnet/RequestDelegate;


# instance fields
.field public final synthetic f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

.field public final synthetic f$1:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/ui/ActionBar/AlertDialog;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;->f$1:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    return-void
.end method


# virtual methods
.method public final run(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iget-object p0, p0, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity$$ExternalSyntheticLambda8;->f$1:Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    invoke-static {v0, p0, p1, p2}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;->$r8$lambda$yWCQpFPXkqfATo6phc_BXBXCqMQ(Lorg/telegram/ui/ActionBar/AlertDialog;Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method
