.class final Lcom/google/android/recaptcha/internal/zzia;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/concurrent/Executor;


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzia;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzia;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzia;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzia;->zza:Lcom/google/android/recaptcha/internal/zzia;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final execute(Ljava/lang/Runnable;)V
    .locals 0

    .line 1
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void
.end method
