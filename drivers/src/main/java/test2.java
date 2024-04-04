//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import project.api.drivers.controllers.CoachController;
//import project.api.drivers.models.Coach;
//import project.api.drivers.services.CoachService;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//@Component
//public class TestClass {
//
//    private CoachService coachService;
//    private Coach coach;
//
//    public void test(){
//        coach.setIdVehicle("72s2");
//        coach.setDriverList(Arrays.asList(1, 2, 3));
//        coach.setCapacity(50);
//        coach.setFuelType("Diesel");
//        coach.setStatus("Running");
//        coach.setRoute("Route 1");
//        coach.setVehicleType("Coach");
//        coach.setTimeStart(new Date());
//        coach.setTimeEnd(new Date());
//        coach.setNumberOfSeats(50);
//        coach.setNumberOfPassenger(30);
//        coach.setPreviousMaintenanceDate(new Date());
//        coach.setNextMaintenanceDate(new Date());
//        coach.setEmptySeat(Arrays.asList(31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50));
//        coach.setPassengerList(Arrays.asList("Passenger1", "Passenger2", "Passenger3"));
//    }
//
//
//
//    public static void main(String[] args) {
//        CoachService coachService1 = new CoachService();
//        CoachController coachController = new CoachController();
//        TestClass test = new TestClass();
//        test.test();
//    }
//}