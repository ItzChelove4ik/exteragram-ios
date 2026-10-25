.class public final synthetic Lcom/google/firebase/messaging/GmsRegistrationClient$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/tasks/Continuation;


# instance fields
.field public final synthetic f$0:Lcom/google/firebase/messaging/GmsRegistrationClient;


# direct methods
.method public synthetic constructor <init>(Lcom/google/firebase/messaging/GmsRegistrationClient;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/firebase/messaging/GmsRegistrationClient$$ExternalSyntheticLambda0;->f$0:Lcom/google/firebase/messaging/GmsRegistrationClient;

    return-void
.end method


# virtual methods
.method public final then(Lcom/google/android/gms/tasks/Task;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/google/firebase/messaging/GmsRegistrationClient$$ExternalSyntheticLambda0;->f$0:Lcom/google/firebase/messaging/GmsRegistrationClient;

    invoke-static {p0, p1}, Lcom/google/firebase/messaging/GmsRegistrationClient;->$r8$lambda$OUM1JmnF2iBVOuCeLVZtNrCCf24(Lcom/google/firebase/messaging/GmsRegistrationClient;Lcom/google/android/gms/tasks/Task;)Lcom/google/android/gms/tasks/Task;

    move-result-object p0

    return-object p0
.end method
