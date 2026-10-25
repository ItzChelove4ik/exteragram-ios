.class public final synthetic Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda17;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/tgnet/TLClassStore$TLObjectFactory;


# direct methods
.method public synthetic constructor <init>()V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final create()Lorg/telegram/tgnet/TLObject;
    .locals 0

    .line 0
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_message_secret;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_message_secret;-><init>()V

    return-object p0
.end method
