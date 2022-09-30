package client;

import com.students_management.stubs.result.ResultServiceGrpc;
import com.students_management.stubs.student.StudentRequest;
import com.students_management.stubs.student.StudentResponse;
import com.students_management.stubs.student.StudentServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * @author ubektas
 */
public class StudentClient {
    private StudentServiceGrpc.StudentServiceBlockingStub studentServiceBlockingStub;

    public StudentClient(Channel channel) {
        studentServiceBlockingStub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public StudentResponse getResults(String studentId){
        StudentRequest studentRequest = StudentRequest.newBuilder().setStudentId(studentId).build();
        StudentResponse studentResponse = studentServiceBlockingStub.getStudentInfo(studentRequest);

        return  studentResponse;

    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext()
                .build();

        final StudentClient studentClient = new StudentClient(channel);
        System.out.println("getResults:");
        StudentResponse studentResponse = studentClient.getResults("st1");
        System.out.println("Name:"+ studentResponse.getName()+ "/"+ studentResponse.getArt());
    }


}
