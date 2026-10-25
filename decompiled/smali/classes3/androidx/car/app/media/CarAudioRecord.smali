.class public abstract Landroidx/car/app/media/CarAudioRecord;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final AUDIO_CONTENT_BUFFER_SIZE:I = 0x200

.field public static final AUDIO_CONTENT_MIME:Ljava/lang/String; = "audio/l16"

.field public static final AUDIO_CONTENT_SAMPLING_RATE:I = 0x3e80

.field private static final RECORDSTATE_RECORDING:I = 0x1

.field private static final RECORDSTATE_REMOTE_CLOSED:I = 0x2

.field private static final RECORDSTATE_STOPPED:I


# instance fields
.field private final mCarContext:Landroidx/car/app/CarContext;

.field private mOpenMicrophoneResponse:Landroidx/car/app/media/OpenMicrophoneResponse;

.field private mRecordingState:I

.field private final mRecordingStateLock:Ljava/lang/Object;


# direct methods
.method public static synthetic $r8$lambda$eiaUsw7_X25Hzek8e7b5aQdG054()V
    .locals 0

    .line 0
    return-void
.end method

.method public static synthetic $r8$lambda$gO6P46xJLpbKFBEuOOe19YVnJeI(Landroidx/car/app/media/CarAudioRecord;)V
    .locals 2

    .line 198
    iget-object v0, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingStateLock:Ljava/lang/Object;

    .line 198
    monitor-enter v0

    const/4 v1, 0x2

    .line 199
    :try_start_0
    iput v1, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    .line 200
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public constructor <init>(Landroidx/car/app/CarContext;)V
    .locals 1

    .line 177
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 142
    iput v0, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    .line 148
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingStateLock:Ljava/lang/Object;

    .line 178
    iput-object p1, p0, Landroidx/car/app/media/CarAudioRecord;->mCarContext:Landroidx/car/app/CarContext;

    return-void
.end method

.method public static create(Landroidx/car/app/CarContext;)Landroidx/car/app/media/CarAudioRecord;
    .locals 1

    .line 158
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-object v0, p0

    check-cast v0, Landroid/content/Context;

    invoke-static {v0}, Landroidx/car/app/utils/CommonUtils;->isAutomotiveOS(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 159
    const-string v0, "androidx.car.app.media.AutomotiveCarAudioRecord"

    goto :goto_0

    .line 160
    :cond_0
    const-string v0, "androidx.car.app.media.ProjectedCarAudioRecord"

    .line 158
    :goto_0
    invoke-static {p0, v0}, Landroidx/car/app/media/CarAudioRecord;->createCarAudioRecord(Landroidx/car/app/CarContext;Ljava/lang/String;)Landroidx/car/app/media/CarAudioRecord;

    move-result-object p0

    return-object p0
.end method

.method private static createCarAudioRecord(Landroidx/car/app/CarContext;Ljava/lang/String;)Landroidx/car/app/media/CarAudioRecord;
    .locals 1

    .line 167
    :try_start_0
    invoke-static {p1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object p1

    .line 168
    const-class v0, Landroidx/car/app/CarContext;

    filled-new-array {v0}, [Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/Class;->getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;

    move-result-object p1

    .line 169
    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/media/CarAudioRecord;
    :try_end_0
    .catch Ljava/lang/ReflectiveOperationException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 171
    :catch_0
    const-string p0, "CarAudioRecord not configured. Did you forget to add a dependency on app-automotive or app-projected artifacts?"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public read([BII)I
    .locals 3

    .line 243
    iget-object v0, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 244
    :try_start_0
    iget v1, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    if-eqz v1, :cond_1

    const/4 v2, 0x2

    if-eq v1, v2, :cond_0

    .line 255
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 257
    invoke-virtual {p0, p1, p2, p3}, Landroidx/car/app/media/CarAudioRecord;->readInternal([BII)I

    move-result p0

    return p0

    :catchall_0
    move-exception p0

    goto :goto_0

    :cond_0
    const/4 p0, -0x1

    .line 250
    :try_start_1
    monitor-exit v0

    return p0

    .line 246
    :cond_1
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "Called read before calling startRecording or after calling stopRecording"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 255
    :goto_0
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method public abstract readInternal([BII)I
.end method

.method public startRecording()V
    .locals 4

    .line 189
    iget-object v0, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 190
    :try_start_0
    iget v1, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    if-nez v1, :cond_1

    .line 195
    iget-object v1, p0, Landroidx/car/app/media/CarAudioRecord;->mCarContext:Landroidx/car/app/CarContext;

    const-class v2, Landroidx/car/app/AppManager;

    .line 196
    invoke-virtual {v1, v2}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/car/app/AppManager;

    new-instance v2, Landroidx/car/app/media/OpenMicrophoneRequest$Builder;

    new-instance v3, Landroidx/car/app/media/CarAudioRecord$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0}, Landroidx/car/app/media/CarAudioRecord$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/media/CarAudioRecord;)V

    invoke-direct {v2, v3}, Landroidx/car/app/media/OpenMicrophoneRequest$Builder;-><init>(Landroidx/car/app/media/CarAudioCallback;)V

    .line 201
    invoke-virtual {v2}, Landroidx/car/app/media/OpenMicrophoneRequest$Builder;->build()Landroidx/car/app/media/OpenMicrophoneRequest;

    move-result-object v2

    .line 196
    invoke-virtual {v1, v2}, Landroidx/car/app/AppManager;->openMicrophone(Landroidx/car/app/media/OpenMicrophoneRequest;)Landroidx/car/app/media/OpenMicrophoneResponse;

    move-result-object v1

    iput-object v1, p0, Landroidx/car/app/media/CarAudioRecord;->mOpenMicrophoneResponse:Landroidx/car/app/media/OpenMicrophoneResponse;

    if-nez v1, :cond_0

    .line 203
    const-string v1, "CarApp"

    const-string v2, "Did not get microphone input from host"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 204
    new-instance v1, Landroidx/car/app/media/OpenMicrophoneResponse$Builder;

    new-instance v2, Landroidx/car/app/media/CarAudioRecord$$ExternalSyntheticLambda1;

    invoke-direct {v2}, Landroidx/car/app/media/CarAudioRecord$$ExternalSyntheticLambda1;-><init>()V

    invoke-direct {v1, v2}, Landroidx/car/app/media/OpenMicrophoneResponse$Builder;-><init>(Landroidx/car/app/media/CarAudioCallback;)V

    .line 205
    invoke-virtual {v1}, Landroidx/car/app/media/OpenMicrophoneResponse$Builder;->build()Landroidx/car/app/media/OpenMicrophoneResponse;

    move-result-object v1

    iput-object v1, p0, Landroidx/car/app/media/CarAudioRecord;->mOpenMicrophoneResponse:Landroidx/car/app/media/OpenMicrophoneResponse;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 207
    :cond_0
    :goto_0
    iget-object v1, p0, Landroidx/car/app/media/CarAudioRecord;->mOpenMicrophoneResponse:Landroidx/car/app/media/OpenMicrophoneResponse;

    invoke-virtual {p0, v1}, Landroidx/car/app/media/CarAudioRecord;->startRecordingInternal(Landroidx/car/app/media/OpenMicrophoneResponse;)V

    const/4 v1, 0x1

    .line 209
    iput v1, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    .line 210
    monitor-exit v0

    return-void

    .line 191
    :cond_1
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot start recording if it has started and not been stopped"

    invoke-direct {p0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 210
    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public abstract startRecordingInternal(Landroidx/car/app/media/OpenMicrophoneResponse;)V
.end method

.method public stopRecording()V
    .locals 4

    .line 215
    iget-object v0, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 216
    :try_start_0
    iget-object v1, p0, Landroidx/car/app/media/CarAudioRecord;->mOpenMicrophoneResponse:Landroidx/car/app/media/OpenMicrophoneResponse;

    if-eqz v1, :cond_1

    .line 217
    iget v2, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    const/4 v3, 0x2

    if-eq v2, v3, :cond_0

    .line 219
    invoke-virtual {v1}, Landroidx/car/app/media/OpenMicrophoneResponse;->getCarAudioCallback()Landroidx/car/app/media/CarAudioCallbackDelegate;

    move-result-object v1

    invoke-virtual {v1}, Landroidx/car/app/media/CarAudioCallbackDelegate;->onStopRecording()V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    const/4 v1, 0x0

    .line 221
    iput-object v1, p0, Landroidx/car/app/media/CarAudioRecord;->mOpenMicrophoneResponse:Landroidx/car/app/media/OpenMicrophoneResponse;

    .line 224
    :cond_1
    invoke-virtual {p0}, Landroidx/car/app/media/CarAudioRecord;->stopRecordingInternal()V

    const/4 v1, 0x0

    .line 225
    iput v1, p0, Landroidx/car/app/media/CarAudioRecord;->mRecordingState:I

    .line 226
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public abstract stopRecordingInternal()V
.end method
