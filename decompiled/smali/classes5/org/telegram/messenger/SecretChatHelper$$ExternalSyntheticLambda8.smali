.class public final synthetic Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/SecretChatHelper;

.field public final synthetic f$1:J

.field public final synthetic f$2:Lorg/telegram/tgnet/TLRPC$EncryptedChat;

.field public final synthetic f$3:J


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/SecretChatHelper;JLorg/telegram/tgnet/TLRPC$EncryptedChat;J)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/messenger/SecretChatHelper;

    iput-wide p2, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$1:J

    iput-object p4, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$2:Lorg/telegram/tgnet/TLRPC$EncryptedChat;

    iput-wide p5, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$3:J

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 6

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/messenger/SecretChatHelper;

    iget-wide v1, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$1:J

    iget-object v3, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$2:Lorg/telegram/tgnet/TLRPC$EncryptedChat;

    iget-wide v4, p0, Lorg/telegram/messenger/SecretChatHelper$$ExternalSyntheticLambda8;->f$3:J

    invoke-static/range {v0 .. v5}, Lorg/telegram/messenger/SecretChatHelper;->$r8$lambda$xgmaC2sVh5giKNGzem_oGBwMW0U(Lorg/telegram/messenger/SecretChatHelper;JLorg/telegram/tgnet/TLRPC$EncryptedChat;J)V

    return-void
.end method
