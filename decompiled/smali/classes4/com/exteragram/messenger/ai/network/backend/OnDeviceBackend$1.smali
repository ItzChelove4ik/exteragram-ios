.class Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/common/StreamingCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->generate(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;Lcom/google/mlkit/genai/prompt/GenerativeModel;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;

.field final synthetic val$accumulated:Ljava/lang/StringBuffer;

.field final synthetic val$sink:Lcom/exteragram/messenger/ai/network/backend/BackendSink;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Ljava/lang/StringBuffer;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 231
    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->this$0:Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$sink:Lcom/exteragram/messenger/ai/network/backend/BackendSink;

    iput-object p3, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$accumulated:Ljava/lang/StringBuffer;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onNewText(Ljava/lang/String;)V
    .locals 1

    .line 234
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$sink:Lcom/exteragram/messenger/ai/network/backend/BackendSink;

    invoke-interface {v0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 237
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$accumulated:Ljava/lang/StringBuffer;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 238
    iget-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$sink:Lcom/exteragram/messenger/ai/network/backend/BackendSink;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$accumulated:Ljava/lang/StringBuffer;

    invoke-virtual {p0}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {p1, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onChunk(Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public onNewThought(Ljava/lang/String;)V
    .locals 0

    .line 243
    iget-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$sink:Lcom/exteragram/messenger/ai/network/backend/BackendSink;

    invoke-interface {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 244
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;->val$sink:Lcom/exteragram/messenger/ai/network/backend/BackendSink;

    invoke-interface {p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onThinking()V

    :cond_0
    return-void
.end method
