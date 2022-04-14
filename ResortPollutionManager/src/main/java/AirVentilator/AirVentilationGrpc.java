package AirVentilator;

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
    comments = "Source: AirVentilation.proto")
public final class AirVentilationGrpc {

  private AirVentilationGrpc() {}

  public static final String SERVICE_NAME = "AirVentilator.AirVentilation";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<AirVentilator.everyHour,
      AirVentilator.ventilator> getAirVentilatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "airVentilator",
      requestType = AirVentilator.everyHour.class,
      responseType = AirVentilator.ventilator.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<AirVentilator.everyHour,
      AirVentilator.ventilator> getAirVentilatorMethod() {
    io.grpc.MethodDescriptor<AirVentilator.everyHour, AirVentilator.ventilator> getAirVentilatorMethod;
    if ((getAirVentilatorMethod = AirVentilationGrpc.getAirVentilatorMethod) == null) {
      synchronized (AirVentilationGrpc.class) {
        if ((getAirVentilatorMethod = AirVentilationGrpc.getAirVentilatorMethod) == null) {
          AirVentilationGrpc.getAirVentilatorMethod = getAirVentilatorMethod = 
              io.grpc.MethodDescriptor.<AirVentilator.everyHour, AirVentilator.ventilator>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "AirVentilator.AirVentilation", "airVentilator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AirVentilator.everyHour.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AirVentilator.ventilator.getDefaultInstance()))
                  .setSchemaDescriptor(new AirVentilationMethodDescriptorSupplier("airVentilator"))
                  .build();
          }
        }
     }
     return getAirVentilatorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AirVentilationStub newStub(io.grpc.Channel channel) {
    return new AirVentilationStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AirVentilationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AirVentilationBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AirVentilationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AirVentilationFutureStub(channel);
  }

  /**
   */
  public static abstract class AirVentilationImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * server streaming rpc
     * </pre>
     */
    public void airVentilator(AirVentilator.everyHour request,
        io.grpc.stub.StreamObserver<AirVentilator.ventilator> responseObserver) {
      asyncUnimplementedUnaryCall(getAirVentilatorMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAirVentilatorMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                AirVentilator.everyHour,
                AirVentilator.ventilator>(
                  this, METHODID_AIR_VENTILATOR)))
          .build();
    }
  }

  /**
   */
  public static final class AirVentilationStub extends io.grpc.stub.AbstractStub<AirVentilationStub> {
    private AirVentilationStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirVentilationStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirVentilationStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirVentilationStub(channel, callOptions);
    }

    /**
     * <pre>
     * server streaming rpc
     * </pre>
     */
    public void airVentilator(AirVentilator.everyHour request,
        io.grpc.stub.StreamObserver<AirVentilator.ventilator> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getAirVentilatorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AirVentilationBlockingStub extends io.grpc.stub.AbstractStub<AirVentilationBlockingStub> {
    private AirVentilationBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirVentilationBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirVentilationBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirVentilationBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * server streaming rpc
     * </pre>
     */
    public java.util.Iterator<AirVentilator.ventilator> airVentilator(
        AirVentilator.everyHour request) {
      return blockingServerStreamingCall(
          getChannel(), getAirVentilatorMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AirVentilationFutureStub extends io.grpc.stub.AbstractStub<AirVentilationFutureStub> {
    private AirVentilationFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirVentilationFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirVentilationFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirVentilationFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_AIR_VENTILATOR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AirVentilationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AirVentilationImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AIR_VENTILATOR:
          serviceImpl.airVentilator((AirVentilator.everyHour) request,
              (io.grpc.stub.StreamObserver<AirVentilator.ventilator>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AirVentilationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AirVentilationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AirVentilator.AirVentilatorImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AirVentilation");
    }
  }

  private static final class AirVentilationFileDescriptorSupplier
      extends AirVentilationBaseDescriptorSupplier {
    AirVentilationFileDescriptorSupplier() {}
  }

  private static final class AirVentilationMethodDescriptorSupplier
      extends AirVentilationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AirVentilationMethodDescriptorSupplier(String methodName) {
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
      synchronized (AirVentilationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AirVentilationFileDescriptorSupplier())
              .addMethod(getAirVentilatorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
