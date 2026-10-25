.class Lcom/google/firebase/installations/FirebaseInstallations$2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/firebase/installations/internal/FidListenerHandle;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/firebase/installations/FirebaseInstallations;->registerFidListener(Lcom/google/firebase/installations/internal/FidListener;)Lcom/google/firebase/installations/internal/FidListenerHandle;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/firebase/installations/FirebaseInstallations;

.field final synthetic val$listener:Lcom/google/firebase/installations/internal/FidListener;


# direct methods
.method public constructor <init>(Lcom/google/firebase/installations/FirebaseInstallations;Lcom/google/firebase/installations/internal/FidListener;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 295
    iput-object p1, p0, Lcom/google/firebase/installations/FirebaseInstallations$2;->this$0:Lcom/google/firebase/installations/FirebaseInstallations;

    iput-object p2, p0, Lcom/google/firebase/installations/FirebaseInstallations$2;->val$listener:Lcom/google/firebase/installations/internal/FidListener;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
