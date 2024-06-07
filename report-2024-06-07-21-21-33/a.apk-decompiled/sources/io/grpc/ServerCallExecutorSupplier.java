package io.grpc;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public interface ServerCallExecutorSupplier {
    @Nullable
    <ReqT, RespT> Executor getExecutor(ServerCall<ReqT, RespT> serverCall, Metadata metadata);
}
