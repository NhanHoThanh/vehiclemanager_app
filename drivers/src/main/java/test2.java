//public void removePassenger(String idVehicle, String idPassenger, Coach coach) throws ExecutionException, InterruptedException {
//        Firestore db = FirestoreClient.getFirestore();
//        DocumentReference docRef = db.collection("Coach").document(idVehicle);
//        DocumentReference passengerDocRef = db.collection("Passenger").document(idPassenger);
//
//        // Get Passenger's seatingPosition
//        ApiFuture<DocumentSnapshot> passengerFuture = passengerDocRef.get();
//        DocumentSnapshot passengerDocument = passengerFuture.get();
//        if (passengerDocument.exists()) {
//        Integer seatingPosition = passengerDocument.getLong("seatingPosition").intValue();
//
//        // Get Coach's emptySeat
//        ApiFuture<DocumentSnapshot> future = docRef.get();
//        DocumentSnapshot document = future.get();
//        if (document.exists()) {
//        List<Long> emptySeat = (List<Long>) document.get("emptySeat");
//        if (emptySeat != null) {
//        // Remove seatingPosition from emptySeat
//        emptySeat.remove(seatingPosition.longValue());
//
//        // Update Coach's emptySeat
//        ApiFuture<WriteResult> updateCoachFuture = docRef.update("emptySeat", emptySeat);
//        updateCoachFuture.get();
//        }
//
//        // Remove passenger from passengerList
//        List<String> passengerList = (List<String>) document.get("passengerList");
//        if (passengerList != null && passengerList.contains(idPassenger)) {
//        passengerList.remove(idPassenger);
//
//        // Update Coach's passengerList
//        ApiFuture<WriteResult> updatePassengerListFuture = docRef.update("passengerList", passengerList);
//        updatePassengerListFuture.get();
//
//        // Decrement numberOfPassenger
//        Long numberOfPassenger = document.getLong("numberOfPassenger");
//        if (numberOfPassenger == null) {
//        numberOfPassenger = 0L;
//        }
//        numberOfPassenger--;
//
//        // Update Coach's numberOfPassenger
//        ApiFuture<WriteResult> updateNumberOfPassengerFuture = docRef.update("numberOfPassenger", numberOfPassenger.intValue());
//        updateNumberOfPassengerFuture.get();
//        } else {
//        System.out.println("idpassenger không tồn tại trong danh sách passengerList.");
//        }
//        } else {
//        System.out.println("Không tìm thấy tài liệu với id: " + idVehicle);
//        }
//        }
//        }