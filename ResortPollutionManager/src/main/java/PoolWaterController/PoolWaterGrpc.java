package PoolWaterController;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: PoolWater.proto")
public final class PoolWaterGrpc {

  private PoolWaterGrpc() {}

  public static final String SERVICE_NAME = "PoolWaterController.PoolWater";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<PoolWaterController.phLevel,
      PoolWaterController.evacuate> getStopPoolEntryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "stopPoolEntry",
      requestType = PoolWaterController.phLevel.class,
      responseType = PoolWaterController.evacuate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<PoolWaterController.phLevel,
      PoolWaterController.evacuate> getStopPoolEntryMethod() {
    io.grpc.MethodDescriptor<PoolWaterController.phLevel, PoolWaterController.evacuate> getStopPoolEntryMethod;
    if ((getStopPoolEntryMethod = PoolWaterGrpc.getStopPoolEntryMethod) == null) {
      synchronized (PoolWaterGrpc.class) {
        if ((getStopPoolEntryMethod = PoolWaterGrpc.getStopPoolEntryMethod) == null) {
          PoolWaterGrpc.getStopPoolEntryMethod = getStopPoolEntryMethod = 
              io.grpc.MethodDescriptor.<PoolWaterController.phLevel, PoolWaterController.evacuate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "PoolWaterController.PoolWater", "stopPoolEntry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PoolWaterController.phLevel.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PoolWaterController.evacuate.getDefaultInstance()))
                  .setSchemaDescriptor(new PoolWaterMethodDescriptorSupplier("stopPoolEntry"))
                  .build();
          }
        }
     }
     return getStopPoolEntryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PoolWaterStub newStub(io.grpc.Channel channel) {
    return new PoolWaterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PoolWaterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PoolWaterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PoolWaterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PoolWaterFutureStub(channel);
  }

  /**
   */
  public static abstract class PoolWaterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<PoolWaterController.phLevel> stopPoolEntry(
        io.grpc.stub.StreamObserver<PoolWaterController.evacuate> responseObserver) {
      return asyncUnimplementedStreamingCall(getStopPoolEntryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStopPoolEntryMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                PoolWaterController.phLevel,
                PoolWaterController.evacuate>(
                  this, METHODID_STOP_POOL_ENTRY)))
          .build();
    }
  }

  /**
   */
  public static final class PoolWaterStub extends io.grpc.stub.AbstractStub<PoolWaterStub> {
    private PoolWaterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PoolWaterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PoolWaterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PoolWaterStub(channel, callOptions);
    }

    /**
     * <pre>
     * client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<PoolWaterController.phLevel> stopPoolEntry(
        io.grpc.stub.StreamObserver<PoolWaterController.evacuate> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getStopPoolEntryMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PoolWaterBlockingStub extends io.grpc.stub.AbstractStub<PoolWaterBlockingStub> {
    private PoolWaterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PoolWaterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PoolWaterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PoolWaterBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class PoolWaterFutureStub extends io.grpc.stub.AbstractStub<PoolWaterFutureStub> {
    private PoolWaterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PoolWaterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PoolWaterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PoolWaterFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STOP_POOL_ENTRY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PoolWaterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PoolWaterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STOP_POOL_ENTRY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.stopPoolEntry(
              (io.grpc.stub.StreamObserver<PoolWaterController.evacuate>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PoolWaterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PoolWaterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return PoolWaterController.PoolWaterImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PoolWater");
    }
  }

  private static final class PoolWaterFileDescriptorSupplier
      extends PoolWaterBaseDescriptorSupplier {
    PoolWaterFileDescriptorSupplier() {}
  }

  private static final class PoolWaterMethodDescriptorSupplier
      extends PoolWaterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PoolWaterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PoolWaterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PoolWaterFileDescriptorSupplier())
              .addMethod(getStopPoolEntryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
